package com.master.ed;


public class AcertoED
{
	private long nr_acerto;
	private String DT_Emissao_inicio;
	private String DT_Emissao_final;
	private String DT_Saida;
	private String DT_Chegada;
	private long NR_Kilometragem_Chegada;
	private double VL_Total_Frete;
	private double VL_Adiantamento_Viagem;
	private double VL_Total_Despesas_Faturadas;
	private double vl_total_despesas_pagas;
	private String tx_observacao;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private long oid;
	private long oid_Ordem_Servico;
	private long oid_Unidade;
	private String oid_Motorista;
	private String oid_Veiculo;
	private String nr_frota;
	private String nr_frota_fim;
	private java.util.Collection acertosDetalhes;
	private double oid_ordem_servico;

	public AcertoED(){
	}

  public String getDm_Stamp() {
    return Dm_Stamp;
  }
  public String getDT_Chegada() {
    return DT_Chegada;
  }

  public String getDT_Saida() {
    return DT_Saida;
  }
  public String getDt_Stamp() {
    return Dt_Stamp;
  }
  public long getNR_Kilometragem_Chegada() {
    return NR_Kilometragem_Chegada;
  }
  public long getOid() {
    return oid;
  }
  public String getOid_Motorista() {
    return oid_Motorista;
  }
  public long getOid_Unidade() {
    return oid_Unidade;
  }
  public String getOid_Veiculo() {
    return oid_Veiculo;
  }
  public String getUsuario_Stamp() {
    return Usuario_Stamp;
  }
  public double getVL_Adiantamento_Viagem() {
    return VL_Adiantamento_Viagem;
  }
  public double getVL_Total_Despesas_Faturadas() {
    return VL_Total_Despesas_Faturadas;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public void setVL_Total_Despesas_Faturadas(double VL_Total_Despesas_Faturadas) {
    this.VL_Total_Despesas_Faturadas = VL_Total_Despesas_Faturadas;
  }
  public void setVL_Adiantamento_Viagem(double VL_Adiantamento_Viagem) {
    this.VL_Adiantamento_Viagem = VL_Adiantamento_Viagem;
  }
  public void setUsuario_Stamp(String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }
  public void setOid_Veiculo(String oid_Veiculo) {
    this.oid_Veiculo = oid_Veiculo;
  }
  public void setOid_Unidade(long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }
  public void setOid_Motorista(String oid_Motorista) {
    this.oid_Motorista = oid_Motorista;
  }
  public void setOid(long oid) {
    this.oid = oid;
  }
  public void setNR_Kilometragem_Chegada(long NR_Kilometragem_Chegada) {
    this.NR_Kilometragem_Chegada = NR_Kilometragem_Chegada;
  }
  public void setDt_Stamp(String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }
  public void setDT_Saida(String DT_Saida) {
    this.DT_Saida = DT_Saida;
  }

  public void setDT_Chegada(String DT_Chegada) {
    this.DT_Chegada = DT_Chegada;
  }
  public void setDm_Stamp(String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }
  public String getNr_frota() {
    return nr_frota;
  }
  public void setNr_frota(String nr_frota) {
    this.nr_frota = nr_frota;
  }
  public String getDT_Emissao_final() {
    return DT_Emissao_final;
  }
  public String getDT_Emissao_inicio() {
    return DT_Emissao_inicio;
  }
  public void setDT_Emissao_final(String DT_Emissao_final) {
    this.DT_Emissao_final = DT_Emissao_final;
  }
  public void setDT_Emissao_inicio(String DT_Emissao_inicio) {
    this.DT_Emissao_inicio = DT_Emissao_inicio;
  }
  public java.util.Collection getAcertosDetalhes() {
    return acertosDetalhes;
  }
  public void setAcertosDetalhes(java.util.Collection acertosDetalhes) {
    this.acertosDetalhes = acertosDetalhes;
  }
  public double getOid_ordem_servico() {
    return oid_ordem_servico;
  }
  public void setOid_ordem_servico(double oid_ordem_servico) {
    this.oid_ordem_servico = oid_ordem_servico;
  }
  public double getVl_total_despesas_pagas() {
    return vl_total_despesas_pagas;
  }
  public void setVl_total_despesas_pagas(double vl_total_despesas_pagas) {
    this.vl_total_despesas_pagas = vl_total_despesas_pagas;
  }
  public long getNr_acerto() {
    return nr_acerto;
  }
  public void setNr_acerto(long nr_acerto) {
    this.nr_acerto = nr_acerto;
  }
  public void setTx_observacao(String tx_observacao) {
    this.tx_observacao = tx_observacao;
  }
  public String getTx_observacao() {
    return tx_observacao;
  }
  public String getNr_frota_fim() {
    return nr_frota_fim;
  }
  public void setNr_frota_fim(String nr_frota_fim) {
    this.nr_frota_fim = nr_frota_fim;
  }

}
