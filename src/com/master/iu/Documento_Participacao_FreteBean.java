package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import com.master.ed.Documento_Participacao_FreteED;
import com.master.rn.Documento_Participacao_FreteRN;
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

public class Documento_Participacao_FreteBean {


  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Documento_Participacao_FreteRN Documento_Participacao_Fretern = new Documento_Participacao_FreteRN();
      Documento_Participacao_FreteED ed = new Documento_Participacao_FreteED();

      // System.out.println("man -1" );

      ed.setOID_Documento_Participacao_Frete(request.getParameter("oid_Documento_Participacao_Frete"));
      ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());

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


      Documento_Participacao_Fretern.altera(ed);
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
      Documento_Participacao_FreteRN Documento_Participacao_Fretern = new Documento_Participacao_FreteRN();
      Documento_Participacao_FreteED ed = new Documento_Participacao_FreteED();

      ed.setOID_Documento_Participacao_Frete(request.getParameter("oid_Documento_Participacao_Frete"));

      Documento_Participacao_Fretern.deleta(ed);
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

  public ArrayList Documento_Participacao_Frete_Lista(HttpServletRequest request)throws Excecoes{

      Documento_Participacao_FreteED ed = new Documento_Participacao_FreteED();
// System.out.println("list 1 ");
      ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());
      ed.setDM_Tipo_Documento(request.getParameter("FT_DM_Tipo_Documento"));
// System.out.println("5 ");

      return new Documento_Participacao_FreteRN().lista(ed);

  }


  public Documento_Participacao_FreteED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Documento_Participacao_FreteRN Documento_Participacao_Fretern = new Documento_Participacao_FreteRN();
      Documento_Participacao_FreteED ed = new Documento_Participacao_FreteED();

          // System.out.println("lote 0 ->> " + request.getParameter("oid_Participacao_Frete") );

      ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());
      ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      String oid_Conhecimento = request.getParameter("oid_Conhecimento");
      if (String.valueOf(oid_Conhecimento) != null &&
          !String.valueOf(oid_Conhecimento).equals("null") &&
          !String.valueOf(oid_Conhecimento).equals("")){
          ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
          ed.setNR_Documento(request.getParameter("FT_NR_Conhecimento"));
          ed.setDM_Tipo_Documento("CTO");
      }

      String oid_Processo = request.getParameter("oid_Processo");
      if (String.valueOf(oid_Processo) != null &&
          !String.valueOf(oid_Processo).equals("null") &&
          !String.valueOf(oid_Processo).equals("")){
          ed.setOID_Processo(request.getParameter("oid_Processo"));
          ed.setNR_Documento(request.getParameter("FT_NR_Processo"));
          ed.setDM_Tipo_Documento("P");
      }

      // System.out.println("lote iu 99 ->> " + request.getParameter("oid_Participacao_Frete") );

      return Documento_Participacao_Fretern.inclui(ed);
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


  public Documento_Participacao_FreteED inclui_Cto_Filtro(HttpServletRequest request)throws Excecoes{

    try{
      Documento_Participacao_FreteRN Documento_Participacao_Fretern = new Documento_Participacao_FreteRN();
      Documento_Participacao_FreteED ed = new Documento_Participacao_FreteED();

          // System.out.println("lote 0 ->> " + request.getParameter("oid_Participacao_Frete") );

      ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());

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

      // System.out.println("lote iu 99 ->> " + request.getParameter("oid_Participacao_Frete") );

      return Documento_Participacao_Fretern.inclui_Cto_Filtro(ed);
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


  public Documento_Participacao_FreteED getByRecord(HttpServletRequest request)throws Excecoes{

    // System.out.println("getByRecord");

      Documento_Participacao_FreteED ed = new Documento_Participacao_FreteED();
      String oid_Documento_Participacao_Frete = request.getParameter("oid_Documento_Participacao_Frete");

      ed.setOID_Documento_Participacao_Frete(request.getParameter("oid_Documento_Participacao_Frete"));

      return new Documento_Participacao_FreteRN().getByRecord(ed);

  }

  public Documento_Participacao_FreteED consultaCto(String oid_Conhecimento, String oid_Participacao_Frete)throws Excecoes{

      Documento_Participacao_FreteED ed = new Documento_Participacao_FreteED();
      ed.setOID_Conhecimento(oid_Conhecimento);
      ed.setOID_Participacao_Frete(new Long(oid_Participacao_Frete).longValue());

      return new Documento_Participacao_FreteRN().getByRecord(ed);

  }



}
