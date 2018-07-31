package com.master.ed;


public class EDI_FiscalED extends MasterED{

//Dados para entrada de informações

  public String CD_Tipo;
  public String DT_Inicial;
  public String DT_Final;
  public String NM_Arquivo;
  public long OID_Unidade;
  public String DM_Periodo;
  public String DM_Operacao;
  public String NM_Finalidade_Arquivo;

//Dados para o registro 10,11 e 90
  public String NM_Inscricao_Estadual;
  public String NM_Razao_Social;
  public String NM_Cidade;
  public String CD_Estado;
  public String NM_Fax;
  public String CD_Convenio;
  public String CD_Natureza;
  public String CD_Finalidade;
  public String NM_Endereco;
  public String NR_Numero;
  public String NM_Complemento;
  public String NM_Bairro;
  public String NR_CEP;
  public String NR_Telefone;
  public String NM_Contato;

//  Registro 70
  public String DT_Emissao;
  public String NR_Modelo;
  public String NM_Serie;
  public String NM_Subserie;
  public String NR_CFOP;
  public String NR_Modalidade;
  public String NR_Situacao;

// Registro 71

  public String CD_Estado_tomador;
  public String DT_Emissao_nota_fiscal;
  public String NR_Modelo_nota_fiscal;
  public String NM_Serie_nota_fiscal;
  public String NM_Subserie_nota_fiscal;
  public String NR_Nota_Fiscal;
  public String Brancos;
  public String NM_Razao_Social_tomador;

// Registro 90
   public String Tipo_Registro50;
   public String Total_Registros50;
   public String Tipo_Registro70;
   public String Total_Registros70;
   public String Tipo_Registro71;
   public String Total_Registros71;

//Dados Saida
  private String NM_inscricao_estadual_tomador;
  private String NR_CNPJ_CPF;
  private String NR_CNPJ_CPF_tomador;
  private String NR_Conhecimento;
  private String VL_Base_calculo;
  private String VL_ICMS;
  private String VL_Isento;
  private String VL_Nota_Fiscal;
  private String VL_Outras;
  private String VL_total_doc_fiscal;
  private String VL_Total_Prestacao;
  private int Total_Registros;
  private String DM_Tipo_Documento_Entrada;
  private String DM_Tipo_Documento_Saida;
  private String PC_Aliquota;
  private String UF_Origem;
  private String UF_Destino;
  private String NM_Cidade_Origem;
  private String NM_Cidade_Destino;
  private String PE_Aliquota_ICMS;
  private String TX_Observacao;

  private double vl_base;
  private double vl_icms;
  private double vl_isento;
  private double vl_nota_fiscal;
  private double vl_outras;

  public EDI_FiscalED(){
 }
  public String getBrancos() {
    return Brancos;
  }
  public void setBrancos(String Brancos) {
    this.Brancos = Brancos;
  }
  public void setCD_Convenio(String CD_Convenio) {
    this.CD_Convenio = CD_Convenio;
  }
  public String getCD_Convenio() {
    return CD_Convenio;
  }
  public String getCD_Estado_tomador() {
    return CD_Estado_tomador;
  }
  public void setCD_Estado(String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }
  public void setCD_Estado_tomador(String CD_Estado_tomador) {
    this.CD_Estado_tomador = CD_Estado_tomador;
  }
  public String getCD_Estado() {
    return CD_Estado;
  }
  public String getCD_Finalidade() {
    return CD_Finalidade;
  }
  public String getCD_Natureza() {
    return CD_Natureza;
  }
  public String getCD_Tipo() {
    return CD_Tipo;
  }
  public void setCD_Finalidade(String CD_Finalidade) {
    this.CD_Finalidade = CD_Finalidade;
  }
  public void setCD_Natureza(String CD_Natureza) {
    this.CD_Natureza = CD_Natureza;
  }
  public void setCD_Tipo(String CD_Tipo) {
    this.CD_Tipo = CD_Tipo;
  }
  public String getDM_Operacao() {
    return DM_Operacao;
  }
  public void setDM_Operacao(String DM_Operacao) {
    this.DM_Operacao = DM_Operacao;
  }
  public void setDM_Periodo(String DM_Periodo) {
    this.DM_Periodo = DM_Periodo;
  }
  public String getDM_Periodo() {
    return DM_Periodo;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public String getDT_Emissao_nota_fiscal() {
    return DT_Emissao_nota_fiscal;
  }
  public String getDT_Final() {
    return DT_Final;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public void setDT_Emissao_nota_fiscal(String DT_Emissao_nota_fiscal) {
    this.DT_Emissao_nota_fiscal = DT_Emissao_nota_fiscal;
  }
  public void setDT_Final(String DT_Final) {
    this.DT_Final = DT_Final;
  }
  public void setDT_Inicial(String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }
  public void setNM_Arquivo(String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }
  public void setNM_Bairro(String NM_Bairro) {
    this.NM_Bairro = NM_Bairro;
  }
  public void setNM_Cidade(String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }
  public void setNM_Complemento(String NM_Complemento) {
    this.NM_Complemento = NM_Complemento;
  }
  public void setNM_Contato(String NM_Contato) {
    this.NM_Contato = NM_Contato;
  }
  public void setNM_Endereco(String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }
  public String getDT_Inicial() {
    return DT_Inicial;
  }
  public String getNM_Arquivo() {
    return NM_Arquivo;
  }
  public String getNM_Bairro() {
    return NM_Bairro;
  }
  public String getNM_Cidade() {
    return NM_Cidade;
  }
  public String getNM_Complemento() {
    return NM_Complemento;
  }
  public String getNM_Contato() {
    return NM_Contato;
  }
  public String getNM_Endereco() {
    return NM_Endereco;
  }
  public String getNM_Fax() {
    return NM_Fax;
  }
  public String getNM_Finalidade_Arquivo() {
    return NM_Finalidade_Arquivo;
  }
  public String getNM_inscricao_estadual_tomador() {
    return NM_inscricao_estadual_tomador;
  }
  public String getNM_Inscricao_Estadual() {
    return NM_Inscricao_Estadual;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public String getNM_Serie_nota_fiscal() {
    return NM_Serie_nota_fiscal;
  }
  public String getNM_Subserie() {
    return NM_Subserie;
  }
  public String getNM_Subserie_nota_fiscal() {
    return NM_Subserie_nota_fiscal;
  }
  public String getNR_CEP() {
    return NR_CEP;
  }
  public void setNM_Fax(String NM_Fax) {
    this.NM_Fax = NM_Fax;
  }
  public void setNM_Finalidade_Arquivo(String NM_Finalidade_Arquivo) {
    this.NM_Finalidade_Arquivo = NM_Finalidade_Arquivo;
  }
  public void setNM_Inscricao_Estadual(String NM_Inscricao_Estadual) {
    this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
  }
  public void setNM_inscricao_estadual_tomador(String NM_inscricao_estadual_tomador) {
    this.NM_inscricao_estadual_tomador = NM_inscricao_estadual_tomador;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public void setNM_Serie_nota_fiscal(String NM_Serie_nota_fiscal) {
    this.NM_Serie_nota_fiscal = NM_Serie_nota_fiscal;
  }
  public void setNM_Subserie(String NM_Subserie) {
    this.NM_Subserie = NM_Subserie;
  }
  public void setNM_Subserie_nota_fiscal(String NM_Subserie_nota_fiscal) {
    this.NM_Subserie_nota_fiscal = NM_Subserie_nota_fiscal;
  }
  public void setNR_CEP(String NR_CEP) {
    this.NR_CEP = NR_CEP;
  }
  public String getNR_CFOP() {
    return NR_CFOP;
  }
  public String getNR_CNPJ_CPF() {
    return NR_CNPJ_CPF;
  }
  public String getNR_CNPJ_CPF_tomador() {
    return NR_CNPJ_CPF_tomador;
  }
  public String getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public String getNR_Modalidade() {
    return NR_Modalidade;
  }
  public String getNR_Modelo() {
    return NR_Modelo;
  }
  public String getNR_Modelo_nota_fiscal() {
    return NR_Modelo_nota_fiscal;
  }
  public String getNR_Nota_Fiscal() {
    return NR_Nota_Fiscal;
  }
  public String getNR_Numero() {
    return NR_Numero;
  }
  public String getNR_Situacao() {
    return NR_Situacao;
  }
  public String getNR_Telefone() {
    return NR_Telefone;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public String getTipo_Registro70() {
    return Tipo_Registro70;
  }
  public void setNR_CFOP(String NR_CFOP) {
    this.NR_CFOP = NR_CFOP;
  }
  public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }
  public void setNR_CNPJ_CPF_tomador(String NR_CNPJ_CPF_tomador) {
    this.NR_CNPJ_CPF_tomador = NR_CNPJ_CPF_tomador;
  }
  public void setNR_Conhecimento(String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public void setNR_Modalidade(String NR_Modalidade) {
    this.NR_Modalidade = NR_Modalidade;
  }
  public void setNR_Modelo(String NR_Modelo) {
    this.NR_Modelo = NR_Modelo;
  }
  public void setNR_Modelo_nota_fiscal(String NR_Modelo_nota_fiscal) {
    this.NR_Modelo_nota_fiscal = NR_Modelo_nota_fiscal;
  }
  public void setNR_Nota_Fiscal(String NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }
  public void setNR_Numero(String NR_Numero) {
    this.NR_Numero = NR_Numero;
  }
  public void setNR_Situacao(String NR_Situacao) {
    this.NR_Situacao = NR_Situacao;
  }
  public void setNR_Telefone(String NR_Telefone) {
    this.NR_Telefone = NR_Telefone;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public void setTipo_Registro70(String Tipo_Registro70) {
    this.Tipo_Registro70 = Tipo_Registro70;
  }
  public void setTipo_Registro71(String Tipo_Registro71) {
    this.Tipo_Registro71 = Tipo_Registro71;
  }
  public void setTotal_Registros(int Total_Registros) {
    this.Total_Registros = Total_Registros;
  }
  public void setTotal_Registros70(String Total_Registros70) {
    this.Total_Registros70 = Total_Registros70;
  }
  public void setTotal_Registros71(String Total_Registros71) {
    this.Total_Registros71 = Total_Registros71;
  }
  public void setVL_Base_calculo(String VL_Base_calculo) {
    this.VL_Base_calculo = VL_Base_calculo;
  }
  public void setVL_ICMS(String VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }
  public void setVL_Isento(String VL_Isento) {
    this.VL_Isento = VL_Isento;
  }
  public void setVL_Nota_Fiscal(String VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }
  public void setVL_Outras(String VL_Outras) {
    this.VL_Outras = VL_Outras;
  }
  public void setVL_total_doc_fiscal(String VL_total_doc_fiscal) {
    this.VL_total_doc_fiscal = VL_total_doc_fiscal;
  }
  public void setVL_Total_Prestacao(String VL_Total_Prestacao) {
    this.VL_Total_Prestacao = VL_Total_Prestacao;
  }
  public String getVL_Total_Prestacao() {
    return VL_Total_Prestacao;
  }
  public String getVL_total_doc_fiscal() {
    return VL_total_doc_fiscal;
  }
  public String getVL_Outras() {
    return VL_Outras;
  }
  public String getVL_Nota_Fiscal() {
    return VL_Nota_Fiscal;
  }
  public String getVL_Isento() {
    return VL_Isento;
  }
  public String getVL_ICMS() {
    return VL_ICMS;
  }
  public String getVL_Base_calculo() {
    return VL_Base_calculo;
  }
  public String getTotal_Registros71() {
    return Total_Registros71;
  }
  public String getTotal_Registros70() {
    return Total_Registros70;
  }
  public int getTotal_Registros() {
    return Total_Registros;
  }
  public String getTipo_Registro71() {
    return Tipo_Registro71;
  }
  public void setDM_Tipo_Documento_Entrada (String DM_Tipo_Documento_Entrada) {

    this.DM_Tipo_Documento_Entrada = DM_Tipo_Documento_Entrada;
  }
  public String getDM_Tipo_Documento_Entrada () {

    return DM_Tipo_Documento_Entrada;
  }

	public String getNM_Razao_Social_tomador() {
	    return NM_Razao_Social_tomador;
	}
	public void setNM_Razao_Social_tomador(String razao_Social_tomador) {
	    NM_Razao_Social_tomador = razao_Social_tomador;
	}
	public String getPC_Aliquota() {
	    return PC_Aliquota;
	}
	public void setPC_Aliquota(String aliquota) {
	    PC_Aliquota = aliquota;
	}
	public String getTipo_Registro50() {
	    return Tipo_Registro50;
	}
	public void setTipo_Registro50(String tipo_Registro50) {
	    Tipo_Registro50 = tipo_Registro50;
	}
	public String getTotal_Registros50() {
	    return Total_Registros50;
	}
	public void setTotal_Registros50(String total_Registros50) {
	    Total_Registros50 = total_Registros50;
	}

  public String getDM_Tipo_Documento_Saida () {

    return DM_Tipo_Documento_Saida;
  }

  public void setDM_Tipo_Documento_Saida (String
                                          DM_Tipo_Documento_Saida) {

    this.DM_Tipo_Documento_Saida = DM_Tipo_Documento_Saida;
  }

  public void setUF_Origem (String UF_Origem) {
    this.UF_Origem = UF_Origem;
  }

  public String getUF_Origem () {
    return UF_Origem;
  }

  public void setUF_Destino (String UF_Destino) {
    this.UF_Destino = UF_Destino;
  }

  public String getUF_Destino () {
    return UF_Destino;
  }

  public void setNM_Cidade_Origem (String NM_Cidade_Origem) {
    this.NM_Cidade_Origem = NM_Cidade_Origem;
  }

  public String getNM_Cidade_Origem () {
    return NM_Cidade_Origem;
  }

  public void setNM_Cidade_Destino (String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }

  public String getNM_Cidade_Destino () {
    return NM_Cidade_Destino;
  }

  public void setPE_Aliquota_ICMS (String PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }

  public String getPE_Aliquota_ICMS () {
    return PE_Aliquota_ICMS;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }
public double getVl_base() {
	return vl_base;
}
public void setVl_base(double vl_base) {
	this.vl_base = vl_base;
}
public double getVl_icms() {
	return vl_icms;
}
public void setVl_icms(double vl_icms) {
	this.vl_icms = vl_icms;
}
public double getVl_isento() {
	return vl_isento;
}
public void setVl_isento(double vl_isento) {
	this.vl_isento = vl_isento;
}
public double getVl_nota_fiscal() {
	return vl_nota_fiscal;
}
public void setVl_nota_fiscal(double vl_nota_fiscal) {
	this.vl_nota_fiscal = vl_nota_fiscal;
}
public double getVl_outras() {
	return vl_outras;
}
public void setVl_outras(double vl_outras) {
	this.vl_outras = vl_outras;
}
}
