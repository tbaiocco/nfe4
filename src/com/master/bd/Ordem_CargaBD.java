package com.master.bd;

import java.sql.*;
import java.util.*;

import com.master.ed.*;
import com.master.rl.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class Ordem_CargaBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria ();
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public Ordem_CargaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ordem_CargaED inclui (Ordem_CargaED ed) throws Excecoes {

//  // System.out.println(" bd 1");

    String sql = null;
    long nr_Ordem_Carga = 0;

    try {

      ed.setOID_Ordem_Carga (String.valueOf (System.currentTimeMillis ()).toString ().substring (8));

      ResultSet rs = executasql.executarConsulta ("select max(nr_Ordem_Carga) as result from Ordens_Cargas");
      while (rs.next ()) {
        nr_Ordem_Carga = rs.getLong ("result") + 1;
      }

      sql = " INSERT INTO Ordens_Cargas (" +
          "OID_Ordem_Carga, " +
          "NR_Ordem_Carga, " +
          "OID_Unidade, " +
          "OID_Pessoa, " +
          "OID_Motorista, " +
          "OID_Veiculo, " +
          "NR_Pedido, " +
          "DT_Ordem_Carga, " +
          "HR_Ordem_Carga, " +
          "TX_Observacao, " +
          "DM_Situacao, " +
          "DM_Tipo, " +
          "oid_Deposito, " +
          "VL_Custo, " +
          "DT_EMISSAO, " +
          "HR_EMISSAO) values ";

      sql += "(" + ed.getOID_Ordem_Carga () +
          "," + nr_Ordem_Carga +
          "," + ed.getOID_Unidade () +
          ",'" + ed.getOID_Pessoa () + "'" +
          ",'" + ed.getOID_Motorista () + "'" +
          ",'" + ed.getOID_Veiculo () + "'" +
          ",'" + ed.getNR_Pedido () + "'" +
          ",'" + ed.getDT_Ordem_Carga () + "'" +
          ",'" + ed.getHR_Ordem_Carga () + "'" +
          ",'" + ed.getTX_Observacao () + "'" +
          ",'1'" +
          ",'" + ed.getDM_Tipo () + "'" +
          "," + ed.getOID_Deposito () +
          "," + ed.getVL_Custo () +
          ",'" + Data.getDataDMY () + "'" +
          ",'" + Data.getHoraHM () + "')";

      // System.out.println ("sql->" + sql);

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Ordem_Carga");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public Ordem_CargaED getByRecord (Ordem_CargaED ed) throws Excecoes {
    Ordem_CargaED edVolta = new Ordem_CargaED ();

    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {
        edVolta = carregaED (res);
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

  private Ordem_CargaED carregaED (ResultSet res) throws Excecoes {

    Ordem_CargaED edVolta = new Ordem_CargaED ();
    // System.out.println ("carregaED 1 ");

    try {
      // System.out.println ("carregaED 2 ");

      edVolta.setOID_Ordem_Carga (res.getString ("OID_Ordem_Carga"));
      edVolta.setNR_Ordem_Carga ("------");

      if (JavaUtil.doValida (res.getString ("NR_Ordem_Carga"))) {
        edVolta.setNR_Ordem_Carga (res.getString ("NR_Ordem_Carga"));
      }
      edVolta.setDT_Ordem_Carga (FormataData.formataDataBT (res.getDate ("DT_Ordem_Carga")));

      if (!"C".equals(res.getString ("DM_Situacao"))){
        edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
        edVolta.setCD_Deposito (res.getString ("CD_Deposito"));
        edVolta.setNM_Deposito (res.getString ("NM_Deposito"));
        edVolta.setOID_Deposito (res.getLong ("OID_Deposito"));
        edVolta.setOID_Compromisso (res.getLong ("OID_Compromisso"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setVL_Custo (res.getDouble ("vl_custo"));
        edVolta.setOID_Motorista (res.getString ("OID_Motorista"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
      
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
      
        edVolta.setDT_Ordem_Carga (FormataData.formataDataBT (res.getString ("DT_Ordem_Carga")));
      
        edVolta.setNM_Razao_Social_Motorista (res.getString ("NM_Razao_Social_Motorista"));
      
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social"));
        edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
        edVolta.setNR_Placa ("");
        if (util.doValida (res.getString ("OID_Veiculo"))) {
          edVolta.setNR_Placa (res.getString ("OID_Veiculo"));
        }
      
        edVolta.setDM_Tipo (res.getString ("DM_Tipo"));
        edVolta.setDM_Nota_Fiscal ("N");
      
        if ("C".equals (res.getString ("DM_Tipo"))) {
          edVolta.setNM_Tipo ("CARGA");
          ResultSet res2 = this.executasql.executarConsulta ("SELECT count (oid_Nota_Fiscal) as result FROM Notas_Fiscais WHERE oid_Ordem_Carga=" + edVolta.getOID_Ordem_Carga ());
          if (res2.next ()) {
            if (res2.getLong ("result") > 0) {
              edVolta.setDM_Nota_Fiscal ("S");
            }
          }
        }
        if ("D".equals (res.getString ("DM_Tipo"))) {
          edVolta.setNM_Tipo ("DESCARGA");
          ResultSet res2 = this.executasql.executarConsulta ("SELECT count (oid_Nota_Fiscal) as result FROM Notas_Fiscais WHERE oid_Ordem_Descarga=" + edVolta.getOID_Ordem_Carga ());
          if (res2.next ()) {
            if (res2.getLong ("result") > 0) {
              edVolta.setDM_Nota_Fiscal ("S");
            }
          }
        }
      }else{
        edVolta.setNM_Tipo ("CANCELADA");
        edVolta.setNM_Deposito (" ");
        edVolta.setNM_Pessoa_Remetente (" ");
        edVolta.setOID_Veiculo (" ");
        
      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "carregaED()");
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "carregaED()");
    }
    return edVolta;
  }

  private String montaSql (Ordem_CargaED ed) throws Excecoes {

    String sql =
        " SELECT Ordens_Cargas.*, " +
        "        Pessoa_Motorista.NM_Razao_Social as NM_Razao_Social_Motorista, " +
        "        Pessoas.NM_Razao_Social, " +
        "        Depositos.NM_Deposito, " +
        "        Depositos.CD_Deposito, " +
        "        Unidades.CD_Unidade " +
        " FROM Ordens_Cargas, Unidades, Pessoas, Depositos, Pessoas Pessoa_Motorista ";
          if (JavaUtil.doValida (ed.getNR_Nota_Fiscal ())) {
            sql +=", Notas_Fiscais ";
          }

      sql+=  " WHERE Ordens_Cargas.OID_Unidade = Unidades.OID_Unidade " +
        " AND Ordens_Cargas.OID_Deposito = Depositos.OID_Deposito " +
        " AND Ordens_Cargas.OID_Pessoa = Pessoas.OID_Pessoa " +
        " AND Ordens_Cargas.OID_Motorista = Pessoa_Motorista.OID_Pessoa ";

        if (JavaUtil.doValida (ed.getNR_Nota_Fiscal ())) {
           if ("C".equals (ed.getDM_Tipo ())) {
             sql += " AND Ordens_Cargas.oid_Ordem_Carga = Notas_Fiscais.oid_Ordem_Carga ";
           }else{
             sql += " AND Ordens_Cargas.oid_Ordem_Carga = Notas_Fiscais.oid_Ordem_Descarga ";
           }
           sql+="  AND Notas_Fiscais.NR_Nota_Fiscal ='" + ed.getNR_Nota_Fiscal () + "'";
        }

    if (JavaUtil.doValida (ed.getOID_Ordem_Carga ())) {
      sql += " and Ordens_Cargas.OID_Ordem_Carga = " + ed.getOID_Ordem_Carga ();
    }
    else {
      if (JavaUtil.doValida (ed.getNR_Ordem_Carga ())) {
        sql += " and Ordens_Cargas.NR_Ordem_Carga = " + ed.getNR_Ordem_Carga ();
      }
      else {
        if (JavaUtil.doValida (ed.getOID_Veiculo ())) {
          sql += " and Ordens_Cargas.oid_Veiculo = '" + ed.getOID_Veiculo () + "'";
        }
        if (JavaUtil.doValida (ed.getNR_Pedido ())) {
          sql += " and Ordens_Cargas.NR_Pedido = '" + ed.getNR_Pedido () + "'";
        }
        if (JavaUtil.doValida (ed.getDM_Tipo ()) && !"T".equals (ed.getDM_Tipo ())) {
          sql += " and Ordens_Cargas.DM_Tipo = '" + ed.getDM_Tipo () + "'";
        }
        if (JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
          sql += " and Ordens_Cargas.DT_Ordem_Carga >= '" + ed.getDT_Emissao_Inicial () + "'";
        }
        if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
          sql += " and Ordens_Cargas.DT_Ordem_Carga <= '" + ed.getDT_Emissao_Final () + "'";
        }
        if (JavaUtil.doValida (ed.getDM_Situacao ()) &&
            !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
          sql += " and Ordens_Cargas.DM_Situacao = '" + ed.getDM_Situacao () + "'";
        }
        if (JavaUtil.doValida (ed.getOID_Pessoa ())) {
          sql += " and Ordens_Cargas.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (JavaUtil.doValida (ed.getOID_Motorista ())) {
          sql += " and Ordens_Cargas.OID_Motorista = '" + ed.getOID_Motorista () + "'";
        }
        sql += " Order BY Ordens_Cargas.NR_Ordem_Carga ";

      }
    }
    // System.out.println (sql);
    return sql;
  }

  public ArrayList Lista_Ordem_Carga (Ordem_CargaED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {
        // System.out.println (res.getString ("oid_Ordem_Carga"));

        list.add (carregaED (res));

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecord()");
    }
    return list;
  }

  public byte[] imprime_Ordem_Carga_Descarga (Ordem_CargaED ed) throws Excecoes {
    byte b[] = null;
    String sql = "";

    sql = " SELECT Ordens_Cargas.*, " +
        "        Notas_Fiscais.*, " +
        "        Pessoas.NM_Razao_Social, " +
        "        Depositos.NM_Deposito, " +
        "        Depositos.CD_Deposito, " +
        "        Unidades.CD_Unidade " +
        " FROM Ordens_Cargas, Unidades, Pessoas, Depositos " +
        " WHERE Ordens_Cargas.OID_Unidade = Unidades.OID_Unidade " +
        " AND Ordens_Cargas.OID_Deposito = Depositos.OID_Deposito " +
        " AND Ordens_Cargas.OID_Pessoa = Pessoas.OID_Pessoa ";
    if ("C".equals (ed.getDM_Tipo ())) {
      sql += " AND Ordens_Cargas.oid_Ordem_Carga = Notas_Fiscais.oid_Ordem_Carga ";
    }
    else {
      sql += " AND Ordens_Cargas.oid_Ordem_Carga = Notas_Fiscais.oid_Ordem_Descarga ";
    }
    sql += " AND Ordens_Cargas.oid_Ordem_Carga = '" + ed.getOID_Ordem_Carga () + "'";

    // System.out.println (" imprime_Ordem_Carga_Descarga ->" + sql);


    try {
      ResultSet res = null;
      res = executasql.executarConsulta (sql.toString ());
      Ordem_CargaRL ordem_cargaRL = new Ordem_CargaRL ();
      b = ordem_cargaRL.imprime_Ordem_Carga_Descarga (res , ed);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m\351todo listar");
      exce.setClasse (getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] gera_Rel_Carga_Descarga (Ordem_CargaED ed) throws Excecoes {
    byte b[] = null;

    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      Ordem_CargaRL ordem_cargaRL = new Ordem_CargaRL ();
      b = ordem_cargaRL.gera_Rel_Carga_Descarga (res , ed);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("gera_Rel_Carga_Descarga");
      exce.setClasse (getClass ().getName ());
      exce.setMetodo ("gera_Rel_Carga_Descarga(Ordem_CargaED ed)");
    }
    return b;
  }

  public void altera (Ordem_CargaED ed) throws Excecoes {

    try {
      String sql = " UPDATE Ordens_Cargas  SET  DT_Stamp = '" + Data.getDataDMY () + "'";

      if (ed.getOID_Deposito () > 0) {
        sql += " ,OID_Deposito = " + ed.getOID_Deposito ();
      }
      if (util.doValida (ed.getNR_Pedido ())) {
        sql += " ,NR_Pedido = '" + ed.getNR_Pedido () + "'";
      }
      if (util.doValida (ed.getDM_Situacao ())) {
        sql += " ,DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }
      if (util.doValida (ed.getTX_Observacao ())) {
        sql += " ,TX_Observacao = '" + ed.getTX_Observacao () + "'";
      }
      if (util.doValida (ed.getDT_Ordem_Carga ())) {
        sql += " ,DT_Ordem_Carga = '" + ed.getDT_Ordem_Carga () + "'";
      }
      if (util.doValida (ed.getHR_Ordem_Carga ())) {
        sql += " ,HR_Ordem_Carga = '" + ed.getHR_Ordem_Carga () + "'";
      }

      if (ed.getVL_Custo () >= 0) {
        sql += " ,VL_Custo = " + ed.getVL_Custo ();
      }

      if (util.doValida (ed.getOID_Veiculo ())) {
        sql += " ,OID_Veiculo = '" + ed.getOID_Veiculo () + "'";
      }
      if (util.doValida (ed.getDM_Tipo ())) {
        sql += " ,DM_Tipo = '" + ed.getDM_Tipo () + "'";
      }
      if (util.doValida (ed.getOID_Motorista ())) {
        sql += " ,oid_Motorista = '" + ed.getOID_Motorista () + "'";
      }

      sql += " WHERE  OID_Ordem_Carga ='" + ed.getOID_Ordem_Carga () + "'";

      // System.out.println (sql);

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("altera_VeiculoxOrdem_Carga()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void cancela (Ordem_CargaED ed) throws Excecoes {

    try {
     String  sql = " UPDATE Ordens_Cargas  SET  TX_Observacao='CANCELADA', DM_Situacao = 'C' " +
                   " WHERE  OID_Ordem_Carga ='" + ed.getOID_Ordem_Carga () + "'";

      // System.out.println (sql);
      int i = executasql.executarUpdate (sql);

          sql = " SELECT Ordens_Cargas.DM_Tipo " +
                " FROM  Ordens_Cargas " +
                " WHERE Ordens_Cargas.oid_Ordem_Carga = '" + ed.getOID_Ordem_Carga () + "'";

          // System.out.println ("cancela-> " + sql);

      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {

          // System.out.println ("cancela 2-> ");

        if ("C".equals (res.getString ("DM_Tipo"))) { //carga
          sql = " UPDATE Notas_Fiscais set OID_Deposito=null, OID_Ordem_Carga=null, DT_Carga_Deposito=null " +
                " WHERE  Notas_Fiscais.oid_Ordem_Carga = '" + ed.getOID_Ordem_Carga () + "'";
          // System.out.println ("CARGA-> " + sql);
          executasql.executarUpdate (sql);
        }
        if ("D".equals (res.getString ("DM_Tipo"))) { //descarga
          sql = " UPDATE Notas_Fiscais set OID_Deposito=null, OID_Ordem_Descarga=null, DT_Descarga_Deposito=null " +
                " WHERE  Notas_Fiscais.OID_Ordem_Descarga = '" + ed.getOID_Ordem_Carga () + "'";
          // System.out.println ("DESCARGA-> " + sql);
          executasql.executarUpdate (sql);
        }
      }        
      
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar");
      excecoes.setMetodo ("cancela()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void vincula_CTRC (Ordem_CargaED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update Ordens_Cargas " +
          "set dm_situacao ='3'" +
          "Where OID_Ordem_Carga ='" + ed.getOID_Ordem_Carga () + "'";
      // System.out.println (sql);

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("vincula_CTRC()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Ordem_CargaED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " DELETE FROM Ordens_Cargas " +
          " WHERE  OID_Ordem_Carga ='" + ed.getOID_Ordem_Carga () + "'";
      // System.out.println (sql);
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("deleta(Ordem_CargaED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void gera_Compromisso (Ordem_CargaED ed) throws Excecoes {

    ResultSet res = null;

    try {

      // System.out.println ("gera comp 1");

      CompromissoED compromissoED = new CompromissoED ();
      CompromissoED compromissoVoltaED = new CompromissoED ();
      CompromissoBD compromissoBD = new CompromissoBD (this.executasql);

      compromissoED.setDt_Entrada (Data.getDataDMY ());
      compromissoED.setDt_Vencimento (Data.getDataDMY ());

      compromissoED.setVl_Compromisso (new Double (ed.getVL_Custo ()));
      compromissoED.setVl_Saldo (new Double (ed.getVL_Custo ()));
      compromissoED.setDt_Emissao (Data.getDataDMY ());
      compromissoED.setNr_Documento (String.valueOf (ed.getNR_Ordem_Carga ()));
      compromissoED.setOid_Unidade (new Long (ed.getOID_Unidade ()));
      // System.out.println ("gera comp 20");

      compromissoED.setOid_Unidade (new Long (ed.getOID_Unidade ()));
      compromissoED.setDM_Tipo_Pagamento ("C");

      compromissoED.setVl_Multa_Apos_Vencimento (new Double (0.0));
      compromissoED.setVl_Juro_Mora_dia (new Double (0.0));
      compromissoED.setVl_Taxa_Banco (new Double (0.0));
      compromissoED.setVl_Desconto_Ate_Vencimento (new Double (0.0));

      // System.out.println ("gera comp 30");

      compromissoED.setOid_Centro_Custo (new Integer (parametro_FixoED.getOID_Centro_Custo_Ordem_Frete ()));

      compromissoED.setOid_Pessoa (ed.getOID_Motorista ());

      compromissoED.setOid_Conta (new Integer (316));

      // System.out.println ("gera comp 40");

      String sql = " SELECT * FROM Contas WHERE OID_Conta=" + compromissoED.getOid_Conta ();

      // System.out.println ("gera comp-> " + sql);

      res = executasql.executarConsulta (sql);
      if (res.next ()) {
        compromissoED.setOid_Tipo_Documento (new Integer (res.getInt ("oid_Tipo_Documento")));
        compromissoED.setOid_Natureza_Operacao (new Integer (res.getInt ("oid_Natureza_Operacao")));
      }

      compromissoVoltaED = compromissoBD.inclui (compromissoED);

      if (compromissoVoltaED.getOid_Compromisso ().longValue () > 0) {
        sql = " UPDATE Ordens_Cargas  SET  oid_Compromisso = '" + compromissoVoltaED.getOid_Compromisso ().longValue () + "'" +
            " WHERE  OID_Ordem_Carga ='" + ed.getOID_Ordem_Carga () + "'";
        // System.out.println ("gera comp-> " + sql);

        executasql.executarUpdate (sql);

      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecord()");
    }
  }

}
