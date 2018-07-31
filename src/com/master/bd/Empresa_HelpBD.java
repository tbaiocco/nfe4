package com.master.bd;

/**
 * @author Jeanine e Vinícius
 * date: 21/05/2008
 */ 

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Empresa_HelpED;
import com.master.ed.Help_DeskED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;


public class Empresa_HelpBD extends BancoUtil {

	private ExecutaSQL executasql;
	FormataDataBean dataFormatada = new FormataDataBean ();

	public Empresa_HelpBD(ExecutaSQL sql) {
		
		this.executasql = sql;
	}
	
	public Empresa_HelpED inclui(Empresa_HelpED ed) throws Excecoes {
		
		String sql = "select max(oid_Empresa) as result from empresas_help ";
		long valOid = 1;

		Empresa_HelpED Empresa_HelpED = new Empresa_HelpED();

		try {
			ResultSet rs = executasql.executarConsulta(sql);
			while (rs.next()) {
				valOid = rs.getLong("result");
			}

			valOid++;

			sql = " insert into empresas_help ( " + "oid_Empresa, "
				+ " nm_Empresa) values";

			sql += "(" + valOid + ",'" 
					+ ed.getNm_Empresa()+ "')";

				executasql.executarUpdate(sql);

				Empresa_HelpED.setOid_Empresa(valOid);

			// this.enviaMail(Help_DeskED, "N");

		} catch (Exception exc) {
			exc.printStackTrace();
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao incluir!!");
			excecoes.setMetodo("inclui");
			excecoes.setExc(exc);
			throw excecoes;
		}
		return Empresa_HelpED;

	}

    public void altera(Empresa_HelpED ed) throws Excecoes {
    	String sql = null;
		try {
						
			sql = "UPDATE " + " empresas_help " + "SET " 
					+ " nm_Empresa = '"
					+ ed.getNm_Empresa()+ "' ";
			
			sql += " where oid_Empresa = '" + ed.getOid_Empresa() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"altera(Empresa_HelpED ed)");
		}
    }
    public void deleta(Empresa_HelpED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM empresas_help " +
            	  " WHERE oid_Empresa = " + ed.getOid_Empresa();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Empresa_HelpED ed)");
        }
    }
    
    public ArrayList lista(Empresa_HelpED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM empresas_help " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_Empresa() > 0)
                sql += "   AND empresas_help.oid_Empresa = "+ed.getOid_Empresa();
            else {
	            if (doValida(ed.getNm_Empresa()))
	                sql += "   AND empresas_help.nm_Empresa LIKE '"+ed.getNm_Empresa()+"%'";
            }
            sql += " ORDER BY empresas_help.nm_Empresa";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
            	Empresa_HelpED edVolta = new Empresa_HelpED();
          
                edVolta.setOid_Empresa(res.getInt("oid_Empresa"));
                edVolta.setNm_Empresa(res.getString("nm_Empresa"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Empresa_HelpED ed)");
        }
    }

    public Empresa_HelpED getByRecord(Empresa_HelpED ed) throws Excecoes {

	    String sql = null;
	    Empresa_HelpED edQBR = new Empresa_HelpED();

	    try{
	    	sql = "SELECT * " + " FROM " + " empresas_help ";
	    	if (ed.getOid_Empresa() > 0 )
	    		sql += " where oid_Empresa = " + ed.getOid_Empresa();
	    	else
	    		sql += " where nm_Empresa like '%" + ed.getNm_Empresa() +"'";
	    	
	    	
	    	
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			
			
			while (res.next()) {

				edQBR.setOid_Empresa(res.getLong("oid_Empresa"));
				edQBR.setNm_Empresa(res.getString("nm_Empresa"));

	      }
	    }
	      catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(SistemaED ed)");
	      }
	    return edQBR;
    }
    
}
