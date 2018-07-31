package com.master.util.rl;

/**
 * Classe para gera��o de PDF em formatos stream e arquivo
 */
public class GeraPDF {

    //variaveis a serem recuperadas via metodos GET e SET
    //Recebe as tags e valores xml
    private StringBuffer sXML;

    //XSL a ser processado com o XML
    private StringBuffer sXSL;

    //objeto array de bytes
    private byte[] arrayPDF;

    public GeraPDF() {
    }

    /**
     * Informa��es de Vers�o do Bean
     */
    public String getBeanInfo() {
        return "Vers�o 1.0";
    }

    /**
     * Inicializa o conte�do XML a ser processado
     */
    public void setXML(StringBuffer sXML) {
        this.sXML = sXML;
    }

    /**
     * Inicializa a localizacao completa do XSL a processar com o XML
     */
    public void setXSL(StringBuffer sXSL) {
        this.sXSL = sXSL;
    }

    /**
     * Retorna um array de bytes com o resultado do PDF processado
     */
    public byte[] getArrayPDF() {
        return this.arrayPDF;
    }
    
}