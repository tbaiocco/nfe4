package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Protocolo_ContabilED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Protocolos_Contabeis
 * @serialData 03/2006
 */

public class Protocolo_ContabilBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Protocolo_ContabilBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Protocolo_ContabilED inclui(Protocolo_ContabilED ed) throws Excecoes {

        String sql = null;
        String nm_Arquivo = ed.getNm_Arquivo();
        int sai = 0 ;
        // Acrescenta barras invertidas que são perdidas na gravação do banco de dados.
		do {
			sai = nm_Arquivo.indexOf("\\",sai);
			if (sai > -1 ){
				nm_Arquivo = nm_Arquivo.substring(0,sai) + "\\" + nm_Arquivo.substring(sai,nm_Arquivo.length());
				sai++;sai++;
			}	
		} while (sai > 0);
		//
        
        try {
            ed.setOid_Protocolo_Contabil(getAutoIncremento("oid_Protocolo_Contabil", "Protocolos_Contabeis"));
            sql = "INSERT INTO " +
            	  "Protocolos_Contabeis " +
            	  "( " +
            	  "oid_Protocolo_Contabil, " +
            	  "oid_Origem, " +
            	  "oid_Usuario, " +
            	  "nm_Arquivo, " +
            	  "dt_Data, " +
            	  "tx_Referencia, " +
            	  "dm_Situacao " +
            	  ") " +
            	  "VALUES " +
            	  "( " +
            	  ed.getOid_Protocolo_Contabil() + "," + 
            	  ed.getOid_Origem() +  "," + 
            	  ed.getOid_Usuario() +  ",'" +
            	  nm_Arquivo + "','" +
            	  ed.getDt_Data() + "','" +
            	  ed.getTx_Referencia() + "','" +
            	  "P'" +
            	  ") ";
            executasql.executarUpdate(sql);
        	return ed;
        	
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Protocolo_ContabilED ed)");
        }
    }

    public void altera(Protocolo_ContabilED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  "UPDATE " +
            	   "Protocolos_Contabeis " +
            	   "SET " +
                   "oid_Protocolo_Contabil = oid_Protocolo_Contabil, " +
                   "dm_Situacao = '" + ed.getDm_Situacao()+"', ";
            
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";

            sql += " WHERE oid_Protocolo_Contabil = " + ed.getOid_Protocolo_Contabil();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Protocolo_ContabilED ed)");
        }
    }

    public void deleta(Protocolo_ContabilED ed) throws Excecoes {

        String sql = null;
        try {
            sql = "DELETE " +
            	  "FROM " +
            	  "Protocolos_Contabeis " +
            	  "WHERE " +
            	  "oid_Protocolo_Contabil = " + ed.getOid_Protocolo_Contabil();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Protocolo_ContabilED ed)");
        }
    }
    
    public ArrayList lista(Protocolo_ContabilED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = "SELECT " +
            	  "* " +
            	  "FROM " +
            	  "Protocolos_Contabeis as p, " +
            	  "usuarios as u, " +
            	  "origens as o " +
            	  "WHERE " +
            	  "1=1 " +
            	  "and p.oid_usuario = u.oid_usuario " +
            	  "and p.oid_origem = o.oid_origem " ;
            if (ed.getOid_Protocolo_Contabil() > 0)
                sql += " AND p.oid_Protocolo_Contabil = "+ed.getOid_Protocolo_Contabil();
            else {
	            if (ed.getOid_Origem() > 0)
	                sql += " AND p.oid_Origem = '"+ed.getOid_Origem()+"'";
	            if (doValida(ed.getNm_Arquivo()))
	                sql += "  AND p.nm_Arquivo LIKE '"+ed.getNm_Arquivo()+"%'";
	            if (doValida(ed.getDt_Inicial()))
	            	sql += " and p.dt_data >= '" + ed.getDt_Inicial()+"'"; 
	            if (doValida(ed.getDt_Final()))
	            	sql += " and p.dt_data <= '" + ed.getDt_Final()+"'"; 
	            	
            }
            sql += " ORDER BY " +
            	   " p.dt_Data, " +
            	   " oid_Protocolo_Contabil";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Protocolo_ContabilED edVolta = new Protocolo_ContabilED();
          
                edVolta.setOid_Protocolo_Contabil(res.getInt("oid_Protocolo_Contabil"));
                edVolta.setOid_Origem(res.getInt("oid_Origem"));
                edVolta.setOid_Usuario(res.getInt("oid_Usuario"));
                edVolta.setNm_Arquivo(res.getString("nm_Arquivo"));
                edVolta.setTx_Referencia(res.getString("tx_Referencia"));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
                edVolta.setDt_Data(FormataData.formataDataBT(res.getString("dt_Data")));
                edVolta.setNm_Origem(res.getString("nm_Origem"));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Protocolo_ContabilED ed)");
        }
    }

    public Protocolo_ContabilED getByRecord(Protocolo_ContabilED ed) throws Excecoes {

	    String sql = null;

	    Protocolo_ContabilED edQBR = new Protocolo_ContabilED();

	    try{

	    	sql = "SELECT " +
	    		  "* " +
	    		  "FROM " +
		      	  "Protocolos_Contabeis as p, " +
		      	  "usuarios as u, " +
		      	  "origens as o " +
		      	  "WHERE " +
		      	  "1=1 " +
		      	  "and p.oid_usuario = p.oid_usuario " +
		      	  "and p.oid_origem = o.oid_origem " +
		      	  "and p.oid_Protocolo_Contabil = " + ed.getOid_Protocolo_Contabil();
		
		  ResultSet res = this.executasql.executarConsulta(sql);
		  while (res.next())
	      {
	          edQBR.setOid_Protocolo_Contabil(res.getInt("oid_Protocolo_Contabil"));
	          edQBR.setOid_Origem(res.getInt("oid_Origem"));
	          edQBR.setOid_Usuario(res.getInt("oid_Usuario"));
	          edQBR.setNm_Arquivo(res.getString("nm_Arquivo"));
	          edQBR.setTx_Referencia(res.getString("tx_Referencia"));
	          edQBR.setDm_Situacao(res.getString("dm_Situacao"));
	          edQBR.setDt_Data(res.getString("dt_Data"));
	          edQBR.setNm_Origem(res.getString("nm_Origem"));
	          edQBR.setNm_Usuario(res.getString("nm_Usuario"));
	      }
	    }
	      catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(SistemaED ed)");
	      }

	    return edQBR;
    }
}