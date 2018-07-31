package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Lote_NFED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Lote_NFBD {

  private ExecutaSQL executasql;

  public Lote_NFBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Lote_NFED inclui(Lote_NFED nf) throws Excecoes{

    String sql = null;
    long valOid = 0;
    ResultSet rs = null;
    ResultSet res = null;

    Lote_NFED ed = (Lote_NFED)nf;

    try{

    sql = "SELECT MAX(LOTES_NOTAS_FISCAIS.OID_LOTE_NOTA_FISCAL) as oid FROM LOTES_NOTAS_FISCAIS";

    rs = this.executasql.executarConsulta(sql);

//// System.out.println(sql);

    while(rs.next()){

    valOid = rs.getLong("oid") + 1;

    ed.setOid_Lote(valOid);
    ed.setNr_Lote(String.valueOf(valOid));

    }

    sql = "INSERT INTO lotes_notas_fiscais (oid_lote_NOTA_FISCAL, "+
	  "oid_unidade, oid_pessoa, nr_lote_NOTA_FISCAL, dt_cadastro, vl_lote, nr_volumes, vl_total_frete, usuario_stamp, dt_stamp) "+
	  "VALUES (" + valOid + ", " +
	  ed.getOid_Unidade() + ", '" +
	  ed.getOid_Pessoa() + "', '" +
	  ed.getNr_Lote() + "', '" +
	  ed.getDt_Cadastro() + "', '" +
	  ed.getVl_Lote() + "', '" +
	  ed.getNr_Volumes() + "', '" +
	  ed.getVl_Total_Frete() + "', '" +
	  ed.getUsuario_stamp() + "', '" +
	  ed.getDt_Stamp() + "')";

    res = this.executasql.executarConsulta(sql);
    //// System.out.println(sql + " lotes notas fiscais");

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;
  }

 public boolean deleta(Lote_NFED ed) throws Excecoes{

	  String sql = null;
	  boolean ok = true;

	  sql = "DELETE FROM lotes_notas_fiscais WHERE oid_lote_nota_fiscal=" + ed.getOid_Lote();
	      //// System.out.println(sql);

	  try{
	      int res = this.executasql.executarUpdate(sql);
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

  public Lote_NFED getByRecord(Lote_NFED nf)throws Excecoes{

      String sql = null;

      Lote_NFED lote = new Lote_NFED();

      try{
	sql = " select * from lotes_notas_fiscais where ";

	if (String.valueOf(nf.getOid_Lote()) != null &&
	    !String.valueOf(nf.getOid_Lote()).equals("")){
	  sql += "lotes_notas_fiscais.oid_lote_nota_fiscal = " + nf.getOid_Lote();

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);
	//// System.out.println(sql);

	FormataDataBean DataFormatada = new FormataDataBean();

	while (res.next()){
//// System.out.println("here");
	  lote.setOid_Lote(res.getLong("oid_lote_nota_fiscal"));
	  lote.setOid_Unidade(res.getLong("oid_unidade"));
	  lote.setOid_Pessoa(res.getString("oid_pessoa"));
	  lote.setNr_Lote(res.getString("nr_lote_nota_fiscal"));
	  lote.setDt_Cadastro(res.getString("dt_cadastro"));
	  lote.setVl_Lote(res.getDouble("vl_lote"));
	  lote.setNr_Volumes(res.getLong("nr_volumes"));
	  lote.setVl_Total_Frete(res.getDouble("vl_total_frete"));
	  lote.setUsuario_stamp(res.getString("usuario_stamp"));
	  lote.setDt_Stamp(res.getString("dt_stamp"));

	  DataFormatada.setDT_FormataData(lote.getDt_Cadastro());
	  lote.setDt_Cadastro(DataFormatada.getDT_FormataData());
//// System.out.println(lote.getDt_Cadastro());

//// System.out.println(lote.getNr_Lote());
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
    return lote;
  }

  public ArrayList getByOid(Lote_NFED nf) throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();

      Lote_NFED lote = new Lote_NFED();

      try{
	sql = " select * from lotes_notas_fiscais where ";

	if (String.valueOf(nf.getOid_Lote()) != null &&
	    !String.valueOf(nf.getOid_Lote()).equals("")){
	  sql += "lotes_notas_fiscais.oid_lote = " + nf.getOid_Lote();

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);
	//// System.out.println(sql);

	FormataDataBean DataFormatada = new FormataDataBean();

	while (res.next()){
	  lote.setOid_Lote(res.getLong("oid_lote"));
	  lote.setOid_Unidade(res.getLong("oid_unidade"));
	  lote.setOid_Pessoa(res.getString("oid_pessoa"));
	  lote.setNr_Lote(res.getString("nr_lote"));
	  lote.setDt_Cadastro(res.getString("dt_cadastro"));
	  lote.setVl_Lote(res.getDouble("vl_lote"));
	  lote.setNr_Volumes(res.getLong("nr_volumes"));
	  lote.setVl_Total_Frete(res.getDouble("vl_total_frete"));
	  lote.setUsuario_stamp(res.getString("usuario_stamp"));
	  lote.setDt_Stamp(res.getString("dt_stamp"));
	  list.add(lote);
	  }
	}
      }
      catch(Exception exc){
	Excecoes excecoes = new Excecoes();
	excecoes.setClasse(this.getClass().getName());
	excecoes.setMensagem("Erro ao selecionar");
	excecoes.setMetodo("getByOid");
	excecoes.setExc(exc);
	throw excecoes;
      }
    return list;
  }

  public Lote_NFED getByCD(Lote_NFED nf)throws Excecoes{

      String sql = null;

      Lote_NFED lote = new Lote_NFED();

      try{
	sql = " select * from lotes_notas_fiscais where ";

	if (String.valueOf(nf.getOid_Lote()) != null &&
	    !String.valueOf(nf.getOid_Lote()).equals("")){
	  sql += "lotes_notas_fiscais.nr_lote_nota_fiscal = " + nf.getNr_Lote();

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);
	//// System.out.println(sql);

	FormataDataBean DataFormatada = new FormataDataBean();

	while (res.next()){
//// System.out.println("here");
	  lote.setOid_Lote(res.getLong("oid_lote_nota_fiscal"));
	  lote.setOid_Unidade(res.getLong("oid_unidade"));
	  lote.setOid_Pessoa(res.getString("oid_pessoa"));
	  lote.setNr_Lote(res.getString("nr_lote_nota_fiscal"));
	  lote.setDt_Cadastro(res.getString("dt_cadastro"));
	  lote.setVl_Lote(res.getDouble("vl_lote"));
	  lote.setNr_Volumes(res.getLong("nr_volumes"));
	  lote.setVl_Total_Frete(res.getDouble("vl_total_frete"));
	  lote.setUsuario_stamp(res.getString("usuario_stamp"));
	  lote.setDt_Stamp(res.getString("dt_stamp"));

//// System.out.println(lote.getNr_Lote());
	  DataFormatada.setDT_FormataData(lote.getDt_Cadastro());
	  lote.setDt_Cadastro(DataFormatada.getDT_FormataData());
//// System.out.println(lote.getDt_Cadastro());

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
    return lote;
  }

  public void update(Lote_NFED ed)throws Excecoes{

      String sql = null;

      try{
	sql = "update lotes_notas_fiscais set oid_lote_nota_fiscal="+ed.getOid_Lote()+", "+
	      "oid_unidade="+ed.getOid_Unidade()+", "+
	      "oid_pessoa='"+ed.getOid_Pessoa()+"', "+
	      "nr_lote_nota_fiscal='"+ed.getNr_Lote()+"', "+
	      "dt_cadastro='"+ ed.getDt_Cadastro()+"', "+
	      "vl_lote=" + ed.getVl_Lote()+", "+
	      "nr_volumes=" + ed.getNr_Volumes()+", "+
	      "vl_total_frete=" + ed.getVl_Total_Frete()+ ", "+
	      "usuario_stamp='"+ed.getUsuario_stamp()+"', "+
	      "dt_stamp='"+ ed.getDt_Stamp()+ "' "+
	      "where oid_lote_nota_fiscal="+ed.getOid_Lote();

	if (String.valueOf(ed.getOid_Lote()) != null &&
	    !String.valueOf(ed.getOid_Lote()).equals("")){

	ResultSet res = null;
	res = this.executasql.executarConsulta(sql);
	//// System.out.println(sql);

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

  public ArrayList lista (Lote_NFED ed) throws Exception{

  String sql = null;
  ArrayList lista = new ArrayList();

  try{


      sql = "select * from lotes_notas_fiscais"+
            " where 1=1 ";

//// System.out.println(String.valueOf(ed.getOid_Lote()));
      if (String.valueOf(ed.getOid_Lote()) != null &&
          !String.valueOf(ed.getOid_Lote()).equals("") &&
	  !String.valueOf(ed.getOid_Lote()).equals("0") &&
          !String.valueOf(ed.getOid_Lote()).equals("null")){
        sql += " and OID_Lote_nota_fiscal = " + ed.getOid_Lote();
        }
//// System.out.println(ed.getNr_Lote());
      if (String.valueOf(ed.getNr_Lote()) != null &&
          !String.valueOf(ed.getNr_Lote()).equals("") &&
          !String.valueOf(ed.getNr_Lote()).equals("null")){
        sql += " and NR_Lote_nota_fiscal = '" + ed.getNr_Lote() + "'";
        }
//// System.out.println(ed.getUsuario_stamp());
      if (String.valueOf(ed.getUsuario_stamp()) != null &&
          !String.valueOf(ed.getUsuario_stamp()).equals("") &&
          !String.valueOf(ed.getUsuario_stamp()).equals("null")){
        sql += " and usuario_stamp = '" + ed.getUsuario_stamp() + "'";
        }
//// System.out.println(ed.getDt_Cadastro());
      if (String.valueOf(ed.getDt_Cadastro()) != null &&
          !String.valueOf(ed.getDt_Cadastro()).equals("") &&
	  !String.valueOf(ed.getDt_Cadastro()).equals(" ") &&
          !String.valueOf(ed.getDt_Cadastro()).equals("null")){
        sql += " and dt_cadastro = '" + ed.getDt_Cadastro() + "'";
        }

//// System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

//// System.out.println("bd 01");
      while(res.next()){
        Lote_NFED loteED = new Lote_NFED();
        loteED.setOid_Lote(res.getLong("oid_lote_nota_fiscal"));
	loteED.setNr_Lote(res.getString("nr_lote_nota_fiscal"));
	loteED.setUsuario_stamp(res.getString("usuario_stamp"));
	loteED.setDt_Cadastro(res.getString("dt_cadastro"));
	 DataFormatada.setDT_FormataData(loteED.getDt_Cadastro());
	 loteED.setDt_Cadastro(DataFormatada.getDT_FormataData());
	loteED.setVl_Lote(res.getDouble("vl_lote"));
//// System.out.println("bd 02" + loteED.getOid_Lote());
	lista.add(loteED);
        }
//// System.out.println("bd 03");
      }
       catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return lista;
  }

}

