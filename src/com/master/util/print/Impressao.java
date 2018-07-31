package com.master.util.print;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;

/**
 * @author Andre
 */
public class Impressao {

    /**
     * Get PrintService object for a printer name. The printer name must exactly
     * match one of the available print services names (character case is
     * ignored).
     * 
     * @param printerName
     *            Printer name (e.g. "\\SDE0010\hp LaserJet 2300 PCL 6")
     * @return PrintService for the printer name or null if not found.
     */
    static public PrintService getPrintService(String printerName) {
        PrintService result = null;

        if (printerName != null) {
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null); //all
            if (printServices != null) {
                int count = printServices.length;
                for (int i = 0; i < count; i++) { //for each PrintService
                    if (printServices[i].getName().equalsIgnoreCase(printerName)) {
                        result = printServices[i];
                        break;
                    }
                }//next PrintService
            }//else: no PrintService available
        }//else: input unavailable
        return result;
    }//getPrintService()

    /**
     * Show printer dialog to select printer and adjust attributes.
     * 
     * @param defaultPrinter
     *            PrintServer to display by default.
     * @return The selected printer to use for printing or null if canceled.
     */
    static public PrintService selectPrinter(PrintService defaultPrinter) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        if (defaultPrinter == null) {
            defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();
        }

        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(MediaSizeName.ISO_A4);
        attributes.add(new Copies(1));

        return ServiceUI.printDialog(null, 200, 200, printServices, defaultPrinter, null, attributes);
    }//selectPrinter()

    /**
     * Print a document to a printer.
     * 
     * @param doc
     *            Document to print.
     * @param attributes
     *            Print attributes.
     * @param printService
     *            Optional PrintService to use (default: DefaultPrintService).
     * @throws PrintException
     *             on error.
     */
    static public void print(Doc doc, PrintRequestAttributeSet attributes, PrintService printService) throws PrintException {
        if (printService == null) {
            printService = PrintServiceLookup.lookupDefaultPrintService();
        }

        DocPrintJob docPrintJob = printService.createPrintJob();
        try {
            docPrintJob.print(doc, attributes);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }//print()

    /**
     * Print content of an input stream to the specified print service.
     * Attributes used: A4, 1 Copy
     * 
     * @param inputStream
     *            Input stream to print (e.g. new FileInputStream("Hello.ps")).
     * @param printService
     *            Optional PrintService to use (e.g.
     *            PrintServiceLookup.lookupDefaultPrintService())
     * @throws PrintException
     *             on error.
     */
    static public void print(InputStream inputStream, PrintService printService) throws PrintException {
        
        DocFlavor flavor = new DocFlavor("application/octet-stream", "java.io.ImputStream");
        Doc doc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        //Doc doc = new SimpleDoc("ANDRE VALADAS", DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(MediaSizeName.ISO_A4);
        attributes.add(new Copies(1));

        print(doc, attributes, printService);
    } //print()

    public static void main(String[] args) throws PrintException, IOException {
        InputStream stream = new FileInputStream("C:/Nota_Fiscal_SAIDA.txt");
        //String porta = "\\\\SERVER-01\\DFX";
        String impressora = "DFX-5000+";
        Doc doc = new SimpleDoc(stream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        Impressao.print(stream, selectPrinter(null));
    }

    public Impressao() {
    }
}