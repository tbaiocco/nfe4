package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Relatorio_Gerencial_GrupoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis Steigleder
 * @serial Relorios_Gerenciais_Grupos
 * @serialData 08/01/2006
 */

public class Relatorio_Gerencial_GrupoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Relatorio_Gerencial_GrupoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Relatorio_Gerencial_GrupoED inclui(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Relatorio_Gerencial_Grupo (getAutoIncremento("oid_Relatorio_Gerencial_Grupo", "Relatorios_Gerenciais_Grupos"));
            sql = " INSERT INTO " +
            	  " Relatorios_Gerenciais_Grupos " +
            	  " (" +
            	  "	oid_Relatorio_Gerencial_Grupo," +
            	  "	cd_Relatorio_Gerencial_Grupo," +
            	  "	nm_Relatorio_Gerencial_Grupo," +
            	  "	oid_Relatorio_Gerencial" +
            	  " )" +
            	  " VALUES " +
            	  " (" +
            	  ed.getOid_Relatorio_Gerencial_Grupo() + ",'" + 
            	  ed.getCd_Relatorio_Gerencial_Grupo() + "','" + 
            	  ed.getNm_Relatorio_Gerencial_Grupo() + "'," + 
            	  ed.getOid_Relatorio_Gerencial() + 
            	  " )";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Relatorio_Gerencial_GrupoED ed)");
        }
    }

    public void altera(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE " +
            	   " Relatorios_Gerenciais_Grupos " +
            	   " SET ";
            sql += " oid_Relatorio_Gerencial_Grupo = oid_Relatorio_Gerencial_Grupo, ";
            if (doValida(ed.getCd_Relatorio_Gerencial_Grupo())) 
                sql += " cd_Relatorio_Gerencial_Grupo = '" + ed.getCd_Relatorio_Gerencial_Grupo() +"', ";
            if (doValida(ed.getNm_Relatorio_Gerencial_Grupo())) 
                sql += " nm_Relatorio_Gerencial_Grupo = '" + ed.getNm_Relatorio_Gerencial_Grupo()+"', ";
            
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";

            sql += " WHERE oid_Relatorio_Gerencial_Grupo = " + ed.getOid_Relatorio_Gerencial_Grupo();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Relatorio_Gerencial_GrupoED ed)");
        }
    }

    public void deleta(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE " +
            	  " FROM " +
            	  " Relatorios_Gerenciais_Grupos " +
            	  " WHERE " +
            	  " oid_Relatorio_Gerencial_Grupo = " + ed.getOid_Relatorio_Gerencial_Grupo();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Relatorio_Gerencial_GrupoED ed)");
        }
    }
    
    public ArrayList lista(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM " +
            	  " Relatorios_Gerenciais_Grupos " +
            	  " WHERE 1=1 ";
            	  
            sql += " AND Relatorios_Gerenciais_Grupos.oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial();
            if (doValida(ed.getCd_Relatorio_Gerencial_Grupo()))
                sql += " AND Relatorios_Gerenciais_Grupos.cd_Relatorio_Gerencial_Grupo = '" + ed.getCd_Relatorio_Gerencial_Grupo() + "'";
            if (doValida(ed.getNm_Relatorio_Gerencial_Grupo()))
                sql += " AND Relatorios_Gerenciais_Grupos.nm_Relatorio_Gerencial_Grupo LIKE '" + ed.getNm_Relatorio_Gerencial_Grupo() + "%'";
            sql += " ORDER BY Relatorios_Gerenciais_Grupos.cd_Relatorio_Gerencial_Grupo";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Relatorio_Gerencial_GrupoED edVolta = new Relatorio_Gerencial_GrupoED();
          
                edVolta.setOid_Relatorio_Gerencial_Grupo(res.getInt("oid_Relatorio_Gerencial_Grupo"));
                edVolta.setCd_Relatorio_Gerencial_Grupo(res.getString("cd_Relatorio_Gerencial_Grupo"));
                edVolta.setNm_Relatorio_Gerencial_Grupo(res.getString("nm_Relatorio_Gerencial_Grupo"));
                edVolta.setOid_Relatorio_Gerencial(res.getInt("oid_Relatorio_Gerencial"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Relatorio_Gerencial_GrupoED ed)");
        }
    }

    public Relatorio_Gerencial_GrupoED getByRecord(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

	    String sql = null;

	    Relatorio_Gerencial_GrupoED edQBR = new Relatorio_Gerencial_GrupoED();

	    try{

	  	  sql = "SELECT " +
		    	"oid_Relatorio_Gerencial_Grupo, " +
				"cd_Relatorio_Gerencial_Grupo, " +
				"nm_Relatorio_Gerencial_Grupo," +
				"oid_Relatorio_Gerencial " +
	            "FROM " +
				"Relatorios_Gerenciais_Grupos " +
				"WHERE " +
				" 1=1 "; 
	  	  		if (ed.getOid_Relatorio_Gerencial_Grupo()>0){  
	  	  			sql +=" and oid_Relatorio_Gerencial_Grupo = " + ed.getOid_Relatorio_Gerencial_Grupo(); 
  	  			} else {
  	  				if (doValida(ed.getCd_Relatorio_Gerencial_Grupo())) {
  	  					sql += " and cd_Relatorio_Gerencial_Grupo = '" + ed.getCd_Relatorio_Gerencial_Grupo() + "' " +
   	  					" and oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial(); 
  	  				}
	    		};

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      while (res.next()){
	    	  edQBR.setOid_Relatorio_Gerencial_Grupo(res.getInt("oid_Relatorio_Gerencial_Grupo"));
	    	  edQBR.setCd_Relatorio_Gerencial_Grupo(res.getString("cd_Relatorio_Gerencial_Grupo"));
	    	  edQBR.setNm_Relatorio_Gerencial_Grupo(res.getString("nm_Relatorio_Gerencial_Grupo"));
	    	  edQBR.setOid_Relatorio_Gerencial(res.getInt("oid_Relatorio_Gerencial"));
	      }
	      
	    }
	      catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(SistemaED ed)");
	      }

	    return edQBR;
    }
}