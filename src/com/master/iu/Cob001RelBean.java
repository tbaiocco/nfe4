package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;
import com.master.util.ed.*;




public class Cob001RelBean {

  public byte[] geraRelTitulos (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String dt_Vencimento_Inicial = request.getParameter ("FT_DT_Vencimento_Inicial");
    String dt_Vencimento_Final = request.getParameter ("FT_DT_Vencimento_Final");
    String dt_Credito_Inicial = request.getParameter ("FT_DT_Credito_Inicial");
    String dt_Credito_Final = request.getParameter ("FT_DT_Credito_Final");
    String NR_Remessa = request.getParameter("FT_NR_Remessa");

    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }
    ed.setNr_Remessa("");
    if (NR_Remessa != null && !NR_Remessa.equals ("") && !NR_Remessa.equals ("null")) {
    	ed.setNr_Remessa(NR_Remessa);
    }

    if (oid_Unidade != null && !oid_Unidade.equals ("")) {
      ed.setOid_Unidade (new Long (request.getParameter ("oid_Unidade")));
    }

    if (oid_Grupo_Economico != null && !oid_Grupo_Economico.equals ("")) {
      ed.setOid_Grupo_Economico (new Long (request.getParameter ("oid_Grupo_Economico")));
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

    if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals ("")) {
      ed.setData_Vencimento_Inicial (dt_Vencimento_Inicial);
    }

    if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals ("")) {
      ed.setData_Vencimento_Final (dt_Vencimento_Final);
    }

    if (dt_Credito_Inicial != null && !dt_Credito_Inicial.equals ("")) {
      ed.setData_Credito_Inicial (dt_Credito_Inicial);
    }

    if (dt_Credito_Final != null && !dt_Credito_Final.equals ("")) {
      ed.setData_Credito_Final (dt_Credito_Final);
    }

    ed.setDM_Quebra (request.getParameter ("FT_DM_Quebra"));
    ed.setDM_Cliente (request.getParameter ("FT_DM_Cliente"));
    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    ed.setDM_Classificar (request.getParameter ("FT_DM_Classificar"));

    ed.setDM_Totaliza_Vencimento (request.getParameter ("FT_DM_Totaliza_Vencimento"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));
    if(ed.getDM_Relatorio().equals("RT8"))
    	ed.setDM_Quebra("X");

    ed.setDM_Origem (request.getParameter ("FT_DM_Origem"));

    ed.setDM_Atualiza_Saldo (request.getParameter ("FT_DM_Atualiza_Saldo"));

    String vl_Cotacao_Informada = request.getParameter ("FT_VL_Cotacao_Informada");
    if (JavaUtil.doValida (vl_Cotacao_Informada)) {
      ed.setVL_Cotacao_Informada (Double.valueOf (vl_Cotacao_Informada).doubleValue ());
    }

    return new DuplicataRelRN ().geraRelTitulos (ed);
  }

  public ArrayList geraEDIRelTitulos (HttpServletRequest request) throws Excecoes {

	    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

	    String oid_Pessoa = request.getParameter ("oid_Pessoa");
	    String oid_Carteira = request.getParameter ("oid_Carteira");
	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
	    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
	    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
	    String dt_Vencimento_Inicial = request.getParameter ("FT_DT_Vencimento_Inicial");
	    String dt_Vencimento_Final = request.getParameter ("FT_DT_Vencimento_Final");
	    String dt_Credito_Inicial = request.getParameter ("FT_DT_Credito_Inicial");
	    String dt_Credito_Final = request.getParameter ("FT_DT_Credito_Final");
	    String NR_Remessa = request.getParameter("FT_NR_Remessa");

	    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
	      ed.setOid_Pessoa (oid_Pessoa);
	    }
	    ed.setNr_Remessa("");
	    if (NR_Remessa != null && !NR_Remessa.equals ("") && !NR_Remessa.equals ("null")) {
	    	ed.setNr_Remessa(NR_Remessa);
	    }

	    if (oid_Unidade != null && !oid_Unidade.equals ("") && !oid_Unidade.equals ("null")) {
	      ed.setOid_Unidade (new Long (request.getParameter ("oid_Unidade")));
	    }

	    if (oid_Grupo_Economico != null && !oid_Grupo_Economico.equals ("") && !oid_Grupo_Economico.equals ("null")) {
	      ed.setOid_Grupo_Economico (new Long (request.getParameter ("oid_Grupo_Economico")));
	    }

	    if (oid_Carteira != null && !oid_Carteira.equals ("") && !oid_Carteira.equals ("null")) {
	      ed.setOid_Carteira (new Integer (oid_Carteira));
	    }

	    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("") && !dt_Emissao_Inicial.equals ("null")) {
	      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
	    }

	    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("") && !dt_Emissao_Final.equals ("null")) {
	      ed.setData_Emissao_Final (dt_Emissao_Final);
	    }

	    if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals ("") && !dt_Vencimento_Inicial.equals ("null")) {
	      ed.setData_Vencimento_Inicial (dt_Vencimento_Inicial);
	    }

	    if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals ("") && !dt_Vencimento_Final.equals ("null")) {
	      ed.setData_Vencimento_Final (dt_Vencimento_Final);
	    }

	    if (dt_Credito_Inicial != null && !dt_Credito_Inicial.equals ("") && !dt_Credito_Inicial.equals ("null")) {
	      ed.setData_Credito_Inicial (dt_Credito_Inicial);
	    }

	    if (dt_Credito_Final != null && !dt_Credito_Final.equals ("") && !dt_Credito_Final.equals ("null")) {
	      ed.setData_Credito_Final (dt_Credito_Final);
	    }

	    ed.setDM_Quebra (request.getParameter ("FT_DM_Quebra"));
	    ed.setDM_Cliente (request.getParameter ("FT_DM_Cliente"));
	    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

	    ed.setDM_Classificar (request.getParameter ("FT_DM_Classificar"));

	    ed.setDM_Totaliza_Vencimento (request.getParameter ("FT_DM_Totaliza_Vencimento"));

	    ed.setDM_Origem (request.getParameter ("FT_DM_Origem"));

	    return new DuplicataRelRN ().geraEdiRelTitulos (ed);
	  }

  public void geraRelTitVencUnidade (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String cd_Duplicata = request.getParameter ("FT_CD_Duplicata");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String dt_Vencimento_Inicial = request.getParameter ("FT_DT_Vencimento_Inicial");
    String dt_Vencimento_Final = request.getParameter ("FT_DT_Vencimento_Final");
    String nm_Razao_Social = request.getParameter ("FT_NM_Razao_Social");

    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }

    if (cd_Duplicata != null && !cd_Duplicata.equals ("")) {
      ed.setNr_Duplicata (new Integer (cd_Duplicata));
    }

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setData_Emissao_Final (dt_Emissao_Final);
    }

    if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals ("")) {
      ed.setData_Vencimento_Inicial (dt_Vencimento_Inicial);
    }

    if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals ("")) {
      ed.setData_Vencimento_Final (dt_Vencimento_Final);
    }

    if (nm_Razao_Social != null && !nm_Razao_Social.equals ("")) {
      ed.setNm_Razao_Social (nm_Razao_Social);
    }

    DuplicataRelRN DuplicataRelRN = new DuplicataRelRN ();
    new EnviaPDF ().enviaBytes (request , Response , DuplicataRelRN.geraRelTitVencUnidade (ed));

  }

  public byte[] geraDiario_Auxiliar_Clientes (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }

    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setData_Emissao_Final (dt_Emissao_Final);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    DuplicataRelRN DuplicataRelRN = new DuplicataRelRN ();

    return DuplicataRelRN.geraDiario_Auxiliar_Clientes (ed);

  }

  public void geraRelTitEmitidosPeriodo (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String cd_Duplicata = request.getParameter ("FT_CD_Duplicata");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String dt_Vencimento_Inicial = request.getParameter ("FT_DT_Vencimento_Inicial");
    String dt_Vencimento_Final = request.getParameter ("FT_DT_Vencimento_Final");
    String nm_Razao_Social = request.getParameter ("FT_NM_Razao_Social");

    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }

    if (oid_Carteira != null && !oid_Carteira.equals ("")) {
      ed.setOid_Carteira (new Integer (oid_Carteira));
    }

    if (cd_Duplicata != null && !cd_Duplicata.equals ("")) {
      ed.setNr_Duplicata (new Integer (cd_Duplicata));
    }

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setData_Emissao_Final (dt_Emissao_Final);
    }

    if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals ("")) {
      ed.setData_Vencimento_Inicial (dt_Vencimento_Inicial);
    }

    if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals ("")) {
      ed.setData_Vencimento_Final (dt_Vencimento_Final);
    }

    if (nm_Razao_Social != null && !nm_Razao_Social.equals ("")) {
      ed.setNm_Razao_Social (nm_Razao_Social);
    }

    DuplicataRelRN DuplicataRelRN = new DuplicataRelRN ();
    new EnviaPDF ().enviaBytes (request , Response , DuplicataRelRN.geraRelTitEmitidosPeriodo (ed));

  }

  public void geraRelTitLiquidadosCart (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String cd_Duplicata = request.getParameter ("FT_CD_Duplicata");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String dt_Movimento_Inicial = request.getParameter ("FT_DT_Movimento_Inicial");
    String dt_Movimento_Final = request.getParameter ("FT_DT_Movimento_Final");
    String nm_Razao_Social = request.getParameter ("FT_NM_Razao_Social");

    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }

    if (oid_Carteira != null && !oid_Carteira.equals ("")) {
      ed.setOid_Carteira (new Integer (oid_Carteira));
    }

    if (oid_Unidade != null && !oid_Unidade.equals ("")) {
      ed.setOid_Unidade (new Long (oid_Unidade));
    }

    if (cd_Duplicata != null && !cd_Duplicata.equals ("")) {
      ed.setNr_Duplicata (new Integer (cd_Duplicata));
    }

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setData_Emissao_Final (dt_Emissao_Final);
    }

    if (dt_Movimento_Inicial != null && !dt_Movimento_Inicial.equals ("")) {
      ed.setData_Movimento_Inicial (dt_Movimento_Inicial);
    }

    if (dt_Movimento_Final != null && !dt_Movimento_Final.equals ("")) {
      ed.setData_Movimento_Final (dt_Movimento_Final);
    }

    if (nm_Razao_Social != null && !nm_Razao_Social.equals ("")) {
      ed.setNm_Razao_Social (nm_Razao_Social);
    }

    ed.setDM_Quebra (request.getParameter ("FT_DM_Quebra"));

    DuplicataRelRN DuplicataRelRN = new DuplicataRelRN ();
    new EnviaPDF ().enviaBytes (request , Response , DuplicataRelRN.geraRelTitLiquidadosCart (ed));

  }

  public byte[] geraRelTitCliente (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String cd_Duplicata = request.getParameter ("FT_CD_Duplicata");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String dt_Vencimento_Inicial = request.getParameter ("FT_DT_Vencimento_Inicial");
    String dt_Vencimento_Final = request.getParameter ("FT_DT_Vencimento_Final");
    String nm_Razao_Social = request.getParameter ("FT_NM_Razao_Social");

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }

    if (oid_Carteira != null && !oid_Carteira.equals ("")) {
      ed.setOid_Carteira (new Integer (oid_Carteira));
    }

    if (cd_Duplicata != null && !cd_Duplicata.equals ("")) {
      ed.setNr_Duplicata (new Integer (cd_Duplicata));
    }

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setData_Emissao_Final (dt_Emissao_Final);
    }

    if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals ("")) {
      ed.setData_Vencimento_Inicial (dt_Vencimento_Inicial);
    }

    if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals ("")) {
      ed.setData_Vencimento_Final (dt_Vencimento_Final);
    }

    if (nm_Razao_Social != null && !nm_Razao_Social.equals ("")) {
      ed.setNm_Razao_Social (nm_Razao_Social);
    }

    return new DuplicataRelRN ().geraRelTitCliente (ed);

  }

  public byte[] geraRelTitulosDespesaCobranca (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String dt_Movimento_Inicial = request.getParameter ("FT_DT_Movimento_Inicial");
    String dt_Movimento_Final = request.getParameter ("FT_DT_Movimento_Final");

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

    if (dt_Movimento_Inicial != null && !dt_Movimento_Inicial.equals ("")) {
      ed.setData_Movimento_Inicial (dt_Movimento_Inicial);
    }

    if (dt_Movimento_Final != null && !dt_Movimento_Final.equals ("")) {
      ed.setData_Movimento_Final (dt_Movimento_Final);
    }

    return new DuplicataRelRN ().geraRelTitulosDespesaCobranca (ed);

  }

  public byte[] imprime_Fatura_PDF (HttpServletRequest request , HttpServletResponse Response) throws Exception {
	    DuplicataPesquisaED ed = this.carregaED(request, Response);
	    return new DuplicataRelRN ().geraRelFatura (ed);
  }

  public void imprime_ProtocoloJasper (HttpServletRequest request , HttpServletResponse Response) throws Exception {
	    DuplicataPesquisaED ed = this.carregaED(request, Response);
	    new DuplicataRelRN().geraProtocoloJasper(ed);
}
  public void imprime_ReciboAdiantamentoJasper (HttpServletRequest request , HttpServletResponse Response) throws Exception {
	    DuplicataPesquisaED ed = this.carregaED(request, Response);
	    new DuplicataRelRN().ReciboAdiantamentoJasper(ed);
}

  public String imprime_Fatura_Matricial (HttpServletRequest request , HttpServletResponse Response) throws Exception {
	    DuplicataPesquisaED ed = this.carregaED(request, Response);
	    return new DuplicataRelRN ().imprime_Fatura_Matricial (ed);
  }

  public byte[] geraRelFatura (HttpServletRequest request , HttpServletResponse Response) throws Exception {
	    String oid_Pessoa = request.getParameter ("oid_Pessoa");
	    String oid_Carteira = request.getParameter ("oid_Carteira");
	    String oid_Duplicata = request.getParameter ("oid_Duplicata");
	    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
	    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
	    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
	    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");
	    String Relatorio = request.getParameter ("Relatorio");
	    String DM_Tipo_Documento = request.getParameter ("DM_Tipo_Documento");

	    DuplicataPesquisaED ed = new DuplicataPesquisaED (Response , Relatorio);

	    if (DM_Tipo_Documento != null && !DM_Tipo_Documento.equals ("")) {
	      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
	    }

	    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
	      ed.setOid_Pessoa (oid_Pessoa);
	    }

	    if (oid_Carteira != null && !oid_Carteira.equals ("")) {
	      ed.setOid_Carteira (new Integer (oid_Carteira));
	    }

	    if (oid_Duplicata != null && !oid_Duplicata.equals ("")) {
	      ed.setOid_Duplicata (new Long(oid_Duplicata).longValue());
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

	    ed.setDM_Relatorio (Parametro_FixoED.getInstancia ().getDM_Tipo_Impressao_Fatura ());

	    ed.setDescFiltro ("");

	    return new DuplicataRelRN ().geraRelFatura (ed);
  }

  public byte[] geraDemonstrativo_Cobranca (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");
    String oid_Duplicata = request.getParameter ("oid_Duplicata");

    if (oid_Duplicata != null && !oid_Duplicata.equals ("")) {
      ed.setOid_Duplicata (new Long(oid_Duplicata).longValue());
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

    return new DuplicataRelRN ().geraDemonstrativo_Cobranca (ed);
  }

  public byte[] geraProtocolo_Cobranca (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Carteira = request.getParameter ("oid_Carteira");
    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");

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

    DuplicataRelRN DuplicataRelRN = new DuplicataRelRN ();
    return DuplicataRelRN.geraProtocolo_Cobranca (ed);
  }

  public void geraRelTitEDI (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Carteira = request.getParameter ("oid_Carteira");
    String NR_Remessa = request.getParameter ("FT_NR_Remessa");

    ed.setNr_Remessa (NR_Remessa);
    if (oid_Carteira != null && !oid_Carteira.equals ("")) {
      ed.setOid_Carteira (new Integer (oid_Carteira));
    }

    DuplicataRelRN DuplicataRelRN = new DuplicataRelRN ();
    new EnviaPDF ().enviaBytes (request , Response , DuplicataRelRN.geraRelTitEDI (ed));

  }

  public void geraDiario_Razao_Clientes (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    DuplicataPesquisaED ed = new DuplicataPesquisaED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
      ed.setOid_Pessoa (oid_Pessoa);
    }

    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");

    if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals ("")) {
      ed.setData_Emissao_Inicial (dt_Emissao_Inicial);
    }

    if (dt_Emissao_Final != null && !dt_Emissao_Final.equals ("")) {
      ed.setData_Emissao_Final (dt_Emissao_Final);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    String origem = request.getParameter ("FT_DM_Origem");
    if (JavaUtil.doValida (origem)) {
      if (origem.equals ("FAT")) {
        new DuplicataRelRN ().geraDiario_Razao_Clientes (ed , Response);
      }
      else if (origem.equals ("CTRC")) {
        new DuplicataRelRN ().geraDiario_Razao_Clientes (ed , Response);
      }
      else if (origem.equals ("CRT")) {
        new DuplicataRelRN ().geraDiario_Razao_Clientes (ed , Response);
      }
    }
    else {
      throw new Mensagens ("Selecione uma Origem para o Diário/Razão!");
    }

  }

  private DuplicataPesquisaED carregaED (HttpServletRequest request , HttpServletResponse Response) throws Exception {

	    String oid_Pessoa = request.getParameter ("oid_Pessoa");
	    String oid_Carteira = request.getParameter ("oid_Carteira");
	    String oid_Duplicata = request.getParameter ("oid_Duplicata");
	    String oid_Movimento_Duplicata = request.getParameter ("oid_Movimento_Duplicata");
	    String dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
	    String dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
	    String NR_Duplicata = request.getParameter ("FT_NR_Duplicata");
	    String NR_Duplicata_Final = request.getParameter ("FT_NR_Duplicata_Final");
	    String Relatorio = request.getParameter ("Relatorio");
	    String DM_Tipo_Documento = request.getParameter ("DM_Tipo_Documento");

	    DuplicataPesquisaED ed = new DuplicataPesquisaED (Response , Relatorio);

	    if (DM_Tipo_Documento != null && !DM_Tipo_Documento.equals ("")) {
	      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
	    }

	    if (oid_Pessoa != null && !oid_Pessoa.equals ("")) {
	      ed.setOid_Pessoa (oid_Pessoa);
	    }

	    if (oid_Carteira != null && !oid_Carteira.equals ("")) {
	      ed.setOid_Carteira (new Integer (oid_Carteira));
	    }

	    if (oid_Duplicata != null && !oid_Duplicata.equals ("")) {
	      ed.setOid_Duplicata (new Long(oid_Duplicata).longValue());
	    }

	    if (oid_Movimento_Duplicata != null && !oid_Movimento_Duplicata.equals ("")) {
		      ed.setOID_Movimento_Duplicata(oid_Movimento_Duplicata);
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

	    ed.setDM_Relatorio (Parametro_FixoED.getInstancia ().getDM_Tipo_Impressao_Fatura ());

	    ed.setDescFiltro ("");

	    return ed;
	  }

}
