package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.DreED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis Steigleder
 * @serial Demonstrativo de Resultados
 * @serialData 02/2006
 */
public class DreBD extends BancoUtil {

    private ExecutaSQL executasql;

    public DreBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public DreED inclui(DreED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Dre(getAutoIncremento("oid_Dre", "DRE"));
            
            sql = " INSERT INTO Dre (" +
            	  " oid_Dre, " +
            	  "	cd_Dre, " +
            	  "	nm_Dre  " +
            	  " ) VALUES (" +
            	  ed.getOid_Dre() + ",'" +
            	  ed.getCd_Dre() + "','" +
            	  ed.getNm_Dre() + "'" +
            	  ")";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(DreED ed)");
        }
    }

    public void altera(DreED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Dre SET ";
            sql += " oid_DRE = oid_DRE, ";
            if (doValida(ed.getCd_Dre())) 
                sql += "cd_Dre = '" + ed.getCd_Dre() +"', ";
            if (doValida(ed.getNm_Dre())) 
                sql += "nm_Dre = '" + ed.getNm_Dre()+"', ";
            
            sql += " dt_Stamp = '" + ed.getDt_stamp() + "', ";
            sql += " usuario_Stamp = '" + ed.getUsuario_Stamp() + "', ";
            sql += " dm_Stamp = '" + ed.getDm_Stamp() + "' ";

            sql += " WHERE oid_Dre = " + ed.getOid_Dre();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(DreED ed)");
        }
    }

    public void deleta(DreED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM DRE " +
            	  " WHERE oid_DRE = " + ed.getOid_Dre();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(DreED ed)");
        }
    }
    
    public ArrayList lista(DreED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Dre " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_Dre() > 0)
                sql += "  and Dre.oid_Dre = " + ed.getOid_Dre();
            else {
	            if (doValida(ed.getCd_Dre()))
	                sql += " and dre.cd_Dre = '"+ed.getCd_Dre()+"' ";
	            if (doValida(ed.getNm_Dre()))
	                sql += " and dre.nm_dre = '"+ed.getNm_Dre()+"'";
            }
            sql += " ORDER BY dre.cd_Dre";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                DreED edVolta = new DreED();
          
                edVolta.setOid_Dre(res.getInt("oid_Dre"));
                edVolta.setCd_Dre(res.getString("cd_Dre"));
                edVolta.setNm_Dre(res.getString("nm_Dre"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Dre ed)");
        }
    }

    public DreED getByRecord(DreED ed) throws Excecoes {

	    String sql = null;

	    DreED edQBR = new DreED();

	    try{

	  	  sql = "SELECT * " +
	            "FROM " +
				"Dre " +
				"WHERE "; 
	  	  		if (ed.getOid_Dre()>0)  
				{  sql +="oid_Dre = '" + ed.getOid_Dre(); }
				else
				{  sql +="cd_Dre = '" + ed.getCd_Dre(); }
				sql +="'";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      while (res.next()){
	      	edQBR.setOid_Dre(res.getLong("oid_Dre"));
	        edQBR.setCd_Dre(res.getString("cd_Dre"));
	        edQBR.setNm_Dre(res.getString("nm_Dre"));
	      }
	    }
	      catch(Exception exc){
	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	        		"getByRecord(DreED ed)");
	      }

	    return edQBR;
     }
}