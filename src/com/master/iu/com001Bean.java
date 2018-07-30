package com.master.iu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Conhecimento_InternacionalED;
import com.master.ed.Tabela_FreteED;
import com.master.rn.Conhecimento_InternacionalRN;
import com.master.rn.Tabela_FreteRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class com001Bean {


  public void reajustaTabelaKieling(HttpServletRequest request)throws Excecoes{

    try{
      Tabela_FreteRN Tabela_Fretern = new Tabela_FreteRN();
      Tabela_FreteED ed = new Tabela_FreteED();
      ed.setOID_Pessoa(request.getParameter ("oid_Pessoa"));

      if (request.getParameter ("oid_Produto")!=null && !"null".equals(request.getParameter ("oid_Produto")) && !"".equals(request.getParameter ("oid_Produto"))){
        ed.setOid_Produto (new Long(request.getParameter ("oid_Produto")).longValue());
      }
      if (request.getParameter ("oid_Cidade_Origem")!=null && !"null".equals(request.getParameter ("oid_Cidade_Origem")) && !"".equals(request.getParameter ("oid_Cidade_Origem"))){
        ed.setOID_Cidade_Origem (new Long(request.getParameter ("oid_Cidade_Origem")).longValue());
      }
      if (request.getParameter ("oid_Cidade_Destino")!=null && !"null".equals(request.getParameter ("oid_Cidade_Destino")) && !"".equals(request.getParameter ("oid_Cidade_Destino"))){
        ed.setOID_Cidade_Destino (new Long(request.getParameter ("oid_Cidade_Destino")).longValue());
      }
      ed.setPE_Reajuste(new Double(request.getParameter ("FT_PE_Reajuste")).doubleValue());

      Tabela_Fretern.reajustaTabelaKieling(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao reajustar tabela Frete");
      excecoes.setMetodo("reajusta(HttpServletRequest request)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void reajustaTabela(HttpServletRequest request)throws Excecoes{

    try{
      Tabela_FreteRN Tabela_Fretern = new Tabela_FreteRN();
      Tabela_FreteED ed = new Tabela_FreteED();
      ed.setOID_Pessoa(request.getParameter ("oid_Pessoa"));

      if (request.getParameter ("oid_Produto")!=null && !"null".equals(request.getParameter ("oid_Produto")) && !"".equals(request.getParameter ("oid_Produto"))){
        ed.setOid_Produto (new Long(request.getParameter ("oid_Produto")).longValue());
      }

      ed.setPE_Reajuste(new Double(request.getParameter ("FT_PE_Reajuste")).doubleValue());

      Tabela_Fretern.reajustaTabela(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao reajustar tabela Frete");
      excecoes.setMetodo("reajusta(HttpServletRequest request)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


  public void imprimeTabela(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    Tabela_FreteED ed = new Tabela_FreteED();

    ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));

    String oid_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
    if (String.valueOf(oid_Cidade_Destino) != null && !String.valueOf(oid_Cidade_Destino).equals("")){
      ed.setOID_Cidade_Destino(new Long(oid_Cidade_Destino).longValue());
    }

    Tabela_FreteRN geRN = new Tabela_FreteRN();
    new EnviaPDF().enviaBytes(request,Response,geRN.imprimeTabela(ed));

  }
  
  public void geraRelCotacaoEmitida(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    	Tabela_FreteED ed = new Tabela_FreteED();

    	ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
    	
    	String oid_Origem = request.getParameter("oid_Pais_Origem");
    	if (JavaUtil.doValida(oid_Origem)){
    	    ed.setOid_Origem(new Long(oid_Origem).longValue());
    	}
    	String oid_Destino = request.getParameter("oid_Pais_Destino");
    	if (JavaUtil.doValida(oid_Destino)){
    	    ed.setOid_Destino(new Long(oid_Destino).longValue());
    	}

    	String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    	if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
    		ed.setDT_Emissao_Inicial(Dt_Emissao_Inicial);
    	}

    	String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
    	if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
    		ed.setDT_Emissao_Final(Dt_Emissao_Final);
    	}

    	String oid_Unidade = request.getParameter("oid_Unidade");
    	if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
    		ed.setOid_Unidade(new Long(oid_Unidade).longValue());
    	}
    	
      ed.setDM_CRT(request.getParameter("FT_DM_CRT"));
  // System.out.println("REL : "+ed.getDM_CRT());

    	Tabela_FreteRN geRN = new Tabela_FreteRN();
    	geRN.geraRelCotacaoEmitida(ed, Response);

    }
  

}