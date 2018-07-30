package com.master.iu;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ModeloNotaFiscalED;
import com.master.ed.Movimento_ProcessoED;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.rn.Movimento_ProcessoRN;
import com.master.util.Excecoes;

public class Fin109MovBean {


  public Movimento_ProcessoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Movimento_ProcessoRN Movimento_ProcessoRN = new Movimento_ProcessoRN();
      Movimento_ProcessoED ed = new Movimento_ProcessoED();

      ed.setDT_Movimento_Processo(request.getParameter("FT_DT_Movimento_Processo"));

      // System.out.println("inclui 1");

      ed.setOid_Processo(request.getParameter("oid_Processo"));

      // System.out.println("inclui 2");

      ed.setNR_Documento(request.getParameter("FT_NR_Documento"));

      ed.setNM_Complemento_Historico(" ");
      String NM_Complemento_Historico = request.getParameter("FT_NM_Complemento_Historico");
      if (NM_Complemento_Historico != null && !NM_Complemento_Historico.equals("") && !NM_Complemento_Historico.equals("null"))
          ed.setNM_Complemento_Historico(NM_Complemento_Historico);


      ed.setDM_Debito_Credito(request.getParameter("FT_DM_Debito_Credito"));
      //// System.out.println("DEBITO"+ed.getDM_Debito_Credito());
      ed.setDM_Tipo_Lancamento("L");
      // System.out.println("inclui 6");

      ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
      ed.setOid_Lote_Pagamento(0);

      ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
      // System.out.println("inclui 8");

      ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));

      // System.out.println("inclui 10");

     return Movimento_ProcessoRN.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
  }


  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Movimento_ProcessoRN Movimento_ProcessoRN = new Movimento_ProcessoRN();
      Movimento_ProcessoED ed = new Movimento_ProcessoED();

        // System.out.println("altera 0");

        ed.setDT_Movimento_Processo(request.getParameter("FT_DT_Movimento_Processo"));
        // System.out.println("altera 1");

        ed.setNR_Documento(request.getParameter("FT_NR_Documento"));
        ed.setNM_Complemento_Historico(request.getParameter("FT_NM_Complemento_Historico"));
        ed.setDM_Debito_Credito(request.getParameter("FT_DM_Debito_Credito"));
        //// System.out.println("DEBITO"+ed.getDM_Debito_Credito());
        ed.setDM_Tipo_Lancamento(request.getParameter("FT_DM_Tipo_Lancamento"));
        // System.out.println("altera 6");

        ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));

        ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
        // System.out.println("altera 8");

        ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));

        // System.out.println("altera 9");

        ed.setOid_Movimento_Processo(new Long(request.getParameter("oid_Movimento_Processo")).longValue());

        // System.out.println("altera 10");

        ed.setNM_Complemento_Historico(" ");
        String NM_Complemento_Historico = request.getParameter("FT_NM_Complemento_Historico");
        if (NM_Complemento_Historico != null && !NM_Complemento_Historico.equals("") && !NM_Complemento_Historico.equals("null"))
          ed.setNM_Complemento_Historico(NM_Complemento_Historico);


        Movimento_ProcessoRN.altera(ed);

        // System.out.println("altera 12");

    }
    catch (Excecoes exc){
      throw exc;
    }
  }



  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Movimento_ProcessoRN Movimento_ProcessoRN = new Movimento_ProcessoRN();
      Movimento_ProcessoED ed = new Movimento_ProcessoED();

      ed.setOid_Movimento_Processo(new Long(request.getParameter("oid_Movimento_Processo")).longValue());

      Movimento_ProcessoRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Movimento_Processo_Lista(HttpServletRequest request)throws Excecoes{

    Movimento_ProcessoED ed = new Movimento_ProcessoED();
      ed.setOid_Processo(request.getParameter("oid_Processo"));

      ed.setDT_Inicial(request.getParameter("FT_DT_Movimento_Processo_Inicial"));
      ed.setDT_Final(request.getParameter("FT_DT_Movimento_Processo_Final"));

    return new Movimento_ProcessoRN().lista(ed);

  }

  public Movimento_ProcessoED getByRecord(HttpServletRequest request)throws Excecoes{

    //// System.out.println("iu a");
      Movimento_ProcessoED ed = new Movimento_ProcessoED();

      String oid_Movimento_Processo = request.getParameter("oid_Movimento_Processo");

    //// System.out.println("iu b");


      if (oid_Movimento_Processo != null && oid_Movimento_Processo.length() > 0 && !oid_Movimento_Processo.equals("null"))
      {
    //// System.out.println("iu c");


      ed.setOid_Movimento_Processo(new Long(request.getParameter("oid_Movimento_Processo")).longValue());
      }

    //// System.out.println("iu d");

      return new Movimento_ProcessoRN().getByRecord(ed);

  }



  public  byte[] imprime_Movimento_Processo(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

    Movimento_ProcessoED ed = new Movimento_ProcessoED();

      //ed.setAcao(request.getParameter("acao"));

      // System.out.println(" IU imprime_Movimento_Processo");
      ed.setDM_Relatorio(request.getParameter("acao"));
      ed.setOid_Processo(request.getParameter("oid_Processo"));

    Movimento_ProcessoRN geRN = new Movimento_ProcessoRN();
    byte[] b = geRN.imprime_Movimento_Processo(ed);

    return b;

  }


}
