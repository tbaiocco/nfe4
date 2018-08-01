package com.master.nfe;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.model.Empresa;

/**
 * Servlet implementation class for Servlet: AtualizaNFe
 *
 */
 public class AtualizaNFe extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AtualizaNFe() {
		super();
	}   	
	
	Empresa empresa = new Empresa();
	
	private void consultaNfe(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try{
			if(JavaUtil.doValida(request.getParameter("emissor"))){
				empresa = new br.core.base.EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if(!JavaUtil.doValida(empresa.getRazaosocial())){
					throw new Excecoes("A empresa emitente nao foi encontrada no sistema!");
				}
			} else {
				throw new Excecoes("A empresa emitente nao foi informada!");
			}

			if(JavaUtil.doValida(request.getParameter("oid_Nota_Fiscal"))){

	             try{
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

	 	             Nota_Fiscal_EletronicaED ed = this.getByRecord(request.getParameter("oid_Nota_Fiscal"));
	 	             
System.out.println("CHAVE nf:" + ed.getNfe_chave_acesso());
	 	             
	            	 TRetConsSitNFe retorno = NfeWeb.consultaXml(config, ed.getNfe_chave_acesso(), ConstantesUtil.NFE);
	                 System.out.println("Status:" + retorno.getCStat());
	                 System.out.println("Motivo:" + retorno.getXMotivo());
	                 System.out.println("Data:" + retorno.getProtNFe().getInfProt().getDhRecbto());
	             	
//	             	response.getWriter().append("Served at: ").append(request.getRemoteHost()).println();
//	                response.getWriter().append("      URI: ")
//	                					 .append(request.getRequestURI())
//	                					 .append(" > ")
//	                					 .append(request.getRequestURL())
//	                					 .println();
//	                response.getWriter().append("   Status: ").append(retorno.getCStat()).println();
//	                response.getWriter().append("   Motivo: ").append(retorno.getXMotivo()).println();
//	                response.getWriter().append("     Data: ").append(retorno.getDhRecbto()).println();
	             	
	             } catch (Excecoes | CertificadoException | NfeException e) {
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
System.out.println("consulta nf:" + oid_Nota_Fiscal);
        return new Nota_Fiscal_EletronicaRN().getByRecord(ed);
    }
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		consultaNfe(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}   	  	    
}