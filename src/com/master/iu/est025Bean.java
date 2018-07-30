package com.master.iu;

/**
 * Título: est025Bean
 * Descrição: Itens da Requisição de Materiais - Bean
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import javax.servlet.http.*;
import com.master.rn.Item_Solicitacao_CompraRN;
import com.master.ed.Solicitacao_CompraED;
import com.master.util.Excecoes;
import com.master.util.Data;
import java.util.*;

public class est025Bean {

  public Solicitacao_CompraED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Item_Solicitacao_CompraRN Item_Solicitacao_CompraRN = new Item_Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
      if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
        ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
      }
      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }
      String oid_Estoque = request.getParameter ("oid_Estoque");
      if (oid_Estoque != null && oid_Estoque.length () > 0) {
        ed.setOid_estoque (Long.parseLong (oid_Estoque));
      }
      String Valor = request.getParameter ("FT_VL_Quantidade");
      if (Valor != null && Valor.length () > 0) {
        ed.setVl_quantidade (Double.parseDouble (Valor));
      }

      Valor = request.getParameter ("FT_VL_Unitario");
      if (Valor != null && Valor.length () > 0) {
        ed.setVl_unitario(Double.parseDouble (Valor));
      }

      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status_item (DM_Status);
      }

      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Obs = request.getParameter ("FT_TX_Observacoes");
      if (NM_Obs != null && NM_Obs.length () > 0) {
        ed.setNm_observacoes (NM_Obs);
      }
      String NM_Produto = request.getParameter ("FT_NM_Produto");
      if (NM_Produto != null && NM_Produto.length () > 0) {
        ed.setNm_produto (NM_Produto);
      }
      String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
      if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length () > 0) {
        ed.setNr_prazo_entrega (Long.parseLong (NR_Prazo_Entrega));
      }
      String NR_Contrato = request.getParameter ("FT_NR_Contrato");
      if (NR_Contrato != null && NR_Contrato.length () > 0) {
        ed.setNr_contrato (NR_Contrato);
      }

      return Item_Solicitacao_CompraRN.inclui (ed);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

  public Solicitacao_CompraED recebe_Itens (HttpServletRequest request) throws Excecoes {

	    try {
	      Item_Solicitacao_CompraRN Item_Solicitacao_CompraRN = new Item_Solicitacao_CompraRN ();
	      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	      String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
	      if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
	        ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
	      }
	      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
	      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	      }
	      String oid_Estoque = request.getParameter ("oid_Estoque");
	      if (oid_Estoque != null && oid_Estoque.length () > 0) {
	        ed.setOid_estoque (Long.parseLong (oid_Estoque));
	      }
	      String oid_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
	      if (oid_Ordem_Servico != null && !oid_Ordem_Servico.equals("null") && !oid_Ordem_Servico.equals("") && oid_Ordem_Servico.length () > 0) {
	        ed.setOid_Ordem_Servico (Long.parseLong (oid_Ordem_Servico));
	      }

	      ed.setDt_entrega(Data.getDataDMY());
	      String DT_Entrega = request.getParameter ("FT_DT_Entrega");
	      if (DT_Entrega != null && DT_Entrega.length ()==10) {
	        ed.setDt_entrega (DT_Entrega);
	      }

	      String Valor = request.getParameter ("FT_VL_Quantidade");
	      if (Valor != null && Valor.length () > 0) {
	        ed.setVl_quantidade (Double.parseDouble (Valor));
	      }

	      Valor = request.getParameter ("FT_VL_Quantidade_Entrega");
	      if (Valor != null && Valor.length () > 0) {
	        ed.setVl_quantidade_entrega (Double.parseDouble (Valor));
	      }

	      return Item_Solicitacao_CompraRN.recebe_Itens (ed);

	    }

	    catch (Excecoes exc) {
	      throw exc;
	    }
	  }


  public Solicitacao_CompraED exclui_Recebimento (HttpServletRequest request) throws Excecoes {

	    try {
	      Item_Solicitacao_CompraRN Item_Solicitacao_CompraRN = new Item_Solicitacao_CompraRN ();
	      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

	      String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
	      if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
	        ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
	      }
	      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
	      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
	        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
	      }
	      String oid_Estoque = request.getParameter ("oid_Estoque");
	      if (oid_Estoque != null && oid_Estoque.length () > 0) {
	        ed.setOid_estoque (Long.parseLong (oid_Estoque));
	      }

	      String DT_Entrega = request.getParameter ("FT_DT_Entrega");
	      if (DT_Entrega != null && DT_Entrega.length ()==10) {
	        ed.setDt_entrega (DT_Entrega);
	      }

	      return Item_Solicitacao_CompraRN.exclui_Recebimento (ed);

	    }

	    catch (Excecoes exc) {
	      throw exc;
	    }
	  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Item_Solicitacao_CompraRN Item_Solicitacao_CompraRN = new Item_Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
      if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
        ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
      }
      String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
      if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
        ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
      }
      String oid_Estoque = request.getParameter ("oid_Estoque");
      if (oid_Estoque != null && oid_Estoque.length () > 0) {
        ed.setOid_estoque (Long.parseLong (oid_Estoque));
      }
      String Valor = request.getParameter ("FT_VL_Quantidade");
      if (Valor != null && Valor.length () > 0) {
        ed.setVl_quantidade (Double.parseDouble (Valor));
      }

      Valor = request.getParameter ("FT_VL_Unitario");
      if (Valor != null && Valor.length () > 0) {
        ed.setVl_unitario(Double.parseDouble (Valor));
      }

      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status_item (DM_Status);
      }

      String DT_Entrega = request.getParameter ("FT_DT_Entrega");
      if (DT_Entrega != null && DT_Entrega.length () > 0) {
        ed.setDt_entrega (DT_Entrega);
      }
      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Obs = request.getParameter ("FT_TX_Observacoes");
      if (NM_Obs != null && NM_Obs.length () > 0) {
        ed.setNm_observacoes (NM_Obs);
      }
      String NM_Produto = request.getParameter ("FT_NM_Produto");
      if (NM_Produto != null && NM_Produto.length () > 0) {
        ed.setNm_produto (NM_Produto);
      }
      String NR_Prazo_Entrega = request.getParameter ("FT_NR_Prazo_Entrega");
      if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length () > 0) {
        ed.setNr_prazo_entrega (Long.parseLong (NR_Prazo_Entrega));
      }
      String NR_Contrato = request.getParameter ("FT_NR_Contrato");
      if (NR_Contrato != null && NR_Contrato.length () > 0) {
        ed.setNr_contrato (NR_Contrato);
      }

      Item_Solicitacao_CompraRN.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Item_Solicitacao_CompraRN Item_Solicitacao_CompraRN = new Item_Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
      if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
        ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
      }

      Item_Solicitacao_CompraRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void cancela (HttpServletRequest request) throws Excecoes {

    try {
      Item_Solicitacao_CompraRN Item_Solicitacao_CompraRN = new Item_Solicitacao_CompraRN ();
      Solicitacao_CompraED ed = new Solicitacao_CompraED ();

      String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
      if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
        ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
      }

      Item_Solicitacao_CompraRN.cancela (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public Solicitacao_CompraED getByRecord (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
    if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
      ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
    }

    return new Item_Solicitacao_CompraRN ().getByRecord (ed);

  }

  public ArrayList Lista (HttpServletRequest request) throws Excecoes {

    Solicitacao_CompraED ed = new Solicitacao_CompraED ();

    String oid_Solicitacao_Compra = request.getParameter ("oid_Solicitacao_Compra");
    if (oid_Solicitacao_Compra != null && oid_Solicitacao_Compra.length () > 0) {
      ed.setOid_Solicitacao_compra (Long.parseLong (oid_Solicitacao_Compra));
    }
    String oid_Item_Solicitacao_Compra = request.getParameter ("oid_Item_Solicitacao_Compra");
    if (oid_Item_Solicitacao_Compra != null && oid_Item_Solicitacao_Compra.length () > 0) {
      ed.setOid_item_Solicitacao_compra (Long.parseLong (oid_Item_Solicitacao_Compra));
    }

    return new Item_Solicitacao_CompraRN ().Lista (ed);

  }

}
