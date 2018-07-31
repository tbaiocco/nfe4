/*
 * Created on 31/08/2004
 */
package com.master.ed;

/**
 * @author Andre
 */
public class MixED extends MasterED{
	
    public MixED(int oid_Mix) {
        this.oid_Mix = oid_Mix;
    }
    public MixED() {
        super();
    }
	private int oid_Mix;
	private String cd_Mix;
	private String nm_Mix;

	/**
	 * @return Returns the cd_Mix.
	 */
	public String getCd_Mix() {
		return cd_Mix;
	}
	/**
	 * @param cd_Mix The cd_Mix to set.
	 */
	public void setCd_Mix(String cd_Mix) {
		this.cd_Mix = cd_Mix;
	}
	/**
	 * @return Returns the nm_Mix.
	 */
	public String getNm_Mix() {
		return nm_Mix;
	}
	/**
	 * @param nm_Mix The nm_Mix to set.
	 */
	public void setNm_Mix(String nm_Mix) {
		this.nm_Mix = nm_Mix;
	}
	/**
	 * @return Returns the oid_Mix.
	 */
	public int getOid_Mix() {
		return oid_Mix;
	}
	/**
	 * @param oid_Mix The oid_Mix to set.
	 */
	public void setOid_Mix(int oid_Mix) {
		this.oid_Mix = oid_Mix;
	}
}
