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

public class Fatura_MasterBD
    extends Transacao {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  Parametro_FixoED paramED = new Parametro_FixoED ();

  public Fatura_MasterBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public Fatura_MasterED inclui (Fatura_MasterED ed) throws Excecoes {

    String sql = null;

    // System.out.println (" INCLUI BD 1");

    Fatura_MasterED conED = new Fatura_MasterED ();

    try {

      sql = " SELECT oid_Fatura_Master FROM Faturas_Master WHERE NR_Master='" + ed.getNR_Master ()+ "'";

    // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        //sql =" UPDATE Faturas_Master SET VL_Master="+ed.getVL_Master() +" WHERE NR_Master='" + ed.getNR_Master ()+ "'";
        sql =" DELETE FROM Faturas_Master WHERE oid_Fatura_Master='" + res.getString("oid_Fatura_Master")+ "'";
        executasql.executarUpdate (sql);

      }else {

        // System.out.println (" INCLUI BD 2");

        ed.setOID_Fatura_Master (String.valueOf (System.currentTimeMillis ()).toString ());

        sql = " INSERT into Faturas_Master (" +
            " OID_Fatura_Master, " +
            " OID_Pessoa, " +
            " DM_Cia_Aerea, " +
            " NR_Master, " +
            " NR_Volumes, " +
            " DT_Entrada, " +
            " HR_Entrada, " +
            " NM_Servico, " +
            " NM_Destinatario, " +
            " DM_Tipo_Servico, " +
            " NM_Trecho, " +
            " VL_Seguro, " +
            " VL_Frete, " +
            " VL_Taxas, " +
            " VL_Master, " +
            " DM_Situacao, " +
            " DT_Fatura_Master, " +
            " NR_Fatura, " +
            " NR_Peso, " +
            " NR_Peso_Cubado  " +
            ") values ";

        sql += "('" +
            ed.getOID_Fatura_Master () + "','" +
            ed.getOID_Pessoa () + "','" +
            ed.getDM_Cia_Aerea () + "','" +
            ed.getNR_Master () + "'," +
            ed.getNR_Volumes () + ",'" +
            Data.getDataDMY () + "','" +
            Data.getHoraHM () + "','" +
            ed.getNM_Servico () + "','" +
            ed.getNM_Destinatario () + "','" +
            ed.getDM_Tipo_Servico () + "','" +
            ed.getNM_Trecho () + "'," +
            ed.getVL_Seguro () + "," +
            ed.getVL_Frete () + "," +
            ed.getVL_Taxas () + "," +
            ed.getVL_Master () + ",'" +
            ed.getDM_Situacao () + "','" +
            ed.getDT_Fatura_Master() + "','" +
            ed.getNR_Fatura () + "'," +
            ed.getNR_Peso () + "," +
            ed.getNR_Peso_Cubado () +
            ")";

        // System.out.println (sql);

        int i = executasql.executarUpdate (sql);

    // System.out.println (" INCLUI BD ok");

        conED.setOID_Fatura_Master (ed.getOID_Fatura_Master ());
      }

    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Fatura_MasterED ed)");
    }
    catch (Exception ex) {
      ex.printStackTrace ();
      throw new Excecoes (ex.getMessage () , ex , null , "");
    }
    return conED;
  }



  public void deleta_Fatura (Fatura_MasterED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    try {

      sql = " SELECT  oid_Fatura_Master " +
          " FROM   Faturas_Master  " +
          " WHERE  Faturas_Master.oid_Lote_Fornecedor is null " +
          " AND    Faturas_Master.NR_Fatura='" + ed.getNR_Fatura () + "'";

      // System.out.println ("sql Fatura_Master:" + sql);

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        // System.out.println ("exclui Fatura_Master:" + res.getString ("oid_Fatura_Master"));

        Fatura_MasterED edVolta = new Fatura_MasterED ();
        edVolta.setOID_Fatura_Master (res.getString ("oid_Fatura_Master"));
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

  public void deleta (Fatura_MasterED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "delete from Faturas_Master WHERE oid_Fatura_Master = ";
      sql += "('" + ed.getOID_Fatura_Master () + "')";
      executasql.executarUpdate (sql);
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

  public ArrayList lista (Fatura_MasterED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  * " +
          " FROM   Faturas_Master WHERE 1=1 ";

      if (String.valueOf (ed.getNR_Master ()) != null &&
          !String.valueOf (ed.getNR_Master ()).equals ("0") &&
          !String.valueOf (ed.getNR_Master ()).equals ("") &&
          !String.valueOf (ed.getNR_Master ()).equals ("null")) {
        sql += " and Faturas_Master.NR_Master = '" + ed.getNR_Master ()+ "'";
      }

      if (String.valueOf (ed.getNR_Fatura ()) != null &&
          !String.valueOf (ed.getNR_Fatura ()).equals ("") &&
          !String.valueOf (ed.getNR_Fatura ()).equals ("null")) {
        sql += " and Faturas_Master.NR_Fatura = '" + ed.getNR_Fatura () + "'";
      }

      if (String.valueOf (ed.getDT_Fatura_Master_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Fatura_Master_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Fatura_Master_Inicial ()).equals ("null")) {
        sql += " and Faturas_Master.DT_Fatura_Master >= '" + ed.getDT_Fatura_Master_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Fatura_Master_Final ()) != null &&
          !String.valueOf (ed.getDT_Fatura_Master_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Fatura_Master_Final ()).equals ("null")) {
        sql += " and Faturas_Master.DT_Fatura_Master <= '" + ed.getDT_Fatura_Master_Final () + "'";
      }
      if ("A".equals(ed.getDM_Situacao())){
        sql += " and Faturas_Master.oid_Lote_Fornecedor is not null ";
      }
      if ("S".equals(ed.getDM_Situacao())){
        sql += " and Faturas_Master.oid_Lote_Fornecedor is null ";
      }


      sql += " Order by Faturas_Master.OID_Fatura_Master ";

      // System.out.println ("sql Fatura_Master:" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula

      boolean listar = false;
      while (res.next ()) {
        Fatura_MasterED edVolta = new Fatura_MasterED ();
        // System.out.println ("wh ");

        edVolta = this.carregaED (res);

        // System.out.println ("wh volta");

        listar = true;

        if (listar) {
          // System.out.println ("wh add");
          list.add (edVolta);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Fatura_MasterED ed)");
    }

    return list;
  }


  public Fatura_MasterED getByRecord (Fatura_MasterED ed) throws Excecoes {

    String sql = null;
    Fatura_MasterED edVolta = new Fatura_MasterED ();
    ResultSet resP = null;

    try {

      sql = " SELECT * from Faturas_Master WHERE Faturas_Master.OID_Fatura_Master = '" + ed.getOID_Fatura_Master () + "'";
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

  public void importa_Fatura (String arquivo , String DM_Cia_Aerea , String NR_Fatura) throws Excecoes {

    String NM_Registro = "";
    String Incluir = "S";
    String nm_Servico="";
    String dt_Master = "";
    String nr_Master = "";
    String nr_Peso = "";
    String nm_Destinatario = "";
    String nm_Trecho = "";
    String vl_Frete = "";
    String vl_Seguro = "";
    String vl_Master = "";
    String vl_Taxas = "";


    // System.out.println ("importa_Fatura_Master -->> " + arquivo);

    int linha = 0, pos=0;
    double var = 0;

    try {
      ManipulaArquivo man = new ManipulaArquivo ("");
      LineNumberReader line = man.leLinha (arquivo);

      if (line.ready ()) {

        // System.out.println ("Line Ready");

        while ( (NM_Registro = line.readLine ()) != null) {

          // System.out.println ("Line ->>" + linha + " - " + NM_Registro);

          if (NM_Registro != null && NM_Registro.length () > 10) {

          // System.out.println ("teste 1 ok");

          
            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "; ; ; ; ; ; ; ; ; ; ; ; ; ; ;               ";
            NM_Registro = NM_Registro.toUpperCase ();

            if ("STANDARD".equals(NM_Registro.substring (0 , 8))){
              nm_Servico = NM_Registro.substring (0 , 31);
            }

            // System.out.println ("nm_Servico ->>" + nm_Servico);
          
            dt_Master = NM_Registro.substring (0 , 10);

            // System.out.println ("dt_Master ->>" + dt_Master);
          
            try {
              String xd = Data.getDiaSemana (dt_Master);
            // System.out.println ("xd ->>" + xd);
              Incluir = "S";
            }
            catch (Exception exc) {
              Incluir = "N";
            }
            // System.out.println ("Incluir ->>" + Incluir);

            if (Incluir == "S") {

              // System.out.println ("incluir ...");
          
              ArrayList lista = man.leStringDelimitado (NM_Registro , ";" , 0);

              nr_Master = "";
              nr_Peso = "";
              nm_Destinatario = "";
              nm_Trecho = "";
              vl_Frete="";
              vl_Seguro="";
              vl_Master="";
              vl_Taxas="";
          
              if (lista.size () != 0) {
                nr_Master = (String) lista.get (11).toString ().trim ();


                nm_Trecho = (String) lista.get (25).toString ().trim (); //certa
                if (nm_Trecho.length()<=4){
                  nm_Trecho = (String) lista.get (26).toString ().trim ();
                }
                if (nm_Trecho.length()<=4){
                  nm_Trecho = (String) lista.get (27).toString ().trim ();
                }
                if (nm_Trecho.length()<=4){
                  nm_Trecho = (String) lista.get (28).toString ().trim ();
                }

                // System.out.println ("pos ->>" + pos);

                nr_Peso = (String) lista.get (34).toString ().trim ();
                if (nr_Peso.length()<=0){
                  nr_Peso = (String) lista.get (35).toString ().trim ();
                }
                if (nr_Peso.length()<=0){
                  nr_Peso = (String) lista.get (35).toString ().trim ();
                }
                if (nr_Peso.length()<=0){
                  nr_Peso = (String) lista.get (36).toString ().trim ();
                }
                if (nr_Peso.length()<=0){
                  nr_Peso = (String) lista.get (37).toString ().trim ();
                }
                if (nr_Peso.length()<=0){
                  nr_Peso = (String) lista.get (38).toString ().trim ();
                }
                if (nr_Peso.length()<=0){
                  nr_Peso = (String) lista.get (39).toString ().trim ();
                }
                if (nr_Peso.length()<=0){
                  nr_Peso = (String) lista.get (40).toString ().trim ();
                }

                vl_Frete = (String) lista.get (41).toString ().trim ();
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (42).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (43).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (44).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (45).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (46).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (47).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (48).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (49).toString ().trim ();
                }
                if (vl_Frete.length()<=0){
                  vl_Frete = (String) lista.get (50).toString ().trim ();
                }

                vl_Seguro = (String) lista.get (52).toString ().trim ();
                if (vl_Seguro.length()<=0){
                  vl_Seguro = (String) lista.get (53).toString ().trim ();
                }
                if (vl_Seguro.length()<=0){
                  vl_Seguro = (String) lista.get (54).toString ().trim ();
                }
                if (vl_Seguro.length()<=0){
                  vl_Seguro = (String) lista.get (55).toString ().trim ();
                }
                if (vl_Seguro.length()<=0){
                  vl_Seguro = (String) lista.get (56).toString ().trim ();
                }
                if (vl_Seguro.length()<=0){
                  vl_Seguro = (String) lista.get (57).toString ().trim ();
                }
                if (vl_Seguro.length()<=0){
                  vl_Seguro = (String) lista.get (58).toString ().trim ();
                }
                if (vl_Seguro.length()<=0){
                  vl_Seguro = (String) lista.get (59).toString ().trim ();
                }

                vl_Taxas = (String) lista.get (62).toString ().trim ();
                if (vl_Taxas.length()<=0){
                  vl_Taxas = (String) lista.get (63).toString ().trim ();
                }
                if (vl_Taxas.length()<=0){
                  vl_Taxas = (String) lista.get (64).toString ().trim ();
                }
                if (vl_Taxas.length()<=0){
                  vl_Taxas = (String) lista.get (65).toString ().trim ();
                }
                if (vl_Taxas.length()<=0){
                  vl_Taxas = (String) lista.get (66).toString ().trim ();
                }
                if (vl_Taxas.length()<=0){
                  vl_Taxas = (String) lista.get (67).toString ().trim ();
                }

                vl_Master = (String) lista.get (68).toString ().trim ();
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (69).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (70).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (71).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (72).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (73).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (74).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (75).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (76).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (77).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (78).toString ().trim ();
                }
                if (vl_Master.length()<=0){
                  vl_Master = (String) lista.get (79).toString ().trim ();
                }
                if (vl_Master.length()<=0 || "0.00".equals(vl_Master)){
                  vl_Master = vl_Frete;
                }

                nm_Destinatario = (String) lista.get (80).toString ().trim ();
                if (nm_Destinatario.length () <= 0) {
                  nm_Destinatario = (String) lista.get (81).toString ().trim ();
                }
                if (nm_Destinatario.length () <= 0) {
                  nm_Destinatario = (String) lista.get (82).toString ().trim ();
                }
                if (nm_Destinatario.length () <= 0) {
                  nm_Destinatario = (String) lista.get (83).toString ().trim ();
                }
                if (nm_Destinatario.length () <= 0) {
                  nm_Destinatario = (String) lista.get (84).toString ().trim ();
                }
                if (nm_Destinatario.length () <= 0) {
                  nm_Destinatario = (String) lista.get (85).toString ().trim ();
                }
                if (nm_Destinatario.length () <= 0) {
                  nm_Destinatario = (String) lista.get (86).toString ().trim ();
                }
              }

              // System.out.println ("NR_Master ->>" + nr_Master);
              // System.out.println ("NM_Trecho ->>" + nm_Trecho);
              // System.out.println ("nr_peso ->>" + nr_Peso);
              // System.out.println ("vl_Frete ->>" + vl_Frete);
              // System.out.println ("vl_Seguro ->>" + vl_Seguro);
              // System.out.println ("vl_Taxas ->>" + vl_Taxas);
              // System.out.println ("vl_Master ->>" + vl_Master);
              // System.out.println ("nm_destinatario ->>" + nm_Destinatario);
          
              Fatura_MasterED importaED = new Fatura_MasterED ();
              importaED.setNR_Fatura (NR_Fatura);
              importaED.setDM_Cia_Aerea (DM_Cia_Aerea);
              importaED.setDT_Fatura_Master (dt_Master);
              importaED.setNM_Servico (nm_Servico);
              importaED.setNM_Destinatario (nm_Destinatario);
              importaED.setNR_Master (nr_Master);
              importaED.setNM_Trecho (nm_Trecho);
              importaED.setDT_Entrada (Data.getDataDMY ());
              importaED.setHR_Entrada (Data.getHoraHM ());
              importaED.setDM_Situacao ("P");
              
              nr_Peso=SeparaEndereco.corrigeNumero(nr_Peso);
              vl_Frete=SeparaEndereco.corrigeNumero(vl_Frete);
              vl_Seguro=SeparaEndereco.corrigeNumero(vl_Seguro);
              vl_Taxas=SeparaEndereco.corrigeNumero(vl_Taxas);

              vl_Master=SeparaEndereco.corrigeNumero(vl_Master);

              // System.out.println ("vl_Master DEPOIS->>" + vl_Master);

              importaED.setNR_Peso (SeparaEndereco.confirmaValorDouble (nr_Peso) /100);
              importaED.setNR_Peso_Cubado (SeparaEndereco.confirmaValorDouble (nr_Peso)/100);
              importaED.setVL_Frete(SeparaEndereco.confirmaValorDouble(vl_Frete)/100);
              importaED.setVL_Seguro(SeparaEndereco.confirmaValorDouble(vl_Seguro)/100);
              importaED.setVL_Taxas(SeparaEndereco.confirmaValorDouble(vl_Taxas)/100);
              importaED.setVL_Master(SeparaEndereco.confirmaValorDouble(vl_Master)/100);

              // System.out.println ("vl_Master importaED->>" + importaED.getVL_Master());

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


  public Fatura_MasterED carregaED (ResultSet res) throws Excecoes {
    Fatura_MasterED edVolta = new Fatura_MasterED ();
    try {
      String sql = "";
      ResultSet resP = null;

      FormataDataBean DataFormatada = new FormataDataBean ();
      edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
      edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
      edVolta.setNR_Fatura (res.getString ("NR_Fatura"));
      edVolta.setVL_Master (res.getDouble ("VL_Master"));
      edVolta.setOID_Fatura_Master (res.getString ("OID_Fatura_Master"));
      edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
      edVolta.setNM_Trecho (res.getString ("NM_Trecho"));
      edVolta.setNR_Master (res.getString ("NR_Master"));

      edVolta.setOID_Lote_Fornecedor(res.getLong("oid_Lote_Fornecedor"));
      edVolta.setDT_Fatura_Master (res.getString ("DT_Fatura_Master"));
      DataFormatada.setDT_FormataData (edVolta.getDT_Fatura_Master ());
      edVolta.setDT_Fatura_Master (DataFormatada.getDT_FormataData ());

      edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
      edVolta.setDT_Entrada (res.getString ("DT_Entrada"));
      DataFormatada.setDT_FormataData (edVolta.getDT_Entrada ());
      edVolta.setDT_Entrada (DataFormatada.getDT_FormataData ());

      edVolta.setVL_Master (res.getDouble ("VL_Master"));
      edVolta.setVL_Taxas (res.getDouble ("VL_Taxas"));
      edVolta.setVL_Seguro (res.getDouble ("VL_Seguro"));
      edVolta.setVL_Frete (res.getDouble ("VL_Frete"));

      edVolta.setNM_Destinatario (res.getString("NM_Destinatario"));

      edVolta.setDM_Conhecimento_Impresso("N");

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Fatura_MasterED ed)");
    }

    return edVolta;
  }
}
