package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Movimento_ConhecimentoED;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class con007Bean {

	
  public Movimento_ConhecimentoED inclui (HttpServletRequest request) throws Excecoes {
    String DM_Nacional_Internacional = request.getParameter ("FT_DM_Nacional_Internacional");

    Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    String oid_Cotacao = request.getParameter ("oid_Cotacao");
    String oid_Usuario = request.getParameter ("oid_Usuario");

    ed.setOID_Conhecimento ("");
    if (JavaUtil.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }

    ed.setOID_Cotacao ("");
    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }

    ed.setDT_Movimento_Conhecimento (request.getParameter ("FT_DT_Movimento_Conhecimento"));
    ed.setHR_Movimento_Conhecimento (request.getParameter ("FT_HR_Movimento_Conhecimento"));
    ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());

    ed.setDM_Tipo_Movimento (request.getParameter ("FT_DM_Tipo_Movimento"));
    ed.setDM_Lancado_Gerado ("L");
    ed.setNM_Pessoa_Entrega (request.getParameter ("FT_NM_Pessoa_Entrega"));
    ed.setVL_Movimento (new Double (request.getParameter ("FT_VL_Movimento")).doubleValue ());

    if (request.getParameter ("FT_VL_Tarifa") != null) {
      ed.setVL_Tarifa (new Double (request.getParameter ("FT_VL_Tarifa")).doubleValue ());
    }
    ed.setOID_Fornecedor (request.getParameter ("oid_Pessoa_Fornecedor"));

    if (JavaUtil.doValida (DM_Nacional_Internacional)) {
      ed.setDM_Nacional_Internacional (DM_Nacional_Internacional);
    }

    if (JavaUtil.doValida (oid_Usuario)) {
        ed.setOID_Usuario(new Long (oid_Usuario).longValue ());
    }

    ed.setTX_Observacao (" ");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    if (JavaUtil.doValida (TX_Observacao)) {
      ed.setTX_Observacao (TX_Observacao);
    }

    return new Movimento_ConhecimentoRN ().inclui (ed);
  }

  public Movimento_ConhecimentoED recalculaMargem (HttpServletRequest request) throws Excecoes {

      Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
      Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

      //request em cima dos campos dos forms html

      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("") &&
          !String.valueOf (oid_Unidade).equals ("null")) {
        ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
      }

      ed.setDT_Movimento_Inicial (request.getParameter ("FT_DT_Emissao_Inicial"));
      ed.setDT_Movimento_Final (request.getParameter ("FT_DT_Emissao_Final"));

      return Movimento_Conhecimentorn.recalculaMargem (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

      Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
      Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

      ed.setOID_Movimento_Conhecimento (request.getParameter ("oid_Movimento_Conhecimento"));
      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());
      ed.setDT_Movimento_Conhecimento (request.getParameter ("FT_DT_Movimento_Conhecimento"));
      ed.setHR_Movimento_Conhecimento (request.getParameter ("FT_HR_Movimento_Conhecimento"));
      ed.setDM_Tipo_Movimento (request.getParameter ("FT_DM_Tipo_Movimento"));
      ed.setNM_Pessoa_Entrega (request.getParameter ("FT_NM_Pessoa_Entrega"));
      ed.setVL_Movimento (new Double (request.getParameter ("FT_VL_Movimento")).doubleValue ());
      ed.setOID_Fornecedor (request.getParameter ("oid_Pessoa_Fornecedor"));
      ed.setTX_Observacao (" ");
      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (JavaUtil.doValida (TX_Observacao)) {
        ed.setTX_Observacao (TX_Observacao);
      }

// System.out.println("altera 1");      
      Movimento_Conhecimentorn.altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

      Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
      Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

      ed.setOID_Movimento_Conhecimento (request.getParameter ("oid_Movimento_Conhecimento"));
      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      Movimento_Conhecimentorn.deleta (ed);
  }

  public void calcula_Margem (HttpServletRequest request) throws Excecoes {

      Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
      Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      Movimento_Conhecimentorn.calcula_Margem (ed);
  }

  public Movimento_ConhecimentoED inclui_Rateio (HttpServletRequest request) throws Excecoes {

      Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
      Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();
      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

      ed.setDT_Movimento_Inicial (request.getParameter ("FT_DT_Movimento_Inicial"));
      ed.setDT_Movimento_Final (request.getParameter ("FT_DT_Movimento_Final"));
      ed.setDM_Tipo_Rateio (request.getParameter ("FT_DM_Tipo_Rateio"));
      ed.setDM_Tipo_Movimento (request.getParameter ("FT_DM_Tipo_Movimento"));
      ed.setDM_Lancado_Gerado ("L");
      ed.setVL_Rateio (new Double (request.getParameter ("FT_VL_Movimento")).doubleValue ());

      return Movimento_Conhecimentorn.inclui_Rateio (ed);
  }

  public Movimento_ConhecimentoED deleta_Rateio (HttpServletRequest request) throws Excecoes {
      Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
      Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();
      ed.setOID_Tipo_Movimento (new Long (request.getParameter ("oid_Tipo_Movimento")).longValue ());
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

      ed.setDT_Movimento_Inicial (request.getParameter ("FT_DT_Movimento_Inicial"));
      ed.setDT_Movimento_Final (request.getParameter ("FT_DT_Movimento_Final"));
      ed.setDM_Tipo_Movimento (request.getParameter ("FT_DM_Tipo_Movimento"));
      ed.setDM_Lancado_Gerado ("L");

      return Movimento_Conhecimentorn.deleta_Rateio (ed);
  }

  public ArrayList Movimento_Conhecimento_Lista (HttpServletRequest request) throws Excecoes {
    String DM_Nacional_Internacional = request.getParameter ("FT_DM_Nacional_Internacional");
    String OID_Tipo_Movimento = request.getParameter ("oid_Tipo_Movimento");
    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    String oid_Cotacao = request.getParameter ("oid_Cotacao");
    String oid_Movimento_Cotacao = request.getParameter ("oid_Movimento_Cotacao");

    Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

    if (String.valueOf (OID_Tipo_Movimento) != null &&
        !String.valueOf (OID_Tipo_Movimento).equals ("") &&
        !String.valueOf (OID_Tipo_Movimento).equals ("null")) {
      ed.setOID_Tipo_Movimento (new Long (OID_Tipo_Movimento).longValue ());
    }
    ed.setDT_Movimento_Conhecimento (request.getParameter ("FT_DT_Movimento_Conhecimento"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    if (String.valueOf (nr_Conhecimento) != null &&
        !String.valueOf (nr_Conhecimento).equals ("") &&
        !String.valueOf (nr_Conhecimento).equals ("null")) {
      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
    }
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));

    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    if (JavaUtil.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    if (JavaUtil.doValida (oid_Movimento_Cotacao)) {
      ed.setOID_Movimento_Cotacao (oid_Movimento_Cotacao);
    }
    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }
    if ("I".equals (DM_Nacional_Internacional)) {
      ed.setDM_Nacional_Internacional (DM_Nacional_Internacional);
    }
    else {
      ed.setDM_Nacional_Internacional ("N");
    }

    return new Movimento_ConhecimentoRN ().lista (ed);
  }

  public ArrayList Movimento_Conhecimento_Lista (String oid_Conhecimento) throws Excecoes {
    Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();
    if (JavaUtil.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    ed.setDM_Nacional_Internacional ("N");
    return new Movimento_ConhecimentoRN ().lista (ed);
  }

  public ArrayList lista_Movimento_Cotacao (String oid_Movimento_Cotacao , String oid_Cotacao) throws Excecoes {

    Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();
    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }
    if (JavaUtil.doValida (oid_Movimento_Cotacao)) {
      ed.setOID_Movimento_Cotacao (oid_Movimento_Cotacao);
    }

    return new Movimento_ConhecimentoRN ().lista (ed);
  }

  public Movimento_ConhecimentoED getByRecord (HttpServletRequest request) throws Excecoes {

    Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

    String oid_Movimento_Conhecimento = request.getParameter ("oid_Movimento_Conhecimento");
    if (String.valueOf (oid_Movimento_Conhecimento) != null &&
        !String.valueOf (oid_Movimento_Conhecimento).equals ("") &&
        !String.valueOf (oid_Movimento_Conhecimento).equals ("null")) {
      ed.setOID_Movimento_Conhecimento (oid_Movimento_Conhecimento);
    }

    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    if (String.valueOf (oid_Conhecimento) != null &&
        !String.valueOf (oid_Conhecimento).equals ("") &&
        !String.valueOf (oid_Conhecimento).equals ("null")) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }

    String oid_Tipo_Movimento = request.getParameter ("oid_Tipo_Movimento");
    if (String.valueOf (oid_Tipo_Movimento) != null &&
        !String.valueOf (oid_Tipo_Movimento).equals ("") &&
        !String.valueOf (oid_Tipo_Movimento).equals ("null")) {
      ed.setOID_Tipo_Movimento (new Long (oid_Tipo_Movimento).longValue ());
    }

    return new Movimento_ConhecimentoRN ().getByRecord (ed);

  }

  public Movimento_ConhecimentoED consulta_Cotacao (HttpServletRequest request) throws Excecoes {

    Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

    String oid_Movimento_Conhecimento = request.getParameter ("oid_Movimento_Conhecimento");
    if (String.valueOf (oid_Movimento_Conhecimento) != null &&
        !String.valueOf (oid_Movimento_Conhecimento).equals ("") &&
        !String.valueOf (oid_Movimento_Conhecimento).equals ("null")) {
      ed.setOID_Movimento_Conhecimento (oid_Movimento_Conhecimento);
    }

    String oid_Cotacao = request.getParameter ("oid_Cotacao");
    if (String.valueOf (oid_Cotacao) != null &&
        !String.valueOf (oid_Cotacao).equals ("") &&
        !String.valueOf (oid_Cotacao).equals ("null")) {
      ed.setOID_Cotacao (oid_Cotacao);
    }

    String oid_Tipo_Movimento = request.getParameter ("oid_Tipo_Movimento");
    if (String.valueOf (oid_Tipo_Movimento) != null &&
        !String.valueOf (oid_Tipo_Movimento).equals ("") &&
        !String.valueOf (oid_Tipo_Movimento).equals ("null")) {
      ed.setOID_Tipo_Movimento (new Long (oid_Tipo_Movimento).longValue ());
    }

    return new Movimento_ConhecimentoRN ().consulta_Cotacao (ed);

  }

  public void geraRelatorio (HttpServletRequest req) throws Excecoes {
    Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
         /* SETAR O ED PARA PESQUISA NO BD
      */


     Movimento_ConhecimentoRN geRN = new Movimento_ConhecimentoRN ();
    geRN.geraRelatorio (ed);
  }

}
