package com.master.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Classe utilitaria para manipulacao dos campos componentes do arquivo a ser gerado.
 * @author teo
 *
 */
public class StringUtil {
	public static String SHORT_DATE = "yyyy-MM-dd";

	static public String preencheString(String nome, int tamanho,
			String preenchimento) {

		String palavra = "";
		int i = 1, tam;

		if (nome != null && !nome.equals("null")) {
			palavra = nome;
		}
		tam = tamanho - palavra.length();
		if (tam > 0) {
			while (i <= tam) {
				palavra = palavra + preenchimento;
				i++;
			}
		}
		return palavra;
	}

	public static String getValueDef(String value, String vdefault) {
		return (doValida(value)) ? value : vdefault;
	}

	public static String getValueDefZero(String value, String vdefault) {
		return (doValidaZero(value)) ? value : vdefault;
	}

	public static boolean doValida(String parametro) {
		return parametro != null
				&& !"0,00".equals(parametro.trim())
				&& !"0,000".equals(parametro.trim())
				&& !"0,0000".equals(parametro.trim())
				&& !"0,00000".equals(parametro.trim())
				&& !"0,000000".equals(parametro.trim())
				&& !"0.00".equals(parametro.trim())
				&& !"0.000".equals(parametro.trim())
				&& !"".equals(parametro.trim())
				&& !"null".equals(parametro.trim().toLowerCase());
	}

	public static String retornaValidado(String string) {
		return StringUtil.doValida(string)?string:"";
	}

	public static String retornaValor(String string) {
		return StringUtil.doValida(string)?string:"0,00";
	}
	public static String retornaValorBranco(String string) {
		return StringUtil.doValida(string)?string:"";
	}

	public static String retornaValidado(String string, int tam) {
		return StringUtil.doValida(string)?trunc(string,tam):"";
	}
	public static String trunc (String value , int maxLength) {
	    if (value != null && value.length () > maxLength) {
	      return value.substring (0 , maxLength);
	    }
	    else {
	      return value;
	    }
	  }

	public static boolean doValidaZero(String parametro) {
		return parametro != null
				&& !"0,00".equals(parametro.trim())
				&& !"0,000".equals(parametro.trim())
				&& !"0,0000".equals(parametro.trim())
				&& !"0,00000".equals(parametro.trim())
				&& !"0,000000".equals(parametro.trim())
				&& !"0.00".equals(parametro.trim())
				&& !"0.000".equals(parametro.trim())
				&& !"".equals(parametro.trim())
				&& !"null".equals(parametro.trim().toLowerCase());
	}

	public static String getValue(String value) {
		return (value != null && !"".equals(value.trim())
				&& !"0".equals(value.trim()) && !"null".equals(value.trim())) ? value
				.trim()
				: "";
	}

	public static double getDoubleMascara(double valor, String MASCARA) {

		if (valor <= 0) {
			valor = 0;
		}
		return Double.parseDouble(new DecimalFormat(MASCARA).format(valor)
				.replace(',', '.'));
	}

	public static String getValor_toString(double valor, String MASCARA) {
		String retorno = new DecimalFormat(MASCARA).format(valor);
		return String.valueOf(limpaCampo(retorno).replace(',', '.'));
	}

	public static String getValor_toStringEmpty(double valor, String MASCARA) {
		String retorno = new DecimalFormat(MASCARA).format(valor);
		return doValida(retorno) ? String.valueOf(limpaCampo(retorno).replace(',', '.')) : "";
	}

	public static String getDataYMD() {

		SimpleDateFormat formatter_date = new SimpleDateFormat(SHORT_DATE);
		java.util.Date currentTime_1 = Calendar.getInstance().getTime();
		return (formatter_date.format(currentTime_1));

	}

	public static String getDataFormata(String data)  {

		SimpleDateFormat formatter_date = new SimpleDateFormat(SHORT_DATE);
		java.util.Date currentTime_1 = stringToCalendar(data, "dd/MM/yyyy").getTime();
		return (formatter_date.format(currentTime_1));

	}

	public static Calendar stringToCalendar(String str1, String format) {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();

        try {

            if ("".equals(str1)) {
                return null;
            }
            formatter.setLenient(false);
            Date date = formatter.parse(str1);
            calendar.setTime(date);
        } catch (Exception exc) {
        }
        return calendar;
    }

	public static Calendar stringToCalendar(String str1, String format, Locale loc) {

        SimpleDateFormat formatter = new SimpleDateFormat(format, loc);
        Calendar calendar = Calendar.getInstance();
//System.out.println("StringUtil:"+str1);
        try {

            if ("".equals(str1)) {
                return Calendar.getInstance();
            }
            calendar.setTime(formatter.parse(str1));
//            formatter.setLenient(false);
//            Date date = formatter.parse(str1);
//            calendar.setTime(date);
//System.out.println("StringUtil:"+FormataData.formataDataHoraBT(calendar.getTime()));
        } catch (Exception exc) {
        	exc.printStackTrace();
        }
        return calendar;
    }

	static public String alinhaNumeroDireita(String numero, int tamanho) {
        int i = 1, tam;
        if(!doValida(numero)){
        	numero = "0";
        }
        String palavra = numero;
        tam = tamanho - numero.length();
        if (tam > 0) {
            while (i <= tam) {
                palavra = "0" + palavra ;
                i++;
            }
        }
        return palavra;
    }

	public static String limpaCampo(String str) {

		String ret = "";

		if (str != null) {
			char[] strChar = str.toCharArray();
			char[] novaStr = new char[strChar.length];
			int j = 0;
			for (int i = 0; i < strChar.length; i++) {
				if ((strChar[i] != '.') && (strChar[i] != '-')
						&& (strChar[i] != ' ')) {
					novaStr[j] = strChar[i];
					j++;
				}
			}
			char[] cRet = new char[j];

			System.arraycopy(novaStr, 0, cRet, 0, j);

			ret = new String(cRet);
		}
		return ret;
	}

	static public String retornaNumeroValido(String valor) {
        int i = 0, erro = 0;
        String numero = "";
        String caracter = "";
        if(doValida(valor)){
        	while (i <= valor.length() - 1 ) {
            	caracter = valor.substring(i, i + 1);

            	if (caracter.equals("0") ||
            		caracter.equals("1") ||
            		caracter.equals("2") ||
            		caracter.equals("3") ||
            		caracter.equals("4") ||
            		caracter.equals("5") ||
            		caracter.equals("6") ||
            		caracter.equals("7") ||
            		caracter.equals("8") ||
            		caracter.equals("9")) {
            		numero+=caracter;
                }
            	i++;
            }
        }
        return numero;
    }

	static public String retornaNumeroValido(String valor, int tam) {
        int i = 0, erro = 0;
        String numero = "";
        String caracter = "";
        if(doValida(valor)){
        	while (i <= valor.length() - 1 ) {
            	caracter = valor.substring(i, i + 1);

            	if (caracter.equals("0") ||
            		caracter.equals("1") ||
            		caracter.equals("2") ||
            		caracter.equals("3") ||
            		caracter.equals("4") ||
            		caracter.equals("5") ||
            		caracter.equals("6") ||
            		caracter.equals("7") ||
            		caracter.equals("8") ||
            		caracter.equals("9")) {
            		numero+=caracter;
                }
            	i++;
            }
        }
        return trunc(numero,tam);
    }

}
