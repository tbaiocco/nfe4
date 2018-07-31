package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Fornecedor_ProdutoED;
import com.master.ed.ProdutoED;
import com.master.root.FornecedorBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Fornecedores do Produto
 * @serialData 02/09/2005
 */
public class Fornecedor_ProdutoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Fornecedor_ProdutoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Fornecedor_ProdutoED inclui(Fornecedor_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {
            
            ed.setOid_Fornecedor_Produto(getAutoIncremento("oid_Fornecedor_Produto", "Fornecedores_Produtos"));
            sql = " INSERT INTO Fornecedores_Produtos (" +
            	  "		 oid_Fornecedor_Produto" +
            	  "		,oid_Pessoa" +
            	  "		,oid_Produto) " +
            	  " VALUES (" +
            	  	ed.getOid_Fornecedor_Produto() +
            	  	",'" + ed.getOid_Pessoa() + "'" +
            	  	"," + ed.getOid_Produto() + ")";
            executasql.executarUpdate(sql);

        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Fornecedor_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Fornecedores_Produtos SET ";
            sql += "     oid_Pessoa = '" + ed.getOid_Pessoa() +"'";
            sql += "    ,oid_Produto = "+ ed.getOid_Produto();
            sql += " WHERE oid_Fornecedor_Produto = " + ed.getOid_Fornecedor_Produto();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Fornecedor_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {
            ed = this.getByRecord(ed);
            sql = " DELETE FROM Fornecedores_Produtos " +
            	  " WHERE oid_Fornecedor_Produto = " + ed.getOid_Fornecedor_Produto();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(Fornecedor_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Fornecedores_Produtos " +
            	  " WHERE 1=1";
            if (ed.getOid_Fornecedor_Produto() > 0)
                sql += "   AND oid_Fornecedor_Produto = "+ed.getOid_Fornecedor_Produto();
            if (doValida(ed.getOid_Pessoa()))
                sql += "   AND oid_Pessoa = "+ed.getOid_Pessoa();
            if (ed.getOid_Produto() > 0)
                sql += "   AND oid_Produto = "+ed.getOid_Produto();
            sql += " ORDER BY oid_Produto";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Fornecedor_ProdutoED edVolta = new Fornecedor_ProdutoED();
                edVolta.setOid_Fornecedor_Produto(res.getInt("oid_Fornecedor_Produto"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setOid_Produto(res.getInt("oid_Produto"));
                if (edVolta.getOid_Produto() > 0 && ed.isCarregaProduto())
                    edVolta.edProduto = new ProdutoBD(executasql).getByRecord(new ProdutoED(String.valueOf(edVolta.getOid_Produto())));
                if (doValida(edVolta.getOid_Pessoa()) && ed.isCarregaFornecedor())
                    edVolta.edFornec = FornecedorBean.getByOID_Fornecedor(edVolta.getOid_Pessoa());
                
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
        return list;
    }
        
    public Fornecedor_ProdutoED getByRecord(Fornecedor_ProdutoED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Fornecedor_ProdutoED) iterator.next();
        } else return new Fornecedor_ProdutoED();
    }
}