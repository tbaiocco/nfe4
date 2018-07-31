package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.master.ed.Forma_PagamentoED;
import com.master.ed.Ocorrencia_PedidoED;
import com.master.ed.PedidoED;
import com.master.ed.RelatorioED;
import com.master.iu.wms005Bean;
import com.master.rl.JasperRL;
import com.master.root.PessoaBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serialData 27/01/2005
 * @JavaBean.class Ocorrencia_PedidoBD
 */
public class Ocorrencia_PedidoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Ocorrencia_PedidoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Ocorrencia_PedidoED inclui(Ocorrencia_PedidoED ed) throws Excecoes {

        String sql = null;

        try {
            if (ed.getOid_Ocorrencia_Pedido() < 1)
                ed.setOid_Ocorrencia_Pedido(getAutoIncremento("oid_Ocorrencia_Pedido", "Ocorrencias_Pedidos"));  
            
            sql = " INSERT INTO Ocorrencias_Pedidos (" +
            	  "		oid_Ocorrencia_Pedido" +
            	  "		,oid_Pedido" +
            	  "		,oid_Pessoa" +
            	  "		,DT_Ocorrencia_Pedido" +
            	  "		,HR_Ocorrencia_Pedido" +
            	  "		,TX_Observacao" +
            	  "		,DM_Motivo) " +
            	  " VALUES (" +
            	  	ed.getOid_Ocorrencia_Pedido() +
            	  	"," + ed.getOid_Pedido() +
            	  	",'" + ed.getOid_Pessoa() + "'" +
            	  	",'" + Data.getDataYMD() + "'" +
            	  	",'" + Data.getHoraHM() + "'" +
            	  	",'" + ed.getTX_Observacao() + "'" +
            	  	",'" + ed.getDM_Motivo() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void altera(Ocorrencia_PedidoED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Ocorrencias_Pedidos SET ";
            sql += " 	 DM_Motivo = '" + ed.getDM_Motivo() + "'";
            sql += " 	,DT_Ocorrencia_Pedido = '" + Data.getDataYMD() + "'";
            sql += " 	,HR_Ocorrencia_Pedido = '" + Data.getHoraHM() + "'";
            sql += " WHERE oid_Ocorrencia_Pedido = " + ed.getOid_Ocorrencia_Pedido();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "altera()");
        }
    }

    public void deleta(Ocorrencia_PedidoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Ocorrencias_Pedidos " +
            	  " WHERE oid_Ocorrencia_Pedido = " + ed.getOid_Ocorrencia_Pedido();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "deleta()");
        }
    }
    
    public ArrayList lista(Ocorrencia_PedidoED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Ocorrencias_Pedidos " +
            	  " WHERE Ocorrencias_Pedidos.oid_Pedido = Pedidos.oid_Pedido " +
            	  "   AND Ocorrencias_Pedidos.oid_Pessoa = Pessoas.oid_Pessoa ";
            if (ed.getOid_Ocorrencia_Pedido() > 0)
                sql +=" AND Ocorrencias_Pedidos.oid_Ocorrencia_Pedido = "+ed.getOid_Ocorrencia_Pedido();
            if (ed.getOid_Pedido() > 0)
                sql +=" AND Ocorrencias_Pedidos.oid_Pedido = "+ed.getOid_Pedido();
            if (doValida(ed.getOid_Pessoa()))
                sql +=" AND Ocorrencias_Pedidos.oid_Pessoa = '"+ed.getOid_Pessoa()+"'";
            if (doValida(ed.edPedido.getOID_Vendedor()))
                sql +=" AND Pedidos.oid_Vendedor = '"+ed.edPedido.getOID_Vendedor()+"'";
            if (doValida(ed.getDM_Motivo()))
                sql +=" AND Ocorrencias_Pedidos.DM_Motivo = '"+ed.getDM_Motivo()+"'";
            if ("F".equals(ed.getDM_Motivo()) && doValida(ed.edPedido.getDM_Critica_Financeira()))
                sql +=" AND Pedidos.DM_Critica_Financeira = '"+ed.edPedido.getDM_Critica_Financeira()+"'";
            if ("E".equals(ed.getDM_Motivo()) && doValida(ed.edPedido.getDM_Critica_Estoque()))
                sql +=" AND Pedidos.DM_Critica_Estoque = '"+ed.edPedido.getDM_Critica_Estoque()+"'";
            
            sql +=" ORDER BY oid_Ocorrencia_Pedido, DM_Motivo";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Ocorrencia_PedidoED edVolta = new Ocorrencia_PedidoED();
          
                edVolta.setOid_Ocorrencia_Pedido(res.getInt("oid_Ocorrencia_Pedido"));
                edVolta.setOid_Pedido(res.getInt("oid_Pedido"));
                //*** Carrega DADOS do PEDIDO
                if (edVolta.getOid_Pedido() > 0)
                    edVolta.edPedido = new PedidoBD(executasql).getByRecord(new PedidoED(edVolta.getOid_Pedido()));
                //*** Carrega DADOS do CLIENTE
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                if (doValida(edVolta.getOid_Pessoa()))
                    edVolta.edPessoa = PessoaBean.getByOID(edVolta.getOid_Pessoa());
                
                edVolta.setDT_Ocorrencia_Pedido(FormataData.formataDataBT(res.getDate("DT_Ocorrencia_Pedido")));
                edVolta.setHR_Ocorrencia_Pedido(res.getString("HR_Ocorrencia_Pedido"));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                edVolta.setDM_Motivo(res.getString("DM_Motivo"));

                lista.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes("Erro ao listar Ocorrencia Pedido ["+exc.getMessage()+"]", exc, 
        		   	this.getClass().getName(), "lista()");
        }
        return lista;
    }

    public Ocorrencia_PedidoED getByRecord(Ocorrencia_PedidoED ed) throws Excecoes {

        Iterator it = this.lista(ed).iterator();
        if (it.hasNext())
            return (Ocorrencia_PedidoED) it.next();
        else return new Ocorrencia_PedidoED();
    }
    
    //*** Verifica Crítica Financeira
    public int verificaFinanceiro(PedidoED edPedido) throws Excecoes {

        String sql = null;
        int oid_Ocorrencia_Pedido = 0,
            oid_Condicao_AVista = 12,
            oldOcorrencia = 0;
        int nrOcorrencias = 0;
        double vlTotalPedidos = 0; 

        try {
            if (!doValida(edPedido.getOID_Pessoa()))
                throw new Excecoes("Cliente não informado!");
            
            sql = " SELECT Pedidos.oid_Pedido" +
                  "       ,Pedidos.oid_Condicao_Pagamento" +
                  "       ,Pedidos.VL_Total_Pedido" +
                  "       ,Clientes.DM_Credito" +
                  "       ,Clientes.DM_Tipo_cobranca" +
                  "       ,Clientes.DT_Desativado" +
                  "       ,Clientes.VL_Minimo_Compra" +
                  "       ,Clientes.VL_Minimo_Fatura" +
                  "       ,Clientes.VL_Maximo_Fatura" +
                  "       ,Clientes.NR_Duplicatas_Vencidas" +
                  "       ,Clientes.VL_Limite_Credito_Adicional" +
            	  " FROM Pedidos" +
            	  " WHERE Clientes.oid_Cliente = Pedidos.oid_Pessoa" +
            	  "   AND Pedidos.oid_Pessoa = Pessoas.oid_Pessoa" +
            	  "   AND Pedidos.DM_Pedido = 'V'" + // Pedido de Venda
            	  "   AND Pedidos.DM_Situacao NOT IN('F','C','A')" + // Q não estejam Finalizados, Cancelados ou Aprovados
            	  "   AND (Pedidos.DM_Critica_Financeira = 'N' OR Pedidos.DM_Critica_Financeira IS NULL)" + // E Crítica ainda não verificada
            	  "   AND Pedidos.oid_Pessoa = '"+edPedido.getOID_Pessoa()+"'";
            if (edPedido.getOID_Pedido() > 0)
                sql +="	AND Pedidos.oid_Pedido = "+edPedido.getOID_Pedido();
            if (doValida(edPedido.getOID_Vendedor()))
          	  	sql +="	AND Pedidos.oid_Vendedor = '"+edPedido.getOID_Vendedor()+"'";
            sql +=" ORDER BY Pedidos.oid_Pessoa";
            
            DuplicataBD DuplicataBD = new DuplicataBD(this.sql); 
            Ocorrencia_PedidoED ed = new Ocorrencia_PedidoED();
            ed.setDM_Motivo("F"); // Financeiro
            ed.setOid_Pessoa(edPedido.getOID_Pessoa());
            
            oid_Ocorrencia_Pedido = getMaximo("oid_Ocorrencia_Pedido", "Ocorrencias_Pedidos","");
            /** ----------- VALOR PEDIDOS ----------- **/
            String wherePedido = "DM_Pedido = 'V' " +
                                 " AND DM_Situacao NOT IN('F','C')" +
                                 " AND oid_Pessoa = '"+edPedido.getOID_Pessoa()+"'";
            if (edPedido.getOID_Pedido() > 0)
                wherePedido += " AND oid_Pedido = "+edPedido.getOID_Pedido();
            vlTotalPedidos = getTableDoubleValue("VL_Total_Pedido", "Pedidos", wherePedido);
            /** ------------------------------------- **/
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                ed.setOid_Pedido(res.getInt("oid_Pedido"));
                /** Cliente DESATIVADO */
                if (doValida(res.getString("DT_Desativado")))
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_DESATIVADO);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                } else
                /** Cliente com Crédito BLOQUEADO */
                if (!"N".equals(res.getString("DM_Credito")))
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_CREDITO_BLOQUEADO);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** Limite de Crédito ESGOTADO */
                double limiteCredito = DuplicataBD.getLimiteCredito(ed.getOid_Pessoa(), Data.manipulaDias(Data.getDataDMY(), -30), Data.getDataDMY()) + res.getDouble("VL_Limite_Credito_Adicional");
                //*** LIBERADO: SO VERIFICA PEDIDOS ACIMA DE 200 REAIS
                //    ISMAEL
                if (vlTotalPedidos > 200 && vlTotalPedidos > limiteCredito)
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_LIMITE_CREDITO+" PEDIDO: R$ "+Valor.round(vlTotalPedidos,2)+" DISPONIVEL: R$ "+Valor.round(limiteCredito,2));
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setTX_Observacao(Ocorrencia_PedidoED.TX_LIMITE_CREDITO+" PEDIDO: R$ "+FormataValor.formataValorBT(vlTotalPedidos,2)+" DISPONIVEL: R$ "+FormataValor.formataValorBT(limiteCredito,2));
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** Nº excedente de Duplicatas em ABERTO */
                if (getTableIntValue("count(*)", 
                                	 "Duplicatas", 
                                	 "(VL_SALDO > 0 OR VL_SALDO <> NULL) " +
                                	 " AND oid_Pessoa = '"+ed.getOid_Pessoa()+"'" +
                                	 " AND DT_Vencimento < '"+Data.getDataYMD()+"'") > res.getInt("NR_Duplicatas_Vencidas")) {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_LIMITE_DUPLICATAS);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** Cliente com Cheque(s) DEVOLVIDO */
                if (doExiste("Cheques_Clientes", " oid_Cliente = '"+ed.getOid_Pessoa()+"' AND DM_Origem = 'P' AND DM_Situacao = 'D'"))
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_CHEQUE_DEVOLVIDO);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** Data da Validade do Cadastro VENCIDA */
                if (doExiste("Clientes", " oid_Cliente = '"+ed.getOid_Pessoa()+"' AND DT_Validade_Cadastro < '"+Data.getDataYMD()+"'"))
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_DT_CADASTRO_VENCIDA);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** Tipo de Cobrança deve ser A VISTA */
                if ("V".equals(res.getString("DM_Tipo_cobranca")) && res.getInt("oid_Condicao_Pagamento") != oid_Condicao_AVista)
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_COBRANCA_AVISTA);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** PRIMEIRA COMPRA deve ser A VISTA */
                if (!doExiste("Duplicatas", " oid_Pessoa = '"+ed.getOid_Pessoa()+"'") && res.getInt("oid_Condicao_Pagamento") != oid_Condicao_AVista)
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_PRIMEIRA_COMPRA);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** Valor inferior ao valor MÍNIMO de compra */
                if (res.getDouble("VL_Total_Pedido") < res.getDouble("VL_Minimo_Compra"))
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_VL_MINIMO_COMPRA);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                    
                /** Valor inferior ao valor MÍNIMO de compra a PRAZO */    
                } else if (res.getInt("oid_Condicao_Pagamento") != oid_Condicao_AVista && res.getDouble("VL_Total_Pedido") < res.getDouble("VL_Minimo_Fatura")) {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_VENDA_AVISTA);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                
                /** Valor superior ao valor MAXIMO para compras */    
                } else if (res.getDouble("VL_Total_Pedido") > res.getDouble("VL_Maximo_Fatura")) {
                    nrOcorrencias++;
                    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_VL_MAXIMO_COMPRA);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                }
                /** TODO VALIDAR OCORRENCIA - Cliente possui Duplicatas PROTESTADAS */    
            	if (false)
                {
            	    nrOcorrencias++;
            	    ed.setTX_Observacao(Ocorrencia_PedidoED.TX_DUPLICATA_PROTESTADA);
            	    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
            	    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
            	}
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "verificaFinanceiro()");
        }
        return nrOcorrencias;
    }
    
    //*** Verifica Crítica Estoque
    public int verificaEstoque(PedidoED edPedido) throws Excecoes {

        String sql = null;
        int oid_Ocorrencia_Pedido = 0, oldOcorrencia = 0;
        int nrOcorrencias = 0;

        try {
            if (!doValida(edPedido.getOID_Pessoa()))
                throw new Excecoes("Cliente não informado para verificar Estoque!");

            sql = " SELECT Pedidos.oid_Pedido" +
                  "       ,Itens_Pedidos.oid_Item_Pedido" +
                  "       ,Itens_Pedidos.oid_Produto" +
                  "       ,Itens_Pedidos.oid_Localizacao" +
                  "       ,Itens_Pedidos.NR_QT_Atendido" +
                  "       ,Itens_Pedidos.NR_Peso_Real" +
                  "       ,Produtos_Clientes.oid_Produto_Cliente" +
                  "       ,Produtos.CD_Produto" +
                  "       ,Unidades_Produtos.DM_Pesagem" +
            	  " FROM Pedidos" +
            	  " WHERE Clientes.oid_Cliente = Pedidos.oid_Pessoa" +
            	  "   AND Pedidos.oid_Pessoa = Pessoas.oid_Pessoa" +
            	  "   AND Pedidos.oid_Pessoa_Distribuidor = Produtos_Clientes.oid_Pessoa" +
            	  "   AND Pedidos.oid_Pedido = Itens_Pedidos.oid_Pedido" +
            	  "   AND Itens_Pedidos.oid_Produto = Produtos_Clientes.oid_Produto" +
            	  "   AND Produtos_Clientes.oid_Produto = Produtos.oid_Produto" +
                  "   AND Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto" +
            	  "   AND Pedidos.DM_Pedido = 'V'" + // Pedido de Venda
            	  "   AND Pedidos.DM_Situacao = 'N'" + // Q não estejam Finalizados, Cancelados ou Aprovados
            	  "   AND Itens_Pedidos.DM_Situacao = 'N'" +
                  "   AND Itens_Pedidos.NR_QT_Atendido > 0" +
            	  "   AND (Pedidos.DM_Critica_Estoque = 'N' OR Pedidos.DM_Critica_Estoque IS NULL)" + // E Crítica ainda não verificada
            	  "   AND Pedidos.oid_Pessoa = '"+edPedido.getOID_Pessoa()+"'";
            if (edPedido.getOID_Pedido() > 0)
                sql +="	AND Pedidos.oid_Pedido = "+edPedido.getOID_Pedido();
            if (doValida(edPedido.getOID_Vendedor()))
          	  	sql +="	AND Pedidos.oid_Vendedor = '"+edPedido.getOID_Vendedor()+"'";
            sql +=" ORDER BY Pedidos.oid_Pessoa, Produtos.CD_Produto";
            
            Ocorrencia_PedidoED ed = new Ocorrencia_PedidoED();
            ed.setDM_Motivo("E"); // Estoque
            ed.setOid_Pessoa(edPedido.getOID_Pessoa());
            
            oid_Ocorrencia_Pedido = super.getMaximo("oid_Ocorrencia_Pedido", "Ocorrencias_Pedidos","");
            
            /**  Se Pedido não possui Itens(Produtos)! */
            ResultSet res = this.executasql.executarConsulta(sql);
            if (!res.first() && edPedido.getOID_Pedido() > 0)
            {
                nrOcorrencias++;
                ed.setTX_Observacao(Ocorrencia_PedidoED.TX_SEM_ITENS);
                if ((oldOcorrencia = this.doExisteOcorrenciaPedido((int)edPedido.getOID_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                    ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                    this.inclui(ed);
                } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
            }
            res.beforeFirst();
            while (res.next())
            {
                ed.setOid_Pedido(res.getInt("oid_Pedido"));
                
                boolean isPesagem = "S".equals("DM_Pesagem");
                double nrQuantidade = isPesagem ? res.getDouble("NR_Peso_Real") : res.getDouble("NR_QT_ATENDIDO");
                
                /** Produto deve ser Pesado em Estoque, Peso Real não informado! */    
                if (isPesagem && nrQuantidade <= 0)
                {
                    nrOcorrencias++;
                    ed.setTX_Observacao(res.getString("CD_Produto")+" - "+Ocorrencia_PedidoED.TX_NECESSITA_PESAGEM);
                    if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                        ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                        this.inclui(ed);
                    } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                } else 
                if (nrQuantidade > 0) {
                    
                    //*** Quantidade em Estoque
                    double qtEstoque = new wms005Bean().getQtdEstoque(res.getString("oid_Produto_Cliente"), null, res.getInt("oid_Localizacao"));
                    boolean possuiEstoque = qtEstoque > 0;
                    double qtDisponivel = 0;
                    
                    //*** Se POSSUI Estoque valida quantidades
                    if (possuiEstoque)
                    {
                        //*** Quantidade Total Atendida do PRODUTO.
                        double qtTotalAtendida = new wms005Bean().getQtdTotalAtendida(null, 
                                                                                      res.getString("oid_Produto"),
                                                                                      null,
                                                                                      res.getInt("oid_Localizacao"),
                                                                                      null);
                        //*** Quantidade Desponivel para Venda
                        qtDisponivel = (qtEstoque - qtTotalAtendida);
                    }
                    
                    /** Produto com Quantidade Insuficiente em Estoque */
                    if (!possuiEstoque || nrQuantidade > qtDisponivel)
                    {
                        nrOcorrencias++;
                        ed.setTX_Observacao(res.getString("CD_Produto")+" - "+Ocorrencia_PedidoED.TX_FALTA_ESTOQUE);
                        if ((oldOcorrencia = this.doExisteOcorrenciaPedido(ed.getOid_Pedido(), ed.getTX_Observacao(), ed.getDM_Motivo())) < 1) {
                            ed.setOid_Ocorrencia_Pedido(++oid_Ocorrencia_Pedido);
                            this.inclui(ed);
                        } else this.altera(new Ocorrencia_PedidoED(oldOcorrencia, ed.getDM_Motivo()));
                        //*** ALTERA QUANTIDADE NO ITEM
                        this.executasql.executarUpdate(" UPDATE Itens_Pedidos SET " +
                                                       "    "+(isPesagem ? "NR_Peso_Real" : "NR_QT_ATENDIDO")+" = "+(qtDisponivel > 0 ? qtDisponivel : 0)+
                                                       " WHERE oid_Item_Pedido = '"+res.getInt("oid_Item_Pedido")+"'");
                    }
                }
            }
        } catch (Excecoes ex) {
            throw ex;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "verificaEstoque()");
        }
        return nrOcorrencias;
    }
    
    //*** Lista Crítica Pedidos
    public ArrayList listaCriticaPedidos(Ocorrencia_PedidoED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        
        try {
            sql = " SELECT DISTINCT " +
                  "      oid_Pedido" +
                  "     ,oid_Pessoa " +
            	  " FROM Ocorrencias_Pedidos" +
            	  " WHERE Pedidos.oid_Pessoa = Ocorrencias_Pedidos.oid_Pessoa" +
            	  "   AND Pedidos.oid_Pedido = Ocorrencias_Pedidos.oid_Pedido" +
            	  "   AND Pedidos.DM_Pedido = 'V'"; // Pedido de Venda
            if (ed.getOid_Pedido() > 0)
                sql +="	AND Pedidos.oid_Pedido = "+ed.getOid_Pedido();
            if (doValida(ed.getOid_Pessoa())) // Cliente
                sql +="	AND Pedidos.oid_Pessoa = '"+ed.getOid_Pessoa()+"'";
            if (doValida(ed.edPedido.getOID_Vendedor()))
          	  	sql +="	AND Pedidos.oid_Vendedor = '"+ed.edPedido.getOID_Vendedor()+"'";
            if (doValida(ed.getDM_Motivo()) && !"T".equals(ed.getDM_Motivo()))
                sql +=" AND Ocorrencias_Pedidos.DM_Motivo = '"+ed.getDM_Motivo()+"'";
            if ("F".equals(ed.getDM_Motivo()) && doValida(ed.edPedido.getDM_Critica_Financeira()) && !"T".equals(ed.edPedido.getDM_Critica_Financeira()))
                sql +=" AND Pedidos.DM_Critica_Financeira = '"+ed.edPedido.getDM_Critica_Financeira()+"'";
            if ("E".equals(ed.getDM_Motivo()) && doValida(ed.edPedido.getDM_Critica_Estoque()) && !"T".equals(ed.edPedido.getDM_Critica_Estoque()))
                sql +=" AND Pedidos.DM_Critica_Estoque = '"+ed.edPedido.getDM_Critica_Estoque()+"'";
            if (doValida(ed.edPedido.getDM_Situacao()) && !"T".equals(ed.edPedido.getDM_Situacao()))
                sql +=" AND Pedidos.DM_Situacao = '"+ed.edPedido.getDM_Situacao()+"'";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Ocorrencia_PedidoED edVolta = new Ocorrencia_PedidoED();
          
                edVolta.setOid_Pedido(res.getInt("oid_Pedido"));
                //*** Carrega DADOS do PEDIDO
                if (edVolta.getOid_Pedido() > 0)
                    edVolta.edPedido = new PedidoBD(executasql).getByRecord(new PedidoED(edVolta.getOid_Pedido()));
                //*** Carrega DADOS do CLIENTE
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                if (doValida(edVolta.getOid_Pessoa()))
                    edVolta.edPessoa = PessoaBean.getByOID(edVolta.getOid_Pessoa());
                
                lista.add(edVolta);
            }
            return lista;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "listaCriticaPedidos()");
        }
    }
    
    private int doExisteOcorrenciaPedido(int oid_Pedido, String TX_Observacao, String DM_Motivo) throws Excecoes {
        
        if (oid_Pedido < 1)
            throw new Excecoes("ID Pedido não informado", this.getClass().getName(), "doExisteOcorrenciaPedido()");
        if (!doValida(TX_Observacao))
            throw new Excecoes("Texto Observação não informado", this.getClass().getName(), "doExisteOcorrenciaPedido()");
        if (!doValida(DM_Motivo))
            throw new Excecoes("Motivo não informado", this.getClass().getName(), "doExisteOcorrenciaPedido()");
        
        return getTableIntValue("oid_Ocorrencia_Pedido",
                				"Ocorrencias_Pedidos",
                			 	" oid_Pedido = "+oid_Pedido +
                			 	" AND TX_Observacao = '"+TX_Observacao+"'" +
                			 	" AND DM_Motivo = '"+DM_Motivo+"'");
    }
    
    /** ------------ RELATÓRIOS ---------------- */
	//*** Ocorrências Pedido
    public void relOcorrenciasPedido(RelatorioED ed) throws Excecoes {

        String sql = null;
        String filter = "FILTRO: "; 
        ArrayList lista = new ArrayList();
        
        try {

            sql = " SELECT * " +
            	  " FROM Ocorrencias_Pedidos " +
            	  " WHERE Ocorrencias_Pedidos.oid_Pedido = Pedidos.oid_Pedido " +
            	  "   AND Ocorrencias_Pedidos.oid_Pessoa = Clientes.oid_Pessoa ";
            //*** Pedido
            if (ed.getNr_pedido() > 0 && ed.getNr_pedido_final() > 0) {
                sql +=" AND Pedidos.NR_Pedido between "+ed.getNr_pedido()+" AND "+ed.getNr_pedido_final();
                filter += "Pedido: "+ed.getNr_pedido()+" a "+ed.getNr_pedido_final()+"  ";
            } else if (ed.getNr_pedido() > 0 || ed.getNr_pedido_final() > 0) {
                sql +=" AND Pedidos.NR_Pedido = "+(ed.getNr_pedido() > 0 ? ed.getNr_pedido() : ed.getNr_pedido_final());
                filter += "Pedido: "+(ed.getNr_pedido() > 0 ? ed.getNr_pedido() : ed.getNr_pedido_final())+"  ";
            }   
            //*** Vendedor
            if (doValida(ed.getCd_vendedor()) && doValida(ed.getCd_vendedor_final())) {
                sql +=" AND Pedidos.oid_Vendedor = Vendedores.oid_Vendedor" +
                	  " AND Vendedores.CD_Vendedor between '"+ed.getCd_vendedor()+"' AND '"+ed.getCd_vendedor_final()+"'";
                filter += "Vendedor: "+ed.getCd_vendedor()+" a "+ed.getCd_vendedor_final()+"  ";
            } else if (doValida(ed.getCd_vendedor()) || doValida(ed.getCd_vendedor_final())) {
                sql +=" AND Pedidos.oid_Vendedor = Vendedores.oid_Vendedor" +
          	  		  " AND Vendedores.CD_Vendedor = '"+(doValida(ed.getCd_vendedor()) ? ed.getCd_vendedor() : ed.getCd_vendedor_final())+"'";
                filter += "Vendedor: "+(doValida(ed.getCd_vendedor()) ? ed.getCd_vendedor() : ed.getCd_vendedor_final())+"  ";
            }
            if (ed.getOid_entregador() > 0)
                sql +=" AND Pedidos.oid_Entregador = "+ed.getOid_entregador();
            //*** Cliente
            if (doValida(ed.getOid_pessoa()))
                sql +=" AND Ocorrencias_Pedidos.oid_Pessoa = '"+ed.getOid_pessoa()+"'";
            //*** Tipo Pedido
            if (ed.getOid_tipo_pedido() > 0)
                sql +=" AND Pedidos.oid_Tipo_Pedido = "+ed.getOid_tipo_pedido(); 
            //*** Data de Emissão
            if (doValida(ed.getDt_emissao_inicial()) && doValida(ed.getDt_emissao_final())) {
                sql +=" AND Pedidos.DT_Pedido between '"+ed.getDt_emissao_inicial()+"' AND '"+ed.getDt_emissao_final()+"'";
                filter += "Emissão: "+ed.getDt_emissao_inicial()+" a "+ed.getDt_emissao_final()+"  ";
            } else if (doValida(ed.getDt_emissao_inicial()) || doValida(ed.getDt_emissao_final())) {
                sql +=" AND Pedidos.DT_Pedido = '"+(doValida(ed.getDt_emissao_inicial()) ? ed.getDt_emissao_inicial(): ed.getDt_emissao_final())+"'";
                filter += "Emissão: "+(doValida(ed.getDt_emissao_inicial()) ? ed.getDt_emissao_inicial(): ed.getDt_emissao_final())+"  ";
            }
            //*** Data de Entrega
            if (doValida(ed.getDt_entrega_inicial()) && doValida(ed.getDt_entrega_final())) {
                sql +=" AND Pedidos.DT_Previsao_Entrega between '"+ed.getDt_entrega_inicial()+"' AND '"+ed.getDt_entrega_final()+"'";
                filter += "Entrega: "+ed.getDt_entrega_inicial()+" a "+ed.getDt_entrega_final()+"  ";
            } else if (doValida(ed.getDt_entrega_inicial()) || doValida(ed.getDt_entrega_final())) {
                sql +=" AND Pedidos.DT_Previsao_Entrega = '"+(doValida(ed.getDt_entrega_inicial()) ? ed.getDt_entrega_inicial() : ed.getDt_entrega_final())+"'";
                filter += "Entrega: "+(doValida(ed.getDt_entrega_inicial()) ? ed.getDt_entrega_inicial(): ed.getDt_entrega_final())+"  ";
            }
            if (doValida(ed.getDm_critica_financeira()))
            {
                if ("X".equals(ed.getDm_critica_financeira()))
                    sql +=" AND Pedidos.DM_Critica_Financeira IN ('A','L')";
                else sql +=" AND Pedidos.DM_Critica_Financeira = '"+ed.getDm_critica_financeira()+"'";
            }
            if (doValida(ed.getDm_critica_estoque()))
            {
                if ("X".equals(ed.getDm_critica_estoque()))
                    sql +=" AND Pedidos.DM_Critica_Estoque :N ('A','L')";
                else sql +=" AND Pedidos.DM_Critica_Estoque = '"+ed.getDm_critica_estoque()+"'";
            }
            //*** MOTIVO
            if (doValida(ed.getDm_motivo()) && !"T".equals(ed.getDm_motivo())) {
                sql +=" AND Ocorrencias_Pedidos.DM_Motivo = '"+ed.getDm_motivo()+"'";
                filter += "Tipo: "+(new Ocorrencia_PedidoED(ed.getDm_motivo()).getDescDM_Motivo()); 
            }
            sql +=" ORDER BY Pedidos.oid_Vendedor, Pedidos.NR_Pedido";
            
            //*** Seta Filtro
            if (!"FILTRO: ".equals(filter)) {
                HashMap map = new HashMap();
                map.put("FILTER", filter);
                ed.setHashMap(map);
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                //*** Carrega DADOS do PEDIDO
                PedidoED edPedido = new PedidoED();
                edPedido.setOID_Vendedor(getTableStringValue("oid_Vendedor", "Pedidos", "oid_Pedido = '"+res.getInt("oid_Pedido")+"'"));
                edPedido.setNR_Pedido(getTableIntValue("NR_Pedido", "Pedidos", "oid_Pedido = '"+res.getInt("oid_Pedido")+"'"));
                edPedido.setDT_Entrega(FormataData.formataDataBT(getTableStringValue("DT_Previsao_Entrega", "Pedidos", "oid_Pedido = '"+res.getInt("oid_Pedido")+"'")));
                edPedido.setNM_Condicao_Pagamento(getTableStringValue("NM_Condicao_Pagamento", "Condicoes_Pagamentos", "Condicoes_Pagamentos.oid_Condicao_Pagamento = Pedidos.oid_Condicao_Pagamento" +
                                                                                                                       " AND Pedidos.oid_Pedido = '"+res.getInt("oid_Pedido")+"'"));
                edPedido.setDM_Meio_Pagamento(Forma_PagamentoED.getDescDM_Tipo_Pagamento(getTableStringValue("DM_Meio_Pagamento", "Pedidos", "oid_Pedido = '"+res.getInt("oid_Pedido")+"'")));
                
                RelatorioED edRel = new RelatorioED();
                edRel.setOid_ocorrencia_pedido(res.getInt("oid_Ocorrencia_Pedido"));
                edRel.setCd_vendedor(getTableStringValue("CD_Vendedor", "Vendedores", "oid_Vendedor = '"+edPedido.getOID_Vendedor()+"'"));
                edRel.setNr_pedido((int)edPedido.getNR_Pedido());
                String codigo = getTableStringValue("CD_Cliente_Palm", "Clientes", "oid_Cliente = '"+res.getString("oid_Pessoa")+"'");
                if (doValida(codigo))
                    codigo = JavaUtil.formatar(codigo, "#####-#");
                else codigo = JavaUtil.formataCNPJ_CPF(getTableStringValue("NR_CNPJ_CPF", "Pessoas", "oid_Pessoa = '"+res.getString("oid_Pessoa")+"'"));
                edRel.setNr_cnpj_cpf(codigo);
                edRel.setNm_razao_social(getTableStringValue("NM_Razao_Social", "Pessoas", "oid_Pessoa = '"+res.getString("oid_Pessoa")+"'"));                
                edRel.setDt_entrega(edPedido.getDT_Entrega());
                edRel.setNm_condicao_pagamento(edPedido.getNM_Condicao_Pagamento()+" / "+edPedido.getDM_Meio_Pagamento());
                
                edRel.setDt_ocorrencia(FormataData.formataDataBT(res.getDate("DT_Ocorrencia_Pedido")));
                edRel.setHr_ocorrencia(res.getString("HR_Ocorrencia_Pedido"));
                edRel.setTx_observacao(res.getString("TX_Observacao"));
                //*** Quebra por Motivo
                edRel.setDm_motivo(new Ocorrencia_PedidoED(res.getString("DM_Motivo")).getDescDM_Motivo());
                
                lista.add(edRel);
            }
            ed.setLista(lista);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "relOcorrenciasPedido()");
        }
        //*** Chama o Gerador de Relatórios Jasper
        new JasperRL(ed).geraRelatorio();
    }
}