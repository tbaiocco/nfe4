package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.rl.*;
import com.master.ed.*;
import java.util.*;

import java.sql.*;


public class Unidade_NegocioBD {

  private ExecutaSQL executasql;

  public Unidade_NegocioBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

/********************************************************
 *
 *******************************************************/
  public Unidade_NegocioED inclui(Unidade_NegocioED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    Unidade_NegocioED Unidade_NegocioED = new Unidade_NegocioED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Unidade_Negocio) as result from Unidades_Negocios");

      while (rs.next()) valOid = rs.getInt("result") + 1;

      sql = " insert into Unidades_Negocios (OID_Unidade_Negocio, NM_Unidade_Negocio, DT_STAMP, USUARIO_STAMP, DM_STAMP) values ";
      sql += "(" + valOid + ",'" + ed.getNm_Unidade_Negocio() + "','" + ed.getDt_stamp() + "'," ;
      sql += "'" + ed.getUsuario_Stamp() + "','" + ed.getDm_Stamp() + "')";

      int i = executasql.executarUpdate(sql);
      String xx = String.valueOf(valOid);

      Unidade_NegocioED.setOid_Unidade_Negocio(new Integer(xx));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Unidade_Negocio");
      excecoes.setMetodo("inclui(Unidade_Negocio_ED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return Unidade_NegocioED;

  }

/********************************************************
 *
 *******************************************************/
  public void altera(Unidade_NegocioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Unidades_Negocios set ";
      sql += " NM_Unidade_Negocio = '" + ed.getNm_Unidade_Negocio() + "', ";
      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
      sql += " where oid_Unidade_Negocio = " + ed.getOid_Unidade_Negocio();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Unidade_Negocio");
      excecoes.setMetodo("altera(Unidade_NegocioED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Unidade_NegocioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Unidades_Negocios WHERE oid_Unidade_Negocio = ";
      sql += ed.getOid_Unidade_Negocio();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Unidade_Negocio");
      excecoes.setMetodo("deleta(Unidade_NegocioED");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Unidade_NegocioED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = "select * from Unidades_Negocios where oid_Unidade_Negocio >= 0";

      if (ed.getOid_Unidade_Negocio() != null && !ed.getOid_Unidade_Negocio().equals("")){
        sql += " and oid_Unidade_Negocio = '" + ed.getOid_Unidade_Negocio() + "'";
      }
      if (ed.getNm_Unidade_Negocio() != null && !ed.getNm_Unidade_Negocio().equals("")){
        sql += " and nm_Unidade_Negocio LIKE '" + ed.getNm_Unidade_Negocio() + "%'";
      }

      sql += " order by Unidades_Negocios.OID_Unidade_Negocio";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Unidade_NegocioED edVolta = new Unidade_NegocioED();
        edVolta.setOid_Unidade_Negocio(new Integer(res.getString("oid_Unidade_Negocio")));
        edVolta.setNm_Unidade_Negocio(res.getString("nm_Unidade_Negocio"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro no método listar");
      excecoes.setMetodo("lista(Unidade_Negocio_ED");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }


/********************************************************
 *
 *******************************************************/
  public Unidade_NegocioED getByRecord(Unidade_NegocioED ed)throws Excecoes{

    String sql = null;

    Unidade_NegocioED edVolta = new Unidade_NegocioED();

    try{


      sql = " select * from Unidades_Negocios where 1=1 ";

      if (String.valueOf(ed.getOid_Unidade_Negocio()) != null &&
          !String.valueOf(ed.getOid_Unidade_Negocio()).equals("")){
        sql += " and OID_Unidade_Negocio = " + ed.getOid_Unidade_Negocio();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Unidade_NegocioED();
        edVolta.setOid_Unidade_Negocio(new Integer(res.getInt("oid_Unidade_Negocio")));
        edVolta.setNm_Unidade_Negocio(res.getString("nm_Unidade_Negocio"));
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord");
      excecoes.setExc(exc);
      throw excecoes;

    }

    return edVolta;
  }



  public void geraRelatorio(Unidade_NegocioED ed)throws Excecoes{

//    String sql = null;
//
//    Unidade_NegocioED edVolta = new Unidade_NegocioED();
//
//    try{
//
//      sql = "select * from Unidade_Negocios where oid_Unidade_Negocio > 0";
//
//      if (ed.getCD_Unidade_Negocio() != null && !ed.getCD_Unidade_Negocio().equals("")){
//        sql += " and CD_Unidade_Negocio = '" + ed.getCD_Unidade_Negocio() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Unidade_NegocioRL Unidade_Negocio_rl = new Unidade_NegocioRL();
//      Unidade_Negocio_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Unidade_NegocioED ed)");
//    }
//
  }

  public Unidade_NegocioED getByRecordUnidade(Unidade_NegocioED ed)throws Excecoes{

    String sql = null;

    Unidade_NegocioED edVolta = new Unidade_NegocioED();

    try{


      sql = " select * from Unidades_Negocios, Centros_Custeios "+
        " where Unidades_Negocios.OID_Unidade_Negocio = Centros_Custeios.OID_Unidade_Negocio" +
        " and Centros_Custeios.oid_Unidade = " + ed.getOID_Unidade();

      if (String.valueOf(ed.getOid_Unidade_Negocio()) != null &&
          !String.valueOf(ed.getOid_Unidade_Negocio()).equals("")){
        sql += " and Unidades_Negocios.OID_Unidade_Negocio = " + ed.getOid_Unidade_Negocio();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Unidade_NegocioED();
        edVolta.setOid_Unidade_Negocio(new Integer(res.getInt("oid_Unidade_Negocio")));
        edVolta.setNm_Unidade_Negocio(res.getString("nm_Unidade_Negocio"));
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord");
      excecoes.setExc(exc);
      throw excecoes;

    }

    return edVolta;
  }

  public ArrayList lista_Unidade(Unidade_NegocioED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = " select * from Unidades_Negocios, Centros_Custeios "+
        " where Unidades_Negocios.OID_Unidade_Negocio = Centros_Custeios.OID_Unidade_Negocio" +
        " and Centros_Custeios.oid_Unidade = " + ed.getOID_Unidade();

      if (ed.getOid_Unidade_Negocio() != null && !ed.getOid_Unidade_Negocio().equals("")){
        sql += " and Unidades_Negocios.oid_Unidade_Negocio = '" + ed.getOid_Unidade_Negocio() + "'";
      }
      if (ed.getNm_Unidade_Negocio() != null && !ed.getNm_Unidade_Negocio().equals("")){
        sql += " and Unidades_Negocios.nm_Unidade_Negocio LIKE '" + ed.getNm_Unidade_Negocio() + "%'";
      }
      sql += " order by Unidades_Negocios.OID_Unidade_Negocio";
// System.out.println(sql);


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Unidade_NegocioED edVolta = new Unidade_NegocioED();
        edVolta.setOid_Unidade_Negocio(new Integer(res.getString("oid_Unidade_Negocio")));
        edVolta.setNm_Unidade_Negocio(res.getString("nm_Unidade_Negocio"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
    	exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro no médo listar");
      excecoes.setMetodo("lista(Unidade_Negocio_ED");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

}
