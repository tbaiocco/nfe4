package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Relatorio_GerencialED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 14/12/2005
 */

public class Relatorio_GerencialBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Relatorio_GerencialBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Relatorio_GerencialED inclui(Relatorio_GerencialED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Relatorio_Gerencial(getAutoIncremento("oid_Relatorio_Gerencial", "Relatorios_Gerenciais"));
            sql = " INSERT INTO Relatorios_Gerenciais (" +
            	  "	oid_Relatorio_Gerencial," +
            	  "	cd_Relatorio_Gerencial," +
            	  "	nm_Relatorio_Gerencial)" +
            	  " VALUES (" +
            	  ed.getOid_Relatorio_Gerencial() +
            	  ",'" + ed.getCd_Relatorio_Gerencial() + "'" +
            	  ",'" + ed.getNm_Relatorio_Gerencial() + "')";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Relatorios_GerenciaisED ed)");
        }
    }

    public void altera(Relatorio_GerencialED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Relatorios_Gerenciais SET ";
            sql += " oid_Relatorio_Gerencial = oid_Relatorio_Gerencial, ";
            if (doValida(ed.getCd_Relatorio_Gerencial())) 
                sql += " 	cd_Relatorio_Gerencial = '" + ed.getCd_Relatorio_Gerencial() +"', ";
            if (doValida(ed.getNm_Relatorio_Gerencial())) 
                sql += "    nm_Relatorio_Gerencial = '" + ed.getNm_Relatorio_Gerencial()+"', ";
            
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";

            sql += " WHERE oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Relatorios_GerenciaisED ed)");
        }
    }

    public void deleta(Relatorio_GerencialED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Relatorios_Gerenciais " +
            	  " WHERE oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Relatorios_GerenciaisED ed)");
        }
    }
    
    public ArrayList lista(Relatorio_GerencialED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Relatorios_Gerenciais " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_Relatorio_Gerencial() > 0)
                sql += "   AND Relatorios_Gerenciais.oid_Relatorio_Gerencial = "+ed.getOid_Relatorio_Gerencial();
            else {
	            if (doValida(ed.getCd_Relatorio_Gerencial()))
	                sql += "   AND Relatorios_Gerenciais.cd_Relatorio_Gerencial = '"+ed.getCd_Relatorio_Gerencial()+"'";
	            if (doValida(ed.getNm_Relatorio_Gerencial()))
	                sql += "   AND Relatorios_Gerenciais.nm_Relatorio_Gerencial LIKE '"+ed.getNm_Relatorio_Gerencial()+"%'";
            }
            sql += " ORDER BY Relatorios_Gerenciais.cd_Relatorio_Gerencial";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Relatorio_GerencialED edVolta = new Relatorio_GerencialED();
          
                edVolta.setOid_Relatorio_Gerencial(res.getInt("oid_Relatorio_Gerencial"));
                edVolta.setCd_Relatorio_Gerencial(res.getString("cd_Relatorio_Gerencial"));
                edVolta.setNm_Relatorio_Gerencial(res.getString("nm_Relatorio_Gerencial"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Relatorios_GerenciaisED ed)");
        }
    }

    public Relatorio_GerencialED getByRecord(Relatorio_GerencialED ed) throws Excecoes {

	    String sql = null;

	    Relatorio_GerencialED edQBR = new Relatorio_GerencialED();

	    try{

	  	  sql = "SELECT " +
		    	"oid_Relatorio_Gerencial, " +
				"cd_Relatorio_Gerencial, " +
				"nm_Relatorio_Gerencial " +
	            "FROM " +
				"Relatorios_Gerenciais " +
				"WHERE "; 
	  	  		if (ed.getOid_Relatorio_Gerencial()>0)  
				{  sql +="oid_Relatorio_Gerencial = '" + ed.getOid_Relatorio_Gerencial(); }
				else
				{  sql +="cd_Relatorio_Gerencial = '" + ed.getCd_Relatorio_Gerencial(); }
				sql +="'";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      while (res.next()){
	      	edQBR.setOid_Relatorio_Gerencial(res.getLong("oid_Relatorio_Gerencial"));
	        edQBR.setCd_Relatorio_Gerencial(res.getString("cd_Relatorio_Gerencial"));
	        edQBR.setNm_Relatorio_Gerencial(res.getString("nm_Relatorio_Gerencial"));
	      }
	    }
	      catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(SistemaED ed)");
	      }

	    return edQBR;
    }
}