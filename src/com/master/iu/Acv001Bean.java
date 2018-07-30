package com.master.iu;

import java.util.*;
import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;

public class Acv001Bean  {

  Utilitaria util = new Utilitaria();

  public ArrayList Lista(HttpServletRequest request) throws Excecoes {
    Acerto_ViagemED ed = new Acerto_ViagemED();

    String NR_Acerto = request.getParameter("FT_NR_Acerto");
    if (util.doValida(NR_Acerto)) {
            ed.setNR_Acerto(new Long(NR_Acerto).longValue());
    }

    String oid_Ordem_Servico = request.getParameter("oid_Ordem_Servico");
    if (oid_Ordem_Servico != null && oid_Ordem_Servico.length() > 0)
    {
            int b = (new Integer(oid_Ordem_Servico)).intValue();
            ed.setOID_Ordem_Servico(b);
    }

    String NR_Ordem_Servico = request.getParameter("FT_NR_Ordem_Servico");
    if (util.doValida(NR_Ordem_Servico)) {
            ed.setNR_Ordem_Servico(new Long(NR_Ordem_Servico).longValue());
    }

    String oid_Unidade = request.getParameter("oid_Unidade");
    if (util.doValida(oid_Unidade)) {
            ed.setOid_Unidade(new Long(oid_Unidade).longValue());
    }

    String oid_Veiculo = request.getParameter("oid_Veiculo");
    if (oid_Veiculo != null && oid_Veiculo.length() > 0) {
            ed.setOid_Veiculo(oid_Veiculo);
    }
    String oid_Veiculo2 = request.getParameter("oid_Veiculo2");
    if (oid_Veiculo2 != null && oid_Veiculo2.length() > 0) {
            ed.setOid_Veiculo2(oid_Veiculo2);
    }
    String oid_Veiculo3 = request.getParameter("oid_Veiculo3");
    if (oid_Veiculo3 != null && oid_Veiculo3.length() > 0) {
            ed.setOid_Veiculo3(oid_Veiculo3);
    }

    String oid_Carreta = request.getParameter("oid_Veiculo_Carreta");
    if (oid_Carreta != null && oid_Carreta.length() > 0) {
            ed.setOid_Carreta(oid_Carreta);
    }


    String oid_Motorista = request.getParameter("oid_Motorista");
    if (oid_Motorista != null && oid_Motorista.length() > 0) {
            ed.setOID_Motorista(oid_Motorista);
    }

    String DT_Inicial = request.getParameter("FT_DT_Inicial");
    if (DT_Inicial != null && DT_Inicial.length() > 0) {
            ed.setDT_Inicial(DT_Inicial);
    }

    String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    if (DT_Emissao_Inicial != null && DT_Emissao_Inicial.length() > 0) {
            ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
    if (DT_Emissao_Final != null && DT_Emissao_Final.length() > 0) {
            ed.setDT_Emissao_Final(DT_Emissao_Final);
    }

    String DT_Final = request.getParameter("FT_DT_Final");
    if (DT_Final != null && DT_Final.length() > 0) {
            ed.setDT_Inicial(DT_Final);
    }

    String DT_Saida = request.getParameter("FT_DT_Saida");
    if (DT_Saida != null && DT_Saida.length() > 0) {
            ed.setDT_Saida(DT_Saida);
    }

    String DT_Chegada = request.getParameter("FT_DT_Chegada");
    if (DT_Chegada != null && DT_Chegada.length() > 0) {
            ed.setDT_Chegada(DT_Chegada);
    }

      return new Acerto_ViagemRN().lista(ed);

  }


  public void finaliza(HttpServletRequest request)throws Excecoes{

    Acerto_ViagemED ed = new Acerto_ViagemED();
    ed = this.carregaED(request);

    new Acerto_ViagemRN().finaliza(ed);
}

  public void reabre(HttpServletRequest request)throws Excecoes{

    Acerto_ViagemED ed = new Acerto_ViagemED();
    String oid_Acerto = request.getParameter("oid_Acerto");
    if (util.doValida(oid_Acerto)) {
            ed.setOID_Acerto(new Long(oid_Acerto).longValue());
    }

    new Acerto_ViagemRN().reabre(ed);
}


  public void altera(HttpServletRequest request)throws Excecoes{

      Acerto_ViagemED ed = new Acerto_ViagemED();
      ed = this.carregaED(request);

      new Acerto_ViagemRN().altera(ed);
  }


  public Acerto_ViagemED inclui(HttpServletRequest request)throws Excecoes{

      Acerto_ViagemED ed = new Acerto_ViagemED();

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (util.doValida(oid_Unidade)) {
              ed.setOid_Unidade(new Long(oid_Unidade).longValue());
      }

      String oid_Usuario = request.getParameter("oid_Usuario");
      if (util.doValida(oid_Usuario)) {
              ed.setOID_Usuario(new Long(oid_Usuario).longValue());
      }

      String oid_Ordem_Servico = request.getParameter("oid_Ordem_Servico");
      if (oid_Ordem_Servico != null && oid_Ordem_Servico.length() > 0)
      {
              int b = (new Integer(oid_Ordem_Servico)).intValue();
              ed.setOID_Ordem_Servico(b);
      }

      String oid_Veiculo = request.getParameter("oid_Veiculo");
      if (oid_Veiculo != null && oid_Veiculo.length() > 0) {
              ed.setOid_Veiculo(oid_Veiculo);
      }
      String oid_Veiculo2 = request.getParameter("oid_Veiculo2");
      if (oid_Veiculo2 != null && oid_Veiculo2.length() > 0) {
              ed.setOid_Veiculo2(oid_Veiculo2);
      }
      String oid_Veiculo3 = request.getParameter("oid_Veiculo3");
      if (oid_Veiculo3 != null && oid_Veiculo3.length() > 0) {
              ed.setOid_Veiculo3(oid_Veiculo3);
      }


      String oid_Motorista = request.getParameter("oid_Motorista");
      if (oid_Motorista != null && oid_Motorista.length() > 0) {
              ed.setOID_Motorista(oid_Motorista);
      }

      String DT_Emissao = request.getParameter("FT_DT_Emissao");
      if (DT_Emissao != null && DT_Emissao.length() > 0)
      {
              ed.setDT_Emissao(DT_Emissao);
      }

      String DT_Saida = request.getParameter("FT_DT_Saida");
      if (DT_Saida != null && DT_Saida.length() > 0)
      {
              ed.setDT_Saida(DT_Saida);
      }

      String DT_Chegada = request.getParameter("FT_DT_Chegada");
      if (DT_Chegada != null && DT_Chegada.length() > 0)
      {
              ed.setDT_Chegada(DT_Chegada);
      }

      String NR_Kilometragem_Saida = request.getParameter("FT_NR_Kilometragem_Saida");
      if (NR_Kilometragem_Saida != null && NR_Kilometragem_Saida.length() > 0)
      {
              ed.setNR_Kilometragem_Saida((new Integer(NR_Kilometragem_Saida)).intValue());
      }

      String NR_Kilometragem_Chegada = request.getParameter("FT_NR_Kilometragem_Chegada");
      if (NR_Kilometragem_Chegada != null && NR_Kilometragem_Chegada.length() > 0)
      {
              ed.setNR_Kilometragem_Chegada((new Integer(NR_Kilometragem_Chegada)).intValue());
      }


      String TX_Observacao = request.getParameter("FT_TX_Observacao");
      if (TX_Observacao != null && TX_Observacao.length() > 0)
      {
              ed.setTX_Observacao(TX_Observacao);
      }

      String VL_Cotacao = request.getParameter("FT_VL_Cotacao");
      if (VL_Cotacao != null && VL_Cotacao.length() > 0)
      {
              ed.setVL_Cotacao(new Double(VL_Cotacao).doubleValue());
      }




      return new Acerto_ViagemRN().inclui(ed);
  }

  public byte[] geraRelAcerto_Veiculos(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {

    Acerto_ViagemED ed = new Acerto_ViagemED();

    String oid_Empresa = request.getParameter("oid_Empresa");
    if (util.doValida(oid_Empresa)) {
            ed.setOid_Empresa(new Long(oid_Empresa).longValue());
    }

    String oid_Unidade = request.getParameter("oid_Unidade");
    if (util.doValida(oid_Unidade)) {
            ed.setOid_Unidade(new Long(oid_Unidade).longValue());
    }

    String oid_Veiculo = request.getParameter("oid_Veiculo");
    if (oid_Veiculo != null && oid_Veiculo.length() > 0) {
            ed.setOid_Veiculo(oid_Veiculo);
    }


    String oid_Motorista = request.getParameter("oid_Motorista");
    if (oid_Motorista != null && oid_Motorista.length() > 0) {
            ed.setOid_Motorista(oid_Motorista);
    }

    String DT_Inicial = request.getParameter("FT_DT_Inicial");
    if (DT_Inicial != null && DT_Inicial.length() > 0)
    {
            ed.setDT_Inicial(DT_Inicial);
    }

    String DT_Final = request.getParameter("FT_DT_Final");
    if (DT_Final != null && DT_Final.length() > 0)
    {
            ed.setDT_Final(DT_Final);
    }

    String DT_Saida = request.getParameter("FT_DT_Saida");
    if (DT_Saida != null && DT_Saida.length() > 0) {
            ed.setDT_Saida(DT_Saida);
    }
    String DT_Saida_Final = request.getParameter("FT_DT_Saida_Final");
    if (DT_Saida_Final != null && DT_Saida_Final.length() > 0) {
            ed.setDT_Saida_Final(DT_Saida_Final);
    }

    String DT_Chegada = request.getParameter("FT_DT_Chegada");
    if (DT_Chegada != null && DT_Chegada.length() > 0) {
            ed.setDT_Chegada(DT_Chegada);
    }
    String DT_Chegada_Final = request.getParameter("FT_DT_Chegada_Final");
    if (DT_Chegada_Final != null && DT_Chegada_Final.length() > 0) {
            ed.setDT_Chegada_Final(DT_Chegada_Final);
    }

    ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));
    ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
    ed.setDM_Tipo_Pagamento(request.getParameter("FT_DM_Tipo_Pagamento"));


    return new Acerto_ViagemRN().geraRelAcerto_Veiculos(ed);
  }

  public byte[] imprimeAcerto(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {

    Acerto_ViagemED ed = new Acerto_ViagemED();

    String oid_Acerto = request.getParameter("oid_Acerto");
    if (util.doValida(oid_Acerto)) {
            ed.setOID_Acerto(new Long(oid_Acerto).longValue());
    }

    Acerto_ViagemRN geRN = new Acerto_ViagemRN();

    return geRN.imprimeAcerto(ed);
  }


  public byte[] geraRelAcertos_Motorista(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {

    Acerto_ViagemED ed = new Acerto_ViagemED();

    String oid_Empresa = request.getParameter("oid_Empresa");
    if (util.doValida(oid_Empresa)) {
            ed.setOid_Empresa(new Long(oid_Empresa).longValue());
    }

    String oid_Unidade = request.getParameter("oid_Unidade");
    if (util.doValida(oid_Unidade)) {
            ed.setOid_Unidade(new Long(oid_Unidade).longValue());
    }

    String oid_Veiculo = request.getParameter("oid_Veiculo");
    if (oid_Veiculo != null && oid_Veiculo.length() > 0) {
            ed.setOid_Veiculo(oid_Veiculo);
    }


    String oid_Motorista = request.getParameter("oid_Motorista");
    if (oid_Motorista != null && oid_Motorista.length() > 0) {
            ed.setOid_Motorista(oid_Motorista);
    }

    String DT_Inicial = request.getParameter("FT_DT_Inicial");
    if (DT_Inicial != null && DT_Inicial.length() > 0)
    {
            ed.setDT_Inicial(DT_Inicial);
    }

    String DT_Final = request.getParameter("FT_DT_Final");
    if (DT_Final != null && DT_Final.length() > 0)
    {
            ed.setDT_Final(DT_Final);
    }
    String DT_Saida = request.getParameter("FT_DT_Saida");
    if (DT_Saida != null && DT_Saida.length() > 0) {
            ed.setDT_Saida(DT_Saida);
    }

    String DT_Chegada = request.getParameter("FT_DT_Chegada");
    if (DT_Chegada != null && DT_Chegada.length() > 0) {
            ed.setDT_Chegada(DT_Chegada);
    }

    ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));

    ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

    return new Acerto_ViagemRN().geraRelAcertos_Motorista(ed);
  }



  public Acerto_ViagemED carregaED(HttpServletRequest request)throws Excecoes{

      Acerto_ViagemED ed = new Acerto_ViagemED();

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (util.doValida(oid_Unidade)) {
              ed.setOid_Unidade(new Long(oid_Unidade).longValue());
      }

      String oid_Acerto = request.getParameter("oid_Acerto");
      if (oid_Acerto != null && oid_Acerto.length() > 0)
      {
              int b = (new Integer(oid_Acerto)).intValue();
              ed.setOID_Acerto(b);
      }

      String oid_Ordem_Servico = request.getParameter("oid_Ordem_Servico");
      if (oid_Ordem_Servico != null && oid_Ordem_Servico.length() > 0)
      {
              int b = (new Integer(oid_Ordem_Servico)).intValue();
              ed.setOID_Ordem_Servico(b);
      }

      String oid_Veiculo = request.getParameter("oid_Veiculo");
      if (oid_Veiculo != null && oid_Veiculo.length() > 0) {
              ed.setOid_Veiculo(oid_Veiculo);
      }
      String oid_Veiculo2 = request.getParameter("oid_Veiculo2");
      if (oid_Veiculo2 != null && oid_Veiculo2.length() > 0) {
              ed.setOid_Veiculo2(oid_Veiculo2);
      }
      String oid_Veiculo3 = request.getParameter("oid_Veiculo3");
      if (oid_Veiculo3 != null && oid_Veiculo3.length() > 0) {
              ed.setOid_Veiculo3(oid_Veiculo3);
      }


      String oid_Motorista = request.getParameter("oid_Motorista");
      if (oid_Motorista != null && oid_Motorista.length() > 0) {
              ed.setOID_Motorista(oid_Motorista);
      }

      String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
      if (oid_Conta_Corrente != null && oid_Conta_Corrente.length() > 0) {
              ed.setOID_Conta_Corrente(oid_Conta_Corrente);
      }


      String DT_Emissao = request.getParameter("FT_DT_Emissao");
      if (DT_Emissao != null && DT_Emissao.length() > 0)
      {
              ed.setDT_Emissao(DT_Emissao);
      }

      String DT_Saida = request.getParameter("FT_DT_Saida");
      if (DT_Saida != null && DT_Saida.length() > 0)
      {
              ed.setDT_Saida(DT_Saida);
      }

      String DT_Chegada = request.getParameter("FT_DT_Chegada");
      if (DT_Chegada != null && DT_Chegada.length() > 0)
      {
              ed.setDT_Chegada(DT_Chegada);
      }

      String NR_Kilometragem_Saida = request.getParameter("FT_NR_Kilometragem_Saida");
      if (NR_Kilometragem_Saida != null && NR_Kilometragem_Saida.length() > 0)
      {
              ed.setNR_Kilometragem_Saida((new Integer(NR_Kilometragem_Saida)).intValue());
      }

      String NR_Kilometragem_Chegada = request.getParameter("FT_NR_Kilometragem_Chegada");
      if (NR_Kilometragem_Chegada != null && NR_Kilometragem_Chegada.length() > 0)
      {
              ed.setNR_Kilometragem_Chegada((new Integer(NR_Kilometragem_Chegada)).intValue());
      }


      String TX_Observacao = request.getParameter("FT_TX_Observacao");
      if (TX_Observacao != null && TX_Observacao.length() > 0)
      {
              ed.setTX_Observacao(TX_Observacao);
      }

      String VL_Cotacao = request.getParameter("FT_VL_Cotacao");
      if (VL_Cotacao != null && VL_Cotacao.length() > 0)
      {
              ed.setVL_Cotacao(new Double(VL_Cotacao).doubleValue());
      }

      String VL_Total_Frete = request.getParameter("FT_VL_Total_Frete");
      if (VL_Total_Frete != null && VL_Total_Frete.length() > 0)
      {
              ed.setVL_Total_Frete(new Double(VL_Total_Frete).doubleValue());
      }

      String VL_Total_Despesas = request.getParameter("FT_VL_Total_Despesas");
      if (VL_Total_Despesas != null && VL_Total_Despesas.length() > 0)
      {
              ed.setVL_Total_Despesas(new Double(VL_Total_Despesas).doubleValue());
      }

      String VL_Total_Despesas_Faturadas = request.getParameter("FT_VL_Total_Despesas_Faturadas");
      if (VL_Total_Despesas_Faturadas != null && VL_Total_Despesas_Faturadas.length() > 0)
      {
              ed.setVL_Total_Despesas_Faturadas(new Double(VL_Total_Despesas_Faturadas).doubleValue());
      }

      String VL_Total_Despesas_Pagas = request.getParameter("FT_VL_Total_Despesas_Pagas");
      if (VL_Total_Despesas_Pagas != null && VL_Total_Despesas_Pagas.length() > 0)
      {
              ed.setVL_Total_Despesas_Pagas(new Double(VL_Total_Despesas_Pagas).doubleValue());
      }

      String VL_Adiantamento_Viagem = request.getParameter("FT_VL_Adiantamento_Viagem");
      if (VL_Adiantamento_Viagem != null && VL_Adiantamento_Viagem.length() > 0)
      {
              ed.setVL_Adiantamento_Viagem(new Double(VL_Adiantamento_Viagem).doubleValue());
      }

      String VL_Frete_Terceiro = request.getParameter("FT_VL_Frete_Terceiro");
      if (VL_Frete_Terceiro != null && VL_Frete_Terceiro.length() > 0)
      {
              ed.setVL_Frete_Terceiro(new Double(VL_Frete_Terceiro).doubleValue());
      }

      String VL_Frete_Proprio = request.getParameter("FT_VL_Frete_Proprio");
      if (VL_Frete_Proprio != null && VL_Frete_Proprio.length() > 0)
      {
              ed.setVL_Frete_Proprio(new Double(VL_Frete_Proprio).doubleValue());
      }

      String VL_Comissao = request.getParameter("FT_VL_Comissao");
      if (VL_Comissao != null && VL_Comissao.length() > 0)
      {
              ed.setVL_Comissao(new Double(VL_Comissao).doubleValue());
      }

      String PE_Comissao = request.getParameter("FT_PE_Comissao");
      if (PE_Comissao != null && PE_Comissao.length() > 0)
      {
              ed.setPE_Comissao(new Double(PE_Comissao).doubleValue());
      }

      String VL_Comissao_Terceiro = request.getParameter("FT_VL_Comissao_Terceiro");
      if (VL_Comissao_Terceiro != null && VL_Comissao_Terceiro.length() > 0)
      {
              ed.setVL_Comissao_Terceiro(new Double(VL_Comissao_Terceiro).doubleValue());
      }

      String PE_Comissao_Terceiro = request.getParameter("FT_PE_Comissao_Terceiro");
      if (PE_Comissao_Terceiro != null && PE_Comissao_Terceiro.length() > 0)
      {
              ed.setPE_Comissao_Terceiro(new Double(PE_Comissao_Terceiro).doubleValue());
      }

      String VL_Frete_Peso = request.getParameter("FT_VL_Frete_Peso");
      if (VL_Frete_Peso != null && VL_Frete_Peso.length() > 0)
      {
              ed.setVL_Frete_Peso(new Double(VL_Frete_Peso).doubleValue());
      }

      String VL_Recebido = request.getParameter("FT_VL_Recebido");
      if (VL_Recebido != null && VL_Recebido.length() > 0)
      {
              ed.setVL_Recebido(new Double(VL_Recebido).doubleValue());
      }

      String VL_Devolvido = request.getParameter("FT_VL_Devolvido");
      if (VL_Devolvido != null && VL_Devolvido.length() > 0)
      {
              ed.setVL_Devolvido(new Double(VL_Devolvido).doubleValue());
      }

      String VL_Saldo = request.getParameter("FT_VL_Saldo");
      if (VL_Saldo != null && VL_Saldo.length() > 0)
      {
              ed.setVL_Saldo(new Double(VL_Saldo).doubleValue());
      }


      return ed;
  }


  public Acerto_ViagemED getByRecord(HttpServletRequest request) throws Excecoes{
        Acerto_ViagemED ed = new Acerto_ViagemED();


        String NR_Acerto = request.getParameter("FT_NR_Acerto");
        if (util.doValida(NR_Acerto)) {
                ed.setNR_Acerto(Long.parseLong(NR_Acerto));
        }
        String oid_Acerto = request.getParameter("oid_Acerto");
        if (util.doValida(oid_Acerto)) {
                ed.setOID_Acerto(new Long(oid_Acerto).longValue());
        }
        return new Acerto_ViagemRN().getByRecord(ed);
  }

  public Acerto_ViagemED getByRecord(String oid_Acerto) throws Excecoes{
        Acerto_ViagemED ed = new Acerto_ViagemED();
        if (util.doValida(oid_Acerto)) {
                ed.setOID_Acerto(new Long(oid_Acerto).longValue());
        }
        return new Acerto_ViagemRN().getByRecord(ed);
  }

  public Acerto_ViagemED consultaAcertoNaoFinalizado (String oid_Veiculo, String oid_Motorista) throws Excecoes{
        Acerto_ViagemED ed = new Acerto_ViagemED();
        ed.setOid_Veiculo(oid_Veiculo);
        ed.setOid_Motorista(oid_Motorista);
        return new Acerto_ViagemRN().consultaAcertoNaoFinalizado(ed);
  }



  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Acerto_ViagemRN Acerto_ViagemRN = new Acerto_ViagemRN();
      Acerto_ViagemED ed = new Acerto_ViagemED();

      String oid_Acerto = request.getParameter("oid_Acerto");
      if (util.doValida(oid_Acerto)) {
              ed.setOID_Acerto(new Long(oid_Acerto).longValue());
      }
      String oid_Ordem_Servico = request.getParameter("oid_Ordem_Servico");
      if (oid_Ordem_Servico != null && oid_Ordem_Servico.length() > 0)
      {
              int b = (new Integer(oid_Ordem_Servico)).intValue();
              ed.setOID_Ordem_Servico(b);
      }

      Acerto_ViagemRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


}
