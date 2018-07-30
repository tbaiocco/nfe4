/*
 * Created on 29/06/2005
 *
 */
package com.master.rn;

import java.util.List;

import com.master.bd.Conhecimento_RCEBD;
import com.master.ed.Conhecimento_RCEED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

/**
 * @author Tiago
 *
 */
public class Conhecimento_RCERN extends Transacao {
    
    public Conhecimento_RCEED inclui(Conhecimento_RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            if (new Conhecimento_RCEBD(sql).exists(ed)) {
                throw new Mensagens("Este conhecimento já foi incluído nesta RCE!");
            }
            Conhecimento_RCEED toReturn = new Conhecimento_RCEBD(sql).inclui(ed);
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
    
    public Conhecimento_RCEED altera(Conhecimento_RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            Conhecimento_RCEED toReturn = new Conhecimento_RCEBD(sql).altera(ed);
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
    
    public void delete(Conhecimento_RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            new Conhecimento_RCEBD(sql).delete(ed);
            fimTransacao(true);
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    
    public List lista(Conhecimento_RCEED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            return new Conhecimento_RCEBD(sql).lista(ed);
        } finally {
            fimTransacao(false);
        }
    }

    public Conhecimento_RCEED getByOid(int oid) throws Excecoes {
        inicioTransacao();
        try {
            return new Conhecimento_RCEBD(sql).getByOid(oid);
        } finally {
            fimTransacao(false);
        }
    }
    
    public List getByOid_RCE(int oid_RCE) throws Excecoes {
        inicioTransacao();
        try {
            return new Conhecimento_RCEBD(sql).getByOid_RCE(oid_RCE);
        } finally {
            fimTransacao(false);
        }
    }

    public List getByOid_Conhecimento(String oid_Conhecimento) throws Excecoes {
        inicioTransacao();
        try {
            return new Conhecimento_RCEBD(sql).getByOid_Conhecimento(oid_Conhecimento);
        } finally {
            fimTransacao(false);
        }
    }
}
