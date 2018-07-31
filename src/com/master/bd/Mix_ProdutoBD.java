/*
 * Created on 31/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Mix_ProdutoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Mix_ProdutoBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Mix_ProdutoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Mix_ProdutoED inclui(Mix_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {

            ed.setOid_Mix_Produto(getAutoIncremento("oid_mix_produto", "mix_produtos"));
                
            sql = " insert into mix_produtos (" +
                  "		oid_mix_produto, " +
                  "		oid_mix, " +
                  "		oid_produto," +
                  "		oid_Pessoa_Distribuidor) " +
                  " values (" +
                  ed.getOid_Mix_Produto() +"," + 
                  ed.getOid_Mix() + "," + 
                  ed.getOid_Produto() + ",'" + 
                  ed.getOid_Pessoa_Distribuidor()+"')";
 
            executasql.executarUpdate(sql);
                
            return ed;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Mix_ProdutoED");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(Mix_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {
            
            sql =  " update mix_produtos set ";
            sql += " oid_mix  = " + ed.getOid_Mix() + ", ";
            sql += " oid_produto = " + ed.getOid_Produto() + ", ";
            sql += " oid_Pessoa_Distribuidor = '" + ed.getOid_Pessoa_Distribuidor() + "'";
            sql += " where oid_mix_produto = " + ed.getOid_Mix_Produto();
            
            executasql.executarUpdate(sql);             
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Mix_Produto");
            excecoes.setMetodo("altera(Mix_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Mix_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {
            
            sql = " delete from mix_produtos " +
            	  " where oid_mix_produto = " + ed.getOid_Mix_Produto();            
            executasql.executarUpdate(sql);

        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Mix_Produto");
            excecoes.setMetodo("deleta(Mix_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public ArrayList lista(Mix_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql =  " select oid_mix_produto, " +
            	   "		oid_mix, " +
            	   "		oid_produto, " +
            	   "		oid_Pessoa_Distribuidor " +
            	   " from mix_produtos";
            sql += " where 1=1";
            if (ed.getOid_Produto() > 0){
                sql += " and oid_produto = " + ed.getOid_Produto();                	   
            } 
            if (ed.getOid_Pessoa_Distribuidor() != null){
                sql += " and oid_Pessoa_Distribuidor = '" + ed.getOid_Pessoa_Distribuidor()+ "'";
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Mix_ProdutoED edVolta = new Mix_ProdutoED();
          
                edVolta.setOid_Mix_Produto(res.getInt("oid_mix_produto"));
                edVolta.setOid_Mix(res.getInt("oid_mix"));
                edVolta.setOid_Produto(res.getInt("oid_produto"));
                edVolta.setOid_Pessoa_Distribuidor(res.getString("oid_Pessoa_Distribuidor"));

                list.add(edVolta);
            }            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Mix_Produto - SQL=" + sql);
            excecoes.setMetodo("lista(Mix_Produto)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }

    public Mix_ProdutoED getByRecord(Mix_ProdutoED ed) throws Excecoes {

        String sql = null;

        Mix_ProdutoED edVolta = new Mix_ProdutoED();
        try {

            sql  = " select oid_mix_produto, " +
            	   "		oid_mix, " +
            	   "		oid_produto, " +
            	   "		oid_Pessoa_Distribuidor " +
        		   " from mix_produtos ";
        	sql += " where 1=1";
      
            if (ed.getOid_Mix_Produto() > 0 ) {
                sql += " and oid_mix_produto = " + ed.getOid_Mix_Produto();
            } else if (ed.getOid_Produto() > 0 ) {
                sql += " and oid_produto = " + ed.getOid_Produto();
            }    
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Mix_Produto(res.getInt("oid_mix_produto"));
                edVolta.setOid_Mix(res.getInt("oid_mix"));
                edVolta.setOid_Produto(res.getInt("oid_produto"));
                edVolta.setOid_Pessoa_Distribuidor(res.getString("oid_Pessoa_Distribuidor"));

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(Mix_Produto)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }

	public Mix_ProdutoED getByOidMixProduto(int oid_Mix_Produto) throws Excecoes {

        String sql = null;

        Mix_ProdutoED edVolta = new Mix_ProdutoED();
        try {

        	sql  = " select oid_mix_produto, " +
        		   "		oid_mix, " +
        		   "		oid_produto, " +
        		   "		oid_Pessoa_Distribuidor " +
        			"from mix_produtos";
        	sql += " where oid_mix_produto = " + oid_Mix_Produto;
      
        	ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Mix_Produto(res.getInt("oid_mix_produto"));
                edVolta.setOid_Mix(res.getInt("oid_mix"));
                edVolta.setOid_Produto(res.getInt("oid_produto"));
                edVolta.setOid_Pessoa_Distribuidor(res.getString("oid_Pessoa_Distribuidor"));

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByOidMixProduto(Mix_Produto)");
            excecoes.setExc(exc);
            throw excecoes;
        }
	}
}