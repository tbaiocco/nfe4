package com.master.util;

import com.master.root.FormataDataBean;

public class SeparaEndereco {

  public SeparaEndereco() {
  }

  static public int inicio_complemento=0;
  public int i=0;

   static public String separaLogradouro(String endereco){
      int contador=0, i=0;
      String  temporario="", endereco_real ="";

      while (i <=50 && i < endereco.length()){
        temporario = String.valueOf(endereco.charAt(i));
         if ((!temporario.equals("/"))&&(!temporario.equals("-"))&&(!temporario.equals(","))){
             endereco_real= endereco_real + temporario;
         }
         else contador=i;

        if(contador!=0)
           i=51;
        else i++;
      }

     inicio_complemento = contador;
     preencheEspacos(endereco_real,34);
     return endereco_real;
   }

  static  public String separaNumero(String numero){

    numero=corrigeNumero(numero);

    int i = 0;
    String numero_real="", palavra = numero;
    char caracter;
    while (i <= palavra.length()-1){
        caracter = palavra.charAt(i);
        if ((caracter!='/')&&(caracter!='-')&&(caracter!='.')&&(caracter!=',')&&(caracter!=' ')){
            numero_real = numero_real + caracter;
        }
     i++;
     }
     return numero_real;
   }

  static  public double confirmaValorDouble(String valor){

    int i = 0, erro=0;
    double vl_double=0;
    String caracter="";

    if (valor !=null && valor.length()>0){
      while (i <= valor.length () - 1 && erro == 0) {
        caracter = valor.substring (i , i + 1);
        if (!caracter.equals ("0") &&
            !caracter.equals ("1") &&
            !caracter.equals ("2") &&
            !caracter.equals ("3") &&
            !caracter.equals ("4") &&
            !caracter.equals ("5") &&
            !caracter.equals ("6") &&
            !caracter.equals ("7") &&
            !caracter.equals ("8") &&
            !caracter.equals (".") &&
            !caracter.equals ("9")) {
          erro++;
        }
        i++;
      }
      if (erro == 0) {
        vl_double = new Double (valor).doubleValue ();
      }
    }
     return vl_double;
   }

  static  public long confirmaValorInteger(String valor){

    // System.out.println(" entrou " + valor);
    int i = 0, erro=0;
    int vl_int=0;
    String caracter="";

    while (i <= valor.length()-1 && erro==0) {
       caracter = valor.substring(i,i+1);
        if ( !caracter.equals("0") &&
             !caracter.equals("1") &&
             !caracter.equals("2") &&
             !caracter.equals("3") &&
             !caracter.equals("4") &&
             !caracter.equals("5") &&
             !caracter.equals("6") &&
             !caracter.equals("7") &&
             !caracter.equals("8") &&
             !caracter.equals("9")) {
             erro++;
        }
        i++;
     }
     if (erro==0) {
        vl_int=new Integer(valor).intValue();
     }
     return vl_int;
   }


  static  public long confirmaValorLong(String valor){
    int i = 0, erro=0;
    long vl_long=0;
    String caracter="";

    while (i <= valor.length()-1 && erro==0) {
       caracter = valor.substring(i,i+1);
        if ( !caracter.equals("0") &&
             !caracter.equals("1") &&
             !caracter.equals("2") &&
             !caracter.equals("3") &&
             !caracter.equals("4") &&
             !caracter.equals("5") &&
             !caracter.equals("6") &&
             !caracter.equals("7") &&
             !caracter.equals("8") &&
             !caracter.equals("9")) {
             erro++;
        }
        i++;
     }
     if (erro==0) {
        vl_long=new Long(valor).longValue();
     }
     return vl_long;
   }


  static  public double confirmaValorRetornadouble(String valor){
    int i = 0, erro=0;
    double vl_double=0;
    String caracter="";

    while (i <= valor.length()-1 && erro==0) {
       caracter = valor.substring(i,i+1);
        if ( !caracter.equals("0") &&
             !caracter.equals("1") &&
             !caracter.equals("2") &&
             !caracter.equals("3") &&
             !caracter.equals("4") &&
             !caracter.equals("5") &&
             !caracter.equals("6") &&
             !caracter.equals("7") &&
             !caracter.equals("8") &&
             !caracter.equals("9")) {
             erro++;
        }
        i++;
     }
     if (erro==0) {
        valor=valor.substring(0,valor.length()-2) + "." + valor.substring(valor.length()-2,valor.length());
        vl_double=new Double(valor).doubleValue();
     }
     return vl_double;
   }


   static public String separaComplemento(String endereco, int tamanho){
     int i=1, tam;
     String complemento="", palavra = endereco.trim();
     char caracter;
       tam = tamanho+1;
      // //// System.out.println("complemento-tam"+tam+"palavra"+palavra);
        if(tam > 0){
        //  //// System.out.println("entrou no if complemento-tam "+i);
          for(i=tam;i<=50;i++){
          // //// System.out.println("entrou no for complemento-tam"+i);
            try{
             caracter = palavra.charAt(i);
             complemento = complemento + caracter;
             }
            catch (Exception e){
             i++;
             }

     //       //// System.out.println("complemento-tam"+complemento+"i: "+i);
        }
     }
     return complemento;
   }

    static public String preencheEspacos(String nome, int tamanho){

       String palavra = "";
       int i=1, tam;

       if (nome != null && !nome.equals("null")) {
         palavra = nome;
       }
       tam = tamanho-palavra.length();
       if(tam > 0){
          while(i<=tam){
            palavra = palavra + " ";
            i++;
       }
     }
     return palavra;
    }

    static public String preencheZeros(String numero, int tamanho){
     int i=1, tam;
     String p1="", palavra = numero;
       tam = tamanho-numero.length();
        if(tam > 0){
          while(i<=tam){
            p1 = p1 + "0";
            i++;
        }
        palavra = p1 + palavra;
     }
     return palavra;
    }

   static public String quebraPalavra(String valor, int inicial, int termino){
     int i=inicial-1, tam=termino-1;
     String informacao = valor, palavra="" ;
     char pl;
     if(informacao != null){
       while(i<=tam){
         try{
          pl = informacao.charAt(i);
         }
         catch (IndexOutOfBoundsException e){
           break;
         }
         palavra = palavra + pl;
         i++;
       }
     }
    return palavra;
   }

   static public String modificaData(String data){
      String data_DDMMYYYY="";
      FormataDataBean DataFormatada = new FormataDataBean();
      DataFormatada.setDT_FormataData(data);
      try{
        data_DDMMYYYY = DataFormatada.getDT_FormataData();
      }
      catch (Exception e){
        data_DDMMYYYY = data;
      }
      return data_DDMMYYYY.substring(6,10)+data_DDMMYYYY.substring(3,5)+data_DDMMYYYY.substring(0,2);
   }

    static public String tiraAspas(String nome){

        int i = 0;
        String nome_real="", palavra = nome.trim();
        char caracter;
        while (i <= palavra.length()-1){
            caracter = palavra.charAt(i);

            if (!String.valueOf(caracter).equals("'")){
                nome_real = nome_real + caracter;
            }else nome_real=nome_real+" ";
            i++;
        }
        return nome_real;
    }

    static public String corrigeString(String nome){
      String nome_real = "";

          // System.out.println("  ajusta nome->> " + nome);

      if (nome != null) {
        String string_valida="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/-,.; ";
        int i = 0;
        int s = 0;
        String palavra = nome.trim ();
        char caracter;
        String caracter_troca = "";
        while (i <= palavra.length () - 1) {
          caracter = palavra.charAt (i);

          //// System.out.println("  I  ->> " + i+ " charac " + caracter);
          s = 0;
          while (s <= string_valida.length () - 1) {
      //  // System.out.println("  I  ->> " + i+ " charac " + caracter + "S-> " + s + " valido " + String.valueOf(string_valida.charAt(s)) );
            if (String.valueOf (caracter).equals (String.valueOf (string_valida.
                charAt (s)))) s = 999;
            s++;
          }
          if (s >= 999) nome_real = nome_real + caracter;
          else {
            caracter_troca = " ";
            if (String.valueOf (caracter).equals ("Ã")) caracter_troca = "A";
            if (String.valueOf (caracter).equals ("ã")) caracter_troca = "a";
            if (String.valueOf (caracter).equals ("á")) caracter_troca = "a";
            if (String.valueOf (caracter).equals ("Á")) caracter_troca = "A";
            if (String.valueOf (caracter).equals ("á")) caracter_troca = "a";
            if (String.valueOf (caracter).equals ("É")) caracter_troca = "E";
            if (String.valueOf (caracter).equals ("é")) caracter_troca = "e";
            if (String.valueOf (caracter).equals ("Í")) caracter_troca = "I";
            if (String.valueOf (caracter).equals ("í")) caracter_troca = "i";
            if (String.valueOf (caracter).equals ("Ó")) caracter_troca = "O";
            if (String.valueOf (caracter).equals ("ó")) caracter_troca = "o";
            if (String.valueOf (caracter).equals ("Ú")) caracter_troca = "U";
            if (String.valueOf (caracter).equals ("ú")) caracter_troca = "u";
            if (String.valueOf (caracter).equals ("Ç")) caracter_troca = "C";
            if (String.valueOf (caracter).equals ("ç")) caracter_troca = "C";
            if (String.valueOf (caracter).equals ("Ü")) caracter_troca = "U";
            if (String.valueOf (caracter).equals ("ü")) caracter_troca = "u";
            if (String.valueOf (caracter).equals ("&")) caracter_troca = "e";
            if (String.valueOf (caracter).equals ("|")) caracter_troca = ";";
            nome_real = nome_real + caracter_troca;
          }
          i++;
        }
        if (nome_real.equals ("null")) nome_real = "";
        nome_real = nome_real + "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ";

      }

     return nome_real;
     }

    static public String corrigeNumero(String numero){

        int i = 0;
        String numero_real="", palavra = numero.trim();

        if (palavra != null && !palavra.equals("null")) {

            char caracter;
            while (i <= palavra.length()-1){
                caracter = palavra.charAt(i);
                if (String.valueOf(caracter).equals("0") ||
                    String.valueOf(caracter).equals("1") ||
                    String.valueOf(caracter).equals("2") ||
                    String.valueOf(caracter).equals("3") ||
                    String.valueOf(caracter).equals("4") ||
                    String.valueOf(caracter).equals("5") ||
                    String.valueOf(caracter).equals("6") ||
                    String.valueOf(caracter).equals("7") ||
                    String.valueOf(caracter).equals("8") ||
                    String.valueOf(caracter).equals("9")) {
                    numero_real = numero_real + caracter;

                }//else i=99999;
                i++;
            }
        }
        return numero_real;
    }

  static public String suprimeEspacos(String nome){
     String nome_real="", palavra = nome;
     int i = 0;
     char caracter;

    while(i<=palavra.length()){
        caracter = palavra.charAt(i);
        if(!String.valueOf(caracter).equals(" "))
            i++;
        else break;
     }
     nome_real= quebraPalavra(palavra, 1, i);
     return nome_real;
   }

  static public String geraData(String data){
      String data_DDMMYYYY="";
      FormataDataBean DataFormatada = new FormataDataBean();
      DataFormatada.setDT_FormataData(data);
      try{
        data_DDMMYYYY = DataFormatada.getDT_FormataData();
      }
      catch (Exception e){
        data_DDMMYYYY = data;
      }
      return data_DDMMYYYY.substring(0,2)+"/"+data_DDMMYYYY.substring(3,5)+"/"+data_DDMMYYYY.substring(6,10);
   }

  public static String limpaCampo(String str) {

      String ret="";

      if (str != null) {
              char[] strChar = str.toCharArray();
              char[] novaStr = new char[strChar.length];
              int j=0;
              for (int i=0;i<strChar.length;i++) {
                      if ( (strChar[i]!='/') && (strChar[i]!='.') && (strChar[i]!='-') && (strChar[i]!=',') && (strChar[i]!=' '))	{
                              novaStr[j] = strChar[i];
                              j++;
                      }
              }
              char[] cRet = new char[j];

              System.arraycopy(novaStr,0,cRet,0,j);

              ret = new String(cRet);
      }
      return ret;
}
  
  // Formata numerico para txt tipo assim : de 1234.56 para 000123456 ou de -1234.56 para 000123456
  public static String formataNumeroParaTxt (double num,int tam, int dec) {
	  return SeparaEndereco.preencheZeros(SeparaEndereco.corrigeNumero(FormataValor.formataValorBT(num,dec)), tam);
  }
  
}

