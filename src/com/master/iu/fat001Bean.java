package com.master.iu;

import java.util.*;
import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;
public class fat001Bean {

  Utilitaria util = new Utilitaria ();

  public ArrayList listaFatura (long NR_Fatura , String DT_Emissao) throws Excecoes {

    return new FaturamentoRN ().listaFatura (NR_Fatura , DT_Emissao);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      FaturamentoRN Faturamentorn = new FaturamentoRN ();
      FaturamentoED ed = new FaturamentoED ();

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
      ed.setDM_Lista (request.getParameter ("FT_DM_Lista"));

      Faturamentorn.altera (ed);
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
      FaturamentoRN Faturamentorn = new FaturamentoRN ();
      FaturamentoED ed = new FaturamentoED ();

      ed.setOID_Fatura (request.getParameter ("oid_Faturamento"));

      Faturamentorn.deleta (ed);
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

  public FaturamentoED getByRecord (HttpServletRequest request) throws Excecoes {

    FaturamentoED ed = new FaturamentoED ();

    String NR_Faturamento = request.getParameter ("FT_NR_Faturamento");
    if (NR_Faturamento != null && NR_Faturamento.length () > 0) {
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    }
    ed.setOID_Fatura (request.getParameter ("oid_Faturamento"));
    return new FaturamentoRN ().getByRecord (ed);

  }

  public FaturamentoED geraFatura (HttpServletRequest request) throws Excecoes {

    FaturamentoED ed = new FaturamentoED ();
    ed = this.carregaED (request);

    return new FaturamentoRN ().geraFatura (ed);

  }

  private FaturamentoED carregaED (HttpServletRequest request) throws Excecoes {

    FaturamentoED ed = new FaturamentoED ();

    // System.out.println ("faturam  carregaED ");

    String VL_Fatura = request.getParameter ("FT_VL_Fatura");
    String DT_Vencimento_Informado = request.getParameter ("FT_DT_Vencimento");
    String DT_Vencimento_Minimo = request.getParameter ("FT_DT_Vencimento_Minimo");
    String NR_Conhecimento_Inicial = request.getParameter ("FT_NR_Conhecimento_Inicial");
    String NR_Conhecimento_Final = request.getParameter ("FT_NR_Conhecimento_Final");
    String NR_Conhecimento_Sequencia = request.getParameter ("FT_NR_Conhecimento_Sequencia");
    String OID_Unidade = request.getParameter ("oid_Unidade");
    String OID_Unidade_Emissora = request.getParameter ("oid_Unidade_Emissora");
    String OID_Produto = request.getParameter ("oid_Produto");
    String OID_Carteira_Informada = request.getParameter ("oid_Carteira");
    String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    String NM_Lote_Faturamento = request.getParameter ("FT_NM_Lote_Faturamento");
    String DM_Tipo_Cobranca = request.getParameter ("FT_DM_Tipo_Cobranca");
    String DM_Tipo_Faturamento = request.getParameter ("FT_DM_Tipo_Faturamento");
    String DM_Tipo_Pagador = request.getParameter ("FT_DM_Tipo_Pagador");
    String OID_Centro_Custo = request.getParameter ("oid_Centro_Custo");

    // System.out.println ("faturam  carregaED ");

    if (String.valueOf (VL_Fatura) != null && !String.valueOf (VL_Fatura).equals ("")) {
      ed.setVL_Fatura (new Double (VL_Fatura).doubleValue ());
    }

    String cd_Rota_Entrega = request.getParameter ("FT_CD_Rota_Entrega");
    if (JavaUtil.doValida (cd_Rota_Entrega)) {
      ed.setCD_Rota_Entrega (cd_Rota_Entrega);
    }

    // System.out.println ("faturam  incluiFatura bean ");
    ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
    ed.setDM_Tipo_Faturamento (request.getParameter ("FT_DM_Tipo_Faturamento"));
    ed.setDT_Emissao_Inicial (request.getParameter ("FT_DT_Emissao_Inicial"));

    // System.out.println ("faturam  bean DT_Vencimento" + DT_Vencimento);
    if (String.valueOf (DT_Vencimento_Informado) != null && !String.valueOf (DT_Vencimento_Informado).equals ("") && !String.valueOf (DT_Vencimento_Informado).equals ("null")) {
      ed.setDT_Vencimento_Informado (DT_Vencimento_Informado);
    }

    // System.out.println ("faturam  bean DT_Vencimento_Minimo" + DT_Vencimento_Minimo);
    if (String.valueOf (DT_Vencimento_Minimo) != null && !String.valueOf (DT_Vencimento_Minimo).equals ("") && !String.valueOf (DT_Vencimento_Minimo).equals ("null")) {
      ed.setDT_Vencimento_Minimo (DT_Vencimento_Minimo);
    }

    ed.setDT_Emissao_Final (request.getParameter ("FT_DT_Emissao_Final"));

    ed.setOID_Pessoa_Pagador (request.getParameter ("oid_Pessoa_Pagador"));

    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setOID_Pessoa_Remetente (request.getParameter ("oid_Pessoa_Remetente"));

    if (String.valueOf (NR_Conhecimento_Inicial) != null && !String.valueOf (NR_Conhecimento_Inicial).equals ("")) {
      ed.setNR_Conhecimento_Inicial (new Long (NR_Conhecimento_Inicial).longValue ());
    }

    if (String.valueOf (NR_Conhecimento_Final) != null && !String.valueOf (NR_Conhecimento_Final).equals ("")) {
      ed.setNR_Conhecimento_Final (new Long (NR_Conhecimento_Final).longValue ());
    }

    if (util.doValida (NR_Conhecimento_Sequencia)) {
      ed.setNR_Conhecimento_Sequencia (NR_Conhecimento_Sequencia);
    }

    // System.out.println ("faturam  bean Unidade->" + OID_Unidade);
    if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("")) {
      ed.setOID_Unidade_CTRC (new Long (OID_Unidade).longValue ());
    }

    if (String.valueOf (OID_Produto) != null && !String.valueOf (OID_Produto).equals ("")) {
      ed.setOID_Produto (new Long (OID_Produto).longValue ());
    }
    // System.out.println ("faturam  bean OID_Produto depois->" + ed.getOID_Produto ());

    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("") && !String.valueOf (oid_Grupo_Economico).equals ("null")) {
      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
    }

    ed.setOID_Carteira_Informada (0);
    if (util.doValida (OID_Carteira_Informada)) {
      ed.setOID_Carteira_Informada (Long.parseLong (OID_Carteira_Informada));
    }

    if (String.valueOf (DM_Tipo_Conhecimento) != null && !String.valueOf (DM_Tipo_Conhecimento).equals ("")) {
      ed.setDM_Tipo_Conhecimento (DM_Tipo_Conhecimento);
    }

    if (String.valueOf (DM_Tipo_Documento) != null && !String.valueOf (DM_Tipo_Documento).equals ("")) {
      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
    }

    if (util.doValida (NM_Lote_Faturamento)) {
      ed.setNM_Lote_Faturamento (NM_Lote_Faturamento);
    }

    if (String.valueOf (OID_Unidade_Emissora) != null &&
        !String.valueOf (OID_Unidade_Emissora).equals ("") &&
        !String.valueOf (OID_Unidade_Emissora).equals ("null")) {
      ed.setOID_Unidade (new Long (OID_Unidade_Emissora).longValue ());
    }

    if (String.valueOf (OID_Centro_Custo) != null &&
        !String.valueOf (OID_Centro_Custo).equals ("") &&
        !String.valueOf (OID_Centro_Custo).equals ("null")) {
      ed.setOID_Centro_Custo (new Long (OID_Centro_Custo).longValue ());
    }

    if (util.doValida (DM_Tipo_Cobranca)) {
      ed.setDM_Tipo_Cobrancao (DM_Tipo_Cobranca);
    }

    if (util.doValida (DM_Tipo_Faturamento)) {
        ed.setDM_Tipo_Faturamento (DM_Tipo_Faturamento);
      }


    if (util.doValida (DM_Tipo_Pagador)) {
      ed.setDM_Tipo_Pagador (DM_Tipo_Pagador);
    }
    return ed;
  }

  public byte[] geraPre_Faturamento (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    FaturamentoED ed = new FaturamentoED ();

    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("")) {
      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (Dt_Emissao_Final);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("") && !String.valueOf (oid_Grupo_Economico).equals ("null")) {
      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
    }

    String Dm_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
    if (String.valueOf (Dm_Tipo_Conhecimento) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
      ed.setDM_Tipo_Conhecimento (Dm_Tipo_Conhecimento);
    }

    String DM_Tipo_Cobranca = request.getParameter ("FT_DM_Tipo_Cobranca");
    if (String.valueOf (DM_Tipo_Cobranca) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
      ed.setDM_Tipo_Cobranca (DM_Tipo_Cobranca);
    }

    String DM_Baixado = request.getParameter ("FT_DM_Baixado");
    if (String.valueOf (DM_Baixado) != null && !String.valueOf (DM_Baixado).equals ("") && !String.valueOf (DM_Baixado).equals ("null")) {
      ed.setDM_Baixado(DM_Baixado);
    }

    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    if (String.valueOf (DM_Tipo_Documento) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
    }

    String DM_Tipo_Transporte = request.getParameter ("FT_DM_Tipo_Transporte");
    if (String.valueOf (DM_Tipo_Transporte) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
      ed.setDM_Tipo_Transporte (DM_Tipo_Transporte);
    }

    String DM_Tipo_Faturamento = request.getParameter ("FT_DM_Tipo_Faturamento");
    if (String.valueOf (DM_Tipo_Faturamento) != null && !String.valueOf (DM_Tipo_Faturamento).equals ("")) {
      ed.setDM_Tipo_Faturamento (DM_Tipo_Faturamento);
    }

    String OID_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    if (String.valueOf (OID_Centro_Custo) != null &&
        !String.valueOf (OID_Centro_Custo).equals ("") &&
        !String.valueOf (OID_Centro_Custo).equals ("null")) {
      ed.setOID_Centro_Custo (new Long (OID_Centro_Custo).longValue ());
    }

    String DM_Compr_Entrega_Fatura = request.getParameter ("FT_DM_Compr_Entrega_Fatura");
    if (String.valueOf (DM_Compr_Entrega_Fatura) != null && !String.valueOf (DM_Compr_Entrega_Fatura).equals ("")) {
      ed.setDM_Compr_Entrega_Fatura (DM_Compr_Entrega_Fatura);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    FaturamentoRN ctoRN = new FaturamentoRN ();

    return ctoRN.geraPre_Faturamento (ed);

  }

  public ArrayList listaFaturaPre_Fatura (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

	    FaturamentoED ed = new FaturamentoED ();

	    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
	    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("")) {
	      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
	    }

	    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
	    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
	      ed.setDT_Emissao_Inicial (Dt_Emissao_Inicial);
	    }

	    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
	    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
	      ed.setDT_Emissao_Final (Dt_Emissao_Final);
	    }

	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
	      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
	    }

	    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
	    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("") && !String.valueOf (oid_Grupo_Economico).equals ("null")) {
	      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
	    }

	    String Dm_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
	    if (String.valueOf (Dm_Tipo_Conhecimento) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
	      ed.setDM_Tipo_Conhecimento (Dm_Tipo_Conhecimento);
	    }

	    String DM_Tipo_Cobranca = request.getParameter ("FT_DM_Tipo_Cobranca");
	    if (String.valueOf (DM_Tipo_Cobranca) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
	      ed.setDM_Tipo_Cobranca (DM_Tipo_Cobranca);
	    }

	    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
	    if (String.valueOf (DM_Tipo_Documento) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
	      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
	    }

	    String DM_Tipo_Transporte = request.getParameter ("FT_DM_Tipo_Transporte");
	    if (String.valueOf (DM_Tipo_Transporte) != null && !String.valueOf (Dm_Tipo_Conhecimento).equals ("")) {
	      ed.setDM_Tipo_Transporte (DM_Tipo_Transporte);
	    }

	    String DM_Tipo_Faturamento = request.getParameter ("FT_DM_Tipo_Faturamento");
	    if (String.valueOf (DM_Tipo_Faturamento) != null && !String.valueOf (DM_Tipo_Faturamento).equals ("")) {
	      ed.setDM_Tipo_Faturamento (DM_Tipo_Faturamento);
	    }

	    String OID_Centro_Custo = request.getParameter ("oid_Centro_Custo");
	    if (String.valueOf (OID_Centro_Custo) != null &&
	        !String.valueOf (OID_Centro_Custo).equals ("") &&
	        !String.valueOf (OID_Centro_Custo).equals ("null")) {
	      ed.setOID_Centro_Custo (new Long (OID_Centro_Custo).longValue ());
	    }

	    String DM_Compr_Entrega_Fatura = request.getParameter ("FT_DM_Compr_Entrega_Fatura");
	    if (String.valueOf (DM_Compr_Entrega_Fatura) != null && !String.valueOf (DM_Compr_Entrega_Fatura).equals ("")) {
	      ed.setDM_Compr_Entrega_Fatura (DM_Compr_Entrega_Fatura);
	    }

	    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    return new FaturamentoRN ().listaFaturaPre_Fatura (ed);
  }

}
