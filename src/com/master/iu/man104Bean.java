package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Lote_FornecedorED;
import com.master.rn.Lote_FornecedorRN;
import com.master.util.Excecoes;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class man104Bean {

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Lote_FornecedorRN Lote_Fornecedorrn = new Lote_FornecedorRN ();
      Lote_FornecedorED ed = new Lote_FornecedorED ();

      //// System.out.println("man -1" );

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      ed.setNR_Documento_Cobranca (new Long (request.getParameter ("FT_NR_Documento_Cobranca")).longValue ());

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));

      // System.out.println ("man 0");

      ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
      ed.setDT_Vencimento (request.getParameter ("FT_DT_Vencimento"));
      ed.setTX_Observacao ("");
      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (String.valueOf (TX_Observacao) != null &&
          !String.valueOf (TX_Observacao).equals ("null") &&
          !String.valueOf (TX_Observacao).equals ("")) {
        ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
      }

      String VL_Cobranca = request.getParameter ("FT_VL_Cobranca");
      if (String.valueOf (VL_Cobranca) != null &&
          !String.valueOf (VL_Cobranca).equals ("") &&
          !String.valueOf (VL_Cobranca).equals ("null")) {
        ed.setVL_Cobranca (new Double (VL_Cobranca).doubleValue ());
      }
      String VL_Desconto = request.getParameter ("FT_VL_Desconto");
      if (String.valueOf (VL_Desconto) != null &&
          !String.valueOf (VL_Desconto).equals ("") &&
          !String.valueOf (VL_Desconto).equals ("null")) {
        ed.setVL_Desconto (new Double (VL_Desconto).doubleValue ());
      }
      String VL_Rateio = request.getParameter ("FT_VL_Rateio");
      if (String.valueOf (VL_Rateio) != null &&
          !String.valueOf (VL_Rateio).equals ("") &&
          !String.valueOf (VL_Rateio).equals ("null")) {
        ed.setVL_Rateio (new Double (VL_Rateio).doubleValue ());
      }

      // System.out.println ("man ");

      Lote_Fornecedorrn.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void confirma_Compromisso (long oid_lote_Fornecedor, long oid_Compromisso) throws Excecoes {

    try {
      Lote_FornecedorRN Lote_Fornecedorrn = new Lote_FornecedorRN ();
      Lote_FornecedorED ed = new Lote_FornecedorED ();

      // System.out.println("comp="  + oid_Compromisso);

      ed.setOID_Lote_Fornecedor (oid_lote_Fornecedor);

      ed.setOID_Compromisso (oid_Compromisso);

      Lote_Fornecedorrn.confirma_Compromisso (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Lote_FornecedorRN Lote_Fornecedorrn = new Lote_FornecedorRN ();
      Lote_FornecedorED ed = new Lote_FornecedorED ();

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      Lote_Fornecedorrn.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList Lote_Fornecedor_Lista (HttpServletRequest request) throws Excecoes {

    Lote_FornecedorED ed = new Lote_FornecedorED ();
//// System.out.println("1 ");

    String oid_Lote_Fornecedor = request.getParameter ("oid_Lote_Fornecedor");
    if (String.valueOf (oid_Lote_Fornecedor) != null &&
        !String.valueOf (oid_Lote_Fornecedor).equals ("") &&
        !String.valueOf (oid_Lote_Fornecedor).equals ("null")) {
      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());
    }

    String NR_Documento_Cobranca = request.getParameter ("FT_NR_Documento_Cobranca");
    if (String.valueOf (NR_Documento_Cobranca) != null &&
        !String.valueOf (NR_Documento_Cobranca).equals ("") &&
        !String.valueOf (NR_Documento_Cobranca).equals ("null")) {
      ed.setNR_Documento_Cobranca (new Long (request.getParameter ("FT_NR_Documento_Cobranca")).longValue ());
    }

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null &&
        !String.valueOf (DT_Emissao_Inicial).equals ("null") &&
        !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (request.getParameter ("FT_DT_Emissao_Inicial"));
    }
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null &&
        !String.valueOf (DT_Emissao_Final).equals ("null") &&
        !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (request.getParameter ("FT_DT_Emissao_Final"));
    }

    String OID_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (OID_Pessoa) != null &&
        !String.valueOf (OID_Pessoa).equals ("null") &&
        !String.valueOf (OID_Pessoa).equals ("")) {
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
    }

    String DM_Situacao = request.getParameter ("DM_Situacao");
    if (String.valueOf (DM_Situacao) != null &&
        !String.valueOf (DM_Situacao).equals ("null") &&
        !String.valueOf (DM_Situacao).equals ("")) {
      ed.setDM_Situacao (request.getParameter ("DM_Situacao"));
    }
//// System.out.println("2 ");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
//// System.out.println("5 ");

    return new Lote_FornecedorRN ().lista (ed);

  }

  public Lote_FornecedorED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Lote_FornecedorRN Lote_Fornecedorrn = new Lote_FornecedorRN ();
      Lote_FornecedorED ed = new Lote_FornecedorED ();

      ed.setNR_Documento_Cobranca (new Long (request.getParameter ("FT_NR_Documento_Cobranca")).longValue ());
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));

      
      // System.out.println ("man 0");

      ed.setDM_Situacao ("A");

      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
      ed.setDT_Vencimento (request.getParameter ("FT_DT_Vencimento"));
      // System.out.println ("man 3");

      ed.setDM_Tipo_Documento ("CTO");

      ed.setTX_Observacao (" ");
      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (String.valueOf (TX_Observacao) != null &&
          !String.valueOf (TX_Observacao).equals ("null") &&
          !String.valueOf (TX_Observacao).equals ("")) {
        ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
      }
      String VL_Cobranca = request.getParameter ("FT_VL_Cobranca");
      if (String.valueOf (VL_Cobranca) != null &&
          !String.valueOf (VL_Cobranca).equals ("") &&
          !String.valueOf (VL_Cobranca).equals ("null")) {
        ed.setVL_Cobranca (new Double (VL_Cobranca).doubleValue ());
      }

      String VL_Desconto = request.getParameter ("FT_VL_Desconto");
      if (String.valueOf (VL_Desconto) != null &&
          !String.valueOf (VL_Desconto).equals ("") &&
          !String.valueOf (VL_Desconto).equals ("null")) {
        ed.setVL_Desconto (new Double (VL_Desconto).doubleValue ());
      }
      String VL_Rateio = request.getParameter ("FT_VL_Rateio");
      if (String.valueOf (VL_Rateio) != null &&
          !String.valueOf (VL_Rateio).equals ("") &&
          !String.valueOf (VL_Rateio).equals ("null")) {
        ed.setVL_Rateio (new Double (VL_Rateio).doubleValue ());
      }
      
      String oid_Usuario=request.getParameter ("oid_Usuario");
      if (oid_Usuario != null && !"null".equals(oid_Usuario) && !"".equals(oid_Usuario)){
        ed.setOID_Usuario (new Long (oid_Usuario).longValue ());
      }

      String oid_Compromisso=request.getParameter ("oid_Compromisso");
      if (oid_Compromisso != null && !"null".equals(oid_Compromisso) && !"".equals(oid_Compromisso)){
        ed.setOID_Compromisso (new Long (oid_Compromisso).longValue ());
      }

      return Lote_Fornecedorrn.inclui (ed);
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

  public Lote_FornecedorED getByRecord (HttpServletRequest request) throws Excecoes {

    Lote_FornecedorED ed = new Lote_FornecedorED ();
    String oid_Lote_Fornecedor = request.getParameter ("oid_Lote_Fornecedor");
    if (String.valueOf (oid_Lote_Fornecedor) != null &&
        !String.valueOf (oid_Lote_Fornecedor).equals ("") &&
        !String.valueOf (oid_Lote_Fornecedor).equals ("null")) {
      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());
    }

    return new Lote_FornecedorRN ().getByRecord (ed);

  }

  public byte[] imprime_Lote_Fornecedor (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    Lote_FornecedorED ed = new Lote_FornecedorED ();
    String oid_Lote_Fornecedor = request.getParameter ("oid_Lote_Fornecedor");

    if (String.valueOf (oid_Lote_Fornecedor) != null &&
        !String.valueOf (oid_Lote_Fornecedor).equals ("") &&
        !String.valueOf (oid_Lote_Fornecedor).equals ("null")) {
      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());
    }
    ed.setDM_Cobranca(request.getParameter ("FT_DM_Cobranca"));
    ed.setDM_Relatorio(request.getParameter ("FT_DM_Relatorio"));

    Lote_FornecedorRN geRN = new Lote_FornecedorRN ();

    byte[] b = geRN.imprime_Lote_Fornecedor (ed);

    return b;

  }

  public Lote_FornecedorED atualiza (HttpServletRequest request) throws Excecoes {

      Lote_FornecedorRN Lote_Fornecedorrn = new Lote_FornecedorRN ();
      Lote_FornecedorED ed = new Lote_FornecedorED ();

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());
      
      String oid_Usuario=request.getParameter ("oid_Usuario");
      if (oid_Usuario != null && !"null".equals(oid_Usuario) && !"".equals(oid_Usuario)){
        ed.setOID_Unidade (new Long (oid_Usuario).longValue ());
      }

      return Lote_Fornecedorrn.atualiza (ed);
  }

  public Lote_FornecedorED reabre (HttpServletRequest request) throws Excecoes {

    try {
      Lote_FornecedorRN Lote_Fornecedorrn = new Lote_FornecedorRN ();
      Lote_FornecedorED ed = new Lote_FornecedorED ();

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());
      
      String oid_Usuario=request.getParameter ("oid_Usuario");
      if (oid_Usuario != null && !"null".equals(oid_Usuario) && !"".equals(oid_Usuario)){
        ed.setOID_Unidade (new Long (oid_Usuario).longValue ());
      }

      return Lote_Fornecedorrn.reabre (ed);
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


}