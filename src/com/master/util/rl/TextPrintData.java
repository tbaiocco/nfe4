/*
 * Created on 12/05/2005
 *
 */
package com.master.util.rl;

import com.master.util.FormataValor;

/**
 * @author Tiago
 */
public class TextPrintData {
    private int linha;
    private int coluna;
    private int length;
    private boolean trunc;
    private String dado;
    
    public int getPosicao() {
        //// System.out.println("getPosicao(): " + dado);
        int toReturn = Integer.parseInt(String.valueOf(getLinha()) + FormataValor.fillLeft(getColuna(), "0", 3));
        return toReturn;
    }
    public int getColuna() {
        return this.coluna;
    }
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    public String getDado() {
        return this.dado;
    }
    public void setDado(String dado) {
        this.dado = dado;
    }
    public int getLinha() {
        return this.linha;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
    public int getLength() {
        return this.length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean isTrunc() {
        return this.trunc;
    }
    public void setTrunc(boolean trunc) {
        this.trunc = trunc;
    }
}