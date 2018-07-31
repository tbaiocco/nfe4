/*
 * Created on 24/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Cliente_VendedorED;
import com.master.ed.Cliente_VendedorRelED;
import com.master.ed.Tipo_Tabela_VendaED;
import com.master.rl.Cliente_VendedorRL;
import com.master.root.ClienteBean;
import com.master.root.VendedorBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class Cliente_VendedorBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Cliente_VendedorBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Cliente_VendedorED inclui(Cliente_VendedorED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Cliente_Vendedor(ed.getOid_Cliente()+ed.getOid_Vendedor());
            sql = " INSERT INTO Clientes_Vendedores ("
                + "oid_Cliente_Vendedor, oid_Cliente, oid_Vendedor, DM_Situacao, oid_Tipo_Tabela_Venda) VALUES ("
                + "'" + ed.getOid_Cliente_Vendedor() + "'" +
                ",'" + ed.getOid_Cliente() + "'" +
                ",'" + ed.getOid_Vendedor() + "'" +
                ",'" + ed.getDm_Situacao() + "'" +
                ","+ed.getOid_Tipo_Tabela_Venda()+")";

            executasql.executarUpdate(sql);
            return ed;
           
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Cliente_VendedorED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Clientes_Vendedores SET ";
            sql +=" oid_Cliente  = '" + ed.getCd_Cliente()  + "', ";
            sql +=" oid_Vendedor = '" + ed.getCd_Vendedor() + "', ";
            sql +=" dm_situacao = '" + ed.getDm_Situacao() + "', ";
            sql +=" oid_Tipo_Tabela_Venda = " + ed.getOid_Tipo_Tabela_Venda();
            sql +=" WHERE oid_Cliente_Vendedor = '" + ed.getOid_Cliente_Vendedor()+ "'";
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Cliente_VendedorED ed) throws Excecoes {

        String sql = null;
        try {

            sql = "DELETE FROM Clientes_Vendedores WHERE oid_Cliente_Vendedor = ";
            sql +="('" + ed.getOid_Cliente_Vendedor() + "')";

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(Cliente_VendedorED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT pessoa_cliente.nm_razao_social as nm_cliente, " +
                  "      pessoa_vendedor.nm_razao_social as nm_vendedor," +
                  "      Vendedores.cd_vendedor, pessoa_cliente.nr_cnpj_cpf as cd_cliente," +
                  "      Clientes_Vendedores.oid_cliente, Clientes_Vendedores.oid_Vendedor, " +
                  "      Clientes_Vendedores.oid_Cliente_Vendedor, Clientes_Vendedores.dm_situacao, " +
                  "      Clientes_Vendedores.oid_Tipo_Tabela_Venda " +
                  " FROM Clientes_Vendedores, Pessoas pessoa_cliente, " +
                  "      Vendedores, Pessoas pessoa_vendedor" +
                  " WHERE pessoa_vendedor.oid_pessoa = clientes_vendedores.oid_vendedor" +
                  "      and pessoa_cliente.oid_pessoa = clientes_vendedores.oid_cliente " +
                  "      and Vendedores.oid_vendedor = clientes_vendedores.oid_vendedor";

            if (doValida(ed.getOid_Cliente_Vendedor()))
                sql += " AND Clientes_Vendedores.oid_Cliente_Vendedor = '"+ed.getOid_Cliente_Vendedor()+"'";
            else {
                if (doValida(ed.getOid_Vendedor()))
                    sql += " AND Clientes_Vendedores.oid_Vendedor = '"+ed.getOid_Vendedor()+"'";
                if (doValida(ed.getOid_Cliente()))
                    sql += " AND Clientes_Vendedores.oid_Cliente = '"+ed.getOid_Cliente()+"'";
                if (doValida(ed.getCd_Cliente()))
                    sql +="      AND pessoa_cliente.nr_cnpj_cpf = '" +ed.getCd_Cliente()+ "'";
                if (doValida(ed.edCliente.getCD_Cliente_Palm()))
                    sql +="      AND Clientes_Vendedores.oid_Cliente = Clientes.oid_Cliente" +
                          "      AND Clientes.CD_Cliente_Palm = "+getSQLString(ed.edCliente.getCD_Cliente_Palm().replaceAll("-",""));
                //*** Situação a nao filtrar
                if (doValida(ed.edCliente.getDM_Credito()))
                    sql +="      AND Clientes_Vendedores.oid_Cliente = Clientes.oid_Cliente" +
                          "      AND Clientes.DM_Credito <> "+getSQLString(ed.edCliente.getDM_Credito());
            }
            sql += " ORDER BY pessoa_cliente.nm_razao_social";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Cliente_VendedorED edVolta = new Cliente_VendedorED();
                edVolta.setOid_Cliente_Vendedor(res.getString("oid_Cliente_Vendedor"));                
                edVolta.setOid_Cliente(res.getString("oid_cliente"));                
                edVolta.setOid_Vendedor(res.getString("oid_Vendedor"));
                edVolta.setOid_Tipo_Tabela_Venda(res.getInt("oid_Tipo_Tabela_Venda"));
                //*** Carrega ED com Dados
                if (edVolta.getOid_Tipo_Tabela_Venda() > 0)
                    edVolta.edTipo_Tabela = new Tipo_Tabela_VendaBD(executasql).getByRecord(new Tipo_Tabela_VendaED(edVolta.getOid_Tipo_Tabela_Venda()));
                if (doValida(edVolta.getOid_Cliente()))
                    edVolta.edCliente = ClienteBean.getByOID_Cliente(edVolta.getOid_Cliente());
                if (doValida(edVolta.getOid_Vendedor()))
                    edVolta.edVendedor = VendedorBean.getByOID_Vendedor(edVolta.getOid_Vendedor());
                edVolta.setCd_Cliente(res.getString("cd_Cliente"));                
                edVolta.setCd_Vendedor(res.getString("cd_Vendedor"));                
                edVolta.setNm_Vendedor(res.getString("nm_Vendedor"));                
                edVolta.setNm_Cliente(res.getString("nm_cliente"));                
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
                
                lista.add(edVolta);
            }
            return lista; 
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public Cliente_VendedorED getByRecord(Cliente_VendedorED ed) throws Excecoes {

        try {
           Iterator it = this.lista(ed).iterator();
           if (it.hasNext())
               return (Cliente_VendedorED) it.next();
           else return new Cliente_VendedorED();
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }
    
    //*** RELATÓRIOS
    public void RelCliente_Vendedor_Simples(HttpServletResponse response, String oid_Vendedor, String ordenar) throws Exception {

        String sql = null;
        ArrayList lista = new ArrayList();

        try {

            sql = " select pessoa_cliente.nm_razao_social as nm_Cliente, " +
            		"      pessoa_vendedor.nm_razao_social as nm_Vendedor," +
            		"	   Clientes_Vendedores.oid_cliente, Clientes_Vendedores.oid_Vendedor, " +
            		"      Vendedores.cd_vendedor, pessoa_cliente.nr_cnpj_cpf as cd_Cliente," +
            		"	   pessoa_cliente.nr_telefone" +
            		" from Clientes_Vendedores, Pessoas pessoa_cliente, " +
            		"	   Vendedores, Pessoas pessoa_vendedor" +
            		" where pessoa_vendedor.oid_pessoa = clientes_vendedores.oid_vendedor" +
            		"   and pessoa_cliente.oid_pessoa = clientes_vendedores.oid_cliente " +
            		"	and Vendedores.oid_vendedor = clientes_vendedores.oid_vendedor";
            		if (doValida(oid_Vendedor))	
            		    sql += "   and Clientes_Vendedores.oid_Vendedor = '" +oid_Vendedor+ "'";
            		if (ordenar.equals(Cliente_VendedorRelED.ORDENAR_NOME))
            		    sql +=  " order by nm_vendedor, pessoa_cliente.nm_razao_social";
            		else if (ordenar.equals(Cliente_VendedorRelED.ORDENAR_CODIGO))
            		    sql +=  " order by Vendedores.cd_vendedor, pessoa_cliente.nm_razao_social";

            ResultSet res = this.executasql.executarConsulta(sql);
            
            //popula
            while (res.next()) {
            	
                Cliente_VendedorRelED edVolta = new Cliente_VendedorRelED();
          
                edVolta.setOid_cliente(res.getString("oid_cliente"));
                edVolta.setOid_vendedor(res.getString("oid_vendedor"));
                edVolta.setCd_cliente(res.getString("cd_Cliente"));
                edVolta.setCd_vendedor(res.getString("cd_Vendedor"));
                edVolta.setNm_vendedor(res.getString("nm_Vendedor"));
                edVolta.setNm_cliente(res.getString("nm_cliente"));  
                
                edVolta.setNr_telefone(res.getString("nr_telefone"));
                
                //*** Códigos da Rota
                sql = " select cd_rota_venda " +          	  		  
          	  		  " from rotas_vendas " +            		
          	  		  " where oid_cliente = '" + edVolta.getOid_cliente() + "'"+
          	  		  "   and oid_vendedor = '" + edVolta.getOid_vendedor() + "'"+
          	  		  //"   and dm_situacao = 'L'"+
                	  " order by cd_rota_venda";	
                
                ResultSet res2 = this.executasql.executarConsulta(sql);
                String Codigo = "";
                while (res2.next()){
                    if (!Codigo.equals(""))
                        Codigo = Codigo + "/";
                    Codigo = Codigo + res2.getString("cd_rota_venda");
                }
                edVolta.setCd_rota_venda(Codigo);
                
                lista.add(edVolta);
            }

            new Cliente_VendedorRL().geraRelClientes(lista, Cliente_VendedorRelED.LAYOUT_VEND_SIMPLES, response);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "RelCliente_Vendedor_Simples()");
        }
    }
    
    
    public void RelCliente_Vendedor_Completo(HttpServletResponse response, String oid_Vendedor, String ordenar) throws Exception {

        String sql = null;
        ArrayList lista = new ArrayList();

        try {

            sql = " select pessoa_cliente.nm_razao_social as nm_Cliente, " +
            		"      pessoa_vendedor.nm_razao_social as nm_Vendedor," +
            		"      Vendedores.cd_vendedor, pessoa_cliente.nr_cnpj_cpf as cd_Cliente," +
            		"	   Clientes_Vendedores.oid_cliente, Clientes_Vendedores.oid_Vendedor, " +
            		"	   pessoa_cliente.oid_cidade, pessoa_cliente.nm_endereco, pessoa_cliente.nm_bairro," +
            		"	   pessoa_cliente.nr_cep, pessoa_cliente.nm_inscricao_estadual, pessoa_cliente.nr_telefone," +
            		"      (select nm_Cidade from Cidades where oid_cidade = pessoa_cliente.oid_cidade) as nm_cidade"+	
            		" from Clientes_Vendedores, Pessoas pessoa_cliente, " +
            		"	   Vendedores, Pessoas pessoa_vendedor" +
            		" where pessoa_vendedor.oid_pessoa = clientes_vendedores.oid_vendedor" +
            		"   and pessoa_cliente.oid_pessoa = clientes_vendedores.oid_cliente " +
            		"	and Vendedores.oid_vendedor = clientes_vendedores.oid_vendedor";
            		if (doValida(oid_Vendedor))
            		    sql += "   and Clientes_Vendedores.oid_Vendedor = '" +oid_Vendedor+ "'";
            		if (ordenar.equals(Cliente_VendedorRelED.ORDENAR_NOME))
            		    sql +=  " order by nm_vendedor, pessoa_cliente.nm_razao_social";
            		else if (ordenar.equals(Cliente_VendedorRelED.ORDENAR_CODIGO))
            		    sql +=  " order by Vendedores.cd_vendedor, pessoa_cliente.nm_razao_social";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Cliente_VendedorRelED edVolta = new Cliente_VendedorRelED();
          
                edVolta.setOid_cliente(res.getString("oid_Cliente"));
                edVolta.setOid_vendedor(res.getString("oid_Vendedor"));
                edVolta.setCd_cliente(res.getString("cd_Cliente"));
                edVolta.setCd_vendedor(res.getString("cd_Vendedor"));
                edVolta.setNm_vendedor(res.getString("nm_Vendedor"));
                edVolta.setNm_cliente(res.getString("nm_cliente"));
                
                edVolta.setNm_endereco(res.getString("nm_endereco"));
                edVolta.setNm_bairro(res.getString("nm_bairro"));
                edVolta.setNr_cep(res.getString("nr_cep"));
                edVolta.setNm_inscricao_estadual(res.getString("nm_inscricao_estadual"));
                edVolta.setNr_telefone(res.getString("nr_telefone"));
                
                edVolta.setNm_cidade(res.getString("nm_cidade"));
                
                //*** Código da Rota
                sql = " select cd_rota_venda " +          	  		  
          	  		  " from rotas_vendas " +            		
          	  		  " where oid_cliente = '" + edVolta.getOid_cliente() + "'"+
          	  		  "   and oid_vendedor = '" + edVolta.getOid_vendedor() + "'"+
          	  		  //"   and dm_situacao = 'L'"+
                	  " order by cd_rota_venda";	
                
                ResultSet res2 = this.executasql.executarConsulta(sql);
                String Codigo = "";
                while (res2.next()){
                    if (!Codigo.equals(""))
                        Codigo = Codigo + "/";
                    Codigo = Codigo + res2.getString("cd_rota_venda");
                }
                edVolta.setCd_rota_venda(Codigo);
                
                lista.add(edVolta);
            }

            new Cliente_VendedorRL().geraRelClientes(lista, Cliente_VendedorRelED.LAYOUT_VEND_COMPLETO, response);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "RelCliente_Vendedor_Completo()");
        }
    }
    public void RelCliente_Vendedor_Rota(HttpServletResponse response, String oid_Vendedor) throws Exception {

        String sql = null;
        ArrayList lista = new ArrayList();

        try {

            sql = " select pessoa_cliente.nm_razao_social as nm_Cliente, " + 
                  "		   pessoa_vendedor.nm_razao_social as nm_Vendedor, " +
                  "		   Vendedores.cd_vendedor, pessoa_cliente.nr_cnpj_cpf as cd_Cliente, " +
                  "		   rotas_vendas.cd_rota_venda, pessoa_cliente.nm_endereco, pessoa_cliente.nm_bairro, " +
                  "		   pessoa_cliente.nr_cep, pessoa_cliente.nm_inscricao_estadual, pessoa_cliente.nr_telefone," +	
                  "        (select nm_cidade from Cidades where oid_cidade = pessoa_cliente.oid_cidade) as nm_cidade"+
                  " from Clientes_Vendedores, Pessoas pessoa_cliente, Vendedores, " +
                  "      Pessoas pessoa_vendedor, rotas_vendas " +
                  "where pessoa_vendedor.oid_pessoa = clientes_vendedores.oid_vendedor " +
                  "	 and pessoa_cliente.oid_pessoa = clientes_vendedores.oid_cliente " +
                  "	 and Vendedores.oid_vendedor = clientes_vendedores.oid_vendedor " +
                  "  and rotas_vendas.oid_vendedor = clientes_vendedores.oid_vendedor " +
                  "  and rotas_vendas.oid_cliente = clientes_vendedores.oid_cliente";
                  if (doValida(oid_Vendedor))	
               		    sql += "   and Clientes_Vendedores.oid_Vendedor = '" +oid_Vendedor+ "'"; 
            sql += " order by Vendedores.cd_vendedor, rotas_vendas.cd_rota_venda, pessoa_cliente.nm_razao_social";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Cliente_VendedorRelED edVolta = new Cliente_VendedorRelED();
                
                //*** Código da Rota
                edVolta.setCd_rota_venda(res.getString("cd_rota_venda"));

                edVolta.setCd_cliente(res.getString("cd_Cliente"));
                edVolta.setCd_vendedor(res.getString("cd_Vendedor"));
                edVolta.setNm_vendedor(res.getString("nm_Vendedor"));
                edVolta.setNm_cliente(res.getString("nm_cliente"));
                
                edVolta.setNm_cidade(res.getString("nm_cidade"));
                edVolta.setNm_endereco(res.getString("nm_endereco"));
                edVolta.setNm_bairro(res.getString("nm_bairro"));
                edVolta.setNr_cep(res.getString("nr_cep"));
                edVolta.setNm_inscricao_estadual(res.getString("Nm_inscricao_estadual"));
                edVolta.setNr_telefone(res.getString("nr_telefone"));
                
                lista.add(edVolta);
            }

            new Cliente_VendedorRL().geraRelClientes(lista, Cliente_VendedorRelED.LAYOUT_ROTA, response);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "RelCliente_Vendedor_Rota()");
        }
    }
}