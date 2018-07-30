package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Comissao_AgenciamentoED;
import com.master.ed.Participacao_FreteED;
import com.master.rn.Comissao_AgenciamentoRN;
import com.master.rn.Participacao_FreteRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.rn.FaturamentoRN;
import com.master.ed.FaturamentoED;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Participacao_FreteBean {


  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Participacao_FreteRN Participacao_Fretern = new Participacao_FreteRN();
      Participacao_FreteED ed = new Participacao_FreteED();

         //// System.out.println("man -1" );

      ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Vencimento(request.getParameter("FT_DT_Vencimento"));
          // System.out.println("man " );



      Participacao_Fretern.altera(ed);
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
      Participacao_FreteRN Participacao_Fretern = new Participacao_FreteRN();
      Participacao_FreteED ed = new Participacao_FreteED();

      ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());

      Participacao_Fretern.deleta(ed);
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

  public ArrayList Participacao_Frete_Lista(HttpServletRequest request)throws Excecoes{

      Participacao_FreteED ed = new Participacao_FreteED();
//// System.out.println("1 ");

      String oid_Participacao_Frete = request.getParameter("oid_Participacao_Frete");
      if (String.valueOf(oid_Participacao_Frete) != null &&
          !String.valueOf(oid_Participacao_Frete).equals("") &&
          !String.valueOf(oid_Participacao_Frete).equals("null")){
          ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());
      }

      String NR_Duplicata = request.getParameter("FT_NR_Duplicata");
      if (String.valueOf(NR_Duplicata) != null &&
          !String.valueOf(NR_Duplicata).equals("") &&
          !String.valueOf(NR_Duplicata).equals("null")){
          ed.setNR_Duplicata(new Long(request.getParameter("FT_NR_Duplicata")).longValue());
      }

       String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (String.valueOf(DT_Emissao_Inicial) != null &&
            !String.valueOf(DT_Emissao_Inicial).equals("null") &&
            !String.valueOf(DT_Emissao_Inicial).equals("")){
            ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao_Inicial"));
        }
       String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (String.valueOf(DT_Emissao_Final) != null &&
            !String.valueOf(DT_Emissao_Final).equals("null") &&
            !String.valueOf(DT_Emissao_Final).equals("")){
            ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
        }

       String OID_Pessoa = request.getParameter("oid_Pessoa");
        if (String.valueOf(OID_Pessoa) != null &&
            !String.valueOf(OID_Pessoa).equals("null") &&
            !String.valueOf(OID_Pessoa).equals("")){
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
        }

       String DM_Situacao = request.getParameter("DM_Situacao");
        if (String.valueOf(DM_Situacao) != null &&
            !String.valueOf(DM_Situacao).equals("null") &&
            !String.valueOf(DM_Situacao).equals("")){
            ed.setDM_Situacao(request.getParameter("DM_Situacao"));
        }
//// System.out.println("2 ");
      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }
//// System.out.println("5 ");

      return new Participacao_FreteRN().lista(ed);

  }


  public Participacao_FreteED inclui(HttpServletRequest request)throws Excecoes{

    try{

      // System.out.println("man 0" );

      Participacao_FreteRN Participacao_Fretern = new Participacao_FreteRN();
      Participacao_FreteED ed = new Participacao_FreteED();

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));

          // System.out.println("man 0" );

      ed.setDM_Situacao("I");

      ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Vencimento(request.getParameter("FT_DT_Vencimento"));
          // System.out.println("man 3" );

      ed.setDM_Tipo_Documento(request.getParameter("FT_DM_Tipo_Documento"));

      String VL_Cobranca = request.getParameter("FT_VL_Cobranca");
      if (String.valueOf(VL_Cobranca) != null &&
        !String.valueOf(VL_Cobranca).equals("") &&
        !String.valueOf(VL_Cobranca).equals("null")){
        ed.setVL_Cobranca(new Double(VL_Cobranca).doubleValue());
      }

      return Participacao_Fretern.inclui(ed);
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

  public Participacao_FreteED getByRecord(HttpServletRequest request)throws Excecoes{

    // System.out.println("lote Fat get by bea  2 ->>");
      Participacao_FreteED ed = new Participacao_FreteED();
      String oid_Participacao_Frete = request.getParameter("oid_Participacao_Frete");
      if (String.valueOf(oid_Participacao_Frete) != null &&
          !String.valueOf(oid_Participacao_Frete).equals("") &&
          !String.valueOf(oid_Participacao_Frete).equals("null")){
          ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());
      }

      return new Participacao_FreteRN().getByRecord(ed);

  }

  public Participacao_FreteED getByRecord(long oid_Participacao_Frete)throws Excecoes{

    // System.out.println("lote Fat get by bea  2 ->>");
      Participacao_FreteED ed = new Participacao_FreteED();
      ed.setOID_Participacao_Frete (oid_Participacao_Frete);
    
      return new Participacao_FreteRN().getByRecord(ed);

  }

  public byte[] imprime_Participacao_Frete(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
      Participacao_FreteED ed = new Participacao_FreteED();
      String oid_Participacao_Frete = request.getParameter("oid_Participacao_Frete");

      if (String.valueOf(oid_Participacao_Frete) != null &&
          !String.valueOf(oid_Participacao_Frete).equals("") &&
          !String.valueOf(oid_Participacao_Frete).equals("null")){
          ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());
      }
 // System.out.println("iu 4 ");

    Participacao_FreteRN geRN = new Participacao_FreteRN();

    return geRN.imprime_Participacao_Frete(ed);

  }

  public FaturamentoED geraFatura(HttpServletRequest request)throws Excecoes{

      FaturamentoED ed = new FaturamentoED();
      // System.out.println("geraFatura  incluiFatura ->> " + request.getParameter("oid_Unidade"));

      ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Vencimento(request.getParameter("FT_DT_Vencimento"));
      ed.setDT_Emissao_Inicial("");
      ed.setDT_Emissao_Final("");
      ed.setOID_Pessoa_Pagador(request.getParameter("oid_Pessoa"));
      //ed..setNM_Participacao_Frete(request.getParameter("oid_Participacao_Frete"));
      //ed.setNM_Participacao_Frete(request.getParameter("oid_Participacao_Frete"));
      if(JavaUtil.doValida(request.getParameter("oid_Participacao_Frete"))){
          //ed.setOid_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());
      }

      return new FaturamentoRN().geraFatura(ed);

  }


  public byte[] rel_Ctos_Participacao_Frete (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

      Participacao_FreteED ed = new Participacao_FreteED();
      String oid_Participacao_Frete = request.getParameter("oid_Participacao_Frete");
      ed.setOID_Participacao_Frete(0);

      if (String.valueOf(oid_Participacao_Frete) != null &&
          !String.valueOf(oid_Participacao_Frete).equals("") &&
          !String.valueOf(oid_Participacao_Frete).equals("null")){
          ed.setOID_Participacao_Frete(new Long(request.getParameter("oid_Participacao_Frete")).longValue());
      }
 // System.out.println("iu 4 ");

    Participacao_FreteRN geRN = new Participacao_FreteRN();

    String oid_Unidade = request.getParameter ("oid_Unidade");

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (Dt_Emissao_Final);
    }

    byte[] b = geRN.rel_Ctos_Participacao_Frete (ed);

    return b;

  }  
  }
