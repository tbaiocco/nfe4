package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ViagemED;
import com.master.rn.ViagemRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class man006Bean
    extends JavaUtil {

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      ViagemRN Viagemrn = new ViagemRN ();
      ViagemED ed = new ViagemED ();
      String OID_Usuario = request.getParameter ("oid_Usuario");
      if (JavaUtil.doValida (OID_Usuario)) {
          ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
      }

      ed.setOID_Viagem (request.getParameter ("oid_Viagem"));
      ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
      ed.setDT_Viagem (request.getParameter ("FT_DT_Viagem"));
      ed.setHR_Viagem (request.getParameter ("FT_HR_Viagem"));

      ed.setDM_Lancado_Ordem_Frete ("VERIFICAR");
      String DM_Lancado_Ordem_Frete = request.getParameter ("FT_DM_Lancado_Ordem_Frete");
      if (String.valueOf (DM_Lancado_Ordem_Frete) != null && !String.valueOf (DM_Lancado_Ordem_Frete).equals ("null") && !String.valueOf (DM_Lancado_Ordem_Frete).equals ("")) {
        ed.setDM_Lancado_Ordem_Frete (request.getParameter ("FT_DM_Lancado_Ordem_Frete"));
      }

      Viagemrn.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      ViagemRN Viagemrn = new ViagemRN ();
      ViagemED ed = new ViagemED ();
      String OID_Usuario = request.getParameter ("oid_Usuario");
      if (JavaUtil.doValida (OID_Usuario)) {
          ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
      }

      ed.setOID_Viagem (request.getParameter ("oid_Viagem"));
      ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      ed.setDT_Viagem (request.getParameter ("FT_DT_Viagem"));
      ed.setHR_Viagem (request.getParameter ("FT_HR_Viagem"));
      ed.setNM_Senha(request.getParameter ("FT_NM_Senha"));

      String OID_Compromisso = request.getParameter ("oid_Compromisso");
      if (String.valueOf (OID_Compromisso) != null && !String.valueOf (OID_Compromisso).equals ("") && !String.valueOf (OID_Compromisso).equals ("null")) {
        ed.setOID_Compromisso (new Long (OID_Compromisso).longValue ());
      }

      String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
      if (String.valueOf (nr_Conhecimento) != null && !String.valueOf (nr_Conhecimento).equals ("") && !String.valueOf (nr_Conhecimento).equals ("null")) {
        ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
      }

      ed.setDM_Lancado_Ordem_Frete ("VERIFICAR");
      String DM_Lancado_Ordem_Frete = request.getParameter ("FT_DM_Lancado_Ordem_Frete");
      if (String.valueOf (DM_Lancado_Ordem_Frete) != null && !String.valueOf (DM_Lancado_Ordem_Frete).equals ("null") && !String.valueOf (DM_Lancado_Ordem_Frete).equals ("")) {
        ed.setDM_Lancado_Ordem_Frete (request.getParameter ("FT_DM_Lancado_Ordem_Frete"));
      }
      //// System.out.println("man 10" );

      Viagemrn.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList Viagem_Lista_Nota (HttpServletRequest request) throws Excecoes {

    ViagemED ed = new ViagemED ();

    String oid_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");
    if (String.valueOf (oid_Nota_Fiscal) != null && !String.valueOf (oid_Nota_Fiscal).equals ("") && !String.valueOf (oid_Nota_Fiscal).equals ("null")) {
      ed.setOID_Nota_Fiscal(request.getParameter ("oid_Nota_Fiscal"));
    }

    ed.setDM_Tipo_Manifesto("RN");

    return new ViagemRN ().lista (ed);

  }

  public ArrayList Viagem_Lista (HttpServletRequest request) throws Excecoes {

	    ViagemED ed = new ViagemED ();

	    String OID_Manifesto = request.getParameter ("oid_Manifesto");
	    if (String.valueOf (OID_Manifesto) != null && !String.valueOf (OID_Manifesto).equals ("") && !String.valueOf (OID_Manifesto).equals ("null")) {
	      ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
	    }

	    String OID_Conhecimento = request.getParameter ("oid_Conhecimento");
	    if (String.valueOf (OID_Conhecimento) != null && !String.valueOf (OID_Conhecimento).equals ("") && !String.valueOf (OID_Conhecimento).equals ("null")) {
	      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
	    }

	    String OID_Processo = request.getParameter ("oid_Processo");
	    if (String.valueOf (OID_Processo) != null && !String.valueOf (OID_Processo).equals ("") && !String.valueOf (OID_Processo).equals ("null")) {
	      ed.setOID_Processo (request.getParameter ("oid_Processo"));
	    }

	    ed.setDT_Viagem (request.getParameter ("FT_DT_Viagem"));
	    ed.setDT_Previsao_Chegada (request.getParameter ("FT_DT_Previsao_Chegada"));
	    ed.setHR_Previsao_Chegada (request.getParameter ("FT_HR_Previsao_Chegada"));

	    String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");

	    if (String.valueOf (nr_Conhecimento) != null && !String.valueOf (nr_Conhecimento).equals ("") && !String.valueOf (nr_Conhecimento).equals ("null")) {
	      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
	    }
	    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
	    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("") && !String.valueOf (oid_Unidade).equals ("null")) {
	      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
	    }

	    return new ViagemRN ().lista (ed);

	  }

  public ViagemED getByRecord (HttpServletRequest request) throws Excecoes {

    ViagemED ed = new ViagemED ();

    String oid_Viagem = request.getParameter ("oid_Viagem");
    if (String.valueOf (oid_Viagem) != null && !String.valueOf (oid_Viagem).equals ("") && !String.valueOf (oid_Viagem).equals ("null")) {
      ed.setOID_Viagem (request.getParameter ("oid_Viagem"));
    }

    return new ViagemRN ().getByRecord (ed);

  }

  public ViagemED consulta_Viagem (String nr_Conhecimento , String OID_Unidade) throws Excecoes {

    ViagemED ed = new ViagemED ();

    if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("") && !String.valueOf (OID_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (OID_Unidade).longValue ());
    }
    if (String.valueOf (nr_Conhecimento) != null && !String.valueOf (nr_Conhecimento).equals ("") && !String.valueOf (nr_Conhecimento).equals ("null")) {
      ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
    }
    return new ViagemRN ().getByRecord (ed);

  }

  public ViagemED consulta_Viagem (String oid_Conhecimento) throws Excecoes {

    ViagemED ed = new ViagemED ();
    ed.setOID_Conhecimento (oid_Conhecimento);
    return new ViagemRN ().getByRecord (ed);

  }

  public ViagemED consulta_Viagem_Manifesto (String oid_Manifesto) throws Excecoes {

	    ViagemED ed = new ViagemED ();
	    ed.setOID_Manifesto (oid_Manifesto);
	    return new ViagemRN ().getByRecord (ed);

	  }

  public void inclui_varios_ctos (HttpServletRequest request) throws Excecoes {
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
    String nr_Conhecimento_Inicial = request.getParameter ("FT_NR_Conhecimento_Inicial");
    String nr_Conhecimento_Final = request.getParameter ("FT_NR_Conhecimento_Final");
    String DT_Emissao = request.getParameter ("FT_DT_Emissao");
    String NR_Romaneio = request.getParameter ("FT_NR_Romaneio");
    String oid_Veiculo = request.getParameter ("oid_Veiculo");

    ViagemED ed = new ViagemED ();
    String OID_Usuario = request.getParameter ("oid_Usuario");
    if (JavaUtil.doValida (OID_Usuario)) {
        ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
    }

    ed.setDT_Viagem (request.getParameter ("FT_DT_Viagem"));
    ed.setHR_Viagem (request.getParameter ("FT_HR_Viagem"));
    ed.setDT_Previsao_Chegada (request.getParameter ("FT_DT_Previsao_Chegada"));
    ed.setHR_Previsao_Chegada (request.getParameter ("FT_HR_Previsao_Chegada"));
    ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("") && !String.valueOf (oid_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    if (String.valueOf (oid_Pessoa_Entregadora) != null && !String.valueOf (oid_Pessoa_Entregadora).equals ("") && !String.valueOf (oid_Pessoa_Entregadora).equals ("null")) {
      ed.setOID_Pessoa_Entregadora (oid_Pessoa_Entregadora);
    }
    if (String.valueOf (nr_Conhecimento_Inicial) != null && !String.valueOf (nr_Conhecimento_Inicial).equals ("") && !String.valueOf (nr_Conhecimento_Inicial).equals ("0") && !String.valueOf (nr_Conhecimento_Inicial).equals ("null")) {
      ed.setNR_Conhecimento_Inicial (new Long (nr_Conhecimento_Inicial).longValue ());
    }

    if (String.valueOf (nr_Conhecimento_Final) != null && !String.valueOf (nr_Conhecimento_Final).equals ("") && !String.valueOf (nr_Conhecimento_Inicial).equals ("0") && !String.valueOf (nr_Conhecimento_Final).equals ("null")) {
      ed.setNR_Conhecimento_Final (new Long (nr_Conhecimento_Final).longValue ());
    }

    if (String.valueOf (NR_Romaneio) != null && !String.valueOf (NR_Romaneio).equals ("") && !String.valueOf (NR_Romaneio).equals ("0") && !String.valueOf (NR_Romaneio).equals ("null")) {
          ed.setNR_Romaneio (new Long (NR_Romaneio).longValue ());
    }

    if (String.valueOf (DT_Emissao) != null && !String.valueOf (DT_Emissao).equals ("") && !String.valueOf (DT_Emissao).equals ("null")) {
      ed.setDT_Emissao (DT_Emissao);
    }
    if (JavaUtil.doValida (oid_Veiculo)) {
      ed.setOid_Veiculo (oid_Veiculo);
    }

    new ViagemRN ().inclui_varios_ctos (ed);
  }

  public void inclui_nota_fiscal_filtro (HttpServletRequest request) throws Excecoes {
	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    String oid_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
	    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
	    String DT_Emissao = request.getParameter ("FT_DT_Emissao");

	    ViagemED ed = new ViagemED ();
	    String OID_Usuario = request.getParameter ("oid_Usuario");
	    if (JavaUtil.doValida (OID_Usuario)) {
	        ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
	    }

	    ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));

	    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("") && !String.valueOf (oid_Unidade).equals ("null")) {
	      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
	    }
	    if (String.valueOf (oid_Pessoa_Remetente) != null && !String.valueOf (oid_Pessoa_Remetente).equals ("") && !String.valueOf (oid_Pessoa_Remetente).equals ("null")) {
		      ed.setOID_Pessoa(oid_Pessoa_Remetente);
		    }
	    if (String.valueOf (oid_Pessoa_Entregadora) != null && !String.valueOf (oid_Pessoa_Entregadora).equals ("") && !String.valueOf (oid_Pessoa_Entregadora).equals ("null")) {
	      ed.setOID_Pessoa_Entregadora (oid_Pessoa_Entregadora);
	    }

	    if (String.valueOf (DT_Emissao) != null && !String.valueOf (DT_Emissao).equals ("") && !String.valueOf (DT_Emissao).equals ("null")) {
	      ed.setDT_Emissao (DT_Emissao);
	    }

	    new ViagemRN ().inclui_nota_fiscal_filtro (ed);
	  }

  public void inclui_ctos_Rota (HttpServletRequest request) throws Excecoes {

    ViagemED ed = new ViagemED ();
    String OID_Usuario = request.getParameter ("oid_Usuario");
    if (JavaUtil.doValida (OID_Usuario)) {
        ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
    }

    ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));

    new ViagemRN ().inclui_ctos_Rota (ed);
  }

  public void inclui (HttpServletRequest request) throws Excecoes {

    try {
      ViagemRN Viagemrn = new ViagemRN ();
      ViagemED ed = new ViagemED ();

      //request em cima dos campos dos forms html

      String OID_Usuario = request.getParameter ("oid_Usuario");
      if (JavaUtil.doValida (OID_Usuario)) {
          ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
      }

      ed.setDT_Viagem (request.getParameter ("FT_DT_Viagem"));
      ed.setHR_Viagem (request.getParameter ("FT_HR_Viagem"));
      ed.setDT_Previsao_Chegada (request.getParameter ("FT_DT_Previsao_Chegada"));
      ed.setHR_Previsao_Chegada (request.getParameter ("FT_HR_Previsao_Chegada"));

      ed.setDT_Previsao_Entrega (request.getParameter ("FT_DT_Previsao_Entrega"));
      ed.setHR_Previsao_Entrega (request.getParameter ("FT_HR_Previsao_Entrega"));

      ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      ed.setOID_Processo (request.getParameter ("oid_Processo"));

      String OID_Unidade = request.getParameter ("oid_Unidade");
      // System.out.println (OID_Unidade);
      if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("") && !String.valueOf (OID_Unidade).equals ("null")) {
        ed.setOID_Unidade (new Long (OID_Unidade).longValue ());
      }

      String nr_Conhecimento = request.getParameter ("FT_NR_Conhecimento");
      // System.out.println (nr_Conhecimento);
      if (String.valueOf (nr_Conhecimento) != null && !String.valueOf (nr_Conhecimento).equals ("") && !String.valueOf (nr_Conhecimento).equals ("null")) {
        ed.setNR_Conhecimento (new Long (nr_Conhecimento).longValue ());
      }

      String nr_Manifesto = request.getParameter ("FT_NR_Manifesto");
      // System.out.println (nr_Manifesto);
      if (String.valueOf (nr_Manifesto) != null && !String.valueOf (nr_Manifesto).equals ("") && !String.valueOf (nr_Manifesto).equals ("null")) {
        ed.setNR_Manifesto (new Long (nr_Manifesto).longValue ());
      }

      String VL_Entrega = request.getParameter ("FT_VL_Entrega");
      // System.out.println (VL_Entrega);
      if (String.valueOf (VL_Entrega) != null && !String.valueOf (VL_Entrega).equals ("") && !String.valueOf (VL_Entrega).equals ("null")) {
        ed.setVL_Entrega (new Double (VL_Entrega).doubleValue ());
      }

      String oid_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
      if (String.valueOf (oid_Pessoa_Entregadora) != null && !String.valueOf (oid_Pessoa_Entregadora).equals ("") && !String.valueOf (oid_Pessoa_Entregadora).equals ("null")) {
        ed.setOID_Pessoa_Entregadora (oid_Pessoa_Entregadora);
      }

      ed.setDM_Tipo_Seguro ("E");
      String DM_Tipo_Seguro = request.getParameter ("FT_DM_Tipo_Seguro");
      if (String.valueOf (DM_Tipo_Seguro) != null && !String.valueOf (DM_Tipo_Seguro).equals ("null") && !String.valueOf (DM_Tipo_Seguro).equals ("")) {
        ed.setDM_Tipo_Seguro (request.getParameter ("FT_DM_Tipo_Seguro"));
      }

      ed.setDM_Lancado_Ordem_Frete ("VERIFICAR");
      String DM_Lancado_Ordem_Frete = request.getParameter ("FT_DM_Lancado_Ordem_Frete");
      if (String.valueOf (DM_Lancado_Ordem_Frete) != null && !String.valueOf (DM_Lancado_Ordem_Frete).equals ("null") && !String.valueOf (DM_Lancado_Ordem_Frete).equals ("")) {
        ed.setDM_Lancado_Ordem_Frete (request.getParameter ("FT_DM_Lancado_Ordem_Frete"));
      }

      ed.setDM_Tipo_Viagem ("T");
      String DM_Tipo_Viagem = request.getParameter ("FT_DM_Tipo_Viagem");
      if (String.valueOf (DM_Tipo_Viagem) != null &&
          !String.valueOf (DM_Tipo_Viagem).equals ("null") &&
          !String.valueOf (DM_Tipo_Viagem).equals ("")) {
        ed.setDM_Tipo_Viagem (request.getParameter ("FT_DM_Tipo_Viagem"));
      }

      Viagemrn.inclui (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public ViagemED geraViagem_CtoAvulso (HttpServletRequest request) throws Excecoes {

    ViagemED ed = new ViagemED ();
    try {
      ViagemRN Viagemrn = new ViagemRN ();
      //request em cima dos campos dos forms html
      String OID_Usuario = request.getParameter ("oid_Usuario");
      if (JavaUtil.doValida (OID_Usuario)) {
          ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
      }

      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      ed.setDM_Cia_Aerea(request.getParameter ("FT_DM_Cia_Aerea"));

      String VL_Ordem_Frete = request.getParameter ("FT_VL_Ordem_Frete");
      if (String.valueOf (VL_Ordem_Frete) != null &&
          !String.valueOf (VL_Ordem_Frete).equals ("") &&
          !String.valueOf (VL_Ordem_Frete).equals ("null")) {
        ed.setVL_Ordem_Frete (new Double (VL_Ordem_Frete).doubleValue ());
      }
      ed.setNR_Master (request.getParameter ("FT_NR_Master"));
      ed.setNR_Voo (request.getParameter ("FT_NR_Voo"));
      ed.setDT_Voo (request.getParameter ("FT_DT_Voo"));
      ed.setHR_Voo (request.getParameter ("FT_HR_Voo"));

      ed = Viagemrn.geraViagem_CtoAvulso (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public long viagemExiste (HttpServletRequest request) throws Excecoes {
    String oid_Manifesto = request.getParameter ("oid_Manifesto");
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    String oid_Processo = request.getParameter ("oid_Processo");
    if (JavaUtil.doValida (oid_Manifesto) && JavaUtil.doValida (oid_Conhecimento)) {
      return viagemExiste (oid_Manifesto , oid_Conhecimento , "T" , "","");
    }
    else {
      if (JavaUtil.doValida (oid_Manifesto) && JavaUtil.doValida (oid_Processo)) {
        return viagemExiste (oid_Manifesto , "" , "T" , oid_Processo,"");
      }
      else {
        throw new Excecoes ("Parâmetros inválidos");
      }
    }
  }

  public long viagemExiste (String oid_Manifesto , String oid_Conhecimento , String DM_Tipo_Viagem , String oid_Processo, String oid_Nota_Fiscal) throws Excecoes {
    return new ViagemRN ().viagemExiste (oid_Manifesto , oid_Conhecimento , DM_Tipo_Viagem , oid_Processo, oid_Nota_Fiscal);
  }

}
