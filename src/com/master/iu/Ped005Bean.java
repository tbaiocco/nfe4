package com.master.iu;

/**
 * Título: Item_Nota_Fiscal_TransacoesBean
 * Autor:  André da Silva Valadas
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.rn.Item_Nota_Fiscal_TransacoesRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.ManipulaString;
import com.master.util.Mensagens;
import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.rn.WMS_Item_Produto_ClienteRN;

public class Ped005Bean extends BancoUtil implements Serializable {
    
    public Item_Nota_Fiscal_TransacoesED inclui(HttpServletRequest request)throws Excecoes{

        Item_Nota_Fiscal_TransacoesED ed = new Item_Nota_Fiscal_TransacoesED();
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");

        String NR_Lote_Produto = request.getParameter("FT_NR_Lote_Produto");
		
        String oid_Pedido = request.getParameter("oid_Pedido");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
        String NR_QT_Atendido = request.getParameter("FT_NR_QT_Atendido");
        String VL_Item = request.getParameter("FT_VL_Item");
        String VL_Produto = request.getParameter("FT_VL_Produto");
        String VL_Unitario = request.getParameter("FT_VL_Unitario");
        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        String PE_Desconto = request.getParameter("FT_PE_Desconto");
        String PE_Desconto_Extra = request.getParameter("FT_PE_Desconto_Extra");
        
        String VL_IPI = request.getParameter("FT_VL_IPI");
        String VL_ICMS = request.getParameter("FT_VL_ICMS");
        String VL_ICMS_Aprov = request.getParameter("FT_VL_ICMS_Aprov");
        String PE_Aliquota_ICMS = request.getParameter("FT_PE_Aliquota_ICMS");
        String PE_Aliquota_ICMS_Aprov = request.getParameter("FT_PE_Aliquota_ICMS_Aprov");
        
        String DM_Quantidade = request.getParameter("FT_DM_Quantidade");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String NR_QT_Devolucao = request.getParameter("FT_NR_QT_Devolucao");
        String VL_Desconto_NF = request.getParameter("FT_VL_Desconto_NF");
        String VL_Custo = request.getParameter("FT_VL_Custo");
        String VL_Adicional = request.getParameter("FT_VL_Adicional");
        ed.setAtualizaItensNota("true".equals(request.getParameter("atualizaItensNota")));
        ed.setAtualizaValorNota("true".equals(request.getParameter("atualizaValorNota")));
        ed.setVendaDireta("true".equals(request.getParameter("vendaDireta")));
        
        //Monta o oid do produto_cliente
        if ("D".equals(DM_Tipo_Nota_Fiscal)) // se for nota de devolucao pega o oid do fornecedor
        	ed.setOid_Produto_Cliente(oid_Produto + oid_Pessoa_Fornecedor);
        else
        	ed.setOid_Produto_Cliente(oid_Produto + oid_Pessoa); // nota de entrada e saida... 
        
        if (doValida(oid_Pedido))
            ed.setOID_Pedido(Long.parseLong(oid_Pedido));        

        if (doValida(NR_Lote_Produto))
            ed.setNR_Lote_Produto(NR_Lote_Produto);        
        
        if (doValida(oid_Natureza_Operacao))
            ed.setOid_natureza_operacao(Long.parseLong(oid_Natureza_Operacao));        
        if (doValida(oid_Item_Pedido))
            ed.setOID_Item_Pedido(Long.parseLong(oid_Item_Pedido));
        if (doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (doValida(oid_Nota_Fiscal))
        	ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
        if (doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
        if (doValida(oid_Preco_Produto))
            ed.setOid_Preco_Produto(Integer.parseInt(oid_Preco_Produto));

        if (doValida(NR_QT_Atendido)){
            ed.setNR_QT_Atendido(Double.parseDouble(NR_QT_Atendido));
            ed.setNR_Peso_Real(ed.getNR_QT_Atendido());
        }
        if (doValida(VL_Item))
            ed.setVL_Item(Double.parseDouble(VL_Item));
        if (doValida(VL_Produto))
        	ed.setVL_Produto(Double.parseDouble(VL_Produto));
        if (doValida(VL_Unitario))
            ed.setVL_Unitario(Double.parseDouble(VL_Unitario));
        if (doValida(VL_Desconto))
            ed.setVL_Desconto(Double.parseDouble(VL_Desconto));
        if (doValida(PE_Desconto))
            ed.setPE_Desconto(Double.parseDouble(PE_Desconto));
        if (doValida(PE_Desconto_Extra))
            ed.setPE_Desconto_Extra(Double.parseDouble(PE_Desconto_Extra));
        
        if (doValida(VL_IPI))
            ed.setVL_IPI(Double.parseDouble(VL_IPI));
        if (doValida(VL_ICMS))
            ed.setVL_ICMS(Double.parseDouble(VL_ICMS));
        if (doValida(VL_ICMS_Aprov))
            ed.setVL_ICMS_Aprov(Double.parseDouble(VL_ICMS_Aprov));
        if (doValida(PE_Aliquota_ICMS))
            ed.setPE_Aliquota_ICMS(Double.parseDouble(PE_Aliquota_ICMS));
        if (doValida(PE_Aliquota_ICMS_Aprov))
            ed.setPE_Aliquota_ICMS_Aprov(Double.parseDouble(PE_Aliquota_ICMS_Aprov));
        if (doValida(DM_Quantidade))
            ed.setDM_Quantidade(DM_Quantidade);
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));
        if (doValida(NR_QT_Devolucao))
            ed.setNR_QT_Devolucao(Double.parseDouble(NR_QT_Devolucao));
        if (doValida(VL_Desconto_NF))
            ed.setVL_Desconto_NF(Double.parseDouble(VL_Desconto_NF));
        if (doValida(VL_Custo))
            ed.setVL_Custo(Double.parseDouble(VL_Custo));
        if (doValida(VL_Adicional))
            ed.setVL_Adicional(Double.parseDouble(VL_Adicional));

        return new Item_Nota_Fiscal_TransacoesRN().inclui(ed);
	}

    public void altera(HttpServletRequest request)throws Excecoes{

    	Item_Nota_Fiscal_TransacoesED ed = new Item_Nota_Fiscal_TransacoesED();

    	String oid_Item_Nota_Fiscal = request.getParameter("oid_Item_Nota_Fiscal");
    	String oid_Pedido = request.getParameter("oid_Pedido");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
        String NR_Lote_Produto = request.getParameter("FT_NR_Lote_Produto");
        String NR_QT_Atendido = request.getParameter("FT_NR_QT_Atendido"); 
        String VL_Item = request.getParameter("FT_VL_Item");
        String VL_Produto = request.getParameter("FT_VL_Produto");
        String VL_Unitario = request.getParameter("FT_VL_Unitario");
        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        String PE_Desconto = request.getParameter("FT_PE_Desconto");
        String PE_Desconto_Extra = request.getParameter("FT_PE_Desconto_Extra");
        String VL_IPI = request.getParameter("FT_VL_IPI");
        String VL_ICMS = request.getParameter("FT_VL_ICMS");
        String VL_ICMS_Aprov = request.getParameter("FT_VL_ICMS_Aprov");
        String PE_Aliquota_ICMS = request.getParameter("FT_PE_Aliquota_ICMS");
        String PE_Aliquota_ICMS_Aprov = request.getParameter("FT_PE_Aliquota_ICMS_Aprov");
        String DM_Quantidade = request.getParameter("FT_DM_Quantidade");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String NR_QT_Devolucao = request.getParameter("FT_NR_QT_Devolucao");
        String VL_Desconto_NF = request.getParameter("FT_VL_Desconto_NF");
        String VL_Custo = request.getParameter("FT_VL_Custo");
        String VL_Adicional = request.getParameter("FT_VL_Adicional");
        String Dm_Devolvido = request.getParameter("FT_DM_Devolvido");
        
        if (doValida(oid_Item_Nota_Fiscal))
            ed.setOID_Item_Nota_Fiscal(Long.parseLong(oid_Item_Nota_Fiscal));
        else throw new Excecoes("ID Item Nota Fiscal não informado!");
        ed.setAtualizaItensNota("true".equals(request.getParameter("atualizaItensNota")));
        ed.setAtualizaValorNota("true".equals(request.getParameter("atualizaValorNota")));
        ed.setVendaDireta("true".equals(request.getParameter("vendaDireta")));
        if (doValida(oid_Pedido))
            ed.setOID_Pedido(Long.parseLong(oid_Pedido));        
        if (doValida(oid_Natureza_Operacao))
            ed.setOid_natureza_operacao(Long.parseLong(oid_Natureza_Operacao));        
        if (doValida(oid_Item_Pedido))
            ed.setOID_Item_Pedido(Long.parseLong(oid_Item_Pedido));
        if (doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (doValida(oid_Nota_Fiscal))
        	ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
        if (doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
        if (doValida(oid_Preco_Produto))
            ed.setOid_Preco_Produto(Integer.parseInt(oid_Preco_Produto));

        if (doValida(NR_Lote_Produto))
            ed.setNR_Lote_Produto(NR_Lote_Produto);        

        if (doValida(Dm_Devolvido))
            ed.setDm_Devolvido(Dm_Devolvido);
        
        if (doValida(NR_QT_Atendido))
        {
            ed.setNR_QT_Atendido(Double.parseDouble(NR_QT_Atendido));
            ed.setNR_Peso_Real(ed.getNR_QT_Atendido());
        }
        if (doValida(VL_Item))
            ed.setVL_Item(Double.parseDouble(VL_Item));
        if (doValida(VL_Produto))
        	ed.setVL_Produto(Double.parseDouble(VL_Produto));
        if (doValida(VL_Unitario))
            ed.setVL_Unitario(Double.parseDouble(VL_Unitario));
        if (doValida(VL_Desconto))
            ed.setVL_Desconto(Double.parseDouble(VL_Desconto));
        if (doValida(PE_Desconto))
            ed.setPE_Desconto(Double.parseDouble(PE_Desconto));
        if (doValida(PE_Desconto_Extra))
            ed.setPE_Desconto_Extra(Double.parseDouble(PE_Desconto_Extra));
        if (doValida(VL_IPI))
            ed.setVL_IPI(Double.parseDouble(VL_IPI));
        if (doValida(VL_ICMS))
            ed.setVL_ICMS(Double.parseDouble(VL_ICMS));
        if (doValida(VL_ICMS_Aprov))
            ed.setVL_ICMS_Aprov(Double.parseDouble(VL_ICMS_Aprov));
        if (doValida(PE_Aliquota_ICMS))
            ed.setPE_Aliquota_ICMS(Double.parseDouble(PE_Aliquota_ICMS));
        if (doValida(PE_Aliquota_ICMS_Aprov))
            ed.setPE_Aliquota_ICMS_Aprov(Double.parseDouble(PE_Aliquota_ICMS_Aprov));
        if (doValida(DM_Quantidade))
            ed.setDM_Quantidade(DM_Quantidade);
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));
        if (doValida(NR_QT_Devolucao))
            ed.setNR_QT_Devolucao(Double.parseDouble(NR_QT_Devolucao));
        if (doValida(VL_Desconto_NF))
            ed.setVL_Desconto_NF(Double.parseDouble(VL_Desconto_NF));
        if (doValida(VL_Custo))
            ed.setVL_Custo(Double.parseDouble(VL_Custo));
        if (doValida(VL_Adicional))
            ed.setVL_Adicional(Double.parseDouble(VL_Adicional));
        
        new Item_Nota_Fiscal_TransacoesRN().altera(ed);    
    }

    public void deleta(HttpServletRequest request)throws Excecoes{

        String oid_Item_Nota_Fiscal = request.getParameter("oid_Item_Nota_Fiscal");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String NR_QT_Atendido = request.getParameter("FT_NR_QT_Atendido"); 
        String NR_QT_Devolucao = request.getParameter("FT_NR_QT_Devolucao");
        
        if (!doValida(oid_Item_Nota_Fiscal))
            throw new Excecoes("ID Item Nota Fiscal não informado!");
        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("ID Nota Fiscal não informado!");        

        Item_Nota_Fiscal_TransacoesED ed = new Item_Nota_Fiscal_TransacoesED(Long.parseLong(oid_Item_Nota_Fiscal));
        ed.setAtualizaItensNota("true".equals(request.getParameter("atualizaItensNota")));
        ed.setAtualizaValorNota("true".equals(request.getParameter("atualizaValorNota")));
        ed.setVendaDireta("true".equals(request.getParameter("vendaDireta")));
        ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
        if (doValida(oid_Item_Pedido))
            ed.setOID_Item_Pedido(Long.parseLong(oid_Item_Pedido));
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));
        if (doValida(NR_QT_Atendido))
            ed.setNR_QT_Atendido(Double.parseDouble(NR_QT_Atendido));
        if (doValida(NR_QT_Devolucao))
            ed.setNR_QT_Devolucao(Double.parseDouble(NR_QT_Devolucao));

        new Item_Nota_Fiscal_TransacoesRN().deleta(ed);    
    }

    public Item_Nota_Fiscal_TransacoesED getByRecord(HttpServletRequest request)throws Excecoes{

    	String oid_Item_Nota_Fiscal = request.getParameter("oid_Item_Nota_Fiscal");
    	if (!doValida(oid_Item_Nota_Fiscal))
    	    throw new Mensagens("ID Item Nota Fiscal não informado!");
    	return new Item_Nota_Fiscal_TransacoesRN().getByRecord(new Item_Nota_Fiscal_TransacoesED(Long.parseLong(oid_Item_Nota_Fiscal)));
    }

    public ArrayList Item_Nota_Fiscal_Lista(HttpServletRequest request)throws Excecoes{

    	Item_Nota_Fiscal_TransacoesED ed = new Item_Nota_Fiscal_TransacoesED();
    	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");

        if (doValida(oid_Nota_Fiscal))
            ed.setOID_Nota_Fiscal(ManipulaString.tiraAspas(oid_Nota_Fiscal));
    	
        ArrayList lista = new Item_Nota_Fiscal_TransacoesRN().lista(ed);
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Item_Nota_Fiscal_TransacoesED) o1).getNM_Produto().compareTo(((Item_Nota_Fiscal_TransacoesED) o2).getNM_Produto());
            }
        });
    	return lista;
    }

    public ArrayList listaItemPedido(HttpServletRequest request)throws Excecoes{

    	WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Produto = request.getParameter("oid_Produto");
        String NR_Quantidade_Movimento = request.getParameter("NR_Quantidade_Movimento");
        String VL_Produto = request.getParameter("VL_Produto");
        
        if (doValida(oid_Pessoa))
            ed.setOID_Pessoa(oid_Pessoa);
        if (doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto );

        return new WMS_Item_Produto_ClienteRN().listaItemPedido(ed);
    }
    
    //*** Busca Valor Total NF
	public double getValorTotalNF(String oid_Nota_Fiscal) throws Excecoes{

	    if (!doValida(oid_Nota_Fiscal)){
	        throw new Excecoes("Nota Fiscal não informada!", this.getClass().getName(), "getValorTotalNF()");
	    }
	    String Where = "oid_Nota_Fiscal = '" +oid_Nota_Fiscal + "'";
	    return super.getTableDoubleValue("vl_liquido_nota_fiscal",
	            	   					 "Notas_Fiscais",
	            	   					 Where);
	}
}