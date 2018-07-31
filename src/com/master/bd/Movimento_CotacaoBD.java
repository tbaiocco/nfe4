package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.master.ed.Movimento_CotacaoED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Movimento_CotacaoBD
    extends BancoUtil {

  private ExecutaSQL executasql;
  String CD_Tipo_Movimento = null;
  String DM_Tipo_Movimento = null;
  String DM_Totaliza = null;

  public Movimento_CotacaoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Movimento_CotacaoED inclui (Movimento_CotacaoED ed) throws Excecoes {

    // System.out.println ("Movimento Cotacao inclui 1");

    String sql = null;
    Movimento_CotacaoED movimento_CotacaoED = new Movimento_CotacaoED ();

    try {
      String oid=(String.valueOf (System.currentTimeMillis ()));

        sql = " INSERT into Movimentos_Cotacaos ( " +
              " OID_Movimento_Cotacao, " +
              " OID_Modal, " +
              " OID_Cotacao, " +
              " DM_Situacao, " +
              " VL_Coleta, " +
              " VL_Entrega, " +
              " VL_Frete_Peso, " +
              " VL_Frete_Valor, " +
              " VL_Pedagio, " +
              " VL_Taxas, " +
              " VL_Outros1, " +
              " VL_Total_Frete, " +
              " VL_ICMS) " +
              " values " +
              "('" + oid + "','" + 
              ed.getOID_Modal() + ",'" +
              ed.getOID_Cotacao() + "','" +
              ed.getDM_Situacao() + "'," +
              ed.getVL_Coleta() + "," +
              ed.getVL_Entrega() + "," +
              ed.getVL_Frete_Peso() + "," +
              ed.getVL_Frete_Valor() + "," +
              ed.getVL_Pedagio() + "," +
              ed.getVL_Taxas() + "," +
              ed.getVL_Outros1() + "," +
              ed.getVL_Total_Frete() + "," +
              ed.getVL_ICMS() + ")";

        // System.out.println ("Movimento Cotacao inclui 3 " + sql);

    }
    
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
    
    return movimento_CotacaoED;
  }

  public void altera (Movimento_CotacaoED ed) throws Excecoes {

    String sql = null;

    try {

  //    sql = " update Movimentos_Cotacaos set OID_Tipo_Movimento= " + ed.getOID_Tipo_Movimento () + ", DM_Tipo_Movimento = '" + ed.getDM_Tipo_Movimento () + "' , VL_Movimento = " + ed.getVL_Movimento () + " , DT_Movimento_Cotacao = '" + ed.getDT_Movimento_Cotacao () + "' , HR_Movimento_Cotacao = '" + ed.getHR_Movimento_Cotacao () + "'";
  //    sql += " where oid_Movimento_Cotacao = '" + ed.getOID_Movimento_Cotacao () + "'";

      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Movimento_CotacaoED ed) throws Excecoes {
    String sql = null;
    try {

      sql = "delete from Movimentos_Cotacaos WHERE oid_Movimento_Cotacao = ";
      sql += "('" + ed.getOID_Movimento_Cotacao () + "')";

      int i = executasql.executarUpdate (sql);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta(Movimento_CotacaoED ed)");
    }
  }

  public ArrayList lista (Movimento_CotacaoED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    double VL_Margem = 0;

    String sql =" SELECT Movimentos_Cotacaos.*, ";
    // System.out.println ("movs. >> " + sql);

    FormataDataBean DataFormatada = new FormataDataBean ();
    DecimalFormat dec = new DecimalFormat ("###,###,##0.00");
    double VL_Total_Frete = 0;
    double VL_Total_Custo = 0;
    double VL_Ressarcimento = 0;
    ResultSet res = this.executasql.executarConsulta (sql);
    try {
      try {
        //popula
        while (res.next ()) {
          Movimento_CotacaoED edVolta = new Movimento_CotacaoED ();
          edVolta.setOID_Movimento_Cotacao (res.getString ("OID_Movimento_Cotacao"));
          edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
          edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));

          edVolta.setDM_Situacao ("");
          list.add (edVolta);
        }
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Movimento_CotacaoED ed)");
      }
    }
    finally {
      closeResultset (res);
    }

    return list;
  }

  public Movimento_CotacaoED getByRecord (Movimento_CotacaoED ed) throws Excecoes {
    Movimento_CotacaoED edVolta = new Movimento_CotacaoED ();

    String sql =
        " SELECT Movimentos_Cotacaos.OID_Movimento_Cotacao, "+
        " FROM Tipos_Movimentos, Movimentos_Cotacaos, ";
    FormataDataBean DataFormatada = new FormataDataBean ();

    // System.out.println (" getByRecord ok" + sql);

    ResultSet res = this.executasql.executarConsulta (sql);
    try {
      try {
        while (res.next ()) {
          // System.out.println (" getByRecord ok");

          edVolta.setOID_Movimento_Cotacao (res.getString ("OID_Movimento_Cotacao"));
          edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
        }
        return edVolta;
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(Movimento_CotacaoED ed)");
      }
    }
    finally {
      closeResultset (res);
    }
  }


}
