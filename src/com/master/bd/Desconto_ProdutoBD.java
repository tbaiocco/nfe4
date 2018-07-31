package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import com.master.ed.Desconto_ProdutoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Max. Descontos dos Produtos Referente a Tabelas de Preços
 * @serialData 28/02/2006
 */
public class Desconto_ProdutoBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Desconto_ProdutoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Desconto_ProdutoED inclui(Desconto_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {            
            ed.setOid_Desconto_Produto(getAutoIncremento("oid_Desconto_Produto", "Descontos_Produtos"));  
            
            sql = " INSERT INTO Descontos_Produtos (" +
            	  "		 oid_Desconto_Produto" +
                  "     ,oid_Tabela_Venda" +
                  "     ,oid_Produto_Cliente" +
                  "     ,NR_Quantidade" +
                  "     ,PE_Desconto) " +
            	  " VALUES (" +
            	  	ed.getOid_Desconto_Produto() +
                    "," + ed.getOid_Tabela_Venda() +
            	  	"," + getSQLString(ed.getOid_Produto_Cliente()) +
                    "," + ed.getNR_Quantidade() +
                    "," + ed.getPE_Desconto() + ")";
            executasql.executarUpdate(sql);
        	return ed;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void deleta(Desconto_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Descontos_Produtos " +
            	  " WHERE oid_Desconto_Produto = " + ed.getOid_Desconto_Produto();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Desconto_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Descontos_Produtos " +
            	  " WHERE 1 = 1 ";
            if (ed.getOid_Desconto_Produto() > 0)
                sql += " AND oid_Desconto_Produto = "+ed.getOid_Desconto_Produto();
            else {
                if (ed.getOid_Tabela_Venda() > 0)
                    sql += " AND oid_Tabela_Venda = "+ed.getOid_Tabela_Venda();
                if (doValida(ed.getOid_Produto_Cliente()))
                    sql += " AND oid_Produto_Cliente = "+getSQLString(ed.getOid_Produto_Cliente());
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Desconto_ProdutoED edVolta = new Desconto_ProdutoED();
                edVolta.setOid_Desconto_Produto(res.getInt("oid_Desconto_Produto"));
                edVolta.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
                edVolta.setOid_Produto_Cliente(res.getString("oid_Produto_Cliente"));
                edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
                edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
        return list;
    }
        
    public Desconto_ProdutoED getByRecord(Desconto_ProdutoED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            return iterator.hasNext() ? (Desconto_ProdutoED) iterator.next() : new Desconto_ProdutoED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }
}