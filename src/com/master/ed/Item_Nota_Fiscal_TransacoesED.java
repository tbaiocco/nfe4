package com.master.ed;

public class Item_Nota_Fiscal_TransacoesED extends MasterED {

    public Item_Nota_Fiscal_TransacoesED() {
    }
    public Item_Nota_Fiscal_TransacoesED(String nota_Fiscal) {
        super();
        OID_Nota_Fiscal = nota_Fiscal;
    }
    public Item_Nota_Fiscal_TransacoesED(long item_Nota_Fiscal) {
        OID_Item_Nota_Fiscal = item_Nota_Fiscal;
    }

    private boolean atualizaItensNota = false;
    private boolean atualizaValorNota = false;
    private boolean vendaDireta = false;

    private String OID_Pessoa;
    private String NM_Razao_Social;
    private String NR_Lote_Produto;

    private String CD_Fornecedor;
    private String NM_Unidade;
    private long oid_natureza_operacao;
    private String NR_CNPJ_CPF;
    private String OID_Vendedor;
    private long OID_Unidade;
    private long OID_Condicao_Pagamento;
    private String NM_Condicao_Pagamento;
    private String CD_Condicao_Pagamento;
    private String DM_Situacao;
    private String DM_Pedido;
    private String DT_Pedido;
    private String HR_Pedido;
    private String DT_Previsao_Entrega;
    private String HR_Previsao_Entrega;
    private String DT_Entrega;
    private String HR_Entrega;
    private double VL_Desconto;
    private double VL_Produto;
    private String DM_Frete;
    private double VL_Item;
    private long OID_Tipo_Documento;
    private long OID_Pedido;
    private long NR_Pedido;
    private String CD_Unidade;
    private String NM_Fantasia;
    private double VL_Frete;
    private double NR_QT_Pedido;
    private String NM_Produto;
    private String OID_Produto;
    private String CD_Produto;
    private String DT_Pedido_Inicial;
    private String DT_Pedido_Final;
    private String DT_Previsao_Inicial;
    private String DT_Previsao_Final;
    private String acao;
    private double PE_Aliquota_IPI;
    private double PE_Desconto;
    private double PE_Desconto_Extra;
    private double VL_Unitario;
    private double VL_IPI;
    private long OID_Item_Pedido;
    private double NR_QT_Atendido;
    private double NR_Peso_Real;
    private long OID_Item_Nota_Fiscal;
    private String OID_Nota_Fiscal;
    private String NM_Pedido;
    private String NR_Nota_Fiscal;
    private String DM_Tipo_Nota_Fiscal;
    private long OID_Deposito;
    private double VL_ICMS;
    private double VL_ICMS_Aprov;
    private double PE_Aliquota_ICMS;
    private double PE_Aliquota_ICMS_Aprov;
    private String CD_Local_Descarga;
    private int oid_Tipo_Estoque;
    private double PE_Margem_Contribuicao;
    private double VL_Margem_Contribuicao;
    private double VL_Base_Calculo_ICMS;
    private int oid_Taxa_Produto;
    private boolean Pesagem;
    private int oid_Localizacao;
    private int oid_Preco_Produto;

    private String DM_Quantidade;
    private double NR_Quantidade;
    private double NR_QT_Devolucao;
    private double VL_Desconto_NF;
    private double VL_Custo;
    private double VL_Adicional; //Influencia sobre o Preço de CUSTO referente a TABELA de PAUTA

    private double NR_Quantidade_Fardo;
    private double vl_Fator;
    
    private int oid_Tipo_Movimento_Produto;

    private double vl_Comissao;

    private String dm_Devolvido;

    private int oid_Tipo_Servico;
    private double PE_ISSQN;
    private double VL_ISSQN;
    private String CD_Tipo_Servico;
    private String NM_Tipo_Servico;

    String oid_Produto_Cliente;
    private String tx_Observacao;

    public String getTx_Observacao() {
  	return tx_Observacao;
  }
  public void setTx_Observacao(String tx_Observacao) {
  	this.tx_Observacao = tx_Observacao;
  }

    public double getPE_Margem_Contribuicao() {
        return PE_Margem_Contribuicao;
    }

    public void setPE_Margem_Contribuicao(double margem_Contribuicao) {
        PE_Margem_Contribuicao = margem_Contribuicao;
    }

    public double getVL_Margem_Contribuicao() {
        return VL_Margem_Contribuicao;
    }

    public void setVL_Margem_Contribuicao(double margem_Contribuicao) {
        VL_Margem_Contribuicao = margem_Contribuicao;
    }

    public String getOID_Pessoa() {
        return OID_Pessoa;
    }

    public void setOID_Pessoa(String OID_Pessoa) {
        this.OID_Pessoa = OID_Pessoa;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public void setCD_Fornecedor(String CD_Fornecedor) {
        this.CD_Fornecedor = CD_Fornecedor;
    }

    public String getCD_Fornecedor() {
        return CD_Fornecedor;
    }

    public void setNM_Unidade(String NM_Unidade) {
        this.NM_Unidade = NM_Unidade;
    }

    public String getNM_Unidade() {
        return NM_Unidade;
    }

    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }

    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }

    public void setOID_Vendedor(String OID_Vendedor) {
        this.OID_Vendedor = OID_Vendedor;
    }

    public String getOID_Vendedor() {
        return OID_Vendedor;
    }

    public void setOID_Unidade(long OID_Unidade) {
        this.OID_Unidade = OID_Unidade;
    }

    public long getOID_Unidade() {
        return OID_Unidade;
    }

    public void setOID_Condicao_Pagamento(long OID_Condicao_Pagamento) {
        this.OID_Condicao_Pagamento = OID_Condicao_Pagamento;
    }

    public long getOID_Condicao_Pagamento() {
        return OID_Condicao_Pagamento;
    }

    public void setNM_Condicao_Pagamento(String NM_Condicao_Pagamento) {
        this.NM_Condicao_Pagamento = NM_Condicao_Pagamento;
    }

    public String getNM_Condicao_Pagamento() {
        return NM_Condicao_Pagamento;
    }

    public void setCD_Condicao_Pagamento(String CD_Condicao_Pagamento) {
        this.CD_Condicao_Pagamento = CD_Condicao_Pagamento;
    }

    public String getCD_Condicao_Pagamento() {
        return CD_Condicao_Pagamento;
    }

    public void setVL_Item(double VL_Item) {
        this.VL_Item = VL_Item;
    }

    public double getVL_Item() {
        return VL_Item;
    }

    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }

    public String getDM_Situacao() {
        return DM_Situacao;
    }

    public void setDM_Pedido(String DM_Pedido) {
        this.DM_Pedido = DM_Pedido;
    }

    public String getDM_Pedido() {
        return DM_Pedido;
    }

    public void setDT_Pedido(String DT_Pedido) {
        this.DT_Pedido = DT_Pedido;
    }

    public String getDT_Pedido() {
        return DT_Pedido;
    }

    public void setHR_Pedido(String HR_Pedido) {
        this.HR_Pedido = HR_Pedido;
    }

    public String getHR_Pedido() {
        return HR_Pedido;
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

    public void setVL_Desconto(double VL_Desconto) {
        this.VL_Desconto = VL_Desconto;
    }

    public double getVL_Desconto() {
        return VL_Desconto;
    }

    public void setVL_Produto(double VL_Produto) {
        this.VL_Produto = VL_Produto;
    }

    public double getVL_Produto() {
        return VL_Produto;
    }

    public void setDM_Frete(String DM_Frete) {
        this.DM_Frete = DM_Frete;
    }

    public String getDM_Frete() {
        return DM_Frete;
    }

    public void setOID_Tipo_Documento(long OID_Tipo_Documento) {
        this.OID_Tipo_Documento = OID_Tipo_Documento;
    }

    public long getOID_Tipo_Documento() {
        return OID_Tipo_Documento;
    }

    public void setOID_Pedido(long OID_Pedido) {
        this.OID_Pedido = OID_Pedido;
    }

    public long getOID_Pedido() {
        return OID_Pedido;
    }

    public void setNR_Pedido(long NR_Pedido) {
        this.NR_Pedido = NR_Pedido;
    }

    public long getNR_Pedido() {
        return NR_Pedido;
    }

    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }

    public String getCD_Unidade() {
        return CD_Unidade;
    }

    public void setNM_Fantasia(String NM_Fantasia) {
        this.NM_Fantasia = NM_Fantasia;
    }

    public String getNM_Fantasia() {
        return NM_Fantasia;
    }

    public void setVL_Frete(double VL_Frete) {
        this.VL_Frete = VL_Frete;
    }

    public double getVL_Frete() {
        return VL_Frete;
    }

    public void setNR_QT_Pedido(double NR_QT_Pedido) {
        this.NR_QT_Pedido = NR_QT_Pedido;
    }

    public double getNR_QT_Pedido() {
        return NR_QT_Pedido;
    }

    public void setNM_Produto(String NM_Produto) {
        this.NM_Produto = NM_Produto;
    }

    public String getNM_Produto() {
        return NM_Produto;
    }

    public void setOID_Produto(String OID_Produto) {
        this.OID_Produto = OID_Produto;
    }

    public String getOID_Produto() {
        return OID_Produto;
    }

    public void setCD_Produto(String CD_Produto) {
        this.CD_Produto = CD_Produto;
    }

    public String getCD_Produto() {
        return CD_Produto;
    }

    public void setDT_Pedido_Inicial(String DT_Pedido_Inicial) {
        this.DT_Pedido_Inicial = DT_Pedido_Inicial;
    }

    public String getDT_Pedido_Inicial() {
        return DT_Pedido_Inicial;
    }

    public void setDT_Pedido_Final(String DT_Pedido_Final) {
        this.DT_Pedido_Final = DT_Pedido_Final;
    }

    public String getDT_Pedido_Final() {
        return DT_Pedido_Final;
    }

    public void setDT_Previsao_Inicial(String DT_Previsao_Inicial) {
        this.DT_Previsao_Inicial = DT_Previsao_Inicial;
    }

    public String getDT_Previsao_Inicial() {
        return DT_Previsao_Inicial;
    }

    public void setDT_Previsao_Final(String DT_Previsao_Final) {
        this.DT_Previsao_Final = DT_Previsao_Final;
    }

    public String getDT_Previsao_Final() {
        return DT_Previsao_Final;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getAcao() {
        return acao;
    }

    public void setPE_Aliquota_IPI(double PE_Aliquota_IPI) {
        this.PE_Aliquota_IPI = PE_Aliquota_IPI;
    }

    public double getPE_Aliquota_IPI() {
        return PE_Aliquota_IPI;
    }

    public void setPE_Desconto(double PE_Desconto) {
        this.PE_Desconto = PE_Desconto;
    }

    public double getPE_Desconto() {
        return PE_Desconto;
    }

    public void setVL_Unitario(double VL_Unitario) {
        this.VL_Unitario = VL_Unitario;
    }

    public double getVL_Unitario() {
        return VL_Unitario;
    }

    public void setVL_IPI(double VL_IPI) {
        this.VL_IPI = VL_IPI;
    }

    public double getVL_IPI() {
        return VL_IPI;
    }

    public void setOID_Item_Pedido(long OID_Item_Pedido) {
        this.OID_Item_Pedido = OID_Item_Pedido;
    }

    public long getOID_Item_Pedido() {
        return OID_Item_Pedido;
    }

    public void setOID_Item_Nota_Fiscal(long OID_Item_Nota_Fiscal) {
        this.OID_Item_Nota_Fiscal = OID_Item_Nota_Fiscal;
    }

    public long getOID_Item_Nota_Fiscal() {
        return OID_Item_Nota_Fiscal;
    }

    public void setNR_QT_Atendido(double NR_QT_Atendido) {
        this.NR_QT_Atendido = NR_QT_Atendido;
    }

    public double getNR_QT_Atendido() {
        return NR_QT_Atendido;
    }

    public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
        this.OID_Nota_Fiscal = OID_Nota_Fiscal;
    }

    public String getOID_Nota_Fiscal() {
        return OID_Nota_Fiscal;
    }

    public void setNM_Pedido(String NM_Pedido) {
        this.NM_Pedido = NM_Pedido;
    }

    public String getNM_Pedido() {
        return NM_Pedido;
    }

    public void setNR_Nota_Fiscal(String NR_Nota_Fiscal) {
        this.NR_Nota_Fiscal = NR_Nota_Fiscal;
    }

    public String getNR_Nota_Fiscal() {
        return NR_Nota_Fiscal;
    }

    public void setOID_Deposito(long OID_Deposito) {
        this.OID_Deposito = OID_Deposito;
    }

    public long getOID_Deposito() {
        return OID_Deposito;
    }

    public void setVL_ICMS(double VL_ICMS) {
        this.VL_ICMS = VL_ICMS;
    }

    public double getVL_ICMS() {
        return VL_ICMS;
    }

    public void setVL_ICMS_Aprov(double VL_ICMS_Aprov) {
        this.VL_ICMS_Aprov = VL_ICMS_Aprov;
    }

    public double getVL_ICMS_Aprov() {
        return VL_ICMS_Aprov;
    }

    public void setPE_Aliquota_ICMS(double PE_Aliquota_ICMS) {
        this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
    }

    public double getPE_Aliquota_ICMS() {
        return PE_Aliquota_ICMS;
    }

    public void setPE_Aliquota_ICMS_Aprov(double PE_Aliquota_ICMS_Aprov) {
        this.PE_Aliquota_ICMS_Aprov = PE_Aliquota_ICMS_Aprov;
    }

    public double getPE_Aliquota_ICMS_Aprov() {
        return PE_Aliquota_ICMS_Aprov;
    }

    public void setCD_Local_Descarga(String CD_Local_Descarga) {
        this.CD_Local_Descarga = CD_Local_Descarga;
    }

    public String getCD_Local_Descarga() {
        return CD_Local_Descarga;
    }

    public long getOid_natureza_operacao() {
        return oid_natureza_operacao;
    }

    public void setOid_natureza_operacao(long oid_natureza_operacao) {
        this.oid_natureza_operacao = oid_natureza_operacao;
    }

    public int getOid_Tipo_Estoque() {
        return oid_Tipo_Estoque;
    }

    public void setOid_Tipo_Estoque(int oid_Tipo_Estoque) {
        this.oid_Tipo_Estoque = oid_Tipo_Estoque;
    }

    public double getVL_Base_Calculo_ICMS() {
        return VL_Base_Calculo_ICMS;
    }

    public void setVL_Base_Calculo_ICMS(double base_Calculo_ICMS) {
        VL_Base_Calculo_ICMS = base_Calculo_ICMS;
    }

    public int getOid_Taxa_Produto() {
        return oid_Taxa_Produto;
    }

    public void setOid_Taxa_Produto(int oid_Taxa_Produto) {
        this.oid_Taxa_Produto = oid_Taxa_Produto;
    }

    public double getNR_Peso_Real() {
        return NR_Peso_Real;
    }

    public void setNR_Peso_Real(double peso_Real) {
        NR_Peso_Real = peso_Real;
    }

    public boolean isPesagem() {
        return Pesagem;
    }

    public void setPesagem(boolean pesagem) {
        Pesagem = pesagem;
    }
    public String getDM_Quantidade() {
        return DM_Quantidade;
    }
    public void setDM_Quantidade(String quantidade) {
        DM_Quantidade = quantidade;
    }
    public double getNR_Quantidade() {
        return NR_Quantidade;
    }
    public void setNR_Quantidade(double quantidade) {
        NR_Quantidade = quantidade;
    }
    public double getVL_Custo() {
        return VL_Custo;
    }
    public void setVL_Custo(double custo) {
        VL_Custo = custo;
    }
    public double getVL_Desconto_NF() {
        return VL_Desconto_NF;
    }
    public void setVL_Desconto_NF(double desconto_NF) {
        VL_Desconto_NF = desconto_NF;
    }
    public double getNR_QT_Devolucao() {
        return NR_QT_Devolucao;
    }
    public void setNR_QT_Devolucao(double devolucao) {
        NR_QT_Devolucao = devolucao;
    }
    public int getOid_Tipo_Movimento_Produto() {
        return oid_Tipo_Movimento_Produto;
    }
    public void setOid_Tipo_Movimento_Produto(int oid_Tipo_Movimento_Produto) {
        this.oid_Tipo_Movimento_Produto = oid_Tipo_Movimento_Produto;
    }
    public int getOid_Localizacao() {
        return oid_Localizacao;
    }
    public void setOid_Localizacao(int oid_Localizacao) {
        this.oid_Localizacao = oid_Localizacao;
    }
    public double getVL_Adicional() {
        return VL_Adicional;
    }
    public void setVL_Adicional(double adicional) {
        VL_Adicional = adicional;
    }
    public boolean isAtualizaItensNota() {
        return atualizaItensNota;
    }
    public void setAtualizaItensNota(boolean atualizaItensNota) {
        this.atualizaItensNota = atualizaItensNota;
    }
    public boolean isAtualizaValorNota() {
        return atualizaValorNota;
    }
    public void setAtualizaValorNota(boolean atualizaValorNota) {
        this.atualizaValorNota = atualizaValorNota;
    }
    public int getOid_Preco_Produto() {
        return oid_Preco_Produto;
    }
    public void setOid_Preco_Produto(int oid_Preco_Produto) {
        this.oid_Preco_Produto = oid_Preco_Produto;
    }
    public String getDM_Tipo_Nota_Fiscal() {
        return DM_Tipo_Nota_Fiscal;
    }
    public void setDM_Tipo_Nota_Fiscal(String tipo_Nota_Fiscal) {
        DM_Tipo_Nota_Fiscal = tipo_Nota_Fiscal;
    }
    public boolean isVendaDireta() {
        return vendaDireta;
    }
    public void setVendaDireta(boolean vendaDireta) {
        this.vendaDireta = vendaDireta;
    }

    public double getPE_Desconto_Extra() {
        return PE_Desconto_Extra;
    }

    public void setPE_Desconto_Extra(double desconto_Extra) {
        PE_Desconto_Extra = desconto_Extra;
    }
	public double getVl_Comissao() {
		return vl_Comissao;
	}
	public void setVl_Comissao(double vl_Comissao) {
		this.vl_Comissao = vl_Comissao;
	}
	public String getOid_Produto_Cliente() {
		return oid_Produto_Cliente;
	}
	public void setOid_Produto_Cliente(String oid_Produto_Cliente) {
		this.oid_Produto_Cliente = oid_Produto_Cliente;
	}
	public String getDm_Devolvido() {
		return dm_Devolvido;
	}
	public void setDm_Devolvido(String dm_Devolvido) {
		this.dm_Devolvido = dm_Devolvido;
	}
	public String getNR_Lote_Produto() {
		return NR_Lote_Produto;
	}
	public void setNR_Lote_Produto(String lote_Produto) {
		NR_Lote_Produto = lote_Produto;
	}
	public int getOid_Tipo_Servico() {
		return oid_Tipo_Servico;
	}
	public void setOid_Tipo_Servico(int oid_Tipo_Servico) {
		this.oid_Tipo_Servico = oid_Tipo_Servico;
	}
	public double getPE_ISSQN() {
		return PE_ISSQN;
	}
	public void setPE_ISSQN(double pe_issqn) {
		PE_ISSQN = pe_issqn;
	}
	public double getVL_ISSQN() {
		return VL_ISSQN;
	}
	public void setVL_ISSQN(double vl_issqn) {
		VL_ISSQN = vl_issqn;
	}
	public String getCD_Tipo_Servico() {
		return CD_Tipo_Servico;
	}
	public void setCD_Tipo_Servico(String tipo_Servico) {
		CD_Tipo_Servico = tipo_Servico;
	}
	public String getNM_Tipo_Servico() {
		return NM_Tipo_Servico;
	}
	public void setNM_Tipo_Servico(String tipo_Servico) {
		NM_Tipo_Servico = tipo_Servico;
	}
	public double getNR_Quantidade_Fardo() {
		return NR_Quantidade_Fardo;
	}
	public void setNR_Quantidade_Fardo(double nR_Quantidade_Fardo) {
		NR_Quantidade_Fardo = nR_Quantidade_Fardo;
	}
	public double getVl_Fator() {
		return vl_Fator;
	}
	public void setVl_Fator(double vl_Fator) {
		this.vl_Fator = vl_Fator;
	}
}