package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Documento_Conta_CorrenteED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.ManipulaString;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class Documento_Conta_CorrenteBD
     {

  private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

  public Documento_Conta_CorrenteBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  
  public Documento_Conta_CorrenteED inclui (Documento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    try {

      ed.setOid_Documento_Conta_Corrente (new Integer (util.getAutoIncremento ("oid_Documento_Conta_Corrente" , "Documentos_Contas_Correntes")));
      sql = " insert into Documentos_Contas_Correntes ";
      sql += "(OID_Documento_Conta_Corrente, oid_Conta_Corrente, OID_Tipo_Documento, Nm_Serie, NR_Inicial, NR_Final, NR_Atual, DT_Stamp, DM_Stamp, Usuario_Stamp)";
      sql += "values (" + ed.getOid_Documento_Conta_Corrente () + ",'" + ed.getoid_Conta_Corrente () + "'," + ed.getOid_Tipo_Documento () + ",'" + ed.getNm_Serie () + "', " + ed.getNr_Inicial () + ", " + ed.getNr_Final ()
          + ", " + ed.getNr_Atual () + ",'" + ed.getDt_stamp () + "','" + ed.getDm_Stamp () + "','" + ed.getUsuario_Stamp () + "')";
      ;
      executasql.executarUpdate (sql);

      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "inclui()");
    }
  }

  public void altera (Documento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " update Documentos_Contas_Correntes set ";
      sql += " Nm_Serie = '" + ed.getNm_Serie () + "', ";
      sql += " Nr_Inicial = " + ed.getNr_Inicial () + ", ";
      sql += " Nr_Final = " + ed.getNr_Final () + ", ";
      sql += " Nr_Atual = " + ed.getNr_Atual () + ", ";
      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "' ";
      sql += " where oid_Documento_Conta_Corrente = " + ed.getOid_Documento_Conta_Corrente ();
      if (util.doValida (ed.getoid_Conta_Corrente ())) {
          sql += " and Documentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getoid_Conta_Corrente () + "'";
      }
      
      // System.out.println(sql);
      
      executasql.executarUpdate (sql);
      if("S".equals(ed.getDm_Padrao())){
        sql = " update Documentos_Contas_Correntes set DM_Padrao='N' ";
        if (util.doValida (ed.getoid_Conta_Corrente ())) {
            sql += " where Documentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getoid_Conta_Corrente () + "'";
        }  
        // System.out.println(sql);
        executasql.executarUpdate (sql);
        sql = " update Documentos_Contas_Correntes set DM_Padrao='S' " +
              " where oid_Documento_Conta_Corrente = " + ed.getOid_Documento_Conta_Corrente ();
        if (util.doValida (ed.getoid_Conta_Corrente ())) {
            sql += " and Documentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getoid_Conta_Corrente () + "'";
        }        
        // System.out.println(sql);
        executasql.executarUpdate (sql);
      }
      
      
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "altera()");
    }
  }

  public void deleta (Documento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    try {

      sql = "delete from Documentos_Contas_Correntes WHERE oid_Documento_Conta_Corrente = ";
      sql += ed.getOid_Documento_Conta_Corrente ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "deleta()");
    }
  }

  public ArrayList lista (Documento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {
      sql = " SELECT * from Documentos_Contas_Correntes, Contas_Correntes, tipos_Documentos  " +
          " WHERE Documentos_Contas_Correntes.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
          " AND Documentos_Contas_Correntes.oid_tipo_Documento = tipos_Documentos.oid_tipo_Documento ";
      if (util.doValida (ed.getoid_Conta_Corrente ())) {
        sql += " and Documentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getoid_Conta_Corrente () + "'";
      }
      if (util.doValida (ed.getNm_Tipo_Documento ())) {
        sql += " and Tipos_Documentos.NM_Tipo_Documento LIKE '%" + ed.getNm_Tipo_Documento () + "%'";
      }

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Documento_Conta_CorrenteED edVolta = new Documento_Conta_CorrenteED ();

        edVolta.setOid_Documento_Conta_Corrente (new Integer (res.getString ("oid_Documento_Conta_Corrente")));
        edVolta.setNm_Serie (res.getString ("Nm_Serie"));
        edVolta.setoid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_Documento"));
        edVolta.setOid_Tipo_Documento (new Integer (res.getString ("oid_tipo_Documento")));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_Documento"));
        edVolta.setNr_Inicial (res.getLong ("Nr_Inicial"));
        edVolta.setNr_Atual (res.getLong ("Nr_Atual"));
        edVolta.setNr_Final (res.getLong ("Nr_Final"));
        edVolta.setDm_Padrao(res.getString("Dm_Padrao"));

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "lista()");
    }
    return list;
  }

  /***************************************************************************
   *
   **************************************************************************/
  public Documento_Conta_CorrenteED getByRecord (Documento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    Documento_Conta_CorrenteED edVolta = new Documento_Conta_CorrenteED ();
    try {

      sql = " SELECT * from Documentos_Contas_Correntes, Contas_Correntes, tipos_Documentos  " +
          " WHERE Documentos_Contas_Correntes.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
          " AND Documentos_Contas_Correntes.oid_tipo_Documento = tipos_Documentos.oid_tipo_Documento ";

      if (ed.getOid_Documento_Conta_Corrente () != null && ed.getOid_Documento_Conta_Corrente ().intValue () > 0) {
        sql += " and Documentos_Contas_Correntes.oid_Documento_Conta_Corrente = " + ed.getOid_Documento_Conta_Corrente ();
      }
      else {
        if (util.doValida (ed.getoid_Conta_Corrente ())) {
          sql += " and Documentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getoid_Conta_Corrente () + "'";
        }
        if (ed.getOid_Tipo_Documento () != null && ed.getOid_Tipo_Documento ().intValue () > 0) {
          sql += " and Documentos_Contas_Correntes.Oid_Tipo_Documento = " + ed.getOid_Tipo_Documento ();
        }
        if (util.doValida (ed.getCd_Tipo_Documento ())) {
          sql += " and Tipos_Documentos.CD_Tipo_Documento = '" + ed.getCd_Tipo_Documento () + "'";
        }
      }
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Documento_Conta_CorrenteED ();

        edVolta.setOid_Documento_Conta_Corrente (new Integer (res.getString ("oid_Documento_Conta_Corrente")));
        edVolta.setNm_Serie (res.getString ("Nm_Serie"));
        edVolta.setoid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_Documento"));
        edVolta.setOid_Tipo_Documento (new Integer (res.getString ("oid_tipo_Documento")));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_Documento"));
        edVolta.setNr_Inicial (res.getLong ("Nr_Inicial"));
        edVolta.setNr_Atual (res.getLong ("Nr_Atual"));
        edVolta.setNr_Final (res.getLong ("Nr_Final"));
        edVolta.setDm_Padrao(res.getString("Dm_Padrao"));
      }

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecord()");
    }

    return edVolta;
  }

  public String consulta_Ultimo_Numero (Documento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    String nr_ultimo_numero = "";
    try {

      sql = " SELECT MAX (oid_lote_pagamento) as ultimo_lote " +
          " FROM   Lotes_Pagamentos, Tipos_Documentos " +
          " WHERE  Lotes_Pagamentos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento " +
          " AND  Tipos_Documentos.DM_Aplicacao ='E' " +
          " AND  Lotes_Pagamentos.DM_Situacao ='L' " +
          " AND  Lotes_Pagamentos.oid_Conta_Corrente ='" + ed.getoid_Conta_Corrente () + "'";
      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        sql = " SELECT nr_documento FROM Lotes_Pagamentos " +
            " WHERE  Lotes_Pagamentos.oid_lote_pagamento=" + res.getLong ("ultimo_lote");
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          nr_ultimo_numero = res.getString ("nr_documento");
        }
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consulta_Ultimo_Numero()");
    }

    return nr_ultimo_numero;
  }

  public void geraRelatorio (Documento_Conta_CorrenteED ed) throws Excecoes {

    // String sql = null;
    //
    // Documento_Conta_CorrenteED edVolta = new
    // Documento_Conta_CorrenteED();
    //
    // try{
    //
    // sql = "select * from Documento_Conta_Correntes where
    // oid_Documento_Conta_Corrente > 0";
    //
    // if (ed.getCD_Documento_Conta_Corrente() != null &&
    // !ed.getCD_Documento_Conta_Corrente().equals("")){
    // sql += " and CD_Documento_Conta_Corrente = '" +
    // ed.getCD_Documento_Conta_Corrente() + "'";
    // }
    // if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
    // sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
    // }
    //
    // ResultSet res = null;
    // res = this.executasql.executarConsulta(sql);
    //
    // Documento_Conta_CorrenteRL Documento_Conta_Corrente_rl = new
    // Documento_Conta_CorrenteRL();
    // Documento_Conta_Corrente_rl.geraRelatEstoque(res);
    // }
    // catch (Excecoes e){
    // throw e;
    // }
    // catch(Exception exc){
    // Excecoes exce = new Excecoes();
    // exce.setExc(exc);
    // exce.setMensagem("Erro no método listar");
    // exce.setClasse(this.getClass().getName());
    // exce.setMetodo("geraRelatorio(Documento_Conta_CorrenteED ed)");
    // }
    //
  }

}
