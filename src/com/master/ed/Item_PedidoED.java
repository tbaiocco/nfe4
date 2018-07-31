package com.master.ed;

public class Item_PedidoED extends MasterED {

    private String OID_Pessoa;
    private String NM_Razao_Social;
    private String CD_Fornecedor;
    private String NM_Unidade;

    public Item_PedidoED(long item_Pedido, long pedido, String situacao) {
        super();
        OID_Pessoa = "";
        NM_Razao_Social = "";
        CD_Fornecedor = "";
        NM_Unidade = "";
        NR_CNPJ_CPF = "";
        OID_Vendedor = "";
        OID_Unidade = 0;
        OID_Condicao_Pagamento = 0;
        NM_Condicao_Pagamento = "";
        CD_Condicao_Pagamento = "";
        DM_Situacao = situacao;
        DM_Pedido = "";
        DT_Pedido = "";
        HR_Pedido = "";
        DT_Previsao_Entrega = "";
        HR_Previsao_Entrega = "";
        DT_Entrega = "";
        HR_Entrega = "";
        VL_Desconto = 0;
        VL_Produto = 0;
        DM_Frete = "";
        VL_Item = 0;
        OID_Tipo_Documento = 0;
        OID_Pedido = pedido;
        NR_Pedido = 0;
        CD_Unidade = "";
        NM_Fantasia = "";
        VL_Frete = 0;
        OID_Item_Pedido = item_Pedido;
        DM_Quantidade = "U";
        NR_Quantidade = 0;
        NR_QT_Pedido = 0;
        NR_QT_Pendente = 0;
        NR_QT_Atendido = 0;
        NM_Produto = "";
        OID_Produto = "";
        CD_Produto = "";
        DT_Pedido_Inicial = "";
        DT_Pedido_Final = "";
        DT_Previsao_Inicial = "";
        DT_Previsao_Final = "";
        this.acao = "";
        PE_Aliquota_IPI = 0;
        PE_Desconto = 0;
        VL_Unitario = 0;
        VL_Unitario_Tabela = 0;
        VL_IPI = 0;
        PE_Margem_Contribuicao = 0;
        VL_Margem_Contribuicao = 0;
        NR_QT_Listar = "";
        this.oid_Nota_Fiscal = "";
        NR_Nota_Fiscal = 0;
        this.oid_Preco_Produto = 0;
        this.oid_Tipo_Produto = 0;
        this.oid_Promocao_Produto = 0;
        VL_Promocao = 0;
    }

    public Item_PedidoED() {
        super();
    }

    public Item_PedidoED(long oid_pedido) {
        super();
        OID_Pedido = oid_pedido;
    }

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
    private long OID_Item_Pedido;
    private double NR_QT_Pedido;
    private double NR_QT_Troca;
    private double NR_QT_Pendente;
    private double NR_QT_Atendido;
    private double VL_Troca;
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
    private double VL_Unitario;
    private double VL_Unitario_Tabela;
    private double VL_IPI;
    private double PE_Margem_Contribuicao;
    private double VL_Margem_Contribuicao;

    private String NR_QT_Listar;

    private String oid_Nota_Fiscal;
    private long NR_Nota_Fiscal;

    private int oid_Preco_Produto;
    private int oid_Tipo_Produto;
    private int oid_Promocao_Produto;
    private int oid_Item_Nota_Fiscal;
    private int oid_Localizacao;
    private double VL_Promocao;
    private double NR_Peso_Real;
    private double NR_Peso_Pedido;
    
    private double NR_Quantidade;
    private String DM_Quantidade;

    public String getDesc_DM_Situacao() {
        if ("C".equals(this.DM_Situacao))
            return "Cancelado";
        else if ("N".equals(this.DM_Situacao))
            return "Em Aberto";
        else if ("F".equals(this.DM_Situacao))
            return "Finalizado";
        else if ("A".equals(this.DM_Situacao))
            return "Aprovado";
        else
            return "Não Informado!";
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

    public void setOID_Item_Pedido(long OID_Item_Pedido) {
        this.OID_Item_Pedido = OID_Item_Pedido;
    }

    public long getOID_Item_Pedido() {
        return OID_Item_Pedido;
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

    public double getNR_QT_Pendente() {
        return NR_QT_Pendente;
    }

    public void setNR_QT_Pendente(double NR_QT_Pendente) {
        this.NR_QT_Pendente = NR_QT_Pendente;
    }

    public double getNR_QT_Atendido() {
        return NR_QT_Atendido;
    }

    public void setNR_QT_Atendido(double NR_QT_Atendido) {
        this.NR_QT_Atendido = NR_QT_Atendido;
    }

    public void setNR_QT_Listar(String NR_QT_Listar) {
        this.NR_QT_Listar = NR_QT_Listar;
    }

    public String getNR_QT_Listar() {
        return NR_QT_Listar;
    }

    public String getOid_Nota_Fiscal() {
        return oid_Nota_Fiscal;
    }

    public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
    }

    public long getNR_Nota_Fiscal() {
        return NR_Nota_Fiscal;
    }

    public void setNR_Nota_Fiscal(long nota_Fiscal) {
        NR_Nota_Fiscal = nota_Fiscal;
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

    public int getOid_Preco_Produto() {
        return oid_Preco_Produto;
    }

    public void setOid_Preco_Produto(int oid_Preco_Produto) {
        this.oid_Preco_Produto = oid_Preco_Produto;
    }

    public int getOid_Tipo_Produto() {
        return oid_Tipo_Produto;
    }

    public void setOid_Tipo_Produto(int oid_Tipo_Produto) {
        this.oid_Tipo_Produto = oid_Tipo_Produto;
    }

    public int getOid_Promocao_Produto() {
        return oid_Promocao_Produto;
    }

    public void setOid_Promocao_Produto(int oid_Promocao_Produto) {
        this.oid_Promocao_Produto = oid_Promocao_Produto;
    }

    public double getVL_Unitario_Tabela() {
        return VL_Unitario_Tabela;
    }

    public void setVL_Unitario_Tabela(double unitario_Tabela) {
        VL_Unitario_Tabela = unitario_Tabela;
    }

    public double getVL_Promocao() {
        return VL_Promocao;
    }

    public void setVL_Promocao(double promocao) {
        VL_Promocao = promocao;
    }

    public double getNR_Peso_Real() {
        return NR_Peso_Real;
    }

    public void setNR_Peso_Real(double peso_Real) {
        NR_Peso_Real = peso_Real;
    }
    public int getOid_Item_Nota_Fiscal() {
        return oid_Item_Nota_Fiscal;
    }
    public void setOid_Item_Nota_Fiscal(int oid_Item_Nota_Fiscal) {
        this.oid_Item_Nota_Fiscal = oid_Item_Nota_Fiscal;
    }
    public double getNR_Peso_Pedido() {
        return NR_Peso_Pedido;
    }
    public void setNR_Peso_Pedido(double peso_Pedido) {
        NR_Peso_Pedido = peso_Pedido;
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
    public int getOid_Localizacao() {
        return oid_Localizacao;
    }
    public void setOid_Localizacao(int oid_Localizacao) {
        this.oid_Localizacao = oid_Localizacao;
    }

    
    public double getVL_Troca() {
        return VL_Troca;
    }

    
    public void setVL_Troca(double troca) {
        VL_Troca = troca;
    }

    
    public double getNR_QT_Troca() {
        return NR_QT_Troca;
    }

    
    public void setNR_QT_Troca(double troca) {
        NR_QT_Troca = troca;
    }
}