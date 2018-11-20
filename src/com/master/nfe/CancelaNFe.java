package com.master.nfe;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.util.CertUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.util.Estados;
import br.cte.base.EmpresaDb;
import br.cte.model.Empresa;
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

	private void cancelaNfe(HttpServletRequest request, HttpServletResponse response) throws ServletException {
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
			
			String certPath = "/data/nfe4/certificados/" + empresa.getCertificado();
	    	String certPass = CertUtil.getSenhaPlain(empresa);

			if(JavaUtil.doValida(request.getParameter("oid_Nota_Fiscal"))){

				Estados ehUF = Estados.RS;
				for(Estados euf : Estados.values()) {
					if(euf.getCodigoIbge().equals(String.valueOf(empresa.getcUf())))
						ehUF = euf;
				}
System.out.println("Estado de config:"+ehUF+"|"+String.valueOf(empresa.getcUf()));
				Certificado certificado = CertificadoService.certificadoPfx(
	             		certPath, 
	             		certPass);
	             //Esse Objeto Voce pode guardar em uma Session.
				ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(ehUF,
	                     empresa.getAmbiente(),
	                     certificado,
	                     "/data/nfe4/schemas",
//	                     MethodHandles.lookup().lookupClass().
//	                     	getResource("/schemas").getPath(), //PEGAR SCHEMAS EM AMBIENTE WEB ESTA PASTA ESTA DENTRO DE RESOURCES
	                     true);
	             
	             Nota_Fiscal_EletronicaED ed = this.getByRecord(request.getParameter("oid_Nota_Fiscal"));
	             try{
	             	new Nota_Fiscal_EletronicaRN(empresa).enviaNFE_cancelada(empresa, ed, config);
	             } catch (Excecoes e) {
	                 e.printStackTrace();
	                 throw e;
	             } catch(Exception e){
	             	e.printStackTrace();
	             	throw new Excecoes();
	             }
			} else {
				System.out.println("NADA LOCALIZADO...");
			}

		} catch (Exception e){
			//nada
//			JavaUtil.getErrors(servico.getErros()).toUpperCase();
			System.out.println("deu erro!!!");
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	private Nota_Fiscal_EletronicaED getByRecord(String oid_Nota_Fiscal) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

        ed.setOid_nota_fiscal(oid_Nota_Fiscal);

        return new Nota_Fiscal_EletronicaRN(empresa).getByRecord(ed);
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
		doGet(request, response);
	}
}