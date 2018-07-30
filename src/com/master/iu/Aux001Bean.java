package com.master.iu;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ContaED;
import com.master.rn.Auxiliar1RN;
import com.master.rn.ContaRN;
import com.master.util.Excecoes;

public class Aux001Bean {

  public ContaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      ContaRN contaRN = new ContaRN();
      ContaED ed = new ContaED();

//// System.out.println("Conta 11");
      ed.setCd_Conta(request.getParameter("FT_CD_Conta"));
//// System.out.println("Conta 12");
      ed.setNm_Conta(request.getParameter("FT_NM_Conta"));
//// System.out.println("Conta 13");
      ed.setOid_Grupo_Conta(new Integer(request.getParameter("FT_OID_Grupo_Conta")));
//// System.out.println("Conta 14");
      ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
//// System.out.println("Conta 15");
      ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
//// System.out.println("Conta 16");
      ed.setDm_Apropriacao(request.getParameter("FT_DM_Apropriacao"));
      ed.setDm_Despesa(request.getParameter("FT_DM_Despesa"));
//// System.out.println("Conta 17");
      ed.setDm_Tipo_Operacao(request.getParameter("FT_DM_Tipo"));
//// System.out.println("Conta 18");
      ed.setCd_Conta_Contabil(request.getParameter("FT_CD_Conta_Contabil"));
//// System.out.println("Conta 19");

      return contaRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      ContaRN contaRN = new ContaRN();
      ContaED ed = new ContaED();

      ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
      ed.setCd_Conta(request.getParameter("FT_CD_Conta"));
      ed.setNm_Conta(request.getParameter("FT_NM_Conta"));
      ed.setOid_Grupo_Conta(new Integer(request.getParameter("FT_OID_Grupo_Conta")));
      ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
      ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
      ed.setDm_Apropriacao(request.getParameter("FT_DM_Apropriacao"));
      ed.setDm_Tipo_Operacao(request.getParameter("FT_DM_Tipo"));
      ed.setCd_Conta_Contabil(request.getParameter("FT_CD_Conta_Contabil"));

      ed.setDm_Despesa(request.getParameter("FT_DM_Despesa"));
      contaRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      ContaRN Contarn = new ContaRN();
      ContaED ed = new ContaED();

      ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));

      Contarn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Conta_Lista(HttpServletRequest request)throws Excecoes{

      ContaED ed = new ContaED();

      String cd_Conta = request.getParameter("FT_CD_Conta");
      String oid_Grupo_Conta = request.getParameter("FT_OID_Grupo_Conta");
      String nm_Conta = request.getParameter("FT_NM_Conta");

      if (cd_Conta != null && !cd_Conta.equals(""))
        ed.setCd_Conta(cd_Conta);

      if (oid_Grupo_Conta != null && !oid_Grupo_Conta.equals(""))
        ed.setOid_Grupo_Conta(new Integer(oid_Grupo_Conta));

      if (nm_Conta != null && !nm_Conta.equals(""))
        ed.setNm_Conta(nm_Conta);

      return new ContaRN().lista(ed);

  }

  public ContaED getByRecord(HttpServletRequest request)throws Excecoes{

      ContaED ed = new ContaED();

      String oid_Conta = request.getParameter("oid_Conta");
      String cd_Conta = request.getParameter("FT_CD_Conta");

      if (oid_Conta != null && oid_Conta.length() > 0)
      {
        ed.setOid_Conta(new Integer(oid_Conta));
      }
      if (cd_Conta != null && !cd_Conta.equals("0")){
        ed.setCd_Conta(cd_Conta);
      }

     return new ContaRN().getByRecord(ed);

  }
  public ContaED getByCD(HttpServletRequest request)throws Excecoes{

      ContaED ed = new ContaED();

      String cd_Conta = request.getParameter("FT_CD_Conta");

       if (cd_Conta != null && !cd_Conta.equals("0")){
        ed.setCd_Conta(cd_Conta);
      }

     return new ContaRN().getByRecord(ed);

  }

    public ContaED getByOid(String Conta)throws Excecoes{

      ContaED ed = new ContaED();

      String oid_Conta = Conta;

      if (oid_Conta != null && oid_Conta.length() > 0)
      {
        ed.setOid_Conta(new Integer(oid_Conta));
      }

     return new ContaRN().getByRecord(ed);

  }


  public ContaED getByRecord_Credito(HttpServletRequest request)throws Excecoes{

      ContaED ed = new ContaED();

      String oid_Conta = request.getParameter("oid_Conta_Credito");
      String cd_Conta = request.getParameter("FT_CD_Conta_Credito");

      if (oid_Conta != null && oid_Conta.length() > 0)
      {
        ed.setOid_Conta(new Integer(oid_Conta));
      }
      if (cd_Conta != null && !cd_Conta.equals("0")){
        ed.setCd_Conta(cd_Conta);
      }

     return new ContaRN().getByRecord(ed);

  }
  public  byte[] geraRelacaoContas(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

//// System.out.println("a 0");
    ContaED ed = new ContaED();
 // System.out.println("AUXILIAR iu ->>  " );

    Auxiliar1RN geRN = new Auxiliar1RN();
    byte[] b = geRN.geraRelacaoContas(ed);

    return b;

  }

  public void bacas(String executar, String arquivo)throws Excecoes{

        // System.out.println (executar);

    try {

      Auxiliar1RN auxiliar1RN = new Auxiliar1RN ();

      auxiliar1RN.bacas (executar, arquivo);

    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

  public String leituraNFE(String arquivo, String tipo)throws Excecoes{

      // System.out.println (executar);

  try {

    Auxiliar1RN auxiliar1RN = new Auxiliar1RN ();

    return auxiliar1RN.leituraNFE(arquivo, tipo);

  }

  catch (Excecoes exc) {
    throw exc;
  }
}
}
