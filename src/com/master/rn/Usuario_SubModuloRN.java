/*
 * Created on 25/02/2005
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Usuario_SubModuloBD;
import com.master.ed.Usuario_SubModuloED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class Usuario_SubModuloRN extends Transacao {

    Usuario_SubModuloBD Usuario_SubModuloBD = null;

    public Usuario_SubModuloRN() {

    }

    public Usuario_SubModuloED inclui(Usuario_SubModuloED ed) throws Excecoes {

         this.inicioTransacao();
         ed = new Usuario_SubModuloBD(this.sql).inclui(ed);
         this.fimTransacao(true);
         return ed;
    }

    public void altera(Usuario_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        new Usuario_SubModuloBD(this.sql).altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(Usuario_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        new Usuario_SubModuloBD(this.sql).deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(Usuario_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Usuario_SubModuloBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
   
    public Usuario_SubModuloED getByRecord(Usuario_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new Usuario_SubModuloBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return ed;
    }
}