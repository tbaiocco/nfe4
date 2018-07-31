package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Setor_ProdutoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class Setor_ProdutoBD {

	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public Setor_ProdutoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Setor_ProdutoED inclui(Setor_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Setor_Produto(util.getAutoIncremento("oid_Setor_Produto", "Setores_Produtos"));  
            
            sql = " INSERT INTO Setores_Produtos (" +
            	  "		 oid_Setor_Produto" +
            	  "		,CD_Setor_Produto" +
            	  "		,NM_Setor_Produto) " +
            	  " VALUES (" +
            	  	ed.getOid_Setor_Produto() +
            	  	",'" + ed.getCD_Setor_Produto() + "'" +
            	  	",'" + ed.getNM_Setor_Produto() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void altera(Setor_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Setores_Produtos SET ";
            sql += " 	CD_Setor_Produto = '" + ed.getCD_Setor_Produto() + "'" +
            	   "   ,NM_Setor_Produto = '"+ed.getNM_Setor_Produto()+"'";
            sql += " WHERE oid_Setor_Produto = " + ed.getOid_Setor_Produto();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            	"altera()");
        }
    }

    public void deleta(Setor_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Setores_Produtos " +
            	  " WHERE oid_Setor_Produto = " + ed.getOid_Setor_Produto();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Setor_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Setores_Produtos " +
            	  " WHERE 1 = 1 ";
            if (ed.getOid_Setor_Produto() > 0)
                sql += " AND oid_Setor_Produto = "+ed.getOid_Setor_Produto();
            if (util.doValida(ed.getCD_Setor_Produto()))
                sql += " AND CD_Setor_Produto = '"+ed.getCD_Setor_Produto()+"'";
            if (util.doValida(ed.getNM_Setor_Produto()))
                sql += " AND NM_Setor_Produto LIKE '"+ed.getNM_Setor_Produto()+"%'";
            
            sql +=" ORDER BY CD_Setor_Produto";
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Setor_ProdutoED edVolta = new Setor_ProdutoED();
                edVolta.setOid_Setor_Produto(res.getInt("oid_Setor_Produto"));
                edVolta.setCD_Setor_Produto(res.getString("CD_Setor_Produto"));
                edVolta.setNM_Setor_Produto(res.getString("NM_Setor_Produto"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
        return list;
    }
        
    public Setor_ProdutoED getByRecord(Setor_ProdutoED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            if (iterator.hasNext())
                return (Setor_ProdutoED) iterator.next();
            else return new Setor_ProdutoED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }
}