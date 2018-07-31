package com.master.ed;

import java.io.Serializable;

/**
 * @author André Valadas
 * @serial Documentos Entregas
 * @serialData 31/03/2005
 */

public class Documento_EntregaED extends MasterED implements Serializable {

    public Documento_EntregaED() {
        super();
    }
    public Documento_EntregaED(int oid_Documento_Entrega) {
        this.oid_Documento_Entrega = oid_Documento_Entrega;
    }
    
    public EntregaED edEntrega = new EntregaED();
    public Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaED();
    
    private int oid_Documento_Entrega;
    private int oid_Entrega;
    private String oid_Nota_Fiscal;
    private String DM_Situacao; //(Em Rota Entrega/ Entregue/ Devolução / Cancelamento)
    private String DM_Forma_Recebimento;
    private String TX_Observacao;
    
    private String notInDM_Situacao;
    
    public String getDescDM_Situacao() {
        if ("N".equals(DM_Situacao))
            return "Em Rota";
        else if ("S".equals(DM_Situacao))
            return "Receb. s/ canhoto";
        else if ("C".equals(DM_Situacao))
            return "Receb. c/ canhoto";
        else if ("D".equals(DM_Situacao))
            return "Devolvido";
        else return "Não informado!";
    }
    
    public String getDescDM_Forma_Recebimento() {
        
        if ("1".equals(DM_Forma_Recebimento)) {
            return "Boleto";
        } else if ("2".equals(DM_Forma_Recebimento)) {
            return "Cheque";
        } else if ("3".equals(DM_Forma_Recebimento)) {
            return "Depósito CC";
        } else if ("4".equals(DM_Forma_Recebimento)) {
            return "Carteira";
        } else if ("5".equals(DM_Forma_Recebimento)) {
            return "Dinheiro";
        } else return "Não informado!";
    }
    
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public int getOid_Documento_Entrega() {
        return oid_Documento_Entrega;
    }
    public int getOid_Entrega() {
        return oid_Entrega;
    }
    public String getOid_Nota_Fiscal() {
        return oid_Nota_Fiscal;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public void setOid_Documento_Entrega(int oid_Documento_Entrega) {
        this.oid_Documento_Entrega = oid_Documento_Entrega;
    }
    public void setOid_Entrega(int oid_Entrega) {
        this.oid_Entrega = oid_Entrega;
    }
    public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
    }
    public void setTX_Observacao(String observacao) {
        TX_Observacao = observacao;
    }
    public String getDM_Forma_Recebimento() {
        return DM_Forma_Recebimento;
    }
    public void setDM_Forma_Recebimento(String forma_Recebimento) {
        DM_Forma_Recebimento = forma_Recebimento;
    }
    public String getNotInDM_Situacao() {
        return notInDM_Situacao;
    }
    public void setNotInDM_Situacao(String notInDM_Situacao) {
        this.notInDM_Situacao = notInDM_Situacao;
    }
}