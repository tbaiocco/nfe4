package com.master.ed;

/**
 * @author Kayel
 *
 */
public class Empresa_StgpED extends RelatorioBaseED {

	private static final long serialVersionUID = 2197194940022060118L;

	public Empresa_StgpED() {
	}
	
	private long oid_Empresa;
	private String nr_Cnpj;
    private String nm_Razao_Social;
    private String nm_Endereco;
    private String nm_Bairro;
    private String nr_Cep;
    private String nm_Inscricao_Estadual;
    private String nm_Cidade;    
    private String cd_Estado;
    private String nr_Telefone;
    private String nr_Fax;
    private String nm_Email;
    private String dm_Tipo_Frota;
    private String nm_Tipo_Frota;

    // Funções para o jsp
    private boolean incluidoOK;
    private boolean codigoExistente;
    
	public String getCd_Estado() {
		return cd_Estado;
	}
	public void setCd_Estado(String cd_Estado) {
		this.cd_Estado = cd_Estado;
	}
	public String getNm_Bairro() {
		return nm_Bairro;
	}
	public void setNm_Bairro(String nm_Bairro) {
		this.nm_Bairro = nm_Bairro;
	}
	public String getNm_Cidade() {
		return nm_Cidade;
	}
	public void setNm_Cidade(String nm_Cidade) {
		this.nm_Cidade = nm_Cidade;
	}
	public String getNm_Endereco() {
		return nm_Endereco;
	}
	public void setNm_Endereco(String nm_Endereco) {
		this.nm_Endereco = nm_Endereco;
	}
	public String getNm_Inscricao_Estadual() {
		return nm_Inscricao_Estadual;
	}
	public void setNm_Inscricao_Estadual(String nm_Inscricao_Estadual) {
		this.nm_Inscricao_Estadual = nm_Inscricao_Estadual;
	}
	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}
	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
	}
	public String getNr_Cep() {
		return nr_Cep;
	}
	public void setNr_Cep(String nr_Cep) {
		this.nr_Cep = nr_Cep;
	}
	public String getNr_Fax() {
		return nr_Fax;
	}
	public void setNr_Fax(String nr_Fax) {
		this.nr_Fax = nr_Fax;
	}
	public String getNr_Telefone() {
		return nr_Telefone;
	}
	public void setNr_Telefone(String nr_Telefone) {
		this.nr_Telefone = nr_Telefone;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getDm_Tipo_Frota() {
		return dm_Tipo_Frota;
	}
	public void setDm_Tipo_Frota(String dm_Tipo_Frota) {
		this.dm_Tipo_Frota = dm_Tipo_Frota;
	}
	public String getNm_Tipo_Frota() {
		return nm_Tipo_Frota;
	}
	public void setNm_Tipo_Frota(String nm_Tipo_Frota) {
		this.nm_Tipo_Frota = nm_Tipo_Frota;
	}
	public String getNr_Cnpj() {
		return nr_Cnpj;
	}
	public void setNr_Cnpj(String nr_Cnpj) {
		this.nr_Cnpj = nr_Cnpj;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public String getNm_Email() {
		return nm_Email;
	}
	public void setNm_Email(String nm_Email) {
		this.nm_Email = nm_Email;
	}
	public boolean isCodigoExistente() {
		return codigoExistente;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
	public boolean isIncluidoOK() {
		return incluidoOK;
	}
	public void setIncluidoOK(boolean incluidoOK) {
		this.incluidoOK = incluidoOK;
	}
}
