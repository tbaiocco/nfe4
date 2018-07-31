package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Tipo_Tabela_VendaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Tipos de Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tipo_Tabela_VendaBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Tipo_Tabela_VendaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Tipo_Tabela_VendaED inclui(Tipo_Tabela_VendaED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Tipo_Tabela_Venda(getAutoIncremento("oid_Tipo_Tabela_Venda", "Tipos_Tabelas_Vendas"));  
            
            sql = " INSERT INTO Tipos_Tabelas_Vendas (" +
            	  "		 oid_Tipo_Tabela_Venda" +
            	  "		,CD_Tipo_Tabela_Venda" +
            	  "		,NM_Tipo_Tabela_Venda) " +
            	  " VALUES (" +
            	  	ed.getOid_Tipo_Tabela_Venda() +
            	  	",'" + ed.getCD_Tipo_Tabela_Venda() + "'" +
            	  	",'" + ed.getNM_Tipo_Tabela_Venda() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void altera(Tipo_Tabela_VendaED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Tipos_Tabelas_Vendas SET ";
            sql += " 	CD_Tipo_Tabela_Venda = '" + ed.getCD_Tipo_Tabela_Venda() + "'" +
            	   "   ,NM_Tipo_Tabela_Venda = '"+ed.getNM_Tipo_Tabela_Venda()+"'";
            sql += " WHERE oid_Tipo_Tabela_Venda = " + ed.getOid_Tipo_Tabela_Venda();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            	"altera()");
        }
    }

    public void deleta(Tipo_Tabela_VendaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Tipos_Tabelas_Vendas " +
            	  " WHERE oid_Tipo_Tabela_Venda = " + ed.getOid_Tipo_Tabela_Venda();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Tipo_Tabela_VendaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Tipos_Tabelas_Vendas " +
            	  " WHERE 1 = 1 ";
            if (ed.getOid_Tipo_Tabela_Venda() > 0)
                sql += " AND oid_Tipo_Tabela_Venda = "+ed.getOid_Tipo_Tabela_Venda();
            if (doValida(ed.getCD_Tipo_Tabela_Venda()))
                sql += " AND CD_Tipo_Tabela_Venda = '"+ed.getCD_Tipo_Tabela_Venda()+"'";
            if (doValida(ed.getNM_Tipo_Tabela_Venda()))
                sql += " AND NM_Tipo_Tabela_Venda LIKE '"+ed.getNM_Tipo_Tabela_Venda()+"%'";
            
            sql +=" ORDER BY CD_Tipo_Tabela_Venda";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Tipo_Tabela_VendaED edVolta = new Tipo_Tabela_VendaED();
                edVolta.setOid_Tipo_Tabela_Venda(res.getInt("oid_Tipo_Tabela_Venda"));
                edVolta.setCD_Tipo_Tabela_Venda(res.getString("CD_Tipo_Tabela_Venda"));
                edVolta.setNM_Tipo_Tabela_Venda(res.getString("NM_Tipo_Tabela_Venda"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
        return list;
    }
        
    public Tipo_Tabela_VendaED getByRecord(Tipo_Tabela_VendaED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            if (iterator.hasNext())
                return (Tipo_Tabela_VendaED) iterator.next();
            else return new Tipo_Tabela_VendaED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }
}