package com.master.iu;

/**
 * Título: est026Bean
 * Descrição: Cotação de Compras - Bean
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import javax.servlet.http.*;
import com.master.rn.Cotacao_CompraRN;
import com.master.ed.Cotacao_CompraED;
import com.master.util.Excecoes;
import com.master.util.Data;
import java.util.*;

public class est026Bean {

  public Cotacao_CompraED inclui_item (HttpServletRequest request) throws Excecoes {

    try {
      Cotacao_CompraRN Cotacao_CompraRN = new Cotacao_CompraRN ();
      Cotacao_CompraED ed = new Cotacao_CompraED ();

      String oid_Item_Cotacao_Compra = request.getParameter ("oid_Item_Cotacao_Compra");
      if (oid_Item_Cotacao_Compra != null && oid_Item_Cotacao_Compra.length () > 0) {
        ed.setOid_item_cotacao_compra (Long.parseLong (oid_Item_Cotacao_Compra));
      }
      String oid_Cotacao_Compra = request.getParameter ("oid_Cotacao_Compra");
      if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
        ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
      }
      String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
      if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
        ed.setOid_fornecedor (oid_Fornecedor);
      }
      String Valor = request.getParameter ("FT_VL_Preco");
      if (Valor != null && Valor.length () > 0) {
        ed.setVl_preco (Double.parseDouble (Valor));
      }
      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status_item (DM_Status);
      }
      ed.setDt_cotacao (Data.getDataDMY ());
      String NM_Obs = request.getParameter ("FT_TX_Observacoes");
      if (NM_Obs != null && NM_Obs.length () > 0) {
        ed.setNm_observacoes (NM_Obs);
      }
      String NM_Condicao_Pgto = request.getParameter ("FT_NM_Condicao_Pgto");
      if (NM_Condicao_Pgto != null && NM_Condicao_Pgto.length () > 0) {
        ed.setCondicao_pgto (NM_Condicao_Pgto);
      }
      String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
      if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length () > 0) {
        ed.setNr_prazo_entrega (new Integer (NR_Prazo_Entrega).intValue ());
      }
      String DM_Frete = request.getParameter ("FT_DM_Frete");
      if (DM_Frete != null && DM_Frete.length () > 0) {
        ed.setDm_frete (DM_Frete);
      }
      else {
        ed.setDm_frete ("P");
      }

      return Cotacao_CompraRN.inclui_item (ed);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void altera_item (HttpServletRequest request) throws Excecoes {

    try {
      Cotacao_CompraRN Cotacao_CompraRN = new Cotacao_CompraRN ();
      Cotacao_CompraED ed = new Cotacao_CompraED ();

      String oid_Item_Cotacao_Compra = request.getParameter ("oid_Item_Cotacao_Compra");
      if (oid_Item_Cotacao_Compra != null && oid_Item_Cotacao_Compra.length () > 0) {
        ed.setOid_item_cotacao_compra (Long.parseLong (oid_Item_Cotacao_Compra));
      }
      String oid_Cotacao_Compra = request.getParameter ("oid_Cotacao_Compra");
      if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
        ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
      }
      String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
      if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
        ed.setOid_fornecedor (oid_Fornecedor);
      }
      String Valor = request.getParameter ("FT_VL_Preco");
      if (Valor != null && Valor.length () > 0) {
        ed.setVl_preco (Double.parseDouble (Valor));
      }
      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status_item (DM_Status);
      }
      String NM_Obs = request.getParameter ("FT_TX_Observacoes");
      if (NM_Obs != null && NM_Obs.length () > 0) {
        ed.setNm_observacoes (NM_Obs);
      }
      String NM_Condicao_Pgto = request.getParameter ("FT_NM_Condicao_Pgto");
      if (NM_Condicao_Pgto != null && NM_Condicao_Pgto.length () > 0) {
        ed.setCondicao_pgto (NM_Condicao_Pgto);
      }
      String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
      if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length () > 0) {
        ed.setNr_prazo_entrega (new Integer (NR_Prazo_Entrega).intValue ());
      }
      String DM_Frete = request.getParameter ("FT_DM_Frete");
      if (DM_Frete != null && DM_Frete.length () > 0) {
        ed.setDm_frete (DM_Frete);
      }

      Cotacao_CompraRN.altera_item (ed);

    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void rejeita_item (HttpServletRequest request) throws Excecoes {

    try {

      Cotacao_CompraRN Cotacao_CompraRN = new Cotacao_CompraRN ();
      Cotacao_CompraED ed = new Cotacao_CompraED ();

      String oid_Item_Cotacao_Compra = request.getParameter ("oid_Item_Cotacao_Compra");
      if (oid_Item_Cotacao_Compra != null && oid_Item_Cotacao_Compra.length () > 0) {
        ed.setOid_item_cotacao_compra (Long.parseLong (oid_Item_Cotacao_Compra));
      }
      String oid_Cotacao_Compra = request.getParameter ("oid_Cotacao_Compra");
      if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
        ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
      }

      Cotacao_CompraRN.rejeita_item (ed);

    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void aceita_item (HttpServletRequest request) throws Excecoes {

    try {

      Cotacao_CompraRN Cotacao_CompraRN = new Cotacao_CompraRN ();
      Cotacao_CompraED ed = new Cotacao_CompraED ();

      String oid_Item_Cotacao_Compra = request.getParameter ("oid_Item_Cotacao_Compra");
      if (oid_Item_Cotacao_Compra != null && oid_Item_Cotacao_Compra.length () > 0) {
        ed.setOid_item_cotacao_compra (Long.parseLong (oid_Item_Cotacao_Compra));
      }
      String oid_Cotacao_Compra = request.getParameter ("oid_Cotacao_Compra");
      if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
        ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
      }

      Cotacao_CompraRN.aceita_item (ed);

    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public Cotacao_CompraED getByRecord_cotacao (HttpServletRequest request) throws Excecoes {

    Cotacao_CompraED ed = new Cotacao_CompraED ();

    String oid_Cotacao_Compra = request.getParameter ("oid_Cotacao_Compra");
    if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
      ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
    }
    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }
    String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
    if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
      ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
    }

    return new Cotacao_CompraRN ().getByRecord_cotacao (ed);

  }

  public ArrayList Lista_cotacoes (HttpServletRequest request) throws Excecoes {

    Cotacao_CompraED ed = new Cotacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }
    String oid_Item_Cotacao_Compra = request.getParameter ("oid_Item_Cotacao_Compra");
    if (oid_Item_Cotacao_Compra != null && oid_Item_Cotacao_Compra.length () > 0) {
      ed.setOid_item_cotacao_compra (Long.parseLong (oid_Item_Cotacao_Compra));
    }
    String oid_Cotacao_Compra = request.getParameter ("oid_Cotacao_Compra");
    if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
      ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
    }
    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      ed.setOid_estoque (Long.parseLong (oid_Estoque));
    }
    String produto = request.getParameter ("FT_NM_Produto");
    if (produto != null && produto.length () > 0) {
      ed.setNm_produto (produto);
    }
    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
      ed.setOid_fornecedor (oid_Fornecedor);
    }
    String Valor = request.getParameter ("FT_VL_Preco");
    if (Valor != null && Valor.length () > 0) {
      ed.setVl_preco (Double.parseDouble (Valor));
    }
    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status_item (DM_Status);
    }
    String NM_Obs = request.getParameter ("FT_TX_Observacoes");
    if (NM_Obs != null && NM_Obs.length () > 0) {
      ed.setNm_observacoes (NM_Obs);
    }
    String NM_Condicao_Pgto = request.getParameter ("FT_NM_Condicao_Pgto");
    if (NM_Condicao_Pgto != null && NM_Condicao_Pgto.length () > 0) {
      ed.setCondicao_pgto (NM_Condicao_Pgto);
    }
    String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
    if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length () > 0) {
      ed.setNr_prazo_entrega (new Integer (NR_Prazo_Entrega).intValue ());
    }
    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (DM_Frete != null && DM_Frete.length () > 0) {
      ed.setDm_frete (DM_Frete);
    }
    return new Cotacao_CompraRN ().Lista_cotacao (ed);

  }

  public Cotacao_CompraED getByRecord_item (HttpServletRequest request) throws Excecoes {

    Cotacao_CompraED ed = new Cotacao_CompraED ();

    String oid_Item_Cotacao_Compra = request.getParameter ("oid_Item_Cotacao_Compra");
    if (oid_Item_Cotacao_Compra != null && oid_Item_Cotacao_Compra.length () > 0) {
      ed.setOid_item_cotacao_compra (Long.parseLong (oid_Item_Cotacao_Compra));
    }

    return new Cotacao_CompraRN ().getByRecord_item (ed);

  }

  public ArrayList Lista_Itens (HttpServletRequest request) throws Excecoes {

    Cotacao_CompraED ed = new Cotacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }
    String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
    if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
      ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
    }
    String oid_Item_Cotacao_Compra = request.getParameter ("oid_Item_Cotacao_Compra");
    if (oid_Item_Cotacao_Compra != null && oid_Item_Cotacao_Compra.length () > 0) {
      ed.setOid_item_cotacao_compra (Long.parseLong (oid_Item_Cotacao_Compra));
    }
    String oid_Cotacao_Compra = request.getParameter ("oid_Cotacao_Compra");
    if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
      ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
    }
    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
      ed.setOid_fornecedor (oid_Fornecedor);
    }
    String Valor = request.getParameter ("FT_VL_Preco");
    if (Valor != null && Valor.length () > 0) {
      ed.setVl_preco (Double.parseDouble (Valor));
    }
    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status_item (DM_Status);
    }
    String NM_Obs = request.getParameter ("FT_TX_Observacoes");
    if (NM_Obs != null && NM_Obs.length () > 0) {
      ed.setNm_observacoes (NM_Obs);
    }
    String NM_Condicao_Pgto = request.getParameter ("FT_NM_Condicao_Pgto");
    if (NM_Condicao_Pgto != null && NM_Condicao_Pgto.length () > 0) {
      ed.setCondicao_pgto (NM_Condicao_Pgto);
    }
    String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
    if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length () > 0) {
      ed.setNr_prazo_entrega (new Integer (NR_Prazo_Entrega).intValue ());
    }
    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (DM_Frete != null && DM_Frete.length () > 0) {
      ed.setDm_frete (DM_Frete);
    }

    return new Cotacao_CompraRN ().Lista_item (ed);

  }

  public ArrayList Lista_ItensByCotacao (String cotacao) throws Excecoes {

    Cotacao_CompraED ed = new Cotacao_CompraED ();

    String oid_Cotacao_Compra = cotacao;
    if (oid_Cotacao_Compra != null && oid_Cotacao_Compra.length () > 0) {
      ed.setOid_cotacao_compra (Long.parseLong (oid_Cotacao_Compra));
    }

    return new Cotacao_CompraRN ().Lista_item (ed);

  }

}
