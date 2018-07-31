/*
 * Created on 01/10/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Rota_VendaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Rota_VendaBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Rota_VendaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }
    
    //*** Rotina que grava, altera ou exclui ordenando o restante dos itens
    //*** I=Inclui, A=Altera, D=Deleta
    public Rota_VendaED GravaDados(Rota_VendaED ed, String operacao) throws Excecoes {

        int nr = 0;
        int nrAntigo = 0;
        ArrayList lista = null;
        Rota_VendaED edLista = new Rota_VendaED(); 

        try {   
            
            //*** Se não for Deletar já seta o código da Rota, ordenando corretamente a mesma
            if (!operacao.toUpperCase().equals("D"))
            {                
                //*** Verifica se o registro passado é no máximo "o próximo" na sequencia, para não deixar buracos na ordem...
                int proximo = getMaximo("nr_sequencia",
                        				"rotas_vendas",
                        				" oid_Vendedor = '"+ed.getOid_Vendedor()+"'" +
                        				" and DM_Dia = '"+ed.getDM_Dia()+"'");
                //*** Se for alteração, nº de sequencia não pode ser maior que o máximo(AutoIncremento - 1)
                if (!operacao.toUpperCase().equals("A"))
                    ++proximo;
                
                if (ed.getNR_Sequencia() > proximo)
                    ed.setNR_Sequencia(proximo);
                ed.setCD_Rota_Venda(Rota_VendaED.getCD_Dia(ed.getDM_Dia(), ed.getNR_Sequencia()));                
            }
            
            //*** Insere
            if (operacao.toUpperCase().equals("I") && ed.getNR_Sequencia() > 0)
            {
                lista = listaParaGravar(ed);
                for (int i=0; i<lista.size(); i++)
                {
                    edLista = (Rota_VendaED) lista.get(i);                    
                    // Se for <= ao nr passado então aumenta em +1 o sequencial dos seguintes
                    if (ed.getNR_Sequencia() <= edLista.getNR_Sequencia())
                    {
                        nr = edLista.getNR_Sequencia();
                        edLista.setNR_Sequencia(++nr);
                        alteraNR_Sequencia(edLista, Rota_VendaED.getCD_Dia(ed.getDM_Dia(), edLista.getNR_Sequencia()));
                    }                    
                }
                // Apos atualizar a sequencia dos outros registros inclui o atual
                return inclui(ed);

            } else 
            if (operacao.toUpperCase().equals("A") && ed.getNR_Sequencia() > 0)
            {
                lista = (ArrayList) listaParaGravar(ed);
                //*** Busca o nº antigo da sequencia para referencia
                for (int i=0; i<lista.size(); i++)
                {
                    edLista = (Rota_VendaED) lista.get(i);
                    if (ed.getOid_Rota_Venda() == edLista.getOid_Rota_Venda())
                    {
                        nrAntigo = edLista.getNR_Sequencia();
                        break;
                    }
                }
                //*** Se nº de sequencia não mudou então não atualiza sequencia dos outros registros
                if (ed.getNR_Sequencia() != nrAntigo)
                {
                    for (int i=0; i<lista.size(); i++)
                    {
                        edLista = (Rota_VendaED) lista.get(i);                    
                        if (ed.getOid_Rota_Venda() != edLista.getOid_Rota_Venda())
                        {
                            //*** Adicionar
                            if (edLista.getNR_Sequencia() < nrAntigo )
                            {
                                if (edLista.getNR_Sequencia() >= ed.getNR_Sequencia())
                                {
                                    if (edLista.getNR_Sequencia() < nrAntigo)
                                    {
                                        //adiciona
                                        nr = edLista.getNR_Sequencia();
                                        edLista.setNR_Sequencia(++nr);
                                        alteraNR_Sequencia(edLista, Rota_VendaED.getCD_Dia(ed.getDM_Dia(), edLista.getNR_Sequencia()));
                                    }
                                }                            
                            } else {
                                //*** Diminuir    
                                if (edLista.getNR_Sequencia() > nrAntigo)
                                {
                                    if (edLista.getNR_Sequencia() <= ed.getNR_Sequencia())
                                    {
                                        //diminui
                                        nr = edLista.getNR_Sequencia();
                                        edLista.setNR_Sequencia(--nr);
                                        alteraNR_Sequencia(edLista, Rota_VendaED.getCD_Dia(ed.getDM_Dia(), edLista.getNR_Sequencia()));
                                    }
                                }
                            }                            
                        }
                    }
                }
                // Apos atualizar a sequencia dos outros registros altera o atual o atual
                altera(ed);
              
            //*** Exclui
            } else 
            if (operacao.toUpperCase().equals("D"))
            {
                lista = (ArrayList) listaParaGravar(ed);
                for (int i=0; i<lista.size(); i++)
                {
                    edLista = (Rota_VendaED) lista.get(i);                    
                    // Se for >= ao nr passado então diminui em -1 o sequencial dos seguintes
                    if ((ed.getOid_Rota_Venda() != edLista.getOid_Rota_Venda())&&
                        (ed.getNR_Sequencia() < edLista.getNR_Sequencia())){
                        nr = edLista.getNR_Sequencia();
                        edLista.setNR_Sequencia(--nr);
                        alteraNR_Sequencia(edLista, Rota_VendaED.getCD_Dia(ed.getDM_Dia(), edLista.getNR_Sequencia()));
                    }                    
                }
                // Apos atualizar a sequencia dos outros registros exclui o atual
                deleta(ed);             
            }
            return ed;
                     
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "GravaDados()");
        }
    }

    public Rota_VendaED inclui(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        try {            
            //*** Pega o valor máximo
            ed.setOid_Rota_Venda(getAutoIncremento("oid_Rota_Venda", "rotas_vendas"));
            sql = "insert into rotas_vendas (" +            		
                  " oid_Rota_Venda, " +
                  " oid_vendedor, " +
                  " oid_cliente, " +
                  " cd_Rota_Venda, " +
                  " nr_sequencia, " +
                  " dm_dia," +
                  " dm_situacao) values ("
                + ed.getOid_Rota_Venda() +"," +
                "'" + ed.getOid_Vendedor() + "'," +
                "'" + ed.getOid_Cliente() + "'," +
                "'" + ed.getCD_Rota_Venda() + "'," +
                + ed.getNR_Sequencia() + "," +
                "'" + ed.getDM_Dia() + "'," +
                "'" + ed.getDM_Situacao() + "')";
                executasql.executarUpdate(sql);
         
            return ed;
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "inclui()");
        }
    }

    //*** UpDate Otimizado
    private void alteraNR_Sequencia(Rota_VendaED ed, String Codigo) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " update rotas_vendas set ";   
            sql += " cd_rota_venda = '" + Codigo + "',";
            sql += " nr_sequencia = " + ed.getNR_Sequencia();
            sql += " where oid_Rota_Venda = " + ed.getOid_Rota_Venda(); 
            executasql.executarUpdate(sql);
            
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "alteraNR_Sequencia()");
        }
    }
    
    //*** UPDATE
    private void altera(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        try {
            
            //*** Seta o Código da venda
            ed.setCD_Rota_Venda(Rota_VendaED.getCD_Dia(ed.getDM_Dia(), ed.getNR_Sequencia())); 
            sql =  " update rotas_vendas set ";            		
            sql += " cd_rota_venda = '" + ed.getCD_Rota_Venda() + "',";
            sql += " nr_sequencia = " + ed.getNR_Sequencia() + ",";
            sql += " dm_dia = '" + ed.getDM_Dia() + "',";
            sql += " dm_situacao = '" + ed.getDM_Situacao() + "'";
            sql += " where oid_Rota_Venda = " + ed.getOid_Rota_Venda();   
            executasql.executarUpdate(sql);
            
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "altera()");
        }
    }

    private void deleta(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        try {

            sql =  " delete from rotas_vendas " +
            	   " where oid_Rota_Venda = " + ed.getOid_Rota_Venda();
            executasql.executarUpdate(sql);
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "deleta()");
        }
    }
    //*** EXCLUI TODOS REGISTROS DE UM DETERMINADO DIA
    public void deletaByDia(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        try {
            sql =  " DELETE FROM Rotas_Vendas " +
                   " WHERE oid_Vendedor="+getSQLString(ed.getOid_Vendedor())+
                   "   AND DM_Dia="+getSQLString(ed.getDM_Dia());
            executasql.executarUpdate(sql);
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "deletaByDia()");
        }
    }
    
    //*** Lista Otimizada
    private ArrayList listaParaGravar(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " select rotas_vendas.oid_rota_venda, " +
            	  " 	   rotas_vendas.nr_sequencia " +
            	  " from rotas_vendas, clientes, vendedores " +            		
                  " where clientes.oid_cliente = rotas_vendas.oid_cliente " +
                  "   and vendedores.oid_vendedor = rotas_vendas.oid_vendedor " +
                  "   and rotas_vendas.dm_dia = '" +ed.getDM_Dia()+ "'"+
                  "   and rotas_vendas.oid_vendedor = '" +ed.getOid_Vendedor()+ "'";
            sql +=" order by rotas_vendas.nr_sequencia";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Rota_VendaED edVolta = new Rota_VendaED();
                edVolta.setOid_Rota_Venda(res.getInt("oid_Rota_Venda"));
                edVolta.setNR_Sequencia(res.getInt("nr_sequencia"));

                list.add(edVolta);
            }            
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "listaParaGravar()");
        }
        return list;
    }
    
    public ArrayList lista(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {
            
            sql = " SELECT rotas_vendas.oid_rota_venda, rotas_vendas.oid_vendedor," +
            	  " 	   rotas_vendas.oid_cliente, rotas_vendas.cd_rota_venda, " +
            	  "        rotas_vendas.nr_sequencia, rotas_vendas.dm_dia, rotas_vendas.dm_situacao, " +
            	  "        pessoas.nr_cnpj_cpf as cd_cliente, pessoas.nm_razao_social as nm_cliente, " +
            	  "        vendedores.cd_vendedor as cd_vendedor, pessoas_vendedor.nm_razao_social as nm_vendedor " +
            	  " FROM rotas_vendas, pessoas, vendedores, pessoas pessoas_vendedor " +            		
                  " WHERE clientes.oid_cliente = rotas_vendas.oid_cliente " +
                  "   and clientes.oid_cliente = pessoas.oid_Pessoa " +
                  "   and vendedores.oid_vendedor = pessoas_vendedor.oid_pessoa " +
                  "   and vendedores.oid_vendedor = rotas_vendas.oid_vendedor ";
            
            if (doValida(ed.getDM_Dia()))
                sql +="   and rotas_vendas.dm_dia = '" +ed.getDM_Dia()+ "'";
            if (doValida(ed.getOid_Vendedor()))
                sql +="   and rotas_vendas.oid_vendedor = '" +ed.getOid_Vendedor()+ "'";
            if (doValida(ed.getOid_Cliente()))
                sql +="   and rotas_vendas.oid_Cliente = '" +ed.getOid_Cliente()+ "'";
            if (doValida(ed.getDM_Situacao()))
                sql +="   and rotas_vendas.dm_Situacao = '" +ed.getDM_Situacao()+ "'";
            
            sql +=" ORDER BY rotas_vendas.cd_rota_venda, rotas_vendas.nr_sequencia";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Rota_VendaED edVolta = new Rota_VendaED();
          
                edVolta.setOid_Rota_Venda(res.getInt("oid_Rota_Venda"));
                edVolta.setOid_Vendedor(res.getString("oid_Vendedor"));
                edVolta.setOid_Cliente(res.getString("oid_Cliente"));                
                
                edVolta.setCD_Rota_Venda(res.getString("cd_Rota_Venda"));
                edVolta.setNR_Sequencia(res.getInt("nr_Sequencia"));
                edVolta.setDM_Dia(res.getString("dm_dia")); 
                edVolta.setDM_Situacao(res.getString("dm_Situacao")); 
                
                edVolta.setCD_Vendedor(res.getString("cd_vendedor"));
                edVolta.setNM_Vendedor(res.getString("nm_vendedor"));
                edVolta.setCD_Cliente(res.getString("cd_cliente"));
                edVolta.setNM_Cliente(res.getString("nm_cliente"));
            	
                list.add(edVolta);
            }            
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "lista()");
        }
        return list;
    }
    
    // Rotas por Cliente
    public ArrayList listaByCliente(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {
            
            sql = " select rotas_vendas.oid_rota_venda, rotas_vendas.oid_vendedor," +
            	  " 	   rotas_vendas.oid_cliente, rotas_vendas.cd_rota_venda, " +
            	  "        rotas_vendas.nr_sequencia, rotas_vendas.dm_dia, rotas_vendas.dm_situacao, " +
            	  "        pessoas.nr_cnpj_cpf as cd_cliente, pessoas.nm_razao_social as nm_cliente, " +
            	  "        vendedores.cd_vendedor as cd_vendedor, pessoas_vendedor.nm_razao_social as nm_vendedor " +
            	  " from rotas_vendas, clientes, pessoas, vendedores, pessoas pessoas_vendedor " +            		
                  " where clientes.oid_cliente = rotas_vendas.oid_cliente " +
                  "   and clientes.oid_cliente = pessoas.oid_Pessoa " +
                  "   and vendedores.oid_vendedor = pessoas_vendedor.oid_pessoa " +
                  "   and vendedores.oid_vendedor = rotas_vendas.oid_vendedor " +
                  "   and rotas_vendas.oid_vendedor = '" +ed.getOid_Vendedor()+ "'" +
                  "   and rotas_vendas.oid_Cliente = '" +ed.getOid_Cliente()+ "'";
            if (doValida(ed.getDM_Dia()))
                sql +="  and rotas_vendas.DM_Dia = '"+ed.getDM_Dia()+"'";
            
            sql +=" order by rotas_vendas.CD_ROTA_VENDA, rotas_vendas.nr_sequencia";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Rota_VendaED edVolta = new Rota_VendaED();
          
                edVolta.setOid_Rota_Venda(res.getInt("oid_Rota_Venda"));
                edVolta.setOid_Vendedor(res.getString("oid_Vendedor"));
                edVolta.setOid_Cliente(res.getString("oid_Cliente"));
                
                edVolta.setCD_Rota_Venda(res.getString("cd_Rota_Venda"));
                edVolta.setNR_Sequencia(res.getInt("nr_Sequencia"));
                edVolta.setDM_Dia(res.getString("dm_dia"));
                edVolta.setDM_Situacao(res.getString("dm_Situacao"));
                
                edVolta.setCD_Vendedor(res.getString("cd_vendedor"));
                edVolta.setNM_Vendedor(res.getString("nm_vendedor"));
                edVolta.setCD_Cliente(res.getString("cd_cliente"));
                edVolta.setNM_Cliente(res.getString("nm_cliente"));
            	
                list.add(edVolta);
            }            
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "listaByCliente()");
        }
        return list;
    }
    
    public Rota_VendaED getByRecord(Rota_VendaED ed) throws Excecoes {

        String sql = null;
        Rota_VendaED edVolta = new Rota_VendaED();
        try {

            sql = " select rotas_vendas.oid_rota_venda, rotas_vendas.oid_vendedor," +
            	  " 	   rotas_vendas.oid_cliente, rotas_vendas.cd_rota_venda, " +
            	  "        rotas_vendas.nr_sequencia, rotas_vendas.dm_dia, rotas_vendas.dm_situacao, " +
      	  		  "        pessoas.nr_cnpj_cpf as cd_cliente, pessoas.nm_razao_social as nm_cliente, " +
      	  		  "        vendedores.cd_vendedor as cd_vendedor, pessoas_vendedor.nm_razao_social as nm_vendedor " +
      	  		  " from rotas_vendas, pessoas, vendedores, pessoas pessoas_vendedor " +            		
      	  		  " where clientes.oid_cliente = rotas_vendas.oid_cliente " +
      	  		  "   and clientes.oid_cliente = pessoas.oid_Pessoa " +
      	  		  "   and vendedores.oid_vendedor = pessoas_vendedor.oid_pessoa " +
      	  		  "   and vendedores.oid_vendedor = rotas_vendas.oid_vendedor ";
            if (ed.getOid_Rota_Venda() > 0)
                sql += "   and rotas_vendas.oid_Rota_Venda = " + ed.getOid_Rota_Venda();
            else {
                if (doValida(ed.getOid_Vendedor()) && doValida(ed.getCD_Rota_Venda()))
                    sql += "   AND Rotas_Vendas.oid_Vendedor = "+getSQLString(ed.getOid_Vendedor())+
                           "   AND Rotas_Vendas.CD_Rota_Venda = "+getSQLString(ed.getCD_Rota_Venda());
            }

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta.setOid_Rota_Venda(res.getInt("oid_Rota_Venda"));
                edVolta.setOid_Vendedor(res.getString("oid_Vendedor"));
                edVolta.setOid_Cliente(res.getString("oid_Cliente"));
                
                edVolta.setCD_Rota_Venda(res.getString("cd_Rota_Venda"));
                edVolta.setNR_Sequencia(res.getInt("nr_Sequencia"));
                edVolta.setDM_Dia(res.getString("dm_dia"));
                edVolta.setDM_Situacao(res.getString("dm_Situacao"));
                
                edVolta.setCD_Vendedor(res.getString("cd_vendedor"));
                edVolta.setNM_Vendedor(res.getString("nm_vendedor"));
                edVolta.setCD_Cliente(res.getString("cd_cliente"));
                edVolta.setNM_Cliente(res.getString("nm_cliente"));
            }
            return edVolta;
        
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "getByRecord()");
        }
    }
}