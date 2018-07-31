package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Grupo_Pessoa_CargaED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class Grupo_Pessoa_CargaBD {

  //ANTARA_MEGA
  //ANTARA_TESTE
  //NALTHUS
  String Base="ANTARA_TESTE";

  private ExecutaSQL executasql;

  public Grupo_Pessoa_CargaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Grupo_Pessoa_CargaED inclui(Grupo_Pessoa_CargaED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    Grupo_Pessoa_CargaED Grupo_Pessoa_CargaED = new Grupo_Pessoa_CargaED();
    Data data = new Data();

    try{

      sql = " INSERT INTO Grupos_Pessoas_Cargas (OID_Pessoa, CD_GRUPO_PESSOA, DM_TIPO_PESSOA) values ( '";
      sql+= ed.getOID_Pessoa() + "',";
      sql+= ed.getCD_Grupo_Pessoa() + ",'" ;
      sql+= ed.getDM_Tipo_Pessoa() + "')";

      ////// System.out.println("inclui = " + sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Grupo_Pessoa_Carga");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Grupo_Pessoa_CargaED;

  }

  public void altera(Grupo_Pessoa_CargaED ed) throws Excecoes{
    String sql = null;

    try{

      sql = " UPDATE Grupos_Pessoas_Cargas SET CD_GRUPO_PESSOA='" + ed.getCD_Grupo_Pessoa()  + "'";
      sql += " WHERE oid_Pessoa = '"   + ed.getOID_Pessoa()  + "'";
      sql += " AND CD_Grupo_Pessoa = '" + ed.getCD_Grupo_Pessoa_Anterior() + "' ";

// System.out.println("altera = " + sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Grupo_Pessoa_Carga");
      excecoes.setMetodo("deleta(Grupo_Pessoa_CargaED)");
      excecoes.setExc(exc);
      throw excecoes;
    }


  }

  public void deleta(Grupo_Pessoa_CargaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " delete from Grupos_Pessoas_Cargas WHERE ";
      sql += " ( oid_Pessoa = '";
      sql +=  ed.getOID_Pessoa()  +"'";
      sql += " AND CD_Grupo_Pessoa = '";
      sql +=  ed.getCD_Grupo_Pessoa();
      sql += "') ";

      ////// System.out.println("delete = " + sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Grupo_Pessoa_Carga");
      excecoes.setMetodo("deleta(Grupo_Pessoa_CargaED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Grupo_Pessoa_CargaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

       if (Base.equals("ANTARA_MEGA")) {
            sql = " select * from ANTARA_MEGA.GRUPOS_PESSOAS, Grupos_Pessoas_Cargas where "+
                  " ANTARA_MEGA.GRUPOS_PESSOAS.CD_GRUPO_PESSOA = Grupos_Pessoas_Cargas.CD_Grupo_Pessoa AND Grupos_Pessoas_Cargas.oid_pessoa = '";
                  sql +=  ed.getOID_Pessoa() + "'";
      }
       if (Base.equals("ANTARA_TESTE")) {
            sql = " select * from ANTARA_TESTE.GRUPOS_PESSOAS, Grupos_Pessoas_Cargas where "+
                  " ANTARA_TESTE.GRUPOS_PESSOAS.CD_GRUPO_PESSOA = Grupos_Pessoas_Cargas.CD_Grupo_Pessoa AND Grupos_Pessoas_Cargas.oid_pessoa = '";
                  sql +=  ed.getOID_Pessoa() + "'";
      }
       if (Base.equals("NALTHUS")) {
            sql = " select * from GRUPOS_PESSOAS, Grupos_Pessoas_Cargas where "+
                  " GRUPOS_PESSOAS.CD_GRUPO_PESSOA = Grupos_Pessoas_Cargas.CD_Grupo_Pessoa AND Grupos_Pessoas_Cargas.oid_pessoa = '";
                  sql +=  ed.getOID_Pessoa() + "'";
      }
      // System.err.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Grupo_Pessoa_CargaED edVolta = new Grupo_Pessoa_CargaED();
        edVolta.setOID_Pessoa(res.getString("OID_PESSOA"));
        edVolta.setCD_Grupo_Pessoa(res.getString("CD_GRUPO_PESSOA"));
        edVolta.setDM_Tipo_Pessoa(res.getString("DM_Tipo_Pessoa"));
        edVolta.setNm_Grupo_Pessoa(res.getString("nm_Grupo_Pessoa"));


        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Grupo_Pessoa_Cargas - SQL="+sql);
      excecoes.setMetodo("lista(Grupo_Pessoa_CargasED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Grupo_Pessoa_CargaED getByRecord(Grupo_Pessoa_CargaED ed)throws Excecoes{

    String sql = null;

    Grupo_Pessoa_CargaED edVolta = new Grupo_Pessoa_CargaED();

    try{

      sql = " select * from Grupos_Pessoas_Cargas where ";
      sql += " ( oid_Pessoa = '" ;
      sql +=  ed.getOID_Pessoa()  +"'";
      sql += " and CD_Grupo_Pessoa = '";
      sql +=  ed.getCD_Grupo_Pessoa();
      sql += "') ";


      ResultSet res = null;

      ////// System.out.println("get by = " + sql);

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Grupo_Pessoa_CargaED();
        edVolta.setOID_Pessoa(res.getString("OID_PESSOA"));
        edVolta.setCD_Grupo_Pessoa(res.getString("CD_GRUPO_PESSOA"));
        edVolta.setDM_Tipo_Pessoa(res.getString("DM_Tipo_Pessoa"));

      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(Grupo_Pessoa_CargaED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(Grupo_Pessoa_CargaED ed)throws Excecoes{

  }

}