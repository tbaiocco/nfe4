package com.master.rn;

import java.io.FileOutputStream;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.*;
import com.master.ed.*;
import com.master.rl.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.Parametro_FixoED;

public class BloquetoRN extends Transacao {

    public BloquetoRN() {
    }

    public DuplicataED geraNumeroBloqueto(DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            DuplicataED toReturn = new BloquetoBD(this.sql).geraNumeroBloqueto(ed);
            this.fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public DuplicataED excluiNumeroBloqueto(DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            DuplicataED toReturn = new BloquetoBD(this.sql).excluiNumeroBloqueto(ed);
            this.fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public byte[] imprime_Bloqueto(DuplicataPesquisaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new BloquetoBD(this.sql).imprime_Bloqueto(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public String imprime_BloquetoMatricial(DuplicataPesquisaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new BloquetoBD(this.sql).imprime_Bloqueto_Matricial(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    //para JBoleto
    public void imprime_JBoleto(DuplicataPesquisaED ed, HttpServletResponse response) throws Excecoes {

	    try {
	      this.inicioTransacao ();
	      DuplicataED ded = new DuplicataED(ed.getOid_Duplicata());
	      ded = new DuplicataBD(this.sql).getByRecord(ded);
	      // System.out.println("---- CHEGANDO ----");
	      CarteiraED carteira = new CarteiraED();
	      carteira.setOid_Carteira(ded.getOid_Carteira());
	      carteira = new CarteiraBD(this.sql).getByRecord(carteira);
	      ded.setCD_Banco(carteira.getCd_banco());
	      ed.setDM_Relatorio("BOLETO");
	      new DuplicataRelBD(this.sql).DemonstrativoCobrancaJasper(ed);
	      String arq = Parametro_FixoED.getInstancia().getPATH_RELATORIOS() + "fat" + ed.getOid_Duplicata() + ".pdf";

	      new BloquetoUtil().imprime_JBoleto(ded, response, arq);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	this.fimTransacao(false);
	    }
	    finally {
	      this.fimTransacao (false);
	    }
	  }

    protected void finalize() throws Throwable {
        if (this.sql != null) {
            this.abortaTransacao();
        }
    }
}
