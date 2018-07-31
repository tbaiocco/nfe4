package com.master.bd;

/**
 * Título: Item_Orcamento_CompraBD
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.ed.Orcamento_CompraED;
import java.util.*;
import java.sql.*;

import com.master.root.FormataDataBean;

public class Orcamento_CompraBD {

  private ExecutaSQL executasql;

  public Orcamento_CompraBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Orcamento_CompraED inclui (Orcamento_CompraED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    Orcamento_CompraED Orcamento_CompraED = new Orcamento_CompraED ();

    try {

      String oid = String.valueOf (System.currentTimeMillis ()).toString () + String.valueOf (ed.getoid_Orcamento_Compra ());

      valOid = new Long (oid.substring (oid.length () - 11 , oid.length ())).longValue ();

      sql = "INSERT INTO Orcamentos_Compras (" +
          " oid_Orcamento_Compra," +
          " oid_Solicitacao_Compra," +
          " oid_Orcamento_Conta," +
          " vl_Compra " +
          ")";
      sql += " values ( ";
      sql += valOid + "," +
          ed.getoid_Solicitacao_Compra () + ",'" +
          ed.getoid_Orcamento_Conta () + "'," +
          ed.getvl_Compra () + ")";

// System.out.println(sql);

      int i = executasql.executarUpdate (sql);
      Orcamento_CompraED.setoid_Orcamento_Compra(valOid);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir!");
      excecoes.setMetodo ("inclui()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return Orcamento_CompraED;

  }

  public void deleta (Orcamento_CompraED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from Orcamentos_Compras WHERE oid_Orcamento_Compra = ";
      sql += "" + ed.getoid_Orcamento_Compra () + "";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public Orcamento_CompraED getByRecord (Orcamento_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Orcamento_CompraED edVolta = new Orcamento_CompraED ();

    try {

      sql = " SELECT * FROM Orcamentos_Compras, Orcamentos_Contas, Contas " +
          " WHERE  Orcamentos_Compras.oid_Orcamento_Conta = Orcamentos_Contas.oid_Orcamento_Conta " +
          " AND    Orcamentos_Contas.oid_Conta = Contas.oid_Conta " +
          " AND    Orcamentos_Compras.oid_Orcamento_Compra = " + ed.getoid_Orcamento_Compra ();

      // System.out.println(sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Orcamento_CompraED ();
        edVolta.setoid_Orcamento_Compra (res.getLong ("oid_Orcamento_Compra"));
        edVolta.setoid_Solicitacao_Compra (res.getLong ("oid_Solicitacao_Compra"));
        edVolta.setoid_Orcamento_Conta (res.getString ("oid_Orcamento_Conta"));
        edVolta.setvl_Compra (res.getDouble ("vl_Compra"));
        edVolta.setDm_Orcado(res.getString("Dm_Orcado"));
        edVolta.setOid_Conta(res.getLong("oid_Conta"));
        edVolta.setCd_Conta(res.getString("cd_Conta"));
        edVolta.setNm_Conta(res.getString("nm_Conta"));
        edVolta.setVl_Orcado(res.getDouble("Vl_Orcado"));

      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList Lista (Orcamento_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    ResultSet res = null;
    Orcamento_CompraED edVolta = new Orcamento_CompraED ();

    try {

      sql = " SELECT * FROM Orcamentos_Compras, Orcamentos_Contas, Contas " +
          	" WHERE  Orcamentos_Compras.oid_Orcamento_Conta = Orcamentos_Contas.oid_Orcamento_Conta " +
          	" AND    Orcamentos_Contas.oid_Conta = Contas.oid_Conta " ;

      if (String.valueOf (ed.getoid_Solicitacao_Compra ()) != null &&
          !String.valueOf (ed.getoid_Solicitacao_Compra ()).equals ("null") &&
          !String.valueOf (ed.getoid_Solicitacao_Compra ()).equals ("0")) {
        sql += " and oid_Solicitacao_Compra = " + ed.getoid_Solicitacao_Compra ();
      }
      if (String.valueOf (ed.getoid_Orcamento_Conta ()) != null &&
          !String.valueOf (ed.getoid_Orcamento_Conta ()).equals ("null") &&
          !String.valueOf (ed.getoid_Orcamento_Conta ()).equals ("0")) {
        sql += " and Orcamentos_Compras.oid_Orcamento_Conta = '" + ed.getoid_Orcamento_Conta () + "'";
      }

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Orcamento_CompraED ();

        edVolta.setoid_Orcamento_Compra (res.getLong ("oid_Orcamento_Compra"));
        edVolta.setoid_Solicitacao_Compra (res.getLong ("oid_Solicitacao_Compra"));
        edVolta.setoid_Orcamento_Conta (res.getString ("oid_Orcamento_Conta"));
        edVolta.setvl_Compra (res.getDouble ("vl_Compra"));
        edVolta.setDm_Orcado(res.getString("Dm_Orcado"));
        edVolta.setOid_Conta(res.getLong("oid_Conta"));
        edVolta.setCd_Conta(res.getString("cd_Conta"));
        edVolta.setNm_Conta(res.getString("nm_Conta"));
        edVolta.setVl_Orcado(res.getDouble("Vl_Orcado"));
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

}
