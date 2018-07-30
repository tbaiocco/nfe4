package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Ocorrencia_ProdutoED;
import com.master.rn.Ocorrencia_ProdutoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 10/09/2004
 */

/**
 * @author Andre Valadas
 */

public class Ocorrencia_ProdutoBean extends JavaUtil implements Serializable {

	public Ocorrencia_ProdutoED inclui(HttpServletRequest request) throws Excecoes {

		try {

			Ocorrencia_ProdutoED ed = new Ocorrencia_ProdutoED();
			
			String oid_Produto = request.getParameter("oid_Produto");
			String oid_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
			String DT_Ocorrencia_Produto = request.getParameter("FT_DT_Ocorrencia_Produto");
			String HR_Ocorrencia_Produto = request.getParameter("FT_HR_Ocorrencia_Produto");
			String TX_Descricao = request.getParameter("FT_TX_Descricao");
			
			//*** Validações
			if ((oid_Produto == null || oid_Produto.equals("0") || oid_Produto.equals("") || oid_Produto.equals("null")) || 
			    (oid_Tipo_Ocorrencia == null || oid_Tipo_Ocorrencia.equals("0") || oid_Tipo_Ocorrencia.equals("") || oid_Tipo_Ocorrencia.equals("null")) ||    
			    (DT_Ocorrencia_Produto == null) || DT_Ocorrencia_Produto.equals("") || DT_Ocorrencia_Produto.equals("null") ||
			    (HR_Ocorrencia_Produto == null) || HR_Ocorrencia_Produto.equals("") || HR_Ocorrencia_Produto.equals("null")) {
			    
			    // Lança exceção!
			    Excecoes ex = new Excecoes();
			    ex.setMensagem("ERRO! Parâmetros incorretos!");
			    throw ex;			    
			    
			} else // System.err.println("Validou Incluir!");
			
		    ed.setOid_Produto(Integer.parseInt(oid_Produto));
		    ed.setOid_Tipo_Ocorrencia(Integer.parseInt(oid_Tipo_Ocorrencia));
		    ed.setDT_Ocorrencia_Produto(DT_Ocorrencia_Produto);
			ed.setHR_Ocorrencia_Produto(HR_Ocorrencia_Produto);
			ed.setTX_Descricao(TX_Descricao);

			return new Ocorrencia_ProdutoRN().inclui(ed);
		}

		catch (Excecoes exc) {
			throw exc;
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		try {

			Ocorrencia_ProdutoED ed = new Ocorrencia_ProdutoED();

			String oid_Ocorrencia_Produto = request.getParameter("oid_Ocorrencia_Produto");
			String oid_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
			String TX_Descricao = request.getParameter("FT_TX_Descricao");

			//*** Validações
			if ((oid_Tipo_Ocorrencia == null || oid_Tipo_Ocorrencia.equals("0") || oid_Tipo_Ocorrencia.equals("") || oid_Tipo_Ocorrencia.equals("null")) ||
		        (oid_Ocorrencia_Produto == null || oid_Ocorrencia_Produto.equals("0") || oid_Ocorrencia_Produto.equals("") || oid_Ocorrencia_Produto.equals("null"))) {
			    
			    // Lança exceção!
			    Excecoes ex = new Excecoes();
			    ex.setMensagem("ERRO! Parâmetros incorretos!");
			    throw ex;			    
			    
			} else // System.err.println("Validou Alterar!");
			
			ed.setOid_Ocorrencia_Produto(Integer.parseInt(oid_Ocorrencia_Produto));
		    ed.setOid_Tipo_Ocorrencia(Integer.parseInt(oid_Tipo_Ocorrencia));
			if ((TX_Descricao != null) && !TX_Descricao.equals("") && !TX_Descricao.equals("null"))
				ed.setTX_Descricao(TX_Descricao);

			new Ocorrencia_ProdutoRN().altera(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		try {

			Ocorrencia_ProdutoED ed = new Ocorrencia_ProdutoED();

			String oid_Ocorrencia_Produto = request.getParameter("oid_Ocorrencia_Produto");

			//*** Validações
			if (oid_Ocorrencia_Produto == null || oid_Ocorrencia_Produto.equals("0") || 
			    oid_Ocorrencia_Produto.equals("") || oid_Ocorrencia_Produto.equals("null")) {

			    //Lança exceção!
			    Excecoes ex = new Excecoes();
			    ex.setMensagem("ERRO! Parâmetros incorretos!");
			    throw ex;	

			} else // System.err.println("Validou Delete!");
			
			ed.setOid_Ocorrencia_Produto(Integer.parseInt(oid_Ocorrencia_Produto));
			new Ocorrencia_ProdutoRN().deleta(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public ArrayList Lista_Ocorrencia_Produto(HttpServletRequest request) throws Excecoes {

		Ocorrencia_ProdutoED ed = new Ocorrencia_ProdutoED();
		
		String oid_Ocorrencia_Produto = request.getParameter("oid_Ocorrencia_Produto");
		String oid_Produto = request.getParameter("oid_Produto");
		
		if (Integer.parseInt(oid_Produto) > 0)
		    ed.setOid_Produto(Integer.parseInt(oid_Produto));
		else if (Integer.parseInt(oid_Ocorrencia_Produto) > 0)
		    ed.setOid_Ocorrencia_Produto(Integer.parseInt(oid_Ocorrencia_Produto));		

		return new Ocorrencia_ProdutoRN().lista(ed);
	}

	public Ocorrencia_ProdutoED getByRecord(int oid_Ocorrencia_Produto) throws Excecoes {

		Ocorrencia_ProdutoED ed = new Ocorrencia_ProdutoED();

		//*** Validações
		if (oid_Ocorrencia_Produto <= 0) {

		    //Lança exceção!
		    Excecoes ex = new Excecoes();
		    ex.setMensagem("ERRO! Parâmetros incorretos!");
		    throw ex;	

		} else // System.err.println("Validou getByRecord!");
		
		ed.setOid_Ocorrencia_Produto(oid_Ocorrencia_Produto);
		
		return new Ocorrencia_ProdutoRN().getByRecord(ed);
	}

	
    // Verifica se registro já existe!
    public boolean doExiste(HttpServletRequest request) throws Excecoes {
        
        String oid_Ocorrencia_Produto = request.getParameter("oid_Ocorrencia_Produto");
        
        String strFrom  = "ocorrencias_produtos";
        String strWhere = "oid_Ocorrencia_Produto = "+Integer.parseInt(oid_Ocorrencia_Produto);
        
        return new BancoUtil().doExiste(strFrom, strWhere);
    }

}