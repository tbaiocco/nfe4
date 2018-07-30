package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Ocorrencia_FornecedorBean {

    private String NM_Contato;
    private String DT_Ocorrencia_Fornecedor;
    private String HR_Ocorrencia_Fornecedor;
    private String DM_Gera_Pendencia;
    private String DT_Final_Pendencia;
    private String TX_Descricao;
    private String Usuario_Stamp;
    private String Dt_Stamp;
    private String Dm_Stamp;
    private int oid;
    private int oid_Tipo_Ocorrencia;
    private String oid_Pessoa;
    private String NM_Razao_Social;
    private String NR_CNPJ_CPF;
    private String NM_Tipo_Ocorrencia;
    private String CD_Tipo_Ocorrencia;

    public Ocorrencia_FornecedorBean() {
        NM_Contato = " ";
        HR_Ocorrencia_Fornecedor = " ";
        TX_Descricao = " ";
    }

    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }

    public void setNR_CNPJ_CPF(String nr_cnpj_cpf) {
        NR_CNPJ_CPF = nr_cnpj_cpf;
    }

    public String getNM_Contato() {
        return NM_Contato;
    }

    public void setNM_Contato(String NM_Contato) {
        this.NM_Contato = NM_Contato;
    }

    public String getDT_Ocorrencia_Fornecedor() {
        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Ocorrencia_Fornecedor);
        DT_Ocorrencia_Fornecedor = DataFormatada.getDT_FormataData();

        return DT_Ocorrencia_Fornecedor;
    }

    public void setDT_Ocorrencia_Fornecedor(String DT_Ocorrencia_Fornecedor) {
        this.DT_Ocorrencia_Fornecedor = DT_Ocorrencia_Fornecedor;
    }

    public String getHR_Ocorrencia_Fornecedor() {
        return HR_Ocorrencia_Fornecedor;
    }

    public void setHR_Ocorrencia_Fornecedor(String HR_Ocorrencia_Fornecedor) {
        this.HR_Ocorrencia_Fornecedor = HR_Ocorrencia_Fornecedor;
    }

    public String getDM_Gera_Pendencia() {
        return DM_Gera_Pendencia;
    }

    public void setDM_Gera_Pendencia(String DM_Gera_Pendencia) {
        this.DM_Gera_Pendencia = DM_Gera_Pendencia;
    }

    public String getDT_Final_Pendencia() {
        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Final_Pendencia);
        DT_Final_Pendencia = DataFormatada.getDT_FormataData();

        return DT_Final_Pendencia;
    }

    public void setDT_Final_Pendencia(String DT_Final_Pendencia) {
        this.DT_Final_Pendencia = DT_Final_Pendencia;
    }

    public String getTX_Descricao() {
        return TX_Descricao;
    }

    public void setTX_Descricao(String TX_Descricao) {
        this.TX_Descricao = TX_Descricao;
    }

    public int getOID_Tipo_Ocorrencia() {
        return oid_Tipo_Ocorrencia;
    }

    public void setOID_Tipo_Ocorrencia(int n) {
        this.oid_Tipo_Ocorrencia = n;
    }

    public String getNM_Tipo_Ocorrencia() {
        return NM_Tipo_Ocorrencia;
    }

    public void setNM_Tipo_Ocorrencia(String NM_Tipo_Ocorrencia) {
        this.NM_Tipo_Ocorrencia = NM_Tipo_Ocorrencia;
    }

    public String getCD_Tipo_Ocorrencia() {
        return CD_Tipo_Ocorrencia;
    }

    public void setCD_Tipo_Ocorrencia(String CD_Tipo_Ocorrencia) {
        this.CD_Tipo_Ocorrencia = CD_Tipo_Ocorrencia;
    }

    public String getOID_Pessoa() {
        return oid_Pessoa;
    }

    public void setOID_Pessoa(String n) {
        this.oid_Pessoa = n;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    /*
     * ---------------- Bloco Padrão para Todas Classes ------------------
     */
    public String getUsuario_Stamp() {
        return Usuario_Stamp;
    }

    public void setUsuario_Stamp(String Usuario_Stamp) {
        this.Usuario_Stamp = Usuario_Stamp;
    }

    public String getDt_Stamp() {
        return Dt_Stamp;
    }

    public void setDt_Stamp(String Dt_Stamp) {
        this.Dt_Stamp = Dt_Stamp;
    }

    public String getDm_Stamp() {
        return Dm_Stamp;
    }

    public void setDm_Stamp(String Dm_Stamp) {
        this.Dm_Stamp = Dm_Stamp;
    }

    public int getOID() {
        return oid;
    }

    public void setOID(int n) {
        this.oid = n;
    }

    public void insert() throws Exception {
        Connection conn = null;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery("SELECT MAX(OID_Ocorrencia_Fornecedor) FROM Ocorrencias_Fornecedores");

            while (cursor.next()) {
                int oid = cursor.getInt(1);
                setOID(oid + 1);
            }
            cursor.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        StringBuffer buff = new StringBuffer();
        buff.append("INSERT INTO Ocorrencias_Fornecedores (OID_Ocorrencia_Fornecedor, OID_Tipo_Ocorrencia, OID_Pessoa,  NM_Contato, DT_Ocorrencia_Fornecedor, HR_Ocorrencia_Fornecedor, DM_Gera_Pendencia, DT_Final_Pendencia, TX_Descricao) ");
        buff.append("VALUES (?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setInt(1, getOID());
            pstmt.setInt(2, getOID_Tipo_Ocorrencia());
            pstmt.setString(3, getOID_Pessoa());
            pstmt.setString(4, getNM_Contato());
            pstmt.setString(5, this.DT_Ocorrencia_Fornecedor);
            pstmt.setString(6, getHR_Ocorrencia_Fornecedor());
            pstmt.setString(7, getDM_Gera_Pendencia());
            pstmt.setString(8, this.DT_Final_Pendencia);
            pstmt.setString(9, getTX_Descricao());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void update() throws Exception {
        Connection conn = null;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        StringBuffer buff = new StringBuffer();
        buff.append("UPDATE Ocorrencias_Fornecedores SET OID_Tipo_Ocorrencia=?, NM_Contato=?, DM_Gera_Pendencia=?, DT_Final_Pendencia=?, TX_Descricao=? ");
        buff.append("WHERE OID_Ocorrencia_Fornecedor=?");
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());

            pstmt.setInt(1, getOID_Tipo_Ocorrencia());
            pstmt.setString(2, getNM_Contato());
            pstmt.setString(3, getDM_Gera_Pendencia());
            pstmt.setString(4, this.DT_Final_Pendencia);
            pstmt.setString(5, getTX_Descricao());
            pstmt.setInt(6, getOID());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void delete() throws Exception {
        Connection conn = null;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        StringBuffer buff = new StringBuffer();
        buff.append("DELETE FROM Ocorrencias_Fornecedores ");
        buff.append("WHERE OID_Ocorrencia_Fornecedor=?");
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setInt(1, getOID());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static final Ocorrencia_FornecedorBean getByOID(int oid) throws Exception {
        Connection conn = null;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        Ocorrencia_FornecedorBean p = new Ocorrencia_FornecedorBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append(" SELECT Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor, " + 
                        "Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia, " + 
                        "Ocorrencias_Fornecedores.OID_Pessoa, " + 
                        "NM_Contato, DT_Ocorrencia_Fornecedor, " + 
                        "HR_Ocorrencia_Fornecedor, " + 
                        "DM_Gera_Pendencia, " + 
                        "DT_Final_Pendencia, " + 
                        "TX_Descricao, " + 
                        "Ocorrencias_Fornecedores.Dt_Stamp, " + 
                        "Ocorrencias_Fornecedores.Usuario_Stamp, " + 
                        "Ocorrencias_Fornecedores.Dm_Stamp, " + 
                        "Pessoas.NM_Razao_Social, " + 
                        "Tipos_Ocorrencias.NM_Tipo_Ocorrencia, " + 
                        "Tipos_Ocorrencias.CD_Tipo_Ocorrencia, " + 
                        "Pessoas.NR_CNPJ_CPF ");
            buff.append(" FROM Ocorrencias_Fornecedores, Pessoas, Tipos_Ocorrencias ");
            buff.append(" WHERE Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ");
            buff.append(" AND Ocorrencias_Fornecedores.OID_Pessoa = Pessoas.OID_Pessoa ");
            buff.append(" AND Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor =");
            buff.append(oid);
            buff.append(" ORDER BY Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor  ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID(cursor.getInt(1));
                p.setOID_Tipo_Ocorrencia(cursor.getInt(2));
                p.setOID_Pessoa(cursor.getString(3));
                p.setNM_Contato(cursor.getString(4));
                p.setDT_Ocorrencia_Fornecedor(cursor.getString(5));
                p.setHR_Ocorrencia_Fornecedor(cursor.getString(6));
                p.setDM_Gera_Pendencia(cursor.getString(7));
                p.setDT_Final_Pendencia(cursor.getString(8));
                p.setTX_Descricao(cursor.getString(9));
                p.setDt_Stamp(cursor.getString(10));
                p.setUsuario_Stamp(cursor.getString(11));
                p.setDm_Stamp(cursor.getString(12));
                p.setNM_Razao_Social(cursor.getString(13));
                p.setNM_Tipo_Ocorrencia(cursor.getString(14));
                p.setCD_Tipo_Ocorrencia(cursor.getString(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static final List getAll() throws Exception {
        Connection conn = null;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Ocorrencia_Fornecedores_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor, " + 
                        "Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia, " + 
                        "Ocorrencias_Fornecedores.OID_Pessoa, " + 
                        "NM_Contato, DT_Ocorrencia_Fornecedor, " + 
                        "HR_Ocorrencia_Fornecedor, " + 
                        "DM_Gera_Pendencia, " + 
                        "DT_Final_Pendencia, " + 
                        "TX_Descricao, " + 
                        "Ocorrencias_Fornecedores.Dt_Stamp, " + 
                        "Ocorrencias_Fornecedores.Usuario_Stamp, " + 
                        "Ocorrencias_Fornecedores.Dm_Stamp, " + 
                        "Pessoas.NM_Razao_Social, " + 
                        "Tipos_Ocorrencias.NM_Tipo_Ocorrencia, " + 
                        "Tipos_Ocorrencias.CD_Tipo_Ocorrencia, " + 
                        "Pessoas.NR_CNPJ_CPF ");
            buff.append("FROM Ocorrencias_Fornecedores, Pessoas, Tipos_Ocorrencias ");
            buff.append("WHERE Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia " + "AND Ocorrencias_Fornecedores.OID_Pessoa = Pessoas.OID_Pessoa ");
            buff.append(" ORDER BY Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor  ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                Ocorrencia_FornecedorBean p = new Ocorrencia_FornecedorBean();
                p.setOID(cursor.getInt(1));
                p.setOID_Tipo_Ocorrencia(cursor.getInt(2));
                p.setOID_Pessoa(cursor.getString(3));
                p.setNM_Contato(cursor.getString(4));
                p.setDT_Ocorrencia_Fornecedor(cursor.getString(5));
                p.setHR_Ocorrencia_Fornecedor(cursor.getString(6));
                p.setDM_Gera_Pendencia(cursor.getString(7));
                p.setDT_Final_Pendencia(cursor.getString(8));
                p.setTX_Descricao(cursor.getString(9));
                p.setDt_Stamp(cursor.getString(10));
                p.setUsuario_Stamp(cursor.getString(11));
                p.setDm_Stamp(cursor.getString(12));
                p.setNM_Razao_Social(cursor.getString(13));
                p.setNM_Tipo_Ocorrencia(cursor.getString(14));
                p.setCD_Tipo_Ocorrencia(cursor.getString(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                Ocorrencia_Fornecedores_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ocorrencia_Fornecedores_Lista;
    }

    public static final List getByNR_CNPJ_CPF(String NR_CNPJ_CPF) throws Exception {
        Connection conn = null;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Ocorrencia_Fornecedores_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor, " + 
                        "Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia, " + 
                        "Ocorrencias_Fornecedores.OID_Pessoa, " + 
                        "NM_Contato, DT_Ocorrencia_Fornecedor, " + 
                        "HR_Ocorrencia_Fornecedor, " + 
                        "DM_Gera_Pendencia, " + 
                        "DT_Final_Pendencia, " + 
                        "TX_Descricao, " + 
                        "Ocorrencias_Fornecedores.Dt_Stamp, " + 
                        "Ocorrencias_Fornecedores.Usuario_Stamp, " + 
                        "Ocorrencias_Fornecedores.Dm_Stamp, " + 
                        "Pessoas.NM_Razao_Social, " + 
                        "Tipos_Ocorrencias.NM_Tipo_Ocorrencia, " + 
                        "Tipos_Ocorrencias.CD_Tipo_Ocorrencia, " + 
                        "Pessoas.NR_CNPJ_CPF ");
            buff.append("FROM Ocorrencias_Fornecedores, Pessoas, Tipos_Ocorrencias ");
            buff.append("WHERE Ocorrencias_Fornecedores.OID_Pessoa = Pessoas.OID_Pessoa " + "AND Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia "
                    + "AND Pessoas.NR_CNPJ_CPF = '" + NR_CNPJ_CPF + "'");
            buff.append(" ORDER BY Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor  ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                Ocorrencia_FornecedorBean p = new Ocorrencia_FornecedorBean();
                p.setOID(cursor.getInt(1));
                p.setOID_Tipo_Ocorrencia(cursor.getInt(2));
                p.setOID_Pessoa(cursor.getString(3));
                p.setNM_Contato(cursor.getString(4));
                p.setDT_Ocorrencia_Fornecedor(cursor.getString(5));
                p.setHR_Ocorrencia_Fornecedor(cursor.getString(6));
                p.setDM_Gera_Pendencia(cursor.getString(7));
                p.setDT_Final_Pendencia(cursor.getString(8));
                p.setTX_Descricao(cursor.getString(9));
                p.setDt_Stamp(cursor.getString(10));
                p.setUsuario_Stamp(cursor.getString(11));
                p.setDm_Stamp(cursor.getString(12));
                p.setNM_Razao_Social(cursor.getString(13));
                p.setNM_Tipo_Ocorrencia(cursor.getString(14));
                p.setCD_Tipo_Ocorrencia(cursor.getString(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                Ocorrencia_Fornecedores_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ocorrencia_Fornecedores_Lista;
    }

    public static final List getByOidPessoa(String oidPessoa) throws Exception {
        Connection conn = null;

        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Ocorrencia_Fornecedores_Lista = new ArrayList();

        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor, " + 
                        "Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia, " + 
                        "Ocorrencias_Fornecedores.OID_Pessoa, " + 
                        "NM_Contato, " + 
                        "DT_Ocorrencia_Fornecedor, " + 
                        "HR_Ocorrencia_Fornecedor, " + 
                        "DM_Gera_Pendencia, " + 
                        "DT_Final_Pendencia, " + 
                        "TX_Descricao, " + 
                        "Ocorrencias_Fornecedores.Dt_Stamp, " + 
                        "Ocorrencias_Fornecedores.Usuario_Stamp, " + 
                        "Ocorrencias_Fornecedores.Dm_Stamp, " + 
                        "Pessoas.NM_Razao_Social, " + 
                        "Tipos_Ocorrencias.NM_Tipo_Ocorrencia, " + 
                        "Tipos_Ocorrencias.CD_Tipo_Ocorrencia, " + 
                        "Pessoas.NR_CNPJ_CPF ");
            buff.append("FROM Ocorrencias_Fornecedores, Pessoas, Tipos_Ocorrencias ");
            buff.append("WHERE Ocorrencias_Fornecedores.OID_Pessoa = Pessoas.OID_Pessoa " + "AND Ocorrencias_Fornecedores.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia "
                    + "AND Pessoas.oid_pessoa = '" + oidPessoa + "' " + "ORDER BY Ocorrencias_Fornecedores.OID_Ocorrencia_Fornecedor");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {

                Ocorrencia_FornecedorBean p = new Ocorrencia_FornecedorBean();
                p.setOID(cursor.getInt(1));
                p.setOID_Tipo_Ocorrencia(cursor.getInt(2));
                p.setOID_Pessoa(cursor.getString(3));
                p.setNM_Contato(cursor.getString(4));
                p.setDT_Ocorrencia_Fornecedor(cursor.getString(5));
                p.setHR_Ocorrencia_Fornecedor(cursor.getString(6));
                p.setDM_Gera_Pendencia(cursor.getString(7));
                p.setDT_Final_Pendencia(cursor.getString(8));
                p.setTX_Descricao(cursor.getString(9));
                p.setDt_Stamp(cursor.getString(10));
                p.setUsuario_Stamp(cursor.getString(11));
                p.setDm_Stamp(cursor.getString(12));
                p.setNM_Razao_Social(cursor.getString(13));
                p.setNM_Tipo_Ocorrencia(cursor.getString(14));
                p.setCD_Tipo_Ocorrencia(cursor.getString(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                Ocorrencia_Fornecedores_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ocorrencia_Fornecedores_Lista;
    }
}