/*
 * Created on 21/10/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.master.ed.Produto_VendedorED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Produto_VendedorBD extends BancoUtil{

    private ExecutaSQL executasql;
    private int oid_Incremento = 0;

    public Produto_VendedorBD(ExecutaSQL sql) {
        this.executasql = sql;
    }
    
    /*** PRODUTOS - (Produto, Mix_Produto, Mix_Vendedor)*/
    public void GravaProdutos(Produto_VendedorED ed) throws Excecoes {
        
        ArrayList lista = null;
        
        /*** Incluir Quando... 
        //        	  1º Mix_Produto for adicionado ao um produto e o mesmo MIX estiver ligado ao MIX_Vendedor; 
        //    		  2º ou Mix_Vendedor for adicionado;
        //			  3º PRODUTO for liberado;*/ 	
        if (ed.getOperacao().equals(Produto_VendedorED.OP_INCLUIR))
        {
            lista = Lista_RelacaoProdutos(ed);
            for (int i=0; i < lista.size(); i++){
                Produto_VendedorED edLista = (Produto_VendedorED) lista.get(i);
                edLista.setDT_Inclusao(Calendar.getInstance().getTime());
                edLista.setOperacao(ed.getOperacao());
                
                //*** Se ja existe o registro e ainda não está referenciado no Contrato então altera apenas
                if (doExiste("Produtos_Vendedores", 
                             "oid_Vendedor = '"+edLista.getOid_Vendedor()+"'" +
                             " and oid_Produto = '"+edLista.getOid_Produto()+"'" +
                             " and oid_Contrato_Vendedor = 0"))
                    altera(edLista);
                else inclui(edLista, false);
            }
        /*** Alterar - Quando...
        //				1º Somente quando o produto alterar seu % Comissão;*/
        } else if (ed.getOperacao().equals(Produto_VendedorED.OP_ALTERAR) && 
                   ed.getModulo().equals(Produto_VendedorED.MODULO_PRODUTO)){
                
            lista = Lista_RelacaoProdutos(ed);
            for (int i=0; i < lista.size(); i++)
            {
                Produto_VendedorED edLista = (Produto_VendedorED) lista.get(i);                
                edLista.setDT_Alteracao(Calendar.getInstance().getTime());
                edLista.setPE_Comissao(ed.getPE_Comissao());
                edLista.setOperacao(ed.getOperacao());
                
                //*** Se ja existe o registro e ainda não está referenciado no Contrato então altera apenas
                if (doExiste("Produtos_Vendedores", 
                        "oid_Vendedor = '"+edLista.getOid_Vendedor()+"'" +
                        " and oid_Produto = '"+edLista.getOid_Produto()+"'" +
                        " and oid_Contrato_Vendedor = 0"))
                    altera(edLista);
                else inclui(edLista, false);
            }                
        /*** Excluir - Quando...
        //    			1º PRODUTO for bloqueado; 
        //              2º ou Mix_Produto for removido; 
        //    			3º ou Mix_Vendedor for removido;*/
        } else if (ed.getOperacao().equals(Produto_VendedorED.OP_EXCLUIR)){
                
            lista = Lista_RelacaoProdutos(ed);
            for (int i=0; i < lista.size(); i++)
            {
                Produto_VendedorED edLista = (Produto_VendedorED) lista.get(i);
                edLista.setDT_Exclusao(Calendar.getInstance().getTime());
                edLista.setOperacao(ed.getOperacao());
                 
                //*** Se ja existe o registro e ainda não está referenciado no Contrato então altera apenas
                if (doExiste("Produtos_Vendedores", 
                        "oid_Vendedor = '"+edLista.getOid_Vendedor()+"'" +
                        " and oid_Produto = '"+edLista.getOid_Produto()+"'" +
                        " and oid_Contrato_Vendedor = 0"))
                    altera(edLista);
                else inclui(edLista, false);
            }        
        } 
    }
    
    /*** Lista Relações dos Produtos...*/
    private ArrayList Lista_RelacaoProdutos(Produto_VendedorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        
        try {

            sql = " select distinct" +
                  "			ven.oid_vendedor," +   
                  "			pro.oid_produto," +
                  "			pro_cli.pe_comissao" +                   
                  "	from produtos pro," +
                  "			produtos_clientes pro_cli," +
                  "			mix_produtos mix_pro," +
                  "			mix_vendedores mix_ven," +
                  "			vendedores ven" +
                  " where pro.oid_produto = pro_cli.oid_produto" +
                  "			and pro.oid_produto = mix_pro.oid_produto" +
                  "			and mix_pro.oid_mix = mix_ven.oid_mix" +
                  "			and mix_ven.oid_Vendedor = ven.oid_Vendedor" +
                  "			and pro_cli.oid_Pessoa = mix_pro.oid_pessoa_distribuidor";
                  if (ed.getModulo().equals(Produto_VendedorED.MODULO_PRODUTO)){
                      sql += "	and pro.oid_produto = " + ed.getOid_Produto();
                  }else if (ed.getModulo().equals(Produto_VendedorED.MODULO_MIX_PRODUTO)){
                      sql += "	and mix_pro.oid_mix = " + ed.getOid_Mix();
                      sql += "	and pro.oid_produto = " + ed.getOid_Produto();
        		  }else if (ed.getModulo().equals(Produto_VendedorED.MODULO_MIX_VENDEDOR)){
                      sql += "	and ven.oid_vendedor = '" + ed.getOid_Vendedor() + "'" +
                      		 "  and mix_pro.oid_mix = " + ed.getOid_Mix();
                  }
            sql += " order by pro.oid_produto";
            	    
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Produto_VendedorED edVolta = new Produto_VendedorED();

                edVolta.setOid_Vendedor(res.getString("oid_vendedor"));
                edVolta.setOid_Produto(res.getInt("oid_produto"));
                edVolta.setPE_Comissao(res.getDouble("pe_comissao"));
                
                list.add(edVolta);
            }            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Produto_Vendedor");
            excecoes.setMetodo("lista(Produto_Vendedor)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }
    
    /*** CONTRATO - (ANEXO ou ADENDOS)*/
    public void GravaContrato(Produto_VendedorED ed) throws Excecoes {
        
        ArrayList lista = null;
        
        //*** ANEXO - Incluidos
        //*** ADENDO - Incluidos, Alterados, Excluidos
        if (ed.getTipo().equals(Produto_VendedorED.TIPO_ANEXO) || 
            ed.getTipo().equals(Produto_VendedorED.TIPO_ADENDO)){                

            ed.setOperacao(Produto_VendedorED.OP_INCLUIR);
            lista = Lista_ContratoByData(ed);
            for (int i=0; i < lista.size(); i++){
                Produto_VendedorED edLista = (Produto_VendedorED) lista.get(i);
                edLista.setDT_Inclusao(ed.getDT_Inclusao());
                edLista.setOid_Contrato_Vendedor(ed.getOid_Contrato_Vendedor());
                
                inclui(edLista, true);
            }
        } 
        if (ed.getTipo().equals(Produto_VendedorED.TIPO_ADENDO))
        {
            //*** Alterados
            ed.setOperacao(Produto_VendedorED.OP_ALTERAR);
            lista = Lista_ContratoByData(ed);
            for (int i=0; i < lista.size(); i++){
                Produto_VendedorED edLista = (Produto_VendedorED) lista.get(i);                
                edLista.setDT_Inclusao(ed.getDT_Inclusao());
                edLista.setOid_Contrato_Vendedor(ed.getOid_Contrato_Vendedor());
                
                inclui(edLista, true);
            }
            //*** Exluidos
            ed.setOperacao(Produto_VendedorED.OP_EXCLUIR);
            lista = Lista_ContratoByData(ed);
            for (int i=0; i < lista.size(); i++){
                Produto_VendedorED edLista = (Produto_VendedorED) lista.get(i);                
                edLista.setDT_Inclusao(ed.getDT_Inclusao());
                edLista.setOid_Contrato_Vendedor(ed.getOid_Contrato_Vendedor());
                
                inclui(edLista, true);
            }
        }
        
        //*** ERRO...
        if (!ed.getTipo().equals(Produto_VendedorED.TIPO_ANEXO) && 
            !ed.getTipo().equals(Produto_VendedorED.TIPO_ADENDO)){
            // System.err.println("ERRO!! TIPO: "+ed.getTipo()+" não Resolvidos!!"); 
        }
    }
    
    /*** Lista Relações dos Produtos Para Anexos e Adendos...*/
    public ArrayList Lista_ContratoByData(Produto_VendedorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select  oid_Produto_Vendedor," +
            	  "			oid_Produto," +
            	  "			oid_Vendedor," +
            	  "			PE_Comissao," +
            	  "			DT_Inclusao," +
            	  "			DT_Alteracao," +
            	  "			DT_Exclusao," +
            	  "			oid_Contrato_Vendedor" +
            	  " from produtos_vendedores" +
            	  " where oid_vendedor = '" +ed.getOid_Vendedor()+ "'" +
            	  "	  and oid_Contrato_Vendedor = 0 ";
            if (ed.getOperacao().equals(Produto_VendedorED.OP_INCLUIR)){
                if (ed.getTipo().equals(Produto_VendedorED.TIPO_ADENDO))
                    sql += "	and DT_Inclusao between '" +ed.getDT_Inicial()+ "' and '" +ed.getDT_Final()+"'";
                sql += "	and DT_Exclusao is null";
            }else if (ed.getOperacao().equals(Produto_VendedorED.OP_ALTERAR)){
                if (ed.getTipo().equals(Produto_VendedorED.TIPO_ADENDO))
                    sql += "	and DT_Alteracao between '" +ed.getDT_Inicial()+ "' and '" +ed.getDT_Final()+"'";
                sql += "	and DT_Exclusao is null";
  		  	}else if (ed.getOperacao().equals(Produto_VendedorED.OP_EXCLUIR) &&
  		  	          ed.getTipo().equals(Produto_VendedorED.TIPO_ADENDO)){
  		  	        sql += "	and DT_Exclusao between '" +ed.getDT_Inicial()+ "' and '" +ed.getDT_Final()+"'";
            }
            sql += " order by oid_produto";
            	    
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Produto_VendedorED edVolta = new Produto_VendedorED();
          
                edVolta.setOid_Produto_Vendedor(res.getInt("oid_produto_vendedor"));
                edVolta.setOid_Vendedor(res.getString("oid_vendedor"));
                edVolta.setOid_Produto(res.getInt("oid_produto"));
                edVolta.setPE_Comissao(res.getDouble("pe_comissao"));
                edVolta.setDT_Inclusao(res.getDate("DT_Inclusao"));
                edVolta.setDT_Alteracao(res.getDate("DT_Alteracao"));
                edVolta.setDT_Exclusao(res.getDate("DT_Exclusao"));                
                edVolta.setOid_Contrato_Vendedor(res.getInt("oid_Contrato_Vendedor"));
                
                list.add(edVolta);
            }            
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao Lista_AdendoByData Produto_Vendedor");
            excecoes.setMetodo("Lista_AdendoByData(Produto_Vendedor)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    } 
    
    //*** Incluir Produto Vendedor
    private Produto_VendedorED inclui(Produto_VendedorED ed, boolean isContrato) throws Excecoes {

        String sql = null;

        try {
            if (oid_Incremento == 0)
                oid_Incremento = getAutoIncremento("oid_Produto_Vendedor", "Produtos_Vendedores");
            else ++oid_Incremento;
            
            ed.setOid_Produto_Vendedor(oid_Incremento);
            
            sql = " insert into produtos_vendedores (" +
            	  "			oid_produto_vendedor, " +
                  "			oid_produto, " +
                  "			oid_vendedor," +
                  "			PE_Comissao," +
                  "			DT_Inclusao," +
                  "			DT_Alteracao," +
                  "			DT_Exclusao," +
                  "			oid_Contrato_Vendedor) values (" +
                  ed.getOid_Produto_Vendedor() +"," +
                  ed.getOid_Produto() + ",'" +
                  ed.getOid_Vendedor()+ "'," +
                  ed.getPE_Comissao() + ",";
                  if (ed.getDT_Inclusao() != null)
                      sql += "'" +ed.getDT_Inclusao()+ "',";
                  else sql += "null,";
                  if (ed.getDT_Alteracao() != null)
                      sql += "'" +ed.getDT_Alteracao()+ "',";
                  else sql += "null,";    
                  if (ed.getDT_Exclusao() != null)
                      sql += "'" +ed.getDT_Exclusao()+ "',";
                  else sql += "null,";
                  sql += (isContrato == true ? ed.getOid_Contrato_Vendedor() : 0) +")";
 
            executasql.executarUpdate(sql);
            return ed;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem(exc.getMessage());
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    //*** Altera somente para produtos que ainda não estão cadastrados no Contrato(oid_Contrato_Vendedor = 0)
    private void altera(Produto_VendedorED ed) throws Excecoes {
        
        String sql = null;
        try {
            
            sql = " update Produtos_Vendedores set ";
            if ((ed.getOperacao().equals(Produto_VendedorED.OP_INCLUIR)) && (ed.getDT_Inclusao() != null)){
                sql += " DT_Inclusao = '" + ed.getDT_Inclusao() + "'," +                	   
                	   " DT_alteracao = null," + 
                	   " DT_exclusao = null";
            }else if ((ed.getOperacao().equals(Produto_VendedorED.OP_ALTERAR)) && (ed.getDT_Alteracao() != null)){
                sql += " DT_Alteracao = '" + ed.getDT_Alteracao() + "'," +                	   
                	   " PE_Comissao = " + ed.getPE_Comissao() +","+
                	   " DT_inclusao = null," + 
                	   " DT_exclusao = null";
            }else if (ed.getOperacao().equals(Produto_VendedorED.OP_EXCLUIR) && ed.getDT_Exclusao() != null){                
                sql += " DT_Exclusao = '" + ed.getDT_Exclusao() + "'," +
                	   " DT_Inclusao = null," +
                	   " DT_alteracao = null";
            } else // System.err.println("ERRO! UPDATE não realizado! [Produto_VendedorBD]");
  		  	
            sql += " where (oid_Contrato_Vendedor = 0 or oid_Contrato_Vendedor is null)" +
            	   "   and oid_Vendedor = '"+ed.getOid_Vendedor()+"'" +
            	   "   and oid_Produto = "+ed.getOid_Produto();
 
            executasql.executarUpdate(sql);
                
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Produto_VendedorED");
            excecoes.setMetodo("altera");
            excecoes.setExc(exc);
            throw excecoes;
        }        
    }

    //*** Exclui por Contrato...
    public void deleta(Produto_VendedorED ed) throws Excecoes {

        String sql = null;
        try {

            sql =  " delete from produtos_vendedores " +
            	   " where oid_Contrato_Vendedor = " + ed.getOid_Contrato_Vendedor()+
            	   "   and oid_Contrato_Vendedor > 0";
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Produto_Vendedor");
            excecoes.setMetodo("deleta(Produto_VendedorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
}