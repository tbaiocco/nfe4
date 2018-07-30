package com.master.iu;

/**
 * Título: wms020Bean
 * Descrição: Notas Fiscais Clientes - Bean
 * Data da criação: 03/2004
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ModeloNotaFiscalED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.ed.WMS_Requisicoes_ProdutosED;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.rn.WMS_Nota_Fiscal_ClientesRN;
import com.master.root.ClienteBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.ed.Parametro_FixoED;

public class wms020Bean {

  private ArrayList lista = null;  

  public WMS_Nota_FiscalED inclui(HttpServletRequest request)throws Excecoes{
     boolean ok = false;

    try{
      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
      ModeloNotaFiscalRN ModeloNotaFiscalRN = new ModeloNotaFiscalRN();
      WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

      ClienteBean ClienteVolta = new ClienteBean();

      //request em cima dos campos dos forms html
      ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));

      ModeloNotaFiscalED Modelo = new ModeloNotaFiscalED();
      Modelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());
      Modelo = ModeloNotaFiscalRN.getByRecord(Modelo);

      ed.setDM_Contabiliza(Modelo.getDM_Aceita_Contabilizacao());

      ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));

      ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));

      ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));

      ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador")); 

      ed.setNr_nota_fiscal(Long.valueOf(request.getParameter("FT_NR_Nota_Fiscal")).longValue());     
      
      ed.setNm_serie(request.getParameter("FT_NM_Serie")); 
      
      ed.setDt_entrada(request.getParameter("FT_DT_Saida"));
      ed.setHr_entrada(request.getParameter("FT_HR_Saida"));  
      ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
      ed.setOID_Unidade(Long.parseLong(request.getParameter("oid_Unidade")));
      ed.setOID_Empresa(Integer.valueOf( request.getParameter("oid_Empresa") ).intValue());
      ed.setCD_Empresa(request.getParameter("FT_CD_Empresa"));
      ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
      
      if(request.getParameter("FT_NR_Quantidade") != null && !request.getParameter("FT_NR_Quantidade").equals("") && !request.getParameter("FT_NR_Quantidade").equals("null") )
        ed.setNR_Quantidade(Integer.valueOf( request.getParameter("FT_NR_Quantidade")).intValue());
      else ed.setNR_Quantidade(0);
      ed.setDM_Frete(request.getParameter("FT_DM_Frete"));
      ed.setNM_Especie(request.getParameter("FT_NM_Especie"));
      ed.setNM_Marca(request.getParameter("FT_NM_Marca"));
      ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
      ed.setNR_Numero(request.getParameter("FT_NR_Numero"));
      ed.setDM_Gerado( "N" );


 //Valor do ICMS    
      double Cvalor = 0;
      ed.setVl_icms(Cvalor);

//Valor do NR_Peso_Liquido
      ed.setNR_Peso_Liquido(Cvalor);

//Valor do NR_Peso_Bruto
      ed.setNR_Peso_Bruto(Cvalor);

//Valor do Total_Produtos
      ed.setVL_Total_Produtos(Cvalor);

//Valor do ICMS_Substituicao
      ed.setVL_ICMS_Substituicao(Cvalor);

//Valor do Base_Calculo_ICMS_Substituicao
      ed.setVL_Base_Calculo_ICMS_Substituicao(Cvalor);

//Valor do Base_Calculo_ICMS
      ed.setVL_Base_Calculo_ICMS(Cvalor);

//Valor do INSS
      ed.setVl_inss(Cvalor);


//Valor do IPI
      ed.setVl_ipi(Cvalor);


//Valor do IRRF
      ed.setVl_irrf(Cvalor);


//Valor do ISQN
      ed.setVl_isqn(Cvalor);


//Valor do Frete
      ed.setVl_total_frete(Cvalor);


//Valor do Seguro
      ed.setVl_total_seguro(Cvalor);

//Valor do Despesas
      ed.setVl_total_despesas(Cvalor);

//Valor do Liquido da Nota Fiscal
      ed.setVl_liquido_nota_fiscal(0);

//Valor do Descontos
      ed.setVl_descontos(Cvalor);

//Valor do Servico
      ed.setVL_Servico(Cvalor);

      ed.setDm_observacao("");
      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("")
        && !String.valueOf(NR_Nota_Fiscal).equals("null")){
            ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
      }
      else {
           ed.setNr_nota_fiscal(0);
      }     
      
      
      ed.setVl_nota_fiscal(Cvalor);


      return WMS_Nota_Fiscal_ClientesRN.inclui(ed);
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
      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
      WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
      ClienteBean ClienteVolta = new ClienteBean();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
      ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
      ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador"));     
      
      ed.setDt_entrada(request.getParameter("FT_DT_Saida"));
      ed.setHr_entrada(request.getParameter("FT_HR_Saida"));          
      ed.setOID_Unidade(Long.parseLong(request.getParameter("oid_Unidade")));
      ed.setOID_Empresa(Integer.valueOf( request.getParameter("oid_Empresa") ).intValue());
      ed.setCD_Empresa(request.getParameter("FT_CD_Empresa"));
      ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
      if(request.getParameter("FT_NR_Quantidade") != null && !request.getParameter("FT_NR_Quantidade").equals("") && !request.getParameter("FT_NR_Quantidade").equals("null") )
        ed.setNR_Quantidade(Integer.valueOf( request.getParameter("FT_NR_Quantidade")).intValue());
      else ed.setNR_Quantidade(0);
      ed.setDM_Frete(request.getParameter("FT_DM_Frete"));
      ed.setNM_Especie(request.getParameter("FT_NM_Especie"));
      ed.setNM_Marca(request.getParameter("FT_NM_Marca"));
      ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
      ed.setNR_Numero(request.getParameter("FT_NR_Numero"));


      String valor = request.getParameter("FT_VL_ICMS");
      double Cvalor = 0;

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_icms(Cvalor);
      
      
      
       valor = request.getParameter("FT_VL_Servico");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }
     
      ed.setVL_Servico(Double.parseDouble(request.getParameter("FT_VL_Servico")));
      

//Valor do NR_Peso_Liquido
      valor = request.getParameter("FT_NR_Peso_Liquido");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setNR_Peso_Liquido(Cvalor);

//Valor do NR_Peso_Bruto
      valor = request.getParameter("FT_NR_Peso_Bruto");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setNR_Peso_Bruto(Cvalor);

//Valor do Total_Produtos
      valor = request.getParameter("FT_VL_Total_Produtos");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Total_Produtos(Cvalor);

//Valor do ICMS_Substituicao
      valor = request.getParameter("FT_VL_ICMS_Substituicao");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_ICMS_Substituicao(Cvalor);

//Valor do Base_Calculo_ICMS_Substituicao
      valor = request.getParameter("FT_VL_Base_Calculo_ICMS_Substituicao");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Base_Calculo_ICMS_Substituicao(Cvalor);

//Valor do Base_Calculo_ICMS
      valor = request.getParameter("FT_VL_Base_Calculo_ICMS");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Base_Calculo_ICMS(Cvalor);

//Valor do INSS
      valor = request.getParameter("FT_VL_INSS");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_inss(Cvalor);


//Valor do IPI
      valor = request.getParameter("FT_VL_IPI");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_ipi(Cvalor);


//Valor do IRRF
      valor = request.getParameter("FT_VL_IRRF");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_irrf(Cvalor);


//Valor do ISQN
      valor = request.getParameter("FT_VL_ISQN");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_isqn(Cvalor);


//Valor do Frete
      valor = request.getParameter("FT_VL_Frete");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_frete(Cvalor);


//Valor do Seguro
      valor = request.getParameter("FT_VL_Seguro");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_seguro(Cvalor);

//Valor do Despesas
      valor = request.getParameter("FT_VL_Despesas");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_despesas(Cvalor);

//Valor do Despesas
      valor = request.getParameter("FT_VL_LIQUIDO");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_liquido_nota_fiscal(Cvalor);



//Valor do Descontos
      valor = request.getParameter("FT_VL_Desconto");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_descontos(Cvalor);


      ed.setDm_observacao(request.getParameter("ft_mm_obs"));
      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("")
        && !String.valueOf(NR_Nota_Fiscal).equals("null")){
            ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
      }
      else {
           ed.setNr_nota_fiscal(0);
      }      

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")
        && !String.valueOf(VL_Nota_Fiscal).equals("null")){
        ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
      }else ed.setVl_nota_fiscal(0);


      WMS_Nota_Fiscal_ClientesRN.altera(ed);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("altera");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
      WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));

      WMS_Nota_Fiscal_ClientesRN.deleta(ed);
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

  public ArrayList lista(HttpServletRequest request, String orderby)throws Excecoes{
      WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

      if(request.getParameter("FT_DM_Tipo") != null && !request.getParameter("FT_DM_Tipo").equals(""))     
        ed.setDm_observacao( request.getParameter("FT_DM_Tipo"));
      
      if(request.getParameter("FT_NR_Nota_Fiscal") != null && !request.getParameter("FT_NR_Nota_Fiscal").equals(""))
         ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      
      if(request.getParameter("FT_NM_Serie") != null && !request.getParameter("FT_NM_Serie").equals(""))
         ed.setNm_serie(request.getParameter("FT_NM_Serie"));
      
      if(request.getParameter("FT_DT_Emissao") != null && !request.getParameter("FT_DT_Emissao").equals(""))
        ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
      
     if(request.getParameter("oid_Pessoa_Destinatario") != null && !request.getParameter("oid_Pessoa_Destinatario").equals(""))
       ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      
     if(request.getParameter("oid_Pessoa_Transportador") != null && !request.getParameter("oid_Pessoa_Transportador").equals(""))
       ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador"));   
  
     if(request.getParameter("FT_CD_Situacao") != null && !request.getParameter("FT_CD_Situacao").equals(""))
       ed.setDm_finalizado(request.getParameter("FT_CD_Situacao"));    
     
     if(request.getParameter("oid_Pessoa_Remetente") != null && !request.getParameter("oid_Pessoa_Remetente").equals(""))
       ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
      
     if(request.getParameter("oid_Unidade") != null && !request.getParameter("oid_Unidade").equals(""))
       ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
     else ed.setOID_Unidade(0);
          
     try{
          WMS_Nota_Fiscal_ClientesRN RN = new WMS_Nota_Fiscal_ClientesRN();
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



  public WMS_Nota_FiscalED getByRecord(HttpServletRequest request)throws Excecoes{

      WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

      if (request.getParameter("FT_NR_Nota_Fiscal") != null && request.getParameter("FT_NR_Nota_Fiscal").length() > 0)
      {
        ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      }
      String NM_Serie = request.getParameter("FT_NM_Serie");
      if (NM_Serie != null && !NM_Serie.equals("") && !NM_Serie.equals("null"))
      {
        ed.setNm_serie(request.getParameter("FT_NM_Serie"));
      }

      if (request.getParameter("oid_Pessoa_Remetente") != null && !request.getParameter("oid_Pessoa_Remetente").equals("") && !request.getParameter("oid_Pessoa_Remetente").equals("null"))
      {
        ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
      }

      if (request.getParameter("oid_Pessoa_Destinatario") != null && !request.getParameter("oid_Pessoa_Destinatario").equals("") && !request.getParameter("oid_Pessoa_Destinatario").equals("null"))
      {
        ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      }

      if (request.getParameter("oid_Pessoa_Transportador") != null && !request.getParameter("oid_Pessoa_Transportador").equals("") && !request.getParameter("oid_Pessoa_Transportador").equals("null"))
      {
        ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador"));
      }
      if (request.getParameter("FT_DT_Emissao") != null && !request.getParameter("FT_DT_Emissao").equals("") && !request.getParameter("FT_DT_Emissao").equals("null"))
      {
        ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
      }

      if ( request.getParameter("oid_Modelo") != null && !request.getParameter("oid_Modelo").equals("") && !request.getParameter("oid_Modelo").equals("null") )
      {
        ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
      }

      return new WMS_Nota_Fiscal_ClientesRN().getByRecord(ed);

  }
  public WMS_Nota_FiscalED getByOid(String oid_Nota)throws Excecoes{

      WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

      ed.setOid_nota_fiscal(oid_Nota);


      return new WMS_Nota_Fiscal_ClientesRN().getByRecord(ed);

  }

  public WMS_Requisicoes_ProdutosED gerarRequisicao( javax.servlet.http.HttpServletRequest request )throws Excecoes{
    WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();
    WMS_Nota_FiscalED nota_fiscalVolta = new WMS_Nota_FiscalED();
    Parametro_FixoED Parametro_Fixo = new Parametro_FixoED();
    try{
      int OID_Deposito = Parametro_Fixo.getOID_Deposito();
      int OID_Tipo_Movimento_Produto = Parametro_Fixo.getOID_Tipo_Movimento_Produto();

      if( request.getParameter( "FT_DM_TipoNota" ).equals( "E" ) )
        OID_Tipo_Movimento_Produto = 2;
      if( request.getParameter( "FT_DM_TipoNota" ).equals( "S" ) )
        OID_Tipo_Movimento_Produto = 3;

      nota_fiscalVolta = this.getByOid( request.getParameter( "oid_Nota_Fiscal" ) );

      ed.setOID_Deposito( OID_Deposito );
      ed.setNR_Nota_Fiscal_Transacao( String.valueOf( nota_fiscalVolta.getNr_nota_fiscal() ) );
      ed.setOID_Pessoa( request.getParameter( "oid_Pessoa_Remetente" ) );
      ed.setOID_Pessoa_Destinatario( request.getParameter( "oid_Pessoa_Destinatario" ) );
      ed.setOID_Pessoa_Transportador( request.getParameter( "oid_Pessoa_Transportador" ) );
      ed.setOID_Tipo_Movimento_Produto( OID_Tipo_Movimento_Produto );
      ed.setDT_Requisicao( Data.getDataDMY() );
      ed.setHR_Requisicao( Data.getHoraHM() );
      ed.setDM_Situacao( "A" );
      ed.setOID_Nota_Fiscal_Transacao( request.getParameter( "oid_Nota_Fiscal" ) );

      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
      return WMS_Nota_Fiscal_ClientesRN.gerarRequisicao( ed );
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void gerarMovimentos( javax.servlet.http.HttpServletRequest request, int OID_Requisicao_Produto )throws Excecoes{
    try{
      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
      WMS_Nota_Fiscal_ClientesRN.gerarMovimentos( request, OID_Requisicao_Produto );
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public byte[] geraRelatorioNotaFiscal(HttpServletRequest req, HttpServletResponse Response)throws Excecoes{
    WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

    ed.setOid_nota_fiscal( req.getParameter( "oid_Nota_Fiscal" ) );
    
   // ed.setNr_nota_fiscal( Long.valueOf( req.getParameter( "FT_NR_Nota_Fiscal" ) ).longValue() );

    WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
    byte[] b = WMS_Nota_Fiscal_ClientesRN.geraRelatorioNotaFiscal( ed );

    return b;
  }
  
  public long calcula_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{   
    WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
    long b = WMS_Nota_Fiscal_ClientesRN.calcula_Quantidade_Itens( ed );

    return b;
  }
  
  public void atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{
    try{
      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();     
      WMS_Nota_Fiscal_ClientesRN.atualiza_Quantidade_Itens(ed);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }
  
  public double calcula_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
    WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
    double b = WMS_Nota_Fiscal_ClientesRN.calcula_Total_Produtos( ed );

    return b;
  }

  public double calcula_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
    WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
    double b = WMS_Nota_Fiscal_ClientesRN.calcula_Total_Nota( ed );

    return b;
  }
  
  public void atualiza_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
    try{
      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();     
      WMS_Nota_Fiscal_ClientesRN.atualiza_Total_Produtos(ed);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("atualiza_Total_Produtos(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }
  
  public void atualiza_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
    try{
      WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();     
      WMS_Nota_Fiscal_ClientesRN.atualiza_Total_Nota(ed);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("atualiza_Total_Nota(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }
  
  public boolean isImpresso(HttpServletRequest req)throws Excecoes{
    WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

    ed.setOid_nota_fiscal(req.getParameter("oid_Nota_Fiscal"));

    WMS_Nota_Fiscal_ClientesRN WMS_Nota_Fiscal_ClientesRN = new WMS_Nota_Fiscal_ClientesRN();
    boolean b = WMS_Nota_Fiscal_ClientesRN.isImpresso( ed );

    return b;
  }

}
