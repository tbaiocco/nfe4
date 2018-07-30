package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Setor_ProdutoED;
import com.master.rn.Setor_ProdutoRN;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class Setor_ProdutoBean  {

	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

	public Setor_ProdutoED inclui(HttpServletRequest request) throws Excecoes {

		String CD_Setor_Produto = request.getParameter("FT_CD_Setor_Produto");
		String NM_Setor_Produto = request.getParameter("FT_NM_Setor_Produto");

		//*** Validações
		if (!util.doValida(CD_Setor_Produto))
		    throw new Mensagens("Código Setor não informado!");
		if (!util.doValida(NM_Setor_Produto))
		    throw new Mensagens("Nome Setor não informado!");

		Setor_ProdutoED ed = new Setor_ProdutoED();
		ed.setCD_Setor_Produto(CD_Setor_Produto);
		ed.setNM_Setor_Produto(NM_Setor_Produto);

		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new Setor_ProdutoRN().inclui(ed);
		} else throw new Mensagens("Esse Setor já existe!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {


		String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");
		String CD_Setor_Produto = request.getParameter("FT_CD_Setor_Produto");
		String NM_Setor_Produto = request.getParameter("FT_NM_Setor_Produto");

		//*** Validações
		if (!util.doValida(oid_Setor_Produto))
		    throw new Mensagens("ID Setor não informado!");
		if (!util.doValida(CD_Setor_Produto))
		    throw new Mensagens("Código Setor não informado!");
		if (!util.doValida(NM_Setor_Produto))
		    throw new Mensagens("Nome Setor não informado!");

		Setor_ProdutoED ed = new Setor_ProdutoED();
		ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));
		ed.setCD_Setor_Produto(CD_Setor_Produto);
		ed.setNM_Setor_Produto(NM_Setor_Produto);
		
		//Válida se pode ser alterado
		util.doValidaUpdate(ed.getOid_Setor_Produto(), ed.getCD_Setor_Produto(), "Setores_Produtos", "oid_Setor_Produto", "CD_Setor_Produto");
		new Setor_ProdutoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");
		if (!util.doValida(oid_Setor_Produto))
		    throw new Mensagens("ID Setor não informado!");
		
		new Setor_ProdutoRN().deleta(new Setor_ProdutoED(Integer.parseInt(oid_Setor_Produto)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");
	    String CD_Setor_Produto = request.getParameter("FT_CD_Setor_Produto");
		String NM_Setor_Produto = request.getParameter("FT_NM_Setor_Produto");

		Setor_ProdutoED ed = new Setor_ProdutoED();
		if (util.doValida(oid_Setor_Produto))
		    ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));
		if (util.doValida(CD_Setor_Produto))
		    ed.setCD_Setor_Produto(CD_Setor_Produto);
		if (util.doValida(NM_Setor_Produto))
		    ed.setNM_Setor_Produto(NM_Setor_Produto);
		
		return new Setor_ProdutoRN().lista(ed);
	}
	
	public Setor_ProdutoED getByOid(String oid_Setor_Produto) throws Excecoes {

		if (!util.doValida(oid_Setor_Produto))
		    throw new Mensagens("ID Setor não informado!");

		return new Setor_ProdutoRN().getByRecord(new Setor_ProdutoED(Integer.parseInt(oid_Setor_Produto)));
	}
	
	public Setor_ProdutoED getByCDSetor_Produto(String CD_Setor_Produto) throws Excecoes {
		
		//*** Validações
		if (!util.doValida(CD_Setor_Produto))
		    throw new Mensagens("Código Setor não informado!");
		Setor_ProdutoED ed = new Setor_ProdutoED();
		ed.setCD_Setor_Produto(CD_Setor_Produto);
		
		return new Setor_ProdutoRN().getByRecord(ed);
	}

    //*** Verifica se registro já existe!
    private boolean doExiste(Setor_ProdutoED ed) throws Excecoes {
        
	    return util.doExiste("Setores_Produtos", 
	            			  " CD_Setor_Produto = '" + ed.getCD_Setor_Produto() +"'" +
	            			  " OR NM_Setor_Produto = '"+ed.getNM_Setor_Produto()+"'");
    }
}