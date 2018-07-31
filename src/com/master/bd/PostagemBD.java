package com.master.bd;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class PostagemBD
    extends Transacao {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  Parametro_FixoED paramED = new Parametro_FixoED ();
  CidadeBean cidadeBean = new CidadeBean ();

  public PostagemBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public PostagemED inclui (PostagemED ed) throws Excecoes {

    String sql = null;

    // System.out.println (" INCLUI BD 1");

    PostagemED conED = new PostagemED ();

    try {

      sql = " SELECT oid_Postagem FROM Postagens WHERE NR_Postagem=" + ed.getNR_Postagem ();
      ResultSet res = this.executasql.executarConsulta (sql);
      if (!res.next ()) {

        ed.setOID_Postagem (String.valueOf (System.currentTimeMillis ()).toString ());

        sql = " INSERT into POSTAGENS (" +
            " OID_Postagem, " +
            " OID_Pessoa, " +
            " OID_Pessoa_Destinatario, " +
            " OID_Conhecimento, " +
            " NR_Lote, " +
            " NR_Postagem, " +
            " NR_Volumes, " +
            " DT_Entrada, " +
            " HR_Entrada, " +
            " NM_Servico, " +
            " NM_Destinatario, " +
            " NM_Endereco, " +
            " NM_Bairro, " +
            " NM_Cidade, " +
            " CD_Estado, " +
            " DM_Tipo_Servico, " +
            " CD_Destino, " +
            " NM_Unidade_Postagem, " +
            " VL_Unitario, " +
            " VL_Servico, " +
            " VL_Desconto, " +
            " VL_Postagem, " +
            " DM_Situacao, " +
            " DT_Postagem, " +
            " NR_Fatura, " +
            " NR_Peso, " +
            " NR_Peso_Cubado,  " +
            " NR_Nota_Fiscal,  " +
            " oid_Modal  " +
            ") values ";

        sql += "('" +
            ed.getOID_Postagem () + "','" +
            ed.getOID_Pessoa () + "','" +
            ed.getOID_Pessoa_Destinatario () + "','" +
            ed.getOID_Conhecimento () + "','" +
            ed.getNR_Lote () + "'," +
            ed.getNR_Postagem () + "," +
            ed.getNR_Volumes () + ",'" +
            Data.getDataDMY () + "','" +
            Data.getHoraHM () + "','" +
            ed.getNM_Servico () + "','" +
            ed.getNM_Destinatario () + "','" +
            ed.getNM_Endereco () + "','" +
            ed.getNM_Bairro () + "','" +
            ed.getNM_Cidade () + "','" +
            ed.getCD_Estado () + "','" +
            ed.getDM_Tipo_Servico () + "','" +
            ed.getCD_Destino () + "','" +
            ed.getNM_Unidade_Postagem () + "'," +
            ed.getVL_Unitario () + "," +
            ed.getVL_Servico () + "," +
            ed.getVL_Desconto () + "," +
            ed.getVL_Postagem () + ",'" +
            ed.getDM_Situacao () + "','" +
            Data.getDataDMY () + "','" +
            ed.getNR_Fatura () + "'," +
            ed.getNR_Peso () + "," +
            ed.getNR_Peso_Cubado () + "," +
            ed.getNR_Nota_Fiscal () + "," +
            ed.getOID_Modal () +
            ")";

        // System.out.println (sql);

        int i = executasql.executarUpdate (sql);

        conED.setOID_Postagem (ed.getOID_Postagem ());
      }

    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(PostagemED ed)");
    }
    catch (Exception ex) {
      ex.printStackTrace ();
      throw new Excecoes (ex.getMessage () , ex , null , "");
    }
    return conED;
  }

  public void altera (PostagemED ed) throws Excecoes {

    String sql = null;

    try {
      
      if (ed.getNR_Fatura () != null && !ed.getNR_Fatura ().equals ("") && !ed.getNR_Fatura ().equals ("null")) {
        sql = " UPDATE Postagens set NR_Lote= '" + ed.getNR_Lote () + "'" +
            "  ,NR_Volumes= " + ed.getNR_Volumes () +
            "  ,OID_Modal= " + ed.getOID_Modal () +
            "  ,NR_Peso= " + ed.getNR_Peso () +
            "  ,NR_Peso_Cubado= " + ed.getNR_Peso_Cubado () +
            "  ,VL_Unitario = " + ed.getVL_Unitario () +
            "  ,VL_Desconto = " + ed.getVL_Desconto () +
            "  ,VL_Servico = " + ed.getVL_Servico () +
            "  ,VL_Postagem = " + ed.getVL_Postagem () +
            "  ,DT_Postagem = " + util.getSQLStringDef (ed.getDT_Postagem () , "") +
            "  ,NM_Servico = " + util.getSQLStringDef (ed.getNM_Servico () , "") +
            "  ,NR_Fatura = " + util.getSQLStringDef (ed.getNR_Fatura () , "") +
            "  ,CD_Destino = " + util.getSQLStringDef (ed.getCD_Destino () , "") +
            "  ,NM_Unidade_Postagem = " + util.getSQLStringDef (ed.getNM_Unidade_Postagem () , "") +
            " WHERE oid_Postagem = '" + ed.getOID_Postagem () + "'";
      }
      else {
        sql = " UPDATE Postagens set NR_Lote= '" + ed.getNR_Lote () + "'" +
            "  ,NR_Volumes= " + ed.getNR_Volumes () +
            "  ,OID_Modal= " + ed.getOID_Modal () +
            "  ,oid_Pessoa = " + util.getSQLStringDef (ed.getOID_Pessoa () , "") +
            "  ,NR_Peso= " + ed.getNR_Peso () +
            "  ,NR_Peso_Cubado= " + ed.getNR_Peso_Cubado () +
            "  ,DT_Postagem = " + util.getSQLStringDef (ed.getDT_Postagem () , "") +
            "  ,NM_Servico = " + util.getSQLStringDef (ed.getNM_Servico () , "") +
            "  ,NM_Destinatario = " + util.getSQLStringDef (ed.getNM_Destinatario () , "") +
            "  ,NM_Endereco = " + util.getSQLStringDef (ed.getNM_Endereco () , "") +
            "  ,NM_Cidade = " + util.getSQLStringDef (ed.getNM_Cidade () , "") +
            "  ,CD_Estado = " + util.getSQLStringDef (ed.getCD_Estado () , "") +
            "  ,CD_Destino = " + util.getSQLStringDef (ed.getCD_Destino () , "") +
            "  ,NM_Unidade_Postagem = " + util.getSQLStringDef (ed.getNM_Unidade_Postagem () , "") +
            " WHERE oid_Postagem = '" + ed.getOID_Postagem () + "'";
      }
      

      // System.out.println (sql);
      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deletaFatura (PostagemED ed) throws Excecoes {

      // System.out.println ("deletaFatura ");

    String sql = null;
    ResultSet res = null;

    try {
    
      sql = " SELECT  oid_Postagem " +
          " FROM   Postagens  " +
          " WHERE  Postagens.NR_Fatura='" + ed.getNR_Fatura () + "'";

      // System.out.println ("sql Postagem:" + sql);
    
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
    
        sql = " UPDATE Postagens SET VL_Unitario = 0, VL_Desconto= 0, VL_Servico= 0, NM_Servico='' , NR_Fatura = '', NM_Unidade_Postagem = '' " +
              " WHERE oid_Postagem = '" + res.getString ("oid_Postagem") + "'";
    
        // System.out.println (sql);
        executasql.executarUpdate (sql);
        // System.out.println ("deletaFatura Postagem:" + res.getString ("oid_Postagem"));
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletaFatura");
      excecoes.setMetodo ("deletaFatura");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deletaLote (PostagemED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    try {

      sql = " SELECT  oid_Postagem " +
          " FROM   Postagens  " +
          " WHERE  Postagens.NR_Lote='" + ed.getNR_Lote () + "'" +
          "   AND  Postagens.NR_Fatura is null ";

      // System.out.println ("sql Postagem:" + sql);

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        // System.out.println ("exclui Postagem:" + res.getString ("oid_Postagem"));

        PostagemED edVolta = new PostagemED ();
        edVolta.setOID_Postagem (res.getString ("oid_Postagem"));
        this.deleta (edVolta);
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (PostagemED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet resCto = null;

    try {

      sql = " SELECT  NR_Postagem " +
          " FROM   Postagens  " +
          " WHERE  Postagens.NR_Postagem > 0 " +
          " AND    Postagens.oid_Postagem='" + ed.getOID_Postagem () + "'";
      // System.out.println ("sql Postagem:" + sql);

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        sql = " SELECT  oid_Conhecimento " +
            " FROM   Conhecimentos  " +
            " WHERE  Conhecimentos.DM_Situacao <>'C' " +
            " AND    Conhecimentos.DM_Impresso ='N' " +
            " AND    Conhecimentos.NR_Postagem ='" + res.getString ("NR_Postagem") + "'";
        // System.out.println ("sql Postagem:" + sql);

        resCto = this.executasql.executarConsulta (sql);
        while (resCto.next ()) {
          ConhecimentoED conED = new ConhecimentoED ();
          conED.setOID_Conhecimento (resCto.getString ("oid_Conhecimento"));
          new ConhecimentoBD (executasql).deleta (conED);
        }

        sql = " SELECT  oid_Conhecimento " +
            " FROM   Conhecimentos  " +
            " WHERE  Conhecimentos.DM_Impresso ='S' " +
            " AND    Conhecimentos.NR_Postagem ='" + res.getString ("NR_Postagem") + "'";
        // System.out.println ("sql Postagem:" + sql);

        resCto = this.executasql.executarConsulta (sql);
        if (resCto.next ()) {
        	String NR_Postagem_Cto="";
        	sql = " SELECT  NR_Postagem  " +
            " FROM   Postagens  " +
            " WHERE  Postagens.oid_Conhecimento ='" + resCto.getString ("oid_Conhecimento") + "'" +
            " AND    Postagens.oid_Postagem <>'" + ed.getOID_Postagem () + "'";
        	ResultSet resPost = this.executasql.executarConsulta (sql);
            if (resPost.next ()) {
            	NR_Postagem_Cto=resPost.getString("NR_Postagem");
            }
        	
        	sql = " UPDATE Conhecimentos SET NR_Postagem='"+NR_Postagem_Cto+"' WHERE oid_Conhecimento= '" + resCto.getString("oid_Conhecimento") + "'";
            executasql.executarUpdate (sql);
        }

        sql = "delete from POSTAGENS WHERE oid_Postagem = ";
        sql += "('" + ed.getOID_Postagem () + "')";
        executasql.executarUpdate (sql);
      
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (PostagemED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  * " +
          " FROM   POSTAGENS WHERE 1=1 ";

      if (String.valueOf (ed.getNR_Postagem ()) != null &&
          !String.valueOf (ed.getNR_Postagem ()).equals ("0") &&
          !String.valueOf (ed.getNR_Postagem ()).equals ("null")) {
        sql += " and POSTAGENS.NR_Postagem = " + ed.getNR_Postagem ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and POSTAGENS.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getNR_Fatura ()) != null &&
          !String.valueOf (ed.getNR_Fatura ()).equals ("") &&
          !String.valueOf (ed.getNR_Fatura ()).equals ("null")) {
        sql += " and POSTAGENS.NR_Fatura = '" + ed.getNR_Fatura () + "'";
      }

      if (String.valueOf (ed.getNR_Lote ()) != null &&
          !String.valueOf (ed.getNR_Lote ()).equals ("") &&
          !String.valueOf (ed.getNR_Lote ()).equals ("null")) {
        sql += " and POSTAGENS.NR_Lote = '" + ed.getNR_Lote () + "'";
      }

      if (String.valueOf (ed.getDT_Postagem_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Postagem_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Postagem_Inicial ()).equals ("null")) {
        sql += " and POSTAGENS.DT_Postagem >= '" + ed.getDT_Postagem_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Postagem_Final ()) != null &&
          !String.valueOf (ed.getDT_Postagem_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Postagem_Final ()).equals ("null")) {
        sql += " and POSTAGENS.DT_Postagem <= '" + ed.getDT_Postagem_Final () + "'";
      }
      sql += " Order by POSTAGENS.NR_Fatura, POSTAGENS.DT_Postagem , POSTAGENS.NR_Postagem ";

      // System.out.println ("sql Postagem:" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula

      boolean listar = false;
      while (res.next ()) {
        PostagemED edVolta = new PostagemED ();
        // System.out.println ("wh ");

        edVolta = this.carregaED (res);

        // System.out.println ("wh volta= sit>>+"+ed.getDM_Situacao ());

        listar = true;
        if ("C".equals(ed.getDM_Situacao ()) || "N".equals(ed.getDM_Situacao ()) || "S".equals(ed.getDM_Situacao ())){
          listar = false;
          if ("C".equals (ed.getDM_Situacao ()) && !" ".equals (edVolta.getNR_Conhecimento ()) && "S".equals(edVolta.getDM_Conhecimento_Impresso())) {
            listar = true;
          }
          if ("N".equals (ed.getDM_Situacao ()) && !" ".equals (edVolta.getNR_Conhecimento ()) && "N".equals(edVolta.getDM_Conhecimento_Impresso())) {
            listar = true;
          }
          if ("S".equals (ed.getDM_Situacao ()) && " ".equals (edVolta.getNR_Conhecimento ())) {
            listar = true;
          }
        }
        if (listar) {
          // System.out.println ("wh add");
          list.add (edVolta);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(PostagemED ed)");
    }

    return list;
  }

  public ArrayList listaPostagemConhecimento (PostagemED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  Postagens.*,  " +
          "         Conhecimentos.NR_Conhecimento  " +
          " FROM   Postagens, Conhecimentos " +
          " WHERE  Postagens.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
          " AND    Postagens.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'" +
          " ORDER  by Postagens.NR_Postagem ";

      // System.out.println ("sql Postagem:" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula

      boolean listar = false;
      while (res.next ()) {
        PostagemED edVolta = new PostagemED ();
        // System.out.println ("wh ");

        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setNR_Fatura (res.getString ("NR_Fatura"));
        edVolta.setVL_Postagem (res.getDouble ("VL_Postagem"));
        edVolta.setNR_Postagem (res.getLong ("NR_Postagem"));
        edVolta.setOID_Postagem (res.getString ("OID_Postagem"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        // System.out.println ("wh volta");
        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "listaPostagemConhecimento(PostagemED ed)");
    }

    return list;
  }

  public PostagemED getByRecord (PostagemED ed) throws Excecoes {

    String sql = null;
    PostagemED edVolta = new PostagemED ();
    ResultSet resP = null;

    try {

      sql = " SELECT * from POSTAGENS WHERE POSTAGENS.OID_Postagem = '" + ed.getOID_Postagem () + "'";
      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        edVolta = this.carregaED (res);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void importa_Arquivo (String arquivo , String tipo , String aux) throws Excecoes {

    if ("FATURA".equals (tipo)) {
      this.importa_Fatura (arquivo , aux);
    }
    if ("POSTAGEM".equals (tipo)) {
      this.importa_Postagem (arquivo , aux , "");
    }

  }

  private void importa_Fatura (String arquivo , String NR_Fatura) throws Excecoes {

    String NM_Registro = "";
    String Incluir = "S";
    // System.out.println ("importa_Fatura -->> " + arquivo);

    int linha = 0;
    double var = 0;

    try {
      ManipulaArquivo man = new ManipulaArquivo ("");
      LineNumberReader line = man.leLinha (arquivo);

      if (line.ready ()) {

        // System.out.println ("Line Ready");

        while ( (NM_Registro = line.readLine ()) != null) {

          // System.out.println ("Line ->>" + linha + " - " + NM_Registro);

          if (NM_Registro != null && NM_Registro.length () > 10) {

            Incluir = "N";

            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
            NM_Registro = NM_Registro.toUpperCase ();
            ArrayList lista = man.leStringDelimitado (NM_Registro , ";" , 0);

            // System.out.println ("jogou para lastIndexOf=> " + lista.lastIndexOf (lista));
            // System.out.println ("jogou para indexOf =>" + lista.indexOf (lista));

            String nr_postagem = "";
            String dt_postagem = "";
            String nr_peso = "";
            String nr_lote = "";
            String nm_servico = "";
            String cd_destino = "";
            String nm_unidade_postagem = "";
            String vl_unitario = "";
            String vl_servico = "";
            String vl_desconto = "";
            String vl_postagem = "";
            if (lista.size () != 0) {
              dt_postagem = (String) lista.get (0).toString ().trim ();
              nm_servico = (String) lista.get (1).toString ().trim ();
              nr_postagem = (String) lista.get (3).toString ().trim ();

              nr_lote = (String) lista.get (4).toString ().trim ();
              cd_destino = (String) lista.get (6).toString ().trim ();
              nm_unidade_postagem = (String) lista.get (7).toString ().trim ();
              nr_peso = (String) lista.get (8).toString ().trim ();
              vl_unitario = (String) lista.get (10).toString ().trim ();
              vl_servico = (String) lista.get (12).toString ().trim ();
            }
            vl_unitario = vl_unitario.replaceAll ("," , ".");
            vl_servico = vl_servico.replaceAll ("," , ".");
            vl_desconto = vl_desconto.replaceAll ("," , ".");
            vl_postagem = vl_postagem.replaceAll ("," , ".");

            PostagemED importaED = new PostagemED ();

            importaED.setNR_Fatura (NR_Fatura);
            importaED.setDT_Postagem (dt_postagem);
            importaED.setNR_Postagem (SeparaEndereco.confirmaValorLong (nr_postagem));
            importaED.setNM_Servico (nm_servico);
            importaED.setNR_Lote (nr_lote);
            importaED.setCD_Destino (cd_destino);
            importaED.setDT_Entrada (Data.getDataDMY ());
            importaED.setHR_Entrada (Data.getHoraHM ());
            importaED.setNM_Unidade_Postagem (nm_unidade_postagem);
            var = SeparaEndereco.confirmaValorDouble (nr_peso);
            importaED.setNR_Peso (var / 1000);
            importaED.setNR_Peso_Cubado (var / 1000);
            importaED.setDM_Situacao ("F");

            var = SeparaEndereco.confirmaValorDouble (vl_unitario);
            importaED.setVL_Unitario (var);

            var = SeparaEndereco.confirmaValorDouble (vl_desconto);
            importaED.setVL_Desconto (var);

            var = SeparaEndereco.confirmaValorDouble (vl_servico);
            importaED.setVL_Servico (var);

            var = SeparaEndereco.confirmaValorDouble (vl_postagem);
            importaED.setVL_Postagem (importaED.getVL_Servico () - importaED.getVL_Desconto ()); ;

            String sqlBusca = " SELECT oid_Postagem FROM Postagens WHERE NR_Postagem=" + importaED.getNR_Postagem ();
            ResultSet res = this.executasql.executarConsulta (sqlBusca);
            if (res.next ()) {
              importaED.setOID_Postagem (res.getString ("oid_Postagem"));
              this.altera (importaED);
            }
            else {
              this.inclui (importaED);
            }
          }
          linha++;
          line.setLineNumber (linha);
        }
      }
      line.close ();
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("importa_Fatura");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  private void importa_Postagem (String arquivo , String oid_Pessoa_Remetente , String nr_lote) throws Excecoes {

    String NM_Registro = "";
    String Incluir = "S";
    // System.out.println ("importa_Postagem -->> " + arquivo);

    int linha = 0;
    double var = 0;

    try {
      ManipulaArquivo man = new ManipulaArquivo ("");
      LineNumberReader line = man.leLinha (arquivo);

      if (line.ready ()) {

        // System.out.println ("Line Ready");

        while ( (NM_Registro = line.readLine ()) != null) {

          // System.out.println ("Line ->>" + linha + " - " + NM_Registro);

          if (NM_Registro != null && NM_Registro.length () > 10) {

            Incluir = "N";

            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
            NM_Registro = NM_Registro.toUpperCase ();
            ArrayList lista = man.leStringDelimitado (NM_Registro , ";" , 0);

            // System.out.println ("jogou para lastIndexOf=> " + lista.lastIndexOf (lista));
            // System.out.println ("jogou para indexOf =>" + lista.indexOf (lista));

            String nr_postagem = "";
            String dt_postagem = "";
            String nr_peso = "";
            String nm_servico = "";
            String nm_destinatario = "";
            String nm_endereco = "";
            String nm_bairro = "";
            String nm_cidade = "";
            String cd_estado = "";
            String cd_destino = "";
            String dm_tipo_servico = "";
            String nm_unidade_postagem = "";
            String nr_nota_fiscal = "";
            if (lista.size () != 0) {
              dt_postagem = (String) lista.get (0).toString ().trim ();

              nm_unidade_postagem = (String) lista.get (3).toString ().trim ();

              nm_servico = (String) lista.get (6).toString ().trim () + " - " + (String) lista.get (7).toString ().trim ();
              nr_postagem = (String) lista.get (8).toString ().trim ();

              nr_postagem = (String) lista.get (8).toString ().trim ();
              dm_tipo_servico = nr_postagem.substring (0 , 2);

              if (oid_Pessoa_Remetente.equals ("92754738002700") || oid_Pessoa_Remetente.equals ("00323324000160")) {
                nr_postagem = String.valueOf (System.currentTimeMillis ()).substring (6 , 12); //SeparaEndereco.corrigeNumero (nr_postagem);
              }
              else {
                nr_postagem = SeparaEndereco.corrigeNumero (nr_postagem);
              }

              nr_peso = (String) lista.get (10).toString ().trim ();

              nm_destinatario = (String) lista.get (11).toString ().trim ();
              nm_endereco = (String) lista.get (12).toString ().trim ();
              nm_bairro = (String) lista.get (13).toString ().trim ();
              nm_cidade = (String) lista.get (14).toString ().trim ();
              cd_estado = (String) lista.get (15).toString ().trim ();
              cd_destino = (String) lista.get (16).toString ().trim ();
              nr_nota_fiscal = (String) lista.get (19).toString ().trim ();
            }
            // System.out.println ("dt_postagem ->>" + dt_postagem);
            // System.out.println ("dm_tipo_servico ->>" + dm_tipo_servico);
            // System.out.println ("nr_postagem ->>" + nr_postagem);
            // System.out.println ("nm_servico ->>" + nm_servico);
            // System.out.println ("cd_destino ->>" + cd_destino);
            // System.out.println ("nm_destinatario ->>" + nm_destinatario);
            // System.out.println ("nm_endereco ->>" + nm_endereco);
            // System.out.println ("nm_bairro ->>" + nm_bairro);
            // System.out.println ("nm_cidade ->>" + nm_cidade);
            // System.out.println ("cd_estado ->>" + cd_estado);
            // System.out.println ("cd_destino ->>" + cd_destino);
            // System.out.println ("nm_unidade_postagem ->>" + nm_unidade_postagem);
            // System.out.println ("nr_peso ->>" + nr_peso);
            // System.out.println ("nr_nota_fiscal ->>" + nr_nota_fiscal);

            PostagemED importaED = new PostagemED ();

            importaED.setNR_Fatura ("");
            importaED.setOID_Pessoa (oid_Pessoa_Remetente);
            importaED.setDT_Postagem (dt_postagem);
            importaED.setNR_Postagem (SeparaEndereco.confirmaValorLong (nr_postagem));
            importaED.setNM_Servico (nm_servico);
            importaED.setNM_Destinatario (nm_destinatario);
            importaED.setNM_Endereco (nm_endereco);
            importaED.setNM_Bairro (nm_bairro);
            importaED.setNM_Cidade (nm_cidade);
            importaED.setCD_Estado (cd_estado);
            importaED.setNR_Lote (nr_lote);
            importaED.setCD_Destino (cd_destino);
            importaED.setDM_Tipo_Servico (dm_tipo_servico);
            importaED.setDT_Entrada (Data.getDataDMY ());
            importaED.setHR_Entrada (Data.getHoraHM ());
            importaED.setNM_Unidade_Postagem (nm_unidade_postagem);
            var = SeparaEndereco.confirmaValorDouble (nr_peso);
            importaED.setNR_Peso (var / 1000);
            importaED.setNR_Peso_Cubado (var / 1000);
            importaED.setDM_Situacao ("P");
            importaED.setNR_Nota_Fiscal (SeparaEndereco.confirmaValorLong (nr_nota_fiscal));

            String sqlBusca = " SELECT oid_Postagem FROM Postagens WHERE NR_Postagem=" + importaED.getNR_Postagem ();
            ResultSet res = this.executasql.executarConsulta (sqlBusca);
            if (res.next ()) {
              importaED.setOID_Postagem (res.getString ("oid_Postagem"));
              this.altera (importaED);
            }
            else {
              this.inclui (importaED);
            }
          }
          linha++;
          line.setLineNumber (linha);
        }
      }
      line.close ();
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("importa_Fatura");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList gera_Remessa (PostagemED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    ResultSet rs = null , res = null , resCli = null;
    String sql = "" , OID_Nota_Fiscal = null , oid_Pessoa_Destinatario = "";
    String DM_Isencao_Seguro = "" , oid_Vendedor = "" , DM_Tipo_Documento = "M";
    long oid_Empresa = 0 , oid_Cidade_Origem = 0 , nr_lote = 0;
    try {

      sql = " SELECT max(nr_lote) as nr_lote FROM Postagens ";
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        nr_lote = res.getLong ("nr_lote") + 1;
      }

      // System.out.println ("NR_Lote=" + nr_lote);

      importa_Postagem (ed.getNM_Arquivo () , ed.getOID_Pessoa () , String.valueOf (nr_lote));

      PostagemED edPostagem = new PostagemED ();
      // System.out.println ("Passei ");

      sql = " SELECT * FROM Unidades WHERE Unidades.oid_Unidade = " + ed.getOID_Unidade ();
      // System.out.println ("Unidade=" + sql);
      resCli = this.executasql.executarConsulta (sql);
      while (resCli.next ()) {

        // System.out.println ("Unidade ok ");

        oid_Empresa = resCli.getLong ("oid_Empresa");
        DM_Tipo_Documento = resCli.getString ("dm_tipo_documento_padrao");
      }

      // System.out.println ("DM_Tipo_Documento=>" + DM_Tipo_Documento);

      sql = " SELECT oid_Cidade, DM_Isencao_Seguro, oid_Vendedor FROM Pessoas, Clientes WHERE Pessoas.oid_Pessoa = Clientes.oid_Cliente AND Clientes.oid_Cliente = '" + ed.getOID_Pessoa () + "'";
      resCli = this.executasql.executarConsulta (sql);
      while (resCli.next ()) {
        // System.out.println (" tem cliente");
        DM_Isencao_Seguro = resCli.getString ("DM_Isencao_Seguro");
        oid_Vendedor = resCli.getString ("oid_Vendedor");
        oid_Cidade_Origem = resCli.getLong ("oid_Cidade");
      }

      // System.out.println ("Passei Cliente=" + sql);

      //      sql = " SELECT * FROM Postagens " +
      //        " WHERE  Postagens.oid_Nota_Fiscal is null " +
      //      " AND    Postagens.NR_Fatura   = '' " +
      //    " AND    Postagens.oid_Pessoa  =  '" + ed.getOID_Pessoa () + "'" +
      //  " AND    Postagens.DT_Postagem = '" + ed.getDT_Postagem () + "'";

      sql = " SELECT * FROM Postagens " +
          " WHERE  Postagens.NR_Lote = '" + nr_lote + "'";

      // System.out.println ("sql" + sql);

      res = executasql.executarConsulta (sql);
      while (res.next ()) {
        this.inicioTransacao ();

        edPostagem = this.carregaED (res);
        edPostagem.setNM_Pessoa_Destinatario ( (res.getString ("NM_Destinatario") + "                                                               ").substring (0 , 40));

        if (edPostagem.getOID_Cidade () > 0) {

          Nota_FiscalED nf = new Nota_FiscalED ();

          // System.out.println (" NF===>>" + edPostagem.getNR_Nota_Fiscal ());

          nf.setOID_Pessoa (edPostagem.getOID_Pessoa ());
          // System.out.println ("Passei EDI 1");
          oid_Pessoa_Destinatario = inclui_Destinatario (edPostagem);
          nf.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
          // System.out.println ("Passei EDI 7");
          nf.setOID_Unidade (ed.getOID_Unidade ());
          nf.setOid_Deposito (ed.getOID_Armazem ());
          nf.setOID_Modal (ed.getOID_Modal ());
          nf.setOID_Produto (ed.getOID_Produto ());
          nf.setDT_Emissao (edPostagem.getDT_Postagem ());
          nf.setNR_Nota_Fiscal (edPostagem.getNR_Nota_Fiscal ());
          nf.setNM_Serie ("");
          // System.out.println ("Passei EDI 8");
          nf.setVL_Nota_Fiscal (0);
          nf.setDM_Situacao ("N");
          nf.setDM_Situacao_Embarque (" ");
          nf.setDM_Transferencia (" ");
          nf.setDM_Exportacao (" ");
          nf.setDM_Situacao_Embarque (" ");
          nf.setDm_Stamp (" ");
          nf.setNR_Pedido (edPostagem.getOID_Postagem ());
          // System.out.println ("Passei EDI 9");
          nf.setNR_Peso (edPostagem.getNR_Peso ());
          nf.setNR_Volumes (1);
          nf.setDT_Entrada (Data.getDataDMY ());
          nf.setHR_Entrada (Data.getHoraHM ());
          nf.setDM_Transferencia ("");
          nf.setDM_Exportacao ("N");
          nf.setDM_Transferencia ("N");
          nf.setDM_Tipo_Conhecimento ("1");
          nf.setTX_Observacao ("");
          nf.setDM_Tipo_Pagamento ("1");

          sql = " SELECT oid_nota_fiscal from notas_fiscais " +
              " WHERE  oid_pessoa     = '" + edPostagem.getOID_Pessoa () + "'" +
              " AND    nr_pedido = '" + edPostagem.getNR_Postagem () + "'";
          // System.out.println ("sql" + sql);

          rs = executasql.executarConsulta (sql);
          if (!rs.next ()) {
            // System.out.println (" vai incluir NF");

            nf = new Nota_FiscalBD (executasql).inclui (nf);

            // System.out.println (" NF OK");
            nf = new Nota_FiscalBD (executasql).getByRecord (nf);
            OID_Nota_Fiscal = nf.getOID_Nota_Fiscal ();
          }

          // System.out.println ("Passei NF=" + OID_Nota_Fiscal);

          if (OID_Nota_Fiscal != null && OID_Nota_Fiscal.length () > 4) {
            sql = "UPDATE POSTAGENS set oid_Nota_Fiscal = '" + OID_Nota_Fiscal + "' WHERE oid_Postagem='" + edPostagem.getOID_Postagem () + "'";

            // System.out.println ("sql" + sql);

            ///-----------------------------------------------

            ConhecimentoED conED = new ConhecimentoED ();

            conED.setOID_Pessoa_Pagador (edPostagem.getOID_Pessoa ());
            conED.setOID_Vendedor (oid_Vendedor);
            conED.setOID_Produto (ed.getOID_Produto ());

            conED.setOID_Unidade (ed.getOID_Unidade ());
            conED.setOID_Empresa (oid_Empresa);
            conED.setOID_Modal (ed.getOID_Modal ());
            conED.setDM_Tipo_Documento (DM_Tipo_Documento);
            conED.setDM_Isento_Seguro (DM_Isencao_Seguro);
            conED.setDM_Tipo_Conhecimento ("1");
            conED.setDM_Tipo_Postagem (res.getString ("DM_Tipo_Servico"));
            conED.setNR_Postagem (res.getLong ("NR_Postagem"));
            conED.setDM_Conhecimento_Varias_Notas_Fiscais ("N");
            conED.setNR_Conhecimento (0);
            conED.setDM_Impresso ("N");
            conED.setDM_Responsavel_Cobranca ("");
            conED.setDM_Tipo_Pagamento ("1");
            conED.setDT_Emissao (Data.getDataDMY ());

            // System.out.println (" inclui ==============");

            conED.setDT_Previsao_Entrega ("");
            conED.setHR_Previsao_Entrega ("");
            conED.setOID_Cidade (oid_Cidade_Origem);
            conED.setOID_Cidade_Destino (edPostagem.getOID_Cidade ());

            // System.out.println ("oid_Cidade_Origem->>" + oid_Cidade_Origem);
            // System.out.println ("oid_Cidade_Destino->>" + edPostagem.getOID_Cidade ());

            conED.setOID_Coleta (0);
            conED.setOID_Veiculo ("");

            conED.setOID_Pessoa (edPostagem.getOID_Pessoa ());
            conED.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
            conED.setOID_Pessoa_Consignatario ("");
            conED.setOID_Pessoa_Redespacho ("");
            conED.setOID_Nota_Fiscal (OID_Nota_Fiscal);
            conED.setTX_Observacao ("Postagem: " + res.getString ("DM_Tipo_Servico") + res.getString ("NR_Postagem"));
            conED.setNM_Natureza (ed.getNM_Natureza () + "*");
            conED.setNM_Especie ("");
            conED.setOID_Tabela_Frete ("");
            conED.setOID_Taxa (1);
            conED.setVL_FRETE_PESO (0.0);
            conED.setVL_FRETE_VALOR (0.0);
            conED.setVL_SEC_CAT (0.0);
            conED.setVL_PEDAGIO (0.0);
            conED.setVL_DESPACHO (0.0);
            conED.setVL_OUTROS1 (0.0);
            conED.setVL_OUTROS2 (0.0);
            conED.setVL_TOTAL_FRETE (0.0);
            conED.setVL_BASE_CALCULO_ICMS (0.0);
            conED.setVL_ICMS (0.0);

            // System.out.println (" inclui 9999 ");

            conED = new ConhecimentoBD (executasql).inclui (conED);

            // System.out.println (" inclui ok");

            //conED = new ConhecimentoBD (executasql).getByRecord (conED);

            // System.out.println (" CALC ok");

            ///---------------------------------------------
            list.add (edPostagem);

            executasql.executarUpdate (sql);
            this.fimTransacao (true);
          }
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMetodo ("gera_Remessa");
      excecoes.setExc (exc);
      OID_Nota_Fiscal = null;
      throw excecoes;
    }

    return list;
  }

  private String inclui_Destinatario (PostagemED ed) throws Excecoes {

    // System.out.println ("inclui_Destinatario");

    String sql = "" , oid_Pessoa = "";
    ResultSet resP = null;
    boolean pessoa_ok = false;
    try {

      sql = " SELECT oid_Pessoa " +
          "  FROM  Pessoas " +
          "  WHERE Pessoas.NM_Razao_Social ='" + ed.getNM_Pessoa_Destinatario ().trim () + "'" +
          "  AND   Pessoas.NM_Endereco = '" + ed.getNM_Endereco ().trim () + "'";
      resP = this.executasql.executarConsulta (sql);
      if (resP.next ()) {
        oid_Pessoa = resP.getString ("oid_Pessoa");
      }
      else {

        PessoaED pessoaED = new PessoaED ();
        pessoaED.setNR_CNPJ_CPF (gera_CNPJ (""));

        // System.out.println ("inclui_Destinatario ok->>" + ed.getNM_Pessoa_Destinatario ());

        pessoaED.setOid_Pessoa (pessoaED.getNR_CNPJ_CPF ());
        pessoaED.setNM_Razao_Social (ed.getNM_Pessoa_Destinatario ().trim ());
        pessoaED.setNM_Endereco (ed.getNM_Endereco ().trim ());
        pessoaED.setNM_Bairro (ed.getNM_Bairro ());
        pessoaED.setOid_Cidade (ed.getOID_Cidade ());

        if (ed.getOID_Cidade () == 0) {
          // System.out.println ("cidade antes->" + ed.getNM_Cidade () + " UF-> " + ed.getCD_Estado ());

          cidadeBean = CidadeBean.getByCidade (ed.getNM_Cidade () , ed.getCD_Estado ());

          // System.out.println ("cidade dpos->" + cidadeBean.getOID ());

          pessoaED.setOid_Cidade (cidadeBean.getOID ());
        }
        pessoaED.setNR_CEP (ed.getCD_Destino ());

        // System.out.println ("9999");

        pessoa_ok = new PessoaBD (this.executasql).inclui (pessoaED);

        // System.out.println ("pessoa ok");

        if (pessoa_ok) {
          oid_Pessoa = pessoaED.getOid_Pessoa ();
        }

        // System.out.println ("pessoa ok->" + oid_Pessoa);

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return oid_Pessoa;
  }

  public PostagemED carregaED (ResultSet res) throws Excecoes {
    PostagemED edVolta = new PostagemED ();
    try {
      String sql = "";
      ResultSet resP = null;

      FormataDataBean DataFormatada = new FormataDataBean ();
      edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
      edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));

      edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));

      edVolta.setNR_Fatura (res.getString ("NR_Fatura"));

      edVolta.setVL_Postagem (res.getDouble ("VL_Postagem"));

      edVolta.setNR_Postagem (res.getLong ("NR_Postagem"));

      edVolta.setOID_Postagem (res.getString ("OID_Postagem"));
      edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));

      edVolta.setCD_Destino (res.getString ("CD_DEstino"));

      edVolta.setNR_Lote (res.getString ("NR_Lote"));

      edVolta.setDT_Postagem (res.getString ("DT_Postagem"));
      DataFormatada.setDT_FormataData (edVolta.getDT_Postagem ());
      edVolta.setDT_Postagem (DataFormatada.getDT_FormataData ());

      edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
      edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
      DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
      edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

      edVolta.setVL_Postagem (res.getDouble ("VL_Postagem"));
      edVolta.setVL_Desconto (res.getDouble ("VL_Desconto"));
      edVolta.setVL_Unitario (res.getDouble ("VL_Unitario"));
      edVolta.setVL_Servico (res.getDouble ("VL_Servico"));

      edVolta.setNM_Pessoa_Remetente (" ");
      edVolta.setNM_Pessoa_Destinatario (" ");
      edVolta.setNM_Endereco (" ");
      edVolta.setNM_Bairro (" ");
      edVolta.setNM_Cidade (" ");
      if (res.getString ("NM_Destinatario") != null && res.getString ("NM_Destinatario").length () > 4) {
        edVolta.setNM_Pessoa_Destinatario ( (res.getString ("NM_Destinatario") + "                                                   ").substring (0 , 40));
      }
      if (res.getString ("NM_Endereco") != null && res.getString ("NM_Endereco").length () > 4) {
        edVolta.setNM_Endereco ( (res.getString ("NM_Endereco") + "                                              ").substring (0 , 40));
      }
      if (res.getString ("NM_Bairro") != null && res.getString ("NM_Bairro").length () > 4) {
        edVolta.setNM_Bairro ( (res.getString ("NM_Bairro") + "                                              ").substring (0 , 40));
      }

      if (res.getString ("NM_Cidade") != null && res.getString ("NM_Cidade").length () > 4) {
        edVolta.setNM_Cidade ("*" + (res.getString ("NM_Cidade") + "                   ").substring (0 , 12) + "/" + res.getString ("CD_Estado"));
      }

      edVolta.setNR_Conhecimento (" ");
      edVolta.setDM_Conhecimento_Impresso("N");
      if (edVolta.getNR_Postagem () > 0) {
        ConhecimentoED edCto = new ConhecimentoED ();
        if (res.getString ("oid_Conhecimento") != null && res.getString ("oid_Conhecimento").length () > 4) {
          edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
          edCto.setOID_Conhecimento (edVolta.getOID_Conhecimento ());
          edCto = new ConhecimentoBD (executasql).getByRecord (edCto);
        }
        else {
          edCto.setNR_Postagem (edVolta.getNR_Postagem ());
          edCto = new ConhecimentoBD (executasql).getByPostagem (edCto);
        }
        if (edCto.getOID_Conhecimento () != null && edCto.getOID_Conhecimento ().length () > 4) {
          //if (edCto.getNR_Conhecimento () > 0 && edCto.getOID_Pessoa().equals(edVolta.getOID_Pessoa())) {

          edVolta.setOID_Conhecimento (edCto.getOID_Conhecimento ());
          edVolta.setNR_Conhecimento (String.valueOf (edCto.getNR_Conhecimento ()).toString ());
          edVolta.setDM_Conhecimento_Impresso(edCto.getDM_Impresso());
          sql = "SELECT NM_Razao_Social FROM Pessoas WHERE oid_Pessoa='" + edCto.getOID_Pessoa () + "'";
          resP = this.executasql.executarConsulta (sql);
          if (resP.next ()) {
            edVolta.setNM_Pessoa_Remetente (resP.getString ("NM_Razao_Social"));
          }

          sql = "SELECT NM_Razao_Social FROM Pessoas WHERE oid_Pessoa='" + edCto.getOID_Pessoa_Destinatario () + "'";
          resP = this.executasql.executarConsulta (sql);
          if (resP.next ()) {
            edVolta.setNM_Pessoa_Destinatario (resP.getString ("NM_Razao_Social"));
          }

          edVolta.setVL_Total_Frete (edCto.getVL_TOTAL_FRETE ());

          sql = " SELECT Cidades.oid_Cidade, Cidades.NM_Cidade, Estados.CD_Estado " +
              "  FROM  Cidades, Regioes_Estados, Estados" +
              "  WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
              "  AND   Regioes_Estados.OID_Estado = Estados.OID_Estado " +
              "  AND   Cidades.oid_Cidade ='" + edCto.getOID_Cidade_Destino () + "'";
          resP = this.executasql.executarConsulta (sql);
          if (resP.next ()) {
            edVolta.setOID_Cidade (resP.getLong ("oid_Cidade"));
            edVolta.setNM_Cidade ( (resP.getString ("NM_Cidade") + "                   ").substring (0 , 12) + "/" + resP.getString ("CD_Estado"));
          }

        }
        else {

          sql = " SELECT Cidades.oid_Cidade, Cidades.NM_Cidade, Estados.CD_Estado " +
              "  FROM  Cidades, Regioes_Estados, Estados" +
              "  WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
              "  AND   Regioes_Estados.OID_Estado = Estados.OID_Estado " +
              "  AND   Cidades.CD_Logradouro ='" + edVolta.getCD_Destino () + "'";
          resP = this.executasql.executarConsulta (sql);
          if (resP.next ()) {
            // System.out.println ("OK");
            edVolta.setOID_Cidade (resP.getLong ("oid_Cidade"));
            edVolta.setNM_Cidade ( (resP.getString ("NM_Cidade") + "                   ").substring (0 , 12) + "/" + resP.getString ("CD_Estado"));
          }
          else {
            // System.out.println ("NAO OK");
            sql = " SELECT Cidades.oid_Cidade, Cidades.NM_Cidade, Estados.CD_Estado " +
                "  FROM  Cidades, Regioes_Estados, Estados" +
                "  WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
                "  AND   Regioes_Estados.OID_Estado = Estados.OID_Estado " +
                "  AND   Cidades.NM_Cidade ='" + res.getString ("NM_Cidade") + "'" +
                "  AND   Estados.CD_Estado ='" + res.getString ("CD_Estado") + "'";
            resP = this.executasql.executarConsulta (sql);
            if (resP.next ()) {
              edVolta.setOID_Cidade (resP.getLong ("oid_Cidade"));
              edVolta.setNM_Cidade ( (resP.getString ("NM_Cidade") + "                   ").substring (0 , 12) + "/" + resP.getString ("CD_Estado"));
            }
          }

          if (res.getString ("oid_Pessoa") != null && res.getString ("oid_Pessoa").length () > 4) {
            sql = "SELECT NM_Razao_Social FROM Pessoas WHERE oid_Pessoa='" + res.getString ("oid_Pessoa") + "'";
            resP = this.executasql.executarConsulta (sql);
            if (resP.next ()) {
              edVolta.setNM_Pessoa_Remetente (resP.getString ("NM_Razao_Social"));
            }
          }

        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(PostagemED ed)");
    }

    return edVolta;
  }

  public String gera_CNPJ (String oid) throws Exception {
    int oid_Unidade = Parametro_FixoED.getInstancia ().getOID_Unidade_Padrao ();

    // System.out.println ("gera_CNPJ ");

    String NR_PROXIMO_CNPJ = null;
    String number = null;
    int oid_Parametro_Filial = 0;
    String sql = "";

    DecimalFormat dec = new DecimalFormat ("00");

    try {
      sql = " SELECT Parametros_Filiais.NR_PROXIMO_CNPJ, Parametros_Filiais.oid_Parametro_Filial " +
          " FROM  Parametros_Filiais " +
          " WHERE oid_unidade = " + oid_Unidade;

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        NR_PROXIMO_CNPJ = res.getString ("NR_PROXIMO_CNPJ");
        oid_Parametro_Filial = res.getInt ("oid_Parametro_Filial");
      }
      double nr_p = new Long (NR_PROXIMO_CNPJ).longValue ();
      if (nr_p == 0 || nr_p < 999999 * 100000) {
        NR_PROXIMO_CNPJ = "999999000000";
      }

      if (NR_PROXIMO_CNPJ != null) {
        long gg = new Long (NR_PROXIMO_CNPJ).longValue () + 1;
        String NR_Proximo_CNPJ = String.valueOf (gg);

        sql = " UPDATE Parametros_Filiais SET  NR_Proximo_CNPJ='" + NR_Proximo_CNPJ + "'" +
            " WHERE OID_Parametro_Filial=" + oid_Parametro_Filial;

        executasql.executarUpdate (sql);

        boolean DM_Achou_Digito = false;
        String cc = "00";

        int cont = 0;
        while (!DM_Achou_Digito && cont < 100) {
          cont++;

          int bb = new Integer (cc).intValue () + 1;
          cc = dec.format (bb);
          number = NR_PROXIMO_CNPJ + cc;
          DM_Achou_Digito = this.CGCValido (number);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return JavaUtil.trunc (number , 14);
  }

  public boolean CGCValido (String number) {

    int soma = 0;

    if (number.length () == 14) {
      for (int i = 0 , j = 5; i < 12; i++) {
        soma += j-- * (number.charAt (i) - '0');
        if (j < 2) {
          j = 9;
        }
      }
      soma = 11 - (soma % 11);
      if (soma > 9) {
        soma = 0;
      }
      if (soma == (number.charAt (12) - '0')) {
        soma = 0;
        for (int i = 0 , j = 6; i < 13; i++) {
          soma += j-- * (number.charAt (i) - '0');
          if (j < 2) {
            j = 9;
          }
        }
        soma = 11 - (soma % 11);
        if (soma > 9) {
          soma = 0;
        }
        if (soma == (number.charAt (13) - '0')) {
          return true;
        }
      }
    }
    return false;
  }

}
