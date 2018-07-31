package com.master.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManipulaArquivo2 {

    private StringBuffer strBuf = new StringBuffer(500);
    private String delimitador = null;
    private String fileName = null;

    public ManipulaArquivo2() {
    }

    public ManipulaArquivo2(String delimitador, String fileName) {

        this.delimitador = delimitador;
        this.fileName = fileName;

    }

    public void enviaBytes(HttpServletRequest req, HttpServletResponse res) {
        try {
            fileName = fileName + Long.toString(System.currentTimeMillis()) + ".txt";
            this.escreveArquivo(fileName);
            //      String fileNameAux = "/data/panazzolo/arquivos/" + fileName;
            String fileNameAux = "c:\\temp\\" + fileName;
            FileInputStream fileInputStream = new FileInputStream(fileNameAux);
            //      res.setContentType("text/txt");
            res.setContentType("text/html");
            //      res.setHeader("Content-Disposition", "attachment;
            // filename=/data/panazzolo/arquivos/" + fileName);
            res.setHeader("Content-Disposition", "attachment; filename=c:\\temp\\" + fileName);

            BufferedOutputStream out = null;
            out = new BufferedOutputStream(res.getOutputStream(), 4096);

            int bytesRead = 0;
            byte bite[] = new byte[4096];
            BufferedInputStream bis = new BufferedInputStream(fileInputStream, 4096);
            while ((bytesRead = bis.read(bite, 0, bite.length)) != -1)
                out.write(bite, 0, bytesRead);

            out.flush();
            bis.close();

            File file = new File(fileName);
            file.delete();
            File fileAux = new File(fileNameAux);
            fileAux.delete();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void geraArquivo(HttpServletRequest req, HttpServletResponse res, String Arquivo) {
        try {
            File filed = new File(Arquivo);
            filed.delete();

            FileWriter file = new FileWriter(Arquivo, true);
            file.write(this.strBuf.toString());
            file.flush();
            file.close();
            FileInputStream fileInputStream = new FileInputStream(Arquivo);
            String[] arquivoSplit = Arquivo.split("/");
            String arquivoAtt = arquivoSplit.length > 0 ? arquivoSplit[arquivoSplit.length - 1] : "";
            res.setContentType("text/txt");
            res.setHeader("Content-Disposition", "attachment; filename=" + arquivoAtt);

            BufferedOutputStream out = null;
            out = new BufferedOutputStream(res.getOutputStream(), 4096);

            int bytesRead = 0;
            byte bite[] = new byte[4096];
            BufferedInputStream bis = new BufferedInputStream(fileInputStream, 4096);
            while ((bytesRead = bis.read(bite, 0, bite.length)) != -1)
                out.write(bite, 0, bytesRead);

            out.flush();
            bis.close();

            filed = new File(Arquivo);
            filed.delete();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //insere um valor
    public void insereValor(String valor) {

        this.strBuf.append(valor + this.delimitador);

    }

    public void insereCampo(String valor) {

        this.strBuf.append(valor);

    }

    //insere a quebra
    public void insereQuebra() {

        //this.strBuf.append("\n");
        strBuf.append("\r\n");
    }
    public void insereQuebraLF() {

        strBuf.append("\n");
    }

    //escreve no arquivo em disco
    public void escreveArquivo(String arquivo) {

        try {
            FileWriter file = new FileWriter("c:\\temp\\" + arquivo, true);
            file.write(this.strBuf.toString());
            file.flush();
            file.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //escreve no arquivo em disco
    public void escreveArquivoPath(String pasta, String arquivo) {

      // System.out.println("ARQUIVO-> "+arquivo );
      // System.out.println("PASTA---> "+pasta );
      // System.out.println("FILE---> "+pasta+ arquivo );

        try {
            FileWriter file = new FileWriter(pasta+ arquivo, true);

      // System.out.println("buffer-> "+strBuf.toString() );

            file.write(this.strBuf.toString());
            file.flush();
            file.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    //le um dado arquivo e retorna um buffer
    public BufferedReader leArquivo(String arquivo) {

        BufferedReader buff = null;

        try {

            FileReader file = new FileReader(arquivo);
            buff = new BufferedReader(file);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return buff;
    }
}