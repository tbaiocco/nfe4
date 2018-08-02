package com.master.nfe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.util.JavaUtil;

import br.nfe.model.Empresa;

public class DownloadNFe extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	Empresa empresa = new Empresa();
	
	public DownloadNFe() {
		super();
	}
	
	private String downloadNfe(HttpServletRequest request, HttpServletResponse response){
		
		return null;
	}
	
	private boolean comunicaCienciaNFe (String chave, String emissor, int ambiente) {
		return false;
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
