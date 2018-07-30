package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.PostagemED;
import com.master.rn.PostagemRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class PostagemBean {

  Utilitaria util = new Utilitaria ();

  ///peso cubado
  public PostagemED inclui (HttpServletRequest request) throws Excecoes {
    PostagemRN PostagemRN = new PostagemRN ();
    PostagemED ed = new PostagemED ();

    String NM_Especie = request.getParameter ("FT_NM_Especie");

    //request em cima dos campos dos forms html

    String NR_Postagem = request.getParameter ("FT_NR_Postagem");
    if (String.valueOf (NR_Postagem) != null && !String.valueOf (NR_Postagem).equals ("")
        && !String.valueOf (NR_Postagem).equals ("null")) {
      ed.setNR_Postagem (new Long (request.getParameter ("FT_NR_Postagem")).longValue ());
    }

    ed.setCD_Destino (request.getParameter ("FT_CD_Destino"));
    ed.setNM_Servico (request.getParameter ("FT_NM_Servico"));
    ed.setNM_Unidade_Postagem (request.getParameter ("FT_NM_Unidade_Postagem"));
    ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Destinatario (request.getParameter ("oid_Pessoa_Destinatario"));
    ed.setDT_Postagem (request.getParameter ("FT_DT_Postagem"));
    ed.setNR_Fatura (request.getParameter ("FT_NR_Fatura"));

    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    String NR_Peso = request.getParameter ("FT_NR_Peso");
    if (String.valueOf (NR_Peso) != null && !String.valueOf (NR_Peso).equals ("")
        && !String.valueOf (NR_Peso).equals ("null")) {
      ed.setNR_Peso (new Double (NR_Peso).doubleValue ());
    }

    // System.out.println ("CUBADO!!! > " + request.getParameter ("FT_NR_Peso_Cubado"));
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    if (String.valueOf (NR_Peso_Cubado) != null && !String.valueOf (NR_Peso_Cubado).equals ("")
        && !String.valueOf (NR_Peso_Cubado).equals ("null")) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }

    ed.setNR_Lote ("");
    String NR_Lote = request.getParameter ("FT_NR_Lote");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    // System.out.println ("nf11111");

    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    if (String.valueOf (NR_Volumes) != null && !String.valueOf (NR_Volumes).equals ("")
        && !String.valueOf (NR_Volumes).equals ("null")) {
      ed.setNR_Volumes (new Double (request.getParameter ("FT_NR_Volumes")).doubleValue ());
    }

    String VL_Postagem = request.getParameter ("FT_VL_Postagem");
    if (String.valueOf (VL_Postagem) != null && !String.valueOf (VL_Postagem).equals ("")
        && !String.valueOf (VL_Postagem).equals ("null")) {
      ed.setVL_Postagem (new Double (VL_Postagem).doubleValue ());
    }

    // System.out.println ("nf6");

    String OID_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (OID_Modal) != null && !String.valueOf (OID_Modal).equals ("")
        && !String.valueOf (OID_Modal).equals ("null")) {
      ed.setOID_Modal (new Long (request.getParameter ("oid_Modal")).longValue ());
    }

    // System.out.println ("nf7");

    return PostagemRN.inclui (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {
    PostagemED ed = new PostagemED ();
    String oid_Postagem = request.getParameter ("oid_Postagem");
    String NR_Postagem = request.getParameter ("FT_NR_Postagem");
    String CD_Destino = request.getParameter ("FT_CD_Destino");
    String NM_Servico = request.getParameter ("FT_NM_Servico");
    String NM_Unidade_Postagem = request.getParameter ("FT_NM_Unidade_Postagem");
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    String DT_Postagem = request.getParameter ("FT_DT_Postagem");
    String NR_Fatura = request.getParameter ("FT_NR_Fatura");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String NR_Peso = request.getParameter ("FT_NR_Peso");
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    String VL_Postagem = request.getParameter ("FT_VL_Postagem");
    String NR_Lote = request.getParameter ("FT_NR_Lote");
    String oid_Modal = request.getParameter ("oid_Modal");

    if (util.doValida (oid_Postagem)) {
      ed.setOID_Postagem (oid_Postagem);
    }
    if (util.doValida (NR_Postagem)) {
      ed.setNR_Postagem (Long.parseLong (NR_Postagem));
    }
    if (util.doValida (CD_Destino)) {
      ed.setCD_Destino (CD_Destino);
    }
    if (util.doValida (NM_Servico)) {
      ed.setNM_Servico (NM_Servico);
    }
    if (util.doValida (NM_Unidade_Postagem)) {
      ed.setNM_Unidade_Postagem (NM_Unidade_Postagem);
    }
    if (util.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (util.doValida (oid_Pessoa_Destinatario)) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    if (util.doValida (DT_Postagem)) {
      ed.setDT_Postagem (DT_Postagem);
    }
    if (util.doValida (NR_Fatura)) {
      ed.setNR_Fatura (NR_Fatura);
    }
    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }
    if (util.doValida (NR_Peso)) {
      ed.setNR_Peso (Double.parseDouble (NR_Peso));
    }
    if (util.doValida (NR_Peso_Cubado)) {
      ed.setNR_Peso_Cubado (Double.parseDouble (NR_Peso_Cubado));
    }
    if (util.doValida (NR_Volumes)) {
      ed.setNR_Volumes (Double.parseDouble (NR_Volumes));
    }
    if (util.doValida (VL_Postagem)) {
      ed.setVL_Postagem (Double.parseDouble (VL_Postagem));
    }
    if (util.doValida (NR_Lote)) {
      ed.setNR_Lote (NR_Lote);
    }
    if (util.doValida (oid_Modal)) {
      ed.setOID_Modal (Long.parseLong (oid_Modal));
    }
    new PostagemRN ().altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      PostagemRN PostagemRN = new PostagemRN ();
      PostagemED ed = new PostagemED ();

      ed.setOID_Postagem (request.getParameter ("oid_Postagem"));

      PostagemRN.deleta (ed);
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

  public void deletaLote (HttpServletRequest request) throws Excecoes {

    try {
      PostagemRN PostagemRN = new PostagemRN ();
      PostagemED ed = new PostagemED ();

      ed.setNR_Lote ("");
      String NR_Lote = request.getParameter ("FT_NR_Lote");
      if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
          && !String.valueOf (NR_Lote).equals ("null")) {
        ed.setNR_Lote (NR_Lote);
      }
      ed.setNR_Fatura ("");
      String NR_Fatura = request.getParameter ("FT_NR_Fatura");
      if (String.valueOf (NR_Fatura) != null && !String.valueOf (NR_Fatura).equals ("")
          && !String.valueOf (NR_Fatura).equals ("null")) {
        ed.setNR_Fatura (NR_Fatura);
      }

      PostagemRN.deletaLote (ed);
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

  public void deletaFatura (HttpServletRequest request) throws Excecoes {

    try {
      PostagemRN PostagemRN = new PostagemRN ();
      PostagemED ed = new PostagemED ();
      String NR_Fatura = request.getParameter ("FT_NR_Fatura");
      if (String.valueOf (NR_Fatura) != null && !String.valueOf (NR_Fatura).equals ("")
          && !String.valueOf (NR_Fatura).equals ("null")) {
        ed.setNR_Fatura (NR_Fatura);
      }

      PostagemRN.deletaFatura (ed);
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


  public ArrayList lista (HttpServletRequest request) throws Excecoes {
    String NR_Fatura = request.getParameter ("FT_NR_Fatura");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String DT_Postagem_Inicial = request.getParameter ("FT_DT_Postagem_Inicial");
    String DT_Postagem_Final = request.getParameter ("FT_DT_Postagem_Final");
    String nr_Postagem = request.getParameter ("FT_NR_Postagem");
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    String NR_Lote = request.getParameter ("FT_NR_Lote");

    PostagemED ed = new PostagemED ();

    ed.setNR_Lote ("");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    if (util.doValida (NR_Fatura)) {
      ed.setNR_Fatura (NR_Fatura);
    }
    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }
    if (util.doValida (DT_Postagem_Inicial)) {
      ed.setDT_Postagem_Inicial (DT_Postagem_Inicial);
    }
    if (util.doValida (DT_Postagem_Final)) {
      ed.setDT_Postagem_Final (DT_Postagem_Final);
    }
    if (util.doValida (nr_Postagem)) {
      ed.setNR_Postagem (Long.parseLong (nr_Postagem));
    }
    if (util.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (util.doValida (oid_Pessoa_Destinatario)) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    return new PostagemRN ().lista (ed);
  }

  public ArrayList listaPostagemConhecimento (HttpServletRequest request) throws Excecoes {
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    PostagemED ed = new PostagemED ();

    if (util.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    return new PostagemRN ().listaPostagemConhecimento (ed);
  }

  public ArrayList listaPostagemConhecimento (String oid_Conhecimento) throws Excecoes {
    PostagemED ed = new PostagemED ();
    if (util.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    return new PostagemRN ().listaPostagemConhecimento (ed);
  }


  public ArrayList gera_Remessa (HttpServletRequest request, String NM_Arquivo) throws Excecoes {
    String DT_Postagem = request.getParameter ("FT_DT_Postagem");
    String NM_Natureza = request.getParameter ("FT_NM_Natureza");
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Modal = request.getParameter ("oid_Modal");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Produto = request.getParameter ("oid_Produto");
    String oid_Armazem = request.getParameter ("oid_Armazem");


    PostagemED ed = new PostagemED ();

    if (util.doValida (DT_Postagem)) {
      ed.setDT_Postagem (DT_Postagem);
    }
    ed.setNM_Natureza ("--");

    if (util.doValida (NM_Natureza)) {
      ed.setNM_Natureza (NM_Natureza);
    }
    if (util.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (util.doValida (oid_Unidade)) {
      ed.setOID_Unidade (Long.parseLong (oid_Unidade));
    }
    if (util.doValida (oid_Modal)) {
      ed.setOID_Modal (Long.parseLong (oid_Modal));
    }
    if (util.doValida (oid_Produto)) {
      ed.setOID_Produto (Long.parseLong (oid_Produto));
    }
    if (util.doValida (oid_Armazem)) {
      ed.setOID_Armazem (Long.parseLong (oid_Armazem));
    }
    if (util.doValida (DT_Postagem)) {
      ed.setDT_Postagem (DT_Postagem);
    }
    if (util.doValida (NM_Arquivo)) {
      ed.setNM_Arquivo (NM_Arquivo);
    }

    return new PostagemRN ().gera_Remessa (ed);
  }

  public PostagemED getByRecord (HttpServletRequest request) throws Excecoes {

    PostagemED ed = new PostagemED ();

    String NR_Postagem = request.getParameter ("FT_NR_Postagem");
    if (NR_Postagem != null && NR_Postagem.length () > 0) {
      ed.setNR_Postagem (new Long (request.getParameter ("FT_NR_Postagem")).longValue ());
    }
    ed.setOID_Postagem (request.getParameter ("oid_Postagem"));
    return new PostagemRN ().getByRecord (ed);

  }


  public void importa_Arquivo (String arquivo, String tipo, String NR_Fatura) throws Excecoes {

      // System.out.println ("inicio importa_Arquivo  ");


    try {
      PostagemRN postagemRN = new PostagemRN ();
      // System.out.println ("inicio  iu importacao arquivo=>  " + arquivo);
      // System.out.println ("inicio importacao  tipo=>  " + tipo);
      // System.out.println ("inicio importacao  NR_Fatura=>  " + NR_Fatura);

      postagemRN.importa_Arquivo (arquivo, tipo, NR_Fatura);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao importa_Arquivo");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
