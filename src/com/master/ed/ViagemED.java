package com.master.ed;

public class ViagemED extends MasterED{
  public ViagemED () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  private String OID_Processo;
  private String OID_Conhecimento;
  private String DT_Viagem;
  private String HR_Viagem;
  private String NM_Senha;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private long NR_Manifesto;
  private long NR_Romaneio;
  private String OID_Manifesto;
  private String OID_Viagem;
  private String OID_Nota_Fiscal;
  private double VL_Total_Frete;
  private String NM_Cidade_Destino;
  private String DT_Previsao_Chegada;
  private String HR_Previsao_Chegada;
  private long NR_Conhecimento_Inicial;
  private long NR_Conhecimento_Final;
  private int OID_Subregiao;
  private long OID_Unidade_Entregadora;
  private String OID_Pessoa_Entregadora;
  private double VL_Entrega;
  private double VL_Compromisso;
  private String DT_Vencimento;
  private Integer NR_Parcela;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private long OID_Cidade;
  private String DT_Previsao_Entrega;
  private String HR_Previsao_Entrega;
  private String DT_Entrega;
  private String HR_Entrega;
  private long OID_Compromisso;
  private long OID_Usuario;
  private String DM_Lancado_Ordem_Frete;
  private String DM_Tipo_Veiculo;
  private long OID_Empresa;
  private String DM_Tipo_Seguro;
  private int DM_Layout;
  private String oid_Veiculo;
  private String DM_Tipo_ICMS;
  private String DM_Tipo_Manifesto;
  private String DM_Tipo_Viagem;
  private String NR_Processo;
  private double NR_Peso;
  private double NR_Volumes;
  private double VL_Nota_Fiscal;
  private String CD_Rota_Entrega;
  private double nr_peso;
  private double nr_volumes;
  private double vl_nota_fiscal;
  private double nr_quantidade_nota_fiscal;
  private int nr_quantidade_visitas;

  private String NR_Master;
  private String NR_Voo;
  private String DT_Voo;
  private String HR_Voo;
  private String DM_Cia_Aerea;
  private double VL_Ordem_Frete;
  private long NR_Nota_Fiscal;

  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setOID_Viagem(String OID_Viagem) {
    this.OID_Viagem = OID_Viagem;
  }
  public String getOID_Viagem() {
    return OID_Viagem;
  }
  public void setDT_Viagem(String DT_Viagem) {
    this.DT_Viagem = DT_Viagem;
  }
  public String getDT_Viagem() {
    return DT_Viagem;
  }
  public void setHR_Viagem(String HR_Viagem) {
    this.HR_Viagem = HR_Viagem;
  }
  public String getHR_Viagem() {
    return HR_Viagem;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }
  public String getOID_Pessoa_Destinatario() {
    return OID_Pessoa_Destinatario;
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
  public void setNR_Conhecimento(long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public long getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setNM_Pessoa_Remetente(String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }
  public String getNM_Pessoa_Remetente() {
    return NM_Pessoa_Remetente;
  }
  public void setNM_Pessoa_Destinatario(String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }
  public String getNM_Pessoa_Destinatario() {
    return NM_Pessoa_Destinatario;
  }
  public void setOID_Manifesto(String OID_Manifesto) {
    this.OID_Manifesto = OID_Manifesto;
  }
  public String getOID_Manifesto() {
    return OID_Manifesto;
  }
  public void setNR_Manifesto(long NR_Manifesto) {
    this.NR_Manifesto = NR_Manifesto;
  }
  public long getNR_Manifesto() {
    return NR_Manifesto;
  }
  public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }
  public String getOID_Nota_Fiscal() {
    return OID_Nota_Fiscal;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setNM_Cidade_Destino(String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }
  public String getNM_Cidade_Destino() {
    return NM_Cidade_Destino;
  }
  public void setDT_Previsao_Chegada(String DT_Previsao_Chegada) {
    this.DT_Previsao_Chegada = DT_Previsao_Chegada;
  }
  public String getDT_Previsao_Chegada() {
    return DT_Previsao_Chegada;
  }
  public void setHR_Previsao_Chegada(String HR_Previsao_Chegada) {
    this.HR_Previsao_Chegada = HR_Previsao_Chegada;
  }
  public String getHR_Previsao_Chegada() {
    return HR_Previsao_Chegada;
  }
  public void setNR_Conhecimento_Inicial(long NR_Conhecimento_Inicial) {
    this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
  }
  public long getNR_Conhecimento_Inicial() {
    return NR_Conhecimento_Inicial;
  }
  public void setNR_Conhecimento_Final(long NR_Conhecimento_Final) {
    this.NR_Conhecimento_Final = NR_Conhecimento_Final;
  }
  public long getNR_Conhecimento_Final() {
    return NR_Conhecimento_Final;
  }
  public void setOID_Subregiao(int OID_Subregiao) {
    this.OID_Subregiao = OID_Subregiao;
  }
  public int getOID_Subregiao() {
    return OID_Subregiao;
  }
  public void setOID_Unidade_Entregadora(long OID_Unidade_Entregadora) {
    this.OID_Unidade_Entregadora = OID_Unidade_Entregadora;
  }
  public long getOID_Unidade_Entregadora() {
    return OID_Unidade_Entregadora;
  }
  public void setOID_Pessoa_Entregadora(String OID_Pessoa_Entregadora) {
    this.OID_Pessoa_Entregadora = OID_Pessoa_Entregadora;
  }
  public String getOID_Pessoa_Entregadora() {
    return OID_Pessoa_Entregadora;
  }
  public void setVL_Entrega(double VL_Entrega) {
    this.VL_Entrega = VL_Entrega;
  }
  public double getVL_Entrega() {
    return VL_Entrega;
  }
  public void setVL_Compromisso(double VL_Compromisso) {
    this.VL_Compromisso = VL_Compromisso;
  }
  public double getVL_Compromisso() {
    return VL_Compromisso;
  }
  public void setDT_Vencimento(String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }
  public String getDT_Vencimento() {
    return DT_Vencimento;
  }
  public void setNR_Parcela(Integer NR_Parcela) {
    this.NR_Parcela = NR_Parcela;
  }
  public Integer getNR_Parcela() {
    return NR_Parcela;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }
  public void setDT_Previsao_Entrega(String DT_Previsao_Entrega) {
    this.DT_Previsao_Entrega = DT_Previsao_Entrega;
  }
  public String getDT_Previsao_Entrega() {
    return DT_Previsao_Entrega;
  }
  public void setHR_Previsao_Entrega(String HR_Previsao_Entrega) {
    this.HR_Previsao_Entrega = HR_Previsao_Entrega;
  }
  public String getHR_Previsao_Entrega() {
    return HR_Previsao_Entrega;
  }
  public void setDT_Entrega(String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }
  public String getDT_Entrega() {
    return DT_Entrega;
  }
  public void setHR_Entrega(String HR_Entrega) {
    this.HR_Entrega = HR_Entrega;
  }
  public String getHR_Entrega() {
    return HR_Entrega;
  }
  public void setOID_Compromisso(long OID_Compromisso) {
    this.OID_Compromisso = OID_Compromisso;
  }
  public long getOID_Compromisso() {
    return OID_Compromisso;
  }
  public void setDM_Lancado_Ordem_Frete(String DM_Lancado_Ordem_Frete) {
    this.DM_Lancado_Ordem_Frete = DM_Lancado_Ordem_Frete;
  }
  public String getDM_Lancado_Ordem_Frete() {
    return DM_Lancado_Ordem_Frete;
  }
  public void setDM_Tipo_Veiculo(String DM_Tipo_Veiculo) {
    this.DM_Tipo_Veiculo = DM_Tipo_Veiculo;
  }
  public String getDM_Tipo_Veiculo() {
    return DM_Tipo_Veiculo;
  }
  public void setOID_Empresa(long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }
  public long getOID_Empresa() {
    return OID_Empresa;
  }
  public void setDM_Tipo_Seguro(String DM_Tipo_Seguro) {
    this.DM_Tipo_Seguro = DM_Tipo_Seguro;
  }
  public String getDM_Tipo_Seguro() {
    return DM_Tipo_Seguro;
  }

  public int getDM_Layout() {
      return this.DM_Layout;
  }
  public void setDM_Layout(int layout) {
      this.DM_Layout = layout;
  }
  public String getOid_Veiculo() {
      return this.oid_Veiculo;
  }

  public double getVL_Nota_Fiscal () {
    return VL_Nota_Fiscal;
  }

  public double getNR_Volumes () {
    return NR_Volumes;
  }

  public double getNR_Peso () {
    return NR_Peso;
  }

  public String getNR_Processo () {
    return NR_Processo;
  }

  public String getOID_Processo () {

    return OID_Processo;
  }

  public String getDM_Tipo_Viagem () {
    return DM_Tipo_Viagem;
  }

  public String getDM_Tipo_Manifesto () {
    return DM_Tipo_Manifesto;
  }

  public void setOid_Veiculo(String oid_Veiculo) {
      this.oid_Veiculo = oid_Veiculo;
  }

  public void setVL_Nota_Fiscal (double VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }

  public void setNR_Volumes (double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }

  public void setNR_Peso (double NR_Peso) {
    this.NR_Peso = NR_Peso;
  }

  public void setNR_Processo (String NR_Processo) {
    this.NR_Processo = NR_Processo;
  }

  public void setOID_Processo (String OID_Processo) {

    this.OID_Processo = OID_Processo;
  }

  public void setDM_Tipo_Viagem (String DM_Tipo_Viagem) {
    this.DM_Tipo_Viagem = DM_Tipo_Viagem;
  }

  public void setDM_Tipo_Manifesto (String DM_Tipo_Manifesto) {
    this.DM_Tipo_Manifesto = DM_Tipo_Manifesto;
  }

  public String getDM_Tipo_ICMS() {
      return this.DM_Tipo_ICMS;
  }
  public void setDM_Tipo_ICMS(String tipo_ICMS) {
      this.DM_Tipo_ICMS = tipo_ICMS;
  }

  private void jbInit () throws Exception {
  }
public String getCD_Rota_Entrega() {
	return CD_Rota_Entrega;
}
public void setCD_Rota_Entrega(String rota_Entrega) {
	CD_Rota_Entrega = rota_Entrega;
}
public double getNr_peso() {
	return nr_peso;
}
public void setNr_peso(double nr_peso) {
	this.nr_peso = nr_peso;
}
public double getNr_quantidade_nota_fiscal() {
	return nr_quantidade_nota_fiscal;
}
public void setNr_quantidade_nota_fiscal(double nr_quantidade_nota_fiscal) {
	this.nr_quantidade_nota_fiscal = nr_quantidade_nota_fiscal;
}
public int getNr_quantidade_visitas() {
	return nr_quantidade_visitas;
}
public void setNr_quantidade_visitas(int nr_quantidade_visitas) {
	this.nr_quantidade_visitas = nr_quantidade_visitas;
}
public double getNr_volumes() {
	return nr_volumes;
}
public void setNr_volumes(double nr_volumes) {
	this.nr_volumes = nr_volumes;
}
public double getVl_nota_fiscal() {
	return vl_nota_fiscal;
}

  public long getNR_Nota_Fiscal () {
    return NR_Nota_Fiscal;
  }

  public double getVL_Ordem_Frete () {
    return VL_Ordem_Frete;
  }

  public String getDM_Cia_Aerea () {
    return DM_Cia_Aerea;
  }

  public String getNR_Voo () {
    return NR_Voo;
  }

  public String getNR_Master () {
    return NR_Master;
  }

  public String getHR_Voo () {
    return HR_Voo;
  }

  public String getDT_Voo () {
    return DT_Voo;
  }

  public void setVl_nota_fiscal(double vl_nota_fiscal) {
	this.vl_nota_fiscal = vl_nota_fiscal;
}

  public void setNR_Nota_Fiscal (long NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }

  public void setVL_Ordem_Frete (double VL_Ordem_Frete) {
    this.VL_Ordem_Frete = VL_Ordem_Frete;
  }

  public void setDM_Cia_Aerea (String DM_Cia_Aerea) {
    this.DM_Cia_Aerea = DM_Cia_Aerea;
  }

  public void setNR_Voo (String NR_Voo) {
    this.NR_Voo = NR_Voo;
  }

  public void setNR_Master (String NR_Master) {
    this.NR_Master = NR_Master;
  }

  public void setHR_Voo (String HR_Voo) {
    this.HR_Voo = HR_Voo;
  }

  public void setDT_Voo (String DT_Voo) {
    this.DT_Voo = DT_Voo;
  }
public long getNR_Romaneio() {
	return NR_Romaneio;
}
public void setNR_Romaneio(long romaneio) {
	NR_Romaneio = romaneio;
}
public long getOID_Usuario() {
	return OID_Usuario;
}
public void setOID_Usuario(long usuario) {
	OID_Usuario = usuario;
}
public String getNM_Senha() {
	return NM_Senha;
}
public void setNM_Senha(String senha) {
	NM_Senha = senha;
}
}
