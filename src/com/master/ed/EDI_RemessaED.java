package com.master.ed;

public class EDI_RemessaED extends MasterED{


  private String OID_EDI_Remessa;
  private String NR_Remessa;
  private String DM_Situacao;
  private String DM_Origem_Remessa;
  private String DT_Remessa;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Entregadora;
  private String OID_Pessoa_Pagador;
  private String NM_Remetente;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private double NR_Peso;
  private double NR_Volumes;
  private String HR_Entrada;
  private String OID_Pessoa_Consignatario;
  private double VL_Total_Frete;
  private double VL_Nota_Fiscal;
  private String NM_Produto;
  private String DT_Remessa_Inicial;
  private String DT_Remessa_Final; 
  private long OID_Modal;
  private String NR_Conhecimento;
  private String OID_Conhecimento;
  private double NR_Peso_Cubado;
  private long OID_Unidade;
  private String NR_Lote;
  private String NR_CNPJ_CPF;
  private String NR_CNPJ_CPF_Dest;
  private String NM_Unidade_EDI_Remessa;
  private String DT_Entrada;
  private String DM_Relatorio;
  private String NM_Cidade;
  private String NM_Bairro;
  private String NM_Endereco;
  private String NM_Destinatario;
  private String CD_Estado;
  private String NR_Cep;
  private String NM_Cidade_Dest;
  private String NM_Bairro_Dest;
  private String NM_Endereco_Dest;
  private String NM_Destinatario_Dest;
  private String CD_Estado_Dest;
  private String NR_Cep_Dest;
  private String DM_Tipo_Servico;
  private long OID_Cidade;
  private long OID_Cidade_Dest;
  private long OID_Produto;
  private long OID_Armazem;
  private String NM_Arquivo;
  private String NM_Natureza;
  private String DM_Tipo_Remessa;
  private long NR_Nota_Fiscal;
  private String NR_CNPJ_CPF_Destinatario;
  private String CD_Produto;
  private String NR_Pedido;
  private String CD_Destino;

  private String DT_EDI_Remessa_Inicial;
  private String DT_EDI_Remessa_Final;
  
  public String getDT_EDI_Remessa_Final() {
	return DT_EDI_Remessa_Final;
}
public void setDT_EDI_Remessa_Final(String remessa_Final) {
	DT_EDI_Remessa_Final = remessa_Final;
}
public String getDT_EDI_Remessa_Inicial() {
	return DT_EDI_Remessa_Inicial;
}
public void setDT_EDI_Remessa_Inicial(String remessa_Inicial) {
	DT_EDI_Remessa_Inicial = remessa_Inicial;
}
public void setOID_EDI_Remessa(String OID_EDI_Remessa) {
    this.OID_EDI_Remessa = OID_EDI_Remessa;
  }
  public String getOID_EDI_Remessa() {
    return OID_EDI_Remessa;
  }
  public void setNR_Remessa (String NR_Remessa) {

    this.NR_Remessa = NR_Remessa;
  }
  public String getNR_Remessa () {

    return NR_Remessa;
  }

  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }

  public void setDT_Remessa(String DT_Remessa) {
    this.DT_Remessa = DT_Remessa;
  }
  public String getDT_Remessa() {
    return DT_Remessa;
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
  public void setNR_Peso(double NR_Peso) {
    this.NR_Peso = NR_Peso;
  }
  public double getNR_Peso() {
    return NR_Peso;
  }
  public void setNR_Volumes(double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }
  public double getNR_Volumes() {
    return NR_Volumes;
  }

  public void setHR_Entrada(String HR_Entrada) {
    this.HR_Entrada = HR_Entrada;
  }
  public String getHR_Entrada() {
    return HR_Entrada;
  }

  public void setOID_Pessoa_Consignatario(String OID_Pessoa_Consignatario) {
    this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
  }
  public String getOID_Pessoa_Consignatario() {
    return OID_Pessoa_Consignatario;
  }

  public String getDT_Remessa_Final() {
    return DT_Remessa_Final;
  }
  public String getDT_Remessa_Inicial() {
    return DT_Remessa_Inicial;
  }
  public void setDT_Remessa_Final(String DT_Remessa_Final) {
    this.DT_Remessa_Final = DT_Remessa_Final;
  }
  public void setDT_Remessa_Inicial(String DT_Remessa_Inicial) {
    this.DT_Remessa_Inicial = DT_Remessa_Inicial;
  }

  public String getNM_Produto() {
    return NM_Produto;
  }

  public void setNM_Produto(String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setOID_Modal(long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }
  public long getOID_Modal() {
    return OID_Modal;
  }

  public void setNR_Conhecimento(String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public String getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }

  public void setNR_Peso_Cubado(double NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }
  public double getNR_Peso_Cubado() {
    return NR_Peso_Cubado;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }

  public void setNR_Lote (String NR_Lote) {
    this.NR_Lote = NR_Lote;
  }

  public String getNR_Lote () {
    return NR_Lote;
  }

  public void setNR_CNPJ_CPF (String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public void setNM_Unidade_EDI_Remessa (String NM_Unidade_EDI_Remessa) {
    this.NM_Unidade_EDI_Remessa = NM_Unidade_EDI_Remessa;
  }

  public String getNM_Unidade_EDI_Remessa () {
    return NM_Unidade_EDI_Remessa;
  }

  public void setDT_Entrada (String DT_Entrada) {
    this.DT_Entrada = DT_Entrada;
  }

  public String getDT_Entrada () {
    return DT_Entrada;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public void setNM_Bairro (String NM_Bairro) {
    this.NM_Bairro = NM_Bairro;
  }

  public String getNM_Bairro () {
    return NM_Bairro;
  }

  public void setNM_Endereco (String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }

  public String getNM_Endereco () {
    return NM_Endereco;
  }

  public void setNM_Destinatario (String NM_Destinatario) {
    this.NM_Destinatario = NM_Destinatario;
  }

  public String getNM_Destinatario () {
    return NM_Destinatario;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setDM_Tipo_Servico (String DM_Tipo_Servico) {
    this.DM_Tipo_Servico = DM_Tipo_Servico;
  }

  public String getDM_Tipo_Servico () {
    return DM_Tipo_Servico;
  }

  public void setOID_Cidade (long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }

  public long getOID_Cidade () {
    return OID_Cidade;
  }

  public void setOID_Produto (long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }

  public long getOID_Produto () {
    return OID_Produto;
  }

  public void setOID_Armazem (long OID_Armazem) {
    this.OID_Armazem = OID_Armazem;
  }

  public long getOID_Armazem () {
    return OID_Armazem;
  }

  public void setNM_Arquivo (String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }

  public String getNM_Arquivo () {
    return NM_Arquivo;
  }

  public void setNM_Natureza (String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }

  public String getNM_Natureza () {
    return NM_Natureza;
  }

  public void setDM_Tipo_Remessa (String DM_Tipo_Remessa) {

    this.DM_Tipo_Remessa = DM_Tipo_Remessa;
  }

  public String getDM_Tipo_Remessa () {

    return DM_Tipo_Remessa;
  }

  public void setNR_Nota_Fiscal (long NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }

  public long getNR_Nota_Fiscal () {
    return NR_Nota_Fiscal;
  }

  public void setNR_CNPJ_CPF_Destinatario (String NR_CNPJ_CPF_Destinatario) {
    this.NR_CNPJ_CPF_Destinatario = NR_CNPJ_CPF_Destinatario;
  }

  public String getNR_CNPJ_CPF_Destinatario () {
    return NR_CNPJ_CPF_Destinatario;
  }

  public void setCD_Produto (String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }

  public String getCD_Produto () {
    return CD_Produto;
  }

  public void setNR_Pedido (String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }

  public String getNR_Pedido () {
    return NR_Pedido;
  }
public String getDM_Origem_Remessa() {
	return DM_Origem_Remessa;
}
public void setDM_Origem_Remessa(String origem_Remessa) {
	DM_Origem_Remessa = origem_Remessa;
}
public String getCD_Estado_Dest() {
	return CD_Estado_Dest;
}
public void setCD_Estado_Dest(String estado_Dest) {
	CD_Estado_Dest = estado_Dest;
}
public String getNM_Bairro_Dest() {
	return NM_Bairro_Dest;
}
public void setNM_Bairro_Dest(String bairro_Dest) {
	NM_Bairro_Dest = bairro_Dest;
}
public String getNM_Cidade_Dest() {
	return NM_Cidade_Dest;
}
public void setNM_Cidade_Dest(String cidade_Dest) {
	NM_Cidade_Dest = cidade_Dest;
}
public String getNM_Destinatario_Dest() {
	return NM_Destinatario_Dest;
}
public void setNM_Destinatario_Dest(String destinatario_Dest) {
	NM_Destinatario_Dest = destinatario_Dest;
}
public String getNM_Endereco_Dest() {
	return NM_Endereco_Dest;
}
public void setNM_Endereco_Dest(String endereco_Dest) {
	NM_Endereco_Dest = endereco_Dest;
}
public String getNR_Cep() {
	return NR_Cep;
}
public void setNR_Cep(String cep) {
	NR_Cep = cep;
}
public String getNR_Cep_Dest() {
	return NR_Cep_Dest;
}
public void setNR_Cep_Dest(String cep_Dest) {
	NR_Cep_Dest = cep_Dest;
}
public String getNR_CNPJ_CPF_Dest() {
	return NR_CNPJ_CPF_Dest;
}
public void setNR_CNPJ_CPF_Dest(String dest) {
	NR_CNPJ_CPF_Dest = dest;
}
public String getNM_Remetente() {
	return NM_Remetente;
}
public void setNM_Remetente(String remetente) {
	NM_Remetente = remetente;
}
public long getOID_Cidade_Dest() {
	return OID_Cidade_Dest;
}
public void setOID_Cidade_Dest(long cidade_Dest) {
	OID_Cidade_Dest = cidade_Dest;
}
public String getOID_Pessoa_Entregadora() {
	return OID_Pessoa_Entregadora;
}
public void setOID_Pessoa_Entregadora(String pessoa_Entregadora) {
	OID_Pessoa_Entregadora = pessoa_Entregadora;
}

public String getCD_Destino() {
	return CD_Destino;
}
public void setCD_Destino(String destino) {
	CD_Destino = destino;
}

public double getVL_Nota_Fiscal() {
	return VL_Nota_Fiscal;
}
public void setVL_Nota_Fiscal(double nota_Fiscal) {
	VL_Nota_Fiscal = nota_Fiscal;
}
public String getOID_Pessoa_Pagador() {
	return OID_Pessoa_Pagador;
}
public void setOID_Pessoa_Pagador(String Pessoa_Pagador) {
	OID_Pessoa_Pagador = Pessoa_Pagador;
}
}
