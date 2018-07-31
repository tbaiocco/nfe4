package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Grupo_Nota_FiscalED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Grupos Notas Fiscais
 * @serialData 17/06/2005
 */
public class Grupo_Nota_FiscalBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Grupo_Nota_FiscalBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Grupo_Nota_FiscalED inclui(Grupo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Grupo_Nota_Fiscal(getAutoIncremento("oid_Grupo_Nota_Fiscal", "Grupos_Notas_Fiscais"));  
            
            sql = " INSERT INTO Grupos_Notas_Fiscais (" +
            	  "		 oid_Grupo_Nota_Fiscal" +
            	  "		,CD_Grupo_Nota_Fiscal" +
            	  "		,NM_Grupo_Nota_Fiscal" +
            	  "		,NM_Modelos) " +
            	  " VALUES (" +
            	  	ed.getOid_Grupo_Nota_Fiscal() +
            	  	",'" + ed.getCD_Grupo_Nota_Fiscal() + "'" +
            	  	",'" + ed.getNM_Grupo_Nota_Fiscal() + "'" +
            	  	",'" + ed.getNM_Modelos() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void altera(Grupo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Grupos_Notas_Fiscais SET ";
            sql += " 	CD_Grupo_Nota_Fiscal = '" + ed.getCD_Grupo_Nota_Fiscal() + "'" +
            	   "   ,NM_Grupo_Nota_Fiscal = '"+ed.getNM_Grupo_Nota_Fiscal()+"'"+
            	   "   ,NM_Modelos = '"+ed.getNM_Modelos()+"'";
            sql += " WHERE oid_Grupo_Nota_Fiscal = " + ed.getOid_Grupo_Nota_Fiscal();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            	"altera()");
        }
    }

    public void deleta(Grupo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Grupos_Notas_Fiscais " +
            	  " WHERE oid_Grupo_Nota_Fiscal = " + ed.getOid_Grupo_Nota_Fiscal();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Grupo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Grupos_Notas_Fiscais " +
            	  " WHERE 1 = 1 ";
            if (ed.getOid_Grupo_Nota_Fiscal() > 0)
                sql += " AND oid_Grupo_Nota_Fiscal = "+ed.getOid_Grupo_Nota_Fiscal();
            if (doValida(ed.getCD_Grupo_Nota_Fiscal()))
                sql += " AND CD_Grupo_Nota_Fiscal = '"+ed.getCD_Grupo_Nota_Fiscal()+"'";
            if (doValida(ed.getNM_Grupo_Nota_Fiscal()))
                sql += " AND NM_Grupo_Nota_Fiscal LIKE '"+ed.getNM_Grupo_Nota_Fiscal()+"%'";
            
            sql +=" ORDER BY CD_Grupo_Nota_Fiscal";
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Grupo_Nota_FiscalED edVolta = new Grupo_Nota_FiscalED();
                edVolta.setOid_Grupo_Nota_Fiscal(res.getInt("oid_Grupo_Nota_Fiscal"));
                edVolta.setCD_Grupo_Nota_Fiscal(res.getString("CD_Grupo_Nota_Fiscal"));
                edVolta.setNM_Grupo_Nota_Fiscal(res.getString("NM_Grupo_Nota_Fiscal"));
                edVolta.setNM_Modelos(res.getString("NM_Modelos"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
        return list;
    }
        
    public Grupo_Nota_FiscalED getByRecord(Grupo_Nota_FiscalED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            if (iterator.hasNext())
                return (Grupo_Nota_FiscalED) iterator.next();
            else return new Grupo_Nota_FiscalED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }
}