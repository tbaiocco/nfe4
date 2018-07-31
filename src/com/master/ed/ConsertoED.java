/*
* Created on 25/06/2007
*
*/
package com.master.ed;

/**
 * @author Cristian Vianna Garcia
 * @serial Consertos/Resulcagens
 * @serialData 08/2007
 */
public class ConsertoED  extends RelatorioBaseED {

	private static final long serialVersionUID = 8058675524793156661L;

	private long oid_Conserto;
	private String nr_Fogo_Conserto;
	private String dt_Conserto;
	private String nr_Frota_Conserto;
	private double vl_Conserto;
	private long dm_Vida_Conserto;
	private String nr_Documento_Conserto;
	private String oid_Fornecedor_Conserto;
	private String tx_Descricao_Conserto;
	private long oid_Pneu_Conserto;
	private String oid_Veiculo;
	private long oid_Empresa;
	private String dt_Inicial_Conserto;
	private String dt_Final_Conserto;
	private String tx_Descricao_Resulcagem;
	private String dm_Opcao;
	private double vl_Prof_Sulco_Resulcagem;
	private String nm_Controle;
	private String nm_Vida_Conserto;

	private long oid_Fabricante_Pneu;
	private long oid_Modelo_Pneu;
	private long oid_Dimensao_Pneu;
	private String nm_Fabricante_Pneu;
	private String nm_Modelo_Pneu;
	private String cd_Modelo_Pneu;
	private String nm_Dimensao_Pneu;
	private String dm_Fabricante_Pneu;
	private String dm_Modelo_Pneu;
	private String dm_Dimensao_Pneu;
	private long nr_Consertos;
	private long nr_Vida;
	private String tx_Situacao;
	private String msg_Stamp;

	private long oid_Pneu;

	//fks
	private String cd_Pneu;
	private String oid_Fornecedor;
	private String nr_Cnpj_Cpf;
	private String nm_Fornecedor;
	private String nr_Placa;

	public String getMsg_Stamp() {
		return msg_Stamp;
	}

	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}

	public ConsertoED() {
	}

	public long getDm_Vida_Conserto() {
		return dm_Vida_Conserto;
	}
	public void setDm_Vida_Conserto(long dm_Vida_Conserto) {
		this.dm_Vida_Conserto = dm_Vida_Conserto;
	}
	public String getDt_Conserto() {
		return dt_Conserto;
	}
	public void setDt_Conserto(String dt_Conserto) {
		this.dt_Conserto = dt_Conserto;
	}
	public String getNr_Documento_Conserto() {
		return nr_Documento_Conserto;
	}
	public void setNr_Documento_Conserto(String nr_Documento_Conserto) {
		this.nr_Documento_Conserto = nr_Documento_Conserto;
	}
	public long getOid_Conserto() {
		return oid_Conserto;
	}
	public void setOid_Conserto(long oid_Conserto) {
		this.oid_Conserto = oid_Conserto;
	}
	public String getOid_Fornecedor_Conserto() {
		return oid_Fornecedor_Conserto;
	}
	public void setOid_Fornecedor_Conserto(String oid_Fornecedor_Conserto) {
		this.oid_Fornecedor_Conserto = oid_Fornecedor_Conserto;
	}
	public String getTx_Descricao_Conserto() {
		return tx_Descricao_Conserto;
	}
	public void setTx_Descricao_Conserto(String tx_Descricao_Conserto) {
		this.tx_Descricao_Conserto = tx_Descricao_Conserto;
	}
	public double getVl_Conserto() {
		return vl_Conserto;
	}
	public void setVl_Conserto(double vl_Conserto) {
		this.vl_Conserto = vl_Conserto;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public long getOid_Pneu_Conserto() {
		return oid_Pneu_Conserto;
	}
	public void setOid_Pneu_Conserto(long oid_Pneu_Conserto) {
		this.oid_Pneu_Conserto = oid_Pneu_Conserto;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public String getDt_Final_Conserto() {
		return dt_Final_Conserto;
	}
	public void setDt_Final_Conserto(String dt_Final_Conserto) {
		this.dt_Final_Conserto = dt_Final_Conserto;
	}
	public String getDt_Inicial_Conserto() {
		return dt_Inicial_Conserto;
	}
	public void setDt_Inicial_Conserto(String dt_Inicial_Conserto) {
		this.dt_Inicial_Conserto = dt_Inicial_Conserto;
	}
	public String getNr_Fogo_Conserto() {
		return nr_Fogo_Conserto;
	}
	public void setNr_Fogo_Conserto(String nr_Fogo_Conserto) {
		this.nr_Fogo_Conserto = nr_Fogo_Conserto;
	}
	public String getNr_Frota_Conserto() {
		return nr_Frota_Conserto;
	}
	public void setNr_Frota_Conserto(String nr_Frota_Conserto) {
		this.nr_Frota_Conserto = nr_Frota_Conserto;
	}
	public String getTx_Descricao_Resulcagem() {
			String nm = null;
			nm = "RESULCAGEM - " + this.getVl_Prof_Sulco_Resulcagem() + " mm";
			return nm;
	}
	public void setTx_Descricao_Resulcagem(String tx_Descricao_Resulcagem) {
		this.tx_Descricao_Resulcagem = tx_Descricao_Resulcagem;
	}
	public double getVl_Prof_Sulco_Resulcagem() {
		return vl_Prof_Sulco_Resulcagem;
	}
	public void setVl_Prof_Sulco_Resulcagem(double vl_Prof_Sulco_Resulcagem) {
		this.vl_Prof_Sulco_Resulcagem = vl_Prof_Sulco_Resulcagem;
	}
	public String getNm_Controle() {
		return nm_Controle;
	}
	public void setNm_Controle(String nm_Controle) {
		this.nm_Controle = nm_Controle;
	}
	public String getDm_Opcao() {
		return dm_Opcao;
	}
	public void setDm_Opcao(String dm_Opcao) {
		this.dm_Opcao = dm_Opcao;
	}
	public String getNm_Vida_Conserto() {
		String nm = null;
		if (this.getDm_Vida_Conserto() == 0)
			nm = "Novo";
		if (this.getDm_Vida_Conserto() == 1)
			nm = "1º Rec.";
		if (this.getDm_Vida_Conserto() == 2)
			nm = "2º Rec.";
		if (this.getDm_Vida_Conserto() == 3)
			nm = "3º Rec.";
		if (this.getDm_Vida_Conserto() == 4)
			nm = "4º Rec.";
		if (this.getDm_Vida_Conserto() == 5)
			nm = "5º Rec.";
		if (this.getDm_Vida_Conserto() == 6)
			nm = "6º Rec.";
		if (this.getDm_Vida_Conserto() == 7)
			nm = "7º Rec.";
		return nm;
	}
	public void setNm_Vida_Conserto(String nm_Vida_Conserto) {
		this.nm_Vida_Conserto = nm_Vida_Conserto;
	}
	public String getNm_Fornecedor() {
		return nm_Fornecedor;
	}
	public void setNm_Fornecedor(String nm_Fornecedor) {
		this.nm_Fornecedor = nm_Fornecedor;
	}

	public String getNm_Dimensao_Pneu() {
		return nm_Dimensao_Pneu;
	}

	public void setNm_Dimensao_Pneu(String nm_Dimensao_Pneu) {
		this.nm_Dimensao_Pneu = nm_Dimensao_Pneu;
	}

	public String getNm_Fabricante_Pneu() {
		return nm_Fabricante_Pneu;
	}

	public void setNm_Fabricante_Pneu(String nm_Fabricante_Pneu) {
		this.nm_Fabricante_Pneu = nm_Fabricante_Pneu;
	}

	public String getNm_Modelo_Pneu() {
		return nm_Modelo_Pneu;
	}

	public void setNm_Modelo_Pneu(String nm_Modelo_Pneu) {
		this.nm_Modelo_Pneu = nm_Modelo_Pneu;
	}

	public long getOid_Dimensao_Pneu() {
		return oid_Dimensao_Pneu;
	}

	public void setOid_Dimensao_Pneu(long oid_Dimensao_Pneu) {
		this.oid_Dimensao_Pneu = oid_Dimensao_Pneu;
	}

	public long getOid_Fabricante_Pneu() {
		return oid_Fabricante_Pneu;
	}

	public void setOid_Fabricante_Pneu(long oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}

	public long getOid_Modelo_Pneu() {
		return oid_Modelo_Pneu;
	}

	public void setOid_Modelo_Pneu(long oid_Modelo_Pneu) {
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
	}

	public String getDm_Dimensao_Pneu() {
		return dm_Dimensao_Pneu;
	}

	public void setDm_Dimensao_Pneu(String dm_Dimensao_Pneu) {
		this.dm_Dimensao_Pneu = dm_Dimensao_Pneu;
	}

	public String getDm_Fabricante_Pneu() {
		return dm_Fabricante_Pneu;
	}

	public void setDm_Fabricante_Pneu(String dm_Fabricante_Pneu) {
		this.dm_Fabricante_Pneu = dm_Fabricante_Pneu;
	}

	public String getDm_Modelo_Pneu() {
		return dm_Modelo_Pneu;
	}

	public void setDm_Modelo_Pneu(String dm_Modelo_Pneu) {
		this.dm_Modelo_Pneu = dm_Modelo_Pneu;
	}

	public long getNr_Consertos() {
		return nr_Consertos;
	}

	public void setNr_Consertos(long nr_Consertos) {
		this.nr_Consertos = nr_Consertos;
	}

	public long getNr_Vida() {
		return nr_Vida;
	}

	public void setNr_Vida(long nr_Vida) {
		this.nr_Vida = nr_Vida;
	}

	public String getTx_Situacao() {
		String nm = null;
		if (this.getNr_Vida() == 0)
			nm = "Novo";
		if (this.getNr_Vida() == 1)
			nm = "1º rec";
		if (this.getNr_Vida() == 2)
			nm = "2º rec";
		if (this.getNr_Vida() == 3)
			nm = "3º rec";
		if (this.getNr_Vida() == 4)
			nm = "4º rec";
		if (this.getNr_Vida() == 5)
			nm = "5º rec";
		if (this.getNr_Vida() == 6)
			nm = "6º rec";
		return nm;
	}

	public void setTx_Situacao(String tx_Situacao) {
		this.tx_Situacao = tx_Situacao;
	}

	public String getOid_Veiculo() {
		return oid_Veiculo;
	}

	public void setOid_Veiculo(String oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}

	public String getCd_Pneu() {
		return cd_Pneu;
	}

	public void setCd_Pneu(String pneu) {
		cd_Pneu = pneu;
	}

	public String getNr_Cnpj_Cpf() {
		return nr_Cnpj_Cpf;
	}

	public void setNr_Cnpj_Cpf(String nr_Cnpj_Cpf) {
		this.nr_Cnpj_Cpf = nr_Cnpj_Cpf;
	}

	public String getOid_Fornecedor() {
		return oid_Fornecedor;
	}

	public void setOid_Fornecedor(String oid_Fornecedor) {
		this.oid_Fornecedor = oid_Fornecedor;
	}

	public String getNr_Placa() {
		return nr_Placa;
	}

	public void setNr_Placa(String nr_Placa) {
		this.nr_Placa = nr_Placa;
	}

	public long getOid_Pneu() {
		return oid_Pneu;
	}

	public void setOid_Pneu(long oid_Pneu) {
		this.oid_Pneu = oid_Pneu;
	}

	public String getCd_Modelo_Pneu() {
		return cd_Modelo_Pneu;
	}

	public void setCd_Modelo_Pneu(String cd_Modelo_Pneu) {
		this.cd_Modelo_Pneu = cd_Modelo_Pneu;
	}
}