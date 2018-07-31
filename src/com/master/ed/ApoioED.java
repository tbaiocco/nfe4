package com.master.ed;

public class ApoioED extends MasterED{

  private String DM_Tipo_Apoio;
  private String NM_Contato;
  private String NM_Telefone;
  private String NM_Referencia;
  private long OID_Apoio;
  private String NM_Apoio;
  private double NR_KM;
  private long OID_Rota;
  private long OID_Cidade;
  private long OID_Cidade_Destino;
  private long OID_Cidade_Apoio;
  private String NM_Endereco;
  private String NR_CEP;
  private String DM_Apoio_Rastreado;
  private String CD_Roteiro;
  
  private String NM_Rodovia;
  private String NM_Trecho;
  private String NM_Origem;
  private String NM_Destino;

  public void setOID_Rota(long OID_Rota) {
    this.OID_Rota = OID_Rota;
  }
  public long getOID_Rota() {
    return OID_Rota;
  }
  public void setDM_Tipo_Apoio(String DM_Tipo_Apoio) {
    this.DM_Tipo_Apoio = DM_Tipo_Apoio;
  }
  public String getDM_Tipo_Apoio() {
    return DM_Tipo_Apoio;
  }
  public void setOID_Apoio(long OID_Apoio) {
    this.OID_Apoio = OID_Apoio;
  }
  public long getOID_Apoio() {
    return OID_Apoio;
  }
  public void setNM_Contato(String NM_Contato) {
    this.NM_Contato = NM_Contato;
  }
  public String getNM_Contato() {
    return NM_Contato;
  }
  public void setNM_Telefone(String NM_Telefone) {
    this.NM_Telefone = NM_Telefone;
  }
  public String getNM_Telefone() {
    return NM_Telefone;
  }
  public void setNR_KM(double NR_KM) {
    this.NR_KM = NR_KM;
  }
  public double getNR_KM() {
    return NR_KM;
  }
  public void setNM_Referencia(String NM_Referencia) {
    this.NM_Referencia = NM_Referencia;
  }
  public String getNM_Referencia() {
    return NM_Referencia;
  }
  public void setNM_Apoio(String NM_Apoio) {
    this.NM_Apoio = NM_Apoio;
  }
  public String getNM_Apoio() {
    return NM_Apoio;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }
  public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }
  public long getOID_Cidade_Destino() {
    return OID_Cidade_Destino;
  }
  public void setOID_Cidade_Apoio(long OID_Cidade_Apoio) {
    this.OID_Cidade_Apoio = OID_Cidade_Apoio;
  }
  public long getOID_Cidade_Apoio() {
    return OID_Cidade_Apoio;
  }
  public void setNM_Endereco(String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }
  public String getNM_Endereco() {
    return NM_Endereco;
  }
  public void setNR_CEP(String NR_CEP) {
    this.NR_CEP = NR_CEP;
  }
  public String getNR_CEP() {
    return NR_CEP;
  }
  public void setDM_Apoio_Rastreado(String DM_Apoio_Rastreado) {
    this.DM_Apoio_Rastreado = DM_Apoio_Rastreado;
  }
  public String getDM_Apoio_Rastreado() {
    return DM_Apoio_Rastreado;
  }
  public void setCD_Roteiro(String CD_Roteiro) {
    this.CD_Roteiro = CD_Roteiro;
  }
  public String getCD_Roteiro() {
    return CD_Roteiro;
  }

	public String getNM_Destino() {
	    return NM_Destino;
	}
	public void setNM_Destino(String destino) {
	    NM_Destino = destino;
	}
	public String getNM_Origem() {
	    return NM_Origem;
	}
	public void setNM_Origem(String origem) {
	    NM_Origem = origem;
	}
	public String getNM_Rodovia() {
	    return NM_Rodovia;
	}
	public void setNM_Rodovia(String rodovia) {
	    NM_Rodovia = rodovia;
	}
	public String getNM_Trecho() {
	    return NM_Trecho;
	}
	public void setNM_Trecho(String trecho) {
	    NM_Trecho = trecho;
	}
}