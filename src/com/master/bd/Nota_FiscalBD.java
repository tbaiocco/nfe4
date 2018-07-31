package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Auxiliar1ED;
import com.master.ed.Nota_FiscalED;
import com.master.ed.PessoaED;
import com.master.ed.RastreamentoED;
import com.master.ed.ReferenciaED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Nota_FiscalBD
    extends BancoUtil {

  private ExecutaSQL executasql;
  FormataDataBean DataFormatada = new FormataDataBean ();
  FormataDataBean dataFormatada = new FormataDataBean ();

  public Nota_FiscalBD (ExecutaSQL sql) {
    super (sql);
    this.executasql = sql;
  }

  public Nota_FiscalED inclui (Nota_FiscalED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    long NR_Despacho = 0;
    String chave = null;

    Nota_FiscalED conED = new Nota_FiscalED ();
    Parametro_FixoED paramED = new Parametro_FixoED ();

    try {
      chave = String.valueOf (ed.getOID_Pessoa ()) + String.valueOf (ed.getNR_Nota_Fiscal ()) + ed.getNM_Serie () + ed.getDM_Tipo_Conhecimento ();

      if ("4".equals (ed.getDM_Tipo_Conhecimento ()) || !"N".equals (paramED.getDM_Despacho ())) {
        ResultSet rs = null;

        sql = " SELECT NR_PROXIMO_DESPACHO ";
        sql += " FROM Parametros_Filiais ";
        sql += " WHERE Parametros_Filiais.OID_Unidade = " + paramED.getOID_Unidade_Padrao ();

        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          NR_Despacho = rs.getLong ("NR_PROXIMO_DESPACHO");
        }
        NR_Despacho++;
        sql = " UPDATE Parametros_Filiais SET NR_PROXIMO_DESPACHO= " + (NR_Despacho);
        sql += " WHERE Parametros_Filiais.OID_Unidade = " + paramED.getOID_Unidade_Padrao ();

        int u = executasql.executarUpdate (sql);

      }
      if ("4".equals (ed.getDM_Tipo_Conhecimento ())) {
        chave = chave + String.valueOf (NR_Despacho);
      }
      if (ed.getOid_Deposito () == 0) {
        ed.setOid_Deposito (paramED.getOID_Deposito ());
      }
      if (ed.getNR_Nota_Fiscal () <= 0 || paramED.getDM_Despacho ().equals ("T")) {
        chave = String.valueOf (NR_Despacho) + String.valueOf (System.currentTimeMillis ());
      }

      ed.setOID_Nota_Fiscal(chave);

      long nr_sequencia = System.currentTimeMillis ();

      sql = "insert into Notas_Fiscais (" +
          "OID_Nota_Fiscal, " +
          "Nr_Sequencia, " +
          "OID_Natureza_Operacao, " +
          "OID_Pessoa, " +
          "OID_Pessoa_Destinatario, " +
          "OID_Pessoa_Redespacho, " +
          "OID_Pessoa_Entregadora, " +
          "OID_Pessoa_Consignatario, " +
          "NR_Nota_Fiscal, " +
          "NR_Despacho, " +
          "NR_Volumes, " +
          "NM_Serie, " +
          "DT_Entrada, " +
          "HR_Entrada, " +
          "VL_Nota_Fiscal, " +
          "DM_Situacao, " +
          "NR_Pedido, " +
          "NR_Codigo_Cliente, " +
          "DT_Emissao, " +
          "NR_Peso, " +
          "VL_Seguro, " +
          "NR_Itens, " +
          "NR_Peso_Cubado, " +
          "DM_Transferencia, " +
          "DM_Exportacao, " +
          "TX_Observacao, " +
          "DM_Tipo_Pagamento, " +
          "OID_Modal, " +
          "OID_Produto, " +
          "OID_Coleta , " +
          "DM_Tipo_Conhecimento, " +
          "NR_Lote, " +
          "NR_Cubagem, " +
          "NR_Cubagem_Total, " +
          "VL_Tabela, " +
          "VL_Total_Frete, " +
          "DM_Tabela, " +
          "NM_Especie, " +
          "OID_Deposito, " +
          "OID_Ordem_Carga," +
          "OID_Ordem_Descarga," +
          "OID_Unidade, " +
          "oid_Unidade_Fiscal, " +
          "oid_Unidade_Contabil, " +
          "oid_Pessoa_Fornecedor, " +
          "dm_Impresso, " +
          "dm_Devolvido, " +
          "oid_Modelo_Nota_Fiscal, " +
          "dm_Tipo_Nota_Fiscal, " +
          "dm_Finalizado, nm_arquivo_imp, nm_ch_nfe, nfe_chave_acesso " +
          ") values ";
      sql += "('" +
          ed.getOID_Nota_Fiscal () + "'," +
          nr_sequencia + "," +
          ed.getOID_Natureza_Operacao () + ",'" +
          ed.getOID_Pessoa () + "','" +
          ed.getOID_Pessoa_Destinatario () + "','" +
          ed.getOID_Pessoa_Redespacho () + "','" +
          ed.getOID_Pessoa_Entregadora () + "','" +
          ed.getOID_Pessoa_Consignatario () + "'," +
          ed.getNR_Nota_Fiscal () + "," +
          NR_Despacho + "," +
          ed.getNR_Volumes () + ",'" +
          ed.getNM_Serie () + "','" +
          ed.getDT_Entrada () + "','" +
          ed.getHR_Entrada () + "'," +
          ed.getVL_Nota_Fiscal () + ",'" +
          JavaUtil.getValue (ed.getDM_Situacao ()) + "','" +
          ed.getNR_Pedido () + "','" +
          ed.getNR_Codigo_Cliente () + "','" +
          ed.getDT_Emissao () + "'," +
          ed.getNR_Peso () + "," +
          ed.getVL_Seguro () + "," +
          ed.getNR_Itens () + "," +
          ed.getNR_Peso_Cubado () + ",'" +
          JavaUtil.getValue (ed.getDM_Transferencia ()) + "','" +
          JavaUtil.getValue (ed.getDM_Exportacao ()) + "','" +
          ed.getTX_Observacao () + "','" +
          JavaUtil.getValue (ed.getDM_Tipo_Pagamento ()) + "'," +
          ed.getOID_Modal () + "," +
          ed.getOID_Produto () + "," +
          ed.getOID_Coleta () + ",'" +
          JavaUtil.getValue (ed.getDM_Tipo_Conhecimento ()) + "','" +
          ed.getNR_Lote () + "'," +
          ed.getNR_Cubagem () + "," +
          ed.getNR_Cubagem_Total () + "," +
          ed.getVL_Tabela () + "," +
          ed.getVL_Total_Frete () + ",'" +
          JavaUtil.getValue (ed.getDM_Tabela ()) + "', " +
          getSQLStringDef (JavaUtil.getValue (ed.getNM_Especie ()) , "") + ", " +
          ed.getOid_Deposito () + ", " +
          ed.getOID_Ordem_Carga () + ", " +
          ed.getOID_Ordem_Descarga () + ", " +
          ed.getOID_Unidade () + ", " +
          ed.getOID_Unidade () + ", " +
          ed.getOID_Unidade () + ", " +
          ed.getOID_Pessoa () + ", " +
          "'N'" + ", " +
          "'N'" + ", " +
          ed.getOid_Modelo_Nota_Fiscal () + ", " +
          "'S'" + ", " +
          "'A'" + ", '" + ed.getNm_arquivo_imp() + "', '" + ed.getNm_chave_nfe() + "', '" + ed.getNm_chave_nfe() + "' " +
          ")";
System.out.println("Ins NF: "+ sql);
      int i = executasql.executarUpdate (sql);
      conED.setOID_Nota_Fiscal (chave);
    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Nota_FiscalED ed)");
    }
    catch (Exception ex) {
      ex.printStackTrace ();
      throw new Excecoes (ex.getMessage () , ex , null , "");
    }
    return conED;
  }

  public void altera (Nota_FiscalED ed) throws Excecoes {

    String sql = null;
    String DM_Impresso = null;

    try {

      sql = " SELECT Conhecimentos.DM_Impresso FROM " +
          " Notas_Fiscais, " +
          " Conhecimentos_Notas_Fiscais, Conhecimentos " +
          " WHERE Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
          " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        DM_Impresso = res.getString ("DM_Impresso");
      }

      if (DM_Impresso != null && DM_Impresso.equals ("S")) {
        //Excecoes exc = new Excecoes ();
       // throw exc;
      }

      sql = " update Notas_Fiscais set OID_Pessoa= '" + ed.getOID_Pessoa () +
      	  "', OID_Pessoa_Destinatario= '" + ed.getOID_Pessoa_Destinatario () +
      	  "', OID_Natureza_Operacao= " + ed.getOID_Natureza_Operacao () +
          " , OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'" +
          " , OID_Pessoa_Redespacho = '" + ed.getOID_Pessoa_Redespacho () + "'" +
          " , NR_Nota_Fiscal= " + ed.getNR_Nota_Fiscal () +
          " , NR_Lote= " + getSQLStringDef (ed.getNR_Lote () , "") +
          " , NR_Volumes= " + ed.getNR_Volumes () +
          " , DT_Entrada= " + getSQLStringDef (ed.getDT_Entrada (), "") +
          " , HR_Entrada= " + getSQLStringDef (ed.getHR_Entrada (), "") +
          " , VL_Nota_Fiscal= " + ed.getVL_Nota_Fiscal () +
          " , NR_Pedido= " + getSQLStringDef (ed.getNR_Pedido (), "") +
          " , NR_Codigo_Cliente= " + getSQLStringDef (ed.getNR_Codigo_Cliente (), "") +
          " , DT_Emissao= " + getSQLStringDef (ed.getDT_Emissao (), "") +
          " , NR_Peso= " + ed.getNR_Peso () +
          " , NR_Peso_Cubado= " + ed.getNR_Peso_Cubado () +
          " , NR_Itens= " + ed.getNR_Itens () +
          " , DM_Transferencia= " + getSQLStringDef (ed.getDM_Transferencia (), "") +
          " , DM_Exportacao= " + getSQLStringDef (ed.getDM_Exportacao (), "") +
          " , DM_Tipo_Pagamento= " + getSQLStringDef (ed.getDM_Tipo_Pagamento (), "") +
          " , TX_Observacao= " + getSQLStringDef (ed.getTX_Observacao (), "") +
          "  ,OID_Modal= " + ed.getOID_Modal () +
          "  ,OID_Deposito= " + ed.getOid_Deposito () +
          "  ,OID_Produto= " + ed.getOID_Produto () +
          "  ,NR_Cubagem= " + ed.getNR_Cubagem () +
          "  ,NR_Cubagem_Total= " + ed.getNR_Cubagem_Total () +
          "  ,VL_Total_Frete = " + ed.getVL_Total_Frete () +
          "  ,NM_Especie = " + getSQLStringDef (ed.getNM_Especie () , "");
      sql += " where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";

      int i = executasql.executarUpdate (sql);

      sql = " SELECT Conhecimentos.OID_Conhecimento FROM Conhecimentos, Conhecimentos_Notas_Fiscais";
      sql += " WHERE Conhecimentos.DM_Impresso ='N' AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      res = this.executasql.executarConsulta (sql);
      String oid_Conhecimento = "";
      while (res.next ()) {
        oid_Conhecimento = res.getString ("OID_Conhecimento");
      }
      if (!oid_Conhecimento.equals ("")) {
        sql = " update Conhecimentos set " +
            " VL_Total_Frete = 0, VL_ICMS = 0, VL_Base_Calculo_ICMS = 0 " +
            " where oid_Conhecimento = '" + oid_Conhecimento + "'";

        i = executasql.executarUpdate (sql);
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      if (DM_Impresso != null && DM_Impresso.equals ("S")) {
        excecoes.setMensagem ("Conhecimento j� impresso com essa nota fiscal - altera��o de notas fiscais n�o permitida");
      }
      else {
        excecoes.setMensagem ("Erro ao alterar");
      }
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void confirma_Carga_Descarga_Deposito (Nota_FiscalED ed) throws Excecoes {

    String sql = null;

    try {

      if ("D".equals (ed.getDM_Carga_Descarga ())) {
        sql = " update Notas_Fiscais set OID_Deposito= " + ed.getOid_Deposito () +
            " ,DT_Descarga_Deposito= '" + Data.getDataDMY () + "'" +
            " ,OID_Ordem_Descarga= " + ed.getOID_Ordem_Carga ();

      }
      if ("C".equals (ed.getDM_Carga_Descarga ())) {
        sql = " update Notas_Fiscais set OID_Deposito =null,  OID_Ordem_Carga= " + ed.getOID_Ordem_Carga () +
            " ,DT_Carga_Deposito= '" + Data.getDataDMY () + "'";
      }
      sql += " WHERE  oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("confirma_Descarga_Armazem");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void confirma_Descarga_Deposito (Nota_FiscalED ed) throws Excecoes {

    String sql = null , DM_Impresso = "";

    try {

      sql = " update Notas_Fiscais set OID_Motorista= '" + ed.getOID_Motorista () + "'" +
          " ,OID_Veiculo= '" + ed.getOID_Veiculo () + "'" +
          " ,DT_Descarga_Deposito= '" + Data.getDataDMY () + "'" +
          " ,OID_Deposito= " + ed.getOid_Deposito () +
          " ,VL_Custo_Descarga= " + ed.getVL_Custo_Descarga () +
          " WHERE  oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";

      // // System.out.println (sql);

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("confirma_Descarga_Armazem");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void erroCalculo (Nota_FiscalED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Notas_Fiscais set nm_erro_calculo= '" + ed.getNM_Erro_Calculo () + "'";
      sql += " where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";

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

  public void deleta (Nota_FiscalED ed) throws Excecoes {

    String sql = null;
    String DM_Impresso = null;

    try {

      sql = " SELECT Conhecimentos.DM_Impresso FROM " +
          " Notas_Fiscais, " +
          " Conhecimentos_Notas_Fiscais, Conhecimentos " +
          " WHERE Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
          " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        DM_Impresso = res.getString ("DM_Impresso");
      }

      if (DM_Impresso != null && DM_Impresso.equals ("S")) {
        Excecoes exc = new Excecoes ();
        throw exc;
      }

      sql = "delete from Conhecimentos_Notas_Fiscais WHERE oid_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Nota_Fiscal () + "')";
      int i = executasql.executarUpdate (sql);

      sql = "delete from Ocorrencias_Notas_Fiscais WHERE oid_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Nota_Fiscal () + "')";
      i = executasql.executarUpdate (sql);

      sql = "delete from Notas_Fiscais WHERE oid_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Nota_Fiscal () + "')";

      i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      if (DM_Impresso != null && DM_Impresso.equals ("S")) {
        excecoes.setMensagem ("Conhecimento j� impresso com essa nota fiscal - exclus�o de notas fiscais n�o permitida");
      }
      else {
        excecoes.setMensagem ("Erro ao excluir");
      }
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Nota_FiscalED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  Notas_Fiscais.oid_Deposito, " +
          "         Notas_Fiscais.DT_Entrada, " +
          "         Notas_Fiscais.DM_Situacao, " +
          "         Notas_Fiscais.NR_Peso, " +
          "         Notas_Fiscais.NR_Volumes, " +
          "         Notas_Fiscais.NM_Erro_Calculo, " +
          "         Notas_Fiscais.VL_Nota_Fiscal, " +
          "         Notas_Fiscais.VL_Seguro, " +
          "         Notas_Fiscais.vl_liquido_nota_fiscal, " +
          "         Notas_Fiscais.DT_Emissao, " +
          "         Notas_Fiscais.NR_Nota_Fiscal, " +
          "         Notas_Fiscais.NR_Despacho, " +
          "         Notas_Fiscais.OID_Coleta, " +
          "         Notas_Fiscais.OID_Nota_Fiscal, " +
          "         Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
          "         Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
          " FROM  Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario " +
          " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
          " AND   Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
          " AND   Notas_Fiscais.dm_tipo_nota_fiscal = 'S' ";
      // Foi incluido esta selecao " dm_tipo_nota_fiscal = 'S' " porque agora ha varios tipos de nf nesta tabela e nao so de transportes.

      if (String.valueOf (ed.getCD_Chassis_Serie ()) != null &&
          !String.valueOf (ed.getCD_Chassis_Serie ()).equals ("") &&
          !String.valueOf (ed.getCD_Chassis_Serie ()).equals ("null")) {
        sql = " select  Notas_Fiscais.DT_Entrada, Notas_Fiscais.DM_Situacao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.NR_Despacho, Notas_Fiscais.OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Itens_Notas_Fiscais ";
        sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal ";
        sql += " AND Itens_Notas_Fiscais.CD_CHASSIS_SERIE = '" + ed.getCD_Chassis_Serie () + "'";
      }
      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0") &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }

      if (String.valueOf (ed.getNR_Despacho ()) != null &&
          !String.valueOf (ed.getNR_Despacho ()).equals ("0") &&
          !String.valueOf (ed.getNR_Despacho ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Despacho = " + ed.getNR_Despacho ();
      }

      if (String.valueOf (ed.getOID_Coleta ()) != null &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("0") &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Coleta = " + ed.getOID_Coleta ();
      }

      if (String.valueOf (ed.getOID_Produto ()) != null &&
          !String.valueOf (ed.getOID_Produto ()).equals ("0") &&
          !String.valueOf (ed.getOID_Produto ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Produto = " + ed.getOID_Produto ();
      }

      if (String.valueOf (ed.getOID_Ordem_Carga ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Carga ()).equals ("0") &&
          !String.valueOf (ed.getOID_Ordem_Carga ()).equals ("null")) {
        sql += " and (Notas_Fiscais.OID_Ordem_Carga = " + ed.getOID_Ordem_Carga () + " OR Notas_Fiscais.OID_Ordem_Descarga = " + ed.getOID_Ordem_Carga () + ")";
      }

      if (String.valueOf (ed.getOid_Deposito ()) != null &&
          !String.valueOf (ed.getOid_Deposito ()).equals ("0") &&
          !String.valueOf (ed.getOid_Deposito ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Deposito = " + ed.getOid_Deposito ();
      }

      if (String.valueOf (ed.getNR_Pedido ()) != null &&
          !String.valueOf (ed.getNR_Pedido ()).equals ("") &&
          !String.valueOf (ed.getNR_Pedido ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Pedido like '" + ed.getNR_Pedido () + "%'";
      }

      if (String.valueOf (ed.getNR_Codigo_Cliente ()) != null &&
          !String.valueOf (ed.getNR_Codigo_Cliente ()).equals ("") &&
          !String.valueOf (ed.getNR_Codigo_Cliente ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Codigo_Cliente = '" + ed.getNR_Codigo_Cliente () + "'";
      }

      if (String.valueOf (ed.getNR_Lote ()) != null &&
          !String.valueOf (ed.getNR_Lote ()).equals ("") &&
          !String.valueOf (ed.getNR_Lote ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Lote like '" + ed.getNR_Lote () + "%'";
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      if (ed.getDT_Emissao_Inicial () != null && !ed.getDT_Emissao_Inicial ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (ed.getDT_Emissao_Final () != null && !ed.getDT_Emissao_Final ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getDM_Situacao ()) != null &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("") &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("T") &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
        sql += " and Notas_Fiscais.DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }

      if (String.valueOf (ed.getOID_Coleta ()) != null &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("0") &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Coleta = " + ed.getOID_Coleta ();
        sql += " Order by Notas_Fiscais.nr_sequencia ";
      }
      else {
        sql += " Order by Notas_Fiscais.DM_Situacao, Notas_Fiscais.DT_Entrada asc, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Pessoa desc";
      }

      // // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean ();

      this.inicioTransacao ();
      this.executasql = this.sql;
      while (res.next ()) {
        Nota_FiscalED edVolta = new Nota_FiscalED ();

        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
        if (res.getDouble("vl_liquido_nota_fiscal")>0){
            edVolta.setVL_Nota_Fiscal (res.getDouble ("vl_liquido_nota_fiscal"));
        }

        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));

        double NR_Despacho = res.getDouble ("NR_Despacho");
        if (NR_Despacho > 0) {
          edVolta.setNR_Despacho (res.getLong ("NR_Despacho"));
        }

        String OID_Coleta = res.getString ("oid_Coleta");
        if (String.valueOf (OID_Coleta) != null && !String.valueOf (OID_Coleta).equals ("") && !String.valueOf (OID_Coleta).equals ("0") && !String.valueOf (OID_Coleta).equals (null)
            && !String.valueOf (OID_Coleta).equals ("null")) {
          edVolta.setOID_Coleta (res.getLong ("oid_Coleta"));
        }

        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        edVolta.setVL_Seguro (res.getDouble ("VL_Seguro"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
        edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());
        edVolta.setNM_Erro_Calculo (res.getString ("NM_Erro_Calculo"));

        edVolta.setNM_Calculo_Frete (" ");
        edVolta.setNR_Conhecimento ("");

        sql = " SELECT Conhecimentos_Notas_Fiscais.OID_Conhecimento_Nota_Fiscal, Conhecimentos_Notas_Fiscais.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.DM_Impresso, Conhecimentos.VL_Total_Frete from Conhecimentos_Notas_Fiscais, Conhecimentos ";
        sql += " WHERE  Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
        sql += " AND Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = '" + edVolta.getOID_Nota_Fiscal () + "'";

        ResultSet resTB = null;
        resTB = this.executasql.executarConsulta (sql);

        edVolta.setNR_Conhecimento ("");
        while (resTB.next ()) {
          edVolta.setNM_Calculo_Frete ("OK");
          edVolta.setVL_Total_Frete (resTB.getDouble ("VL_Total_Frete"));
          if (edVolta.getVL_Total_Frete () <= 0) {
            edVolta.setNM_Calculo_Frete ("N�O CALCULADO");
          }
          if (resTB.getString ("DM_Impresso").equals ("S")) {
            edVolta.setNM_Calculo_Frete ("IMPRESSO");
          }
          edVolta.setNR_Conhecimento (resTB.getString ("NR_Conhecimento"));
          edVolta.setOID_Conhecimento (resTB.getString ("OID_Conhecimento"));
        }
        if (edVolta.getNR_Conhecimento () != null && edVolta.getNR_Conhecimento ().equals ("") && res.getString ("NM_Erro_Calculo") != null && !res.getString ("NM_Erro_Calculo").equals ("null")) {
          edVolta.setNM_Calculo_Frete ("->> " + res.getString ("NM_Erro_Calculo"));
        }

        edVolta.setNM_Deposito (" ");

        if (res.getLong ("oid_deposito") > 0) {
          sql = " select NM_Deposito from Depositos where ";
          sql += " oid_deposito = " + res.getLong ("oid_deposito");

          ResultSet resTP = this.executasql.executarConsulta (sql);

          while (resTP.next ()) {
            edVolta.setNM_Deposito (resTP.getString ("NM_Deposito"));
          }
        }

        list.add (edVolta);
      }
      this.fimTransacao (true);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Nota_FiscalED ed)");
    }

    return list;
  }

  public ArrayList listaConsultaClienteSimples (Nota_FiscalED ed) throws Excecoes {

    // // // System.out.println("listaConsultaClienteSimples->>" + ed.getDM_Relatorio());
    if (ed.getDM_Relatorio()==null || ed.getDM_Relatorio().length()<5){
      ed.setDM_Relatorio ("RELATORIO_CONSULTA");
    }
    ArrayList list = new ArrayList ();
    String nr_Conhecimento="";
    String sql="";
    ResultSet res2 = null;
    int oid_Unidade_Anterior=0;
    String cd_Unidade="";
    String nm_remet="";
    String oid_Pessoa_Remet="", oid_Pessoa_Dest="";
	PessoaED pessoaRemetED = new PessoaED ();
	PessoaED pessoaDestED = new PessoaED ();

    try {


      ResultSet res = executaPesquisa (ed);

    // // // System.out.println("pesquisa OK");

      this.inicioTransacao ();
      this.executasql = this.sql;
      while (res.next ()) {
        Nota_FiscalED edVolta = new Nota_FiscalED ();

        if ("RESUMO".equals(ed.getDM_Relatorio().substring(0,6))||
            "RESUMO".equals(ed.getDM_Exportacao().substring(0,6))){
          // // // System.out.println("resumo por estado destino");

          edVolta.setNM_Destino(res.getString("NM_Classifica1"));
          edVolta.setVL_Total_Frete(res.getDouble("VL_Classifica1"));
          edVolta.setNR_Itens(res.getDouble("VL_Classifica2"));
          edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Classifica3"));
          edVolta.setNR_Peso(res.getDouble("VL_Classifica4"));
          edVolta.setNR_Itens2(res.getDouble("VL_Classifica6"));

          edVolta.setNR0(res.getDouble("D0"));
          edVolta.setNR1(res.getDouble("D1"));
          edVolta.setNR2(res.getDouble("D2"));
          edVolta.setNR3(res.getDouble("D3"));
          edVolta.setNR4(res.getDouble("D4"));
          edVolta.setNR5(res.getDouble("D5"));
          edVolta.setNR6(res.getDouble("D6"));
        }

        if ("RELATORIO".equals(ed.getDM_Relatorio().substring(0,9)) ||
            "EXPORTA".equals(ed.getDM_Exportacao().substring(0,7))){  //rel analitico

          //setavar
          // // System.out.println("p1");

          edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
          edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
          edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
          if (res.getString ("NR_Nota_Fiscal") == null || res.getString ("NR_Nota_Fiscal").equals("null")) {
            edVolta.setNR_NF (" ");
          }
          else {
            edVolta.setNR_NF (res.getString ("NR_Nota_Fiscal"));
          }
          // // System.out.println("p2");

          edVolta.setNR_Peso (res.getDouble ("NR_Peso_NF"));
          edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso_NF"));
          edVolta.setNR_Volume_Nota_Fiscal (res.getDouble ("NR_Volumes"));
          edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
          edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

          DataFormatada.setDT_FormataData (res.getString ("DT_Emissao"));
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());


          dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_NF"));
          edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());

          dataFormatada.setDT_FormataData (res.getString ("DT_Entrada"));
          edVolta.setDT_Entrada (dataFormatada.getDT_FormataData ());

          edVolta.setNR_Conhecimento (" '' ");
          edVolta.setNR_AWB (" ");
          edVolta.setDM_Tipo_Pagamento (" ");
          edVolta.setNM_Origem (" '' ");
          edVolta.setNM_Destino (" '' ");
          edVolta.setNM_Pessoa_Entrega (" ");
          edVolta.setNM_Pessoa_Remetente (" '' ");
          edVolta.setNM_Pessoa_Destinatario (" '' ");
          edVolta.setUF_Origem (" ");
          edVolta.setUF_Destino (" ");
          edVolta.setNM_Centro_Custo(" ");
          edVolta.setOid_Pessoa (" ");
          edVolta.setNM_Remetente (" ");
          edVolta.setNM_Cidade_Origem (" ");
          edVolta.setNM_Estado_Origem (" ");
          edVolta.setNM_Destinatario (" ");
          edVolta.setNM_Cidade_Destino (" ");
          edVolta.setNM_Estado_Destino (" ");
          edVolta.setDT_Entrega (" ");
          edVolta.setNR_Placa (" ");
          edVolta.setNR_Fatura (" ");
          edVolta.setNM_Tipo_Veiculo (" ");
          edVolta.setNM_Natureza (res.getString ("NM_Natureza"));

          edVolta.setDT_Coleta ("");
          edVolta.setHR_Coleta ("");
          edVolta.setDT_Entrega ("");
          edVolta.setHR_Entrega ("");
          edVolta.setNM_Cia_Aerea ("");

          edVolta.setNR_Fatura ("");

          if (oid_Unidade_Anterior != res.getInt ("oid_Unidade")) {
            sql = " SELECT CD_Unidade FROM Unidades " +
                " WHERE  Unidades.oid_Unidade =" + res.getLong ("oid_Unidade");
            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              cd_Unidade = res2.getString ("CD_Unidade");
            }
            oid_Unidade_Anterior = res.getInt ("oid_Unidade");
          }
          edVolta.setCD_Unidade (cd_Unidade);

          //cto
          if (!nr_Conhecimento.equals(res.getString ("NR_Conhecimento"))){
            String nm_Tipo_Documento = "CTO";
            if ("M".equals (res.getString ("DM_Tipo_Documento"))) {
              nm_Tipo_Documento = "MIN";
            }
            if ("N".equals (res.getString ("DM_Tipo_Documento"))) {
              nm_Tipo_Documento = "NFS";
            }

            if (res.getString ("NR_Conhecimento") == null || res.getString ("NR_Conhecimento").equals ("") || res.getString ("NR_Conhecimento").equals ("null")) {
              edVolta.setNR_Conhecimento (" ");
            }
            else {
              edVolta.setNR_Conhecimento (nm_Tipo_Documento + "-" + edVolta.getCD_Unidade() + "-" + res.getString ("NR_Conhecimento"));
            }

          // // System.out.println("p5");
            edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));

            if (res.getString ("NR_AWB") == null || res.getString ("NR_AWB").equals ("") || res.getString ("NR_AWB").equals ("null")) {
              edVolta.setNR_AWB (" ");
            }
            else {
              edVolta.setNR_AWB (res.getString ("NR_AWB"));
            }

            edVolta.setDM_Tipo_Pagamento ("CIF");
            if (!"1".equals (res.getString ("DM_Tipo_Pagamento"))) {
              edVolta.setDM_Tipo_Pagamento ("FOB");
            }
          // // System.out.println("p6");

            edVolta.setOid_Pessoa_Pagador (res.getString ("oid_pessoa_pagador"));
            edVolta.setOid_Pessoa_Destinatario (res.getString ("Oid_Pessoa_Destinatario"));
            edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));

            if (!oid_Pessoa_Remet.equals(res.getString ("oid_pessoa"))){
            	pessoaRemetED = new PessoaBD (executasql).getByRecord (res.getString ("oid_pessoa"));
            }
            oid_Pessoa_Remet=res.getString ("oid_pessoa");
            edVolta.setNR_CNPJ_CPF_Remetente (pessoaRemetED.getNR_CNPJ_CPF ());
            edVolta.setNM_Remetente (pessoaRemetED.getNM_Razao_Social ());
            edVolta.setNM_Pessoa_Remetente (pessoaRemetED.getNM_Razao_Social ());
            edVolta.setNM_Inscricao_Estadual_Remetente ("|" + pessoaRemetED.getNM_Inscricao_Estadual ());
            edVolta.setNM_Cidade_Origem (pessoaRemetED.getNM_Cidade ());
            edVolta.setNM_Estado_Origem (pessoaRemetED.getCD_Estado ());
            edVolta.setUF_Origem(pessoaRemetED.getCD_Estado ());
            edVolta.setNM_Origem (pessoaRemetED.getCD_Estado () + "/" + (pessoaRemetED.getNM_Cidade () + "                   ").substring (0 , 20));
            nm_remet=edVolta.getNM_Razao_Social()+"                                    ";

            if (!oid_Pessoa_Dest.equals(res.getString ("oid_pessoa_destinatario"))){
            	pessoaDestED = new PessoaBD (executasql).getByRecord (res.getString ("oid_pessoa_destinatario"));
            }
            oid_Pessoa_Dest=res.getString ("oid_pessoa_destinatario");

            edVolta.setNR_CNPJ_CPF_Destinatario (pessoaDestED.getNR_CNPJ_CPF ());
            edVolta.setNM_Destinatario (pessoaDestED.getNM_Razao_Social ());
            edVolta.setNM_Pessoa_Destinatario (pessoaDestED.getNM_Razao_Social ());
            edVolta.setNM_Inscricao_Estadual_Destinatario ("|" + pessoaDestED.getNM_Inscricao_Estadual ());
            edVolta.setNM_Cidade_Destino (pessoaDestED.getNM_Cidade ());
            edVolta.setNM_Destino (pessoaDestED.getNM_Cidade ());
            edVolta.setNM_Estado_Destino (pessoaDestED.getCD_Estado ());
            edVolta.setUF_Destino(pessoaDestED.getCD_Estado ());
            edVolta.setOID_Cidade_Destino(res.getInt("OID_Cidade_Destino"));

            edVolta.setNM_Pessoa_Entrega ("---");
            if (res.getString ("NM_Pessoa_Entrega") != null && res.getString ("NM_Pessoa_Entrega").length () > 4) {
              edVolta.setNM_Pessoa_Entrega (res.getString ("NM_Pessoa_Entrega"));
            }


            if (res.getLong("oid_Centro_Custo")>0){
              sql = "SELECT NM_Centro_Custo FROM Centros_Custos WHERE Centros_Custos.oid_Centro_Custo =" + res.getLong ("oid_Centro_Custo");
              res2 = this.executasql.executarConsulta (sql);
              while (res2.next ()) {
                edVolta.setNM_Centro_Custo(res2.getString("NM_Centro_Custo"));
              }
            }
            // // System.out.println("p7");

            edVolta.setNM_Natureza (res.getString ("NM_Natureza"));
            edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

            edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso"));
            edVolta.setNR_Peso_Cubado (res.getDouble ("NR_Peso"));
            if (res.getDouble ("NR_Peso_Cubado") > res.getDouble ("NR_Peso")) {
              edVolta.setNR_Peso_Cubado (res.getDouble ("NR_Peso_Cubado"));
            }
          // // System.out.println("p8");

            edVolta.setNR_Volumes_Conhecimento (res.getDouble ("NR_Volumes_Conhecimento"));


            dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_CTO"));
            edVolta.setDT_Emissao_Conhecimento (dataFormatada.getDT_FormataData ());

          // // System.out.println("p9");

            if (res.getString ("NR_Pedido") != null && !res.getString ("NR_Pedido").equals ("null")) {
              edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
            }

            if (res.getString ("NR_Codigo_Cliente") != null && !res.getString ("NR_Codigo_Cliente").equals ("null")) {
              edVolta.setNR_Codigo_Cliente (res.getString ("NR_Codigo_Cliente"));
            }

            if (res.getString ("DM_Cia_Aerea") != null && !res.getString ("DM_Cia_Aerea").equals ("null")) {
              edVolta.setNM_Cia_Aerea (res.getString ("DM_Cia_Aerea"));
            }

            edVolta.setCD_CFO_Conhecimento (res.getString ("cd_cfo_conhecimento"));


            edVolta.setVL_Frete (res.getDouble ("Vl_total_frete"));
            edVolta.setVL_Frete_Peso (res.getDouble ("Vl_frete_peso"));
            edVolta.setVL_Frete_Valor (res.getDouble ("Vl_frete_valor"));
            edVolta.setVL_Pedagio (res.getDouble ("VL_Pedagio"));
            edVolta.setVL_Despacho (res.getDouble ("VL_Despacho"));
            edVolta.setVL_Sec_Cat (res.getDouble ("VL_Sec_Cat"));
            edVolta.setVL_Ademe (res.getDouble ("VL_Outros1") + res.getDouble ("VL_Outros2") + res.getDouble ("VL_Outros3") + res.getDouble ("VL_Outros4") + res.getDouble ("VL_Outros5"));
            edVolta.setVL_Coleta (res.getDouble ("VL_OUTROS3") + res.getDouble ("VL_OUTROS4") + res.getDouble ("VL_OUTROS5"));
            edVolta.setVL_Entrega (res.getDouble ("VL_OUTROS1") + res.getDouble ("VL_OUTROS6") + res.getDouble ("VL_OUTROS7") + res.getDouble ("VL_OUTROS8"));
            edVolta.setVL_Base_Calculo_ICMS (res.getDouble ("VL_Base_Calculo_ICMS"));
            edVolta.setVL_ICMS (res.getDouble ("VL_ICMS"));
            edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_Aliquota_ICMS"));
          // // System.out.println("p12");

            if ("M1".equals (ed.getDM_Relatorio ())) {
              sql = " SELECT Veiculos.NR_Placa, nm_modelo_veiculo FROM Manifestos, Viagens, Modelos_Veiculos, Veiculos " +
                  " WHERE  Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
                  " AND    Manifestos.oid_Veiculo = Veiculos.oid_Veiculo " +
                  " AND    Modelos_Veiculos.oid_modelo_veiculo = Veiculos.oid_modelo_veiculo " +
                  " AND    Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
                  " ORDER BY Veiculos.oid_Veiculo ";

              res2 = this.executasql.executarConsulta (sql);
              if (res2.next ()) {
                edVolta.setNR_Placa (res2.getString ("NR_Placa"));
                edVolta.setNM_Tipo_Veiculo (res2.getString ("nm_modelo_veiculo"));
              }
            }
          // // System.out.println("p13");

            if ("M2".equals (ed.getDM_Relatorio ()) && res.getLong ("oid_Modal") > 0) {
              sql = " SELECT CD_Modal FROM Modal " +
                  " WHERE  Modal.oid_Modal =" + res.getLong ("oid_Modal");
              res2 = this.executasql.executarConsulta (sql);
              if (res2.next ()) {
                edVolta.setCD_Modal (res2.getString ("CD_Modal"));
              }
            }

            if ("M2".equals (ed.getDM_Relatorio ()) && res.getLong ("oid_Produto") > 0) {
              sql = " SELECT NM_Produto FROM Produtos " +
                  " WHERE  Produtos.oid_Produto =" + res.getLong ("oid_Produto");
              res2 = this.executasql.executarConsulta (sql);
              if (res2.next ()) {
                edVolta.setNM_Produto (res2.getString ("NM_Produto"));
              }
            }

          }
          // // System.out.println("p14");


          sql = " SELECT Documentos_Lotes_Faturamentos.oid_Lote_Faturamento " +
          		" FROM   Documentos_Lotes_Faturamentos " +
           	    " WHERE  Documentos_Lotes_Faturamentos.oid_conhecimento='" + res.getString ("oid_Conhecimento") + "'";

	      res2 = this.executasql.executarConsulta (sql);
	      if (res2.next ()) {
	    	  edVolta.setOID_Lote(res2.getLong("oid_Lote_Faturamento"));
	      }

	      edVolta.setNM_Ocorrencia(" ");

          sql = " SELECT Ocorrencias_Conhecimentos.Tx_observacao, Tipos_Ocorrencias.NM_Tipo_Ocorrencia " +
          	    " FROM   Ocorrencias_Conhecimentos, Tipos_Ocorrencias " +
          	    " WHERE  Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.oid_Tipo_Ocorrencia " +
          	    " AND    Tipos_Ocorrencias.DM_Tipo <>'W' " +
          	    " AND    Tipos_Ocorrencias.DM_Tipo <>'P' " +
          	    " AND    Tipos_Ocorrencias.DM_Tipo <>'A' " +
          	    " AND    Tipos_Ocorrencias.DM_Tipo <>'D' " +
          	    " AND    Ocorrencias_Conhecimentos.OID_Conhecimento = '" + edVolta.getOID_Conhecimento () + "'" +
          	    " ORDER  BY Ocorrencias_Conhecimentos.OID_Ocorrencia_Conhecimento ";
          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
        	edVolta.setNM_Ocorrencia(res2.getString ("NM_Tipo_Ocorrencia"));
        	if (res2.getString ("Tx_observacao") != null && res2.getString ("Tx_observacao").length()>4) {
                edVolta.setNM_Ocorrencia (res2.getString ("NM_Tipo_Ocorrencia") + "-"+res2.getString ("Tx_observacao"));
        	}
          }

          sql = " SELECT NM_Razao_Social, NR_Master FROM Viagens, Ordens_Manifestos, Ordens_Fretes, Pessoas " +
              " WHERE  Ordens_Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
              " AND    Ordens_Manifestos.oid_Ordem_Frete = Ordens_Fretes.oid_Ordem_Frete " +
              " AND    Ordens_Fretes.oid_Pessoa = Pessoas.oid_Pessoa " +
              " AND    Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
              " ORDER BY Viagens.dt_viagem ";

          // // System.out.println (sql);

          res2 = this.executasql.executarConsulta (sql);
          if (res2.next ()) {
            edVolta.setNM_Cia_Aerea (res2.getString ("NM_Razao_Social"));
            edVolta.setNR_AWB (res2.getString ("NR_Master"));
          }

          nr_Conhecimento = res.getString ("NR_Conhecimento");
          // // System.out.println("p15");

          DataFormatada.setDT_FormataData (res.getString ("DT_Previsao_Entrega"));
          edVolta.setDT_Previsao_Entrega (DataFormatada.getDT_FormataData ());
          edVolta.setHR_Previsao_Entrega (res.getString ("HR_Previsao_Entrega"));
          DataFormatada.setDT_FormataData (res.getString ("DT_Entrega"));
          edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
          edVolta.setHR_Entrega (res.getString ("HR_Entrega"));

          edVolta.setDM_Tracking(res.getString ("DM_Tracking"));
          edVolta.setNR_Dias_Entrega_Realizado(res.getInt("NR_Dias_Entrega_Realizado"));
          edVolta.setNR_Dias_Entrega_Previsto(res.getInt("NR_Dias_Entrega_Previsto"));
          edVolta = this.indicadores_Entrega (edVolta);

          // // System.out.println("p16");

          if (nm_remet.substring (0 , 4).equals ("DANA")) {
            edVolta.setDT_Previsao_Entrega ("IMEDIATA");
            edVolta.setHR_Previsao_Entrega (" ");
            if (res.getString ("DT_Entrega") == null || res.getString ("DT_Entrega").length () < 5) {
              edVolta.setDT_Entrega (" ");
              edVolta.setHR_Entrega (" ");
              edVolta.setNR_Dias_Realizado_Entrega (" ");
            }
          }


          double NR_Despacho = res.getDouble ("NR_Despacho");
          if (NR_Despacho > 0) {
            edVolta.setNR_Despacho (res.getLong ("NR_Despacho"));
          }

          // // System.out.println("p17");

          if (res.getLong ("NR_Postagem") > 0) {
            edVolta.setNR_Postagem (res.getLong ("NR_Postagem"));
            edVolta.setDM_Tipo_Postagem(res.getString("DM_Tipo_Postagem"));
          }

          String OID_Coleta = res.getString ("oid_Coleta");
          if (String.valueOf (OID_Coleta) != null && !String.valueOf (OID_Coleta).equals ("") && !String.valueOf (OID_Coleta).equals ("0") && !String.valueOf (OID_Coleta).equals (null)
              && !String.valueOf (OID_Coleta).equals ("null")) {
            edVolta.setOID_Coleta (res.getLong ("oid_Coleta"));
          }

          edVolta.setNR_Pedido (" ");

          String NR_Pedido = res.getString ("NR_Pedido");
          if (String.valueOf (NR_Pedido) != null && !String.valueOf (NR_Pedido).equals ("") && !String.valueOf (NR_Pedido).equals ("0") && !String.valueOf (NR_Pedido).equals (null)
              && !String.valueOf (NR_Pedido).equals ("null")) {
            edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
          }

          // // System.out.println("p20");

          edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
          edVolta.setDM_Tipo_Documento (res.getString ("DM_Tipo_Documento"));
          edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
          edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());
        }
        list.add (edVolta);
      }
      this.fimTransacao (true);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Nota_FiscalED ed)");
    }

    return list;
  }

  public ArrayList listaConsultaCliente (Nota_FiscalED ed) throws Excecoes {
    boolean notaEmArmazem = false;
    ArrayList list = new ArrayList ();
    String sql =
        " select Conhecimentos.NR_Conhecimento, " +
        "        Conhecimentos.OID_Conhecimento, " +
        "        Conhecimentos.DT_Previsao_entrega, " +
        "        Conhecimentos.HR_Previsao_Entrega, " +
        "        Conhecimentos.NR_Lote, " +
        "        Notas_Fiscais.DM_Situacao, " +
        "        Notas_Fiscais.NR_Peso, " +
        "        Notas_Fiscais.NR_Volumes, " +
        "        Notas_Fiscais.VL_Nota_Fiscal, " +
        "        Notas_Fiscais.DT_Emissao, " +
        "        Notas_Fiscais.NR_Nota_Fiscal, " +
        "        Notas_Fiscais.NR_Despacho, " +
        "        Notas_Fiscais.OID_Coleta, " +
        "        Notas_Fiscais.OID_Nota_Fiscal, " +
        "        Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
        "        Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario," +
        "        Pessoa_Remetente.NM_Fantasia as NM_Fantasia_Remetente, " +
        "        Pessoa_Destinatario.NM_Fantasia as NM_Fantasia_Destinatario," +
        "        Itens_Notas_Fiscais.CD_Referencia," +
        "        Itens_Notas_Fiscais.NR_Volumes as NR_Volumes_Item " +
        " from  Notas_Fiscais " +
        "       left join Itens_Notas_Fiscais " +
        "         on Notas_Fiscais.oid_nota_fiscal = Itens_Notas_Fiscais.oid_nota_fiscal" +
        "       inner join Pessoas Pessoa_Remetente ";
    if (JavaUtil.doValida (ed.getOID_Pessoa ())) {
      sql +=
          "     inner join Clientes " +
          "       on Pessoa_Remetente.oid_Pessoa = Clientes.oid_Pessoa ";
    }
    sql += "      on Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
        "       inner join Pessoas Pessoa_Destinatario " +
        "         on Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
    if (JavaUtil.doValida (ed.getOID_Pessoa_Destinatario ())) {
      sql +=
          "     inner join Clientes " +
          "       on Pessoa_Destinatario.oid_Pessoa = Clientes.oid_Pessoa ";
    }
    sql +=
        "       inner join Conhecimentos_Notas_Fiscais" +
        "         inner join Conhecimentos " +
        "           on Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
        "         on Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
        " where 1=1 ";
    if (JavaUtil.doValida (ed.getCD_Acesso ())) {
      sql += " and Clientes.CD_Acesso = '" + ed.getCD_Acesso () + "'";
    }
    else {
      throw new Excecoes ("C�digo de acesso n�o informado!");
    }
    if (ed.getNR_Despacho () > 0) {
      sql += " and Notas_Fiscais.NR_Despacho = " + ed.getNR_Despacho ();
    }
    if (ed.getNR_Nota_Fiscal () > 0) {
      sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
    }
    if (JavaUtil.doValida (ed.getNR_Pedido ())) {
      sql += " and Notas_Fiscais.NR_Pedido = '" + ed.getNR_Pedido () + "'";
    }
    if (JavaUtil.doValida (ed.getNR_Lote ())) {
      sql += " and Conhecimentos.NR_Lote = '" + ed.getNR_Lote () + "'";
    }
    if (JavaUtil.doValida (ed.getOID_Pessoa ())) {
      sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    else {
      sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }
    if (JavaUtil.doValida (ed.getOID_Pessoa_Consignatario ())) {
      sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
    }
    if (JavaUtil.doValida (ed.getDT_Emissao ())) {
      sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
    }
    else {
      sql += " and Notas_Fiscais.DT_Emissao > '01/01/2004'";
    }
    if (JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
      sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
    }
    if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
      sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
    }
    if (JavaUtil.doValida (ed.getCD_Referencia ())) {
      sql += " and Itens_Notas_Fiscais.CD_Referencia = '" + ed.getCD_Referencia () + "'";
    }
    if ("E".equals (ed.getDM_Situacao ())) {
      sql += " and not Conhecimentos.DT_Entrega is null ";
    }
    else if ("P".equals (ed.getDM_Situacao ())) {
      sql += " and Conhecimentos.DT_Entrega is null ";
    }
    if (JavaUtil.doValida (ed.getOID_Pessoa ())) {
      sql += " Order by Pessoa_Destinatario.NM_Fantasia ";
    }
    else {
      sql += " Order by Pessoa_Remetente.NM_Fantasia ";
    }
    sql += " ,Itens_Notas_Fiscais.CD_Referencia, Notas_Fiscais.DT_Entrada asc, Notas_Fiscais.NR_Nota_Fiscal";

    ResultSet res = this.executasql.executarConsulta (sql);
    try {
      while (res.next ()) {

        RastreamentoED edVolta = new RastreamentoED ();

        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        edVolta.setNR_Lote (res.getString ("NR_Lote"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));

        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        double NR_Despacho = res.getDouble ("NR_Despacho");
        if (NR_Despacho > 0) {
          edVolta.setNR_Despacho (res.getLong ("NR_Despacho"));
        }

        // // // System.out.println ("r1 ");

        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));

        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));

        if (JavaUtil.doValida (res.getString ("NM_Fantasia_Remetente"))) {
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Fantasia_Remetente"));
        }

        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        if (JavaUtil.doValida (res.getString ("NM_Fantasia_Destinatario"))) {
          edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Fantasia_Destinatario"));
        }
        // // // System.out.println ("r2 ");

        edVolta.setDT_Emissao (FormataData.formataDataBT (res.getDate ("DT_Emissao")));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setCD_Referencia (JavaUtil.getValueDef (res.getString ("CD_Referencia") , "NF sem itens"));

        if (JavaUtil.doValida (res.getString ("CD_Referencia"))) {
          ReferenciaED filtro = new ReferenciaED ();
          filtro.setCD_Referencia (res.getString ("CD_Referencia"));
          ReferenciaED referencia = new ReferenciaBD (executasql).getByRecord (filtro);
          edVolta.setNM_Produto (referencia.getNM_Referencia ());
        }
        edVolta.setNR_Volumes_Item (res.getLong ("NR_Volumes_Item"));

        // // // System.out.println ("r3 ");

        String sqlManifesto =
            " select Manifestos.DT_Emissao, " +
            "        Manifestos.HR_Saida, " +
            "        Manifestos.DT_Previsao_Chegada, " +
            "        Manifestos.HR_Previsao_Chegada, " +
            "        Veiculos.NR_Rastreador, " +
            "        Pessoa_Motorista.NM_Razao_Social as NM_Razao_Social_Motorista " +
            " from Viagens, " +
            "      Manifestos, " +
            "      Pessoas Pessoa_Motorista, " +
            "      Veiculos " +
            " where Viagens.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "' " +
            "   and Viagens.oid_Manifesto = Manifestos.oid_Manifesto " +
            "   and Manifestos.oid_Pessoa = Pessoa_Motorista.oid_Pessoa " +
            "   and Manifestos.oid_Veiculo = Veiculos.oid_Veiculo ";
        if (JavaUtil.doValida (ed.getDT_Saida_Inicial ())) {
          sqlManifesto += " and Manifestos.DT_Emissao >= " + getSQLString (ed.getDT_Saida_Inicial ());
        }
        if (JavaUtil.doValida (ed.getDT_Saida_Final ())) {
          sqlManifesto += " and Manifestos.DT_Emissao <= " + getSQLString (ed.getDT_Saida_Final ());
        }

        ResultSet resManifesto = executasql.executarConsulta (sqlManifesto);
        try {
          if (resManifesto.next ()) {
            edVolta.setDT_Entrada (FormataData.formataDataBT (resManifesto.getDate ("DT_Emissao")));
            edVolta.setHR_Entrada (resManifesto.getString ("HR_Saida"));
            edVolta.setDT_Previsao_Chegada (FormataData.formataDataBT (resManifesto.getDate ("DT_Previsao_Chegada")));
            edVolta.setHR_Previsao_Chegada (resManifesto.getString ("HR_Previsao_Chegada"));
            edVolta.setNM_Motorista (resManifesto.getString ("NM_Razao_Social_Motorista"));

            // // // System.out.println ("r4 ");

            long nrRastreador = Long.parseLong (JavaUtil.getStringNotNull (resManifesto.getString ("NR_Rastreador") , "0"));
            String sqlRastreador =
                " select p1.* " +
                " from Positionhistory_Iipos p1 " +
                " where p1.iipos_mctaddress = " + nrRastreador +
                "   and p1.iipos_timeposition = (select max(p2.iipos_timeposition) " +
                "                                from Positionhistory_Iipos p2 " +
                "                                where p2.iipos_mctaddress = " + nrRastreador + ") ";
            ResultSet resRastreador = executasql.executarConsulta (sqlRastreador);
            try {

              // // // System.out.println ("r5 ");

              if (resRastreador.next ()) {

            	String Data = FormataData.formataDataBT (resRastreador.getDate ("iipos_timeposition"));
            	String Hora = resRastreador.getString("iipos_timeposition").substring(11,19);
                edVolta.setNM_Referencia_Rastreamento (resRastreador.getString ("iipos_landmark") + " - " + Data + " - " + Hora);
                edVolta.setNr_latitude (resRastreador.getDouble ("iipos_latitude"));
                edVolta.setNr_longitude (resRastreador.getDouble ("iipos_longitude"));
              }
              else {

                // // // System.out.println ("r7 ");

                sqlRastreador = " select p1.* " +
                    " from Positionhistory_Iipos p1 " +
                    " where p1.oid_nota_fiscal ='" + res.getString ("OID_Nota_Fiscal") + "'" +
                    "   and p1.iipos_timeposition = (select max(p2.iipos_timeposition) " +
                    "                                from Positionhistory_Iipos p2 " +
                    "                                where p2.oid_nota_fiscal = '" + res.getString ("OID_Nota_Fiscal") + "')";

                // // // System.out.println (sqlRastreador);

                resRastreador = executasql.executarConsulta (sqlRastreador);
                if (resRastreador.next ()) {
                  String Data = FormataData.formataDataBT (resRastreador.getDate ("iipos_timeposition"));
                  String Hora = resRastreador.getString("iipos_timeposition").substring(11,19);
                  edVolta.setNM_Referencia_Rastreamento (resRastreador.getString ("iipos_landmark") + " - " + Data + " - " + Hora);
                  edVolta.setNr_latitude (resRastreador.getDouble ("iipos_latitude"));
                  edVolta.setNr_longitude (resRastreador.getDouble ("iipos_longitude"));
                }
                else {
                  edVolta.setNM_Referencia_Rastreamento ("---");
                }
              }

            }
            finally {
              closeResultset (resRastreador);
            }
            notaEmArmazem = false;
          }
          else {

            edVolta.setNM_Referencia_Rastreamento ("Armaz�m");
            edVolta.setDT_Entrada ("");
            edVolta.setHR_Entrada ("");
            edVolta.setDT_Previsao_Chegada ("");
            edVolta.setHR_Previsao_Chegada ("");
            edVolta.setNM_Motorista ("");
            notaEmArmazem = true;
          }
        }
        finally {
          closeResultset (resManifesto);
        }
        if (!notaEmArmazem) {
          list.add (edVolta);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "listaConsultaCliente(Nota_FiscalED ed)");
    }
    finally {
      closeResultset (res);
    }
    return list;
  }

  public ArrayList gera_Arquivo_Nota_Fiscal (Nota_FiscalED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " select NR_Peso, NR_Volumes, VL_Nota_Fiscal, DT_Emissao, NR_Nota_Fiscal, OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario ";
      sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0")) {
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      sql += " Order by Notas_Fiscais.NR_Nota_Fiscal ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        Nota_FiscalED edVolta = new Nota_FiscalED ();

        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        list.add (edVolta);
      }
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

  public Nota_FiscalED getByRecord (Nota_FiscalED ed) throws Excecoes {

    String sql = null;
    ResultSet resTP = null;
    int QT_Conhecimentos_Gerados = 0 , QT_Conhecimentos_OK = 0 , QT_Conhecimentos_Impressos = 0;
    double VL_Total_Frete = 0;

    Nota_FiscalED edVolta = new Nota_FiscalED ();

    try {

      sql = " select * from Notas_Fiscais where 1=1 ";
      if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      }
      if (String.valueOf (ed.getNM_Serie ()) != null &&
          !String.valueOf (ed.getNM_Serie ()).equals ("") &&
          !String.valueOf (ed.getNM_Serie ()).equals ("null")) {
        sql += " and Notas_Fiscais.NM_Serie = '" + ed.getNM_Serie () + "'";
      }
      if (String.valueOf (ed.getDM_Tipo_Conhecimento ()) != null &&
          !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("") &&
          !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("null")) {
        sql += " and Notas_Fiscais.DM_Tipo_Conhecimento = '" + ed.getDM_Tipo_Conhecimento () + "'";
      }
      if (String.valueOf (ed.getDM_Tipo_Conhecimento ()) != null &&
          String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("4") && ///REENTREGA
          !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("") &&
          !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("null")) {
        sql += " and 1=2 "; ///REENTREGA
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Notas_Fiscais.oid_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (ed.getNR_Nota_Fiscal () > 0) {
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }
      if (String.valueOf (ed.getNR_Despacho ()) != null &&
          !String.valueOf (ed.getNR_Despacho ()).equals ("0")) {
        sql += " and Notas_Fiscais.NR_Despacho = " + ed.getNR_Despacho ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Tipo_Conhecimento (res.getString ("DM_Tipo_Conhecimento"));
        edVolta.setHR_Entrada (res.getString ("HR_Entrada"));
        edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
        edVolta.setNR_Codigo_Cliente (res.getString ("NR_Codigo_Cliente"));
        edVolta.setNR_Lote (res.getString ("NR_Lote"));
        edVolta.setNM_Serie (res.getString ("NM_Serie"));
        edVolta.setDM_Transferencia (res.getString ("DM_Transferencia"));
        edVolta.setDM_Exportacao (res.getString ("DM_Exportacao"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
        edVolta.setDM_Exportacao (res.getString ("DM_Exportacao"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Cubagem (res.getDouble ("NR_Cubagem"));
        edVolta.setNR_Cubagem_Total (res.getDouble ("NR_Cubagem_Total"));

        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Peso_Cubado (res.getDouble ("NR_Peso_Cubado"));
        edVolta.setNR_Itens (res.getDouble ("NR_Itens"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        double NR_Despacho = res.getDouble ("NR_Despacho");
        if (NR_Despacho > 0) {
          edVolta.setNR_Despacho (res.getLong ("NR_Despacho"));
        }

        edVolta.setVL_Seguro (res.getDouble ("VL_Seguro"));

        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setNR_Volumes_Itens(res.getLong("NR_Volumes"));

        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
        edVolta.setOID_Motorista (res.getString ("OID_Motorista"));
        edVolta.setOID_Modal (res.getLong ("OID_Modal"));
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
        edVolta.setOID_Produto (res.getLong ("OID_Produto"));
        edVolta.setOID_Coleta (res.getLong ("OID_Coleta"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Consignatario (res.getString ("OID_Pessoa_Consignatario"));
        edVolta.setOID_Pessoa_Entregadora (res.getString ("OID_Pessoa_Entregadora"));
        edVolta.setOID_Pessoa_Redespacho (res.getString ("OID_Pessoa_Redespacho"));
        edVolta.setOID_Natureza_Operacao (res.getLong ("OID_NATUREZA_OPERACAO"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

        if (res.getDouble("vl_liquido_nota_fiscal")>0){
            edVolta.setVL_Nota_Fiscal (res.getDouble ("vl_liquido_nota_fiscal"));
        }

        edVolta.setVL_Custo_Descarga (res.getDouble ("VL_Custo_Descarga"));
        edVolta.setOid_Deposito (res.getLong ("OID_Deposito"));
        edVolta.setOID_Ordem_Carga (res.getLong ("OID_Ordem_Carga"));
        edVolta.setOID_Ordem_Descarga (res.getLong ("OID_Ordem_Descarga"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setDT_Descarga_Deposito (res.getString ("DT_Descarga_Deposito"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Descarga_Deposito ());
        edVolta.setDT_Descarga_Deposito (DataFormatada.getDT_FormataData ());

        edVolta.setDT_Carga_Deposito (res.getString ("DT_Carga_Deposito"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Carga_Deposito ());
        edVolta.setDT_Carga_Deposito (DataFormatada.getDT_FormataData ());
        edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
        edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

        if (edVolta.getOID_Natureza_Operacao () > 0) {
          sql = " select * from Naturezas_Operacoes where";
          sql += " OID_NATUREZA_OPERACAO = " + edVolta.getOID_Natureza_Operacao ();

          resTP = this.executasql.executarConsulta (sql);

          while (resTP.next ()) {
            edVolta.setNM_Natureza_Operacao (resTP.getString ("NM_Natureza_Operacao"));
            edVolta.setCD_Natureza_Operacao (resTP.getString ("CD_Natureza_Operacao"));
          }
        }

        sql = " select  VL_Total_Frete , DM_Impresso, NR_Conhecimento ";
        sql += " FROM   Conhecimentos_Notas_Fiscais, Conhecimentos  ";
        sql += " WHERE  Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento ";
        sql += " and Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";

        resTP = this.executasql.executarConsulta (sql);

        edVolta.setNR_Conhecimento ("");
        while (resTP.next ()) {
          QT_Conhecimentos_Gerados++;
          edVolta.setNR_Conhecimento (resTP.getString ("NR_Conhecimento"));

          VL_Total_Frete = resTP.getDouble ("VL_Total_Frete");
          if (VL_Total_Frete > 0) {
            QT_Conhecimentos_OK++;
          }
          if (resTP.getString ("DM_Impresso").equals ("S")) {
            QT_Conhecimentos_Impressos++;
          }
        }

        // // // System.out.println ("CONSULTA NF 1");

        edVolta.setQT_Conhecimentos_Gerados (QT_Conhecimentos_Gerados);
        edVolta.setQT_Conhecimentos_OK (QT_Conhecimentos_OK);
        edVolta.setQT_Conhecimentos_Impressos (QT_Conhecimentos_Impressos);

        edVolta.setNM_Pessoa_Entrega (" ");
        edVolta.setDT_Previsao_Entrega (" ");
        edVolta.setHR_Previsao_Entrega (" ");
        edVolta.setDT_Entrega (" ");
        edVolta.setHR_Entrega (" ");
        edVolta.setNR_Postagem (0);

        sql = " Select  Conhecimentos.NR_Postagem, Conhecimentos.DM_Tipo_Postagem, Conhecimentos.DT_Emissao, Conhecimentos.DT_Previsao_Entrega, Conhecimentos.HR_Previsao_Entrega, Conhecimentos.DT_Entrega, Conhecimentos.HR_Entrega, Conhecimentos.NM_Pessoa_Entrega " +
            " FROM   Conhecimentos, Conhecimentos_Notas_Fiscais " +
            " WHERE Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_Conhecimento " +
            " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = '" + res.getString ("OID_Nota_Fiscal") + "'";

        // // // System.out.println (sql);

        ResultSet res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {

          // // // System.out.println ("DT_Previsao_Entrega->" + res2.getString ("DT_Previsao_Entrega"));
          // // // System.out.println ("DT_Entrega->" + res2.getString ("DT_Entrega"));
          // // // System.out.println ("NR_Postagem->" + res2.getLong ("NR_Postagem"));

          if (res2.getString ("NM_Pessoa_Entrega") != null && !res2.getString ("NM_Pessoa_Entrega").equals ("null")) {
            edVolta.setNM_Pessoa_Entrega (res2.getString ("NM_Pessoa_Entrega"));
          }
          if (res2.getLong ("NR_Postagem") > 0) {
            edVolta.setNR_Postagem (res2.getLong ("NR_Postagem"));
            edVolta.setDM_Tipo_Postagem(res2.getString("DM_Tipo_Postagem"));
          }

          int nr_Dias_Entrega = 0;
          int nr_Dias_Previsto_Entrega = 0;
          int nr_Dias_Realizado_Entrega = 0;
          edVolta.setNR_Dias_Previsto_Entrega (" ");
          edVolta.setNR_Dias_Entrega (" ");
          edVolta.setNR_Dias_Realizado_Entrega ("Pend.");

          if (res2.getString ("DT_Previsao_Entrega") == null || res2.getString ("DT_Previsao_Entrega").length () < 5) {
            edVolta.setDT_Previsao_Entrega (" ");
            edVolta.setHR_Previsao_Entrega (" ");
          }
          else {
            edVolta.setDT_Previsao_Entrega (res2.getString ("DT_Previsao_Entrega"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Entrega ());
            edVolta.setDT_Previsao_Entrega (DataFormatada.getDT_FormataData ());
            edVolta.setHR_Previsao_Entrega (res2.getString ("HR_Previsao_Entrega"));

            try {
              nr_Dias_Previsto_Entrega = Data.diferencaDias (DataFormatada.getDT_FormataData (res2.getString ("DT_Previsao_Entrega")) , DataFormatada.getDT_FormataData (res2.getString ("DT_Emissao")));
              edVolta.setNR_Dias_Previsto_Entrega (String.valueOf (nr_Dias_Previsto_Entrega));
            }
            catch (Exception e) {
              throw new Excecoes (e.getClass () + ": " + e.getMessage () , e , this.getClass ().getName () , "calc prazo");
            }

            if (res2.getString ("DT_Entrega") == null || res2.getString ("DT_Entrega").length () < 5) {
              if (Data.comparaData (DataFormatada.getDT_FormataData (res2.getString ("DT_Previsao_Entrega")) , Data.getDataDMY ())) {
                edVolta.setDT_Entrega (" ");
                edVolta.setHR_Entrega (" ");
              }
              else {
                edVolta.setDT_Entrega (DataFormatada.getDT_FormataData (res2.getString ("DT_Previsao_Entrega")));
                edVolta.setHR_Entrega (" ");
              }
            }
            else {
              edVolta.setDT_Entrega (res2.getString ("DT_Entrega"));
              DataFormatada.setDT_FormataData (edVolta.getDT_Entrega ());
              edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
              edVolta.setHR_Entrega (res2.getString ("HR_Entrega"));
            }
            if (edVolta.getDT_Entrega () != null && edVolta.getDT_Entrega ().length () > 4) {
              try {
                nr_Dias_Entrega = Data.diferencaDias (edVolta.getDT_Entrega () , DataFormatada.getDT_FormataData (res2.getString ("DT_Emissao")));
                edVolta.setNR_Dias_Entrega (String.valueOf (nr_Dias_Entrega));
                nr_Dias_Realizado_Entrega = nr_Dias_Entrega - nr_Dias_Previsto_Entrega;
                if (nr_Dias_Realizado_Entrega > 0) {
                  edVolta.setNR_Dias_Realizado_Entrega (String.valueOf (nr_Dias_Realizado_Entrega));
                }
                else {
                  edVolta.setNR_Dias_Realizado_Entrega ("-OK-");
                }
              }
              catch (Exception e) {
                throw new Excecoes (e.getClass () + ": " + e.getMessage () , e , this.getClass ().getName () , "calc prazo");
              }
            }
          }
          // // // System.out.println ("edVolta->" + edVolta.getDT_Entrega ());

        }

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

  public Nota_FiscalED getByRecord_Conhecimento (Nota_FiscalED ed) throws Excecoes {

    String sql = null;

    Nota_FiscalED edVolta = new Nota_FiscalED ();

    try {

      sql = " select * from Notas_Fiscais where 1=1 ";

      if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("")) {
        sql += " and OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      }
      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0")) {
        sql += " and NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
        if (String.valueOf (ed.getNM_Serie ()) != null &&
            !String.valueOf (ed.getNM_Serie ()).equals ("null") &&
            !String.valueOf (ed.getNM_Serie ()).equals ("")) {
          sql += " and NM_Serie = '" + ed.getNM_Serie () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa ()) != null &&
                !String.valueOf (ed.getOID_Pessoa ()).equals ("null") &&
                !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
              sql += " and OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
      }

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setHR_Entrada (res.getString ("HR_Entrada"));
        edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
        edVolta.setNM_Serie (res.getString ("NM_Serie"));
        edVolta.setDM_Transferencia (res.getString ("DM_Transferencia"));
        edVolta.setDM_Exportacao (res.getString ("DM_Exportacao"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Cubagem (res.getDouble ("NR_Cubagem"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        edVolta.setNR_Despacho (res.getLong ("NR_Despacho"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setNR_Volumes_Itens(res.getLong("NR_Volumes"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Consignatario (res.getString ("OID_Pessoa_Consignatario"));
        edVolta.setOID_Natureza_Operacao (res.getLong ("OID_Natureza_Operacao"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
        if (res.getDouble("vl_liquido_nota_fiscal")>0){
            edVolta.setVL_Nota_Fiscal (res.getDouble ("vl_liquido_nota_fiscal"));
        }

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
        edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

        edVolta.setNR_Lote (res.getString ("NR_Lote"));
        sql = " select oid_conhecimento from Conhecimentos_Notas_Fiscais  " +
            " WHERE oid_nota_fiscal = '" + res.getString ("oid_Nota_Fiscal") + "'";
        res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {
          edVolta.setOID_Conhecimento (res2.getString ("oid_conhecimento"));
        }

      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord_Conhecimento(Nota_FiscalED ed)");
    }

    return edVolta;
  }

  public int notasMesmoNumero (Nota_FiscalED ed) throws Excecoes {
    String sql =
        " select count(*) as cont from Notas_Fiscais where 1=1 ";
    sql += " and OID_Nota_Fiscal <> '" + ed.getOID_Nota_Fiscal () + "'";
    sql += " and NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
    sql += " and oid_Pessoa = " + JavaUtil.getSQLString (ed.getOID_Pessoa ());
    int toReturn = 0;
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        toReturn = res.getInt ("cont");
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "notasMesmoNumero(Nota_FiscalED ed)");
    }
    return toReturn;
  }

  public ArrayList lista_Traking (Nota_FiscalED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    Nota_FiscalED ed = (Nota_FiscalED) edComp;

    try {

      sql = " select Notas_Fiscais.DM_Situacao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DT_Entrada, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario ";
      sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf (ed.getCD_Referencia ()) != null &&
          !String.valueOf (ed.getCD_Referencia ()).equals ("")) {
        sql =
            " select Itens_Notas_Fiscais.NR_Volumes as NR_Volumes_Item, Produtos.NM_Produto, Notas_Fiscais.DM_Situacao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DT_Entrada, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Referencias, Itens_Notas_Fiscais, Produtos ";
        sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal ";
        sql += " AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_Referencia ";
        sql += " AND Referencias.OID_Produto = Produtos.OID_Produto ";
        sql += " AND Referencias.CD_Referencia = '" + ed.getCD_Referencia () + "'";
      }

      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0")) {
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }

      if (ed.getDT_Emissao_Inicial () != null && !ed.getDT_Emissao_Inicial ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (ed.getDT_Emissao_Final () != null && !ed.getDT_Emissao_Final ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getDM_Situacao ()) != null &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("") &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
        sql += " and Notas_Fiscais.DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }

      sql += " Order by Notas_Fiscais.DM_Situacao, Notas_Fiscais.DT_Entrada asc, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Pessoa desc";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        Nota_FiscalED edVolta = new Nota_FiscalED ();
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
        edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

        if (String.valueOf (ed.getCD_Referencia ()) != null &&
            !String.valueOf (ed.getCD_Referencia ()).equals ("")) {

          edVolta.setNM_Produto (res.getString ("NM_Produto"));
          edVolta.setNR_Volumes_Itens (res.getLong ("NR_Volumes_Item"));

          sql = " SELECT Embarques.DM_Situacao, Embarques.NR_Placa, Embarques.DT_Nova_Previsao_Chegada, Embarques.HR_Nova_Previsao_Chegada, Embarques.DT_Previsao_Chegada, Embarques.HR_Previsao_Chegada, Embarques.NM_Motorista, Embarques.NR_Celular from Notas_Fiscais_Embarques, Notas_Fiscais, Embarques ";
          sql += " WHERE Notas_Fiscais_Embarques.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
          sql += " AND Notas_Fiscais_Embarques.OID_Embarque = Embarques.OID_Embarque ";
          sql += " AND Notas_Fiscais_Embarques.OID_Nota_Fiscal = '" + edVolta.getOID_Nota_Fiscal () + "'";

          ResultSet resTB = null;
          resTB = this.executasql.executarConsulta (sql);

          edVolta.setNR_Placa ("*");
          edVolta.setDT_Previsao_Chegada ("*");

          edVolta.setHR_Previsao_Chegada ("*");
          edVolta.setNM_Motorista ("*");
          edVolta.setNR_Celular ("*");
          edVolta.setDM_Situacao_Embarque ("*");

          while (resTB.next ()) {

            edVolta.setDM_Situacao_Embarque (resTB.getString ("DM_Situacao"));
            edVolta.setNR_Placa (resTB.getString ("NR_Placa"));

            edVolta.setDT_Previsao_Chegada (resTB.getString ("DT_Nova_Previsao_Chegada"));

            if (edVolta.getDT_Previsao_Chegada () != null) {
              DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Chegada ());
              edVolta.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());

              edVolta.setHR_Previsao_Chegada (resTB.getString ("HR_Nova_Previsao_Chegada"));
            }
            else {
              edVolta.setDT_Previsao_Chegada (resTB.getString ("DT_Previsao_Chegada"));
              DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Chegada ());
              edVolta.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());

              edVolta.setHR_Previsao_Chegada (resTB.getString ("HR_Previsao_Chegada"));
            }

            edVolta.setNM_Motorista (resTB.getString ("NM_Motorista"));
            edVolta.setNR_Celular (resTB.getString ("NR_Celular"));
          }

          sql = " SELECT Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Ocorrencias_Notas_Fiscais.HR_Ocorrencia_Nota_Fiscal, Ocorrencias_Notas_Fiscais.DT_Ocorrencia_Nota_Fiscal from Ocorrencias_Notas_Fiscais, Tipos_Ocorrencias ";
          sql += " WHERE Ocorrencias_Notas_Fiscais.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
          sql += " AND Ocorrencias_Notas_Fiscais.OID_Nota_Fiscal = '" + edVolta.getOID_Nota_Fiscal () + "'";
          sql += " ORDER BY Ocorrencias_Notas_Fiscais.OID_Ocorrencia_Nota_Fiscal";

          resTB = null;
          resTB = this.executasql.executarConsulta (sql);

          edVolta.setNM_Ocorrencia ("*");
          edVolta.setDT_Ocorrencia_Nota_Fiscal ("*");
          edVolta.setHR_Ocorrencia_Nota_Fiscal ("*");

          while (resTB.next ()) {

            edVolta.setNM_Ocorrencia (resTB.getString ("NM_Tipo_Ocorrencia"));
            edVolta.setDT_Ocorrencia_Nota_Fiscal (resTB.getString ("DT_Ocorrencia_Nota_Fiscal"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Ocorrencia_Nota_Fiscal ());
            edVolta.setDT_Ocorrencia_Nota_Fiscal (DataFormatada.getDT_FormataData ());

            edVolta.setHR_Ocorrencia_Nota_Fiscal (resTB.getString ("HR_Ocorrencia_Nota_Fiscal"));

          }
        }

        list.add (edVolta);
      }
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

  public ArrayList gera_Arquivo_Nota_Fiscal_Traking (Nota_FiscalED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    Nota_FiscalED ed = (Nota_FiscalED) edComp;

    try {

      sql = " select Notas_Fiscais.OID_Pessoa, Notas_Fiscais.OID_Pessoa_Destinatario, Notas_Fiscais.DM_Situacao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DT_Entrada, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario ";
      sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf (ed.getCD_Referencia ()) != null &&
          !String.valueOf (ed.getCD_Referencia ()).equals ("")) {

        sql = " select Notas_Fiscais.OID_Pessoa, Notas_Fiscais.OID_Pessoa_Destinatario, Itens_Notas_Fiscais.NR_Volumes as NR_Volumes_Item, Produtos.NM_Produto, Notas_Fiscais.DM_Situacao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DT_Entrada, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Referencias, Itens_Notas_Fiscais, Produtos ";
        sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal ";
        sql += " AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_Referencia ";
        sql += " AND Referencias.OID_Produto = Produtos.OID_Produto ";
        sql += " AND Referencias.CD_Referencia = '" + ed.getCD_Referencia () + "'";

      }

      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0")) {
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }

      if (ed.getDT_Emissao_Inicial () != null && !ed.getDT_Emissao_Inicial ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (ed.getDT_Emissao_Final () != null && !ed.getDT_Emissao_Final ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getDM_Situacao ()) != null &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("") &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
        sql += " and Notas_Fiscais.DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }

      sql += " Order by Notas_Fiscais.NR_Nota_Fiscal";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();
      ManipulaArquivo man = new ManipulaArquivo (";");

      while (res.next ()) {
        Nota_FiscalED edVolta = new Nota_FiscalED ();

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
        edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

        list.add (edVolta);

//        man.insereValor(String.valueOf(edVolta.getNR_Nota_Fiscal()));
//        man.insereValor(String.valueOf(edVolta.getNR_Volumes()));
//        man.insereValor(String.valueOf(edVolta.getVL_Nota_Fiscal()));
//        man.insereValor(String.valueOf(edVolta.getNR_Peso()));
//        man.insereValor(edVolta.getOID_Pessoa());
//        man.insereValor(edVolta.getOID_Pessoa_Destinatario());
//        man.insereValor(edVolta.getDT_Emissao());
//        man.insereValor(edVolta.getDT_Entrada());

//        man.insereQuebra();

      }
//      man.escreveArquivo("\\data\\delta\\arquivos\\Nota_Fiscal.txt");

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

  public ArrayList Nota_Fiscal_Rastreamento_Lista (Nota_FiscalED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    Nota_FiscalED ed = (Nota_FiscalED) edComp;

    try {

      sql = " select Notas_Fiscais.DM_Situacao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DT_Entrada, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario ";
      sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf (ed.getCD_Referencia ()) != null &&
          !String.valueOf (ed.getCD_Referencia ()).equals ("")) {
        sql =
            " select Itens_Notas_Fiscais.NR_Volumes as NR_Volumes_Item, Produtos.NM_Produto, Notas_Fiscais.DM_Situacao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DT_Entrada, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Referencias, Itens_Notas_Fiscais, Produtos ";
        sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal ";
        sql += " AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_Referencia ";
        sql += " AND Referencias.OID_Produto = Produtos.OID_Produto ";
        sql += " AND Referencias.CD_Referencia = '" + ed.getCD_Referencia () + "'";
      }

      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0")) {
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }

      if (ed.getDT_Emissao_Inicial () != null && !ed.getDT_Emissao_Inicial ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (ed.getDT_Emissao_Final () != null && !ed.getDT_Emissao_Final ().equals ("")) {
        sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getDM_Situacao ()) != null &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("") &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
        sql += " and Notas_Fiscais.DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }

      sql += " Order by Notas_Fiscais.DM_Situacao, Notas_Fiscais.DT_Entrada asc, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Pessoa desc";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        Nota_FiscalED edVolta = new Nota_FiscalED ();
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
        edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

        if (String.valueOf (ed.getCD_Referencia ()) != null &&
            !String.valueOf (ed.getCD_Referencia ()).equals ("")) {

          edVolta.setNM_Produto (res.getString ("NM_Produto"));
          edVolta.setNR_Volumes_Itens (res.getLong ("NR_Volumes_Item"));

          sql = " SELECT Embarques.DM_Situacao, Embarques.NR_Placa, Embarques.DT_Nova_Previsao_Chegada, Embarques.HR_Nova_Previsao_Chegada, Embarques.DT_Previsao_Chegada, Embarques.HR_Previsao_Chegada, Embarques.NM_Motorista, Embarques.NR_Celular from Notas_Fiscais_Embarques, Notas_Fiscais, Embarques ";
          sql += " WHERE Notas_Fiscais_Embarques.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
          sql += " AND Notas_Fiscais_Embarques.OID_Embarque = Embarques.OID_Embarque ";
          sql += " AND Notas_Fiscais_Embarques.OID_Nota_Fiscal = '" + edVolta.getOID_Nota_Fiscal () + "'";

          ResultSet resTB = null;
          resTB = this.executasql.executarConsulta (sql);

          edVolta.setNR_Placa ("*");
          edVolta.setDT_Previsao_Chegada ("*");

          edVolta.setHR_Previsao_Chegada ("*");
          edVolta.setNM_Motorista ("*");
          edVolta.setNR_Celular ("*");
          edVolta.setDM_Situacao_Embarque ("*");

          while (resTB.next ()) {

            edVolta.setDM_Situacao_Embarque (resTB.getString ("DM_Situacao"));
            edVolta.setNR_Placa (resTB.getString ("NR_Placa"));

            edVolta.setDT_Previsao_Chegada (resTB.getString ("DT_Nova_Previsao_Chegada"));

            if (edVolta.getDT_Previsao_Chegada () != null) {
              DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Chegada ());
              edVolta.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());

              edVolta.setHR_Previsao_Chegada (resTB.getString ("HR_Nova_Previsao_Chegada"));
            }
            else {
              edVolta.setDT_Previsao_Chegada (resTB.getString ("DT_Previsao_Chegada"));
              DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Chegada ());
              edVolta.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());

              edVolta.setHR_Previsao_Chegada (resTB.getString ("HR_Previsao_Chegada"));
            }

            edVolta.setNM_Motorista (resTB.getString ("NM_Motorista"));
            edVolta.setNR_Celular (resTB.getString ("NR_Celular"));
          }

          sql = " SELECT Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Ocorrencias_Notas_Fiscais.HR_Ocorrencia_Nota_Fiscal, Ocorrencias_Notas_Fiscais.DT_Ocorrencia_Nota_Fiscal from Ocorrencias_Notas_Fiscais, Tipos_Ocorrencias ";
          sql += " WHERE Ocorrencias_Notas_Fiscais.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
          sql += " AND Ocorrencias_Notas_Fiscais.OID_Nota_Fiscal = '" + edVolta.getOID_Nota_Fiscal () + "'";
          sql += " ORDER BY Ocorrencias_Notas_Fiscais.OID_Ocorrencia_Nota_Fiscal";

          resTB = null;
          resTB = this.executasql.executarConsulta (sql);

          edVolta.setNM_Ocorrencia ("*");
          edVolta.setDT_Ocorrencia_Nota_Fiscal ("*");
          edVolta.setHR_Ocorrencia_Nota_Fiscal ("*");

          while (resTB.next ()) {

            edVolta.setNM_Ocorrencia (resTB.getString ("NM_Tipo_Ocorrencia"));
            edVolta.setDT_Ocorrencia_Nota_Fiscal (resTB.getString ("DT_Ocorrencia_Nota_Fiscal"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Ocorrencia_Nota_Fiscal ());
            edVolta.setDT_Ocorrencia_Nota_Fiscal (DataFormatada.getDT_FormataData ());

            edVolta.setHR_Ocorrencia_Nota_Fiscal (resTB.getString ("HR_Ocorrencia_Nota_Fiscal"));

          }
        }

        list.add (edVolta);
      }
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

  public byte[] geraRelNota_Fiscal (Nota_FiscalED ed) throws Excecoes {
    byte b[] = null;

    String sql = " SELECT  Notas_Fiscais.*,          " +
        " Notas_Fiscais.dt_carga_deposito as DT_Carga, " +
        " '000' as oid_Conhecimento,          " +
        " Depositos.CD_Deposito,          " +
        " Depositos.NM_Deposito  " +
        " FROM  Notas_Fiscais, Depositos  " +
        " WHERE Notas_Fiscais.oid_deposito = Depositos.oid_deposito ";

    if ("M2".equals (ed.getDM_Relatorio ())) {
      sql = " SELECT  Notas_Fiscais.*,          " +
          " Conhecimentos.dt_emissao as DT_Carga, " +
          " Conhecimentos.oid_Conhecimento, " +
          " Depositos.CD_Deposito,          " +
          " Depositos.NM_Deposito  " +
          " FROM  Notas_Fiscais, Depositos, Conhecimentos_Notas_Fiscais, Conhecimentos   " +
          " WHERE Notas_Fiscais.oid_deposito = Depositos.oid_deposito " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal  " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento  ";

      if (ed.getDT_Saida_Inicial () != null && !ed.getDT_Saida_Inicial ().equals ("")) {
        sql = sql + " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Saida_Inicial () + "'";
      }
      if (ed.getDT_Saida_Final () != null && !ed.getDT_Saida_Final ().equals ("")) {
        sql = sql + " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Saida_Final () + "'";
      }
    }

    if ("M3".equals (ed.getDM_Relatorio ())) {
      sql = " SELECT  Notas_Fiscais.*,          " +
          " Conhecimentos.dt_emissao as DT_Carga, " +
          " Conhecimentos.oid_Conhecimento, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Depositos.CD_Deposito,          " +
          " Depositos.NM_Deposito  " +
          " FROM  Notas_Fiscais, Depositos, Conhecimentos_Notas_Fiscais, Conhecimentos   " +
          " WHERE Notas_Fiscais.oid_deposito = Depositos.oid_deposito " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal  " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento  " +
          " AND   Conhecimentos.NR_Conhecimento = '" + ed.getNR_Conhecimento () + "'";
    }

    if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0") && !String.valueOf (ed.getOID_Produto ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.OID_Produto = " + ed.getOID_Produto ();
    }
    if (String.valueOf (ed.getOid_Deposito ()) != null && !String.valueOf (ed.getOid_Deposito ()).equals ("0") && !String.valueOf (ed.getOid_Deposito ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.OID_Deposito = " + ed.getOid_Deposito ();
    }
    if (String.valueOf (ed.getNR_Pedido ()) != null && !String.valueOf (ed.getNR_Pedido ()).equals ("") && !String.valueOf (ed.getNR_Pedido ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.NR_Pedido = '" + ed.getNR_Pedido () + "'";
    }
    if (String.valueOf (ed.getNR_Lote ()) != null && !String.valueOf (ed.getNR_Lote ()).equals ("") && !String.valueOf (ed.getNR_Lote ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.NR_Lote = '" + ed.getNR_Lote () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
    }
    if (ed.getDT_Emissao_Inicial () != null && !ed.getDT_Emissao_Inicial ().equals ("")) {
      sql = sql + " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
    }
    if (ed.getDT_Emissao_Final () != null && !ed.getDT_Emissao_Final ().equals ("")) {
      sql = sql + " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
    }

    if (String.valueOf (ed.getDM_Situacao ()) != null && !String.valueOf (ed.getDM_Situacao ()).equals ("") && !String.valueOf (ed.getDM_Situacao ()).equals ("T") && !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.DM_Situacao = '" + ed.getDM_Situacao () + "'";
    }
    if (String.valueOf (ed.getOID_Coleta ()) != null && !String.valueOf (ed.getOID_Coleta ()).equals ("0") && !String.valueOf (ed.getOID_Coleta ()).equals ("null")) {
      sql = sql + " and Notas_Fiscais.OID_Coleta = " + ed.getOID_Coleta ();
    }
    sql = sql + " Order by Notas_Fiscais.oid_Deposito, Notas_Fiscais.DT_Entrada asc, Notas_Fiscais.NR_Nota_Fiscal ";

    try {
      ResultSet res = null;
      res = executasql.executarConsulta (sql.toString ());
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m\351todo listar");
      exce.setClasse (getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] geraRelRemessas (Nota_FiscalED ed) throws Excecoes {

    byte b[] = null;
    ArrayList list = new ArrayList ();

    // // // System.out.println("geraRelRemessas antes lista");

    list = this.listaConsultaClienteSimples (ed);

    // // // System.out.println("geraRelRemessas LISTA OK");

    return b;
  }

  private ResultSet executaPesquisa (Nota_FiscalED ed) throws Excecoes {

    ResultSet res = null;
    boolean auxiliar = false;
    long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (3 , 12)).longValue ());
    String Classifica = "PRIMEIRO";
    String Campo_Lido = "";
    String Campo2 = "";
    double vl_total_frete = 0;
    double vl_qtde_emb = 0;
    double vl_qtde_emb_300 = 0;
    double vl_notas_fiscais = 0;
    double vl_peso = 0;
    int d0 = 0 , d1 = 0 , d2 = 0 , d3 = 0 , d4 = 0 , d5 = 0 , d6 = 0 , d7 = 0 , d8 = 0;
    try {

      String sql =
          " SELECT Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.OID_Conhecimento, " +
          " Conhecimentos.oid_Modal, " +
          " Conhecimentos.oid_Unidade, " +
          " Conhecimentos.oid_Produto, " +
          " Conhecimentos.oid_pessoa, " +
          " Conhecimentos.Oid_Pessoa_Destinatario, " +
          " Conhecimentos.oid_pessoa_pagador, " +
          " Conhecimentos.OID_Centro_Custo, " +
          " Conhecimentos.OID_Cidade_Destino, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.cd_cfo_conhecimento, " +
          " Conhecimentos.DM_Cia_Aerea, " +
          " Conhecimentos.NR_AWB, " +
          " Conhecimentos.NR_Minuta, " +
          " Conhecimentos.NR_Postagem, " +
          " Conhecimentos.DM_Tipo_Postagem, " +
          " Conhecimentos.NM_Natureza, " +
          " Conhecimentos.TX_Observacao, " +
          " Conhecimentos.DT_Emissao as DT_Emissao_CTO, " +
          " Conhecimentos.DT_Entrega, " +
          " Conhecimentos.HR_Entrega, " +
          " Conhecimentos.DT_Previsao_Entrega, " +
          " Conhecimentos.HR_Previsao_Entrega, " +
          " Conhecimentos.DM_Tipo_Documento, " +
          " Conhecimentos.NM_Pessoa_Entrega, " +
          " Conhecimentos.NR_Dias_Entrega_Previsto, " +
          " Conhecimentos.NR_Dias_Entrega_Realizado, " +
          " Conhecimentos.VL_Total_Frete, " +
          " Conhecimentos.Vl_frete_peso, " +
          " Conhecimentos.Vl_frete_valor, " +
          " Conhecimentos.VL_Pedagio, " +
          " Conhecimentos.VL_Despacho, " +
          " Conhecimentos.VL_Sec_Cat, " +
          " Conhecimentos.VL_Outros1, " +
          " Conhecimentos.VL_Outros2, " +
          " Conhecimentos.VL_Outros3, " +
          " Conhecimentos.VL_Outros4, " +
          " Conhecimentos.VL_Outros5, " +
          " Conhecimentos.VL_Outros6, " +
          " Conhecimentos.VL_Outros7, " +
          " Conhecimentos.VL_Outros8, " +
          " Conhecimentos.VL_Base_Calculo_ICMS, " +
          " Conhecimentos.VL_ICMS, " +
          " Conhecimentos.PE_Aliquota_ICMS, " +
          " Conhecimentos.NR_Peso, " +
          " Conhecimentos.NR_Peso_Cubado, " +
          " Conhecimentos.NR_Volumes as NR_Volumes_Conhecimento, " +
          " Conhecimentos.DM_Tipo_Pagamento, " +
          " Conhecimentos.DM_Tipo_Documento, " +
          " Clientes.DM_Tracking, " +
          " Notas_Fiscais.DT_Entrada, " +
          " Notas_Fiscais.DT_Emissao as DT_Emissao_NF, " +
          " Notas_Fiscais.DM_Situacao, " +
          " Notas_Fiscais.NR_Peso as NR_Peso_NF, " +
          " Notas_Fiscais.NR_Volumes , " +
          " Notas_Fiscais.VL_Nota_Fiscal, " +
          " Notas_Fiscais.DT_Emissao, " +
          " Notas_Fiscais.NR_Nota_Fiscal," +
          " Notas_Fiscais.NR_Despacho," +
          " Notas_Fiscais.NR_Pedido," +
          " Notas_Fiscais.NR_Codigo_Cliente," +
          " Notas_Fiscais.OID_Coleta, " +
          " Notas_Fiscais.OID_Nota_Fiscal " +
          " FROM Clientes, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais " +
          " WHERE Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND   Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
          //" AND   Conhecimentos.oid_Pessoa = Clientes.oid_Pessoa " +
          " AND   Conhecimentos.oid_Pessoa_pagador = Clientes.oid_Pessoa " +
          " AND   Conhecimentos.DM_Impresso='S' " +
          " AND   Conhecimentos.DM_Situacao <>'C' " +
          " AND   Clientes.CD_Acesso = '" + ed.getCD_Acesso () + "'";

      if ("RESUMO".equals(ed.getDM_Relatorio ().substring(0,6))) {
        sql = " SELECT Conhecimentos.DT_Emissao as DT_Emissao_CTO, " +
            " Conhecimentos.DT_Entrega, " +
            " Conhecimentos.NR_Dias_Entrega_Realizado, " +
            " Conhecimentos.NR_Dias_Entrega_Previsto, " +
            " Conhecimentos.DT_Previsao_Entrega, " +
            " Conhecimentos.VL_Total_Frete, " +
            " Notas_Fiscais.DT_Emissao, " +
            " Notas_Fiscais.NR_Peso as NR_Peso_NF, " +
            " Notas_Fiscais.NR_Volumes, " +
            " Estado_Origem.CD_Estado  as CD_Estado_Origem, " +
            " Cidade_Origem.NM_Cidade  as NM_Cidade_Origem, " +
            " Cidade_Destino.NM_Cidade  as NM_Cidade_DEstino, " +
            " Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
            " Regiao_Estado_Origem.NM_Regiao_Estado  as NM_Regiao_Estado_Origem, " +
            " Regiao_Estado_Destino.NM_Regiao_Estado  as NM_Regiao_Estado_Destino, " +
            " Clientes.DM_Tracking, " +
            " Notas_Fiscais.VL_Nota_Fiscal " +
            " FROM Clientes, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario " +
            " ,Cidades Cidade_Origem, " +
            " Cidades Cidade_Destino, " +
            " Regioes_Estados Regiao_Estado_Origem,  " +
            " Regioes_Estados Regiao_Estado_Destino, " +
            " Estados Estado_Origem, " +
            " Estados Estado_Destino  " +
            " WHERE Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
            " AND   Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
            " AND   Conhecimentos.oid_Pessoa_pagador = Clientes.oid_Pessoa " +
            " AND   Conhecimentos.DM_Impresso='S' " +
            " AND   Conhecimentos.DM_Situacao <>'C' " +
            " AND   Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
            " AND   Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
            " AND   Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
            " AND   Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
            " AND   Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
            " AND   Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado " +
            " AND   Clientes.CD_Acesso = '" + ed.getCD_Acesso () + "'";
      }
      if (String.valueOf (ed.getNR_Conhecimento ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0") &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }

      if (String.valueOf (ed.getNR_Despacho ()) != null &&
          !String.valueOf (ed.getNR_Despacho ()).equals ("0") &&
          !String.valueOf (ed.getNR_Despacho ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Despacho = " + ed.getNR_Despacho ();
      }

      if (String.valueOf (ed.getNR_Pedido ()) != null &&
          !String.valueOf (ed.getNR_Pedido ()).equals ("0") &&
          !String.valueOf (ed.getNR_Pedido ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Pedido = '" + ed.getNR_Pedido () + "'";
      }

      if (String.valueOf (ed.getNR_Codigo_Cliente ()) != null &&
          !String.valueOf (ed.getNR_Codigo_Cliente ()).equals ("0") &&
          !String.valueOf (ed.getNR_Codigo_Cliente ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Codigo_Cliente = '" + ed.getNR_Codigo_Cliente () + "'";
      }

      if (String.valueOf (ed.getOID_Coleta ()) != null &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("0") &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Coleta = " + ed.getOID_Coleta ();
      }

      if (String.valueOf (ed.getOID_Produto ()) != null &&
          !String.valueOf (ed.getOID_Produto ()).equals ("0") &&
          !String.valueOf (ed.getOID_Produto ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Produto = " + ed.getOID_Produto ();
      }

      if (String.valueOf (ed.getNR_Pedido ()) != null &&
          !String.valueOf (ed.getNR_Pedido ()).equals ("") &&
          !String.valueOf (ed.getNR_Pedido ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Pedido = '" + ed.getNR_Pedido () + "'";
      }

      if (String.valueOf (ed.getNR_Lote ()) != null &&
          !String.valueOf (ed.getNR_Lote ()).equals ("") &&
          !String.valueOf (ed.getNR_Lote ()).equals ("null")) {
        sql += " and Notas_Fiscais.NR_Lote = '" + ed.getNR_Lote () + "'";
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Emissao ())) {
        sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      else if (JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
        sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
        sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Entrega_Inicial ())) {
        sql += " and Conhecimentos.DT_Entrega >= '" + ed.getDT_Entrega_Inicial () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Entrega_Final ())) {
        sql += " and Conhecimentos.DT_Entrega <= '" + ed.getDT_Entrega_Final () + "'";
      }
      if ("C".equals (ed.getDM_Tipo_Documento ())) {
        sql += " and Conhecimentos.DM_Tipo_Documento='C' ";
      }
      if ("M".equals (ed.getDM_Tipo_Documento ())) {
        sql += " and Conhecimentos.DM_Tipo_Documento='M' ";
      }

      if ("E".equals (ed.getDM_Situacao ())) {
        sql += " and not Conhecimentos.DT_Entrega is null ";
      }
      else if ("P".equals (ed.getDM_Situacao ())) {
        sql += " and Conhecimentos.DT_Entrega is null ";
      }

      if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("C")
          && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
        sql += " AND Conhecimentos.oid_cidade = " + ed.getOID_Origem ();
      }

      ///
      if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("R")
          && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
        //sql += " AND Regiao_Estado_Origem.oid_Regiao_Estado = " + ed.getOID_Origem ();
      }
      if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("E")
          && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
        //sql += " AND Estado_Origem.oid_Estado = " + ed.getOID_Origem ();
      }

      if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("C")
          && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
        sql += " AND Conhecimentos.oid_cidade_Destino = " + ed.getOID_Destino ();
      }

      if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("R")
          && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
        //sql += " AND Regiao_Estado_Destino.oid_Regiao_Estado = " + ed.getOID_Destino ();
      }
      if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("E")
          && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
        //sql += " AND Estado_Destino.oid_Estado = " + ed.getOID_Destino ();
      }


      if ("RESUMO_UFDEST".equals(ed.getDM_Relatorio ())) {
        sql += " ORDER by Estado_Destino.cd_estado ";
        auxiliar = true;
      }
      if ("RESUMO_REGDEST".equals(ed.getDM_Relatorio ())) {
        sql += " ORDER by Regiao_Estado_Destino.NM_Regiao_Estado ";
        auxiliar = true;
      }
      if ("RESUMO_CIDDEST".equals(ed.getDM_Relatorio ())) {
        sql += " ORDER by Estado_Destino.cd_estado, Cidade_Destino.NM_Cidade ";
        auxiliar = true;
      }
      if ("RESUMO_DTEMISSAO".equals(ed.getDM_Relatorio ())||
          "RESUMO_DTEMISSAO_EXP".equals(ed.getDM_Exportacao ())) {
        sql += " ORDER by Conhecimentos.DT_Emissao ";
        auxiliar = true;
      }

      if (!auxiliar) {
          if ("RELATORIO_REMESSA1".equals(ed.getDM_Relatorio ())) {
              sql += " ORDER by Conhecimentos.NR_Conhecimento";

          }else {
	        if ("1".equals (ed.getDM_Ordem ())) {
	          sql += " ORDER by Notas_Fiscais.NR_Despacho";
	        }
	        if ("2".equals (ed.getDM_Ordem ())) {
	          sql += " ORDER by Notas_Fiscais.NR_Nota_Fiscal";
	        }
	        if ("3".equals (ed.getDM_Ordem ())) {
	          sql += " ORDER by Conhecimentos.NR_Conhecimento";
	        }
	        if ("4".equals (ed.getDM_Ordem ())) {
	          sql += " ORDER by Notas_Fiscais.DT_Emissao Desc";
	        }
	        if ("5".equals (ed.getDM_Ordem ())) {
	          sql += " ORDER by Conhecimentos.DT_Previsao_Entrega";
	        }
	        if ("6".equals (ed.getDM_Ordem ())) {
	          sql += " ORDER by Conhecimentos.DT_Entrega";
	        }
        }
        if (!JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
          sql += "  LIMIT 500 ";
        }
      }


      //// System.out.println (sql);

      res = this.executasql.executarConsulta (sql);

      if (auxiliar == true) {
        while (res.next ()) {

          Nota_FiscalED edVolta = new Nota_FiscalED ();

          DataFormatada.setDT_FormataData (res.getString ("DT_Emissao"));
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
          DataFormatada.setDT_FormataData (res.getString ("DT_Previsao_Entrega"));
          edVolta.setDT_Previsao_Entrega (DataFormatada.getDT_FormataData ());
          DataFormatada.setDT_FormataData (res.getString ("DT_Entrega"));
          edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
          edVolta.setDM_Tracking(res.getString ("DM_Tracking"));
          edVolta.setNR_Dias_Entrega_Realizado(res.getInt("NR_Dias_Entrega_Realizado"));
          edVolta = this.indicadores_Entrega (edVolta);



          if (ed.getDM_Relatorio ().equals ("RESUMO_UFDEST")) {
            Campo_Lido = res.getString ("CD_Estado_Destino");
          }
          if (ed.getDM_Relatorio ().equals ("RESUMO_REGDEST")) {
            Campo_Lido = res.getString ("NM_Regiao_Estado_Destino");
          }
          if (ed.getDM_Relatorio ().equals ("RESUMO_CIDDEST")) {
            Campo_Lido = res.getString ("CD_Estado_Destino") + "-" + (res.getString ("NM_Cidade_DEstino")+"                    ").substring(0,15);
          }

          if ("RESUMO_DTEMISSAO".equals (ed.getDM_Relatorio ()) ||
              "RESUMO_DTEMISSAO_EXP".equals (ed.getDM_Exportacao ())) {
            Campo_Lido = dataFormatada.getDT_FormataData (res.getString ("DT_Emissao_CTO"));
          }

          if (Classifica.equals ("PRIMEIRO") || Campo_Lido.equals (Classifica)) {
            Classifica = Campo_Lido;
            vl_total_frete += res.getDouble ("vl_total_frete");
            vl_qtde_emb += 1;
            if (vl_total_frete>=300){
              vl_qtde_emb_300 += 1;
            }

            vl_notas_fiscais += res.getDouble ("VL_NOTA_FISCAL");
            vl_peso += res.getDouble ("NR_Peso_NF");
            if ("OK".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d0++; //no dia
            }
            else if ("1".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d1++; //1 dia
            }
            else if ("2".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d2++;
            }
            else if ("3".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d3++;
            }
            else if ("P".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d5++; //pendente
            }
            else if ("OK-".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d6++; //antecipado
            }
            else {
              d4++; //mais que 3 dias
            }

          }
          else {
            if (vl_total_frete > 0) {
              Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
              edAuxiliar1.setNM_Classifica1 (Classifica);
              edAuxiliar1.setNR_Sort (Nr_Sort);
              edAuxiliar1.setVL_Classifica1 (vl_total_frete);
              edAuxiliar1.setVL_Classifica2 (vl_qtde_emb);
              edAuxiliar1.setVL_Classifica3 (vl_notas_fiscais);
              edAuxiliar1.setVL_Classifica4 (vl_peso);
              edAuxiliar1.setVL_Classifica6 (vl_qtde_emb_300);
              edAuxiliar1.setD0 (d0);
              edAuxiliar1.setD1 (d1);
              edAuxiliar1.setD2 (d2);
              edAuxiliar1.setD3 (d3);
              edAuxiliar1.setD4 (d4);
              edAuxiliar1.setD5 (d5);
              edAuxiliar1.setD6 (d6);
              edAuxiliar1.setD7 (d7);
              edAuxiliar1.setD8 (d8);

              new Auxiliar1BD (this.executasql).inclui(edAuxiliar1);

            }
            Classifica = Campo_Lido;

            vl_total_frete = res.getDouble ("vl_total_frete");
            vl_qtde_emb = 1;
            vl_qtde_emb_300 = 1;
            vl_notas_fiscais = res.getDouble ("VL_NOTA_FISCAL");
            vl_peso = res.getDouble ("NR_Peso_NF");
            d0 = 0;
            d1 = 0;
            d2 = 0;
            d3 = 0;
            d4 = 0;
            d5 = 0;
            d6 = 0;
            d7 = 0;
            d8 = 0;

            if ("OK".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d0++; //no dia
            }
            else if ("1".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d1++; //1 dia
            }
            else if ("2".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d2++;
            }
            else if ("3".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d3++;
            }
            else if ("P".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d5++; //pendente
            }
            else if ("OK-".equals (edVolta.getNR_Dias_Realizado_Entrega ())) {
              d6++; //antecipado
            }
            else {
              d4++; //mais que 3 dias
            }

          }
        }
        // // // System.out.println("FIM CONS " + vl_total_frete);
        // // // System.out.println("FIM CONS " + vl_qtde_emb);


        if (vl_total_frete + vl_qtde_emb > 0) {
          Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
          edAuxiliar1.setNM_Classifica1 (Classifica);
          edAuxiliar1.setNR_Sort (Nr_Sort);
          edAuxiliar1.setVL_Classifica1 (vl_total_frete);
          edAuxiliar1.setVL_Classifica2 (vl_qtde_emb);
          edAuxiliar1.setVL_Classifica3 (vl_notas_fiscais);
          edAuxiliar1.setVL_Classifica4 (vl_peso);
          edAuxiliar1.setVL_Classifica6 (vl_qtde_emb_300);
          edAuxiliar1.setD0 (d0);
          edAuxiliar1.setD1 (d1);
          edAuxiliar1.setD2 (d2);
          edAuxiliar1.setD3 (d3);
          edAuxiliar1.setD4 (d4);
          edAuxiliar1.setD5 (d5);
          edAuxiliar1.setD6 (d6);
          edAuxiliar1.setD7 (d7);
          edAuxiliar1.setD8 (d8);
          new Auxiliar1BD (this.executasql).inclui(edAuxiliar1);
          //          Auxiliar1RN.inclui (edAuxiliar1);
        }

        sql = " SELECT * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
        sql += " ORDER by auxiliar1.nm_classifica1  ";

        // // // System.out.println(sql);
        res = this.executasql.executarConsulta (sql.toString ());
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Nota_FiscalED ed)");
    }

    return res;
  }


  public Nota_FiscalED indicadores_Entrega (Nota_FiscalED edVolta) throws Excecoes {

    long nr_Dias_Entrega = 0;
    long nr_Dias_Previsto_Entrega = 0;
    long nr_Dias_Realizado_Entrega = 0;
    edVolta.setNR_Dias_Previsto_Entrega (" ");
    edVolta.setNR_Dias_Entrega (" ");
    edVolta.setNR_Dias_Realizado_Entrega ("Pend.");

          // // // System.out.println (" indicadores_Entrega -> " );
    if (edVolta.getDT_Entrega() == null || edVolta.getDT_Entrega().length()<5) {
      edVolta.setDT_Entrega ("---");
      edVolta.setHR_Entrega (" ");
    }

    if (edVolta.getDT_Previsao_Entrega() ==null || edVolta.getDT_Previsao_Entrega().length()<5) {
      edVolta.setDT_Previsao_Entrega (" ");
      edVolta.setHR_Previsao_Entrega (" ");
    }
    if (edVolta.getHR_Previsao_Entrega() ==null || edVolta.getHR_Previsao_Entrega().length()<5 || "00.00".equals(edVolta.getHR_Previsao_Entrega())) {
      edVolta.setHR_Previsao_Entrega (" ");
    }

    if ("S".equals(edVolta.getDM_Tracking())){
      if (edVolta.getDT_Entrega () == null || edVolta.getDT_Entrega ().length () < 5) {
        if (Data.comparaData (edVolta.getDT_Previsao_Entrega () , Data.getDataDMY ())) {
          edVolta.setDT_Entrega (" ");
          edVolta.setHR_Entrega (" ");
        }
        else {
          edVolta.setDT_Entrega (edVolta.getDT_Previsao_Entrega ());
          edVolta.setHR_Entrega (" ");
        }
      }
    }

    try {
      if (edVolta.getNR_Dias_Entrega_Previsto()>0){
        nr_Dias_Previsto_Entrega=edVolta.getNR_Dias_Entrega_Previsto();
      }else{
        nr_Dias_Previsto_Entrega = Data.diferencaDias (edVolta.getDT_Previsao_Entrega () , edVolta.getDT_Emissao ());
        // // System.out.println ("nr_Dias_Previsto_Entrega=" + nr_Dias_Previsto_Entrega);
        String dt_Ent = edVolta.getDT_Emissao ();
        // // System.out.println ("dt_Ent=" + dt_Ent);

        int cont_Dia = 0;
        int dias_Uteis = 0;
        if (nr_Dias_Previsto_Entrega > 0) {
          while (!edVolta.getDT_Previsao_Entrega ().equals (dt_Ent) && cont_Dia < 30) {

            // // System.out.println ("getDT_Previsao_Entrega=" + edVolta.getDT_Previsao_Entrega ());
            // // System.out.println ("dt_Ent=" + dt_Ent);

            // // System.out.println ("cont_Dia=" + cont_Dia);

            cont_Dia++;
            dt_Ent = Data.getSomaDiaData (edVolta.getDT_Emissao () , cont_Dia);

            // // System.out.println ("dt_Ent som dia=" + dt_Ent);

          }
          if (dias_Uteis < nr_Dias_Previsto_Entrega) nr_Dias_Previsto_Entrega = dias_Uteis;

          if (nr_Dias_Previsto_Entrega <= 0) {
            nr_Dias_Previsto_Entrega=1;
          }

        }
      }
      edVolta.setNR_Dias_Previsto_Entrega (String.valueOf (nr_Dias_Previsto_Entrega));
    }
    catch (Exception e) {
      throw new Excecoes (e.getClass () + ": " + e.getMessage () , e , this.getClass ().getName () , "calc prazo");
    }

    if (edVolta.getDT_Entrega () != null && edVolta.getDT_Entrega ().length () > 4) {
      try {
        //nr_Dias_Entrega = Data.diferencaDias (edVolta.getDT_Entrega () , edVolta.getDT_Emissao ());
        nr_Dias_Entrega = edVolta.getNR_Dias_Entrega_Realizado();

        edVolta.setNR_Dias_Entrega (String.valueOf (nr_Dias_Entrega));
        nr_Dias_Realizado_Entrega = nr_Dias_Entrega - nr_Dias_Previsto_Entrega;
        //nr_Dias_Realizado_Entrega = edVolta.getNR_Dias_Entrega_Realizado();

        if (nr_Dias_Realizado_Entrega > 0) {
          edVolta.setNR_Dias_Realizado_Entrega (String.valueOf (nr_Dias_Realizado_Entrega));
        }
        else {
          edVolta.setNR_Dias_Realizado_Entrega ("OK");
          if (nr_Dias_Realizado_Entrega< 0) {
            edVolta.setNR_Dias_Realizado_Entrega ("OK-");
          }
        }
      }
      catch (Exception e) {
        throw new Excecoes (e.getClass () + ": " + e.getMessage () , e , this.getClass ().getName () , "calc prazo");
      }
    }else{
      edVolta.setNR_Dias_Realizado_Entrega ("P");
    }

    // // // System.out.println (" setNR_Dias_Realizado_Entrega -> " + edVolta.getNR_Dias_Realizado_Entrega ());

    return edVolta;
  }


  public byte[] imprime_Etiqueta (Nota_FiscalED ed) throws Excecoes {

    byte b[] = null;

      String sql =
          " SELECT Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.NR_Minuta, " +
          " Conhecimentos.DT_Emissao as DT_Emissao_CTO, " +
          " Cidade_Destino.NM_Cidade  as NM_Cidade_DEstino, " +
          " Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
          " Unidades.CD_Unidade, " +
          " Notas_Fiscais.NR_CX1, " +
          " Notas_Fiscais.NR_CX2, " +
          " Notas_Fiscais.NR_CX3, " +
          " Notas_Fiscais.NR_CX4, " +
          " Notas_Fiscais.NR_CX5, " +
          " Notas_Fiscais.NR_CX6, " +
          " Notas_Fiscais.NR_CX7, " +
          " Notas_Fiscais.NR_CX8, " +
          " Notas_Fiscais.NR_CX9, " +
          " Notas_Fiscais.NR_CX10, " +
          " Notas_Fiscais.NR_CX11, " +
          " Notas_Fiscais.NR_CX12, " +
          " Notas_Fiscais.NR_Peso, " +
          " Notas_Fiscais.NR_Volumes, " +
          " Notas_Fiscais.DT_Emissao, " +
          " Notas_Fiscais.NR_Nota_Fiscal," +
          " Notas_Fiscais.NR_Pedido," +
          " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
          " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, " +
          " Pessoa_Destinatario.NM_Fantasia as NM_Fantasia_Destinatario, " +
          " Pessoa_Destinatario.NM_Endereco as NM_Endereco_Destinatario " +
          " FROM Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Unidades, " +
          " Cidades Cidade_Destino, " +
          " Regioes_Estados Regiao_Estado_Destino, " +
          " Estados Estado_Destino  " +
          " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
          " AND   Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
          " AND   Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND   Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
          " AND   Conhecimentos.DM_Situacao <>'C' " +
          " AND   Conhecimentos.DM_Impresso ='S' " +
          " AND   Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
          " AND   Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          " AND   Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          " AND   Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado " ;

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial ();
      }
      if (String.valueOf (ed.getNR_Conhecimento_Final ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final ();
      }
      if (JavaUtil.doValida(ed.getCD_Rota_Entrega())) {
          sql += " and Conhecimentos.CD_Rota_Entrega = '" + ed.getCD_Rota_Entrega() + "'";
      }

      sql += " ORDER BY Conhecimentos.NR_Conhecimento, Notas_Fiscais.NR_Nota_Fiscal";


    // // // System.out.println(sql);

    // // // System.out.println("imprime_Etiqueta antes lista");

    return b;
  }


}
