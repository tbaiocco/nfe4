package com.master.iu;

/**
 * Título: est022Bean
 * Descrição: Requisição de Materiais - Bean
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import javax.servlet.http.*;
import com.master.rn.Requisicao_CompraRN;
import com.master.ed.Requisicao_CompraED;
import com.master.util.Excecoes;
import com.master.util.Data;
import java.util.*;

public class est022Bean {

  public Requisicao_CompraED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN ();
      Requisicao_CompraED ed = new Requisicao_CompraED ();

      String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
      if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
        ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
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
        ed.setDm_status_requisicao (DM_Status);
      }

      ed.setDt_requisicao (Data.getDataDMY ());
      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Entrega = request.getParameter ("FT_NM_Entrega");
      if (NM_Entrega != null && NM_Entrega.length () > 0) {
        ed.setNm_entrega (NM_Entrega);
      }

      return Requisicao_CompraRN.inclui (ed);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN ();
      Requisicao_CompraED ed = new Requisicao_CompraED ();

      String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
      if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
        ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
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
        ed.setDm_status_requisicao (DM_Status);
      }

      ed.setDt_stamp (Data.getDataDMY ());

      String NM_Entrega = request.getParameter ("FT_NM_Entrega");
      if (NM_Entrega != null && NM_Entrega.length () > 0) {
        ed.setNm_entrega (NM_Entrega);
      }

      Requisicao_CompraRN.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN ();
      Requisicao_CompraED ed = new Requisicao_CompraED ();

      String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
      if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
        ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
      }

      Requisicao_CompraRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void cancela (HttpServletRequest request) throws Excecoes {

    try {
      Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN ();
      Requisicao_CompraED ed = new Requisicao_CompraED ();

      String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
      if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
        ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
      }

      Requisicao_CompraRN.cancela (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void autoriza (HttpServletRequest request) throws Excecoes {

    try {
      Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN ();
      Requisicao_CompraED ed = new Requisicao_CompraED ();

      String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
      if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
        ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
      }
      String oid_Autorizador = request.getParameter ("oid_Autorizador");
      if (oid_Autorizador != null && oid_Autorizador.length () > 0) {
        ed.setOid_autorizador (Long.parseLong (oid_Autorizador));
      }

      Requisicao_CompraRN.autoriza (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public Requisicao_CompraED getByRecord (HttpServletRequest request) throws Excecoes {

    Requisicao_CompraED ed = new Requisicao_CompraED ();

    String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
    if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
      ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
    }

    return new Requisicao_CompraRN ().getByRecord (ed);

  }

  public ArrayList Lista (HttpServletRequest request) throws Excecoes {

    Requisicao_CompraED ed = new Requisicao_CompraED ();

    String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
    if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
      ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
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
      ed.setDm_status_requisicao (DM_Status);
    }
    String DT_Requisicao = request.getParameter ("FT_DT_Requisicao");
    if (DT_Requisicao != null && DT_Requisicao.length () > 0) {
      ed.setDt_requisicao (DT_Requisicao);
    }

    return new Requisicao_CompraRN ().Lista (ed);

  }

  public ArrayList Lista_Itens (HttpServletRequest request) throws Excecoes {

    Requisicao_CompraED ed = new Requisicao_CompraED ();

    String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
    if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
      ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
    }

    return new Requisicao_CompraRN ().Lista_Itens (ed);

  }

  public Requisicao_CompraED entregar (HttpServletRequest request) throws Excecoes {

    try {
      Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN ();
      Requisicao_CompraED ed = new Requisicao_CompraED ();

      String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
      if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
        ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
      }

      return Requisicao_CompraRN.entregar (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public void imprime_Protocolo (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    try {
      Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN ();
      Requisicao_CompraED ed = new Requisicao_CompraED ();

      String oid_Requisicao_Compra = request.getParameter ("oid_Requisicao_Compra");
      if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length () > 0) {
        ed.setOid_requisicao_compra (Long.parseLong (oid_Requisicao_Compra));
      }
      String NM_Usuario = request.getParameter ("FT_NM_Usuario");
      if (NM_Usuario != null && NM_Usuario.length () > 0) {
        ed.setNm_usuario (NM_Usuario);
      }
      String NM_Unidade = request.getParameter ("FT_NM_Unidade");
      if (NM_Unidade != null && NM_Unidade.length () > 0) {
        ed.setNm_unidade (NM_Unidade);
      }
      String Data = request.getParameter ("FT_DT_Requisicao");
      if (Data != null && Data.length () > 0) {
        ed.setDt_requisicao (Data);
      }
      String Centro_Custo = request.getParameter ("FT_NM_Centro_Custo");
      if (Centro_Custo != null && Centro_Custo.length () > 0) {
        ed.setCentro_custo (Centro_Custo);
      }
      // System.out.println (oid_Requisicao_Compra);
      // System.out.println (NM_Unidade + NM_Usuario + Data + Centro_Custo);

      Requisicao_CompraRN.imprime_Protocolo (ed , response);
    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

}
