package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Movimento_ChequeED;
import com.master.rn.Movimento_ChequeRN;
import com.master.util.Excecoes;

public class Movimento_ChequeBean {
  public Movimento_ChequeBean () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  public Movimento_ChequeED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Movimento_ChequeRN movimento_ChequeRN = new Movimento_ChequeRN ();
      Movimento_ChequeED ed = new Movimento_ChequeED ();

      ed.setOid_Cheque (new Integer (request.getParameter ("oid_Cheque")).intValue ());

      ed.setDt_Pagamento (request.getParameter ("FT_DT_Pagamento"));
      ed.setVl_Pagamento (new Double (request.getParameter ("FT_VL_Pagamento")));
      ed.setVl_Cheque (new Double (request.getParameter ("FT_VL_Cheque")).doubleValue ());
      ed.setVl_Saldo (new Double (request.getParameter ("FT_VL_Saldo")).doubleValue ());

      String vl_Multa_Pagamento = request.getParameter ("FT_VL_Multa_Pagamento");
      if (!vl_Multa_Pagamento.equals ("")) {
        ed.setVl_Multa_Pagamento (new Double (vl_Multa_Pagamento));
      }

      String vl_Outras_Despesas = request.getParameter ("FT_VL_Outras_Despesas");
      if (!vl_Outras_Despesas.equals ("")) {
        ed.setVl_Outras_Despesas (new Double (vl_Outras_Despesas));
      }

      String vl_Desconto = request.getParameter ("FT_VL_Desconto");
      if (!vl_Desconto.equals ("")) {
        ed.setVl_Desconto (new Double (vl_Desconto));
      }

      String obs = request.getParameter ("FT_TX_Observacao");
      if (!obs.equals ("")) {
        ed.setTx_Observacao (obs);
      }

      return movimento_ChequeRN.inclui (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }

  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Movimento_ChequeRN movimento_ChequeRN = new Movimento_ChequeRN ();
      Movimento_ChequeED ed = new Movimento_ChequeED ();

      ed.setOid_Mov_Cheque (new Integer (request.getParameter ("oid_Mov_Cheque")));

      ed.setDt_Pagamento (request.getParameter ("FT_DT_Pagamento"));
      ed.setVl_Pagamento (new Double (request.getParameter ("FT_VL_Pagamento")));
      ed.setVl_Cheque (new Double (request.getParameter ("FT_VL_Cheque")).doubleValue ());

      String vl_Multa_Pagamento = request.getParameter ("FT_VL_Multa_Pagamento");
      if (!vl_Multa_Pagamento.equals ("")) {
        ed.setVl_Multa_Pagamento (new Double (vl_Multa_Pagamento));
      }

      String vl_Outras_Despesas = request.getParameter ("FT_VL_Outras_Despesas");
      if (!vl_Outras_Despesas.equals ("")) {
        ed.setVl_Outras_Despesas (new Double (vl_Outras_Despesas));
      }

      String vl_Desconto = request.getParameter ("FT_VL_Desconto");
      if (!vl_Desconto.equals ("")) {
        ed.setVl_Desconto (new Double (vl_Desconto));
      }

      String obs = request.getParameter ("FT_TX_Observacao");
      if (!obs.equals ("")) {
        ed.setTx_Observacao (obs);
      }

      movimento_ChequeRN.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Movimento_ChequeRN movimento_ChequeRN = new Movimento_ChequeRN ();
      Movimento_ChequeED ed = new Movimento_ChequeED ();

      ed.setOid_Mov_Cheque (new Integer (request.getParameter ("oid_Mov_Cheque")));

      movimento_ChequeRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public ArrayList movimento_Cheque_Lista (HttpServletRequest request) throws Excecoes {

    Movimento_ChequeED ed = new Movimento_ChequeED ();

    String oid_Cheque = request.getParameter ("oid_Cheque");
    ed.setOid_Cheque (new Integer (oid_Cheque).intValue ());

    return new Movimento_ChequeRN ().lista (ed);

  }

  public Movimento_ChequeED getByRecord (HttpServletRequest request) throws Excecoes {

    Movimento_ChequeED ed = new Movimento_ChequeED ();

    String oid_Mov_Cheque = request.getParameter ("oid_Mov_Cheque");
    if (oid_Mov_Cheque != null && oid_Mov_Cheque.length () > 0) {
      ed.setOid_Mov_Cheque (new Integer (oid_Mov_Cheque));
    }

    return new Movimento_ChequeRN ().getByRecord (ed);

  }

  private void jbInit () throws Exception {
  }
}
