package com.master.iu;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Pessoa_EMailED;
import com.master.rn.Pessoa_EMailRN;
import com.master.util.Data;
import com.master.util.Excecoes;

public class Pessoa_EmailBean {

  private Data dt_stamp = new Data ();

  public Pessoa_EMailED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Pessoa_EMailRN Pessoa_EMailrn = new Pessoa_EMailRN ();
      Pessoa_EMailED pessoaEmail = new Pessoa_EMailED ();

      // System.out.println ("bean");
      pessoaEmail.setOid_Host (Long.parseLong (request.getParameter ("oid_Host")));
      pessoaEmail.setOid_evento (Long.parseLong (request.getParameter ("oid_Evento")));
      pessoaEmail.setNm_Email_Origem (request.getParameter ("FT_NM_Email_Origem"));
      pessoaEmail.setNm_Email_Destino (request.getParameter ("FT_NM_Email_Destino"));
      pessoaEmail.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
      pessoaEmail.setNm_Usuario_Destino (request.getParameter ("FT_NM_Usuario_Destino"));
      pessoaEmail.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
      // System.out.println (pessoaEmail.getOid_Pessoa ());

      return Pessoa_EMailrn.inclui (pessoaEmail);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir ");
      excecoes.setMetodo ("incluiPessoa");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (HttpServletRequest request) throws Excecoes {

    Pessoa_EMailED ed = new Pessoa_EMailED ();

    String oid_pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_pessoa) != null && String.valueOf (oid_pessoa).length () > 0) {
      ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
    }

    return new Pessoa_EMailRN ().lista (ed);

  }


  public boolean deleta (HttpServletRequest request) throws Excecoes {

    Pessoa_EMailED pessoaEmail = new Pessoa_EMailED ();

    String oid_Pessoa_EMail = request.getParameter ("oid_Pessoa_Email");

    // System.out.println (oid_Pessoa_EMail);
    if (oid_Pessoa_EMail != null && oid_Pessoa_EMail.length () > 0) {
      pessoaEmail.setOid_Pessoa_EMail (Long.parseLong (request.getParameter ("oid_Pessoa_Email")));
    }

    return new Pessoa_EMailRN ().deleta (pessoaEmail);

  }

  public Pessoa_EMailED getByRecord (HttpServletRequest request) throws Excecoes {

    Pessoa_EMailED pessoaEmail = new Pessoa_EMailED ();

    String oid_Pessoa_EMail = request.getParameter ("oid_Pessoa_EMail");

    // System.out.println (oid_Pessoa_EMail);
    if (oid_Pessoa_EMail != null && oid_Pessoa_EMail.length () > 0) {
      pessoaEmail.setOid_Pessoa_EMail (new Long (request.getParameter ("oid_Pessoa_EMail")).longValue ());

    }

    return new Pessoa_EMailRN ().getByRecord (pessoaEmail);

  }

  public void update (HttpServletRequest request) throws Excecoes {

    try {
      Pessoa_EMailRN Pessoa_EMailrn = new Pessoa_EMailRN ();
      Pessoa_EMailED pessoaEmail = new Pessoa_EMailED ();

      //request em cima dos campos dos forms html

      pessoaEmail.setOid_Pessoa_EMail (Long.parseLong (request.getParameter ("oid_Pessoa_Email")));
      pessoaEmail.setNm_Email_Origem (request.getParameter ("FT_NM_Email_Origem"));
      pessoaEmail.setNm_Email_Destino (request.getParameter ("FT_NM_Email_Destino"));
      pessoaEmail.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
      pessoaEmail.setNm_Usuario_Destino (request.getParameter ("FT_NM_Usuario_Destino"));
      Pessoa_EMailrn.update (pessoaEmail);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar a Pessoa");
      excecoes.setMetodo ("update");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }
}