/*
 * Created on 30/09/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class Area_VendaED extends MasterED{
	
	private int oid_Area_Venda;
	private String CD_Area_Venda;
	private String NM_Area_Venda;

    /**
     * @return Returns the cD_Area_Venda.
     */
    public String getCD_Area_Venda() {
        return CD_Area_Venda;
    }
    /**
     * @param area_Venda The cD_Area_Venda to set.
     */
    public void setCD_Area_Venda(String area_Venda) {
        CD_Area_Venda = area_Venda;
    }
    /**
     * @return Returns the nM_Area_Venda.
     */
    public String getNM_Area_Venda() {
        return NM_Area_Venda;
    }
    /**
     * @param area_Venda The nM_Area_Venda to set.
     */
    public void setNM_Area_Venda(String area_Venda) {
        NM_Area_Venda = area_Venda;
    }
    /**
     * @return Returns the oid_Area_Venda.
     */
    public int getOid_Area_Venda() {
        return oid_Area_Venda;
    }
    /**
     * @param oid_Area_Venda The oid_Area_Venda to set.
     */
    public void setOid_Area_Venda(int oid_Area_Venda) {
        this.oid_Area_Venda = oid_Area_Venda;
    }
}
