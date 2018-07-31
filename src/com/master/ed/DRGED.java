package com.master.ed;

import java.io.Serializable;

/**
 * @author André Valadas
 * @serial Demonstrativos Resumos Gerenciais
 * @serialData 14/10/2005
 */
public class DRGED extends MasterED implements Serializable {

    public DRGED() {
        super();
    }
    public DRGED(int oid_DRG) {
        this.oid_DRG = oid_DRG;
    }
    
    private int oid_DRG;
    private String CD_DRG;
    private String NM_DRG;
    private String DM_Agrupamento;
    
	/**
	 * @return Returns the cD_DRG.
	 */
	public String getCD_DRG() {
		return CD_DRG;
	}
	/**
	 * @param DRG The cD_DRG to set.
	 */
	public void setCD_DRG(String DRG) {
		CD_DRG = DRG;
	}
	/**
	 * @return Returns the dM_Agrupamento.
	 */
	public String getDM_Agrupamento() {
		return DM_Agrupamento;
	}
	/**
	 * @param Agrupamento The dM_Agrupamento to set.
	 */
	public void setDM_Agrupamento(String Agrupamento) {
		DM_Agrupamento = Agrupamento;
	}
	/**
	 * @return Returns the nM_DRG.
	 */
	public String getNM_DRG() {
		return NM_DRG;
	}
	/**
	 * @param DRG The nM_DRG to set.
	 */
	public void setNM_DRG(String DRG) {
		NM_DRG = DRG;
	}
	/**
	 * @return Returns the oid_DRG.
	 */
	public int getOid_DRG() {
		return oid_DRG;
	}
	/**
	 * @param oid_DRG The oid_DRG to set.
	 */
	public void setOid_DRG(int oid_DRG) {
		this.oid_DRG = oid_DRG;
	}
}