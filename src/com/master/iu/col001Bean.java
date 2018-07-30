package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.ed.ColetaED;
import com.master.ed.ManifestoED;
import com.master.ed.Solicitacao_CompraED;
import com.master.rn.ColetaRN;
import com.master.rn.ConhecimentoRN;
import com.master.rn.ManifestoRN;
import com.master.rn.Solicitacao_CompraRN;
import com.master.util.*;
import com.master.util.ed.Parametro_FixoED;

public class col001Bean {

	Utilitaria util = new Utilitaria ();

	public ColetaED inclui (HttpServletRequest request) throws Excecoes {
		ColetaED ed = new ColetaED ();

		ed = this.carregaED (request);
		ed.setDM_Situacao ("1");

		return new ColetaRN ().inclui (ed);
	}

	public ColetaED getByRecord (HttpServletRequest request) throws Excecoes {
		ColetaED ed = new ColetaED ();

		String NR_Coleta = request.getParameter ("FT_NR_Coleta");
		if (util.doValida (NR_Coleta)) {
			ed.setNR_Coleta (NR_Coleta);
		}
		String oid_Unidade = request.getParameter ("oid_Unidade");
		if (util.doValida (oid_Unidade)) {
			ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
		}
		String oid_Coleta = request.getParameter ("oid_Coleta");
		if (util.doValida (oid_Coleta)) {
			ed.setOID_Coleta (oid_Coleta);
		}
		return new ColetaRN ().getByRecord (ed);
	}

	public ColetaED copia_Coleta (HttpServletRequest request) throws Excecoes {
		ColetaED ed = new ColetaED ();

		String oid_Coleta = request.getParameter ("oid_Coleta");
		if (util.doValida (oid_Coleta)) {
			ed.setOID_Coleta (oid_Coleta);
		}
		return new ColetaRN ().copia_Coleta (ed);
	}

	public ColetaED getByRecord (String oid_Coleta) throws Excecoes {
		ColetaED ed = new ColetaED ();

		ed.setOID_Coleta (oid_Coleta);
		return new ColetaRN ().getByRecord (ed);
	}

	public ColetaED getNR_Coleta (String NR_Coleta) throws Excecoes {
		ColetaED ed = new ColetaED ();

		ed.setNR_Coleta (NR_Coleta);
		return new ColetaRN ().getByRecord (ed);
	}
	
	public void altera (HttpServletRequest request) throws Excecoes {

		ColetaED ed = new ColetaED ();
		ed = this.carregaED (request);

		ColetaED edVolta = new ColetaED ();
		edVolta = new ColetaRN ().getByRecord (ed);

		new ColetaRN ().altera (ed);
		if (!JavaUtil.doValida (edVolta.getOID_Veiculo ())) {
			edVolta.setOID_Veiculo ("");
		}
		if (JavaUtil.doValida (ed.getOID_Veiculo ())
				&& !edVolta.getOID_Veiculo ().equals (ed.getOID_Veiculo ())) {
			new ColetaRN ().altera_Veiculo (ed);
		}
		if (!edVolta.getDT_Coleta ().equals (ed.getDT_Coleta ())) {
			new ColetaRN ().remarca (ed);
		}
	}

	public void cancela (HttpServletRequest request) throws Excecoes {

		ColetaED ed = new ColetaED ();
		ed = this.carregaED (request);

		ColetaED edVolta = new ColetaED ();
		edVolta = new ColetaRN ().getByRecord (ed);

		new ColetaRN ().cancela (ed);
	}

	public void finaliza_coleta(HttpServletRequest request)throws Excecoes{
	      String HR_Coletado = request.getParameter("FT_HR_Coletado");
	      String DT_Coletado = request.getParameter("FT_DT_Coletado");
	      

	      ColetaED ed = new ColetaED();
		  ed.setOID_Coleta(request.getParameter("oid_Coleta"));      
	      
	      ed.setHR_Coleta("");
	      if (String.valueOf(HR_Coletado) != null &&
	          !String.valueOf(HR_Coletado).equals("null") &&
	          !String.valueOf(HR_Coletado).equals("")){
	          ed.setHR_Coletado(HR_Coletado);
	      }
	      ed.setDT_Coleta("");
	      if (String.valueOf(DT_Coletado) != null &&
	          !String.valueOf(DT_Coletado).equals("null") &&
	          !String.valueOf(DT_Coletado).equals("")){
	          ed.setDT_Coletado(DT_Coletado);
	      }

	      new ColetaRN().finaliza_coleta(ed);
	  }
	
	public ArrayList Lista_Coleta (HttpServletRequest request) throws Excecoes {

		ColetaED ed = new ColetaED ();

		ed = this.carregaED (request);

		return new ColetaRN ().Lista_Coleta (ed);

	}

	public ArrayList Lista_Programacao (HttpServletRequest request) throws Excecoes {

		ColetaED ed = new ColetaED ();

		ed = this.carregaED (request);
		ed.setDM_Tipo_Coleta ("P");
		return new ColetaRN ().Lista_Coleta (ed);

	}

	public void Imprime_Coleta (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
		ColetaED ed = new ColetaED ();

		ed = this.carregaED (request);

		ColetaRN geRN = new ColetaRN ();

		byte[] b = geRN.Imprime_Coleta (ed);

		new EnviaPDF ().enviaBytes (request , Response , b);
	}

	public void Imprime_Coleta_rf (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
		ColetaED ed = new ColetaED ();

		ed = this.carregaED (request);

		ColetaRN geRN = new ColetaRN ();
		byte[] b = geRN.Imprime_Coleta (ed);
		new EnviaPDF ().enviaBytes (request , Response , b);
	}

	public String Imprime_Coleta_Matricial (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

		return new ColetaRN ().Imprime_Coleta_Matricial (this.carregaED (request));
	}

	public void Relatorio_Coleta_Notas_Fiscais (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
		ColetaED ed = new ColetaED ();

		ed = this.carregaED (request);

		ColetaRN geRN = new ColetaRN ();
		byte[] b = geRN.Relatorio_Coleta_Notas_Fiscais (ed);
		new EnviaPDF ().enviaBytes (request , Response , b);
	}

	public byte[] Relatorio_Coleta (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

		ColetaED ed = new ColetaED ();
		ed = this.carregaED (request);

		ColetaRN geRN = new ColetaRN ();

		return geRN.Relatorio_Coleta (ed);

	}

	public byte[] Relatorio_Programacao (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

		ColetaED ed = new ColetaED ();
		ed = this.carregaED (request);
		ed.setDM_Tipo_Coleta ("P");

		ColetaRN geRN = new ColetaRN ();

		return geRN.Relatorio_Coleta (ed);

	}

	public void altera_Veiculo (HttpServletRequest request) throws Excecoes {

		try {
			ColetaRN ColetaRN = new ColetaRN ();
			ColetaED ed = new ColetaED ();

			ed = this.carregaED (request);

			ColetaRN.altera_Veiculo (ed);
		}
		catch (Excecoes exc) {
			throw exc;
		}
		catch (Exception exc) {
			exc.printStackTrace ();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("erro ao alterar");
			excecoes.setMetodo ("altera_Veiculo");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public ColetaED confirma_Reprova_Cotacao (HttpServletRequest request , String acao) throws Excecoes {

		try {
			ColetaRN ColetaRN = new ColetaRN ();
			ColetaED ed = new ColetaED ();

			ed.setAcao (acao);

			String oid_Coleta = request.getParameter ("oid_Coleta");
			if (util.doValida (oid_Coleta)) {
				ed.setOID_Coleta (oid_Coleta);
			}

			String VL_Total_Frete = request.getParameter ("FT_VL_Total_Frete");
			if (util.doValida (VL_Total_Frete)) {
				ed.setVL_Total_Frete (new Double (VL_Total_Frete).doubleValue ());
			}

			ed.setDM_Motivo_Perda ("");
			ed.setTX_Motivo_Perda ("");
			String DM_Motivo_Perda = request.getParameter ("FT_DM_Motivo_Perda");
			if (JavaUtil.doValida (DM_Motivo_Perda)) {
				ed.setDM_Motivo_Perda (DM_Motivo_Perda);
				String TX_Motivo_Perda = request.getParameter ("FT_TX_Motivo_Perda");
				if (JavaUtil.doValida (TX_Motivo_Perda)) {
					ed.setTX_Motivo_Perda (TX_Motivo_Perda);
				}
			}

			return ColetaRN.confirma_Reprova_Cotacao (ed);
		}
		catch (Excecoes exc) {
			throw exc;
		}
		catch (Exception exc) {
			exc.printStackTrace ();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("erro ao alterar");
			excecoes.setMetodo ("confirma_Cotacao");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public void vincula_CTRC (String oid_Coleta , String oid_Conhecimento) throws Excecoes {

		try {
			ColetaRN ColetaRN = new ColetaRN ();
			ColetaED ed = new ColetaED ();

			if (util.doValida (oid_Coleta)) {
				ed.setOID_Coleta (oid_Coleta);
			}

			if (util.doValida (oid_Conhecimento)) {
				ed.setOID_Conhecimento (oid_Conhecimento);
			}

			if (util.doValida (oid_Coleta) && util.doValida (oid_Conhecimento)) {
				ColetaRN.vincula_CTRC (ed);
			}
		}
		catch (Excecoes exc) {
			throw exc;
		}
		catch (Exception exc) {
			exc.printStackTrace ();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("erro ao alterar");
			excecoes.setMetodo ("altera_Veiculo");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public ColetaED carregaED (HttpServletRequest request) throws Excecoes {

		ColetaED ed = new ColetaED ();

		String oid_Coleta = request.getParameter ("oid_Coleta");
		if (util.doValida (oid_Coleta)) {
			ed.setOID_Coleta (oid_Coleta);
		}

		String oid_Programacao_Carga = request.getParameter ("oid_Programacao_Carga");
		if (util.doValida (oid_Programacao_Carga)) {
			ed.setOid_Programacao_Carga(new Long(oid_Programacao_Carga).longValue());
		}

		String oid_Cotacao = request.getParameter ("oid_Cotacao");
		if (util.doValida (oid_Cotacao)) {
			ed.setOID_Cotacao (oid_Cotacao);
		}

		String DM_Procedencia = request.getParameter ("FT_DM_Procedencia");
		ed.setDM_Procedencia ("");
		if (util.doValida (DM_Procedencia)) {
			ed.setDM_Procedencia (DM_Procedencia);
		}

		String DM_Situacao = request.getParameter ("FT_DM_Situacao");
		if (util.doValida (DM_Situacao)) {
			ed.setDM_Situacao (DM_Situacao);
		}

		String DM_Situacao_Cotacao = request.getParameter ("FT_DM_Situacao_Cotacao");
		if (util.doValida (DM_Situacao_Cotacao)) {
			ed.setDM_Situacao_Cotacao (DM_Situacao_Cotacao);
		}

		String DM_Tipo_Coleta = request.getParameter ("FT_DM_Tipo_Coleta");
		ed.setDM_Tipo_Coleta ("C");
		if (util.doValida (DM_Tipo_Coleta)) {
			ed.setDM_Tipo_Coleta (DM_Tipo_Coleta);
		}

		String DT_Emissao = request.getParameter ("FT_DT_Emissao");
		if (util.doValida (DT_Emissao)) {
			ed.setDT_Emissao (DT_Emissao);
		}

		String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
		if (util.doValida (DT_Emissao_Inicial)) {
			ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
		}

		String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
		if (util.doValida (DT_Emissao_Final)) {
			ed.setDT_Emissao_Final (DT_Emissao_Final);
		}

		String DT_Programada_Inicial = request.getParameter ("FT_DT_Programada_Inicial");
		if (util.doValida (DT_Programada_Inicial)) {
			ed.setDT_Programada_Inicial (DT_Programada_Inicial);
		}

		String DT_Programada_Final = request.getParameter ("FT_DT_Programada_Final");
		if (util.doValida (DT_Programada_Final)) {
			ed.setDT_Programada_Final (DT_Programada_Final);
		}

		String NR_Coleta = request.getParameter ("FT_NR_Coleta");
		if (util.doValida (NR_Coleta)) {
			ed.setNR_Coleta (NR_Coleta);
		}

		String oid_Unidade = request.getParameter ("oid_Unidade");
		if (util.doValida (oid_Unidade)) {
			ed.setOID_Unidade (new Integer (oid_Unidade).intValue ());
		}

		String oid_Cidade = request.getParameter ("oid_Cidade");
		if (util.doValida (oid_Cidade)) {
			ed.setOID_Cidade (new Integer (oid_Cidade).intValue ());
		}

		String oid_Modal = request.getParameter ("oid_Modal");
	    ed.setOID_Modal (Parametro_FixoED.getInstancia ().getOID_Modal ());
		if (util.doValida (oid_Modal)) {
			ed.setOID_Modal (new Integer (oid_Modal).intValue ());
		}

		String oid_Produto = request.getParameter ("oid_Produto");
	    ed.setOID_Produto (Parametro_FixoED.getInstancia ().getOID_Produto ());
	    if (JavaUtil.doValida (oid_Produto)) {
	      ed.setOID_Produto (new Long (oid_Produto).longValue ());
	    }
		
		String oid_Motorista = request.getParameter ("oid_Motorista");
		if (util.doValida (oid_Motorista)) {
			ed.setOID_Motorista (oid_Motorista);
		}

		String oid_Pessoa = request.getParameter ("oid_Pessoa");
		if (util.doValida (oid_Pessoa)) {
			ed.setOID_Pessoa (oid_Pessoa);
		}
		String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
		if (util.doValida (oid_Pessoa_Destinatario)) {
			ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
		}
		String oid_Pessoa_Usuario = request.getParameter ("oid_Pessoa_Usuario");
		if (util.doValida (oid_Pessoa_Usuario)) {
			ed.setOID_Pessoa_Usuario (oid_Pessoa_Usuario);

		}
		ed.setDM_Tipo("");
		String DM_Tipo = request.getParameter ("FT_DM_Tipo");
		if (util.doValida (DM_Tipo)) {
			ed.setDM_Tipo (DM_Tipo);
			if (util.doValida (oid_Pessoa_Usuario)) {
				if ("S".equals (DM_Tipo)) {
					ed.setOID_Pessoa_Destinatario (oid_Pessoa_Usuario);
				}
				if ("D".equals (DM_Tipo)) {
					ed.setOID_Pessoa (oid_Pessoa_Usuario);
				}
			}
		}

		String oid_Cidade_Destinatario = request.getParameter ("oid_Cidade_Destinatario");
		if (util.doValida (oid_Cidade_Destinatario)) {
			ed.setOID_Cidade_Destinatario (new Integer (oid_Cidade_Destinatario).intValue ());
		}

		String DT_Coleta = request.getParameter ("FT_DT_Coleta");
		if (util.doValida (DT_Coleta)) {
			ed.setDT_Coleta (DT_Coleta);
			ed.setDT_Programada (DT_Coleta);
		}

		String HR_Coleta = request.getParameter ("FT_HR_Coleta");
		if (util.doValida (HR_Coleta)) {
			ed.setHR_Coleta (HR_Coleta);
		}

		String DT_Programada = request.getParameter ("FT_DT_Programada");
		if (util.doValida (DT_Programada)) {
			ed.setDT_Programada (DT_Programada);
		}

		String HR_Programada = request.getParameter ("FT_HR_Programada");
		if (util.doValida (HR_Programada)) {
			ed.setHR_Programada (HR_Programada);
		}

		String HR_Coleta_Minima = request.getParameter ("FT_HR_Coleta_Minima");
		ed.setHR_Coleta_Minima ("");
		if (HR_Coleta_Minima != null && !HR_Coleta_Minima.equals ("null") && !HR_Coleta_Minima.equals ("")) {
			ed.setHR_Coleta_Minima (HR_Coleta_Minima);
		}

		String HR_Coleta_Maxima = request.getParameter ("FT_HR_Coleta_Maxima");
		ed.setHR_Coleta_Maxima ("");
		if (HR_Coleta_Maxima != null && !HR_Coleta_Maxima.equals ("null") && !HR_Coleta_Maxima.equals ("")) {
			ed.setHR_Coleta_Maxima (HR_Coleta_Maxima);
		}

		String NM_Contato = request.getParameter ("FT_NM_Contato");
		ed.setNM_Pessoa_Contato ("");
		if (NM_Contato != null && !NM_Contato.equals ("") && !NM_Contato.equals ("null")) {
			ed.setNM_Pessoa_Contato (NM_Contato);
		}

		String NR_Ramal_Contato = request.getParameter ("FT_NR_Ramal_Contato");
		if (util.doValida (NR_Ramal_Contato)) {
			ed.setNR_Ramal_Contato(new Integer (NR_Ramal_Contato).intValue ());
		}
		
		String NM_Solicitante = request.getParameter ("FT_NM_Solicitante");
		ed.setNM_Pessoa_Solicitante ("");
		if (NM_Solicitante != null && !NM_Solicitante.equals ("") && !NM_Solicitante.equals ("null")) {
			ed.setNM_Pessoa_Solicitante (NM_Solicitante);
		}

		String NR_Ramal_Solicitante = request.getParameter ("FT_NR_Ramal_Solicitante");
		if (util.doValida (NR_Ramal_Solicitante)) {
			ed.setNR_Ramal_Solicitante(new Integer (NR_Ramal_Solicitante).intValue ());
		}
		
		String NM_Endereco_Coleta = request.getParameter ("FT_NM_Endereco_Coleta");
		ed.setNM_Endereco_Coleta ("");
		if (NM_Endereco_Coleta != null && !NM_Endereco_Coleta.equals ("") && !NM_Endereco_Coleta.equals ("null")) {
			ed.setNM_Endereco_Coleta (NM_Endereco_Coleta);
		}

		String NM_Bairro_Coleta = request.getParameter ("FT_NM_Bairro_Coleta");
		ed.setNM_Bairro_Coleta ("");
		if (NM_Bairro_Coleta != null && !NM_Bairro_Coleta.equals ("") && !NM_Bairro_Coleta.equals ("null")) {
			ed.setNM_Bairro_Coleta (NM_Bairro_Coleta);
		}

		String TX_Observacao = request.getParameter ("FT_TX_Observacao");
		ed.setTX_Observacao ("");
		if (TX_Observacao != null && !TX_Observacao.equals ("") && !TX_Observacao.equals ("null")) {
			ed.setTX_Observacao (TX_Observacao);
		}

		String NR_Telefone = request.getParameter ("FT_NR_Telefone");
		ed.setNR_Telefone ("");
		if (NR_Telefone != null && !NR_Telefone.equals ("") && !NR_Telefone.equals ("null")) {
			ed.setNR_Telefone (NR_Telefone);
		}

		String NR_Cartao = request.getParameter ("FT_NR_Cartao");
		ed.setNR_Cartao ("");
		if (NR_Cartao != null && !NR_Cartao.equals ("") && !NR_Cartao.equals ("null")) {
			ed.setNR_Cartao (NR_Cartao);
		}
		
		String NR_Liberacao = request.getParameter ("FT_NR_Liberacao");
		ed.setNR_Liberacao ("");
		if (NR_Liberacao != null && !NR_Liberacao.equals ("") && !NR_Liberacao.equals ("null")) {
			ed.setNR_Liberacao (NR_Liberacao);
		}
		
		String NR_Pedido = request.getParameter ("FT_NR_Pedido");
		ed.setNR_Pedido ("");
		if (NR_Pedido != null && !NR_Pedido.equals ("") && !NR_Pedido.equals ("null")) {
			ed.setNR_Pedido (NR_Pedido);
		}

		String NM_E_Mail = request.getParameter ("FT_NM_E_Mail");
		ed.setNM_E_Mail ("");
		if (NM_E_Mail != null && !NM_E_Mail.equals ("") && !NM_E_Mail.equals ("null")) {
			ed.setNM_E_Mail (NM_E_Mail);
		}

		String NR_Volumes = request.getParameter ("FT_NR_Volumes");
		if (util.doValida (NR_Volumes)) {
			ed.setNR_Volumes (new Integer (NR_Volumes).intValue ());
		}

		String NR_Peso = request.getParameter ("FT_NR_Peso");
		if (util.doValida (NR_Peso)) {
			ed.setNR_Peso (new Double (NR_Peso).doubleValue ());
		}

		String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
		if (util.doValida (NR_Peso_Cubado)) {
			ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
		}

		String oid_Usuario = request.getParameter ("oid_Usuario");
		if (util.doValida (oid_Usuario)) {
			ed.setOid_Usuario (new Long (oid_Usuario).longValue ());
		}

		String NR_Peso2 = request.getParameter ("FT_NR_Peso2");
		if (util.doValida (NR_Peso2)) {
			ed.setNR_Peso2 (new Integer (NR_Peso2).intValue ());
		}

		String NR_Peso3 = request.getParameter ("FT_NR_Peso3");
		if (util.doValida (NR_Peso3)) {
			ed.setNR_Peso3 (new Integer (NR_Peso3).intValue ());
		}

		String NR_Peso4 = request.getParameter ("FT_NR_Peso4");
		if (util.doValida (NR_Peso4)) {
			ed.setNR_Peso4 (new Integer (NR_Peso4).intValue ());
		}

		String NR_Cubagem = request.getParameter ("FT_NR_Cubagem");
		if (util.doValida (NR_Cubagem)) {
			ed.setNR_Cubagem (new Double (NR_Cubagem).doubleValue ());
		}

		String VL_Mercadoria = request.getParameter ("FT_VL_Mercadoria");
		if (util.doValida (VL_Mercadoria)) {
			ed.setVL_Mercadoria (new Double (VL_Mercadoria).doubleValue ());
		}

		String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
		ed.setOID_Pessoa_Pagador (ed.getOID_Pessoa ());
		if (util.doValida (oid_Pessoa_Pagador)) {
			ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
		}

		String DT_Previsao_Entrega = request.getParameter ("FT_DT_Previsao_Entrega");
		ed.setDT_Previsao_Entrega (ed.getDT_Emissao ());
		if (util.doValida (DT_Previsao_Entrega)) {
			ed.setDT_Previsao_Entrega (DT_Previsao_Entrega);
		}

		String HR_Previsao_Entrega = request.getParameter ("FT_HR_Previsao_Entrega");
		ed.setHR_Previsao_Entrega ("");
		if (util.doValida (HR_Previsao_Entrega)) {
			ed.setHR_Previsao_Entrega (HR_Previsao_Entrega);
		}

		String DM_Intervalo_Almoco = request.getParameter ("FT_DM_Intervalo_Almoco");
		ed.setDM_Intervalo_Almoco ("N");
		if (util.doValida (DM_Intervalo_Almoco)) {
			ed.setDM_Intervalo_Almoco (DM_Intervalo_Almoco);
		}

		String DM_Tipo_Veiculo = request.getParameter ("FT_DM_Tipo_Veiculo");
		ed.setDM_Tipo_Veiculo ("");
		if (util.doValida (DM_Tipo_Veiculo)) {
			ed.setDM_Tipo_Veiculo (DM_Tipo_Veiculo);
		}

		String DM_KIT = request.getParameter ("FT_DM_KIT");
		ed.setDM_KIT ("N");
		if (util.doValida (DM_KIT)) {
			ed.setDM_KIT (DM_KIT);
		}

		String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");
		if (util.doValida (DM_Relatorio)) {
			ed.setDM_Relatorio (DM_Relatorio);
		}

		String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");
		ed.setDM_Tipo_Conhecimento ("1");
		if (util.doValida (DM_Tipo_Conhecimento)) {
			ed.setDM_Tipo_Conhecimento (DM_Tipo_Conhecimento);
		}

		String NM_Especie = request.getParameter ("FT_NM_Especie");
		ed.setNM_Especie ("");
		if (util.doValida (NM_Especie)) {
			ed.setNM_Especie (NM_Especie);
		}

		String NM_Documento = request.getParameter ("FT_NM_Documento");
		ed.setNM_Documento ("");
		if (util.doValida (NM_Documento)) {
			ed.setNM_Documento (NM_Documento);
		}

		String NM_Documento2 = request.getParameter ("FT_NM_Documento2");
		ed.setNM_Documento2 ("");
		if (util.doValida (NM_Documento2)) {
			ed.setNM_Documento2 (NM_Documento2);
		}

		String NM_Documento3 = request.getParameter ("FT_NM_Documento3");
		ed.setNM_Documento3 ("");
		if (util.doValida (NM_Documento3)) {
			ed.setNM_Documento3 (NM_Documento3);
		}

		String NM_Documento4 = request.getParameter ("FT_NM_Documento4");
		ed.setNM_Documento4 ("");
		if (util.doValida (NM_Documento4)) {
			ed.setNM_Documento4 (NM_Documento4);
		}

		String oid_Veiculo = request.getParameter ("oid_Veiculo");
		if (oid_Veiculo != null && !oid_Veiculo.equals ("") && !oid_Veiculo.equals ("null")) {
			ed.setOID_Veiculo (oid_Veiculo);
		}
		String oid_Veiculo_Carreta = request.getParameter ("oid_Veiculo_Carreta");
		if (oid_Veiculo_Carreta != null && !oid_Veiculo_Carreta.equals ("") && !oid_Veiculo_Carreta.equals ("null")) {
			ed.setOID_Carreta (oid_Veiculo_Carreta);
		}
		String oid_Veiculo_Carreta2 = request.getParameter ("oid_Veiculo_Carreta2");
		if (oid_Veiculo_Carreta2 != null && !oid_Veiculo_Carreta2.equals ("") && !oid_Veiculo_Carreta2.equals ("null")) {
			ed.setOID_Carreta2 (oid_Veiculo_Carreta2);
		}

		String NM_Destinatario = request.getParameter ("FT_NM_Destinatario");
		ed.setNM_Destinatario ("");
		if (util.doValida (NM_Destinatario)) {
			ed.setNM_Destinatario (NM_Destinatario);
		}

		String NM_Destinatario2 = request.getParameter ("FT_NM_Destinatario2");
		ed.setNM_Destinatario2 ("");
		if (util.doValida (NM_Destinatario2)) {
			ed.setNM_Destinatario2 (NM_Destinatario2);
		}

		String NM_Destinatario3 = request.getParameter ("FT_NM_Destinatario3");
		ed.setNM_Destinatario3 ("");
		if (util.doValida (NM_Destinatario3)) {
			ed.setNM_Destinatario3 (NM_Destinatario3);
		}

		String NM_Destinatario4 = request.getParameter ("FT_NM_Destinatario4");
		ed.setNM_Destinatario4 ("");
		if (util.doValida (NM_Destinatario4)) {
			ed.setNM_Destinatario4 (NM_Destinatario4);
		}

		return ed;
	}

	public ColetaED getByOID (String oid_Coleta) throws Excecoes {
		ColetaED ed = new ColetaED ();

		if (util.doValida (oid_Coleta)) {
			ed.setOID_Coleta (oid_Coleta);
		}
		return new ColetaRN ().getByRecord (ed);
	}

	public void Imprime_ColetaJasper (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
		ColetaED ed = new ColetaED ();

		ed = this.carregaED (request);
		ColetaRN geRN = new ColetaRN ();
		geRN.Imprime_ColetaJasper (ed , Response);
	}	

	public void deleta (HttpServletRequest request) throws Excecoes {

		ColetaED ed = new ColetaED ();
		ed = this.carregaED (request);
		new ColetaRN ().deleta (ed);
	}

}
