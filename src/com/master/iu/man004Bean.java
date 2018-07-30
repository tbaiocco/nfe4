package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.master.ed.ColetaED;
import com.master.ed.Manifesto_InternacionalED;
import com.master.ed.Ordem_FreteED;
import com.master.ed.Tipo_EventoED;
import com.master.ed.UsuarioED;
import com.master.rn.ColetaRN;
import com.master.rn.Ordem_FreteRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.ed.Parametro_FixoED;

public class man004Bean
    implements Serializable {

  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();
  Utilitaria util = new Utilitaria ();

  public void confirma_Recuperacao_Master (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      //// System.out.println("1");

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      ed.setDT_Master_Recuperado ("");
      String DT_Master_Recuperado = request.getParameter ("FT_DT_Master_Recuperado");
      if (String.valueOf (DT_Master_Recuperado) != null &&
          !String.valueOf (DT_Master_Recuperado).equals ("null") &&
          !String.valueOf (DT_Master_Recuperado).equals ("")) {
        ed.setDT_Master_Recuperado (request.getParameter ("FT_DT_Master_Recuperado"));
      }
      //// System.out.println("2");

      ed.setHR_Master_Recuperado ("");
      String HR_Master_Recuperado = request.getParameter ("FT_HR_Master_Recuperado");
      if (String.valueOf (HR_Master_Recuperado) != null &&
          !String.valueOf (HR_Master_Recuperado).equals ("null") &&
          !String.valueOf (HR_Master_Recuperado).equals ("")) {
        ed.setHR_Master_Recuperado (request.getParameter ("FT_HR_Master_Recuperado"));
      }

      //// System.out.println("3");


      Ordem_Fretern.confirma_Recuperacao_Master (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Ordem_FreteED inclui (HttpServletRequest request) throws Excecoes {

    // System.err.println (" inclui_ iu 1  ");

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));

      ed.setOID_Fornecedor (request.getParameter ("oid_Pessoa"));
      String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
      if (String.valueOf (oid_Fornecedor) != null &&
          !String.valueOf (oid_Fornecedor).equals ("") &&
          !String.valueOf (oid_Fornecedor).equals ("null")) {
        ed.setOID_Fornecedor (oid_Fornecedor);
      }
      ed.setNR_Transacao_Pedagio (request.getParameter ("FT_NR_Transacao_Pedagio"));

      String oid_Usuario = request.getParameter ("oid_Usuario");
      if (util.doValida (oid_Usuario)) {
        ed.setOID_Usuario (new Long (request.getParameter ("oid_Usuario")).longValue ());
      }

      if (JavaUtil.doValida (request.getParameter ("oid_Motorista_Atual"))) {
        ed.setOID_Motorista (request.getParameter ("oid_Motorista_Atual"));
      }
      else {
        ed.setOID_Motorista (request.getParameter ("oid_Motorista"));
      }
      ed.setDM_Frete (request.getParameter ("FT_DM_Frete"));
      ed.setDM_Tipo_Pedagio (request.getParameter ("FT_DM_Tipo_Pedagio"));
      ed.setDM_Adiantamento (request.getParameter ("FT_DM_Adiantamento"));

      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
      ed.setHR_Emissao (request.getParameter ("FT_HR_Emissao"));

      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      ed.setOID_Unidade_Adiantamento1 (ed.getOID_Unidade ());
      ed.setOID_Unidade_Adiantamento2 (ed.getOID_Unidade ());
      ed.setOID_Unidade_Saldo (ed.getOID_Unidade ());

      String oid_Unidade = request.getParameter ("oid_Unidade_Adiantamento1");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("") &&
          !String.valueOf (oid_Unidade).equals ("null")) {
        ed.setOID_Unidade_Adiantamento1 (new Long (oid_Unidade).longValue ());
      }
      oid_Unidade = request.getParameter ("oid_Unidade_Adiantamento2");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("") &&
          !String.valueOf (oid_Unidade).equals ("null")) {
        ed.setOID_Unidade_Adiantamento2 (new Long (oid_Unidade).longValue ());
      }
      oid_Unidade = request.getParameter ("oid_Unidade_Saldo");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("") &&
          !String.valueOf (oid_Unidade).equals ("null")) {
        ed.setOID_Unidade_Saldo (new Long (oid_Unidade).longValue ());
      }

      ed.setDM_Tipo_Pagamento_Adiantamento1 ("2");
      ed.setDM_Tipo_Pagamento_Adiantamento2 ("2");
      ed.setDM_Tipo_Pagamento_Saldo ("2");
      String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento_Adiantamento1");
      if (String.valueOf (DM_Tipo_Pagamento) != null &&
          !String.valueOf (DM_Tipo_Pagamento).equals ("") &&
          !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
        ed.setDM_Tipo_Pagamento_Adiantamento1 (DM_Tipo_Pagamento);
      }
      DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento_Adiantamento2");
      if (String.valueOf (DM_Tipo_Pagamento) != null &&
          !String.valueOf (DM_Tipo_Pagamento).equals ("") &&
          !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
        ed.setDM_Tipo_Pagamento_Adiantamento2 (DM_Tipo_Pagamento);
      }
      DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento_Saldo");
      if (String.valueOf (DM_Tipo_Pagamento) != null &&
          !String.valueOf (DM_Tipo_Pagamento).equals ("") &&
          !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
        ed.setDM_Tipo_Pagamento_Saldo (DM_Tipo_Pagamento);
      }

      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));

      ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));

      // System.err.println (" inclui_ iu 2  ");

      String VL_Vale_Pedagio = request.getParameter ("FT_VL_Vale_Pedagio");
      if (String.valueOf (VL_Vale_Pedagio) != null &&
          !String.valueOf (VL_Vale_Pedagio).equals ("") &&
          !String.valueOf (VL_Vale_Pedagio).equals ("null")) {
        ed.setVL_Vale_Pedagio (new Double (VL_Vale_Pedagio).doubleValue ());
      }

      String VL_Vale_Pedagio_Empresa = request.getParameter ("FT_VL_Vale_Pedagio_Empresa");
      if (String.valueOf (VL_Vale_Pedagio_Empresa) != null &&
          !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("") &&
          !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("null")) {
        ed.setVL_Vale_Pedagio_Empresa (new Double (VL_Vale_Pedagio_Empresa).doubleValue ());
      }

      // System.err.println (" inclui_ iu 4  ");

      ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Adiantamento1"));

      ed.setDT_Adiantamento2 (request.getParameter ("FT_DT_Adiantamento2"));

      ed.setDT_Saldo (request.getParameter ("FT_DT_Saldo"));

      String VL_Adiantamento1 = request.getParameter ("FT_VL_Adiantamento1");
      if (String.valueOf (VL_Adiantamento1) != null &&
          !String.valueOf (VL_Adiantamento1).equals ("") &&
          !String.valueOf (VL_Adiantamento1).equals ("null")) {
        ed.setVL_Adiantamento1 (new Double (VL_Adiantamento1).doubleValue ());
      }

      String VL_Adiantamento2 = request.getParameter ("FT_VL_Adiantamento2");
      if (String.valueOf (VL_Adiantamento2) != null &&
          !String.valueOf (VL_Adiantamento2).equals ("") &&
          !String.valueOf (VL_Adiantamento2).equals ("null")) {
        ed.setVL_Adiantamento2 (new Double (VL_Adiantamento2).doubleValue ());
      }

      String VL_Saldo = request.getParameter ("FT_VL_Saldo");
      if (String.valueOf (VL_Saldo) != null &&
          !String.valueOf (VL_Saldo).equals ("") &&
          !String.valueOf (VL_Saldo).equals ("null")) {
        ed.setVL_Saldo (new Double (VL_Saldo).doubleValue ());
      }

      String VL_Ordem_Frete = request.getParameter ("FT_VL_Ordem_Frete");
      if (String.valueOf (VL_Ordem_Frete) != null &&
          !String.valueOf (VL_Ordem_Frete).equals ("") &&
          !String.valueOf (VL_Ordem_Frete).equals ("null")) {
        ed.setVL_Ordem_Frete (new Double (VL_Ordem_Frete).doubleValue ());
      }

      String VL_Pedagio = request.getParameter ("FT_VL_Pedagio");
      if (String.valueOf (VL_Pedagio) != null &&
          !String.valueOf (VL_Pedagio).equals ("") &&
          !String.valueOf (VL_Pedagio).equals ("null")) {
        ed.setVL_Pedagio (new Double (VL_Pedagio).doubleValue ());
      }

      String VL_Set_Senat = request.getParameter ("FT_VL_Set_Senat");
      if (String.valueOf (VL_Set_Senat) != null &&
          !String.valueOf (VL_Set_Senat).equals ("") &&
          !String.valueOf (VL_Set_Senat).equals ("null")) {
        ed.setVL_Set_Senat (new Double (VL_Set_Senat).doubleValue ());
      }

      String VL_Taxa_Expediente = request.getParameter ("FT_VL_Taxa_Expediente");
      if (String.valueOf (VL_Taxa_Expediente) != null &&
          !String.valueOf (VL_Taxa_Expediente).equals ("") &&
          !String.valueOf (VL_Taxa_Expediente).equals ("null")) {
        ed.setVL_Taxa_Expediente (new Double (VL_Taxa_Expediente).doubleValue ());
      }

      String VL_IRRF = request.getParameter ("FT_VL_IRRF");
      if (String.valueOf (VL_IRRF) != null &&
          !String.valueOf (VL_IRRF).equals ("") &&
          !String.valueOf (VL_IRRF).equals ("null")) {
        ed.setVL_IRRF (new Double (VL_IRRF).doubleValue ());
      }

      String VL_INSS_Pago = request.getParameter ("FT_VL_INSS_Pago");
      if (String.valueOf (VL_INSS_Pago) != null &&
          !String.valueOf (VL_INSS_Pago).equals ("") &&
          !String.valueOf (VL_INSS_Pago).equals ("null")) {
        ed.setVL_INSS_Pago (new Double (VL_INSS_Pago).doubleValue ());
      }
      // System.err.println (" inclui_ iu 5  ");

      ed.setNR_Ordem_Frete (0);
      ed.setDM_Impresso ("N");
      String NR_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
      if (NR_Ordem_Frete != null &&
          !NR_Ordem_Frete.equals ("") &&
          !NR_Ordem_Frete.equals ("null")) {
        ed.setNR_Ordem_Frete (new Long (NR_Ordem_Frete).longValue ());
        ed.setDM_Impresso ("S");
      }

      String NR_Qtde_Coleta = request.getParameter ("FT_NR_Qtde_Coleta");

      if (String.valueOf (NR_Qtde_Coleta) != null &&
          !String.valueOf (NR_Qtde_Coleta).equals ("") &&
          !String.valueOf (NR_Qtde_Coleta).equals ("null")) {
        ed.setNR_Qtde_Coleta (new Long (NR_Qtde_Coleta).longValue ());
      }

      String NR_Qtde_Entrega = request.getParameter ("FT_NR_Qtde_Entrega");
      if (String.valueOf (NR_Qtde_Entrega) != null &&
          !String.valueOf (NR_Qtde_Entrega).equals ("") &&
          !String.valueOf (NR_Qtde_Entrega).equals ("null")) {
        ed.setNR_Qtde_Entrega (new Long (NR_Qtde_Entrega).longValue ());
      }

      ed.setNM_Pagamento (request.getParameter ("FT_NM_Pagamento"));

      // System.err.println (" inclui_ iu 6  ");

      String VL_Descontos = request.getParameter ("FT_VL_Descontos");
      if (String.valueOf (VL_Descontos) != null &&
          !String.valueOf (VL_Descontos).equals ("") &&
          !String.valueOf (VL_Descontos).equals ("null")) {
        ed.setVL_Descontos (new Double (VL_Descontos).doubleValue ());
      }

      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));

      String oid_Manifesto = request.getParameter ("oid_Manifesto");
      if (oid_Manifesto != null &&
          !oid_Manifesto.equals ("") &&
          !oid_Manifesto.equals ("null")) {
        ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
      }
      // System.err.println (" inclui_ iu 99  ");

      String oid_MIC = request.getParameter ("oid_Manifesto_Internacional");
      if (oid_MIC != null &&
          !oid_MIC.equals ("") &&
          !oid_MIC.equals ("null")) {
        ed.setOID_MIC (request.getParameter ("oid_Manifesto_Internacional"));
      }

      // System.err.println (" inclui_ iu 99 MIC ");

      String VL_INSS_Empresa = request.getParameter ("FT_VL_INSS_Empresa");
      if (String.valueOf (VL_INSS_Empresa) != null &&
          !String.valueOf (VL_INSS_Empresa).equals ("") &&
          !String.valueOf (VL_INSS_Empresa).equals ("null")) {
        ed.setVL_INSS_Empresa (new Double (VL_INSS_Empresa).doubleValue ());
      }

      String VL_INSS_Prestador = request.getParameter ("FT_VL_INSS_Prestador");
      if (String.valueOf (VL_INSS_Prestador) != null &&
          !String.valueOf (VL_INSS_Prestador).equals ("") &&
          !String.valueOf (VL_INSS_Prestador).equals ("null")) {
        ed.setVL_INSS_Prestador (new Double (VL_INSS_Prestador).doubleValue ());
      }

      String VL_Multa_Atrazo = request.getParameter ("FT_VL_Multa_Atrazo");
      if (String.valueOf (VL_Multa_Atrazo) != null &&
          !String.valueOf (VL_Multa_Atrazo).equals ("") &&
          !String.valueOf (VL_Multa_Atrazo).equals ("null")) {
        ed.setVL_Multa_Atrazo (new Double (VL_Multa_Atrazo).doubleValue ());
      }

      String VL_Coleta = request.getParameter ("FT_VL_Coleta");
      if (String.valueOf (VL_Coleta) != null &&
          !String.valueOf (VL_Coleta).equals ("") &&
          !String.valueOf (VL_Coleta).equals ("null")) {
        ed.setVL_Coleta (new Double (VL_Coleta).doubleValue ());
      }

      String VL_Carga = request.getParameter ("FT_VL_Carga");
      if (String.valueOf (VL_Carga) != null &&
          !String.valueOf (VL_Carga).equals ("") &&
          !String.valueOf (VL_Carga).equals ("null")) {
        ed.setVL_Carga (new Double (VL_Carga).doubleValue ());
      }

      String VL_Descarga = request.getParameter ("FT_VL_Descarga");
      if (String.valueOf (VL_Descarga) != null &&
          !String.valueOf (VL_Descarga).equals ("") &&
          !String.valueOf (VL_Descarga).equals ("null")) {
        ed.setVL_Descarga (new Double (VL_Descarga).doubleValue ());
      }

      String VL_Premio = request.getParameter ("FT_VL_Premio");
      if (String.valueOf (VL_Premio) != null &&
          !String.valueOf (VL_Premio).equals ("") &&
          !String.valueOf (VL_Premio).equals ("null")) {
        ed.setVL_Premio (new Double (VL_Premio).doubleValue ());
      }
      String VL_Outros = request.getParameter ("FT_VL_Outros");
      if (String.valueOf (VL_Outros) != null &&
          !String.valueOf (VL_Outros).equals ("") &&
          !String.valueOf (VL_Outros).equals ("null")) {
        ed.setVL_Outros (new Double (VL_Outros).doubleValue ());
      }
      String VL_Liquido_Ordem_Frete = request.getParameter ("FT_VL_Liquido_Ordem_Frete");
      if (String.valueOf (VL_Liquido_Ordem_Frete) != null &&
          !String.valueOf (VL_Liquido_Ordem_Frete).equals ("") &&
          !String.valueOf (VL_Liquido_Ordem_Frete).equals ("null")) {
        ed.setVL_Liquido_Ordem_Frete (new Double (VL_Liquido_Ordem_Frete).doubleValue ());
      }

      String VL_Total_Frete_CTRC = request.getParameter ("FT_VL_Total_Frete_CTRC");
      if (String.valueOf (VL_Total_Frete_CTRC) != null &&
          !String.valueOf (VL_Total_Frete_CTRC).equals ("") &&
          !String.valueOf (VL_Total_Frete_CTRC).equals ("null")) {
        ed.setVL_Total_Frete_CTRC (new Double (VL_Total_Frete_CTRC).doubleValue ());
      }

      ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Adiantamento1"));

      ed.setDT_Adiantamento2 (request.getParameter ("FT_DT_Adiantamento2"));

      ed.setDT_Saldo (request.getParameter ("FT_DT_Saldo"));

      ed.setNM_Pagamento (request.getParameter ("FT_NM_Pagamento"));

      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));

      String oid_Programacao_Carga = request.getParameter ("oid_Programacao_Carga");
      if(JavaUtil.doValida(oid_Programacao_Carga)  && !"------".equals(oid_Programacao_Carga)){
      	ed.setOid_Programacao_Carga(new Long(oid_Programacao_Carga).longValue());
      }

      return Ordem_Fretern.inclui (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public Ordem_FreteED consulta_Saldo_Adiantamento (String oid_Pessoa , String oid_Motorista , String oid_Veiculo) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Pessoa (oid_Pessoa);
      ed.setOID_Motorista (oid_Motorista);
      ed.setOID_Veiculo (oid_Veiculo);

      return Ordem_Fretern.consulta_Saldo_Adiantamento (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public Ordem_FreteED consulta_Saldo_Adiantamento (String oid_Pessoa , String oid_Motorista , String oid_Veiculo , String oid_Manifesto) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Pessoa (oid_Pessoa);
      ed.setOID_Motorista (oid_Motorista);
      ed.setOID_Veiculo (oid_Veiculo);
      ed.setOID_Manifesto (oid_Manifesto);

      return Ordem_Fretern.consulta_Saldo_Adiantamento (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public void altera (HttpServletRequest request) throws Excecoes {
    String oid_Motorista = request.getParameter ("oid_Motorista");

    Ordem_FreteED ed = new Ordem_FreteED ();

    ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
    ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    ed.setDM_Tipo_Pedagio (request.getParameter ("FT_DM_Tipo_Pedagio"));
    ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Adiantamento1"));
    ed.setDT_Adiantamento2 (request.getParameter ("FT_DT_Adiantamento2"));
    ed.setDT_Saldo (request.getParameter ("FT_DT_Saldo"));
    ed.setNR_Transacao_Pedagio (request.getParameter ("FT_NR_Transacao_Pedagio"));

    ed.setOID_Unidade_Adiantamento1 (ed.getOID_Unidade ());
    ed.setOID_Unidade_Adiantamento2 (ed.getOID_Unidade ());
    ed.setOID_Unidade_Saldo (ed.getOID_Unidade ());

    String oid_Unidade = request.getParameter ("oid_Unidade_Adiantamento1");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade_Adiantamento1 (new Long (oid_Unidade).longValue ());
    }
    oid_Unidade = request.getParameter ("oid_Unidade_Adiantamento2");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade_Adiantamento2 (new Long (oid_Unidade).longValue ());
    }
    oid_Unidade = request.getParameter ("oid_Unidade_Saldo");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade_Saldo (new Long (oid_Unidade).longValue ());
    }

    ed.setDM_Tipo_Pagamento_Adiantamento1 ("2");

    ed.setDM_Tipo_Pagamento_Adiantamento2 ("2");
    ed.setDM_Tipo_Pagamento_Saldo ("2");
    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento_Adiantamento1");
    if (String.valueOf (DM_Tipo_Pagamento) != null &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("") &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
      ed.setDM_Tipo_Pagamento_Adiantamento1 (DM_Tipo_Pagamento);
    }
    DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento_Adiantamento2");
    if (String.valueOf (DM_Tipo_Pagamento) != null &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("") &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
      ed.setDM_Tipo_Pagamento_Adiantamento2 (DM_Tipo_Pagamento);
    }
    DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento_Saldo");
    if (String.valueOf (DM_Tipo_Pagamento) != null &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("") &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
      ed.setDM_Tipo_Pagamento_Saldo (DM_Tipo_Pagamento);
    }

    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    if (String.valueOf (TX_Observacao) != null &&
        !String.valueOf (TX_Observacao).equals ("") &&
        !String.valueOf (TX_Observacao).equals ("null")) {
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
    }

    String VL_Liquido_Ordem_Frete = request.getParameter ("FT_VL_Liquido_Ordem_Frete");
    if (String.valueOf (VL_Liquido_Ordem_Frete) != null &&
        !String.valueOf (VL_Liquido_Ordem_Frete).equals ("") &&
        !String.valueOf (VL_Liquido_Ordem_Frete).equals ("null")) {
      ed.setVL_Liquido_Ordem_Frete (new Double (VL_Liquido_Ordem_Frete).doubleValue ());
    }

    String VL_Multa_Atrazo = request.getParameter ("FT_VL_Multa_Atrazo");
    if (String.valueOf (VL_Multa_Atrazo) != null &&
        !String.valueOf (VL_Multa_Atrazo).equals ("") &&
        !String.valueOf (VL_Multa_Atrazo).equals ("null")) {
      ed.setVL_Multa_Atrazo (new Double (VL_Multa_Atrazo).doubleValue ());
    }

    String VL_INSS_Empresa = request.getParameter ("FT_VL_INSS_Empresa");
    if (String.valueOf (VL_INSS_Empresa) != null &&
        !String.valueOf (VL_INSS_Empresa).equals ("") &&
        !String.valueOf (VL_INSS_Empresa).equals ("null")) {
      ed.setVL_INSS_Empresa (new Double (VL_INSS_Empresa).doubleValue ());
    }

    String VL_INSS_Prestador = request.getParameter ("FT_VL_INSS_Prestador");
    if (String.valueOf (VL_INSS_Prestador) != null &&
        !String.valueOf (VL_INSS_Prestador).equals ("") &&
        !String.valueOf (VL_INSS_Prestador).equals ("null")) {
      ed.setVL_INSS_Prestador (new Double (VL_INSS_Prestador).doubleValue ());
    }

    String VL_Adiantamento1 = request.getParameter ("FT_VL_Adiantamento1");
    if (String.valueOf (VL_Adiantamento1) != null &&
        !String.valueOf (VL_Adiantamento1).equals ("") &&
        !String.valueOf (VL_Adiantamento1).equals ("null")) {
      ed.setVL_Adiantamento1 (new Double (VL_Adiantamento1).doubleValue ());
    }

    String VL_Adiantamento2 = request.getParameter ("FT_VL_Adiantamento2");
    if (String.valueOf (VL_Adiantamento2) != null &&
        !String.valueOf (VL_Adiantamento2).equals ("") &&
        !String.valueOf (VL_Adiantamento2).equals ("null")) {
      ed.setVL_Adiantamento2 (new Double (VL_Adiantamento2).doubleValue ());
    }

    String VL_Saldo = request.getParameter ("FT_VL_Saldo");
    if (String.valueOf (VL_Saldo) != null &&
        !String.valueOf (VL_Saldo).equals ("") &&
        !String.valueOf (VL_Saldo).equals ("null")) {
      ed.setVL_Saldo (new Double (VL_Saldo).doubleValue ());
    }

    String VL_Ordem_Frete = request.getParameter ("FT_VL_Ordem_Frete");
    if (String.valueOf (VL_Ordem_Frete) != null &&
        !String.valueOf (VL_Ordem_Frete).equals ("") &&
        !String.valueOf (VL_Ordem_Frete).equals ("null")) {
      ed.setVL_Ordem_Frete (new Double (VL_Ordem_Frete).doubleValue ());
    }

    String VL_Pedagio = request.getParameter ("FT_VL_Pedagio");
    if (String.valueOf (VL_Pedagio) != null &&
        !String.valueOf (VL_Pedagio).equals ("") &&
        !String.valueOf (VL_Pedagio).equals ("null")) {
      ed.setVL_Pedagio (new Double (VL_Pedagio).doubleValue ());
    }

    String VL_Set_Senat = request.getParameter ("FT_VL_Set_Senat");
    if (String.valueOf (VL_Set_Senat) != null &&
        !String.valueOf (VL_Set_Senat).equals ("") &&
        !String.valueOf (VL_Set_Senat).equals ("null")) {
      ed.setVL_Set_Senat (new Double (VL_Set_Senat).doubleValue ());
    }

    String VL_Taxa_Expediente = request.getParameter ("FT_VL_Taxa_Expediente");
    if (String.valueOf (VL_Taxa_Expediente) != null &&
        !String.valueOf (VL_Taxa_Expediente).equals ("") &&
        !String.valueOf (VL_Taxa_Expediente).equals ("null")) {
      ed.setVL_Taxa_Expediente (new Double (VL_Taxa_Expediente).doubleValue ());
    }

    String VL_IRRF = request.getParameter ("FT_VL_IRRF");
    if (String.valueOf (VL_IRRF) != null &&
        !String.valueOf (VL_IRRF).equals ("") &&
        !String.valueOf (VL_IRRF).equals ("null")) {
      ed.setVL_IRRF (new Double (VL_IRRF).doubleValue ());
    }

    String NR_Qtde_Coleta = request.getParameter ("FT_NR_Qtde_Coleta");
    if (String.valueOf (NR_Qtde_Coleta) != null &&
        !String.valueOf (NR_Qtde_Coleta).equals ("") &&
        !String.valueOf (NR_Qtde_Coleta).equals ("null")) {
      ed.setNR_Qtde_Coleta (new Long (NR_Qtde_Coleta).longValue ());
    }
    String NR_Qtde_Entrega = request.getParameter ("FT_NR_Qtde_Entrega");
    if (String.valueOf (NR_Qtde_Entrega) != null &&
        !String.valueOf (NR_Qtde_Entrega).equals ("") &&
        !String.valueOf (NR_Qtde_Entrega).equals ("null")) {
      ed.setNR_Qtde_Entrega (new Long (NR_Qtde_Entrega).longValue ());
    }

    String VL_Descontos = request.getParameter ("FT_VL_Descontos");
    if (String.valueOf (VL_Descontos) != null &&
        !String.valueOf (VL_Descontos).equals ("") &&
        !String.valueOf (VL_Descontos).equals ("null")) {
      ed.setVL_Descontos (new Double (VL_Descontos).doubleValue ());
    }

    String VL_Carga = request.getParameter ("FT_VL_Carga");
    if (String.valueOf (VL_Carga) != null &&
        !String.valueOf (VL_Carga).equals ("") &&
        !String.valueOf (VL_Carga).equals ("null")) {
      ed.setVL_Carga (new Double (VL_Carga).doubleValue ());
    }

    String VL_Descarga = request.getParameter ("FT_VL_Descarga");
    if (String.valueOf (VL_Descarga) != null &&
        !String.valueOf (VL_Descarga).equals ("") &&
        !String.valueOf (VL_Descarga).equals ("null")) {
      ed.setVL_Descarga (new Double (VL_Descarga).doubleValue ());
    }

    String VL_Coleta = request.getParameter ("FT_VL_Coleta");
    if (String.valueOf (VL_Coleta) != null &&
        !String.valueOf (VL_Coleta).equals ("") &&
        !String.valueOf (VL_Coleta).equals ("null")) {
      ed.setVL_Coleta (new Double (VL_Coleta).doubleValue ());
    }
    String VL_Premio = request.getParameter ("FT_VL_Premio");
    if (String.valueOf (VL_Premio) != null &&
        !String.valueOf (VL_Premio).equals ("") &&
        !String.valueOf (VL_Premio).equals ("null")) {
      ed.setVL_Premio (new Double (VL_Premio).doubleValue ());
    }

    String VL_Outros = request.getParameter ("FT_VL_Outros");
    if (String.valueOf (VL_Outros) != null &&
        !String.valueOf (VL_Outros).equals ("") &&
        !String.valueOf (VL_Outros).equals ("null")) {
      ed.setVL_Outros (new Double (VL_Outros).doubleValue ());
    }
    String VL_Vale_Pedagio = request.getParameter ("FT_VL_Vale_Pedagio");
    if (String.valueOf (VL_Vale_Pedagio) != null &&
        !String.valueOf (VL_Vale_Pedagio).equals ("") &&
        !String.valueOf (VL_Vale_Pedagio).equals ("null")) {
      ed.setVL_Vale_Pedagio (new Double (VL_Vale_Pedagio).doubleValue ());
    }
    String VL_Vale_Pedagio_Empresa = request.getParameter ("FT_VL_Vale_Pedagio_Empresa");
    if (String.valueOf (VL_Vale_Pedagio_Empresa) != null &&
        !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("") &&
        !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("null")) {
      ed.setVL_Vale_Pedagio_Empresa (new Double (VL_Vale_Pedagio_Empresa).doubleValue ());
    }

    String VL_INSS_Pago = request.getParameter ("FT_VL_INSS_Pago");
    if (String.valueOf (VL_INSS_Pago) != null &&
        !String.valueOf (VL_INSS_Pago).equals ("") &&
        !String.valueOf (VL_INSS_Pago).equals ("null")) {
      ed.setVL_INSS_Pago (new Double (VL_INSS_Pago).doubleValue ());
    }

    ed.setNM_Pagamento (request.getParameter ("FT_NM_Pagamento"));

    DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("") &&
        !String.valueOf (DM_Tipo_Pagamento).equals ("null")) {
      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
    }
    if (util.doValida (oid_Motorista)) {
      ed.setOID_Motorista (oid_Motorista);
    }
    new Ordem_FreteRN ().altera (ed);
  }

  public void rateio_Ordem_Frete (HttpServletRequest request) throws Excecoes {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      Ordem_Fretern.rateio_Ordem_Frete (ed);
  }

  public void altera_Plano_Viagem (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

//// System.out.println(" altera  plano iu  1  ");
      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
//// System.out.println(" altera  plano iu  2  ");

      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (String.valueOf (TX_Observacao) != null &&
          !String.valueOf (TX_Observacao).equals ("") &&
          !String.valueOf (TX_Observacao).equals ("null")) {
        ed.setTX_Observacao_Plano (request.getParameter ("FT_TX_Observacao"));
      }
      String var = "";
      ed.setNM_Roteiro1 (var);
      ed.setNM_Roteiro2 (var);
      ed.setNM_Roteiro3 (var);
      ed.setNM_Roteiro4 (var);
      ed.setNM_Roteiro5 (var);
      ed.setNM_Roteiro6 (var);
      ed.setNM_Roteiro7 (var);
      ed.setNM_Roteiro8 (var);
      ed.setNM_Roteiro9 (var);
      ed.setNM_Roteiro10 (var);
      ed.setDM_Check1 (var);
      ed.setDM_Check2 (var);
      ed.setDM_Check3 (var);
      ed.setDM_Check4 (var);
      ed.setDM_Check5 (var);
      ed.setDM_Check6 (var);
      ed.setDM_Check7 (var);
      ed.setDM_Check8 (var);
      ed.setDM_Check9 (var);
      ed.setDM_Check10 (var);
      ed.setDM_Check11 (var);
      ed.setDT_Ponto1 (var);
      ed.setDT_Ponto2 (var);
      ed.setDT_Ponto3 (var);
      ed.setDT_Ponto4 (var);
      ed.setDT_Ponto5 (var);
      ed.setDT_Ponto6 (var);
      ed.setDT_Ponto7 (var);
      ed.setDT_Ponto8 (var);
      ed.setHR_Ponto1 (var);
      ed.setHR_Ponto2 (var);
      ed.setHR_Ponto3 (var);
      ed.setHR_Ponto4 (var);
      ed.setHR_Ponto5 (var);
      ed.setHR_Ponto6 (var);
      ed.setHR_Ponto7 (var);
      ed.setHR_Ponto8 (var);
      ed.setNM_Ponto1 (var);
      ed.setNM_Ponto2 (var);
      ed.setNM_Ponto3 (var);
      ed.setNM_Ponto4 (var);
      ed.setNM_Ponto5 (var);
      ed.setNM_Ponto6 (var);
      ed.setNM_Ponto7 (var);
      ed.setNM_Ponto8 (var);

      var = request.getParameter ("FT_NM_Roteiro1");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro1 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro2");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro2 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro3");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro3 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro4");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro4 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro5");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro5 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro6");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro6 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro7");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro7 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro8");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro8 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro9");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro9 (var);
      }
      var = request.getParameter ("FT_NM_Roteiro10");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Roteiro10 (var);
      }

      var = request.getParameter ("FT_DM_Check1");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check1 (var);
      }
      var = request.getParameter ("FT_DM_Check2");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check2 (var);
      }
      var = request.getParameter ("FT_DM_Check3");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check3 (var);
      }
      var = request.getParameter ("FT_DM_Check4");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check4 (var);
      }
      var = request.getParameter ("FT_DM_Check5");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check5 (var);
      }
      var = request.getParameter ("FT_DM_Check6");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check6 (var);
      }
      var = request.getParameter ("FT_DM_Check7");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check7 (var);
      }
      var = request.getParameter ("FT_DM_Check8");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check8 (var);
      }
      var = request.getParameter ("FT_DM_Check9");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check9 (var);
      }
      var = request.getParameter ("FT_DM_Check10");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check10 (var);
      }
      var = request.getParameter ("FT_DM_Check11");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDM_Check11 (var);
      }

      var = request.getParameter ("FT_DT_Ponto1");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto1 (var);
      }
      var = request.getParameter ("FT_DT_Ponto2");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto2 (var);
      }
      var = request.getParameter ("FT_DT_Ponto3");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto3 (var);
      }
      var = request.getParameter ("FT_DT_Ponto4");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto4 (var);
      }
      var = request.getParameter ("FT_DT_Ponto5");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto5 (var);
      }
      var = request.getParameter ("FT_DT_Ponto6");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto6 (var);
      }
      var = request.getParameter ("FT_DT_Ponto7");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto7 (var);
      }
      var = request.getParameter ("FT_DT_Ponto8");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setDT_Ponto8 (var);
      }

      var = request.getParameter ("FT_HR_Ponto1");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto1 (var);
      }
      var = request.getParameter ("FT_HR_Ponto2");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto2 (var);
      }
      var = request.getParameter ("FT_HR_Ponto3");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto3 (var);
      }
      var = request.getParameter ("FT_HR_Ponto4");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto4 (var);
      }
      var = request.getParameter ("FT_HR_Ponto5");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto5 (var);
      }
      var = request.getParameter ("FT_HR_Ponto6");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto6 (var);
      }
      var = request.getParameter ("FT_HR_Ponto7");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto7 (var);
      }
      var = request.getParameter ("FT_HR_Ponto8");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setHR_Ponto8 (var);
      }

      var = request.getParameter ("FT_NM_Ponto1");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto1 (var);
      }
      var = request.getParameter ("FT_NM_Ponto2");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto2 (var);
      }
      var = request.getParameter ("FT_NM_Ponto3");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto3 (var);
      }
      var = request.getParameter ("FT_NM_Ponto4");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto4 (var);
      }
      var = request.getParameter ("FT_NM_Ponto5");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto5 (var);
      }
      var = request.getParameter ("FT_NM_Ponto6");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto6 (var);
      }
      var = request.getParameter ("FT_NM_Ponto7");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto7 (var);
      }
      var = request.getParameter ("FT_NM_Ponto8");
      if (String.valueOf (var) != null &&
          !String.valueOf (var).equals ("") &&
          !String.valueOf (var).equals ("null")) {
        ed.setNM_Ponto8 (var);
      }

//// System.out.println(" altera  plano iu  3  ");


      Ordem_Fretern.altera_Plano_Viagem (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("altera(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      String oid_Ordem_Principal = request.getParameter ("oid_Ordem_Principal");
      if (String.valueOf (oid_Ordem_Principal) != null &&
          !String.valueOf (oid_Ordem_Principal).equals ("") &&
          !String.valueOf (oid_Ordem_Principal).equals ("null")) {
        ed.setOID_Ordem_Principal (request.getParameter ("oid_Ordem_Principal"));
        ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
        ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
        ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Adiantamento"));
      }

      Ordem_Fretern.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("deleta(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void Master_deleta (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      Ordem_Fretern.Master_deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("deleta(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public String cancela (HttpServletRequest request) throws Excecoes {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      ed.setTX_Observacao ("Adto CANCELADO p/" + request.getParameter ("FT_NM_Usuario") + " Motivo : " + request.getParameter ("FT_TX_Observacao"));

      return Ordem_Fretern.cancela (ed);
  }

  public void Libera_Saldo (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      Ordem_Fretern.Libera_Saldo (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("deleta(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void desvincula_Adto_Ordem_Principal (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      Ordem_Fretern.desvincula_Adto_Ordem_Principal (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao desvincula_Adto_Ordem_Principal");
      excecoes.setMetodo ("desvincula_Adto_Ordem_Principal(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList Ordem_Frete_Lista (HttpServletRequest request) throws Excecoes {

    Ordem_FreteED ed = new Ordem_FreteED ();

    ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    ed.setOID_Veiculo (request.getParameter ("FT_NR_Placa"));
    ed.setNR_Placa (request.getParameter ("FT_NR_Placa"));
    ed.setDM_Frete (request.getParameter ("FT_DM_Frete"));
    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    String nr_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
    if (String.valueOf (nr_Ordem_Frete) != null &&
        !String.valueOf (nr_Ordem_Frete).equals ("") &&
        !String.valueOf (nr_Ordem_Frete).equals ("null")) {
      ed.setNR_Ordem_Frete (new Long (nr_Ordem_Frete).longValue ());
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Motorista = request.getParameter ("oid_Motorista");
    if (JavaUtil.doValida (oid_Motorista)) {
      ed.setOID_Motorista (oid_Motorista);
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (JavaUtil.doValida (oid_Pessoa)) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (JavaUtil.doValida (oid_Fornecedor)) {
      ed.setOID_Fornecedor (oid_Fornecedor);
    }

    // System.out.println ("// Alteracao GM - consulta pelo nr_acerto ou s/ acerto");
    // System.out.println ("teo ====== > " + request.getParameter ("CF_Acerto"));
    // System.out.println (request.getParameter ("FT_NR_Acerto"));
    String dm_Acerto = request.getParameter ("CF_Acerto");
    ed.setDm_Stamp (dm_Acerto);
    String nr_Acerto = request.getParameter ("FT_NR_Acerto");
    if (String.valueOf (nr_Acerto) != null &&
        !String.valueOf (nr_Acerto).equals ("") &&
        !String.valueOf (nr_Acerto).equals ("null")) {
      ed.setNR_Acerto (new Long (nr_Acerto).longValue ());
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    return new Ordem_FreteRN ().lista (ed);

  }

  public ArrayList lista_Adiantamentos_Manifestos (HttpServletRequest request) throws Excecoes {
    String DM_Tipo_Adiantamento = request.getParameter ("FT_DM_Tipo_Adiantamento");

    Ordem_FreteED ed = new Ordem_FreteED ();

    ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    ed.setOID_Veiculo (request.getParameter ("FT_NR_Placa"));
    ed.setNR_Placa (request.getParameter ("FT_NR_Placa"));
    ed.setDM_Frete (request.getParameter ("FT_DM_Frete"));

    String nr_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
    if (String.valueOf (nr_Ordem_Frete) != null &&
        !String.valueOf (nr_Ordem_Frete).equals ("") &&
        !String.valueOf (nr_Ordem_Frete).equals ("null")) {
      ed.setNR_Ordem_Frete (new Long (nr_Ordem_Frete).longValue ());
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Motorista = request.getParameter ("oid_Motorista");
    if (JavaUtil.doValida (oid_Motorista)) {
      ed.setOID_Motorista (oid_Motorista);
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (JavaUtil.doValida (oid_Pessoa)) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    // System.out.println ("// Alteracao GM - consulta pelo nr_acerto ou s/ acerto");
    // System.out.println ("teo ====== > " + request.getParameter ("CF_Acerto"));
    // System.out.println (request.getParameter ("FT_NR_Acerto"));
    String dm_Acerto = request.getParameter ("CF_Acerto");
    ed.setDm_Stamp (dm_Acerto);
    String nr_Acerto = request.getParameter ("FT_NR_Acerto");
    if (String.valueOf (nr_Acerto) != null &&
        !String.valueOf (nr_Acerto).equals ("") &&
        !String.valueOf (nr_Acerto).equals ("null")) {
      ed.setNR_Acerto (new Long (nr_Acerto).longValue ());
    }
    if (util.doValida (DM_Tipo_Adiantamento)) {
      ed.setDM_Tipo_Adiantamento (DM_Tipo_Adiantamento);
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    String DM_Acerto = request.getParameter ("FT_DM_Acerto");
    if (String.valueOf (DM_Acerto) != null && !String.valueOf (DM_Acerto).equals ("")) {
      ed.setDM_Acerto (DM_Acerto);
    }

    return new Ordem_FreteRN ().lista_Adiantamentos_Manifestos (ed);

  }

  public ArrayList lista_Adiantamento_Vinculado (HttpServletRequest request) throws Excecoes {

    Ordem_FreteED ed = new Ordem_FreteED ();

    ed.setOID_Ordem_Principal (request.getParameter ("oid_Ordem_Frete"));

    // System.out.println ("lista_Adiantamento_Vinculado ->> " + request.getParameter ("oid_Ordem_Frete"));

    return new Ordem_FreteRN ().lista_Adiantamento_Vinculado (ed);

  }

  public Ordem_FreteED getByRecord (String oid_Ordem_Frete) throws Excecoes {
    Ordem_FreteED ed = new Ordem_FreteED ();
    ed.setOID_Ordem_Frete (oid_Ordem_Frete);
    return new Ordem_FreteRN ().getByRecord (ed);
  }

  public Ordem_FreteED getByRecord (HttpServletRequest request) throws Excecoes {
    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
    Ordem_FreteED ed = new Ordem_FreteED ();
    if (JavaUtil.doValida (oid_Ordem_Frete)) {
      ed.setOID_Ordem_Frete (oid_Ordem_Frete);
    }
    else {
      String NR_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
      if (String.valueOf (NR_Ordem_Frete) != null &&
          !String.valueOf (NR_Ordem_Frete).equals ("") &&
          !String.valueOf (NR_Ordem_Frete).equals ("null")) {
        ed.setNR_Ordem_Frete (new Long (NR_Ordem_Frete).longValue ());
      }
      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (util.doValida (oid_Unidade)) {
        ed.setOID_Unidade (Long.parseLong (oid_Unidade));
      }
    }
    return new Ordem_FreteRN ().getByRecord (ed);
  }

  public Ordem_FreteED getByRecord_Plano_Viagem (HttpServletRequest request) throws Excecoes {

    Ordem_FreteED ed = new Ordem_FreteED ();

    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
    if (String.valueOf (oid_Ordem_Frete) != null &&
        !String.valueOf (oid_Ordem_Frete).equals ("") &&
        !String.valueOf (oid_Ordem_Frete).equals ("null")) {
      ed.setOID_Ordem_Frete (oid_Ordem_Frete);
    }

    return new Ordem_FreteRN ().getByRecord_Plano_Viagem (ed);
  }

  public void OrdemFreteRelatorio (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    Ordem_FreteED ed = new Ordem_FreteED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("") && !String.valueOf (oid_Pessoa_Pagador).equals ("null")) {
      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
    }

    // System.out.println(" PEssoa->> " + request.getParameter ("oid_Pessoa_Pagador"));


    String oid_Motorista = request.getParameter ("oid_Motorista");
    if (String.valueOf (oid_Motorista) != null && !String.valueOf (oid_Motorista).equals ("") && !String.valueOf (oid_Motorista).equals ("null")) {
      ed.setOID_Motorista (oid_Motorista);
    }

    String oid_Local_Pagamento = request.getParameter ("oid_Local_Pagamento");
    if (String.valueOf (oid_Local_Pagamento) != null && !String.valueOf (oid_Local_Pagamento).equals ("") && !String.valueOf (oid_Local_Pagamento).equals ("null")) {
      ed.setOID_Local_Pagamento (oid_Local_Pagamento);
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (String.valueOf (DM_Frete) != null && !String.valueOf (DM_Frete).equals ("")) {
      ed.setDM_Frete (DM_Frete);
    }

    String DM_Tipo_Pessoa = request.getParameter ("FT_DM_Tipo_Pessoa");
    if (String.valueOf (DM_Tipo_Pessoa) != null && !String.valueOf (DM_Tipo_Pessoa).equals ("")) {
      ed.setDM_Tipo_Pessoa (DM_Tipo_Pessoa);
    }
    // System.out.println ("DM_Tipo_Pessoa->>" + DM_Tipo_Pessoa);

    String DM_Acerto = request.getParameter ("FT_DM_Acerto");
    if (String.valueOf (DM_Acerto) != null && !String.valueOf (DM_Acerto).equals ("")) {
      ed.setDM_Acerto (DM_Acerto);
    }

    String DM_Adiantamento = request.getParameter ("FT_DM_Adiantamento");
    if (String.valueOf (DM_Adiantamento) != null && !String.valueOf (DM_Adiantamento).equals ("")) {
      ed.setDM_Adiantamento (DM_Adiantamento);
    }

    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    //// System.out.println("oid_Empresa" +  ed.getOID_Empresa() );

    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    //// System.out.println("FT_NM_Empresa" +  ed.getNM_Empresa() );



    ed.setDM_Lista_Conhecimento (request.getParameter ("FT_DM_Lista_Conhecimento"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));
    // System.out.println ("FT_DM_Relatorio->>" + request.getParameter ("FT_DM_Relatorio"));

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    Ordem_FreteRN geRN = new Ordem_FreteRN ();
    new EnviaPDF ().enviaBytes (request , Response , geRN.OrdemFreteRelatorio (ed));

  }

  public Ordem_FreteED calcula_Ordem_Frete (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

      ed.setOID_Fornecedor (request.getParameter ("oid_Pessoa"));
      String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
      if (String.valueOf (oid_Fornecedor) != null &&
          !String.valueOf (oid_Fornecedor).equals ("") &&
          !String.valueOf (oid_Fornecedor).equals ("null")) {
        ed.setOID_Fornecedor (oid_Fornecedor);
      }
      String oid_Motorista = request.getParameter ("oid_Motorista");
      if (JavaUtil.doValida (oid_Motorista)) {
        ed.setOID_Motorista (oid_Motorista);
      }

      // System.out.println (" calculo 1  ");

      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (String.valueOf (oid_Unidade) != null &&
          !String.valueOf (oid_Unidade).equals ("") &&
          !String.valueOf (oid_Unidade).equals ("null")) {
        ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      }
      ///###  MEGA EMPRESA
      String oid_Empresa = request.getParameter ("oid_Empresa");
      if (String.valueOf (oid_Empresa) != null &&
          !String.valueOf (oid_Empresa).equals ("") &&
          !String.valueOf (oid_Empresa).equals ("null")) {
        ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
      }

      // System.out.println (" calculo 3  ");

      ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));

      ed.setDT_Saldo (request.getParameter ("FT_DT_Saldo"));

      ed.setDM_Adiantamento (request.getParameter ("FT_DM_Adiantamento"));

      String VL_Adiantamento1 = request.getParameter ("FT_VL_Adiantamento1");
      if (String.valueOf (VL_Adiantamento1) != null &&
          !String.valueOf (VL_Adiantamento1).equals ("") &&
          !String.valueOf (VL_Adiantamento1).equals ("null")) {
        ed.setVL_Adiantamento1 (new Double (VL_Adiantamento1).doubleValue ());
      }

      String VL_Adiantamento2 = request.getParameter ("FT_VL_Adiantamento2");
      if (String.valueOf (VL_Adiantamento2) != null &&
          !String.valueOf (VL_Adiantamento2).equals ("") &&
          !String.valueOf (VL_Adiantamento2).equals ("null")) {
        ed.setVL_Adiantamento2 (new Double (VL_Adiantamento2).doubleValue ());
      }

      // System.out.println (" calculo 3 b  ");

      String VL_Saldo = request.getParameter ("FT_VL_Saldo");
      if (String.valueOf (VL_Saldo) != null &&
          !String.valueOf (VL_Saldo).equals ("") &&
          !String.valueOf (VL_Saldo).equals ("null")) {
        ed.setVL_Saldo (new Double (VL_Saldo).doubleValue ());
      }

      String VL_Ordem_Frete = request.getParameter ("FT_VL_Ordem_Frete");
      if (String.valueOf (VL_Ordem_Frete) != null &&
          !String.valueOf (VL_Ordem_Frete).equals ("") &&
          !String.valueOf (VL_Ordem_Frete).equals ("null")) {
        // System.out.println (" calculo VL_Ordem_Frete  " + VL_Ordem_Frete);

        ed.setVL_Ordem_Frete (new Double (VL_Ordem_Frete).doubleValue ());
      }

      // System.out.println (" calculo 3 3  ");

      String VL_Descontos = request.getParameter ("FT_VL_Descontos");
      if (String.valueOf (VL_Descontos) != null &&
          !String.valueOf (VL_Descontos).equals ("") &&
          !String.valueOf (VL_Descontos).equals ("null")) {
        // System.out.println (" calculo 3 3  " + VL_Descontos);

        ed.setVL_Descontos (new Double (VL_Descontos).doubleValue ());
      }

      String VL_Multa = request.getParameter ("FT_VL_Multa");
      if (String.valueOf (VL_Multa) != null &&
          !String.valueOf (VL_Multa).equals ("") &&
          !String.valueOf (VL_Multa).equals ("null")) {
        // System.out.println (" calculo Multa  " + VL_Multa);

        ed.setVL_Multa (new Double (VL_Multa).doubleValue ());
      }

      String VL_Reembolso = request.getParameter ("FT_VL_Reembolso");
      if (String.valueOf (VL_Reembolso) != null &&
          !String.valueOf (VL_Reembolso).equals ("") &&
          !String.valueOf (VL_Reembolso).equals ("null")) {
        // System.out.println (" calculo Reembolso  " + VL_Reembolso);

        ed.setVL_Reembolso (new Double (VL_Reembolso).doubleValue ());
      }

      String VL_Estadia = request.getParameter ("FT_VL_Estadia");
      if (String.valueOf (VL_Estadia) != null &&
          !String.valueOf (VL_Estadia).equals ("") &&
          !String.valueOf (VL_Estadia).equals ("null")) {
        // System.out.println (" calculo Estadia  " + VL_Estadia);

        ed.setVL_Estadia (new Double (VL_Estadia).doubleValue ());
      }

      // System.out.println (" calculo 3 e  ");

      String VL_Set_Senat = request.getParameter ("FT_VL_Set_Senat");
      if (String.valueOf (VL_Set_Senat) != null &&
          !String.valueOf (VL_Set_Senat).equals ("") &&
          !String.valueOf (VL_Set_Senat).equals ("null")) {
        ed.setVL_Set_Senat (new Double (VL_Set_Senat).doubleValue ());
      }

      // System.out.println (" calculo 5  ");

      String VL_Pedagio = request.getParameter ("FT_VL_Pedagio");
      if (String.valueOf (VL_Pedagio) != null &&
          !String.valueOf (VL_Pedagio).equals ("") &&
          !String.valueOf (VL_Pedagio).equals ("null")) {
        ed.setVL_Pedagio (new Double (VL_Pedagio).doubleValue ());
      }

      String VL_Coleta = request.getParameter ("FT_VL_Coleta");
      if (String.valueOf (VL_Coleta) != null &&
          !String.valueOf (VL_Coleta).equals ("") &&
          !String.valueOf (VL_Coleta).equals ("null")) {
        ed.setVL_Coleta (new Double (VL_Coleta).doubleValue ());
      }

      String VL_Carga = request.getParameter ("FT_VL_Carga");
      if (String.valueOf (VL_Carga) != null &&
          !String.valueOf (VL_Carga).equals ("") &&
          !String.valueOf (VL_Carga).equals ("null")) {
        ed.setVL_Carga (new Double (VL_Carga).doubleValue ());
      }

      String VL_Descarga = request.getParameter ("FT_VL_Descarga");
      if (String.valueOf (VL_Descarga) != null &&
          !String.valueOf (VL_Descarga).equals ("") &&
          !String.valueOf (VL_Descarga).equals ("null")) {
        ed.setVL_Descarga (new Double (VL_Descarga).doubleValue ());
      }

      String VL_Premio = request.getParameter ("FT_VL_Premio");
      if (String.valueOf (VL_Premio) != null &&
          !String.valueOf (VL_Premio).equals ("") &&
          !String.valueOf (VL_Premio).equals ("null")) {
        ed.setVL_Premio (new Double (VL_Premio).doubleValue ());
      }
      String VL_Outros = request.getParameter ("FT_VL_Outros");
      if (String.valueOf (VL_Outros) != null &&
          !String.valueOf (VL_Outros).equals ("") &&
          !String.valueOf (VL_Outros).equals ("null")) {
        ed.setVL_Outros (new Double (VL_Outros).doubleValue ());
      }

      // System.out.println (" calculo 9  ");

      String VL_Vale_Pedagio = request.getParameter ("FT_VL_Vale_Pedagio");
      if (String.valueOf (VL_Vale_Pedagio) != null &&
          !String.valueOf (VL_Vale_Pedagio).equals ("") &&
          !String.valueOf (VL_Vale_Pedagio).equals ("null")) {
        ed.setVL_Vale_Pedagio (new Double (VL_Vale_Pedagio).doubleValue ());
      }
      String VL_Vale_Pedagio_Empresa = request.getParameter ("FT_VL_Vale_Pedagio_Empresa");
      if (String.valueOf (VL_Vale_Pedagio_Empresa) != null &&
          !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("") &&
          !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("null")) {
        ed.setVL_Vale_Pedagio_Empresa (new Double (VL_Vale_Pedagio_Empresa).doubleValue ());
      }

      String VL_INSS_Pago = request.getParameter ("FT_VL_INSS_Pago");
      if (String.valueOf (VL_INSS_Pago) != null &&
          !String.valueOf (VL_INSS_Pago).equals ("") &&
          !String.valueOf (VL_INSS_Pago).equals ("null")) {
        ed.setVL_INSS_Pago (new Double (VL_INSS_Pago).doubleValue ());
      }

      return Ordem_Fretern.calcula_Ordem_Frete (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Calcular IRRF");
      excecoes.setMetodo ("calcula");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Ordem_FreteED transfere_Adiantamento_para_Cota_Corrente (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));

      return Ordem_Fretern.transfere_Adiantamento_para_Cota_Corrente (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro");
      excecoes.setMetodo ("transfere_Adiantamento_para_Cota_Corrente");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public Ordem_FreteED inclui_adiantamento (HttpServletRequest request) throws Excecoes {

	String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Local_Pagamento = request.getParameter ("oid_Local_Pagamento");
    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    String NR_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
    String VL_Ordem_Frete = request.getParameter ("FT_VL_Ordem_Frete");
    String VL_Cotacao = request.getParameter ("FT_VL_Cotacao");
    String VL_Cotacao_Padrao = request.getParameter ("FT_VL_Cotacao_Padrao");
    String VL_Saldo = request.getParameter ("FT_VL_Saldo");
    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    String oid_Manifesto = request.getParameter ("oid_Manifesto");
    String oid_MIC = request.getParameter ("oid_MIC");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String DM_Tipo_Adiantamento = request.getParameter ("FT_DM_Tipo_Adiantamento");
    String DT_Pagamento = request.getParameter ("FT_DT_Pagamento");
    String NR_Pagamento = request.getParameter ("FT_NR_Cheque_Adto");

    Ordem_FreteED ed = new Ordem_FreteED ();

    ed.setDM_Tipo_Pagamento ("A");
    if (util.doValida (DT_Pagamento)) {
        ed.setDT_Pagamento (request.getParameter ("FT_DT_Pagamento"));
      }
    if (util.doValida (NR_Pagamento)) {
        ed.setDT_Pagamento (request.getParameter ("FT_NR_Cheque_Adto"));
      }

    if (util.doValida (DM_Tipo_Pagamento)) {
      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
    }
    if (util.doValida (oid_Pessoa)) {
      ed.setOID_Pessoa (oid_Pessoa);
    }
    else {
      throw new Mensagens ("Parâmetros incorretos");
    }
    if (util.doValida (NR_Ordem_Frete)) {
      ed.setNR_Ordem_Frete (Long.parseLong (NR_Ordem_Frete));
    }
    else {
      ed.setNR_Ordem_Frete (0);
    }
    if (util.doValida (oid_Fornecedor)) {
      ed.setOID_Fornecedor (oid_Fornecedor);
    }
    else {
      ed.setOID_Fornecedor (oid_Pessoa);
    }

    ed.setOID_Local_Pagamento (oid_Pessoa);
    if (util.doValida (oid_Local_Pagamento)) {
      ed.setOID_Local_Pagamento (oid_Local_Pagamento);
    }

    if ("P".equals(ed.getDM_Tipo_Pagamento())){///posto
      ed.setOID_Local_Pagamento (ed.getOID_Fornecedor());
    }

    String VL_Total_Frete_CTRC = request.getParameter ("FT_VL_Total_Frete_CTRC");
    if (String.valueOf (VL_Total_Frete_CTRC) != null &&
        !String.valueOf (VL_Total_Frete_CTRC).equals ("") &&
        !String.valueOf (VL_Total_Frete_CTRC).equals ("null")) {
      ed.setVL_Total_Frete_CTRC (new Double (VL_Total_Frete_CTRC).doubleValue ());
    }

    ed.setDM_Impresso ("N");
    ed.setDM_Frete ("A");
    ed.setDM_Tipo_Pedagio ("A");

    ed.setNR_Transacao_Pedagio (request.getParameter ("FT_NR_Transacao_Pedagio"));

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    ed.setHR_Emissao (request.getParameter ("FT_HR_Emissao"));
    ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Emissao"));
    ed.setDT_Adiantamento2 (request.getParameter ("FT_DT_Emissao"));
    ed.setDT_Saldo (request.getParameter ("FT_DT_Emissao"));
    // Adto
    ed.setDT_Pagamento (request.getParameter ("FT_DT_Pagamento"));
    ed.setNR_Cheque_Adto (request.getParameter ("FT_NR_Cheque_Adto"));

    ed.setOID_Ordem_Principal ("");

    if (util.doValida (oid_Ordem_Frete)) {
      ed.setOID_Ordem_Principal (request.getParameter ("oid_Ordem_Frete"));
      ed.setNR_Ordem_Frete (Long.parseLong (request.getParameter ("FT_NR_Ordem_Frete")));
      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
      ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Adiantamento"));
    }
    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
    if (util.doValida (TX_Observacao)) {
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
    }
    if (util.doValida (VL_Ordem_Frete)) {
      ed.setVL_Ordem_Frete (new Double (VL_Ordem_Frete).doubleValue ());
      ed.setVL_Adiantamento1 (new Double (VL_Ordem_Frete).doubleValue ());
    }
    if (util.doValida (VL_Cotacao)) {
      ed.setVL_Cotacao (new Double (VL_Cotacao).doubleValue ());
    }
    if (util.doValida (VL_Cotacao_Padrao)) {
      ed.setVL_Cotacao_Padrao (new Double (VL_Cotacao_Padrao).doubleValue ());
    }
    if (util.doValida (VL_Saldo)) {
      ed.setVL_Saldo (new Double (VL_Saldo).doubleValue ());
    }
    if (util.doValida (oid_Manifesto)) {
      ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
    }
    if (util.doValida (oid_MIC)) {
      ed.setOID_MIC (request.getParameter ("oid_MIC"));
    }
    if (util.doValida (oid_Motorista)) {
      ed.setOID_Motorista (oid_Motorista);
    }
    if (util.doValida (DM_Tipo_Adiantamento)) {
      ed.setDM_Tipo_Adiantamento (DM_Tipo_Adiantamento);
    }

    ed.setOID_Unidade_Adiantamento1 (ed.getOID_Unidade ());
    String oid_Unidade_Pagamento = request.getParameter ("oid_Unidade_Pagamento");
    if (util.doValida (oid_Unidade_Pagamento)) {
      ed.setOID_Unidade_Adiantamento1 (new Long (request.getParameter ("oid_Unidade_Pagamento")).longValue ());
    }


    String oid_Usuario = request.getParameter ("oid_Usuario");
    if (JavaUtil.doValida (oid_Usuario)) {
       ed.setOID_Usuario (new Long (request.getParameter ("oid_Usuario")).longValue ());
    }else {

       HttpSession session = request.getSession(true);

       if (session != null && session.getAttribute("usuario") != null
    		   && (session.getAttribute("usuario") instanceof UsuarioED)) {
          UsuarioED usuario = (UsuarioED) session.getAttribute("usuario");
          ed.setOID_Usuario (new Long (String.valueOf(usuario.getOid_Usuario())).longValue ());
       }
    }

    String VL_Coleta = request.getParameter ("FT_VL_Coleta");
    if (String.valueOf (VL_Coleta) != null &&
        !String.valueOf (VL_Coleta).equals ("") &&
        !String.valueOf (VL_Coleta).equals ("null")) {
      ed.setVL_Coleta (new Double (VL_Coleta).doubleValue ());
    }

    String VL_Carga = request.getParameter ("FT_VL_Carga");
    if (String.valueOf (VL_Carga) != null &&
        !String.valueOf (VL_Carga).equals ("") &&
        !String.valueOf (VL_Carga).equals ("null")) {
      ed.setVL_Carga (new Double (VL_Carga).doubleValue ());
    }

    String VL_Descarga = request.getParameter ("FT_VL_Descarga");
    if (String.valueOf (VL_Descarga) != null &&
        !String.valueOf (VL_Descarga).equals ("") &&
        !String.valueOf (VL_Descarga).equals ("null")) {
      ed.setVL_Descarga (new Double (VL_Descarga).doubleValue ());
    }

    String VL_Vale_Pedagio = request.getParameter ("FT_VL_Vale_Pedagio");
    if (String.valueOf (VL_Vale_Pedagio) != null &&
        !String.valueOf (VL_Vale_Pedagio).equals ("") &&
        !String.valueOf (VL_Vale_Pedagio).equals ("null")) {
      ed.setVL_Vale_Pedagio (new Double (VL_Vale_Pedagio).doubleValue ());
    }
    String VL_Vale_Pedagio_Empresa = request.getParameter ("FT_VL_Vale_Pedagio_Empresa");
    if (String.valueOf (VL_Vale_Pedagio_Empresa) != null &&
        !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("") &&
        !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("null")) {
      ed.setVL_Vale_Pedagio_Empresa (new Double (VL_Vale_Pedagio_Empresa).doubleValue ());
    }
    String VL_Outros = request.getParameter ("FT_VL_Outros");
    if (String.valueOf (VL_Outros) != null &&
        !String.valueOf (VL_Outros).equals ("") &&
        !String.valueOf (VL_Outros).equals ("null")) {
      ed.setVL_Outros (new Double (VL_Outros).doubleValue ());
    }

    return new Ordem_FreteRN ().inclui_adiantamento (ed);
  }

  public ArrayList GeraEDI_Ordem_Frete (HttpServletRequest request) throws Excecoes {
    Tipo_EventoED ed = new Tipo_EventoED ();

    ed.setCd_Tipo_Evento (request.getParameter ("FT_CD_Tipo_Evento"));
    ed.setNm_Tipo_Evento (request.getParameter ("FT_NM_Tipo_Evento"));
    ed.setCd_Historico (request.getParameter ("FT_CD_Historico"));
    ed.setCd_Conta_Credito (request.getParameter ("FT_CD_Conta_Credito"));
    ed.setCd_Conta_Debito (request.getParameter ("FT_CD_Conta_Debito"));
    ed.setNm_Arquivo_Saida (request.getParameter ("FT_NM_Arquivo_Saida"));
    ed.setDt_Inicial (request.getParameter ("FT_DT_Inicial"));
    ed.setDt_Final (request.getParameter ("FT_DT_Final"));
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    return new Ordem_FreteRN ().GeraEDI_Ordem_Frete (ed);

  }

  public void alteraVeiculo (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();
      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
      ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));

      Ordem_Fretern.alteraVeiculo (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("altera(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera_Adiantamento (HttpServletRequest request) throws Excecoes {
    String oid_Ordem_Principal = request.getParameter ("oid_Ordem_Principal");
    String oid_Local_Pagamento = request.getParameter ("oid_Local_Pagamento");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String VL_Ordem_Frete = request.getParameter ("FT_VL_Ordem_Frete");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String VL_Cotacao = request.getParameter ("FT_VL_Cotacao");
    String VL_Cotacao_Padrao = request.getParameter ("FT_VL_Cotacao_Padrao");
    String DM_Tipo_Adiantamento = request.getParameter ("FT_DM_Tipo_Adiantamento");

    Ordem_FreteED ed = new Ordem_FreteED ();

    ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
    ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));
    ed.setNR_Transacao_Pedagio (request.getParameter ("FT_NR_Transacao_Pedagio"));

    ed.setDM_Tipo_Pagamento ("A");

    ed.setOID_Ordem_Principal ("");

    if (String.valueOf (oid_Ordem_Principal) != null &&
        !String.valueOf (oid_Ordem_Principal).equals ("") &&
        !String.valueOf (oid_Ordem_Principal).equals ("null")) {
      ed.setOID_Ordem_Principal (request.getParameter ("oid_Ordem_Principal"));
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
      ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Adiantamento"));
    }
    if (String.valueOf (TX_Observacao) != null &&
        !String.valueOf (TX_Observacao).equals ("") &&
        !String.valueOf (TX_Observacao).equals ("null")) {
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
    }
    if (String.valueOf (VL_Ordem_Frete) != null &&
        !String.valueOf (VL_Ordem_Frete).equals ("")) {
      ed.setVL_Ordem_Frete (new Double (VL_Ordem_Frete).doubleValue ());
    }
    if (util.doValida (oid_Motorista)) {
      ed.setOID_Motorista (oid_Motorista);
    }
    if (String.valueOf (VL_Cotacao) != null &&
        !String.valueOf (VL_Cotacao).equals ("") &&
        !String.valueOf (VL_Cotacao).equals ("null")) {
      ed.setVL_Cotacao (new Double (VL_Cotacao).doubleValue ());
    }
    if (String.valueOf (VL_Cotacao_Padrao) != null &&
        !String.valueOf (VL_Cotacao_Padrao).equals ("") &&
        !String.valueOf (VL_Cotacao_Padrao).equals ("null")) {
      ed.setVL_Cotacao_Padrao (new Double (VL_Cotacao_Padrao).doubleValue ());
    }
    if (util.doValida (DM_Tipo_Adiantamento)) {
      ed.setDM_Tipo_Adiantamento (DM_Tipo_Adiantamento);
    }
    if (util.doValida (oid_Local_Pagamento)) {
      ed.setOID_Local_Pagamento (oid_Local_Pagamento);
    }
    String VL_Coleta = request.getParameter ("FT_VL_Coleta");
    if (String.valueOf (VL_Coleta) != null &&
        !String.valueOf (VL_Coleta).equals ("") &&
        !String.valueOf (VL_Coleta).equals ("null")) {
      ed.setVL_Coleta (new Double (VL_Coleta).doubleValue ());
    }

    String VL_Carga = request.getParameter ("FT_VL_Carga");
    if (String.valueOf (VL_Carga) != null &&
        !String.valueOf (VL_Carga).equals ("") &&
        !String.valueOf (VL_Carga).equals ("null")) {
      ed.setVL_Carga (new Double (VL_Carga).doubleValue ());
    }

    String VL_Descarga = request.getParameter ("FT_VL_Descarga");
    if (String.valueOf (VL_Descarga) != null &&
        !String.valueOf (VL_Descarga).equals ("") &&
        !String.valueOf (VL_Descarga).equals ("null")) {
      ed.setVL_Descarga (new Double (VL_Descarga).doubleValue ());
    }

    String VL_Vale_Pedagio = request.getParameter ("FT_VL_Vale_Pedagio");
    if (String.valueOf (VL_Vale_Pedagio) != null &&
        !String.valueOf (VL_Vale_Pedagio).equals ("") &&
        !String.valueOf (VL_Vale_Pedagio).equals ("null")) {
      ed.setVL_Vale_Pedagio (new Double (VL_Vale_Pedagio).doubleValue ());
    }
    String VL_Vale_Pedagio_Empresa = request.getParameter ("FT_VL_Vale_Pedagio_Empresa");
    if (String.valueOf (VL_Vale_Pedagio_Empresa) != null &&
        !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("") &&
        !String.valueOf (VL_Vale_Pedagio_Empresa).equals ("null")) {
      ed.setVL_Vale_Pedagio_Empresa (new Double (VL_Vale_Pedagio_Empresa).doubleValue ());
    }

    String VL_Outros = request.getParameter ("FT_VL_Outros");
    if (String.valueOf (VL_Outros) != null &&
        !String.valueOf (VL_Outros).equals ("") &&
        !String.valueOf (VL_Outros).equals ("null")) {
      ed.setVL_Outros (new Double (VL_Outros).doubleValue ());
    }

    new Ordem_FreteRN ().altera_Adiantamento (ed);
  }

  public void deleta_acerto (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
      String oid_Acerto = request.getParameter ("oid_Acerto");
      if (String.valueOf (oid_Acerto) != null &&
          !String.valueOf (oid_Acerto).equals ("") &&
          !String.valueOf (oid_Acerto).equals ("null")) {
        ed.setOID_Acerto (new Long (oid_Acerto).longValue ());
      }

      Ordem_Fretern.deleta_acerto (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("deleta(HttpServletRequest request)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList Ordem_Frete_Lista_Acerto (HttpServletRequest request) throws Excecoes {

    Ordem_FreteED ed = new Ordem_FreteED ();
    // System.out.println ("teo01");
    ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    ed.setOID_Veiculo (request.getParameter ("FT_NR_Placa"));
    ed.setNR_Placa (request.getParameter ("FT_NR_Placa"));

    String nr_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
    if (String.valueOf (nr_Ordem_Frete) != null &&
        !String.valueOf (nr_Ordem_Frete).equals ("") &&
        !String.valueOf (nr_Ordem_Frete).equals ("null")) {
      ed.setNR_Ordem_Frete (new Long (nr_Ordem_Frete).longValue ());
    }

    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (String.valueOf (DM_Frete) != null &&
        !String.valueOf (DM_Frete).equals ("") &&
        !String.valueOf (DM_Frete).equals ("null")) {
      ed.setDM_Frete (new String (DM_Frete));
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    String oid_Acerto = request.getParameter ("oid_Acerto");
    if (String.valueOf (oid_Acerto) != null &&
        !String.valueOf (oid_Acerto).equals ("") &&
        !String.valueOf (oid_Acerto).equals ("null")) {
      ed.setOID_Acerto (new Long (oid_Acerto).longValue ());
    }
    // System.out.println ("teo02");
    return new Ordem_FreteRN ().Ordem_Frete_Lista_Acerto (ed);

  }

  public byte[] imprime_Plano_Viagem (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    Ordem_FreteED ed = new Ordem_FreteED ();

    String OID_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
    if (String.valueOf (OID_Ordem_Frete) != null &&
        !String.valueOf (OID_Ordem_Frete).equals ("") &&
        !String.valueOf (OID_Ordem_Frete).equals ("null")) {
      ed.setOID_Ordem_Frete (OID_Ordem_Frete);
    }

    Ordem_FreteRN ordem_FreteRN = new Ordem_FreteRN ();
    return ordem_FreteRN.imprime_Plano_Viagem (ed);

  }

  public byte[] imprime_Ordem_Frete_Adiantamento (HttpServletRequest request , HttpServletResponse Response) throws Exception {
    Ordem_FreteED ed = new Ordem_FreteED ();

    // //// System.out.println("be1");


    String nr_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
    if (String.valueOf (nr_Ordem_Frete) != null &&
        !String.valueOf (nr_Ordem_Frete).equals ("") &&
        !String.valueOf (nr_Ordem_Frete).equals ("null")) {
      ed.setNR_Ordem_Frete (new Long (nr_Ordem_Frete).longValue ());
    }
    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
    if (util.doValida (oid_Ordem_Frete)) {
      ed.setOID_Ordem_Frete (oid_Ordem_Frete);
    }
    String oid_Ordem_Principal = request.getParameter ("oid_Ordem_Principal");
    if (util.doValida (oid_Ordem_Principal)) {
      ed.setOID_Ordem_Principal (oid_Ordem_Principal);
    }
    // //// System.out.println("be2");

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    // //// System.out.println("be3");

    Ordem_FreteRN ordem_FreteRN = new Ordem_FreteRN ();

    return ordem_FreteRN.imprime_Ordem_Frete_Adiantamento (ed);

  }

  public String imprime_Ordem_Frete_Adiantamento_Matricial (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

		return new Ordem_FreteRN ().imprime_Ordem_Frete_Adiantamento_Matricial (this.carregaED (request));
  }

  public String imprime_Ordem_Frete_Matricial (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

		return new Ordem_FreteRN ().imprime_Ordem_Frete_Matricial (this.carregaED (request));
}

  ///### Kieling 17062003
  public ArrayList Master_Lista (HttpServletRequest request) throws Excecoes {

    Ordem_FreteED ed = new Ordem_FreteED ();

    ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    ed.setOID_Veiculo (request.getParameter ("FT_NR_Placa"));

    String NR_Master = request.getParameter ("FT_NR_Master");
    if (String.valueOf (NR_Master) != null &&
        !String.valueOf (NR_Master).equals ("") &&
        !String.valueOf (NR_Master).equals ("null")) {
      ed.setNR_Master (NR_Master);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("") &&
        !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null &&
        !String.valueOf (oid_Pessoa).equals ("") &&
        !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    return new Ordem_FreteRN ().Master_Lista (ed);

  }

  ///### Kieling 17062003
  public Ordem_FreteED Master_inclui (HttpServletRequest request) throws Excecoes {

    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();
      Ordem_FreteED ed = new Ordem_FreteED ();

      String oid_Manifesto = request.getParameter ("oid_Manifesto");
      if (oid_Manifesto != null &&
          !oid_Manifesto.equals ("") &&
          !oid_Manifesto.equals ("null")) {
        ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
      }

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));

      ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
      ed.setHR_Emissao (request.getParameter ("FT_HR_Emissao"));

      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());

      ed.setDT_Adiantamento1 (request.getParameter ("FT_DT_Adiantamento1"));

      ed.setDT_Adiantamento2 (request.getParameter ("FT_DT_Adiantamento2"));

      ed.setDT_Saldo (request.getParameter ("FT_DT_Saldo"));

      String VL_ICMS = request.getParameter ("FT_VL_ICMS");
      if (String.valueOf (VL_ICMS) != null &&
          !String.valueOf (VL_ICMS).equals ("") &&
          !String.valueOf (VL_ICMS).equals ("null")) {
        ed.setVL_ICMS (new Double (VL_ICMS).doubleValue ());
      }

      String NR_Peso_Master = request.getParameter ("FT_NR_Peso_Master");
      if (String.valueOf (NR_Peso_Master) != null &&
          !String.valueOf (NR_Peso_Master).equals ("") &&
          !String.valueOf (NR_Peso_Master).equals ("null")) {
        ed.setNR_Peso_Master (new Double (NR_Peso_Master).doubleValue ());
      }

      String PE_ICMS = request.getParameter ("FT_PE_ICMS");
      if (String.valueOf (PE_ICMS) != null &&
          !String.valueOf (PE_ICMS).equals ("") &&
          !String.valueOf (PE_ICMS).equals ("null")) {
        ed.setPE_ICMS (new Double (PE_ICMS).doubleValue ());
      }

      String VL_Ordem_Frete = request.getParameter ("FT_VL_Ordem_Frete");
      if (String.valueOf (VL_Ordem_Frete) != null &&
          !String.valueOf (VL_Ordem_Frete).equals ("") &&
          !String.valueOf (VL_Ordem_Frete).equals ("null")) {
        ed.setVL_Ordem_Frete (new Double (VL_Ordem_Frete).doubleValue ());
      }

      ed.setNR_Ordem_Frete (0);

      ed.setDM_Impresso ("S");

      String VL_Total_Frete_CTRC = request.getParameter ("FT_VL_Total_Frete_CTRC");
      if (String.valueOf (VL_Total_Frete_CTRC) != null &&
          !String.valueOf (VL_Total_Frete_CTRC).equals ("") &&
          !String.valueOf (VL_Total_Frete_CTRC).equals ("null")) {
        ed.setVL_Total_Frete_CTRC (new Double (VL_Total_Frete_CTRC).doubleValue ());
      }

      String NR_Total_Peso_CTRC = request.getParameter ("FT_NR_Total_Peso_CTRC");
      if (String.valueOf (NR_Total_Peso_CTRC) != null &&
          !String.valueOf (NR_Total_Peso_CTRC).equals ("") &&
          !String.valueOf (NR_Total_Peso_CTRC).equals ("null")) {
        ed.setNR_Total_Peso_CTRC (new Double (NR_Total_Peso_CTRC).doubleValue ());
      }

      ed.setNR_Master (request.getParameter ("FT_NR_Master"));
      ed.setNR_Voo (request.getParameter ("FT_NR_Voo"));
      ed.setDT_Voo (request.getParameter ("FT_DT_Voo"));
      ed.setHR_Voo (request.getParameter ("FT_HR_Voo"));

      return Ordem_Fretern.Master_inclui (ed);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

///### KIELING 17062003
  public Ordem_FreteED Master_getByRecord (HttpServletRequest request) throws Excecoes {

    Ordem_FreteED ed = new Ordem_FreteED ();

    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
    if (String.valueOf (oid_Ordem_Frete) != null &&
        !String.valueOf (oid_Ordem_Frete).equals ("") &&
        !String.valueOf (oid_Ordem_Frete).equals ("null")) {
      ed.setOID_Ordem_Frete (oid_Ordem_Frete);
    }

    String NR_Master = request.getParameter ("FT_NR_Master");
    if (String.valueOf (NR_Master) != null &&
        !String.valueOf (NR_Master).equals ("") &&
        !String.valueOf (NR_Master).equals ("null")) {
      ed.setNR_Master (NR_Master);
    }

    return new Ordem_FreteRN ().Master_getByRecord (ed);

  }

  public byte[] imprime_Ordem_Frete_PDF (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
		    return new Ordem_FreteRN ().imprime_Ordem_Frete_PDF (this.carregaED (request));
		  }

  public byte[] imprime_Ordem_Frete (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
	    return new Ordem_FreteRN ().imprime_Ordem_Frete_PDF (this.carregaED (request));
	  }

  private Ordem_FreteED carregaED (HttpServletRequest request) throws Excecoes {

	  Ordem_FreteED ed = new Ordem_FreteED ();

	    String nr_Ordem_Frete = request.getParameter ("FT_NR_Ordem_Frete");
	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    String oid_Ordem_Frete = request.getParameter ("oid_Ordem_Frete");
	    String oid_Motorista = request.getParameter ("oid_Motorista");

	    if (util.doValida (nr_Ordem_Frete)) {
	      ed.setNR_Ordem_Frete (new Long (nr_Ordem_Frete).longValue ());
	    }
	    if (util.doValida (oid_Unidade)) {
	      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
	    }
	    if (util.doValida (oid_Ordem_Frete)) {
	      ed.setOID_Ordem_Frete (oid_Ordem_Frete);
	    }
	    if (util.doValida (oid_Motorista)) {
	      ed.setOID_Motorista (oid_Motorista);
	    }

	  return ed;
  }

  public void geraRelCFEmitido (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Manifesto_InternacionalED ed = new Manifesto_InternacionalED ();

    ed.setDT_Emissao_Inicial (request.getParameter ("FT_DT_Emissao"));
    ed.setDT_Emissao_Final (request.getParameter ("FT_DT_Fim"));

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa"));
    if (JavaUtil.doValida (request.getParameter ("oid_Seguradora"))) {
      ed.setOID_Seguradora (new Long (request.getParameter ("oid_Seguradora")).longValue ());
    }
    ed.setOID_Veiculo (request.getParameter ("oid_Veiculo"));

    String oid_Unidade_Origem = request.getParameter ("oid_Unidade_Origem");
    if (String.valueOf (oid_Unidade_Origem) != null && !String.valueOf (oid_Unidade_Origem).equals ("")) {
      ed.setOID_Unidade_Origem (new Integer (oid_Unidade_Origem).intValue ());
    }
    String oid_Unidade_Destino = request.getParameter ("oid_Unidade_Destino");
    if (String.valueOf (oid_Unidade_Destino) != null && !String.valueOf (oid_Unidade_Destino).equals ("")) {
      ed.setOID_Unidade_Destino (new Integer (oid_Unidade_Destino).intValue ());
    }

    Ordem_FreteRN geRN = new Ordem_FreteRN ();
    geRN.geraRelCFEmitido (ed , Response);

  }

  public ArrayList Ordem_Frete_Lista_Fora_Intervalo (HttpServletRequest request) throws Excecoes {
    String nr_Ordem_Frete_Inicial = request.getParameter ("FT_NR_Ordem_Frete_Inicial");
    String nr_Ordem_Frete_Final = request.getParameter ("FT_NR_Ordem_Frete_Final");
    String oid_Unidade = request.getParameter ("oid_Unidade");

    Ordem_FreteED ed = new Ordem_FreteED ();

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    if (String.valueOf (nr_Ordem_Frete_Inicial) != null && !String.valueOf (nr_Ordem_Frete_Inicial).equals ("")) {
      ed.setNR_Ordem_Frete_Inicial (new Long (nr_Ordem_Frete_Inicial).longValue ());
    }
    if (String.valueOf (nr_Ordem_Frete_Final) != null && !String.valueOf (nr_Ordem_Frete_Final).equals ("")) {
      ed.setNR_Ordem_Frete_Final (new Long (nr_Ordem_Frete_Final).longValue ());
    }

    return new Ordem_FreteRN ().lista_Fora_Intervalo (ed);

  }

  public long inclui_Compromisso (String oid_Ordem_Frete , String dm_Tipo_Ordem_Frete) throws Excecoes {
    long NR_Compromisso;
    try {
      Ordem_FreteRN Ordem_Fretern = new Ordem_FreteRN ();

      NR_Compromisso = Ordem_Fretern.inclui_Compromisso (oid_Ordem_Frete , dm_Tipo_Ordem_Frete);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao inclui_Compromisso");
      excecoes.setMetodo ("inclui_Compromisso()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return NR_Compromisso;

  }

  public ArrayList geraDirf(HttpServletRequest request) throws Excecoes {

	    Ordem_FreteED ed = new Ordem_FreteED ();

	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    if (String.valueOf (oid_Unidade) != null &&
	        !String.valueOf (oid_Unidade).equals ("") &&
	        !String.valueOf (oid_Unidade).equals ("null")) {
	      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
	    }

	    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
	    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
	      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
	    }

	    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
	    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
	      ed.setDt_Emissao_Final (Dt_Emissao_Final);
	    }

	    return new Ordem_FreteRN().geraDirf(ed);

	  }

}
