package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.bd.Periodo_AbertoBD;
import com.master.ed.Periodo_AbertoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Regis Steigleder
 * @serial Periodos_Abertos
 * @serialData 02/12/2005
 */
public class Periodo_AbertoRN extends Transacao {

    public Periodo_AbertoRN() {
    }
   
    public Periodo_AbertoED inclui(Periodo_AbertoED ed, HttpServletRequest request ) throws Excecoes {

        try {
        	this.inicioTransacao();
        	Periodo_AbertoED edQBR = new Periodo_AbertoBD(this.sql).getByRecord(ed);
        	if (edQBR.getOid_Periodo_Aberto()== 0) 
        	{
        		request.setAttribute("erro", "F");
                ed = new Periodo_AbertoBD(this.sql).inclui(ed);
                this.fimTransacao(true);
        	} else {
        		request.setAttribute("erro", "T");
        	}
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Periodo_AbertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Periodo_AbertoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Periodo_AbertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Periodo_AbertoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Periodo_AbertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Periodo_AbertoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public void lista(Periodo_AbertoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Periodo_AbertoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Periodo_AbertoED getByRecord(Periodo_AbertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Periodo_AbertoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(Periodo_AbertoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Periodo_AbertoED edQBR = new Periodo_AbertoBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Periodo_Aberto()== 0 ? null : edQBR);
		} finally { 
			this.fimTransacao(false);
		}
	}

	public boolean isPeriodoAberto(String dt) throws Excecoes {
        try {
            this.inicioTransacao();
            return new Periodo_AbertoBD(this.sql).isAberto(dt);
        } finally {
            this.fimTransacao(false);
        }
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}