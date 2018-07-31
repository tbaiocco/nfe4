/*
 * Created on 04/02/2005
 *
 */
package com.master.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class FormataData {
	private static final String FORMATO_DATA_TELA = "dd/MM/yyyy";
	private static final String FORMATO_HORA_TELA = "HH:mm";
	private static final String FORMATO_DATA_BD = "yyyy-MM-dd";
	public static final int DIA_EM_MILISEGUNDOS = 86400000;
	private static final String FORMATO_DATA_HORA_COMPLETO = "dd/MM/yyyy HH:mm:ss";
	private static final String FORMATO_DATA_HORA_COMPLETO_BANCO = "yyyy-MM-dd HH:mm:ss";

	/** Formata a data que veio do banco de dados
	 * @param data
	 * @return String com a data formatada
	 */
	public static String formataDataBT(java.sql.Date data) {
		if (data != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_DATA_TELA);
			return formatter.format(data);
		} else {
			return "";
		}
	}

	public static String formataDataBT(String data) {
		if (JavaUtil.doValida(data)) {
			java.sql.Date sqlDate = java.sql.Date.valueOf(data);
			return formataDataBT(sqlDate);
		} else {
			return "";
		}
	}

	/** Formata a data/hora que veio do banco de dados
	 * @param data
	 * @return String com a data/hora formatada
	 */
	public static String formataDataHoraBT(java.util.Date data) {
		if (data != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_DATA_BD);
			return formatter.format(data);
		} else {
			return "";
		}
	}
	/** Formata a data/hora que veio para o banco de dados
	 * @param data
	 * @return String com a data/hora formatada
	 */
	public static String formataDataHoraTB(java.util.Date data) {
		if (data != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_DATA_HORA_COMPLETO);
			return formatter.format(data);
		} else {
			return "";
		}
	}

	public static java.util.Date getDataBT(java.util.Date data) {
		if (data != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_DATA_BD);
			return java.sql.Date.valueOf(formatter.format(data));
		} else {
			return null;
		}
	}
	// entra AAAA-MM sai MM/AAAA 
	public static String getMesAno(String data) {
		if (data != null) {
			String x=data.substring(5, 7)+"/" + data.substring(0, 4);
			return x;
		} else {
			return null;
		}
	}

	
	/**
	 * Formata a data que veio da tela para gravar no banco
	 * @param data
	 * @return
	 */
	public static java.sql.Date formataDataTB(String data) {
		if (JavaUtil.doValida(data)) {
			SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_DATA_TELA);
			java.util.Date dataTela;
			try {
				dataTela = formatter.parse(data);
			} catch (ParseException e) {
				// System.err.println("Erro ao formatar data [" + data + "]");
				e.printStackTrace();
				return null;
			}
			formatter = new SimpleDateFormat(FORMATO_DATA_BD);
			return java.sql.Date.valueOf(formatter.format(dataTela));
		} else {
			return null;
		}
	}
	public static String formataHoraBT(java.util.Date HR_FormataData) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_HORA_TELA);
		return dateFormat.format(HR_FormataData);
    }

	//	*** String para Data
	public static Date strToDate(String data) throws Excecoes {
		Calendar calendar = Data.stringToCalendar(data, FORMATO_DATA_HORA_COMPLETO_BANCO);
		return calendar.getTime();
	}
	// Transforma uma string de data no formato da tela para uma string no formato de banco
	public static String stringTostringTB (String data) throws Excecoes {
		java.util.Date dt = formataDataTB(data);
		SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_DATA_BD);
		return formatter.format(dt);
	}
    
}