package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Solicitacao_CompraRN;
import com.master.ed.Solicitacao_CompraED;
import com.master.util.Excecoes;
import com.master.util.Data;
import java.util.*;

public class est024Bean {

  public Solicitacao_CompraED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }
      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (oid_Unidade != null && oid_Unidade.length () > 0) {
        ed.setOid_unidade (Long.parseLong (oid_Unidade));
      }
      String oid_Usuario = request.getParameter ("oid_Usuario");
      if (oid_Usuario != null && oid_Usuario.length () > 0) {
        ed.setOid_usuario (Long.parseLong (oid_Usuario));
      }
      String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
      if (oid_Centro_Custo != null && oid_Centro_Custo.length () > 0) {
        ed.setOid_centro_custo (Long.parseLong (oid_Centro_Custo));
      }
      String oid_Modelo = request.getParameter ("oid_Modelo");
      if (oid_Modelo != null && oid_Modelo.length () > 0) {
        ed.setOid_modelo_nota_fiscal (Long.parseLong (oid_Modelo));
      }
      String oid_Autorizador = request.getParameter ("oid_Autorizador");
      if (oid_Autorizador != null && oid_Autorizador.length () > 0) {
        ed.setOid_autorizador (Long.parseLong (oid_Autorizador));
      }

      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status_Solicitacao (DM_Status);
      }

      String oid_Conta = request.getParameter ("oid_Conta");
      if (oid_Conta != null && oid_Conta.length () > 0) {
        ed.setOid_Conta (Long.parseLong (oid_Conta));
      }

      String oid_Conta_Servico = request.getParameter ("oid_Conta_Servico");
      if (oid_Conta_Servico != null && oid_Conta_Servico.length () > 0) {
        ed.setOid_Conta_Servico (Long.parseLong (oid_Conta_Servico));
      }

      String oid_Veiculo = request.getParameter ("oid_Veiculo");
      if (oid_Veiculo != null && oid_Veiculo.length () > 0) {
        ed.setOid_Veiculo (oid_Veiculo);
      }

      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (TX_Observacao != null && TX_Observacao.length () > 0) {
        ed.setTX_Observacao (TX_Observacao);
      }

      ed.setDt_Solicitacao (Data.getDataDMY ());
      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Entrega = request.getParameter ("FT_NM_Entrega");
      if (NM_Entrega != null && NM_Entrega.length () > 0) {
        ed.setNm_entrega (NM_Entrega);
      }
      String DT_Entrega = request.getParameter ("FT_DT_Entrega");
      if (DT_Entrega != null && DT_Entrega.length () > 0) {
        ed.setDt_entrega(DT_Entrega);
      }

      return Solicitacao_CompraRN.inclui (ed);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }
      String oid_Unidade = request.getParameter ("oid_Unidade");
      if (oid_Unidade != null && oid_Unidade.length () > 0) {
        ed.setOid_unidade (Long.parseLong (oid_Unidade));
      }
      String oid_Usuario = request.getParameter ("oid_Usuario");
      if (oid_Usuario != null && oid_Usuario.length () > 0) {
        ed.setOid_usuario (Long.parseLong (oid_Usuario));
      }

      String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
      if (oid_Centro_Custo != null && oid_Centro_Custo.length () > 0) {
        ed.setOid_centro_custo (Long.parseLong (oid_Centro_Custo));
      }
      String oid_Modelo = request.getParameter ("oid_Modelo");
      if (oid_Modelo != null && oid_Modelo.length () > 0) {
        ed.setOid_modelo_nota_fiscal (Long.parseLong (oid_Modelo));
      }
      String oid_Autorizador = request.getParameter ("oid_Autorizador");
      if (oid_Autorizador != null && oid_Autorizador.length () > 0) {
        ed.setOid_autorizador (Long.parseLong (oid_Autorizador));
      }

      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status_Solicitacao (DM_Status);
      }

      String DM_tipo_compra = request.getParameter ("FT_DM_Tipo_Compra");
      if (DM_tipo_compra != null && DM_tipo_compra.length () > 0) {
        ed.setDm_tipo_compra (DM_tipo_compra);
      }

      String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
      if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
        ed.setOid_fornecedor (oid_Fornecedor);
      }

      String oid_Conta = request.getParameter ("oid_Conta");
      if (oid_Conta != null && oid_Conta.length () > 0) {
        ed.setOid_Conta (Long.parseLong (oid_Conta));
      }
      String oid_Conta_Servico = request.getParameter ("oid_Conta_Servico");
      if (oid_Conta_Servico != null && oid_Conta_Servico.length () > 0) {
        ed.setOid_Conta_Servico (Long.parseLong (oid_Conta_Servico));
      }

      String oid_Veiculo = request.getParameter ("oid_Veiculo");
      if (oid_Veiculo != null && oid_Veiculo.length () > 0) {
        ed.setOid_Veiculo (oid_Veiculo);
      }

      String TX_Observacao = request.getParameter ("FT_TX_Observacao");
      if (TX_Observacao != null && TX_Observacao.length () > 0) {
        ed.setTX_Observacao (TX_Observacao);
      }

      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Entrega = request.getParameter ("FT_NM_Entrega");
      if (NM_Entrega != null && NM_Entrega.length () > 0) {
        ed.setNm_entrega (NM_Entrega);
      }

      String DT_Entrega = request.getParameter ("FT_DT_Entrega");
      if (DT_Entrega != null && DT_Entrega.length () > 0) {
        ed.setDt_entrega(DT_Entrega);
      }

      Solicitacao_CompraRN.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }

      Solicitacao_CompraRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void cancela (HttpServletRequest request) throws Excecoes {

    try {
      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }

      Solicitacao_CompraRN.cancela (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void exclui_Pedido (HttpServletRequest request) throws Excecoes {

	    try {
	      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
	      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
	      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	      }
	      // System.out.println(request.getParameter ("oid_Solicitacao_Compra"));

	      Solicitacao_CompraRN.exclui_Pedido (ed);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	  }

  public void inclui_Nota_Fiscal_Itens_Recebidos (HttpServletRequest request) throws Excecoes {

	    try {

	    	System.out.println("inclui_Nota_Fiscal_Itens_RecebidosBEAN");

	      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
	      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
	      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	      }

	      ed.setDt_emissao(request.getParameter ("FT_DT_Emissao"));
	      ed.setNr_nota_fiscal(request.getParameter ("FT_NR_Nota_Fiscal"));
	      ed.setNm_serie(request.getParameter ("FT_NM_Serie"));
	      ed.setNr_parcelas(Long.parseLong (request.getParameter ("FT_NR_Parcelas")));

	      System.out.println("inclui_Nota_Fiscal_Itens_RecebidosBEAN2");

	      Solicitacao_CompraRN.inclui_Nota_Fiscal_Itens_Recebidos (ed);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	  }

  public void atualiza_Estoque_Itens_Recebidos (HttpServletRequest request) throws Excecoes {

	    try {
	      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
	      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
	      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	      }

	      Solicitacao_CompraRN.atualiza_Estoque_Itens_Recebidos (ed);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	  }

  public void finaliza (HttpServletRequest request) throws Excecoes {

    try {
      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }

      Solicitacao_CompraRN.finaliza (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void gera_Pedido (HttpServletRequest request) throws Excecoes {

    try {
      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }
      String oid_Autorizador = request.getParameter ("oid_Autorizador");
      if (oid_Autorizador != null && oid_Autorizador.length () > 0) {
        ed.setOid_autorizador (Long.parseLong (oid_Autorizador));
      }
      String Usuario_Autorizador = request.getParameter ("Usuario_Autorizador");
      if (Usuario_Autorizador != null && Usuario_Autorizador.length () > 0) {
        ed.setCd_usuario_autorizador (Usuario_Autorizador);
      }

      ed.setOid_Orcamento_Conta ( request.getParameter ("oid_Orcamento_Conta"));

      Solicitacao_CompraRN.gera_Pedido (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public Solicitacao_CompraED getByRecord (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");

    if (oid_Solicitacao_Compra != null && !"null".equals (oid_Solicitacao_Compra) && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }

    return new Solicitacao_CompraRN ().getByRecord (ed);

  }

  public ArrayList Lista (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (oid_Unidade != null && oid_Unidade.length () > 0) {
      ed.setOid_unidade (Long.parseLong (oid_Unidade));
    }
    String oid_Usuario = request.getParameter ("oid_Usuario");
    if (oid_Usuario != null && oid_Usuario.length () > 0) {
      ed.setOid_usuario (Long.parseLong (oid_Usuario));
    }
    String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    if (oid_Centro_Custo != null && oid_Centro_Custo.length () > 0) {
      ed.setOid_centro_custo (Long.parseLong (oid_Centro_Custo));
    }
    String oid_Modelo = request.getParameter ("oid_Modelo");
    if (oid_Modelo != null && oid_Modelo.length () > 0) {
      ed.setOid_modelo_nota_fiscal (Long.parseLong (oid_Modelo));
    }
    String oid_Autorizador = request.getParameter ("oid_Autorizador");
    if (oid_Autorizador != null && oid_Autorizador.length () > 0) {
      ed.setOid_autorizador (Long.parseLong (oid_Autorizador));
    }
    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status_Solicitacao (DM_Status);
    }
    String DT_Solicitacao = request.getParameter ("FT_DT_Solicitacao");
    if (DT_Solicitacao != null && DT_Solicitacao.length () > 0) {
      ed.setDt_Solicitacao (DT_Solicitacao);
    }

    String oid_Conta = request.getParameter ("oid_Conta");
    if (oid_Conta != null && oid_Conta.length () > 0) {
      ed.setOid_Conta (Long.parseLong (oid_Conta));
    }

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    if (oid_Veiculo != null && oid_Veiculo.length () > 0) {
      ed.setOid_Veiculo (oid_Veiculo);
    }

    return new Solicitacao_CompraRN ().Lista (ed);

  }

  public ArrayList Lista_Itens (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }

    return new Solicitacao_CompraRN ().Lista_Itens (ed);

  }

  public Solicitacao_CompraED getByRecord_pedido (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }
    String oid_Pedido_Compra = request.getParameter ("oid_Pedido_Compra");
    if (oid_Pedido_Compra != null && oid_Pedido_Compra.length () > 0) {
      ed.setOid_Pedido_compra (Long.parseLong (oid_Pedido_Compra));
    }

    return new Solicitacao_CompraRN ().getByRecord_pedido (ed);

  }

  public ArrayList Lista_Itens_pedido (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Pedido_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }

    return new Solicitacao_CompraRN ().Lista_Itens (ed);

  }

  public ArrayList Lista_Itens_Entrega (HttpServletRequest request) throws Excecoes {

	    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	    String oid_Solicitacao_Compra = request.getParameter ("oid_Pedido_Compra");
	    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	    }
	    return new Solicitacao_CompraRN ().Lista_Itens_Entrega (ed);
  }

  public void imprime_pedido (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

	    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	    // System.out.println ("imprime_pedido iu ");

	    String oid_Solicitacao_Compra = request.getParameter ("oid_Pedido_Compra");
	    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	    }

	    new Solicitacao_CompraRN ().imprime_pedido (ed , response);
	  }

  public void imprime_solicitacao (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

	    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	    // System.out.println ("imprime_solicitacao iu ");

	    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
	    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	    }

	    new Solicitacao_CompraRN ().imprime_solicitacao (ed , response);
  }

  public ArrayList Lista_Fornecedores_Cotacoes (String oid) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = oid;
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }

    return new Solicitacao_CompraRN ().Lista_Fornecedores_Cotacoes (ed);

  }

  public ArrayList Lista_Pedidos (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Pedido_Compra = request.getParameter ("oid_Pedido_Compra");
    if (oid_Pedido_Compra != null && oid_Pedido_Compra.length () > 0) {
      ed.setOid_Pedido_compra (Long.parseLong (oid_Pedido_Compra));
    }
    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      ed.setOid_estoque (Long.parseLong (oid_Estoque));
    }
    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
      ed.setOid_fornecedor (oid_Fornecedor);
    }
    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status_pedido (DM_Status);
    }
    String DT_Solicitacao = request.getParameter ("FT_DT_Inicio");
    if (DT_Solicitacao != null && DT_Solicitacao.length () > 0) {
      ed.setDt_Solicitacao (DT_Solicitacao);
    }
    String DT_Pedido = request.getParameter ("FT_DT_Fim");
    if (DT_Pedido != null && DT_Pedido.length () > 0) {
      ed.setDt_pedido (DT_Pedido);
    }

    String oid_Conta = request.getParameter ("oid_Conta");
    if (oid_Conta != null && oid_Conta.length () > 0) {
      ed.setOid_Conta (Long.parseLong (oid_Conta));
    }

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    if (oid_Veiculo != null && oid_Veiculo.length () > 0) {
      ed.setOid_Veiculo (oid_Veiculo);
    }

    return new Solicitacao_CompraRN ().Lista_Pedidos (ed);

  }

  public String finaliza_pedido (HttpServletRequest request) throws Excecoes {

    String msg = "";
    try {
      Solicitacao_CompraRN Solicitacao_CompraRN = new Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Pedido_Compra = request.getParameter ("oid_Pedido_Compra");
      if (oid_Pedido_Compra != null && oid_Pedido_Compra.length () > 0) {
        ed.setOid_Pedido_compra (Long.parseLong (oid_Pedido_Compra));
      }

      msg = Solicitacao_CompraRN.finaliza_pedido (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    return msg;
  }

  public Solicitacao_CompraED getByRecord_pedidoS (String oid_Pedido_Compra) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    if (oid_Pedido_Compra != null && oid_Pedido_Compra.length () > 0) {
      ed.setOid_Pedido_compra (Long.parseLong (oid_Pedido_Compra));
    }

    return new Solicitacao_CompraRN ().getByRecord_pedido (ed);

  }

  public ArrayList Lista_Pedidos_NF (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
      ed.setOid_fornecedor (oid_Fornecedor);
    }

    return new Solicitacao_CompraRN ().Lista_Pedidos_NF (ed);

  }

  public void relSolicitacao (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (oid_Unidade != null && oid_Unidade.length () > 0) {
      ed.setOid_unidade (Long.parseLong (oid_Unidade));
    }
    String oid_Usuario = request.getParameter ("oid_Usuario");
    if (oid_Usuario != null && oid_Usuario.length () > 0) {
      ed.setOid_usuario (Long.parseLong (oid_Usuario));
    }
    String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    if (oid_Centro_Custo != null && oid_Centro_Custo.length () > 0) {
      ed.setOid_centro_custo (Long.parseLong (oid_Centro_Custo));
    }
    String oid_Modelo = request.getParameter ("oid_Modelo");
    if (oid_Modelo != null && oid_Modelo.length () > 0) {
      ed.setOid_modelo_nota_fiscal (Long.parseLong (oid_Modelo));
    }
    String oid_Autorizador = request.getParameter ("oid_Autorizador");
    if (oid_Autorizador != null && oid_Autorizador.length () > 0) {
      ed.setOid_autorizador (Long.parseLong (oid_Autorizador));
    }
    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status_Solicitacao (DM_Status);
    }
    String DT_Solicitacao = request.getParameter ("FT_DT_Solicitacao");
    if (DT_Solicitacao != null && DT_Solicitacao.length () > 0) {
      ed.setDt_Solicitacao (DT_Solicitacao);
    }

    new Solicitacao_CompraRN ().relSolicitacao (ed , response);

  }

  public void relPedido_Compra (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Pedido_Compra = request.getParameter ("oid_Pedido_Compra");
    if (oid_Pedido_Compra != null && oid_Pedido_Compra.length () > 0) {
      ed.setOid_Pedido_compra (Long.parseLong (oid_Pedido_Compra));
    }

    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status_pedido (DM_Status);
    }
    String DT_Solicitacao = request.getParameter ("FT_DT_Inicio");
    if (DT_Solicitacao != null && DT_Solicitacao.length () > 0) {
      ed.setDt_Solicitacao (DT_Solicitacao);
    }
    String DT_Pedido = request.getParameter ("FT_DT_Fim");
    if (DT_Pedido != null && DT_Pedido.length () > 0) {
      ed.setDt_pedido (DT_Pedido);
    }

    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
      ed.setOid_fornecedor (oid_Fornecedor);
    }

    String oid_Conta = request.getParameter ("oid_Conta");
    if (oid_Conta != null && oid_Conta.length () > 0) {
      ed.setOid_Conta (Long.parseLong (oid_Conta));
    }
    String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    if (oid_Centro_Custo != null && oid_Centro_Custo.length () > 0) {
      ed.setOid_centro_custo (Long.parseLong (oid_Centro_Custo));
    }

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    if (oid_Veiculo != null && oid_Veiculo.length () > 0) {
      ed.setOid_Veiculo (oid_Veiculo);
    }

    new Solicitacao_CompraRN ().relPedido_Compra (ed , response);

  }

  public void relPrazoMedio (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }

    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status_Solicitacao (DM_Status);
    }
    String DT_Solicitacao = request.getParameter ("FT_DT_Inicio");
    if (DT_Solicitacao != null && DT_Solicitacao.length () > 0) {
      ed.setDt_Solicitacao (DT_Solicitacao);
    }
    String DT_Pedido = request.getParameter ("FT_DT_Fim");
    if (DT_Pedido != null && DT_Pedido.length () > 0) {
      ed.setDt_pedido (DT_Pedido);
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (oid_Unidade != null && oid_Unidade.length () > 0) {
      ed.setOid_unidade (Long.parseLong (oid_Unidade));
    }
    String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    if (oid_Centro_Custo != null && oid_Centro_Custo.length () > 0) {
      ed.setOid_centro_custo (Long.parseLong (oid_Centro_Custo));
    }
    String oid_Autorizador = request.getParameter ("oid_Autorizador");
    if (oid_Autorizador != null && oid_Autorizador.length () > 0) {
      ed.setOid_autorizador (Long.parseLong (oid_Autorizador));
    }

    new Solicitacao_CompraRN ().relPrazoMedio (ed , response);

  }

  public Solicitacao_CompraED getMov_ServicoToNF (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    ed.setOid_fornecedor (request.getParameter ("oid_Fornecedor"));

    ed.setNr_documento (request.getParameter ("FT_NR_Documento"));

//		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
//		if (oid_Nota_Fiscal != null && oid_Nota_Fiscal.length() > 0)
//		{
//			ed.setOid_nota_fiscal(oid_Nota_Fiscal);
//		}
//		String oid_Movimento_Ordem_Servico = request.getParameter("oid_Movimento_Ordem_Servico");
//		if (oid_Movimento_Ordem_Servico != null && oid_Movimento_Ordem_Servico.length() > 0 && !oid_Movimento_Ordem_Servico.equals("null"))
//		{
//			ed.setOid_movimento_ordem_servico(Long.parseLong(oid_Movimento_Ordem_Servico));
//		}
    return new Solicitacao_CompraRN ().getMov_ServicoToNF (ed);
  }
}
