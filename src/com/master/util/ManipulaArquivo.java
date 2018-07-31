package com.master.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Classe para Manipulação de Arquivos de Exportação
 */
public class ManipulaArquivo {

  private StringBuffer strBuf = new StringBuffer (500);
  private String delimitador = ";";

  public ManipulaArquivo () {
  }

  public ManipulaArquivo (String delimitador) {
    this.delimitador = delimitador;
  }

  //insere um valor
  public void insereValor (String valor) {
    this.strBuf.append (SeparaEndereco.corrigeString (JavaUtil.getValueDef (valor , "")).trim ().toUpperCase () + this.delimitador);
  }

  public void insereValorSemDelimitador (String valor) {
    this.strBuf.append (SeparaEndereco.corrigeString (JavaUtil.getValueDef (valor , "")).trim ().toUpperCase ());
  }

  public void insereValorCase (String valor) {
    this.strBuf.append (SeparaEndereco.corrigeString (JavaUtil.getValueDef (valor , "")).trim () + this.delimitador);
  }

  public void insereValorDefault (String valor) {
    this.strBuf.append (valor + this.delimitador);
  }

  public void insereValor (String valor , String vldefault) {
    this.strBuf.append (SeparaEndereco.corrigeString (JavaUtil.getValueDef (valor , vldefault)).trim ().toUpperCase () + this.delimitador);
  }

  //insere a quebra
  public void insereQuebra () {
    this.strBuf = new StringBuffer (this.strBuf.substring (0 , this.strBuf.length () - 1));
    this.strBuf.append ("\n");
  }

  public void clearArquivo () {
    if (this.strBuf.length () > 0)
      this.strBuf.delete (0 , this.strBuf.length ());
  }

  //escreve no arquivo em disco
  public void escreveArquivo (String arquivo) {

    try {
      FileWriter file = new FileWriter (arquivo , true);
      file.write (this.strBuf.toString ());
      file.flush ();
      file.close ();
    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
  }

  //le um dado arquivo e retorna um buffer
  public BufferedReader leArquivo (String arquivo) {

    BufferedReader buff = null;
    try {
      FileReader file = new FileReader (arquivo);
      buff = new BufferedReader (file);

    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
    return buff;
  }

  //le um arquivo com reader linha a linha
  public LineNumberReader leLinha (String arquivo) {

    LineNumberReader line = null;
    try {
      FileReader file = new FileReader (arquivo);
      line = new LineNumberReader (file);

    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
    return line;
  }

  public String toString () {
    return this.strBuf.toString ();
  }

  public ArrayList leStringDelimitado (String linha , String delimitador , int posicao) throws Excecoes {
    ArrayList lista = new ArrayList ();
    String coluna = "";
    int i = 0;
    int tam_col = 0;
    char caracter;

    if (linha != null) {
      while (i <= linha.length () - 1) {
        caracter = linha.charAt (i);
        //// System.out.println("  I  ->> " + i+ " charac " + caracter);
        //// System.out.println("  coluna ->>  " + coluna);
        //// System.out.println("  tam_col ->> " + tam_col);

        if (!String.valueOf (caracter).equals (delimitador)) {
          //// System.out.println("  col + ->>  " + coluna);
          tam_col++;
          coluna += String.valueOf (caracter);
        }
        else {
          if (tam_col > 0) {
            lista.add (coluna);
          }
          else {
            lista.add ("  ");
          }
          tam_col = 0;
          coluna = "";
        }
        i++;
      }
    }
    return lista;
  }

  public ArrayList leStringDelimitado (String linha , String delimitador) throws Excecoes {
    ArrayList toReturn = new ArrayList ();
    try {
      StringTokenizer stk = new StringTokenizer (linha , delimitador);
      int c = stk.countTokens ();

      // System.out.println ("Count T=======>" + c);

      for (int i = 0; i < c; i++) {
        String nextToken = "999";
        if (stk.nextElement () != null && stk.nextElement ().toString ().length () > 0) {
          nextToken = stk.nextToken ();
        }
        // System.out.println ("---->" + i);
        // System.out.println ("nextToken->" + nextToken);

        toReturn.add (nextToken);
      }
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes ("erro ao ler TOKENS" , e , this.getClass ().getName () , "public ArrayList leStringDelimitado (String linha, String delimitador)");
    }
    return toReturn;
  }
}