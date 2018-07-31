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

import com.master.ed.AgenciaED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class AgenciaBD {

  private ExecutaSQL executasql;

  public AgenciaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

/**************************************
 *
 *******************************************************/
  public AgenciaED inclui(AgenciaED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;

    AgenciaED agenciaED  = new AgenciaED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Agencia) as result from Agencias");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");
      valOid = valOid+1;

      sql = " insert into Agencias (OID_AGENCIA, CD_AGENCIA, OID_CIDADE, OID_BANCO, "+
        "DT_STAMP, USUARIO_STAMP, DM_STAMP) values ("+valOid+",'"+ed.getCd_Agencia()+"',"+
        ed.getOid_Cidade()+","+ed.getOid_Banco()+",'"+ed.getDt_stamp()+"','"+ed.getUsuario_Stamp()+"','"+
        ed.getDm_Stamp()+"')";

//      // System.err.println(sql);
      int i = executasql.executarUpdate(sql);
      agenciaED.setOid_Agencia(new Integer(Long.toString(valOid)));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro de de inclusão no objeto Agencia");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return agenciaED;

  }

/********************************************************
 *
 *******************************************************/
  public void altera(AgenciaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Agencias set ";
      sql += " oid_cidade = " + ed.getOid_Cidade() + ", ";
      sql += " oid_banco = " + ed.getOid_Banco() + ", ";
      sql += " dt_stamp = '" + ed.getDt_stamp() + "', ";
      sql += " usuario_stamp = '" + ed.getUsuario_Stamp() + "', ";
      sql += " dm_stamp = '" + ed.getDm_Stamp() + "' ";
      sql += " where oid_Agencia = " + ed.getOid_Agencia();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro ao alterar dados de agência");
      excecoes.setClasse(this.getClass().getName());
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

/********************************************************
 *
 *******************************************************/
  public void deleta(AgenciaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Agencias WHERE oid_Agencia = "+ed.getOid_Agencia();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro ao excluir agência");
      excecoes.setClasse(this.getClass().getName());
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(AgenciaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = "select * from agencias, bancos, cidades, pessoas where ";
      sql += "agencias.oid_cidade = cidades.oid_cidade and ";
      sql += " agencias.oid_banco = bancos.oid_banco and";
      sql += " bancos.oid_pessoa = pessoas.oid_pessoa ";

      if (ed.getOid_Banco() != null && !ed.getOid_Banco().equals("")){
        sql += " and agencias.oid_banco = " + ed.getOid_Banco() + "";
      }
      if (ed.getCd_Agencia() != null && !ed.getCd_Agencia().equals("")){
        sql += " and agencias.cd_agencia = '" + ed.getCd_Agencia() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

//      // System.err.println(sql);

      //popula
      while (res.next()){
        AgenciaED edVolta = new AgenciaED();
        edVolta.setCd_Agencia(res.getString("cd_agencia"));
        edVolta.setOid_Agencia(new Integer(res.getInt("oid_agencia")));
        edVolta.setOid_Banco(new Integer(res.getInt("oid_banco")));
        edVolta.setOid_Cidade(new Integer(res.getInt("oid_cidade")));
        edVolta.setNome_Pessoa(res.getString("nm_fantasia"));
        edVolta.setNm_Cidade(res.getString("nm_cidade"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
    }

    return list;

  }

/********************************************************
 *
 *******************************************************/
  public AgenciaED getByRecord(AgenciaED ed)throws Excecoes{

    String sql = null;

    AgenciaED edVolta = new AgenciaED();

    try{

      sql = " select * from Agencias where oid_Agencia > 0";

      if (String.valueOf(ed.getOid_Agencia()) != null &&
          !String.valueOf(ed.getOid_Agencia()).equals("0")){
        sql += " and OID_Agencia = " + ed.getOid_Agencia();
      }
//      // System.err.println(sql);
//      if (ed.getOID_Pessoa() != null && !ed.getOID_Pessoa().equals("")){
//        sql += " and OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
//      }
//      if (ed.getCD_Banco() != null && !ed.getCD_Banco().equals("")){
//        sql += " and CD_Banco = '" + ed.getCD_Banco() + "'";
//      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new AgenciaED();
        edVolta.setCd_Agencia(res.getString("cd_agencia"));
        edVolta.setOid_Agencia(new Integer(res.getInt("oid_agencia")));
        edVolta.setOid_Banco(new Integer(res.getInt("oid_banco")));
        edVolta.setOid_Cidade(new Integer(res.getInt("oid_cidade")));
      }
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar agência");
      excecoes.setMetodo("getByRecord");
      excecoes.setExc(exc);
      throw excecoes;

    }

    return edVolta;
  }

  public void geraRelatorio(AgenciaED ed)throws Excecoes{

//    String sql = null;
//
//    AgenciaED edVolta = new AgenciaED();
//
//    try{
//
//      sql = "select * from Agencias where oid_Agencia > 0";
//
//      if (ed.getCD_Agencia() != null && !ed.getCD_Agencia().equals("")){
//        sql += " and CD_Agencia = '" + ed.getCD_Agencia() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      AgenciaRL Agencia_rl = new AgenciaRL();
//      Agencia_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(AgenciaED ed)");
//    }
//
  }

}