package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Documento_EntregaED;
import com.master.ed.EntregaED;
import com.master.ed.Movimento_DuplicataED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Origem_DuplicataED;
import com.master.ed.Parcelamento_FinanceiroED;
import com.master.rn.Movimento_DuplicataRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author André Valadas
 * @serial Documentos Entregas
 * @serialData 31/03/2005
 */
public class Documento_EntregaBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Documento_EntregaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Documento_EntregaED inclui(Documento_EntregaED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Documento_Entrega(getAutoIncremento("oid_Documento_Entrega", "Documentos_Entregas"));

            sql = " INSERT INTO Documentos_Entregas (" +
            	  "		 oid_Documento_Entrega" +
            	  "		,oid_Entrega" +
            	  "		,oid_Nota_Fiscal" +
                  "     ,DM_Forma_Recebimento" +
            	  "		,DM_Situacao) " +
            	  " VALUES (" +
            	  	ed.getOid_Documento_Entrega() +
            	  	"," + ed.getOid_Entrega() +
            	  	",'" + ed.getOid_Nota_Fiscal() + "'" +
                    ",'" + ed.getDM_Forma_Recebimento() + "'" +
            	  	",'" + ed.getDM_Situacao() + "')";

            executasql.executarUpdate(sql);
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Documento_EntregaED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Documentos_Entregas SET ";
            sql += " 	 DM_Situacao = '"+ed.getDM_Situacao()+"'";
            if (doValida(ed.getDM_Forma_Recebimento()))
                sql += "    ,DM_Forma_Recebimento = '"+ed.getDM_Forma_Recebimento()+"'";
            sql += " 	,TX_Observacao = '"+ed.getTX_Observacao()+"'";
            sql += " WHERE oid_Documento_Entrega = "+ed.getOid_Documento_Entrega();

            //*** Calcula Valor dos Documentos DEVOLVIDOS ou CANCELADOS
            this.calculaDevCanc(ed);
            //*** ALTERA Documento_Entrega
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Documento_EntregaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Documentos_Entregas " +
            	  " WHERE oid_Documento_Entrega = " + ed.getOid_Documento_Entrega();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }

    public ArrayList lista(Documento_EntregaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Documentos_Entregas " +
            	  " WHERE Documentos_Entregas.oid_Entrega = Entregas.oid_Entrega" +
            	  "   AND Documentos_Entregas.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal";

            if (ed.getOid_Documento_Entrega() > 0)
                sql += "   AND Documentos_Entregas.oid_Documento_Entrega = "+ed.getOid_Documento_Entrega();
            if (ed.getOid_Entrega() > 0)
                sql += "   AND Documentos_Entregas.oid_Entrega = "+ed.getOid_Entrega();
            if (doValida(ed.getOid_Nota_Fiscal()))
                sql += "   AND Documentos_Entregas.oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"'";
            if (ed.edNota.getNr_nota_fiscal() > 0)
                sql += "   AND Notas_Fiscais.NR_Nota_Fiscal = "+ed.edNota.getNr_nota_fiscal();
            if (doValida(ed.getDM_Situacao()))
                sql += "   AND Documentos_Entregas.DM_Situacao = '"+ed.getDM_Situacao()+"'";
            if (doValida(ed.getNotInDM_Situacao()))
                sql += "   AND Documentos_Entregas.DM_Situacao NOT IN("+ed.getNotInDM_Situacao()+")";
            if (doValida(ed.getDM_Forma_Recebimento()))
                sql += "   AND Documentos_Entregas.DM_Forma_Recebimento = '"+ed.getDM_Forma_Recebimento()+"'";
            if (doValida(ed.edEntrega.getDM_Situacao()))
                sql += "   AND Entregas.DM_Situacao = '"+ed.edEntrega.getDM_Situacao()+"'";
            //*** Data da Entrega
            if (doValida(ed.edEntrega.getDT_Entrega()) && doValida(ed.edEntrega.getDT_Entrega_Final())) {
                sql +="    AND Entregas.DT_Entrega BETWEEN '"+ed.edEntrega.getDT_Entrega()+"' AND '"+ed.edEntrega.getDT_Entrega_Final()+"'";
            } else if (doValida(ed.edEntrega.getDT_Entrega()) || doValida(ed.edEntrega.getDT_Entrega_Final())) {
                sql +="    AND Entregas.DT_Entrega = '"+(doValida(ed.edEntrega.getDT_Entrega()) ? ed.edEntrega.getDT_Entrega() : ed.edEntrega.getDT_Entrega_Final())+"'";
            }

            //*** Data da Acerto
            if (doValida(ed.edEntrega.getDT_Acerto()) && doValida(ed.edEntrega.getDT_Acerto_Final())) {
                sql +="    AND Entregas.DT_Acerto BETWEEN '"+ed.edEntrega.getDT_Acerto()+"' AND '"+ed.edEntrega.getDT_Acerto_Final()+"'";
            } else if (doValida(ed.edEntrega.getDT_Entrega()) || doValida(ed.edEntrega.getDT_Entrega_Final())) {
                sql +="    AND Entregas.DT_Acerto = '"+(doValida(ed.edEntrega.getDT_Acerto()) ? ed.edEntrega.getDT_Acerto() : ed.edEntrega.getDT_Acerto_Final())+"'";
            }
            sql += " ORDER BY Entregas.oid_Entregador, Entregas.NR_Entrega, Notas_Fiscais.NR_Nota_Fiscal";
            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                Documento_EntregaED edVolta = new Documento_EntregaED();

                edVolta.setOid_Documento_Entrega(res.getInt("oid_Documento_Entrega"));
                edVolta.setOid_Entrega(res.getInt("oid_Entrega"));
                edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                //*** Carrega Dados
                if (edVolta.getOid_Entrega() > 0)
                    edVolta.edEntrega = new EntregaBD(executasql).getByRecord(new EntregaED(edVolta.getOid_Entrega(), true, true, true));
                if (doValida(edVolta.getOid_Nota_Fiscal()))
                    edVolta.edNota = new Nota_Fiscal_EletronicaBD(executasql).getByRecord(new Nota_Fiscal_EletronicaED(edVolta.getOid_Nota_Fiscal()));

                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDM_Forma_Recebimento(res.getString("DM_Forma_Recebimento"));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
        return list;
    }

    public Documento_EntregaED getByRecord(Documento_EntregaED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Documento_EntregaED) iterator.next();
        } else return new Documento_EntregaED();
    }

    public void geraDocumentosEntrega(EntregaED ed) throws Excecoes {

        if (ed.getOid_Entrega() < 1)
            throw new Excecoes("Entrega não Informada!");
        if (ed.getOid_Entregador() < 1)
            throw new Excecoes("Entregador não Informado!");
        if (!doValida(ed.getDT_Entrega()))
            throw new Excecoes("Data da Entrega não informada!");

        String sql = null;
        try {

            sql = " SELECT Notas_Fiscais.oid_Nota_Fiscal" +
                  "     ,Notas_Fiscais.DM_Situacao" +
            	  "		,Notas_Fiscais.DM_Forma_Pagamento" +
            	  "	  	,Notas_Fiscais.VL_Liquido_Nota_Fiscal" +
            	  "	  	,Condicoes_Pagamentos.cd_condicao_pagamento" +
            	  "	  	,Condicoes_Pagamentos.nm_condicao_pagamento" +
            	  "		,Formas_Pagamentos.DM_Tipo_Cobranca" +
            	  " FROM Notas_Fiscais " +
            	  "		 LEFT JOIN Documentos_Entregas" +
            	  "			ON Notas_Fiscais.oid_Nota_Fiscal = Documentos_Entregas.oid_Nota_Fiscal" +
                  "     ,Condicoes_Pagamentos" +
                  "     ,Formas_Pagamentos" +
            	  " WHERE (Notas_Fiscais.oid_Nota_Fiscal <> Documentos_Entregas.oid_Nota_Fiscal OR Documentos_Entregas.oid_Nota_Fiscal is null)" +
            	  "	  AND Notas_Fiscais.oid_entregador = Entregadores.oid_entregador" +
            	  "   AND Entregadores.oid_pessoa = Pessoas.oid_pessoa" +
            	  "   AND Notas_Fiscais.oid_condicao_pagamento = Condicoes_Pagamentos.oid_condicao_pagamento" +
            	  "   AND Condicoes_Pagamentos.oid_condicao_pagamento = Formas_Pagamentos.oid_condicao_pagamento " +
            	  "   AND Formas_Pagamentos.DM_Tipo_Pagamento = Notas_Fiscais.DM_Forma_Pagamento " +
            	  "   AND Notas_Fiscais.DM_Tipo_Nota_Fiscal IN ('S','D')" +
            	  "   AND Notas_Fiscais.DM_Situacao IN ('F','D')" +
            	  "   AND Notas_Fiscais.oid_Entregador = "+ed.getOid_Entregador()+
                  "   AND Notas_Fiscais.DT_Entrega = '"+ed.getDT_Entrega()+"'";

            ResultSet res = this.executasql.executarConsulta(sql);

            //*** Variaveis Auxiliares
            double vlVendas = 0,
            	   vlCobranca = 0,
                   vlDevolucao = 0;
            while (res.next())
            {
                Documento_EntregaED edDocEntrega = new Documento_EntregaED();
                edDocEntrega.setOid_Entrega(ed.getOid_Entrega());
                edDocEntrega.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edDocEntrega.setDM_Forma_Recebimento(res.getString("DM_Forma_Pagamento"));
                edDocEntrega.setDM_Situacao("D".equals(res.getString("DM_Situacao")) ? "D" : "N"); //Em Rota ou Devolvido

                //*** Valor Vendas
                vlVendas += res.getDouble("VL_Liquido_Nota_Fiscal");
                //*** Valor COBRADO NA ENTREGA
                // 	  Forma de Pagamento == DM_Tipo_Cobranca = 'E'
                //    Situacao == 'D' eh abatido como VL_Devolvido
                if ("E".equals(res.getString("DM_Tipo_Cobranca")))
                {
                    vlCobranca += res.getDouble("VL_Liquido_Nota_Fiscal");
                    if ("D".equals(res.getString("DM_situacao")))
                        vlDevolucao += res.getDouble("VL_Liquido_Nota_Fiscal");
                }
                this.inclui(edDocEntrega);
            }
            //*** Atualiza Valores de Entrega
            if (res.first()) {
                ed.setVL_Vendas(vlVendas);
                ed.setVL_Cobranca(vlCobranca);
                ed.setVL_Devolucao_Cancelamento(vlDevolucao);
                ed.setVL_Saldo(vlCobranca);
                new EntregaBD(executasql).altera(ed);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraDocumentosEntrega()");
        }
    }

    //*** Seta Documentos EM ROTA como ENTREGUE e Liquida Documento em DINHEIRO e CHEQUES
    public void finalizaDocumentos(Documento_EntregaED ed) throws Excecoes {

        String sql = null;
        double vlLiquidar = 0;
        double vlCheques = 0;
        try {
            if (ed.getOid_Entrega() < 1)
                throw new Excecoes("Entrega não informada!");

            ed.setNotInDM_Situacao("'D','C'");
            ArrayList lista = this.lista(ed);
            for (int i=0; i<lista.size(); i++)
            {
                Documento_EntregaED edDoc = (Documento_EntregaED) lista.get(i);
                //*** Liquida se Recebimento do tipo CHEQUE ou DINHEIRO
                if ("2".equals(edDoc.getDM_Forma_Recebimento()) || "5".equals(edDoc.getDM_Forma_Recebimento()))
                {
                    //*** VERIFICA DE DEVE MOVIMENTAR FIANCEIRO
                    if ("S".equals(edDoc.edNota.edModelo.getDM_Movimenta_Financeiro()))
                    {
                        //*** Movimentos Duplicatas - [PARCELAS]
                        Parcelamento_FinanceiroED edParcela = new Parcelamento_FinanceiroED();
                        edParcela.setOID_Nota_Fiscal(edDoc.edNota.getOid_nota_fiscal());

                        //*** SE FORMA de RECEBIMENTO TIPO CHEQUE, liquidar somente os valor informado pelos cheques
                        if ("2".equals(edDoc.getDM_Forma_Recebimento()))
                        {
                            vlCheques = getTableDoubleValue("sum(VL_Cheque)",
                                                            "Cheques_Clientes",
                                                            "oid_Entrega = "+edDoc.getOid_Entrega()+
                                                            " AND oid_Nota_Fiscal = '"+edDoc.getOid_Nota_Fiscal()+"'" +
                                                            " AND DM_Situacao NOT IN("+ed.getNotInDM_Situacao()+")");
                        }

                        ArrayList listaParcelas = new Parcelamento_FinanceiroBD(this.sql).lista(edParcela);
                        for (int x=0; x < listaParcelas.size(); x++)
                        {
                            //*** Se tipo Cheque e não há mais valor informado nos CHEQUES a liquidar, cai fora.
                            if ("2".equals(edDoc.getDM_Forma_Recebimento()) && vlCheques <= 0)
                                break;
                            Parcelamento_FinanceiroED edParc = (Parcelamento_FinanceiroED) listaParcelas.get(x);
                            Origem_DuplicataED edDuplicataOrig = new Origem_DuplicataBD(this.sql).getByParcelamento(new Origem_DuplicataED((int)edParc.getOID_Parcelamento(), edParc.getOID_Nota_Fiscal()));
                            //*** Se DUPLICATA Ja EXCLUIDA DO SISTEMA cai fora.
                            if (!doValida(edDuplicataOrig.getOID_Duplicata()))
                                break;

                            Movimento_DuplicataED edMov = new Movimento_DuplicataED();
                            edMov.setDM_Debito_Credito("C");
                            edMov.setDM_Tipo_Lancamento("L");
                            edMov.setDM_Tipo_Pagamento("T");
                            edMov.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Liquidacao_Cobranca()));
                            edMov.setOid_Entrega(edDoc.getOid_Entrega());
                            edMov.setOid_Duplicata(new Long(edDuplicataOrig.getOID_Duplicata()).longValue());
                            vlLiquidar = 0;
                            //*** Verifica se deve liquidar o valor TOTAL ou PARCIAL da parcela ou
                            if ("2".equals(edDoc.getDM_Forma_Recebimento()))
                            {
                                if (vlCheques >= edParc.getVL_Parcela())
                                    vlLiquidar = edParc.getVL_Parcela();
                                else {
                                    vlLiquidar = (edParc.getVL_Parcela() - (edParc.getVL_Parcela() - vlCheques));
                                    edMov.setDM_Tipo_Pagamento("P");
                                }
                                vlCheques -= vlLiquidar;
                            } else vlLiquidar = edParc.getVL_Parcela();

                            edMov.setVL_Pago(vlLiquidar);
                            edMov.setOID_Carteira(new Integer(edDuplicataOrig.getOid_Carteira()));
                            edMov.setOid_Conta_Corrente(getTableStringValue("oid_Conta_Corrente","Carteiras","oid_Carteira="+edMov.getOID_Carteira()));
                            edMov.setOid_Conta(new Integer(getTableIntValue("oid_Conta","Contas_Correntes","oid_Conta_Corrente="+getSQLString(edMov.getOid_Conta_Corrente()))));
                            edMov.setDT_Movimento(Data.getDataDMY());
                            new Movimento_DuplicataRN().inclui(edMov);
                        }
                    }
                }
            }
            sql = " UPDATE Documentos_Entregas SET " +
            	  " 	DM_Situacao = 'S'" +
            	  " WHERE DM_Situacao = 'N'" +
            	  "   AND oid_Entrega = "+ed.getOid_Entrega();
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"finalizaDocumentos()");
        }
    }

    //*** Calcula Valor dos Documentos DEVOLVIDOS ou CANCELADOS
    public void calculaDevCanc(Documento_EntregaED edNew) throws Excecoes {

        String sql = null;
        String DM_Sistuacao_old;
        double vlDevolvido = 0,
        	   vlSaldo = 0,
        	   vlDiferenca = 0;
        try {
            if (edNew.getOid_Documento_Entrega() < 1)
                throw new Excecoes("Documento Entrega não informado!");
            if (!doValida(edNew.getDM_Situacao()))
                throw new Excecoes("Situação do Documento Entrega não informada!");

            //*** Busca Dados anteriores antes de executar o sql do UPDATE
            Documento_EntregaED ed = this.getByRecord(new Documento_EntregaED(edNew.getOid_Documento_Entrega()));
            DM_Sistuacao_old = ed.getDM_Situacao();
            ed.setDM_Situacao(edNew.getDM_Situacao());

            //*** Verifica se deve ADICIONAR ou DIMINUIR do Valor de Cobrança
            if (!ed.getDM_Situacao().equals(DM_Sistuacao_old) &&
               ("D".equals(ed.getDM_Situacao()) || "D".equals(DM_Sistuacao_old)))
            {
                //*** Caso nao seje COBRADO NA ENTREGA cai fora.
                if (!"E".equals(getTableStringValue("DM_Tipo_Cobranca",
                                                    "Formas_Pagamentos",
                                                    "Condicoes_Pagamentos.oid_Condicao_Pagamento = Formas_Pagamentos.oid_Condicao_Pagamento" +
                                                    " AND Notas_Fiscais.oid_Condicao_Pagamento = Condicoes_Pagamentos.oid_Condicao_Pagamento" +
                                                    " AND Formas_Pagamentos.DM_Tipo_Pagamento = Notas_Fiscais.DM_Forma_Pagamento" +
                                                    " AND Notas_Fiscais.oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"'")))
                    return;

                vlDevolvido = ed.edEntrega.getVL_Devolucao_Cancelamento();
                vlSaldo = ed.edEntrega.getVL_Saldo();
                vlDiferenca = ed.edNota.getVl_liquido_nota_fiscal();

                //-- Saldo  ++ Devolução
                if ("D".equals(ed.getDM_Situacao()))
                {
                    vlSaldo -= vlDiferenca;
                    vlDevolvido += vlDiferenca;
                //++ Saldo  -- Devolução
                } else {
                    vlSaldo += vlDiferenca;
                    vlDevolvido -= vlDiferenca;
                }
                sql = " UPDATE Entregas SET " +
          	  		  " 	 VL_Saldo = "+ vlSaldo +
          	  		  "		,VL_Devolucao_Cancelamento = "+ vlDevolvido +
          	  		  " WHERE oid_Entrega = " + ed.getOid_Entrega();
                executasql.executarUpdate(sql);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"calculaDevCanc()");
        }
    }
}