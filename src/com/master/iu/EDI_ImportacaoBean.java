package com.master.iu;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_ImportacaoED;
import com.master.rn.Auxiliar1RN;
import com.master.rn.EDI_ImportacaoRN;
import com.master.root.UsuarioBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.SeparaEndereco;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author Ronaldo P
 */
public class EDI_ImportacaoBean
    extends BancoUtil {

  Parametro_FixoED param_Fixo = Parametro_FixoED.getInstancia ();

  public void importaArquivo (HttpServletRequest request) throws Excecoes {

    // // System.out.println ("inicio importaArquivo  ");

    try {
      EDI_ImportacaoED ed = new EDI_ImportacaoED ();

      EDI_ImportacaoRN edi_ImportacaoRN = new EDI_ImportacaoRN ();
      String arquivo = request.getParameter ("FT_NM_Arquivo");
//      String arquivo = request.getParameter ("arquivo");
      String padrao = request.getParameter ("FT_DM_Tipo_Padrao");
      String acao = request.getParameter ("acao");
      String pasta = request.getParameter ("FT_NM_Arquivo_Imp");

      acao = "PASTA";

      String oid_Pessoa = request.getParameter ("oid_Pessoa");
      if (JavaUtil.doValida (oid_Pessoa)) {
        ed.setOID_Pessoa (new Long (oid_Pessoa).longValue ());
        ed.setNR_CNPJ_CPF_Remetente (request.getParameter ("FT_NR_CNPJ_CPF"));
        ed.setNM_Razao_Social_Remetente (request.getParameter ("FT_NM_Razao_Social"));
      }

      // // System.out.println ("inicio  iu importacao arquivo=>  " + arquivo);
      // // System.out.println ("inicio    importacao acao=>  " + acao);
      // // System.out.println ("inicio    importacao padrao=>  " + padrao);
      // // System.out.println ("inicio importacao  pasta=>  " + pasta);

      edi_ImportacaoRN.importaArquivo (ed , acao , arquivo , padrao , pasta);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("ERRO NO LAY-OUT DO ARQUIVO. VOCE DEVE LIMPAR O DIRETORIO E REFAZER O PROCESSO !!! ");
      excecoes.setMetodo ("importaArquivo");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista_Nota_Fiscal_EDI (HttpServletRequest request) throws Excecoes {

    try {
      EDI_ImportacaoED ed = new EDI_ImportacaoED ();
      // // System.out.println ("bean 1");

      String DT_Emissao = request.getParameter ("FT_DT_Emissao");
      if (String.valueOf (DT_Emissao) != null && !String.valueOf (DT_Emissao).equals ("")) {
        ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
      }
      // // System.out.println ("bean2");
      String DT_Importacao = request.getParameter ("FT_DT_Importacao");
      if (String.valueOf (DT_Importacao) != null && !String.valueOf (DT_Importacao).equals ("")) {
        ed.setDT_Importacao (request.getParameter ("FT_DT_Importacao"));
      }

      // // System.out.println ("bean3");
      String DM_Situacao = request.getParameter ("FT_DM_Situacao");
      if (String.valueOf (DM_Situacao) != null && !String.valueOf (DM_Situacao).equals ("")) {
        ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));
      }
      String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
      if (String.valueOf (NR_Nota_Fiscal) != null && !String.valueOf (NR_Nota_Fiscal).equals ("")) {
        ed.setNR_NOTA_FISCAL(request.getParameter ("FT_NR_Nota_Fiscal"));
      }

      // // System.out.println ("bean4");
      ed.setDM_Tipo_EDI ("R");
      String DM_Tipo_EDI = request.getParameter ("FT_DM_Tipo_EDI");
      if (String.valueOf (DM_Tipo_EDI) != null && !String.valueOf (DM_Tipo_EDI).equals ("")) {
        ed.setDM_Tipo_EDI (request.getParameter ("FT_DM_Tipo_EDI"));
      }

      // // System.out.println ("bean5");
      String oid_Pessoa = request.getParameter ("oid_Pessoa");
      if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("0")) {
        ed.setNR_CNPJ_CPF_Remetente (oid_Pessoa);
      }
      // // System.out.println ("bean 9999");
      return new EDI_ImportacaoRN ().lista_Nota_Fiscal_EDI (ed);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("lista_Nota_Fiscal_EDI");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public boolean deleta (HttpServletRequest request) throws Excecoes {

    try {
      EDI_ImportacaoRN EDI_Importacaorn = new EDI_ImportacaoRN ();
      EDI_ImportacaoED ed = new EDI_ImportacaoED ();
      //request em cima dos campos dos forms html

      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      //// // System.out.println(ed.getOID_Nota_Fiscal() + "cheguei");
      return EDI_Importacaorn.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao deletar");
      excecoes.setMetodo ("deletar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public boolean deletaTudo (HttpServletRequest request) throws Excecoes {

    try {
      EDI_ImportacaoRN EDI_Importacaorn = new EDI_ImportacaoRN ();
      EDI_ImportacaoED ed = new EDI_ImportacaoED ();
      //request em cima dos campos dos forms html

      String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa");
      if (oid_Pessoa_Remetente != null && oid_Pessoa_Remetente.length () > 0) {
        ed.setNR_CNPJ_CPF_Remetente (request.getParameter ("oid_Pessoa"));
      }

      String DT_Importacao = request.getParameter ("FT_DT_Importacao");
      if (DT_Importacao != null && DT_Importacao.length () > 0) {
        ed.setDT_Importacao (DT_Importacao);
      }

      String OID_Unidade = request.getParameter ("oid_Unidade");
      if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("")
          && !String.valueOf (OID_Unidade).equals ("null")) {
        ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
      }
      return EDI_Importacaorn.deletaTudo (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao deletar");
      excecoes.setMetodo ("deletar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui (HttpServletRequest request) throws Excecoes {
	    String NM_Registro = "";
	    String tipo_Registro = "";
	    String chave = null;
	    String nr_nota_fiscal = "";

	    try {
	      EDI_ImportacaoRN EDI_Importacaorn = new EDI_ImportacaoRN ();
          EDI_ImportacaoED importaED = new EDI_ImportacaoED ();

          String NR_Peso = request.getParameter ("FT_NR_Peso");
          if (String.valueOf (NR_Peso) != null && !String.valueOf (NR_Peso).equals ("")
              && !String.valueOf (NR_Peso).equals ("null")) {
            importaED.setVL_Peso (new Double (NR_Peso).doubleValue ());
          }
          String NR_Volumes = request.getParameter ("FT_NR_Volumes");
          if (String.valueOf (NR_Volumes) != null && !String.valueOf (NR_Volumes).equals ("")
              && !String.valueOf (NR_Volumes).equals ("null")) {
            importaED.setNR_Volumes (new Double (request.getParameter ("FT_NR_Volumes")).doubleValue ());
          }
          String VL_Nota_Fiscal = request.getParameter ("FT_VL_Nota_Fiscal");
          if (String.valueOf (VL_Nota_Fiscal) != null && !String.valueOf (VL_Nota_Fiscal).equals ("")
              && !String.valueOf (VL_Nota_Fiscal).equals ("null")) {
            importaED.setVL_Nota_Fiscal (new Double (VL_Nota_Fiscal).doubleValue ());
          }

          importaED.setNR_Pedido (request.getParameter ("FT_NR_Pedido"));

          importaED.setNR_NOTA_FISCAL (request.getParameter ("FT_NR_Nota_Fiscal"));
          importaED.setNM_Serie ("1");

          importaED.setNR_CNPJ_CPF_Remetente (request.getParameter ("oid_Pessoa_Remetente"));
          importaED.setNM_Razao_Social_Remetente (request.getParameter ("FT_NM_Razao_Social_Remente"));
          importaED.setNR_CNPJ_CPF_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
          importaED.setNM_INSCRICAO_Remetente ("");
          importaED.setNM_Endereco_Remetente ("");
          importaED.setNM_Cidade_Remetente ("");
          importaED.setNR_CEP_Remetente ("");
          importaED.setCD_Estado_Remetente ("");
          importaED.setDT_Embarque (Data.getDataDMY ());

          importaED.setNR_CNPJ_CPF_Pagador (importaED.getNR_CNPJ_CPF_Destinatario ());

          importaED.setDM_Situacao ("I");

          importaED.setDM_Tipo_Padrao ("PADRAO");
          chave = String.valueOf (importaED.getNR_CNPJ_CPF_Remetente ());
          chave += String.valueOf (new Long (importaED.getNR_NOTA_FISCAL ()).longValue ());
          chave += String.valueOf (importaED.getNM_Serie ());
          chave += String.valueOf (importaED.getDM_Tipo_EDI ());
          importaED.setOID_Nota_Fiscal (SeparaEndereco.separaNumero (chave));

          importaED.setDT_Emissao_Nota_Fiscal (Data.getDataDMY ());

          importaED.setCD_Condicao_Frete ("F");
          importaED.setDM_Tipo_EDI ("R");
	      EDI_Importacaorn.inclui (importaED);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("erro ao Incluir");
	      excecoes.setMetodo ("inclui");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public EDI_ImportacaoED getByRecord (HttpServletRequest request) throws Excecoes {

    EDI_ImportacaoED ed = new EDI_ImportacaoED ();

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length () > 0) {
      ed.setNR_NOTA_FISCAL (request.getParameter ("FT_NR_Nota_Fiscal"));
    }

    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (oid_Pessoa_Remetente != null && oid_Pessoa_Remetente.length () > 0) {
      ed.setNR_CNPJ_CPF_Remetente (request.getParameter ("oid_Pessoa_Remetente"));
    }

    String NM_Serie = request.getParameter ("FT_NM_Serie");
    if (NM_Serie != null && !NM_Serie.equals ("") && !NM_Serie.equals ("null")) {
      ed.setNM_Serie (request.getParameter ("FT_NM_Serie"));
    }

    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));

    return new EDI_ImportacaoRN ().getByRecord (ed);

  }

  public EDI_ImportacaoED getByRecord_Nota_Com_Item (HttpServletRequest request) throws Excecoes {

    EDI_ImportacaoED ed = new EDI_ImportacaoED ();

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length () > 0) {
      ed.setNR_NOTA_FISCAL (request.getParameter ("FT_NR_Nota_Fiscal"));
    }

    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (oid_Pessoa_Remetente != null && oid_Pessoa_Remetente.length () > 0) {
      ed.setNR_CNPJ_CPF_Remetente (request.getParameter ("oid_Pessoa_Remetente"));
    }

    String NM_Serie = request.getParameter ("FT_NM_Serie");
    if (NM_Serie != null && !NM_Serie.equals ("") && !NM_Serie.equals ("null")) {
      ed.setNM_Serie (request.getParameter ("FT_NM_Serie"));
    }

    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));

    return new EDI_ImportacaoRN ().getByRecord_Nota_Com_Item (ed);

  }

  public boolean getByRecord_Diretorio_EDI (String Dirs) throws Excecoes {

    EDI_ImportacaoED ed = new EDI_ImportacaoED ();

    if (Dirs != null && Dirs.length () > 0) {
      ed.setNM_Arquivo (Dirs);
    }

    return new EDI_ImportacaoRN ().getByRecord_Diretorio_EDI (ed);

  }

  public String confirma_Nota_Fiscal (HttpServletRequest request) throws Excecoes {

    EDI_ImportacaoED ed = new EDI_ImportacaoED ();

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length () > 0) {
      ed.setNR_NOTA_FISCAL (request.getParameter ("FT_NR_Nota_Fiscal"));
    }

    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (oid_Pessoa_Remetente != null && oid_Pessoa_Remetente.length () > 0) {
      ed.setNR_CNPJ_CPF_Remetente (request.getParameter ("oid_Pessoa_Remetente"));
    }

    String NM_Serie = request.getParameter ("FT_NM_Serie");
    if (NM_Serie != null && !NM_Serie.equals ("") && !NM_Serie.equals ("null")) {
      ed.setNM_Serie (request.getParameter ("FT_NM_Serie"));
    }

    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));

    String OID_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("")
        && !String.valueOf (OID_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    }
    String OID_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (OID_Modal) != null && !String.valueOf (OID_Modal).equals ("")
        && !String.valueOf (OID_Modal).equals ("null")) {
      ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
    }
    String OID_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (OID_Produto) != null && !String.valueOf (OID_Produto).equals ("")
        && !String.valueOf (OID_Produto).equals ("null")) {
      ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    }
    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOID_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    if (String.valueOf (NR_Peso_Cubado) != null && !String.valueOf (NR_Peso_Cubado).equals ("")
        && !String.valueOf (NR_Peso_Cubado).equals ("null")) {
      ed.setNR_Peso_Cubado (new Double (request.getParameter ("FT_NR_Peso_Cubado")).doubleValue ());
    }

    return new EDI_ImportacaoRN ().confirma_Nota_Fiscal (ed);

  }

  public String confirma_Nota_Fiscal_Filtro (HttpServletRequest request) throws Excecoes {

    EDI_ImportacaoED ed = new EDI_ImportacaoED ();

    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (oid_Pessoa_Remetente != null && oid_Pessoa_Remetente.length () > 0) {
      ed.setNR_CNPJ_CPF_Remetente (request.getParameter ("oid_Pessoa_Remetente"));
    }

    String DT_Importacao = request.getParameter ("FT_DT_Importacao");
    if (DT_Importacao != null && DT_Importacao.length () > 0) {
      ed.setDT_Importacao (DT_Importacao);
    }

    String OID_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("")
        && !String.valueOf (OID_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    }

    String OID_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (OID_Modal) != null && !String.valueOf (OID_Modal).equals ("")
        && !String.valueOf (OID_Modal).equals ("null")) {
      ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
    }
    String OID_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (OID_Produto) != null && !String.valueOf (OID_Produto).equals ("")
        && !String.valueOf (OID_Produto).equals ("null")) {
      ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    }
    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOID_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }

    return new EDI_ImportacaoRN ().confirma_Nota_Fiscal_Filtro (ed);

  }

  public long gera_Conhecimento (HttpServletRequest request) throws Excecoes {

    EDI_ImportacaoED ed = new EDI_ImportacaoED ();

    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (oid_Pessoa_Remetente != null && oid_Pessoa_Remetente.length () > 0) {
      ed.setNR_CNPJ_CPF_Remetente (request.getParameter ("oid_Pessoa_Remetente"));
    }
    String OID_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (OID_Unidade) != null && !String.valueOf (OID_Unidade).equals ("")
        && !String.valueOf (OID_Unidade).equals ("null")) {
      ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());
    }
    String OID_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (OID_Produto) != null && !String.valueOf (OID_Produto).equals ("")
        && !String.valueOf (OID_Produto).equals ("null")) {
      ed.setOID_Produto (new Long (request.getParameter ("oid_Produto")).longValue ());
    }
    String OID_Deposito = request.getParameter ("oid_Deposito");
    if (String.valueOf (OID_Deposito) != null && !String.valueOf (OID_Deposito).equals ("")
        && !String.valueOf (OID_Deposito).equals ("null")) {
      ed.setOID_Deposito (new Long (request.getParameter ("oid_Deposito")).longValue ());
    }
    String OID_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (OID_Modal) != null && !String.valueOf (OID_Modal).equals ("")
        && !String.valueOf (OID_Modal).equals ("null")) {
      ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
    }
    String DT_Importacao = request.getParameter ("FT_DT_Importacao");
    if (String.valueOf (DT_Importacao) != null && !String.valueOf (DT_Importacao).equals ("")) {
      ed.setDT_Importacao (request.getParameter ("FT_DT_Importacao"));
    }

    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    if (String.valueOf (DM_Tipo_Documento) != null && !String.valueOf (DM_Tipo_Documento).equals ("")) {
      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
    }

    String CD_Rota_Entrega = request.getParameter ("FT_CD_Rota_Entrega");
    if (JavaUtil.doValida (CD_Rota_Entrega)) {
      ed.setCD_Rota_Entrega (request.getParameter ("FT_CD_Rota_Entrega"));
    }

    String OID_Usuario = request.getParameter ("oid_Usuario");
    if (String.valueOf (OID_Usuario) != null && !String.valueOf (OID_Usuario).equals ("")
        && !String.valueOf (OID_Usuario).equals ("null")) {
      ed.setUser (new Integer(request.getParameter ("oid_Usuario")).intValue ());
    }else {
       ed.setUser (UsuarioBean.getUsuarioCorrente (request).getOid_Usuario ().intValue ());
    }


    return new EDI_ImportacaoRN ().gera_Conhecimento (ed);

  }

  public void ediMuller(String arquivo)throws Excecoes{

    try {
	  EDI_ImportacaoRN ediMullerRN = new EDI_ImportacaoRN ();
	  ediMullerRN.ediMuller (arquivo);
    }

    catch (Excecoes exc) {
      throw exc;
    }
  }

}