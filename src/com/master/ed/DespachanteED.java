/*
 * Created on 19/01/2005
 */
package com.master.ed;

/**
 * @author André Valadas
 *
 */
public class DespachanteED extends MasterED {

    public DespachanteED() {
    }
    
    public DespachanteED(int oid_Despachante) {
        super();
        this.oid_Despachante = oid_Despachante;
    }
    
    private int oid_Despachante;
    private String CD_Despachante;
    private String DM_Tipo;
    private String oid_Pessoa;

    private String NM_Despachante;

    public String getDescDM_Tipo() {
        if ("D".equals(DM_Tipo)) 
            return "Despachante";
        else if ("C".equals(DM_Tipo)) 
            return "Corretora";
        else return "Não informado!";
    }
    
    public String getCD_Despachante() {
        return CD_Despachante;
    }
    public void setCD_Despachante(String Despachante) {
        CD_Despachante = Despachante;
    }
    public String getNM_Despachante() {
        return NM_Despachante;
    }
    public void setNM_Despachante(String Despachante) {
        NM_Despachante = Despachante;
    }
    public int getOid_Despachante() {
        return oid_Despachante;
    }
    public void setOid_Despachante(int oid_Despachante) {
        this.oid_Despachante = oid_Despachante;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public String getDM_Tipo() {
        return DM_Tipo;
    }
    public void setDM_Tipo(String tipo) {
        DM_Tipo = tipo;
    }
}
