package com.master.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.master.util.ed.Parametro_FixoED;

/**
 * Título: Logger
 * Descrição: Classe que gera saída no catalina ou em algum arquivo
 * Data da criação: 02/2004
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class Logger {

  private boolean status = false;
  private String arquivo = null;
  private String classe = null;
  private String metodo = null;
  private String log = null;
  ManipulaArquivo2 MA;

  public Logger( boolean st ) {
    status = st;
    try{
        String caminho = Parametro_FixoED.getInstancia().getAppPath();
        String Arqlog = caminho+"/"+Parametro_FixoED.getInstancia().getNM_Base()+"_"+Parametro_FixoED.getInstancia().getNM_Empresa()+"_"+Data.getDataDMY()+".log";
	    File f = new File(Arqlog);
	    FileOutputStream fout = new FileOutputStream(f);
	    PrintStream pst = new PrintStream(fout);
	    if(st){
		    System.setOut(pst);
		    System.setErr(pst);
		    // System.out.println( "------------------------------------------------------------------------------" );
		    // System.out.println( "CLASSE: " + getClass() );
		    // System.out.println( "LOG: " + Arqlog );
		    // System.out.println( "------------ "+ Data.getDataDMY() + " - " + Data.getHoraHM() +" ------------" );
		    // System.out.println( "------------------------------------------------------------------------------" );
	    }
    }catch (FileNotFoundException fnofex){
        //throw new Excecoes(fnofex.getMessage(), fnofex, getClass().getName(), "Logger( boolean st )");
    }
  }
  
//  public Logger( boolean st, String c ) {
//      status = st;
//      try{
//          String caminho = Parametro_FixoED.getInstancia().getAppPath();
//          String Arqlog = caminho+"/"+Parametro_FixoED.getInstancia().getNM_Base()+"_"+Parametro_FixoED.getInstancia().getNM_Empresa()+".log";
//  	    File f = new File(Arqlog);
//  	    FileOutputStream fout = new FileOutputStream(f);
//  	    PrintStream pst = new PrintStream(fout);
//  	    if(st){
//  		    System.setOut(pst);
//  		    System.setErr(pst);
//  		    // System.out.println( "------------------------------------------------------------------------------" );
//  		    // System.out.println( "CLASSE: " + c );
//  		    // System.out.println( "LOG: \n" + Arqlog );
//  		    // System.out.println( "------------ "+ Data.getDataDMY() + " - " + Data.getHoraHM() +" ------------" );
//  		    // System.out.println( "------------------------------------------------------------------------------" );
//  	    }
//      }catch (FileNotFoundException fnofex){
//          //throw new Excecoes(fnofex.getMessage(), fnofex, getClass().getName(), "Logger( boolean st )");
//      }
//    }

  public Logger( boolean st, String arq ){
    MA = new ManipulaArquivo2( "\n", arq );
    arquivo = arq;
    status = st;
  }
  
  

  public void setClass( String str ){
    classe = str;
  }

  public void setMethod( String str ){
    if( metodo == null || !metodo.equals( str ) )
      log = null;
    metodo = str;
  }

  public void log( String str ){
    if( log == null )
      log = str;
    else
      log += "\n\n" + str;
  }

  public void writeLog(){
    if( arquivo != null && status == true ){
      MA.insereQuebra();
      MA.insereValor( "------------ "+ Data.getDataDMY() + " - " + Data.getHoraHM() +" ------------" );
      MA.insereValor( "CLASSE: " + classe );
      MA.insereValor( "METODO: " + metodo );
      MA.insereValor( "LOG: \n" + log );
      MA.escreveArquivo( arquivo );
    }else if( status == true ){
      // System.out.println( "" );
      // System.out.println( "------------ "+ Data.getDataDMY() + " - " + Data.getHoraHM() +" ------------" );
      // System.out.println( "CLASSE: " + classe );
      // System.out.println( "METODO: " + metodo );
      // System.out.println( "LOG: \n" + log );
    }
  }

}
