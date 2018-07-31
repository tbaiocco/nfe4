package com.master.util;

import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviaPDF {

    public EnviaPDF() {
    }

    public void enviaBytes(HttpServletRequest req, HttpServletResponse res, byte[] b) throws Excecoes {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        try {
            // aqui come�a a rotina para mandar de volta para o browser
            // o array de bytes
            res.setContentType("application/pdf");
            baos.write(b);
            res.setContentLength(baos.size());
            baos.writeTo(res.getOutputStream());
            // fim da rotina que manda o array de bytes do
            // pdf para o browser
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "enviaBytes(HttpServletRequest req,HttpServletResponse res, byte[] b)");
        }finally{
            baos=null;
            b=null;
        }

    }

    public void enviaArquivo(HttpServletRequest req, HttpServletResponse res, String arquivo) throws Excecoes {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        try {
            java.io.FileInputStream from = new java.io.FileInputStream(arquivo);
            String caminhoPDF = System.getProperty("CaminhoPDF");
            if (caminhoPDF == null)
                throw new Exception("Propriedade de caminho do PDF n�o foi informada");

            res.setContentType("application/pdf");
            int bytes_read;

            while ((bytes_read = from.read(buffer)) != -1)
                baos.write(buffer, 0, bytes_read);
            res.setContentLength(baos.size());
            baos.writeTo(res.getOutputStream());
            from=null;
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao enviar arquivo PDF");
            excecoes.setMetodo("enviaArquivo");
            excecoes.setExc(exc);
            throw excecoes;
        }finally{
            baos=null;
            buffer=null;
            arquivo=null;
        }
    }

    public void enviaArquivo(HttpServletResponse res, String arquivo) throws Excecoes {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        try {
            java.io.FileInputStream from = new java.io.FileInputStream(arquivo);

            res.setContentType("application/pdf");
            res.setHeader("Content-Disposition:","inline; filename="+arquivo);
            int bytes_read;

            while ((bytes_read = from.read(buffer)) != -1)
                baos.write(buffer, 0, bytes_read);
            res.setContentLength(baos.size());
            baos.writeTo(res.getOutputStream());
            from=null;
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao enviar arquivo PDF");
            excecoes.setMetodo("enviaArquivo");
            excecoes.setExc(exc);
            throw excecoes;
        }finally{
            baos=null;
            buffer=null;
            arquivo=null;
        }

    }
    
}