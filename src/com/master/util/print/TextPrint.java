package com.master.util.print;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;

import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.print.properties.Alinhar;
import com.master.util.print.properties.Center;
import com.master.util.print.properties.Rigth;

/**
 * @author André Valadas
 * MÓDULO PARA MANIPULAÇÃO DE TEXTO PARA IMPRESSÃO EM IMPRESSORAS MATRICIAIS
 */
//*** Classe para manipulação do arquivo de impressão
public class TextPrint {

    private String porta = "";
    private int pageWidth = 0;
    private int pageHeigth = 0;
    private int margemE = 0;
    private int margemS = 0;

    private int linhaCount = 1;
    private int boldCount = 0;
    private int boldLength = 0;
    private StringBuffer linha = new StringBuffer(500);
    private StringBuffer arquivoPrint = new StringBuffer(500);

    private boolean pulaPaginaAuto;

    /** CONSTRUTORES
     * @throws Excecoes*/
    //*** Busca propriedades DEFAULT
    public TextPrint() throws Excecoes {
        this("");
    }
    public TextPrint(boolean setPropriedades) throws Excecoes {
        if (setPropriedades)
            new TextPrint("");
        else {
            this.pageWidth = 1000;
            this.pageHeigth = 1000;
        }
    }
    public TextPrint(String porta) throws Excecoes {
        super();
        Properties prop = new Properties();
        InputStream fis = this.getClass().getResourceAsStream("properties/print.properties");
        try {
            prop.load(fis);
        } catch (IOException e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "TextPrint()");
        }
        this.porta = JavaUtil.getValueDef(porta, prop.getProperty("porta", "LPT1"));
        this.pageWidth = Integer.parseInt(prop.getProperty("pageWidth", "100"));
        this.pageHeigth = Integer.parseInt(prop.getProperty("pageHeigth", "60"));
        this.margemE = Integer.parseInt(prop.getProperty("margemE", "0"));
        this.margemS = Integer.parseInt(prop.getProperty("margemS", "0"));
        setPropriedades();
    }
    //*** Carrega propriedades passadas por parâmetro
    public TextPrint(String porta, int pageWidth, int pageHeigth, int mEsquerda, int mSuperior) throws Excecoes {
        super();
        this.porta = JavaUtil.getValueDef(porta, "LPT1");
        this.pageWidth = JavaUtil.getValueDef(String.valueOf(pageWidth), 100);
        this.pageHeigth = JavaUtil.getValueDef(String.valueOf(pageHeigth), 60);
        this.margemE = JavaUtil.getValueDef(String.valueOf(mEsquerda), 0);
        this.margemS = JavaUtil.getValueDef(String.valueOf(mSuperior), 0);
        setPropriedades();
    }
    //*** Ajusta página de acordo com propriedades
    private void setPropriedades() {
    	if (linhaCount == 1)
    		resetImpressora();

        nextLine(margemS);
        if (linha.length() < margemE)
            setMargemE();
    }
    private void setMargemE() {
        while (linha.length() < margemE)
            write(" ");
    }


    /** Métodos AUXILIARES */
    //*** Valida Tamanho passado
    private int getLength(String string, int maxLength) {

        int auxLength = string.length() > maxLength ? maxLength : string.length();
        //*** Valida length() da linha com pageWidth
        if (pageWidth <= (linha.length() - boldLength)) {
            auxLength = boldCount > 0 ? 1 : 0;
        } else if (pageWidth < ((linha.length() - boldLength) + auxLength)) {
            auxLength = (Math.max(auxLength, (linha.length() - boldLength)) - Math.min(auxLength, (linha.length() - boldLength)));
        }
        return auxLength;
    }
    //*** Aplica BOLD
    private void setBOLD(String value, int length) {

        value = JavaUtil.getStringNotNull(value, "");
        boldCount++;
        int auxLength = linha.length();
        String whitSpaces = "";
        //value = value.substring(1, value.length() - 1);
        linha.insert(linha.length(), value.toCharArray(), 0, getLength(value, length));
        write("\r");

        for (int i=0; i < auxLength; i++)
            whitSpaces += " ";
        whitSpaces += value;
        boldLength += whitSpaces.length();
        linha.insert(linha.length(), whitSpaces.toCharArray(), 0, whitSpaces.length());
        //write(value, length);
    }
    //*** Ajusta Alinhamento
    private String getTextoAlinhado(Alinhar align, String value, int length) {

        int auxLength = 0;
        int auxSpaces = 0;
        String WhiteSpaces = "";
        if (align.getValue() > 1)
        {
            value = JavaUtil.getStringNotNull(value, "");
            if (value.trim().length() > length)
                value = String.copyValueOf(value.trim().toCharArray(), 0, length);
            auxLength = value.trim().length();
            auxSpaces = length - auxLength;
            if (auxSpaces < 1)
                return value;

            if (align.getValue() == Alinhar.RIGTH)
            {
                while (WhiteSpaces.length() < auxSpaces)
                    WhiteSpaces += " ";
                value = WhiteSpaces+value;

            } else if (align.getValue() == Alinhar.CENTER) {

                if (auxSpaces >= 2)
                {
                    auxSpaces = (auxSpaces / 2);
                    for (int i=0; i < auxSpaces; i++)
                        WhiteSpaces += " ";
                    value = WhiteSpaces+value+WhiteSpaces;
                }
            }
        }
        return value;
    }
    //*** Retorna Arquivo para impressão
    public StringBuffer getString() {
        nextLine();
        return arquivoPrint;
    }
    //*** Retorna Tamanho da Linha
    public int getLength() {
        return (linha.length() - margemE);
    }
    //*** Quebra de Página
    public void nextPage() {

        linhaCount = 1;
        boldCount = 0;
        boldLength = 0;
        arquivoPrint.append(linha.toString()+"\f");
        linha.delete(0, linha.length());
        setPropriedades();

    }
    //*** Quebra de linha
    public void nextLine() {

        linhaCount++;
        boldLength = 0;
        //*** Valida pageHeigth
        if (!pulaPaginaAuto || ((pageHeigth + boldCount) > linhaCount)) {
            arquivoPrint.append(linha.toString()+"\r\n");
            linha.delete(0, linha.length());
            setMargemE();
        } else nextPage();
    }
    //*** Multiplas quebras de linha
    public void nextLine(int nLinha) {
        if (nLinha > 0) {
            //*** Valida pageHeigth
            if (!pulaPaginaAuto || ((pageHeigth + boldCount) > (linhaCount + nLinha))) {
                for (int i=0; i<nLinha; i++) {
                    linhaCount++;
                    arquivoPrint.append(linha.toString()+"\r\n");
                    if (linha.length() > 0)
                        linha.delete(0, linha.length());
                }
                setMargemE();
            } else nextPage();
        }
    }
    /** CARACTERES */
    //*** Inclui Linha com Caracter passado
    public void writeCaracter(char caracter) {

        String value = "";
        for (int i=0; i<(pageWidth - margemE); i++)
            value += caracter;
        write(value);

    }
    //*** Inclui Caracter a Direita da página
    public void writeCaracterR(char caracter) {
        write(getLength()-1,String.valueOf(caracter));
    }

    /** Métodos COMUNS */
    //*** Inclui String
    public void write(String value) {

        value = JavaUtil.getStringNotNull(value, "");
        linha.insert(linha.length(), value.toCharArray(), 0, getLength(value, value.length()));

    }
    public void writeNextLine(String value) {
        write(value);
        nextLine();
    }
    public void writeNextLine(int index, String value) {
        write(index, value);
        nextLine();
    }
    public void writeNextLine(int index, String value, int length) {
        write(index, value, length);
        nextLine();
    }
    //*** Inclui String com BOLD
    public void write(String value, boolean bold) {

        if (bold == true)
            setBOLD(value, getLength(value, value.length()));
        else write(value);

    }
    //*** Inclui String com LENGTH
    public void write(String value, int length) {

        value = JavaUtil.getStringNotNull(value, "");
        linha.insert(linha.length(), value.toCharArray(), 0, getLength(value, length));

    }
    //*** Inclui String com LENGTH e BOLD
    public void write(String value, int length, boolean bold) {

        if (bold == true)
            setBOLD(value, length);
        else write(value, length);

    }

    /** Métodos com ALIGN */
    //*** Inclui String com ALIGN
    public void write(Center center, String value) {

        value = getTextoAlinhado(center, value, pageWidth - margemE);
        write(value, value.length());

    }
    //*** Inclui String com ALIGN e LENGTH
    public void write(Alinhar align, String value, int length) {

        value = getTextoAlinhado(align, value, getLength(value, pageWidth - margemE));
        write(value, length);

    }
    //*** Inclui String com ALIGN, BOLD
    public void write(Center center, String value, boolean bold) {

        value = getTextoAlinhado(center, value, pageWidth - margemE);
        write(value, value.length(), bold);

    }
    //*** Inclui String com ALIGN, LENGTH e BOLD
    public void write(Alinhar align, String value, int length, boolean bold) {

        value = getTextoAlinhado(align, value, getLength(value, length));
        write(value, length, bold);

    }
    //*** Inclui String com INDEX, ALIGN
    public void write(Center center, int index, String value) {

        value = getTextoAlinhado(center, value, pageWidth - margemE);
        write(index, value, value.length());

    }
    //*** Inclui String com INDEX, ALIGN RIGTH
    public void write(Rigth rigth, int index, String value) {

        value = JavaUtil.getStringNotNull(value, "");
        int auxIndex = index;
        if (value.trim().length() > index) {
            value = value.trim().substring(0, index);
        } else auxIndex = (index - value.trim().length());
        write(auxIndex, value.trim(), value.trim().length());
    }
    public void writeNextLine(Rigth rigth, int index, String value) {
        write(rigth, index, value);
        nextLine();
    }
    //*** Inclui String com INDEX, ALIGN e LENGTH
    public void write(Alinhar align, int index, String value, int length) {

        value = getTextoAlinhado(align, value, getLength(value, length));
        write(index, value, length);

    }
    //*** Inclui String com INDEX, ALIGN e BOLD
    public void write(Center center, int index, String value, boolean bold) {

        value = getTextoAlinhado(center, value, pageWidth - margemE);
        write(index, value, value.length(), bold);

    }
    //*** Inclui String com INDEX, ALIGN, LENGTH e BOLD
    public void write(Alinhar align, int index, String value, int length, boolean bold) {

        value = getTextoAlinhado(align, value, getLength(value, length));
        write(index, value, length, bold);

    }

    /** Métodos com INDEX */
    //*** Inclui String com INDEX
    public void write(int index, String value) {

        value = JavaUtil.getStringNotNull(value, "");
        if (linha.length() > (index + margemE)) {
            linha.delete((index + margemE),linha.length());
            index = 0;
        } else index = ((index + margemE) - linha.length());

        for (int i=0; i < index; i++)
            write(" ");
        linha.insert(linha.length(), value.toCharArray(), 0, getLength(value, value.length()));
    }
    //*** Inclui String com INDEX e BOLD
    public void write(int index, String value, boolean bold) {

        value = JavaUtil.getStringNotNull(value, "");
        if (linha.length() > (index + margemE)) {
            linha.delete((index + margemE),linha.length());
            index = 0;
        } else index = ((index + margemE) - linha.length());

        for (int i=0; i < index; i++)
            write(" ");

        if (bold == true)
            setBOLD(value, value.length());
        else linha.insert(linha.length(), value.toCharArray(), 0, getLength(value, value.length()));
    }
    //*** Inclui String com INDEX e LEGTH
    public void write(int index, String value, int length) {

        value = JavaUtil.getStringNotNull(value, "");
        if (linha.length() > (index + margemE)) {
            linha.delete((index + margemE),linha.length());
            index = 0;
        } else index = ((index + margemE) - linha.length());

        for (int i=0; i < index; i++)
            write(" ");
        linha.insert(linha.length(), value.toCharArray(), 0, getLength(value, length));
    }
    //*** Inclui String com INDEX e LEGTH e pula linha
    public void writeWrap(int index, String value, int length) {

        value = JavaUtil.getStringNotNull(value, "");
        if (linha.length() > (index + margemE)) {
            linha.delete((index + margemE),linha.length());
            index = 0;
        } else index = ((index + margemE) - linha.length());
//System.out.println("v:"+value+"|l:"+length);
        for (int i=0; i < index; i++)
            write(" ");
        int auxLength = 0;
        String toInsert = value;
        auxLength = getLength(toInsert, length);
//System.out.println("aux:"+auxLength);
        linha.insert(linha.length(), toInsert.toCharArray(), 0, auxLength);
        toInsert = toInsert.substring(auxLength);
//System.out.println("2:"+toInsert);
        if (toInsert.length() > 0) {
            nextLine();
            writeWrap(index, toInsert, length);
        }
    }
    //*** Inclui String com INDEX, LEGTH e BOLD
    public void write(int index, String value, int length, boolean bold) {

        if (bold == true)
        {
            if (linha.length() > (index + margemE)) {
                linha.delete((index + margemE),linha.length());
                index = 0;
            } else index = ((index + margemE) - linha.length());

            for (int i=0; i < index; i++)
                write(" ");
            setBOLD(value, getLength(value, length));
        } else write(index, value, length);
    }
    /** Métodos para IMPRESSÃO
     * @throws Excecoes
     * @throws IOException*/
    public void Printer(boolean defaultPrinter) throws Excecoes, IOException {

        if (linhaCount < 1)
            throw new Mensagens("Arquivo em branco!");

        if (defaultPrinter == false)
            porta = selectPrinter();
        if (!JavaUtil.doValida(porta))
            throw new Mensagens("Impressora não informada!");

        /**-- IMPRESSORA --**/
        PrintWriter printer = new PrintWriter(new FileWriter(porta));
        printer.println(this.getString());
        printer.close();
        printer.flush();
    }
    /** Printer(String fileName)
     * @throws Excecoes
     * @throws IOException*/
    public void Printer(String fileName, boolean defaultPrinter) throws Excecoes, IOException {

        if (!JavaUtil.doValida(fileName))
            throw new Excecoes("Arquivo inválido ou não informado!");
        if (!new File(fileName).exists())
            throw new Excecoes("Arquivo: "+fileName+" não encontrado!");
        if (!new File(fileName).canRead())
            throw new Excecoes("Arquivo: "+fileName+" não possui permissão para leitura!");

        if (defaultPrinter == false)
            porta = selectPrinter();
        if (!JavaUtil.doValida(porta))
            throw new Mensagens("Impressora não informada!");

        FileReader file = new FileReader(fileName);
        int character = 0;
        String texto = "";

        while ((character = file.read()) > 0) {
            char letra = (char)character;
			texto += String.valueOf(letra);
        }
        /**-- IMPRESSORA --**/
        PrintWriter printer = new PrintWriter(new FileWriter(porta));
        printer.println(texto);
        printer.close();
        printer.flush();
    }
    /** SELEÇÃO DE IMPRESSORA
     * Abre caixa de diálogo para selecionar entre as impressoras existentes.
     * @return A impressora seleciona para usar ou null em caso de cancelamento.
     */
    public String selectPrinter() {

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();

        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(MediaSizeName.ISO_A4);
        attributes.add(new Copies(1));

        defaultPrinter = ServiceUI.printDialog(null, 200, 200, printServices, defaultPrinter, null, attributes);
        return defaultPrinter.getName();
    }
    /** ARQUIVO - SALVAR
     * @throws Excecoes
     * @throws IOException*/
    public void saveToFile(String fileName) throws Excecoes, IOException {

        if (linhaCount < 1)
            throw new Mensagens("Arquivo em branco!");
        FileUtil.saveToFile(fileName, this.getString().toString());
    }

    /** ----------- TAMANHO DA FONTE ------------ **/
    static class FontSize {
        public static final String ESC = String.valueOf((char)27);
        public static final String NORMAL = String.valueOf((char)18);
        public static final String COMPRIMIDO = String.valueOf(ESC + (char)15);
        //public static final String S_COMPRIMIDO = String.valueOf((char)15 + (char)27 + "M");
        public static final String S_COMPRIMIDO = String.valueOf(ESC + (char)80 + (char)15);
    }
    public void setFontSizeNORMAL() {
        write(FontSize.NORMAL);
    }
    public void setFontSizeCOMPRIMIDO() {
        write(FontSize.COMPRIMIDO);
    }
    public void setFontSizeS_COMPRIMIDO() {
        write(FontSize.S_COMPRIMIDO);
    }
    /** ------------------------------------------- **/

    public void resetImpressora() {
    	write(String.valueOf((char)27 + "@"));
    }
    public void setCondesado() {
    	write(String.valueOf((char)15));
    }
    public void setDraft() {
        write(String.valueOf((char)27 + "&k2S"));
    }
    public boolean isPulaPaginaAuto() {
		return pulaPaginaAuto;
	}
	public void setPulaPaginaAuto(boolean pulaPaginaAuto) {
		this.pulaPaginaAuto = pulaPaginaAuto;
	}
}