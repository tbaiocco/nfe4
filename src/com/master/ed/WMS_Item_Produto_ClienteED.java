package com.master.ed;

import com.master.util.Valores;

/**
 * Título: WMS_Item_Produto_ClienteED
 * Descrição: Itens da Nota Fiscal - ED
 * Data da criação: 11/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/  

public class WMS_Item_Produto_ClienteED extends MasterED{

	private String oid_Item_Produto_Cliente;
	private String oid_Produto_Cliente;
	private String oid_Nota_Fiscal;
	private int oid_Tipo_Estoque;
	private int oid_Localizacao;
	private double nr_Quantidade_Movimento;
	private double nr_Quantidade_Devolucao;
	private int nr_Item_Nota_Fiscal;  
	private double vl_Produto;
	private double vl_Unitario;
	private double vl_Ipi;
	private String oid_Nota_Fiscal_Devolucao;
	private String dt_Stamp;
	private String usuario_Stamp;
	private String dm_Stamp;
	private String dm_Devolvido;

	private String OID_Produto;
	private String OID_Pessoa;
	private String CD_Produto;
	private String NM_Produto;
	private String CD_Tipo_Estoque;
	private String NM_Tipo_Estoque;
	private String NM_Razao_Social;
	private String NR_CNPJ_CPF;
	private String CD_Localizacao;
	private String NM_Localizacao;
	private int NR_Nota_Fiscal;
	private String DT_Emissao;
	
	private String nr_Quantidade_Movimento_TX;
	private String nr_Quantidade_Devolucao_TX;
	private String vl_Produto_TX;
	private String vl_Unitario_TX;
	private String vl_Ipi_TX;
  
	public String getCD_Produto() {
		return CD_Produto;
	}
	public void setCD_Produto(String produto) {
		CD_Produto = produto;
	}
	public String getCD_Tipo_Estoque() {
		return CD_Tipo_Estoque;
	}
	public void setCD_Tipo_Estoque(String tipo_Estoque) {
		CD_Tipo_Estoque = tipo_Estoque;
	}
	public String getDm_Stamp() {
		return dm_Stamp;
	}
	public void setDm_Stamp(String dm_Stamp) {
		this.dm_Stamp = dm_Stamp;
	}
	public String getDt_Stamp() {
		return dt_Stamp;
	}
	public void setDt_Stamp(String dt_Stamp) {
		this.dt_Stamp = dt_Stamp;
	}
	public String getNM_Produto() {
		return NM_Produto;
	}
	public void setNM_Produto(String produto) {
		NM_Produto = produto;
	}
	public String getNM_Razao_Social() {
		return NM_Razao_Social;
	}
	public void setNM_Razao_Social(String razao_Social) {
		NM_Razao_Social = razao_Social;
	}
	public String getNM_Tipo_Estoque() {
		return NM_Tipo_Estoque;
	}
	public void setNM_Tipo_Estoque(String tipo_Estoque) {
		NM_Tipo_Estoque = tipo_Estoque;
	}
	public String getNR_CNPJ_CPF() {
		return NR_CNPJ_CPF;
	}
	public void setNR_CNPJ_CPF(String nr_cnpj_cpf) {
		NR_CNPJ_CPF = nr_cnpj_cpf;
	}
	public int getNr_Item_Nota_Fiscal() {
		return nr_Item_Nota_Fiscal;
	}
	public void setNr_Item_Nota_Fiscal(int nr_Item_Nota_Fiscal) {
		this.nr_Item_Nota_Fiscal = nr_Item_Nota_Fiscal;
	}
	public double getNr_Quantidade_Devolucao() {
		return nr_Quantidade_Devolucao;
	}
	public void setNr_Quantidade_Devolucao(double nr_Quantidade_Devolucao) {
		this.nr_Quantidade_Devolucao = nr_Quantidade_Devolucao;
	}
	public String getNr_Quantidade_Devolucao_TX() {
		return nr_Quantidade_Devolucao_TX;
	}
	public void setNr_Quantidade_Devolucao_TX(String nr_Quantidade_Devolucao_TX) {
		this.nr_Quantidade_Devolucao_TX = nr_Quantidade_Devolucao_TX;
		this.setNr_Quantidade_Devolucao(Valores.converteStringToDouble(nr_Quantidade_Devolucao_TX));
	}
	
	public String getNr_Quantidade_Movimento_TX() {
		return nr_Quantidade_Movimento_TX;
	}
	public void setNr_Quantidade_Movimento_TX(String nr_Quantidade_Movimento_TX) {
		this.nr_Quantidade_Movimento_TX = nr_Quantidade_Movimento_TX;
		this.setNr_Quantidade_Movimento(Valores.converteStringToDouble(nr_Quantidade_Movimento_TX));
	}
	public String getOid_Item_Produto_Cliente() {
		return oid_Item_Produto_Cliente;
	}
	public void setOid_Item_Produto_Cliente(String oid_Item_Produto_Cliente) {
		this.oid_Item_Produto_Cliente = oid_Item_Produto_Cliente;
	}
	public int getOid_Localizacao() {
		return oid_Localizacao;
	}
	public void setOid_Localizacao(int oid_Localizacao) {
		this.oid_Localizacao = oid_Localizacao;
	}
	public String getOid_Nota_Fiscal() {
		return oid_Nota_Fiscal;
	}
	public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
		this.oid_Nota_Fiscal = oid_Nota_Fiscal;
	}
	public String getOid_Nota_Fiscal_Devolucao() {
		return oid_Nota_Fiscal_Devolucao;
	}
	public void setOid_Nota_Fiscal_Devolucao(String oid_Nota_Fiscal_Devolucao) {
		this.oid_Nota_Fiscal_Devolucao = oid_Nota_Fiscal_Devolucao;
	}
	public String getOID_Pessoa() {
		return OID_Pessoa;
	}
	public void setOID_Pessoa(String pessoa) {
		OID_Pessoa = pessoa;
	}
	public String getOID_Produto() {
		return OID_Produto;
	}
	public void setOID_Produto(String produto) {
		OID_Produto = produto;
	}
	public String getOid_Produto_Cliente() {
		return oid_Produto_Cliente;
	}
	public void setOid_Produto_Cliente(String oid_Produto_Cliente) {
		this.oid_Produto_Cliente = oid_Produto_Cliente;
	}
	public int getOid_Tipo_Estoque() {
		return oid_Tipo_Estoque;
	}
	public void setOid_Tipo_Estoque(int oid_Tipo_Estoque) {
		this.oid_Tipo_Estoque = oid_Tipo_Estoque;
	}
	public String getUsuario_Stamp() {
		return usuario_Stamp;
	}
	public void setUsuario_Stamp(String usuario_Stamp) {
		this.usuario_Stamp = usuario_Stamp;
	}
	public double getVl_Unitario() {
		return vl_Unitario;
	}
	public void setVl_Unitario(double vl_Unitario) {
		this.vl_Unitario = vl_Unitario;
	}
	public String getCD_Localizacao() {
		return CD_Localizacao;
	}
	public void setCD_Localizacao(String localizacao) {
		CD_Localizacao = localizacao;
	}
	public String getNM_Localizacao() {
		return NM_Localizacao;
	}
	public void setNM_Localizacao(String localizacao) {
		NM_Localizacao = localizacao;
	}
	public double getVl_Ipi() {
		return vl_Ipi;
	}
	public void setVl_Ipi(double vl_Ipi) {
		this.vl_Ipi = vl_Ipi;
	}
	public String getVl_Unitario_TX() {
		return vl_Unitario_TX;
	}
	public void setVl_Unitario_TX(String vl_Unitario_TX) {
		this.vl_Unitario_TX = vl_Unitario_TX;
		this.setVl_Unitario(Valores.converteStringToDouble(vl_Unitario_TX));
	}
	public String getVl_Produto_TX() {
		return vl_Produto_TX;
	}
	public void setVl_Produto_TX(String vl_Produto_TX) {
		this.vl_Produto_TX = vl_Produto_TX;
		this.setVl_Produto(Valores.converteStringToDouble(vl_Produto_TX));
	}
	public String getVl_Ipi_TX() {
		return vl_Ipi_TX;
	}
	public void setVl_Ipi_TX(String vl_Ipi_TX) {
		this.vl_Ipi_TX = vl_Ipi_TX;
		this.setVl_Ipi(Valores.converteStringToDouble(vl_Ipi_TX));
	}
	public int getNR_Nota_Fiscal() {
		return NR_Nota_Fiscal;
	}
	public void setNR_Nota_Fiscal(int nota_Fiscal) {
		NR_Nota_Fiscal = nota_Fiscal;
	}
	public String getDT_Emissao() {
		return DT_Emissao;
	}
	public void setDT_Emissao(String emissao) {
		DT_Emissao = emissao;
	}
	public String getDm_Devolvido() {
		return dm_Devolvido;
	}
	public void setDm_Devolvido(String dm_Devolvido) {
		this.dm_Devolvido = dm_Devolvido;
	}
	public double getNr_Quantidade_Movimento() {
		return nr_Quantidade_Movimento;
	}
	public void setNr_Quantidade_Movimento(double nr_Quantidade_Movimento) {
		this.nr_Quantidade_Movimento = nr_Quantidade_Movimento;
	}
	public double getVl_Produto() {
		return vl_Produto;
	}
	public void setVl_Produto(double vl_Produto) {
		this.vl_Produto = vl_Produto;
	}

}