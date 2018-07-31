package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_LocalizacaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class WMS_LocalizacaoBD	{

	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

  public WMS_LocalizacaoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_LocalizacaoED inclui (WMS_LocalizacaoED ed) throws Excecoes {

    String sql = null;

    try {
      ed.setOid_Localizacao (util.getAutoIncremento ("oid_Localizacao" , "Localizacoes"));
      //*** Concatena CD_Localizacao
       ed.setCD_Localizacao (JavaUtil.LFill (ed.getOid_Deposito () , 2 , true) + ed.getNm_Rua () + ed.getNr_Endereco () + ed.getNr_Apartamento ());
      sql = " INSERT INTO Localizacoes (" +
          "		 oid_localizacao" +
          "		,oid_deposito" +
          "		,CD_Localizacao" +
          "		,nm_rua" +
          "		,nr_endereco" +
          "		,nr_apartamento" +
          "		, dm_situacao) " +
          " VALUES (" +
          +ed.getOid_Localizacao () +
          "," + ed.getOid_Deposito () +
          ",'" + ed.getCD_Localizacao () + "'" +
          ",'" + ed.getNm_Rua () + "'" +
          ",'" + ed.getNr_Endereco () + "'" +
          ",'" + ed.getNr_Apartamento () + "'" +
          ",'" + ed.getDM_Situacao () + "')";

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "inclui()");
    }
    return ed;
  }

  public WMS_LocalizacaoED altera (WMS_LocalizacaoED ed) throws Excecoes {

    String sql = null;

    try {

      String CD_Localizacao = (JavaUtil.LFill (ed.getOid_Deposito () , 2 , true) + ed.getNm_Rua () + ed.getNr_Endereco () + ed.getNr_Apartamento ());

      sql = " UPDATE Localizacoes SET ";
      sql += " 	nm_rua = '" + ed.getNm_Rua () + "',";
      sql += " 	nr_endereco = '" + ed.getNr_Endereco () + "',";
      sql += " 	nr_apartamento = '" + ed.getNr_Apartamento () + "',";
      sql += " 	CD_localizacao = '" + CD_Localizacao + "',";
      sql += " 	dm_situacao = '" + ed.getDM_Situacao () + "'";
      sql += " WHERE oid_localizacao = " + ed.getOid_Localizacao ();

      executasql.executarUpdate (sql);
      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "altera()");
    }
  }

  public void deleta (WMS_LocalizacaoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " DELETE FROM Localizacoes " +
          " WHERE oid_localizacao = '" + ed.getOid_Localizacao () + "'";

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "deleta()");
    }
  }

  public WMS_LocalizacaoED getByRecord (WMS_LocalizacaoED ed) throws Excecoes {

    String sql = null;
    WMS_LocalizacaoED edVolta = new WMS_LocalizacaoED ();

    try {
      sql = " SELECT * " +
          " FROM Localizacoes" +
          "	  	,Depositos" +
          "		,Unidades" +
          "		,Pessoas " +
          " WHERE Localizacoes.oid_Deposito = Depositos.oid_Deposito " +
          "   AND Depositos.oid_Unidade = Unidades.oid_Unidade " +
          "   AND Unidades.oid_Pessoa = Pessoas.oid_Pessoa ";

      if (ed.getOid_Deposito () > 0) {
        sql += "   AND Localizacoes.oid_Deposito = " + ed.getOid_Deposito ();
      }
      if (ed.getOid_Localizacao () > 0) {
        sql += "   AND Localizacoes.oid_Localizacao = '" + ed.getOid_Localizacao () + "'";
      }
      if (util.doValida (ed.getCD_Localizacao ())) {
        sql += "   AND Localizacoes.CD_Localizacao = '" + ed.getCD_Localizacao () + "'";
      }

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new WMS_LocalizacaoED ();
        edVolta.setOid_Deposito (res.getInt ("oid_deposito"));
        edVolta.setOid_Localizacao (res.getInt ("oid_localizacao"));
        edVolta.setCD_Localizacao (res.getString ("CD_localizacao"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setNM_Deposito (res.getString ("NM_Deposito"));
        edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNm_Rua (res.getString ("nm_rua"));
        edVolta.setNr_Endereco (res.getString ("nr_endereco"));
        edVolta.setNr_Apartamento (res.getString ("nr_apartamento"));
      }
      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecord()");
    }
  }

  public WMS_LocalizacaoED getByOid_Localizacao (WMS_LocalizacaoED ed) throws Excecoes {

    return this.getByRecord (ed);
  }

  public WMS_LocalizacaoED getByOid_Deposito (WMS_LocalizacaoED ed) throws Excecoes {

    return this.getByRecord (ed);
  }

  public ArrayList lista (WMS_LocalizacaoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT * " +
          " FROM Localizacoes" +
          "	    ,Depositos" +
          "	  	,Unidades" +
          "	  	,Pessoas " +
          " WHERE Localizacoes.oid_Deposito = Depositos.oid_Deposito " +
          "   AND Depositos.oid_Unidade = Unidades.oid_Unidade " +
          "   AND Unidades.oid_Pessoa = Pessoas.oid_Pessoa ";
      if (ed.getOid_Deposito () > 0) {
        sql += " AND Localizacoes.oid_Deposito = " + ed.getOid_Deposito ();
      }
      else {
        if (util.doValida (ed.getNm_Rua ())) {
          sql += " AND Localizacoes.nm_rua LIKE '" + ed.getNm_Rua () + "%'";
        }
        if (util.doValida (ed.getNr_Endereco ())) {
          sql += " AND Localizacoes.Nr_Endereco = '" + ed.getNr_Endereco () + "'";
        }
        if (util.doValida (ed.getNr_Apartamento ())) {
          sql += " AND Localizacoes.Nr_Apartamento = '" + ed.getNr_Apartamento () + "'";
        }
        if (util.doValida (ed.getNM_Deposito() )) {
            sql += " AND Depositos.nm_deposito LIKE '" + ed.getNM_Deposito() + "%'";
          }
      }
      sql += " ORDER BY Localizacoes.oid_Deposito, Localizacoes.NM_RUA, Localizacoes.NR_ENDERECO ";


      ResultSet res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        WMS_LocalizacaoED edVolta = new WMS_LocalizacaoED ();
        edVolta.setOid_Deposito (res.getInt ("oid_deposito"));
        edVolta.setOid_Localizacao (res.getInt ("oid_localizacao"));
        edVolta.setCD_Localizacao (res.getString ("CD_localizacao"));
        edVolta.setNM_Deposito (res.getString ("NM_Deposito"));
        edVolta.setNm_Rua (res.getString ("NM_Rua"));
        edVolta.setNr_Endereco (res.getString ("NR_ENDERECO"));
        edVolta.setNr_Apartamento (res.getString ("NR_APARTAMENTO"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "lista()");
    }
    return list;
  }
}