package com.master.rn;

/**
 * @author André Valadas
 */

import java.util.ArrayList;
import com.master.bd.Item_PedidoBD;
import com.master.bd.PedidoBD;
import com.master.ed.Item_PedidoED;
import com.master.ed.Promocao_ProdutoED;
import com.master.iu.ProdutoBean;
import com.master.root.VendedorBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.Transacao;

public class Item_PedidoRN extends Transacao {

    public Item_PedidoRN() {
    }

    public Item_PedidoED inclui(Item_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Item_PedidoBD(this.sql).inclui(ed);
            new PedidoBD(this.sql).total_Pedido(ed.getOID_Pedido());
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void altera(Item_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Item_PedidoBD(this.sql).altera(ed);
            new PedidoBD(this.sql).total_Pedido(ed.getOID_Pedido());
            this.fimTransacao(true);
        } catch(Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(Item_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Item_PedidoBD(this.sql).deleta(ed);
            new PedidoBD(this.sql).total_Pedido(ed.getOID_Pedido());
            this.fimTransacao(true);
        } catch(Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public Item_PedidoED getByRecord(Item_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Item_PedidoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista(Item_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Item_PedidoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_Recebidos(Item_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Item_PedidoBD(sql).lista_Recebidos(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    //*** ATUALIZA SITUACAO Item do Pedido
    public void atualizaSituacaoItemPedido(Item_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
	        new Item_PedidoBD(this.sql).atualizaSituacaoItemPedido(ed);
	        new PedidoBD(this.sql).total_Pedido(ed.getOID_Pedido());
	        this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    /** PESAGEM ITENS
     *  listaItens<Itens_Pedidos>
     */
    public void pesagemItens(ArrayList listaItens) throws Exception {

        if (listaItens.size() < 1)
            throw new Mensagens("Lista de Itens vazia! Execute novamente a consulta!");
        
        try {
            long oid_Pedido = 0;
            this.inicioTransacao();
            for (int i=0; i<listaItens.size(); i++)
            {
                Item_PedidoED edItem = (Item_PedidoED) listaItens.get(i);
                oid_Pedido = edItem.getOID_Pedido();
                this.sql.executarUpdate(" UPDATE Itens_Pedidos SET " +
                                        "    NR_Peso_Real = "+edItem.getNR_Peso_Real()+
                                        "   ,VL_Desconto = "+edItem.getVL_Desconto()+
                                        "   ,VL_Promocao = "+edItem.getVL_Promocao()+
                                        "   ,VL_Produto = "+edItem.getVL_Produto()+
                                        "   ,VL_Item = "+edItem.getVL_Item()+
                                        " WHERE oid_Item_Pedido = "+edItem.getOID_Item_Pedido());
            }
            new PedidoBD(this.sql).total_Pedido(oid_Pedido);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    /** ---------------------------------------------------------------------- **/
    /** CALCULO ITEM VENDA **/
    public static void calculaProduto(Item_PedidoED ed, boolean calcMargem) throws Exception
    {
        //*** Converte valores em variáveis numéricas
        boolean isPromocao = ed.getOid_Promocao_Produto() > 0;
        boolean isPesagem = new ProdutoBean().isPesagemByProduto(ed.getOID_Produto());
        
        //*** Valida se é Pesagem
        double nrQtAtendido = (isPesagem 
                              ? (ed.getNR_Peso_Real() > 0 
                                 ?  ed.getNR_Peso_Real()
                                 : (ed.getNR_QT_Atendido() * new BancoUtil().getTableDoubleValue("NR_Peso_Medio", "Produtos", "oid_Produto="+JavaUtil.getSQLString(ed.getOID_Produto()))))
                              : ed.getNR_QT_Atendido());
        
        double vlUnitario = ed.getVL_Unitario();
        double vlDesconto = ed.getVL_Desconto();
        double peDesconto = ed.getPE_Desconto();
        double vlPromocao = 0;
        
        if (nrQtAtendido < 0)
        {
            ed.setNR_QT_Atendido(0);
            nrQtAtendido = 0;
        }
        //*** VALIDA SE FOR PROMOÇÃO
        if (isPromocao && nrQtAtendido > 0 && vlUnitario > 0)
        {
            Promocao_ProdutoED edPromo = new Promocao_ProdutoRN().getByRecord(new Promocao_ProdutoED(ed.getOid_Promocao_Produto()));
            double peDesconto_Promocao = edPromo.getPE_Desconto();
            vlPromocao = Valor.round((vlUnitario/100)*peDesconto_Promocao);
            vlUnitario = (vlUnitario - vlPromocao);
        } else vlPromocao = 0;
        
        //*** DESCONTO
        if (peDesconto > 0)
        {
            vlDesconto = Valor.round((vlUnitario/100)*peDesconto);
            vlUnitario = Valor.round(vlUnitario - vlDesconto);
            vlDesconto = Valor.round(vlDesconto*nrQtAtendido);
        } else vlDesconto = 0;
        
        //*** Calcula Valores
        double vlItem = Valor.round(nrQtAtendido * vlUnitario);

        //*** Converte valor das variáveis para o formulário novamente
        ed.setVL_Produto(Valor.round(vlItem + vlDesconto + vlPromocao));
        ed.setVL_Item(Valor.round(vlItem));
        if (calcMargem)
            calculaMargem(ed, false);
    }
    
    /** MARGEM (GORDURA) **/
    public static void calculaMargem(Item_PedidoED ed, boolean isPercentual) throws Exception
    {
        //*** Converte valores em variáveis numéricas 
        double vlUnitario = ed.getVL_Unitario();
        double vlUnitarioTab = ed.getVL_Unitario_Tabela();
        double peMargem = 0;
        if (isPercentual)
            peMargem = ed.getPE_Margem_Contribuicao();
        else peMargem = -(Valor.round(100 - ((vlUnitario*100)/vlUnitarioTab)));
        VendedorBean edVen = VendedorBean.getByOID_Vendedor(ed.getOID_Vendedor());
        double peMax = edVen.getPE_Acrescimo_Maximo();
        double peMin = edVen.getPE_Desconto_Maximo();

        if (!"L".equals(edVen.getDM_Margem()))
        {
            ed.setPE_Margem_Contribuicao(0);
            ed.setVL_Margem_Contribuicao(0);
            ed.setVL_Unitario(vlUnitarioTab);
        } else {
            if (peMargem < (peMin-0.1))
                ed.setPE_Margem_Contribuicao(peMin);
            else if (peMargem > (peMax+0.1))
                ed.setPE_Margem_Contribuicao(peMax);
        
            boolean isPesagem = new ProdutoBean().isPesagemByProduto(ed.getOID_Produto());
            double vlMargem = Valor.round((vlUnitarioTab/100) * peMargem);
            double nrQtAtendido = (isPesagem 
                                  ? (ed.getNR_Peso_Real() > 0 
                                     ?  ed.getNR_Peso_Real()
                                     : (ed.getNR_QT_Atendido() * new BancoUtil().getTableDoubleValue("NR_Peso_Medio", "Produtos", "oid_Produto="+JavaUtil.getSQLString(ed.getOID_Produto()))))
                                  : ed.getNR_QT_Atendido());
            
            ed.setPE_Margem_Contribuicao(peMargem);
            ed.setVL_Margem_Contribuicao(Valor.round(vlMargem * nrQtAtendido));
            ed.setVL_Unitario(Valor.round(vlUnitarioTab + vlMargem));
        }
        calculaProduto(ed, false);
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql != null)
            this.abortaTransacao();
    }
}