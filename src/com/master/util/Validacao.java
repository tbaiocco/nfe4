package com.master.util;

/**
 * Classe com método para validação de CPF e CNPJ
 * @author André Valadas
 * @since   03/03/2006
 */
public class Validacao {

  /**
   * Realiza a validação do CNPJ.
   * @param strCNPJ número de CNPJ a ser validado
   * @return true se o CNPJ é válido e false se não é válido
   */

  static public boolean CNPJ (String strCNPJ) {

    int soma = 0 , dig;
    String cnpj_calc = strCNPJ.substring (0 , 12);
    if (strCNPJ.length () != 14 || "00000000000000".equals (strCNPJ) || "11111111111111".equals (strCNPJ) || 
        strCNPJ.startsWith ("99999") || "99999".equals(strCNPJ.substring(0,5)) ||
        "22222222222222".equals (strCNPJ) || "33333333333333".equals (strCNPJ) || "44444444444444".equals (strCNPJ) || "55555555555555".equals (strCNPJ) ||
        "66666666666666".equals (strCNPJ) || "77777777777777".equals (strCNPJ) || "88888888888888".equals (strCNPJ) || "99999999999999".equals (strCNPJ)) {
      return false;
    }

    char[] chr_cnpj = strCNPJ.toCharArray ();
    /* Primeira parte */
    for (int i = 0; i < 4; i++) {
      if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
      }
    }
    for (int i = 0; i < 8; i++) {
      if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
      }
    }

    dig = 11 - (soma % 11);
    cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString (dig);

    /* Segunda parte */
    soma = 0;
    for (int i = 0; i < 5; i++) {
      if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
      }
    }
    for (int i = 0; i < 8; i++) {
      if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
      }
    }
    dig = 11 - (soma % 11);
    cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString (dig);
    return strCNPJ.equals (cnpj_calc);
  }

  /**
   * Realiza a validação do CPF.
   * @param strCPF número de CPF a ser validado
   * @return true se o CPF é válido e false se não é válido
   */
  static public boolean CPF (String strCPF) {

    int d1 , d2;
    int digito1 , digito2 , resto;
    int digitoCPF;
    String nDigResult;
    d1 = d2 = 0;
    digito1 = digito2 = resto = 0;
    for (int nCount = 1; nCount < strCPF.length () - 1; nCount++) {
      digitoCPF = Integer.valueOf (strCPF.substring (nCount - 1 , nCount)).intValue ();
      // multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
      // e assim por diante.
      d1 = d1 + (11 - nCount) * digitoCPF;
      // para o segundo digito repita o procedimento incluindo o primeiro
      // digito calculado no passo anterior.
      d2 = d2 + (12 - nCount) * digitoCPF;
    }
    ;
    // Primeiro resto da divisão por 11.
    resto = (d1 % 11);
    // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
    // menos o resultado anterior.
    if (resto < 2) {
      digito1 = 0;
    }
    else {
      digito1 = 11 - resto;
    }
    d2 += 2 * digito1;
    // Segundo resto da divisão por 11.
    resto = (d2 % 11);
    // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
    // menos o resultado anterior.
    if (resto < 2) {
      digito2 = 0;
    }
    else {
      digito2 = 11 - resto;
    }
    // Digito verificador do CPF que está sendo validado.
    String nDigVerific = strCPF.substring (strCPF.length () - 2 , strCPF.length ());
    // Concatenando o primeiro resto com o segundo.
    nDigResult = String.valueOf (digito1) + String.valueOf (digito2);
    // comparar o digito verificador do cpf com o primeiro resto + o segundo
    // resto.
    return nDigVerific.equals (nDigResult);
  }

  public static void main (String[] args) {
    // System.out.println (CNPJ ("99999900090045") ? "OK" : "Incorreto");
    // System.out.println (CNPJ ("87226528000161") ? "OK" : "Incorreto");
    // System.out.println (CPF ("00726775037") ? "OK" : "Incorreto");
  }
}