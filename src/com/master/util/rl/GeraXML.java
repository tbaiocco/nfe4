package com.master.util.rl;

import java.io.FileWriter;
import java.io.StringWriter;

import com.master.util.ed.Parametro_FixoED;

public class GeraXML {

  /** Indica que a tag nao é de fechamento. Portanto, é uma tag de abertura. */
  public static final boolean ABRE  = false;
  /** Indica que a tag é de fechamento. */
  public static final boolean FECHA = true;

//  private static String sArq = "arq.xml";
//  private static File file = new File(sArq);

  /** Arquivo XML a ser gerado. */
  public static FileWriter arquivo;

  // DTD do xml
  private static final String dtdTipoDoc     = "xml";
  private static final String dtdVersao      = "1.0";
  private static final String dtdCodificacao = "iso-8859-1";

  /** Indica quantas indentacoes a realizar no XML. */
  private int ident = 0;
  /** Texto XML para o arquivo XML. */
  private StringWriter texto = new StringWriter();

  public GeraXML() {
  }


  //escreve o arquivo e fecha
//  public void arquivoRecebeTextoFecha() {
//
//          try {
//                  this.arquivo.write(this.texto.toString());
//                  this.arquivo.flush();
//                  this.arquivo.close();
//
//          }
//          catch (Exception e) {
//          }
//
//  }


  /**
  * Insere a tag de declaracao de tipo de documento (DTD) no textoXML.
  * @return  void
  */
  public void insereDTD() {
    try	{
          ident = 0;
    this.texto.write("<?");
          this.texto.write(dtdTipoDoc+" ");
    this.texto.write("version='"+dtdVersao+"' ");
    this.texto.write("encoding='"+dtdCodificacao+"'");
    this.texto.write("?>");
    this.texto.write(13);
    this.texto.write(10);
          }
    catch(Exception e) {
      // System.out.println(e.getMessage());
      System.exit(1);
    }
  }


  /**
  * Insere uma stat-tag ou end-tag no XML.
  * @return  void
  */
  public void insereTagXML(String elemento, boolean fechaTag) {
  	if (fechaTag) {
  		this.ident--;
  	};
  	if (this.ident < 0) {
  		this.ident = 0;
  	};
  	try	{
  		for (int i=0; i < this.ident; i++ ) {
  			this.texto.write("  ");
  		}
  		this.texto.write("<");
  		if (fechaTag) {
  			this.texto.write("/");
  		};
  		this.texto.write(elemento);
  		this.texto.write(">");
  		this.texto.write(13);
  		this.texto.write(10);
  	}
  	catch(Exception e) {
  		// System.out.println(e.getMessage());
  		System.exit(1);
  	}
  	if (!fechaTag) {
  		this.ident++;
  	};
	if ("RELATORIO".equals(elemento) && fechaTag == ABRE) {
 		insereElementoXML("URL", Parametro_FixoED.getInstancia().getUrlLocal());
	}
  }


  /**
  * Insere a stat-tag, o conteúdo e a end-tag de um elemento XML.
  * @return  void
  */
  public void insereElementoXML(String elemento, String conteudo) {
          try	{

          //trata caracteres especiais

      for (int i=0; i < this.ident; i++ ) {
      this.texto.write("  ");
      }
      this.texto.write("<");
      this.texto.write(elemento);
      this.texto.write(">");
      this.texto.write(this.trataCaracteresEspeciais(conteudo));
      this.texto.write("<");
      this.texto.write("/");
            this.texto.write(elemento);
      this.texto.write(">");
                  this.texto.write(13);
                  this.texto.write(10);
    }
    catch(Exception e) {
      // System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  public StringBuffer retornaXML(){
    return new StringBuffer(this.texto.toString());
  }


        private String trataCaracteresEspeciais(String conteudo)
                throws Exception {

                StringBuffer sb;
                String extendido;
                char[] caracteres;
                char c;
                Character oC;

                if (null != conteudo) {

                        caracteres = conteudo.toCharArray();

                        sb = new StringBuffer(caracteres.length);

                        for (int i = 0; i < caracteres.length; i++) {

                                c = caracteres[i];

                                //caracteres não utilizados
                                if (((c >=   0) && (c <=   8)) ||
                                    ((c >=  11) && (c <=  31)) ||
                                                ((c >= 127) && (c <= 159)) ||
                                                 (c >  255)) {
                                        //simplesmente ignora o caracter
                                }
                                //coloca o caracter, simplersmente
                                /*
                                c == 34 símbolo e comercial '"'
                                c == 38 símbolo e comercial '&'
                                c == 62 símbolo maior do que '>'
                                c == 60 símbolo menor do que '<'
                                c == 92 símbolo e comercial '\'
                                */
                                else if (((c >= 32) && (c <=  33)) ||
                                                                 ((c >= 35) && (c <=  37)) ||
                                                                 ((c >= 39) && (c <=  59)) ||
                                                                  (c == 61) ||
                                                                 ((c >= 63) && (c <=  91)) ||
                                                                 ((c >= 93) && (c <= 126))) {
                                        sb.append(c);
                                }
                                else {
                                        oC = new Character(c);
                                        sb.append("&#"+oC.hashCode()+";");
                                }
                        }//end for
                        conteudo = sb.toString().trim();

                }//end if

                return conteudo;
        }


}