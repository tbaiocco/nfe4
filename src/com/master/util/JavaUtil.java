/*
 * Created on 23/11/2004
 */
package com.master.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andre Valadas
 */
public class JavaUtil {
  /**
   * Valida campos
   */
  public static boolean doValida (String parametro) {
    return parametro != null
        && !"0".equals (parametro.trim ())
        && !"0,00".equals (parametro.trim ())
        && !"0,000".equals (parametro.trim ())
        && !"0.00".equals (parametro.trim ())
        && !"0.000".equals (parametro.trim ())
        && !"".equals (parametro.trim ())
        && !"null".equals (parametro.trim ().toLowerCase ());
  }

  public static String getValue (String value) {
    return (value != null
            && !"".equals (value.trim ())
            && !"0".equals (value.trim ())
            && !"null".equals (value.trim ())) ? value.trim () : "";
  }

  public static String getValueDif (String value , String vmask) {
    if (getValue (value) != "" && vmask != null) {
      return value.equals (vmask) ? "" : value;
    }
    else {
      return getValue (value);
    }
  }

  public static String getValueDef (String value , String vdefault) {
    return (doValida (value)) ? value : vdefault;
  }

  public static int getValueDef (int value , int intDefault) {
    return (value > 0) ? value : intDefault;
  }

  public static int getValueDef (String value , int intDefault) {
    return (doValida (value)) ? Integer.parseInt (value) : intDefault;
  }

  public static double getValueDef (double value , double dblDefault) {
    return (value > 0) ? value : dblDefault;
  }

  public static double getValueDef (String value , double dblDefault) {
    return (doValida (value)) ? Double.parseDouble (value) : dblDefault;
  }

  public static String getStringNotNull (String string , String replaceString) {
    return string == null ? replaceString : string;
  }

  public static String getStringNotNull (String string , String replaceString , String retorno) {
    String toReturn = getStringNotNull (string , replaceString);
    return toReturn.equals (replaceString) ? toReturn : retorno;
  }

  public static void contaCaracteres (HttpServletRequest request) {
    Enumeration p = request.getParameterNames ();
    int qtde = 0;
    while (p.hasMoreElements ()) {
      String t = p.nextElement ().toString ();
      qtde = qtde + request.getParameter (t).length ();
      // System.out.println (t + " = " + request.getParameter (t) + "     qtde=" + qtde);
    }
    // System.out.println ("QueryString == " + request.getQueryString ());
  }

  public static String trimString (String string) {
    return getStringNotNull (string , "").trim ();
  }

  public static String getSQLDate (String date) {
    return (!doValida (date)) ? "null" : "'" + date + "'";
  }

  public static String getSQLString (String string) {
    return string == null ? "null" : "'" + string + "'";
  }

  public static String getSQLStringLike (String string) {
    return string == null ? "null" : "'%" + string + "%'";
  }

  public static String getSQLStringDef (String string , String defaultValue) {
    return string == null ? "'" + defaultValue + "'" : "'" + string + "'";
  }

  public static String parseSN (String value , String retornS , String retornN) {
    return "S".equals (value) ? retornS : retornN;
  }

  /**
   * Insere zeros a esquerda
   * @param String str
   * @param int NewLen - tamanho da nova string com zeros a esquerda
   * @param fillChar caracter que sera usado para porencher o tamanho
   * @param boolena trunc - se a string original for maior que NnewLen ela trunca
   */
  public static String LFill (String str , int NewLen , char fillChar , boolean trunc) {
    String result;
    if (str != null) {
      result = str;
    }
    else {
      result = "";
    }
    if (result.length () <= NewLen) {
      while (result.length () < NewLen) {
        result = fillChar + result;
      }
    }
    else {
      if (trunc) {
        result = result.substring (0 , NewLen);
      }
    }
    return result;
  }

  public static String LFill (String str , int NewLen , boolean trunc) {
    return LFill (str , NewLen , '0' , trunc);
  }

  public static String LFill (int value , int NewLen , boolean trunc) {
    return LFill (value + "" , NewLen , trunc);
  }

  public static String formataCNPJ_CPF (String value) {
    return formatar (value , value.length () > 11 ? "##.###.###/####-##" : "###.###.###-##");
  }

  public static String formataCNPJ_CPF (String value , String pais) {
    if (doValida (pais) && pais.indexOf ("BR") > -1) {
      if (doValida (value) && value.length () == 14) {
        return value.substring (0 , 2) + "." + value.substring (2 , 5) + "." + value.substring (5 , 8) + "/" + value.substring (8 , 12) + "-" + value.substring (12);
      }
      else if (doValida (value) && value.length () == 11) {
        return value.substring (0 , 3) + "." + value.substring (3 , 6) + "." + value.substring (6 , 9) + "-" + value.substring (9);
      }
      else {
        return value;
      }
    }
    else {
      return value;
    }
  }

  public static String mascaraMICCRT (String cdPais , String permisso , String nrDoc) {

    String mascara = cdPais + "." + permisso + ".";
    int sizeNrDoc = 13 - mascara.length () - nrDoc.length ();
    for (int a = 0; a < sizeNrDoc; a++) {
      mascara = mascara + "0";
    }
    return mascara + nrDoc;
  }

  public static String trunc (String value , int maxLength) {
    if (value != null && value.length () > maxLength) {
      return value.substring (0 , maxLength);
    }
    else {
      return value;
    }
  }

  public static long trunc (long value , int maxLength) {
    if (value > 0 && String.valueOf (value).length () > maxLength) {
      return Long.parseLong (String.valueOf (value).substring (0 , maxLength));
    }
    else {
      return value;
    }
  }

  public static long truncInverse (long value , int maxLength) {
    if (value > 0 && String.valueOf (value).length () > maxLength) {
      int initLength = (String.valueOf (value).length () - maxLength);
      return Long.parseLong (String.valueOf (value).substring (initLength , String.valueOf (value).length ()));
    }
    else {
      return value;
    }
  }

  public static int trunc (int value , int maxLength) {
    if (value > 0 && String.valueOf (value).length () > maxLength) {
      return Integer.parseInt (String.valueOf (value).substring (0 , maxLength));
    }
    else {
      return value;
    }
  }

  /** Formatador por Mascáras
   * Exemplos:
   * *-- CPF --*
   * OnKeyPress="formatar(this, '###.###.###-##')"
   * *-- CEP --*
   * OnKeyPress="formatar(this, '#####-###')"
   * */
  public static String formatar (String value , String mask) {
    if (!doValida (value)) {
      return "";
    }

    String toReturn = "";
    int add = 0;
    for (int i = 0; i < value.length (); i++) {
      try {
        if (mask.length () >= (add + i + 1) && !mask.substring (add + i , add + i + 1).equals ("#")) {
          toReturn += mask.substring (add + i , add + i + 1);
          add++;
        }
      }
      catch (Exception e) {}
      toReturn += value.substring (i , i + 1);
    }
    return toReturn;
  }
  	/**
  	 * Troca & por &amp;
  	 * @param p
  	 * @return
  	 * @throws Excecoes
  	 */
	public static String preperaString ( String p ) throws Excecoes {
		String wp = p;
		String stringSaida = null;
		if (JavaUtil.doValida(p)) {
			if ( wp.indexOf("&")> 0 ) {
				wp = p.replaceAll("&","&amp;");
				stringSaida = wp;
			} else {
				stringSaida = p;
			}
		} else {
			stringSaida=p;
		}
		return stringSaida;
	}

	public static void showErrors(HashMap erros) {
		Set set = erros.entrySet();

		Iterator i = set.iterator();
		while (i.hasNext()) {
		    Map.Entry me = (Map.Entry) i.next();
		    System.out.println(me.getKey() + " : " + me.getValue());
		}
	}

  public static String getErrors(HashMap erros) {
		Set set = erros.entrySet();
		String toReturn = "";
		Iterator i = set.iterator();
		while (i.hasNext()) {
		    Map.Entry me = (Map.Entry) i.next();
		    toReturn += me.getValue() + "<br>";
		}
		return toReturn;
	}

}