package com.master.nfe;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.cte.RetornaMensagem;
import com.master.ed.ManifestoED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.rn.MDFeRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

import br.mdfe.core.base.EmpresaDb;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeEvento;
import br.model.Empresa;
import br.model.NfeCancelamento;
import br.model.NfeNotaFiscal;
import br.servicos.MdfeServicos;
import br.servicos.NfeServicos;

/**
 * Servlet implementation class for Servlet: CancelaNFe
 *
 */
 public class CancelaNFe extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	 Empresa empresa = new Empresa();

	public CancelaNFe() {
		super();
	}

	private void cancelaNfe(HttpServletRequest request, HttpServletResponse response){
		NfeServicos servico = new NfeServicos();
		try{
			if(JavaUtil.doValida(request.getParameter("emissor"))){
				empresa = new br.core.base.EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if(!JavaUtil.doValida(empresa.getRazaosocial())){
					throw new Excecoes("A empresa emitente não foi encontrada no sistema!");
				}
			} else {
				throw new Excecoes("A empresa emitente não foi informada!");
			}
			Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaED();

			if(JavaUtil.doValida(request.getParameter("oid_Nota_Fiscal"))){
//				ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
////
				NfeNotaFiscal nota = new NfeNotaFiscal();

//				Mdfe man = new MDFeRN(empresa).getDados(ed, false);
//		        mdfe.setCOrgao(empresa.getcUf());
//		        mdfe.setCNPJ(man.getEmit().getCNPJ());
//		        mdfe.setTpAmb(man.getTpAmb());
//		        mdfe.setChMDFe(man.getChAcesso());
//		        mdfe.setTpEvento(110111);
//		        mdfe.setNProtAprovacaoMDFe(man.getNProt());
//		        mdfe.setDescEvento("Cancelamento");
//		        mdfe.setXJust("ERRO NA EMISSAO DO MDFE");
//		        mdfe.setNSeqEvento(1);
//		        mdfe.setDhEvento(new Date());

				if (nota.getNProt() != null) {
					NfeCancelamento retorno = servico.cancelaNfe(nota);
		            if (retorno != null) {
		            	System.out.println("Retorno " + " 1.00 ok!!!\n"
		                        + " Protocolo: " + retorno.getnProt() + "\n"
		                        + " Data: " + retorno.getDhRecbto() + "\n"
		                        + " cstat: " + retorno.getcStat() + " \n"
		                        + " Motivo: " + retorno.getxMotivotivo());
		                if (retorno.getnProt() != null) {

			            	new MDFeRN(empresa).alteraCancelamento(ed, retorno);

//			            	MdfeConsulta cons = servico.consultaMdfe(man.getChAcesso(), man.getTpAmb(), man.getEmit().getCNPJ());
//			            	if( cons.getNProt() != null){
//			            		System.out.println("Consulta 1.00 ok!!!\n"
//			                            + " Protocolo: " + cons.getNProt() + "\n"
//			                            + " Data: " + cons.getDhRecbto() + "\n"
//			                            + " cstat: " + cons.getCStat() + "\n"
//			                            + " Motivo: " + cons.getXMotivo());
//			            	}

			            } else {
			            	throw new Mensagens("Retorno do lote não possui MDFe...\n\r");
			            }
		            } else {
		            	throw new Excecoes(""+JavaUtil.getErrors(servico.getErros()).toUpperCase());
		            }
		        } else {
					System.out.println("NADA LOCALIZADO...");
				}
			} else {
				System.out.println("NADA LOCALIZADO...");
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
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String retorno = request.getParameter("urlRetono");
		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");

		cancelaNfe(request,response);

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

		cancelaNfe(request,response);

		if(JavaUtil.doValida(retorno) && JavaUtil.doValida(oid_Nota_Fiscal)){
			response.sendRedirect(retorno+"?oid_Nota_Fiscal="+oid_Nota_Fiscal+"&eRequest=true&acao=S&Busca_Campo=Nota_Fiscal");
		}
	}
}