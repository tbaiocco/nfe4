package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_SeguradoraED;
import com.master.rn.EDI_SeguradoraRN;
import com.master.util.Excecoes;

public class Edi301Bean {

  public ArrayList gera_EDI_Seguradora(HttpServletRequest request)throws Excecoes{

      EDI_SeguradoraED ed = new EDI_SeguradoraED();

// System.err.println("edi bean 1");

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      String oid_Seguradora = request.getParameter("oid_Seguradora");
      if (oid_Seguradora != null && !oid_Seguradora.equals("") && !oid_Seguradora.equals("null"))
        ed.setOid_Seguradora(new Long(oid_Seguradora).longValue());

// System.err.println("edi bean 10");

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      String DM_Situacao = request.getParameter("FT_DM_Situacao");
      if (DM_Situacao != null && !DM_Situacao.equals("") && !DM_Situacao.equals("null"))
        ed.setDM_Situacao(DM_Situacao);

      String DM_Tipo_Documento = request.getParameter("FT_DM_Tipo_Documento");
      if (DM_Tipo_Documento != null && !DM_Tipo_Documento.equals("") && !DM_Tipo_Documento.equals("null"))
        ed.setDM_Tipo_Documento(DM_Tipo_Documento);
      
      String DM_Situacao_Cliente = request.getParameter("FT_DM_Situacao_Cliente");
      if (DM_Situacao_Cliente != null && !DM_Situacao_Cliente.equals("") && !DM_Situacao_Cliente.equals("null"))
        ed.setDM_Situacao_Cliente(DM_Situacao_Cliente);

      String DM_Tipo_Transporte = request.getParameter("FT_DM_Tipo_Transporte");
      if (String.valueOf(DM_Tipo_Transporte) != null && !String.valueOf(DM_Tipo_Transporte).equals("")){
        ed.setDM_Tipo_Transporte(DM_Tipo_Transporte);
      }


// System.err.println("edi bean 99");

      return new EDI_SeguradoraRN().gera_EDI_Seguradora(ed);

  }

  public ArrayList gera_EDI_Seguradora_CRT(HttpServletRequest request)throws Excecoes{

      EDI_SeguradoraED ed = new EDI_SeguradoraED();

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

      String oid_Seguradora = request.getParameter("oid_Seguradora");
      if (oid_Seguradora != null && !oid_Seguradora.equals("") && !oid_Seguradora.equals("null"))
        ed.setOid_Seguradora(new Long(oid_Seguradora).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      String DM_Situacao = request.getParameter("FT_DM_Situacao");
      if (DM_Situacao != null && !DM_Situacao.equals("") && !DM_Situacao.equals("null"))
        ed.setDM_Situacao(DM_Situacao);


// System.err.println("edi bean 99");

      return new EDI_SeguradoraRN().gera_EDI_Seguradora_CRT(ed);

  }



}
