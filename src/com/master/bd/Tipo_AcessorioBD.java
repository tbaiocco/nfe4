package com.master.bd;

/**
 * <p>Title: Tipo_AcessorioBD </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.ed.Tipo_AcessorioED;
import java.util.*;
import java.sql.*;
import com.master.root.FormataDataBean;

public class Tipo_AcessorioBD {

  private ExecutaSQL executasql;

  public Tipo_AcessorioBD(ExecutaSQL sql) {
    this.executasql = sql;
  }


  public Tipo_AcessorioED inclui(Tipo_AcessorioED ed) throws Excecoes{

      String sql = null;
      long valOid = 0;
      String chave = null;
      FormataDataBean dataFormatada = new FormataDataBean();

      try{
        ResultSet rs = executasql.executarConsulta("select max(oid_Tipo_Acessorio) as result from Tipos_Acessorios ");

        //pega proximo valor da chave
        while (rs.next()){
          valOid = rs.getLong("result");
          ed.setOid_Tipo_Acessorio(++valOid);
        }

        sql = " insert into Tipos_Acessorios (oid_Tipo_Acessorio, nm_Tipo_Acessorio, dm_tipo_Acessorio,  " +
        		"dt_stamp, usuario_stamp, dm_stamp) values ";
        sql += "(" + ed.getOid_Tipo_Acessorio() + ",'" + ed.getNM_Tipo_Acessorio() + "','" + ed.getDM_Tipo_Acessorio() + "','" +
                ed.getDt_stamp()+"','"+ed.getUsuario_Stamp()+"','"+ed.getDm_Stamp()+"')";

        int i = executasql.executarUpdate(sql);
      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMetodo("inclui()");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return ed;
    }

    public void altera(Tipo_AcessorioED ed) throws Excecoes{

      String sql = null;
      FormataDataBean dataFormatada = new FormataDataBean();

      try{
        sql = "update Tipos_Acessorios "+
              "set nm_Tipo_Acessorio = '"+ed.getNM_Tipo_Acessorio()+
              "', dt_stamp ='"+Data.getDataDMY()+"'"+
              ", usuario_stamp ='"+ed.getUsuario_Stamp()+"'"+
              ", oid_estoque ="+ed.getOid_Estoque() +
              ", DM_Tipo_Acessorio ='"+ed.getDM_Tipo_Acessorio()+"'"+
              ", dm_stamp ='S' "+
              "Where OID_Tipo_Acessorio ="+ed.getOid_Tipo_Acessorio();

	      // System.out.print(sql);
        int i = executasql.executarUpdate(sql);
      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar");
        excecoes.setMetodo("alterar()");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

    public void deleta(Tipo_AcessorioED ed) throws Excecoes{

      String sql = null;
      String DM_Impresso = null;

      try{
        sql = "delete from Tipos_Acessorios Where oid_Tipo_Acessorio ="+ed.getOid_Tipo_Acessorio();

        int i = executasql.executarUpdate(sql);
      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMetodo("excluir()");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }


    public ArrayList lista(Tipo_AcessorioED ed)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();
      FormataDataBean dataFormatada = new FormataDataBean();

      try{
        sql = "Select * "+
              "From  Tipos_Acessorios "+
              "Where 1=1";

        if (String.valueOf(ed.getDM_Tipo_Acessorio()) != null &&
                !String.valueOf(ed.getDM_Tipo_Acessorio()).equals("") &&
                !String.valueOf(ed.getDM_Tipo_Acessorio()).equals("null") &&
                !String.valueOf(ed.getDM_Tipo_Acessorio()).equals("0")){
              sql += " AND DM_Tipo_Acessorio = '" + ed.getDM_Tipo_Acessorio() + "'";
            }

        if (String.valueOf(ed.getOid_Tipo_Acessorio()) != null &&
                !String.valueOf(ed.getOid_Tipo_Acessorio()).equals("") &&
                !String.valueOf(ed.getOid_Tipo_Acessorio()).equals("null") &&
                !String.valueOf(ed.getOid_Tipo_Acessorio()).equals("0")){
              sql += " AND oid_Tipo_Acessorio = '" + ed.getOid_Tipo_Acessorio() + "'";
            }

        if (ed.getNM_Tipo_Acessorio() != null &&
                !ed.getNM_Tipo_Acessorio().equals("") &&
                !ed.getNM_Tipo_Acessorio().equals("null")){
              sql += " AND nm_Tipo_Acessorio LIKE '" + ed.getNM_Tipo_Acessorio() + "%'";
            }
        sql += " ORDER BY NM_Tipo_Acessorio";
        
        ResultSet res = null;
        res = this.executasql.executarConsulta(sql);
        double valor = 0;
        //popula
        while (res.next()){
          Tipo_AcessorioED edVolta = new Tipo_AcessorioED();
          edVolta.setOid_Tipo_Acessorio(res.getLong("oid_Tipo_Acessorio"));
          edVolta.setNM_Tipo_Acessorio(res.getString("NM_Tipo_Acessorio"));
          edVolta.setDM_Tipo_Acessorio(res.getString("DM_Tipo_Acessorio"));
          list.add(edVolta);
        }
      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar()");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return list;
    }

    public Tipo_AcessorioED getByRecord(Tipo_AcessorioED ed)throws Excecoes{

      String sql = null;
      Tipo_AcessorioED edVolta = new Tipo_AcessorioED();
      FormataDataBean dataFormatada = new FormataDataBean();

      try{
			sql = "Select * "+
			"From  Tipos_Acessorios "+
			"Where 1=1 ";
			if (String.valueOf(ed.getOid_Tipo_Acessorio()) != null &&
			        !String.valueOf(ed.getOid_Tipo_Acessorio()).equals("") &&
			!String.valueOf(ed.getOid_Tipo_Acessorio()).equals("null") &&
			!String.valueOf(ed.getOid_Tipo_Acessorio()).equals("0")){
			  sql += " AND oid_Tipo_Acessorio = '" + ed.getOid_Tipo_Acessorio() + "'";
			    }
			if("COMPARAR".equals(ed.getDM_Tipo_Acessorio()))
				sql += " AND nm_Tipo_Acessorio = '" + ed.getNM_Tipo_Acessorio() + "'";
			else{
				if (ed.getNM_Tipo_Acessorio() != null && 
						!ed.getNM_Tipo_Acessorio().equals("") &&
						!ed.getNM_Tipo_Acessorio().equals("null")){
				  sql += " AND nm_Tipo_Acessorio like '" + ed.getNM_Tipo_Acessorio() + "%'";
				}
			}
			
			sql += " ORDER BY OID_Tipo_Acessorio";
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			double valor = 0;
			//popula
			while (res.next()){
			  edVolta.setOid_Tipo_Acessorio(res.getLong("oid_Tipo_Acessorio"));
			  edVolta.setNM_Tipo_Acessorio(res.getString("NM_Tipo_Acessorio"));
			  edVolta.setOid_Estoque(res.getLong("oid_Estoque"));
			  edVolta.setDM_Tipo_Acessorio(res.getString("DM_Tipo_Acessorio"));
		    }
      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar");
        excecoes.setMetodo("getByRecord()");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return edVolta;
    }


}