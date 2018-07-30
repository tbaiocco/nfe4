package com.master.iu;

/**
 * Título: wms013Bean
 * Descrição: Requisições - Bean
 * Data da criação: 12/2003
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_Requisicoes_ProdutosED;
import com.master.rn.WMS_Requisicoes_ProdutosRN;
import com.master.util.Excecoes;

public class wms013Bean {

  private ArrayList lista = null;

  public WMS_Requisicoes_ProdutosED isExisteRequisicao( String oid_nota_fiscal_transacao ) throws Excecoes{
    try{
      WMS_Requisicoes_ProdutosRN WMS_Requisicoes_ProdutosRN = new WMS_Requisicoes_ProdutosRN();
      return WMS_Requisicoes_ProdutosRN.isExisteRequisicao( oid_nota_fiscal_transacao );
    }
    catch (Excecoes exc){
      throw exc;
    }
  }
  
  public WMS_Requisicoes_ProdutosED isExisteRequisicaoAberta( String oid_nota_fiscal_transacao ) throws Excecoes{
    try{
      WMS_Requisicoes_ProdutosRN WMS_Requisicoes_ProdutosRN = new WMS_Requisicoes_ProdutosRN();
      return WMS_Requisicoes_ProdutosRN.isExisteRequisicaoAberta( oid_nota_fiscal_transacao );
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public WMS_Requisicoes_ProdutosED inclui(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Requisicoes_ProdutosRN WMS_Requisicoes_ProdutosRN = new WMS_Requisicoes_ProdutosRN();
      WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();

      ed.setOID_Deposito( Integer.valueOf( request.getParameter("FT_OID_Deposito") ).intValue() );
      ed.setOID_Pessoa( request.getParameter("oid_Pessoa_Remetente") );
      ed.setOID_Pessoa_Destinatario( request.getParameter("oid_Pessoa_Destinatario") );
      ed.setOID_Pessoa_Transportador( request.getParameter("oid_Pessoa_Transportador") );
      ed.setOID_Tipo_Movimento_Produto( Integer.valueOf( request.getParameter("FT_OID_Tipo_Movimento_Produto") ).intValue() );
      ed.setOID_Nota_Fiscal_Transacao( request.getParameter("FT_OID_Nota_Fiscal") );
      ed.setDT_Requisicao( request.getParameter("FT_DT_Requisicao") );
      ed.setDT_Conclusao( request.getParameter("FT_DT_Conclusao") );
      ed.setHR_Requisicao( request.getParameter("FT_HR_Requisicao") );
      ed.setHR_Conclusao( request.getParameter("FT_HR_Conclusao") );

      return WMS_Requisicoes_ProdutosRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Requisicoes_ProdutosRN WMS_Requisicoes_ProdutosRN = new WMS_Requisicoes_ProdutosRN();
      WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();

      ed.setOID_Deposito( Integer.valueOf( request.getParameter("FT_OID_Deposito") ).intValue() );
      ed.setOID_Pessoa( request.getParameter("oid_Pessoa_Remetente") );
      ed.setOID_Pessoa_Destinatario( request.getParameter("oid_Pessoa_Destinatario") );
      ed.setOID_Pessoa_Transportador( request.getParameter("oid_Pessoa_Transportador") );
      ed.setOID_Tipo_Movimento_Produto( Integer.valueOf( request.getParameter("FT_OID_Tipo_Movimento_Produto") ).intValue() );
      ed.setOID_Nota_Fiscal_Transacao( request.getParameter("oid_Nota_Fiscal") );
      ed.setDT_Requisicao( request.getParameter("FT_DT_Requisicao") );
      ed.setDT_Conclusao( request.getParameter("FT_DT_Conclusao") );
      ed.setHR_Requisicao( request.getParameter("FT_HR_Requisicao") );
      ed.setHR_Conclusao( request.getParameter("FT_HR_Conclusao") );
      ed.setDM_Situacao( request.getParameter("FT_DM_Situacao") );
      ed.setOID_Requisicao_Produto( Integer.valueOf( request.getParameter("FT_OID_Requisicao_Produto") ).intValue() );

      WMS_Requisicoes_ProdutosRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Requisicoes_ProdutosRN WMS_Requisicoes_Produtosrn = new WMS_Requisicoes_ProdutosRN();
      WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();

      ed.setOID_Requisicao_Produto( Integer.valueOf( request.getParameter("FT_OID_Requisicao_Produto") ).intValue() );

      WMS_Requisicoes_Produtosrn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public WMS_Requisicoes_ProdutosED getByRecord(HttpServletRequest request)throws Excecoes{

      WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();

      String OID_Requisicao_Produto = request.getParameter("FT_OID_Requisicao_Produto");

      if (OID_Requisicao_Produto != null && !OID_Requisicao_Produto.equals("0")){
         ed.setOID_Requisicao_Produto( Integer.valueOf( OID_Requisicao_Produto ).intValue() );
      }


     return new WMS_Requisicoes_ProdutosRN().getByRecord(ed);

  }

  public ArrayList lista(HttpServletRequest request, String orderby)throws Excecoes{

      WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();

      if( request.getParameter( "FT_OID_Deposito" ) != null && !request.getParameter( "FT_OID_Deposito" ).equals( "" ) )
         ed.setOID_Deposito( Integer.valueOf( request.getParameter( "FT_OID_Deposito" ) ).intValue() );

      if( request.getParameter( "oid_Pessoa_Remetente" ) != null && !request.getParameter( "oid_Pessoa_Remetente" ).equals( "" ) )
         ed.setOID_Pessoa( request.getParameter( "oid_Pessoa_Remetente" ) );

      if( request.getParameter( "oid_Pessoa_Destinatario" ) != null && !request.getParameter( "oid_Pessoa_Destinatario" ).equals( "" ) )
         ed.setOID_Pessoa_Destinatario( request.getParameter( "oid_Pessoa_Destinatario" ) );

      if( request.getParameter( "oid_Pessoa_Transportador" ) != null && !request.getParameter( "oid_Pessoa_Transportador" ).equals( "" ) )
         ed.setOID_Pessoa_Transportador( request.getParameter( "oid_Pessoa_Transportador" ) );

      if( request.getParameter( "FT_OID_Nota_Fiscal" ) != null && !request.getParameter( "FT_OID_Nota_Fiscal" ).equals( "" ) )
         ed.setOID_Nota_Fiscal_Transacao( request.getParameter( "FT_OID_Nota_Fiscal" ) );

      if( request.getParameter( "FT_OID_Tipo_Movimento_Produto" ) != null && !request.getParameter( "FT_OID_Tipo_Movimento_Produto" ).equals( "" ) )
         ed.setOID_Tipo_Movimento_Produto( Integer.valueOf( request.getParameter( "FT_OID_Tipo_Movimento_Produto" ) ).intValue() );

      if( request.getParameter( "FT_DM_Nota_Fiscal" ) != null && !request.getParameter( "FT_DM_Nota_Fiscal" ).equals( "" ) )
         ed.setDM_Nota_Fiscal( request.getParameter( "FT_DM_Nota_Fiscal" ) );

      if( request.getParameter( "FT_DM_Situacao" ) != null && !request.getParameter( "FT_DM_Situacao" ).equals( "" ) )
         ed.setDM_Situacao( request.getParameter( "FT_DM_Situacao" ) );

      if( request.getParameter( "FT_DT_Conclusao" ) != null && !request.getParameter( "FT_DT_Conclusao" ).equals( "" ) )
         ed.setDT_Conclusao( request.getParameter( "FT_DT_Conclusao" ) );

      if( request.getParameter( "FT_HR_Conclusao" ) != null && !request.getParameter( "FT_HR_Conclusao" ).equals( "" ) )
         ed.setHR_Conclusao( request.getParameter( "FT_HR_Conclusao" ) );

      if( request.getParameter( "FT_DT_Requisicao" ) != null && !request.getParameter( "FT_DT_Requisicao" ).equals( "" ) )
         ed.setDT_Requisicao( request.getParameter( "FT_DT_Requisicao" ) );

      if( request.getParameter( "FT_HR_Requisicao" ) != null && !request.getParameter( "FT_HR_Requisicao" ).equals( "" ) )
         ed.setHR_Requisicao( request.getParameter( "FT_HR_Requisicao" ) );
      
      if( request.getParameter( "oid_Unidade" ) != null && !request.getParameter( "oid_Unidade" ).equals( "" ) )
         ed.setOID_Unidade( Long.valueOf( request.getParameter( "oid_Unidade" ) ).longValue() );

      try{
          WMS_Requisicoes_ProdutosRN RN = new WMS_Requisicoes_ProdutosRN();
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

  public boolean isFinalizada( int oid_requisicao_produto )throws Excecoes{

    try{
      WMS_Requisicoes_ProdutosRN WMS_Requisicoes_Produtosrn = new WMS_Requisicoes_ProdutosRN();

      return WMS_Requisicoes_Produtosrn.isFinalizada( oid_requisicao_produto );
    }
    catch (Excecoes exc){
      throw exc;
    }
  }
  public void alteraSituacao( int oid_requisicao_produto, String dm_situacao ) throws Excecoes{

    try{
      WMS_Requisicoes_ProdutosRN WMS_Requisicoes_Produtosrn = new WMS_Requisicoes_ProdutosRN();
      WMS_Requisicoes_Produtosrn.alteraSituacao( oid_requisicao_produto, dm_situacao );
    }
    catch (Excecoes exc){
      throw exc;
    }
  }
}
