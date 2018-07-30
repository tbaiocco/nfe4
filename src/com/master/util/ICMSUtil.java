/*
 * Created on 21/06/2005
 *
 */
package com.master.util;

import com.master.util.ed.Parametro_FixoED;

/**
 * @author Tiago
 *
 */
public class ICMSUtil {
    public static double calculaICMS(double baseICMS, double peICMS) {
        if ("POR_FORA".equals(Parametro_FixoED.getInstancia().getDM_Tipo_Calculo_ICMS())) {

            return  baseICMS / ((100 - peICMS) / 100) - baseICMS;
        } else return baseICMS * peICMS / 100;
    }


    public static boolean isTabelaAereo(String DM_Tipo_Tabela_Frete) {
        return "C".equals(DM_Tipo_Tabela_Frete);
    }
}
