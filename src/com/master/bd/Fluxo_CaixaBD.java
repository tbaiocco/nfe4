package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DecimalFormat;

import com.master.ed.Fluxo_CaixaED;
import com.master.rl.Fluxo_CaixaRL;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.util.Utilitaria;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Fluxo_CaixaBD {

  private ExecutaSQL executasql;
  String dataReferencia = Data.getDataDMY ();
  String SHORT_DATE = "dd/MM/yyyy";
  DateFormatter dateFormatter = new DateFormatter ();
  FormataDataBean DataFormatada = new FormataDataBean ();
  Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia ();
  private FormataDataBean dataFormatada = new FormataDataBean ();
  Utilitaria util = new Utilitaria (executasql);
  DecimalFormat dec = new DecimalFormat ("###,##0.00");

  public Fluxo_CaixaBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public Fluxo_CaixaED inclui (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    try {

      if (ed.getVL_Fluxo () != 0) {
        //*** Busca OID
         ed.setOid_Fluxo_Caixa (util.getAutoIncremento ("oid_Fluxo_Caixa" , "Fluxos_Caixas"));

        sql = " INSERT INTO Fluxos_Caixas (" +
            "     OID_Fluxo_Caixa" +
            "     ,oid_Conta_Corrente" +
            "     ,NR_Documento" +
            "     ,NM_FLUXO" +
            "     ,DT_Fluxo" +
            "     ,DT_Vencimento" +
            "     ,DT_Saldo" +
            "     ,TX_Observacao" +
            "     ,DT_STAMP" +
            "     ,USUARIO_STAMP" +
            "     ,DM_Entrada_Saida" +
            "     ,DM_Situacao" +
            "     ,DM_Fluxo" +
            "     ,DM_Tipo_Lancamento" +
            "     ,DM_STAMP" +
            "     ,oid_Duplicata" +
            "     ,oid_Compromisso" +
            "     ,oid_Unidade" +
            "     ,oid_Carteira" +
            "     ,oid_Usuario" +
            "     ,VL_Fluxo" +
            "     ,VL_Limite" +
            "     ,VL_Nao_Compensado" +
            "     ,VL_Saldo_Inicial" +
            "     ,NR_Dias)" +
            " VALUES (" +
            ed.getOid_Fluxo_Caixa () + ",";
        sql += "'" + ed.getOid_Conta_Corrente () + "',";
        sql += ed.getNR_Documento () == null ? "null," : "'" + ed.getNR_Documento () + "',";
        sql += ed.getNM_Fluxo () == null ? "null," : "'" + ed.getNM_Fluxo () + "',";
        sql += "'" + ed.getDT_Fluxo () + "',";
        sql += "'" + ed.getDT_Vencimento () + "',";
        sql += "'" + ed.getDT_Saldo () + "',";
        sql += ed.getTX_Observacao () == null ? "'null'," : "'" + ed.getTX_Observacao () + "',";
        sql += "'" + ed.getDt_stamp () + "',";
        sql += "'" + ed.getUsuario_Stamp () + "',";
        sql += "'" + ed.getDM_Entrada_Saida () + "',";
        sql += "'" + ed.getDM_Situacao () + "',";
        sql += "'" + ed.getDM_Fluxo () + "',";
        sql += "'" + ed.getDM_Tipo_Lancamento () + "',";
        sql += "'" + ed.getDm_Stamp () + "',";
        sql += ed.getOid_Duplicata () + ",";
        sql += ed.getOid_Compromisso () + ",";
        sql += ed.getOid_Unidade () + ",";
        sql += ed.getOID_Carteira () + ",";
        sql += ed.getOID_Usuario () + ",";
        sql += ed.getVL_Fluxo () + ",";
        sql += ed.getVL_Limite () + ",";
        sql += ed.getVL_Nao_Compensados () + ",";
        sql += ed.getVL_Saldo_Inicial () + ",";
        sql += ed.getNR_Dias () + ")";

        // System.out.println (sql);

        executasql.executarUpdate (sql);
      }
      return ed;

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
  }

  public double consultaSaldo_Conta_Corrente (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;

    Fluxo_CaixaED edFluxo = new Fluxo_CaixaED ();
    double vl_saldo = 0 , vl_nao_compensado = 0 , vl_limite_credito = 0 , vl_saldo_inicial = 0;
    ResultSet rs2 = null;

    try {

      sql = " SELECT sum(nr_limite_credito) as vl_limite_credito " +
          " FROM Carteiras" +
          " WHERE Carteiras.oid_conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

      rs2 = this.executasql.executarConsulta (sql);
      while (rs2.next ()) {
        vl_limite_credito = rs2.getDouble ("vl_limite_credito") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ();
      }
      // System.out.println ("=========================================================");
      // System.out.println ("Conta_Corrente=" + ed.getOid_Conta_Corrente ());
      // System.out.println ("vl_limite_credito=" + vl_limite_credito);

      sql = " SELECT sum(vl_lote_pagamento) as VL_Saldo " +
          " FROM Lotes_Pagamentos" +
          " WHERE lotes_pagamentos.oid_unidade = Unidades.oid_Unidade  " +
          "   AND lotes_pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente  " +
          "   AND lotes_pagamentos.vl_lote_pagamento  > 0 " +
          "   AND Lotes_Pagamentos.DT_Compensacao is null " +
          "   AND Lotes_Pagamentos.DM_Situacao = 'L' " +
          "   AND Contas_Correntes.oid_conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'" +
          "   AND DT_PROGRAMADA <'" + dataReferencia + "'";

      //// System.out.println("vl_nao_compensado=" + sql);


      rs2 = this.executasql.executarConsulta (sql);
      while (rs2.next ()) {
        vl_nao_compensado = (rs2.getDouble ("VL_Saldo") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ());
      }
      // System.out.println ("vl_nao_compensado=" + vl_nao_compensado);

      sql = " SELECT VL_Saldo_Inicial" +
          "       ,dt_saldo " +
          " FROM Saldos " +
          " WHERE Saldos.dt_Saldo = (Select max(DT_Saldo) from Saldos where Saldos.oid_Conta_Corrente ='" + ed.getOid_Conta_Corrente () + "')" +
          "   AND Saldos.oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "'";

      rs2 = this.executasql.executarConsulta (sql);
      while (rs2.next ()) {
        // System.out.println ("VL_Saldo_Inicial=" + rs2.getDouble ("VL_Saldo_Inicial"));

        vl_saldo_inicial = rs2.getDouble ("VL_Saldo_Inicial") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ();
        vl_saldo = vl_saldo_inicial - vl_nao_compensado + vl_limite_credito;
        edFluxo.setDT_Fluxo (Data.getDataDMY ());
        edFluxo.setVL_Fluxo (vl_saldo);
        edFluxo.setVL_Saldo_Inicial (vl_saldo_inicial);
        edFluxo.setVL_Limite (vl_limite_credito);
        edFluxo.setVL_Nao_Compensados (vl_nao_compensado);
        edFluxo.setDT_Saldo ("01/01/2001");
        
        if (rs2.getString ("dt_saldo")!=null && rs2.getString ("dt_saldo").length()>4){
          DataFormatada.setDT_FormataData (rs2.getString ("dt_saldo"));
          edFluxo.setDT_Saldo (DataFormatada.getDT_FormataData ());
        }
        edFluxo.setDT_Vencimento ("01/01/2001");

        edFluxo.setOid_Unidade (ed.getOid_Unidade ());
        edFluxo.setOid_Conta_Corrente (ed.getOid_Conta_Corrente ());
        edFluxo.setOID_Usuario (ed.getOID_Usuario ());

        edFluxo.setNR_Documento ("");
        edFluxo.setTX_Observacao (" ");
        edFluxo.setDM_Fluxo ("0");
        edFluxo.setNM_Fluxo ( (ed.getNM_Conta_Corrente () + "                                                      ").substring (0 , 40));
        edFluxo.setDM_Entrada_Saida ("I");
        edFluxo.setDM_Tipo_Lancamento ("A");
        edFluxo.setDM_Situacao ("L");
        this.inclui (edFluxo);
      }
      return vl_saldo;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaSaldo_Conta_Corrente()");
    }
  }

  public Fluxo_CaixaED consultaIndice_Financeiro (Fluxo_CaixaED ed) throws Excecoes {

    try {
      // System.out.println ("getVL_Cotacao_Informada->>" + ed.getVL_Cotacao_Informada ());
      ed.setVL_Cotacao (1);
      ed.setVL_Cotacao_Padrao (1);
      if (!"N".equals (ed.getDM_Atualiza_Saldo ())) {

        if (new Long (parametro_FixoED.getOID_Moeda_Padrao ()).longValue () != ed.getOID_Moeda ()) {

          ed.setVL_Cotacao (util.getTableDoubleValue ("VL_Cotacao" ,
                                                      "Indice_Financeiro" ,
                                                      "oid_moeda = " + ed.getOID_Moeda () +
                                                      " AND dt_cotacao='" + dataReferencia + "'"));

          if (ed.getVL_Cotacao_Informada () == 0) {
            ed.setVL_Cotacao_Padrao (util.getTableDoubleValue ("VL_Cotacao" ,
                                                               "Indice_Financeiro" ,
                                                               "oid_moeda = " + parametro_FixoED.getOID_Moeda_Padrao () +
                                                               " AND dt_cotacao='" + dataReferencia + "'"));
          }
          else {
            ed.setVL_Cotacao_Padrao (ed.getVL_Cotacao_Informada ());
          }
        }
        // System.out.println ("setVL_Cotacao  .....->>" + ed.getVL_Cotacao ());
        // System.out.println ("setVL_Cotacao_Padrao->>" + ed.getVL_Cotacao_Padrao ());
      }

      return ed;
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaIndice_Financeiro()");
    }
  }

  public double consultaDuplicatas (Fluxo_CaixaED ed , int d1 , int d2) throws Excecoes {

    String sql = null;
    double vl_saldo = 0;

    Fluxo_CaixaED edFluxo = new Fluxo_CaixaED ();
    Calendar cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);

    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d1);
    String DT_1 = dateFormatter.calendarToString (cal , SHORT_DATE);

    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d2);
    String DT_2 = dateFormatter.calendarToString (cal , SHORT_DATE);

    try {
      sql = " SELECT sum(VL_Saldo) as VL_Saldo " +
          " FROM Duplicatas" +
          " WHERE Duplicatas.oid_unidade = Unidades.oid_Unidade  " +
          "   AND Duplicatas.VL_Saldo > 0 " +
          "   AND Duplicatas.oid_Carteira = '" + ed.getOID_Carteira () + "'";
      if (d1 == d2) {
        sql += "  AND dt_credito ='" + DT_1 + "'";
      }
      else {
        if (d1 == 0 && d2 < 0) {
          sql += "  AND dt_credito <'" + DT_2 + "'";
        }
        if (d1 < 0 && d2 <= 0) {
          sql += "  AND dt_credito >='" + DT_1 + "'" +
              "  AND dt_credito <'" + DT_2 + "'";
        }
        if (d1 >= 0 && d2 > 0) {
          sql += "  AND dt_credito > '" + DT_1 + "'" +
              "  AND dt_credito <='" + DT_2 + "'";
        }
        if (d1 > 0 && d2 == 0) {
          sql += "  AND dt_credito >'" + DT_1 + "'";
        }
        if (d1 == 0 && d2 == 0) {
          sql += "  AND dt_credito ='" + dataReferencia + "'";
        }
      }

      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        edFluxo.setDT_Vencimento (DT_1);
        edFluxo.setDT_Fluxo (Data.getDataDMY ());

        edFluxo.setOid_Unidade (ed.getOid_Unidade ());
        edFluxo.setOID_Usuario (ed.getOID_Usuario ());
        edFluxo.setOID_Carteira (ed.getOID_Carteira ());
        edFluxo.setOid_Conta_Corrente ("0");
        edFluxo.setNR_Documento ("");
        edFluxo.setTX_Observacao (" ");
        edFluxo.setDM_Fluxo ("3");

        if ("D".equals (ed.getDM_Tipo_Fluxo ())) {
          edFluxo.setNM_Fluxo (ed.getNM_Conta_Corrente ());
        }

        if ("P".equals (ed.getDM_Tipo_Fluxo ())) {
          if (d1 == 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Vencido antes de " + String.valueOf (d2 * -1) + " dias.( Antes de " + DT_2 + ")");
          }
          if (d1 < 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Vencido a " + String.valueOf (d2 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 < 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencido até " + String.valueOf (d1 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 >= 0 && d2 > 0) {
            edFluxo.setNM_Fluxo ("A Vencer até " + String.valueOf (d2) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 > 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencido após " + String.valueOf (d1) + " dias.( Depois de " + DT_1 + ")");
          }
          if (d1 == 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencendo Hoje!");
          }
        }

        edFluxo.setDM_Entrada_Saida ("E");
        edFluxo.setDM_Tipo_Lancamento ("A");
        edFluxo.setDM_Situacao ("L");
        vl_saldo = rs.getDouble ("VL_Saldo") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ();
        edFluxo.setVL_Fluxo (vl_saldo);
        this.inclui (edFluxo);
      }

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaDuplicatas()");
    }

    return vl_saldo;

  }

  public double consultaCheques_Clientes (Fluxo_CaixaED ed , int d1 , int d2) throws Excecoes {

    String sql = null;
    double vl_saldo = 0;
    Fluxo_CaixaED edFluxo = new Fluxo_CaixaED ();

    Calendar cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d1);
    String DT_1 = dateFormatter.calendarToString (cal , SHORT_DATE);

    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d2);
    String DT_2 = dateFormatter.calendarToString (cal , SHORT_DATE);

    try {
      sql = " SELECT sum(VL_Cheque) as VL_Saldo " +
          " FROM Cheques_Clientes " +
          " WHERE DT_Compensacao IS NOT NULL" +
          "   AND DM_Situacao = 'F'" +
          "   AND oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

      if (d1 == d2) {
        sql += "  AND DT_Programado ='" + DT_1 + "'";
      }
      else {
        if (d1 == 0 && d2 < 0) {
          sql += "  AND DT_Programado <'" + DT_2 + "'";
        }
        if (d1 < 0 && d2 <= 0) {
          sql += "  AND DT_Programado >='" + DT_1 + "'" + "  AND DT_Programado <'" + DT_2 + "'";
        }
        if (d1 >= 0 && d2 > 0) {
          sql += "  AND DT_Programado > '" + DT_1 + "'" + "  AND DT_Programado <='" + DT_2 + "'";
        }
        if (d1 > 0 && d2 == 0) {
          sql += "  AND DT_Programado >'" + DT_1 + "'";
        }
        if (d1 == 0 && d2 == 0) {
          sql += "  AND DT_Programado ='" + dataReferencia + "'";
        }
      }

      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        edFluxo.setDT_Vencimento (DT_1);
        edFluxo.setDT_Fluxo (Data.getDataDMY ());

        edFluxo.setOid_Unidade (ed.getOid_Unidade ());
        edFluxo.setOID_Usuario (ed.getOID_Usuario ());
        edFluxo.setOid_Conta_Corrente (ed.getOid_Conta_Corrente ());
        edFluxo.setNR_Documento ("");
        edFluxo.setTX_Observacao (" ");
        edFluxo.setDM_Fluxo ("2");

        if ("D".equals (ed.getDM_Tipo_Fluxo ())) {
          edFluxo.setNM_Fluxo (ed.getNM_Conta_Corrente ());
        }

        if ("P".equals (ed.getDM_Tipo_Fluxo ())) {
          if (d1 == 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Vencido antes de " + String.valueOf (d2 * -1) + " dias.( Antes de " + DT_2 + ")");
          }
          if (d1 < 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Vencido a " + String.valueOf (d2 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 < 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencido até " + String.valueOf (d1 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 >= 0 && d2 > 0) {
            edFluxo.setNM_Fluxo ("A Vencer até " + String.valueOf (d2) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 > 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencido após " + String.valueOf (d1) + " dias.( Depois de " + DT_1 + ")");
          }
          if (d1 == 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencendo Hoje!");
          }
        }
        edFluxo.setDM_Entrada_Saida ("E");
        edFluxo.setDM_Tipo_Lancamento ("A");
        edFluxo.setDM_Situacao ("L");
        vl_saldo = rs.getDouble ("VL_Saldo") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ();
        edFluxo.setVL_Fluxo (vl_saldo);
        this.inclui (edFluxo);
      }
      return vl_saldo;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaCheques_Clientes()");
    }
  }

  public double consultaCompromissosUnidade (Fluxo_CaixaED ed , int d1 , int d2) throws Excecoes {

    String sql = null;
    double vl_saldo = 0;
    long oid_Moeda = 0;

    Fluxo_CaixaED edFluxo = new Fluxo_CaixaED ();
    Calendar cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);

    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d1);
    String DT_1 = dateFormatter.calendarToString (cal , SHORT_DATE);

    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d2);
    String DT_2 = dateFormatter.calendarToString (cal , SHORT_DATE);

    try {

      sql = " SELECT VL_Saldo" +
          "       ,oid_Moeda " +
          " FROM  Compromissos" +
          " WHERE Compromissos.VL_Saldo > 0 AND Compromissos.DT_Liberado is not null ";
      if (ed.getOid_Unidade ().intValue () > 0) {
        sql += "   AND Compromissos.oid_Unidade = " + ed.getOid_Unidade ();
      }

      if (d1 == d2) {
        sql += "  AND dt_vencimento ='" + DT_1 + "'";
      }
      else {
        if (d1 == 0 && d2 < 0) {
          sql += "  AND dt_vencimento <'" + DT_2 + "'";
        }
        if (d1 < 0 && d2 <= 0) {
          sql += "  AND dt_vencimento >='" + DT_1 + "'" +
              "  AND dt_vencimento <'" + DT_2 + "'";
        }
        if (d1 >= 0 && d2 > 0) {
          sql += "  AND dt_vencimento > '" + DT_1 + "'" +
              "  AND dt_vencimento <='" + DT_2 + "'";
        }
        if (d1 > 0 && d2 == 0) {
          sql += "  AND dt_vencimento >'" + DT_1 + "'";
        }
        if (d1 == 0 && d2 == 0) {
          sql += "  AND dt_vencimento ='" + dataReferencia + "'";
        }
      }

      sql += " ORDER BY Compromissos.oid_Moeda ";

      // System.out.println("FLUXO CX->> " + sql);
      
      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        if (oid_Moeda != rs.getLong ("oid_Moeda")) {
          edFluxo.setDT_Vencimento (DT_1);
          edFluxo.setDT_Fluxo (Data.getDataDMY ());

          edFluxo.setOid_Unidade (ed.getOid_Unidade ());
          edFluxo.setOID_Usuario (ed.getOID_Usuario ());
          edFluxo.setOid_Conta_Corrente ("0");
          edFluxo.setNR_Documento ("");
          edFluxo.setTX_Observacao (" ");
          edFluxo.setDM_Fluxo ("5");

          if ("D".equals (ed.getDM_Tipo_Fluxo ())) {
            edFluxo.setNM_Fluxo (ed.getNM_Conta_Corrente ());
          }

          edFluxo.setDM_Entrada_Saida ("S");
          edFluxo.setDM_Tipo_Lancamento ("A");
          edFluxo.setDM_Situacao ("L");
          edFluxo.setVL_Fluxo (vl_saldo);

          this.inclui (edFluxo);
          vl_saldo = 0;

        }
        ed.setOID_Moeda (rs.getLong ("oid_Moeda"));
        ed = this.consultaIndice_Financeiro (ed);

        vl_saldo += rs.getDouble ("VL_Saldo") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ();
        oid_Moeda = rs.getLong ("oid_Moeda");
      }
      if (vl_saldo > 0) {
        Fluxo_CaixaED edFluxo2 = new Fluxo_CaixaED ();
        edFluxo2.setDT_Vencimento (DT_1);
        edFluxo2.setDT_Fluxo (Data.getDataDMY ());
        edFluxo2.setOid_Unidade (ed.getOid_Unidade ());
        edFluxo2.setOID_Usuario (ed.getOID_Usuario ());
        edFluxo2.setOid_Conta_Corrente ("0");
        edFluxo2.setNR_Documento ("");
        edFluxo2.setTX_Observacao (" ");
        edFluxo2.setDM_Fluxo ("5");

        if ("D".equals (ed.getDM_Tipo_Fluxo ())) {
          edFluxo2.setNM_Fluxo (ed.getNM_Conta_Corrente ());
        }

        edFluxo2.setDM_Entrada_Saida ("S");
        edFluxo2.setDM_Tipo_Lancamento ("A");
        edFluxo2.setDM_Situacao ("L");
        edFluxo2.setVL_Fluxo (vl_saldo);

        this.inclui (edFluxo2);
      }
      return vl_saldo;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaCompromissosUnidade()");
    }
  }

  public double consultaCompromissos (Fluxo_CaixaED ed , int d1 , int d2) throws Excecoes {

    String sql = null;
    double vl_saldo = 0;
    Fluxo_CaixaED edFluxo = new Fluxo_CaixaED ();

    Calendar cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d1);
    String DT_1 = dateFormatter.calendarToString (cal , SHORT_DATE);

    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d2);
    String DT_2 = dateFormatter.calendarToString (cal , SHORT_DATE);

    try {
      sql = " SELECT sum(VL_Saldo) as VL_Saldo " +
          " FROM Compromissos " +
          " WHERE Compromissos.oid_unidade = Unidades.oid_Unidade  " +
          "   AND Compromissos.VL_Saldo > 0 AND Compromissos.DT_Liberado is not null" +
          "   AND Compromissos.oid_Moeda = " + ed.getOID_Moeda ();

      if (d1 == d2) {
        sql += "  AND dt_vencimento ='" + DT_1 + "'";
      }
      else {
        if (d1 == 0 && d2 < 0) {
          sql += "  AND dt_vencimento <'" + DT_2 + "'";
        }
        if (d1 < 0 && d2 <= 0) {
          sql += "  AND dt_vencimento >='" + DT_1 + "'" +
              "  AND dt_vencimento <'" + DT_2 + "'";
        }
        if (d1 >= 0 && d2 > 0) {
          sql += "  AND dt_vencimento > '" + DT_1 + "'" +
              "  AND dt_vencimento <='" + DT_2 + "'";
        }
        if (d1 > 0 && d2 == 0) {
          sql += "  AND dt_vencimento >'" + DT_1 + "'";
        }
        if (d1 == 0 && d2 == 0) {
          sql += "  AND dt_vencimento ='" + dataReferencia + "'";
        }
      }

      // System.out.println("FLUXO CX->> " + sql);

      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {

        edFluxo.setDT_Vencimento (DT_1);

        edFluxo.setDT_Fluxo (Data.getDataDMY ());
        edFluxo.setOid_Unidade (new Long (0));
        edFluxo.setOID_Usuario (ed.getOID_Usuario ());

        edFluxo.setOid_Conta_Corrente ("0");
        edFluxo.setNR_Documento ("");
        edFluxo.setTX_Observacao (" ");
        edFluxo.setDM_Fluxo ("5");

        if ("D".equals (ed.getDM_Tipo_Fluxo ())) {
          edFluxo.setNM_Fluxo ("Dia " + String.valueOf (d2) + " ->> " + DT_2 + "");
        }

        if ("P".equals (ed.getDM_Tipo_Fluxo ())) {
          if (d1 == 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Vencido antes de " + String.valueOf (d2 * -1) + " dias.( Antes de " + DT_2 + ")");
          }
          if (d1 < 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Vencido a " + String.valueOf (d2 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 < 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencido até " + String.valueOf (d1 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 >= 0 && d2 > 0) {
            edFluxo.setNM_Fluxo ("A Vencer até " + String.valueOf (d2) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 > 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencido após " + String.valueOf (d1) + " dias.( Depois de " + DT_1 + ")");
          }
          if (d1 == 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Vencendo Hoje!");
          }
        }

        edFluxo.setDM_Entrada_Saida ("S");
        edFluxo.setDM_Tipo_Lancamento ("A");
        edFluxo.setDM_Situacao ("L");
        vl_saldo = rs.getDouble ("VL_Saldo") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ();
        edFluxo.setVL_Fluxo (vl_saldo);

        this.inclui (edFluxo);
      }
      return vl_saldo;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaCompromissosOLD()");
    }
  }

  public double consultaPagamentos (Fluxo_CaixaED ed , int d1 , int d2) throws Excecoes {

    String sql = null;
    double vl_saldo = 0;
    Fluxo_CaixaED edFluxo = new Fluxo_CaixaED ();

    Calendar cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d1);
    String DT_1 = dateFormatter.calendarToString (cal , SHORT_DATE);

    cal = Data.stringToCalendar (dataReferencia , "dd/MM/yyyy");
    cal.set (Calendar.DAY_OF_MONTH , diasAtuais + d2);
    String DT_2 = dateFormatter.calendarToString (cal , SHORT_DATE);

    try {

      sql = " SELECT sum(vl_lote_pagamento) as VL_Saldo " +
          " FROM lotes_pagamentos" +
          " WHERE lotes_pagamentos.oid_unidade = Unidades.oid_Unidade  " +
          "   AND lotes_pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente  " +
          "   AND lotes_pagamentos.vl_lote_pagamento  > 0 " +
          "   AND Lotes_Pagamentos.DT_Compensacao is null " +
          "   AND Lotes_Pagamentos.DM_Situacao = 'L' " +
          "   AND Contas_Correntes.oid_conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

      if (d1 == d2) {
        sql += "  AND DT_PROGRAMADA ='" + DT_1 + "'";
      }
      else {
        if (d1 == 0 && d2 < 0) {
          sql += "  AND DT_PROGRAMADA <'" + DT_2 + "'";
        }
        if (d1 < 0 && d2 <= 0) {
          sql += "  AND DT_PROGRAMADA >='" + DT_1 + "'" +
              "  AND DT_PROGRAMADA <'" + DT_2 + "'";
        }
        if (d1 >= 0 && d2 > 0) {
          sql += "  AND DT_PROGRAMADA > '" + DT_1 + "'" +
              "  AND DT_PROGRAMADA <='" + DT_2 + "'";
        }
        if (d1 > 0 && d2 == 0) {
          sql += "  AND DT_PROGRAMADA >'" + DT_1 + "'";
        }
        if (d1 == 0 && d2 == 0) {
          sql += "  AND DT_PROGRAMADA ='" + dataReferencia + "'";
        }
      }

      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        edFluxo.setDT_Vencimento (DT_1);

        edFluxo.setDT_Fluxo (Data.getDataDMY ());
        edFluxo.setOid_Unidade (ed.getOid_Unidade ());
        edFluxo.setOID_Usuario (ed.getOID_Usuario ());
        edFluxo.setOid_Conta_Corrente (ed.getOid_Conta_Corrente ());
        edFluxo.setNR_Documento ("");
        edFluxo.setTX_Observacao (" ");
        edFluxo.setDM_Fluxo ("7");

        if ("D".equals (ed.getDM_Tipo_Fluxo ())) {
          edFluxo.setNM_Fluxo (ed.getNM_Conta_Corrente ());
        }

        if ("P".equals (ed.getDM_Tipo_Fluxo ())) {
          if (d1 == 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Programado antes de " + String.valueOf (d2 * -1) + " dias.( Antes de " + DT_2 + ")");
          }
          if (d1 < 0 && d2 < 0) {
            edFluxo.setNM_Fluxo ("Programado a " + String.valueOf (d2 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 < 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Programado até " + String.valueOf (d1 * -1) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 >= 0 && d2 > 0) {
            edFluxo.setNM_Fluxo ("Programado até " + String.valueOf (d2) + " dias.(" + DT_1 + " a " + DT_2 + ")");
          }
          if (d1 > 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Programado após " + String.valueOf (d1) + " dias.( Depois de " + DT_1 + ")");
          }
          if (d1 == 0 && d2 == 0) {
            edFluxo.setNM_Fluxo ("Programado para Hoje!");
          }
        }
        edFluxo.setDM_Entrada_Saida ("S");
        edFluxo.setDM_Tipo_Lancamento ("A");
        edFluxo.setDM_Situacao ("L");

        vl_saldo = rs.getDouble ("VL_Saldo") / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao ();

        edFluxo.setVL_Fluxo (vl_saldo);
        this.inclui (edFluxo);
      }
      return vl_saldo;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaPagamentos()");
    }
  }

  public Fluxo_CaixaED getByRecord (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    Fluxo_CaixaED edVolta = new Fluxo_CaixaED ();
    try {

      sql = " SELECT Fluxos_Caixas.oid_Fluxo_Caixa" +
          "     ,Fluxos_Caixas.oid_Fluxo_Caixa" +
          "     ,Fluxos_Caixas.Oid_Conta_Corrente" +
          "     ,Fluxos_Caixas.Oid_Unidade" +
          "     ,Fluxos_Caixas.DM_Entrada_Saida" +
          "     ,Fluxos_Caixas.DM_Tipo_Lancamento" +
          "     ,Fluxos_Caixas.DM_Situacao" +
          "     ,Fluxos_Caixas.DT_Fluxo" +
          "     ,Fluxos_Caixas.NR_Documento" +
          "     ,Fluxos_Caixas.TX_Observacao" +
          "     ,Fluxos_Caixas.VL_Fluxo" +
          "     ,Contas_Correntes.CD_Conta_Corrente" +
          "     ,Contas_Correntes.NR_Conta_Corrente" +
          "     ,pessoas.NM_Razao_Social" +
          "     ,pessoas.NM_fantasia   " +
          " FROM Fluxos_Caixas" +
          "     ,Contas_Correntes" +
          "     ,pessoas " +
          " WHERE Fluxos_Caixas.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
          "   AND Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
          "   AND Fluxos_Caixas.oid_Fluxo_Caixa = " + ed.getOid_Fluxo_Caixa ();

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Fluxo_CaixaED ();
        edVolta.setOid_Fluxo_Caixa (res.getLong ("oid_Fluxo_Caixa"));
        edVolta.setOid_Conta_Corrente (res.getString ("Oid_Conta_Corrente"));
        edVolta.setNR_Conta (res.getString ("NR_Conta_Corrente"));
        edVolta.setCD_Conta_Corrente (res.getString ("CD_Conta_Corrente"));
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setOid_Unidade (new Long (res.getLong ("Oid_Unidade")));

        edVolta.setNM_Fantasia (res.getString ("NM_fantasia"));
        edVolta.setDM_Entrada_Saida (res.getString ("DM_Entrada_Saida"));
        edVolta.setDM_Tipo_Lancamento (res.getString ("DM_Tipo_Lancamento"));

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        DataFormatada.setDT_FormataData (res.getString ("DT_Fluxo"));
        edVolta.setDT_Fluxo (DataFormatada.getDT_FormataData ());
        edVolta.setNR_Documento (res.getString ("NR_Documento"));

        if (edVolta.getNR_Documento () == null) {
          edVolta.setNR_Documento ("");
        }
        String tx_Obs = res.getString ("TX_Observacao");
        if (tx_Obs != null) {
          edVolta.setTX_Observacao (tx_Obs);
        }

        edVolta.setVL_Fluxo (res.getDouble ("VL_Fluxo"));
      }
      return edVolta;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
  }

  public Fluxo_CaixaED consultaSaldo (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    Fluxo_CaixaED edVolta = new Fluxo_CaixaED ();
    try {

      sql = " SELECT max(DT_Fluxo) as DT_Fluxo " +
          " FROM Fluxos_Caixas " +
          " WHERE oid_Unidade=" + ed.getOid_Unidade ();

      ResultSet res = this.executasql.executarConsulta (sql);
      edVolta.setDT_Fluxo (Data.getDataDMY ());

      while (res.next ()) {
        edVolta = new Fluxo_CaixaED ();
        DataFormatada.setDT_FormataData (res.getString ("DT_Fluxo"));
        edVolta.setDT_Fluxo (DataFormatada.getDT_FormataData ());
      }
      return edVolta;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaSaldo()");
    }
  }

  public void altera (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " UPDATE Fluxos_Caixas SET ";
      sql += "     NR_Documento = '" + ed.getNR_Documento () + "',";
      sql += "     DT_Fluxo = '" + ed.getDT_Fluxo () + "',";
      sql += "     TX_Observacao = '" + ed.getTX_Observacao () + "',";
      sql += "     DT_STAMP = '" + ed.getDt_stamp () + "',";
      sql += "     USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "',";
      sql += "     DM_STAMP = '" + ed.getDm_Stamp () + "',";
      sql += "     DM_Entrada_Saida = '" + ed.getDM_Entrada_Saida () + "',";
      sql += "     DM_Tipo_Lancamento = '" + ed.getDM_Tipo_Lancamento () + "',";
      sql += "     oid_Duplicata = " + ed.getOid_Duplicata () + ",";
      sql += "     VL_Fluxo = " + ed.getVL_Fluxo () + "";
      sql += " WHERE oid_Fluxo_Caixa = " + ed.getOid_Fluxo_Caixa ();

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
  }

  public void liberaBloqueia (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    try {

      ed.setDM_Situacao (util.getTableStringValue ("DM_Situacao" ,
                                                   "Fluxos_Caixas" ,
                                                   "oid_Fluxo_Caixa = " + ed.getOid_Fluxo_Caixa ()));

      sql = " UPDATE Fluxos_Caixas SET ";
      if ("L".equals (ed.getDM_Situacao ())) {
        sql += " DM_Situacao = 'B'";
      }
      else {
        sql += " DM_Situacao = 'L'";
      }
      sql += " WHERE oid_Fluxo_Caixa = " + ed.getOid_Fluxo_Caixa ();

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "liberaBloqueia()");
    }
  }

  public Fluxo_CaixaED atualiza_intervalo_dias (Fluxo_CaixaED ed) throws Excecoes {

    Fluxo_CaixaED edVolta = new Fluxo_CaixaED ();
    edVolta.setDM_Situacao ("OK");
    long oid_Moeda = 0;
    try {

      String sql = " DELETE FROM Fluxos_Caixas " +
          " WHERE oid_usuario = " + ed.getOID_Usuario ();

      executasql.executarUpdate (sql);
      this.consultaSaldo_Conta_Corrente (ed);

      sql = " SELECT Pessoas.NM_Razao_Social" +
          "     ,Contas_Correntes.oid_Conta_Corrente" +
          "     ,Contas_Correntes.oid_Moeda  " +
          " FROM Contas_Correntes, Pessoas " +
          " WHERE Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
          " ORDER BY Contas_Correntes.oid_Moeda ";

      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        ed.setOid_Conta_Corrente (rs.getString ("oid_Conta_Corrente"));
        ed.setOID_Moeda (rs.getLong ("oid_Moeda"));
        ed.setVL_Cotacao (1);
        ed.setVL_Cotacao_Padrao (1);
        ed.setNM_Conta_Corrente (rs.getString ("NM_Razao_Social"));

        if (parametro_FixoED.getOID_Moeda_Padrao () != rs.getInt ("oid_Moeda")) {

          ed.setVL_Cotacao (util.getTableDoubleValue ("VL_Cotacao" ,
                                                      "Indice_Financeiro" ,
                                                      "oid_moeda = " + ed.getOID_Moeda () +
                                                      " AND dt_cotacao='" + dataReferencia + "'"));
          ed.setVL_Cotacao_Padrao (util.getTableDoubleValue ("VL_Cotacao" ,
                                                             "Indice_Financeiro" ,
                                                             "oid_moeda = " + parametro_FixoED.getOID_Moeda_Padrao () +
                                                             " AND dt_cotacao='" + dataReferencia + "'"));
        }
        this.consultaSaldo_Conta_Corrente (ed);

        if ("Fx_Periodo1".equals (ed.getDM_Relatorio ())) {
          ed.setDM_Tipo_Fluxo ("P");
          this.consultaDuplicatas (ed , 0 , -90);
          this.consultaDuplicatas (ed , -90 , -60);
          this.consultaDuplicatas (ed , -60 , -30);
          this.consultaDuplicatas (ed , -30 , -21);
          this.consultaDuplicatas (ed , -21 , -14);
          this.consultaDuplicatas (ed , -14 , -7);
          this.consultaDuplicatas (ed , -7 , 0);
          this.consultaDuplicatas (ed , 0 , 0);
          this.consultaDuplicatas (ed , 0 , 7);
          this.consultaDuplicatas (ed , 7 , 14);
          this.consultaDuplicatas (ed , 14 , 21);
          this.consultaDuplicatas (ed , 21 , 30);
          this.consultaDuplicatas (ed , 30 , 60);
          this.consultaDuplicatas (ed , 60 , 90);
          this.consultaDuplicatas (ed , 90 , 0);
          if (oid_Moeda != rs.getLong ("oid_Moeda")) {
            oid_Moeda = rs.getLong ("oid_Moeda");
          }
        }
        if ("Fx_Dia".equals (ed.getDM_Relatorio ().substring (0 , 6))) {
          ed.setDM_Tipo_Fluxo ("D");
          String d = ed.getDM_Relatorio ().substring (7 , 10);

          int dias = dias = new Integer (d).intValue ();
          int i = 0;
          while (i < dias) {
            this.consultaDuplicatas (ed , i , i);
            this.consultaPagamentos (ed , i , i);
            if (oid_Moeda != rs.getLong ("oid_Moeda")) {
              // this.consultaCompromissos (ed , i , i);
              oid_Moeda = rs.getLong ("oid_Moeda");
            }
            i++;
          }
        }
      }
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "atualiza_old()");
    }
    return edVolta;
  }

  public Fluxo_CaixaED atualiza (Fluxo_CaixaED ed) throws Excecoes {

    Fluxo_CaixaED edVolta = new Fluxo_CaixaED ();
    edVolta.setDM_Situacao ("OK");
    double vl_saldo_atual = 0;
    
    dataReferencia=ed.getDT_Inicial();
    
    try {

      String sql = " DELETE FROM Fluxos_Caixas "; //WHERE oid_usuario = " + ed.getOID_Usuario ();
      executasql.executarUpdate (sql);

      if ("Fx_Dia".equals (ed.getDM_Relatorio ())) {
        ed.setDM_Tipo_Fluxo ("D");
        long dias = ed.getNR_Dias ();
        int i = 0;
        while (i < dias) {
          if (i == 0) {
            sql = " SELECT Pessoas.NM_Razao_Social" +
                "     ,Contas_Correntes.oid_Conta_Corrente" +
                "     ,Contas_Correntes.cd_Conta_Corrente" +
                "     ,Contas_Correntes.oid_Moeda  " +
                " FROM Contas_Correntes, Pessoas " +
                " WHERE Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
                "   AND DM_Fluxo_Caixa =  'S' " +
                "   AND (DM_Tipo_Conta_Corrente = 'U' OR  DM_Tipo_Conta_Corrente = 'B') ";

            ResultSet rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              ed.setOid_Conta_Corrente (rs.getString ("oid_Conta_Corrente"));
              ed.setNM_Conta_Corrente (rs.getString ("cd_Conta_Corrente") + " - " + rs.getString ("NM_Razao_Social"));
              ed.setOID_Moeda (rs.getLong ("oid_Moeda"));
              ed = this.consultaIndice_Financeiro (ed);

              vl_saldo_atual += this.consultaSaldo_Conta_Corrente (ed);
            }
          }

          sql = " SELECT Pessoas.NM_Razao_Social" +
              "     ,Contas_Correntes.oid_Conta_Corrente" +
              "     ,Contas_Correntes.oid_Moeda" +
              "     ,Carteiras.cd_Carteira" +
              "     ,Carteiras.dm_Carteira" +
              "     ,Carteiras.oid_Carteira " +
              " FROM Contas_Correntes" +
              "     ,Pessoas" +
              "     ,Carteiras " +
              " WHERE Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
              "   AND DM_Fluxo_Caixa =  'S' " +
              "   AND Contas_Correntes.oid_conta_Corrente = Carteiras.oid_conta_Corrente ";

          ResultSet rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            ed.setOID_Carteira (rs.getLong ("oid_Carteira"));
            ed.setNM_Conta_Corrente (rs.getString ("cd_Carteira") + "(" + rs.getString ("dm_Carteira") + ") " + rs.getString ("NM_Razao_Social"));
            ed.setOID_Moeda (rs.getLong ("oid_Moeda"));
            ed = this.consultaIndice_Financeiro (ed);

            vl_saldo_atual += this.consultaDuplicatas (ed , i , i);
          }

          sql = " SELECT Pessoas.NM_Razao_Social" +
              "     ,Contas_Correntes.oid_Conta_Corrente" +
              "     ,Contas_Correntes.oid_Moeda" +
              "     ,Contas_Correntes.cd_Conta_Corrente " +
              " FROM Contas_Correntes, Pessoas " +
              " WHERE Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
              "   AND DM_Fluxo_Caixa =  'S' " +
              "   AND (DM_Tipo_Conta_Corrente = 'U' OR  DM_Tipo_Conta_Corrente = 'B') ";

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            ed.setOid_Conta_Corrente (rs.getString ("oid_Conta_Corrente"));
            ed.setNM_Conta_Corrente (rs.getString ("cd_Conta_Corrente") + " - " + rs.getString ("NM_Razao_Social"));
            ed.setOID_Moeda (rs.getLong ("oid_Moeda"));
            ed = this.consultaIndice_Financeiro (ed);

            vl_saldo_atual += this.consultaPagamentos (ed , i , i);
            //vl_saldo_atual += this.consultaCheques_Clientes (ed , i , i);
          }

          if ("F".equals (ed.getDM_Pagamentos ())) {
            ed.setOid_Unidade (new Long (0));
            ed.setNM_Conta_Corrente ("Fornecedores Diversos");
            vl_saldo_atual += this.consultaCompromissos (ed , i , i);
          }
          else {
            sql = " SELECT Pessoas.NM_Fantasia" +
                "     ,Unidades.cd_Unidade" +
                "     ,Unidades.oid_Unidade " +
                " FROM Unidades, Pessoas " +
                " WHERE Pessoas.oid_pessoa = Unidades.oid_Pessoa ";
            rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              ed.setOid_Unidade (new Long (rs.getLong ("oid_Unidade")));
              ed.setNM_Conta_Corrente (rs.getString ("cd_Unidade") + " " +
                                       rs.getString ("NM_Fantasia"));

              vl_saldo_atual += this.consultaCompromissosUnidade (ed , i , i);
            }
          }

          i++;
        }
      }

      if ("Prog".equals (ed.getDM_Relatorio ())) {
        ed.setDM_Tipo_Fluxo ("D");
        long dias = ed.getNR_Dias ();
        int i = 0;
        while (i < dias) {
          if (i == 0) {
            sql = " SELECT Pessoas.NM_Razao_Social" +
                "     ,Contas_Correntes.oid_Conta_Corrente" +
                "     ,Contas_Correntes.cd_Conta_Corrente" +
                "     ,Contas_Correntes.oid_Moeda  " +
                " FROM Contas_Correntes, Pessoas " +
                " WHERE Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
                "   AND DM_Fluxo_Caixa =  'S' " +
                "   AND (DM_Tipo_Conta_Corrente = 'U' OR  DM_Tipo_Conta_Corrente = 'B') ";

            ResultSet rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              ed.setOid_Conta_Corrente (rs.getString ("oid_Conta_Corrente"));
              ed.setNM_Conta_Corrente (rs.getString ("cd_Conta_Corrente") + " - " + rs.getString ("NM_Razao_Social"));
              ed.setOID_Moeda (rs.getLong ("oid_Moeda"));
              ed = this.consultaIndice_Financeiro (ed);

              vl_saldo_atual += this.consultaSaldo_Conta_Corrente (ed);
            }
          }

          i++;
        }
      }

      return edVolta;

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "atualiza()");
    }
  }

  public void deleta (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " DELETE FROM Fluxos_Caixas " +
          " WHERE oid_Fluxo_Caixa = " + ed.getOid_Fluxo_Caixa ();

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public ArrayList lista (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    double vl_saldo = 0;
    ResultSet res2 = null;
    DecimalFormat dec = new DecimalFormat ("##,###,##0.00");

    try {

      sql = " SELECT * FROM Fluxos_Caixas " +
          //" WHERE oid_usuario = " + ed.getOID_Usuario () +
          " ORDER BY Fluxos_Caixas.DT_Vencimento, Fluxos_Caixas.DM_Fluxo ";

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Fluxo_CaixaED edVolta = new Fluxo_CaixaED ();

        edVolta.setOid_Fluxo_Caixa (res.getLong ("oid_Fluxo_Caixa"));
        edVolta.setNR_Documento (res.getString ("NR_Documento"));
        edVolta.setNM_Fluxo (res.getString ("NM_Fluxo"));
        edVolta.setDM_Fluxo (res.getString ("DM_Fluxo"));
        if (edVolta.getDM_Fluxo ().equals ("0")) {
          edVolta.setNM_Tipo_Fluxo ("Saldo Inic");
        }
        if (edVolta.getDM_Fluxo ().equals ("2")) {
          edVolta.setNM_Tipo_Fluxo ("Cheques Clientes");
        }
        if (edVolta.getDM_Fluxo ().equals ("3")) {
          edVolta.setNM_Tipo_Fluxo ("Cobrança");
        }
        if (edVolta.getDM_Fluxo ().equals ("4")) {
          edVolta.setNM_Tipo_Fluxo ("Prev.Entradas");
        }
        if (edVolta.getDM_Fluxo ().equals ("5")) {
          edVolta.setNM_Tipo_Fluxo ("Pagamentos");
        }
        if (edVolta.getDM_Fluxo ().equals ("6")) {
          edVolta.setNM_Tipo_Fluxo ("Prev.Saída");
        }
        if (edVolta.getDM_Fluxo ().equals ("7")) {
          edVolta.setNM_Tipo_Fluxo ("Prog.Cta Corrente");
        }
        if (edVolta.getDM_Fluxo ().equals ("9")) {
          edVolta.setNM_Tipo_Fluxo ("Saldo Final");
        }

        edVolta.setOid_Conta_Corrente (res.getString ("Oid_Conta_Corrente"));
        edVolta.setCD_Conta_Corrente ("-");
        edVolta.setNM_Razao_Social ("-");

        if (util.doValida (edVolta.getOid_Conta_Corrente ())) {
          sql = " SELECT Contas_Correntes.CD_Conta_Corrente" +
              "     ,Pessoas.NM_Razao_Social " +
              " FROM contas_correntes" +
              "     ,Pessoas " +
              " WHERE Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
              "   AND Contas_Correntes.Oid_Conta_Corrente = '" + edVolta.getOid_Conta_Corrente () + "'";
          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            edVolta.setCD_Conta_Corrente (res2.getString ("CD_Conta_Corrente"));
            edVolta.setNM_Razao_Social (res2.getString ("NM_Razao_Social"));
          }
        }
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Entrada_Saida (res.getString ("DM_Entrada_Saida"));

        edVolta.setVL_E (" ");
        edVolta.setVL_S (" ");
        edVolta.setVL_I (" ");
        edVolta.setVL_SF (" ");
        if ("E".equals (res.getString ("DM_Entrada_Saida"))) {
          edVolta.setVL_Entrada (res.getDouble ("VL_Fluxo"));
          edVolta.setVL_E (dec.format (res.getDouble ("VL_Fluxo")));
        }
        if ("S".equals (res.getString ("DM_Entrada_Saida"))) {
          edVolta.setVL_Saida (res.getDouble ("VL_Fluxo"));
          edVolta.setVL_S (dec.format (res.getDouble ("VL_Fluxo")));
        }
        if ("I".equals (res.getString ("DM_Entrada_Saida"))) {
          edVolta.setVL_Saldo_Inicial (res.getDouble ("VL_Fluxo"));
          edVolta.setVL_I (dec.format (res.getDouble ("VL_Fluxo")));
        }
        edVolta.setVL_Fluxo (res.getDouble ("VL_Fluxo"));

        DataFormatada.setDT_FormataData (res.getString ("DT_Fluxo"));
        edVolta.setDT_Fluxo (DataFormatada.getDT_FormataData ());
        DataFormatada.setDT_FormataData (res.getString ("DT_Vencimento"));
        edVolta.setDT_Vencimento (DataFormatada.getDT_FormataData ());

        if ("E".equals (edVolta.getDM_Entrada_Saida ()) && "L".equals (edVolta.getDM_Situacao ())) {
          vl_saldo += res.getDouble ("VL_Fluxo");
        }
        if ("I".equals (edVolta.getDM_Entrada_Saida ()) && "L".equals (edVolta.getDM_Situacao ())) {
          vl_saldo += res.getDouble ("VL_Fluxo");
        }
        if ("S".equals (edVolta.getDM_Entrada_Saida ()) && "L".equals (edVolta.getDM_Situacao ())) {
          vl_saldo -= res.getDouble ("VL_Fluxo");
        }

        edVolta.setVL_Saldo (vl_saldo);
        edVolta.setVL_SF (dec.format (vl_saldo));

        list.add (edVolta);
      }
      return list;

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
  }

  public byte[] imprime_Fluxo_Caixa (Fluxo_CaixaED ed) throws Excecoes {

    String sql = null;
    sql = " SELECT * FROM Fluxos_Caixas  " +
        //" WHERE oid_usuario = " + ed.getOID_Usuario () +
        " ORDER BY Fluxos_Caixas.dt_vencimento, Fluxos_Caixas.DM_FLUXO ";

    try {

      ResultSet res = this.executasql.executarConsulta (sql.toString ());
      return new Fluxo_CaixaRL ().imprime_Fluxo_Caixa_Diario (res , ed);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "imprime_Fluxo_Caixa()");
    }
  }
}
