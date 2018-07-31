/*
 * Created on 10/09/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Classificacao_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Classificacao_FiscalBD {

    private ExecutaSQL executasql;

    public Classificacao_FiscalBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Classificacao_FiscalED inclui(Classificacao_FiscalED ed) throws Excecoes {

        String sql = null;
        int valOid = 0;
        Classificacao_FiscalED edVolta = new Classificacao_FiscalED();

        try {

            sql  = " select count(*) as result from classificacoes_fiscais";
            sql += " where cd_Reduzido = '" + ed.getCD_Reduzido() +"'";

            ResultSet res = this.executasql.executarConsulta(sql);

            //*** Varifica se existe
            while (res.next()) {
                valOid = res.getInt("result");
            }
            
            if (valOid <= 0){            	
            	sql  = " select max(oid_Classificacao_Fiscal) as count from classificacoes_fiscais";

            	res = this.executasql.executarConsulta(sql);

                //*** Pega o valor máximo
                while (res.next())
                    valOid = res.getInt("count");                    
                
                sql = "insert into classificacoes_fiscais ("
                    + "oid_Classificacao_Fiscal, cd_Reduzido, cd_Fiscal) values ("
                    + ++valOid +",'" + ed.getCD_Reduzido() + "','" + ed.getCD_Fiscal() + "')";
                
                executasql.executarUpdate(sql);
                edVolta.setOid_Classificacao_Fiscal(ed.getOid_Classificacao_Fiscal());   
                
            }
         
            return edVolta;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Classificacao_Fiscal");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(Classificacao_FiscalED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " update classificacoes_fiscais set ";
            sql += " cd_Reduzido  = '" + ed.getCD_Reduzido()  + "', ";
            sql += " cd_Fiscal = '" + ed.getCD_Fiscal() + "'";
            sql += " where oid_Classificacao_Fiscal = " + ed.getOid_Classificacao_Fiscal();
            
            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Classificacao_Fiscal");
            excecoes.setMetodo("altera(Classificacao_FiscalED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Classificacao_FiscalED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " delete from classificacoes_fiscais " +
            	   " where oid_Classificacao_Fiscal = " + ed.getOid_Classificacao_Fiscal();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Classificacao_Fiscal");
            excecoes.setMetodo("deleta(Classificacao_FiscalED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public ArrayList lista(Classificacao_FiscalED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select oid_Classificacao_Fiscal, cd_reduzido, cd_fiscal from classificacoes_fiscais";
            if (ed.getCD_Fiscal()!= null)
            	sql +=" WHERE cd_fiscal LIKE '" + ed.getCD_Fiscal() + "%' ";
        	sql +=" order by cd_reduzido";
            
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Classificacao_FiscalED edVolta = new Classificacao_FiscalED();
          
                edVolta.setOid_Classificacao_Fiscal(res.getInt("oid_Classificacao_Fiscal"));
                edVolta.setCD_Reduzido(res.getString("cd_Reduzido"));
                edVolta.setCD_Fiscal(res.getString("cd_Fiscal"));

                list.add(edVolta);
            }
            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Classificacao_Fiscal - SQL=" + sql);
            excecoes.setMetodo("lista(Classificacao_FiscalED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return list;
    }
    
    public Classificacao_FiscalED getByRecord(Classificacao_FiscalED ed) throws Excecoes {

        String sql = null;

        Classificacao_FiscalED edVolta = new Classificacao_FiscalED();
        try {

        	sql  = " select oid_Classificacao_Fiscal, cd_Reduzido, cd_Fiscal from classificacoes_fiscais";
        	sql += " where 1 = 1";
        	
            if (ed.getOid_Classificacao_Fiscal() > 0 ) {
                sql += " and oid_Classificacao_Fiscal = " + ed.getOid_Classificacao_Fiscal();
            }
            
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Classificacao_Fiscal(res.getInt("oid_Classificacao_Fiscal"));
                edVolta.setCD_Reduzido(res.getString("cd_Reduzido"));
                edVolta.setCD_Fiscal(res.getString("cd_Fiscal"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(Classificacao_FiscalED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        
    }
    
    public Classificacao_FiscalED getByOidClassificacao_Fiscal(int oid_Classificacao_Fiscal) throws Excecoes {

        String sql = null;

        Classificacao_FiscalED edVolta = new Classificacao_FiscalED();
        try {

        	sql  = " select oid_Classificacao_Fiscal, cd_reduzido, cd_Fiscal from classificacoes_fiscais";
        	sql += " where oid_Classificacao_Fiscal = " + oid_Classificacao_Fiscal;
        	
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Classificacao_Fiscal(res.getInt("oid_Classificacao_Fiscal"));
                edVolta.setCD_Reduzido(res.getString("cd_Reduzido"));
                edVolta.setCD_Fiscal(res.getString("cd_Fiscal"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByOidClassificacao_Fiscal(Classificacao_FiscalED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        
    }
    
    public Classificacao_FiscalED getByCdReduzido(String cd_Reduzido) throws Excecoes {

        String sql = null;

        Classificacao_FiscalED edVolta = new Classificacao_FiscalED();
        try {

        	sql  = " select oid_Classificacao_Fiscal, cd_Reduzido, cd_Fiscal from classificacoes_fiscais";
        	sql += " where cd_Reduzido = '" + cd_Reduzido +"'";
      
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Classificacao_Fiscal(res.getInt("oid_Classificacao_Fiscal"));
                edVolta.setCD_Reduzido(res.getString("cd_Reduzido"));
                edVolta.setCD_Fiscal(res.getString("cd_Fiscal"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByCdReduzido(Classificacao_FiscalED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }
}