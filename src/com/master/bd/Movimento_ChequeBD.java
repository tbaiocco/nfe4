package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Movimento_ChequeED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Movimento_ChequeBD {

    private ExecutaSQL executasql;

    public Movimento_ChequeBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Movimento_ChequeED inclui(Movimento_ChequeED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " SELECT oid_mov_Cheque " + 
                  " FROM Movimentos_Cheques " + 
                  " WHERE Movimentos_Cheques.oid_lote_pagamento=" + ed.getOid_Lote_Pagamento() + 
                  "   AND Movimentos_Cheques.oid_Cheque = " + ed.getOid_Cheque();
            ResultSet resMovComp = this.executasql.executarConsulta(sql.toString());
            if (!resMovComp.next())
            {
                ResultSet rs = executasql.executarConsulta("select max(oid_Mov_Cheque) as result from Movimentos_Cheques");
                if (rs.next())
                    ed.setOid_Mov_Cheque(new Integer(rs.getInt("result")+1));

                sql = " INSERT INTO Movimentos_Cheques (" +
                      "     OID_MOV_Cheque" +
                      "     ,DT_PAGAMENTO" +
                      "     ,VL_PAGAMENTO" +
                      "     ,VL_MULTA_PAGAMENTO" +
                      "     ,VL_JUROS_PAGAMENTO" +
                      "     ,VL_ESTORNO" +
                      "     ,VL_DESCONTO" +
                      "     ,VL_OUTRAS_DESPESAS" +
                      "     ,TX_OBSERVACAO" +
                      "     ,NR_LOTE_PAGAMENTO" +
                      "     ,OID_LOTE_PAGAMENTO" +
                      "     ,OID_Cheque) " +
                      " VALUES (" +
                        ed.getOid_Mov_Cheque() + ",";
                sql += "'" + ed.getDt_Pagamento() + "',";
                sql += ed.getVl_Pagamento() + ",";
                sql += ed.getVl_Multa_Pagamento() == null ? "null," : ed.getVl_Multa_Pagamento() + ",";
                sql += ed.getVl_Juros_Pagamento() == null ? "null," : ed.getVl_Juros_Pagamento() + ",";
                sql += "0.0,";
                sql += ed.getVl_Desconto() == null ? "null," : ed.getVl_Desconto() + ",";
                sql += ed.getVl_Outras_Despesas() == null ? "null," : ed.getVl_Outras_Despesas() + ",";
                sql += ed.getTx_Observacao() == null ? "null," : "'" + ed.getTx_Observacao() + "',";
                sql += ed.getNr_Lote_Pagamento() == null ? "null," : ed.getNr_Lote_Pagamento() + ",";
                sql += ed.getOid_Lote_Pagamento() + ",";
                sql += ed.getOid_Cheque() + ")";

                // System.out.println(sql);

                executasql.executarUpdate(sql);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "Inclui()");
        }
        return ed;
    }

    public Movimento_ChequeED getByRecord(Movimento_ChequeED ed) throws Excecoes {

        String sql = null;
        Movimento_ChequeED edVolta = new Movimento_ChequeED();
        try {

            sql = " SELECT * " +
                  " FROM Movimentos_Cheques " +
                  " WHERE  Movimentos_Cheques.oid_mov_Cheque = " + ed.getOid_Mov_Cheque();

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta.setDm_Situacao(res.getString("Dm_Situacao"));

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("dt_pagamento"));
                edVolta.setDt_Pagamento(DataFormatada.getDT_FormataData());

                edVolta.setVl_Pagamento(new Double(res.getDouble("vl_pagamento")));
                edVolta.setVl_Multa_Pagamento(new Double(res.getDouble("vl_multa_pagamento")));
                edVolta.setVl_Estorno(new Double(res.getDouble("vl_Estorno")));
                edVolta.setVl_Outras_Despesas(new Double(res.getDouble("vl_outras_despesas")));
                edVolta.setVl_Desconto(new Double(res.getDouble("vl_desconto")));

                String obs = res.getString("tx_observacao");
                if (obs != null)
                    edVolta.setTx_Observacao(obs);

                edVolta.setOid_Mov_Cheque(new Integer(res.getInt("oid_mov_Cheque")));
                edVolta.setOid_Cheque(res.getInt("oid_Cheque"));
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
        return edVolta;
    }

    public void altera(Movimento_ChequeED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Movimentos_Cheques SET ";
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

            sql += " DT_Stamp = '" + ed.getDT_Stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "',";
            sql += " DM_STAMP = '" + ed.getDM_Stamp() + "' ";
            sql += " where oid_Mov_Cheque = " + ed.getOid_Mov_Cheque();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Movimento_ChequeED ed) throws Excecoes {

        String sql = null;
        try {

            sql = "DELETE FROM Movimentos_Cheques WHERE oid_Mov_Cheque = ";
            sql += ed.getOid_Mov_Cheque();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(Movimento_ChequeED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        int nr_parcela=0;
        try {
            sql = " SELECT * " +
                  " FROM Movimentos_Cheques" +
                  " WHERE movimentos_Cheques.oid_Cheque = " + ed.getOid_Cheque();
            sql += " ORDER BY movimentos_Cheques.dt_pagamento ";
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Movimento_ChequeED edVolta = new Movimento_ChequeED();
                nr_parcela++;
                edVolta.setNR_Parcela(nr_parcela);

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("dt_pagamento"));
                edVolta.setDt_Pagamento(DataFormatada.getDT_FormataData());
                edVolta.setVl_Pagamento(new Double(res.getDouble("vl_pagamento")));
                edVolta.setOid_Mov_Cheque(new Integer(res.getInt("oid_mov_Cheque")));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
        return list;
    }
}