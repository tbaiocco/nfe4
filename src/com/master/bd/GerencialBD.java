package com.master.bd;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import com.master.ed.*;
import com.master.rl.*;
import com.master.rn.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class GerencialBD
    extends Transacao {
  private ExecutaSQL executasql;

  Utilitaria util = new Utilitaria (executasql);

  public GerencialBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  FormataDataBean dataFormatada = new FormataDataBean ();
  SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  UnidadeBean Unidades = new UnidadeBean ();

  double vl_total_frete = 0;
  double vl_ordem_frete_terceiro = 0;
  double vl_frete_liquido = 0;
  double vl_notas_fiscais = 0;
  double nr_peso = 0;
  double nr_peso_cubado = 0;
  double nr_volumes = 0;
  double vl_margem = 0;
  double vl_total_custo = 0;
  double nr_despachos = 0;
  double tt_Prazo_Medio = 0;
  String Nr_Sort = "";
  String OID_Pessoa = "PRIMEIRO";
  String OID_Pessoa_Lida = "";
  String data = "";
  String NM_Campo = "";
  String NM_Campo2 = "";
  String data1 = "";
  String data2 = "";
  String DM_Situacao_Cliente = "";
  String NM_Origem = "";
  String NM_Destino = "";
  String NM_Pais_Origem = "";
  String NM_Pais_Destino = "";
  String DM_Prazo_Medio = "";

  String DM_Consulta_Conta = "N";

  double valor_mes1 = 0;
  double valor_mes2 = 0;
  double valor_mes3 = 0;

  long OID_Modal = 999999;
  long OID_Tipo_Movimento = 999999;
  long OID_Grupo_Economico = 999999;

  long OID_Cidade_Origem = 999999;
  long OID_Cidade_Destino = 999999;

  long OID_Regiao_Origem = 999999;
  long OID_Regiao_Destino = 999999;

  long OID_Unidade_Origem = 999999;
  long OID_Unidade_Destino = 999999;

  String OID_Veiculo = "";

  double tvl_total_frete = 0;
  double tvl_notas_fiscais = 0;
  double tnr_peso = 0;
  double tnr_peso_cubado = 0;
  double tnr_volumes = 0;
  double tvl_margem = 0;
  double tnr_despachos = 0;
  double vl_ordem_frete = 0;

  double tt_total_frete = 0;
  double tt_qtde_embarques = 0;
  double tt_peso = 0;
  double tt_desconto = 0;
  double tt_icms = 0;
  double tt_issqn = 0;

  String DM_Quebra_Unidade = "N";
  String DM_Quebra_Situacao_Cliente = "N";
  String DM_Quebra_Pais = "N";
  String DM_Quebra_Fornecedor = "N";
  String DM_Quebra_Cliente = "N";
  String DM_Quebra_Vendedor = "N";
  String DM_Quebra_Veiculo = "N";
  String DM_Quebra_Modal = "N";
  String DM_Quebra_Produto = "N";
  String DM_Quebra_Grupo_Economico = "N";
  String DM_Quebra_Cidade = "N";
  String DM_Quebra_Regiao = "N";
  String DM_Quebra_Tipo_Movimento = "N";
  String DM_Mapa = "N";
  String DM_Analitico_Sintetico = "S";
  String DM_Analise = "";
  double Vl_Analise = 0;
  String Dt_Analise = "";
  int ordem = 0;
  double v1 = 0 , v2 = 0 , v3 = 0 , v4 = 0 , v5 = 0 , v6 = 0 , v7 = 0 , v8 = 0 , v9 = 0 , v10 = 0 , vencido = 0 , vencer = 0;
  long OID_Carteira = 0 , OID_Conta_Corrente = 0 , OID_Conta = 0;
  long OID_Gerencial = 0;

  double dia1 = 0;
  double dia2 = 0;
  double dia3 = 0;
  double dia4 = 0;
  double dia5 = 0;
  double dia6 = 0;
  int lidos = 0;

  public GerencialED getByRecord (GerencialED ed) throws Excecoes {

    // System.err.println ("getByRecord  ");

    String sql = null;

    GerencialED edVolta = new GerencialED ();

    try {

      sql = " select * FROM Gerencial ";
      sql += "  where  Gerencial.Nr_Sort = '" + ed.getNR_Sort () + "'";

      if ("Tit.Receber".equals (ed.getNM_Campo ())) {
        sql += " and Gerencial.oid_carteira= " + ed.getOID_Carteira ();
      }

      if ("Compromissos".equals (ed.getNM_Campo ())) {
        sql += " and Gerencial.oid_conta= " + ed.getOID_Conta ();
      }

      if ("Bancos".equals (ed.getNM_Campo ())) {
        sql += " and Gerencial.oid_conta_corrente= " + ed.getOID_Conta_Corrente ();
      }

      if ("Veiculos".equals (ed.getNM_Campo ())) {
        sql += " and Gerencial.oid_Veiculo= '" + ed.getOID_Veiculo () + "'";
      }

      if ("Pessoa".equals (ed.getNM_Campo ())) {
        sql += " and Gerencial.oid_Pessoa= '" + ed.getOID_Pessoa () + "'";
      }

      if ("S".equals (DM_Consulta_Conta)) {
        sql += " and Gerencial.oid_conta= " + ed.getOID_Conta ();
        if (!"".equals (ed.getNM_Campo2 ())) {
          sql += " and Gerencial.NM_Campo2 = '" + ed.getNM_Campo2 () + "'";
        }
      }

      if ("Analise Faturamento".equals (ed.getNM_Campo2 ())) {
        sql += " and Gerencial.oid_Unidade_Origem= " + ed.getOID_Unidade_Origem ();
      }

      // System.err.println ("achou sql  " + sql);

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        // System.err.println ("achou get by");

        edVolta.setOID_Gerencial (res.getLong ("OID_Gerencial"));
        edVolta.setVL_1 (res.getDouble ("vl_1"));
        edVolta.setVL_2 (res.getDouble ("vl_2"));
        edVolta.setVL_3 (res.getDouble ("vl_3"));
        edVolta.setVL_4 (res.getDouble ("vl_4"));
        edVolta.setVL_5 (res.getDouble ("vl_5"));
        edVolta.setVL_6 (res.getDouble ("vl_6"));
        edVolta.setVL_7 (res.getDouble ("vl_7"));
        edVolta.setVL_8 (res.getDouble ("vl_8"));
        edVolta.setVL_9 (res.getDouble ("vl_9"));
        edVolta.setVL_10 (res.getDouble ("vl_10"));
        edVolta.setVL_Vencido (res.getDouble ("vl_Vencido"));
        edVolta.setVL_Vencer (res.getDouble ("vl_Vencer"));
      }

      // System.out.println (" leu edVolta.setOID_Gerencial ->> " + edVolta.getOID_Gerencial ());

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord(ContaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public GerencialED altera (GerencialED ed) throws Excecoes {

    String sql = null;

    GerencialED edVolta = new GerencialED ();

    try {

      // System.out.println (" alterando OID_Gerencial ->> " + ed.getOID_Gerencial ());

      sql = " update Gerencial set VL_Vencido= " + ed.getVL_Vencido ();

      if (ed.getVL_1 () > 0) {
        sql += ", VL_1= " + ed.getVL_1 ();
      }
      if (ed.getVL_2 () > 0) {
        sql += ", VL_2= " + ed.getVL_2 ();
      }
      if (ed.getVL_3 () > 0) {
        sql += ", VL_3= " + ed.getVL_3 ();
      }
      if (ed.getVL_4 () > 0) {
        sql += ", VL_4= " + ed.getVL_4 ();
      }
      if (ed.getVL_5 () > 0) {
        sql += ", VL_5= " + ed.getVL_5 ();
      }
      if (ed.getVL_6 () > 0) {
        sql += ", VL_6= " + ed.getVL_6 ();
      }
      if (ed.getVL_7 () > 0) {
        sql += ", VL_7= " + ed.getVL_7 ();
      }
      if (ed.getVL_8 () > 0) {
        sql += ", VL_8= " + ed.getVL_8 ();
      }
      if (ed.getVL_9 () > 0) {
        sql += ", VL_9= " + ed.getVL_9 ();
      }
      if (ed.getVL_10 () > 0) {
        sql += ", VL_10= " + ed.getVL_10 ();
      }

      sql += " , Vl_vencer= " + ed.getVL_Vencer ();

      sql += " Where oid_gerencial = " + ed.getOID_Gerencial ();

      // System.out.println ("vai alterar" + sql);

      int i = executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord(ContaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public byte[] geraAnalise_Gerencial_Conhecimentos (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    OID_Gerencial = 0;

    DM_Quebra_Unidade = "N";
    DM_Quebra_Vendedor = "N";
    DM_Quebra_Modal = "N";
    DM_Quebra_Veiculo = "N";
    DM_Quebra_Produto = "N";
    DM_Quebra_Situacao_Cliente = "N";
    DM_Quebra_Cliente = "N";
    DM_Quebra_Grupo_Economico = "N";
    DM_Quebra_Situacao_Cliente = "N";
    DM_Quebra_Cidade = "N";
    DM_Quebra_Regiao = "N";
    DM_Mapa = "N";
    DM_Analitico_Sintetico = "S";

    if ( (ed.getDM_Pessoa () == null) || ed.getDM_Pessoa ().equals ("")) {
      ed.setDM_Pessoa ("P");
    }
    if ( (ed.getDM_Classificar () == null) || ed.getDM_Classificar ().equals ("")) {
      ed.setDM_Classificar ("1");
    }

    if (ed.getDM_Relatorio ().equals ("CSU")) {
      DM_Quebra_Unidade = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCF")) {
      DM_Quebra_Veiculo = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSV")) {
      DM_Quebra_Vendedor = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSM")) {
      DM_Quebra_Modal = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSP")) {
      DM_Quebra_Produto = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CST")) {
      DM_Quebra_Situacao_Cliente = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSC")) {
      DM_Quebra_Cliente = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCC")) {
      DM_Quebra_Cliente = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCV")) {
      DM_Quebra_Cliente = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSG")) {
      DM_Quebra_Grupo_Economico = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCID")) {
      DM_Quebra_Cidade = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCREG")) {
      DM_Quebra_Regiao = "S";
    }
    if (ed.getDM_Relatorio ().equals ("MAP1")) {
      DM_Mapa = "S";
    }
    if (ed.getDM_Relatorio ().equals ("REL1")) {
      DM_Analitico_Sintetico = "A";
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>>  " + ed.getDM_Relatorio ());

    if (DM_Mapa.equals ("N")) {
      sql = "  SELECT Conhecimentos.oid_Pessoa, ";

         if ("CT".equals (ed.getDM_Vendedor ())) {
           sql += "  Conhecimentos.oid_Vendedor, ";
         }
         else {
           sql +="  Clientes.oid_Vendedor, ";
         }

         sql+="  Conhecimentos.oid_Modal, " +
          "  Conhecimentos.oid_Veiculo, " +
          "  Conhecimentos.oid_Conhecimento, " +
          "  Conhecimentos.oid_Pessoa_Pagador, " +
          "  Conhecimentos.oid_Pessoa_Destinatario, " +
          "  Conhecimentos.nr_volumes, " +
          "  Conhecimentos.vl_total_frete,  " +
          "  Conhecimentos.vl_total_custo , " +
          "  Conhecimentos.NR_Peso,  " +
          "  Conhecimentos.NR_Peso_Cubado, " +
          "  Conhecimentos.NR_Volumes, " +
          "  Estado_Origem.CD_Estado  as CD_Estado_Origem, " +
          "  Regiao_Estado_Destino.oid_Regiao_Estado as oid_Regiao_Destino, " +
          "  Cidade_Origem.NM_Cidade  as NM_Cidade_Origem, " +
          "  Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          "  Cidade_Destino.NM_Cidade  as NM_Cidade_DEstino, " +
          "  Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
          "  Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          "  Clientes.DM_Situacao as DM_Situacao_Cliente, " +
          "  Conhecimentos.VL_Nota_Fiscal " +
          "  FROM   Conhecimentos, Unidades, Clientes, " +
          "  Cidades Cidade_Origem, " +
          "  Cidades Cidade_Destino, " +
          "  Regioes_Estados Regiao_Estado_Origem,  " +
          "  Regioes_Estados Regiao_Estado_Destino, " +
          "  Estados Estado_Origem, " +
          "  Estados Estado_Destino " +
          "  WHERE  Conhecimentos.oid_unidade = Unidades.oid_Unidade  " +
          "  AND    Conhecimentos.OID_Pessoa_Pagador = Clientes.oid_cliente " +
          "  AND    Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
          "  AND    Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          "  AND    Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
          "  AND    Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          "  AND    Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
          "  AND    Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado ";


    }
    if (DM_Quebra_Grupo_Economico.equals ("S")) {
      sql = "  SELECT Conhecimentos.oid_Pessoa, ";

         if ("CT".equals (ed.getDM_Vendedor ())) {
           sql += "  Conhecimentos.oid_Vendedor, ";
         }
         else {
           sql +="  Clientes.oid_Vendedor, ";
         }

         sql+="  Conhecimentos.oid_Modal, " +
          " Conhecimentos.oid_Veiculo, " +
          " Conhecimentos.oid_Conhecimento, " +
          " Conhecimentos.oid_Pessoa_Pagador, " +
          " Conhecimentos.oid_Pessoa_Destinatario, " +
          " Conhecimentos.nr_volumes, " +
          " Conhecimentos.vl_total_frete, " +
          " Conhecimentos.vl_total_custo , " +
          " Conhecimentos.NR_Peso, " +
          " Conhecimentos.NR_Peso_Cubado,  " +
          " Conhecimentos.NR_Volumes, " +
          " Conhecimentos.VL_Nota_Fiscal, " +
          " Estado_Origem.CD_Estado  as CD_Estado_Origem, " +
          " Regiao_Estado_Destino.oid_Regiao_Estado as oid_Regiao_Destino, " +
          " Cidade_Origem.NM_Cidade  as NM_Cidade_Origem, " +
          " Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          " Cidade_Destino.NM_Cidade  as NM_Cidade_DEstino, " +
          " Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
          " Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          " Clientes.DM_Situacao as DM_Situacao_Cliente, " +
          " Clientes.OID_Grupo_Economico " +
          " FROM  Conhecimentos, Clientes, Unidades, " +
          "  Cidades Cidade_Origem, " +
          "  Cidades Cidade_Destino, " +
          "  Regioes_Estados Regiao_Estado_Origem,  " +
          "  Regioes_Estados Regiao_Estado_Destino, " +
          "  Estados Estado_Origem, " +
          "  Estados Estado_Destino " +
          " WHERE Conhecimentos.oid_unidade = Unidades.oid_Unidade  " +
          " AND   Conhecimentos.OID_Pessoa_Pagador = Clientes.oid_cliente " +
          "  AND    Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
          "  AND    Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          "  AND    Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
          "  AND    Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          "  AND    Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
          "  AND    Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado ";


    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 2 ");

    if (DM_Mapa.equals ("S")) {


      sql = "  SELECT Conhecimentos.oid_conhecimento, ";

         if ("CT".equals (ed.getDM_Vendedor ())) {
           sql += "  Conhecimentos.oid_Vendedor, ";
         }
         else {
           sql +="  Clientes.oid_Vendedor, ";
         }

         sql+="  Conhecimentos.oid_Modal, " +
          " Conhecimentos.oid_Veiculo, " +
          " Conhecimentos.oid_Conhecimento, " +
          " Conhecimentos.oid_Pessoa_Pagador, " +
          " Conhecimentos.oid_Pessoa_Destinatario, " +
          " Conhecimentos.nr_volumes, " +
          " Conhecimentos.vl_total_frete, " +
          " Conhecimentos.vl_total_custo , " +
          " Conhecimentos.NR_Peso, " +
          " Conhecimentos.NR_Peso_Cubado, " +
          " Conhecimentos.NR_Volumes,  " +
          " Conhecimentos.VL_Nota_Fiscal, " +
          " Clientes.DM_Situacao as DM_Situacao_Cliente, " +
          " Estado_Origem.CD_Estado  as CD_Estado_Origem, " +
          " Regiao_Estado_Destino.oid_Regiao_Estado as oid_Regiao_Destino, " +
          " Cidade_Origem.NM_Cidade  as NM_Cidade_Origem, " +
          " Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          " Cidade_Destino.NM_Cidade  as NM_Cidade_DEstino, " +
          " Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
          " Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          " Modal.DM_Tipo_Tabela_Frete " +
          " FROM   Conhecimentos, Modal, Unidades, Clientes, " +
          "  Cidades Cidade_Origem, " +
          "  Cidades Cidade_Destino, " +
          "  Regioes_Estados Regiao_Estado_Origem,  " +
          "  Regioes_Estados Regiao_Estado_Destino, " +
          "  Estados Estado_Origem, " +
          "  Estados Estado_Destino " +
          " WHERE Conhecimentos.oid_unidade = Unidades.oid_Unidade " +
          " AND   Conhecimentos.OID_Pessoa_Pagador = Clientes.oid_cliente " +
          " AND   Conhecimentos.oid_modal = Modal.oid_Modal " +
          " AND   Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
          " AND   Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          " AND   Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
          " AND   Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          " AND   Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
          " AND   Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado ";
    }
 

    if (DM_Analitico_Sintetico.equals ("A")) {
      sql = " SELECT Conhecimentos.oid_Veiculo, " +
          "        Conhecimentos.oid_Conhecimento, " +
          "        Conhecimentos.nr_conhecimento, " +
          "        Conhecimentos.dt_emissao, " +
          "        Conhecimentos.oid_Modal, " +
          "        Conhecimentos.oid_pessoa_pagador, " +
          "        Conhecimentos.oid_pessoa_destinatario, " +
          "        Conhecimentos.nr_peso, " +
          "        Conhecimentos.nr_peso_cubado, " +
          "        Conhecimentos.nr_volumes, " +
          "        Conhecimentos.dm_tipo_pagamento, " +
          "        Conhecimentos.vl_total_frete, " +
          "        Conhecimentos.VL_Total_Custo , " +
          "        Conhecimentos.VL_Custo_Coleta , " +
          "        Conhecimentos.VL_Custo_Transferencia , " +
          "        Conhecimentos.VL_Custo_Entrega , " +
          "        Conhecimentos.VL_Custo_Imposto , " +
          "        Conhecimentos.VL_Custo_Seguro , " +
          "        Conhecimentos.VL_Custo_Outros , " +
          "        Conhecimentos.VL_Custo_Monitoramento , " +
          "        Conhecimentos.VL_Custo_Comunicacao , " +
          "        Conhecimentos.VL_Custo_Gerenciamento_Risco , " +
          "        Conhecimentos.VL_Custo_Comissao, " +
          "        Conhecimentos.VL_Custo_Financeiro , " +
          "        Conhecimentos.VL_Custo_Administrativo , " +
          "        Conhecimentos.VL_Ressarcimento, " +
          "        Conhecimentos.VL_NOTA_FISCAL, " +
          "        Estado_Origem.CD_Estado  as CD_Estado_Origem, " +
          "        Cidade_Origem.NM_Cidade  as NM_Cidade_Origem, " +
          "        Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          "        Cidade_Destino.NM_Cidade  as NM_Cidade_DEstino, " +
          "        Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
          "        Regiao_Estado_Destino.oid_Regiao_Estado as oid_Regiao_Destino, " +
          "        Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
          "        Unidades.CD_Unidade, Unidades.oid_Pessoa as oid_Pessoa_Unidade " +
          " FROM  Conhecimentos, Clientes, Unidades, " +
          "  Cidades Cidade_Origem, " +
          "  Cidades Cidade_Destino, " +
          "  Regioes_Estados Regiao_Estado_Origem,  " +
          "  Regioes_Estados Regiao_Estado_Destino, " +
          "  Estados Estado_Origem, " +
          "  Estados Estado_Destino " +
          " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
          " AND Conhecimentos.OID_Pessoa_Pagador  = Clientes.oid_cliente " +
          " AND   Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
          " AND   Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          " AND   Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
          " AND   Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          " AND   Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
          " AND   Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado ";
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 3 ");

    sql += " AND Conhecimentos.DM_Impresso = 'S' " +
        " AND Conhecimentos.DM_Situacao <> 'C' " +
        " AND Unidades.DM_Situacao       <> 'I' " +
        " AND Conhecimentos.VL_Total_Frete > 0";

    if (ed.getOID_Empresa () > 0) {
      sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
    }

    if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 4 ");

    if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Modal = " + ed.getOID_Modal ();
    }

    if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
    }

    //Origem/Destino
    if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("C")
        && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
      sql += " AND Conhecimentos.oid_cidade = " + ed.getOID_Origem ();
    }
    if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("R")
        && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
      sql += " AND Regiao_Estado_Origem.oid_Regiao_Estado = " + ed.getOID_Origem ();
    }
    if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("E")
        && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
      sql += " AND Estado_Origem.oid_Estado = " + ed.getOID_Origem ();
    }
    if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("C")
        && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
      sql += " AND Conhecimentos.oid_cidade_Destino = " + ed.getOID_Destino ();
    }
    if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("R")
        && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
      sql += " AND Regiao_Estado_Destino.oid_Regiao_Estado = " + ed.getOID_Destino ();
    }
    if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("E")
        && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
      sql += " AND Estado_Destino.oid_Estado = " + ed.getOID_Destino ();
    }
    //


    if (String.valueOf (ed.getOID_Pessoa_Remetente ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa_Remetente () + "'";
    }

    if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 5 ");

    if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    }

    if (!"T".equals (ed.getDM_Tipo_Documento ())) {
      if ("M".equals (ed.getDM_Tipo_Documento ())) {
        sql += " and Conhecimentos.DM_Tipo_Documento = 'M'";
      }
      else {
        sql += " and Conhecimentos.DM_Tipo_Documento = 'C'";
      }
    }



//      if (String.valueOf(ed.getOID_Pessoa()) != null &&
//         !String.valueOf(ed.getOID_Pessoa()).equals("") &&
//         !String.valueOf(ed.getOID_Pessoa()).equals("null")){
//        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
//      }

    if (String.valueOf (ed.getOID_Vendedor ()) != null &&
        !String.valueOf (ed.getOID_Vendedor ()).equals ("") &&
        !String.valueOf (ed.getOID_Vendedor ()).equals ("null")) {
      if ("CT".equals (ed.getDM_Vendedor ())) {
        sql += " and Conhecimentos.OID_Vendedor = '" + ed.getOID_Vendedor () + "'";
      }
      else {
        sql += " and Clientes.OID_Vendedor = '" + ed.getOID_Vendedor () + "'";
      }
    }

    if (String.valueOf (ed.getOID_Veiculo ()) != null &&
        !String.valueOf (ed.getOID_Veiculo ()).equals ("") &&
        !String.valueOf (ed.getOID_Veiculo ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Veiculo = '" + ed.getOID_Veiculo () + "'";
    }

    if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 10 ");

    if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
    }

    if (ed.getVL_Margem_Inicial () != 0) {
      sql += " and ((Conhecimentos.vl_total_frete-Conhecimentos.vl_total_custo)/Conhecimentos.vl_total_frete*100) >= " + ed.getVL_Margem_Inicial ();
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 10 ");

    if (ed.getVL_Margem_Final () != 0) {
      sql += " and ((Conhecimentos.vl_total_frete-Conhecimentos.vl_total_custo)/Conhecimentos.vl_total_frete*100) <= " + ed.getVL_Margem_Final ();
    }

    if (ed.getDM_Situacao_Cliente () != null && !"null".equals (ed.getDM_Situacao_Cliente ()) && !"".equals (ed.getDM_Situacao_Cliente ())) {
      sql += " and Clientes.DM_Situacao = '" + ed.getDM_Situacao_Cliente () + "'";
    }

    if (String.valueOf (ed.getOID_Grupo_Economico()) != null && !String.valueOf (ed.getOID_Grupo_Economico ()).equals ("0")) {
        sql += " and Clientes.OID_Grupo_Economico = " + ed.getOID_Grupo_Economico ();
      }
 

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 20 ");

    if (String.valueOf (ed.getDM_Frete ()) != null &&
        !String.valueOf (ed.getDM_Frete ()).equals ("null") &&
        String.valueOf (ed.getDM_Frete ()).equals ("F")) {
      sql += " and Conhecimentos.NR_Peso <= 7500 ";
    }

    if (String.valueOf (ed.getDM_Frete ()) != null &&
        !String.valueOf (ed.getDM_Frete ()).equals ("null") &&
        String.valueOf (ed.getDM_Frete ()).equals ("C")) {
      sql += " and Conhecimentos.NR_Peso > 7500 ";
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 30 b ");

    if (DM_Quebra_Cliente.equals ("S") && ed.getDM_Pessoa ().equals ("R")) {
      sql += " order by conhecimentos.oid_pessoa ";
    }
    if (DM_Quebra_Cliente.equals ("S") && ed.getDM_Pessoa ().equals ("D")) {
      sql += " order by conhecimentos.oid_pessoa_destinatario ";
    }
    if (DM_Quebra_Cliente.equals ("S") && ed.getDM_Pessoa ().equals ("P")) {
      sql += " order by conhecimentos.oid_pessoa_pagador ";
    }

    if (DM_Quebra_Veiculo.equals ("S")) {
      sql += " order by conhecimentos.oid_Veiculo ";
      OID_Veiculo="PRIMEIRO";
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 40 ");

    if (DM_Quebra_Cidade.equals ("S")) {
      sql += " order by conhecimentos.oid_cidade, conhecimentos.oid_cidade_destino ";
    }

    if (DM_Quebra_Regiao.equals ("S")) {
      sql += " order by Regiao_Estado_Destino.oid_Regiao_Estado ";
    }

    if (DM_Quebra_Modal.equals ("S")) {
      sql += " order by conhecimentos.oid_modal ";
    }
    if (DM_Quebra_Grupo_Economico.equals ("S")) {
      sql += " order by clientes.oid_grupo_economico ";
    }

    // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 50 ");

    if (DM_Quebra_Situacao_Cliente.equals ("S")) {
      sql += " order by clientes.DM_Situacao ";
    }

    if (DM_Quebra_Vendedor.equals ("S")) {

      if ("CT".equals (ed.getDM_Vendedor ())) {
        sql += " order by conhecimentos.oid_Vendedor ";
      }
      else {
        sql += " order by Clientes.oid_Vendedor ";
      }
    }

    // System.out.println (" sql->>> " + sql);

    GerencialED edVolta = new GerencialED ();
    try {

      //// // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());
      
      // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 100 ");

      if (DM_Analitico_Sintetico.equals ("S")) {

        // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 102 ");

        Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

        while (res.next ()) {
          this.inicioTransacao ();
          this.executasql = this.sql;

          
          // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 110 ");

          OID_Pessoa_Lida = res.getString ("OID_Pessoa");
          if (ed.getDM_Pessoa ().equals ("D")) {
            OID_Pessoa_Lida = res.getString ("OID_Pessoa_Destinatario");
          }
          if (ed.getDM_Pessoa ().equals ("P")) {
            OID_Pessoa_Lida = res.getString ("OID_Pessoa_Pagador");
          }

          if (DM_Quebra_Cliente.equals ("S")) {
            // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 110 OID_Pessoa= " + OID_Pessoa);

            if (OID_Pessoa.equals ("") || OID_Pessoa_Lida.equals (OID_Pessoa)) {
              OID_Pessoa = OID_Pessoa_Lida;

              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 110 total= " + OID_Pessoa);

              OID_Pessoa = OID_Pessoa_Lida;
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");

            }
          }


          // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 150 ");

          if (DM_Quebra_Cidade.equals ("S")) {

            if (OID_Cidade_Origem == 999999 || res.getLong ("OID_Cidade") == OID_Cidade_Origem || res.getLong ("OID_Cidade_Destino") == OID_Cidade_Destino) {

              OID_Cidade_Origem = res.getLong ("OID_Cidade");
              OID_Cidade_Destino = res.getLong ("OID_Cidade_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {

                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Cidade_Origem = res.getLong ("OID_Cidade");
              OID_Cidade_Destino = res.getLong ("OID_Cidade_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Regiao.equals ("S")) {

            if (OID_Regiao_Destino == 999999 || res.getLong ("oid_Regiao_Destino") == OID_Regiao_Destino) {
              OID_Regiao_Destino = res.getLong ("oid_Regiao_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");

            }
            else {
              if (vl_total_frete > 0) {

                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Regiao_Destino = res.getLong ("oid_Regiao_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Modal.equals ("S")) {
            if (OID_Modal == 999999 || res.getInt ("OID_Modal") == OID_Modal) {
              OID_Modal = res.getInt ("OID_Modal");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Modal = res.getInt ("OID_Modal");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Veiculo.equals ("S")) {

            if (OID_Veiculo.equals ("PRIMEIRO") || res.getString ("OID_Veiculo").equals (OID_Veiculo)) {
              OID_Veiculo = res.getString ("OID_Veiculo");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                inclui (edVolta = carregaED (ed));
              }
              OID_Veiculo = res.getString ("OID_Veiculo");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 200 ");

          if (DM_Quebra_Situacao_Cliente.equals ("S")) {
            if (DM_Situacao_Cliente.equals ("") || DM_Situacao_Cliente.equals (res.getString ("DM_Situacao_Cliente"))) {
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
            DM_Situacao_Cliente = res.getString ("DM_Situacao_Cliente");
          }

          if (DM_Quebra_Grupo_Economico.equals ("S")) {
            if (OID_Grupo_Economico == 999999 || res.getInt ("OID_Grupo_Economico") == OID_Grupo_Economico) {
              OID_Grupo_Economico = res.getInt ("OID_Grupo_Economico");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Grupo_Economico = res.getInt ("OID_Grupo_Economico");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 300 ");

          if (DM_Quebra_Vendedor.equals ("S")) {

            if (OID_Pessoa.equals ("PRIMEIRO") || res.getString ("OID_Vendedor").equals (OID_Pessoa)) {
              OID_Pessoa = res.getString ("OID_Vendedor");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                inclui (edVolta = carregaED (ed));
              }
              OID_Pessoa = res.getString ("OID_Vendedor");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }
          this.fimTransacao (true);

        }

        // System.out.println (" fim do sort- tot=" + vl_total_frete);

        this.inicioTransacao ();
        this.executasql = this.sql;
        inclui (edVolta = carregaED (edVolta));

        ed.setNR_Sort (Nr_Sort);
        ed.setTVL_Total_Frete (tvl_total_frete);
        ed.setTVL_Margem (tvl_margem);
        ed.setTVL_Notas_Fiscais (tvl_notas_fiscais);
        ed.setTNR_Despachos (tnr_despachos);
        ed.setTNR_Peso (tnr_peso);
        ed.setTNR_Peso_Cubado (tnr_peso_cubado);
        ed.setTNR_Volumes (tnr_volumes);
        this.fimTransacao (true);

        // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> anter imp ");

        b = this.imprime_Gerencial_Conhecimento (ed);
   
      }

      // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> 500 ");

      if (DM_Analitico_Sintetico.equals ("A")) {
        GerencialRL conRL = new GerencialRL ();

        b = conRL.geraRelConhecCusto (res , ed);
      }
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] geraAnalise_Gerencial_Clientes (GerencialED edGerencial) throws Excecoes {

    byte[] b = null;
    // System.out.println (" BD 0 geraAnalise_Gerencial_Clientes");

    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

    int mes1 = new Integer (edGerencial.getNM_Mes_Inicial ()).intValue ();
    int mes2 = new Integer (edGerencial.getNM_Mes_Final ()).intValue ();
    int ano1 = new Integer (edGerencial.getNM_Ano_Inicial ()).intValue ();
    int ano2 = new Integer (edGerencial.getNM_Ano_Final ()).intValue ();
    int mesatual = mes1;
    int anoatual = ano1;
    int mesler = 12;

    while (anoatual <= ano2) {
      if (anoatual == ano2) {
        mesler = mes2;
      }
      while (mesatual <= mesler) {
        String mes_atual = String.valueOf (mesatual);
        if (mesatual < 10) {
          mes_atual = "0" + mes_atual;
        }
        data1 = "01/" + mes_atual + "/" + String.valueOf (anoatual);
        data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_atual + "/" + String.valueOf (anoatual));
        ordem++;
        
        this.totaliza_cliente (edGerencial);
        mesatual++;
      }
      mesatual = 1;
      anoatual++;
    }

    edGerencial.setTVL_Total_Frete (tvl_total_frete);
    edGerencial.setTVL_Margem (tvl_margem);
    edGerencial.setTVL_Notas_Fiscais (tvl_notas_fiscais);
    edGerencial.setTNR_Despachos (tnr_despachos);
    edGerencial.setTNR_Peso (tnr_peso);
    edGerencial.setTNR_Peso_Cubado (tnr_peso_cubado);
    edGerencial.setTNR_Volumes (tnr_volumes);

    return this.imprime_Gerencial_Cliente (edGerencial);
  }

  public byte[] geraAnalise_Gerencial_Modal (GerencialED edGerencial) throws Excecoes {

	    byte[] b = null;
	    // System.out.println (" BD 0 geraAnalise_Gerencial_Modal");

	    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

	    int mes1 = new Integer (edGerencial.getNM_Mes_Inicial ()).intValue ();
	    int mes2 = new Integer (edGerencial.getNM_Mes_Final ()).intValue ();
	    int ano1 = new Integer (edGerencial.getNM_Ano_Inicial ()).intValue ();
	    int ano2 = new Integer (edGerencial.getNM_Ano_Final ()).intValue ();
	    int mesatual = mes1;
	    int anoatual = ano1;
	    int mesler = 12;

	    while (anoatual <= ano2) {
	      if (anoatual == ano2) {
	        mesler = mes2;
	      }
	      while (mesatual <= mesler) {
	        String mes_atual = String.valueOf (mesatual);
	        if (mesatual < 10) {
	          mes_atual = "0" + mes_atual;
	        }
	        data1 = "01/" + mes_atual + "/" + String.valueOf (anoatual);
	        data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_atual + "/" + String.valueOf (anoatual));
	        ordem++;
	        
	        this.totaliza_modal (edGerencial);
	        mesatual++;
	      }
	      mesatual = 1;
	      anoatual++;
	    }

	    edGerencial.setTVL_Total_Frete (tvl_total_frete);
	    edGerencial.setTVL_Margem (tvl_margem);
	    edGerencial.setTVL_Notas_Fiscais (tvl_notas_fiscais);
	    edGerencial.setTNR_Despachos (tnr_despachos);
	    edGerencial.setTNR_Peso (tnr_peso);
	    edGerencial.setTNR_Peso_Cubado (tnr_peso_cubado);
	    edGerencial.setTNR_Volumes (tnr_volumes);

	    b = this.imprime_Gerencial_Modal (edGerencial);

	    return b;
  }

  public byte[] geraAnalise_Gerencial_Tabelas_Fretes (GerencialED edGerencial) throws Excecoes {

	    byte[] b = null;
	     //System.out.println (" BD 0 geraAnalise_Gerencial_Tabelas_Fretes");

	    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

	    int mes1 = new Integer (edGerencial.getNM_Mes_Inicial ()).intValue ();
	    int mes2 = new Integer (edGerencial.getNM_Mes_Final ()).intValue ();
	    int ano1 = new Integer (edGerencial.getNM_Ano_Inicial ()).intValue ();
	    int ano2 = new Integer (edGerencial.getNM_Ano_Final ()).intValue ();
	    int mesatual = mes1;
	    int anoatual = ano1;
	    int mesler = 12;
	    
	    if ("RELTAB1".equals(edGerencial.getDM_Relatorio())){
	        data1 = "01/" + edGerencial.getNM_Mes_Inicial () + "/" + edGerencial.getNM_Ano_Inicial ();
	        data2 = Data.getDataMaximaNoMes ("31" + "/" + edGerencial.getNM_Mes_Final () + "/" + edGerencial.getNM_Ano_Final ());
	        this.totaliza_tabela_frete (edGerencial);
	    }else {
		    while (anoatual <= ano2) {
		      if (anoatual == ano2) {
		        mesler = mes2;
		      }
		      while (mesatual <= mesler) {
		        String mes_atual = String.valueOf (mesatual);
		        if (mesatual < 10) {
		          mes_atual = "0" + mes_atual;
		        }
		        data1 = "01/" + mes_atual + "/" + String.valueOf (anoatual);
		        data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_atual + "/" + String.valueOf (anoatual));
		        ordem++;
		        
		        this.totaliza_tabela_frete (edGerencial);
		        mesatual++;
		      }
		      mesatual = 1;
		      anoatual++;
		    }
	    }    
	    edGerencial.setTNR_Despachos (tnr_despachos);

	    b = this.imprime_Gerencial_Tabelas_Fretes (edGerencial);

	    return b;
  }
  
  public byte[] geraAnalise_Gerencial_Fornecedores (GerencialED edGerencial) throws Excecoes {

	    byte[] b = null;
	    // System.out.println (" BD 0 geraAnalise_Gerencial_Fornecedores");

	    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

	    int mes1 = new Integer (edGerencial.getNM_Mes_Inicial ()).intValue ();
	    int mes2 = new Integer (edGerencial.getNM_Mes_Final ()).intValue ();
	    int ano1 = new Integer (edGerencial.getNM_Ano_Inicial ()).intValue ();
	    int ano2 = new Integer (edGerencial.getNM_Ano_Final ()).intValue ();
	    int mesatual = mes1;
	    int anoatual = ano1;
	    int mesler = 12;

	    while (anoatual <= ano2) {
	      if (anoatual == ano2) {
	        mesler = mes2;
	      }
	      while (mesatual <= mesler) {
	        String mes_atual = String.valueOf (mesatual);
	        if (mesatual < 10) {
	          mes_atual = "0" + mes_atual;
	        }
	        data1 = "01/" + mes_atual + "/" + String.valueOf (anoatual);
	        data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_atual + "/" + String.valueOf (anoatual));
	        ordem++;

	        if (edGerencial.getOID_Pessoa() != null && edGerencial.getOID_Pessoa().length()>4) {
		        totaliza_fornecedor (edGerencial);
	        }else {    
	            try {

	        	String sql = " SELECT Movimentos_Conhecimentos.oid_Fornecedor " +
	              " FROM  Movimentos_Conhecimentos, Conhecimentos, Pessoas " +
	              " WHERE Conhecimentos.oid_Conhecimento = Movimentos_Conhecimentos.oid_Conhecimento " +
	              " AND   Movimentos_Conhecimentos.oid_Fornecedor = Pessoas.oid_Pessoa " +
	              " AND   Conhecimentos.DT_Emissao >='" + data1 + "'"+
	              " AND   Conhecimentos.DT_Emissao <='" + data2 + "'"+
	              " GROUP BY oid_Fornecedor ";
	    
		          // System.out.println (sql);
		
		          ResultSet resFor = this.executasql.executarConsulta (sql.toString ());
		          while (resFor.next ()) {
		            // System.out.println (" oid_Fornecedor ->> " + resFor.getString ("oid_Fornecedor") );
		            edGerencial.setOID_Pessoa (resFor.getString ("oid_Fornecedor"));
		            totaliza_fornecedor (edGerencial);
		          }    
		          edGerencial.setOID_Pessoa ("");
		          totaliza_fornecedor (edGerencial);
	            }
	            catch (Exception exc) {
	              Excecoes excecoes = new Excecoes ();
	              excecoes.setClasse (this.getClass ().getName ());
	              excecoes.setMensagem ("Erro ao recuperar registro");
	              excecoes.setMetodo ("getByRecord(ContaED)");
	              excecoes.setExc (exc);
	              throw excecoes;
	            }
		          
	        }
	        
	        
	        mesatual++;
	      }
	      mesatual = 1;
	      anoatual++;
	    }

	    edGerencial.setTVL_Total_Frete (tvl_total_frete);
	    edGerencial.setTVL_Margem (tvl_margem);
	    edGerencial.setTVL_Notas_Fiscais (tvl_notas_fiscais);
	    edGerencial.setTNR_Despachos (tnr_despachos);
	    edGerencial.setTNR_Peso (tnr_peso);
	    edGerencial.setTNR_Peso_Cubado (tnr_peso_cubado);
	    edGerencial.setTNR_Volumes (tnr_volumes);


	    b = this.imprime_Gerencial_Fornecedores (edGerencial);

	    return b;
	  }
  
  public byte[] geraAnalise_Gerencial_Clientes_Modal (GerencialED edGerencial) throws Excecoes {

    byte[] b = null;
    ResultSet res = null;
    ResultSet res2 = null;
    // System.out.println (" BD 0 geraAnalise_Gerencial_Clientes_Modal");

    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();
    data1 = "01/" + edGerencial.getNM_Mes_Inicial()+ "/" + edGerencial.getNM_Ano_Inicial ();
    data2 = Data.getDataMaximaNoMes ("31" + "/" + edGerencial.getNM_Mes_Final ()+ "/" + edGerencial.getNM_Ano_Final ());

      String sql = " SELECT " +
          " Clientes.oid_Cliente, Pessoas.NM_Razao_Social " +
          " FROM  Clientes, Pessoas " +
          " WHERE Clientes.oid_Cliente = Pessoas.oid_Pessoa";

      if (String.valueOf (edGerencial.getOID_Pessoa ()) != null &&
          !String.valueOf (edGerencial.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (edGerencial.getOID_Pessoa ()).equals ("null")) {
        sql += " and Clientes.oid_Cliente = '" + edGerencial.getOID_Pessoa () + "'";
      }
      if (String.valueOf (edGerencial.getOID_Grupo_Economico ()) != null && !String.valueOf (edGerencial.getOID_Grupo_Economico ()).equals ("0")) {
        sql += " and Clientes.OID_Grupo_Economico = " + edGerencial.getOID_Grupo_Economico ();
      }
      sql +=" ORDER BY Pessoas.NM_Razao_Social " ;
      try {
    
        res = this.executasql.executarConsulta (sql.toString ());
        while (res.next ()) {

          // System.out.println (" Cliente ->> " + res.getString ("oid_Cliente") + "- " + res.getString ("NM_Razao_Social"));

          sql = " SELECT Conhecimentos.oid_Modal " +
              " FROM  Conhecimentos " +
              " WHERE Conhecimentos.oid_Pessoa_Pagador = '" +res.getString ("oid_Cliente")+ "'" +
              " AND   Conhecimentos.DT_Emissao >='" + data1 + "'"+
              " AND   Conhecimentos.DT_Emissao <='" + data2 + "'"+
              " GROUP BY oid_Modal ";
    
          // System.out.println (sql);

          res2 = this.executasql.executarConsulta (sql.toString ());
          while (res2.next ()) {
  
            // System.out.println (" Cliente ->> " + res2.getString ("oid_Modal") + "- " + res.getString ("NM_Razao_Social"));

            GerencialED ed = new GerencialED ();
            ed.setOID_Pessoa (res.getString ("oid_Cliente"));
            ed.setNM_Campo ( (res.getString ("NM_Razao_Social") + "                               ").substring (0 , 25));
            ed.setOID_Modal(res2.getLong("oid_Modal"));
            ed.setNR_Sort (Nr_Sort);

            this.totaliza_cliente (ed);
          }    
        }
      }
      catch (Exception exc) {
        Excecoes excecoes = new Excecoes ();
        excecoes.setClasse (this.getClass ().getName ());
        excecoes.setMensagem ("Erro ao recuperar registro");
        excecoes.setMetodo ("getByRecord(ContaED)");
        excecoes.setExc (exc);
        throw excecoes;
      }

    edGerencial.setTVL_Total_Frete (tvl_total_frete);
    edGerencial.setTVL_Margem (tvl_margem);
    edGerencial.setTVL_Notas_Fiscais (tvl_notas_fiscais);
    edGerencial.setTNR_Despachos (tnr_despachos);
    edGerencial.setTNR_Peso (tnr_peso);
    edGerencial.setTNR_Peso_Cubado (tnr_peso_cubado);
    edGerencial.setTNR_Volumes (tnr_volumes);

    return this.imprime_Gerencial_Cliente_Modal (edGerencial);
  }

  public byte[] geraAnalise_Gerencial_Clientes_Anual (GerencialED edGerencial) throws Excecoes {

    byte[] b = null;
    // System.out.println (" BD 0 geraAnalise_Gerencial_Clientes_Anual");

    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

    totaliza_cliente_Anual (edGerencial);
    this.totaliza_Ordens_Fretes_Terceiro_Anual (edGerencial);

    return this.imprime_Gerencial_Cliente (edGerencial);
  }

  //
  public byte[] geraAnalise_Gerencial_Veiculos_Anual (GerencialED edGerencial) throws Excecoes {

    byte[] b = null;
    // System.out.println (" BD 0 geraAnalise_Gerencial_Veiculos_Anual");

    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

    totaliza_Veiculo_Anual (edGerencial);

    return this.imprime_Gerencial_Veiculos_Anual (edGerencial);
  }

  public byte[] geraAnalise_Gerencial_Movimento_Conhecimentos (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    OID_Gerencial = 0;

    // System.out.println ("geraAnalise_Gerencial_Movimento_Conhecimentos >>>  " + ed.getDM_Relatorio ());

    DM_Quebra_Tipo_Movimento = "N";
    DM_Quebra_Fornecedor = "N";
    DM_Analitico_Sintetico = "A";

    if (ed.getDM_Relatorio ().equals ("MC1")) {
      DM_Quebra_Fornecedor = "S";
    }
//       if (ed.getDM_Relatorio().equals("CSV")) DM_Quebra_Tipo_Movimento="S";
//       if (ed.getDM_Relatorio().equals("CSM")) DM_Quebra_Modal="S";
//       if (ed.getDM_Relatorio().equals("CSP")) DM_Quebra_Produto="S";
//       if (ed.getDM_Relatorio().equals("CSG")) DM_Quebra_Grupo_Economico="S";
//       if (ed.getDM_Relatorio().equals("CSCID")) DM_Quebra_Cidade="S";

//      if (ed.getDM_Relatorio().equals("MC1")) DM_Analitico_Sintetico="A";
//      if (ed.getDM_Relatorio().equals("MC2")) DM_Analitico_Sintetico="A";

    if (ed.getDM_Relatorio ().equals ("CSF")) {
      DM_Quebra_Fornecedor = "S";
      DM_Analitico_Sintetico = "S";
    }

    if (DM_Analitico_Sintetico.equals ("A")) {
      sql = " select Conhecimentos.oid_Unidade, Conhecimentos.DT_Emissao, Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, Conhecimentos.nr_volumes, Conhecimentos.VL_NOTA_FISCAL, Conhecimentos.dm_tipo_pagamento, Movimentos_Conhecimentos.VL_Previsto, Movimentos_Conhecimentos.VL_Realizado, Movimentos_Conhecimentos.NR_Documento, Movimentos_Conhecimentos.Oid_Lote_Fornecedor, Movimentos_Conhecimentos.Oid_Tipo_Movimento, Movimentos_Conhecimentos.Oid_Fornecedor, Conhecimentos.vl_total_frete, Movimentos_Conhecimentos.vl_movimento, Movimentos_Conhecimentos.vl_realizado, Conhecimentos.vl_total_custo, " +
          " conhecimentos.nr_peso, Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_pagador , Conhecimentos.oid_Modal, Conhecimentos.oid_Pessoa_Destinatario,  conhecimentos.nr_peso_cubado " +
          " from Conhecimentos, Movimentos_Conhecimentos " +
          " WHERE Conhecimentos.oid_Conhecimento      = Movimentos_Conhecimentos.oid_Conhecimento ";
    }
    if (DM_Analitico_Sintetico.equals ("S")) {
      sql = " select Conhecimentos.nr_volumes, Conhecimentos.VL_NOTA_FISCAL, Movimentos_Conhecimentos.VL_Previsto, Movimentos_Conhecimentos.VL_Realizado, Movimentos_Conhecimentos.Oid_Fornecedor, Conhecimentos.vl_total_frete, Movimentos_Conhecimentos.vl_movimento, Conhecimentos.vl_total_custo, " +
          " conhecimentos.nr_peso, conhecimentos.nr_peso_cubado " +
          " FROM  Conhecimentos, Movimentos_Conhecimentos " +
          " WHERE Conhecimentos.oid_Conhecimento      = Movimentos_Conhecimentos.oid_Conhecimento ";
    }

    //sql +=" AND Tipos_Movimentos.DM_Tipo_Movimento = 'D'";

    sql += " AND Conhecimentos.DM_Impresso = 'S' " +
        " AND Conhecimentos.DM_Situacao <> 'C' " +
        " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
        " AND Conhecimentos.VL_Total_Frete > 0";

    if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
    }

    if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Modal = " + ed.getOID_Modal ();
    }

    if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
    }

    if (String.valueOf (ed.getOID_Cidade_Origem ()) != null && !String.valueOf (ed.getOID_Cidade_Origem ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Cidade = " + ed.getOID_Cidade_Origem ();
    }

    if (String.valueOf (ed.getOID_Cidade_Destino ()) != null && !String.valueOf (ed.getOID_Cidade_Destino ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino ();
    }

    if (String.valueOf (ed.getOID_Pessoa_Remetente ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa_Remetente () + "'";
    }

    if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }

    if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    }

    if (String.valueOf (ed.getOID_Pessoa_Fornecedor ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Fornecedor ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Fornecedor ()).equals ("null")) {
      sql += " and Movimentos_Conhecimentos.OID_Fornecedor = '" + ed.getOID_Pessoa_Fornecedor () + "'";
    }

    if (String.valueOf (ed.getOID_Tipo_Movimento ()) != null &&
        !String.valueOf (ed.getOID_Tipo_Movimento ()).equals ("") &&
        !String.valueOf (ed.getOID_Tipo_Movimento ()).equals ("null")) {
      sql += " and Movimentos_Conhecimentos.OID_Tipo_Movimento = " + ed.getOID_Tipo_Movimento ();
    }

//      if (String.valueOf(ed.getOID_Pessoa()) != null &&
//         !String.valueOf(ed.getOID_Pessoa()).equals("") &&
//         !String.valueOf(ed.getOID_Pessoa()).equals("null")){
//        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
//      }

    if (String.valueOf (ed.getOID_Vendedor ()) != null &&
        !String.valueOf (ed.getOID_Vendedor ()).equals ("") &&
        !String.valueOf (ed.getOID_Vendedor ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Vendedor = '" + ed.getOID_Vendedor () + "'";
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

    if (String.valueOf (ed.getDT_Movimento_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("null")) {
      sql += " and Movimentos_Conhecimentos.DT_Movimento_Conhecimento >= '" + ed.getDT_Movimento_Inicial () + "'";
    }
    if (String.valueOf (ed.getDT_Movimento_Final ()) != null &&
        !String.valueOf (ed.getDT_Movimento_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Movimento_Final ()).equals ("null")) {
      sql += " and Movimentos_Conhecimentos.DT_Movimento_Conhecimento <= '" + ed.getDT_Movimento_Final () + "'";
    }

    if (String.valueOf (ed.getDM_Frete ()) != null &&
        !String.valueOf (ed.getDM_Frete ()).equals ("null") &&
        String.valueOf (ed.getDM_Frete ()).equals ("F")) {
      sql += " and Conhecimentos.NR_Peso <= 7500 ";
    }

    if (String.valueOf (ed.getDM_Frete ()) != null &&
        !String.valueOf (ed.getDM_Frete ()).equals ("null") &&
        String.valueOf (ed.getDM_Frete ()).equals ("C")) {
      sql += " and Conhecimentos.NR_Peso > 7500 ";
    }

    if (DM_Quebra_Fornecedor.equals ("S")) {
      sql += " and Movimentos_Conhecimentos.oid_Fornecedor is not null ";
      sql += " order by Movimentos_Conhecimentos.oid_Fornecedor ";
    }

    // System.out.println (" sql " + sql);

    GerencialED edVolta = new GerencialED ();

    try {

      //// // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      // System.out.println ("bd 4 1");

      if (DM_Analitico_Sintetico.equals ("S")) {

        Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

        while (res.next ()) {
          this.inicioTransacao ();
          this.executasql = this.sql;

          // System.out.println ("oid_fornecedor=>" + res.getString ("OID_Fornecedor"));

          if (DM_Quebra_Tipo_Movimento.equals ("S")) {
            if (OID_Tipo_Movimento == 999999 || res.getInt ("OID_Tipo_Movimento") == OID_Tipo_Movimento) {
              OID_Tipo_Movimento = res.getInt ("OID_Tipo_Movimento");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Tipo_Movimento = res.getInt ("OID_Tipo_Movimento");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Fornecedor.equals ("S")) {

            if (OID_Pessoa.equals ("PRIMEIRO") || res.getString ("OID_Fornecedor").equals (OID_Pessoa)) {
              OID_Pessoa = res.getString ("OID_Fornecedor");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {

                inclui (edVolta = carregaED (ed));
              }
              OID_Pessoa = res.getString ("OID_Fornecedor");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }
          this.fimTransacao (true);
        }

        // System.out.println ("geraAnalise_Gerencial_Conhecimentos quase ");

        this.inicioTransacao ();
        this.executasql = this.sql;
        inclui (edVolta = carregaED (edVolta));

        ed.setNR_Sort (Nr_Sort);
        ed.setTVL_Total_Frete (tvl_total_frete);
        ed.setTVL_Margem (tvl_margem);
        ed.setTVL_Notas_Fiscais (tvl_notas_fiscais);
        ed.setTNR_Despachos (tnr_despachos);
        ed.setTNR_Peso (tnr_peso);
        ed.setTNR_Peso_Cubado (tnr_peso_cubado);
        ed.setTNR_Volumes (tnr_volumes);
        this.fimTransacao (true);

        // System.out.println ("geraAnalise_Gerencial_Conhecimentos >>> anter imp ");
        //b = this.imprime_Gerencial_Movimento_Conhecimento (ed);

        b = this.imprime_Gerencial_Conhecimento (ed);

      }

      if (DM_Analitico_Sintetico.equals ("A")) {

        // System.out.println ("bd 5 1");

        GerencialRL conRL = new GerencialRL ();
        b = conRL.geraAnalise_Gerencial_Movimento_Conhecimentos_Modelo01 (res , ed);

      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] geraAnalise_Gerencial_Financeira (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    ResultSet res = null;

    try {

      Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

      if (ed.getDM_Cobranca ().equals ("S")) {
        sql = " select Duplicatas.OID_Carteira, Duplicatas.VL_Saldo, Duplicatas.DT_Vencimento ";
        sql += " From  Duplicatas ";
        sql += " WHere Duplicatas.VL_Saldo > 0  ";
        if (ed.getDM_Vencido ().equals ("N")) {
          sql += " and Duplicatas.DT_Vencimento >= '" + ed.getDT_1 () + "'";
        }
        if (ed.getDM_Vencer ().equals ("N")) {
          sql += " and Duplicatas.DT_Vencimento <= '" + ed.getDT_5 () + "'";
        }

        // System.out.println (" sql " + sql);

        res = this.executasql.executarConsulta (sql.toString ());

        while (res.next ()) {
          OID_Carteira = res.getLong ("OID_Carteira");
          data = dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento"));
          valor_mes1 = res.getDouble ("VL_Saldo");
          NM_Campo = "Tit.Receber";
          this.totaliza_financeiro (ed);
        }
      }

      if (ed.getDM_Contas_Pagar ().equals ("S")) {

        sql = " select Compromissos.OID_Conta, Compromissos.VL_Saldo, Compromissos.DT_Vencimento ";
        sql += " From  Compromissos ";
        sql += " WHere Compromissos.VL_Saldo > 0  ";
        if (ed.getDM_Vencido ().equals ("N")) {
          sql += " and Compromissos.DT_Vencimento >= '" + ed.getDT_1 () + "'";
        }
        if (ed.getDM_Vencer ().equals ("N")) {
          sql += " and Compromissos.DT_Vencimento <= '" + ed.getDT_5 () + "'";
        }

        res = this.executasql.executarConsulta (sql.toString ());

        while (res.next ()) {
          OID_Conta = res.getLong ("oid_conta");
          data = dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento"));
          valor_mes1 = res.getDouble ("VL_Saldo");
          NM_Campo = "Compromissos";
          this.totaliza_financeiro (ed);
        }
      }
      if (ed.getDM_Banco ().equals ("S")) {
        sql = " select OID_Conta_Corrente, DT_Programada, VL_Lote_Pagamento ";
        sql += " From  Lotes_Pagamentos ";
        sql += " WHere Lotes_Pagamentos.dt_compensacao is null and VL_Lote_Pagamento > 0 ";
        if (ed.getDM_Vencido ().equals ("N")) {
          sql += " and Lotes_Pagamentos.DT_Programada >= '" + ed.getDT_1 () + "'";
        }
        if (ed.getDM_Vencer ().equals ("N")) {
          sql += " and Lotes_Pagamentos.DT_Programada <= '" + ed.getDT_5 () + "'";
        }

        res = this.executasql.executarConsulta (sql.toString ());

        while (res.next ()) {
          OID_Conta_Corrente = res.getLong ("OID_Conta_Corrente");
          data = dataFormatada.getDT_FormataData (res.getString ("DT_Programada"));
          valor_mes1 = res.getDouble ("VL_Lote_Pagamento");
          NM_Campo = "Bancos";
          this.totaliza_financeiro (ed);
        }
      }

      b = this.imprime_Gerencial_financeiro (ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] geraAnalise_Gerencial_Veiculos (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
    int mes2 = 12;
    int ano2 = 2010;

    if ("--".equals (ed.getNM_Mes_Final ())) {
      mes2 = mes1;
      ed.setNM_Mes_Final (ed.getNM_Mes_Inicial ());
    }
    else {
      mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
    }
    if ("----".equals (ed.getNM_Ano_Final ())) {
      ano2 = ano1;
      ed.setNM_Ano_Final (ed.getNM_Ano_Inicial ());
    }
    else {
      ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();
    }

    String mes_1 = String.valueOf (mes1);
    if (mes1 < 10) {
      mes_1 = "0" + mes_1;
    }
    data1 = "01/" + mes_1 + "/" + String.valueOf (ano1);

    String mes_2 = String.valueOf (mes2);
    if (mes2 < 10) {
      mes_2 = "0" + mes_2;
    }
    data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_2 + "/" + String.valueOf (ano2));

    ResultSet res = null;

    try {

      // System.out.println (" BD 0 geraAnalise_Gerencial_Veiculos");

      Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

      sql = " SELECT Conhecimentos.oid_Veiculo, " +
          "        Conhecimentos.VL_Frete_Peso, " +
          "        Conhecimentos.VL_Pedagio, " +
          "        Conhecimentos.VL_Total_Frete, " +
          "        Conhecimentos.NR_Peso  " +
          " From  Conhecimentos, Unidades, Modal " +
          " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
          " AND Conhecimentos.oid_Modal = Modal.oid_Modal " +
          " AND Unidades.DM_Situacao       <> 'I' " +
          " AND Conhecimentos.DM_Impresso  = 'S' " +
          " AND Conhecimentos.VL_Total_Frete >  0  " +
          " AND Conhecimentos.DM_Situacao   <> 'C' " +
          " AND Conhecimentos.DT_Emissao >= '" + data1 + "'" +
          " AND Conhecimentos.DT_Emissao <= '" + data2 + "'";

      if ("R2".equals (ed.getDM_Relatorio ())) {
        sql = " SELECT Ordens_Fretes.oid_Veiculo, " +
            "        Conhecimentos.VL_Frete_Peso, " +
            "        Conhecimentos.VL_Pedagio, " +
            "        Conhecimentos.VL_Total_Frete, " +
            "        Conhecimentos.NR_Peso  " +
            " From  Conhecimentos, Unidades, Modal, Ordens_Fretes, Viagens, Ordens_Manifestos  " +
            " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
            " AND Conhecimentos.oid_Modal = Modal.oid_Modal " +
            " AND Conhecimentos.oid_Conhecimento = Viagens.oid_Conhecimento " +
            " AND Viagens.oid_Manifesto = Ordens_Manifestos.oid_Manifesto " +
            " AND Ordens_Manifestos.oid_Ordem_Frete = Ordens_Fretes.oid_Ordem_Frete " +
            " AND Conhecimentos.DM_Impresso  = 'S' " +
            " AND Conhecimentos.VL_Total_Frete >  0  " +
            " AND Conhecimentos.DM_Situacao   <> 'C' " +
            " AND Ordens_Fretes.VL_Ordem_Frete >  0  " +
            " AND Ordens_Fretes.DM_Frete   =  'P' " +
            " AND Ordens_Fretes.DM_Impresso   =  'S' " +
            " AND Ordens_Fretes.DT_Emissao >= '" + data1 + "'" +
            " AND Ordens_Fretes.DT_Emissao <= '" + data2 + "'";
      }

      if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
        sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }

      if ("R2".equals (ed.getDM_Relatorio ())) {
        sql += " Order By  Ordens_Fretes.oid_Veiculo ";
      }
      else {
        sql += " Order By  Conhecimentos.oid_Veiculo ";
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      NM_Campo = "Veiculos";
      NM_Campo2 = "Frete Proprio";

      vl_total_frete = 0;
      vl_frete_liquido = 0;
      vl_ordem_frete_terceiro = 0;
      while (res.next ()) {
        if (!OID_Veiculo.equals (res.getString ("OID_Veiculo")) && vl_total_frete > 0) {

          this.totaliza_veiculo (ed);
          vl_frete_liquido = 0;
          vl_total_frete = 0;
          nr_peso = 0;
          nr_despachos = 0;
        }
        OID_Veiculo = res.getString ("OID_Veiculo");
        vl_total_frete += res.getDouble ("VL_Total_Frete");
        vl_frete_liquido += (res.getDouble ("VL_Frete_Peso") + res.getDouble ("VL_Pedagio"));
        nr_peso += res.getDouble ("NR_Peso");
        nr_despachos++;

      }
      if (vl_total_frete > 0) {
        this.totaliza_veiculo (ed);
      }

      sql = " SELECT Ordens_Fretes.oid_Veiculo, " +
          "        Ordens_Fretes.VL_Ordem_Frete  " +
          " From   Ordens_Fretes, Unidades " +
          " WHERE Ordens_Fretes.oid_Unidade = Unidades.oid_Unidade " +
          " AND Ordens_Fretes.VL_Ordem_Frete >  0  " +
          " AND Ordens_Fretes.DM_Frete   =  'T' " +
          " AND Ordens_Fretes.DM_Impresso   =  'S' " +
          " AND Ordens_Fretes.DT_Emissao >= '" + data1 + "'" +
          " AND Ordens_Fretes.DT_Emissao <= '" + data2 + "'";

      if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
        sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
      }

      sql += " Order By  Ordens_Fretes.oid_Veiculo ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      NM_Campo = "Veiculos";
      NM_Campo2 = "Frete Terceiro";

      vl_ordem_frete_terceiro = 0;
      vl_frete_liquido = 0;
      vl_total_frete = 0;
      nr_peso = 0;
      nr_despachos = 0;

      OID_Veiculo = "";
      while (res.next ()) {
        // System.out.println (" OID_Veiculo " + OID_Veiculo + " vl_ordem_frete " + vl_ordem_frete_terceiro);
        if (!OID_Veiculo.equals (res.getString ("OID_Veiculo")) && vl_ordem_frete_terceiro > 0) {
          this.totaliza_veiculo (ed);
          vl_ordem_frete_terceiro = 0;
          nr_peso = 0;
          nr_despachos = 0;
        }
        OID_Veiculo = res.getString ("OID_Veiculo");
        vl_ordem_frete_terceiro += res.getDouble ("VL_Ordem_Frete");
        nr_despachos++;

      }
      if (vl_ordem_frete_terceiro > 0) {
        this.totaliza_veiculo (ed);
      }

      if ("R2".equals (ed.getDM_Relatorio ())) {

        sql = " SELECT Ordens_Fretes.oid_Veiculo, " +
            "        Ordens_Fretes.VL_Ordem_Frete  " +
            " From   Ordens_Fretes, Unidades " +
            " WHERE Ordens_Fretes.oid_Unidade = Unidades.oid_Unidade " +
            " AND Ordens_Fretes.VL_Ordem_Frete >  0  " +
            " AND Ordens_Fretes.DM_Frete   =  'P' " +
            " AND Ordens_Fretes.DM_Impresso   =  'S' " +
            " AND Ordens_Fretes.DT_Emissao >= '" + data1 + "'" +
            " AND Ordens_Fretes.DT_Emissao <= '" + data2 + "'";

        if (String.valueOf (ed.getOID_Empresa ()) != null &&
            !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
          sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa ();
        }

        if (String.valueOf (ed.getOID_Unidade ()) != null &&
            !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
          sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
        }

        sql += " Order By  Ordens_Fretes.oid_Veiculo ";

        // System.out.println (" sql " + sql);

        res = this.executasql.executarConsulta (sql.toString ());

        NM_Campo = "Veiculos";
        NM_Campo2 = "Ordem Frete";
        vl_ordem_frete = 0;
        OID_Veiculo = "";
        while (res.next ()) {
          if (!OID_Veiculo.equals (res.getString ("OID_Veiculo")) &&
              vl_ordem_frete > 0) {
            this.totaliza_veiculo (ed);
            vl_ordem_frete = 0;
          }
          OID_Veiculo = res.getString ("OID_Veiculo");
          vl_ordem_frete += res.getDouble ("VL_Ordem_Frete");
        }
        if (vl_ordem_frete > 0) {
          this.totaliza_veiculo (ed);
        }
      }

      // System.out.println (" FIMa ");

      b = this.imprime_Gerencial_Veiculos (ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] geraAnalise_Gerencial_Prazo_Medio (GerencialED ed) throws Excecoes {
    byte[] b = null;

    Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

    DM_Prazo_Medio = "";

    try {

      DM_Prazo_Medio = "COBRANCA";
      if ("R1".equals (ed.getDM_Relatorio ()) ||
          "R2".equals (ed.getDM_Relatorio ())) {

        if ("R1".equals (ed.getDM_Relatorio ())) {
          atualiza_Prazo_Medio_Cobranca_Conhecimento (ed);
        }

        if ("R2".equals (ed.getDM_Relatorio ())) {
          atualiza_Prazo_Medio_Cobranca_Conhecimento_Internacional (ed);
        }

        if ("R3".equals (ed.getDM_Relatorio ())) {
          atualiza_Prazo_Medio_Cobranca_Conhecimento (ed);
          atualiza_Prazo_Medio_Cobranca_Conhecimento_Internacional (ed);
        }

      }

      if ("P1".equals (ed.getDM_Relatorio ()) ||
          "P2".equals (ed.getDM_Relatorio ()) ||
          "P3".equals (ed.getDM_Relatorio ())) {

        DM_Prazo_Medio = "PAGAMENTO";

        if ("P1".equals (ed.getDM_Relatorio ())) {

          atualiza_Prazo_Medio_Pagamento (ed);
        }

      }
      b = this.imprime_Gerencial_Prazo_Medio (ed);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método geraAnalise_Gerencial_Prazo_Medio_Cobranca");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public GerencialED atualiza_Prazo_Medio_Cobranca_Conhecimento (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    ResultSet res = null;

    try {

      // System.out.println (" BD 0 atualiza_Prazo_Medio_Cobranca_Conhecimento");

      NM_Campo = "Pessoa";

      tt_Prazo_Medio = 0;
      if ("PG".equals (ed.getDM_Tipo ())) {
        tt_Prazo_Medio = totaliza_Prazo_Medio (ed , "");
      }

      // System.out.println (" ed.getDM_Tipo()=" + ed.getDM_Tipo ());
      // System.out.println (" tt_Prazo_Medio=" + tt_Prazo_Medio);

      try {

        dia1 = 0;
        dia2 = 0;
        dia3 = 0;
        dia4 = 0;
        dia5 = 0;
        dia6 = 0;
        lidos = 0;
        OID_Pessoa = "";

        sql = " SELECT Pessoas.NM_Razao_Social, Conhecimentos.VL_Total_Frete, Conhecimentos.DT_Emissao as DT_Emissao_Cto, " +
            "        Duplicatas.oid_Pessoa , " +
            "        Duplicatas.DT_Emissao    as DT_Emissao_Dup, " +
            "        Movimentos_Duplicatas.Dt_movimento    as DT_Pgto_Dup, " +
            "        Duplicatas.DT_Vencimento as DT_Vencimento_Dup " +
            " From  Conhecimentos, Duplicatas, Origens_Duplicatas, Movimentos_Duplicatas  " +
            " WHERE Duplicatas.oid_Pessoa = Pessoas.oid_Pessoa " +
            " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento  " +
            " AND Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Movimentos_Duplicatas.DM_Principal ='S'  " +
            " AND Origens_Duplicatas.DM_Situacao <> 'E' " +
            " AND Conhecimentos.VL_Total_Frete  > 0 " +
            " AND Conhecimentos.DM_Impresso  = 'S' " +
            " AND Conhecimentos.DM_Situacao   <> 'C' " +
            " AND Movimentos_Duplicatas.Dt_movimento >= '" + ed.getDT_1 () + "'" +
            " AND Movimentos_Duplicatas.Dt_movimento <= '" + ed.getDT_2 () + "'";
      }
      catch (Exception ex) {
      }

      if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
        //  sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        //  sql += " and Unidades.OID_Unidade = " + ed.getOID_Unidade();
      }

      sql += "Order By  Duplicatas.oid_Pessoa ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {


        if (!OID_Pessoa.equals (res.getString ("oid_Pessoa")) && lidos > 0) {

          this.totaliza_prazo_medio (ed);
          dia1 = 0;
          dia2 = 0;
          dia3 = 0;
          dia4 = 0;
          dia5 = 0;
          dia6 = 0;
          lidos = 0;
        }

        //// System.out.println (" ---->> ");

        OID_Pessoa = res.getString ("oid_Pessoa");
        NM_Campo2 = res.getString ("nm_razao_social");
        lidos++;
        double VL_Total_Frete = 1;

        if (!"A".equals (ed.getDM_Tipo ())) {
          VL_Total_Frete = res.getDouble ("VL_Total_Frete");
        }

        dia1 += VL_Total_Frete * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Cto"))));
        dia2 += VL_Total_Frete * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Cto"))));
        dia3 += VL_Total_Frete * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Dup"))));

        dia4 += VL_Total_Frete * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Pgto_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Cto"))));
        dia5 += VL_Total_Frete * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Pgto_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Dup"))));
        dia6 += VL_Total_Frete * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Pgto_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento_Dup"))));
      }

      if (lidos > 0) {
        this.totaliza_prazo_medio (ed);
      }

      // System.out.println (" FIMa ");

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método atualiza_Prazo_Medio_Cobranca_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("atualiza_Prazo_Medio_Cobranca_Conhecimento(GerencialED ed)");
    }
    return ed;
  }

  public GerencialED atualiza_Prazo_Medio_Pagamento (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    ResultSet res = null;

    try {

      // System.out.println (" BD 0 atualiza_Prazo_Medio_Pagamento");
      NM_Campo = "Pessoa";

      tt_Prazo_Medio = 0;

      if ("PG".equals (ed.getDM_Tipo ())) {
        tt_Prazo_Medio = totaliza_Prazo_Medio (ed , "");
      }

      sql = " SELECT Compromissos.DT_Emissao , " +
          "        Compromissos.oid_Pessoa   , " +
          "        Compromissos.DT_Entrada   , " +
          "        Movimentos_Compromissos.dt_pagamento   , " +
          "        Movimentos_Compromissos.vl_pagamento   , " +
          "        Compromissos.DT_Vencimento  " +
          " From  Compromissos, Movimentos_Compromissos, Unidades " +
          " WHERE Movimentos_Compromissos.vl_pagamento >0  " +
          " AND Compromissos.oid_Unidade = Unidades.oid_Unidade " +
          " AND Compromissos.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso " +
          " AND Movimentos_Compromissos.dt_pagamento >= '" + ed.getDT_1 () + "'" +
          " AND Movimentos_Compromissos.dt_pagamento <= '" + ed.getDT_2 () + "'";

      if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
        sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Unidades.OID_Unidade = " + ed.getOID_Unidade ();
      }

      sql += " Order By  Compromissos.oid_Pessoa ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {

        this.inicioTransacao ();
        this.executasql = this.sql;

        // System.out.println (" oid_Pessoa " + OID_Pessoa + " rs->" + res.getString ("oid_Pessoa"));
        if (!OID_Pessoa.equals (res.getString ("oid_Pessoa")) && lidos > 0) {

          this.totaliza_prazo_medio (ed);
          dia1 = 0;
          dia2 = 0;
          dia3 = 0;
          dia4 = 0;
          dia5 = 0;
          dia6 = 0;
          lidos = 0;
        }

        // System.out.println (" ---->> ");

        OID_Pessoa = res.getString ("oid_Pessoa");
        lidos++;

        double vl_pagamento = 1;

        if (!"A".equals (ed.getDM_Tipo ())) {
          vl_pagamento = res.getDouble ("vl_pagamento");
        }

        dia1 += vl_pagamento * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Emissao")) , dataFormatada.getDT_FormataData (res.getString ("DT_Entrada"))));
        dia2 += vl_pagamento * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Entrada")) , dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento"))));
        dia3 += vl_pagamento * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Emissao")) , dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento"))));

        dia4 += vl_pagamento * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Emissao")) , dataFormatada.getDT_FormataData (res.getString ("dt_pagamento"))));
        dia5 += vl_pagamento * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Entrada")) , dataFormatada.getDT_FormataData (res.getString ("dt_pagamento"))));
        dia6 += vl_pagamento * (Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento")) , dataFormatada.getDT_FormataData (res.getString ("dt_pagamento"))));

        this.fimTransacao (true);
      }

      if (lidos > 0) {
        this.inicioTransacao ();
        this.executasql = this.sql;
        // System.out.println (" totaliza prazo");

        this.totaliza_prazo_medio (ed);

        this.fimTransacao (true);
      }
      // System.out.println (" FIMa ");

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método atualiza_Prazo_Medio_Pagamento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("atualiza_Prazo_Medio_Pagamento(GerencialED ed)");
    }
    return ed;
  }

  public GerencialED atualiza_Prazo_Medio_Cobranca_Conhecimento_Internacional (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    ResultSet res = null;

    try {

      // System.out.println (" BD 0 atualiza_Prazo_Medio_Cobranca_Conhecimento_Internacional");

      NM_Campo = "Pessoa";

      try {
        sql = " SELECT Conhecimentos_Internacionais.DT_Emissao as DT_Emissao_Cto, " +
            "        Duplicatas.oid_Pessoa , " +
            "        Duplicatas.DT_Emissao    as DT_Emissao_Dup, " +
            "        Duplicatas.DT_Vencimento as DT_Vencimento_Dup " +
            " From  Conhecimentos_Internacionais, Duplicatas, Origens_Duplicatas  " +
            " WHERE 1=1 " + // Duplicatas.oid_Unidade = Unidades.oid_Unidade " +
            " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento  " +
            " AND Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Origens_Duplicatas.DM_Situacao <> 'E' " +
            " AND Conhecimentos_Internacionais.DM_Situacao   <> 'C' " +
            " AND Duplicatas.DT_Emissao >= '" + ed.getDT_1 () + "'" +
            " AND Duplicatas.DT_Emissao <= '" + ed.getDT_2 () + "'";
      }
      catch (Exception ex) {
      }

      if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
        //  sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        //  sql += " and Unidades.OID_Unidade = " + ed.getOID_Unidade();
      }

      sql += " Order By  Duplicatas.oid_Pessoa ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {

        // System.out.println (" oid_Pessoa " + OID_Pessoa + " rs->" + res.getString ("oid_Pessoa"));
        if (!OID_Pessoa.equals (res.getString ("oid_Pessoa")) && lidos > 0) {

          this.totaliza_prazo_medio (ed);
          dia1 = 0;
          dia2 = 0;
          dia3 = 0;
          dia4 = 0;
          dia5 = 0;
          dia6 = 0;
          lidos = 0;
        }

        OID_Pessoa = res.getString ("oid_Pessoa");
        lidos++;

        dia1 += Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Cto")));
        dia2 += Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Cto")));
        dia3 += Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Dup")));

      }
      if (lidos > 0) {
        this.totaliza_prazo_medio (ed);
      }

      try {

        dia1 = 0;
        dia2 = 0;
        dia3 = 0;
        dia4 = 0;
        dia5 = 0;
        dia6 = 0;
        lidos = 0;
        OID_Pessoa = "";

        sql = " SELECT Conhecimentos_Internacionais.DT_Emissao as DT_Emissao_Cto, " +
            "        Duplicatas.oid_Pessoa , " +
            "        Duplicatas.DT_Emissao    as DT_Emissao_Dup, " +
            "        Movimentos_Duplicatas.Dt_movimento    as DT_Pgto_Dup, " +
            "        Duplicatas.DT_Vencimento as DT_Vencimento_Dup " +
            " From  Conhecimentos_Internacionais, Duplicatas, Origens_Duplicatas, Movimentos_Duplicatas  " +
            " WHERE 1=1 " + // Duplicatas.oid_Unidade = Unidades.oid_Unidade " +
            " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento  " +
            " AND Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Movimentos_Duplicatas.DM_Principal ='S'  " +
            " AND Origens_Duplicatas.DM_Situacao <> 'E' " +
            " AND Conhecimentos_Internacionais.DM_Situacao   <> 'C' " +
            " AND Movimentos_Duplicatas.Dt_movimento >= '" + ed.getDT_1 () + "'" +
            " AND Movimentos_Duplicatas.Dt_movimento <= '" + ed.getDT_2 () + "'";
      }
      catch (Exception ex) {
      }

      if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
        //  sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        //  sql += " and Unidades.OID_Unidade = " + ed.getOID_Unidade();
      }

      sql += " Order By  Duplicatas.oid_Pessoa ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {

        // System.out.println (" oid_Pessoa " + OID_Pessoa + " rs->" + res.getString ("oid_Pessoa"));
        if (!OID_Pessoa.equals (res.getString ("oid_Pessoa")) && lidos > 0) {

          this.totaliza_prazo_medio (ed);
          dia1 = 0;
          dia2 = 0;
          dia3 = 0;
          dia4 = 0;
          dia5 = 0;
          dia6 = 0;
          lidos = 0;
        }

        // System.out.println (" ---->> ");

        OID_Pessoa = res.getString ("oid_Pessoa");
        lidos++;

        dia4 += Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Pgto_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Dup")));
        dia5 += Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Pgto_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_Cto")));
        dia6 += Data.diferencaDias (dataFormatada.getDT_FormataData (res.getString ("DT_Pgto_Dup")) , dataFormatada.getDT_FormataData (res.getString ("DT_Vencimento_Dup")));

      }

      if (lidos > 0) {
        this.totaliza_prazo_medio (ed);
      }

      // System.out.println (" FIMa ");

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método atualiza_Prazo_Medio_Cobranca_Conhecimento_Internacional");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("atualiza_Prazo_Medio_Cobranca_Conhecimento_Internacional(GerencialED ed)");
    }
    return ed;
  }

  public GerencialED totaliza_financeiro (GerencialED ed) throws Excecoes {

    OID_Gerencial = 0;
    GerencialED edGerencial = new GerencialED ();

    try {

      edGerencial.setNR_Sort (Nr_Sort);
      edGerencial.setNM_Campo (NM_Campo);
      edGerencial.setNM_Campo2 (NM_Campo2);
      edGerencial.setOID_Carteira (OID_Carteira);
      edGerencial.setOID_Conta (OID_Conta);
      edGerencial.setOID_Conta_Corrente (OID_Conta_Corrente);

      edGerencial = getByRecord (edGerencial);

      OID_Gerencial = edGerencial.getOID_Gerencial ();

      v1 = edGerencial.getVL_1 ();
      v2 = edGerencial.getVL_2 ();
      v3 = edGerencial.getVL_3 ();
      v4 = edGerencial.getVL_4 ();
      v5 = edGerencial.getVL_5 ();
      vencido = edGerencial.getVL_Vencido ();
      vencer = edGerencial.getVL_Vencer ();

      // System.out.println (" leu valor_mes1 " + valor_mes1 + " data " + data + " ed.getDT_1() " + ed.getDT_1 () + " v1 " + v1 + " v2 " + v2 + " v3 " + v3 + " v4 " + v4 + " v5 " + v5 + " vencer " + vencer);

      Date data1 = formatter.parse (data);
      Calendar caldata = Calendar.getInstance ();
      caldata.setTime (data1);

      Date data2 = formatter.parse (ed.getDT_5 ());
      caldata.setTime (data2);

      if (data.equals (ed.getDT_1 ())) {
        v1 = v1 + valor_mes1;
      }
      else if (data.equals (ed.getDT_2 ())) {
        v2 = v2 + valor_mes1;
      }
      else if (data.equals (ed.getDT_3 ())) {
        v3 = v3 + valor_mes1;
      }
      else if (data.equals (ed.getDT_4 ())) {
        v4 = v4 + valor_mes1;
      }
      else if (data.equals (ed.getDT_5 ())) {
        v5 = v5 + valor_mes1;
      }
      else {
        if (data1.after (data2)) {
          vencer = vencer + valor_mes1;
        }
        else {
          vencido = vencido + valor_mes1;
        }
      }

      if (edGerencial.getOID_Gerencial () <= 0) {

        inclui (edGerencial = carregaED (edGerencial));
      }
      else {

        // System.out.println (" altera valor_mes1 " + valor_mes1 + " data " + data + " ed.getDT_1() " + ed.getDT_1 () + " v1 " + v1 + " v2 " + v2 + " v3 " + v3 + " v4 " + v4 + " v5 " + v5 + " vencer " + vencer);

        altera (edGerencial = carregaED (edGerencial));
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de Gerencial de dados");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_receita_despesa (GerencialED ed) throws Excecoes {

    OID_Gerencial = 0;
    OID_Carteira = 0;
    OID_Conta_Corrente = 0;
    GerencialED edGerencial = new GerencialED ();

    // System.out.println ("totaliza " + NM_Campo);

    try {

      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      edGerencial.setNR_Sort (Nr_Sort);
      edGerencial.setNM_Campo (NM_Campo);
      edGerencial.setNM_Campo2 (NM_Campo2);
      edGerencial.setOID_Conta (OID_Conta);
      edGerencial.setVL_1 (valor_mes1);
      edGerencial.setVL_2 (valor_mes2);
      edGerencial.setVL_3 (valor_mes3);

      edGerencial = getByRecord (edGerencial);

      OID_Gerencial = edGerencial.getOID_Gerencial ();

      v1 = valor_mes1;
      v2 = valor_mes2;
      v3 = valor_mes3;

      if (edGerencial.getOID_Gerencial () <= 0) {
        inclui (edGerencial = carregaED (edGerencial));
      }
      else {
        altera (edGerencial = carregaED (edGerencial));
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de Gerencial de dados");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_veiculo (GerencialED ed) throws Excecoes {

    OID_Gerencial = 0;
    GerencialED edGerencial = new GerencialED ();

    // System.out.println ("totaliza " + NM_Campo);

    try {

      edGerencial.setNR_Sort (Nr_Sort);
      edGerencial.setNM_Campo (NM_Campo);
      edGerencial.setOID_Veiculo (OID_Veiculo);
      edGerencial.setVL_1 (vl_total_frete);
      edGerencial.setVL_2 (nr_peso);
      edGerencial.setVL_3 (nr_despachos);
      edGerencial.setVL_4 (vl_ordem_frete_terceiro);
      edGerencial.setVL_5 (vl_ordem_frete);
      edGerencial.setVL_6 (vl_frete_liquido);

      edGerencial = getByRecord (edGerencial);

      OID_Gerencial = edGerencial.getOID_Gerencial ();

      v1 = vl_total_frete;
      v2 = nr_peso;
      v3 = nr_despachos;
      v4 = vl_ordem_frete_terceiro;
      v5 = vl_ordem_frete;
      v6 = vl_frete_liquido;

      if (edGerencial.getOID_Gerencial () <= 0) {
        inclui (edGerencial = carregaED (edGerencial));
      }
      else {
        altera (edGerencial = carregaED (edGerencial));
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de Gerencial de dados");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_cliente (GerencialED ed) throws Excecoes {

    // System.out.println ("totaliza " + NM_Campo);

    try {
      String sqlFiltro = "  ";

      String sql = " SELECT " +
          " COUNT (Conhecimentos.oid_conhecimento) as nr_despacho, " +
          " SUM (Conhecimentos.nr_volumes) as nr_volumes, " +
          " SUM (Conhecimentos.VL_NOTA_FISCAL) as vl_nota_fiscal, " +
          " SUM (Conhecimentos.vl_total_frete) as vl_total_frete, " +
          " SUM (Conhecimentos.vl_total_custo) as vl_total_custo, " +
          " SUM (Conhecimentos.nr_peso) as  nr_peso," +
          " SUM (Conhecimentos.nr_peso_cubado) as  nr_peso_cubado" +
          " FROM  Conhecimentos, Clientes, Unidades, Modal, Cidades Cidade_Origem, Cidades Cidade_Destino, Regioes_Estados Regiao_Estado_Origem,  Regioes_Estados Regiao_Estado_Destino, Estados Estado_Origem, Estados Estado_Destino " +
          " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
          " AND   Unidades.DM_Situacao            <> 'I' " +
          " AND Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
          " AND Conhecimentos.oid_Pessoa_Pagador  = Clientes.oid_Cliente " +
          " AND Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          " AND Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
          " AND Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          " AND Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
          " AND Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado " +
          " AND Conhecimentos.oid_modal           = Modal.oid_modal ";

      sqlFiltro = " AND Conhecimentos.DM_Impresso = 'S' " +
          " AND Conhecimentos.DM_Situacao <> 'C' " +
          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
          " AND Conhecimentos.VL_Total_Frete > 0";

      if (ed.getOID_Empresa () > 0) {
        sqlFiltro += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sqlFiltro += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }

      if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
        sqlFiltro += " and Conhecimentos.OID_Modal = " + ed.getOID_Modal ();
      }

      if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
        sqlFiltro += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
      }

      if (String.valueOf (ed.getOID_Grupo_Economico ()) != null && !String.valueOf (ed.getOID_Grupo_Economico ()).equals ("0")) {
        sqlFiltro += " and Clientes.OID_Grupo_Economico = " + ed.getOID_Grupo_Economico ();
      }

      if (String.valueOf (ed.getOID_Cidade_Origem ()) != null && !String.valueOf (ed.getOID_Cidade_Origem ()).equals ("0")) {
        sqlFiltro += " and Conhecimentos.OID_Cidade = " + ed.getOID_Cidade_Origem ();
      }

      if (String.valueOf (ed.getOID_Cidade_Destino ()) != null && !String.valueOf (ed.getOID_Cidade_Destino ()).equals ("0")) {
        sqlFiltro += " and Conhecimentos.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sqlFiltro += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getOID_Vendedor ()) != null &&
          !String.valueOf (ed.getOID_Vendedor ()).equals ("") &&
          !String.valueOf (ed.getOID_Vendedor ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Vendedor = '" + ed.getOID_Vendedor () + "'";
      }

      sqlFiltro += " and Conhecimentos.DT_Emissao >= '" + data1 + "'";
      sqlFiltro += " and Conhecimentos.DT_Emissao <= '" + data2 + "'";


      sql = sql + sqlFiltro;
      //System.out.println (" BD 0 sql=>> " + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        // System.out.println (" BD OK ->>" + ed.getOID_Pessoa ());
        if (res.getDouble ("vl_total_frete") > 0) {
          GerencialED edGerencial = new GerencialED ();
          edGerencial.setOID_Pessoa (ed.getOID_Pessoa ());
          edGerencial.setOID_Modal (ed.getOID_Modal ());
          edGerencial.setNR_Sort (Nr_Sort);
          String periodo=data1.substring(6)+data1.substring(2, 5);

          edGerencial.setNM_Campo (periodo);
          edGerencial.setNM_Campo2 (String.valueOf (100000 + ordem).substring (3));
          edGerencial.setVL_Total_Frete (res.getDouble ("vl_total_frete"));
          edGerencial.setVL_Notas_Fiscais (res.getDouble ("vl_nota_fiscal"));
          edGerencial.setNR_Despachos (res.getDouble ("nr_despacho"));
          edGerencial.setNR_Peso (res.getDouble ("nr_peso"));
          edGerencial.setNR_Peso_Cubado (res.getDouble ("nr_peso_cubado"));
          edGerencial.setNR_Volumes (res.getDouble ("nr_volumes"));
          edGerencial.setVL_Margem (res.getDouble ("vl_total_frete") - res.getDouble ("vl_total_custo"));

          
          
          long qt_atrazado = total_Entrega_Cliente ("D" , sqlFiltro);
          long qt_no_prazo = total_Entrega_Cliente ("P" , sqlFiltro);
          long qt_antecipado = total_Entrega_Cliente ("A" , sqlFiltro);
          long qt_justificado = total_Entrega_Cliente ("J" , sqlFiltro);

          edGerencial.setNR_Entrega_Atrazada (qt_atrazado);
          edGerencial.setNR_Entrega_Prazo (qt_no_prazo);
          edGerencial.setNR_Entrega_Antecipada (qt_antecipado);
          edGerencial.setNR_Entrega_Justificada (qt_justificado);

          tvl_total_frete += res.getDouble ("vl_total_frete");
          tvl_margem += res.getDouble ("vl_total_frete") - res.getDouble ("vl_total_custo");
          tvl_notas_fiscais += res.getDouble ("vl_nota_fiscal");
          tnr_despachos += res.getDouble ("nr_despacho");
          tnr_peso += res.getDouble ("nr_peso");
          tnr_peso_cubado += res.getDouble ("nr_peso_cubado");
          tnr_volumes += res.getDouble ("nr_volumes");
          // System.out.println (" BD OK 99");

          // System.out.println (" BD OK qt_atrazado->> " + qt_atrazado);
          // System.out.println (" BD OK qt_no_prazo->> " + qt_no_prazo);
          // System.out.println (" BD OK qt_antecipado->> " + qt_antecipado);
          // System.out.println (" BD OK qt_justificado->> " + qt_justificado);

          inclui (edGerencial);

          // System.out.println (" BD OK ->> volta");
        }
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de totaliza_cliente");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_modal (GerencialED ed) throws Excecoes {

	    // System.out.println ("totaliza_modal " + NM_Campo);

	    try {
	      String sqlFiltro = "  ";

	      String sql = " SELECT Modal.DM_Tipo_Transporte ," +
	          " COUNT (Conhecimentos.oid_conhecimento) as nr_despacho, " +
	          " SUM (Conhecimentos.nr_volumes) as nr_volumes, " +
	          " SUM (Conhecimentos.VL_NOTA_FISCAL) as vl_nota_fiscal, " +
	          " SUM (Conhecimentos.vl_total_frete) as vl_total_frete, " +
	          " SUM (Conhecimentos.vl_total_custo) as vl_total_custo, " +
	          " SUM (Conhecimentos.nr_peso) as  nr_peso," +
	          " SUM (Conhecimentos.nr_peso_cubado) as  nr_peso_cubado" +
	          " FROM  Conhecimentos, Clientes, Unidades, Modal " +
	          " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
	          " AND   Unidades.DM_Situacao            <> 'I' " +
	          " AND   Conhecimentos.oid_Pessoa_Pagador  = Clientes.oid_Cliente " +
	          " AND   Conhecimentos.oid_modal           = Modal.oid_modal ";

	      sqlFiltro = " AND Conhecimentos.DM_Impresso = 'S' " +
	          " AND Conhecimentos.DM_Situacao <> 'C' " +
	          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
	          " AND Conhecimentos.VL_Total_Frete > 0";

	      if (ed.getOID_Empresa () > 0) {
	        sqlFiltro += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
	      }

	      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
	      }

	      if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Modal = " + ed.getOID_Modal ();
	      }

	      if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
	      }

	      if (String.valueOf (ed.getOID_Grupo_Economico ()) != null && !String.valueOf (ed.getOID_Grupo_Economico ()).equals ("0")) {
	        sqlFiltro += " and Clientes.OID_Grupo_Economico = " + ed.getOID_Grupo_Economico ();
	      }

	      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
	          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
	          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
	        sqlFiltro += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa () + "'";
	      }

	      if (String.valueOf (ed.getOID_Vendedor ()) != null &&
	          !String.valueOf (ed.getOID_Vendedor ()).equals ("") &&
	          !String.valueOf (ed.getOID_Vendedor ()).equals ("null")) {
	        sql += " and Clientes.OID_Vendedor = '" + ed.getOID_Vendedor () + "'";
	      }

	      sqlFiltro += " and Conhecimentos.DT_Emissao >= '" + data1 + "'";
	      sqlFiltro += " and Conhecimentos.DT_Emissao <= '" + data2 + "'";
	      sqlFiltro += " GROUP BY DM_Tipo_Transporte";


	      sql = sql + sqlFiltro;

	      // System.out.println (" BD 0 sql " + sql);
	      
	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      double tt_frete=0, tt_rodo=0, tt_aereo=0, tt_servico=0, tt_outros=0;
	      while (res.next ()) {
	    	  tt_frete+=res.getDouble ("vl_total_frete");
	    	  if ("R".equals(res.getString("DM_Tipo_Transporte"))) {
	    		  tt_rodo+=res.getDouble ("vl_total_frete");
	          }else {
		          if ("A".equals(res.getString("DM_Tipo_Transporte"))) {
		        	  tt_aereo+=res.getDouble ("vl_total_frete");
		          }else {
			          if ("S".equals(res.getString("DM_Tipo_Transporte"))) {
			        	  tt_servico+=res.getDouble ("vl_total_frete");
			          }else {
			        	  tt_outros+=res.getDouble ("vl_total_frete");
			          }
		          }
	          }
	    	  
	      }	  
	    	  
	      // System.out.println (" BD OK ->>" + ed.getOID_Pessoa ());
	      if (tt_frete > 0) {
	          GerencialED edGerencial = new GerencialED ();
	          edGerencial.setOID_Pessoa (ed.getOID_Pessoa ());
	          edGerencial.setOID_Modal (ed.getOID_Modal ());
	          edGerencial.setNR_Sort (Nr_Sort);
	          String periodo=data1.substring(6)+data1.substring(2, 5);
	          
	          edGerencial.setNM_Campo (periodo);
	          edGerencial.setNM_Campo2 (String.valueOf (100000 + ordem).substring (3));
	          edGerencial.setVL_Total_Frete (tt_frete);
	          edGerencial.setVL_1 (tt_rodo);
	          edGerencial.setVL_2 (tt_aereo);
	          edGerencial.setVL_3 (tt_servico);
	          edGerencial.setVL_4 (tt_outros);
	          
	          tvl_total_frete += tt_frete;

	          inclui (edGerencial);

	          // System.out.println (" BD OK ->> volta");
	      }
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setMensagem ("Erro de totaliza_modal");
	      excecoes.setExc (exc);
	    }
	    return ed;

  }
  
  public GerencialED totaliza_tabela_frete (GerencialED ed) throws Excecoes {

	    // System.out.println ("totaliza_modal " + NM_Campo);

	    try {
	      String sqlFiltro = "  ";
	      String sql ="";
	      int qtt_despacho=0, qtt_rodo=0, qtt_aereo=0, qtt_servico=0, qtt_outros=0;
	      int qtab_despacho=0, qtab_rodo=0, qtab_aereo=0, qtab_servico=0, qtab_outros=0;
	      
	      sqlFiltro = " AND Conhecimentos.DM_Impresso = 'S' " +
          " AND Conhecimentos.DM_Situacao <> 'C' " +
          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
          " AND Conhecimentos.VL_Total_Frete > 0";

	      if (ed.getOID_Empresa () > 0) {
	        sqlFiltro += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
	      }
	
	      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
	      }
	
	      if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Modal = " + ed.getOID_Modal ();
	      }
	
	      if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
	      }
	
	      if (String.valueOf (ed.getOID_Grupo_Economico ()) != null && !String.valueOf (ed.getOID_Grupo_Economico ()).equals ("0")) {
	        sqlFiltro += " and Clientes.OID_Grupo_Economico = " + ed.getOID_Grupo_Economico ();
	      }
	
	      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
	          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
	          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
	        sqlFiltro += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa () + "'";
	      }
	
	      if (String.valueOf (ed.getOID_Vendedor ()) != null &&
	          !String.valueOf (ed.getOID_Vendedor ()).equals ("") &&
	          !String.valueOf (ed.getOID_Vendedor ()).equals ("null")) {
	    	  sqlFiltro += " and Clientes.OID_Vendedor = '" + ed.getOID_Vendedor () + "'";
	      }

	      sqlFiltro += " and Conhecimentos.DT_Emissao >= '" + data1 + "'";
	      sqlFiltro += " and Conhecimentos.DT_Emissao <= '" + data2 + "'";
	      
	      sql = " SELECT Conhecimentos.oid_Pessoa_Pagador " + 
	      		" FROM  Conhecimentos, Clientes, Unidades  " +
	            " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
	            " AND   Unidades.DM_Situacao            <> 'I' " +
	            " AND   Conhecimentos.oid_Pessoa_Pagador  = Clientes.oid_Cliente ";
	      		sql+=sqlFiltro + " GROUP BY oid_Pessoa_Pagador ";

	  	       //System.out.println (" BD 0 sql " + sql);
	 	  ResultSet resCli = this.executasql.executarConsulta (sql);
		  while (resCli.next ()) {

	    	  qtt_despacho=0;
	    	  qtt_rodo=0;
	    	  qtt_aereo=0;
	    	  qtt_servico=0;
	    	  qtt_outros=0;
	    	  qtab_despacho=0;
	    	  qtab_rodo=0;
	    	  qtab_aereo=0;
	    	  qtab_servico=0;
	    	  qtab_outros=0;
			  
			  //SELECT TODOS CTRC
			  sql = " SELECT Modal.DM_Tipo_Transporte ," +
	          " COUNT (Conhecimentos.oid_conhecimento) as nr_despacho " +
	          " FROM  Conhecimentos, Clientes, Unidades, Modal  " +
	          " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
	          " AND   Conhecimentos.oid_modal           = Modal.oid_modal " +
	          " AND   Unidades.DM_Situacao            <> 'I' " +
	          " AND   Conhecimentos.oid_Pessoa_Pagador  = Clientes.oid_Cliente " +
	          " AND   Clientes.oid_Cliente  = '"+ resCli.getString("oid_Pessoa_Pagador")+ "'";
			  sql+=sqlFiltro + " GROUP BY DM_Tipo_Transporte ";
		      //System.out.println (" BD 0 sql " + sql);
			  
		      ResultSet res = this.executasql.executarConsulta (sql);
		      while (res.next ()) {
		  	       //System.out.println ("Cliente:" + resCli.getString("oid_Pessoa_Pagador")+ " Mod: " +res.getString("DM_Tipo_Transporte")+"=>> "+res.getInt ("nr_despacho"));
		    	  
		    	  tnr_despachos+=res.getInt ("nr_despacho");

		    	  qtt_despacho+=res.getInt ("nr_despacho");
		    	  
		    	  if ("R".equals(res.getString("DM_Tipo_Transporte"))) {
		    		  qtt_rodo=res.getInt ("nr_despacho");
		          }else {
			          if ("A".equals(res.getString("DM_Tipo_Transporte"))) {
			        	  qtt_aereo=res.getInt ("nr_despacho");
			          }else {
				          if ("S".equals(res.getString("DM_Tipo_Transporte"))) {
				        	  qtt_servico=res.getInt ("nr_despacho");
				          }else {
				        	  qtt_outros=res.getInt ("nr_despacho");
				          }
			          }
		          }
		      }	  

		      //SELECT CTRC CALCULADOS COM TABELA
			  sql = " SELECT Modal.DM_Tipo_Transporte ," +
	          " COUNT (Conhecimentos.oid_conhecimento) as nr_despacho " +
	          " FROM  Conhecimentos, Clientes, Unidades, Modal  " +
	          " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
	          " AND   Conhecimentos.oid_modal           = Modal.oid_modal " +
	          " AND   Unidades.DM_Situacao            <> 'I' " +
	          " AND   Conhecimentos.DM_Tipo_Calculo   =  'T' " +
	          " AND   Conhecimentos.oid_Pessoa_Pagador  = Clientes.oid_Cliente " +
	          " AND   Clientes.oid_Cliente  = '"+ resCli.getString("oid_Pessoa_Pagador")+ "'";
			  sql+=sqlFiltro + " GROUP BY DM_Tipo_Transporte ";
		      //System.out.println (" BD 0 sql " + sql);
			  
		      res = this.executasql.executarConsulta (sql);
		      while (res.next ()) {
		  	       //System.out.println ("Cliente:" + resCli.getString("oid_Pessoa_Pagador")+ " Mod: " +res.getString("DM_Tipo_Transporte")+"=>> "+res.getInt ("nr_despacho"));
		    	  
		    	  qtab_despacho+=res.getInt ("nr_despacho");
		    	  
		    	  if ("R".equals(res.getString("DM_Tipo_Transporte"))) {
		    		  qtab_rodo=res.getInt ("nr_despacho");
		          }else {
			          if ("A".equals(res.getString("DM_Tipo_Transporte"))) {
			        	  qtab_aereo=res.getInt ("nr_despacho");
			          }else {
				          if ("S".equals(res.getString("DM_Tipo_Transporte"))) {
				        	  qtab_servico=res.getInt ("nr_despacho");
				          }else {
				        	  qtab_outros=res.getInt ("nr_despacho");
				          }
			          }
		          }
		      }	  
		      
		      if (qtt_despacho > 0) {
		          GerencialED edGerencial = new GerencialED ();
			      edGerencial.setNM_Campo ("------");

			      //SELECT ULTIMO CTRC
				  sql = " SELECT max (DT_Emissao) as dt_cto " +
		          " FROM  Conhecimentos, Clientes, Unidades, Modal  " +
		          " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
		          " AND   Conhecimentos.oid_modal           = Modal.oid_modal " +
		          " AND   Unidades.DM_Situacao            <> 'I' " +
		          " AND   Conhecimentos.oid_Pessoa_Pagador  = Clientes.oid_Cliente " +
		          " AND   Clientes.oid_Cliente  = '"+ resCli.getString("oid_Pessoa_Pagador")+ "'";
				  
				  //sql+=sqlFiltro;
			      //System.out.println (" BD 0 sql " + sql);
				  
			      res = this.executasql.executarConsulta (sql);
			      if (res.next ()) {
				      System.out.println (res.getString("dt_cto"));
				      edGerencial.setNM_Campo (FormataData.formataDataBT (res.getString ("dt_cto")));
			      }
		          
		          edGerencial.setOID_Pessoa (resCli.getString("oid_Pessoa_Pagador"));
		          edGerencial.setNR_Sort (Nr_Sort);
		          
		          String periodo=data1.substring(6)+data1.substring(2, 5);
		          
		          edGerencial.setNM_Campo2 (periodo);
		          edGerencial.setVL_1 (qtt_rodo);
		          edGerencial.setVL_2 (qtt_aereo);
		          edGerencial.setVL_3 (qtt_servico);
		          edGerencial.setVL_4 (qtt_outros);

		          edGerencial.setVL_11 (qtab_rodo);
		          edGerencial.setVL_12 (qtab_aereo);
		          edGerencial.setVL_13 (qtab_servico);
		          edGerencial.setVL_14 (qtab_outros);
		          
		          inclui (edGerencial);

		          // System.out.println (" BD OK ->> volta");
		      }
		    	  
		      // System.out.println (" BD OK ->>" + ed.getOID_Pessoa ());
			  
		  }
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setMensagem ("Erro de totaliza_tabela_frete");
	      excecoes.setExc (exc);
	    }
	    return ed;

	  }
  
  public GerencialED totaliza_fornecedor (GerencialED ed) throws Excecoes {

	    // System.out.println ("totaliza_fornecedor " + NM_Campo);

	    try {
	      String sqlFiltro = "  ";

	      String sql = " SELECT " +
	          " COUNT (Conhecimentos.oid_conhecimento) as nr_despacho, " +
	          " SUM (Conhecimentos.nr_volumes) as nr_volumes, " +
	          " SUM (Movimentos_Conhecimentos.VL_Movimento) as VL_Movimento, " +
	          " SUM (Conhecimentos.VL_NOTA_FISCAL) as vl_nota_fiscal, " +
	          " SUM (Conhecimentos.vl_total_frete) as vl_total_frete, " +
	          " SUM (Conhecimentos.vl_total_custo) as vl_total_custo, " +
	          " SUM (Conhecimentos.nr_peso) as  nr_peso," +
	          " SUM (Conhecimentos.nr_peso_cubado) as  nr_peso_cubado" +
	          " FROM  Conhecimentos, Movimentos_Conhecimentos, Unidades, Tipos_Movimentos " +
	          //"      , Modal, Cidades Cidade_Origem, Cidades Cidade_Destino, Regioes_Estados Regiao_Estado_Origem,  Regioes_Estados Regiao_Estado_Destino, Estados Estado_Origem, Estados Estado_Destino " +
	          " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
	          " AND Unidades.DM_Situacao            <> 'I' " +
	          " AND Conhecimentos.oid_Conhecimento  = Movimentos_Conhecimentos.oid_Conhecimento " +
	          " AND Tipos_Movimentos.oid_Tipo_Movimento  = Movimentos_Conhecimentos.oid_Tipo_Movimento ";
 	          
	          //" AND Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
	          //" AND Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
	          //" AND Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
	          //" AND Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
	          //" AND Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
	          //" AND Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado " +
	          //" AND Conhecimentos.oid_modal           = Modal.oid_modal ";

	      sqlFiltro = " AND Conhecimentos.DM_Impresso = 'S' " +
	          " AND Tipos_Movimentos.DM_Debito_Credito = 'D' " +
	          " AND Tipos_Movimentos.DM_Tipo_Movimento = 'D' " +
	          " AND Conhecimentos.DM_Situacao <> 'C' " +
	          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
	          " AND Conhecimentos.VL_Total_Frete > 0";

	      if (ed.getOID_Empresa () > 0) {
	        sqlFiltro += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
	      }

	      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
	      }

	      if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Modal = " + ed.getOID_Modal ();
	      }

	      if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
	      }

	      if (String.valueOf (ed.getOID_Cidade_Origem ()) != null && !String.valueOf (ed.getOID_Cidade_Origem ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Cidade = " + ed.getOID_Cidade_Origem ();
	      }

	      if (String.valueOf (ed.getOID_Cidade_Destino ()) != null && !String.valueOf (ed.getOID_Cidade_Destino ()).equals ("0")) {
	        sqlFiltro += " and Conhecimentos.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino ();
	      }


	      if (String.valueOf (ed.getOID_Vendedor ()) != null &&
	          !String.valueOf (ed.getOID_Vendedor ()).equals ("") &&
	          !String.valueOf (ed.getOID_Vendedor ()).equals ("null")) {
	        sql += " and Conhecimentos.OID_Vendedor = '" + ed.getOID_Vendedor () + "'";
	      }

	      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
		          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
		          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
		        sqlFiltro += " and Movimentos_Conhecimentos.OID_Fornecedor = '" + ed.getOID_Pessoa () + "'";
		  }else {
		        sqlFiltro += " and (Movimentos_Conhecimentos.OID_Fornecedor is null  OR Movimentos_Conhecimentos.OID_Fornecedor ='' OR Movimentos_Conhecimentos.OID_Fornecedor ='null' )";
		  }
	      
	      
	      sqlFiltro += " and Conhecimentos.DT_Emissao >= '" + data1 + "'";
	      sqlFiltro += " and Conhecimentos.DT_Emissao <= '" + data2 + "'";


	      sql = sql + sqlFiltro;

	      // System.out.println (" BD 0 sql " + sql);

	      ResultSet res = null;
	      res = executasql.executarConsulta (sql);

	      while (res.next ()) {
	    	  
	        // System.out.println (" BD OK ->>" + ed.getOID_Pessoa ());
	        if (res.getDouble ("vl_total_frete") > 0) {
	          GerencialED edGerencial = new GerencialED ();
	          edGerencial.setOID_Pessoa (ed.getOID_Pessoa ());
	          edGerencial.setOID_Pessoa_Fornecedor (ed.getOID_Pessoa_Fornecedor ());
	          edGerencial.setOID_Modal (ed.getOID_Modal ());
	          edGerencial.setNR_Sort (Nr_Sort);
	          String periodo=data1.substring(6)+data1.substring(2, 5);
	          
	          edGerencial.setNM_Campo (periodo+"-"+" *DIVERSOS*");

	          if (!"".equals(ed.getOID_Pessoa())) {
			      ResultSet resCli = executasql.executarConsulta (" SELECT NM_Razao_Social FROM Pessoas WHERE Pessoas.oid_Pessoa='" + ed.getOID_Pessoa () +"'");
			      if (resCli.next ()) {
			          edGerencial.setNM_Campo (periodo+"-"+(resCli.getString("NM_Razao_Social")+"                      ").substring(0,15));
			      }
	          }

	          
	          edGerencial.setNM_Campo2 (String.valueOf (100000 + ordem).substring (3));
	          edGerencial.setVL_Total_Frete (res.getDouble ("vl_total_frete"));
	          edGerencial.setVL_Notas_Fiscais(res.getDouble ("vl_nota_fiscal"));
	          edGerencial.setVL_1 (res.getDouble ("VL_Movimento"));
	          edGerencial.setNR_Despachos (res.getDouble ("nr_despacho"));
	          edGerencial.setNR_Peso (res.getDouble ("nr_peso"));
	          edGerencial.setNR_Peso_Cubado (res.getDouble ("nr_peso_cubado"));
	          edGerencial.setNR_Volumes (res.getDouble ("nr_volumes"));
	          edGerencial.setVL_Margem (res.getDouble ("vl_total_frete") - res.getDouble ("vl_total_custo"));

	          /*
	          long qt_atrazado = total_Entrega_Cliente ("D" , sqlFiltro);
	          long qt_no_prazo = total_Entrega_Cliente ("P" , sqlFiltro);
	          long qt_antecipado = total_Entrega_Cliente ("A" , sqlFiltro);
	          long qt_justificado = total_Entrega_Cliente ("J" , sqlFiltro);

	          edGerencial.setNR_Entrega_Atrazada (qt_atrazado);
	          edGerencial.setNR_Entrega_Prazo (qt_no_prazo);
	          edGerencial.setNR_Entrega_Antecipada (qt_antecipado);
	          edGerencial.setNR_Entrega_Justificada (qt_justificado);
	          */

	          tvl_total_frete += res.getDouble ("vl_total_frete");
	          tvl_margem += res.getDouble ("vl_total_frete") - res.getDouble ("vl_total_custo");
	          tvl_notas_fiscais += res.getDouble ("vl_nota_fiscal");
	          tnr_despachos += res.getDouble ("nr_despacho");
	          tnr_peso += res.getDouble ("nr_peso");
	          tnr_peso_cubado += res.getDouble ("nr_peso_cubado");
	          tnr_volumes += res.getDouble ("nr_volumes");
	          // System.out.println (" BD OK 99");


	          inclui (edGerencial);

	          // System.out.println (" BD OK ->> volta");
	        }
	      }
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setMensagem ("Erro de totaliza_cliente");
	      excecoes.setExc (exc);
	    }
	    return ed;

	  }
  
  private long total_Entrega_Cliente (String DM_Tipo_Entrega , String sqlFiltro) throws Excecoes {

    // System.out.println ("totaliza " + NM_Campo);

    String sql = "";
    
    //qt_atrazado = D
    //qt_no_prazo = P
    //qt_antecipado = A
    //qt_justificado = J
    //nr_dias_entrega_realizado

    sql = " SELECT COUNT (oid_Conhecimento) as qt_entrega " +
    " FROM  Conhecimentos, Clientes, Unidades, Modal, Cidades Cidade_Origem, Cidades Cidade_Destino, Regioes_Estados Regiao_Estado_Origem,  Regioes_Estados Regiao_Estado_Destino, Estados Estado_Origem, Estados Estado_Destino " +
    " WHERE Unidades.oid_Unidade            = Conhecimentos.oid_Unidade " +
    " AND   Unidades.DM_Situacao            <> 'I' " +
    " AND Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
    " AND Conhecimentos.oid_Pessoa_Pagador  = Clientes.oid_Cliente " +
    " AND Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
    " AND Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
    " AND Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
    " AND Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
    " AND Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado " +
    " AND Conhecimentos.oid_modal           = Modal.oid_modal ";
    if ("D".equals(DM_Tipo_Entrega)) {
    	sql +=" AND Conhecimentos.DT_Entrega is not null AND Conhecimentos.nr_dias_entrega_realizado>0 ";
    }
    if ("P".equals(DM_Tipo_Entrega)) {
    	sql +=" AND Conhecimentos.DT_Entrega is not null AND Conhecimentos.nr_dias_entrega_realizado=0 ";
    }
    if ("A".equals(DM_Tipo_Entrega)) {
    	sql +=" AND Conhecimentos.DT_Entrega is not null AND Conhecimentos.nr_dias_entrega_realizado<0 ";
    }
    if ("J".equals(DM_Tipo_Entrega)) {
    	sql +=" AND Conhecimentos.DT_Entrega is not null AND Conhecimentos.DM_Tipo_Entrega='J' ";
    }
    
    sql = sql + sqlFiltro;

    // System.out.println (" BD sql ->> " + sql);

    long qt_entrega = 0;
    try {

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        // System.out.println (" BD OK ->> volta");
        qt_entrega = res.getLong ("qt_entrega");
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de total_Entrega_Cliente");
      excecoes.setExc (exc);
    }
    return qt_entrega;

  }

  public GerencialED totaliza_cliente_Anual (GerencialED ed) throws Excecoes {

    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
    int mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
    int ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();
    int mesatual = mes1;
    int anoatual = ano1;
    int mesler = 12;

    String oid_Vendedor = ed.getOID_Vendedor ();

    // System.out.println (" BD 0 totaliza_cliente_Anual");

    try {

      String sql = " SELECT " +
          " Clientes.oid_Cliente, Pessoas.NM_Razao_Social " +
          " FROM  Clientes, Pessoas, Conhecimentos" +
          " WHERE Clientes.oid_Cliente = Pessoas.oid_Pessoa" +
          " AND   Conhecimentos.oid_Pessoa_Pagador = Clientes.oid_Cliente";

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Clientes.oid_Cliente = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Grupo_Economico ()) != null && !String.valueOf (ed.getOID_Grupo_Economico ()).equals ("0")) {
        sql += " and Clientes.OID_Grupo_Economico = " + ed.getOID_Grupo_Economico ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {

        // System.out.println (" Cliente ->> " + res.getString ("oid_Cliente") + "- " + res.getString ("NM_Razao_Social"));

        GerencialED edGerencial = new GerencialED ();
        edGerencial.setOID_Pessoa (res.getString ("oid_Cliente"));
        edGerencial.setNM_Campo ( (res.getString ("NM_Razao_Social") + "                               ").substring (0 , 25));
        edGerencial.setNR_Sort (Nr_Sort);

        mesatual = mes1;
        anoatual = ano1;
        mesler = 12;
        int volta = 0;

        while (anoatual <= ano2) {
          if (anoatual == ano2) {
            mesler = mes2;
          }
          while (mesatual <= mesler) {
            String mes_atual = String.valueOf (mesatual);
            if (mesatual < 10) {
              mes_atual = "0" + mes_atual;
            }
            data1 = "01/" + mes_atual + "/" + String.valueOf (anoatual);
            data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_atual + "/" + String.valueOf (anoatual));

            // // System.out.println (" Cliente data1,data2->> " + data1 + "-" + data2);
            volta++;
            if (volta == 1) {
              edGerencial.setVL_Mes1 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 2) {
              edGerencial.setVL_Mes2 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 3) {
              edGerencial.setVL_Mes3 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 4) {
              edGerencial.setVL_Mes4 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 5) {
              edGerencial.setVL_Mes5 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 6) {
              edGerencial.setVL_Mes6 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 7) {
              edGerencial.setVL_Mes7 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 8) {
              edGerencial.setVL_Mes8 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 9) {
              edGerencial.setVL_Mes9 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 10) {
              edGerencial.setVL_Mes10 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 11) {
              edGerencial.setVL_Mes11 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            if (volta == 12) {
              edGerencial.setVL_Mes12 (total_Faturamento_Mes (data1 , data2 , res.getString ("oid_Cliente") , oid_Vendedor));
            }
            ordem++;
            mesatual++;
          }
          mesatual = 1;
          anoatual++;
        }
        double vl_total_ano = edGerencial.getVL_Mes1 () + edGerencial.getVL_Mes2 () + edGerencial.getVL_Mes3 () + edGerencial.getVL_Mes4 () + edGerencial.getVL_Mes5 () + edGerencial.getVL_Mes6 () + edGerencial.getVL_Mes7 () + edGerencial.getVL_Mes8 () + edGerencial.getVL_Mes9 () + edGerencial.getVL_Mes10 () + edGerencial.getVL_Mes11 () + edGerencial.getVL_Mes12 ();
        if (vl_total_ano > 0) {
          edGerencial.setVL_1 (vl_total_ano);
          inclui (edGerencial);
        }

        // System.out.println (" BD OK ->> volta");
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de totaliza_cliente");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_Ordens_Fretes_Terceiro_Anual (GerencialED ed) throws Excecoes {

    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
    int mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
    int ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();
    int mesatual = mes1;
    int anoatual = ano1;
    int mesler = 12;

    // System.out.println (" BD 0 totaliza_Ordens_Fretes_Terceiro_Anual");

    try {

      GerencialED edGerencial = new GerencialED ();
      edGerencial.setOID_Pessoa (parametro_FixoED.getOID_Cliente_Complemento_Padrao ());
      edGerencial.setNM_Campo ("ORDENS FRETER TERCEIROS");
      edGerencial.setNR_Sort (Nr_Sort);

      mesatual = mes1;
      anoatual = ano1;
      mesler = 12;
      int volta = 0;

      while (anoatual <= ano2) {
        if (anoatual == ano2) {
          mesler = mes2;
        }
        while (mesatual <= mesler) {
          String mes_atual = String.valueOf (mesatual);
          if (mesatual < 10) {
            mes_atual = "0" + mes_atual;
          }
          data1 = "01/" + mes_atual + "/" + String.valueOf (anoatual);
          data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_atual + "/" + String.valueOf (anoatual));

          // // System.out.println (" Cliente data1,data2->> " + data1 + "-" + data2);
          volta++;
          if (volta == 1) {
            edGerencial.setVL_Mes1 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 2) {
            edGerencial.setVL_Mes2 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 3) {
            edGerencial.setVL_Mes3 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 4) {
            edGerencial.setVL_Mes4 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 5) {
            edGerencial.setVL_Mes5 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 6) {
            edGerencial.setVL_Mes6 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 7) {
            edGerencial.setVL_Mes7 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 8) {
            edGerencial.setVL_Mes8 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 9) {
            edGerencial.setVL_Mes9 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 10) {
            edGerencial.setVL_Mes10 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 11) {
            edGerencial.setVL_Mes11 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          if (volta == 12) {
            edGerencial.setVL_Mes12 (total_Receita_Ordem_Frete (data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade ()));
          }
          ordem++;
          mesatual++;
        }
        mesatual = 1;
        anoatual++;
      }
      double vl_total_ano = edGerencial.getVL_Mes1 () + edGerencial.getVL_Mes2 () + edGerencial.getVL_Mes3 () + edGerencial.getVL_Mes4 () + edGerencial.getVL_Mes5 () + edGerencial.getVL_Mes6 () + edGerencial.getVL_Mes7 () + edGerencial.getVL_Mes8 () + edGerencial.getVL_Mes9 () + edGerencial.getVL_Mes10 () + edGerencial.getVL_Mes11 () + edGerencial.getVL_Mes12 ();
      if (vl_total_ano > 0) {
        edGerencial.setVL_1 (vl_total_ano);
        inclui (edGerencial);
      }

      // System.out.println (" BD OK ->> volta");
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de totaliza_cliente");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_Veiculo_Anual (GerencialED ed) throws Excecoes {

    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
    int mes2 = 12;
    int ano2 = 2010;

    if ("--".equals (ed.getNM_Mes_Final ())) {
      mes2 = mes1;
      ed.setNM_Mes_Final (ed.getNM_Mes_Inicial ());
    }
    else {
      mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
    }
    if ("----".equals (ed.getNM_Ano_Final ())) {
      ano2 = ano1;
      ed.setNM_Ano_Final (ed.getNM_Ano_Inicial ());
    }
    else {
      ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();
    }

    // System.out.println (" BD 0 totaliza_Veiculo_Anual");
    int mesatual = mes1;
    int anoatual = ano1;
    int mesler = 12;
    int totalizar = 0;
    double vl_total_ano = 0;
    int mes_trimestre = 1;

    try {
      String sql = " SELECT " +
          " Veiculos.oid_Veiculo " +
          " FROM  Veiculos, Complementos_Veiculos, Modelos_Veiculos, Tipos_Veiculos " +
          " WHERE Veiculos.oid_Veiculo = Complementos_Veiculos.oid_Veiculo" +
          " AND   Veiculos.oid_Modelo_Veiculo = Modelos_Veiculos.oid_Modelo_Veiculo" +
          " AND   Modelos_Veiculos.oid_Tipo_Veiculo = Tipos_Veiculos.oid_Tipo_Veiculo";

      if ("S".equals (ed.getDM_Tipo ())) { //selecionados
        sql += " AND  Veiculos.dm_mapa='S' ";
      }
      if ("PA".equals (ed.getDM_Tipo ())) { //proprios+agreg
        sql += " AND   (Complementos_Veiculos.dm_procedencia='P' OR Complementos_Veiculos.dm_procedencia='A') ";
      }
      if ("A".equals (ed.getDM_Tipo ())) { //agreg
        sql += " AND   Complementos_Veiculos.dm_procedencia='A' ";
      }
      if ("P".equals (ed.getDM_Tipo ())) { //propri
        sql += " AND   Complementos_Veiculos.dm_procedencia='P' ";
      }
      if ("T".equals (ed.getDM_Tipo ())) { //terc
        sql += " AND   Complementos_Veiculos.dm_procedencia='T' ";
      }

      if (ed.getOid_Modelo_Veiculo () > 0) {
        sql += " AND   Modelos_Veiculos.oid_Modelo_Veiculo= " + ed.getOid_Modelo_Veiculo ();
      }
      if (ed.getOid_Marca_Veiculo () > 0) {
        sql += " AND   Modelos_Veiculos.oid_Marca_Veiculo= " + ed.getOid_Marca_Veiculo ();
      }

      sql += " ORDER BY  Tipos_Veiculos.dm_tipo_implemento, Veiculos.oid_Veiculo ";

      // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {

        //tem que ler os tipos de servicos, dm_tipo_servico
        totalizar = 0;
        vl_total_ano = 0;

        // System.out.println (" oid_Veiculo ->> " + res.getString ("oid_Veiculo"));

        while (totalizar < 54) {

          // System.out.println ("totalizar ->>" + totalizar);

          mesatual = mes1;
          anoatual = ano1;
          mesler = 12;
          mes_trimestre = 1;
          GerencialED edGerencial = new GerencialED ();
          edGerencial.setOID_Veiculo (res.getString ("oid_Veiculo"));
          edGerencial.setNR_Sort (Nr_Sort);

          totalizar++;
          if (totalizar == 3) {
            totalizar = 11;
          }
          if (totalizar == 20) {
            totalizar = 31;
          }
          if (totalizar == 34) {
            totalizar = 51;
          }

          if (totalizar <= 10) {
            if (totalizar == 1) {
              edGerencial.setNM_Campo ("FAT.PROPRIO");
            }
            if (totalizar == 2) {
              edGerencial.setNM_Campo ("FAT.TERCEIROS");
            }
            edGerencial.setNM_Campo2 ("RECEITA");
          }

          if (totalizar > 10 && totalizar <= 30) {
            edGerencial.setNM_Campo2 ("CUSTO VARIAVEL");
            if (totalizar == 11) {
              edGerencial.setNM_Campo ("MANUTENCAO");
            }
            if (totalizar == 13) {
              edGerencial.setNM_Campo ("PNEUS");
            }
            if (totalizar == 14) {
              edGerencial.setNM_Campo ("PEDAGIO");
            }
            if (totalizar == 15) {
              edGerencial.setNM_Campo ("CARGA/DESC.");
            }
            if (totalizar == 16) {
              edGerencial.setNM_Campo ("DESP.VIAGEM");
            }
            if (totalizar == 17) {
              edGerencial.setNM_Campo ("DIARIAS");
            }
            if (totalizar == 18) {
              edGerencial.setNM_Campo ("LAVAG./LUB.");
            }
            if (totalizar == 19) {
              edGerencial.setNM_Campo ("RASTREAMENTO");
            }
          }
          if (totalizar > 30 && totalizar <= 50) {
            edGerencial.setNM_Campo2 ("CUSTO FIXO");
            if (totalizar == 31) {
              edGerencial.setNM_Campo ("IPVA");
            }
            if (totalizar == 32) {
              edGerencial.setNM_Campo ("SEGURO");
            }
            if (totalizar == 33) {
              edGerencial.setNM_Campo ("DEPRECIACAO");
            }
          }

          if (totalizar == 51) {
            edGerencial.setNM_Campo ("COMISSAO");
            edGerencial.setNM_Campo2 ("CUSTO VARIAVEL");
          }

          if (totalizar == 52) {
            edGerencial.setNM_Campo ("KILOMETRAGEM");
            edGerencial.setNM_Campo2 ("MEDIA");
          }
          if (totalizar == 53) {
            edGerencial.setNM_Campo ("COMBUSTIVEL");
            edGerencial.setNM_Campo2 ("CUSTO VARIAVEL");
          }

          while (anoatual <= ano2) {
            if (anoatual == ano2) {
              mesler = mes2;
            }
            while (mesatual <= mesler) {
              String mes_atual = String.valueOf (mesatual);
              if (mesatual < 10) {
                mes_atual = "0" + mes_atual;
              }
              data1 = "01/" + mes_atual + "/" + String.valueOf (anoatual);
              data2 = Data.getDataMaximaNoMes ("31" + "/" + mes_atual + "/" + String.valueOf (anoatual));

              // // System.out.println (" Cliente data1,data2->> " + data1 + "-" + data2);
              if ("R_ANUAL1".equals (ed.getDM_Relatorio ())) { /// anual
                if (mesatual == 1) {
                  edGerencial.setVL_Mes1 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 2) {
                  edGerencial.setVL_Mes2 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 3) {
                  edGerencial.setVL_Mes3 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 4) {
                  edGerencial.setVL_Mes4 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 5) {
                  edGerencial.setVL_Mes5 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 6) {
                  edGerencial.setVL_Mes6 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 7) {
                  edGerencial.setVL_Mes7 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 8) {
                  edGerencial.setVL_Mes8 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 9) {
                  edGerencial.setVL_Mes9 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 10) {
                  edGerencial.setVL_Mes10 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 11) {
                  edGerencial.setVL_Mes11 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mesatual == 12) {
                  edGerencial.setVL_Mes12 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
              }
              else { /// trimestral
                if (mes_trimestre == 1) {
                  edGerencial.setVL_Mes1 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mes_trimestre == 2) {
                  edGerencial.setVL_Mes2 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
                if (mes_trimestre == 3) {
                  edGerencial.setVL_Mes3 (totalizar_Veiculo (totalizar , data1 , data2 , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getString ("oid_Veiculo")));
                }
              }
              mesatual++;
              mes_trimestre++;
            }
            mesatual = 1;
            anoatual++;
          }
          vl_total_ano = edGerencial.getVL_Mes1 () + edGerencial.getVL_Mes2 () + edGerencial.getVL_Mes3 () + edGerencial.getVL_Mes4 () + edGerencial.getVL_Mes5 () + edGerencial.getVL_Mes6 () + edGerencial.getVL_Mes7 () + edGerencial.getVL_Mes8 () + edGerencial.getVL_Mes9 () + edGerencial.getVL_Mes10 () + edGerencial.getVL_Mes11 () + edGerencial.getVL_Mes12 ();
          if (vl_total_ano > 0) {
            edGerencial.setVL_1 (vl_total_ano);
            inclui (edGerencial);
          }

        }

        // System.out.println (" BD OK ->> volta");
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de totaliza_cliente");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_prazo_medio (GerencialED ed) throws Excecoes {

    OID_Gerencial = 0;
    GerencialED edGerencial = new GerencialED ();
    ResultSet resLocal = null;

    // System.out.println ("totaliza " + NM_Campo);

    try {

      edGerencial.setNR_Sort (Nr_Sort);
      edGerencial.setNM_Campo (NM_Campo);
      edGerencial.setNM_Campo2 (NM_Campo2);

      edGerencial.setOID_Pessoa (OID_Pessoa);
      
      // System.out.println (" totaliza_prazo_medio 2 ");

      edGerencial = getByRecord (edGerencial);

      OID_Gerencial = edGerencial.getOID_Gerencial ();
      if ("A".equals (ed.getDM_Tipo ())) {
        if (dia1 > 0) {
          v1 = dia1 / lidos;
        }
        if (dia2 > 0) {
          v2 = dia2 / lidos;
        }
        if (dia3 > 0) {
          v3 = dia3 / lidos;
        }
        if (dia4 > 0) {
          v4 = dia4 / lidos;
        }
        if (dia5 > 0) {
          v5 = dia5 / lidos;
        }
        if (dia6 > 0) {
          v6 = dia6 / lidos;
        }
      }
      else {
        if ("PC".equals (ed.getDM_Tipo ())) {
          tt_Prazo_Medio = this.totaliza_Prazo_Medio (ed , OID_Pessoa);
        }
        if (dia1 > 0) {
          v1 = dia1 / tt_Prazo_Medio;
        }
        if (dia2 > 0) {
          v2 = dia2 / tt_Prazo_Medio;
        }
        if (dia3 > 0) {
          v3 = dia3 / tt_Prazo_Medio;
        }
        if (dia4 > 0) {
          v4 = dia4 / tt_Prazo_Medio;
        }
        if (dia5 > 0) {
          v5 = dia5 / tt_Prazo_Medio;
        }
        if (dia6 > 0) {
          v6 = dia6 / tt_Prazo_Medio;
        }
      }

      if (edGerencial.getOID_Gerencial () <= 0) {







        inclui (edGerencial = carregaED (edGerencial));
      }
      else {
        altera (edGerencial = carregaED (edGerencial));
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de Gerencial de dados");
      excecoes.setExc (exc);
    }
    return ed;

  }

  public GerencialED totaliza_conhecimento (GerencialED ed , ResultSet res , String acao) throws Excecoes {

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();
    Movimento_ConhecimentoRN movimento_ConhecimentoRN = new Movimento_ConhecimentoRN ();

    // System.out.println ("totaliza_conhecimento..." + acao);

    try {

      if (acao.equals ("zera")) {
        nr_despachos = 0;
        vl_total_frete = 0;
        vl_margem = 0;
        vl_notas_fiscais = 0;
        nr_peso = 0;
        nr_peso_cubado = 0;
        nr_volumes = 0;
      }

      vl_total_custo = res.getDouble ("vl_total_custo");
      if (vl_total_custo <= 0 && res.getString ("oid_conhecimento")!=null && res.getString ("oid_conhecimento").length()>4) {
        movimento_ConhecimentoED.setOID_Conhecimento (res.getString ("oid_conhecimento"));
        movimento_ConhecimentoED = movimento_ConhecimentoRN.calcula_Margem (movimento_ConhecimentoED);
        vl_total_custo = movimento_ConhecimentoED.getVL_Despesas ();
      }

      
      nr_despachos++;
      vl_total_frete = vl_total_frete + res.getDouble ("vl_total_frete");

      vl_margem = vl_margem + (res.getDouble ("vl_total_frete") - vl_total_custo);

      vl_notas_fiscais = vl_notas_fiscais + res.getDouble ("VL_Nota_Fiscal");
      nr_peso = nr_peso + res.getDouble ("nr_peso");
      nr_peso_cubado = nr_peso_cubado + res.getDouble ("nr_peso_cubado");
      nr_volumes = nr_volumes + res.getDouble ("NR_Volumes");

      tnr_despachos++;
      tvl_margem = tvl_margem + vl_margem;
      tvl_total_frete = tvl_total_frete + res.getDouble ("vl_total_frete");
      tvl_notas_fiscais = tvl_notas_fiscais + res.getDouble ("VL_Nota_Fiscal");
      tnr_peso = tnr_peso + res.getDouble ("nr_peso");
      tnr_peso_cubado = tnr_peso_cubado + res.getDouble ("nr_peso_cubado");
      tnr_volumes = tnr_volumes + res.getDouble ("NR_Volumes");

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao totaliza_conhecimentor ");
      excecoes.setMetodo ("");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public GerencialED carregaED (GerencialED ed) throws Excecoes {

    GerencialED edGerencial = new GerencialED ();
    try {

      // System.out.println (" carrega ed OID_Gerencial ->> " + OID_Gerencial);
      // System.out.println (" carrega ed OID_Pessoa ->> " + OID_Pessoa);
      // System.out.println (" carrega ed OID_Modal ->> " + OID_Modal);
      // System.out.println (" carrega ed OID_Veiculo ->> " + OID_Veiculo);

      // System.out.println (" carrega ed OID_Grupo_Economico ->> " + OID_Grupo_Economico);
      // System.out.println (" carrega ed vl_total_frete ->> " + vl_total_frete);

      edGerencial.setOID_Gerencial (OID_Gerencial);

      edGerencial.setOID_Pessoa (OID_Pessoa);
      edGerencial.setOID_Modal (OID_Modal);
      edGerencial.setOID_Veiculo (OID_Veiculo);
      edGerencial.setOID_Grupo_Economico (OID_Grupo_Economico);
      edGerencial.setOID_Cidade_Origem (OID_Cidade_Origem);
      edGerencial.setOID_Cidade_Destino (OID_Cidade_Destino);
      edGerencial.setOID_Regiao_Destino (OID_Regiao_Destino);
      edGerencial.setOID_Unidade_Origem (OID_Unidade_Origem);
      edGerencial.setOID_Unidade_Destino (OID_Unidade_Destino);
      edGerencial.setNR_Sort (Nr_Sort);
      edGerencial.setDM_Situacao_Cliente (DM_Situacao_Cliente);
      edGerencial.setNM_Origem (NM_Origem);
      edGerencial.setNM_Destino (NM_Destino);

      edGerencial.setNM_Campo (NM_Campo);
      edGerencial.setNM_Campo2 (NM_Campo2);
      edGerencial.setVL_Total_Frete (vl_total_frete);
      edGerencial.setVL_Margem (vl_margem);
      edGerencial.setVL_Notas_Fiscais (vl_notas_fiscais);
      edGerencial.setNR_Despachos (nr_despachos);
      edGerencial.setNR_Peso (nr_peso);
      edGerencial.setNR_Peso_Cubado (nr_peso_cubado);
      edGerencial.setNR_Volumes (nr_volumes);
      // System.out.println (" carrega ed 3");

      edGerencial.setTVL_Total_Frete (tvl_total_frete);
      edGerencial.setTVL_Margem (tvl_margem);
      edGerencial.setTVL_Notas_Fiscais (tvl_notas_fiscais);
      edGerencial.setTNR_Despachos (tnr_despachos);
      edGerencial.setTNR_Peso (tnr_peso);
      edGerencial.setTNR_Peso_Cubado (tnr_peso_cubado);
      edGerencial.setTNR_Volumes (tnr_volumes);
      // System.out.println (" carrega ed 4");

      edGerencial.setOID_Carteira (OID_Carteira);
      edGerencial.setOID_Conta (OID_Conta);
      edGerencial.setOID_Conta_Corrente (OID_Conta_Corrente);
      // System.out.println (" carrega ed 5");
      edGerencial.setVL_Vencido (vencido);
      edGerencial.setVL_1 (v1);
      edGerencial.setVL_2 (v2);
      edGerencial.setVL_3 (v3);
      edGerencial.setVL_4 (v4);
      edGerencial.setVL_5 (v5);
      edGerencial.setVL_6 (v6);
      edGerencial.setVL_7 (v7);
      edGerencial.setVL_8 (v8);
      edGerencial.setVL_9 (v9);
      edGerencial.setVL_10 (v10);
      edGerencial.setVL_Vencer (vencer);
      // System.out.println (" carrega ed 6");

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao totaliza_conhecimentor ");
      excecoes.setMetodo ("");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edGerencial;
  }

  public byte[] imprime_Gerencial_Movimento_Conhecimento (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    // System.out.println (" ATE imprime_Gerencial_Movimento_Conhecimento ");

    try {

      this.inicioTransacao ();
      this.executasql = this.sql;

      ResultSet res = null;

      sql = " select ";
      sql += "  GERENCIAL.OID_GERENCIAL, ";
      sql += "  GERENCIAL.VL_TOTAL_FRETE, ";
      sql += "  GERENCIAL.NR_PESO, ";
      sql += "  GERENCIAL.OID_PESSOA, ";
      sql += "  GERENCIAL.NR_PESO_CUBADO, ";
      sql += "  GERENCIAL.NR_VOLUMES, ";
      sql += "  GERENCIAL.VL_MARGEM, ";
      sql += "  GERENCIAL.NR_DESPACHOS, ";
      sql += "  GERENCIAL.NR_SORT, ";
      sql += "  GERENCIAL.VL_NOTAS_FISCAIS, ";
      sql += "  GERENCIAL.VL_COLETA, ";
      sql += "  GERENCIAL.VL_ICMS, ";
      sql += "  GERENCIAL.VL_SIMPLES, ";
      sql += "  GERENCIAL.VL_SEGURO, ";
      sql += "  GERENCIAL.VL_CUSTO_COLETA, ";
      sql += "  GERENCIAL.VL_CUSTO_ENTREGA, ";
      sql += "  GERENCIAL.VL_CARGA, ";
      sql += "  GERENCIAL.NM_CAMPO, ";

      if (DM_Quebra_Cliente.equals ("S")) {
        sql += "  NM_Razao_Social ";
        sql += "  from Gerencial,  Pessoas ";
        sql += "  where Gerencial.oid_pessoa = Pessoas.oid_pessoa ";
      }
      if (DM_Quebra_Modal.equals ("S")) {
        sql += "  Modal.CD_Modal, ";
        sql += "  Modal.NM_Modal ";
        sql += "  from Gerencial, Modal ";
        sql += "  where Gerencial.oid_modal = modal.oid_modal ";
      }
      if (DM_Quebra_Grupo_Economico.equals ("S")) {
        sql += "  Grupos_Economicos.CD_Grupo_Economico, ";
        sql += "  Grupos_Economicos.NM_Grupo_Economico ";
        sql += "  from Gerencial, Grupos_Economicos ";
        sql += "  where Gerencial.oid_Grupo_Economico = Grupos_Economicos.oid_Grupo_Economico ";
      }

      if (DM_Quebra_Cidade.equals ("S")) {
        sql += " Cidade_Origem.nm_cidade as NM_Cidade_Origem, Cidade_Destino.nm_cidade as NM_Cidade_Destino ";
        sql += " from Gerencial, Cidades Cidade_Origem, Cidades Cidade_Destino  ";
        sql += " where Gerencial.oid_Cidade_Origem  = Cidade_Origem.oid_Cidade ";
        sql += " and   Gerencial.oid_Cidade_Destino = Cidade_Destino.oid_Cidade ";
      }
      if (DM_Quebra_Vendedor.equals ("S")) {
        sql += "  NM_Razao_Social ";
        sql += "  from Gerencial,  Pessoas ";
        sql += "  where Gerencial.oid_pessoa = Pessoas.oid_pessoa ";
      }

      sql += " and   Gerencial.Nr_Sort = '" + ed.getNR_Sort () + "'";

      // System.out.println (" ATE OK ");

      if (ed.getDM_Classificar () != null && !ed.getDM_Classificar ().equals ("")) {
        if (ed.getDM_Classificar ().equals ("1")) {
          sql += " order by Gerencial.vl_total_Frete desc ";
        }
        if (ed.getDM_Classificar ().equals ("2")) {
          sql += " order by Gerencial.vl_margem desc ";
        }
        if (ed.getDM_Classificar ().equals ("3")) {
          sql += " order by Gerencial.NR_Peso desc ";
        }
        if (ed.getDM_Classificar ().equals ("4")) {
          sql += " order by Gerencial.NR_Peso_Cubado desc ";
        }
        if (ed.getDM_Classificar ().equals ("5")) {
          sql += " order by Gerencial.NR_Volumes desc ";
        }
        if (ed.getDM_Classificar ().equals ("6")) {
          sql += " order by Gerencial.VL_Notas_Fiscais desc ";
        }
        if (ed.getDM_Classificar ().equals ("7")) {
          sql += " order by Gerencial.NR_Despachos desc ";
        }
      }
      else {
        sql += " order by Gerencial.vl_total_Frete desc ";
      }

      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01 (res , ed , executasql);

      this.fimTransacao (false);

//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo02(res,ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Conhecimento (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    try {

      this.inicioTransacao ();
      this.executasql = this.sql;

      ResultSet res = null;

      sql = " select ";
      sql += "  GERENCIAL.OID_GERENCIAL, ";
      sql += "  GERENCIAL.VL_TOTAL_FRETE, ";
      sql += "  GERENCIAL.NR_PESO, ";
      sql += "  GERENCIAL.OID_PESSOA, ";
      sql += "  GERENCIAL.OID_VEICULO, ";
      sql += "  GERENCIAL.NR_PESO_CUBADO, ";
      sql += "  GERENCIAL.NR_VOLUMES, ";
      sql += "  GERENCIAL.VL_MARGEM, ";
      sql += "  GERENCIAL.NR_DESPACHOS, ";
      sql += "  GERENCIAL.NR_SORT, ";
      sql += "  GERENCIAL.VL_NOTAS_FISCAIS, ";
      sql += "  GERENCIAL.DM_Situacao_Cliente, ";
      sql += "  GERENCIAL.NM_Origem, ";
      sql += "  GERENCIAL.NM_Destino, ";
      sql += "  GERENCIAL.VL_COLETA, ";
      sql += "  GERENCIAL.VL_ICMS, ";
      sql += "  GERENCIAL.VL_SIMPLES, ";
      sql += "  GERENCIAL.VL_SEGURO, ";
      sql += "  GERENCIAL.VL_CUSTO_COLETA, ";
      sql += "  GERENCIAL.VL_CUSTO_ENTREGA, ";
      sql += "  GERENCIAL.VL_CARGA, ";
      sql += "  GERENCIAL.NM_CAMPO ";

      if (DM_Quebra_Cliente.equals ("S")) {
        sql += "  ,NM_Razao_Social ";
        sql += "  from Gerencial,  Pessoas ";
        sql += "  where Gerencial.oid_pessoa = Pessoas.oid_pessoa ";
      }

      if (DM_Quebra_Fornecedor.equals ("S")) {
        sql += "  ,NM_Razao_Social ";
        sql += "  from Gerencial,  Pessoas ";
        sql += "  where Gerencial.oid_pessoa = Pessoas.oid_pessoa ";
      }

      // System.out.println ("sql 1->>  " + sql);

      if (DM_Quebra_Modal.equals ("S")) {
        sql += "  ,Modal.CD_Modal, ";
        sql += "  Modal.NM_Modal ";
        sql += "  from Gerencial, Modal ";
        sql += "  where Gerencial.oid_modal = modal.oid_modal ";
      }

      if (DM_Quebra_Veiculo.equals ("S")) {
        sql += "  ,Veiculos.NR_Frota, ";
        sql += "  Veiculos.NR_Placa, ";
        sql += "  Modelos_Veiculos.NM_Modelo_Veiculo, ";
        sql += "  Tipos_Veiculos.NM_Tipo_Veiculo, ";
        sql += "  Marcas_Veiculos.NM_Marca_Veiculo ";
        sql += "  from Gerencial, Veiculos, Modelos_Veiculos, Marcas_Veiculos, Tipos_Veiculos  ";
        sql += "  where Gerencial.oid_Veiculo = Veiculos.oid_Veiculo ";
        sql += "  AND   Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo ";
        sql += "  AND   Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo ";
        sql += "  AND   Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo ";
      }

      if (DM_Quebra_Grupo_Economico.equals ("S")) {
        sql += "  ,Grupos_Economicos.CD_Grupo_Economico, ";
        sql += "  Grupos_Economicos.NM_Grupo_Economico ";
        sql += "  from Gerencial, Grupos_Economicos ";
        sql += "  where Gerencial.oid_Grupo_Economico = Grupos_Economicos.oid_Grupo_Economico ";
      }

      if (DM_Quebra_Cidade.equals ("S")) {
        sql += " ,Cidade_Origem.nm_Cidade as NM_Cidade_Origem, Cidade_Destino.nm_cidade as NM_Cidade_Destino ";
        sql += " from Gerencial, Cidades Cidade_Origem, Cidades Cidade_Destino  ";
        sql += " where Gerencial.oid_Cidade_Origem  = Cidade_Origem.oid_Cidade ";
        sql += " and   Gerencial.oid_Cidade_Destino = Cidade_Destino.oid_Cidade ";
      }

      if (DM_Quebra_Regiao.equals ("S")) {
        sql += " , Regioes_Estados.NM_Regiao_Estado ";
        sql += " from Gerencial, Regioes_Estados  ";
        sql += " where Gerencial.oid_Regiao_Destino  = Regioes_Estados.oid_Regiao_Estado ";
      }

      if (DM_Quebra_Vendedor.equals ("S")) {
        sql += "  ,NM_Razao_Social ";
        sql += "  from Gerencial,  Pessoas ";
        sql += "  where Gerencial.oid_pessoa = Pessoas.oid_pessoa ";
      }
      if (DM_Quebra_Pais.equals ("S")) {
        sql += " FROM  Gerencial ";
        sql += " where 1=1 ";
      }
      if (DM_Quebra_Situacao_Cliente.equals ("S")) {
        sql += " FROM  Gerencial ";
        sql += " where 1=1 ";
      }

      sql += " and   Gerencial.Nr_Sort = '" + ed.getNR_Sort () + "'";
      if (ed.getDM_Classificar () != null && !ed.getDM_Classificar ().equals ("null")) {
        if (ed.getDM_Classificar ().equals ("1")) {
          sql += " order by Gerencial.vl_total_Frete desc ";
        }
        if (ed.getDM_Classificar ().equals ("2")) {
          sql += " order by Gerencial.vl_margem desc ";
        }
        if (ed.getDM_Classificar ().equals ("3")) {
          sql += " order by Gerencial.NR_Peso desc ";
        }
        if (ed.getDM_Classificar ().equals ("4")) {
          sql += " order by Gerencial.NR_Peso_Cubado desc ";
        }
        if (ed.getDM_Classificar ().equals ("5")) {
          sql += " order by Gerencial.NR_Volumes desc ";
        }
        if (ed.getDM_Classificar ().equals ("6")) {
          sql += " order by Gerencial.VL_Notas_Fiscais desc ";
        }
        if (ed.getDM_Classificar ().equals ("7")) {
          sql += " order by Gerencial.NR_Despachos desc ";
        }
        if (ed.getDM_Classificar ().equals ("9")) {
          sql += " order by Gerencial.PE_Margem desc ";
        }
        if (ed.getDM_Classificar ().equals ("8")) {
          if (DM_Quebra_Veiculo.equals ("S")) {
            sql += " order by Veiculos.NR_Frota, Gerencial.oid_Veiculo ";
          }
        }
      }
      else {
        sql += " order by Gerencial.vl_total_Frete desc ";
      }
      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01 (res , ed , executasql);

      this.fimTransacao (false);

//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo02(res,ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Cliente (GerencialED ed) throws Excecoes {

    // System.out.println (" BD imprime_Gerencial_Cliente ");

    String sql = null;
    byte[] b = null;

    try {

      ResultSet res = null;

      sql = "  SELECT * " +
          "  FROM  Gerencial " +
          "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'" +
          "  ORDER by VL_1 Desc ";
      //"  ORDER by Gerencial.NM_Campo2 ";
      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Cliente (res , ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método imprime_Gerencial_Cliente");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("imprime_Gerencial_Cliente(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Fornecedores (GerencialED ed) throws Excecoes {

	    // System.out.println (" BD imprime_Gerencial_Fornecedor ");

	    String sql = null;
	    byte[] b = null;

	    try {

	      ResultSet res = null;

	      sql = "  SELECT * " +
	          "  FROM  Gerencial " +
	          "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'" +
	          "  ORDER by Gerencial.NM_Campo ";
	      // System.out.println ("sql ->>  " + sql);

	      res = this.executasql.executarConsulta (sql.toString ());

	      GerencialRL conRL = new GerencialRL ();
	      
	      b = conRL.geraAnalise_Gerencial_Fornecedores (res , ed);

	    }

	    catch (Excecoes e) {
	      throw e;
	    }
	    catch (Exception exc) {
	      Excecoes exce = new Excecoes ();
	      exce.setExc (exc);
	      exce.setMensagem ("Erro no método imprime_Gerencial_Cliente");
	      exce.setClasse (this.getClass ().getName ());
	      exce.setMetodo ("imprime_Gerencial_Cliente(GerencialED ed)");
	    }
	    return b;
	  }

  public byte[] imprime_Gerencial_Tabelas_Fretes (GerencialED ed) throws Excecoes {

	    // System.out.println (" BD imprime_Gerencial_Modal ");

	    String sql = null;
	    byte[] b = null;

	    try {

	      ResultSet res = null;

	      sql = "  SELECT * " +
	          "  FROM  Gerencial, Pessoas " +
	          "  WHERE Gerencial.oid_Pessoa = Pessoas.oid_Pessoa " +
	          "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'" +
	          "  ORDER by Pessoas.NM_Razao_Social, Gerencial.NM_Campo2 ";

	      
	      // System.out.println ("sql ->>  " + sql);

	      res = this.executasql.executarConsulta (sql.toString ());

	      GerencialRL conRL = new GerencialRL ();
	      
	      b = conRL.geraAnalise_Gerencial_Tabelas_Fretes (res , ed);

	    }

	    catch (Excecoes e) {
	      throw e;
	    }
	    catch (Exception exc) {
	      Excecoes exce = new Excecoes ();
	      exce.setExc (exc);
	      exce.setMensagem ("Erro no método imprime_Gerencial_Cliente");
	      exce.setClasse (this.getClass ().getName ());
	      exce.setMetodo ("imprime_Gerencial_Cliente(GerencialED ed)");
	    }
	    return b;
  }

  public byte[] imprime_Gerencial_Modal (GerencialED ed) throws Excecoes {

	    // System.out.println (" BD imprime_Gerencial_Modal ");

	    String sql = null;
	    byte[] b = null;

	    try {

	      ResultSet res = null;

	      sql = "  SELECT * " +
	          "  FROM  Gerencial" +
	          "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'" +
	          "  ORDER by Gerencial.NM_Campo ";
	      // System.out.println ("sql ->>  " + sql);

	      res = this.executasql.executarConsulta (sql.toString ());

	      GerencialRL conRL = new GerencialRL ();
	      
	      b = conRL.geraAnalise_Gerencial_Modal (res , ed);

	    }

	    catch (Excecoes e) {
	      throw e;
	    }
	    catch (Exception exc) {
	      Excecoes exce = new Excecoes ();
	      exce.setExc (exc);
	      exce.setMensagem ("Erro no método imprime_Gerencial_Cliente");
	      exce.setClasse (this.getClass ().getName ());
	      exce.setMetodo ("imprime_Gerencial_Cliente(GerencialED ed)");
	    }
	    return b;
  }
  
  public byte[] imprime_Gerencial_Cliente_Modal (GerencialED ed) throws Excecoes {

    // System.out.println (" BD imprime_Gerencial_Cliente ");

    String sql = null;
    byte[] b = null;

    try {

      ResultSet res = null;

      sql = "  SELECT * " +
          "  FROM  Gerencial, Pessoas, Modal " +
          "  WHERE Gerencial.oid_Pessoa = Pessoas.oid_Pessoa " +
          "  AND   Gerencial.oid_Modal = Modal.oid_Modal " +
          "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'" +
          "  ORDER by Pessoas.NM_Razao_Social, Modal.CD_Modal ";
          
      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Cliente_Modal (res , ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método imprime_Gerencial_Cliente");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("imprime_Gerencial_Cliente(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Veiculos_Anual (GerencialED ed) throws Excecoes {

    // System.out.println (" BD imprime_Gerencial_Cliente ");

    String sql = null;
    byte[] b = null;

    try {

      ResultSet res = null;

      sql = "  SELECT * " +
          "  FROM  Gerencial, Modelos_Veiculos, Veiculos " +
          "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'" +
          "  AND   Gerencial.oid_Veiculo = Veiculos.oid_Veiculo" +
          "  AND   Veiculos.oid_Modelo_Veiculo = Modelos_Veiculos.oid_Modelo_Veiculo" +
          "  ORDER by oid_Gerencial ";
      //"  ORDER by Gerencial.NM_Campo2 ";

      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      ResultSet resTotal = null;

      sql = "  SELECT NM_Campo, SUM(VL_Mes1) as VL_Mes1, SUM(VL_Mes2) as VL_Mes2, SUM(VL_Mes3) as VL_Mes3 , SUM(VL_1) as VL_1  " +
          "  FROM  Gerencial " +
          "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'" +
          "  GROUP by Gerencial.NM_Campo  " +
          "  ORDER by Gerencial.NM_Campo  ";

      // System.out.println ("sql ->>  " + sql);

      resTotal = this.executasql.executarConsulta (sql.toString ());

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Veiculo_Anual (res , resTotal , ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método imprime_Gerencial_Cliente");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("imprime_Gerencial_Cliente(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Receita_Despesa (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    double vl_total_despesa1 = 0 , vl_total_receita1 = 0 , vl_total_investimento1 = 0;
    double vl_total_despesa2 = 0 , vl_total_receita2 = 0 , vl_total_investimento2 = 0;
    double vl_total_despesa3 = 0 , vl_total_receita3 = 0 , vl_total_investimento3 = 0;
    try {

      ResultSet res = null;

      sql = " select ";
      sql += "  SUM (GERENCIAL.VL_1) as VL_1, ";
      sql += "  SUM (GERENCIAL.VL_2) as VL_2, ";
      sql += "  SUM (GERENCIAL.VL_3) as VL_3  ";
      sql += "  from Gerencial, Contas ";
      sql += "  WHERE Gerencial.NM_CAMPO = '1-Receita'";
      sql += "  AND   Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }
      // System.out.println (" imp1  ");

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        // System.out.println ("somando receita ");
        vl_total_receita1 = res.getDouble ("VL_1");
        vl_total_receita2 = res.getDouble ("VL_2");
        vl_total_receita3 = res.getDouble ("VL_3");
      }

      sql = " select ";
      sql += "  SUM (GERENCIAL.VL_1) as VL_1, ";
      sql += "  SUM (GERENCIAL.VL_2) as VL_2, ";
      sql += "  SUM (GERENCIAL.VL_3) as VL_3  ";
      sql += "  from Gerencial, Contas ";
      sql += "  WHERE Gerencial.NM_CAMPO = '2-Despesa'";
      sql += "  AND   Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }

      // System.out.println (" imp1  2");

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        // System.out.println ("somando despesa ");
        vl_total_despesa1 = res.getDouble ("VL_1");
        vl_total_despesa2 = res.getDouble ("VL_2");
        vl_total_despesa3 = res.getDouble ("VL_3");
      }

      sql = " select ";
      sql += "  SUM (GERENCIAL.VL_1) as VL_1, ";
      sql += "  SUM (GERENCIAL.VL_2) as VL_2, ";
      sql += "  SUM (GERENCIAL.VL_3) as VL_3  ";
      sql += "  from Gerencial, Contas ";
      sql += "  WHERE Gerencial.NM_CAMPO = '3-Investimento'";
      sql += "  AND   Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }

      // System.out.println (" imp1  2");

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        // System.out.println ("somando Investimento ");
        vl_total_investimento1 = res.getDouble ("VL_1");
        vl_total_investimento2 = res.getDouble ("VL_2");
        vl_total_investimento3 = res.getDouble ("VL_3");
      }

      sql = " select * ";
      sql += "  FROM  Gerencial, Contas, Grupos_Contas ";
      sql += "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'";
      sql += "  AND Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta ";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }

      if ("ARC".equals ( (ed.getDM_Relatorio () + "    ").substring (0 , 3))) {
        sql += "  order by Gerencial.NM_Campo,  Gerencial.VL_1 desc";
      }

      if ("AGC".equals ( (ed.getDM_Relatorio () + "    ").substring (0 , 3))) {
        sql += "  order by Gerencial.NM_Campo, Grupos_Contas.NM_Grupo_Conta, Contas.NM_Conta";
      }

      if ("AGT".equals ( (ed.getDM_Relatorio () + "    ").substring (0 , 3))) {
        sql += "  order by Gerencial.NM_Campo, Grupos_Contas.NM_Grupo_Conta, Contas.NM_Conta";
      }

      // System.out.println (" imp1 3 ");

      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
//      while (res.next()){
//         // System.out.println("r2 tem dados ");
//      }

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Receita_Despesa (res , ed , vl_total_receita1 , vl_total_despesa1 , vl_total_investimento1 , vl_total_receita2 , vl_total_despesa2 , vl_total_investimento2 , vl_total_receita3 , vl_total_despesa3 , vl_total_investimento3);

//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo02(res,ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Demonstrativo_Resultado (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    double vl_total_despesa1 = 0 , vl_total_receita1 = 0 , vl_total_investimento1 = 0;
    double vl_total_despesa2 = 0 , vl_total_receita2 = 0 , vl_total_investimento2 = 0;
    double vl_total_despesa3 = 0 , vl_total_receita3 = 0 , vl_total_investimento3 = 0;
    try {

      ResultSet res = null;

      sql = " select ";
      sql += "  SUM (GERENCIAL.VL_1) as VL_1, ";
      sql += "  SUM (GERENCIAL.VL_2) as VL_2, ";
      sql += "  SUM (GERENCIAL.VL_3) as VL_3  ";
      sql += "  from Gerencial, Contas ";
      sql += "  WHERE Gerencial.NM_CAMPO = '1-Receita'";
      sql += "  AND   Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }
      // System.out.println (" imp1  ");

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        // System.out.println ("somando receita ");
        vl_total_receita1 = res.getDouble ("VL_1");
        vl_total_receita2 = res.getDouble ("VL_2");
        vl_total_receita3 = res.getDouble ("VL_3");
      }

      sql = " select ";
      sql += "  SUM (GERENCIAL.VL_1) as VL_1, ";
      sql += "  SUM (GERENCIAL.VL_2) as VL_2, ";
      sql += "  SUM (GERENCIAL.VL_3) as VL_3  ";
      sql += "  from Gerencial, Contas ";
      sql += "  WHERE Gerencial.NM_CAMPO = '2-Despesa'";
      sql += "  AND   Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }

      // System.out.println (" imp1  2");

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        // System.out.println ("somando despesa ");
        vl_total_despesa1 = res.getDouble ("VL_1");
        vl_total_despesa2 = res.getDouble ("VL_2");
        vl_total_despesa3 = res.getDouble ("VL_3");
      }

      sql = " select ";
      sql += "  SUM (GERENCIAL.VL_1) as VL_1, ";
      sql += "  SUM (GERENCIAL.VL_2) as VL_2, ";
      sql += "  SUM (GERENCIAL.VL_3) as VL_3  ";
      sql += "  from Gerencial, Contas ";
      sql += "  WHERE Gerencial.NM_CAMPO = '3-Investimento'";
      sql += "  AND   Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }

      // System.out.println (" imp1  2");

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        // System.out.println ("somando Investimento ");
        vl_total_investimento1 = res.getDouble ("VL_1");
        vl_total_investimento2 = res.getDouble ("VL_2");
        vl_total_investimento3 = res.getDouble ("VL_3");
      }

      sql = " select * ";
      sql += "  FROM  Gerencial, Contas, Grupos_Contas ";
      sql += "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'";
      sql += "  AND Gerencial.oid_Conta = Contas.oid_Conta ";
      sql += "  AND Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta ";

      if (ed.getOID_Grupo_Conta () > 0) {
        sql += "  AND Contas.oid_Grupo_Conta = " + ed.getOID_Grupo_Conta ();
      }

      if ("DRE".equals ( (ed.getDM_Relatorio () + "    ").substring (0 , 3))) {
        sql += "  order by Gerencial.NM_Campo, Grupos_Contas.NM_Grupo_Conta, Contas.NM_Conta";
      }

      // System.out.println (" imp1 3 ");

      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
//      while (res.next()){
//         // System.out.println("r2 tem dados ");
//      }

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Demonstrativo_Resultado (res , ed , vl_total_receita1 , vl_total_despesa1 , vl_total_investimento1 , vl_total_receita2 , vl_total_despesa2 , vl_total_investimento2 , vl_total_receita3 , vl_total_despesa3 , vl_total_investimento3);

//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo02(res,ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Veiculos (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    double vl_total_frete = 0;
    try {

      ResultSet res = null;

      sql = " select ";
      sql += "  GERENCIAL.VL_1 ";
      sql += "  from Gerencial ";
      sql += "  WHERE Gerencial.NM_CAMPO = 'Veiculos'";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        vl_total_frete += res.getDouble ("VL_1");
      }

      sql = " select * ";
      sql += "  FROM  Gerencial ";
      sql += "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      if ("R1".equals (ed.getDM_Relatorio ())) {
        sql += "  order by Gerencial.OID_Veiculo ";
      }
      if ("R2".equals (ed.getDM_Relatorio ())) {
        sql += "  order by Gerencial.OID_Veiculo ";
      }

      res = this.executasql.executarConsulta (sql.toString ());

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Veiculos (res , ed , vl_total_frete , executasql);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Prazo_Medio (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    try {

      ResultSet res = null;

      sql = " select * ";
      sql += "  FROM  Gerencial ";
      sql += "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      sql += "  order by Gerencial.NM_Campo2 ";

      // System.out.println (" imprime_Gerencial_Prazo_Medio=============== " + sql);

      res = this.executasql.executarConsulta (sql);

      // System.out.println ("DM_Prazo_Medio->> " + DM_Prazo_Medio);

      while (res.next ()) {
        // System.out.println (" *** * * * * * * * * tem dados no RES->>>"+res.getString ("NM_CAMPO2") );
      }

      res = this.executasql.executarConsulta (sql);

      GerencialRL conRL = new GerencialRL ();
      if ("COBRANCA".equals (DM_Prazo_Medio)) {
        // System.out.println (" imp1 3 R ");
        b = conRL.geraAnalise_Gerencial_Prazo_Medio_Cobranca (res , ed);
      }

      if ("PAGAMENTO".equals (DM_Prazo_Medio)) {
        // System.out.println (" imp1 3 P ");

        b = conRL.geraAnalise_Gerencial_Prazo_Medio_Pagamento (res , ed);
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_financeiro (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    try {

      ResultSet res = null;

      sql = " select ";
      sql += "  GERENCIAL.OID_GERENCIAL, ";
      sql += "  GERENCIAL.OID_CONTA, ";
      sql += "  GERENCIAL.NM_CAMPO, ";
      sql += "  GERENCIAL.OID_CONTA_CORRENTE, ";
      sql += "  GERENCIAL.OID_CARTEIRA, ";
      sql += "  GERENCIAL.VL_VENCIDO, ";
      sql += "  GERENCIAL.VL_1, ";
      sql += "  GERENCIAL.VL_2, ";
      sql += "  GERENCIAL.VL_3, ";
      sql += "  GERENCIAL.VL_4, ";
      sql += "  GERENCIAL.VL_5, ";
      sql += "  GERENCIAL.VL_VENCER, ";
      sql += "  GERENCIAL.NR_SORT ";
      sql += "  from Gerencial ";
      sql += "  where Gerencial.Nr_Sort = '" + Nr_Sort + "'";
      sql += "  order by Gerencial.NM_Campo desc ";

// // System.out.println("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
//      while (res.next()){
//         // System.out.println("r2 tem dados ");
//      }

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Financeira_Modelo01 (res , ed);

//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo02(res,ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public void inclui (GerencialED ed) throws Excecoes {

    // System.out.println (" inclui 1");

    String sql = null;
    long valOid = 0;

    try {

      valOid = System.currentTimeMillis ();

      double pe_margem=0;
      if (ed.getVL_Total_Frete ()>0){
        pe_margem=(ed.getVL_Margem () / ed.getVL_Total_Frete () * 100);
      }
      // System.out.println (" inclui ed.campo " + ed.getNM_Campo ());
      // System.out.println (" inclui ed.campo2 " + ed.getNM_Campo2 ());

      ed.setNM_Campo2 ( (ed.getNM_Campo2 () + "                                                       ").substring (0 , 30).trim ());

      // System.out.println (" inclui 2");

      sql = " insert into Gerencial (OID_Gerencial, DM_Situacao_Cliente, NM_Campo, NM_Campo2, OID_Veiculo, OID_Pessoa, OID_Modal, OID_Grupo_Economico, OID_Conta, OID_Carteira, OID_Conta_Corrente, OID_Cidade_Origem, OID_Cidade_Destino, oid_Regiao_Destino, oid_Unidade_Origem, oid_Unidade_Destino, NM_Origem, NM_Destino, Nr_Sort, VL_Total_Frete, VL_Notas_Fiscais, NR_Peso, NR_Peso_Cubado, NR_Volumes,  NR_Despachos, VL_Vencido, NR_Entrega_Atrazada, NR_Entrega_Prazo, NR_Entrega_Antecipada, NR_Entrega_Justificada, VL_Mes1, VL_Mes2, VL_Mes3, VL_Mes4, VL_Mes5, VL_Mes6, VL_Mes7, VL_Mes8, VL_Mes9, VL_Mes10, VL_Mes11, VL_Mes12, Vl_1, Vl_2, Vl_3, Vl_4, Vl_5, Vl_6, Vl_7, Vl_8, Vl_9, Vl_10, Vl_11, Vl_12, Vl_13, Vl_14, Vl_15, Vl_16, Vl_17, Vl_18, Vl_19, Vl_20, Vl_21, Vl_22, Vl_23, Vl_24, Vl_25, Vl_26, Vl_27, Vl_28, Vl_29, Vl_30, Vl_31, Vl_vencer, PE_Margem , VL_Margem ) values ";
      sql += "(" + ++valOid + ",'";
      sql += ed.getDM_Situacao_Cliente () + "','";
      sql += ed.getNM_Campo () + "','";
      sql += ed.getNM_Campo2 () + "','";
      sql += ed.getOID_Veiculo () + "','";
      sql += ed.getOID_Pessoa () + "',";
      sql += ed.getOID_Modal () + ",";
      sql += ed.getOID_Grupo_Economico () + ",";
      sql += ed.getOID_Conta () + ",";
      sql += ed.getOID_Carteira () + ",";
      sql += ed.getOID_Conta_Corrente () + ",";
      sql += ed.getOID_Cidade_Origem () + ",";
      sql += ed.getOID_Cidade_Destino () + ",";
      sql += ed.getOID_Regiao_Destino () + ",";
      sql += ed.getOID_Unidade_Origem () + ",";
      sql += ed.getOID_Unidade_Destino () + ",'";
      sql += ed.getNM_Origem () + "','";
      sql += ed.getNM_Destino () + "','";
      sql += ed.getNR_Sort () + "',";
      sql += ed.getVL_Total_Frete () + ",";
      sql += ed.getVL_Notas_Fiscais () + ",";
      sql += ed.getNR_Peso () + ",";
      sql += ed.getNR_Peso_Cubado () + ",";
      sql += ed.getNR_Volumes () + ",";
      sql += ed.getNR_Despachos () + ",";
      sql += ed.getVL_Vencido () + ",";
      sql += ed.getNR_Entrega_Atrazada () + ",";
      sql += ed.getNR_Entrega_Prazo () + ",";
      sql += ed.getNR_Entrega_Antecipada () + ",";
      sql += ed.getNR_Entrega_Justificada () + ",";
      sql += ed.getVL_Mes1 () + ",";
      sql += ed.getVL_Mes2 () + ",";
      sql += ed.getVL_Mes3 () + ",";
      sql += ed.getVL_Mes4 () + ",";
      sql += ed.getVL_Mes5 () + ",";
      sql += ed.getVL_Mes6 () + ",";
      sql += ed.getVL_Mes7 () + ",";
      sql += ed.getVL_Mes8 () + ",";
      sql += ed.getVL_Mes9 () + ",";
      sql += ed.getVL_Mes10 () + ",";
      sql += ed.getVL_Mes11 () + ",";
      sql += ed.getVL_Mes12 () + ",";
      sql += ed.getVL_1 () + ",";
      sql += ed.getVL_2 () + ",";
      sql += ed.getVL_3 () + ",";
      sql += ed.getVL_4 () + ",";
      sql += ed.getVL_5 () + ",";
      sql += ed.getVL_6 () + ",";
      sql += ed.getVL_7 () + ",";
      sql += ed.getVL_8 () + ",";
      sql += ed.getVL_9 () + ",";
      sql += ed.getVL_10 () + ",";
      sql += ed.getVL_11 () + ",";
      sql += ed.getVL_12 () + ",";
      sql += ed.getVL_13 () + ",";
      sql += ed.getVL_14 () + ",";
      sql += ed.getVL_15 () + ",";
      sql += ed.getVL_16 () + ",";
      sql += ed.getVL_17 () + ",";
      sql += ed.getVL_18 () + ",";
      sql += ed.getVL_19 () + ",";
      sql += ed.getVL_20 () + ",";
      sql += ed.getVL_21 () + ",";
      sql += ed.getVL_22 () + ",";
      sql += ed.getVL_23 () + ",";
      sql += ed.getVL_24 () + ",";
      sql += ed.getVL_25 () + ",";
      sql += ed.getVL_26 () + ",";
      sql += ed.getVL_27 () + ",";
      sql += ed.getVL_28 () + ",";
      sql += ed.getVL_29 () + ",";
      sql += ed.getVL_30 () + ",";
      sql += ed.getVL_31 () + ",";
      sql += ed.getVL_Vencer () + ",";
      sql += pe_margem + ",";
      sql += ed.getVL_Margem () + ")";

      // System.out.println ("inclui" + sql);

      executasql.executarUpdate (sql);

     // sql = "SELECT count(oid_gerencial) as result FROM Gerencial ";
      //ResultSet res = this.executasql.executarConsulta (sql);
      //if (res.next ()) {
       // // System.err.println ("incluidos=>>>" + res.getLong ("result"));
      //}

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de Gerencial de dados");
      excecoes.setExc (exc);
    }
  }

  public void deleta (GerencialED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from Gerencial WHERE oid_Gerencial = ";
      sql += "(" + ed.getOID_Gerencial () + ")";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir ");
      excecoes.setMetodo ("deletar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public byte[] geraAnalise_Gerencial_Contas_Receber (GerencialED edComp) throws Excecoes {

    String sql = null;
    byte[] b = null;
    GerencialED ed = (GerencialED) edComp;

    sql = "select Duplicatas.OID_Duplicata, Pessoas.oid_pessoa, Pessoas.nr_cnpj_cpf," +
        "Pessoas.nm_razao_Social, Pessoas_Bancos.nm_razao_Social as " +
        "NM_Razao_Banco, Carteiras.oid_Carteira, Carteiras.cd_Carteira, " +
        "Contas_Correntes.NR_conta_corrente, Tipos_Documentos.oid_tipo_documento, " +
        "Tipos_Documentos.cd_tipo_documento, Tipos_Documentos.nm_tipo_documento, " +
        "Duplicatas.nr_Duplicata, Duplicatas.nr_parcela, Duplicatas.nr_documento, Duplicatas.nr_bancario, " +
        "Duplicatas.vl_juro_mora_dia, " +
        "Duplicatas.dt_vencimento, Duplicatas.vl_saldo, Duplicatas.dt_emissao, Duplicatas.vl_duplicata, " +
        "Duplicatas.vl_saldo from Duplicatas, pessoas, Pessoas Pessoas_Bancos, " +
        "tipos_documentos, Carteiras, Contas_Correntes where " +
        " duplicatas.oid_pessoa = pessoas.oid_pessoa and" +
        " duplicatas.oid_Carteira = Carteiras.oid_Carteira and" +
        " duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento and" +
        " duplicatas.VL_Saldo > 0 and" +
        " carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente and " +
        " contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa ";

    if (ed.getOID_Pessoa () != null) {
      sql += " and duplicatas.oid_pessoa = '" + ed.getOID_Pessoa () + "'";
    }

    if (ed.getOID_Carteira () != 0) {
      sql += " and duplicatas.oid_carteira = '" + ed.getOID_Carteira () + "'";
    }

    if (ed.getVL_Titulo_Inicial () != 0) {
      sql += " and duplicatas.vl_saldo >= " + ed.getVL_Titulo_Inicial ();
    }

    if (ed.getVL_Titulo_Final () != 0) {
      sql += " and duplicatas.vl_saldo <= " + ed.getVL_Titulo_Final ();
    }

    if (ed.getOID_Unidade () != 0) {
      sql += " and duplicatas.oid_Unidade = " + ed.getOID_Unidade ();
    }

    if (ed.getOID_Tipo_Documento () != null) {
      sql += " and duplicatas.oid_Tipo_Documento = " + ed.getOID_Tipo_Documento ();
    }

    if (ed.getDT_Emissao_Inicial () != null) {
      sql += " and duplicatas.dt_emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
    }

    if (ed.getDT_Emissao_Final () != null) {
      sql += " and duplicatas.dt_emissao <= '" + ed.getDT_Emissao_Inicial () + "'";
    }

    if (ed.getDT_Vencimento_Inicial () != null) {
      sql += " and duplicatas.dt_vencimento >= '" + ed.getDT_Vencimento_Inicial () + "'";
    }

    if (ed.getDT_Vencimento_Final () != null) {
      sql += " and duplicatas.dt_vencimento <= '" + ed.getDT_Vencimento_Final () + "'";
    }

    if (ed.getNM_Razao_Social () != null) {
      sql += " and pessoas.nm_razao_social like '" + ed.getNM_Razao_Social () + "%'";
    }

    String orderby = "duplicatas.dt_vencimento";

    if (ed.getDM_Classificar () != null) {
      if (ed.getDM_Classificar ().equals ("2")) {
        orderby = "duplicatas.vl_saldo";
      }
      else if (ed.getDM_Classificar ().equals ("3")) {
        orderby = "duplicatas.nr_duplicata";
      }
      else if (ed.getDM_Classificar ().equals ("4")) {
        orderby = "Pessoas.nm_razao_Social";
      }
    }

    if (ed.getDM_Relatorio ().equals ("RTCLI")) {
      sql += " order by Pessoas.oid_Pessoa , " + orderby;
    }
    else if (ed.getDM_Relatorio ().equals ("RTCLIGER")) {
      sql += " order by Pessoas.nm_razao_Social , " + orderby;
    }
    else if (ed.getDM_Relatorio ().equals ("RTCLIGER2")) {
      sql += " order by Pessoas.nm_razao_Social , " + orderby;
    }
    else if (ed.getDM_Relatorio ().equals ("RTCLIGER2CTO")) {
      sql += " order by Pessoas.nm_razao_Social , " + orderby;
    }
    else if (ed.getDM_Relatorio ().equals ("RTCART")) {
      sql += " order by carteiras.oid_Carteira , " + orderby;
    }
    else if (ed.getDM_Relatorio ().equals ("RTVEND")) {
      sql += " order by Duplicatas.oid_Vendedor , " + orderby;
    }

    try {

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      GerencialRL GerencialRL = new GerencialRL ();

      if (ed.getDM_Relatorio ().equals ("RTCLI")) {
        b = GerencialRL.geraRelTitCliente (res);
      }

      if (ed.getDM_Relatorio ().equals ("RTCLIGER")) {
        b = GerencialRL.geraRelTitClienteGeral (res);
      }

      if (ed.getDM_Relatorio ().equals ("RTCLIGER2") || ed.getDM_Relatorio ().equals ("RTCLIGER2CTO")) {
        b = GerencialRL.geraRelTitClienteGeral_2 (res , ed);
      }

      if (ed.getDM_Relatorio ().equals ("RTCART")) {
        b = GerencialRL.geraAnalise_Titulos_Carteira (res);
      }

      if (ed.getDM_Relatorio ().equals ("RTVEND")) {
        b = GerencialRL.geraAnalise_Titulos_Vendedor (res);
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraAnalise_Gerencial_Contas_Receber(GerencialED edComp)");
    }
    return b;
  }

  public byte[] geraAnalise_Gerencial_CRT (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    OID_Gerencial = 0;

    if (ed.getDM_Relatorio ().equals ("CSU")) {
      DM_Quebra_Unidade = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSV")) {
      DM_Quebra_Vendedor = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSM")) {
      DM_Quebra_Modal = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSP")) {
      DM_Quebra_Produto = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSC")) {
      DM_Quebra_Cliente = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCC")) {
      DM_Quebra_Cliente = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCV")) {
      DM_Quebra_Cliente = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSG")) {
      DM_Quebra_Grupo_Economico = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSCID")) {
      DM_Quebra_Cidade = "S";
    }
    if (ed.getDM_Relatorio ().equals ("CSPAIS")) {
      DM_Quebra_Pais = "S";
    }
    if (ed.getDM_Relatorio ().equals ("MAP1")) {
      DM_Mapa = "S";
    }
    if (ed.getDM_Relatorio ().equals ("REL1")) {
      DM_Analitico_Sintetico = "A";
    }
    if (ed.getDM_Relatorio ().equals ("MV1")) {
      DM_Quebra_Cliente = "S";
      DM_Mapa = "N";
    }

    // System.out.println ("geraAnalise_Gerencial_CRT - INTERNACIONAL >>>  " + ed.getDM_Relatorio ());

    if (DM_Mapa.equals ("N")) {
      sql = "  select Conhecimentos.oid_Unidade, Conhecimentos.oid_Unidade_Destino, Conhecimentos.oid_conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Modal, " +
          " Conhecimentos.oid_cidade, Conhecimentos.oid_cidade_destino, fat.oid_Pessoa_Pagador, Conhecimentos.oid_Pessoa_Destinatario, " +
          " Conhecimentos.nr_volumes_observacao as nr_volumes, fat.vl_faturar as vl_total_frete, '0.00' as vl_total_custo , " +
          " Conhecimentos.vl_Peso as nr_peso, Conhecimentos.vl_Peso_Cubado as nr_peso_cubado, Conhecimentos.VL_Nota_Fiscal ";
      sql += " FROM   Conhecimentos_Internacionais Conhecimentos, Conhecimentos_Faturamentos fat" +
          " WHERE  Conhecimentos.oid_conhecimento = fat.oid_conhecimento ";
    }
    if (DM_Quebra_Grupo_Economico.equals ("S")) {
      sql = "  select Conhecimentos.oid_Unidade, Conhecimentos.oid_Unidade_Destino, Conhecimentos.oid_conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Modal, " +
          " Conhecimentos.oid_cidade, Conhecimentos.oid_cidade_destino, fat.oid_Pessoa_Pagador, Conhecimentos.oid_Pessoa_Destinatario, " +
          " Conhecimentos.nr_volumes_observacao as nr_volumes, fat.vl_faturar as vl_total_frete, '0.00' as vl_total_custo , " +
          " Conhecimentos.vl_Peso as nr_peso, Conhecimentos.vl_Peso_Cubado as nr_peso_cubado, Conhecimentos.VL_Nota_Fiscal, " +
          " Clientes.OID_Grupo_Economico ";
      sql += " FROM   Conhecimentos_Internacionais Conhecimentos, Conhecimentos_Faturamentos fat, Clientes  " +
          " WHERE  Conhecimentos.oid_conhecimento = fat.oid_conhecimento AND " +
          " fat.OID_Pessoa_Pagador = Clientes.oid_cliente ";
    }

    if (DM_Mapa.equals ("S")) {
      sql = "  select Conhecimentos.oid_Unidade, Conhecimentos.oid_Unidade_Destino, Conhecimentos.oid_conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Modal, " +
          " Conhecimentos.oid_cidade, Conhecimentos.oid_cidade_destino, fat.oid_Pessoa_Pagador, Conhecimentos.oid_Pessoa_Destinatario, " +
          " Conhecimentos.nr_volumes_observacao as nr_volumes, fat.vl_faturar as vl_total_frete, '0.00' as vl_total_custo , " +
          " Conhecimentos.vl_Peso as nr_peso, Conhecimentos.vl_Peso_Cubado as nr_peso_cubado, Conhecimentos.VL_Nota_Fiscal, " +
          " Modal.DM_Tipo_Tabela_Frete ";
      sql += " FROM   Conhecimentos_Internacionais Conhecimentos, Conhecimentos_Faturamentos fat, Modal " +
          " WHERE Conhecimentos.oid_conhecimento = fat.oid_conhecimento AND " +
          " Conhecimentos.oid_modal = Modal.oid_Modal ";
    }
    if (DM_Analitico_Sintetico.equals ("A")) {
      sql = " select Conhecimentos.oid_Unidade, Conhecimentos.oid_Unidade_Destino, Conhecimentos.DT_Emissao, Modal.DM_Tipo_Tabela_Frete, Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, " +
          " Conhecimentos.nr_volumes_observacao as nr_volumes, Conhecimentos.VL_NOTA_FISCAL, Conhecimentos.dm_tipo_pagamento, duplicatas.nr_duplicata, " +
          " fat.vl_faturar as vl_total_frete, '0.00' as vl_custo_coleta, '0.00' as vl_custo_transferencia, '0.00' as vl_custo_entrega, " +
          " Conhecimentos.vl_seguro as vl_custo_seguro, '0.00' as vl_custo_imposto, '0.00' as vl_custo_outros, '0.00' as vl_total_custo, " +
          " '0000-00-00' as Dt_Entrega, '00:00' as Hr_Entrega , '0000-00-00' as Dt_Previsao_Entrega, '00:00' as Hr_Previsao_Entrega, " +
          " 'NONE' as nm_pessoa_entrega, conhecimentos.vl_peso as nr_peso, Conhecimentos.oid_pessoa, fat.oid_pessoa_pagador , Conhecimentos.oid_Modal, " +
          " Conhecimentos.oid_Pessoa_Destinatario,  conhecimentos.vl_peso_cubado as nr_peso_cubado, Unidades.CD_Unidade, Unidades.oid_Pessoa as oid_Pessoa_Unidade " +
          " from Conhecimentos_Internacionais Conhecimentos, Conhecimentos_Faturamentos fat, Unidades, Modal, Cidades Cidade_Origem, Cidades Cidade_Destino, " +
          " Regioes_Estados Regiao_Estado_Origem,  Regioes_Estados Regiao_Estado_Destino, Estados Estado_Origem, Estados Estado_Destino, duplicatas " +
          " where Conhecimentos.oid_conhecimento = fat.oid_conhecimento AND fat.oid_duplicata = duplicatas.oid_duplicata " +
          " AND Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
          " AND Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
          " AND Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          " AND Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
          " AND Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          " AND Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
          " AND Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado " +
          " AND Conhecimentos.oid_modal           = Modal.oid_modal ";
      //" AND Conhecimentos.VL_Total_Frete > 0" ;
    }

    sql += " AND (Conhecimentos.DM_Situacao = 'F' or Conhecimentos.DM_Situacao = 'L') ";
    //" AND Conhecimentos.VL_Total_Frete > 0 " ;


    if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
    }

    if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Modal = " + ed.getOID_Modal ();
    }

    if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
    }

    if (String.valueOf (ed.getOID_Cidade_Origem ()) != null && !String.valueOf (ed.getOID_Cidade_Origem ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Cidade = " + ed.getOID_Cidade_Origem ();
    }

    if (String.valueOf (ed.getOID_Cidade_Destino ()) != null && !String.valueOf (ed.getOID_Cidade_Destino ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino ();
    }

    if (String.valueOf (ed.getOID_Pessoa_Remetente ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa_Remetente () + "'";
    }

    if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }

    if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null &&
        !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
      sql += " and fat.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    }

//       if (String.valueOf(ed.getOID_Vendedor()) != null &&
//          !String.valueOf(ed.getOID_Vendedor()).equals("") &&
//          !String.valueOf(ed.getOID_Vendedor()).equals("null")){
//         sql += " and Conhecimentos.OID_Vendedor = '" + ed.getOID_Vendedor() + "'";
//       }


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

//       if (String.valueOf(ed.getDM_Frete()) != null &&
//       !String.valueOf(ed.getDM_Frete()).equals("null") &&
//       String.valueOf(ed.getDM_Frete()).equals("F")) {
//         sql += " and Conhecimentos.NR_Peso <= 7500 ";
//       }
//
//       if (String.valueOf(ed.getDM_Frete()) != null &&
//       !String.valueOf(ed.getDM_Frete()).equals("null") &&
//       String.valueOf(ed.getDM_Frete()).equals("C")) {
//         sql += " and Conhecimentos.NR_Peso > 7500 ";
//       }


    if (DM_Quebra_Cliente.equals ("S") && ed.getDM_Pessoa ().equals ("R")) {
      sql += " order by conhecimentos.oid_pessoa ";
    }
    if (DM_Quebra_Cliente.equals ("S") && ed.getDM_Pessoa ().equals ("D")) {
      sql += " order by conhecimentos.oid_pessoa_destinatario ";
    }
    if (DM_Quebra_Cliente.equals ("S") && ed.getDM_Pessoa ().equals ("P")) {
      sql += " order by fat.oid_pessoa_pagador ";
    }

    if (DM_Quebra_Cidade.equals ("S")) {
      sql += " order by conhecimentos.oid_cidade, conhecimentos.oid_cidade_destino ";
    }

    if (DM_Quebra_Pais.equals ("S")) {
      sql += " order by conhecimentos.oid_Unidade, conhecimentos.oid_Unidade_Destino  ";
    }

    if (DM_Quebra_Modal.equals ("S")) {
      sql += " order by conhecimentos.oid_modal ";
    }
    if (DM_Quebra_Grupo_Economico.equals ("S")) {
      sql += " order by clientes.oid_grupo_economico ";
    }

//      if (DM_Quebra_Vendedor.equals("S") ) {
//         sql += " order by conhecimentos.oid_Vendedor ";
//      }


    // System.out.println (" sql " + sql);

    GerencialED edVolta = new GerencialED ();

    try {

      //// // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      if (DM_Analitico_Sintetico.equals ("S")) {

        Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

        while (res.next ()) {
          this.inicioTransacao ();
          this.executasql = this.sql;

          OID_Pessoa_Lida = res.getString ("OID_Pessoa");
          if (ed.getDM_Pessoa ().equals ("D")) {
            OID_Pessoa_Lida = res.getString ("OID_Pessoa_Destinatario");
          }
          if (ed.getDM_Pessoa ().equals ("P")) {
            OID_Pessoa_Lida = res.getString ("OID_Pessoa_Pagador");
          }

          if (DM_Quebra_Cliente.equals ("S")) {
            if (OID_Pessoa.equals ("PRIMEIRO") || OID_Pessoa_Lida.equals (OID_Pessoa)) {
              OID_Pessoa = OID_Pessoa_Lida;
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Pessoa = OID_Pessoa_Lida;
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Pais.equals ("S")) {
            Unidades = Unidades.getByLocalizacao (res.getLong ("OID_Unidade"));
            NM_Origem = Unidades.getNM_Pais ();

            Unidades = Unidades.getByLocalizacao (res.getLong ("OID_Unidade_Destino"));
            NM_Destino = Unidades.getNM_Pais ();

            // System.out.println ("NM_Origem->" + NM_Origem);
            // System.out.println ("NM_Pais_Origem->" + NM_Pais_Origem);
            // System.out.println ("NM_Destino->" + NM_Destino);
            // System.out.println ("NM_Pais_Destino->" + NM_Pais_Destino);
            if (NM_Pais_Origem.equals (NM_Origem) && NM_Pais_Destino.equals (NM_Destino)) {
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              NM_Pais_Origem = NM_Origem;
              NM_Pais_Destino = NM_Destino;
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Unidade.equals ("S")) {

            if (OID_Unidade_Origem == 999999 || res.getLong ("OID_Unidade") == OID_Unidade_Origem || res.getLong ("OID_Unidade_Destino") == OID_Unidade_Destino) {
              OID_Unidade_Origem = res.getLong ("OID_Unidade");
              OID_Unidade_Destino = res.getLong ("OID_Unidade_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Unidade_Origem = res.getLong ("OID_Unidade");
              OID_Unidade_Destino = res.getLong ("OID_Unidade_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Cidade.equals ("S")) {

            if (OID_Cidade_Origem == 999999 || res.getLong ("OID_Cidade") == OID_Cidade_Origem || res.getLong ("OID_Cidade_Destino") == OID_Cidade_Destino) {

              OID_Cidade_Origem = res.getLong ("OID_Cidade");
              OID_Cidade_Destino = res.getLong ("OID_Cidade_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {

                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Cidade_Origem = res.getLong ("OID_Cidade");
              OID_Cidade_Destino = res.getLong ("OID_Cidade_Destino");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Modal.equals ("S")) {
            if (OID_Modal == 999999 || res.getInt ("OID_Modal") == OID_Modal) {
              OID_Modal = res.getInt ("OID_Modal");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Modal = res.getInt ("OID_Modal");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Grupo_Economico.equals ("S")) {
            if (OID_Grupo_Economico == 999999 || res.getInt ("OID_Grupo_Economico") == OID_Grupo_Economico) {
              OID_Grupo_Economico = res.getInt ("OID_Grupo_Economico");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                GerencialED edGerencial = new GerencialED ();
                inclui (edGerencial = carregaED (edGerencial));
              }
              OID_Grupo_Economico = res.getInt ("OID_Grupo_Economico");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }

          if (DM_Quebra_Vendedor.equals ("S")) {

            if (OID_Pessoa.equals ("PRIMEIRO") || res.getString ("OID_Vendedor").equals (OID_Pessoa)) {
              OID_Pessoa = res.getString ("OID_Vendedor");
              edVolta = this.totaliza_conhecimento (edVolta , res , "acumula");
            }
            else {
              if (vl_total_frete > 0) {
                inclui (edVolta = carregaED (ed));
              }
              OID_Pessoa = res.getString ("OID_Vendedor");
              edVolta = this.totaliza_conhecimento (edVolta , res , "zera");
            }
          }
          this.fimTransacao (true);

        }
        if (vl_total_frete > 0) {
          inclui (edVolta = carregaED (edVolta));
        }
        ed.setNR_Sort (Nr_Sort);
        ed.setTVL_Total_Frete (tvl_total_frete);
        ed.setTVL_Margem (tvl_margem);
        ed.setTVL_Notas_Fiscais (tvl_notas_fiscais);
        ed.setTNR_Despachos (tnr_despachos);
        ed.setTNR_Peso (tnr_peso);
        ed.setTNR_Peso_Cubado (tnr_peso_cubado);
        ed.setTNR_Volumes (tnr_volumes);
        this.fimTransacao (true);

        b = this.imprime_Gerencial_Conhecimento (ed);

      }

      if (DM_Analitico_Sintetico.equals ("A")) {
        GerencialRL conRL = new GerencialRL ();
        b = conRL.geraRelConhecCusto (res , ed);
      }
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] geraAnalise_Gerencial_Receita_Despesa (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    String dt_inicial = "";
    String dt_final = "";
    ResultSet res = null;
    ResultSet res2 = null;
    DM_Consulta_Conta = "S";

    try {

      // System.out.println (" BD 0");

      Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

      ///------------------------------------------------------------///
      ///RECEITA COM CTO
      NM_Campo = "1-Receita";
      NM_Campo2 = "Cto Frete";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Modal.oid_Modal, Modal.oid_Conta " +
          " From  Modal " +
          " Order By  Modal.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());

        // System.out.println (" dt_inicial ->>" + dt_inicial);
        // System.out.println (" dt_final ->>" + dt_final);
        // System.out.println (" ed.getDM_Criterio_Receita() ->>" + ed.getDM_Criterio_Receita ());

        valor_mes1 = this.total_Receita_Conhecimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getLong ("oid_Modal") , ed.getDM_Criterio_Receita (),"");

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Receita_Conhecimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getLong ("oid_Modal") , ed.getDM_Criterio_Receita (),"");
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Receita_Conhecimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , res.getLong ("oid_Modal") , ed.getDM_Criterio_Receita (),"");
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }
      ///RECEITA COM ORDEM SERVICO
      NM_Campo = "1-Receita";
      NM_Campo2 = "Ordem Servico";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " SELECT Contas.oid_Conta " +
          " From  Contas " +
          " WHERE Contas.DM_Despesa ='R' ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());

        // System.out.println (" dt_inicial ->>" + dt_inicial);
        // System.out.println (" dt_final ->>" + dt_final);

        valor_mes1 = this.total_Receita_Ordem_Servico (dt_inicial , dt_final , ed.getOID_Empresa () , res.getLong ("oid_conta"));

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Receita_Ordem_Servico (dt_inicial , dt_final , ed.getOID_Empresa () , res.getLong ("oid_conta"));
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Receita_Ordem_Servico (dt_inicial , dt_final , ed.getOID_Empresa () , res.getLong ("oid_conta"));
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///RECEITA COM ORDEM DE FRETE
      NM_Campo = "1-Receita";
      NM_Campo2 = "Ordem Frete";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      OID_Conta = parametro_FixoED.getOID_Conta_Frete_Terceiros ();

      dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
      dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      valor_mes1 = this.total_Receita_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());

      if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
        valor_mes2 = this.total_Receita_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());
      }
      if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
        valor_mes3 = this.total_Receita_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());
      }
      if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
        this.totaliza_receita_despesa (ed);
      }

      ///------------------------------------------------------------///
      ///DESPESA COM ORDEM DE FRETE
      NM_Campo = "2-Despesa";
      NM_Campo2 = "Ordem Frete";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      OID_Conta = parametro_FixoED.getOID_Conta_Ordem_Frete ();

      dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
      dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      valor_mes1 = this.total_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , "Total_Ordem");

      if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
        valor_mes2 = this.total_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , "Total_Ordem");
      }
      if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
        valor_mes3 = this.total_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , "Total_Ordem");
      }
      if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
        this.totaliza_receita_despesa (ed);
      }

      ///------------------------------------------------------------///
      ///DESPESA COM PEDAGIO
      NM_Campo = "2-Despesa";
      NM_Campo2 = "Ordem Frete";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      OID_Conta = parametro_FixoED.getOID_Conta_Debito_Pedagio ();

      dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
      dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      valor_mes1 = this.total_Despesa_Vale_Pedagio_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());

      if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
        valor_mes2 = this.total_Despesa_Vale_Pedagio_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());
      }
      if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
        valor_mes3 = this.total_Despesa_Vale_Pedagio_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());
      }
      if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
        this.totaliza_receita_despesa (ed);
      }

      ///------------------------------------------------------------///
      ///DESPESA COM DESCONTO EM CTO
      NM_Campo = "2-Despesa";
      NM_Campo2 = "Desc.Ctos";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      OID_Conta = parametro_FixoED.getOID_Conta_Desconto_Frete ();

      dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
      dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      valor_mes1 = this.total_Despesa_Desconto_Conhecimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal ());

      if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
        valor_mes2 = this.total_Despesa_Desconto_Conhecimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal ());
      }
      if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
        valor_mes3 = this.total_Despesa_Desconto_Conhecimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal ());
      }
      if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
        this.totaliza_receita_despesa (ed);
      }

      ///------------------------------------------------------------///
      ///DESPESA COM CONTA CORRENTE

      NM_Campo = "2-Despesa";
      NM_Campo2 = "Cta Corrente";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Contas.oid_Conta " +
          " From  Contas, Movimentos_Contas_Correntes " +
          " WHERE Contas.DM_Despesa = 'S' " +
          " AND   Contas.oid_Conta = Movimentos_Contas_Correntes.oid_Conta " +
          " Group By  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Conta_Corrente (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);

        // System.out.println (" total_Despesa_Conta_Corrente= " + valor_mes1);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Conta_Corrente (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Conta_Corrente (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///INVESTIMENTO

      NM_Campo = "3-Investimento";
      NM_Campo2 = "Compromissos";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Contas.oid_Conta " +
          " From  Contas " +
          " WHERE Contas.DM_Despesa = 'I' " +
          " Order By  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Investimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Investimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Investimento (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///DESPESAS COMPROMISSOS

      NM_Campo = "2-Despesa";
      NM_Campo2 = "Compromissos";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Contas.oid_Conta " +
          " From  Contas " +
          " WHERE Contas.DM_Despesa = 'S' " +
          " Order By  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa (),0);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa (),0);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa (),0);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      //DESPESA LIQUIDACAO COMPROMISSO
      NM_Campo = "2-Despesa";
      NM_Campo2 = "Liq.Coprom.";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      OID_Conta = parametro_FixoED.getOID_Conta_Movimento_Compromisso ();

      dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
      dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      valor_mes1 = this.total_Despesa_Liquidacao_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());

      if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
        valor_mes2 = this.total_Despesa_Liquidacao_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());
      }
      if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
        valor_mes3 = this.total_Despesa_Liquidacao_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade ());
      }
      if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
        this.totaliza_receita_despesa (ed);
      }

      ///------------------------------------------------------------///
      ///DESPESAS FORNECEDORES

      NM_Campo = "2-Despesa";
      NM_Campo2 = "Fornecedores";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Fornecedores.oid_Conta  " +
          " From  Fornecedores, Contas " +
          " WHERE Fornecedores.oid_Conta = Contas.oid_Conta " +
          "   AND Contas.DM_Despesa = 'D' " +
          " Group By  Fornecedores.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Fornecedor (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Fornecedor (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Fornecedor (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo ());
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///DESPESAS DIVERSAS

      NM_Campo = "2-Despesa";
      NM_Campo2 = "Despesas";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Contas.oid_Conta " +
          " From  Contas " +
          " WHERE Contas.DM_Despesa = 'D' " +
          " Order By  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Diversas (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Diversas (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Diversas (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///DESPESAS ACERTO/FROTA
      NM_Campo = "2-Despesa";
      NM_Campo2 = "Acerto Viagem";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Contas.oid_Conta " +
          " From  Contas, Tipos_Servicos " +
          " WHERE Contas.oid_Conta = Tipos_Servicos.oid_Conta " +
          " AND   Tipos_Servicos.DM_Tipo_Despesa <> 'C' " +
          " Group By  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());

        valor_mes1 = this.total_Despesa_Manutencao (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Manutencao (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Manutencao (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo ());
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///DESPESAS COMBUSTIVEL
      NM_Campo = "2-Despesa";
      NM_Campo2 = "Combusteis";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Contas.oid_Conta " +
          " From  Contas, Tipos_Servicos " +
          " WHERE Contas.oid_Conta = Tipos_Servicos.oid_Conta " +
          " AND   Tipos_Servicos.DM_Tipo_Despesa = 'C' " +
          " Group By  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
 
      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Combustivel (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Combustivel (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Combustivel (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Criterio_Despesa ());
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///DESPESA COMISSAO MOTORISTA
      NM_Campo = "2-Despesa";
      NM_Campo2 = "Acerto Viagem";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }
      OID_Conta = 275;

      dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
      dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      valor_mes1 = this.total_Despesa_Comissao_Motorista (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getDM_Criterio_Despesa ());

      if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
        valor_mes2 = this.total_Despesa_Comissao_Motorista (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getDM_Criterio_Despesa ());
      }
      if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
        dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
        valor_mes3 = this.total_Despesa_Comissao_Motorista (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getDM_Criterio_Despesa ());
      }
      if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
        this.totaliza_receita_despesa (ed);
      }

      ///------------------------------------------------------------///
      ///DESPESA COM CONTAS A RECEBER

      NM_Campo = "2-Despesa";
      NM_Campo2 = "Cta.Receber";
      if ("N".equals (ed.getDM_Consolida ())) {
        NM_Campo2 = "";
      }

      sql = " select Contas.oid_Conta " +
          " From  Contas, Movimentos_Duplicatas " +
          " WHERE Contas.oid_Conta = Movimentos_Duplicatas.oid_Conta " +
          " Group By  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Cobranca (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Cobranca (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Cobranca (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      sql = " SELECT Contas.oid_Conta, Contas.DM_Despesa " +
          " FROM   Contas " +
          " WHERE  Contas.vl_orcado >0 ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      NM_Campo2 = "Orcamento";

      while (res.next ()) {
        sql = " SELECT oid_Conta FROM Gerencial " +
            " WHERE  Gerencial.oid_Conta = " + res.getLong ("oid_conta");
        res2 = this.executasql.executarConsulta (sql.toString ());
        if (!res2.next ()) {
          OID_Conta = res.getLong ("oid_conta");
          NM_Campo = "2-Despesa";
          if ("R".equals (res.getString ("DM_Despesa"))) {
            NM_Campo = "1-Receita";
          }
          if ("I".equals (res.getString ("DM_Despesa"))) {
            NM_Campo = "3-Investimento";
          }
          this.totaliza_receita_despesa (ed);
        }
      }
      
      // System.out.println (" FIM============= ");

      b = this.imprime_Gerencial_Receita_Despesa (ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] geraAnalise_Gerencial_Demonstrativo_Resultado (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    String dt_inicial = "";
    String dt_final = "";
    ResultSet res = null;
    ResultSet res2 = null;
    DM_Consulta_Conta = "S";

    try {

      // System.out.println (" geraAnalise_Gerencial_Demonstrativo_Resultado 0");

      Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

      ///RECEITA COM ORDEM SERVICO

      sql = " SELECT Contas.oid_Conta, Grupos_Contas.DM_DRE " +
          " From   Contas, Grupos_Contas " +
          " WHERE  Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta " +
          " AND    Grupos_Contas.DM_DRE ='010' ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        NM_Campo = res.getString ("DM_DRE"); // "1-RECEITA BRUTA";

        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());

        // System.out.println (" dt_inicial ->>" + dt_inicial);
        // System.out.println (" dt_final ->>" + dt_final);

        valor_mes1 = this.total_Receita_Ordem_Servico (dt_inicial , dt_final , ed.getOID_Empresa () , res.getLong ("oid_conta"));

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Receita_Ordem_Servico (dt_inicial , dt_final , ed.getOID_Empresa () , res.getLong ("oid_conta"));
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Receita_Ordem_Servico (dt_inicial , dt_final , ed.getOID_Empresa () , res.getLong ("oid_conta"));
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }

      }

      ///------------------------------------------------------------///
      ///IMPOSTOS DIRETOS - 020

      sql = " SELECT Contas.oid_Conta, Grupos_Contas.DM_DRE " +
          " From   Contas, Grupos_Contas " +
          " WHERE  Grupos_Contas.DM_DRE ='020' " +
          " AND    Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta " +
          " ORDER BY  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        NM_Campo = res.getString ("DM_DRE");

        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }
      }

      ///------------------------------------------------------------///
      ///CUSTOS VARIAVEIS - 030

      sql = " SELECT Contas.oid_Conta, Grupos_Contas.DM_DRE " +
          " From   Contas, Grupos_Contas " +
          " WHERE  Grupos_Contas.DM_DRE ='030' " +
          " AND    Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta " +
          " ORDER BY  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        NM_Campo = res.getString ("DM_DRE");

        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }
      }

      ///------------------------------------------------------------///
      ///DESPESAS - 040

      sql = " SELECT Contas.oid_Conta, Grupos_Contas.DM_DRE " +
          " From   Contas, Grupos_Contas " +
          " WHERE  Grupos_Contas.DM_DRE ='040' " +
          " AND    Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta " +
          " ORDER BY  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        NM_Campo = res.getString ("DM_DRE");

        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }
      }

      ///------------------------------------------------------------///
      ///DESPESA COM ORDEM DE FRETE
      ///DESPESAS - 040

      sql = " SELECT Contas.oid_Conta, Grupos_Contas.DM_DRE " +
          " From   Contas, Grupos_Contas " +
          " WHERE  Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta " +
          " AND    Contas.oid_Conta =" + parametro_FixoED.getOID_Conta_Ordem_Frete ();

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      if (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        NM_Campo = res.getString ("DM_DRE"); //

        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = (total_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , "Total_Adiantamento") +
                      total_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , "Total_Saldo"));

        // System.out.println (" valor_mes1 ORDEM FRETE ->>" + valor_mes1);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , "Total_Adiantamento_Saldo");
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Ordem_Frete (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , "Total_Adiantamento_Saldo");
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }
      }

      ///------------------------------------------------------------///
      ///INVESTIMENTO - 050

      sql = " SELECT Contas.oid_Conta, Grupos_Contas.DM_DRE " +
          " From   Contas, Grupos_Contas " +
          " WHERE  Grupos_Contas.DM_DRE ='050' " +
          " AND    Contas.oid_Grupo_Conta = Grupos_Contas.oid_Grupo_Conta " +
          " ORDER BY  Contas.OID_Conta ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;
      while (res.next ()) {
        OID_Conta = res.getLong ("oid_conta");
        NM_Campo = res.getString ("DM_DRE");

        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
        valor_mes1 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Despesa_Compromisso (dt_inicial , dt_final , ed.getOID_Empresa () , ed.getOID_Unidade () , OID_Conta , ed.getDM_Periodo (),0);
        }
        if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
          this.totaliza_receita_despesa (ed);
        }
      }

      // System.out.println (" imprime_Gerencial_Demonstrativo_Cobranca ");

      b = this.imprime_Gerencial_Demonstrativo_Resultado (ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  private double total_Receita_Conhecimento (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Modal , String dm_Periodo, String oid_Pessoa_Pagador) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    tt_total_frete = 0;
    tt_qtde_embarques = 0;
    tt_peso = 0;
    tt_desconto = 0;

    try {

      // System.out.println (" dm_Periodo=" + dm_Periodo);

      sql = " select SUM   (Conhecimentos.VL_Total_Frete)   as tt_total_frete, " +
          "          SUM   (Conhecimentos.NR_Peso)          as tt_peso, " +
          "          SUM   (Conhecimentos.VL_Desconto)      as tt_desconto, " +
          "          COUNT (Conhecimentos.oid_Conhecimento) as tt_qtde_embarques " +
          " From  Conhecimentos, Unidades " +
          " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
          " AND Conhecimentos.DM_Impresso  = 'S' " +
          " AND Unidades.DM_Situacao       <> 'I' " +
          " AND Conhecimentos.VL_Total_Frete >  0  " +
          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
          " AND Conhecimentos.DM_Situacao   <> 'C' ";


      if (oid_Modal > 0) {
        sql += " and Conhecimentos.oid_Modal = " + oid_Modal;
      }
      if (oid_Pessoa_Pagador !=null && oid_Pessoa_Pagador.length()>4) {
        sql += " and Conhecimentos.OID_Pessoa_Pagador ='" + oid_Pessoa_Pagador + "'";
      }

      if ("P".equals (dm_Periodo)) { //pagamento{
    	  sql= " SELECT SUM (vl_credito) as tt_total_frete FROM  movimentos_duplicatas, duplicatas, unidades " +
    	  " WHERE  movimentos_duplicatas.oid_duplicata = duplicatas.oid_duplicata " +
    	  " AND    duplicatas.oid_unidade = unidades.oid_unidade " +
    	  " AND    movimentos_duplicatas.dm_principal='S' " +
    	  " AND    movimentos_duplicatas.dt_movimento >= '" + data_inicial + "'" +
          " AND    movimentos_duplicatas.dt_movimento <= '" + data_final + "'";
      }else {
          if (!"E".equals (dm_Periodo)) { //entrada
              sql += " AND   Conhecimentos.DT_Coleta >= '" + data_inicial + "'" +
              	      " AND   Conhecimentos.DT_Coleta <= '" + data_final + "'";
            }
            else { //emissao
              sql += " AND   Conhecimentos.DT_Emissao >= '" + data_inicial + "'" +
                     " AND   Conhecimentos.DT_Emissao <= '" + data_final + "'";
            }
      }

      if (oid_Empresa > 0) {
          sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
          sql += " and Unidades.OID_Unidade = " + oid_Unidade;
      }

      
      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("tt_total_frete");
        tt_total_frete = res.getDouble ("tt_total_frete");
        if (!"P".equals (dm_Periodo)) { //pagamento{
            tt_qtde_embarques = res.getDouble ("tt_qtde_embarques");
            tt_peso = res.getDouble ("tt_peso");
            tt_desconto = res.getDouble ("tt_desconto");
        }

      }

      // System.out.println (" total=== " + total);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  private double total_Conhecimento_Faturado (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Modal , String dm_Periodo, String oid_Pessoa_Pagador) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;

    try {

      // System.out.println (" BD 0");

      sql = " select SUM   (Conhecimentos.VL_Total_Frete)   as tt_total_frete " +
          " From  Conhecimentos, Unidades, Origens_Duplicatas, Duplicatas " +
          " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
          " AND Conhecimentos.oid_Conhecimento = Origens_Duplicatas.oid_Conhecimento " +
          " AND Duplicatas.oid_Duplicata = Origens_Duplicatas.oid_Duplicata " +
          " AND Conhecimentos.DM_Impresso  = 'S' " +
          " AND Unidades.DM_Situacao       <> 'I' " +
          " AND Conhecimentos.VL_Total_Frete >  0  " +
          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
          " AND Origens_Duplicatas.DM_Situacao = 'A' " +
          " AND Conhecimentos.DM_Situacao   <> 'C' ";
      if ("E".equals(dm_Periodo)){
        sql += " AND   Duplicatas.DT_Emissao >= '" + data_inicial + "'" +
            " AND   Duplicatas.DT_Emissao <= '" + data_final + "'";
      }
      if ("V".equals(dm_Periodo)){
        sql += " AND   Duplicatas.DT_Vencimento >= '" + data_inicial + "'" +
            " AND   Duplicatas.DT_Vencimento <= '" + data_final + "'";
      }
      if (oid_Modal > 0) {
        sql += " and Conhecimentos.oid_Modal = " + oid_Modal;
      }
      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Unidade;
      }
      if (oid_Pessoa_Pagador !=null && oid_Pessoa_Pagador.length()>4) {
        sql += " and Conhecimentos.OID_Pessoa_Pagador ='" + oid_Pessoa_Pagador + "'";
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("tt_total_frete");
      }

      // System.out.println (" total=== " + total);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  private double total_Impostos_Conhecimento (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , String DM_Imposto) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    tt_icms = 0;
    tt_issqn = 0;

    try {

      // System.out.println (" BD 0");

      sql = " select SUM   (Conhecimentos.VL_ICMS)       as tt_icms, " +
          "          SUM   (Conhecimentos.vl_issqn)      as tt_issqn " +
          " From  Conhecimentos, Unidades " +
          " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
          " AND Unidades.DM_Situacao       <> 'I' " +
          " AND Conhecimentos.DM_Impresso  = 'S' " +
          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
          " AND Conhecimentos.VL_Total_Frete >  0  " +
          " AND Conhecimentos.DM_Situacao   <> 'C' " +
          " AND Conhecimentos.DT_Emissao >= '" + data_inicial + "'" +
          " AND Conhecimentos.DT_Emissao <= '" + data_final + "'";

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        if ("ICMS".equals (DM_Imposto)) {
          total = res.getDouble ("tt_icms");
        }
        if ("ISSQN".equals (DM_Imposto)) {
          total = res.getDouble ("tt_issqn");
        }
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  private double total_Despesa_Desconto_Conhecimento (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Modal) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (Conhecimentos.VL_Desconto) as VL_Total " +
          " From  Conhecimentos, Unidades " +
          " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
          " AND Unidades.DM_Situacao       <> 'I' " +
          " AND Conhecimentos.DM_Impresso  = 'S' " +
          " AND Conhecimentos.VL_Total_Frete >  0  " +
          " AND Conhecimentos.DM_Situacao   <> 'C' " +
          " AND Conhecimentos.DT_Emissao >= '" + data_inicial + "'" +
          " AND Conhecimentos.DT_Emissao <= '" + data_final + "'";

      if (oid_Modal > 0) {
        sql += " and Conhecimentos.oid_Modal = " + oid_Modal;
      }
      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Empresa;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  private double total_Receita_Ordem_Frete (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (Ordens_Fretes.VL_Ordem_Frete) as VL_Total " +
          " From  Ordens_Fretes, Unidades " +
          " WHERE Ordens_Fretes.oid_Unidade = Unidades.oid_Unidade " +
          " AND Unidades.DM_Situacao   <> 'I' " +
          " AND Ordens_Fretes.DM_Frete  = 'T' " +
          " AND Ordens_Fretes.VL_Ordem_Frete >  0  " +
          " AND Ordens_Fretes.DT_Emissao >= '" + data_inicial + "'" +
          " AND Ordens_Fretes.DT_Emissao <= '" + data_final + "'";

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Empresa;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Ordem_Frete");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Ordem_Frete");
    }
    return total;
  }

  private double total_Despesa_Vale_Pedagio_Ordem_Frete (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (Ordens_Fretes.vl_vale_pedagio) as VL_Total " +
          " From  Ordens_Fretes, Unidades " +
          " WHERE Ordens_Fretes.oid_Unidade = Unidades.oid_Unidade " +
          " AND Unidades.DM_Situacao   <> 'I' " +
          " AND Ordens_Fretes.DM_Frete  = 'P' " +
          " AND Ordens_Fretes.DM_Impresso = 'S' " +
          " AND Ordens_Fretes.VL_Ordem_Frete >  0  " +
          " AND Ordens_Fretes.DT_Emissao >= '" + data_inicial + "'" +
          " AND Ordens_Fretes.DT_Emissao <= '" + data_final + "'";

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Empresa;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Ordem_Frete");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Ordem_Frete");
    }
    return total;
  }

  private double total_Receita_Ordem_Servico (String data_inicial , String data_final , long oid_Empresa , long oid_Conta) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " SELECT SUM (vl_lancamento) as vl_lancamento " +
          " FROM   Processos, Movimentos_Processos " +
          " WHERE  Movimentos_Processos.dm_debito_credito ='C' " +
          " AND    Processos.dm_situacao <> 'C' " +
          " AND    Processos.oid_processo = Movimentos_Processos.oid_processo " +
          " AND    Processos.dt_previsao >= '" + data_inicial + "'" +
          " AND    Processos.dt_previsao <= '" + data_final + "'" +
          " AND    Processos.oid_Conta = " + oid_Conta;

      if (oid_Empresa > 0) {
        sql += " and Processos.oid_Empresa = " + oid_Empresa;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("vl_lancamento");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  private double total_Ordem_Frete (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , String DM_Total) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (Ordens_Fretes.VL_Ordem_Frete)   as VL_Total, " +
          "          SUM (Ordens_Fretes.VL_Saldo)         as VL_Total_Saldo, " +
          "          SUM (Ordens_Fretes.vl_adiantamento1) as VL_Total_vl_adiantamento1, " +
          "          SUM (Ordens_Fretes.vl_adiantamento2) as VL_Total_vl_adiantamento2, " +
          "          SUM (Ordens_Fretes.VL_Pedagio)       as VL_Total_Pedagio, " +
          "          SUM (Ordens_Fretes.VL_Outros)        as VL_Total_Outros, " +
          "          SUM (Ordens_Fretes.vl_Vale_Pedagio)  as VL_Total_Vale_Pedagio, " +
          "          SUM (Ordens_Fretes.vl_Descontos)     as VL_Total_Descontos  " +
          " From  Ordens_Fretes, Unidades " +
          " WHERE Ordens_Fretes.oid_Unidade = Unidades.oid_Unidade " +
          " AND Unidades.DM_Situacao     <> 'I' " +
          " AND Ordens_Fretes.DM_Impresso = 'S' " +
          " AND Ordens_Fretes.VL_Ordem_Frete >  0  " +
          " AND Ordens_Fretes.DT_Emissao >= '" + data_inicial + "'" +
          " AND Ordens_Fretes.DT_Emissao <= '" + data_final + "'";

      if ("Total_Adiantamento".equals (DM_Total)) {
        sql += " AND Ordens_Fretes.DM_Frete  = 'A' ";
      }
      else {
        sql += " AND Ordens_Fretes.DM_Frete  = 'P' ";
      }

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Empresa;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
        if ("Total_Liquido".equals (DM_Total)) {
          total = res.getDouble ("VL_Total") + res.getDouble ("VL_Total_Pedagio") + res.getDouble ("VL_Total_Outros") + res.getDouble ("VL_Total_Vale_Pedagio") - res.getDouble ("VL_Total_Descontos");
        }
        if ("Total_Saldo".equals (DM_Total)) {
          total = res.getDouble ("VL_Total_Saldo");
        }
        if ("Total_Adiantamento".equals (DM_Total)) {
          total = res.getDouble ("VL_Total_vl_Adiantamento1") + res.getDouble ("VL_Total_vl_Adiantamento2");
        }
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Ordem_Frete");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Ordem_Frete");
    }

    // System.out.println (" total_Ordem_Frete ->>" + total);

    return total;
  }

  private double total_Despesa_Conta_Corrente (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " SELECT SUM (Movimentos_Contas_Correntes.VL_Lancamento) as VL_Total   " +
          " FROM Movimentos_Contas_Correntes, Contas_Correntes " +
          " WHERE Movimentos_Contas_Correntes.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
          " AND Movimentos_Contas_Correntes.DM_Debito_Credito = 'D' " +
          " AND Movimentos_Contas_Correntes.VL_Lancamento >  0  " +
          " AND Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + data_inicial + "'" +
          " AND Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + data_final + "'" +
          " AND Movimentos_Contas_Correntes.oid_Conta = " + oid_Conta;

      if (oid_Empresa > 0) {
        sql += " and Contas_Correntes.oid_Empresa = " + oid_Empresa;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  private double total_Despesa_Manutencao (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta , String dm_Periodo) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (movimentos_ordens_servicos.VL_Movimento) as VL_Total " +
          " FROM  movimentos_ordens_servicos, Unidades, Ordens_Servicos, Acertos, Tipos_Servicos " +
          " WHere movimentos_ordens_servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
          " AND   Ordens_Servicos.oid_Ordem_Servico = Acertos.oid_Ordem_Servico " +
          " AND   Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade " +
          " AND   movimentos_ordens_servicos.oid_tipo_Servico = Tipos_Servicos.oid_Tipo_Servico  " +
          " AND   movimentos_ordens_servicos.VL_Movimento > 0  " +
          " AND   movimentos_ordens_servicos.DM_FATURADO_PAGO ='P' " +
          " AND   Tipos_Servicos.DM_Tipo_Despesa <> 'C' " + ///
          " AND   Unidades.DM_Situacao       <> 'I' " +
          " AND   Tipos_Servicos.oid_Conta = " + oid_Conta;
      if ("M".equals (dm_Periodo)) { //M=emissao
        sql += " AND   Acertos.DT_Saida >= '" + data_inicial + "'" +
            " AND   Acertos.DT_Chegada <= '" + data_final + "'";
      }
      else { //E=Entrada
        sql += " AND   Acertos.Dt_Emissao >= '" + data_inicial + "'" +
            " AND   Acertos.Dt_Emissao <= '" + data_final + "'";
      }

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Ordens_Servicos.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("total_Despesa_Manutencao");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Despesa_Manutencao");
    }
    return total;
  }

  private double total_Despesa_Combustivel (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta , String dm_Periodo) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (movimentos_ordens_servicos.VL_Movimento) as VL_Total " +
          " FROM  movimentos_ordens_servicos, Unidades, Ordens_Servicos, Acertos, Tipos_Servicos " +
          " WHere movimentos_ordens_servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
          " AND   Ordens_Servicos.oid_Ordem_Servico = Acertos.oid_Ordem_Servico " +
          " AND   Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade " +
          " AND   movimentos_ordens_servicos.oid_tipo_Servico = Tipos_Servicos.oid_Tipo_Servico  " +
          " AND   movimentos_ordens_servicos.VL_Movimento > 0  " +
          //" AND   movimentos_ordens_servicos.DM_FATURADO_PAGO ='P' " +
          " AND   Unidades.DM_Situacao       <> 'I' " +
          " AND   Tipos_Servicos.DM_Tipo_Despesa = 'C' " + ///
          " AND   Tipos_Servicos.oid_Conta = " + oid_Conta;

      sql = "SELECT SUM (movimentos_ordens_servicos.VL_Movimento) as VL_Total	" +
          " FROM Ordens_Servicos, Unidades, Veiculos, Acertos, Modelos_Veiculos, Tipos_Servicos " +
          " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
          "  AND Acertos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
          "  AND Veiculos.oid_Modelo_Veiculo = Modelos_Veiculos.oid_Modelo_Veiculo " +
          "  AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo " +
          "  AND   movimentos_ordens_servicos.oid_tipo_Servico = Tipos_Servicos.oid_Tipo_Servico  " +
          "  AND   Tipos_Servicos.DM_Tipo_Despesa = 'C' " + ///
          "  AND   Tipos_Servicos.oid_Conta = " + oid_Conta +
          "  AND   Unidades.DM_Situacao       <> 'I' ";
          //"  AND Acertos.NR_Kilometragem_Chegada > 0 ";


	sql = "  SELECT SUM (movimentos_ordens_servicos.VL_Movimento) as VL_Total " +
	 " FROM Ordens_Servicos, Unidades, Acertos, Tipos_Servicos, movimentos_ordens_servicos " +
	 " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
	 " AND Acertos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
	 " AND   movimentos_ordens_servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
	 " AND   movimentos_ordens_servicos.oid_tipo_Servico = Tipos_Servicos.oid_Tipo_Servico " +
	 " AND   Tipos_Servicos.DM_Tipo_Despesa = 'C' " +
	 " AND   Tipos_Servicos.oid_Conta = " + oid_Conta +
	 " AND   Unidades.DM_Situacao <> 'I' " +
	 " AND   Acertos.NR_Kilometragem_Chegada > 0 ";

      // System.out.println("geraRelAcerto_Veiculos 2");
      dm_Periodo="M";

      if ("M".equals (dm_Periodo)) { //M=emissao
        sql += " AND   Acertos.DT_Saida >= '" + data_inicial + "'" +
            " AND   Acertos.DT_Chegada <= '" + data_final + "'";
      }
      else { //E=Entrada
        sql += " AND   Acertos.Dt_Emissao >= '" + data_inicial + "'" +
            " AND   Acertos.Dt_Emissao <= '" + data_final + "'";
      }

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Ordens_Servicos.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }

      // System.out.println ("total_Despesa_Combustivel= " + total);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("total_Despesa_Manutencao");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Despesa_Manutencao");
    }
    return total;
  }

  private double total_Despesa_Comissao_Motorista (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , String dm_Periodo) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select sum (VL_Comissao) as VL_Total " +
          " FROM   Acertos, Ordens_Servicos, Unidades " +
          " WHere Ordens_Servicos.oid_Ordem_Servico = Acertos.oid_Ordem_Servico " +
          " AND   Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Unidades.DM_Situacao       <> 'I' ";

      if ("M".equals (dm_Periodo)) { //M=emissao
        sql += " AND   Acertos.DT_Saida >= '" + data_inicial + "'" +
            " AND   Acertos.DT_Chegada <= '" + data_final + "'";
      }
      else { //E=Entrada
        sql += " AND   Acertos.Dt_Emissao >= '" + data_inicial + "'" +
            " AND   Acertos.Dt_Emissao <= '" + data_final + "'";
      }

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }
      if (oid_Unidade > 0) {
        sql += " and Ordens_Servicos.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("total_Despesa_Comissao_Motorista");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Despesa_Comissao_Motorista");
    }
    return total;
  }

  private double total_Despesa_Liquidacao_Compromisso (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM(Movimentos_Compromissos.VL_Multa_Pagamento) as VL_Total , SUM(Movimentos_Compromissos.VL_Juros_Pagamento) as VL_Total2, SUM (Movimentos_Compromissos.VL_Outras_Despesas) as VL_Total3 " +
          " FROM  Compromissos, Unidades, Movimentos_Compromissos  " +
          " WHere Compromissos.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Compromissos.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso " +
          " AND   Unidades.DM_Situacao       <> 'I' " +
          " AND   Movimentos_Compromissos.VL_Pagamento > 0  " +
          " AND   Movimentos_Compromissos.dt_pagamento >= '" + data_inicial + "'" +
          " AND   Movimentos_Compromissos.dt_pagamento <= '" + data_final + "'";

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }

      if (oid_Unidade > 0) {
        sql += " and Compromissos.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total") + res.getDouble ("VL_Total2") + res.getDouble ("VL_Total3");
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  private double total_Investimento (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta , String dm_Periodo) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (Compromissos.VL_Compromisso) as VL_Total " +
          " FROM  Compromissos, Unidades " +
          " WHere Compromissos.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Unidades.DM_Situacao       <> 'I' " +
          " AND   Compromissos.VL_Compromisso > 0  " +
          " AND   compromissos.dt_liberado is not null " +
          " AND   compromissos.oid_compromisso_vincula is null " +
          " AND   compromissos.oid_compromisso_parcela is null " +
          " AND   Compromissos.oid_Conta = " + oid_Conta;
      if ("M".equals (dm_Periodo)) {
        sql += " AND   Compromissos.dt_emissao >= '" + data_inicial + "'" +
            " AND   Compromissos.dt_emissao <= '" + data_final + "'";
      }
      else {
        sql += " AND   Compromissos.dt_entrada >= '" + data_inicial + "'" +
            " AND   Compromissos.dt_entrada <= '" + data_final + "'";
      }
      if ("P".equals (dm_Periodo)) {
    	  sql =" SELECT SUM (vl_pagamento) as tt_pg, sum (vl_multa_pagamento) as tt_multa ,sum (vl_juros_pagamento) as tt_juros, sum (vl_desconto) as tt_desc, sum (vl_outras_despesas) as tt_outros, sum (vl_estorno) as tt_estorno " +
	    	  " FROM  movimentos_compromissos, Compromissos, Unidades  " +
	    	  " WHERE movimentos_compromissos.oid_compromisso = compromissos.oid_compromisso " +
          	  " AND   compromissos.oid_Unidade = Unidades.oid_Unidade " +
          	  " AND   Unidades.DM_Situacao       <> 'I' " +
          	  " AND   Compromissos.VL_Compromisso > 0  " +
          	  " AND   compromissos.dt_liberado is not null " +
          	  " AND   compromissos.oid_compromisso_vincula is null " +
          	  " AND   compromissos.oid_compromisso_parcela is null " +
	    	  " AND   movimentos_compromissos.dt_pagamento >= '" + data_inicial + "'" +
	          " AND   movimentos_compromissos.dt_pagamento <= '" + data_final + "'" +
          	  " AND   Compromissos.oid_Conta = " + oid_Conta;
      }

      
      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }

      if (oid_Unidade > 0) {
        sql += " and Compromissos.OID_Unidade = " + oid_Unidade;
      }
      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
          if ("P".equals (dm_Periodo)) {
        	  total = res.getDouble ("tt_pg")+res.getDouble ("tt_multa")+res.getDouble ("tt_juros")+res.getDouble ("tt_outros")-res.getDouble ("tt_desc")-res.getDouble ("tt_estorno");
          }else {
        	  total = res.getDouble ("VL_Total");
          }
      }

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Conhecimento");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Conhecimento");
    }
    return total;
  }

  public double total_Despesa_Compromisso (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta , String dm_Periodo, long oid_Centro_Custo) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet res2 = null;
    double total = 0;
    int comp_ok = 0;
    GerencialED ed = new GerencialED ();
    try {


      sql = " SELECT Compromissos.oid_Compromisso, Compromissos.oid_Pessoa, Compromissos.VL_Compromisso, Compromissos.nr_meses_amortizacao ";

      if ("P".equals (dm_Periodo)) {
    	  sql +=" ,movimentos_compromissos.vl_pagamento as tt_pg, movimentos_compromissos.vl_multa_pagamento as tt_multa ,movimentos_compromissos.vl_juros_pagamento as tt_juros, movimentos_compromissos.vl_desconto as tt_desc, movimentos_compromissos.vl_outras_despesas as tt_outros, movimentos_compromissos.vl_estorno as tt_estorno ";
      }	  
      	  
      sql+= " FROM  Compromissos, Unidades ";
	      if ("P".equals (dm_Periodo)) {
	    	  sql +=" ,movimentos_compromissos  ";
	      }

      sql+=" WHERE Compromissos.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Unidades.DM_Situacao       <> 'I' " +
          " AND   Compromissos.VL_Compromisso > 0  " +
          " AND   compromissos.dt_liberado is not null " +
          " AND   compromissos.oid_compromisso_vincula is null " +
          " AND   compromissos.oid_compromisso_parcela is null ";
	      if ("P".equals (dm_Periodo)) {
	    	  sql +=" AND  movimentos_compromissos.oid_compromisso = compromissos.oid_compromisso ";
	      }

      if (oid_Conta>0){
        sql += " AND   Compromissos.oid_Conta =" + oid_Conta;
      }
      
      if (oid_Centro_Custo>0){
        sql += " AND   Compromissos.oid_Centro_Custo =" + oid_Centro_Custo;
      }

      if (oid_Conta == 307) { //monica gm
        //dm_Periodo = "V";
      }

      
      if ("P".equals (dm_Periodo)) {
    	  sql +=" AND  movimentos_compromissos.oid_compromisso = compromissos.oid_compromisso " +
    	  	    " AND  movimentos_compromissos.dt_pagamento >='" + data_inicial + "'" +
                " AND  movimentos_compromissos.dt_pagamento <= '" + data_final + "'";

      }else {
    	  if ("M".equals (dm_Periodo)) {
    		  sql += " AND   Compromissos.dt_emissao >= '" + data_inicial + "'" +
    		         " AND   Compromissos.dt_emissao <= '" + data_final + "'";
    	  }  else {
    		  if ("V".equals (dm_Periodo)) {
    			  sql += " AND   Compromissos.dt_vencimento >= '" + data_inicial + "'" +
    			  		 " AND   Compromissos.dt_vencimento <= '" + data_final + "'";
    		  }
    		  else {
    			  sql += " AND   Compromissos.dt_entrada >= '" + data_inicial + "'" +
    			  		 " AND   Compromissos.dt_entrada <= '" + data_final + "'";
    		  }
    	  }	  
      }

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }

      if (oid_Unidade > 0) {
        sql += " and Compromissos.OID_Unidade = " + oid_Unidade;
      }
      
      
      sql += " LIMIT 10";
      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {


        sql = " Select count (oid_Compromisso) as qt_ordem_frete from Movimentos_Ordens WHERE oid_Compromisso = " + res.getLong ("oid_Compromisso");
        res2 = this.executasql.executarConsulta (sql.toString ());
        while (res2.next ()) {
          comp_ok = res2.getInt ("qt_ordem_frete");
        }

        if (comp_ok == 0) {
          sql = " Select count (Contas.oid_Conta) as qt_conta from Contas, Fornecedores WHERE Fornecedores.oid_Conta = Contas.oid_conta AND Fornecedores.oid_Fornecedor = '" +
              res.getString ("oid_Pessoa") + "'";
          res2 = this.executasql.executarConsulta (sql.toString ());
          while (res2.next ()) {
            comp_ok = res2.getInt ("qt_conta");
          }
        }

        if (comp_ok == 0) {
        	double vl_compromisso=res.getDouble ("VL_Compromisso");
            if ("P".equals (dm_Periodo)) {
            	vl_compromisso = res.getDouble ("tt_pg")+res.getDouble ("tt_multa")+res.getDouble ("tt_juros")+res.getDouble ("tt_outros")-res.getDouble ("tt_desc")-res.getDouble ("tt_estorno");
            }  
        	
            if (res.getLong ("nr_meses_amortizacao") > 0) {
            	total += vl_compromisso / res.getLong ("nr_meses_amortizacao");
            }
            else {
            	total += vl_compromisso;
            }
        }
      }
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Ordem_Frete");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Ordem_Frete");
    }
    return total;
  }

  private double total_Despesa_Cobranca (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    try {

      // System.out.println (" BD 0");

      sql = " SELECT SUM (Movimentos_Duplicatas.VL_Debito) as VL_Total " +
          " FROM   Movimentos_Duplicatas, Duplicatas, Unidades, Tipos_Instrucoes  " +
          " WHERE Movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata " +
          " AND   Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao  " +
          " AND   Duplicatas.oid_Unidade = Unidades.oid_Unidade " +
          " AND   (Tipos_Instrucoes.dm_altera_titulo ='DBA' OR Tipos_Instrucoes.dm_altera_titulo ='DCO' )" +
          " AND Unidades.DM_Situacao       <> 'I' " +
          " AND   Movimentos_Duplicatas.VL_Debito > 0" +
          " AND   Movimentos_Duplicatas.oid_Conta = " + oid_Conta +
          " AND   Movimentos_Duplicatas.dt_movimento >= '" + data_inicial + "'" +
          " AND   Movimentos_Duplicatas.dt_movimento <= '" + data_final + "'";

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }

      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Total");
      }
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("total_Despesa_Cobranca");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Despesa_Cobranca");
    }
    return total;
  }

  private double total_Despesa_Diversas (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double total = 0;
    GerencialED ed = new GerencialED ();
    try {

      // System.out.println (" BD 0");

      sql = " select SUM (Despesas.VL_Despesa) as VL_Despesa, SUM (Despesas.VL_Compromisso) as VL_Compromisso " +
          " FROM  Despesas, Unidades " +
          " WHere Despesas.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Unidades.DM_Situacao       <> 'I' " +
          " AND   Despesas.VL_Despesa > 0  " +
          " AND   Despesas.oid_Conta = " + oid_Conta +
          " AND   Despesas.DT_Emissao >= '" + ed.getDT_1 () + "'" +
          " AND   Despesas.DT_Emissao <= '" + ed.getDT_2 () + "'";

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }

      if (oid_Unidade > 0) {
        sql += " and Unidades.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        total = res.getDouble ("VL_Despesa") - res.getDouble ("VL_Compromisso");
      }
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Ordem_Frete");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Ordem_Frete");
    }
    return total;
  }

  private double total_Despesa_Fornecedor (String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , long oid_Conta , String dm_Periodo) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet res2 = null;
    double total = 0;
    int comp_ok = 0;
    GerencialED ed = new GerencialED ();
    try {

      // System.out.println (" BD 0");

      sql = " select Compromissos.oid_Compromisso, Compromissos.VL_Compromisso " +
          " FROM  Compromissos, Unidades " +
          " WHere Compromissos.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Compromissos.oid_Pessoa = Fornecedores.oid_Fornecedor " +
          " AND   Unidades.DM_Situacao       <> 'I' " +
          " AND   compromissos.dt_liberado is not null " +
          " AND   Compromissos.VL_Compromisso > 0  " +
          " AND   compromissos.oid_compromisso_vincula is null " +
          " AND   compromissos.oid_compromisso_parcela is null " +
          " AND   Fornecedores.oid_Conta = " + oid_Conta;

      if ("M".equals (dm_Periodo)) {
        sql += " AND   Compromissos.dt_emissao >= '" + data_inicial + "'" +
               " AND   Compromissos.dt_emissao <= '" + data_final + "'";
      }
      else {
        if ("V".equals (dm_Periodo)) {
          sql += " AND   Compromissos.dt_vencimento >= '" + data_inicial + "'" +
                 " AND   Compromissos.dt_vencimento <= '" + data_final + "'";
        }
        else {
          sql += " AND   Compromissos.dt_entrada >= '" + data_inicial + "'" +
                 " AND   Compromissos.dt_entrada <= '" + data_final + "'";
        }
      }

      if (oid_Empresa > 0) {
        sql += " and Unidades.oid_Empresa = " + oid_Empresa;
      }

      if (oid_Unidade > 0) {
        sql += " and Compromissos.OID_Unidade = " + oid_Unidade;
      }

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {
        sql = " Select count (oid_Compromisso) as qt_ordem_frete from Movimentos_Ordens WHERE oid_Compromisso = " + res.getLong ("oid_Compromisso");
        res2 = this.executasql.executarConsulta (sql.toString ());
        while (res2.next ()) {
          comp_ok = res2.getInt ("qt_ordem_frete");
        }

        if (comp_ok == 0) {
          total += res.getDouble ("VL_Compromisso");
        }
      }
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro total_Receita_Ordem_Frete");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Receita_Ordem_Frete");
    }
    return total;
  }

  public byte[] geraAnalise_Gerencial_Faturamento (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    String dt_inicial = "";
    String dt_final = "";
    ResultSet res = null;
    double total_frete1 = 0;
    double qtde_embarques1 = 0;
    double peso1 = 0;
    double desconto1 = 0;
    double total_frete2 = 0;
    double qtde_embarques2 = 0;
    double peso2 = 0;
    double desconto2 = 0;
    double total_frete3 = 0;
    double qtde_embarques3 = 0;
    double peso3 = 0;
    double desconto3 = 0;
    NM_Campo2 = "Analise Faturamento";
    try {

      // System.out.println (" BD 0");

      Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

      ///------------------------------------------------------------///
      ///RECEITA COM CTO

      sql = " select Oid_Unidade, Oid_Empresa " +
          " From  Unidades " +
          " Order By  Unidades.oid_Empresa, Unidades.CD_Unidade ";

      // System.out.println (" sql " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      valor_mes1 = 0;
      valor_mes2 = 0;
      valor_mes3 = 0;

      while (res.next ()) {
        total_frete1 = 0;
        qtde_embarques1 = 0;
        peso1 = 0;
        desconto1 = 0;

        total_frete2 = 0;
        qtde_embarques2 = 0;
        peso2 = 0;
        desconto2 = 0;

        total_frete3 = 0;
        qtde_embarques3 = 0;
        peso3 = 0;
        desconto3 = 0;

        dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
        dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());

        //faturamento
        valor_mes1 = this.total_Receita_Conhecimento (dt_inicial , dt_final , res.getLong ("oid_Empresa") , res.getLong ("oid_Unidade") , ed.getOID_Modal () , ed.getDM_Periodo (),"");
        total_frete1 = tt_total_frete;
        qtde_embarques1 = tt_qtde_embarques;
        peso1 = tt_peso;
        desconto1 = tt_desconto;

        if (!"--".equals (ed.getNM_Mes_2 ()) && !"----".equals (ed.getNM_Ano_2 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_2 () + "/" + ed.getNM_Ano_2 ());
          valor_mes2 = this.total_Receita_Conhecimento (dt_inicial , dt_final , res.getLong ("oid_Empresa") , res.getLong ("oid_Unidade") , ed.getOID_Modal () , ed.getDM_Periodo (),"");
          total_frete2 = tt_total_frete;
          qtde_embarques2 = tt_qtde_embarques;
          peso2 = tt_peso;
          desconto2 = tt_desconto;

        }
        if (!"--".equals (ed.getNM_Mes_3 ()) && !"----".equals (ed.getNM_Ano_3 ())) {
          dt_inicial = "01/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ();
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_3 () + "/" + ed.getNM_Ano_3 ());
          valor_mes3 = this.total_Receita_Conhecimento (dt_inicial , dt_final , res.getLong ("oid_Empresa") , res.getLong ("oid_Unidade") , ed.getOID_Modal () , ed.getDM_Periodo (),"");
          total_frete3 = tt_total_frete;
          qtde_embarques3 = tt_qtde_embarques;
          peso3 = tt_peso;
          desconto3 = tt_desconto;
        }

        OID_Unidade_Origem = res.getLong ("oid_Unidade");

        if ("M1".equals (ed.getDM_Relatorio ())) {
          NM_Campo = "Faturamento";
          valor_mes1 = total_frete1;
          valor_mes2 = total_frete2;
          valor_mes3 = total_frete3;
          if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
            this.totaliza_receita_despesa (ed);
          }
        }

        if ("M2".equals (ed.getDM_Relatorio ())) {
          NM_Campo = "Embarques";
          valor_mes1 = qtde_embarques1;
          valor_mes2 = qtde_embarques2;
          valor_mes3 = qtde_embarques3;
          if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
            this.totaliza_receita_despesa (ed);
          }
        }
        if ("M3".equals (ed.getDM_Relatorio ())) {
          NM_Campo = "Peso";
          valor_mes1 = peso1;
          valor_mes2 = peso2;
          valor_mes3 = peso3;
          if (valor_mes1 + valor_mes2 + valor_mes3 > 0) {
            this.totaliza_receita_despesa (ed);
          }
        }

      }

      // System.out.println (" FIMa ");

      b = this.imprime_Gerencial_Faturamento (ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }


  public byte[] geraAnalise_Gerencial_Faturamento_Diario (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    String dt_inicial = "";
    String dt_final = "";
    ResultSet resCli = null;
    NM_Campo2 = "Analise Faturamento Diario";
    try {

      // System.out.println (" BD 0");

      Nr_Sort = String.valueOf (System.currentTimeMillis ()).toString ();

      dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
      dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());

      ///---EMBARQUES------------------------------------------------///
      if ("MD_EMB1".equals(ed.getDM_Relatorio())){
        sql = " SELECT oid_Pessoa_Pagador as oid_Pessoa  " +
            " FROM  Conhecimentos " +
            " WHERE Conhecimentos.DM_Impresso='S' " +
            " AND   Conhecimentos.DM_Situacao<>'C' " +
            " AND   Conhecimentos.DT_Emissao>='" + dt_inicial + "'" +
            " AND   Conhecimentos.DT_Emissao<='" + dt_final + "'" +
            " GROUP BY oid_Pessoa_Pagador ";
        // System.out.println (" sql " + sql);
      
        resCli = this.executasql.executarConsulta (sql.toString ());
        while (resCli.next ()) {
          this.inicioTransacao ();
          this.executasql = this.sql;
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      
          GerencialED edGerencial = new GerencialED ();
          edGerencial.setOID_Gerencial (System.currentTimeMillis ());
          edGerencial.setOID_Pessoa (resCli.getString ("oid_Pessoa"));
          edGerencial.setNR_Sort (Nr_Sort);
          edGerencial.setNM_Campo (resCli.getString ("oid_Pessoa"));
          edGerencial.setNM_Campo2 (NM_Campo2);
          edGerencial.setOID_Cliente (resCli.getString ("oid_Pessoa"));
      
          dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_1 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "02/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_2 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "03/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_3 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "04/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_4 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "05/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_5 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "06/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_6 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "07/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_7 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "08/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_8 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "09/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_9 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "10/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_10 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "11/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_11 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "11/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_11 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "12/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_12 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "13/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_13 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "14/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_14 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "15/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_15 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "16/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_16 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "17/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_17 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "18/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_19 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "19/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_19 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "20/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_20 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "21/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_21 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "22/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_22 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "23/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_23 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "24/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_24 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "25/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_25 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "26/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_26 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "27/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_27 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "28/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_28 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "29/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          if (!dt_inicial.equals (dt_final)) {
            edGerencial.setVL_29 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
            dt_inicial = "30/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
            if (!dt_inicial.equals (dt_final)) {
              edGerencial.setVL_30 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
              dt_inicial = "31/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
              if (dt_inicial.equals (dt_final)) {
                edGerencial.setVL_31 (total_Receita_Conhecimento (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , ed.getDM_Periodo () , resCli.getString ("oid_Pessoa")));
              }
            }
          }
      
          inclui (edGerencial);
          this.fimTransacao (true);
      
        }
      }

      ///-----FATURAMENTO--------------------------------------------///
      if ("MD_FAT1".equals(ed.getDM_Relatorio()) || "MD_FAT2".equals(ed.getDM_Relatorio()) ){
        String dm_Emissao_Vencimento="E";
        if ("MD_FAT2".equals(ed.getDM_Relatorio())){
          dm_Emissao_Vencimento="V";
        }

        
        sql = " SELECT Conhecimentos.oid_Pessoa_Pagador as oid_Pessoa  " +
          " From  Conhecimentos, Unidades, Origens_Duplicatas, Duplicatas " +
          " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
          " AND Conhecimentos.oid_Conhecimento = Origens_Duplicatas.oid_Conhecimento " +
          " AND Duplicatas.oid_Duplicata = Origens_Duplicatas.oid_Duplicata " +
          " AND Conhecimentos.DM_Impresso  = 'S' " +
          " AND Unidades.DM_Situacao       <> 'I' " +
          " AND Conhecimentos.VL_Total_Frete >  0  " +
          " AND Conhecimentos.DM_Tipo_Documento = 'C' " +
          " AND Origens_Duplicatas.DM_Situacao = 'A' " +
          " AND Conhecimentos.DM_Situacao   <> 'C' " +
          " AND   Conhecimentos.DT_Emissao>='" + dt_inicial + "'" +
          " AND   Conhecimentos.DT_Emissao<='" + dt_final + "'" +
          " GROUP BY oid_Pessoa_Pagador ";
        // System.out.println (" sql " + sql);
      
        resCli = this.executasql.executarConsulta (sql.toString ());
        while (resCli.next ()) {
          this.inicioTransacao ();
          this.executasql = this.sql;
          dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ());
      
          GerencialED edGerencial = new GerencialED ();
          edGerencial.setOID_Gerencial (System.currentTimeMillis ());
          edGerencial.setOID_Pessoa (resCli.getString ("oid_Pessoa"));
          edGerencial.setNR_Sort (Nr_Sort);
          edGerencial.setNM_Campo (resCli.getString ("oid_Pessoa"));
          edGerencial.setNM_Campo2 (NM_Campo2);
          edGerencial.setOID_Cliente (resCli.getString ("oid_Pessoa"));
      
          dt_inicial = "01/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_1 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "02/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_2 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "03/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_3 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "04/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_4 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "05/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_5 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "06/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_6 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "07/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_7 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "08/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_8 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "09/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_9 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "10/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_10 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "11/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_11 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "11/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_11 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "12/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_12 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "13/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_13 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "14/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_14 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "15/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_15 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "16/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_16 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "17/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_17 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "18/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_19 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "19/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_19 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "20/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_20 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "21/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_21 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "22/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_22 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "23/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_23 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "24/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_24 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "25/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_25 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "26/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_26 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "27/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_27 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "28/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          edGerencial.setVL_28 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
      
          dt_inicial = "29/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
          if (!dt_inicial.equals (dt_final)) {
            edGerencial.setVL_29 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
            dt_inicial = "30/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
            if (!dt_inicial.equals (dt_final)) {
              edGerencial.setVL_30 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
              dt_inicial = "31/" + ed.getNM_Mes_1 () + "/" + ed.getNM_Ano_1 ();
              if (dt_inicial.equals (dt_final)) {
                edGerencial.setVL_31 (total_Conhecimento_Faturado (dt_inicial , dt_inicial , ed.getOID_Empresa () , ed.getOID_Unidade () , ed.getOID_Modal () , dm_Emissao_Vencimento , resCli.getString ("oid_Pessoa")));
              }
            }
          }
      
          inclui (edGerencial);
          this.fimTransacao (true);
      
        }
      }

      // System.out.println (" FIMa ");

      this.inicioTransacao ();
      this.executasql = this.sql;
      b = this.imprime_Gerencial_Faturamento_Diario (ed);
      this.fimTransacao(true);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }


  public byte[] imprime_Gerencial_Faturamento (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    double vl_total_faturameto1 = 0;
    double vl_total_faturameto2 = 0;
    double vl_total_faturameto3 = 0;
    try {

      ResultSet res = null;

      sql = " select ";
      sql += "  SUM (GERENCIAL.VL_1) as VL_1, ";
      sql += "  SUM (GERENCIAL.VL_2) as VL_2, ";
      sql += "  SUM (GERENCIAL.VL_3) as VL_3  ";
      sql += "  from Gerencial ";
      sql += "  WHERE Gerencial.NM_CAMPO = 'Faturamento'";
      sql += "  AND   Gerencial.Nr_Sort = '" + Nr_Sort + "'";

      // System.out.println (" imp1  ");

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        // System.out.println ("somando receita ");
        vl_total_faturameto1 = res.getDouble ("VL_1");
        vl_total_faturameto2 = res.getDouble ("VL_2");
        vl_total_faturameto3 = res.getDouble ("VL_3");
      }

      sql = " select * ";
      sql += "  FROM  Gerencial, Unidades, Empresas, Pessoas, Cidades ";
      sql += "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'";
      sql += "  AND Gerencial.oid_Unidade_Origem = Unidades.oid_Unidade ";
      sql += "  AND Unidades.oid_Empresa = Empresas.oid_empresa ";
      sql += "  AND Unidades.oid_pessoa = Pessoas.oid_pessoa ";
      sql += "  AND Pessoas.oid_Cidade = Cidades.oid_Cidade ";
      sql += "  order by Gerencial.NM_Campo, Unidades.oid_Empresa,  Unidades.CD_Unidade, Gerencial.NM_Campo";

      // System.out.println (" imp1 3 ");

      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
//      while (res.next()){
//         // System.out.println("r2 tem dados ");
//      }

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Faturamento (res , ed , vl_total_faturameto1 , vl_total_faturameto2 , vl_total_faturameto3);

//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Gerencial_Conhecimentos_Modelo02(res,ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public byte[] imprime_Gerencial_Faturamento_Diario (GerencialED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    try {

      ResultSet res = null;

      sql = " select * ";
      sql += "  FROM  Gerencial, Pessoas ";
      sql += "  WHERE Gerencial.Nr_Sort = '" + Nr_Sort + "'";
      sql += "  AND Gerencial.oid_pessoa = Pessoas.oid_pessoa ";
      sql += "  order by Pessoas.NM_Razao_Social ";

      // System.out.println (" imp1 3 ");

      // System.out.println ("sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
//      while (res.next()){
//         // System.out.println("r2 tem dados ");
//      }

      GerencialRL conRL = new GerencialRL ();

      b = conRL.geraAnalise_Gerencial_Faturamento_Diario (res , ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  private double total_Faturamento_Mes (String data_inicial , String data_final , String oid_Cliente , String oid_Vendedor) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double vl_faturamento = 0;
    try {

      // System.out.println (" BD 0");

      sql = " SELECT sum (vl_total_frete) as vl_faturamento " +
          " FROM CONHECIMENTOS, Unidades " +
          " WHERE CONHECIMENTOS.OID_PESSOA_PAGADOR = '" + oid_Cliente + "'" +
          " AND   CONHECIMENTOS.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Unidades.DM_Situacao      <> 'I' " +
          " AND   CONHECIMENTOS.DM_SITUACAO <> 'C' " +
          " AND   CONHECIMENTOS.DM_Tipo_Documento = 'C' " +
          " AND   CONHECIMENTOS.VL_TOTAL_FRETE >0 " +
          " AND   CONHECIMENTOS.DT_EMISSAO >='" + data_inicial + "'" +
          " AND   CONHECIMENTOS.DT_EMISSAO <='" + data_final + "'";
      // // System.out.println (" sql " + sql);

      if (String.valueOf (oid_Vendedor) != null &&
          !String.valueOf (oid_Vendedor).equals ("") &&
          !String.valueOf (oid_Vendedor).equals ("null")) {
        sql += " AND CONHECIMENTOS.OID_Vendedor = '" + oid_Vendedor + "'";
      }

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        vl_faturamento = res.getDouble ("vl_faturamento");
      }
      // // System.out.println (" oid_Cliente Fat= " + vl_faturamento);


    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("total_Faturamento_Mes");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Faturamento_Mes");
    }
    return vl_faturamento;
  }

  private double totalizar_Veiculo (int totalizar , String data_inicial , String data_final , long oid_Empresa , long oid_Unidade , String oid_Veiculo) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double vl_total = 0;
    try {

      if (totalizar == 1) { //FATURAMENTO PROPRIO
        sql = " SELECT SUM(Acertos.VL_Frete_Proprio) AS vl_total " +
            " FROM Ordens_Servicos, Unidades, Acertos " +
            " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
            "  AND Acertos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
            "  AND Acertos.NR_Kilometragem_Chegada > 0 " +
            "  AND Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'" +
            "  AND Acertos.DT_Emissao >= '" + data_inicial + "'" +
            "  AND Acertos.DT_Emissao <= '" + data_final + "'";

        if (oid_Empresa > 0) {
          sql += " and Unidades.oid_Empresa = " + oid_Empresa;
        }
        if (oid_Unidade > 0) {
          sql += " and Unidades.OID_Unidade = " + oid_Unidade;
        }

        // System.out.println (" sql " + sql);

        res = this.executasql.executarConsulta (sql.toString ());
        while (res.next ()) {
          vl_total = res.getDouble ("vl_total");
        }

      }

      if (totalizar == 2) { //FATURAMENTO TERCEIRO
        sql = " SELECT SUM(Acertos.VL_Frete_Terceiro) AS vl_total " +
            " FROM Ordens_Servicos, Unidades, Acertos " +
            " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
            "  AND Acertos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
            "  AND Acertos.NR_Kilometragem_Chegada > 0 " +
            "  AND Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'" +
            "  AND Acertos.DT_Emissao >= '" + data_inicial + "'" +
            "  AND Acertos.DT_Emissao <= '" + data_final + "'";

        if (oid_Empresa > 0) {
          sql += " and Unidades.oid_Empresa = " + oid_Empresa;
        }
        if (oid_Unidade > 0) {
          sql += " and Unidades.OID_Unidade = " + oid_Unidade;
        }

        res = this.executasql.executarConsulta (sql.toString ());
        while (res.next ()) {
          vl_total = res.getDouble ("vl_total");
        }
      }

      if (totalizar >= 11 && totalizar <= 40) { //MANUTENCAO

        String DM_Tipo_Despesa = "";
        //custo variavel
        if (totalizar == 11) {
          DM_Tipo_Despesa = "V";
        }
        if (totalizar == 13) {
          DM_Tipo_Despesa = "E";
        }
        if (totalizar == 14) {
          DM_Tipo_Despesa = "P";
        }
        if (totalizar == 15) {
          DM_Tipo_Despesa = "D";
        }
        if (totalizar == 16) {
          DM_Tipo_Despesa = "A";
        }
        if (totalizar == 17) {
          DM_Tipo_Despesa = "S";
        }
        if (totalizar == 18) {
          DM_Tipo_Despesa = "L";
        }
        if (totalizar == 19) {
          DM_Tipo_Despesa = "R";
        }

        //custo fixo
        if (totalizar == 31) {
          DM_Tipo_Despesa = "Y";
        }
        if (totalizar == 32) {
          DM_Tipo_Despesa = "Z";
        }
        if (totalizar == 33) {
          DM_Tipo_Despesa = "X";
        }

        sql = " SELECT SUM (VL_Movimento) as VL_Movimento " +
            " FROM  Tipos_Servicos, Movimentos_Ordens_Servicos " +
            " WHERE Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
            " AND Movimentos_Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'" +
            " AND Movimentos_Ordens_Servicos.dt_movimento_ordem_servico >= '" + data_inicial + "'" +
            " AND Movimentos_Ordens_Servicos.dt_movimento_ordem_servico <= '" + data_final + "'" +
            " AND Tipos_Servicos.DM_Tipo_Despesa = '" + DM_Tipo_Despesa + "'";
        res = this.executasql.executarConsulta (sql.toString ());
        while (res.next ()) {
          vl_total = res.getDouble ("VL_Movimento");
        }
      }

      if (totalizar == 51) { //COMISSAO MOTORISTA
        sql = " SELECT SUM (vl_comissao) as  vl_comissao " +
            " From Acertos, Ordens_Servicos, Unidades " +
            " WHERE Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade " +
            " AND Ordens_Servicos.oid_Ordem_Servico = Acertos.oid_Ordem_Servico " +
            " AND Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'" +
            " AND Acertos.NR_Kilometragem_Chegada > 0 " +
            " AND Acertos.DT_Emissao >= '" + data_inicial + "'" +
            " AND Acertos.DT_Emissao <= '" + data_final + "'";

        if (oid_Empresa > 0) {
          sql += " and Unidades.oid_Empresa = " + oid_Empresa;
        }
        if (oid_Unidade > 0) {
          sql += " and Unidades.OID_Unidade = " + oid_Unidade;
        }

        res = this.executasql.executarConsulta (sql.toString ());
        while (res.next ()) {
          vl_total = res.getDouble ("vl_comissao");
        }
      }

      if (totalizar == 52) { //KILOMETRAGEM
        sql = " SELECT Ordens_Servicos.NR_Kilometragem, " +
            "        Acertos.NR_Kilometragem_Chegada " +
            " From Acertos, Ordens_Servicos, Unidades " +
            " WHERE Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade " +
            " AND Ordens_Servicos.oid_Ordem_Servico = Acertos.oid_Ordem_Servico " +
            " AND Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'" +
            " AND Acertos.NR_Kilometragem_Chegada > 0 " +
            " AND Acertos.DT_Emissao >= '" + data_inicial + "'" +
            " AND Acertos.DT_Emissao <= '" + data_final + "'";

        if (oid_Empresa > 0) {
          sql += " and Unidades.oid_Empresa = " + oid_Empresa;
        }
        if (oid_Unidade > 0) {
          sql += " and Unidades.OID_Unidade = " + oid_Unidade;
        }

        res = this.executasql.executarConsulta (sql.toString ());
        while (res.next ()) {
          vl_total += res.getDouble ("NR_Kilometragem_Chegada") - res.getDouble ("NR_Kilometragem");
        }
      }
      if (totalizar == 53) { //COMBUSTIVEL
        sql = " SELECT SUM (VL_Movimento) as VL_Movimento " +
            " FROM  Tipos_Servicos, Movimentos_Ordens_Servicos, Acertos, Ordens_Servicos, Unidades  " +
            " WHERE Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
            " AND Movimentos_Ordens_Servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
            " AND Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade " +
            " AND Ordens_Servicos.oid_Ordem_Servico = Acertos.oid_Ordem_Servico " +
            " AND Movimentos_Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'" +
            " AND Acertos.NR_Kilometragem_Chegada > 0 " +
            " AND Acertos.DT_Emissao >= '" + data_inicial + "'" +
            " AND Acertos.DT_Emissao <= '" + data_final + "'" +
            " AND Tipos_Servicos.DM_Tipo_Despesa = 'C'";

        if (oid_Empresa > 0) {
          sql += " and Unidades.oid_Empresa = " + oid_Empresa;
        }
        if (oid_Unidade > 0) {
          sql += " and Unidades.OID_Unidade = " + oid_Unidade;
        }

        res = this.executasql.executarConsulta (sql.toString ());
        while (res.next ()) {
          vl_total = res.getDouble ("VL_Movimento");
        }
      }

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("total_Faturamento_Mes");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("total_Faturamento_Mes");
    }
    return vl_total;
  }

  private double totaliza_Prazo_Medio (GerencialED ed , String oid_Pessoa) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    try {

      // System.out.println (" totaliza_Prazo_Medio DM_Tipo=>" + DM_Prazo_Medio);
      tt_Prazo_Medio = 0;

      if ("COBRANCA".equals (DM_Prazo_Medio)) {
        sql = " SELECT sum (Conhecimentos.VL_Total_Frete)  as tt_Prazo_Medio  " +
            " FROM  Conhecimentos, Duplicatas, Origens_Duplicatas, Movimentos_Duplicatas, Unidades  " +
            " WHERE Duplicatas.oid_Unidade = Unidades.oid_Unidade " +
            " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento  " +
            " AND Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Movimentos_Duplicatas.DM_Principal ='S'  " +
            " AND Origens_Duplicatas.DM_Situacao <> 'E' " +
            " AND Conhecimentos.VL_Total_Frete  > 0 " +
            " AND Conhecimentos.DM_Impresso  = 'S' " +
            " AND Conhecimentos.DM_Situacao   <> 'C' " +
            " AND Movimentos_Duplicatas.Dt_movimento >= '" + ed.getDT_1 () + "'" +
            " AND Movimentos_Duplicatas.Dt_movimento <= '" + ed.getDT_2 () + "'";

        if (oid_Pessoa != null && oid_Pessoa.length () > 4) {
          sql += " and Duplicatas.oid_pessoa = '" + oid_Pessoa + "'";
        }

      }
      // System.out.println (" sql " + sql);

      if ("PAGAMENTO".equals (DM_Prazo_Medio)) {
        sql = " SELECT SUM (Movimentos_Compromissos.vl_pagamento) as  tt_Prazo_Medio " +
            " From  Compromissos, Movimentos_Compromissos " +
            " WHERE Movimentos_Compromissos.vl_pagamento >0  " +
            " AND Compromissos.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso " +
            " AND Movimentos_Compromissos.dt_pagamento >= '" + ed.getDT_1 () + "'" +
            " AND Movimentos_Compromissos.dt_pagamento <= '" + ed.getDT_2 () + "'";

        if (oid_Pessoa != null && oid_Pessoa.length () > 4) {
          sql += " and Compromissos.oid_pessoa = '" + oid_Pessoa + "'";
        }

      }
      if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
        sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Unidades.OID_Unidade = " + ed.getOID_Unidade ();
      }

      res = this.executasql.executarConsulta (sql.toString ());

      if (res.next ()) {
        tt_Prazo_Medio = res.getDouble ("tt_Prazo_Medio");
      }

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("totaliza_Prazo_Medio");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("totaliza_Prazo_Medio");
    }
    return tt_Prazo_Medio;
  }

}
