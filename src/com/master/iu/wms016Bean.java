package com.master.iu;

/**
 * Título: wms016Bean
 * Descrição: Tipos de Pallet - Bean
 * Data da criação: 02/2004
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_Tipo_PalletED;
import com.master.rn.WMS_Tipo_PalletRN;
import com.master.util.Excecoes;

public class wms016Bean {

  private ArrayList lista = null;

  public WMS_Tipo_PalletED inclui(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Tipo_PalletRN WMS_Tipo_PalletRN = new WMS_Tipo_PalletRN();
      WMS_Tipo_PalletED ed = new WMS_Tipo_PalletED();

        ed.setNm_Descricao(request.getParameter("FT_NM_Descricao"));
        ed.setNm_Material(request.getParameter("FT_NM_Material"));
	// System.out.println("tipo pallet 1" );
        ed.setNr_Largura(new Double(request.getParameter("FT_NR_Largura")).doubleValue());
        ed.setNr_Profundidade(new Double(request.getParameter("FT_NR_Profundidade")).doubleValue());
	// System.out.println("tipo pallet 2" );

      return WMS_Tipo_PalletRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Tipo_PalletRN WMS_Tipo_PalletRN = new WMS_Tipo_PalletRN();
      WMS_Tipo_PalletED ed = new WMS_Tipo_PalletED();

        ed.setOid_Tipo_Pallet(new Integer(request.getParameter("FT_OID_Tipo_Pallet")).intValue());
        ed.setNm_Descricao(request.getParameter("FT_NM_Descricao"));
        ed.setNm_Material(request.getParameter("FT_NM_Material"));
        ed.setNr_Largura(new Double(request.getParameter("FT_NR_Largura")).doubleValue());
        ed.setNr_Profundidade(new Double(request.getParameter("FT_NR_Profundidade")).doubleValue());

      WMS_Tipo_PalletRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Tipo_PalletRN WMS_Tipo_Palletrn = new WMS_Tipo_PalletRN();
      WMS_Tipo_PalletED ed = new WMS_Tipo_PalletED();

      ed.setOid_Tipo_Pallet(new Integer(request.getParameter("FT_OID_Tipo_Pallet")).intValue());

      WMS_Tipo_Palletrn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public WMS_Tipo_PalletED getByRecord(HttpServletRequest request)throws Excecoes{

      WMS_Tipo_PalletED ed = new WMS_Tipo_PalletED();

      String oid_Tipo_Pallet = request.getParameter("FT_OID_Tipo_Pallet");

      if (oid_Tipo_Pallet != null && oid_Tipo_Pallet.length() != 0 ){
        ed.setOid_Tipo_Pallet(new Integer(oid_Tipo_Pallet).intValue());

      }

     return new WMS_Tipo_PalletRN().getByRecord(ed);

  }

  public WMS_Tipo_PalletED getByOid(String oid_Tipo_Pallet)throws Excecoes{
     return new WMS_Tipo_PalletRN().getByOid(oid_Tipo_Pallet);
  }

  public ArrayList getAll()throws Excecoes{
     return new WMS_Tipo_PalletRN().getAll();
  }


  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    WMS_Tipo_PalletED ed = new WMS_Tipo_PalletED();

//    ed.setCd_Embalagem(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    WMS_Tipo_PalletRN geRN = new WMS_Tipo_PalletRN();
    geRN.geraRelatorio(ed);
  }

  public ArrayList lista(HttpServletRequest request, String orderby)throws Excecoes{
      WMS_Tipo_PalletED ed = new WMS_Tipo_PalletED();
      if(!request.getParameter("FT_OID_Tipo_Pallet").equals(""))
         ed.setOid_Tipo_Pallet(new Integer(request.getParameter("FT_OID_Tipo_Pallet")).intValue());
      if(!request.getParameter("FT_NM_Descricao").equals(""))
         ed.setNm_Descricao(request.getParameter("FT_NM_Descricao"));
      if(!request.getParameter("FT_NM_Material").equals(""))
        ed.setNm_Material(request.getParameter("FT_NM_Material"));
     if(!request.getParameter("FT_NR_Largura").equals(""))
       ed.setNr_Largura(new Integer(request.getParameter("FT_NR_Largura")).intValue());
      if(!request.getParameter("FT_NR_Profundidade").equals(""))
       ed.setNr_Profundidade(new Integer(request.getParameter("FT_NR_Profundidade")).intValue());

     try{
          WMS_Tipo_PalletRN RN = new WMS_Tipo_PalletRN();
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
