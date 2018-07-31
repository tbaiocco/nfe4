/*
 * Created on 21/07/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.master.util;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Administrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Valores {

    /**
	 * Método responsável por tratar numeros do tipo double.
	 * @param double
	 * @param int
	 * @return double
	 */
	public static double trataDouble(double decimal, int casasDecimais){
	   BigDecimal bd = new BigDecimal(decimal);
	   bd = bd.setScale(casasDecimais, BigDecimal.ROUND_HALF_UP);
	   decimal = bd.doubleValue();
	   return decimal;
	}

    /**
     * Realiza a conversao de uma string "monetaria" para um valor double.
     * Exemplo:
     * 	Recebe 1.500,25 retorna 1500.25
     * 	Recebe 500,00 retorna 500.0
     * 	Recebe null retorna 0.0
     * @param numero
     * @return double
     */
    public static double converteStringToDouble(String numero) {
        double num;
        try {
            if (JavaUtil.doValida(numero))
            {
                char array[] = numero.toCharArray();
                String numStr = "";
                for (int i=0; i < array.length; i++) {
                    if (array[i] != '.')
                        numStr += array[i];
                }
                numStr = numStr.replace(',','.');
                num = Double.parseDouble(numStr);
            }
            else {
                num = 0;
            }
        }
        catch(Exception e) {
            // System.out.println(e);
            num = 0;
        }
        return num;
    }
    /**
     * Realiza a conversao de uma string formatada com ponto para um valor int.
     * Exemplo:
     * 	Recebe 1.500 retorna 1500
     * 	Recebe null retorna 0.0
     * @param numero
     * @return int
     */

    public static int converteStringToInt(String numero) {
    	int num=0;
        try {
            if (JavaUtil.doValida(numero))
            {
                char array[] = numero.toCharArray();
                String numStr = "";
                for (int i=0; i < array.length; i++) {
                    if (array[i] != '.')
                        numStr += array[i];
                }
                num = Integer.parseInt(numStr);
            }
            else {
                num = 0;
            }
        }
        catch(Exception e) {
            // System.out.println(e);
            num = 0;
        }
    	return num;
    }
    /**
     * Realiza a conversao de uma string formatada com ponto para um valor long.
     * Exemplo:
     * 	Recebe 1.500 retorna 1500
     * 	Recebe null retorna 0
     * @param numero
     * @return int
     */

    public static long converteStringToLong(String numero) {
    	long num=0;
        try {
            if (JavaUtil.doValida(numero))
            {
                char array[] = numero.toCharArray();
                String numStr = "";
                for (int i=0; i < array.length; i++) {
                    if (array[i] != '.')
                        numStr += array[i];
                }
                num = Long.parseLong(numStr);
            }
            else {
                num = 0;
            }
        }
        catch(Exception e) {
            // System.out.println(e);
            num = 0;
        }
    	return num;
    }
    /**
     * Realiza a conversao de um double para uma String formatada (2 casas decimais).
     * Exemplo:
     * 	Recebe 1500.25 e retorna 1.500,25
     * 	Recebe 500.0 e retorna 500,00
     * 	Recebe 0.0 e retorna 0,00
     * @param numero
     * @return double
     */
    public static String converteDoubleToString(double numero) {
        //tratando o double para duas casas decimais
    	StringBuffer result = new StringBuffer();
    	try{
    		double val = Valores.trataDouble(numero,2);
    		String numStr = String.valueOf(val);
    		String antesPonto;
    		String depoisPonto;

    		int cont = 0;
    		char caracters[];

    		//obtem a posicao do ponto dentro do double
    		int posPonto = numStr.lastIndexOf(".");

    		//obtem a string com o valor anterior ao ponto
    		antesPonto = numStr.substring(0,posPonto);

    		//obtem a string antes do ponto e coloca num array de caracteres
    		caracters = antesPonto.toCharArray();

    		//obtem o tamanho do array para fazer o loop
    		int i = caracters.length-1;

    		//faz um loop decrescente no array de caracteres para adicionar o ponto (milhar)
    		while (i >= 0) {
    			result.append(caracters[i]);
    			cont++;
    			if ((cont == 3) && (i != 0)) {
    				result.append(".");
    				cont = 0;
    			}
    			i--;
    		}

    		//reverte
    		result.reverse();

    		//obtem a string depois do ponto
    		depoisPonto = numStr.substring(posPonto+1,numStr.length());

    		//se o tamanho depois do ponto for igual a 1, adiciona um zero
    		if (depoisPonto.length() == 1) {
    			depoisPonto += "0";
    		}

    		//adiciona uma vírgula e a parte depois do ponto
    		result.append(","+depoisPonto);

    		return result.toString();
    	} catch(Exception e){
    		return "0,00";
    	}

    }

	public static String formatarValor(double valor, int casasDecimais, boolean separador) {

		String valorFormatado = "";
		java.text.DecimalFormat  formatador  = new java.text.DecimalFormat();

		formatador.setMaximumFractionDigits(casasDecimais);
		valorFormatado = "" + formatador.format(valor);

		//// System.out.println("Entrou : valorFormatado = " + valorFormatado);

		if (valorFormatado.indexOf(",") != -1){
			String dec = valorFormatado.substring(valorFormatado.indexOf(",")+1);
			while (dec.length() < casasDecimais){
				dec += "0";
			}
			if (separador){
				valorFormatado = valorFormatado.substring(0, valorFormatado.indexOf(",")) + "," + dec;
			} else {
				valorFormatado = valorFormatado.substring(0, valorFormatado.indexOf(",")) + dec;
			}
		} else {
			valorFormatado = SeparaEndereco.limpaCampo(valorFormatado);
			for (int i = 0; i < casasDecimais; i++){
				valorFormatado += "0";
			}
		}

		//// System.out.println("Saiu : valorFormatado = " + valorFormatado);
		return valorFormatado;

	}

    public synchronized static String formatNumber(long number, String formatString){
        NumberFormat format = new DecimalFormat(formatString);
        return format.format(number);
    }

    /**
     *Recebe um double e retorna formato "0.000,00" .
     *
     *@param valor double.
     *@return valor String com o formato 0.000,00 .
     */
    public synchronized static String formatMonetario(double valor){

		DecimalFormat dF = new DecimalFormat("##,###,##0.00");
		return dF.format(valor);

    }

    public static String doubleToStrNaoArredonda(double nrDouble, char tipoSepDecimal){

        String nrString = String.valueOf(nrDouble);
        int tam = nrString.length();
        int pos = nrString.indexOf(".");
        int dif = tam - pos;

        if(dif == 2){
            nrString = nrString + "0";
            nrString = nrString.replace('.',tipoSepDecimal);
        }
        if(dif>2){
            nrString = nrString.substring(0, (nrString.indexOf(".")+3));
            nrString = nrString.replace('.',tipoSepDecimal);
        }

        return nrString;
    }

    public static String doubleToStrNaoArredonda3Decimais(double nrDouble, char tipoSepDecimal){

       String nrString = String.valueOf(nrDouble);
       int tam = nrString.length();
       int pos = nrString.indexOf(".");
       int dif = tam - pos;

       if(dif == 2){
           nrString = nrString + "00";
           nrString = nrString.replace('.',tipoSepDecimal);
       }

       if(dif == 3){
           nrString = nrString + "0";
           nrString = nrString.replace('.',tipoSepDecimal);
       }

       if(dif>3){
           nrString = nrString.substring(0, (nrString.indexOf(".")+4));
           nrString = nrString.replace('.',tipoSepDecimal);
       }

       return nrString;
   }

    public synchronized static String formatMonetario3Decimais(double valor){

		DecimalFormat dF = new DecimalFormat("###,###,##0.000");
		return dF.format(valor);

    }

}
