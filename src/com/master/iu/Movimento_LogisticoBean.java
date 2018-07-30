package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Movimento_LogisticoED;
import com.master.rn.Conhecimento_InternacionalRN;
import com.master.rn.Movimento_LogisticoRN;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Movimento_LogisticoBean {

	private Movimento_LogisticoED carregaED(HttpServletRequest request) throws Excecoes{
		Movimento_LogisticoED ed = new Movimento_LogisticoED();
		try{
			if(JavaUtil.doValida(request.getParameter("oid_Movimento_Logistico"))){
				ed.setOid_movimento_logistico(new Long(request.getParameter("oid_Movimento_Logistico")).longValue());
			}
			if(JavaUtil.doValida(request.getParameter("oid_Conhecimento"))){
				ed.setOid_conhecimento(request.getParameter("oid_Conhecimento"));
			}
			if(JavaUtil.doValida(request.getParameter("oid_Nota_Fiscal"))){
				ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
			}
			if(JavaUtil.doValida(request.getParameter("oid_Unidade_Origem"))){
				ed.setOid_unidade_origem(new Long(request.getParameter("oid_Unidade_Origem")).longValue());
			}
			if(JavaUtil.doValida(request.getParameter("oid_Unidade_Destino"))){
				ed.setOid_unidade_destino(new Long(request.getParameter("oid_Unidade_Destino")).longValue());
			}
			if(JavaUtil.doValida(request.getParameter("FT_DM_Tipo_Movimento"))){
				ed.setDm_tipo_movimento(request.getParameter("FT_DM_Tipo_Movimento"));
			}
			if(JavaUtil.doValida(request.getParameter("FT_NR_Quantidade"))){
				ed.setNr_quantidade(new Double(request.getParameter("FT_NR_Quantidade")).doubleValue());
			}
			if(JavaUtil.doValida(request.getParameter("oid_Manifesto"))){
				ed.setOid_manifesto(request.getParameter("oid_Manifesto"));
			}
			if(JavaUtil.doValida(request.getParameter("oid_Viagem"))){
				ed.setOid_viagem(request.getParameter("oid_Viagem"));
			}
			String data = request.getParameter("FT_DT_Movimento");
			String hora = request.getParameter("FT_HR_Movimento");
			if(!JavaUtil.doValida(data))
				data = Data.getDataDMY();
			if(!JavaUtil.doValida(hora))
				hora = Data.getHoraHM();
			data = data + hora;
			ed.setDt_hr_movimento(Data.stringToCalendar(data, "dd/MM/yyyyHH:mm").getTime());

			if(JavaUtil.doValida(request.getParameter("FT_DT_Inicial"))){
				ed.setDt_inicial(request.getParameter("FT_DT_Inicial"));
			}
			if(JavaUtil.doValida(request.getParameter("FT_DT_Final"))){
				ed.setDt_final(request.getParameter("FT_DT_Final"));
			}
			if(JavaUtil.doValida(request.getParameter("oid_Unidade_Final"))){
				ed.setOid_unidade_final(new Long(request.getParameter("oid_Unidade_Final")).longValue());
			}

		}
		catch(Exception e){
			e.printStackTrace();
			throw new Excecoes("Erro de carregamento.", e, this.getClass().getName(), "carregaED()");
		}
		return ed;
	}

	public Movimento_LogisticoED inclui(HttpServletRequest request)throws Excecoes{

		try{
			Movimento_LogisticoRN Movimento_Logisticorn = new Movimento_LogisticoRN();
			return Movimento_Logisticorn.inclui(this.carregaED(request));
		}
		catch (Excecoes exc){
			throw exc;
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de inclusao.", exc, this.getClass().getName(), "inclui()");
		}
	}

	public void altera(HttpServletRequest request)throws Excecoes{

		try{
			Movimento_LogisticoRN Movimento_Logisticorn = new Movimento_LogisticoRN();
			Movimento_Logisticorn.altera(this.carregaED(request));
		}
		catch (Excecoes exc){
			throw exc;
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de alteracao.", exc, this.getClass().getName(), "altera()");
		}
	}

	public void deleta(HttpServletRequest request)throws Excecoes{

		try{
			Movimento_LogisticoRN Movimento_Logisticorn = new Movimento_LogisticoRN();
			Movimento_Logisticorn.deleta(this.carregaED(request));
		}
		catch (Excecoes exc){
			throw exc;
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de exclusao.", exc, this.getClass().getName(), "deleta()");
		}
	}

	public ArrayList Movimento_Logistico_Lista(HttpServletRequest request)throws Excecoes{

		Movimento_LogisticoED ed = this.carregaED(request);
		return new Movimento_LogisticoRN().lista(ed);

	}

	public Movimento_LogisticoED getByRecord(HttpServletRequest request)throws Excecoes{
		Movimento_LogisticoED ed = this.carregaED(request);
		return new Movimento_LogisticoRN().getByRecord(ed);
	}

	public void geraRelatorio(HttpServletRequest request, HttpServletResponse response)throws Excecoes{
		Movimento_LogisticoED ed = this.carregaED(request);

		ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
	  	ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));

	  	if (JavaUtil.doValida(request.getParameter("oid_Pessoa_Pagador"))){
	  		ed.setOid_pessoa_pagador(request.getParameter("oid_Pessoa_Pagador"));
	  	}

	  	if (JavaUtil.doValida(request.getParameter("FT_NR_Conhecimento"))){
	  		ed.setNr_conhecimento(request.getParameter("FT_NR_Conhecimento"));
	  	}

	  	String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
	  	if (JavaUtil.doValida(Dt_Emissao_Inicial)){
	  		ed.setDt_inicial(Dt_Emissao_Inicial);
	  	}

	  	String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
	  	if (JavaUtil.doValida(Dt_Emissao_Final)){
	  		ed.setDt_final(Dt_Emissao_Final);
	  	}
	  	String oid_Unidade = request.getParameter("oid_Unidade");
	  	if (JavaUtil.doValida(oid_Unidade)){
	  		ed.setOid_unidade_origem(new Long(oid_Unidade).longValue());
	  	}
	  	String oid_Unidade_destino = request.getParameter("oid_Unidade_Destino");
	  	if (JavaUtil.doValida(oid_Unidade_destino)){
	  		ed.setOid_unidade_destino(new Long(oid_Unidade_destino).longValue());
	  	}

	  	ed.setRelatorio(request.getParameter("relatorio"));

	  	ed.setSO(request.getParameter("FT_DM_Saida"));

		new Movimento_LogisticoRN().geraRelatorio(ed, response);
	}

	public ArrayList Movimento_Logistico_Lista(String oid_Conhecimento)throws Excecoes{

		Movimento_LogisticoED ed = new Movimento_LogisticoED();
		if(JavaUtil.doValida(oid_Conhecimento))
			ed.setOid_conhecimento(oid_Conhecimento);
		return new Movimento_LogisticoRN().lista(ed);

	}

	public void gera_Movimento(String oid_Nota_Fiscal)throws Excecoes{

		try{
			Movimento_LogisticoRN Movimento_Logisticorn = new Movimento_LogisticoRN();
			Movimento_LogisticoED ed = new Movimento_LogisticoED();
			if(JavaUtil.doValida(oid_Nota_Fiscal))
				ed.setOid_nota_fiscal(oid_Nota_Fiscal);
			Movimento_Logisticorn.gera_Movimento(ed);
		}
		catch (Excecoes exc){
			throw exc;
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de geracao.", exc, this.getClass().getName(), "gera_Movimento()");
		}
	}

}
