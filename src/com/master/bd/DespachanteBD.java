/*
 * Created on 19/01/2005
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.DespachanteED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 */
public class DespachanteBD extends BancoUtil{

    private ExecutaSQL executasql;

    public DespachanteBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public DespachanteED inclui(DespachanteED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Despachante(getAutoIncremento("oid_Despachante", "Despachantes"));  
            
            sql = " INSERT INTO Despachantes (" +
            	  "		oid_Despachante" +
            	  "		,oid_Pessoa" +
            	  "		,cd_Despachante" +
            	  "		,DM_Tipo) " +
            	  " VALUES (" +
            	  	ed.getOid_Despachante() +
            	  	",'" + ed.getOid_Pessoa() + "'" +
            	  	",'" + ed.getCD_Despachante() + "'" +
            	  	",'" + ed.getDM_Tipo() + "')";
// System.out.println(sql);                
            executasql.executarUpdate(sql);
        	return ed;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Despachante");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(DespachanteED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Despachantes SET ";
            sql += " 	cd_Despachante = '" + ed.getCD_Despachante() + "'" +
            	   "   ,DM_Tipo = '"+ed.getDM_Tipo()+"'";
            sql += " WHERE oid_Despachante = " + ed.getOid_Despachante();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Despachante");
            excecoes.setMetodo("altera(DespachanteED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(DespachanteED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Despachantes " +
            	  " WHERE oid_Despachante = " + ed.getOid_Despachante();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Despachante");
            excecoes.setMetodo("deleta(DespachanteED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public ArrayList lista(DespachanteED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT  Despachantes.oid_Despachante" +
            	  "			,Despachantes.oid_Pessoa" +
            	  "		 	,Despachantes.cd_Despachante" +
            	  "		 	,Despachantes.DM_Tipo" +
            	  "			,Pessoas.NM_Razao_Social as nm_Despachante " +
            	  " FROM Despachantes, Pessoas " +
            	  " WHERE Despachantes.oid_Pessoa = Pessoas.oid_Pessoa ";
            if (doValida(ed.getOid_Pessoa()))
                sql += "   AND Despachantes.oid_Pessoa = '" + ed.getOid_Pessoa() +"'";
            sql +=" ORDER BY Despachantes.cd_Despachante";
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                DespachanteED edVolta = new DespachanteED();
          
                edVolta.setOid_Despachante(res.getInt("oid_Despachante"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setCD_Despachante(res.getString("cd_Despachante"));
                edVolta.setNM_Despachante(res.getString("nm_Despachante"));
                edVolta.setDM_Tipo(res.getString("DM_Tipo"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Despachante - SQL=" + sql);
            excecoes.setMetodo("lista(DespachanteED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }
        
    public DespachanteED getByRecord(DespachanteED ed) throws Excecoes {

        String sql = null;

        DespachanteED edVolta = new DespachanteED();
        try {

            sql = " SELECT  Despachantes.oid_Despachante" +
            	  "			,Despachantes.oid_Pessoa" +
            	  "		 	,Despachantes.cd_Despachante" +
            	  "		 	,Despachantes.DM_Tipo" +
            	  "			,Pessoas.NM_Razao_Social as nm_Despachante " +
            	  " FROM Despachantes, Pessoas " +
            	  " WHERE Despachantes.oid_Pessoa = Pessoas.oid_Pessoa ";
            if (ed.getOid_Despachante() > 0)
            	sql += "   AND Despachantes.oid_Despachante = " + ed.getOid_Despachante();	
            if (doValida(ed.getOid_Pessoa()))
                sql += "   AND Despachantes.oid_Pessoa = '" + ed.getOid_Pessoa() +"'";
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            
            while (res.next()) {
      	
                edVolta.setOid_Despachante(res.getInt("oid_Despachante"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setCD_Despachante(res.getString("cd_Despachante"));
                edVolta.setNM_Despachante(res.getString("nm_Despachante"));
                edVolta.setDM_Tipo(res.getString("DM_Tipo"));
            }
            return edVolta;
        } catch(Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro!");
            excecoes.setMetodo("getByRecord(DespachanteED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public DespachanteED getByCdDespachante(String cd_Despachante) throws Excecoes {

        String sql = null;

        DespachanteED edVolta = new DespachanteED();
        try {

            sql = " SELECT  Despachantes.oid_Despachante" +
      	  		  "			,Despachantes.oid_Pessoa" +
      	  		  "		 	,Despachantes.cd_Despachante" +
      	  		  "		 	,Despachantes.DM_Tipo" +
      	  		  "			,Pessoas.NM_Razao_Social as nm_Despachante " +
      	  		  " FROM Despachantes, Pessoas " +
      	  		  " WHERE Despachantes.oid_Pessoa = Pessoas.oid_Pessoa " +
      	  		  "   AND Despachantes.CD_Despachante = '" +cd_Despachante+"'";	

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
	
                edVolta.setOid_Despachante(res.getInt("oid_Despachante"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setCD_Despachante(res.getString("cd_Despachante"));
                edVolta.setNM_Despachante(res.getString("nm_Despachante"));
                edVolta.setDM_Tipo(res.getString("DM_Tipo"));
            }
            return edVolta;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByCdDespachante(DespachanteED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }
}