package com.master.ed;

public class DespesaED extends MasterED {


    private Integer oid_Despesa;
    private Integer oid_Compromisso;
    private String nr_Documento;
    private Integer nr_Parcela;
    private String dt_Emissao;
    private String dt_Vencimento;
    private Double vl_Despesa;
    private Double vl_Multa_Apos_Vencimento;
    private Double vl_Juro_Mora_dia;
    private String tx_Observacao;
    private Integer nr_Despesa;
    private Double vl_Desconto_Ate_Vencimento;
    private Double vl_Compromisso;
    private Integer oid_Tipo_Documento;
    private Integer oid_Conta;
    private Integer oid_Centro_Custo;
    private String oid_Pessoa;
    private Long oid_Unidade;
    private String nr_CNPJ_CPF;
    private String nm_Razao_Social;
    private String cd_Tipo_Documento;
    private String nm_Tipo_Documento;
    private String cd_Centro_Custo;
    private String nm_Centro_Custo;
    private String cd_Conta;
    private String nm_Conta;
    private String cd_Unidade;
    private String nm_Fantasia;
    private String dm_Numeracao_Automatica;
    private String nr_Proximo_Numero;
    private String OID_Ordem_Frete;
    private Integer OID_Movimento_Ordem_Servico;
    private String DM_Lote_Pagamento;
    private Double vl_Imposto;
    private Double pe_Imposto;
    private Integer oid_Natureza_Operacao;
    private String dt_Entrada;
    private String cd_Natureza_Operacao;
    private String nm_Natureza_Operacao;
    private Integer oid_Despesa_Vinculado;
    private String DM_Vinculador;
    private Double VL_Despesas_Vinculados;
    private String oid_pessoa_Vinculado;
    private String nr_cnpj_cpf_Vinculado;
    private String nm_razao_Social_Vinculado;
    private String cd_tipo_documento_Vinculado;
    private String nm_tipo_documento_Vinculado;
    private Integer nr_Despesa_Vinculado;
    private Double vl_Compromisso_Vinculado;
    private Long nr_documento_Vinculado;
    private Integer nr_parcela_Vinculado;
    private String Dt_Inicial;
    private String Dt_Final;
    private String dt_Stamp;
    private Integer oid_Despesa_Parcela;
    private String cd_Conta_Credito;
    private String nm_Conta_Credito;
    private Integer oid_Conta_Credito;
    private Double vl_Taxa_Banco;
    private String DM_Tipo_Documento;
    private String DM_Tipo_Pagamento;
    private String CD_Barra;

    ///### Transito 12062003
    private Integer NR_Dia_Provisao;
    private Integer NR_Qtde_Provisao;
    private String DM_Situacao;
    private String DM_Debito_Credito;
    private Double VL_Despesa_Original;
    private String dt_Vencimento_Inicial;
    private String dt_Vencimento_Final;
    private String DM_Relatorio;
    private String dt_Pagamento_Inicial;
    private String dt_Pagamento_Final;

    public DespesaED() {
    }

    public String getDt_Emissao() {
        return dt_Emissao;
    }

    public String getDt_Vencimento() {
        return dt_Vencimento;
    }

    public Integer getNr_Despesa() {
        return nr_Despesa;
    }

    public String getNr_Documento() {
        return nr_Documento;
    }

    public Integer getNr_Parcela() {
        return nr_Parcela;
    }

    public Integer getOid_Centro_Custo() {
        return oid_Centro_Custo;
    }

    public Integer getOid_Despesa() {
        return oid_Despesa;
    }

    public Integer getOid_Conta() {
        return oid_Conta;
    }

    public String getOid_Pessoa() {
        return oid_Pessoa;
    }

    public Integer getOid_Tipo_Documento() {
        return oid_Tipo_Documento;
    }

    public Long getOid_Unidade() {
        return oid_Unidade;
    }

    public String getTx_Observacao() {
        return tx_Observacao;
    }

    public Double getVl_Despesa() {
        return vl_Despesa;
    }

    public Double getVl_Desconto_Ate_Vencimento() {
        return vl_Desconto_Ate_Vencimento;
    }

    public Double getVl_Juro_Mora_dia() {
        return vl_Juro_Mora_dia;
    }

    public Double getVl_Multa_Apos_Vencimento() {
        return vl_Multa_Apos_Vencimento;
    }

    public Double getVl_Compromisso() {
        return vl_Compromisso;
    }

    public void setVl_Compromisso(Double vl_Compromisso) {
        this.vl_Compromisso = vl_Compromisso;
    }

    public void setVl_Multa_Apos_Vencimento(Double vl_Multa_Apos_Vencimento) {
        this.vl_Multa_Apos_Vencimento = vl_Multa_Apos_Vencimento;
    }

    public void setVl_Juro_Mora_dia(Double vl_Juro_Mora_dia) {
        this.vl_Juro_Mora_dia = vl_Juro_Mora_dia;
    }

    public void setVl_Desconto_Ate_Vencimento(Double vl_Desconto_Ate_Vencimento) {
        this.vl_Desconto_Ate_Vencimento = vl_Desconto_Ate_Vencimento;
    }

    public void setVl_Despesa(Double vl_Despesa) {
        this.vl_Despesa = vl_Despesa;
    }

    public void setTx_Observacao(String tx_Observacao) {
        this.tx_Observacao = tx_Observacao;
    }

    public void setOid_Unidade(Long oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }

    public void setOid_Tipo_Documento(Integer oid_Tipo_Documento) {
        this.oid_Tipo_Documento = oid_Tipo_Documento;
    }

    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }

    public void setOid_Conta(Integer oid_Conta) {
        this.oid_Conta = oid_Conta;
    }

    public void setOid_Despesa(Integer oid_Despesa) {
        this.oid_Despesa = oid_Despesa;
    }

    public void setOid_Centro_Custo(Integer oid_Centro_Custo) {
        this.oid_Centro_Custo = oid_Centro_Custo;
    }

    public void setNr_Parcela(Integer nr_Parcela) {
        this.nr_Parcela = nr_Parcela;
    }

    public void setNr_Documento(String nr_Documento) {
        this.nr_Documento = nr_Documento;
    }

    public void setNr_Despesa(Integer nr_Despesa) {
        this.nr_Despesa = nr_Despesa;
    }

    public void setDt_Vencimento(String dt_Vencimento) {
        this.dt_Vencimento = dt_Vencimento;
    }

    public void setDt_Emissao(String dt_Emissao) {
        this.dt_Emissao = dt_Emissao;
    }

    public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
        this.nr_CNPJ_CPF = nr_CNPJ_CPF;
    }

    public String getNr_CNPJ_CPF() {
        return nr_CNPJ_CPF;
    }

    public void setNm_Razao_Social(String nm_Razao_Social) {
        this.nm_Razao_Social = nm_Razao_Social;
    }

    public String getNm_Razao_Social() {
        return nm_Razao_Social;
    }

    public void setCd_Tipo_Documento(String cd_Tipo_Documento) {
        this.cd_Tipo_Documento = cd_Tipo_Documento;
    }

    public String getCd_Tipo_Documento() {
        return cd_Tipo_Documento;
    }

    public void setNm_Tipo_Documento(String nm_Tipo_Documento) {
        this.nm_Tipo_Documento = nm_Tipo_Documento;
    }

    public String getNm_Tipo_Documento() {
        return nm_Tipo_Documento;
    }

    public void setCd_Centro_Custo(String cd_Centro_Custo) {
        this.cd_Centro_Custo = cd_Centro_Custo;
    }

    public String getCd_Centro_Custo() {
        return cd_Centro_Custo;
    }

    public void setNm_Centro_Custo(String nm_Centro_Custo) {
        this.nm_Centro_Custo = nm_Centro_Custo;
    }

    public String getNm_Centro_Custo() {
        return nm_Centro_Custo;
    }

    public void setCd_Conta(String cd_Conta) {
        this.cd_Conta = cd_Conta;
    }

    public String getCd_Conta() {
        return cd_Conta;
    }

    public void setNm_Conta(String nm_Conta) {
        this.nm_Conta = nm_Conta;
    }

    public String getNm_Conta() {
        return nm_Conta;
    }

    public void setCd_Unidade(String cd_Unidade) {
        this.cd_Unidade = cd_Unidade;
    }

    public String getCd_Unidade() {
        return cd_Unidade;
    }

    public void setNm_Fantasia(String nm_Fantasia) {
        this.nm_Fantasia = nm_Fantasia;
    }

    public String getNm_Fantasia() {
        return nm_Fantasia;
    }

    public void setDm_Numeracao_Automatica(String dm_Numeracao_Automatica) {
        this.dm_Numeracao_Automatica = dm_Numeracao_Automatica;
    }

    public String getDm_Numeracao_Automatica() {
        return dm_Numeracao_Automatica;
    }

    public void setNr_Proximo_Numero(String nr_Proximo_Numero) {
        this.nr_Proximo_Numero = nr_Proximo_Numero;
    }

    public String getNr_Proximo_Numero() {
        return nr_Proximo_Numero;
    }

    public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
        this.OID_Ordem_Frete = OID_Ordem_Frete;
    }

    public String getOID_Ordem_Frete() {
        return OID_Ordem_Frete;
    }

    public void setOID_Movimento_Ordem_Servico(Integer OID_Movimento_Ordem_Servico) {
        this.OID_Movimento_Ordem_Servico = OID_Movimento_Ordem_Servico;
    }

    public Integer getOID_Movimento_Ordem_Servico() {
        return OID_Movimento_Ordem_Servico;
    }

    public void setDM_Lote_Pagamento(String DM_Lote_Pagamento) {
        this.DM_Lote_Pagamento = DM_Lote_Pagamento;
    }

    public String getDM_Lote_Pagamento() {
        return DM_Lote_Pagamento;
    }

    public void setVl_Imposto(Double vl_Imposto) {
        this.vl_Imposto = vl_Imposto;
    }

    public Double getVl_Imposto() {
        return vl_Imposto;
    }

    public void setPe_Imposto(Double pe_Imposto) {
        this.pe_Imposto = pe_Imposto;
    }

    public Double getPe_Imposto() {
        return pe_Imposto;
    }

    public void setOid_Natureza_Operacao(Integer oid_Natureza_Operacao) {
        this.oid_Natureza_Operacao = oid_Natureza_Operacao;
    }

    public Integer getOid_Natureza_Operacao() {
        return oid_Natureza_Operacao;
    }

    public void setDt_Entrada(String dt_Entrada) {
        this.dt_Entrada = dt_Entrada;
    }

    public String getDt_Entrada() {
        return dt_Entrada;
    }

    public void setCd_Natureza_Operacao(String cd_Natureza_Operacao) {
        this.cd_Natureza_Operacao = cd_Natureza_Operacao;
    }

    public String getCd_Natureza_Operacao() {
        return cd_Natureza_Operacao;
    }

    public void setNm_Natureza_Operacao(String nm_Natureza_Operacao) {
        this.nm_Natureza_Operacao = nm_Natureza_Operacao;
    }

    public String getNm_Natureza_Operacao() {
        return nm_Natureza_Operacao;
    }

    public void setOid_Despesa_Vinculado(Integer oid_Despesa_Vinculado) {
        this.oid_Despesa_Vinculado = oid_Despesa_Vinculado;
    }

    public Integer getOid_Despesa_Vinculado() {
        return oid_Despesa_Vinculado;
    }

    public void setDM_Vinculador(String DM_Vinculador) {
        this.DM_Vinculador = DM_Vinculador;
    }

    public String getDM_Vinculador() {
        return DM_Vinculador;
    }

    public void setVL_Despesas_Vinculados(Double VL_Despesas_Vinculados) {
        this.VL_Despesas_Vinculados = VL_Despesas_Vinculados;
    }

    public Double getVL_Despesas_Vinculados() {
        return VL_Despesas_Vinculados;
    }

    public void setOid_pessoa_Vinculado(String oid_pessoa_Vinculado) {
        this.oid_pessoa_Vinculado = oid_pessoa_Vinculado;
    }

    public String getOid_pessoa_Vinculado() {
        return oid_pessoa_Vinculado;
    }

    public void setNr_cnpj_cpf_Vinculado(String nr_cnpj_cpf_Vinculado) {
        this.nr_cnpj_cpf_Vinculado = nr_cnpj_cpf_Vinculado;
    }

    public String getNr_cnpj_cpf_Vinculado() {
        return nr_cnpj_cpf_Vinculado;
    }

    public void setNm_razao_Social_Vinculado(String nm_razao_Social_Vinculado) {
        this.nm_razao_Social_Vinculado = nm_razao_Social_Vinculado;
    }

    public String getNm_razao_Social_Vinculado() {
        return nm_razao_Social_Vinculado;
    }

    public void setCd_tipo_documento_Vinculado(String cd_tipo_documento_Vinculado) {
        this.cd_tipo_documento_Vinculado = cd_tipo_documento_Vinculado;
    }

    public String getCd_tipo_documento_Vinculado() {
        return cd_tipo_documento_Vinculado;
    }

    public void setNm_tipo_documento_Vinculado(String nm_tipo_documento_Vinculado) {
        this.nm_tipo_documento_Vinculado = nm_tipo_documento_Vinculado;
    }

    public String getNm_tipo_documento_Vinculado() {
        return nm_tipo_documento_Vinculado;
    }

    public void setNr_Despesa_Vinculado(Integer nr_Despesa_Vinculado) {
        this.nr_Despesa_Vinculado = nr_Despesa_Vinculado;
    }

    public Integer getNr_Despesa_Vinculado() {
        return nr_Despesa_Vinculado;
    }

    public void setVl_Compromisso_Vinculado(Double vl_Compromisso_Vinculado) {
        this.vl_Compromisso_Vinculado = vl_Compromisso_Vinculado;
    }

    public Double getVl_Compromisso_Vinculado() {
        return vl_Compromisso_Vinculado;
    }

    public void setNr_documento_Vinculado(Long nr_documento_Vinculado) {
        this.nr_documento_Vinculado = nr_documento_Vinculado;
    }

    public Long getNr_documento_Vinculado() {
        return nr_documento_Vinculado;
    }

    public void setNr_parcela_Vinculado(Integer nr_parcela_Vinculado) {
        this.nr_parcela_Vinculado = nr_parcela_Vinculado;
    }

    public Integer getNr_parcela_Vinculado() {
        return nr_parcela_Vinculado;
    }

    public void setDt_Inicial(String Dt_Inicial) {
        this.Dt_Inicial = Dt_Inicial;
    }

    public String getDt_Inicial() {
        return Dt_Inicial;
    }

    public void setDt_Final(String Dt_Final) {
        this.Dt_Final = Dt_Final;
    }

    public String getDt_Final() {
        return Dt_Final;
    }

    public void setDt_Stamp(String dt_Stamp) {
        this.dt_Stamp = dt_Stamp;
    }

    public String getDt_Stamp() {
        return dt_Stamp;
    }

    public void setOid_Despesa_Parcela(Integer oid_Despesa_Parcela) {
        this.oid_Despesa_Parcela = oid_Despesa_Parcela;
    }

    public Integer getOid_Despesa_Parcela() {
        return oid_Despesa_Parcela;
    }

    public void setCd_Conta_Credito(String cd_Conta_Credito) {
        this.cd_Conta_Credito = cd_Conta_Credito;
    }

    public String getCd_Conta_Credito() {
        return cd_Conta_Credito;
    }

    public void setNm_Conta_Credito(String nm_Conta_Credito) {
        this.nm_Conta_Credito = nm_Conta_Credito;
    }

    public String getNm_Conta_Credito() {
        return nm_Conta_Credito;
    }

    public void setOid_Conta_Credito(Integer oid_Conta_Credito) {
        this.oid_Conta_Credito = oid_Conta_Credito;
    }

    public Integer getOid_Conta_Credito() {
        return oid_Conta_Credito;
    }

    public void setVl_Taxa_Banco(Double vl_Taxa_Banco) {
        this.vl_Taxa_Banco = vl_Taxa_Banco;
    }

    public Double getVl_Taxa_Banco() {
        return vl_Taxa_Banco;
    }

    public void setDM_Tipo_Documento(String DM_Tipo_Documento) {
        this.DM_Tipo_Documento = DM_Tipo_Documento;
    }

    public String getDM_Tipo_Documento() {
        return DM_Tipo_Documento;
    }

    public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
        this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
    }

    public String getDM_Tipo_Pagamento() {
        return DM_Tipo_Pagamento;
    }

    public void setNR_Dia_Provisao(Integer NR_Dia_Provisao) {
        this.NR_Dia_Provisao = NR_Dia_Provisao;
    }

    public Integer getNR_Dia_Provisao() {
        return NR_Dia_Provisao;
    }

    public void setNR_Qtde_Provisao(Integer NR_Qtde_Provisao) {
        this.NR_Qtde_Provisao = NR_Qtde_Provisao;
    }

    public Integer getNR_Qtde_Provisao() {
        return NR_Qtde_Provisao;
    }

    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }

    public String getDM_Situacao() {
        return DM_Situacao;
    }

    public void setDM_Debito_Credito(String DM_Debito_Credito) {
        this.DM_Debito_Credito = DM_Debito_Credito;
    }

    public String getDM_Debito_Credito() {
        return DM_Debito_Credito;
    }

    public void setVL_Despesa_Original(Double VL_Despesa_Original) {
        this.VL_Despesa_Original = VL_Despesa_Original;
    }

    public Double getVL_Despesa_Original() {
        return VL_Despesa_Original;
    }

    public void setDt_Vencimento_Inicial(String dt_Vencimento_Inicial) {
        this.dt_Vencimento_Inicial = dt_Vencimento_Inicial;
    }

    public String getDt_Vencimento_Inicial() {
        return dt_Vencimento_Inicial;
    }

    public void setDt_Vencimento_Final(String dt_Vencimento_Final) {
        this.dt_Vencimento_Final = dt_Vencimento_Final;
    }

    public String getDt_Vencimento_Final() {
        return dt_Vencimento_Final;
    }

    public void setDM_Relatorio(String DM_Relatorio) {
        this.DM_Relatorio = DM_Relatorio;
    }

    public String getDM_Relatorio() {
        return DM_Relatorio;
    }

    public void setDt_Pagamento_Inicial(String dt_Pagamento_Inicial) {
        this.dt_Pagamento_Inicial = dt_Pagamento_Inicial;
    }

    public String getDt_Pagamento_Inicial() {
        return dt_Pagamento_Inicial;
    }

    public void setDt_Pagamento_Final(String dt_Pagamento_Final) {
        this.dt_Pagamento_Final = dt_Pagamento_Final;
    }

  public void setOid_Compromisso (Integer oid_Compromisso) {
    this.oid_Compromisso = oid_Compromisso;
  }

  public String getDt_Pagamento_Final() {
        return dt_Pagamento_Final;
    }

  public Integer getOid_Compromisso () {
    return oid_Compromisso;
  }

  public String getCD_Barra() {
        return CD_Barra;
    }
    public void setCD_Barra(String barra) {
        CD_Barra = barra;
    }
}
