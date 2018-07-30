package com.master.iu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.DuplicataED;
import com.master.ed.DuplicataPesquisaED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.rn.BloquetoRN;
import com.master.rn.DuplicataRN;
import com.master.rn.DuplicataRelRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.ed.Parametro_FixoED;

public class BloquetoBean
    extends JavaUtil {

  public DuplicataED geraNumeroBloqueto (HttpServletRequest request) throws Excecoes {

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");
    String NR_Bloqueto = request.getParameter ("FT_NR_Bloqueto");
    String NR_Digito_Bloqueto = request.getParameter ("FT_NR_Digito_Bloqueto");
    String CD_Banco = request.getParameter ("FT_CD_Banco");

    DuplicataED ed = new DuplicataED ();

    if (doValida (oid_Pessoa))
      ed.setOid_Pessoa (oid_Pessoa);
    if (doValida (oid_Carteira))
      ed.setOid_Carteira (new Integer (oid_Carteira));
    if (doValida (NR_Duplicata))
      ed.setNr_Duplicata (new Integer (NR_Duplicata));
    if (doValida (NR_Bloqueto))
      ed.setNR_Bloqueto_informado (NR_Bloqueto);
    if (doValida (NR_Digito_Bloqueto))
      ed.setNR_Digito_Bloqueto (NR_Digito_Bloqueto);
    if (doValida (CD_Banco))
      ed.setCD_Banco (CD_Banco);
    if (doValida (NR_Duplicata_Final))
      ed.setNr_Duplicata_Final (new Integer (NR_Duplicata_Final));

    return new BloquetoRN ().geraNumeroBloqueto (ed);
  }

  public DuplicataED excluiNumeroBloqueto (HttpServletRequest request) throws Excecoes {

    String oid_Carteira = request.getParameter ("oid_Carteira");
    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");
    DuplicataED ed = new DuplicataED ();

    if (doValida (oid_Carteira))
      ed.setOid_Carteira (new Integer (oid_Carteira));
    if (doValida (NR_Duplicata))
      ed.setNr_Duplicata (new Integer (NR_Duplicata));
    if (doValida (NR_Duplicata_Final))
      ed.setNr_Duplicata_Final (new Integer (NR_Duplicata_Final));

    return new BloquetoRN ().excluiNumeroBloqueto (ed);
  }


  public byte[] imprime_Bloqueto (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    DuplicataPesquisaED ed = this.montaED(request);
    return new BloquetoRN ().imprime_Bloqueto (ed);
  }

  public String imprime_BloquetoMatricial (HttpServletRequest request) throws Excecoes {

    DuplicataPesquisaED ed =  ed=this.montaED(request);
    return new BloquetoRN ().imprime_BloquetoMatricial (ed);
  }


  public DuplicataPesquisaED montaED (HttpServletRequest request ) throws Excecoes {

    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");
    String NR_Bloqueto = request.getParameter ("FT_NR_Bloqueto");
    String NR_Digito_Bloqueto = request.getParameter ("FT_NR_Digito_Bloqueto");
    String CD_Banco = request.getParameter ("FT_CD_Banco");

    if (doValida (oid_Pessoa))
      ed.setOid_Pessoa (oid_Pessoa);
    if (doValida (oid_Carteira))
      ed.setOid_Carteira (new Integer (oid_Carteira));
    if (doValida (dt_Emissao_Inicial))
      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
    if (doValida (dt_Emissao_Final))
      ed.setData_Emissao_Final (dt_Emissao_Final);
    if (doValida (NR_Duplicata))
      ed.setNr_Duplicata (new Integer (NR_Duplicata));
    if (doValida (NR_Bloqueto))
      ed.setNR_Bloqueto_informado (NR_Bloqueto);
    if (doValida (NR_Digito_Bloqueto))
      ed.setNR_Digito_Bloqueto (NR_Digito_Bloqueto);
    if (doValida (CD_Banco))
      ed.setCD_Banco (CD_Banco);
    if (doValida (NR_Duplicata_Final))
      ed.setNr_Duplicata_Final (new Integer (NR_Duplicata_Final));

    ed.setDM_Relatorio(request.getParameter ("FT_DM_Relatorio"));
    return ed;
  }

  //para JBoleto
  public void imprime_JBoleto (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

	    String oid_Pessoa = request.getParameter ("oid_Pessoa");
	    String oid_Carteira = request.getParameter ("oid_Carteira");
	    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
	    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
	    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
	    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");
	    String oid_Duplicata = request.getParameter ("oid_Duplicata");

	    DuplicataPesquisaED ed = new DuplicataPesquisaED ();
	    ed.setResponse(Response);

	    if (oid_Duplicata != null && !oid_Duplicata.equals ("")) {
	      ed.setOid_Duplicata (new Long (oid_Duplicata).longValue());
	    }

	    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
	      ed.setOid_Pessoa (oid_Pessoa);
	    }

	    if (oid_Carteira != null && !oid_Carteira.equals ("")) {
	      ed.setOid_Carteira (new Integer (oid_Carteira));
	    }

	    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
	      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
	    }

	    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
	      ed.setData_Emissao_Final (dt_Emissao_Final);
	    }

	    if (NR_Duplicata != null && !NR_Duplicata.equals ("")) {
	      ed.setNr_Duplicata (new Integer (NR_Duplicata));
	    }

	    if (NR_Duplicata_Final != null && !NR_Duplicata_Final.equals ("")) {
	      ed.setNr_Duplicata_Final (new Integer (NR_Duplicata_Final));
	    }

	    ed.setDM_Relatorio (Parametro_FixoED.getInstancia().getDM_Tipo_Impressao_Fatura());

	    new BloquetoRN().imprime_JBoleto(ed, Response);
	  }


}