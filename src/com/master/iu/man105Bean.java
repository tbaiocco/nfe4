package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Documento_Lote_FornecedorED;
import com.master.rn.Documento_Lote_FornecedorRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class man105Bean {

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("man -1");

      ed.setOID_Documento_Lote_Fornecedor (request.getParameter ("oid_Documento_Lote_Fornecedor"));
      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      // System.out.println ("2 ");

      String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
      if (String.valueOf (oid_Conhecimento) != null &&
          !String.valueOf (oid_Conhecimento).equals ("null") &&
          !String.valueOf (oid_Conhecimento).equals ("")) {
        ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      }

      // System.out.println ("4 ");

      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());

      // System.out.println ("5 ");

      ed.setTX_Observacao (" ");
      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (String.valueOf (TX_Observacao) != null &&
          !String.valueOf (TX_Observacao).equals ("null") &&
          !String.valueOf (TX_Observacao).equals ("")) {
        ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
      }

      String VL_Cobrado = request.getParameter ("FT_VL_Cobrado");
      if (String.valueOf (VL_Cobrado) != null &&
          !String.valueOf (VL_Cobrado).equals ("") &&
          !String.valueOf (VL_Cobrado).equals ("null")) {
        ed.setVL_Cobrado (new Double (VL_Cobrado).doubleValue ());
      }

      ed.setOID_Pessoa (" ");
      String OID_Pessoa = request.getParameter ("oid_Pessoa");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
      }

      // System.out.println ("99 ");

      Documento_Lote_Fornecedorrn.altera (ed);
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
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      ed.setOID_Documento_Lote_Fornecedor (request.getParameter ("oid_Documento_Lote_Fornecedor"));
      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      // System.out.println ("2 ");

      String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
      if (String.valueOf (oid_Conhecimento) != null &&
          !String.valueOf (oid_Conhecimento).equals ("null") &&
          !String.valueOf (oid_Conhecimento).equals ("")) {
        ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      }

      // System.out.println ("4 ");

      Documento_Lote_Fornecedorrn.deleta (ed);
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

  public ArrayList Documento_Lote_Fornecedor_Lista (HttpServletRequest request) throws Excecoes {

    Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();
    // System.out.println ("list 1 ");
    ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

    // System.out.println ("5 ");

    return new Documento_Lote_FornecedorRN ().lista (ed);

  }

  public Documento_Lote_FornecedorED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("lote 0");

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
      if (String.valueOf (oid_Conhecimento) != null &&
          !String.valueOf (oid_Conhecimento).equals ("null") &&
          !String.valueOf (oid_Conhecimento).equals ("")) {
        ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
        ed.setNR_Documento (request.getParameter ("FT_NR_Conhecimento"));
        ed.setDM_Tipo_Documento ("C");
      }

      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());

      ed.setTX_Observacao (" ");
      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (String.valueOf (TX_Observacao) != null &&
          !String.valueOf (TX_Observacao).equals ("null") &&
          !String.valueOf (TX_Observacao).equals ("")) {
        ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
      }

      ed.setOID_Pessoa (" ");
      String OID_Pessoa = request.getParameter ("oid_Pessoa");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
      }

      String VL_Previsto = request.getParameter ("FT_VL_Previsto");
      if (String.valueOf (VL_Previsto) != null &&
          !String.valueOf (VL_Previsto).equals ("") &&
          !String.valueOf (VL_Previsto).equals ("null")) {
        ed.setVL_Previsto (new Double (VL_Previsto).doubleValue ());
      }

      String VL_Cobrado = request.getParameter ("FT_VL_Cobrado");
      if (String.valueOf (VL_Cobrado) != null &&
          !String.valueOf (VL_Cobrado).equals ("") &&
          !String.valueOf (VL_Cobrado).equals ("null")) {
        ed.setVL_Cobrado (new Double (VL_Cobrado).doubleValue ());
      }

      return Documento_Lote_Fornecedorrn.inclui (ed);
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

  public Documento_Lote_FornecedorED inclui_Cto_Filtro (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("inclui_Cto_Filtro Bean 1");

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      // System.out.println ("inclui_Cto_Filtro Bean 2");

      String oid_Modal = request.getParameter ("oid_Modal");
      if (String.valueOf (oid_Modal) != null &&
          !String.valueOf (oid_Modal).equals ("null") &&
          !String.valueOf (oid_Modal).equals ("")) {
        ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
      }

      // System.out.println ("inclui_Cto_Filtro Bean 3");

      String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
      if (String.valueOf (oid_Cidade_Destino) != null &&
          !String.valueOf (oid_Cidade_Destino).equals ("null") &&
          !String.valueOf (oid_Cidade_Destino).equals ("")) {
        ed.setOID_Cidade_Destino (new Long (request.getParameter ("oid_Cidade_Destino")).longValue ());
      }

      // System.out.println ("inclui_Cto_Filtro Bean 4");

      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("null") &&
          !String.valueOf (oid_Unidade).equals ("")) {
        ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      }

      // System.out.println ("inclui_Cto_Filtro Bean 5");

      String NR_Conhecimento_Inicial = request.getParameter ("FT_NR_Conhecimento_Inicial");
      if (String.valueOf (NR_Conhecimento_Inicial) != null && !String.valueOf (NR_Conhecimento_Inicial).equals ("")) {
        ed.setNR_Conhecimento_Inicial (new Long (NR_Conhecimento_Inicial).longValue ());
      }

      String NR_Conhecimento_Final = request.getParameter ("FT_NR_Conhecimento_Final");
      if (String.valueOf (NR_Conhecimento_Final) != null && !String.valueOf (NR_Conhecimento_Final).equals ("")) {
        ed.setNR_Conhecimento_Final (new Long (NR_Conhecimento_Final).longValue ());
      }

      // System.out.println ("inclui_Cto_Filtro Bean 6");

      String NR_Postagem_Inicial = request.getParameter ("FT_NR_Postagem_Inicial");
      if (String.valueOf (NR_Postagem_Inicial) != null && !String.valueOf (NR_Postagem_Inicial).equals ("") && !String.valueOf (NR_Postagem_Inicial).equals ("null")) {
        ed.setNR_Postagem_Inicial (new Long (NR_Postagem_Inicial).longValue ());
      }

      String NR_Postagem_Final = request.getParameter ("FT_NR_Postagem_Final");
      if (String.valueOf (NR_Postagem_Final) != null && !String.valueOf (NR_Postagem_Final).equals ("") && !String.valueOf (NR_Postagem_Final).equals ("null")) {
        ed.setNR_Postagem_Final (new Long (NR_Postagem_Final).longValue ());
      }

      // System.out.println ("inclui_Cto_Filtro Bean 8");

      ed.setDT_Emissao_Inicial (request.getParameter ("FT_DT_Emissao_Inicial"));

      ed.setDT_Emissao_Final (request.getParameter ("FT_DT_Emissao_Final"));

      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());

      String OID_Pessoa = request.getParameter ("oid_Pessoa_Remetente");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa (OID_Pessoa);
      }

      OID_Pessoa = request.getParameter ("oid_Pessoa_Destinatario");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa_Destinatario (OID_Pessoa);
      }

      OID_Pessoa = request.getParameter ("oid_Pessoa_Entregadora");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa_Entregadora (OID_Pessoa);
      }

      String VL_Cobrado = request.getParameter ("FT_VL_Cobrado");
      if (String.valueOf (VL_Cobrado) != null &&
          !String.valueOf (VL_Cobrado).equals ("") &&
          !String.valueOf (VL_Cobrado).equals ("null")) {
        ed.setVL_Cobrado (new Double (VL_Cobrado).doubleValue ());
      }

      // System.out.println ("inclui_Cto_Filtro Bean 999");

      return Documento_Lote_Fornecedorrn.inclui_Cto_Filtro (ed);
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

  public Documento_Lote_FornecedorED inclui_Fatura_Postagem (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("inclui_Cto_Filtro Bean 1");

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());


      String NR_Fatura_Postagem = request.getParameter ("FT_NR_Fatura_Postagem");
      if (String.valueOf (NR_Fatura_Postagem) != null && !String.valueOf (NR_Fatura_Postagem).equals ("")) {
        ed.setNR_Fatura_Postagem (NR_Fatura_Postagem);
      }

      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());
      // System.out.println ("inclui_Cto_Filtro Bean 999");

      return Documento_Lote_Fornecedorrn.inclui_Fatura_Postagem (ed);
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

  public Documento_Lote_FornecedorED inclui_Fatura_Master (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("inclui_Fatura_Master Bean Fat=>>"+request.getParameter ("FT_NR_Fatura"));

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());


      String NR_Fatura = request.getParameter ("FT_NR_Fatura");
      if (String.valueOf (NR_Fatura) != null && !String.valueOf (NR_Fatura).equals ("")) {
        ed.setNR_Fatura_Master(NR_Fatura);
      }

      return Documento_Lote_Fornecedorrn.inclui_Fatura_Master (ed);
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

  public Documento_Lote_FornecedorED deleta_Cto_Filtro (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("deleta_Cto_Filtro Bean 1");

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("null") &&
          !String.valueOf (oid_Unidade).equals ("")) {
        ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      }

      String NR_Conhecimento_Inicial = request.getParameter ("FT_NR_Conhecimento_Inicial");
      if (String.valueOf (NR_Conhecimento_Inicial) != null && !String.valueOf (NR_Conhecimento_Inicial).equals ("")) {
        ed.setNR_Conhecimento_Inicial (new Long (NR_Conhecimento_Inicial).longValue ());
      }

      String NR_Conhecimento_Final = request.getParameter ("FT_NR_Conhecimento_Final");
      if (String.valueOf (NR_Conhecimento_Final) != null && !String.valueOf (NR_Conhecimento_Final).equals ("")) {
        ed.setNR_Conhecimento_Final (new Long (NR_Conhecimento_Final).longValue ());
      }

      // System.out.println ("deleta_Cto_Filtro Bean 5");

      String oid_Tipo_Movimento = request.getParameter ("oid_Tipo_Movimento");
      if (String.valueOf (oid_Tipo_Movimento) != null &&
          !String.valueOf (oid_Tipo_Movimento).equals ("null") &&
          !String.valueOf (oid_Tipo_Movimento).equals ("")) {
        ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());
      }

      // System.out.println ("deleta_Cto_Filtro Bean 8");

      String OID_Pessoa = request.getParameter ("oid_Pessoa_Remetente");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa (OID_Pessoa);
      }

      OID_Pessoa = request.getParameter ("oid_Pessoa_Destinatario");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa_Destinatario (OID_Pessoa);
      }

      // System.out.println ("deleta_Cto_Filtro Bean 10");

      String VL_Cobrado = request.getParameter ("FT_VL_Cobrado");
      if (String.valueOf (VL_Cobrado) != null &&
          !String.valueOf (VL_Cobrado).equals ("") &&
          !String.valueOf (VL_Cobrado).equals ("null")) {
        ed.setVL_Cobrado (new Double (VL_Cobrado).doubleValue ());
      }

      // System.out.println ("deleta_Cto_Filtro Bean 999");

      return Documento_Lote_Fornecedorrn.deleta_Cto_Filtro (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("deleta_Cto_Filtro");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Documento_Lote_FornecedorED rateio_Cto_Filtro (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("inclui cto filtro aprov->>" + request.getParameter ("oid_Lote_Fornecedor"));

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      // System.out.println ("rateio_Cto_Filtro Bean 2");

      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("null") &&
          !String.valueOf (oid_Unidade).equals ("")) {
        ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      }

      // System.out.println ("rateio_Cto_Filtro Bean 3");

      String NR_Conhecimento_Inicial = request.getParameter ("FT_NR_Conhecimento_Inicial");
      if (String.valueOf (NR_Conhecimento_Inicial) != null && !String.valueOf (NR_Conhecimento_Inicial).equals ("")) {
        ed.setNR_Conhecimento_Inicial (new Long (NR_Conhecimento_Inicial).longValue ());
      }

      String NR_Conhecimento_Final = request.getParameter ("FT_NR_Conhecimento_Final");
      if (String.valueOf (NR_Conhecimento_Final) != null && !String.valueOf (NR_Conhecimento_Final).equals ("")) {
        ed.setNR_Conhecimento_Final (new Long (NR_Conhecimento_Final).longValue ());
      }

      // System.out.println ("rateio_Cto_Filtro Bean 5");

      String oid_Tipo_Movimento = request.getParameter ("oid_Tipo_Movimento");
      if (String.valueOf (oid_Tipo_Movimento) != null &&
          !String.valueOf (oid_Tipo_Movimento).equals ("null") &&
          !String.valueOf (oid_Tipo_Movimento).equals ("")) {
        ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());
      }

      // System.out.println ("deleta_Cto_Filtro Bean 8");

      String OID_Pessoa = request.getParameter ("oid_Pessoa_Remetente");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa (OID_Pessoa);
      }

      OID_Pessoa = request.getParameter ("oid_Pessoa_Destinatario");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa_Destinatario (OID_Pessoa);
      }

      // System.out.println ("rateio_Cto_Filtro Bean 10");

      String VL_Cobrado = request.getParameter ("FT_VL_Cobrado");
      if (String.valueOf (VL_Cobrado) != null &&
          !String.valueOf (VL_Cobrado).equals ("") &&
          !String.valueOf (VL_Cobrado).equals ("null")) {
        ed.setVL_Cobrado (new Double (VL_Cobrado).doubleValue ());
      }

      ed.setDM_Rateio("G");
      if ("R".equals(request.getParameter ("FT_DM_Rateio"))){
        ed.setDM_Rateio("R");
      }

      ed.setDM_Criterio("T");
      if ("P".equals(request.getParameter ("FT_DM_Criterio"))){
        ed.setDM_Criterio("P");
      }
      
      // System.out.println ("rateio_Cto_Filtro Rateio=" + ed.getDM_Rateio());
      // System.out.println ("rateio_Cto_Filtro Criterio=" + ed.getDM_Criterio());

      return Documento_Lote_Fornecedorrn.rateio_Cto_Filtro (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("rateio_Cto_Filtro");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Documento_Lote_FornecedorED inclui_Master (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("lote 0");

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
      if (String.valueOf (oid_Ordem_Frete) != null &&
          !String.valueOf (oid_Ordem_Frete).equals ("null") &&
          !String.valueOf (oid_Ordem_Frete).equals ("")) {
        ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
        ed.setNR_Documento (request.getParameter ("FT_NR_Master"));
        ed.setNR_Master (request.getParameter ("FT_NR_Master"));
        ed.setNR_Ordem_Frete (request.getParameter ("FT_NR_Ordem_Frete"));
        // System.out.println ("iu inclui master : " + ed.getNR_Master ());

        ed.setDM_Tipo_Documento ("C");
      }

      String VL_Cobrado = request.getParameter ("FT_VL_Cobrado");
      if (String.valueOf (VL_Cobrado) != null &&
          !String.valueOf (VL_Cobrado).equals ("") &&
          !String.valueOf (VL_Cobrado).equals ("null")) {
        ed.setVL_Cobrado (new Double (VL_Cobrado).doubleValue ());
      }
      ed.setOID_Pessoa (" ");
      String OID_Pessoa = request.getParameter ("oid_Pessoa");
      if (String.valueOf (OID_Pessoa) != null &&
          !String.valueOf (OID_Pessoa).equals ("null") &&
          !String.valueOf (OID_Pessoa).equals ("")) {
        ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
      }

      return Documento_Lote_Fornecedorrn.inclui_Master (ed);
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

  public Documento_Lote_FornecedorED inclui_Manifesto (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("lote 0");

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      String oid_Manifesto = request.getParameter ("oid_Manifesto");
      if (String.valueOf (oid_Manifesto) != null &&
          !String.valueOf (oid_Manifesto).equals ("null") &&
          !String.valueOf (oid_Manifesto).equals ("")) {
        ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
        // System.out.println ("iu inclui manifesto : " + ed.getNR_Master ());

        ed.setDM_Tipo_Documento ("C");
      }

      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());
      // System.out.println ("inclui_Cto_Filtro Bean 999");

      String VL_Cobrado = request.getParameter ("FT_VL_Cobrado");
      if (String.valueOf (VL_Cobrado) != null &&
          !String.valueOf (VL_Cobrado).equals ("") &&
          !String.valueOf (VL_Cobrado).equals ("null")) {
        ed.setVL_Cobrado (new Double (VL_Cobrado).doubleValue ());
      }

      return Documento_Lote_Fornecedorrn.inclui_Manifesto (ed);
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

  public Documento_Lote_FornecedorED inclui_Ordem_Frete (HttpServletRequest request) throws Excecoes {

    try {
      Documento_Lote_FornecedorRN Documento_Lote_Fornecedorrn = new Documento_Lote_FornecedorRN ();
      Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();

      // System.out.println ("lote 0");

      ed.setOID_Lote_Fornecedor (new Long (request.getParameter ("oid_Lote_Fornecedor")).longValue ());

      String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
      if (String.valueOf (oid_Ordem_Frete) != null &&
          !String.valueOf (oid_Ordem_Frete).equals ("null") &&
          !String.valueOf (oid_Ordem_Frete).equals ("")) {
        ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
        ed.setNR_Ordem_Frete (request.getParameter ("FT_NR_Ordem_Frete"));
        // System.out.println ("iu inclui_Ordem_Frete : " + ed.getOID_Ordem_Frete ());

        ed.setDM_Tipo_Documento ("C");
      }

      return Documento_Lote_Fornecedorrn.inclui_Ordem_Frete (ed);
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

  public Documento_Lote_FornecedorED getByRecord (Documento_Lote_FornecedorED ed) throws Excecoes {

    return new Documento_Lote_FornecedorRN ().getByRecord (ed);

  }

  public Documento_Lote_FornecedorED getByRecord (HttpServletRequest request) throws Excecoes {

    Documento_Lote_FornecedorED ed = new Documento_Lote_FornecedorED ();
    String oid_Documento_Lote_Fornecedor = request.getParameter ("oid_Documento_Lote_Fornecedor");

    ed.setOID_Documento_Lote_Fornecedor (request.getParameter ("oid_Documento_Lote_Fornecedor"));

    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    if (String.valueOf (oid_Conhecimento) != null &&
        !String.valueOf (oid_Conhecimento).equals ("null") &&
        !String.valueOf (oid_Conhecimento).equals ("")) {
      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    }

    return new Documento_Lote_FornecedorRN ().getByRecord (ed);

  }

}
