package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.DRG_ItemED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Itens do D.R.G.
 * @serialData 14/10/2005
 */
public class DRG_ItemBD extends BancoUtil {

    private ExecutaSQL executasql;

    public DRG_ItemBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public DRG_ItemED inclui(DRG_ItemED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_DRG_Item(getAutoIncremento("oid_DRG_Item", "DRG_Itens"));
            
            sql = " INSERT INTO DRG_Itens (" +
            	  "		 oid_DRG_Item" +
            	  "		,oid_DRG" +
            	  "		,NR_Sequendia" +
                  "     ,NM_DRG_Item" +
            	  "		,CD_Estrutural) " +
            	  " VALUES (" +
            	  	ed.getOid_DRG_Item() +
            	  	"," + ed.getOid_DRG() +
            	  	"," + ed.getNR_Sequendia() +
                    ",'" + ed.getNM_DRG_Item() +"'"+
            	  	",'" + ed.getCD_Estrutural() +"')";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(DRG_ItemED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE DRG_Itens SET ";
            sql += " 	 oid_DRG_Item = oid_DRG_Item ";
            if (ed.getOid_DRG() > 0) 
                sql += " 	,oid_DRG = " + ed.getOid_DRG();
            if (ed.getNR_Sequendia() > 0) 
                sql += "    ,NR_Sequendia = " + ed.getNR_Sequendia();
            if (doValida(ed.getNM_DRG_Item())) 
                sql += "    ,NM_DRG_Item = '" + ed.getNM_DRG_Item()+"'";
            if (doValida(ed.getCD_Estrutural())) 
                sql += "    ,CD_Estrutural = '" + ed.getCD_Estrutural()+"'";
            sql += " WHERE oid_DRG_Item = " + ed.getOid_DRG_Item();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(DRG_ItemED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM DRG_Itens " +
            	  " WHERE oid_DRG_Item = " + ed.getOid_DRG_Item();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(DRG_ItemED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM DRG_Itens " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_DRG_Item() > 0)
                sql += "   AND DRG_Itens.oid_DRG_Item = "+ed.getOid_DRG_Item();
            else {
	            if (ed.getOid_DRG() > 0)
	                sql += "   AND DRG_Itens.oid_DRG = "+ed.getOid_DRG();
	            if (ed.getNR_Sequendia() > 0)
	                sql += "   AND DRG_Itens.NR_Sequendia = "+ed.getNR_Sequendia();
	            if (doValida(ed.getNM_DRG_Item()))
	                sql += "   AND DRG_Itens.NM_DRG_Item = '"+ed.getNM_DRG_Item()+"'";
	            if (doValida(ed.getCD_Estrutural()))
	                sql += "   AND DRG_Itens.CD_Estrutural = '"+ed.getCD_Estrutural()+"'";
            }
            sql += " ORDER BY DRG_Itens.oid_DRG";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                DRG_ItemED edVolta = new DRG_ItemED();
          
                edVolta.setOid_DRG_Item(res.getInt("oid_DRG_Item"));
                edVolta.setOid_DRG(res.getInt("oid_DRG"));
                edVolta.setNR_Sequendia(res.getInt("NR_Sequendia"));
                edVolta.setNM_DRG_Item(res.getString("NM_DRG_Item"));
                edVolta.setCD_Estrutural(res.getString("CD_Estrutural"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }

    public DRG_ItemED getByRecord(DRG_ItemED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (DRG_ItemED) iterator.next();
        } else return new DRG_ItemED();
    }
}