package com.master.util;

/**
 * Descrição: Para Alerts ao cliente(!= de Excecoes q redireciona para página de erro)
 * @author André Valadas
 * 
 * ex.:	try {
       		PedidoBean.testaErro();
       		%>
        	alert("Sem Erros!");
      		<%
      	} catch (Mensagens e) {
      		%>
      		alert("<%=e.getMessage()%>");
      		window.history.go(-1);
      		<%
      	}
 */

public class Mensagens extends Excecoes {

    private String mensagem;
    private Exception exc;
    private String classe;
    private String metodo;

    public Mensagens(String mensagem, String metodo) {
        this(mensagem, null, "", metodo);
    }
    
    public Mensagens(String mensagem, String classe, String metodo) {
        this(mensagem, null, classe, metodo);
    }
    
    public Mensagens(String mensagem, Exception exc, String classe, String metodo) {
        super();
        this.mensagem = mensagem;
        this.exc = (exc == null ? this : exc);
        this.classe = classe;
        this.metodo = metodo;
    }

    public Mensagens(String mensagem) {
        this(mensagem, null, "", "");
    }

    public Mensagens() {
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
        return this.getMensagem();
    }

    public String getMessageJs() {
        return JSUtil.processaString(getMensagem());
    }

    public void printStackTrace() {
        if (exc != null && !(exc instanceof Mensagens)) {
            exc.printStackTrace();
        } else {
            super.printStackTrace();
        }
    }
}