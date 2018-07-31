/*
 * Created on 30/09/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Area_VendedorED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Area_VendedorBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Area_VendedorBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Area_VendedorED inclui(Area_VendedorED ed) throws Excecoes {

        String sql = null;
        Area_VendedorED edVolta = new Area_VendedorED();

        try {

            // Auto-incrementa
            edVolta.setOid_Area_Vendedor(getAutoIncremento("oid_Area_Vendedor","areas_vendedores"));
                            
            sql = "insert into areas_vendedores ("
                + "oid_Area_Vendedor, oid_Area_Venda, oid_vendedor) values ("
                + edVolta.getOid_Area_Vendedor() +"," + ed.getOid_Area_Venda() + ",'" + ed.getOid_Vendedor() + "')";
            
            //*** SQL
            //// System.out.println("SQL incluir >> "+sql);
 
            executasql.executarUpdate(sql);
         
            return edVolta;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Area_VendedorED");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(Area_VendedorED ed) throws Excecoes {

        String sql = null;
        try {         

            sql =  " update areas_vendedores set ";
            sql += " oid_Area_Venda  = " + ed.getOid_Area_Venda() + ", ";
            sql += " oid_vendedor = '" + ed.getOid_Vendedor() +"'";
            sql += " where oid_Area_Vendedor = " + ed.getOid_Area_Vendedor();
            
            executasql.executarUpdate(sql);
         
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Area_Vendedor");
            excecoes.setMetodo("altera(Area_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Area_VendedorED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " delete from areas_vendedores " +
            	   " where oid_Area_Vendedor = " + ed.getOid_Area_Vendedor();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Area_Vendedor");
            excecoes.setMetodo("deleta(Area_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

	public Area_VendedorED getByOidArea_Vendedor(int oid_Area_Vendedor) throws Excecoes {

        String sql = null;

        Area_VendedorED edVolta = new Area_VendedorED();
        try {

        	sql  = " select areas_vendedores.oid_Area_Vendedor, areas_vendedores.oid_Area_Venda, areas_vendedores.oid_vendedor, areas_vendas.cd_Area_Venda, areas_vendas.nm_Area_Venda " +
        			"from areas_vendedores, areas_vendas";
        	sql += " where areas_vendedores.oid_Area_Venda = areas_vendas.oid_Area_Venda" +
        			" and areas_vendedores.oid_Area_Vendedor = " + oid_Area_Vendedor;
        	
        	//*** SQL
        	//// System.out.println("SQL getBy >> "+sql);
      
        	ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Area_Vendedor(res.getInt("oid_Area_Vendedor"));
                edVolta.setOid_Vendedor(res.getString("oid_vendedor"));
                edVolta.setOid_Area_Venda(res.getInt("oid_Area_Venda"));
                edVolta.setCD_Area_Venda(res.getString("CD_Area_Venda"));
                edVolta.setNM_Area_Venda(res.getString("NM_Area_Venda"));

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByOidArea_Vendedor(Area_Vendedor)");
            excecoes.setExc(exc);
            throw excecoes;
        }
	}
    
    public ArrayList lista(Area_VendedorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql  = " select areas_vendedores.oid_Area_Vendedor, areas_vendedores.oid_Area_Venda, areas_vendedores.oid_vendedor, areas_vendas.cd_Area_Venda, areas_vendas.nm_Area_Venda " +
					"from areas_vendedores, areas_vendas";
            sql += " where areas_vendedores.oid_Area_Venda = areas_vendas.oid_Area_Venda";

            if (ed.getOid_Vendedor() != null && !ed.getOid_Vendedor().equals("") &&
               !ed.getOid_Vendedor().equals("null") && ed.getOid_Vendedor().length() > 0){
                sql += " and oid_vendedor = '" + ed.getOid_Vendedor() +"'";
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Area_VendedorED edVolta = new Area_VendedorED();
          
                edVolta.setOid_Area_Vendedor(res.getInt("oid_Area_Vendedor"));
                edVolta.setOid_Vendedor(res.getString("oid_vendedor"));
                edVolta.setOid_Area_Venda(res.getInt("oid_Area_Venda"));
                edVolta.setCD_Area_Venda(res.getString("CD_Area_Venda"));
                edVolta.setNM_Area_Venda(res.getString("NM_Area_Venda"));
                
                list.add(edVolta);
            }
            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Area_Vendedor");
            excecoes.setMetodo("lista(Area_Vendedor)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }
}