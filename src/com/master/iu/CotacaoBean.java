package com.master.iu;

import java.util.*;
import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;
import com.master.util.ed.*;

public class CotacaoBean {

  public CotacaoED inclui (HttpServletRequest request) throws Excecoes {

    String NR_Cotacao = request.getParameter ("FT_NR_Cotacao");
    String NR_Peso = request.getParameter ("FT_NR_Peso");
    String NR_Cubagem = request.getParameter ("FT_NR_Cubagem");
    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    String VL_Nota_Fiscal = request.getParameter ("FT_VL_Nota_Fiscal");
    String OID_Usuario = request.getParameter ("oid_Usuario");
    String oid_Coleta = request.getParameter ("oid_Coleta");
    String OID_Modal = request.getParameter ("oid_Modal");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String OID_Vendedor = request.getParameter ("oid_Vendedor");
    String DM_Tipo_Cotacao = request.getParameter ("FT_DM_Tipo_Cotacao");
    String DT_Previsao_Entrega = request.getParameter ("FT_DT_Previsao_Entrega");
    String HR_Previsao_Entrega = request.getParameter ("FT_HR_Previsao_Entrega");
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");

    // System.out.println ("---------------------NR_Peso_Cubado--------------->>" + NR_Peso_Cubado);

    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    String oid_Estado_Origem = request.getParameter ("oid_Estado_Origem");
    String oid_Estado_Destino = request.getParameter ("oid_Estado_Destino");

    CotacaoED ed = new CotacaoED ();
    ed.setDM_Responsavel_Cobranca (request.getParameter ("FT_DM_Responsavel_Cobranca"));
    ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    ed.setNM_Natureza (request.getParameter ("FT_NM_Natureza"));
    ed.setNM_Especie (request.getParameter ("FT_NM_Especie"));
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
    if (JavaUtil.doValida (OID_Usuario)) {
      ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
    }
    if (JavaUtil.doValida (oid_Coleta)) {
      ed.setOID_Coleta (new Long (oid_Coleta).longValue ());
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
    ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());

    String NM_Solicitante = request.getParameter ("FT_NM_Solicitante");
    ed.setNM_Solicitante (JavaUtil.getValueDef (NM_Solicitante , ""));

    ed.setTX_Observacao (JavaUtil.getValueDef (TX_Observacao , ""));
    ed.setOID_Vendedor (JavaUtil.getValueDef (OID_Vendedor , ""));
    ed.setDM_Tipo_Cotacao (JavaUtil.getValueDef (DM_Tipo_Cotacao , "1"));

    if (JavaUtil.doValida (NR_Cotacao)) {
      ed.setNR_Cotacao (new Long (NR_Cotacao).longValue ());
    }
    ed.setDT_Previsao_Entrega (JavaUtil.getValueDef (DT_Previsao_Entrega , ""));
    ed.setHR_Previsao_Entrega (JavaUtil.getValueDef (HR_Previsao_Entrega , ""));

    if (JavaUtil.doValida (NR_Peso_Cubado)) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }
    if (JavaUtil.doValida (NR_Peso)) {
      ed.setNR_Peso (Double.parseDouble (NR_Peso));
    }

    if (JavaUtil.doValida (NR_Cubagem)) {
      ed.setNR_Cubagem (Double.parseDouble (NR_Cubagem));
    }

    if (JavaUtil.doValida (VL_Nota_Fiscal)) {
      ed.setVL_Nota_Fiscal (Double.parseDouble (VL_Nota_Fiscal));
    }
    if (JavaUtil.doValida (NR_Volumes)) {
      ed.setNR_Volumes (Double.parseDouble (NR_Volumes));
    }
    return new CotacaoRN ().inclui (ed);
  }


  public void altera (HttpServletRequest request) throws Excecoes {
    String DT_Previsao_Entrega = request.getParameter ("FT_DT_Previsao_Entrega");
    String HR_Previsao_Entrega = request.getParameter ("FT_HR_Previsao_Entrega");
    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    String oid_Estado_Origem = request.getParameter ("oid_Estado_Origem");
    String oid_Estado_Destino = request.getParameter ("oid_Estado_Destino");

    String NR_Peso = request.getParameter ("FT_NR_Peso");
    String NR_Cubagem = request.getParameter ("FT_NR_Cubagem");
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    String VL_Nota_Fiscal = request.getParameter ("FT_VL_Nota_Fiscal");
    String PE_Desconto = request.getParameter ("FT_PE_Desconto");

    CotacaoED ed = new CotacaoED ();
    ed.setDM_Tipo_Pagamento (request.getParameter ("FT_DM_Tipo_Pagamento"));
    ed.setNM_Natureza (request.getParameter ("FT_NM_Natureza"));
    ed.setNM_Especie (request.getParameter ("FT_NM_Especie"));
    ed.setOID_Cotacao (request.getParameter ("oid_Cotacao"));
    ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Redespacho (request.getParameter ("oid_Pessoa_Redespacho"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Pagador (request.getParameter ("oid_Pessoa_Pagador"));
    ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());

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

    ed.setOID_Vendedor (request.getParameter ("oid_Vendedor"));
    ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));

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

    // System.out.println ("NR_Peso " + NR_Peso);
    if (JavaUtil.doValida (NR_Peso)) {
      ed.setNR_Peso (new Double (NR_Peso).doubleValue ());
    }
    // System.out.println ("ed.getNR_Peso() " + ed.getNR_Peso ());
    if (JavaUtil.doValida (NR_Peso_Cubado)) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }
    if (JavaUtil.doValida (NR_Cubagem)) {
      ed.setNR_Cubagem (Double.parseDouble (NR_Cubagem));
    }

    if (JavaUtil.doValida (NR_Volumes)) {
      ed.setNR_Volumes (new Double (NR_Volumes).doubleValue ());
    }

    if (JavaUtil.doValida (VL_Nota_Fiscal)) {
      ed.setVL_Nota_Fiscal (new Double (VL_Nota_Fiscal).doubleValue ());
    }

    if (JavaUtil.doValida (PE_Desconto)) {
      ed.setPE_Desconto (new Double (PE_Desconto).doubleValue ());
    }

    String NM_Solicitante = request.getParameter ("FT_NM_Solicitante");
    ed.setNM_Solicitante (JavaUtil.getValueDef (NM_Solicitante , ""));

    new CotacaoRN ().altera (ed);
  }

  public void confirma_Cotacao (HttpServletRequest request) throws Excecoes {

    CotacaoED ed = new CotacaoED ();
    ed.setOID_Cotacao (request.getParameter ("oid_Cotacao"));

    String oid_Movimento_Cotacao = request.getParameter ("oid_Movimento_Cotacao");
    if (JavaUtil.doValida (oid_Movimento_Cotacao)) {
      ed.setOID_Movimento_Cotacao (oid_Movimento_Cotacao);
    }

    String oid_Modal = request.getParameter ("oid_Modal");
    ed.setOID_Modal (Parametro_FixoED.getInstancia ().getOID_Modal ());
    if (JavaUtil.doValida (oid_Modal)) {
      ed.setOID_Modal (new Long (oid_Modal).longValue ());
    }

    new CotacaoRN ().confirma_Cotacao (ed);
  }

  public CotacaoED copia_Cotacao (HttpServletRequest request, String DM_Tipo_Documento) throws Excecoes {

    CotacaoED ed = new CotacaoED ();
    ed.setOID_Cotacao (request.getParameter ("oid_Cotacao"));

    return new CotacaoRN ().copia_Cotacao (ed);
  }


  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      CotacaoRN Cotacaorn = new CotacaoRN ();
      CotacaoED ed = new CotacaoED ();

      ed.setOID_Cotacao (request.getParameter ("oid_Cotacao"));

      Cotacaorn.deleta (ed);
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

  public void altera_Situacao (HttpServletRequest request) throws Excecoes {

    try {
      CotacaoRN Cotacaorn = new CotacaoRN ();
      CotacaoED ed = new CotacaoED ();

      ed.setAcao (request.getParameter ("acao"));
      ed.setOID_Cotacao (request.getParameter ("oid_Cotacao"));
      ed.setTX_Observacao (request.getParameter ("FT_DM_Motivo") + "-" + request.getParameter ("FT_TX_Observacao"));

      ed.setDM_Motivo_Perda ("");
      ed.setTX_Motivo_Perda ("");
      String DM_Motivo_Perda = request.getParameter ("FT_DM_Motivo_Perda");
      if (JavaUtil.doValida (DM_Motivo_Perda)) {
        ed.setDM_Motivo_Perda (DM_Motivo_Perda);
        String TX_Motivo_Perda = request.getParameter ("FT_TX_Motivo_Perda");
        if (JavaUtil.doValida (TX_Motivo_Perda)) {
          ed.setTX_Motivo_Perda (TX_Motivo_Perda);
        }
        String VL_Preco_Perda = request.getParameter ("FT_VL_Preco_Perda");
        if (JavaUtil.doValida (VL_Preco_Perda)) {
          ed.setVL_Preco_Perda (new Double (VL_Preco_Perda).doubleValue ());
        }
      }

      Cotacaorn.altera_Situacao (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao altera_Situacao");
      excecoes.setMetodo ("altera_Situacao");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList Cotacao_Lista (HttpServletRequest request) throws Excecoes {
    String nr_Cotacao = request.getParameter ("FT_NR_Cotacao");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Coleta = request.getParameter ("oid_Coleta");
    CotacaoED ed = new CotacaoED ();

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }
    
    ed.setDM_Situacao(request.getParameter ("FT_DM_Situacao"));
    
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario (request.getParameter ("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("") && !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    if (String.valueOf (oid_Coleta) != null && !String.valueOf (oid_Coleta).equals ("") && !String.valueOf (oid_Coleta).equals ("null")) {
      ed.setOID_Coleta (new Long (oid_Coleta).longValue ());
    }

    return new CotacaoRN ().lista (ed);

  }

  public ArrayList lista_Cotacao_Salva (String oid_Cotacao) throws Excecoes {
    CotacaoED ed = new CotacaoED ();

    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }

    return new CotacaoRN ().lista_Cotacao_Salva (ed);

  }


  public ArrayList lista_Cotacao_Salva (String oid_Cotacao, String oid_Coleta) throws Excecoes {
    CotacaoED ed = new CotacaoED ();

    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }
    if (JavaUtil.doValida (oid_Coleta)) {
      ed.setOID_Coleta (new Long(oid_Coleta).longValue());
    }

    return new CotacaoRN ().lista_Cotacao_Salva (ed);

  }


  public CotacaoED getByOID_Cotacao (String oid_Cotacao) throws Excecoes {

    CotacaoED ed = new CotacaoED ();

    ed.setOID_Cotacao (oid_Cotacao);
    return new CotacaoRN ().getByRecord (ed);

  }

  public CotacaoED getByRecord (HttpServletRequest request) throws Excecoes {
    CotacaoED ed = new CotacaoED ();

    String NR_Cotacao = request.getParameter ("FT_NR_Cotacao");
    if (JavaUtil.doValida (NR_Cotacao)) {
      ed.setNR_Cotacao (Long.parseLong (NR_Cotacao));
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (JavaUtil.doValida (oid_Unidade)) {
      ed.setOID_Unidade (Long.parseLong (oid_Unidade));
    }
    String oid_Cotacao = request.getParameter ("oid_Cotacao");
    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }
    return new CotacaoRN ().getByRecord (ed);
  }

  public CotacaoED consulta_Cotacao_Salva (HttpServletRequest request) throws Excecoes {
    CotacaoED ed = new CotacaoED ();

    String oid_Movimento_Cotacao = request.getParameter ("oid_Movimento_Cotacao");
    if (JavaUtil.doValida (oid_Movimento_Cotacao)) {
      ed.setOID_Movimento_Cotacao (oid_Movimento_Cotacao);
    }
    return new CotacaoRN ().consulta_Cotacao_Salva (ed);
  }

  public CotacaoED salva_Cotacao (HttpServletRequest request) throws Excecoes {
    CotacaoED ed = new CotacaoED ();
    String oid_Cotacao = request.getParameter ("oid_Cotacao");
    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }
    return new CotacaoRN ().salva_Cotacao (ed);
  }



  public byte[] imprime_Cotacao (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    CotacaoED ed = new CotacaoED ();

    String oid_Cotacao = request.getParameter ("oid_Cotacao");
    if (JavaUtil.doValida (oid_Cotacao)) {
      ed.setOID_Cotacao (oid_Cotacao);
    }
    // System.out.println("Bean 1");
    CotacaoRN geRN = new CotacaoRN ();

    byte[] b = geRN.imprime_Cotacao (ed);

    // System.out.println("Bean 99");

    return b;

  }



}
