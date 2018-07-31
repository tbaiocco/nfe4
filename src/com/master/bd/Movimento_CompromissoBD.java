package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Movimento_CompromissoED;
import com.master.ed.Movimento_CompromissoPesquisaED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Movimento_CompromissoBD {

  private ExecutaSQL executasql;

  public Movimento_CompromissoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Movimento_CompromissoED inclui (Movimento_CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " SELECT oid_mov_compromisso " +
          " FROM Movimentos_Compromissos " +
          " WHERE Movimentos_Compromissos.oid_lote_pagamento=" + ed.getOid_Lote_Pagamento () +
          "   AND Movimentos_Compromissos.oid_compromisso = " + ed.getOid_Compromisso ();
      ResultSet resMovComp = this.executasql.executarConsulta (sql.toString ());
      if (!resMovComp.next ()) {
        ResultSet rs = executasql.executarConsulta ("select max(oid_Mov_Compromisso) as result from Movimentos_Compromissos");
        if (rs.next ()) {
          ed.setOid_Mov_Compromisso (new Integer (rs.getInt ("result") + 1));
        }

        sql = " INSERT INTO Movimentos_Compromissos (" +
            "     OID_MOV_COMPROMISSO" +
            "     ,DT_PAGAMENTO" +
            "     ,VL_PAGAMENTO" +
            "     ,VL_MULTA_PAGAMENTO" +
            "     ,VL_JUROS_PAGAMENTO" +
            "     ,VL_ESTORNO" +
            "     ,VL_DESCONTO" +
            "     ,VL_OUTRAS_DESPESAS" +
            "     ,TX_OBSERVACAO" +
            "     ,DT_STAMP" +
            "     ,USUARIO_STAMP" +
            "     ,DM_STAMP" +
            "     ,DM_TIPO_PAGAMENTO" +
            "     ,NR_LOTE_PAGAMENTO" +
            "     ,OID_LOTE_PAGAMENTO" +
            "     ,OID_COMPROMISSO) " +
            " VALUES (" +
            ed.getOid_Mov_Compromisso () + ",";
        sql += "'" + ed.getDt_Pagamento () + "',";
        sql += ed.getVl_Pagamento () + ",";
        sql += ed.getVl_Multa_Pagamento () == null ? "null," : ed.getVl_Multa_Pagamento () + ",";
        sql += ed.getVl_Juros_Pagamento () == null ? "null," : ed.getVl_Juros_Pagamento () + ",";
        sql += "0.0,";
        sql += ed.getVl_Desconto () == null ? "null," : ed.getVl_Desconto () + ",";
        sql += ed.getVl_Outras_Despesas () == null ? "null," : ed.getVl_Outras_Despesas () + ",";
        sql += ed.getTx_Observacao () == null ? "null," : "'" + ed.getTx_Observacao () + "',";
        sql += "'" + ed.getDt_stamp () + "',";
        sql += "'" + ed.getUsuario_Stamp () + "',";
        sql += "'" + ed.getDm_Stamp () + "',";
        sql += ed.getDm_Tipo_Pagamento () == null ? "null," : "'" + ed.getDm_Tipo_Pagamento () + "',";
        sql += ed.getNr_Lote_Pagamento () == null ? "null," : ed.getNr_Lote_Pagamento () + ",";
        sql += ed.getOid_Lote_Pagamento () + ",";
        sql += ed.getOid_Compromisso () + ")";

        
        // System.out.println(sql);

        executasql.executarUpdate (sql);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "Inclui()");
    }
    return ed;
  }

  public Movimento_CompromissoED getByRecord (Movimento_CompromissoED ed) throws Excecoes {

    String sql = null;
    Movimento_CompromissoED edVolta = new Movimento_CompromissoED ();
    try {

      sql = " SELECT * " +
          " FROM Movimentos_Compromissos" +
          "     ,compromissos" +
          "     ,pessoas" +
          "     ,tipos_documentos " +
          " WHERE movimentos_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
          "   AND compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
          "   AND compromissos.oid_pessoa = pessoas.oid_pessoa ";

      if (ed.getOid_Mov_Compromisso () != null) {
        sql += " and movimentos_Compromissos.oid_mov_compromisso = " + ed.getOid_Mov_Compromisso ();
      }

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_social"));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_compromisso")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
        edVolta.setDm_Situacao (res.getString ("Dm_Situacao"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_pagamento"));
        edVolta.setDt_Pagamento (DataFormatada.getDT_FormataData ());

        edVolta.setNr_Documento (res.getString ("nr_documento"));

        edVolta.setVl_Pagamento (new Double (res.getDouble ("vl_pagamento")));
        edVolta.setVl_Multa_Pagamento (new Double (res.getDouble ("vl_multa_pagamento")));
        edVolta.setVl_Estorno (new Double (res.getDouble ("vl_Estorno")));
        edVolta.setVl_Outras_Despesas (new Double (res.getDouble ("vl_outras_despesas")));
        edVolta.setVl_Desconto (new Double (res.getDouble ("vl_desconto")));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));

        String obs = res.getString ("tx_observacao");
        if (obs != null) {
          edVolta.setTx_Observacao (obs);
        }

        // System.out.println("OID LOTE=" + res.getInt ("Oid_Lote_Pagamento"));
        edVolta.setOid_Lote_Pagamento(res.getLong("Oid_Lote_Pagamento"));
        edVolta.setOid_Mov_Compromisso (new Integer (res.getInt ("oid_mov_compromisso")));
        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_compromisso")));
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

  public void altera (Movimento_CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " UPDATE Movimentos_Compromissos SET ";
      if (ed.getDt_Pagamento () != null) {
        sql += " DT_PAGAMENTO = '" + ed.getDt_Pagamento () + "', ";
      }
      else {
        sql += " DT_PAGAMENTO = null,";
      }

      if (ed.getVl_Multa_Pagamento () != null) {
        sql += " VL_MULTA_PAGAMENTO = " + ed.getVl_Multa_Pagamento () + ", ";
      }
      else {
        sql += " VL_MULTA_PAGAMENTO = null,";
      }

      if (ed.getVl_Juros_Pagamento () != null) {
        sql += " VL_JUROS_PAGAMENTO = " + ed.getVl_Juros_Pagamento () + ", ";
      }
      else {
        sql += " VL_JUROS_PAGAMENTO = null,";
      }

      if (ed.getVl_Desconto () != null) {
        sql += " VL_DESCONTO = " + ed.getVl_Desconto () + ", ";
      }
      else {
        sql += " VL_DESCONTO = null,";
      }

      if (ed.getVl_Outras_Despesas () != null) {
        sql += " VL_OUTRAS_DESPESAS = " + ed.getVl_Outras_Despesas () + ", ";
      }
      else {
        sql += " VL_OUTRAS_DESPESAS = null,";
      }

      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "',";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "' ";
      sql += " where oid_Mov_Compromisso = " + ed.getOid_Mov_Compromisso ();
      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
  }

  public void deleta (Movimento_CompromissoED ed) throws Excecoes {

    String sql = null;
    try {
      long oid_Compromisso = 0;
      double vl_saldo = 0;
      sql = " SELECT Compromissos.oid_Compromisso, Movimentos_Compromissos.vl_pagamento, Compromissos.vl_saldo, Compromissos.vl_compromisso " +
          " FROM Movimentos_Compromissos, Compromissos " +
          " WHERE Movimentos_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
          " AND   oid_Mov_Compromisso = " + ed.getOid_Mov_Compromisso ();
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        oid_Compromisso = res.getLong ("oid_Compromisso");
        vl_saldo = (res.getDouble ("vl_pagamento") + res.getDouble ("vl_saldo"));
        if (vl_saldo > res.getDouble ("vl_compromisso")) {
          vl_saldo = res.getDouble ("vl_compromisso");
        }
      }
      if (oid_Compromisso > 0) {
        sql = " UPDATE Compromissos SET VL_Saldo= " + vl_saldo +
            " WHERE  Compromissos.oid_Compromisso =" + oid_Compromisso;
        executasql.executarUpdate (sql);
      }

      sql = "DELETE FROM Movimentos_Compromissos WHERE oid_Mov_Compromisso = ";
      sql += ed.getOid_Mov_Compromisso ();

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public ArrayList lista (Movimento_CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    Movimento_CompromissoPesquisaED ed = (Movimento_CompromissoPesquisaED) edComp;

    try {
      sql = " SELECT * " +
          " FROM Movimentos_Compromissos" +
          "     ,compromissos" +
          "     ,pessoas " +
          " WHERE movimentos_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
          "   and compromissos.oid_pessoa = pessoas.oid_pessoa ";

      if (ed.getOid_Pessoa () != null) {
        sql += " and compromissos.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
      }
      if (ed.getNr_Documento () != null) {
        sql += " and compromissos.Nr_Documento = " + ed.getNr_Documento ();
      }
      if (ed.getOid_Compromisso () != null) {
        sql += " and movimentos_compromissos.oid_compromisso = '" + ed.getOid_Compromisso () + "'";
      }
      if (ed.getDt_Pgto_Inicial () != null) {
        sql += " and movimentos_compromissos.dt_pagamento >= '" + ed.getDt_Pgto_Inicial () + "'";
      }
      if (ed.getDt_Pgto_Final () != null) {
        sql += " and movimentos_compromissos.dt_pagamento <= '" + ed.getDt_Pgto_Final () + "'";
      }
      sql += " ORDER BY movimentos_compromissos.dt_pagamento ";
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Movimento_CompromissoPesquisaED edVolta = new Movimento_CompromissoPesquisaED ();

        edVolta.setNm_Razao_Social (res.getString ("nm_razao_social"));
        edVolta.setNr_Parcela (new Integer (res.getString ("nr_parcela")));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_pagamento"));
        edVolta.setDt_Pagamento (DataFormatada.getDT_FormataData ());

        edVolta.setVl_Estorno (new Double (res.getDouble ("vl_Estorno")));
        edVolta.setVl_Pagamento (new Double (res.getDouble ("vl_pagamento")));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_compromisso")));
        edVolta.setNr_Documento (res.getString ("NR_Documento"));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setOid_Mov_Compromisso (new Integer (res.getInt ("oid_mov_compromisso")));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    return list;
  }

  public Movimento_CompromissoED liquidaCompromissos (Movimento_CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " SELECT oid_compromisso, VL_Compromisso " +
          " FROM Compromissos " +
          " WHERE Compromissos.NR_Compromisso in (" + ed.getNR_Compromissos() + ")" ;
        // System.out.println(sql);

        ResultSet rs = executasql.executarConsulta (sql);
        while(rs.next ()) {
        // System.out.println("compromisso ->> "+ rs.getString("oid_compromisso"));
          Movimento_CompromissoED movED = new Movimento_CompromissoED();
          movED.setOid_Compromisso(new Integer(rs.getString("oid_compromisso")));
          movED.setVl_Pagamento(new Double (rs.getString("VL_Compromisso")));
          movED.setDt_Pagamento(ed.getDt_Pagamento());
          this.inclui(movED);

          sql = " UPDATE Compromissos SET VL_Saldo=0 WHERE oid_Compromisso = '" +  rs.getString("oid_compromisso") + "'";
          executasql.executarUpdate (sql);

        }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "Inclui()");
    }
    return ed;
  }

}