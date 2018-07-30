package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.OracleConnection2;

import com.master.ed.RelatorioED;
import com.master.rl.JasperRL;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.ed.Parametro_FixoED;

public class Tabela_Frete_InternacionalBean extends JavaUtil {

	private String DM_Origem;
	private String NM_Origem;
	private String DM_Destino;
	private String NM_Destino;
	private String DT_Vigencia;
	private String DT_Validade;
	private String DT_Cotacao;
	private String DT_Prazo_Pgto;
	private double NR_Peso_Inicial;
	private double NR_Peso_Final;
	private long NR_Peso_Minimo;
	private double VL_Frete;
	private double VL_Frete_Kg;
	private double VL_Taxas;
	private double PE_Ad_Valorem;
	private double VL_Outros1;
	private double VL_Outros2;
	private double VL_Outros3;
	private double VL_Outros4;
	private double VL_Outros5;
	private double VL_Outros6;
	private double VL_Outros7;
	private double VL_Outros8;
	private double VL_Outros9;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private long oid;
	private long oid_Produto;
	private long oid_Origem;
	private long oid_Destino;
	private long OID_Modal;
	private String oid_Pessoa;
	private String NM_Razao_Social;
	private String NM_Fantasia;
	private String NM_Modal;
	private String NM_Produto;
	private String CD_Produto;
	private String CD_Modal;
	private double VL_Frete_Minimo;
	private double PE_Ademe;
	private double VL_Ademe_Minimo;
	private double VL_Ad_Valorem_Minimo;
	private double VL_Maximo_Nota_Fiscal;
	private double VL_Minimo_Nota_Fiscal;
	private double VL_Despacho;
	private double VL_Frete_Valor_Minimo;
	private String DM_ICMS;
	private String DM_Tipo_Peso;
	private String DM_Tipo_Prazo_Pgto;
	private String OID_Pessoa_Redespacho;
	private String oid_Pessoa_Redespacho;
	private String DM_Tipo_Tabela;
	private long NR_Prazo_Entrega;
	private String DM_Tipo_Pedagio;
	private double VL_Pedagio;
	private long OID_Empresa;
	private long NR_Prazo_Faturamento;
	private double PE_Reentrega;
	private double PE_Devolucao;
	private double PE_Refaturamento;
	private String DM_Unidade;
	private String NM_Tipo_Tabela;
	private long nr_Cotacao;
	private long nr_Cotacao_Copia;
	private long oid_Unidade;
	private String TX_Observacao;
	private String NM_Setor;
	private String NM_Contato;
	private String NR_Telefone;
	private String NR_Fax;
	private String EMail;
	private double VL_Excedente_Estadia;

	private String oid_Pessoa_Transportadora;
	private String NM_Razao_Social_Transportadora;
	private String NM_Fantasia_Transportadora;

	private String NR_CNPJ_CPF_Transportadora;
	private String NM_Vendedor_Transportadora;
	private String NR_Telefone_Vendedor;
	private String NR_Fax_Vendedor;
	private String EMail_Vendedor;
	private String transit_Time;
	private double VL_Produto;
	private String DM_Frete_Responsabilidade;
	private String NM_Responsavel;
	private double VL_Faixa1;
	private double VL_Faixa2;
	private double VL_Faixa3;
	private double VL_Faixa4;
	private double VL_Faixa5;

	private String NM_Origem_Editado;
	private String NM_Destino_Editado;
	private String NM_Produto_Editado;
	
	private String DM_RCTR_C;
	private String DM_RCTR_VI;
	private String DM_RCTR_DC;
	
	private double PE_Comissao;
	private double PE_Frete_E;
	private double PE_Frete_I;
	private double VL_Comissao;
	private double VL_Frete_E;
	private double VL_Frete_I;

	public String getDM_Tipo_Prazo_Pgto() {
		return DM_Tipo_Prazo_Pgto;
	}
	public void setDM_Tipo_Prazo_Pgto(String tipo_Prazo_Pgto) {
		DM_Tipo_Prazo_Pgto = tipo_Prazo_Pgto;
	}
	public double getNR_Peso_Final() {
		return NR_Peso_Final;
	}
	public void setNR_Peso_Final(double peso_Final) {
		NR_Peso_Final = peso_Final;
	}
	public double getNR_Peso_Inicial() {
		return NR_Peso_Inicial;
	}
	public void setNR_Peso_Inicial(double peso_Inicial) {
		NR_Peso_Inicial = peso_Inicial;
	}
	public long getNr_Cotacao_Copia() {
		return nr_Cotacao_Copia;
	}
	public void setNr_Cotacao_Copia(long nr_Cotacao_Copia) {
		this.nr_Cotacao_Copia = nr_Cotacao_Copia;
	}
	public String getNM_Responsavel() {
		return NM_Responsavel;
	}
	public void setNM_Responsavel(String responsavel) {
		NM_Responsavel = responsavel;
	}
	public String getDM_Frete_Responsabilidade() {
		return DM_Frete_Responsabilidade;
	}
	public void setDM_Frete_Responsabilidade(String frete_Responsabilidade) {
		DM_Frete_Responsabilidade = frete_Responsabilidade;
	}
	public double getVL_Produto() {
		return VL_Produto;
	}
	public void setVL_Produto(double produto) {
		VL_Produto = produto;
	}
    public String getTransit_Time() {
        return transit_Time;
    }
    public void setTransit_Time(String transit_Time) {
        this.transit_Time = transit_Time;
    }
    public String getEMail_Vendedor() {
        return EMail_Vendedor;
    }
    public void setEMail_Vendedor(String mail_Vendedor) {
        EMail_Vendedor = mail_Vendedor;
    }
    public String getNM_Vendedor_Transportadora() {
        return NM_Vendedor_Transportadora;
    }
    public void setNM_Vendedor_Transportadora(String vendedor_Transportadora) {
        NM_Vendedor_Transportadora = vendedor_Transportadora;
    }
    public String getNR_CNPJ_CPF_Transportadora() {
        return NR_CNPJ_CPF_Transportadora;
    }
    public void setNR_CNPJ_CPF_Transportadora(String transportadora) {
        NR_CNPJ_CPF_Transportadora = transportadora;
    }
    public String getNR_Fax_Vendedor() {
        return NR_Fax_Vendedor;
    }
    public void setNR_Fax_Vendedor(String fax_Vendedor) {
        NR_Fax_Vendedor = fax_Vendedor;
    }
    public String getNR_Telefone_Vendedor() {
        return NR_Telefone_Vendedor;
    }
    public void setNR_Telefone_Vendedor(String telefone_Vendedor) {
        NR_Telefone_Vendedor = telefone_Vendedor;
    }
    public String getNM_Fantasia_Transportadora() {
        return NM_Fantasia_Transportadora;
    }
    public void setNM_Fantasia_Transportadora(String fantasia_Transportadora) {
        NM_Fantasia_Transportadora = fantasia_Transportadora;
    }
    public String getNM_Razao_Social_Transportadora() {
        return NM_Razao_Social_Transportadora;
    }
    public void setNM_Razao_Social_Transportadora(
            String razao_Social_Transportadora) {
        NM_Razao_Social_Transportadora = razao_Social_Transportadora;
    }
    public String getOid_Pessoa_Transportadora() {
        return oid_Pessoa_Transportadora;
    }
    public void setOid_Pessoa_Transportadora(String oid_Pessoa_Transportadora) {
        this.oid_Pessoa_Transportadora = oid_Pessoa_Transportadora;
    }
    public double getVL_Excedente_Estadia() {
        return VL_Excedente_Estadia;
    }
    public void setVL_Excedente_Estadia(double excedente_Estadia) {
        VL_Excedente_Estadia = excedente_Estadia;
    }
    public String getEMail() {
        return EMail;
    }
    public void setEMail(String mail) {
        EMail = mail;
    }
    public String getNR_Fax() {
        return NR_Fax;
    }
    public void setNR_Fax(String fax) {
        NR_Fax = fax;
    }
    public String getNR_Telefone() {
        return NR_Telefone;
    }
    public void setNR_Telefone(String telefone) {
        NR_Telefone = telefone;
    }
    public String getNM_Contato() {
        return NM_Contato;
    }
    public void setNM_Contato(String contato) {
        NM_Contato = contato;
    }
    public String getNM_Setor() {
        return NM_Setor;
    }
    public void setNM_Setor(String setor) {
        NM_Setor = setor;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setTX_Observacao(String observacao) {
        TX_Observacao = observacao;
    }
    public String getDT_Cotacao() {
	    FormataDataBean DataFormatada = new FormataDataBean();
	    DataFormatada.setDT_FormataData(DT_Cotacao);
	    DT_Cotacao = DataFormatada.getDT_FormataData();
	    return DT_Cotacao;
    }
    public void setDT_Cotacao(String DT_Cotacao) {
        this.DT_Cotacao = DT_Cotacao;
    }

    public String getNM_Fantasia() {
        return NM_Fantasia;
    }
    public void setNM_Fantasia(String fantasia) {
        NM_Fantasia = fantasia;
    }
	public long getNr_Cotacao() {
        return nr_Cotacao;
    }
    public void setNr_Cotacao(long nr_Cotacao) {
        this.nr_Cotacao = nr_Cotacao;
    }
    public long getOid_Unidade() {
        return oid_Unidade;
    }

    public void setOid_Unidade(long oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }

	public Tabela_Frete_InternacionalBean(){
	    NR_Peso_Inicial=0;
	    NR_Peso_Final=0;
	}

	public String getDM_Origem()
	{
	    return DM_Origem;
	}
	public void setDM_Origem(String DM_Origem)
	{
	    this.DM_Origem = DM_Origem;
	}

	public String getNM_Origem()
	{
	    return NM_Origem;
	}
	public void setNM_Origem(String NM_Origem)
	{
	    this.NM_Origem = NM_Origem;
	}


	public String getDM_Destino()
	{
	    return DM_Destino;
	}
	public void setDM_Destino(String DM_Destino)
	{
	    this.DM_Destino = DM_Destino;
	}

	public String getNM_Destino()
	{
	    return NM_Destino;
	}
	public void setNM_Destino(String NM_Destino)
	{
	    this.NM_Destino = NM_Destino;
	}

	public String getDT_Vigencia()
	{
	    FormataDataBean DataFormatada = new FormataDataBean();
	    DataFormatada.setDT_FormataData(DT_Vigencia);
	    DT_Vigencia = DataFormatada.getDT_FormataData();
	    return DT_Vigencia;
	}
	public void setDT_Vigencia(String DT_Vigencia)
	{
	    this.DT_Vigencia = DT_Vigencia;
	}

	public String getDT_Prazo_Pgto() {
		FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Prazo_Pgto);
        DT_Prazo_Pgto = DataFormatada.getDT_FormataData();
	    return DT_Prazo_Pgto;
	}
	public void setDT_Prazo_Pgto(String DT_Prazo_Pgto) {
		this.DT_Prazo_Pgto = DT_Prazo_Pgto;
	}

	public String getDT_Validade(){
		FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Validade);
        DT_Validade = DataFormatada.getDT_FormataData();
	    return DT_Validade;
	}
	public void setDT_Validade(String DT_Validade){
	    this.DT_Validade = DT_Validade;
	}

	public double getVL_Frete()
	{
	    return VL_Frete;
	}

	public void setVL_Frete(double VL_Frete)
	{
	    this.VL_Frete = VL_Frete;
	}

	public double getVL_Frete_Kg()
	{
	    return VL_Frete_Kg;
	}
	public void setVL_Frete_Kg(double VL_Frete_Kg)
	{
	    this.VL_Frete_Kg = VL_Frete_Kg;
	}

	public double getVL_Taxas()
	{
	    return VL_Taxas;
	}
	public void setVL_Taxas(double VL_Taxas)
	{
	    this.VL_Taxas = VL_Taxas;
	}

	public double getPE_Ad_Valorem()
	{
	    return PE_Ad_Valorem;
	}
	public void setPE_Ad_Valorem(double PE_Ad_Valorem)
	{
	    this.PE_Ad_Valorem = PE_Ad_Valorem;
	}

	public double getVL_Outros1()
	{
	    return VL_Outros1;
	}
	public void setVL_Outros1(double VL_Outros1)
	{
	    this.VL_Outros1 = VL_Outros1;
	}

	public double getVL_Outros2()
	{
	    return VL_Outros2;
	}
	public void setVL_Outros2(double VL_Outros2)
	{
	    this.VL_Outros2 = VL_Outros2;
	}

	public double getVL_Outros5()
	{
	    return VL_Outros5;
	}
	public void setVL_Outros5(double VL_Outros5)
	{
	    this.VL_Outros5 = VL_Outros5;
	}

	public double getVL_Outros4()
	{
	    return VL_Outros4;
	}
	public void setVL_Outros4(double VL_Outros4)
	{
	    this.VL_Outros4 = VL_Outros4;
	}

	public double getVL_Outros3()
	{
	    return VL_Outros3;
	}
	public void setVL_Outros3(double VL_Outros3)
	{
	    this.VL_Outros3 = VL_Outros3;
	}

	public double getVL_Outros6()
	{
	    return VL_Outros6;
	}
	public void setVL_Outros6(double VL_Outros6)
	{
	    this.VL_Outros6 = VL_Outros6;
	}

	public double getVL_Outros7()
	{
	    return VL_Outros7;
	}
	public void setVL_Outros7(double VL_Outros7)
	{
	    this.VL_Outros7 = VL_Outros7;
	}

	public double getVL_Outros8()
	{
	    return VL_Outros8;
	}
	public void setVL_Outros8(double VL_Outros8)
	{
	    this.VL_Outros8 = VL_Outros8;
	}

	public double getVL_Outros9()
	{
	    return VL_Outros9;
	}
	public void setVL_Outros9(double VL_Outros9)
	{
	    this.VL_Outros9 = VL_Outros9;
	}

	public long getOID_Produto()
	{
	    return oid_Produto;
	}
	public void setOID_Produto(long n)
	{
	    this.oid_Produto = n;
	}

	public long getOID_Origem()
	{
	    return oid_Origem;
	}
	public void setOID_Origem(long n)
	{
	    this.oid_Origem = n;
	}

	public long getOID_Destino()
	{
	    return oid_Destino;
	}
	public void setOID_Destino(long n)
	{
	    this.oid_Destino = n;
	}

	public String getNM_Produto()
	{
	    return NM_Produto;
	}
	public void setNM_Produto(String NM_Produto)
	{
	    this.NM_Produto = NM_Produto;
	}


	public String getCD_Produto()
	{
	    return CD_Produto;
	}
	public void setCD_Produto(String CD_Produto)
	{
	    this.CD_Produto = CD_Produto;
	}

	public long getOID_Modal()
	{
	    return OID_Modal;
	}
	public void setOID_Modal(long n)
	{
	    this.OID_Modal = n;
	}
	public String getCD_Modal()
	{
	    return CD_Modal;
	}
	public void setCD_Modal(String CD_Modal)
	{
	    this.CD_Modal = CD_Modal;
	}
	public String getNM_Modal()
	{
	    return NM_Modal;
	}
	public void setNM_Modal(String NM_Modal)
	{
	    this.NM_Modal = NM_Modal;
	}

	public String getOID_Pessoa()
	{
	    return oid_Pessoa;
	}
	public void setOID_Pessoa(String n)
	{
	    this.oid_Pessoa = n;
	}
	public String getNM_Razao_Social()
	{
	    return NM_Razao_Social;
	}
	public void setNM_Razao_Social(String NM_Razao_Social)
	{
	    this.NM_Razao_Social = NM_Razao_Social;
	}
	public void setVL_Frete_Minimo(double VL_Frete_Minimo) {
	    this.VL_Frete_Minimo = VL_Frete_Minimo;
	}
	public double getVL_Frete_Minimo() {
	    return VL_Frete_Minimo;
	}
	public void setPE_Ademe(double PE_Ademe) {
	    this.PE_Ademe = PE_Ademe;
	}
	public double getPE_Ademe() {
	    return PE_Ademe;
	}
	public void setVL_Ademe_Minimo(double VL_Ademe_Minimo) {
	    this.VL_Ademe_Minimo = VL_Ademe_Minimo;
	}
	public double getVL_Ademe_Minimo() {
	    return VL_Ademe_Minimo;
	}
	public void setVL_Ad_Valorem_Minimo(double VL_Ad_Valorem_Minimo) {
	    this.VL_Ad_Valorem_Minimo = VL_Ad_Valorem_Minimo;
	}
	public double getVL_Ad_Valorem_Minimo() {
	    return VL_Ad_Valorem_Minimo;
	}
	public void setVL_Maximo_Nota_Fiscal(double VL_Maximo_Nota_Fiscal) {
	    this.VL_Maximo_Nota_Fiscal = VL_Maximo_Nota_Fiscal;
	}
	public double getVL_Maximo_Nota_Fiscal() {
	    return VL_Maximo_Nota_Fiscal;
	}
	public void setVL_Despacho(double VL_Despacho) {
	    this.VL_Despacho = VL_Despacho;
	}
	public double getVL_Despacho() {
	    return VL_Despacho;
	}
	public void setVL_Frete_Valor_Minimo(double VL_Frete_Valor_Minimo) {
	    this.VL_Frete_Valor_Minimo = VL_Frete_Valor_Minimo;
	}
	public double getVL_Frete_Valor_Minimo() {
	    return VL_Frete_Valor_Minimo;
	}
	public void setDM_ICMS(String DM_ICMS) {
	    this.DM_ICMS = DM_ICMS;
	}
	public String getDM_ICMS() {
	    return DM_ICMS;
	}
	public void setDM_Tipo_Peso(String DM_Tipo_Peso) {
	    this.DM_Tipo_Peso = DM_Tipo_Peso;
	}
	public String getDM_Tipo_Peso() {
	    return DM_Tipo_Peso;
	}
	public void setOID_Pessoa_Redespacho(String OID_Pessoa_Redespacho) {
	    this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
	}
	public String getOID_Pessoa_Redespacho() {
	    return OID_Pessoa_Redespacho;
	}
	public void setOid_Pessoa_Redespacho(String oid_Pessoa_Redespacho) {
	    this.oid_Pessoa_Redespacho = oid_Pessoa_Redespacho;
	}
	public String getOid_Pessoa_Redespacho() {
	    return oid_Pessoa_Redespacho;
	}
	public void setDM_Tipo_Tabela(String DM_Tipo_Tabela) {
	    this.DM_Tipo_Tabela = DM_Tipo_Tabela;
	}
	public String getDM_Tipo_Tabela() {
	    return DM_Tipo_Tabela;
	}
	public void setNR_Prazo_Entrega(long NR_Prazo_Entrega) {
	    this.NR_Prazo_Entrega = NR_Prazo_Entrega;
	}
	public long getNR_Prazo_Entrega() {
	    return NR_Prazo_Entrega;
	}
	public void setDM_Tipo_Pedagio(String DM_Tipo_Pedagio) {
	    this.DM_Tipo_Pedagio = DM_Tipo_Pedagio;
	}
	public String getDM_Tipo_Pedagio() {
	    return DM_Tipo_Pedagio;
	}
	public void setVL_Pedagio(double VL_Pedagio) {
	    this.VL_Pedagio = VL_Pedagio;
	}
	public double getVL_Pedagio() {
	    return VL_Pedagio;
	}
	public void setOID_Empresa(long OID_Empresa) {
	    this.OID_Empresa = OID_Empresa;
	}
	public long getOID_Empresa() {
	    return OID_Empresa;
	}
	public void setNR_Prazo_Faturamento(long NR_Prazo_Faturamento) {
	    this.NR_Prazo_Faturamento = NR_Prazo_Faturamento;
	}
	public long getNR_Prazo_Faturamento() {
	    return NR_Prazo_Faturamento;
	}
	public void setPE_Reentrega(double PE_Reentrega) {
	    this.PE_Reentrega = PE_Reentrega;
	}
	public double getPE_Reentrega() {
	    return PE_Reentrega;
	}
	public void setPE_Devolucao(double PE_Devolucao) {
	    this.PE_Devolucao = PE_Devolucao;
	}
	public double getPE_Devolucao() {
	    return PE_Devolucao;
	}
	public void setDM_Unidade(String DM_Unidade) {
	    this.DM_Unidade = DM_Unidade;
	}
	public String getDM_Unidade() {
	    return DM_Unidade;
	}
	public void setPE_Refaturamento(double PE_Refaturamento) {
	    this.PE_Refaturamento = PE_Refaturamento;
	}
	public double getPE_Refaturamento() {
	    return PE_Refaturamento;
	}
	public double getVL_Minimo_Nota_Fiscal() {
	    return VL_Minimo_Nota_Fiscal;
	}
	public void setVL_Minimo_Nota_Fiscal(double VL_Minimo_Nota_Fiscal) {
	    this.VL_Minimo_Nota_Fiscal = VL_Minimo_Nota_Fiscal;
	}
	public long getNR_Peso_Minimo() {
	    return NR_Peso_Minimo;
	}
	public void setNR_Peso_Minimo(long NR_Peso_Minimo) {
	    this.NR_Peso_Minimo = NR_Peso_Minimo;
	}
	public void setNM_Tipo_Tabela(String NM_Tipo_Tabela) {
	    this.NM_Tipo_Tabela = NM_Tipo_Tabela;
	}
	public String getNM_Tipo_Tabela() {
	    return NM_Tipo_Tabela;
	}

	/*
	 *---------------- Bloco Padrão para Todas Classes ------------------
	 */
	public String getUsuario_Stamp()
	{
	    return Usuario_Stamp;
	}
	public void setUsuario_Stamp(String Usuario_Stamp)
	{
	    this.Usuario_Stamp = Usuario_Stamp;
	}
	public String getDt_Stamp()
	{
	    return Dt_Stamp;
	}
	public void setDt_Stamp(String Dt_Stamp)
	{
	    this.Dt_Stamp = Dt_Stamp;
	}
	public String getDm_Stamp()
	{
	    return Dm_Stamp;
	}
	public void setDm_Stamp(String Dm_Stamp)
	{
	    this.Dm_Stamp = Dm_Stamp;
	}
	public long getOID()
	{
	    return oid;
	}
	public void setOID(long n)
	{
	    this.oid = n;
	}
    public double getVL_Faixa1() {
        return this.VL_Faixa1;
    }
    public void setVL_Faixa1(double faixa1) {
        this.VL_Faixa1 = faixa1;
    }
    public double getVL_Faixa2() {
        return this.VL_Faixa2;
    }
    public void setVL_Faixa2(double faixa2) {
        this.VL_Faixa2 = faixa2;
    }
    public double getVL_Faixa3() {
        return this.VL_Faixa3;
    }
    public void setVL_Faixa3(double faixa3) {
        this.VL_Faixa3 = faixa3;
    }
    public double getVL_Faixa4() {
        return this.VL_Faixa4;
    }
    public void setVL_Faixa4(double faixa4) {
        this.VL_Faixa4 = faixa4;
    }
    public double getVL_Faixa5() {
        return this.VL_Faixa5;
    }
    public void setVL_Faixa5(double faixa5) {
        this.VL_Faixa5 = faixa5;
    }

	public void insert_Internacional() throws Exception{

	    Connection conn = null;
	    String sql = null;
	    boolean updateNRCotacao = false;

		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		try{
			Statement stmt = conn.createStatement();

			sql = "SELECT MAX(OID_Tabela_Frete) FROM Tabelas_Fretes";

			ResultSet cursor = stmt.executeQuery(sql);

			while (cursor.next()){
				long oid = cursor.getLong(1);
				setOID(oid + 1);
			}

			if(getNr_Cotacao()<=0){

			    sql =  " SELECT NR_Proxima_cotacao ";
			    sql += " FROM Parametros_Filiais ";
			    sql += " WHERE OID_Unidade = " + getOid_Unidade();

//			    // System.out.println("Tab_Frete_Internacional.insert() - sql1 = "+sql);

			    cursor = null;
			    cursor = stmt.executeQuery(sql);

			    while (cursor.next()){
			        setNr_Cotacao(cursor.getLong(1));
			        updateNRCotacao = true;
			    }
			}
			cursor.close();
			stmt.close();
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Tabelas_Fretes (OID_Tabela_Frete, OID_Produto, OID_Modal, OID_Pessoa, " + //4
				    "OID_Origem, OID_Destino, DM_Origem, NM_Origem, DM_Destino, NM_Destino, NR_Peso_Inicial, " + //7
				    "NR_Peso_Final, DT_Vigencia, DT_Validade, VL_Frete, VL_Frete_Minimo, PE_Ademe, " + //6
				    "VL_Ademe_Minimo, PE_Ad_Valorem, VL_Ad_Valorem_Minimo, VL_Maximo_Nota_Fiscal, VL_Taxas, " + //5
				    "VL_Despacho, VL_Frete_Valor_Minimo, DM_ICMS, DM_Tipo_Tabela, DM_Tipo_Peso, VL_Outros7, " + //6
				    "VL_Outros8,  VL_Outros9, Dt_Stamp, Usuario_Stamp, Dm_Stamp, OID_Pessoa_Redespacho, " + //6
				    "NR_Prazo_Entrega, DM_Tipo_Pedagio, VL_Pedagio, OID_Empresa, NR_Prazo_Faturamento, " + //5
				    "PE_Reentrega, PE_Devolucao, PE_Refaturamento, VL_Minimo_Nota_Fiscal, NR_Peso_Minimo, " + //5
				    "nr_cotacao, oid_unidade, dt_cotacao, tx_observacao, nm_contato, nm_setor, nr_telefone, " + //7
				    "nr_fax, email, oid_pessoa_transportadora, nm_vendedor, nr_telefone_vendedor, " + //5
				    "nr_fax_vendedor, email_vendedor, vl_excedente_estadia, transit_time, vl_produto, " +//5
				    "DM_Frete_Responsabilidade, NM_Responsavel, dias_prazo_pgto, dm_tipo_prazo_pgto, " +//4
				    "NM_Origem_Editado, NM_Destino_Editado, NM_Produto_Editado, DM_Aplicacao, DM_RCTR_C, DM_RCTR_VI, DM_RCTR_DC, " +//7
				    "pe_comissao, pe_frete_E, pe_frete_I, vl_comissao, vl_frete_E, vl_frete_I) "); //3 = 75

		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + //40
				    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); //35

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());

			pstmt.setLong(1, getOID());
			pstmt.setLong(2, getOID_Produto());
			pstmt.setLong(3, getOID_Modal());
			pstmt.setString(4, getOID_Pessoa());
			pstmt.setLong(5, getOID_Origem());
			pstmt.setLong(6, getOID_Destino());
			pstmt.setString(7, getDM_Origem());
			pstmt.setString(8, getNM_Origem());
			pstmt.setString(9, getDM_Destino());
			pstmt.setString(10, getNM_Destino());
			pstmt.setDouble(11, getNR_Peso_Inicial());
			pstmt.setDouble(12, getNR_Peso_Final());
			pstmt.setString(13, this.DT_Vigencia);
			pstmt.setString(14, this.DT_Validade);
			pstmt.setDouble(15, getVL_Frete());
			pstmt.setDouble(16, getVL_Frete_Minimo());
			pstmt.setDouble(17, getPE_Ademe());
			pstmt.setDouble(18, getVL_Ademe_Minimo());
			pstmt.setDouble(19, getPE_Ad_Valorem());
			pstmt.setDouble(20, getVL_Ad_Valorem_Minimo());
			pstmt.setDouble(21, getVL_Maximo_Nota_Fiscal());
			pstmt.setDouble(22, getVL_Taxas());
			pstmt.setDouble(23, getVL_Despacho());
			pstmt.setDouble(24, getVL_Frete_Valor_Minimo());
			pstmt.setString(25, getDM_ICMS());
			pstmt.setString(26, getDM_Tipo_Tabela());
			pstmt.setString(27, getDM_Tipo_Peso());
			pstmt.setDouble(28, getVL_Outros7());
			pstmt.setDouble(29, getVL_Outros8());
			pstmt.setDouble(30, getVL_Outros9());
			pstmt.setString(31, getDt_Stamp());
			pstmt.setString(32, getUsuario_Stamp());
			pstmt.setString(33, getDm_Stamp());
			pstmt.setString(34, getOID_Pessoa_Redespacho());
			pstmt.setLong(35, getNR_Prazo_Entrega());
			pstmt.setString(36, getDM_Tipo_Pedagio());
			pstmt.setDouble(37, getVL_Pedagio());
			pstmt.setLong(38, getOID_Empresa());
			pstmt.setLong(39, getNR_Prazo_Faturamento());
			pstmt.setDouble(40, getPE_Reentrega());
			pstmt.setDouble(41, getPE_Devolucao());
			pstmt.setDouble(42, getPE_Refaturamento());
			pstmt.setDouble(43, getVL_Minimo_Nota_Fiscal());
			pstmt.setLong(44, getNR_Peso_Minimo());
			pstmt.setLong(45, getNr_Cotacao());
			pstmt.setLong(46, getOid_Unidade());
			pstmt.setString(47, this.DT_Cotacao);
			pstmt.setString(48, getTX_Observacao());
			pstmt.setString(49, getNM_Contato());
			pstmt.setString(50, getNM_Setor());
			pstmt.setString(51, getNR_Telefone());
			pstmt.setString(52, getNR_Fax());
			pstmt.setString(53, getEMail());
			pstmt.setString(54, getOid_Pessoa_Transportadora());
			pstmt.setString(55, getNM_Vendedor_Transportadora());
			pstmt.setString(56, getNR_Telefone_Vendedor());
			pstmt.setString(57, getNR_Fax_Vendedor());
			pstmt.setString(58, getEMail_Vendedor());
			pstmt.setDouble(59, getVL_Excedente_Estadia());
			pstmt.setString(60, getTransit_Time());
			pstmt.setDouble(61, getVL_Produto());
			pstmt.setString(62, getDM_Frete_Responsabilidade());
			pstmt.setString(63, getNM_Responsavel());
			pstmt.setString(64, this.DT_Prazo_Pgto);
			pstmt.setString(65, getDM_Tipo_Prazo_Pgto());
			pstmt.setString(66, getNM_Origem_Editado());
			pstmt.setString(67, getNM_Destino_Editado());
			pstmt.setString(68, getNM_Produto_Editado());
            pstmt.setString(69, "CI");
            pstmt.setString(70, getDM_RCTR_C());
            pstmt.setString(71, getDM_RCTR_VI());
            pstmt.setString(72, getDM_RCTR_DC());
            pstmt.setDouble(73, getPE_Comissao());
            pstmt.setDouble(74, getPE_Frete_E());
            pstmt.setDouble(75, getPE_Frete_I());
            pstmt.setDouble(76, getVL_Comissao());
            pstmt.setDouble(77, getVL_Frete_E());
            pstmt.setDouble(78, getVL_Frete_I());

//			// System.out.print("Tab_Frete_InterBean().insert_Internacional() sql = "+pstmt.toString());

			pstmt.executeUpdate();

			if(updateNRCotacao){
				sql = "update Parametros_Filiais set NR_Proxima_cotacao = " + (getNr_Cotacao() + 1) +
			          " WHERE OID_Unidade = " + getOid_Unidade();

//				// System.out.println("Tab_Frete_Internacional.insert() - sql2 = "+sql);

				pstmt = null;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}

		}
		catch (Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}


		try{
		    sql = "UPDATE Tabelas_Fretes SET tx_observacao=? WHERE nr_cotacao=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, getTX_Observacao());
			pstmt.setLong(2, getNr_Cotacao());

//			// System.out.println("Tab_Frete_Inter.update_Inter() sql = "+sql);

			pstmt.executeUpdate();
		}
		catch(Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		try{
			conn.commit();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}





	public void update_Internacional() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tabela_Frete do DSN
			// o NM_Tabela_Frete de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o update.
		*/
		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE Tabelas_Fretes SET OID_Produto=?, OID_Modal=?, OID_Origem=?, OID_Destino=?, " +
				    "DM_Origem=?, NM_Origem=?, DM_Destino=?, NM_Destino=?, NR_Peso_Inicial=?, NR_Peso_Final=?, " +
				    "DT_Vigencia=?, DT_Validade=?, VL_Frete=?, VL_Frete_Minimo=?, VL_Taxas=?, PE_Ad_Valorem=?, " +
				    "VL_Ad_Valorem_Minimo=?, PE_Ademe=?, VL_Ademe_Minimo=?, VL_Maximo_Nota_Fiscal=?, " +
				    "VL_Despacho=?, VL_Frete_Valor_Minimo=?,  DM_ICMS=?,  DM_Tipo_Tabela=?,  DM_Tipo_Peso=?, " +
				    "oid_pessoa_redespacho=?, NR_Prazo_Entrega=? , DM_Tipo_Pedagio=?, VL_Pedagio=?, " +
				    "NR_Prazo_Faturamento=?, PE_Reentrega=?, PE_Devolucao=?, OID_Empresa=?, PE_Refaturamento=?, " +
				    "VL_Minimo_Nota_Fiscal=?, NR_Peso_Minimo=?, dt_cotacao=?, nm_contato=?, " +
				    "nm_setor=?, nr_telefone=?, nr_fax=?, email=?, oid_pessoa_transportadora=?, nm_vendedor=?, " +
				    "nr_telefone_vendedor=?, nr_fax_vendedor=?, email_vendedor=? , vl_excedente_estadia=?, " +
				    "transit_time=?, vl_produto=?, DM_Frete_Responsabilidade=?, NM_Responsavel=?, " +
				    "dias_prazo_pgto=?, dm_tipo_prazo_pgto=?, NM_Origem_Editado=?, NM_Destino_Editado=?, NM_Produto_Editado=?, " +
				    "DM_RCTR_C=?, DM_RCTR_VI=?, DM_RCTR_DC=?, pe_comissao=?, pe_frete_E=?, pe_frete_I=?, vl_comissao=?, vl_frete_E=?, vl_frete_I=? " );

		buff.append("WHERE OID_Tabela_Frete=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());

			pstmt.setLong(1, getOID_Produto());
			pstmt.setLong(2, getOID_Modal());
			pstmt.setLong(3, getOID_Origem());
			pstmt.setLong(4, getOID_Destino());
			pstmt.setString(5, getDM_Origem());
			pstmt.setString(6, getNM_Origem());
			pstmt.setString(7, getDM_Destino());
			pstmt.setString(8, getNM_Destino());
			pstmt.setDouble(9, getNR_Peso_Inicial());
			pstmt.setDouble(10, getNR_Peso_Final());
			pstmt.setString(11, this.DT_Vigencia);
			pstmt.setString(12, this.DT_Validade);
			pstmt.setDouble(13, getVL_Frete());
			pstmt.setDouble(14, getVL_Frete_Minimo());
			pstmt.setDouble(15, getVL_Taxas());
			pstmt.setDouble(16, getPE_Ad_Valorem());
			pstmt.setDouble(17, getVL_Ad_Valorem_Minimo());
			pstmt.setDouble(18, getPE_Ademe());
			pstmt.setDouble(19, getVL_Ademe_Minimo());
			pstmt.setDouble(20, getVL_Maximo_Nota_Fiscal());
			pstmt.setDouble(21, getVL_Despacho());
			pstmt.setDouble(22, getVL_Frete_Valor_Minimo());
			pstmt.setString(23, getDM_ICMS());
			pstmt.setString(24, getDM_Tipo_Tabela());
			pstmt.setString(25, getDM_Tipo_Peso());
			pstmt.setString(26, getOID_Pessoa_Redespacho());
			pstmt.setLong(27, getNR_Prazo_Entrega());
			pstmt.setString(28, getDM_Tipo_Pedagio());
			pstmt.setDouble(29, getVL_Pedagio());
			pstmt.setLong(30, getNR_Prazo_Faturamento());
			pstmt.setDouble(31, getPE_Reentrega());
			pstmt.setDouble(32, getPE_Devolucao());
			pstmt.setLong(33, getOID_Empresa());
			pstmt.setDouble(34, getPE_Refaturamento());
			pstmt.setDouble(35, getVL_Minimo_Nota_Fiscal());
			pstmt.setLong(36, getNR_Peso_Minimo());
			pstmt.setString(37, this.DT_Cotacao);
			pstmt.setString(38, getNM_Contato());
			pstmt.setString(39, getNM_Setor());
			pstmt.setString(40, getNR_Telefone());
			pstmt.setString(41, getNR_Fax());
			pstmt.setString(42, getEMail());

			pstmt.setString(43, getOid_Pessoa_Transportadora());
			pstmt.setString(44, getNM_Vendedor_Transportadora());
			pstmt.setString(45, getNR_Telefone_Vendedor());
			pstmt.setString(46, getNR_Fax_Vendedor());
			pstmt.setString(47, getEMail_Vendedor());
			pstmt.setDouble(48, getVL_Excedente_Estadia());
			pstmt.setString(49, getTransit_Time());
			pstmt.setDouble(50, getVL_Produto());
			pstmt.setString(51, getDM_Frete_Responsabilidade());
			pstmt.setString(52, getNM_Responsavel());
			pstmt.setString(53, this.DT_Prazo_Pgto);
			pstmt.setString(54, getDM_Tipo_Prazo_Pgto());

			pstmt.setString(55, getNM_Origem_Editado());
			pstmt.setString(56, getNM_Destino_Editado());
			pstmt.setString(57, getNM_Produto_Editado());
			
			pstmt.setString(58, getDM_RCTR_C());
            pstmt.setString(59, getDM_RCTR_VI());
            pstmt.setString(60, getDM_RCTR_DC());
            
            pstmt.setDouble(61, getPE_Comissao());
            pstmt.setDouble(62, getPE_Frete_E());
            pstmt.setDouble(63, getPE_Frete_I());
            pstmt.setDouble(64, getVL_Comissao());
            pstmt.setDouble(65, getVL_Frete_E());
            pstmt.setDouble(66, getVL_Frete_I());

			pstmt.setLong(67, getOID());

//			// System.out.println("Tab_Frete_Inter.update_Inter() sql = "+pstmt.toString());

			pstmt.executeUpdate();
		}
		catch(Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		String sql = "UPDATE Tabelas_Fretes SET tx_observacao=? WHERE nr_cotacao=?";

		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, getTX_Observacao());
			pstmt.setLong(2, getNr_Cotacao());

//			// System.out.println("Tab_Frete_Inter.update_Inter() sql = "+sql);

			pstmt.executeUpdate();
		}
		catch(Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		try
		{
			conn.commit();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}





	public void delete() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tabela_Frete do DSN
			// o NM_Tabela_Frete de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o DELETE.
		*/
		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM Tabelas_Fretes ");
		buff.append("WHERE OID_Tabela_Frete=?");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setLong(1, getOID());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conexão.
		*/
		try
		{
			conn.commit();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	public static final Tabela_Frete_InternacionalBean getByOID(long oid)
	throws Exception {
		Connection conn = OracleConnection2.getWEB();
		try {
			conn.setAutoCommit(false);
			Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
			try {
				String sql =
				    " select TF.OID_Tabela_Frete, " +
				    "        TF.OID_Produto, " +
				    "        TF.OID_Modal, " +
				    "        TF.OID_Pessoa, " +
				    "        NR_Peso_Inicial, " +
					"        NR_Peso_Final, " +
					"        VL_Frete, " +
					"        VL_Frete_Kg, " +
					"        VL_Taxas, " +
					"        PE_Ad_Valorem, " +
					"        VL_Outros1, " +
					"        VL_Outros2, " +
					"        VL_Outros3, " +
					"        VL_Outros4, " +
					"        VL_Outros5, " +
					"        VL_Outros6, " +
					"        VL_Outros7, " +
					"        VL_Outros8, " +
					"        VL_Outros9, " +
					"        TF.Dt_Stamp, " +
					"        TF.Usuario_Stamp, " +
					"        TF.Dm_Stamp, " +
					"        Pessoas.NM_Razao_Social, " +
					"        Produtos.CD_Produto, " +
					"        Produtos.NM_Produto, " +
					"        Modal.CD_Modal, " +
					"        Modal.NM_Modal, " +
					"        TF.OID_Origem, " +
					"        TF.OID_Destino, " +
					"        TF.NM_Origem, " +
					"        TF.NM_Destino, " +
					"        TF.NM_Origem_Editado, " +
					"        TF.NM_Destino_Editado, " +
					"        TF.NM_Produto_Editado, " +
					"        TF.DM_Origem, " +
					"        TF.DM_Destino, " +
					"        TF.DT_Vigencia, " +
					"        TF.DT_Validade, " +
					"        Produtos.DM_Unidade, " +
					"        TF.dt_cotacao, " +
					"        TF.tx_observacao, " +
					"        TF.nm_contato, " +
					"        TF.nm_setor, " +
					"        TF.nr_telefone, " +
					"        TF.nr_fax, " +
					"        TF.email, " +
					"        TF.oid_pessoa_transportadora, " +
					"        TF.nm_vendedor, " +
					"        TF.nr_telefone_vendedor, " +
					"        TF.nr_fax_vendedor, " +
					"        TF.email_vendedor, " +
					"        TF.vl_excedente_estadia, " +
					"        TF.transit_time, " +
					"        TF.vl_produto, " +
					"        TF.DM_Frete_Responsabilidade, " +
					"        TF.NM_Responsavel, " +
					"        TF.dias_prazo_pgto, " +
					"        TF.VL_Faixa1, " +
					"        TF.VL_Faixa2, " +
					"        TF.VL_Faixa3, " +
					"        TF.VL_Faixa4, " +
					"        TF.VL_Faixa5, " +
					"        TF.PE_Reentrega," +
					"        TF.PE_Devolucao, " +
					"        TF.DM_Tipo_Peso " +
					" from Tabelas_Fretes TF, Pessoas, Modal, Produtos " +
					" where TF.OID_Produto = Produtos.OID_Produto " +
					"   and TF.OID_Modal = Modal.OID_Modal " +
					"   and TF.OID_Pessoa = Pessoas.OID_Pessoa AND TF.OID_Tabela_Frete = " + oid;
				Statement stmt = conn.createStatement();
				try {
					ResultSet cursor = stmt.executeQuery(sql);
					try {
						while (cursor.next()){
							p.setOID(cursor.getLong("OID_Tabela_Frete"));
							p.setOID_Produto(cursor.getLong("OID_Produto"));
							p.setOID_Modal(cursor.getLong("OID_Modal"));
							p.setOID_Pessoa(cursor.getString("OID_Pessoa"));
							p.setNR_Peso_Inicial(cursor.getDouble("NR_Peso_Inicial"));
							p.setNR_Peso_Final(cursor.getDouble("NR_Peso_Final"));
							p.setVL_Frete(cursor.getDouble("VL_Frete"));
							p.setVL_Frete_Kg(cursor.getDouble("VL_Frete_Kg"));
							p.setVL_Taxas(cursor.getDouble("VL_Taxas"));
							p.setPE_Ad_Valorem(cursor.getDouble("PE_Ad_Valorem"));
							p.setVL_Outros1(cursor.getDouble("VL_Outros1"));
							p.setVL_Outros2(cursor.getDouble("VL_Outros2"));
							p.setVL_Outros3(cursor.getDouble("VL_Outros3"));
							p.setVL_Outros4(cursor.getDouble("VL_Outros4"));
							p.setVL_Outros5(cursor.getDouble("VL_Outros5"));
							p.setVL_Outros8(cursor.getDouble("VL_Outros6"));
							p.setVL_Outros7(cursor.getDouble("VL_Outros7"));
							p.setVL_Outros6(cursor.getDouble("VL_Outros8"));
							p.setVL_Outros9(cursor.getDouble("VL_Outros9"));
							p.setDt_Stamp(cursor.getString("Dt_Stamp"));
							p.setUsuario_Stamp(cursor.getString("Usuario_Stamp"));
							p.setDm_Stamp(cursor.getString("Dm_Stamp"));
							p.setNM_Razao_Social(cursor.getString("NM_Razao_Social"));
							p.setCD_Produto(cursor.getString("CD_Produto"));
							p.setNM_Produto(cursor.getString("NM_Produto"));
							p.setCD_Modal(cursor.getString("CD_Modal"));
							p.setNM_Modal(cursor.getString("NM_Modal"));
							p.setOID_Origem(cursor.getLong("OID_Origem"));
							p.setOID_Destino(cursor.getLong("OID_Destino"));
							p.setNM_Origem(cursor.getString("NM_Origem"));
							p.setNM_Destino(cursor.getString("NM_Destino"));
							p.setDM_Origem(cursor.getString("DM_Origem"));
							p.setDM_Destino(cursor.getString("DM_Destino"));
							p.setDT_Vigencia(cursor.getString("DT_Vigencia"));
							p.setDT_Validade(cursor.getString("DT_Validade"));
							p.setPE_Reentrega(cursor.getDouble("PE_Reentrega"));
							p.setPE_Devolucao(cursor.getDouble("PE_Devolucao"));
							p.setDM_Unidade(cursor.getString("DM_Unidade"));
							p.setDT_Cotacao(cursor.getString("DT_Cotacao"));
							p.setTX_Observacao(cursor.getString("tx_observacao"));
							p.setNM_Contato(cursor.getString("nm_contato"));
							p.setNM_Setor(cursor.getString("nm_setor"));
							p.setNR_Telefone(cursor.getString("nr_telefone"));
							p.setNR_Fax(cursor.getString("nr_fax"));
							p.setEMail(cursor.getString("email"));
							p.setOid_Pessoa_Transportadora(cursor.getString("oid_pessoa_transportadora"));
							p.setNM_Vendedor_Transportadora(cursor.getString("nm_vendedor"));
							p.setNR_Telefone_Vendedor(cursor.getString("nr_telefone_vendedor"));
							p.setNR_Fax_Vendedor(cursor.getString("nr_fax_vendedor"));
							p.setEMail_Vendedor(cursor.getString("email_vendedor"));
							p.setVL_Excedente_Estadia(cursor.getDouble("vl_excedente_estadia"));
							p.setTransit_Time(cursor.getString("transit_time"));
							p.setVL_Produto(cursor.getDouble("vl_produto"));
							p.setDM_Frete_Responsabilidade(cursor.getString("DM_Frete_Responsabilidade"));
							p.setNM_Responsavel(cursor.getString("NM_Responsavel"));
							p.setDT_Prazo_Pgto(cursor.getString("dias_prazo_pgto"));
							p.setVL_Faixa1(cursor.getDouble("VL_Faixa1"));
							p.setVL_Faixa1(cursor.getDouble("VL_Faixa2"));
							p.setVL_Faixa1(cursor.getDouble("VL_Faixa3"));
							p.setVL_Faixa1(cursor.getDouble("VL_Faixa4"));
							p.setVL_Faixa1(cursor.getDouble("VL_Faixa5"));
							p.setDM_Tipo_Peso(cursor.getString("DM_Tipo_Peso"));

							p.setNM_Origem_Editado(cursor.getString("NM_Origem_Editado"));
							p.setNM_Destino_Editado(cursor.getString("NM_Destino_Editado"));
							p.setNM_Produto_Editado(cursor.getString("NM_Produto_Editado"));
						}
						return p;
					} finally {
					    cursor.close();
					}
				} finally {
				    stmt.close();
				}
			} finally {
			    conn.close();
			}
		} catch (Exception e){
			throw new Excecoes(e.getMessage(), e, Tabela_Frete_InternacionalBean.class.getName(), "getByOID(long oid)");
		}
	}

	public static final Tabela_Frete_InternacionalBean getByOID_Internacional(long oid) throws Exception{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tabela_Frete do DSN
			// o NM_Tabela_Frete de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT TF.OID_Tabela_Frete, TF.OID_Produto, " +
					    "TF.OID_Modal, TF.OID_Pessoa, NR_Peso_Inicial, NR_Peso_Final, " +
					    "VL_Frete, VL_Frete_Minimo, VL_Taxas, PE_Ad_Valorem, VL_Ad_Valorem_Minimo, PE_Ademe, " +
					    "VL_Ademe_Minimo, VL_Maximo_Nota_Fiscal, VL_Despacho, VL_Frete_Valor_Minimo,  DM_ICMS, " +
					    "DM_Tipo_Tabela,  DM_Tipo_Peso,  VL_Outros9, TF.Dt_Stamp, " +
					    "TF.Usuario_Stamp, TF.Dm_Stamp, Pessoas.NM_Razao_Social, " +
					    "Produtos.CD_Produto, Produtos.NM_Produto , Modal.CD_Modal, Modal.NM_Modal, " +
					    "TF.OID_Origem, TF.OID_Destino , TF.NM_Origem, " +
					    "TF.NM_Destino, TF.DM_Origem, TF.DM_Destino, " +
					    "TF.DT_Vigencia, TF.DT_Validade, OID_Pessoa_Redespacho, " +
					    "NR_Prazo_Entrega, DM_Tipo_Pedagio, VL_Pedagio, TF.OID_Empresa, " +
					    "TF.NR_Prazo_Faturamento, TF.PE_Reentrega, " +
					    "TF.PE_Devolucao , Produtos.DM_Unidade, " +
					    "TF.PE_Refaturamento, TF.VL_Minimo_Nota_Fiscal, " +
					    "TF.NR_Peso_Minimo, TF.nr_cotacao, TF.oid_unidade, Pessoas.NM_Fantasia, TF.dt_cotacao, " +
					    "TF.tx_observacao, TF.nm_contato, TF.nm_setor, TF.nr_telefone, TF.nr_fax, TF.email, " +
					    "TF.oid_pessoa_transportadora, TF.nm_vendedor, TF.nr_telefone_vendedor, " +
					    "TF.nr_fax_vendedor, TF.email_vendedor, TF.vl_excedente_estadia, TF.transit_time, " +
					    "TF.vl_produto, TF.DM_Frete_Responsabilidade, TF.NM_Responsavel, TF.dias_prazo_pgto, " +
					    "TF.dm_tipo_prazo_pgto, TF.NM_Origem_Editado, TF.NM_Destino_Editado, TF.NM_Produto_Editado, " +
					    " TF.DM_RCTR_C, TF.DM_RCTR_VI, TF.DM_RCTR_DC, TF.pe_comissao, TF.pe_frete_E, TF.pe_frete_I, TF.vl_comissao, TF.vl_frete_E, TF.vl_frete_I ");

			buff.append("FROM Tabelas_Fretes TF, Pessoas, Modal, Produtos ");

			buff.append("WHERE TF.OID_Produto = Produtos.OID_Produto " +
					    "AND TF.OID_Modal = Modal.OID_Modal " +
					    "AND TF.OID_Pessoa = Pessoas.OID_Pessoa " +
					    "AND TF.OID_Tabela_Frete = " + oid);

// System.out.println("Tab_Frete_Internacional.getByOID_Internacional() sql - "+buff.toString());

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

            Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

			while (cursor.next())
			{
				p.setOID(cursor.getLong(1));
				p.setOID_Produto(cursor.getLong(2));
				p.setOID_Modal(cursor.getLong(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setNR_Peso_Inicial(cursor.getDouble(5));
				p.setNR_Peso_Final(cursor.getDouble(6));
				p.setVL_Frete(cursor.getDouble(7));
				p.setVL_Frete_Minimo(cursor.getDouble(8));
				p.setVL_Taxas(cursor.getDouble(9));
				p.setPE_Ad_Valorem(cursor.getDouble(10));
				p.setVL_Ad_Valorem_Minimo(cursor.getDouble(11));
				p.setPE_Ademe(cursor.getDouble(12));
				p.setVL_Ademe_Minimo(cursor.getDouble(13));
				p.setVL_Maximo_Nota_Fiscal(cursor.getDouble(14));
				p.setVL_Despacho(cursor.getDouble(15));
				p.setVL_Frete_Valor_Minimo(cursor.getDouble(16));
				p.setDM_ICMS(cursor.getString(17));
				p.setDM_Tipo_Tabela(cursor.getString(18));
				p.setDM_Tipo_Peso(cursor.getString(19));
				p.setVL_Outros9(cursor.getDouble(20));
				p.setDt_Stamp(cursor.getString(21));
				p.setUsuario_Stamp(cursor.getString(22));
				p.setDm_Stamp(cursor.getString(23));
				p.setNM_Razao_Social(cursor.getString(24));
				p.setCD_Produto(cursor.getString(25));
				p.setNM_Produto(cursor.getString(26));
				p.setCD_Modal(cursor.getString(27));
				p.setNM_Modal(cursor.getString(28));
				p.setOID_Origem(cursor.getLong(29));
				p.setOID_Destino(cursor.getLong(30));
				p.setNM_Origem(cursor.getString(31));
				p.setNM_Destino(cursor.getString(32));
				p.setDM_Origem(cursor.getString(33));
				p.setDM_Destino(cursor.getString(34));
				p.setDT_Vigencia(cursor.getString(35));
				p.setDT_Validade(cursor.getString(36));
				p.setOID_Pessoa_Redespacho(cursor.getString(37));
				p.setNR_Prazo_Entrega(cursor.getLong(38));
				p.setDM_Tipo_Pedagio(cursor.getString(39));
				p.setVL_Pedagio(cursor.getDouble(40));
				p.setOID_Empresa(cursor.getLong(41));
				p.setNR_Prazo_Faturamento(cursor.getLong(42));
				p.setPE_Reentrega(cursor.getDouble(43));
				p.setPE_Devolucao(cursor.getDouble(44));
				p.setDM_Unidade(cursor.getString(45));
				p.setPE_Refaturamento(cursor.getDouble(46));
				p.setVL_Minimo_Nota_Fiscal(cursor.getDouble(47));
				p.setNR_Peso_Minimo(cursor.getLong(48));

				p.setNr_Cotacao(cursor.getLong(49));
				p.setOid_Unidade(cursor.getLong(50));
				p.setNM_Fantasia(cursor.getString(51));
				p.setDT_Cotacao(cursor.getString(52));
				p.setTX_Observacao(cursor.getString(53));
				p.setNM_Contato(cursor.getString(54));
				p.setNM_Setor(cursor.getString(55));
				p.setNR_Telefone(cursor.getString(56));
				p.setNR_Fax(cursor.getString(57));
				p.setEMail(cursor.getString(58));

				p.setOid_Pessoa_Transportadora(cursor.getString(59));
				p.setNM_Vendedor_Transportadora(cursor.getString(60));
				p.setNR_Telefone_Vendedor(cursor.getString(61));
				p.setNR_Fax_Vendedor(cursor.getString(62));
				p.setEMail_Vendedor(cursor.getString(63));
				p.setVL_Excedente_Estadia(cursor.getDouble(64));
				p.setTransit_Time(cursor.getString(65));
				p.setVL_Produto(cursor.getDouble(66));
				p.setDM_Frete_Responsabilidade(cursor.getString(67));
				p.setNM_Responsavel(cursor.getString(68));
				p.setDT_Prazo_Pgto(cursor.getString(69));
				p.setDM_Tipo_Prazo_Pgto(cursor.getString(70));

				p.setNM_Origem_Editado(cursor.getString(71));
				p.setNM_Destino_Editado(cursor.getString(72));
				p.setNM_Produto_Editado(cursor.getString(73));
				
				p.setDM_RCTR_C(cursor.getString(74));
				p.setDM_RCTR_VI(cursor.getString(75));
				p.setDM_RCTR_DC(cursor.getString(76));
				
				p.setPE_Comissao(cursor.getDouble(77));
				p.setPE_Frete_E(cursor.getDouble(78));
				p.setPE_Frete_I(cursor.getDouble(79));
				p.setVL_Comissao(cursor.getDouble(80));
				p.setVL_Frete_E(cursor.getDouble(81));
				p.setVL_Frete_I(cursor.getDouble(82));

			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return p;
	}


	public static final List getAll(String oid_Unidade, String NR_CNPJ_CPF, long NR_Cotacao, String DT_Cotacao, String DT_Cotacao_Final)
		throws Exception
	{
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o nome do DSN
			// o nome de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Tabela_Fretes_Lista = new ArrayList();
		try{
			StringBuffer buff = new StringBuffer();

			buff.append("SELECT TF.OID_Tabela_Frete, TF.OID_Produto, TF.OID_Modal, TF.OID_Pessoa, " +
						"NR_Peso_Inicial, NR_Peso_Final, VL_Frete, VL_Frete_Kg, VL_Taxas, PE_Ad_Valorem, " +
						"VL_Outros1, VL_Outros2, VL_Outros3, VL_Outros4, VL_Outros5, VL_Outros6,  VL_Outros7, " +
						"VL_Outros8,  VL_Outros9, TF.Dt_Stamp, TF.Usuario_Stamp, TF.Dm_Stamp, " +
						"Pessoas.NM_Razao_Social, Produtos.CD_Produto, Produtos.NM_Produto, Modal.CD_Modal, " +
						"Modal.NM_Modal, TF.OID_Origem, TF.OID_Destino , TF.NM_Origem, TF.NM_Destino, " +
						"TF.DM_Tipo_Tabela, TF.nr_cotacao, TF.oid_unidade, Pessoas.NM_Fantasia, TF.tx_observacao, " +
						"TF.nm_contato, TF.nm_setor, TF.nr_telefone, TF.nr_fax, TF.email, " +
						"TF.oid_pessoa_transportadora, TF.nm_vendedor, TF.nr_telefone_vendedor, " +
					    "TF.nr_fax_vendedor, TF.email_vendedor, TF.vl_excedente_estadia, TF.transit_time, " +
					    "vl_produto, DM_Frete_Responsabilidade, NM_Responsavel ");

			buff.append("FROM Tabelas_Fretes TF, Pessoas, Modal, Produtos ");


			buff.append("WHERE TF.DM_Aplicacao = 'CI' and " +
						"TF.OID_Produto = Produtos.OID_Produto AND " +
						"TF.OID_Modal = Modal.OID_Modal AND " +
						"TF.OID_Pessoa = Pessoas.OID_Pessoa AND " +
						"TF.oid_unidade = " + oid_Unidade);

	        if(!"".equals(NR_CNPJ_CPF)){
		        buff.append(" AND TF.OID_Pessoa = '" + NR_CNPJ_CPF + "'");
	        }

	        if(NR_Cotacao > 0){
		        buff.append(" AND TF.NR_Cotacao = " + NR_Cotacao);
	        }
	        if(JavaUtil.doValida(DT_Cotacao)){
		        buff.append(" AND TF.DT_Cotacao >= '" + DT_Cotacao + "'");
	        }
	        if(JavaUtil.doValida(DT_Cotacao_Final)){
		        buff.append(" AND TF.DT_Cotacao <= '" + DT_Cotacao_Final + "'");
	        }
	        buff.append(" order by TF.nr_cotacao");

//	        // System.out.println("Tab_Frete_Internacional.getAll() sql - "+buff.toString());

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
				p.setOID(cursor.getLong(1));
				p.setOID_Produto(cursor.getLong(2));
				p.setOID_Modal(cursor.getLong(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setNR_Peso_Inicial(cursor.getDouble(5));
				p.setNR_Peso_Final(cursor.getDouble(6));
				p.setVL_Frete(cursor.getDouble(7));
				p.setVL_Frete_Kg(cursor.getDouble(8));
				p.setVL_Taxas(cursor.getDouble(9));
				p.setPE_Ad_Valorem(cursor.getDouble(10));
				p.setVL_Outros1(cursor.getDouble(11));
				p.setVL_Outros2(cursor.getDouble(12));
				p.setVL_Outros3(cursor.getDouble(13));
				p.setVL_Outros4(cursor.getDouble(14));
				p.setVL_Outros5(cursor.getDouble(15));
				p.setVL_Outros8(cursor.getDouble(16));
				p.setVL_Outros7(cursor.getDouble(17));
				p.setVL_Outros6(cursor.getDouble(18));
				p.setVL_Outros9(cursor.getDouble(19));
				p.setDt_Stamp(cursor.getString(20));
				p.setUsuario_Stamp(cursor.getString(21));
				p.setDm_Stamp(cursor.getString(22));
				p.setNM_Razao_Social(cursor.getString(23));
				p.setCD_Produto(cursor.getString(24));
				p.setNM_Produto(cursor.getString(25));
				p.setCD_Modal(cursor.getString(26));
				p.setNM_Modal(cursor.getString(27));
				p.setOID_Origem(cursor.getLong(28));
				p.setOID_Destino(cursor.getLong(29));
				p.setNM_Origem(cursor.getString(30));
				p.setNM_Destino(cursor.getString(31));

				p.setDM_Tipo_Tabela(cursor.getString(32));
				if ("C".equals(p.getDM_Tipo_Tabela()))
				   p.setNM_Tipo_Tabela("Cotação");
				if ("P".equals(p.getDM_Tipo_Tabela()))
				   p.setNM_Tipo_Tabela("Percentual");
				if ("F".equals(p.getDM_Tipo_Tabela()))
				   p.setNM_Tipo_Tabela("Fracionado");

				p.setNr_Cotacao(cursor.getLong(33));
				p.setOid_Unidade(cursor.getLong(34));
				p.setNM_Fantasia(cursor.getString(35));
				p.setTX_Observacao(cursor.getString(36));
				p.setNM_Contato(cursor.getString(37));
				p.setNM_Setor(cursor.getString(38));
				p.setNR_Telefone(cursor.getString(39));
				p.setNR_Fax(cursor.getString(40));
				p.setEMail(cursor.getString(41));

				p.setOid_Pessoa_Transportadora(cursor.getString(42));
				p.setNM_Vendedor_Transportadora(cursor.getString(43));
				p.setNR_Telefone_Vendedor(cursor.getString(44));
				p.setNR_Fax_Vendedor(cursor.getString(45));
				p.setEMail_Vendedor(cursor.getString(46));
				p.setVL_Excedente_Estadia(cursor.getDouble(47));
				p.setTransit_Time(cursor.getString(48));
				p.setVL_Produto(cursor.getDouble(49));
				p.setDM_Frete_Responsabilidade(cursor.getString(50));
				p.setNM_Responsavel(cursor.getString(51));

				Tabela_Fretes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tabela_Fretes_Lista;
	}

	public static final List getByNR_CNPJ_CPF_Lista(String NR_CNPJ_CPF)
		throws Exception
	{
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o nome do DSN
			// o nome de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Tabela_Fretes_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT TF.OID_Tabela_Frete, TF.OID_Produto, TF.OID_Modal, TF.OID_Pessoa, NR_Peso_Inicial, " +
					    "NR_Peso_Final, VL_Frete, VL_Frete_Kg, VL_Taxas, PE_Ad_Valorem, VL_Outros1, VL_Outros2, " +
					    "VL_Outros3, VL_Outros4, VL_Outros5, VL_Outros6,  VL_Outros7,  VL_Outros8,  VL_Outros9, " +
					    "TF.Dt_Stamp, TF.Usuario_Stamp, TF.Dm_Stamp, Pessoas.NM_Razao_Social, Produtos.CD_Produto, " +
					    "Produtos.NM_Produto, Modal.CD_Modal, Modal.NM_Modal, TF.OID_Origem, TF.OID_Destino , " +
					    "TF.NM_Origem, TF.NM_Destino, TF.DM_Tipo_Tabela, TF.oid_pessoa_transportadora, " +
					    "TF.nm_vendedor, TF.nr_telefone_vendedor, TF.nr_fax_vendedor, TF.email_vendedor, " +
					    "TF.vl_excedente_estadia, TF.transit_time, vl_produto, DM_Frete_Responsabilidade, " +
					    "NM_Responsavel ");

			buff.append("FROM Tabelas_Fretes TF, Pessoas, Modal, Produtos ");

			buff.append("WHERE TF.DM_Tipo_Tabela <> 'R' " +
					    "and TF.OID_Produto = Produtos.OID_Produto " +
					    "AND TF.OID_Modal = Modal.OID_Modal " +
					    "AND TF.OID_Pessoa = Pessoas.OID_Pessoa AND TF.OID_Pessoa = '" + NR_CNPJ_CPF + "'");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
				p.setOID(cursor.getLong(1));
				p.setOID_Produto(cursor.getLong(2));
				p.setOID_Modal(cursor.getLong(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setNR_Peso_Inicial(cursor.getDouble(5));
				p.setNR_Peso_Final(cursor.getDouble(6));
				p.setVL_Frete(cursor.getDouble(7));
				p.setVL_Frete_Kg(cursor.getDouble(8));
				p.setVL_Taxas(cursor.getDouble(9));
				p.setPE_Ad_Valorem(cursor.getDouble(10));
				p.setVL_Outros1(cursor.getDouble(11));
				p.setVL_Outros2(cursor.getDouble(12));
				p.setVL_Outros3(cursor.getDouble(13));
				p.setVL_Outros4(cursor.getDouble(14));
				p.setVL_Outros5(cursor.getDouble(15));
				p.setVL_Outros8(cursor.getDouble(16));
				p.setVL_Outros7(cursor.getDouble(17));
				p.setVL_Outros6(cursor.getDouble(18));
				p.setVL_Outros9(cursor.getDouble(19));
				p.setDt_Stamp(cursor.getString(20));
				p.setUsuario_Stamp(cursor.getString(21));
				p.setDm_Stamp(cursor.getString(22));
				p.setNM_Razao_Social(cursor.getString(23));
				p.setCD_Produto(cursor.getString(24));
				p.setNM_Produto(cursor.getString(25));
				p.setCD_Modal(cursor.getString(26));
				p.setNM_Modal(cursor.getString(27));
				p.setOID_Origem(cursor.getLong(28));
				p.setOID_Destino(cursor.getLong(29));
				p.setNM_Origem(cursor.getString(30));
				p.setNM_Destino(cursor.getString(31));
				p.setDM_Tipo_Tabela(cursor.getString(32));
				if ("C".equals(p.getDM_Tipo_Tabela()))
				   p.setNM_Tipo_Tabela("Cotação");
				if ("P".equals(p.getDM_Tipo_Tabela()))
				   p.setNM_Tipo_Tabela("Percentual");
				if ("F".equals(p.getDM_Tipo_Tabela()))
				   p.setNM_Tipo_Tabela("Fracionado");

				p.setOid_Pessoa_Transportadora(cursor.getString(33));
				p.setNM_Vendedor_Transportadora(cursor.getString(34));
				p.setNR_Telefone_Vendedor(cursor.getString(35));
				p.setNR_Fax_Vendedor(cursor.getString(36));
				p.setEMail_Vendedor(cursor.getString(37));
				p.setVL_Excedente_Estadia(cursor.getDouble(38));
				p.setTransit_Time(cursor.getString(39));
				p.setVL_Produto(cursor.getDouble(40));
				p.setDM_Frete_Responsabilidade(cursor.getString(41));
				p.setNM_Responsavel(cursor.getString(42));

				Tabela_Fretes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tabela_Fretes_Lista;
	}

	public static final List getByNR_CNPJ_CPF_Tipo_Tabela(String oid_Unidade, String NR_CNPJ_CPF, long NR_Cotacao, String DM_Tipo_Tabela, String DT_Cotacao, String DT_Cotacao_Final) throws Exception{

	    Connection conn = null;

	    try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
	    catch (Exception e){
	        e.printStackTrace();
	        throw e;
	    }

	    List Tabela_Fretes_Lista = new ArrayList();

	    try{
	        StringBuffer buff = new StringBuffer();

	        buff.append("SELECT TF.OID_Tabela_Frete, TF.OID_Produto, TF.OID_Modal, TF.OID_Pessoa, ");
	        buff.append("NR_Peso_Inicial, NR_Peso_Final, VL_Frete, VL_Frete_Kg, VL_Taxas, PE_Ad_Valorem, ");
	        buff.append("VL_Outros1, VL_Outros2, VL_Outros3, VL_Outros4, VL_Outros5, VL_Outros6,  VL_Outros7, ");
	        buff.append("VL_Outros8,  VL_Outros9, TF.Dt_Stamp, TF.Usuario_Stamp, TF.Dm_Stamp, ");
	        buff.append("Pessoas.NM_Razao_Social, Produtos.CD_Produto, Produtos.NM_Produto, Modal.CD_Modal, ");
	        buff.append("Modal.NM_Modal, TF.OID_Origem, TF.OID_Destino , TF.NM_Origem, ");
	        buff.append("TF.NM_Destino, TF.DM_Tipo_Tabela, TF.nr_cotacao, TF.oid_unidade, Pessoas.NM_Fantasia, " +
	        		    "TF.tx_observacao, TF.nm_contato, TF.nm_setor, TF.nr_telefone, TF.nr_fax, TF.email, " +
	        		    "TF.oid_pessoa_transportadora, TF.nm_vendedor, TF.nr_telefone_vendedor, " +
	        		    "TF.nr_fax_vendedor, TF.email_vendedor, TF.vl_excedente_estadia, TF.transit_time, " +
	        		    "vl_produto, DM_Frete_Responsabilidade, NM_Responsavel ");

	        buff.append("FROM Tabelas_Fretes TF, Pessoas, Modal, Produtos ");

	        buff.append("WHERE TF.DM_Tipo_Tabela <> 'R' ");
	        buff.append("AND TF.OID_Produto = Produtos.OID_Produto ");
	        buff.append("AND TF.OID_Modal = Modal.OID_Modal ");
	        buff.append("AND TF.OID_Pessoa = Pessoas.OID_Pessoa ");
	        buff.append("AND TF.oid_unidade = '" + oid_Unidade + "' ");

	        if(!"".equals(NR_CNPJ_CPF)){
		        buff.append(" AND TF.OID_Pessoa = '" + NR_CNPJ_CPF + "'");
	        }

	        if(!"".equals(DM_Tipo_Tabela)){
		        buff.append(" AND TF.DM_Tipo_Tabela = '" + DM_Tipo_Tabela + "'");
	        }

	        if(NR_Cotacao > 0){
		        buff.append(" AND TF.NR_Cotacao = " + NR_Cotacao);
	        }
	        if(JavaUtil.doValida(DT_Cotacao)){
		        buff.append(" AND TF.DT_Cotacao >= '" + DT_Cotacao + "'");
	        }
	        if(JavaUtil.doValida(DT_Cotacao_Final)){
		        buff.append(" AND TF.DT_Cotacao <= '" + DT_Cotacao_Final + "'");
	        }

	        buff.append(" order by TF.nr_cotacao");

//	        // System.out.println("Tabela_Frete_Internacional.getByNR_CNPJ_CPF_Tipo_Tabela() - sql ="+buff.toString());

	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());

	        while (cursor.next()){
	            Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
	            p.setOID(cursor.getLong(1));
	            p.setOID_Produto(cursor.getLong(2));
	            p.setOID_Modal(cursor.getLong(3));
	            p.setOID_Pessoa(cursor.getString(4));
	            p.setNR_Peso_Inicial(cursor.getDouble(5));
	            p.setNR_Peso_Final(cursor.getDouble(6));
	            p.setVL_Frete(cursor.getDouble(7));
	            p.setVL_Frete_Kg(cursor.getDouble(8));
	            p.setVL_Taxas(cursor.getDouble(9));
	            p.setPE_Ad_Valorem(cursor.getDouble(10));
	            p.setVL_Outros1(cursor.getDouble(11));
	            p.setVL_Outros2(cursor.getDouble(12));
	            p.setVL_Outros3(cursor.getDouble(13));
	            p.setVL_Outros4(cursor.getDouble(14));
	            p.setVL_Outros5(cursor.getDouble(15));
	            p.setVL_Outros8(cursor.getDouble(16));
	            p.setVL_Outros7(cursor.getDouble(17));
	            p.setVL_Outros6(cursor.getDouble(18));
	            p.setVL_Outros9(cursor.getDouble(19));
	            p.setDt_Stamp(cursor.getString(20));
	            p.setUsuario_Stamp(cursor.getString(21));
	            p.setDm_Stamp(cursor.getString(22));
	            p.setNM_Razao_Social(cursor.getString(23));
	            p.setCD_Produto(cursor.getString(24));
	            p.setNM_Produto(cursor.getString(25));
	            p.setCD_Modal(cursor.getString(26));
	            p.setNM_Modal(cursor.getString(27));
	            p.setOID_Origem(cursor.getLong(28));
	            p.setOID_Destino(cursor.getLong(29));
	            p.setNM_Origem(cursor.getString(30));
	            p.setNM_Destino(cursor.getString(31));
	            p.setDM_Tipo_Tabela(cursor.getString(32));

	            if ("C".equals(p.getDM_Tipo_Tabela()))
	                p.setNM_Tipo_Tabela("Cotação");
	            if ("P".equals(p.getDM_Tipo_Tabela()))
	                p.setNM_Tipo_Tabela("Percentual");
	            if ("F".equals(p.getDM_Tipo_Tabela()))
	                p.setNM_Tipo_Tabela("Fracionado");

				p.setNr_Cotacao(cursor.getLong(33));
				p.setOid_Unidade(cursor.getLong(34));
				p.setNM_Fantasia(cursor.getString(35));
				p.setTX_Observacao(cursor.getString(36));
				p.setNM_Contato(cursor.getString(37));
				p.setNM_Setor(cursor.getString(38));
				p.setNR_Telefone(cursor.getString(39));
				p.setNR_Fax(cursor.getString(40));
				p.setEMail(cursor.getString(41));

				p.setOid_Pessoa_Transportadora(cursor.getString(42));
				p.setNM_Vendedor_Transportadora(cursor.getString(43));
				p.setNR_Telefone_Vendedor(cursor.getString(44));
				p.setNR_Fax_Vendedor(cursor.getString(45));
				p.setEMail_Vendedor(cursor.getString(46));
				p.setVL_Excedente_Estadia(cursor.getDouble(47));
				p.setTransit_Time(cursor.getString(48));
				p.setVL_Produto(cursor.getDouble(49));
				p.setDM_Frete_Responsabilidade(cursor.getString(50));
				p.setNM_Responsavel(cursor.getString(51));

	            Tabela_Fretes_Lista.add(p);
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return Tabela_Fretes_Lista;
}


    public void imprimeCotacao(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Connection conn = null;
        String sql = null;
        String siglaUnidade = null;
        String oid_Pessoa = null;
        HashMap paramCabecalho = new HashMap();
        ArrayList corpoCotacao = new ArrayList();
        boolean erro = true;
        boolean setaCabecalho = true;

        Locale.setDefault(Locale.GERMAN);

        FormataDataBean dataFormatada = new FormataDataBean();
        DecimalFormat semDec = new DecimalFormat("0");
        DecimalFormat dec1 = new DecimalFormat("###,###,##0.0");
        DecimalFormat dec2 = new DecimalFormat("###,###,##0.00");
        DecimalFormat dec3 = new DecimalFormat("###,###,##0.000");
        DecimalFormat dec4 = new DecimalFormat("###,###,##0.0000");

        String Relatorio = request.getParameter("Relatorio");
        String NR_Cotacao = request.getParameter("FT_NR_Cotacao");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String tipo_Cotacao = request.getParameter("FT_DM_Tipo_Tabela");
        String imprime_Toda_Cotacao = request.getParameter("imprime_Toda_Cotacao");
        String oid_Tabela_Frete = request.getParameter("oid_Tabela_Frete");
    	String idioma_Cotacao = request.getParameter("FT_DM_Idioma_Cotacao");

        if (!doValida(Relatorio)) throw new Mensagens("Nome do Relatório não informado!");

        if (NR_Cotacao != null && NR_Cotacao.length() != 0 && oid_Unidade != null && oid_Unidade.length() != 0 ){

    	    try{
    	        conn = OracleConnection2.getWEB();
    	        conn.setAutoCommit(false);
    	    }
    	    catch(Exception e){
    	        e.printStackTrace();
    	        throw e;
    	    }

    	    try{
    	        Statement stmt = conn.createStatement();

    	        sql = "SELECT paises.cd_pais, pessoas.nm_fantasia, unidades.oid_pessoa " +
    	        	  "FROM unidades, pessoas, cidades, regioes_estados, estados, regioes_paises, paises " +
    	        	  "WHERE unidades.oid_unidade = " + Integer.parseInt(oid_Unidade) + " " +
    	        	  "AND unidades.oid_pessoa = pessoas.oid_pessoa " +
    	        	  "AND pessoas.oid_cidade = cidades.oid_cidade " +
    	        	  "AND cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado " +
    	        	  "AND regioes_estados.oid_estado = estados.oid_estado " +
    	        	  "AND estados.oid_regiao_pais = regioes_paises.oid_regiao_pais " +
    	        	  "AND regioes_paises.oid_pais = paises.oid_pais";

    	        ResultSet cursor = stmt.executeQuery(sql);

    	        while(cursor.next()){
    	            siglaUnidade = cursor.getString("nm_fantasia").toUpperCase();
    	            oid_Pessoa = cursor.getString("oid_pessoa");

    	            if(!"".equals(idioma_Cotacao) && !idioma_Cotacao.equals("null") && idioma_Cotacao.equals("PT")){

    	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("C")){
    	                    Relatorio = "CotacaoLotacaoPt";
    	                }

    	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("F")){
    	                    Relatorio = "CotacaoFracionadoPt";
    	                }

    	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("P")){
    	                    Relatorio = "CotacaoPercentualPt";
    	                }

    	                erro = false;
    	            }
    	            else{
    	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("C")){
    	                    Relatorio = "CotacaoLotacaoEs";
    	                }

    	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("F")){
    	                    Relatorio = "CotacaoFracionadoEs";
    	                }

    	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("P")){
    	                    Relatorio = "CotacaoPercentualEs";
    	                }

    	                erro = false;
    	            }

    	            if(erro){
    	                throw new Excecoes("Não foi possível determinar o idioma da cotacao. Contate o suporte.", this.getClass().getName(), "imprimeCotacao()");
    	            }
    	        }

	            sql = "SELECT * FROM tabelas_fretes " +
		          	  "WHERE oid_unidade = '" + oid_Unidade + "' " +
		          	  "AND nr_cotacao = " + NR_Cotacao;

	            if(!"".equals(imprime_Toda_Cotacao) && imprime_Toda_Cotacao.equals("N")){

	            	sql += " AND oid_tabela_frete = " + oid_Tabela_Frete;

	            }

//	            // System.out.println("Tab_frete_internac.imprimeCotacao() sql2 - "+sql);

    	        cursor = null;
    	        cursor = stmt.executeQuery(sql);

    	        RelatorioED ed = new RelatorioED(response, Relatorio);
    	        int cont = 0;
    	        while(cursor.next()){

    	            if(setaCabecalho){

    	    	        PessoaBean pessoaBean = PessoaBean.getByOID(cursor.getString("oid_pessoa"));

    	                paramCabecalho.put("dt_cotacao", dataFormatada.getDT_FormataData(cursor.getString("dt_cotacao")));
    	                paramCabecalho.put("nm_sigla", siglaUnidade);
    	                paramCabecalho.put("nr_cotacao", String.valueOf(cursor.getInt("nr_cotacao")));
    	                paramCabecalho.put("nm_razao_social", pessoaBean.getNM_Razao_Social());
    	                paramCabecalho.put("nr_cnpj_cpf", pessoaBean.getNR_CNPJ_CPF());
    	                paramCabecalho.put("nm_contato", cursor.getString("nm_contato"));
    	                paramCabecalho.put("depto_setor", cursor.getString("nm_setor"));
    	                paramCabecalho.put("nr_telefone", cursor.getString("nr_telefone"));
    	                paramCabecalho.put("nr_fax", cursor.getString("nr_fax"));
    	                paramCabecalho.put("email", cursor.getString("email"));
    	                paramCabecalho.put("nm_mercadoria", (ProdutoBean.getByOID(cursor.getInt("oid_produto"))).getNM_Produto());
    	                paramCabecalho.put("observacao", cursor.getString("tx_observacao"));
    	                paramCabecalho.put("vl_excedente_estadia", dec2.format(cursor.getDouble("vl_excedente_estadia")));
    	                paramCabecalho.put("nm_razao_social_transportador", (PessoaBean.getByNR_CNPJ_CPF(cursor.getString("oid_pessoa_transportadora"))).getNM_Razao_Social());
    	                paramCabecalho.put("nm_vendedor_transportador", cursor.getString("nm_vendedor"));
    	                paramCabecalho.put("nr_telefone_vendedor", cursor.getString("nr_telefone_vendedor"));
    	                paramCabecalho.put("nr_fax_vendedor", cursor.getString("nr_fax_vendedor"));
    	                paramCabecalho.put("email_vendedor", cursor.getString("email_vendedor"));
    	                paramCabecalho.put("dt_validade", dataFormatada.getDT_FormataData(cursor.getString("dt_validade")));
    	                paramCabecalho.put("path_imagens", Parametro_FixoED.getInstancia().getPATH_IMAGENS());

    	                String tipo_Prazo_Pgto = cursor.getString("dm_tipo_prazo_pgto");

    	                if(JavaUtil.doValida(tipo_Prazo_Pgto) && tipo_Prazo_Pgto.equals("C")){
    	                	if(idioma_Cotacao.equals("PT")){
    	                		tipo_Prazo_Pgto = " dia(s) a partir da emissão do CRT.";
    	                	}
    	                	else{
    	                		tipo_Prazo_Pgto = " día(s) a partir de la emisión del CRT.";
    	                	}

    	                }
    	                else if(tipo_Prazo_Pgto.equals("M")){
    	                	if(idioma_Cotacao.equals("PT")){
    	                		tipo_Prazo_Pgto = " dia(s) a partir da entrega da mercadoria.";
    	                	}
    	                	else{
    	                		tipo_Prazo_Pgto = " día(s) a partir de la emisión de la mercaderia.";
    	                	}

    	                }
    	                else{
    	                	tipo_Prazo_Pgto = ".";
    	                }

    	                paramCabecalho.put("dias_prazo_pgto", cursor.getString("dias_prazo_pgto") + tipo_Prazo_Pgto);

    	                String frete_Responsa = cursor.getString("dm_frete_responsabilidade");

    	                if(!"".equals(frete_Responsa) && frete_Responsa.equals("E")){
    	                	frete_Responsa = "Exportador";
    	                }
    	                else if(frete_Responsa.equals("I")){
    	                	frete_Responsa = "Importador";
    	                }
    	                else if(frete_Responsa.equals("R")){
    	                	frete_Responsa = "Remetente";
    	                }
    	                else if(frete_Responsa.equals("D")){
    	                	frete_Responsa = "Destinatário";
    	                }
    	                else if(frete_Responsa.equals("T")){
    	                	if(idioma_Cotacao.equals("PT")){
    	                		frete_Responsa = "Terceiro - Nome do Responsável: " + cursor.getString("nm_responsavel").trim();
    	                	}
    	                	else{
    	                		frete_Responsa = "Tercero - Nombre del Responsable: " + cursor.getString("nm_responsavel").trim();
    	                	}

    	                }

    	                paramCabecalho.put("dm_frete_responsabilidade", frete_Responsa);

    	                ed.setHashMap(paramCabecalho);

    	                setaCabecalho = false;
    	            }

    	            RelatorioED edRel = new RelatorioED();

	    	        edRel.setNm_origem(cursor.getString("nm_origem"));
	    	        edRel.setNm_destino(cursor.getString("nm_destino"));

    	            if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("C")){
	    	            edRel.setPeso_bruto(dec3.format(cursor.getDouble("nr_peso_inicial")));
	    	            edRel.setMetro_cubico(dec1.format(cursor.getDouble("nr_peso_final")));
	    	            edRel.setValor_frete(dec2.format(cursor.getDouble("vl_frete")));
	    	            edRel.setPer_ad_valoren(dec2.format(cursor.getDouble("pe_ad_valorem")));
    	            }

	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("F")){
	    	            edRel.setFaixas_peso(semDec.format(cursor.getDouble("nr_peso_inicial"))+" a "+semDec.format(cursor.getDouble("nr_peso_final")));

	    	            String dm_Tipo_Peso = cursor.getString("DM_Tipo_Peso");

	    	            if(!"".equals(dm_Tipo_Peso) && dm_Tipo_Peso.equals("F")){
	    	            	dm_Tipo_Peso = " p/ CRT";
	    	            }
	    	            else if(dm_Tipo_Peso.equals("K")){
	    	            	dm_Tipo_Peso = " p/ Kg";
	    	            }
	    	            else if(dm_Tipo_Peso.equals("T")){
	    	            	dm_Tipo_Peso = " p/ Ton";
	    	            }

	    	            edRel.setValor_frete(dec2.format(cursor.getDouble("vl_frete"))+dm_Tipo_Peso);
	    	            edRel.setPer_ad_valoren(dec2.format(cursor.getDouble("pe_ad_valorem")));
	                }

	                if(!"".equals(tipo_Cotacao) && tipo_Cotacao.equals("P")){
	    	            edRel.setPer_sobre_invoice(dec2.format(cursor.getDouble("pe_ad_valorem")));
	    	            edRel.setValor_frete_minimo(dec2.format(cursor.getDouble("vl_frete_minimo")));
	                }

	                edRel.setTaxa_por_crt(dec2.format(cursor.getDouble("vl_taxas")));
    	            edRel.setTransit_time(cursor.getString("transit_time")+" dia(s)");

    	            cont++;
    	            corpoCotacao.add(edRel);
    	        }

    	       /* for( ; cont < 15 ; cont++){
    	        	RelatorioED edRel = new RelatorioED();
    	            edRel.setNm_origem(String.valueOf(" "));
    	            corpoCotacao.add(edRel);
    	        }*/

    	        cursor.close();
    	        stmt.close();
    	        conn.close();

    	        ed.setLista(corpoCotacao);
//    	        // System.out.println("Tab_Frete_Internac.imprimeContacao() - vai gerar relatorio");

    	        String nome = ed.getNomeRelatorio() +" "+Parametro_FixoED.getInstancia().getPATH_RELATORIOS()+" "+
    	        			  Parametro_FixoED.getInstancia().getPATH_IMAGENS();
//    	        // System.out.println("Tab_Frete_Internac.imprimeContacao() - "+nome);

                //*** Chama o Gerador de Relatórios Jasper
                new JasperRL(ed).geraRelatorio();

	        }
    	    catch(Exception e){
    	        throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "imprimeCotacao()");
	        }
        }
  	}



    public void insertCopiaCotacao()throws Exception{

        Connection conn = null;
        String sql = null;
        String oid_Pessoa = null;
        boolean nr_Cotacao_Invalido = true;

    	try{
    		conn = OracleConnection2.getWEB();
    		conn.setAutoCommit(false);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}

    	try{
    		Statement stmt = conn.createStatement();

    		sql = "SELECT * FROM tabelas_fretes " +
    			  "WHERE nr_cotacao = " + getNr_Cotacao_Copia() + " " +
    			  "AND oid_unidade = "+ getOid_Unidade();

    		ResultSet cursor = stmt.executeQuery(sql);

    		while(cursor.next()){

    			nr_Cotacao_Invalido = false;

    			setOID_Origem(cursor.getLong("OID_Origem"));
    			setDM_Origem(cursor.getString("DM_Origem"));
    			setNM_Origem(cursor.getString("NM_Origem"));

    			setOID_Destino(cursor.getLong("OID_Destino"));
    			setDM_Destino(cursor.getString("DM_Destino"));
    			setNM_Destino(cursor.getString("NM_Destino"));

    			setNR_Peso_Inicial(cursor.getDouble("NR_Peso_Inicial"));
    			setNR_Peso_Final(cursor.getDouble("NR_Peso_Final"));

    			setVL_Frete(cursor.getDouble("VL_Frete"));
    			setDM_Tipo_Peso(cursor.getString("DM_Tipo_Peso"));
    			setPE_Ad_Valorem(cursor.getDouble("PE_Ad_Valorem"));
    			setVL_Taxas(cursor.getDouble("VL_Taxas"));
    			setTransit_Time(cursor.getString("transit_time"));

    			this.insert_Internacional();
    		}

    		if(nr_Cotacao_Invalido){
    			throw new Mensagens("O número de cotação para realizar a cópia não existe!");
    		}

    		cursor.close();
    		stmt.close();
    		conn.close();

    	}
    	catch(Exception e){
    		throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "imprimeCotacao()");
    	}

  	}


    public static final boolean validaNrCotacaoCopia(long nr_Cotacao) throws Exception{

	Connection conn = null;
	boolean cotacaoExiste = false;
	try	{
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	}
	catch (Exception e){
		e.printStackTrace();
		throw e;
	}

	try{
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT oid_tabela_frete FROM Tabelas_Fretes ");
		buff.append("WHERE nr_cotacao = " + nr_Cotacao + " ");
		buff.append("AND dm_tipo_tabela = 'F'");

//		// System.out.println("Tab_Frete_Internac.validaNrCotacao() sql - " + buff.toString());

		Statement stmt = conn.createStatement();
		ResultSet cursor = stmt.executeQuery(buff.toString());

		while (cursor.next()){
			cotacaoExiste = true;
		}
		cursor.close();
		stmt.close();
		conn.close();
	}
	catch (Exception e){
		e.printStackTrace();
	}
	return cotacaoExiste;
}


    public static final List getCotacaoByCRT(String oid_Pessoa_Cotacao, int oid_Produto, int oid_Cidade_Origem, int oid_Cidade_Destino) throws Exception{

	Connection conn = null;

	List Tabela_Fretes_Lista = new ArrayList();

	try	{
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	}
	catch (Exception e){
		e.printStackTrace();
		throw e;
	}

	try{
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT * FROM Tabelas_Fretes ");
		buff.append("WHERE oid_pessoa = '" + oid_Pessoa_Cotacao + "' ");
		buff.append("AND oid_produto = " + oid_Produto);

//		// System.out.println("Tab_Frete_Internac.validaNrCotacao() sql - " + buff.toString());

		Statement stmt = conn.createStatement();
		ResultSet cursor = stmt.executeQuery(buff.toString());

		while (cursor.next()){

			Tabela_Frete_InternacionalBean tfiBean = new Tabela_Frete_InternacionalBean();

			tfiBean.setOID(cursor.getLong("oid_tabela_frete"));
			tfiBean.setNr_Cotacao(cursor.getLong("nr_cotacao"));
			tfiBean.setOid_Unidade(cursor.getLong("oid_unidade"));
			tfiBean.setOID_Pessoa(cursor.getString("oid_pessoa"));

			tfiBean.setDM_Tipo_Tabela(cursor.getString("dm_tipo_tabela"));

			tfiBean.setOID_Origem(cursor.getLong("oid_origem"));
			tfiBean.setDM_Origem(cursor.getString("dm_origem"));

			tfiBean.setOID_Destino(cursor.getLong("oid_destino"));
			tfiBean.setDM_Destino(cursor.getString("dm_destino"));

			tfiBean.setOID_Produto(cursor.getLong("oid_produto"));
			tfiBean.setOID_Modal(cursor.getLong("oid_modal"));
			tfiBean.setNR_Peso_Inicial(cursor.getDouble("nr_peso_inicial"));

			Tabela_Fretes_Lista.add(tfiBean);

		}
		cursor.close();
		stmt.close();
		conn.close();
	}
	catch (Exception e){
		e.printStackTrace();
	}
	return Tabela_Fretes_Lista;
}


    public static final List getListaCotacaoByCRT(String oid_Pessoa_Cotacao, int oid_Produto, int oid_Cidade_Origem, int oid_Cidade_Destino) throws Exception{

	Connection conn = null;

	List todas_Cotacoes_Lista = new ArrayList();

	List cotacao_Lista = new ArrayList();
	List fracionado_Lista = new ArrayList();
	List percentual_Lista = new ArrayList();

	try{
		todas_Cotacoes_Lista = getCotacaoByCRT(oid_Pessoa_Cotacao, oid_Produto, oid_Cidade_Origem, oid_Cidade_Destino);

		int a;
		if ((a = todas_Cotacoes_Lista.size()) != 0) {

			for (int i=0; i<a; i++) {

				Tabela_Frete_InternacionalBean tfiBean = (Tabela_Frete_InternacionalBean) todas_Cotacoes_Lista.get(i);

				String tipo_Cotacao = tfiBean.getDM_Tipo_Tabela();

				tfiBean.setNM_Origem(getOrigemDestinoByTipo(tfiBean.getDM_Origem(), (int)tfiBean.getOID_Origem()));
				tfiBean.setNM_Destino(getOrigemDestinoByTipo(tfiBean.getDM_Destino(), (int)tfiBean.getOID_Destino()));

				if(JavaUtil.doValida(tipo_Cotacao) && tipo_Cotacao.equals("C")){
					cotacao_Lista.add(tfiBean);
				}
				else if(tipo_Cotacao.equals("F")){
					fracionado_Lista.add(tfiBean);
				}
				else if(tipo_Cotacao.equals("P")){
					percentual_Lista.add(tfiBean);
				}
			}
			//o index deve ser mantido para depois as listas serem montadas em comL505.jsp
			todas_Cotacoes_Lista.add(1,cotacao_Lista);
			todas_Cotacoes_Lista.add(2,fracionado_Lista);
			todas_Cotacoes_Lista.add(3,percentual_Lista);

		}

	}
	catch (Exception e){
		e.printStackTrace();
	}
	return todas_Cotacoes_Lista;
}


    public static final String getOrigemDestinoByTipo(String tipo_Origem_Destino, int oid_Origem_Destino) throws Exception{

    	String nome_Origem_Destino = null;

	try{

		if(JavaUtil.doValida(tipo_Origem_Destino) && "C".equals(tipo_Origem_Destino)){
			nome_Origem_Destino = "Cidade - " + CidadeBean.getByOID(oid_Origem_Destino).getNM_Cidade();
		}
		else if("R".equals(tipo_Origem_Destino)){
			nome_Origem_Destino = "Praça - " + Regiao_EstadoBean.getByOID(oid_Origem_Destino).getNM_Regiao_Estado();
		}
		else if("E".equals(tipo_Origem_Destino)){
			nome_Origem_Destino = "Estado - " + EstadoBean.getByOID(oid_Origem_Destino).getNM_Estado();
		}
		else if("P".equals(tipo_Origem_Destino)){
			nome_Origem_Destino = "Pais - " + PaisBean.getByOID(oid_Origem_Destino).getNM_Pais();
		}

	}
	catch (Exception e){
		e.printStackTrace();
	}

	return nome_Origem_Destino;
  }


    public String getNM_Destino_Editado() {
        return NM_Destino_Editado;
    }
    public void setNM_Destino_Editado(String destino_Editado) {
        NM_Destino_Editado = destino_Editado;
    }
    public String getNM_Origem_Editado() {
        return NM_Origem_Editado;
    }
    public void setNM_Origem_Editado(String origem_Editado) {
        NM_Origem_Editado = origem_Editado;
    }
    public String getNM_Produto_Editado() {
        return NM_Produto_Editado;
    }
    public void setNM_Produto_Editado(String produto_Editado) {
        NM_Produto_Editado = produto_Editado;
    }

    public List getListaCotacoesToCRT(String oid_Pessoa_Cotacao, long oid_Produto, int oid_Unidade,
            						  long oid_Cidade_Origem, long oid_Cidade_Destino, String nm_Origem, String nm_Destino,
            						  double peso) throws Exception{

	Connection conn = null;

	List Tabela_Fretes_Lista = new ArrayList();

	try	{
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	}
	catch (Exception e){
		e.printStackTrace();
		throw e;
	}

	try{
	    CidadeBean ori = new CidadeBean();
	    CidadeBean des = new CidadeBean();
	    long origem = 0;
	    long destino = 0;
	    if (oid_Cidade_Destino > 0){
	        des = CidadeBean.getByOID(new Long(oid_Cidade_Destino).intValue());
	    }
	    if (oid_Cidade_Origem > 0){
	        ori = CidadeBean.getByOID(new Long(oid_Cidade_Origem).intValue());
	    }


		StringBuffer buff = new StringBuffer();
		buff.append("SELECT TF.OID_Tabela_Frete, TF.OID_Produto, TF.OID_Modal, TF.OID_Pessoa, ");
        buff.append("NR_Peso_Inicial, NR_Peso_Final, VL_Frete, VL_Frete_Kg, VL_Taxas, PE_Ad_Valorem, ");
        buff.append("VL_Outros1, VL_Outros2, VL_Outros3, VL_Outros4, VL_Outros5, VL_Outros6,  VL_Outros7, ");
        buff.append("VL_Outros8,  VL_Outros9, TF.Dt_Stamp, TF.Usuario_Stamp, TF.Dm_Stamp, ");
        buff.append("Pessoas.NM_Razao_Social, Produtos.CD_Produto, Produtos.NM_Produto, Modal.CD_Modal, ");
        buff.append("Modal.NM_Modal, TF.OID_Origem, TF.OID_Destino , TF.NM_Origem, ");
        buff.append("TF.NM_Destino, TF.DM_Tipo_Tabela, TF.nr_cotacao, TF.oid_unidade, Pessoas.NM_Fantasia, " +
        		    "TF.tx_observacao, TF.nm_contato, TF.nm_setor, TF.nr_telefone, TF.nr_fax, TF.email, " +
        		    "TF.oid_pessoa_transportadora, TF.nm_vendedor, TF.nr_telefone_vendedor, " +
        		    "TF.nr_fax_vendedor, TF.email_vendedor, TF.vl_excedente_estadia, TF.transit_time, " +
        		    "vl_produto, DM_Frete_Responsabilidade, NM_Responsavel, TF.DT_Validade ");

        buff.append("FROM Tabelas_Fretes TF, Pessoas, Modal, Produtos ");

        buff.append("WHERE TF.OID_Produto = Produtos.OID_Produto ");
        buff.append("AND TF.OID_Modal = Modal.OID_Modal ");
        buff.append("AND TF.OID_Pessoa = Pessoas.OID_Pessoa ");
        buff.append(" AND TF.DT_Validade >= '" + Data.getDataDMY() + "'");
        if(!"".equals(oid_Pessoa_Cotacao)){
	        buff.append(" AND ((TF.DM_Tipo_Tabela = 'C' and TF.OID_Pessoa = '" + oid_Pessoa_Cotacao + "') or " +
	        		         "(TF.DM_Tipo_Tabela = 'F' and TF.OID_Pessoa = '90300583000169'))");
        }
        if(oid_Produto > 0){
	        buff.append(" AND TF.OID_Produto = " + oid_Produto);
        }
        if(oid_Unidade > 0){
	        buff.append(" AND TF.Oid_Unidade = " + oid_Unidade);
        }
        if(peso > 0){
	        buff.append(" AND (TF.NR_Peso_Inicial = " + peso + " or (TF.NR_Peso_Inicial >= " + peso + " and TF.NR_Peso_Inicial <= " + peso + ") )");
        }
        if(nm_Origem != null && !nm_Origem.equals("") && !nm_Origem.equals("null")
           && origem > 0){
            buff.append(" AND (TF.nm_Origem_Editado = '" + nm_Origem + "' or " +
            		    "(TF.oid_origem = " + ori.getOID_Pais() + " and dm_origem = 'P') or " +
            		    "(TF.oid_origem = " + ori.getOID_Estado() + " and dm_origem = 'E') or " +
            		    "(TF.oid_origem = " + ori.getOID_Regiao_Estado() + " and dm_origem = 'R') or " +
            		    "(TF.oid_origem = " + ori.getOID() + " and dm_origem = 'C')" + ")");
        }
        if(nm_Destino != null && !nm_Destino.equals("") && !nm_Destino.equals("null")
           && destino > 0){
            buff.append(" AND (TF.nm_Destino_Editado = '" + nm_Destino + "' or " +
            		    "(TF.oid_Destino = " + des.getOID_Pais() + " and dm_Destino = 'P') or " +
            		    "(TF.oid_Destino = " + des.getOID_Estado() + " and dm_Destino = 'E') or " +
            		    "(TF.oid_Destino = " + des.getOID_Regiao_Estado() + " and dm_Destino = 'R') or " +
            		    "(TF.oid_Destino = " + des.getOID() + " and dm_Destino = 'C')" + ")");
        }

        buff.append(" order by TF.NR_Peso_Inicial");

// System.out.println("Tabela_Frete_Internacional.getByNR_CNPJ_CPF_Tipo_Tabela() - sql ="+buff.toString());

        Statement stmt = conn.createStatement();
        ResultSet cursor = stmt.executeQuery(buff.toString());

        while (cursor.next()){
            Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
            p.setOID(cursor.getLong(1));
            p.setOID_Produto(cursor.getLong(2));
            p.setOID_Modal(cursor.getLong(3));
            p.setOID_Pessoa(cursor.getString(4));
            p.setNR_Peso_Inicial(cursor.getDouble(5));
            p.setNR_Peso_Final(cursor.getDouble(6));
            p.setVL_Frete(cursor.getDouble(7));
            p.setVL_Frete_Kg(cursor.getDouble(8));
            p.setVL_Taxas(cursor.getDouble(9));
            p.setPE_Ad_Valorem(cursor.getDouble(10));
            p.setVL_Outros1(cursor.getDouble(11));
            p.setVL_Outros2(cursor.getDouble(12));
            p.setVL_Outros3(cursor.getDouble(13));
            p.setVL_Outros4(cursor.getDouble(14));
            p.setVL_Outros5(cursor.getDouble(15));
            p.setVL_Outros8(cursor.getDouble(16));
            p.setVL_Outros7(cursor.getDouble(17));
            p.setVL_Outros6(cursor.getDouble(18));
            p.setVL_Outros9(cursor.getDouble(19));
            p.setDt_Stamp(cursor.getString(20));
            p.setUsuario_Stamp(cursor.getString(21));
            p.setDm_Stamp(cursor.getString(22));
            p.setNM_Razao_Social(cursor.getString(23));
            p.setCD_Produto(cursor.getString(24));
            p.setNM_Produto(cursor.getString(25));
            p.setCD_Modal(cursor.getString(26));
            p.setNM_Modal(cursor.getString(27));
            p.setOID_Origem(cursor.getLong(28));
            p.setOID_Destino(cursor.getLong(29));
            p.setNM_Origem(cursor.getString(30));
            p.setNM_Destino(cursor.getString(31));
            p.setDM_Tipo_Tabela(cursor.getString(32));

            if ("C".equals(p.getDM_Tipo_Tabela()))
                p.setNM_Tipo_Tabela("Cotação");
            if ("P".equals(p.getDM_Tipo_Tabela()))
                p.setNM_Tipo_Tabela("Percentual");
            if ("F".equals(p.getDM_Tipo_Tabela()))
                p.setNM_Tipo_Tabela("Fracionado");

			p.setNr_Cotacao(cursor.getLong(33));
			p.setOid_Unidade(cursor.getLong(34));
			p.setNM_Fantasia(cursor.getString(35));
			p.setTX_Observacao(cursor.getString(36));
			p.setNM_Contato(cursor.getString(37));
			p.setNM_Setor(cursor.getString(38));
			p.setNR_Telefone(cursor.getString(39));
			p.setNR_Fax(cursor.getString(40));
			p.setEMail(cursor.getString(41));

			p.setOid_Pessoa_Transportadora(cursor.getString(42));
			p.setNM_Vendedor_Transportadora(cursor.getString(43));
			p.setNR_Telefone_Vendedor(cursor.getString(44));
			p.setNR_Fax_Vendedor(cursor.getString(45));
			p.setEMail_Vendedor(cursor.getString(46));
			p.setVL_Excedente_Estadia(cursor.getDouble(47));
			p.setTransit_Time(cursor.getString(48));
			p.setVL_Produto(cursor.getDouble(49));
			p.setDM_Frete_Responsabilidade(cursor.getString(50));
			p.setNM_Responsavel(cursor.getString(51));
			p.setDT_Validade(cursor.getString(52));

			Tabela_Fretes_Lista.add(p);

		}
		cursor.close();
		stmt.close();
		conn.close();
	}
	catch (Exception e){
		e.printStackTrace();
	}
	return Tabela_Fretes_Lista;
}
    
    public String getDM_RCTR_C() {
        return DM_RCTR_C;
    }
    public void setDM_RCTR_C(String dm_rctr_c) {
        DM_RCTR_C = dm_rctr_c;
    }
    public String getDM_RCTR_DC() {
        return DM_RCTR_DC;
    }
    public void setDM_RCTR_DC(String dm_rctr_dc) {
        DM_RCTR_DC = dm_rctr_dc;
    }
    public String getDM_RCTR_VI() {
        return DM_RCTR_VI;
    }
    public void setDM_RCTR_VI(String dm_rctr_vi) {
        DM_RCTR_VI = dm_rctr_vi;
    }
    public double getPE_Comissao() {
        return PE_Comissao;
    }
    public void setPE_Comissao(double comissao) {
        PE_Comissao = comissao;
    }
    public double getPE_Frete_E() {
        return PE_Frete_E;
    }
    public void setPE_Frete_E(double frete_E) {
        PE_Frete_E = frete_E;
    }
    public double getPE_Frete_I() {
        return PE_Frete_I;
    }
    public void setPE_Frete_I(double frete_I) {
        PE_Frete_I = frete_I;
    }
    
    
    public void geraRelatorio(HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	Connection conn = null;
	int NR_Cotacao = 0;
	String oid_Unidade = null;
	String dt_inicio = null;
	String dt_fim = null;
	try
	{
		// Pede uma conexão ao gerenciador do driver
		// passando como parâmetro o nome do DSN
		// o nome de usuário e a senha do banco.
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}

	List Tabela_Fretes_Lista = new ArrayList();
	try{
	    
	    if(JavaUtil.doValida(request.getParameter("FT_NR_Cotacao"))){
	        NR_Cotacao = Integer.parseInt(request.getParameter("FT_NR_Cotacao"));
	    }
	    if(JavaUtil.doValida(request.getParameter("oid_Unidade"))){
	        oid_Unidade = request.getParameter("oid_Unidade");
	    }
	    if(JavaUtil.doValida(request.getParameter("FT_DT_Inicio"))){
	        dt_inicio = request.getParameter("FT_DT_Inicio");
	    }
	    if(JavaUtil.doValida(request.getParameter("FT_DT_Fim"))){
	        dt_fim = request.getParameter("FT_DT_Fim");
	    }
	    
		StringBuffer buff = new StringBuffer();

		buff.append("SELECT TF.OID_Tabela_Frete, TF.OID_Produto, TF.OID_Modal, TF.OID_Pessoa, " +
					"NR_Peso_Inicial, NR_Peso_Final, VL_Frete, VL_Frete_Kg, VL_Taxas, PE_Ad_Valorem, " +
					"VL_Outros1, VL_Outros2, VL_Outros3, VL_Outros4, VL_Outros5, VL_Outros6,  VL_Outros7, " +
					"VL_Outros8,  VL_Outros9, TF.Dt_Stamp, TF.Usuario_Stamp, TF.Dm_Stamp, " +
					"Pessoas.NM_Razao_Social, Produtos.CD_Produto, Produtos.NM_Produto, Modal.CD_Modal, " +
					"Modal.NM_Modal, TF.OID_Origem, TF.OID_Destino , TF.NM_Origem, TF.NM_Destino, " +
					"TF.DM_Tipo_Tabela, TF.nr_cotacao, TF.oid_unidade, Pessoas.NM_Fantasia, TF.tx_observacao, " +
					"TF.nm_contato, TF.nm_setor, TF.nr_telefone, TF.nr_fax, TF.email, " +
					"TF.oid_pessoa_transportadora, TF.nm_vendedor, TF.nr_telefone_vendedor, " +
				    "TF.nr_fax_vendedor, TF.email_vendedor, TF.vl_excedente_estadia, TF.transit_time, " +
				    "vl_produto, DM_Frete_Responsabilidade, NM_Responsavel ");

		buff.append("FROM Tabelas_Fretes TF, Pessoas, Modal, Produtos ");


		buff.append("WHERE TF.DM_Aplicacao = 'CI' and " +
					"TF.OID_Produto = Produtos.OID_Produto AND " +
					"TF.OID_Modal = Modal.OID_Modal AND " +
					"TF.OID_Pessoa = Pessoas.OID_Pessoa ");
					

		if(JavaUtil.doValida(oid_Unidade)){
	        buff.append(" AND TF.oid_unidade = " + oid_Unidade);
        }
        if(!"".equals(request.getParameter("oid_Pessoa"))){
	        buff.append(" AND TF.OID_Pessoa = '" + request.getParameter("oid_Pessoa") + "'");
        }
        if(NR_Cotacao > 0){
	        buff.append(" AND TF.NR_Cotacao = " + NR_Cotacao);
        }
        if(JavaUtil.doValida(dt_inicio)){
	        buff.append(" AND TF.dt_cotacao >= " + dt_inicio);
        }
        if(JavaUtil.doValida(dt_fim)){
	        buff.append(" AND TF.dt_cotacao <= " + dt_fim);
        }
        
        buff.append(" order by TF.nr_cotacao");

//        // System.out.println("Tab_Frete_Internacional.getAll() sql - "+buff.toString());

		Statement stmt = conn.createStatement();
		ResultSet cursor = stmt.executeQuery(buff.toString());

		while (cursor.next())
		{
			Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
			p.setOID(cursor.getLong(1));
			p.setOID_Produto(cursor.getLong(2));
			p.setOID_Modal(cursor.getLong(3));
			p.setOID_Pessoa(cursor.getString(4));
			p.setNR_Peso_Inicial(cursor.getDouble(5));
			p.setNR_Peso_Final(cursor.getDouble(6));
			p.setVL_Frete(cursor.getDouble(7));
			p.setVL_Frete_Kg(cursor.getDouble(8));
			p.setVL_Taxas(cursor.getDouble(9));
			p.setPE_Ad_Valorem(cursor.getDouble(10));
			p.setVL_Outros1(cursor.getDouble(11));
			p.setVL_Outros2(cursor.getDouble(12));
			p.setVL_Outros3(cursor.getDouble(13));
			p.setVL_Outros4(cursor.getDouble(14));
			p.setVL_Outros5(cursor.getDouble(15));
			p.setVL_Outros8(cursor.getDouble(16));
			p.setVL_Outros7(cursor.getDouble(17));
			p.setVL_Outros6(cursor.getDouble(18));
			p.setVL_Outros9(cursor.getDouble(19));
			p.setDt_Stamp(cursor.getString(20));
			p.setUsuario_Stamp(cursor.getString(21));
			p.setDm_Stamp(cursor.getString(22));
			p.setNM_Razao_Social(cursor.getString(23));
			p.setCD_Produto(cursor.getString(24));
			p.setNM_Produto(cursor.getString(25));
			p.setCD_Modal(cursor.getString(26));
			p.setNM_Modal(cursor.getString(27));
			p.setOID_Origem(cursor.getLong(28));
			p.setOID_Destino(cursor.getLong(29));
			p.setNM_Origem(cursor.getString(30));
			p.setNM_Destino(cursor.getString(31));

			p.setDM_Tipo_Tabela(cursor.getString(32));
			if ("C".equals(p.getDM_Tipo_Tabela()))
			   p.setNM_Tipo_Tabela("Cotação");
			if ("P".equals(p.getDM_Tipo_Tabela()))
			   p.setNM_Tipo_Tabela("Percentual");
			if ("F".equals(p.getDM_Tipo_Tabela()))
			   p.setNM_Tipo_Tabela("Fracionado");

			p.setNr_Cotacao(cursor.getLong(33));
			p.setOid_Unidade(cursor.getLong(34));
			p.setNM_Fantasia(cursor.getString(35));
			p.setTX_Observacao(cursor.getString(36));
			p.setNM_Contato(cursor.getString(37));
			p.setNM_Setor(cursor.getString(38));
			p.setNR_Telefone(cursor.getString(39));
			p.setNR_Fax(cursor.getString(40));
			p.setEMail(cursor.getString(41));

			p.setOid_Pessoa_Transportadora(cursor.getString(42));
			p.setNM_Vendedor_Transportadora(cursor.getString(43));
			p.setNR_Telefone_Vendedor(cursor.getString(44));
			p.setNR_Fax_Vendedor(cursor.getString(45));
			p.setEMail_Vendedor(cursor.getString(46));
			p.setVL_Excedente_Estadia(cursor.getDouble(47));
			p.setTransit_Time(cursor.getString(48));
			p.setVL_Produto(cursor.getDouble(49));
			p.setDM_Frete_Responsabilidade(cursor.getString(50));
			p.setNM_Responsavel(cursor.getString(51));

			Tabela_Fretes_Lista.add(p);
		}
		cursor.close();
		stmt.close();
		conn.close();
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	//return Tabela_Fretes_Lista;
}
    
    public static final Tabela_Frete_InternacionalBean getByNR_Cotacao(long nr) throws Exception{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tabela_Frete do DSN
			// o NM_Tabela_Frete de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tabela_Frete_InternacionalBean p = new Tabela_Frete_InternacionalBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT TF.OID_Tabela_Frete, TF.OID_Produto, " +
					    "TF.OID_Modal, TF.OID_Pessoa, NR_Peso_Inicial, NR_Peso_Final, " +
					    "VL_Frete, VL_Frete_Minimo, VL_Taxas, PE_Ad_Valorem, VL_Ad_Valorem_Minimo, PE_Ademe, " +
					    "VL_Ademe_Minimo, VL_Maximo_Nota_Fiscal, VL_Despacho, VL_Frete_Valor_Minimo,  DM_ICMS, " +
					    "DM_Tipo_Tabela,  DM_Tipo_Peso,  VL_Outros9, TF.Dt_Stamp, " +
					    "TF.Usuario_Stamp, TF.Dm_Stamp, Pessoas.NM_Razao_Social, " +
					    "Produtos.CD_Produto, Produtos.NM_Produto , Modal.CD_Modal, Modal.NM_Modal, " +
					    "TF.OID_Origem, TF.OID_Destino , TF.NM_Origem, " +
					    "TF.NM_Destino, TF.DM_Origem, TF.DM_Destino, " +
					    "TF.DT_Vigencia, TF.DT_Validade, OID_Pessoa_Redespacho, " +
					    "NR_Prazo_Entrega, DM_Tipo_Pedagio, VL_Pedagio, TF.OID_Empresa, " +
					    "TF.NR_Prazo_Faturamento, TF.PE_Reentrega, " +
					    "TF.PE_Devolucao , Produtos.DM_Unidade, " +
					    "TF.PE_Refaturamento, TF.VL_Minimo_Nota_Fiscal, " +
					    "TF.NR_Peso_Minimo, TF.nr_cotacao, TF.oid_unidade, Pessoas.NM_Fantasia, TF.dt_cotacao, " +
					    "TF.tx_observacao, TF.nm_contato, TF.nm_setor, TF.nr_telefone, TF.nr_fax, TF.email, " +
					    "TF.oid_pessoa_transportadora, TF.nm_vendedor, TF.nr_telefone_vendedor, " +
					    "TF.nr_fax_vendedor, TF.email_vendedor, TF.vl_excedente_estadia, TF.transit_time, " +
					    "TF.vl_produto, TF.DM_Frete_Responsabilidade, TF.NM_Responsavel, TF.dias_prazo_pgto, " +
					    "TF.dm_tipo_prazo_pgto, TF.NM_Origem_Editado, TF.NM_Destino_Editado, TF.NM_Produto_Editado, " +
					    " TF.DM_RCTR_C, TF.DM_RCTR_VI, TF.DM_RCTR_DC, TF.pe_comissao, TF.pe_frete_E, TF.pe_frete_I ");

			buff.append("FROM Tabelas_Fretes TF, Pessoas, Modal, Produtos ");

			buff.append("WHERE TF.OID_Produto = Produtos.OID_Produto " +
					    "AND TF.OID_Modal = Modal.OID_Modal " +
					    "AND TF.OID_Pessoa = Pessoas.OID_Pessoa " +
					    "AND TF.NR_Cotacao = " + nr);

// System.out.println("Tab_Frete_Internacional.getByNR_Cotacao() sql - "+buff.toString());

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

            Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

			while (cursor.next())
			{
				p.setOID(cursor.getLong(1));
				p.setOID_Produto(cursor.getLong(2));
				p.setOID_Modal(cursor.getLong(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setNR_Peso_Inicial(cursor.getDouble(5));
				p.setNR_Peso_Final(cursor.getDouble(6));
				p.setVL_Frete(cursor.getDouble(7));
				p.setVL_Frete_Minimo(cursor.getDouble(8));
				p.setVL_Taxas(cursor.getDouble(9));
				p.setPE_Ad_Valorem(cursor.getDouble(10));
				p.setVL_Ad_Valorem_Minimo(cursor.getDouble(11));
				p.setPE_Ademe(cursor.getDouble(12));
				p.setVL_Ademe_Minimo(cursor.getDouble(13));
				p.setVL_Maximo_Nota_Fiscal(cursor.getDouble(14));
				p.setVL_Despacho(cursor.getDouble(15));
				p.setVL_Frete_Valor_Minimo(cursor.getDouble(16));
				p.setDM_ICMS(cursor.getString(17));
				p.setDM_Tipo_Tabela(cursor.getString(18));
				p.setDM_Tipo_Peso(cursor.getString(19));
				p.setVL_Outros9(cursor.getDouble(20));
				p.setDt_Stamp(cursor.getString(21));
				p.setUsuario_Stamp(cursor.getString(22));
				p.setDm_Stamp(cursor.getString(23));
				p.setNM_Razao_Social(cursor.getString(24));
				p.setCD_Produto(cursor.getString(25));
				p.setNM_Produto(cursor.getString(26));
				p.setCD_Modal(cursor.getString(27));
				p.setNM_Modal(cursor.getString(28));
				p.setOID_Origem(cursor.getLong(29));
				p.setOID_Destino(cursor.getLong(30));
				p.setNM_Origem(cursor.getString(31));
				p.setNM_Destino(cursor.getString(32));
				p.setDM_Origem(cursor.getString(33));
				p.setDM_Destino(cursor.getString(34));
				p.setDT_Vigencia(cursor.getString(35));
				p.setDT_Validade(cursor.getString(36));
				p.setOID_Pessoa_Redespacho(cursor.getString(37));
				p.setNR_Prazo_Entrega(cursor.getLong(38));
				p.setDM_Tipo_Pedagio(cursor.getString(39));
				p.setVL_Pedagio(cursor.getDouble(40));
				p.setOID_Empresa(cursor.getLong(41));
				p.setNR_Prazo_Faturamento(cursor.getLong(42));
				p.setPE_Reentrega(cursor.getDouble(43));
				p.setPE_Devolucao(cursor.getDouble(44));
				p.setDM_Unidade(cursor.getString(45));
				p.setPE_Refaturamento(cursor.getDouble(46));
				p.setVL_Minimo_Nota_Fiscal(cursor.getDouble(47));
				p.setNR_Peso_Minimo(cursor.getLong(48));

				p.setNr_Cotacao(cursor.getLong(49));
				p.setOid_Unidade(cursor.getLong(50));
				p.setNM_Fantasia(cursor.getString(51));
				p.setDT_Cotacao(cursor.getString(52));
				p.setTX_Observacao(cursor.getString(53));
				p.setNM_Contato(cursor.getString(54));
				p.setNM_Setor(cursor.getString(55));
				p.setNR_Telefone(cursor.getString(56));
				p.setNR_Fax(cursor.getString(57));
				p.setEMail(cursor.getString(58));

				p.setOid_Pessoa_Transportadora(cursor.getString(59));
				p.setNM_Vendedor_Transportadora(cursor.getString(60));
				p.setNR_Telefone_Vendedor(cursor.getString(61));
				p.setNR_Fax_Vendedor(cursor.getString(62));
				p.setEMail_Vendedor(cursor.getString(63));
				p.setVL_Excedente_Estadia(cursor.getDouble(64));
				p.setTransit_Time(cursor.getString(65));
				p.setVL_Produto(cursor.getDouble(66));
				p.setDM_Frete_Responsabilidade(cursor.getString(67));
				p.setNM_Responsavel(cursor.getString(68));
				p.setDT_Prazo_Pgto(cursor.getString(69));
				p.setDM_Tipo_Prazo_Pgto(cursor.getString(70));

				p.setNM_Origem_Editado(cursor.getString(71));
				p.setNM_Destino_Editado(cursor.getString(72));
				p.setNM_Produto_Editado(cursor.getString(73));
				
				p.setDM_RCTR_C(cursor.getString(74));
				p.setDM_RCTR_VI(cursor.getString(75));
				p.setDM_RCTR_DC(cursor.getString(76));
				
				p.setPE_Comissao(cursor.getDouble(77));
				
				p.setPE_Frete_E(cursor.getDouble(78));
				p.setPE_Frete_I(cursor.getDouble(79));

			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return p;
	}
    
	/**
	 * @return Returns the vL_Comissao.
	 */
	public double getVL_Comissao() {
		return VL_Comissao;
	}
	/**
	 * @param comissao The vL_Comissao to set.
	 */
	public void setVL_Comissao(double comissao) {
		VL_Comissao = comissao;
	}
	/**
	 * @return Returns the vL_Frete_E.
	 */
	public double getVL_Frete_E() {
		return VL_Frete_E;
	}
	/**
	 * @param frete_E The vL_Frete_E to set.
	 */
	public void setVL_Frete_E(double frete_E) {
		VL_Frete_E = frete_E;
	}
	/**
	 * @return Returns the vL_Frete_I.
	 */
	public double getVL_Frete_I() {
		return VL_Frete_I;
	}
	/**
	 * @param frete_I The vL_Frete_I to set.
	 */
	public void setVL_Frete_I(double frete_I) {
		VL_Frete_I = frete_I;
	}
}
