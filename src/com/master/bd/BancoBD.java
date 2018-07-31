package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.BancoED;
import com.master.ed.Livro_FiscalED;
import com.master.rl.BancoRL;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

import com.master.ed.EDI_ImportacaoED;

import com.master.bd.Item_Nota_Fiscal_TransacoesBD;;

public class BancoBD {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria();

    public BancoBD(ExecutaSQL sql) {
        this.executasql = sql;
        util = new Utilitaria(this.executasql);
    }

    public void inclui(BancoED ed) throws Excecoes {

        String sql = null;

        try {

            ed.setOID_Banco(util.getAutoIncremento("oid_Banco", "Bancos"));

            sql = " INSERT INTO Bancos (OID_Banco, OID_Pessoa, CD_Banco, CD_Remessa, CD_Baixa, CD_Alteracao_Vencimento, CD_Desconto, CD_Primeira_Instr_Protesto, CD_Sustar_Protesto, CD_Protesto, CD_Segunda_Instr_Protesto, CD_Tipo_Documento, NR_Dias_Protesto, DM_Formulario) values ";
            sql += "(" + ed.getOID_Banco() + ",'" + ed.getOID_Pessoa() + "','" + ed.getCD_Banco() + "',";
            sql += "'" + ed.getCD_Remessa() + "','" + ed.getCD_Baixa() + "',";
            sql += "'" + ed.getCD_Alteracao_Vencimento() + "','" + ed.getCD_Desconto() + "',";
            sql += "'" + ed.getCD_Primeira_Instr_Protesto() + "','" + ed.getCD_Sustar_Protesto() + "',";
            sql += "'" + ed.getCD_Protesto() + "','" + ed.getCD_Segunda_Instr_Protesto() + "',";
            sql += "'" + ed.getCD_Tipo_Documento() + "','" + ed.getNR_Dias_Protesto() + "','" + ed.getDM_Formulario() + "')";

            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"inclui()");
        }
    }

    public void altera(BancoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " UPDATE Bancos SET ";
            sql += " CD_Banco = '" + ed.getCD_Banco() + "', ";
            sql += " CD_Remessa = '" + ed.getCD_Remessa() + "', ";
            sql += " DM_Formulario = '" + ed.getDM_Formulario() + "', ";
            sql += " CD_Baixa = '" + ed.getCD_Baixa() + "', ";
            sql += " CD_Alteracao_Vencimento = '" + ed.getCD_Alteracao_Vencimento() + "', ";
            sql += " CD_Desconto = '" + ed.getCD_Desconto() + "', ";
            sql += " CD_Primeira_Instr_Protesto = '" + ed.getCD_Primeira_Instr_Protesto() + "', ";
            sql += " CD_Sustar_Protesto = '" + ed.getCD_Sustar_Protesto() + "', ";
            sql += " CD_Protesto = '" + ed.getCD_Protesto() + "', ";
            sql += " CD_Segunda_Instr_Protesto = '" + ed.getCD_Segunda_Instr_Protesto() + "', ";
            sql += " CD_Tipo_Documento = '" + ed.getCD_Tipo_Documento() + "', ";
            sql += " NR_Dias_Protesto = '" + ed.getNR_Dias_Protesto() + "' ";
            sql += " WHERE oid_Banco = " + ed.getOID_Banco();

            executasql.executarUpdate(sql);

        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(BancoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = "DELETE FROM Bancos WHERE oid_Banco = ";
            sql += "(" + ed.getOID_Banco() + ")";

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"deleta()");
        }
    }

    public ArrayList lista(BancoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {
            sql = " SELECT * " +
            	  " FROM Bancos, Pessoas " +
            	  " WHERE Pessoas.oid_Pessoa = Bancos.oid_Pessoa";

            if (ed.getNM_Banco() != null && !ed.getNM_Banco().equals("")) {
                sql += " AND NM_Razao_Social LIKE '" + ed.getNM_Banco() + "%'";
            }
            if (ed.getCD_Banco() != null && !ed.getCD_Banco().equals("")) {
                sql += " AND CD_Banco = '" + ed.getCD_Banco() + "'";
            }

            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                BancoED edVolta = new BancoED();
                edVolta.setOID_Banco(res.getLong("oid_Banco"));
                edVolta.setOID_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setCD_Remessa(res.getString("CD_Remessa"));
                edVolta.setCD_Banco(res.getString("CD_Banco"));
                edVolta.setCD_Alteracao_Vencimento(res.getString("CD_Alteracao_Vencimento"));
                edVolta.setCD_Baixa(res.getString("CD_Baixa"));
                edVolta.setCD_Desconto(res.getString("CD_Desconto"));
                edVolta.setCD_Primeira_Instr_Protesto(res.getString("CD_Primeira_Instr_Protesto"));
                edVolta.setCD_Protesto(res.getString("CD_Protesto"));
                edVolta.setCD_Segunda_Instr_Protesto(res.getString("CD_Segunda_Instr_Protesto"));
                edVolta.setCD_Sustar_Protesto(res.getString("CD_Sustar_Protesto"));
                edVolta.setCD_Tipo_Documento(res.getString("CD_Tipo_Documento"));
                edVolta.setNR_Dias_Protesto(res.getString("NR_Dias_Protesto"));
                edVolta.setNM_Banco(res.getString("NM_Razao_Social"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"lista()");
        }

        return list;
    }

    public BancoED getByRecord(BancoED ed) throws Excecoes {

        String sql = null;
        BancoED edVolta = new BancoED();
        try {

            sql = " SELECT *,Pessoas.oid_Pessoa as pessoas_oid_Pessoa, Pessoas.NM_Razao_Social" +
            	  " FROM Bancos, Pessoas " +
            	  " WHERE Bancos.oid_Pessoa = Pessoas.oid_Pessoa";

            if (ed.getOID_Banco() > 0)
                sql += " AND oid_Banco = " + ed.getOID_Banco();
            if (JavaUtil.doValida(ed.getOID_Pessoa()))
                sql += " AND Bancos.oid_Pessoa = '" + ed.getOID_Pessoa() + "'";
            if (JavaUtil.doValida(ed.getCD_Banco()))
                sql += " AND CD_Banco = '" + ed.getCD_Banco() + "'";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta.setOID_Banco(res.getLong("oid_Banco"));
                edVolta.setOID_Pessoa(res.getString("pessoas_oid_Pessoa"));
                edVolta.setCD_Remessa(res.getString("CD_Remessa"));
                edVolta.setCD_Banco(res.getString("CD_Banco"));
                edVolta.setDM_Formulario(res.getString("DM_Formulario"));
                edVolta.setCD_Alteracao_Vencimento(res.getString("CD_Alteracao_Vencimento"));
                edVolta.setCD_Baixa(res.getString("CD_Baixa"));
                edVolta.setCD_Desconto(res.getString("CD_Desconto"));
                edVolta.setCD_Primeira_Instr_Protesto(res.getString("CD_Primeira_Instr_Protesto"));
                edVolta.setCD_Protesto(res.getString("CD_Protesto"));
                edVolta.setCD_Segunda_Instr_Protesto(res.getString("CD_Segunda_Instr_Protesto"));
                edVolta.setCD_Sustar_Protesto(res.getString("CD_Sustar_Protesto"));
                edVolta.setCD_Tipo_Documento(res.getString("CD_Tipo_Documento"));
                edVolta.setNR_Dias_Protesto(res.getString("NR_Dias_Protesto"));
                edVolta.setNM_Banco(res.getString("NM_Razao_Social"));
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "getByRecord()");
        }
        return edVolta;
    }

    public void geraRelatorio(BancoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " SELECT * " +
            	  " FROM Bancos " +
            	  " WHERE oid_Banco > 0";

            if (ed.getCD_Banco() != null && !ed.getCD_Banco().equals("")) {
                sql += " and CD_Banco = '" + ed.getCD_Banco() + "'";
            }
            if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")) {
                sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
            }

            ResultSet res = this.executasql.executarConsulta(sql);

            new BancoRL().geraRelatEstoque(res);
        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            Excecoes exce = new Excecoes();
            exce.setExc(exc);
            exce.setMensagem("Erro no m�todo listar");
            exce.setClasse(this.getClass().getName());
            exce.setMetodo("geraRelatorio(BancoED ed)");
        }
    }

//    public void verifica_Vista(BancoED ed) throws Excecoes {
//
//        String sql = null;
//        String NM_Registro = "";
//        String NR_CNPJ = "";
//
//        try {
//
//            ManipulaArquivo man = new ManipulaArquivo("");
//            LineNumberReader line = man.leLinha("/home/gelson/avista.met");
//            int linha = 0;
//
//            if(line.ready()){
//// System.out.println("clientes Vista line.ready");
//	            while ((NM_Registro = line.readLine()) != null){
//	                NR_CNPJ = NM_Registro.substring(0,14);
//	                NR_CNPJ = NR_CNPJ.trim();
//
//	                sql = "select * from clientes where oid_pessoa = '"+NR_CNPJ+"'";
//	                ResultSet res = this.executasql.executarConsulta(sql);
//	                if(res.next()){
//			            sql = "update clientes set dm_tipo_cobranca = 'V'" +
//			            	  " WHERE oid_pessoa = '"+NR_CNPJ+"'";
//			            executasql.executarUpdate(sql);
//	                }
//	            }
//            }
//        }
//        catch (Exception exc) {
//            // System.out.println("problema no cnpj:"+NR_CNPJ);
//            exc.printStackTrace();
//            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
//            		"deleta()");
//        }
//    }

}