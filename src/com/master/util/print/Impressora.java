package com.master.util.print;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public abstract class Impressora {

    private String NEW_LINE = "\n";
    private String CARRIAGE_RETURN = "\r";
    private String FORM_FEED = "\f";

    private StringBuffer buffer = null;
    private String impressora = null;

    public Impressora(String impressora) {
        /*
         * Deve ser passado o nome da porta da impressora "/dev/lp0" no LINUX
         * "LPT1:" no WINDOWS
         */
        this.impressora = impressora;
        init();
    }

    public void setCaracterCondensado() {
    }

    public void getCaracterNormal() {
    }

    public void setAlinhamentoVertical18() {
    }

    public void setTamanhoPagina() {
    }

    public void reset() {
    }

    public void print(String s) {
        buffer.append(s);
    }

    public void println(String s) {
        buffer.append(s);
        println();
    }

    public void println() {
        buffer.append(NEW_LINE + CARRIAGE_RETURN);
    }

    public void printff() {
        buffer.append(FORM_FEED);
    }

    public void init() {
        buffer = new StringBuffer();
    }

    public void flush() {
        FileOutputStream prn = null;
        boolean impresso;

        do {
            try {
                prn = new FileOutputStream(impressora);
            } catch (FileNotFoundException fnfe) {
                prn = null;
                if (JOptionPane.showConfirmDialog(null, "Impressora nao esta pronta,\nVerifique o problema e pressione OK para tentar novamente.", "Erro de Comunicacao com a Impressora",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE) != JOptionPane.OK_OPTION) {
                    return;
                }
            }
        } while (prn == null);

        try {
            impresso = false;
            do {
                try {
                    prn.write(buffer.toString().getBytes());
                    prn.flush();
                    impresso = true;
                } catch (IOException ioe) {
                    if (JOptionPane.showConfirmDialog(null, "Impressora nao esta pronta,\nVerifique o problema e pressione OK para tentar novamente.", "Erro de Comunicacao com a Impressora",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE) != JOptionPane.OK_OPTION) {
                        return;
                    }
                }
            } while (!impresso);
        } finally {
            try {
                prn.close();
            } catch (IOException ioe) {
            }
        }
    }
}
