package com.master.iu;

/**
 * Título: Servico_Nota_Fiscal_TransacoesBean
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.rn.Servico_Nota_Fiscal_TransacoesRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.ManipulaString;
import com.master.util.Mensagens;
import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.rn.WMS_Item_Produto_ClienteRN;

public class Ped005ServicoBean extends BancoUtil implements Serializable {

    public Item_Nota_Fiscal_TransacoesED inclui(HttpServletRequest request)throws Excecoes{

        Item_Nota_Fiscal_TransacoesED ed = new Item_Nota_Fiscal_TransacoesED();

        String oid_Tipo_Servico = request.getParameter("oid_Tipo_Servico");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String VL_Item = request.getParameter("FT_VL_Item");
        String VL_Unitario = request.getParameter("FT_VL_Unitario");
        String VL_ISSQN = request.getParameter("FT_VL_ISSQN");
        String PE_ISSQN = request.getParameter("FT_PE_ISSQN");

        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");

        if (doValida(oid_Nota_Fiscal))
        	ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);

        if (doValida(oid_Tipo_Servico))
            ed.setOid_Tipo_Servico(Integer.parseInt(oid_Tipo_Servico));

        if (doValida(VL_Item))
            ed.setVL_Item(Double.parseDouble(VL_Item));
        if (doValida(VL_Unitario))
            ed.setVL_Unitario(Double.parseDouble(VL_Unitario));
        if (doValida(VL_ISSQN))
            ed.setVL_ISSQN(Double.parseDouble(VL_ISSQN));
        if (doValida(PE_ISSQN))
            ed.setPE_ISSQN(Double.parseDouble(PE_ISSQN));

        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));

        return new Servico_Nota_Fiscal_TransacoesRN().inclui(ed);
	}

    public void altera(HttpServletRequest request)throws Excecoes{

    	Item_Nota_Fiscal_TransacoesED ed = new Item_Nota_Fiscal_TransacoesED();

    	String oid_Item_Nota_Fiscal = request.getParameter("oid_Item_Nota_Fiscal");
    	String oid_Tipo_Servico = request.getParameter("oid_Tipo_Servico");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String VL_Item = request.getParameter("FT_VL_Item");
        String VL_Unitario = request.getParameter("FT_VL_Unitario");
        String VL_ISSQN = request.getParameter("FT_VL_ISSQN");
        String PE_ISSQN = request.getParameter("FT_PE_ISSQN");

        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");

        if (doValida(oid_Item_Nota_Fiscal))
            ed.setOID_Item_Nota_Fiscal(Long.parseLong(oid_Item_Nota_Fiscal));
        else throw new Excecoes("ID Item Nota Fiscal não informado!");
        if (doValida(oid_Nota_Fiscal))
        	ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);

        if (doValida(oid_Tipo_Servico))
            ed.setOid_Tipo_Servico(Integer.parseInt(oid_Tipo_Servico));

        if (doValida(VL_Item))
            ed.setVL_Item(Double.parseDouble(VL_Item));
        if (doValida(VL_Unitario))
            ed.setVL_Unitario(Double.parseDouble(VL_Unitario));
        if (doValida(VL_ISSQN))
            ed.setVL_ISSQN(Double.parseDouble(VL_ISSQN));
        if (doValida(PE_ISSQN))
            ed.setPE_ISSQN(Double.parseDouble(PE_ISSQN));

        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));

        new Servico_Nota_Fiscal_TransacoesRN().altera(ed);
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

        new Servico_Nota_Fiscal_TransacoesRN().deleta(ed);
    }

    public Item_Nota_Fiscal_TransacoesED getByRecord(HttpServletRequest request)throws Excecoes{

    	String oid_Item_Nota_Fiscal = request.getParameter("oid_Item_Nota_Fiscal");
    	if (!doValida(oid_Item_Nota_Fiscal))
    	    throw new Mensagens("ID Item Nota Fiscal não informado!");
    	return new Servico_Nota_Fiscal_TransacoesRN().getByRecord(new Item_Nota_Fiscal_TransacoesED(Long.parseLong(oid_Item_Nota_Fiscal)));
    }

    public ArrayList Item_Nota_Fiscal_Lista(HttpServletRequest request)throws Excecoes{

    	Item_Nota_Fiscal_TransacoesED ed = new Item_Nota_Fiscal_TransacoesED();
    	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");

        if (doValida(oid_Nota_Fiscal))
            ed.setOID_Nota_Fiscal(ManipulaString.tiraAspas(oid_Nota_Fiscal));

        ArrayList lista = new Servico_Nota_Fiscal_TransacoesRN().lista(ed);
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Item_Nota_Fiscal_TransacoesED) o1).getNM_Tipo_Servico().compareTo(((Item_Nota_Fiscal_TransacoesED) o2).getNM_Tipo_Servico());
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