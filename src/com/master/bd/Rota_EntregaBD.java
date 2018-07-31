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

import com.master.ed.Rota_EntregaED;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;


public class Rota_EntregaBD {
  Calcula_FreteBD Calcula_FreteBD = null;

  private ExecutaSQL executasql;

  public Rota_EntregaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Rota_EntregaED inclui(Rota_EntregaED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;

    Rota_EntregaED Rota_EntregaED = new Rota_EntregaED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(OID_Rota_Entrega) as result from Rotas_Entregas");

      while (rs.next()) valOid = rs.getInt("result");

      valOid = valOid + 1;

      ed.setOID_Rota_Entrega(String.valueOf(valOid));

      sql = " insert into Rotas_Entregas ("+
      "OID_Rota_Entrega, "+
      "OID_Cidade, "+
      "NR_Ordem, "+
      "NR_Prazo_Entrega, "+
      "NM_Rota_Entrega, "+
      "CD_Rota_Entrega, "+
      "OID_Pessoa "+
      ") values ";
      sql += "(" + ed.getOID_Rota_Entrega() + "," +
                ed.getOID_Cidade() + "," +
                ed.getNR_Ordem() + "," +
                ed.getNR_Prazo_Entrega() + ",'" +
                ed.getNM_Rota_Entrega() + "','" +
                ed.getCD_Rota_Entrega() + "','" +
                ed.getOID_Pessoa() + "')";
      int i = executasql.executarUpdate(sql);

      Rota_EntregaED.setOID_Rota_Entrega(ed.getOID_Rota_Entrega());
    }

    catch(Exception exc){
      exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Rota_Entrega");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Rota_EntregaED;
  }

  public void altera(Rota_EntregaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Rotas_Entregas set " +
      	" NM_Rota_Entrega = '" + ed.getNM_Rota_Entrega() + "'," +
        " OID_Cidade = " + ed.getOID_Cidade() + "," +
        " NR_Ordem = " + ed.getNR_Ordem() + "," +
        " NR_Prazo_Entrega = " + ed.getNR_Prazo_Entrega() + "," +
        " OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      sql += " where oid_Rota_Entrega = " + ed.getOID_Rota_Entrega();
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

  public void deleta(Rota_EntregaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Rotas_Entregas WHERE oid_Rota_Entrega = ";
      sql += "(" + ed.getOID_Rota_Entrega() + ")";

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

  public ArrayList lista(Rota_EntregaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = " select *,"+
      " Cidade_Remetente.NM_Cidade, "+
      " Estado_Remetente.CD_Estado "+
      " from Rotas_Entregas, "+
      " cidades Cidade_Remetente," +
      " Regioes_Estados Regiao_Estado_Remetente, "+
      " Estados Estado_Remetente "+
      " WHERE "+
      " Rotas_Entregas.oid_cidade = Cidade_Remetente.oid_cidade " +
      " AND Cidade_Remetente.oid_Regiao_Estado = Regiao_Estado_Remetente.oid_Regiao_Estado " +
      " AND Estado_Remetente.oid_Estado = Regiao_Estado_Remetente.oid_Estado";

      if (String.valueOf(ed.getOID_Rota_Entrega()) != null &&
            !String.valueOf(ed.getOID_Rota_Entrega()).equals("") &&
            !String.valueOf(ed.getOID_Rota_Entrega()).equals("null")){
          sql += " and Rotas_Entregas.OID_Rota_Entrega = " + ed.getOID_Rota_Entrega();
        }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
            !String.valueOf(ed.getOID_Pessoa()).equals("") &&
            !String.valueOf(ed.getOID_Pessoa()).equals("null")){
          sql += " and Rotas_Entregas.OID_Pessoa = '" + ed.getOID_Pessoa() +"'";
        }

      if (String.valueOf(ed.getNM_Rota_Entrega()) != null &&
            !String.valueOf(ed.getNM_Rota_Entrega()).equals("") &&
            !String.valueOf(ed.getNM_Rota_Entrega()).equals("null")){
          sql += " and Rotas_Entregas.NM_Rota_Entrega = '" + ed.getNM_Rota_Entrega() + "'";
        }
      if (String.valueOf(ed.getCD_Rota_Entrega()) != null &&
            !String.valueOf(ed.getCD_Rota_Entrega()).equals("") &&
            !String.valueOf(ed.getCD_Rota_Entrega()).equals("null")){
          sql += " and Rotas_Entregas.CD_Rota_Entrega = '" + ed.getCD_Rota_Entrega() + "'";
        }

      if (String.valueOf(ed.getOID_Cidade()) != null &&
          !String.valueOf(ed.getOID_Cidade()).equals("0")){
        sql += " and Rotas_Entregas.OID_Cidade = " + ed.getOID_Cidade();
      }

      if (String.valueOf(ed.getNR_Ordem()) != null &&
            !String.valueOf(ed.getNR_Ordem()).equals("0")){
          sql += " and Rotas_Entregas.NR_Ordem = " + ed.getNR_Ordem();
        }
      if (String.valueOf(ed.getNR_Prazo_Entrega()) != null &&
            !String.valueOf(ed.getNR_Prazo_Entrega()).equals("0")){
          sql += " and Rotas_Entregas.NR_Prazo_Entrega = " + ed.getNR_Prazo_Entrega();
        }

      sql += " Order by Rotas_Entregas.cd_rota_entrega, Rotas_Entregas.nr_ordem, Rotas_Entregas.oid_Rota_Entrega ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        Rota_EntregaED edVolta = new Rota_EntregaED();

        edVolta.setOID_Rota_Entrega(res.getString("OID_Rota_Entrega"));
        edVolta.setNM_Rota_Entrega(res.getString("NM_Rota_Entrega"));
        edVolta.setCD_Rota_Entrega(res.getString("CD_Rota_Entrega"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        PessoaBean pessoa = PessoaBean.getByOID(res.getString("OID_Pessoa"));
        edVolta.setNM_Pessoa(pessoa.getNM_Razao_Social());
        edVolta.setNR_CNPJ_CPF(pessoa.getNR_CNPJ_CPF());
        edVolta.setNM_Endereco_Pessoa(pessoa.getNM_Endereco());
        edVolta.setNR_Telefone(pessoa.getNR_Telefone());
        edVolta.setOID_Cidade(res.getLong("OID_Cidade"));
        edVolta.setNM_Cidade(res.getString("NM_Cidade"));
        edVolta.setCD_Estado(res.getString("CD_Estado"));
        edVolta.setNR_Ordem(res.getLong("NR_Ordem"));
        edVolta.setNR_Prazo_Entrega(res.getLong("NR_Prazo_Entrega"));

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

  public Rota_EntregaED getByRecord(Rota_EntregaED ed)throws Excecoes{

    String sql = null;

    Rota_EntregaED edVolta = new Rota_EntregaED();

    try{

      sql = " select * from Rotas_Entregas where 1=1 ";

      if (JavaUtil.doValida(ed.getOID_Rota_Entrega())){
    	  sql += " and OID_Rota_Entrega = " + ed.getOID_Rota_Entrega();
      }
      if (JavaUtil.doValida(ed.getCD_Rota_Entrega())){
    	  sql += " and CD_Rota_Entrega = '" + ed.getCD_Rota_Entrega() + "'";
      }

//      // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){

        edVolta.setOID_Rota_Entrega(res.getString("OID_Rota_Entrega"));
        edVolta.setNM_Rota_Entrega(res.getString("NM_Rota_Entrega"));
        edVolta.setCD_Rota_Entrega(res.getString("CD_Rota_Entrega"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Cidade(res.getLong("OID_Cidade"));
        edVolta.setNR_Ordem(res.getLong("NR_Ordem"));
        edVolta.setNR_Prazo_Entrega(res.getLong("NR_Prazo_Entrega"));

//      // System.out.println(res.getString("NM_Rota_Entrega"));

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

  public ArrayList getRotas()throws Excecoes{

	    String sql = null;
	    ArrayList list = new ArrayList();

	    try{

	      sql = " select distinct(cd_rota_entrega), nm_rota_entrega "+
	       	    " from Rotas_Entregas ";

	      sql += " Group by cd_rota_entrega, nm_Rota_Entrega ";
//// System.out.println("getRotas> "+ sql);
	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);

	      while (res.next()){
	        Rota_EntregaED edVolta = new Rota_EntregaED();

	        edVolta.setNM_Rota_Entrega(res.getString("NM_Rota_Entrega"));
	        edVolta.setCD_Rota_Entrega(res.getString("CD_Rota_Entrega"));

	        list.add(edVolta);
	      }
	    }
	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao listar");
	      excecoes.setMetodo("getRotas");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }

	    return list;
	  }

  public ArrayList listaCDByPessoa(Rota_EntregaED ed)throws Excecoes{

	    String sql = null;
	    ArrayList list = new ArrayList();

	    try{

	      sql = " select distinct(cd_rota_entrega), nm_rota_entrega "+
	       	    " from Rotas_Entregas ";
	      sql += " Group by cd_rota_entrega, nm_Rota_Entrega ";

	      if(JavaUtil.doValida(ed.getOID_Pessoa())){
	    	  sql = " select distinct(cd_rota_entrega), nm_rota_entrega, oid_pessoa "+
	       	    	" from Rotas_Entregas ";
	    	  sql += " where oid_pessoa = '" + ed.getOID_Pessoa() + "'";
	    	  sql += " Group by cd_rota_entrega, nm_Rota_Entrega, oid_pessoa ";
	      }

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);

	      while (res.next()){
	        Rota_EntregaED edVolta = new Rota_EntregaED();

	        edVolta.setNM_Rota_Entrega(res.getString("NM_Rota_Entrega"));
	        edVolta.setCD_Rota_Entrega(res.getString("CD_Rota_Entrega"));

	        list.add(edVolta);
	      }
	    }
	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao listar");
	      excecoes.setMetodo("listaCDByPessoa");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }

	    return list;
	  }

  public Rota_EntregaED getCDByPessoa(Rota_EntregaED ed)throws Excecoes{

	    String sql = null;
	    ArrayList list = new ArrayList();
	    Rota_EntregaED edVolta = new Rota_EntregaED();

	    try{

	      sql = " select distinct(cd_rota_entrega), nm_rota_entrega "+
	       	    " from Rotas_Entregas ";
	      sql += " Group by cd_rota_entrega, nm_Rota_Entrega ";

	      if(JavaUtil.doValida(ed.getOID_Pessoa())){
	    	  sql = " select distinct(cd_rota_entrega), nm_rota_entrega, oid_pessoa "+
	       	    	" from Rotas_Entregas ";
	    	  sql += " where oid_pessoa = '" + ed.getOID_Pessoa() + "'";
	    	  sql += " Group by cd_rota_entrega, nm_Rota_Entrega, oid_pessoa ";
	      }

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);

	      while (res.next()){
	        edVolta = new Rota_EntregaED();
	        edVolta.setNM_Rota_Entrega(res.getString("NM_Rota_Entrega"));
	        edVolta.setCD_Rota_Entrega(res.getString("CD_Rota_Entrega"));
	      }
	    }
	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao listar");
	      excecoes.setMetodo("listaCDByPessoa");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }

	    return edVolta;
	  }

//  public byte[] geraRelRota_EntregaEmbarque(Rota_EntregaED ed)throws Excecoes{
//
//    String sql = null;
//    byte[] b = null;
//
//
//
//    Rota_EntregaED edVolta = new Rota_EntregaED();
//
//    try{
//  	sql = " select * from Rotas_Entregas where 1=1 ";
//
//  	if (String.valueOf(ed.getOID_Rota_Entrega()) != null &&
//      	!String.valueOf(ed.getOID_Rota_Entrega()).equals("") &&
//      	!String.valueOf(ed.getOID_Rota_Entrega()).equals("null")){
//    	sql += " and OID_Rota_Entrega = " + ed.getOID_Rota_Entrega();
//  	}
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql.toString());
//
//      Rota_EntregaRL conRL = new Rota_EntregaRL();
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
//      exce.setMetodo("geraRelatorio(Rota_EntregaED ed)");
//    }
//    return b;
//  }

}
