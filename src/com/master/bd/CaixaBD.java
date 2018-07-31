package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.*;
import java.text.*;
import java.util.*;

import com.master.ed.*;
import com.master.rl.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class CaixaBD
    extends Transacao {

  private ExecutaSQL executasql;

  public CaixaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public CaixaED inclui (CaixaED ed) throws Excecoes {

    String sql = null;
    long valOid = 1;

    CaixaED CaixaED = new CaixaED ();

    try {

      //pega proximo valor da chave
      ResultSet rs = executasql.executarConsulta ("select max(oid_Caixa) as result from Caixas");
      while (rs.next ()) {
        valOid = rs.getLong ("result") + 1;
      }
      // ed.getOid_Caixa(valOid);


      sql = " insert into Caixas " +
          "(OID_Caixa, " +
          "NR_Documento, " +
          "DT_Caixa, " +
          "NM_Complemento_Historico, " +
          "DT_STAMP, " +
          "USUARIO_STAMP, " +
          "DM_Debito_Credito, " +
          "DM_Grupo, " +
          "DM_Tipo_Lancamento, " +
          "DM_Situacao, " +
          "DM_STAMP, " +
          "OID_CONTA, " +
          "OID_Unidade, " +
          "OID_Historico, " +
          "OID_Tipo_Documento, " +
          "VL_Lancamento " +
          ") values " +
          "(" + valOid + ",";
      sql += ed.getNR_Documento () == null ? "null," : "'" + ed.getNR_Documento () + "',";
      sql += "'" + ed.getDT_Caixa () + "',";
      sql += ed.getNM_Complemento_Historico () == null ? "'null'," : "'" + ed.getNM_Complemento_Historico () + "',";
      sql += "'" + ed.getDt_stamp () + "',";
      sql += "'" + ed.getUsuario_Stamp () + "',";
      sql += "'" + ed.getDM_Debito_Credito () + "',";
      sql += "'" + ed.getDM_Grupo () + "',";
      sql += "'" + ed.getDM_Tipo_Lancamento () + "',";
      sql += "'A',";
      sql += "'" + ed.getDm_Stamp () + "',";
      sql += ed.getOID_Conta () + ",";
      sql += ed.getOid_Unidade () + ",";
      sql += ed.getOID_Historico () + ",";
      sql += ed.getOID_Tipo_Documento () + ",";
      sql += ed.getVL_Lancamento () + ")";

      //// System.out.println (sql);

      int i = executasql.executarUpdate (sql);

      //// System.out.println("Passei 1");
      CaixaED.setOid_Caixa (valOid);

      //// System.out.println("Passei 2");
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Caixa");
      excecoes.setMetodo ("Inclui(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    //// System.out.println("Passei 3");
    return CaixaED;

  }

  public CaixaED getByRecord (CaixaED ed) throws Excecoes {

    String sql = null;

    CaixaED edVolta = new CaixaED ();

    try {

      sql = "select * from Caixas, unidades, tipos_documentos, Historicos, contas, pessoas where " +
          " Caixas.oid_conta = contas.oid_conta and" +
          " Caixas.oid_unidade = unidades.oid_unidade and" +
          " Unidades.oid_Pessoa = Pessoas.oid_Pessoa and" +
          " Caixas.oid_Historico = Historicos.oid_Historico and" +
          " Caixas.oid_tipo_documento = tipos_documentos.oid_tipo_documento";
      sql += " and Caixas.oid_Caixa = " + ed.getOid_Caixa ();

      ResultSet res = null;

      FormataDataBean dataFormatada = new FormataDataBean ();

      //// System.out.println(sql);


      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new CaixaED ();
        edVolta.setOid_Caixa (new Long (res.getString ("oid_caixa")).longValue ());

        //// System.out.println("r1");

        edVolta.setCD_Tipo_Documento (res.getString ("CD_Tipo_Documento"));
        //// System.out.println("r2");
        edVolta.setNM_Tipo_Documento (res.getString ("NM_Tipo_Documento"));

        edVolta.setCD_Conta (res.getString ("CD_Conta"));
        edVolta.setNM_Conta (res.getString ("NM_Conta"));
        edVolta.setOID_Conta (res.getString ("oid_conta"));

        edVolta.setCD_Historico (res.getString ("CD_Historico"));
        edVolta.setNM_Historico (res.getString ("NM_Historico"));
        edVolta.setOID_Historico (new Integer (res.getString ("oid_Historico")));

        edVolta.setOid_Unidade (new Long (res.getString ("oid_Unidade")).longValue ());

        //// System.out.println("r7");

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setNM_Fantasia (res.getString ("NM_fantasia"));
        edVolta.setDM_Debito_Credito (res.getString ("DM_Debito_Credito"));
        edVolta.setDM_Grupo (res.getString ("DM_Grupo"));
        edVolta.setDM_Tipo_Lancamento (res.getString ("DM_Tipo_Lancamento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        //// System.out.println("r8");

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("DT_Caixa"));
        edVolta.setDT_Caixa (DataFormatada.getDT_FormataData ());
        edVolta.setNR_Documento (res.getString ("NR_Documento"));

        if (edVolta.getNR_Documento () == null) {
          edVolta.setNR_Documento ("");
        }

        String tx_Obs = res.getString ("NM_Complemento_Historico");
        if (tx_Obs != null) {
          edVolta.setNM_Complemento_Historico (tx_Obs);
        }

        edVolta.setVL_Lancamento (new Double (res.getString ("VL_Lancamento")));

        //// System.out.println("r11");

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao acessar Caixa");
      excecoes.setMetodo ("getByRecord(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void altera (CaixaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Caixas set ";
      sql += " NR_Documento = '" + ed.getNR_Documento () + "',";
      sql += "DT_Caixa = '" + ed.getDT_Caixa () + "',";
      sql += "NM_Complemento_Historico = '" + ed.getNM_Complemento_Historico () + "',";
      sql += " DT_STAMP = '" + ed.getDt_stamp () + "',";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "',";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "',";
      sql += " DM_Debito_Credito = '" + ed.getDM_Debito_Credito () + "',";
      sql += " DM_Grupo = '" + ed.getDM_Grupo () + "',";
      sql += " DM_Tipo_Lancamento = '" + ed.getDM_Tipo_Lancamento () + "',";
      sql += " oid_Historico = " + ed.getOID_Historico () + ",";
      sql += " oid_conta = " + ed.getOID_Conta () + ",";
      sql += " VL_Lancamento = " + ed.getVL_Lancamento () + "";
      sql += " where oid_Caixa = " + ed.getOid_Caixa ();

      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados de Caixa ");
      excecoes.setMetodo ("altera(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void atualiza (CaixaED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    // System.out.println ("atu caixa Unidade:" + ed.getOid_Unidade () + " Data " + ed.getDT_Caixa ());

    try {

      sql = " DELETE FROM Caixas " +
          " WHERE  Caixas.DM_Tipo_Lancamento ='G' " +
          " AND Caixas.oid_Unidade= " + ed.getOid_Unidade () +
          " AND Caixas.DT_Caixa = '" + ed.getDT_Caixa () + "'" +
          " AND Caixas.DM_Grupo = '" + ed.getDM_Grupo () + "'";

      executasql.executarUpdate (sql);

      sql = " SELECT Compromissos.oid_unidade, " +
          "  Compromissos.oid_Compromisso, " +
          " Compromissos.NR_Documento as NR_Doc_Comp, " +
          " Compromissos.NR_Parcela, " +
          " Compromissos.Vl_Compromisso, " +
          " Compromissos.TX_Observacao, " +
          " Pessoas_Compromisso.NM_Razao_Social as NM_Razao_Social_Compromisso , " +
          " Pessoas_Conta.NM_Razao_Social as NM_Razao_Social_Conta , " +
          " Tipo_Documento_Compromisso.CD_Tipo_Documento as Doc_Comp, " +
          " Tipo_Documento_Compromisso.oid_Tipo_Documento as oid_Doc_Comp, " +
          " Tipo_Documento_Lote.CD_Tipo_Documento as Doc_Lote, " +
          " Tipo_Documento_Lote.oid_Tipo_Documento as oid_Doc_Lote, " +
          " Lotes_Pagamentos.oid_Lote_Pagamento as oid_Lote_Pagamento, " +
          " Lotes_Pagamentos.Nr_Documento as NR_Doc_Lote, " +
          " Lotes_Pagamentos.DT_Emissao as DT_Lote, " +
          " Lotes_Pagamentos.VL_Lote_Pagamento, " +
          " Lotes_Compromissos.Vl_Pagamento, " +
          " Lotes_Compromissos.Vl_Juros_Pagamento, " +
          " Lotes_Compromissos.Vl_Desconto, " +
          " Lotes_Compromissos.Vl_Outras_Despesas, " +
          " Contas_Correntes.CD_Conta_Corrente, " +
          " Conta_Debito.oid_Conta as oid_Conta_Debito, " +
          " Conta_Debito.DM_Tipo_Operacao as DM_Tipo_Operacao_Debito, " +
          " Conta_Credito.oid_Conta as oid_Conta_Credito, " +
          " Conta_Credito.DM_Tipo_Operacao as DM_Tipo_Operacao_Credito, " +
          " Conta_Caixa.oid_Conta as oid_Conta_Caixa, " +
          " Conta_Caixa.DM_Tipo_Operacao as DM_Tipo_Operacao_Caixa , " +
          " Historico_Lote.oid_Historico as oid_Historico_lote  , " +
          " Historico_Compromisso.oid_Historico as oid_Historico_comp , " +
          " Historico_Cx.oid_Historico as oid_historico_cx   " +
          " FROM  Historicos  Historico_Lote, Historicos Historico_Compromisso, Historicos Historico_Cx,  Tipos_Documentos Tipo_Documento_Lote, Tipos_Documentos Tipo_Documento_Compromisso, compromissos, contas conta_debito, contas conta_credito, contas conta_caixa,  Lotes_Pagamentos, Lotes_Compromissos, Contas_Correntes, Pessoas Pessoas_Compromisso, Pessoas Pessoas_Conta " +
          " WHERE Compromissos.oid_compromisso = Lotes_Compromissos.oid_Compromisso " +
          " AND   Lotes_Compromissos.oid_lote_Pagamento = Lotes_Pagamentos.oid_lote_Pagamento  " +
          " AND   Lotes_Pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente  " +
          " AND   Lotes_Pagamentos.oid_Tipo_Documento = Tipo_Documento_Lote.oid_Tipo_Documento " +
          " AND   Compromissos.oid_Tipo_Documento = Tipo_Documento_Compromisso.oid_Tipo_Documento " +
          " AND   Compromissos.oid_pessoa = Pessoas_Compromisso.oid_Pessoa " +
          " AND   Contas_Correntes.oid_Conta = conta_credito.oid_conta  " +
          " AND   Contas_Correntes.oid_Pessoa = Pessoas_Conta.oid_Pessoa  " +
          " AND   Conta_credito.oid_Historico =  Historico_Lote.oid_Historico  " +
          " AND   Conta_debito.oid_Historico =  Historico_Compromisso.oid_Historico  " +
          " AND   Conta_caixa.oid_Historico =  Historico_Cx.oid_Historico " +
          " AND   Lotes_Pagamentos.Vl_Lote_Pagamento > 0  " +
          " AND   Compromissos.oid_conta = conta_debito.oid_conta  " + //conta_debito
          " AND   compromissos.oid_conta         = conta_caixa.oid_conta  " +
          " AND Lotes_Pagamentos.oid_Unidade = " + ed.getOid_Unidade () +
          " AND Lotes_Pagamentos.dt_Emissao = '" + ed.getDT_Caixa () + "'" +
          " AND Contas_Correntes.DM_Grupo = '" + ed.getDM_Grupo () + "'" +
          " AND Lotes_Pagamentos.DM_Situacao <> 'C' " +
          " ORDER by Lotes_Pagamentos.oid_lote_pagamento, Lotes_Pagamentos.nr_documento";
          //" ORDER by Lotes_Pagamentos.oid_Tipo_Documento, Lotes_Pagamentos.nr_documento";

      // System.out.println ("atu caixa " + sql);

      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      res = this.executasql.executarConsulta (sql.toString ());

      String NR_Anterior = null;
      String Complemento = null;
      while (res.next ()) {

        // System.out.println ("atu caixa  no whi");

        CaixaED edCaixa = new CaixaED ();

        if (! (res.getString ("oid_Lote_Pagamento").equals (NR_Anterior))) {

          // System.out.println ("atu caixa 2");

          edCaixa.setDT_Caixa (DataFormatada.getDT_FormataData (res.getString ("DT_Lote")));
          edCaixa.setNR_Documento (res.getString ("NR_Doc_Lote"));
          Complemento = res.getString ("Doc_Lote") + " Nr " + res.getString ("NR_Doc_Lote") + " " + res.getString ("NM_Razao_Social_Conta");
          edCaixa.setNM_Complemento_Historico (Complemento);
          edCaixa.setDM_Debito_Credito ("C");
          edCaixa.setDM_Tipo_Lancamento ("G");
          edCaixa.setOID_Conta (res.getString ("oid_Conta_Debito"));
          // System.out.println ("atu caixa 10");

          edCaixa.setOid_Unidade (ed.getOid_Unidade ());
          edCaixa.setOID_Tipo_Documento (new Integer (res.getString ("oid_Doc_Lote")));
          edCaixa.setOID_Historico (new Integer (res.getString ("oid_Historico_Lote")));
          edCaixa.setVL_Lancamento (new Double (res.getString ("VL_Lote_Pagamento")));
          // System.out.println ("atu caixa 11");
          edCaixa.setDM_Grupo (ed.getDM_Grupo ());
          inclui (edCaixa);

          // System.out.println ("atu caixa 11 B");

          if ("S".equals (res.getString ("DM_Tipo_Operacao_Caixa"))) {

            // System.out.println ("atu caixa 20");

            edCaixa.setDT_Caixa (DataFormatada.getDT_FormataData (res.getString ("DT_Lote")));
            edCaixa.setNR_Documento (res.getString ("NR_Doc_Lote"));
            Complemento = " ->>> FUNDO DE CAIXA:" + res.getString ("Doc_Lote") + " Nr " + res.getString ("NR_Doc_Lote") + " " + res.getString ("NM_Razao_Social_Conta");
            edCaixa.setNM_Complemento_Historico (Complemento);
            edCaixa.setDM_Debito_Credito ("D");
            edCaixa.setDM_Tipo_Lancamento ("G");
            edCaixa.setOID_Conta (res.getString ("oid_Conta_Caixa"));
            // System.out.println ("atu caixa 30");
            edCaixa.setOid_Unidade (ed.getOid_Unidade ());
            edCaixa.setOID_Tipo_Documento (new Integer (res.getString ("oid_Doc_Lote")));
            edCaixa.setOID_Historico (new Integer (res.getString ("oid_historico_cx")));
            edCaixa.setVL_Lancamento (new Double (res.getString ("VL_Lote_Pagamento")));
            edCaixa.setDM_Grupo (ed.getDM_Grupo ());
            inclui (edCaixa);
          }
        }
        //NR_Anterior = res.getString ("NR_Doc_Lote");
        NR_Anterior = res.getString ("oid_Lote_Pagamento");

        // System.out.println ("atu caixa 50");

        edCaixa.setDT_Caixa (DataFormatada.getDT_FormataData (res.getString ("DT_Lote")));
        edCaixa.setNR_Documento (res.getString ("NR_Doc_Comp"));
        Complemento = res.getString ("Doc_Comp") + " Nr " + res.getString ("NR_Doc_Comp") + "/" + res.getString ("NR_Parcela") + "  " + res.getString ("NM_Razao_Social_Compromisso"); // +  res.getString("Tx_Observacao");
        edCaixa.setNM_Complemento_Historico (Complemento);
        edCaixa.setDM_Debito_Credito ("D");
        edCaixa.setDM_Tipo_Lancamento ("G");
        // System.out.println ("atu caixa 55");
        edCaixa.setOID_Conta (res.getString ("oid_Conta_Credito"));
        edCaixa.setOid_Unidade (ed.getOid_Unidade ());
        // System.out.println ("atu caixa 60");
        edCaixa.setOID_Tipo_Documento (new Integer (res.getString ("oid_Doc_Comp")));
        edCaixa.setOID_Historico (new Integer (res.getString ("oid_Historico_comp")));
        edCaixa.setVL_Lancamento (new Double (res.getString ("VL_Pagamento")));
        edCaixa.setDM_Grupo (ed.getDM_Grupo ());
  
        if (!"SCX".equals(res.getString("CD_Conta_Corrente"))){
          inclui (edCaixa);
        }

      }

      sql = " UPDATE Saldos set  DM_Situacao = 'A'  " +
          " Where DT_Saldo = '" + ed.getDT_Caixa () + "'" +
          " and  DM_Grupo = '" + ed.getDM_Grupo () + "'" +
          " and  oid_Unidade = '" + ed.getOid_Unidade () + "'";

      // System.out.println ("finaliza 4");

      executasql.executarUpdate (sql);

      // System.out.println ("Atualizou");

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados de Caixa ");
      excecoes.setMetodo ("altera(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void estorna (CaixaED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    // System.out.println ("estorna caixa Unidade:" + ed.getOid_Unidade () + " Data " + ed.getDT_Caixa ());

    try {

      sql = " DELETE FROM Caixas " +
          " WHERE  Caixas.DM_Tipo_Lancamento ='G' " +
          " AND Caixas.oid_Unidade= " + ed.getOid_Unidade () +
          " AND Caixas.DT_Caixa = '" + ed.getDT_Caixa () + "'" +
          " AND Caixas.DM_Grupo = '" + ed.getDM_Grupo () + "'";

      executasql.executarUpdate (sql);

      sql = " DELETE FROM Saldos  " +
          " Where DT_Saldo = '" + ed.getDT_Caixa () + "'" +
          " and  DM_Grupo = '" + ed.getDM_Grupo () + "'" +
          " and  oid_Unidade = '" + ed.getOid_Unidade () + "'";

      // System.out.println ("estorna 4");

      executasql.executarUpdate (sql);

      // System.out.println ("estornou");

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao estorna ");
      excecoes.setMetodo ("estorna(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public void deleta (CaixaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from Caixas WHERE oid_Caixa = ";
      sql += ed.getOid_Caixa ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Caixa");
      excecoes.setMetodo ("deleta(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;

    }
  }

  public ArrayList lista (CaixaED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CaixaED ed = (CaixaED) edComp;

    try {
      sql = "select * from Caixas, Historicos, Contas, tipos_documentos, unidades, pessoas where " +
          " Caixas.oid_tipo_documento = tipos_Documentos.oid_tipo_documento and " +
          " Caixas.oid_unidade = unidades.oid_unidade and" +
          " Unidades.oid_Pessoa = Pessoas.oid_Pessoa and" +
          " Caixas.oid_Historico = Historicos.oid_Historico " +
          " AND Caixas.oid_conta = Contas.oid_conta " +
          " AND Caixas.oid_Unidade= " + ed.getOid_Unidade () +
          " AND Caixas.DM_Grupo = '" + ed.getDM_Grupo () + "'" +
          " AND Caixas.DT_Caixa >= '" + ed.getDT_Inicial () + "'" +
          " AND Caixas.DT_Caixa <= '" + ed.getDT_Final () + "'";

      sql += " ORDER BY Caixas.DT_Caixa, Caixas.oid_caixa ";

      // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        CaixaED edVolta = new CaixaED ();
        //// System.out.println("lista 1");

        edVolta.setOid_Caixa (new Long (res.getString ("oid_caixa")).longValue ());
        //// System.out.println("lista 2");

        edVolta.setNR_Documento (res.getString ("NR_Documento"));
        //// System.out.println("lista 3");
        edVolta.setDM_Tipo_Lancamento (res.getString ("DM_Tipo_Lancamento"));

        edVolta.setDM_Debito_Credito (res.getString ("DM_Debito_Credito"));

        edVolta.setDM_Grupo (res.getString ("DM_Grupo"));
        //// System.out.println("lista 4");
        edVolta.setVL_Lancamento (new Double (res.getString ("VL_Lancamento")));
        //// System.out.println("lista 5");

        FormataDataBean DataFormatada = new FormataDataBean ();
        //// System.out.println("lista 6");

        DataFormatada.setDT_FormataData (res.getString ("DT_Caixa"));
        //// System.out.println("lista 7");
        edVolta.setDT_Caixa (DataFormatada.getDT_FormataData ());
        //// System.out.println("lista 8");
        edVolta.setNM_Conta (res.getString ("NM_Conta"));
        //// System.out.println("lista 9");
        edVolta.setNM_Historico (res.getString ("NM_Historico"));
        //// System.out.println("lista 10");
        edVolta.setCD_Conta (res.getString ("CD_Conta"));
        //// System.out.println("lista 11");

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar dados de Caixa");
      excecoes.setMetodo ("lista(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public byte[] imprime_Caixa (CaixaED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = "select * from Caixas, Historicos, Contas, tipos_documentos where " +
        " Caixas.oid_tipo_documento = tipos_Documentos.oid_tipo_documento and " +
        " Caixas.oid_Historico = Historicos.oid_Historico  " +
        " AND Caixas.oid_Unidade= " + ed.getOid_Unidade () +
        " AND Caixas.DT_Caixa >= '" + ed.getDT_Inicial () + "'" +
        " AND Caixas.DT_Caixa <= '" + ed.getDT_Final () + "'" +
        " AND Caixas.DM_Grupo = '" + ed.getDM_Grupo () + "'" +
        " AND Caixas.oid_conta = Contas.oid_conta ";

    //sql += " ORDER BY Caixas.DT_Caixa, tipos_documentos.CD_Tipo_Documento, Caixas.nr_documento, Caixas.vl_lancamento ";
    sql += " ORDER BY Caixas.DT_Caixa, Caixas.oid_Caixa ";

    // System.out.println (sql);

    CaixaED edVolta = new CaixaED ();

    try {

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      CaixaRL conRL = new CaixaRL ();
      b = conRL.imprime_Caixa (res,ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(CompromissoED ed)");
    }
    return b;
  }

  public CaixaED geraSaldoInicial (CaixaED ed) throws Excecoes {

    CaixaED edVolta = new CaixaED ();

    String sql = null;
    ResultSet rs = null;
    long aux = 0;
    long i = 0;
    double saldoInicial = 0;
    edVolta.setDM_Situacao ("OK");
    String dataUltimoMovimento = "";

    try {

      // System.out.println ("geraSaldoInicial 8 z");

      sql = "Select DT_Saldo from Saldos where oid_Unidade=" + ed.getOid_Unidade () + " and DM_Grupo = '" + ed.getDM_Grupo () + "'";

      // System.out.println ("geraSaldoInicial 10 Y" + sql);

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        // System.out.println ("geraSaldoInicial 10 Y " + dataUltimoMovimento);
        dataUltimoMovimento = "TEM";
      }
      // System.out.println ("geraSaldoInicial 11x");

      if (dataUltimoMovimento.equals ("TEM")) {
        // System.out.println ("geraSaldoInicial 13");

        sql = "Select oid_saldo from Saldos where " +
            " DT_Saldo = '" + ed.getDT_Caixa () + "'" +
            " and DM_Grupo = '" + ed.getDM_Grupo () + "'" +
            " and oid_Unidade = " + ed.getOid_Unidade ();

        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          i++;
          edVolta.setDM_Situacao ("Já foi gerado o saldo inicial para esta data.");
        }

        sql = "Select * from Caixas where " +
            " DT_Caixa = '" + ed.getDT_Caixa () + "'" +
            " and DM_Grupo = '" + ed.getDM_Grupo () + "'" +
            " and oid_Unidade = " + ed.getOid_Unidade ();

        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          i++;
          edVolta.setDM_Situacao ("Já tem lançamentos incluídos nesta data.");
        }

        if (i == 0) {
          // System.out.println ("geraSaldoInicial 20");

          sql = "Select DT_Saldo, vl_saldo_Inicial from Saldos where oid_Unidade=" + ed.getOid_Unidade () + " and DT_Saldo=(Select max(DT_Saldo) from Saldos where oid_Unidade=" + ed.getOid_Unidade () + " and DT_Saldo<'" + ed.getDT_Caixa () + "' and DM_Grupo='" + ed.getDM_Grupo () + "')";

          // System.out.println (sql);

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            FormataDataBean DataFormatada = new FormataDataBean ();
            DataFormatada.setDT_FormataData (rs.getString ("DT_Saldo"));
            dataUltimoMovimento = DataFormatada.getDT_FormataData ();

            saldoInicial = rs.getDouble ("vl_saldo_Inicial");
          }

          // System.out.println ("geraSaldoInicial 21");

          sql = "select * from Caixas where  DT_Caixa = '" + dataUltimoMovimento + "' and DM_Grupo='" + ed.getDM_Grupo () + "' and oid_Unidade = " + ed.getOid_Unidade ();
          // System.out.println ("geraSaldoInicial 21" + sql);

          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            if (rs.getString ("dm_debito_credito").equals ("C")) {
              saldoInicial -= rs.getDouble ("vl_lancamento");
            }
            else {
              saldoInicial += rs.getDouble ("vl_lancamento");
            }
          }
        }
      }
      if (i == 0) {

        aux = System.currentTimeMillis ();
        sql = "Insert into Saldos (oid_saldo, DM_Grupo, DT_Saldo, vl_saldo_inicial, vl_saldo_final, dm_situacao,oid_Unidade) values (" + aux + ",'" + ed.getDM_Grupo () + "','" + ed.getDT_Caixa () + "', " + saldoInicial + ",0.0,'I'," + ed.getOid_Unidade () + ")";
        // System.out.println (sql);

        i = executasql.executarUpdate (sql);
        edVolta.setDM_Situacao ("Saldo Inicial Gerado com Sucesso.");
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados de Caixa ");
      excecoes.setMetodo ("altera(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public CaixaED consultaSaldo (CaixaED ed) throws Excecoes {

    String sql = null;

    CaixaED edVolta = new CaixaED ();

    try {

      sql = "Select DM_Situacao, DT_Saldo, vl_saldo_Inicial, vl_saldo_Final from Saldos where oid_Unidade=" + ed.getOid_Unidade () + " and DT_Saldo=(Select max(DT_Saldo) from Saldos where oid_Unidade=" + ed.getOid_Unidade () + " and DM_Grupo='" + ed.getDM_Grupo () + "')";

      // System.out.println (sql);

      ResultSet res = null;

      FormataDataBean DataFormatada = new FormataDataBean ();

      res = this.executasql.executarConsulta (sql);
      edVolta.setDM_Saldo_Inicial ("");
      edVolta.setDT_Caixa ("01/01/2001");
      edVolta.setVL_Saldo_Inicial (0);
      edVolta.setVL_Saldo_Final (0);
      edVolta.setDM_Situacao ("N");

      while (res.next ()) {
        edVolta = new CaixaED ();
        edVolta.setDM_Saldo_Inicial ("OK");
        DataFormatada.setDT_FormataData (res.getString ("DT_Saldo"));
        edVolta.setDT_Caixa (DataFormatada.getDT_FormataData ());
        edVolta.setVL_Saldo_Inicial (new Double (res.getString ("VL_Saldo_Inicial")).doubleValue ());
        edVolta.setVL_Saldo_Final (new Double (res.getString ("VL_Saldo_Final")).doubleValue ());
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        // System.out.println ("Saldo Inicial->>" + edVolta.getVL_Saldo_Inicial ());
        // System.out.println ("Saldo Final->>" + edVolta.getVL_Saldo_Final ());

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao consultar saldo inicial");
      excecoes.setMetodo ("getByRecord(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public CaixaED finalizaMovimento (CaixaED ed) throws Excecoes {

    CaixaED edVolta = new CaixaED ();

    // System.out.println ("finalizaMovimento 1");

    String sql = null;
    ResultSet rs = null;
    long aux = 0;
    int i;
    double saldoInicial = ed.getVL_Saldo_Inicial ();
    double saldoFinal = 0;

    try {
      edVolta.setDM_Situacao ("");

      sql = "select * from Caixas where  DM_Situacao='F' and DT_Caixa = '" + ed.getDT_Caixa () + "' and DM_Grupo = '" + ed.getDM_Grupo () + "' and oid_Unidade = " + ed.getOid_Unidade ();
      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        edVolta.setDM_Situacao ("Já foi finalizado o Movimento neste dia.");
      }

      // System.out.println ("finaliza 1");
      if (Parametro_FixoED.getInstancia ().getDM_Atualiza_Movimento_Caixa ().equals ("S")) {
        sql = "select * from Saldos where  DM_Situacao='I' and DT_Saldo = '" + ed.getDT_Caixa () + "' and DM_Grupo = '" + ed.getDM_Grupo () + "' and oid_Unidade = " + ed.getOid_Unidade ();
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setDM_Situacao ("Falta Atualizar os pagamentos do dia.");
        }
      }

      // System.out.println ("finaliza 2");

      if (edVolta.getDM_Situacao ().equals ("")) {
        sql = "select sum(VL_lancamento) as vl_debito from Caixas where dm_debito_credito ='D' and  DM_Situacao='A' and DT_Caixa = '" + ed.getDT_Caixa () + "' and DM_Grupo = '" + ed.getDM_Grupo () + "' and oid_Unidade = " + ed.getOid_Unidade ();
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          saldoInicial += rs.getDouble ("vl_debito");

          // System.out.println ("vl_debito=" + rs.getDouble ("vl_debito"));
        }

        // System.out.println ("finaliza 3");

        sql = "select sum(VL_lancamento) as vl_credito from Caixas where dm_debito_credito ='C' and  DM_Situacao='A' and DT_Caixa = '" + ed.getDT_Caixa () + "' and DM_Grupo = '" + ed.getDM_Grupo () + "' and oid_Unidade = " + ed.getOid_Unidade ();
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          saldoInicial -= rs.getDouble ("vl_credito");
          // System.out.println ("vl_credito=" + rs.getDouble ("vl_credito"));
        }

        saldoFinal = saldoInicial;
        if (saldoInicial < 0) {
          saldoInicial = saldoInicial * -1;
        }

        DecimalFormat dec = new DecimalFormat ("########0.00");

        // System.out.println ("saldo iu  " + ed.getVL_Saldo_Inicial ());

        // System.out.println ("saldoInicial" + dec.format (saldoInicial));
        // System.out.println ("saldoFinal" + dec.format (saldoFinal));
        // System.out.println ("getVL_Saldo_Final" + dec.format (ed.getVL_Saldo_Final ()));

        // System.out.println ("saldo final format:  " + dec.format (ed.getVL_Saldo_Final ()));
        // System.out.println ("saldo inicial form:  " + dec.format (saldoInicial));

        if (dec.format (ed.getVL_Saldo_Final ()).equals (dec.format (saldoInicial))) {
          sql = " UPDATE Caixas set DM_Situacao = 'F' " +
              " Where DT_Caixa = '" + ed.getDT_Caixa () + "'" +
              " and DM_Grupo = '" + ed.getDM_Grupo () + "'" +
              " and oid_Unidade = '" + ed.getOid_Unidade () + "'";

          i = executasql.executarUpdate (sql);

          sql = " UPDATE Saldos set  DM_Situacao = 'F', VL_Saldo_Final= " + saldoFinal +
              " Where DT_Saldo = '" + ed.getDT_Caixa () + "'" +
              " and   DM_Grupo = '" + ed.getDM_Grupo () + "'" +
              " and oid_Unidade = '" + ed.getOid_Unidade () + "'";

          // System.out.println ("finaliza 4");

          i = executasql.executarUpdate (sql);

          edVolta.setDM_Situacao ("Finalizado com Sucesso.");
        }
        else {
          edVolta.setDM_Situacao ("Valor do Saldo Final não confere com os Lançamentos.");
        }
      }

      // System.out.println ("finaliza 8");

      if (edVolta.getDM_Situacao ().equals ("")) {
        edVolta.setDM_Situacao ("Não há Movimento neste dia.");
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados de Caixa ");
      excecoes.setMetodo ("altera(CaixaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;

  }

}
