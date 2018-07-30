package com.master.root;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormataDataBean {

    private static final String FORMATO_DATA_TELA = "dd/MM/yyyy";
    private static final String FORMATO_HORA_TELA = "HH:mm";
    private static final String FORMATO_DATA_BD = "yyyy-MM-dd";


    private String DT_FormataData;

    public FormataDataBean() {
        DT_FormataData = "";
    }

    public void setDT_FormataData(String DT_FormataData) {
        this.DT_FormataData = DT_FormataData;
    }

    public String getDT_FormataData(String DT_FormataData) {
        this.DT_FormataData = DT_FormataData;
        return this.getDT_FormataData();
    }

    public String getDT_FormataData() {
        if (this.DT_FormataData != null) {
            try {
                SimpleDateFormat sdf;
                SimpleDateFormat sdfb;

                //a data que volta do banco
                String sData = DT_FormataData;
                //o formato que quero
                sdf = new SimpleDateFormat(FORMATO_DATA_TELA);

                //a mascara do banco
                sdfb = new SimpleDateFormat(FORMATO_DATA_BD);

                if (sData != null) {
                    try {
                        DT_FormataData = sdf.format(sdfb.parse(sData));
                    } catch (ParseException e) {
                        DT_FormataData = sData;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return DT_FormataData;
        }
        return "";
    }

    //*** Novo Método FormatDate() - André Valadas - 26/10/2004
    public java.util.Date getFormatDate(java.util.Date date) {
        if (date != null) {
            try {
                //convertendo a data
                SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_DATA_BD);
                String sDate = formatter.format(date);
                return java.sql.Date.valueOf(sDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public String getHR_FormataData(java.util.Date HR_FormataData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_HORA_TELA);
        return dateFormat.format(HR_FormataData);
    }

    //*** André Valadas
    public static String getFormatDate(String data) {
        if (data != null) {
            try {
                return new SimpleDateFormat(FORMATO_DATA_TELA).format(new SimpleDateFormat(FORMATO_DATA_BD).parse(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}