package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_EstoqueED;
import com.master.rn.WMS_EstoqueRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class wms005Bean  {
    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    private ArrayList lista = null;
    public void setLista(ArrayList array) {
        this.lista = array;
    }
    public ArrayList getLista() {
        return this.lista;
    }

    //*** Tranfere Fisicamente Quantidade de Entre Estoques
    public void tranfereEstoque(HttpServletRequest request) throws Excecoes {

        String oid_Estoque = request.getParameter("oid_Estoque");
        String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String TX_Observacao = request.getParameter("FT_TX_Observacao");

        if (!util.doValida(oid_Estoque))
            throw new Excecoes("ID Estoque não informado!");
        WMS_EstoqueED ed = new WMS_EstoqueED(oid_Estoque);

        if (util.doValida(oid_Produto_Cliente))
            ed.setOID_Produto_Cliente(oid_Produto_Cliente);
        if (util.doValida(oid_Localizacao))
            ed.setOID_Localizacao(Integer.parseInt(oid_Localizacao));
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOID_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));
        if (util.doValida(TX_Observacao))
            ed.setTx_Observacao(TX_Observacao);

        new WMS_EstoqueRN().tranfereEstoque(ed);
    }

    public WMS_EstoqueED inclui(HttpServletRequest request) throws Excecoes {

        WMS_EstoqueRN WMS_EstoqueRN = new WMS_EstoqueRN();
        WMS_EstoqueED ed = new WMS_EstoqueED();

        ed.setOID_Produto_Cliente(request.getParameter("oid_Produto_Cliente"));
        ed.setOID_Localizacao(Integer.valueOf(request.getParameter("oid_Localizacao")).intValue());
        ed.setNR_Quantidade(Double.parseDouble(request.getParameter("FT_NR_Quantidade")));
        ed.setOID_Tipo_Estoque(Integer.valueOf(request.getParameter("oid_Tipo_Estoque")).intValue());
        String TX_Observacao = request.getParameter("FT_TX_Observacao");

        if (util.doValida(TX_Observacao))
            ed.setTx_Observacao(TX_Observacao);

        return WMS_EstoqueRN.inclui(ed, true);

    }

    public void subtrai(String oid_estoque_cliente, int quantidade) throws Excecoes {
        WMS_EstoqueRN WMS_EstoqueRN = new WMS_EstoqueRN();
        WMS_EstoqueRN.subtrai(oid_estoque_cliente, quantidade, true);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        String oid_Estoque = request.getParameter("oid_Estoque");
        String oid_Produto = request.getParameter("oid_Produto_Cliente");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String TX_Observacao = request.getParameter("FT_TX_Observacao");

        if (!util.doValida(oid_Estoque))
            throw new Excecoes("ID Estoque não informado!");
        WMS_EstoqueED ed = new WMS_EstoqueED(oid_Estoque);

        if (util.doValida(oid_Produto))
            ed.setOID_Produto_Cliente(oid_Produto);
        if (util.doValida(oid_Localizacao))
            ed.setOID_Localizacao(Integer.parseInt(oid_Localizacao));
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOID_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));
        if (util.doValida(TX_Observacao))
            ed.setTx_Observacao(TX_Observacao);

        new WMS_EstoqueRN().altera(ed);
    }

    public void reajusteEstoque(HttpServletRequest request) throws Excecoes {

        String oid_Estoque = request.getParameter("oid_Estoque");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String TX_Observacao = request.getParameter("FT_TX_Observacao");

        if (!util.doValida(oid_Estoque))
            throw new Excecoes("ID Estoque não informado!");
        WMS_EstoqueED ed = new WMS_EstoqueED(oid_Estoque);

        if (util.doValida(oid_Produto))
            ed.setOID_Produto(Integer.parseInt(oid_Produto));
        if (util.doValida(oid_Produto_Cliente))
            ed.setOID_Produto_Cliente(oid_Produto_Cliente);
        if (util.doValida(TX_Observacao))
            ed.setTx_Observacao(TX_Observacao);
        if (util.doValida(oid_Localizacao))
            ed.setOID_Localizacao(Integer.parseInt(oid_Localizacao));
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOID_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));

        new WMS_EstoqueRN().reajusteEstoque(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        WMS_EstoqueRN WMS_Estoquern = new WMS_EstoqueRN();
        WMS_EstoqueED ed = new WMS_EstoqueED();
        ed.setOID_Estoque(request.getParameter("oid_Estoque"));
        WMS_Estoquern.deleta(ed);
    }

    public WMS_EstoqueED getByRecord(HttpServletRequest request) throws Excecoes {

        String OID_Estoque = request.getParameter("oid_Estoque");
        WMS_EstoqueED ed = new WMS_EstoqueED();
        if (util.doValida(OID_Estoque)) {
            ed.setOID_Estoque(OID_Estoque);
        }
        return new WMS_EstoqueRN().getByRecord(ed);
    }

    public boolean haNoEstoque(String parametro) throws Excecoes {
        return new WMS_EstoqueRN().haNoEstoque(parametro);
    }
    public boolean haNoEstoque(String parametro, String oid_tipo_estoque) throws Excecoes {
        return new WMS_EstoqueRN().haNoEstoque(parametro, Integer.parseInt(oid_tipo_estoque));
    }

    public ArrayList lista(HttpServletRequest request, String orderby) throws Excecoes {

        WMS_EstoqueED ed = new WMS_EstoqueED();

        if (util.doValida(request.getParameter("oid_Tipo_Estoque")))
            ed.setOID_Tipo_Estoque(Integer.valueOf(request.getParameter("oid_Tipo_Estoque")).intValue());

        if (util.doValida(request.getParameter("oid_Produto_Cliente")))
            ed.setOID_Produto_Cliente(request.getParameter("oid_Produto_Cliente"));

        //*** Fornecedor
        if (util.doValida(request.getParameter("oid_Pessoa")))
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
        //*** Distribuidor
        if (util.doValida(request.getParameter("oid_cliente")))
            ed.setOid_Pessoa_Distribuidor(request.getParameter("oid_cliente"));
        if (util.doValida(request.getParameter("oid_Localizacao")))
            ed.setOID_Localizacao(Integer.parseInt(request.getParameter("oid_Localizacao")));
        if (util.doValida(request.getParameter("FT_NR_Quantidade")))
            ed.setNR_Quantidade(Double.valueOf(request.getParameter("FT_NR_Quantidade")).doubleValue());

        this.setLista(new WMS_EstoqueRN().lista(ed, orderby));
        return this.getLista();
    }

    /** --------------------- QUANTIDADES ESTOQUE --------------------- **/
    /** QUANTIDADE DISPONÍVEL DE UM PRODUTO_CLIENTE EM ESTOQUE **/
    public double getQtdEstoque(String oid_Produto_Cliente, String oid_Tipo_Estoque, int oid_Localizacao) throws Excecoes {

        try {
            if (!util.doValida(oid_Produto_Cliente))
                throw new Excecoes("ID Produto Cliente não informado!");
            //*** Caso não seje passado a Localizacao, dusca no Produto
            if (oid_Localizacao < 1)
            {
                oid_Localizacao = util.getTableIntValue("oid_Localizacao","Produtos_Clientes","oid_Produto_Cliente = '"+oid_Produto_Cliente+"'");
                if (oid_Localizacao < 1)
                    throw new Excecoes("ID Localizacao não encontrado!");
            }
            //*** Caso não seje passado um Tipo de Estoque, dusca do Estoque Disponível
            if (!util.doValida(oid_Tipo_Estoque))
            {
                oid_Tipo_Estoque = String.valueOf(Parametro_FixoED.getInstancia().getOID_Tipo_Estoque());
                if (!util.doValida(oid_Tipo_Estoque))
                    throw new Excecoes("ID Tipo Estoque não informado!");
            }
            return util.getTableDoubleValue("sum(NR_Quantidade)",
                                             "Estoques_Clientes,Produtos_Clientes,Tipos_Estoques,Localizacoes",
                                             "Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente " +
                                             " AND Estoques_Clientes.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque "+
                                             " AND Estoques_Clientes.oid_Localizacao = Localizacoes.oid_Localizacao "+
                                             " AND Estoques_Clientes.oid_Tipo_Estoque = "+oid_Tipo_Estoque +
                                             " AND Estoques_Clientes.oid_Localizacao = "+oid_Localizacao +
                                             " AND Produtos_Clientes.oid_Produto_Cliente = '"+oid_Produto_Cliente+"'");
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getQtdEstoque()");
        }
    }
    public double getQtdEstoque(String oid_Produto_Cliente, String oid_Tipo_Estoque) throws Excecoes {

        try {
            if (!util.doValida(oid_Produto_Cliente))
                throw new Excecoes("ID Produto Cliente não informado!");
            //*** Caso não seje passado um Tipo de Estoque, dusca do Estoque Disponível
            if (!util.doValida(oid_Tipo_Estoque))
                oid_Tipo_Estoque = String.valueOf(Parametro_FixoED.getInstancia().getOID_Tipo_Estoque());
            return util.getTableDoubleValue("sum(NR_Quantidade)",
                                             "Estoques_Clientes,Produtos_Clientes,Tipos_Estoques ",
                                             "Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente " +
                                             " AND Estoques_Clientes.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque "+
                                             " AND Estoques_Clientes.oid_Tipo_Estoque = "+oid_Tipo_Estoque +
                                             " AND Produtos_Clientes.oid_Produto_Cliente = '"+oid_Produto_Cliente+"'");
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getQtdEstoque()");
        }
    }
    public double getQtdEstoque(String oid_Produto_Cliente) throws Excecoes {
        return this.getQtdEstoque(oid_Produto_Cliente, null);
    }


    /** QUANTIDADE TOTAL ATENDIDA DOS PRODUTOS **/
    public double getQtdTotalAtendida(String oid_Produto_Cliente, String oid_Produto, String oid_Item_Pedido, String oid_Item_Nota_Fiscal, String oid_Nota_Fiscal, String oid_Tipo_Estoque, int oid_Localizacao, String dmTipoNF, String dmSituacao) throws Excecoes {

        try {
            if (!util.doValida(oid_Produto) && !util.doValida(oid_Produto_Cliente) && !util.doValida(oid_Item_Pedido) && !util.doValida(oid_Item_Nota_Fiscal) && !util.doValida(oid_Nota_Fiscal))
                throw new Excecoes("Produto não informado para Consulta de Estoque ATENDIDO!");
            String from = " Itens_Notas_Fiscais , Notas_Fiscais , Produtos_Clientes ";
            String where = " Itens_Notas_Fiscais.DM_Situacao = 'N'" +
                           //" AND Notas_Fiscais.DM_Situacao = "+getSQLStringDef(dmSituacao, "N") +
            			   " AND Notas_Fiscais.DM_Finalizado = "+util.getSQLStringDef(dmSituacao, "A") +
                           " AND Itens_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal" +
                           " AND Itens_Notas_Fiscais.oid_Produto = Produtos_Clientes.oid_Produto";
            if (util.doValida(oid_Produto_Cliente))
                where += " AND Produtos_Clientes.oid_Produto_Cliente = "+util.getSQLString(oid_Produto_Cliente);
            else if (util.doValida(oid_Produto))
                where += " AND Itens_Notas_Fiscais.oid_Produto = " +util.getSQLString(oid_Produto);
            else if (util.doValida(oid_Item_Pedido))
                where += " AND Itens_Notas_Fiscais.oid_Item_Pedido = " +util.getSQLString(oid_Item_Pedido);
            else if (util.doValida(oid_Item_Nota_Fiscal))
                where += " AND Itens_Notas_Fiscais.oid_Item_Nota_Fiscal = " +util.getSQLString(oid_Item_Nota_Fiscal);
            else if (util.doValida(oid_Nota_Fiscal))
                where += " AND Itens_Notas_Fiscais.oid_Nota_Fiscal = " +util.getSQLString(oid_Nota_Fiscal);
            //----------------------//
            if (util.doValida(dmTipoNF))
                where += " AND Notas_Fiscais.DM_Tipo_Nota_Fiscal = "+util.getSQLString(dmTipoNF);
            if (util.doValida(oid_Tipo_Estoque)) {
            	from += ", Estoques_Clientes";
                where += " AND Produtos_Clientes.oid_Produto_Cliente = Estoques_Clientes.oid_Produto_Cliente" +
                         " AND Estoques_Clientes.oid_Tipo_Estoque = "+util.getSQLString(oid_Tipo_Estoque);
            }
            if (oid_Localizacao > 0)
                where += " AND Itens_Notas_Fiscais.oid_Localizacao = " +oid_Localizacao;
            return util.getTableDoubleValue("sum(NR_QT_Atendido)", from, where);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getQtdTotalAtendida()");
        }
    }
    public double getQtdTotalAtendida(String oid_Produto_Cliente, String oid_Produto, String oid_Tipo_Estoque, int oid_Localizacao, String dmTipoNota) throws Excecoes {
        return this.getQtdTotalAtendida(oid_Produto_Cliente, oid_Produto, null, null, null, oid_Tipo_Estoque, oid_Localizacao, dmTipoNota, null);
    }
    public double getQtdTotalAtendidaByItemPedido(String oid_Item_Pedido) throws Excecoes {
        return this.getQtdTotalAtendida(null, null, oid_Item_Pedido, null, null, null, -1, null, null);
    }
    public double getQtdTotalAtendidaByNotaItemPedido(String oid_Nota_Fiscal, String oid_Item_Pedido) throws Excecoes {
        return this.getQtdTotalAtendida(null, null, oid_Item_Pedido, null, oid_Nota_Fiscal, null, -1, null, null);
    }
    public double getQtdTotalAtendidaByNota(String oid_Nota_Fiscal) throws Excecoes {
        return this.getQtdTotalAtendida(null, null, null, null, oid_Nota_Fiscal, null, -1, null, null);
    }

    public ArrayList lista_Lote(HttpServletRequest request, String orderby) throws Excecoes {

        WMS_EstoqueED ed = new WMS_EstoqueED();

        if (util.doValida(request.getParameter("oid_Tipo_Estoque")))
            ed.setOID_Tipo_Estoque(Integer.valueOf(request.getParameter("oid_Tipo_Estoque")).intValue());

        if (util.doValida(request.getParameter("oid_Produto_Cliente")))
            ed.setOID_Produto_Cliente(request.getParameter("oid_Produto_Cliente"));

        //*** Fornecedor
        if (util.doValida(request.getParameter("oid_Pessoa")))
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
        //*** Distribuidor
        if (util.doValida(request.getParameter("oid_cliente")))
            ed.setOid_Pessoa_Distribuidor(request.getParameter("oid_cliente"));
        if (util.doValida(request.getParameter("oid_Localizacao")))
            ed.setOID_Localizacao(Integer.parseInt(request.getParameter("oid_Localizacao")));
        if (util.doValida(request.getParameter("FT_NR_Quantidade")))
            ed.setNR_Quantidade(Double.valueOf(request.getParameter("FT_NR_Quantidade")).doubleValue());

        if (util.doValida(request.getParameter("FT_NR_Lote_Produto")))
            ed.setNR_Lote_Produto(request.getParameter("FT_NR_Lote_Produto"));

        this.setLista(new WMS_EstoqueRN().lista_Lote(ed, orderby));
        return this.getLista();
    }

}