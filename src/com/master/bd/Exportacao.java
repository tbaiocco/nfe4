package com.master.bd;

import java.sql.ResultSet;

import com.master.util.Excecoes;
import com.master.util.ManipulaArquivo;
import com.master.util.bd.Transacao;


/**
 * @author André Valadas
 */
public class Exportacao extends Transacao {

    public void exportaConhecimento() throws Excecoes {
        
        ManipulaArquivo arquivo = new ManipulaArquivo(" - "); // Quebra entre valores
        String extensaoArq = ".txt";
        String nomeArq = "NR_Conhecimento";
        String localExportacao = "C://"; //*** Isso em Windows, em Linux usa-se algo do tipo: /data/
        String query = "";
        try {
            this.inicioTransacao();
            query = " SELECT oid_Pessoa " +
            		" FROM Pessoas " +
            		" WHERE 1 = 1"; 
            
            ResultSet res = this.sql.executarConsulta(query);
            while (res.next()) {
                //*** Limpa Arquivo
                arquivo.clearArquivo();
                /** Valores */
                
                arquivo.insereValor(res.getString("oid_Pessoa"));
                
                
                
                

                /** ---------------------------- */
                //*** Grava Arquivo com Base no NR_Conhecimento
                arquivo.escreveArquivo(localExportacao+nomeArq+"("+(res.getString("oid_Pessoa"))+")"+extensaoArq);
            }            
                
        } catch (Exception exc) {
            throw new Excecoes (exc.getMessage(), exc, this.getClass().getName(), 
                	"exportaConhecimento()");
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public static void main(String[] args) throws Excecoes {
        new Exportacao().exportaConhecimento();
    }
}
