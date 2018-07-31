package com.master.util.mail;

import java.io.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.ed.*;

/**
 * @author Andr� Valadas
 * @serialData 03/11/2005
 * @JavaBean.class Mailer
 */

public class Mailer {

  private static String pathMessage = Parametro_FixoED.getInstancia ().getAppPath () + "/errorLog";

  private static String fileNameError = "/sistema.txt";

  /**------ Monta Mesagem de Erro ------**/
  private static String getMsgError (HttpServletRequest request , Exception exc) throws Excecoes {

    UsuarioED edUser = UsuarioBean.getUsuarioCorrente (request);
    String texto = "-- ERRO DE OPERACAO --";
    texto += "\n--------------------------------------";
    texto += "\nMensagem: " + (exc instanceof Excecoes ? ( (Excecoes) exc).getMessageJs () : exc.getClass ().getName () + ": " + exc.getMessage ());
    if (exc instanceof Excecoes) {
      Excecoes ex = (Excecoes) exc;
      if (JavaUtil.doValida (ex.getClasse ())) {
        texto += "\nClasse: " + ex.getClasse ();
      }
      if (JavaUtil.doValida (ex.getMetodo ())) {
        texto += "\nM�todo: " + ex.getMetodo ();
      }
    }
    texto += "\nData: " + Data.getDataDMY () + " - " + Data.getHoraHM ();
    texto += "\n--------------------------------------";
    texto += "\nIP: " + request.getRemoteAddr ();
    texto += "\nHost: " + request.getRemoteHost ();
    if (JavaUtil.doValida (request.getRemoteUser ())) {
      texto += "\nUser: " + request.getRemoteUser ();
    }
    texto += "\nUsu�rio: " + edUser.getNm_Usuario () + " Perfil: " + edUser.getDescDM_Perfil ();
    texto += "\n*********************************************************\n\n\n";
    return texto;
  }

  /**------ Salva Mensagem(Log) Reportando Erro ------**/
  public static void saveErrorMessage (HttpServletRequest request , Exception exc) {

    //*** Valida Exce��o...
     if (!validadeException (exc)) {
       return;
     }
    try {
      if (!new File (pathMessage).exists ()) {
        FileUtil.createDir (pathMessage);
      }
      FileUtil.saveToFile (pathMessage + fileNameError , getMsgError (request , exc));
    }
    catch (Excecoes e) {
      e.printStackTrace ();
    }
    catch (IOException e) {
      e.printStackTrace ();
    }
  }

  /**------ Envia E-mail Reportando Erro ------**/
  public static void sendErrorMail (HttpServletRequest request , Exception exc , String webmaster) throws Excecoes {

    //*** Valida Exce��o...
     if (!validadeException (exc) || !JavaUtil.doValida (webmaster)) {
       return;
     }
    //*** Cria propriedades a serem usadas na sess�o.
     Properties props = new Properties ();
    Parametro_FixoED pf = Parametro_FixoED.getInstancia ();
    UsuarioED edUser = UsuarioBean.getUsuarioCorrente (request);

    //*** Define propriedades da sess�o.
     props.put ("mail.transport.protocol" , "smtp");
    props.put ("mail.smtp.host" , "correio.nalthus.com.br");

    //*** Cria sess�o.setDebug(true) � interessante pois
     // mostra os passos do envio da mensagem e o
     // recebimento da mensagem do servidor no console.
     Session session = Session.getDefaultInstance (props);
    session.setDebug (false);

    //*** Cria mensagem e seta alguns valores que constituem os seus headers.
     Message msg = new MimeMessage (session);
    try {
      msg.setRecipient (Message.RecipientType.TO , new InternetAddress ("andre@nalthus.com.br"));
      // msg.setRecipient(Message.RecipientType.CC, new InternetAddress("hacker_z@ig.com.br"));
      msg.setFrom (new InternetAddress ("Johann<johann@johann.com.br>"));
      msg.setSubject (edUser.getNm_Usuario () + " - " + pf.getNM_Empresa () + " [" + Data.getDataDMY () + "-" + Data.getHoraHM () + "]");

      msg.setSentDate (new java.util.Date ());
      msg.setText (getMsgError (request , exc));

      //*** Envia mensagem.
       Transport.send (msg);
    }
    catch (Exception e) { /* ... */
    }
  }

  /**------ Exce��es que n�o deve Capturar ------**/
  private static boolean validadeException (Exception exc) {
    return! (exc instanceof java.lang.IllegalStateException) &&
//        ! (exc instanceof org.apache.catalina.connector.ClientAbortException) &&
        ! (exc instanceof com.master.util.Mensagens);
  }

  /**
   * @author Teofilo P. Baiocco
   * @serialData 17/07/2006
   * Envia e-mail sem anexos.
   * to: enderecos separados por ";"
   */
  public static void sendMail (String subject , String to , String mensagem) throws AddressException , Exception {

    // System.out.println("sendMail 1");
    Properties mailProps = null;
    Session mailSession = null;
    Message message = null;
    String from = "dalmeida@kieling.com.br";
    to=from;

    String username="nalthus2@kieling.com.br";
    String host = "smtp.plugin.com.br";
    String senha = "master";

    host = "smtp.kieling.com.br";
    username="nalthus2@kieling.com.br";
    senha = "nalthus2";

    // System.out.println ("sendMail host->" + host);
    // System.out.println ("sendMail from->" + from);
    // System.out.println ("sendMail username->" + username);
    // System.out.println ("sendMail senha->" + senha);
    // System.out.println ("sendMail subject->" + subject);
    // System.out.println ("sendMail to->" + to);
    // System.out.println ("sendMail mensagem->" + mensagem);


    StringTokenizer srtTok = new StringTokenizer (to , ";");
    while (srtTok != null && srtTok.hasMoreTokens ()) {

      mailProps = new Properties ();

      //		*** Define propriedades da sess�o.
      mailProps.put ("mail.transport.protocol" , "smtp");
      mailProps.put ("mail.smtp.host" , host);

      mailProps.put ("mail.smtp.auth" , "false");
      mailProps.put ("mail.debug" , "true");
      mailProps.put ("mail.smtp.port" , "25");
      mailProps.put ("mail.smtp.starttls.enable" , "true");
      mailProps.put ("mail.smtp.socketFactory.port" , "25");

      //mailSession = Session.getDefaultInstance(mailProps, null);

      //Autenticador do servidor de saida
      Authenticator auth = new MyAuthenticator ();
      mailSession = Session.getInstance (mailProps , auth);
      //mailSession = Session.getDefaultInstance(mailProps);

      //As duas linhas seguintes de c�digo, colocam no
      //formato de endere�os,
      //supostamente v�lidos, de email os dados
      //passados pelos par�metros to e from.

    // System.out.println("sendMail 10");

      javax.mail.internet.InternetAddress destinatario = new javax.mail.internet.InternetAddress (srtTok.nextToken ());
      javax.mail.internet.InternetAddress remetente = new javax.mail.internet.InternetAddress (from);

    // System.out.println("sendMail 20");

      //As duas linhas de c�digo a seguir, s�o
      //respons�veis por setar os atributos e
      //propriedas necess�rias do objeto message
      //para que o email seja enviado.
      //inicializa��o do objeto Message

      message = new MimeMessage (mailSession);

      //Defini��o de quem est� enviando o email
      message.setFrom (remetente);

    // System.out.println("sendMail 21");

      //define o(s) destinat�rio(s) e qual o tipo do
      //destinat�rio.
      //os poss�veis tipos de destinat�rio: TO, CC, BCC

    // System.out.println("sendMail 31");

      message.setRecipient (Message.RecipientType.TO , destinatario);
      //defini��o do assunto do email

      message.setSubject (subject);
      //defini��o do conte�do da mesnagem e do
      //tipo da mensagem

    // System.out.println("sendMail 41");

      message.setContent (mensagem.toString () , "text/plain");

      message.setSentDate (new Date ());

      //a linha de c�digo seguinte � a respons�vel
      //pelo envio do email

    // System.out.println("sendMail 51");

      Transport transport = mailSession.getTransport ("smtp");

    // System.out.println("sendMail 58");

      transport.connect(host, username, senha);

    // System.out.println("sendMail 61");

      message.saveChanges ();
      transport.send (message);
      transport.close ();
    }

  }

  public static void sendMail (String subject , String to , String mensagem , String attachment , String attachPath) throws AddressException , Exception {

    //defini��o do mailserver

    Properties mailProps = null;
    Session mailSession = null;
    Message message = null;
    BodyPart messageBodyPart = null;
    Multipart multipart = null;
    DataSource source = null;
    String from = "sistema@nalthus.com.br";
    StringTokenizer srtTok = new StringTokenizer (to , ";");

    while (srtTok != null && srtTok.hasMoreTokens ()) {

      mailProps = new Properties ();

      //		*** Define propriedades da sess�o.
      mailProps.put ("mail.transport.protocol" , "smtp");
      mailProps.put ("mail.smtp.host" , "smtp.plugin.com.br");
      mailProps.put ("mail.smtp.auth" , "true");

      //mailSession = Session.getDefaultInstance(mailProps, null);

      //Autenticador do servidor de saida
      Authenticator auth = new MyAuthenticator ();
      mailSession = Session.getInstance (mailProps , auth);
      //mailSession = Session.getInstance(mailProps);
      //mailSession.setDebug(true);

      //As duas linhas seguintes de c�digo, colocam no
      //formato de endere�os,
      //supostamente v�lidos, de email os dados
      //passados pelos par�metros to e from.

      javax.mail.internet.InternetAddress destinatario = new javax.mail.internet.InternetAddress (srtTok.nextToken ());
      javax.mail.internet.InternetAddress remetente = new javax.mail.internet.InternetAddress (from);

      //As duas linhas de c�digo a seguir, s�o
      //respons�veis por setar os atributos e
      //propriedas necess�rias do objeto message
      //para que o email seja enviado.
      //inicializa��o do objeto Message

      message = new MimeMessage (mailSession);

      //Defini��o de quem est� enviando o email
      message.setFrom (remetente);

      //define o(s) destinat�rio(s) e qual o tipo do
      //destinat�rio.
      //os poss�veis tipos de destinat�rio: TO, CC, BCC

      message.setRecipient (Message.RecipientType.TO , destinatario);
      //defini��o do assunto do email

      message.setSubject (subject);

      //Cria o corpo multiplo da mensagem
      messageBodyPart = new MimeBodyPart ();

      //Insere o texto na mensagem
      messageBodyPart.setText (mensagem);

      multipart = new MimeMultipart ();
      multipart.addBodyPart (messageBodyPart);

      //Insere o atachado como segunda parte da mensagem
      if (!new File (attachPath + attachment).exists ()) {
        throw new Excecoes ("O arquivo de anexo n�o existe!" , "Mailer" , "public void sendMail(String subject, String to,String from, String mensagem, String attachment)");
      }
      messageBodyPart = new MimeBodyPart ();
      source = new FileDataSource (attachPath + attachment);
      messageBodyPart.setDataHandler (new DataHandler (source));
      messageBodyPart.setFileName (attachment);
      multipart.addBodyPart (messageBodyPart);

      //Insere as duas partes do corpo na mensagem
      message.setContent (multipart);

      message.setSentDate (new Date ());

      //a linha de c�digo seguinte � a respons�vel
      //pelo envio do email
      Transport transport = mailSession.getTransport ("smtp");
      transport.connect ("smtp.plugin.com.br" , "sistema@nalthus.com.br" , "master");
      message.saveChanges ();
      transport.send (message);
      transport.close ();
      //Transport.send (message);
    }

  }


  public static void sendMail (EmailED ed) throws AddressException , Exception {

	  Properties mailProps = null;
	    Session mailSession = null;
	    Message message = null;
	    BodyPart messageBodyPart = null;
	    Multipart multipart = null;
	    DataSource source = null;

	    // System.out.println ("sendMail host------->" + ed.getNM_Host());
	    // System.out.println ("sendMail from->" + ed.getNM_Email_Origem());
	    // System.out.println ("sendMail username->" + ed.getNM_Username());
	    // System.out.println ("sendMail senha->" + ed.getNM_Senha());
	    // System.out.println ("sendMail subject->" + ed.getNM_Subject());
	    // System.out.println ("sendMail to->" + ed.getNM_Email_Destino());
	    // System.out.println ("sendMail mensagem->" + ed.getTX_Mensagem());
	    // System.out.println ("sendMail path attach->" + ed.getNM_Path());
	    // System.out.println ("sendMail file->" + ed.getNM_File());
	    // System.out.println ("sendMail porta->" + ed.getNR_Porta());
	    // System.out.println ("sendMail autenticacao->" + ed.getDM_Autenticacao());

	    StringTokenizer srtTok = new StringTokenizer (ed.getNM_Email_Destino() , ";");
	    while (srtTok != null && srtTok.hasMoreTokens ()) {
	      mailProps = new Properties ();

	      String dest = srtTok.nextToken ();

//	      java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

	      //*** Define propriedades da sess�o.
	      mailProps.put ("mail.transport.protocol" , ed.getNM_Protocolo ());
	      mailProps.put ("mail.smtp.host" , ed.getNM_Host ());
	      mailProps.put ("mail.smtp.port" , ed.getNR_Porta ());
	      mailProps.put ("mail.smtp.socketFactory.port" , ed.getNR_Porta ());
//	      mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      mailProps.put("mail.smtp.socketFactory.fallback", "false");
//	      mailProps.put ("mail.smtp.starttls.enable" , "true");
//	      mailProps.put("mail.smtp.ssl", "true");
//	      mailProps.put("mail.smtp.ehlo", "true");
	      if ("S".equals (ed.getDM_Autenticacao ())) {
	        mailProps.put ("mail.smtp.auth" , "true");
	      }
	      else {
	        mailProps.put ("mail.smtp.auth" , "false");
	      }
	      mailProps.put ("mail.debug" , "false");


	      Authenticator auth = new MyAuthenticator (ed.getNM_Username(),ed.getNM_Senha());
	      mailSession = Session.getInstance (mailProps , auth);

	      javax.mail.internet.InternetAddress destinatario = new javax.mail.internet.InternetAddress (dest);
	      javax.mail.internet.InternetAddress remetente = new javax.mail.internet.InternetAddress (ed.getNM_Email_Origem());

	      message = new MimeMessage (mailSession);

	      //Defini��o de quem est� enviando o email
	      message.setFrom (remetente);

	      message.setRecipient (Message.RecipientType.TO , destinatario);
	      message.setSubject (ed.getNM_Subject()+"-");
//	      message.setText(ed.getTX_Mensagem()+" ");
	      message.setContent(ed.getTX_Mensagem(),"text/html;charset=UTF-8");

	      if((JavaUtil.doValida (ed.getNM_Path()) && JavaUtil.doValida (ed.getNM_File()))
	    		  || (JavaUtil.doValida (ed.getNM_Path2()) && JavaUtil.doValida (ed.getNM_File2()))){
	        //Cria o corpo multiplo da mensagem
	        messageBodyPart = new MimeBodyPart ();
	        messageBodyPart.setContent(ed.getTX_Mensagem(), "text/html;charset=UTF-8");
	        multipart = new MimeMultipart ();
	        multipart.addBodyPart (messageBodyPart);
//	        System.out.println("file sent: "+ed.getNM_Path () + ed.getNM_File ());
	        //Insere o atachado como segunda parte da mensagem
	        if (new File (ed.getNM_Path () + ed.getNM_File ()).exists ()) {
	          messageBodyPart = new MimeBodyPart ();
	          source = new FileDataSource (ed.getNM_Path () + ed.getNM_File ());
	          messageBodyPart.setDataHandler (new DataHandler (source));
	          messageBodyPart.setFileName (ed.getNM_File ());

	          multipart.addBodyPart (messageBodyPart);
//	          System.out.println("Adicionando conteudo/arquivo...");

	        }
	        if (new File (ed.getNM_Path2() + ed.getNM_File2()).exists ()) {
	        	BodyPart messageBodyPart2 = new MimeBodyPart ();
		        DataSource source2 = new FileDataSource (ed.getNM_Path2() + ed.getNM_File2());
		          messageBodyPart2.setDataHandler (new DataHandler (source2));
		          messageBodyPart2.setFileName (ed.getNM_File2());

		          multipart.addBodyPart (messageBodyPart2);
//		          System.out.println("Adicionando conteudo/arquivo 2...");

	        }

	      }

//	      Insere as partes do corpo na mensagem
          message.setContent (multipart);

	      message.setSentDate (new Date ());

//	       System.out.print("*********sendMail: "+dest+"");

	      Transport transport = mailSession.getTransport (ed.getNM_Protocolo());
	      transport.connect (ed.getNM_Host(), ed.getNM_Username(),ed.getNM_Senha());

	      message.saveChanges ();
	      transport.send (message);
	      transport.close ();
//	      System.out.println("... OK!");
	    }
	  }
}

class MyAuthenticator
    extends Authenticator {

  private String username = "";
  private String password = "";

  public MyAuthenticator (String user, String pass){
	  username = user;
	  password = pass;
  }

  public MyAuthenticator (){
  }

  public PasswordAuthentication getPasswordAuthentication () {

    return new PasswordAuthentication (username , password);
  }

}