package com.master.ed;

import java.io.Serializable;

import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;

/**
 * @author André Valadas
 */

public class EntregaED extends MasterED implements Serializable {

    public EntregaED() {
        super();
    }
    public EntregaED(int oid_Entrega) {
        this.oid_Entrega = oid_Entrega;
    }
    public EntregaED(int oid_Entrega, int oid_Unidade) {
        this.oid_Entrega = oid_Entrega;
        this.oid_Unidade = oid_Unidade;
    }
    public EntregaED(boolean entregador, boolean unidade, boolean veiculo) {
        carregaEntregador = entregador;
        carregaUnidade = unidade;
        carregaVeiculo = veiculo;
    }
    public EntregaED(int oid_Entrega, boolean entregador, boolean unidade, boolean veiculo) {
        this.oid_Entrega = oid_Entrega;
        carregaEntregador = entregador;
        carregaUnidade = unidade;
        carregaVeiculo = veiculo;
    }
    
    public EntregadorED edEntregador = new EntregadorED();
    public UnidadeBean edUnidade = new UnidadeBean();
    public VeiculoBean edVeiculo = new VeiculoBean();
    
    private boolean carregaEntregador = true;
    private boolean carregaUnidade = true;
    private boolean carregaVeiculo = true;
    
    private int oid_Entrega;
    private int oid_Unidade;
    private int oid_Entregador;
    private String oid_Veiculo;
    private int NR_Entrega;
    private String DT_Entrega;
    private String DT_Acerto; //data do sistema no acerto na volta
    private String DM_Situacao;
    
    private double VL_Vendas; //soma das Notas Fiscais
    private double VL_Cobranca; //soma das NF cheque ou a vista
    private double VL_Devolucao_Cancelamento; //soma das nf Dev/Canc
    private double VL_Dinheiro; //informado no Acerto
    private double VL_Moeda;
    private double VL_Cheque; //soma dos cheques informados
    private double VL_Vale;
    private double VL_Saldo; //VL_COBRANCA – VL_DEV – VL_DINHEIRO – VL_MOEDA – VL_CHEQUE – VL_VALE
    
    //*** Para Filtro
    private String DT_Entrega_Final;
    private String DT_Acerto_Final;
    
    public String getDescDM_Situacao() {
        if ("N".equals(DM_Situacao))
            return "Em Rota";
        else if ("F".equals(DM_Situacao))
            return "Finalizado";
        else return "Não informado!";
    }
    public String getDT_Acerto() {
        return DT_Acerto;
    }
    public String getDT_Entrega() {
        return DT_Entrega;
    }
    public int getNR_Entrega() {
        return NR_Entrega;
    }
    public int getOid_Entrega() {
        return oid_Entrega;
    }
    public int getOid_Entregador() {
        return oid_Entregador;
    }
    public int getOid_Unidade() {
        return oid_Unidade;
    }
    public String getOid_Veiculo() {
        return oid_Veiculo;
    }
    public double getVL_Cheque() {
        return VL_Cheque;
    }
    public double getVL_Cobranca() {
        return VL_Cobranca;
    }
    public double getVL_Devolucao_Cancelamento() {
        return VL_Devolucao_Cancelamento;
    }
    public double getVL_Dinheiro() {
        return VL_Dinheiro;
    }
    public double getVL_Moeda() {
        return VL_Moeda;
    }
    public double getVL_Saldo() {
        return VL_Saldo;
    }
    public double getVL_Vale() {
        return VL_Vale;
    }
    public double getVL_Vendas() {
        return VL_Vendas;
    }
    public void setDT_Acerto(String acerto) {
        DT_Acerto = acerto;
    }
    public void setDT_Entrega(String entrega) {
        DT_Entrega = entrega;
    }
    public void setNR_Entrega(int entrega) {
        NR_Entrega = entrega;
    }
    public void setOid_Entrega(int oid_Entrega) {
        this.oid_Entrega = oid_Entrega;
    }
    public void setOid_Entregador(int oid_Entregador) {
        this.oid_Entregador = oid_Entregador;
    }
    public void setOid_Unidade(int oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }
    public void setOid_Veiculo(String oid_Veiculo) {
        this.oid_Veiculo = oid_Veiculo;
    }
    public void setVL_Cheque(double cheque) {
        VL_Cheque = cheque;
    }
    public void setVL_Cobranca(double cobranca) {
        VL_Cobranca = cobranca;
    }
    public void setVL_Devolucao_Cancelamento(double devolucao_Cancelamento) {
        VL_Devolucao_Cancelamento = devolucao_Cancelamento;
    }
    public void setVL_Dinheiro(double dinheiro) {
        VL_Dinheiro = dinheiro;
    }
    public void setVL_Moeda(double moeda) {
        VL_Moeda = moeda;
    }
    public void setVL_Saldo(double saldo) {
        VL_Saldo = saldo;
    }
    public void setVL_Vale(double vale) {
        VL_Vale = vale;
    }
    public void setVL_Vendas(double vendas) {
        VL_Vendas = vendas;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public String getDT_Acerto_Final() {
        return DT_Acerto_Final;
    }
    public String getDT_Entrega_Final() {
        return DT_Entrega_Final;
    }
    public void setDT_Acerto_Final(String acerto_Final) {
        DT_Acerto_Final = acerto_Final;
    }
    public void setDT_Entrega_Final(String entrega_Final) {
        DT_Entrega_Final = entrega_Final;
    }
    public boolean isCarregaEntregador() {
        return carregaEntregador;
    }
    public void setCarregaEntregador(boolean carregaEntregador) {
        this.carregaEntregador = carregaEntregador;
    }
    public boolean isCarregaUnidade() {
        return carregaUnidade;
    }
    public void setCarregaUnidade(boolean carregaUnidade) {
        this.carregaUnidade = carregaUnidade;
    }
    public boolean isCarregaVeiculo() {
        return carregaVeiculo;
    }
    public void setCarregaVeiculo(boolean carregaVeiculo) {
        this.carregaVeiculo = carregaVeiculo;
    }
}