package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Edi_Nota_Fiscal_CTRCED;
import com.master.rn.Edi_Nota_Fiscal_CTRCRN;
import com.master.util.Excecoes;

public class Edi304Bean {
	
  public ArrayList gera_Edi_Nota_Fiscal_CTRC (HttpServletRequest request) throws Excecoes {

    Edi_Nota_Fiscal_CTRCED ed = new Edi_Nota_Fiscal_CTRCED ();

    // System.err.println ("edi bean 1");

    String oid_Empresa = request.getParameter ("oid_Empresa");
    if (oid_Empresa != null && !oid_Empresa.equals ("") && !oid_Empresa.equals ("null")) {
      ed.setOid_Empresa (new Long (oid_Empresa).longValue ());
    }

    // System.err.println ("edi bean 10");

    String dt_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (dt_Inicial != null && !dt_Inicial.equals ("") && !dt_Inicial.equals ("null")) {
      ed.setDT_Inicial (dt_Inicial);
    }

    String dt_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (dt_Final != null && !dt_Final.equals ("") && !dt_Final.equals ("null")) {
      ed.setDT_Final (dt_Final);
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa_Remetente");
    if (oid_Pessoa != null && !oid_Pessoa.equals ("") && !oid_Pessoa.equals ("null")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }

    String oid_Pessoa_Detinatario = request.getParameter ("oid_Pessoa_Destinatario");
    if (oid_Pessoa_Detinatario != null && !oid_Pessoa_Detinatario.equals ("") && !oid_Pessoa_Detinatario.equals ("null")) {
      ed.setOid_Pessoa_Destinatario (oid_Pessoa_Detinatario);
    }

    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    if (DM_Situacao != null && !DM_Situacao.equals ("") && !DM_Situacao.equals ("null")) {
      ed.setDM_Situacao (DM_Situacao);
    }

    String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");
    if (DM_Relatorio != null && !DM_Relatorio.equals ("") && !DM_Relatorio.equals ("null")) {
      ed.setDM_Relatorio (DM_Relatorio);
    }

    // System.err.println ("edi bean 99");

    return new Edi_Nota_Fiscal_CTRCRN ().gera_Edi_Nota_Fiscal_CTRC (ed);

  }

}
