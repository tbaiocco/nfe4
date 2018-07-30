/*
 * Created on 25/02/2005
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Tipo_Permissao_SubModuloBD;
import com.master.ed.Tipo_Permissao_SubModuloED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class Tipo_Permissao_SubModuloRN extends Transacao {

    Tipo_Permissao_SubModuloBD Tipo_Permissao_SubModuloBD = null;

    public Tipo_Permissao_SubModuloRN() {

    }

    public Tipo_Permissao_SubModuloED inclui( Tipo_Permissao_SubModuloED ed) throws Excecoes {

         this.inicioTransacao();
         ed = new Tipo_Permissao_SubModuloBD(this.sql).inclui(ed);
         this.fimTransacao(true);
         
         return ed;
    }

    public void altera( Tipo_Permissao_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        new Tipo_Permissao_SubModuloBD(this.sql).altera(ed);
        this.fimTransacao(true);
    }

    public void deleta( Tipo_Permissao_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        new Tipo_Permissao_SubModuloBD(this.sql).deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(Tipo_Permissao_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Tipo_Permissao_SubModuloBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
   
    public Tipo_Permissao_SubModuloED getByRecord(Tipo_Permissao_SubModuloED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new Tipo_Permissao_SubModuloBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return ed;
    }
}