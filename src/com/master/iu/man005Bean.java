package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.master.ed.Ordem_Frete_TerceiroED;
import com.master.ed.UsuarioED;
import com.master.rn.Ordem_Frete_TerceiroRN;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;


public class man005Bean {

  public Ordem_Frete_TerceiroED inclui (HttpServletRequest request) throws
      Excecoes {

    try {
      Ordem_Frete_TerceiroRN Ordem_Frete_Terceirorn = new
          Ordem_Frete_TerceiroRN ();
      Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

      //request em cima dos campos dos forms html
      //// System.out.println(" a" );
      ed.setDM_Frete ("T");
      if(JavaUtil.doValida(request.getParameter("FT_DM_Frete"))){
        ed.setDM_Frete (request.getParameter ("FT_DM_Frete"));
      }

      ed.setDT_Emissao (Data.getDataDMY ());
      if(JavaUtil.doValida(request.getParameter("FT_DT_Emissao"))){
        ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
      }


      //// System.out.println(" emissao " + ed.getDT_Emissao());

      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      // System.out.println(" b" );

      String oid_Usuario = request.getParameter ("oid_Usuario");
      if (JavaUtil.doValida (oid_Usuario)) {
         ed.setOID_Usuario (new Long (request.getParameter ("oid_Usuario")).longValue ());
      }else {

	       HttpSession session = request.getSession(true);
	
	       if (session != null && session.getAttribute("usuario") != null 
	    		   && (session.getAttribute("usuario") instanceof UsuarioED)) {
	          UsuarioED usuario = (UsuarioED) session.getAttribute("usuario");
	          ed.setOID_Usuario (new Long (String.valueOf(usuario.getOid_Usuario())).longValue ());
         }
      }

      // System.out.println(" c" );

      if(request.getParameter("FT_NR_Ordem_Frete_Terceiro") !=null && !request.getParameter("FT_NR_Ordem_Frete_Terceiro").equals("") && !request.getParameter("FT_NR_Ordem_Frete_Terceiro").equals("null")){
        ed.setNR_Ordem_Frete_Terceiro (new Long (request.getParameter ("FT_NR_Ordem_Frete_Terceiro")).longValue ());
      }

      ed.setNM_Serie ("900");
      // System.out.println(" c" );

      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
      ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
      //// System.out.println(" c" );
      ed.setOID_Motorista (request.getParameter ("oid_Motorista"));

      ed.setNM_Destino (request.getParameter ("FT_NM_Destino"));
      ed.setNM_Origem (request.getParameter ("FT_NM_Origem"));

      ed.setOID_Fornecedor (request.getParameter ("oid_Fornecedor"));
      // System.out.println(" a" );

      String VL_Adiantamento = request.getParameter ("FT_VL_Adiantamento");
      if (String.valueOf (VL_Adiantamento) != null &&
          !String.valueOf (VL_Adiantamento).equals ("null")) {
        ed.setVL_Adiantamento (new Double (VL_Adiantamento).doubleValue ());
      }

      String VL_Cheque = request.getParameter ("FT_VL_Cheque");
      if (String.valueOf (VL_Cheque) != null &&
          !String.valueOf (VL_Cheque).equals ("null")) {
        ed.setVL_Cheque (new Double (VL_Cheque).doubleValue ());
      }

      ed.setDT_Coleta (Data.getDataDMY ());
      if(JavaUtil.doValida(request.getParameter("FT_DT_Coleta"))){
        ed.setDT_Coleta (request.getParameter ("FT_DT_Coleta"));
      }

      ed.setDT_Entrega (Data.getDataDMY ());
      if(JavaUtil.doValida(request.getParameter("FT_DT_Entrega"))){
        ed.setDT_Entrega (request.getParameter ("FT_DT_Entrega"));
      }

      ed.setNM_Produto (" ");
      if(JavaUtil.doValida(request.getParameter("FT_NM_Produto"))){
        ed.setNM_Produto (request.getParameter ("FT_NM_Produto"));
      }

      String VL_Agenciamento = request.getParameter ("FT_VL_Agenciamento");
      if (String.valueOf (VL_Agenciamento) != null &&
          !String.valueOf (VL_Agenciamento).equals ("null")) {
        ed.setVL_Agenciamento (new Double (VL_Agenciamento).doubleValue ());
      }
      String VL_Carga = request.getParameter ("FT_VL_Carga");
      if (String.valueOf (VL_Carga) != null &&
          !String.valueOf (VL_Carga).equals ("null")) {
        ed.setVL_Carga (new Double (VL_Carga).doubleValue ());
      }
      String VL_Descarga = request.getParameter ("FT_VL_Descarga");
      if (String.valueOf (VL_Descarga) != null &&
          !String.valueOf (VL_Descarga).equals ("null")) {
        ed.setVL_Descarga (new Double (VL_Descarga).doubleValue ());
      }


      // System.out.println(" b" );
      String VL_Especie = request.getParameter ("FT_VL_Especie");
      if (String.valueOf (VL_Especie) != null &&
          !String.valueOf (VL_Especie).equals ("null")) {
        ed.setVL_Especie (new Double (VL_Especie).doubleValue ());
      }
      String VL_Devolvido = request.getParameter ("FT_VL_Devolvido");
      if (String.valueOf (VL_Devolvido) != null &&
          !String.valueOf (VL_Devolvido).equals ("null")) {
        ed.setVL_Devolvido (new Double (VL_Devolvido).doubleValue ());
      }
      // System.out.println(" c" );
      String VL_Saldo = request.getParameter ("FT_VL_Saldo");
      if (String.valueOf (VL_Saldo) != null &&
          !String.valueOf (VL_Saldo).equals ("null")) {
        ed.setVL_Saldo (new Double (VL_Saldo).doubleValue ());
      }

      String VL_Despesas = request.getParameter ("FT_VL_Despesas");
      if (String.valueOf (VL_Despesas) != null &&
          !String.valueOf (VL_Despesas).equals ("null")) {
        ed.setVL_Despesas (new Double (VL_Despesas).doubleValue ());
      }

      String VL_Frete_Devolvido = request.getParameter ("FT_VL_Frete_Devolvido");
      if (String.valueOf (VL_Frete_Devolvido) != null &&
          !String.valueOf (VL_Frete_Devolvido).equals ("null")) {
        ed.setVL_Frete_Devolvido (new Double (VL_Frete_Devolvido).doubleValue ());
      }
      String VL_Deposito = request.getParameter ("FT_VL_Deposito");
      if (String.valueOf (VL_Deposito) != null &&
          !String.valueOf (VL_Deposito).equals ("null")) {
        ed.setVL_Deposito (new Double (VL_Deposito).doubleValue ());
      }

      String VL_Ordem_Frete_Terceiro = request.getParameter ("FT_VL_Ordem_Frete_Terceiro");
      if (String.valueOf (VL_Ordem_Frete_Terceiro) != null &&
          !String.valueOf (VL_Ordem_Frete_Terceiro).equals ("null")) {
        ed.setVL_Ordem_Frete_Terceiro (new Double (VL_Ordem_Frete_Terceiro).
                                       doubleValue ());
      }

      return Ordem_Frete_Terceirorn.inclui (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_Frete_TerceiroRN Ordem_Frete_Terceirorn = new  Ordem_Frete_TerceiroRN ();

      Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

      ed.setOID_Ordem_Frete_Terceiro (request.getParameter ("oid_Ordem_Frete_Terceiro"));

      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
      ed.setNM_Destino (request.getParameter ("FT_NM_Destino"));
      ed.setNM_Origem (request.getParameter ("FT_NM_Origem"));

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
      ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
      ed.setOID_Fornecedor (request.getParameter ("oid_Fornecedor"));
      ed.setOID_Motorista (request.getParameter ("oid_Motorista"));

      if(request.getParameter("FT_NR_Ordem_Frete_Terceiro") !=null && !request.getParameter("FT_NR_Ordem_Frete_Terceiro").equals("") && !request.getParameter("FT_NR_Ordem_Frete_Terceiro").equals("null")){
        ed.setNR_Ordem_Frete_Terceiro (new Long (request.getParameter ("FT_NR_Ordem_Frete_Terceiro")).longValue ());
      }

      String VL_Adiantamento = request.getParameter ("FT_VL_Adiantamento");
      if (String.valueOf (VL_Adiantamento) != null &&
          !String.valueOf (VL_Adiantamento).equals ("null")) {
        ed.setVL_Adiantamento (new Double (VL_Adiantamento).doubleValue ());
      }
      // System.out.println(" a1" );

      String VL_Cheque = request.getParameter ("FT_VL_Cheque");
      if (String.valueOf (VL_Cheque) != null &&
          !String.valueOf (VL_Cheque).equals ("null")) {
        ed.setVL_Cheque (new Double (VL_Cheque).doubleValue ());
      }

      String VL_Especie = request.getParameter ("FT_VL_Especie");
      if (String.valueOf (VL_Especie) != null &&
          !String.valueOf (VL_Especie).equals ("null")) {
        ed.setVL_Especie (new Double (VL_Especie).doubleValue ());
      }

      // System.out.println(" a2" );

      String VL_Devolvido = request.getParameter ("FT_VL_Devolvido");
      if (String.valueOf (VL_Devolvido) != null &&
          !String.valueOf (VL_Devolvido).equals ("null")) {
        ed.setVL_Devolvido (new Double (VL_Devolvido).doubleValue ());
      }
      String VL_Saldo = request.getParameter ("FT_VL_Saldo");
      if (String.valueOf (VL_Saldo) != null &&
          !String.valueOf (VL_Saldo).equals ("null")) {
        ed.setVL_Saldo (new Double (VL_Saldo).doubleValue ());
      }

      // System.out.println(" a3" );

      String VL_Ordem_Frete_Terceiro = request.getParameter ("FT_VL_Ordem_Frete_Terceiro");
      if (String.valueOf (VL_Ordem_Frete_Terceiro) != null &&
          !String.valueOf (VL_Ordem_Frete_Terceiro).equals ("null")) {
        ed.setVL_Ordem_Frete_Terceiro (new Double (VL_Ordem_Frete_Terceiro).doubleValue ());
      }

      String VL_Despesas = request.getParameter ("FT_VL_Despesas");
      if (String.valueOf (VL_Despesas) != null &&
          !String.valueOf (VL_Despesas).equals ("null")) {
        ed.setVL_Despesas (new Double (VL_Despesas).doubleValue ());
      }

      // System.out.println(" a4" );

      String VL_Frete_Devolvido = request.getParameter ("FT_VL_Frete_Devolvido");
      if (String.valueOf (VL_Frete_Devolvido) != null &&
          !String.valueOf (VL_Frete_Devolvido).equals ("null")) {
        ed.setVL_Frete_Devolvido (new Double (VL_Frete_Devolvido).doubleValue ());
      }

      String VL_Deposito = request.getParameter ("FT_VL_Deposito");
      if (String.valueOf (VL_Deposito) != null &&
          !String.valueOf (VL_Deposito).equals ("null")) {
        ed.setVL_Deposito (new Double (VL_Deposito).doubleValue ());
      }

      ed.setDT_Coleta (Data.getDataDMY ());
      if(JavaUtil.doValida(request.getParameter("FT_DT_Coleta"))){
        ed.setDT_Coleta (request.getParameter ("FT_DT_Coleta"));
      }

      // System.out.println(" a5" );

      ed.setDT_Entrega (Data.getDataDMY ());
      if(JavaUtil.doValida(request.getParameter("FT_DT_Entrega"))){
        ed.setDT_Entrega (request.getParameter ("FT_DT_Entrega"));
      }
  
      ed.setNM_Produto (" ");
      if(JavaUtil.doValida(request.getParameter("FT_NM_Produto"))){
        ed.setNM_Produto (request.getParameter ("FT_NM_Produto"));
      }

      String VL_Agenciamento = request.getParameter ("FT_VL_Agenciamento");
      if (String.valueOf (VL_Agenciamento) != null &&
          !String.valueOf (VL_Agenciamento).equals ("null")) {
        ed.setVL_Agenciamento (new Double (VL_Agenciamento).doubleValue ());
      }

      // System.out.println(" a6" );

      String VL_Carga = request.getParameter ("FT_VL_Carga");
      if (String.valueOf (VL_Carga) != null &&
          !String.valueOf (VL_Carga).equals ("null")) {
        ed.setVL_Carga (new Double (VL_Carga).doubleValue ());
      }

      String VL_Descarga = request.getParameter ("FT_VL_Descarga");
      if (String.valueOf (VL_Descarga) != null &&
          !String.valueOf (VL_Descarga).equals ("null")) {
        ed.setVL_Descarga (new Double (VL_Descarga).doubleValue ());
      }

      // System.out.println(" a7" );

      Ordem_Frete_Terceirorn.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("altera(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_Frete_TerceiroRN Ordem_Frete_Terceirorn = new
          Ordem_Frete_TerceiroRN ();
      Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

      ed.setOID_Ordem_Frete_Terceiro (request.getParameter (
          "oid_Ordem_Frete_Terceiro"));

      Ordem_Frete_Terceirorn.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("deleta(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void cancela (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_Frete_TerceiroRN Ordem_Frete_Terceirorn = new
          Ordem_Frete_TerceiroRN ();
      Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

      ed.setOID_Ordem_Frete_Terceiro (request.getParameter (
          "oid_Ordem_Frete_Terceiro"));

      Ordem_Frete_Terceirorn.cancela (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("deleta(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList Ordem_Frete_Terceiro_Lista (HttpServletRequest request) throws
      Excecoes {

    Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    ed.setDM_Frete ("T");

    String nr_Ordem_Frete_Terceiro = request.getParameter (
        "FT_NR_Ordem_Frete_Terceiro");
    if (String.valueOf (nr_Ordem_Frete_Terceiro) != null &&
        !String.valueOf (nr_Ordem_Frete_Terceiro).equals ("") &&
        !String.valueOf (nr_Ordem_Frete_Terceiro).equals ("null")) {
      ed.setNR_Ordem_Frete_Terceiro (new Long (nr_Ordem_Frete_Terceiro).
                                     longValue ());
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String OID_Veiculo = request.getParameter ("FT_NR_Placa");
    if (String.valueOf (OID_Veiculo) != null &&
        !String.valueOf (OID_Veiculo).equals ("") &&
        !String.valueOf (OID_Veiculo).equals ("null")) {
      ed.setOID_Veiculo (OID_Veiculo);
    }

    String NR_Recibo = request.getParameter ("FT_NR_Recibo");
    if (String.valueOf (NR_Recibo) != null &&
        !String.valueOf (NR_Recibo).equals ("") &&
        !String.valueOf (NR_Recibo).equals ("null")) {
      ed.setNR_Recibo (new Long (NR_Recibo).longValue ());
    }

    // System.out.println ("---4");

    return new Ordem_Frete_TerceiroRN ().lista (ed);

  }

  public ArrayList Ordem_Frete_Terceiro_Lista_Programacao (HttpServletRequest request) throws
      Excecoes {

    Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    ed.setDM_Frete ("S");

    String NR_Programacao = request.getParameter ("FT_NR_Programacao");
    if (String.valueOf (NR_Programacao) != null &&
        !String.valueOf (NR_Programacao).equals ("") &&
        !String.valueOf (NR_Programacao).equals ("null")) {
      ed.setNR_Programacao (new Long (NR_Programacao).longValue ());
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String OID_Veiculo = request.getParameter ("FT_NR_Placa");
    if (String.valueOf (OID_Veiculo) != null &&
        !String.valueOf (OID_Veiculo).equals ("") &&
        !String.valueOf (OID_Veiculo).equals ("null")) {
      ed.setOID_Veiculo (OID_Veiculo);
    }

    String NR_Recibo = request.getParameter ("FT_NR_Recibo");
    if (String.valueOf (NR_Recibo) != null &&
        !String.valueOf (NR_Recibo).equals ("") &&
        !String.valueOf (NR_Recibo).equals ("null")) {
      ed.setNR_Recibo (new Long (NR_Recibo).longValue ());
    }

    // System.out.println ("---4");

    return new Ordem_Frete_TerceiroRN ().lista (ed);

  }

  public Ordem_Frete_TerceiroED getByRecord (HttpServletRequest request) throws
      Excecoes {

    Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();
  
    String oid_Ordem_Frete_Terceiro = request.getParameter ("oid_Ordem_Frete_Terceiro");
    String NR_Recibo = request.getParameter ("FT_NR_Recibo");
    if (String.valueOf (oid_Ordem_Frete_Terceiro) != null &&
        !String.valueOf (oid_Ordem_Frete_Terceiro).equals ("") &&
        !String.valueOf (oid_Ordem_Frete_Terceiro).equals ("null")) {
        ed.setOID_Ordem_Frete_Terceiro (oid_Ordem_Frete_Terceiro);
    }else {
      if (String.valueOf (NR_Recibo) != null &&
          !String.valueOf (NR_Recibo).equals ("") &&
          !String.valueOf (NR_Recibo).equals ("null")) {
        ed.setNR_Recibo(new Long(NR_Recibo).longValue());
      }      
    }

    return new Ordem_Frete_TerceiroRN ().getByRecord (ed);

  }

  public Ordem_Frete_TerceiroED getByOid(String oid)
  throws Excecoes {
      Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();
      ed.setOID_Ordem_Frete_Terceiro(oid);
      return new Ordem_Frete_TerceiroRN ().getByRecord (ed);
  }

  public byte[] imprime_Programacao (HttpServletRequest request ,
                                HttpServletResponse Response) throws Excecoes {
    Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

    // System.out.println("be1");

    ed.setDM_Frete ("S");


    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete_Terceiro");
    if (String.valueOf (oid_Ordem_Frete) != null &&
        !String.valueOf (oid_Ordem_Frete).equals ("") &&
        !String.valueOf (oid_Ordem_Frete).equals ("null")) {
      ed.setOID_Ordem_Frete_Terceiro (oid_Ordem_Frete);

      // System.out.println("be1-2");
    }


    Ordem_Frete_TerceiroRN ordem_FreteRN = new Ordem_Frete_TerceiroRN ();
    byte[] b = ordem_FreteRN.imprime_Ordem_Frete_Terceiro (ed);

    return b;

  }

  public byte[] imprime_Ordem_Frete_Terceiro (HttpServletRequest request ,
                                HttpServletResponse Response) throws Excecoes {
    Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

    // System.out.println("be1");

    ed.setDM_Frete ("T");


    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete_Terceiro");
    if (String.valueOf (oid_Ordem_Frete) != null &&
        !String.valueOf (oid_Ordem_Frete).equals ("") &&
        !String.valueOf (oid_Ordem_Frete).equals ("null")) {
      ed.setOID_Ordem_Frete_Terceiro (oid_Ordem_Frete);

      // System.out.println("be1-2");
    }


    Ordem_Frete_TerceiroRN ordem_FreteRN = new Ordem_Frete_TerceiroRN ();
    byte[] b = ordem_FreteRN.imprime_Ordem_Frete_Terceiro (ed);

    return b;

  }

public byte[] geraRelOrdemFreteTerceiro (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Ordem_Frete_TerceiroED ed = new Ordem_Frete_TerceiroED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }
    // System.out.println(" PEssoa->> " + request.getParameter ("oid_Pessoa_Pagador"));


    String oid_Motorista = request.getParameter ("oid_Motorista");
    if (String.valueOf (oid_Motorista) != null && !String.valueOf (oid_Motorista).equals ("") && !String.valueOf (oid_Motorista).equals ("null")) {
      ed.setOID_Motorista (oid_Motorista);
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (Dt_Emissao_Final);
    }

    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (String.valueOf (DM_Frete) != null && !String.valueOf (DM_Frete).equals ("")) {
      ed.setDM_Frete (DM_Frete);
    }

    String DM_Acerto = request.getParameter ("FT_DM_Acerto");
    if (String.valueOf (DM_Acerto) != null && !String.valueOf (DM_Acerto).equals ("")) {
      ed.setDM_Acerto (DM_Acerto);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    //// System.out.println("oid_Empresa" +  ed.getOID_Empresa() );


    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
    ed.setOID_Veiculo (request.getParameter ("FT_NR_Placa"));
    //// System.out.println(" c" );
    ed.setOID_Motorista (request.getParameter ("oid_Motorista"));
  
    ed.setNM_Destino (request.getParameter ("FT_NM_Destino"));
    ed.setNM_Origem (request.getParameter ("FT_NM_Origem"));
  
    ed.setOID_Fornecedor (request.getParameter ("oid_Fornecedor"));


    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    Ordem_Frete_TerceiroRN ordem_FreteRN = new Ordem_Frete_TerceiroRN ();
    byte[] b = ordem_FreteRN.geraRelOrdemFreteTerceiro (ed);

    return b;

  }


}
