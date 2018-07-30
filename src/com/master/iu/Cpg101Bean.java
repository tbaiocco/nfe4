package com.master.iu;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.CaixaED;
import com.master.rn.CaixaRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;

public class Cpg101Bean {

  public CaixaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      CaixaRN CaixaRN = new CaixaRN();
      CaixaED ed = new CaixaED();

      ed.setDT_Caixa(request.getParameter("FT_DT_Caixa"));
       //// System.out.println("inclui 1");

      ed.setNR_Documento(request.getParameter("FT_NR_Documento"));
       //// System.out.println("inclui 2");
      ed.setNM_Complemento_Historico(request.getParameter("FT_NM_Complemento_Historico"));
       //// System.out.println("inclui 3");
      ed.setDM_Debito_Credito(request.getParameter("FT_DM_Debito_Credito"));
      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));
       //// System.out.println("inclui 4");
      ed.setDM_Tipo_Lancamento(request.getParameter("FT_DM_Tipo_Lancamento"));
       //// System.out.println("inclui 5");
      ed.setOID_Conta(request.getParameter("oid_Conta"));
       //// System.out.println("inclui 6");
      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
       //// System.out.println("inclui 7");
      ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
       //// System.out.println("inclui 8");
      ed.setOID_Historico(new Integer(request.getParameter("oid_Historico")));
       //// System.out.println("inclui 9");
      ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));
       //// System.out.println("inclui 10");

     return CaixaRN.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public CaixaED geraSaldoInicial(HttpServletRequest request)throws Excecoes{

    try{
      CaixaRN CaixaRN = new CaixaRN();
      CaixaED ed = new CaixaED();

      ed.setDT_Caixa(request.getParameter("FT_DT_Caixa"));
      // System.out.println("inclui 1");
      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));

      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      // System.out.println("geraSaldoInicial 2");

     return CaixaRN.geraSaldoInicial(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      CaixaRN CaixaRN = new CaixaRN();
      CaixaED ed = new CaixaED();

      ed.setDT_Caixa(request.getParameter("FT_DT_Caixa"));
      ed.setNR_Documento(request.getParameter("FT_NR_Documento"));
      ed.setDM_Debito_Credito(request.getParameter("FT_DM_Debito_Credito"));
      ed.setDM_Tipo_Lancamento(request.getParameter("FT_DM_Tipo_Lancamento"));
      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));
      ed.setOID_Conta(request.getParameter("oid_Conta"));
      ed.setOid_Caixa(new Long(request.getParameter("oid_Caixa")).longValue());

      ed.setOID_Historico(new Integer(request.getParameter("oid_Historico")));
      ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));
      String NM_Complemento_Historico = request.getParameter("FT_NM_Complemento_Historico");
      if (!NM_Complemento_Historico.equals(""))
        ed.setNM_Complemento_Historico(NM_Complemento_Historico);

      CaixaRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }


  public void atualiza(HttpServletRequest request)throws Excecoes{

    try{
      CaixaRN CaixaRN = new CaixaRN();
      CaixaED ed = new CaixaED();

      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      ed.setDT_Caixa(request.getParameter("FT_DT_Caixa"));
      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));

      CaixaRN.atualiza(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void estorna(HttpServletRequest request)throws Excecoes{

    try{
      CaixaRN CaixaRN = new CaixaRN();
      CaixaED ed = new CaixaED();

      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      ed.setDT_Caixa(request.getParameter("FT_DT_Caixa"));
      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));

      CaixaRN.estorna(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }


  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      CaixaRN CaixaRN = new CaixaRN();
      CaixaED ed = new CaixaED();

      ed.setOid_Caixa(new Long(request.getParameter("oid_Caixa")).longValue());


      CaixaRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Caixa_Lista(HttpServletRequest request)throws Excecoes{

    CaixaED ed = new CaixaED();
      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      ed.setDT_Inicial(request.getParameter("FT_DT_Caixa_Inicial"));
      ed.setDT_Final(request.getParameter("FT_DT_Caixa_Final"));

    // System.out.println("FT_DM_Grupo=>>" +request.getParameter("FT_DM_Grupo") );

      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));

    return new CaixaRN().lista(ed);

  }

  public CaixaED getByRecord(HttpServletRequest request)throws Excecoes{

    //// System.out.println("iu a");
      CaixaED ed = new CaixaED();

      String oid_Caixa = request.getParameter("oid_Caixa");

    //// System.out.println("iu b");


      if (oid_Caixa != null && oid_Caixa.length() > 0 && !oid_Caixa.equals("null"))
      {
    //// System.out.println("iu c");


      ed.setOid_Caixa(new Long(request.getParameter("oid_Caixa")).longValue());
      }

    //// System.out.println("iu d");

      return new CaixaRN().getByRecord(ed);

  }


  public void imprime_Caixa(HttpServletRequest request, HttpServletResponse res)throws Excecoes{

      CaixaED ed = new CaixaED();

      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));
      ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));

      ed.setDT_Inicial(request.getParameter("FT_DT_Caixa_Inicial"));
      ed.setDT_Final(request.getParameter("FT_DT_Caixa_Final"));

    CaixaRN geRN = new CaixaRN();
    byte[] b = geRN.imprime_Caixa(ed);
    new EnviaPDF().enviaBytes(request,res,b);

  }

  public CaixaED finalizaMovimento(HttpServletRequest request)throws Excecoes{

    try{
      CaixaRN CaixaRN = new CaixaRN();
      CaixaED ed = new CaixaED();

      ed.setDT_Caixa(request.getParameter("FT_DT_Caixa"));
      // System.out.println("inclui 1");

      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      // System.out.println("inclui 2");
      ed.setDM_Grupo(request.getParameter("FT_DM_Grupo"));


      ed.setVL_Saldo_Inicial(new Double(request.getParameter("FT_VL_Saldo_Inicial")).doubleValue());
      // System.out.println("inclui 3");
      ed.setVL_Saldo_Final(new Double(request.getParameter("FT_VL_Saldo_Final")).doubleValue());

      // System.out.println("inclui 10");

     return CaixaRN.finalizaMovimento(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
  }


}
