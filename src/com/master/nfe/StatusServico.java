package com.master.nfe;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.util.CertUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;

import br.cte.base.EmpresaDb;
import br.cte.model.Empresa;

/**
 * Servlet implementation class StatusServico
 */
public class StatusServico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Empresa empresa = new Empresa();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServico() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void doStatusCheck(HttpServletRequest request, HttpServletResponse response) {
    	String certPath = "/data/nfe4/certificados/tonelli.pfx";
    	String certPass = "1444";
    	try {
    		
    		if(JavaUtil.doValida(request.getParameter("emissor"))){
				empresa = new EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if(!JavaUtil.doValida(empresa.getRazaosocial())){
					throw new Excecoes("A empresa emitente nao foi encontrada no sistema!");
				}
			} else {
				throw new Excecoes("A empresa emitente nao foi informada!");
			}
			
			certPath = "/data/nfe4/certificados/" + empresa.getCertificado();
	    	certPass = CertUtil.getSenhaPlain(empresa);
    		 
             // Inicia As Certificado
             Certificado certificado = CertificadoService.certificadoPfx(
             		certPath, 
             		certPass);
             //Esse Objeto Voce pode guardar em uma Session.
             ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(Estados.RS,
                     ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                     certificado,
                     MethodHandles.lookup().lookupClass().
                     getResource("/schemas").getPath(), //PEGAR SCHEMAS EM AMBIENTE WEB ESTA PASTA ESTA DENTRO DE RESOURCES
                     false);


             TRetConsStatServ retorno = NfeWeb.statusServico(config, ConstantesUtil.NFCE);
             System.out.println("Status:" + retorno.getCStat());
             System.out.println("Motivo:" + retorno.getXMotivo());
             System.out.println("Data:" + retorno.getDhRecbto());
             
             response.getWriter().append("Served at: ").append(request.getRemoteHost()).println();
             response.getWriter().append("      URI: ")
             					 .append(request.getRequestURI())
             					 .append(" > ")
             					 .append(request.getRequestURL())
             					 .println();
             response.getWriter().append("   Status: ").append(retorno.getCStat()).println();
             response.getWriter().append("   Motivo: ").append(retorno.getXMotivo()).println();
             response.getWriter().append("     Data: ").append(retorno.getDhRecbto()).println();

         } catch (Exception e) {
             System.err.println(e.getMessage());
             e.printStackTrace();
//             response.getWriter().append(">> ERROR: ").append(e.getMessage());
         }
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doStatusCheck(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
