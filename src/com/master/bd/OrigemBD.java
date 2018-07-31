package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.OrigemED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Origens
 * @serialData 14/10/2005
 */

public class OrigemBD extends BancoUtil {

    private ExecutaSQL executasql;

    public OrigemBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public OrigemED inclui(OrigemED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOID_Origem(getAutoIncremento("oid_Origem", "Origens"));
            if (ed.getOID_Origem()<100){ ed.setOID_Origem(100); } // reserva registros para os fixos
            sql = " INSERT INTO Origens (" +
            	  "	OID_Origem," +
            	  "	CD_Origem," +
            	  "	NM_Origem)" +
            	  " VALUES (" +
            	  ed.getOID_Origem() +
            	  ",'" + ed.getCD_Origem() + "'" +
            	  ",'" + ed.getNM_Origem() + "')";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(OrigemED ed)");
        }
    }

    public void altera(OrigemED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Origens SET ";
            sql += " 	 oid_Origem = oid_Origem, ";
            if (doValida(ed.getCD_Origem())) 
                sql += " 	CD_Origem = '" + ed.getCD_Origem() +"', ";
            if (doValida(ed.getNM_Origem())) 
                sql += "    NM_Origem = '" + ed.getNM_Origem()+"', ";
            
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";

            sql += " WHERE oid_Origem = " + ed.getOID_Origem();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(OrigemED ed)");
        }
    }

    public void deleta(OrigemED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Origens " +
            	  " WHERE oid_Origem = " + ed.getOID_Origem();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(OrigemED ed)");
        }
    }
    
    public ArrayList lista(OrigemED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Origens " +
            	  " WHERE 1=1";
            	  
            if (ed.getOID_Origem() > 0)
                sql += "   AND Origens.oid_Origem = "+ed.getOID_Origem();
            else {
	            if (doValida(ed.getCD_Origem()))
	                sql += "   AND Origens.CD_Origem = '"+ed.getCD_Origem()+"'";
	            if (doValida(ed.getNM_Origem()))
	                sql += "   AND Origens.NM_Origem LIKE '"+ed.getNM_Origem()+"%'";
            }
            sql += " ORDER BY Origens.CD_Origem";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                OrigemED edVolta = new OrigemED();
          
                edVolta.setOID_Origem(res.getInt("oid_Origem"));
                edVolta.setCD_Origem(res.getString("CD_Origem"));
                edVolta.setNM_Origem(res.getString("NM_Origem"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(OrigemED ed)");
        }
    }

    public OrigemED getByRecord(OrigemED ed) throws Excecoes {

	    String sql = null;

	    OrigemED edQBR = new OrigemED();

	    try{

	  	  sql = "select " +
		    	"oid_origem, " +
				"cd_origem, " +
				"nm_origem " +
	            "from " +
				"origens " +
				"where "; 
	  	  		if (ed.getOID_Origem()>0)  
				{  sql +="oid_origem = '" + ed.getOID_Origem(); }
				else
				{  sql +="cd_origem = '" + ed.getCD_Origem(); }
				sql +="'";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      while (res.next()){

	      	edQBR.setOID_Origem(res.getLong("oid_origem"));

	        edQBR.setCD_Origem(res.getString("cd_origem"));
	        edQBR.setNM_Origem(res.getString("nm_origem"));
	      }
	    }
	      catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(SistemaED ed)");
	      }

	    return edQBR;
    }
}