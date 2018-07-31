package com.master.util.print;

public class ImpressoraEpson extends Impressora {

    // Codigos de controle da impressora
    private final String CONDENSADO = "\u001B\u000F";
    private final String NORMAL = "\u0012";
    private final String ALINHAMENTO_VERTICAL_18 = "\u001B\u0030";
    private final String TAMANHO_DA_PAGINA = "\u001BC\u0044";
    private final String RESET = "\u001B@";

    public ImpressoraEpson(String impressora) {
        super(impressora);
    }

    public void setCaracterCondensado() {
        print(CONDENSADO);
    }

    public void getCaracterNormal() {
        print(NORMAL);
    }

    public void setAlinhamentoVertical18() {
        print(ALINHAMENTO_VERTICAL_18);
    }    
    public void setTamanhoPagina() {
        print(TAMANHO_DA_PAGINA);
    }

    public void reset() {
        print(RESET);
    }
}
