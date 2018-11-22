package com.master.nfe;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.root.UnidadeBean;
import com.master.util.CertUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.cte.base.EmpresaDb;
import br.cte.model.Empresa;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;

public class DownloadNFe extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Empresa empresa = new Empresa();

	public DownloadNFe() {
		super();
	}

	private String downloadNfe(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String arquivo = null;
		try {
			if (JavaUtil.doValida(request.getParameter("emissor"))) {
				empresa = new EmpresaDb().getEmpresa(request.getParameter("emissor"));
				if (!JavaUtil.doValida(empresa.getRazaosocial())) {
					throw new Excecoes("A empresa emitente nao foi encontrada no sistema!");
				}
			} else {
				throw new Excecoes("A empresa emitente nao foi informada!");
			}

			if (JavaUtil.doValida(request.getParameter("oid_Nota_Fiscal"))) {

				arquivo = getDocumentNFe(request);
			} else {
				System.out.println("NADA LOCALIZADO...");
			}

		} catch (Exception e) {
			// nada
			// JavaUtil.getErrors(servico.getErros()).toUpperCase();
			System.out.println("deu erro!!!");
			e.printStackTrace();
			throw new ServletException(e);
		}

		return arquivo;
	}

	private String getDocumentNFe(HttpServletRequest request) throws Exception, IOException {
		String arquivo = null;
		
		Estados ehUF = Estados.RS;
		for (Estados euf : Estados.values()) {
			if (euf.getCodigoIbge().equals(String.valueOf(empresa.getcUf())))
				ehUF = euf;
		}
		System.out.println("Estado de config:" + ehUF + "|" + String.valueOf(empresa.getcUf()));

		String certPath = "/data/nfe4/certificados/" + empresa.getCertificado();
		String certPass = CertUtil.getSenhaPlain(empresa);
		try {
			Certificado certificado = CertificadoService.certificadoPfx(certPath, certPass);
			// Esse Objeto Voce pode guardar em uma Session.
			ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(ehUF, empresa.getAmbiente(),
					certificado, "/data/nfe4/schemas", true);

			Nota_Fiscal_EletronicaED ed = this.getByRecord(request.getParameter("oid_Nota_Fiscal"));
			int oid_uni = new Integer (String.valueOf(ed.getOID_Unidade_Fiscal())).intValue();

		    UnidadeBean unidade_Remetente = UnidadeBean.getByOID_Unidade(empresa, oid_uni);

			System.out.println("Consulta pela CHAVE nf:" + ed.getNfe_chave_acesso());

			String cnpj = ManipulaString.limpaCampo(unidade_Remetente.getNR_CNPJ_CPF());
			String chave = ed.getNfe_chave_acesso();

			RetDistDFeInt retorno = NfeWeb.distribuicaoDfe(config, ConstantesUtil.TIPOS.CNPJ, cnpj,
					ConstantesUtil.TIPOS.CHAVE, chave);

			System.out.println("Status:" + retorno.getCStat());
			System.out.println("Motivo:" + retorno.getXMotivo());
			System.out.println("NSU:" + retorno.getUltNSU());
			System.out.println("Max NSU:" + retorno.getMaxNSU());

			if (StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO.getCodigo().equals(retorno.getCStat())) {

				List<DocZip> listaDoc = retorno.getLoteDistDFeInt().getDocZip();

				System.out.println("Encontrado " + listaDoc.size() + " Notas.");
				for (DocZip docZip : listaDoc) {
					System.out.println("Schema: " + docZip.getSchema());
					System.out.println("NSU:" + docZip.getNSU());
					System.out.println("XML: " + XmlUtil.gZipToXml(docZip.getValue()));
					
					//gravar arquivo
					arquivo = "/data/doc-e/nfe/" + cnpj +"/" + chave + "-procNFe.xml";
		        	if(!new File(arquivo).exists()) {
		        		FileUtil.saveToFile(arquivo, XmlUtil.gZipToXml(docZip.getValue()));
		        	}
				}
			}

		} catch (Excecoes | CertificadoException | NfeException e) {
			e.printStackTrace();
			throw e;
		}
		return arquivo;
	}

	private Nota_Fiscal_EletronicaED getByRecord(String oid_Nota_Fiscal) throws Excecoes {

		Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
		System.out.println("Cert :" + empresa.getCertificado());
		System.out.println("DB URL: " + empresa.getDbURL());

		ed.setOid_nota_fiscal(oid_Nota_Fiscal);
		System.out.println("consulta nf:" + oid_Nota_Fiscal);
		return new Nota_Fiscal_EletronicaRN(empresa).getByRecord(ed);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String arquivoGerado = downloadNfe(request, response);
		response.getWriter().write(arquivoGerado);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
