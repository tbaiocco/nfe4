package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.ed.EDI_ExportacaoED;
import com.master.rn.EDI_ExportacaoRN;
import com.master.util.Excecoes;


public class Edi010Bean {

  public ArrayList gera_EDI_Faturas(HttpServletRequest request)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      ed.setDM_Grupo_Economico(request.getParameter("FT_DM_Grupo_Economico"));
      ed.setDM_Tipo_Frete(request.getParameter("FT_DM_Tipo_Frete"));

      String Nr_duplicata = request.getParameter("FT_NR_Duplicata");
      if (Nr_duplicata != null && !Nr_duplicata.equals("") && !Nr_duplicata.equals("null"))
        ed.setNR_Duplicata(Nr_duplicata);

      return new EDI_ExportacaoRN().gera_EDI_Faturas(ed);

  }

  public ArrayList gera_EDI_FaturasIpiranga(HttpServletRequest request)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      ed.setDM_Grupo_Economico(request.getParameter("FT_DM_Grupo_Economico"));
      ed.setDM_Tipo_Frete(request.getParameter("FT_DM_Tipo_Frete"));

      String Nr_duplicata = request.getParameter("FT_NR_Duplicata");
      if (Nr_duplicata != null && !Nr_duplicata.equals("") && !Nr_duplicata.equals("null"))
        ed.setNR_Duplicata(Nr_duplicata);

      return new EDI_ExportacaoRN().gera_EDI_FaturasIpiranga(ed);

  }

  public ArrayList gera_EDI_Conhecimento(HttpServletRequest request)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("0") && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long (oid_Unidade).longValue());

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      ed.setOid_Pessoa_Entregadora (request.getParameter ("oid_Pessoa_Entregadora"));

      ed.setDM_Grupo_Economico(request.getParameter("FT_DM_Grupo_Economico"));
      ed.setDM_Tipo_Frete(request.getParameter("FT_DM_Tipo_Frete"));

      ed.setDM_Tipo_Padrao(request.getParameter("FT_DM_Tipo_Padrao").trim());
      // System.out.println(ed.getDM_Tipo_Padrao());

      return new EDI_ExportacaoRN().gera_EDI_Conhecimento(ed);

  }

  public ArrayList gera_EDI_Nota_Fiscal(HttpServletRequest request)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("0") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long (oid_Unidade).longValue());

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      ed.setDM_Grupo_Economico(request.getParameter("FT_DM_Grupo_Economico"));

      return new EDI_ExportacaoRN().gera_EDI_Nota_Fiscal(ed);

  }

  public ArrayList gera_EDI_Nota_FiscalByManifesto(HttpServletRequest request)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (oid_Unidade != null && !oid_Unidade.equals("0") && !oid_Unidade.equals("null"))
        ed.setOid_Unidade(new Long (oid_Unidade).longValue());

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      ed.setDM_Grupo_Economico(request.getParameter("FT_DM_Grupo_Economico"));

      String oid_Manifesto = request.getParameter("oid_Manifesto");
      if (oid_Manifesto != null && !oid_Manifesto.equals("") && !oid_Manifesto.equals("null"))
        ed.setOID_Manifesto(oid_Manifesto);

      return new EDI_ExportacaoRN().gera_EDI_Nota_FiscalByManifesto(ed);

  }

  public ArrayList exporta_Conhecimento(HttpServletRequest request)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      ed.setDM_Tipo_Frete(request.getParameter("FT_DM_Tipo_Frete"));

      return new EDI_ExportacaoRN().exporta_Conhecimento(ed);

  }


  public ArrayList gera_EDI_Ocorrencia(HttpServletRequest request)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String oid_Padrao = request.getParameter("oid_Padrao_Edi");
      if (oid_Padrao != null && !oid_Padrao.equals("") && !oid_Padrao.equals("null"))
        ed.setOid_Padrao(new Long(oid_Padrao).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      String dt_Lancamento_Inicial = request.getParameter("FT_DT_Lancamento_Inicial");
      if (dt_Lancamento_Inicial != null && !dt_Lancamento_Inicial.equals("") && !dt_Lancamento_Inicial.equals("null"))
        ed.setDT_Lancamento_Inicial(dt_Lancamento_Inicial);

      String dt_Lancamento_Final = request.getParameter("FT_DT_Lancamento_Final");
      if (dt_Lancamento_Final != null && !dt_Lancamento_Final.equals("") && !dt_Lancamento_Final.equals("null"))
        ed.setDT_Lancamento_Final(dt_Lancamento_Final);

      ed.setDM_Grupo_Economico(request.getParameter("FT_DM_Grupo_Economico"));
      ed.setDM_Tipo_Frete(request.getParameter("FT_DM_Tipo_Frete"));

      return new EDI_ExportacaoRN().gera_EDI_Ocorrencia(ed);

  }

  public void exporta_Nota_Fiscal(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      EDI_ExportacaoED ed = new EDI_ExportacaoED();

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (oid_Pessoa != null && !oid_Pessoa.equals("") && !oid_Pessoa.equals("null"))
        ed.setOid_Pessoa(oid_Pessoa);

      String oid_Empresa = request.getParameter("oid_Empresa");
      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
        ed.setDT_Inicial(dt_Inicial);

      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
        ed.setDT_Final(dt_Final);

      ed.setDM_Tipo_Frete(request.getParameter("FT_DM_Tipo_Frete"));

      new EDI_ExportacaoRN().exporta_Nota_Fiscal(ed, request, response);

  }

}
