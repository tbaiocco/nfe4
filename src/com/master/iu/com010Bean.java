package com.master.iu;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Duplica_TabelaED;
import com.master.rn.Duplica_TabelaRN;
import com.master.util.Excecoes;

public class com010Bean {

  public void copia_Tabela (HttpServletRequest request) throws Exception {

    try {
      Duplica_TabelaRN Duplica_Tabelarn = new Duplica_TabelaRN ();    
      Duplica_TabelaED ed = new Duplica_TabelaED ();

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
      ed.setDM_Aplicacao (request.getParameter ("FT_DM_Aplicacao"));
      ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
      ed.setOID_Produto_Origem (new Integer (request.getParameter ("oid_Produto_Origem")).intValue ());
      ed.setOID_Produto_Destino (new Integer (request.getParameter ("oid_Produto_Destino")).intValue ());

      Duplica_Tabelarn.copia_Tabela (ed);

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

  public void copia_Tabela_Fornecedor (HttpServletRequest request) throws Exception {

	    try {
	      Duplica_TabelaRN Duplica_Tabelarn = new Duplica_TabelaRN ();    
	      Duplica_TabelaED ed = new Duplica_TabelaED ();

	      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
	      ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
	      ed.setOID_Produto_Origem (new Integer (request.getParameter ("oid_Produto_Origem")).intValue ());
	      ed.setOID_Produto_Destino (new Integer (request.getParameter ("oid_Produto_Destino")).intValue ());

	      ed.setDM_Aplicacao("F");
	      ed.setDM_Tipo_Tabela("F");

	      Duplica_Tabelarn.copia_Tabela (ed);

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
  
  public void inclui_km (HttpServletRequest request) throws Exception {

    try {
      Duplica_TabelaRN Duplica_Tabelarn = new Duplica_TabelaRN ();
      Duplica_TabelaED ed = new Duplica_TabelaED ();

      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
      ed.setDM_Tipo_Tabela_Frete (request.getParameter ("FT_DM_Tipo_Tabela_Frete"));
      ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
      ed.setOID_Produto_Origem (new Integer (request.getParameter ("oid_Produto_Origem")).intValue ());
      ed.setOID_Produto_Destino (new Integer (request.getParameter ("oid_Produto_Destino")).intValue ());
      ed.setPE_Desconto (new Double (request.getParameter ("FT_PE_Desconto")).doubleValue ());

      ed.setPE_C_AD_Valorem (new Double (request.getParameter ("FT_PE_C_AD_Valorem")).doubleValue ());
      ed.setPE_D_AD_Valorem (new Double (request.getParameter ("FT_PE_D_AD_Valorem")).doubleValue ());
      ed.setPE_E_AD_Valorem (new Double (request.getParameter ("FT_PE_E_AD_Valorem")).doubleValue ());
      ed.setPE_R_AD_Valorem (new Double (request.getParameter ("FT_PE_R_AD_Valorem")).doubleValue ());

      Duplica_Tabelarn.inclui_km (ed);

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

}