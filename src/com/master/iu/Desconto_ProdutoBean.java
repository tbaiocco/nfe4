package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Desconto_ProdutoED;
import com.master.rn.Desconto_ProdutoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Valores;

/**
 * @author André Valadas
 * @serial Max. Descontos dos Produtos Referente a Tabelas de Preços
 * @serialData 28/02/2006
 */
public class Desconto_ProdutoBean extends BancoUtil implements Serializable {

	public Desconto_ProdutoED inclui(HttpServletRequest request) throws Excecoes {

		String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
		String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String PE_Desconto = request.getParameter("FT_PE_Desconto");
        
		//*** Validações
		if (!doValida(oid_Tabela_Venda))
		    throw new Mensagens("Tabela de Venda não informada!");
		if (!doValida(oid_Produto_Cliente))
		    throw new Mensagens("Produto não informado!");
        if (!doValida(PE_Desconto))
            throw new Mensagens("Desconto não informado!");

		Desconto_ProdutoED ed = new Desconto_ProdutoED();
        ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
		ed.setOid_Produto_Cliente(oid_Produto_Cliente);
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Valores.converteStringToDouble(NR_Quantidade));
        ed.setPE_Desconto(Valores.converteStringToDouble(PE_Desconto));
        return new Desconto_ProdutoRN().inclui(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Desconto_Produto = request.getParameter("oid_Desconto_Produto");
		if (!doValida(oid_Desconto_Produto))
		    throw new Mensagens("ID Desconto Produto não informado!");
		
		new Desconto_ProdutoRN().deleta(new Desconto_ProdutoED(Integer.parseInt(oid_Desconto_Produto)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Desconto_Produto = request.getParameter("oid_Desconto_Produto");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");

		Desconto_ProdutoED ed = new Desconto_ProdutoED();
		if (doValida(oid_Desconto_Produto))
		    ed.setOid_Desconto_Produto(Integer.parseInt(oid_Desconto_Produto));
		if (doValida(oid_Tabela_Venda))
		    ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
        if (doValida(oid_Produto_Cliente))
            ed.setOid_Produto_Cliente(oid_Produto_Cliente);
		
		return new Desconto_ProdutoRN().lista(ed);
	}
}