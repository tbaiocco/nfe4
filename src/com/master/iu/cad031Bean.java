package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Ocorrencia_EDIED;
import com.master.rn.Ocorrencia_EDIRN;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class cad031Bean {

  private Data dt_stamp = new Data ();

  public Ocorrencia_EDIED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Ocorrencia_EDIRN Ocorrencia_EDIRN = new Ocorrencia_EDIRN ();
      Ocorrencia_EDIED Ocorrencia_EDIED = new Ocorrencia_EDIED ();

      //request em cima dos campos dos forms html

      // System.out.println ("bean");
      Ocorrencia_EDIED.setOid_Tipo_Ocorrencia (Long.parseLong (request.getParameter ("oid_Tipo_Ocorrencia")));
      Ocorrencia_EDIED.setOid_padrao (Long.parseLong (request.getParameter ("oid_Padrao_Exp")));
      Ocorrencia_EDIED.setCd_Ocorrencia (request.getParameter ("FT_CD_Ocorrencia"));
      // System.out.println ("bean 99");

      return Ocorrencia_EDIRN.inclui (Ocorrencia_EDIED);
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

  public ArrayList lista (HttpServletRequest request) throws Excecoes {

    // System.out.println ("bean lista ");

    Ocorrencia_EDIRN Ocorrencia_EDIRN = new Ocorrencia_EDIRN ();
    Ocorrencia_EDIED Ocorrencia_EDIED = new Ocorrencia_EDIED ();

    String oid_Padrao = request.getParameter ("oid_Padrao");

    // System.out.println (oid_Padrao);
    if (JavaUtil.doValida (oid_Padrao)) {
      Ocorrencia_EDIED.setOid_padrao (Long.parseLong (oid_Padrao));
    }

    return new Ocorrencia_EDIRN ().lista (Ocorrencia_EDIED);

  }

  public boolean deleta (HttpServletRequest request) throws Excecoes {

    Ocorrencia_EDIED Ocorrencia_EDIED = new Ocorrencia_EDIED ();

    String oid_Ocorrencia_EDI = request.getParameter ("oid_Ocorrencia_EDI");

    // System.out.println (oid_Ocorrencia_EDI);
    if (oid_Ocorrencia_EDI != null && oid_Ocorrencia_EDI.length () > 0) {
      Ocorrencia_EDIED.setOid_Ocorrencia_Edi (new Long (request.getParameter ("oid_Ocorrencia_EDI")).longValue ());

    }

    return new Ocorrencia_EDIRN ().deleta (Ocorrencia_EDIED);

  }

  public Ocorrencia_EDIED getByRecord (HttpServletRequest request) throws Excecoes {

    Ocorrencia_EDIED Ocorrencia_EDIED = new Ocorrencia_EDIED ();

    String oid_Ocorrencia_EDI = request.getParameter ("oid_Ocorrencia_EDI");

    // System.out.println (oid_Ocorrencia_EDI);
    if (oid_Ocorrencia_EDI != null && oid_Ocorrencia_EDI.length () > 0) {
      Ocorrencia_EDIED.setOid_Ocorrencia_Edi (new Long (request.getParameter ("oid_Ocorrencia_EDI")).longValue ());

    }

    return new Ocorrencia_EDIRN ().getByRecord (Ocorrencia_EDIED);

  }

  public void update (HttpServletRequest request) throws Excecoes {

    try {
      Ocorrencia_EDIRN Ocorrencia_EDIRN = new Ocorrencia_EDIRN ();
      Ocorrencia_EDIED Ocorrencia_EDIED = new Ocorrencia_EDIED ();

      //request em cima dos campos dos forms html
      String oid_Ocorrencia_EDI = request.getParameter ("oid_Ocorrencia_EDI");

      // System.out.println (oid_Ocorrencia_EDI);
      if (oid_Ocorrencia_EDI != null && oid_Ocorrencia_EDI.length () > 0) {
        Ocorrencia_EDIED.setOid_Ocorrencia_Edi (new Long (request.getParameter ("oid_Ocorrencia_EDI")).longValue ());
      }
      Ocorrencia_EDIED.setCd_Ocorrencia (request.getParameter ("FT_CD_Ocorrencia"));

      Ocorrencia_EDIRN.update (Ocorrencia_EDIED);
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