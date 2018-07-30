package com.master.rn;

import com.master.rl.JasperRL;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.ColetaBD;
import com.master.bd.EstoqueBD;
import com.master.ed.ColetaED;
import com.master.ed.EstoqueED;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EstoqueRN
    extends Transacao {
  EstoqueBD EstoqueBD = null;

  public EstoqueRN () {
    //EstoqueBD = new EstoqueBD(this.sql);
  }

  /********************************************************
   *
   *******************************************************/
  public EstoqueED inclui (EstoqueED ed) throws Excecoes {

    EstoqueED EstoqueED = new EstoqueED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      EstoqueBD = new EstoqueBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      EstoqueED = EstoqueBD.inclui (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      throw e;
    }

    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir item de estoque");
      excecoes.setMetodo ("Inclui");
      excecoes.setExc (e);
      throw excecoes;
    }

    return EstoqueED;
  }


  public EstoqueED incluiContagem (EstoqueED ed) throws Excecoes {

	    EstoqueED EstoqueED = new EstoqueED ();

	    try {

	      //a chamada a este metodo da superclasse
	      //iniciotransacao faz com que se abra uma conexao
	      //e a deixe no pool
	      this.inicioTransacao ();

	      //instancia objeto sql, que eh
	      //uma referencia ao objeto ExecutaSQL, que por sua
	      //vez possui a referencia a conexao ativa
	      EstoqueBD = new EstoqueBD (this.sql);

	      //chama o inclui passando a estrutura de dados
	      //como parametro

	      EstoqueED = EstoqueBD.incluiContagem (ed);

	      //faz o commit em cima do objeto transacao
	      this.fimTransacao (true);
	    }
	    catch (Excecoes e) {
	      throw e;
	    }

	    catch (Exception e) {
	      this.abortaTransacao ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao incluir item de estoque");
	      excecoes.setMetodo ("Inclui");
	      excecoes.setExc (e);
	      throw excecoes;
	    }

	    return EstoqueED;
	  }

  /********************************************************
   *
   *******************************************************/
  public void altera (EstoqueED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.altera (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar item de estoque");
      excecoes.setMetodo ("altera");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void deleta (EstoqueED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir item de estoque");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }


  public void ativa_Desativa (EstoqueED ed) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      EstoqueBD = new EstoqueBD (this.sql);
	      EstoqueBD.ativa_Desativa (ed);

	      this.fimTransacao (true);

	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception e) {
	      this.abortaTransacao ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao excluir item de estoque");
	      excecoes.setMetodo ("deleta");
	      excecoes.setExc (e);
	      throw excecoes;
	    }

	  }

  /********************************************************
   *
   *******************************************************/
  public EstoqueED getByRecord (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EstoqueED edVolta = new EstoqueED ();
    //atribui ao ed de retorno
    edVolta = new EstoqueBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new EstoqueBD (this.sql).Lista (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }


  public ArrayList ListaContagem (EstoqueED ed) throws Excecoes {
	    //inicia conexao com bd
	    this.inicioTransacao ();
	    //instancia ed de retorno
	    ArrayList lista = new ArrayList ();
	    //atribui ao ed de retorno
	    lista = new EstoqueBD (this.sql).ListaContagem (ed);
	    //libera conexao nao "commitando"
	    this.fimTransacao (false);

	    return lista;
	  }

  /********************************************************
   *
   *******************************************************/
  public EstoqueED getCodigo_Item (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EstoqueED edVolta = new EstoqueED ();
    //atribui ao ed de retorno
    edVolta = new EstoqueBD (this.sql).getCodigo_Item (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public void update_Proximo_Item (long proximo , int grupo , int subgrupo) throws Excecoes {

    try {

      this.inicioTransacao ();

      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.update_Proximo_nro (proximo , grupo , subgrupo);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir item de estoque");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public EstoqueED inclui_itemXfornecedor (EstoqueED ed) throws Excecoes {

    EstoqueED EstoqueED = new EstoqueED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      EstoqueBD = new EstoqueBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      EstoqueED = EstoqueBD.inclui_itemXfornecedor (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      throw e;
    }

    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir item de estoque");
      excecoes.setMetodo ("Inclui");
      excecoes.setExc (e);
      throw excecoes;
    }

    return EstoqueED;
  }

  /********************************************************
   *
   *******************************************************/
  public void altera_itemXfornecedor (EstoqueED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.altera_itemXfornecedor (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar item de estoque");
      excecoes.setMetodo ("altera");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void deleta_itemXfornecedor (EstoqueED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.deleta_itemXfornecedor (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir item de estoque");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public EstoqueED getByRecord_itemXfornecedor (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EstoqueED edVolta = new EstoqueED ();
    //atribui ao ed de retorno
    edVolta = new EstoqueBD (this.sql).getByRecord_itemXfornecedor (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_itemXfornecedor (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new EstoqueBD (this.sql).Lista_itemXfornecedor (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList ListaWMS (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new EstoqueBD (this.sql).ListaWMS (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public EstoqueED getEstoque (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EstoqueED edVolta = new EstoqueED ();
    //atribui ao ed de retorno
    edVolta = new EstoqueBD (this.sql).getEstoque (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public void baixa_Estoque (String oid_Estoque , double Quantidade) throws Excecoes {

    try {

      this.inicioTransacao ();
      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.baixa_Estoque (oid_Estoque , Quantidade , "");
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao baixar estoque");
      excecoes.setMetodo ("baixa_Estoque()");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_Movimentacao_item (EstoqueED ed , String tipo) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new EstoqueBD (this.sql).Lista_Movimentacao_item (ed , tipo);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public void Movimento_Entrada_Estoque (String oid_Estoque , String oid_NF , double Quantidade) throws Excecoes {

    try {

      this.inicioTransacao ();
      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.Movimento_Entrada_Estoque (oid_Estoque , oid_NF , Quantidade);
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao movimentar estoque");
      excecoes.setMetodo ("movimento_Estoque()");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Analise_de_Compras (EstoqueED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new EstoqueBD (this.sql).Analise_de_Compras (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public void entrada_Estoque (String oid_Estoque , double Quantidade) throws Excecoes {

    try {

      this.inicioTransacao ();
      EstoqueBD = new EstoqueBD (this.sql);
      EstoqueBD.entrada_Estoque (oid_Estoque , Quantidade , "");
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao adicionar estoque");
      excecoes.setMetodo ("entrada_Estoque()");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  public byte[] gera_Rel_Movimento_Estoque (EstoqueED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    EstoqueBD = new EstoqueBD (this.sql);
    byte[] b = EstoqueBD.gera_Rel_Movimento_Estoque (ed);
    this.fimTransacao (false);
    return b;
  }

	public void Relatorio_Itens_Estoque(HttpServletRequest request, HttpServletResponse response) throws Excecoes {
		try {
			this.inicioTransacao();
		    EstoqueED ed = new EstoqueED ();

		    String oid_Estoque = request.getParameter ("oid_Estoque");
		    if (oid_Estoque != null && oid_Estoque.length () > 0) {
		      long b = (new Long (oid_Estoque)).longValue ();
		      ed.setOid_estoque (b);
		    }
		    String oid_Grupo_Estoque = request.getParameter ("oid_Grupo_Estoque");
		    if (oid_Grupo_Estoque != null && oid_Grupo_Estoque.length () > 0) {
		      int b = (new Integer (oid_Grupo_Estoque)).intValue ();
		      ed.setOid_grupo_estoque (b);
		    }
		    String CD_Estoque = request.getParameter ("FT_CD_Estoque");
		    if (CD_Estoque != null && CD_Estoque.length () > 0) {
		      ed.setCd_estoque (CD_Estoque);
		    }
		    String NM_Estoque = request.getParameter ("FT_NM_Estoque");
		    if (NM_Estoque != null && NM_Estoque.length () > 0) {
		      ed.setNm_estoque (NM_Estoque);
		    }

		    String NM_Referencia_Fabrica = request.getParameter ("FT_NM_Referencia_Fabrica");
		    if (NM_Referencia_Fabrica != null && NM_Referencia_Fabrica.length () > 0) {
		      ed.setNm_referencia (NM_Referencia_Fabrica);
		    }

		    String DM_Status = request.getParameter ("FT_DM_Status");
		    if (DM_Status != null && DM_Status.length () > 0) {
		      ed.setDm_status (DM_Status);
		    }

		    String DM_Divergencia = request.getParameter ("FT_DM_Divergencia");
		    if (DM_Divergencia != null && DM_Divergencia.length () > 0) {
		      ed.setDM_Divergencia (DM_Divergencia);
		    }

		    String NR_Dias_Contagem = request.getParameter ("FT_NR_Dias_Contagem");
		    if (NR_Dias_Contagem != null && NR_Dias_Contagem.length () > 0) {
		      ed.setNR_Dias_Contagem ((new Integer (NR_Dias_Contagem)).intValue ());
		    }

		    String DM_Tipo_Produto = request.getParameter ("FT_DM_Tipo_Produto");
		    if (DM_Tipo_Produto != null && DM_Tipo_Produto.length () > 0) {
		      ed.setDm_tipo_produto (DM_Tipo_Produto);
		    }

		    String CD_Deposito = request.getParameter ("FT_CD_Deposito");
		    if (CD_Deposito != null && CD_Deposito.length () > 0) {
		      ed.setCD_Deposito (CD_Deposito);
		    }
		    String NM_Rua = request.getParameter ("FT_NM_Rua");
		    if (NM_Rua != null && NM_Rua.length () > 0) {
		      ed.setNM_Rua (NM_Rua);
		    }
		    String NR_Endereco = request.getParameter ("FT_NR_Endereco");
		    if (NR_Endereco != null && NR_Endereco.length () > 0) {
		      ed.setNR_Endereco (NR_Endereco);
		    }
		    String NR_Apartamento = request.getParameter ("FT_NR_Apartamento");
		    if (NR_Apartamento != null && NR_Apartamento.length () > 0) {
		      ed.setNR_Apartamento (NR_Apartamento);
		    }


		    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

			ArrayList listMov = new ArrayList();
			listMov = new EstoqueBD(this.sql).Relatorio_Itens_Estoque(ed);
			ed.setLista(listMov);
			ed.setResponse(response);
			ed.setNomeRelatorio("RelatorioItensEstoque");
		    if ("ESTCON1".equals(ed.getDM_Relatorio())) {
				ed.setNomeRelatorio("RelatorioItensEstoqueContagem");
		    }
		    if ("ESTCON2".equals(ed.getDM_Relatorio())) {
				ed.setNomeRelatorio("RelatorioItensEstoqueContagemLocalizacao");
		    }


			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
		}
		finally {
			this.fimTransacao(true);
		}
	}

}