package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.master.ed.Ordem_EmbarqueED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andr� Valadas
 */
public class Ordem_EmbarqueBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Ordem_EmbarqueBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Ordem_EmbarqueED inclui(Ordem_EmbarqueED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Ordem_Embarque(getAutoIncremento("oid_Ordem_Embarque", "Ordens_Embarques"));
            ed.setNR_Carga(ed.getOid_Ordem_Embarque());
            
            sql = " INSERT INTO Ordens_Embarques (" +
            	  "		 oid_Ordem_Embarque" +
            	  "		,CD_Ordem_Servico" +
            	  "		,NM_Tecnico" +
            	  "		,NR_Placa" +
            	  "		,NR_Carga" +
            	  "		,DT_Entrega) " +
            	  " VALUES (" +
            	  	ed.getOid_Ordem_Embarque() +
            	  	",'" + ed.getCd_ordem_servico() + "'" +
            	  	",'" + ed.getNm_tecnico() + "'" +
            	  	",'" + ed.getNr_placa() + "'" +
            	  	"," + ed.getNR_Carga() +
            	  	",'"+ ed.getDT_Entrega() + "')";
            
            executasql.executarUpdate(sql);
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void deleta(Ordem_EmbarqueED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Ordens_Embarques " +
            	  " WHERE oid_Ordem_Embarque = " + ed.getOid_Ordem_Embarque();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(Ordem_EmbarqueED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        Map cacheUni = new HashMap();
        Map cacheEnt = new HashMap();
        Map cacheVei = new HashMap();
        ArrayList list = new ArrayList();

        try {
            sql = " SELECT Ordens_Embarques.* " +
            	  " FROM Ordens_Embarques where 1=1" ;
            	  
            if (ed.getOid_Ordem_Embarque() > 0)
                sql += "   AND Ordens_Embarques.oid_Ordem_Embarque = "+ed.getOid_Ordem_Embarque();
            else {
                if (doValida(ed.getCd_ordem_servico()))
                    sql += "   AND Ordens_Embarques.cd_Ordem_Servico = '"+ed.getCd_ordem_servico() + "'";
                if (doValida(ed.getNm_tecnico()))
                    sql += "   AND Ordens_Embarques.nm_Tecnico = '"+ed.getNm_tecnico() + "'";
                if (doValida(ed.getNr_placa()))
                    sql += "   AND Ordens_Embarques.Nr_placa = '"+ed.getNr_placa()+"'";
                if (ed.getNR_Carga() > 0)
                    sql += "   AND Ordens_Embarques.NR_Carga = "+ed.getNR_Carga();
                if (doValida(ed.getDT_Entrega()))
                    sql += "   AND Ordens_Embarques.DT_Entrega = "+getSQLDate(ed.getDT_Entrega());
                sql += " ORDER BY Ordens_Embarques.NR_Carga";
            }
            
            res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                Ordem_EmbarqueED edVolta = new Ordem_EmbarqueED();
          
                edVolta.setOid_Ordem_Embarque(res.getInt("oid_Ordem_Embarque"));
                edVolta.setCd_ordem_servico(res.getString("CD_Ordem_Servico"));
                edVolta.setNm_tecnico(res.getString("NM_Tecnico"));
                edVolta.setNr_placa(res.getString("NR_Placa"));
                edVolta.setNR_Carga(res.getInt("NR_Carga"));
                edVolta.setDT_Entrega(FormataDataBean.getFormatDate(res.getString("DT_Entrega")));
                
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        } finally {
            closeResultset(res);
            cacheUni.clear();
            cacheEnt.clear();
            cacheVei.clear();
            cacheUni = null;
            cacheEnt = null;
            cacheVei = null;
        }
    }
        
    public Ordem_EmbarqueED getByRecord(Ordem_EmbarqueED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Ordem_EmbarqueED) iterator.next();            
        } else return new Ordem_EmbarqueED();
    }
    
}