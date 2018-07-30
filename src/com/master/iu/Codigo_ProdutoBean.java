package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Codigo_ProdutoED;
import com.master.rn.Codigo_ProdutoRN;
import com.master.util.Excecoes;

/*
 * Created on 24/08/2004
 */

/**
 * @author Andre Valadas
 */
public class Codigo_ProdutoBean implements Serializable {

	public Codigo_ProdutoED inclui(HttpServletRequest request)
			throws Excecoes {

		try {
			
			Codigo_ProdutoED ed = new Codigo_ProdutoED();
			
			String oid_Produto = request.getParameter("oid_Produto");
			String Cd_Barra = request.getParameter("FT_CD_Barra");
			String Dm_Produto = request.getParameter("FT_DM_Produto");
			String Dm_Situacao = request.getParameter("FT_DM_Situacao");
			
			//*** Validações
			if ((oid_Produto != null && !oid_Produto.equals("0") && !oid_Produto.equals("") && !oid_Produto.equals("null")) && 
			    (Cd_Barra != null && !Cd_Barra.equals("0") && !Cd_Barra.equals("") && !Cd_Barra.equals("null")) && 
				(Dm_Produto != null && !Dm_Produto.equals("0")) && !Dm_Produto.equals("") && !Dm_Produto.equals("null") &&
				(Dm_Situacao != null && !Dm_Situacao.equals("0") && !Dm_Situacao.equals("") && !Dm_Situacao.equals("null"))) {
				
    		    ed.setOid_Produto(new Integer(oid_Produto).intValue());
				ed.setCd_Barra(Cd_Barra);
				ed.setDm_Produto(Dm_Produto);
				ed.setDm_Situacao(Dm_Situacao);
				
			} 
			
			return new Codigo_ProdutoRN().inclui(ed);
		}

		catch (Excecoes exc) {
			throw exc;
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		try {
			Codigo_ProdutoRN Codigo_ProdutoRN = new Codigo_ProdutoRN();
			Codigo_ProdutoED ed = new Codigo_ProdutoED();
			
			String oid_Codigo_Produto = request.getParameter("oid_Codigo_Produto");
			String Cd_Barra = request.getParameter("FT_CD_Barra");
			String Dm_Produto = request.getParameter("FT_DM_Produto");
			String Dm_Situacao = request.getParameter("FT_DM_Situacao");
			
			//*** Validações
			if ((oid_Codigo_Produto != null && !oid_Codigo_Produto.equals("0") && !oid_Codigo_Produto.equals("") && !oid_Codigo_Produto.equals("null")) &&
			    (Cd_Barra != null && !Cd_Barra.equals("0") && !Cd_Barra.equals("") && !Cd_Barra.equals("null"))){
			    
			    ed.setOid_Codigo_Produto(new Integer(oid_Codigo_Produto).intValue());
				ed.setCd_Barra(Cd_Barra);			    
			    
			    if (Dm_Produto != null && !Dm_Produto.equals("0") && !Dm_Produto.equals("") && !Dm_Produto.equals("null")){
			        ed.setDm_Produto(Dm_Produto);
			    }
			    if (Dm_Situacao != null && !Dm_Situacao.equals("0") && !Dm_Situacao.equals("") && !Dm_Situacao.equals("null")){
			        ed.setDm_Situacao(Dm_Situacao);
			    }
			    
			}else // System.err.println("ERRO! Parâmetros incorretos!");
			
			Codigo_ProdutoRN.altera(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		try {
			Codigo_ProdutoRN Codigo_ProdutoRN = new Codigo_ProdutoRN();
			Codigo_ProdutoED ed = new Codigo_ProdutoED();

			String oid_Codigo_Produto = request.getParameter("oid_Codigo_Produto");
			
			//*** Validações
			if (oid_Codigo_Produto != null && !oid_Codigo_Produto.equals("0") && 
			   !oid_Codigo_Produto.equals("") && !oid_Codigo_Produto.equals("null")){
				
				ed.setOid_Codigo_Produto(new Integer(oid_Codigo_Produto).intValue());
		
				Codigo_ProdutoRN.deleta(ed);
				
			}				

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public ArrayList Codigo_Produto_Lista(HttpServletRequest request) throws Excecoes {

		Codigo_ProdutoED ed = new Codigo_ProdutoED();
		Codigo_ProdutoRN Codigo_ProdutoRN = new Codigo_ProdutoRN();

		String oid_Produto = request.getParameter("oid_Produto");

		//*** Validações
		if (oid_Produto != null && !oid_Produto.equals("0") && !oid_Produto.equals("") && !oid_Produto.equals("null"))
			ed.setOid_Produto(new Integer(oid_Produto).intValue());

		return Codigo_ProdutoRN.lista(ed);

	}

	public Codigo_ProdutoED getByRecord(HttpServletRequest request)
			throws Excecoes {

		Codigo_ProdutoED ed = new Codigo_ProdutoED();

		String oid_Codigo_Produto = request.getParameter("oid_Codigo_Produto");
		
		//*** Validações
		if (oid_Codigo_Produto != null && !oid_Codigo_Produto.equals("0") && 
		   !oid_Codigo_Produto.equals("") && !oid_Codigo_Produto.equals("null")){
			
			ed.setOid_Codigo_Produto(new Integer(oid_Codigo_Produto).intValue());
		}

		return new Codigo_ProdutoRN().getByRecord(ed);

	}

}