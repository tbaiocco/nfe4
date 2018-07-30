package com.master.nfe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mdfe.model.MdfeLote;
import br.mdfe.model.MdfeRetornoEnvioLote;
import br.model.Empresa;
import br.model.NfeLote;
import br.model.NfeNotaFiscal;
import br.model.NfeRetornoEnvioLote;
import br.servicos.NfeServicos;

import com.master.cte.RetornaMensagem;
import com.master.ed.ManifestoED;
import com.master.rn.MDFeRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

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

	private void enviaRecebeNfe(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("Servlet..."+request.getParameter("emissor"));
		NfeNotaFiscal notafiscal = new NfeNotaFiscal();
		NfeServicos servico = new NfeServicos();
        boolean enviaEmail = false;
		try{
			if(JavaUtil.doValida(request.getParameter("emissor"))){
				empresa = new br.core.base.EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if(!JavaUtil.doValida(empresa.getRazaosocial())){
					throw new Excecoes("A empresa emitente não foi encontrada no sistema!");
				}
				if(!JavaUtil.doValida(empresa.getDbURL())){
					throw new Excecoes("A empresa emitente não possui parâmetro de conexão ao Banco de Dados!");
				}
				System.out.println("------ Dados de conexao BD:");
				System.out.println("DRIVER:"+empresa.getDbDriver());
				System.out.println("URL:"+empresa.getDbURL());
				System.out.println("PORT:"+empresa.getDbPort());
				System.out.println("USER:"+empresa.getDbUser());
				System.out.println("PASS:"+empresa.getDbPass());
			} else {
				throw new Excecoes("A empresa emitente não foi informada!");
			}

			if(JavaUtil.doValida(request.getParameter("sendMail")) && "true".equals(request.getParameter("sendMail"))){
				enviaEmail=true;
			}

			ManifestoED ed = new ManifestoED();
			if(JavaUtil.doValida(request.getParameter("oid_Manifesto"))){
				ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
				//primeiro tem que numerar o bicho!!!
//				new CTeRN(empresa).numeraCTe(ed);
				//busca dados envio
//				mdfe = new MDFeRN(empresa).getDados(ed, true);
System.out.println("WS MDFe");
//				mdfe = new MDFeTeste().getMdfe(1);

				NfeLote retorno = servico.enviaNfe(notafiscal);

				if (retorno != null) {
					System.out.println("Retorno 1.00 ok!!!\n"
		                    + " CHAVE: " + retorno.getNfeNotaFiscal().getChaveAcesso() + "\n"
		                    + " Recibo: " + retorno.getNRec() + "\n"
		                    + " Data: " + retorno.getData() + "\n"
		                    + " cstat: " + retorno.getCStat() + " \n"
		                    + " Motivo: " + retorno.getXMotivo());

		            if (retorno.getNRec() != null) {
		            	System.out.println("aguardando 5s para consultar recibo!!!");
			            Thread.sleep(5000);

		            	new MDFeRN(empresa).alteraEnvio(ed, retorno);

		            	NfeRetornoEnvioLote ret = servico.retornoEnvioNfe(retorno.getNRec(), retorno.getNfeNotaFiscal());

		            	if (ret != null) {
		                	System.out.println("Retorno 1.00 ok!!!\n"
		                            + " Protocolo: " + ret.getnProt() + "\n"
		                            + " Data: " + ret.getDhRecbto() + "\n"
		                            + " cstat: " + ret.getcStat() + "\n"
		                            + " Motivo: " + ret.getxMotivo());

		                    new MDFeRN(empresa).alteraRetorno(ed, ret);

//		                    if(enviaEmail){
//		                    	if ("100".equals(retorno2.getCStat())) {
//		                    		System.out.println("Vai enviar e-mail...");
//		                    		enviaEmail(ed);
//		                    		System.out.println("e-mail ENVIADO...");
//
////		                    		aqui gera a copia do xml no diretorio de averbacao...
//		                    		try{
//		                    			String caminho = "/data/cte/cte/" + ManipulaString.limpaCampo(empresa.getCnpj()) + "/";
//			                    		String arq = cte.getChaveAcesso()+"-procCte.xml";
//			                    		FileUtil.copyFile(caminho+arq, "/data/backup/Averbar/"+arq);
//		                    		} catch(Exception e){
//		                    			System.out.println("problemas na copia do xml...");
//		                    			e.printStackTrace();
//		                    		}
//
//		                    	}
//		                    }

		                } else {
		                    throw new Excecoes("Retorno de lote é NULO...\n\r"+JavaUtil.getErrors(servico.getErros()).toUpperCase());
		                }
		            } else {
		            	throw new Excecoes("Envio do lote não gerou RECIBO para o MDFe...\n\r");
		            }
		        } else {
		            throw new Excecoes(""+JavaUtil.getErrors(servico.getErros()).toUpperCase());
		        }

			}

		} catch(Excecoes e){
			System.out.println("PROBLEMA!!!");
			e.printStackTrace();
			RetornaMensagem.montaTelaErro(request, response, e);
		} catch (Exception e){
			//nada
//			JavaUtil.getErrors(servico.getErros()).toUpperCase();
			System.out.println("PROBLEMA erro!!!");
			e.printStackTrace();
			RetornaMensagem.montaTelaErro(request, response, new Excecoes(e.getMessage()+" | "+JavaUtil.getErrors(servico.getErros()).toUpperCase()));
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