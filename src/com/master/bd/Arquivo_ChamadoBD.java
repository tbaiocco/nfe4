/*
 * Created on 05/07/2006
 */
package com.master.bd;

/**
 * by Jonas
 **/

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Arquivo_ChamadoED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class Arquivo_ChamadoBD {


	private ExecutaSQL executasql;

	public Arquivo_ChamadoBD(ExecutaSQL sql){
		this.executasql = sql;
	}

	public Arquivo_ChamadoED inclui(Arquivo_ChamadoED ed) throws Excecoes{

		  	String sql = null;
		  	long valOid = 0;

		  	Arquivo_ChamadoED Arquivo_ChamadoED = new Arquivo_ChamadoED();



		    try{
		      ResultSet rs = executasql.executarConsulta("select oid_arquivo_chamado as result from arquivos_chamados order by oid_arquivo_chamado desc limit 1");
		      while (rs.next()){
		      	valOid = rs.getLong("result");
		      }
		      valOid++;

//		      // System.out.println();
//			    // System.out.println("nm_arquivo "+ed.getNM_Arquivo());
//			    // System.out.println("nm_patch_arquivo "+ed.getNM_Path_Arquivo());
//			    // System.out.println("oid_arquivo_chamado "+valOid);
//			    // System.out.println("oid_Chamado "+ed.getOID_Chamado());
//			    // System.out.println();

		      ed.setOID_Arquivo_Chamado(Long.toString(valOid));

		      sql = " insert into  Arquivos_Chamados (oid_arquivo_chamado, oid_chamado, nm_arquivo, nm_path_arquivo) values ";
		      sql += "('" + ed.getOID_Arquivo_Chamado() + "','" + ed.getOID_Chamado() + "','" + ed.getNM_Arquivo() + "','" + ed.getNM_Path_Arquivo() + "')";

		      // System.out.println();
		      // System.out.println(sql);
		      // System.out.println();

		      int i = executasql.executarUpdate(sql);

		      Arquivo_ChamadoED.setOID_Arquivo_Chamado(ed.getOID_Arquivo_Chamado());

		    }
		      catch(Exception exc){
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao incluir Arquivo_Chamado");
		        excecoes.setMetodo("inclui");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }
		      return Arquivo_ChamadoED;

		  }

		  public Arquivo_ChamadoED altera(Arquivo_ChamadoED ed) throws Excecoes{

			  	String sql = null;

			  	Arquivo_ChamadoED Arquivo_ChamadoED = new Arquivo_ChamadoED();

			    try{

			        sql = " update Arquivos_Chamados set nm_arquivo = '" + ed.getNM_Arquivo() + "', nm_path_arquivo = '" + ed.getNM_Path_Arquivo() + "'";
			        sql += " where oid_arquivo_chamado = '" + ed.getOID_Arquivo_Chamado() + "'";

			        int i = executasql.executarUpdate(sql);

			      }

			      catch(Exception exc){
			        Excecoes excecoes = new Excecoes();
			        excecoes.setClasse(this.getClass().getName());
			        excecoes.setMensagem("Erro ao alterar Arquivo_chamado");
			        excecoes.setMetodo("altera");
			        excecoes.setExc(exc);
			        throw excecoes;
			      }
			      return Arquivo_ChamadoED;
			  }

		  public void deleta(Arquivo_ChamadoED ed) throws Excecoes{

		    String sql = null;

		    try{

		      sql = "delete from Arquivos_Chamados WHERE oid_arquivo_chamado = ";
		      sql += "'" + ed.getOID_Arquivo_Chamado() + "'";

		      int i = executasql.executarUpdate(sql);
		    }

		      catch(Exception exc){
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao excluir Arquivo_Chamado");
		        excecoes.setMetodo("excluir");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }
		  }

		  public ArrayList lista(Arquivo_ChamadoED ed)throws Excecoes{

		    String sql = null;
		    ArrayList list = new ArrayList();

		    try{

		    	sql="select * from arquivos_chamados where oid_chamado = '"+ed.getOID_Chamado()+"'";

		      ResultSet res = null;
		      res = this.executasql.executarConsulta(sql);

		      FormataDataBean DataFormatada = new FormataDataBean();

		      while (res.next()){
		      	Arquivo_ChamadoED edVolta = new Arquivo_ChamadoED();

		        edVolta.setOID_Arquivo_Chamado(res.getString("oid_arquivo_chamado"));
		        edVolta.setOID_Chamado(res.getLong("oid_chamado"));

		        edVolta.setNM_Arquivo(res.getString("nm_arquivo"));
		        edVolta.setNM_Path_Arquivo(res.getString("nm_path_arquivo"));

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

		  public Arquivo_ChamadoED getByRecord(Arquivo_ChamadoED ed)throws Excecoes{

		    String sql = null;

		    Arquivo_ChamadoED edVolta = new Arquivo_ChamadoED();

		    try{

		      sql =  " SELECT * FROM Arquivos_Chamados WHERE";

		      if (String.valueOf(ed.getOID_Arquivo_Chamado()) != null &&
		          !String.valueOf(ed.getOID_Arquivo_Chamado()).equals("")){
		        sql += " OID_Arquivo_Chamado = " + ed.getOID_Arquivo_Chamado();
		      }

		      ResultSet res = null;
		      res = this.executasql.executarConsulta(sql);
		      while (res.next()){
		        edVolta.setOID_Chamado(res.getLong("OID_Chamado"));
		        edVolta.setOID_Arquivo_Chamado(res.getString("OID_Arquivo_Chamado"));
		        edVolta.setNM_Arquivo(res.getString("nm_arquivo"));
		        edVolta.setNM_Path_Arquivo(res.getString("nm_path_arquivo"));
		      }
		    }
		      catch(Exception exc){
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao selecionar");
		        excecoes.setMetodo("seleção");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }

		    return edVolta;
		  }


}
