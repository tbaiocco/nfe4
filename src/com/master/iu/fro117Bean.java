package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Rota_EntregaED;
import com.master.rn.Rota_EntregaRN;
import com.master.util.Excecoes;

public class fro117Bean {

  public Rota_EntregaED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Rota_EntregaRN Rota_Entregarn = new Rota_EntregaRN ();
      Rota_EntregaED ed = new Rota_EntregaED ();

      String OID_Cidade = request.getParameter ("oid_Cidade_Origem");
      if (OID_Cidade != null && !OID_Cidade.equals ("null") && !OID_Cidade.equals ("")) {
        ed.setOID_Cidade (new Long (OID_Cidade).longValue ());
      }

      String NR_Ordem = request.getParameter ("FT_NR_Ordem");
      if (NR_Ordem != null && !NR_Ordem.equals ("null") && !NR_Ordem.equals ("")) {
        ed.setNR_Ordem (new Long (NR_Ordem).longValue ());
      }
      String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
      if (NR_Prazo_Entrega != null && !NR_Prazo_Entrega.equals ("null") && !NR_Prazo_Entrega.equals ("")) {
        ed.setNR_Prazo_Entrega (new Long (NR_Prazo_Entrega).longValue ());
      }
      ed.setNM_Rota_Entrega (request.getParameter ("FT_NM_Rota_Entrega"));
      ed.setCD_Rota_Entrega (request.getParameter ("FT_CD_Rota_Entrega"));
      ed.setOID_Rota_Entrega (request.getParameter ("oid_Rota_Entrega"));
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Destinatario"));

      return Rota_Entregarn.inclui (ed);
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
      Rota_EntregaRN Rota_Entregarn = new Rota_EntregaRN ();
      Rota_EntregaED ed = new Rota_EntregaED ();

      String OID_Cidade = request.getParameter ("oid_Cidade_Origem");
      if (OID_Cidade != null && !OID_Cidade.equals ("null") && !OID_Cidade.equals ("")) {
        ed.setOID_Cidade (new Long (OID_Cidade).longValue ());
      }

      String NR_Ordem = request.getParameter ("FT_NR_Ordem");
      if (NR_Ordem != null && !NR_Ordem.equals ("null") && !NR_Ordem.equals ("")) {
        ed.setNR_Ordem (new Long (NR_Ordem).longValue ());
      }
      String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
      if (NR_Prazo_Entrega != null && !NR_Prazo_Entrega.equals ("null") && !NR_Prazo_Entrega.equals ("")) {
        ed.setNR_Prazo_Entrega (new Long (NR_Prazo_Entrega).longValue ());
      }
      ed.setNM_Rota_Entrega (request.getParameter ("FT_NM_Rota_Entrega"));
      ed.setCD_Rota_Entrega (request.getParameter ("FT_CD_Rota_Entrega"));
      ed.setOID_Rota_Entrega (request.getParameter ("oid_Rota_Entrega"));
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Destinatario"));

      Rota_Entregarn.altera (ed);
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
      Rota_EntregaRN Rota_Entregarn = new Rota_EntregaRN ();
      Rota_EntregaED ed = new Rota_EntregaED ();

      ed.setOID_Rota_Entrega (request.getParameter ("oid_Rota_Entrega"));

      Rota_Entregarn.deleta (ed);
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

  public ArrayList Rota_Entrega_Lista (HttpServletRequest request) throws Excecoes {

    Rota_EntregaED ed = new Rota_EntregaED ();

    String OID_Cidade = request.getParameter ("oid_Cidade_Origem");
    if (OID_Cidade != null && !OID_Cidade.equals ("null") && !OID_Cidade.equals ("")) {
      ed.setOID_Cidade (new Long (OID_Cidade).longValue ());
    }

    String NR_Ordem = request.getParameter ("FT_NR_Ordem");
    if (NR_Ordem != null && !NR_Ordem.equals ("null") && !NR_Ordem.equals ("")) {
      ed.setNR_Ordem (new Long (NR_Ordem).longValue ());
    }
    String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
    if (NR_Prazo_Entrega != null && !NR_Prazo_Entrega.equals ("null") && !NR_Prazo_Entrega.equals ("")) {
      ed.setNR_Prazo_Entrega (new Long (NR_Prazo_Entrega).longValue ());
    }
    ed.setNM_Rota_Entrega (request.getParameter ("FT_NM_Rota_Entrega"));
    ed.setCD_Rota_Entrega (request.getParameter ("FT_CD_Rota_Entrega"));
    ed.setOID_Rota_Entrega (request.getParameter ("oid_Rota_Entrega"));
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Destinatario"));

    return new Rota_EntregaRN ().lista (ed);

  }

  public ArrayList lista_CDByPessoa (HttpServletRequest request) throws Excecoes {

    Rota_EntregaED ed = new Rota_EntregaED ();

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Destinatario"));

    return new Rota_EntregaRN ().listaCDByPessoa (ed);

  }

  public Rota_EntregaED getCDByPessoa (String oid_pessoa, long oid_Cidade) throws Excecoes {

    Rota_EntregaED ed = new Rota_EntregaED ();

    ed.setOID_Pessoa (oid_pessoa);
    ed.setOID_Cidade(oid_Cidade);

    return new Rota_EntregaRN ().getCDByPessoa (ed);

  }

  public Rota_EntregaED getCDByPessoa (String oid_pessoa) throws Excecoes {

    Rota_EntregaED ed = new Rota_EntregaED ();

    ed.setOID_Pessoa (oid_pessoa);

    return new Rota_EntregaRN ().getCDByPessoa (ed);

  }

  public Rota_EntregaED getByRecord (HttpServletRequest request) throws Excecoes {

    Rota_EntregaED ed = new Rota_EntregaED ();
    ed.setOID_Rota_Entrega (request.getParameter ("oid_Rota_Entrega"));
    return new Rota_EntregaRN ().getByRecord (ed);
  }

  public Rota_EntregaED getByCD_Rota_Entrega (String cd_Rota_Entrega) throws Excecoes {

    Rota_EntregaED ed = new Rota_EntregaED ();
    ed.setCD_Rota_Entrega (cd_Rota_Entrega);
    return new Rota_EntregaRN ().getByRecord (ed);
  }

  public Rota_EntregaED getByOID (String oid_Rota_Entrega) throws Excecoes {

    Rota_EntregaED ed = new Rota_EntregaED ();
    ed.setOID_Rota_Entrega (oid_Rota_Entrega);
    return new Rota_EntregaRN ().getByRecord (ed);
  }

  public ArrayList getRotas () throws Excecoes {

    return new Rota_EntregaRN ().getRotas ();
  }

}