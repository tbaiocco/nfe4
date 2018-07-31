package com.master.ed;

import java.io.*;

public class ProcessoED extends RelatorioBaseED implements Serializable {

  public ProcessoED () {
    super ();
  }

  public ProcessoED (String oid_Processo) {
    this.oid_Processo = oid_Processo;
  }

  private String oid_Processo;
  private String nr_Processo;
  private String dm_Tipo_Processo;
  private Integer oid_Conta;
  private Integer oid_Moeda;
  private String oid_Pessoa;
  private String nm_Razao_Social;
  private String cd_Moeda;
  private String cd_Conta;
  private String nr_CNPJ_CPF;
  private String nm_Moeda;
  private String nm_Conta;
  private String cd_Banco;
  private String Nm_Empresa;
  private Integer oid_Empresa;
  private String cd_Empresa;
  private String DM_Situacao;
  private String NM_Contato;

  private String DT_Abertura;
  private String HR_Abertura;
  private String DT_Encerramento;
  private String HR_Encerramento;
  private String NR_Placa;
  private String oid_Veiculo;
  private String oid_Motorista;
  private String NR_CNPJ_CPF_Motorista;
  private String NM_Razao_Social_Motorista;
  private int NR_Odometro_Inicial;
  private int NR_Odometro_Final;
  private String DM_Tipo_Faturamento;
  private String DM_Meio_Pagamento;
  private String NM_Situacao;
  private String NM_Ajudante;
  private String TX_Servico;
  private String DT_Previsao;
  private String HR_Previsao;
  private double VL_Credito;
  private double VL_Debito;
  private long oid_Lote_Faturamento;
  private double VL_Saldo;

  private String DT_Abertura_Inicial;
  private String DT_Abertura_Final;
  private String DM_Ordenacao;
  private String DT_Previsao_Inicial;
  private String DT_Previsao_Final;
  private String DM_Liberado_Cancelar;
  private long oid_Duplicata;
  private String NR_Duplicata;

  public String getDescTipo_Faturamento () {
    if ("N".equals (DM_Tipo_Faturamento))
      return "N.Fiscal";
    else if ("C".equals (DM_Tipo_Faturamento))
      return "Cto Frete";
    else if ("R".equals (DM_Tipo_Faturamento))
      return "Recibo";
    else return "Não encontrado!";
  }

  public String getDescMeio_Pagamento () {
    if ("V".equals (DM_Meio_Pagamento))
      return "A Vista";
    else if ("P".equals (DM_Meio_Pagamento))
      return "Prazo c/Cheque";
    else if ("B".equals (DM_Meio_Pagamento))
      return "Prazo c/Boleto";
    else if ("C".equals (DM_Meio_Pagamento))
      return "Crédito Conta";
    else return "Não encontrado!";
  }

  public String getDm_Tipo_Processo () {
    return dm_Tipo_Processo;
  }

  public String getNr_Processo () {
    return nr_Processo;
  }

  public Integer getOid_Conta () {
    return oid_Conta;
  }

  public String getOid_Processo () {
    return oid_Processo;
  }

  public Integer getOid_Moeda () {
    return oid_Moeda;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_Moeda (Integer oid_Moeda) {
    this.oid_Moeda = oid_Moeda;
  }

  public void setOid_Processo (String oid_Processo) {
    this.oid_Processo = oid_Processo;
  }

  public void setOid_Conta (Integer oid_Conta) {
    this.oid_Conta = oid_Conta;
  }

  public void setNr_Processo (String nr_Processo) {
    this.nr_Processo = nr_Processo;
  }

  public void setDm_Tipo_Processo (String dm_Tipo_Processo) {
    this.dm_Tipo_Processo = dm_Tipo_Processo;
  }

  public void setNm_Razao_Social (String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }

  public String getNm_Razao_Social () {
    return nm_Razao_Social;
  }

  public void setCd_Moeda (String cd_Moeda) {
    this.cd_Moeda = cd_Moeda;
  }

  public String getCd_Moeda () {
    return cd_Moeda;
  }

  public void setCd_Conta (String cd_Conta) {
    this.cd_Conta = cd_Conta;
  }

  public String getCd_Conta () {
    return cd_Conta;
  }

  public void setNr_CNPJ_CPF (String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }

  public String getNr_CNPJ_CPF () {
    return nr_CNPJ_CPF;
  }

  public void setNm_Moeda (String nm_Moeda) {
    this.nm_Moeda = nm_Moeda;
  }

  public String getNm_Moeda () {
    return nm_Moeda;
  }

  public void setNm_Conta (String nm_Conta) {
    this.nm_Conta = nm_Conta;
  }

  public String getNm_Conta () {
    return nm_Conta;
  }

  public void setCd_Banco (String cd_Banco) {
    this.cd_Banco = cd_Banco;
  }

  public String getCd_Banco () {
    return cd_Banco;
  }

  public void setOid_Empresa (Integer oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }

  public Integer getOid_Empresa () {
    return oid_Empresa;
  }

  public void setNm_Empresa (String Nm_Empresa) {
    this.Nm_Empresa = Nm_Empresa;
  }

  public String getNm_Empresa () {
    return Nm_Empresa;
  }

  public void setCd_Empresa (String cd_Empresa) {
    this.cd_Empresa = cd_Empresa;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getCd_Empresa () {
    return cd_Empresa;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public String getDM_Meio_Pagamento () {
    return DM_Meio_Pagamento;
  }

  public void setDM_Meio_Pagamento (String meio_Pagamento) {
    DM_Meio_Pagamento = meio_Pagamento;
  }

  public String getDM_Tipo_Faturamento () {
    return DM_Tipo_Faturamento;
  }

  public void setDM_Tipo_Faturamento (String tipo_Faturamento) {
    DM_Tipo_Faturamento = tipo_Faturamento;
  }

  public String getDT_Abertura () {
    return DT_Abertura;
  }

  public void setDT_Abertura (String abertura) {
    DT_Abertura = abertura;
  }

  public String getDT_Encerramento () {
    return DT_Encerramento;
  }

  public void setDT_Encerramento (String encerramento) {
    DT_Encerramento = encerramento;
  }

  public String getHR_Abertura () {
    return HR_Abertura;
  }

  public void setHR_Abertura (String abertura) {
    HR_Abertura = abertura;
  }

  public String getHR_Encerramento () {
    return HR_Encerramento;
  }

  public void setHR_Encerramento (String encerramento) {
    HR_Encerramento = encerramento;
  }

  public String getNM_Razao_Social_Motorista () {
    return NM_Razao_Social_Motorista;
  }

  public void setNM_Razao_Social_Motorista (String razao_Social_Motorista) {
    NM_Razao_Social_Motorista = razao_Social_Motorista;
  }

  public String getNR_CNPJ_CPF_Motorista () {
    return NR_CNPJ_CPF_Motorista;
  }

  public void setNR_CNPJ_CPF_Motorista (String motorista) {
    NR_CNPJ_CPF_Motorista = motorista;
  }

  public int getNR_Odometro_Final () {
    return NR_Odometro_Final;
  }

  public void setNR_Odometro_Final (int odometro_Final) {
    NR_Odometro_Final = odometro_Final;
  }

  public int getNR_Odometro_Inicial () {
    return NR_Odometro_Inicial;
  }

  public void setNR_Odometro_Inicial (int odometro_Inicial) {
    NR_Odometro_Inicial = odometro_Inicial;
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public void setNR_Placa (String placa) {
    NR_Placa = placa;
  }

  public String getOid_Motorista () {
    return oid_Motorista;
  }

  public void setOid_Motorista (String oid_Motorista) {
    this.oid_Motorista = oid_Motorista;
  }

  public String getOid_Veiculo () {
    return oid_Veiculo;
  }

  public long getOid_Lote_Faturamento () {
    return oid_Lote_Faturamento;
  }

  public long getOid_Duplicata () {
    return oid_Duplicata;
  }

  public String getNR_Duplicata () {
    return NR_Duplicata;
  }

  public String getDM_Liberado_Cancelar () {
    return DM_Liberado_Cancelar;
  }

  public String getDT_Previsao_Final () {
    return DT_Previsao_Final;
  }

  public String getDT_Previsao_Inicial () {
    return DT_Previsao_Inicial;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public double getVL_Debito () {
    return VL_Debito;
  }

  public double getVL_Credito () {
    return VL_Credito;
  }

  public String getHR_Previsao () {
    return HR_Previsao;
  }

  public String getDT_Previsao () {
    return DT_Previsao;
  }

  public String getTX_Servico () {
    return TX_Servico;
  }

  public String getNM_Ajudante () {
    return NM_Ajudante;
  }

  public String getNM_Situacao () {
    return NM_Situacao;
  }

  public void setOid_Veiculo (String oid_Veiculo) {
    this.oid_Veiculo = oid_Veiculo;
  }

  public void setOid_Lote_Faturamento (long oid_Lote_Faturamento) {
    this.oid_Lote_Faturamento = oid_Lote_Faturamento;
  }

  public void setOid_Duplicata (long oid_Duplicata) {
    this.oid_Duplicata = oid_Duplicata;
  }

  public void setNR_Duplicata (String NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }

  public void setDM_Liberado_Cancelar (String DM_Liberado_Cancelar) {
    this.DM_Liberado_Cancelar = DM_Liberado_Cancelar;
  }

  public void setDT_Previsao_Final (String DT_Previsao_Final) {
    this.DT_Previsao_Final = DT_Previsao_Final;
  }

  public void setDT_Previsao_Inicial (String DT_Previsao_Inicial) {
    this.DT_Previsao_Inicial = DT_Previsao_Inicial;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public void setVL_Debito (double VL_Debito) {
    this.VL_Debito = VL_Debito;
  }

  public void setVL_Credito (double VL_Credito) {
    this.VL_Credito = VL_Credito;
  }

  public void setHR_Previsao (String HR_Previsao) {
    this.HR_Previsao = HR_Previsao;
  }

  public void setDT_Previsao (String DT_Previsao) {
    this.DT_Previsao = DT_Previsao;
  }

  public void setTX_Servico (String TX_Servico) {
    this.TX_Servico = TX_Servico;
  }

  public void setNM_Ajudante (String NM_Ajudante) {
    this.NM_Ajudante = NM_Ajudante;
  }

  public void setNM_Situacao (String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }

  public String getNM_Contato () {
    return NM_Contato;
  }

  public void setNM_Contato (String contato) {
    NM_Contato = contato;
  }

public String getDT_Abertura_Final() {
	return DT_Abertura_Final;
}

public void setDT_Abertura_Final(String abertura_Final) {
	DT_Abertura_Final = abertura_Final;
}

public String getDT_Abertura_Inicial() {
	return DT_Abertura_Inicial;
}

public void setDT_Abertura_Inicial(String abertura_Inicial) {
	DT_Abertura_Inicial = abertura_Inicial;
}

public String getDM_Ordenacao() {
	return DM_Ordenacao;
}

public void setDM_Ordenacao(String ordenacao) {
	DM_Ordenacao = ordenacao;
}

}
