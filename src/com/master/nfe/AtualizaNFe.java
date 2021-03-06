package com.master.nfe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.util.CertUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.cte.base.EmpresaDb;
import br.cte.model.Empresa;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;

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
			
//			empresa.setDbDriver("org.postgresql.Driver");
//			   empresa.setDbURL("jdbc:postgresql://127.0.0.1:5432/miro");
//			   empresa.setDbUser("postgres");
//			   empresa.setDbPass("");
			
			if(JavaUtil.doValida(request.getParameter("emissor"))){
				empresa = new EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if(!JavaUtil.doValida(empresa.getRazaosocial())){
					throw new Excecoes("A empresa emitente nao foi encontrada no sistema!");
				}
			} else {
				throw new Excecoes("A empresa emitente nao foi informada!");
			}

			if(JavaUtil.doValida(request.getParameter("oid_Nota_Fiscal"))){
				
				Estados ehUF = Estados.RS;
				for(Estados euf : Estados.values()) {
					if(euf.getCodigoIbge().equals(String.valueOf(empresa.getcUf())))
						ehUF = euf;
				}
System.out.println("Estado de config:"+ehUF+"|"+String.valueOf(empresa.getcUf()));

//				String certPath = "/data/nfe4/certificados/miro_cac.pfx";
//		    	String certPass = "1444";
				String certPath = "/data/nfe4/certificados/" + empresa.getCertificado();
		    	String certPass = CertUtil.getSenhaPlain(empresa);
	             try{
	            	 Certificado certificado = CertificadoService.certificadoPfx(
	 	             		certPath, 
	 	             		certPass);
	 	             //Esse Objeto Voce pode guardar em uma Session.
	 	             ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(ehUF,
//	 	            		 ConstantesUtil.AMBIENTE.HOMOLOGACAO,
	 	            		 empresa.getAmbiente(),
	 	                     certificado,
	 	                    "/data/nfe4/schemas",
//	 	                     MethodHandles.lookup().lookupClass().
//	 	                     getResource("/schemas").getPath(), //PEGAR SCHEMAS EM AMBIENTE WEB ESTA PASTA ESTA DENTRO DE RESOURCES
	 	                     true);

	 	             Nota_Fiscal_EletronicaED ed = this.getByRecord(request.getParameter("oid_Nota_Fiscal"));
	 	             
System.out.println("CHAVE nf:" + ed.getNfe_chave_acesso());
	 	             
	            	 TRetConsSitNFe retorno = NfeWeb.consultaXml(config, ed.getNfe_chave_acesso(), ConstantesUtil.NFE);
	                 System.out.println("Status:" + retorno.getCStat());
	                 System.out.println("Motivo:" + retorno.getXMotivo());
	                 if (retorno.getProtNFe() != null && retorno.getProtNFe().getInfProt() != null) {
	                	 System.out.println("Data:" + retorno.getProtNFe().getInfProt().getDhRecbto());
	                	 response.getWriter().append("Served at: ").append(request.getRemoteHost()).println();
	 	                response.getWriter().append("      URI: ")
	 	                					 .append(request.getRequestURI())
	 	                					 .append(" > ")
	 	                					 .append(request.getRequestURL())
	 	                					 .println();
	 	                response.getWriter().append("   Status: ").append(retorno.getCStat()).println();
	 	                response.getWriter().append("   Motivo: ").append(retorno.getXMotivo()).println();
	 	                
	 	                response.getWriter().append("   Prot: ").append(retorno.getProtNFe().getInfProt().getNProt()).println();
	 	                response.getWriter().append("  ChNFE: ").append(retorno.getProtNFe().getInfProt().getChNFe()).println();
	 	                
	 	             //busca dados envio
			             TNFe nfe = new Nota_Fiscal_EletronicaRN(empresa).geraNFe(request.getParameter("oid_Nota_Fiscal"), Data.getDataDMY(), Data.getHoraHM());
			             
						nfe.getInfNFe().setId("NFe"+ed.getNfe_chave_acesso());
						nfe.getInfNFe().getIde().setCDV(ed.getNfe_chave_acesso().substring(ed.getNfe_chave_acesso().length()-1));
						
						nfe.getInfNFe().getIde().setTpAmb(config.getAmbiente());
						
						if(nfe.getInfNFe().getIde().getTpAmb() == ConstantesUtil.AMBIENTE.HOMOLOGACAO) {
							nfe.getInfNFe().getEmit().setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
							nfe.getInfNFe().getEmit().setXFant("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
							
							nfe.getInfNFe().getDest().setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
						}

			            TEnviNFe enviNFe = new TEnviNFe();
			            enviNFe.setVersao("4.00");
			            enviNFe.setIdLote("1");
			            enviNFe.setIndSinc("1");
			            enviNFe.getNFe().add(nfe);
			            
			            // Monta e Assina o XML
			            enviNFe = NfeWeb.montaNfe(config, enviNFe, false);

			            response.getWriter().append("  XML  : ").append(XmlUtil.criaNfeProc(enviNFe, retorno.getProtNFe()));
			 	               
			            String OK = new Nota_Fiscal_EletronicaRN(empresa).updateRetornoNFE(ed, retorno, enviNFe);
	 	               
	                 }
//	             	
//	                response.getWriter().append("     Data: ").append(retorno.getDhRecbto()).println();
	             	
	             } catch (/*Excecoes |*/ CertificadoException | NfeException e) {
	                 e.printStackTrace();
	                 throw e;
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
        System.out.println("Cert :"+empresa.getCertificado());
        System.out.println("DB URL: "+empresa.getDbURL());

        ed.setOid_nota_fiscal(oid_Nota_Fiscal);
System.out.println("consulta nf:" + oid_Nota_Fiscal);
        return new Nota_Fiscal_EletronicaRN(empresa).getByRecord(ed);
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