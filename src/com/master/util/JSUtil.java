/*
 * Created on 08/04/2005
 *
 */
package com.master.util;

/**
 * @author Tiago
 *
 */
public class JSUtil {
    public static String processaString(String value) {
        return processaString(value, "\"");
    }
    public static String processaString(String value, String delimitadorString) {
        if (value != null) {
            String toReturn = "";
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) == 10) {
                    toReturn += "\\r";
                } else if (value.charAt(i) == 13) {
                    toReturn += "\\n";
                } else toReturn += String.valueOf(value.charAt(i));
            }
            if ("\"".equals(delimitadorString)) {
                return toReturn.replace('"', '\'');
            } else return toReturn.replace('\'', '"');
        } else return "";
    }
}
