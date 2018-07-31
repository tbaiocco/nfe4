/*
 * Created on 10/12/2004
 */
package com.master.util;

import java.text.DecimalFormat;

/**
 * @author Andre Valadas
 */
public class Valor {

  private final static String DUAS_CASAS_DEC = "#0.00";
  private final static String TRES_CASAS_DEC = "#0.000";
  private final static String QUATRO_CASAS_DEC = "#0.0000";
  private final static String CINCO_CASAS_DEC = "#0.00000";
  private final static String SEIS_CASAS_DEC = "#0.000000";

  //*** Formata Valor para Decimal com Duas Casas, substituindo virgulas por ponto
   public static double getValorArredondado (double valor) {
     if (valor > 0) {
       return Double.parseDouble (new DecimalFormat (DUAS_CASAS_DEC).format (valor).replace (',' , '.'));
     }
     else return valor;
   }

  //*** Formata Valor de acordo com o numero de casas Decimais passado por par�metro
   public static double getValorArredondado (double valor , int nCasas) {

     String MASCARA = DUAS_CASAS_DEC;
     if (valor > 0) {
       //*** Seta M�scara
        if (nCasas == 3)
          MASCARA = TRES_CASAS_DEC;
        else if (nCasas == 4)
          MASCARA = QUATRO_CASAS_DEC;
        else if (nCasas == 5)
          MASCARA = CINCO_CASAS_DEC;
        else if (nCasas == 6)
          MASCARA = SEIS_CASAS_DEC;

       return Double.parseDouble (new DecimalFormat (MASCARA).format (valor).replace (',' , '.'));
     }
     else return valor;
   }

  /**
   * trunca o valor apos as casas decimais
   * @param valor - numero a ser truncado
   * @param decimal - numero de casas decimais
   */
  public static double trunc (double valor , int decimal) {
    if (decimal > 0) {
      double result = (valor * Math.pow (10 , decimal));
      return Math.floor (result) / Math.pow (10 , decimal);
    }
    else return Math.floor (valor);
  }

  public static long truncLong (double valor , int decimal) {
    return (long) trunc (valor , decimal);
  }

  public static long truncLong (double valor) {
    return (long) trunc (valor , 0);
  }

  public static int truncInt (long valor , int decimal) {
    return (int) trunc (valor , decimal);
  }

  public static int truncInt (long valor) {
    return (int) trunc (valor , 0);
  }

  /**
   * arredonda o valor apos as casas decimais
   * @param valor - numero a ser truncado
   * @param decimal - numero de casas decimais
   */
  public static double round (double valor , int decimal) {
    if (decimal > 0) {
      double result = (valor * Math.pow (10 , decimal));
      return Math.round (result) / Math.pow (10 , decimal);
    }
    else return Math.floor (valor);
  }

  public static double round (double valor) {
    return round (valor , 2);
  }

  /**
   * Calcula % sobre valor
   */
  public static double calcPercentual (double valor , double percentual) {
    if (valor != 0 && percentual != 0) {
      return ( (valor * percentual) / 100);
    }
    else return 0;
  }

  /**
   * Valida Valor Toler�ncia
   * @param valorBase - valor base a ser comparado com tolerancia
   * @param valorTol - valor a ser toleravel
   * @param tolerancia - valor para tolerancia sobre o valorTol
   * @param decimal - numero de casas decimais
   */
  public static boolean validaTolerancia (double valorBase , double valorTol , double tolerancia , int decimal) {
    if (valorBase != 0 && tolerancia != 0) {
      return (valorTol <= Valor.round (valorBase + tolerancia , JavaUtil.getValueDef (decimal , 2)) &&
              valorTol >= Valor.round (valorBase - tolerancia , JavaUtil.getValueDef (decimal , 2)));
    }
    return true;
  }

  public static boolean validaTolerancia (double valorBase , double valorTol , double tolerancia) {
    return Valor.validaTolerancia (valorBase , valorTol , tolerancia , 2);
  }

  
}