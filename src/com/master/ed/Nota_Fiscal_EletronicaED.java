package com.master.ed;

public class Nota_Fiscal_EletronicaED extends MasterED {

    public Nota_Fiscal_EletronicaED() {
        super();
    }

    public Nota_Fiscal_EletronicaED(String oid_nota_fiscal) {
        super();
        this.oid_nota_fiscal = oid_nota_fiscal;
    }

    public Modelo_Nota_FiscalED edModelo = new Modelo_Nota_FiscalED();

    private String oid_nota_fiscal;

    private long nr_nota_fiscal;
    private long nr_nota_fiscal_final;
    private String dt_emissao;
    private String DT_Entrega;
    private String DT_Entrega_Final;
    private double nr_volumes;
    private long nr_volumes_nota;
    private double NR_Peso;
    private double NR_Peso_Cubado;
    private String nm_serie;
    private String dt_entrada;
    private String dt_entrada_final;
    private String hr_entrada;
    private String oid_pessoa;
    private String oid_pessoa_destinatario;
    private String oid_Pessoa_Consignatario;
    private String oid_Pessoa_Redespacho;
    private String oid_pessoa_transportador;
    private long oid_produto;
    private long oid_natureza_operacao;
    private int oid_Natureza_Operacao_Servico;
    private long oid_modelo_nota_fiscal;
    private int oid_grupo_nota_fiscal;
    private String dm_tipo_nota_fiscal;
    private String usuario_stamp;
    private String dm_stamp;
    private String dm_observacao;
    private String DM_Frete;
    private String nm_complemento;
    private double vl_descontos;
    private double vl_icms;
    private double VL_ICMS_Subst;
    private double vl_inss;
    private double vl_ipi;
    private double vl_irrf;
    private double vl_isqn;
    private double vl_liquido_nota_fiscal;
    private double vl_nota_fiscal;
    private double vl_total_despesas;
    private double vl_total_frete;
    private double vl_total_seguro;
    private String dm_forma_pagamento;
    private long nr_parcelas;
    private String dm_finalizado;
    private long OID_Unidade_Fiscal;
    private long OID_Unidade_Contabil;
    private String NM_Unidade_Fiscal;
    private String NM_Unidade_Contabil;
    private String CD_Unidade_Fiscal;
    private String CD_Unidade_Contabil;
    private String DM_Contabiliza;
    private String DM_Estoque;
    private String DM_Financeiro;
    private double VL_Parcela_Calculo;
    private double VL_Servico;
    private double VL_Pis;
    private double VL_Cofins;
    private double VL_Csll;
    private long OID_Unidade;
    private String NM_Unidade;
    private String CD_Unidade;
    private String oid_pessoa_fornecedor;
    private String DM_Situacao;
    private double nr_Itens;
    private Integer oid_Centro_Custo;
    private String DM_Pendencia;
    private String NM_Pendencia;
    private String NM_Situacao;
    private long NR_Proximo_Lote;
    private String DT_Inicial;
    private String DT_Final;
    private String DM_Relatorio;
    private int oid_Tabela_Venda;
    private int oid_Condicao_Pagamento;
    private String CD_Condicao_Pagamento;
    private String NM_Condicao_Pagamento;
    private String oid_Conhecimento;
    private int oid_Conta;
    private String oid_Vendedor;
    private String oid_Veiculo;
    private int oid_Entregador;
    private String DM_Impresso;

    private String oid_Pedido;
    private String DM_Romaneio;

    private boolean AtualizarEstoque;
    private String DM_Tipo_Operacao;
    private String DM_Tipo_Devolucao;

    private int oid_Carga_Entrega;
    private int oid_Localizacao;
    private String oid_Nota_Fiscal_Devolucao;
    private String oid_Nota_Fiscal_Original;
    private double VL_Desconto_Itens;
    private double VL_Adicional;
    private double VL_Diferenca_Aliq_ICMS;
    private double VL_Base_Calculo_ICMS;
    private double VL_Base_Calculo_ICMS_Subst;
    private double PE_Aliquota_ICMS;
    private double vl_Comissao;
    private String dm_Devolvido;

    private boolean updatePrecosByCond;
    private boolean updatePrecosByTabela;
    private String tx_Observacao;

    private String oid_Manifesto;
    private String nr_Manifesto;
    private String nr_Conhecimento;

    private String dm_grupo_faturamento;
    private String oid_pagador;

    //DADOS NFE
    private String nfe_recibo;
    private String nfe_data_lote;
    private String nfe_cstat_lote;
    private String nfe_motivo_lote;

    private String nfe_chave_acesso;
    private String nfe_protocolo;
    private String nfe_digestvalue;
    private String nfe_cstat;
    private String nfe_motivo;
    private String nfe_dt_hr_recebimento;

    public String getTx_Observacao() {
  	return tx_Observacao;
  }
  public void setTx_Observacao(String tx_Observacao) {
  	this.tx_Observacao = tx_Observacao;
  }

    public String getDesc_DM_Situacao() {
        if ("C".equals(DM_Situacao))
            return "Cancelada";
        if ("A".equals(DM_Situacao) || "N".equals(DM_Situacao))
            return "Em Aberto";
        if ("F".equals(DM_Situacao))
            return "Finalizada";
        if ("D".equals(DM_Situacao))
            return "Devolvida";
        if ("X".equals(DM_Situacao))
            return "X";
        return "Inválida!";
    }

    public String getDesc_DM_Impresso() {
        if ("S".equals(this.DM_Impresso))
            return "Já Impresso";
        else return "Não Impresso";
    }

    public String getDescDM_Estoque() {
        if ("S".equals(this.DM_Estoque))
            return "Atualizado";
        else if ("E".equals(this.DM_Estoque))
            return "Estornado";
        else return "Sem Movimentos";
    }

    public String getDesc_DM_Tipo_Nota_Fiscal() {
        if ("E".equals(dm_tipo_nota_fiscal)) {
            return "Entrada";
        } else if ("D".equals(dm_tipo_nota_fiscal)) {
            return "Devolução";
        } else return "Saída";
    }

    public String getDescDM_Forma_Pagamento() {

        if ("1".equals(dm_forma_pagamento)) {
            return "Boleto";
        } else if ("2".equals(dm_forma_pagamento)) {
            return "Cheque";
        } else if ("3".equals(dm_forma_pagamento)) {
            return "Depósito CC";
        } else if ("4".equals(dm_forma_pagamento)) {
            return "Carteira";
        } else if ("5".equals(dm_forma_pagamento)) {
            return "Dinheiro";
        } else return "Não informado!";
    }

    public String getDescDM_Tipo_Devolucao() {
        if ("P".equals(DM_Tipo_Devolucao))
            return "Parcial";
        else if ("T".equals(DM_Tipo_Devolucao))
            return "Total";
        else return "Não informado!";
    }

    public String getOid_Vendedor() {
        return oid_Vendedor;
    }
    public void setOid_Vendedor(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
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
    public double getVL_Cofins() {
        return VL_Cofins;
    }
    public void setVL_Cofins(double VL_Cofins) {
        this.VL_Cofins = VL_Cofins;
    }
    public double getVL_Csll() {
        return VL_Csll;
    }
    public void setVL_Csll(double VL_Csll) {
        this.VL_Csll = VL_Csll;
    }
    public double getVL_Pis() {
        return VL_Pis;
    }
    public void setVL_Pis(double VL_Pis) {
        this.VL_Pis = VL_Pis;
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
    public void setOid_pessoa_fornecedor(String oid_pessoa_fornecedor) {
        this.oid_pessoa_fornecedor = oid_pessoa_fornecedor;
    }
    public String getOid_pessoa_fornecedor() {
        return oid_pessoa_fornecedor;
    }
    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setNr_Itens(double nr_Itens) {
        this.nr_Itens = nr_Itens;
    }
    public double getNr_Itens() {
        return nr_Itens;
    }
    public Integer getOid_Centro_Custo() {
        return oid_Centro_Custo;
    }
    public void setOid_Centro_Custo(Integer oid_Centro_Custo) {
        this.oid_Centro_Custo = oid_Centro_Custo;
    }
    public void setDM_Pendencia(String DM_Pendencia) {
        this.DM_Pendencia = DM_Pendencia;
    }
    public String getDM_Pendencia() {
        return DM_Pendencia;
    }
    public void setNM_Pendencia(String NM_Pendencia) {
        this.NM_Pendencia = NM_Pendencia;
    }
    public String getNM_Pendencia() {
        return NM_Pendencia;
    }
    public void setNM_Situacao(String NM_Situacao) {
        this.NM_Situacao = NM_Situacao;
    }
    public String getNM_Situacao() {
        return NM_Situacao;
    }
    public void setNR_Proximo_Lote(long NR_Proximo_Lote) {
        this.NR_Proximo_Lote = NR_Proximo_Lote;
    }
    public long getNR_Proximo_Lote() {
        return NR_Proximo_Lote;
    }
    public void setDT_Inicial(String DT_Inicial) {
        this.DT_Inicial = DT_Inicial;
    }
    public String getDT_Inicial() {
        return DT_Inicial;
    }
    public void setDT_Final(String DT_Final) {
        this.DT_Final = DT_Final;
    }
    public String getDT_Final() {
        return DT_Final;
    }
    public void setDM_Relatorio(String DM_Relatorio) {
        this.DM_Relatorio = DM_Relatorio;
    }
    public String getDM_Relatorio() {
        return DM_Relatorio;
    }
    public String getCD_Condicao_Pagamento() {
        return CD_Condicao_Pagamento;
    }
    public void setCD_Condicao_Pagamento(String condicao_Pagamento) {
        CD_Condicao_Pagamento = condicao_Pagamento;
    }
    public String getNM_Condicao_Pagamento() {
        return NM_Condicao_Pagamento;
    }
    public void setNM_Condicao_Pagamento(String condicao_Pagamento) {
        NM_Condicao_Pagamento = condicao_Pagamento;
    }
    public int getOid_Condicao_Pagamento() {
        return oid_Condicao_Pagamento;
    }
    public void setOid_Condicao_Pagamento(int oid_Condicao_Pagamento) {
        this.oid_Condicao_Pagamento = oid_Condicao_Pagamento;
    }
    public String getOid_Conhecimento() {
        return oid_Conhecimento;
    }
    public void setOid_Conhecimento(String oid_Conhecimento) {
        this.oid_Conhecimento = oid_Conhecimento;
    }
    public int getOid_Conta() {
        return oid_Conta;
    }
    public void setOid_Conta(int oid_Conta) {
        this.oid_Conta = oid_Conta;
    }
    public int getOid_Entregador() {
        return oid_Entregador;
    }
    public void setOid_Entregador(int oid_Entregador) {
        this.oid_Entregador = oid_Entregador;
    }
    public boolean isAtualizarEstoque() {
        return AtualizarEstoque;
    }
    public void setAtualizarEstoque(boolean atualizarEstoque) {
        AtualizarEstoque = atualizarEstoque;
    }
    public String getDM_Impresso() {
        return DM_Impresso;
    }
    public void setDM_Impresso(String impresso) {
        DM_Impresso = impresso;
    }
    public long getNr_nota_fiscal_final() {
        return nr_nota_fiscal_final;
    }
    public void setNr_nota_fiscal_final(long nr_nota_fiscal_final) {
        this.nr_nota_fiscal_final = nr_nota_fiscal_final;
    }
    public String getDM_Romaneio() {
        return DM_Romaneio;
    }
    public void setDM_Romaneio(String romaneio) {
        DM_Romaneio = romaneio;
    }
    public String getDM_Tipo_Devolucao() {
        return DM_Tipo_Devolucao;
    }
    public void setDM_Tipo_Devolucao(String tipo_Devolucao) {
        DM_Tipo_Devolucao = tipo_Devolucao;
    }
    public String getOid_Nota_Fiscal_Devolucao() {
        return oid_Nota_Fiscal_Devolucao;
    }
    public String getOid_Nota_Fiscal_Original() {
        return oid_Nota_Fiscal_Original;
    }
    public void setOid_Nota_Fiscal_Devolucao(String oid_Nota_Fiscal_Devolucao) {
        this.oid_Nota_Fiscal_Devolucao = oid_Nota_Fiscal_Devolucao;
    }
    public void setOid_Nota_Fiscal_Original(String oid_Nota_Fiscal_Original) {
        this.oid_Nota_Fiscal_Original = oid_Nota_Fiscal_Original;
    }
    public double getVL_Desconto_Itens() {
        return VL_Desconto_Itens;
    }
    public void setVL_Desconto_Itens(double desconto_Itens) {
        VL_Desconto_Itens = desconto_Itens;
    }
    public double getVL_Base_Calculo_ICMS() {
        return VL_Base_Calculo_ICMS;
    }
    public void setVL_Base_Calculo_ICMS(double base_Calculo_ICMS) {
        VL_Base_Calculo_ICMS = base_Calculo_ICMS;
    }
    public double getVL_Base_Calculo_ICMS_Subst() {
        return VL_Base_Calculo_ICMS_Subst;
    }
    public void setVL_Base_Calculo_ICMS_Subst(double base_Calculo_ICMS_Subst) {
        VL_Base_Calculo_ICMS_Subst = base_Calculo_ICMS_Subst;
    }
    public double getVL_ICMS_Subst() {
        return VL_ICMS_Subst;
    }
    public void setVL_ICMS_Subst(double subst) {
        VL_ICMS_Subst = subst;
    }
    public String getDM_Tipo_Operacao() {
        return DM_Tipo_Operacao;
    }
    public void setDM_Tipo_Operacao(String tipo_Operacao) {
        DM_Tipo_Operacao = tipo_Operacao;
    }
    public int getOid_grupo_nota_fiscal() {
        return oid_grupo_nota_fiscal;
    }
    public void setOid_grupo_nota_fiscal(int oid_grupo_nota_fiscal) {
        this.oid_grupo_nota_fiscal = oid_grupo_nota_fiscal;
    }
    public int getOid_Localizacao() {
        return oid_Localizacao;
    }
    public void setOid_Localizacao(int oid_Localizacao) {
        this.oid_Localizacao = oid_Localizacao;
    }
    public double getPE_Aliquota_ICMS() {
        return PE_Aliquota_ICMS;
    }
    public void setPE_Aliquota_ICMS(double aliquota_ICMS) {
        PE_Aliquota_ICMS = aliquota_ICMS;
    }
    public double getNR_Peso() {
        return NR_Peso;
    }
    public void setNR_Peso(double peso) {
        NR_Peso = peso;
    }
    public String getOid_Pessoa_Consignatario() {
        return oid_Pessoa_Consignatario;
    }
    public void setOid_Pessoa_Consignatario(String oid_Pessoa_Consignatario) {
        this.oid_Pessoa_Consignatario = oid_Pessoa_Consignatario;
    }
    public String getOid_Pessoa_Redespacho() {
        return oid_Pessoa_Redespacho;
    }
    public void setOid_Pessoa_Redespacho(String oid_Pessoa_Redespacho) {
        this.oid_Pessoa_Redespacho = oid_Pessoa_Redespacho;
    }
    public double getVL_Adicional() {
        return VL_Adicional;
    }
    public void setVL_Adicional(double adicional) {
        VL_Adicional = adicional;
    }
    public String getDt_entrada_final() {
        return dt_entrada_final;
    }
    public void setDt_entrada_final(String dt_entrada_final) {
        this.dt_entrada_final = dt_entrada_final;
    }
    public int getOid_Natureza_Operacao_Servico() {
        return oid_Natureza_Operacao_Servico;
    }
    public void setOid_Natureza_Operacao_Servico(int oid_Natureza_Operacao_Servico) {
        this.oid_Natureza_Operacao_Servico = oid_Natureza_Operacao_Servico;
    }
    public String getOid_Veiculo() {
        return oid_Veiculo;
    }
    public void setOid_Veiculo(String oid_Veiculo) {
        this.oid_Veiculo = oid_Veiculo;
    }
    public String getDT_Entrega() {
        return DT_Entrega;
    }
    public void setDT_Entrega(String entrega) {
        DT_Entrega = entrega;
    }
    public String getDT_Entrega_Final() {
        return DT_Entrega_Final;
    }
    public void setDT_Entrega_Final(String entrega_Final) {
        DT_Entrega_Final = entrega_Final;
    }
    public int getOid_Tabela_Venda() {
        return oid_Tabela_Venda;
    }
    public void setOid_Tabela_Venda(int oid_Tabela_Venda) {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
    }
    public boolean isUpdatePrecosByCond() {
        return updatePrecosByCond;
    }
    public void setUpdatePrecosByCond(boolean updatePrecosByCond) {
        this.updatePrecosByCond = updatePrecosByCond;
    }
    public boolean isUpdatePrecosByTabela() {
        return updatePrecosByTabela;
    }
    public void setUpdatePrecosByTabela(boolean updatePrecosByTabela) {
        this.updatePrecosByTabela = updatePrecosByTabela;
    }
    public String getOid_Pedido() {
        return oid_Pedido;
    }
    public void setOid_Pedido(String oid_Pedido) {
        this.oid_Pedido = oid_Pedido;
    }


    public double getNR_Peso_Cubado() {
        return NR_Peso_Cubado;
    }


    public void setNR_Peso_Cubado(double peso_Cubado) {
        NR_Peso_Cubado = peso_Cubado;
    }


    public int getOid_Carga_Entrega() {
        return oid_Carga_Entrega;
    }


    public void setOid_Carga_Entrega(int oid_Carga_Entrega) {
        this.oid_Carga_Entrega = oid_Carga_Entrega;
    }
	public double getVL_Diferenca_Aliq_ICMS() {
		return VL_Diferenca_Aliq_ICMS;
	}
	public void setVL_Diferenca_Aliq_ICMS(double diferenca_Aliq_ICMS) {
		VL_Diferenca_Aliq_ICMS = diferenca_Aliq_ICMS;
	}

	public double getVl_Comissao() {
		return vl_Comissao;
	}

	public void setVl_Comissao(double vl_Comissao) {
		this.vl_Comissao = vl_Comissao;
	}
	public String getNm_complemento() {
		return nm_complemento;
	}
	public void setNm_complemento(String nm_complemento) {
		this.nm_complemento = nm_complemento;
	}

	public String getDm_Devolvido() {
		return dm_Devolvido;
	}

	public void setDm_Devolvido(String dm_Devolvido) {
		this.dm_Devolvido = dm_Devolvido;
	}

	public long getNr_volumes_nota() {
		return nr_volumes_nota;
	}

	public void setNr_volumes_nota(long nr_volumes_nota) {
		this.nr_volumes_nota = nr_volumes_nota;
	}

	public String getOid_Manifesto() {
		return oid_Manifesto;
	}

	public void setOid_Manifesto(String oid_Manifesto) {
		this.oid_Manifesto = oid_Manifesto;
	}

	public String getNr_Conhecimento() {
		return nr_Conhecimento;
	}

	public void setNr_Conhecimento(String nr_Conhecimento) {
		this.nr_Conhecimento = nr_Conhecimento;
	}

	public String getNr_Manifesto() {
		return nr_Manifesto;
	}

	public void setNr_Manifesto(String nr_Manifesto) {
		this.nr_Manifesto = nr_Manifesto;
	}

	public long getOid_produto() {
		return oid_produto;
	}

	public void setOid_produto(long oid_produto) {
		this.oid_produto = oid_produto;
	}

	public String getDm_grupo_faturamento() {
		return dm_grupo_faturamento;
	}

	public void setDm_grupo_faturamento(String dm_grupo_faturamento) {
		this.dm_grupo_faturamento = dm_grupo_faturamento;
	}

	public String getOid_pagador() {
		return oid_pagador;
	}

	public void setOid_pagador(String oid_pagador) {
		this.oid_pagador = oid_pagador;
	}

	public String getDM_Frete() {
		return DM_Frete;
	}

	public void setDM_Frete(String frete) {
		DM_Frete = frete;
	}

	public String getNfe_chave_acesso() {
		return nfe_chave_acesso;
	}

	public void setNfe_chave_acesso(String nfe_chave_acesso) {
		this.nfe_chave_acesso = nfe_chave_acesso;
	}

	public String getNfe_cstat() {
		return nfe_cstat;
	}

	public void setNfe_cstat(String nfe_cstat) {
		this.nfe_cstat = nfe_cstat;
	}

	public String getNfe_cstat_lote() {
		return nfe_cstat_lote;
	}

	public void setNfe_cstat_lote(String nfe_cstat_lote) {
		this.nfe_cstat_lote = nfe_cstat_lote;
	}

	public String getNfe_data_lote() {
		return nfe_data_lote;
	}

	public void setNfe_data_lote(String nfe_data_lote) {
		this.nfe_data_lote = nfe_data_lote;
	}

	public String getNfe_digestvalue() {
		return nfe_digestvalue;
	}

	public void setNfe_digestvalue(String nfe_digestvalue) {
		this.nfe_digestvalue = nfe_digestvalue;
	}

	public String getNfe_dt_hr_recebimento() {
		return nfe_dt_hr_recebimento;
	}

	public void setNfe_dt_hr_recebimento(String nfe_dt_hr_recebimento) {
		this.nfe_dt_hr_recebimento = nfe_dt_hr_recebimento;
	}

	public String getNfe_motivo() {
		return nfe_motivo;
	}

	public void setNfe_motivo(String nfe_motivo) {
		this.nfe_motivo = nfe_motivo;
	}

	public String getNfe_motivo_lote() {
		return nfe_motivo_lote;
	}

	public void setNfe_motivo_lote(String nfe_motivo_lote) {
		this.nfe_motivo_lote = nfe_motivo_lote;
	}

	public String getNfe_protocolo() {
		return nfe_protocolo;
	}

	public void setNfe_protocolo(String nfe_protocolo) {
		this.nfe_protocolo = nfe_protocolo;
	}

	public String getNfe_recibo() {
		return nfe_recibo;
	}

	public void setNfe_recibo(String nfe_recibo) {
		this.nfe_recibo = nfe_recibo;
	}

	public String getOid_pessoa_transportador() {
		return oid_pessoa_transportador;
	}

	public void setOid_pessoa_transportador(String oid_pessoa_transportador) {
		this.oid_pessoa_transportador = oid_pessoa_transportador;
	}
}