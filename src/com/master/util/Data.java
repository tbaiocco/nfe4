package com.master.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.master.root.DateFormatter;

public class Data {


    public static String SHORT_DATE = "dd/MM/yyyy";
    public static String SHORT_DATE_BD = "yyyy-MM-dd";
    public static String SHORT_TIME = "HH:mm";
    private static Calendar calendar = null;
    public static String SHORT_DATE_no_barras = "ddMMyyyy";
    public static String SHORT_DATE_HOUR = "dd/MM/yyyy HH:mm:ss";

    public static String getDataCompleta() {

        calendar = Calendar.getInstance();
        SimpleDateFormat formatter_date = new SimpleDateFormat(SHORT_DATE_HOUR);
        java.util.Date currentTime_1 = calendar.getTime();
        return (formatter_date.format(currentTime_1));

    }

    public static String getDataDMY() {

        calendar = Calendar.getInstance();
        SimpleDateFormat formatter_date = new SimpleDateFormat(SHORT_DATE);
        java.util.Date currentTime_1 = calendar.getTime();
        return (formatter_date.format(currentTime_1));

    }
    public static int diferencaMeses(String Data1, String Data2) throws Excecoes {
        int doRetorno=0;
        if (JavaUtil.doValida(Data1) && JavaUtil.doValida(Data2)) {
	        Calendar DT_Data1 = Calendar.getInstance();
	        Calendar DT_Data2 = Calendar.getInstance();
	        try {
	            DT_Data1.setTime(new SimpleDateFormat(SHORT_DATE).parse(Data1));
	            DT_Data2.setTime(new SimpleDateFormat(SHORT_DATE).parse(Data2));
	            doRetorno= diferencaMeses(DT_Data1,DT_Data2);
	        } catch(ParseException e) {
	            throw new Excecoes(e.getMessage());
	        }
        }
        return doRetorno;
    }

    //*** Retorna Data Corrente no formato do BANCO
    public static String getDataYMD() {

        calendar = Calendar.getInstance();
        SimpleDateFormat formatter_date = new SimpleDateFormat(SHORT_DATE_BD);
        java.util.Date currentTime_1 = calendar.getTime();
        return (formatter_date.format(currentTime_1));

    }

    //*** String para Data
    public static Date strToDate(String data) throws Excecoes {
        Calendar calendar = Data.stringToCalendar(data, SHORT_DATE);
        return calendar.getTime();
    }

    public static String getMesCorrente() {

        calendar = Calendar.getInstance();
        SimpleDateFormat formatter_date = new SimpleDateFormat(SHORT_DATE);
        java.util.Date currentTime_1 = calendar.getTime();

        String mes = (formatter_date.format(currentTime_1)).substring(3, 5);
        int mes_ano = new Integer(mes).intValue();
        switch (mes_ano) {
        case 1:
          mes = "JANEIRO";
          break;
        case 2:
          mes = "FEVEREIRO";
          break;
        case 3:
          mes = "MARCO";
          break;
        case 4:
          mes = "ABRIL";
          break;
        case 5:
          mes = "MAIO";
          break;
        case 6:
          mes = "JUNHO";
          break;
        case 7:
          mes = "JULHO";
          break;
        case 8:
          mes = "AGOSTO";
          break;
        case 9:
          mes = "SETEMBRO";
          break;
        case 10:
          mes = "OUTUBRO";
          break;
        case 11:
          mes = "NOVEMBRO";
          break;
        case 12:
          mes = "DEZEMBRO";
          break;
      }
        return mes;
    }

    public static String getMesCorrente(String data) throws Excecoes {

        calendar = stringToCalendar(data, SHORT_DATE);
        SimpleDateFormat formatter_date = new SimpleDateFormat(SHORT_DATE);
        java.util.Date currentTime_1 = calendar.getTime();

        String mes = (formatter_date.format(currentTime_1)).substring(3, 5);
        int mes_ano = new Integer(mes).intValue();
        switch (mes_ano) {
        case 1:
          mes = "JANEIRO";
          break;
        case 2:
          mes = "FEVEREIRO";
          break;
        case 3:
          mes = "MARCO";
          break;
        case 4:
          mes = "ABRIL";
          break;
        case 5:
          mes = "MAIO";
          break;
        case 6:
          mes = "JUNHO";
          break;
        case 7:
          mes = "JULHO";
          break;
        case 8:
          mes = "AGOSTO";
          break;
        case 9:
          mes = "SETEMBRO";
          break;
        case 10:
          mes = "OUTUBRO";
          break;
        case 11:
          mes = "NOVEMBRO";
          break;
        case 12:
          mes = "DEZEMBRO";
          break;
      }
        return mes;
    }

    public static String getHoraHM() {

        //instancia objeto de formatacao de data
        //instancia objeto de data
        calendar = Calendar.getInstance();
        SimpleDateFormat formatter_time = new SimpleDateFormat(SHORT_TIME);
        java.util.Date currentTime_1 = calendar.getTime();
        return (formatter_time.format(currentTime_1));

    }

    public static String getMileSegundo() {

        int mes_ano = Calendar.MILLISECOND;
        return String.valueOf(mes_ano);
    }

    public static int diferencaDias(Calendar cal1, Calendar cal2) {
        Date d1 = cal1.getTime();
        Date d2 = cal2.getTime();

        if (d1.after(d2))
            return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
        else
            return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static int diferencaDias(String strData1, String strData2) throws ParseException {
    	int difDias=0;
        if (JavaUtil.doValida(strData1) && JavaUtil.doValida(strData2)) {
            Calendar cal1 = Calendar.getInstance(Locale.US);
            Calendar cal2 = Calendar.getInstance(Locale.US);
            cal1.setTime(new SimpleDateFormat(Data.SHORT_DATE).parse(strData1));
            cal2.setTime(new SimpleDateFormat(Data.SHORT_DATE).parse(strData2));
            difDias= diferencaDias(cal1, cal2);
        }
    	return difDias;
    }


    public static int diferencaAnos(Calendar cal1, Calendar cal2) {

        Date d1 = cal1.getTime();
        Date d2 = cal2.getTime();
        Calendar c = Calendar.getInstance();
        int i = -1;
        c.setTime((java.util.Date) d1.clone());
        while (c.getTime().getTime() <= d2.getTime()) {
            c.add(Calendar.YEAR, 1);
            i++;
        }
        return i;
    }

    public static int diferencaMeses(Calendar cal1, Calendar cal2) {

        Date d1 = cal1.getTime();
        Date d2 = cal2.getTime();
        Calendar c = Calendar.getInstance();
        int i = -1;
        c.setTime((java.util.Date) d1.clone());
        while (c.getTime().getTime() <= d2.getTime()) {
            c.add(Calendar.MONTH, 1);
            i++;
        }
        return i;
    }
	public static long diferencaDatasDias(Calendar data1, Calendar data2) {

		long dias = 0;
		if ( (data1 == null) || (data2 == null) ) {
			return dias;
		}
		else {
			//dias = ((data2.getTimeInMillis() - data1.getTimeInMillis())/1000/60/60/24);
		    dias = ((data2.getTime().getTime() - data1.getTime().getTime())/1000/60/60/24);
			return dias;
		}

	}

    public static Calendar stringToCalendar(String str1, String format) throws Excecoes {

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

    //*** Retorna TRUE se Data1 > Data2
    public static boolean comparaData(String Data1, String Data2) throws Excecoes {
        boolean doRetorno=false;
        if (JavaUtil.doValida(Data1) && JavaUtil.doValida(Data2)) {
	        Calendar DT_Data1 = Calendar.getInstance();
	        Calendar DT_Data2 = Calendar.getInstance();
	        try {
	            DT_Data1.setTime(new SimpleDateFormat(SHORT_DATE).parse(Data1));
	            DT_Data2.setTime(new SimpleDateFormat(SHORT_DATE).parse(Data2));
	            doRetorno= DT_Data1.after(DT_Data2);
	        } catch(ParseException e) {
	            throw new Excecoes(e.getMessage());
	        }
        }
        return doRetorno;
    }

    //*** Retorna TRUE se Data1 > Data2 e comparador ">"
    //*** Retorna TRUE se Data1 < Data2 e comparador "<"
    //*** Retorna TRUE se Data1 = Data2 e comparador "="
    public static boolean comparaData(String Data1, String comparador, String Data2) throws Excecoes {
        boolean doRetorno=false;

        if (JavaUtil.doValida(Data1) && JavaUtil.doValida(Data2)) {
	        if (!JavaUtil.doValida(comparador))
	            throw new Mensagens("Comparador >,<,= n�o informado!");

	        Calendar DT_Data1 = Calendar.getInstance();
	        Calendar DT_Data2 = Calendar.getInstance();
	        try {
	            DT_Data1.setTime(new SimpleDateFormat(SHORT_DATE).parse(Data1));
	            DT_Data2.setTime(new SimpleDateFormat(SHORT_DATE).parse(Data2));
	            if (">".equals(comparador))
	            	return DT_Data1.after(DT_Data2);
	            else if ("<".equals(comparador))
	            	return DT_Data1.before(DT_Data2);
	            else
	            	return DT_Data1.equals(DT_Data2);
	        } catch(ParseException e) {
	            throw new Excecoes(e.getMessage());
	        }
        }
        return doRetorno;
    }


    /**
     * Dia da Semana Por Extenso
     * @param strData - Data em formato DD/MM/AAAA
     * @return dia por extenso
     */
    public static boolean isSegunda(String strData) throws Excecoes {
      boolean dia_ok=false;
      //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7
      if (getDiaSemanaNumero(strData)==2) {
    	  dia_ok=true;
      }
      return dia_ok;
    }
    public static boolean isTerca(String strData) throws Excecoes {
      boolean dia_ok=false;
      //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7
      if (getDiaSemanaNumero(strData)==3) {
    	  dia_ok=true;
      }
      return dia_ok;
    }
    public static boolean isQuarta(String strData) throws Excecoes {
      boolean dia_ok=false;
      //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7
      if (getDiaSemanaNumero(strData)==4) {
    	  dia_ok=true;
      }
      return dia_ok;
    }
    public static boolean isQuinta(String strData) throws Excecoes {
      boolean dia_ok=false;
      //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7
      if (getDiaSemanaNumero(strData)==5) {
    	  dia_ok=true;
      }
      return dia_ok;
    }
    public static boolean isSexta(String strData) throws Excecoes {
      boolean dia_ok=false;
      //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7
      if (getDiaSemanaNumero(strData)==6) {
    	  dia_ok=true;
      }
      return dia_ok;
    }

    public static boolean isSabado(String strData) throws Excecoes {
      boolean dia_ok=false;

      //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7
      if (getDiaSemanaNumero(strData)==7) {
    	  dia_ok=true;
      }
      return dia_ok;
    }
    public static boolean isDomingo(String strData) throws Excecoes {
      boolean dia_ok=false;
      //// System.out.println("� Domingo:"+getDiaSemana(strData));

      //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7
      if (getDiaSemanaNumero(strData)==1) {
    	  dia_ok=true;
      }
      return dia_ok;
    }

    /**
     * Dia da Semana Por Extenso
     * @param strData - Data em formato DD/MM/AAAA
     * @return dia por extenso
     */
    public static String getDiaSemana(String strData) throws Excecoes {
        if (!JavaUtil.doValida(strData)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return "";
        }
        DateFormat dataEntrada = new SimpleDateFormat(SHORT_DATE);
        DateFormat dataSaida = new SimpleDateFormat("EEEE");
        Calendar cal = Calendar.getInstance();

        /*
            Montag = SEGUNDA
            Dienstag = TERCA
            Mittwoch = QUARTA
            Donnerstag = QUINTA
            Freitag = SEXTA
            Samstag = SABADO
            Sonntag = DOMINGO
         */

        try {
            Date data = dataEntrada.parse(strData);
            cal.setTime(data);
            //int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
            //// System.out.println("O dia " + strData + " cai num(a) " + dataSaida.format(data));
            //if (diaSemana >= Calendar.MONDAY && diaSemana <= Calendar.FRIDAY)
            //    // System.out.println("� um dia �til");
            //else // System.out.println("� no final de semana");
            return dataSaida.format(data).trim();
        } catch (ParseException e) {
            throw new Excecoes("Data inv�lida: " + strData, "com.master.util.Data", "getDiaSemana()");
        }
    }

    /**
     * Data maxima do Mes
     * @param strData - Data em formato DD/MM/AAAA
     * @return seta ultima data v�lida para o mes passado, caso data passada seja superior a ultima data valida do mes
     */
    public static String getDataMaximaNoMes(String strData) throws Excecoes {
        if (!JavaUtil.doValida(strData)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return "";
        }
        int dia = Integer.parseInt(strData.substring(0, 2));
        // System.out.println("data "+strData);
        // System.out.println("dia "+dia);
        int mes = Integer.parseInt(strData.substring(3, 5));
        // System.out.println("mes "+mes);
        int ano = Integer.parseInt(strData.substring(6, 10));
        mes--;

        try {
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(new SimpleDateFormat(SHORT_DATE).parse(strData));

            int monthFound = cal.get(Calendar.MONTH);
            int yearFound = cal.get(Calendar.YEAR);

            if (yearFound > ano)
                cal.set(Calendar.YEAR, ano);
            if (monthFound > mes)
                cal.set(Calendar.MONTH, mes);

            int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (dayMax < dia)
                cal.set(Calendar.DAY_OF_MONTH, dayMax);

            return new SimpleDateFormat(SHORT_DATE).format(cal.getTime());
        } catch (ParseException e) {
            throw new Excecoes("Data inv�lida: "+strData, "com.master.util.Data", "getDataMaximaNoMes()");
        }
    }
    /**
     * Ultimo dia do Mes
     * @param strData - Data em formato DD/MM/AAAA
     * @return seta ultima data v�lida para o mes passado, caso data passada seja superior a ultima data valida do mes
     */
    public static String getUltimoDiaDoMes(String strData) throws Excecoes {
        if (!JavaUtil.doValida(strData)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return "";
        }
        int dia = Integer.parseInt(strData.substring(0, 2));
        int mes = Integer.parseInt(strData.substring(3, 5));
        int ano = Integer.parseInt(strData.substring(6, 10));
        mes--;

        try {
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(new SimpleDateFormat(SHORT_DATE).parse(strData));

            int monthFound = cal.get(Calendar.MONTH);
            int yearFound = cal.get(Calendar.YEAR);

            if (yearFound > ano)
                cal.set(Calendar.YEAR, ano);
            if (monthFound > mes)
                cal.set(Calendar.MONTH, mes);

            int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //if (dayMax < dia)
                cal.set(Calendar.DAY_OF_MONTH, dayMax);

            return new SimpleDateFormat(SHORT_DATE).format(cal.getTime());
        } catch (ParseException e) {
            throw new Excecoes("Data inv�lida: "+strData, "com.master.util.Data", "getDataMaximaNoMes()");
        }
    }

    /**
     * Manupula dias
     * @param strData - Data em formato DD/MM/AAAA
     * @param dias - positivo p/ mais e negativo p/ menos
     * @return data com menos/mais diferen�a de dias
     */
    public static String manipulaDias(String strData, int dias) throws Excecoes {

    	if (!JavaUtil.doValida(strData)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return "";
        }

        try {
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(new SimpleDateFormat(SHORT_DATE).parse(strData));

            cal.add(Calendar.DAY_OF_MONTH, dias);
            return new SimpleDateFormat(SHORT_DATE).format(cal.getTime());
        } catch (ParseException e) {
            throw new Excecoes("Data inv�lida: "+strData, "com.master.util.Data", "manipulaDias()");
        }
    }

    /**
     * Data p/ formato de EDIs de Bancos, e arquivos textos exportados em geral
     * @param data - Data em formato DD/MM/AAAA
     * @return data no formatoAAAAMMDD
     */
    public static String getDataAAAAMMDD(String data) {
        if (data.indexOf('/') != -1) {
            data = data.replaceAll("/", "");
            data = data.substring(4, data.length()) + data.substring(2, 4) + data.substring(0,2);
        } else if (data.indexOf('-') != -1) {
            data = data.replaceAll("-", "");
        }
        return data;
    }

    public static void main(String[] args) {
        try {
            //// System.out.println(diferencaDias("30/10/2005", "30/10/2005"));

            // System.out.println(manipulaDias("19/12/2005", -30));

            // System.out.println(manipulaDias("19/12/2005", -100));
            // System.out.println(manipulaDias("19/12/2005", 30));
            /*// System.out.println(getDataMaximaNoMes("31/02/2000"));
            DateFormat dataEntrada = DateFormat.getDateInstance();
            Calendar cal = Calendar.getInstance();

            Date data = dataEntrada.parse("20/02/2008");
            cal.setTime(data);

            // System.out.println(cal.get(Calendar.MONTH));
            // System.out.println(cal.get(Calendar.DAY_OF_MONTH));
            // System.out.println(cal.get(Calendar.YEAR));
            // System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Dia da Semana em n�meros 1-Domingo, 2-Segunda, 3-ter�a .... 7-s�bado
     * @param strData - Data em formato DD/MM/AAAA
     * @return n�mero do dia
     */
    public static String getSomaDiaData(String strData , int NR_Dias) throws Excecoes {
        if (!JavaUtil.doValida(strData)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return "";
        }

        String SHORT_DATE = "dd/MM/yyyy";
        DateFormat dataEntrada = new SimpleDateFormat(SHORT_DATE);

        try {
          Date data = dataEntrada.parse(strData);
          DateFormatter dateFormatter = new DateFormatter ();

          Calendar cal = Data.stringToCalendar (strData , "dd/MM/yyyy");
          int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
          cal.set (Calendar.DAY_OF_MONTH , diasAtuais + NR_Dias);

          return dateFormatter.calendarToString (cal , SHORT_DATE);
        } catch (ParseException e) {
            throw new Excecoes("Data inv�lida: " + strData, "com.master.util.Data", "getDiaSemana()");
        }
    }

    /**
     * Adiciona Numero de Meses a uma Data
     * @param strData - Data em formato DD/MM/AAAA
     * @return n�mero de Meses
     */
    public static String getSomaMesesData(String strData , int nrMeses) throws Excecoes {
        if (!JavaUtil.doValida(strData)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return "";
        }

        DateFormatter dateFormatter = new DateFormatter ();
        Calendar cal = Data.stringToCalendar(strData, SHORT_DATE);
        int mesAtual = cal.get(Calendar.MONTH);
        cal.set(Calendar.MONTH , mesAtual + nrMeses);

        return dateFormatter.calendarToString (cal , SHORT_DATE);
    }

    /**
     * Dia da Semana em n�meros 1-Domingo, 2-Segunda, 3-ter�a .... 7-s�bado
     * @param strData - Data em formato DD/MM/AAAA
     * @return n�mero do dia
     */
    public static int getDiaSemanaNumero(String strData) throws Excecoes {
        if (!JavaUtil.doValida(strData)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return 0;
        }

        String SHORT_DATE = "dd/MM/yyyy";
        DateFormat dataEntrada = new SimpleDateFormat(SHORT_DATE);

        try {
          Date data = dataEntrada.parse(strData);

          Calendar cal = Data.stringToCalendar (strData , "dd/MM/yyyy");
          //Domingo = 1, Segunda=2, ter�a=3 ... s�bado=7

            return cal.get (Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            throw new Excecoes("Data inv�lida: " + strData, "com.master.util.Data", "getDiaSemana()");
        }
    }

    public static String getMesAnterior(String data) throws Excecoes {

        calendar = stringToCalendar(data, SHORT_DATE);

        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
        calendar.set(Calendar.DAY_OF_MONTH,1);

        return new SimpleDateFormat(SHORT_DATE).format(calendar.getTime());
    }

    /**
     * Dia da Semana em n�meros 1-Domingo, 2-Segunda, 3-ter�a .... 7-s�bado
     * @param strData1 - Data em formato DD/MM/AAAA inicio
     * @param strData2 - Data em formato DD/MM/AAAA final
     * @param int dia  - Dia a ser acumulado
     * @return somatorio do dia no periodo
     */
    public static int getSomaDias(String strData1, String strData2, int dia) throws Excecoes {

    	if (!JavaUtil.doValida(strData1)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return 0;
        }

    	if (!JavaUtil.doValida(strData2)){
        	///throw new Excecoes("Deve ser fornecida uma data no formato DD/MM/AAAA", "com.master.util.Data", "getDiaSemana()");
        	return 0;
        }

        String SHORT_DATE = "dd/MM/yyyy";
        DateFormat dataEntrada = new SimpleDateFormat(SHORT_DATE);
        int toReturn = 0;

        try {
        	Calendar c = stringToCalendar(strData1, SHORT_DATE);
        	Date d2 = dataEntrada.parse(strData2);
        	while(c.getTime().getTime() <= d2.getTime()){
        		if(c.get(Calendar.DAY_OF_WEEK)==dia)
        			toReturn++;
        		c.add(Calendar.DAY_OF_YEAR, 1);
        	}
            return toReturn;

        } catch (ParseException e) {
            throw new Excecoes("Parametros inv�lidos: " + strData1 + "," + strData2 + "," + dia, "com.master.util.Data", "getSomaDias()");
        }
    }

    public static String limpaData(String data) {
        if (data.indexOf('/') != -1) {
            data = data.replaceAll("/", "");
        } else if (data.indexOf('-') != -1) {
            data = data.replaceAll("-", "");
        }
        return data;
    }

}