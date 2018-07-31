package com.master.ed;

import java.io.Serializable;

/**
 * @author André Valadas
 * @serial Itens do D.R.G.
 * @serialData 14/10/2005
 */
public class DRG_ItemED extends MasterED implements Serializable {

    public DRG_ItemED() {
        super();
    }
    public DRG_ItemED(int oid_DRG_Item) {
        this.oid_DRG_Item = oid_DRG_Item;
    }
    public DRG_ItemED(int oid_DRG_Item, int oid_DRG) {
        this.oid_DRG = oid_DRG;
        this.oid_DRG_Item = oid_DRG_Item;
    }
    
    private int oid_DRG_Item;
    private int oid_DRG;
    private int NR_Sequendia;
    private String NM_DRG_Item;
    private String CD_Estrutural;

	/**
	 * @return Returns the cD_Estrutural.
	 */
	public String getCD_Estrutural() {
		return CD_Estrutural;
	}
	/**
	 * @param estrutural The cD_Estrutural to set.
	 */
	public void setCD_Estrutural(String estrutural) {
		CD_Estrutural = estrutural;
	}
	/**
	 * @return Returns the nM_DRG_Item.
	 */
	public String getNM_DRG_Item() {
		return NM_DRG_Item;
	}
	/**
	 * @param item The nM_DRG_Item to set.
	 */
	public void setNM_DRG_Item(String item) {
		NM_DRG_Item = item;
	}
	/**
	 * @return Returns the nR_Sequendia.
	 */
	public int getNR_Sequendia() {
		return NR_Sequendia;
	}
	/**
	 * @param sequendia The nR_Sequendia to set.
	 */
	public void setNR_Sequendia(int sequendia) {
		NR_Sequendia = sequendia;
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
	/**
	 * @return Returns the oid_DRG_Item.
	 */
	public int getOid_DRG_Item() {
		return oid_DRG_Item;
	}
	/**
	 * @param oid_DRG_Item The oid_DRG_Item to set.
	 */
	public void setOid_DRG_Item(int oid_DRG_Item) {
		this.oid_DRG_Item = oid_DRG_Item;
	}
}