package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Instrucao_DuplicataED;
import com.master.rn.Instrucao_DuplicataRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class cob006Bean {

  public Instrucao_DuplicataED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Instrucao_DuplicataRN Instrucao_Duplicatarn = new Instrucao_DuplicataRN();
      Instrucao_DuplicataED ed = new Instrucao_DuplicataED();

      //request em cima dos campos dos forms html

      ed.setDt_Instrucao(request.getParameter("FT_DT_Instrucao_Duplicata"));
      ed.setHr_Instrucao(request.getParameter("FT_HR_Instrucao_Duplicata"));
      ed.setOid_Tipo_Instrucao(new Long(request.getParameter("oid_Tipo_Instrucao")).longValue());
      ed.setOid_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());
      ed.setOid_Carteira(new Integer(request.getParameter("oid_Carteira")));
      ed.setTx_Observacao(request.getParameter("FT_TX_Observacao"));
      ed.setDm_Situacao("I");
      ed.setDt_Remessa(null);
      ed.setDt_Retorno(null);
      ed.setDt_Novo_Vencimento(null);
      ed.setDt_Stamp(ed.getDt_Instrucao());
      ed.setNr_Remessa(0);
      return Instrucao_Duplicatarn.inclui(ed);
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


  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Instrucao_DuplicataRN Instrucao_Duplicatarn = new Instrucao_DuplicataRN();
      Instrucao_DuplicataED ed = new Instrucao_DuplicataED();

      ed.setOid_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());
      String oid_Instrucao = request.getParameter("oid_Instrucao");
      if (String.valueOf(oid_Instrucao) != null &&
          !String.valueOf(oid_Instrucao).equals("") &&
          !String.valueOf(oid_Instrucao).equals("null")){
          ed.setOid_Instrucao(new Long(oid_Instrucao).longValue());
      }
      ed.setOid_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());

      Instrucao_Duplicatarn.deleta(ed);
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

  public ArrayList Instrucao_Duplicata_Lista(HttpServletRequest request)throws Excecoes{

      Instrucao_DuplicataED ed = new Instrucao_DuplicataED();

   // System.err.println("1");
      String oid_Instrucao = request.getParameter("oid_Instrucao");
      if (String.valueOf(oid_Instrucao) != null &&
          !String.valueOf(oid_Instrucao).equals("") &&
          !String.valueOf(oid_Instrucao).equals("null")){
          ed.setOid_Instrucao(new Long(oid_Instrucao).longValue());
      }
   // System.err.println("2");
      String oid_Duplicata = request.getParameter("oid_Duplicata");
      if (String.valueOf(oid_Duplicata) != null &&
          !String.valueOf(oid_Duplicata).equals("") &&
          !String.valueOf(oid_Duplicata).equals("null")){
          ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());
      }
   // System.err.println("3");


      return new Instrucao_DuplicataRN().lista(ed);

  }

  public Instrucao_DuplicataED getByRecord(HttpServletRequest request)throws Excecoes{

      Instrucao_DuplicataED ed = new Instrucao_DuplicataED();

      String oid_Instrucao = request.getParameter("oid_Instrucao");
      if (String.valueOf(oid_Instrucao) != null &&
          !String.valueOf(oid_Instrucao).equals("") &&
          !String.valueOf(oid_Instrucao).equals("null")){
          ed.setOid_Instrucao(new Long(oid_Instrucao).longValue());
      }
      String oid_Duplicata = request.getParameter("oid_Duplicata");
      if (String.valueOf(oid_Duplicata) != null &&
          !String.valueOf(oid_Duplicata).equals("") &&
          !String.valueOf(oid_Duplicata).equals("null")){
          ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());
      }

      return new Instrucao_DuplicataRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Instrucao_DuplicataED ed = new Instrucao_DuplicataED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Instrucao_DuplicataRN geRN = new Instrucao_DuplicataRN();
    geRN.geraRelatorio(ed);
  }

}