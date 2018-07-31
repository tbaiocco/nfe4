package com.master.ed;

/**
 * @author André Valadas
 * @serial Pneus Novos
 * @since 10/2008
 */
public class Pneu_NovoED extends RelatorioBaseED {
	private static final long serialVersionUID = -1104806801928949164L;

	private long oid_Pneu_Novo;
	private long oid_Fabricante_Pneu;
	private long oid_Dimensao_Pneu;
	private long oid_Modelo_Pneu;
	private String oid_Fornecedor;
	private long oid_Material;
	private long oid_Nota_Fiscal_Frota;
	private long oid_Empresa;

	private long nr_Quantidade;
	private double vl_Unitario;

	//Referencias
	FornecedorED fornecedor = new FornecedorED();

	//Construtores
	public Pneu_NovoED() {
		super();
	}
	public Pneu_NovoED(long oid_Pneu_Novo) {
		super();
		this.setOid_Pneu_Novo(oid_Pneu_Novo);
	}
	public Pneu_NovoED(long oid_Empresa, long oid_Nota_Fiscal_Frota) {
		super();
		this.oid_Empresa = oid_Empresa;
		this.oid_Nota_Fiscal_Frota = oid_Nota_Fiscal_Frota;
	}

	public long getOid_Pneu_Novo() {
		return oid_Pneu_Novo;
	}
	public void setOid_Pneu_Novo(long oid_Pneu_Novo) {
		this.oid_Pneu_Novo = oid_Pneu_Novo;
	}
	public long getOid_Fabricante_Pneu() {
		return oid_Fabricante_Pneu;
	}
	public void setOid_Fabricante_Pneu(long oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}
	public long getOid_Dimensao_Pneu() {
		return oid_Dimensao_Pneu;
	}
	public void setOid_Dimensao_Pneu(long oid_Dimensao_Pneu) {
		this.oid_Dimensao_Pneu = oid_Dimensao_Pneu;
	}
	public long getOid_Modelo_Pneu() {
		return oid_Modelo_Pneu;
	}
	public void setOid_Modelo_Pneu(long oid_Modelo_Pneu) {
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
	}
	public String getOid_Fornecedor() {
		return oid_Fornecedor;
	}
	public void setOid_Fornecedor(String oid_Fornecedor) {
		this.oid_Fornecedor = oid_Fornecedor;
	}
	public long getOid_Material() {
		return oid_Material;
	}
	public void setOid_Material(long oid_Material) {
		this.oid_Material = oid_Material;
	}
	public long getOid_Nota_Fiscal_Frota() {
		return oid_Nota_Fiscal_Frota;
	}
	public void setOid_Nota_Fiscal_Frota(long oid_Nota_Fiscal_Frota) {
		this.oid_Nota_Fiscal_Frota = oid_Nota_Fiscal_Frota;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getNr_Quantidade() {
		return nr_Quantidade;
	}
	public void setNr_Quantidade(long nr_Quantidade) {
		this.nr_Quantidade = nr_Quantidade;
	}
	public double getVl_Unitario() {
		return vl_Unitario;
	}
	public void setVl_Unitario(double vl_Unitario) {
		this.vl_Unitario = vl_Unitario;
	}
	public FornecedorED getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(FornecedorED fornecedor) {
		this.fornecedor = fornecedor;
	}
}