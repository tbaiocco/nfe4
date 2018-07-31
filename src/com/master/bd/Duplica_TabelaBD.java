package com.master.bd;

import java.sql.ResultSet;

import com.master.ed.Duplica_TabelaED;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

public class Duplica_TabelaBD
    extends Transacao {
  double PE_Desc_FP = 1;
  double PE_Desc_FV = 1;
  double PE_Desc_TX = 1;

  private ExecutaSQL executasql;

  public Duplica_TabelaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui_km (Duplica_TabelaED ed) throws Excecoes {

    ////// System.out.println("ok bd 1");


    String sql = null;
    long valOid = 0;
    String chave = null;

    if (ed.getPE_Desconto () > 0) {
      PE_Desc_FP = (100 - ed.getPE_Desconto ()) / 100;
    }

    try {

      sql = " SELECT * FROM Tabelas_Fretes ";
      sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto_Origem ();

      ////// System.out.println(sql);

      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sql);
      Data data = new Data ();
      FormataDataBean DataFormatada = new FormataDataBean ();

      int qt_tabelas = 0;
      while (rs.next ()) {
        this.inicioTransacao ();
        this.executasql = this.sql;

        String incluiu = "N";

        this.fimTransacao (true);
        qt_tabelas++;
        // System.out.println ("Copia tabela " + qt_tabelas);
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Duplica_Tabela");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void copia_Tabela (Duplica_TabelaED ed) throws Excecoes {


    String sql = null;
    try {

      sql = " SELECT * FROM Tabelas_Fretes ";
      sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

      if (ed.getOID_Produto_Origem () > 0) {
        sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto_Origem ();
      }

      if (!"T".equals (ed.getDM_Aplicacao ())) {
        sql += " AND Tabelas_Fretes.DM_Aplicacao = '" + ed.getDM_Aplicacao () + "'";
      }

      // System.out.println (sql);

      ResultSet rs = null;
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro em copiar Tabela");
      excecoes.setMetodo ("copia_Tabela");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
