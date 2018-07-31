package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Motivo_DevolucaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Motivos de Devolução dos Cheques
 * @serialData 24/08/2005
 */
public class Motivo_DevolucaoBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Motivo_DevolucaoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Motivo_DevolucaoED inclui(Motivo_DevolucaoED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Motivo_Devolucao(getAutoIncremento("oid_Motivo_Devolucao", "Motivos_Devolucoes"));  
            
            sql = " INSERT INTO Motivos_Devolucoes (" +
            	  "		 oid_Motivo_Devolucao" +
            	  "		,CD_Motivo_Devolucao" +
            	  "		,NM_Motivo_Devolucao) " +
            	  " VALUES (" +
            	  	ed.getOid_Motivo_Devolucao() +
            	  	",'" + ed.getCD_Motivo_Devolucao() + "'" +
            	  	",'" + ed.getNM_Motivo_Devolucao() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void altera(Motivo_DevolucaoED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Motivos_Devolucoes SET ";
            sql += " 	CD_Motivo_Devolucao = '" + ed.getCD_Motivo_Devolucao() + "'" +
            	   "   ,NM_Motivo_Devolucao = '"+ed.getNM_Motivo_Devolucao()+"'";
            sql += " WHERE oid_Motivo_Devolucao = " + ed.getOid_Motivo_Devolucao();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            	"altera()");
        }
    }

    public void deleta(Motivo_DevolucaoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Motivos_Devolucoes " +
            	  " WHERE oid_Motivo_Devolucao = " + ed.getOid_Motivo_Devolucao();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Motivo_DevolucaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Motivos_Devolucoes " +
            	  " WHERE 1 = 1 ";
            if (ed.getOid_Motivo_Devolucao() > 0)
                sql += " AND oid_Motivo_Devolucao = "+ed.getOid_Motivo_Devolucao();
            if (doValida(ed.getCD_Motivo_Devolucao()))
                sql += " AND CD_Motivo_Devolucao = '"+ed.getCD_Motivo_Devolucao()+"'";
            if (doValida(ed.getNM_Motivo_Devolucao()))
                sql += " AND NM_Motivo_Devolucao LIKE '"+ed.getNM_Motivo_Devolucao()+"%'";
            
            sql +=" ORDER BY CD_Motivo_Devolucao";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
            	
                Motivo_DevolucaoED edVolta = new Motivo_DevolucaoED();
                edVolta.setOid_Motivo_Devolucao(res.getInt("oid_Motivo_Devolucao"));
                edVolta.setCD_Motivo_Devolucao(res.getString("CD_Motivo_Devolucao"));
                edVolta.setNM_Motivo_Devolucao(res.getString("NM_Motivo_Devolucao"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
        return list;
    }
        
    public Motivo_DevolucaoED getByRecord(Motivo_DevolucaoED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            if (iterator.hasNext())
                return (Motivo_DevolucaoED) iterator.next();
            else return new Motivo_DevolucaoED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }
}