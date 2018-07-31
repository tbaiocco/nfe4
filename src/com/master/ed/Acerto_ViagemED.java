package com.master.ed;

public class Acerto_ViagemED
    extends RelatorioBaseED implements java.io.Serializable {

  public Acerto_ViagemED () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  private String NM_Carretas_Cavalo ;

  private long NR_Acerto;

  private String DT_Emissao;

  private String DT_Saida;

  private String DT_Chegada;
  
  private String DT_Chegada_Final;

  private long NR_Kilometragem_Chegada;

  private double VL_Total_Frete;

  private double VL_Adiantamento_Viagem;

  private double VL_Total_Despesas_Faturadas;

  private double VL_Total_Despesas_Pagas;

  private String TX_Observacao;

  private String Usuario_Stamp;

  private String Dt_Stamp;
  
  private String DT_Saida_Final;

  private String Dm_Stamp;

  private long oid;

  private long oid_Unidade;

  private String oid_Motorista;

  private String oid_Veiculo;

  private double VL_Comissao;

  private double PE_Comissao;

  private double VL_Comissao_Terceiro;

  private double PE_Comissao_Terceiro;

  private double VL_Frete_Peso;

  private double VL_Frete_Terceiro;

  private double VL_Servico;

  private double VL_Frete_Proprio;

  private long oid_Movimento_Conta_Corrente;

  private double VL_Cotacao;

  private String DM_Situacao;

  private double PE_Base_Comissao;

  private double VL_Base_Comissao;

  private String DM_Impresso;

  private double VL_Recebido;

  private double VL_Devolvido;

  private double VL_Total_Despesas;

  private String OID_Motorista;

  private String OID_Conhecimento;
  private String OID_Nota_Fiscal;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;

  private long OID_Acerto;
  private long OID_Ordem_Servico;
  private long NR_Conhecimento;
  private long NR_Nota_Fiscal;
  private double VL_Total_Extras;
  private double NR_Litros;

  private double VL_TOTAL_FRETE;


  private double PE_Comissao_Acerto;
  private double VL_Base_Comissao_Acerto;
  private long NR_Kilometragem_Saida;
  private long NR_Kilometragem_Total;
  private double NR_Media;
  private String DT_Inicial;
  private String DT_Final;
  private String NM_Razao_Social;
  private String NM_Unidade;
  private long NR_Ordem_Servico;
  private String DT_Ordem_Servico;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private double VL_Retido;
  private double VL_Saldo;
  private String acao;
  private String OID_Conta_Corrente;
  private String DM_Relatorio;
  private long oid_Empresa;
  private String NM_Situacao;
  private String oid_Carreta;
  private String DM_Tipo_Pagamento;
  private double VL_Total_Custo_Carga;
  private double VL_Total_Custo_Descarga;
  private double VL_Total_Carga_Descarga;
  private long OID_Usuario;
  private String NM_Situacao_Conhecimento;

  private String oid_Veiculo2;
  private String oid_Veiculo3;
  private int NR_Kilometragem2;
  private int NR_Kilometragem3;

  private long nr_Kilometragem_Acumulada_Saida;
  private long nr_Kilometragem_Acumulada_Chegada;

  //Relatorio Jasper ACERTO
  private String nm_Motorista;
  private double nr_peso;
  private String nm_Cidade;
  private String nm_Cidade_Destino;
  private String nm_Tipo_Conhecimento;
  private double vl_Nota_Fiscal;
  private String nm_Tipo_Servico;
  private String nm_Fornecedor;
  private long nr_Documento;
  private String dt_Movimento_Ordem_Servico;
  private long nr_Quantidade;
  private double vl_Movimento;
  private String dm_Faturado_Pago;
  private long nr_Ordem_Frete;
  private String nm_Tipo_Adiantamento;
  private double vl_Ordem_Frete;
  private String nm_Adiantamento_Ordem_frete;
  private String nr_Frota;
  private long NR_Kilometragem_Percorido;
  private double NR_Litros_Posto;
  private double NR_Litros_Estoque;
  private double NR_Litros_Total;
  private String TX_Observacao_Saldo;
  private double VL_Frete_Base;
  private double VL_Abastecimento_Interno;

  //Relatorio Jasper ACERTO RESUMO

  private double VL_Receita;
  private double VL_Despesa;
  private double VL_Dia;
  private double VL_KM;
  private double PE_Conta;
  private double NR_KM_Viagem;
  private double NR_Dias_Viagem;
  private double NR_KM_Litro;
  private double NR_KM_Dia;
  private String NM_Marca_Veiculo;
  private String NM_Modelo_Veiculo;
  private String DT_Ano_Veiculo;
  private String NM_Tipo_Veiculo;
  private double VL_Deducoes_PIS_COFINS;

  private long NR_Dias_Ociosidade;
  private String NM_Dias_Ociosidade;
  private double VL_Despesa_KM;

  private double VL_Pedagio;
  private double VL_Descarga;
  private double VL_Manutencao;
  private double VL_outros;
  private double VL_Resultado;

  private String dt_Saida_Filtro;
  private String dt_Chegada_Filtro;

  private double VL_Abastecimento_Estoque;

  private String NM_Financiamento;


  private double NR_KM_Bruto;
  private double NR_KM_Liquido;
  private double VL_Custo_Consumo;
  private double VL_Receita_KM;
  private double VL_Resultado_KM;
  private double PE_Faturamento_Dia;
  private double PE_Resultado;
  private double VL_Ociosidade;

  private double VL_Custo_Litro;
  private double VL_Faturamento_Dia;
  private double VL_Financiamento;
  private double VL_Financiamento_Cavalo;
  private double VL_Financiamento_Carreta;
  private double VL_Custo_Financiamentos;
  private double VL_Financiamento_Carreta_Acerto;
  private double VL_Oficina_Cavalo;
  private double VL_Oficina_Carreta;
  private double VL_Custo_Oficina;
  
  private String DM_Tipo_Veiculo;
  private double VL_Resultado_Geral;
  private double VL_Resultado_Geral_Dia;
  private double VL_Resultado_Geral_KM;
  private double PE_Resultado_Geral;

  
  private String DT_Proximo_Embarque;
  
public double getPE_Resultado_Geral() {
	return PE_Resultado_Geral;
}

public void setPE_Resultado_Geral(double resultado_Geral) {
	PE_Resultado_Geral = resultado_Geral;
}

public double getVL_Resultado_Geral_Dia() {
	return VL_Resultado_Geral_Dia;
}

public void setVL_Resultado_Geral_Dia(double resultado_Geral_Dia) {
	VL_Resultado_Geral_Dia = resultado_Geral_Dia;
}

public double getVL_Resultado_Geral_KM() {
	return VL_Resultado_Geral_KM;
}

public void setVL_Resultado_Geral_KM(double resultado_Geral_KM) {
	VL_Resultado_Geral_KM = resultado_Geral_KM;
}

public double getVL_Resultado_Geral() {
	return VL_Resultado_Geral;
}

public void setVL_Resultado_Geral(double resultado_Geral) {
	VL_Resultado_Geral = resultado_Geral;
}

public double getVL_Abastecimento_Estoque() {
	return VL_Abastecimento_Estoque;
}

public void setVL_Abastecimento_Estoque(double abastecimento_Estoque) {
	VL_Abastecimento_Estoque = abastecimento_Estoque;
}

public double getVL_Resultado() {
	return VL_Resultado;
}

public void setVL_Resultado(double resultado) {
	VL_Resultado = resultado;
}

public double getVL_Manutencao() {
	return VL_Manutencao;
}

public void setVL_Manutencao(double manutencao) {
	VL_Manutencao = manutencao;
}

public double getVL_outros() {
	return VL_outros;
}

public void setVL_outros(double vl_outros) {
	VL_outros = vl_outros;
}

public double getVL_Descarga() {
	return VL_Descarga;
}

public void setVL_Descarga(double descarga) {
	VL_Descarga = descarga;
}

public double getVL_Pedagio() {
	return VL_Pedagio;
}

public void setVL_Pedagio(double pedagio) {
	VL_Pedagio = pedagio;
}

public double getVL_Despesa_KM() {
	return VL_Despesa_KM;
}

public void setVL_Despesa_KM(double despesa_KM) {
	VL_Despesa_KM = despesa_KM;
}

public double getVL_Deducoes_PIS_COFINS() {
	return VL_Deducoes_PIS_COFINS;
}

public void setVL_Deducoes_PIS_COFINS(double deducoes_PIS_COFINS) {
	VL_Deducoes_PIS_COFINS = deducoes_PIS_COFINS;
}

public String getDT_Ano_Veiculo() {
	return DT_Ano_Veiculo;
}

public void setDT_Ano_Veiculo(String ano_Veiculo) {
	DT_Ano_Veiculo = ano_Veiculo;
}

public String getNM_Marca_Veiculo() {
	return NM_Marca_Veiculo;
}

public void setNM_Marca_Veiculo(String marca_Veiculo) {
	NM_Marca_Veiculo = marca_Veiculo;
}

public String getNM_Modelo_Veiculo() {
	return NM_Modelo_Veiculo;
}

public void setNM_Modelo_Veiculo(String modelo_Veiculo) {
	NM_Modelo_Veiculo = modelo_Veiculo;
}

public String getNM_Tipo_Veiculo() {
	return NM_Tipo_Veiculo;
}

public void setNM_Tipo_Veiculo(String tipo_Veiculo) {
	NM_Tipo_Veiculo = tipo_Veiculo;
}

public double getNR_Dias_Viagem() {
	return NR_Dias_Viagem;
}

public void setNR_Dias_Viagem(double dias_Viagem) {
	NR_Dias_Viagem = dias_Viagem;
}

public double getNR_KM_Dia() {
	return NR_KM_Dia;
}

public void setNR_KM_Dia(double dia) {
	NR_KM_Dia = dia;
}

public double getNR_KM_Litro() {
	return NR_KM_Litro;
}

public void setNR_KM_Litro(double litro) {
	NR_KM_Litro = litro;
}

public double getNR_KM_Viagem() {
	return NR_KM_Viagem;
}

public void setNR_KM_Viagem(double viagem) {
	NR_KM_Viagem = viagem;
}

public double getVL_Receita() {
	return VL_Receita;
}

public void setVL_Receita(double receita) {
	VL_Receita = receita;
}

public double getVL_Frete_Base() {
	return VL_Frete_Base;
}

public void setVL_Frete_Base(double frete_Base) {
	VL_Frete_Base = frete_Base;
}

public double getNR_Litros_Estoque() {
	return NR_Litros_Estoque;
}

public void setNR_Litros_Estoque(double litros_Estoque) {
	NR_Litros_Estoque = litros_Estoque;
}

public double getNR_Litros_Posto() {
	return NR_Litros_Posto;
}

public void setNR_Litros_Posto(double litros_Posto) {
	NR_Litros_Posto = litros_Posto;
}

public long getNR_Kilometragem_Percorido() {
	return NR_Kilometragem_Percorido;
}

public void setNR_Kilometragem_Percorido(long kilometragem_Percorido) {
	NR_Kilometragem_Percorido = kilometragem_Percorido;
}

public String getNr_Frota() {
	return nr_Frota;
}

public void setNr_Frota(String nr_Frota) {
	this.nr_Frota = nr_Frota;
}

public String getNm_Adiantamento_Ordem_frete() {
	return nm_Adiantamento_Ordem_frete;
}

public void setNm_Adiantamento_Ordem_frete(String nm_Adiantamento_Ordem_frete) {
	this.nm_Adiantamento_Ordem_frete = nm_Adiantamento_Ordem_frete;
}

public double getVl_Ordem_Frete() {
	return vl_Ordem_Frete;
}

public void setVl_Ordem_Frete(double vl_Ordem_Frete) {
	this.vl_Ordem_Frete = vl_Ordem_Frete;
}

public String getDm_Faturado_Pago() {
	return dm_Faturado_Pago;
}

public void setDm_Faturado_Pago(String dm_Faturado_Pago) {
	this.dm_Faturado_Pago = dm_Faturado_Pago;
}

public double getVl_Movimento() {
	return vl_Movimento;
}

public void setVl_Movimento(double vl_Movimento) {
	this.vl_Movimento = vl_Movimento;
}

public String getDt_Movimento_Ordem_Servico() {
	return dt_Movimento_Ordem_Servico;
}

public void setDt_Movimento_Ordem_Servico(String dt_Movimento_Ordem_Servico) {
	this.dt_Movimento_Ordem_Servico = dt_Movimento_Ordem_Servico;
}

public String getNm_Fornecedor() {
	return nm_Fornecedor;
}

public void setNm_Fornecedor(String nm_Fornecedor) {
	this.nm_Fornecedor = nm_Fornecedor;
}

public String getNm_Tipo_Servico() {
	return nm_Tipo_Servico;
}

public void setNm_Tipo_Servico(String nm_Tipo_Servico) {
	this.nm_Tipo_Servico = nm_Tipo_Servico;
}

public double getVl_Nota_Fiscal() {
	return vl_Nota_Fiscal;
}

public void setVl_Nota_Fiscal(double vl_Nota_Fiscal) {
	this.vl_Nota_Fiscal = vl_Nota_Fiscal;
}

public String getNm_Tipo_Conhecimento() {
	return nm_Tipo_Conhecimento;
}

public void setNm_Tipo_Conhecimento(String nm_Tipo_Conhecimento) {
	this.nm_Tipo_Conhecimento = nm_Tipo_Conhecimento;
}

public String getNm_Cidade_Destino() {
	return nm_Cidade_Destino;
}

public void setNm_Cidade_Destino(String nm_Cidade_Destino) {
	this.nm_Cidade_Destino = nm_Cidade_Destino;
}

public String getNm_Cidade() {
	return nm_Cidade;
}

public void setNm_Cidade(String nm_Cidade) {
	this.nm_Cidade = nm_Cidade;
}

public double getNr_peso() {
	return nr_peso;
}

public void setNr_peso(double nr_peso) {
	this.nr_peso = nr_peso;
}

public String getNm_Motorista() {
	return nm_Motorista;
}

public void setNm_Motorista(String nm_Motorista) {
	this.nm_Motorista = nm_Motorista;
}

public long getNr_Kilometragem_Acumulada_Saida() {
	return nr_Kilometragem_Acumulada_Saida;
}

public void setNr_Kilometragem_Acumulada_Saida(
		long nr_Kilometragem_Acumulada_Saida) {
	this.nr_Kilometragem_Acumulada_Saida = nr_Kilometragem_Acumulada_Saida;
}

public long getNr_Kilometragem_Acumulada_Chegada() {
	return nr_Kilometragem_Acumulada_Chegada;
}

public void setNr_Kilometragem_Acumulada_Chegada(
		long nr_Kilometragem_Acumulada_Chegada) {
	this.nr_Kilometragem_Acumulada_Chegada = nr_Kilometragem_Acumulada_Chegada;
}

public String getDM_Impresso () {
    return DM_Impresso;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public String getDT_Chegada () {
    return DT_Chegada;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public String getDT_Saida () {
    return DT_Saida;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public long getNR_Acerto () {
    return NR_Acerto;
  }

  public long getNR_Kilometragem_Chegada () {
    return NR_Kilometragem_Chegada;
  }

  public long getOid () {
    return oid;
  }

  public String getOid_Motorista () {
    return oid_Motorista;
  }

  public long getOid_Movimento_Conta_Corrente () {
    return oid_Movimento_Conta_Corrente;
  }

  public long getOid_Unidade () {
    return oid_Unidade;
  }

  public String getOid_Veiculo () {
    return oid_Veiculo;
  }

  public String getAcao () {
    return acao;
  }

  public long getOid_Empresa () {
    return oid_Empresa;
  }

  public String getOid_Carreta () {
    return oid_Carreta;
  }

  public long getOID_Usuario () {
    return OID_Usuario;
  }

  public double getVL_Total_Carga_Descarga () {
    return VL_Total_Carga_Descarga;
  }

  public double getVL_Total_Custo_Descarga () {

    return VL_Total_Custo_Descarga;
  }

  public double getVL_Total_Custo_Carga () {

    return VL_Total_Custo_Carga;
  }

  public String getDM_Tipo_Pagamento () {
    return DM_Tipo_Pagamento;
  }

  public String getNM_Situacao () {
    return NM_Situacao;
  }

  public double getVL_Comissao_Terceiro () {
    return VL_Comissao_Terceiro;
  }

  public double getPE_Comissao_Terceiro () {
    return PE_Comissao_Terceiro;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getOID_Conta_Corrente () {
    return OID_Conta_Corrente;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public double getVL_Retido () {
    return VL_Retido;
  }

  public String getDT_Emissao_Final () {
    return DT_Emissao_Final;
  }

  public String getDT_Emissao_Inicial () {
    return DT_Emissao_Inicial;
  }

  public String getDT_Ordem_Servico () {
    return DT_Ordem_Servico;
  }

  public long getNR_Ordem_Servico () {
    return NR_Ordem_Servico;
  }

  public String getNM_Unidade () {
    return NM_Unidade;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public String getDT_Final () {
    return DT_Final;
  }

  public String getDT_Inicial () {
    return DT_Inicial;
  }

  public double getNR_Media () {
    return NR_Media;
  }

  public long getNR_Kilometragem_Total () {
    return NR_Kilometragem_Total;
  }

  public long getNR_Kilometragem_Saida () {
    return NR_Kilometragem_Saida;
  }

  public double getNR_Litros () {
    return NR_Litros;
  }

  public double getVL_Total_Extras () {
    return VL_Total_Extras;
  }

  public long getOID_Ordem_Servico () {
    return OID_Ordem_Servico;
  }

  public long getOID_Acerto () {
    return OID_Acerto;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public double getVL_Total_Despesas_Pagas () {
    return VL_Total_Despesas_Pagas;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public double getVL_Total_Despesas () {
    return VL_Total_Despesas;
  }

  public double getVL_Total_Despesas_Faturadas () {
    return VL_Total_Despesas_Faturadas;
  }

  public double getVL_Recebido () {
    return VL_Recebido;
  }

  public double getVL_Frete_Terceiro () {
    return VL_Frete_Terceiro;
  }

  public double getVL_Frete_Proprio () {
    return VL_Frete_Proprio;
  }

  public double getVL_Frete_Peso () {
    return VL_Frete_Peso;
  }

  public double getVL_Devolvido () {
    return VL_Devolvido;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public double getVL_Comissao () {
    return VL_Comissao;
  }

  public double getVL_Base_Comissao () {
    return VL_Base_Comissao;
  }

  public double getVL_Adiantamento_Viagem () {
    return VL_Adiantamento_Viagem;
  }

  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public double getPE_Comissao () {
    return PE_Comissao;
  }

  public double getPE_Base_Comissao () {
    return PE_Base_Comissao;
  }

  public void setVL_Total_Despesas_Faturadas (double VL_Total_Despesas_Faturadas) {
    this.VL_Total_Despesas_Faturadas = VL_Total_Despesas_Faturadas;
  }

  public void setVL_Recebido (double VL_Recebido) {
    this.VL_Recebido = VL_Recebido;
  }

  public void setVL_Frete_Terceiro (double VL_Frete_Terceiro) {
    this.VL_Frete_Terceiro = VL_Frete_Terceiro;
  }

  public void setVL_Frete_Proprio (double VL_Frete_Proprio) {
    this.VL_Frete_Proprio = VL_Frete_Proprio;
  }

  public void setVL_Frete_Peso (double VL_Frete_Peso) {
    this.VL_Frete_Peso = VL_Frete_Peso;
  }

  public void setVL_Devolvido (double VL_Devolvido) {
    this.VL_Devolvido = VL_Devolvido;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public void setVL_Comissao (double VL_Comissao) {
    this.VL_Comissao = VL_Comissao;
  }

  public void setVL_Base_Comissao (double VL_Base_Comissao) {
    this.VL_Base_Comissao = VL_Base_Comissao;
  }

  public void setVL_Adiantamento_Viagem (double VL_Adiantamento_Viagem) {
    this.VL_Adiantamento_Viagem = VL_Adiantamento_Viagem;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public void setPE_Comissao (double PE_Comissao) {
    this.PE_Comissao = PE_Comissao;
  }

  public void setPE_Base_Comissao (double PE_Base_Comissao) {
    this.PE_Base_Comissao = PE_Base_Comissao;
  }

  public void setOid_Veiculo (String oid_Veiculo) {
    this.oid_Veiculo = oid_Veiculo;
  }

  public void setOid_Unidade (long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setOid_Movimento_Conta_Corrente (long oid_Movimento_Conta_Corrente) {
    this.oid_Movimento_Conta_Corrente = oid_Movimento_Conta_Corrente;
  }

  public void setOid_Motorista (String oid_Motorista) {
    this.oid_Motorista = oid_Motorista;
  }

  public void setOid (long oid) {
    this.oid = oid;
  }

  public void setAcao (String acao) {
    this.acao = acao;
  }

  public void setOid_Empresa (long oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }

  public void setOid_Carreta (String oid_Carreta) {
    this.oid_Carreta = oid_Carreta;
  }

  public void setOID_Usuario (long OID_Usuario) {
    this.OID_Usuario = OID_Usuario;
  }

  public void setVL_Total_Carga_Descarga (double VL_Total_Carga_Descarga) {
    this.VL_Total_Carga_Descarga = VL_Total_Carga_Descarga;
  }

  public void setVL_Total_Custo_Descarga (double VL_Total_Custo_Descarga) {

    this.VL_Total_Custo_Descarga = VL_Total_Custo_Descarga;
  }

  public void setVL_Total_Custo_Carga (double VL_Total_Custo_Carga) {

    this.VL_Total_Custo_Carga = VL_Total_Custo_Carga;
  }

  public void setDM_Tipo_Pagamento (String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }

  public void setNM_Situacao (String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }

  public void setVL_Comissao_Terceiro (double VL_Comissao_Terceiro) {
    this.VL_Comissao_Terceiro = VL_Comissao_Terceiro;
  }

  public void setPE_Comissao_Terceiro (double PE_Comissao_Terceiro) {
    this.PE_Comissao_Terceiro = PE_Comissao_Terceiro;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setOID_Conta_Corrente (String OID_Conta_Corrente) {
    this.OID_Conta_Corrente = OID_Conta_Corrente;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public void setVL_Retido (double VL_Retido) {
    this.VL_Retido = VL_Retido;
  }

  public void setDT_Emissao_Final (String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }

  public void setDT_Emissao_Inicial (String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }

  public void setDT_Ordem_Servico (String DT_Ordem_Servico) {
    this.DT_Ordem_Servico = DT_Ordem_Servico;
  }

  public void setNR_Ordem_Servico (long NR_Ordem_Servico) {
    this.NR_Ordem_Servico = NR_Ordem_Servico;
  }

  public void setNM_Unidade (String NM_Unidade) {
    this.NM_Unidade = NM_Unidade;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public void setDT_Final (String DT_Final) {
    this.DT_Final = DT_Final;
  }

  public void setDT_Inicial (String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }

  public void setNR_Media (double NR_Media) {
    this.NR_Media = NR_Media;
  }

  public void setNR_Kilometragem_Total (long NR_Kilometragem_Total) {
    this.NR_Kilometragem_Total = NR_Kilometragem_Total;
  }

  public void setNR_Kilometragem_Saida (long NR_Kilometragem_Saida) {
    this.NR_Kilometragem_Saida = NR_Kilometragem_Saida;
  }

  public void setNR_Litros (double NR_Litros) {
    this.NR_Litros = NR_Litros;
  }

  public void setVL_Total_Extras (double VL_Total_Extras) {
    this.VL_Total_Extras = VL_Total_Extras;
  }

  public void setOID_Ordem_Servico (long OID_Ordem_Servico) {
    this.OID_Ordem_Servico = OID_Ordem_Servico;
  }

  public void setOID_Acerto (long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public void setVL_Total_Despesas_Pagas (double VL_Total_Despesas_Pagas) {
    this.VL_Total_Despesas_Pagas = VL_Total_Despesas_Pagas;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public void setVL_Total_Despesas (double VL_Total_Despesas) {
    this.VL_Total_Despesas = VL_Total_Despesas;
  }

  public void setDM_Impresso (String DM_Impresso) {
    this.DM_Impresso = DM_Impresso;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public void setDm_Stamp (String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }

  public void setDT_Chegada (String DT_Chegada) {
    this.DT_Chegada = DT_Chegada;
  }

  public void setDT_Emissao (String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }

  public void setDT_Saida (String DT_Saida) {
    this.DT_Saida = DT_Saida;
  }

  public void setDt_Stamp (String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }

  public void setNR_Acerto (long NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }

  public void setNR_Kilometragem_Chegada (long NR_Kilometragem_Chegada) {
    this.NR_Kilometragem_Chegada = NR_Kilometragem_Chegada;
  }

  private void jbInit () throws Exception {
  }

public int getNR_Kilometragem2() {
	return NR_Kilometragem2;
}

public void setNR_Kilometragem2(int kilometragem2) {
	NR_Kilometragem2 = kilometragem2;
}

public int getNR_Kilometragem3() {
	return NR_Kilometragem3;
}

public void setNR_Kilometragem3(int kilometragem3) {
	NR_Kilometragem3 = kilometragem3;
}

public String getOid_Veiculo2() {
	return oid_Veiculo2;
}

public void setOid_Veiculo2(String oid_Veiculo2) {
	this.oid_Veiculo2 = oid_Veiculo2;
}

public String getOid_Veiculo3() {
	return oid_Veiculo3;
}

public void setOid_Veiculo3(String oid_Veiculo3) {
	this.oid_Veiculo3 = oid_Veiculo3;
}

public double getVL_Servico() {
	return VL_Servico;
}

public void setVL_Servico(double servico) {
	VL_Servico = servico;
}

public String getOID_Conhecimento() {
	return OID_Conhecimento;
}

public void setOID_Conhecimento(String conhecimento) {
	OID_Conhecimento = conhecimento;
}

public double getPE_Comissao_Acerto() {
	return PE_Comissao_Acerto;
}

public void setPE_Comissao_Acerto(double comissao_Acerto) {
	PE_Comissao_Acerto = comissao_Acerto;
}

public double getVL_Base_Comissao_Acerto() {
	return VL_Base_Comissao_Acerto;
}

public void setVL_Base_Comissao_Acerto(double base_Comissao_Acerto) {
	VL_Base_Comissao_Acerto = base_Comissao_Acerto;
}

public String getOID_Nota_Fiscal() {
	return OID_Nota_Fiscal;
}

public void setOID_Nota_Fiscal(String nota_Fiscal) {
	OID_Nota_Fiscal = nota_Fiscal;
}

public String getCD_Unidade() {
	return CD_Unidade;
}

public void setCD_Unidade(String unidade) {
	CD_Unidade = unidade;
}

public String getNM_Pessoa_Destinatario() {
	return NM_Pessoa_Destinatario;
}

public void setNM_Pessoa_Destinatario(String pessoa_Destinatario) {
	NM_Pessoa_Destinatario = pessoa_Destinatario;
}

public String getNM_Pessoa_Remetente() {
	return NM_Pessoa_Remetente;
}

public void setNM_Pessoa_Remetente(String pessoa_Remetent) {
	NM_Pessoa_Remetente = pessoa_Remetent;
}

public long getNR_Conhecimento() {
	return NR_Conhecimento;
}

public void setNR_Conhecimento(long conhecimento) {
	NR_Conhecimento = conhecimento;
}

public double getVL_TOTAL_FRETE() {
	return VL_TOTAL_FRETE;
}

public void setVL_TOTAL_FRETE(double vl_total_frete) {
	VL_TOTAL_FRETE = vl_total_frete;
}

public long getNR_Nota_Fiscal() {
	return NR_Nota_Fiscal;
}

public void setNR_Nota_Fiscal(long nota_Fiscal) {
	NR_Nota_Fiscal = nota_Fiscal;
}

public String getNM_Situacao_Conhecimento() {
	return NM_Situacao_Conhecimento;
}

public void setNM_Situacao_Conhecimento(String situacao_Conhecimento) {
	NM_Situacao_Conhecimento = situacao_Conhecimento;
}


public long getNr_Ordem_Frete() {
	return nr_Ordem_Frete;
}

public void setNr_Ordem_Frete(long nr_Ordem_Frete) {
	this.nr_Ordem_Frete = nr_Ordem_Frete;
}

public String getNm_Tipo_Adiantamento() {
	return nm_Tipo_Adiantamento;
}

public void setNm_Tipo_Adiantamento(String nm_Tipo_Adiantamento) {
	this.nm_Tipo_Adiantamento = nm_Tipo_Adiantamento;
}

public long getNr_Documento() {
	return nr_Documento;
}

public void setNr_Documento(long nr_Documento) {
	this.nr_Documento = nr_Documento;
}

public long getNr_Quantidade() {
	return nr_Quantidade;
}

public void setNr_Quantidade(long nr_Quantidade) {
	this.nr_Quantidade = nr_Quantidade;
}

public String getTX_Observacao_Saldo() {
	return TX_Observacao_Saldo;
}

public void setTX_Observacao_Saldo(String observacao_Saldo) {
	TX_Observacao_Saldo = observacao_Saldo;
}

public double getVL_Abastecimento_Interno() {
	return VL_Abastecimento_Interno;
}

public void setVL_Abastecimento_Interno(double abastecimento_Interno) {
	VL_Abastecimento_Interno = abastecimento_Interno;
}

public double getNR_Litros_Total() {
	return NR_Litros_Total;
}

public void setNR_Litros_Total(double litros_Total) {
	NR_Litros_Total = litros_Total;
}

public double getVL_Despesa() {
	return VL_Despesa;
}

public void setVL_Despesa(double despesa) {
	VL_Despesa = despesa;
}

public double getPE_Conta() {
	return PE_Conta;
}

public void setPE_Conta(double conta) {
	PE_Conta = conta;
}

public double getVL_Dia() {
	return VL_Dia;
}

public void setVL_Dia(double dia) {
	VL_Dia = dia;
}

public double getVL_KM() {
	return VL_KM;
}

public void setVL_KM(double vl_km) {
	VL_KM = vl_km;
}

public String getNM_Dias_Ociosidade() {
	return NM_Dias_Ociosidade;
}

public void setNM_Dias_Ociosidade(String dias_Ociosidade) {
	NM_Dias_Ociosidade = dias_Ociosidade;
}

public long getNR_Dias_Ociosidade() {
	return NR_Dias_Ociosidade;
}

public void setNR_Dias_Ociosidade(long dias_Ociosidade) {
	NR_Dias_Ociosidade = dias_Ociosidade;
}

public String getDt_Chegada_Filtro() {
	return dt_Chegada_Filtro;
}

public void setDt_Chegada_Filtro(String dt_Chegada_Filtro) {
	this.dt_Chegada_Filtro = dt_Chegada_Filtro;
}

public String getDt_Saida_Filtro() {
	return dt_Saida_Filtro;
}

public void setDt_Saida_Filtro(String dt_Saida_Filtro) {
	this.dt_Saida_Filtro = dt_Saida_Filtro;
}

public String getNM_Financiamento() {
	return NM_Financiamento;
}

public void setNM_Financiamento(String financiamento) {
	NM_Financiamento = financiamento;
}


public double getNR_KM_Bruto() {
	return NR_KM_Bruto;
}

public void setNR_KM_Bruto(double bruto) {
	NR_KM_Bruto = bruto;
}

public double getNR_KM_Liquido() {
	return NR_KM_Liquido;
}

public void setNR_KM_Liquido(double liquido) {
	NR_KM_Liquido = liquido;
}

public double getPE_Faturamento_Dia() {
	return PE_Faturamento_Dia;
}

public void setPE_Faturamento_Dia(double faturamento_Dia) {
	PE_Faturamento_Dia = faturamento_Dia;
}

public double getVL_Custo_Consumo() {
	return VL_Custo_Consumo;
}

public void setVL_Custo_Consumo(double custo_Consumo) {
	VL_Custo_Consumo = custo_Consumo;
}

public double getVL_Receita_KM() {
	return VL_Receita_KM;
}

public void setVL_Receita_KM(double receita_KM) {
	VL_Receita_KM = receita_KM;
}

public double getVL_Resultado_KM() {
	return VL_Resultado_KM;
}

public void setVL_Resultado_KM(double resultado_KM) {
	VL_Resultado_KM = resultado_KM;
}

public double getPE_Resultado() {
	return PE_Resultado;
}

public void setPE_Resultado(double resultado) {
	PE_Resultado = resultado;
}

public double getVL_Custo_Litro() {
	return VL_Custo_Litro;
}

public void setVL_Custo_Litro(double custo_Litro) {
	VL_Custo_Litro = custo_Litro;
}

public double getVL_Faturamento_Dia() {
	return VL_Faturamento_Dia;
}

public void setVL_Faturamento_Dia(double faturamento_Dia) {
	VL_Faturamento_Dia = faturamento_Dia;
}

public double getVL_Financiamento() {
	return VL_Financiamento;
}

public void setVL_Financiamento(double financiamento) {
	VL_Financiamento = financiamento;
}

public double getVL_Ociosidade() {
	return VL_Ociosidade;
}

public void setVL_Ociosidade(double ociosidade) {
	VL_Ociosidade = ociosidade;
}

public String getDM_Tipo_Veiculo() {
	return DM_Tipo_Veiculo;
}

public void setDM_Tipo_Veiculo(String tipo_Veiculo) {
	DM_Tipo_Veiculo = tipo_Veiculo;
}

public double getVL_Financiamento_Carreta() {
	return VL_Financiamento_Carreta;
}

public void setVL_Financiamento_Carreta(double financiamento_Carreta) {
	VL_Financiamento_Carreta = financiamento_Carreta;
}

public double getVL_Custo_Financiamentos() {
	return VL_Custo_Financiamentos;
}

public void setVL_Custo_Financiamentos(double custo_Financiamentos) {
	VL_Custo_Financiamentos = custo_Financiamentos;
}

public double getVL_Financiamento_Cavalo() {
	return VL_Financiamento_Cavalo;
}

public void setVL_Financiamento_Cavalo(double financiamento_Cavalo) {
	VL_Financiamento_Cavalo = financiamento_Cavalo;
}

public double getVL_Oficina_Carreta() {
	return VL_Oficina_Carreta;
}

public void setVL_Oficina_Carreta(double oficina_Carreta) {
	VL_Oficina_Carreta = oficina_Carreta;
}

public double getVL_Oficina_Cavalo() {
	return VL_Oficina_Cavalo;
}

public void setVL_Oficina_Cavalo(double oficina_Cavalo) {
	VL_Oficina_Cavalo = oficina_Cavalo;
}

public double getVL_Custo_Oficina() {
	return VL_Custo_Oficina;
}

public void setVL_Custo_Oficina(double custo_Oficina) {
	VL_Custo_Oficina = custo_Oficina;
}

public double getVL_Financiamento_Carreta_Acerto() {
	return VL_Financiamento_Carreta_Acerto;
}

public void setVL_Financiamento_Carreta_Acerto(
		double financiamento_Carreta_Acerto) {
	VL_Financiamento_Carreta_Acerto = financiamento_Carreta_Acerto;
}

public String getDT_Proximo_Embarque() {
	return DT_Proximo_Embarque;
}

public void setDT_Proximo_Embarque(String proximo_Embarque) {
	DT_Proximo_Embarque = proximo_Embarque;
}

public String getNM_Carretas_Cavalo() {
	return NM_Carretas_Cavalo;
}

public void setNM_Carretas_Cavalo(String carretas_Cavalo) {
	NM_Carretas_Cavalo = carretas_Cavalo;
}

public String getDT_Saida_Final() {
	return DT_Saida_Final;
}

public void setDT_Saida_Final(String saida_Final) {
	DT_Saida_Final = saida_Final;
}

public String getDT_Chegada_Final() {
	return DT_Chegada_Final;
}

public void setDT_Chegada_Final(String chegada_Final) {
	DT_Chegada_Final = chegada_Final;
}

}
