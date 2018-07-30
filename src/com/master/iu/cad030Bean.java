package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Pessoa_EDIED;
import com.master.rn.Pessoa_EDIRN;
import com.master.util.Data;
import com.master.util.Excecoes;

public class cad030Bean {

  private Data dt_stamp = new Data ();

  public Pessoa_EDIED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Pessoa_EDIRN Pessoa_EDIrn = new Pessoa_EDIRN ();
      Pessoa_EDIED pessoa_ed = new Pessoa_EDIED ();

      //request em cima dos campos dos forms html

      // System.out.println ("bean");

//      pessoa_ed.setOid_padrao_Imp(Long.parseLong(request.getParameter("oid_Padrao_Imp")));
      pessoa_ed.setOid_padrao_Exp (Long.parseLong (request.getParameter ("oid_Padrao_Exp")));
      pessoa_ed.setNm_arquivo_Imp (request.getParameter ("FT_NM_Arquivo_Imp"));
      pessoa_ed.setNm_arquivo_Exp (request.getParameter ("FT_NM_Arquivo_Exp"));
      //pessoa_ed.setDt_stamp_pessoa(dt_stamp.getDataDMY());
      pessoa_ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
      // System.out.println (pessoa_ed.getOid_Pessoa ());

      return Pessoa_EDIrn.inclui (pessoa_ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir o Pessoa");
      excecoes.setMetodo ("incluiPessoa");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList getByOidPessoa (HttpServletRequest request) throws Excecoes {

    Pessoa_EDIED ed = new Pessoa_EDIED ();

    String oid_pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_pessoa) != null && String.valueOf (oid_pessoa).length () > 0) {
      ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
    }
    String DM_Tipo_Transacao = request.getParameter ("FT_DM_Tipo_Transacao");
    if (String.valueOf (DM_Tipo_Transacao) != null && String.valueOf (DM_Tipo_Transacao).length () > 0) {
      ed.setDm_tipo_transacao (request.getParameter ("DM_Tipo_Transacao"));
    }

    return new Pessoa_EDIRN ().getByOidPessoa (ed);

  }

  public ArrayList lista_PadroesExp (HttpServletRequest request) throws Excecoes {

    Pessoa_EDIED ed = new Pessoa_EDIED ();

    String oid_pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_pessoa) != null && String.valueOf (oid_pessoa).length () > 0) {
      ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
    }
    ed.setDm_tipo_transacao ("E");

    return new Pessoa_EDIRN ().getByOidPessoa (ed);
  }

  public ArrayList lista_PadroesImp (HttpServletRequest request) throws Excecoes {

    Pessoa_EDIED ed = new Pessoa_EDIED ();

    String oid_pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_pessoa) != null && String.valueOf (oid_pessoa).length () > 0) {
      ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
    }
    ed.setDm_tipo_transacao ("I");

    return new Pessoa_EDIRN ().getByOidPessoa (ed);
  }

  public Pessoa_EDIED getByOidPessoa2 (String oid) throws Excecoes {

    Pessoa_EDIED ed = new Pessoa_EDIED ();

    String oid_pessoa = oid;
//// System.out.println("bean " +oid_pessoa);
    if (String.valueOf (oid_pessoa) != null && String.valueOf (oid_pessoa).length () > 0) {
      ed.setOid_Pessoa (oid_pessoa);
    }

    return new Pessoa_EDIRN ().getByOidPessoa2 (ed);

  }

  public String getByPasta (String oid_Pessoa , String oid_Padrao , String dm_Imp_Exp) throws Excecoes {

    return new Pessoa_EDIRN ().getByPasta (oid_Pessoa , oid_Padrao , dm_Imp_Exp);

  }

  public boolean deleta (HttpServletRequest request) throws Excecoes {

    Pessoa_EDIED pessoa_ed = new Pessoa_EDIED ();

    String oid_pessoa_edi = request.getParameter ("oid_Pessoa_Edi");

    // System.out.println (oid_pessoa_edi);
    if (oid_pessoa_edi != null && oid_pessoa_edi.length () > 0) {
      pessoa_ed.setOid_pessoa_edi (new Long (request.getParameter ("oid_Pessoa_Edi")).longValue ());

    }

    return new Pessoa_EDIRN ().deleta (pessoa_ed);

  }

  public Pessoa_EDIED getByRecord (HttpServletRequest request) throws Excecoes {

    Pessoa_EDIED pessoa_ed = new Pessoa_EDIED ();

    String oid_pessoa_edi = request.getParameter ("oid_Pessoa_Edi");

    // System.out.println (oid_pessoa_edi);
    if (oid_pessoa_edi != null && oid_pessoa_edi.length () > 0) {
      pessoa_ed.setOid_pessoa_edi (new Long (request.getParameter ("oid_Pessoa_Edi")).longValue ());

    }

    return new Pessoa_EDIRN ().getByRecord (pessoa_ed);

  }

  /*
    public Pessoa_EDIED getByOidTipo(String oid_tipo) throws Excecoes{

    Pessoa_EDIED pessoa_ed = new Pessoa_EDIED();

           if (oid_tipo != null && oid_tipo.length() > 0)
           {
       pessoa_ed.setOid_tipo_pessoa(new Long(oid_tipo).longValue());
          }

          return new Pessoa_EDIRN().getByOidTipo(pessoa_ed);

    }*/

  public void update (HttpServletRequest request) throws Excecoes {

    try {
      Pessoa_EDIRN Pessoa_EDIrn = new Pessoa_EDIRN ();
      Pessoa_EDIED pessoa_ed = new Pessoa_EDIED ();

      //request em cima dos campos dos forms html
      pessoa_ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
      pessoa_ed.setOid_padrao_Imp (Long.parseLong (request.getParameter ("oid_Padrao_Imp")));
      pessoa_ed.setOid_padrao_Exp (Long.parseLong (request.getParameter ("oid_Padrao_Exp")));
      pessoa_ed.setNm_arquivo_Imp (request.getParameter ("FT_NM_Arquivo_Imp"));
      pessoa_ed.setNm_arquivo_Exp (request.getParameter ("FT_NM_Arquivo_Exp"));

      Pessoa_EDIrn.update (pessoa_ed);
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