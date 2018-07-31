package com.master.ed;

import com.master.util.Valores;

/**
 * Título: WMS_Nota_FiscalED
 * Descrição: Notas Fiscais - ED
 * Data da criação: 11/2003
 * Atualizado em: 05/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_Nota_FiscalED extends MasterED{

  private String oid_nota_fiscal;
  private long nr_nota_fiscal;
  private String dt_emissao;
  private String dt_emissao_final;
  private double nr_volumes;
  private String nm_serie;
  private String dt_entrada;
  private String hr_entrada;
  private String oid_pessoa;
  private String oid_cliente;
  private String oid_pessoa_destinatario;
  private String oid_pessoa_transportador;
  private long oid_natureza_operacao;
  private long oid_modelo_nota_fiscal;
  private String dm_tipo_nota_fiscal;
  private String dt_stamp;
  private String usuario_stamp;
  private String dm_stamp;
  private String dm_observacao;
  private double vl_descontos;
  private double vl_icms;
  private String vl_icms_TX;
  private double vl_inss;
  private double vl_ipi;
  private String vl_ipi_TX;
  private String DM_Picking;
  private double VL_PIS;
  private double VL_CSLL;
  private double VL_Cofins;
  private String DM_Exportada; 
  private double vl_irrf;
  private double vl_isqn;
  private double vl_liquido_nota_fiscal;
  private String vl_liquido_nota_fiscal_TX;
  private double vl_nota_fiscal;
  private double vl_total_despesas;
  private String vl_total_despesas_TX;
  private double vl_total_frete;
  private String vl_total_frete_TX;
  private double vl_total_seguro;
  private String vl_total_seguro_TX;
  private String dm_forma_pagamento;
  private long nr_parcelas;
  private String dm_finalizado;
  private long OID_Unidade_Fiscal;
  private long OID_Unidade;
  private long OID_Unidade_Contabil;
  private String NM_Unidade_Fiscal;
  private String NM_Unidade;
  private String NM_Unidade_Contabil;
  private String nm_Fantasia;
  private String CD_Unidade_Fiscal;
  private String CD_Unidade_Contabil;
  private String CD_Unidade;
  private String cd_Natureza_Operacao;
  private String DM_Contabiliza;
  private String DM_Estoque;
  private String DM_Financeiro;
  private double VL_Parcela_Calculo;
  private double VL_Servico;
  private String nm_razao_social_cliente;
  private String NM_Razao_Social_Remetente;
  private String NM_Razao_Social_Destinatario;
  private String NM_Razao_Social_Transportador;
  private String DM_Gerado;
  private int OID_Empresa;
  private String CD_Empresa;
  private String NM_Empresa;
  private double VL_Base_Calculo_ICMS;
  private String VL_Base_Calculo_ICMS_TX;
  private double VL_Base_Calculo_ICMS_Substituicao;
  private String VL_Base_Calculo_ICMS_Substituicao_TX;
  private double VL_ICMS_Substituicao;
  private String VL_ICMS_Substituicao_TX;
  private double VL_Total_Produtos;
  private String VL_Total_Produtos_TX;
  private double NR_Quantidade;
  private String DM_Frete;
  private String NM_Especie;
  private String NM_Marca;
  private String NR_Placa;
  private String NR_Numero;
  private double NR_Peso_Bruto;
  private double NR_Peso_Liquido;  
  private String DM_Impresso;
  private String DM_Requisicao;
  private String NM_Arquivo;
  private String nm_Natureza_Operacao;
  private int OID_Deposito;
  private String NM_Deposito;
  private String DM_Cancelada;
  private int nr_itens;
  
  private String dm_calcINSS;
  private String dm_calcPIS;
  private String dm_calcCOFINS;
  
  private String isCalcINSS;
  private String isCalcPIS;
  private String isCalcCOFINS;
  
  public String getDM_Cancelada() {
    return DM_Cancelada;
  }

  public void setDM_Cancelada(String dm_Cancelada) {
    this.DM_Cancelada = dm_Cancelada;
  }
  
  public String getNM_Deposito() {
    return NM_Deposito;
  }
  
  public int getOID_Deposito() {
    return OID_Deposito;
  }
  
  public void setNM_Deposito(String NM_Deposito) {
    this.NM_Deposito = NM_Deposito;
  }
  
  public void setOID_Deposito(int OID_Deposito) {
    this.OID_Deposito = OID_Deposito;
  }
  
  public void setNM_Arquivo(String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }
  
  public String getNM_Arquivo() {
    return NM_Arquivo;
  }
  
  public void setDM_Requisicao(String dm_Requisicao) {
    this.DM_Requisicao = dm_Requisicao;
  }
  
  public String getDM_Requisicao() {
    return DM_Requisicao;
  }

  public void setDM_Gerado(String dm_Gerado) {
    this.DM_Gerado = dm_Gerado;
  }
  
  public String getDM_Gerado() {
    return DM_Gerado;
  }

  public void setDM_Impresso(String dm_Impresso) {
    this.DM_Impresso = dm_Impresso;
  }
  
  public String getDM_Impresso() {
    return DM_Impresso;
  }
  
  public void setDm_stamp(String dm_stamp) {
    this.dm_stamp = dm_stamp;
  }
  public void setDm_tipo_nota_fiscal(String dm_tipo_nota_fiscal) {
    this.dm_tipo_nota_fiscal = dm_tipo_nota_fiscal;
  }
  public void setDt_emissao(String dt_emissao) {
    this.dt_emissao = dt_emissao;
  }
  
  public void setDt_emissao_final(String dt_emissao_final) {
    this.dt_emissao_final = dt_emissao_final;
  }
  
  public void setDt_entrada(String dt_entrada) {
    this.dt_entrada = dt_entrada;
  }
  public void setHr_entrada(String hr_entrada) {
    this.hr_entrada = hr_entrada;
  }
  public void setNr_nota_fiscal(long nr_nota_fiscal) {
    this.nr_nota_fiscal = nr_nota_fiscal;
  }
  public void setNm_serie(String nm_serie) {
    this.nm_serie = nm_serie;
  }
  public void setNr_volumes(double nr_volumes) {
    this.nr_volumes = nr_volumes;
  }
  public void setOid_modelo_nota_fiscal(long oid_modelo_nota_fiscal) {
    this.oid_modelo_nota_fiscal = oid_modelo_nota_fiscal;
  }
  public void setOid_natureza_operacao(long oid_natureza_operacao) {
    this.oid_natureza_operacao = oid_natureza_operacao;
  }
  public void setOid_nota_fiscal(String oid_nota_fiscal) {
    this.oid_nota_fiscal = oid_nota_fiscal;
  }
  public void setOid_pessoa(String oid_pessoa) {
    this.oid_pessoa = oid_pessoa;
  }
  public void setOid_pessoa_destinatario(String oid_pessoa_destinatario) {
    this.oid_pessoa_destinatario = oid_pessoa_destinatario;
  }
  public void setUsuario_stamp(String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }
  public void setVl_icms(double vl_icms) {
    this.vl_icms = vl_icms;
  }
  public void setVl_inss(double vl_inss) {
    this.vl_inss = vl_inss;
  }
  public void setVl_ipi(double vl_ipi) {
    this.vl_ipi = vl_ipi;
  }
  public void setVl_irrf(double vl_irrf) {
    this.vl_irrf = vl_irrf;
  }
  public void setVl_isqn(double vl_isqn) {
    this.vl_isqn = vl_isqn;
  }
  public void setVl_liquido_nota_fiscal(double vl_liquido_nota_fiscal) {
    this.vl_liquido_nota_fiscal = vl_liquido_nota_fiscal;
  }
  public void setVl_nota_fiscal(double vl_nota_fiscal) {
    this.vl_nota_fiscal = vl_nota_fiscal;
  }
  public void setVl_total_despesas(double vl_total_despesas) {
    this.vl_total_despesas = vl_total_despesas;
  }
  public void setVl_total_frete(double vl_total_frete) {
    this.vl_total_frete = vl_total_frete;
  }
  public String getDm_stamp() {
    return dm_stamp;
  }
  public String getDm_tipo_nota_fiscal() {
    return dm_tipo_nota_fiscal;
  }
  public String getDt_emissao() {
    return dt_emissao;
  }
  public String getDt_emissao_final() {
    return dt_emissao_final;
  }
  public String getDt_entrada() {
    return dt_entrada;
  }
  public String getHr_entrada() {
    return hr_entrada;
  }
  public String getNm_serie() {
    return nm_serie;
  }
  public long getNr_nota_fiscal() {
    return nr_nota_fiscal;
  }
  public double getNr_volumes() {
    return nr_volumes;
  }
  public long getOid_modelo_nota_fiscal() {
    return oid_modelo_nota_fiscal;
  }
  public long getOid_natureza_operacao() {
    return oid_natureza_operacao;
  }
  public String getOid_nota_fiscal() {
    return oid_nota_fiscal;
  }
  public String getOid_pessoa() {
    return oid_pessoa;
  }
  public String getOid_pessoa_destinatario() {
    return oid_pessoa_destinatario;
  }
  public String getUsuario_stamp() {
    return usuario_stamp;
  }
  public double getVl_icms() {
    return vl_icms;
  }
  public double getVl_ipi() {
    return vl_ipi;
  }
  public double getVl_inss() {
    return vl_inss;
  }
  public double getVl_irrf() {
    return vl_irrf;
  }
  public double getVl_isqn() {
    return vl_isqn;
  }
  public double getVl_liquido_nota_fiscal() {
    return vl_liquido_nota_fiscal;
  }
  public double getVl_nota_fiscal() {
    return vl_nota_fiscal;
  }
  public double getVl_total_despesas() {
    return vl_total_despesas;
  }
  public double getVl_total_frete() {
    return vl_total_frete;
  }
  public void setDm_observacao(String dm_observacao) {
    this.dm_observacao = dm_observacao;
  }
  public String getDm_observacao() {
    return dm_observacao;
  }
  public double getVl_descontos() {
    return vl_descontos;
  }
  public void setVl_descontos(double vl_descontos) {
    this.vl_descontos = vl_descontos;
  }
  public void setVl_total_seguro(double vl_total_seguro) {
    this.vl_total_seguro = vl_total_seguro;
  }
  public double getVl_total_seguro() {
    return vl_total_seguro;
  }
  public void setDm_forma_pagamento(String dm_forma_pagamento) {
    this.dm_forma_pagamento = dm_forma_pagamento;
  }
  public String getDm_forma_pagamento() {
    return dm_forma_pagamento;
  }
  public void setNr_parcelas(long nr_parcelas) {
    this.nr_parcelas = nr_parcelas;
  }
  public long getNr_parcelas() {
    return nr_parcelas;
  }
  public void setDm_finalizado(String dm_finalizado) {
    this.dm_finalizado = dm_finalizado;
  }
  public String getDm_finalizado() {
    return dm_finalizado;
  }
  public void setOID_Unidade_Fiscal(long OID_Unidade_Fiscal) {
    this.OID_Unidade_Fiscal = OID_Unidade_Fiscal;
  }
  public long getOID_Unidade_Fiscal() {
    return OID_Unidade_Fiscal;
  }
  public void setOID_Unidade_Contabil(long OID_Unidade_Contabil) {
    this.OID_Unidade_Contabil = OID_Unidade_Contabil;
  }
  public long getOID_Unidade_Contabil() {
    return OID_Unidade_Contabil;
  }
  public void setNM_Unidade_Fiscal(String NM_Unidade_Fiscal) {
    this.NM_Unidade_Fiscal = NM_Unidade_Fiscal;
  }
  public String getNM_Unidade_Fiscal() {
    return NM_Unidade_Fiscal;
  }
  public void setNM_Unidade_Contabil(String NM_Unidade_Contabil) {
    this.NM_Unidade_Contabil = NM_Unidade_Contabil;
  }
  public String getNM_Unidade_Contabil() {
    return NM_Unidade_Contabil;
  }
  public void setCD_Unidade_Fiscal(String CD_Unidade_Fiscal) {
    this.CD_Unidade_Fiscal = CD_Unidade_Fiscal;
  }
  public String getCD_Unidade_Fiscal() {
    return CD_Unidade_Fiscal;
  }
  public void setCD_Unidade_Contabil(String CD_Unidade_Contabil) {
    this.CD_Unidade_Contabil = CD_Unidade_Contabil;
  }
  public String getCD_Unidade_Contabil() {
    return CD_Unidade_Contabil;
  }
  
  
  public void setDM_Contabiliza(String DM_Contabiliza) {
    this.DM_Contabiliza = DM_Contabiliza;
  }
  public String getDM_Contabiliza() {
    return DM_Contabiliza;
  }
  public void setDM_Estoque(String DM_Estoque) {
    this.DM_Estoque = DM_Estoque;
  }
  public String getDM_Estoque() {
    return DM_Estoque;
  }
  public void setDM_Financeiro(String DM_Financeiro) {
    this.DM_Financeiro = DM_Financeiro;
  }
  public String getDM_Financeiro() {
    return DM_Financeiro;
  }
  public void setVL_Parcela_Calculo(double VL_Parcela_Calculo) {
    this.VL_Parcela_Calculo = VL_Parcela_Calculo;
  }
  public double getVL_Parcela_Calculo() {
    return VL_Parcela_Calculo;
  }
  public void setVL_Servico(double VL_Servico) {
    this.VL_Servico = VL_Servico;
  }
  public double getVL_Servico() {
    return VL_Servico;
  }
  public String getOid_pessoa_transportador() {
    return oid_pessoa_transportador;
  }
  public void setOid_pessoa_transportador(String oid_pessoa_transportador) {
    this.oid_pessoa_transportador = oid_pessoa_transportador;
  }
  public String getNM_Razao_Social_Destinatario() {
    return NM_Razao_Social_Destinatario;
  }
  public String getNM_Razao_Social_Remetente() {
    return NM_Razao_Social_Remetente;
  }
  public String getNM_Razao_Social_Transportador() {
    return NM_Razao_Social_Transportador;
  }
  public void setNM_Razao_Social_Destinatario(String NM_Razao_Social_Destinatario) {
    this.NM_Razao_Social_Destinatario = NM_Razao_Social_Destinatario;
  }
  public void setNM_Razao_Social_Remetente(String NM_Razao_Social_Remetente) {
    this.NM_Razao_Social_Remetente = NM_Razao_Social_Remetente;
  }
  public void setNM_Razao_Social_Transportador(String NM_Razao_Social_Transportador) {
    this.NM_Razao_Social_Transportador = NM_Razao_Social_Transportador;
  }
  public String getCD_Empresa() {
    return CD_Empresa;
  }
  public void setCD_Empresa(String CD_Empresa) {
    this.CD_Empresa = CD_Empresa;
  }
  public String getDM_Frete() {
    return DM_Frete;
  }
  public void setDM_Frete(String DM_Frete) {
    this.DM_Frete = DM_Frete;
  }
  public String getNM_Empresa() {
    return NM_Empresa;
  }
  public String getNM_Especie() {
    return NM_Especie;
  }
  public String getNM_Marca() {
    return NM_Marca;
  }
  public void setNM_Empresa(String NM_Empresa) {
    this.NM_Empresa = NM_Empresa;
  }
  public void setNM_Especie(String NM_Especie) {
    this.NM_Especie = NM_Especie;
  }
  public void setNM_Marca(String NM_Marca) {
    this.NM_Marca = NM_Marca;
  }
  public String getNR_Numero() {
    return NR_Numero;
  }
  public void setNR_Numero(String NR_Numero) {
    this.NR_Numero = NR_Numero;
  }
  public double getNR_Peso_Bruto() {
    return NR_Peso_Bruto;
  }
  public double getNR_Peso_Liquido() {
    return NR_Peso_Liquido;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public double getNR_Quantidade() {
    return NR_Quantidade;
  }
  public void setNR_Peso_Bruto(double NR_Peso_Bruto) {
    this.NR_Peso_Bruto = NR_Peso_Bruto;
  }
  public void setNR_Peso_Liquido(double NR_Peso_Liquido) {
    this.NR_Peso_Liquido = NR_Peso_Liquido;
  }
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public void setNR_Quantidade(double NR_Quantidade) {
    this.NR_Quantidade = NR_Quantidade;
  }
  public int getOID_Empresa() {
    return OID_Empresa;
  }
  public void setOID_Empresa(int OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }
  public double getVL_Base_Calculo_ICMS() {
    return VL_Base_Calculo_ICMS;
  }
  public double getVL_Base_Calculo_ICMS_Substituicao() {
    return VL_Base_Calculo_ICMS_Substituicao;
  }
  public void setVL_Base_Calculo_ICMS(double VL_Base_Calculo_ICMS) {
    this.VL_Base_Calculo_ICMS = VL_Base_Calculo_ICMS;
  }
  public void setVL_Base_Calculo_ICMS_Substituicao(double VL_Base_Calculo_ICMS_Substituicao) {
    this.VL_Base_Calculo_ICMS_Substituicao = VL_Base_Calculo_ICMS_Substituicao;
  }
  public double getVL_ICMS_Substituicao() {
    return VL_ICMS_Substituicao;
  }
  public void setVL_ICMS_Substituicao(double VL_ICMS_Substituicao) {
    this.VL_ICMS_Substituicao = VL_ICMS_Substituicao;
  }
  public double getVL_Total_Produtos() {
    return VL_Total_Produtos;
  }
  public void setVL_Total_Produtos(double VL_Total_Produtos) {
    this.VL_Total_Produtos = VL_Total_Produtos;
  }
  
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  
  public void setNM_Unidade(String NM_Unidade) {
    this.NM_Unidade = NM_Unidade;
  }
  public String getNM_Unidade() {
    return NM_Unidade;
  }
 
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }  

  public void setVL_PIS(double VL_PIS) {
    this.VL_PIS = VL_PIS;
  }
  public double getVL_PIS() {
    return VL_PIS;
  }
  public void setVL_CSLL(double VL_CSLL) {
    this.VL_CSLL = VL_CSLL;
  }
  public double getVL_CSLL() {
    return VL_CSLL;
  }
  public void setVL_Cofins(double VL_Cofins) {
    this.VL_Cofins = VL_Cofins;
  }
  public double getVL_Cofins() {
    return VL_Cofins;
  }
  
  public void setDM_Exportada(String DM_Exportada) {
    this.DM_Exportada = DM_Exportada;
  }
  public String getDM_Exportada() {
    return DM_Exportada;
  }
  
  public void setDM_Picking(String dm_Picking) {
    this.DM_Picking = dm_Picking;
  }
  
  public String getDM_Picking() {
    return DM_Picking;
  }

	public String getDm_calcCOFINS() {
		return dm_calcCOFINS;
	}
	
	public String getDm_calcINSS() {
		return dm_calcINSS;
	}
	
	public String getDm_calcPIS() {
		return dm_calcPIS;
	}
	
	public void setDm_calcCOFINS(String string) {
		dm_calcCOFINS = string;
	}
	
	public void setDm_calcINSS(String string) {
		dm_calcINSS = string;
	}
	
	public void setDm_calcPIS(String string) {
		dm_calcPIS = string;
	}


public String getIsCalcCOFINS() {
	return isCalcCOFINS;
}


public String getIsCalcINSS() {
	return isCalcINSS;
}


public String getIsCalcPIS() {
	return isCalcPIS;
}


public void setIsCalcCOFINS(String string) {
	isCalcCOFINS = string;
}


public void setIsCalcINSS(String string) {
	isCalcINSS = string;
}


public void setIsCalcPIS(String string) {
	isCalcPIS = string;
}

public String getDt_stamp() {
	return dt_stamp;
}

public void setDt_stamp(String dt_stamp) {
	this.dt_stamp = dt_stamp;
}

public int getNr_itens() {
	return nr_itens;
}

public void setNr_itens(int nr_itens) {
	this.nr_itens = nr_itens;
}

public String getVL_Base_Calculo_ICMS_TX() {
	return VL_Base_Calculo_ICMS_TX;
}

public void setVL_Base_Calculo_ICMS_TX(String VL_base_Calculo_ICMS_TX) {
	VL_Base_Calculo_ICMS_TX = VL_base_Calculo_ICMS_TX;
	this.setVL_Base_Calculo_ICMS(Valores.converteStringToDouble(VL_base_Calculo_ICMS_TX));
}

public String getVl_icms_TX() {
	return vl_icms_TX;
}

public void setVl_icms_TX(String vl_icms_TX) {
	this.vl_icms_TX = vl_icms_TX;
	this.setVl_icms(Valores.converteStringToDouble(vl_icms_TX));
}

public String getVL_Base_Calculo_ICMS_Substituicao_TX() {
	return VL_Base_Calculo_ICMS_Substituicao_TX;
}

public void setVL_Base_Calculo_ICMS_Substituicao_TX(String VL_Base_Calculo_ICMS_Substituicao_TX) {
	this.VL_Base_Calculo_ICMS_Substituicao_TX = VL_Base_Calculo_ICMS_Substituicao_TX;
	this.setVL_Base_Calculo_ICMS_Substituicao(Valores.converteStringToDouble(VL_Base_Calculo_ICMS_Substituicao_TX));
}

public String getVL_ICMS_Substituicao_TX() {
	return VL_ICMS_Substituicao_TX;
}

public void setVL_ICMS_Substituicao_TX(String VL_ICMS_Substituicao_TX) {
	this.VL_ICMS_Substituicao_TX = VL_ICMS_Substituicao_TX;
	this.setVL_ICMS_Substituicao(Valores.converteStringToDouble(VL_ICMS_Substituicao_TX));
}

public String getVL_Total_Produtos_TX() {
	return VL_Total_Produtos_TX;
}

public void setVL_Total_Produtos_TX(String VL_Total_Produtos_TX) {
	this.VL_Total_Produtos_TX = VL_Total_Produtos_TX;
	this.setVL_Total_Produtos(Valores.converteStringToDouble(VL_Total_Produtos_TX));
}

public String getVl_total_frete_TX() {
	return vl_total_frete_TX;
}

public void setVl_total_frete_TX(String vl_total_frete_TX) {
	this.vl_total_frete_TX = vl_total_frete_TX;
	this.setVl_total_frete(Valores.converteStringToDouble(vl_total_frete_TX));
}

public String getVl_total_seguro_TX() {
	return vl_total_seguro_TX;
}

public void setVl_total_seguro_TX(String vl_total_seguro_TX) {
	this.vl_total_seguro_TX = vl_total_seguro_TX;
	this.setVl_total_seguro(Valores.converteStringToDouble(vl_total_seguro_TX));
}

public String getVl_ipi_TX() {
	return vl_ipi_TX;
}

public void setVl_ipi_TX(String vl_ipi_TX) {
	this.vl_ipi_TX = vl_ipi_TX;
	this.setVl_ipi(Valores.converteStringToDouble(vl_ipi_TX));
}

public String getVl_liquido_nota_fiscal_TX() {
	return vl_liquido_nota_fiscal_TX;
}

public void setVl_liquido_nota_fiscal_TX(String vl_liquido_nota_fiscal_TX) {
	this.vl_liquido_nota_fiscal_TX = vl_liquido_nota_fiscal_TX;
	this.setVl_liquido_nota_fiscal(Valores.converteStringToDouble(vl_liquido_nota_fiscal_TX));
}

public String getVl_total_despesas_TX() {
	return vl_total_despesas_TX;
}

public void setVl_total_despesas_TX(String vl_total_despesas_TX) {
	this.vl_total_despesas_TX = vl_total_despesas_TX;
	this.setVl_total_despesas(Valores.converteStringToDouble(vl_total_despesas_TX));
}

public String getOid_cliente() {
	return oid_cliente;
}

public void setOid_cliente(String oid_cliente) {
	this.oid_cliente = oid_cliente;
}

public String getNm_razao_social_cliente() {
	return nm_razao_social_cliente;
}

public void setNm_razao_social_cliente(String nm_razao_social_cliente) {
	this.nm_razao_social_cliente = nm_razao_social_cliente;
}

public String getCd_Natureza_Operacao() {
	return cd_Natureza_Operacao;
}

public void setCd_Natureza_Operacao(String cd_Natureza_Operacao) {
	this.cd_Natureza_Operacao = cd_Natureza_Operacao;
}

public String getNm_Natureza_Operacao() {
	return nm_Natureza_Operacao;
}

public void setNm_Natureza_Operacao(String nm_Natureza_Operacao) {
	this.nm_Natureza_Operacao = nm_Natureza_Operacao;
}

public String getNm_Fantasia() {
	return nm_Fantasia;
}

public void setNm_Fantasia(String nm_Fantasia) {
	this.nm_Fantasia = nm_Fantasia;
}




}