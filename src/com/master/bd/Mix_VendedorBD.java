/*
 * Created on 31/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Mix_VendedorED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Mix_VendedorBD {

    private ExecutaSQL executasql;

    public Mix_VendedorBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Mix_VendedorED inclui(Mix_VendedorED ed) throws Excecoes {

        String sql = null;
        int valOid = 0;
        Mix_VendedorED edVolta = new Mix_VendedorED();

        try {

            sql  = " select count(*) as result from mix_vendedores";
            sql += " where oid_mix = " + ed.getOid_Mix();
            sql += "   and oid_vendedor = '" + ed.getOid_Vendedor()+"'";

            ResultSet res = this.executasql.executarConsulta(sql);

            //*** Varifica se existe
            while (res.next()) {
                valOid = res.getInt("result");
            }
            
            if (valOid <= 0){
            	
            	sql  = " select max(oid_mix_vendedor) as count from mix_vendedores";
            	res = this.executasql.executarConsulta(sql);

                //*** Pega o valor máximo
                while (res.next())
                    valOid = res.getInt("count");             
                
                sql = "insert into mix_vendedores ("
                    + "oid_mix_vendedor, oid_mix, oid_vendedor) values ("
                    + ++valOid +"," + ed.getOid_Mix() + ",'" + ed.getOid_Vendedor() + "')";
 
                executasql.executarUpdate(sql);                
                edVolta.setOid_Mix_Vendedor(valOid);                
                
            }
         
            return edVolta;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Mix_VendedorED");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(Mix_VendedorED ed) throws Excecoes {

        String sql = null;
        int valOid = 0;

        try {
            
            sql  = " select count(*) as result from mix_vendedores";
            sql += " where oid_mix = " + ed.getOid_Mix();
            sql += "   and oid_vendedor = '" + ed.getOid_Vendedor() +"'";

            ResultSet res = this.executasql.executarConsulta(sql);

            //*** Varifica se existe
            while (res.next()) {
                valOid = res.getInt("result");                    
            }            
            //// System.err.println("Dentro do BD (result) == "+valOid);
            
            if (valOid <= 0){  

                sql =  " update mix_vendedores set ";
                sql += " oid_mix  = " + ed.getOid_Mix() + ", ";
                sql += " oid_vendedor = '" + ed.getOid_Vendedor() +"'";
                sql += " where oid_mix_vendedor = " + ed.getOid_Mix_Vendedor();
            
                executasql.executarUpdate(sql);
                
            }
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Mix_Vendedor");
            excecoes.setMetodo("altera(Mix_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Mix_VendedorED ed) throws Excecoes {

        String sql = null;
        
        try {

            sql =  " delete from mix_vendedores " +
            	   " where oid_mix_vendedor = " + ed.getOid_Mix_Vendedor();            
            //// System.err.println(sql);
            executasql.executarUpdate(sql);

        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Mix_Vendedor");
            excecoes.setMetodo("deleta(Mix_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public ArrayList lista(Mix_VendedorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql =  " select oid_mix_vendedor, oid_mix, oid_vendedor " +
            	   " from mix_vendedores";
            sql += " where 1=1";

            if (ed.getOid_Vendedor() != null && !ed.getOid_Vendedor().equals("") &&
               !ed.getOid_Vendedor().equals("null") && ed.getOid_Vendedor().length() > 0){
                sql += " and oid_vendedor = '" + ed.getOid_Vendedor() +"'";
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Mix_VendedorED edVolta = new Mix_VendedorED();
          
                edVolta.setOid_Mix_Vendedor(res.getInt("oid_mix_vendedor"));
                edVolta.setOid_Mix(res.getInt("oid_mix"));
                edVolta.setOid_Vendedor(res.getString("oid_vendedor"));
                
                list.add(edVolta);
            }            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Mix_Vendedor - SQL=" + sql);
            excecoes.setMetodo("lista(Mix_Vendedor)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }

    public Mix_VendedorED getByRecord(Mix_VendedorED ed) throws Excecoes {

        String sql = null;

        Mix_VendedorED edVolta = new Mix_VendedorED();
        try {

        	sql  = " select oid_mix_vendedor, oid_mix, oid_vendedor " +
        			"from mix_vendedores";
        	sql += " where 1=1";
      
            if (ed.getOid_Mix_Vendedor() > 0 ) {
                sql += " and oid_mix_vendedor = " + ed.getOid_Mix_Vendedor();
            }
            
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Mix_Vendedor(res.getInt("oid_mix_vendedor"));
                edVolta.setOid_Mix(res.getInt("oid_mix"));
                edVolta.setOid_Vendedor(res.getString("oid_vendedor"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(Mix_Vendedor)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }

	public Mix_VendedorED getByOidMixVendedor(int oid_Mix_Vendedor) throws Excecoes {

        String sql = null;

        Mix_VendedorED edVolta = new Mix_VendedorED();
        try {

        	sql  = " select oid_mix_vendedor, oid_mix, oid_vendedor " +
        			"from mix_vendedores";
        	sql += " where oid_mix_vendedor = " + oid_Mix_Vendedor;
      
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Mix_Vendedor(res.getInt("oid_mix_vendedor"));
                edVolta.setOid_Mix(res.getInt("oid_mix"));
                edVolta.setOid_Vendedor(res.getString("oid_vendedor"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByOidMixVendedor(Mix_Vendedor)");
            excecoes.setExc(exc);
            throw excecoes;
        }
	}

}