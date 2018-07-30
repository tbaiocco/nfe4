package com.master.iu;

import javax.servlet.http.*;

import com.master.rn.EstoqueRN;
import com.master.ed.EstoqueED;
import com.master.util.Excecoes;
import com.master.util.Data;
import java.util.*;

public class est014Bean {

  public EstoqueED inclui (HttpServletRequest request) throws Excecoes {

    try {
      EstoqueRN EstoqueRN = new EstoqueRN ();
      EstoqueED ed = new EstoqueED ();

      String oid_Subgrupo_Estoque = request.getParameter ("oid_Subgrupo_Estoque");
      if (oid_Subgrupo_Estoque != null && oid_Subgrupo_Estoque.length () > 0) {
        int b = (new Integer (oid_Subgrupo_Estoque)).intValue ();
        ed.setOid_sub_grupo_estoque (b);
      }

      String oid_Grupo_Estoque = request.getParameter ("oid_Grupo_Estoque");
      if (oid_Grupo_Estoque != null && oid_Grupo_Estoque.length () > 0) {
        int b = (new Integer (oid_Grupo_Estoque)).intValue ();
        ed.setOid_grupo_estoque (b);
      }
      String oid_Produto = request.getParameter ("oid_Produto");
      if (oid_Produto != null && oid_Produto.length () > 0) {
        long b = (new Long (oid_Produto)).longValue ();
        ed.setOid_produto (b);
      }
      String oid_Unidade = request.getParameter ("oid_Unidade_Produto");
      if (oid_Unidade != null && oid_Unidade.length () > 0) {
        long b = (new Long (oid_Unidade)).longValue ();
        ed.setOid_unidade_produto (b);
      }
      String CD_Estoque = request.getParameter ("FT_CD_Estoque");
      if (CD_Estoque != null && CD_Estoque.length () > 0) {
        ed.setCd_estoque (CD_Estoque);
      }
      String NM_Estoque = request.getParameter ("FT_NM_Estoque");
      if (NM_Estoque != null && NM_Estoque.length () > 0) {
        ed.setNm_estoque (NM_Estoque);
      }
      String NM_Classificacao_Fiscal = request.getParameter ("FT_NM_Classificacao_Fiscal");
      if (NM_Classificacao_Fiscal != null && NM_Classificacao_Fiscal.length () > 0) {
        ed.setNm_classificacao_fiscal (NM_Classificacao_Fiscal);
      }
      String NM_Referencia_Fabrica = request.getParameter ("FT_NM_Referencia_Fabrica");
      if (NM_Referencia_Fabrica != null && NM_Referencia_Fabrica.length () > 0) {
        ed.setNm_referencia (NM_Referencia_Fabrica);
      }
      String DM_Negativo = request.getParameter ("FT_DM_Negativo");
      if (DM_Negativo != null && DM_Negativo.length () > 0) {
        ed.setDm_negativo (DM_Negativo);
      }
      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status (DM_Status);
      }
      String DM_Tipo_Produto = request.getParameter ("FT_DM_Tipo_Produto");
      if (DM_Tipo_Produto != null && DM_Tipo_Produto.length () > 0) {
        ed.setDm_tipo_produto (DM_Tipo_Produto);
      }
      String VL_Estoque_Minimo = request.getParameter ("FT_VL_Estoque_Minimo");
      if (VL_Estoque_Minimo != null && VL_Estoque_Minimo.length () > 0) {
        ed.setVl_estoque_minimo (new Double (VL_Estoque_Minimo).doubleValue ());

      }
      String VL_Estoque_Maximo = request.getParameter ("FT_VL_Estoque_Maximo");
      if (VL_Estoque_Maximo != null && VL_Estoque_Maximo.length () > 0) {
        ed.setVl_estoque_maximo (new Double (VL_Estoque_Maximo).doubleValue ());

      }
      String VL_Custo = request.getParameter ("FT_VL_Custo");
      if (VL_Custo != null && VL_Custo.length () > 0) {
        ed.setVl_custo (new Double (VL_Custo).doubleValue ());

      }
      String VL_Custo_Minimo = request.getParameter ("FT_VL_Custo_Minimo");
      if (VL_Custo_Minimo != null && VL_Custo_Minimo.length () > 0) {
        ed.setVl_custo_minimo (new Double (VL_Custo_Minimo).doubleValue ());
      }
      String VL_Custo_Maximo = request.getParameter ("FT_VL_Custo_Maximo");
      if (VL_Custo_Maximo != null && VL_Custo_Maximo.length () > 0) {
        ed.setVl_custo_maximo (new Double (VL_Custo_Maximo).doubleValue ());
      }
      ed.setVl_custo_minimo (ed.getVl_custo ());
      ed.setVl_custo_maximo (ed.getVl_custo ());

      String NR_Leadtime = request.getParameter ("FT_NR_Leadtime");
      if (NR_Leadtime != null && NR_Leadtime.length () > 0) {
        long b = (new Long (NR_Leadtime)).longValue ();
        ed.setNr_leadtime (b);
      }
      String NR_Leadtime_Maximo = request.getParameter ("FT_NR_Leadtime_Maximo");
      if (NR_Leadtime_Maximo != null && NR_Leadtime_Maximo.length () > 0) {
        long b = (new Long (NR_Leadtime_Maximo)).longValue ();
        ed.setNr_leadtime_maximo (b);
      }
      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Aplicacao = request.getParameter ("FT_NM_Aplicacao");
      if (NM_Aplicacao != null && NM_Aplicacao.length () > 0) {
        ed.setNm_aplicacao (NM_Aplicacao);
      }

      // System.out.println ("oid_Grupo_Estoque->> " + oid_Grupo_Estoque);
      return EstoqueRN.inclui (ed);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      EstoqueRN EstoqueRN = new EstoqueRN ();
      EstoqueED ed = new EstoqueED ();

      String oid_Estoque = request.getParameter ("oid_Estoque");
      if (oid_Estoque != null && oid_Estoque.length () > 0) {
        long b = (new Long (oid_Estoque)).longValue ();
        ed.setOid_estoque (b);
      }
      String oid_Grupo_Estoque = request.getParameter ("oid_Grupo_Estoque");
      if (oid_Grupo_Estoque != null && oid_Grupo_Estoque.length () > 0) {
        int b = (new Integer (oid_Grupo_Estoque)).intValue ();
        ed.setOid_grupo_estoque (b);
      }
      String oid_Subgrupo_Estoque = request.getParameter ("oid_Subgrupo_Estoque");
      if (oid_Subgrupo_Estoque != null && oid_Subgrupo_Estoque.length () > 0) {
        int b = (new Integer (oid_Subgrupo_Estoque)).intValue ();
        ed.setOid_sub_grupo_estoque (b);
      }
      String oid_Produto = request.getParameter ("oid_Produto");
      if (oid_Produto != null && oid_Produto.length () > 0) {
        long b = (new Long (oid_Produto)).longValue ();
        ed.setOid_produto (b);
      }
      String oid_Unidade = request.getParameter ("oid_Unidade_Produto");
      if (oid_Unidade != null && oid_Unidade.length () > 0) {
        long b = (new Long (oid_Unidade)).longValue ();
        ed.setOid_unidade_produto (b);
      }
      String CD_Estoque = request.getParameter ("FT_CD_Estoque");
      if (CD_Estoque != null && CD_Estoque.length () > 0) {
        ed.setCd_estoque (CD_Estoque);
      }
      String NM_Estoque = request.getParameter ("FT_NM_Estoque");
      if (NM_Estoque != null && NM_Estoque.length () > 0) {
        ed.setNm_estoque (NM_Estoque);
      }
      String NM_Classificacao_Fiscal = request.getParameter ("FT_NM_Classificacao_Fiscal");
      if (NM_Classificacao_Fiscal != null && NM_Classificacao_Fiscal.length () > 0) {
        ed.setNm_classificacao_fiscal (NM_Classificacao_Fiscal);
      }
      String NM_Referencia_Fabrica = request.getParameter ("FT_NM_Referencia_Fabrica");
      if (NM_Referencia_Fabrica != null && NM_Referencia_Fabrica.length () > 0) {
        ed.setNm_referencia (NM_Referencia_Fabrica);
      }
      String DM_Negativo = request.getParameter ("FT_DM_Negativo");
      if (DM_Negativo != null && DM_Negativo.length () > 0) {
        ed.setDm_negativo (DM_Negativo);
      }
      String DM_Status = request.getParameter ("FT_DM_Status");
      if (DM_Status != null && DM_Status.length () > 0) {
        ed.setDm_status (DM_Status);
      }
      String DM_Tipo_Produto = request.getParameter ("FT_DM_Tipo_Produto");
      if (DM_Tipo_Produto != null && DM_Tipo_Produto.length () > 0) {
        ed.setDm_tipo_produto (DM_Tipo_Produto);
      }
      String VL_Estoque_Minimo = request.getParameter ("FT_VL_Estoque_Minimo");
      if (VL_Estoque_Minimo != null && VL_Estoque_Minimo.length () > 0) {
        ed.setVl_estoque_minimo (new Double (VL_Estoque_Minimo).doubleValue ());

      }

      // Estoque Atual
      String VL_Estoque = request.getParameter ("FT_VL_Estoque");
      if (VL_Estoque != null && VL_Estoque.length () > 0) {
        ed.setVl_estoque (new Double (VL_Estoque).doubleValue ());

      }

      String VL_Estoque_Maximo = request.getParameter ("FT_VL_Estoque_Maximo");
      if (VL_Estoque_Maximo != null && VL_Estoque_Maximo.length () > 0) {
        ed.setVl_estoque_maximo (new Double (VL_Estoque_Maximo).doubleValue ());

      }
      String VL_Custo = request.getParameter ("FT_VL_Custo");
      if (VL_Custo != null && VL_Custo.length () > 0) {
        ed.setVl_custo (new Double (VL_Custo).doubleValue ());

      }
      String VL_Custo_Minimo = request.getParameter ("FT_VL_Custo_Minimo");
      if (VL_Custo_Minimo != null && VL_Custo_Minimo.length () > 0) {
        ed.setVl_custo_minimo (new Double (VL_Custo_Minimo).doubleValue ());
      }
      String VL_Custo_Maximo = request.getParameter ("FT_VL_Custo_Maximo");
      if (VL_Custo_Maximo != null && VL_Custo_Maximo.length () > 0) {
        ed.setVl_custo_maximo (new Double (VL_Custo_Maximo).doubleValue ());
      }
      if (ed.getVl_custo_minimo () > ed.getVl_custo ()) {
        ed.setVl_custo_minimo (ed.getVl_custo ());
      }
      if (ed.getVl_custo_maximo () < ed.getVl_custo ()) {
        ed.setVl_custo_maximo (ed.getVl_custo ());
      }

      String NR_Leadtime = request.getParameter ("FT_NR_Leadtime");
      if (NR_Leadtime != null && NR_Leadtime.length () > 0) {
        long b = (new Long (NR_Leadtime)).longValue ();
        ed.setNr_leadtime (b);
      }
      String NR_Leadtime_Maximo = request.getParameter ("FT_NR_Leadtime_Maximo");
      if (NR_Leadtime_Maximo != null && NR_Leadtime_Maximo.length () > 0) {
        long b = (new Long (NR_Leadtime_Maximo)).longValue ();
        ed.setNr_leadtime_maximo (b);
      }
      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Aplicacao = request.getParameter ("FT_NM_Aplicacao");
      if (NM_Aplicacao != null && NM_Aplicacao.length () > 0) {
        ed.setNm_aplicacao (NM_Aplicacao);
      }

      EstoqueRN.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      EstoqueRN EstoqueRN = new EstoqueRN ();
      EstoqueED ed = new EstoqueED ();

      String oid_Estoque = request.getParameter ("oid_Estoque");
      if (oid_Estoque != null && oid_Estoque.length () > 0) {
        long b = (new Long (oid_Estoque)).longValue ();
        ed.setOid_estoque (b);
      }

      EstoqueRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public EstoqueED getByRecord (HttpServletRequest request) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }
    String CD_Estoque = request.getParameter ("FT_CD_Estoque");
    if (CD_Estoque != null && CD_Estoque.length () > 0) {
      ed.setCd_estoque (CD_Estoque);
    }
    return new EstoqueRN ().getByRecord (ed);

  }

  public EstoqueED getByCD_Estoque (String cd) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String CD_Estoque = cd;
    if (CD_Estoque != null && CD_Estoque.length () > 0) {
      ed.setCd_estoque (CD_Estoque);
    }

    return new EstoqueRN ().getByRecord (ed);

  }

  public EstoqueED getByNm_Estoque (String Nm_Estoque) throws Excecoes {

	    EstoqueED ed = new EstoqueED ();

	    ed.setNm_estoque(Nm_Estoque);
	    return new EstoqueRN ().getByRecord (ed);

	  }

  public ArrayList getByNM_Estoque (String nome) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String NM_Estoque = nome;
    if (NM_Estoque != null && NM_Estoque.length () > 0) {
      ed.setNm_estoque (NM_Estoque);
    }

    return new EstoqueRN ().Lista (ed);

  }

  public EstoqueED getByOid (String oid_Estoque) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }

    return new EstoqueRN ().getByRecord (ed);

  }

  public ArrayList Lista (HttpServletRequest request) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }
    String oid_Grupo_Estoque = request.getParameter ("oid_Grupo_Estoque");
    if (oid_Grupo_Estoque != null && oid_Grupo_Estoque.length () > 0) {
      int b = (new Integer (oid_Grupo_Estoque)).intValue ();
      ed.setOid_grupo_estoque (b);
    }
    String oid_Subgrupo_Estoque = request.getParameter ("oid_Subgrupo_Estoque");
    if (oid_Subgrupo_Estoque != null && oid_Subgrupo_Estoque.length () > 0) {
      int b = (new Integer (oid_Subgrupo_Estoque)).intValue ();
      ed.setOid_sub_grupo_estoque (b);
    }
    String oid_Produto = request.getParameter ("oid_Produto");
    if (oid_Produto != null && oid_Produto.length () > 0) {
      long b = (new Long (oid_Produto)).longValue ();
      ed.setOid_produto (b);
    }
    String oid_Unidade = request.getParameter ("oid_Unidade_Produto");
    if (oid_Unidade != null && oid_Unidade.length () > 0) {
      long b = (new Long (oid_Unidade)).longValue ();
      ed.setOid_unidade_produto (b);
    }
    String CD_Estoque = request.getParameter ("FT_CD_Estoque");
    if (CD_Estoque != null && CD_Estoque.length () > 0) {
      ed.setCd_estoque (CD_Estoque);
    }
    String NM_Estoque = request.getParameter ("FT_NM_Estoque");
    if (NM_Estoque != null && NM_Estoque.length () > 0) {
      ed.setNm_estoque (NM_Estoque);
    }
    String NM_Referencia_Fabrica = request.getParameter ("FT_NM_Referencia_Fabrica");
    if (NM_Referencia_Fabrica != null && NM_Referencia_Fabrica.length () > 0) {
      ed.setNm_referencia (NM_Referencia_Fabrica);
    }
    String DM_Negativo = request.getParameter ("FT_DM_Negativo");
    if (DM_Negativo != null && DM_Negativo.length () > 0) {
      ed.setDm_negativo (DM_Negativo);
    }
    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status (DM_Status);
    }
    String DM_Tipo_Produto = request.getParameter ("FT_DM_Tipo_Produto");
    if (DM_Tipo_Produto != null && DM_Tipo_Produto.length () > 0) {
      ed.setDm_tipo_produto (DM_Tipo_Produto);
    }

    return new EstoqueRN ().Lista (ed);

  }

  public EstoqueED getCodigo_Item (HttpServletRequest request) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String oid_Grupo_Estoque = request.getParameter ("oid_Grupo_Estoque");
    if (oid_Grupo_Estoque != null && oid_Grupo_Estoque.length () > 0) {
      int b = (new Integer (oid_Grupo_Estoque)).intValue ();
      ed.setOid_grupo_estoque (b);
    }
    String oid_Subgrupo_Estoque = request.getParameter ("oid_Subgrupo_Estoque");
    if (oid_Subgrupo_Estoque != null && oid_Subgrupo_Estoque.length () > 0) {
      int b = (new Integer (oid_Subgrupo_Estoque)).intValue ();
      ed.setOid_sub_grupo_estoque (b);
    }

    return new EstoqueRN ().getCodigo_Item (ed);

  }

  public EstoqueED inclui_itemXfornecedor (HttpServletRequest request) throws Excecoes {

    try {
      EstoqueRN EstoqueRN = new EstoqueRN ();
      EstoqueED ed = new EstoqueED ();

      String oid_Estoque = request.getParameter ("oid_Estoque");
      if (oid_Estoque != null && oid_Estoque.length () > 0) {
        long b = (new Long (oid_Estoque)).longValue ();
        ed.setOid_estoque (b);
      }
      String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
      if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
        ed.setOid_fornecedor (oid_Fornecedor);
      }
      ed.setDt_stamp (Data.getDataDMY ());

      String dm_preferencial = request.getParameter ("FT_DM_Preferencial");
      if (dm_preferencial != null && dm_preferencial.length () > 0) {
        ed.setDm_preferencial (dm_preferencial);
      }

      String vl_lista = request.getParameter ("FT_VL_Lista");
      if (vl_lista != null && vl_lista.length () > 0) {
        ed.setVl_lista_preco (new Double (vl_lista).doubleValue ());
      }

      return EstoqueRN.inclui_itemXfornecedor (ed);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void altera_itemXfornecedor (HttpServletRequest request) throws Excecoes {

    try {
      EstoqueRN EstoqueRN = new EstoqueRN ();
      EstoqueED ed = new EstoqueED ();

      String oid_Estoque = request.getParameter ("oid_Estoque");
      if (oid_Estoque != null && oid_Estoque.length () > 0) {
        long b = (new Long (oid_Estoque)).longValue ();
        ed.setOid_estoque (b);
      }
      String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
      if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
        ed.setOid_fornecedor (oid_Fornecedor);
      }
      ed.setDt_stamp (Data.getDataDMY ());

      String dm_preferencial = request.getParameter ("FT_DM_Preferencial");
      if (dm_preferencial != null && dm_preferencial.length () > 0) {
        ed.setDm_preferencial (dm_preferencial);
      }

      String oid_item_fornecedor = request.getParameter ("oid_item_fornecedor");
      if (oid_item_fornecedor != null && oid_item_fornecedor.length () > 0) {
        long b = (new Long (oid_item_fornecedor)).longValue ();
        ed.setOid_item_fornecedor (b);
      }

      String vl_lista = request.getParameter ("FT_VL_Lista");
      if (vl_lista != null && vl_lista.length () > 0) {
        ed.setVl_lista_preco (new Double (vl_lista).doubleValue ());
      }

      EstoqueRN.altera_itemXfornecedor (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void deleta_itemXfornecedor (HttpServletRequest request) throws Excecoes {

    try {
      EstoqueRN EstoqueRN = new EstoqueRN ();
      EstoqueED ed = new EstoqueED ();

      String oid_item_fornecedor = request.getParameter ("oid_item_fornecedor");
      if (oid_item_fornecedor != null && oid_item_fornecedor.length () > 0) {
        long b = (new Long (oid_item_fornecedor)).longValue ();
        ed.setOid_item_fornecedor (b);
      }

      EstoqueRN.deleta_itemXfornecedor (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public EstoqueED getByRecord_itemXfornecedor (HttpServletRequest request) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }
    String oid_item_fornecedor = request.getParameter ("oid_item_fornecedor");
    if (oid_item_fornecedor != null && oid_item_fornecedor.length () > 0) {
      long b = (new Long (oid_item_fornecedor)).longValue ();
      ed.setOid_item_fornecedor (b);
    }
    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
      ed.setOid_fornecedor (oid_Fornecedor);
    }

    return new EstoqueRN ().getByRecord_itemXfornecedor (ed);

  }

  public ArrayList Lista_itemXfornecedor (HttpServletRequest request) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }
    String oid_item_fornecedor = request.getParameter ("oid_item_fornecedor");
    if (oid_item_fornecedor != null && oid_item_fornecedor.length () > 0) {
      long b = (new Long (oid_item_fornecedor)).longValue ();
      ed.setOid_item_fornecedor (b);
    }
    String oid_Fornecedor = request.getParameter ("oid_Fornecedor");
    if (oid_Fornecedor != null && oid_Fornecedor.length () > 0) {
      ed.setOid_fornecedor (oid_Fornecedor);
    }

    String dm_preferencial = request.getParameter ("FT_DM_Preferencial");
    if (dm_preferencial != null && dm_preferencial.length () > 0) {
      ed.setDm_preferencial (dm_preferencial);
    }

    return new EstoqueRN ().Lista_itemXfornecedor (ed);

  }

  public ArrayList getByNM_EstoqueWMS (String nome) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String NM_Estoque = nome;
    if (NM_Estoque != null && NM_Estoque.length () > 0) {
      ed.setNm_estoque (NM_Estoque);
    }

    return new EstoqueRN ().ListaWMS (ed);

  }

  public EstoqueED getEstoque (String oid_Estoque) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }

    return new EstoqueRN ().getEstoque (ed);

  }

  public ArrayList Lista_Movimentacao_item (HttpServletRequest request , String tipo) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }

    return new EstoqueRN ().Lista_Movimentacao_item (ed , tipo);

  }

  public ArrayList Analise_de_Compras (HttpServletRequest request) throws Excecoes {

    EstoqueED ed = new EstoqueED ();

    String oid_Estoque = request.getParameter ("oid_Estoque");
    if (oid_Estoque != null && oid_Estoque.length () > 0) {
      long b = (new Long (oid_Estoque)).longValue ();
      ed.setOid_estoque (b);
    }
    String oid_Grupo_Estoque = request.getParameter ("oid_Grupo_Estoque");
    if (oid_Grupo_Estoque != null && oid_Grupo_Estoque.length () > 0) {
      int b = (new Integer (oid_Grupo_Estoque)).intValue ();
      ed.setOid_grupo_estoque (b);
    }
    String oid_Subgrupo_Estoque = request.getParameter ("oid_Subgrupo_Estoque");
    if (oid_Subgrupo_Estoque != null && oid_Subgrupo_Estoque.length () > 0) {
      int b = (new Integer (oid_Subgrupo_Estoque)).intValue ();
      ed.setOid_sub_grupo_estoque (b);
    }
    String oid_Produto = request.getParameter ("oid_Produto");
    if (oid_Produto != null && oid_Produto.length () > 0) {
      long b = (new Long (oid_Produto)).longValue ();
      ed.setOid_produto (b);
    }
    String oid_Unidade = request.getParameter ("oid_Unidade_Produto");
    if (oid_Unidade != null && oid_Unidade.length () > 0) {
      long b = (new Long (oid_Unidade)).longValue ();
      ed.setOid_unidade_produto (b);
    }
    String CD_Estoque = request.getParameter ("FT_CD_Estoque");
    if (CD_Estoque != null && CD_Estoque.length () > 0) {
      ed.setCd_estoque (CD_Estoque);
    }
    String NM_Estoque = request.getParameter ("FT_NM_Estoque");
    if (NM_Estoque != null && NM_Estoque.length () > 0) {
      ed.setNm_estoque (NM_Estoque);
    }
    String NM_Referencia_Fabrica = request.getParameter ("FT_NM_Referencia_Fabrica");
    if (NM_Referencia_Fabrica != null && NM_Referencia_Fabrica.length () > 0) {
      ed.setNm_referencia (NM_Referencia_Fabrica);
    }
    String DM_Negativo = request.getParameter ("FT_DM_Negativo");
    if (DM_Negativo != null && DM_Negativo.length () > 0) {
      ed.setDm_negativo (DM_Negativo);
    }
    String DM_Status = request.getParameter ("FT_DM_Status");
    if (DM_Status != null && DM_Status.length () > 0) {
      ed.setDm_status (DM_Status);
    }
    String DM_Tipo_Produto = request.getParameter ("FT_DM_Tipo_Produto");
    if (DM_Tipo_Produto != null && DM_Tipo_Produto.length () > 0) {
      ed.setDm_tipo_produto (DM_Tipo_Produto);
    }

    return new EstoqueRN ().Analise_de_Compras (ed);

  }

}
