/*
 * Created on 12/11/2004
 *
 */
package com.master.ed;

/**
 * @author Régis Steigleder
 * @serial Medicao de sulcos de pneus
 * @serialData 08/2007
 */
public class Medicao_PneuED  extends RelatorioED {

	private static final long serialVersionUID = -8397876872177895166L;

	private long oid_Medicao_Pneu;
	private long oid_Empresa;
	private long oid_Pneu;
	private long nr_Vida;
	private String dt_Medicao_Pneu;
	private String dt_Medicao_Pneu_Inicial;
	private String dt_Medicao_Pneu_Final;
	private String tx_Situacao;
	private double nr_Mm_Sulco;
	private double nr_Km_Acumulada_Pneu;

	private String nr_Fogo;

	public String getDt_Medicao_Pneu() {
		return dt_Medicao_Pneu;
	}

	public void setDt_Medicao_Pneu(String dt_Medicao_Pneu) {
		this.dt_Medicao_Pneu = dt_Medicao_Pneu;
	}

	public String getNr_Fogo() {
		return nr_Fogo;
	}

	public void setNr_Fogo(String nr_Fogo) {
		this.nr_Fogo = nr_Fogo;
	}

	public double getNr_Km_Acumulada_Pneu() {
		return nr_Km_Acumulada_Pneu;
	}

	public void setNr_Km_Acumulada_Pneu(double nr_Km_Acumulada_Pneu) {
		this.nr_Km_Acumulada_Pneu = nr_Km_Acumulada_Pneu;
	}

	public double getNr_Mm_Sulco() {
		return nr_Mm_Sulco;
	}

	public void setNr_Mm_Sulco(double nr_Mm_Sulco) {
		this.nr_Mm_Sulco = nr_Mm_Sulco;
	}

	public long getNr_Vida() {
		return nr_Vida;
	}

	public void setNr_Vida(long nr_Vida) {
		this.nr_Vida = nr_Vida;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public long getOid_Medicao_Pneu() {
		return oid_Medicao_Pneu;
	}

	public void setOid_Medicao_Pneu(long oid_Medicao_Pneu) {
		this.oid_Medicao_Pneu = oid_Medicao_Pneu;
	}

	public long getOid_Pneu() {
		return oid_Pneu;
	}

	public void setOid_Pneu(long oid_Pneu) {
		this.oid_Pneu = oid_Pneu;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTx_Situacao() {
		String nm = null;
		if (this.getNr_Vida() == 0)
			nm = "Novo";
		if (this.getNr_Vida() == 1)
			nm = "1º recapagem";
		if (this.getNr_Vida() == 2)
			nm = "2º recapagem";
		if (this.getNr_Vida() == 3)
			nm = "3º recapagem";
		if (this.getNr_Vida() == 4)
			nm = "4º recapagem";
		if (this.getNr_Vida() == 5)
			nm = "5º recapagem";
		if (this.getNr_Vida() == 6)
			nm = "6º recapagem";
		return nm;
	}

	public void setTx_Situacao(String tx_Situacao) {
		this.tx_Situacao = tx_Situacao;
	}

	public String getDt_Medicao_Pneu_Final() {
		return dt_Medicao_Pneu_Final;
	}

	public void setDt_Medicao_Pneu_Final(String dt_Medicao_Pneu_Final) {
		this.dt_Medicao_Pneu_Final = dt_Medicao_Pneu_Final;
	}

	public String getDt_Medicao_Pneu_Inicial() {
		return dt_Medicao_Pneu_Inicial;
	}

	public void setDt_Medicao_Pneu_Inicial(String dt_Medicao_Pneu_Inicial) {
		this.dt_Medicao_Pneu_Inicial = dt_Medicao_Pneu_Inicial;
	}

}
