package com.master.rn;

/**
 * @author Andr� Valadas
 */
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import com.master.bd.Ocorrencia_PedidoBD;
import com.master.bd.PedidoBD;
import com.master.ed.PedidoED;
import com.master.ed.RelatorioED;
import com.master.rl.PedidoRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class PedidoRN extends Transacao {

    public PedidoRN() {
    }

    public PedidoED inclui(PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new PedidoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void altera(PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new PedidoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void deleta(PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new PedidoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public PedidoED getByRecord(PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new PedidoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    //*** VERIFICA CRITICAS FINANCEIRAS
    public int verificaCriticaFinanceira(PedidoED ed) throws Exception {

        try {
	        this.inicioTransacao();
	        int nrFinanceiro = 0;
	        //*** Situa�ao deve ser N="N�o verificada"
	        //*** Financeiro
	        if ("N".equals(ed.getDM_Critica_Financeira()))
            {
	            //*** Retorna N� de Ocorrencias Geradas
	            nrFinanceiro = new Ocorrencia_PedidoBD(this.sql).verificaFinanceiro(ed);
	            //*** Atualiza Cr�tica Financeira do Pedido
	            ed.setDM_Critica_Financeira(nrFinanceiro > 0 ? "S" : "A");
	            new PedidoBD(this.sql).atualizaCriticaFinanceira(ed.getOID_Pedido(), ed.getDM_Critica_Financeira());
	        }
	        this.fimTransacao(true);
	        return nrFinanceiro;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    //*** APROVA��O DO PEDIDO DE VENDA
    public int setAprovacaoPedidoVenda(PedidoED ed) throws Exception {

        try {
            this.inicioTransacao();
            boolean isVendaDireta = ed.getOID_Tipo_Pedido() == 3;
            int nrFinanceiro = 0;
            int nrEstoque = 0;
            //*** Situa�ao deve ser N="N�o verificada"
            /** FINANCEIRO **/
            if ("N".equals(ed.getDM_Critica_Financeira()))
            {
                //*** Retorna N� de Ocorrencias Geradas
                nrFinanceiro = new Ocorrencia_PedidoBD(this.sql).verificaFinanceiro(ed);
                //*** Atualiza Cr�tica Financeira do Pedido
                ed.setDM_Critica_Financeira(nrFinanceiro > 0 ? "S" : "A");
                new PedidoBD(this.sql).atualizaCriticaFinanceira(ed.getOID_Pedido(), ed.getDM_Critica_Financeira());
            }
            /** ESTOQUE **/
            if ("N".equals(ed.getDM_Critica_Estoque()))
            {
                //*** Retorna N� de Ocorrencias Geradas
                nrEstoque = new Ocorrencia_PedidoBD(this.sql).verificaEstoque(ed);
                //*** Atualiza Cr�tica Estoque do Pedido
                ed.setDM_Critica_Estoque(nrEstoque > 0 ? "S" : "A");
                new PedidoBD(this.sql).atualizaCriticaEstoque(ed.getOID_Pedido(), ed.getDM_Critica_Estoque());
            }
            /** APROVA��O **/
            if (!isVendaDireta && ("A".equals(ed.getDM_Critica_Financeira()) || "L".equals(ed.getDM_Critica_Financeira())) && ("A".equals(ed.getDM_Critica_Estoque()) || "L".equals(ed.getDM_Critica_Estoque())))
            {
                //*** Exige Entregador
                if (ed.getOid_Entregador() < 1)
                    throw new Mensagens("Informe o Entregador antes de Finalizar o Pedido!");
                //*** Gera NF, Itens_NF, Duplicatas(Parcelas Contas a Receber), Seta Produto como Aprovado!
                new PedidoBD(this.sql).setAprovacaoPedidoVenda(ed);
            }
            this.fimTransacao(true);
            return (nrFinanceiro + nrEstoque);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    //*** por LISTA
    public void aprovacaoByEntregadorPedido(ArrayList lista, String oid_Entregador, String oid_Veiculo, String update) throws Exception {

        try {
            if ("true".equals(update))
                this.atualizaEntregadorPedido(lista, Integer.parseInt(oid_Entregador), oid_Veiculo);
            
            int nrOcorrencias = 0, nrOcorrencias_OLD = 0;
            int naoAprovados = 0, jaCriticado = 0;
            //*** Aprova Pedidos de Venda
            for (int i = 0; i < lista.size(); i++)
            {
                PedidoED ed = (PedidoED) lista.get(i);
                if (JavaUtil.doValida(oid_Entregador))
                    ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
                //*** Se ja existem criticas n�o verifica
                if (!"S".equals(ed.getDM_Critica_Financeira()) && !"S".equals(ed.getDM_Critica_Estoque()))
                    nrOcorrencias += this.setAprovacaoPedidoVenda(ed);
                else ++jaCriticado;
                
                //*** Se foram adicionadas Ocorrencias, ent�o pedido n�o foi Aprovado
                if (nrOcorrencias > nrOcorrencias_OLD)
                    ++naoAprovados;
                nrOcorrencias_OLD = nrOcorrencias;
            }
            /** Lan�a mensagem na tela para usu�rio **/ 
            if (naoAprovados > 0 || jaCriticado > 0)
            {
                String error = "ATEN��O: ";
                if (naoAprovados > 0)
                {
                    if (naoAprovados != lista.size())
                        error += "De ["+lista.size()+"] Pedido(s) ["+naoAprovados+"] N�O foram aprovados devido a Ocorr�ncias encontradas! ";
                    else error += "NENHUM dos Pedidos listados n�o foram aprovados devido a Ocorr�ncias encontradas! ";
                }
                if (jaCriticado > 0)
                {
                    if (jaCriticado != lista.size())
                        error += "["+jaCriticado+"] dos Pedidos j� POSSUIAM Criticas! ";
                    else error += "Os Pedidos listados n�o foram verificados pois TODOS j� possuiam Criticas! ";
                }
                if (nrOcorrencias > 0)
                    error += "TOTAL: ["+nrOcorrencias+"] Ocorr�ncias!";
                throw new Mensagens(error);
            }
        } catch(Mensagens e) {
            this.abortaTransacao();
            throw e;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    //*** Atualiza SITUACAO do Pedido
    public void atualizaSituacaoPedido(long oid_Pedido, String DM_Situacao) throws Exception {

        try {
	        this.inicioTransacao();
	        new PedidoBD(this.sql).atualizaSituacaoPedido(oid_Pedido, DM_Situacao);
	        if ("C".equals(DM_Situacao))
	            new PedidoBD(this.sql).total_Pedido(oid_Pedido);
	        this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    //*** Libera Pedidos Bloqueados
    public void liberaPedido(PedidoED ed) throws Exception {

        try {
            if (ed.getNR_Pedido() < 1)
                throw new Mensagens("Pedido n�o informado!");
            if (ed.getOID_Unidade() < 1)
                throw new Mensagens("Unidade n�o informada para libera��o!");
            if (!JavaUtil.doValida(ed.getOID_Vendedor()))
                throw new Mensagens("Vendedor n�o informada para libera��o!");
            if (!JavaUtil.doValida(ed.getDT_Entrega()))
                throw new Mensagens("Data de Entrega n�o informada para libera��o!");
            if (!JavaUtil.doValida(ed.getDM_Filtrar_ACF()))//*** Tipo de Critica a ser Liberada(F ou E)
                throw new Mensagens("Tipo de Cr�tica n�o informada para libera��o!");
            if (!JavaUtil.doValida(ed.getDM_Pedido()))
                throw new Mensagens("Tipo de Pedido n�o informado para libera��o!");
            
            this.inicioTransacao();
            long oid_Pedido = new BancoUtil().getTableIntValue("oid_Pedido", "Pedidos", 
                                                               " DM_Situacao = 'N'" +
                                                               " AND DM_Pedido = "+JavaUtil.getSQLString(ed.getDM_Pedido())+
                                                               " AND DM_Critica_"+("F".equals(ed.getDM_Filtrar_ACF()) ? "Financeira" : "Estoque")+" = 'S'" +
                                                               " AND NR_Pedido="+ed.getNR_Pedido()+
                                                               " AND oid_Unidade="+ed.getOID_Unidade()+
                                                               " AND oid_Vendedor="+JavaUtil.getSQLString(ed.getOID_Vendedor())+
                                                               " AND DT_Previsao_Entrega="+JavaUtil.getSQLDate(ed.getDT_Entrega()));
            if (oid_Pedido < 1)
                throw new Mensagens("Pedido n�o encontrado para Libera��o!");
            if ("F".equals(ed.getDM_Filtrar_ACF()))
                new PedidoBD(this.sql).atualizaCriticaFinanceira(oid_Pedido, "L");
            else new PedidoBD(this.sql).atualizaCriticaEstoque(oid_Pedido, "L");
            
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    //*** Atualiza a Critica Financeira do Pedido (A=Aprovado , N=N�o Verificada, S=Cr�dito Bloqueado, L=Estoque Liberado)
    public void atualizaCriticaFinanceira(long oid_Pedido, String DM_Critica_Financeira) throws Excecoes {

        try {
	        this.inicioTransacao();
	        new PedidoBD(this.sql).atualizaCriticaFinanceira(oid_Pedido, DM_Critica_Financeira);
	        this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    //*** Atualiza a Critica Estoque do Pedido (A=Aprovada , N=N�o Verificada, S=Cr�dito Bloqueado, L=Cr�dito Liberado)
    public void atualizaCriticaEstoque(long oid_Pedido, String DM_Critica_Estoque) throws Excecoes {

        try {
	        this.inicioTransacao();
	        new PedidoBD(this.sql).atualizaCriticaEstoque(oid_Pedido, DM_Critica_Estoque);
	        this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    //*** Atualiza Entregador do Pedido
    public void atualizaEntregadorPedido(ArrayList lista, int oid_Entregador, String oid_Veiculo) throws Excecoes {

        try {
	        if (lista.size() < 1)
	            throw new Mensagens("Lista de Pedidos vazia! Execute novamente a consulta!");
	        
	        this.inicioTransacao();
	        PedidoBD PedidoBD = new PedidoBD(this.sql);
	        for (int i=0; i < lista.size(); i++) {
	            PedidoBD.atualizaEntregadorPedido(((PedidoED) lista.get(i)).getOID_Pedido(), oid_Entregador, oid_Veiculo);
	        }
	        this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    
    public ArrayList lista(PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new PedidoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    /** ------------ RELAT�RIOS ---------------- */
    public void relNecessidadeCompra(HttpServletResponse response, RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relNecessidadeCompra(response, ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    //*** Produtos por Entregador
    public void relProdutosEntregador(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relProdutosEntregador(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    //*** Produtos p/ Pesar(Pedido de Venda)
    public void relProdutosPesar(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relProdutosPesar(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    //*** Produtos n�o Vendidos/Sem Estoque(Pedido de Venda)
    public void relFaltaEstoque(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relFaltaEstoque(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    //*** Pedido de Compra e Venda
    public void relPedidoCompraVenda(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relPedidoCompraVenda(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    /** Pedido de Compra e Venda MATRICIAL **/
    public String printPedidos(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            return new PedidoRL(this.sql).printPedidos(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    //*** Trocas Clientes
    public void relTrocasClientes(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relTrocasClientes(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    //*** Margem de Contribui��o do Vendedor
    public void relResumoGorduras(RelatorioED ed) throws Exception {
     
        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relResumoGorduras(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
        
    //*** Relat�rio geral para COMPRA
    public void relListagemGeral(RelatorioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new PedidoRL(this.sql).relListagemGeral(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}