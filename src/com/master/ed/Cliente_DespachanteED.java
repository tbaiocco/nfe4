/*
 * Created on 19/01/2005
 */
package com.master.ed;

import com.master.root.ClienteBean;

/**
 * @author André Valadas
 *
 */
public class Cliente_DespachanteED extends MasterED {

    private int oid_Cliente_Despachante;
    private int oid_Despachante;
    private String oid_Cliente;
    private String DM_Situacao;
    public DespachanteED DespachanteED = new DespachanteED();
    public ClienteBean edCliente = new ClienteBean();
    private int oid_Aduana;
    private String CD_Aduana;
	private String NM_Aduana;
    
    public Cliente_DespachanteED() {
    }
    
    public Cliente_DespachanteED(int oid_Cliente_Despachante) {
        super();
        this.oid_Cliente_Despachante = oid_Cliente_Despachante;
    }
    
    public String getDescDM_Situacao(){
        if ("L".equals(DM_Situacao) || "A".equals(DM_Situacao))
            return "LIBERADO";
        else if ("B".equals(DM_Situacao) || "C".equals(DM_Situacao))
            return "BLOQUEADO";
        else return "NÃO INFORMADA";
    }
    public DespachanteED getDespachanteED() {
        return DespachanteED;
    }
    public void setDespachanteED(DespachanteED despachanteED) {
        DespachanteED = despachanteED;
    }
    public int getOid_Cliente_Despachante() {
        return oid_Cliente_Despachante;
    }
    public void setOid_Cliente_Despachante(int oid_Cliente_Despachante) {
        this.oid_Cliente_Despachante = oid_Cliente_Despachante;
    }
    public int getOid_Despachante() {
        return oid_Despachante;
    }
    public void setOid_Despachante(int oid_Despachante) {
        this.oid_Despachante = oid_Despachante;
    }
    public String getOid_Cliente() {
        return oid_Cliente;
    }
    public void setOid_Cliente(String oid_Cliente) {
        this.oid_Cliente = oid_Cliente;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public ClienteBean getEdCliente() {
        return edCliente;
    }
    public void setEdCliente(ClienteBean edCliente) {
        this.edCliente = edCliente;
    }
    public int getOid_Aduana() {
        return oid_Aduana;
    }
    public void setOid_Aduana(int oid_Aduana) {
        this.oid_Aduana = oid_Aduana;
    }
    public String getCD_Aduana() {
        return CD_Aduana;
    }
    public void setCD_Aduana(String aduana) {
        CD_Aduana = aduana;
    }
    public String getNM_Aduana() {
        return NM_Aduana;
    }
    public void setNM_Aduana(String aduana) {
        NM_Aduana = aduana;
    }
}