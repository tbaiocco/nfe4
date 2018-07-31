package com.master.iu;

/**
 * @author Andr� Valadas
 * M�dulo: Pre�os dos Produtos
*/
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Preco_ProdutoED;
import com.master.ed.Produto_ClienteED;
import com.master.rn.Preco_ProdutoRN;
import com.master.rn.Produto_ClienteRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Valor;

public class Pro004Bean extends JavaUtil {

	public Preco_ProdutoED inclui(HttpServletRequest request)throws Excecoes {

	    String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
	    String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
	    String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
	    String VL_Produto = request.getParameter("FT_VL_Produto");
	    String VL_Desconto_Avista = request.getParameter("FT_VL_Desconto_Avista");
	    String VL_Desconto_7_Dias = request.getParameter("FT_VL_Desconto_7_Dias");
	    String VL_Acrescimo_21_Dias = request.getParameter("FT_VL_Acrescimo_21_Dias");
	    String VL_Acrescimo_28_Dias = request.getParameter("FT_VL_Acrescimo_28_Dias");
        String VL_Acrescimo_30_Dias = request.getParameter("FT_VL_Acrescimo_30_Dias");
	    String PE_Desconto_Avista = request.getParameter("FT_PE_Desconto_Avista");
	    String PE_Desconto_7_Dias = request.getParameter("FT_PE_Desconto_7_Dias");
	    String PE_Acrescimo_21_Dias = request.getParameter("FT_PE_Acrescimo_21_Dias");
	    String PE_Acrescimo_28_Dias = request.getParameter("FT_PE_Acrescimo_28_Dias");
        String PE_Acrescimo_30_Dias = request.getParameter("FT_PE_Acrescimo_30_Dias");
	    
	    if (!doValida(oid_Produto_Cliente))
	        throw new Mensagens("Produto n�o informado!");
	    if (!doValida(oid_Pessoa_Distribuidor))
	        throw new Mensagens("Distribuidora n�o informada!");
	    if (!doValida(oid_Tabela_Venda))
	        throw new Mensagens("Tabela de Venda n�o informada!");
	    if (!doValida(VL_Produto))
	        throw new Mensagens("Valor do Produto n�o informado!");
	    
        Preco_ProdutoED ed = new Preco_ProdutoED(Integer.parseInt(oid_Tabela_Venda));
        ed.setOid_Pessoa(oid_Pessoa_Distribuidor);
        ed.setOID_Produto_Cliente(oid_Produto_Cliente);
        ed.setVL_Produto(Double.parseDouble(VL_Produto));
        
        //*** Valores
        if (doValida(VL_Desconto_Avista))
            ed.setVL_Desconto_Avista(Double.parseDouble(VL_Desconto_Avista));
        if (doValida(VL_Desconto_7_Dias))
            ed.setVL_Desconto_7_Dias(Double.parseDouble(VL_Desconto_7_Dias));
        if (doValida(VL_Acrescimo_21_Dias))
            ed.setVL_Acrescimo_21_Dias(Double.parseDouble(VL_Acrescimo_21_Dias));
        if (doValida(VL_Acrescimo_28_Dias))
            ed.setVL_Acrescimo_28_Dias(Double.parseDouble(VL_Acrescimo_28_Dias));
        if (doValida(VL_Acrescimo_30_Dias))
            ed.setVL_Acrescimo_30_Dias(Double.parseDouble(VL_Acrescimo_30_Dias));
        //*** Percentuais
        if (doValida(PE_Desconto_Avista))
            ed.setPE_Desconto_Avista(Double.parseDouble(PE_Desconto_Avista));
        if (doValida(PE_Desconto_7_Dias))
            ed.setPE_Desconto_7_Dias(Double.parseDouble(PE_Desconto_7_Dias));
        if (doValida(PE_Acrescimo_21_Dias))
            ed.setPE_Acrescimo_21_Dias(Double.parseDouble(PE_Acrescimo_21_Dias));
        if (doValida(PE_Acrescimo_28_Dias))
            ed.setPE_Acrescimo_28_Dias(Double.parseDouble(PE_Acrescimo_28_Dias));
        if (doValida(PE_Acrescimo_30_Dias))
            ed.setPE_Acrescimo_30_Dias(Double.parseDouble(PE_Acrescimo_30_Dias));
        
        return new Preco_ProdutoRN().inclui(ed);
	}
    
    public Preco_ProdutoED incluiByProduto(HttpServletRequest request)throws Excecoes {

        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Produto = request.getParameter("oid_Produto");
        
        if (!doValida(oid_Produto))
            throw new Mensagens("Produto n�o informado!");
        if (!doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Distribuidora n�o informada!");
        if (!doValida(oid_Tabela_Venda))
            throw new Mensagens("Tabela de Venda n�o informada!");
        //*** Valida se j� existe o Produto na Tabela
        if (new BancoUtil().doExiste("Precos_Produtos","oid_Produto_Cliente = '"+(oid_Produto+oid_Pessoa_Distribuidor)+"'" +
                                     " AND oid_Tabela_Venda = "+oid_Tabela_Venda+
                                     " AND oid_Pessoa = '"+oid_Pessoa_Distribuidor+"'"))
            throw new Mensagens("O Produto informado ja consta nessa Tabela!");
        
        Produto_ClienteED edProduto = new Produto_ClienteRN().getByRecord(new Produto_ClienteED(oid_Produto+oid_Pessoa_Distribuidor));
        
        if (!doValida(edProduto.getOID_Produto_Cliente()))
            throw new Mensagens("Produto Cliente n�o encontrado!");
        if (edProduto.getVL_Produto() <= 0)
            throw new Mensagens("Produto sem Valor de ULTIMA COMPRA!", "INCLUI");
        if (edProduto.getVL_Markup() <= 0)
            throw new Mensagens("Produto sem Valor de MARKUP!", "INCLUI");
        
        Preco_ProdutoED ed = new Preco_ProdutoED(Integer.parseInt(oid_Tabela_Venda));
        ed.setOid_Pessoa(oid_Pessoa_Distribuidor);
        ed.setOID_Produto_Cliente(edProduto.getOID_Produto_Cliente());
        ed.setPE_Desconto_Avista(edProduto.getPE_Desconto_Avista());
        ed.setPE_Desconto_7_Dias(edProduto.getPE_Desconto_7_Dias());
        ed.setPE_Acrescimo_21_Dias(edProduto.getPE_Acrescimo_21_Dias());
        ed.setPE_Acrescimo_28_Dias(edProduto.getPE_Acrescimo_28_Dias());
        ed.setDT_Vigencia(Data.getDataDMY());
        
        //*** Calcula
        calculaPrecosByPerc(ed, edProduto.getVL_Produto(), edProduto.getVL_Markup(), 0);
        
        return new Preco_ProdutoRN().inclui(ed);
    }

	public void altera(HttpServletRequest request)throws Excecoes{

        String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
	    String VL_Produto = request.getParameter("FT_VL_Produto");
	    String VL_Desconto_Avista = request.getParameter("FT_VL_Desconto_Avista");
	    String VL_Desconto_7_Dias = request.getParameter("FT_VL_Desconto_7_Dias");
	    String VL_Acrescimo_21_Dias = request.getParameter("FT_VL_Acrescimo_21_Dias");
	    String VL_Acrescimo_28_Dias = request.getParameter("FT_VL_Acrescimo_28_Dias");
        String VL_Acrescimo_30_Dias = request.getParameter("FT_VL_Acrescimo_30_Dias");
	    String PE_Desconto_Avista = request.getParameter("FT_PE_Desconto_Avista");
	    String PE_Desconto_7_Dias = request.getParameter("FT_PE_Desconto_7_Dias");
	    String PE_Acrescimo_21_Dias = request.getParameter("FT_PE_Acrescimo_21_Dias");
	    String PE_Acrescimo_28_Dias = request.getParameter("FT_PE_Acrescimo_28_Dias");
        String PE_Acrescimo_30_Dias = request.getParameter("FT_PE_Acrescimo_30_Dias");
        String DM_Alterado = request.getParameter("FT_DM_Alterado");
	    
	    if (!doValida(oid_Preco_Produto))
	        throw new Mensagens("ID Pre�o Produto n�o informado!");
	    if (!doValida(VL_Produto))
	        throw new Mensagens("Valor do Produto n�o informado!");
	    
        Preco_ProdutoED ed = new Preco_ProdutoED(Long.parseLong(oid_Preco_Produto));
        ed.setVL_Produto(Double.parseDouble(VL_Produto));
        
        //*** Valores
        if (doValida(VL_Desconto_Avista))
            ed.setVL_Desconto_Avista(Double.parseDouble(VL_Desconto_Avista));
        if (doValida(VL_Desconto_7_Dias))
            ed.setVL_Desconto_7_Dias(Double.parseDouble(VL_Desconto_7_Dias));
        if (doValida(VL_Acrescimo_21_Dias))
            ed.setVL_Acrescimo_21_Dias(Double.parseDouble(VL_Acrescimo_21_Dias));
        if (doValida(VL_Acrescimo_28_Dias))
            ed.setVL_Acrescimo_28_Dias(Double.parseDouble(VL_Acrescimo_28_Dias));
        if (doValida(VL_Acrescimo_30_Dias))
            ed.setVL_Acrescimo_30_Dias(Double.parseDouble(VL_Acrescimo_30_Dias));
        //*** Percentuais
        if (doValida(PE_Desconto_Avista))
            ed.setPE_Desconto_Avista(Double.parseDouble(PE_Desconto_Avista));
        if (doValida(PE_Desconto_7_Dias))
            ed.setPE_Desconto_7_Dias(Double.parseDouble(PE_Desconto_7_Dias));
        if (doValida(PE_Acrescimo_21_Dias))
            ed.setPE_Acrescimo_21_Dias(Double.parseDouble(PE_Acrescimo_21_Dias));
        if (doValida(PE_Acrescimo_28_Dias))
            ed.setPE_Acrescimo_28_Dias(Double.parseDouble(PE_Acrescimo_28_Dias));
        if (doValida(PE_Acrescimo_30_Dias))
            ed.setPE_Acrescimo_30_Dias(Double.parseDouble(PE_Acrescimo_30_Dias));
        if (doValida(DM_Alterado))
            ed.setDM_Alterado(DM_Alterado);

        new Preco_ProdutoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request)throws Excecoes {

	    String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
	    if (!doValida(oid_Preco_Produto))
	        throw new Mensagens("ID Pre�o Produto n�o informado!");
	    
        new Preco_ProdutoRN().deleta(new Preco_ProdutoED(Long.parseLong(oid_Preco_Produto)));
	}

	public Preco_ProdutoED getByRecord(HttpServletRequest request)throws Excecoes {

	    Preco_ProdutoED ed = new Preco_ProdutoED();

	    String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
	    String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
	    if (doValida(oid_Preco_Produto))
            ed.setOID_Preco_Produto(Long.parseLong(oid_Preco_Produto));
	    if (doValida(oid_Tabela_Venda))
	        ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
        return new Preco_ProdutoRN().getByRecord(ed);
	}
    
    public Preco_ProdutoED getByRecord(HttpServletRequest request, boolean loadTable)throws Excecoes {

        Preco_ProdutoED ed = new Preco_ProdutoED(0, loadTable, true);

        String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        if (doValida(oid_Preco_Produto))
            ed.setOID_Preco_Produto(Long.parseLong(oid_Preco_Produto));
        if (doValida(oid_Tabela_Venda))
            ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
        return new Preco_ProdutoRN().getByRecord(ed);
    }

	public ArrayList Produto_Lista(HttpServletRequest request)throws Excecoes{

    	Preco_ProdutoED ed = new Preco_ProdutoED();
        String OID_Pessoa = request.getParameter("oid_Pessoa");
    	String OID_Preco_Produto = request.getParameter("oid_Preco_Produto");
        String OID_Produto_Cliente = request.getParameter("oid_Produto_Cliente");

        if (doValida(OID_Pessoa))
            ed.setOid_Pessoa(OID_Pessoa);
        if (doValida(OID_Preco_Produto))
            ed.setOID_Preco_Produto(Long.parseLong(OID_Preco_Produto));
        if (doValida(OID_Produto_Cliente))
            ed.setOID_Produto_Cliente(OID_Produto_Cliente);

        ed.setNM_Tabela(request.getParameter("FT_NM_Tabela"));
        ed.setDT_Vigencia(request.getParameter("FT_DT_Vigencia"));
        ed.setDT_Validade(request.getParameter("FT_DT_Validade"));
        ed.setDM_Alterado(request.getParameter("FT_DM_Alterado"));

        return new Preco_ProdutoRN().lista(ed);
	}
    
    /** M�TODOS AUXILIARES */
	public Preco_ProdutoED getPrecoByOIDCondPagamento(int oid_Condicao_Pagamento, long oid_Preco_Produto) throws Exception{

	    if (oid_Condicao_Pagamento < 1)
	        throw new Mensagens("ID Condi��o Pagamento n�o informado!");
	    if (oid_Preco_Produto < 1)
	        throw new Mensagens("ID Pre�o Produto n�o informado!");

	    //*** Dados do Preco do Produto
        Preco_ProdutoED edPreco = new Preco_ProdutoRN().getByRecord(new Preco_ProdutoED(oid_Preco_Produto, false, false));
	    
	    //*** Dados da Condic�o de Pagamento
	    return edPreco;
	}
    public Preco_ProdutoED getPrecoByTabela(int oid_Condicao_Pagamento, String oid_Produto, int oid_Tabela_Venda) throws Exception{

        if (oid_Condicao_Pagamento < 1)
            throw new Mensagens("ID Condi��o Pagamento n�o informado!");
        if (!doValida(oid_Produto))
            throw new Mensagens("ID Produto n�o informado!");
        if (oid_Tabela_Venda < 1)
            throw new Mensagens("ID Tabela Venda n�o informada!");
        
        long oid_PrecoProduto = new BancoUtil().getTableIntValue("oid_Preco_Produto", "Precos_Produtos", 
                "Precos_Produtos.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente" +
                " AND Produtos_Clientes.oid_Produto = '"+oid_Produto+"'" +
                " AND Precos_Produtos.oid_Tabela_Venda = "+oid_Tabela_Venda);
        if (oid_PrecoProduto < 1)
            return new Preco_ProdutoED();
        return this.getPrecoByOIDCondPagamento(oid_Condicao_Pagamento, oid_PrecoProduto);
    }
    
    
    //*** Calcula Valor para venda (% sobre %)
    public static void calculaPrecosByPerc(Preco_ProdutoED edPreco, double vlProduto, double vlMarkup, double peReajuste) {
        
        double vlVenda = vlProduto;
        if (vlVenda <= 0)
            return;
        if (vlMarkup > 0)
            vlVenda = Valor.getValorArredondado(vlProduto / vlMarkup);
        if (peReajuste > 0)
            vlVenda = Valor.getValorArredondado(vlVenda + Valor.calcPercentual(vlVenda, peReajuste));

        edPreco.setVL_Desconto_Avista(vlVenda);
        edPreco.setVL_Desconto_7_Dias(vlVenda);
        edPreco.setVL_Produto(vlVenda);
        edPreco.setVL_Acrescimo_21_Dias(vlVenda);
        edPreco.setVL_Acrescimo_28_Dias(vlVenda);
        
        if (edPreco.getPE_Desconto_7_Dias() > 0)
            edPreco.setVL_Desconto_7_Dias(Valor.getValorArredondado(vlVenda - Valor.calcPercentual(vlVenda, edPreco.getPE_Desconto_7_Dias())));
        if (edPreco.getPE_Desconto_Avista() > 0)
            edPreco.setVL_Desconto_Avista(Valor.getValorArredondado(edPreco.getVL_Desconto_7_Dias() - Valor.calcPercentual(edPreco.getVL_Desconto_7_Dias(), edPreco.getPE_Desconto_Avista())));
        if (edPreco.getPE_Acrescimo_21_Dias() > 0)
            edPreco.setVL_Acrescimo_21_Dias(Valor.getValorArredondado(vlVenda + Valor.calcPercentual(vlVenda, edPreco.getPE_Acrescimo_21_Dias())));
        if (edPreco.getPE_Acrescimo_28_Dias() > 0)
            edPreco.setVL_Acrescimo_28_Dias(Valor.getValorArredondado(edPreco.getVL_Acrescimo_21_Dias() + Valor.calcPercentual(edPreco.getVL_Acrescimo_21_Dias(), edPreco.getPE_Acrescimo_28_Dias())));
        if (edPreco.getPE_Acrescimo_30_Dias() > 0)
            edPreco.setVL_Acrescimo_30_Dias(Valor.getValorArredondado(edPreco.getVL_Acrescimo_28_Dias() + Valor.calcPercentual(edPreco.getVL_Acrescimo_28_Dias(), edPreco.getPE_Acrescimo_30_Dias())));
        else edPreco.setVL_Acrescimo_30_Dias(edPreco.getVL_Acrescimo_28_Dias());
    }
}