/*
 * Created on 29/09/2007
 *
 */
package com.master.ed;

/**
 * @author Régis Steigleder
 * @serial Recapagens
 * @serialData 10/2007
 * MODIFICADO
 * @author Ralph Renato
 * @serial consulta recapagens efetuadas
 * @serialData 11/2007
 */
public class RecapagemED  extends RelatorioBaseED {

	private static final long serialVersionUID = 3219306945788115541L;

	private long oid_Recapagem;
	private long oid_Pneu;
	private long oid_Empresa;
	private String oid_Fornecedor_Recapagem;
	private long oid_Fabricante_Banda;
	private long oid_Banda;
	private double vl_Recapagem;
	private String dt_Recapagem;
	private long nr_Nota_Fiscal_Recapagem;
	private String nr_Os_Recapagem;
	private double nr_Mm_Sulco_Recapagem;
	private String dm_Garantia_Recapagem;
	private long nr_Perimetro;
	private String msg_Stamp;
	//
	private String nr_Fogo;
	private String nr_Cnpj_Cpf_Recapagem;
	private String nm_Fornecedor_Recapagem;
	private String cd_Fabricante_Banda;
	private String nm_Fabricante_Banda;
	private String cd_Banda;
	private String nm_Banda;
	// Consulta Recapagens Efetuadas
	private String dt_Recapagem_Inicial;
	private String dt_Recapagem_Final;
	private long nr_Vida;
	private String nm_Dimensao_Pneu;
	private String tx_Situacao;
	private long oid_Dimensao_Pneu;
	private String oid_Fornecedor;

	private String cd_Pneu;
	private String nr_Cnpj_Cpf;
	private String nm_Fornecedor;

	public RecapagemED () {
	}

	public String getDm_Garantia_Recapagem() {
		return dm_Garantia_Recapagem;
	}

	public void setDm_Garantia_Recapagem(String dm_Garantia_Recapagem) {
		this.dm_Garantia_Recapagem = dm_Garantia_Recapagem;
	}

	public String getDt_Recapagem() {
		return dt_Recapagem;
	}

	public void setDt_Recapagem(String dt_Recapagem) {
		this.dt_Recapagem = dt_Recapagem;
	}

	public double getNr_Mm_Sulco_Recapagem() {
		return nr_Mm_Sulco_Recapagem;
	}

	public void setNr_Mm_Sulco_Recapagem(double nr_Mm_Sulco_Recapagem) {
		this.nr_Mm_Sulco_Recapagem = nr_Mm_Sulco_Recapagem;
	}

	public long getNr_Nota_Fiscal_Recapagem() {
		return nr_Nota_Fiscal_Recapagem;
	}

	public void setNr_Nota_Fiscal_Recapagem(long nr_Nota_Fiscal_Recapagem) {
		this.nr_Nota_Fiscal_Recapagem = nr_Nota_Fiscal_Recapagem;
	}

	public String getNr_Os_Recapagem() {
		return nr_Os_Recapagem;
	}

	public void setNr_Os_Recapagem(String nr_Os_Recapagem) {
		this.nr_Os_Recapagem = nr_Os_Recapagem;
	}

	public long getOid_Banda() {
		return oid_Banda;
	}

	public void setOid_Banda(long oid_Banda) {
		this.oid_Banda = oid_Banda;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public long getOid_Fabricante_Banda() {
		return oid_Fabricante_Banda;
	}

	public void setOid_Fabricante_Banda(long oid_Fabricante_Banda) {
		this.oid_Fabricante_Banda = oid_Fabricante_Banda;
	}

	public String getOid_Fornecedor_Recapagem() {
		return oid_Fornecedor_Recapagem;
	}

	public void setOid_Fornecedor_Recapagem(String oid_Fornecedor_Recapagem) {
		this.oid_Fornecedor_Recapagem = oid_Fornecedor_Recapagem;
	}

	public long getOid_Pneu() {
		return oid_Pneu;
	}

	public void setOid_Pneu(long oid_Pneu) {
		this.oid_Pneu = oid_Pneu;
	}

	public long getOid_Recapagem() {
		return oid_Recapagem;
	}

	public void setOid_Recapagem(long oid_Recapagem) {
		this.oid_Recapagem = oid_Recapagem;
	}

	public double getVl_Recapagem() {
		return vl_Recapagem;
	}

	public void setVl_Recapagem(double vl_Recapagem) {
		this.vl_Recapagem = vl_Recapagem;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public String getNm_Banda() {
		return nm_Banda;
	}


	public void setNm_Banda(String nm_Banda) {
		this.nm_Banda = nm_Banda;
	}


	public String getNm_Fabricante_Banda() {
		return nm_Fabricante_Banda;
	}


	public void setNm_Fabricante_Banda(String nm_Fabricante_Banda) {
		this.nm_Fabricante_Banda = nm_Fabricante_Banda;
	}


	public String getNm_Fornecedor_Recapagem() {
		return nm_Fornecedor_Recapagem;
	}


	public void setNm_Fornecedor_Recapagem(String nm_Fornecedor_Recapagem) {
		this.nm_Fornecedor_Recapagem = nm_Fornecedor_Recapagem;
	}


	public String getNr_Fogo() {
		return nr_Fogo;
	}


	public void setNr_Fogo(String nr_Fogo) {
		this.nr_Fogo = nr_Fogo;
	}

	public long getNr_Perimetro() {
		return nr_Perimetro;
	}

	public void setNr_Perimetro(long nr_Perimetro) {
		this.nr_Perimetro = nr_Perimetro;
	}

	public String getDt_Recapagem_Final() {
		return dt_Recapagem_Final;
	}

	public void setDt_Recapagem_Final(String dt_Recapagem_Final) {
		this.dt_Recapagem_Final = dt_Recapagem_Final;
	}

	public String getDt_Recapagem_Inicial() {
		return dt_Recapagem_Inicial;
	}

	public void setDt_Recapagem_Inicial(String dt_Recapagem_Inicial) {
		this.dt_Recapagem_Inicial = dt_Recapagem_Inicial;
	}

	public String getNm_Dimensao_Pneu() {
		return nm_Dimensao_Pneu;
	}

	public void setNm_Dimensao_Pneu(String nm_Dimensao_Pneu) {
		this.nm_Dimensao_Pneu = nm_Dimensao_Pneu;
	}

	public long getNr_Vida() {
		return nr_Vida;
	}

	public void setNr_Vida(long nr_Vida) {
		this.nr_Vida = nr_Vida;
	}

	public long getOid_Dimensao_Pneu() {
		return oid_Dimensao_Pneu;
	}

	public void setOid_Dimensao_Pneu(long oid_Dimensao_Pneu) {
		this.oid_Dimensao_Pneu = oid_Dimensao_Pneu;
	}

	public String getOid_Fornecedor() {
		return oid_Fornecedor;
	}

	public void setOid_Fornecedor(String oid_Fornecedor) {
		this.oid_Fornecedor = oid_Fornecedor;
	}

	public String getTx_Situacao() {
		String nm = null;
		if (this.getNr_Vida() == 0)
			nm = "NOVO";
		if (this.getNr_Vida() == 1)
			nm = "1º RECAPAGEM";
		if (this.getNr_Vida() == 2)
			nm = "2º RECAPAGEM";
		if (this.getNr_Vida() == 3)
			nm = "3º RECAPAGEM";
		if (this.getNr_Vida() == 4)
			nm = "4º RECAPAGEM";
		if (this.getNr_Vida() == 5)
			nm = "5º RECAPAGEM";
		if (this.getNr_Vida() == 6)
			nm = "6º RECAPAGEM";
		return nm;
	}

	public void setTx_Situacao(String tx_Situacao) {
		this.tx_Situacao = tx_Situacao;
	}

	public String getMsg_Stamp() {
		return msg_Stamp;
	}

	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}

	public String getCd_Banda() {
		return cd_Banda;
	}

	public void setCd_Banda(String cd_Banda) {
		this.cd_Banda = cd_Banda;
	}

	public String getCd_Fabricante_Banda() {
		return cd_Fabricante_Banda;
	}

	public void setCd_Fabricante_Banda(String cd_Fabricante_Banda) {
		this.cd_Fabricante_Banda = cd_Fabricante_Banda;
	}

	public String getCd_Pneu() {
		return cd_Pneu;
	}

	public void setCd_Pneu(String pneu) {
		cd_Pneu = pneu;
	}

	public String getNm_Fornecedor() {
		return nm_Fornecedor;
	}

	public void setNm_Fornecedor(String nm_Fornecedor) {
		this.nm_Fornecedor = nm_Fornecedor;
	}

	public String getNr_Cnpj_Cpf() {
		return nr_Cnpj_Cpf;
	}

	public void setNr_Cnpj_Cpf(String nr_Cnpj_Cpf) {
		this.nr_Cnpj_Cpf = nr_Cnpj_Cpf;
	}

	public String getNr_Cnpj_Cpf_Recapagem() {
		return nr_Cnpj_Cpf_Recapagem;
	}

	public void setNr_Cnpj_Cpf_Recapagem(String nr_Cnpj_Cpf_Recapagem) {
		this.nr_Cnpj_Cpf_Recapagem = nr_Cnpj_Cpf_Recapagem;
	}
}