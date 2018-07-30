package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Documento_Lote_FaturamentoED;
import com.master.rn.Documento_Lote_FaturamentoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Documento_Lote_FaturamentoBean {


  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Documento_Lote_FaturamentoRN Documento_Lote_Faturamentorn = new Documento_Lote_FaturamentoRN();
      Documento_Lote_FaturamentoED ed = new Documento_Lote_FaturamentoED();

      // System.out.println("man -1" );

      ed.setOID_Documento_Lote_Faturamento(request.getParameter("oid_Documento_Lote_Faturamento"));
      ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());

          // System.out.println("2 " );

      String oid_Conhecimento = request.getParameter("oid_Conhecimento");
      if (String.valueOf(oid_Conhecimento) != null &&
          !String.valueOf(oid_Conhecimento).equals("null") &&
          !String.valueOf(oid_Conhecimento).equals("")){
          ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
      }

          // System.out.println("4 " );


      ed.setOID_Pessoa(" ");
      String OID_Pessoa = request.getParameter("oid_Pessoa");
      if (String.valueOf(OID_Pessoa) != null &&
          !String.valueOf(OID_Pessoa).equals("null") &&
          !String.valueOf(OID_Pessoa).equals("")){
          ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      }


          // System.out.println("99 " );


      Documento_Lote_Faturamentorn.altera(ed);
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



  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Documento_Lote_FaturamentoRN Documento_Lote_Faturamentorn = new Documento_Lote_FaturamentoRN();
      Documento_Lote_FaturamentoED ed = new Documento_Lote_FaturamentoED();

      ed.setOID_Documento_Lote_Faturamento(request.getParameter("oid_Documento_Lote_Faturamento"));
      String oid_Conhecimento = request.getParameter("oid_Conhecimento");
      if (String.valueOf(oid_Conhecimento) != null &&
          !String.valueOf(oid_Conhecimento).equals("null") &&
          !String.valueOf(oid_Conhecimento).equals("")){
          ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
          ed.setNR_Documento(request.getParameter("FT_NR_Conhecimento"));
          ed.setDM_Tipo_Documento("C");
      }

      Documento_Lote_Faturamentorn.deleta(ed);
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

  public ArrayList Documento_Lote_Faturamento_Lista(HttpServletRequest request)throws Excecoes{

      Documento_Lote_FaturamentoED ed = new Documento_Lote_FaturamentoED();
// System.out.println("list 1 ");
      ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());
      ed.setDM_Tipo_Documento(request.getParameter("FT_DM_Tipo_Documento"));
// System.out.println("5 ");

      return new Documento_Lote_FaturamentoRN().lista(ed);

  }


  public Documento_Lote_FaturamentoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Documento_Lote_FaturamentoRN Documento_Lote_Faturamentorn = new Documento_Lote_FaturamentoRN();
      Documento_Lote_FaturamentoED ed = new Documento_Lote_FaturamentoED();

          // System.out.println("lote 0 ->> " + request.getParameter("oid_Lote_Faturamento") );

      ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());

      String oid_Conhecimento = request.getParameter("oid_Conhecimento");
      if (String.valueOf(oid_Conhecimento) != null &&
          !String.valueOf(oid_Conhecimento).equals("null") &&
          !String.valueOf(oid_Conhecimento).equals("")){
          ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
          ed.setNR_Documento(request.getParameter("FT_NR_Conhecimento"));
          ed.setDM_Tipo_Documento("C");
      }

      String oid_Ordem_Frete_Terceiro = request.getParameter("oid_Ordem_Frete_Terceiro");
      if (String.valueOf(oid_Ordem_Frete_Terceiro) != null &&
          !String.valueOf(oid_Ordem_Frete_Terceiro).equals("null") &&
          !String.valueOf(oid_Ordem_Frete_Terceiro).equals("")){
          ed.setOID_Ordem_Frete_Terceiro(request.getParameter("oid_Ordem_Frete_Terceiro"));
          ed.setNR_Documento(request.getParameter("FT_NR_Ordem_Frete_Terceiro"));
          ed.setDM_Tipo_Documento("O");
      }

      String oid_Processo = request.getParameter("oid_Processo");

          // System.out.println("lote 1 iu ->> " + request.getParameter("oid_Processo") );

      if (String.valueOf(oid_Processo) != null &&
          !String.valueOf(oid_Processo).equals("null") &&
          !String.valueOf(oid_Processo).equals("")){
          ed.setOID_Processo(request.getParameter("oid_Processo"));
          ed.setNR_Documento(request.getParameter("FT_NR_Processo"));
          ed.setDM_Tipo_Documento("P");
      }

      String FT_VL_Faturado=request.getParameter("FT_VL_Faturado");
      if (String.valueOf(FT_VL_Faturado) != null &&
          !String.valueOf(FT_VL_Faturado).equals("null") &&
          !String.valueOf(FT_VL_Faturado).equals("")){
            ed.setVL_Faturado(new Double(request.getParameter("FT_VL_Faturado")).doubleValue());
      }


      // System.out.println("lote iu 99 ->> " + request.getParameter("oid_Lote_Faturamento") );

      return Documento_Lote_Faturamentorn.inclui(ed);
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


  public Documento_Lote_FaturamentoED inclui_Cto_Filtro(HttpServletRequest request)throws Excecoes{

    try{
      Documento_Lote_FaturamentoRN Documento_Lote_Faturamentorn = new Documento_Lote_FaturamentoRN();
      Documento_Lote_FaturamentoED ed = new Documento_Lote_FaturamentoED();

          // System.out.println("lote 0 ->> " + request.getParameter("oid_Lote_Faturamento") );

      ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
          !String.valueOf(oid_Unidade).equals("null") &&
          !String.valueOf(oid_Unidade).equals("")){
          ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      }

      String oid_Modal = request.getParameter("oid_Modal");
      if (String.valueOf(oid_Modal) != null &&
          !String.valueOf(oid_Modal).equals("null") &&
          !String.valueOf(oid_Modal).equals("")){
          ed.setOID_Modal(new Long(request.getParameter("oid_Modal")).longValue());
      }

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (String.valueOf(oid_Pessoa) != null &&
          !String.valueOf(oid_Pessoa).equals("null") &&
          !String.valueOf(oid_Pessoa).equals("")){
          ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      }

      String DM_Tipo_Documento = request.getParameter("FT_DM_Tipo_Documento");
      if (String.valueOf(DM_Tipo_Documento) != null &&
          !String.valueOf(DM_Tipo_Documento).equals("null") &&
          !String.valueOf(DM_Tipo_Documento).equals("")){
          ed.setDM_Tipo_Documento(DM_Tipo_Documento);
      }

      String NR_Conhecimento_Inicial = request.getParameter("FT_NR_Conhecimento_Inicial");
      if (String.valueOf(NR_Conhecimento_Inicial) != null && !String.valueOf(NR_Conhecimento_Inicial).equals("")){
        ed.setNR_Conhecimento_Inicial(new Long(NR_Conhecimento_Inicial).longValue());
      }

      String NR_Conhecimento_Final = request.getParameter("FT_NR_Conhecimento_Final");
      if (String.valueOf(NR_Conhecimento_Final) != null && !String.valueOf(NR_Conhecimento_Final).equals("")){
        ed.setNR_Conhecimento_Final(new Long(NR_Conhecimento_Final).longValue());
      }

      ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao_Inicial"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));

      // System.out.println("lote iu 99 ->> " + request.getParameter("oid_Lote_Faturamento") );

      return Documento_Lote_Faturamentorn.inclui_Cto_Filtro(ed);
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


  public Documento_Lote_FaturamentoED getByRecord(HttpServletRequest request)throws Excecoes{

    // System.out.println("getByRecord");

      Documento_Lote_FaturamentoED ed = new Documento_Lote_FaturamentoED();
      String oid_Documento_Lote_Faturamento = request.getParameter("oid_Documento_Lote_Faturamento");

      ed.setOID_Documento_Lote_Faturamento(request.getParameter("oid_Documento_Lote_Faturamento"));

      return new Documento_Lote_FaturamentoRN().getByRecord(ed);

  }

  public Documento_Lote_FaturamentoED consultaCto(String oid_Conhecimento, String oid_Lote_Faturamento)throws Excecoes{

      Documento_Lote_FaturamentoED ed = new Documento_Lote_FaturamentoED();
      ed.setOID_Conhecimento(oid_Conhecimento);
      ed.setOID_Lote_Faturamento(new Long(oid_Lote_Faturamento).longValue());

      return new Documento_Lote_FaturamentoRN().getByRecord(ed);

  }



}
