package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.SugestaoED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class SugestaoBD {

  private ExecutaSQL executasql;

  public SugestaoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public SugestaoED inclui(SugestaoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    SugestaoED manED = new SugestaoED();
    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_sugestao_contabil) as result from SUGESTOES_CONTABEIS");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");
      valOid = valOid+1;

      ed.setOid_Sugestao(valOid);

      sql =  " insert into SUGESTOES_CONTABEIS (oid_sugestao_contabil, nm_sugestao_contabil) values ";
      sql += "(" + ed.getOid_Sugestao() + ", UPPER('" + ed.getNM_Sugestao().toUpperCase()+"'))";

      int i = executasql.executarUpdate(sql);
      manED.setOid_Sugestao(ed.getOid_Sugestao());

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Sugestao");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return manED;

  }

  public void altera(SugestaoED ed) throws Excecoes{

    String sql = null;

    try{
      sql = " update SUGESTOES_CONTABEIS set "+
      "nm_sugestao_contabil = UPPER('"+ed.getNM_Sugestao()+"') " +
      "where oid_sugestao_contabil = " + ed.getOid_Sugestao();
//////// System.out.println(sql);
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Sugestao");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(SugestaoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from SUGESTOES_CONTABEIS WHERE oid_sugestao_contabil = "+ ed.getOid_Sugestao();
//////// System.out.println(sql);
      int i = executasql.executarUpdate(sql);

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Sugestao");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


    public void deletaVinculo(SugestaoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from EVENTOS_CONTABEIS WHERE oid_sugestao_contabil = "+ ed.getOid_Sugestao();
      //////// System.out.println(sql);
      int u = executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Eventos");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(SugestaoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT oid_sugestao_contabil, UPPER(nm_sugestao_contabil) as nm_sugestao_contabil FROM SUGESTOES_CONTABEIS order by oid_sugestao_contabil";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        SugestaoED edVolta = new SugestaoED();
        edVolta.setOid_Sugestao(res.getLong("oid_sugestao_contabil"));
        edVolta.setNM_Sugestao(res.getString("nm_sugestao_contabil"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Sugestao");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public SugestaoED getByRecord(SugestaoED ed)throws Excecoes{

    String sql = null;
    SugestaoED edVolta = new SugestaoED();
    try{
      sql = "SELECT oid_sugestao_contabil, UPPER(nm_sugestao_contabil) as nm_sugestao_contabil FROM SUGESTOES_CONTABEIS WHERE 1=1 ";
      if((ed.getOid_Sugestao()!=0)){
        sql += "AND oid_sugestao_contabil = "+ ed.getOid_Sugestao();
      }
      //////// System.out.println(sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        edVolta.setOid_Sugestao(res.getLong("oid_sugestao_contabil"));
        edVolta.setNM_Sugestao(res.getString("nm_sugestao_contabil"));
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Sugestao");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edVolta;
  }

  public ArrayList getByNM_Sugestao(SugestaoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
    //////// System.out.println("valor da sugestao");
      sql = "SELECT oid_sugestao_contabil, UPPER(nm_sugestao_contabil) as nm_sugestao_contabil FROM SUGESTOES_CONTABEIS WHERE 1=1 ";
      if((ed.getNM_Sugestao()!=null)&&(!ed.getNM_Sugestao().equals(""))){
       sql += "AND UPPER(nm_sugestao_contabil) Like UPPER('"+ed.getNM_Sugestao()+"%')";
      }

       sql += " order by oid_sugestao_contabil";
      ResultSet res = null;
      //////// System.out.println(sql);
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        SugestaoED edVolta = new SugestaoED();
        edVolta.setOid_Sugestao(res.getLong("oid_sugestao_contabil"));
        edVolta.setNM_Sugestao(res.getString("nm_sugestao_contabil"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Sugestao");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

 public SugestaoED getByModelo(String oid_Modelo)throws Excecoes{

    String sql = null;
    SugestaoED edVolta = new SugestaoED();
    try{
      sql = "SELECT sugestoes.oid_sugestao_contabil AS oid_sugestao_contabil, UPPER(sugestoes.nm_sugestao_contabil) AS nm_sugestao_contabil "+
            "FROM sugestoes_modelos modelos,  sugestoes_contabeis sugestoes "+
            "WHERE sugestoes.oid_sugestao_contabil = modelos.oid_sugestao_contabil "+
            "AND modelos.oid_modelo_nota_fiscal = "+ oid_Modelo;

//////// System.out.println(sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        edVolta.setOid_Sugestao(res.getLong("oid_sugestao_contabil"));
        edVolta.setNM_Sugestao(res.getString("nm_sugestao_contabil"));
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Sugestao");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edVolta;
  }


}
