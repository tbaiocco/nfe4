

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

package com.master.root;
import java.io.BufferedReader;
import java.util.StringTokenizer;

import com.master.util.ManipulaArquivo;

public class Arquivo {

  public Arquivo() {

    try{

      //exemplo de rotina para escrever no arquivo

	//no conbstrutor, na inicialização, passa-se
	//qual o delimitador a gerar
      ManipulaArquivo man = new ManipulaArquivo(";");
      man.insereValor("um");
      man.insereValor("dois");
      man.insereValor("tres");
      man.insereValor("quatro");
	//insere uma quebra de linha
      man.insereQuebra();

      man.insereValor("um");
      man.insereValor("dois");
      man.insereValor("tres");
      man.insereValor("quatro");
      man.insereQuebra();
		
	//manda escrever no arrquivo, pois
	//foi tudo tratado em memoria
      man.escreveArquivo("C:\\temp\\arquivo.txt");



      //exemplo de rotina para ler do arquivo
      BufferedReader buff = man.leArquivo("C:\\temp\\arquivo.txt");
		
	//Tod a esta rotina terá de ser feita para ler uma linha
	//e depois ler cada elemento
	//separado pelo delimitador
      StringTokenizer st = null;
      String a = null;
      while ((a = buff.readLine()) != null){

        st = new StringTokenizer(a, ";");
        while (st.hasMoreTokens()) {
            String item = st.nextToken();
            // //// System.out.println(item);
        }
      }

    }
    catch(Exception exc){
      exc.printStackTrace();
    }
  }

  public static void main(String args[]){

    new Arquivo();

  }

}
