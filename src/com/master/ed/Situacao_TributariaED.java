/*
 * Created on 10/09/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class Situacao_TributariaED extends MasterED{
	
	private int oid_Situacao_Tributaria;
	private String CD_Situacao_Tributaria;
	private String DM_Procedencia;
	private String CD_Tipo;
	private String NM_Situacao_Tributaria;

    /**
     * @return Returns the cD_Situacao_Tributaria.
     */
    public String getCD_Situacao_Tributaria() {
        return CD_Situacao_Tributaria;
    }
    /**
     * @param situacao_Tributaria The cD_Situacao_Tributaria to set.
     */
    public void setCD_Situacao_Tributaria(String situacao_Tributaria) {
        CD_Situacao_Tributaria = situacao_Tributaria;
    }
    /**
     * @return Returns the cD_Tipo.
     */
    public String getCD_Tipo() {
        return CD_Tipo;
    }
    /**
     * @param tipo The cD_Tipo to set.
     */
    public void setCD_Tipo(String tipo) {
        CD_Tipo = tipo;
    }
    /**
     * @return Returns the dM_Procedencia.
     */
    public String getDM_Procedencia() {
        return DM_Procedencia;
    }
    /**
     * @param procedencia The dM_Procedencia to set.
     */
    public void setDM_Procedencia(String procedencia) {
        DM_Procedencia = procedencia;
    }
    /**
     * @return Returns the nM_Situacao_Tributaria.
     */
    public String getNM_Situacao_Tributaria() {
        return NM_Situacao_Tributaria;
    }
    /**
     * @param situacao_Tributaria The nM_Situacao_Tributaria to set.
     */
    public void setNM_Situacao_Tributaria(String situacao_Tributaria) {
        NM_Situacao_Tributaria = situacao_Tributaria;
    }
    /**
     * @return Returns the oid_Situacao_Tributaria.
     */
    public int getOid_Situacao_Tributaria() {
        return oid_Situacao_Tributaria;
    }
    /**
     * @param oid_Situacao_Tributaria The oid_Situacao_Tributaria to set.
     */
    public void setOid_Situacao_Tributaria(int oid_Situacao_Tributaria) {
        this.oid_Situacao_Tributaria = oid_Situacao_Tributaria;
    }
}
