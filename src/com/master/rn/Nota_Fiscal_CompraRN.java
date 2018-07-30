package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.EstoqueBD;
import com.master.bd.Nota_Fiscal_CompraBD;
import com.master.ed.Lote_CompromissoED;
import com.master.ed.Nota_Fiscal_CompraED;
import com.master.ed.Posto_CompromissoED;
import com.master.ed.Produto_ClienteED;
import com.master.ed.Solicitacao_CompraED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;


public class Nota_Fiscal_CompraRN extends Transacao  {
  Nota_Fiscal_CompraBD Nota_Fiscal_CompraBD = null;


  public Nota_Fiscal_CompraRN() {
  }

  public Nota_Fiscal_CompraED inclui(Nota_Fiscal_CompraED ed)throws Excecoes{
    Nota_Fiscal_CompraED conED = new Nota_Fiscal_CompraED();
    try{

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Calendar DT_Ocorrencia = Calendar.getInstance();
      Date DT_Emissao = formatter.parse(ed.getDt_emissao());
      Calendar DT_Emissao_Calendario = Calendar.getInstance();
      DT_Emissao_Calendario.setTime(DT_Emissao);
      Date DT_Hoje = new Date();
      Calendar DT_Hoje_Calendario = Calendar.getInstance();
      DT_Hoje_Calendario.setTime(DT_Hoje);

      if (String.valueOf(ed.getDt_entrada()) != null &&
        !String.valueOf(ed.getDt_entrada()).equals("") &&
        !String.valueOf(ed.getDt_entrada()).equals("null")){
        Date Ocorrencia = formatter.parse(ed.getDt_entrada());
        DT_Ocorrencia.setTime(Ocorrencia);
        if (DT_Ocorrencia.after(DT_Hoje_Calendario) || DT_Emissao_Calendario.after(DT_Ocorrencia)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data da entrada tem de ser menor ou igual a hoje");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("inclui(Nota_Fiscal_CompraED ed)");
          throw exc;
        }
      }


     if (DT_Emissao_Calendario.after(DT_Hoje_Calendario)){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Data emissão tem de ser menor ou igual a data de hoje.");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Nota_Fiscal_CompraED ed)");
        throw exc;
      }

      if (ed.getVl_nota_fiscal() <= 0 && !ed.getDm_tipo_nota_fiscal().equals("0") && !ed.getDm_tipo_nota_fiscal().equals("1")&& !ed.getDm_tipo_nota_fiscal().equals("P")){

        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor da nota fiscal menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Nota_Fiscal_CompraED ed))");
        throw exc;
      }

      this.inicioTransacao();
      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      conED = Nota_Fiscal_CompraBD.inclui(ed);
      ed.setOid_nota_fiscal(conED.getOid_nota_fiscal());

     this.fimTransacao(true);
    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return conED;

  }


   public boolean inclui_Lancamento(Nota_Fiscal_CompraED ed)throws Excecoes{
   boolean aux = false;
   try{
      this.inicioTransacao();
      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      Nota_Fiscal_CompraBD.inclui_Lancamento(ed);
      this.fimTransacao(true);
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir os lancamentos");
      excecoes.setMetodo("inclui");
      aux = false;
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
    return aux;
  }

  public boolean inclui_Parcelamento(Nota_Fiscal_CompraED ed)throws Excecoes{
   boolean aux = false;
   Nota_Fiscal_CompraED auxiliar = (Nota_Fiscal_CompraED)ed;
   try{
      this.inicioTransacao();
      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      if(ed.getDM_Financeiro().equals("S")){
         Nota_Fiscal_CompraBD.inclui_Parcelamento(auxiliar);
         aux = true;
      }
      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir parcelamentos");
      excecoes.setMetodo("inclui");
      aux = false;
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
    return aux;
  }


  public void altera(Nota_Fiscal_CompraED ed)throws Excecoes{

    if (ed.getOid_nota_fiscal() == null){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código da Nota Fiscal não foi informado !!!");
      throw exc;
    }

    try{

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Calendar DT_Ocorrencia = Calendar.getInstance();

      Date DT_Emissao = formatter.parse(ed.getDt_emissao());
      Calendar DT_Emissao_Calendario = Calendar.getInstance();
      DT_Emissao_Calendario.setTime(DT_Emissao);

      Date DT_Hoje = new Date();
      Calendar DT_Hoje_Calendario = Calendar.getInstance();
      DT_Hoje_Calendario.setTime(DT_Hoje);
      /*
      // // System.out.println("Pass by hour integrity");
      if (String.valueOf(ed.getDt_entrada()) != null &&
        !String.valueOf(ed.getDt_entrada()).equals("") &&
        !String.valueOf(ed.getDt_entrada()).equals("null")){
        Date Ocorrencia = formatter.parse(ed.getDt_entrada());
        DT_Ocorrencia.setTime(Ocorrencia);
        if (DT_Ocorrencia.after(DT_Hoje_Calendario) || DT_Emissao_Calendario.after(DT_Ocorrencia)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data da entrada tem de ser menor ou igual a hoje");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("altera(Nota_Fiscal_CompraED ed)");
          throw exc;
        }
      }*/
      if (DT_Emissao_Calendario.after(DT_Hoje_Calendario)){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Data emissão tem de ser menor ou igual a data de hoje.");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("altera(Nota_Fiscal_CompraED ed)");
        throw exc;
      }

      if (ed.getVl_nota_fiscal() <= 0 && !ed.getDm_tipo_nota_fiscal().equals("0") && !ed.getDm_tipo_nota_fiscal().equals("1")&& !ed.getDm_tipo_nota_fiscal().equals("P")){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor da nota fiscal menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("altera(Nota_Fiscal_CompraED ed))");
        throw exc;
      }

      this.inicioTransacao();

      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      Nota_Fiscal_CompraBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void altera_Apos_Finalizada(Nota_Fiscal_CompraED ed)throws Excecoes{

	    if (ed.getOid_nota_fiscal() == null){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código da Nota Fiscal não foi informado !!!");
	      throw exc;
	    }

	    try{

	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	      Calendar DT_Ocorrencia = Calendar.getInstance();

	      Date DT_Emissao = formatter.parse(ed.getDt_emissao());
	      Calendar DT_Emissao_Calendario = Calendar.getInstance();
	      DT_Emissao_Calendario.setTime(DT_Emissao);

	      Date DT_Hoje = new Date();
	      Calendar DT_Hoje_Calendario = Calendar.getInstance();
	      DT_Hoje_Calendario.setTime(DT_Hoje);
	      /*
	      // // // System.out.println("Pass by hour integrity");
	      if (String.valueOf(ed.getDt_entrada()) != null &&
	        !String.valueOf(ed.getDt_entrada()).equals("") &&
	        !String.valueOf(ed.getDt_entrada()).equals("null")){
	        Date Ocorrencia = formatter.parse(ed.getDt_entrada());
	        DT_Ocorrencia.setTime(Ocorrencia);
	        if (DT_Ocorrencia.after(DT_Hoje_Calendario) || DT_Emissao_Calendario.after(DT_Ocorrencia)){
	          Excecoes exc = new Excecoes();
	          exc.setMensagem("Data da entrada tem de ser menor ou igual a hoje");
	          exc.setClasse(this.getClass().getName());
	          exc.setMetodo("altera(Nota_Fiscal_CompraED ed)");
	          throw exc;
	        }
	      }*/
	      if (DT_Emissao_Calendario.after(DT_Hoje_Calendario)){
	        Excecoes exc = new Excecoes();
	        exc.setMensagem("Data emissão tem de ser menor ou igual a data de hoje.");
	        exc.setClasse(this.getClass().getName());
	        exc.setMetodo("altera(Nota_Fiscal_CompraED ed)");
	        throw exc;
	      }

	      if (ed.getVl_nota_fiscal() <= 0 && !ed.getDm_tipo_nota_fiscal().equals("0") && !ed.getDm_tipo_nota_fiscal().equals("1")&& !ed.getDm_tipo_nota_fiscal().equals("P")){
	        Excecoes exc = new Excecoes();
	        exc.setMensagem("Valor da nota fiscal menor ou igual a zero");
	        exc.setClasse(this.getClass().getName());
	        exc.setMetodo("altera(Nota_Fiscal_CompraED ed))");
	        throw exc;
	      }

	      this.inicioTransacao();

	      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
	      Nota_Fiscal_CompraBD.altera_Apos_Finalizada(ed);

	      this.fimTransacao(true);

	    }
	    catch(Excecoes exc){
	    this.abortaTransacao();
	    throw exc;}

	    catch(Exception e){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao alterar");
	      excecoes.setMetodo("alterar");
	      excecoes.setExc(e);
	      //faz rollback pois deu algum erro
	      this.abortaTransacao();

	      throw excecoes;
	    }
	  }

  public void deleta(Nota_Fiscal_CompraED ed)throws Excecoes{

    if (ed.getOid_nota_fiscal().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota Fiscal não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      Nota_Fiscal_CompraBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void inclui_Contabilizacao(Nota_Fiscal_CompraED ed)throws Excecoes{

	    if (ed.getOid_nota_fiscal().compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Nota Fiscal não foi informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
	      Nota_Fiscal_CompraBD.inclui_Contabilizacao(ed);

	      this.fimTransacao(true);

	    }
	    catch(Excecoes exc){
	    this.abortaTransacao();
	    throw exc;}

	    catch(Exception e){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao inclui_Contabilizacao");
	      excecoes.setMetodo("inclui_Contabilizacao");
	      excecoes.setExc(e);
	      //faz rollback pois deu algum erro
	      this.abortaTransacao();

	      throw excecoes;
	    }
	  }

    public void finaliza(Nota_Fiscal_CompraED ed)throws Excecoes{
    if (ed.getOid_nota_fiscal().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota Fiscal não foi informada !!!");
      throw exc;
    }

    try{
      this.inicioTransacao();
      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);

      if (ed.getDm_tipo_nota_fiscal().equals("E")) {
          if(Nota_Fiscal_CompraBD.verificaItens(ed)){
        	if(true){ //sem verificar lctos TEMPORARIO - TEO!
            //if(Nota_Fiscal_CompraBD.verificaLancamentos(ed)){
              if(Nota_Fiscal_CompraBD.verificaParcelamentos(ed)){
                Nota_Fiscal_CompraBD.finaliza(ed);
                //Nota_Fiscal_CompraBD.verificaFiscal(ed);
              }else{
                Exception exc = new Exception("Erro ao finalizar os Parcelamentos");
                throw exc;
              }
           }else{
              Exception exc = new Exception("Erro ao finalizar os Lancamentos");
              throw exc;
            }
          }else{
            Exception exc = new Exception("Erro ao finalizar os Itens");
            throw exc;
          }
        } else {
        	if  ( "4".equals(ed.getDm_forma_pagamento())) { // se compromisso em carteira não trata os parcelamentos
      		  Nota_Fiscal_CompraBD.finaliza(ed);
      		  //Nota_Fiscal_CompraBD.verificaFiscal(ed);
      	  } else {
      		  if(Nota_Fiscal_CompraBD.verificaParcelamentos(ed)){
      			  Nota_Fiscal_CompraBD.finaliza(ed);
      			  //Nota_Fiscal_CompraBD.verificaFiscal(ed);
      		  }else{
      			  Exception exc = new Exception("Erro ao finalizar os Parcelamentos");
      			  throw exc;
      		  }
      	  }
        }

//     if(ed.getNr_volumes()>0){
//         else {
//		if (Nota_Fiscal_CompraBD.verificaLancamentos(ed)) {
//          		Nota_Fiscal_CompraBD.finaliza(ed);
//          		//Nota_Fiscal_CompraBD.verificaFiscal(ed);
//		}
//        }
//     }else{
//        if (!ed.getDm_tipo_nota_fiscal().equals("0") && !ed.getDm_tipo_nota_fiscal().equals("1")&& !ed.getDm_tipo_nota_fiscal().equals("P")) {
//          if(true){
//            //if(Nota_Fiscal_CompraBD.verificaLancamentos(ed)){
//
//          }else{
//            Exception exc = new Exception("Erro ao finalizar os Lancamentos");
//            throw exc;
//          }
//
//        } else {
//            Nota_Fiscal_CompraBD.finaliza(ed);
//            //Nota_Fiscal_CompraBD.verificaFiscal(ed);
//        }
//     }

     this.fimTransacao(true);

    } catch(Excecoes exc) {
        this.abortaTransacao();
        throw exc;
    } catch(Exception e) {
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(e.getMessage());
      excecoes.setMetodo("finalizar");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
  }



  public ArrayList lista(Nota_Fiscal_CompraED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Nota_Fiscal_CompraBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Nota_Fiscal_CompraED getByRecord(Nota_Fiscal_CompraED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Nota_Fiscal_CompraED edVolta = new Nota_Fiscal_CompraED();
      //atribui ao ed de retorno
      edVolta = new Nota_Fiscal_CompraBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void reabre(Nota_Fiscal_CompraED ed)throws Excecoes{

    if (ed.getOid_nota_fiscal().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Cóo do Nota Fiscal nãfoi informado!!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      Nota_Fiscal_CompraBD.reabre(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;
    }

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao reabrir nota fiscal");
      excecoes.setMetodo("reabre");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

   public boolean inclui_Lancamentos_Pagamento_Compromisso(Lote_CompromissoED ed)throws Excecoes{
   boolean aux = false;
   Lote_CompromissoED auxiliar = (Lote_CompromissoED)ed;

   try{
      this.inicioTransacao();
      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      if(ed.getDM_Contabiliza().equals("S")){
         Nota_Fiscal_CompraBD.inclui_Lancamentos_Pagamento_Compromisso(auxiliar);
         aux = true;
      }
      this.fimTransacao(true);
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui_Lancamentos_Pagamento_Compromisso");
      aux = false;
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }
    return aux;
  }

  public void geraRelatorioImpostoSemanalNotasFiscais(String dt_ini, String dt_fim, String oid_unidade, String nm_fantasia, HttpServletResponse response) throws Excecoes{

        try{

            this.inicioTransacao();

            Nota_Fiscal_CompraBD nftBD = new Nota_Fiscal_CompraBD(this.sql);
            nftBD.geraRelatorioImpostoSemanalNotasFiscais(dt_ini, dt_fim, oid_unidade, nm_fantasia, response);

            this.fimTransacao(true);

        } catch(Exception e){
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao gerar relatorio");
            excecoes.setMetodo("geraRelatorioImpostoSemanalNotasFiscais");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }

  }

   public boolean inclui_Lancamentos_Lote_Posto(Posto_CompromissoED ed)throws Excecoes{
   boolean aux = false;
   Posto_CompromissoED auxiliar = (Posto_CompromissoED)ed;

   try{
      this.inicioTransacao();
      Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
      if(ed.getDM_Contabiliza().equals("S")){
         Nota_Fiscal_CompraBD.inclui_Lancamentos_Lote_Posto(auxiliar);
         aux = true;
      }
      this.fimTransacao(true);
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui_Lancamentos_Pagamento_Compromisso");
      aux = false;
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }
    return aux;
  }

   public Nota_Fiscal_CompraED calculaPISCOFINSCSLL(Nota_Fiscal_CompraED ed)throws Excecoes{
       Nota_Fiscal_CompraED conED = new Nota_Fiscal_CompraED();
       try{

         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         Calendar DT_Ocorrencia = Calendar.getInstance();
         Date DT_Emissao = formatter.parse(ed.getDt_emissao());
         Calendar DT_Emissao_Calendario = Calendar.getInstance();
         DT_Emissao_Calendario.setTime(DT_Emissao);
         Date DT_Hoje = new Date();
         Calendar DT_Hoje_Calendario = Calendar.getInstance();
         DT_Hoje_Calendario.setTime(DT_Hoje);

        if (DT_Emissao_Calendario.after(DT_Hoje_Calendario)){
           Excecoes exc = new Excecoes();
           exc.setMensagem("Data emissão tem de ser menor ou igual a data de hoje.");
           exc.setClasse(this.getClass().getName());
           exc.setMetodo("inclui(Nota_Fiscal_CompraED ed)");
           throw exc;
         }

         if (ed.getVl_nota_fiscal() <= 0 && !ed.getDm_tipo_nota_fiscal().equals("0") && !ed.getDm_tipo_nota_fiscal().equals("1")&& !ed.getDm_tipo_nota_fiscal().equals("P")){

           Excecoes exc = new Excecoes();
           exc.setMensagem("Valor da nota fiscal menor ou igual a zero");
           exc.setClasse(this.getClass().getName());
           exc.setMetodo("inclui(Nota_Fiscal_CompraED ed))");
           throw exc;
         }

         this.inicioTransacao();

         Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);

         conED = Nota_Fiscal_CompraBD.calculaPISCOFINSCSLL(ed);

         this.fimTransacao(true);
       }
       catch(Excecoes exc){
       this.abortaTransacao();
       throw exc;}

       catch(Exception e){
         Excecoes excecoes = new Excecoes();
         excecoes.setClasse(this.getClass().getName());
         excecoes.setMetodo("inclui");
         excecoes.setExc(e);
         //faz rollback pois deu algum erro
         this.abortaTransacao();

         throw excecoes;
       }

       return conED;

     }

   public ArrayList lista_Pedidos(Nota_Fiscal_CompraED ed)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       ArrayList lista = new Nota_Fiscal_CompraBD(sql).lista_Pedidos(ed);
       this.fimTransacao(false);
       return lista;
   }

   public Solicitacao_CompraED inclui_Pedido_Nota_Fiscal(Solicitacao_CompraED ed) throws Excecoes{

       Solicitacao_CompraED edVolta = new Solicitacao_CompraED();

       try{
           this.inicioTransacao();
           Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
           edVolta = Nota_Fiscal_CompraBD.inclui_Pedido_Nota_Fiscal(ed);
           this.fimTransacao(true);
       }
       catch(Excecoes e){
           this.abortaTransacao();
           throw e;
       }
       catch(Exception exc){
           Excecoes excecoes = new Excecoes();
           excecoes.setClasse(this.getClass().getName());
           excecoes.setMetodo("inclui_Pedido_Nota_Fiscal()");
           excecoes.setExc(exc);
           this.abortaTransacao();
           throw excecoes;
       }
       return edVolta;
   }

   public Solicitacao_CompraED getByRecord_Pedido_Nota_Fiscal(Solicitacao_CompraED ed)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       Solicitacao_CompraED edVolta = new Solicitacao_CompraED();
       edVolta = new Nota_Fiscal_CompraBD(sql).getByRecord_Pedido_Nota_Fiscal(ed);
       this.fimTransacao(false);
       return edVolta;
   }

   public void deleta_Pedido_Nota_Fiscal(Nota_Fiscal_CompraED ed)throws Excecoes{

       try{

         this.inicioTransacao();
         Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
         Nota_Fiscal_CompraBD.deleta_Pedido_Nota_Fiscal(ed);
         this.fimTransacao(true);

       }
       catch(Excecoes exc){
       this.abortaTransacao();
       throw exc;
       }

       catch(Exception e){
         Excecoes excecoes = new Excecoes();
         excecoes.setClasse(this.getClass().getName());
         excecoes.setMensagem("Erro ao excluir!");
         excecoes.setMetodo("deleta_Pedido_Nota_Fiscal()");
         excecoes.setExc(e);
         this.abortaTransacao();

         throw excecoes;
       }
     }

   public ArrayList lista_Romaneio(Nota_Fiscal_CompraED ed)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       ArrayList lista = new Nota_Fiscal_CompraBD(sql).lista_Romaneio(ed);
       this.fimTransacao(false);
       return lista;
   }

   public Solicitacao_CompraED inclui_Romaneio(Nota_Fiscal_CompraED ed) throws Excecoes{

       Solicitacao_CompraED edVolta = new Solicitacao_CompraED();

       try{
           this.inicioTransacao();
           Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
           edVolta = Nota_Fiscal_CompraBD.inclui_Romaneio(ed);
           this.fimTransacao(true);
       }
       catch(Excecoes e){
           this.abortaTransacao();
           throw e;
       }
       catch(Exception exc){
           Excecoes excecoes = new Excecoes();
           excecoes.setClasse(this.getClass().getName());
           excecoes.setMetodo("inclui_Romaneio()");
           excecoes.setExc(exc);
           this.abortaTransacao();
           throw excecoes;
       }
       return edVolta;
   }

   public Solicitacao_CompraED getByRecord_Romaneio(Solicitacao_CompraED ed)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       Solicitacao_CompraED edVolta = new Solicitacao_CompraED();
       edVolta = new Nota_Fiscal_CompraBD(sql).getByRecord_Romaneio(ed);
       this.fimTransacao(false);
       return edVolta;
   }

   public void inclui_Local_Qtde(Solicitacao_CompraED ed)throws Excecoes{

       Produto_ClienteED prodED = new Produto_ClienteED();
       Produto_ClienteRN prodRN = new Produto_ClienteRN();
       EstoqueBD estBD = new EstoqueBD(this.sql);
       try{

         this.inicioTransacao();
         Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
         Nota_Fiscal_CompraBD.inclui_Local_Qtde(ed);

         //inclui estoque
         //estBD.entrada_Estoque(String.valueOf(ed.getOid_estoque()), ed.getVl_quantidade());
         //Movimento de entrada
         //estBD.Movimento_Entrada_Estoque(String.valueOf(ed.getOid_estoque()), ed.getOid_nota_fiscal(), ed.getVl_quantidade());

         this.fimTransacao(true);

       }
       catch(Excecoes exc){
       this.abortaTransacao();
       throw exc;
       }

       catch(Exception e){
         Excecoes excecoes = new Excecoes();
         excecoes.setClasse(this.getClass().getName());
         excecoes.setMensagem("Erro ao incluir!");
         excecoes.setMetodo("inclui_Local_Qtde()");
         excecoes.setExc(e);
         this.abortaTransacao();

         throw excecoes;
       }
     }

   public void imprime_Romaneio(Nota_Fiscal_CompraED ed, HttpServletResponse res)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       new Nota_Fiscal_CompraBD(sql).imprime_Romaneio(ed, res);
       this.fimTransacao(false);
   }

   public boolean verifica_Pedidos(Nota_Fiscal_CompraED ed)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       boolean lista = new Nota_Fiscal_CompraBD(sql).verifica_Pedidos(ed);
       this.fimTransacao(false);
       return lista;
   }

   public void geraRel_NFEntrada(Nota_Fiscal_CompraED ed, HttpServletResponse res)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       new Nota_Fiscal_CompraBD(sql).geraRel_NFEntrada(ed, res);
       this.fimTransacao(false);
   }

   public Solicitacao_CompraED inclui_Movimento_OS_Nota_Fiscal(Solicitacao_CompraED ed) throws Excecoes{

       Solicitacao_CompraED edVolta = new Solicitacao_CompraED();

       try{
           this.inicioTransacao();
           Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
           edVolta = Nota_Fiscal_CompraBD.inclui_Movimento_OS_Nota_Fiscal(ed);
           this.fimTransacao(true);
       }
       catch(Excecoes e){
           this.abortaTransacao();
           throw e;
       }
       catch(Exception exc){
           Excecoes excecoes = new Excecoes();
           excecoes.setClasse(this.getClass().getName());
           excecoes.setMetodo("inclui_Movimento_OS_Nota_Fiscal()");
           excecoes.setExc(exc);
           this.abortaTransacao();
           throw excecoes;
       }
       return edVolta;
   }

   public void deleta_Movimento_OS_Nota_Fiscal(Solicitacao_CompraED ed)throws Excecoes{

       try{

         this.inicioTransacao();
         Nota_Fiscal_CompraBD = new Nota_Fiscal_CompraBD(this.sql);
         Nota_Fiscal_CompraBD.deleta_Movimento_OS_Nota_Fiscal(ed);
         this.fimTransacao(true);

       }
       catch(Excecoes exc){
       this.abortaTransacao();
       throw exc;
       }

       catch(Exception e){
         Excecoes excecoes = new Excecoes();
         excecoes.setClasse(this.getClass().getName());
         excecoes.setMensagem("Erro ao excluir!");
         excecoes.setMetodo("deleta_Movimento_OS_Nota_Fiscal()");
         excecoes.setExc(e);
         this.abortaTransacao();

         throw excecoes;
       }
     }

   public ArrayList listaMov_ServicoToNF(Solicitacao_CompraED ed)throws Excecoes{

       //retorna um arraylist de ED´s
       this.inicioTransacao();
       ArrayList lista = new Nota_Fiscal_CompraBD(sql).listaMov_ServicoToNF(ed);
       this.fimTransacao(false);
       return lista;
   }

   /********************************************************
   *
   *******************************************************/
    public Solicitacao_CompraED getMov_ServicoToNF(Solicitacao_CompraED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        Solicitacao_CompraED edVolta = new Solicitacao_CompraED();
        //atribui ao ed de retorno
        edVolta = new Nota_Fiscal_CompraBD(sql).getMov_ServicoToNF(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

}
