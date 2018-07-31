package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.ReferenciaED;
import com.master.rl.ReferenciaRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class ReferenciaBD {

  private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria();

  public ReferenciaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }


  public ReferenciaED inclui (ReferenciaED ed) throws Excecoes {
    ed.setOID_Referencia (util.getAutoIncremento ("OID_Referencia" , "Referencias"));

    ReferenciaED toReturn = new ReferenciaED ();

    String sql = " insert into Referencias (OID_Referencia, OID_Produto, CD_Referencia, DT_Stamp, DM_Stamp, Usuario_Stamp, DM_Tipo_Produto, NM_Referencia) values ";
    sql += "(" + ed.getOID_Referencia () + "," + ed.getOID_Produto () + ",'" + ed.getCD_Referencia () + "','" + ed.getDt_stamp () + "','" + ed.getDm_Stamp () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Tipo_Produto () + "', '" + ed.getNM_Referencia () + "')";

    try {
      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui(ReferenciaED ed)");
    }
    toReturn = getByRecord (ed);
    return toReturn;
  }

  public void altera (ReferenciaED ed) throws Excecoes {

    String sql = " update Referencias " +
        " set oid_Produto = " + ed.getOID_Produto () +
        "    ,CD_Referencia = '" + ed.getCD_Referencia () + "'" +
        "    ,NM_Referencia = '" + ed.getNM_Referencia () + "'" +
        "    ,DM_TIPO_PRODUTO = '" + ed.getDM_Tipo_Produto () + "' ";
    sql += " where oid_Referencia = " + ed.getOID_Referencia ();
    try {
      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera(ReferenciaED ed)");
    }
  }

  public void deleta (ReferenciaED ed) throws Excecoes {
    String sql = "delete from Referencias WHERE oid_Referencia = ";
    sql += "(" + ed.getOID_Referencia () + ")";
    try {
      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleta(ReferenciaED ed)");
    }
  }

  public List lista (ReferenciaED ed) throws Excecoes {
    String sql = " select * from Referencias, Produtos  ";
    sql += " where Produtos.oid_Produto = Referencias.oid_Produto ";
    if (util.doValida (ed.getCD_Referencia ())) {
      sql += " and Referencias.CD_Referencia = '" + ed.getCD_Referencia () + "'";
    }
    if (util.doValida (ed.getNM_Produto ())) {
      sql += " and Produtos.NM_Produto LIKE  '" + ed.getNM_Produto () + "%'";
    }
    if (ed.getOID_Produto () > 0) {
      sql += " and Referencias.OID_Produto = " + ed.getOID_Produto ();
    }
    sql += " Order by Produtos.NM_Produto, Referencias.NM_Referencia ";

    // System.out.println(sql);
    
    ResultSet res = this.executasql.executarConsulta (sql);

    FormataDataBean DataFormatada = new FormataDataBean ();

    List toReturn = new ArrayList ();

    try {
      while (res.next ()) {
        ReferenciaED edVolta = new ReferenciaED ();
        edVolta.setOID_Referencia (res.getLong ("OID_Referencia"));
        edVolta.setCD_Referencia (res.getString ("CD_Referencia"));
        edVolta.setNM_Referencia (res.getString ("NM_Referencia"));
        edVolta.setOID_Produto (res.getLong ("oid_Produto"));
        edVolta.setCD_Produto (res.getString ("CD_Produto"));
        edVolta.setNM_Produto (res.getString ("NM_Produto"));
        edVolta.setDM_Tipo_Produto (res.getString ("DM_Tipo_Produto"));
        toReturn.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista(ReferenciaED ed)");
    }
    return toReturn;
  }


  public List listaReferencia (ReferenciaED ed) throws Excecoes {
    String sql = " select * from Referencias  ";
    sql += " where Referencias.nm_referencia is not null  ";
    sql += " Order by Referencias.NM_Referencia ";

    // System.out.println(sql);
    ResultSet res = this.executasql.executarConsulta (sql);

    FormataDataBean DataFormatada = new FormataDataBean ();

    List toReturn = new ArrayList ();

    try {
      while (res.next ()) {

    // System.out.println("OK");

        ReferenciaED edVolta = new ReferenciaED ();
        edVolta.setOID_Referencia (res.getLong ("OID_Referencia"));
        edVolta.setCD_Referencia (res.getString ("CD_Referencia"));
        edVolta.setNM_Referencia (res.getString ("NM_Referencia"));
        toReturn.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista(ReferenciaED ed)");
    }
    return toReturn;
  }

  public ReferenciaED getByRecord (ReferenciaED ed) throws Excecoes {
    String sql =
        " select Referencias.OID_Referencia, " +
        "        Referencias.CD_Referencia, " +
        "        Referencias.NM_Referencia, " +
        "        Referencias.DM_TIPO_PRODUTO, " +
        "        Referencias.OID_Produto, " +
        "        Produtos.CD_Produto, " +
        "        Produtos.NM_Produto " +
        " from Referencias, Produtos" +
        " where Produtos.OID_Produto = Referencias.OID_Produto ";
    if (ed.getOID_Referencia () > 0) {
      sql += " and OID_Referencia = " + ed.getOID_Referencia ();
    }
    if (util.doValida (ed.getCD_Referencia ())) {
      sql += " and CD_Referencia = '" + ed.getCD_Referencia () + "'";
    }
    // System.out.println ("SQL Item da nota: " + sql);
    ResultSet res = this.executasql.executarConsulta (sql);
    try {
      ReferenciaED toReturn = new ReferenciaED ();
      if (res.next ()) {
        toReturn.setOID_Referencia (res.getLong ("OID_Referencia"));
        toReturn.setCD_Referencia (res.getString ("CD_Referencia"));
        toReturn.setNM_Referencia (res.getString ("NM_Referencia"));
        toReturn.setDM_Tipo_Produto (res.getString ("DM_Tipo_Produto"));
        toReturn.setOID_Produto (res.getLong ("OID_Produto"));
        toReturn.setNM_Produto (res.getString ("NM_Produto"));
        toReturn.setCD_Produto (res.getString ("CD_Produto"));
      }
      return toReturn;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "getByRecord(ReferenciaED ed)");
    }
  }

  public void geraRelatorio (ReferenciaED ed) throws Excecoes {

    String sql = null;

    ReferenciaED edVolta = new ReferenciaED ();

    try {

      sql = "select * from Referencias where oid_Referencia > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      ReferenciaRL Referencia_rl = new ReferenciaRL ();
      Referencia_rl.geraRelatEstoque (res);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ReferenciaED ed)");
    }

  }
}