package com.master.ed;

import javax.servlet.http.*;

public class ManifestoED extends RelatorioBaseED {
  public ManifestoED () {
  }

  public ManifestoED (HttpServletResponse response , String nomeRelatorio) {
    super (response , nomeRelatorio);
  }

  private String OID_Conhecimento;
  private String OID_Pessoa;
  private String NR_CNPJ_CPF_Pessoa;
  private String NM_Razao_Social_Pessoa;
  private String NM_Fantasia_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long oid_Empresa;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private String DM_Expresso;
  private long NR_Manifesto;
  private String DT_Previsao_Chegada;
  private String HR_Previsao_Chegada;
  private long NR_Odometro_Inicial;
  private long NR_Odometro_Final;
  private String DT_Troca_Motorista;
  private long NR_Odometro_Troca_Motorista;
  private long OID_Cidade;
  private long OID_Seguradora;
  private long OID_Gaiola;
  private String NM_Liberacao_Seguradora;
  private String NR_Placa;
  private String OID_Veiculo;
  private String NM_Serie;
  private String NM_Cidade_Destino;
  private String NM_Cidade_Unidade;
  private String OID_Pessoa_Reveza;
  private String OID_Manifesto;
  private long NR_Acerto;
  private long OID_Acerto;
  private String DM_Lancado_Ordem_Frete;
  private String OID_Veiculo_Carreta;
  private String CD_Roteiro;
  private String NM_Roteiro;
  private long OID_Embarque;
  private long NR_Embarque;
  private long OID_Cidade_Origem;
  private double VL_Total_Frete_CTRC;
  private String NR_Master;
  private double NR_Total_Peso_CTRC;
  private String DM_Situacao;
  private String DT_Chegada;
  private String HR_Chegada;
  private String TX_Observacao;
  private long OID_Tipo_Ocorrencia;
  private String DM_Tipo;
  private String NM_Pessoa_Entrega;
  private String OID_Ordem_Frete;
  private long OID_Subregiao;
  private long OID_Unidade_Destino;
  private String OID_Pessoa_Unidade_Destino;
  private String DT_Ordem_Manifesto;
  private String HR_Ordem_Manifesto;
  private String HR_Saida;
  private String NM_Ajudante1;
  private String NM_Ajudante2;
  private String NM_Ajudante3;
  private double VL_Frete_Carreteiro;
  private String OID_Pessoa_Entregadora;
  private String NM_Pessoa_Entregadora;
  private double PE_Custo_Entrega;
  private String DM_Manifesto_Romaneio;
  private double PE_Comissao_Motorista;
  private double VL_Comissao_Motorista;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DM_Relatorio;
  private long OID_Empresa;
  private String DM_Tipo_Veiculo;
  private String DM_Tipo_ICMS;
  private long oid_Unidade_Entregadora;
  private String DM_Tipo_Manifesto;
  private String DM_Viagem;
  private double VL_Total_Frete_Peso_CTRC;
  private int NR_Ajudante;
  private int NR_KIT;
  private String DM_Nota_Fiscal;
  private String CD_Rota_Entrega;
  private long NR_KM_Viagem;

  private double nr_peso;
  private double nr_volumes;
  private double vl_nota_fiscal;
  private double nr_quantidade_nota_fiscal;
  private int nr_quantidade_visitas;
  private String OID_Rota_Entrega;

  private String DM_Inicial;
  private String DM_Ano;

  private double nr_peso2;
  private double nr_volumes2;
  private double vl_nota_fiscal2;
  private double nr_quantidade_nota_fiscal2;
  private int nr_quantidade_visitas2;
  private double nr_peso3;
  private double nr_volumes3;
  private double vl_nota_fiscal3;
  private double nr_quantidade_nota_fiscal3;
  private int nr_quantidade_visitas3;
  private double nr_peso_total;
  private double nr_volumes_total;
  private int nr_visitas_total;
  private double nr_peso_rota;
  private double nr_volumes_rota;
  private int nr_visitas_rota;
  //Cia Aerea
  private String CD_Cia_Aerea;
  private long OID_Cia_Aerea;
  //Tipo Servico Aereo
  private long OID_Tipo_Servico_Aereo;
  private String CD_Tipo_Servico_Aereo;
  private String NM_Tipo_Servico_Aereo;

  private String oid_Programacao_Carga;


  public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public void setOID_Manifesto (String OID_Manifesto) {
    this.OID_Manifesto = OID_Manifesto;
  }

  public String getOID_Manifesto () {
    return OID_Manifesto;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa_Destinatario (String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }

  public String getOID_Pessoa_Destinatario () {
    return OID_Pessoa_Destinatario;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setDT_Emissao (String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public void setNR_Conhecimento (long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public long getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setNM_Pessoa_Remetente (String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }

  public String getNM_Pessoa_Remetente () {
    return NM_Pessoa_Remetente;
  }

  public void setNM_Pessoa_Destinatario (String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }

  public String getNM_Pessoa_Destinatario () {
    return NM_Pessoa_Destinatario;
  }

  public void setDM_Expresso (String DM_Expresso) {
    this.DM_Expresso = DM_Expresso;
  }

  public String getDM_Expresso () {
    return DM_Expresso;
  }

  public void setNR_Manifesto (long NR_Manifesto) {
    this.NR_Manifesto = NR_Manifesto;
  }

  public long getNR_Manifesto () {
    return NR_Manifesto;
  }

  public void setDT_Previsao_Chegada (String DT_Previsao_Chegada) {
    this.DT_Previsao_Chegada = DT_Previsao_Chegada;
  }

  public String getDT_Previsao_Chegada () {
    return DT_Previsao_Chegada;
  }

  public void setHR_Previsao_Chegada (String HR_Previsao_Chegada) {
    this.HR_Previsao_Chegada = HR_Previsao_Chegada;
  }

  public String getHR_Previsao_Chegada () {
    return HR_Previsao_Chegada;
  }

  public void setNR_Odometro_Inicial (long NR_Odometro_Inicial) {
    this.NR_Odometro_Inicial = NR_Odometro_Inicial;
  }

  public long getNR_Odometro_Inicial () {
    return NR_Odometro_Inicial;
  }

  public void setNM_Liberacao_Seguradora (String NM_Liberacao_Seguradora) {
    this.NM_Liberacao_Seguradora = NM_Liberacao_Seguradora;
  }

  public String getNM_Liberacao_Seguradora () {
    return NM_Liberacao_Seguradora;
  }

  public void setDT_Troca_Motorista (String DT_Troca_Motorista) {
    this.DT_Troca_Motorista = DT_Troca_Motorista;
  }

  public String getDT_Troca_Motorista () {
    return DT_Troca_Motorista;
  }

  public void setNR_Odometro_Troca_Motorista (long NR_Odometro_Troca_Motorista) {
    this.NR_Odometro_Troca_Motorista = NR_Odometro_Troca_Motorista;
  }

  public long getNR_Odometro_Troca_Motorista () {
    return NR_Odometro_Troca_Motorista;
  }

  public void setOID_Cidade (long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }

  public long getOID_Cidade () {
    return OID_Cidade;
  }

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public void setOID_Seguradora (long OID_Seguradora) {
    this.OID_Seguradora = OID_Seguradora;
  }

  public long getOID_Seguradora () {
    return OID_Seguradora;
  }

  public void setOID_Pessoa_Reveza (String OID_Pessoa_Reveza) {
    this.OID_Pessoa_Reveza = OID_Pessoa_Reveza;
  }

  public String getOID_Pessoa_Reveza () {
    return OID_Pessoa_Reveza;
  }

  public void setNR_Placa (String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public void setNM_Serie (String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }

  public String getNM_Serie () {
    return NM_Serie;
  }

  public void setNM_Cidade_Destino (String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }

  public String getNM_Cidade_Destino () {
    return NM_Cidade_Destino;
  }

  public void setNM_Cidade_Unidade (String NM_Cidade_Unidade) {
    this.NM_Cidade_Unidade = NM_Cidade_Unidade;
  }

  public String getNM_Cidade_Unidade () {
    return NM_Cidade_Unidade;
  }

  public void setNR_Acerto (long NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }

  public long getNR_Acerto () {
    return NR_Acerto;
  }

  public void setOID_Acerto (long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }

  public long getOID_Acerto () {
    return OID_Acerto;
  }

  public void setDM_Lancado_Ordem_Frete (String DM_Lancado_Ordem_Frete) {
    this.DM_Lancado_Ordem_Frete = DM_Lancado_Ordem_Frete;
  }

  public String getDM_Lancado_Ordem_Frete () {
    return DM_Lancado_Ordem_Frete;
  }

  public void setOID_Veiculo_Carreta (String OID_Veiculo_Carreta) {
    this.OID_Veiculo_Carreta = OID_Veiculo_Carreta;
  }

  public String getOID_Veiculo_Carreta () {
    return OID_Veiculo_Carreta;
  }

  public void setCD_Roteiro (String CD_Roteiro) {
    this.CD_Roteiro = CD_Roteiro;
  }

  public String getCD_Roteiro () {
    return CD_Roteiro;
  }

  public void setNM_Roteiro (String NM_Roteiro) {
    this.NM_Roteiro = NM_Roteiro;
  }

  public String getNM_Roteiro () {
    return NM_Roteiro;
  }

  public void setOID_Embarque (long OID_Embarque) {
    this.OID_Embarque = OID_Embarque;
  }

  public long getOID_Embarque () {
    return OID_Embarque;
  }

  public void setNR_Embarque (long NR_Embarque) {
    this.NR_Embarque = NR_Embarque;
  }

  public long getNR_Embarque () {
    return NR_Embarque;
  }

  public void setOID_Cidade_Origem (long OID_Cidade_Origem) {
    this.OID_Cidade_Origem = OID_Cidade_Origem;
  }

  public long getOID_Cidade_Origem () {
    return OID_Cidade_Origem;
  }

  public void setVL_Total_Frete_CTRC (double VL_Total_Frete_CTRC) {
    this.VL_Total_Frete_CTRC = VL_Total_Frete_CTRC;
  }

  public double getVL_Total_Frete_CTRC () {
    return VL_Total_Frete_CTRC;
  }

  public void setNR_Master (String NR_Master) {
    this.NR_Master = NR_Master;
  }

  public String getNR_Master () {
    return NR_Master;
  }

  public void setNR_Total_Peso_CTRC (double NR_Total_Peso_CTRC) {
    this.NR_Total_Peso_CTRC = NR_Total_Peso_CTRC;
  }

  public double getNR_Total_Peso_CTRC () {
    return NR_Total_Peso_CTRC;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDT_Chegada (String DT_Chegada) {
    this.DT_Chegada = DT_Chegada;
  }

  public String getDT_Chegada () {
    return DT_Chegada;
  }

  public void setHR_Chegada (String HR_Chegada) {
    this.HR_Chegada = HR_Chegada;
  }

  public String getHR_Chegada () {
    return HR_Chegada;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setOID_Tipo_Ocorrencia (long OID_Tipo_Ocorrencia) {
    this.OID_Tipo_Ocorrencia = OID_Tipo_Ocorrencia;
  }

  public long getOID_Tipo_Ocorrencia () {
    return OID_Tipo_Ocorrencia;
  }

  public void setDM_Tipo (String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }

  public String getDM_Tipo () {
    return DM_Tipo;
  }

  public void setNM_Pessoa_Entrega (String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }

  public String getNM_Pessoa_Entrega () {
    return NM_Pessoa_Entrega;
  }

  public void setOID_Ordem_Frete (String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }

  public String getOID_Ordem_Frete () {
    return OID_Ordem_Frete;
  }

  public void setOID_Subregiao (long OID_Subregiao) {
    this.OID_Subregiao = OID_Subregiao;
  }

  public long getOID_Subregiao () {
    return OID_Subregiao;
  }

  public void setOID_Unidade_Destino (long OID_Unidade_Destino) {
    this.OID_Unidade_Destino = OID_Unidade_Destino;
  }

  public long getOID_Unidade_Destino () {
    return OID_Unidade_Destino;
  }

  public void setOID_Pessoa_Unidade_Destino (String OID_Pessoa_Unidade_Destino) {
    this.OID_Pessoa_Unidade_Destino = OID_Pessoa_Unidade_Destino;
  }

  public String getOID_Pessoa_Unidade_Destino () {
    return OID_Pessoa_Unidade_Destino;
  }

  public void setDT_Ordem_Manifesto (String DT_Ordem_Manifesto) {
    this.DT_Ordem_Manifesto = DT_Ordem_Manifesto;
  }

  public String getDT_Ordem_Manifesto () {
    return DT_Ordem_Manifesto;
  }

  public void setHR_Ordem_Manifesto (String HR_Ordem_Manifesto) {
    this.HR_Ordem_Manifesto = HR_Ordem_Manifesto;
  }

  public String getHR_Ordem_Manifesto () {
    return HR_Ordem_Manifesto;
  }

  public void setHR_Saida (String HR_Saida) {
    this.HR_Saida = HR_Saida;
  }

  public String getHR_Saida () {
    return HR_Saida;
  }

  public void setNM_Ajudante1 (String NM_Ajudante1) {
    this.NM_Ajudante1 = NM_Ajudante1;
  }

  public String getNM_Ajudante1 () {
    return NM_Ajudante1;
  }

  public void setNM_Ajudante2 (String NM_Ajudante2) {
    this.NM_Ajudante2 = NM_Ajudante2;
  }

  public String getNM_Ajudante2 () {
    return NM_Ajudante2;
  }

  public void setNM_Ajudante3 (String NM_Ajudante3) {
    this.NM_Ajudante3 = NM_Ajudante3;
  }

  public String getNM_Ajudante3 () {
    return NM_Ajudante3;
  }

  public double getVL_Frete_Carreteiro () {
    return VL_Frete_Carreteiro;
  }

  public void setVL_Frete_Carreteiro (double frete_Carreteiro) {
    VL_Frete_Carreteiro = frete_Carreteiro;
  }

  public void setOID_Pessoa_Entregadora (String OID_Pessoa_Entregadora) {
    this.OID_Pessoa_Entregadora = OID_Pessoa_Entregadora;
  }

  public String getOID_Pessoa_Entregadora () {
    return OID_Pessoa_Entregadora;
  }

  public void setNM_Pessoa_Entregadora (String NM_Pessoa_Entregadora) {
    this.NM_Pessoa_Entregadora = NM_Pessoa_Entregadora;
  }

  public String getNM_Pessoa_Entregadora () {
    return NM_Pessoa_Entregadora;
  }

  public void setPE_Custo_Entrega (double PE_Custo_Entrega) {
    this.PE_Custo_Entrega = PE_Custo_Entrega;
  }

  public double getPE_Custo_Entrega () {
    return PE_Custo_Entrega;
  }

  public void setDM_Manifesto_Romaneio (String DM_Manifesto_Romaneio) {
    this.DM_Manifesto_Romaneio = DM_Manifesto_Romaneio;
  }

  public String getDM_Manifesto_Romaneio () {
    return DM_Manifesto_Romaneio;
  }

  public double getPE_Comissao_Motorista () {
    return this.PE_Comissao_Motorista;
  }

  public void setPE_Comissao_Motorista (double comissao_Motorista) {
    this.PE_Comissao_Motorista = comissao_Motorista;
  }

  public double getVL_Comissao_Motorista () {
    return this.VL_Comissao_Motorista;
  }

  public void setVL_Comissao_Motorista (double comissao_Motorista) {
    this.VL_Comissao_Motorista = comissao_Motorista;
  }

  public long getOid_Empresa () {
    return this.oid_Empresa;
  }

  public long getOid_Unidade_Entregadora () {
    return oid_Unidade_Entregadora;
  }

  public long getNR_Odometro_Final () {
    return NR_Odometro_Final;
  }

  public long getNR_KM_Viagem () {
    return NR_KM_Viagem;
  }

  public String getDM_Nota_Fiscal () {
    return DM_Nota_Fiscal;
  }

  public int getNR_KIT () {
    return NR_KIT;
  }

  public int getNR_Ajudante () {
    return NR_Ajudante;
  }

  public double getVL_Total_Frete_Peso_CTRC () {
    return VL_Total_Frete_Peso_CTRC;
  }

  public String getDM_Tipo_Manifesto () {
    return DM_Tipo_Manifesto;
  }

  public String getDM_Tipo_ICMS () {
    return DM_Tipo_ICMS;
  }

  public String getDM_Tipo_Veiculo () {
    return DM_Tipo_Veiculo;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setOid_Empresa (long oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }

  public void setOid_Unidade_Entregadora (long oid_Unidade_Entregadora) {
    this.oid_Unidade_Entregadora = oid_Unidade_Entregadora;
  }

  public void setNR_Odometro_Final (long NR_Odometro_Final) {
    this.NR_Odometro_Final = NR_Odometro_Final;
  }

  public void setNR_KM_Viagem (long NR_KM_Viagem) {
    this.NR_KM_Viagem = NR_KM_Viagem;
  }

  public void setDM_Nota_Fiscal (String DM_Nota_Fiscal) {
    this.DM_Nota_Fiscal = DM_Nota_Fiscal;
  }

  public void setNR_KIT (int NR_KIT) {
    this.NR_KIT = NR_KIT;
  }

  public void setNR_Ajudante (int NR_Ajudante) {
    this.NR_Ajudante = NR_Ajudante;
  }

  public void setVL_Total_Frete_Peso_CTRC (double VL_Total_Frete_Peso_CTRC) {
    this.VL_Total_Frete_Peso_CTRC = VL_Total_Frete_Peso_CTRC;
  }

  public void setDM_Tipo_Manifesto (String DM_Tipo_Manifesto) {
    this.DM_Tipo_Manifesto = DM_Tipo_Manifesto;
  }

  public void setDM_Tipo_ICMS (String DM_Tipo_ICMS) {
    this.DM_Tipo_ICMS = DM_Tipo_ICMS;
  }

  public void setDM_Tipo_Veiculo (String DM_Tipo_Veiculo) {
    this.DM_Tipo_Veiculo = DM_Tipo_Veiculo;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDT_Emissao_Final () {
    return this.DT_Emissao_Final;
  }

  public void setDT_Emissao_Final (String emissao_Final) {
    this.DT_Emissao_Final = emissao_Final;
  }

  public String getDT_Emissao_Inicial () {
    return this.DT_Emissao_Inicial;
  }

  public void setDT_Emissao_Inicial (String emissao_Inicial) {
    this.DT_Emissao_Inicial = emissao_Inicial;
  }

  public String getNM_Fantasia_Pessoa () {
    return this.NM_Fantasia_Pessoa;
  }

  public void setNM_Fantasia_Pessoa (String fantasia_Pessoa) {
    this.NM_Fantasia_Pessoa = fantasia_Pessoa;
  }

  public String getNM_Razao_Social_Pessoa () {
    return this.NM_Razao_Social_Pessoa;
  }

  public void setNM_Razao_Social_Pessoa (String razao_Social_Pessoa) {
    this.NM_Razao_Social_Pessoa = razao_Social_Pessoa;
  }

  public String getNR_CNPJ_CPF_Pessoa () {
    return this.NR_CNPJ_CPF_Pessoa;
  }

  public void setNR_CNPJ_CPF_Pessoa (String pessoa) {
    this.NR_CNPJ_CPF_Pessoa = pessoa;
  }

  public String getCD_Rota_Entrega () {
    return CD_Rota_Entrega;
  }

  public void setCD_Rota_Entrega (String rota_Entrega) {
    CD_Rota_Entrega = rota_Entrega;
  }

  public double getNr_peso () {
    return nr_peso;
  }

  public void setNr_peso (double nr_peso) {
    this.nr_peso = nr_peso;
  }

  public double getNr_quantidade_nota_fiscal () {
    return nr_quantidade_nota_fiscal;
  }

  public void setNr_quantidade_nota_fiscal (double nr_quantidade_nota_fiscal) {
    this.nr_quantidade_nota_fiscal = nr_quantidade_nota_fiscal;
  }

  public int getNr_quantidade_visitas () {
    return nr_quantidade_visitas;
  }

  public void setNr_quantidade_visitas (int nr_quantidade_visitas) {
    this.nr_quantidade_visitas = nr_quantidade_visitas;
  }

  public double getNr_volumes () {
    return nr_volumes;
  }

  public void setNr_volumes (double nr_volumes) {
    this.nr_volumes = nr_volumes;
  }

  public double getVl_nota_fiscal () {
    return vl_nota_fiscal;
  }

  public String getOID_Rota_Entrega () {
    return OID_Rota_Entrega;
  }

  public void setVl_nota_fiscal (double vl_nota_fiscal) {
    this.vl_nota_fiscal = vl_nota_fiscal;
  }

  public void setOID_Rota_Entrega (String OID_Rota_Entrega) {
    this.OID_Rota_Entrega = OID_Rota_Entrega;
  }

public long getOID_Gaiola() {
	return OID_Gaiola;
}

public void setOID_Gaiola(long gaiola) {
	OID_Gaiola = gaiola;
}

public String getDM_Viagem() {
	return DM_Viagem;
}

public void setDM_Viagem(String viagem) {
	DM_Viagem = viagem;
}

public String getDM_Inicial() {
	return DM_Inicial;
}

public void setDM_Inicial(String inicial) {
	DM_Inicial = inicial;
}

public String getDM_Ano() {
	return DM_Ano;
}

public void setDM_Ano(String ano) {
	DM_Ano = ano;
}

public double getNr_peso2() {
	return nr_peso2;
}

public void setNr_peso2(double nr_peso2) {
	this.nr_peso2 = nr_peso2;
}

public double getNr_peso3() {
	return nr_peso3;
}

public void setNr_peso3(double nr_peso3) {
	this.nr_peso3 = nr_peso3;
}

public double getNr_quantidade_nota_fiscal2() {
	return nr_quantidade_nota_fiscal2;
}

public void setNr_quantidade_nota_fiscal2(double nr_quantidade_nota_fiscal2) {
	this.nr_quantidade_nota_fiscal2 = nr_quantidade_nota_fiscal2;
}

public double getNr_quantidade_nota_fiscal3() {
	return nr_quantidade_nota_fiscal3;
}

public void setNr_quantidade_nota_fiscal3(double nr_quantidade_nota_fiscal3) {
	this.nr_quantidade_nota_fiscal3 = nr_quantidade_nota_fiscal3;
}

public int getNr_quantidade_visitas2() {
	return nr_quantidade_visitas2;
}

public void setNr_quantidade_visitas2(int nr_quantidade_visitas2) {
	this.nr_quantidade_visitas2 = nr_quantidade_visitas2;
}

public int getNr_quantidade_visitas3() {
	return nr_quantidade_visitas3;
}

public void setNr_quantidade_visitas3(int nr_quantidade_visitas3) {
	this.nr_quantidade_visitas3 = nr_quantidade_visitas3;
}

public double getNr_volumes2() {
	return nr_volumes2;
}

public void setNr_volumes2(double nr_volumes2) {
	this.nr_volumes2 = nr_volumes2;
}

public double getNr_volumes3() {
	return nr_volumes3;
}

public void setNr_volumes3(double nr_volumes3) {
	this.nr_volumes3 = nr_volumes3;
}

public double getVl_nota_fiscal2() {
	return vl_nota_fiscal2;
}

public void setVl_nota_fiscal2(double vl_nota_fiscal2) {
	this.vl_nota_fiscal2 = vl_nota_fiscal2;
}

public double getVl_nota_fiscal3() {
	return vl_nota_fiscal3;
}

public void setVl_nota_fiscal3(double vl_nota_fiscal3) {
	this.vl_nota_fiscal3 = vl_nota_fiscal3;
}

public double getNr_peso_rota() {
	return nr_peso_rota;
}

public void setNr_peso_rota(double nr_peso_rota) {
	this.nr_peso_rota = nr_peso_rota;
}

public double getNr_peso_total() {
	return nr_peso_total;
}

public void setNr_peso_total(double nr_peso_total) {
	this.nr_peso_total = nr_peso_total;
}

public int getNr_visitas_rota() {
	return nr_visitas_rota;
}

public void setNr_visitas_rota(int nr_visitas_rota) {
	this.nr_visitas_rota = nr_visitas_rota;
}

public int getNr_visitas_total() {
	return nr_visitas_total;
}

public void setNr_visitas_total(int nr_visitas_total) {
	this.nr_visitas_total = nr_visitas_total;
}

public double getNr_volumes_rota() {
	return nr_volumes_rota;
}

public void setNr_volumes_rota(double nr_volumes_rota) {
	this.nr_volumes_rota = nr_volumes_rota;
}

public double getNr_volumes_total() {
	return nr_volumes_total;
}

public void setNr_volumes_total(double nr_volumes_total) {
	this.nr_volumes_total = nr_volumes_total;
}

public String getCD_Cia_Aerea() {
	return CD_Cia_Aerea;
}

public void setCD_Cia_Aerea(String cia_Aerea) {
	CD_Cia_Aerea = cia_Aerea;
}

public String getCD_Tipo_Servico_Aereo() {
	return CD_Tipo_Servico_Aereo;
}

public void setCD_Tipo_Servico_Aereo(String tipo_Servico_Aereo) {
	CD_Tipo_Servico_Aereo = tipo_Servico_Aereo;
}

public String getNM_Tipo_Servico_Aereo() {
	return NM_Tipo_Servico_Aereo;
}

public void setNM_Tipo_Servico_Aereo(String tipo_Servico_Aereo) {
	NM_Tipo_Servico_Aereo = tipo_Servico_Aereo;
}

public long getOID_Cia_Aerea() {
	return OID_Cia_Aerea;
}

public void setOID_Cia_Aerea(long cia_Aerea) {
	OID_Cia_Aerea = cia_Aerea;
}

public long getOID_Tipo_Servico_Aereo() {
	return OID_Tipo_Servico_Aereo;
}

public void setOID_Tipo_Servico_Aereo(long tipo_Servico_Aereo) {
	OID_Tipo_Servico_Aereo = tipo_Servico_Aereo;
}

public String getOid_Programacao_Carga() {
	return oid_Programacao_Carga;
}

public void setOid_Programacao_Carga(String oid_Programacao_Carga) {
	this.oid_Programacao_Carga = oid_Programacao_Carga;
}
}
