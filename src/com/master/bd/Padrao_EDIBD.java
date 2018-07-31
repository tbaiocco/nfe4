package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Padrao_EDIED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Padrao_EDIBD {

  private ExecutaSQL executasql;

  public Padrao_EDIBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Padrao_EDIED incluiPadrao(Padrao_EDIED padrao_ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    ResultSet rs = null;
    ResultSet res = null;

    Padrao_EDIED padraoED = (Padrao_EDIED)padrao_ed;

    try{

    sql = "SELECT MAX(padroes_edi.OID_PADRAO) as oid FROM padroes_edi";

    rs = this.executasql.executarConsulta(sql);


    while(rs.next()){

    valOid = rs.getLong("oid") + 1;

    }

    padraoED.setOid_padrao(valOid);
    padraoED.setCd_padrao(padrao_ed.getCd_padrao());
    padraoED.setNm_descricao_padrao(padrao_ed.getNm_descricao_padrao());
    padraoED.setDt_stamp_padrao(padrao_ed.getDt_stamp_padrao());
    padraoED.setUsuario_stamp(padrao_ed.getUsuario_stamp());
    padraoED.setOid_tipo_padrao(padrao_ed.getOid_tipo_padrao());

    padraoED.setDm_tipo_padrao(padrao_ed.getDm_tipo_padrao());
    padraoED.setDm_tipo_transacao(padrao_ed.getDm_tipo_transacao());
    padraoED.setDm_classe(padrao_ed.getDm_classe());

//// System.out.println(padraoED.getOid_padrao());

    sql = "INSERT INTO PADROES_EDI (oid_padrao, "+
	  "cd_padrao, nm_descricao, dm_classe, dm_tipo_padrao, dm_tipo_transacao, dt_stamp, usuario_stamp, oid_tipo_padrao) "+
	  "VALUES (" + padraoED.getOid_padrao() +", '" +
	  padraoED.getCd_padrao() + "', '" +
	  padraoED.getNm_descricao_padrao() + "', '" +
	  padraoED.getDm_classe() + "', '" +
	  padraoED.getDm_tipo_padrao() + "', '" +
	  padraoED.getDm_tipo_transacao() + "', '" +
	  padraoED.getDt_stamp_padrao() + "', '" +
	  padraoED.getUsuario_stamp() + "', " +
	  padraoED.getOid_tipo_padrao() + ")";
      //// System.out.println(sql);

    executasql.executarUpdate(sql);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("incluiPadrao");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return padraoED;
  }

  public Padrao_EDIED incluiTipo(Padrao_EDIED padrao_ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    ResultSet rs = null;
    ResultSet res = null;

    Padrao_EDIED padraoED = (Padrao_EDIED)padrao_ed;

    try{

    sql = "SELECT MAX(tipos_padroes_edi.OID_TIPO_PADRAO) as oid_tipo FROM tipos_padroes_edi";

    rs = this.executasql.executarConsulta(sql);


    while(rs.next()){

    valOid = rs.getLong("oid_tipo") + 1;
    padraoED.setOid_tipo_padrao(valOid);
    }

    padraoED.setCd_tipo(padrao_ed.getCd_tipo());
    padraoED.setNm_descricao_tipo(padrao_ed.getNm_descricao_tipo());
    padraoED.setDt_stamp_tipo(padrao_ed.getDt_stamp_tipo());
    padraoED.setUsuario_stamp_tipo(padrao_ed.getUsuario_stamp_tipo());


    sql = "INSERT INTO TIPOS_PADROES_EDI (oid_tipo_padrao, "+
	  "cd_tipo, nm_descricao, dt_stamp, usuario_stamp) "+
	  "VALUES (" + padraoED.getOid_tipo_padrao() +", '" +
	  padraoED.getCd_tipo() + "', '" +
	  padraoED.getNm_descricao_tipo() + "', '" +
	  padraoED.getDt_stamp_tipo() + "', '" +
	  padraoED.getUsuario_stamp_tipo() + "')";

    executasql.executarUpdate(sql);


    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("incluiTipo");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return padraoED;
  }

  public Padrao_EDIED getByCd(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;

      Padrao_EDIED edi = new Padrao_EDIED();

      try{
	sql = " select * from tipos_padroes_edi ";

	if (String.valueOf(padrao_ed.getCd_tipo()) != null &&
	    !String.valueOf(padrao_ed.getCd_tipo()).equals("")){
	  sql += " where cd_tipo = '" + padrao_ed.getCd_tipo() + "'";

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	if (res.next()){
	  edi.setOid_tipo_padrao(res.getLong("OID_TIPO_PADRAO"));
	  edi.setCd_tipo(res.getString("CD_TIPO"));
	  edi.setNm_descricao_tipo(res.getString("NM_DESCRICAO"));
	  edi.setDt_stamp_tipo(res.getString("DT_STAMP"));
	  edi.setUsuario_stamp_tipo(res.getString("USUARIO_STAMP"));
	  }else{
	  }
	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByCd");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return edi;
  }

  public ArrayList getByNmDescricao(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;
      ArrayList Padrao_Lista = new ArrayList();

      Padrao_EDIED edi = new Padrao_EDIED();

      try{
	sql = " select * from tipos_padroes_edi";

	if (padrao_ed.getNm_descricao_tipo() != null &&
	!padrao_ed.getNm_descricao_tipo().equals(""))
	{
	  sql += " where nm_descricao LIKE '" + padrao_ed.getNm_descricao_tipo() + "%'";
	}

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	while (res.next()){
	edi = new Padrao_EDIED();
	  edi.setOid_tipo_padrao(res.getLong("OID_TIPO_PADRAO"));
	  edi.setCd_tipo(res.getString("CD_TIPO"));
	  edi.setNm_descricao_tipo(res.getString("NM_DESCRICAO"));
	  Padrao_Lista.add(edi);
	  }

      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByNmDescricao");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return Padrao_Lista;
  }

  public ArrayList getByNmDescricao2(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;
      ArrayList Padrao_Lista = new ArrayList();

      Padrao_EDIED edi = new Padrao_EDIED();

      try{
	sql = " select * from padroes_edi";

	if (padrao_ed.getNm_descricao_padrao() != null &&
	!padrao_ed.getNm_descricao_padrao().equals(""))
	{
	  sql += " where nm_descricao LIKE '" + padrao_ed.getNm_descricao_padrao() + "%'";
	}

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	while (res.next()){
	edi = new Padrao_EDIED();
	  edi.setOid_padrao(res.getLong("OID_PADRAO"));
	  edi.setCd_padrao(res.getString("CD_PADRAO"));
	  edi.setNm_descricao_padrao(res.getString("NM_DESCRICAO"));
	  Padrao_Lista.add(edi);
	  }

      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByNmDescricao2");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return Padrao_Lista;
  }

  public Padrao_EDIED getByCdPadrao(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;

      Padrao_EDIED edi = new Padrao_EDIED();

      try{
	sql = " select * from padroes_edi ";

	if (String.valueOf(padrao_ed.getCd_padrao()) != null &&
	    !String.valueOf(padrao_ed.getCd_padrao()).equals("")){
	  sql += " where cd_padrao = '" + padrao_ed.getCd_padrao() + "'";

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	if (res.next()){
	  edi.setOid_padrao(res.getLong("OID_PADRAO"));
	  edi.setCd_padrao(res.getString("CD_PADRAO"));
	  edi.setNm_descricao_padrao(res.getString("NM_DESCRICAO"));
	  edi.setDt_stamp_padrao(res.getString("DT_STAMP"));
	  edi.setUsuario_stamp(res.getString("USUARIO_STAMP"));
	  }else{
	  }
	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByCdPadrao");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return edi;
  }

  /*public Padrao_EDIED getByCdPadrao2(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;

      Padrao_EDIED edi = new Padrao_EDIED();

      try{
	sql = " select * from padroes_edi ";

	if (String.valueOf(padrao_ed.getCd_padrao()) != null &&
	    !String.valueOf(padrao_ed.getCd_padrao()).equals("")){
	  sql += " where cd_padrao = '" + padrao_ed.getCd_padrao() + "'";

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	if (res.next()){
	  edi.setOid_padrao(res.getLong("OID_PADRAO"));
	  edi.setCd_padrao(res.getString("CD_PADRAO"));
	  edi.setNm_descricao_padrao(res.getString("NM_DESCRICAO"));
	  edi.setDt_stamp_padrao(res.getString("DT_STAMP"));
	  edi.setUsuario_stamp(res.getString("USUARIO_STAMP"));
	  }else{
	  }
	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByCdPadrao");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return edi;
  }*/

    public ArrayList getByOidPadrao(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;
      ArrayList Lista = new ArrayList();

      Padrao_EDIED edi = new Padrao_EDIED();

      try{

      FormataDataBean DataFormatada = new FormataDataBean();

      if (padrao_ed.getOid_padrao() == 0){

      	sql = " select oid_padrao, cd_padrao, padroes_edi.nm_descricao as nm_descricao_padrao, tipos_padroes_edi.nm_descricao as nm_descricao_tipo, dm_tipo_padrao, dm_tipo_transacao " +
              " from padroes_edi, tipos_padroes_edi " +
              " where padroes_edi.oid_tipo_padrao = tipos_padroes_edi.oid_tipo_padrao ";

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	while (res.next()){
	edi = new Padrao_EDIED();
	  edi.setOid_padrao(res.getLong("OID_PADRAO"));
	  edi.setCd_padrao(res.getString("CD_PADRAO"));
	  edi.setNm_descricao_padrao(res.getString("NM_DESCRICAO_PADRAO"));
	  edi.setNm_descricao_tipo(res.getString("NM_DESCRICAO_TIPO"));

	  edi.setDm_tipo_padrao(res.getString("Dm_tipo_padrao"));

	  edi.setDm_tipo_transacao(res.getString("Dm_tipo_transacao"));
          if (edi.getDm_tipo_transacao().equals("E")) edi.setDm_tipo_transacao("Exportação");
          if (edi.getDm_tipo_transacao().equals("I")) edi.setDm_tipo_transacao("Importação");

	  Lista.add(edi);
	  }

      }else {

      	sql = " select * from padroes_edi ";

	sql += "where oid_padrao = " + padrao_ed.getOid_padrao();

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	while (res.next()){
	  edi.setOid_padrao(res.getLong("OID_PADRAO"));
	  edi.setCd_padrao(res.getString("CD_PADRAO"));
	  edi.setNm_descricao_padrao(res.getString("NM_DESCRICAO"));
	  edi.setDt_stamp_padrao(res.getString("DT_STAMP"));
	  edi.setUsuario_stamp(res.getString("USUARIO_STAMP"));

	  DataFormatada.setDT_FormataData(edi.getDt_stamp_padrao());
	  edi.setDt_stamp_padrao(DataFormatada.getDT_FormataData());
	  Lista.add(edi);
	  }
        }
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByOidPadrao");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return Lista;
  }

  public ArrayList getByOidTipo2(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;
      ArrayList Lista = new ArrayList();

      Padrao_EDIED edi = new Padrao_EDIED();

      try{

      FormataDataBean DataFormatada = new FormataDataBean();


      if (padrao_ed.getOid_tipo_padrao() == 0){

      	sql = "select * from tipos_padroes_edi";

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	while (res.next()){
	edi = new Padrao_EDIED();
	  edi.setOid_tipo_padrao(res.getLong("OID_TIPO_PADRAO"));
	  edi.setCd_tipo(res.getString("CD_TIPO"));
	  edi.setNm_descricao_tipo(res.getString("NM_DESCRICAO"));
	  edi.setDt_stamp_tipo(res.getString("DT_STAMP"));
	  edi.setUsuario_stamp_tipo(res.getString("USUARIO_STAMP"));


	  DataFormatada.setDT_FormataData(edi.getDt_stamp_tipo());
	  edi.setDt_stamp_tipo(DataFormatada.getDT_FormataData());
	  Lista.add(edi);
	  }

      }else {

      	sql = " select * from tipos_padroes_edi ";

	sql += "where oid_tipo_padrao = " + padrao_ed.getOid_tipo_padrao();

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	while (res.next()){
	  edi.setOid_tipo_padrao(res.getLong("OID_TIPO_PADRAO"));
	  edi.setCd_tipo(res.getString("CD_TIPO"));
	  edi.setNm_descricao_tipo(res.getString("NM_DESCRICAO"));
	  edi.setDt_stamp_tipo(res.getString("DT_STAMP"));
	  edi.setUsuario_stamp_tipo(res.getString("USUARIO_STAMP"));

	  DataFormatada.setDT_FormataData(edi.getDt_stamp_tipo());
	  edi.setDt_stamp_tipo(DataFormatada.getDT_FormataData());
	  Lista.add(edi);
	  }
        }
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByOidTipo");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return Lista;
  }

  public boolean deleta(Padrao_EDIED ed) throws Excecoes{

	  String sql = null;
	  boolean ok = true;

	  sql = "DELETE FROM padroes_edi WHERE oid_padrao=" + ed.getOid_padrao();

	  try{
	      executasql.executarUpdate(sql);
	  }
	  catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMetodo("deleta");
	      excecoes.setExc(exc);
	      ok = false;
	      throw excecoes;
	    }
      return ok;
  }

  public boolean deletaTipo(Padrao_EDIED ed) throws Excecoes{

	  String sql = null;
	  boolean ok = true;

	  sql = "DELETE FROM tipos_padroes_edi WHERE oid_tipo_padrao='" + ed.getOid_tipo_padrao() +"'";

	  try{
	      executasql.executarUpdate(sql);
	  }
	  catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMetodo("deletaTipo");
	      excecoes.setExc(exc);
	      ok = false;
	      throw excecoes;
	    }
      return ok;
  }

  public Padrao_EDIED getByRecord(Padrao_EDIED ed)throws Excecoes{

      String sql = null;

      Padrao_EDIED edi = new Padrao_EDIED();

      try{
	sql = " select * from padroes_edi where ";

	if (String.valueOf(ed.getOid_padrao()) != null &&
	    !String.valueOf(ed.getOid_padrao()).equals("")){
	  sql += "padroes_edi.Oid_padrao = '" + ed.getOid_padrao() + "'";

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	FormataDataBean DataFormatada = new FormataDataBean();

	while (res.next()){
	  edi.setOid_padrao(res.getLong("oid_padrao"));
	  edi.setCd_padrao(res.getString("CD_Padrao"));
	  edi.setDm_tipo_padrao(res.getString("Dm_tipo_padrao"));
	  edi.setDm_classe(res.getString("Dm_Classe"));
	  edi.setDm_tipo_transacao(res.getString("Dm_tipo_transacao"));
	  edi.setNm_descricao_padrao(res.getString("NM_Descricao"));
	  edi.setDt_stamp_padrao(res.getString("DT_Stamp"));
	  edi.setUsuario_stamp(res.getString("Usuario_Stamp"));
	  edi.setOid_tipo_padrao(res.getLong("Oid_Tipo_Padrao"));

	  DataFormatada.setDT_FormataData(edi.getDt_stamp_padrao());
	  edi.setDt_stamp_padrao(DataFormatada.getDT_FormataData());

	}
	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByRecord");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return edi;
  }

  public Padrao_EDIED getByOidTipo(Padrao_EDIED padrao_ed) throws Excecoes{

      String sql = null;
      FormataDataBean DataFormatada = new FormataDataBean();
      Padrao_EDIED edi = new Padrao_EDIED();

      try{
	sql = " select * from tipos_padroes_edi ";

	if (String.valueOf(padrao_ed.getOid_tipo_padrao()) != null &&
	    !String.valueOf(padrao_ed.getOid_tipo_padrao()).equals("")){
	  sql += " where oid_tipo_padrao = '" + padrao_ed.getOid_tipo_padrao() + "'";

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);

	if (res.next()){
	  edi.setOid_tipo_padrao(res.getLong("OID_TIPO_PADRAO"));
	  edi.setCd_tipo(res.getString("CD_TIPO"));
	  edi.setNm_descricao_tipo(res.getString("NM_DESCRICAO"));
	  edi.setUsuario_stamp_tipo(res.getString("USUARIO_STAMP"));
	  edi.setDt_stamp_tipo(res.getString("DT_STAMP"));

	  DataFormatada.setDT_FormataData(edi.getDt_stamp_tipo());
	  edi.setDt_stamp_tipo(DataFormatada.getDT_FormataData());
	  }
	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByOidTipo");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return edi;
  }

  public void updatePadrao(Padrao_EDIED ed)throws Excecoes{

      String sql = null;

      try{
	sql = "update padroes_edi set cd_padrao='"+ed.getCd_padrao()+"', "+
	      "nm_descricao='"+ed.getNm_descricao_padrao()+"', "+
	      "dm_classe'"+ed.getDm_classe()+"', "+
	      "dm_tipo_padrao'"+ed.getDm_tipo_padrao()+"', "+
	      "dm_tipo_transacao'"+ed.getDm_tipo_transacao()+"', "+
	      "nm_descricao='"+ed.getNm_descricao_padrao()+"', "+
	      "dt_stamp='"+ed.getDt_stamp_padrao()+"', "+
	      "usuario_stamp='"+ed.getUsuario_stamp()+"', "+
	      "oid_tipo_Padrao="+ed.getOid_tipo_padrao()+" "+
	      "where oid_padrao="+ed.getOid_padrao();

	if (String.valueOf(ed.getOid_padrao()) != null &&
	    !String.valueOf(ed.getOid_padrao()).equals("")){

	executasql.executarUpdate(sql);

	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao alterar");
	excecoes.setMetodo("update");
	excecoes.setExc(exc);
	throw excecoes;
      }
  }

  public void updateTipo(Padrao_EDIED ed)throws Excecoes{

      String sql = null;

      try{
	sql = "update tipos_padroes_edi set cd_tipo='"+ed.getCd_tipo()+"', "+
	      "nm_descricao='"+ed.getNm_descricao_tipo()+"', "+
	      "dt_stamp='"+ed.getDt_stamp_tipo()+"', "+
	      "usuario_stamp='"+ed.getUsuario_stamp_tipo()+"' "+
	      "where oid_tipo_padrao="+ed.getOid_tipo_padrao();

	if (String.valueOf(ed.getOid_tipo_padrao()) != null &&
	    !String.valueOf(ed.getOid_tipo_padrao()).equals("")){

	executasql.executarUpdate(sql);

	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao alterar");
	excecoes.setMetodo("update");
	excecoes.setExc(exc);
	throw excecoes;
      }
  }

}

