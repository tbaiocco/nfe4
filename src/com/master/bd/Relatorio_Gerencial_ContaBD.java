package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Relatorio_Gerencial_ContaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 14/12/2005
 */

public class Relatorio_Gerencial_ContaBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Relatorio_Gerencial_ContaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Relatorio_Gerencial_ContaED inclui(Relatorio_Gerencial_ContaED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Relatorio_Gerencial_Conta(getAutoIncremento("oid_Relatorio_Gerencial_conta", "Relatorios_Gerenciais_Contas"));
            sql = " INSERT INTO " +
            	  " Relatorios_Gerenciais_Contas " +
            	  " ( " +
            	  "	oid_Relatorio_Gerencial_Conta," +
            	  "	oid_Conta," +	
            	  "	oid_Usuario," +
            	  "	oid_Relatorio_Gerencial," +
            	  "	oid_Relatorio_Gerencial_Grupo " +
            	  " ) " +
            	  " VALUES (" +
            	  ed.getOid_Relatorio_Gerencial_Conta() + ", " +
            	  ed.getOid_Conta() + ", " +
            	  ed.getOid_Usuario() + ", " + 
            	  ed.getOid_Relatorio_Gerencial() + ", " +
            	  ed.getOid_Relatorio_Gerencial_Grupo() + 
            	  " ) ";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Relatorio_Gerenciai_ContaED ed)");
        }
    }

    public void altera(Relatorio_Gerencial_ContaED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Relatorios_Gerenciais_Contas SET " +
            " oid_Relatorio_Gerencial_Conta = oid_Relatorio_Gerencial_Conta, " +
            " oid_Conta = " + ed.getOid_Conta() + ", " +
            " oid_Usuario = " + ed.getOid_Usuario() + ", " +
            " oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial() + ", " +
            " oid_Relatorio_Gerencial_Grupo = " + ed.getOid_Relatorio_Gerencial_Grupo() + ", " +
            " DT_STAMP = '" + ed.getDt_stamp() + "', " +
            " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', " +
            " DM_STAMP = '" + ed.getDm_Stamp() + "' " + 
            " WHERE  " +  
            " oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Relatorio_Gerencial_ContaED ed)");
        }
    }

    public void deleta(Relatorio_Gerencial_ContaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE " + 
            " FROM Relatorios_Gerenciais_Contas " +
            " WHERE oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Relatorio_Gerencial_ContaED ed)");
        }
    }
    
    public ArrayList lista(Relatorio_Gerencial_ContaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM " +
            	  " Relatorios_Gerenciais_Contas as rgc, " +
            	  " Usuarios as u, " +
            	  " Contas as c" +
            	  " WHERE 1=1 ";
            	  
            if (ed.getOid_Relatorio_Gerencial_Conta() > 0)
                sql += " AND rgc.oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta();
            else {
	            if (ed.getOid_Relatorio_Gerencial() > 0)
	                sql += "  AND rgc.oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial();
	            if (ed.getOid_Usuario() > 0)
	                sql += "  AND rgc.oid_Usuario = " + ed.getOid_Usuario();
	            if (ed.getOid_Relatorio_Gerencial_Grupo() > 0)
	                sql += "  AND rgc.oid_Relatorio_Gerencial_Grupo = " + ed.getOid_Relatorio_Gerencial_Grupo(); 
            }
            sql += " and rgc.oid_Conta = c.oid_Conta" + 
            	   " and rgc.oid_Usuario = u.oid_Usuario " +  	
                   " ORDER BY " +
                   " c.cd_Conta ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Relatorio_Gerencial_ContaED edVolta = new Relatorio_Gerencial_ContaED();
                edVolta.setOid_Relatorio_Gerencial_Conta(res.getInt("oid_Relatorio_Gerencial_Conta"));
                edVolta.setOid_Conta(res.getInt("oid_Conta"));
                edVolta.setOid_Usuario(res.getInt("oid_Usuario"));
                edVolta.setOid_Relatorio_Gerencial(res.getInt("oid_Relatorio_Gerencial"));
                edVolta.setOid_Relatorio_Gerencial_Grupo(res.getInt("oid_Relatorio_Gerencial_Grupo"));
                edVolta.setCd_Conta(res.getString("cd_Conta"));
                edVolta.setNm_Conta(res.getString("nm_Conta"));
                edVolta.setCd_Estrutural(res.getString("cd_Estrutural"));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Relatorio_Gerencial_ContaED ed)");
        }
    }

    public Relatorio_Gerencial_ContaED getByRecord(Relatorio_Gerencial_ContaED ed) throws Excecoes {
	    String sql = null;
	    Relatorio_Gerencial_ContaED edQBR = new Relatorio_Gerencial_ContaED();
	    try {
    		sql = " SELECT * " +
  	  		  	  " FROM " +
		      	  " Relatorios_Gerenciais_Contas as rgc, " +
		      	  " Usuarios as u, " +
		      	  " Contas as c " + 
		      	  " WHERE ";
			if (ed.getOid_Relatorio_Gerencial_Conta() > 0 ) {
  	  			sql +=" rgc.oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta();
			} else {
				if (ed.getOid_Relatorio_Gerencial_Grupo() > 0 ) {
					sql +=" rgc.oid_Relatorio_Gerencial_Grupo = " + ed.getOid_Relatorio_Gerencial_Grupo() +
						  " and rgc.oid_Conta = " + ed.getOid_Conta(); 
				}
			}
            sql += " and rgc.oid_Conta = c.oid_Conta" + 
     	    	   " and rgc.oid_Usuario = u.oid_Usuario " +  	
     	    	   " ORDER BY " +
     	    	   " c.cd_Conta ";
				
        	ResultSet res = null;
        	res = this.executasql.executarConsulta(sql);
        	while (res.next()){
        		populaED(edQBR, res);
    		}
    	}
	    catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Relatorio_Gerencial_ContaED ed)");
	    }
	    return edQBR;
    }

	protected void populaED(Relatorio_Gerencial_ContaED ed, ResultSet res) throws SQLException {
		ed.setOid_Relatorio_Gerencial_Conta(res.getInt("oid_Relatorio_Gerencial_Conta"));
		ed.setOid_Conta(res.getInt("oid_Conta"));
		ed.setOid_Usuario(res.getInt("oid_Usuario"));
		ed.setOid_Relatorio_Gerencial(res.getInt("oid_Relatorio_Gerencial"));
		ed.setOid_Relatorio_Gerencial_Grupo(res.getInt("oid_Relatorio_Gerencial_Grupo"));
		ed.setCd_Conta(res.getString("cd_Conta"));
		ed.setNm_Conta(res.getString("nm_Conta"));
		ed.setCd_Estrutural(res.getString("cd_Estrutural"));
		ed.setNm_Usuario(res.getString("nm_Usuario"));
	}
}