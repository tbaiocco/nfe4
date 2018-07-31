package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Dre_Item_FormacaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis Steigleder	
 * @serial Formações dos Itens do DRE
 * @serialData 02/2006
 */
public class Dre_Item_FormacaoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Dre_Item_FormacaoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Dre_Item_FormacaoED inclui(Dre_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Dre_Item_Formacao(getAutoIncremento("oid_Dre_Item_Formacao", "Dre_Itens_Formacao"));
            
            sql = " INSERT INTO Dre_Itens_Formacao (" +
        	  	  " oid_Dre_Item_Formacao, " 		  +
            	  "	oid_Dre_Item, " 				  +
            	  "	oid_Conta " 				      +
            	  " ) " 							  + 
            	  " VALUES ("                         +
            	  ed.getOid_Dre_Item_Formacao() + "," +
            	  ed.getOid_Dre_Item() 			+ "," +
            	  ed.getOid_Conta() 			+ " " + 
            	  ")"                                 ;
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Dre_Item_FormacaoED ed)");
        }
    }

    public void altera(Dre_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Dre_Itens_Formacao SET ";
            sql += " oid_Dre_Item_Formacao = oid_Dre_Item_Formacao ";
            if (ed.getOid_Dre_Item() > 0) 
                sql += " ,oid_Dre_Item = " + ed.getOid_Dre_Item();
            if (ed.getOid_Conta() > 0) 
                sql += " ,oid_Conta = " + ed.getOid_Conta();
            sql += " WHERE oid_Dre_Item_Formacao = " + ed.getOid_Dre_Item_Formacao();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Dre_Item_FormacaoED ed)");
        }
    }

    public void deleta(Dre_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Dre_Itens_Formacao " +
            	  " WHERE oid_Dre_Item_Formacao = " + ed.getOid_Dre_Item_Formacao();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Dre_Item_FormacaoED ed)");
        }
    }
    
    public ArrayList lista(Dre_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {
            sql = " SELECT  "                  +
            	  " oid_Dre_Item_Formacao, "   +
            	  " oid_Dre_Item, "   		   +
            	  " Dre_Itens_Formacao.oid_Conta as oid_Conta, "   +
            	  " cd_Estrutural, "   		   +
            	  " cd_Conta, "   			   +
            	  " nm_Conta "   			   +
            	  " FROM "                     +
            	  " Dre_Itens_Formacao , "     +
            	  " Contas "                   +
            	  " WHERE 1=1 and "            + 
            	  " Dre_Itens_Formacao.oid_conta = Contas.oid_conta " ; 
            if (ed.getOid_Dre_Item_Formacao() > 0)
                sql += " AND oid_DRG_Item_Formacao = " + ed.getOid_Dre_Item_Formacao();
            else {
	            if (ed.getOid_Dre_Item() > 0)
	                sql += "  AND oid_Dre_Item = " + ed.getOid_Dre_Item();
	            if (ed.getOid_Conta() > 0)
	                sql += "   AND oid_Conta = "+ed.getOid_Conta();
            }
            sql += " ORDER BY cd_Estrutural";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Dre_Item_FormacaoED edVolta = new Dre_Item_FormacaoED();
          
                edVolta.setOid_Dre_Item_Formacao(res.getInt("oid_Dre_Item_Formacao"));
                edVolta.setOid_Dre_Item(res.getInt("oid_Dre_Item"));
                edVolta.setOid_Conta(res.getInt("oid_Conta"));
                edVolta.setCd_Estrutural(res.getString("cd_Estrutural"));
                edVolta.setCd_Conta(res.getString("cd_Conta"));
                edVolta.setNm_Conta(res.getString("nm_Conta"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Dre_Item_FormacaoED ed)");
        }
    }

    public Dre_Item_FormacaoED getByRecord(Dre_Item_FormacaoED ed) throws Excecoes {
    	
        String sql = null;
        
        Dre_Item_FormacaoED edQBR = new Dre_Item_FormacaoED();
        
        try {
        	
            sql = " SELECT  "                  +
      	  		  " oid_Dre_Item_Formacao, "   +
      	  		  " oid_Dre_Item, "   		   +
      	  		  " Dre_Itens_Formacao.oid_Conta as oid_Conta, "   +
      	  		  " cd_Estrutural, "   		   +
      	  		  " cd_Conta, "   			   +
      	  		  " nm_Conta "   			   +
      	  		  " FROM "                     +
      	  		  " Dre_Itens_Formacao , "     +
      	  		  " Contas "                   +
      	  		  " WHERE 1=1 and "            + 
      	  		  " Dre_Itens_Formacao.oid_conta = Contas.oid_conta " ; 
            if (ed.getOid_Dre_Item_Formacao() > 0)
                sql += " and Dre_Itens_Formacao.oid_dre_Item_Formacao = " + ed.getOid_Dre_Item_Formacao();
            else {
	            if (ed.getOid_Conta() > 0) {
	            	sql += " and Dre_Itens_Formacao.oid_Dre_Item = " + ed.getOid_Dre_Item() + " " +
	                " and Dre_Itens_Formacao.oid_Conta = " + ed.getOid_Conta() ;
	            } else { 
	            	if (ed.getOid_Dre_Item() > 0) {
		            	sql += " and Dre_Itens_Formacao.oid_Dre_Item = " + ed.getOid_Dre_Item() + " "; 
	            	}
	            }
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);
            
            while (res.next())
            {
                edQBR.setOid_Dre_Item_Formacao(res.getInt("oid_Dre_Item_Formacao"));
                edQBR.setOid_Dre_Item(res.getInt("oid_Dre_Item"));
                edQBR.setOid_Conta(res.getInt("oid_Conta"));
                edQBR.setCd_Estrutural(res.getString("cd_Estrutural"));
                edQBR.setCd_Conta(res.getString("cd_Conta"));
                edQBR.setNm_Conta(res.getString("nm_Conta"));
            }
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"getByRecord(Dre_Item_FormacaoED ed)");
        }
        
        return edQBR;
    }
}