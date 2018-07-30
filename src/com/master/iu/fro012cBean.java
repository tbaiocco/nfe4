package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Ordem_CompraED;
import com.master.rn.Ordem_CompraRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <
 */

public class fro012cBean {

  public ArrayList Ordem_Compra_Lista (HttpServletRequest request) throws Excecoes {

    Ordem_CompraED ed = new Ordem_CompraED ();

    String OID_Ordem_Compra = request.getParameter ("oid_Ordem_Compra");
    if (String.valueOf (OID_Ordem_Compra) != null &&
        !String.valueOf (OID_Ordem_Compra).equals ("") &&
        !String.valueOf (OID_Ordem_Compra).equals ("null")) {
      ed.setOID_Ordem_Compra (new Long (OID_Ordem_Compra).longValue ());
    }
    ed.setDT_Emissao_Inicial (request.getParameter ("FT_DT_Ordem_Compra"));

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    return new Ordem_CompraRN ().lista (ed);

  }

  public byte[] Relatorio_Ordem_Compra (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    Ordem_CompraED ed = new Ordem_CompraED ();

    String OID_Ordem_Compra = request.getParameter ("oid_Ordem_Compra");
    if (String.valueOf (OID_Ordem_Compra) != null &&
        !String.valueOf (OID_Ordem_Compra).equals ("") &&
        !String.valueOf (OID_Ordem_Compra).equals ("null")) {
      ed.setOID_Ordem_Compra (new Long (OID_Ordem_Compra).longValue ());
    }
    ed.setDT_Emissao_Inicial (request.getParameter ("FT_DT_Inicial"));
    ed.setDT_Emissao_Final (request.getParameter ("FT_DT_Final"));

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Funcionario (request.getParameter ("oid_Pessoa_Funcionario"));

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    ed.setDM_Relatorio(request.getParameter ("FT_DM_Relatorio"));
    
    
    Ordem_CompraRN geRN = new Ordem_CompraRN ();
    byte[] b = geRN.Relatorio_Ordem_Compra (ed);

    return b;

  }

  public void deleta_Movimento (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_CompraRN Ordem_CompraRN = new Ordem_CompraRN ();
      Ordem_CompraED ed = new Ordem_CompraED ();

      ed.setOID_Movimento_Ordem_Compra (new Long (request.getParameter ("oid_Movimento_Ordem_Compra")).longValue ());

      Ordem_CompraRN.deleta_Movimento (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public byte[] imprime_Requisicao (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    Ordem_CompraED ed = new Ordem_CompraED ();

    String OID_Ordem_Compra = request.getParameter ("oid_Ordem_Compra");
    if (String.valueOf (OID_Ordem_Compra) != null &&
        !String.valueOf (OID_Ordem_Compra).equals ("") &&
        !String.valueOf (OID_Ordem_Compra).equals ("null")) {
      ed.setOID_Ordem_Compra (new Long (OID_Ordem_Compra).longValue ());
    }
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Tipo_Servico"));
    ed.setDT_Emissao(request.getParameter ("FT_DT_Ordem_Compra"));

    // System.out.println ("FT_DM_Tipo_Servico->" + request.getParameter ("FT_DM_Tipo_Servico"));

    Ordem_CompraRN geRN = new Ordem_CompraRN ();
    byte[] b = geRN.imprime_Requisicao (ed);

    return b;

  }

}