package com.master.ed;

public class Ordem_ManifestoED extends MasterED{

  private String DT_Ordem_Manifesto;
  private String HR_Ordem_Manifesto;
  private String DT_Emissao;
  private long NR_Manifesto;
  private long OID_Unidade;
  private String CD_Unidade;
  private long NR_Ordem_Frete;
  private long OID_Cidade_Destino;
  private String OID_Manifesto;
  private String OID_Ordem_Frete;
  private String OID_Ordem_Manifesto;
  private String OID_Veiculo;
  private String NR_Master;
  private String nr_Ordem_Frete;
  private String DM_Frete;
  
  private String DM_MIC;

	public String getDM_Frete() {
		return DM_Frete;
	}
	public void setDM_Frete(String DM_Frete) {
		this.DM_Frete = DM_Frete;
	}
  public void setOID_Manifesto(String OID_Manifesto) {
    this.OID_Manifesto = OID_Manifesto;
  }
  public String getOID_Manifesto() {
    return OID_Manifesto;
  }
  public void setOID_Ordem_Manifesto(String OID_Ordem_Manifesto) {
    this.OID_Ordem_Manifesto = OID_Ordem_Manifesto;
  }
  public String getOID_Ordem_Manifesto() {
    return OID_Ordem_Manifesto;
  }
  public void setDT_Ordem_Manifesto(String DT_Ordem_Manifesto) {
    this.DT_Ordem_Manifesto = DT_Ordem_Manifesto;
  }
  public String getDT_Ordem_Manifesto() {
    return DT_Ordem_Manifesto;
  }
  public void setHR_Ordem_Manifesto(String HR_Ordem_Manifesto) {
    this.HR_Ordem_Manifesto = HR_Ordem_Manifesto;
  }
  public String getHR_Ordem_Manifesto() {
    return HR_Ordem_Manifesto;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public void setNR_Manifesto(long NR_Manifesto) {
    this.NR_Manifesto = NR_Manifesto;
  }
  public long getNR_Manifesto() {
    return NR_Manifesto;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }
  public String getOID_Ordem_Frete() {
    return OID_Ordem_Frete;
  }
  public void setNR_Ordem_Frete(long NR_Ordem_Frete) {
    this.NR_Ordem_Frete = NR_Ordem_Frete;
  }
  public long getNR_Ordem_Frete() {
    return NR_Ordem_Frete;
  }
  public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }
  public long getOID_Cidade_Destino() {
    return OID_Cidade_Destino;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setNR_Master(String NR_Master) {
    this.NR_Master = NR_Master;
  }
  public String getNR_Master() {
    return NR_Master;
  }
  public void setNr_Ordem_Frete(String nr_Ordem_Frete) {
    this.nr_Ordem_Frete = nr_Ordem_Frete;
  }
  public String getNr_Ordem_Frete() {
    return nr_Ordem_Frete;
  }

public String getDM_MIC() {
    return DM_MIC;
}
public void setDM_MIC(String dm_mic) {
    DM_MIC = dm_mic;
}
}