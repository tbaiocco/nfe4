package com.master.bd;




import com.master.util.*;
import com.master.util.bd.*;
import com.master.rl.*;
import com.master.ed.*;
import java.util.*;

import java.sql.*;

public class Produto_CustoBD {

  private ExecutaSQL executasql;

  public Produto_CustoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Produto_CustoED inclui(Produto_CustoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    Produto_CustoED Produto_CustoED = new Produto_CustoED();

    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_Produto_Custo) as result from Produtos_Custos");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result") + 1;

      sql = "insert into Produtos_Custos ("+
      "OID_Produto_Custo,NM_Produto_Custo,CD_Produto_Custo,"+
      "DT_STAMP,USUARIO_STAMP,DM_STAMP,"+
      "OID_Unidade_Grupo ) values ("+
      "'"+ valOid + "','" + ed.getNm_Produto_Custo() + "','" + valOid;

      sql += "','" + ed.getDt_stamp() + "','" + ed.getUsuario_Stamp() + "','"+
      ed.getDm_Stamp() + "'," + ed.getOid_Unidade_Grupo() + ")";

      int i = executasql.executarUpdate(sql);
      Produto_CustoED.setOid_Produto_Custo(new Integer(Long.toString(valOid)));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Produto_Custo");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Produto_CustoED;

  }

  public void altera(Produto_CustoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Produtos_Custos set ";
      sql += " NM_Produto_Custo = '" + ed.getNm_Produto_Custo() + "', ";
      sql += " CD_Produto_Custo = '" + ed.getCd_Produto_Custo() + "', ";
      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "'";
      sql += " where oid_Produto_Custo = " + ed.getOid_Produto_Custo();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados de Produto_Custo");
      excecoes.setMetodo("altera(Produto_CustoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Produto_CustoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Produtos_Custos WHERE oid_Produto_Custo = ";
      sql += "(" + ed.getOid_Produto_Custo() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Produto_Custo");
      excecoes.setMetodo("deleta(Produto_CustoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Produto_CustoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = "select * from Produtos_Custos, Unidades_Grupos, Unidades_Negocios, Grupos_Produtos where "+
      " Unidades_Grupos.oid_Unidade_Negocio = Unidades_Negocios.oid_Unidade_Negocio "+
      " and Unidades_Grupos.oid_Grupo_Produto = Grupos_Produtos.oid_Grupo_Produto "+
      " and Produtos_Custos.oid_Unidade_Grupo = Unidades_Grupos.oid_Unidade_Grupo ";

      if (ed.getOid_Unidade_Grupo() != null && !ed.getOid_Unidade_Grupo().equals("")){
        sql += " and Produtos_Custos.oid_Unidade_Grupo = " + ed.getOid_Unidade_Grupo() ;
      }
      if (ed.getOid_Produto_Custo() != null && !ed.getOid_Produto_Custo().equals("")){
        sql += " and Produtos_Custos.oid_Produto_Custo = " + ed.getOid_Produto_Custo();
      }
      if (ed.getOID_Unidade_Negocio() != null && !ed.getOID_Unidade_Negocio().equals("")){
        sql += " and Unidades_Negocios.oid_Unidade_Negocio = " + ed.getOID_Unidade_Negocio();
      }
      if (ed.getNm_Produto_Custo() != null && !ed.getNm_Produto_Custo().equals("")){
        sql += " and Produtos_Custos.nm_Produto_Custo = '" + ed.getNm_Produto_Custo() + "'";
      }
      if (ed.getCd_Produto_Custo() != null && !ed.getCd_Produto_Custo().equals("")){
        sql += " and Produtos_Custos.Cd_Produto_Custo = '" + ed.getCd_Produto_Custo() + "'";
      }

       sql += " order by to_number(cd_Produto_Custo,'9999')";

      // System.out.println(sql);


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Produto_CustoED edVolta = new Produto_CustoED();

        edVolta.setOid_Produto_Custo(new Integer(res.getString("oid_Produto_Custo")));
        edVolta.setCd_Produto_Custo(res.getString("cd_Produto_Custo"));
        edVolta.setNm_Produto_Custo(res.getString("nm_Produto_Custo"));

        edVolta.setNm_Unidade_Grupo(res.getString("nm_Unidade_Negocio") + " - " + res.getString("nm_Grupo_Produto"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Produtos_Custos - SQL="+sql);
      excecoes.setMetodo("lista(Produtos_CustosED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Produto_CustoED getByRecord(Produto_CustoED ed)throws Excecoes{

    String sql = null;

    Produto_CustoED edVolta = new Produto_CustoED();

    try{

      sql = "select * from Produtos_Custos, Unidades_Grupos, Unidades_Negocios, Grupos_Produtos where "+
      " Unidades_Grupos.oid_Unidade_Negocio = Unidades_Negocios.oid_Unidade_Negocio "+
      " and Unidades_Grupos.oid_Grupo_Produto = Grupos_Produtos.oid_Grupo_Produto "+
      " and Produtos_Custos.oid_Unidade_Grupo = Unidades_Grupos.oid_Unidade_Grupo ";

      if (ed.getOid_Produto_Custo() != null &&
          !String.valueOf(ed.getOid_Produto_Custo()).equals("0")){
        sql += " and Produtos_Custos.OID_Produto_Custo = " + ed.getOid_Produto_Custo();
      }

      if (ed.getCd_Produto_Custo() != null && !ed.getCd_Produto_Custo().equals("0") && !ed.getCd_Produto_Custo().equals("")){
        sql += " and Produtos_Custos.cd_Produto_Custo = '" + ed.getCd_Produto_Custo() + "'";
      }

      if (ed.getOID_Unidade_Negocio() != null && !ed.getOID_Unidade_Negocio().equals("") && !ed.getOID_Unidade_Negocio().equals("null")){
        sql += " and Unidades_Negocios.oid_Unidade_Negocio = " + ed.getOID_Unidade_Negocio();
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Produto_CustoED();

        edVolta.setOid_Produto_Custo(new Integer(res.getString("oid_Produto_Custo")));
        edVolta.setCd_Produto_Custo(res.getString("cd_Produto_Custo"));
        edVolta.setNm_Produto_Custo(res.getString("nm_Produto_Custo"));
        edVolta.setOid_Unidade_Grupo(new Integer(res.getString("oid_Unidade_Grupo")));
        edVolta.setNm_Unidade_Grupo(res.getString("nm_Unidade_Negocio") + " - " + res.getString("nm_Grupo_Produto"));
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(Produto_CustoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(Produto_CustoED ed)throws Excecoes{

//    String sql = null;
//
//    Produto_CustoED edVolta = new Produto_CustoED();
//
//    try{
//
//      sql = "select * from Produtos_Custos where oid_Produto_Custo > 0";
//
//      if (ed.getCD_Produto_Custo() != null && !ed.getCD_Produto_Custo().equals("")){
//        sql += " and CD_Produto_Custo = '" + ed.getCD_Produto_Custo() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Produto_CustoRL Produto_Custo_rl = new Produto_CustoRL();
//      Produto_Custo_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Produto_CustoED ed)");
//    }
//
  }

}
