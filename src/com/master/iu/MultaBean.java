package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.MultaED;
import com.master.rn.MultaRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;

public class MultaBean {
  Utilitaria util = new Utilitaria ();

  public void altera (HttpServletRequest request) throws Excecoes {
    String NR_Odometro_Inicial = request.getParameter ("FT_NR_Odometro_Inicial");
    String NM_Local = request.getParameter ("FT_NM_Local");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String HR_Multa = request.getParameter ("FT_HR_Multa");
    String VL_Multa = request.getParameter ("FT_VL_Multa");
    MultaED ed = new MultaED ();

    String oid_Tipo_Ocorrencia = request.getParameter ("oid_Tipo_Ocorrencia");
    if (String.valueOf (oid_Tipo_Ocorrencia) != null &&
        !String.valueOf (oid_Tipo_Ocorrencia).equals ("null") &&
        !String.valueOf (oid_Tipo_Ocorrencia).equals ("")) {
      ed.setOID_Tipo_Ocorrencia (new Long (request.getParameter ("oid_Tipo_Ocorrencia")).longValue ());
    }

    String DM_Culpa = request.getParameter ("FT_DM_Culpa");
    if (String.valueOf (DM_Culpa) != null &&
        !String.valueOf (DM_Culpa).equals ("null") &&
        !String.valueOf (DM_Culpa).equals ("")) {
      ed.setDM_Culpa (request.getParameter ("FT_DM_Culpa"));
    }

    ed.setOID_Multa (request.getParameter ("oid_Multa"));
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
    ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
    ed.setOID_Cidade (new Long (request.getParameter ("oid_Cidade")).longValue ());
    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    if (String.valueOf (NR_Odometro_Inicial) != null &&
        !String.valueOf (NR_Odometro_Inicial).equals ("null") &&
        !String.valueOf (NR_Odometro_Inicial).equals ("")) {
      ed.setNR_Odometro_Inicial (new Long (NR_Odometro_Inicial).longValue ());
    }
    if (String.valueOf (NM_Local) != null &&
        !String.valueOf (NM_Local).equals ("")) {
      ed.setNM_Local (NM_Local);
    }

    ed.setNM_Local (request.getParameter ("FT_NM_Local"));

    ed.setTX_Observacao ("");
    if (String.valueOf (TX_Observacao) != null &&
        !String.valueOf (TX_Observacao).equals ("null") &&
        !String.valueOf (TX_Observacao).equals ("")) {
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
    }
    ed.setHR_Multa ("");
    if (String.valueOf (HR_Multa) != null &&
        !String.valueOf (HR_Multa).equals ("null") &&
        !String.valueOf (HR_Multa).equals ("")) {
      ed.setHR_Multa (request.getParameter ("FT_HR_Multa"));
    }

    if (JavaUtil.doValida (VL_Multa)) {
      ed.setVL_Multa (Double.parseDouble (VL_Multa));
    }
    else {
      ed.setVL_Multa (0);
    }

    new MultaRN ().altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      MultaRN Multarn = new MultaRN ();
      MultaED ed = new MultaED ();

      ed.setOID_Multa (request.getParameter ("oid_Multa"));

      Multarn.deleta (ed);
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

  public ArrayList Multa_Lista (HttpServletRequest request) throws Excecoes {

    MultaED ed = new MultaED ();
//// System.out.println("1 ");

    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    if (String.valueOf (DT_Emissao) != null &&
        !String.valueOf (DT_Emissao).equals ("null") &&
        !String.valueOf (DT_Emissao).equals ("")) {
      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    }
    String nr_Multa = request.getParameter ("FT_NR_Multa");
    if (String.valueOf (nr_Multa) != null &&
        !String.valueOf (nr_Multa).equals ("") &&
        !String.valueOf (nr_Multa).equals ("null")) {
      ed.setNR_Multa (new Long (nr_Multa).longValue ());
    }

    String OID_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (OID_Pessoa) != null &&
        !String.valueOf (OID_Pessoa).equals ("null") &&
        !String.valueOf (OID_Pessoa).equals ("")) {
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
    }
    String OID_Veiculo = request.getParameter ("oid_Veiculo");
    if (String.valueOf (OID_Veiculo) != null &&
        !String.valueOf (OID_Veiculo).equals ("null") &&
        !String.valueOf (OID_Veiculo).equals ("")) {
      ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
//// System.out.println("5 ");

    return new MultaRN ().lista (ed);

  }


  public ArrayList lista_Parcela_Conta_Corrente (HttpServletRequest request) throws Excecoes {

    MultaED ed = new MultaED ();
//// System.out.println("1 ");

    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    if (String.valueOf (DT_Emissao) != null &&
        !String.valueOf (DT_Emissao).equals ("null") &&
        !String.valueOf (DT_Emissao).equals ("")) {
      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    }
//// System.out.println("5 ");
    ed.setOID_Multa (request.getParameter ("oid_Multa"));

    return new MultaRN ().lista_Parcela_Conta_Corrente (ed);

  }



  public MultaED inclui (HttpServletRequest request) throws Excecoes {
    MultaED ed = new MultaED ();

    String oid_Tipo_Ocorrencia = request.getParameter ("oid_Tipo_Ocorrencia");
    if (String.valueOf (oid_Tipo_Ocorrencia) != null &&
        !String.valueOf (oid_Tipo_Ocorrencia).equals ("null") &&
        !String.valueOf (oid_Tipo_Ocorrencia).equals ("")) {
      ed.setOID_Tipo_Ocorrencia (new Long (request.getParameter ("oid_Tipo_Ocorrencia")).longValue ());
    }

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));

    // System.out.println ("man 0");

    ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
    ed.setOID_Cidade (new Long (request.getParameter ("oid_Cidade")).longValue ());
    // System.out.println ("man 1 n2");

    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    String NR_Odometro_Inicial = request.getParameter ("FT_NR_Odometro_Inicial");
    if (String.valueOf (NR_Odometro_Inicial) != null &&
        !String.valueOf (NR_Odometro_Inicial).equals ("null") &&
        !String.valueOf (NR_Odometro_Inicial).equals ("")) {
      ed.setNR_Odometro_Inicial (new Long (NR_Odometro_Inicial).longValue ());
    }

    String NR_Multa = request.getParameter ("FT_NR_Multa");
    if (String.valueOf (NR_Multa) != null &&
        !String.valueOf (NR_Multa).equals ("null") &&
        !String.valueOf (NR_Multa).equals ("")) {
      ed.setNR_Multa (new Long (NR_Multa).longValue ());
    }

    // System.out.println ("man 4");

    String NM_Local = request.getParameter ("FT_NM_Local");
    if (String.valueOf (NM_Local) != null &&
        !String.valueOf (NM_Local).equals ("")) {
      ed.setNM_Local (NM_Local);
    }

    // System.out.println ("man 8");

    ed.setTX_Observacao (" ");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    if (String.valueOf (TX_Observacao) != null &&
        !String.valueOf (TX_Observacao).equals ("null") &&
        !String.valueOf (TX_Observacao).equals ("")) {
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
    }
    ed.setHR_Multa (" ");
    String HR_Multa = request.getParameter ("FT_HR_Multa");
    if (String.valueOf (HR_Multa) != null &&
        !String.valueOf (HR_Multa).equals ("null") &&
        !String.valueOf (HR_Multa).equals ("")) {
      ed.setHR_Multa (request.getParameter ("FT_HR_Multa"));
    }

    // System.out.println ("man 12");

    String DM_Culpa = request.getParameter ("FT_DM_Culpa");
    if (String.valueOf (DM_Culpa) != null &&
        !String.valueOf (DM_Culpa).equals ("null") &&
        !String.valueOf (DM_Culpa).equals ("")) {
      ed.setDM_Culpa (request.getParameter ("FT_DM_Culpa"));
    }

    String VL_Multa = request.getParameter ("FT_VL_Multa");
    if (JavaUtil.doValida (VL_Multa)) {
      ed.setVL_Multa (Double.parseDouble (VL_Multa));
    }
    else {
      ed.setVL_Multa (0);
    }
    // System.out.println ("man 13");
    return new MultaRN ().inclui (ed);
  }

  public MultaED inclui_Parcela_Conta_Corrente (HttpServletRequest request) throws Excecoes {
    MultaED ed = new MultaED ();

    ed.setOID_Multa (request.getParameter ("oid_Multa"));
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
    ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));

    String NR_Multa = request.getParameter ("FT_NR_Multa");
    if (String.valueOf (NR_Multa) != null &&
        !String.valueOf (NR_Multa).equals ("null") &&
        !String.valueOf (NR_Multa).equals ("")) {
      ed.setNR_Multa (new Long (NR_Multa).longValue ());
    }
    ed.setDT_Parcela (request.getParameter ("FT_DT_Parcela"));


    String VL_Parcela = request.getParameter ("FT_VL_Parcela");
    if (JavaUtil.doValida (VL_Parcela)) {
      ed.setVL_Parcela (Double.parseDouble (VL_Parcela));
    }
    // System.out.println ("man 13");
    return new MultaRN ().inclui_Parcela_Conta_Corrente (ed);
  }


  public MultaED getByRecord (HttpServletRequest request) throws Excecoes {

    MultaED ed = new MultaED ();

    String NR_Multa = request.getParameter ("FT_NR_Multa");
    if (String.valueOf (NR_Multa) != null &&
        !String.valueOf (NR_Multa).equals ("") &&
        !String.valueOf (NR_Multa).equals ("null")) {
      ed.setNR_Multa (new Long (request.getParameter ("FT_NR_Multa")).longValue ());
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    }

    String oid_Multa = request.getParameter ("oid_Multa");

    if (String.valueOf (oid_Multa) != null &&
        !String.valueOf (oid_Multa).equals ("") &&
        !String.valueOf (oid_Multa).equals ("null")) {
      ed.setOID_Multa (request.getParameter ("oid_Multa"));
    }

    if (request.getParameter ("MIC") != null && request.getParameter ("MIC").equals ("S")) {
      return new MultaRN ().getByRecord (ed);
    }
    else {
      return new MultaRN ().getByRecord (ed);
    }

  }

  public byte[] gera_Relatorio_Multa (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    String NR_Multa = request.getParameter ("FT_NR_Multa");
    String OID_Cidade = request.getParameter ("oid_Cidade_Destino");
    String OID_Empresa = request.getParameter ("oid_Empresa");
    String OID_Veiculo = request.getParameter ("FT_NR_Placa");
    String OID_Unidade = request.getParameter ("oid_Unidade");
    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String OID_Pessoa = request.getParameter ("oid_Pessoa");
    String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");

    MultaED ed = new MultaED ();

    if (JavaUtil.doValida (NR_Multa)) {
      ed.setNR_Multa (new Long (NR_Multa).longValue ());
    }
    if (JavaUtil.doValida (OID_Cidade)) {
      ed.setOID_Cidade (new Long (OID_Cidade).longValue ());
    }

    if (JavaUtil.doValida (OID_Empresa)) {
      ed.setOID_Empresa (new Long (OID_Empresa).longValue ());
    }

    if (JavaUtil.doValida (OID_Unidade)) {
      ed.setOID_Unidade (new Long (OID_Unidade).longValue ());
    }

    if (JavaUtil.doValida (DT_Emissao_Inicial)) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }
    if (JavaUtil.doValida (DT_Emissao_Final)) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }
    if (JavaUtil.doValida (OID_Pessoa)) {
      ed.setOID_Pessoa (OID_Pessoa);
    }
    if (JavaUtil.doValida (OID_Veiculo)) {
      ed.setOID_Veiculo (OID_Veiculo);
    }
    if (JavaUtil.doValida (DM_Relatorio)) {
      ed.setDM_Relatorio (DM_Relatorio);
    }
    if (util.doValida (oid_Pessoa_Destinatario)) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }

    MultaRN manRN = new MultaRN ();
    byte[] b = manRN.gera_Relatorio_Multa (ed);

    return b;

  }

}
