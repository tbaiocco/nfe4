package com.master.iu;

/**
 * Título: wms014Bean
 * Descrição: Movimentos - Bean
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_Movimentos_ProdutosED;
import com.master.rn.WMS_Movimentos_ProdutosRN;
import com.master.util.Excecoes;

public class wms014Bean {

  private ArrayList lista = null;

  public WMS_Movimentos_ProdutosED inclui(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Movimentos_ProdutosRN WMS_Movimentos_ProdutosRN = new WMS_Movimentos_ProdutosRN();
      WMS_Movimentos_ProdutosED ed = new WMS_Movimentos_ProdutosED();

      ed.setOID_Produto_Cliente( request.getParameter("FT_OID_Produto") + request.getParameter("oid_Pessoa") );
      ed.setOID_Requisicao_Produto( Integer.valueOf( request.getParameter("FT_OID_Requisicao_Produto") ).intValue() );
      ed.setOID_Operador( Integer.valueOf( request.getParameter("FT_OID_Operador") ).intValue() );
      ed.setNR_Quantidade_Requerida( Integer.valueOf( request.getParameter("FT_NR_Quantidade_Requerida") ).intValue() );
      ed.setDT_Movimentacao( request.getParameter("FT_DT_Movimentacao") );
      ed.setHR_Movimentacao( request.getParameter("FT_HR_Movimentacao") );
      ed.setDM_Tipo_Movimento( request.getParameter("FT_DM_Tipo_Movimento") );

      return WMS_Movimentos_ProdutosRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Movimentos_ProdutosRN WMS_Movimentos_ProdutosRN = new WMS_Movimentos_ProdutosRN();
      WMS_Movimentos_ProdutosED ed = new WMS_Movimentos_ProdutosED();

      ed.setOID_Produto_Cliente( request.getParameter("FT_OID_Produto") + request.getParameter("oid_Pessoa") );
      ed.setOID_Requisicao_Produto( Integer.valueOf( request.getParameter("FT_OID_Requisicao_Produto") ).intValue() );
      ed.setOID_Operador( Integer.valueOf( request.getParameter("FT_OID_Operador") ).intValue() );
      ed.setNR_Quantidade_Requerida( Integer.valueOf( request.getParameter("FT_NR_Quantidade_Requerida") ).intValue() );
      if( request.getAttribute( "FT_NR_Quantidade_Efetiva" ) != null ){
        ed.setNR_Quantidade_Efetiva( Integer.valueOf( String.valueOf( request.getAttribute("FT_NR_Quantidade_Efetiva") ) ).intValue() );
      }else
        ed.setNR_Quantidade_Efetiva( Integer.valueOf( request.getParameter("FT_NR_Quantidade_Efetiva") ).intValue() );
      ed.setDT_Movimentacao( request.getParameter("FT_DT_Movimentacao") );
      ed.setHR_Movimentacao( request.getParameter("FT_HR_Movimentacao") );
      ed.setDM_Tipo_Movimento( request.getParameter("FT_DM_Tipo_Movimento") );
      ed.setOID_Movimento_Produto( Integer.valueOf( request.getParameter("FT_OID_Movimento_Produto") ).intValue() );

      WMS_Movimentos_ProdutosRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Movimentos_ProdutosRN WMS_Movimentos_Produtosrn = new WMS_Movimentos_ProdutosRN();
      WMS_Movimentos_ProdutosED ed = new WMS_Movimentos_ProdutosED();

      ed.setOID_Movimento_Produto( Integer.valueOf( request.getParameter("FT_OID_Movimento_Produto") ).intValue() );

      WMS_Movimentos_Produtosrn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public WMS_Movimentos_ProdutosED getByRecord(HttpServletRequest request)throws Excecoes{

      WMS_Movimentos_ProdutosED ed = new WMS_Movimentos_ProdutosED();

      String OID_Movimento_Produto = request.getParameter("FT_OID_Movimento_Produto");

      if (OID_Movimento_Produto != null && !OID_Movimento_Produto.equals("0")){
         ed.setOID_Movimento_Produto( Integer.valueOf( OID_Movimento_Produto ).intValue() );
      }


     return new WMS_Movimentos_ProdutosRN().getByRecord(ed);

  }


  public ArrayList lista(HttpServletRequest request, String orderby)throws Excecoes{

      WMS_Movimentos_ProdutosED ed = new WMS_Movimentos_ProdutosED();

      if( request.getParameter( "FT_OID_Requisicao_Produto" ) != null && !request.getParameter( "FT_OID_Requisicao_Produto" ).equals( "" ) )
         ed.setOID_Requisicao_Produto( Integer.valueOf( request.getParameter( "FT_OID_Requisicao_Produto" ) ).intValue() );

      try{
          WMS_Movimentos_ProdutosRN RN = new WMS_Movimentos_ProdutosRN();
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
