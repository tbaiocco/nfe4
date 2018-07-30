package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.BancoED;
import com.master.rn.BancoRN;
import com.master.util.Excecoes;

public class fin004Bean {

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      BancoRN Bancorn = new BancoRN();
      BancoED ed = new BancoED();
      
      //request em cima dos campos dos forms html\\
      
      // System.out.println("fin004Bean 1");
      ed.setCD_Remessa(request.getParameter("FT_CD_Remessa"));
      ed.setCD_Banco(request.getParameter("FT_CD_Banco"));
      ed.setDM_Formulario(request.getParameter("FT_DM_Formulario"));
      ed.setCD_Alteracao_Vencimento(request.getParameter("FT_CD_Alteracao_Vencimento"));
      ed.setCD_Baixa(request.getParameter("FT_CD_Baixa"));
      ed.setCD_Desconto(request.getParameter("FT_CD_Desconto"));
      ed.setCD_Primeira_Instr_Protesto(request.getParameter("FT_CD_Primeira_Instr_Protesto"));
      // System.out.println("fin004Bean 2");
      ed.setCD_Protesto(request.getParameter("FT_CD_Protesto"));
      ed.setCD_Segunda_Instr_Protesto(request.getParameter("FT_CD_Segunda_Instr_Protesto"));
      ed.setCD_Sustar_Protesto(request.getParameter("FT_CD_Sustar_Protesto"));
      ed.setCD_Tipo_Documento(request.getParameter("FT_CD_Tipo_Documento"));
      ed.setNR_Dias_Protesto(request.getParameter("FT_NR_Dias_Protesto"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      // System.out.println("fin004Bean 3");

      Bancorn.inclui(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      BancoRN Bancorn = new BancoRN();
      BancoED ed = new BancoED();
      ed.setDM_Formulario(request.getParameter("FT_DM_Formulario"));

      ed.setCD_Remessa(request.getParameter("FT_CD_Remessa"));
      ed.setCD_Banco(request.getParameter("FT_CD_Banco"));
      ed.setCD_Alteracao_Vencimento(request.getParameter("FT_CD_Alteracao_Vencimento"));
      ed.setCD_Baixa(request.getParameter("FT_CD_Baixa"));
      ed.setCD_Desconto(request.getParameter("FT_CD_Desconto"));
      ed.setCD_Primeira_Instr_Protesto(request.getParameter("FT_CD_Primeira_Instr_Protesto"));
      ed.setCD_Protesto(request.getParameter("FT_CD_Protesto"));
      ed.setCD_Segunda_Instr_Protesto(request.getParameter("FT_CD_Segunda_Instr_Protesto"));
      ed.setCD_Sustar_Protesto(request.getParameter("FT_CD_Sustar_Protesto"));
      ed.setCD_Tipo_Documento(request.getParameter("FT_CD_Tipo_Documento"));
      ed.setNR_Dias_Protesto(request.getParameter("FT_NR_Dias_Protesto"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOID_Banco(new Integer(request.getParameter("oid_Banco")).intValue());

      Bancorn.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      BancoRN Bancorn = new BancoRN();
      BancoED ed = new BancoED();

      ed.setOID_Banco(new Integer(request.getParameter("oid_Banco")).intValue());

      Bancorn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Banco_Lista(HttpServletRequest request)throws Excecoes{

      BancoED ed = new BancoED();

      ed.setCD_Banco(request.getParameter("FT_CD_Banco"));
      ed.setNM_Banco(request.getParameter("FT_NM_Banco"));
      return new BancoRN().lista(ed);

  }

  public BancoED getByRecord(HttpServletRequest request)throws Excecoes{

      BancoED ed = new BancoED();

      String oid_Banco = request.getParameter("oid_Banco");
      if (oid_Banco != null && oid_Banco.length() > 0)
      {
        ed.setOID_Banco(new Long(request.getParameter("oid_Banco")).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      String FT_NR_CNPJ_CPF_Banco = request.getParameter("FT_NR_CNPJ_CPF_Banco");
      if (FT_NR_CNPJ_CPF_Banco != null && !FT_NR_CNPJ_CPF_Banco.equals("")
        && !FT_NR_CNPJ_CPF_Banco.equals("null")){
        ed.setOID_Pessoa(FT_NR_CNPJ_CPF_Banco);
      }
      ed.setCD_Banco(request.getParameter("FT_CD_Banco"));
      ed.setNM_Banco(request.getParameter("FT_NM_Banco"));
      return new BancoRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    BancoED ed = new BancoED();

    ed.setCD_Banco(req.getParameter("codigo"));
    ed.setCD_Remessa(req.getParameter("nome"));

    BancoRN geRN = new BancoRN();
    geRN.geraRelatorio(ed);
  }

}
