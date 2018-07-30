
package com.master.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.master.util.ed.Parametro_FixoED;

/**
 * @author André Valadas
 */
/**
 * @author SPMTI
 *
 */
public class FileUtil {

    public List listaArquivos(String path) throws Excecoes {
        List toReturn = new ArrayList();
        File file = new File(path);
        if (file.isDirectory()) {
            if (file.exists()) {
                if (file.canRead()) {
                    File[] files = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                            toReturn.add(files[i].getName());
                        }
                    }
                    Collections.sort(toReturn, new Comparator() {
    		            public int compare(Object o1, Object o2) {
    		                String s1 = (String)o1;
    		                String s2 = (String)o2;
    		                return (s1.compareTo(s2));
    		            }
    		        });
                    return toReturn;
                } else throw new Mensagens("Caminho informado não pode ser lido! Verifique as permissões!");
            } else throw new Mensagens("Caminho informado não existe!");
        } else throw new Mensagens("Caminho informado não é um diretório!");
    }

    /**
     * Compacta Arquivos para formato ZIP
     * @param fileNames - Arquivos para compactação
     * @param outFilename - Arquivo ZIP a ser gerado
     * @param pathDestino - PathDestino opcional
     */
    public static void fileToZIP(String[] fileNames, String outFilename, String pathDestino) throws IOException {
        // Exemplo de arquivos para incluir no ZIP
        //String[] fileNames = new String[]{"filename1", "filename2"};

        byte[] buf = new byte[1024];
        try {
            //*** Cria o arquivo ZIP
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));

            //*** Comprime files(arquivos)
            for (int i=0; i<fileNames.length; i++)
            {
                FileInputStream in = new FileInputStream(fileNames[i]);
                //*** Adiciona arquivo para entrada do ZIP
                out.putNextEntry(new ZipEntry(fileNames[i]));

                //*** Transfere bytes do arquivo para o ZIP
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                //*** Fecha entrada
                out.closeEntry();
                in.close();
            }
            //*** Arquivo ZIP completo
            out.close();

            //*** Se deve mudar de diretório
            if (JavaUtil.doValida(pathDestino) && new File(pathDestino).exists())
            {
                if (!pathDestino.endsWith(File.separator))
                    pathDestino += File.separator;
                moveFile(outFilename, pathDestino);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Descompacta Arquivos ZIP
     * @param fileName - Arquivo zipado para descompactação
     * @param pathDestino - PathDestino opcional
     * @return File(arquivo) com extensão .txt
     */
    public static File fileFromZIP(String fileName, String pathDestino) throws IOException {

        try {
            //*** Abre o arquivo Zipado
            ZipInputStream in = new ZipInputStream(new FileInputStream(fileName));

            ZipEntry entry = in.getNextEntry();
            //*** Busca o próximo arquivo/diretorio
            while (entry != null && entry.isDirectory())
            {
                entry = in.getNextEntry();
            }

            //*** Abre o arquivo de Retorno
            String outFilename = "unZipedFile_"+System.currentTimeMillis()+".txt";
            OutputStream out = new FileOutputStream(outFilename);

            //*** Transfere bytes comprimidos para arquivo de saida (output file)
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            //*** Fecha o file e stream
            out.close();
            in.close();

            //*** Se deve mudar de diretório
            if (JavaUtil.doValida(pathDestino) && new File(pathDestino).exists())
            {
                if (!pathDestino.endsWith(File.separator))
                    pathDestino += File.separator;
                moveFile(outFilename, pathDestino);
                return new File(pathDestino+outFilename);
            } else return new File(outFilename);

        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Compacta Arquivos para formato GZIP
     * @param fileNames - Arquivos para compactação
     * @param outFilename - Arquivo GZIP a ser gerado
     * @param pathDestino - PathDestino opcional
     */
    public static void fileToGZIP(String[] fileNames, String outFilename, String pathDestino) throws IOException {
        // Exemplo de arquivos para incluir no GZIP
        // Ao contrario do ZIP o GZIP agrupa todos os arquivos em um unico
        //String[] fileNames = new String[]{"filename1", "filename2"};

        byte[] buf = new byte[1024];
        try {
            //*** Cria o arquivo ZIP
            GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(outFilename));

            //*** Comprime files(arquivos)
            for (int i=0; i<fileNames.length; i++)
            {
                FileInputStream in = new FileInputStream(fileNames[i]);

                //*** Transfere bytes do arquivo para o ZIP
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
            }
            //*** Arquivo ZIP completo
            out.finish();
            out.close();

            //*** Se deve mudar de diretório
            if (JavaUtil.doValida(pathDestino) && new File(pathDestino).exists())
            {
                if (!pathDestino.endsWith(File.separator))
                    pathDestino += File.separator;
                moveFile(outFilename, pathDestino);
            }
        } catch (IOException e) {
        }
    }

    /**
     * Descompacta Arquivos GZIP
     * @param fileName - Arquivo zipado para descompactação
     * @param pathDestino - PathDestino opcional
     * @return File(arquivo) com extensão .txt
     */
    public static File fileFromGZIP(String fileName, String pathDestino) throws IOException {

        try {
            //*** Abre o arquivo Zipado
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(fileName));
            //*** Abre o arquivo de Retorno
            String outFilename = "unZipedFile_"+System.currentTimeMillis()+".txt";
            OutputStream out = new FileOutputStream(outFilename);

            //*** Transfere bytes comprimidos para arquivo de saida (output file)
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            //*** Fecha o file e stream
            in.close();
            out.close();
            //*** Se deve mudar de diretório
            if (JavaUtil.doValida(pathDestino) && new File(pathDestino).exists())
            {
                if (!pathDestino.endsWith(File.separator))
                    pathDestino += File.separator;
                moveFile(outFilename, pathDestino);
                return new File(pathDestino+outFilename);
            } else return new File(outFilename);

        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Exclui Arquivo
     * @param fileName - Arquivo a ser excluído
     * @return true se excluido com sucesso
     */
    public static boolean deleteFile(String fileName) {
    	return deleteFile(new File(fileName));
    }
    public static boolean deleteFile(File file) {
        if (file.exists())
            return file.delete();
        else return false;
    }

    /**
     * Move Arquivo ou Diretório
     * @param fileName - Arquivo ou Diretório a ser movido
     * @param toDirectoryName - Diretório de destino
     * @return se movido com sucesso
     */
    public static boolean moveFile(String fileName, String toDirectoryName) throws IOException {

        //*** File (ou Diretorio) a ser movido
        File file = new File(fileName);
        //*** Diretório de destino
        File dir = new File(toDirectoryName);
        //*** Move file para novo Diretório
        return file.renameTo(new File(dir, file.getName()));
    }

    public static void copyFile(String origem, String destino) throws IOException {

    	File or = new File(origem);
    	File de = new File(destino);
    	if (de.exists()){
//            System.err.println(de.getName()+" já existe, ignorando...");
//            return;
         }
         FileInputStream fisOrigem = new FileInputStream(or);
         FileOutputStream fisDestino = new FileOutputStream(de);
         FileChannel fcOrigem = fisOrigem.getChannel();
         FileChannel fcDestino = fisDestino.getChannel();
         fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
         fisOrigem.close();
         fisDestino.close();
    }

    public List listaDiretorio(String path) throws Excecoes {
        List toReturn = new ArrayList();
        File file = new File(path);
        if (file.isDirectory()) {
            if (file.canRead()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        toReturn.add(files[i].getName());
                    }
                }
                return toReturn;
            } else throw new Mensagens("Caminho informado não pode ser lido! Verifique as permissões!");
        } else throw new Mensagens("Caminho informado não é um diretório!");
    }

    /**
     * Salva Arquivo
     * @param fileName - Arquivo a ser salvo
     * @param fileString - conteudo a ser escrino no arquivo
     * @return se movido com sucesso
     */
    public static void saveToFile(String fileName, String fileString) throws Excecoes, IOException {

        if (!JavaUtil.doValida(fileName))
            throw new Mensagens("Arquivo não informado!");
        /**-- SALVA --**/
        FileWriter file = new FileWriter(fileName, true);
        file.write(fileString);
        file.flush();
        file.close();
    }
    /**
     * Carrega Arquivo
     * @param fileName - Arquivo a ser Carregado
     * @return String existente no arquivo
     */
    public static String loadFromFile(String fileName) throws Excecoes, IOException {

        if (!JavaUtil.doValida(fileName))
            throw new Mensagens("Arquivo não informado!");
        if (!new File(fileName).exists())
            throw new Mensagens("Arquivo: "+fileName+" não encontrado!");
        if (!new File(fileName).canRead())
            throw new Mensagens("Arquivo: "+fileName+" não possui permissão para leitura!");

        System.out.println(/**-- CARREGA --**/);
        FileReader file = new FileReader(fileName);
        int character = 0;
        String strTexto = "";

        while ((character = file.read()) > 0) {
            strTexto += String.valueOf((char)character);
//System.out.print(String.valueOf((char)character));
        }
        file.close();
        return strTexto;
    }

    public void openFile(String file, String name, HttpServletResponse res) {
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            res.setContentType("application/msword");
            res.setHeader("Content-disposition",
                    "attachment; filename=" + name );
            BufferedOutputStream out = null;
            out = new BufferedOutputStream(res.getOutputStream(), 4096);

            int bytesRead = 0;
            byte bite[] = new byte[4096];
            BufferedInputStream bis = new BufferedInputStream(fileInputStream, 4096);
            while ((bytesRead = bis.read(bite, 0, bite.length)) != -1)
                out.write(bite, 0, bytesRead);

            out.flush();
            bis.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static byte[] loadFileToByteArray(String file) {
    	ByteArrayOutputStream out = new ByteArrayOutputStream(4096);

    	try {

            FileInputStream fileInputStream = new FileInputStream(file);

            int bytesRead = 0;
            byte[] bite = new byte[4096];
            BufferedInputStream bis = new BufferedInputStream(fileInputStream, 4096);
            while ((bytesRead = bis.read(bite, 0, bite.length)) != -1)
                out.write(bite, 0, bytesRead);

//            out.flush();
//            bis.close();
//            return out.toByteArray();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * Cria um Diretorio
     * @param pathDir - Caminho e diretorio a ser criado
     * @return true se excluido com sucesso
     */
    public static void createDir(String pathDir) throws Excecoes {
        File file = new File(pathDir);
        if(!file.exists())
            file.mkdir();
        else throw new Mensagens("Diretório informado já existe!");
    }
    public static void createDir(String dirName, String path) throws Excecoes {
        createDir(path + dirName);
    }

    public static void deleteDir(String pathDir) throws Excecoes {
    	File file = new File(pathDir);
        if (file.exists())
            file.delete();
        else  throw new Mensagens("Diretório informado não existe!");
    }
	public static void deleteDir(String dirName, String path) throws Excecoes {
        deleteDir(path + dirName);
	}

    public boolean deleteALLDir(String path) throws Excecoes {
        boolean dm_limpa_diretorio = false;
    	List toReturn = new ArrayList();
        File file = new File(path);
        if (file.isDirectory()) {
            if (file.exists()) {
                if (file.canRead()) {
                    File[] files = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                        	files[i].delete();
                        	dm_limpa_diretorio = true;
                        }
                    }
                } else throw new Mensagens("Caminho informado não pode ser lido! Verifique as permissões!");
            } else throw new Mensagens("Caminho informado não existe!");
        } else throw new Mensagens("Caminho informado não é um diretório!");
        return dm_limpa_diretorio;
    }


    /** Salva uma String num arquivo em forma de log.
     * @param fileName : nome do arquivo (sem path)
     * @param fileString : mensagem a ser gravada
     * @throws Excecoes
     */
    public static void saveStrToFile(String fileName, String fileString) throws Excecoes {

  	  try {
  		  fileName = Parametro_FixoED.getInstancia().getAppPath() + "/log/" + fileName;

  		  if (!JavaUtil.doValida(fileName))
  	          throw new Mensagens("Arquivo não informado!");
// System.out.println("salvando LOG");
  		  File arquivo = new File(fileName);
  		  if(!arquivo.exists()){
  			  FileWriter file = new FileWriter(arquivo, true);
  			  file.write("ARQUIVO DE LOG: " + fileName + "\r\n");
  			  file.write("CRIADO EM: "+Data.getDataDMY()+"| "+Data.getHoraHM() + "\r\n");
  			  file.write("---------------------------------------------------------------------------------------------------------------\r\n");
  			  file.write("\r\n");
  			  file.write(fileString);
  		      file.flush();
  		      file.close();
  		  } else {
  			  FileWriter file = new FileWriter(arquivo, true);
  			  file.write("---------------------------------------------------------------------------------------------------------------\r\n");
  			  file.write("LOG EM: "+Data.getDataDMY()+"| "+Data.getHoraHM() + "\r\n");
  			  file.write("---------------------------------------------------------------------------------------------------------------\r\n");
  			  file.write("\r\n");
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
    
    public static void appendToFile(String fileName, String fileString) throws Excecoes {

    	  try {
    		  if (!JavaUtil.doValida(fileName))
    	          throw new Mensagens("Arquivo não informado!");
    		  
    		  if(fileName.split("/").length <= 0)
    			  fileName = Parametro_FixoED.getInstancia().getAppPath() + "/log/" + fileName;

    		  File arquivo = new File(fileName);
    		  
    		  FileWriter file = new FileWriter(arquivo, true);
    		  file.write(Data.getDataCompleta() + ": " + fileString + "\r\n");
    		  file.write(fileString);
    		  file.flush();
    		  file.close();
    		  
  // System.out.println("LOGs salvos");
    	  } catch(Exception e){
    		  e.printStackTrace();
    		  throw new Excecoes();
    	  }

      }

    public static boolean renameLogFile(String fileName) throws Excecoes {
    	boolean toReturn = true;
    	try{
    		fileName = Parametro_FixoED.getInstancia().getAppPath() + "/log/" + fileName;
            //*** File (ou Diretorio) a ser movido
            File file = new File(fileName);
            //*** Renomeia
            toReturn = file.renameTo(new File(fileName + "_" + Data.getDataAAAAMMDD(Data.getDataDMY())));
    	} catch(Exception e){
    		e.printStackTrace();
    		throw new Excecoes();
  	  	}
    	return toReturn;
    }

    public static int contaLinhaArquivo(String arquivo) {
    	int linha = 0;
        LineNumberReader line = null;
        try {
          FileReader file = new FileReader (arquivo);
          line = new LineNumberReader (file);
          if (line.ready ()) {
        	  String NM_Registro = "";
        	  while ( (NM_Registro = line.readLine ()) != null) {
        		  linha++;
                  line.setLineNumber (linha);
        	  }
//        	  if(linha>1)
//        		  linha--;
          }
        }
        catch (Exception exc) {
          exc.printStackTrace ();
        }
        return linha;
      }

}