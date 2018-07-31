package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.CarteiraED;
import com.master.util.Utilitaria;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class CarteiraBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);

  public CarteiraBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public CarteiraED inclui (CarteiraED ed) throws Excecoes {
    long valOid = 0;
    CarteiraED carteiraED = new CarteiraED ();

    try {
      ResultSet rs = executasql.executarConsulta ("select max(oid_Carteira) as result from Carteiras");
      try {
        //pega proximo valor da chave
        while (rs.next ()) {
          valOid = rs.getInt ("result");
        }
      }
      finally {
        util.closeResultset (rs);
      }
      valOid = valOid + 1;

      String sql =
          " insert into Carteiras " +
          "   (OID_CARTEIRA " +
          "   ,DM_CARTEIRA " +
          "   ,PE_JUROS " +
          "   ,PE_MULTA " +
          "   ,CD_TIPO_CARTEIRA " +
          "   ,NR_REMESSA " +
          "   ,CD_CARTEIRA " +
          "   ,DM_REMETE_EDI " +
          "   ,CD_EMPRESA_BANCO " +
          "   ,DT_ABERTURA_CARTEIRA " +
          "   ,DT_ENCERRAMENTO_CARTEIRA " +
          "   ,DT_INICIO_EDI " +
          "   ,DM_TIPO_EMISSAO " +
          "   ,NR_CONVENIO " +
          "   ,DM_ALTERAR_VENCIMENTO " +
          "   ,DM_CONCEDER_DESCONTO " +
          "   ,DM_PEDIDO_BAIXA " +
          "   ,DM_PROTESTAR " +
          "   ,DM_SUSTAR_PROTESTO " +
          "   ,NR_TAXA_DESCONTO " +
          "   ,NR_LIMITE_CREDITO " +
          "   ,OID_CONTA_CORRENTE " +
          "   ,DM_Tipo_Impressao_Bloqueto " +
          "   ,NR_Bloqueto_Inicial " +
          "   ,NR_Bloqueto_Atual " +
          "   ,NR_Bloqueto_Final " +
          "   ,NR_Dias_Liberacao_Cobranca " +
          "   ,DT_STAMP " +
          "   ,USUARIO_STAMP " +
          "   ,DM_STAMP) values" +
          "(" + ++valOid + ",";
      sql += "'" + ed.getDm_Carteira () + "',";
      sql += ed.getPe_Juros () == null ? "null," : ed.getPe_Juros () + ",";
      sql += ed.getPe_Multa () == null ? "null," : ed.getPe_Multa () + ",";
      sql += ed.getCd_Tipo_Carteira () == null ? "null," : "'" + ed.getCd_Tipo_Carteira () + "',";
      sql += ed.getNr_Remessa () == null ? "null," : ed.getNr_Remessa () + ",";
      sql += ed.getCd_Carteira () == null ? "null," : "'" + ed.getCd_Carteira () + "',";
      sql += "'" + ed.getDm_Remete_EDI () + "',";
      sql += ed.getCd_Empresa_Banco () == null ? "null," : "'" + ed.getCd_Empresa_Banco () + "',";
      sql += ed.getDt_abertura_carteira () == null ? "null," : "'" + ed.getDt_abertura_carteira () + "',";
      sql += ed.getDt_Encerramento_Carteira () == null ? "null," : "'" + ed.getDt_Encerramento_Carteira () + "',";
      sql += ed.getDT_Inicio_EDI () == null ? "null," : "'" + ed.getDT_Inicio_EDI () + "',";
      sql += "'" + ed.getDm_Tipo_Emissao () + "',";
      sql += ed.getNr_Convenio () == null ? "null," : "'" + ed.getNr_Convenio () + "',";
      sql += "'" + ed.getDm_Alterar_Vencimento () + "',";
      sql += "'" + ed.getDm_Conceder_Desconto () + "',";
      sql += "'" + ed.getDm_Pedido_Baixa () + "',";
      sql += "'" + ed.getDm_Protestar () + "',";
      sql += "'" + ed.getDm_Sustar_Protesto () + "',";
      sql += ed.getNr_Taxa_Desconto () == null ? "null," : ed.getNr_Taxa_Desconto () + ",";
      sql += ed.getNr_Limite_Credito () == null ? "null," : ed.getNr_Limite_Credito () + ",";
      sql += "'" + ed.getOid_Conta_Corrente () + "', ";
      sql += util.getSQLString (ed.getDM_Tipo_Impressao_Bloqueto ()) + ", ";
      sql += ed.getNR_Bloqueto_Inicial () + ", ";
      sql += ed.getNR_Bloqueto_Atual () + ", ";
      sql += ed.getNR_Bloqueto_Final () + ", ";
      sql += ed.getNR_Dias_Liberacao_Cobranca () == null ? "null," : "'" + ed.getNR_Dias_Liberacao_Cobranca () + "',";
      sql += "'" + ed.getDt_stamp ();
      sql += "','" + ed.getUsuario_Stamp ();
      sql += "','" + ed.getDm_Stamp () + "')";

      executasql.executarUpdate (sql);
      carteiraED.setOid_Carteira (new Integer (Long.toString (valOid)));

      return carteiraED;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(CarteiraED ed)");
    }
  }

  public CarteiraED getByRecord (CarteiraED ed) throws Excecoes {
    String sql =
        " select Carteiras.* " +
        "       ,Contas_Correntes.oid_Conta_Corrente " +
        "       ,Contas_Correntes.CD_Conta_Corrente " +
        "       ,Contas_Correntes.nr_conta_corrente" +
        "       ,Contas_Correntes.oid_Moeda " +
        "       ,Contas_Correntes.oid_Pessoa " +
        "       ,Pessoas.NR_CNPJ_CPF" +
        "       ,Pessoas.NM_Razao_Social" +
        " from Carteiras " +
        "     ,Contas_Correntes " +
        "     ,Pessoas" +
        " where Carteiras.oid_conta_corrente = Contas_Correntes.oid_Conta_Corrente " +
        "   and Contas_Correntes.oid_Pessoa = Pessoas.oid_Pessoa ";
    if (ed.getCd_Carteira () != null && !ed.getCd_Carteira ().equals ("")) {
      sql += " and carteiras.cd_carteira = '" + ed.getCd_Carteira () + "'";
    }
    if (ed.getOid_Carteira () != null && !ed.getOid_Carteira ().equals ("")) {
      sql += " and carteiras.oid_carteira = " + ed.getOid_Carteira ();
    }

    CarteiraED edVolta = new CarteiraED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      try {
        while (res.next ()) {
          edVolta = new CarteiraED ();

          edVolta.setOid_Carteira (new Integer (res.getInt ("Oid_Carteira")));
          edVolta.setCd_Carteira (res.getString ("cd_carteira"));
          edVolta.setTX_Fatura (res.getString ("TX_Fatura"));
          edVolta.setTX_Bloqueto (res.getString ("TX_Bloqueto"));
          edVolta.setCd_Empresa_Banco (res.getString ("cd_empresa_banco"));
          edVolta.setCd_Tipo_Carteira (res.getString ("cd_tipo_carteira"));
          edVolta.setDM_Tipo_Impressao_Bloqueto (res.getString ("DM_Tipo_Impressao_Bloqueto"));
          edVolta.setNR_Bloqueto_Inicial (res.getLong ("NR_Bloqueto_Inicial"));
          edVolta.setNR_Bloqueto_Atual (res.getLong ("NR_Bloqueto_Atual"));
          edVolta.setNR_Bloqueto_Final (res.getLong ("NR_Bloqueto_Final"));
          edVolta.setNR_Dias_Liberacao_Cobranca (res.getString ("NR_Dias_Liberacao_Cobranca"));
          edVolta.setDm_Alterar_Vencimento (res.getString ("dm_alterar_vencimento"));
          edVolta.setDm_Carteira (res.getString ("dm_carteira"));
          edVolta.setDm_Conceder_Desconto (res.getString ("dm_conceder_desconto"));
          edVolta.setDm_Pedido_Baixa (res.getString ("dm_pedido_baixa"));
          edVolta.setDm_Protestar (res.getString ("dm_protestar"));
          edVolta.setDm_Remete_EDI (res.getString ("dm_remete_edi"));
          edVolta.setDm_Sustar_Protesto (res.getString ("dm_sustar_protesto"));
          edVolta.setDm_Tipo_Emissao (res.getString ("dm_tipo_emissao"));
          edVolta.setDt_abertura_carteira (FormataData.formataDataBT (res.getDate ("dt_abertura_carteira")));
          edVolta.setDT_Inicio_EDI (FormataData.formataDataBT (res.getDate ("dt_Inicio_EDI")));
          edVolta.setDt_Encerramento_Carteira (FormataData.formataDataBT (res.getString ("dt_encerramento_carteira")));
          edVolta.setNr_Convenio (res.getString ("nr_convenio"));
          edVolta.setNr_Limite_Credito (new Double (res.getDouble ("nr_limite_credito")));
          edVolta.setNr_Remessa (new Integer (res.getInt ("nr_remessa")));
          edVolta.setNr_Taxa_Desconto (new Double (res.getDouble ("nr_taxa_desconto")));
          edVolta.setPe_Juros (new Double (res.getDouble ("pe_juros")));
          edVolta.setPe_Multa (new Double (res.getDouble ("pe_Multa")));

          edVolta.setOid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
          edVolta.setCd_Conta_Corrente (res.getString ("cd_conta_corrente"));
          edVolta.setNr_Conta_Corrente (res.getString ("nr_conta_corrente"));
          edVolta.setOid_Moeda (res.getString ("oid_Moeda"));

          edVolta.setOid_Pessoa (res.getString ("oid_Pessoa"));
          edVolta.setNr_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
          edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social"));
          if (JavaUtil.doValida (edVolta.getOid_Pessoa ())) {
            edVolta.setCd_banco (new Utilitaria (executasql).getTableStringValue ("CD_Banco" , "Bancos" , "oid_Pessoa = " + JavaUtil.getSQLString (edVolta.getOid_Pessoa ())));
          }
        }
        return edVolta;
      }
      finally {
        util.closeResultset (res);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(CarteiraED ed)");
    }
  }

  public CarteiraED getByRecordByMoeda (CarteiraED ed) throws Excecoes {

    CarteiraED edVolta = new CarteiraED ();
    String sql =
        " select Carteiras.* " +
        "       ,Contas_Correntes.* " +
        "       ,Pessoas.NR_CNPJ_CPF" +
        "       ,Pessoas.NM_Razao_Social" +
        " from Carteiras " +
        "     ,Contas_Correntes " +
        "     ,Pessoas" +
        " where Carteiras.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
        "   and Contas_Correntes.oid_Pessoa = Pessoas.oid_Pessoa ";
    if (ed.getCd_Carteira () != null && !ed.getCd_Carteira ().equals ("")) {
      sql += " and carteiras.cd_carteira = '" + ed.getCd_Carteira () + "'";
    }
    if (ed.getOid_Carteira () != null && !ed.getOid_Carteira ().equals ("")) {
      sql += " and carteiras.oid_carteira = " + ed.getOid_Carteira ();
    }
    if (JavaUtil.doValida (ed.getOid_Moeda ())) {
      sql += " and contas_correntes.oid_moeda = " + ed.getOid_Moeda ();
    }

    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      try {
        while (res.next ()) {

          edVolta = new CarteiraED ();

          edVolta.setOid_Carteira (new Integer (res.getInt ("Oid_Carteira")));
          edVolta.setCd_Carteira (res.getString ("cd_carteira"));
          edVolta.setOid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
          edVolta.setCd_Conta_Corrente (res.getString ("cd_conta_corrente"));
          edVolta.setCd_Empresa_Banco (res.getString ("cd_empresa_banco"));
          edVolta.setCd_Tipo_Carteira (res.getString ("cd_tipo_carteira"));
          edVolta.setDm_Alterar_Vencimento (res.getString ("dm_alterar_vencimento"));
          edVolta.setDm_Carteira (res.getString ("dm_carteira"));
          edVolta.setDm_Conceder_Desconto (res.getString ("dm_conceder_desconto"));
          edVolta.setDm_Pedido_Baixa (res.getString ("dm_pedido_baixa"));
          edVolta.setDm_Protestar (res.getString ("dm_protestar"));
          edVolta.setDm_Remete_EDI (res.getString ("dm_remete_edi"));
          edVolta.setDm_Sustar_Protesto (res.getString ("dm_sustar_protesto"));
          edVolta.setDm_Tipo_Emissao (res.getString ("dm_tipo_emissao"));

          edVolta.setDt_abertura_carteira (FormataData.formataDataBT (res.getDate ("dt_abertura_carteira")));
          edVolta.setDT_Inicio_EDI (FormataData.formataDataBT (res.getDate ("dt_Inicio_EDI")));
          edVolta.setDt_Encerramento_Carteira (FormataData.formataDataBT (res.getDate ("dt_encerramento_carteira")));

          edVolta.setOid_Pessoa (res.getString ("oid_Pessoa"));
          edVolta.setNr_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
          edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social"));
          if (JavaUtil.doValida (edVolta.getOid_Pessoa ())) {
            edVolta.setCd_banco (new Utilitaria (executasql).getTableStringValue ("CD_Banco" , "Bancos" , "oid_Pessoa = " + JavaUtil.getSQLString (edVolta.getOid_Pessoa ())));
          }

          edVolta.setNr_Conta_Corrente (res.getString ("nr_conta_corrente"));
          edVolta.setNr_Convenio (res.getString ("nr_convenio"));

          edVolta.setNr_Limite_Credito (new Double (res.getDouble ("nr_limite_credito")));
          edVolta.setNr_Remessa (new Integer (res.getInt ("nr_remessa")));
          edVolta.setNr_Taxa_Desconto (new Double (res.getDouble ("nr_taxa_desconto")));

          edVolta.setPe_Juros (new Double (res.getDouble ("pe_juros")));
          edVolta.setPe_Multa (new Double (res.getDouble ("pe_Multa")));
          edVolta.setDM_Tipo_Impressao_Bloqueto (res.getString ("DM_Tipo_Impressao_Bloqueto"));
          edVolta.setNR_Bloqueto_Inicial (res.getLong ("NR_Bloqueto_Inicial"));
          edVolta.setNR_Bloqueto_Atual (res.getLong ("NR_Bloqueto_Atual"));
          edVolta.setNR_Bloqueto_Final (res.getLong ("NR_Bloqueto_Final"));
        }
        return edVolta;
      }
      finally {
        util.closeResultset (res);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecordByMoeda(CarteiraED ed)");
    }
  }

  public void altera (CarteiraED ed) throws Excecoes {

    String sql = " update Carteiras set ";
    sql += " DM_CARTEIRA = '" + ed.getDm_Carteira () + "', ";

    if (!"".equals (ed.getPe_Juros ())) {
      sql += " PE_JUROS = " + ed.getPe_Juros () + ", ";
    }
    else {
      sql += " PE_JUROS = null,";
    }

    if (!"".equals (ed.getPe_Multa ())) {
      sql += " PE_MULTA = " + ed.getPe_Multa () + ", ";
    }
    else {
      sql += " PE_MULTA = null, ";
    }

    sql += " CD_TIPO_CARTEIRA = '" + ed.getCd_Tipo_Carteira () + "', ";

    if (!"".equals (ed.getNr_Remessa ())) {
      sql += " NR_REMESSA = '" + ed.getNr_Remessa () + "', ";
    }
    else {
      sql += " NR_REMESSA = null, ";
    }

    if (ed.getTX_Fatura () != null && !"null".equals (ed.getTX_Fatura ())) {
      sql += " TX_Fatura = '" + ed.getTX_Fatura () + "', ";
    }
    else {
      sql += " TX_Fatura = '', ";
    }

    if (ed.getTX_Bloqueto () != null && !"null".equals (ed.getTX_Bloqueto ())) {
      sql += " TX_Bloqueto = '" + ed.getTX_Bloqueto () + "', ";
    }
    else {
      sql += " TX_Bloqueto = '', ";
    }

    sql += " CD_CARTEIRA = '" + ed.getCd_Carteira () + "', ";
    sql += " DM_REMETE_EDI = '" + ed.getDm_Remete_EDI () + "', ";

    if (!"".equals (ed.getCd_Empresa_Banco ())) {
      sql += " CD_EMPRESA_BANCO = '" + ed.getCd_Empresa_Banco () + "', ";
    }
    else {
      sql += " CD_EMPRESA_BANCO = null, ";
    }

    if (!"".equals (ed.getDt_abertura_carteira ())) {
      sql += " DT_ABERTURA_CARTEIRA = '" + ed.getDt_abertura_carteira () + "', ";
    }
    else {
      sql += " DT_ABERTURA_CARTEIRA = null, ";
    }

    if (!"".equals (ed.getDT_Inicio_EDI ())) {
      sql += " DT_Inicio_EDI = '" + ed.getDT_Inicio_EDI () + "', ";
    }
    else {
      sql += " DT_Inicio_EDI = null, ";
    }

    if (!"".equals (ed.getDt_Encerramento_Carteira ())) {
      sql += " DT_ENCERRAMENTO_CARTEIRA = '" + ed.getDt_Encerramento_Carteira () + "', ";
    }
    else {
      sql += " DT_ENCERRAMENTO_CARTEIRA = null, ";
    }

    sql += " DM_TIPO_EMISSAO = '" + ed.getDm_Tipo_Emissao () + "', ";

    if (!"".equals (ed.getNr_Convenio ())) {
      sql += " NR_CONVENIO = '" + ed.getNr_Convenio () + "', ";
    }
    else {
      sql += " NR_CONVENIO = null, ";
    }

    if (!"".equals (ed.getNR_Dias_Liberacao_Cobranca ())) {
      sql += " NR_Dias_Liberacao_Cobranca = '" + ed.getNR_Dias_Liberacao_Cobranca () + "', ";
    }
    else {
      sql += " NR_Dias_Liberacao_Cobranca = '1', ";
    }

    sql += " DM_ALTERAR_VENCIMENTO = '" + ed.getDm_Alterar_Vencimento () + "', ";
    sql += " DM_CONCEDER_DESCONTO = '" + ed.getDm_Conceder_Desconto () + "', ";
    sql += " DM_PEDIDO_BAIXA = '" + ed.getDm_Pedido_Baixa () + "', ";
    sql += " DM_PROTESTAR = '" + ed.getDm_Protestar () + "', ";
    sql += " DM_SUSTAR_PROTESTO = '" + ed.getDm_Sustar_Protesto () + "', ";

    if (!"".equals (ed.getNr_Taxa_Desconto ())) {
      sql += " NR_TAXA_DESCONTO = " + ed.getNr_Taxa_Desconto () + ", ";
    }

    if (!"".equals (ed.getNr_Limite_Credito ())) {
      sql += " NR_LIMITE_CREDITO = " + ed.getNr_Limite_Credito () + ", ";
    }

    if (JavaUtil.doValida (ed.getDM_Tipo_Impressao_Bloqueto ())) {
      sql += " DM_Tipo_Impressao_Bloqueto = " + util.getSQLString (ed.getDM_Tipo_Impressao_Bloqueto ()) + ", ";
    }
    sql += " NR_Bloqueto_Inicial = " + ed.getNR_Bloqueto_Inicial () + ", ";
    sql += " NR_Bloqueto_Atual = " + ed.getNR_Bloqueto_Atual () + ", ";
    sql += " NR_Bloqueto_Final = " + ed.getNR_Bloqueto_Final () + ", ";
    sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
    sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "',";
    sql += " DM_STAMP = '" + ed.getDm_Stamp () + "' ";
    sql += " where oid_Carteira = " + ed.getOid_Carteira ();

    try {
      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(CarteiraED ed)");
    }
  }

  public void deleta (CarteiraED ed) throws Excecoes {
    String sql =
        " delete from Carteiras " +
        " WHERE oid_Carteira = " + ed.getOid_Carteira ();
    try {
      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta(CarteiraED ed)");
    }
  }

  public ArrayList lista (CarteiraED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    String sql =
        " select Carteiras.oid_carteira " +
        "       ,Carteiras.cd_carteira" +
        "       ,Carteiras.nr_remessa" +
        "       ,Carteiras.dm_remete_edi" +
        "       ,Carteiras.dm_tipo_emissao" +
        "       ,Carteiras.cd_carteira" +
        "       ,Carteiras.dt_abertura_carteira" +
        "       ,Contas_Correntes.nr_conta_corrente " +
        "       ,Contas_Correntes.oid_Pessoa " +
        "       ,Pessoas.NR_CNPJ_CPF" +
        "       ,Pessoas.NM_Razao_Social" +
        " from Carteiras " +
        "     ,Contas_Correntes " +
        "     ,Pessoas" +
        " where Carteiras.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
        "   and Contas_Correntes.oid_Pessoa = Pessoas.oid_Pessoa ";
    if (ed.getCd_Carteira () != null && !ed.getCd_Carteira ().equals ("")) {
      sql += " and carteiras.cd_carteira = '" + ed.getCd_Carteira () + "'";
    }
    if (ed.getOid_Conta_Corrente () != null && !ed.getOid_Conta_Corrente ().equals ("")) {
      sql += " and carteiras.oid_conta_corrente = '" + ed.getOid_Conta_Corrente () + "'";
    }
    if (ed.getDm_Carteira () != null && !ed.getDm_Carteira ().equals ("") && !ed.getDm_Carteira ().equalsIgnoreCase ("T")) {
      sql += " and carteiras.dm_carteira = '" + ed.getDm_Carteira () + "'";
    }

    if (ed.getNm_Razao_Social () != null && !ed.getNm_Razao_Social ().equals ("")) {
      sql += " and pessoas.NM_RAZAO_SOCIAL like '" + ed.getNm_Razao_Social () + "%'";
    }
    sql += " ORDER BY Carteiras.CD_Carteira";
    ResultSet res = this.executasql.executarConsulta (sql);
    try {
      try {
        while (res.next ()) {
          CarteiraED edVolta = new CarteiraED ();

          edVolta.setOid_Pessoa (res.getString ("oid_Pessoa"));
          edVolta.setNr_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
          edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social"));
          if (JavaUtil.doValida (edVolta.getOid_Pessoa ())) {
            edVolta.setCd_banco (new Utilitaria (executasql).getTableStringValue ("CD_Banco" , "Bancos" , "oid_Pessoa = " + JavaUtil.getSQLString (edVolta.getOid_Pessoa ())));
          }

          edVolta.setOid_Carteira (new Integer (res.getInt ("oid_carteira")));
          edVolta.setCd_Carteira (res.getString ("cd_carteira"));
          edVolta.setNr_Conta_Corrente (res.getString ("nr_conta_corrente"));
          edVolta.setNr_Remessa (new Integer (res.getInt ("nr_remessa")));

          edVolta.setDm_Remete_EDI (res.getString ("dm_remete_edi"));
          edVolta.setDm_Tipo_Emissao (res.getString ("dm_tipo_emissao"));

          edVolta.setDt_abertura_carteira (FormataData.formataDataBT (res.getDate ("dt_abertura_carteira")));

          list.add (edVolta);
        }
        return list;
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(CarteiraED ed)");
      }
    }
    finally {
      util.closeResultset (res);
    }
  }

  public ArrayList listaByMoeda (CarteiraED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    String sql =
        " select Carteiras.oid_carteira " +
        "       ,Carteiras.cd_carteira" +
        "       ,Carteiras.nr_remessa" +
        "       ,Carteiras.dm_remete_edi" +
        "       ,Carteiras.dm_tipo_emissao" +
        "       ,Carteiras.cd_carteira" +
        "       ,Carteiras.dt_abertura_carteira" +
        "       ,Contas_Correntes.nr_conta_corrente " +
        "       ,Contas_Correntes.oid_Pessoa " +
        "       ,Pessoas.NR_CNPJ_CPF" +
        "       ,Pessoas.NM_Razao_Social" +
        " from Carteiras " +
        "     ,Contas_Correntes " +
        "     ,Pessoas" +
        " where Carteiras.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
        "   and Contas_Correntes.oid_Pessoa = Pessoas.oid_Pessoa ";
    if (ed.getCd_Carteira () != null && !ed.getCd_Carteira ().equals ("")) {
      sql += " and carteiras.cd_carteira = '" + ed.getCd_Carteira () + "'";
    }
    if (ed.getOid_Conta_Corrente () != null && !ed.getOid_Conta_Corrente ().equals ("")) {
      sql += " and carteiras.oid_conta_corrente = '" + ed.getOid_Conta_Corrente () + "'";
    }
    if (ed.getDm_Carteira () != null && !ed.getDm_Carteira ().equals ("") && !ed.getDm_Carteira ().equalsIgnoreCase ("T")) {
      sql += " and carteiras.dm_carteira = '" + ed.getDm_Carteira () + "'";
    }
    if (JavaUtil.doValida (ed.getOid_Moeda ())) {
      sql += " and contas_correntes.oid_moeda = " + ed.getOid_Moeda ();
    }
    if (ed.getNm_Razao_Social () != null && !ed.getNm_Razao_Social ().equals ("")) {
      sql += " and pessoas.NM_RAZAO_SOCIAL like '" + ed.getNm_Razao_Social () + "%'";
    }

    ResultSet res = this.executasql.executarConsulta (sql);
    try {
      try {
        while (res.next ()) {
          CarteiraED edVolta = new CarteiraED ();

          edVolta.setOid_Pessoa (res.getString ("oid_Pessoa"));
          edVolta.setNr_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
          edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social"));
          if (JavaUtil.doValida (edVolta.getOid_Pessoa ())) {
            edVolta.setCd_banco (new Utilitaria (executasql).getTableStringValue ("CD_Banco" , "Bancos" , "oid_Pessoa = " + JavaUtil.getSQLString (edVolta.getOid_Pessoa ())));
          }

          edVolta.setOid_Carteira (new Integer (res.getInt ("oid_carteira")));
          edVolta.setCd_Carteira (res.getString ("cd_carteira"));
          edVolta.setNr_Conta_Corrente (res.getString ("nr_conta_corrente"));
          edVolta.setNr_Remessa (new Integer (res.getInt ("nr_remessa")));

          edVolta.setDm_Remete_EDI (res.getString ("dm_remete_edi"));
          edVolta.setDm_Tipo_Emissao (res.getString ("dm_tipo_emissao"));

          list.add (edVolta);
        }
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "listaByMoeda(CarteiraED ed)");
      }
      return list;
    }
    finally {
      util.closeResultset (res);
    }
  }
}