package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Pessoa_EDIED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Pessoa_EDIBD {

  private ExecutaSQL executasql;

  public Pessoa_EDIBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Pessoa_EDIED inclui (Pessoa_EDIED pessoa_ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    ResultSet rs = null;
    ResultSet res = null;

    Pessoa_EDIED pessoaED = (Pessoa_EDIED) pessoa_ed;

    try {

      sql = "SELECT MAX(Pessoas_EDI.OID_PESSOA_EDI) as oid FROM Pessoas_EDI";

      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        valOid = rs.getLong ("oid") + 1;
      }
//// System.out.println(valOid);

      pessoaED.setOid_pessoa_edi (valOid);

      sql = "INSERT INTO Pessoas_EDI (oid_padrao_exp, " +
          "oid_padrao_imp, nm_arquivo_imp, nm_arquivo_exp, oid_pessoa, oid_pessoa_edi) " +
          "VALUES (" + pessoaED.getOid_padrao_Exp () + ", " +
          pessoaED.getOid_padrao_Exp () + ", '" +
          pessoaED.getNm_arquivo_Imp () + "', '" +
          pessoaED.getNm_arquivo_Exp () + "', '" +
          pessoaED.getOid_Pessoa () + "'," +
          pessoaED.getOid_pessoa_edi () + ")";

// System.out.println(sql);

      executasql.executarUpdate (sql);
      // System.out.println (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return pessoaED;
  }

  public boolean deleta (Pessoa_EDIED ed) throws Excecoes {

    String sql = null;
    boolean ok = true;

    sql = "DELETE FROM pessoas_edi WHERE oid_pessoa_edi=" + ed.getOid_pessoa_edi ();
    // System.out.println (sql);

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

  public Pessoa_EDIED getByRecord (Pessoa_EDIED ed) throws Excecoes {

    String sql = null;

    Pessoa_EDIED edi = new Pessoa_EDIED ();

    try {

      sql = " select pessoas.oid_Pessoa, nm_arquivo_exp, nm_arquivo_imp, oid_pessoa_edi, oid_padrao_exp, dm_classe, oid_padrao_imp, oid_padrao, nm_razao_social,  cd_padrao, padroes_edi.nm_descricao as nm_descricao_padrao, tipos_padroes_edi.nm_descricao as nm_descricao_tipo, dm_tipo_padrao, dm_tipo_transacao " +
          " from pessoas_edi, pessoas, padroes_edi, tipos_padroes_edi " +
          " where padroes_edi.oid_tipo_padrao = tipos_padroes_edi.oid_tipo_padrao " +
          " and   pessoas_edi.oid_pessoa  = pessoas.oid_pessoa " +
          " and   pessoas_edi.oid_padrao_exp  = padroes_edi.oid_padrao ";

      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("")) {
        sql += " and pessoas_edi.Oid_pessoa_Edi = " + ed.getOid_pessoa_edi ();

        // System.out.println (sql);

        ResultSet res = null;
        res = this.executasql.executarConsulta (sql);

        FormataDataBean DataFormatada = new FormataDataBean ();

        while (res.next ()) {
          edi.setOid_Pessoa (res.getString ("oid_pessoa"));

          edi.setOid_pessoa_edi (res.getLong ("oid_pessoa_edi"));
          edi.setOid_padrao_Imp (res.getLong ("oid_padrao_imp"));
          edi.setOid_padrao_Exp (res.getLong ("oid_padrao_exp"));
          edi.setCd_padrao (res.getString ("cd_padrao"));

          edi.setNm_razao_social (res.getString ("nm_razao_social"));
          edi.setNm_descricao_padrao (res.getString ("NM_DESCRICAO_PADRAO"));
          edi.setNm_descricao_tipo (res.getString ("NM_DESCRICAO_TIPO"));

          edi.setDm_classe (res.getString ("Dm_classe"));

          edi.setDm_tipo_padrao (res.getString ("Dm_tipo_padrao"));

          edi.setDm_tipo_transacao (res.getString ("Dm_tipo_transacao"));
          if (edi.getDm_tipo_transacao ().equals ("E")) {
            edi.setDm_tipo_transacao ("Exportação");
          }
          if (edi.getDm_tipo_transacao ().equals ("I")) {
            edi.setDm_tipo_transacao ("Importação");
          }

          edi.setNm_arquivo_Exp (res.getString ("nm_arquivo_exp"));
          edi.setNm_arquivo_Imp (res.getString ("nm_arquivo_imp"));

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

  public ArrayList getByOidPessoa (Pessoa_EDIED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    Pessoa_EDIED edi = new Pessoa_EDIED ();

    try {
      sql = " select pessoas_edi.oid_pessoa_edi, oid_padrao, cd_padrao, padroes_edi.nm_descricao as nm_descricao_padrao, tipos_padroes_edi.nm_descricao as nm_descricao_tipo, dm_tipo_padrao, dm_tipo_transacao " +
          " from pessoas_edi, padroes_edi, tipos_padroes_edi " +
          " where pessoas_edi.oid_padrao_exp = padroes_edi.oid_padrao " +
          " and   padroes_edi.oid_tipo_padrao = tipos_padroes_edi.oid_tipo_padrao ";

      // System.out.println ("l");

      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
        sql += " and pessoas_edi.Oid_pessoa = '" + ed.getOid_Pessoa () + "'";
      }

      if (String.valueOf (ed.getDm_tipo_transacao ()) != null &&
          !String.valueOf (ed.getDm_tipo_transacao ()).equals ("") &&
          !String.valueOf (ed.getDm_tipo_transacao ()).equals ("null")) {
        sql += " and padroes_edi.Dm_tipo_transacao = '" + ed.getDm_tipo_transacao () + "'";
      }

      // System.out.println ("->" + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
//// System.out.println(sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        edi = new Pessoa_EDIED ();

        edi.setOid_pessoa_edi (res.getLong ("oid_pessoa_edi"));
        edi.setCd_padrao (res.getString ("CD_PADRAO"));
        edi.setNm_descricao_padrao (res.getString ("NM_DESCRICAO_PADRAO"));
        edi.setNm_descricao_tipo (res.getString ("NM_DESCRICAO_TIPO"));

        edi.setDm_tipo_padrao (res.getString ("Dm_tipo_padrao"));
        if (edi.getDm_tipo_padrao ().equals ("PCD")) {
          edi.setDm_tipo_padrao ("PROCEDA");
        }
        if (edi.getDm_tipo_padrao ().equals ("STA")) {
          edi.setDm_tipo_padrao ("SINTRA");
        }

        edi.setDm_tipo_transacao (res.getString ("Dm_tipo_transacao"));
        if (edi.getDm_tipo_transacao ().equals ("E")) {
          edi.setDm_tipo_transacao ("Exportação");
        }
        if (edi.getDm_tipo_transacao ().equals ("I")) {
          edi.setDm_tipo_transacao ("Importação");
        }

        list.add (edi);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("getByOidPessoa");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return list;
  }

  public Pessoa_EDIED getByOidPessoa2 (Pessoa_EDIED ed) throws Excecoes {

    String sql = null;

    Pessoa_EDIED edi = new Pessoa_EDIED ();

    try {
      sql = " select * from pessoas_edi where ";

      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("")) {
        sql += "pessoas_edi.Oid_pessoa = '" + ed.getOid_Pessoa () + "'";

        ResultSet res = null;
        res = this.executasql.executarConsulta (sql);
        //// System.out.println(sql);

        FormataDataBean DataFormatada = new FormataDataBean ();

        while (res.next ()) {
          edi.setOid_Pessoa (res.getString ("oid_pessoa"));
          edi.setOid_padrao_Imp (res.getLong ("oid_padrao_imp"));
          edi.setOid_padrao_Exp (res.getLong ("oid_padrao_exp"));
          edi.setNm_arquivo_Imp (res.getString ("nm_arquivo_imp"));
          edi.setNm_arquivo_Exp (res.getString ("nm_arquivo_exp"));
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("getByOidPessoa");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edi;
  }

  public String getByPasta (String oid_Pessoa , String oid_Padrao , String dm_Imp_Exp) throws Excecoes {

    String sql = null;
    String nm_Pasta = "";

    // System.out.println ("Pessoa->" + oid_Pessoa);
    // System.out.println ("Padrao->" + oid_Padrao);
    // System.out.println ("DM->" + dm_Imp_Exp);

    try {
      sql = " SELECT * FROM pessoas_edi  " +
          " WHERE  pessoas_edi.Oid_pessoa = '" + oid_Pessoa + "'";

      if ("E".equals (dm_Imp_Exp)) {
        sql += " AND    pessoas_edi.oid_padrao_exp = '" + oid_Padrao + "'";
      }
      else {
        sql += " AND    pessoas_edi.oid_padrao_imp = '" + oid_Padrao + "'";
      }

      // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        if ("E".equals (dm_Imp_Exp)) {
          nm_Pasta = res.getString ("nm_arquivo_exp");
        }
        else {
          nm_Pasta = res.getString ("nm_arquivo_imp");
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao getByPasta");
      excecoes.setMetodo ("getByPasta");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return nm_Pasta;
  }

  public void update (Pessoa_EDIED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update pessoas_edi set oid_pessoa='" + ed.getOid_Pessoa () + "', " +
          "oid_padrao_imp=" + ed.getOid_padrao_Imp () + ", " +
          "oid_padrao_exp=" + ed.getOid_padrao_Exp () + ", " +
          "nm_arquivo_imp='" + ed.getNm_arquivo_Imp () + "', " +
          "nm_arquivo_exp='" + ed.getNm_arquivo_Exp () + "' " +
          "where oid_pessoa='" + ed.getOid_Pessoa ()+ "'";

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
