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

import com.master.ed.RotaED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class RotaBD {
  Calcula_FreteBD Calcula_FreteBD = null;

  private ExecutaSQL executasql;

  public RotaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public RotaED inclui(RotaED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;
    String CD_CFO_Rota = null;
    boolean DM_Nota_Em_Rota = false;

    RotaED rotaED = new RotaED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(OID_Rota) as result from Rotas");

      while (rs.next()) valOid = rs.getInt("result");

      valOid = valOid + 1;

      ed.setNR_Trecho(String.valueOf(valOid));

      sql = " insert into Rotas ("+
      "OID_Rota, "+
      "OID_Cidade, "+
      "OID_Cidade_Destino, "+
      "NM_Rodovia, "+
      "NR_KM, "+
      "NR_MEDIA_HORARIA, "+
      "NR_HR "+
      ") values ";
      sql += "(" + ed.getNR_Trecho() + "," +
                ed.getOID_Cidade() + "," +
                ed.getOID_Cidade_Destino() + ",'" +
                ed.getNM_Rodovia() + "'," +
                ed.getNR_KM() + "," +
                ed.getNR_Media_Horaria() + ",'" +
                ed.getNR_HR() + "')";
// System.out.println(sql);
      int i = executasql.executarUpdate(sql);

      rotaED.setOID_Rota(ed.getOID_Rota());
    }

    catch(Exception exc){
exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Rota");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return rotaED;
  }

  public void altera(RotaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Rotas set NM_Rodovia = '" + ed.getNM_Rodovia() + "'," +
        " OID_Cidade = " + ed.getOID_Cidade() + "," +
        " OID_Cidade_Destino = " + ed.getOID_Cidade_Destino() + "," +
        " NR_KM = " + ed.getNR_KM() + "," +
        " NR_MEDIA_HORARIA = " + ed.getNR_Media_Horaria() + "," +
        " NR_HR = '" + ed.getNR_HR() + "'";
      sql += " where oid_Rota = " + ed.getOID_Rota();
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(RotaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Rotas WHERE oid_Rota = ";
      sql += "('" + ed.getOID_Rota() + "')";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(RotaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = " select "+
      "OID_Rota, " +
      "NM_Rodovia, "+
      "NR_KM, "+
      "NR_MEDIA_HORARIA, "+
      "NR_HR, "+
      "Cidade_Remetente.NM_Cidade as NM_Cidade_Origem, "+
      "Cidade_Destinatario.NM_Cidade as NM_Cidade_Destino, "+
      "Estado_Remetente.CD_Estado as CD_Estado_Origem, "+
      "Estado_Destinatario.CD_Estado as CD_Estado_Destino "+
      " from Rotas, "+
      " cidades Cidade_Remetente, "+
      " estados Estado_Remetente, "+
      " regioes_estados Regiao_Estado_Remetente, "+
      " cidades Cidade_Destinatario, "+
      " estados Estado_Destinatario, "+
      " regioes_estados Regiao_Estado_Destinatario "+
      " WHERE "+
      " Rotas.oid_cidade = Cidade_Remetente.oid_cidade and"+
      " Cidade_Remetente.oid_regiao_Estado = Regiao_Estado_Remetente.oid_regiao_estado and"+
      " Regiao_Estado_Remetente.oid_Estado = Estado_Remetente.oid_Estado and"+
      " Rotas.oid_cidade_destino = Cidade_Destinatario.oid_cidade and"+
      " Cidade_Destinatario.oid_regiao_Estado = Regiao_Estado_Destinatario.oid_regiao_estado and"+
      " Regiao_Estado_Destinatario.oid_Estado = Estado_Destinatario.oid_Estado ";

      if (String.valueOf(ed.getOID_Rota()) != null &&
          !String.valueOf(ed.getOID_Rota()).equals("") &&
          !String.valueOf(ed.getOID_Rota()).equals("null")){
        sql += " and Rotas.OID_Rota = " + ed.getOID_Rota();
      }

      if (String.valueOf(ed.getNM_Rodovia()) != null &&
          !String.valueOf(ed.getNM_Rodovia()).equals("") &&
          !String.valueOf(ed.getNM_Rodovia()).equals("null")){
        sql += " and Rotas.NM_Rodovia = '" + ed.getNM_Rodovia() + "'";
      }

      if (String.valueOf(ed.getOID_Cidade()) != null &&
          !String.valueOf(ed.getOID_Cidade()).equals("0")){
        sql += " and Rotas.OID_Cidade = " + ed.getOID_Cidade();
      }

      if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
          !String.valueOf(ed.getOID_Cidade_Destino()).equals("0")){
        sql += " and Rotas.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
      }

      sql += " Order by Rotas.oid_Rota ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        RotaED edVolta = new RotaED();

        edVolta.setOID_Rota(res.getString("OID_Rota"));
        edVolta.setNR_Trecho(res.getString("OID_Rota"));
        edVolta.setNM_Rodovia(res.getString("NM_Rodovia"));
        edVolta.setNR_KM(res.getDouble("NR_KM"));
        edVolta.setNR_Media_Horaria(res.getString("NR_MEDIA_HORARIA"));
        edVolta.setNR_HR(res.getString("NR_HR"));
        edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));
        edVolta.setNM_Estado_Origem(res.getString("CD_Estado_Origem"));
        edVolta.setNM_Estado_Destino(res.getString("CD_Estado_Destino"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public RotaED getByRecord(RotaED ed)throws Excecoes{

    String sql = null;

    RotaED edVolta = new RotaED();

    try{

      sql = " select * from Rotas where 1=1 ";

      if (String.valueOf(ed.getOID_Rota()) != null &&
          !String.valueOf(ed.getOID_Rota()).equals("") &&
          !String.valueOf(ed.getOID_Rota()).equals("null")){
        sql += " and OID_Rota = " + ed.getOID_Rota();
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){

        edVolta.setOID_Rota(res.getString("OID_Rota"));
        edVolta.setNR_Trecho(res.getString("OID_Rota"));
        edVolta.setNM_Rodovia(res.getString("NM_Rodovia"));
        edVolta.setNR_KM(res.getDouble("NR_KM"));
        edVolta.setNR_Media_Horaria(res.getString("NR_MEDIA_HORARIA"));
        edVolta.setNR_HR(res.getString("NR_HR"));
        edVolta.setOID_Cidade(res.getLong("OID_Cidade"));
        edVolta.setOID_Cidade_Destino(res.getLong("OID_Cidade_Destino"));

      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

//  public byte[] geraRelRotaEmbarque(RotaED ed)throws Excecoes{
//
//    String sql = null;
//    byte[] b = null;
//
//      sql = " select Rotas.DT_Emissao, Rotas.NR_Rota, Rotas.OID_Rota, Rotas.nr_volumes, Rotas.dm_tipo_pagamento, Rotas.nr_duplicata, Rotas.vl_total_frete, Rotas.Dt_Entrega, Rotas.Hr_Entrega, "+
//      " Rotas.nr_peso, Unidades.CD_Unidade, Cidades.NM_Cidade, "+
//      " Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente,"+
//      " Pessoa_Unidade.NM_Fantasia          as NM_Fantasia            ,"+
//      " Pessoa_Pagador.NM_Razao_Social      as NM_Razao_Social_Pagador,"+
//      " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario "+
//      " from Rotas, Unidades, Cidades, "+
//      " Pessoas Pessoa_Remetente, Pessoas Pessoa_Pagador, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario "+
//      " where Unidades.oid_Unidade = Rotas.oid_Unidade "+
//      " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
//      " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "+
//      " AND Rotas.DM_Impresso = 'S' "+
//      " AND Rotas.VL_Total_Frete > 0" +
//      " AND Rotas.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "+
//      " AND Rotas.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "+
//      " AND Rotas.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
//
//      if (String.valueOf(ed.getNR_Rota()) != null && !String.valueOf(ed.getNR_Rota()).equals("0")){
//        sql += " and Rotas.NR_Rota = " + ed.getNR_Rota();
//      }
//      if (String.valueOf(ed.getOID_Unidade()) != null && !String.valueOf(ed.getOID_Unidade()).equals("0")){
//        sql += " and Rotas.OID_Unidade = " + ed.getOID_Unidade();
//      }
//      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
//        sql += " and Rotas.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
//      }
//      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
//        sql += " and Rotas.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
//      }
//      if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null && !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("")){
//        sql += " and Rotas.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
//      }
//      if (String.valueOf(ed.getOID_Pessoa_Pagador()) != null &&
//         !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
//         !String.valueOf(ed.getOID_Pessoa_Pagador()).equals("null")){
//        sql += " and Rotas.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "'";
//      }
//      if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
//        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
//        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
//        sql += " and Rotas.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
//      }
//      if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
//      !String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
//      !String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
//        sql += " and Rotas.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
//      }
//
//      if (String.valueOf(ed.getDm_Situacao_Cobranca()) != null &&
//      !String.valueOf(ed.getDm_Situacao_Cobranca()).equals("null") &&
//      !String.valueOf(ed.getDm_Situacao_Cobranca()).equals("T") &&
//      !String.valueOf(ed.getDm_Situacao_Cobranca()).equals("")){
//        sql += " and Rotas.DM_Situacao = '" + ed.getDm_Situacao_Cobranca() + "'";
//      }
//
//
//      if (String.valueOf(ed.getDM_Tipo_Embarque()) != null &&
//      !String.valueOf(ed.getDM_Tipo_Embarque()).equals("null") &&
//      String.valueOf(ed.getDM_Tipo_Embarque()).equals("F")) {
//        sql += " and Rotas.NR_Peso <= 7500 ";
//      }
//
//      if (String.valueOf(ed.getDM_Tipo_Embarque()) != null &&
//      !String.valueOf(ed.getDM_Tipo_Embarque()).equals("null") &&
//      String.valueOf(ed.getDM_Tipo_Embarque()).equals("C")) {
//        sql += " and Rotas.NR_Peso > 7500 ";
//      }
//
//      sql += " order by unidades.cd_unidade, Rotas.dt_emissao, Rotas.nr_Rota ";
//
//
//    RotaED edVolta = new RotaED();
//
//    try{
//
//      //// //////// // System.out.println(sql);
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql.toString());
//
//      RotaRL conRL = new RotaRL();
//      b =  conRL.geraRelConhecEmbarque(res);
//    }
//
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(RotaED ed)");
//    }
//    return b;
//  }

}
