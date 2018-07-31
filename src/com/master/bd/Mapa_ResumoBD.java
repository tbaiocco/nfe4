package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Mapa_ResumoED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Mapa Resumo PDV
 * @serialData 16/03/2006
 */
public class Mapa_ResumoBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Mapa_ResumoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Mapa_ResumoED inclui(Mapa_ResumoED ed) throws Excecoes {

        String sql = null;
        try {            
            ed.setOid_Mapa_Resumo(getAutoIncremento("oid_Mapa_Resumo", "Mapas_Resumos"));  
            
            sql = " INSERT INTO Mapas_Resumos (" +
            	  "		 oid_Mapa_Resumo" +
            	  "		,oid_Nota_Fiscal" +
                  "     ,DT_Emissao" +
                  "     ,NR_Documento" +
                  "     ,NR_Ultimo_Documento" +
                  "     ,NR_Reducao" +
                  "     ,VL_GT_Inicial" +
                  "     ,VL_GT_Final" +
                  "     ,VL_Cancelamento" +
            	  "		,VL_Desconto) " +
            	  " VALUES (" +
            	  	ed.getOid_Mapa_Resumo() +
            	  	"," + getSQLString(ed.getOid_Nota_Fiscal()) +
                    "," + getSQLDate(ed.getDT_Emissao()) +
                    "," + ed.getNR_Documento() +
                    "," + ed.getNR_Ultimo_Documento() +
                    "," + ed.getNR_Reducao() +
                    "," + ed.getVL_GT_Inicial() +
                    "," + ed.getVL_GT_Final() +
                    "," + ed.getVL_Cancelamento() +
            	  	"," + ed.getVL_Desconto() + ")";
            executasql.executarUpdate(sql);
            
            //*** ATUALIZA DATA/HORA NA NOTA FISCAL
            executasql.executarUpdate(" UPDATE Notas_Fiscais SET " +
                                      "    DT_Emissao = "+getSQLDate(Data.getDataDMY()) +
                                      "   ,HR_Entrada = "+getSQLString(Data.getHoraHM())+
                                      " WHERE oid_Nota_Fiscal = "+getSQLString(ed.getOid_Nota_Fiscal()));
        	return ed;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }
    
    public void altera(Mapa_ResumoED ed) throws Excecoes {

        String sql = null;
        try {            
            sql = " UPDATE Mapas_Resumos SET " +
                  "      DT_Emissao=" +getSQLDate(ed.getDT_Emissao())+
                  "     ,NR_Documento=" +ed.getNR_Documento()+
                  "     ,NR_Ultimo_Documento=" +ed.getNR_Ultimo_Documento()+
                  "     ,NR_Reducao=" +ed.getNR_Reducao()+
                  "     ,VL_GT_Inicial=" +ed.getVL_GT_Inicial()+
                  "     ,VL_GT_Final=" +ed.getVL_GT_Final()+
                  "     ,VL_Cancelamento=" +ed.getVL_Cancelamento()+
                  "     ,VL_Desconto= " +ed.getVL_Desconto()+
                  " WHERE oid_Nota_Fiscal=" +getSQLString(ed.getOid_Nota_Fiscal());
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void deleta(Mapa_ResumoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Mapas_Resumos " +
            	  " WHERE oid_Mapa_Resumo = oid_Mapa_Resumo ";
            if (ed.getOid_Mapa_Resumo() > 0)
                sql += " AND oid_Mapa_Resumo = " + ed.getOid_Mapa_Resumo();
            else if (doValida(ed.getOid_Nota_Fiscal()))
                sql += " AND oid_Nota_Fiscal = "+ getSQLString(ed.getOid_Nota_Fiscal());
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Mapa_ResumoED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Mapas_Resumos " +
            	  " WHERE 1 = 1 ";
            if (ed.getOid_Mapa_Resumo() > 0)
                sql += " AND oid_Mapa_Resumo = "+ed.getOid_Mapa_Resumo();
            else {
                if (doValida(ed.getOid_Nota_Fiscal()))
                    sql += " AND oid_Nota_Fiscal = "+getSQLString(ed.getOid_Nota_Fiscal());
                if (doValida(ed.getDT_Emissao()))
                    sql += " AND DT_Emissao = "+getSQLDate(ed.getDT_Emissao());
            }
            sql +=" ORDER BY DT_Emissao DESC";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Mapa_ResumoED edVolta = new Mapa_ResumoED();
                edVolta.setOid_Mapa_Resumo(res.getInt("oid_Mapa_Resumo"));
                edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edVolta.setDT_Emissao(FormataData.formataDataBT(res.getString("DT_Emissao")));
                edVolta.setNR_Documento(res.getInt("NR_Documento"));
                edVolta.setNR_Ultimo_Documento(res.getInt("NR_Ultimo_Documento"));
                edVolta.setNR_Reducao(res.getInt("NR_Reducao"));
                edVolta.setVL_GT_Inicial(res.getDouble("VL_GT_Inicial"));
                edVolta.setVL_GT_Final(res.getDouble("VL_GT_Final"));
                edVolta.setVL_Cancelamento(res.getDouble("VL_Cancelamento"));
                edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
                lista.add(edVolta);
            }
            return lista;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
    }
        
    public Mapa_ResumoED getByRecord(Mapa_ResumoED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            return iterator.hasNext() ? (Mapa_ResumoED) iterator.next() : new Mapa_ResumoED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }
}