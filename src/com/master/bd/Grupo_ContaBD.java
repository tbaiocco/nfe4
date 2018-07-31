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

import com.master.ed.Grupo_ContaED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Grupo_ContaBD {

  private ExecutaSQL executasql;

  public Grupo_ContaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  /********************************************************
   *
   *******************************************************/
  public Grupo_ContaED inclui (Grupo_ContaED ed) throws Excecoes {

    String sql = null;
//    long valOid = 0;
    Grupo_ContaED grupo_ContaED = new Grupo_ContaED ();

    try {

//      ResultSet rs = executasql.executarConsulta("select max(oid_Grupo_Conta) as result from Grupos_Contas");

      //pega proximo valor da chave
//      while (rs.next()) valOid = rs.getInt("result");


      sql = " insert into Grupos_Contas (OID_GRUPO_CONTA, NM_GRUPO_CONTA, DT_STAMP, USUARIO_STAMP, DM_STAMP, DM_DRE	) values ";
      sql += "(" + ed.getOid_Grupo_Conta () + ",'" + ed.getNm_Grupo_Conta () + "','" + ed.getDt_stamp () + "',";
      sql += "'" + ed.getUsuario_Stamp () + "','" + ed.getDm_Stamp () + "','" + ed.getDM_DRE () + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      //System.out.println(" ######### grupos conta = "+ sql);
      int i = executasql.executarUpdate (sql);
      grupo_ContaED.setOid_Grupo_Conta (ed.getOid_Grupo_Conta ());
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Grupo_Conta");
      excecoes.setMetodo ("inclui(Grupo_Conta_ED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return grupo_ContaED;

  }

  /********************************************************
   *
   *******************************************************/
  public void altera (Grupo_ContaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Grupos_Contas set ";
      sql += " NM_GRUPO_CONTA = '" + ed.getNm_Grupo_Conta () + "', ";
      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "', ";
      sql += " DM_DRE = '" + ed.getDM_DRE () + "' ";
      sql += " where oid_Grupo_Conta = " + ed.getOid_Grupo_Conta ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar Grupo_Conta");
      excecoes.setMetodo ("altera(Grupo_ContaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  /********************************************************
   *
   *******************************************************/
  public void deleta (Grupo_ContaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from Grupos_Contas WHERE oid_Grupo_Conta = ";
      sql += ed.getOid_Grupo_Conta ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Grupo_Conta");
      excecoes.setMetodo ("deleta(Grupo_ContaED");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList lista (Grupo_ContaED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = "select * from Grupos_Contas where oid_grupo_conta > 0";

      if (ed.getOid_Grupo_Conta () != null && !ed.getOid_Grupo_Conta ().equals ("")) {
        sql += " and oid_Grupo_Conta = '" + ed.getOid_Grupo_Conta () + "'";
      }
      if (ed.getNm_Grupo_Conta () != null && !ed.getNm_Grupo_Conta ().equals ("")) {
        sql += " and nm_Grupo_Conta LIKE '" + ed.getNm_Grupo_Conta () + "%'";
      }
      sql += " ORDER BY nm_Grupo_Conta ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        Grupo_ContaED edVolta = new Grupo_ContaED ();
        edVolta.setOid_Grupo_Conta (new Integer (res.getString ("oid_grupo_conta")));
        edVolta.setNm_Grupo_Conta (res.getString ("nm_grupo_conta"));

        edVolta.setNM_DRE (this.consulta_NM_DRE(res.getString ("DM_DRE")));

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro no método listar");
      excecoes.setMetodo ("lista(Grupo_Conta_ED");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  /********************************************************
   *
   *******************************************************/
  public Grupo_ContaED getByRecord (Grupo_ContaED ed) throws Excecoes {

    String sql = null;

    Grupo_ContaED edVolta = new Grupo_ContaED ();

    try {

      sql = " select * from grupos_contas where oid_grupo_Conta > 0";

      if (String.valueOf (ed.getOid_Grupo_Conta ()) != null &&
          !String.valueOf (ed.getOid_Grupo_Conta ()).equals ("0")) {
        sql += " and OID_Grupo_Conta = " + ed.getOid_Grupo_Conta ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Grupo_ContaED ();
        edVolta.setOid_Grupo_Conta (new Integer (res.getInt ("oid_grupo_conta")));
        edVolta.setNm_Grupo_Conta (res.getString ("nm_grupo_conta"));
        edVolta.setDM_DRE (res.getString ("DM_DRE"));

        edVolta.setNM_DRE (this.consulta_NM_DRE(res.getString ("DM_DRE")));

      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord");
      excecoes.setExc (exc);
      throw excecoes;

    }

    return edVolta;
  }

  public void geraRelatorio (Grupo_ContaED ed) throws Excecoes {

//    String sql = null;
//
//    Grupo_ContaED edVolta = new Grupo_ContaED();
//
//    try{
//
//      sql = "select * from Grupo_Contas where oid_Grupo_Conta > 0";
//
//      if (ed.getCD_Grupo_Conta() != null && !ed.getCD_Grupo_Conta().equals("")){
//        sql += " and CD_Grupo_Conta = '" + ed.getCD_Grupo_Conta() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Grupo_ContaRL Grupo_Conta_rl = new Grupo_ContaRL();
//      Grupo_Conta_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Grupo_ContaED ed)");
//    }
//
  }

  public String consulta_NM_DRE (String DM_DRE) throws Excecoes {
    String NM_DRE="---------------";

    if ("000".equals (DM_DRE))
      NM_DRE="ATIVO";
    if ("010".equals (DM_DRE))
      NM_DRE="RECEITA";
    if ("020".equals (DM_DRE))
      NM_DRE="IMPOSTOS";
    if ("030".equals (DM_DRE))
      NM_DRE="CUSTOS VARIAVEIS";
    if ("040".equals (DM_DRE))
      NM_DRE="DESPESAS";
    if ("050".equals (DM_DRE))
      NM_DRE="INVESTIMENTOS";
    if ("900".equals (DM_DRE))
      NM_DRE="PASSIVO";
    if ("999".equals (DM_DRE))
      NM_DRE="OUTRAS/NAO DRE";


    return NM_DRE;
  }


}