/*
 * Created on 10/09/2004
 */
package com.master.ed;

/**
 * @author Andre
 */
public class Classificacao_FiscalED extends MasterED{
	
	private int oid_Classificacao_Fiscal;
	private String CD_Reduzido;
	private String CD_Fiscal;
	
    /**
     * @return Returns the cD_Fiscal.
     */
    public String getCD_Fiscal() {
        return CD_Fiscal;
    }
    /**
     * @param fiscal The cD_Fiscal to set.
     */
    public void setCD_Fiscal(String fiscal) {
        CD_Fiscal = fiscal;
    }
    /**
     * @return Returns the cD_Reduzido.
     */
    public String getCD_Reduzido() {
        return CD_Reduzido;
    }
    /**
     * @param reduzido The cD_Reduzido to set.
     */
    public void setCD_Reduzido(String reduzido) {
        CD_Reduzido = reduzido;
    }
    /**
     * @return Returns the oid_Classificacao_Fiscal.
     */
    public int getOid_Classificacao_Fiscal() {
        return oid_Classificacao_Fiscal;
    }
    /**
     * @param oid_Classificacao_Fiscal The oid_Classificacao_Fiscal to set.
     */
    public void setOid_Classificacao_Fiscal(int oid_Classificacao_Fiscal) {
        this.oid_Classificacao_Fiscal = oid_Classificacao_Fiscal;
    }
}
