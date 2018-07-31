package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.DRGED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Demonstrativos Resumos Gerenciais
 * @serialData 14/10/2005
 */
public class DRGBD extends BancoUtil {

    private ExecutaSQL executasql;

    public DRGBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public DRGED inclui(DRGED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_DRG(getAutoIncremento("oid_DRG", "DRG"));
            
            sql = " INSERT INTO DRG (" +
            	  "		 oid_DRG" +
            	  "		,CD_DRG" +
            	  "		,NM_DRG" +
                  "     ,DM_Agrupamento) " +
            	  " VALUES (" +
            	  	ed.getOid_DRG() +
            	  	",'" + ed.getCD_DRG() + "'" +
            	  	",'" + ed.getNM_DRG() + "'" +
                    ",'" + ed.getDM_Agrupamento() + "')";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(DRGED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE DRG SET ";
            sql += " 	 oid_DRG = oid_DRG ";
            if (doValida(ed.getCD_DRG())) 
                sql += " 	,CD_DRG = '" + ed.getCD_DRG() +"'";
            if (doValida(ed.getNM_DRG())) 
                sql += "    ,NM_DRG = '" + ed.getNM_DRG()+"'";
            if (doValida(ed.getDM_Agrupamento())) 
                sql += "    ,DM_Agrupamento = '" + ed.getDM_Agrupamento() +"'";
            sql += " WHERE oid_DRG = " + ed.getOid_DRG();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(DRGED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM DRG " +
            	  " WHERE oid_DRG = " + ed.getOid_DRG();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(DRGED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM DRG " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_DRG() > 0)
                sql += "   AND DRG.oid_DRG = "+ed.getOid_DRG();
            else {
	            if (doValida(ed.getCD_DRG()))
	                sql += "   AND DRG.CD_DRG = '"+ed.getCD_DRG()+"'";
	            if (doValida(ed.getNM_DRG()))
	                sql += "   AND DRG.NM_DRG = '"+ed.getNM_DRG()+"'";
	            if (doValida(ed.getDM_Agrupamento()))
	                sql += "   AND DRG.DM_Agrupamento = '"+ed.getDM_Agrupamento()+"'";
            }
            sql += " ORDER BY DRG.CD_DRG";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                DRGED edVolta = new DRGED();
          
                edVolta.setOid_DRG(res.getInt("oid_DRG"));
                edVolta.setCD_DRG(res.getString("CD_DRG"));
                edVolta.setNM_DRG(res.getString("NM_DRG"));
                edVolta.setDM_Agrupamento(res.getString("DM_Agrupamento"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }

    public DRGED getByRecord(DRGED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (DRGED) iterator.next();
        } else return new DRGED();
    }
}