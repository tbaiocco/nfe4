package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_ContabilED;
import com.master.rn.EDI_ContabilRN;
import com.master.util.Excecoes;

public class Edi401Bean {

  public ArrayList gera_EDI_Pagamentos_Compromissos(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      // System.out.print("oid_Unidade" + oid_Unidade + " ed " +  ed.getOid_Unidade() );

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      return new EDI_ContabilRN().gera_EDI_Pagamentos_Compromissos(ed);

  }

  public ArrayList gera_EDI_Compromissos(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      return new EDI_ContabilRN().gera_EDI_Compromissos(ed);

  }

  public ArrayList gera_EDI_Faturamento(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      return new EDI_ContabilRN().gera_EDI_Faturamento(ed);

  }


  public ArrayList gera_EDI_Liquidacoes(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      return new EDI_ContabilRN().gera_EDI_Liquidacoes(ed);

  }

  public ArrayList gera_EDI_Conhecimento(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      return new EDI_ContabilRN().gera_EDI_Conhecimento(ed);

  }

  public ArrayList gera_EDI_Clientes(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());


      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      return new EDI_ContabilRN().gera_EDI_Clientes(ed);

  }

  public ArrayList gera_EDI_Pessoas_Embarques(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());


      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      return new EDI_ContabilRN().gera_EDI_Pessoas_Embarques(ed);

  }

  public ArrayList gera_EDI_Ordem_Frete(HttpServletRequest request)throws Excecoes{
    EDI_ContabilED ed = new EDI_ContabilED();

    String oid_Empresa = request.getParameter("oid_Empresa");
    if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
      ed.setOid_Empresa(new Long(oid_Empresa).longValue());

    String oid_Unidade = request.getParameter("oid_Unidade");
    if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
      ed.setOid_Unidade(new Long(oid_Unidade).longValue());

    String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
      ed.setDT_Inicial(dt_Inicial);

    String dt_Final = request.getParameter("FT_DT_Emissao_Final");
    if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
      ed.setDT_Final(dt_Final);

    String DM_Tipo_Ordem_Frete = request.getParameter("FT_DM_Tipo_Ordem_Frete");
    if (DM_Tipo_Ordem_Frete != null && !DM_Tipo_Ordem_Frete.equals("") && !DM_Tipo_Ordem_Frete.equals("null"))
      ed.setDM_Tipo_Ordem_Frete(DM_Tipo_Ordem_Frete);

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (DM_Frete != null && !DM_Frete.equals("") && !DM_Frete.equals("null"))
      ed.setDM_Frete(DM_Frete);

    String DM_Tipo_Pessoa = request.getParameter("FT_DM_Tipo_Pessoa");
    if (String.valueOf(DM_Tipo_Pessoa) != null && !String.valueOf(DM_Tipo_Pessoa).equals("")){
      ed.setDM_Tipo_Pessoa(DM_Tipo_Pessoa);
    }

    String DM_Modelo_Relatorio = request.getParameter("FT_DM_Modelo_Relatorio");
    if (String.valueOf(DM_Modelo_Relatorio) != null && !String.valueOf(DM_Modelo_Relatorio).equals("")){
      ed.setDM_Modelo_Relatorio(DM_Modelo_Relatorio);
    }

    if ("M".equals(ed.getDM_Modelo_Relatorio())){
      return new EDI_ContabilRN().gera_EDI_Ordem_Frete_Motorista(ed);
    }else{
      return new EDI_ContabilRN().gera_EDI_Ordem_Frete(ed);
    }
  }

  public ArrayList gera_EDI_Movimentos_Contas_Correntes(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      String CD_Conta_Corrente = request.getParameter("FT_CD_Conta_Corrente");
      if (CD_Conta_Corrente != null && !CD_Conta_Corrente.equals("") && !CD_Conta_Corrente.equals("null"))
        ed.setCD_Conta_Corrente(CD_Conta_Corrente);

      return new EDI_ContabilRN().gera_EDI_Movimentos_Contas_Correntes(ed);

  }
  public ArrayList gera_EDI_Lancamentos_Contabeis(HttpServletRequest request)throws Excecoes{

      EDI_ContabilED ed = new EDI_ContabilED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      String DM_Tipo_Lancamento = request.getParameter("FT_DM_Tipo_Lancamento");
      if (DM_Tipo_Lancamento != null && !DM_Tipo_Lancamento.equals("") && !DM_Tipo_Lancamento.equals("null"))
          ed.setDM_Tipo_Lancamento(DM_Tipo_Lancamento);

      String DM_Lista_Encerramento = request.getParameter("FT_DM_Lista_Encerramento");
      if (DM_Lista_Encerramento != null && !DM_Lista_Encerramento.equals("") && !DM_Lista_Encerramento.equals("null"))
          ed.setDm_Lista_Encerramento(DM_Lista_Encerramento);

      long oid_Origem = 0;

      //////ACERTOS CONTAS
      if (ed.getDM_Tipo_Lancamento ().equals ("1")) {
        oid_Origem = 6;
      }

      //////CONHECIMENTOS
      if (ed.getDM_Tipo_Lancamento ().equals ("2")) { ///
        oid_Origem = 2;
      }

      //////ORDEM FRETE
      if (ed.getDM_Tipo_Lancamento ().equals ("3")) { ///
        oid_Origem = 4;
      }

      //////LOTES PAGAMENTOS
      if (ed.getDM_Tipo_Lancamento ().equals ("4")) {
        oid_Origem = 3;
      }

      //////COMPENSAÇÃO LOTES PAGAMENTOS
      if (ed.getDM_Tipo_Lancamento ().equals ("14")) {
        oid_Origem = 108;
      }

      //////MOVIMENTO DUPLICATA
      if (ed.getDM_Tipo_Lancamento ().equals ("5")) {
        oid_Origem = 8;
      }

      //////NOTAS FISCAIS COMPRAS
      if (ed.getDM_Tipo_Lancamento ().equals ("6")) {
        oid_Origem = 7;
      }

      //////NOTAS FISCAIS SERVIÇOS
      if (ed.getDM_Tipo_Lancamento ().equals ("16")) {
        oid_Origem = 111;
      }

      //////MOVIMENTO CONTA CORRENTE
      if (ed.getDM_Tipo_Lancamento ().equals ("9")) {
        oid_Origem = 10;
      }

      //////DUPLICATA
      if (ed.getDM_Tipo_Lancamento ().equals ("12")) {
        oid_Origem = 9;
      }

      //////Vincula Compromissos - Troca de CF nos postos
      if (ed.getDM_Tipo_Lancamento ().equals ("13")) {
        oid_Origem = 107;
      }

      //////Lançamentos Manuais
      if (ed.getDM_Tipo_Lancamento ().equals ("20")) {
        oid_Origem = 20;
      }

      //////Todos Lançamentos
      if (ed.getDM_Tipo_Lancamento ().equals ("21")) {
        oid_Origem = 21;
      }

      ed.setOid_Origem(oid_Origem);

      return new EDI_ContabilRN().gera_EDI_Lancamentos_Contabeis(ed);

  }


}
