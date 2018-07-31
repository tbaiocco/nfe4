package com.master.util.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class PrintString {

    public PrintString() {
    }

    public synchronized static boolean print(StringBuffer print) {
        Properties prop = new Properties();
        try {
            // System.out.println(">>>>>> IMPRIMINDO ..... " + print.toString() + " <<<<<< ");
            InputStream fis = new PrintString().getClass().getResourceAsStream("print.properties");
            prop.load(fis);
            String porta = prop.getProperty("porta", "lpt1");
            String commando = prop.getProperty("commando", "/usr/local/lprng/bin/lpr -Pzebra ");
            fis.close();

            FileOutputStream fos = new FileOutputStream(new File(porta));

            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(fos, "850"));
            buffer.write(print.toString());

            buffer.close();
            fos.close();
            // System.out.println(commando + " " + porta);
            Process process = Runtime.getRuntime().exec(commando + " " + porta);
            try {
                process.waitFor();
            } catch (InterruptedException e) {
            }
            return true;
        } catch (FileNotFoundException e) {
            // System.out.println(e.toString());
            // System.out.println("Verifique se o arquivo \"print.properties\" esta configurado corretamente.");
            return false;
        } catch (IOException e) {
            // System.out.println(e.toString());
            // System.out.println("Verifique se o arquivo \"print.properties\" esta configurado corretamente.");
            return false;
        }
    }

    public static void main(String a[]) {
        PrintString.print(new StringBuffer("NU"));
    }
}