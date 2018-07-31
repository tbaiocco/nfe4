package com.master.ed;

import java.io.Serializable;

/**
 * @author André Valadas
 * @serial Lotes Cheques
 * @serialData 16/08/2005
 */
public class Lote_ChequeED extends MasterED implements Serializable {

    public Lote_ChequeED() {
        super();
    }
    public Lote_ChequeED(int oid_Lote_Cheque) {
        this.oid_Lote_Cheque = oid_Lote_Cheque;
    }
    public Lote_ChequeED(boolean dev) {
        carregaMotivoDev = dev;
    }
    public Lote_ChequeED(int oid_Lote_Cheque, boolean dev) {
        this.oid_Lote_Cheque = oid_Lote_Cheque;
        carregaMotivoDev = dev;
    }
    public Lote_ChequeED(String situacao, int cliente, int recebimento) {
        DM_Situacao = situacao;
        oid_Cheque_Cliente = cliente;
        oid_Lote_Recebimento = recebimento;
    }
    public Lote_ChequeED(String situacao, int cliente, int recebimento, boolean dev) {
        DM_Situacao = situacao;
        oid_Cheque_Cliente = cliente;
        oid_Lote_Recebimento = recebimento;
        carregaMotivoDev = dev;
    }
    
    private int oid_Lote_Cheque;
    private int oid_Lote_Recebimento;
    private int oid_Cheque_Cliente;
    private int oid_Motivo_Devolucao;
    private String DM_Situacao;
    
    private boolean carregaMotivoDev = true;
    public Cheque_ClienteED edCheque = new Cheque_ClienteED();
    public Motivo_DevolucaoED edMotivo = new Motivo_DevolucaoED();
    
    public String getDescDM_Situacao(){
        if ("N".equals(DM_Situacao))
           return "Em espera";     
        else if ("C".equals(DM_Situacao))
           return "Cancelado";     
        else if ("D".equals(DM_Situacao))
           return "Devolvido";     
        else if ("F".equals(DM_Situacao))
           return "Compensado";     
        else return "Não Informada!";
    }
    
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public int getOid_Cheque_Cliente() {
        return oid_Cheque_Cliente;
    }
    public void setOid_Cheque_Cliente(int oid_Cheque_Cliente) {
        this.oid_Cheque_Cliente = oid_Cheque_Cliente;
    }
    public int getOid_Lote_Cheque() {
        return oid_Lote_Cheque;
    }
    public void setOid_Lote_Cheque(int oid_Lote_Cheque) {
        this.oid_Lote_Cheque = oid_Lote_Cheque;
    }
    public int getOid_Lote_Recebimento() {
        return oid_Lote_Recebimento;
    }
    public void setOid_Lote_Recebimento(int oid_Lote_Recebimento) {
        this.oid_Lote_Recebimento = oid_Lote_Recebimento;
    }
    public int getOid_Motivo_Devolucao() {
        return oid_Motivo_Devolucao;
    }
    public void setOid_Motivo_Devolucao(int oid_Motivo_Devolucao) {
        this.oid_Motivo_Devolucao = oid_Motivo_Devolucao;
    }
    public boolean isCarregaMotivoDev() {
        return carregaMotivoDev;
    }
    public void setCarregaMotivoDev(boolean carregaMotivoDev) {
        this.carregaMotivoDev = carregaMotivoDev;
    }
}