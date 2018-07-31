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

import com.master.ed.Grupo_PessoaED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class Grupo_PessoaBD {
  //ANTARA_MEGA
  //ANTARA_TESTE
  //NALTHUS

  String Base="ANTARA_TESTE";

  private ExecutaSQL executasql;

  public Grupo_PessoaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Grupo_PessoaED inclui(Grupo_PessoaED ed) throws Excecoes{

    String sql = null;
    Grupo_PessoaED Grupo_PessoaED = new Grupo_PessoaED();

    try{



      sql = " insert into Grupos_Pessoas (Cd_Grupo_Pessoa, NM_Grupo_Pessoa) values ";
      sql += "(" + "'" + ed.getCd_Grupo_Pessoa() + "','" + ed.getNm_Grupo_Pessoa() + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
      Grupo_PessoaED.setCd_Grupo_Pessoa(ed.getCd_Grupo_Pessoa());
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Grupo_Pessoa");
      excecoes.setMetodo("inclui(Grupo_Pessoa_ED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return Grupo_PessoaED;

  }

  public void altera(Grupo_PessoaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Grupos_Pessoas set ";
      sql += " NM_Grupo_Pessoa = '" + ed.getNm_Grupo_Pessoa() + "' ";
      sql += " where Cd_Grupo_Pessoa = '" + ed.getCd_Grupo_Pessoa() + "'";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Grupo_Pessoa");
      excecoes.setMetodo("altera(Grupo_PessoaED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Grupo_PessoaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Grupos_Pessoas WHERE Cd_Grupo_Pessoa = '";
      sql += ed.getCd_Grupo_Pessoa() + "'";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Grupo_Pessoa");
      excecoes.setMetodo("deleta(Grupo_PessoaED");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Grupo_PessoaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      if (Base.equals("ANTARA_MEGA")) {
         sql = "select * from Antara_Mega.Grupos_Pessoas  where DM_Analitico_Sintetico='A' and Cd_Grupo_Pessoa is not null";
      }
      if (Base.equals("ANTARA_TESTE")) {
         sql = "select * from Antara_teste.Grupos_Pessoas  where DM_Analitico_Sintetico='A' and Cd_Grupo_Pessoa is not null";
      }
      if (Base.equals("NALTHUS")) {
         sql = "select * from Grupos_Pessoas  where DM_Analitico_Sintetico='A'";
      }

      //// System.out.println("SQL = " + sql);

      if (ed.getCd_Grupo_Pessoa() != null && !ed.getCd_Grupo_Pessoa().equals("")){
        sql += " and Cd_Grupo_Pessoa = '" + ed.getCd_Grupo_Pessoa() + "'";
      }
      if (ed.getNm_Grupo_Pessoa() != null && !ed.getNm_Grupo_Pessoa().equals("")){
        sql += " and nm_Grupo_Pessoa LIKE '" + ed.getNm_Grupo_Pessoa() + "%'";
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Grupo_PessoaED edVolta = new Grupo_PessoaED();
        edVolta.setCd_Grupo_Pessoa(res.getString("Cd_Grupo_Pessoa"));
        edVolta.setNm_Grupo_Pessoa(res.getString("nm_Grupo_Pessoa"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro no método listar");
      excecoes.setMetodo("lista(Grupo_Pessoa_ED");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }


  public Grupo_PessoaED getByRecord(Grupo_PessoaED ed)throws Excecoes{

    String sql = null;

    Grupo_PessoaED edVolta = new Grupo_PessoaED();

    try{

      if (Base.equals("ANTARA_MEGA")) {
        sql = " select * from Antara_Mega.Grupos_Pessoas where Cd_Grupo_Pessoa is not null";
      }
      if (Base.equals("ANTARA_TESTE")) {
        sql = " select * from Antara_Teste.Grupos_Pessoas where Cd_Grupo_Pessoa is not null";
      }
      if (Base.equals("NALTHUS")) {
        sql = " select * from Grupos_Pessoas where Cd_Grupo_Pessoa is not null";
      }

      if (String.valueOf(ed.getCd_Grupo_Pessoa()) != null &&
          !String.valueOf(ed.getCd_Grupo_Pessoa()).equals("")){
        sql += " and Cd_Grupo_Pessoa = '" + ed.getCd_Grupo_Pessoa() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Grupo_PessoaED();
        edVolta.setCd_Grupo_Pessoa(res.getString("Cd_Grupo_Pessoa"));
        edVolta.setNm_Grupo_Pessoa(res.getString("nm_Grupo_Pessoa"));
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



  public void geraRelatorio(Grupo_PessoaED ed)throws Excecoes{

//    String sql = null;
//
//    Grupo_PessoaED edVolta = new Grupo_PessoaED();
//
//    try{
//
//      sql = "select * from Grupo_Pessoas where Cd_Grupo_Pessoa > 0";
//
//      if (ed.getCD_Grupo_Pessoa() != null && !ed.getCD_Grupo_Pessoa().equals("")){
//        sql += " and CD_Grupo_Pessoa = '" + ed.getCD_Grupo_Pessoa() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Grupo_PessoaRL Grupo_Pessoa_rl = new Grupo_PessoaRL();
//      Grupo_Pessoa_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Grupo_PessoaED ed)");
//    }
//
  }

}