package com.master.ed;

import javax.servlet.http.HttpServletResponse;

public class DepreciacaoED extends RelatorioBaseED {

    public DepreciacaoED() {
        super();
  }
    public DepreciacaoED(HttpServletResponse response,
            String nomeRelatorio) {
        super(response, nomeRelatorio);
    }
    public DepreciacaoED(String oid_Depreciacao) {
        this.oid_Depreciacao = oid_Depreciacao;
    }

  private String oid_Depreciacao;
  private String oid_Depreciacao_Mes;
  private String nm_Depreciacao;
  private Integer oid_Produto_Patrimonio;
  private String nm_Produto_Patrimonio;
  private String cd_Produto_Patrimonio;

  private String oid_Categoria;
  private String nm_Categoria;

  private Integer oid_Unidade;
  private String nm_Unidade;
  private String cd_Unidade;
  private long nr_nota_fiscal;
  private long nr_Anos;
  private long nr_Meses;
  private long nr_quantidade;
  private long nr_parcela;
  private Double pe_Fator_Anual;

  private double vl_original;
  private double vl_depreciacao_mes;
  private double vl_depreciacao_contabilizada;
  private double vl_depreciacao_pendente;

  private double vl_depreciacao_vendida;
  private long nr_meses_vendido;

  private long nr_parcela_pendente;
  private long nr_parcela_contabilizada;

  private String dt_aquisicao;
  private String dt_aquisicao_inicial;
  private String dt_aquisicao_final;
  private String dt_emissao_inicial;
  private String dt_emissao_final;
  private String dt_inicio_depreciacao;
  private String dt_inicio_depreciacao_inicial;
  private String dt_inicio_depreciacao_final;
  private String dt_depreciacao_mes;
  private String dm_situacao;
  private String oid_pessoa;
  private String nm_razao_social;
  private String nr_cnpj_cpf;

  private String cd_conta_devedora;
  private String nm_conta_devedora;
  private String cd_conta_credora;
  private String nm_conta_credora;

  private double vl_total_depreciacao;
  private double vl_venda;
  private double vl_ganho_perda;
  private String dt_venda;
  private String tx_observacao;
  private String tx_observacao_depreciacao;
  private long oid_conta_credora;
  private long oid_conta_devedora;

public String getCd_Produto_Patrimonio() {
	return cd_Produto_Patrimonio;
}
public void setCd_Produto_Patrimonio(String cd_Produto_Patrimonio) {
	this.cd_Produto_Patrimonio = cd_Produto_Patrimonio;
}
public String getNm_Depreciacao() {
	return nm_Depreciacao;
}
public void setNm_Depreciacao(String nm_Depreciacao) {
	this.nm_Depreciacao = nm_Depreciacao;
}
public String getNm_Produto_Patrimonio() {
	return nm_Produto_Patrimonio;
}
public void setNm_Produto_Patrimonio(String nm_Produto_Patrimonio) {
	this.nm_Produto_Patrimonio = nm_Produto_Patrimonio;
}
public String getOid_Depreciacao() {
	return oid_Depreciacao;
}
public void setOid_Depreciacao(String oid_Depreciacao) {
	this.oid_Depreciacao = oid_Depreciacao;
}
public Integer getOid_Produto_Patrimonio() {
	return oid_Produto_Patrimonio;
}
public void setOid_Produto_Patrimonio(Integer oid_Produto_Patrimonio) {
	this.oid_Produto_Patrimonio = oid_Produto_Patrimonio;
}
public String getCd_Unidade() {
	return cd_Unidade;
}
public void setCd_Unidade(String cd_Unidade) {
	this.cd_Unidade = cd_Unidade;
}
public String getNm_Unidade() {
	return nm_Unidade;
}
public void setNm_Unidade(String nm_Unidade) {
	this.nm_Unidade = nm_Unidade;
}
public Integer getOid_Unidade() {
	return oid_Unidade;
}
public void setOid_Unidade(Integer oid_Unidade) {
	this.oid_Unidade = oid_Unidade;
}
public String getDm_situacao() {
	return dm_situacao;
}
public void setDm_situacao(String dm_situacao) {
	this.dm_situacao = dm_situacao;
}
public String getDt_aquisicao() {
	return dt_aquisicao;
}
public void setDt_aquisicao(String dt_aquisicao) {
	this.dt_aquisicao = dt_aquisicao;
}
public String getDt_inicio_depreciacao() {
	return dt_inicio_depreciacao;
}
public void setDt_inicio_depreciacao(String dt_inicio_depreciacao) {
	this.dt_inicio_depreciacao = dt_inicio_depreciacao;
}
public long getNr_nota_fiscal() {
	return nr_nota_fiscal;
}
public void setNr_nota_fiscal(long nr_nota_fiscal) {
	this.nr_nota_fiscal = nr_nota_fiscal;
}
public long getNr_quantidade() {
	return nr_quantidade;
}
public void setNr_quantidade(long nr_quantidade) {
	this.nr_quantidade = nr_quantidade;
}
public double getVl_original() {
	return vl_original;
}
public void setVl_original(double vl_original) {
	this.vl_original = vl_original;
}
public String getNm_razao_social() {
	return nm_razao_social;
}
public void setNm_razao_social(String nm_razao_social) {
	this.nm_razao_social = nm_razao_social;
}
public String getNr_cnpj_cpf() {
	return nr_cnpj_cpf;
}
public void setNr_cnpj_cpf(String nr_cnpj_cpf) {
	this.nr_cnpj_cpf = nr_cnpj_cpf;
}
public String getOid_pessoa() {
	return oid_pessoa;
}
public void setOid_pessoa(String oid_pessoa) {
	this.oid_pessoa = oid_pessoa;
}
public String getNm_Categoria() {
	return nm_Categoria;
}
public void setNm_Categoria(String nm_Categoria) {
	this.nm_Categoria = nm_Categoria;
}
public long getNr_Anos() {
	return nr_Anos;
}
public void setNr_Anos(long nr_Anos) {
	this.nr_Anos = nr_Anos;
}
public long getNr_Meses() {
	return nr_Meses;
}
public void setNr_Meses(long nr_Meses) {
	this.nr_Meses = nr_Meses;
}
public double getVl_depreciacao_mes() {
	return vl_depreciacao_mes;
}
public void setVl_depreciacao_mes(double vl_depreciacao_mes) {
	this.vl_depreciacao_mes = vl_depreciacao_mes;
}
public Double getPe_Fator_Anual() {
	return pe_Fator_Anual;
}
public void setPe_Fator_Anual(Double pe_Fator_Anual) {
	this.pe_Fator_Anual = pe_Fator_Anual;
}
public String getOid_Categoria() {
	return oid_Categoria;
}
public void setOid_Categoria(String oid_Categoria) {
	this.oid_Categoria = oid_Categoria;
}
public String getOid_Depreciacao_Mes() {
	return oid_Depreciacao_Mes;
}
public void setOid_Depreciacao_Mes(String oid_Depreciacao_Mes) {
	this.oid_Depreciacao_Mes = oid_Depreciacao_Mes;
}
public String getDt_depreciacao_mes() {
	return dt_depreciacao_mes;
}
public void setDt_depreciacao_mes(String dt_depreciacao_mes) {
	this.dt_depreciacao_mes = dt_depreciacao_mes;
}
public long getNr_parcela() {
	return nr_parcela;
}
public void setNr_parcela(long nr_parcela) {
	this.nr_parcela = nr_parcela;
}
public String getDt_emissao_final() {
	return dt_emissao_final;
}
public void setDt_emissao_final(String dt_emissao_final) {
	this.dt_emissao_final = dt_emissao_final;
}
public String getDt_emissao_inicial() {
	return dt_emissao_inicial;
}
public void setDt_emissao_inicial(String dt_emissao_inicial) {
	this.dt_emissao_inicial = dt_emissao_inicial;
}
public long getNr_parcela_contabilizada() {
	return nr_parcela_contabilizada;
}
public void setNr_parcela_contabilizada(long nr_parcela_contabilizada) {
	this.nr_parcela_contabilizada = nr_parcela_contabilizada;
}
public long getNr_parcela_pendente() {
	return nr_parcela_pendente;
}
public void setNr_parcela_pendente(long nr_parcela_pendente) {
	this.nr_parcela_pendente = nr_parcela_pendente;
}
public double getVl_depreciacao_contabilizada() {
	return vl_depreciacao_contabilizada;
}
public void setVl_depreciacao_contabilizada(double vl_depreciacao_contabilizada) {
	this.vl_depreciacao_contabilizada = vl_depreciacao_contabilizada;
}
public double getVl_depreciacao_pendente() {
	return vl_depreciacao_pendente;
}
public void setVl_depreciacao_pendente(double vl_depreciacao_pendente) {
	this.vl_depreciacao_pendente = vl_depreciacao_pendente;
}
public String getCd_conta_credora() {
	return cd_conta_credora;
}
public void setCd_conta_credora(String cd_conta_credora) {
	this.cd_conta_credora = cd_conta_credora;
}
public String getCd_conta_devedora() {
	return cd_conta_devedora;
}
public void setCd_conta_devedora(String cd_conta_devedora) {
	this.cd_conta_devedora = cd_conta_devedora;
}
public String getNm_conta_credora() {
	return nm_conta_credora;
}
public void setNm_conta_credora(String nm_conta_credora) {
	this.nm_conta_credora = nm_conta_credora;
}
public String getNm_conta_devedora() {
	return nm_conta_devedora;
}
public void setNm_conta_devedora(String nm_conta_devedora) {
	this.nm_conta_devedora = nm_conta_devedora;
}
public String getDt_venda() {
	return dt_venda;
}
public void setDt_venda(String dt_venda) {
	this.dt_venda = dt_venda;
}
public long getOid_conta_credora() {
	return oid_conta_credora;
}
public void setOid_conta_credora(long oid_conta_credora) {
	this.oid_conta_credora = oid_conta_credora;
}
public long getOid_conta_devedora() {
	return oid_conta_devedora;
}
public void setOid_conta_devedora(long oid_conta_devedora) {
	this.oid_conta_devedora = oid_conta_devedora;
}
public String getTx_observacao() {
	return tx_observacao;
}
public void setTx_observacao(String tx_observacao) {
	this.tx_observacao = tx_observacao;
}
public double getVl_ganho_perda() {
	return vl_ganho_perda;
}
public void setVl_ganho_perda(double vl_ganho_perda) {
	this.vl_ganho_perda = vl_ganho_perda;
}
public double getVl_total_depreciacao() {
	return vl_total_depreciacao;
}
public void setVl_total_depreciacao(double vl_total_depreciacao) {
	this.vl_total_depreciacao = vl_total_depreciacao;
}
public double getVl_venda() {
	return vl_venda;
}
public void setVl_venda(double vl_venda) {
	this.vl_venda = vl_venda;
}
public long getNr_meses_vendido() {
	return nr_meses_vendido;
}
public void setNr_meses_vendido(long nr_meses_vendido) {
	this.nr_meses_vendido = nr_meses_vendido;
}
public double getVl_depreciacao_vendida() {
	return vl_depreciacao_vendida;
}
public void setVl_depreciacao_vendida(double vl_depreciacao_vendida) {
	this.vl_depreciacao_vendida = vl_depreciacao_vendida;
}
public String getDt_aquisicao_final() {
	return dt_aquisicao_final;
}
public void setDt_aquisicao_final(String dt_aquisicao_final) {
	this.dt_aquisicao_final = dt_aquisicao_final;
}
public String getDt_aquisicao_inicial() {
	return dt_aquisicao_inicial;
}
public void setDt_aquisicao_inicial(String dt_aquisicao_inicial) {
	this.dt_aquisicao_inicial = dt_aquisicao_inicial;
}
public String getDt_inicio_depreciacao_final() {
	return dt_inicio_depreciacao_final;
}
public void setDt_inicio_depreciacao_final(String dt_inicio_depreciacao_final) {
	this.dt_inicio_depreciacao_final = dt_inicio_depreciacao_final;
}
public String getDt_inicio_depreciacao_inicial() {
	return dt_inicio_depreciacao_inicial;
}
public void setDt_inicio_depreciacao_inicial(
		String dt_inicio_depreciacao_inicial) {
	this.dt_inicio_depreciacao_inicial = dt_inicio_depreciacao_inicial;
}
public String getTx_observacao_depreciacao() {
	return tx_observacao_depreciacao;
}
public void setTx_observacao_depreciacao(String tx_observacao_depreciacao) {
	this.tx_observacao_depreciacao = tx_observacao_depreciacao;
}

}
