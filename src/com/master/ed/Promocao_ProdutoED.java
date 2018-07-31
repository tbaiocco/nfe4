package com.master.ed;

import com.master.util.Data;
import com.master.util.Excecoes;

/**
 * Título: ProdutoED Descrição: Produtos - ED Data da criação: 10/2003
 * Atualizado em: 02/2004 Empresa: ÊxitoLogística Mastercom Autor: Carlos
 * Eduardo de Holleben
 */

public class Promocao_ProdutoED extends MasterED {

    
    public Promocao_ProdutoED(long promocao_Produto) {
        OID_Promocao_Produto = promocao_Produto;
    }
    public Promocao_ProdutoED() {
        super();
    }
    private String CD_Produto;
    private String NM_Produto;
    private String Usuario_Stamp;
    private String Dt_Stamp;
    private String Dm_Stamp;
    private String oid_Pessoa;
    private String NM_Razao_Social;
    private String OID_Produto;
    private int Oid_Unidade_Produto;
    private double NR_Peso_Medio;
    private double NR_Peso_Liquido;
    private int NR_QT_Caixa;
    private String CD_Fornecedor;
    private String NM_Unidade_Produto;
    private String CD_Unidade_Produto;
    private String NM_Estrutura_Produto;
    private String CD_Estrutura_Produto;
    private String NM_Descricao_Caixa;

    private String nm_Estrutura_Produto;

    private String nm_Nivel_Produto1;

    private String nm_Nivel_Produto2;

    private String nm_Nivel_Produto3;

    private String nm_Nivel_Produto4;

    private String nm_Nivel_Produto5;
    private String NR_CNPJ_CPF;
    private String oid_Pessoa_Cliente;
    private String NM_Razao_Social_Cliente;
    private String OID_Produto_Cliente;
    private double VL_Produto;
    private double PE_Desconto_Avista;
    private double PE_Desconto_7_Dias;
    private double PE_Acrescimo_21_Dias;
    private double PE_Acrescimo_28_Dias;
    private double VL_Desconto_Avista;
    private double VL_Desconto_7_Dias;
    private double VL_Acrescimo_21_Dias;
    private double VL_Acrescimo_28_Dias;

    private String NM_Tabela;
    private long OID_Promocao_Produto;
    private String DT_Vigencia;
    private String DT_Validade;
    private String NR_CNPJ_CPF_Cliente;
    private double NR_QT_Minima;
    private double NR_QT_Maxima;
    private double PE_Desconto;

    private String DT_Referencia;

    public String getCD_Produto() {
        return CD_Produto;
    }

    public String getNM_Produto() {
        return NM_Produto;
    }

    public void setCD_Produto(String CD_Produto) {
        this.CD_Produto = CD_Produto;
    }

    public void setNM_Produto(String NM_Produto) {
        this.NM_Produto = NM_Produto;
    }

    public String getDt_Stamp() {
        return Dt_Stamp;
    }

    public String getUsuario_Stamp() {
        return Usuario_Stamp;
    }

    public void setDt_Stamp(String Dt_Stamp) {
        this.Dt_Stamp = Dt_Stamp;
    }

    public void setUsuario_Stamp(String Usuario_Stamp) {
        this.Usuario_Stamp = Usuario_Stamp;
    }

    public String getOID_Produto() {
        return OID_Produto;
    }

    public String getOid_Pessoa() {
        return oid_Pessoa;
    }

    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }

    public void setOID_Produto(String OID_Produto) {
        this.OID_Produto = OID_Produto;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public void setOid_Unidade_Produto(int Oid_Unidade_Produto) {
        this.Oid_Unidade_Produto = Oid_Unidade_Produto;
    }

    public int getOid_Unidade_Produto() {
        return Oid_Unidade_Produto;
    }

    public void setNR_Peso_Medio(double NR_Peso_Medio) {
        this.NR_Peso_Medio = NR_Peso_Medio;
    }

    public double getNR_Peso_Medio() {
        return NR_Peso_Medio;
    }

    public void setNR_Peso_Liquido(double NR_Peso_Liquido) {
        this.NR_Peso_Liquido = NR_Peso_Liquido;
    }

    public double getNR_Peso_Liquido() {
        return NR_Peso_Liquido;
    }

    public void setNR_QT_Caixa(int NR_QT_Caixa) {
        this.NR_QT_Caixa = NR_QT_Caixa;
    }

    public int getNR_QT_Caixa() {
        return NR_QT_Caixa;
    }

    public void setCD_Fornecedor(String CD_Fornecedor) {
        this.CD_Fornecedor = CD_Fornecedor;
    }

    public String getCD_Fornecedor() {
        return CD_Fornecedor;
    }

    public void setNM_Unidade_Produto(String NM_Unidade_Produto) {
        this.NM_Unidade_Produto = NM_Unidade_Produto;
    }

    public String getNM_Unidade_Produto() {
        return NM_Unidade_Produto;
    }

    public void setCD_Unidade_Produto(String CD_Unidade_Produto) {
        this.CD_Unidade_Produto = CD_Unidade_Produto;
    }

    public String getCD_Unidade_Produto() {
        return CD_Unidade_Produto;
    }

    public void setNM_Estrutura_Produto(String NM_Estrutura_Produto) {
        this.NM_Estrutura_Produto = NM_Estrutura_Produto;
    }

    public String getNM_Estrutura_Produto() {
        return NM_Estrutura_Produto;
    }

    public void setCD_Estrutura_Produto(String CD_Estrutura_Produto) {
        this.CD_Estrutura_Produto = CD_Estrutura_Produto;
    }

    public String getCD_Estrutura_Produto() {
        return CD_Estrutura_Produto;
    }

    public void setNM_Descricao_Caixa(String NM_Descricao_Caixa) {
        this.NM_Descricao_Caixa = NM_Descricao_Caixa;
    }

    public String getNM_Descricao_Caixa() {
        return NM_Descricao_Caixa;
    }

    public String getNm_Estrutura_Produto() {
        return nm_Estrutura_Produto;
    }

    public String getNm_Nivel_Produto1() {
        return nm_Nivel_Produto1;
    }

    public String getNm_Nivel_Produto2() {
        return nm_Nivel_Produto2;
    }

    public void setNm_Nivel_Produto1(String nm_Nivel_Produto1) {
        this.nm_Nivel_Produto1 = nm_Nivel_Produto1;
    }

    public void setNm_Estrutura_Produto(String nm_Estrutura_Produto) {
        this.nm_Estrutura_Produto = nm_Estrutura_Produto;
    }

    public void setNm_Nivel_Produto2(String nm_Nivel_Produto2) {
        this.nm_Nivel_Produto2 = nm_Nivel_Produto2;
    }

    public void setNm_Nivel_Produto3(String nm_Nivel_Produto3) {
        this.nm_Nivel_Produto3 = nm_Nivel_Produto3;
    }

    public String getNm_Nivel_Produto3() {
        return nm_Nivel_Produto3;
    }

    public void setNm_Nivel_Produto4(String nm_Nivel_Produto4) {
        this.nm_Nivel_Produto4 = nm_Nivel_Produto4;
    }

    public void setNm_Nivel_Produto5(String nm_Nivel_Produto5) {
        this.nm_Nivel_Produto5 = nm_Nivel_Produto5;
    }

    public String getNm_Nivel_Produto4() {
        return nm_Nivel_Produto4;
    }

    public String getNm_Nivel_Produto5() {
        return nm_Nivel_Produto5;
    }

    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }

    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }

    public void setOid_Pessoa_Cliente(String oid_Pessoa_Cliente) {
        this.oid_Pessoa_Cliente = oid_Pessoa_Cliente;
    }

    public String getOid_Pessoa_Cliente() {
        return oid_Pessoa_Cliente;
    }

    public void setNM_Razao_Social_Cliente(String NM_Razao_Social_Cliente) {
        this.NM_Razao_Social_Cliente = NM_Razao_Social_Cliente;
    }

    public String getNM_Razao_Social_Cliente() {
        return NM_Razao_Social_Cliente;
    }

    public void setOID_Produto_Cliente(String OID_Produto_Cliente) {
        this.OID_Produto_Cliente = OID_Produto_Cliente;
    }

    public String getOID_Produto_Cliente() {
        return OID_Produto_Cliente;
    }

    public void setVL_Produto(double VL_Produto) {
        this.VL_Produto = VL_Produto;
    }

    public double getVL_Produto() {
        return VL_Produto;
    }

    public void setPE_Desconto_Avista(double PE_Desconto_Avista) {
        this.PE_Desconto_Avista = PE_Desconto_Avista;
    }

    public double getPE_Desconto_Avista() {
        return PE_Desconto_Avista;
    }

    public void setPE_Desconto_7_Dias(double PE_Desconto_7_Dias) {
        this.PE_Desconto_7_Dias = PE_Desconto_7_Dias;
    }

    public double getPE_Desconto_7_Dias() {
        return PE_Desconto_7_Dias;
    }

    public void setPE_Acrescimo_21_Dias(double PE_Acrescimo_21_Dias) {
        this.PE_Acrescimo_21_Dias = PE_Acrescimo_21_Dias;
    }

    public double getPE_Acrescimo_21_Dias() {
        return PE_Acrescimo_21_Dias;
    }

    public void setPE_Acrescimo_28_Dias(double PE_Acrescimo_28_Dias) {
        this.PE_Acrescimo_28_Dias = PE_Acrescimo_28_Dias;
    }

    public double getPE_Acrescimo_28_Dias() {
        return PE_Acrescimo_28_Dias;
    }

    public void setNM_Tabela(String NM_Tabela) {
        this.NM_Tabela = NM_Tabela;
    }

    public String getNM_Tabela() {
        return NM_Tabela;
    }

    public void setOID_Promocao_Produto(long OID_Promocao_Produto) {
        this.OID_Promocao_Produto = OID_Promocao_Produto;
    }

    public long getOID_Promocao_Produto() {
        return OID_Promocao_Produto;
    }

    public void setDT_Vigencia(String DT_Vigencia) {
        this.DT_Vigencia = DT_Vigencia;
    }

    public String getDT_Vigencia() {
        return DT_Vigencia;
    }

    public void setDT_Validade(String DT_Validade) {
        this.DT_Validade = DT_Validade;
    }

    public String getDT_Validade() {
        return DT_Validade;
    }

    public double getVL_Acrescimo_21_Dias() {
        return VL_Acrescimo_21_Dias;
    }

    public double getVL_Acrescimo_28_Dias() {
        return VL_Acrescimo_28_Dias;
    }

    public double getVL_Desconto_7_Dias() {
        return VL_Desconto_7_Dias;
    }

    public double getVL_Desconto_Avista() {
        return VL_Desconto_Avista;
    }

    public void setVL_Desconto_Avista(double VL_Desconto_Avista) {
        this.VL_Desconto_Avista = VL_Desconto_Avista;
    }

    public void setVL_Desconto_7_Dias(double VL_Desconto_7_Dias) {
        this.VL_Desconto_7_Dias = VL_Desconto_7_Dias;
    }

    public void setVL_Acrescimo_28_Dias(double VL_Acrescimo_28_Dias) {
        this.VL_Acrescimo_28_Dias = VL_Acrescimo_28_Dias;
    }

    public void setVL_Acrescimo_21_Dias(double VL_Acrescimo_21_Dias) {
        this.VL_Acrescimo_21_Dias = VL_Acrescimo_21_Dias;
    }

    public void setNR_CNPJ_CPF_Cliente(String NR_CNPJ_CPF_Cliente) {
        this.NR_CNPJ_CPF_Cliente = NR_CNPJ_CPF_Cliente;
    }

    public String getNR_CNPJ_CPF_Cliente() {
        return NR_CNPJ_CPF_Cliente;
    }

    public void setNR_QT_Minima(double NR_QT_Minima) {
        this.NR_QT_Minima = NR_QT_Minima;
    }

    public double getNR_QT_Minima() {
        return NR_QT_Minima;
    }

    public void setNR_QT_Maxima(double NR_QT_Maxima) {
        this.NR_QT_Maxima = NR_QT_Maxima;
    }

    public double getNR_QT_Maxima() {
        return NR_QT_Maxima;
    }

    public void setPE_Desconto(double PE_Desconto) {
        this.PE_Desconto = PE_Desconto;
    }

    public double getPE_Desconto() {
        return PE_Desconto;
    }

    public boolean isVencida() throws Excecoes {
        return !(this.OID_Promocao_Produto > 0 && (Data.stringToCalendar(Data.getDataDMY(), "dd/MM/yyyy").before(Data.stringToCalendar(this.DT_Validade, "dd/MM/yyyy")) || Data.getDataDMY().equals(
                this.DT_Validade)))
                || !(this.OID_Promocao_Produto > 0 && (Data.stringToCalendar(Data.getDataDMY(), "dd/MM/yyyy").after(Data.stringToCalendar(this.DT_Vigencia, "dd/MM/yyyy")) || Data.getDataDMY().equals(
                        this.DT_Vigencia)));
    }

    public boolean isVencida(String DataRef) throws Excecoes {
        return !(this.OID_Promocao_Produto > 0 && DataRef != null && (Data.stringToCalendar(DataRef, "dd/MM/yyyy").before(Data.stringToCalendar(this.DT_Validade, "dd/MM/yyyy")) || DataRef
                .equals(this.DT_Validade)))
                || !(this.OID_Promocao_Produto > 0 && DataRef != null && (Data.stringToCalendar(DataRef, "dd/MM/yyyy").after(Data.stringToCalendar(this.DT_Vigencia, "dd/MM/yyyy")) || DataRef
                        .equals(this.DT_Vigencia)));
    }

    public String getDT_Referencia() {
        return DT_Referencia;
    }

    public void setDT_Referencia(String referencia) {
        DT_Referencia = referencia;
    }
}