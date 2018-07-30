package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_FiscalED;
import com.master.rn.EDI_FiscalRN;
import com.master.util.Excecoes;

public class Edi011Bean {

  public ArrayList gera_Arquivo_EDI_Sintegra(HttpServletRequest request)throws Excecoes{
	// System.out.println("gera_arquivo_EDI_Fiscal()");
      EDI_FiscalED SintegraED = new EDI_FiscalED();

    /*Inserçao dos atributos do request*/
      SintegraED.setDT_Inicial(request.getParameter("FT_DT_Inicial"));
      SintegraED.setDT_Final(request.getParameter("FT_DT_Final"));
    //  SintegraED.setNM_Arquivo(request.getParameter("FT_NM_Arquivo_Saida"));
      SintegraED.setOID_Unidade(Long.parseLong(request.getParameter("OID_Unidade")));
      SintegraED.setDM_Periodo(request.getParameter("CB_Periodicidade"));

// System.out.println("gera_arquivo " + request.getParameter("FT_DM_Tipo_Documento_Saida"));
      SintegraED.setDM_Tipo_Documento_Entrada(request.getParameter("FT_DM_Tipo_Documento_Entrada"));
      SintegraED.setDM_Tipo_Documento_Saida(request.getParameter("FT_DM_Tipo_Documento_Saida"));
      SintegraED.setDM_Operacao(request.getParameter("CB_Operacao"));
      SintegraED.setNM_Finalidade_Arquivo(request.getParameter("CB_Finalidade"));
      SintegraED.setUF_Destino(request.getParameter("FT_UF_Destino"));

	// System.out.println("Vai chamar o rn");
      return new EDI_FiscalRN().gera_Arquivo_EDI_Sintegra(SintegraED);
  }

  public String gera_Arquivo_EDI_ISSQN(HttpServletRequest request)throws Excecoes{

	  EDI_FiscalED IssqnED = new EDI_FiscalED();
	  IssqnED.setDT_Inicial(request.getParameter("FT_DT_Inicial"));
	  IssqnED.setDT_Final(request.getParameter("FT_DT_Final"));
	  IssqnED.setOID_Unidade(Long.parseLong(request.getParameter("OID_Unidade")));

      return new EDI_FiscalRN().gera_Arquivo_EDI_ISSQN(IssqnED);
  }

}
