package com.master.util.print.properties;

/**
 * @author André Valadas
 */

//*** Variaveis de Alinhamento para a Classe "TextPrint"
public class Alinhar {
    
    private int value;
    public final static int LEFT = 1;
    public final static int RIGTH = 2;
    public final static int CENTER = 3;
    
    protected Alinhar(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
