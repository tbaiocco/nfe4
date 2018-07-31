package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Dre_ItemED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Itens do DRE
 * @serialData 02/2006
 */
public class DRE_ItemBD extends BancoUtil {

    private ExecutaSQL executasql;

    public DRE_ItemBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Dre_ItemED inclui(Dre_ItemED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Dre_Item(getAutoIncremento("oid_Dre_Item", "Dre_Itens"));
            ed.setNr_Grau(ed.calcGrau(ed.getCd_Estrutural()));
            sql = " INSERT INTO DRE_Itens ("  +
            	  "	oid_Dre_Item," 			  +
            	  "	oid_Dre," 				  +
            	  "	cd_Estrutural,"			  +
                  " nm_Item," 				  +
            	  "	dm_Tipo_Conta," 		  +
            	  "	dm_Acao," 				  +
            	  "	nr_Grau" 				  +
            	  ") " 						  +
            	  " VALUES (" 				  +
            	  ed.getOid_Dre_Item() 	+ "," + 
            	  ed.getOid_Dre() 		+ ",'" + 
            	  ed.getCd_Estrutural() + "','" +
            	  ed.getNm_Item() 		+ "','" +
            	  ed.getDm_Tipo_Conta() + "','" +
            	  ed.getDm_Acao() 		+ "'," +
            	  ed.getNr_Grau() 		+ " " +
            	  ")"						  ;
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Dre_ItemED ed)");
        }
    }

    public void altera(Dre_ItemED ed) throws Excecoes {
    	ed.setNr_Grau(ed.calcGrau(ed.getCd_Estrutural()));
        String sql = null;
        try {
            
            sql =  " UPDATE DRE_Itens SET ";
            sql += " oid_DRE_Item = oid_DRE_Item " +
                   " ,nr_Grau = " + ed.getNr_Grau();
            if (ed.getOid_Dre() > 0) 
                sql += " ,oid_Dre = " + ed.getOid_Dre();
            if (doValida(ed.getCd_Estrutural())) 
                sql += " ,cd_Estrutural = '" + ed.getCd_Estrutural() + "' ";
            if (doValida(ed.getNm_Item())) 
                sql += " ,nm_Item = '" + ed.getNm_Item()+ "' ";
            if (doValida(ed.getDm_Tipo_Conta())) 
                sql += " ,dm_Tipo_Conta = '" + ed.getDm_Tipo_Conta()+ "' ";
            if (doValida(ed.getDm_Acao())) 
                sql += " ,dm_Acao = '" + ed.getDm_Acao()+ "' ";
            
            sql += " ,DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";

            sql += " WHERE oid_DRE_Item = " + ed.getOid_Dre_Item();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Dre_ItemED ed)");
        }
    }

    public void deleta(Dre_ItemED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM DRE_Itens " +
            	  " WHERE oid_DRE_Item = " + ed.getOid_Dre_Item();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Dre_ItemED ed)");
        }
    }
    
    public ArrayList lista(Dre_ItemED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * "       +
            	  " FROM DRE_Itens " +
            	  " WHERE 1=1"       ;
            	  
            if (ed.getOid_Dre_Item() > 0)
                sql += " and Dre_Itens.oid_dre_Item = " + ed.getOid_Dre_Item();
            else {
	            if (ed.getOid_Dre() > 0)
	                sql += " and Dre_Itens.oid_DRE = " + ed.getOid_Dre();
	            if (doValida(ed.getCd_Estrutural()))
	                sql += " and Dre_Itens.Cd_Estrutural like '" + ed.getCd_Estrutural() + "%' ";
            }
            sql += " ORDER BY Dre_Itens.cd_Estrutural";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Dre_ItemED edVolta = new Dre_ItemED();
          
                edVolta.setOid_Dre_Item(res.getInt("oid_Dre_Item"));
                edVolta.setOid_Dre(res.getInt("oid_Dre"));
                edVolta.setCd_Estrutural(res.getString("cd_Estrutural"));
                edVolta.setNm_Item(res.getString("nm_Item"));
                edVolta.setDm_Tipo_Conta(res.getString("dm_Tipo_Conta"));
                edVolta.setDm_Acao(res.getString("dm_Acao"));
                edVolta.setNr_Grau(new Integer(res.getInt("nr_Grau")));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }

    public Dre_ItemED getByRecord(Dre_ItemED ed) throws Excecoes {
    	
        String sql = null;
        
        Dre_ItemED edQBR = new Dre_ItemED();
        
        try {
        	
            sql = " SELECT * "       +
            	  " FROM DRE_Itens " +
            	  " WHERE 1=1"       ;
            if (ed.getOid_Dre_Item() > 0)
                sql += " and Dre_Itens.oid_dre_Item = " + ed.getOid_Dre_Item();
            else {
	            if (doValida(ed.getCd_Estrutural()))
	                sql += " and Dre_Itens.Cd_Estrutural = '" + ed.getCd_Estrutural() + "' ";
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);
            
            while (res.next())
            {
                edQBR.setOid_Dre_Item(res.getInt("oid_Dre_Item"))     ;
                edQBR.setOid_Dre(res.getInt("oid_Dre"))				  ;
                edQBR.setCd_Estrutural(res.getString("cd_Estrutural"));
                edQBR.setNm_Item(res.getString("nm_Item"))			  ;
                edQBR.setDm_Tipo_Conta(res.getString("dm_Tipo_Conta"));
                edQBR.setDm_Acao(res.getString("dm_Acao"))			  ;
                edQBR.setNr_Grau(new Integer(res.getInt("nr_Grau")))  ;
            }
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
        
        return edQBR;
    }
}
    