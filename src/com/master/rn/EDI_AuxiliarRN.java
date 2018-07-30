package com.master.rn;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.master.bd.EDI_AuxiliarBD;
import com.master.ed.EDI_AuxiliarED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class EDI_AuxiliarRN extends Transacao {

    public static void main(String[] args) throws SQLException, Excecoes {
        new EDI_AuxiliarRN().importar();
    }
    
    public void importar() throws SQLException, Excecoes {
        
        this.inicioTransacao();
        this.sql.executarUpdate("DELETE FROM Rotas_Vendas");
        this.sql.executarUpdate("DELETE FROM Clientes_Vendedores");
        this.sql.executarUpdate("DELETE FROM Observacoes_Clientes WHERE USUARIO_STAMP = 'IMPORTACAO'");
        this.sql.executarUpdate("DELETE FROM Duplicatas" +
                " WHERE (select count(*) from Origens_Duplicatas where duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata) = 0" +
                "   AND TX_Observacao = 'IMPORTACAO'");
        
        ResultSet res = this.sql.executarConsulta("select oid_Pessoa" +
                "        from pessoas" +
                "        where pessoas.oid_pessoa not in('00726775037','87226528000161','03868193000195')" +
                "  AND (select count(*) from clientes where pessoas.oid_Pessoa = clientes.oid_Pessoa) > 0" +
                "  AND (select count(*) from vendedores where pessoas.oid_Pessoa = vendedores.oid_Pessoa) = 0" +
                "  AND (select count(*) from bancos where pessoas.oid_Pessoa = bancos.oid_Pessoa) = 0" +
                "  AND (select count(*) from fornecedores where pessoas.oid_Pessoa = fornecedores.oid_Pessoa) = 0" +
                "  AND (select count(*) from entregadores where pessoas.oid_Pessoa = entregadores.oid_Pessoa) = 0" +
                "  AND (select count(*) from motoristas where pessoas.oid_Pessoa = motoristas.oid_Pessoa) = 0" +
                "  AND (select count(*) from tabelas_fretes where pessoas.oid_Pessoa = tabelas_fretes.oid_Pessoa) = 0" +
                "  AND (select count(*) from compromissos where pessoas.oid_Pessoa = compromissos.oid_Pessoa) = 0");
        int count=0;
        while (res.next())
        {
            count++;
            this.sql.executarUpdate("DELETE FROM Clientes WHERE oid_Pessoa="+JavaUtil.getSQLString(res.getString("oid_Pessoa")));
            this.sql.executarUpdate("DELETE FROM Pessoas WHERE oid_Pessoa="+JavaUtil.getSQLString(res.getString("oid_Pessoa")));
        }
        this.fimTransacao(true);
        System.exit(-1);
    }
    
    public EDI_AuxiliarRN() {
    }

    public void importaPessoa(EDI_AuxiliarED ed) throws Excecoes {
        try {
            new EDI_AuxiliarBD().importaPessoa(ed);
        } catch (Excecoes e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void importaTabelaVenda(EDI_AuxiliarED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new EDI_AuxiliarBD(this.sql).importaTabelaVenda(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void importaPercProdutos(EDI_AuxiliarED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new EDI_AuxiliarBD(this.sql).importaPercProdutos(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void importaEstoque(EDI_AuxiliarED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new EDI_AuxiliarBD(this.sql).importaEstoque(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void importaDuplicatas(EDI_AuxiliarED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new EDI_AuxiliarBD(this.sql).importaDuplicatas(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void importaRotas(EDI_AuxiliarED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new EDI_AuxiliarBD(this.sql).importaRotas(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
}