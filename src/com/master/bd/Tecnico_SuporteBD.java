package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Tecnico_SuporteED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Jeanine e Vinícius
 * @serial Técnico_Suporte
 * @serialData 21/05/2008
 */

public class Tecnico_SuporteBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Tecnico_SuporteBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Tecnico_SuporteED inclui(Tecnico_SuporteED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Tecnico(getAutoIncremento("oid_Tecnico", "Tecnicos"));
            if (ed.getOid_Tecnico()<100){ ed.setOid_Tecnico(100); } // reserva registros para os fixos
            sql = " INSERT INTO Tecnicos (" +
            	  "	oid_tecnico," +
            	  "	cd_tecnico," +
            	  "	dm_tecnico," +
            	  "	nm_tecnico)" +
            	  " VALUES (" +
            	  ed.getOid_Tecnico() +
            	  ",'" + ed.getCd_Tecnico() + "'" +
            	  ",'" + ed.getDm_Tecnico() + "'" +
            	  ",'" + ed.getNm_Tecnico() + "')";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Tecnico_SuporteED ed)");
        }
    }

    public void altera(Tecnico_SuporteED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Tecnicos SET ";
            sql += " 	oid_Tecnico = oid_Tecnico, ";
            if (doValida(ed.getCd_Tecnico())) 
                sql += " 	cd_Tecnico = '" + ed.getCd_Tecnico() +"', ";
            if (doValida(ed.getNm_Tecnico())) 
                sql += "    nm_Tecnico = '" + ed.getNm_Tecnico()+"', ";
            if (doValida(ed.getDm_Tecnico())) 
                sql += "    dm_Tecnico = '" + ed.getDm_Tecnico()+"', ";
            
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";

            sql += " WHERE oid_Tecnico = " + ed.getOid_Tecnico();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Tecnico_SuporteED ed)");
        }
    }

    public void deleta(Tecnico_SuporteED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Tecnicos " +
            	  " WHERE oid_Tecnico = " + ed.getOid_Tecnico();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Tecnico_SuporteED ed)");
        }
    }
    
    public ArrayList lista(Tecnico_SuporteED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Tecnicos " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_Tecnico() > 0)
                sql += "   AND Tecnicos.oid_Tecnico = "+ed.getOid_Tecnico();
            else {
	            if (doValida(ed.getCd_Tecnico()))
	                sql += "   AND Tecnicos.cd_Tecnico = '"+ed.getCd_Tecnico()+"'";
	            if (doValida(ed.getDm_Tecnico()))
	                sql += "   AND Tecnicos.dm_Tecnico = '"+ed.getDm_Tecnico()+"'";
	            if (doValida(ed.getNm_Tecnico()))
	                sql += "   AND Tecnicos.nm_Tecnico LIKE '"+ed.getNm_Tecnico()+"%'";
            }
            sql += " ORDER BY Tecnicos.nm_Tecnico";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
            	Tecnico_SuporteED edVolta = new Tecnico_SuporteED();
          
                edVolta.setOid_Tecnico(res.getInt("oid_Tecnico"));
                edVolta.setCd_Tecnico(res.getString("cd_Tecnico"));
                edVolta.setNm_Tecnico(res.getString("nm_Tecnico"));
                edVolta.setDm_Tecnico(res.getString("dm_Tecnico"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Tecnico_SuporteED ed)");
        }
    }

    public Tecnico_SuporteED getByRecord(Tecnico_SuporteED ed) throws Excecoes {

	    String sql = null;
	    ResultSet res = null;
	    Tecnico_SuporteED edQBR = new Tecnico_SuporteED();

	    try{

	  	  sql = "select " +
		    	"oid_tecnico, " +
				"cd_tecnico, " +
				"nm_tecnico, " +
				"dm_tecnico " +
	            "from " +
				"tecnicos " +
				"where "; 
	  	  		if (ed.getOid_Tecnico()>0)  
				{  sql +="oid_tecnico = '" + ed.getOid_Tecnico(); }
				else
				{  sql +="cd_tecnico = '" + ed.getCd_Tecnico(); }
				sql +="'";

	      res = this.executasql.executarConsulta(sql);
	      while (res.next()){

	      	edQBR.setOid_Tecnico(res.getLong("oid_Tecnico"));

	        edQBR.setCd_Tecnico(res.getString("cd_Tecnico"));
	        edQBR.setNm_Tecnico(res.getString("nm_Tecnico"));
	        edQBR.setDm_Tecnico(res.getString("dm_Tecnico"));
	      }
	    }
	      catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(SistemaED ed)");
	      }
	    return edQBR;
    }
}