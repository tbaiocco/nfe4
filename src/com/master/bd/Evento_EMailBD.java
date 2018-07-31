package com.master.bd;

import java.sql.*;
import java.util.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;

public class Evento_EMailBD {

  private ExecutaSQL executasql;

  public Evento_EMailBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Evento_EMailED inclui (Evento_EMailED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    ResultSet rs = null;

    try {

            // System.out.println("inclui ev b1 1");

      sql = "SELECT MAX(Eventos_EMail.OID_Evento) as oid FROM Eventos_EMail";
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        valOid = rs.getLong ("oid") + 1;
      }

      ed.setOid_Evento (valOid);
      sql = "INSERT INTO Eventos_EMail (OID_Evento, " +
          "cd_Evento, nm_Evento, dt_stamp, usuario_stamp) " +
          "VALUES (" + ed.getOid_Evento () + ", '" +
          ed.getCd_Evento () + "', '" +
          ed.getNm_Evento () + "', '" +
          ed.getDt_stamp_tipo () + "', '" +
          ed.getUsuario_stamp () + "')";

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public ArrayList lista (Evento_EMailED ed) throws Excecoes {

    String sql = null;
    ArrayList Lista = new ArrayList ();

    Evento_EMailED edVolta = new Evento_EMailED ();

    try {

      FormataDataBean DataFormatada = new FormataDataBean ();

      sql = " SELECT *  " +
          " FROM Eventos_EMail ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        edVolta = new Evento_EMailED ();
        edVolta.setOid_Evento (res.getLong ("OID_Evento"));
        edVolta.setCd_Evento (res.getString ("CD_Evento"));
        edVolta.setNm_Evento (res.getString ("NM_Evento"));
        Lista.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("lista");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return Lista;
  }

  public boolean deleta (Evento_EMailED ed) throws Excecoes {

    String sql = null;
    boolean ok = true;

    sql = "DELETE FROM Eventos_EMail WHERE oid_Evento='" + ed.getOid_Evento () + "'";

    try {
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta");
      excecoes.setExc (exc);
      ok = false;
      throw excecoes;
    }
    return ok;
  }

  public Evento_EMailED getByRecord (Evento_EMailED ed) throws Excecoes {

    String sql = null;
    FormataDataBean DataFormatada = new FormataDataBean ();
    Evento_EMailED edVolta = new Evento_EMailED ();

    try {
      sql = " SELECT * FROM Eventos_EMail WHERE 1=1 ";

      if (ed.getOid_Evento () > 0) {
        sql += " AND oid_Evento = '" + ed.getOid_Evento () + "'";
      }
      else {
        sql += " AND cd_Evento = '" + ed.getCd_Evento () + "'";
      }

      ResultSet res = this.executasql.executarConsulta (sql);

      if (res.next ()) {
        edVolta.setOid_Evento (res.getLong ("Oid_Evento"));
        edVolta.setCd_Evento (res.getString ("Cd_Evento"));
        edVolta.setNm_Evento (res.getString ("Nm_Evento"));
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("getByRecord");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public void update (Evento_EMailED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "UPDATE Eventos_EMail SET cd_Evento='" + ed.getCd_Evento () + "', " +
          " nm_Evento='" + ed.getNm_Evento () + "' " +
          "WHERE oid_Evento=" + ed.getOid_Evento ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("update");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
