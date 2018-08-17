package com.master.nfe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.Chave;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.cte.base.EmpresaDb;
import br.cte.model.Empresa;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;

/**
 * Servlet implementation class EnviaNFeAsinc
 */
public class EnviaNFeAsinc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnviaNFeAsinc() {
        super();
        // TODO Auto-generated constructor stub
    }

    Empresa empresa = new Empresa();

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
				//busca dados envio
	             TNFe nfe = new Nota_Fiscal_EletronicaRN(empresa).geraNFe(request.getParameter("oid_Nota_Fiscal"), Data.getDataDMY(), Data.getHoraHM());
	             
	             nfe.getInfNFe().getIde().setTpAmb(config.getAmbiente());
	             
System.out.println("WS NFe *** ASSINCRONA ***");
//				mdfe = new MDFeTeste().getMdfe(1);

				String aamm = nfe.getInfNFe().getIde().getDhEmi().substring(0, 7);
				aamm = aamm.replace("-", "");
						
//						new SimpleDateFormat("yyMM").format(Data.strToDate(nfe.getInfNFe().getIde().getDhEmi()));

				//chave
				Chave chNFe = new Chave(nfe.getInfNFe().getIde().getCUF(),
						nfe.getInfNFe().getEmit().getCNPJ(),
						nfe.getInfNFe().getIde().getMod(),
						nfe.getInfNFe().getIde().getSerie(),
						nfe.getInfNFe().getIde().getNNF(),
						nfe.getInfNFe().getIde().getTpEmis(),
						nfe.getInfNFe().getIde().getCNF());
				
				chNFe.setAno(aamm.substring(2,4));
				chNFe.setMes(aamm.substring(4));
				
				Nota_Fiscal_EletronicaED ed = this.getByRecord(request.getParameter("oid_Nota_Fiscal"));
				
				if(JavaUtil.doValida(ed.getNfe_chave_acesso())) {
					nfe.getInfNFe().setId("NFe"+ed.getNfe_chave_acesso());
					nfe.getInfNFe().getIde().setCDV(ed.getNfe_chave_acesso().substring(ed.getNfe_chave_acesso().length()-1));
				} else {
					nfe.getInfNFe().setId(chNFe.getChNFe());
					nfe.getInfNFe().getIde().setCDV(chNFe.getChNFe().substring(chNFe.getChNFe().length()-1));
					
				}
				
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
	            enviNFe = NfeWeb.montaNfe(config, enviNFe, true);

	            // Envia a Nfe para a Sefaz
	            TRetEnviNFe retornoEnv = NfeWeb.enviarNfe(config, enviNFe, ConstantesUtil.NFE);
	            
	            if (!retornoEnv.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo())) {
	                throw new NfeException("Status:" + retornoEnv.getCStat() + " - Motivo:" + retornoEnv.getXMotivo());
	            }
	            
	            String OK = new Nota_Fiscal_EletronicaRN(empresa).updateRetornoLote(ed, retornoEnv, enviNFe);
        		System.out.println("Retorno upd Recibo:"+OK);
        		System.out.println();
        		System.out.println();
	            
	            String recibo = retornoEnv.getInfRec().getNRec();

	            TRetConsReciNFe retorno;
	            while (true) {
	                retorno = NfeWeb.consultaRecibo(config, recibo, ConstantesUtil.NFE);
	                if (retorno.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())) {
	                    System.out.println("Lote Em Processamento, vai tentar novamente apos 3 Segundos.");
	                    Thread.sleep(3000);
	                    continue;
	                } else {
	                    break;
	                }
	            }

	            if (!retorno.getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
	                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
	            }

				if (retorno != null) {
					
					System.out.println("   Status:" + retorno.getProtNFe().get(0).getInfProt().getCStat());
		            System.out.println("   Motivo:" + retorno.getProtNFe().get(0).getInfProt().getXMotivo());
		            System.out.println("     Data:" + retorno.getProtNFe().get(0).getInfProt().getDhRecbto());
		            System.out.println("Protocolo:" + retorno.getProtNFe().get(0).getInfProt().getNProt());
		            System.out.println("       CH:" + retorno.getProtNFe().get(0).getInfProt().getChNFe());

//		            System.out.println("Xml Final :" + XmlUtil.criaNfeProc(enviNFe, retorno.getProtNFe()));

		            OK = new Nota_Fiscal_EletronicaRN(empresa).updateRetornoNFE(ed, retorno, enviNFe);
		            if(OK.startsWith("ERRO")) {
		            	throw new Exception(OK);
		            }
		            
		        } else {
//		            throw new Excecoes(""+JavaUtil.getErrors(servico.getErros()).toUpperCase());
		        }

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
		String retorno = request.getParameter("urlReturn");
		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");

		enviaRecebeNfe(request,response);
//System.out.println("VOLTAR para:"+retorno+"?oid_Nota_Fiscal="+oid_Nota_Fiscal+"&eRequest=true&acao=S&Busca_Campo=Nota_Fiscal");
		if(JavaUtil.doValida(retorno) && JavaUtil.doValida(oid_Nota_Fiscal)){
			response.sendRedirect(retorno+"?oid_Nota_Fiscal="+oid_Nota_Fiscal+"&eRequest=true&acao=S&Busca_Campo=Nota_Fiscal");
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
