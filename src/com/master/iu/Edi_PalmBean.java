package com.master.iu;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.master.bd.EDI_PalmBD;
import com.master.bd.Lancamento_ContabilBD;
import com.master.ed.Lancamento_ContabilED;
import com.master.ed.PalmED;
import com.master.root.VendedorBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.Mensagens;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author André Valadas
 */
public class Edi_PalmBean extends BancoUtil {
    
    Parametro_FixoED pf = Parametro_FixoED.getInstancia();

    public void exportaArquivoPALM(HttpServletRequest request) throws Exception {
        
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String DT_Pedido = request.getParameter("FT_DT_Pedido");
        String DT_Pedido_Final = request.getParameter("FT_DT_Pedido_Final");
        String DT_Duplicata = request.getParameter("FT_DT_Duplicata");
        String DT_Duplicata_Final = request.getParameter("FT_DT_Duplicata_Final");
        String DT_Mensagem = request.getParameter("FT_DT_Mensagem");
        
        if (!doValida(DT_Pedido))
            throw new Mensagens("Periodo Inicial dos Pedidos não informado!");
        if (!doValida(DT_Pedido_Final))
            throw new Mensagens("Periodo Final dos Pedidos não informado!");
        if (!doValida(DT_Duplicata))
            throw new Mensagens("Periodo Inicial das Duplicatas não informado!");
        if (!doValida(DT_Duplicata_Final))
            throw new Mensagens("Periodo Final das Duplicatas não informado!");
        if (!doValida(DT_Mensagem))
            throw new Mensagens("Data de Mensagens não informado!");
        
        
        // System.out.println("### BEGIN! CARGA p/ PALM ["+Data.getHoraHM()+"]");
        List lista = doValida(CD_Vendedor) ? VendedorBean.getByCD_Vendedor1(CD_Vendedor) : VendedorBean.getAll("V','R");
        for (int i = 0; i < lista.size(); i++)
        {
            VendedorBean edVen = (VendedorBean)lista.get(i);
            //*** Somente Vendedores/Supervisores ATIVOS
            if ("A".equals(edVen.getDM_Situacao()))
            {
                // System.out.println("CARGA... Vendedor: "+edVen.getCD_Vendedor()+" ["+Data.getHoraHM()+"]");

                if ("E".equals(pf.getPalmModelo()))
                {
                	new EDI_PalmBD(this.sql, false).exportaArquivoPALM(new PalmED(edVen.getOID_Vendedor()
                                                                              ,edVen.getCD_Vendedor()
                                                                              ,DT_Pedido
                                                                              ,DT_Pedido_Final
                                                                              ,DT_Duplicata
                                                                              ,DT_Duplicata_Final
                                                                              ,DT_Mensagem));
                }else if ("E2".equals(pf.getPalmModelo()))
                {
                	new EDI_PalmBD(this.sql, false).exportaArquivoPALM_E2(new PalmED(edVen.getOID_Vendedor()
                            ,edVen.getCD_Vendedor()
                            ,DT_Pedido
                            ,DT_Pedido_Final
                            ,DT_Duplicata
                            ,DT_Duplicata_Final
                            ,DT_Mensagem));
                }
            }
        }
        // System.out.println("### END! CARGA p/ PALM ["+Data.getHoraHM()+"]");
    }
    
    public void importaArquivoPALM(String strFile, final String cdVendedor) throws Exception {
        
        if (!doValida(strFile) || !doValida(cdVendedor))
        {
            // System.out.println("File: ["+strFile+"] ou Vendedor: ["+cdVendedor+"] Inválidos!");
            return;
        }
        
        //*** Filtro para Arquivos de importação
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("r"+getValueDef(cdVendedor, ""));
            }
        };
        
        //*** Validações
        try {
            this.inicioTransacao();
            if (doValida(strFile)) {
                File dir = new File(strFile);
                if (dir.exists()) {
        	        if (dir.isDirectory()) {
        		        File[] files = dir.listFiles(filter);
        		        if (files != null) {
        		            for (int i=0; i<files.length; i++)
                            {
        		                // Pega o nome do arquivo
        		                String filename = files[i].getAbsolutePath();
        		                // System.out.println(filename+" ["+Data.getHoraHM()+"]");
                                validaFileToImport(new File(filename), filename, pf.getPalmPath()+"/recebidos/", pf.getPalmPath()+"/temp/");
                                this.fimTransacao(true);
                                this.inicioTransacao();
        		            }
        		        } else throw new Mensagens("Não foram encontrado arquivos no diretório: "+dir.getAbsolutePath()+"!");
        	        } else validaFileToImport(dir, strFile, pf.getPalmPath()+"/recebidos/", pf.getPalmPath()+"/temp/");
                } else throw new Mensagens("Arquivo: "+dir.getAbsolutePath()+" não encontrado!");
            } else throw new Mensagens("Arquivo não informado!"); 
            
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    private void validaFileToImport(File file, String strFile, String pathRecebidos, String pathTemp) throws Exception {
        
        //*** Verifica se arquivo esta ZIPADO
        if (!strFile.endsWith(".txt"))
        {
            file = FileUtil.fileFromGZIP(strFile, pathTemp);

            if ("E".equals(pf.getPalmModelo()))
            {
                new EDI_PalmBD(this.sql, false).importaArquivoPALM(file);
            }else if ("E2".equals(pf.getPalmModelo()))
            {
                new EDI_PalmBD(this.sql, false).importaArquivoPALM_E2(file);
            }
            FileUtil.deleteFile(file);
            
        } else 
            if ("E".equals(pf.getPalmModelo()))
            {
            	new EDI_PalmBD(this.sql, false).importaArquivoPALM(file);
            }else if ("E2".equals(pf.getPalmModelo()))
            {
            	new EDI_PalmBD(this.sql, false).importaArquivoPALM_E2(file);
            }
        	
        
        //*** Renomeia Arquivo
        int initLength = strFile.lastIndexOf(File.separator);
        String strNew = strFile.substring(0,initLength+9)+" "+Data.getDataDMY().replaceAll("/","-")+" "+Data.getHoraHM().replaceAll(":","_")+strFile.substring(strFile.lastIndexOf("."),strFile.length());
        File newFile = new File(strFile);
        newFile.renameTo(new File(strNew));
        
        //*** Move arquivo Original para outra Pasta
        FileUtil.moveFile(strNew, pathRecebidos);
    }
}