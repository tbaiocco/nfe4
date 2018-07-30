package com.master.iu;

/**
 */

import javax.servlet.http.*;
import com.master.rn.Orcamento_CompraRN;
import com.master.ed.Orcamento_CompraED;
import com.master.util.Excecoes;
import java.util.*;

public class est015Bean {

  public Orcamento_CompraED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Orcamento_CompraRN Orcamento_CompraRN = new Orcamento_CompraRN ();
      Orcamento_CompraED ed = new Orcamento_CompraED ();

      String oid_Orcamento_Compra = request.getParameter ("oid_Orcamento_Compra");
      if (oid_Orcamento_Compra != null && oid_Orcamento_Compra.length () > 0) {
        ed.setoid_Orcamento_Compra (Long.parseLong (oid_Orcamento_Compra));
      }

      String oid_Orcamento_Conta = request.getParameter ("oid_Orcamento_Conta");
      if (oid_Orcamento_Conta != null && oid_Orcamento_Conta.length () > 0) {
        ed.setoid_Orcamento_Conta (oid_Orcamento_Conta);
      }

      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setoid_Solicitacao_Compra (Long.parseLong (oid_Solicitacao_Compra));
      }
      String Valor = request.getParameter ("FT_VL_Compra");
      if (Valor != null && Valor.length () > 0) {
        ed.setvl_Compra(Double.parseDouble (Valor));
      }

      return Orcamento_CompraRN.inclui (ed);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }


  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Orcamento_CompraRN Orcamento_CompraRN = new Orcamento_CompraRN ();
      Orcamento_CompraED ed = new Orcamento_CompraED ();

      String oid_Orcamento_Compra = request.getParameter ("oid_Orcamento_Compra");
      if (oid_Orcamento_Compra != null && oid_Orcamento_Compra.length () > 0) {
        ed.setoid_Orcamento_Compra (Long.parseLong (oid_Orcamento_Compra));
      }

      Orcamento_CompraRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public Orcamento_CompraED getByRecord (HttpServletRequest request) throws Excecoes {

    Orcamento_CompraED ed = new Orcamento_CompraED ();

    String oid_Orcamento_Compra = request.getParameter ("oid_Orcamento_Compra");
    if (oid_Orcamento_Compra != null && oid_Orcamento_Compra.length () > 0) {
      ed.setoid_Orcamento_Compra (Long.parseLong (oid_Orcamento_Compra));
    }

    return new Orcamento_CompraRN ().getByRecord (ed);

  }

  public ArrayList Lista (HttpServletRequest request) throws Excecoes {

    Orcamento_CompraED ed = new Orcamento_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setoid_Solicitacao_Compra (Long.parseLong (oid_Solicitacao_Compra));
    }

    return new Orcamento_CompraRN ().Lista (ed);

  }

  public ArrayList Lista (String oid_Orcamento_Conta) throws Excecoes {

    Orcamento_CompraED ed = new Orcamento_CompraED ();

    ed.setoid_Orcamento_Conta(oid_Orcamento_Conta);

    return new Orcamento_CompraRN ().Lista (ed);

  }

}
