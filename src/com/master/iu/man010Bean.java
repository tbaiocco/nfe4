package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Viagem_InternacionalRN;
import com.master.ed.Viagem_InternacionalED;
import com.master.util.Excecoes;
import java.util.*;
import com.master.util.*;

public class man010Bean {

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Viagem_InternacionalRN Viagem_Internacionalrn = new Viagem_InternacionalRN();
      Viagem_InternacionalED ed = new Viagem_InternacionalED();
      
      ed.setDM_Capa_Mic(request.getParameter("FT_DM_Capa_Mic"));

      ed.setOID_Viagem_Internacional(request.getParameter("oid_Viagem_Internacional"));
      ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
      ed.setDT_Viagem_Internacional(request.getParameter("FT_DT_Viagem_Internacional"));
      ed.setHR_Viagem_Internacional(request.getParameter("FT_HR_Viagem_Internacional"));

      String VL_Total_Frete = request.getParameter("FT_VL_Total_Frete");
      if (String.valueOf(VL_Total_Frete) != null &&
        !String.valueOf(VL_Total_Frete).equals("") &&
        !String.valueOf(VL_Total_Frete).equals("null")){
        ed.setVL_Total_Frete(new Double(VL_Total_Frete).doubleValue());
      }

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null &&
        !String.valueOf(VL_Nota_Fiscal).equals("") &&
        !String.valueOf(VL_Nota_Fiscal).equals("null")){
        ed.setVL_Nota_Fiscal(new Double(VL_Nota_Fiscal).doubleValue());
      }

      String VL_Seguro = request.getParameter("FT_VL_Seguro");
      if (String.valueOf(VL_Seguro) != null &&
        !String.valueOf(VL_Seguro).equals("") &&
        !String.valueOf(VL_Seguro).equals("null")){
        ed.setVL_Seguro(new Double(VL_Seguro).doubleValue());
      }

      String VL_Peso = request.getParameter("FT_VL_Peso");
      if (String.valueOf(VL_Peso) != null &&
        !String.valueOf(VL_Peso).equals("") &&
        !String.valueOf(VL_Peso).equals("null")){
        ed.setVL_Peso(new Double(VL_Peso).doubleValue());
      }

      String VL_Peso_Cubado = request.getParameter("FT_VL_Peso_Cubado");
      if (String.valueOf(VL_Peso_Cubado) != null &&
        !String.valueOf(VL_Peso_Cubado).equals("") &&
        !String.valueOf(VL_Peso_Cubado).equals("null")){
        ed.setVL_Peso_Cubado(new Double(VL_Peso_Cubado).doubleValue());
      }

      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null &&
        !String.valueOf(NR_Volumes).equals("") &&
        !String.valueOf(NR_Volumes).equals("null")){
        ed.setNR_Volumes(new Long(NR_Volumes).longValue());
      }

      ed.setNM_Embalagem(request.getParameter("FT_NM_Embalagem"));
      
      ed.setNCM(request.getParameter("FT_NCM"));

      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      ed.setTX_Observacao2(request.getParameter("FT_TX_Observacao2"));
      ed.setTX_Observacao3(request.getParameter("FT_TX_Observacao3"));
      ed.setTX_Observacao4(request.getParameter("FT_TX_Observacao4"));
      ed.setTX_Observacao5(request.getParameter("FT_TX_Observacao5"));
      ed.setTX_Observacao6(request.getParameter("FT_TX_Observacao6"));
      ed.setTX_Observacao7(request.getParameter("FT_TX_Observacao7"));
      ed.setTX_Observacao8(request.getParameter("FT_TX_Observacao8"));
      ed.setTX_Observacao9(request.getParameter("FT_TX_Observacao9"));
      ed.setTX_Observacao10(request.getParameter("FT_TX_Observacao10"));
      ed.setTX_Observacao11(request.getParameter("FT_TX_Observacao11"));
      ed.setTX_Observacao12(request.getParameter("FT_TX_Observacao12"));
      ed.setTX_Observacao13(request.getParameter("FT_TX_Observacao13"));
      ed.setTX_Observacao14(request.getParameter("FT_TX_Observacao14"));
      ed.setTX_Observacao15(request.getParameter("FT_TX_Observacao15"));
      ed.setTX_Observacao16(request.getParameter("FT_TX_Observacao16"));
      ed.setTX_Observacao17(request.getParameter("FT_TX_Observacao17"));
      ed.setTX_Observacao18(request.getParameter("FT_TX_Observacao18"));

      ed.setNM_Documento1(request.getParameter("FT_TX_Documentos"));
      ed.setNM_Documento2(request.getParameter("FT_TX_Documentos2"));
      ed.setNM_Documento3(request.getParameter("FT_TX_Documentos3"));
      ed.setNM_Documento4(request.getParameter("FT_TX_Documentos4"));
      ed.setNM_Documento5(request.getParameter("FT_TX_Documentos5"));
      ed.setNM_Documento6(request.getParameter("FT_TX_Documentos6"));

      Viagem_Internacionalrn.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
exc.printStackTrace();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Viagem_InternacionalRN Viagem_Internacionalrn = new Viagem_InternacionalRN();
      Viagem_InternacionalED ed = new Viagem_InternacionalED();

      ed.setOID_Viagem_Internacional(request.getParameter("oid_Viagem_Internacional"));
      ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));

      Viagem_Internacionalrn.deleta(ed);
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

  public ArrayList Viagem_Internacional_Lista(HttpServletRequest request)throws Excecoes{

      Viagem_InternacionalED ed = new Viagem_InternacionalED();

      String OID_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
      if (String.valueOf(OID_Manifesto_Internacional) != null &&
          !String.valueOf(OID_Manifesto_Internacional).equals("") &&
          !String.valueOf(OID_Manifesto_Internacional).equals("null")){
      ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
      }
      ed.setDT_Viagem_Internacional(request.getParameter("FT_DT_Viagem_Internacional"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));

      String nr_Conhecimento_Internacional = request.getParameter("FT_NR_Conhecimento");

      if (String.valueOf(nr_Conhecimento_Internacional) != null &&
        !String.valueOf(nr_Conhecimento_Internacional).equals("") &&
        !String.valueOf(nr_Conhecimento_Internacional).equals("null")){
        ed.setNR_Conhecimento_Internacional(new Long(nr_Conhecimento_Internacional).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }

      return new Viagem_InternacionalRN().lista(ed);

  }

  public Viagem_InternacionalED getByRecord(HttpServletRequest request)throws Excecoes{

      Viagem_InternacionalED ed = new Viagem_InternacionalED();

      String oid_Viagem_Internacional = request.getParameter("oid_Viagem_Internacional");
      if (String.valueOf(oid_Viagem_Internacional) != null &&
          !String.valueOf(oid_Viagem_Internacional).equals("") &&
          !String.valueOf(oid_Viagem_Internacional).equals("null")){
        ed.setOID_Viagem_Internacional(request.getParameter("oid_Viagem_Internacional"));
      }

      return new Viagem_InternacionalRN().getByRecord(ed);

  }

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Viagem_InternacionalRN Viagem_Internacionalrn = new Viagem_InternacionalRN();
      Viagem_InternacionalED ed = new Viagem_InternacionalED();

      //request em cima dos campos dos forms html
      
      ed.setDM_Capa_Mic(request.getParameter("FT_DM_Capa_Mic"));

      ed.setDT_Viagem_Internacional(request.getParameter("FT_DT_Viagem_Internacional"));
      ed.setHR_Viagem_Internacional(request.getParameter("FT_HR_Viagem_Internacional"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));
      ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
      ed.setOID_Conhecimento_Internacional(request.getParameter("oid_Conhecimento_Internacional"));

      String OID_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(OID_Unidade) != null &&
        !String.valueOf(OID_Unidade).equals("") &&
        !String.valueOf(OID_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(OID_Unidade).longValue());
      }

      String nr_Conhecimento_Internacional = request.getParameter("FT_NR_Conhecimento");
      if (String.valueOf(nr_Conhecimento_Internacional) != null &&
        !String.valueOf(nr_Conhecimento_Internacional).equals("") &&
        !String.valueOf(nr_Conhecimento_Internacional).equals("null")){
        ed.setNR_Conhecimento_Internacional(new Long(nr_Conhecimento_Internacional).longValue());
      }
//// // System.out.println("E1");
      String VL_Entrega = request.getParameter("FT_VL_Entrega");
      if (String.valueOf(VL_Entrega) != null &&
        !String.valueOf(VL_Entrega).equals("") &&
        !String.valueOf(VL_Entrega).equals("null")){
        ed.setVL_Entrega(new Double(VL_Entrega).doubleValue());
      }
//// // System.out.println("E2");

      String oid_Pessoa_Entregadora = request.getParameter("oid_Pessoa_Entregadora");
      if (String.valueOf(oid_Pessoa_Entregadora) != null &&
        !String.valueOf(oid_Pessoa_Entregadora).equals("") &&
        !String.valueOf(oid_Pessoa_Entregadora).equals("null")){
        ed.setOID_Pessoa_Entregadora(oid_Pessoa_Entregadora);
      }
//// // System.out.println("E3");

      String VL_Total_Frete = request.getParameter("FT_VL_Total_Frete");
      if (String.valueOf(VL_Total_Frete) != null &&
        !String.valueOf(VL_Total_Frete).equals("") &&
        !String.valueOf(VL_Total_Frete).equals("null")){
        ed.setVL_Total_Frete(new Double(VL_Total_Frete).doubleValue());
      }
//// // System.out.println("E4");

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null &&
        !String.valueOf(VL_Nota_Fiscal).equals("") &&
        !String.valueOf(VL_Nota_Fiscal).equals("null")){
        ed.setVL_Nota_Fiscal(new Double(VL_Nota_Fiscal).doubleValue());
      }
//// // System.out.println("E5");

      String VL_Seguro = request.getParameter("FT_VL_Seguro");
      if (String.valueOf(VL_Seguro) != null &&
        !String.valueOf(VL_Seguro).equals("") &&
        !String.valueOf(VL_Seguro).equals("null")){
        ed.setVL_Seguro(new Double(VL_Seguro).doubleValue());
      }

// // System.out.println("E7"+request.getParameter("FT_VL_Peso"));
      String VL_Peso = request.getParameter("FT_VL_Peso");
      if (String.valueOf(VL_Peso) != null &&
        !String.valueOf(VL_Peso).equals("") &&
        !String.valueOf(VL_Peso).equals("null")){
        ed.setVL_Peso(new Double(VL_Peso).doubleValue());
      }
//// // System.out.println("E7");

      String VL_Peso_Cubado = request.getParameter("FT_VL_Peso_Cubado");
      if (String.valueOf(VL_Peso_Cubado) != null &&
        !String.valueOf(VL_Peso_Cubado).equals("") &&
        !String.valueOf(VL_Peso_Cubado).equals("null")){
        ed.setVL_Peso_Cubado(new Double(VL_Peso_Cubado).doubleValue());
      }
// // System.out.println("E8");

      String NR_Volumes = request.getParameter("FT_NR_Volumes");
// // System.out.println(NR_Volumes);

      if (String.valueOf(NR_Volumes) != null &&
        !String.valueOf(NR_Volumes).equals("") &&
        !String.valueOf(NR_Volumes).equals("null")){
        ed.setNR_Volumes(new Long(NR_Volumes).longValue());
      }
//// // System.out.println("E9");

      ed.setNM_Embalagem(request.getParameter("FT_NM_Embalagem"));
//// // System.out.println("E10");
      
      ed.setNCM(request.getParameter("FT_NCM"));

      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      ed.setTX_Observacao2(request.getParameter("FT_TX_Observacao2"));
      ed.setTX_Observacao3(request.getParameter("FT_TX_Observacao3"));
      ed.setTX_Observacao4(request.getParameter("FT_TX_Observacao4"));
      ed.setTX_Observacao5(request.getParameter("FT_TX_Observacao5"));
      ed.setTX_Observacao6(request.getParameter("FT_TX_Observacao6"));
      ed.setTX_Observacao7(request.getParameter("FT_TX_Observacao7"));
      ed.setTX_Observacao8(request.getParameter("FT_TX_Observacao8"));
      ed.setTX_Observacao9(request.getParameter("FT_TX_Observacao9"));
      ed.setTX_Observacao10(request.getParameter("FT_TX_Observacao10"));
      ed.setTX_Observacao11(request.getParameter("FT_TX_Observacao11"));
      ed.setTX_Observacao12(request.getParameter("FT_TX_Observacao12"));
      ed.setTX_Observacao13(request.getParameter("FT_TX_Observacao13"));
      ed.setTX_Observacao14(request.getParameter("FT_TX_Observacao14"));
      ed.setTX_Observacao15(request.getParameter("FT_TX_Observacao15"));
      ed.setTX_Observacao16(request.getParameter("FT_TX_Observacao16"));
      ed.setTX_Observacao17(request.getParameter("FT_TX_Observacao17"));
      ed.setTX_Observacao18(request.getParameter("FT_TX_Observacao18"));

      ed.setNM_Documento1(request.getParameter("FT_TX_Documentos"));
      ed.setNM_Documento2(request.getParameter("FT_TX_Documentos2"));
      ed.setNM_Documento3(request.getParameter("FT_TX_Documentos3"));
      ed.setNM_Documento4(request.getParameter("FT_TX_Documentos4"));
      ed.setNM_Documento5(request.getParameter("FT_TX_Documentos5"));
      ed.setNM_Documento6(request.getParameter("FT_TX_Documentos6"));
//// // System.out.println("E11");

      Viagem_Internacionalrn.inclui(ed);
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

  public void geraViagem_Internacional(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    Viagem_InternacionalED ed = new Viagem_InternacionalED();

      //ed.setDM_SO(request.getParameter("so"));

      String OID_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
      if (String.valueOf(OID_Manifesto_Internacional) != null &&
          !String.valueOf(OID_Manifesto_Internacional).equals("") &&
          !String.valueOf(OID_Manifesto_Internacional).equals("null")){
      ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
      }
      ed.setNR_Copia(1);
      String NR_Copia = request.getParameter("FT_NR_Copia");
      if (String.valueOf(NR_Copia) != null &&
        !String.valueOf(NR_Copia).equals("") &&
        !String.valueOf(NR_Copia).equals("null")){
        ed.setNR_Copia(new Long(NR_Copia).longValue());
      }
      ed.setNR_Imprimir(1);
      String NR_Imprimir = request.getParameter("FT_NR_Imprimir");
      if (String.valueOf(NR_Imprimir) != null &&
        !String.valueOf(NR_Imprimir).equals("") &&
        !String.valueOf(NR_Imprimir).equals("null")){
        ed.setNR_Imprimir(new Long(NR_Imprimir).longValue());
      }
      String DM_Impressora = request.getParameter("FT_DM_Impressora");
      if (String.valueOf(DM_Impressora) != null &&
      		!String.valueOf(DM_Impressora).equals("") &&
			!String.valueOf(DM_Impressora).equals("null")){
      	ed.setDM_Impressora(DM_Impressora);
      }
      
      String carimbo = request.getParameter("FT_DM_Carimbo");
      if (carimbo != null && !carimbo.equals("") &&  !carimbo.equals("null")){
        ed.setDM_SO(carimbo);
      }


    Viagem_InternacionalRN geRN = new Viagem_InternacionalRN();
    byte[] b = geRN.geraViagem_Internacional(ed,Response);
    geRN = null;

  }
  public void geraViagem_Internacional_Antigo(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
      Viagem_InternacionalED ed = new Viagem_InternacionalED();

        ed.setDM_SO(request.getParameter("so"));

        String OID_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
        if (String.valueOf(OID_Manifesto_Internacional) != null &&
            !String.valueOf(OID_Manifesto_Internacional).equals("") &&
            !String.valueOf(OID_Manifesto_Internacional).equals("null")){
        ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
        }
        ed.setNR_Copia(1);
        String NR_Copia = request.getParameter("FT_NR_Copia");
        if (String.valueOf(NR_Copia) != null &&
          !String.valueOf(NR_Copia).equals("") &&
          !String.valueOf(NR_Copia).equals("null")){
          ed.setNR_Copia(new Long(NR_Copia).longValue());
        }
        ed.setNR_Imprimir(1);
        String NR_Imprimir = request.getParameter("FT_NR_Imprimir");
        if (String.valueOf(NR_Imprimir) != null &&
          !String.valueOf(NR_Imprimir).equals("") &&
          !String.valueOf(NR_Imprimir).equals("null")){
          ed.setNR_Imprimir(new Long(NR_Imprimir).longValue());
        }
        String DM_Impressora = request.getParameter("FT_DM_Impressora");
        if (String.valueOf(DM_Impressora) != null &&
        		!String.valueOf(DM_Impressora).equals("") &&
  			!String.valueOf(DM_Impressora).equals("null")){
        	ed.setDM_Impressora(DM_Impressora);
        }


      Viagem_InternacionalRN geRN = new Viagem_InternacionalRN();

      new EnviaPDF().enviaBytes(request,Response,geRN.geraViagem_Internacional_Antigo(ed));

    }
  
  public Viagem_InternacionalED getQtdeEmbarcada(String mic, String crt)throws Excecoes{

      Viagem_InternacionalED ed = new Viagem_InternacionalED();

      ed.setOID_Manifesto_Internacional(mic);
      ed.setOID_Conhecimento_Internacional(crt);

      return new Viagem_InternacionalRN().getQtdeEmbarcada(ed);

  }
  
  public void geraViagem_Nacional(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
      Viagem_InternacionalED ed = new Viagem_InternacionalED();

        //ed.setDM_SO(request.getParameter("so"));

        String OID_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
        if (String.valueOf(OID_Manifesto_Internacional) != null &&
            !String.valueOf(OID_Manifesto_Internacional).equals("") &&
            !String.valueOf(OID_Manifesto_Internacional).equals("null")){
        ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
        }
        ed.setNR_Copia(1);
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (JavaUtil.doValida(oid_Unidade)){
          ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }
        String NR_Manifesto_Internacional = request.getParameter("FT_NR_Manifesto_Internacional");
        if (JavaUtil.doValida(String.valueOf(NR_Manifesto_Internacional))){
            ed.setNR_Manifesto_Internacional(Long.parseLong(request.getParameter("FT_NR_Manifesto_Internacional")));
        }
        String NR_Manifesto_Internacional_Final = request.getParameter("FT_NR_Manifesto_Internacional_Final");
        if (JavaUtil.doValida(String.valueOf(NR_Manifesto_Internacional_Final))){
            ed.setNR_Manifesto_Internacional_Final(Long.parseLong(request.getParameter("FT_NR_Manifesto_Internacional_Final")));
        }
        ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao"));
        ed.setDT_Emissao_Final(request.getParameter("FT_DT_Fim"));
        
        String DM_Impressora = request.getParameter("FT_DM_Impressora");
        if (String.valueOf(DM_Impressora) != null &&
        		!String.valueOf(DM_Impressora).equals("") &&
  			!String.valueOf(DM_Impressora).equals("null")){
        	ed.setDM_Impressora(DM_Impressora);
        }
        
        String carimbo = request.getParameter("FT_DM_Carimbo");
        if (carimbo != null && !carimbo.equals("") &&  !carimbo.equals("null")){
          ed.setDM_SO(carimbo);
        }


      Viagem_InternacionalRN geRN = new Viagem_InternacionalRN();

      new EnviaPDF().enviaBytes(request,Response,geRN.geraViagem_Nacional(ed,Response));

    }
  
  public void geraViagem_Internacional_Multiplo(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
      Viagem_InternacionalED ed = new Viagem_InternacionalED();

        String OID_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
        if (JavaUtil.doValida(String.valueOf(OID_Manifesto_Internacional))){
            ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
        }
        String NR_Manifesto_Internacional = request.getParameter("FT_NR_Manifesto_Internacional");
        if (JavaUtil.doValida(String.valueOf(NR_Manifesto_Internacional))){
            ed.setNR_Manifesto_Internacional(Long.parseLong(request.getParameter("FT_NR_Manifesto_Internacional")));
        }
        String NR_Manifesto_Internacional_Final = request.getParameter("FT_NR_Manifesto_Internacional_Final");
        if (JavaUtil.doValida(String.valueOf(NR_Manifesto_Internacional_Final))){
            ed.setNR_Manifesto_Internacional_Final(Long.parseLong(request.getParameter("FT_NR_Manifesto_Internacional_Final")));
        }
        ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao"));
        ed.setDT_Emissao_Final(request.getParameter("FT_DT_Fim"));
        String oid_Unidade = request.getParameter("oid_Unidade_Origem");
        if (JavaUtil.doValida(oid_Unidade)){
          ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }
        
        ed.setDM_Imprime1(request.getParameter("FT_DM_imprime1"));
        ed.setDM_Imprime2(request.getParameter("FT_DM_imprime2"));
        ed.setDM_Imprime3(request.getParameter("FT_DM_imprime3"));
        ed.setDM_Imprime4(request.getParameter("FT_DM_imprime4"));
        ed.setDM_Imprime5(request.getParameter("FT_DM_imprime5"));
        ed.setDM_ImprimeCP(request.getParameter("FT_DM_imprimeCP"));
// System.out.println("1:"+request.getParameter("FT_DM_imprime1"));
// System.out.println("2:"+request.getParameter("FT_DM_imprime2"));
// System.out.println("3:"+request.getParameter("FT_DM_imprime3"));
// System.out.println("CP:"+request.getParameter("FT_DM_imprimeCP"));
        
        ed.setNR_Imprimir(1);
        String NR_Imprimir = request.getParameter("FT_NR_Imprimir");
        if (String.valueOf(NR_Imprimir) != null &&
          !String.valueOf(NR_Imprimir).equals("") &&
          !String.valueOf(NR_Imprimir).equals("null")){
          ed.setNR_Imprimir(new Long(NR_Imprimir).longValue());
        }
        
        String carimbo = request.getParameter("FT_DM_Carimbo");
        if (carimbo != null && !carimbo.equals("") &&  !carimbo.equals("null")){
          ed.setDM_SO(carimbo);
        }

      Viagem_InternacionalRN geRN = new Viagem_InternacionalRN();
      geRN.geraViagem_Internacional_Multiplo(ed,Response);
      geRN = null;

    }


}
