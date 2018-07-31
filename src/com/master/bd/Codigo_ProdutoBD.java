/*
 * Created on 24/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Codigo_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Codigo_ProdutoBD {

    private ExecutaSQL executasql;

    public Codigo_ProdutoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Codigo_ProdutoED inclui(Codigo_ProdutoED ed) throws Excecoes {

        String sql = null;
        int valOid = 0;
        Codigo_ProdutoED edVolta = new Codigo_ProdutoED();

        try {

            sql  = " select count(*) as result from codigos_produtos";
            sql += " where codigos_produtos.oid_produto = " + ed.getOid_Produto();
            sql += "   and cd_barra = '" +ed.getCd_Barra()+ "'";
                
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //*** Varifica se existe
            while (res.next()) {
                valOid = res.getInt("result");                    
            }
            
            if (valOid <= 0){
            	
            	sql  = " select max(oid_codigo_produto) as count from codigos_produtos";
                res = null;
                res = this.executasql.executarConsulta(sql);

                //*** Pega o valor máximo
                while (res.next())
                    valOid = res.getInt("count");                    
                
                sql = "insert into codigos_produtos ("
                    + "oid_codigo_produto, oid_produto, cd_barra, dm_Produto, dm_Situacao) values ("
                    + ++valOid +","+ ed.getOid_Produto() + ",'" + ed.getCd_Barra() + "','"
                    + ed.getDm_Produto() + "','" + ed.getDm_Situacao() + "')";

                executasql.executarUpdate(sql);
                edVolta.setOid_Produto(ed.getOid_Produto());   
                
            }
         
            return edVolta;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Codigo_Produto");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(Codigo_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " update codigos_produtos set ";
            sql += " cd_barra  = '" + ed.getCd_Barra()  + "', ";
            sql += " dm_Produto = '" + ed.getDm_Produto() + "', ";
            sql += " dm_Situacao = '" + ed.getDm_Situacao() + "' ";
            sql += " where oid_codigo_produto = " + ed.getOid_Codigo_Produto();
            
            ////SQL
            // System.err.println(sql);
            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Codigo_Produto");
            excecoes.setMetodo("altera(Codigo_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Codigo_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " delete from codigos_produtos " +
            	   " where oid_codigo_produto = " + ed.getOid_Codigo_Produto();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Codigo_Produto");
            excecoes.setMetodo("deleta(Codigo_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public ArrayList lista(Codigo_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select cp.*, pd.nm_produto as nm_produto" +
                  " from codigos_produtos cp, " +
                  "      produtos pd" +
                  " where cp.oid_produto = pd.oid_produto";
            
            if (ed.getOid_Produto() > 0 ) {
                sql += " and cp.oid_Produto = " + ed.getOid_Produto();
            }
            
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Codigo_ProdutoED edVolta = new Codigo_ProdutoED();
          
                edVolta.setOid_Codigo_Produto(new Integer(res.getString("oid_Codigo_Produto")).intValue());
                edVolta.setOid_Produto(new Integer(res.getString("oid_Produto")).intValue());
                edVolta.setCd_Barra(res.getString("cd_barra"));
                edVolta.setDm_Produto(res.getString("dm_Produto"));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
                edVolta.setNm_Produto(res.getString("nm_Produto"));
                
                list.add(edVolta);
            }
            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Codigo_Produto - SQL=" + sql);
            excecoes.setMetodo("lista(Codigo_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return list;
    }

    public Codigo_ProdutoED getByRecord(Codigo_ProdutoED ed) throws Excecoes {

        String sql = null;

        Codigo_ProdutoED edVolta = new Codigo_ProdutoED();
        try {

            sql = " select cp.*, pd.nm_produto as nm_produto, pd.cd_produto" +
            	  " from codigos_produtos cp, " +
            	  "      produtos pd" +
            	  " where cp.oid_produto = pd.oid_produto";
      
            if (ed.getOid_Codigo_Produto() > 0 ) {
                sql += " and cp.oid_Codigo_Produto = " + ed.getOid_Codigo_Produto();
            }
            
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Codigo_Produto(new Integer(res.getString("oid_Codigo_Produto")).intValue());
                edVolta.setOid_Produto(new Integer(res.getString("oid_Produto")).intValue());
                edVolta.setCd_Barra(res.getString("cd_barra"));
                edVolta.setDm_Produto(res.getString("dm_Produto"));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
                edVolta.setCd_Produto(res.getString("cd_produto"));
                edVolta.setNm_Produto(res.getString("nm_Produto"));   

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(Codigo_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        
    }

}