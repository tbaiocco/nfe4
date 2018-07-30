package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_DuplicataED;
import com.master.rn.EDI_DuplicataRN;
import com.master.util.Excecoes;

public class Edi302Bean {

	  public ArrayList gera_EDI_Cliente(HttpServletRequest request)throws Excecoes{

	      EDI_DuplicataED ed = new EDI_DuplicataED();

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

	      
	      
	      String oid_Pessoa = request.getParameter("oid_Pessoa");
	      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
	        ed.setOid_Pessoa(oid_Pessoa);

	      String DM_Situacao_Cliente = request.getParameter("FT_DM_Situacao_Cliente");
	      if (DM_Situacao_Cliente != null && !DM_Situacao_Cliente.equals("") && !DM_Situacao_Cliente.equals("null"))
	        ed.setDM_Situacao_Cliente(DM_Situacao_Cliente);

	      String DM_Tipo_Transporte = request.getParameter("FT_DM_Tipo_Transporte");
	      if (String.valueOf(DM_Tipo_Transporte) != null && !String.valueOf(DM_Tipo_Transporte).equals("")){
	        ed.setDM_Tipo_Transporte(DM_Tipo_Transporte);
	      }

	// System.err.println("edi bean 99");

	      return new EDI_DuplicataRN().gera_EDI_Cliente(ed);

	  }

	  public ArrayList gera_EDI_Duplicata(HttpServletRequest request)throws Excecoes{

	      EDI_DuplicataED ed = new EDI_DuplicataED();

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

	      
	      
	      String oid_Pessoa = request.getParameter("oid_Pessoa");
	      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
	        ed.setOid_Pessoa(oid_Pessoa);

	      String DM_Situacao_Cliente = request.getParameter("FT_DM_Situacao_Cliente");
	      if (DM_Situacao_Cliente != null && !DM_Situacao_Cliente.equals("") && !DM_Situacao_Cliente.equals("null"))
	        ed.setDM_Situacao_Cliente(DM_Situacao_Cliente);

	      String DM_Tipo_Transporte = request.getParameter("FT_DM_Tipo_Transporte");
	      if (String.valueOf(DM_Tipo_Transporte) != null && !String.valueOf(DM_Tipo_Transporte).equals("")){
	        ed.setDM_Tipo_Transporte(DM_Tipo_Transporte);
	      }


	// System.err.println("edi bean 99");

	      return new EDI_DuplicataRN().gera_EDI_Duplicata(ed);

	  }

}
