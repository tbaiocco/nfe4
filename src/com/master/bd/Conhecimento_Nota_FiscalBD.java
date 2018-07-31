package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Conhecimento_Nota_FiscalED;
import com.master.ed.Movimento_LogisticoED;
import com.master.rn.Movimento_LogisticoRN;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Conhecimento_Nota_FiscalBD {

  private ExecutaSQL executasql;

  public Conhecimento_Nota_FiscalBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Conhecimento_Nota_FiscalED inclui (Conhecimento_Nota_FiscalED ed) throws
      Excecoes {
    String chave = String.valueOf (System.currentTimeMillis ()); //ed.getOID_Conhecimento () + ed.getOID_Nota_Fiscal ();
    ed.setOID_Conhecimento_Nota_Fiscal (chave);
    String sql = "";

    double nr_peso = 0;
    double nr_peso_cubado = 0;
    double nr_volumes = 0;
    double vl_nota_fiscal = 0;
    double vl_seguro = 0;

    /* Valida se a NF já foi incluída em outro conhecimento do mesmo tipo */
    try {
      if (Parametro_FixoED.getInstancia ().isNF_Multi_Conhecimento ()) {
        sql = " SELECT Conhecimentos_Notas_Fiscais.DM_Tipo_Conhecimento from Conhecimentos_Notas_Fiscais " +
            " WHERE Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = '" +
            ed.getOID_Nota_Fiscal () + "' ";
        ResultSet res = this.executasql.executarConsulta (sql);

        if (res.next ()) {
          if (ed.getDM_Tipo_Conhecimento ().equals (res.getString (
              "DM_Tipo_Conhecimento"))) {
            throw new Mensagens (
                "Nota Fiscal lançada em outro conhecimento do mesmo tipo!");
          }
          else {
            throw new Mensagens (
                "Nota Fiscal lançada em outro conhecimento!");
          }
        }
      }
      else {
        ed.setDM_Tipo_Conhecimento ("");
      }

      /* Insere o registro */
      sql = " insert into Conhecimentos_Notas_Fiscais (OID_Conhecimento_Nota_Fiscal, OID_Conhecimento, OID_Nota_Fiscal, DT_Conhecimento_Nota_Fiscal, HR_Conhecimento_Nota_Fiscal, DM_Tipo_Conhecimento) values ";
      sql += "('" + ed.getOID_Conhecimento_Nota_Fiscal () + "','" +
          ed.getOID_Conhecimento () + "','" + ed.getOID_Nota_Fiscal () + "','" +
          ed.getDT_Conhecimento_Nota_Fiscal () + "','" +
          ed.getHR_Conhecimento_Nota_Fiscal () + "', '" +
          ed.getDM_Tipo_Conhecimento () + "')";
      // // System.out.println (sql);

      int i = executasql.executarUpdate (sql);

      sql = " UPDATE Notas_Fiscais SET DM_Situacao='E',  oid_Conhecimento='" + ed.getOID_Conhecimento () + "'" +
          " WHERE oid_Nota_Fiscal='" + ed.getOID_Nota_Fiscal () + "'";
      i = executasql.executarUpdate (sql);

      //Atualização dos dados de Peso, Cubagem, Vl da Nota e Volumes no CTRC.
      //Localiza os valores das Notas que ja estao inclusas no CTRC.

      String DM_Tipo_Conhecimento="";
      sql = " SELECT Conhecimentos.DM_Tipo_Conhecimento, Notas_Fiscais.nr_peso, Notas_Fiscais.nr_volumes, Notas_Fiscais.nr_cubagem, " +
      		" Notas_Fiscais.vl_nota_fiscal, Notas_Fiscais.vl_seguro " +
          " FROM  Notas_Fiscais, Conhecimentos_Notas_Fiscais, Conhecimentos " +
          " WHERE Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      ResultSet res = executasql.executarConsulta (sql);
      while (res.next ()) {
        nr_peso += res.getDouble ("nr_peso");
        nr_peso_cubado += res.getDouble ("nr_cubagem");
        nr_volumes += res.getDouble ("nr_volumes");
        vl_nota_fiscal += res.getDouble ("vl_nota_fiscal");
        vl_seguro += res.getDouble ("vl_seguro");
        DM_Tipo_Conhecimento=res.getString ("DM_Tipo_Conhecimento");

      }

      sql = " UPDATE Conhecimentos SET nr_peso = " + nr_peso;

      if (!"C".equals(DM_Tipo_Conhecimento)){
        sql +=", nr_peso_cubado = " + nr_peso_cubado;
      }

      if (vl_seguro > 0){
          sql+=  ", nr_volumes = " + nr_volumes +
          ", vl_nota_fiscal = " + vl_nota_fiscal +
          ", vl_nota_fiscal_seguro = " + vl_seguro +
          " WHERE oid_Conhecimento='" + ed.getOID_Conhecimento () + "'";
      }else{
          sql+=  ", nr_volumes = " + nr_volumes +
          ", vl_nota_fiscal = " + vl_nota_fiscal +
          " WHERE oid_Conhecimento='" + ed.getOID_Conhecimento () + "'";
      }
      // // System.out.println (sql);
      i = executasql.executarUpdate (sql);

      if ("1901920CN1".equals (ed.getOID_Conhecimento ())) {
        this.baca_inclui (0 , 0);
      }

//    Transit-Time
      // comentado RONALDO
      //   new Movimento_LogisticoBD(this.executasql).gera_Movimento(new Movimento_LogisticoED(ed.getOID_Nota_Fiscal()));

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Conhecimento_Nota_FiscalED ed)");
    }
    /* Retorna o ed com os dados do registro recém incluído */
    Conhecimento_Nota_FiscalED conhecimento_Nota_FiscalED = new Conhecimento_Nota_FiscalED ();
    conhecimento_Nota_FiscalED.setOID_Conhecimento_Nota_Fiscal (ed.getOID_Conhecimento_Nota_Fiscal ());
    conhecimento_Nota_FiscalED.setOID_Conhecimento (ed.getOID_Conhecimento ());
    conhecimento_Nota_FiscalED.setOID_Unidade (ed.getOID_Unidade ());

    // // System.out.println ("->>" + ed.getOID_Nota_Fiscal ());

    conhecimento_Nota_FiscalED.setOID_Nota_Fiscal (ed.getOID_Nota_Fiscal ());
    return conhecimento_Nota_FiscalED;
  }

  public void altera (Conhecimento_Nota_FiscalED ed) throws Excecoes {

  }

  public void deleta (Conhecimento_Nota_FiscalED ed) throws Excecoes {

    String sql = null;

    double nr_peso = 0;
    double nr_peso_cubado = 0;
    double nr_volumes = 0;
    double vl_nota_fiscal = 0;

    try {

      sql = " SELECT Conhecimentos_Notas_Fiscais.oid_conhecimento, Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal, Notas_Fiscais.nr_peso, Notas_Fiscais.nr_volumes, " +
          " Notas_Fiscais.nr_cubagem, Notas_Fiscais.vl_nota_fiscal " +
          " from Notas_Fiscais, Conhecimentos_Notas_Fiscais " +
          " WHERE Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
          " AND Conhecimentos_Notas_Fiscais.oid_Conhecimento_Nota_Fiscal = '" + ed.getOID_Conhecimento_Nota_Fiscal () + "'";
      ResultSet res = executasql.executarConsulta (sql);
      while (res.next ()) {
        ed.setOID_Conhecimento (res.getString ("oid_conhecimento"));
        ed.setOID_Nota_Fiscal (res.getString ("oid_Nota_Fiscal"));
        nr_peso = res.getDouble ("nr_peso");
        nr_peso_cubado = res.getDouble ("nr_cubagem");
        nr_volumes = res.getDouble ("nr_volumes");
        vl_nota_fiscal = res.getDouble ("vl_nota_fiscal");
      }

      sql = "delete from Conhecimentos_Notas_Fiscais WHERE oid_Conhecimento_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Conhecimento_Nota_Fiscal () + "')";

      int i = executasql.executarUpdate (sql);

      sql = " UPDATE Conhecimentos SET nr_peso = nr_peso-" + nr_peso +
          ", nr_peso_cubado = nr_peso_cubado-" + nr_peso_cubado +
          ", nr_volumes = nr_volumes-" + nr_volumes +
          ", vl_nota_fiscal = vl_nota_fiscal-" + vl_nota_fiscal +
          " WHERE oid_Conhecimento='" + ed.getOID_Conhecimento () + "'";
      // // System.out.println (sql);
      i = executasql.executarUpdate (sql);

      sql = " UPDATE Notas_Fiscais SET DM_Situacao='A',  oid_Conhecimento=null " +
          " WHERE oid_Nota_Fiscal='" + ed.getOID_Nota_Fiscal () + "'";
      i = executasql.executarUpdate (sql);

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

  public ArrayList lista (Conhecimento_Nota_FiscalED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT Conhecimentos.DT_Entrega, Conhecimentos.HR_Entrega, Conhecimentos.NM_Pessoa_Entrega, Conhecimentos_Notas_Fiscais.OID_Conhecimento_Nota_Fiscal, Conhecimentos_Notas_Fiscais.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos_Notas_Fiscais.OID_Conhecimento, Conhecimentos_Notas_Fiscais.DT_Conhecimento_Nota_Fiscal, Conhecimentos_Notas_Fiscais.HR_Conhecimento_Nota_Fiscal, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Nota_Fiscal, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Volumes, Notas_Fiscais.NR_Peso, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.OID_Coleta, Notas_Fiscais.NR_Lote, Notas_Fiscais.VL_Tabela, Notas_Fiscais.VL_Total_Frete, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Conhecimentos_Notas_Fiscais, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Conhecimentos ";
      sql += " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";

      boolean DM_Tem_Filtro = false;
      if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("") &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("null")) {
        sql += " and Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = '" +
            ed.getOID_Nota_Fiscal () + "'";
            DM_Tem_Filtro = true;
      }

      if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("") &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("null")) {
        sql += " and Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" +
            ed.getOID_Conhecimento () + "'";
            DM_Tem_Filtro = true;
      }

      if (!DM_Tem_Filtro){
    	  SQLException e = new SQLException();
    	  throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Conhecimento_Nota_FiscalED ed)");
      }
      sql += " Order by Notas_Fiscais.NR_Nota_Fiscal ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Conhecimento_Nota_FiscalED edVolta = new Conhecimento_Nota_FiscalED ();
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
        edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
        edVolta.setDT_Entrega ("Pendente");
        edVolta.setHR_Entrega ("--:--");
        edVolta.setNM_Pessoa_Entrega ("-");
        edVolta.setNM_Pessoa_Entrega ("-");
        if (res.getString ("DT_Entrega") != null && res.getString ("DT_Entrega").length () > 4) {
          edVolta.setDT_Entrega (res.getString ("DT_Entrega"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Entrega ());
          edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
          edVolta.setHR_Entrega (res.getString ("HR_Entrega"));
          edVolta.setNM_Pessoa_Entrega (res.getString ("NM_Pessoa_Entrega"));
        }

        edVolta.setOID_Conhecimento_Nota_Fiscal (res.getString (
            "OID_Conhecimento_Nota_Fiscal"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setDT_Conhecimento_Nota_Fiscal (res.getString (
            "DT_Conhecimento_Nota_Fiscal"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Conhecimento_Nota_Fiscal ());
        edVolta.setDT_Conhecimento_Nota_Fiscal (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Conhecimento_Nota_Fiscal (res.getString (
            "HR_Conhecimento_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setNM_Pessoa_Remetente (res.getString (
            "NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString (
            "NM_Razao_Social_Destinatario"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (Valor.truncLong (res.getDouble ("NR_Volumes")));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

        String OID_Coleta = res.getString ("oid_Coleta");
        if (String.valueOf (OID_Coleta) != null &&
            !String.valueOf (OID_Coleta).equals ("") &&
            !String.valueOf (OID_Coleta).equals ("0") &&
            !String.valueOf (OID_Coleta).equals (null)
            && !String.valueOf (OID_Coleta).equals ("null")) {
          edVolta.setOID_Coleta (res.getLong ("oid_Coleta"));
        }
        edVolta.setNR_Lote (res.getString ("NR_Lote"));
        ed.setVL_Tabela (res.getDouble ("VL_Tabela"));

        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Conhecimento_Nota_FiscalED ed)");
    }
    return list;
  }

  public Conhecimento_Nota_FiscalED getByRecord (Conhecimento_Nota_FiscalED ed) throws Excecoes {

    String sql = null;

    Conhecimento_Nota_FiscalED edVolta = new Conhecimento_Nota_FiscalED ();

    try {

      sql = " SELECT Notas_Fiscais.*, " +
          " Conhecimentos_Notas_Fiscais.DT_Conhecimento_Nota_Fiscal, " +
          " Conhecimentos_Notas_Fiscais.HR_Conhecimento_Nota_Fiscal, " +
          " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
          " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.DM_Impresso, " +
          " Conhecimentos.OID_Conhecimento, " +
          " Conhecimentos.OID_Unidade, " +
          " Conhecimentos_Notas_Fiscais.OID_Conhecimento_Nota_Fiscal, " +
          " Conhecimentos.DT_Emissao as DT_Emissao_Conhecimento " +
          " FROM  Conhecimentos_Notas_Fiscais, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Conhecimentos " +
          " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
          " AND   Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
          " AND Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
          " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";

      if (String.valueOf (ed.getOID_Conhecimento_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Conhecimento_Nota_Fiscal ()).equals ("")) {
        sql += " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento_Nota_Fiscal = '" + ed.getOID_Conhecimento_Nota_Fiscal () + "'";
      }

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
        edVolta.setDM_Transferencia (res.getString ("DM_Transferencia"));
        edVolta.setDM_Exportacao (res.getString ("DM_Exportacao"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));

        edVolta.setDT_Conhecimento_Nota_Fiscal (res.getString ("DT_Conhecimento_Nota_Fiscal"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Conhecimento_Nota_Fiscal ());
        edVolta.setDT_Conhecimento_Nota_Fiscal (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Conhecimento_Nota_Fiscal (res.getString ("HR_Conhecimento_Nota_Fiscal"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Consignatario (res.getString ("OID_Pessoa_Consignatario"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setNR_Cubagem (res.getDouble ("NR_Cubagem"));
        edVolta.setNR_Cubagem_Total (res.getDouble ("NR_Cubagem_Total"));

        edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
        edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
        edVolta.setOID_Conhecimento_Nota_Fiscal (res.getString ("OID_Conhecimento_Nota_Fiscal"));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Peso_Cubado (res.getDouble ("NR_Peso_Cubado"));
        edVolta.setNR_Itens (res.getDouble ("NR_Itens"));
        edVolta.setNR_Volumes (res.getLong ("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

        edVolta.setDT_Emissao_Conhecimento (res.getString ("DT_Emissao_Conhecimento"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao_Conhecimento ());
        edVolta.setDT_Emissao_Conhecimento (DataFormatada.getDT_FormataData ());

        edVolta.setNM_Serie (res.getString ("NM_Serie"));
        edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
        edVolta.setNR_Codigo_Cliente (res.getString ("NR_Codigo_Cliente"));
        edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
        edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Entrada (res.getString ("HR_Entrada"));

        edVolta.setOID_Natureza_Operacao (res.getLong ("OID_NATUREZA_OPERACAO"));
        edVolta.setOID_Produto (res.getLong ("OID_Produto"));
        edVolta.setOid_Deposito (res.getLong ("OID_Deposito"));

        edVolta.setNR_Lote (res.getString ("NR_Lote"));
        edVolta.setVL_Tabela (res.getDouble ("VL_Tabela"));
        edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
        edVolta.setNM_Especie (res.getString ("NM_Especie"));

        sql = " select * from Naturezas_Operacoes where";
        sql += " OID_NATUREZA_OPERACAO = " + edVolta.getOID_Natureza_Operacao ();

        ResultSet resTP = null;
        resTP = this.executasql.executarConsulta (sql);

        while (resTP.next ()) {
          edVolta.setNM_Natureza_Operacao (resTP.getString ("NM_Natureza_Operacao"));
          edVolta.setCD_Natureza_Operacao (resTP.getString ("CD_Natureza_Operacao"));
        }

      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord (Conhecimento_Nota_FiscalED ed)");
    }

    return edVolta;
  }

  public int qtde_Notas_Fiscais_Conhecimento (String oid_Conhecimento) throws Excecoes {

	    String sql = null;
	    int qt_NF=5;
	    int qt_Digitada=0;
	    int qt_Cliente=0;
	    try {

	      sql = " SELECT COUNT (oid_Conhecimento) as qt_Digitada  " +
	          " FROM  Conhecimentos_Notas_Fiscais " +
	          " WHERE Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + oid_Conhecimento + "'";

	      ResultSet res = this.executasql.executarConsulta (sql);
	      if (res.next ()) {
	    	  qt_Digitada=res.getInt("qt_Digitada");
	    	  sql =" SELECT Clientes.NR_QT_Notas_Fiscais " +
	    	  	   " FROM  Conhecimentos, Clientes " +
	    	  	   " WHERE Conhecimentos.oid_Pessoa_Pagador = Clientes.oid_CLiente " +
	 	           " AND   Conhecimentos.OID_Conhecimento = '" + oid_Conhecimento + "'";

	    	  	res = this.executasql.executarConsulta (sql);
		        if (res.next ()) {
		        	qt_Cliente=res.getInt("NR_QT_Notas_Fiscais");
		        }
		        qt_NF=qt_Cliente-qt_Digitada;
	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "qtde_Notas_Fiscais_Conhecimento");
	    }

	    return qt_NF;
	  }


  private void baca_inclui (long NR_Cto , long NR_NF) throws Excecoes {

    baca_inclui_cto_nf2 (55089 , 22153);
    baca_inclui_cto_nf2 (55130 , 22127);
    baca_inclui_cto_nf2 (55130 , 22135);
    baca_inclui_cto_nf2 (55399 , 22479);

  }

  private void baca_inclui_cto_nf2 (long NR_Cto , long NR_NF) throws Excecoes {

    String sql = null;

    // // System.out.println (" CTO : " + NR_Cto + " NR_NF : " + NR_NF);

    try {
      ResultSet res = null;
      ResultSet res2 = null;
      ResultSet res3 = null;
      sql = "Select oid_Conhecimento, oid_Pessoa, dt_emissao FROM Conhecimentos WHERE  NR_Conhecimento = " + NR_Cto;
      // // System.out.println (sql);

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        sql = "Select oid_Nota_Fiscal FROM Notas_Fiscais WHERE  NR_Nota_Fiscal = " + NR_NF + " AND oid_Pessoa = '" + res.getString ("oid_Pessoa") + "'";
        // // System.out.println (sql);

        res2 = this.executasql.executarConsulta (sql);

        if (res2.next ()) {
          sql = " SELECT oid_Conhecimento FROM Conhecimentos_Notas_Fiscais WHERE oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "' and oid_Nota_Fiscal ='" + res2.getString ("OID_Nota_Fiscal") + "'";
          // // System.out.println (sql);

          res3 = this.executasql.executarConsulta (sql);
          int x = 0;
          if (res3.next ()) {
            x++;
          }
          if (x == 0) {
            sql = " insert into Conhecimentos_Notas_Fiscais (OID_Conhecimento_Nota_Fiscal, OID_Conhecimento, OID_Nota_Fiscal, DT_Conhecimento_Nota_Fiscal, HR_Conhecimento_Nota_Fiscal, DM_Tipo_Conhecimento) values ";
            sql += "('" + (res.getString ("OID_Conhecimento") + System.currentTimeMillis ()) + "','" + res.getString ("oid_Conhecimento") + "','" + res2.getString ("OID_Nota_Fiscal") + "','" +
                res.getString ("DT_Emissao") + "','" + "10:01" + "', '" + "H" + "')";
            // // System.out.println (sql);
            int i = executasql.executarUpdate (sql);
          }

        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "altera(Conhecimento_Nota_FiscalED ed)");
    }
  }

  private void baca_inclui_cto_nf (Conhecimento_Nota_FiscalED ed) throws Excecoes {

    String sql = null;

    try {
      ResultSet res = null;
      ResultSet res2 = null;
      sql = " SELECT * FROM Notas_Fiscais ORDER BY DT_Emissao";

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        double vl_nota_fiscal = res.getDouble ("vl_nota_fiscal");
        sql = "Select oid_Conhecimento FROM Conhecimentos WHERE  DM_ENVIO_EDI='N' AND oid_pessoa = '" + res.getString ("oid_Pessoa") + "'" +
            " AND oid_pessoa_destinatario = '" + res.getString ("oid_pessoa_destinatario") + "'" +
            " AND vl_nota_fiscal=" + vl_nota_fiscal;
        // // System.out.println (sql);

        res2 = this.executasql.executarConsulta (sql);
        if (res2.next ()) {
          sql = " insert into Conhecimentos_Notas_Fiscais (OID_Conhecimento_Nota_Fiscal, OID_Conhecimento, OID_Nota_Fiscal, DT_Conhecimento_Nota_Fiscal, HR_Conhecimento_Nota_Fiscal, DM_Tipo_Conhecimento) values ";
          sql += "('" + (res2.getString ("OID_Conhecimento") + System.currentTimeMillis ()) + "','" + res2.getString ("oid_Conhecimento") + "','" + res.getString ("OID_Nota_Fiscal") + "','" +
              res.getString ("DT_Emissao") + "','" + "10:01" + "', '" + "Y" + "')";
          // // System.out.println (sql);

          int i = executasql.executarUpdate (sql);

          sql = " UPDATE Notas_Fiscais SET DM_FINALIZADO='S' WHERE oid_Nota_Fiscal='" + res.getString ("OID_Nota_Fiscal") + "'";
          executasql.executarUpdate (sql);
          // // System.out.println (sql);

          sql = " UPDATE Conhecimentos SET DM_ENVIO_EDI='S' WHERE oid_Conhecimento='" +
              res2.getString ("oid_Conhecimento") + "'";
          executasql.executarUpdate (sql);
          // // System.out.println (sql);

        }

      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "altera(Conhecimento_Nota_FiscalED ed)");
    }
  }

  private void baca_inclui_cto_nfNEWWW (Conhecimento_Nota_FiscalED ed) throws Excecoes {

    String sql = null;

    try {
      ResultSet res = null;
      ResultSet res2 = null;
      ResultSet res3 = null;
      String NR_Pedido2 = "000";

      int x = 90001;

      sql = " SELECT * FROM Notas_Fiscais WHERE DM_FINALIZADO='N' and NR_Pedido2='' ORDER BY dt_entrada, oid_pessoa ";

      res = this.executasql.executarConsulta (sql);
      String oid_p = "" , dt_ent = "" , oid_pessoa = "" , dt_entrada = "";
      while (res.next ()) {
        if (!res.getString ("oid_pessoa").equals (oid_p) || !res.getString ("dt_entrada").equals (dt_ent)) {
          x++;
        }
        oid_p = res.getString ("oid_pessoa");
        dt_ent = res.getString ("dt_entrada");

        String xx = String.valueOf (x);
        sql = "update Notas_Fiscais set NR_Pedido2='" + xx + "' WHERE oid_nota_Fiscal ='" + res.getString ("oid_nota_fiscal") + "'";
        // // System.out.println (sql);
        executasql.executarUpdate (sql);

      }

      double vl_nota_fiscal = 0;
      sql = " SELECT * FROM Notas_Fiscais ORDER BY NR_Pedido2";

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        if (res.getString ("NR_Pedido2") != null && res.getString ("NR_Pedido2").length () >= 5) {
          if ("000".equals (NR_Pedido2)) {
            NR_Pedido2 = res.getString ("NR_Pedido2");
          }
          if (NR_Pedido2.equals (res.getString ("NR_Pedido2"))) {
            vl_nota_fiscal += res.getDouble ("vl_nota_fiscal");
            oid_pessoa = res.getString ("oid_pessoa");
            dt_entrada = res.getString ("dt_entrada");

            // // System.out.println ("nr PEDIDO ->> " + NR_Pedido2 + "vl_nota_fiscal ->> " + vl_nota_fiscal);

          }
          else if (vl_nota_fiscal > 0) {
            sql =
                "Select oid_Conhecimento FROM Conhecimentos WHERE dm_envio_edi='N' AND oid_pessoa = '" + oid_pessoa + "' AND dt_emissao = '" + dt_entrada + "'" +
                " AND vl_nota_fiscal=" + vl_nota_fiscal;
            // // System.out.println (sql);

            res2 = this.executasql.executarConsulta (sql);
            int k = 0;
            while (res2.next () && k == 0) {
              k++;
              sql = " SELECT * FROM Notas_Fiscais WHERE NR_Pedido2 = '" + NR_Pedido2 + "'";
              res3 = this.executasql.executarConsulta (sql);
              while (res3.next ()) {

                sql = " insert into Conhecimentos_Notas_Fiscais (OID_Conhecimento_Nota_Fiscal, OID_Conhecimento, OID_Nota_Fiscal, DT_Conhecimento_Nota_Fiscal, HR_Conhecimento_Nota_Fiscal, DM_Tipo_Conhecimento) values ";
                sql += "('" +
                    (res2.getString ("OID_Conhecimento") +
                     System.currentTimeMillis ()) +
                    "','" + res2.getString ("oid_Conhecimento") + "','" +
                    res3.getString ("OID_Nota_Fiscal") + "','" +
                    res3.getString ("DT_Emissao") + "','" + "10:01" + "', '" +
                    "U" +
                    "')";
                // // System.out.println (sql);

                int i = executasql.executarUpdate (sql);

                sql = " UPDATE Notas_Fiscais SET DM_FINALIZADO='S' WHERE oid_Nota_Fiscal='" +
                    res3.getString ("OID_Nota_Fiscal") + "'";
                executasql.executarUpdate (sql);
                // // System.out.println (sql);

                sql = " UPDATE Conhecimentos SET DM_ENVIO_EDI='S' WHERE oid_Conhecimento='" +
                    res2.getString ("oid_Conhecimento") + "'";
                executasql.executarUpdate (sql);
                // // System.out.println (sql);

              }

              vl_nota_fiscal = 0;

            }
            NR_Pedido2 = res.getString ("NR_Pedido2");
            vl_nota_fiscal = res.getDouble ("vl_nota_fiscal");

          }

        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "altera(Conhecimento_Nota_FiscalED ed)");
    }
  }

}
