/*
 * Created on 10/09/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Situacao_TributariaED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Situacao_TributariaBD {

    private ExecutaSQL executasql;

    public Situacao_TributariaBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Situacao_TributariaED inclui(Situacao_TributariaED ed) throws Excecoes {

        String sql = null;
        int valOid = 0;
        Situacao_TributariaED edVolta = new Situacao_TributariaED();

        try {

            sql  = " select count(*) as result from situacoes_tributarias";
            sql += " where cd_Situacao_Tributaria = '" + ed.getCD_Situacao_Tributaria() +"'";

            ResultSet res = this.executasql.executarConsulta(sql);

            //*** Varifica se existe
            while (res.next()) {
                valOid = res.getInt("result");
            }
            
            if (valOid <= 0){            	
            	sql  = " select max(oid_Situacao_Tributaria) as count from situacoes_tributarias";

            	res = this.executasql.executarConsulta(sql);

                //*** Pega o valor máximo
                while (res.next())
                    valOid = res.getInt("count");                    
                
                sql = "insert into situacoes_tributarias ("
                    + "		oid_Situacao_Tributaria, " +
                 	  "     cd_Situacao_Tributaria, " +
                 	  "		dm_Procedencia," +
                 	  "		cd_tipo," +
                 	  "		nm_situacao_tributaria) values ("
                    + ++valOid +",'" + ed.getCD_Situacao_Tributaria() + "','" + ed.getDM_Procedencia() + "'," +
                      "'"+ed.getCD_Tipo()+ "','"+ ed.getNM_Situacao_Tributaria() +"')";
                
                executasql.executarUpdate(sql);
                edVolta.setOid_Situacao_Tributaria(ed.getOid_Situacao_Tributaria());   
                
            }
            return edVolta;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "inclui()");
        }
    }

    public void altera(Situacao_TributariaED ed) throws Excecoes {

        String sql = null;
        try {

            sql =  " update situacoes_tributarias set ";
            sql += " cd_Situacao_Tributaria  = '" + ed.getCD_Situacao_Tributaria() + "', ";
            sql += " dm_Procedencia = '" + ed.getDM_Procedencia() + "',";
            sql += " cd_tipo = '" + ed.getCD_Tipo() + "',";
            sql += " nm_situacao_tributaria = '" + ed.getNM_Situacao_Tributaria() + "'";
            sql += " where oid_Situacao_Tributaria = " + ed.getOid_Situacao_Tributaria();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "altera()");
        }
    }

    public void deleta(Situacao_TributariaED ed) throws Excecoes {

        String sql = null;
        try {

            sql =  " delete from situacoes_tributarias " +
            	   " where oid_Situacao_Tributaria = " + ed.getOid_Situacao_Tributaria();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(Situacao_TributariaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " select oid_Situacao_Tributaria, " +
            	  "		   cd_Situacao_Tributaria, " +
            	  " 	   dm_Procedencia, " +
            	  " 	   cd_tipo, " +
            	  "        nm_situacao_tributaria " +
            	  " from situacoes_tributarias " ;
            if (ed.getNM_Situacao_Tributaria()!= null)
            	sql +=" WHERE nm_situacao_tributaria LIKE '" + ed.getNM_Situacao_Tributaria() + "%' ";
        	sql +=" order by cd_Situacao_Tributaria";
            
             ResultSet res = this.executasql.executarConsulta(sql);
             while (res.next())
             {
                Situacao_TributariaED edVolta = new Situacao_TributariaED();
          
                edVolta.setOid_Situacao_Tributaria(res.getInt("oid_Situacao_Tributaria"));
                edVolta.setCD_Situacao_Tributaria(res.getString("cd_Situacao_Tributaria"));
                edVolta.setDM_Procedencia(res.getString("dm_Procedencia"));
                edVolta.setCD_Tipo(res.getString("cd_tipo"));
                edVolta.setNM_Situacao_Tributaria(res.getString("nm_situacao_tributaria"));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "lista()");
        }
        return list;
    }
    
    public Situacao_TributariaED getByRecord(Situacao_TributariaED ed) throws Excecoes {

        String sql = null;
        Situacao_TributariaED edVolta = new Situacao_TributariaED();
        try {

            sql = " select oid_Situacao_Tributaria, " +
      	  		  "		   cd_Situacao_Tributaria, " +
      	  		  " 	   dm_Procedencia, " +
      	  		  " 	   cd_tipo, " +
      	  		  "        nm_situacao_tributaria " +
      	  		  " from situacoes_tributarias";
        	sql+= " where 1 = 1";
      
            if (ed.getOid_Situacao_Tributaria() > 0 )
                sql += " and oid_Situacao_Tributaria = " + ed.getOid_Situacao_Tributaria();

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta.setOid_Situacao_Tributaria(res.getInt("oid_Situacao_Tributaria"));
                edVolta.setCD_Situacao_Tributaria(res.getString("cd_Situacao_Tributaria"));
                edVolta.setDM_Procedencia(res.getString("dm_Procedencia"));
                edVolta.setCD_Tipo(res.getString("cd_tipo"));
                edVolta.setNM_Situacao_Tributaria(res.getString("nm_situacao_tributaria"));     

            }
           return edVolta;
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "getByRecord()");
        }
    }
    
    public Situacao_TributariaED getByOidSituacao_Tributaria(int oid_Situacao_Tributaria) throws Excecoes {

        String sql = null;
        Situacao_TributariaED edVolta = new Situacao_TributariaED();
        try {

            sql = " select oid_Situacao_Tributaria, " +
	  		  	  "		   cd_Situacao_Tributaria, " +
	  		  	  " 	   dm_Procedencia, " +
	  		  	  " 	   cd_tipo, " +
	  		  	  "        nm_situacao_tributaria " +
	  		  	  " from situacoes_tributarias";
        	sql+= " where oid_Situacao_Tributaria = " + oid_Situacao_Tributaria;
        	
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta.setOid_Situacao_Tributaria(res.getInt("oid_Situacao_Tributaria"));
                edVolta.setCD_Situacao_Tributaria(res.getString("cd_Situacao_Tributaria"));
                edVolta.setDM_Procedencia(res.getString("dm_Procedencia"));
                edVolta.setCD_Tipo(res.getString("cd_tipo"));
                edVolta.setNM_Situacao_Tributaria(res.getString("nm_situacao_tributaria"));
            }
           return edVolta;
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "getByOidSituacao_Tributaria()");
        }
    }
    
    public Situacao_TributariaED getByCD_Situacao_Tributaria(String cd_Situacao_Tributaria) throws Excecoes {

        String sql = null;
        Situacao_TributariaED edVolta = new Situacao_TributariaED();
        try {

            sql = " select oid_Situacao_Tributaria, " +
		  	  	  "		   cd_Situacao_Tributaria, " +
		  	  	  " 	   dm_Procedencia, " +
		  	  	  " 	   cd_tipo, " +
		  	  	  "        nm_situacao_tributaria " +
		  	  	  " from situacoes_tributarias";
        	sql+= " where cd_Situacao_Tributaria = '" + cd_Situacao_Tributaria +"'";
      
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta.setOid_Situacao_Tributaria(res.getInt("oid_Situacao_Tributaria"));
                edVolta.setCD_Situacao_Tributaria(res.getString("cd_Situacao_Tributaria"));
                edVolta.setDM_Procedencia(res.getString("dm_Procedencia"));
                edVolta.setCD_Tipo(res.getString("cd_tipo"));
                edVolta.setNM_Situacao_Tributaria(res.getString("nm_situacao_tributaria"));
                
            }
           return edVolta;
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "getByCD_Situacao_Tributaria()");
        }
    }
    
    public boolean doExiste(Situacao_TributariaED ed) throws Excecoes {
        
        String sql = null;
        boolean existe = false;
        try {
            sql = " select dm_Procedencia, " +
		  	  	  " 	   cd_tipo " +
		  	  	  " from situacoes_tributarias";
        	sql+= " where dm_Procedencia = '"+ ed.getDM_Procedencia() +"'" +
        		  " and   cd_tipo = '"+ ed.getCD_Tipo() +"'";
      
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
                existe = true;
            
            return existe;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, exc.getClass().getName(), "doExiste()");
        }
    }
}