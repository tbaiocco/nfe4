package com.master.iu;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.SeguroED;
import com.master.rn.SeguroRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;


public class SeguroBean extends JavaUtil implements Serializable {



  public  byte[] geraSeguroConhecimento(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

//// System.out.println("a 0");
    SeguroED ed = new SeguroED();

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

      String DM_Situacao_Cliente = request.getParameter("FT_DM_Situacao_Cliente");
      if (DM_Situacao_Cliente != null && !DM_Situacao_Cliente.equals("") && !DM_Situacao_Cliente.equals("null"))
        ed.setDM_Situacao_Cliente(DM_Situacao_Cliente);

      String DM_Tipo_Transporte = request.getParameter("FT_DM_Tipo_Transporte");
      if (String.valueOf(DM_Tipo_Transporte) != null && !String.valueOf(DM_Tipo_Transporte).equals("")){
        ed.setDM_Tipo_Transporte(DM_Tipo_Transporte);
      }


      String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
      if (String.valueOf(DM_Relatorio) != null && !String.valueOf(DM_Relatorio).equals("")){
        ed.setDM_Relatorio(DM_Relatorio);
      }

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

      SeguroRN segRN = new SeguroRN();

      return segRN.geraSeguroConhecimento(ed);

  }

}
