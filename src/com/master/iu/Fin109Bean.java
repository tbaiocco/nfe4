package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ProcessoED;
import com.master.rn.ProcessoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Fin109Bean {

  public ProcessoED inclui (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();

    ed.setDm_Tipo_Processo (request.getParameter ("FT_DM_Tipo_Processo"));
    ed.setNr_Processo (request.getParameter ("FT_NR_Processo"));
    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
    ed.setOid_Moeda (new Integer (request.getParameter ("oid_Moeda")));
    ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));
    ed.setOid_Empresa (new Integer (request.getParameter ("oid_Empresa")));

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String DT_Abertura = request.getParameter ("FT_DT_Abertura");
    String HR_Abertura = request.getParameter ("FT_HR_Abertura");
    String DT_Previsao = request.getParameter ("FT_DT_Previsao");
    String HR_Previsao = request.getParameter ("FT_HR_Previsao");
    String DT_Encerramento = request.getParameter ("FT_DT_Encerramento");
    String HR_Encerramento = request.getParameter ("FT_HR_Encerramento");
    String NR_Odometro_Inicial = request.getParameter ("FT_NR_Odometro_Inicial");
    String NR_Odometro_Final = request.getParameter ("FT_NR_Odometro_Final");
    String DM_Tipo_Faturamento = request.getParameter ("FT_DM_Tipo_Faturamento");
    String DM_Meio_Pagamento = request.getParameter ("FT_DM_Meio_Pagamento");
    String TX_Servico = request.getParameter ("FT_TX_Servico");
    String NM_Ajudante = request.getParameter ("FT_NM_Ajudante");
    String NM_Contato = request.getParameter ("FT_NM_Contato");
    if (JavaUtil.doValida (oid_Veiculo))
      ed.setOid_Veiculo (oid_Veiculo);
    if (JavaUtil.doValida (oid_Motorista))
      ed.setOid_Motorista (oid_Motorista);
    if (JavaUtil.doValida (DT_Abertura))
      ed.setDT_Abertura (DT_Abertura);
    if (JavaUtil.doValida (HR_Abertura))
      ed.setHR_Abertura (HR_Abertura);
    if (JavaUtil.doValida (DT_Previsao))
      ed.setDT_Previsao (DT_Previsao);
    if (JavaUtil.doValida (HR_Previsao))
      ed.setHR_Previsao (HR_Previsao);
    if (JavaUtil.doValida (DT_Encerramento))
      ed.setDT_Encerramento (DT_Encerramento);
    if (JavaUtil.doValida (HR_Encerramento))
      ed.setHR_Encerramento (HR_Encerramento);
    if (JavaUtil.doValida (NR_Odometro_Inicial))
      ed.setNR_Odometro_Inicial (Integer.parseInt (NR_Odometro_Inicial));
    if (JavaUtil.doValida (NR_Odometro_Final))
      ed.setNR_Odometro_Final (Integer.parseInt (NR_Odometro_Final));
    if (JavaUtil.doValida (DM_Meio_Pagamento))
      ed.setDM_Meio_Pagamento (DM_Meio_Pagamento);
    if (JavaUtil.doValida (TX_Servico))
      ed.setTX_Servico (TX_Servico);
    if (JavaUtil.doValida (NM_Ajudante))
      ed.setNM_Ajudante (NM_Ajudante);
    if (JavaUtil.doValida (DM_Tipo_Faturamento))
      ed.setDM_Tipo_Faturamento (DM_Tipo_Faturamento);
    if (JavaUtil.doValida (NM_Contato))
      ed.setNM_Contato (NM_Contato);

    return new ProcessoRN ().inclui (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();
    ed.setOid_Empresa (new Integer (request.getParameter ("oid_Empresa")));

    ed.setOid_Processo (request.getParameter ("oid_Processo"));
    ed.setDm_Tipo_Processo (request.getParameter ("FT_DM_Tipo_Processo"));
    ed.setNr_Processo (request.getParameter ("FT_NR_Processo"));
    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
    ed.setOid_Moeda (new Integer (request.getParameter ("oid_Moeda")));
    ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String DT_Abertura = request.getParameter ("FT_DT_Abertura");
    String HR_Abertura = request.getParameter ("FT_HR_Abertura");
    String DT_Encerramento = request.getParameter ("FT_DT_Encerramento");
    String HR_Encerramento = request.getParameter ("FT_HR_Encerramento");
    String NR_Odometro_Inicial = request.getParameter ("FT_NR_Odometro_Inicial");
    String NR_Odometro_Final = request.getParameter ("FT_NR_Odometro_Final");
    String DM_Tipo_Faturamento = request.getParameter ("FT_DM_Tipo_Faturamento");
    String DM_Meio_Pagamento = request.getParameter ("FT_DM_Meio_Pagamento");
    String NM_Contato = request.getParameter ("FT_NM_Contato");
    String TX_Servico = request.getParameter ("FT_TX_Servico");
    String NM_Ajudante = request.getParameter ("FT_NM_Ajudante");
    String DT_Previsao = request.getParameter ("FT_DT_Previsao");
    String HR_Previsao = request.getParameter ("FT_HR_Previsao");

    if (JavaUtil.doValida (oid_Veiculo))
      ed.setOid_Veiculo (oid_Veiculo);
    if (JavaUtil.doValida (oid_Motorista))
      ed.setOid_Motorista (oid_Motorista);
    if (JavaUtil.doValida (DT_Abertura))
      ed.setDT_Abertura (DT_Abertura);
    if (JavaUtil.doValida (HR_Abertura))
      ed.setHR_Abertura (HR_Abertura);
    if (JavaUtil.doValida (DT_Encerramento))
      ed.setDT_Encerramento (DT_Encerramento);
    if (JavaUtil.doValida (HR_Encerramento))
      ed.setHR_Encerramento (HR_Encerramento);
    if (JavaUtil.doValida (NR_Odometro_Inicial))
      ed.setNR_Odometro_Inicial (Integer.parseInt (NR_Odometro_Inicial));
    if (JavaUtil.doValida (NR_Odometro_Final))
      ed.setNR_Odometro_Final (Integer.parseInt (NR_Odometro_Final));
    if (JavaUtil.doValida (DM_Meio_Pagamento))
      ed.setDM_Meio_Pagamento (DM_Meio_Pagamento);
    if (JavaUtil.doValida (DM_Tipo_Faturamento))
      ed.setDM_Tipo_Faturamento (DM_Tipo_Faturamento);
    if (JavaUtil.doValida (NM_Contato))
      ed.setNM_Contato (NM_Contato);
    if (JavaUtil.doValida (DT_Previsao))
      ed.setDT_Previsao (DT_Previsao);
    if (JavaUtil.doValida (HR_Previsao))
      ed.setHR_Previsao (HR_Previsao);
    if (JavaUtil.doValida (TX_Servico))
      ed.setTX_Servico (TX_Servico);
    if (JavaUtil.doValida (NM_Ajudante))
      ed.setNM_Ajudante (NM_Ajudante);

    new ProcessoRN ().altera (ed);
  }

  public void finaliza (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();
    ed.setOid_Processo (request.getParameter ("oid_Processo"));
    ed.setNM_Situacao ("FINALIZA");

    new ProcessoRN ().alteraSituacao (ed);
  }
  public void cancela (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();
    ed.setOid_Processo (request.getParameter ("oid_Processo"));
    ed.setNM_Situacao ("CANCELA");

    new ProcessoRN ().alteraSituacao (ed);
  }
  public void liberaFaturameto (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();
    ed.setOid_Processo (request.getParameter ("oid_Processo"));
    ed.setNM_Situacao ("LIBERA");

    new ProcessoRN ().alteraSituacao (ed);
  }


  public void deleta (HttpServletRequest request) throws Excecoes {

    new ProcessoRN ().deleta (new ProcessoED (request.getParameter (
        "oid_Processo")));
  }

  public ArrayList lista (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();
    String nr_Processo = request.getParameter ("FT_NR_Processo");
    String oid_Pessoa = request.getParameter ("oid_Pessoa");

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String NR_Odometro_Inicial = request.getParameter ("FT_NR_Odometro_Inicial");
    String NR_Odometro_Final = request.getParameter ("FT_NR_Odometro_Final");
    String DM_Tipo_Faturamento = request.getParameter ("FT_DM_Tipo_Faturamento");
    String DM_Meio_Pagamento = request.getParameter ("FT_DM_Meio_Pagamento");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String DT_Abertura_Inicial = request.getParameter ("FT_DT_Abertura_Inicial");
    String DT_Abertura_Final = request.getParameter ("FT_DT_Abertura_Final");
    String DT_Previsao_Inicial = request.getParameter ("FT_DT_Previsao_Inicial");
    String DT_Previsao_Final = request.getParameter ("FT_DT_Previsao_Final");
    String DM_Ordenacao = request.getParameter ("FT_DM_Ordenacao");
    String oid_Conta = request.getParameter ("oid_Conta");

    if (JavaUtil.doValida (oid_Veiculo))
      ed.setOid_Veiculo (oid_Veiculo);
    if (JavaUtil.doValida (oid_Motorista))
      ed.setOid_Motorista (oid_Motorista);
    if (JavaUtil.doValida (NR_Odometro_Inicial))
      ed.setNR_Odometro_Inicial (Integer.parseInt (NR_Odometro_Inicial));
    if (JavaUtil.doValida (NR_Odometro_Final))
      ed.setNR_Odometro_Final (Integer.parseInt (NR_Odometro_Final));
    if (JavaUtil.doValida (DM_Meio_Pagamento))
      ed.setDM_Meio_Pagamento (DM_Meio_Pagamento);
    if (JavaUtil.doValida (DM_Tipo_Faturamento))
      ed.setDM_Tipo_Faturamento (DM_Tipo_Faturamento);
    if (JavaUtil.doValida (DM_Situacao))
      ed.setDM_Situacao (DM_Situacao);
    if (JavaUtil.doValida (nr_Processo))
      ed.setNr_Processo (nr_Processo);
    if (JavaUtil.doValida (oid_Pessoa))
      ed.setOid_Pessoa (oid_Pessoa);
    if (JavaUtil.doValida (DT_Abertura_Inicial))
        ed.setDT_Abertura_Inicial (DT_Abertura_Inicial);
    if (JavaUtil.doValida (DT_Abertura_Final))
        ed.setDT_Abertura_Final (DT_Abertura_Final);
    if (JavaUtil.doValida (DT_Previsao_Inicial))
        ed.setDT_Previsao_Inicial (DT_Previsao_Inicial);
    if (JavaUtil.doValida (DT_Previsao_Final))
        ed.setDT_Previsao_Final (DT_Previsao_Final);
    if (JavaUtil.doValida (DM_Ordenacao))
        ed.setDM_Ordenacao(DM_Ordenacao);
    if (JavaUtil.doValida (oid_Conta))
        ed.setOid_Conta(new Integer(oid_Conta));

    return new ProcessoRN ().lista (ed);
  }


  public ArrayList lista_Fatura (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();

    ed.setOid_Processo (request.getParameter ("oid_Processo"));

    return new ProcessoRN ().lista_Fatura (ed);
  }

  public ProcessoED getByRecord (String oid_Processo) throws Excecoes {

    ProcessoED ed = new ProcessoED ();
    ed.setOid_Processo (oid_Processo);
    return new ProcessoRN ().getByRecord (ed);
}

  public ProcessoED getByRecord (HttpServletRequest request) throws Excecoes {

    ProcessoED ed = new ProcessoED ();
    String nr_Processo = request.getParameter ("FT_NR_Processo");
    if (JavaUtil.doValida (nr_Processo))
      ed.setNr_Processo (nr_Processo);

    ed.setOid_Processo (request.getParameter ("oid_Processo"));

      // System.out.println("getByRecord iu-> "+ed.getNr_Processo());

    return new ProcessoRN ().getByRecord (ed);
  }
}
