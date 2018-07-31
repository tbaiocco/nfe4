/*
 * Created on 03/09/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Comissao_AgenciamentoED;
import com.master.rl.Comissao_AgenciamentoRL;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author Andre Valadas
 */
public class Comissao_AgenciamentoBD {

  private ExecutaSQL executasql;
  private String sql = null;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public Comissao_AgenciamentoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Comissao_AgenciamentoED inclui (Comissao_AgenciamentoED ed) throws Excecoes {

    int valOid = 0;
    Comissao_AgenciamentoED edVolta = new Comissao_AgenciamentoED ();

    try {


        sql = " insert into comissoes_vendedores (";
        sql += "	oid_Comissao_Agenciamento, ";
        sql += " 	oid_Vendedor, ";
        sql += " 	pe_Comissao, ";
        sql += "	oid_produto,";
        sql += "	dm_situacao) ";
        sql += " values (";
        sql += ++valOid + ",";
        sql += ed.getPE_Comissao () + ",";
        sql += ed.getOid_Produto () + ",";
        sql += "'" + ed.getDM_Situacao () + "')";

        //*** SQL
         //// System.err.println("SQL Incluir >> "+sql);

         executasql.executarUpdate (sql);
        edVolta.setOid_Comissao_Agenciamento (valOid);

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

  public void altera (Comissao_AgenciamentoED ed) throws Excecoes {

    try {

      sql = " update comissoes_vendedores set ";
      sql += " pe_Comissao  = " + ed.getPE_Comissao () + ", ";
      sql += " oid_produto  = " + ed.getOid_Produto () + ", ";
      sql += " dm_situacao  = '" + ed.getDM_Situacao () + "'";
      sql += " where oid_Comissao_Agenciamento = " + ed.getOid_Comissao_Agenciamento ();

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

  public void deleta (Comissao_AgenciamentoED ed) throws Excecoes {

    try {

      sql = " delete from comissoes_vendedores " +
          " where oid_Comissao_Agenciamento = " + ed.getOid_Comissao_Agenciamento ();

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

  public ArrayList lista (Comissao_AgenciamentoED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    ResultSet auxRes = null;

    try {


       ResultSet res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {

        Comissao_AgenciamentoED edVolta = new Comissao_AgenciamentoED ();

        edVolta.setOid_Comissao_Agenciamento (res.getInt ("oid_Comissao_Agenciamento"));
        edVolta.setPE_Comissao (res.getDouble ("pe_Comissao"));
        edVolta.setDM_Situacao (res.getString ("dm_situacao"));
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

  public Comissao_AgenciamentoED getByRecord (Comissao_AgenciamentoED ed) throws Excecoes {

    Comissao_AgenciamentoED edVolta = new Comissao_AgenciamentoED ();
    ResultSet auxRes = null;

    try {

      sql = " select c.oid_Comissao_Agenciamento as oid_Comissao_Agenciamento, "
          + " 	c.oid_Vendedor as oid_Vendedor, "
          + " 	c.pe_Comissao as pe_Comissao, "
          + " 	c.oid_mix as oid_mix,"
          + "	c.oid_produto as oid_produto,"
          + "	c.oid_tipo_produto as oid_tipo_produto,"
          + "	c.oid_estrutura_produto as oid_estrutura_produto,"
          + "	c.dm_situacao as dm_situacao"
          + " from comissoes_vendedores c";
      sql += " where c.oid_Comissao_Agenciamento = " + ed.getOid_Comissao_Agenciamento ();

      //*** SQL
       //// System.err.println("getByRecord Comissao >> "+sql);

       ResultSet res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {

        edVolta.setOid_Comissao_Agenciamento (res.getInt ("oid_Comissao_Agenciamento"));
        edVolta.setPE_Comissao (res.getDouble ("pe_Comissao"));
        edVolta.setDM_Situacao (res.getString ("dm_situacao"));

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

  public byte[] gera_Comissao_Agenciamento (Comissao_AgenciamentoED ed) throws Excecoes {

    // System.out.println ("gera_Comissao_Agenciamento 1");

    String sql = null;
    byte[] b = null;

    try {

      sql = "  SELECT Conhecimentos.oid_conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.NR_conhecimento, Conhecimentos.VL_Frete_Peso, Conhecimentos.VL_Total_Frete, Conhecimentos.oid_Unidade, Conhecimentos.DT_Emissao, Conhecimentos.NR_Peso, Conhecimentos.TX_Observacao " +
          "  FROM Conhecimentos " +
          " WHERE Conhecimentos.DM_Impresso = 'S' " +
          " AND   Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'" +
          " AND   Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'" +
          " AND   Conhecimentos.DM_Impresso = 'S' " +
          " AND   Conhecimentos.DM_Situacao <> 'C' " +
          " AND   Conhecimentos.VL_Total_Frete > 0" +
          //" AND   Conhecimentos.DM_Tipo_Comissao <> 'C' " +
          " AND   Conhecimentos.OID_Unidade = '" + ed.getOid_Unidade() + "'";

      if ("M".equals (ed.getDM_Tipo_Documento ())) {
        sql += " and Conhecimentos.DM_Tipo_Documento='M' ";
      }
      if ("C".equals (ed.getDM_Tipo_Documento ())) {
        sql += " and Conhecimentos.DM_Tipo_Documento='C' ";
      }

      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql.toString ());

      Comissao_AgenciamentoRL conRL = new Comissao_AgenciamentoRL ();
      b = conRL.gera_Comissao_Agenciamento(res, ed,executasql);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("gera_Comissao_Agenciamento(ConhecimentoED ed)");
    }
    return b;
  }

}
