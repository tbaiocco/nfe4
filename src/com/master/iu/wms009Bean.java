package com.master.iu;

/**
 * Título: wms009Bean
 * Descrição: Itens da Nota Fiscal - Bean
 * Data da criação: 11/2003
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.rn.WMS_Item_Produto_ClienteRN;
import com.master.util.Excecoes;

public class wms009Bean {
    
  private ArrayList lista = null;    

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Item_Produto_ClienteRN WMS_Item_Produto_ClienteRN = new WMS_Item_Produto_ClienteRN();
      WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();
      ed.setOid_Nota_Fiscal( request.getParameter("oid_Nota_Fiscal") );
      ed.setOid_Item_Produto_Cliente( request.getParameter("FT_OID_Produto") + request.getParameter("oid_Pessoa") );
      ed.setOid_Tipo_Estoque( new Integer( request.getParameter("FT_OID_Tipo_Estoque") ).intValue() );
      ed.setVl_Produto( new Double(request.getParameter("FT_VL_Produto")).doubleValue() );
      ed.setNr_Quantidade_Movimento( new Double( request.getParameter("FT_NR_Quantidade") ).doubleValue() );
      WMS_Item_Produto_ClienteRN.inclui(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Item_Produto_ClienteRN WMS_Item_Produto_ClienteRN = new WMS_Item_Produto_ClienteRN();
      WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();

      ed.setOid_Produto_Cliente( request.getParameter("oid_Item_Produto_Cliente") );
      ed.setOid_Nota_Fiscal( request.getParameter("oid_Nota_Fiscal") );
      ed.setOid_Produto_Cliente( request.getParameter("FT_OID_Produto") + request.getParameter("oid_Pessoa") );
      ed.setOid_Tipo_Estoque( new Integer( request.getParameter("FT_OID_Tipo_Estoque") ).intValue() );
      ed.setVl_Produto( new Double(request.getParameter("FT_VL_Produto")).doubleValue() );
      ed.setNr_Quantidade_Movimento( new Double( request.getParameter("FT_NR_Quantidade") ).doubleValue() );
      WMS_Item_Produto_ClienteRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void alteraPelaMovimentacao(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Item_Produto_ClienteRN WMS_Item_Produto_ClienteRN = new WMS_Item_Produto_ClienteRN();
      WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();

      ed.setOid_Item_Produto_Cliente( String.valueOf( request.getAttribute("oid_Item_Produto_Cliente") ) );
      ed.setNr_Quantidade_Devolucao( Integer.valueOf( String.valueOf( request.getAttribute( "FT_NR_Quantidade_Devolucao" ) ) ).intValue() );
      WMS_Item_Produto_ClienteRN.alteraPelaMovimentacao(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("alteraPelaMovimentacao");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Item_Produto_ClienteRN WMS_Item_Produto_ClienteRN = new WMS_Item_Produto_ClienteRN();
      WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();

      ed.setOid_Item_Produto_Cliente(request.getParameter("oid_Item_Produto_Cliente"));
      WMS_Item_Produto_ClienteRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public WMS_Item_Produto_ClienteED getByRecord(HttpServletRequest request)throws Excecoes{

      WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();

      String OID_Item_Produto_Cliente = request.getParameter("FT_OID_Item_Produto_Cliente");
      if (String.valueOf(OID_Item_Produto_Cliente) != null &&
          !String.valueOf(OID_Item_Produto_Cliente).equals("") &&
          !String.valueOf(OID_Item_Produto_Cliente).equals("null")){
        ed.setOid_Item_Produto_Cliente(request.getParameter("FT_OID_Item_Produto_Cliente"));
      }

      String OID_Nota_Fiscal = request.getParameter("FT_OID_Nota_Fiscal");
      if (String.valueOf(OID_Nota_Fiscal) != null &&
          !String.valueOf(OID_Nota_Fiscal).equals("") &&
          !String.valueOf(OID_Nota_Fiscal).equals("null")){
        ed.setOid_Nota_Fiscal(request.getParameter("FT_OID_Nota_Fiscal"));
      }

      return new WMS_Item_Produto_ClienteRN().getByRecord(ed);

  }

  public ArrayList lista(HttpServletRequest request, String orderby)throws Excecoes{
      WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();
           
     if(!request.getParameter("oid_Nota_Fiscal").equals(""))       
       ed.setOid_Nota_Fiscal( request.getParameter("oid_Nota_Fiscal"));

     try{
          WMS_Item_Produto_ClienteRN RN = new WMS_Item_Produto_ClienteRN();
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

  public WMS_Item_Produto_ClienteED Reverter( String oid )throws Excecoes{

      return new WMS_Item_Produto_ClienteRN().Reverter( oid );
  }

  public boolean itemJaIncluso( String oid_produto_cliente, String oid_nota_fiscal )throws Excecoes{
      return new WMS_Item_Produto_ClienteRN().itemJaIncluso( oid_produto_cliente, oid_nota_fiscal );
  }

  public String verificaItens(HttpServletRequest req) throws Excecoes{
    try{            
      WMS_Item_Produto_ClienteRN WMS_Item_Produto_ClienteRN = new WMS_Item_Produto_ClienteRN();   
      WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
      WMS_Item_Produto_ClienteED edI = new WMS_Item_Produto_ClienteED();
      
      ed.setOID_Unidade( new Long( req.getParameter("oid_Unidade") ).longValue() );
      ed.setOid_pessoa( req.getParameter("oid_Pessoa") );
      ed.setOid_nota_fiscal( req.getParameter("oid_Nota_Fiscal") );
      edI.setOid_Produto_Cliente( req.getParameter("FT_OID_Produto") + req.getParameter("oid_Pessoa") );
      edI.setNr_Quantidade_Movimento( new Double( req.getParameter("FT_NR_Quantidade") ).doubleValue() );
      
      ed.setDM_Gerado( "N" );
      
      return WMS_Item_Produto_ClienteRN.verificaItens(edI,ed);       
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao buscar item!");
      excecoes.setMetodo("verificaItens()");
      excecoes.setExc(exc);
      throw excecoes;
    }      
  }
  
  public void atualiza_Notas(HttpServletRequest req) throws Excecoes{
    try{
        WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();
        ed.setOid_Nota_Fiscal(req.getParameter("oid_Nota_Fiscal"));
        ed.setOid_Item_Produto_Cliente(req.getParameter("oid_Item_Produto_Cliente"));
        WMS_Item_Produto_ClienteRN WMS_Item_Produto_ClienteRN = new WMS_Item_Produto_ClienteRN();      
        WMS_Item_Produto_ClienteRN.atualiza_Notas( ed );  
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("atualiza_Notas(HttpServletRequest req)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }
}
