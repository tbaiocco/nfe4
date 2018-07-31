package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Lote_CompromissoED;
import com.master.ed.Lote_PagamentoED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Lote_CompromissoBD  {

    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria ();

    public Lote_CompromissoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Lote_CompromissoED inclui(Lote_CompromissoED ed) throws Excecoes {

        String sql = null;
        String chave = null;
        try {
            chave = (String.valueOf(ed.getOid_Lote_Pagamento()) + String.valueOf(ed.getOid_Compromisso()));
            ed.setOid_Lote_Compromisso(chave);

            sql = " INSERT INTO Lotes_Compromissos (" +
                  "      Oid_Lote_Compromisso" +
                  "     ,DT_PAGAMENTO" +
                  "     ,VL_PAGAMENTO" +
                  "     ,VL_MULTA_PAGAMENTO" +
                  "     ,VL_JUROS_PAGAMENTO" +
                  "     ,VL_DESCONTO" +
                  "     ,VL_Saldo_Lote_Compromisso" +
                  "     ,VL_OUTRAS_DESPESAS" +
                  "     ,TX_OBSERVACAO" +
                  "     ,USUARIO_STAMP" +
                  "     ,DM_STAMP" +
                  "     ,DM_TIPO_PAGAMENTO" +
                  "     ,NR_LOTE_PAGAMENTO" +
                  "     ,OID_COMPROMISSO" +
                  "     ,OID_Lote_Pagamento" +
                  "     ,DM_Situacao)" +
                  " VALUES('" +
                  ed.getOid_Lote_Compromisso() + "',";
            sql += "'" + ed.getDt_Pagamento() + "',";
            sql += ed.getVl_Pagamento() + ",";
            sql += ed.getVl_Multa_Pagamento() == null ? "null," : ed.getVl_Multa_Pagamento() + ",";
            sql += ed.getVl_Juros_Pagamento() == null ? "null," : ed.getVl_Juros_Pagamento() + ",";
            sql += ed.getVl_Desconto() == null ? "null," : ed.getVl_Desconto() + ",";
            sql += ed.getVl_Saldo_Lote_Compromisso() == null ? "null," : ed.getVl_Saldo_Lote_Compromisso() + ",";
            sql += ed.getVl_Outras_Despesas() == null ? "null," : ed.getVl_Outras_Despesas() + ",";
            sql += ed.getTx_Observacao() == null ? "null," : "'" + ed.getTx_Observacao() + "',";
            sql += "'',";
            sql += "'',";
            sql += ed.getDm_Tipo_Pagamento() == null ? "null," : "'" + ed.getDm_Tipo_Pagamento() + "',";
            sql += ed.getNr_Lote_Pagamento() == null ? "null," : ed.getNr_Lote_Pagamento() + ",";
            sql += ed.getOid_Compromisso() + ",";
            sql += ed.getOid_Lote_Pagamento() + ",";
            sql += "'" + "A" + "')";
            executasql.executarUpdate(sql);

            sql = " UPDATE Lotes_Pagamentos SET ";
            sql +="     VL_Lote_Pagamento = " + this.calculaLote(ed.getOid_Lote_Pagamento());
            sql +=" WHERE oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();

            executasql.executarUpdate(sql);

            sql = " UPDATE Compromissos SET ";
            sql +="     DM_Lote_Pagamento = 'S'";
            sql += " WHERE Compromissos.oid_Compromisso = " + ed.getOid_Compromisso();
            executasql.executarUpdate(sql);

            return ed;
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Lote_CompromissoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Lotes_Compromissos SET ";
            if (ed.getDt_Pagamento() != null)
                sql += " DT_PAGAMENTO = '" + ed.getDt_Pagamento() + "', ";
            else sql += " DT_PAGAMENTO = null,";
            if (ed.getVl_Multa_Pagamento() != null)
                sql += " VL_MULTA_PAGAMENTO = " + ed.getVl_Multa_Pagamento() + ", ";
            else sql += " VL_MULTA_PAGAMENTO = null,";
            if (ed.getVl_Juros_Pagamento() != null)
                sql += " VL_JUROS_PAGAMENTO = " + ed.getVl_Juros_Pagamento() + ", ";
            else sql += " VL_JUROS_PAGAMENTO = null,";
            if (ed.getVl_Desconto() != null)
                sql += " VL_DESCONTO = " + ed.getVl_Desconto() + ", ";
            else sql += " VL_DESCONTO = null,";
            if (ed.getVl_Outras_Despesas() != null)
                sql += " VL_OUTRAS_DESPESAS = " + ed.getVl_Outras_Despesas() + ", ";
            else sql += " VL_OUTRAS_DESPESAS = null,";

            sql += " USUARIO_STAMP = '',";
            sql += " DM_STAMP = '' ";
            sql += " WHERE Oid_Lote_Compromisso = " + ed.getOid_Lote_Compromisso();

            executasql.executarUpdate(sql);
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "altera()");
        }
    }

    public double calculaLote(long oid_Lote_Pagamento) throws Excecoes {

        String sql = null;
        double vl_lote=0;
        try {

            //*** Calcula Valor do Lote
            sql =" SELECT sum(VL_Pagamento) as vl_lote FROM Lotes_Compromissos WHERE oid_Lote_Pagamento=" +oid_Lote_Pagamento;

            ResultSet res = this.executasql.executarConsulta(sql);
            if (res.next())
            {
            	vl_lote=res.getDouble("vl_lote");
            }

        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "altera()");
        }
        return vl_lote;
    }

    public void deleta(Lote_CompromissoED ed) throws Excecoes {

        String sql = null;
        try {
            //*** Busca Referencia do Compromisso
            ed.setOid_Compromisso(new Integer(util.getTableIntValue("oid_Compromisso",
                    "Lotes_Compromissos",
                    "oid_Lote_Compromisso = "+ed.getOid_Lote_Compromisso())));

            sql = " DELETE FROM Lotes_Compromissos " +
                  " WHERE Oid_Lote_Compromisso = "+ed.getOid_Lote_Compromisso();
            executasql.executarUpdate(sql);

            //*** Calcula Valor do Lote
            sql = " UPDATE Lotes_Pagamentos SET ";
            sql +="     VL_Lote_Pagamento = " + this.calculaLote(ed.getOid_Lote_Pagamento());
            sql +=" WHERE oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
            executasql.executarUpdate(sql);

            sql = " UPDATE Compromissos SET ";
            sql +="     DM_LOTE_PAGAMENTO = 'N'";
            sql +=" WHERE oid_compromisso = " + ed.getOid_Compromisso();
            executasql.executarUpdate(sql);
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "deleta()");
        }
    }

    public Lote_CompromissoED getByRecord(Lote_CompromissoED ed) throws Excecoes {

        String sql = null;
        Lote_CompromissoED edVolta = new Lote_CompromissoED();
        try {

            sql = " SELECT * " +
                  " FROM Lotes_Compromissos" +
                  "     ,compromissos, pessoas" +
                  "     ,tipos_documentos " +
                  " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
                  "   AND compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
                  "   AND compromissos.oid_pessoa = pessoas.oid_pessoa " +
                  "   AND Lotes_Compromissos.Oid_Lote_Compromisso = " + ed.getOid_Lote_Compromisso();

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta.setNr_Lote_Pagamento(new Integer(res.getInt("OID_Lote_Pagamento")));
                edVolta.setOid_Lote_Pagamento(res.getLong("OID_Lote_Pagamento"));
                edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
                edVolta.setNr_Parcela(new Integer(res.getString("nr_parcela")));
                edVolta.setNm_Tipo_Documento(res.getString("nm_tipo_documento"));

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("dt_pagamento"));
                edVolta.setDt_Pagamento(DataFormatada.getDT_FormataData());

                edVolta.setVl_Pagamento(new Double(res.getDouble("vl_pagamento")));
                edVolta.setOid_Compromisso(new Integer(res.getInt("oid_compromisso")));
                edVolta.setNr_Compromisso(new Integer(res.getInt("nr_compromisso")));
                edVolta.setVl_Saldo(new Double(res.getDouble("vl_saldo_lote_compromisso")));
                edVolta.setNr_Documento(res.getString("nr_documento"));

                edVolta.setVl_Multa_Pagamento(new Double(res.getDouble("vl_multa_pagamento")));
                edVolta.setVl_Outras_Despesas(new Double(res.getDouble("vl_outras_despesas")));
                edVolta.setVl_Desconto(new Double(res.getDouble("vl_desconto")));
                String obs = res.getString("tx_observacao");
                if (obs != null)
                    edVolta.setTx_Observacao(obs);

                edVolta.setOid_Lote_Compromisso(res.getString("Oid_Lote_Compromisso"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                if (!"E".equals(res.getString("DM_Situacao")))
                {
                    edVolta.setDM_Situacao(util.getTableStringValue("DM_Situacao", "Lotes_Pagamentos", "oid_Lote_Pagamento = " + edVolta.getOid_Lote_Pagamento()));
                }
            }
            return edVolta;
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "getByRecord()");
        }
    }

    public void estorna_Pagamento(Lote_CompromissoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " SELECT Lotes_Compromissos.OID_Compromisso" +
                  "     ,Lotes_Compromissos.VL_Pagamento" +
                  "     ,Lotes_Pagamentos.VL_Lote_Pagamento" +
                  "     ,Lotes_Pagamentos.oid_conta_corrente" +
                  "     ,Lotes_Pagamentos.nr_documento" +
                  "     ,Lotes_Pagamentos.oid_tipo_documento" +
                  "     ,Lotes_Pagamentos.DT_Emissao" +
                  "     ,Lotes_Compromissos.vl_multa_pagamento" +
                  "     ,Lotes_Compromissos.vl_desconto" +
                  "     ,Lotes_Compromissos.vl_outras_despesas" +
                  "     ,Compromissos.VL_Saldo" +
                  "     ,Compromissos.VL_Compromisso" +
                  "     ,Compromissos.nr_Compromisso" +
                  "     ,Lotes_Compromissos.oid_Lote_Pagamento" +
                  "     ,Contas_Correntes.dm_tipo_conta_corrente" +
                  " FROM Lotes_Compromissos" +
                  "     ,Compromissos" +
                  "     ,Lotes_Pagamentos " +
                  "     ,Contas_Correntes " +
                  " WHERE Lotes_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
                  "   AND Contas_Correntes.oid_conta_corrente = Lotes_Pagamentos.oid_conta_corrente " +
                  "   AND Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento " +
                  "   AND Lotes_Compromissos.DM_Situacao <> 'E' " +
                  "   AND Lotes_Compromissos.oid_Lote_Compromisso = " + ed.getOid_Lote_Compromisso();

            double vl_saldo = 0;
            double vl_compromisso = 0;
            double vl_pagamento = 0;
            double vl_multa_pagamento = 0;
            double vl_desconto = 0;
            double vl_outras_despesas = 0;
            long oid_Compromisso = 0;

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next() && vl_saldo == 0) {

                oid_Compromisso = res.getLong("OID_Compromisso");
                vl_compromisso = res.getDouble("Vl_Compromisso");
                vl_pagamento = res.getDouble("VL_Pagamento");
                vl_saldo = res.getDouble("VL_Saldo");
                vl_multa_pagamento = res.getDouble("vl_multa_pagamento");
                vl_desconto = res.getDouble("vl_desconto");
                vl_outras_despesas = res.getDouble("vl_outras_despesas");

                sql = " UPDATE Movimentos_Compromissos SET " +
                      "      VL_Estorno = " + res.getDouble("VL_Pagamento") +
                      "     ,VL_Pagamento= 0 " +
                      "     ,TX_Observacao ='PAGAMENTO ESTORNADO'" +
                      "     ,DM_Situacao='E' "+
                      " WHERE OID_Compromisso = " + res.getString("OID_Compromisso") +
                      "  AND oid_Lote_Pagamento = " + res.getString("oid_Lote_Pagamento");

                executasql.executarUpdate(sql);

                sql = " UPDATE Lotes_Pagamentos SET " +
                      "     VL_Lote_Pagamento= " + this.calculaLote(ed.getOid_Lote_Pagamento()) +
                      " WHERE oid_Lote_Pagamento = "+ res.getString("oid_Lote_Pagamento");
                executasql.executarUpdate(sql); // (nao fazer porque na
                                                // compensacao o valor vem
                                                // integral);

                sql = " UPDATE Lotes_Compromissos SET " +
                      "     DM_SITUACAO = 'E' " +
                      " WHERE oid_Lote_Compromisso = " + ed.getOid_Lote_Compromisso();
                executasql.executarUpdate(sql);
            }
            if (oid_Compromisso > 0)
            {
              vl_saldo=vl_saldo+vl_pagamento-vl_multa_pagamento-vl_outras_despesas+vl_desconto;
              if (vl_saldo < 0) vl_saldo = 0;
              if (vl_saldo > vl_compromisso) vl_saldo=vl_compromisso;
                  sql = " UPDATE Compromissos SET " +
                      "     DM_Lote_Pagamento = 'N'" +
                      "     ,Vl_Saldo= " + vl_saldo +
                      " WHERE oid_Compromisso = " + oid_Compromisso;
                  executasql.executarUpdate(sql);
            }


            this.estorna_Movimento_Conta_Corrente(res);

        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "estorna()");
        }
    }

    public void estorna_Movimento_Conta_Corrente(ResultSet res) throws Excecoes {

        String sql = null;
        try {

            Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED();
            edMovConta.setDT_Movimento_Conta_Corrente(res.getString("DT_Emissao"));
            edMovConta.setOid_Lote_Pagamento(res.getLong("Oid_Lote_Pagamento"));

            edMovConta.setOid_Conta_Corrente(res.getString("OID_Conta_Corrente"));
            edMovConta.setOid_Conta(util.getTableIntValue("oid_Conta", "Contas_Correntes", "oid_Conta_Corrente = '" + edMovConta.getOid_Conta_Corrente() + "'"));

            edMovConta.setNR_Documento(res.getString("NR_Documento"));

            edMovConta.setNM_Complemento_Historico("Estorno Lote Pgto: " + res.getString("OID_LOTE_PAGAMENTO") + " Compr. Nr: "+res.getString("nr_Compromisso"));

            if ("U".equals(res.getString("dm_tipo_conta_corrente") )){
                edMovConta.setDM_Debito_Credito("D");
            }else{
                edMovConta.setDM_Debito_Credito("C");
            }
            edMovConta.setDM_Tipo_Lancamento("G");
            edMovConta.setOID_Tipo_Documento(new Integer(res.getString("oid_Tipo_Documento")));
            edMovConta.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Estorno()));

            edMovConta.setVL_Lancamento(new Double(res.getString("VL_Pagamento")));
            edMovConta = new Movimento_Conta_CorrenteBD(executasql).inclui(edMovConta);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "devolucao()");
        }
    }

    public ArrayList lista(Lote_CompromissoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {
            sql = " SELECT Lotes_Pagamentos.OID_Lote_Pagamento" +
                  "     ,Lotes_Compromissos.dt_pagamento" +
                  "     ,Lotes_Compromissos.vl_pagamento" +
                  "     ,Lotes_Compromissos.Oid_Lote_Compromisso" +
                  "     ,Lotes_Compromissos.DM_Situacao" +
                  "     ,compromissos.nr_parcela" +
                  "     ,compromissos.nr_compromisso" +
                  "     ,compromissos.NR_Documento" +
                  "     ,compromissos.vl_saldo" +
                  "     ,pessoas.nm_razao_social " +
                  " FROM Lotes_Pagamentos" +
                  "     ,Lotes_Compromissos" +
                  "     ,compromissos" +
                  "     ,pessoas " +
                  " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
                  "   AND Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento " +
                  "   AND compromissos.oid_pessoa = pessoas.oid_pessoa ";

            if (ed.getOid_Pessoa()!= null && ed.getOid_Pessoa().length()>4)
                sql += " AND compromissos.oid_pessoa = '" + ed.getOid_Pessoa() + "'";
            if (ed.getNr_Documento() != null)
                sql += " AND compromissos.Nr_Documento = " + ed.getNr_Documento();
            if (util.doValida(String.valueOf(ed.getOid_Lote_Pagamento())))
                sql += " AND Lotes_Compromissos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
            if (ed.getOid_Compromisso() != null)
                sql += " AND Lotes_Compromissos.oid_compromisso = '" + ed.getOid_Compromisso() + "'";
            if (ed.getDt_Pgto_Inicial() != null)
                sql += " AND Lotes_Compromissos.dt_pagamento >= '" + ed.getDt_Pgto_Inicial() + "'";
            if (ed.getDt_Pgto_Final() != null)
                sql += " AND Lotes_Compromissos.dt_pagamento <= '" + ed.getDt_Pgto_Final() + "'";

            //sql += " ORDER BY Lotes_Compromissos.dt_pagamento ";
            sql += " ORDER BY Lotes_Compromissos.oid ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
            	Lote_CompromissoED edVolta = new Lote_CompromissoED();

                edVolta.setNr_Lote_Pagamento(new Integer(res.getInt("OID_Lote_Pagamento")));
                edVolta.setOid_Lote_Pagamento(res.getLong("OID_Lote_Pagamento"));

                edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
                edVolta.setNr_Parcela(new Integer(res.getInt("nr_parcela")));

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("dt_pagamento"));
                edVolta.setDt_Pagamento(DataFormatada.getDT_FormataData());

                edVolta.setVl_Pagamento(new Double(res.getDouble("vl_pagamento")));
                edVolta.setNr_Compromisso(new Integer(res.getInt("nr_compromisso")));
                edVolta.setNr_Documento(res.getString("NR_Documento"));
                edVolta.setVl_Saldo(new Double(res.getDouble("vl_saldo")));
                edVolta.setOid_Lote_Compromisso(res.getString("Oid_Lote_Compromisso"));

                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                if ("E".equals(res.getString("DM_Situacao")))
                {
                    edVolta.setDM_Situacao("ESTORNADO");
                }
                if ("C".equals(res.getString("DM_Situacao")))
                {
                    edVolta.setDM_Situacao("LOTE CANCELADO");
                }
                list.add(edVolta);
            }
            return list;
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "lista()");
        }
    }
}
