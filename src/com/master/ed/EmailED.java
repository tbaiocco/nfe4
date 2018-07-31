package com.master.ed;

public class EmailED
    extends MasterED {

  private String OID_Pessoa;
  private String DT_Envio;
  private String HR_Envio;
  private String DT_Email;
  private String NM_Email_Origem;
  private String TX_Mensagem;
  private String OID_Email;
  private String DT_Envio_Inicial;
  private String DT_Envio_Final;

  private String DM_Situacao;
  private String DM_Relatorio;
  private long OID_Tipo_Padrao;
  private String NM_Usuario;

  private String HR_Email;

  private String Usuario_Stamp;

  private String DT_Stamp;

  private String DM_Stamp;

  private String NM_Tipo_Padrao;

  private String CD_Tipo_Padrao;

  private String DM_Tipo_Email;

  private String OID_Email_Enviado;
  private String NM_Tipo_Email;
  private String OID_Pessoa_Usuario;
  private long oid_Usuario;
  private String NM_Host;
  private String NM_Username;
  private String NM_Senha;
  private String NM_File;
  private String NM_Path;
  private String NM_Subject;
  private String NM_Email_Destino;
  private String NR_Porta;
  private String DM_Autenticacao;
  private String NM_Protocolo;

  private String NM_File2;
  private String NM_Path2;

  public void setOID_Email (String OID_Email) {
    this.OID_Email = OID_Email;
  }

  public String getOID_Email () {
    return OID_Email;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setDT_Envio (String DT_Envio) {
    this.DT_Envio = DT_Envio;
  }

  public String getDT_Envio () {
    return DT_Envio;
  }

  public void setDT_Email (String DT_Email) {
    this.DT_Email = DT_Email;
  }

  public String getDT_Email () {
    return DT_Email;
  }

  public void setNM_Email_Origem (String NM_Email_Origem) {

    this.NM_Email_Origem = NM_Email_Origem;
  }

  public String getNM_Email_Origem () {

    return NM_Email_Origem;
  }

  public void setTX_Mensagem (String TX_Mensagem) {
    this.TX_Mensagem = TX_Mensagem;
  }

  public String getTX_Mensagem () {
    return TX_Mensagem;
  }

  public void setDT_Envio_Inicial (String DT_Envio_Inicial) {
    this.DT_Envio_Inicial = DT_Envio_Inicial;
  }

  public String getDT_Envio_Inicial () {
    return DT_Envio_Inicial;
  }

  public void setDT_Envio_Final (String DT_Envio_Final) {
    this.DT_Envio_Final = DT_Envio_Final;
  }

  public String getDT_Envio_Final () {
    return DT_Envio_Final;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public String getHR_Envio () {
    return HR_Envio;
  }

  public void setHR_Envio (String emissao) {
    HR_Envio = emissao;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setOID_Tipo_Padrao (long OID_Tipo_Padrao) {
    this.OID_Tipo_Padrao = OID_Tipo_Padrao;
  }

  public long getOID_Tipo_Padrao () {
    return OID_Tipo_Padrao;
  }

  public String getCD_Tipo_Padrao () {
    return CD_Tipo_Padrao;
  }

  public void setCD_Tipo_Padrao (String CD_Tipo_Padrao) {
    this.CD_Tipo_Padrao = CD_Tipo_Padrao;
  }

  public String getDM_Stamp () {

    return DM_Stamp;
  }

  public void setDM_Stamp (String DM_Stamp) {

    this.DM_Stamp = DM_Stamp;
  }

  public void setDT_Stamp (String DT_Stamp) {

    this.DT_Stamp = DT_Stamp;
  }

  public void setHR_Email (String HR_Email) {
    this.HR_Email = HR_Email;
  }

  public String getDT_Stamp () {

    return DT_Stamp;
  }

  public String getHR_Email () {
    return HR_Email;
  }

  public void setNM_Tipo_Padrao (String NM_Tipo_Padrao) {
    this.NM_Tipo_Padrao = NM_Tipo_Padrao;
  }

  public String getNM_Tipo_Padrao () {
    return NM_Tipo_Padrao;
  }

  public String getNM_Usuario () {
    return NM_Usuario;
  }

  public void setNM_Usuario (String NM_Usuario) {
    this.NM_Usuario = NM_Usuario;
  }

  public long getOid_Usuario () {
    return oid_Usuario;
  }

  public String getNM_File () {

    return NM_File;
  }

  public String getNM_Path () {

    return NM_Path;
  }

  public String getNM_Protocolo () {
    return NM_Protocolo;
  }

  public String getDM_Autenticacao () {
    return DM_Autenticacao;
  }

  public String getNR_Porta () {
    return NR_Porta;
  }

  public String getNM_Email_Destino () {

    return NM_Email_Destino;
  }

  public String getNM_Subject () {
    return NM_Subject;
  }

  public String getNM_Senha () {
    return NM_Senha;
  }

  public String getNM_Username () {
    return NM_Username;
  }

  public String getNM_Host () {
    return NM_Host;
  }

  public String getOID_Pessoa_Usuario () {
    return OID_Pessoa_Usuario;
  }

  public String getNM_Tipo_Email () {
    return NM_Tipo_Email;
  }

  public String getOID_Email_Enviado () {
    return OID_Email_Enviado;
  }

  public String getDM_Tipo_Email () {
    return DM_Tipo_Email;
  }

  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setOid_Usuario (long oid_Usuario) {
    this.oid_Usuario = oid_Usuario;
  }

  public void setNM_File (String NM_File) {

    this.NM_File = NM_File;
  }

  public void setNM_Path (String NM_Path) {

    this.NM_Path = NM_Path;
  }

  public void setNM_Protocolo (String NM_Protocolo) {
    this.NM_Protocolo = NM_Protocolo;
  }

  public void setDM_Autenticacao (String DM_Autenticacao) {
    this.DM_Autenticacao = DM_Autenticacao;
  }

  public void setNR_Porta (String NR_Porta) {
    this.NR_Porta = NR_Porta;
  }

  public void setNM_Email_Destino (String NM_Email_Destino) {

    this.NM_Email_Destino = NM_Email_Destino;
  }

  public void setNM_Subject (String NM_Subject) {
    this.NM_Subject = NM_Subject;
  }

  public void setNM_Senha (String NM_Senha) {
    this.NM_Senha = NM_Senha;
  }

  public void setNM_Username (String NM_Username) {
    this.NM_Username = NM_Username;
  }

  public void setNM_Host (String NM_Host) {
    this.NM_Host = NM_Host;
  }

  public void setOID_Pessoa_Usuario (String OID_Pessoa_Usuario) {
    this.OID_Pessoa_Usuario = OID_Pessoa_Usuario;
  }

  public void setNM_Tipo_Email (String NM_Tipo_Email) {
    this.NM_Tipo_Email = NM_Tipo_Email;
  }

  public void setOID_Email_Enviado (String OID_Email_Enviado) {
    this.OID_Email_Enviado = OID_Email_Enviado;
  }

  public void setDM_Tipo_Email (String DM_Tipo_Email) {
    this.DM_Tipo_Email = DM_Tipo_Email;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

public String getNM_File2() {
	return NM_File2;
}

public void setNM_File2(String file2) {
	NM_File2 = file2;
}

public String getNM_Path2() {
	return NM_Path2;
}

public void setNM_Path2(String path2) {
	NM_Path2 = path2;
}

}
