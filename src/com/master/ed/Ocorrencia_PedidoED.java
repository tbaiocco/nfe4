/*
 * Created on 27/01/2005
 */
package com.master.ed;

import com.master.root.PessoaBean;

/**
 * @author André Valadas
 */
public class Ocorrencia_PedidoED extends MasterED {

    /** Financeiro
    1 - Se cliente tem Crédito liberado; (Cliente DM_Credito); 
    2 - Se Valor limite de crédito; Valor Limite - (VL_Pedidos em aberto + vl_Saldo das duplicatas);
    3 - Se Quantidade de Duplicatas em aberto; (Criar campo em Clientes);
    4 - Se Data de Validade do Cadastro não estiver vencida;
    5 - Se DM_Tipo_cobrança em Clientes for avista(V);
    6 - Se não existe duplicata, venda somente a vista;
    7 - Se Vl do Pedido for menor q valor mín. compra(vl_minimo_compra) do cliente
    8 - Se Vl do Pedido for menor q valor mín. de faturamento(FT_VL_Minimo_Fatura) venda somente a vista;
    9 - Se cliente possuir duplicata protestada*****
    */
    public static String TX_CREDITO_BLOQUEADO = "Crédito BLOQUEADO";
    public static String TX_DESATIVADO = "Cliente DESATIVADO";
    public static String TX_LIMITE_CREDITO = "Limite de Crédito ESGOTADO";
    public static String TX_LIMITE_DUPLICATAS = "Nº excedente de Duplicatas em ABERTO";
    public static String TX_DT_CADASTRO_VENCIDA = "Data da Validade do Cadastro VENCIDA";
    public static String TX_COBRANCA_AVISTA = "Tipo de Cobrança deve ser A VISTA";
    public static String TX_PRIMEIRA_COMPRA = "PRIMEIRA COMPRA deve ser A VISTA";
    public static String TX_VL_MINIMO_COMPRA = "Valor inferior ao valor MÍNIMO de compra";
    public static String TX_VL_MAXIMO_COMPRA = "Valor superior ao valor MÁXIMO para compra";
    public static String TX_VENDA_AVISTA = "Valor inferior ao valor MÍNIMO de compra a PRAZO";
    public static String TX_DUPLICATA_PROTESTADA = "Cliente possui Duplicatas PROTESTADAS";
    public static String TX_CHEQUE_DEVOLVIDO = "Cliente INADIMPLENTE";
    /** Estoque
    1 - Se não há estoque para produto;
    2 - Se produto é do tipo pesagem e ainda não foi informado o seu Peso_Real(informado no estoque);
    3 - Se Pedido não possui Itens
    */
    public static String TX_FALTA_ESTOQUE = "Produto com Quantidade Insuficiente em Estoque";
    public static String TX_NECESSITA_PESAGEM = "Produto deve ser Pesado em Estoque, Peso Real não informado!";
    public static String TX_SEM_ITENS = "Pedido não possui Itens(Produtos)!";
    
    public Ocorrencia_PedidoED() {
        super();
    }
    public Ocorrencia_PedidoED(int oid_Ocorrencia_Pedido) {
        super();
        this.oid_Ocorrencia_Pedido = oid_Ocorrencia_Pedido;
    }
    public Ocorrencia_PedidoED(String motivo) {
        DM_Motivo = motivo;
    }
    public Ocorrencia_PedidoED(int oid_Ocorrencia_Pedido, String motivo) {
        this.oid_Ocorrencia_Pedido = oid_Ocorrencia_Pedido;
        DM_Motivo = motivo;
    }
    public Ocorrencia_PedidoED(int oid_Pedido, String oid_Pessoa, String observacao, String motivo) {
        super();
        this.oid_Pedido = oid_Pedido;
        this.oid_Pessoa = oid_Pessoa;
        TX_Observacao = observacao;
        DM_Motivo = motivo;
    }
    
    public PedidoED edPedido = new PedidoED();
    public PessoaBean edPessoa = new PessoaBean();
    
    private int oid_Ocorrencia_Pedido;
    private int oid_Pedido;
    private String oid_Pessoa;
    private String DT_Ocorrencia_Pedido;
    private String HR_Ocorrencia_Pedido;
    private String TX_Observacao;
    private String DM_Motivo;
    
    public String getDescDM_Motivo() {
        if ("F".equals(DM_Motivo))
            return "Financeiro";
        else if ("E".equals(DM_Motivo))
            return "Estoque";
        else if ("L".equals(DM_Motivo))
            return "Liberado";
        else return "Não informado!";
    }
    public String getDM_Motivo() {
        return DM_Motivo;
    }
    public String getDT_Ocorrencia_Pedido() {
        return DT_Ocorrencia_Pedido;
    }
    public String getHR_Ocorrencia_Pedido() {
        return HR_Ocorrencia_Pedido;
    }
    public int getOid_Ocorrencia_Pedido() {
        return oid_Ocorrencia_Pedido;
    }
    public int getOid_Pedido() {
        return oid_Pedido;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setDM_Motivo(String motivo) {
        DM_Motivo = motivo;
    }
    public void setDT_Ocorrencia_Pedido(String ocorrencia_Pedido) {
        DT_Ocorrencia_Pedido = ocorrencia_Pedido;
    }
    public void setHR_Ocorrencia_Pedido(String ocorrencia_Pedido) {
        HR_Ocorrencia_Pedido = ocorrencia_Pedido;
    }
    public void setOid_Ocorrencia_Pedido(int oid_Ocorrencia_Pedido) {
        this.oid_Ocorrencia_Pedido = oid_Ocorrencia_Pedido;
    }
    public void setOid_Pedido(int oid_Pedido) {
        this.oid_Pedido = oid_Pedido;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public void setTX_Observacao(String observacao) {
        TX_Observacao = observacao;
    }
}
