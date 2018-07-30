package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Abastecimento_ViagemED;
import com.master.rn.Abastecimento_ViagemRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.ed.ManifestoED;
import com.master.rn.ManifestoRN;
import com.master.util.Mensagens;
import javax.servlet.http.HttpServletResponse;




/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Abastecimento_ViagemBean {




  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Abastecimento_ViagemRN Abastecimento_Viagemrn = new Abastecimento_ViagemRN();
      Abastecimento_ViagemED ed = new Abastecimento_ViagemED();

      String oid_Abastecimento_Viagem = request.getParameter("oid_Abastecimento_Viagem");
      if (String.valueOf(oid_Abastecimento_Viagem) != null &&
          !String.valueOf(oid_Abastecimento_Viagem).equals("") &&
          !String.valueOf(oid_Abastecimento_Viagem).equals("null")){
        ed.setOID_Abastecimento_Viagem(request.getParameter("oid_Abastecimento_Viagem"));
      }

      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
      ed.setDT_Abastecimento_Viagem(request.getParameter("FT_DT_Abastecimento_Viagem"));
      ed.setHR_Abastecimento_Viagem(request.getParameter("FT_HR_Abastecimento_Viagem"));

      String DM_Abastecimento_Viagem = request.getParameter("FT_DM_Abastecimento_Viagem");
      if (String.valueOf(DM_Abastecimento_Viagem) != null &&
          !String.valueOf(DM_Abastecimento_Viagem).equals("null") &&
          !String.valueOf(DM_Abastecimento_Viagem).equals("")){
          ed.setDM_Abastecimento_Viagem(request.getParameter("FT_DM_Abastecimento_Viagem"));
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

      // System.out.println("man 10" );

      String OID_Posto = request.getParameter("oid_Posto");
      if (String.valueOf(OID_Posto) != null &&
          !String.valueOf(OID_Posto).equals("") &&
          !String.valueOf(OID_Posto).equals("null")){
        ed.setOID_Posto(request.getParameter("oid_Posto"));
      }

      String NR_Litros = request.getParameter ("FT_NR_Litros");
      if (JavaUtil.doValida (NR_Litros)) {
        ed.setNR_Litros (new Double (NR_Litros).doubleValue ());
      }

      Abastecimento_Viagemrn.altera(ed);
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
      Abastecimento_ViagemRN Abastecimento_Viagemrn = new Abastecimento_ViagemRN();
      Abastecimento_ViagemED ed = new Abastecimento_ViagemED();

      ed.setOID_Abastecimento_Viagem(request.getParameter("oid_Abastecimento_Viagem"));

      Abastecimento_Viagemrn.deleta(ed);
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

  public ArrayList Abastecimento_Viagem_Lista(HttpServletRequest request)throws Excecoes{

      Abastecimento_ViagemED ed = new Abastecimento_ViagemED();

      String OID_Manifesto = request.getParameter("oid_Manifesto");
      if (String.valueOf(OID_Manifesto) != null &&
          !String.valueOf(OID_Manifesto).equals("") &&
          !String.valueOf(OID_Manifesto).equals("null")){
      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
      }
      String OID_Posto = request.getParameter("oid_Posto");
      if (String.valueOf(OID_Posto) != null &&
          !String.valueOf(OID_Posto).equals("") &&
          !String.valueOf(OID_Posto).equals("null")){
        ed.setOID_Posto(request.getParameter("oid_Posto"));
      }


      return new Abastecimento_ViagemRN().lista(ed);

  }

  public Abastecimento_ViagemED getByRecord(HttpServletRequest request)throws Excecoes{

      Abastecimento_ViagemED ed = new Abastecimento_ViagemED();

      String oid_Abastecimento_Viagem = request.getParameter("oid_Abastecimento_Viagem");
      if (String.valueOf(oid_Abastecimento_Viagem) != null &&
          !String.valueOf(oid_Abastecimento_Viagem).equals("") &&
          !String.valueOf(oid_Abastecimento_Viagem).equals("null")){
        ed.setOID_Abastecimento_Viagem(request.getParameter("oid_Abastecimento_Viagem"));
      }

      return new Abastecimento_ViagemRN().getByRecord(ed);

  }

  public double getByRecord(String oid_Manifesto)throws Excecoes{

      return new Abastecimento_ViagemRN().getByLitrosAbastecimento(oid_Manifesto);

  }


  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Abastecimento_ViagemRN Abastecimento_Viagemrn = new Abastecimento_ViagemRN();
      Abastecimento_ViagemED ed = new Abastecimento_ViagemED();

      //request em cima dos campos dos forms html

      ed.setDT_Abastecimento_Viagem(request.getParameter("FT_DT_Abastecimento_Viagem"));
      ed.setHR_Abastecimento_Viagem(request.getParameter("FT_HR_Abastecimento_Viagem"));

      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));


      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));

      String DM_Abastecimento_Viagem = request.getParameter("FT_DM_Abastecimento_Viagem");
      if (String.valueOf(DM_Abastecimento_Viagem) != null &&
          !String.valueOf(DM_Abastecimento_Viagem).equals("null") &&
          !String.valueOf(DM_Abastecimento_Viagem).equals("")){
          ed.setDM_Abastecimento_Viagem(request.getParameter("FT_DM_Abastecimento_Viagem"));
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

      String OID_Posto = request.getParameter("oid_Posto");
      if (String.valueOf(OID_Posto) != null &&
          !String.valueOf(OID_Posto).equals("") &&
          !String.valueOf(OID_Posto).equals("null")){
        ed.setOID_Posto(request.getParameter("oid_Posto"));
      }

      String NR_Litros = request.getParameter ("FT_NR_Litros");
      if (JavaUtil.doValida (NR_Litros)) {
        ed.setNR_Litros (new Double (NR_Litros).doubleValue ());
      }


      Abastecimento_Viagemrn.inclui(ed);
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


  public byte[] imprime_Ordem_Abastecimento(HttpServletRequest request, HttpServletResponse Response)
  throws Excecoes {
      String oid_Manifesto = request.getParameter("oid_Manifesto");
      Abastecimento_ViagemED ed = new Abastecimento_ViagemED();
      if (JavaUtil.doValida(oid_Manifesto)) {
          ed.setOID_Manifesto(oid_Manifesto);
      } else throw new Mensagens("Informe o manifesto!");
      return new Abastecimento_ViagemRN().imprime_Ordem_Abastecimento(ed);
  }

}
