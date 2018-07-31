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
import java.util.ArrayList;

import com.master.ed.Documento_Lote_FaturamentoED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Documento_Lote_FaturamentoBD
    extends Transacao {

  private ExecutaSQL executasql;
  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();

  public Documento_Lote_FaturamentoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void altera (Documento_Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Documentos_Lotes_Faturamentos set ... ";
      sql += " where oid_Documento_Lote_Faturamento = '" + ed.getOID_Documento_Lote_Faturamento () + "'";

      // System.out.println (sql);

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

  public void deleta (Documento_Lote_FaturamentoED ed) throws Excecoes {

    //// System.out.println("delete Documento_Lote_Faturamento ->> " );


    String sql = null;

    try {

      sql = " SELECT vl_faturado, oid_processo, oid_lote_faturamento " +
          " FROM Documentos_Lotes_Faturamentos WHERE oid_Documento_Lote_Faturamento = ";
      sql += "('" + ed.getOID_Documento_Lote_Faturamento () + "')";

      ResultSet res = this.executasql.executarConsulta (sql);
      double VL_Faturado = 0;
      String oid_processo = null;
      long oid_lote_faturamento = 0;
      while (res.next ()) {
        VL_Faturado = res.getDouble ("VL_Faturado");
        oid_processo = res.getString ("oid_processo");
        oid_lote_faturamento = res.getLong ("oid_lote_faturamento");
      }
      if (oid_processo != null && !oid_processo.equals ("null") && !oid_processo.equals ("")) {

        sql = " UPDATE Processos set " +
            " oid_Lote_Faturamento = null" +
            ", VL_Faturado = VL_Faturado - " + VL_Faturado +
            " WHERE oid_Processo = '" + oid_processo + "'";
        // System.out.println ("UPDATE  " + sql);
        executasql.executarUpdate (sql);
      }

      sql = " UPDATE Lotes_Faturamentos set vl_cobranca = vl_cobranca - " + VL_Faturado +
          " WHERE oid_lote_faturamento = " + oid_lote_faturamento;

      executasql.executarUpdate (sql);

      sql = "delete from Documentos_Lotes_Faturamentos WHERE oid_Documento_Lote_Faturamento = ";
      sql += "('" + ed.getOID_Documento_Lote_Faturamento () + "')";

      int i = executasql.executarUpdate (sql);

      sql = " UPDATE Conhecimentos set oid_Lote_Faturamento = 0, NM_Lote_Faturamento = '' " +
          " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      // System.out.println ("UPDATE  " + sql);

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Documento_Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    double vl_total_frete = 0;
    try {

      // System.out.println ("ed.getDM_Tipo_Documento() === " + ed.getDM_Tipo_Documento ());

      if ("CTO".equals (ed.getDM_Tipo_Documento ()) || "C".equals (ed.getDM_Tipo_Documento ())) {
        ed.setDM_Tipo_Documento ("C");
      }
      else if ("OFT".equals (ed.getDM_Tipo_Documento ()) || "O".equals (ed.getDM_Tipo_Documento ())) {
        ed.setDM_Tipo_Documento ("O");
      }
      else if ("MIN".equals (ed.getDM_Tipo_Documento ())) {
        ed.setDM_Tipo_Documento ("MIN");
      }
      else {
        ed.setDM_Tipo_Documento ("P");
      }
      sql = " SELECT * from Documentos_Lotes_Faturamentos " +
            " WHERE  oid_Lote_Faturamento = '" + ed.getOID_Lote_Faturamento () + "'" +
            " Order by NR_Documento ";
      // System.out.println (" lista->> " + sql);

      ResultSet res = null;
      ResultSet res2 = null;

      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        // System.out.println (" lista->> 2");

        Documento_Lote_FaturamentoED edVolta = new Documento_Lote_FaturamentoED ();

        edVolta.setOID_Lote_Faturamento (res.getLong ("OID_Lote_Faturamento"));
        edVolta.setOID_Documento_Lote_Faturamento (res.getString ("OID_Documento_Lote_Faturamento"));

        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edVolta.setNR_Documento (res.getString ("NR_Documento"));
        edVolta.setVL_Faturado (res.getDouble ("vl_faturado"));

        if (res.getString ("oid_Conhecimento") != null &&
            (res.getString ("DM_Tipo_Documento").equals ("C")    ||
             res.getString ("DM_Tipo_Documento").equals ("CTO")  ||
             res.getString ("DM_Tipo_Documento").equals ("MIN"))) {
          sql = " SELECT VL_Total_Frete FROM Conhecimentos " +
                " WHERE Conhecimentos.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

          // System.out.println (" lista cto->> " + sql);

          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            // System.out.println (" lista->> 5");
            vl_total_frete += res2.getDouble ("VL_Total_Frete");
            edVolta.setVL_Total_Frete (res2.getDouble ("VL_Total_Frete"));
          }
          // System.out.println (" lista->> 6");

        }
        // System.out.println (" lista->> 7");

        list.add (edVolta);
      }
//      Documento_Lote_FaturamentoED edVolta = new Documento_Lote_FaturamentoED ();
//      edVolta.setOID_Documento_Lote_Faturamento ("");
//      edVolta.setOID_Conhecimento ("");
//      edVolta.setNR_Documento ("Total---->>");
//      edVolta.setVL_Total_Frete (vl_total_frete);
//      list.add (edVolta);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Documento_Lote_FaturamentoED getByRecord (Documento_Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;

    Documento_Lote_FaturamentoED edVolta = new Documento_Lote_FaturamentoED ();

    try {

      sql = " SELECT " +
          " Documentos_Lotes_Faturamentos.OID_Lote_Faturamento, " +
          " Documentos_Lotes_Faturamentos.OID_Documento_Lote_Faturamento, " +
          " Documentos_Lotes_Faturamentos.oid_Conhecimento, " +
          " Documentos_Lotes_Faturamentos.NR_Documento, " +
          " Documentos_Lotes_Faturamentos.vl_faturado, " +
          " Documentos_Lotes_Faturamentos.DM_Tipo_Documento, " +
          " Lotes_Faturamentos.oid_unidade, " +
          " Lotes_Faturamentos.dt_emissao, " +
          " Lotes_Faturamentos.oid_pessoa " +
          " from Documentos_Lotes_Faturamentos, lotes_faturamentos " +
          " WHERE Documentos_Lotes_Faturamentos.OID_Lote_Faturamento = lotes_faturamentos.OID_Lote_Faturamento" ;
          if (ed.getOID_Documento_Lote_Faturamento () != null && ed.getOID_Documento_Lote_Faturamento () != "" && ed.getOID_Documento_Lote_Faturamento () != "null"){
            sql +=" AND Documentos_Lotes_Faturamentos.OID_Documento_Lote_Faturamento = '" + ed.getOID_Documento_Lote_Faturamento () + "'";
          }else {
            sql +=" AND Documentos_Lotes_Faturamentos.OID_Lote_Faturamento = '" + ed.getOID_Lote_Faturamento () + "'" +
                  " AND Documentos_Lotes_Faturamentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'" ;
          }


      // System.out.println (" lista->> " + sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Lote_Faturamento (res.getLong ("OID_Lote_Faturamento"));
        edVolta.setOID_Documento_Lote_Faturamento (res.getString ("OID_Documento_Lote_Faturamento"));
        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edVolta.setNR_Documento (res.getString ("NR_Documento"));
        edVolta.setDM_Tipo_Documento (res.getString ("DM_Tipo_Documento"));
        edVolta.setVL_Faturado (res.getDouble ("vl_faturado"));
        edVolta.setOID_Unidade (res.getLong ("oid_unidade"));
        edVolta.setOID_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        // System.out.println (" lista->> 4");

        if (res.getString ("oid_Conhecimento") != null &&
            (res.getString ("DM_Tipo_Documento").equals ("C") ||
             res.getString ("DM_Tipo_Documento").equals ("CTO") ||
             res.getString ("DM_Tipo_Documento").equals ("MIN"))) {
          sql = " SELECT VL_Total_Frete from Conhecimentos " +
              " WHERE Conhecimentos.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

          // System.out.println (" lista cto->> " + sql);

          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            // System.out.println (" lista->> 5");

            edVolta.setVL_Total_Frete (res2.getDouble ("VL_Total_Frete"));
          }
          // System.out.println (" lista->> 6");

        }
        else {
          sql = " SELECT " +
              " Documentos_Lotes_Faturamentos.oid_Conhecimento " +
              " from Documentos_Lotes_Faturamentos " +
              " WHERE Documentos_Lotes_Faturamentos.dm_tipo_documento = 'C'" +
              " AND Documentos_Lotes_Faturamentos.OID_Lote_Faturamento = " + edVolta.getOID_Lote_Faturamento ();
          ResultSet resLocal = null;
          resLocal = this.executasql.executarConsulta (sql);
          while (resLocal.next ()) {
            edVolta.setOID_Conhecimento (resLocal.getString ("oid_Conhecimento"));
          }
        }
        // System.out.println (" lista->> 7");
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Documento_Lote_FaturamentoED inclui (Documento_Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;
    String chave = null;
    Documento_Lote_FaturamentoED edVolta = new Documento_Lote_FaturamentoED ();
    ResultSet res = null;

    try {

      chave = (String.valueOf (ed.getOID_Lote_Faturamento ()) + String.valueOf (System.currentTimeMillis ()));

      // System.out.println (" inclui->> " + chave);

      sql = " insert into Documentos_Lotes_Faturamentos (OID_Documento_Lote_Faturamento, NR_Documento, DM_Tipo_Documento, OID_Conhecimento, OID_Lote_Faturamento, OID_Processo, VL_Faturado, OID_Ordem_Frete_Terceiro, vl_original) values ";
      sql += "('" + chave + "','" + ed.getNR_Documento () + "','" + ed.getDM_Tipo_Documento () + "','" + ed.getOID_Conhecimento () + "'," + ed.getOID_Lote_Faturamento () + "," + ed.getOID_Processo () + "," + ed.getVL_Faturado () + ",'" + ed.getOID_Ordem_Frete_Terceiro () + "'," + ed.getVL_Total_Frete() + ")";

//      System.out.println ("DOC LOTE FAT >> " + sql);

      executasql.executarUpdate (sql);

      sql = " UPDATE Lotes_Faturamentos set vl_cobranca = vl_cobranca + " + ed.getVL_Faturado () +
          " WHERE oid_lote_faturamento = " + ed.getOID_Lote_Faturamento ();

      executasql.executarUpdate (sql);

      //Cto
      if (ed.getOID_Conhecimento () != null && ed.getOID_Conhecimento ().length () > 4) {
        sql = " UPDATE Conhecimentos set NM_Lote_Faturamento = '" + String.valueOf (ed.getOID_Lote_Faturamento ()) + "'" +
              " ,oid_Lote_Faturamento = '" + String.valueOf (ed.getOID_Lote_Faturamento ()) + "'" +
              " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        // System.out.println ("UPDATE  " + sql);
        executasql.executarUpdate (sql);
      }

      //Processos
      if (ed.getOID_Processo () != null && !ed.getOID_Processo ().equals ("null")) {

        sql = " SELECT sum (VL_Faturado) as VL_Faturado " +
              " FROM Processos WHERE oid_Processo = " + ed.getOID_Processo ();

        // System.out.println ("UPDATE  " + sql);

        res = this.executasql.executarConsulta (sql);
        double VL_Faturado = 0;
        while (res.next ()) {
          VL_Faturado = res.getDouble ("VL_Faturado");
        }
        VL_Faturado += ed.getVL_Faturado ();

        sql = " UPDATE Processos set oid_Lote_Faturamento = " + ed.getOID_Lote_Faturamento () + ", VL_Faturado = " + VL_Faturado +
              " WHERE oid_Processo = '" + ed.getOID_Processo () + "'";
        // System.out.println ("UPDATE  " + sql);
        executasql.executarUpdate (sql);
      }

      //OF.Terceiro
      if (ed.getOID_Ordem_Frete_Terceiro () != null && !ed.getOID_Ordem_Frete_Terceiro ().equals ("null")) {

        sql = " UPDATE Ordens_Fretes set oid_Lote_Faturamento = " + ed.getOID_Lote_Faturamento () +
              " WHERE oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete_Terceiro () + "'";

        // System.out.println ("UPDATE  " + sql);
        executasql.executarUpdate (sql);
      }

      edVolta.setOID_Documento_Lote_Faturamento (chave);

      // System.out.println ("kieli 2 incluiu Documento_Lote_Faturamento");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public Documento_Lote_FaturamentoED inclui_Cto_Filtro (Documento_Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    try {

      sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete " +
          " FROM   Conhecimentos " +
          " WHERE  Conhecimentos.VL_Total_Frete >0 " +
          " AND    Conhecimentos.DM_Situacao <>'C'  " +
          " AND    Conhecimentos.DM_Impresso = 'S'  " +
          " AND   (Conhecimentos.oid_Lote_Faturamento is null OR Conhecimentos.oid_Lote_Faturamento =0) " +
          " AND   (Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa () + "' OR Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "')"  ;

      if ("MIN".equals (ed.getDM_Tipo_Documento ())) {
        sql += " AND    Conhecimentos.DM_Tipo_Documento ='M'  ";
      }
      else {
        sql += " AND    Conhecimentos.DM_Tipo_Documento ='C'  ";
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Unidade = '" + ed.getOID_Unidade () + "'";
      }

      if (String.valueOf (ed.getOID_Modal ()) != null &&
          !String.valueOf (ed.getOID_Modal ()).equals ("") &&
          !String.valueOf (ed.getOID_Modal ()).equals ("0") &&
          !String.valueOf (ed.getOID_Modal ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Modal = '" + ed.getOID_Modal () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial ();
      }

      if (String.valueOf (ed.getNR_Conhecimento_Final ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final ();
      }

      // System.out.println("filtro Ctos Manif " + sql);

      int qt_cto=0;
      res = this.executasql.executarConsulta(sql);

      while (res.next ()) {
        qt_cto++;

        // System.out.println ("Lendo Cto => " + qt_cto + "  " + res.getString ("oid_Conhecimento"));

        Documento_Lote_FaturamentoED edLote = new Documento_Lote_FaturamentoED ();
        edLote.setOID_Lote_Faturamento (ed.getOID_Lote_Faturamento ());
        edLote.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edLote.setNR_Documento (res.getString ("NR_Conhecimento"));
        edLote.setDM_Tipo_Documento (ed.getDM_Tipo_Documento());
        edLote.setVL_Faturado (res.getDouble ("vl_total_frete"));

        inclui(edLote);

      }

      // System.out.println ("incluiu Documento_Lote_Faturamento");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui_Cto_Filtro");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

}
