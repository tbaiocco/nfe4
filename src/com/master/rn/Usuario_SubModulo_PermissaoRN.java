/*
 * Created on 25/02/2005
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Usuario_SubModulo_PermissaoBD;
import com.master.ed.Usuario_SubModulo_PermissaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class Usuario_SubModulo_PermissaoRN extends Transacao {

    Usuario_SubModulo_PermissaoBD Usuario_SubModulo_PermissaoBD = null;

    public Usuario_SubModulo_PermissaoRN() {

    }

    public Usuario_SubModulo_PermissaoED inclui(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

         this.inicioTransacao();
         ed = new Usuario_SubModulo_PermissaoBD(this.sql).inclui(ed);
         this.fimTransacao(true);
         return ed;
    }

    public void altera(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        new Usuario_SubModulo_PermissaoBD(this.sql).altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        new Usuario_SubModulo_PermissaoBD(this.sql).deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Usuario_SubModulo_PermissaoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
   
    public Usuario_SubModulo_PermissaoED getByRecord(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new Usuario_SubModulo_PermissaoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return ed;
    }
}