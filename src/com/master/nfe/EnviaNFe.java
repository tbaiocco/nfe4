package com.master.nfe;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import br.cte.base.EmpresaDb;
import br.cte.model.Empresa;

import com.master.ed.ManifestoED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.rn.MDFeRN;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;


/**
 * Servlet implementation class for Servlet: EnviaNFe
 *
 */
 public class EnviaNFe extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	 Empresa empresa = new Empresa();

	public EnviaNFe() {
		super();
	}

	private void enviaRecebeNfe(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		System.out.println("Servlet..."+request.getParameter("emissor"));
        boolean enviaEmail = false;
		try{
			if(JavaUtil.doValida(request.getParameter("emissor"))){
				empresa = new EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if(!JavaUtil.doValida(empresa.getRazaosocial())){
					throw new Excecoes("A empresa emitente nao foi encontrada no sistema!");
				}
			} else {
				throw new Excecoes("A empresa emitente nao foi informada!");
			}
			Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaED();

			if(JavaUtil.doValida(request.getParameter("oid_Nota_Fiscal"))){

				Certificado certificado = CertificadoService.certificadoPfx(
	             		empresa.getCertificado(), 
	             		empresa.getSenha());
	             //Esse Objeto Voce pode guardar em uma Session.
	             ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(Estados.RS,
	                     empresa.getAmbiente(),
	                     certificado,
	                     MethodHandles.lookup().lookupClass().
	                     getResource("/schemas").getPath(), //PEGAR SCHEMAS EM AMBIENTE WEB ESTA PASTA ESTA DENTRO DE RESOURCES
	                     true);
				//busca dados envio
	             TNFe nfe = new Nota_Fiscal_EletronicaRN(empresa).geraNFe(request.getParameter("oid_Nota_Fiscal"), Data.getDataDMY(), Data.getHoraHM());
	             
System.out.println("WS MDFe");
//				mdfe = new MDFeTeste().getMdfe(1);


System.out.println("enviando :"+nfe.getInfNFe().getId() + "|"+nfe.getInfNFe().getIde().getCNF());
				// Monta EnviNfe
	            TEnviNFe enviNFe = new TEnviNFe();
	            enviNFe.setVersao("4.00");
	            enviNFe.setIdLote("1");
	            enviNFe.setIndSinc("1");
	            enviNFe.getNFe().add(nfe);

	            // Monta e Assina o XML
	            enviNFe = Nfe.montaNfe(enviNFe, true);

	            // Envia a Nfe para a Sefaz
	            TRetEnviNFe retorno = NfeWeb.enviarNfe(config, enviNFe, ConstantesUtil.NFE);

	            if (!retorno.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
	                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
	            }

	            if (!retorno.getProtNFe().getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
	                throw new NfeException("Status:" + retorno.getProtNFe().getInfProt().getCStat() + " - Motivo:" + retorno.getProtNFe().getInfProt().getXMotivo());
	            }

	            System.out.println("Status:" + retorno.getProtNFe().getInfProt().getCStat());
	            System.out.println("Motivo:" + retorno.getProtNFe().getInfProt().getXMotivo());
	            System.out.println("Data:" + retorno.getProtNFe().getInfProt().getDhRecbto());
	            System.out.println("Protocolo:" + retorno.getProtNFe().getInfProt().getNProt());

	            System.out.println("Xml Final :" + XmlUtil.criaNfeProc(enviNFe, retorno.getProtNFe()));


//				if (retorno != null) {
//					System.out.println("Retorno 1.00 ok!!!\n"
//		                    + " CHAVE: " + retorno.getNfeNotaFiscal().getChaveAcesso() + "\n"
//		                    + " Recibo: " + retorno.getNRec() + "\n"
//		                    + " Data: " + retorno.getData() + "\n"
//		                    + " cstat: " + retorno.getCStat() + " \n"
//		                    + " Motivo: " + retorno.getXMotivo());
//
//		            if (retorno.getNRec() != null) {
//		            	System.out.println("aguardando 5s para consultar recibo!!!");
//			            Thread.sleep(5000);
//
//			            OK = this.updateRetornoLote(ed, retorno);
//
//			            if (retorno.getNfeNotaFiscal() != null) {
//			                System.out.println("ChAcessoGerada: " + retorno.getNfeNotaFiscal().getChaveAcesso()+"\n");
//
////			                System.out.println("iniciando consulta de recibo... ");
//			                NfeRetornoEnvioLote ret = null;
//
//			                if (notafiscal.getIndSinc() == 0) {
//			                    System.out.println("iniciando consulta de recibo... ");
//			                    try {
//			                        System.out.println("aguardando 3s para consultar recibo!!!");
//			                        Thread.sleep(3000);
//			                    } catch (InterruptedException ex) {
//			                        ex.printStackTrace();
//			                    }
//			                    ret = servico.retornoEnvioNfe(retorno.getNRec(), retorno.getNfeNotaFiscal());
//			                } else if (retorno.getNfeRetornoEnvioLote() != null) {
//			                    ret = retorno.getNfeRetornoEnvioLote();
//
//			                    System.out.println("iniciando consulta de recibo 2... ");
//			                    try {
//			                        System.out.println("aguardando 3s para consultar recibo!!!");
//			                        Thread.sleep(3000);
//			                    } catch (InterruptedException ex) {
//			                        ex.printStackTrace();
//			                    }
//			                    ret = servico.retornoEnvioNfe(retorno.getNRec(), retorno.getNfeNotaFiscal());
//			                }
//
//			                if (ret != null) {
//			                	System.out.println("Retorno NFE 3.10 ok!!!\n"
//			                            + " Protocolo: " + ret.getnProt() + "\n"
//			                            + " Data: " + ret.getDhRecbto() + "\n"
//			                            + " cstat: " + ret.getcStat() + "\n"
//			                            + " Motivo: " + ret.getxMotivo());
//			                    nfReturn = notafiscal;
//			                    nfReturn.setChaveAcesso(retorno.getNfeNotaFiscal().getChaveAcesso());
//			                    nfReturn.setProtocolo(ret.getnProt());
//			                    nfReturn.setDhRecbto(Utils.getInstance().convertStringDateSefaztoData(ret.getDhRecbto()));
//			                    nfReturn.setcStat(Integer.parseInt(ret.getcStat()));
//			                    nfReturn.setxMotivo(ret.getxMotivo());
//			                    //update na NF
////			                    if(JavaUtil.doValida(ed.getNfe_cstat()) && "100".equals(ed.getNfe_cstat())){
//			        //
////			                    } else {
//			                    	OK = this.updateRetornoNFE(ed, retorno, ret);
////			                    }
//			                } else {
//			                	throw new Mensagens("Erro ao buscar NFE 3.10...\n\r"+JavaUtil.getErrors(servico.getErros()).toUpperCase());
//			                }
//			            } else {
//			            	throw new Mensagens("Retorno do lote n�o tem a nfe...\n\r");
//			            }
//		            } else {
//		            	throw new Excecoes("Envio do lote n�o gerou RECIBO para o MDFe...\n\r");
//		            }
//		        } else {
//		            throw new Excecoes(""+JavaUtil.getErrors(servico.getErros()).toUpperCase());
//		        }

			}

		} catch(Excecoes e){
			System.out.println("PROBLEMA!!!");
			e.printStackTrace();
			throw new ServletException(e);
		} catch (Exception e){
			//nada
//			JavaUtil.getErrors(servico.getErros()).toUpperCase();
			System.out.println("PROBLEMA erro!!!");
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String retorno = request.getParameter("urlRetono");
		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");

		enviaRecebeNfe(request,response);

		if(JavaUtil.doValida(retorno) && JavaUtil.doValida(oid_Nota_Fiscal)){
			response.sendRedirect(retorno+"?oid_Nota_Fiscal="+oid_Nota_Fiscal+"&eRequest=true&acao=S&Busca_Campo=Nota_Fiscal");
		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String retorno = request.getParameter("urlRetono");
		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");

		enviaRecebeNfe(request,response);

		if(JavaUtil.doValida(retorno) && JavaUtil.doValida(oid_Nota_Fiscal)){
			response.sendRedirect(retorno+"?oid_Nota_Fiscal="+oid_Nota_Fiscal+"&eRequest=true&acao=S&Busca_Campo=Nota_Fiscal");
		}
	}
}