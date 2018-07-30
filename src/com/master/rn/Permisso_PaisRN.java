/*
 * Created on 09/03/2005
 *
 */
package com.master.rn;

import java.util.ArrayList;
import java.util.List;

import com.master.bd.Permisso_PaisBD;
import com.master.ed.Permisso_PaisED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class Permisso_PaisRN extends Transacao {
    
    public Permisso_PaisED inclui(Permisso_PaisED ed) throws Excecoes {
        this.inicioTransacao();
        try {
            Permisso_PaisED toReturn = new Permisso_PaisBD(this.sql).inclui(ed);            
            this.fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void altera(Permisso_PaisED ed) throws Excecoes {
        this.inicioTransacao();
        try {
            new Permisso_PaisBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void delete(Permisso_PaisED ed) throws Excecoes {
        this.inicioTransacao();
        try {
            new Permisso_PaisBD(this.sql).delete(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public List lista(Permisso_PaisED ed) throws Excecoes {
        this.inicioTransacao();
        try {
            List toReturn = new Permisso_PaisBD(this.sql).lista(ed);
            this.fimTransacao(false);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
        
    }
    
    public Permisso_PaisED getByRecord(int oid) throws Excecoes {
        this.inicioTransacao();
        try {
            Permisso_PaisED toReturn= new Permisso_PaisBD(this.sql).getByRecord(oid);
            this.fimTransacao(false);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
        
    }
}