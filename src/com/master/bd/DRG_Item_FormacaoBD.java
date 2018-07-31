package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.DRG_Item_FormacaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Formações dos Itens do D.R.G.
 * @serialData 14/10/2005
 */
public class DRG_Item_FormacaoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public DRG_Item_FormacaoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public DRG_Item_FormacaoED inclui(DRG_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_DRG_Item_Formacao(getAutoIncremento("oid_DRG_Item_Formacao", "DRG_Itens_Formacoes"));
            
            sql = " INSERT INTO DRG_Itens_Formacoes (" +
            	  "		 oid_DRG_Item_Formacao" +
            	  "		,oid_DRG_Item" +
            	  "		,oid_Conta" +
                  "     ,DM_Considerar" +
            	  "		,DM_Inverter) " +
            	  " VALUES (" +
            	  	ed.getOid_DRG_Item_Formacao() +
            	  	"," + ed.getOid_DRG_Item() +
            	  	"," + ed.getOid_Conta() +
                    ",'" + ed.getDM_Considerar() +"'"+
            	  	",'" + ed.getDM_Inverter() +"')";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(DRG_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE DRG_Itens_Formacoes SET ";
            sql += " 	 oid_DRG_Item_Formacao = oid_DRG_Item_Formacao ";
            if (ed.getOid_DRG_Item() > 0) 
                sql += " 	,oid_DRG_Item = " + ed.getOid_DRG_Item();
            if (ed.getOid_Conta() > 0) 
                sql += "    ,oid_Conta = " + ed.getOid_Conta();
            if (doValida(ed.getDM_Considerar())) 
                sql += "    ,DM_Considerar = '" + ed.getDM_Considerar()+"'";
            if (doValida(ed.getDM_Inverter())) 
                sql += "    ,DM_Inverter = '" + ed.getDM_Inverter()+"'";
            sql += " WHERE oid_DRG_Item_Formacao = " + ed.getOid_DRG_Item_Formacao();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(DRG_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM DRG_Itens_Formacoes " +
            	  " WHERE oid_DRG_Item_Formacao = " + ed.getOid_DRG_Item_Formacao();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(DRG_Item_FormacaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM DRG_Itens_Formacoes " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_DRG_Item_Formacao() > 0)
                sql += "   AND DRG_Itens_Formacoes.oid_DRG_Item_Formacao = "+ed.getOid_DRG_Item_Formacao();
            else {
	            if (ed.getOid_DRG_Item() > 0)
	                sql += "   AND DRG_Itens_Formacoes.oid_DRG_Item = "+ed.getOid_DRG_Item();
	            if (ed.getOid_Conta() > 0)
	                sql += "   AND DRG_Itens_Formacoes.oid_Conta = "+ed.getOid_Conta();
	            if (doValida(ed.getDM_Considerar()))
	                sql += "   AND DRG_Itens_Formacoes.DM_Considerar = '"+ed.getDM_Considerar()+"'";
	            if (doValida(ed.getDM_Inverter()))
	                sql += "   AND DRG_Itens_Formacoes.DM_Inverter = '"+ed.getDM_Inverter()+"'";
            }
            sql += " ORDER BY DRG_Itens_Formacoes.oid_DRG_Item";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                DRG_Item_FormacaoED edVolta = new DRG_Item_FormacaoED();
          
                edVolta.setOid_DRG_Item_Formacao(res.getInt("oid_DRG_Item_Formacao"));
                edVolta.setOid_DRG_Item(res.getInt("oid_DRG_Item"));
                edVolta.setOid_Conta(res.getInt("oid_Conta"));
                edVolta.setDM_Considerar(res.getString("DM_Considerar"));
                edVolta.setDM_Inverter(res.getString("DM_Inverter"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }

    public DRG_Item_FormacaoED getByRecord(DRG_Item_FormacaoED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (DRG_Item_FormacaoED) iterator.next();
        } else return new DRG_Item_FormacaoED();
    }
}