/*
 * Created on 12/05/2005
 *
 */
package com.master.util.rl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.print.TextPrint;

/**
 * @author Tiago
 *
 */
public class TextPrintManager {
    private HashMap map = new HashMap();

    public void addVariable(String variable, String value) {
        map.put(variable, JavaUtil.getStringNotNull(value, ""));
    }

    private List parseVariables(Properties properties)
    throws Excecoes {
        List toReturn = new ArrayList();
        Enumeration enumerat = properties.keys();
        while (enumerat.hasMoreElements()) {
            String key = (String)enumerat.nextElement();
            if (key.indexOf("CONF_") == -1) {
	            String value = properties.getProperty(key);
	            if (value != null) {
	                TextPrintData data = getDataLinhaColuna(value);
	                String valor = (String)map.get(key);
	                if (valor != null) {
	                	if (data.getLength() > 0 && !data.isTrunc()) {
	                		int length = valor.length();
	                		int currentIndex = 0;
	                		int line = 0;
	                		while (length > 0) {
	                			TextPrintData dataNew = getDataLinhaColuna(value);
	                			dataNew.setLinha(dataNew.getLinha() + line);
	                			dataNew.setDado(valor.substring(currentIndex, (dataNew.getLength() < length) ? (currentIndex + dataNew.getLength()) : (currentIndex + length)));
	                			length -= dataNew.getLength();
	                			currentIndex += dataNew.getLength();
	                			toReturn.add(dataNew);
	                			line++;
	                		}
	                	} else {
	                        data.setDado(valor);
	                		toReturn.add(data);
	                	}
	                }
	            }
            }
        }
        return toReturn;
    }

    private TextPrintData getDataLinhaColuna(String value)
    throws Excecoes {
        TextPrintData toReturn = new TextPrintData();
        int linha;
        int coluna;
        int length;
        boolean trunc;
        String[] valores = value.split(",");
        linha = Integer.parseInt(valores[0]);
        coluna = Integer.parseInt(valores[1]);
        length = Integer.parseInt(valores[2]);
        trunc = Boolean.valueOf(valores[3]).booleanValue();

        toReturn.setLinha(linha);
        toReturn.setColuna(coluna);
        toReturn.setLength(length);
        toReturn.setTrunc(trunc);
        return toReturn;
    }

    public String parse(Properties layout, String arquivoOutput) throws Excecoes {

        List lista = parseVariables(layout);

		try {
	        Collections.sort(lista, new Comparator() {
	                public int compare(Object o1, Object o2) {
	                    TextPrintData data1 = (TextPrintData)o1;
	                    TextPrintData data2 = (TextPrintData)o2;
	                    if (data1.getPosicao() < data2.getPosicao()) {
	                        return -1;
	                    } else if (data1.getPosicao() > data2.getPosicao()) {
	                        return 1;
	                    } else return 0;
	                }
	        	}
	        );
		} catch (Exception shit){
		    shit.printStackTrace();
		    throw new Excecoes(shit.getMessage(), getClass().getName(), "parse(Properties layout)");
		}

        TextPrint text = new TextPrint(false);
        String ESC = String.valueOf((char)27);

        /** COMPRIMIR **/
        boolean comprimir = Boolean.valueOf(layout.getProperty("CONF_COMPRIMIR", "false")).booleanValue();
        if (comprimir) {
            text.setFontSizeCOMPRIMIDO();
        }
        /** ESPACAMENTO DE LINHA 1/8 **/
        boolean espacamento_8 = Boolean.valueOf(layout.getProperty("CONF_ESPACAMENTO_8", "false")).booleanValue();
        if (espacamento_8){
            text.write(String.valueOf(ESC+"0"));
        }
        /** ESPACAMENTO DE LINHA 1/6 **/
        boolean espacamento_6 = Boolean.valueOf(layout.getProperty("CONF_ESPACAMENTO_6", "false")).booleanValue();
        if (espacamento_6){
            text.write(String.valueOf(ESC+"2"));
        }

        boolean condensado = Boolean.valueOf(layout.getProperty("CONF_CONDENSADO", "false")).booleanValue();
        if (condensado) {
            text.setCondesado();
	    }

        boolean draft = Boolean.valueOf(layout.getProperty("CONF_DRAFT", "false")).booleanValue();
        if (draft) {
            text.setDraft();
        }

        int linhaAnterior = 1;
        Iterator iterator = lista.iterator();

        while (iterator.hasNext()) {
            TextPrintData data = (TextPrintData)iterator.next();
            if (data.getLinha() > 0 && data.getColuna() > 0) {
	            if (linhaAnterior < data.getLinha()) {
	                text.nextLine(data.getLinha() - linhaAnterior);
	                linhaAnterior = data.getLinha();
	            }
	            if (data.isTrunc()) {
	                if (data.getLength() > 0) {
//	                	if(data.getDado().length() > data.getLength()){
//	                		text.writeWrap(data.getColuna(), data.getDado(), data.getLength());
//	                	} else {
	                		text.write(data.getColuna(), data.getDado(), data.getLength());
//	                	}
	                } else {
	                	text.write(data.getColuna(), data.getDado());
	                }
	            } else {
	                text.writeWrap(data.getColuna(), data.getDado(), data.getLength());
	            }
        	}
        }

        int linhasPular = Integer.parseInt(layout.getProperty("CONF_LINHAS_PULAR", "0"));
        for (int i = 0; i < linhasPular; i++) {
        	text.nextLine();
        }

        try {
        	String arquivoPathOutput = Parametro_FixoED.getInstancia().getRelatoriosMatricialOutput() + "/" + arquivoOutput;
            File file = new File(arquivoPathOutput);
            if (file.exists()) {
                file.delete();
            }

            text.saveToFile(arquivoPathOutput);

        } catch (Excecoes e) {
            throw new Excecoes(e.getMessage(), getClass().getName(), "parse(Properties layout)");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Excecoes(e.getMessage(), getClass().getName(), "parse(Properties layout)");
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro de runtime", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Excecoes(e.getMessage(), getClass().getName(), "parse(Properties layout)");
        }
        return "relatoriosmatricial/output/" + arquivoOutput;
    }

    public String parseString(Properties layout, String arquivoOutput) throws Excecoes {
        List lista = parseVariables(layout);
        Collections.sort(lista, new Comparator() {
                public int compare(Object o1, Object o2) {
                    TextPrintData data1 = (TextPrintData)o1;
                    TextPrintData data2 = (TextPrintData)o2;
                    if (data1.getPosicao() < data2.getPosicao()) {
                        return -1;
                    } else if (data1.getPosicao() > data2.getPosicao()) {
                        return 1;
                    } else return 0;
                }
            }
        );
        TextPrint text = new TextPrint(false);
        String ESC = String.valueOf((char)27);

        /** COMPRIMIR **/
        boolean comprimir = Boolean.valueOf(layout.getProperty("CONF_COMPRIMIR", "false")).booleanValue();
        if (comprimir)
            text.setFontSizeCOMPRIMIDO();

        /** ESPACAMENTO DE LINHA 1/8 **/
        boolean espacamento_8 = Boolean.valueOf(layout.getProperty("CONF_ESPACAMENTO_8", "false")).booleanValue();
        if (espacamento_8)
            text.write(String.valueOf(ESC+"0"));

        boolean condensado = Boolean.valueOf(layout.getProperty("CONF_CONDENSADO", "false")).booleanValue();
        if (condensado)
            text.setCondesado();

        boolean draft = Boolean.valueOf(layout.getProperty("CONF_DRAFT", "false")).booleanValue();
        if (draft)
            text.setDraft();

        int linhaAnterior = 0;
        Iterator iterator = lista.iterator();
        while (iterator.hasNext()) {
            TextPrintData data = (TextPrintData)iterator.next();
            if (data.getLinha() > 0 && data.getColuna() > 0)
            {
	            if (linhaAnterior == 0) {
                	linhaAnterior = data.getLinha();
                }
                if (linhaAnterior < data.getLinha()) {
                    text.nextLine(data.getLinha() - linhaAnterior);
                    linhaAnterior = data.getLinha();
                }
                if (data.isTrunc()) {
                    if (data.getLength() > 0) {
                        text.write(data.getColuna(), data.getDado(), data.getLength());
                    } else text.write(data.getColuna(), data.getDado());
                } else {
                    text.writeWrap(data.getColuna(), data.getDado(), data.getLength());
                }
            }
        }
        int linhasPular = Integer.parseInt(layout.getProperty("CONF_LINHAS_PULAR", "0"));
        for (int i = 0; i < linhasPular; i++) {
            text.nextLine();
        }
        return text.getString().toString();
    }
}
