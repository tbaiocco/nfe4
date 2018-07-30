package com.master.util;

public class Excecoes extends Exception {

    private String mensagem;
    private Exception exc;
    private String classe;
    private String metodo;

    public Excecoes(String mensagem, String classe, String metodo) {
        this(mensagem, null, classe, metodo);
    }
    
    public Excecoes(String mensagem, Exception exc, String classe, String metodo) {
        super();
        this.mensagem = mensagem;
        this.exc = (exc == null ? this : exc);
        this.classe = classe;
        this.metodo = metodo;
    }

    public Excecoes(String mensagem) {
        this(mensagem, null, "", "");
    }

    public Excecoes() {
    }

    public Exception getExc() {
        return exc;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getClasse() {
        return classe;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public String getMessage() {
        return exc == null ? this.getMensagem() : exc.getClass().getName() + ":" + this.getMensagem();
    }

    public String getMessageJs() {
        return this.getMensagem().replace('\n', ' ').replace('"', '\'');
    }
    
    public void printStackTrace() {
        if (exc != null && !(exc instanceof Excecoes)) {
            exc.printStackTrace();
        } else {
            super.printStackTrace();
        }
    }
}