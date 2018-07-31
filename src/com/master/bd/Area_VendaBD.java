/*
 * Created on 31/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Area_VendaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Area_VendaBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Area_VendaBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Area_VendaED inclui(Area_VendaED ed) throws Excecoes {

        String sql = null;
        Area_VendaED edVolta = new Area_VendaED();

        try {
            
            //*** Pega o valor máximo
            edVolta.setOid_Area_Venda(getAutoIncremento("oid_Area_Venda", "areas_vendas"));                    
                
            sql = "insert into areas_vendas ("
                + "oid_Area_Venda, cd_Area_Venda, nm_Area_Venda) values ("
                + edVolta.getOid_Area_Venda() +",'" + ed.getCD_Area_Venda() + "','" + ed.getNM_Area_Venda() + "')";
                
                executasql.executarUpdate(sql);
         
            return edVolta;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Area_Venda");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(Area_VendaED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " update areas_vendas set ";
            sql += " cd_area_venda  = '" + ed.getCD_Area_Venda()  + "', ";
            sql += " nm_area_venda = '" + ed.getNM_Area_Venda() + "'";
            sql += " where oid_area_venda = " + ed.getOid_Area_Venda();
            
            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Area_Venda");
            excecoes.setMetodo("altera(Area_VendaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Area_VendaED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " delete from areas_vendas " +
            	   " where oid_Area_Venda = " + ed.getOid_Area_Venda();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Area_Venda");
            excecoes.setMetodo("deleta(Area_VendaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public ArrayList lista(Area_VendaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select oid_area_venda, cd_area_venda, nm_area_venda " +
            	  " from areas_vendas";
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Area_VendaED edVolta = new Area_VendaED();
          
                edVolta.setOid_Area_Venda(res.getInt("oid_Area_Venda"));
                edVolta.setCD_Area_Venda(res.getString("cd_area_venda"));
                edVolta.setNM_Area_Venda(res.getString("nm_area_venda"));

                list.add(edVolta);
            }
            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Area_Venda ");
            excecoes.setMetodo("lista(Area_VendaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return list;
    }
    
    public Area_VendaED getByRecord(Area_VendaED ed) throws Excecoes {

        String sql = null;

        Area_VendaED edVolta = new Area_VendaED();
        try {

        	sql  = " select oid_area_venda, cd_area_venda, nm_area_venda from areas_vendas";
        	sql += " where 1 = 1";
      
            if (ed.getOid_Area_Venda() > 0 ) {
                sql += " and oid_Area_Venda = " + ed.getOid_Area_Venda();
            }

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Area_Venda(res.getInt("oid_Area_Venda"));
                edVolta.setCD_Area_Venda(res.getString("cd_area_venda"));
                edVolta.setNM_Area_Venda(res.getString("nm_area_venda"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(Area_VendaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }
    
    public Area_VendaED getByOidArea_Venda(int oid_Area_Venda) throws Excecoes {

        String sql = null;

        Area_VendaED edVolta = new Area_VendaED();
        try {

        	sql  = " select oid_area_venda, cd_area_venda, nm_area_venda from areas_vendas";
        	sql += " where oid_Area_Venda = " + oid_Area_Venda;

        	//*** SQL
        	//// System.err.println("[getByOidArea_Venda] SQL = "+sql);
        	
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Area_Venda(res.getInt("oid_Area_Venda"));
                edVolta.setCD_Area_Venda(res.getString("cd_area_venda"));
                edVolta.setNM_Area_Venda(res.getString("nm_area_venda"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByOidArea_Venda(Area_VendaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }
    
    public Area_VendaED getByCDArea_Venda(String cd_Area_Venda) throws Excecoes {

        String sql = null;

        Area_VendaED edVolta = new Area_VendaED();
        try {

        	sql  = " select oid_area_venda, cd_area_venda, nm_area_venda from areas_vendas";
        	sql += " where cd_Area_Venda = '" + cd_Area_Venda +"'";
      
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Area_Venda(res.getInt("oid_Area_Venda"));
                edVolta.setCD_Area_Venda(res.getString("cd_area_venda"));
                edVolta.setNM_Area_Venda(res.getString("nm_area_venda"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByCdArea_Venda(Area_VendaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }    
    //*** RELATÓRIOS
}