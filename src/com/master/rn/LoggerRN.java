package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class LoggerRN extends Transacao  {

  public LoggerRN() {
    //Loggerbd = new LoggerBD(this.sql);
  }


  public static void saveStrToFile(String fileName, String fileString) throws Excecoes, IOException {

	  try {
		  //fileName = Parametro_FixoED.getInstancia().getAppPath() + "/log/" + fileName;

		  if (!JavaUtil.doValida(fileName))
	          throw new Mensagens("Arquivo não informado!");
// System.out.println("salvando LOG");
		  File arquivo = new File(fileName);
		  if(!arquivo.exists()){
			  FileWriter file = new FileWriter(arquivo, true);
			  file.write("ARQUIVO DE LOG: " + fileName + "\r\n");
			  file.write("CRIADO EM: "+Data.getDataDMY()+"| "+getHora_com_segundos() + "\r\n");
			  file.write("---------------------------------------------------------------------------------------------------------------\r\n");
			  file.write("\r\n");
			  file.write(fileString);
		      file.flush();
		      file.close();
		  } else {
			  FileWriter file = new FileWriter(arquivo, true);
			  //file.write("---------------------------------------------------------------------------------------------------------------\r\n");
			  file.write("\r\n>>> LOG EM: "+Data.getDataDMY()+"| "+getHora_com_segundos() + "\r\n");
			  file.write("---------------------------------------------------------------------------------------------------------------\r\n");
			  //file.write("\r\n");
			  file.write(fileString);
		      file.flush();
		      file.close();
		  }
// System.out.println("LOGs salvos");
	  } catch(Exception e){
		  e.printStackTrace();
		  throw new Excecoes();
	  }

  }

  public static String getHora_com_segundos() {

      Calendar calendar = Calendar.getInstance();
      SimpleDateFormat formatter_time = new SimpleDateFormat("HH:mm:ss");
      java.util.Date currentTime_1 = calendar.getTime();
      return (formatter_time.format(currentTime_1));

  }

}