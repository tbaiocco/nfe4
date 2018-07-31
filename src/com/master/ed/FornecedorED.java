package com.master.ed;

/**
 * @author Regis
 *
 */
public class FornecedorED extends RelatorioBaseED {

	private static final long serialVersionUID = 6272299895398061731L;
	
	public FornecedorED() {
	}
	
	private long oid_Empresa;
	private String oid_Fornecedor;
	private String oid_Pessoa;
	private String nr_Cnpj_Cic;
    private String nm_Razao_Social;
    private String nm_Endereco;
    private String nm_Bairro;
    private String nr_Cep;
    private String nm_Inscricao_Estadual;
    private String nm_Cidade;    
    private String cd_Estado;
    private String nr_Telefone;
    private String nr_Fax;
    private String nm_eMail;
	private String msg_Stamp;

	public String getMsg_Stamp() {
		return msg_Stamp;
	}
	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}
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
	public String getNr_Telefone() {
		return nr_Telefone;
	}
	public void setNr_Telefone(String nr_Telefone) {
		this.nr_Telefone = nr_Telefone;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public String getOid_Fornecedor() {
		return oid_Fornecedor;
	}
	public void setOid_Fornecedor(String oid_Fornecedor) {
		this.oid_Fornecedor = oid_Fornecedor;
	}
	public String getOid_Pessoa() {
		return oid_Pessoa;
	}
	public void setOid_Pessoa(String oid_Pessoa) {
		this.oid_Pessoa = oid_Pessoa;
	}
	public String getNm_eMail() {
		return nm_eMail;
	}
	public void setNm_eMail(String nm_eMail) {
		this.nm_eMail = nm_eMail;
	}
	public String getNr_Cnpj_Cic() {
		return nr_Cnpj_Cic;
	}
	public void setNr_Cnpj_Cic(String nr_Cnpj_Cic) {
		this.nr_Cnpj_Cic = nr_Cnpj_Cic;
	}
	public String getNr_Fax() {
		return nr_Fax;
	}
	public void setNr_Fax(String nr_Fax) {
		this.nr_Fax = nr_Fax;
	}


}
