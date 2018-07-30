package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Ordem_ServicoED;
import com.master.rn.Ordem_ServicoRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;

public class fro012Bean {

  public ArrayList Ordem_Servico_Lista (HttpServletRequest request) throws Excecoes {

    Ordem_ServicoED ed = new Ordem_ServicoED ();

    String OID_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
    if (String.valueOf (OID_Ordem_Servico) != null && !String.valueOf (OID_Ordem_Servico).equals ("") && !String.valueOf (OID_Ordem_Servico).equals ("null")) {
      ed.setOID_Ordem_Servico (new Long (OID_Ordem_Servico).longValue ());
    }
    ed.setDT_Emissao_Inicial (request.getParameter ("FT_DT_Ordem_Servico"));

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("") && !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    return new Ordem_ServicoRN ().lista (ed);

  }

  public byte[] Relatorio_Ordem_Servico(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
    Ordem_ServicoED ed = new Ordem_ServicoED ();

    String OID_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
    if (String.valueOf (OID_Ordem_Servico) != null && !String.valueOf (OID_Ordem_Servico).equals ("") && !String.valueOf (OID_Ordem_Servico).equals ("null")) {
      ed.setOID_Ordem_Servico (new Long (OID_Ordem_Servico).longValue ());
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }
    String NR_Placa = request.getParameter ("FT_NR_Placa");
    if (String.valueOf (NR_Placa) != null && !String.valueOf (NR_Placa).equals ("")) {
      ed.setNR_Placa (NR_Placa);
    }

    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }
    String DM_Tipo_Despesa = request.getParameter ("FT_DM_Tipo_Despesa");
    if (String.valueOf (DM_Tipo_Despesa) != null && !String.valueOf (DM_Tipo_Despesa).equals ("")) {
      ed.setDM_Tipo_Despesa (DM_Tipo_Despesa);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    if (String.valueOf (oid_Tipo_Servico) != null && !String.valueOf (oid_Tipo_Servico).equals ("")) {
      ed.setOID_Tipo_Servico (new Long (oid_Tipo_Servico).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    if (String.valueOf (oid_Empresa) != null && !String.valueOf (oid_Empresa).equals ("") && !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }

    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    return new Ordem_ServicoRN().Relatorio_Ordem_Servico(ed);
  }

  public byte[] resumo_Custo_Faturamento_Veiculo(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
    Ordem_ServicoED ed = new Ordem_ServicoED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }

    String NR_Placa = request.getParameter ("FT_NR_Placa");
    if (String.valueOf (NR_Placa) != null && !String.valueOf (NR_Placa).equals ("")) {
      ed.setNR_Placa (NR_Placa);
    }

    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }
    String DM_Tipo_Despesa = request.getParameter ("FT_DM_Tipo_Despesa");
    if (String.valueOf (DM_Tipo_Despesa) != null && !String.valueOf (DM_Tipo_Despesa).equals ("")) {
      ed.setDM_Tipo_Despesa (DM_Tipo_Despesa);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    if (String.valueOf (oid_Tipo_Servico) != null && !String.valueOf (oid_Tipo_Servico).equals ("")) {
      ed.setOID_Tipo_Servico (new Long (oid_Tipo_Servico).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    if (String.valueOf (oid_Empresa) != null && !String.valueOf (oid_Empresa).equals ("") && !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }

    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    return new Ordem_ServicoRN().resumo_Custo_Faturamento_Veiculo(ed);
  }

  public void deleta_Movimento (HttpServletRequest request) throws Excecoes {

    Ordem_ServicoRN Ordem_ServicoRN = new Ordem_ServicoRN ();
    Ordem_ServicoED ed = new Ordem_ServicoED ();

    ed.setOID_Movimento_Ordem_Servico (new Long (request.getParameter ("oid_Movimento_Ordem_Servico")).longValue ());

    Ordem_ServicoRN.deleta_Movimento (ed);
  }

  public void inclui_Rateio (HttpServletRequest request) throws Excecoes {

    Ordem_ServicoRN Ordem_ServicoRN = new Ordem_ServicoRN ();
    Ordem_ServicoED ed = new Ordem_ServicoED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    if (String.valueOf (DT_Emissao) != null && !String.valueOf (DT_Emissao).equals ("")) {
      ed.setDT_Emissao (DT_Emissao);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    if (String.valueOf (oid_Tipo_Servico) != null && !String.valueOf (oid_Tipo_Servico).equals ("")) {
      ed.setOID_Tipo_Servico (new Long (oid_Tipo_Servico).longValue ());
    }

    String VL_Rateio = request.getParameter ("FT_VL_Rateio");
    if (String.valueOf (VL_Rateio) != null && !String.valueOf (VL_Rateio).equals ("")) {
      ed.setVL_Rateio (new Double (VL_Rateio).doubleValue());
    }


    Ordem_ServicoRN.inclui_Rateio (ed);
  }

  public void inclui_Custo_Fixo (HttpServletRequest request) throws Excecoes {

    Ordem_ServicoRN Ordem_ServicoRN = new Ordem_ServicoRN ();
    Ordem_ServicoED ed = new Ordem_ServicoED ();

    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    if (String.valueOf (DT_Emissao) != null && !String.valueOf (DT_Emissao).equals ("")) {
      ed.setDT_Emissao (DT_Emissao);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    if (String.valueOf (oid_Tipo_Servico) != null && !String.valueOf (oid_Tipo_Servico).equals ("")) {
      ed.setOID_Tipo_Servico (new Long (oid_Tipo_Servico).longValue ());
    }
    Ordem_ServicoRN.inclui_Custo_Fixo (ed);
  }


  public byte[] imprime_Requisicao (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Ordem_ServicoED ed = new Ordem_ServicoED ();

    String OID_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
    if (String.valueOf (OID_Ordem_Servico) != null && !String.valueOf (OID_Ordem_Servico).equals ("") && !String.valueOf (OID_Ordem_Servico).equals ("null")) {
      ed.setOID_Ordem_Servico (new Long (OID_Ordem_Servico).longValue ());
    }
    ed.setDM_Relatorio (request.getParameter ("acao"));

    Ordem_ServicoRN geRN = new Ordem_ServicoRN ();
    byte[] b = geRN.imprime_Requisicao (ed);

    return b;

  }

  public byte[] ficha_Manutencao (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Ordem_ServicoED ed = new Ordem_ServicoED ();

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }

    String NR_Placa = request.getParameter ("FT_NR_Placa");
    if (String.valueOf (NR_Placa) != null && !String.valueOf (NR_Placa).equals ("")) {
      ed.setNR_Placa (NR_Placa);
    }
    String DM_Tipo_Despesa = request.getParameter ("FT_DM_Tipo_Despesa");
    if (String.valueOf (DM_Tipo_Despesa) != null && !String.valueOf (DM_Tipo_Despesa).equals ("")) {
      ed.setDM_Tipo_Despesa (DM_Tipo_Despesa);
    }

    String DM_Tipo_Implemento = request.getParameter ("FT_DM_Tipo_Implemento");
    if (String.valueOf (DM_Tipo_Implemento) != null && !String.valueOf (DM_Tipo_Implemento).equals ("")) {
      ed.setDM_Tipo_Implemento (DM_Tipo_Implemento);
    }

    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    if (String.valueOf (oid_Tipo_Servico) != null && !String.valueOf (oid_Tipo_Servico).equals ("")) {
      ed.setOID_Tipo_Servico (new Long (oid_Tipo_Servico).longValue ());
    }
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    if (String.valueOf (TX_Observacao) != null && !String.valueOf (TX_Observacao).equals ("") && !String.valueOf (TX_Observacao).equals ("null")) {
      ed.setTX_Observacao (TX_Observacao);
    }
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    Ordem_ServicoRN geRN = new Ordem_ServicoRN ();
    byte[] b = geRN.ficha_Manutencao (ed);

    return b;
  }
}