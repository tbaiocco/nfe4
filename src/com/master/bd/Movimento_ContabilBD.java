package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Movimento_ContabilED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.FormataValor;
import com.master.util.FormataData;

/**
 * @author Régis Steigleder
 * @serial Movimentos Contabeis
 * @serialData 23/11/2005
 */
public class Movimento_ContabilBD
    extends BancoUtil {

  private ExecutaSQL executasql;

  public Movimento_ContabilBD (ExecutaSQL sql) {
    super (sql);
    this.executasql = sql;
  }

  public Movimento_ContabilED inclui (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    try {
      ed.setOid_Movimento_Contabil (getAutoIncremento ("oid_Movimento_Contabil" , "Movimentos_Contabeis"));
      if (ed.getNr_Lote () == 0) {
        ed.setNr_Lote (getAutoIncremento ("nr_Lote" , "Movimentos_Contabeis"));
      }
      ed.setCd_Lote (ed.getDt_Movimento () + ed.getOid_Unidade () + ed.getOid_Origem ());
      sql = "INSERT INTO " +
          " Movimentos_Contabeis " +
          " ( " +
          " oid_Movimento_Contabil, " +
          " dt_Movimento, " +
          " oid_Unidade, " +
          " oid_Origem, " +
          " oid_Conta, " +
          " tx_Historico, " +
          " tx_Historico_Complementar, " +
          " dm_Debito_Credito, " +
          " vl_Lancamento, " +
          " dt_Digitacao, " +
          " tx_Chave_Origem, " +
          " nr_Lote, " +
          " cd_Lote, " +
          " oid_Historico, " +
          " oid_Centro_Custo " +
          " ) " +
          " VALUES " +
          " ( " +
          ed.getOid_Movimento_Contabil () +
          ",'" + ed.getDt_Movimento () +
          "'," + ed.getOid_Unidade () +
          "," + ed.getOid_Origem () +
          "," + ed.getOid_Conta () +
          ",'" + ed.getTx_Historico () +
          "','" + this.getValueDef (ed.getTx_Historico_Complementar () , " ") +
          "','" + ed.getDm_Debito_Credito () +
          "'," + ed.getVl_Lancamento () +
          ",'" + ed.getDt_Digitacao () +
          "','" + ed.getTx_Chave_Origem () +
          "'," + ed.getNr_Lote () +
          ",'" + ed.getCd_Lote () +
          "'," + ed.getOid_Historico () +
          "," + this.getValueDef (ed.getOid_Centro_Custo (), 1) +
          " )";

      executasql.executarUpdate (sql);
      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "inclui(Movimento_ContabilED ed)");
    }
  }

  public void altera (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    try {
      sql = "UPDATE " +
          " Movimentos_Contabeis " +
          "SET " +
          " oid_Movimento_Contabil = oid_Movimento_Contabil, " +
          " dt_Movimento = '" + ed.getDt_Movimento () + "', " +
          " oid_Unidade = " + ed.getOid_Unidade () + ", " +
          " oid_Origem = " + ed.getOid_Origem () + ", " +
          " oid_Conta = " + ed.getOid_Conta () + ", " +
          " tx_Historico = '" + ed.getTx_Historico () + "', " +
          " tx_Historico_Complementar = '" + this.getValueDef (ed.getTx_Historico_Complementar () , " ") + "', " +
          " dm_Debito_Credito = '" + ed.getDm_Debito_Credito () + "', " +
          " vl_Lancamento = " + ed.getVl_Lancamento () + ", " +
          " dt_Digitacao = '" + ed.getDt_Digitacao () + "', " +
          " tx_Chave_Origem = '" + ed.getTx_Chave_Origem () + "', " +
          " nr_Lote = " + ed.getNr_Lote () + ", " +
          " cd_Lote = '" + ed.getCd_Lote () + "', " +
          " oid_Historico = " + ed.getOid_Historico ();
      //if (ed.getOid_Movimento_Contabil_Contrapartida() > 0)
      //	sql += " oid_Centro_Custo = " + ed.getOid_Centro_Custo() ;
      sql += " WHERE " +
          " oid_Movimento_Contabil = " + ed.getOid_Movimento_Contabil ();
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "altera(Movimento_ContabilED ed)");
    }
  }

  public void deletaCP (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    try {
      sql = "DELETE FROM " +
          " Movimentos_Contabeis" +
          " WHERE " +
          " nr_Lote = " + ed.getNr_Lote ();
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "deletaCP(Movimento_ContabilED ed)");
    }
  }

  public void deleta (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    try {
      sql = "DELETE FROM " +
          " Movimentos_Contabeis " +
          " WHERE " +
          " oid_Movimento_Contabil = " + ed.getOid_Movimento_Contabil ();
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "deleta(Movimento_ContabilED ed)");
    }
  }

  public ArrayList lista (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    ArrayList list = new ArrayList ();
    try {
      sql = "SELECT * " +
          " FROM " +
          " Movimentos_Contabeis as m , " +
          " Unidades as u , " +
          " Origens as o , " +
          " Contas as c , " +
          " Historicos as h, " +
          " Pessoas as p, " +
          " Centros_Custos as cc " +
          " WHERE 1=1 and ";
      if (ed.getNr_Lote () > 0) {
        sql += " m.nr_Lote  = " + ed.getNr_Lote () + " and ";
      }
      if (ed.getOid_Unidade () > 0) {
        sql += " m.oid_Unidade  = " + ed.getOid_Unidade () + " and ";
      }
      if (ed.getOid_Origem () > 0) {
        sql += " m.oid_Origem = " + ed.getOid_Origem () + " and ";
      }
      if (ed.getOid_Conta () > 0) {
        sql += " m.oid_Conta  = " + ed.getOid_Conta () + " and ";
      }
      if (doValida (ed.getDt_Movimento_Inicial ()) && doValida (ed.getDt_Movimento_Final ())) {
        sql += " m.dt_Movimento between '" + ed.getDt_Movimento_Inicial () + "' and '" + ed.getDt_Movimento_Final () + "' and ";
      }
      else {
        if (doValida (ed.getDt_Movimento_Inicial ())) {
          sql += " m.dt_Movimento = '" + ed.getDt_Movimento_Inicial () + "' and ";
        }
        if (doValida (ed.getDt_Movimento_Final ())) {
          sql += " m.dt_Movimento = '" + ed.getDt_Movimento_Final () + "' and ";
        }
      }
      if (doValida (ed.getDt_Digitacao_Inicial ()) && doValida (ed.getDt_Digitacao_Final ())) {
        sql += " m.dt_Digitacao between '" + ed.getDt_Digitacao_Inicial () + "' and '" + ed.getDt_Digitacao_Final () + "' and ";
      }
      if (doValida (ed.getTipo ())) {
        if (!"A".equals (ed.getTipo ())) {
          sql += " m.dm_Debito_Credito = '" + ed.getTipo () + "' and ";
        }
      }
      if (doValida (ed.getTx_Historico_Complementar ())) {
        sql += " m.tx_Historico_Complementar like '%" + ed.getTx_Historico_Complementar() + "%' and ";
      }
      if (ed.getOid_Movimento_Contabil () > 0) {
        sql += " m.oid_Movimento_Contabil  = " + ed.getOid_Movimento_Contabil () + " and ";
      }
      if (ed.getOid_Centro_Custo () > 0) {
        sql += " m.oid_Centro_Custo  = " + ed.getOid_Centro_Custo () + " and ";
      }

      if (ed.getVl_Lancamento_Inicial () > 0 && ed.getVl_Lancamento_Final () > 0 ) {
          sql += " m.vl_Lancamento  >= " + ed.getVl_Lancamento_Inicial () + " and " + " m.vl_Lancamento  <= " + ed.getVl_Lancamento_Final () + " and ";
      }else{
          if (ed.getVl_Lancamento_Inicial () > 0 ) {
              sql += " m.vl_Lancamento  = " + ed.getVl_Lancamento_Inicial () + " and ";
          }
          if (ed.getVl_Lancamento_Final () > 0 ) {
              sql += " m.vl_Lancamento  = " + ed.getVl_Lancamento_Final () + " and ";
          }
      }

      sql += " u.oid_unidade = m.oid_unidade and " +
          " o.oid_origem = m.oid_origem and  " +
          " c.oid_conta = m.oid_conta and  " +
          " h.oid_historico = m.oid_historico and " +
          " p.oid_pessoa = u.oid_pessoa and " +
          " cc.oid_centro_custo = m.oid_centro_custo " +
          " ORDER BY " +
          " dt_Movimento, " +
          " oid_Movimento_Contabil";

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        Movimento_ContabilED edVolta = new Movimento_ContabilED ();
        edVolta = populaRegistro (res , edVolta);
        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "lista(Movimento_ContabilED ed)");
    }
  }

  public ArrayList listaInconsistentes (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    ArrayList list = new ArrayList ();
    try {
      sql = "SELECT " +
          " nr_lote, " +
          " dt_movimento, " +
          " vl_lancamento, " +
          " nm_origem " +
          " FROM " +
          " Movimentos_Contabeis as m , " +
          " Origens as o " +
          " WHERE 1=1 and ";
      if (ed.getOid_Unidade () > 0) {
        sql += " m.oid_Unidade  = " + ed.getOid_Unidade () + " and ";
      }
      if (ed.getOid_Origem () > 0) {
        sql += " m.oid_Origem = " + ed.getOid_Origem () + " and ";
      }
      if (doValida (ed.getDt_Movimento_Inicial ()) && doValida (ed.getDt_Movimento_Final ())) {
        sql += " m.dt_Movimento between '" + ed.getDt_Movimento_Inicial () + "' and '" + ed.getDt_Movimento_Final () + "' and ";
      }
      if (ed.getNr_Lote () > 0) {
        sql += " m.nr_Lote  = " + ed.getNr_Lote () + " and ";
      }
      sql += " m.oid_origem = o.oid_origem  " +
          " group by  " +
          " nr_lote, " +
          " dt_movimento, " +
          " nm_origem , vl_lancamento" +
          " having " +
          " ( sum(case when dm_debito_credito='D' then vl_lancamento else 0 end) - sum(case when dm_debito_credito='C' then vl_lancamento else 0 end) ) <> 0  " +
          " ORDER BY " +
          " dt_movimento, " +
          " nm_origem, " +
          " nr_lote";
      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        Movimento_ContabilED edVolta = new Movimento_ContabilED ();
        edVolta.setNr_Lote (res.getInt ("nr_Lote"));
        edVolta.setDt_Movimento (FormataData.formataDataBT (res.getString ("Dt_Movimento")));
        edVolta.setNm_Origem (res.getString ("nm_Origem"));
        //edVolta.setTx_Chave_Origem (res.getString ("tx_Chave_Origem"));
        edVolta.setTx_Inconsistencia ("VALORES DE DÉBITO E CRÉDITO NÃO FECHAM");
        edVolta.setVl_Lancamento (res.getDouble ("vl_Lancamento"));

        list.add (edVolta);
      }

      sql = "SELECT " +
          " nr_lote, " +
          " dt_movimento, " +
          " nm_origem, " +
          " vl_lancamento, " +
          " tx_chave_origem " +
          " FROM " +
          " Movimentos_Contabeis as m , " +
          " Origens as o, " +
          " Contas as c " +
          " WHERE 1=1 and " +
          " ( cd_Estrutural is null or " +
          " dm_Tipo_Conta = 'S' ) and ";
      if (ed.getOid_Unidade () > 0) {
        sql += " m.oid_Unidade  = " + ed.getOid_Unidade () + " and ";
      }
      if (ed.getOid_Origem () > 0) {
        sql += " m.oid_Origem = " + ed.getOid_Origem () + " and ";
      }
      if (doValida (ed.getDt_Movimento_Inicial ()) && doValida (ed.getDt_Movimento_Final ())) {
        sql += " m.dt_Movimento between '" + ed.getDt_Movimento_Inicial () + "' and '" + ed.getDt_Movimento_Final () + "' and ";
      }
      if (ed.getNr_Lote () > 0) {
        sql += " m.nr_Lote  = " + ed.getNr_Lote () + " and ";
      }

      sql += " m.oid_origem = o.oid_origem and " +
          " m.oid_conta = c.oid_conta " +
          " group by  " +
          " nr_lote, " +
          " dt_movimento, " +
          " nm_origem, " +
          " tx_chave_origem, vl_lancamento " +
          " ORDER BY " +
          " dt_movimento, " +
          " nm_origem, " +
          " nr_lote";


      ResultSet res1 = this.executasql.executarConsulta (sql);

      while (res1.next ()) {
        Movimento_ContabilED edVolta = new Movimento_ContabilED ();
        edVolta.setNr_Lote (res1.getInt ("nr_Lote"));
        edVolta.setDt_Movimento (FormataData.formataDataBT (res1.getString ("Dt_Movimento")));
        edVolta.setNm_Origem (res1.getString ("nm_Origem"));
        edVolta.setTx_Chave_Origem (res1.getString ("tx_Chave_Origem"));
        edVolta.setTx_Inconsistencia ("CONTA COM CÓDIGO ESTRUTURAL INVÁLIDO");
        edVolta.setVl_Lancamento (res.getDouble ("vl_Lancamento"));
        list.add (edVolta);
      }

      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "listaInconsistentes(Movimento_ContabilED ed)");
    }
  }

  public Movimento_ContabilED getByRecord (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    Movimento_ContabilED edQBR = new Movimento_ContabilED ();
    try {
      sql = "SELECT * " +
          " FROM " +
          " Movimentos_Contabeis as m , " +
          " Unidades as u , " +
          " Origens as o , " +
          " Contas as c , " +
          " Historicos as h, " +
          " Pessoas as p, " +
          " Centros_Custos as cc " +
          " WHERE ";
      if (ed.getOid_Movimento_Contabil () > 0) {
        sql += " oid_Movimento_Contabil = " + ed.getOid_Movimento_Contabil () + " and ";
      }
      sql += " u.oid_unidade = m.oid_unidade and " +
          " o.oid_origem = m.oid_origem and  " +
          " c.oid_conta = m.oid_conta and  " +
          " h.oid_historico = m.oid_historico and " +
          " p.oid_pessoa = u.oid_pessoa " +
          " and cc.oid_centro_custo = m.oid_centro_custo ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edQBR = populaRegistro (res , edQBR);

      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(SistemaED ed)");
    }
    return edQBR;
  }

  public Movimento_ContabilED getSaldoConta (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    Movimento_ContabilED edQBR = new Movimento_ContabilED ();
    try {
      sql = "SELECT " +
          " sum ( case when dm_debito_credito='D' then vl_lancamento else 0 end ) - " +
          " sum ( case when dm_debito_credito='C' then vl_lancamento else 0 end ) as vl_Saldo " +
          " FROM " +
          " Movimentos_Contabeis  " +
          " WHERE 1=1 ";
      if (ed.getOid_Unidade () > 0) {
        sql += " and oid_Unidade = " + ed.getOid_Unidade ();
      }
      if (ed.getOid_Conta () > 0) {
        sql += " and oid_Conta = " + ed.getOid_Conta () ;
      }
      if (doValida (ed.getDt_Movimento_Inicial ())) {
        sql += " and dt_Movimento  <  '" + ed.getDt_Movimento_Inicial () + "' ";
      }
//      System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edQBR.setVl_Saldo (res.getDouble ("vl_Saldo"));
      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getSaldoConta(SistemaED ed)");
    }
    return edQBR;
  }

  public Movimento_ContabilED getSomaConta (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    Movimento_ContabilED edQBR = new Movimento_ContabilED ();
    try {
      sql = "SELECT " +
          " sum ( case when dm_debito_credito='D' then vl_lancamento else 0 end ) as vl_Debito, " +
          " sum ( case when dm_debito_credito='C' then vl_lancamento else 0 end ) as vl_Credito " +
          " FROM " +
          " Movimentos_Contabeis  " +
          " WHERE ";
      if (ed.getOid_Unidade () > 0) {
        sql += " oid_Unidade = " + ed.getOid_Unidade () + " and ";
      }
      if (ed.getOid_Conta () > 0) {
        sql += " oid_Conta = " + ed.getOid_Conta () + " and ";
      }
      if (doValida (ed.getDt_Movimento_Inicial ()) && doValida (ed.getDt_Movimento_Final ())) {
        sql += " dt_Movimento between '" + ed.getDt_Movimento_Inicial () + "' and '" + ed.getDt_Movimento_Final () + "'";
      }
      if (doValida (ed.getDm_Lista_Encerramento ()) && "N".equals(ed.getDm_Lista_Encerramento())) {
          sql += " and oid_origem <> 11 " ; // retira do relatorio o encerramento do exercício
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edQBR.setVl_Debito (res.getDouble ("vl_Debito"));
        edQBR.setVl_Credito (res.getDouble ("vl_Credito"));
      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getSomaConta(SistemaED ed)");
    }
    return edQBR;
  }

  /**
   * Dependendo do tipo de lote que estiver no banco:
   * se parametro p = "QT"
   * 		se tiver um debito/credito somente para vários creditos/debitos retorna "ctbM002.jsp"
   * 		se tiver vários debito/credito para vários créditos/débitos retorna "ctbM004.jsp"
   * se parametro p = "DC"
   * 		retorna a qtd de lancamentos a débito e a crédito
   * @param ed, p
   * @return "ctbM004.jsp" : "ctbM002.jsp" se parametro p ="QT"
   * 		   "D" ou "C" se parametro p = "DC"
   * @throws Excecoes
   */
  public String getQualTela (Movimento_ContabilED ed , String p) throws Excecoes {
    String sql = null;
    int ctaD = 0;
    int ctaC = 0;
    try {
      sql = "SELECT " +
          " sum ( case when dm_debito_credito='D' then 1 else 0 end ) as ctaD, " +
          " sum ( case when dm_debito_credito='C' then 1 else 0 end ) as ctaC  " +
          " FROM " +
          " Movimentos_Contabeis  " +
          " WHERE " +
          " nr_Lote = " + ed.getNr_Lote ();

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        ctaD = res.getInt ("ctaD"); // qtd de lancamentos a débito no lote
        ctaC = res.getInt ("ctaC"); // qtd de lancamentos a crédito no lote
      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getSomaConta(SistemaED ed)");
    }
    if (p == "QT") {
      return (ctaD > 1 && ctaC > 1) ? "ctbM004.jsp" : "ctbM002.jsp";
    }
    else {
      return (ctaD == 1) ? "D" : "C";
    }

  }

  private Movimento_ContabilED populaRegistro (ResultSet res , Movimento_ContabilED edVolta) throws SQLException {
    edVolta.setOid_Movimento_Contabil (res.getInt ("Oid_Movimento_Contabil"));
    edVolta.setDt_Movimento (FormataData.formataDataBT (res.getString ("Dt_Movimento")));
    edVolta.setOid_Unidade (res.getInt ("Oid_Unidade"));
    edVolta.setOid_Origem (res.getInt ("Oid_Origem"));
    edVolta.setOid_Conta (res.getInt ("Oid_Conta"));
    edVolta.setTx_Historico (res.getString ("Tx_Historico"));
    edVolta.setTx_Historico_Complementar (res.getString ("Tx_Historico_Complementar"));
    edVolta.setDm_Debito_Credito (res.getString ("Dm_Debito_Credito"));
    edVolta.setVl_Lancamento_TX (FormataValor.formataValorBT (res.getDouble ("Vl_Lancamento") , 2));
    edVolta.setDt_Digitacao (FormataData.formataDataBT (res.getString ("Dt_Digitacao")));
    edVolta.setTx_Chave_Origem (res.getString ("Tx_Chave_Origem"));
    edVolta.setNr_Lote (res.getInt ("nr_Lote"));
    edVolta.setCd_Lote (res.getString ("Cd_Lote"));
    edVolta.setCd_Unidade (res.getString ("cd_Unidade"));
    edVolta.setNm_Fantasia (res.getString ("nm_Fantasia"));
    edVolta.setCd_Origem (res.getString ("cd_Origem"));
    edVolta.setNm_Origem (res.getString ("nm_Origem"));
    edVolta.setCd_Conta (res.getString ("cd_Conta"));
    edVolta.setNm_Conta (res.getString ("nm_Conta"));
    edVolta.setVl_Lancamento (res.getDouble ("vl_Lancamento"));
    edVolta.setCd_Historico (res.getString ("cd_Historico"));
    edVolta.setOid_Historico (res.getInt ("oid_Historico"));
    edVolta.setOid_Centro_Custo (res.getInt ("Oid_Centro_Custo"));
    edVolta.setCd_Centro_Custo (res.getString ("cd_Centro_Custo"));
    edVolta.setNm_Centro_Custo (res.getString ("nm_Centro_Custo"));
    edVolta.setCd_Estrutural(res.getString ("Cd_estrutural"));
    return edVolta;
  }

  public void desfaz_CTB (Movimento_ContabilED ed) throws Excecoes {
    String sql = null;
    try {
      sql = "DELETE FROM " +
          " Movimentos_Contabeis " +
          " WHERE " +
          " oid_origem = " + ed.getOid_Origem () +
          " AND tx_chave_origem = '" + ed.getTx_Chave_Origem () + "'";
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "desfaz_CTB(Movimento_ContabilED ed)");
    }
  }

  public Movimento_ContabilED getDadosSaldoSPED(Movimento_ContabilED ed) throws Excecoes {
	    String sql = null;
	    Movimento_ContabilED edQBR = new Movimento_ContabilED ();
	    try {
	      sql = "SELECT " +
	          " sum ( case when dm_debito_credito='D' then vl_lancamento else 0 end ) as vl_Debito, " +
	          " sum ( case when dm_debito_credito='C' then vl_lancamento else 0 end ) as vl_Credito " +
	          " FROM " +
	          " Movimentos_Contabeis  " +
	          " WHERE ";
	      if (ed.getOid_Unidade () > 0) {
	        sql += " oid_Unidade = " + ed.getOid_Unidade () + " and ";
	      }
	      if (ed.getOid_Conta () > 0) {
	        sql += " oid_Conta = " + ed.getOid_Conta () + " and ";
	      }
	      if (doValida (ed.getDt_Movimento_Inicial ()) && doValida (ed.getDt_Movimento_Final ())) {
	        sql += " dt_Movimento between '" + ed.getDt_Movimento_Inicial () + "' and '" + ed.getDt_Movimento_Final () + "'";
	      }
//	      if (doValida (ed.getDm_Lista_Encerramento ()) && "N".equals(ed.getDm_Lista_Encerramento())) {
//	          sql += " and oid_origem <> 11 " ; // retira do relatorio o encerramento do exercício
//	      }
//          System.out.println(sql);
	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	        edQBR.setVl_Debito (res.getDouble ("vl_Debito"));
	        edQBR.setVl_Credito (res.getDouble ("vl_Credito"));
	        edQBR.setVl_Saldo(res.getDouble ("vl_Debito") - res.getDouble ("vl_Credito"));
	      }
	    }
	    catch (Exception e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getSomaConta(SistemaED ed)");
	    }
	    return edQBR;
	  }

  public ArrayList listaSPED(Movimento_ContabilED ed) throws Excecoes {
	    String sql = null;
	    ArrayList list = new ArrayList ();
	    try {
	      sql = "SELECT * " +
	          " FROM " +
	          " Movimentos_Contabeis as m , " +
	          " Unidades as u , " +
	          " Origens as o , " +
	          " Contas as c , " +
	          " Historicos as h, " +
	          " Pessoas as p, " +
	          " Centros_Custos as cc " +
	          " WHERE 1=1 and ";
	      if (ed.getNr_Lote () > 0) {
	        sql += " m.nr_Lote  = " + ed.getNr_Lote () + " and ";
	      }
	      if (ed.getOid_Unidade () > 0) {
	        sql += " m.oid_Unidade  = " + ed.getOid_Unidade () + " and ";
	      }
	      if (ed.getOid_Origem () > 0) {
	        sql += " m.oid_Origem = " + ed.getOid_Origem () + " and ";
	      }
	      if (ed.getOid_Conta () > 0) {
	        sql += " m.oid_Conta  = " + ed.getOid_Conta () + " and ";
	      }
	      if (doValida (ed.getDt_Movimento_Inicial ()) && doValida (ed.getDt_Movimento_Final ())) {
	        sql += " m.dt_Movimento between '" + ed.getDt_Movimento_Inicial () + "' and '" + ed.getDt_Movimento_Final () + "' and ";
	      }
	      else {
	        if (doValida (ed.getDt_Movimento_Inicial ())) {
	          sql += " m.dt_Movimento = '" + ed.getDt_Movimento_Inicial () + "' and ";
	        }
	        if (doValida (ed.getDt_Movimento_Final ())) {
	          sql += " m.dt_Movimento = '" + ed.getDt_Movimento_Final () + "' and ";
	        }
	      }
	      if (doValida (ed.getDt_Digitacao_Inicial ()) && doValida (ed.getDt_Digitacao_Final ())) {
	        sql += " m.dt_Digitacao between '" + ed.getDt_Digitacao_Inicial () + "' and '" + ed.getDt_Digitacao_Final () + "' and ";
	      }
	      if (doValida (ed.getTipo ())) {
	        if (!"A".equals (ed.getTipo ())) {
	          sql += " m.dm_Debito_Credito = '" + ed.getTipo () + "' and ";
	        }
	      }
	      if (doValida (ed.getTx_Historico_Complementar ())) {
	        sql += " m.tx_Historico_Complementar like '%" + ed.getTx_Historico_Complementar() + "%' and ";
	      }
	      if (ed.getOid_Movimento_Contabil () > 0) {
	        sql += " m.oid_Movimento_Contabil  = " + ed.getOid_Movimento_Contabil () + " and ";
	      }
	      if (ed.getOid_Centro_Custo () > 0) {
	        sql += " m.oid_Centro_Custo  = " + ed.getOid_Centro_Custo () + " and ";
	      }

	      if (ed.getVl_Lancamento_Inicial () > 0 && ed.getVl_Lancamento_Final () > 0 ) {
	          sql += " m.vl_Lancamento  >= " + ed.getVl_Lancamento_Inicial () + " and " + " m.vl_Lancamento  <= " + ed.getVl_Lancamento_Final () + " and ";
	      }else{
	          if (ed.getVl_Lancamento_Inicial () > 0 ) {
	              sql += " m.vl_Lancamento  = " + ed.getVl_Lancamento_Inicial () + " and ";
	          }
	          if (ed.getVl_Lancamento_Final () > 0 ) {
	              sql += " m.vl_Lancamento  = " + ed.getVl_Lancamento_Final () + " and ";
	          }
	      }

	      sql += " u.oid_unidade = m.oid_unidade and " +
	          " o.oid_origem = m.oid_origem and  " +
	          " c.oid_conta = m.oid_conta and  " +
	          " h.oid_historico = m.oid_historico and " +
	          " p.oid_pessoa = u.oid_pessoa and " +
	          " cc.oid_centro_custo = m.oid_centro_custo " +
	          " ORDER BY nr_lote, " +
	          " dt_Movimento, " +
	          " oid_Movimento_Contabil";
	      ResultSet res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        Movimento_ContabilED edVolta = new Movimento_ContabilED ();
	        edVolta = populaRegistro (res , edVolta);
	        list.add (edVolta);
	      }
	      return list;
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "lista(Movimento_ContabilED ed)");
	    }
	  }

}