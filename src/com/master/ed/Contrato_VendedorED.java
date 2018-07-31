/*
 * Created on 22/10/2004
 */
package com.master.ed;

import java.util.Date;

import com.master.root.FormataDataBean;

/**
 * @author Andre Valadas
 */
public class Contrato_VendedorED extends MasterED{
	
    public Contrato_VendedorED() {
        super();
    }
    
    public Contrato_VendedorED(int oid_Contrato_Vendedor) {
        super();
        this.oid_Contrato_Vendedor = oid_Contrato_Vendedor;
    }
    
    public Contrato_VendedorED(String oid_Vendedor) {
        super();
        this.oid_Vendedor = oid_Vendedor;
    }
    
    //*** Campos da Tabela
	private int oid_Contrato_Vendedor;
	private String oid_Vendedor;
	private String DM_Tipo_Contrato;
	private String NR_Contrato;
	private int NR_Folha;  
	private Date DT_Contrato;
	private String TX_Observacao;
	
	//*** Complementos
	private String oid_Pessoa;
	private Date DT_Inicial;
	private Date DT_Final;

    public String getDM_Tipo_Contrato() {
        return DM_Tipo_Contrato;
    }
    public void setDM_Tipo_Contrato(String tipo_Contrato) {
        DM_Tipo_Contrato = tipo_Contrato;
    }
    public Date getDT_Contrato() {
        return new FormataDataBean().getFormatDate(DT_Contrato);
    }
    public void setDT_Contrato(Date contrato) {
        DT_Contrato = contrato;
    }
    public String getNR_Contrato() {
        return NR_Contrato;
    }
    public void setNR_Contrato(String contrato) {
        NR_Contrato = contrato;
    }
    public int getNR_Folha() {
        return NR_Folha;
    }
    public void setNR_Folha(int folha) {
        NR_Folha = folha;
    }
    public int getOid_Contrato_Vendedor() {
        return oid_Contrato_Vendedor;
    }
    public void setOid_Contrato_Vendedor(int oid_Contrato_Vendedor) {
        this.oid_Contrato_Vendedor = oid_Contrato_Vendedor;
    }
    public String getOid_Vendedor() {
        return oid_Vendedor;
    }
    public void setOid_Vendedor(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setTX_Observacao(String observacao) {
        TX_Observacao = observacao;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public Date getDT_Final() {
        return new FormataDataBean().getFormatDate(DT_Final);
    }
    public void setDT_Final(Date final1) {
        DT_Final = final1;
    }
    public Date getDT_Inicial() {
        return new FormataDataBean().getFormatDate(DT_Inicial);
    }
    public void setDT_Inicial(Date inicial) {
        DT_Inicial = inicial;
    }
}
