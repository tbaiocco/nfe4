package com.master.iu;

/**
 * Título: wms015Bean
 * Descrição: Series - Bean
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_Series_ProdutosED;
import com.master.rn.WMS_Series_ProdutosRN;
import com.master.util.Excecoes;

public class wms015Bean {

  private ArrayList lista = null;

  public WMS_Series_ProdutosED inclui(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Series_ProdutosRN WMS_Series_ProdutosRN = new WMS_Series_ProdutosRN();
      WMS_Series_ProdutosED ed = new WMS_Series_ProdutosED();

      ed.setOID_Movimento_Produto( Integer.valueOf( request.getParameter("FT_OID_Movimento_Produto") ).intValue() );
      ed.setNR_Fabrica( request.getParameter("FT_NR_Fabrica") );

      return WMS_Series_ProdutosRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Series_ProdutosRN WMS_Series_ProdutosRN = new WMS_Series_ProdutosRN();
      WMS_Series_ProdutosED ed = new WMS_Series_ProdutosED();

      ed.setOID_Serie_Produto( Integer.valueOf( request.getParameter("FT_OID_Serie_Produto") ).intValue() );
      ed.setOID_Movimento_Produto( Integer.valueOf( request.getParameter("FT_OID_Movimento_Produto") ).intValue() );
      ed.setNR_Fabrica( request.getParameter("FT_NR_Fabrica") );

      WMS_Series_ProdutosRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Series_ProdutosRN WMS_Series_Produtosrn = new WMS_Series_ProdutosRN();
      WMS_Series_ProdutosED ed = new WMS_Series_ProdutosED();

      ed.setOID_Serie_Produto( Integer.valueOf( request.getParameter("FT_OID_Serie_Produto") ).intValue() );

      WMS_Series_Produtosrn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public WMS_Series_ProdutosED getByRecord(HttpServletRequest request)throws Excecoes{

      WMS_Series_ProdutosED ed = new WMS_Series_ProdutosED();

      String OID_Serie_Produto = request.getParameter("FT_OID_Serie_Produto");

      if (OID_Serie_Produto != null && !OID_Serie_Produto.equals("0")){
         ed.setOID_Serie_Produto( Integer.valueOf( OID_Serie_Produto ).intValue() );
      }


     return new WMS_Series_ProdutosRN().getByRecord(ed);

  }

  public int contByOid_Movimento_Produto(int oid_movimento_produto)throws Excecoes{
     return new WMS_Series_ProdutosRN().contByOid_Movimento_Produto(oid_movimento_produto);
  }

  public ArrayList lista(HttpServletRequest request)throws Excecoes{

      WMS_Series_ProdutosED ed = new WMS_Series_ProdutosED();

      if( request.getParameter( "FT_OID_Movimento_Produto" ) != null && !request.getParameter( "FT_OID_Movimento_Produto" ).equals( "" ) )
         ed.setOID_Movimento_Produto( Integer.valueOf( request.getParameter( "FT_OID_Movimento_Produto" ) ).intValue() );

      try{
          WMS_Series_ProdutosRN RN = new WMS_Series_ProdutosRN();
          this.setLista( RN.lista( ed ) );
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
