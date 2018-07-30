package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Nota_FiscalED;
import com.master.rn.Nota_FiscalRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.util.Data;


public class con003Bean {

  Utilitaria util = new Utilitaria ();

  ///peso cubado
  public Nota_FiscalED inclui (HttpServletRequest request) throws Excecoes {
    Nota_FiscalRN Nota_Fiscalrn = new Nota_FiscalRN ();
    Nota_FiscalED ed = new Nota_FiscalED ();
    // System.out.println ("inclui");

    String NM_Especie = request.getParameter ("FT_NM_Especie");

    //request em cima dos campos dos forms html

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (String.valueOf (NR_Nota_Fiscal) != null && !String.valueOf (NR_Nota_Fiscal).equals ("")
        && !String.valueOf (NR_Nota_Fiscal).equals ("null")) {
      ed.setNR_Nota_Fiscal (new Long (request.getParameter ("FT_NR_Nota_Fiscal")).longValue ());
    }
    else {
      ed.setNR_Nota_Fiscal (0);
    }

    String OID_Natureza_Operacao = request.getParameter ("oid_Natureza_Operacao");
    if (String.valueOf (OID_Natureza_Operacao) != null && !String.valueOf (OID_Natureza_Operacao).equals ("")
        && !String.valueOf (OID_Natureza_Operacao).equals ("null")) {
      ed.setOID_Natureza_Operacao (new Long (request.getParameter ("oid_Natureza_Operacao")).longValue ());
    }

    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOid_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }

    // System.out.println ("nf1");

    ed.setNM_Serie (request.getParameter ("FT_NM_Serie"));
    if ("".equals(ed.getNM_Serie())){
        ed.setNM_Serie ("1");
    }

    ed.setAcao(request.getParameter ("acao"));
    ed.setDM_Transferencia (request.getParameter ("FT_DM_Transferencia"));
    ed.setDM_Exportacao (request.getParameter ("FT_DM_Exportacao"));
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    ed.setDT_Entrada (request.getParameter ("FT_DT_Entrada"));
    ed.setNR_Pedido (request.getParameter ("FT_NR_Pedido"));
    ed.setNR_Codigo_Cliente (request.getParameter ("FT_NR_Codigo_Cliente"));

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    String NR_Peso = request.getParameter ("FT_NR_Peso");
    if (String.valueOf (NR_Peso) != null && !String.valueOf (NR_Peso).equals ("")
        && !String.valueOf (NR_Peso).equals ("null")) {
      ed.setNR_Peso (new Double (NR_Peso).doubleValue ());
    }

    String VL_Total_Frete = request.getParameter ("FT_VL_Total_Frete");
    if (String.valueOf (VL_Total_Frete) != null && !String.valueOf (VL_Total_Frete).equals ("")
        && !String.valueOf (VL_Total_Frete).equals ("null")) {
      ed.setVL_Total_Frete (new Double (VL_Total_Frete).doubleValue ());
    }

    // System.out.println ("FT_DM_Tabela: " + request.getParameter ("FT_DM_Tabela"));
    String DM_Tabela = request.getParameter ("FT_DM_Tabela");
    if (String.valueOf (DM_Tabela) != null && !String.valueOf (DM_Tabela).equals ("")
        && !String.valueOf (DM_Tabela).equals ("null")) {
      ed.setDM_Tabela (request.getParameter ("FT_DM_Tabela"));
    }
    else {
      ed.setDM_Tabela ("N");
    }

    // System.out.println ("nf2");

    String VL_Tabela = request.getParameter ("FT_VL_Tabela");
    if (String.valueOf (VL_Tabela) != null && !String.valueOf (VL_Tabela).equals ("")
        && !String.valueOf (VL_Tabela).equals ("null")) {
      ed.setVL_Tabela (new Double (VL_Tabela).doubleValue ());
    }

    // System.out.println ("nf4");

    String NR_Cubagem = request.getParameter ("FT_NR_Cubagem");
    if (String.valueOf (NR_Cubagem) != null && !String.valueOf (NR_Cubagem).equals ("")
        && !String.valueOf (NR_Cubagem).equals ("null")) {
      ed.setNR_Cubagem (new Double (NR_Cubagem).doubleValue ());
    }

    String NR_Cubagem_Total = request.getParameter ("FT_NR_Cubagem_Total");
    if (String.valueOf (NR_Cubagem_Total) != null && !String.valueOf (NR_Cubagem_Total).equals ("")
        && !String.valueOf (NR_Cubagem_Total).equals ("null")) {
      ed.setNR_Cubagem_Total (new Double (NR_Cubagem_Total).doubleValue ());
    }

    ed.setHR_Entrada ("");

    String HR_Entrada = request.getParameter ("FT_HR_Entrada");
    if (String.valueOf (HR_Entrada) != null && !String.valueOf (HR_Entrada).equals ("")
        && !String.valueOf (HR_Entrada).equals ("null")) {
      ed.setHR_Entrada (request.getParameter ("FT_HR_Entrada"));
    }

    ed.setDM_Tipo_Conhecimento ("1");
    String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    if (String.valueOf (DM_Tipo_Conhecimento) != null && !String.valueOf (DM_Tipo_Conhecimento).equals ("")
        && !String.valueOf (DM_Tipo_Conhecimento).equals ("null")) {
      ed.setDM_Tipo_Conhecimento (request.getParameter ("FT_DM_Tipo_Conhecimento"));
    }

    //ed.setNR_Peso_Cubado(ed.getNR_Peso());
    // System.out.println ("CUBADO!!! > " + request.getParameter ("FT_NR_Peso_Cubado"));
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    if (String.valueOf (NR_Peso_Cubado) != null && !String.valueOf (NR_Peso_Cubado).equals ("")
        && !String.valueOf (NR_Peso_Cubado).equals ("null")) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }

    String NR_Itens = request.getParameter ("FT_NR_Itens");
    if (String.valueOf (NR_Itens) != null && !String.valueOf (NR_Itens).equals ("")
        && !String.valueOf (NR_Itens).equals ("null")) {
      ed.setNR_Itens (new Double (NR_Itens).doubleValue ());
    }

    ed.setNR_Lote ("");
    String NR_Lote = request.getParameter ("FT_NR_Lote");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    // System.out.println ("nf11111");

    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    if (String.valueOf (NR_Volumes) != null && !String.valueOf (NR_Volumes).equals ("")
        && !String.valueOf (NR_Volumes).equals ("null")) {
      ed.setNR_Volumes (new Double (request.getParameter ("FT_NR_Volumes")).doubleValue ());
    }

    String VL_Nota_Fiscal = request.getParameter ("FT_VL_Nota_Fiscal");
    if (String.valueOf (VL_Nota_Fiscal) != null && !String.valueOf (VL_Nota_Fiscal).equals ("")
        && !String.valueOf (VL_Nota_Fiscal).equals ("null")) {
      ed.setVL_Nota_Fiscal (new Double (VL_Nota_Fiscal).doubleValue ());
    }
    ///###
    // System.out.println ("nf2 again");

    String OID_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (OID_Produto) != null && !String.valueOf (OID_Produto).equals ("")
        && !String.valueOf (OID_Produto).equals ("null")) {
      ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    }
    //// System.out.println("nf3");

    String oid_Pessoa_Consignatario = request.getParameter ("oid_Pessoa_Consignatario");
    if (String.valueOf (oid_Pessoa_Consignatario) != null && !String.valueOf (oid_Pessoa_Consignatario).equals ("")
        && !String.valueOf (oid_Pessoa_Consignatario).equals (null)
        && !String.valueOf (oid_Pessoa_Consignatario).equals ("null")) {
      ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    }
    else {
      ed.setOID_Pessoa_Consignatario ("");
    }

    //// System.out.println("nf4");

    String oid_Pessoa_Redespacho = request.getParameter ("oid_Pessoa_Redespacho");
    if (String.valueOf (oid_Pessoa_Redespacho) != null && !String.valueOf (oid_Pessoa_Redespacho).equals ("")
        && !String.valueOf (oid_Pessoa_Redespacho).equals (null)
        && !String.valueOf (oid_Pessoa_Redespacho).equals ("null")) {
      ed.setOID_Pessoa_Redespacho (request.getParameter ("oid_Pessoa_Redespacho"));
    }
    else {
      ed.setOID_Pessoa_Redespacho ("");
    }

    //// System.out.println("nf5");


    String OID_Coleta = request.getParameter ("oid_Coleta");
    if (String.valueOf (OID_Coleta) != null && !String.valueOf (OID_Coleta).equals ("")
        && !String.valueOf (OID_Coleta).equals ("null")) {
      ed.setOID_Coleta (new Long (request.getParameter ("oid_Coleta")).longValue ());
    }

    String OID_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("")
        && !String.valueOf (OID_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    }

    // System.out.println ("nf6");

    String OID_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (OID_Modal) != null && !String.valueOf (OID_Modal).equals ("")
        && !String.valueOf (OID_Modal).equals ("null")) {
      ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
    }

    // System.out.println ("nf7");

    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")
        && !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
    }
    else {
      ed.setDM_Tipo_Pagamento ("");
    }

    // System.out.println ("nf8");

    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    if (String.valueOf (TX_Observacao) != null && !String.valueOf (TX_Observacao).equals ("")
        && !String.valueOf (TX_Observacao).equals ("null")) {
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
    }
    else {
      ed.setTX_Observacao ("");
    }

    // System.out.println ("nf9");

    if (util.doValida (NM_Especie)) {
      ed.setNM_Especie (NM_Especie);
    }

    ed.setNR_CX1 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX1"))) {
      ed.setNR_CX1 (request.getParameter ("FT_NR_CX1"));
    }
    ed.setNR_CX2 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX2"))) {
      ed.setNR_CX2 (request.getParameter ("FT_NR_CX2"));
    }
    ed.setNR_CX3 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX3"))) {
      ed.setNR_CX3 (request.getParameter ("FT_NR_CX3"));
    }
    ed.setNR_CX4 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX4"))) {
      ed.setNR_CX4 (request.getParameter ("FT_NR_CX4"));
    }
    ed.setNR_CX5 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX5"))) {
      ed.setNR_CX5 (request.getParameter ("FT_NR_CX5"));
    }
    ed.setNR_CX6 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX6"))) {
      ed.setNR_CX6 (request.getParameter ("FT_NR_CX6"));
    }
    ed.setNR_CX7 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX7"))) {
      ed.setNR_CX7 (request.getParameter ("FT_NR_CX7"));
    }
    ed.setNR_CX8 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX8"))) {
      ed.setNR_CX8 (request.getParameter ("FT_NR_CX8"));
    }
    ed.setNR_CX9 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX9"))) {
      ed.setNR_CX9 (request.getParameter ("FT_NR_CX9"));
    }
    ed.setNR_CX10 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX10"))) {
      ed.setNR_CX10 (request.getParameter ("FT_NR_CX10"));
    }
    ed.setNR_CX11 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX11"))) {
      ed.setNR_CX11 (request.getParameter ("FT_NR_CX11"));
    }
    ed.setNR_CX12 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX12"))) {
      ed.setNR_CX12 (request.getParameter ("FT_NR_CX12"));
    }
    // System.out.println ("nf 10");

    return Nota_Fiscalrn.inclui (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {
    Nota_FiscalED ed = new Nota_FiscalED ();
    String oid_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");
    String oid_Natureza_Operacao = request.getParameter ("oid_Natureza_Operacao");
    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    String NM_Serie = request.getParameter ("FT_NM_Serie");
    String DM_Transferencia = request.getParameter ("FT_DM_Transferencia");
    String DM_Exportacao = request.getParameter ("FT_DM_Exportacao");
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    String oid_Pessoa_Consignatario = request.getParameter ("oid_Pessoa_Consignatario");
    String oid_Pessoa_Redespacho = request.getParameter ("oid_Pessoa_Redespacho");
    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    String DT_Entrada = request.getParameter ("FT_DT_Entrada");
    String HR_Entrada = request.getParameter ("FT_HR_Entrada");
    String NR_Pedido = request.getParameter ("FT_NR_Pedido");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String NR_Peso = request.getParameter ("FT_NR_Peso");
    String NR_Cubagem = request.getParameter ("FT_NR_Cubagem");
    String NR_Cubagem_Total = request.getParameter ("FT_NR_Cubagem_Total");
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    String VL_Nota_Fiscal = request.getParameter ("FT_VL_Nota_Fiscal");
    String oid_Produto = request.getParameter ("oid_Produto");
    String NR_Lote = request.getParameter ("FT_NR_Lote");
    String oid_Modal = request.getParameter ("oid_Modal");
    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String NR_Itens = request.getParameter ("FT_NR_Itens");
    String VL_Total_Frete = request.getParameter ("FT_VL_Total_Frete");
    String VL_Tabela = request.getParameter ("FT_VL_Tabela");
    String NM_Especie = request.getParameter ("FT_NM_Especie");

    // System.out.println ("NF ALTERA IU");

    if (util.doValida (oid_Nota_Fiscal)) {
      ed.setOID_Nota_Fiscal (oid_Nota_Fiscal);
    }
    if (util.doValida (oid_Natureza_Operacao)) {
      ed.setOID_Natureza_Operacao (Long.parseLong (oid_Natureza_Operacao));
    }
    if (util.doValida (NR_Nota_Fiscal)) {
      ed.setNR_Nota_Fiscal (Long.parseLong (NR_Nota_Fiscal));
    }
    if (util.doValida (NM_Serie)) {
      ed.setNM_Serie (NM_Serie);
    }
    if (util.doValida (DM_Transferencia)) {
      ed.setDM_Transferencia (DM_Transferencia);
    }
    if (util.doValida (DM_Exportacao)) {
      ed.setDM_Exportacao (DM_Exportacao);
    }
    if (util.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (util.doValida (oid_Pessoa_Destinatario)) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    if (util.doValida (oid_Pessoa_Consignatario)) {
      ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    }
    if (util.doValida (oid_Pessoa_Redespacho)) {
      ed.setOID_Pessoa_Redespacho (request.getParameter ("oid_Pessoa_Redespacho"));
    }
    if (util.doValida (DT_Emissao)) {
      ed.setDT_Emissao (DT_Emissao);
    }
    if (util.doValida (DT_Entrada)) {
      ed.setDT_Entrada (DT_Entrada);
    }
    if (util.doValida (HR_Entrada)) {
      ed.setHR_Entrada (HR_Entrada);
    }
    if (util.doValida (NR_Pedido)) {
      ed.setNR_Pedido (NR_Pedido);
    }
    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }
    if (util.doValida (NR_Peso)) {
      ed.setNR_Peso (Double.parseDouble (NR_Peso));
    }
    if (util.doValida (NR_Cubagem)) {
      ed.setNR_Cubagem (Double.parseDouble (NR_Cubagem));
    }
    if (util.doValida (NR_Cubagem_Total)) {
      ed.setNR_Cubagem_Total (Double.parseDouble (NR_Cubagem_Total));
    }
    if (util.doValida (NR_Peso_Cubado)) {
      ed.setNR_Peso_Cubado (Double.parseDouble (NR_Peso_Cubado));
    }
    if (util.doValida (NR_Volumes)) {
      ed.setNR_Volumes (Double.parseDouble (NR_Volumes));
    }
    if (util.doValida (VL_Nota_Fiscal)) {
      ed.setVL_Nota_Fiscal (Double.parseDouble (VL_Nota_Fiscal));
    }
    if (util.doValida (oid_Produto)) {
      ed.setOID_Produto (Long.parseLong (oid_Produto));
    }
    if (util.doValida (NR_Lote)) {
      ed.setNR_Lote (NR_Lote);
    }
    if (util.doValida (oid_Modal)) {
      ed.setOID_Modal (Long.parseLong (oid_Modal));
    }
    if (util.doValida (DM_Tipo_Pagamento)) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }
    else {
      ed.setDM_Tipo_Pagamento ("");
    }
    if (util.doValida (TX_Observacao)) {
      ed.setTX_Observacao (TX_Observacao);
    }
    if (util.doValida (NR_Itens)) {
      ed.setNR_Itens (Double.parseDouble (NR_Itens));
    }
    if (util.doValida (VL_Total_Frete)) {
      ed.setVL_Total_Frete (Double.parseDouble (VL_Total_Frete));
    }
    if (util.doValida (VL_Tabela)) {
      ed.setVL_Tabela (Double.parseDouble (VL_Tabela));
    }
    if (util.doValida (NM_Especie)) {
      ed.setNM_Especie (NM_Especie);
    }

    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOid_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }
    ed.setNR_Codigo_Cliente (request.getParameter ("FT_NR_Codigo_Cliente"));

    ed.setNR_CX1 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX1"))) {
      ed.setNR_CX1 (request.getParameter ("FT_NR_CX1"));
    }
    ed.setNR_CX2 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX2"))) {
      ed.setNR_CX2 (request.getParameter ("FT_NR_CX2"));
    }
    ed.setNR_CX3 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX3"))) {
      ed.setNR_CX3 (request.getParameter ("FT_NR_CX3"));
    }
    ed.setNR_CX4 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX4"))) {
      ed.setNR_CX4 (request.getParameter ("FT_NR_CX4"));
    }
    ed.setNR_CX5 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX5"))) {
      ed.setNR_CX5 (request.getParameter ("FT_NR_CX5"));
    }
    ed.setNR_CX6 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX6"))) {
      ed.setNR_CX6 (request.getParameter ("FT_NR_CX6"));
    }
    ed.setNR_CX7 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX7"))) {
      ed.setNR_CX7 (request.getParameter ("FT_NR_CX7"));
    }
    ed.setNR_CX8 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX8"))) {
      ed.setNR_CX8 (request.getParameter ("FT_NR_CX8"));
    }
    ed.setNR_CX9 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX9"))) {
      ed.setNR_CX9 (request.getParameter ("FT_NR_CX9"));
    }
    ed.setNR_CX10 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX10"))) {
      ed.setNR_CX10 (request.getParameter ("FT_NR_CX10"));
    }
    ed.setNR_CX11 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX11"))) {
      ed.setNR_CX11 (request.getParameter ("FT_NR_CX11"));
    }
    ed.setNR_CX12 (" ");
    if (util.doValida (request.getParameter ("FT_NR_CX12"))) {
      ed.setNR_CX12 (request.getParameter ("FT_NR_CX12"));
    }

    new Nota_FiscalRN ().altera (ed);
  }

  public void confirma_Descarga_Deposito (HttpServletRequest request) throws Excecoes {
    Nota_FiscalED ed = new Nota_FiscalED ();
    String oid_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");
    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Veiculo = request.getParameter ("oid_Veiculo");

    if (util.doValida (oid_Nota_Fiscal)) {
      ed.setOID_Nota_Fiscal (oid_Nota_Fiscal);
    }
    if (util.doValida (oid_Veiculo)) {
      ed.setOID_Veiculo (oid_Veiculo);
    }
    if (util.doValida (oid_Pessoa)) {
      ed.setOID_Motorista (oid_Pessoa);
    }
    String VL_Custo_Descarga = request.getParameter ("FT_VL_Custo_Descarga");
    if (util.doValida (VL_Custo_Descarga)) {
      ed.setVL_Custo_Descarga (Double.parseDouble (VL_Custo_Descarga));
    }

    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOid_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }

    new Nota_FiscalRN ().confirma_Descarga_Deposito (ed);
  }

  public void confirma_Carga_Descarga_Deposito (HttpServletRequest request) throws Excecoes {
    Nota_FiscalED ed = new Nota_FiscalED ();
    String oid_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");
    String oid_Ordem_Carga = request.getParameter ("oid_Ordem_Carga");
    String DM_Tipo = request.getParameter ("FT_DM_Tipo");

    if (util.doValida (DM_Tipo)) {
      ed.setDM_Carga_Descarga (DM_Tipo);
    }

    if (util.doValida (oid_Ordem_Carga)) {
      ed.setOID_Ordem_Carga (new Long (request.getParameter ("oid_Ordem_Carga")).longValue ());
    }
    if (util.doValida (oid_Nota_Fiscal)) {
      ed.setOID_Nota_Fiscal (oid_Nota_Fiscal);
    }

    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOid_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }

    new Nota_FiscalRN ().confirma_Carga_Descarga_Deposito (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Nota_FiscalRN Nota_Fiscalrn = new Nota_FiscalRN ();
      Nota_FiscalED ed = new Nota_FiscalED ();

      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));

      Nota_Fiscalrn.deleta (ed);
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

  public ArrayList Nota_Fiscal_Lista (HttpServletRequest request) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    String NR_Pedido = request.getParameter ("FT_NR_Pedido");
    if (String.valueOf (NR_Pedido) != null && !String.valueOf (NR_Pedido).equals ("") && !String.valueOf (NR_Pedido).equals ("null") && !String.valueOf (NR_Pedido).equals (null)) {
      ed.setNR_Pedido (NR_Pedido.trim());
    }

    String NR_Codigo_Cliente = request.getParameter ("FT_NR_Codigo_Cliente");
    if (String.valueOf (NR_Codigo_Cliente) != null && !String.valueOf (NR_Codigo_Cliente).equals ("") && !String.valueOf (NR_Codigo_Cliente).equals ("null") && !String.valueOf (NR_Codigo_Cliente).equals (null)) {
      ed.setNR_Codigo_Cliente (request.getParameter ("FT_NR_Codigo_Cliente"));
    }

    String OID_Ordem_Carga = request.getParameter ("oid_Ordem_Carga");
    if (String.valueOf (OID_Ordem_Carga) != null && !String.valueOf (OID_Ordem_Carga).equals ("")
        && !String.valueOf (OID_Ordem_Carga).equals ("null")) {
      ed.setOID_Ordem_Carga (new Long (request.getParameter ("oid_Ordem_Carga")).longValue ());
    }

    String OID_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (OID_Produto) != null && !String.valueOf (OID_Produto).equals ("")
        && !String.valueOf (OID_Produto).equals ("null")) {
      ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    }
    String NR_Lote = request.getParameter ("FT_NR_Lote");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    String NR_Despacho = request.getParameter ("FT_NR_Despacho");
    if (String.valueOf (NR_Despacho) != null && !String.valueOf (NR_Despacho).equals ("")
        && !String.valueOf (NR_Despacho).equals ("null")) {
      ed.setNR_Despacho (new Long (NR_Despacho).longValue ());
    }

    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    if (String.valueOf (DT_Emissao) != null && !String.valueOf (DT_Emissao).equals ("") && !String.valueOf (DT_Emissao).equals ("null")) {
      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    }

    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setDT_Emissao_Inicial (dt_Emissao_Inicial);
    }

    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setDT_Emissao_Final (dt_Emissao_Final);
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa_Remetente");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    }
    oid_Pessoa = request.getParameter ("oid_Pessoa_Destinatario");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    }

    oid_Pessoa = request.getParameter ("oid_Pessoa_Consignatario");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    }

    String nr_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (String.valueOf (nr_Nota_Fiscal) != null && !String.valueOf (nr_Nota_Fiscal).equals ("") && !String.valueOf (nr_Nota_Fiscal).equals ("null")) {
      ed.setNR_Nota_Fiscal (new Long (nr_Nota_Fiscal).longValue ());
    }

    String CD_Chassis_Serie = request.getParameter ("FT_CD_Chassis_Serie");
    if (CD_Chassis_Serie != null && !CD_Chassis_Serie.equals ("") && !CD_Chassis_Serie.equals ("null")) {
      ed.setCD_Chassis_Serie (request.getParameter ("FT_CD_Chassis_Serie"));
    }

    String OID_Coleta = request.getParameter ("oid_Coleta");
    if (OID_Coleta != null && OID_Coleta.length () > 0 && !String.valueOf (OID_Coleta).equals ("null") && !String.valueOf (OID_Coleta).equals ("")) {
      ed.setOID_Coleta (new Long (request.getParameter ("oid_Coleta")).longValue ());
    }

    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOid_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    return new Nota_FiscalRN ().lista (ed);

  }

  public Nota_FiscalED getByRecord (HttpServletRequest request) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length () > 0) {
      ed.setNR_Nota_Fiscal (new Long (request.getParameter ("FT_NR_Nota_Fiscal")).longValue ());
    }

    String NR_Despacho = request.getParameter ("FT_NR_Despacho");
    if (NR_Despacho != null && NR_Despacho.length () > 0) {
      ed.setNR_Despacho (new Long (request.getParameter ("FT_NR_Despacho")).longValue ());
    }

    String NM_Serie = request.getParameter ("FT_NM_Serie");
    if (NM_Serie != null && !NM_Serie.equals ("") && !NM_Serie.equals ("null")) {
      ed.setNM_Serie (request.getParameter ("FT_NM_Serie"));
    }

    ed.setDM_Tipo_Conhecimento ("");
    String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    if (String.valueOf (DM_Tipo_Conhecimento) != null && !String.valueOf (DM_Tipo_Conhecimento).equals ("")
        && !String.valueOf (DM_Tipo_Conhecimento).equals ("null")) {
      ed.setDM_Tipo_Conhecimento (request.getParameter ("FT_DM_Tipo_Conhecimento"));
    }

    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

    return new Nota_FiscalRN ().getByRecord (ed);

  }

  public Nota_FiscalED getByOID (String oid_Nota_Fiscal) throws Excecoes {

    if (!util.doValida (oid_Nota_Fiscal)) {
      throw new Excecoes ("ID Nota Fiscal não informado!");
    }

    Nota_FiscalED ed = new Nota_FiscalED ();
    ed.setOID_Nota_Fiscal (oid_Nota_Fiscal);

    return new Nota_FiscalRN ().getByRecord (ed);

  }

  public Nota_FiscalED getByRecord_Conhecimento (HttpServletRequest request) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length () > 0) {
      ed.setNR_Nota_Fiscal (new Long (request.getParameter ("FT_NR_Nota_Fiscal")).longValue ());
    }
    ed.setNM_Serie (request.getParameter ("FT_NM_Serie"));
    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

    return new Nota_FiscalRN ().getByRecord_Conhecimento (ed);

  }
  public Nota_FiscalED getByRecord_Conhecimento_Nota (HttpServletRequest request) throws Excecoes {

	    Nota_FiscalED ed = new Nota_FiscalED ();

	    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
	    if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length () > 0) {
	      ed.setNR_Nota_Fiscal (new Long (request.getParameter ("FT_NR_Nota_Fiscal")).longValue ());
	    }
	    ed.setNM_Serie (request.getParameter ("FT_NM_Serie"));
	    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));

	    return new Nota_FiscalRN ().getByRecord_Conhecimento (ed);

	  }

  public int notasMesmoNumero (Nota_FiscalED filtro) throws Excecoes {
    Nota_FiscalED ed = new Nota_FiscalED ();
    ed.setOID_Nota_Fiscal (filtro.getOID_Nota_Fiscal ());
    ed.setNR_Nota_Fiscal (filtro.getNR_Nota_Fiscal ());
    ed.setOID_Pessoa (filtro.getOID_Pessoa ());

    return new Nota_FiscalRN ().notasMesmoNumero (ed);
  }

  public ArrayList Nota_Fiscal_Lista_Traking (HttpServletRequest request) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setDT_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setDT_Emissao_Final (dt_Emissao_Final);
    }

    String nr_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (String.valueOf (nr_Nota_Fiscal) != null &&
        !String.valueOf (nr_Nota_Fiscal).equals ("")) {
      ed.setNR_Nota_Fiscal (new Long (nr_Nota_Fiscal).longValue ());
    }

    ed.setCD_Referencia (request.getParameter ("FT_CD_Referencia"));

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));

    return new Nota_FiscalRN ().lista_Traking (ed);

  }

  public ArrayList gera_Arquivo_Nota_Fiscal_Traking (HttpServletRequest request) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setDT_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setDT_Emissao_Final (dt_Emissao_Final);
    }

    String nr_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (String.valueOf (nr_Nota_Fiscal) != null &&
        !String.valueOf (nr_Nota_Fiscal).equals ("")) {
      ed.setNR_Nota_Fiscal (new Long (nr_Nota_Fiscal).longValue ());
    }

    ed.setCD_Referencia (request.getParameter ("FT_CD_Referencia"));

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));

    return new Nota_FiscalRN ().gera_Arquivo_Nota_Fiscal_Traking (ed);

  }

  public ArrayList Nota_Fiscal_Rastreamento_Lista (HttpServletRequest request) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setDT_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setDT_Emissao_Final (dt_Emissao_Final);
    }

    String nr_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (String.valueOf (nr_Nota_Fiscal) != null &&
        !String.valueOf (nr_Nota_Fiscal).equals ("")) {
      ed.setNR_Nota_Fiscal (new Long (nr_Nota_Fiscal).longValue ());
    }

    ed.setCD_Referencia (request.getParameter ("FT_CD_Referencia"));

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));

    return new Nota_FiscalRN ().Nota_Fiscal_Rastreamento_Lista (ed);
  }

  public byte[] geraRelNota_Fiscal (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    String NR_Pedido = request.getParameter ("FT_NR_Pedido");
    if (String.valueOf (NR_Pedido) != null && !String.valueOf (NR_Pedido).equals ("") && !String.valueOf (NR_Pedido).equals ("null") && !String.valueOf (NR_Pedido).equals (null)) {
      ed.setNR_Pedido (request.getParameter ("FT_NR_Pedido"));
    }
    String OID_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (OID_Produto) != null && !String.valueOf (OID_Produto).equals ("")
        && !String.valueOf (OID_Produto).equals ("null")) {
      ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    }
    String NR_Lote = request.getParameter ("FT_NR_Lote");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    String NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    if (String.valueOf (NR_Conhecimento) != null && !String.valueOf (NR_Conhecimento).equals ("")
        && !String.valueOf (NR_Conhecimento).equals ("null")) {
      ed.setNR_Conhecimento (NR_Conhecimento);
    }

    String NR_Despacho = request.getParameter ("FT_NR_Despacho");
    if (String.valueOf (NR_Despacho) != null && !String.valueOf (NR_Despacho).equals ("")
        && !String.valueOf (NR_Despacho).equals ("null")) {
      ed.setNR_Despacho (new Long (NR_Despacho).longValue ());
    }

    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setDT_Emissao_Inicial (dt_Emissao_Inicial);
    }

    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setDT_Emissao_Final (dt_Emissao_Final);
    }

    String dt_Saida_Inicial = request.getParameter ("FT_DT_Saida_Inicial");
    if (dt_Saida_Inicial != null && !dt_Saida_Inicial.equals ("")) {
      ed.setDT_Saida_Inicial (dt_Saida_Inicial);
    }

    String dt_Saida_Final = request.getParameter ("FT_DT_Saida_Final");
    if (dt_Saida_Final != null && !dt_Saida_Final.equals ("")) {
      ed.setDT_Saida_Final (dt_Saida_Final);
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa_Remetente");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    }
    oid_Pessoa = request.getParameter ("oid_Pessoa_Destinatario");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    }

    oid_Pessoa = request.getParameter ("oid_Pessoa_Consignatario");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    }

    String OID_Coleta = request.getParameter ("oid_Coleta");
    if (OID_Coleta != null && OID_Coleta.length () > 0 && !String.valueOf (OID_Coleta).equals ("null") && !String.valueOf (OID_Coleta).equals ("")) {
      ed.setOID_Coleta (new Long (request.getParameter ("oid_Coleta")).longValue ());
    }

    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOid_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    Nota_FiscalRN nfRN = new Nota_FiscalRN ();
    byte[] b = nfRN.geraRelNota_Fiscal (ed);

    return b;

  }

  public byte[] imprime_Etiqueta (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    String OID_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("")
        && !String.valueOf (OID_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    }

    String NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento_Inicial");
    if (String.valueOf (NR_Conhecimento) != null && !String.valueOf (NR_Conhecimento).equals ("")
        && !String.valueOf (NR_Conhecimento).equals ("null")) {
      ed.setNR_Conhecimento_Inicial (new Long (NR_Conhecimento).longValue());
    }
    NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento_Final");
    if (String.valueOf (NR_Conhecimento) != null && !String.valueOf (NR_Conhecimento).equals ("")
        && !String.valueOf (NR_Conhecimento).equals ("null")) {
      ed.setNR_Conhecimento_Final (new Long (NR_Conhecimento).longValue());
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("")
        && !String.valueOf (oid_Pessoa).equals (null)
        && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
    }

    String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
    if (JavaUtil.doValida(cd_Rota_Entrega)){
  	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    Nota_FiscalRN nfRN = new Nota_FiscalRN ();
    byte[] b = nfRN.imprime_Etiqueta (ed);

    return b;

  }

  public ArrayList listaConsultaCliente (HttpServletRequest request) throws Excecoes {

    return new Nota_FiscalRN ().listaConsultaCliente (carregaED (request));
  }

  public byte[] geraRelRemessas (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Nota_FiscalRN nfRN = new Nota_FiscalRN ();
    byte[] b = nfRN.geraRelRemessas (carregaED (request));

    return b;
  }

  private Nota_FiscalED carregaED (HttpServletRequest request) throws Excecoes {

    Nota_FiscalED ed = new Nota_FiscalED ();

    String NR_Pedido = request.getParameter ("FT_NR_Pedido");
    String NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String DT_Saida_Inicial = request.getParameter ("FT_DT_Saida_Inicial");
    String DT_Saida_Final = request.getParameter ("FT_DT_Saida_Final");
    String DT_Entrega_Inicial = request.getParameter ("FT_DT_Entrega_Inicial");
    String DT_Entrega_Final = request.getParameter ("FT_DT_Entrega_Final");
    String nr_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    String oid_Pessoa_Consignatario = request.getParameter ("oid_Pessoa_Consignatario");
    String NR_Despacho = request.getParameter ("FT_NR_Despacho");
    String CD_Acesso = request.getParameter ("FT_CD_Acesso");
    String CD_Referencia = request.getParameter ("FT_CD_Referencia");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    String DM_Tipo_Consulta = request.getParameter ("FT_DM_Tipo_Consulta");
    String DM_Ordem = request.getParameter ("FT_DM_Ordem");

    String NR_Codigo_Cliente = request.getParameter ("FT_NR_Codigo_Cliente");
    if (String.valueOf (NR_Codigo_Cliente) != null && !String.valueOf (NR_Codigo_Cliente).equals ("") && !String.valueOf (NR_Codigo_Cliente).equals ("null") && !String.valueOf (NR_Codigo_Cliente).equals (null)) {
      ed.setNR_Codigo_Cliente (NR_Codigo_Cliente);
    }

    // System.out.println ("NR_Codigo_Cliente->>"+NR_Codigo_Cliente);

    ed.setNR_Lote ("");
    String NR_Lote = request.getParameter ("FT_NR_Lote");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    if (util.doValida (NR_Pedido)) {
      ed.setNR_Pedido (NR_Pedido);
    }
    if (util.doValida (DT_Emissao)) {
      ed.setDT_Emissao (DT_Emissao);
    }
    if (util.doValida (DT_Emissao_Inicial)) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }
    if (util.doValida (DT_Emissao_Final)) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }

    if (util.doValida (DT_Entrega_Inicial)) {
      ed.setDT_Entrega_Inicial (DT_Entrega_Inicial);
    }
    if (util.doValida (DT_Entrega_Final)) {
      ed.setDT_Entrega_Final (DT_Entrega_Final);
    }

    if (util.doValida (DT_Saida_Inicial)) {
      ed.setDT_Saida_Inicial (DT_Saida_Inicial);
    }
    if (util.doValida (DT_Saida_Final)) {
      ed.setDT_Saida_Final (DT_Saida_Final);
    }

    // System.out.println ("iu 1");

    if (util.doValida (nr_Nota_Fiscal)) {
      ed.setNR_Nota_Fiscal (Long.parseLong (nr_Nota_Fiscal));
    }

    // System.out.println ("iu 2");

    if (util.doValida (NR_Despacho)) {
      ed.setNR_Despacho (Long.parseLong (NR_Despacho));
    }

    // System.out.println ("iu 3");

    if (util.doValida (NR_Conhecimento)) {
      ed.setNR_Conhecimento (NR_Conhecimento);

    }

    // System.out.println ("iu 4");

    if (util.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (util.doValida (oid_Pessoa_Destinatario)) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    if (util.doValida (oid_Pessoa_Consignatario)) {
      ed.setOID_Pessoa_Consignatario (oid_Pessoa_Consignatario);
    }
    // System.out.println ("iu 3");

    if (util.doValida (CD_Acesso)) {
      ed.setCD_Acesso (request.getParameter ("FT_CD_Acesso"));
    }
    else {
      throw new Mensagens ("Código de acesso não informado!");
    }
    if (util.doValida (CD_Referencia)) {
      ed.setCD_Referencia (CD_Referencia);
    }
    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }
    if (util.doValida (DM_Tipo_Documento)) {
      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
    }
    if (util.doValida (DM_Ordem)) {
      ed.setDM_Ordem (DM_Ordem);
    }

    if ("EXPORTA".equals(request.getParameter ("acao"))){
      ed.setDM_Relatorio ("-------------------");
      ed.setDM_Exportacao (request.getParameter ("FT_DM_Exportacao"));
    }else{
      ed.setDM_Exportacao ("--------------------");
      ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));
    }

    // System.out.println ("iu 4");

    // System.out.println ("DM_Tipo_Consulta->>" + DM_Tipo_Consulta);
    // System.out.println ("setDM_Relatorio->>" + request.getParameter ("FT_DM_Relatorio"));

    String Dm_Origem = request.getParameter ("FT_DM_Origem");
    if (String.valueOf (Dm_Origem) != null && !String.valueOf (Dm_Origem).equals ("")) {
      ed.setDM_Origem (Dm_Origem);
    }

    String Dm_Destino = request.getParameter ("FT_DM_Destino");
    if (String.valueOf (Dm_Destino) != null && !String.valueOf (Dm_Destino).equals ("")) {
      ed.setDM_Destino (Dm_Destino);
    }

    // System.out.println ("iu 5");

    ed.setOID_Origem (0);
    if ("T".equals (Dm_Origem)) {
      String oid_Origem = request.getParameter ("oid_Origem");
      if (String.valueOf (oid_Origem) != null && !String.valueOf (oid_Origem).equals ("0") && !String.valueOf (oid_Origem).equals ("")) {
        ed.setOID_Origem (new Long (oid_Origem).longValue ());
      }
    }

    // System.out.println ("iu 6");

    ed.setOID_Destino (0);
    if ("T".equals (Dm_Destino)) {
      String oid_Destino = request.getParameter ("oid_Destino");
      if (String.valueOf (oid_Destino) != null && !String.valueOf (oid_Destino).equals ("0") && !String.valueOf (oid_Destino).equals ("")) {
        ed.setOID_Destino (new Long (oid_Destino).longValue ());
      }
    }

    // System.out.println ("iu 7");

    ed.setDM_Tipo_Consulta ("COM_ITENS_DA_NOTA");
    if (util.doValida (DM_Tipo_Consulta)) {
      ed.setDM_Tipo_Consulta (DM_Tipo_Consulta);
    }

    String NM_Mes_Inicial = request.getParameter ("FT_NM_Mes_Inicial");
    String NM_Ano_Inicial = request.getParameter ("FT_NM_Ano_Inicial");
    if (util.doValida (NM_Mes_Inicial) && util.doValida (NM_Ano_Inicial)) {
      ed.setNM_Mes_Inicial (NM_Mes_Inicial);
      ed.setNM_Ano_Inicial (NM_Ano_Inicial);
      ed.setDT_Emissao_Inicial ("01/" + ed.getNM_Mes_Inicial () + "/" + ed.getNM_Ano_Inicial ());
      ed.setDT_Emissao_Final (Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_Inicial () + "/" + ed.getNM_Ano_Inicial ()));
    }

    return ed;

  }

}
