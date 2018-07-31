package com.master.ed;

public class ProdutoED extends MasterED implements Cloneable {

    public ProdutoED(String produto) {
        super();
        OID_Produto = produto;
    }
    
    public Object clone() throws CloneNotSupportedException {
        ProdutoED ed = new ProdutoED();        
        ed.setCD_Produto(CD_Produto);
        ed.setNM_Produto(NM_Produto);
        ed.setUsuario_Stamp ( Usuario_Stamp);
        ed.setDt_Stamp ( Dt_Stamp);
        ed.setDm_Stamp ( Dm_Stamp);
        ed.setOid(oid);
        ed.setOID_Deposito ( OID_Deposito);
        ed.setOid_Pessoa(oid_Pessoa);
        ed.setNM_Razao_Social ( NM_Razao_Social);
        ed.setNM_Deposito ( NM_Deposito);
        ed.setOID_Produto (  OID_Produto);
        ed.setOid_Estrutura_Produto ( Oid_Estrutura_Produto);
        ed.setOid_Unidade_Produto ( Oid_Unidade_Produto);
        ed.setNR_Peso_Medio ( NR_Peso_Medio);
        ed.setNR_Peso_Liquido ( NR_Peso_Liquido);
        ed.setNR_QT_Caixa ( NR_QT_Caixa);
        ed.setCD_Fornecedor ( CD_Fornecedor);
        ed.setNM_Unidade_Produto ( NM_Unidade_Produto);
        ed.setCD_Unidade_Produto ( CD_Unidade_Produto);
        ed.setNM_Estrutura_Produto ( NM_Estrutura_Produto);
        ed.setCD_Estrutura_Produto ( CD_Estrutura_Produto);
        ed.setNM_Descricao_Caixa ( NM_Descricao_Caixa);
        ed.setDm_Estrutura_Produto( dm_Estrutura_Produto);
        ed.setOid_Nivel_Produto1(oid_Nivel_Produto1);
        ed.setNm_Nivel_Produto1( nm_Nivel_Produto1);
        ed.setCd_Nivel_Produto1( cd_Nivel_Produto1);
        ed.setDm_Nivel_Produto1 ( dm_Nivel_Produto1);
        ed.setOid_Nivel_Produto2 ( oid_Nivel_Produto2);
        ed.setNm_Nivel_Produto2 ( nm_Nivel_Produto2);
        ed.setCd_Nivel_Produto2 ( cd_Nivel_Produto2);
        ed.setDm_Nivel_Produto2 ( dm_Nivel_Produto2);
        ed.setOid_Nivel_Produto3 ( oid_Nivel_Produto3);
        ed.setNm_Nivel_Produto3 ( nm_Nivel_Produto3);
        ed.setCd_Nivel_Produto3 ( cd_Nivel_Produto3);
        ed.setDm_Nivel_Produto3 ( dm_Nivel_Produto3);
        ed.setOid_Nivel_Produto4 ( oid_Nivel_Produto4);
        ed.setNm_Nivel_Produto4 ( nm_Nivel_Produto4);
        ed.setCd_Nivel_Produto4 ( cd_Nivel_Produto4);
        ed.setDm_Nivel_Produto4 ( dm_Nivel_Produto4);
        ed.setOid_Nivel_Produto5 ( oid_Nivel_Produto5);
        ed.setNm_Nivel_Produto5 ( nm_Nivel_Produto5);
        ed.setCd_Nivel_Produto5 ( cd_Nivel_Produto5);
        ed.setDm_Nivel_Produto5 ( dm_Nivel_Produto5);
        ed.setNR_CNPJ_CPF ( NR_CNPJ_CPF);
        ed.setOid_Pessoa_Cliente ( oid_Pessoa_Cliente);
        ed.setNM_Razao_Social_Cliente ( NM_Razao_Social_Cliente);
        ed.setNM_Cidade_Cliente ( NM_Cidade_Cliente);
        ed.setOID_Produto_Cliente ( OID_Produto_Cliente);
        ed.setOID_Tipo_Produto ( OID_Tipo_Produto);
        ed.setNM_Tipo_Produto ( NM_Tipo_Produto);
        ed.setCD_Tipo_Produto ( CD_Tipo_Produto);
        ed.setOid_Estrutura_Fornecedor ( oid_Estrutura_Fornecedor);
        ed.setOid_Classificacao_Fiscal ( oid_Classificacao_Fiscal);
        ed.setOid_Situacao_Tributaria ( oid_Situacao_Tributaria);
        ed.setOid_Mix ( oid_Mix);
        ed.setOid_Pessoa_Distribuidor ( oid_Pessoa_Distribuidor);
        ed.setCD_Estrutura_Fornecedor ( CD_Estrutura_Fornecedor);
        ed.setNM_Estrutura_Fornecedor ( NM_Estrutura_Fornecedor);
        ed.setCD_Reduzido ( CD_Reduzido);
        ed.setCD_Fiscal ( CD_Fiscal);
        ed.setCD_Situacao_Tributaria ( CD_Situacao_Tributaria);
        ed.setNM_Situacao_Tributaria ( NM_Situacao_Tributaria);
        ed.setCD_Mix ( CD_Mix);
        ed.setNM_Mix ( NM_Mix);
        ed.setCD_Distribuidor ( CD_Distribuidor);
        ed.setNM_Distribuidor ( NM_Distribuidor);
        ed.setDT_Pedido ( DT_Pedido);
        ed.setVL_Unitario ( VL_Unitario);
        ed.setOid_Condicao_Pagamento ( oid_Condicao_Pagamento);
        ed.setOid_Setor_Produto ( oid_Setor_Produto);
        ed.setNR_QT_Estoque ( NR_QT_Estoque);
        ed.setDM_Pesagem ( DM_Pesagem);
        ed.setPesagem ( Pesagem);
        ed.setDM_Situacao ( DM_Situacao);
        ed.setNOT_DM_Situacao ( NOT_DM_Situacao);
        ed.setPossui_Estoque ( Possui_Estoque);
        ed.setData_Vencida ( Data_Vencida);
        ed.setExiste_Tabela ( Existe_Tabela);
        ed.setOid_Tipo_Estoque ( oid_Tipo_Estoque);
        ed.setOid_Localizacao ( oid_Localizacao);
        ed.edPreco = edPreco;
        ed.edSetor = edSetor;
        return ed;
    }
    
    
    public ProdutoED(String oid_Pessoa_Distribuidor, int oid_Tabela_Venda) {
        this.oid_Pessoa_Distribuidor = oid_Pessoa_Distribuidor;
        this.edPreco.setOid_Tabela_Venda(oid_Tabela_Venda);
    }
    public ProdutoED() {
        super();
        oid_Nivel_Produto1 = 0;
        nm_Nivel_Produto1 = "";
        cd_Nivel_Produto1 = "";
        dm_Nivel_Produto1 = "";
        oid_Nivel_Produto2 = 0;
        nm_Nivel_Produto2 = "";
        cd_Nivel_Produto2 = "";
        dm_Nivel_Produto2 = "";
        oid_Nivel_Produto3 = 0;
        nm_Nivel_Produto3 = "";
        cd_Nivel_Produto3 = "";
        dm_Nivel_Produto3 = "";
        oid_Nivel_Produto4 = 0;
        nm_Nivel_Produto4 = "";
        cd_Nivel_Produto4 = "";
        dm_Nivel_Produto4 = "";
        oid_Nivel_Produto5 = 0;
        nm_Nivel_Produto5 = "";
        cd_Nivel_Produto5 = "";
        dm_Nivel_Produto5 = "";
    }
    
    public ProdutoED(boolean loadPreco) {
        this.carregaPreco = loadPreco;
        oid_Nivel_Produto1 = 0;
        nm_Nivel_Produto1 = "";
        cd_Nivel_Produto1 = "";
        dm_Nivel_Produto1 = "";
        oid_Nivel_Produto2 = 0;
        nm_Nivel_Produto2 = "";
        cd_Nivel_Produto2 = "";
        dm_Nivel_Produto2 = "";
        oid_Nivel_Produto3 = 0;
        nm_Nivel_Produto3 = "";
        cd_Nivel_Produto3 = "";
        dm_Nivel_Produto3 = "";
        oid_Nivel_Produto4 = 0;
        nm_Nivel_Produto4 = "";
        cd_Nivel_Produto4 = "";
        dm_Nivel_Produto4 = "";
        oid_Nivel_Produto5 = 0;
        nm_Nivel_Produto5 = "";
        cd_Nivel_Produto5 = "";
        dm_Nivel_Produto5 = "";
    }
    
    private String CD_Produto;
    private String NM_Produto;
    private String Usuario_Stamp;
    private String Dt_Stamp;
    private String Dm_Stamp;
    private int oid;
    private int OID_Deposito;
    private String oid_Pessoa;
    private String NM_Razao_Social;
    private String NM_Deposito;
    private String OID_Produto;
    private int Oid_Estrutura_Produto;
    private int Oid_Unidade_Produto;
    private double NR_Peso_Medio;
    private double NR_Peso_Liquido;
    private int NR_QT_Caixa;
    private String CD_Fornecedor;
    private String NM_Unidade_Produto;
    private String CD_Unidade_Produto;
    private String NM_Estrutura_Produto;
    private String CD_Estrutura_Produto;
    private String NM_Descricao_Caixa;

    private String dm_Estrutura_Produto;

    private int oid_Nivel_Produto1;
    private String nm_Nivel_Produto1;
    private String cd_Nivel_Produto1;
    private String dm_Nivel_Produto1;

    private int oid_Nivel_Produto2;
    private String nm_Nivel_Produto2;
    private String cd_Nivel_Produto2;
    private String dm_Nivel_Produto2;

    private int oid_Nivel_Produto3;
    private String nm_Nivel_Produto3;
    private String cd_Nivel_Produto3;
    private String dm_Nivel_Produto3;

    private int oid_Nivel_Produto4;
    private String nm_Nivel_Produto4;
    private String cd_Nivel_Produto4;
    private String dm_Nivel_Produto4;

    private int oid_Nivel_Produto5;
    private String nm_Nivel_Produto5;
    private String cd_Nivel_Produto5;
    private String dm_Nivel_Produto5;
    private String NR_CNPJ_CPF;
    private String oid_Pessoa_Cliente;
    private String NM_Razao_Social_Cliente;
    private String NM_Cidade_Cliente;
    private String OID_Produto_Cliente;
    private int OID_Tipo_Produto;
    private String NM_Tipo_Produto;
    private String CD_Tipo_Produto;

    private int oid_Estrutura_Fornecedor;
    private int oid_Classificacao_Fiscal;
    private int oid_Situacao_Tributaria;
    private int oid_Mix;
    private String oid_Pessoa_Distribuidor;
    //*** Implementados
    private String CD_Estrutura_Fornecedor;
    private String NM_Estrutura_Fornecedor;
    private String CD_Reduzido;
    private String CD_Fiscal;
    private String CD_Situacao_Tributaria;
    private String NM_Situacao_Tributaria;
    private String CD_Mix;
    private String NM_Mix;
    private String CD_Distribuidor;
    private String NM_Distribuidor;
    private String DT_Pedido;
    private double VL_Unitario;
    private int oid_Condicao_Pagamento;
    private int oid_Setor_Produto;

    private double NR_QT_Estoque;
    private String DM_Pesagem; // Unidade Produto
    private boolean Pesagem;
    private String DM_Situacao;
    private String NOT_DM_Situacao;

    // Produtos de Venda
    private boolean Possui_Estoque;
    private boolean Data_Vencida;
    private boolean Existe_Tabela;
    private int oid_Tipo_Estoque;
    private int oid_Localizacao;
    private String CD_Localizacao;

    boolean carregaPreco = true;
    public Preco_ProdutoED edPreco = new Preco_ProdutoED();
    public Setor_ProdutoED edSetor = new Setor_ProdutoED();

    public String getCD_Produto() {
        return CD_Produto;
    }

    public String getNM_Produto() {
        return NM_Produto;
    }

    public int getOid() {
        return oid;
    }

    public void setCD_Produto(String CD_Produto) {
        this.CD_Produto = CD_Produto;
    }

    public void setNM_Produto(String NM_Produto) {
        this.NM_Produto = NM_Produto;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getDm_Stamp() {
        return Dm_Stamp;
    }

    public String getDt_Stamp() {
        return Dt_Stamp;
    }

    public String getUsuario_Stamp() {
        return Usuario_Stamp;
    }

    public void setDm_Stamp(String Dm_Stamp) {
        this.Dm_Stamp = Dm_Stamp;
    }

    public void setDt_Stamp(String Dt_Stamp) {
        this.Dt_Stamp = Dt_Stamp;
    }

    public void setUsuario_Stamp(String Usuario_Stamp) {
        this.Usuario_Stamp = Usuario_Stamp;
    }

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

    public int getOID_Deposito() {
        return OID_Deposito;
    }

    public void setOID_Deposito(int OID_Deposito) {
        this.OID_Deposito = OID_Deposito;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public String getNM_Deposito() {
        return NM_Deposito;
    }

    public void setNM_Deposito(String NM_Deposito) {
        this.NM_Deposito = NM_Deposito;
    }

    public void setOid_Estrutura_Produto(int Oid_Estrutura_Produto) {
        this.Oid_Estrutura_Produto = Oid_Estrutura_Produto;
    }

    public int getOid_Estrutura_Produto() {
        return Oid_Estrutura_Produto;
    }

    public void setOid_Unidade_Produto(int Oid_Unidade_Produto) {
        this.Oid_Unidade_Produto = Oid_Unidade_Produto;
    }

    public int getOid_Unidade_Produto() {
        return Oid_Unidade_Produto;
    }

    public void setNR_Peso_Medio(double NR_Peso_Medio) {
        this.NR_Peso_Medio = NR_Peso_Medio;
    }

    public double getNR_Peso_Medio() {
        return NR_Peso_Medio;
    }

    public void setNR_Peso_Liquido(double NR_Peso_Liquido) {
        this.NR_Peso_Liquido = NR_Peso_Liquido;
    }

    public double getNR_Peso_Liquido() {
        return NR_Peso_Liquido;
    }

    public void setNR_QT_Caixa(int NR_QT_Caixa) {
        this.NR_QT_Caixa = NR_QT_Caixa;
    }

    public int getNR_QT_Caixa() {
        return NR_QT_Caixa;
    }

    public void setCD_Fornecedor(String CD_Fornecedor) {
        this.CD_Fornecedor = CD_Fornecedor;
    }

    public String getCD_Fornecedor() {
        return CD_Fornecedor;
    }

    public void setNM_Unidade_Produto(String NM_Unidade_Produto) {
        this.NM_Unidade_Produto = NM_Unidade_Produto;
    }

    public String getNM_Unidade_Produto() {
        return NM_Unidade_Produto;
    }

    public void setCD_Unidade_Produto(String CD_Unidade_Produto) {
        this.CD_Unidade_Produto = CD_Unidade_Produto;
    }

    public String getCD_Unidade_Produto() {
        return CD_Unidade_Produto;
    }

    public void setNM_Estrutura_Produto(String NM_Estrutura_Produto) {
        this.NM_Estrutura_Produto = NM_Estrutura_Produto;
    }

    public String getNM_Estrutura_Produto() {
        return NM_Estrutura_Produto;
    }

    public void setCD_Estrutura_Produto(String CD_Estrutura_Produto) {
        this.CD_Estrutura_Produto = CD_Estrutura_Produto;
    }

    public String getCD_Estrutura_Produto() {
        return CD_Estrutura_Produto;
    }

    public void setNM_Descricao_Caixa(String NM_Descricao_Caixa) {
        this.NM_Descricao_Caixa = NM_Descricao_Caixa;
    }

    public String getNM_Descricao_Caixa() {
        return NM_Descricao_Caixa;
    }

    public String getCd_Nivel_Produto2() {
        return cd_Nivel_Produto2;
    }

    public String getCd_Nivel_Produto3() {
        return cd_Nivel_Produto3;
    }

    public String getCd_Nivel_Produto4() {
        return cd_Nivel_Produto4;
    }

    public String getCd_Nivel_Produto5() {
        return cd_Nivel_Produto5;
    }

    public void setCd_Nivel_Produto5(String cd_Nivel_Produto5) {
        this.cd_Nivel_Produto5 = cd_Nivel_Produto5;
    }

    public void setCd_Nivel_Produto4(String cd_Nivel_Produto4) {
        this.cd_Nivel_Produto4 = cd_Nivel_Produto4;
    }

    public void setCd_Nivel_Produto3(String cd_Nivel_Produto3) {
        this.cd_Nivel_Produto3 = cd_Nivel_Produto3;
    }

    public void setCd_Nivel_Produto2(String cd_Nivel_Produto2) {
        this.cd_Nivel_Produto2 = cd_Nivel_Produto2;
    }

    public void setCd_Nivel_Produto1(String cd_Nivel_Produto1) {
        this.cd_Nivel_Produto1 = cd_Nivel_Produto1;
    }

    public void setDm_Estrutura_Produto(String dm_Estrutura_Produto) {
        this.dm_Estrutura_Produto = dm_Estrutura_Produto;
    }

    public void setDm_Nivel_Produto1(String dm_Nivel_Produto1) {
        this.dm_Nivel_Produto1 = dm_Nivel_Produto1;
    }

    public void setDm_Nivel_Produto2(String dm_Nivel_Produto2) {
        this.dm_Nivel_Produto2 = dm_Nivel_Produto2;
    }

    public void setDm_Nivel_Produto3(String dm_Nivel_Produto3) {
        this.dm_Nivel_Produto3 = dm_Nivel_Produto3;
    }

    public void setDm_Nivel_Produto4(String dm_Nivel_Produto4) {
        this.dm_Nivel_Produto4 = dm_Nivel_Produto4;
    }

    public void setDm_Nivel_Produto5(String dm_Nivel_Produto5) {
        this.dm_Nivel_Produto5 = dm_Nivel_Produto5;
    }

    public String getDm_Estrutura_Produto() {
        return dm_Estrutura_Produto;
    }

    public String getDm_Nivel_Produto1() {
        return dm_Nivel_Produto1;
    }

    public String getDm_Nivel_Produto2() {
        return dm_Nivel_Produto2;
    }

    public String getDm_Nivel_Produto3() {
        return dm_Nivel_Produto3;
    }

    public String getDm_Nivel_Produto4() {
        return dm_Nivel_Produto4;
    }

    public String getDm_Nivel_Produto5() {
        return dm_Nivel_Produto5;
    }

    public String getNm_Nivel_Produto1() {
        return nm_Nivel_Produto1;
    }

    public String getNm_Nivel_Produto2() {
        return nm_Nivel_Produto2;
    }

    public void setNm_Nivel_Produto1(String nm_Nivel_Produto1) {
        this.nm_Nivel_Produto1 = nm_Nivel_Produto1;
    }

    public void setNm_Nivel_Produto2(String nm_Nivel_Produto2) {
        this.nm_Nivel_Produto2 = nm_Nivel_Produto2;
    }

    public void setNm_Nivel_Produto3(String nm_Nivel_Produto3) {
        this.nm_Nivel_Produto3 = nm_Nivel_Produto3;
    }

    public String getNm_Nivel_Produto3() {
        return nm_Nivel_Produto3;
    }

    public void setNm_Nivel_Produto4(String nm_Nivel_Produto4) {
        this.nm_Nivel_Produto4 = nm_Nivel_Produto4;
    }

    public void setNm_Nivel_Produto5(String nm_Nivel_Produto5) {
        this.nm_Nivel_Produto5 = nm_Nivel_Produto5;
    }

    public String getNm_Nivel_Produto4() {
        return nm_Nivel_Produto4;
    }

    public String getNm_Nivel_Produto5() {
        return nm_Nivel_Produto5;
    }

    public void setOid_Nivel_Produto1(int oid_Nivel_Produto1) {
        this.oid_Nivel_Produto1 = oid_Nivel_Produto1;
    }

    public void setOid_Nivel_Produto2(int oid_Nivel_Produto2) {
        this.oid_Nivel_Produto2 = oid_Nivel_Produto2;
    }

    public void setOid_Nivel_Produto3(int oid_Nivel_Produto3) {
        this.oid_Nivel_Produto3 = oid_Nivel_Produto3;
    }

    public void setOid_Nivel_Produto4(int oid_Nivel_Produto4) {
        this.oid_Nivel_Produto4 = oid_Nivel_Produto4;
    }

    public int getOid_Nivel_Produto1() {
        return oid_Nivel_Produto1;
    }

    public int getOid_Nivel_Produto2() {
        return oid_Nivel_Produto2;
    }

    public int getOid_Nivel_Produto3() {
        return oid_Nivel_Produto3;
    }

    public int getOid_Nivel_Produto4() {
        return oid_Nivel_Produto4;
    }

    public void setOid_Nivel_Produto5(int oid_Nivel_Produto5) {
        this.oid_Nivel_Produto5 = oid_Nivel_Produto5;
    }

    public int getOid_Nivel_Produto5() {
        return oid_Nivel_Produto5;
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

    public void setNM_Cidade_Cliente(String NM_Cidade_Cliente) {
        this.NM_Cidade_Cliente = NM_Cidade_Cliente;
    }

    public String getNM_Cidade_Cliente() {
        return NM_Cidade_Cliente;
    }

    public void setOID_Produto_Cliente(String OID_Produto_Cliente) {
        this.OID_Produto_Cliente = OID_Produto_Cliente;
    }

    public String getOID_Produto_Cliente() {
        return OID_Produto_Cliente;
    }

    public void setOID_Tipo_Produto(int OID_Tipo_Produto) {
        this.OID_Tipo_Produto = OID_Tipo_Produto;
    }

    public int getOID_Tipo_Produto() {
        return OID_Tipo_Produto;
    }

    public void setNM_Tipo_Produto(String NM_Tipo_Produto) {
        this.NM_Tipo_Produto = NM_Tipo_Produto;
    }

    public String getNM_Tipo_Produto() {
        return NM_Tipo_Produto;
    }

    public void setCD_Tipo_Produto(String CD_Tipo_Produto) {
        this.CD_Tipo_Produto = CD_Tipo_Produto;
    }

    public String getCD_Tipo_Produto() {
        return CD_Tipo_Produto;
    }

    /**
     * @return Returns the oid_Classificacao_Fiscal.
     */
    public int getOid_Classificacao_Fiscal() {
        return oid_Classificacao_Fiscal;
    }

    /**
     * @param oid_Classificacao_Fiscal
     *            The oid_Classificacao_Fiscal to set.
     */
    public void setOid_Classificacao_Fiscal(int oid_Classificacao_Fiscal) {
        this.oid_Classificacao_Fiscal = oid_Classificacao_Fiscal;
    }

    /**
     * @return Returns the oid_Estrutura_Fornecedor.
     */
    public int getOid_Estrutura_Fornecedor() {
        return oid_Estrutura_Fornecedor;
    }

    /**
     * @param oid_Estrutura_Fornecedor
     *            The oid_Estrutura_Fornecedor to set.
     */
    public void setOid_Estrutura_Fornecedor(int oid_Estrutura_Fornecedor) {
        this.oid_Estrutura_Fornecedor = oid_Estrutura_Fornecedor;
    }

    /**
     * @return Returns the oid_Situacao_Tributaria.
     */
    public int getOid_Situacao_Tributaria() {
        return oid_Situacao_Tributaria;
    }

    /**
     * @param oid_Situacao_Tributaria
     *            The oid_Situacao_Tributaria to set.
     */
    public void setOid_Situacao_Tributaria(int oid_Situacao_Tributaria) {
        this.oid_Situacao_Tributaria = oid_Situacao_Tributaria;
    }

    /**
     * @return Returns the cD_Estrutura_Fornecedor.
     */
    public String getCD_Estrutura_Fornecedor() {
        return CD_Estrutura_Fornecedor;
    }

    /**
     * @param estrutura_Vendedor
     *            The cD_Estrutura_Fornecedor to set.
     */
    public void setCD_Estrutura_Fornecedor(String estrutura_Vendedor) {
        CD_Estrutura_Fornecedor = estrutura_Vendedor;
    }

    /**
     * @return Returns the cD_Fiscal.
     */
    public String getCD_Fiscal() {
        return CD_Fiscal;
    }

    /**
     * @param fiscal
     *            The cD_Fiscal to set.
     */
    public void setCD_Fiscal(String fiscal) {
        CD_Fiscal = fiscal;
    }

    /**
     * @return Returns the cD_Reduzido.
     */
    public String getCD_Reduzido() {
        return CD_Reduzido;
    }

    /**
     * @param reduzido
     *            The cD_Reduzido to set.
     */
    public void setCD_Reduzido(String reduzido) {
        CD_Reduzido = reduzido;
    }

    /**
     * @return Returns the cD_Situacao_Tributaria.
     */
    public String getCD_Situacao_Tributaria() {
        return CD_Situacao_Tributaria;
    }

    /**
     * @param situacao_Tributaria
     *            The cD_Situacao_Tributaria to set.
     */
    public void setCD_Situacao_Tributaria(String situacao_Tributaria) {
        CD_Situacao_Tributaria = situacao_Tributaria;
    }

    /**
     * @return Returns the nM_Estrutura_Fornecedor.
     */
    public String getNM_Estrutura_Fornecedor() {
        return NM_Estrutura_Fornecedor;
    }

    /**
     * @param estrutura_Vendedor
     *            The nM_Estrutura_Fornecedor to set.
     */
    public void setNM_Estrutura_Fornecedor(String estrutura_Vendedor) {
        NM_Estrutura_Fornecedor = estrutura_Vendedor;
    }

    /**
     * @return Returns the nM_Situacao_Tributaria.
     */
    public String getNM_Situacao_Tributaria() {
        return NM_Situacao_Tributaria;
    }

    /**
     * @param situacao_Tributaria
     *            The nM_Situacao_Tributaria to set.
     */
    public void setNM_Situacao_Tributaria(String situacao_Tributaria) {
        NM_Situacao_Tributaria = situacao_Tributaria;
    }

    public String getOid_Pessoa_Distribuidor() {
        return oid_Pessoa_Distribuidor;
    }

    public void setOid_Pessoa_Distribuidor(String oid_Pessoa_Distribuidor) {
        this.oid_Pessoa_Distribuidor = oid_Pessoa_Distribuidor;
    }

    public int getOid_Mix() {
        return oid_Mix;
    }

    public void setOid_Mix(int oid_Mix) {
        this.oid_Mix = oid_Mix;
    }

    public String getCD_Distribuidor() {
        return CD_Distribuidor;
    }

    public void setCD_Distribuidor(String distribuidor) {
        CD_Distribuidor = distribuidor;
    }

    public String getCD_Mix() {
        return CD_Mix;
    }

    public void setCD_Mix(String mix) {
        CD_Mix = mix;
    }

    public String getNM_Distribuidor() {
        return NM_Distribuidor;
    }

    public void setNM_Distribuidor(String distribuidor) {
        NM_Distribuidor = distribuidor;
    }

    public String getNM_Mix() {
        return NM_Mix;
    }

    public void setNM_Mix(String mix) {
        NM_Mix = mix;
    }

    public String getDT_Pedido() {
        return DT_Pedido;
    }

    public void setDT_Pedido(String pedido) {
        DT_Pedido = pedido;
    }

    public double getVL_Unitario() {
        return VL_Unitario;
    }

    public void setVL_Unitario(double unitario) {
        VL_Unitario = unitario;
    }

    public int getOid_Condicao_Pagamento() {
        return oid_Condicao_Pagamento;
    }

    public void setOid_Condicao_Pagamento(int oid_Condicao_Pagamento) {
        this.oid_Condicao_Pagamento = oid_Condicao_Pagamento;
    }

    public boolean isPossui_Estoque() {
        return Possui_Estoque;
    }

    public void setPossui_Estoque(boolean possui_Estoque) {
        Possui_Estoque = possui_Estoque;
    }

    public boolean isData_Vencida() {
        return Data_Vencida;
    }

    public void setData_Vencida(boolean data_Vencida) {
        Data_Vencida = data_Vencida;
    }

    public boolean isExiste_Tabela() {
        return Existe_Tabela;
    }

    public void setExiste_Tabela(boolean existe_Tabela) {
        Existe_Tabela = existe_Tabela;
    }

    public double getNR_QT_Estoque() {
        return NR_QT_Estoque;
    }

    public void setNR_QT_Estoque(double estoque) {
        NR_QT_Estoque = estoque;
    }

    public String getDM_Pesagem() {
        return DM_Pesagem;
    }

    public void setDM_Pesagem(String pesagem) {
        DM_Pesagem = pesagem;
    }
    public boolean isPesagem() {
        return Pesagem;
    }
    public void setPesagem(boolean pesagem) {
        Pesagem = pesagem;
    }
    public String getNOT_DM_Situacao() {
        return NOT_DM_Situacao;
    }
    public void setNOT_DM_Situacao(String situacao) {
        NOT_DM_Situacao = situacao;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public int getOid_Tipo_Estoque() {
        return oid_Tipo_Estoque;
    }
    public void setOid_Tipo_Estoque(int oid_Tipo_Estoque) {
        this.oid_Tipo_Estoque = oid_Tipo_Estoque;
    }
    public int getOid_Setor_Produto() {
        return oid_Setor_Produto;
    }
    public void setOid_Setor_Produto(int oid_Setor_Produto) {
        this.oid_Setor_Produto = oid_Setor_Produto;
    }
    public int getOid_Localizacao() {
        return oid_Localizacao;
    }
    public void setOid_Localizacao(int oid_Localizacao) {
        this.oid_Localizacao = oid_Localizacao;
    }

	public String getCD_Localizacao() {
		return CD_Localizacao;
	}

	public void setCD_Localizacao(String localizacao) {
		CD_Localizacao = localizacao;
	}
}