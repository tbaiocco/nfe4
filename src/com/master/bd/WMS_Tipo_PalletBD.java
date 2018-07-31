package com.master.bd;

/**
 * Título: WMS_Tipo_PalletBD
 * Descrição: Tipos de Pallet - BD
 * Data da criação: 02/2004
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Tipo_PalletED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class WMS_Tipo_PalletBD {

  private ExecutaSQL executasql;

  public WMS_Tipo_PalletBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_Tipo_PalletED inclui(WMS_Tipo_PalletED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    WMS_Tipo_PalletED WMS_Tipo_PalletED = new WMS_Tipo_PalletED();

    try{


      ResultSet rs = executasql.executarConsulta("select max(Oid_Tipo_Pallet) as result from tipos_pallet");

      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into tipos_pallet (";
      sql+= "Oid_tipo_pallet,Nm_Descricao,Nm_Material,Nr_Largura,Nr_Profundidade) values (";
      sql+=  ++valOid + ",";
      sql+= "'" + ed.getNm_Descricao() + "',";
      sql+= "'" + ed.getNm_Material() + "',";
      sql+= ed.getNr_Largura() + ",";
      sql+= ed.getNr_Profundidade() + ")";

      // System.out.println(sql);

      int i = executasql.executarUpdate(sql);
      WMS_Tipo_PalletED.setOid_Tipo_Pallet(new Integer(Long.toString(valOid)).intValue());
      WMS_Tipo_PalletED.setNm_Descricao(ed.getNm_Descricao());
      WMS_Tipo_PalletED.setNm_Material(ed.getNm_Material());
      WMS_Tipo_PalletED.setNr_Largura(ed.getNr_Largura());
      WMS_Tipo_PalletED.setNr_Profundidade(ed.getNr_Profundidade());
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Tipo de Pallet já registrado!");
      excecoes.setMetodo("inclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return WMS_Tipo_PalletED;

  }

  public void altera(WMS_Tipo_PalletED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update tipos_pallet set ";
      sql += " Nm_Descricao = '" + ed.getNm_Descricao() + "', ";
      sql += " Nm_Material = '" + ed.getNm_Material() + "', ";
      sql += " Nr_Largura = " + ed.getNr_Largura() + ", ";
      sql += " Nr_Profundidade = " + ed.getNr_Profundidade();
      sql += " where oid_tipo_pallet = " + ed.getOid_Tipo_Pallet();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados do Tipo de Pallet");
      excecoes.setMetodo("altera()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(WMS_Tipo_PalletED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from tipos_pallet WHERE Oid_Tipo_Pallet = ";
      sql += "(" + ed.getOid_Tipo_Pallet() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Este Tipo de Pallet está sendo usado pelo sistema!");
      excecoes.setMetodo("deleta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public WMS_Tipo_PalletED getByRecord(WMS_Tipo_PalletED ed)throws Excecoes{

    String sql = null;

    WMS_Tipo_PalletED edVolta = new WMS_Tipo_PalletED();

    try{

      sql = " select * from tipos_pallet";

      if ( !String.valueOf(ed.getOid_Tipo_Pallet()).equals("0")){
        sql += " where Oid_tipo_pallet = " + ed.getOid_Tipo_Pallet();
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        edVolta = new WMS_Tipo_PalletED();

        edVolta.setOid_Tipo_Pallet(new Integer(res.getString("Oid_Tipo_Pallet")).intValue());
        edVolta.setNm_Descricao(res.getString("NM_Descricao"));
        edVolta.setNm_Material(res.getString("NM_Material"));
        edVolta.setNr_Largura(new Double(res.getString("NR_Largura")).doubleValue());
        edVolta.setNr_Profundidade(new Double(res.getString("NR_Profundidade")).doubleValue());
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public WMS_Tipo_PalletED getByOid( String oid_tipo_pallet )throws Excecoes{

    String sql = null;

    WMS_Tipo_PalletED edVolta = new WMS_Tipo_PalletED();

    try{

      sql = " select * from tipos_pallet";
      sql += " where Oid_Tipo_Pallet = " + oid_tipo_pallet;


      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        edVolta = new WMS_Tipo_PalletED();

        edVolta.setOid_Tipo_Pallet(new Integer(res.getString("Oid_tipo_pallet")).intValue());
        edVolta.setNm_Descricao(res.getString("NM_Descricao"));
        edVolta.setNm_Material(res.getString("NM_Material"));
        edVolta.setNr_Largura(new Double(res.getString("NR_Largura")).doubleValue());
        edVolta.setNr_Profundidade(new Double(res.getString("NR_Profundidade")).doubleValue());
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList getAll()throws Excecoes{

    String sql = null;

    WMS_Tipo_PalletED edVolta = new WMS_Tipo_PalletED();
    ArrayList array = new ArrayList();
    try{

      sql = " select * from tipos_pallet";

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        edVolta = new WMS_Tipo_PalletED();

        edVolta.setOid_Tipo_Pallet(new Integer(res.getString("Oid_tipo_pallet")).intValue());
        edVolta.setNm_Descricao(res.getString("NM_Descricao"));
        edVolta.setNm_Material(res.getString("NM_Material"));
        edVolta.setNr_Largura(new Double(res.getString("NR_Largura")).doubleValue());
        edVolta.setNr_Profundidade(new Double(res.getString("NR_Profundidade")).doubleValue());
        array.add( edVolta );
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getAll()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return array;
  }

  public void geraRelatorio(WMS_Tipo_PalletED ed)throws Excecoes{

//    String sql = null;
//
//    WMS_Tipo_PalletED edVolta = new WMS_Tipo_PalletED();
//
//    try{
//
//      sql = "select * from Embalagens where Oid_Embalagem > 0";
//
//      if (ed.getCd_Embalagem() != null && !ed.getCd_Embalagem().equals("")){
//        sql += " and Cd_Embalagem = '" + ed.getCd_Embalagem() + "'";
//      }
//      if (ed.getCd_Remessa() != null && !ed.getCd_Remessa().equals("")){
//        sql += " and Cd_Remessa = '" + ed.getCd_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      WMS_Tipo_PalletRL WMS_Tipo_Pallet_rl = new WMS_Tipo_PalletRL();
//      WMS_Tipo_Pallet_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(WMS_Tipo_PalletED ed)");
//    }
//
  }

  public ArrayList lista(WMS_Tipo_PalletED edV, String orderby)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    String order_by = " ORDER BY oid_Tipo_Pallet";
    try{
      sql = "SELECT * FROM Tipos_Pallet WHERE 1=1 ";

     if(edV.getOid_Tipo_Pallet()!= 0)
         sql += "and oid_Tipo_Pallet="+edV.getOid_Tipo_Pallet();

     if(edV.getNm_Descricao()!= null)
        sql += " and nm_Descricao LIKE '"+edV.getNm_Descricao()+"%'";

     if(edV.getNr_Largura()!= 0)
        sql += " and Nr_Largura ="+edV.getNr_Largura();

     if(edV.getNr_Profundidade()!= 0)
        sql += " and Nr_Profundidade ="+edV.getNr_Profundidade();

     if(edV.getNm_Material()!= null)
        sql += " and Nm_Material LIKE '"+edV.getNm_Material()+"%'";


      if( !order_by.equals( orderby ) )
        sql += orderby;
      else
        sql += order_by;

      ResultSet res = null, res2 = null;

      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        WMS_Tipo_PalletED ed = new WMS_Tipo_PalletED();

        ed.setOid_Tipo_Pallet( res.getInt( "oid_tipo_pallet" ) );
        ed.setNm_Descricao( res.getString( "Nm_Descricao" ) );
        ed.setNm_Material( res.getString( "Nm_Material" ) );
        ed.setNr_Largura( res.getDouble( "Nr_Largura" ) );
        ed.setNr_Profundidade( res.getDouble( "Nr_Profundidade" ) );

         list.add(ed);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Tipos de Pallet");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

}