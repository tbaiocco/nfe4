package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Relatorio_Gerencial_MetaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais Metas
 * @serialData 14/12/2005
 */

public class Relatorio_Gerencial_MetaBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Relatorio_Gerencial_MetaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Relatorio_Gerencial_MetaED inclui(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Relatorio_Gerencial_Meta(getAutoIncremento("oid_Relatorio_Gerencial_Meta", "Relatorios_Gerenciais_Metas"));
            sql = " INSERT INTO " +
            	  " Relatorios_Gerenciais_Metas " +
            	  " ( " +
            	  " oid_relatorio_gerencial_meta, " +
            	  " oid_relatorio_gerencial, " +
            	  " oid_unidade, " +
            	  " oid_relatorio_gerencial_conta, " +
            	  " tx_mes_ano, " +
            	  " vl_meta, " +
            	  " vl_tendencia, " +
            	  " vl_realizado, " +
            	  " dm_congelado " +
            	  " ) " +
            	  " VALUES (" +
            	  ed.getOid_Relatorio_Gerencial_Meta() + ", " +
            	  ed.getOid_Relatorio_Gerencial() + ", " +
            	  ed.getOid_Unidade() + ", " +
            	  ed.getOid_Relatorio_Gerencial_Conta() + ", " + 
            	  "'" + ed.getTx_Mes_Ano() + "', " +
            	  ed.getVl_Meta() + ", " + 
            	  ed.getVl_Tendencia() + ", " +
            	  ed.getVl_Realizado() + ", " +
            	  " 'F' " +
            	  " ) ";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Relatorio_Gerencial_MetaED ed)");
        }
    }

    public void altera(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE " +
    		" Relatorios_Gerenciais_Metas " +
    		" SET " +
            " oid_Relatorio_Gerencial_Meta = oid_Relatorio_Gerencial_Meta, " +
            " oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial() + ", " +
            " oid_Unidade = " + ed.getOid_Unidade() + ", " +
            " oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta() + ", " +
            " tx_Mes_Ano = '" + ed.getTx_Mes_Ano() + "', " +
            " vl_Meta = " + ed.getVl_Meta() + ", " +
            " vl_Tendencia = " + ed.getVl_Tendencia() + ", " +
            " vl_Realizado = " + ed.getVl_Realizado() + ", " +
            " dm_Congelado = '" + ed.getDm_Congelado() + "', " +
            " dt_Stamp = '" + ed.getDt_stamp() + "', " +
            " usuario_Stamp = '" + ed.getUsuario_Stamp() + "', " +
            " dm_Stamp = '" + ed.getDm_Stamp() + "' " + 
            " WHERE  " +  
            " oid_Relatorio_Gerencial_Meta = " + ed.getOid_Relatorio_Gerencial_Meta();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Relatorio_Gerencial_MetaED ed)");
        }
    }

    public void deleta(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE " + 
            " FROM " +
            " Relatorios_Gerenciais_Metas " +
            " WHERE " +
            " oid_Relatorio_Gerencial_Meta = " + ed.getOid_Relatorio_Gerencial_Meta();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Relatorio_Gerencial_MetaED ed)");
        }
    }
    
    public ArrayList lista(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT " +
            	  " rg.oid_relatorio_gerencial as rg_oid_relatorio_gerencial , " +
        		  " cd_relatorio_gerencial, " +
        		  "	nm_relatorio_gerencial, " +
        		  " rgg.oid_relatorio_gerencial_grupo as rgg_oid_relatorio_gerencial_grupo, " +
        		  "	cd_relatorio_gerencial_grupo, " +
        		  " nm_relatorio_gerencial_grupo, " +
        		  "	rgc.oid_relatorio_gerencial_conta as rgc_oid_relatorio_gerencial_conta, " +
        		  "	rgc.oid_conta as rgc_oid_conta, " +
        		  "	cd_conta, " +
        		  "	nm_conta, " +
        		  "	cd_estrutural, " +
        		  "	rgc.oid_usuario as rgc_oid_usuario, " +
        		  "	nm_usuario " +
            	  " FROM " +
            	  " relatorios_gerenciais rg, " +
            	  " relatorios_gerenciais_grupos rgg, " +
            	  " relatorios_gerenciais_contas rgc, " +
            	  " usuarios u, " +
            	  " contas c " +
            	  " WHERE 1=1 ";
            	  
            if (ed.getOid_Relatorio_Gerencial() > 0)
                sql += "  AND rg.oid_Relatorio_Gerencial = " + ed.getOid_Relatorio_Gerencial();
	            
        	sql +=" and rg.oid_relatorio_gerencial = rgg.oid_relatorio_gerencial and " +
        		  " rgg.oid_relatorio_gerencial_grupo = rgc.oid_relatorio_gerencial_grupo and " +
        		  "	rgc.oid_usuario = u.oid_usuario  and " +
        		  " rgc.oid_conta = c.oid_conta " + 
                  " ORDER BY " +
                  " cd_relatorio_gerencial, " +
                  " cd_relatorio_gerencial_grupo, " +
                  " cd_estrutural";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Relatorio_Gerencial_MetaED edVolta = new Relatorio_Gerencial_MetaED();

                edVolta.setOid_Relatorio_Gerencial_Meta(0);
                edVolta.setTx_Mes_Ano("");
                edVolta.setVl_Meta(0);
                edVolta.setVl_Meta_TX("0,00");
                edVolta.setVl_Tendencia(0);
                edVolta.setVl_Tendencia_TX("0,00");
                edVolta.setVl_Realizado(0);
                edVolta.setVl_Realizado_TX("0,00");
                edVolta.setDm_Congelado("F");
                edVolta.setOid_Relatorio_Gerencial(res.getLong("rg_oid_relatorio_gerencial"));
                edVolta.setOid_Relatorio_Gerencial_Conta(res.getLong("rgc_oid_relatorio_gerencial_conta"));
      		    edVolta.setOid_Conta(res.getLong("rgc_oid_conta"));
    		    edVolta.setCd_Relatorio_Gerencial(res.getString("cd_relatorio_gerencial"));
    		    edVolta.setNm_Relatorio_Gerencial(res.getString("nm_relatorio_gerencial"));
    		    edVolta.setOid_Relatorio_Gerencial_Grupo(res.getLong("rgg_oid_relatorio_gerencial_grupo"));
    		    edVolta.setCd_Relatorio_Gerencial_Grupo(res.getString("cd_relatorio_gerencial_grupo"));
    		    edVolta.setNm_Relatorio_Gerencial_Grupo(res.getString("nm_relatorio_gerencial_grupo")); 
    		    edVolta.setOid_Relatorio_Gerencial_Conta(res.getLong("rgc_oid_relatorio_gerencial_conta"));
    		    edVolta.setOid_Conta(res.getLong("rgc_oid_conta"));
    		    edVolta.setCd_Conta(res.getString("cd_conta"));
    		    edVolta.setNm_Conta(res.getString("nm_conta"));
    		    edVolta.setCd_Estrutural(res.getString("cd_estrutural"));
    		    edVolta.setOid_Usuario(res.getLong("rgc_oid_usuario"));
    		    edVolta.setNm_Usuario(res.getString("nm_usuario"));                
        		list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Relatorio_Gerencial_MetaED ed)");
        }
    }

    public ArrayList listaMeta(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {
            sql = " SELECT * " +
            	  " FROM " +
            	  " Relatorios_Gerenciais_Metas " +
            	  " WHERE 1=1 ";
            	  
            if (ed.getOid_Relatorio_Gerencial_Conta() > 0)
                sql += "  AND oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta();
	            
            sql += " ORDER BY " +
            " oid_Relatorio_Gerencial_Meta ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Relatorio_Gerencial_MetaED edVolta = new Relatorio_Gerencial_MetaED();
                populaED(edVolta, res);
        		list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Relatorio_Gerencial_MetaED ed)");
        }
    }

    public Relatorio_Gerencial_MetaED getByRecord(Relatorio_Gerencial_MetaED ed) throws Excecoes {
	    String sql = null;
	    Relatorio_Gerencial_MetaED edQBR = new Relatorio_Gerencial_MetaED();
	    try {
    		sql = " SELECT * " +
  	  		  	  " FROM " +
		      	  " Relatorios_Gerenciais_Metas " +
		      	  " WHERE 1=1 ";
			if (ed.getOid_Relatorio_Gerencial_Meta() > 0 ) {
  	  			sql +=" and oid_Relatorio_Gerencial_Meta = " + ed.getOid_Relatorio_Gerencial_Meta();
			} else {
				if (ed.getOid_Relatorio_Gerencial_Conta() > 0 && doValida(ed.getTx_Mes_Ano())){
					sql +=" and oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta() + " " +
						  " and oid_Unidade = " + ed.getOid_Unidade() + 	
						  " and tx_Mes_Ano = '" + ed.getTx_Mes_Ano() + "' ";
					}
			}
            sql += " ORDER BY " +
            " oid_Relatorio_Gerencial_Meta ";
				
        	ResultSet res = null;
        	res = this.executasql.executarConsulta(sql);
        	while (res.next()){
        		populaED(edQBR, res);
    		}
    	}
	    catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Relatorio_Gerencial_MetaED ed)");
	    }
	    return edQBR;
    }

    public Relatorio_Gerencial_MetaED getMeta(Relatorio_Gerencial_MetaED ed) throws Excecoes {
	    String sql = null;
	    Relatorio_Gerencial_MetaED edQBR = new Relatorio_Gerencial_MetaED();
	    try {
    		sql = " SELECT  " +
    			  " oid_relatorio_gerencial_conta, " +
    			  " sum(vl_meta) as vl_Meta, " +
    			  " sum(vl_Tendencia) as vl_Tendencia, " +
    			  "	sum(vl_realizado) as vl_realizado " +
  	  		  	  " FROM " +
		      	  " Relatorios_Gerenciais_Metas " +
		      	  " WHERE 1=1 " +
		      	  " and oid_Relatorio_Gerencial_Conta = " + ed.getOid_Relatorio_Gerencial_Conta() + " " +
				  " and tx_Mes_Ano = '" + ed.getTx_Mes_Ano() + "' ";
    			  if ( ed.getOid_Unidade() > 0 )
    				  sql +=" and oid_Unidade = " + ed.getOid_Unidade() ;
            sql +=" Group by " +
            	  " oid_relatorio_gerencial_conta ";
				
        	ResultSet res = null;
        	res = this.executasql.executarConsulta(sql);
        	while (res.next()){
        		edQBR.setOid_Relatorio_Gerencial_Conta(res.getInt("oid_Relatorio_Gerencial_Conta"));
        		edQBR.setVl_Meta(res.getDouble("vl_Meta"));
        		edQBR.setVl_Tendencia(res.getDouble("vl_Tendencia"));
        		edQBR.setVl_Realizado(res.getDouble("vl_Realizado"));
    		}
    	}
	    catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Relatorio_Gerencial_MetaED ed)");
	    }
	    return edQBR;
    }

	protected void populaED(Relatorio_Gerencial_MetaED ed, ResultSet res) throws SQLException {
        ed.setOid_Relatorio_Gerencial_Meta(res.getInt("oid_Relatorio_Gerencial_Meta"));
        ed.setOid_Relatorio_Gerencial(res.getInt("oid_Relatorio_Gerencial"));
        ed.setOid_Unidade(res.getInt("oid_Unidade"));
        ed.setOid_Relatorio_Gerencial_Conta(res.getInt("oid_Relatorio_Gerencial_Conta"));
        ed.setTx_Mes_Ano(res.getString("tx_Mes_Ano"));
        ed.setVl_Meta(res.getDouble("vl_Meta"));
        ed.setVl_Meta_TX(FormataValor.formataValorBT(res.getDouble("vl_Meta"), 2));
        ed.setVl_Tendencia(res.getDouble("vl_Tendencia"));
        ed.setVl_Tendencia_TX(FormataValor.formataValorBT(res.getDouble("Vl_Tendencia"), 2));
        ed.setVl_Realizado(res.getDouble("vl_Realizado"));
        ed.setVl_Realizado_TX(FormataValor.formataValorBT(res.getDouble("Vl_Realizado"), 2));
        ed.setDm_Congelado(res.getString("dm_Congelado"));
	}

}