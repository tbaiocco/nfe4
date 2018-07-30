package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Movimento_CompromissoED;
import com.master.ed.Movimento_CompromissoPesquisaED;
import com.master.rn.Movimento_CompromissoRN;
import com.master.util.Excecoes;

public class Cpg003Bean {

  public Movimento_CompromissoED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Movimento_CompromissoRN movimento__CompromissoRN = new Movimento_CompromissoRN ();
      Movimento_CompromissoED ed = new Movimento_CompromissoED ();

      ed.setOid_Compromisso (new Integer (request.getParameter ("oid_Compromisso")));

      ed.setDt_Pagamento (request.getParameter ("FT_DT_Pagamento"));
      ed.setDt_Emissao (request.getParameter ("FT_DT_Emissao"));
      ed.setVl_Pagamento (new Double (request.getParameter ("FT_VL_Pagamento")));
      ed.setVl_Compromisso (new Double (request.getParameter ("FT_VL_Compromisso")));

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

      return movimento__CompromissoRN.inclui (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }

  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Movimento_CompromissoRN movimento__CompromissoRN = new Movimento_CompromissoRN ();
      Movimento_CompromissoED ed = new Movimento_CompromissoED ();

      ed.setOid_Compromisso (new Integer (request.getParameter ("oid_Compromisso")));
      ed.setOid_Mov_Compromisso (new Integer (request.getParameter ("oid_Mov_Compromisso")));

      ed.setDt_Pagamento (request.getParameter ("FT_DT_Pagamento"));
      ed.setDt_Emissao (request.getParameter ("FT_DT_Emissao"));
      ed.setVl_Pagamento (new Double (request.getParameter ("FT_VL_Pagamento")));
      ed.setVl_Compromisso (new Double (request.getParameter ("FT_VL_Compromisso")));

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

      movimento__CompromissoRN.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Movimento_CompromissoRN movimento__CompromissoRN = new Movimento_CompromissoRN ();
      Movimento_CompromissoED ed = new Movimento_CompromissoED ();

      ed.setOid_Mov_Compromisso (new Integer (request.getParameter ("oid_Mov_Compromisso")));

      movimento__CompromissoRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public ArrayList movimento_Compromisso_Lista (HttpServletRequest request) throws Excecoes {

    Movimento_CompromissoPesquisaED ed = new Movimento_CompromissoPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Compromisso = request.getParameter ("oid_Compromisso");
    String data_Inicial = request.getParameter ("FT_DT_Vencimento_Inicial");
    String data_Final = request.getParameter ("FT_DT_Vencimento_Final");
    String nr_Documento = request.getParameter ("FT_NR_Documento");

    if (oid_Compromisso != null && !oid_Compromisso.equals ("") && !oid_Compromisso.equals ("null")) {
      ed.setOid_Compromisso (new Integer (oid_Compromisso));
    }
    else {
      if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
        ed.setOid_Pessoa (oid_Pessoa);
      }

      if (data_Inicial != null && !data_Inicial.equals ("")) {
        ed.setDt_Pgto_Inicial (data_Inicial);
      }

      if (data_Final != null && !data_Final.equals ("")) {
        ed.setDt_Pgto_Final (data_Final);
      }

      if (nr_Documento != null && !nr_Documento.equals ("")) {
        ed.setNr_Documento (nr_Documento);
      }
    }

    return new Movimento_CompromissoRN ().lista (ed);

  }

  public Movimento_CompromissoED getByRecord (HttpServletRequest request) throws Excecoes {

    Movimento_CompromissoED ed = new Movimento_CompromissoED ();

    String oid_Mov_Compromisso = request.getParameter ("oid_Mov_Compromisso");
    if (oid_Mov_Compromisso != null && oid_Mov_Compromisso.length () > 0) {
      ed.setOid_Mov_Compromisso (new Integer (oid_Mov_Compromisso));
    }

    return new Movimento_CompromissoRN ().getByRecord (ed);

  }

  public Movimento_CompromissoED liquidaCompromissos (HttpServletRequest request) throws Excecoes {

    try {
      Movimento_CompromissoRN movimentoCompromissoRN = new Movimento_CompromissoRN ();
      Movimento_CompromissoED ed = new Movimento_CompromissoED ();
      ed.setDt_Pagamento (request.getParameter ("FT_DT_Pagamento"));
      ed.setNR_Compromissos(request.getParameter ("FT_NR_Compromissos"));

        // System.out.println("Compromissos =" + ed.getNR_Compromissos());
      
      return movimentoCompromissoRN.liquidaCompromissos (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }

  }

}
