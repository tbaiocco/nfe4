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


public class EDI_RemessaBD
    extends Transacao {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  Parametro_FixoED paramED = new Parametro_FixoED ();
  CidadeBean cidadeBean = new CidadeBean ();
  ManipulaString manipulaString = new ManipulaString ();
  String nr_lote="";

  public EDI_RemessaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public EDI_RemessaED inclui (EDI_RemessaED ed) throws Excecoes {

    String sql = null;

    // System.out.println (" INCLUI BD 1");

    EDI_RemessaED conED = new EDI_RemessaED ();

    try {

      sql = " SELECT oid_EDI_Remessa FROM EDI_Remessas WHERE NR_Remessa='" + ed.getNR_Remessa ()+"'";
      ResultSet res = this.executasql.executarConsulta (sql);
      if (!res.next ()) {

        ed.setOID_EDI_Remessa (String.valueOf (System.currentTimeMillis ()).toString ());

        sql = " INSERT into EDI_Remessas ( OID_EDI_Remessa,  DM_Origem_Remessa,  OID_Pessoa,  OID_Pessoa_Destinatario,  OID_Pessoa_Entregadora,  OID_Pessoa_Pagador,  OID_Conhecimento,  NR_Lote,  NR_Remessa,  NR_Volumes,  DT_Entrada,  HR_Entrada,  NR_Pedido,  NR_CNPJ_CPF,  NR_CNPJ_CPF_Destinatario,  NM_Remetente,  NM_Endereco,  NM_Bairro,  NM_Cidade,  CD_Estado,  NR_Cep,  NM_Destinatario,  NM_Endereco_Dest,  NM_Bairro_Dest,  NM_Cidade_Dest,  CD_Estado_Dest,  NR_Cep_Dest,  CD_Produto,  DM_Tipo_Servico,  DM_Situacao,  DT_Remessa,  NR_Peso,  NR_Peso_Cubado,   VL_Nota_Fiscal,   NR_Nota_Fiscal,   oid_Modal  ) values ";
        sql = sql + "('" + ed.getOID_EDI_Remessa() + "','" + ed.getDM_Origem_Remessa() + "','" + ed.getOID_Pessoa() + "','" + ed.getOID_Pessoa_Destinatario() + "','" + ed.getOID_Pessoa_Entregadora() + "','" + ed.getOID_Pessoa_Pagador() + "','" + ed.getOID_Conhecimento() + "','" + ed.getNR_Lote() + "','" + ed.getNR_Remessa() + "'," + ed.getNR_Volumes() + ",'" + Data.getDataDMY() + "','" + Data.getHoraHM() + "','" + ed.getNR_Pedido() + "','" + ed.getNR_CNPJ_CPF() + "','" + ed.getNR_CNPJ_CPF_Destinatario().trim() + "','" + ed.getNM_Remetente().trim() + "','" + ed.getNM_Endereco().trim() + "','" + ed.getNM_Bairro() + "','" + ed.getNM_Cidade().trim() + "','" + ed.getCD_Estado().trim() + "','" + ed.getNR_Cep().trim() + "','" + ed.getNM_Destinatario().trim() + "','" + ed.getNM_Endereco_Dest().trim() + "','" + ed.getNM_Bairro_Dest() + "','" + ed.getNM_Cidade_Dest().trim() + "','" + ed.getCD_Estado_Dest().trim() + "','" + ed.getNR_Cep_Dest().trim() + "','" + ed.getCD_Produto() + "','" + ed.getDM_Tipo_Servico() + "','" + ed.getDM_Situacao() + "','" + Data.getDataDMY() + "'," + ed.getNR_Peso() + "," + ed.getNR_Peso_Cubado() + "," + ed.getVL_Nota_Fiscal() + "," + ed.getNR_Nota_Fiscal() + "," + ed.getOID_Modal() + ")";


        // System.out.println (sql);

        int i = executasql.executarUpdate (sql);

        conED.setOID_EDI_Remessa (ed.getOID_EDI_Remessa ());
      }

    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(EDI_RemessaED ed)");
    }
    catch (Exception ex) {
      ex.printStackTrace ();
      throw new Excecoes (ex.getMessage () , ex , null , "");
    }
    return conED;
  }


  public void deleta (EDI_RemessaED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet resCto = null;

    try {

      sql = " SELECT  oid_Conhecimento " +
          " FROM   EDI_Remessas  " +
          " WHERE  EDI_Remessas.oid_EDI_Remessa='" + ed.getOID_EDI_Remessa () + "'";
      // System.out.println ("sql EDI_Remessa:" + sql);

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        sql = " SELECT  oid_Conhecimento " +
            " FROM   Conhecimentos  " +
            " WHERE  Conhecimentos.DM_Situacao <>'C' " +
            " AND    Conhecimentos.DM_Impresso ='N' " +
            " AND    Conhecimentos.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'";
        // System.out.println ("sql EDI_Remessa:" + sql);

        resCto = this.executasql.executarConsulta (sql);
        while (resCto.next ()) {
          ConhecimentoED conED = new ConhecimentoED ();
          conED.setOID_Conhecimento (resCto.getString ("oid_Conhecimento"));
          new ConhecimentoBD (executasql).deleta (conED);
        }
      }

      sql = "delete from EDI_Remessas WHERE oid_EDI_Remessa = ";
      sql += "('" + ed.getOID_EDI_Remessa () + "')";

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

  public void deleta_Lista (ArrayList lista) throws Excecoes {
    try {
      for (int i = 0; i < lista.size (); i++) {
        // System.out.println (" deleta_Lista " + i);
        EDI_RemessaED EDI_RemessaED = (EDI_RemessaED) lista.get (i);

        // System.out.println (" deleta Comp= " + EDI_RemessaED.getNR_Remessa ());

        this.deleta (EDI_RemessaED);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public void calcula_Lista (ArrayList lista) throws Excecoes {
    try {
      ResultSet resP=null;
      String sql="";

      
      for (int i = 0; i < lista.size (); i++) {
        // System.out.println (" calcula_Lista " + i);
        EDI_RemessaED ed = (EDI_RemessaED) lista.get (i);

        // System.out.println ("  calcula_Lista= " + ed.getNR_Remessa ());
        // System.out.println ("  calcula_Lista= " + ed.getOID_Conhecimento());
        this.inicioTransacao();
        this.executasql = this.sql;
        
        if (ed.getOID_Conhecimento()!=null && !ed.getOID_Conhecimento().equals("null")){
          sql = "SELECT oid_Conhecimento, NR_Conhecimento, VL_Total_Frete, DM_Impresso FROM Conhecimentos WHERE oid_Conhecimento='" + ed.getOID_Conhecimento()+ "'";
          resP = this.executasql.executarConsulta (sql);
          if (resP.next ()) {
            // System.out.println ("Remessa c/cto=> " + i + "->> "+ resP.getString ("NR_Conhecimento"));
            if ("N".equals (resP.getString ("DM_Impresso"))) {
            	ConhecimentoED conED = new ConhecimentoED ();
            	conED.setOID_Conhecimento (resP.getString ("oid_Conhecimento"));
            	conED = new ConhecimentoBD (executasql).getByRecord (conED);
            	// System.out.println ("Remessa FRETE cto=>" + conED.getVL_TOTAL_FRETE());
            }
          }
        }
        this.fimTransacao(true);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public ArrayList lista (EDI_RemessaED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  * " +
          " FROM   EDI_Remessas WHERE 1=1 ";

      if (String.valueOf (ed.getNR_Remessa ()) != null &&
          !String.valueOf (ed.getNR_Remessa ()).equals ("0") &&
          !String.valueOf (ed.getNR_Remessa ()).equals ("null")) {
        sql += " and EDI_Remessas.NR_Remessa = " + ed.getNR_Remessa ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and EDI_Remessas.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getNR_Lote ()) != null &&
          !String.valueOf (ed.getNR_Lote ()).equals ("") &&
          !String.valueOf (ed.getNR_Lote ()).equals ("null")) {
        sql += " and EDI_Remessas.NR_Lote = '" + ed.getNR_Lote () + "'";
      }

      if (String.valueOf (ed.getDT_Remessa_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Remessa_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Remessa_Inicial ()).equals ("null")) {
        sql += " and EDI_Remessas.DT_Remessa >= '" + ed.getDT_Remessa_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Remessa_Final ()) != null &&
          !String.valueOf (ed.getDT_Remessa_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Remessa_Final ()).equals ("null")) {
        sql += " and EDI_Remessas.DT_Remessa <= '" + ed.getDT_Remessa_Final () + "'";
      }
      sql += " Order by  EDI_Remessas.DT_Remessa , EDI_Remessas.NR_Remessa ";

      // System.out.println ("sql EDI_Remessa:" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula

      boolean listar = false;
      while (res.next ()) {
        EDI_RemessaED edVolta = new EDI_RemessaED ();
        // System.out.println ("wh ");

        edVolta = this.carregaED (res);

        // System.out.println ("wh volta");

        listar = true;
        if ("C".equals (ed.getDM_Situacao ()) && " ".equals (edVolta.getNR_Conhecimento ())) {
          listar = false;
        }
        if ("S".equals (ed.getDM_Situacao ()) && !" ".equals (edVolta.getNR_Conhecimento ())) {
          listar = false;
        }
        if (listar) {
          // System.out.println ("wh add");
          list.add (edVolta);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(EDI_RemessaED ed)");
    }

    return list;
  }

  public ArrayList listaEDI_RemessaConhecimento (EDI_RemessaED ed) throws Excecoes {
    ArrayList list = new ArrayList ();

    try {
      String sql =
          " SELECT  EDI_Remessas.*,  " +
          "         Conhecimentos.NR_Conhecimento  " +
          " FROM   EDI_Remessas, Conhecimentos " +
          " WHERE  EDI_Remessas.oid_Conhecimento = Conhecimentos.oid_Conhecimento "+
          " AND    EDI_Remessas.oid_Conhecimento = '"+ ed.getOID_Conhecimento() +"'" +
          " ORDER  by EDI_Remessas.NR_Remessa ";

      // System.out.println ("sql EDI_Remessa:" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula

      boolean listar = false;
      while (res.next ()) {
        EDI_RemessaED edVolta = new EDI_RemessaED ();
        // System.out.println ("wh ");

        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
        edVolta.setNR_Remessa (res.getString ("NR_Remessa"));
        edVolta.setOID_EDI_Remessa (res.getString ("OID_EDI_Remessa"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        // System.out.println ("wh volta");
        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "listaEDI_RemessaConhecimento(EDI_RemessaED ed)");
    }

    return list;
  }


  public EDI_RemessaED getByRecord (EDI_RemessaED ed) throws Excecoes {

    String sql = null;
    EDI_RemessaED edVolta = new EDI_RemessaED ();
    ResultSet resP = null;

    try {

      sql = " SELECT * from EDI_Remessas WHERE EDI_Remessas.OID_EDI_Remessa = '" + ed.getOID_EDI_Remessa () + "'";
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

  public String localiza_Entregador (long oid_Cidade, long oid_Modal) throws Excecoes {

        // System.out.println("localiza_Entregador");

    String sql = null;
    String oid_Pessoa="";
    try {


      sql = " SELECT Regioes_Entregas.oid_Pessoa  " +
          " FROM  Regioes_Entregas " +
          " WHERE Regioes_Entregas.DM_Destino ='C' " +
          " AND   Regioes_Entregas.oid_Destino = " + oid_Cidade +
          " AND   Regioes_Entregas.oid_Modal   = " + oid_Modal;
        // System.out.println(sql);

      ResultSet res = this.executasql.executarConsulta (sql);

      if (res.next ()) {
        // System.out.println("acho ent p/cidade="+res.getString ("oid_Pessoa"));

        oid_Pessoa = res.getString ("oid_Pessoa");
      }
      else {
        sql = " SELECT Regioes_Estados.oid_Estado  " +
            " FROM   Regioes_Estados, Cidades " +
            " WHERE  Regioes_Estados.oid_Regiao_Estado = Cidades.oid_Regiao_Estado " +
            " AND    Cidades.oid_Cidade= " + oid_Cidade;

        // System.out.println(sql);
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          sql = " SELECT Regioes_Entregas.oid_Pessoa  " +
              " FROM  Regioes_Entregas " +
              " WHERE Regioes_Entregas.DM_Destino ='E' " +
              " AND   Regioes_Entregas.oid_Destino = " + res.getString ("oid_Estado") +
              " AND   Regioes_Entregas.oid_Modal   = " + oid_Modal;
        // System.out.println(sql);
          res = this.executasql.executarConsulta (sql);
          if (res.next ()) {
            // System.out.println("acho ent p/estado="+res.getString ("oid_Pessoa"));

            oid_Pessoa = res.getString ("oid_Pessoa");
          }
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao localiza_Entregador");
      excecoes.setMetodo ("localiza_Entregador");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return oid_Pessoa;
  }

  private void importa_Remessa (String dm_origem_remessa, String arquivo , String oid_Cliente) throws Excecoes {

	    // System.out.println ("dm_origem_remessa -->> " + dm_origem_remessa);
	  
	       	if("DAUDT".equals(dm_origem_remessa))
	       		importa_NotFisLupatech(arquivo, oid_Cliente);
	        else if("NOTFISLUPA".equals(dm_origem_remessa))
		            importa_NotFisLupatech(arquivo, oid_Cliente);
	 	    else if("GETNET".equals(dm_origem_remessa))
	 	    		importa_GetNet(arquivo, oid_Cliente);
	 	    else importa_Regispel(arquivo, oid_Cliente);

  }
	  
  private void importa_GetNet (String arquivo , String oid_Cliente) throws Excecoes {

    String NM_Registro = "";
    String Incluir = "S";
    System.out.println ("importa_GetNet -->> " + arquivo);

    int linha = 0;
    double var = 0;

    long nr_rem = 0;
    long nr_l = 0;
    String nr_remessa="";
    String cd_produto = "";
    String dm_tipo_servico = "";
    String dm_peso = "";
    String nr_peso = "";
    String nr_volumes = "";
    String nm_natureza = "";
    String tx_observacao = "";
    String cd_cliente = "";
    String nr_pedido = "";
    String nr_nota_fiscal = "";
    String vl_nota_fiscal ="";
    String nm_remetente = "";
    String nm_endereco= "";
    String nm_cidade = "";
    String cd_estado = "";
    String nr_cep = "";
    String NR_CNPJ_CPF = "";
    
    String nm_destinatario = "";
    String nm_endereco_dest = "";
    String nm_cidade_dest = "";
    String cd_estado_dest = "";
    String nr_cep_dest = "";
    String NR_CNPJ_CPF_dest = "";
    String nr_inscricao_estadual = "";
    String nr_inscricao_estadual_dest = "";
  
    String DT_Remessa = "";
    String dm_tipo_registro="";
    String livre="";
    String comp_end="";
    
    long nf2 = 1000;

    try {
      ManipulaArquivo man = new ManipulaArquivo ("");
      LineNumberReader line = man.leLinha (arquivo);
      //this.inicioTransacao();
      //this.executasql = this.sql;

      System.out.println (" ---- ANTES --- nov" );
      
      String sql =" SELECT max(NR_Remessa) as NR_Remessa, max(NR_Lote) as NR_Lote FROM EDI_Remessas ";
       System.out.println ("sql=" + sql);
      ResultSet resRem = executasql.executarConsulta (sql);
      if (resRem.next ()) {
          //System.out.println (" ----  1=" +resRem.getLong("NR_Remessa"));
          //System.out.println (" ----  2=" +resRem.getLong("NR_Lote"));
    	  //nr_rem=resRem.getLong("NR_Remessa");
    	  //nr_l=resRem.getLong("NR_Lote");
      }
      nr_l++;
      nr_lote=String.valueOf(nr_l);
      //this.fimTransacao(true);
      System.out.println (" =>>>  teste 2");
      
      if (line.ready ()) {

         System.out.println ("Line Ready");

        while ( (NM_Registro = line.readLine ()) != null) {

           System.out.println ("Line ->>" + linha + " - " + NM_Registro);

          if (NM_Registro != null && NM_Registro.length () > 10) {

        	  	Incluir = "N";

        	  	

	            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
	            NM_Registro = NM_Registro.toUpperCase ();
        	  	System.out.println ("NM_Registro rep2->>" + NM_Registro);

	            dm_tipo_registro=NM_Registro.substring(0,3);

	            nr_pedido=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 1, ";");
	            nr_remessa=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 1, ";");
	            NR_CNPJ_CPF=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 2, ";");
	            nr_inscricao_estadual=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 3, ";");

	            nm_remetente=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 4, ";");
	            nm_endereco=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 6, ";");
	            nm_endereco+=","+ManipulaString.buscaSegmentoDelimitado(NM_Registro, 7, ";");
            	nm_endereco+="-"+ManipulaString.buscaSegmentoDelimitado(NM_Registro, 8, ";");

            	nm_cidade=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 9, ";");
            	cd_estado=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 10, ";");
            	nr_cep=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 11, ";");
	            nm_endereco=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 12, ";");
            	  
	            NR_CNPJ_CPF_dest=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 12, ";");
	            nr_inscricao_estadual_dest=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 13, ";");
	            nm_destinatario=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 14, ";");
	            nm_endereco_dest=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 16, ";");
	            nm_endereco_dest+=","+ManipulaString.buscaSegmentoDelimitado(NM_Registro, 17, ";");
	            nm_endereco_dest+="-"+ManipulaString.buscaSegmentoDelimitado(NM_Registro, 18, ";");
	            nm_cidade_dest=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 19, ";");
	            cd_estado_dest=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 20, ";");
	            nr_cep_dest=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 21, ";");
	            nr_peso=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 22, ";");
	            nr_volumes=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 23, ";");
	            vl_nota_fiscal=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 24, ";");
	            nr_nota_fiscal=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 25, ";");

	            // System.out.println ("NR_CNPJ_CPF ------->>" + NR_CNPJ_CPF);
                 System.out.println ("nm_remetente -------->>" + nm_remetente);
	            
            	// System.out.println ("dm_tipo_registro ->>" + dm_tipo_registro);
               
                DT_Remessa=Data.getDataDMY();
                
                // System.out.println ("DT_Remessa ->>" + DT_Remessa);
                // System.out.println ("cd_produto ->>" + cd_produto);
                // System.out.println ("dm_tipo_servico ->>" + dm_tipo_servico);
                // System.out.println ("nr_pedido ->>" + nr_pedido);

                // System.out.println ("NR_CNPJ_CPF ->>" + NR_CNPJ_CPF);
                // System.out.println ("nm_remetente ->>" + nm_remetente);
                // System.out.println ("nm_endereco ->>" + nm_endereco);
                // System.out.println ("nm_cidade ->>" + nm_cidade);
                // System.out.println ("cd_estado ->>" + cd_estado);
                // System.out.println ("nr_cep ->>" + nr_cep);

                // System.out.println ("NR_CNPJ_CPF_dest ->>" + NR_CNPJ_CPF_dest);
                // System.out.println ("nm_destinatario ->>" + nm_destinatario);
                // System.out.println ("nm_endereco_dest ->>" + nm_endereco_dest);
                // System.out.println ("nm_cidade_dest ->>" + nm_cidade_dest);
                // System.out.println ("cd_estado_dest ->>" + cd_estado_dest);

                System.out.println ("nr_cep_dest ->>" + nr_cep_dest);

                // System.out.println ("dm_peso ->>" + dm_peso);
                // System.out.println ("nr_remessa ->>" + nr_remessa);
                // System.out.println ("nr_lote ->>" + nr_lote);

                // System.out.println ("nr_nota_fiscal ->>" + nr_nota_fiscal);
                // System.out.println ("nr_peso ->>" + nr_peso);

                nr_peso=nr_peso.replaceAll(",", "." );
                nr_volumes=nr_volumes.replaceAll(",", "." );
                vl_nota_fiscal=vl_nota_fiscal.replaceAll(",", "." );
                
                 System.out.println ("nr_volumes ->>" + nr_volumes);
                // System.out.println ("vl_nota_fiscal ->>" + vl_nota_fiscal);

                nf2=0;
                nr_nota_fiscal = SeparaEndereco.corrigeNumero(nr_nota_fiscal);
                if (nr_nota_fiscal.length()>0) {
	                 System.out.println ("nr_nota_fiscal 2->>" + nr_nota_fiscal.length());
	                nf2 = new Long(nr_nota_fiscal).longValue();
	                nr_nota_fiscal=String.valueOf(nf2).toString();
                }

                 System.out.println ("nf2 ===================>>" + nf2);
                

                EDI_RemessaED importaED = new EDI_RemessaED ();
                importaED.setOID_Pessoa (oid_Cliente);
                importaED.setDT_Remessa (DT_Remessa);
                nr_rem++;

                importaED.setNR_Remessa (String.valueOf(nr_remessa));
                importaED.setNR_Lote (String.valueOf(nr_lote));

                // System.out.println ("importaEDsetNR_Lote ===================>>" + importaED.getNR_Lote());
                // System.out.println ("setNR_Remessa ===================>>" + importaED.getNR_Remessa());

                
                importaED.setDM_Origem_Remessa("GETNET");
                
                importaED.setNR_CNPJ_CPF (NR_CNPJ_CPF);
                importaED.setNM_Remetente (nm_destinatario);
                importaED.setNM_Endereco (nm_endereco);
                importaED.setNM_Bairro (" ");
                importaED.setNM_Cidade (nm_cidade);
                importaED.setCD_Estado (cd_estado);
                importaED.setNR_Cep (nr_cep);
                
                importaED.setNR_CNPJ_CPF_Destinatario (NR_CNPJ_CPF_dest);
                importaED.setNM_Destinatario (nm_destinatario);
                importaED.setNM_Endereco_Dest (nm_endereco_dest);
                importaED.setNM_Bairro_Dest ("");
                importaED.setNM_Cidade_Dest (nm_cidade_dest);
                importaED.setCD_Estado_Dest (cd_estado_dest);
                importaED.setNR_Cep_Dest (nr_cep_dest);

                importaED.setNR_Pedido (nr_pedido);
                importaED.setDM_Tipo_Servico (dm_tipo_servico);
                importaED.setDT_Entrada (Data.getDataDMY ());
                importaED.setHR_Entrada (Data.getHoraHM ());
                var = SeparaEndereco.confirmaValorDouble (nr_peso);
                importaED.setNR_Peso (var );
                importaED.setNR_Peso_Cubado (var );
                importaED.setDM_Situacao ("P");

                var = SeparaEndereco.confirmaValorDouble (nr_volumes);
                importaED.setNR_Volumes (var );
                
                importaED.setNR_Nota_Fiscal (nf2);
                importaED.setCD_Produto(cd_produto);

                System.out.println ("incluiu importa_Regispel importaED ->>");
                
                this.inclui (importaED);

                
                // System.out.println ("NR_Remessa ->>" + nr_remessa);

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
      excecoes.setMetodo ("importa_Remessa");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  private void importa_Regispel (String arquivo , String oid_Cliente) throws Excecoes {

	    String NM_Registro = "";
	    String Incluir = "S";
	    // System.out.println ("importa_Regispel -->> " + arquivo);

	    int linha = 0;
	    double var = 0;

	    String NR_Remessa = "";
	    String cd_produto = "";
	    String dm_tipo_servico = "";
	    String dm_peso = "";
	    String nr_peso = "";
	    String nr_pedido = "";

	    String nm_destinatario = "";
	    String nm_endereco_dest = "";
	    String nm_cidade_dest = "";
	    String cd_estado_dest = "";
	    String nr_cep_dest = "";
	    String NR_CNPJ_CPF_dest = "";
	  
	    String nr_nota_fiscal = "";
	  
	    String DT_Remessa = "";
	    String dm_tipo_registro="";



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
	            dm_tipo_registro=NM_Registro.substring(0,3);


	            // System.out.println ("dm_tipo_registro ->>" + dm_tipo_registro);

	            if (linha==0){
	              nr_lote=NM_Registro.substring (34 , 39);
	              String dt_rem=NM_Registro.substring (28 , 30);
	              String mes_rem=NM_Registro.substring (25 , 27);
	              String ano_rem=NM_Registro.substring (20 , 24);

	              // System.out.println ("dt_rem ->>" + dt_rem);
	              // System.out.println ("mes_rem ->>" + mes_rem);
	              // System.out.println ("ano_rem ->>" + ano_rem);

	              // System.out.println ("nr_lote =====>>" + nr_lote);

	              
	              DT_Remessa=dt_rem+"/"+mes_rem+"/"+ano_rem;
	            }else{
	              if("009".equals(dm_tipo_registro)){
	                cd_produto = NM_Registro.substring (19 , 23);
	                dm_tipo_servico = NM_Registro.substring (23 , 24);
	                dm_peso = NM_Registro.substring (24 , 26);
	                nr_peso = NM_Registro.substring (36 , 44);

	                nm_destinatario = NM_Registro.substring (52 , 84);
	                nm_endereco_dest = NM_Registro.substring (84 , 148);
	                nm_cidade_dest = NM_Registro.substring (148 , 178);
	                cd_estado_dest = NM_Registro.substring (178 , 180);
	                nr_cep_dest = NM_Registro.substring (180 , 188);
	                NR_CNPJ_CPF_dest = NM_Registro.substring (188 , 202);

	                nr_nota_fiscal = "";
	                nr_pedido = NM_Registro.substring (245 , 252);

	                // System.out.println ("DT_Remessa ->>" + DT_Remessa);
	                // System.out.println ("cd_produto ->>" + cd_produto);
	                // System.out.println ("dm_tipo_servico ->>" + dm_tipo_servico);
	                // System.out.println ("nr_pedido ->>" + nr_pedido);
	                // System.out.println ("NR_CNPJ_CPF ->>" + NR_CNPJ_CPF_dest);
	                // System.out.println ("nr_cep_dest ->>" + nr_cep_dest);
	                // System.out.println ("nm_destinatario ->>" + nm_destinatario);
	                // System.out.println ("nm_endereco ->>" + nm_endereco_dest);
	                // System.out.println ("nm_cidade ->>" + nm_cidade_dest);
	                // System.out.println ("cd_estado ->>" + cd_estado_dest);
	                // System.out.println ("dm_peso ->>" + dm_peso);
	                // System.out.println ("nr_peso ->>" + nr_peso);
	                // System.out.println ("nr_lote ->>" + nr_lote);
	                // System.out.println ("nr_nota_fiscal ->>" + nr_nota_fiscal);

	                NR_Remessa=nr_lote+"-"+nr_pedido;

	                // System.out.println ("NR_Remessa ->>" + NR_Remessa);

	                EDI_RemessaED importaED = new EDI_RemessaED ();
	                importaED.setOID_Pessoa (oid_Cliente);
	                importaED.setDT_Remessa (DT_Remessa);
	                importaED.setNR_Remessa (NR_Remessa);
	                importaED.setDM_Origem_Remessa("REGPEL");
	                
	                importaED.setNM_Remetente (" ");
	                importaED.setNM_Endereco (" ");
	                importaED.setNM_Bairro (" ");
	                importaED.setNM_Cidade (" ");
	                importaED.setCD_Estado (" ");
	                importaED.setNR_Cep (" ");
	                
	                importaED.setNR_CNPJ_CPF_Destinatario (NR_CNPJ_CPF_dest);
	                importaED.setNM_Destinatario (nm_destinatario);
	                importaED.setNM_Endereco_Dest (nm_endereco_dest);
	                importaED.setNM_Bairro_Dest ("");
	                importaED.setNM_Cidade_Dest (nm_cidade_dest);
	                importaED.setCD_Estado_Dest (cd_estado_dest);
	                importaED.setNR_Cep_Dest (nr_cep_dest);

	                importaED.setNR_Lote (nr_lote);
	                importaED.setNR_Pedido (nr_pedido);
	                importaED.setDM_Tipo_Servico (dm_tipo_servico);
	                importaED.setDT_Entrada (Data.getDataDMY ());
	                importaED.setHR_Entrada (Data.getHoraHM ());
	                var = SeparaEndereco.confirmaValorDouble (nr_peso);
	                importaED.setNR_Peso (var / 1000000);
	                importaED.setNR_Peso_Cubado (var / 1000000);
	                importaED.setDM_Situacao ("P");
	                importaED.setNR_Nota_Fiscal (0);
	                importaED.setCD_Produto(cd_produto);

	                this.inclui (importaED);

	                // System.out.println ("incluiu importa_Regispel importaED ->>");

	              }
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
	      excecoes.setMetodo ("importa_Regispel");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }
  
  private void importa_NotFisLupatech(String arquivo, String oid_Cliente)
  throws Excecoes
{
  String NM_Registro = "";
  String tipo_Registro = "";
  String alfa1 = "";
  String Incluir = "S";
  long nr_rem = 0L;
  long nr_l = 0L;
  EDI_RemessaED importaED = new EDI_RemessaED();
  importaED.setNR_CNPJ_CPF_Destinatario(" ");
  importaED.setNM_Remetente(" ");
  importaED.setNM_Endereco(" ");
  importaED.setNM_Bairro(" ");
  importaED.setNM_Cidade(" ");
  importaED.setCD_Estado(" ");
  importaED.setNR_Cep(" ");
  importaED.setNM_Destinatario(" ");
  importaED.setNM_Endereco_Dest(" ");
  importaED.setNM_Bairro_Dest(" ");
  importaED.setNM_Cidade_Dest(" ");
  importaED.setCD_Estado_Dest(" ");
  importaED.setNR_Cep_Dest(" ");
  int linha = 0;
  String peso = "";
  // System.out.println("importa_NotFisProc3 -->> " + arquivo);
  try
  {
      ManipulaArquivo man = new ManipulaArquivo("");
      LineNumberReader line = man.leLinha(arquivo);
      String sql = " SELECT max(NR_Remessa) as NR_Remessa, max(NR_Lote) as NR_Lote FROM EDI_Remessas ";
      // System.out.println("sql=" + sql);
      ResultSet resRem = executasql.executarConsulta(sql);
      if(resRem.next())
      {
          nr_rem = resRem.getLong("NR_Remessa");
          nr_l = resRem.getLong("NR_Lote");
      }
      nr_l++;
      nr_lote = String.valueOf(nr_l);
      if(line.ready())
          while((NM_Registro = line.readLine()) != null) 
          {
              Incluir = "N";
              NM_Registro = SeparaEndereco.corrigeString(NM_Registro.toUpperCase()) + "       ";
              tipo_Registro = NM_Registro.substring(1, 3);
              if("11".endsWith(tipo_Registro))
              {
                  importaED.setOID_Pessoa(NM_Registro.substring(3, 17).trim());
                  importaED.setNM_Endereco(NM_Registro.substring(32, 72).trim());
                  importaED.setNM_Cidade(SeparaEndereco.tiraAspas(NM_Registro.substring(72, 107)).trim());
                  importaED.setNR_Cep(NM_Registro.substring(107, 116).trim());
                  importaED.setCD_Estado(NM_Registro.substring(116, 125).trim());
                  importaED.setDT_Remessa(NM_Registro.substring(125, 133));
                  alfa1 = NM_Registro.substring(125, 133);
                  importaED.setDT_Remessa(SeparaEndereco.quebraPalavra(alfa1, 1, 2) + "/" + SeparaEndereco.quebraPalavra(alfa1, 3, 4) + "/" + SeparaEndereco.quebraPalavra(alfa1, 5, 8));
                  if(alfa1.equals("") || alfa1.equals(null) || alfa1.equals("null"))
                      importaED.setDT_Remessa(Data.getDataDMY());
                  importaED.setNM_Remetente(SeparaEndereco.tiraAspas(NM_Registro.substring(133, 168)));
              }
              if("12".endsWith(tipo_Registro))
              {
                  importaED.setNM_Destinatario(SeparaEndereco.tiraAspas(NM_Registro.substring(3, 43)).trim());
                  importaED.setNR_CNPJ_CPF_Destinatario(NM_Registro.substring(43, 57).trim());
                  importaED.setNM_Endereco_Dest(NM_Registro.substring(72, 112).trim());
                  importaED.setNM_Bairro_Dest(NM_Registro.substring(112, 132).trim());
                  importaED.setNM_Cidade_Dest(SeparaEndereco.tiraAspas(NM_Registro.substring(132, 167)).trim());
                  importaED.setNR_Cep_Dest(NM_Registro.substring(167, 176).trim());
                  importaED.setCD_Estado_Dest(NM_Registro.substring(185, 194).trim());
              }
              // System.out.println("8>> setNM_Destinatario =" + importaED.getNM_Destinatario());
              if("13".endsWith(tipo_Registro))
              {
                  importaED.setNR_Nota_Fiscal((new Long(SeparaEndereco.corrigeNumero(NM_Registro.substring(32, 40).trim()))).longValue());
                  importaED.setNR_Remessa(importaED.getOID_Pessoa().substring(0, 2) + String.valueOf(importaED.getNR_Nota_Fiscal()));
                  importaED.setNR_Pedido(importaED.getOID_Pessoa().substring(0, 2) + String.valueOf(importaED.getNR_Nota_Fiscal()));
                  alfa1 = NM_Registro.substring(40, 48);
                  importaED.setDT_Entrada(SeparaEndereco.quebraPalavra(alfa1, 1, 2) + "/" + SeparaEndereco.quebraPalavra(alfa1, 3, 4) + "/" + SeparaEndereco.quebraPalavra(alfa1, 5, 8));
                  if(alfa1.equals("") || alfa1.equals(null) || alfa1.equals("null"))
                      importaED.setDT_Entrada(Data.getDataDMY());
                  importaED.setVL_Nota_Fiscal(Double.parseDouble(NM_Registro.substring(85, 98) + "." + NM_Registro.substring(98, 100)));
                  String oid_Pessoa_Entregadora = SeparaEndereco.corrigeNumero(NM_Registro.substring(213, 227));
                  if(oid_Pessoa_Entregadora != null && oid_Pessoa_Entregadora.length() > 4)
                      importaED.setOID_Pessoa_Entregadora(oid_Pessoa_Entregadora);
                  importaED.setDM_Origem_Remessa("NOTFISLUPA");
                  importaED.setNR_Lote(nr_lote);
                  importaED.setDM_Situacao("P");

                  String nr_Vol = NM_Registro.substring(79, 83);
                  //System.out.println("nr_Vol: " + nr_Vol);

                  importaED.setNR_Volumes(Double.parseDouble(nr_Vol));
                  if(importaED.getNR_Volumes() > 999999)
                      importaED.setNR_Volumes(1);

                 //System.out.println("Volumes: " + importaED.getNR_Volumes());
                 
                  peso = NM_Registro.substring(100, 105) + "." + NM_Registro.substring(105, 107);
                  importaED.setNR_Peso(Double.parseDouble(peso));
                  if(importaED.getNR_Peso() > 999999)
                      importaED.setNR_Peso(999999);
              }
              if("17".endsWith(tipo_Registro))
              {
                  importaED.setOID_Pessoa_Pagador(NM_Registro.substring(43, 57).trim());
                  Incluir = "S";
              }
              if(Incluir.equals("S"))
              {
                  importaED.setDM_Situacao("I");
                  inclui(importaED);
                  // System.out.println("incluiu importaED.setNR_Pedido  ->> " + importaED.getNR_Pedido());
                  // System.out.println("incluiu importaED.setOID_Pessoa_Pagador  ->> " + importaED.getOID_Pessoa_Pagador());
                  // System.out.println("incluiu importaED.getOID_Pessoa_Entregadora  ->> " + importaED.getOID_Pessoa_Entregadora());
                  // System.out.println("incluiu registro ->> " + NM_Registro);
              }
              linha++;
              line.setLineNumber(linha);
          }
      line.close();
  }
  catch(Exception exc)
  {
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(getClass().getName());
      excecoes.setMetodo("importa_Regispel");
      excecoes.setExc(exc);
      throw excecoes;
  }
}


  private void importa_Daudt(String arquivo, String oid_Cliente)
  throws Excecoes
{
  String NM_Registro = "";
  String tipo_Registro = "";
  String alfa1 = "";
  String Incluir = "S";
  long nr_rem = 0L;
  long nr_l = 0L;
  EDI_RemessaED importaED = new EDI_RemessaED();
  importaED.setNR_CNPJ_CPF_Destinatario(" ");
  importaED.setNM_Remetente(" ");
  importaED.setNM_Endereco(" ");
  importaED.setNM_Bairro(" ");
  importaED.setNM_Cidade(" ");
  importaED.setCD_Estado(" ");
  importaED.setNR_Cep(" ");
  importaED.setNM_Destinatario(" ");
  importaED.setNM_Endereco_Dest(" ");
  importaED.setNM_Bairro_Dest(" ");
  importaED.setNM_Cidade_Dest(" ");
  importaED.setCD_Estado_Dest(" ");
  importaED.setNR_Cep_Dest(" ");
  int linha = 0;
  String peso = "";
  // System.out.println("importa_Daudt -->> " + arquivo);
  try
  {
      ManipulaArquivo man = new ManipulaArquivo("");
      LineNumberReader line = man.leLinha(arquivo);
      String sql = " SELECT max(NR_Remessa) as NR_Remessa, max(NR_Lote) as NR_Lote FROM EDI_Remessas ";
      // System.out.println("sql=" + sql);
      ResultSet resRem = executasql.executarConsulta(sql);
      if(resRem.next())
      {
          nr_rem = resRem.getLong("NR_Remessa");
          nr_l = resRem.getLong("NR_Lote");
      }
      nr_l++;
      nr_lote = String.valueOf(nr_l);
      if(line.ready())
          while((NM_Registro = line.readLine()) != null) 
          {
              Incluir = "N";
              NM_Registro = SeparaEndereco.corrigeString(NM_Registro.toUpperCase()) + "       ";
              tipo_Registro = NM_Registro.substring(1, 3);
              if("11".endsWith(tipo_Registro))
              {
                  importaED.setOID_Pessoa(NM_Registro.substring(3, 17).trim());
                  importaED.setNM_Endereco(NM_Registro.substring(32, 72).trim());
                  importaED.setNM_Cidade(SeparaEndereco.tiraAspas(NM_Registro.substring(72, 107)).trim());
                  importaED.setNR_Cep(NM_Registro.substring(107, 116).trim());
                  importaED.setCD_Estado(NM_Registro.substring(116, 125).trim());
                  importaED.setDT_Remessa(NM_Registro.substring(125, 133));
                  alfa1 = NM_Registro.substring(125, 133);
                  importaED.setDT_Remessa(SeparaEndereco.quebraPalavra(alfa1, 1, 2) + "/" + SeparaEndereco.quebraPalavra(alfa1, 3, 4) + "/" + SeparaEndereco.quebraPalavra(alfa1, 5, 8));
                  if(alfa1.equals("") || alfa1.equals(null) || alfa1.equals("null"))
                      importaED.setDT_Remessa(Data.getDataDMY());
                  importaED.setNM_Remetente(SeparaEndereco.tiraAspas(NM_Registro.substring(133, 168)));
              }
              if("12".endsWith(tipo_Registro))
              {
                  importaED.setNM_Destinatario(SeparaEndereco.tiraAspas(NM_Registro.substring(3, 43)).trim());
                  importaED.setNR_CNPJ_CPF_Destinatario(NM_Registro.substring(43, 57).trim());
                  importaED.setNM_Endereco_Dest(NM_Registro.substring(72, 112).trim());
                  importaED.setNM_Bairro_Dest(NM_Registro.substring(112, 132).trim());
                  importaED.setNM_Cidade_Dest(SeparaEndereco.tiraAspas(NM_Registro.substring(132, 167)).trim());
                  importaED.setNR_Cep_Dest(NM_Registro.substring(167, 176).trim());
                  importaED.setCD_Estado_Dest(NM_Registro.substring(185, 194).trim());
              }
              // System.out.println("8>> setNM_Destinatario =" + importaED.getNM_Destinatario());
              if("13".endsWith(tipo_Registro))
              {
                  importaED.setNR_Nota_Fiscal((new Long(SeparaEndereco.corrigeNumero(NM_Registro.substring(32, 40).trim()))).longValue());
                  importaED.setNR_Remessa(importaED.getOID_Pessoa().substring(0, 2) + String.valueOf(importaED.getNR_Nota_Fiscal()));
                  importaED.setNR_Pedido(importaED.getOID_Pessoa().substring(0, 2) + String.valueOf(importaED.getNR_Nota_Fiscal()));
                  alfa1 = NM_Registro.substring(40, 48);
                  importaED.setDT_Entrada(SeparaEndereco.quebraPalavra(alfa1, 1, 2) + "/" + SeparaEndereco.quebraPalavra(alfa1, 3, 4) + "/" + SeparaEndereco.quebraPalavra(alfa1, 5, 8));
                  if(alfa1.equals("") || alfa1.equals(null) || alfa1.equals("null"))
                      importaED.setDT_Entrada(Data.getDataDMY());
                  importaED.setVL_Nota_Fiscal(Double.parseDouble(NM_Registro.substring(85, 98) + "." + NM_Registro.substring(98, 100)));
                  String oid_Pessoa_Entregadora = SeparaEndereco.corrigeNumero(NM_Registro.substring(213, 227));
                  if(oid_Pessoa_Entregadora != null && oid_Pessoa_Entregadora.length() > 4)
                      importaED.setOID_Pessoa_Entregadora(oid_Pessoa_Entregadora);
                  importaED.setDM_Origem_Remessa("NOTFISLUPA");
                  importaED.setNR_Lote(nr_lote);
                  importaED.setDM_Situacao("P");
                  peso = NM_Registro.substring(100, 105) + "." + NM_Registro.substring(105, 107);
                  importaED.setNR_Peso(Double.parseDouble(peso));
                  if(importaED.getNR_Peso() > 999999D)
                      importaED.setNR_Peso(999999D);
              }
              if("17".endsWith(tipo_Registro))
              {
                  importaED.setOID_Pessoa_Pagador(NM_Registro.substring(43, 57).trim());
                  Incluir = "S";
              }
              if(Incluir.equals("S"))
              {
                  importaED.setDM_Situacao("I");
                  inclui(importaED);
                  // System.out.println("incluiu importaED.setNR_Pedido  ->> " + importaED.getNR_Pedido());
                  // System.out.println("incluiu importaED.setOID_Pessoa_Pagador  ->> " + importaED.getOID_Pessoa_Pagador());
                  // System.out.println("incluiu importaED.getOID_Pessoa_Entregadora  ->> " + importaED.getOID_Pessoa_Entregadora());
                  // System.out.println("incluiu registro ->> " + NM_Registro);
              }
              linha++;
              line.setLineNumber(linha);
          }
      line.close();
  }
  catch(Exception exc)
  {
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(getClass().getName());
      excecoes.setMetodo("importa_Regispel");
      excecoes.setExc(exc);
      throw excecoes;
  }
}


  
  public ArrayList gera_Remessa(EDI_RemessaED ed)
  throws Excecoes
{
  ArrayList list = new ArrayList();
  ResultSet rs = null;
  ResultSet res = null;
  ResultSet resCli = null;
  String sql = "";
  String OID_Nota_Fiscal = null;
  String oid_Pessoa = "";
  String oid_Pessoa_Destinatario = "";
  String DM_Isencao_Seguro = "";
  String oid_Vendedor = "";
  String DM_Tipo_Documento = "M";
  long oid_Empresa = 0;
  long oid_Cidade_Origem = 0;
  try
  {
      importa_Remessa(ed.getDM_Origem_Remessa(), ed.getNM_Arquivo(), ed.getOID_Pessoa());
      EDI_RemessaED edEDI_Remessa = new EDI_RemessaED();
      
      System.out.println("Passei GERA REME =>>>>>  ");
      
      sql = " SELECT * FROM Unidades WHERE Unidades.oid_Unidade = " + ed.getOID_Unidade();
      System.out.println("Unidade=" + sql);
      for(resCli = executasql.executarConsulta(sql); resCli.next();)
      {
          System.out.println("Unidade ok ");
          oid_Empresa = resCli.getLong("oid_Empresa");
          DM_Tipo_Documento = resCli.getString("dm_tipo_documento_padrao");
      }

      System.out.println("DM_Tipo_Documento=>" + DM_Tipo_Documento);
      sql = " SELECT oid_Cidade, DM_Isencao_Seguro, oid_Vendedor FROM Pessoas, Clientes WHERE Pessoas.oid_Pessoa = Clientes.oid_Cliente AND Clientes.oid_Cliente = '" + ed.getOID_Pessoa() + "'";
      for(resCli = executasql.executarConsulta(sql); resCli.next();)
      {
          System.out.println(" tem cliente");
          oid_Cidade_Origem = resCli.getLong("oid_Cidade");
      }

      sql = " SELECT oid_Vendedor, DM_Isencao_Seguro FROM Clientes WHERE oid_Cliente = '05127438000230'";
      for(resCli = executasql.executarConsulta(sql); resCli.next();)
      {
          System.out.println(" tem cliente");
          DM_Isencao_Seguro = resCli.getString("DM_Isencao_Seguro");
          oid_Vendedor = resCli.getString("oid_Vendedor");
      }

      System.out.println("Passei Cliente=" + sql);
      sql = " SELECT * FROM EDI_Remessas  WHERE  EDI_Remessas.NR_Lote = '" + nr_lote + "'";
      System.out.println("sql" + sql);
      for(res = executasql.executarConsulta(sql); res.next();)
      {
          edEDI_Remessa = carregaED(res);
          edEDI_Remessa.setNM_Pessoa_Remetente((res.getString("NM_Remetente") + "                                                               ").substring(0, 40));
          edEDI_Remessa.setNM_Pessoa_Destinatario((res.getString("NM_Destinatario") + "                                                               ").substring(0, 40));
          System.out.println(" NedEDI_Remessa.getOID_Cidade_Dest=" + edEDI_Remessa.getOID_Cidade_Dest());

          boolean nf_OK=true;
          if (edEDI_Remessa.getNR_Nota_Fiscal()>0){
	          sql = " SELECT oid_Nota_Fiscal FROM Notas_Fiscais  " + 
	                " WHERE  NR_Nota_Fiscal = " + edEDI_Remessa.getNR_Nota_Fiscal() +
	                " AND    oid_Pessoa = '" + edEDI_Remessa.getOID_Pessoa() + "'" ;
	          ResultSet resNF = executasql.executarConsulta(sql);          
	          if (resNF.next() && edEDI_Remessa.getOID_Cidade_Dest() > 0)
	          {
	        	  nf_OK=false;
	          }
          }
          
          if (nf_OK && edEDI_Remessa.getOID_Cidade_Dest() > 0)
          {
              Nota_FiscalED nf = new Nota_FiscalED();
              System.out.println(" NF===>>" + edEDI_Remessa.getNR_Nota_Fiscal());
              System.out.println(" getNR_Pedido===>>" + edEDI_Remessa.getNR_Pedido());
              System.out.println("Passei EDI 1");
              oid_Pessoa = ed.getOID_Pessoa();
              oid_Pessoa_Destinatario = inclui_Destinatario(edEDI_Remessa);
              System.out.println("oid_Pessoa NF=" + oid_Pessoa);
              System.out.println("oid_Pessoa_Destinatario NF=" + oid_Pessoa_Destinatario);
              nf.setOID_Pessoa(oid_Pessoa);
              nf.setOID_Pessoa_Destinatario(oid_Pessoa_Destinatario);
              nf.setOID_Pessoa_Entregadora(edEDI_Remessa.getOID_Pessoa_Entregadora());
              System.out.println("Passei EDI 7");
              nf.setOID_Unidade(ed.getOID_Unidade());
              nf.setOid_Deposito(ed.getOID_Armazem());
              nf.setOID_Modal(ed.getOID_Modal());
              nf.setOID_Produto(ed.getOID_Produto());
              nf.setDT_Emissao(edEDI_Remessa.getDT_Remessa());
              nf.setNR_Nota_Fiscal(edEDI_Remessa.getNR_Nota_Fiscal());
              nf.setNM_Serie("");
              System.out.println("Passei setNR_Nota_Fiscal=>>" + edEDI_Remessa.getNR_Nota_Fiscal());
              nf.setVL_Nota_Fiscal(edEDI_Remessa.getVL_Nota_Fiscal());
              System.out.println("Passei getVL_Nota_Fiscal=>>" + edEDI_Remessa.getVL_Nota_Fiscal());
              nf.setDM_Situacao("N");
              nf.setDM_Situacao_Embarque(" ");
              nf.setDM_Transferencia(" ");
              nf.setDM_Exportacao(" ");
              nf.setDM_Situacao_Embarque(" ");
              nf.setDm_Stamp(" ");
              nf.setNR_Pedido(edEDI_Remessa.getNR_Pedido());
              System.out.println("Passei getNR_Pedido=>>" + edEDI_Remessa.getNR_Pedido());
              nf.setNR_Lote(nr_lote);
              System.out.println("Passei EDI 9");
              nf.setNR_Peso(edEDI_Remessa.getNR_Peso());
              nf.setNR_Volumes(1.0D);
              nf.setDT_Entrada(Data.getDataDMY());
              nf.setHR_Entrada(Data.getHoraHM());
              nf.setDM_Transferencia("");
              nf.setDM_Exportacao("N");
              nf.setDM_Transferencia("N");
              nf.setDM_Tipo_Conhecimento("1");
              nf.setTX_Observacao(" ");
              if("REGPEL".equals(edEDI_Remessa.getDM_Origem_Remessa()) || "GETNET".equals(edEDI_Remessa.getDM_Origem_Remessa()))
                  nf.setTX_Observacao("Cod.Mat.: " + edEDI_Remessa.getCD_Produto() + "  Cont.Cliente:" + edEDI_Remessa.getNR_Pedido());
              nf.setDM_Tipo_Pagamento("1");
              sql = " SELECT oid_nota_fiscal from notas_fiscais  WHERE  oid_pessoa     = '" + oid_Pessoa + "'" + " AND    nr_pedido = '" + edEDI_Remessa.getNR_Remessa() + "'";
              System.out.println("sql" + sql);
              rs = executasql.executarConsulta(sql);
              if(!rs.next())
              {
                  System.out.println(" vai incluir NF");
                  nf = (new Nota_FiscalBD(executasql)).inclui(nf);
                  System.out.println(" NF OK");
                  nf = (new Nota_FiscalBD(executasql)).getByRecord(nf);
                  OID_Nota_Fiscal = nf.getOID_Nota_Fiscal();
              } else
              {
                  OID_Nota_Fiscal = rs.getString("oid_nota_fiscal");
              }
              System.out.println("Passei NF=" + OID_Nota_Fiscal);
              if(OID_Nota_Fiscal != null && OID_Nota_Fiscal.length() > 4)
              {
                  ConhecimentoED conED = new ConhecimentoED();
                  conED.setDM_Tipo_Pagamento("1");
                  conED.setOID_Pessoa_Pagador(edEDI_Remessa.getOID_Pessoa());
                  conED.setNM_Natureza(ed.getNM_Natureza());
                  System.out.println("Passei NF=" + OID_Nota_Fiscal);
                  System.out.println("oid_Cidade_Origem=" + oid_Cidade_Origem);
                  System.out.println("ed.getOID_Pessoa_Pagador() =" + ed.getOID_Pessoa_Pagador());
                  conED.setOID_Vendedor(oid_Vendedor);
                  conED.setOID_Produto(ed.getOID_Produto());
                  conED.setOID_Unidade(ed.getOID_Unidade());
                  conED.setOID_Empresa(oid_Empresa);
                  conED.setOID_Modal(ed.getOID_Modal());
                  conED.setDM_Tipo_Documento(DM_Tipo_Documento);
                  conED.setDM_Isento_Seguro(DM_Isencao_Seguro);
                  conED.setDM_Tipo_Conhecimento("1");
                  conED.setNR_Remessa(res.getString("NR_Remessa"));
                  conED.setDM_Conhecimento_Varias_Notas_Fiscais("N");
                  conED.setNR_Conhecimento(0L);
                  conED.setDM_Impresso("N");
                  conED.setDM_Responsavel_Cobranca("");
                  conED.setDT_Emissao(Data.getDataDMY());
                  System.out.println(" inclui ==============");
                  conED.setDT_Previsao_Entrega("");
                  conED.setHR_Previsao_Entrega("");
                  conED.setOID_Cidade_Destino(edEDI_Remessa.getOID_Cidade_Dest());
                  System.out.println("oid_Cidade_Origem-->>>>>" + oid_Cidade_Origem);
                  System.out.println("oid_Cidade_Destino->>>->>" + edEDI_Remessa.getOID_Cidade_Dest());
                  if(edEDI_Remessa.getOID_Pessoa_Entregadora() != null && edEDI_Remessa.getOID_Pessoa_Entregadora().length() > 4)
                      conED.setOID_Pessoa_Entregadora(edEDI_Remessa.getOID_Pessoa_Entregadora());
                  else
                  if(nf.getOID_Pessoa_Entregadora() != null && nf.getOID_Pessoa_Entregadora().length() > 4)
                      conED.setOID_Pessoa_Entregadora(nf.getOID_Pessoa_Entregadora());
                  else
                      conED.setOID_Pessoa_Entregadora(localiza_Entregador(conED.getOID_Cidade_Destino(), conED.getOID_Modal()));
                  if("REGPEL".equals(edEDI_Remessa.getDM_Origem_Remessa()))
                  {
                      conED.setOID_Pessoa_Pagador("05127438000230");
                      conED.setOID_Pessoa_Consignatario("05127438000230");
                      conED.setDM_Tipo_Pagamento("3");
                      conED.setOID_Cidade(oid_Cidade_Origem);
                      conED.setTX_Observacao("Cod.Mat.: " + edEDI_Remessa.getCD_Produto() + "  Cont.Cliente:" + edEDI_Remessa.getNR_Pedido());
                      conED.setNM_Natureza("Arq:" + nr_lote + " " + ed.getNM_Natureza());
                  }
                  if("GETNET".equals(edEDI_Remessa.getDM_Origem_Remessa()))
                  {
                      conED.setOID_Pessoa_Pagador(ed.getOID_Pessoa());
                      conED.setDM_Tipo_Pagamento("1");
                      conED.setOID_Cidade(edEDI_Remessa.getOID_Cidade());
                      conED.setTX_Observacao("Cod.Mat.: " + edEDI_Remessa.getCD_Produto() + "  Cont.Cliente:" + edEDI_Remessa.getNR_Pedido());
                      conED.setNM_Natureza("Arq:" + nr_lote + " " + ed.getNM_Natureza());
                  }
                  if("NOTFISLUPA".equals(edEDI_Remessa.getDM_Origem_Remessa()))
                  {
                      conED.setDM_Tipo_Conhecimento("R");
                      conED.setOID_Pessoa_Pagador(edEDI_Remessa.getOID_Pessoa_Pagador());
                      conED.setDM_Tipo_Pagamento("1");
                      if(ed.getOID_Pessoa_Pagador() != ed.getOID_Pessoa())
                          conED.setDM_Tipo_Pagamento("2");
                      conED.setOID_Cidade(edEDI_Remessa.getOID_Cidade());
                      conED.setTX_Observacao(" ");
                      conED.setNM_Natureza(" ");
                      if("02457005000254".equals(conED.getOID_Pessoa_Entregadora()))
                          conED.setOID_Modal(56L);
                      if("02457005000416".equals(conED.getOID_Pessoa_Entregadora()))
                          conED.setOID_Modal(56L);
                      System.out.println("getOID_Pessoa_Entregadora-->>>>>" + conED.getOID_Pessoa_Entregadora());
                      System.out.println("getOID_Modal-->>>>>" + conED.getOID_Modal());
                  }
                  conED.setOID_Coleta(0L);
                  conED.setOID_Veiculo("");
                  conED.setOID_Pessoa(oid_Pessoa);
                  conED.setOID_Pessoa_Destinatario(oid_Pessoa_Destinatario);
                  conED.setOID_Pessoa_Redespacho("");
                  conED.setOID_Nota_Fiscal(OID_Nota_Fiscal);
                  conED.setNM_Especie("");
                  conED.setOID_Tabela_Frete("");
                  conED.setOID_Taxa(1L);
                  conED.setVL_FRETE_PESO(0.0D);
                  conED.setVL_FRETE_VALOR(0.0D);
                  conED.setVL_SEC_CAT(0.0D);
                  conED.setVL_PEDAGIO(0.0D);
                  conED.setVL_DESPACHO(0.0D);
                  conED.setVL_OUTROS1(0.0D);
                  conED.setVL_OUTROS2(0.0D);
                  conED.setVL_TOTAL_FRETE(0.0D);
                  conED.setVL_BASE_CALCULO_ICMS(0.0D);
                  conED.setVL_ICMS(0.0D);
                  System.out.println(" inclui 9999aaa ");
                  conED = (new ConhecimentoBD(executasql)).inclui(conED);
                  if("NOTFISLUPA".equals(edEDI_Remessa.getDM_Origem_Remessa()) && conED.getOID_Conhecimento() != null && conED.getOID_Conhecimento().length() > 4)
                  {
                      sql = " UPDATE Conhecimentos SET DM_Impresso='S', VL_Total_Frete=0.01 WHERE oid_Conhecimento ='" + conED.getOID_Conhecimento() + "'";
                      System.out.println("sql" + sql);
                      executasql.executarUpdate(sql);
                  }
                  if("DAUDT".equals(edEDI_Remessa.getDM_Origem_Remessa()) && conED.getOID_Conhecimento() != null && conED.getOID_Conhecimento().length() > 4)
                  {
                      sql = " UPDATE Conhecimentos SET DM_Impresso='S', VL_Total_Frete=20.0 WHERE oid_Conhecimento ='" + conED.getOID_Conhecimento() + "'";
                      System.out.println("sql" + sql);
                      executasql.executarUpdate(sql);
                  }
                  System.out.println(" inclui ok");
                  System.out.println(" CALC ok");
                  list.add(edEDI_Remessa);
                  sql = "UPDATE EDI_Remessas set oid_Nota_Fiscal = '" + OID_Nota_Fiscal + "', oid_Conhecimento = '" + conED.getOID_Conhecimento() + "' WHERE oid_EDI_Remessa='" + edEDI_Remessa.getOID_EDI_Remessa() + "'";
                  System.out.println("sql" + sql);
                  executasql.executarUpdate(sql);
              }
          }
      }

  }
  catch(Exception exc)
  {
      Excecoes excecoes = new Excecoes();
      excecoes.setMetodo("gera_Remessa");
      excecoes.setExc(exc);
      OID_Nota_Fiscal = null;
      throw excecoes;
  }
  return list;
}

  private String inclui_Remetente (EDI_RemessaED ed) throws Excecoes {

    System.out.println ("inclui_Remetente");

    String sql = "" , oid_Pessoa = "";
    ResultSet resP = null;
    boolean pessoa_ok = false;
    try {

      sql = " SELECT oid_Pessoa " +
          "  FROM  Pessoas " +
          "  WHERE Pessoas.NR_CNPJ_CPF ='" + ed.getNR_CNPJ_CPF ().trim() + "'";

          System.out.println ("sql 1->>" + sql);

      resP = this.executasql.executarConsulta (sql);
      System.out.println ("2");

      if (resP.next ()) {
        oid_Pessoa = resP.getString ("oid_Pessoa");
        
        sql = " UPDATE Pessoas SET "+
        	" NM_razao_social = '" + ed.getNM_Pessoa_Remetente ().trim () + "', " +
        	" NM_endereco = '" + ed.getNM_Endereco ().trim () + "', " +
        	" NR_Cep = '" + ed.getNR_Cep () + "' "+
        	" WHERE OID_Pessoa='" + oid_Pessoa+ "'";
        	
        	System.out.println(sql);
        
        	executasql.executarUpdate (sql);
      
      }else{
          System.out.println ("3");
        sql = " SELECT oid_Pessoa " +
            "  FROM  Pessoas " +
            "  WHERE Pessoas.NM_Razao_Social ='" + ed.getNM_Pessoa_Remetente ().trim () + "'" +
            "  AND   Pessoas.NM_Endereco = '" + ed.getNM_Endereco ().trim () + "'";
          System.out.println ("sql 2->>" + sql);

        resP = this.executasql.executarConsulta (sql);
        if (resP.next ()) {
          oid_Pessoa = resP.getString ("oid_Pessoa");
        }
      }  
      System.out.println ("6");

      if ("".equals(oid_Pessoa)){  
          PessoaED pessoaED = new PessoaED ();

          System.out.println ("getNR_CNPJ_CPF->>" + ed.getNR_CNPJ_CPF ());

          if (ed.getNR_CNPJ_CPF_Destinatario ()!=null && ed.getNR_CNPJ_CPF ().length()>4){
            pessoaED.setNR_CNPJ_CPF (ed.getNR_CNPJ_CPF ().trim());
          }else{
            pessoaED.setNR_CNPJ_CPF (gera_CNPJ (""));
          }

          System.out.println ("inclui_Destinatario ok->>" + ed.getNM_Pessoa_Remetente ());

          pessoaED.setOid_Pessoa (pessoaED.getNR_CNPJ_CPF ());
          pessoaED.setNM_Razao_Social (ed.getNM_Pessoa_Remetente ().trim ());
          pessoaED.setNM_Endereco (ed.getNM_Endereco ().trim ());
          pessoaED.setNM_Bairro (ed.getNM_Bairro());
          pessoaED.setOid_Cidade (ed.getOID_Cidade ());

          if (pessoaED.getOid_Cidade () == 0) {
            System.out.println ("cidade antes->" + ed.getNM_Cidade () + " UF-> " + ed.getCD_Estado ());

            cidadeBean = CidadeBean.getByCidade (ed.getNM_Cidade () , ed.getCD_Estado ());

            System.out.println ("cidade dpos->" + cidadeBean.getOID ());

            pessoaED.setOid_Cidade (cidadeBean.getOID ());
          }
          pessoaED.setNR_CEP (ed.getNR_Cep ());

          System.out.println ("9999");

          pessoa_ok = new PessoaBD (this.executasql).inclui (pessoaED);

          System.out.println ("pessoa ok");

          if (pessoa_ok) {
            oid_Pessoa = pessoaED.getOid_Pessoa ();
          }

          System.out.println ("pessoa ok->" + oid_Pessoa);

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

  private String inclui_Destinatario (EDI_RemessaED ed) throws Excecoes {

	    System.out.println ("inclui_Destinatario");

	    String sql = "" , oid_Pessoa = "";
	    ResultSet resP = null;
	    boolean pessoa_ok = false;
	    try {

	      sql = " SELECT oid_Pessoa " +
	          "  FROM  Pessoas " +
	          "  WHERE Pessoas.NR_CNPJ_CPF ='" + ed.getNR_CNPJ_CPF_Destinatario ().trim() + "'";

	          System.out.println ("sql 1->>" + sql);

	      resP = this.executasql.executarConsulta (sql);
	      System.out.println ("2");

          System.out.println ("getNR_Cep_Dest="+ed.getNR_Cep_Dest ());

	      if (resP.next ()) {
	        oid_Pessoa = resP.getString ("oid_Pessoa");
	        
	        sql = " UPDATE Pessoas SET "+
	        	" NM_razao_social = '" + ed.getNM_Pessoa_Destinatario ().trim() + "', " +
	        	" NM_endereco = '" + ed.getNM_Endereco_Dest ().trim() + "', " +
	        	" NR_Cep = '" + ed.getNR_Cep_Dest () + "' "+
	        	" WHERE OID_Pessoa='" + oid_Pessoa+ "'";
	        	
	        	 System.out.println(sql);
	        
	        	executasql.executarUpdate (sql);
	      
	      }else{
	          System.out.println ("3");
	        sql = " SELECT oid_Pessoa " +
	            "  FROM  Pessoas " +
	            "  WHERE Pessoas.NM_Razao_Social ='" + ed.getNM_Pessoa_Destinatario ().trim() + "'" +
	            "  AND   Pessoas.NM_Endereco = '" + ed.getNM_Endereco_Dest ().trim() + "'";
	          System.out.println ("sql 2->>" + sql);

	        resP = this.executasql.executarConsulta (sql);
	        if (resP.next ()) {
	          oid_Pessoa = resP.getString ("oid_Pessoa");
	        }
	      }  
	      System.out.println ("6");

	      if ("".equals(oid_Pessoa)){  
	          PessoaED pessoaED = new PessoaED ();

	          System.out.println ("incluigetNR_CNPJ_CPF_Destinatario->>" + ed.getNR_CNPJ_CPF_Destinatario ());

	          if (ed.getNR_CNPJ_CPF_Destinatario ()!=null && ed.getNR_CNPJ_CPF_Destinatario ().length()>4){
	            pessoaED.setNR_CNPJ_CPF (ed.getNR_CNPJ_CPF_Destinatario ().trim());
	          }else{
	            pessoaED.setNR_CNPJ_CPF (gera_CNPJ (""));
	          }

	          System.out.println ("inclui_Destinatario ok->>" + ed.getNM_Pessoa_Destinatario ());

	          pessoaED.setOid_Pessoa (pessoaED.getNR_CNPJ_CPF ());
	          pessoaED.setNM_Razao_Social (ed.getNM_Pessoa_Destinatario ().trim ());
	          pessoaED.setNM_Endereco (ed.getNM_Endereco_Dest ().trim ());
	          pessoaED.setNM_Bairro (ed.getNM_Bairro_Dest ());
	          pessoaED.setOid_Cidade (ed.getOID_Cidade_Dest ());

	          if (pessoaED.getOid_Cidade () == 0) {
	            System.out.println ("cidade antes->" + ed.getNM_Cidade_Dest () + " UF-> " + ed.getCD_Estado_Dest ());

	            cidadeBean = CidadeBean.getByCidade (ed.getNM_Cidade_Dest () , ed.getCD_Estado_Dest ());

	            System.out.println ("cidade dpos->" + cidadeBean.getOID ());

	            pessoaED.setOid_Cidade (cidadeBean.getOID ());
	          }
	          pessoaED.setNR_CEP (ed.getNR_Cep_Dest ());

	          System.out.println ("getNR_Cep_Dest="+ed.getNR_Cep_Dest ());

	          pessoa_ok = new PessoaBD (this.executasql).inclui (pessoaED);

	          System.out.println ("pessoa ok");

	          if (pessoa_ok) {
	            oid_Pessoa = pessoaED.getOid_Pessoa ();
	          }

	          System.out.println ("pessoa ok->" + oid_Pessoa);

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
  
  public EDI_RemessaED carregaED(ResultSet res)
  throws Excecoes
{
  EDI_RemessaED edVolta = new EDI_RemessaED();
  try
  {
      String sql = "";
      ResultSet resP = null;
      FormataDataBean DataFormatada = new FormataDataBean();
      edVolta.setNR_Peso(res.getDouble("NR_Peso"));
      edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
      edVolta.setNR_Volumes(res.getDouble("NR_Volumes"));
      edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
      edVolta.setNR_Remessa(res.getString("NR_Remessa"));
      edVolta.setOID_EDI_Remessa(res.getString("OID_EDI_Remessa"));
      edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
      edVolta.setOID_Pessoa_Pagador(res.getString("OID_Pessoa_Pagador"));
      edVolta.setOID_Pessoa_Entregadora(res.getString("OID_Pessoa_Entregadora"));
      edVolta.setCD_Produto(res.getString("CD_Produto"));
      edVolta.setNR_Lote(res.getString("NR_Lote"));
      edVolta.setNR_Pedido(res.getString("NR_Pedido"));
      edVolta.setDT_Remessa(res.getString("DT_Remessa"));
      DataFormatada.setDT_FormataData(edVolta.getDT_Remessa());
      edVolta.setDT_Remessa(DataFormatada.getDT_FormataData());
      edVolta.setDM_Situacao(res.getString("DM_Situacao"));
      edVolta.setDM_Origem_Remessa(res.getString("DM_Origem_Remessa"));
      edVolta.setDT_Entrada(res.getString("DT_Entrada"));
      DataFormatada.setDT_FormataData(edVolta.getDT_Entrada());
      edVolta.setDT_Entrada(DataFormatada.getDT_FormataData());
      edVolta.setNM_Pessoa_Remetente(" ");
      edVolta.setNM_Pessoa_Destinatario(" ");
      edVolta.setNM_Endereco(" ");
      edVolta.setNM_Endereco_Dest(" ");
      edVolta.setNM_Endereco(" ");
      edVolta.setNM_Bairro(" ");
      edVolta.setNM_Cidade(" ");
      edVolta.setNR_CNPJ_CPF(res.getString("NR_CNPJ_CPF"));
      edVolta.setNR_CNPJ_CPF_Destinatario(res.getString("NR_CNPJ_CPF_Destinatario"));
      if(res.getString("NM_Destinatario") != null && res.getString("NM_Destinatario").length() > 4)
          edVolta.setNM_Pessoa_Destinatario((res.getString("NM_Destinatario") + "                                                                                                            ").substring(0, 100));
      if(res.getString("NM_Endereco") != null && res.getString("NM_Endereco").length() > 4)
          edVolta.setNM_Endereco((res.getString("NM_Endereco") + "                                                                                                             ").substring(0, 100));
      if(res.getString("NM_Endereco_Dest") != null && res.getString("NM_Endereco_Dest").length() > 4)
          edVolta.setNM_Endereco_Dest((res.getString("NM_Endereco_Dest") + "                                                                                                             ").substring(0, 100));
      if(res.getString("NM_Cidade") != null && res.getString("NM_Cidade").length() > 4)
          edVolta.setNM_Cidade("*" + (res.getString("NM_Cidade") + "                                 ").substring(0, 30) + "/" + res.getString("CD_Estado"));
      if(res.getString("NM_Cidade_Dest") != null && res.getString("NM_Cidade_Dest").length() > 4)
          edVolta.setNM_Cidade_Dest("*" + (res.getString("NM_Cidade_Dest") + "                                 ").substring(0, 30) + "/" + res.getString("CD_Estado"));
      edVolta.setNR_Conhecimento(" ");
      edVolta.setNR_Cep_Dest(res.getString("NR_Cep_Dest"));
      
      sql = " SELECT Cidades.oid_Cidade, Cidades.NM_Cidade, Estados.CD_Estado   FROM  Cidades, Regioes_Estados, Estados  WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado   AND   Regioes_Estados.OID_Estado = Estados.OID_Estado   AND   Cidades.CD_Logradouro ='" + edVolta.getNR_Cep() + "'";
      System.out.println(sql);
      resP = executasql.executarConsulta(sql);
      if(resP.next())
      {
          System.out.println("Rem OK");
          edVolta.setOID_Cidade(resP.getLong("oid_Cidade"));
          edVolta.setNM_Cidade((resP.getString("NM_Cidade") + "                   ").substring(0, 12) + "/" + resP.getString("CD_Estado"));
      } else
      {
          System.out.println("Rem NAO OK");
          sql = " SELECT Cidades.oid_Cidade, Cidades.NM_Cidade, Estados.CD_Estado   FROM  Cidades, Regioes_Estados, Estados  WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado   AND   Regioes_Estados.OID_Estado = Estados.OID_Estado   AND   Cidades.NM_Cidade ='" + res.getString("NM_Cidade") + "'" + 
          	    "  AND   Estados.CD_Estado ='" + res.getString("CD_Estado") + "'";
          System.out.println(sql);
          resP = executasql.executarConsulta(sql);
          if(resP.next())
          {
              edVolta.setOID_Cidade(resP.getLong("oid_Cidade"));
              edVolta.setNM_Cidade((resP.getString("NM_Cidade") + "                   ").substring(0, 12) + "/" + resP.getString("CD_Estado"));
              System.out.println("Rem Cidade OK");
          }
      }
      sql = " SELECT Cidades.oid_Cidade, Cidades.NM_Cidade, Estados.CD_Estado   FROM  Cidades, Regioes_Estados, Estados  WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado   AND   Regioes_Estados.OID_Estado = Estados.OID_Estado   AND   Cidades.CD_Logradouro ='" + edVolta.getNR_Cep_Dest() + "'";
      System.out.println(sql);
      resP = executasql.executarConsulta(sql);
      if(resP.next())
      {
          System.out.println("Dest OK");
          edVolta.setOID_Cidade_Dest(resP.getLong("oid_Cidade"));
          edVolta.setNM_Cidade_Dest((resP.getString("NM_Cidade") + "                   ").substring(0, 12) + "/" + resP.getString("CD_Estado"));
      } else
      {
          System.out.println("Dest NAO OK");
          sql = " SELECT Cidades.oid_Cidade, Cidades.NM_Cidade, Estados.CD_Estado   FROM  Cidades, Regioes_Estados, Estados  WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado   AND   Regioes_Estados.OID_Estado = Estados.OID_Estado   AND   Cidades.NM_Cidade ='" + res.getString("NM_Cidade_Dest") + "'" +
          	   "  AND   Estados.CD_Estado ='" + res.getString("CD_Estado_Dest") + "'";
          System.out.println(sql);
          resP = executasql.executarConsulta(sql);
          if(resP.next())
          {
              edVolta.setOID_Cidade_Dest(resP.getLong("oid_Cidade"));
              edVolta.setNM_Cidade_Dest((resP.getString("NM_Cidade") + "                   ").substring(0, 12) + "/" + resP.getString("CD_Estado"));
              System.out.println("Dest Cidade OK");
          }
      }
      if(res.getString("oid_Pessoa") != null && res.getString("oid_Pessoa").length() > 4)
      {
          sql = "SELECT NM_Razao_Social FROM Pessoas WHERE oid_Pessoa='" + res.getString("oid_Pessoa") + "'";
          resP = executasql.executarConsulta(sql);
          if(resP.next())
              edVolta.setNM_Pessoa_Remetente(resP.getString("NM_Razao_Social"));
      }
      if(res.getString("oid_Conhecimento") != null && res.getString("oid_Conhecimento").length() > 4)
      {
          sql = "SELECT oid_Conhecimento, NR_Conhecimento, VL_Total_Frete, DM_Impresso FROM Conhecimentos WHERE oid_Conhecimento='" + res.getString("oid_Conhecimento") + "'";
          resP = executasql.executarConsulta(sql);
          if(resP.next())
          {
              edVolta.setOID_Conhecimento(resP.getString("oid_Conhecimento"));
              edVolta.setNR_Conhecimento(resP.getString("NR_Conhecimento"));
              edVolta.setVL_Total_Frete(resP.getDouble("VL_Total_Frete"));
              System.out.println("Remessa c/cto=>" + resP.getString("oid_Conhecimento"));
              if("---".equals(resP.getString("DM_Impresso")))
              {
                  ConhecimentoED conED = new ConhecimentoED();
                  conED.setOID_Conhecimento(resP.getString("oid_Conhecimento"));
                  conED = (new ConhecimentoBD(executasql)).getByRecord(conED);
                  edVolta.setVL_Total_Frete(conED.getVL_TOTAL_FRETE());
              }
          }
      }
  }
  catch(SQLException e)
  {
      throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(EDI_RemessaED ed)");
  }
  return edVolta;
}


  public String gera_CNPJ (String oid) throws Exception {
    int oid_Unidade = Parametro_FixoED.getInstancia ().getOID_Unidade_Padrao ();

        System.out.println ("gera_CNPJ ");

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
