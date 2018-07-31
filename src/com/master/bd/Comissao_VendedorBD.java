/*
 * Created on 03/09/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Comissao_VendedorED;
import com.master.ed.Estrutura_ProdutoED;
import com.master.rl.Comissao_VendedorRL;
import com.master.rn.Estrutura_ProdutoRN;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author Andre Valadas
 */
public class Comissao_VendedorBD {

  private ExecutaSQL executasql;
  private String sql = null;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public Comissao_VendedorBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Comissao_VendedorED inclui (Comissao_VendedorED ed) throws Excecoes {

    int valOid = 0;
    Comissao_VendedorED edVolta = new Comissao_VendedorED ();

    try {

      sql = " select count(*) as count from comissoes_vendedores";
      sql += " where oid_Vendedor = '" + ed.getOid_Vendedor () + "'";

      if (ed.getOid_Mix () > 0) {
        sql += "   and oid_mix = " + ed.getOid_Mix ();
      }
      else if (ed.getOid_Estrutura_Produto () > 0) {
        sql += "   and oid_estrutura_produto = " + ed.getOid_Estrutura_Produto ();
      }
      else if (ed.getOid_Produto () > 0) {
        sql += "   and oid_produto = " + ed.getOid_Produto ();
      }
      else if (ed.getOid_Tipo_Produto () > 0) {
        sql += "   and oid_tipo_produto = " + ed.getOid_Tipo_Produto ();
      }

      ResultSet res = this.executasql.executarConsulta (sql);

      //*** Varifica se existe-
       while (res.next ()) {
         valOid = res.getInt ("count");
       }

      if (valOid <= 0) {

        sql = " select max(oid_comissao_vendedor) as maxOID from comissoes_vendedores";

        res = this.executasql.executarConsulta (sql);

        //*** Pega o valor máximo
         while (res.next ()) {
           valOid = res.getInt ("maxOID");
         }

        sql = " insert into comissoes_vendedores (";
        sql += "	oid_comissao_vendedor, ";
        sql += " 	oid_Vendedor, ";
        sql += " 	pe_Comissao, ";
        sql += " 	oid_mix, ";
        sql += "	oid_produto,";
        sql += "	oid_tipo_produto,";
        sql += "	oid_estrutura_produto,";
        sql += "	dm_situacao) ";
        sql += " values (";
        sql += ++valOid + ",";
        sql += "'" + ed.getOid_Vendedor () + "',";
        sql += ed.getPE_Comissao () + ",";
        sql += ed.getOid_Mix () + ",";
        sql += ed.getOid_Produto () + ",";
        sql += ed.getOid_Tipo_Produto () + ",";
        sql += ed.getOid_Estrutura_Produto () + ",";
        sql += "'" + ed.getDM_Situacao () + "')";

        //*** SQL
         //// System.err.println("SQL Incluir >> "+sql);

         executasql.executarUpdate (sql);
        edVolta.setOid_Comissao_Vendedor (valOid);
        edVolta.setOid_Vendedor (ed.getOid_Vendedor ());

      }
      else {
        // System.err.println ("ERRO! Essa Comissao ja existe!!!");
      }

      return edVolta;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Comissao_Vededor");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera (Comissao_VendedorED ed) throws Excecoes {

    try {

      sql = " update comissoes_vendedores set ";
      sql += " oid_Vendedor = '" + ed.getOid_Vendedor () + "', ";
      sql += " pe_Comissao  = " + ed.getPE_Comissao () + ", ";
      sql += " oid_mix  	  = " + ed.getOid_Mix () + ", ";
      sql += " oid_produto  = " + ed.getOid_Produto () + ", ";
      sql += " oid_tipo_produto  = " + ed.getOid_Tipo_Produto () + ", ";
      sql += " oid_estrutura_produto = " + ed.getOid_Estrutura_Produto () + ", ";
      sql += " dm_situacao  = '" + ed.getDM_Situacao () + "'";
      sql += " where oid_comissao_vendedor = " + ed.getOid_Comissao_Vendedor ();

      //*** SQL
       //// System.err.println("Comissao alteraBD >> "+sql);

       executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados de Comissao_Vededor");
      excecoes.setMetodo ("altera(Comissao_VededorED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Comissao_VendedorED ed) throws Excecoes {

    try {

      sql = " delete from comissoes_vendedores " +
          " where oid_comissao_vendedor = " + ed.getOid_Comissao_Vendedor ();

      //*** SQL
       //// System.err.println("Delete Comissao >> "+sql);

       executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar Comissao_Vededor");
      excecoes.setMetodo ("deleta(Comissao_VededorED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Comissao_VendedorED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    ResultSet auxRes = null;

    try {

      sql = " select oid_comissao_vendedor, "
          + " 	oid_Vendedor, "
          + " 	pe_Comissao, "
          + " 	oid_mix,"
          + "	oid_produto,"
          + "	oid_tipo_produto,"
          + "	oid_estrutura_produto,"
          + "	dm_situacao"
          + " from comissoes_vendedores";
      sql += " where oid_vendedor = '" + ed.getOid_Vendedor () + "'";

      //*** SQL
       //// System.err.println("Lista Comissao >> "+sql);

       ResultSet res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {

        Comissao_VendedorED edVolta = new Comissao_VendedorED ();

        edVolta.setOid_Comissao_Vendedor (res.getInt ("oid_comissao_vendedor"));
        edVolta.setOid_Vendedor (res.getString ("oid_Vendedor"));
        edVolta.setPE_Comissao (res.getDouble ("pe_Comissao"));
        edVolta.setOid_Mix (res.getInt ("oid_mix"));
        edVolta.setOid_Produto (res.getInt ("oid_produto"));
        edVolta.setOid_Tipo_Produto (res.getInt ("oid_tipo_produto"));
        edVolta.setOid_Estrutura_Produto (res.getInt ("oid_estrutura_produto"));
        edVolta.setDM_Situacao (res.getString ("dm_situacao"));

        //*** Busca pelo oid
         if (edVolta.getOid_Mix () > 0) {

           sql = " select cd_mix, nm_mix from mix" +
               " where oid_mix = " + edVolta.getOid_Mix ();
           //*** SQL
            //// System.err.println("Filtro Comissao oid_Mix >> ["+res.getRow()+"] = "+sql);
            auxRes = this.executasql.executarConsulta (sql);
            while (auxRes.next ()) {
             edVolta.setCD_Mix (auxRes.getString ("cd_mix"));
             edVolta.setNM_Mix (auxRes.getString ("nm_mix"));
           }

         }
         else if (edVolta.getOid_Estrutura_Produto () > 0) {

           Estrutura_ProdutoED edEstrutura = new Estrutura_ProdutoED ();
           edEstrutura.setOid_Estrutura_Produto (edVolta.getOid_Estrutura_Produto ());

           //*** SQL
            //// System.err.println("Filtro Comissao Oid_Estrutura_Produto## ");

            edEstrutura = new Estrutura_ProdutoRN ().getByRecord (edEstrutura);

           edVolta.setCD_Estrutura_Produto (edEstrutura.getCd_Estrutura_Produto ());
           edVolta.setNM_Estrutura_Produto (edEstrutura.getNm_Estrutura_Produto ());

         }
         else if (edVolta.getOid_Produto () > 0) {

           sql = " select cd_produto, nm_produto from produtos" +
               " where oid_produto = " + edVolta.getOid_Produto ();
           //*** SQL
            //// System.err.println("Filtro Comissao oid_Produto >> ["+res.getRow()+"] = "+sql);
            auxRes = this.executasql.executarConsulta (sql);
            while (auxRes.next ()) {
             edVolta.setCD_Produto (auxRes.getString ("cd_produto"));
             edVolta.setNM_Produto (auxRes.getString ("nm_produto"));
           }
         }
         else if (edVolta.getOid_Tipo_Produto () > 0) {

           sql = " select cd_tipo_produto, nm_tipo_produto from tipos_produtos" +
               " where oid_tipo_produto = " + edVolta.getOid_Tipo_Produto ();
           //*** SQL
            //// System.err.println("Filtro Comissao oid_Tipo_Produto >> ["+res.getRow()+"] = "+sql);
            auxRes = this.executasql.executarConsulta (sql);
            while (auxRes.next ()) {
             edVolta.setCD_Tipo_Produto (auxRes.getString ("cd_tipo_produto"));
             edVolta.setNM_Tipo_Produto (auxRes.getString ("nm_tipo_produto"));
           }
         }
         ;

        //*** Adiciona na lista
         list.add (edVolta);
      }

      return list;

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Comissao_Vededor - SQL=" + sql);
      excecoes.setMetodo ("lista(Comissao_Vededor)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Comissao_VendedorED getByRecord (Comissao_VendedorED ed) throws Excecoes {

    Comissao_VendedorED edVolta = new Comissao_VendedorED ();
    ResultSet auxRes = null;

    try {

      sql = " select c.oid_comissao_vendedor as oid_comissao_vendedor, "
          + " 	c.oid_Vendedor as oid_Vendedor, "
          + " 	c.pe_Comissao as pe_Comissao, "
          + " 	c.oid_mix as oid_mix,"
          + "	c.oid_produto as oid_produto,"
          + "	c.oid_tipo_produto as oid_tipo_produto,"
          + "	c.oid_estrutura_produto as oid_estrutura_produto,"
          + "	c.dm_situacao as dm_situacao"
          + " from comissoes_vendedores c";
      sql += " where c.oid_comissao_vendedor = " + ed.getOid_Comissao_Vendedor ();

      //*** SQL
       //// System.err.println("getByRecord Comissao >> "+sql);

       ResultSet res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {

        edVolta.setOid_Comissao_Vendedor (res.getInt ("oid_comissao_vendedor"));
        edVolta.setOid_Vendedor (res.getString ("oid_Vendedor"));
        edVolta.setPE_Comissao (res.getDouble ("pe_Comissao"));
        edVolta.setOid_Mix (res.getInt ("oid_mix"));
        edVolta.setOid_Produto (res.getInt ("oid_produto"));
        edVolta.setOid_Tipo_Produto (res.getInt ("oid_tipo_produto"));
        edVolta.setOid_Estrutura_Produto (res.getInt ("oid_estrutura_produto"));
        edVolta.setDM_Situacao (res.getString ("dm_situacao"));

        //// System.err.println("Carregou o edVolta!!!");

        //*** Busca pelo oid
         if (edVolta.getOid_Mix () > 0) {

           sql = " select cd_mix, nm_mix from mix" +
               " where oid_mix = " + edVolta.getOid_Mix ();
           //*** SQL
            //// System.err.println("Filtro Comissao oid_Mix >> ["+res.getRow()+"] = "+sql);
            auxRes = this.executasql.executarConsulta (sql);
            while (auxRes.next ()) {
             edVolta.setCD_Mix (auxRes.getString ("cd_mix"));
             edVolta.setNM_Mix (auxRes.getString ("nm_mix"));
           }

         }
         else if (edVolta.getOid_Estrutura_Produto () > 0) {

           Estrutura_ProdutoED edEstrutura = new Estrutura_ProdutoED ();
           edEstrutura.setOid_Estrutura_Produto (edVolta.getOid_Estrutura_Produto ());

           edEstrutura = new Estrutura_ProdutoRN ().getByRecord (edEstrutura);

           edVolta.setCD_Estrutura_Produto (edEstrutura.getCd_Estrutura_Produto ());
           edVolta.setNM_Estrutura_Produto (edEstrutura.getNm_Estrutura_Produto ());

         }
         else if (edVolta.getOid_Produto () > 0) {

           sql = " select cd_produto, nm_produto from produtos" +
               " where oid_produto = " + edVolta.getOid_Produto ();
           //*** SQL
            //// System.err.println("Filtro Comissao oid_Produto >> ["+res.getRow()+"] = "+sql);
            auxRes = this.executasql.executarConsulta (sql);
            while (auxRes.next ()) {
             edVolta.setCD_Produto (auxRes.getString ("cd_produto"));
             edVolta.setNM_Produto (auxRes.getString ("nm_produto"));
           }
         }
         else if (edVolta.getOid_Tipo_Produto () > 0) {

           sql = " select cd_tipo_produto, nm_tipo_produto from tipos_produtos" +
               " where oid_tipo_produto = " + edVolta.getOid_Tipo_Produto ();
           //*** SQL
            //// System.err.println("Filtro Comissao oid_Tipo_Produto >> ["+res.getRow()+"] = "+sql);
            auxRes = this.executasql.executarConsulta (sql);
            while (auxRes.next ()) {
             edVolta.setCD_Tipo_Produto (auxRes.getString ("cd_tipo_produto"));
             edVolta.setNM_Tipo_Produto (auxRes.getString ("nm_tipo_produto"));
           }
         }
      }

      return edVolta;

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord(Comissao_Vededor)");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public byte[] geraComissaoConhecimentos (Comissao_VendedorED ed) throws Excecoes {

    // System.out.println ("geraComissaoConhecimentos 1");

    String sql = null;
    byte[] b = null;

    try {

      sql = "  select Conhecimentos.oid_conhecimento, Conhecimentos.oid_Pessoa_Pagador, Conhecimentos.VL_Frete_Peso, Conhecimentos.oid_vendedor as oid_vendedor_conhecimento, Clientes.oid_vendedor as oid_vendedor_cliente ";
      sql += "  From Conhecimentos , Clientes ";
      sql += " WHERE Conhecimentos.oid_Pessoa_Pagador = Clientes.oid_cliente ";
      sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      sql += " and Conhecimentos.DM_Impresso = 'S' "
          + " AND Conhecimentos.DM_Situacao <> 'C' ";
      sql += " and Conhecimentos.VL_Total_Frete > 0";
      sql += " and Conhecimentos.DM_Tipo_Comissao <> 'C' ";
      sql += " and Clientes.OID_Vendedor = '" + ed.getOid_Vendedor () + "'";

      if ("M".equals (ed.getDM_Tipo_Documento ())) {
        sql += " and Conhecimentos.DM_Tipo_Documento='M' ";
      }
      if ("C".equals (ed.getDM_Tipo_Documento ())) {
        sql += " and Conhecimentos.DM_Tipo_Documento='C' ";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());
      // System.out.println ("geraComissaoConhecimentos 2");

      while (res.next ()) {
        if (!res.getString ("oid_vendedor_conhecimento").equals (res.getString ("oid_vendedor_cliente"))) {
          //         Comissao_VendedorED edV = new Comissao_VendedorED();
          //       ConhecimentoRN Conhecimentorn = new ConhecimentoRN();
          //     edV.setOID_Conhecimento(res.getString("oid_conhecimento"));
          //   edV.setOID_Vendedor(res.getString("vendedor_cliente"));
          //  Conhecimentorn.altera_Vendedor(edV);
        }
      }

      // System.out.println ("geraComissaoConhecimentos 3");

      if (parametro_FixoED.getDM_Criterio_Comissao ().equals ("TODOS_CTOS_LIQUIDO")) {

        sql = " select Conhecimentos.DT_Emissao, " +
            " Conhecimentos.oid_Pessoa_Pagador,  " +
            " Conhecimentos.NR_Conhecimento, " +
            " Conhecimentos.OID_Conhecimento, " +
            " Conhecimentos.nr_volumes, " +
            " Conhecimentos.VL_Frete_Peso, " +
            " Conhecimentos.dm_tipo_pagamento, " +
            " Conhecimentos.nr_duplicata, " +
            " Conhecimentos.vl_total_frete , " +
            " Conhecimentos.VL_Total_Custo , " +
            " Conhecimentos.VL_Custo_Coleta , " +
            " Conhecimentos.VL_Custo_Transferencia , " +
            " Conhecimentos.VL_Custo_Entrega , " +
            " Conhecimentos.VL_Custo_Imposto , " +
            " Conhecimentos.VL_Custo_Seguro , " +
            " Conhecimentos.VL_Custo_Outros , " +
            " Conhecimentos.VL_Custo_Monitoramento , " +
            " Conhecimentos.VL_Custo_Comunicacao , " +
            " Conhecimentos.VL_Custo_Gerenciamento_Risco , " +
            " Conhecimentos.VL_Custo_Comissao, " +
            " Conhecimentos.VL_Custo_Financeiro , " +
            " Conhecimentos.VL_Custo_Administrativo , " +
            " Conhecimentos.VL_Ressarcimento, " +
            " Conhecimentos.vl_icms, " +
            " conhecimentos.nr_peso, " +
            " Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente," +
            " Clientes.oid_Pessoa as CD_Cliente," +
            " Clientes.oid_Vendedor ," +
            " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
            " from Conhecimentos, Clientes, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario " +
            " where Conhecimentos.DM_Impresso = 'S' " +
            " AND Conhecimentos.DM_Situacao <> 'C' " +
            " AND Conhecimentos.VL_Total_Frete > 0" +
            " AND Conhecimentos.oid_Pessoa_Pagador = Clientes.oid_Pessoa " +
            " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
            " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

        if (String.valueOf (ed.getOid_Vendedor ()) != null && !String.valueOf (ed.getOid_Vendedor ()).equals ("0")) {
          sql += " and Clientes.OID_Vendedor = '" + ed.getOid_Vendedor () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null && !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao_Final ()) != null && !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
        }
        if ("M".equals (ed.getDM_Tipo_Documento ())) {
          sql += " and Conhecimentos.DM_Tipo_Documento='M' ";
        }
        if ("C".equals (ed.getDM_Tipo_Documento ())) {
          sql += " and Conhecimentos.DM_Tipo_Documento='C' ";
        }

        sql += " order by conhecimentos.oid_Pessoa_Pagador, conhecimentos.nr_conhecimento ";

      }
      // System.out.println (sql);

      if (parametro_FixoED.getDM_Criterio_Comissao ().equals ("DUPLICATA_PAGA_E_MARGEM")) {

        sql = " select Conhecimentos.DT_Emissao, Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, Conhecimentos.oid_Pessoa_Pagador, Conhecimentos.NR_Duplicata, Conhecimentos.VL_Frete_Peso, Conhecimentos.vl_total_frete,  Conhecimentos.vl_total_custo, Movimentos_Duplicatas.DT_Movimento "
            + " from  Movimentos_Duplicatas, Origens_Duplicatas, Tipos_Instrucoes, Conhecimentos "
            + " where Conhecimentos.DM_Impresso = 'S' "
            + " AND Conhecimentos.VL_Total_Frete > 0"
            + " AND Origens_duplicatas.dm_situacao <> 'E' "
            + " AND movimentos_Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata "
            + " AND Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao "
            + " AND (tipos_instrucoes.dm_altera_titulo = 'LTO' or tipos_instrucoes.dm_altera_titulo = 'LCA') "
            + " AND movimentos_duplicatas.VL_Credito > 0  "
            + " AND Origens_Duplicatas.oid_conhecimento = Conhecimentos.oid_Conhecimento "
            + " AND Movimentos_Duplicatas.DT_Movimento >= '" + ed.getDT_Emissao_Inicial () + "'"
            + " AND Movimentos_Duplicatas.DT_Movimento <= '" + ed.getDT_Emissao_Final () + "'";

        if (String.valueOf (ed.getOid_Vendedor ()) != null && !String.valueOf (ed.getOid_Vendedor ()).equals ("0")) {
          sql += " and Conhecimentos.OID_Vendedor = '" + ed.getOid_Vendedor () + "'";
        }
        if ("M".equals (ed.getDM_Tipo_Documento ())) {
          sql += " and Conhecimentos.DM_Tipo_Documento='M' ";
        }
        if ("C".equals (ed.getDM_Tipo_Documento ())) {
          sql += " and Conhecimentos.DM_Tipo_Documento='C' ";
        }

        sql += " order by  conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";

      }

      // System.out.println (sql);

      res = this.executasql.executarConsulta (sql.toString ());

      Comissao_VendedorRL conRL = new Comissao_VendedorRL ();
      //b = conRL.geraComissaoVendaCliente (res , ed , executasql);
      b = conRL.geraComissaoConhecimentos(res, ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  /**
   * Retorna a comissão para um dado vendedor e produto, obedecendo a estrutura de comissionamento da empresa
   * @param Comissao_VendedorED.oid_vendedor e Comissao_VendedorED.oid_produto
   * @return Comissao_VendedorED.pe_Comissao
   * @throws Excecoes
   */
  public Comissao_VendedorED getPEComissao (Comissao_VendedorED ed) throws Excecoes {

    Comissao_VendedorED edVolta = new Comissao_VendedorED ();
    double peComissao = 0;
    double peComissaoPr = 0;
    try {
      // Pega as caracteristicas do produto para o comissionamento.
      sql = "SELECT DISTINCT " +
          "Produtos.oid_produto 			as oid_produto, " +
          "Mix_produtos.oid_mix 			as oid_mix, " +
          "Produtos.oid_Estrutura_Produto as oid_estrutura_produto, " +
          "Produtos.oid_Tipo_Produto 		as oid_tipo_produto, " +
          "Produtos_Clientes.pe_comissao	as pe_comissao " +
          "FROM " +
          "Produtos, " +
          "Produtos_Clientes, " +
          "Mix_Produtos " +
          "WHERE " +
          "Produtos.oid_produto = Produtos_Clientes.oid_produto AND " +
          "Produtos.oid_produto = Mix_Produtos.oid_produto AND ";
      sql += "Produtos.oid_produto = " + ed.getOid_Produto ();
      ResultSet resPr = this.executasql.executarConsulta (sql);
      if (resPr.next ()) { // Se o produto existe no cadastro de produtos_clientes
        peComissaoPr = resPr.getDouble ("pe_comissao"); // Guarda a comissao para o  produto
        // Pega as comissoes do vendedor e ordena adequadamente pela estrutura de comissionamento da empresa.
        sql = "SELECT " +
            "oid_produto, " +
            "oid_mix, " +
            "oid_estrutura_produto, " +
            "oid_tipo_produto, " +
            "pe_comissao, " +
            "dm_situacao  " +
            "FROM " +
            "Comissoes_Vendedores " +
            "WHERE ";
        sql += "oid_vendedor = '" + ed.getOid_Vendedor () + "' and " +
            "dm_Situacao = 'A' " +
            "ORDER BY " +
            "(case when oid_produto > 0 then 0 else 1 end) , " +
            "(case when oid_mix > 0 then 0 else 1 end), " +
            "(case when oid_estrutura_produto > 0 then 0 else 1 end), " +
            "(case when oid_tipo_produto > 0 then 0 else 1 end) ";
        ResultSet resCv = this.executasql.executarConsulta (sql);

        //Procura na estrutura de comissionamento o percentual correspondente
        while (resCv.next ()) {

          if (resCv.getInt ("oid_produto") == ed.getOid_Produto ()) { // Comissão do vendedor para o produto
            peComissao = resCv.getDouble ("pe_comissao");
            break;
          }
          if (resCv.getInt ("oid_mix") == resPr.getInt ("oid_mix")) { // Comissão do vendedor para o mix
            peComissao = resCv.getDouble ("pe_comissao");
            break;
          }
          if (resCv.getInt ("oid_estrutura_produto") == resPr.getInt ("oid_estrutura_produto")) { // Comissão do vendedor para a estrutura de produto
            peComissao = resCv.getDouble ("pe_comissao");
            break;
          }
          if (resCv.getInt ("oid_tipo_produto") == resPr.getInt ("oid_tipo_produto")) { // Comissão do vendedor para o tipo de produto
            peComissao = resCv.getDouble ("pe_comissao");
            break;
          }
        }
      }
      else { // O produto não está cadastrado em produto_cliente então retorna 0=zero
        peComissaoPr = 0;
      }
      // Se não achou nada até aqui pega a comissão do vendedor, direto
      if (peComissao == 0) {
        sql = "SELECT " +
            "pe_comissao " +
            "FROM " +
            "Vendedores " +
            "WHERE ";
        sql += "oid_vendedor = '" + ed.getOid_Vendedor () + "' ";
        ResultSet resVe = this.executasql.executarConsulta (sql);
        resVe.next ();
        peComissao = resVe.getDouble ("pe_comissao");
        if (peComissao == 0) { // Se o vendedor não tem comissao pra ele só resta pegar a do produto, direto
          peComissao = peComissaoPr;
        }
      }
      edVolta.setPE_Comissao (peComissao);
      return edVolta;

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getPEComissao(Comissao_Vededor)");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  /**
   * Retorna a comissão de venda direta para um dado vendedor
   * @param Comissao_VendedorED.oid_vendedor
   * @return Comissao_VendedorED.pe_Comissao
   * @throws Excecoes
   */
  public Comissao_VendedorED getPEComissaoVD (Comissao_VendedorED ed) throws Excecoes {

    Comissao_VendedorED edVolta = new Comissao_VendedorED ();
    double peComissao = 0;
    try {
      sql = "SELECT " +
          "pe_comissaovd " +
          "FROM " +
          "Vendedores " +
          "WHERE ";
      sql += "oid_vendedor = '" + ed.getOid_Vendedor () + "' ";
      ResultSet resVe = this.executasql.executarConsulta (sql);
      resVe.next ();
      peComissao = resVe.getDouble ("pe_comissaovd");
      edVolta.setPE_Comissao (peComissao);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getPEComissao(Comissao_Vededor)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

}
