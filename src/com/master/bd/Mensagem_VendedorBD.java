package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Mensagem_VendedorED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Mensagens Vendedores
 * @serialData 27/02/2006
 */
public class Mensagem_VendedorBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Mensagem_VendedorBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Mensagem_VendedorED inclui(Mensagem_VendedorED ed) throws Excecoes {

        String sql = null;
        try {            
            ed.setOid_Mensagem_Vendedor(getAutoIncremento("oid_Mensagem_Vendedor", "Mensagens_Vendedores"));  
            
            sql = " INSERT INTO Mensagens_Vendedores (" +
            	  "		 oid_Mensagem_Vendedor" +
            	  "		,oid_Vendedor" +
                  "     ,DT_Mensagem" +
                  "     ,HR_Mensagem" +
                  "     ,TX_Assunto" +
            	  "		,TX_Mensagem) " +
            	  " VALUES (" +
            	  	ed.getOid_Mensagem_Vendedor() +
            	  	"," + getSQLString(ed.getOid_Vendedor()) +
                    "," + getSQLDate(ed.getDT_Mensagem()) +
                    "," + getSQLString(ed.getHR_Mensagem()) +
                    "," + getSQLString(ed.getTX_Assunto()) +
            	  	"," + getSQLString(ed.getTX_Mensagem()) + ")";
            executasql.executarUpdate(sql);
        	return ed;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void deleta(Mensagem_VendedorED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Mensagens_Vendedores " +
            	  " WHERE oid_Mensagem_Vendedor = oid_Mensagem_Vendedor ";
            if (ed.getOid_Mensagem_Vendedor() > 0)
                sql += " AND oid_Mensagem_Vendedor = " + ed.getOid_Mensagem_Vendedor();
            else if (doValida(ed.getDT_Mensagem()))
                sql += " AND DT_Mensagem = "+ getSQLDate(ed.getDT_Mensagem());
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Mensagem_VendedorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Mensagens_Vendedores " +
            	  " WHERE 1 = 1 ";
            if (ed.getOid_Mensagem_Vendedor() > 0)
                sql += " AND oid_Mensagem_Vendedor = "+ed.getOid_Mensagem_Vendedor();
            else {
                if (doValida(ed.getOid_Vendedor()))
                    sql += " AND oid_Vendedor = "+getSQLString(ed.getOid_Vendedor());
                if (doValida(ed.getDT_Mensagem()))
                    sql += " AND DT_Mensagem = "+getSQLDate(ed.getDT_Mensagem());
            }
            sql +=" ORDER BY DT_Mensagem DESC";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Mensagem_VendedorED edVolta = new Mensagem_VendedorED();
                edVolta.setOid_Mensagem_Vendedor(res.getInt("oid_Mensagem_Vendedor"));
                edVolta.setOid_Vendedor(res.getString("oid_Vendedor"));
                edVolta.setDT_Mensagem(FormataData.formataDataBT(res.getString("DT_Mensagem")));
                edVolta.setHR_Mensagem(res.getString("HR_Mensagem"));
                edVolta.setTX_Assunto(res.getString("TX_Assunto"));
                edVolta.setTX_Mensagem(res.getString("TX_Mensagem"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
        return list;
    }
        
    public Mensagem_VendedorED getByRecord(Mensagem_VendedorED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            return iterator.hasNext() ? (Mensagem_VendedorED) iterator.next() : new Mensagem_VendedorED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }
}