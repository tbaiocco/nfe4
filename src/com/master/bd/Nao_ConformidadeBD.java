
package com.master.bd;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.Parametro_FixoED;
import com.master.ed.Nao_ConformidadeED;




public class Nao_ConformidadeBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Nao_ConformidadeBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Nao_ConformidadeED inclui(Nao_ConformidadeED ed) throws Excecoes {

        String sql = null;
        Nao_ConformidadeED edVolta = new Nao_ConformidadeED();

        try {

            // Auto-incrementa
            edVolta.setOid_nao_conformidade(getAutoIncremento("oid_nao_conformidade","nao_conformidades"));
                            
            sql = "insert into nao_conformidades ("
                + "oid_nao_conformidade, nm_descricao_problema, nm_disposicao_problema, nm_acao_contencao," +
                		"nm_causa, nm_acao_corretiva, nm_verificacao_eficacia, dt_prazo, dt_emissao, oid_cliente_fornecedor, oid_identificador_problema, nm_verificacao_implemantacao) values ("
                + edVolta.getOid_nao_conformidade() + ","
				+  "'" + ed.getNM_descricao_problema() + "'" + ","
				+  "'" + ed.getNM_disposicao_problema() + "'" + ","
				+  "'" + ed.getNM_acao_contencao() + "'" + ","
				+  "'" + ed.getNM_causa() + "'" + ","
				+  "'" + ed.getNM_acao_corretiva() + "'" + ","
				+  "'" + ed.getNM_verificacao_eficacia() + "'" + ","
				+  "'" + ed.getDT_Prazo() + "'" + ","
				+  "'" + ed.getDT_Emissao() + "'" + ","
				+  "'" + ed.getOid_cliente_fornecedor() + "'" + ","
				+  "'" + ed.getOid_identificador_problema() + "'" + ","
				+  "'" + ed.getNM_verificacao_implemantacao() + "'" + ");";
            
            //*** SQL
// System.out.println("SQL incluir >> "+sql);
 
            executasql.executarUpdate(sql);
         
            return edVolta;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Nao_ConformidadeED");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void altera(Nao_ConformidadeED ed) throws Excecoes {

        String sql = null;
        try {         

            sql =  " update nao_conformidades set ";
            sql += " oid_nao_conformidade  = " + ed.getOid_nao_conformidade() + ", ";
            sql += " dt_prazo  = " + ed.getDT_Prazo() + ", ";
            sql += " nm_descricao_problema  = " + ed.getNM_descricao_problema() + ", ";
            sql += " nm_disposicao_problema  = " + ed.getNM_disposicao_problema() + ", ";
            sql += " nm_acao_contencao  = " + ed.getNM_acao_contencao() + ", ";
            sql += " nm_causa  = " + ed.getNM_causa() + ", ";
            sql += " nm_acao_corretiva  = " + ed.getNM_acao_corretiva() + ", ";
            sql += " nm_acao_preventiva  = " + ed.getNM_acao_preventiva() + ", ";
            sql += " nm_verificacao_implemantacao  = " + ed.getNM_verificacao_implemantacao() + ", ";
            sql += " nm_verificacao_eficacia  = " + ed.getNM_verificacao_eficacia() + ", ";
            sql += " oid_cliente_fornecedor  = " + ed.getOid_cliente_fornecedor() + ", ";
            sql += " oid_identificador_problema  = " + ed.getOid_identificador_problema() + ", ";
            sql += " dt_emissao = '" + ed.getDT_Emissao() +"'";
            sql += " where oid_nao_conformidade = " + ed.getOid_nao_conformidade();
            
            executasql.executarUpdate(sql);
         
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Nao_Conformidades");
            excecoes.setMetodo("altera(Nao_ConformidadeED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Nao_ConformidadeED ed) throws Excecoes {

        String sql = null;


        try {

            sql =  " delete from nao_conformidades " +
            	   " where oid_nao_conformidade = " + ed.getOid_nao_conformidade();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Nao_Conformidade");
            excecoes.setMetodo("deleta(Nao_ConformidadeED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    public Nao_ConformidadeED getByOid_Nao_Conformidade(String oid_Nao_Conformidade) throws Excecoes {

        String sql = null;

        Nao_ConformidadeED edVolta = new Nao_ConformidadeED();
        try {

        	sql  = " select oid_nao_conformidade from nao_conformidades";
        	sql += " where oid_nao_conformidade = '" + oid_Nao_Conformidade +"'";
      
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                
            	edVolta.setOid_nao_conformidade(res.getInt("oid_nao_conformidade"));                                       

            }
           return edVolta;
        
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByCdArea_Venda(Area_VendaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }    

	
    public ArrayList lista(Nao_ConformidadeED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql  = " select nao_conformidades.oid_nao_conformidade, Oid_cliente_fornecedor, " +
				   " Oid_identificador_problema, dt_emissao, dt_prazo, p1.nm_razao_social as rz_p1, p2.nm_razao_social as rz_p2 " +
				   " from nao_conformidades, pessoas p1, pessoas p2 " +
				   " where nao_conformidades.Oid_cliente_fornecedor = p1.oid_pessoa " +
				   " and nao_conformidades.Oid_identificador_problema = p2.oid_pessoa ";

            if (ed.getOid_nao_conformidade() >0 ){
                sql += " and oid_nao_conformidade = '" + ed.getOid_nao_conformidade() +"'";
            }
            if (JavaUtil.doValida(ed.getOid_cliente_fornecedor())){
                sql += " and Oid_cliente_fornecedor = '" + ed.getOid_cliente_fornecedor() +"'";
            }
            if (JavaUtil.doValida(ed.getOid_identificador_problema())){
                sql += " and Oid_identificador_problema = '" + ed.getOid_identificador_problema() +"'";
            }
            if (JavaUtil.doValida(ed.getDT_Emissao())){
                sql += " and DT_Emissao = '" + ed.getDT_Emissao() +"'";
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
            	Nao_ConformidadeED edVolta = new Nao_ConformidadeED();
          
                edVolta.setOid_nao_conformidade(res.getInt("oid_nao_conformidade"));
                edVolta.setDT_Emissao(res.getString("dt_emissao"));
                edVolta.setDT_Prazo(res.getString("dt_prazo"));
                
                edVolta.setNM_cliente_fornecedor(res.getString("rz_p1"));
                edVolta.setNM_identificador_problema(res.getString("rz_p2"));
                
                list.add(edVolta);
            }
            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Nao_Conformidade");
            excecoes.setMetodo("lista(Nao_Conformidade)");
            // System.out.println(exc);
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }
}