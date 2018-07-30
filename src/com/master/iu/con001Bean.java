package com.master.iu;

import java.util.*;
import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;
import com.master.util.ed.*;

public class con001Bean {
  public ConhecimentoED inclui (HttpServletRequest request) throws Excecoes {


    String NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    String NR_Postagem = request.getParameter ("FT_NR_Postagem");
    String NR_Peso = request.getParameter ("FT_NR_Peso");
    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    String VL_Nota_Fiscal = request.getParameter ("FT_VL_Nota_Fiscal");
    String VL_Nota_Fiscal_Seguro = request.getParameter ("FT_VL_Nota_Fiscal_Seguro");
    String VL_Frete_Peso = request.getParameter ("FT_VL_Frete_Peso");
    String VL_Pedagio = request.getParameter ("FT_VL_Pedagio");
    String VL_Frete = request.getParameter ("FT_VL_Frete");
    String VL_ICMS = request.getParameter ("FT_VL_ICMS");
    String OID_Coleta = request.getParameter ("oid_Coleta");
    String OID_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    String OID_Lote_Faturamento = request.getParameter ("oid_Lote_Faturamento");
    String OID_Participacao_Frete = request.getParameter ("oid_Participacao_Frete");
    String OID_Usuario = request.getParameter ("oid_Usuario");
    String OID_Modal = request.getParameter ("oid_Modal");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String TX_Roteiro = request.getParameter ("FT_TX_Roteiro");
    String OID_Vendedor = request.getParameter ("oid_Vendedor");
    String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    String DM_Tipo_Postagem = request.getParameter ("FT_DM_Tipo_Postagem");
    String PE_Aliquota_ICMS = request.getParameter ("FT_PE_Aliquota_ICMS");
    String NM_Serie = request.getParameter ("FT_NM_Serie");
    String OID_Veiculo = request.getParameter ("oid_Veiculo");
    String OID_Carreta = request.getParameter ("oid_Carreta");
    String oid_Carreta2 = request.getParameter ("oid_Carreta2");
    String DT_Previsao_Entrega = request.getParameter ("FT_DT_Previsao_Entrega");
    String HR_Previsao_Entrega = request.getParameter ("FT_HR_Previsao_Entrega");
    String PE_Carga_Expressa = request.getParameter ("FT_PE_Carga_Expressa");
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    String DT_Programada = request.getParameter ("FT_DT_Programada");

    // System.out.println ("---------------------NR_Peso_Cubado--------------->>" + NR_Peso_Cubado);

    String NR_Transacao_Pedagio = request.getParameter ("FT_NR_Transacao_Pedagio");
    String DM_Tipo_Cobranca = request.getParameter ("FT_DM_Tipo_Cobranca");
    String DM_Calcula_Frete = request.getParameter ("FT_DM_Calcula_Frete");
    String DM_Retira_Aeroporto = request.getParameter ("FT_DM_Retira_Aeroporto");
    String TX_Local_Retirada = request.getParameter ("FT_TX_Local_Retirada");
    String DM_Tipo_Tarifa = request.getParameter ("FT_DM_Tipo_Tarifa");
    String NR_Master = request.getParameter ("FT_NR_Master");
    String oid_Unidade_Agente = request.getParameter ("oid_Unidade_Agente");
    String CD_CFO_Conhecimento_Editado = request.getParameter ("FT_CD_CFO_Conhecimento_Editado");

    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    String oid_Estado_Origem = request.getParameter ("oid_Estado_Origem");
    String oid_Estado_Destino = request.getParameter ("oid_Estado_Destino");

    String NM_Liberacao_Veiculo = request.getParameter ("FT_NM_Liberacao_Veiculo") + " (" + request.getParameter ("FT_DM_Monitoramento") + ")";

    String CD_Roteiro = request.getParameter ("FT_CD_Roteiro");

    ConhecimentoED ed = new ConhecimentoED ();
    if (JavaUtil.doValida (NR_Conhecimento) && !"C".equals (DM_Calcula_Frete)) {
      ed.setDM_Impresso ("S");
    }
    else {
      ed.setDM_Impresso ("N");
    }

    String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
    if (JavaUtil.doValida(cd_Rota_Entrega)){
  	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
    }

    ed.setDT_Programada ("");
    if (String.valueOf (DT_Programada) != null &&
        !String.valueOf (DT_Programada).equals ("") &&
        !String.valueOf (DT_Programada).equals ("null")) {
      ed.setDT_Programada ( (DT_Programada));
    }

    if (JavaUtil.doValida (request.getParameter ("FT_NR_Pedido"))) {
      ed.setNR_Pedido (request.getParameter ("FT_NR_Pedido"));
    }
    else {
      ed.setNR_Pedido ("");
    }

    String oid_Programacao_Carga = request.getParameter ("oid_Programacao_Carga");
    if(JavaUtil.doValida(oid_Programacao_Carga)  && !"------".equals(oid_Programacao_Carga)){
    	ed.setOid_Programacao_Carga(new Long(oid_Programacao_Carga).longValue());
    }

    ed.setDM_Isento_Seguro (request.getParameter ("FT_DM_Isento_Seguro"));
    ed.setDM_Responsavel_Cobranca (request.getParameter ("FT_DM_Responsavel_Cobranca"));
    ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    ed.setNM_Natureza (request.getParameter ("FT_NM_Natureza"));
    ed.setNM_Especie (request.getParameter ("FT_NM_Especie"));

    if (String.valueOf (CD_Roteiro) != null &&
        !String.valueOf (CD_Roteiro).equals ("") &&
        !String.valueOf (CD_Roteiro).equals ("null")) {
      ed.setCD_Roteiro (CD_Roteiro);
    }

    if (JavaUtil.doValida (oid_Cidade_Origem)) {
      ed.setOID_Cidade (Long.parseLong (oid_Cidade_Origem));
    }
    if (JavaUtil.doValida (oid_Cidade_Destino)) {
      ed.setOID_Cidade_Destino (Long.parseLong (oid_Cidade_Destino));
    }
    if (JavaUtil.doValida (oid_Estado_Origem)) {
      ed.setOID_Estado_Origem (Long.parseLong (oid_Estado_Origem));
    }
    if (JavaUtil.doValida (oid_Estado_Destino)) {
      ed.setOID_Estado_Destino (Long.parseLong (oid_Estado_Destino));
    }

    if (JavaUtil.doValida (OID_Coleta)) {
      ed.setOID_Coleta (new Long (OID_Coleta).longValue ());
    }

    if (JavaUtil.doValida (OID_Centro_Custo)) {
      ed.setOID_Centro_Custo (new Long (OID_Centro_Custo).longValue ());
    }

    if (JavaUtil.doValida (OID_Lote_Faturamento)) {
      ed.setOID_Lote_Faturamento (new Long (OID_Lote_Faturamento).longValue ());
    }

    if (JavaUtil.doValida (OID_Participacao_Frete)) {
        ed.setOID_Participacao_Frete (new Long (OID_Participacao_Frete).longValue ());
    }

    if (JavaUtil.doValida (OID_Usuario)) {
      ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
    }

    ed.setOID_Modal (Parametro_FixoED.getInstancia ().getOID_Modal ());
    if (JavaUtil.doValida (OID_Modal)) {
      ed.setOID_Modal (new Long (OID_Modal).longValue ());
    }

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Redespacho (request.getParameter ("oid_Pessoa_Redespacho"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Pagador (request.getParameter ("oid_Pessoa_Pagador"));
    ed.setOID_Pessoa_Entregadora (request.getParameter ("oid_Pessoa_Entregadora"));
    ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));

    ed.setTX_Roteiro (JavaUtil.getValueDef (TX_Roteiro , ""));
    ed.setOID_Vendedor (JavaUtil.getValueDef (OID_Vendedor , ""));
    ed.setDM_Tipo_Conhecimento (JavaUtil.getValueDef (DM_Tipo_Conhecimento , "1"));
    ed.setDM_Tipo_Documento (JavaUtil.getValueDef (DM_Tipo_Documento , "C"));
    ed.setDM_Tipo_Postagem (JavaUtil.getValueDef (DM_Tipo_Postagem , ""));

    if (JavaUtil.doValida (NR_Conhecimento)) {
      ed.setNR_Conhecimento (new Long (NR_Conhecimento).longValue ());
    }
    if (JavaUtil.doValida (NR_Postagem)) {
      ed.setNR_Postagem (new Long (NR_Postagem).longValue ());
    }
    if (JavaUtil.doValida (NM_Serie)) {
      ed.setNM_Serie (NM_Serie);
    }

    if (JavaUtil.doValida (NM_Liberacao_Veiculo)) {
      ed.setNM_Liberacao_Veiculo (NM_Liberacao_Veiculo);
    }

    if (JavaUtil.doValida (request.getParameter ("FT_NR_Lote"))) {
      ed.setNR_Lote (request.getParameter ("FT_NR_Lote"));
    }
    else {
      ed.setNR_Lote ("");
    }


    ed.setOID_Veiculo (JavaUtil.getValueDef (OID_Veiculo , ""));
    ed.setOID_Carreta (JavaUtil.getValueDef (OID_Carreta , ""));

    if (JavaUtil.doValida (oid_Carreta2)) {
      ed.setOID_Carreta2 (oid_Carreta2);
    }

    ed.setDT_Previsao_Entrega (JavaUtil.getValueDef (DT_Previsao_Entrega , ""));
    ed.setHR_Previsao_Entrega (JavaUtil.getValueDef (HR_Previsao_Entrega , ""));

    if (JavaUtil.doValida (PE_Carga_Expressa)) {
      ed.setPE_Carga_Expressa (new Double (PE_Carga_Expressa).doubleValue ());
    }
    if (JavaUtil.doValida (NR_Peso_Cubado)) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }

    ed.setVL_FRETE_PESO (0.0);
    ed.setVL_FRETE_VALOR (0.0);
    ed.setVL_SEC_CAT (0.0);
    ed.setVL_PEDAGIO (0.0);
    ed.setVL_DESPACHO (0.0);
    ed.setVL_OUTROS1 (0.0);
    ed.setVL_OUTROS2 (0.0);
    ed.setVL_TOTAL_FRETE (0.0);

    if (JavaUtil.doValida (NR_Transacao_Pedagio)) {
      ed.setNR_Transacao_Pedagio (NR_Transacao_Pedagio);
    }
    if (JavaUtil.doValida (NR_Peso)) {
      ed.setNR_Peso (Double.parseDouble (NR_Peso));
    }

    if (JavaUtil.doValida (VL_Frete_Peso)) {
      ed.setVL_FRETE_PESO (Double.parseDouble (VL_Frete_Peso));
    }

    if (JavaUtil.doValida (VL_Nota_Fiscal)) {
      ed.setVL_Nota_Fiscal (Double.parseDouble (VL_Nota_Fiscal));
    }
    if (JavaUtil.doValida (VL_Nota_Fiscal_Seguro)) {
        ed.setVL_Nota_Fiscal_Seguro (Double.parseDouble (VL_Nota_Fiscal_Seguro));
        TX_Observacao+=" Vlr.Seg:" + VL_Nota_Fiscal_Seguro;
      }
    if (JavaUtil.doValida (NR_Volumes)) {
      ed.setNR_Volumes (Double.parseDouble (NR_Volumes));
    }
    if (JavaUtil.doValida (VL_Pedagio)) {
      ed.setVL_PEDAGIO (Double.parseDouble (VL_Pedagio));
    }
    if (JavaUtil.doValida (VL_Frete)) {
      ed.setVL_Frete (Double.parseDouble (VL_Frete));
      ed.setVL_TOTAL_FRETE (Double.parseDouble (VL_Frete));
    }
    if (JavaUtil.doValida (VL_ICMS)) {
      ed.setVL_ICMS (Double.parseDouble (VL_ICMS));
    }
    if (JavaUtil.doValida (PE_Aliquota_ICMS)) {
      ed.setPE_Aliquota_ICMS (Double.parseDouble (PE_Aliquota_ICMS));
    }

    if (JavaUtil.doValida (DM_Tipo_Cobranca)) {
      ed.setDM_Tipo_Cobranca (DM_Tipo_Cobranca);
    }
    if (JavaUtil.doValida (DM_Retira_Aeroporto)) {
      ed.setDM_Retira_Aeroporto (DM_Retira_Aeroporto);
    }

    if (JavaUtil.doValida (TX_Local_Retirada)) {
      ed.setTX_Local_Retirada (TX_Local_Retirada);
    }

    if (JavaUtil.doValida (DM_Tipo_Tarifa)) {
      ed.setDM_Tipo_Tarifa (DM_Tipo_Tarifa);
    }
    if (JavaUtil.doValida (NR_Master)) {
      ed.setNR_Master (NR_Master);
    }
    if (JavaUtil.doValida (oid_Unidade_Agente)) {
      ed.setOid_Unidade_Agente (Integer.parseInt (oid_Unidade_Agente));
    }
    if (JavaUtil.doValida (CD_CFO_Conhecimento_Editado)) {
      ed.setCD_CFO_Conhecimento_Editado (CD_CFO_Conhecimento_Editado);
    }

    ed.setTX_Observacao (JavaUtil.getValueDef (TX_Observacao , ""));

    return new ConhecimentoRN ().inclui (ed);
  }

  public ConhecimentoED inclui_CTRC_Nota_Fiscal_Lote (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      //request em cima dos campos dos forms html

      String OID_Coleta = request.getParameter ("oid_Coleta");
      if (String.valueOf (OID_Coleta) != null && !String.valueOf (OID_Coleta).equals ("")
          && !String.valueOf (OID_Coleta).equals ("null")) {
        ed.setOID_Coleta (new Long (OID_Coleta).longValue ());
      }

      return Conhecimentorn.inclui_CTRC_Nota_Fiscal_Lote (ed);
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

  public ConhecimentoED deleta_CTRC_Nota_Fiscal_Lote (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();
      //request em cima dos campos dos forms html

      String OID_Coleta = request.getParameter ("oid_Coleta");
      if (String.valueOf (OID_Coleta) != null && !String.valueOf (OID_Coleta).equals ("")
          && !String.valueOf (OID_Coleta).equals ("null")) {
        ed.setOID_Coleta (new Long (OID_Coleta).longValue ());
      }

      return Conhecimentorn.deleta_CTRC_Nota_Fiscal_Lote (ed);
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

  public ConhecimentoED recalcula_CTRC_Nota_Fiscal_Lote (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();
      //request em cima dos campos dos forms html

      String OID_Coleta = request.getParameter ("oid_Coleta");
      if (String.valueOf (OID_Coleta) != null && !String.valueOf (OID_Coleta).equals ("")
          && !String.valueOf (OID_Coleta).equals ("null")) {
        ed.setOID_Coleta (new Long (OID_Coleta).longValue ());
      }

      return Conhecimentorn.recalcula_CTRC_Nota_Fiscal_Lote (ed);
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
    String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    String OID_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
    String OID_Veiculo = request.getParameter ("oid_Veiculo");
    String OID_Carreta = request.getParameter ("oid_Carreta");
    String OID_Motorista = request.getParameter ("oid_Motorista");
    String oid_Carreta2 = request.getParameter ("oid_Carreta2");
    String DT_Coleta = request.getParameter ("FT_DT_Coleta");
    String DT_Programada = request.getParameter ("FT_DT_Programada");
    String DT_Previsao_Entrega = request.getParameter ("FT_DT_Previsao_Entrega");
    String HR_Previsao_Entrega = request.getParameter ("FT_HR_Previsao_Entrega");
    String NR_Transacao_Pedagio = request.getParameter ("FT_NR_Transacao_Pedagio");
    String DM_Retira_Aeroporto = request.getParameter ("FT_DM_Retira_Aeroporto");
    String TX_Local_Retirada = request.getParameter ("FT_TX_Local_Retirada");
    String DM_Tipo_Tarifa = request.getParameter ("FT_DM_Tipo_Tarifa");
    String DM_Cia_Aerea = request.getParameter ("FT_DM_Cia_Aerea");
    String NR_Master = request.getParameter ("FT_NR_Master");
    String oid_Unidade_Agente = request.getParameter ("oid_Unidade_Agente");
    String CD_CFO_Conhecimento_Editado = request.getParameter ("FT_CD_CFO_Conhecimento_Editado");
    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    String oid_Estado_Origem = request.getParameter ("oid_Estado_Origem");
    String oid_Estado_Destino = request.getParameter ("oid_Estado_Destino");

    String NR_Peso = request.getParameter ("FT_NR_Peso");
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    String PE_Carga_Expressa = request.getParameter ("FT_PE_Carga_Expressa");
    String PE_Aliquota_ICMS = request.getParameter ("FT_PE_Aliquota_ICMS");
    String VL_Frete = request.getParameter ("FT_VL_Frete");
    String VL_Nota_Fiscal = request.getParameter ("FT_VL_Nota_Fiscal");
    String VL_Nota_Fiscal_Seguro = request.getParameter ("FT_VL_Nota_Fiscal_Seguro");
    String CD_Roteiro = request.getParameter ("FT_CD_Roteiro");
    String NR_Postagem = request.getParameter ("FT_NR_Postagem");

    String DM_Tipo_Cobranca = request.getParameter ("FT_DM_Tipo_Cobranca");
    String DM_Tipo_Postagem = request.getParameter ("FT_DM_Tipo_Postagem");

    String NM_Liberacao_Veiculo = request.getParameter ("FT_NM_Liberacao_Veiculo") ;
    String DM_Monitoramento = request.getParameter ("FT_DM_Monitoramento");


    ConhecimentoED ed = new ConhecimentoED ();

    if (String.valueOf (CD_Roteiro) != null &&
        !String.valueOf (CD_Roteiro).equals ("") &&
        !String.valueOf (CD_Roteiro).equals ("null")) {
      ed.setCD_Roteiro (CD_Roteiro);
    }

    String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
    if (JavaUtil.doValida(cd_Rota_Entrega)){
  	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
    }

    if (JavaUtil.doValida (request.getParameter ("FT_NR_Pedido"))) {
      ed.setNR_Pedido (request.getParameter ("FT_NR_Pedido"));
    }
    else {
      ed.setNR_Pedido ("");
    }

    String oid_Programacao_Carga = request.getParameter ("oid_Programacao_Carga");
    if(JavaUtil.doValida(oid_Programacao_Carga)  && !"------".equals(oid_Programacao_Carga)){
    	ed.setOid_Programacao_Carga(new Long(oid_Programacao_Carga).longValue());
    }

    ed.setDM_Isento_Seguro (request.getParameter ("FT_DM_Isento_Seguro"));
    ed.setDM_Responsavel_Cobranca (request.getParameter ("FT_DM_Responsavel_Cobranca"));
    ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
    ed.setDM_Tipo_Cobranca (DM_Tipo_Cobranca);
    ed.setNM_Atendente (request.getParameter ("FT_NM_Atendente"));
    ed.setNM_Natureza (request.getParameter ("FT_NM_Natureza"));
    ed.setDM_Tipo_Postagem (JavaUtil.getValueDef (DM_Tipo_Postagem , ""));

    if (JavaUtil.doValida (request.getParameter ("FT_NR_Lote"))) {
      ed.setNR_Lote (request.getParameter ("FT_NR_Lote"));
    }
    else {
      ed.setNR_Lote ("");
    }

    ed.setNM_Especie (request.getParameter ("FT_NM_Especie"));
    ed.setNM_Pessoa_Entrega (request.getParameter ("FT_NM_Pessoa_Entrega"));

    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Redespacho (request.getParameter ("oid_Pessoa_Redespacho"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Pagador (request.getParameter ("oid_Pessoa_Pagador"));
    ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    ed.setOID_Tabela_Frete (request.getParameter ("oid_Tabela_Frete"));

    if (JavaUtil.doValida (VL_Nota_Fiscal_Seguro)) {
        ed.setVL_Nota_Fiscal_Seguro (Double.parseDouble (VL_Nota_Fiscal_Seguro));
      }

    if (JavaUtil.doValida (oid_Cidade_Origem)) {
      ed.setOID_Cidade (Long.parseLong (oid_Cidade_Origem));
    }
    if (JavaUtil.doValida (oid_Cidade_Destino)) {
      ed.setOID_Cidade_Destino (Long.parseLong (oid_Cidade_Destino));
    }
    if (JavaUtil.doValida (oid_Estado_Origem)) {
      ed.setOID_Estado_Origem (Long.parseLong (oid_Estado_Origem));
    }
    if (JavaUtil.doValida (oid_Estado_Destino)) {
      ed.setOID_Estado_Destino (Long.parseLong (oid_Estado_Destino));
    }

    if (JavaUtil.doValida (NR_Postagem)) {
      ed.setNR_Postagem (new Long (NR_Postagem).longValue ());
    }

    ed.setOID_Vendedor (request.getParameter ("oid_Vendedor"));
    ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));

    if (JavaUtil.doValida (DM_Tipo_Conhecimento)) {
      ed.setDM_Tipo_Conhecimento (DM_Tipo_Conhecimento);
    }
    else {
      ed.setDM_Tipo_Conhecimento ("1");
    }

    String TX_Roteiro = request.getParameter ("FT_TX_Roteiro");
    ed.setTX_Roteiro (JavaUtil.getValueDef (TX_Roteiro , ""));

    ed.setDM_Impresso (request.getParameter ("FT_DM_Impresso"));

    if ("N".equals (request.getParameter ("FT_DM_Impresso")) && !"B".equals (DM_Tipo_Conhecimento)) {
      ed.setVL_TOTAL_FRETE (0);
    }

    if (String.valueOf (OID_Pessoa_Entregadora) != null && !String.valueOf (OID_Pessoa_Entregadora).equals ("")) {
      ed.setOID_Pessoa_Entregadora (OID_Pessoa_Entregadora);
    }

    ed.setOID_Veiculo ("");
    if (String.valueOf (OID_Veiculo) != null &&
        !String.valueOf (OID_Veiculo).equals ("") &&
        !String.valueOf (OID_Veiculo).equals ("null")) {
      ed.setOID_Veiculo ( (OID_Veiculo));
    }
    ed.setOID_Carreta ("");
    if (String.valueOf (OID_Carreta) != null &&
        !String.valueOf (OID_Carreta).equals ("") &&
        !String.valueOf (OID_Carreta).equals ("null")) {
      ed.setOID_Carreta ( (OID_Carreta));
    }
    ed.setOID_Motorista ("");
    if (String.valueOf (OID_Motorista) != null &&
        !String.valueOf (OID_Motorista).equals ("") &&
        !String.valueOf (OID_Motorista).equals ("null")) {
      ed.setOID_Motorista ( (OID_Motorista));
    }

    if (JavaUtil.doValida (oid_Carreta2)) {
      ed.setOID_Carreta2 (oid_Carreta2);
    }

    ed.setDT_Coleta ("");
    if (String.valueOf (DT_Coleta) != null &&
        !String.valueOf (DT_Coleta).equals ("") &&
        !String.valueOf (DT_Coleta).equals ("null")) {
      ed.setDT_Coleta ( (DT_Coleta));
    }

    ed.setDT_Programada ("");
    if (String.valueOf (DT_Programada) != null &&
        !String.valueOf (DT_Programada).equals ("") &&
        !String.valueOf (DT_Programada).equals ("null")) {
      ed.setDT_Programada ( (DT_Programada));
    }
    ed.setDT_Previsao_Entrega ("");
    if (String.valueOf (DT_Previsao_Entrega) != null &&
        !String.valueOf (DT_Previsao_Entrega).equals ("") &&
        !String.valueOf (DT_Previsao_Entrega).equals ("null")) {
      ed.setDT_Previsao_Entrega ( (DT_Previsao_Entrega));
    }
    ed.setHR_Previsao_Entrega ("");
    if (String.valueOf (HR_Previsao_Entrega) != null &&
        !String.valueOf (HR_Previsao_Entrega).equals ("") &&
        !String.valueOf (HR_Previsao_Entrega).equals ("null")) {
      ed.setHR_Previsao_Entrega ( (HR_Previsao_Entrega));
    }

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    if (JavaUtil.doValida (NR_Transacao_Pedagio)) {
      ed.setNR_Transacao_Pedagio (NR_Transacao_Pedagio);
    }
    if (JavaUtil.doValida (DM_Retira_Aeroporto)) {
      ed.setDM_Retira_Aeroporto (DM_Retira_Aeroporto);
    }
    if (JavaUtil.doValida (TX_Local_Retirada)) {
      ed.setTX_Local_Retirada (TX_Local_Retirada);
    }
    if (JavaUtil.doValida (DM_Tipo_Tarifa)) {
      ed.setDM_Tipo_Tarifa (DM_Tipo_Tarifa);
    }
    if (JavaUtil.doValida (DM_Cia_Aerea)) {
      ed.setDM_Cia_Aerea (DM_Cia_Aerea);
    }
    if (JavaUtil.doValida (NR_Master)) {
      ed.setNR_Master (NR_Master);
    }
    if (JavaUtil.doValida (oid_Unidade_Agente)) {
      ed.setOid_Unidade_Agente (Integer.parseInt (oid_Unidade_Agente));
    }
    if (JavaUtil.doValida (CD_CFO_Conhecimento_Editado)) {
      ed.setCD_CFO_Conhecimento_Editado (CD_CFO_Conhecimento_Editado);
    }

    // System.out.println ("NR_Peso " + NR_Peso);
    if (JavaUtil.doValida (NR_Peso)) {
      ed.setNR_Peso (new Double (NR_Peso).doubleValue ());
    }
    // System.out.println ("ed.getNR_Peso() " + ed.getNR_Peso ());
    if (JavaUtil.doValida (NR_Peso_Cubado)) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }

    if (JavaUtil.doValida (NR_Volumes)) {
      ed.setNR_Volumes (new Double (NR_Volumes).doubleValue ());
    }

    if (JavaUtil.doValida (PE_Carga_Expressa)) {
      ed.setPE_Carga_Expressa (new Double (PE_Carga_Expressa).doubleValue ());
    }

    if (JavaUtil.doValida (VL_Nota_Fiscal)) {
      ed.setVL_Nota_Fiscal (new Double (VL_Nota_Fiscal).doubleValue ());
    }

    if (JavaUtil.doValida (VL_Frete)) {
      ed.setVL_TOTAL_FRETE (new Double (VL_Frete).doubleValue ());
    }

    if (JavaUtil.doValida (PE_Aliquota_ICMS)) {
      ed.setPE_Aliquota_ICMS (new Double (PE_Aliquota_ICMS).doubleValue ());
    }

    String OID_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    if (JavaUtil.doValida (OID_Centro_Custo)) {
      ed.setOID_Centro_Custo (new Long (OID_Centro_Custo).longValue ());
    }
    if (JavaUtil.doValida (NM_Liberacao_Veiculo)) {
      ed.setNM_Liberacao_Veiculo (NM_Liberacao_Veiculo);
      if (JavaUtil.doValida (DM_Monitoramento)) {
        ed.setNM_Liberacao_Veiculo (NM_Liberacao_Veiculo+ "-" +DM_Monitoramento);
      }
    }

    new ConhecimentoRN ().altera (ed);
  }

  public void altera_AWB (HttpServletRequest request) throws Excecoes {

    String DM_Retira_Aeroporto = request.getParameter ("FT_DM_Retira_Aeroporto");
    String TX_Local_Retirada = request.getParameter ("FT_TX_Local_Retirada");
    String DM_Tipo_Tarifa = request.getParameter ("FT_DM_Tipo_Tarifa");
    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    String DM_Tipo_Embalagem = request.getParameter ("FT_DM_Tipo_Embalagem");
    String DM_Cia_Aerea = request.getParameter ("FT_DM_Cia_Aerea");
    String NR_Master = request.getParameter ("FT_NR_Master");
    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    String NR_Peso_AWB = request.getParameter ("FT_NR_Peso");
    String NR_Volumes_AWB = request.getParameter ("FT_NR_Volumes");
    String DM_Impressao_AWB = request.getParameter ("FT_DM_Impressao_AWB");

    String VL_Frete_Aereo = request.getParameter ("FT_VL_Frete_Aereo");
    String VL_Frete_Valor = request.getParameter ("FT_VL_Frete_Valor");
    String VL_Master = request.getParameter ("FT_VL_Master");
    String VL_Nota_Fiscal_Declarado = request.getParameter ("FT_VL_Nota_Fiscal_Declarado");
    String VL_Taxa_Terrestre_Origem = request.getParameter ("FT_VL_Taxa_Terrestre_Origem");
    String VL_Taxa_Terrestre_Destino = request.getParameter ("FT_VL_Taxa_Terrestre_Destino");
    String VL_Taxa_Transportador = request.getParameter ("FT_VL_Taxa_Transportador");
    String VL_Tarifa_AWB = request.getParameter ("FT_VL_Tarifa_AWB");
    String NR_AWB = request.getParameter ("FT_NR_AWB");


    ConhecimentoED ed = new ConhecimentoED ();

    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());

    ed.setNM_Natureza_AWB (request.getParameter ("FT_NM_Natureza"));
    ed.setNM_Especie_AWB (request.getParameter ("FT_NM_Especie"));
    ed.setTX_Observacao_AWB (request.getParameter ("FT_TX_Observacao"));
    ed.setTX_Observacao_AWB2 (request.getParameter ("FT_TX_Observacao2"));
    ed.setTX_Observacao_AWB3 (request.getParameter ("FT_TX_Observacao3"));
    ed.setTX_Observacao_AWB4 (request.getParameter ("FT_TX_Observacao4"));
    ed.setTX_Observacao_AWB5 (request.getParameter ("FT_TX_Observacao5"));
    ed.setTX_Observacao_AWB6 (request.getParameter ("FT_TX_Observacao6"));

    if (JavaUtil.doValida (oid_Cidade_Origem)) {
      ed.setOID_Cidade_Aereo_Origem (Long.parseLong (oid_Cidade_Origem));
    }
    if (JavaUtil.doValida (oid_Cidade_Destino)) {
      ed.setOID_Cidade_Aereo_Destino (Long.parseLong (oid_Cidade_Destino));
    }

    if (JavaUtil.doValida (DM_Retira_Aeroporto)) {
      ed.setDM_Retira_Aeroporto (DM_Retira_Aeroporto);
    }
    if (JavaUtil.doValida (TX_Local_Retirada)) {
      ed.setTX_Local_Retirada (TX_Local_Retirada);
    }
    if (JavaUtil.doValida (DM_Tipo_Tarifa)) {
      ed.setDM_Tipo_Tarifa (DM_Tipo_Tarifa);
    }
    if (JavaUtil.doValida (DM_Tipo_Pagamento)) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }
    if (JavaUtil.doValida (DM_Tipo_Embalagem)) {
      ed.setDM_Tipo_Embalagem (DM_Tipo_Embalagem);
    }
    if (JavaUtil.doValida (DM_Cia_Aerea)) {
      ed.setDM_Cia_Aerea (DM_Cia_Aerea);
    }
    if (JavaUtil.doValida (NR_Master)) {
      ed.setNR_Master (NR_Master);
    }
    if (JavaUtil.doValida (DM_Impressao_AWB)) {
      ed.setDM_Impressao_AWB (DM_Impressao_AWB);
    }


    // System.out.println ("NR_Peso_AWB " + NR_Peso_AWB);
    if (JavaUtil.doValida (NR_Peso_AWB)) {
      ed.setNR_Peso_AWB (new Double (NR_Peso_AWB).doubleValue ());
    }

    // System.out.println ("NR_Volumes_AWB " + NR_Volumes_AWB);
    if (JavaUtil.doValida (NR_Volumes_AWB)) {
      ed.setNR_Volumes_AWB (new Double (NR_Volumes_AWB).doubleValue ());
    }

    // System.out.println ("VL_Frete_Aereo " + VL_Frete_Aereo);
    if (JavaUtil.doValida (VL_Frete_Aereo)) {
      ed.setVL_Frete_Aereo (new Double (VL_Frete_Aereo).doubleValue ());
    }

    if (JavaUtil.doValida (VL_Frete_Valor)) {
      ed.setVL_Frete_Valor_AWB (Double.parseDouble (VL_Frete_Valor));
    }

    // System.out.println ("VL_Taxa_Terrestre_Origem " + VL_Taxa_Terrestre_Origem);
    if (JavaUtil.doValida (VL_Taxa_Terrestre_Origem)) {
      ed.setVL_Taxa_Terrestre_Origem (new Double (VL_Taxa_Terrestre_Origem).doubleValue ());
    }
    // System.out.println ("VL_Taxa_Terrestre_Destino " + VL_Taxa_Terrestre_Destino);
    if (JavaUtil.doValida (VL_Taxa_Terrestre_Destino)) {
      ed.setVL_Taxa_Terrestre_Destino (new Double (VL_Taxa_Terrestre_Destino).doubleValue ());
    }
    // System.out.println ("VL_Taxa_Transportador " + VL_Taxa_Transportador);
    if (JavaUtil.doValida (VL_Taxa_Transportador)) {
      ed.setVL_Taxa_Transportador (new Double (VL_Taxa_Transportador).doubleValue ());
    }
    // System.out.println ("VL_Master " + VL_Master);
    if (JavaUtil.doValida (VL_Master)) {
      ed.setVL_Master (new Double (VL_Master).doubleValue ());
    }
    // System.out.println ("VL_Nota_Fiscal_Declarado " + VL_Nota_Fiscal_Declarado);
    if (JavaUtil.doValida (VL_Nota_Fiscal_Declarado)) {
      ed.setVL_Nota_Fiscal_Declarado (new Double (VL_Nota_Fiscal_Declarado).doubleValue ());
    }
    // System.out.println ("VL_Tarifa_AWB " + VL_Tarifa_AWB);
    if (JavaUtil.doValida (VL_Tarifa_AWB)) {
      ed.setVL_Tarifa_AWB (new Double (VL_Tarifa_AWB).doubleValue ());
    }

    // System.out.println ("NR_AWB " + NR_AWB);
    if (JavaUtil.doValida (NR_AWB)) {
      ed.setNR_AWB (new Long (NR_AWB).longValue ());
    }


    new ConhecimentoRN ().altera_AWB (ed);
  }

  public void altera_Minuta_Aereo (HttpServletRequest request) throws Excecoes {
    String DM_Retira_Aeroporto = request.getParameter ("FT_DM_Retira_Aeroporto");
    String TX_Local_Retirada = request.getParameter ("FT_TX_Local_Retirada");
    String DM_Tipo_Tarifa = request.getParameter ("FT_DM_Tipo_Tarifa");
    String DM_Tipo_Embalagem = request.getParameter ("FT_DM_Tipo_Embalagem");
    String DM_Cia_Aerea = request.getParameter ("FT_DM_Cia_Aerea");
    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    String NR_Peso = request.getParameter ("FT_NR_Peso");
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    ConhecimentoED ed = new ConhecimentoED ();

    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    if (JavaUtil.doValida (oid_Cidade_Origem)) {
      ed.setOID_Cidade (Long.parseLong (oid_Cidade_Origem));
    }
    if (JavaUtil.doValida (oid_Cidade_Destino)) {
      ed.setOID_Cidade_Destino (Long.parseLong (oid_Cidade_Destino));
    }
    if (JavaUtil.doValida (DM_Retira_Aeroporto)) {
      ed.setDM_Retira_Aeroporto (DM_Retira_Aeroporto);
    }
    if (JavaUtil.doValida (TX_Local_Retirada)) {
      ed.setTX_Local_Retirada (TX_Local_Retirada);
    }
    if (JavaUtil.doValida (DM_Tipo_Tarifa)) {
      ed.setDM_Tipo_Tarifa (DM_Tipo_Tarifa);
    }
    if (JavaUtil.doValida (DM_Tipo_Embalagem)) {
      ed.setDM_Tipo_Embalagem (DM_Tipo_Embalagem);
    }
    if (JavaUtil.doValida (DM_Cia_Aerea)) {
      ed.setDM_Cia_Aerea (DM_Cia_Aerea);
    }
    // System.out.println ("NR_Peso " + NR_Peso);
    if (JavaUtil.doValida (NR_Peso)) {
      ed.setNR_Peso (new Double (NR_Peso).doubleValue ());
    }
    // System.out.println ("ed.getNR_Peso() " + ed.getNR_Peso ());
    if (JavaUtil.doValida (NR_Peso_Cubado)) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }
    ed.setNM_Natureza_AWB (request.getParameter ("FT_NM_Natureza"));
    ed.setTX_Observacao_AWB (request.getParameter ("FT_TX_Observacao"));

    new ConhecimentoRN ().altera_Minuta_Aereo (ed);
  }

  public void altera_Cobranca (HttpServletRequest request) throws Excecoes {

    String DM_Tipo_Cobranca = request.getParameter ("FT_DM_Tipo_Cobranca");
    ConhecimentoED ed = new ConhecimentoED ();

    ed.setDM_Tipo_Cobranca (DM_Tipo_Cobranca);

    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    new ConhecimentoRN ().altera_Cobranca (ed);
  }

  public void atualiza_Minuta (HttpServletRequest request) throws Excecoes {

    ConhecimentoED ed = new ConhecimentoED ();
    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    new ConhecimentoRN ().atualiza_Minuta (ed);
  }


  public ConhecimentoED copia_Conhecimento (HttpServletRequest request, String DM_Tipo_Documento) throws Excecoes {

    ConhecimentoED ed = new ConhecimentoED ();
    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    ed.setDM_Tipo_Documento(DM_Tipo_Documento);

    return new ConhecimentoRN ().copia_Conhecimento (ed);
  }

  public void altera_Total_Frete (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      ed.setVL_TOTAL_FRETE (new Double (request.getParameter ("FT_VL_Frete")).doubleValue ());
      ed.setVL_FRETE_PESO (new Double (request.getParameter ("FT_VL_Frete_Peso")).doubleValue ());
      ed.setVL_PEDAGIO (new Double (request.getParameter ("FT_VL_Pedagio")).doubleValue ());
      ed.setVL_ICMS (new Double (request.getParameter ("FT_VL_ICMS")).doubleValue ());
      ed.setDM_Tipo_Desconto_Pedagio (request.getParameter ("FT_DM_Tipo_Desconto_Pedagio"));
      ed.setDM_Tipo_Conhecimento (request.getParameter ("FT_DM_Tipo_Conhecimento"));

      Conhecimentorn.altera_Total_Frete (ed);
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

  public void altera_Fiscal (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      ed.setVL_BASE_CALCULO_ICMS (new Double (request.getParameter ("FT_VL_Base_Calculo_ICMS")).doubleValue ());
      ed.setPE_ALIQUOTA_ICMS (new Double (request.getParameter ("FT_PE_Aliquota_ICMS")).doubleValue ());
      ed.setVL_ICMS (new Double (request.getParameter ("FT_VL_ICMS")).doubleValue ());

      String CD_CFO_Conhecimento_Editado = request.getParameter ("FT_CD_CFO_Conhecimento_Editado");
      ed.setCD_CFO_Conhecimento_Editado ("");
      if (JavaUtil.doValida (CD_CFO_Conhecimento_Editado)) {
        ed.setCD_CFO_Conhecimento_Editado (CD_CFO_Conhecimento_Editado);
      }

      Conhecimentorn.altera_Fiscal (ed);
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

  public void solicita_Cotacao (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      Conhecimentorn.solicita_Cotacao (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao solicita_Cotacao");
      excecoes.setMetodo ("solicita_Cotacao");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void realizacao_Coleta_Eletronica (HttpServletRequest request) throws Excecoes {

	    try {
	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      ConhecimentoED ed = new ConhecimentoED ();

	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

	      String Dt_Coleta = request.getParameter ("FT_DT_Realizacao_Coleta");
	      if (String.valueOf (Dt_Coleta) != null && !String.valueOf (Dt_Coleta).equals ("")) {
	        ed.setDT_Realizacao_Coleta (Dt_Coleta);
	      }
	      String HR_Coleta = request.getParameter ("FT_HR_Realizacao_Coleta");
	      if (String.valueOf (HR_Coleta) != null && !String.valueOf (HR_Coleta).equals ("")) {
	        ed.setHR_Realizacao_Coleta (HR_Coleta);
	      }

	      Conhecimentorn.confirma_Coleta_Eletronica (ed,"REALIZADA");
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("erro ao solicita_Cotacao");
	      excecoes.setMetodo ("realizacao_Coleta_Eletronica");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void confirma_Coleta_Eletronica (HttpServletRequest request) throws Excecoes {

	    try {
	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      ConhecimentoED ed = new ConhecimentoED ();

	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));


	     Conhecimentorn.confirma_Coleta_Eletronica (ed,"CONFIRMADA");
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("erro ao solicita_Cotacao");
	      excecoes.setMetodo ("confirma_Coleta_Eletronica");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void solicita_Coleta_Eletronica (HttpServletRequest request) throws Excecoes {

	    try {
	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      ConhecimentoED ed = new ConhecimentoED ();

	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

	      Conhecimentorn.solicita_Coleta_Eletronica (ed);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("erro ao solicita_Cotacao");
	      excecoes.setMetodo ("solicita_Cotacao");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void altera_Pagador (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setDM_Tipo_Cobranca (request.getParameter ("FT_DM_Tipo_Cobranca"));
      ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setOID_Pessoa_Pagador (request.getParameter ("oid_Pessoa_Pagador"));
      ed.setOID_Pessoa_Redespacho (request.getParameter ("oid_Pessoa_Redespacho"));
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));

      Conhecimentorn.altera_Pagador (ed);
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

  public void altera_Entregador (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      ed.setOID_Pessoa_Entregadora (request.getParameter ("oid_Pessoa_Entregadora"));

      String NR_Conhecimento_Entregadora = request.getParameter ("FT_NR_Conhecimento_Entregadora");
      if (String.valueOf (NR_Conhecimento_Entregadora) != null && !String.valueOf (NR_Conhecimento_Entregadora).equals ("null")) {
        ed.setNR_Conhecimento_Entregadora (NR_Conhecimento_Entregadora);
      }

      String Dt_Previsao_Entregadora = request.getParameter ("FT_DT_Previsao_Entregadora");
      if (String.valueOf (Dt_Previsao_Entregadora) != null && !String.valueOf (Dt_Previsao_Entregadora).equals ("")) {
        ed.setDT_Previsao_Entregadora (Dt_Previsao_Entregadora);
      }

      String VL_Frete = request.getParameter ("FT_VL_Frete_Entregadora");
      if (JavaUtil.doValida (VL_Frete)) {
        ed.setVL_Frete_Entregadora (Double.parseDouble (VL_Frete));
      }


      Conhecimentorn.altera_Entregador (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao altera_Entregador");
      excecoes.setMetodo ("altera_Entregador");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera_Situacao (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

      Conhecimentorn.altera_Situacao (ed);
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

  public void altera_Placas (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();
      String OID_Veiculo = request.getParameter ("oid_Veiculo");
      String OID_Carreta = request.getParameter ("oid_Carreta");

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      ed.setOID_Veiculo (JavaUtil.getValueDef (OID_Veiculo , ""));
      ed.setOID_Carreta (JavaUtil.getValueDef (OID_Carreta , ""));

      String Dt_Coleta = request.getParameter ("FT_DT_Coleta");
      if (String.valueOf (Dt_Coleta) != null && !String.valueOf (Dt_Coleta).equals ("")) {
        ed.setDT_Coleta (Dt_Coleta);
      }

      Conhecimentorn.altera_Placas (ed);
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

  public void altera_Previsao_Entrega (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setOID_Pessoa_Entregadora (request.getParameter ("oid_Pessoa_Entregadora"));

      String Dt_Previsao_Entrega = request.getParameter ("FT_DT_Previsao_Entrega");
      if (String.valueOf (Dt_Previsao_Entrega) != null && !String.valueOf (Dt_Previsao_Entrega).equals ("")) {
        ed.setDT_Previsao_Entrega (Dt_Previsao_Entrega);
      }

      String HR_Previsao_Entrega = request.getParameter ("FT_HR_Previsao_Entrega");
      if (String.valueOf (HR_Previsao_Entrega) != null && !String.valueOf (HR_Previsao_Entrega).equals ("")) {
        ed.setHR_Previsao_Entrega (HR_Previsao_Entrega);
      }

      String VL_Custo_Entrega = request.getParameter ("FT_VL_Custo_Entrega");
      if (String.valueOf (VL_Custo_Entrega) != null &&
          !String.valueOf (VL_Custo_Entrega).equals ("") &&
          !String.valueOf (VL_Custo_Entrega).equals ("null")) {
        ed.setVL_Custo_Entrega (new Double (VL_Custo_Entrega).doubleValue ());
      }

      Conhecimentorn.altera_Previsao_Entrega (ed);
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

  public void altera_Vendedor (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setOID_Vendedor (request.getParameter ("oid_Vendedor"));

      String DM_Tipo_Comissao = request.getParameter ("FT_DM_Tipo_Comissao");
      if (String.valueOf (DM_Tipo_Comissao) != null && !String.valueOf (DM_Tipo_Comissao).equals ("")) {
        ed.setDM_Tipo_Comissao (request.getParameter ("FT_DM_Tipo_Comissao"));
      }
      else {
        ed.setDM_Tipo_Comissao ("A");
      }

      Conhecimentorn.altera_Vendedor (ed);
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

  public void altera_Rota_Entrega(HttpServletRequest request) throws Excecoes {

	    try {
	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      ConhecimentoED ed = new ConhecimentoED ();

	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
	      ed.setCD_Rota_Entrega(request.getParameter("FT_CD_Rota_Entrega"));

	      Conhecimentorn.altera_Rota_Entrega(ed);
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

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      ed.setDT_Emissao(request.getParameter ("FT_DT_Emissao"));

      Conhecimentorn.deleta (ed);
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

  public String cancela (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setTX_Observacao (request.getParameter ("FT_DM_Motivo") + "-" + request.getParameter ("FT_TX_Observacao"));
      ed.setDT_Emissao(request.getParameter ("FT_DT_Emissao"));

      return Conhecimentorn.cancela (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao cancelar");
      excecoes.setMetodo ("cancela");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void zeraCTRC (HttpServletRequest request) throws Excecoes {

	    try {
	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      ConhecimentoED ed = new ConhecimentoED ();

	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
	      ed.setTX_Observacao (request.getParameter ("FT_DM_Motivo") + "-" + request.getParameter ("FT_TX_Observacao"));
	      ed.setDT_Emissao(request.getParameter ("FT_DT_Emissao"));

	      Conhecimentorn.zeraCTRC (ed);
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

  public void alteraMinuta (HttpServletRequest request) throws Excecoes {

	    try {
	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      ConhecimentoED ed = new ConhecimentoED ();

	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
	      ed.setTX_Observacao (request.getParameter ("FT_DM_Motivo") + "-" + request.getParameter ("FT_TX_Observacao"));

	      String VL_Frete = request.getParameter ("FT_VL_Frete");
	      if (JavaUtil.doValida (VL_Frete)) {
	        ed.setVL_TOTAL_FRETE (Double.parseDouble (VL_Frete));
	      }



	      Conhecimentorn.alteraMinuta (ed);
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

  public String reentrega_Devolucao (HttpServletRequest request) throws Excecoes {

    try {
      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
      ConhecimentoED ed = new ConhecimentoED ();

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setDM_Tipo_Conhecimento (request.getParameter ("FT_DM_Tipo_Conhecimento"));
      ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));

      return Conhecimentorn.reentrega_Devolucao (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("reentrega_Devolucao");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList Conhecimento_Lista (HttpServletRequest request) throws Excecoes {
    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    String nr_Minuta = request.getParameter ("FT_NR_Minuta");
    String NR_Recibo = request.getParameter ("FT_NR_Recibo");
    String nr_ACT = request.getParameter ("FT_NR_ACT");
    String nr_AWB = request.getParameter ("FT_NR_AWB");
    String nr_Nota_Fiscal_Servico = request.getParameter ("FT_NR_Nota_Fiscal_Servico");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String NR_Placa = request.getParameter ("FT_NR_Placa");
    String NR_Placa_Carreta = request.getParameter ("FT_NR_Placa_Carreta");
    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    String nr_Postagem = request.getParameter ("FT_NR_Postagem");

    ConhecimentoED ed = new ConhecimentoED ();
    boolean consultaUnica = false;

    if (JavaUtil.doValida (DM_Tipo_Documento)) {
      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
    }

    String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
    if (JavaUtil.doValida(cd_Rota_Entrega)){
  	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
    }
    // System.out.println ("Rota->>>" + cd_Rota_Entrega);

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));

    // System.out.println ("DM_Tipo_Documento->>>" + DM_Tipo_Documento);
    ed.setDM_Tipo_Documento (DM_Tipo_Documento);

    // System.out.println ("DM_Tipo_Conhecimento->>>" + DM_Tipo_Conhecimento);
    ed.setDM_Tipo_Conhecimento (DM_Tipo_Conhecimento);

    if (JavaUtil.doValida (nr_Minuta)) {
      ed.setNR_Minuta (new Long (nr_Minuta).longValue ());
      consultaUnica = true;
    }
    if (JavaUtil.doValida (nr_ACT)) {
      ed.setNR_ACT (new Long (nr_ACT).longValue ());
      consultaUnica = true;
    }
    if (JavaUtil.doValida (nr_AWB)) {
      ed.setNR_AWB (new Long (nr_AWB).longValue ());
      consultaUnica = true;
    }
    if (JavaUtil.doValida (nr_Nota_Fiscal_Servico)) {
      ed.setNR_Nota_Fiscal_Servico (new Long (nr_Nota_Fiscal_Servico).longValue ());
      consultaUnica = true;
    }
    if (JavaUtil.doValida (nr_Conhecimento)) {
      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
      consultaUnica = true;
    }
    if (JavaUtil.doValida (nr_Postagem)) {
      ed.setNR_Postagem (new Long (nr_Postagem).longValue ());
      consultaUnica = true;
    }


    String Dt_Previsao_Entrega_Inicial = request.getParameter ("FT_DT_Previsao_Entrega_Inicial");
    if (String.valueOf (Dt_Previsao_Entrega_Inicial) != null && !String.valueOf (Dt_Previsao_Entrega_Inicial).equals ("")) {
      ed.setDt_Previsao_Entrega_Inicial (Dt_Previsao_Entrega_Inicial);
    }

    String Dt_Previsao_Entrega_Final = request.getParameter ("FT_DT_Previsao_Entrega_Final");
    if (String.valueOf (Dt_Previsao_Entrega_Final) != null && !String.valueOf (Dt_Previsao_Entrega_Final).equals ("")) {
      ed.setDt_Previsao_Entrega_Final (Dt_Previsao_Entrega_Final);
    }

    String Dm_Situacao_Entrega = request.getParameter ("FT_DM_Situacao_Entrega");
    if (String.valueOf (Dm_Situacao_Entrega) != null && !String.valueOf (Dm_Situacao_Entrega).equals ("")) {
      ed.setDM_Situacao_Entrega (Dm_Situacao_Entrega);
    }
    String Dm_Situacao = request.getParameter ("FT_DM_Situacao");
    if (String.valueOf (Dm_Situacao) != null && !String.valueOf (Dm_Situacao).equals ("")) {
      ed.setDM_Situacao (Dm_Situacao);
    }

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Entregadora (request.getParameter ("oid_Pessoa_Entregadora"));
    ed.setOID_Pessoa_Pagador (request.getParameter ("oid_Pessoa_Pagador"));

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    if (String.valueOf (NR_Placa) != null &&
        !String.valueOf (NR_Placa).equals ("") &&
        !String.valueOf (NR_Placa).equals ("null")) {
      ed.setOID_Veiculo (request.getParameter ("FT_NR_Placa"));
    }

    if (String.valueOf (NR_Placa_Carreta) != null &&
        !String.valueOf (NR_Placa_Carreta).equals ("") &&
        !String.valueOf (NR_Placa_Carreta).equals ("null")) {
      ed.setOID_Carreta (request.getParameter ("FT_NR_Placa_Carreta"));
    }

    String oid_Coleta = request.getParameter ("oid_Coleta");
    if (JavaUtil.doValida (oid_Coleta)) {
      ed.setOID_Coleta (Long.parseLong (oid_Coleta));
    }

    if (JavaUtil.doValida (request.getParameter ("FT_NR_Lote"))) {
      ed.setNR_Lote (request.getParameter ("FT_NR_Lote"));
    }
    else {
      ed.setNR_Lote ("");
    }

    if (JavaUtil.doValida (request.getParameter ("FT_NR_Pedido"))) {
      ed.setNR_Pedido (request.getParameter ("FT_NR_Pedido"));
    }
    else {
      ed.setNR_Pedido ("");
    }

    if (JavaUtil.doValida (request.getParameter ("FT_NM_Natureza"))) {
      ed.setNM_Natureza (request.getParameter ("FT_NM_Natureza"));
    }
    else {
      ed.setNM_Natureza ("");
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }else{
    	if (!consultaUnica) ed.setDt_Emissao_Inicial (Data.getSomaDiaData(Data.getDataDMY(),-30));
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }


    return new ConhecimentoRN ().lista (ed);

  }

  public ArrayList Conhecimento_Lista_Fora_Intervalo (HttpServletRequest request) throws Excecoes {
    String nr_Conhecimento_Inicial = request.getParameter ("FT_NR_Conhecimento_Inicial");
    String nr_Conhecimento_Final = request.getParameter ("FT_NR_Conhecimento_Final");
    String oid_Unidade = request.getParameter ("oid_Unidade");

    ConhecimentoED ed = new ConhecimentoED ();

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    if (String.valueOf (nr_Conhecimento_Inicial) != null && !String.valueOf (nr_Conhecimento_Inicial).equals ("")) {
      ed.setNR_Conhecimento_Inicial (new Long (nr_Conhecimento_Inicial).longValue ());
    }
    if (String.valueOf (nr_Conhecimento_Final) != null && !String.valueOf (nr_Conhecimento_Final).equals ("")) {
      ed.setNR_Conhecimento_Final (new Long (nr_Conhecimento_Final).longValue ());
    }

    return new ConhecimentoRN ().lista_Fora_Intervalo (ed);

  }

  public ConhecimentoED getByOID_Conhecimento (String oid_Conhecimento) throws Excecoes {

    ConhecimentoED ed = new ConhecimentoED ();

    ed.setOID_Conhecimento (oid_Conhecimento);
    return new ConhecimentoRN ().getByRecord (ed);

  }

  public ConhecimentoED getByRecord (HttpServletRequest request) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    String forcaCalculo = request.getParameter ("forcaCalculo");
    if (JavaUtil.doValida (forcaCalculo)) {
      ed.setForcaCalculo (Boolean.valueOf (forcaCalculo).booleanValue ());
    }

    String NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    if (JavaUtil.doValida (NR_Conhecimento)) {
      ed.setNR_Conhecimento (Long.parseLong (NR_Conhecimento));
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (JavaUtil.doValida (oid_Unidade)) {
      ed.setOID_Unidade (Long.parseLong (oid_Unidade));
    }
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    if (JavaUtil.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }

    String NR_Minuta = request.getParameter ("FT_NR_Minuta");
    if (JavaUtil.doValida (NR_Minuta)) {
      ed.setNR_Minuta (Long.parseLong (NR_Minuta));
    }

    ed.setDM_Calcular ("Frete");

    return new ConhecimentoRN ().getByRecord (ed);
  }

  public ConhecimentoED getByAWB (HttpServletRequest request) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    String NR_AWB = request.getParameter ("FT_NR_AWB");
    if (JavaUtil.doValida (NR_AWB)) {
      ed.setNR_AWB (Long.parseLong (NR_AWB));
    }

    return new ConhecimentoRN ().getByAWB (ed);
  }

  public ConhecimentoED getByControle_Entrega (HttpServletRequest request) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    String NR_Controle_Entrega = request.getParameter ("FT_NR_Controle_Entrega");
    if (JavaUtil.doValida (NR_Controle_Entrega)) {
      ed.setNR_Controle_Entrega(NR_Controle_Entrega);
    }

    return new ConhecimentoRN ().getByControle_Entrega (ed);
  }

  public ConhecimentoED getByPostagem (HttpServletRequest request) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    String NR_Postagem = request.getParameter ("FT_NR_Postagem");
    if (JavaUtil.doValida (NR_Postagem)) {
      ed.setNR_Postagem (Long.parseLong (NR_Postagem));
    }

    return new ConhecimentoRN ().getByPostagem (ed);
  }

  public ConhecimentoED getByRecordRedespacho (HttpServletRequest request) throws Excecoes {

    ConhecimentoED ed = new ConhecimentoED ();

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (JavaUtil.doValida (oid_Unidade)) {
      ed.setOID_Unidade (Long.parseLong (oid_Unidade));
    }
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    if (JavaUtil.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    String NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    if (JavaUtil.doValida (NR_Conhecimento)) {
      ed.setNR_Conhecimento (Long.parseLong (NR_Conhecimento));
    }
    String oid_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
    if (JavaUtil.doValida (oid_Pessoa_Entregadora)) {
      ed.setOID_Pessoa_Entregadora (oid_Pessoa_Entregadora);
    }
    ed.setDM_Calcular ("Redespacho");

    return new ConhecimentoRN ().getByRecord (ed);
  }





  public void geraConhecimentoEntrega (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("")) {
      ed.setOID_Pessoa (oid_Pessoa_Pagador);
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    String Dm_Situacao_Cobranca = request.getParameter ("FT_DM_Situacao_Cobranca");
    if (String.valueOf (Dm_Situacao_Cobranca) != null && !String.valueOf (Dm_Situacao_Cobranca).equals ("")) {
      ed.setDm_Situacao_Cobranca (Dm_Situacao_Cobranca);
    }

    String DM_Tipo_Remessa = request.getParameter ("FT_DM_Tipo_Remessa");
    if (String.valueOf (DM_Tipo_Remessa) != null && !String.valueOf (DM_Tipo_Remessa).equals ("")) {
      ed.setDM_Tipo_Remessa (DM_Tipo_Remessa);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String Dm_Situacao_Entrega = request.getParameter ("FT_DM_Situacao_Entrega");
    if (String.valueOf (Dm_Situacao_Entrega) != null && !String.valueOf (Dm_Situacao_Entrega).equals ("")) {
      ed.setDM_Situacao_Entrega (Dm_Situacao_Entrega);
    }

    String DM_Tipo_Embarque = request.getParameter ("FT_DM_Tipo_Embarque");
    if (String.valueOf (DM_Tipo_Embarque) != null && !String.valueOf (DM_Tipo_Embarque).equals ("")) {
      ed.setDM_Tipo_Embarque (DM_Tipo_Embarque);
    }

    String DM_Frete_Emitido = request.getParameter ("FT_DM_Frete_Emitido");
    if (String.valueOf (DM_Frete_Emitido) != null && !String.valueOf (DM_Frete_Emitido).equals ("")) {
      ed.setDM_Frete_Emitido (DM_Frete_Emitido);
    }

    String DM_Frete_Recebido = request.getParameter ("FT_DM_Frete_Recebido");
    if (String.valueOf (DM_Frete_Recebido) != null && !String.valueOf (DM_Frete_Recebido).equals ("")) {
      ed.setDM_Frete_Recebido (DM_Frete_Recebido);
    }

    ConhecimentoRN geRN = new ConhecimentoRN ();
    new EnviaPDF ().enviaBytes (request , Response , geRN.geraConhecimentoEntrega (ed));

  }

  public void geraConhecimentoProduto (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    if (String.valueOf (nr_Conhecimento) != null && !String.valueOf (nr_Conhecimento).equals ("")) {
      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
    }
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));

    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("")) {
      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    String Dm_Situacao_Cobranca = request.getParameter ("FT_DM_Situacao_Cobranca");
    if (String.valueOf (Dm_Situacao_Cobranca) != null && !String.valueOf (Dm_Situacao_Cobranca).equals ("")) {
      ed.setDm_Situacao_Cobranca (Dm_Situacao_Cobranca);
    }

    String DM_Tipo_Embarque = request.getParameter ("FT_DM_Tipo_Embarque");
    if (String.valueOf (DM_Tipo_Embarque) != null && !String.valueOf (DM_Tipo_Embarque).equals ("")) {
      ed.setDM_Tipo_Embarque (DM_Tipo_Embarque);
    }

    String DM_Tipo_Produto = request.getParameter ("FT_DM_Tipo_Produto");
    if (String.valueOf (DM_Tipo_Produto) != null && !String.valueOf (DM_Tipo_Produto).equals ("")) {
      ed.setDM_Tipo_Produto (DM_Tipo_Produto);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    ConhecimentoRN geRN = new ConhecimentoRN ();
    new EnviaPDF ().enviaBytes (request , Response , geRN.geraConhecimentoProduto (ed));

  }


  public byte[] geraRelConhecTransRodCarga (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    if (String.valueOf (nr_Conhecimento) != null &&
        !String.valueOf (nr_Conhecimento).equals ("")) {
      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
    }
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    ConhecimentoRN geRN = new ConhecimentoRN ();

    return geRN.geraRelConhecTransRodCarga (ed);

  }

  public void geraConhecimentoFatura (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String Dm_Situacao_Cobranca = request.getParameter ("FT_DM_Situacao_Cobranca");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    String Dm_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    String DM_Tipo_Cobranca = request.getParameter ("FT_DM_Tipo_Cobranca");

    ConhecimentoED ed = new ConhecimentoED ();

    if (String.valueOf (nr_Conhecimento) != null && !String.valueOf (nr_Conhecimento).equals ("")) {
      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
    }
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));

    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("")) {
      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
    }

    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    if (String.valueOf (Dm_Situacao_Cobranca) != null && !String.valueOf (Dm_Situacao_Cobranca).equals ("")) {
      ed.setDm_Situacao_Cobranca (Dm_Situacao_Cobranca);
    }

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }

    if (String.valueOf (Dm_Tipo_Conhecimento) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
      ed.setDM_Tipo_Conhecimento (Dm_Tipo_Conhecimento);
    }
    ed.setDM_Tipo_Cobranca (DM_Tipo_Cobranca);

    ConhecimentoRN geRN = new ConhecimentoRN ();
    new EnviaPDF ().enviaBytes (request , Response , geRN.geraConhecimentoFatura (ed));

  }

  public byte[] imprimeRecibo_CTRC (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    ConhecimentoED ed = new ConhecimentoED ();

    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento_Inicial");
    if (String.valueOf (nr_Conhecimento) != null &&
        !String.valueOf (nr_Conhecimento).equals ("")) {
      ed.setNR_Conhecimento_Inicial (new Long (nr_Conhecimento).longValue ());
    }
    nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento_Final");
    if (String.valueOf (nr_Conhecimento) != null &&
        !String.valueOf (nr_Conhecimento).equals ("")) {
      ed.setNR_Conhecimento_Final (new Long (nr_Conhecimento).longValue ());
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null &&
        !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    ConhecimentoRN geRN = new ConhecimentoRN ();

    return geRN.imprimeRecibo_CTRC (ed);

  }


  public byte[] imprime_Conhecimento (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    ConhecimentoRN geRN = new ConhecimentoRN ();
    return geRN.imprime_Conhecimento (this.carregaED_Impressao (request));

  }

  public void imprime_ConhecimentoJasper (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    new ConhecimentoRN ().imprime_ConhecimentoJasper (this.carregaED_Impressao (request) , Response);
  }

  public String imprime_ConhecimentoMatricial (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    return new ConhecimentoRN ().imprime_ConhecimentoMatricial (this.carregaED_Impressao (request));
  }

  public void relICMSCliente (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
    String Relatorio = request.getParameter ("Relatorio");
    String oid_Empresa = request.getParameter ("oid_Empresa");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Pagador = request.getParameter ("oid_Pagador");
    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");

    if (!JavaUtil.doValida (Relatorio)) {
      throw new Mensagens ("Nome do Relatório não informado!");
    }

    ConhecimentoED ed = new ConhecimentoED (response , Relatorio);

    if (JavaUtil.doValida (oid_Empresa)) {
      ed.setOID_Empresa (Long.parseLong (oid_Empresa));
    }
    if (JavaUtil.doValida (oid_Unidade)) {
      ed.setOID_Unidade (Long.parseLong (oid_Unidade));
    }
    if (JavaUtil.doValida (oid_Pagador)) {
      ed.setOID_Pessoa_Pagador (oid_Pagador);
    }
    if (JavaUtil.doValida (DT_Emissao_Inicial)) {
      ed.setDt_Emissao_Inicial (DT_Emissao_Inicial);
    }
    if (JavaUtil.doValida (DT_Emissao_Final)) {
      ed.setDt_Emissao_Final (DT_Emissao_Final);
    }
    new ConhecimentoRN ().relICMSCliente (ed);
  }

  public void alteraNM_Lote_Faturamento (HttpServletRequest request) throws Excecoes {
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    String NM_Lote_Faturamento = request.getParameter ("FT_NM_Lote_Faturamento");
    ConhecimentoED dados = new ConhecimentoED ();
    if (JavaUtil.doValida (oid_Conhecimento)) {
      dados.setOID_Conhecimento (oid_Conhecimento);
    }
    else {
      throw new Mensagens ("Informe o conhecimento!");
    }
    if (JavaUtil.doValida (NM_Lote_Faturamento)) {
      dados.setNM_Lote_Faturamento (NM_Lote_Faturamento);
    }
    else {
      throw new Mensagens ("Informe o nome do lote de faturamento!");
    }
    new ConhecimentoRN ().alteraNM_Lote_Faturamento (dados);
  }

  public void relConhecimentoEntrega (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
    String Relatorio = request.getParameter ("Relatorio");
    String oid_Empresa = request.getParameter ("oid_Empresa");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Pagador = request.getParameter ("oid_Pagador");
    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");

    if (!JavaUtil.doValida (Relatorio)) {
      throw new Mensagens ("Nome do Relatório não informado!");
    }

    ConhecimentoED ed = new ConhecimentoED (response , Relatorio);

    if (JavaUtil.doValida (oid_Empresa)) {
      ed.setOID_Empresa (Long.parseLong (oid_Empresa));
    }
    if (JavaUtil.doValida (oid_Unidade)) {
      ed.setOID_Unidade (Long.parseLong (oid_Unidade));
    }
    if (JavaUtil.doValida (oid_Pagador)) {
      ed.setOID_Pessoa_Pagador (oid_Pagador);
    }
    if (JavaUtil.doValida (DT_Emissao_Inicial)) {
      ed.setDt_Emissao_Inicial (DT_Emissao_Inicial);
    }
    if (JavaUtil.doValida (DT_Emissao_Final)) {
      ed.setDt_Emissao_Final (DT_Emissao_Final);
    }
    new ConhecimentoRN ().relConhecimentoEntrega (ed);
  }

  public void geraRelDAC (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String NR_Placa = request.getParameter ("FT_NR_Placa");
    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");

    ConhecimentoED ed = new ConhecimentoED ();

    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    if ( (nr_Conhecimento) != null && !String.valueOf (nr_Conhecimento).equals ("")) {
      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
    }
    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    String Ano = request.getParameter ("FT_DT_Ano");
    if (JavaUtil.doValida (Ano)) {
      ed.setTX_Ano (request.getParameter ("FT_DT_Ano"));
    }
    String Semestre = request.getParameter ("FT_DT_Semestre");
    if (JavaUtil.doValida (Semestre)) {
      ed.setTX_Semestre (request.getParameter ("FT_DT_Semestre"));
    }

    if (JavaUtil.doValida (Ano) && JavaUtil.doValida (Semestre)) {
      if (Semestre.equals ("1")) {
        ed.setDt_Emissao_Inicial ("01/01/" + Ano);
        ed.setDt_Emissao_Final ("30/06/" + Ano);
      }
      else {
        ed.setDt_Emissao_Inicial ("01/07/" + Ano);
        ed.setDt_Emissao_Final ("31/12/" + Ano);
      }
    }

    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    if (String.valueOf (NR_Placa) != null &&
        !String.valueOf (NR_Placa).equals ("") &&
        !String.valueOf (NR_Placa).equals ("null")) {
      ed.setOID_Veiculo (request.getParameter ("FT_NR_Placa"));
    }
    if (JavaUtil.doValida (DM_Tipo_Documento)) {
      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
    }

    String oid_Coleta = request.getParameter ("oid_Coleta");
    if (JavaUtil.doValida (oid_Coleta)) {
      ed.setOID_Coleta (Long.parseLong (oid_Coleta));
    }

    new ConhecimentoRN ().geraRelDAC (ed , response);

  }

  private ConhecimentoED carregaED_Impressao (HttpServletRequest request) throws Excecoes {

    ConhecimentoED ed = new ConhecimentoED ();
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    String NR_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
    String NR_Conhecimento_Inicial = request.getParameter ("FT_NR_Conhecimento_Inicial");
    String NR_Conhecimento_Final = request.getParameter ("FT_NR_Conhecimento_Final");
    String oid_Pessoa = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Consignatario = request.getParameter ("oid_Pessoa_Consignatario");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    String oid_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Usuario = request.getParameter ("oid_Usuario");
    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String PE_Taxa_Ajuste = request.getParameter ("FT_PE_Taxa_Ajuste");
    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");


    String oid_Programacao_Carga = request.getParameter ("oid_Programacao_Carga");
    if(JavaUtil.doValida(oid_Programacao_Carga) && !"------".equals(oid_Programacao_Carga)){
    	ed.setOid_Programacao_Carga(new Long(oid_Programacao_Carga).longValue());
    }

    if (JavaUtil.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    else {
      if (JavaUtil.doValida (DT_Emissao)) {
        ed.setDT_Emissao (DT_Emissao);
      }
      if (JavaUtil.doValida (Dt_Emissao_Inicial)) {
        ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
      }
      if (JavaUtil.doValida (Dt_Emissao_Final)) {
        ed.setDt_Emissao_Final (Dt_Emissao_Final);
      }
      if (JavaUtil.doValida (NR_Conhecimento)) {
        ed.setNR_Conhecimento (Long.parseLong (NR_Conhecimento));
      }
      if (JavaUtil.doValida (NR_Conhecimento_Inicial)) {
        ed.setNR_Conhecimento_Inicial (Long.parseLong (NR_Conhecimento_Inicial));
      }
      if (JavaUtil.doValida (NR_Conhecimento_Final)) {
        ed.setNR_Conhecimento_Final (Long.parseLong (NR_Conhecimento_Final));
      }
      if (JavaUtil.doValida (oid_Pessoa)) {
        ed.setOID_Pessoa (oid_Pessoa);
      }
      if (JavaUtil.doValida (oid_Pessoa_Consignatario)) {
        ed.setOID_Pessoa_Consignatario (oid_Pessoa_Consignatario);
      }
      if (JavaUtil.doValida (oid_Pessoa_Destinatario)) {
        ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
      }
      if (JavaUtil.doValida (oid_Pessoa_Entregadora)) {
        ed.setOID_Pessoa_Entregadora (oid_Pessoa_Entregadora);
      }
      if (JavaUtil.doValida (oid_Unidade)) {
        ed.setOID_Unidade (Long.parseLong (oid_Unidade));
      }
      if (JavaUtil.doValida (oid_Usuario)) {
        ed.setOID_Usuario (Long.parseLong (oid_Usuario));
      }
      if (String.valueOf (PE_Taxa_Ajuste) != null && !String.valueOf (PE_Taxa_Ajuste).equals ("") && !String.valueOf (PE_Taxa_Ajuste).equals ("null")) {
        ed.setPE_Taxa_Ajuste (new Double (PE_Taxa_Ajuste).doubleValue ());
      }
      String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
      if (JavaUtil.doValida(cd_Rota_Entrega)){
    	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
    }
    }

    // System.out.println ("NR_Conhecimento_Inicial->> " + NR_Conhecimento_Inicial);
    // System.out.println ("DM_Tipo_Documento->> " + DM_Tipo_Documento);
    // System.out.println ("DM_Relatorio->> " + DM_Relatorio);

    ed.setDM_Tipo_Documento (JavaUtil.getValueDef (DM_Tipo_Documento , "C"));
    ed.setDM_Relatorio (JavaUtil.getValueDef (DM_Relatorio , "I"));

    return ed;

  }

  public String substituiConhecimento (HttpServletRequest request) throws Excecoes {

	    try {
	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      ConhecimentoED ed = new ConhecimentoED ();

	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento_Atual"));
	      ed.setOID_Novo_Conhecimento (request.getParameter ("oid_Conhecimento"));

	      return Conhecimentorn.substituiConhecimento (ed);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("erro ao cancelar");
	      excecoes.setMetodo ("cancela");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

}
