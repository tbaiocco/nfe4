package com.master.ed;

/**
 * @author Andr� Valadas
 * - Formas de Pagamentos
 */
public class Forma_PagamentoED extends MasterED {

    public Forma_PagamentoED() {
        super();
    }
    public Forma_PagamentoED(int oid_Forma_Pagamento) {
        this.oid_Forma_Pagamento = oid_Forma_Pagamento;
    }
    public Forma_PagamentoED(int oid_Forma_Pagamento, int oid_Condicao_Pagamento) {
        this.oid_Forma_Pagamento = oid_Forma_Pagamento;
        this.oid_Condicao_Pagamento = oid_Condicao_Pagamento;
    }
    //*** Objeto Condi��o de Pagamento
    
    private int oid_Forma_Pagamento;
    private int oid_Condicao_Pagamento;
    private String DM_Tipo_Pagamento;
    private String DM_Tipo_Operacao;//Compra, Venda, Compra e Venda, Bloqueado
    private String DM_Tipo_Cobranca;//Na entrega, Faturado
    
    public static String getDescDM_Tipo_Pagamento(String dmTipoPagamento) {
        if ("1".equals(dmTipoPagamento)) {
            return "Boleto";
        } else if ("2".equals(dmTipoPagamento)) {
            return "Cheque";
        } else if ("3".equals(dmTipoPagamento)) {
            return "Dep�sito CC";
        } else if ("4".equals(dmTipoPagamento)) {
            return "Carteira";
        } else if ("5".equals(dmTipoPagamento)) {
            return "Dinheiro";
        } else return "N�o informado!";
    }
    public String getDescDM_Tipo_Pagamento() {
        return getDescDM_Tipo_Pagamento(DM_Tipo_Pagamento);
    }
    
    public static String getDescDM_Tipo_Operacao(String dmTipoOperacao) {
        if ("C".equals(dmTipoOperacao)) {
            return "Compra";
        } else if ("V".equals(dmTipoOperacao)) {
            return "Venda";
        } else if ("CV".equals(dmTipoOperacao)) {
            return "Compra/Venda";
        } else if ("B".equals(dmTipoOperacao)) {
            return "Bloqueada";
        } else return "N�o informado!";
    }
    public String getDescDM_Tipo_Operacao() {
        return getDescDM_Tipo_Operacao(DM_Tipo_Operacao);
    }
    
    public static String getDescDM_Tipo_Cobranca(String dmTipoCobranca) {
        if ("E".equals(dmTipoCobranca)) {
            return "Na Entrega";
        } else if ("F".equals(dmTipoCobranca)) {
            return "Faturado";
        } else return "N�o informado!";
    }
    public String getDescDM_Tipo_Cobranca() {
        return getDescDM_Tipo_Cobranca(DM_Tipo_Cobranca);
    }
    
    public String getDM_Tipo_Cobranca() {
        return DM_Tipo_Cobranca;
    }
    public void setDM_Tipo_Cobranca(String tipo_Cobranca) {
        DM_Tipo_Cobranca = tipo_Cobranca;
    }
    public String getDM_Tipo_Operacao() {
        return DM_Tipo_Operacao;
    }
    public void setDM_Tipo_Operacao(String tipo_Operacao) {
        DM_Tipo_Operacao = tipo_Operacao;
    }
    public String getDM_Tipo_Pagamento() {
        return DM_Tipo_Pagamento;
    }
    public void setDM_Tipo_Pagamento(String tipo_Pagamento) {
        DM_Tipo_Pagamento = tipo_Pagamento;
    }
    public int getOid_Condicao_Pagamento() {
        return oid_Condicao_Pagamento;
    }
    public void setOid_Condicao_Pagamento(int oid_Condicao_Pagamento) {
        this.oid_Condicao_Pagamento = oid_Condicao_Pagamento;
    }
    public int getOid_Forma_Pagamento() {
        return oid_Forma_Pagamento;
    }
    public void setOid_Forma_Pagamento(int oid_Forma_Pagamento) {
        this.oid_Forma_Pagamento = oid_Forma_Pagamento;
    }
}
