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
import java.util.Iterator;

import com.master.ed.Imposto_UnidadeED;
import com.master.ed.Mensagem_VendedorED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class Imposto_UnidadeBD {

  private ExecutaSQL executasql;

  public Imposto_UnidadeBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Imposto_UnidadeED inclui(Imposto_UnidadeED ed) throws Excecoes{

    String sql = null;
    int valOid = 0;
    Imposto_UnidadeED Imposto_UnidadeED = new Imposto_UnidadeED();

    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_Imposto_Unidade) as result from Impostos_Unidades");

      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Impostos_Unidades ("+
      "oid_Imposto_Unidade, oid_Imposto, oid_Unidade, oid_Centro_Custo, oid_Conta,  oid_Pessoa, DM_Vencimento) values ("+
      ""+ ++valOid + ",'" + ed.getOid_Imposto() + "','" + ed.getOid_Unidade() + "','" + ed.getOid_Centro_Custo() + "','" + ed.getOid_Conta() + "','" + ed.getOid_Pessoa() + "','" + ed.getDm_Vencimento()  + "')";


      // System.err.println(sql);


      executasql.executarUpdate(sql);
      Imposto_UnidadeED.setOid_Imposto_Unidade(valOid);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Imposto_Unidade");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Imposto_UnidadeED;

  }

  public void altera(Imposto_UnidadeED ed) throws Excecoes{

    String sql = null;

    try{


      sql = " UPDATE Impostos_Unidades set "; 
      sql += " oid_Pessoa       = '" + ed.getOid_Pessoa()      + "', ";
      sql += " oid_Conta        = '" + ed.getOid_Conta()      + "', ";
      sql += " oid_Centro_Custo = '" + ed.getOid_Centro_Custo()      + "', ";
      sql += " DM_Vencimento      = '" + ed.getDm_Vencimento()      + "' ";
      sql += " where oid_Imposto_Unidade = " + ed.getOid_Imposto_Unidade();

      // System.out.println(sql);
      
      executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar. ");
      excecoes.setMetodo("altera(Imposto_UnidadeED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void atualiza(Imposto_UnidadeED ed) throws Excecoes{

	    String sql = null;

	    try{

	    	  sql = " SELECT  Impostos_Unidades.*, Tipos_Impostos.*  " + 
	    	    " FROM Impostos_Unidades, Tipos_Impostos, Unidades " + 
	            " WHERE Impostos_Unidades.oid_Imposto = Tipos_Impostos.oid_Tipo_Imposto " +
	      		" AND   Impostos_Unidades.oid_Unidade = Unidades.oid_Unidade ";

		      if (ed.getOid_Imposto_Unidade()>0) {
		    	  sql += " AND Impostos_Unidades.oid_Imposto_Unidade = " + ed.getOid_Imposto_Unidade();
		      }

		      ResultSet res = this.executasql.executarConsulta(sql);

		      //popula
		      while (res.next()){
		    	  sql="";
		    	  //IMPOSTO DE RENDA
		    	  if ("IR".equals(res.getString("DM_Tipo_Imposto"))) {
			    	  if ("OPF".equals(res.getString("DM_Tipo_Imposto"))) {
				    	  sql = " SELECT  SUM (VL_IRRF) as tt_Imposto FROM Ordens_Fretes  " +
				    	        " WHERE Ordens_Fretes.DM_Ipresso ='S' " +
				    	        " AND   Ordens_Fretes.DM_Situacao <>'C' " +
				    	        " AND   Ordens_Fretes.oid_Unidade = " + res.getLong("oid_Unidade") +
				    	        " AND   Ordens_Fretes.DT_Emissao >='" + ed.getDm_Vencimento() + "'" +
				    	        " AND   Ordens_Fretes.DT_Emissao <='" + ed.getDm_Vencimento() + "'";
			    	  }
		    	  }
		    	  if (!"".equals(sql)) {
				      ResultSet resImp = this.executasql.executarConsulta(sql);
				      if (resImp.next()){
				    	  sql =" SELECT oid_Imposto FROM Impostos " + 
				    	       " WHERE oid_Imposto_Unidade = "	+ res.getLong("DM_Tipo_Imposto") + 
				    	       " AND   dm_Periodo='" + ed.getDm_Periodo() +"'";
				    	  
				         // sql = "insert into Impostos_Unidades ("+
				         // "oid_Imposto_Unidade, oid_Imposto, oid_Unidade, oid_Centro_Custo, oid_Conta,  oid_Pessoa, DM_Vencimento) values ("+
				         // ""+ ++valOid + ",'" + ed.getOid_Imposto() + "','" + ed.getOid_Unidade() + "','" + ed.getOid_Centro_Custo() + "','" + ed.getOid_Conta() + "','" + ed.getOid_Pessoa() + "','" + ed.getDm_Vencimento()  + "')";


				          // System.err.println(sql);


				          executasql.executarUpdate(sql);
				    	  
				    	  //sql =" INSERT into Impostos (
				      }

		    	  }
		      }

	    
	    }

	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao alterar. ");
	      excecoes.setMetodo("altera(Imposto_UnidadeED)");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
  }
  
  
  public void deleta(Imposto_UnidadeED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Impostos_Unidades WHERE oid_Imposto_Unidade = ";
      sql += "(" + ed.getOid_Imposto_Unidade() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Imposto_Unidade");
      excecoes.setMetodo("deleta(Imposto_UnidadeED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Imposto_UnidadeED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = " SELECT  Impostos_Unidades.*, Tipos_Impostos.*, Contas.*, Centros_Custos.*, Pessoas.NM_Fantasia " + 
    	    " FROM Impostos_Unidades, Tipos_Impostos, Unidades, Pessoas, Contas, Centros_Custos " + 
            " WHERE Impostos_Unidades.oid_Imposto = Tipos_Impostos.oid_Tipo_Imposto " +
            " AND   Impostos_Unidades.oid_Conta = Contas.oid_Conta " +
            " AND   Impostos_Unidades.oid_Centro_Custo = Centros_Custos.oid_Centro_Custo " +
      		" AND   Impostos_Unidades.oid_Unidade = Unidades.oid_Unidade " +
            " AND   Unidades.oid_Pessoa = Pessoas.oid_Pessoa ";

      if (ed.getOid_Imposto_Unidade()>0) {
    	  sql += " AND Impostos_Unidades.oid_Imposto_Unidade = " + ed.getOid_Imposto_Unidade();
      }
      
      if (ed.getOid_Unidade()>0) {
    	  sql += " AND Impostos_Unidades.oid_Unidade = " + ed.getOid_Unidade();
      }
      if (ed.getOid_Imposto()>0) {
    	  sql += " AND Impostos_Unidades.oid_Imposto = " + ed.getOid_Imposto();
      }
      	
      sql+=	" ORDER BY Pessoas.NM_Fantasia, Tipos_Impostos.CD_Tipo_Imposto ";

      // System.err.println(sql);
      ResultSet res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Imposto_UnidadeED edVolta = new Imposto_UnidadeED();

        edVolta.setOid_Imposto_Unidade(res.getInt("oid_Imposto_Unidade"));
        edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
        edVolta.setOid_Imposto(res.getInt("oid_Imposto"));
        edVolta.setOid_Conta(res.getInt("oid_Conta"));

        edVolta.setOid_Centro_Custo(res.getInt("oid_Centro_Custo"));
        edVolta.setCD_Centro_Custo(res.getString("CD_Centro_Custo"));
        edVolta.setNM_Centro_Custo(res.getString("NM_Centro_Custo"));

        edVolta.setOid_Conta(res.getInt("oid_Conta"));
        edVolta.setCD_Conta(res.getString("CD_Conta"));
        edVolta.setNM_Conta(res.getString("NM_Conta"));
        
        edVolta.setCD_Tipo_Imposto(res.getString("CD_Tipo_Imposto"));
        edVolta.setNM_Tipo_Imposto(res.getString("NM_Tipo_Imposto"));
        edVolta.setDm_Vencimento(res.getString("Dm_Vencimento"));
        edVolta.setNM_Fantasia(res.getString("nm_Fantasia"));

    	edVolta.setNR_CNPJ_CPF(" ");
    	edVolta.setNM_Razao_Social(" ");
        
        sql = 	" SELECT  Pessoas.NR_CNPJ_CPF, Pessoas.NM_Razao_Social " + 
			    " FROM  Pessoas " + 
		        " WHERE Pessoas.oid_Pessoa ='" + res.getString("oid_Pessoa") +"'";

        ResultSet resAux = this.executasql.executarConsulta(sql);
        if (resAux.next()){
            edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
        	edVolta.setNR_CNPJ_CPF(resAux.getString("NR_CNPJ_CPF"));
        	edVolta.setNM_Razao_Social(resAux.getString("NM_Razao_Social"));
        }

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Impostos_Unidades - SQL="+sql);
      excecoes.setMetodo("lista(Impostos_UnidadesED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  
  public Imposto_UnidadeED getByRecord(Imposto_UnidadeED ed) throws Excecoes {

      try {
          Iterator iterator = this.lista(ed).iterator();
          return iterator.hasNext() ? (Imposto_UnidadeED) iterator.next() : new Imposto_UnidadeED();
      } catch(Exception exc) {
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
      }
  }


  public void geraRelatorio(Imposto_UnidadeED ed)throws Excecoes{

  }

}