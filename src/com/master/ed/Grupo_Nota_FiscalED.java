package com.master.ed;

/**
 * @author André Valadas
 * @serial Grupos Notas Fiscais
 * @serialData 17/06/2005
 */
public class Grupo_Nota_FiscalED extends MasterED {

    public Grupo_Nota_FiscalED() {
    }
    public Grupo_Nota_FiscalED(int oid_Grupo_Nota_Fiscal) {
        this.oid_Grupo_Nota_Fiscal = oid_Grupo_Nota_Fiscal;
    }
    
    private int oid_Grupo_Nota_Fiscal;
    private String CD_Grupo_Nota_Fiscal;
    private String NM_Grupo_Nota_Fiscal;
    private String NM_Modelos;
    
    public String getCD_Grupo_Nota_Fiscal() {
        return CD_Grupo_Nota_Fiscal;
    }
    public int getOid_Grupo_Nota_Fiscal() {
        return oid_Grupo_Nota_Fiscal;
    }
    public void setCD_Grupo_Nota_Fiscal(String Grupo_Nota_Fiscal) {
        CD_Grupo_Nota_Fiscal = Grupo_Nota_Fiscal;
    }
    public void setOid_Grupo_Nota_Fiscal(int oid_Grupo_Nota_Fiscal) {
        this.oid_Grupo_Nota_Fiscal = oid_Grupo_Nota_Fiscal;
    }
    public String getNM_Grupo_Nota_Fiscal() {
        return NM_Grupo_Nota_Fiscal;
    }
    public void setNM_Grupo_Nota_Fiscal(String Grupo_Nota_Fiscal) {
        NM_Grupo_Nota_Fiscal = Grupo_Nota_Fiscal;
    }
    public String getNM_Modelos() {
        return NM_Modelos;
    }
    public void setNM_Modelos(String tipos) {
        NM_Modelos = tipos;
    }
}