package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Fatura_CompromissoED;
import com.master.rl.Fatura_CompromissoRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class Fatura_CompromissoBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  FormataDataBean DataFormatada = new FormataDataBean ();

  public Fatura_CompromissoBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public Fatura_CompromissoED inclui (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;

    try {
      long Oid_Fatura_Compromisso = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (3 , 12)).longValue ());
    
      ed.setOid_Fatura_Compromisso (new Integer (Long.toString (Oid_Fatura_Compromisso)));

      sql = " INSERT INTO Faturas_Compromissos(" +
          "     OID_Fatura_Compromisso" +
          "     ,NR_DOCUMENTO" +
          "     ,DT_EMISSAO" +
          "     ,TX_OBSERVACAO" +
          "     ,DT_STAMP" +
          "     ,USUARIO_STAMP" +
          "     ,OID_Pessoa" +
          "     ,DM_Imprimir" +
          "     ,DM_STAMP" +
          "     ,OID_Unidade" +
          "     ,OID_Compromisso" +
          "     ,VL_Fatura_Compromisso" +
          "     ,DM_Situacao)" +
          " VALUES ('"
          + Oid_Fatura_Compromisso + "',";
      sql += ed.getNr_Documento () == null ? "null," : "'" + ed.getNr_Documento () + "',";
      sql += "'" + ed.getDt_Emissao () + "',";
      sql += ed.getTx_Observacao () == null ? "null," : "'" + ed.getTx_Observacao () + "',";
      sql += "'" + ed.getDt_stamp () + "',";
      sql += "'" + ed.getUsuario_Stamp () + "',";
      sql += "'" + ed.getOid_Pessoa () + "',";
      sql += "'" + ed.getDM_Imprimir () + "',";
      sql += "'" + ed.getDm_Stamp () + "',";
      sql += ed.getOid_Unidade () + ",";
      sql += ed.getOID_Compromisso () + ",";
      sql += ed.getVl_Fatura_Compromisso () + ",";
      sql += "'I'" + ")";

      // System.out.println (sql);

      executasql.executarUpdate (sql);

      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
  }

  public Fatura_CompromissoED incluiCompromissoVinculado (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;

    try {
      sql =" UPDATE Compromissos SET " +
           " Oid_Fatura_Compromisso= " + ed.getOid_Fatura_Compromisso() +
           " , dt_liberado = null " +
           " WHERE oid_Compromisso = " + ed.getOID_Compromisso();

      // System.out.println (sql);

      executasql.executarUpdate (sql);

      sql =" UPDATE Faturas_Compromissos SET vl_fatura_compromisso= " + 
             totalizaCompromissoVinculado(ed.getOid_Fatura_Compromisso().intValue()) + 
           " WHERE oid_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso();
      // System.out.println (sql);

      executasql.executarUpdate (sql);


      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
  }


  public Fatura_CompromissoED getByRecord (Fatura_CompromissoED ed) throws Excecoes {

    Fatura_CompromissoED edVolta = new Fatura_CompromissoED ();
    try {
      String sql = " SELECT Faturas_Compromissos.* " +
          "       ,Unidades.oid_unidade " +
          "       ,Unidades.cd_unidade " +
          "       ,Pessoas.NR_CNPJ_CPF" +
          "       ,Pessoas.NM_Razao_Social" +
          "       ,Pessoa_Unidade.NM_Fantasia as NM_Fantasia_Unidade " +
          " FROM Faturas_Compromissos, " +
          "      Unidades, " +
          "      Pessoas, " +
          "      Pessoas Pessoa_Unidade " +
          " WHERE Faturas_Compromissos.oid_unidade = unidades.oid_unidade " +
          "   and Faturas_Compromissos.oid_Pessoa  = Pessoas.oid_Pessoa " + 
          "   and Unidades.oid_Pessoa              = Pessoa_Unidade.oid_Pessoa ";

      if (ed.getOid_Fatura_Compromisso () != null && ed.getOid_Fatura_Compromisso ().intValue () > 0) {
        sql += " and Faturas_Compromissos.oid_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso ();
      }

      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
      // System.out.println ("w1");

        edVolta = new Fatura_CompromissoED ();
        edVolta.setOid_Fatura_Compromisso (new Integer (res.getInt ("oid_Fatura_Compromisso")));

        edVolta.setOid_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNr_CNPJ_CPF (res.getString ("Nr_CNPJ_CPF"));
        edVolta.setOid_Unidade (new Long (res.getInt ("oid_unidade")));
        edVolta.setOID_Compromisso(res.getInt ("Oid_Compromisso"));
        edVolta.setCd_Unidade (res.getString ("cd_unidade"));
        edVolta.setNm_Fantasia (res.getString ("NM_Fantasia_Unidade"));
        edVolta.setDM_Imprimir (res.getString ("DM_Imprimir"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        edVolta.setNr_Fatura_Compromisso (new Integer (res.getInt ("oid_Fatura_Compromisso")));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setNr_Documento (res.getString ("nr_documento"));

        if (edVolta.getNr_Documento () == null) {
          edVolta.setNr_Documento ("");
        }

        String tx_Obs = res.getString ("tx_observacao");
        if (tx_Obs != null) {
          edVolta.setTx_Observacao (tx_Obs);
        }

        edVolta.setVl_Fatura_Compromisso (new Double (res.getDouble ("vl_Fatura_Compromisso")));
      // System.out.println ("w99");

      }
      return edVolta;

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
  }

  public void altera (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " UPDATE Faturas_Compromissos SET ";

      if (ed.getNr_Documento () == null) {
        sql += "nr_documento = null,";
      }
      else {
        sql += " nr_documento = '" + ed.getNr_Documento () + "',";
      }

      if (ed.getDt_Emissao () == null) {
        sql += "DT_Emissao = null,";
      }
      else {
        sql += "DT_Emissao = '" + ed.getDt_Emissao () + "',";
      }

      if (ed.getTx_Observacao () == null) {
        sql += "tx_observacao = null,";
      }
      else {
        sql += "tx_observacao = '" + ed.getTx_Observacao () + "',";
      }

      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "',";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "',";
      sql += " DM_Imprimir = '" + ed.getDM_Imprimir () + "'";
      sql += " WHERE oid_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso ();
      // System.out.println (sql);

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
  }

  public void desvinculaTudo (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    String oid_Compromisso=null;

    try {

      sql = " SELECT Compromissos.oid_Compromisso " +
            " FROM   Faturas_Compromissos, Compromissos " +
            " WHERE  Faturas_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
            " AND    Compromissos.vl_saldo = Compromissos.vl_compromisso " +
            " AND    Faturas_Compromissos.oid_Fatura_Compromisso = '" + ed.getOid_Fatura_Compromisso() + "'";
        // System.out.println (sql);

      res = this.executasql.executarConsulta (sql);

      if (res.next ()) {
        oid_Compromisso=res.getString ("oid_Compromisso");

        sql = " SELECT oid_Compromisso, VL_Compromisso " +
              " FROM   Compromissos " +
              " WHERE  Compromissos.oid_Fatura_Compromisso = '" + ed.getOid_Fatura_Compromisso () + "'";
        res = this.executasql.executarConsulta (sql);

        while (res.next ()) {
          // System.out.println ("Compromisso-> " +res.getString ("oid_Compromisso") );


          sql = " UPDATE Compromissos SET VL_Saldo=" + res.getDouble ("VL_Compromisso") + " ,oid_Fatura_Compromisso=null, oid_Compromisso_Vincula=null " +
                " WHERE OID_Compromisso = '" + res.getString ("oid_Compromisso") + "'";
            // System.out.println (sql);

          executasql.executarUpdate (sql);

        }

        sql = " UPDATE Compromissos SET oid_Compromisso_Vincula=null " +
              " WHERE oid_Compromisso_Vincula = '" + oid_Compromisso + "'";
        // System.out.println (sql);
      
        executasql.executarUpdate (sql);


        sql = " DELETE FROM Compromissos " +
              " WHERE  OID_Compromisso = '" + oid_Compromisso + "'";
  
        // System.out.println (sql);
        executasql.executarUpdate (sql);


        sql = " UPDATE Faturas_Compromissos SET vl_fatura_compromisso = 0, oid_Compromisso = null " +
              " WHERE oid_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso ();
        // System.out.println (sql);
        executasql.executarUpdate (sql);

      }

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
  }


  public void deleta (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    try {

      sql = " SELECT Compromissos.oid_compromisso " +
            " FROM  Compromissos " +
            " WHERE Compromissos.oid_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso ();

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        sql = " UPDATE Compromissos SET oid_Fatura_Compromisso=null ";
        sql += " WHERE OID_Compromisso = '" + res.getString ("OID_Compromisso") + "'";
        executasql.executarUpdate (sql);
      }

      sql = " DELETE FROM Faturas_Compromissos " +
            " WHERE oid_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso ();

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public void excluiCompromisso (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    try {

      sql = " UPDATE Compromissos SET oid_Fatura_Compromisso=null ";
      sql += " WHERE OID_Compromisso = '" + ed.getOID_Compromisso() + "'";
      executasql.executarUpdate (sql);

      sql =" UPDATE Faturas_Compromissos SET vl_fatura_compromisso= " + 
             totalizaCompromissoVinculado(ed.getOid_Fatura_Compromisso().intValue()) + 
           " WHERE oid_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso();
      // System.out.println (sql);

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }


  public ArrayList lista (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT * " +
          " FROM Faturas_Compromissos" +
          "     ,Pessoas" +
          " WHERE Faturas_Compromissos.oid_Pessoa = pessoas.oid_pessoa ";

      if (util.doValida (ed.getOid_Pessoa ()) || ed.getNR_Compromisso () != null || ed.getOID_Compromisso () > 0) {
        sql = " SELECT * " +
            " FROM Faturas_Compromissos" +
            "     ,compromissos" +
            "     ,Pessoas" +
            " WHERE Faturas_Compromissos.oid_Fatura_Compromisso = compromissos.oid_Fatura_Compromisso " +
            "   and Faturas_Compromissos.oid_pessoa = pessoas.oid_pessoa ";

        if (util.doValida (ed.getOid_Pessoa ())) {
          sql += " and compromissos.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
        }
        if (ed.getNR_Compromisso () != null && ed.getNR_Compromisso ().intValue () > 0) {
          sql += " and compromissos.NR_Compromisso = " + ed.getNR_Compromisso ();
        }
        if (ed.getOID_Compromisso () > 0) {
          sql += " and compromissos.OID_Compromisso = " + ed.getOID_Compromisso ();
        }
      }
      if (util.doValida (ed.getDT_Emissao_Inicial ())) {
        sql += " and Faturas_Compromissos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }
      if (util.doValida (ed.getDT_Emissao_Final ())) {
        sql += " and Faturas_Compromissos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }
      if (ed.getNr_Fatura_Compromisso () != null && ed.getNr_Fatura_Compromisso ().intValue () > 0) {
        sql += " and Faturas_Compromissos.OID_Fatura_Compromisso = " + ed.getNr_Fatura_Compromisso ();
      }
      if (ed.getVl_Fatura_Compromisso () != null && ed.getVl_Fatura_Compromisso ().doubleValue () > 0) {
        sql += " and Faturas_Compromissos.Vl_Fatura_Compromisso = '" + ed.getVl_Fatura_Compromisso () + "'";
      }
      if (util.doValida (ed.getNr_Documento ())) {
        sql += " and Faturas_Compromissos.NR_Documento = '" + ed.getNr_Documento () + "'";
      }

      sql += " ORDER BY Faturas_Compromissos.oid_Fatura_Compromisso, Faturas_Compromissos.DT_Emissao ";

      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        Fatura_CompromissoED edVolta = new Fatura_CompromissoED ();
        edVolta.setOid_Fatura_Compromisso (new Integer (res.getInt ("oid_Fatura_Compromisso")));
        edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNr_Fatura_Compromisso (new Integer (res.getInt ("oid_Fatura_Compromisso")));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setVl_Fatura_Compromisso (new Double (res.getDouble ("VL_Fatura_Compromisso")));
        edVolta.setOID_Compromisso(res.getInt ("Oid_Compromisso"));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());
        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
  }

  public ArrayList listaCompromissoVinculado (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
        sql = " SELECT * " +
            " FROM Compromissos" +
            "     ,Pessoas" +
            " WHERE Compromissos.oid_pessoa = pessoas.oid_pessoa " +
            " AND   Compromissos.OID_Fatura_Compromisso = " + ed.getOid_Fatura_Compromisso();

      sql += " ORDER BY Compromissos.NR_Compromisso ";

      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        Fatura_CompromissoED edVolta = new Fatura_CompromissoED ();
        edVolta.setOID_Compromisso(res.getLong ("OID_Compromisso"));
        edVolta.setOid_Fatura_Compromisso(ed.getOid_Fatura_Compromisso());
        edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNR_Compromisso(new Integer (res.getString ("nr_Compromisso")));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));
        edVolta.setVL_Compromisso(res.getDouble ("VL_Compromisso"));
        edVolta.setVL_Saldo(res.getDouble ("VL_Saldo"));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());
        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
  }

  public byte[] imprime_Fatura_Compromisso (Fatura_CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " SELECT Faturas_Compromissos.oid_Fatura_Compromisso" +
          "     ,Faturas_Compromissos.oid_unidade " +
          "     ,Faturas_Compromissos.nr_documento as nr_documento_lote" +
          "     ,Faturas_Compromissos.vl_Fatura_Compromisso as vl_Fatura_Compromisso" +
          "     ,Faturas_Compromissos.dt_Emissao" +
          "     ,compromissos.vl_compromisso " +
          "     ,compromissos.vl_saldo " +
          "     ,compromissos.nr_documento as nr_documento_compromisso" +
          "     ,compromissos.nr_parcela" +
          "     ,compromissos.nr_compromisso" +
          "     ,pessoas.nm_razao_social  as nm_razao_social" +
          "     ,pessoas_compromisso.nm_razao_social  as nm_razao_social_compromisso" +
          " FROM compromissos" +
          "     ,pessoas " +
          "     ,pessoas pessoas_compromisso" +
          "     ,Faturas_Compromissos" +
          " WHERE Faturas_Compromissos.oid_Fatura_Compromisso = Compromissos.oid_Fatura_Compromisso " +
          "   and Faturas_Compromissos.oid_pessoa     = pessoas.oid_pessoa " +
          "   and compromissos.oid_pessoa             = pessoas_compromisso.oid_pessoa ";

      if (ed.getNr_Fatura_Compromisso () != null && ed.getNr_Fatura_Compromisso ().intValue () > 0) {
        sql += " and Faturas_Compromissos.oid_Fatura_Compromisso >= " + ed.getNr_Fatura_Compromisso ();
      }
      if (ed.getNR_Fatura_Compromisso_Final () != null && ed.getNR_Fatura_Compromisso_Final ().intValue () > 0) {
        sql += " and Faturas_Compromissos.oid_Fatura_Compromisso <= " + ed.getNR_Fatura_Compromisso_Final ();
      }
      sql += " ORDER BY Faturas_Compromissos.oid_Fatura_Compromisso, compromissos.nr_documento, compromissos.nr_parcela";

      return new Fatura_CompromissoRL ().imprime_Fatura_Compromisso (this.executasql.executarConsulta (sql.toString ()) , ed);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "imprime_Fatura_Compromisso()");
    }
  }

  private double totalizaCompromissoVinculado (long oid_Fatura_Compromisso) throws Excecoes {

    String sql = null;
    double vl_total=0;

    try {

      sql=" SELECT SUM (VL_Saldo) as VL_Compromisso" +
            " FROM  Compromissos " +
            " WHERE Compromissos.oid_Fatura_Compromisso = " + oid_Fatura_Compromisso;
      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        vl_total=res.getDouble("VL_Compromisso");
      }

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
    return vl_total;
  }



}
