package com.master.bd;

/**
 * <p>Title: Saldo_LoteBD </p>
 * <p>Description: Inclui, altera, exclui e gerencia saldos para programacao e/ou emissao de cheques.
 * Controla Limite de pgtos por dia. </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: ExitoLogistica & MasterCOM </p>
 * @author Teofilo Poletto Baiocco
 * @version 1.0
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.master.ed.Saldo_LoteED;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

public class Saldo_LoteBD {

  private ExecutaSQL executasql;

  public Saldo_LoteBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Saldo_LoteED inclui (Saldo_LoteED ed) throws Excecoes {

    String sql = null;
    String chave = null;
    long Nr_Caixa = 0;
    long valOid = 1;
    int novoDia;
    String data_Saldo = "";

    Saldo_LoteED Saldo_LoteED = new Saldo_LoteED ();

    try {
      DateFormatter datefor = new DateFormatter ();
      Calendar cal1 = Data.stringToCalendar ("01/01/2006" , "dd/MM/yyyy");
      Calendar cal2 = Data.stringToCalendar ("31/12/2008" , "dd/MM/yyyy");

      // System.out.println ("1");

      while (!cal1.after (cal2)) {

        ResultSet rs = executasql.executarConsulta ("select max(oid_Saldo) as result from Saldos_Lotes");
        while (rs.next ()) {
          valOid = rs.getLong ("result") + 1;
        }

        // System.out.println ("2 " + cal1.get (Calendar.DAY_OF_MONTH) + " " + cal1.get (Calendar.DAY_OF_WEEK));

        if (1==1) {//cal1.get (Calendar.DAY_OF_MONTH) != 15 && cal1.get (Calendar.DAY_OF_WEEK) != 1 && cal1.get (Calendar.DAY_OF_WEEK) != 7) {


          data_Saldo = datefor.calendarToString (cal1 , "dd/MM/yyyy");

          // System.out.println ("data_Saldo=> " + data_Saldo);

          sql =  " select oid_Saldo from Saldos_Lotes ";
          sql += " where DT_Saldo = '" + data_Saldo + "'";
          ResultSet res2 = this.executasql.executarConsulta (sql);
          if (!res2.next ()) {
            sql = " insert into Saldos_Lotes " +
                "(OID_Saldo, " +
                "DT_Saldo, " +
                "DT_STAMP, " +
                "USUARIO_STAMP, " +
                "DM_STAMP, " +
                "VL_Saldo " +
                ") values " +
                "(" + valOid + ",";
            sql += "'" + data_Saldo + "',";
            sql += "'" + Data.getDataDMY () + "',";
            sql += "'" + ed.getUsuario_Stamp () + "',";
            sql += "'S',";
            sql += 25000.0 + ")";

            // System.out.println (sql);

            int i = executasql.executarUpdate (sql);
            valOid++;
          }
        }

        // System.out.println ("3");

        novoDia = cal1.get (Calendar.DAY_OF_MONTH);
        novoDia++;
        cal1.set (Calendar.DAY_OF_MONTH , novoDia);

        // System.out.println ("4 " + datefor.calendarToString (cal1 , "dd/MM/yyyy"));
      }

      //pega proximo valor da chave
//        ResultSet rs = executasql.executarConsulta("select max(oid_Saldo) as result from Saldos_Lotes");
//        while (rs.next()){
//          valOid = rs.getLong("result") + 1;
//        }
//
//        sql = " insert into Saldos_Lotes "+
//        "(OID_Saldo, "+
//        "DT_Saldo, " +
//        "DT_STAMP, " +
//        "USUARIO_STAMP, "+
//        "DM_STAMP, "+
//        "VL_Saldo "+
//        ") values "+
//        "(" + valOid + ",";
//        sql += "'" + ed.getDT_Saldo() + "',";
//        sql += "'" + Data.getDataDMY() + "',";
//        sql += "'" + ed.getUsuario_Stamp() + "',";
//        sql += "'S',";
//        sql += ed.getVL_Saldo() + ")";
//
//        //// System.out.println(sql);
//
//        int i = executasql.executarUpdate(sql);

      Saldo_LoteED.setOid_Saldo (valOid);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Saldo");
      excecoes.setMetodo ("Inclui_Saldo_Lote(Saldo_LoteED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return Saldo_LoteED;
  }

  public void altera (Saldo_LoteED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Saldos_Lotes set ";
      sql += " DT_STAMP = '" + Data.getDataDMY () + "',";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "',";
      sql += " DM_STAMP = 'S',";
      sql += " VL_Saldo = " + ed.getVL_Saldo ();
      sql += " where oid_Saldo = " + ed.getOid_Saldo ();

      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados de Saldo ");
      excecoes.setMetodo ("altera_Saldo_Lote(Saldo_LoteED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Saldo_LoteED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from Saldos_Lotes WHERE oid_Saldo = ";
      sql += ed.getOid_Saldo ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Saldo");
      excecoes.setMetodo ("deleta_Saldo_Lote(Saldo_LoteED)");
      excecoes.setExc (exc);
      throw excecoes;

    }
  }

  public Saldo_LoteED getByRecord (Saldo_LoteED ed) throws Excecoes {

    String sql = null;
    Saldo_LoteED edVolta = new Saldo_LoteED ();

    try {

      sql = "select * from Saldos_Lotes ";
      sql += " where oid_Saldo = " + ed.getOid_Saldo ();

      ResultSet res = null;

      FormataDataBean dataFormatada = new FormataDataBean ();

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Saldo_LoteED ();
        edVolta.setOid_Saldo (res.getLong ("oid_Saldo"));
        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("DT_Saldo"));
        edVolta.setDT_Saldo (DataFormatada.getDT_FormataData ());
        edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao acessar Saldo");
      excecoes.setMetodo ("getByRecord_Saldo_Lote(Saldo_LoteED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public ArrayList lista (Saldo_LoteED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    Saldo_LoteED edVolta = new Saldo_LoteED ();

    try {
      sql = "select * from Saldos_Lotes " +
          " where 1 =1 ";
      if (ed.getDT_Inicial () != null && !ed.getDT_Inicial ().equals ("")) {
        sql += " and dt_Saldo >= '" + ed.getDT_Inicial () + "'";
      }
      if (ed.getDT_Final () != null && !ed.getDT_Final ().equals ("")) {
        sql += " AND dt_Saldo <= '" + ed.getDT_Final () + "'";
      }
      sql += " ORDER BY dt_Saldo ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        edVolta = new Saldo_LoteED ();
        edVolta.setOid_Saldo (res.getLong ("oid_Saldo"));
        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("DT_Saldo"));
        edVolta.setDT_Saldo (DataFormatada.getDT_FormataData ());
        edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar dados de Saldo");
      excecoes.setMetodo ("lista_Saldo_Lote(Saldo_LoteED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return list;
  }

  public Saldo_LoteED getTotalProgramado (Saldo_LoteED ed) throws Excecoes {

    String sql = null;
    Saldo_LoteED edVolta = new Saldo_LoteED ();
    double vl_programado = 0;

    try {

      sql =  " SELECT vl_lote_pagamento FROM Lotes_Pagamentos WHERE DM_Situacao <> 'C' ";
      sql += " AND    dt_programada = '" + ed.getDT_Saldo () + "'";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        vl_programado += res.getDouble (1);
      }
      sql = " select vl_saldo from Saldos_Lotes ";
      sql += " where dt_saldo = '" + ed.getDT_Saldo () + "'";
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setVL_Saldo (res.getDouble (1));
      }
      edVolta.setVL_Programado (vl_programado);
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao acessar Saldo");
      excecoes.setMetodo ("getTotalProgramado(Saldo_LoteED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

}
