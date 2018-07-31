/*
 * Created on 31/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.MixED;
import com.master.ed.RelatorioED;
import com.master.rl.MixRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class MixBD extends BancoUtil{

    private ExecutaSQL executasql;

    public MixBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public MixED inclui(MixED ed) throws Excecoes {

        String sql = null;
        
        try {

            ed.setOid_Mix(getAutoIncremento("oid_mix", "mix"));
                
            sql = "insert into mix ("
                + "oid_Mix, cd_Mix, nm_Mix) values ("
                + ed.getOid_Mix() +",'" + ed.getCd_Mix() + "','" + ed.getNm_Mix() + "')";
            
            executasql.executarUpdate(sql);
               
            return ed;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem(exc.getMessage());
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(MixED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " update mix set ";
            sql += " cd_mix  = '" + ed.getCd_Mix()  + "', ";
            sql += " nm_mix = '" + ed.getNm_Mix() + "'";
            sql += " where oid_mix = " + ed.getOid_Mix();
            
            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Mix");
            excecoes.setMetodo("altera(MixED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(MixED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " delete from mix " +
            	   " where oid_Mix = " + ed.getOid_Mix();
            
            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Mix");
            excecoes.setMetodo("deleta(MixED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public ArrayList lista(MixED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select oid_mix, cd_mix, nm_mix " +
            	  " from mix" +
            	  " order by cd_mix";
            
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                MixED edVolta = new MixED();
          
                edVolta.setOid_Mix(res.getInt("oid_Mix"));
                edVolta.setCd_Mix(res.getString("cd_mix"));
                edVolta.setNm_Mix(res.getString("nm_mix"));

                list.add(edVolta);
            }
            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Mix - SQL=" + sql);
            excecoes.setMetodo("lista(MixED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return list;
    }
    
    public MixED getByRecord(MixED ed) throws Excecoes {

        String sql = null;

        MixED edVolta = new MixED();
        try {

        	sql  = " select oid_mix, cd_mix, nm_mix from mix";
        	sql += " where 1 = 1";
      
            if (ed.getOid_Mix() > 0 ) {
                sql += " and oid_Mix = " + ed.getOid_Mix();
            }
            
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Mix(res.getInt("oid_Mix"));
                edVolta.setCd_Mix(res.getString("cd_mix"));
                edVolta.setNm_Mix(res.getString("nm_mix"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(MixED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        
    }
    
    public MixED getByOidMix(int oid_Mix) throws Excecoes {

        String sql = null;

        MixED edVolta = new MixED();
        try {

        	sql  = " select oid_mix, cd_mix, nm_mix from mix";
        	sql += " where oid_Mix = " + oid_Mix;

        	//*** SQL
        	//// System.err.println("[getByOidMix] SQL = "+sql);
        	
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Mix(res.getInt("oid_Mix"));
                edVolta.setCd_Mix(res.getString("cd_mix"));
                edVolta.setNm_Mix(res.getString("nm_mix"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByOidMix(MixED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        
    }
    
    public MixED getByCdMix(String cd_Mix) throws Excecoes {

        String sql = null;

        MixED edVolta = new MixED();
        try {

        	sql  = " SELECT oid_mix, cd_mix, nm_mix FROM mix";
        	sql += " WHERE cd_Mix = '" + cd_Mix +"'";
      
        	ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Mix(res.getInt("oid_Mix"));
                edVolta.setCd_Mix(res.getString("cd_mix"));
                edVolta.setNm_Mix(res.getString("nm_mix"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByCdMix(MixED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }
    
    //*** RELATÓRIOS
    public void RelMix(HttpServletResponse response) throws Exception {

        String sql = null;
        ArrayList lista = new ArrayList();

        try{

            sql  = " select cd_mix, nm_mix from mix";
            sql += " where oid_mix > 0";
            sql += " order by cd_mix";

            ResultSet res = this.executasql.executarConsulta(sql);
            
    		while (res.next()){    		    
    		    RelatorioED ed = new RelatorioED();
    			ed.setCd_mix(res.getString("cd_mix"));
    			ed.setNm_mix(res.getString("nm_mix"));
   			
    			lista.add(ed);   
    		}

            new MixRL().geraRelMix(lista, response);

        }catch(Exception exc){
            Excecoes exce = new Excecoes();
            exce.setExc(exc);
            exce.setMensagem("Erro no médo listar");
            exce.setClasse(this.getClass().getName());
        }
      }
}