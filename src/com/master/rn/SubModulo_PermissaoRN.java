/*
 * Created on 25/02/2005
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.SubModulo_PermissaoBD;
import com.master.bd.Tipo_Permissao_SubModuloBD;
import com.master.ed.SubModulo_PermissaoED;
import com.master.ed.Tipo_Permissao_SubModuloED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class SubModulo_PermissaoRN extends Transacao {

    SubModulo_PermissaoBD SubModulo_PermissaoBD = null;

    public SubModulo_PermissaoRN() {

    }

    public SubModulo_PermissaoED inclui( SubModulo_PermissaoED ed, boolean addPermissoes) throws Excecoes {

         this.inicioTransacao();
         ed = new SubModulo_PermissaoBD(this.sql).inclui(ed);
         this.fimTransacao(true);
         //*** Se deve adicionar as Permissões Basicas
         if (addPermissoes) {
             ArrayList lista = new Tipo_Permissao_SubModuloBD(this.sql).listaPermissoesBase();
             for (int i = 0; i < lista.size(); i++) {
                 Tipo_Permissao_SubModuloED edTipo = (Tipo_Permissao_SubModuloED) lista.get(i);
                 edTipo.setOid_SubModulo_Permissao(ed.getOid_SubModulo_Permissao());
                 new Tipo_Permissao_SubModuloRN().inclui(edTipo);
             }
         }         
         return ed;
    }

    public void altera( SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        new SubModulo_PermissaoBD(this.sql).altera(ed);
        this.fimTransacao(true);
    }

    public void deleta( SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        new SubModulo_PermissaoBD(this.sql).deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new SubModulo_PermissaoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
   
    public SubModulo_PermissaoED getByRecord(SubModulo_PermissaoED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new SubModulo_PermissaoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return ed;
    }
}