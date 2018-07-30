/*
 * Created on 28/06/2005
 *
 */
package com.master.rn;

import java.util.List;

import com.master.bd.Conhecimento_RCEBD;
import com.master.bd.RCEBD;
import com.master.ed.Conhecimento_RCEED;
import com.master.ed.RCEED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

/**
 * @author Tiago
 *
 */
public class RCERN extends Transacao {
    
    public RCEED inclui(RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            RCEED toReturn = new RCEBD(sql).inclui(ed);
            fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    
    public RCEED altera(RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            RCEED toReturn = new RCEBD(sql).altera(ed);
            fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    
    public void delete(RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            Conhecimento_RCEED crceED = new Conhecimento_RCEED();
            crceED.setOid_RCE(ed.getOid_RCE());
            new Conhecimento_RCEBD(sql).delete(crceED);
            new RCEBD(sql).delete(ed);
            fimTransacao(true);
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    
    public List lista(RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            return new RCEBD(sql).lista(ed);
        } finally {
            fimTransacao(false);
        }
    }

    public RCEED getByRecord(RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            return new RCEBD(sql).getByRecord(ed);
        } finally {
            fimTransacao(false);
        }
    }
    
    public RCEED getByOid(int oid) throws Excecoes {
        inicioTransacao();
        try {
            return new RCEBD(sql).getByOid(oid);
        } finally {
            fimTransacao(false);
        }
    }

    public RCEED getByNumero(int oid_Unidade, int numero) throws Excecoes {
        inicioTransacao();
        try {
            return new RCEBD(sql).getByNumero(oid_Unidade, numero);
        } finally {
            fimTransacao(false);
        }
    }
    
    public void relRCE(RCEED filtro) throws Excecoes {
        if (filtro.getOid_RCE() <= 0) {
            throw new Mensagens("Informe a RCE a imprimir!");
        }
        inicioTransacao();
        try {
            new RCEBD(sql).relRCE(filtro);
        } finally {
            fimTransacao(false);
        }
    }
}
