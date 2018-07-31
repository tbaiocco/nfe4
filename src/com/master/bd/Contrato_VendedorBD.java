/*
 * Created on 24/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Contrato_VendedorED;
import com.master.ed.Contrato_VendedorRelED;
import com.master.ed.PessoaED;
import com.master.ed.Produto_VendedorED;
import com.master.rl.Contrato_VendedorRL;
import com.master.rn.PessoaRN;
import com.master.rn.Produto_VendedorRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Contrato_VendedorBD extends BancoUtil{

    private ExecutaSQL executasql;
    private SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

    public Contrato_VendedorBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Contrato_VendedorED inclui(Contrato_VendedorED ed) throws Excecoes {

        String sql = null;

        try {
            
            ed.setOid_Contrato_Vendedor(getAutoIncremento("oid_Contrato_Vendedor", "Contratos_Vendedores"));
       	  	        		
            sql = " insert into Contratos_Vendedores (" +
            	  "			oid_Contrato_Vendedor," +
            	  "			oid_Vendedor," +
                  "         oid_Pessoa," +
            	  "			DM_Tipo_Contrato," +
            	  "			NR_Contrato," +
            	  "			NR_Folha," +
            	  "			DT_Contrato," +
            	  "			DT_Inicial," +
            	  "			DT_Final," +
            	  "			TX_Observacao) values (" +
            	  ed.getOid_Contrato_Vendedor() + ",'" +
            	  ed.getOid_Vendedor() + "','" +
                  ed.getOid_Pessoa() + "','" +
            	  ed.getDM_Tipo_Contrato() + "','" +
            	  ed.getNR_Contrato() + "'," +
            	  ed.getNR_Folha() + ",";
            	  if (ed.getDT_Contrato() != null)
                      sql += "'" +ed.getDT_Contrato()+ "',";
                  else sql += "null,";
            	  if (ed.getDT_Inicial() != null)
                      sql += "'" +ed.getDT_Inicial()+ "',";
                  else sql += "null,";
            	  if (ed.getDT_Final() != null)
                      sql += "'" +ed.getDT_Final()+ "',";
                  else sql += "null,";
            	  sql += "'"+ed.getTX_Observacao().trim() + "')";

            executasql.executarUpdate(sql);
            
            //*** Atualiza dados em Produtos_Vendedores
            Produto_VendedorED edProduto = new Produto_VendedorED();
            edProduto.setOid_Contrato_Vendedor(ed.getOid_Contrato_Vendedor());
            edProduto.setOid_Vendedor(ed.getOid_Vendedor());
            edProduto.setOid_Pessoa(ed.getOid_Pessoa());

            edProduto.setDT_Inclusao(ed.getDT_Contrato());
            edProduto.setDT_Inicial(ed.getDT_Inicial());
            edProduto.setDT_Final(ed.getDT_Final());
            if (ed.getDM_Tipo_Contrato().toUpperCase().equals("ANEXO"))
                edProduto.setTipo(Produto_VendedorED.TIPO_ANEXO);
            else edProduto.setTipo(Produto_VendedorED.TIPO_ADENDO);
            edProduto.setOperacao(Produto_VendedorED.OP_INCLUIR);
            
            new Produto_VendedorRN().GravaContrato(edProduto);
            
            return ed;
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Contrato_Vendedor");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Contrato_VendedorED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " DELETE FROM Contratos_Vendedores " +
            	  " WHERE oid_Contrato_Vendedor = " + ed.getOid_Contrato_Vendedor();

            executasql.executarUpdate(sql);
            
            //*** DELETA o produtos relacionados ao CONTRATO em Produtos_Vendedores
            new Produto_VendedorBD(executasql).deleta(new Produto_VendedorED(ed.getOid_Contrato_Vendedor()));
            
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Contrato_Vendedor");
            excecoes.setMetodo("deleta(Contrato_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public Contrato_VendedorED getByRecord(Contrato_VendedorED ed) throws Excecoes {

        String sql = null;
        Contrato_VendedorED edVolta = new Contrato_VendedorED();

        try {

            sql = " select oid_Contrato_Vendedor," +
            	  "		   oid_Vendedor," +
            	  "        DM_Tipo_Contrato," +
            	  "	       NR_Contrato," +
            	  "        NR_Folha," +
            	  " 	   DT_Contrato," +
            	  "		   DT_Inicial," +
            	  "		   DT_Final," +
            	  "		   TX_Observacao," +
            	  "		   oid_Pessoa" +
            	  " from Contratos_Vendedores " +
            	  " where oid_Contrato_Vendedor = "+ed.getOid_Contrato_Vendedor();
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                edVolta.setOid_Contrato_Vendedor(res.getInt("oid_Contrato_Vendedor"));
                edVolta.setOid_Vendedor(res.getString("oid_Vendedor"));
                edVolta.setDM_Tipo_Contrato(res.getString("DM_Tipo_Contrato"));
                edVolta.setNR_Contrato(res.getString("NR_Contrato"));
                edVolta.setNR_Folha(res.getInt("NR_Folha"));
                edVolta.setDT_Contrato(res.getDate("DT_Contrato"));
                edVolta.setDT_Inicial(res.getDate("DT_Inicial"));
                edVolta.setDT_Final(res.getDate("DT_Final"));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));

            }            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Contrato_Vendedor");
            excecoes.setMetodo("lista(Contrato_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return edVolta;
    }
    
    public ArrayList lista(Contrato_VendedorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " select oid_Contrato_Vendedor," +
            	  "		   oid_Vendedor," +
            	  "        DM_Tipo_Contrato," +
            	  "	       NR_Contrato," +
            	  "        NR_Folha," +
            	  " 	   DT_Contrato," +
            	  "		   DT_Inicial," +
            	  "		   DT_Final," +
            	  "		   TX_Observacao," +
                  "        oid_Pessoa" +
            	  " from Contratos_Vendedores" +
            	  " where oid_vendedor = '"+ed.getOid_Vendedor()+"'";

            if (doValida(ed.getOid_Vendedor())){
                sql += " and oid_Vendedor = '" + ed.getOid_Vendedor()+ "'";
            }
            if (doValida(ed.getDM_Tipo_Contrato())){
                sql += " and DM_Tipo_Contrato = '" + ed.getDM_Tipo_Contrato()+ "'";
            }
            sql += " order by NR_Contrato";
            
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Contrato_VendedorED edVolta = new Contrato_VendedorED();
          
                edVolta.setOid_Contrato_Vendedor(res.getInt("oid_Contrato_Vendedor"));
                edVolta.setOid_Vendedor(res.getString("oid_Vendedor"));
                edVolta.setDM_Tipo_Contrato(res.getString("DM_Tipo_Contrato"));
                edVolta.setNR_Contrato(res.getString("NR_Contrato"));
                edVolta.setNR_Folha(res.getInt("NR_Folha"));
                edVolta.setDT_Contrato(res.getDate("DT_Contrato"));
                edVolta.setDT_Inicial(res.getDate("DT_Inicial"));
                edVolta.setDT_Final(res.getDate("DT_Final"));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                
                list.add(edVolta);
            }            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Contrato_Vendedor");
            excecoes.setMetodo("lista(Contrato_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }
    
    //*** RELATÓRIOS
    public void RelContrato_Vendedor_ANEXO(HttpServletResponse response, Contrato_VendedorRelED ed) throws Exception {

        String sql = null;
        ArrayList lista = new ArrayList();
        ResultSet res = null;
        
        try {

            sql = " select pes.nm_razao_social," +
                  "   	  pro.cd_produto," +
                  "		  pro.nm_produto," +
                  "		  pv.pe_comissao," +
                  "       cv.dt_contrato" +
                  " from produtos_vendedores pv," + 
                  "		  produtos pro," +
                  "		  contratos_vendedores cv," + 
                  "		  pessoas pes" +
                  " where pv.oid_vendedor = cv.oid_vendedor" +
                  "		  and pv.oid_vendedor = pes.oid_pessoa" + 
                  "		  and pv.oid_contrato_vendedor = cv.oid_contrato_vendedor" +
                  "		  and pv.oid_produto = pro.oid_produto" +
                  "		  and pv.dt_exclusao is null" +
                  "		  and cv.oid_Contrato_Vendedor = " + ed.getOid_contrato_vendedor() +
                  "		  order by pro.cd_produto ";            	

            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Contrato_VendedorRelED edVolta = new Contrato_VendedorRelED();
          
                edVolta.setNm_razao_social(res.getString("Nm_razao_social"));
                edVolta.setCd_produto(res.getString("Cd_produto"));
                edVolta.setNm_produto(res.getString("Nm_produto"));
                edVolta.setPe_comissao(res.getDouble("Pe_comissao"));
                
                ed.setDt_contrato(res.getDate("dt_contrato"));
                
                lista.add(edVolta);
            }
            
            //*** Se não retornou itens então busca o Cód. e Nome do vendedor
            if (lista.size() <= 0){
                PessoaED edPessoa = new PessoaRN().getByRecord(new PessoaED(ed.getOid_vendedor()));                
                Contrato_VendedorRelED edVolta = new Contrato_VendedorRelED();
                edVolta.setNm_razao_social(edPessoa.getNM_Razao_Social());
                edVolta.setCd_produto("  - - -  ");
                edVolta.setNm_produto("  - - - - - - - - - - - - -  ");
                edVolta.setOperacao("NENHUM");
                
                lista.add(edVolta);                
            }
            
            //*** Agrupa mix do vendedor
            sql = " select cd_mix " +          	  		  
      	  		  " from mix m, mix_vendedores mv" +            		
      	  		  " where m.oid_mix = mv.oid_mix" +
      	  		  "   and oid_vendedor = '" + ed.getOid_vendedor() + "'"+
            	  " order by cd_mix";	
            
            res = this.executasql.executarConsulta(sql);
            String Mixes = "";
            while (res.next()){
                if (!Mixes.equals(""))
                    Mixes = Mixes + "/";
                Mixes = Mixes + res.getString("cd_mix");
            }
            ed.setMix(Mixes);
            ed.setRelatorio("ANEXO");

            new Contrato_VendedorRL().geraRelContrato(lista, ed, response);

        }catch(Exception exc){
            Excecoes exce = new Excecoes();
            exce.setExc(exc);
            exce.setMensagem("Erro ao carregar relatório! [Contrato_Vendedor]");
            exce.setClasse(this.getClass().getName());
        }
    }    
    
    public void RelContrato_Vendedor_ADENDO(HttpServletResponse response, Contrato_VendedorRelED ed) throws Exception {

        String sql = null;
        ArrayList lista = new ArrayList();
        ResultSet res = null;
        
        try {
            
            //*** DATA CONTRATO
            sql = " select dt_contrato" +
                  " from contratos_vendedores " +
                  " where oid_vendedor = '"+ ed.getOid_vendedor()+ "'"+
                  "   and dm_tipo_contrato = 'ANEXO'";
            res = this.executasql.executarConsulta(sql);      
            //popula
            while (res.next()) {    
                ed.setDt_contrato(res.getDate("dt_contrato"));
            }
            
            //*** Carrega os dados do ADENDO...
             Contrato_VendedorED edContrato = getByRecord(new Contrato_VendedorED(ed.getOid_contrato_vendedor()));
            
            ed.setNR_Contrato(edContrato.getNR_Contrato());
            ed.setNR_Folha(new Integer(edContrato.getNR_Folha()));
            ed.setDT_Inicial(data.format(edContrato.getDT_Inicial()));
            ed.setDT_Final(data.format(edContrato.getDT_Final()));            

            //*** Busca os dados para o relatório..
            sql = " select pes.nm_razao_social," +
                  "   	  pro.cd_produto," +
                  "		  pro.nm_produto," +
                  "		  pv.pe_comissao," +
                  "		  pv.dt_exclusao," +
                  "		  pv.dt_alteracao," +
                  "		  pv.dt_inclusao" +
                  " from produtos_vendedores pv," + 
                  "		  produtos pro," +
                  "		  pessoas pes" +
                  "	where pv.oid_vendedor = pes.oid_pessoa" + 
                  "		  and pv.oid_produto = pro.oid_produto" +
                  "    	  and pv.oid_Contrato_Vendedor = " + ed.getOid_contrato_vendedor() +
                  "	order by pro.cd_produto ";

            res = this.executasql.executarConsulta(sql);
            
            //popula
            while (res.next())
            {
                Contrato_VendedorRelED edVolta = new Contrato_VendedorRelED();
          
                edVolta.setNm_razao_social(res.getString("Nm_razao_social"));
                edVolta.setCd_produto(res.getString("Cd_produto"));
                edVolta.setNm_produto(res.getString("Nm_produto"));
                edVolta.setPe_comissao(res.getDouble("Pe_comissao"));
                if (res.getDate("dt_exclusao") != null)
                    edVolta.setOperacao("EXCLUÍDOS");
                else if (res.getDate("dt_alteracao") != null)
                    edVolta.setOperacao("ALTERADOS");
                else if (res.getDate("dt_inclusao") != null)
                    edVolta.setOperacao("INCLUÍDOS");
                else throw new Excecoes("ERRO! OPERACAO não setada!");
        
                lista.add(edVolta);
            }
            
            //*** Se não retornou itens então busca o Cód. e Nome do vendedor
            if (lista.size() <= 0){
                PessoaED edPessoa = new PessoaRN().getByRecord(new PessoaED(ed.getOid_vendedor()));                
                Contrato_VendedorRelED edVolta = new Contrato_VendedorRelED();
                edVolta.setNm_razao_social(edPessoa.getNM_Razao_Social());
                edVolta.setCd_produto("  - - -  ");
                edVolta.setNm_produto("  - - - - - - - - - - - - -  ");
                edVolta.setOperacao("NENHUM");
                
                lista.add(edVolta);                
            }
            
            //*** Agrupa mix do vendedor
            sql = " select cd_mix " +          	  		  
      	  		  " from mix m, mix_vendedores mv" +            		
      	  		  " where m.oid_mix = mv.oid_mix" +
      	  		  "   and oid_vendedor = '" + ed.getOid_vendedor() + "'"+
            	  " order by cd_mix";	
            
            ResultSet res2 = this.executasql.executarConsulta(sql);
            String Mixes = "";
            while (res2.next()){
                if (!Mixes.equals(""))
                    Mixes = Mixes + "/";
                Mixes = Mixes + res2.getString("cd_mix");
            }
            ed.setMix(Mixes);
            ed.setRelatorio("ADENDO");            
            
            /** Lista Ordenada
            // Este algorítmo serve para ordenar uma lista, a classe 
            // que implementa Comparator deve implementar o método compare, 
            // que "ensina" como comparar os objetos da lista para fazer a ordenação */
            Collections.sort(lista, new Comparator(){
                public int compare(Object o1, Object o2) {
                    if (o1 instanceof Contrato_VendedorRelED
                        && o2 instanceof Contrato_VendedorRelED) {
                        return ((Contrato_VendedorRelED)o1).getOperacao().compareTo(((Contrato_VendedorRelED)o2).getOperacao());
                    } else return 0;
                }}
            );

            new Contrato_VendedorRL().geraRelContrato(lista, ed, response);
            
        }catch(Exception exc){
            Excecoes exce = new Excecoes();
            exce.setExc(exc);
            exce.setMensagem("Erro ao carregar relatório! [Contrato_Vendedor]");
            exce.setClasse(this.getClass().getName());
            throw exce;
    	}
    }
}