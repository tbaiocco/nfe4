/*
 * Created on 15/10/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.SegmentoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class SegmentoBD extends BancoUtil{

    private ExecutaSQL executasql;

    public SegmentoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public SegmentoED inclui(SegmentoED ed) throws Excecoes {

        String sql = null;

        try {
            
            ed.setOid_Segmento(getAutoIncremento("oid_Segmento", "Segmentos"));  
            
            sql = "insert into Segmentos ("
                + "oid_Segmento, cd_Segmento, nm_Segmento) values ("
                + ed.getOid_Segmento() +",'" + ed.getCd_Segmento() + "','" + ed.getNm_Segmento() + "')";
                
            executasql.executarUpdate(sql);
        
            return ed;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Segmento");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(SegmentoED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " update Segmentos set ";
            sql += " cd_Segmento  = '" + ed.getCd_Segmento() + "', ";
            sql += " nm_Segmento = '" + ed.getNm_Segmento() + "'";
            sql += " where oid_Segmento = " + ed.getOid_Segmento();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Segmento");
            excecoes.setMetodo("altera(SegmentoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(SegmentoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " delete from Segmentos " +
            	  " where oid_Segmento = " + ed.getOid_Segmento();
            
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Segmento");
            excecoes.setMetodo("deleta(SegmentoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public ArrayList lista(SegmentoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select oid_Segmento, cd_Segmento, nm_Segmento " +
            	  " from Segmentos" +
            	  " order by cd_Segmento";
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                SegmentoED edVolta = new SegmentoED();
          
                edVolta.setOid_Segmento(res.getInt("oid_Segmento"));
                edVolta.setCd_Segmento(res.getString("cd_Segmento"));
                edVolta.setNm_Segmento(res.getString("nm_Segmento"));

                list.add(edVolta);
            }            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Segmento - SQL=" + sql);
            excecoes.setMetodo("lista(SegmentoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }
        
    public SegmentoED getByOidSegmento(int oid_Segmento) throws Excecoes {

        String sql = null;

        SegmentoED edVolta = new SegmentoED();
        try {

        	sql  = " select oid_Segmento, cd_Segmento, nm_Segmento from Segmentos";
        	sql += " where oid_Segmento = " + oid_Segmento;
        	
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Segmento(res.getInt("oid_Segmento"));
                edVolta.setCd_Segmento(res.getString("cd_Segmento"));
                edVolta.setNm_Segmento(res.getString("nm_Segmento"));        

            }
           return edVolta;
        
        } catch(Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByOidSegmento(SegmentoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public SegmentoED getByCdSegmento(String cd_Segmento) throws Excecoes {

        String sql = null;

        SegmentoED edVolta = new SegmentoED();
        try {

        	sql  = " select oid_Segmento, cd_Segmento, nm_Segmento from Segmentos";
        	sql += " where cd_Segmento = '" + cd_Segmento +"'";
                  ResultSet  res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_Segmento(res.getInt("oid_Segmento"));
                edVolta.setCd_Segmento(res.getString("cd_Segmento"));
                edVolta.setNm_Segmento(res.getString("nm_Segmento"));        

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByCdSegmento(SegmentoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }
    
    //*** RELATÓRIOS
    /*public void RelSegmento(HttpServletResponse response) throws Exception {

        String sql = null;
        ArrayList lista = new ArrayList();

        try{

            sql  = " select oid_Segmento, cd_Segmento, nm_Segmento from Segmentos";
            sql += " where oid_Segmento > 0";
            sql += " order by cd_Segmento";

            ResultSet res = this.executasql.executarConsulta(sql);
            
            SegmentoRelED ed = new SegmentoRelED();

    		while (res.next()){
    			ed = new SegmentoRelED();
    			ed.setOid_Segmento(res.getDouble("oid_Segmento"));
    			ed.setCd_Segmento(res.getString("cd_Segmento"));
    			ed.setNm_Segmento(res.getString("nm_Segmento"));
   			
    			lista.add(ed);   
    		}

            new SegmentoRL().geraRelSegmento(res, lista, response);

        }catch(Exception exc){
            Excecoes exce = new Excecoes();
            exce.setExc(exc);
            exce.setMensagem("Erro no médo listar");
            exce.setClasse(this.getClass().getName());
        }
      }*/
}