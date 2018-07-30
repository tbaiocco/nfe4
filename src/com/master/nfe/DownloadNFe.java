package com.master.nfe;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import br.nfe.core.ClassesNfe.v200.RetDownloadNFe;
import br.nfe.core.base.EmpresaDb;
import br.nfe.model.Empresa;
import br.nfe.model.NfeCce;
import br.nfe.utils.Arquivo;
import br.nfe.utils.Configuracoes;
import br.nfe.utils.Utils;
import br.servicos.NfeServicos;

import com.master.cte.RetornaMensagem;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.JavaUtil;

public class DownloadNFe extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	Empresa empresa = new Empresa();
	
	public DownloadNFe() {
		super();
	}
	
	private String downloadNfe(HttpServletRequest request, HttpServletResponse response){
		NfeServicos servico = new NfeServicos();
		try{
			String cnpjDestinatario = "";
			if(JavaUtil.doValida(request.getParameter("emissor"))){
				empresa = new EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if(!JavaUtil.doValida(empresa.getRazaosocial())){
					throw new Excecoes("A empresa emitente não foi encontrada no sistema!");
				}
			} else {
				throw new Excecoes("A empresa emitente não foi informada!");
			}
			cnpjDestinatario = empresa.getCnpj();
//			if(JavaUtil.doValida(request.getParameter("destinatario"))){
//				cnpjDestinatario = request.getParameter("destinatario");
//			} else {
//				throw new Excecoes("O destinatário deve ser informado!");
//			}
			String chave = request.getParameter("chaveBuscar");
			if(JavaUtil.doValida(chave)) {
				String arqXML = Configuracoes.getInstance().getAppDir() + "/tmp/ret_"+chave+".xml";
				int amb = new Integer(JavaUtil.getValueDef(empresa.getAmbiente(),"1")).intValue();
				RetDownloadNFe retorno = servico.DowloadNfe(amb, Utils.getInstance().getDigitos(empresa.getCnpj()), Utils.getInstance().getDigitos(cnpjDestinatario), chave);
				String cStat = "";
		        if (retorno != null) {
		            System.out.println("Retorno 2.00 ok!!!\n"
		                    + " Data: " + retorno.getDhResp() + "\n"
		                    + " cstat: " + retorno.getcStat() + "\n"
		                    + " Motivo: " + retorno.getxMotivo() + "\n"
		                    + " XML: " + retorno.getRetNFe());
		            System.out.println("--------------------------------------------- \n");
		            cStat = retorno.getRetNFe().getcStat();
		            if(JavaUtil.doValida(cStat) && "633".equals(cStat)) {
		            	System.out.println("comunicando...");
		            	if(!comunicaCienciaNFe(chave, Utils.getInstance().getDigitos(empresa.getCnpj()), amb)) {
		            		System.out.println("Erro ao comunicar operação!");
		            		 throw new Excecoes("Não foi possível comunicar a operação à SEFAZ!");
		            	} else {
		            		retorno = servico.DowloadNfe(amb, Utils.getInstance().getDigitos(empresa.getCnpj()), Utils.getInstance().getDigitos(cnpjDestinatario), chave);
		        	        if (retorno != null) {
		        	            System.out.println("Retorno 2.00 ok!!!\n"
		        	                    + " Data: " + retorno.getDhResp() + "\n"
		        	                    + " cstat: " + retorno.getcStat() + "\n"
		        	                    + " Motivo: " + retorno.getxMotivo() + "\n"
		        	                    + " cstat NFe: " + retorno.getRetNFe().getcStat() + "\n"
		        	                    + " Motivo NFe: " + retorno.getRetNFe().getxMotivo() + "\n"
		        	                    + " XML: " + retorno.getRetNFe());
		        	            System.out.println("--------------------------------------------- \n");
		        	            cStat = retorno.getRetNFe().getcStat();
		        	        } else {
		        	        	System.out.println("Erro ao buscar status do servico 2.00 ... consulte hashMap");
		        	        	 throw new Excecoes(""+JavaUtil.getErrors(servico.getErros()).toUpperCase());
		        	        }
		            	}
		            }
		            
		            if(!"140".equals(cStat)) {
		            	throw new Excecoes("ERRO: " + cStat + " - " + retorno.getRetNFe().getxMotivo());
		            }
		            
		            String zippedBase64Str = retorno.getRetNFe().getProcNFeGrupoZip().getNFeZip();
		            byte[] bytesNFe = org.apache.commons.codec.binary.Base64.decodeBase64(zippedBase64Str.getBytes());
		            zippedBase64Str = retorno.getRetNFe().getProcNFeGrupoZip().getProtNFeZip();
		            byte[] bytesProt = org.apache.commons.codec.binary.Base64.decodeBase64(zippedBase64Str.getBytes());
		            GZIPInputStream zi = null;
		            GZIPInputStream ziProt = null;
		            Arquivo a = new Arquivo(arqXML);
		            try {
		              zi = new GZIPInputStream(new ByteArrayInputStream(bytesNFe));
		              String conteudo = IOUtils.toString(zi);
		              ziProt = new GZIPInputStream(new ByteArrayInputStream(bytesProt));
		              String conteudoProt = IOUtils.toString(ziProt);
		              System.out.println(conteudo);
		              System.out.println(conteudoProt);
		              a.abrirEscrita();
		              a.escreverLinha("<nfeProc xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"3.10\">");
		              if(conteudo.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\"?>"))
		            	  conteudo = conteudo.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
		              a.escreverLinha(conteudo);
		              if(conteudoProt.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\"?>"))
		            	  conteudoProt = conteudoProt.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
		              a.escreverLinha(conteudoProt);
		              a.escreverLinha("</nfeProc>");
		            } finally {
		            	a.fecharArquivo();
		            	IOUtils.closeQuietly(zi);
		            	IOUtils.closeQuietly(ziProt);
		            }
		        } else {
		            System.out.println("Erro ao buscar status do servico 2.00 ... consulte hashMap");
		            throw new Excecoes(""+JavaUtil.getErrors(servico.getErros()).toUpperCase());
		        }
		        return arqXML;
			} else {
				System.out.println("Chave da NFe não informada");
	            throw new Excecoes("Chave da NFe não informada\n\r");
			}
			
			
		} catch(Excecoes e){
			System.out.println("deu erro!!!");
			e.printStackTrace();
			RetornaMensagem.montaTelaErro(request, response, e);
		} catch (Exception e){
			//nada
//			JavaUtil.getErrors(servico.getErros()).toUpperCase();
			System.out.println("deu erro!!!");
			e.printStackTrace();
			RetornaMensagem.montaTelaErro(request, response, new Excecoes(""+JavaUtil.getErrors(servico.getErros()).toUpperCase()));
		}
		return null;
	}
	
	private boolean comunicaCienciaNFe (String chave, String emissor, int ambiente) {
		NfeServicos servico = new NfeServicos();
		try {
			NfeCce cce = new NfeCce();
			if(chave.startsWith("43")) 
				cce.setcOrgao("43");
			else
				cce.setcOrgao("91");
            cce.setCNPJ(emissor);
//            cce.setnProt(nota.getProtocolo());
            cce.setDescEvento("Ciencia da Operacao");
            cce.setnSeqEvento(1);
            cce.setTpEvento(210210);
            cce.setDhEvento(new Date());
            cce.setVerEvento("1.00");
            cce.setVersao("1.00");
            cce.setTpAmbiente(ambiente);
            cce.setChNFe(chave);

	        NfeCce retorno = servico.recepcaoEventoNfe(cce.getTpAmbiente(), cce.getCNPJ(), cce);
	        if (retorno != null) {
	            System.out.println("Retorno evento 2.00 ok!!!\n"
	                    + " Protocolo: " + retorno.getnProt() + "\n"
	                    + " Data: " + retorno.getDhRegEvento() + "\n"
	                    + " cstat: " + retorno.getcStat() + " \n"
	                    + " Motivo: " + retorno.getxMotivo());
	           
	            if("135".equals(retorno.getcStat()) || "136".equals(retorno.getcStat())) {
	            	return true;
	            } else {
	            	return false;
	            }

	        } else {
	            System.out.println("Erro ao buscar cancelamento 2.00 ... consulte hashMap");
	            JavaUtil.showErrors(servico.getErros());
	            return false;
	        }
		} catch (Exception e) {
			return false;
		}
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String retorno = request.getParameter("urlRetono");
		String chave = request.getParameter("FT_NM_Chave_NFE");
		String dm_tipo_edi = request.getParameter("FT_DM_Tipo_EDI");

		String arquivoGerado = downloadNfe(request,response);

		if(JavaUtil.doValida(retorno) && JavaUtil.doValida(arquivoGerado)){
//			response.sendRedirect(retorno+"?arquivoGerado="+arquivoGerado+"&FT_NM_Chave_NFE="+chave+"&eRequest=true&acao=abreXML&Busca_Campo=XML");
			response.sendRedirect(retorno+"?arquivoGerado="+arquivoGerado+"&FT_NM_Chave_NFE="+chave+"&FT_DM_Tipo_EDI="+dm_tipo_edi+"&eRequest=true&acao=importaXML&Busca_Campo=XML");
		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String retorno = request.getParameter("urlRetono");
		String chave = request.getParameter("FT_NM_Chave_NFE");
		String dm_tipo_edi = request.getParameter("FT_DM_Tipo_EDI");

		String arquivoGerado = downloadNfe(request,response);

		if(JavaUtil.doValida(retorno) && JavaUtil.doValida(arquivoGerado)){
//			response.sendRedirect(retorno+"?arquivoGerado="+arquivoGerado+"&FT_NM_Chave_NFE="+chave+"&eRequest=true&acao=abreXML&Busca_Campo=XML");
			response.sendRedirect(retorno+"?arquivoGerado="+arquivoGerado+"&FT_NM_Chave_NFE="+chave+"&FT_DM_Tipo_EDI="+dm_tipo_edi+"&eRequest=true&acao=importaXML&Busca_Campo=XML");
		}
	}
}
