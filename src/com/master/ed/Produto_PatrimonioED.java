package com.master.ed;

public class Produto_PatrimonioED extends MasterED {

    public Produto_PatrimonioED() {
        super();
  }
    public Produto_PatrimonioED(String oid_Produto_Patrimonio) {
        this.oid_Produto_Patrimonio = oid_Produto_Patrimonio;
    }

  private String oid_Produto_Patrimonio;
  private String nm_Produto_Patrimonio;
  private Integer oid_Categoria;
  private String nm_Categoria;
  private String cd_Categoria;
  private Integer oid_Conta;
  private String nm_Conta;
  private String cd_Conta;

public String getCd_Categoria() {
	return cd_Categoria;
}
public void setCd_Categoria(String cd_Categoria) {
	this.cd_Categoria = cd_Categoria;
}
public String getNm_Produto_Patrimonio() {
	return nm_Produto_Patrimonio;
}
public void setNm_Produto_Patrimonio(String nm_Produto_Patrimonio) {
	this.nm_Produto_Patrimonio = nm_Produto_Patrimonio;
}
public String getNm_Categoria() {
	return nm_Categoria;
}
public void setNm_Categoria(String nm_Categoria) {
	this.nm_Categoria = nm_Categoria;
}
public String getOid_Produto_Patrimonio() {
	return oid_Produto_Patrimonio;
}
public void setOid_Produto_Patrimonio(String oid_Produto_Patrimonio) {
	this.oid_Produto_Patrimonio = oid_Produto_Patrimonio;
}
public Integer getOid_Categoria() {
	return oid_Categoria;
}
public void setOid_Categoria(Integer oid_Categoria) {
	this.oid_Categoria = oid_Categoria;
}
public String getCd_Conta() {
	return cd_Conta;
}
public void setCd_Conta(String cd_Conta) {
	this.cd_Conta = cd_Conta;
}
public String getNm_Conta() {
	return nm_Conta;
}
public void setNm_Conta(String nm_Conta) {
	this.nm_Conta = nm_Conta;
}
public Integer getOid_Conta() {
	return oid_Conta;
}
public void setOid_Conta(Integer oid_Conta) {
	this.oid_Conta = oid_Conta;
}

}
