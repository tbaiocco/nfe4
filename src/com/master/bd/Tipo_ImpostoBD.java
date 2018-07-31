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

import com.master.ed.Tipo_ImpostoED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class Tipo_ImpostoBD {

  private ExecutaSQL executasql;

  public Tipo_ImpostoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Tipo_ImpostoED inclui(Tipo_ImpostoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    Tipo_ImpostoED Tipo_ImpostoED = new Tipo_ImpostoED();

    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_Tipo_Imposto) as result from Tipos_Impostos");

      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Tipos_Impostos ("+
      "OID_Tipo_Imposto,NM_Tipo_Imposto, CD_Tipo_Imposto, DM_Tipo_Imposto, DM_Origem, DM_Aplicacao, DM_Recolhimento) values ("+
      ""+ ++valOid + ",'" + ed.getNm_Tipo_Imposto() + "','" + ed.getCd_Tipo_Imposto() + "','" + ed.getDm_Tipo_Imposto() + "','" + ed.getDm_Origem() + "','" + ed.getDm_Aplicacao() + "','" + ed.getDm_Recolhimento() + "')";


      // System.err.println(sql);


      executasql.executarUpdate(sql);
      Tipo_ImpostoED.setOid_Tipo_Imposto(new Integer(Long.toString(valOid)));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Tipo_Imposto");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Tipo_ImpostoED;

  }

  public void altera(Tipo_ImpostoED ed) throws Excecoes{

    String sql = null;

    try{

    //      if (DT_Emissao_Calendario.after(DT_Hoje_Calendario)){
//        Excecoes exc = new Excecoes();
//        exc.setMensagem("Data emissão tem de ser menor ou igual a data de hoje.");
//        exc.setClasse(this.getClass().getName());
//        exc.setMetodo("Inclui(CompromissoRN)");
//        throw exc;
//      }


      sql = " update Tipos_Impostos set ";
      sql += " NM_Tipo_Imposto = '" + ed.getNm_Tipo_Imposto() + "', ";
      sql += " CD_Tipo_Imposto = '" + ed.getCd_Tipo_Imposto() + "', ";
      sql += " DM_Origem = '" + ed.getDm_Origem() + "', ";
      sql += " DM_Aplicacao = '" + ed.getDm_Aplicacao() + "', ";
      sql += " DM_Recolhimento = '" + ed.getDm_Recolhimento() + "', ";
      sql += " DM_Tipo_Imposto      = '" + ed.getDm_Tipo_Imposto()      + "' ";
      sql += " where oid_Tipo_Imposto = " + ed.getOid_Tipo_Imposto();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar. ");
      excecoes.setMetodo("altera(Tipo_ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Tipo_ImpostoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Tipos_Impostos WHERE oid_Tipo_Imposto = ";
      sql += "(" + ed.getOid_Tipo_Imposto() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Tipo_Imposto");
      excecoes.setMetodo("deleta(Tipo_ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Tipo_ImpostoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = "select * from Tipos_Impostos";


      //// System.err.println(sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Tipo_ImpostoED edVolta = new Tipo_ImpostoED();

        edVolta.setOid_Tipo_Imposto(new Integer(res.getString("oid_Tipo_Imposto")));
        edVolta.setCd_Tipo_Imposto(res.getString("cd_Tipo_Imposto"));
        edVolta.setNm_Tipo_Imposto(res.getString("nm_Tipo_Imposto"));
        edVolta.setDm_Tipo_Imposto(res.getString("dm_Tipo_Imposto"));
        edVolta.setDm_Origem(res.getString("dm_Origem"));
        edVolta.setDm_Aplicacao(res.getString("dm_Aplicacao"));
        edVolta.setDm_Recolhimento(res.getString("dm_Recolhimento"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Tipos_Impostos - SQL="+sql);
      excecoes.setMetodo("lista(Tipos_ImpostosED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Tipo_ImpostoED getByRecord(Tipo_ImpostoED ed)throws Excecoes{

    String sql = null;

    Tipo_ImpostoED edVolta = new Tipo_ImpostoED();

    try{

      sql = " select * from Tipos_Impostos where 1=1 ";

      if (ed.getOid_Tipo_Imposto() != null &&
          !String.valueOf(ed.getOid_Tipo_Imposto()).equals("0")){
        sql += " and OID_Tipo_Imposto = " + ed.getOid_Tipo_Imposto();
      }

      if (ed.getCd_Tipo_Imposto() != null && !ed.getCd_Tipo_Imposto().equals("0") && !ed.getCd_Tipo_Imposto().equals("")){
        sql += " and Tipos_Impostos.cd_Tipo_Imposto = '" + ed.getCd_Tipo_Imposto() + "'";
      }

      ResultSet res = null;

      // System.err.println(sql);

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Tipo_ImpostoED();
        // System.err.println(sql);
        edVolta.setOid_Tipo_Imposto(new Integer(res.getString("oid_Tipo_Imposto")));
        edVolta.setCd_Tipo_Imposto(res.getString("cd_Tipo_Imposto"));
        edVolta.setNm_Tipo_Imposto(res.getString("nm_Tipo_Imposto"));
        edVolta.setDm_Tipo_Imposto(res.getString("dm_Tipo_Imposto"));
        edVolta.setDm_Origem(res.getString("dm_Origem"));
        edVolta.setDm_Aplicacao(res.getString("dm_Aplicacao"));
        edVolta.setDm_Recolhimento(res.getString("dm_Recolhimento"));
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(Tipo_ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(Tipo_ImpostoED ed)throws Excecoes{

//    String sql = null;
//
//    Tipo_ImpostoED edVolta = new Tipo_ImpostoED();
//
//    try{
//
//      sql = "select * from Tipos_Impostos where oid_Tipo_Imposto > 0";
//
//      if (ed.getCD_Tipo_Imposto() != null && !ed.getCD_Tipo_Imposto().equals("")){
//        sql += " and CD_Tipo_Imposto = '" + ed.getCD_Tipo_Imposto() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Tipo_ImpostoRL Tipo_Imposto_rl = new Tipo_ImpostoRL();
//      Tipo_Imposto_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Tipo_ImpostoED ed)");
//    }
//
  }

}