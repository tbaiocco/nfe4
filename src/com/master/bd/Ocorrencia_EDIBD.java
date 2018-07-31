package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Ocorrencia_EDIED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ocorrencia_EDIBD {

  private ExecutaSQL executasql;

  public Ocorrencia_EDIBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_EDIED inclui (Ocorrencia_EDIED ed) throws Excecoes {
    // System.out.println ("bd 1");

    String sql = null;
    long valOid = 0;
    ResultSet rs = null;
    ResultSet res = null;

    try {

      sql = "SELECT MAX(Ocorrencias_edi.OID_Ocorrencia_Edi) as oid FROM Ocorrencias_edi";

      rs = this.executasql.executarConsulta (sql);

//// System.out.println(sql);

      while (rs.next ()) {

        valOid = rs.getLong ("oid") + 1;

      }

      sql = "INSERT INTO Ocorrencias_edi (oid_ocorrencia_edi, oid_padrao, oid_tipo_ocorrencia, " +
          "cd_ocorrencia) " +
          "VALUES (" + valOid + ", " +
          ed.getOid_padrao () + ", " +
          ed.getOid_Tipo_Ocorrencia () + ", '" +
          ed.getCd_Ocorrencia () + "')";

      // System.out.println (sql);

      executasql.executarUpdate (sql);

      ed.setOid_Ocorrencia_Edi (valOid);
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("incluiPadrao");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public ArrayList lista (Ocorrencia_EDIED ed) throws Excecoes {

    String sql = null;
    ArrayList Lista = new ArrayList ();

    Ocorrencia_EDIED edi = new Ocorrencia_EDIED ();

    try {

      FormataDataBean DataFormatada = new FormataDataBean ();

      sql = " select oid_ocorrencia_edi, Ocorrencias_edi.oid_padrao, Ocorrencias_edi.oid_tipo_Ocorrencia, cd_tipo_ocorrencia, cd_ocorrencia, nm_tipo_Ocorrencia, cd_padrao, nm_descricao, dm_tipo_padrao, Dm_tipo_transacao " +
          " from Ocorrencias_edi, tipos_Ocorrencias, padroes_edi " +
          " where Ocorrencias_edi.oid_padrao          = Padroes_EDI.oid_padrao " +
          " and   Ocorrencias_edi.oid_tipo_Ocorrencia = tipos_Ocorrencias.oid_tipo_ocorrencia ";

      // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        // System.out.println ("tem");

        edi = new Ocorrencia_EDIED ();
        edi.setOid_Ocorrencia_Edi (res.getLong ("oid_ocorrencia_edi"));
        edi.setOid_padrao (res.getLong ("oid_padrao"));
        edi.setCd_padrao (res.getString ("cd_padrao"));
        edi.setCd_tipo_ocorrencia (res.getString ("cd_tipo_ocorrencia"));

        edi.setNm_descricao_padrao (res.getString ("nm_descricao"));

        edi.setNm_tipo_Ocorrencia (res.getString ("nm_tipo_Ocorrencia"));

        edi.setCd_Ocorrencia (res.getString ("cd_ocorrencia"));
        edi.setDm_tipo_padrao (res.getString ("Dm_tipo_padrao"));

        edi.setDm_tipo_transacao (res.getString ("Dm_tipo_transacao"));
        if (edi.getDm_tipo_transacao ().equals ("E")) {
          edi.setDm_tipo_transacao ("Exportação");
        }
        if (edi.getDm_tipo_transacao ().equals ("I")) {
          edi.setDm_tipo_transacao ("Importação");
        }
        // System.out.println ("fim");

        Lista.add (edi);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("getByOidPadrao");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return Lista;
  }

  public boolean deleta (Ocorrencia_EDIED ed) throws Excecoes {

    String sql = null;
    boolean ok = true;

    sql = "DELETE FROM Ocorrencias_edi WHERE oid_ocorrencia_edi=" + ed.getOid_Ocorrencia_Edi ();
    // System.out.println (sql);

    try {
      int res = this.executasql.executarUpdate (sql);
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

  public Ocorrencia_EDIED getByRecord (Ocorrencia_EDIED ed) throws Excecoes {

    String sql = null;

    Ocorrencia_EDIED edi = new Ocorrencia_EDIED ();

    try {
      sql = " select oid_ocorrencia_edi, Ocorrencias_edi.oid_padrao, Ocorrencias_edi.oid_tipo_Ocorrencia, cd_tipo_ocorrencia, cd_ocorrencia, nm_tipo_Ocorrencia, cd_padrao, nm_descricao, dm_tipo_padrao, Dm_tipo_transacao " +
          " from Ocorrencias_edi, tipos_Ocorrencias, padroes_edi " +
          " where Ocorrencias_edi.oid_padrao          = Padroes_EDI.oid_padrao " +
          " and   Ocorrencias_edi.oid_tipo_Ocorrencia = tipos_Ocorrencias.oid_tipo_ocorrencia ";

      if (String.valueOf (ed.getOid_padrao ()) != null &&
          !String.valueOf (ed.getOid_padrao ()).equals ("")) {
        sql += " and Ocorrencias_edi.Oid_Ocorrencia_Edi = " + ed.getOid_Ocorrencia_Edi ();

        // System.out.println (sql);
        ResultSet res = null;
        res = this.executasql.executarConsulta (sql);

        FormataDataBean DataFormatada = new FormataDataBean ();

        while (res.next ()) {
          // System.out.println ("tem");

          edi = new Ocorrencia_EDIED ();
          edi.setOid_Ocorrencia_Edi (res.getLong ("oid_ocorrencia_edi"));
          edi.setOid_padrao (res.getLong ("oid_padrao"));
          edi.setCd_padrao (res.getString ("cd_padrao"));
          edi.setCd_tipo_ocorrencia (res.getString ("cd_tipo_ocorrencia"));

          edi.setNm_descricao_padrao (res.getString ("nm_descricao"));

          edi.setNm_tipo_Ocorrencia (res.getString ("nm_tipo_Ocorrencia"));

          edi.setCd_Ocorrencia (res.getString ("cd_ocorrencia"));
          edi.setDm_tipo_padrao (res.getString ("Dm_tipo_padrao"));

          edi.setDm_tipo_transacao (res.getString ("Dm_tipo_transacao"));
          if (edi.getDm_tipo_transacao ().equals ("E")) {
            edi.setDm_tipo_transacao ("Exportação");
          }
          if (edi.getDm_tipo_transacao ().equals ("I")) {
            edi.setDm_tipo_transacao ("Importação");
          }
          // System.out.println ("fim");

        }
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
    return edi;
  }

  public void update (Ocorrencia_EDIED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " update Ocorrencias_edi set cd_ocorrencia='" + ed.getCd_Ocorrencia () + "'" +
            " where oid_Ocorrencia_Edi=" + ed.getOid_Ocorrencia_Edi ();

      // System.out.println (sql);
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
