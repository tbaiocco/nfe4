package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.EntregadorED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 */
public class EntregadorBD extends BancoUtil {

    private ExecutaSQL executasql;

    public EntregadorBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public EntregadorED inclui(EntregadorED ed) throws Excecoes {

        String sql = null;
        try {            
            ed.setOid_Entregador(getAutoIncremento("oid_Entregador", "Entregadores"));  
            
            sql = " INSERT INTO Entregadores (" +
            	  "		 oid_Entregador" +
            	  "		,oid_Pessoa" +
                  "     ,oid_Veiculo" +
            	  "		,cd_Entregador) " +
            	  " VALUES (" +
            	  	ed.getOid_Entregador() +
            	  	",'" + ed.getOid_Pessoa() + "'" +
                    ",'" + ed.getOid_Veiculo() + "'" +
            	  	",'" + ed.getCD_Entregador() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(EntregadorED ed) throws Excecoes {

        String sql = null;
        try {
            sql =  " UPDATE Entregadores SET ";
            sql += "     cd_Entregador = '" + ed.getCD_Entregador() + "'";
            sql += "    ,oid_Veiculo = '" + ed.getOid_Veiculo() + "'";
            sql += " WHERE oid_Entregador = " + ed.getOid_Entregador();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(EntregadorED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Entregadores " +
            	  " WHERE oid_Entregador = " + ed.getOid_Entregador();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(EntregadorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT Entregadores.oid_Entregador" +
            	  "		  ,Entregadores.oid_Pessoa" +
                  "       ,Entregadores.oid_Veiculo" +
            	  "		  ,Entregadores.CD_Entregador" +
            	  " FROM Entregadores" +
            	  " WHERE 1 = 1";
            if (ed.getOid_Entregador() > 0)
                sql += "   AND Entregadores.oid_Entregador = " + ed.getOid_Entregador();    
            if (doValida(ed.getOid_Pessoa()))
                sql += "   AND Entregadores.oid_Pessoa = '" + ed.getOid_Pessoa() +"'";
            if (doValida(ed.getCD_Entregador()))
                sql += "   AND Entregadores.CD_Entregador = '" + ed.getCD_Entregador() +"'";
            if (doValida(ed.getNM_Entregador()))
                sql+=" AND Pessoas.NM_Razao_Social LIKE '"+ed.getNM_Entregador()+"%'";
            sql+=" ORDER BY Entregadores.CD_Entregador";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                EntregadorED edVolta = new EntregadorED();
          
                edVolta.setOid_Entregador(res.getInt("oid_Entregador"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
                edVolta.setCD_Entregador(res.getString("CD_Entregador"));
                edVolta.setNM_Entregador(getTableStringValue("NM_Razao_Social","Pessoas","oid_Pessoa = '"+edVolta.getOid_Pessoa()+"'"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }
        
    public EntregadorED getByRecord(EntregadorED ed) throws Excecoes {

        try {
            Iterator it = this.lista(ed).iterator();
            if (it.hasNext())
                return (EntregadorED) it.next();
            else return new EntregadorED();
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }
}