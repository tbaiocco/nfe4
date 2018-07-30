package com.master.iu;

/**
 * Título: wms004Bean
 * Descrição: Embalagens - Bean
 * Data da criação: 10/2003
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_EmbalagemED;
import com.master.rn.WMS_EmbalagemRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class wms004Bean {

  private ArrayList lista = null;
	Utilitaria util = new Utilitaria ();

  public WMS_EmbalagemED inclui(HttpServletRequest request)throws Excecoes{

    try{
      WMS_EmbalagemRN WMS_EmbalagemRN = new WMS_EmbalagemRN();
      WMS_EmbalagemED ed = new WMS_EmbalagemED();

        ed.setNm_Tipo(request.getParameter("FT_NM_Tipo"));
        ed.setNm_Descricao(request.getParameter("FT_NM_Descricao"));
        ed.setNm_Material(request.getParameter("FT_NM_Material"));

		String NR_Peso = request.getParameter ("FT_NR_Peso_Bruto");
		if (util.doValida (NR_Peso)) {
			ed.setNr_Peso_Bruto(new Double (NR_Peso).doubleValue ());
		}
		NR_Peso = request.getParameter ("FT_NR_Peso_Liquido");
		if (util.doValida (NR_Peso)) {
			ed.setNr_Peso_Liquido(new Double (NR_Peso).doubleValue ());
		}
        
      return WMS_EmbalagemRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      WMS_EmbalagemRN WMS_EmbalagemRN = new WMS_EmbalagemRN();
      WMS_EmbalagemED ed = new WMS_EmbalagemED();

        ed.setOid_Embalagem(new Integer(request.getParameter("FT_OID_Embalagem")).intValue());
        ed.setNm_Tipo(request.getParameter("FT_NM_Tipo"));
        ed.setNm_Descricao(request.getParameter("FT_NM_Descricao"));
        ed.setNm_Material(request.getParameter("FT_NM_Material"));
		String NR_Peso = request.getParameter ("FT_NR_Peso_Bruto");
		if (util.doValida (NR_Peso)) {
			ed.setNr_Peso_Bruto(new Double (NR_Peso).doubleValue ());
		}
		NR_Peso = request.getParameter ("FT_NR_Peso_Liquido");
		if (util.doValida (NR_Peso)) {
			ed.setNr_Peso_Liquido(new Double (NR_Peso).doubleValue ());
		}

      WMS_EmbalagemRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      WMS_EmbalagemRN WMS_Embalagemrn = new WMS_EmbalagemRN();
      WMS_EmbalagemED ed = new WMS_EmbalagemED();

      ed.setOid_Embalagem(new Integer(request.getParameter("FT_OID_Embalagem")).intValue());

      WMS_Embalagemrn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public WMS_EmbalagemED getByRecord(HttpServletRequest request)throws Excecoes{

      WMS_EmbalagemED ed = new WMS_EmbalagemED();

      String oid_Embalagem = request.getParameter("FT_OID_Embalagem");

      if (oid_Embalagem != null && oid_Embalagem.length() != 0 ){
        ed.setOid_Embalagem(new Integer(oid_Embalagem).intValue());

      }

     return new WMS_EmbalagemRN().getByRecord(ed);

  }

  public ArrayList getAll()throws Excecoes{
     return new WMS_EmbalagemRN().getAll();
  }

  public WMS_EmbalagemED getByOid(String oid_embalagem)throws Excecoes{
     return new WMS_EmbalagemRN().getByOid(oid_embalagem);
  }

  public ArrayList lista(HttpServletRequest request, String orderby)throws Excecoes{
      
      WMS_EmbalagemED ed = new WMS_EmbalagemED();
      
      String OID_Embalagem = request.getParameter("FT_OID_Embalagem");
      String NM_Descricao = request.getParameter("FT_NM_Descricao");
      String NM_Material = request.getParameter("FT_NM_Material");
      String NM_Tipo = request.getParameter("FT_NM_Tipo");
      
      if(OID_Embalagem != null && OID_Embalagem.trim().length() > 0)
          ed.setOid_Embalagem(new Integer(OID_Embalagem).intValue());
      if(NM_Descricao != null && NM_Descricao.trim().length() > 0)
          ed.setNm_Descricao(NM_Descricao);
      if(NM_Material != null && NM_Material.trim().length() > 0)
          ed.setNm_Material(NM_Material);
      if(NM_Tipo != null && NM_Tipo.trim().length() > 0)
          ed.setNm_Tipo(NM_Tipo);

      try{
          WMS_EmbalagemRN RN = new WMS_EmbalagemRN();
          this.setLista( RN.lista( ed, orderby ) );
      }catch( Exception ex ){}

    return this.getLista();
  }

  public void setLista( ArrayList array ){
    this.lista = array;
  }

  public ArrayList getLista(){
    return this.lista;
  }
}
