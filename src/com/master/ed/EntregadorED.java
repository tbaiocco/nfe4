/*
 * Created on 11/11/2004
 */
package com.master.ed;

/**
 * @author André Valadas
 *
 */
public class EntregadorED extends MasterED {

    private int oid_Entregador;
    private String CD_Entregador;
    private String oid_Pessoa;
    private String oid_Veiculo;

    private String NM_Entregador;

    public EntregadorED() {
    }
    
    public EntregadorED(int oid_Entregador) {
        super();
        this.oid_Entregador = oid_Entregador;
    }
    
    public String getCD_Entregador() {
        return CD_Entregador;
    }
    public void setCD_Entregador(String Entregador) {
        CD_Entregador = Entregador;
    }
    public String getNM_Entregador() {
        return NM_Entregador;
    }
    public void setNM_Entregador(String Entregador) {
        NM_Entregador = Entregador;
    }
    public int getOid_Entregador() {
        return oid_Entregador;
    }
    public void setOid_Entregador(int oid_Entregador) {
        this.oid_Entregador = oid_Entregador;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public String getOid_Veiculo() {
        return oid_Veiculo;
    }
    public void setOid_Veiculo(String oid_Veiculo) {
        this.oid_Veiculo = oid_Veiculo;
    }
}