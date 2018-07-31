package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_DepositoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.JavaUtil;


public class WMS_DepositoBD
  {

	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

  public WMS_DepositoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_DepositoED inclui (WMS_DepositoED ed) throws Excecoes {

    String sql = null;
    try {

      ed.setOid_Deposito (new Integer (util.getAutoIncremento ("oid_Deposito" , "Depositos")));

      sql = " INSERT INTO Depositos (" +
          "		OID_Deposito" +
          "		,oid_Unidade" +
          "		,CD_Deposito" +
          "		,NM_Deposito" +
          "		,DT_STAMP" +
          "		,Usuario_STAMP" +
          "		,DM_STAMP" +
          "		,DM_Tipo" +
          "		,CD_Local_Picking" +
          "		,CD_Local_Entrada" +
          "		,CD_Local_Carga" +
          "		,CD_Local_Saida" +
          "		,CD_Local_Descarga)" +
          " VALUES (" +
          ed.getOid_Deposito () +
          "," + ed.getOid_Unidade () +
          ",'" + ed.getCd_Deposito () + "'" +
          ",'" + ed.getNm_Deposito () + "'" +
          ",'" + ed.getDt_stamp () + "'" +
          ",'" + ed.getUsuario_Stamp () + "'" +
          ",'" + ed.getDm_Stamp () + "'" +
          ",'" + ed.getDM_Tipo () + "'" +
          ",'" + ed.getCD_Local_Picking () + "'" +
          ",'" + ed.getCD_Local_Entrada () + "'" +
          ",'" + ed.getCD_Local_Carga () + "'" +
          ",'" + ed.getCD_Local_Saida () + "'" +
          ",'" + ed.getCD_Local_Descarga () + "')";

      executasql.executarUpdate (sql);
      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "inclui()");
    }
  }

  public void altera (WMS_DepositoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Depositos SET ";
      sql += " 	CD_Deposito = '" + ed.getCd_Deposito () + "', ";
      sql += " 	NM_Deposito = '" + ed.getNm_Deposito () + "', ";
      sql += " 	DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " 	Usuario_STAMP = '" + ed.getUsuario_Stamp () + "', ";
      sql += " 	DM_STAMP = '" + ed.getDm_Stamp () + "', ";
      sql += " 	oid_Unidade = " + ed.getOid_Unidade () + ", ";
      sql += " 	CD_Local_Picking = '" + ed.getCD_Local_Picking () + "', ";
      sql += " 	CD_Local_Carga = '" + ed.getCD_Local_Carga () + "', ";
      sql += " 	CD_Local_Entrada = '" + ed.getCD_Local_Entrada () + "', ";
      sql += " 	CD_Local_Descarga = '" + ed.getCD_Local_Descarga () + "', ";
      sql += " 	CD_Local_Saida = '" + ed.getCD_Local_Saida () + "', ";
      sql += " 	DM_Tipo = '" + ed.getDM_Tipo () + "' ";
      sql += " WHERE oid_Deposito = " + ed.getOid_Deposito ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "altera()");
    }
  }

  public void deleta (WMS_DepositoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " DELETE FROM Depositos " +
          " WHERE oid_Deposito = " + ed.getOid_Deposito ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "deleta()");
    }
  }

  public ArrayList lista (WMS_DepositoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT Depositos.*" +
          " 	  ,Unidades.CD_Unidade" +
          "		  ,Pessoas.NM_Razao_Social" +
          "		  ,Pessoas.NM_Fantasia" +
          "		  ,Pessoas.NR_CNPJ_CPF" +
          " FROM Depositos, Unidades, Pessoas " +
          " WHERE Depositos.oid_Unidade = Unidades.oid_Unidade " +
          "   AND Unidades.oid_Pessoa = Pessoas.oid_Pessoa";

      if (ed.getOid_Deposito () != null && ed.getOid_Deposito ().intValue () > 0) {
        sql += " AND Depositos.oid_Deposito = " + ed.getOid_Deposito ();
      }
      if (util.doValida (ed.getCd_Deposito ())) {
        sql += " AND Depositos.Cd_Deposito = '" + ed.getCd_Deposito () + "'";
      }
      if (util.doValida (ed.getNm_Deposito ())) {
        sql += " AND Depositos.nm_Deposito LIKE '" + ed.getNm_Deposito () + "%'";
      }

      sql += " ORDER BY nm_Deposito";

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        WMS_DepositoED edVolta = new WMS_DepositoED ();
        edVolta.setOid_Deposito (new Integer (res.getInt ("oid_Deposito")));
        edVolta.setCd_Deposito (res.getString ("Cd_Deposito"));
        edVolta.setNm_Deposito (res.getString ("nm_Deposito"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
        edVolta.setNR_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));

        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "lista()");
    }
  }

  public WMS_DepositoED getByRecord (WMS_DepositoED ed) throws Excecoes {

    String sql = null;

    WMS_DepositoED edVolta = new WMS_DepositoED ();

    try {

      sql = " SELECT  *, Depositos.oid_Unidade " +
          " FROM Depositos" +
          "	  ,Unidades" +
          "	  ,Pessoas" +
          " WHERE Depositos.oid_Unidade = Unidades.oid_Unidade " +
          "   AND Unidades.oid_Pessoa = Pessoas.oid_Pessoa";

      if (ed.getOid_Deposito () != null && ed.getOid_Deposito ().intValue () > 0) {
        sql += "   AND Depositos.OID_Deposito = " + ed.getOid_Deposito ();
      }
      if (util.doValida (ed.getCd_Deposito ())) {
        sql += "   AND Depositos.CD_Deposito = '" + ed.getCd_Deposito () + "'";
      }

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new WMS_DepositoED ();
        edVolta.setOid_Deposito (new Integer (res.getInt ("oid_Deposito")));
        edVolta.setCd_Deposito (res.getString ("Cd_Deposito"));
        edVolta.setNm_Deposito (res.getString ("nm_Deposito"));
        edVolta.setOid_Unidade (new Integer (res.getInt ("oid_Unidade")));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
        edVolta.setNR_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
        edVolta.setDM_Tipo (res.getString ("DM_Tipo"));

        edVolta.setCD_Local_Picking (res.getString ("CD_Local_Picking"));
        edVolta.setCD_Local_Entrada (res.getString ("CD_Local_Entrada"));
        edVolta.setCD_Local_Carga (res.getString ("CD_Local_Carga"));
        edVolta.setCD_Local_Saida (res.getString ("CD_Local_Saida"));
        edVolta.setCD_Local_Descarga (res.getString ("CD_Local_Descarga"));
      }
      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecord()");

    }
  }

  public WMS_DepositoED getByCD_Deposito (String CD_Deposito) throws Excecoes {
    WMS_DepositoED ed = new WMS_DepositoED ();
    ed.setCd_Deposito (CD_Deposito);
    return this.getByRecord (ed);
  }

  public void geraRelatorio (WMS_DepositoED ed) throws Excecoes {

    //    String sql = null;
    //
    //    WMS_DepositoED edVolta = new WMS_DepositoED();
    //
    //    try{
    //
    //      sql = "select * from Depositos where oid_Deposito > 0";
    //
    //      if (ed.getCD_Deposito() != null && !ed.getCD_Deposito().equals("")){
    //        sql += " and CD_Deposito = '" + ed.getCD_Deposito() + "'";
    //      }
    //      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
    //        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
    //      }
    //
    //      ResultSet res = null;
    //      res = this.executasql.executarConsulta(sql);
    //
    //      DepositoRL Deposito_rl = new DepositoRL();
    //      Deposito_rl.geraRelatEstoque(res);
    //    }
    //    catch (Excecoes e){
    //      throw e;
    //    }
    //    catch(Exception exc){
    //      Excecoes exce = new Excecoes();
    //      exce.setExc(exc);
    //      exce.setMensagem("Erro no método listar");
    //      exce.setClasse(this.getClass().getName());
    //      exce.setMetodo("geraRelatorio(WMS_DepositoED ed)");
    //    }
    //
  }
}