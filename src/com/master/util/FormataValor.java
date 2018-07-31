/*
 * Created on 25/04/2005
 *
 */
package com.master.util;

import java.text.DecimalFormat;

/**
 * @author Tiago
 *
 */
public class FormataValor {
    private static final String PATTERN_BT = "#,##0";
    
    public static String formataValorBT(double valor, int decimal) {
        DecimalFormat format = new DecimalFormat(PATTERN_BT);
        format.setMinimumFractionDigits(decimal);
        format.setMaximumFractionDigits(decimal);
        return format.format(Double.isNaN(valor) ? 0 : valor);
    }
    
    public static String formataValorBT(String valor, int decimal) { 
        String toReturn = "";
        try {
            if (JavaUtil.doValida(valor))
            {
                String numStr = "";
                if (valor.indexOf(",") != -1)
                {
                    char array[] = valor.toCharArray();
                    for (int i=0; i < array.length; i++) {
                        if (array[i] != '.') 
                            numStr += array[i];
                    }
                    numStr = numStr.replace(',','.');
                } else numStr = valor;
                toReturn = FormataValor.formataValorBT(Double.parseDouble(numStr),decimal);
            }
        } catch(Exception e) {
            // System.out.println(e);
        }
        return toReturn;
    }
    
    public static String fillLeft(double valor, String caracter, int tamanho) {
        String pattern = caracter;
        for (int i = 0; i < tamanho - 1; i++) {
            pattern = pattern.concat(caracter);
        }
        return new DecimalFormat(pattern).format(valor);
    }
}