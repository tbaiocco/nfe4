package com.master.util;

/**
 * @author Andre Valadas
 * @serialData 17/02/2006
 */
public class ModuloUtil {

    /**
     * CÁLCULO P/ MÓDULO 10
     */
    public static String getModulo10(String value) throws Mensagens {
        
        int modulo = 10;
        int calculo = 0,
            resto = 0,
            soma = 0;
        try {
            for (int i=0; i < value.length(); i++)
            {
                int pos = value.length()-i;
                //*** Divisão inicial por 2 da Direita p/ Esquerda
                if (i % 2 == 0)
                {
                    soma = 2 * Integer.parseInt(value.substring(pos-1, pos));
                    while (soma >= 10)
                    {
                        String strSoma = String.valueOf(soma);
                        soma = 0;
                        for (int x=0; x<strSoma.length(); x++)
                            soma += Integer.parseInt(strSoma.substring(x, x+1));
                    }
                    calculo += soma;
                } else calculo += Integer.parseInt(value.substring(pos-1, pos));
            }
            resto = calculo % modulo;
            int toReturn = modulo - resto;
            if (toReturn >= 10)
                toReturn = 0;
            return String.valueOf(toReturn);
        } catch(Exception e) {
            throw new Mensagens("Erro getModulo10 - ["+e.getMessage()+"]");
        }
    }
    
    /**
     * CÁLCULO P/ MÓDULO 11
     */
    public static String getModulo11(String value, int maxMulti) throws Mensagens {
        
        int modulo = 11;
        int calculo = 0,
            resto = 0,
            soma = 0;
        int multi = 2;
        try {
            for (int i=0; i < value.length(); i++)
            {
                int pos = value.length()-i;
                //*** Divisão inicial por 2 da Direita p/ Esquerda
                soma = multi * Integer.parseInt(value.substring(pos-1, pos));
                calculo += soma;
                multi++;
                if (multi > maxMulti)
                    multi = 2;
            }
            resto = calculo % modulo;
            int toReturn = modulo - resto;
            if (toReturn >= 10)
                toReturn = 0;
            return String.valueOf(toReturn);
        } catch(Exception e) {
            throw new Mensagens("Erro getModulo11 - ["+e.getMessage()+"]");
        }
    }
    
    /**
     * DIGITO VERIFICADOR CD_Cliente
     * Módulo 10, só que Calcula Valor invertido, da Esquerda p/ Direita.
     */
    public static String getDigitoVerificador(String value) throws Mensagens {
        
        //*** Validações
        if (!JavaUtil.doValida(value) || value.trim().length() != 5)
            return "";
        int modulo = 10;
        //*** Variáveis
        int calculo = 0,
            resto = 0,
            soma = 0;
        
        try {
            for (int i=0; i < value.length(); i++)
            {
                if (i % 2 != 0)
                {
                    soma = 2 * Integer.parseInt(value.substring(i, i+1));
                    while (soma >= 10)
                    {
                        String strSoma = String.valueOf(soma);
                        soma = 0;
                        for (int x=0; x<strSoma.length(); x++)
                            soma += Integer.parseInt(strSoma.substring(x, x+1));
                    }
                    calculo += soma;
                } else calculo += Integer.parseInt(value.substring(i, i+1));
            }
            resto = calculo % modulo;
            int toReturn = modulo - resto;
            if (toReturn >= 10)
                toReturn = 0;
            return String.valueOf(toReturn);
        } catch(Exception e) {
            throw new Mensagens("Erro getDigitoVerificador - ["+e.getMessage()+"]");
        }
    }
    
    /**
     * TESTES
     */
    public static void main(String[] args) throws Exception {

        /** BOLETO BANCO DO BRASIL **/
        // System.out.println("BANCO DO BRASIL NEW == "+BloquetoUtil.calculaBANCO_BRASIL("12135251598"));
        
        /** CHEQUES **/
        //******** banco+agencia+x1+comp+nrCheque+"5"+x2+C.Corrente+x3 *********//
        String banda = "<00801349<0100000325<006568367301:";
        
        //*** P/ BIU 
        // comp+banco+agencia+x2+00+C.Corrente+x1+nrCheque+x3
        // "0100080134000065683673090000321"

        // System.out.println("---------------------");
        // System.out.println("x1 == "+getModulo10(banda.substring(10,20))+" banda: "+banda.substring(10,20));
        // System.out.println("x2 == "+getModulo10(banda.substring(1,8))+" banda: "+banda.substring(1,8));
        // System.out.println("x3 == "+getModulo10(banda.substring(22,32))+" banda: "+banda.substring(22,32));
        
        // System.out.println("c1 == "+getModulo11("0100080134",9));
        // System.out.println("c2 == "+getModulo11("656836730",9));
        // System.out.println("c3 == "+getModulo11("000032",9));
    }
}
