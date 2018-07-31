package com.master.ed;

public class Preco_ProdutoED extends MasterED {

    public Preco_ProdutoED() {
        super();
    }
    public Preco_ProdutoED(long preco_Produto) {
        this.OID_Preco_Produto = preco_Produto;
    }
    public Preco_ProdutoED(long preco_Produto, boolean loadTabela, boolean loadProduto) {
        this.OID_Preco_Produto = preco_Produto;
        this.carregaTabela = loadTabela;
        this.carregaProduto = loadProduto;
    }
    public Preco_ProdutoED(int oid_Tabela_Venda) {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
    }
    public Preco_ProdutoED(int oid_Tabela_Venda, boolean loadTabela, boolean loadProduto) {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
        this.carregaTabela = loadTabela;
        this.carregaProduto = loadProduto;
    }
    public Preco_ProdutoED(String produto) {
        this.OID_Produto = produto;
    }
    public Preco_ProdutoED(String produto, boolean loadTabela, boolean loadProduto) {
        this.OID_Produto = produto;
        this.carregaTabela = loadTabela;
        this.carregaProduto = loadProduto;
    }
    //*** Referencia a Tabela de Vendas
    public Tabela_VendaED edTabela = new Tabela_VendaED();
    private boolean carregaTabela = false;
    private boolean carregaProduto = false;

    private long OID_Preco_Produto;
    private int oid_Tabela_Venda;
    private String OID_Produto_Cliente;
    private double VL_Produto;
    private double VL_Preco_Custo;
    private double PE_Desconto_Avista;
    private double PE_Desconto_7_Dias;
    private double PE_Acrescimo_21_Dias;
    private double PE_Acrescimo_28_Dias;
    private double PE_Acrescimo_30_Dias;
    private double VL_Desconto_Avista;
    private double VL_Desconto_7_Dias;
    private double VL_Acrescimo_21_Dias;
    private double VL_Acrescimo_28_Dias;
    private double VL_Acrescimo_30_Dias;
    private String oid_Pessoa;
    private String NR_CNPJ_CPF;
    private String NM_Razao_Social;
    private String oid_Pessoa_Cliente;
    private String NM_Razao_Social_Cliente;
    private String NM_Tabela;
    private String DT_Vigencia;
    private String DT_Vigencia_Final;
    private String DT_Validade;
    private String NR_CNPJ_CPF_Cliente;
    private String DM_Alterado;
    
    //-----------------------
    private String OID_Produto;
    private String CD_Produto;
    private String NM_Produto;
    private double NR_QT_Caixa;
    private double NR_Peso_Medio;

    private String oid_Pessoa_Fornecedor;
    private String NR_CNPJ_CPF_Fornecedor;
    private String NM_Razao_Social_Fornecedor;
    
    private int oid_Estrutura_Produto;
    private String CD_Estrutura_Produto;
    private String NM_Estrutura_Produto;
    
    private int oid_Tipo_Produto;
    private String CD_Tipo_Produto;
    private String NM_Tipo_Produto;
    
    private int oid_Unidade_Produto;
    private String CD_Unidade_Produto;
    private String NM_Unidade_Produto;
    
    private String DM_Situacao;
    private double PE_Comissao;
    private double NR_QT_Atual;
    //----------------------------------
    
    //*** Implementado
    private double VL_Venda;

    public String getOID_Produto() {
        return OID_Produto;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public void setOID_Produto(String OID_Produto) {
        this.OID_Produto = OID_Produto;
    }
    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }
    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }
    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }
    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }
    public void setOid_Pessoa_Cliente(String oid_Pessoa_Cliente) {
        this.oid_Pessoa_Cliente = oid_Pessoa_Cliente;
    }
    public String getOid_Pessoa_Cliente() {
        return oid_Pessoa_Cliente;
    }
    public void setNM_Razao_Social_Cliente(String NM_Razao_Social_Cliente) {
        this.NM_Razao_Social_Cliente = NM_Razao_Social_Cliente;
    }
    public String getNM_Razao_Social_Cliente() {
        return NM_Razao_Social_Cliente;
    }
    public void setOID_Produto_Cliente(String OID_Produto_Cliente) {
        this.OID_Produto_Cliente = OID_Produto_Cliente;
    }
    public String getOID_Produto_Cliente() {
        return OID_Produto_Cliente;
    }
    public void setVL_Produto(double VL_Produto) {
        this.VL_Produto = VL_Produto;
    }
    public double getVL_Produto() {
        return VL_Produto;
    }
    public void setPE_Desconto_Avista(double PE_Desconto_Avista) {
        this.PE_Desconto_Avista = PE_Desconto_Avista;
    }
    public double getPE_Desconto_Avista() {
        return PE_Desconto_Avista;
    }
    public void setPE_Desconto_7_Dias(double PE_Desconto_7_Dias) {
        this.PE_Desconto_7_Dias = PE_Desconto_7_Dias;
    }
    public double getPE_Desconto_7_Dias() {
        return PE_Desconto_7_Dias;
    }
    public void setPE_Acrescimo_21_Dias(double PE_Acrescimo_21_Dias) {
        this.PE_Acrescimo_21_Dias = PE_Acrescimo_21_Dias;
    }
    public double getPE_Acrescimo_21_Dias() {
        return PE_Acrescimo_21_Dias;
    }
    public void setPE_Acrescimo_28_Dias(double PE_Acrescimo_28_Dias) {
        this.PE_Acrescimo_28_Dias = PE_Acrescimo_28_Dias;
    }
    public double getPE_Acrescimo_28_Dias() {
        return PE_Acrescimo_28_Dias;
    }
    public void setNM_Tabela(String NM_Tabela) {
        this.NM_Tabela = NM_Tabela;
    }
    public String getNM_Tabela() {
        return NM_Tabela;
    }
    public void setOID_Preco_Produto(long OID_Preco_Produto) {
        this.OID_Preco_Produto = OID_Preco_Produto;
    }
    public long getOID_Preco_Produto() {
        return OID_Preco_Produto;
    }
    public void setDT_Vigencia(String DT_Vigencia) {
        this.DT_Vigencia = DT_Vigencia;
    }
    public String getDT_Vigencia() {
        return DT_Vigencia;
    }
    public void setDT_Validade(String DT_Validade) {
        this.DT_Validade = DT_Validade;
    }
    public String getDT_Validade() {
        return DT_Validade;
    }
    public double getVL_Acrescimo_21_Dias() {
        return VL_Acrescimo_21_Dias;
    }
    public double getVL_Acrescimo_28_Dias() {
        return VL_Acrescimo_28_Dias;
    }
    public double getVL_Desconto_7_Dias() {
        return VL_Desconto_7_Dias;
    }
    public double getVL_Desconto_Avista() {
        return VL_Desconto_Avista;
    }
    public void setVL_Desconto_Avista(double VL_Desconto_Avista) {
        this.VL_Desconto_Avista = VL_Desconto_Avista;
    }
    public void setVL_Desconto_7_Dias(double VL_Desconto_7_Dias) {
        this.VL_Desconto_7_Dias = VL_Desconto_7_Dias;
    }
    public void setVL_Acrescimo_28_Dias(double VL_Acrescimo_28_Dias) {
        this.VL_Acrescimo_28_Dias = VL_Acrescimo_28_Dias;
    }
    public void setVL_Acrescimo_21_Dias(double VL_Acrescimo_21_Dias) {
        this.VL_Acrescimo_21_Dias = VL_Acrescimo_21_Dias;
    }
    public void setNR_CNPJ_CPF_Cliente(String NR_CNPJ_CPF_Cliente) {
        this.NR_CNPJ_CPF_Cliente = NR_CNPJ_CPF_Cliente;
    }
    public String getNR_CNPJ_CPF_Cliente() {
        return NR_CNPJ_CPF_Cliente;
    }
    public double getVL_Venda() {
        return VL_Venda;
    }
    public void setVL_Venda(double venda) {
        VL_Venda = venda;
    }
    public int getOid_Tabela_Venda() {
        return oid_Tabela_Venda;
    }
    public void setOid_Tabela_Venda(int oid_Tabela_Venda) {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
    }
    public double getPE_Acrescimo_30_Dias() {
        return PE_Acrescimo_30_Dias;
    }
    public void setPE_Acrescimo_30_Dias(double acrescimo_30_Dias) {
        PE_Acrescimo_30_Dias = acrescimo_30_Dias;
    }
    public double getVL_Acrescimo_30_Dias() {
        return VL_Acrescimo_30_Dias;
    }
    public void setVL_Acrescimo_30_Dias(double acrescimo_30_Dias) {
        VL_Acrescimo_30_Dias = acrescimo_30_Dias;
    }
    public boolean isCarregaTabela() {
        return carregaTabela;
    }
    public void setCarregaTabela(boolean carregaTabela) {
        this.carregaTabela = carregaTabela;
    }
    public String getDM_Alterado() {
        return DM_Alterado;
    }
    public void setDM_Alterado(String alterado) {
        DM_Alterado = alterado;
    }
    public String getDT_Vigencia_Final() {
        return DT_Vigencia_Final;
    }
    public void setDT_Vigencia_Final(String vigencia_Final) {
        DT_Vigencia_Final = vigencia_Final;
    }
    public boolean isCarregaProduto() {
        return carregaProduto;
    }
    public void setCarregaProduto(boolean carregaProduto) {
        this.carregaProduto = carregaProduto;
    }
    public String getCD_Estrutura_Produto() {
        return CD_Estrutura_Produto;
    }
    public void setCD_Estrutura_Produto(String estrutura_Produto) {
        CD_Estrutura_Produto = estrutura_Produto;
    }
    public String getCD_Produto() {
        return CD_Produto;
    }
    public void setCD_Produto(String produto) {
        CD_Produto = produto;
    }
    public String getNM_Estrutura_Produto() {
        return NM_Estrutura_Produto;
    }
    public void setNM_Estrutura_Produto(String estrutura_Produto) {
        NM_Estrutura_Produto = estrutura_Produto;
    }
    public String getNM_Produto() {
        return NM_Produto;
    }
    public void setNM_Produto(String produto) {
        NM_Produto = produto;
    }
    public int getOid_Estrutura_Produto() {
        return oid_Estrutura_Produto;
    }
    public void setOid_Estrutura_Produto(int oid_Estrutura_Produto) {
        this.oid_Estrutura_Produto = oid_Estrutura_Produto;
    }
    public String getOid_Pessoa_Fornecedor() {
        return oid_Pessoa_Fornecedor;
    }
    public void setOid_Pessoa_Fornecedor(String oid_Pessoa_Fornecedor) {
        this.oid_Pessoa_Fornecedor = oid_Pessoa_Fornecedor;
    }
    public String getCD_Tipo_Produto() {
        return CD_Tipo_Produto;
    }
    public void setCD_Tipo_Produto(String tipo_Produto) {
        CD_Tipo_Produto = tipo_Produto;
    }
    public String getCD_Unidade_Produto() {
        return CD_Unidade_Produto;
    }
    public void setCD_Unidade_Produto(String unidade_Produto) {
        CD_Unidade_Produto = unidade_Produto;
    }
    public String getNM_Tipo_Produto() {
        return NM_Tipo_Produto;
    }
    public void setNM_Tipo_Produto(String tipo_Produto) {
        NM_Tipo_Produto = tipo_Produto;
    }
    public String getNM_Unidade_Produto() {
        return NM_Unidade_Produto;
    }
    public void setNM_Unidade_Produto(String unidade_Produto) {
        NM_Unidade_Produto = unidade_Produto;
    }
    public double getNR_Peso_Medio() {
        return NR_Peso_Medio;
    }
    public void setNR_Peso_Medio(double peso_Medio) {
        NR_Peso_Medio = peso_Medio;
    }
    public double getNR_QT_Caixa() {
        return NR_QT_Caixa;
    }
    public void setNR_QT_Caixa(double caixa) {
        NR_QT_Caixa = caixa;
    }
    public int getOid_Tipo_Produto() {
        return oid_Tipo_Produto;
    }
    public void setOid_Tipo_Produto(int oid_Tipo_Produto) {
        this.oid_Tipo_Produto = oid_Tipo_Produto;
    }
    public int getOid_Unidade_Produto() {
        return oid_Unidade_Produto;
    }
    public void setOid_Unidade_Produto(int oid_Unidade_Produto) {
        this.oid_Unidade_Produto = oid_Unidade_Produto;
    }
    public String getNM_Razao_Social_Fornecedor() {
        return NM_Razao_Social_Fornecedor;
    }
    public void setNM_Razao_Social_Fornecedor(String razao_Social_Fornecedor) {
        NM_Razao_Social_Fornecedor = razao_Social_Fornecedor;
    }
    public String getNR_CNPJ_CPF_Fornecedor() {
        return NR_CNPJ_CPF_Fornecedor;
    }
    public void setNR_CNPJ_CPF_Fornecedor(String fornecedor) {
        NR_CNPJ_CPF_Fornecedor = fornecedor;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public double getNR_QT_Atual() {
        return NR_QT_Atual;
    }
    public void setNR_QT_Atual(double atual) {
        NR_QT_Atual = atual;
    }
    public double getPE_Comissao() {
        return PE_Comissao;
    }
    public void setPE_Comissao(double comissao) {
        PE_Comissao = comissao;
    }
	public double getVL_Preco_Custo() {
		return VL_Preco_Custo;
	}
	public void setVL_Preco_Custo(double preco_Custo) {
		VL_Preco_Custo = preco_Custo;
	}
}