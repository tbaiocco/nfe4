package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.ContaED;
import com.master.ed.Natureza_OperacaoED;
import com.master.rl.ContaRL;
import com.master.rn.Natureza_OperacaoRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class ContaBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria ();

  public ContaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  /***************************************************************************
   *
   **************************************************************************/
  public ContaED inclui (ContaED ed) throws Excecoes {

    String sql = null;

    try {

      ed.setOid_Conta (new Integer (util.getAutoIncremento ("oid_Conta" , "Contas")));
      ed.setNr_Grau (ed.calcGrau (ed.getCd_Estrutural ()));

      sql = " INSERT INTO contas (" +
          "		OID_CONTA" +
          "		,NM_CONTA" +
          "		,CD_CONTA" +
          "		,DM_TIPO_OPERACAO" +
          "		,DM_APROPRIACAO" +
          "		,CD_CONTA_CONTABIL" +
          "		,DT_STAMP" +
          "		,USUARIO_STAMP" +
          "		,DM_STAMP" +
          "		,OID_HISTORICO" +
          "		,vl_Limite_Bloqueio_Compromisso" +
          "		,OID_GRUPO_CONTA" +
          "		,OID_TIPO_DOCUMENTO" +
          "		,DM_DESPESA" +
          "		,Oid_Natureza_Operacao " +
          "		,CD_ESTRUTURAL " +
          "		,CD_ESTRUTURAL_SPED " +
          "		,DM_CENTRO_CUSTO " +
          "		,DM_TIPO_CONTA " +
          "		,DM_ZERA " +
          "		,NR_GRAU) " +

          " VALUES (" +
          "'" + ed.getOid_Conta () + "'" +
          ",'" + ed.getNm_Conta () + "'" +
          ",'" + ed.getCd_Conta () + "'" +
          ",'" + ed.getDm_Tipo_Operacao () + "'" +
          ",'" + ed.getDm_Apropriacao () + "', ";
      if (!Utilitaria.doValida (ed.getCd_Conta_Contabil ()))
        sql += "null,";
      else sql += "'" + ed.getCd_Conta_Contabil () + "',";
      sql += "'" + ed.getDt_stamp () + "'" +
          ",'" + ed.getUsuario_Stamp () + "'" +
          ",'" + ed.getDm_Stamp () + "'" +
          "," + ed.getOid_Historico () +
          "," + ed.getvl_Limite_Bloqueio_Compromisso () +
          "," + ed.getOid_Grupo_Conta () +
          "," + ed.getOid_Tipo_Documento () + "" +
          ",'" + ed.getDm_Despesa () + "'" +
          "," + ed.getOid_Natureza_Operacao () +
          ",'" + ed.getCd_Estrutural () + "'" +
          ",'" + ed.getCd_Estrutural_Sped() + "'" +
          ",'" + ed.getDm_Centro_Custo () + "'" +
          ",'" + ed.getDm_Tipo_Conta () + "'" +
          ",'" + ed.getDm_Zera () + "'" +
          "," + ed.getNr_Grau () + ")";

      executasql.executarUpdate (sql);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ContaED ed)");
    }
    return ed;
  }

  /***************************************************************************
   *
   **************************************************************************/
  public void altera (ContaED ed) throws Excecoes {

    String sql = null;

    try {
      ed.setNr_Grau (ed.calcGrau (ed.getCd_Estrutural ()));
      sql = " UPDATE Contas SET ";
      sql += " NM_CONTA = '" + ed.getNm_Conta () + "', ";
      sql += " CD_CONTA = '" + ed.getCd_Conta () + "', ";
      if (ed.getDm_Tipo_Operacao () != null && !ed.getDm_Tipo_Operacao ().equals (""))
        sql += " DM_TIPO_OPERACAO = '" + ed.getDm_Tipo_Operacao () + "', ";
      else sql += " DM_TIPO_OPERACAO = null,";

      if (ed.getDm_Apropriacao () != null && !ed.getDm_Apropriacao ().equals (""))
        sql += " DM_APROPRIACAO = '" + ed.getDm_Apropriacao () + "', ";
      else sql += " DM_APROPRIACAO = null,";

      if (ed.getCd_Conta_Contabil () != null && !ed.getCd_Conta_Contabil ().equals (""))
        sql += " CD_CONTA_CONTABIL = '" + ed.getCd_Conta_Contabil () + "', ";
      else sql += " CD_CONTA_CONTABIL = null,";

      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "', ";
      sql += " OID_Historico = " + ed.getOid_Historico () + ", ";
      sql += " vl_Limite_Bloqueio_Compromisso = " + ed.getvl_Limite_Bloqueio_Compromisso () + ", ";
      //sql += " OID_GRUPO_CONTA = " + ed.getOid_Grupo_Conta () + ", ";
      sql += " OID_TIPO_DOCUMENTO = " + ed.getOid_Tipo_Documento () + ", ";
      sql += " dm_despesa = '" + ed.getDm_Despesa () + "', ";
      sql += " Oid_Natureza_Operacao = " + ed.getOid_Natureza_Operacao () + ", ";
      sql += " CD_ESTRUTURAL = '" + ed.getCd_Estrutural () + "', ";
      sql += " CD_ESTRUTURAL_SPED = '" + ed.getCd_Estrutural_Sped() + "', ";
      sql += " DM_CENTRO_CUSTO = '" + ed.getDm_Centro_Custo () + "', ";
      sql += " DM_TIPO_CONTA = '" + ed.getDm_Tipo_Conta () + "', ";
      sql += " DM_ZERA = '" + ed.getDm_Zera () + "', ";
      sql += " dm_libera_compromisso = '" + ed.getDm_libera_compromisso () + "', ";
      sql += " NR_GRAU = '" + ed.getNr_Grau () + "' ";
      sql += " WHERE oid_Conta = " + ed.getOid_Conta ();

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(ContaED ed)");
    }
  }

  public void altera_fluxo (ContaED ed) throws Excecoes {

	  String sql = null;

	    try {
	    	 sql = " UPDATE Contas SET ";
	    	 sql += " DM_Pedido_Compra = '" + ed.getDM_Pedido_Compra () + "', ";
	         sql += " DM_Fluxo_Caixa = '" + ed.getDM_Fluxo_Caixa () + "', ";
	         sql += " DM_Vencimento = '" + ed.getDM_Vencimento () + "' ";
	         sql += " WHERE oid_Conta = " + ed.getOid_Conta ();

	      executasql.executarUpdate (sql);
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_fluxo(ContaED ed)");
	    }
}

  public void altera_Grupo (ContaED ed) throws Excecoes {

	    String sql = null;

	    try {
	      sql = " UPDATE Contas SET " +
	    	  	" OID_GRUPO_CONTA = " + ed.getOid_Grupo_Conta () +
	    	  	" WHERE oid_Conta = " + ed.getOid_Conta ();

	      executasql.executarUpdate (sql);
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Grupo(ContaED ed)");
	    }
  }

  public void altera_Orcamento (ContaED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " UPDATE Orcamentos_Contas SET ";
      sql += " vl_Orcado = " + ed.getVL_Orcado ();
      sql += " WHERE oid_Orcamento_Conta = '" + ed.getOid_Orcamento_Conta () + "'";
      // System.out.println (sql);

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Orcamento(ContaED ed)");
    }
  }

  /***************************************************************************
   *
   **************************************************************************/
  public void deleta (ContaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "DELETE FROM Orcamentos_Contas WHERE oid_Conta = ";
      sql += "(" + ed.getOid_Conta () + ")";
      executasql.executarUpdate (sql);

      sql = "DELETE FROM Contas WHERE oid_Conta = ";
      sql += "(" + ed.getOid_Conta () + ")";

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta(ContaED ed)");
    }
  }

  /***************************************************************************
   *
   **************************************************************************/
  public ArrayList lista (ContaED ed) throws Excecoes {

    String ordem = ed.getDM_Relatorio ();
    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT * " +
          " FROM " +
          " Contas as c , " +
          " Historicos as h , " +
          " Tipos_Documentos as t " +
          " WHERE " +
          " c.oid_Historico = h.oid_Historico and " +
          " c.oid_tipo_documento = t.oid_tipo_documento " +
          " and cd_Conta <> '_$_' ";

      if (ed.getOid_Grupo_Conta () != null && ed.getOid_Grupo_Conta ().intValue () > 0)
        sql += " and oid_grupo_conta = " + ed.getOid_Grupo_Conta ();
      if (ed.getOid_Conta () != null && ed.getOid_Conta ().intValue () > 0)
        sql += " and oid_conta = " + ed.getOid_Conta ();
      if (Utilitaria.doValida (ed.getCd_Conta ()))
        sql += " and CD_Conta = '" + ed.getCd_Conta () + "'";
      if (Utilitaria.doValida (ed.getNm_Conta ()))
        sql += " and nm_conta Like '" + ed.getNm_Conta () + "%'";
      if (Utilitaria.doValida (ed.getCd_Estrutural ()))
        sql += " and CD_ESTRUTURAL Like '" + ed.getCd_Estrutural () + "%'";
      if (Utilitaria.doValida (ed.getDm_Tipo_Conta ())) {
        if (!"T".equals (ed.getDm_Tipo_Conta ()))
          sql += " and DM_TIPO_CONTA = '" + ed.getDm_Tipo_Conta () + "'";
      }

      if (Utilitaria.doValida (ed.getCd_Estrutural_Inicial ())) {
        sql += " and cd_Estrutural >= '" +
            ed.getCd_Estrutural_Inicial () + "' ";
      }
      if (Utilitaria.doValida (ed.getCd_Estrutural_Inicial ()) || Utilitaria.doValida (ed.getCd_Estrutural_Final ())) {
        sql += " and cd_Estrutural <= '" +
            ed.getCd_Estrutural_Final () + "' ";
      }

      sql += " ORDER BY ";
      if (Utilitaria.doValida (ed.getDM_Relatorio ())) {
        if ("1".equals (ordem))
          sql += " nm_conta ";
        if ("2".equals (ordem))
          sql += " CD_Conta ";
        if ("3".equals (ordem))
          sql += " CD_ESTRUTURAL ";
      }
      else {
        sql += " nm_conta ";
      }
      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {

        ContaED edVolta = new ContaED ();
        edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
        edVolta.setCd_Conta (res.getString ("cd_conta"));
        edVolta.setNm_Conta (res.getString ("nm_conta"));
        edVolta.setCd_Estrutural (res.getString ("CD_ESTRUTURAL"));
        edVolta.setCd_Estrutural_Sped(res.getString ("CD_ESTRUTURAL_SPED"));
        edVolta.setDm_Tipo_Conta (res.getString ("DM_TIPO_CONTA"));
        edVolta.setDm_Centro_Custo (res.getString ("DM_CENTRO_CUSTO"));
        edVolta.setOid_Historico (new Integer (res.getInt ("oid_Historico")));
        edVolta.setCd_Historico (res.getString ("cd_historico"));
        edVolta.setNm_Historico (res.getString ("nm_historico"));
        edVolta.setDm_Zera (res.getString ("nm_historico"));
        edVolta.setNr_Grau (new Integer (res.getInt ("nr_grau")));
        edVolta.setVL_Orcado (res.getDouble ("VL_Orcado"));


        edVolta.setOid_Grupo_Conta (new Integer (res.getInt ("oid_grupo_conta")));
        sql = " SELECT NM_Grupo_Conta FROM Grupos_Contas WHERE oid_Grupo_Conta = " + edVolta.getOid_Grupo_Conta ();
        ResultSet res2 = this.executasql.executarConsulta (sql);
        if (res2.next ()) edVolta.setNm_Grupo_Conta (res2.getString ("NM_Grupo_Conta"));

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    return list;
  }

  /***************************************************************************
   *
   **************************************************************************/
  public ContaED getByRecord (ContaED ed) throws Excecoes {

    String sql = null;

    ContaED edVolta = new ContaED ();

    try {

      sql = " SELECT * FROM contas, Historicos, tipos_documentos, empresas " +
          " WHERE contas.oid_Historico = Historicos.oid_Historico " +
          "   AND contas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
          "   AND cd_Conta <> '_$_' ";

      if (ed.getOid_Conta () != null && !String.valueOf (ed.getOid_Conta ()).equals ("0")) {
        sql += " AND OID_conta = " + ed.getOid_Conta ();
      }
      else
    	if (ed.getCd_Conta () != null && !ed.getCd_Conta ().equals ("0") && !ed.getCd_Conta ().equals ("")) {
        sql += " and contas.cd_Conta = '" + ed.getCd_Conta () + "'";

        if (Utilitaria.doValida (ed.getDm_Tipo_Conta ())) {
          sql += " and dm_Tipo_Conta = '" + ed.getDm_Tipo_Conta () + "' ";
        }
      }

      // System.out.println ("contaVolta sql->>>" + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new ContaED ();

        edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
        edVolta.setCd_Conta (res.getString ("cd_Conta"));
        edVolta.setNm_Conta (res.getString ("nm_conta"));
        edVolta.setCd_Historico (res.getString ("cd_historico"));
        edVolta.setNm_Historico (res.getString ("nm_historico"));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
        edVolta.setCd_Conta_Contabil (res.getString ("cd_conta_contabil"));
        edVolta.setDm_Apropriacao (res.getString ("dm_apropriacao"));
        edVolta.setDm_Tipo_Operacao (res.getString ("dm_tipo_operacao"));
        edVolta.setOid_Empresa (new Integer (res.getInt ("oid_Empresa")));
        edVolta.setvl_Limite_Bloqueio_Compromisso (res.getDouble ("vl_Limite_Bloqueio_Compromisso"));
        edVolta.setVL_Orcado (res.getDouble ("VL_Orcado"));
        edVolta.setNm_Empresa (res.getString ("Nm_Empresa"));
        edVolta.setOid_Historico (new Integer (res.getInt ("oid_Historico")));
        edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
        edVolta.setDm_Despesa (res.getString ("dm_despesa"));
        edVolta.setDm_libera_compromisso (res.getString ("dm_libera_compromisso"));

        edVolta.setOid_Natureza_Operacao (new Integer (res.getInt ("Oid_Natureza_Operacao")));
        if (edVolta.getOid_Natureza_Operacao ().intValue () > 0) {
          Natureza_OperacaoED edNat = new Natureza_OperacaoRN ().getByRecord (new Natureza_OperacaoED (edVolta.getOid_Natureza_Operacao ()));
          edVolta.setCd_Natureza_Operacao (edNat.getCd_Natureza_Operacao ());
          edVolta.setNm_Natureza_Operacao (edNat.getNm_Natureza_Operacao ());
        }

        edVolta.setOid_Grupo_Conta (new Integer (res.getInt ("oid_grupo_conta")));
        sql = " SELECT NM_Grupo_Conta FROM Grupos_Contas WHERE oid_Grupo_Conta = " + edVolta.getOid_Grupo_Conta ();
        ResultSet res2 = this.executasql.executarConsulta (sql);
        if (res2.next ())
          edVolta.setNm_Grupo_Conta (res2.getString ("NM_Grupo_Conta"));

        edVolta.setCd_Estrutural (res.getString ("cd_Estrutural"));
        edVolta.setCd_Estrutural_Sped(res.getString ("CD_ESTRUTURAL_SPED"));
        edVolta.setDm_Centro_Custo (res.getString ("dm_Centro_Custo"));
        edVolta.setDm_Tipo_Conta (res.getString ("dm_Tipo_Conta"));
        edVolta.setDm_Zera (res.getString ("dm_Zera"));


        edVolta.setDM_Fluxo_Caixa (res.getString("DM_Fluxo_Caixa"));
        edVolta.setDM_Pedido_Compra(res.getString("DM_Pedido_Compra"));
        edVolta.setDM_Vencimento(res.getString("DM_Vencimento"));
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "getByRecord(ContaED ed)");
    }

    return edVolta;
  }

  public ContaED getByOrcamento (ContaED ed) throws Excecoes {

    String sql = null;

    ContaED edVolta = new ContaED ();

    try {

      sql = " SELECT Orcamentos_Contas.*, Contas.CD_Conta, Contas.NM_Conta, Contas.oid_Conta " +
            " FROM   Contas, Orcamentos_Contas  " +
            " WHERE  Contas.oid_Conta = Orcamentos_Contas.oid_Conta ";

      if (ed.getOid_Orcamento_Conta () != null && !ed.getOid_Orcamento_Conta ().equals ("null") && !ed.getOid_Orcamento_Conta ().equals ("")) {
        sql += " AND   Orcamentos_Contas.oid_Orcamento_conta = '" + ed.getOid_Orcamento_Conta () + "'";
      }
      else {
        sql += " AND   Orcamentos_Contas.oid_conta = '" + ed.getOid_Conta ().intValue () + "'" +
               " AND   Orcamentos_Contas.Dm_Orcado = '" + ed.getDm_Orcado () + "'";
      }

      System.out.println ("getByOrcamento=>>> " + sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
        edVolta.setOid_Orcamento_Conta (res.getString ("Oid_Orcamento_Conta"));
        edVolta.setCd_Conta (res.getString ("cd_conta"));
        edVolta.setNm_Conta (res.getString ("nm_conta"));
        edVolta.setVL_Orcado (res.getDouble ("VL_Orcado"));
        edVolta.setDm_Orcado (res.getString ("Dm_Orcado"));

        this.saldo_Orcamento (edVolta);

      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "getByRecord(ContaED ed)");
    }

    return edVolta;
  }

  public byte[] geraRelacaoContas (ContaED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    ResultSet res = null;

    sql = " SELECT * FROM contas, grupos_contas" +
        " WHERE contas.oid_grupo_Conta = grupos_contas.oid_grupo_conta " +

        " and cd_Conta <> '_$_' ";

    if (ed.getOid_Grupo_Conta () != null && ed.getOid_Grupo_Conta ().intValue () > 0)
      sql += " AND contas.oid_grupo_conta = " + ed.getOid_Grupo_Conta ();

    if ("2".equals (ed.getDM_Relatorio ())) {
      sql += " ORDER BY contas.cd_conta_Contabil ";
    }
    else sql += " ORDER BY grupos_contas.nm_grupo_conta, Contas.nm_conta ";

    res = this.executasql.executarConsulta (sql.toString ());

    ContaRL conRL = new ContaRL ();

    b = conRL.geraRelacaoContas (res , ed);
    return b;
  }

  public ContaED getByCD (ContaED ed) throws Excecoes {

    String sql = null;

    ContaED edVolta = new ContaED ();

    try {

      sql = " SELECT * FROM contas " +
          " WHERE  " +
          " cd_Conta = '" + ed.getCd_Conta () + "'" +
          "  and cd_Conta <> '_$_' ";

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new ContaED ();
        edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
        edVolta.setNm_Conta (res.getString ("nm_conta"));
        edVolta.setDm_Tipo_Operacao (res.getString ("dm_tipo_operacao"));
        edVolta.setDm_Apropriacao (res.getString ("dm_apropriacao"));
        edVolta.setOid_Historico (new Integer (res.getInt ("oid_Historico")));
        edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
        edVolta.setDm_Despesa (res.getString ("dm_despesa"));
        edVolta.setCd_Conta (res.getString ("cd_Conta"));
        edVolta.setOid_Natureza_Operacao (new Integer (res.getInt ("Oid_Natureza_Operacao")));
        edVolta.setCd_Estrutural (res.getString ("cd_Estrutural"));
        edVolta.setDm_Tipo_Conta (res.getString ("dm_Tipo_Conta"));
        edVolta.setDm_Zera (res.getString ("dm_Zera"));
        edVolta.setNr_Grau (new Integer (res.getInt ("nr_grau")));
        edVolta.setDm_Centro_Custo (res.getString ("dm_Centro_Custo"));
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "getByRecord(ContaED ed)");
    }

    return edVolta;
  }

  public byte[] geraRelOrcamento (ContaED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    ResultSet res = null;

    sql = " SELECT Orcamentos_Contas.*, Contas.CD_Conta, Contas.NM_Conta, Contas.oid_Conta " +
        " FROM  Contas, Orcamentos_Contas  " +
        " WHERE Contas.oid_Conta = Orcamentos_Contas.oid_Conta " +
        " AND   Contas.cd_Conta <> '_$_' ";

    if (ed.getOid_Conta () != null && ed.getOid_Conta ().intValue () > 0)
      sql += " AND Orcamentos_Contas.oid_conta = " + ed.getOid_Conta ();

    if (Utilitaria.doValida (ed.getDm_Orcado ())) {
      sql += " AND Orcamentos_Contas.Dm_Orcado = '" + ed.getDm_Orcado () + "'";
    }
    sql += " ORDER BY Contas.NM_Conta, Orcamentos_Contas.dm_orcado ";

    res = this.executasql.executarConsulta (sql.toString ());

    ContaRL conRL = new ContaRL ();

    b = conRL.geraRelOrcamento (res , ed);
    return b;
  }

  public ContaED getByEstrutural (ContaED ed) throws Excecoes {

    String sql = null;

    ContaED edVolta = new ContaED ();

    try {

      sql = " SELECT * FROM contas " +
          " WHERE  " +
          " cd_Estrutural = '" + ed.getCd_Estrutural () + "'" +
          "  and cd_Conta <> '_$_' ";

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new ContaED ();
        edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
        edVolta.setNm_Conta (res.getString ("nm_conta"));
        edVolta.setDm_Tipo_Operacao (res.getString ("dm_tipo_operacao"));
        edVolta.setDm_Apropriacao (res.getString ("dm_apropriacao"));
        edVolta.setOid_Historico (new Integer (res.getInt ("oid_Historico")));
        edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
        edVolta.setDm_Despesa (res.getString ("dm_despesa"));
        edVolta.setCd_Conta (res.getString ("cd_Conta"));
        edVolta.setOid_Natureza_Operacao (new Integer (res.getInt ("Oid_Natureza_Operacao")));
        edVolta.setCd_Estrutural (res.getString ("cd_Estrutural"));
        edVolta.setDm_Tipo_Conta (res.getString ("dm_Tipo_Conta"));
        edVolta.setDm_Zera (res.getString ("dm_Zera"));
        edVolta.setNr_Grau (new Integer (res.getInt ("nr_grau")));
        edVolta.setDm_Centro_Custo (res.getString ("dm_Centro_Custo"));
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "getByRecord(ContaED ed)");
    }

    return edVolta;
  }

  public ArrayList lista_Orcamento (ContaED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT Orcamentos_Contas.*, Contas.CD_Conta, Contas.NM_Conta, Contas.oid_Conta " +
            " FROM  Contas, Orcamentos_Contas  " +
            " WHERE Contas.oid_Conta = Orcamentos_Contas.oid_Conta ";

      if (ed.getOid_Conta () != null && ed.getOid_Conta ().intValue () > 0)
        sql += " AND Orcamentos_Contas.oid_conta = " + ed.getOid_Conta ();

      if (Utilitaria.doValida (ed.getDm_Orcado ()) && !"null-null".equals(ed.getDm_Orcado ())) {
        sql += " AND Orcamentos_Contas.Dm_Orcado = '" + ed.getDm_Orcado () + "'";
      }
      sql += " ORDER BY Contas.NM_Conta, Orcamentos_Contas.dm_orcado ";

      System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {

        ContaED edVolta = new ContaED ();
        edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
        edVolta.setOid_Orcamento_Conta (res.getString ("Oid_Orcamento_Conta"));
        edVolta.setCd_Conta (res.getString ("cd_conta"));
        edVolta.setNm_Conta (res.getString ("nm_conta"));
        edVolta.setVL_Orcado (res.getDouble ("VL_Orcado"));
        edVolta.setDm_Orcado (res.getString ("Dm_Orcado"));
        edVolta = this.saldo_Orcamento (edVolta);
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    return list;
  }

  public ContaED inclui_Orcamento (ContaED ed) throws Excecoes {

    String sql = null;

    try {
      ed.setOid_Orcamento_Conta (String.valueOf (100000 + ed.getOid_Conta ().intValue ()) + "-" + ed.getDm_Orcado ());
      sql = " INSERT INTO Orcamentos_Contas (" +
          "		 Oid_Orcamento_CONTA" +
          "		,Oid_Conta" +
          "		,DM_Orcado" +
          "		,DT_STAMP" +
          "		,USUARIO_STAMP" +
          "		,DM_STAMP" +
          "		,VL_Orcado)" +
          " VALUES (" +
          "'" + ed.getOid_Orcamento_Conta () + "'" +
          ",'" + ed.getOid_Conta () + "'" +
          ",'" + ed.getDm_Orcado () + "'" +
          ",'" + ed.getDt_stamp () + "'" +
          ",'" + ed.getUsuario_Stamp () + "'" +
          ",'" + ed.getDm_Stamp () + "'" +
          "," + ed.getVL_Orcado () + ")";

      // System.out.println (sql);

      executasql.executarUpdate (sql);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ContaED ed)");
    }
    return ed;
  }

  public void deleta_Orcamento (ContaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "DELETE FROM Orcamentos_Contas WHERE oid_Orcamento_Conta = ";
      sql += "'" + ed.getOid_Orcamento_Conta () + "'";

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta(ContaED ed)");
    }
  }

  public ContaED copia_Orcamento (ContaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " SELECT Orcamentos_Contas.VL_Orcado, Orcamentos_Contas.oid_Conta " +
          " FROM  Contas, Orcamentos_Contas  " +
          " WHERE Contas.oid_Conta = Orcamentos_Contas.oid_Conta " +
          " AND   Orcamentos_Contas.Dm_Orcado = '" + ed.getDm_Orcado () + "'";

      if (ed.getOid_Conta () != null && !ed.getOid_Conta ().equals ("null") && !ed.getOid_Conta ().equals ("")) {
        sql += " AND   Orcamentos_Contas.oid_conta = '" + ed.getOid_Conta ().intValue () + "'";
      }

      // System.out.println ("getByOrcamento=>>> " + sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        ContaED edCopia = new ContaED ();
        edCopia.setOid_Conta (new Integer (res.getInt ("oid_conta")));
        edCopia.setVL_Orcado (res.getDouble ("VL_Orcado"));
        edCopia.setDm_Orcado (ed.getDm_Orcado_Destino ());

        // System.out.println ("VL_Orcado=>>> " + res.getDouble ("VL_Orcado"));

        this.inclui_Orcamento (edCopia);

      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "copia_Orcamento(ContaED ed)");
    }
    return ed;
  }

  private ContaED saldo_Orcamento (ContaED ed) throws Excecoes {

    ed.setVL_Saldo (ed.getVL_Orcado ());
    String sql = "";
    ResultSet res = null;
    try {

      sql = " SELECT SUM (vl_compra) as tt_compra " +
            " FROM   Orcamentos_Compras " +
            " WHERE  oid_orcamento_conta = '" + ed.getOid_Orcamento_Conta () + "'";
      // System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        ed.setVL_Apropriado (res.getDouble ("tt_compra"));
        ed.setVL_Saldo (ed.getVL_Orcado () - ed.getVL_Apropriado ());
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    return ed;
  }

  public ArrayList listaToSaldo(ContaED ed) throws Excecoes {

	    String ordem = ed.getDM_Relatorio ();
	    String sql = null;
	    ArrayList list = new ArrayList ();

	    try {

	      sql = " SELECT * " +
	          " FROM " +
	          " Contas as c " +
	          " WHERE " +
	          " cd_Conta <> '_$_' ";

	      if (ed.getOid_Conta () != null && ed.getOid_Conta ().intValue () > 0)
	        sql += " and oid_conta = " + ed.getOid_Conta ();
	      if (util.doValida (ed.getCd_Conta ()))
	        sql += " and CD_Conta = '" + ed.getCd_Conta () + "'";
	      if (util.doValida (ed.getNm_Conta ()))
	        sql += " and nm_conta Like '" + ed.getNm_Conta () + "%'";
	      if (util.doValida (ed.getCd_Estrutural ()))
	        sql += " and CD_ESTRUTURAL Like '" + ed.getCd_Estrutural () + "%'";
	      if (util.doValida (ed.getCd_Estrutural_Sped ()))
	          sql += " and CD_ESTRUTURAL_SPED Like '" + ed.getCd_Estrutural_Sped () + "%'";
	      if (util.doValida (ed.getDm_Tipo_Conta ())) {
	        if (!"T".equals (ed.getDm_Tipo_Conta ()))
	          sql += " and DM_TIPO_CONTA = '" + ed.getDm_Tipo_Conta () + "'";
	      }

	      if (util.doValida (ed.getCd_Estrutural_Inicial ())) {
	        sql += " and cd_Estrutural >= '" +
	            ed.getCd_Estrutural_Inicial () + "' ";
	      }
	      if (util.doValida (ed.getCd_Estrutural_Inicial ()) || util.doValida (ed.getCd_Estrutural_Final ())) {
	        sql += " and cd_Estrutural <= '" +
	            ed.getCd_Estrutural_Final () + "' ";
	      }

	      sql += " ORDER BY ";
	      if (util.doValida (ed.getDM_Relatorio ())) {
	        if ("1".equals (ordem))
	          sql += " nm_conta ";
	        if ("2".equals (ordem))
	          sql += " CD_Conta ";
	        if ("3".equals (ordem))
	          sql += " CD_ESTRUTURAL ";
	      }
	      else {
	        sql += " nm_conta ";
	      }
	      ResultSet res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {

	        ContaED edVolta = new ContaED ();
	        edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
	        edVolta.setCd_Conta (res.getString ("cd_conta"));
	        edVolta.setNm_Conta (res.getString ("nm_conta"));
	        edVolta.setCd_Estrutural (res.getString ("CD_ESTRUTURAL"));
	        edVolta.setCd_Estrutural_Sped (res.getString ("CD_ESTRUTURAL_SPED"));
	        edVolta.setDm_Tipo_Conta (res.getString ("DM_TIPO_CONTA"));
	        edVolta.setDm_Centro_Custo (res.getString ("DM_CENTRO_CUSTO"));
	        edVolta.setOid_Historico (new Integer (res.getInt ("oid_Historico")));
	        edVolta.setNr_Grau (new Integer (res.getInt ("nr_grau")));

	        list.add (edVolta);
	      }
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "listaToSaldo()");
	    }
	    return list;
	  }

}
