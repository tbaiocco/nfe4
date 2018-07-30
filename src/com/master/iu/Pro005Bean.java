package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Promocao_ProdutoED;
import com.master.rn.Promocao_ProdutoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Pro005Bean extends JavaUtil{

    public Promocao_ProdutoED inclui(HttpServletRequest request)throws Excecoes{

        Promocao_ProdutoED ed = new Promocao_ProdutoED();

        String OID_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String OID_Pessoa = request.getParameter("oid_Pessoa");
        
        if (doValida(OID_Produto_Cliente))
            ed.setOID_Produto_Cliente(OID_Produto_Cliente);
        if (doValida(OID_Pessoa))
            ed.setOid_Pessoa(OID_Pessoa);
        
        ed.setDT_Vigencia(request.getParameter("FT_DT_Vigencia"));
        ed.setDT_Validade(request.getParameter("FT_DT_Validade"));
        ed.setPE_Desconto(Double.parseDouble(request.getParameter("FT_PE_Desconto")));
        ed.setNR_QT_Minima(Double.parseDouble(request.getParameter("FT_NR_QT_Minima")));
        ed.setNR_QT_Maxima(Double.parseDouble(request.getParameter("FT_NR_QT_Maxima")));

        return new Promocao_ProdutoRN().inclui(ed);
	}

	public void altera(HttpServletRequest request)throws Excecoes {

	    Promocao_ProdutoED ed = new Promocao_ProdutoED();

        String oid_Promocao_Produto = request.getParameter("oid_Promocao_Produto");

        if (doValida(oid_Promocao_Produto))
            ed.setOID_Promocao_Produto(Long.parseLong(oid_Promocao_Produto));
        else throw new Excecoes("Parâmetros incorretos!");
        
        ed.setDT_Vigencia(request.getParameter("FT_DT_Vigencia"));
        ed.setDT_Validade(request.getParameter("FT_DT_Validade"));
        ed.setPE_Desconto(Double.parseDouble(request.getParameter("FT_PE_Desconto")));
        ed.setNR_QT_Minima(Double.parseDouble(request.getParameter("FT_NR_QT_Minima")));
        ed.setNR_QT_Maxima(Double.parseDouble(request.getParameter("FT_NR_QT_Maxima")));

        new Promocao_ProdutoRN().altera(ed);
    }

	public void deleta(HttpServletRequest request)throws Excecoes{

	    Promocao_ProdutoED ed = new Promocao_ProdutoED();

        ed.setOID_Promocao_Produto(new Long(request.getParameter("oid_Promocao_Produto")).longValue());

        new Promocao_ProdutoRN().deleta(ed);
    }

	public Promocao_ProdutoED getByRecord(HttpServletRequest request)throws Excecoes{

	    Promocao_ProdutoED ed = new Promocao_ProdutoED();

	    String oid_Promocao_Produto = request.getParameter("oid_Promocao_Produto");
	    String OID_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String OID_Pessoa = request.getParameter("oid_Pessoa");
        
	    if (doValida(oid_Promocao_Produto)){
	        ed.setOID_Promocao_Produto(Long.parseLong(oid_Promocao_Produto));
	    } else {
	        if (doValida(OID_Produto_Cliente))
	            ed.setOID_Produto_Cliente(OID_Produto_Cliente);
	        if (doValida(OID_Pessoa))
	            ed.setOid_Pessoa(OID_Pessoa);
	    }

        return new Promocao_ProdutoRN().getByRecord(ed);
	}
	
	public Promocao_ProdutoED getByOid(String oid_Promocao_Produto)throws Excecoes{

	    Promocao_ProdutoED ed = new Promocao_ProdutoED();

	    if (!doValida(oid_Promocao_Produto))
	        throw new Excecoes("Parâmetros incorretos!");
	        
	    ed.setOID_Promocao_Produto(Long.parseLong(oid_Promocao_Produto));
	    
        return new Promocao_ProdutoRN().getByRecord(ed);
	}
	
	public Promocao_ProdutoED getByUltDataVigencia(String oid_Produto_Cliente, String oid_Pessoa_Distribuidor, String DT_Referencia)throws Excecoes{

	    Promocao_ProdutoED ed = new Promocao_ProdutoED();

	    if (!doValida(oid_Produto_Cliente) || !doValida(oid_Pessoa_Distribuidor) || !doValida(DT_Referencia))
	        throw new Excecoes("Parâmetros incorretos!");
	        
	    ed.setOID_Produto_Cliente(oid_Produto_Cliente);
	    ed.setOid_Pessoa(oid_Pessoa_Distribuidor);
	    ed.setDT_Referencia(DT_Referencia);

        return new Promocao_ProdutoRN().getByUltDataVigencia(ed);
	}

	public ArrayList Produto_Lista(HttpServletRequest request)throws Excecoes{

	    Promocao_ProdutoED ed = new Promocao_ProdutoED();

        String OID_Promocao_Produto = request.getParameter("oid_Promocao_Produto");
        String OID_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String OID_Pessoa = request.getParameter("oid_Pessoa");
        
        if (doValida(OID_Promocao_Produto))
            ed.setOID_Promocao_Produto(new Long(request.getParameter("oid_Promocao_Produto")).longValue());
        if (doValida(OID_Produto_Cliente))
            ed.setOID_Produto_Cliente(OID_Produto_Cliente);
        if (doValida(OID_Pessoa))
            ed.setOid_Pessoa(OID_Pessoa);

        ed.setNM_Tabela(request.getParameter("FT_NM_Tabela"));
        ed.setDT_Vigencia(request.getParameter("FT_DT_Vigencia"));
        ed.setDT_Validade(request.getParameter("FT_DT_Validade"));

        return new Promocao_ProdutoRN().lista(ed);
	}
	
	//*** Verifica se existe algum item do Pedido de Venda relacionado a essa promoção
	//    Se existe não deixa alterar nem Excluir Promoção
	public boolean doExisteItem(String oid_Produto_Cliente, String oid_Pessoa_Distribuidor)throws Excecoes{

	    if (!doValida(oid_Produto_Cliente) || !doValida(oid_Pessoa_Distribuidor))
	        throw new Excecoes("Parâmetros incorretos!");
	        
	    return new BancoUtil().doExiste("itens_Pedidos",
        	            			    "Produtos_Clientes.oid_Produto = itens_Pedidos.oid_Produto" +
        	            			    " AND Produtos_Clientes.oid_Produto_Cliente = '"+oid_Produto_Cliente+"'" +
        	            			    " AND Produtos_Clientes.oid_Pessoa = '"+oid_Pessoa_Distribuidor+"'");
	}
}