package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Plano_ViagemED;
import com.master.rn.Plano_ViagemRN;
import com.master.util.Excecoes;




/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class man106Bean {




  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Plano_ViagemRN Plano_Viagemrn = new Plano_ViagemRN();
      Plano_ViagemED ed = new Plano_ViagemED();

      String oid_Plano_Viagem = request.getParameter("oid_Plano_Viagem");
      if (String.valueOf(oid_Plano_Viagem) != null &&
          !String.valueOf(oid_Plano_Viagem).equals("") &&
          !String.valueOf(oid_Plano_Viagem).equals("null")){
        ed.setOID_Plano_Viagem(request.getParameter("oid_Plano_Viagem"));
      }

      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
      ed.setDT_Plano_Viagem(request.getParameter("FT_DT_Plano_Viagem"));
      ed.setHR_Plano_Viagem(request.getParameter("FT_HR_Plano_Viagem"));

      String DM_Plano_Viagem = request.getParameter("FT_DM_Plano_Viagem");
      if (String.valueOf(DM_Plano_Viagem) != null &&
          !String.valueOf(DM_Plano_Viagem).equals("null") &&
          !String.valueOf(DM_Plano_Viagem).equals("")){
          ed.setDM_Plano_Viagem(request.getParameter("FT_DM_Plano_Viagem"));
      }

      ed.setNM_Origem(" ");
      String NM_Origem = request.getParameter("FT_NM_Origem");
      if (String.valueOf(NM_Origem) != null &&
          !String.valueOf(NM_Origem).equals("null") &&
          !String.valueOf(NM_Origem).equals("")){
          ed.setNM_Origem(request.getParameter("FT_NM_Origem"));
      }

      ed.setNM_Destino(" ");
      String NM_Destino = request.getParameter("FT_NM_Destino");
      if (String.valueOf(NM_Destino) != null &&
          !String.valueOf(NM_Destino).equals("null") &&
          !String.valueOf(NM_Destino).equals("")){
          ed.setNM_Destino(request.getParameter("FT_NM_Destino"));
      }

      ed.setTX_Observacao(" ");
      String TX_Observacao = request.getParameter("FT_TX_Observacao");
      if (String.valueOf(TX_Observacao) != null &&
          !String.valueOf(TX_Observacao).equals("null") &&
          !String.valueOf(TX_Observacao).equals("")){
          ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      }
          //// System.out.println("man 10" );



      Plano_Viagemrn.altera(ed);
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
      Plano_ViagemRN Plano_Viagemrn = new Plano_ViagemRN();
      Plano_ViagemED ed = new Plano_ViagemED();

      ed.setOID_Plano_Viagem(request.getParameter("oid_Plano_Viagem"));

      Plano_Viagemrn.deleta(ed);
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

  public ArrayList Plano_Viagem_Lista(HttpServletRequest request)throws Excecoes{

      Plano_ViagemED ed = new Plano_ViagemED();

      String OID_Manifesto = request.getParameter("oid_Manifesto");
      if (String.valueOf(OID_Manifesto) != null &&
          !String.valueOf(OID_Manifesto).equals("") &&
          !String.valueOf(OID_Manifesto).equals("null")){
      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
      }
      String OID_Coleta = request.getParameter("oid_Coleta");
      if (String.valueOf(OID_Coleta) != null &&
          !String.valueOf(OID_Coleta).equals("") &&
          !String.valueOf(OID_Coleta).equals("null")){
      ed.setOID_Coleta(new Long(request.getParameter("oid_Coleta")).longValue());
      }


      return new Plano_ViagemRN().lista(ed);

  }

  public Plano_ViagemED getByRecord(HttpServletRequest request)throws Excecoes{

      Plano_ViagemED ed = new Plano_ViagemED();

      String oid_Plano_Viagem = request.getParameter("oid_Plano_Viagem");
      if (String.valueOf(oid_Plano_Viagem) != null &&
          !String.valueOf(oid_Plano_Viagem).equals("") &&
          !String.valueOf(oid_Plano_Viagem).equals("null")){
        ed.setOID_Plano_Viagem(request.getParameter("oid_Plano_Viagem"));
      }

      return new Plano_ViagemRN().getByRecord(ed);

  }


  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Plano_ViagemRN Plano_Viagemrn = new Plano_ViagemRN();
      Plano_ViagemED ed = new Plano_ViagemED();

      //request em cima dos campos dos forms html

      ed.setDT_Plano_Viagem(request.getParameter("FT_DT_Plano_Viagem"));
      ed.setHR_Plano_Viagem(request.getParameter("FT_HR_Plano_Viagem"));

      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));


      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));

      String DM_Plano_Viagem = request.getParameter("FT_DM_Plano_Viagem");
      if (String.valueOf(DM_Plano_Viagem) != null &&
          !String.valueOf(DM_Plano_Viagem).equals("null") &&
          !String.valueOf(DM_Plano_Viagem).equals("")){
          ed.setDM_Plano_Viagem(request.getParameter("FT_DM_Plano_Viagem"));
      }

      ed.setNM_Origem(" ");
      String NM_Origem = request.getParameter("FT_NM_Origem");
      if (String.valueOf(NM_Origem) != null &&
          !String.valueOf(NM_Origem).equals("null") &&
          !String.valueOf(NM_Origem).equals("")){
          ed.setNM_Origem(request.getParameter("FT_NM_Origem"));
      }

      ed.setNM_Destino(" ");
      String NM_Destino = request.getParameter("FT_NM_Destino");
      if (String.valueOf(NM_Destino) != null &&
          !String.valueOf(NM_Destino).equals("null") &&
          !String.valueOf(NM_Destino).equals("")){
          ed.setNM_Destino(request.getParameter("FT_NM_Destino"));
      }


      ed.setTX_Observacao("    ");
      String TX_Observacao = request.getParameter("FT_TX_Observacao");
      if (String.valueOf(TX_Observacao) != null &&
          !String.valueOf(TX_Observacao).equals("null") &&
          !String.valueOf(TX_Observacao).equals("")){
          ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      }

      String OID_Coleta = request.getParameter("oid_Coleta");
      if (String.valueOf(OID_Coleta) != null &&
          !String.valueOf(OID_Coleta).equals("") &&
          !String.valueOf(OID_Coleta).equals("null")){
        ed.setOID_Coleta (new Long (request.getParameter ("oid_Coleta")).
                          longValue ());
      }

      Plano_Viagemrn.inclui(ed);
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

}
