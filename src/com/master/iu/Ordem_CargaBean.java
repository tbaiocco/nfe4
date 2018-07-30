package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.ed.Ordem_CargaED;
import com.master.rn.Ordem_CargaRN;
import com.master.util.*;

public class Ordem_CargaBean {

  Utilitaria util = new Utilitaria ();

  public Ordem_CargaED inclui (HttpServletRequest request) throws Excecoes {
    Ordem_CargaED ed = new Ordem_CargaED ();

    ed = this.carregaED (request);
    ed.setDM_Situacao ("1");

    return new Ordem_CargaRN ().inclui (ed);
  }

  public Ordem_CargaED getByRecord (HttpServletRequest request) throws Excecoes {
    Ordem_CargaED ed = new Ordem_CargaED ();

    String NR_Ordem_Carga = request.getParameter ("FT_NR_Ordem_Carga");
    if (util.doValida (NR_Ordem_Carga)) {
      ed.setNR_Ordem_Carga (NR_Ordem_Carga);
    }
    String oid_Ordem_Carga = request.getParameter ("oid_Ordem_Carga");
    if (util.doValida (oid_Ordem_Carga)) {
      ed.setOID_Ordem_Carga (oid_Ordem_Carga);
    }
    return new Ordem_CargaRN ().getByRecord (ed);
  }

  public Ordem_CargaED getByRecord (String oid_Ordem_Carga) throws Excecoes {
    Ordem_CargaED ed = new Ordem_CargaED ();

    ed.setOID_Ordem_Carga (oid_Ordem_Carga);
    return new Ordem_CargaRN ().getByRecord (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    Ordem_CargaED ed = new Ordem_CargaED ();
    ed = this.carregaED (request);

    Ordem_CargaED edVolta = new Ordem_CargaED ();
    edVolta = new Ordem_CargaRN ().getByRecord (ed);

    new Ordem_CargaRN ().altera (ed);
//// System.out.println("veic novo:"+ed.getOID_Veiculo());
//// System.out.println("veic velho:"+edVolta.getOID_Veiculo());
    if (!JavaUtil.doValida (edVolta.getOID_Veiculo ())) {
      edVolta.setOID_Veiculo ("");
    }
  }

  public void cancela (HttpServletRequest request) throws Excecoes {

    Ordem_CargaED ed = new Ordem_CargaED ();
    String oid_Ordem_Carga = request.getParameter ("oid_Ordem_Carga");
    if (util.doValida (oid_Ordem_Carga)) {
      ed.setOID_Ordem_Carga (oid_Ordem_Carga);
    }
    new Ordem_CargaRN ().cancela (ed);

  }

  public void gera_Compromisso (HttpServletRequest request) throws Excecoes {
    Ordem_CargaED ed = new Ordem_CargaED ();
    ed = this.carregaED (request);
    new Ordem_CargaRN ().gera_Compromisso (ed);
  }

  public ArrayList Lista_Ordem_Carga (HttpServletRequest request) throws Excecoes {

    Ordem_CargaED ed = new Ordem_CargaED ();

    ed = this.carregaED (request);

    return new Ordem_CargaRN ().Lista_Ordem_Carga (ed);

  }

  public byte[] imprime_Ordem_Carga_Descarga (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Ordem_CargaED ed = new Ordem_CargaED ();

    ed.setOID_Ordem_Carga (request.getParameter ("oid_Ordem_Carga"));
    String DM_Tipo = request.getParameter ("FT_DM_Tipo");
    if (util.doValida (DM_Tipo)) {
      ed.setDM_Tipo (DM_Tipo);
    }
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    Ordem_CargaRN nfRN = new Ordem_CargaRN ();
    byte[] b = nfRN.imprime_Ordem_Carga_Descarga (ed);

    return b;

  }

  public byte[] gera_Rel_Carga_Descarga (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Ordem_CargaED ed = new Ordem_CargaED ();

    ed = this.carregaED (request);

    Ordem_CargaRN nfRN = new Ordem_CargaRN ();
    byte[] b = nfRN.gera_Rel_Carga_Descarga (ed);

    return b;

  }

  public Ordem_CargaED carregaED (HttpServletRequest request) throws Excecoes {

    Ordem_CargaED ed = new Ordem_CargaED ();

    String oid_Ordem_Carga = request.getParameter ("oid_Ordem_Carga");
    if (util.doValida (oid_Ordem_Carga)) {
      ed.setOID_Ordem_Carga (oid_Ordem_Carga);
    }

    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }

    String DM_Tipo = request.getParameter ("FT_DM_Tipo");
    if (util.doValida (DM_Tipo)) {
      ed.setDM_Tipo (DM_Tipo);
    }

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (util.doValida (DT_Emissao_Inicial)) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (util.doValida (DT_Emissao_Final)) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }

    String NR_Ordem_Carga = request.getParameter ("FT_NR_Ordem_Carga");
    if (util.doValida (NR_Ordem_Carga)) {
      ed.setNR_Ordem_Carga (NR_Ordem_Carga);
    }

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (util.doValida (NR_Nota_Fiscal)) {
      ed.setNR_Nota_Fiscal (NR_Nota_Fiscal);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (util.doValida (oid_Unidade)) {
      ed.setOID_Unidade (new Integer (oid_Unidade).intValue ());
    }

    String oid_Deposito = request.getParameter ("oid_Deposito");
    if (util.doValida (oid_Deposito)) {
      ed.setOID_Deposito (new Integer (oid_Deposito).intValue ());
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (util.doValida (oid_Pessoa)) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String oid_Motorista = request.getParameter ("oid_Motorista");
    if (util.doValida (oid_Motorista)) {
      ed.setOID_Motorista (oid_Motorista);
    }
    else
      oid_Motorista = request.getParameter ("oid_Pessoa_Motorista");
    if (util.doValida (oid_Motorista)) {
      ed.setOID_Motorista (oid_Motorista);
    }


    String DT_Ordem_Carga = request.getParameter ("FT_DT_Ordem_Carga");
    if (util.doValida (DT_Ordem_Carga)) {
      ed.setDT_Ordem_Carga (DT_Ordem_Carga);
    }

    String HR_Ordem_Carga = request.getParameter ("FT_HR_Ordem_Carga");
    if (util.doValida (HR_Ordem_Carga)) {
      ed.setHR_Ordem_Carga (HR_Ordem_Carga);
    }

    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    ed.setTX_Observacao ("");
    if (TX_Observacao != null && !TX_Observacao.equals ("") && !TX_Observacao.equals ("null")) {
      ed.setTX_Observacao (TX_Observacao);
    }

    String NR_Pedido = request.getParameter ("FT_NR_Pedido");
    ed.setNR_Pedido ("");
    if (NR_Pedido != null && !NR_Pedido.equals ("") && !NR_Pedido.equals ("null")) {
      ed.setNR_Pedido (NR_Pedido);
    }
    String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");
    if (util.doValida (DM_Relatorio)) {
      ed.setDM_Relatorio (DM_Relatorio);
    }

    String VL_Custo = request.getParameter ("FT_VL_Custo");
    if (util.doValida (VL_Custo)) {
      ed.setVL_Custo (new Double (VL_Custo).doubleValue ());
    }

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    if (oid_Veiculo != null && !oid_Veiculo.equals ("") && !oid_Veiculo.equals ("null")) {
      ed.setOID_Veiculo (oid_Veiculo);
    }
    return ed;
  }

  public Ordem_CargaED getByOID (String oid_Ordem_Carga) throws Excecoes {
    Ordem_CargaED ed = new Ordem_CargaED ();

    if (util.doValida (oid_Ordem_Carga)) {
      ed.setOID_Ordem_Carga (oid_Ordem_Carga);
    }
    return new Ordem_CargaRN ().getByRecord (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    Ordem_CargaED ed = new Ordem_CargaED ();
    ed = this.carregaED (request);
    new Ordem_CargaRN ().deleta (ed);
  }

}
