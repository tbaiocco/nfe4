package com.master.ed;

/**
 * @author André Valadas
 */

public class PedidoED extends MasterED {

    public PedidoED() {
        super();
    }
    public PedidoED(long pedido) {
        super();
        OID_Pedido = pedido;
    }
    public PedidoED(String pessoa, long pedido) {
        super();
        OID_Pessoa = pessoa;
        OID_Pedido = pedido;
    }
    public PedidoED(long pedido, String dm_pedido) {
        DM_Pedido = dm_pedido;
        OID_Pedido = pedido;
    }
    
    public Modelo_Nota_FiscalED edModelo = new Modelo_Nota_FiscalED();
    
    private String OID_Pessoa;
    private String NR_CNPJ_CPF;
    private String NM_Razao_Social;
    private String DM_Situacao_Cliente;    
    private String CD_Fornecedor;
    private String NM_Unidade;
    private String OID_Produto;
    private String OID_Vendedor;
    private String oid_Pessoa_Distribuidor;
    private String dt_Stamp;
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
    private double VL_Total_Desconto;
    private double VL_Total_Produto;
    private String DM_Frete;
    private double VL_Total_Item;
    private long OID_Tipo_Documento;
    private long OID_Pedido;
    private long NR_Pedido;
    private double VL_Frete;
    private String CD_Unidade;
    private String DT_Pedido_Inicial;
    private String DT_Pedido_Final;
    private String DT_Previsao_Inicial;
    private String DT_Previsao_Final;
    private String acao;
    private String NM_Comprador;
    private String NM_Liberador;
    private String NM_Vendedor;
    private String CD_Vendedor;
    private long OID_Tipo_Pedido;
    private String TX_Observacao;
    private String NM_Tipo_Pedido;
    private String CD_Tipo_Pedido;
    private double VL_Total_IPI;
    private double VL_Total_Pedido;
    private double VL_Total_Troca;
    private double VL_Total_Margem;
    private String DM_Relatorio;
    private String DM_Meio_Pagamento;
    private String DT_Faturamento;
    private String DT_Aprovacao;
    private String HR_Aprovacao;
    private int oid_Entregador;
    private String oid_Veiculo;
    private long oid_Natureza_Operacao;
    private String NR_Pedido_Palm;
    private String DM_Critica_Financeira;
    private String DM_Critica_Estoque;
    private int NR_Nota_Fiscal;
    private String NM_Serie;
    private int oid_Modelo_Nota_Fiscal;
    private int oid_Tabela_Venda;

    //*** Campos implementados
    private String DM_Nota_Fiscal;
    private String CD_Distribuidor;
    private String NM_Distribuidor;
    private String CD_Entregador;
    private String NM_Entregador;
    private String CD_Natureza_Operacao;
    private String NM_Natureza_Operacao;
    private long NR_Pedido_Final;
    private String Numeros_Pedido;
    private String DM_Sem_Entregador;
    private String DM_Filtrar_ACF; //*** Aprovado, Cancelado, Finalizado
    
    private boolean updatePrecosByCond;
    private boolean updatePrecosByTabela;

    public String getDesc_DM_Situacao() {
        if ("C".equals(this.DM_Situacao))
            return "Cancelado";
        else if ("N".equals(this.DM_Situacao))
            return "Em Aberto";
        else if ("F".equals(this.DM_Situacao))
            return "Finalizado";
        else if ("A".equals(this.DM_Situacao))
            return "Aprovado";
        else return "Não Informado!";
    }
    public String getDescDM_Critica_Financeira() {
        if ("A".equals(this.DM_Critica_Financeira))
            return "Aprovada";
        else if ("N".equals(this.DM_Critica_Financeira))
            return "Não Verificada";
        else if ("S".equals(this.DM_Critica_Financeira))
            return "Crédito Bloqueado";
        else if ("L".equals(this.DM_Critica_Financeira))
            return "Crédito Liberado";
        else return "Não informada!";
    }
    public String getDescDM_Critica_Estoque() {
        if ("A".equals(this.DM_Critica_Estoque))
            return "Aprovado";
        else if ("N".equals(this.DM_Critica_Estoque))
            return "Não Verificado";
        else if ("S".equals(this.DM_Critica_Estoque))
            return "Possui Ocorrências";
        else if ("L".equals(this.DM_Critica_Estoque))
            return "Estoque Liberado";
        else return "Não informado!";
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

    public String getCD_Vendedor() {
        return CD_Vendedor;
    }

    public void setCD_Vendedor(String vendedor) {
        CD_Vendedor = vendedor;
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

    public void setVL_Total_Item(double VL_Total_Item) {
        this.VL_Total_Item = VL_Total_Item;
    }

    public double getVL_Total_Item() {
        return VL_Total_Item;
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

    public void setVL_Total_Desconto(double VL_Total_Desconto) {
        this.VL_Total_Desconto = VL_Total_Desconto;
    }

    public double getVL_Total_Desconto() {
        return VL_Total_Desconto;
    }

    public void setVL_Total_Produto(double VL_Total_Produto) {
        this.VL_Total_Produto = VL_Total_Produto;
    }

    public double getVL_Total_Produto() {
        return VL_Total_Produto;
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

    public void setVL_Frete(double VL_Frete) {
        this.VL_Frete = VL_Frete;
    }

    public double getVL_Frete() {
        return VL_Frete;
    }

    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }

    public String getCD_Unidade() {
        return CD_Unidade;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getDT_Pedido_Final() {
        return DT_Pedido_Final;
    }

    public String getDT_Pedido_Inicial() {
        return DT_Pedido_Inicial;
    }

    public void setDT_Pedido_Final(String DT_Pedido_Final) {
        this.DT_Pedido_Final = DT_Pedido_Final;
    }

    public void setDT_Pedido_Inicial(String DT_Pedido_Inicial) {
        this.DT_Pedido_Inicial = DT_Pedido_Inicial;
    }

    public void setDT_Previsao_Final(String DT_Previsao_Final) {
        this.DT_Previsao_Final = DT_Previsao_Final;
    }

    public void setDT_Previsao_Inicial(String DT_Previsao_Inicial) {
        this.DT_Previsao_Inicial = DT_Previsao_Inicial;
    }

    public void setDt_Stamp(String dt_Stamp) {
        this.dt_Stamp = dt_Stamp;
    }

    public String getDT_Previsao_Final() {
        return DT_Previsao_Final;
    }

    public String getDT_Previsao_Inicial() {
        return DT_Previsao_Inicial;
    }

    public String getDt_Stamp() {
        return dt_Stamp;
    }

    public void setNM_Comprador(String NM_Comprador) {
        this.NM_Comprador = NM_Comprador;
    }

    public String getNM_Comprador() {
        return NM_Comprador;
    }

    public void setNM_Liberador(String NM_Liberador) {
        this.NM_Liberador = NM_Liberador;
    }

    public String getNM_Liberador() {
        return NM_Liberador;
    }

    public void setNM_Vendedor(String NM_Vendedor) {
        this.NM_Vendedor = NM_Vendedor;
    }

    public String getNM_Vendedor() {
        return NM_Vendedor;
    }

    public void setOID_Tipo_Pedido(long OID_Tipo_Pedido) {
        this.OID_Tipo_Pedido = OID_Tipo_Pedido;
    }

    public long getOID_Tipo_Pedido() {
        return OID_Tipo_Pedido;
    }

    public void setTX_Observacao(String TX_Observacao) {
        this.TX_Observacao = TX_Observacao;
    }

    public String getTX_Observacao() {
        return TX_Observacao;
    }

    public void setNM_Tipo_Pedido(String NM_Tipo_Pedido) {
        this.NM_Tipo_Pedido = NM_Tipo_Pedido;
    }

    public String getNM_Tipo_Pedido() {
        return NM_Tipo_Pedido;
    }

    public void setCD_Tipo_Pedido(String CD_Tipo_Pedido) {
        this.CD_Tipo_Pedido = CD_Tipo_Pedido;
    }

    public String getCD_Tipo_Pedido() {
        return CD_Tipo_Pedido;
    }

    public void setVL_Total_IPI(double VL_Total_IPI) {
        this.VL_Total_IPI = VL_Total_IPI;
    }

    public double getVL_Total_IPI() {
        return VL_Total_IPI;
    }

    public void setVL_Total_Pedido(double VL_Total_Pedido) {
        this.VL_Total_Pedido = VL_Total_Pedido;
    }

    public double getVL_Total_Pedido() {
        return VL_Total_Pedido;
    }

    public String getOID_Produto() {
        return OID_Produto;
    }

    public void setOID_Produto(String OID_Produto) {
        this.OID_Produto = OID_Produto;
    }

    public void setDM_Relatorio(String DM_Relatorio) {
        this.DM_Relatorio = DM_Relatorio;
    }

    public String getDM_Relatorio() {
        return DM_Relatorio;
    }

    public String getOid_Pessoa_Distribuidor() {
        return oid_Pessoa_Distribuidor;
    }

    public void setOid_Pessoa_Distribuidor(String oid_Pessoa_Distribuidor) {
        this.oid_Pessoa_Distribuidor = oid_Pessoa_Distribuidor;
    }

    public String getCD_Distribuidor() {
        return CD_Distribuidor;
    }

    public void setCD_Distribuidor(String distribuidor) {
        CD_Distribuidor = distribuidor;
    }

    public String getNM_Distribuidor() {
        return NM_Distribuidor;
    }

    public void setNM_Distribuidor(String distribuidor) {
        NM_Distribuidor = distribuidor;
    }

    public String getDM_Meio_Pagamento() {
        return DM_Meio_Pagamento;
    }

    public void setDM_Meio_Pagamento(String meio_Pagamento) {
        DM_Meio_Pagamento = meio_Pagamento;
    }

    public String getDT_Faturamento() {
        return DT_Faturamento;
    }

    public void setDT_Faturamento(String faturamento) {
        DT_Faturamento = faturamento;
    }

    public double getVL_Total_Troca() {
        return VL_Total_Troca;
    }

    public void setVL_Total_Troca(double total_Troca) {
        VL_Total_Troca = total_Troca;
    }

    public String getDT_Aprovacao() {
        return DT_Aprovacao;
    }

    public void setDT_Aprovacao(String aprovacao) {
        DT_Aprovacao = aprovacao;
    }

    public String getHR_Aprovacao() {
        return HR_Aprovacao;
    }

    public void setHR_Aprovacao(String aprovacao) {
        HR_Aprovacao = aprovacao;
    }

    public double getVL_Total_Margem() {
        return VL_Total_Margem;
    }

    public void setVL_Total_Margem(double total_Margem) {
        VL_Total_Margem = total_Margem;
    }

    public int getOid_Entregador() {
        return oid_Entregador;
    }

    public void setOid_Entregador(int oid_Entregador) {
        this.oid_Entregador = oid_Entregador;
    }

    public long getOid_Natureza_Operacao() {
        return oid_Natureza_Operacao;
    }

    public void setOid_Natureza_Operacao(long oid_Natureza_Operacao) {
        this.oid_Natureza_Operacao = oid_Natureza_Operacao;
    }

    public String getCD_Entregador() {
        return CD_Entregador;
    }

    public void setCD_Entregador(String entregador) {
        CD_Entregador = entregador;
    }

    public String getCD_Natureza_Operacao() {
        return CD_Natureza_Operacao;
    }

    public void setCD_Natureza_Operacao(String natureza_Operacao) {
        CD_Natureza_Operacao = natureza_Operacao;
    }

    public String getNM_Entregador() {
        return NM_Entregador;
    }

    public void setNM_Entregador(String entregador) {
        NM_Entregador = entregador;
    }

    public String getNM_Natureza_Operacao() {
        return NM_Natureza_Operacao;
    }

    public void setNM_Natureza_Operacao(String natureza_Operacao) {
        NM_Natureza_Operacao = natureza_Operacao;
    }

    public String getNR_Pedido_Palm() {
        return NR_Pedido_Palm;
    }

    public void setNR_Pedido_Palm(String pedido_Palm) {
        NR_Pedido_Palm = pedido_Palm;
    }
    public boolean isCancelado() {
        return ("C".equals(DM_Situacao));
    }
    public String getDM_Critica_Estoque() {
        return DM_Critica_Estoque;
    }
    public String getDM_Critica_Financeira() {
        return DM_Critica_Financeira;
    }
    public void setDM_Critica_Estoque(String critica_Estoque) {
        DM_Critica_Estoque = critica_Estoque;
    }
    public void setDM_Critica_Financeira(String critica_Financeira) {
        DM_Critica_Financeira = critica_Financeira;
    }
    public String getDM_Sem_Entregador() {
        return DM_Sem_Entregador;
    }
    public void setDM_Sem_Entregador(String sem_Entregador) {
        DM_Sem_Entregador = sem_Entregador;
    }
    public long getNR_Pedido_Final() {
        return NR_Pedido_Final;
    }
    public void setNR_Pedido_Final(long pedido_Final) {
        NR_Pedido_Final = pedido_Final;
    }
    public String getDM_Filtrar_ACF() {
        return DM_Filtrar_ACF;
    }
    public void setDM_Filtrar_ACF(String filtrar_ACF) {
        DM_Filtrar_ACF = filtrar_ACF;
    }
    public String getNM_Serie() {
        return NM_Serie;
    }
    public int getNR_Nota_Fiscal() {
        return NR_Nota_Fiscal;
    }
    public void setNM_Serie(String serie) {
        NM_Serie = serie;
    }
    public void setNR_Nota_Fiscal(int nota_Fiscal) {
        NR_Nota_Fiscal = nota_Fiscal;
    }
    public String getDM_Nota_Fiscal() {
        return DM_Nota_Fiscal;
    }
    public void setDM_Nota_Fiscal(String nota_Fiscal) {
        DM_Nota_Fiscal = nota_Fiscal;
    }
    public int getOid_Modelo_Nota_Fiscal() {
        return oid_Modelo_Nota_Fiscal;
    }
    public void setOid_Modelo_Nota_Fiscal(int oid_Modelo_Nota_Fiscal) {
        this.oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
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
    public String getDM_Situacao_Cliente() {
        return DM_Situacao_Cliente;
    }
    public void setDM_Situacao_Cliente(String situacao_Cliente) {
        DM_Situacao_Cliente = situacao_Cliente;
    }
    public String getNumeros_Pedido() {
        return Numeros_Pedido;
    }
    public void setNumeros_Pedido(String numeros_Pedido) {
        Numeros_Pedido = numeros_Pedido;
    }
    public String getOid_Veiculo() {
        return oid_Veiculo;
    }
    public void setOid_Veiculo(String oid_Veiculo) {
        this.oid_Veiculo = oid_Veiculo;
    }
}