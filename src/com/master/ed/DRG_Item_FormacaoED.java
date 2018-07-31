package com.master.ed;

import java.io.Serializable;

/**
 * @author André Valadas
 * @serial Formações dos Itens do D.R.G.
 * @serialData 14/10/2005
 */
public class DRG_Item_FormacaoED extends MasterED implements Serializable {

    public DRG_Item_FormacaoED() {
        super();
    }
    public DRG_Item_FormacaoED(int oid_DRG_Item_Formacao) {
        this.oid_DRG_Item_Formacao = oid_DRG_Item_Formacao;
    }
    public DRG_Item_FormacaoED(int oid_DRG_Item_Formacao, int oid_DRG_Item) {
        this.oid_DRG_Item = oid_DRG_Item;
        this.oid_DRG_Item_Formacao = oid_DRG_Item_Formacao;
    }
    
    private int oid_DRG_Item_Formacao;
    private int oid_DRG_Item;
    private int oid_Conta;
    private String DM_Considerar;
    private String DM_Inverter;
    
	/**
	 * @return Returns the dM_Considerar.
	 */
	public String getDM_Considerar() {
		return DM_Considerar;
	}
	/**
	 * @param considerar The dM_Considerar to set.
	 */
	public void setDM_Considerar(String considerar) {
		DM_Considerar = considerar;
	}
	/**
	 * @return Returns the dM_Inverter.
	 */
	public String getDM_Inverter() {
		return DM_Inverter;
	}
	/**
	 * @param inverter The dM_Inverter to set.
	 */
	public void setDM_Inverter(String inverter) {
		DM_Inverter = inverter;
	}
	/**
	 * @return Returns the oid_Conta.
	 */
	public int getOid_Conta() {
		return oid_Conta;
	}
	/**
	 * @param oid_Conta The oid_Conta to set.
	 */
	public void setOid_Conta(int oid_Conta) {
		this.oid_Conta = oid_Conta;
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
	/**
	 * @return Returns the oid_DRG_Item_Formacao.
	 */
	public int getOid_DRG_Item_Formacao() {
		return oid_DRG_Item_Formacao;
	}
	/**
	 * @param oid_DRG_Item_Formacao The oid_DRG_Item_Formacao to set.
	 */
	public void setOid_DRG_Item_Formacao(int oid_DRG_Item_Formacao) {
		this.oid_DRG_Item_Formacao = oid_DRG_Item_Formacao;
	}
}