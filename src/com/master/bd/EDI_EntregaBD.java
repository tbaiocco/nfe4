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
import com.master.rn.Ocorrencia_ConhecimentoRN;

public class EDI_EntregaBD
    extends Transacao {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  Parametro_FixoED paramED = new Parametro_FixoED ();
  CidadeBean cidadeBean = new CidadeBean ();
  String nr_lote = "";

  public EDI_EntregaBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public EDI_EntregaED inclui (EDI_EntregaED ed) throws Excecoes {

    String sql = null;

    // System.out.println (" INCLUI BD ========1");

    EDI_EntregaED conED = new EDI_EntregaED ();

    try {

      sql = " SELECT oid_EDI_Entrega " +
          " FROM EDI_Entregas " +
          " WHERE NR_Nota_Fiscal=" + ed.getNR_Nota_Fiscal () +
          " AND   OID_Pessoa='" + ed.getOID_Pessoa () + "'" +
          " AND   NR_Conhecimento='" + ed.getNR_Conhecimento () + "'" +
          " AND   nr_lote=" + ed.getNR_Lote ();

      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      if (!res.next ()) {

        ed.setOID_EDI_Entrega (String.valueOf (System.currentTimeMillis ()).toString ());

        // System.out.println ("ATES");

        sql = " INSERT into EDI_Entregas (" +
            " OID_EDI_Entrega, " +
            " OID_Pessoa_Entregadora, " +
            " OID_Pessoa, " +
            " OID_Conhecimento, " +
            " NR_Conhecimento, " +
            " NR_Lote, " +
            " DT_Entrega, " +
            " HR_Entrega, " +
            " TX_Observacao, " +
            " CD_Tipo_Ocorrencia, " +
            " DM_Tipo_Servico, " +
            " NM_Pessoa_Entrega, " +
            " DM_Situacao, " +
            " DT_Entrada, " +
            " VL_Entrega,  " +
            " NR_Nota_Fiscal,  " +
            " oid_Padrao_EDI  " +
            ") values ";

        sql += "('" +
            ed.getOID_EDI_Entrega () + "','" +
            ed.getOID_Pessoa_Entregadora () + "','" +
            ed.getOID_Pessoa () + "','" +
            ed.getOID_Conhecimento () + "','" +
            ed.getNR_Conhecimento () + "','" +
            ed.getNR_Lote () + "','" +
            ed.getDT_Entrega () + "','" +
            ed.getHR_Entrega () + "','" +
            ed.getTX_Observacao ().trim () + "','" +
            ed.getCD_Tipo_Ocorrencia () + "','" +
            ed.getDM_Tipo_Servico () + "','" +
            ed.getNM_Pessoa_Entrega () + "','" +
            ed.getDM_Situacao () + "','" +
            Data.getDataDMY () + "'," +
            ed.getVL_Entrega () + "," +
            ed.getNR_Nota_Fiscal () + "," +
            ed.getOID_Padrao_EDI () +
            ")";

        // System.out.println (sql);

        int i = executasql.executarUpdate (sql);

        conED.setOID_EDI_Entrega (ed.getOID_EDI_Entrega ());
      }

    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(EDI_EntregaED ed)");
    }
    catch (Exception ex) {
      ex.printStackTrace ();
      throw new Excecoes (ex.getMessage () , ex , null , "");
    }
    return conED;
  }

  public void deleta (EDI_EntregaED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    try {

      sql = " SELECT  EDI_Entregas.oid_Conhecimento, EDI_Entregas.oid_EDI_Entrega, EDI_Entregas.oid_Ocorrencia_Conhecimento " +
            " FROM    EDI_Entregas  " +
            " WHERE   EDI_Entregas.NR_Lote = '" + ed.getNR_Lote () + "'";

      // System.out.println ("sql EDI_Entrega:" + sql);

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        if (res.getLong("oid_Ocorrencia_Conhecimento")>0){

          sql = " UPDATE Conhecimentos SET DT_Entrega=null, HR_Entrega='', NM_Pessoa_Entrega='' " +
                " WHERE  Conhecimentos.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
                " AND    Conhecimentos.oid_Ocorrencia_Conhecimento ='" + res.getString ("oid_Ocorrencia_Conhecimento") + "'";
                // System.out.println ("sql EDI_Entrega:" + sql);
                executasql.executarUpdate (sql);

          sql = " DELETE from Ocorrencias_Conhecimentos " +
                " WHERE oid_Ocorrencia_Conhecimento ='" + res.getString ("oid_Ocorrencia_Conhecimento") + "'";
                // System.out.println ("sql EDI_Entrega:" + sql);
                executasql.executarUpdate (sql);

        }

        sql = " DELETE from EDI_Entregas " +
              " WHERE   EDI_Entregas.oid_EDI_Entrega = '" + res.getString ("oid_EDI_Entrega") + "'";
                // System.out.println ("sql EDI_Entrega:" + sql);
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


  public ArrayList lista (EDI_EntregaED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  * " +
          " FROM   EDI_Entregas WHERE 1=1 ";

      if (String.valueOf (ed.getOID_Pessoa_Entregadora ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("null")) {
        sql += " and EDI_Entregas.OID_Pessoa_Entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'";
      }

      if (String.valueOf (ed.getNR_Lote ()) != null &&
          !String.valueOf (ed.getNR_Lote ()).equals ("") &&
          !String.valueOf (ed.getNR_Lote ()).equals ("null")) {
        sql += " and EDI_Entregas.NR_Lote = '" + ed.getNR_Lote () + "'";
      }

      // System.out.println ("sql EDI_Entrega:" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula

      boolean listar = false;
      while (res.next ()) {
        EDI_EntregaED edVolta = new EDI_EntregaED ();
        // System.out.println ("wh ");

        edVolta = this.carregaED (res);

        // System.out.println ("wh volta");

        listar = true;
        if ("C".equals (ed.getDM_Situacao ()) && " ".equals (edVolta.getNR_Conhecimento ())) {
          listar = false;
        }
        if ("P".equals (ed.getDM_Situacao ()) && !" ".equals (edVolta.getNR_Conhecimento ())) {
          listar = false;
        }
        if (listar) {
          // System.out.println ("wh add");
          list.add (edVolta);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(EDI_EntregaED ed)");
    }

    return list;
  }

  public ArrayList listaEDI_EntregaConhecimento (EDI_EntregaED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  EDI_Entregas.*,  " +
          "         Conhecimentos.NR_Conhecimento  " +
          " FROM   EDI_Entregas, Conhecimentos " +
          " WHERE  EDI_Entregas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
          " AND    EDI_Entregas.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'" +
          " ORDER  by EDI_Entregas.NR_Entrega ";

      // System.out.println ("sql EDI_Entrega:" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula

      boolean listar = false;
      while (res.next ()) {
        EDI_EntregaED edVolta = new EDI_EntregaED ();
        // System.out.println ("wh ");

        edVolta.setNR_Entrega (res.getString ("NR_Entrega"));
        edVolta.setOID_EDI_Entrega (res.getString ("OID_EDI_Entrega"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        // System.out.println ("wh volta");
        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "listaEDI_EntregaConhecimento(EDI_EntregaED ed)");
    }

    return list;
  }

  public EDI_EntregaED getByRecord (EDI_EntregaED ed) throws Excecoes {

    String sql = null;
    EDI_EntregaED edVolta = new EDI_EntregaED ();
    ResultSet resP = null;

    try {

      sql = " SELECT * from EDI_Entregas WHERE EDI_Entregas.OID_EDI_Entrega = '" + ed.getOID_EDI_Entrega () + "'";
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

  private void importa_Entrega (String arquivo , String OID_Pessoa_Entregadora) throws Excecoes {

    String NM_Registro = "";
    String Incluir = "S";
    // System.out.println ("importa_EDI_Entrega -->> " + arquivo);
    // System.out.println ("OID_Pessoa_Entregadora -->> " + OID_Pessoa_Entregadora);

    int linha = 0;
    double var = 0;

    String NR_Entrega = "";
    String cd_Tipo_Ocorrencia = "";
    String TX_Observacao = "";
    String NM_Pessoa_Entrega = "";
    String NR_Nota_Fiscal = "";
    String NR_Conhecimento="";
    String DT_Entrega = "";
    String HR_Entrega = "";
    String dm_tipo_registro = "";
    String oid_Pessoa_Arquivo = "";
    String oid_Pessoa = "";

    //trazer da tela
    String DM_Tipo_Padrao = "Proceda 3.0a";
    long oid_Padrao_EDI=15;
    if ("99999900571906".equals(OID_Pessoa_Entregadora)) {
        DM_Tipo_Padrao = "TexLog";
        oid_Padrao_EDI=7;
    }
    
    //
    // System.out.println ("DM_Tipo_Padrao -->> " + DM_Tipo_Padrao);
    
    String sql="";

    try {

      ManipulaArquivo man = new ManipulaArquivo ("");
      LineNumberReader line = man.leLinha (arquivo);

      if (line.ready ()) {

        // System.out.println ("Line Ready");

        while ( (NM_Registro = line.readLine ()) != null) { 

          // System.out.println ("Line ->>" + linha + " - " + NM_Registro);

          if (NM_Registro != null && NM_Registro.length () > 10) {

            Incluir = "N";

            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "                                                                                                                                   ";
            NM_Registro = NM_Registro.toUpperCase ();
            dm_tipo_registro = NM_Registro.substring (0 , 3);

            // System.out.println ("dm_tipo_registro ->>" + dm_tipo_registro);

            //PROCEDA 3.0a
            if ("Proceda 3.0a".equals (DM_Tipo_Padrao)) {
              if ("000".equals (dm_tipo_registro)) {
                nr_lote = NM_Registro.substring (86 , 95);
              }

              if ("341".equals (dm_tipo_registro)) {
                oid_Pessoa_Arquivo = NM_Registro.substring (3 , 17);
              }

              if ("342".equals (dm_tipo_registro)) {
                oid_Pessoa = NM_Registro.substring (3 , 17);
                NR_Nota_Fiscal = NM_Registro.substring (21 , 28);
                cd_Tipo_Ocorrencia = NM_Registro.substring (28 , 30);
                String dt_ent = NM_Registro.substring (30 , 32);
                String mes_ent = NM_Registro.substring (32 , 34);
                String ano_ent = NM_Registro.substring (34 , 38);
                HR_Entrega = NM_Registro.substring (38 , 42);

                if (HR_Entrega != null && HR_Entrega.length () == 4) {
                  HR_Entrega = NM_Registro.substring (38 , 40)+":"+NM_Registro.substring (40 , 42);
                }

                TX_Observacao = NM_Registro.substring (44 , 100);
                if (TX_Observacao ==null) TX_Observacao=" ";

                if (NM_Pessoa_Entrega ==null || NM_Pessoa_Entrega.length()<=4) NM_Pessoa_Entrega="Assinatura.";
                
                // System.out.println ("nr_lote ->>" + nr_lote);
                // System.out.println ("oid_Pessoa_Remetente ->>" + oid_Pessoa);
                // System.out.println ("NR_Nota_Fiscal ->>" + NR_Nota_Fiscal);
                // System.out.println ("cd_Tipo_Ocorrencia ->>" + cd_Tipo_Ocorrencia);

                // System.out.println ("dt_ent ->>" + dt_ent);
                // System.out.println ("mes_ent ->>" + mes_ent);
                // System.out.println ("ano_ent ->>" + ano_ent);
                // System.out.println ("hora_ent ->>" + HR_Entrega);
                DT_Entrega = dt_ent + "/" + mes_ent + "/" + ano_ent;

                Incluir = "S";

              }
            }

            //TEXLOGOLLD
            if ("TexLog--".equals (DM_Tipo_Padrao)) {
              if ("117".equals (dm_tipo_registro)) {
                nr_lote = NM_Registro.substring (22 ,28);
              }
              // System.out.println ("nr_lote ->>" + nr_lote);

              oid_Pessoa_Arquivo = OID_Pessoa_Entregadora;

              if ("203".equals (dm_tipo_registro)) {
            	NR_Conhecimento = NM_Registro.substring (3 ,10);
                oid_Pessoa = "";
                NR_Nota_Fiscal = "000000";


                String dt_ent = NM_Registro.substring (35 , 37);
                String mes_ent = NM_Registro.substring (34 , 36);
                String ano_ent = NM_Registro.substring (29 , 33);

                String entrega_retornou= NM_Registro.substring (59 , 60);
                // System.out.println ("cd_Tipo_Ocorrencia ->>" + cd_Tipo_Ocorrencia);

                cd_Tipo_Ocorrencia="001";
                if (!"N".equals(entrega_retornou)){
                	cd_Tipo_Ocorrencia="027";
                }
                
                HR_Entrega = "10:00";
                TX_Observacao = "";
                NM_Pessoa_Entrega ="";
                
                // System.out.println ("oid_Pessoa_Remetente ->>" + oid_Pessoa);
                // System.out.println ("NR_Nota_Fiscal ->>" + NR_Nota_Fiscal);
                // System.out.println ("NR_Conhecimento ->>" + NR_Conhecimento);

                // System.out.println ("dt_ent ->>" + dt_ent);
                // System.out.println ("mes_ent ->>" + mes_ent);
                // System.out.println ("ano_ent ->>" + ano_ent);
                // System.out.println ("hora_ent ->>" + HR_Entrega);
                DT_Entrega = dt_ent + "/" + mes_ent + "/" + ano_ent;

                // System.out.println ("DT_Entrega ->>" + DT_Entrega);

                Incluir = "S";

              }
            }

            
            //TEXLOG
            if ("TexLog".equals (DM_Tipo_Padrao)) {
              if ("117".equals (dm_tipo_registro)) {
                nr_lote = NM_Registro.substring (22 ,28);
              }
              // System.out.println ("nr_lote ->>" + nr_lote);

              oid_Pessoa_Arquivo = OID_Pessoa_Entregadora;

              if ("203".equals (dm_tipo_registro) || "204".equals (dm_tipo_registro)) {
            	NR_Conhecimento = NM_Registro.substring (3 ,10);
                oid_Pessoa = "";
                NR_Nota_Fiscal = "000000";


                String dt_ent = NM_Registro.substring (35 , 37);
                String mes_ent = NM_Registro.substring (33 , 35);
                String ano_ent = NM_Registro.substring (29 , 33);

                HR_Entrega = NM_Registro.substring (37 , 41);
                
                String entrega_retornou= NM_Registro.substring (151 , 152);
                // System.out.println ("cd_Tipo_Ocorrencia ->>" + cd_Tipo_Ocorrencia);
                // System.out.println ("entrega_retornou3 ->>" + entrega_retornou);

                cd_Tipo_Ocorrencia="001";
                if (!"N".equals(entrega_retornou)){
                	cd_Tipo_Ocorrencia="027";
                }
                
                TX_Observacao = "";
                NM_Pessoa_Entrega ="";
                NM_Pessoa_Entrega = NM_Registro.substring (71 , 98);
                TX_Observacao = NM_Registro.substring (158 , 200);
                
                // System.out.println ("oid_Pessoa_Remetente ->>" + oid_Pessoa);
                // System.out.println ("NR_Nota_Fiscal ->>" + NR_Nota_Fiscal);
                // System.out.println ("NR_Conhecimento ->>" + NR_Conhecimento);

                // System.out.println ("dt_ent ->>" + dt_ent);
                // System.out.println ("mes_ent ->>" + mes_ent);
                // System.out.println ("ano_ent ->>" + ano_ent);
                // System.out.println ("hora_ent ->>" + HR_Entrega);
                DT_Entrega = dt_ent + "/" + mes_ent + "/" + ano_ent;

                // System.out.println ("DT_Entrega ->>" + DT_Entrega);
                // System.out.println ("HR_Entrega ->>" + HR_Entrega);
                // System.out.println ("NM_Pessoa_Entrega ->>" + NM_Pessoa_Entrega);
                // System.out.println ("TX_Observacao ->>" + TX_Observacao);

                Incluir = "S";

              }
            }
            
            
            if ("S".equals (Incluir)) {

                // System.out.println ("incluiu importaED 1");

            	EDI_EntregaED importaED = new EDI_EntregaED ();
              importaED.setOID_Pessoa_Entregadora (OID_Pessoa_Entregadora);
              importaED.setOID_Pessoa (oid_Pessoa);
              importaED.setDT_Entrega (DT_Entrega);
              importaED.setNR_Entrega (NR_Entrega);
              // System.out.println ("incluiu importaED 2");

              importaED.setTX_Observacao (TX_Observacao);
              importaED.setNR_Lote (nr_lote);
              importaED.setNM_Pessoa_Entrega (NM_Pessoa_Entrega);
              importaED.setDT_Entrada (DT_Entrega);
              importaED.setHR_Entrega (HR_Entrega);
              // System.out.println ("incluiu importaED 3");

              importaED.setVL_Entrega (0);
              importaED.setDM_Situacao ("P");
              // System.out.println ("incluiu importaED 4");
              importaED.setNR_Conhecimento(NR_Conhecimento);
              importaED.setNR_Nota_Fiscal (SeparaEndereco.confirmaValorLong (NR_Nota_Fiscal));
              // System.out.println ("incluiu importaED 5");
              importaED.setCD_Tipo_Ocorrencia (cd_Tipo_Ocorrencia);
              importaED.setOID_Padrao_EDI(oid_Padrao_EDI);
              this.inclui (importaED);

              // System.out.println ("incluiu importaED ->>");
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
      excecoes.setMetodo ("importa_Entrega");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList gera_Entrega (EDI_EntregaED ed) throws Excecoes {
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    ResultSet res = null;
    String sql = "", nm_Agente_Parceiro="" ;
    try {

      sql = " SELECT NM_Fantasia FROM Pessoas " +
          " WHERE  Pessoas.oid_Pessoa = '" + ed.getOID_Pessoa_Entregadora () + "'";
      res = executasql.executarConsulta (sql);
      while (res.next ()) {
        nm_Agente_Parceiro=res.getString("NM_Fantasia");
      }


      importa_Entrega (ed.getNM_Arquivo () , ed.getOID_Pessoa_Entregadora ());

      EDI_EntregaED edEDI_Entrega = new EDI_EntregaED ();
      // System.out.println ("Passei ");

      sql = " SELECT * FROM EDI_Entregas " +
          " WHERE  EDI_Entregas.NR_Lote = '" + nr_lote + "'";

      // System.out.println ("sql" + sql);

      res = executasql.executarConsulta (sql);
      while (res.next ()) {

        // System.out.println (" leu antes  carrega");

        edEDI_Entrega = this.carregaED (res);

        // System.out.println (" carrega ok");

        if ("P".equals (edEDI_Entrega.getDM_Situacao ()) && "S".equals(edEDI_Entrega.getDM_Tem_Conhecimento())) {
            sql = " SELECT  * FROM Tipos_Ocorrencias, Ocorrencias_Edi  "  +
                  " WHERE Tipos_Ocorrencias.oid_Tipo_Ocorrencia = Ocorrencias_Edi.oid_Tipo_Ocorrencia " +
                  " AND   Ocorrencias_Edi.oid_padrao = " + edEDI_Entrega.getOID_Padrao_EDI() + 
                  " AND   Ocorrencias_Edi.cd_ocorrencia = '" + edEDI_Entrega.getCD_Tipo_Ocorrencia() + "'";
              // System.out.println (sql);

            ResultSet res2= this.executasql.executarConsulta (sql);
            while (res2.next ()) {
            	
              // System.out.println ("Montando Ocorrencia->"+res.getString("NM_Pessoa_Entrega"));

              Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
              ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (edEDI_Entrega.getDT_Entrega_EDI());
              ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (edEDI_Entrega.getHR_Entrega_EDI());
              ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (res2.getLong ("oid_Tipo_Ocorrencia"));
              ocorrencia_ConhecimentoED.setOID_Conhecimento (edEDI_Entrega.getOID_Conhecimento());

              String tx_observacao="EdI :" + nm_Agente_Parceiro +" - " +edEDI_Entrega.getNR_Lote ();
              if (edEDI_Entrega.getTX_Observacao ()!=null && !edEDI_Entrega.getTX_Observacao ().equals("null")){
                tx_observacao+=" ->>" +edEDI_Entrega.getTX_Observacao();
              }
              
              ocorrencia_ConhecimentoED.setTX_Observacao (tx_observacao);
              ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega ("->"+res.getString("NM_Pessoa_Entrega"));
              ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Tipo"));
              ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Acesso"));
              ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Avaria"));
              ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Status"));
              // System.out.println ("Vai incluir Ocorrencia 222");

              ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoBD (executasql).inclui (ocorrencia_ConhecimentoED);

              sql = " UPDATE EDI_Entregas set DM_Situacao='C', oid_Tipo_Ocorrencia=" + res2.getLong ("oid_Tipo_Ocorrencia") + ", oid_Ocorrencia_Conhecimento=" + ocorrencia_ConhecimentoED.getOID_Ocorrencia_Conhecimento() + ",  oid_Conhecimento='" + edEDI_Entrega.getOID_Conhecimento() + "' WHERE oid_EDI_Entrega='" + edEDI_Entrega.getOID_EDI_Entrega () + "'";
              // System.out.println ("sql" + sql);
              executasql.executarUpdate (sql);

          }
        }

        ///---------------------------------------------
        list.add (edEDI_Entrega);

      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMetodo ("gera_Entrega");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public EDI_EntregaED carregaED (ResultSet res) throws Excecoes {
    EDI_EntregaED edVolta = new EDI_EntregaED ();
    try {
      String sql = "";

      // System.out.println ("carregaED 1");
      ResultSet resP = null;

      FormataDataBean DataFormatada = new FormataDataBean ();

      edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));

      edVolta.setOID_EDI_Entrega (res.getString ("OID_EDI_Entrega"));
      edVolta.setOID_Pessoa_Entregadora (res.getString ("OID_Pessoa_Entregadora"));
      edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));

      edVolta.setNM_Pessoa_Entrega (res.getString ("NM_Pessoa_Entrega"));

      // System.out.println ("carregaED 2");

      edVolta.setNR_Lote (res.getString ("NR_Lote"));

      edVolta.setDM_Tem_Conhecimento("N");  

      edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
      
      DataFormatada.setDT_FormataData (res.getString ("DT_Entrega"));
      edVolta.setDT_Entrega_EDI (DataFormatada.getDT_FormataData ());

      // System.out.println ("carregaED 3");

      edVolta.setHR_Entrega_EDI (res.getString ("HR_Entrega"));

      edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
      edVolta.setNM_Situacao ("Pendente");
      if ("C".equals(res.getString ("DM_Situacao"))){
        edVolta.setNM_Situacao ("Confirmado");
      }

      // System.out.println ("carregaED 4");

      if (res.getString ("TX_Observacao") != null && res.getString ("TX_Observacao").length () > 4) {
        edVolta.setTX_Observacao ( (res.getString ("TX_Observacao") + "                                                   ").substring (0 , 40));
      }

      edVolta.setNR_Conhecimento (" ");

      // System.out.println ("carregaED 10");
      edVolta.setOID_Padrao_EDI(res.getLong("oid_Padrao_EDI"));

      sql="";
      if (res.getString ("NR_Conhecimento") != null && !res.getString ("NR_Conhecimento").equals("") && !res.getString ("NR_Conhecimento").equals("null")) {
          sql = "SELECT oid_Conhecimento, NR_Conhecimento, VL_Total_Frete, DM_Impresso, Conhecimentos.DT_Entrega, Conhecimentos.HR_Entrega FROM Conhecimentos WHERE oid_Unidade=5 and NR_Conhecimento='" + res.getString ("NR_Conhecimento") + "'";
      }
      if (res.getString ("oid_Conhecimento") != null && res.getString ("oid_Conhecimento").length () > 4) {
          sql = "SELECT oid_Conhecimento, NR_Conhecimento, VL_Total_Frete, DM_Impresso, Conhecimentos.DT_Entrega, Conhecimentos.HR_Entrega FROM Conhecimentos WHERE oid_Conhecimento='" + res.getString ("oid_Conhecimento") + "'";
      }

      if ("".equals(sql) && res.getLong ("NR_Nota_Fiscal")>0) {
          sql = " SELECT Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete, DM_Impresso, Conhecimentos.DT_Entrega, Conhecimentos.HR_Entrega " +
          " FROM   Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais " +
          " WHERE  Conhecimentos_Notas_Fiscais.oid_Conhecimento=Conhecimentos.oid_Conhecimento " +
          " AND    Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal=Notas_Fiscais.oid_Nota_Fiscal " +
          " AND    Notas_Fiscais.oid_Pessoa = '" + res.getString ("OID_Pessoa") + "'" +
          " AND    Notas_Fiscais.NR_Nota_Fiscal = " + res.getLong ("NR_Nota_Fiscal") +
          " LIMIT 1 ";
      }
      // System.out.println (sql);
      
      if (!"".equals(sql)) {
          resP = this.executasql.executarConsulta (sql);
          if (resP.next ()) {
              // System.out.println ("TEM RESTP 1");
        	edVolta.setDM_Tem_Conhecimento("S");  
            edVolta.setOID_Conhecimento (resP.getString ("oid_Conhecimento"));
            edVolta.setNR_Conhecimento (resP.getString ("NR_Conhecimento"));
            edVolta.setVL_Total_Frete (resP.getDouble ("VL_Total_Frete"));
            // System.out.println ("TEM RESTP 2");

            DataFormatada.setDT_FormataData (resP.getString("DT_Entrega"));
            edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
            edVolta.setHR_Entrega(resP.getString("HR_Entrega"));
          }
      }

      edVolta.setCD_Tipo_Ocorrencia (res.getString ("CD_Tipo_Ocorrencia"));

      edVolta.setNM_Tipo_Ocorrencia (" ");
      if (res.getLong("oid_Tipo_Ocorrencia")>0){
        edVolta.setOID_Tipo_Ocorrencia (res.getLong ("oid_Tipo_Ocorrencia"));
        sql = "SELECT NM_Tipo_Ocorrencia FROM Tipos_Ocorrencias WHERE oid_Tipo_Ocorrencia='" + res.getString ("oid_Tipo_Ocorrencia") + "'";
        resP = this.executasql.executarConsulta (sql);
        if (resP.next ()) {
          edVolta.setNM_Tipo_Ocorrencia (resP.getString("NM_Tipo_Ocorrencia"));
        }
      }      
      
      // System.out.println ("volta");
      
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(EDI_EntregaED ed)");
    }

    return edVolta;
  }

}
