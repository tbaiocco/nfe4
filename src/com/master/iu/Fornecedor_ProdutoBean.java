package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Fornecedor_ProdutoED;
import com.master.rn.Fornecedor_ProdutoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Fornecedores do Produto
 * @serialData 02/09/2005
 */
public class Fornecedor_ProdutoBean extends JavaUtil implements Serializable {

	public Fornecedor_ProdutoED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    String oid_Produto = request.getParameter("oid_Produto");
	    
		//*** Validações 
		if (!doValida(oid_Pessoa))
		    throw new Mensagens("Fornecedor não informado!");
		if (!doValida(oid_Produto))
		    throw new Mensagens("Produto não informado!");
        if (new BancoUtil().doExiste("Fornecedores_Produtos",
                                     "oid_Pessoa = '"+oid_Pessoa+"'" +
                                     " AND oid_Produto = "+oid_Produto))
            throw new Mensagens("Esse Fornecedor ja esta cadastrado para esse Produto!");
        
		return new Fornecedor_ProdutoRN().inclui(new Fornecedor_ProdutoED(oid_Pessoa, Integer.parseInt(oid_Produto)));
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Fornecedor_Produto = request.getParameter("oid_Fornecedor_Produto");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Produto = request.getParameter("oid_Produto");

	    //*** Validações 
	    if (!doValida(oid_Fornecedor_Produto))
		    throw new Excecoes("ID Fornecedor Produto não informado!");
        if (!doValida(oid_Pessoa))
            throw new Excecoes("Fornecedor não informado!");
        if (!doValida(oid_Produto))
            throw new Excecoes("Produto não informado!");
        if (new BancoUtil().doExiste("Fornecedores_Produtos",
                                     "oid_Pessoa = '"+oid_Pessoa+"'" +
                                     " AND oid_Produto = "+oid_Produto+
                                     " AND oid_Fornecedor_Produto <> "+oid_Fornecedor_Produto))
            throw new Mensagens("Esse Fornecedor ja esta cadastrado para esse Produto!");
	    
		Fornecedor_ProdutoED ed = new Fornecedor_ProdutoED(Integer.parseInt(oid_Fornecedor_Produto));
        ed.setOid_Pessoa(oid_Pessoa);
        ed.setOid_Produto(Integer.parseInt(oid_Produto));
		new Fornecedor_ProdutoRN().altera(ed);
	}

    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Fornecedor_Produto = request.getParameter("oid_Fornecedor_Produto");
        if (!doValida(oid_Fornecedor_Produto))
            throw new Mensagens("ID Fornecedor Produto não informado!");
        new Fornecedor_ProdutoRN().deleta(new Fornecedor_ProdutoED(Integer.parseInt(oid_Fornecedor_Produto)));
    }
    
	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    return this.lista(request, true, true);
	}
    
    public ArrayList lista(HttpServletRequest request, boolean cFornecedor, boolean cProduto) throws Excecoes {

        String oid_Fornecedor_Produto = request.getParameter("oid_Fornecedor_Produto");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Produto = request.getParameter("oid_Produto");
        
        Fornecedor_ProdutoED ed = new Fornecedor_ProdutoED(cFornecedor, cProduto);
        //*** Validações
        if (doValida(oid_Fornecedor_Produto))
            ed.setOid_Fornecedor_Produto(Integer.parseInt(oid_Fornecedor_Produto));
        if (doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (doValida(oid_Produto))
            ed.setOid_Produto(Integer.parseInt(oid_Produto));

        return new Fornecedor_ProdutoRN().lista(ed);
    }

	public Fornecedor_ProdutoED getByOid(String oid_Fornecedor_Produto) throws Excecoes {

		return this.getByOid(oid_Fornecedor_Produto, true, true);
	}
    public Fornecedor_ProdutoED getByOid(String oid_Fornecedor_Produto, boolean cFornecedor, boolean cProduto) throws Excecoes {

        if (!doValida(oid_Fornecedor_Produto))
            throw new Mensagens("ID Fornecedor Produto não informado!");
        return new Fornecedor_ProdutoRN().getByRecord(new Fornecedor_ProdutoED(Integer.parseInt(oid_Fornecedor_Produto), cFornecedor, cProduto));
    }
    
    public Fornecedor_ProdutoED getByRecord(String oid_Pessoa, String oid_Produto) throws Excecoes {

        if (!doValida(oid_Pessoa))
            throw new Mensagens("ID Lote Recebimento não informado!");
        if (!doValida(oid_Produto))
            throw new Mensagens("ID Cheque Cliente não informado!");
        return new Fornecedor_ProdutoRN().getByRecord(new Fornecedor_ProdutoED(oid_Pessoa, Integer.parseInt(oid_Produto)));
    }
}