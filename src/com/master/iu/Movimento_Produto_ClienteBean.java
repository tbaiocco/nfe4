package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Movimento_Produto_ClienteED;
import com.master.rn.Movimento_Produto_ClienteRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author André Valadas
 * - Movimento dos Produtos Clientes
 */
public class Movimento_Produto_ClienteBean extends JavaUtil implements Serializable {

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Movimento_Produto = request.getParameter("oid_Movimento_Produto");
	    String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
	    String oid_Localizacao = request.getParameter("oid_Localizacao");
	    String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
	    String oid_Tipo_Movimento_Produto = request.getParameter("oid_Tipo_Movimento_Produto");
	    String oid_Operador = request.getParameter("oid_Operador");
	    String oid_Requisicao_Produto = request.getParameter("oid_Requisicao_Produto");
	    String dm_Ordenacao = request.getParameter("dm_Ordenacao");
		
	    String dt_inicial = request.getParameter("dt_inicial");
	    String dt_final = request.getParameter("dt_final");
	    
		Movimento_Produto_ClienteED ed = new Movimento_Produto_ClienteED();
		if (doValida(oid_Movimento_Produto))
		    ed.setOid_Movimento_Produto(Long.parseLong(oid_Movimento_Produto));
		if (doValida(oid_Produto_Cliente))
		    ed.setOid_Produto_Cliente(oid_Produto_Cliente);
        if (doValida(oid_Tipo_Estoque))
            ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
		if (doValida(oid_Localizacao))
		    ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
		if (doValida(oid_Nota_Fiscal))
		    ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
		if (doValida(oid_Tipo_Movimento_Produto))
		    ed.setOid_Tipo_Movimento_Produto(Integer.parseInt(oid_Tipo_Movimento_Produto));
		if (doValida(oid_Operador))
		    ed.setOid_Operador(Integer.parseInt(oid_Operador));
		if (doValida(oid_Requisicao_Produto))
		    ed.setOid_Requisicao_Produto(Integer.parseInt(oid_Requisicao_Produto));
		if (doValida(dm_Ordenacao))
		    ed.setDm_Ordenacao(dm_Ordenacao);

		if (doValida(dt_inicial))
		    ed.setDt_inicial(dt_inicial);
		if (doValida(dt_final))
		    ed.setDt_final(dt_final);

		return new Movimento_Produto_ClienteRN().lista(ed);
	}
}