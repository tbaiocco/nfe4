/*
 * Created on 09/03/2005
 *
 */
package com.master.ed;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class Permisso_PaisED extends MasterED {
	private int oid_Permisso_Pais;
	private int oid_Pais_Origem;
	private String CD_Pais_Origem;
	private String NM_Pais_Origem;
	private int oid_Pais_Destino;
	private String CD_Pais_Destino;
	private String NM_Pais_Destino;
	private int oid_aidof;
	private String CD_aidof;
	private String NR_Permisso;
	
    public Permisso_PaisED() {
    }

	/** Construtor completo
	 * @param oid_Permisso_Pais
	 * @param oid_Pais_Origem
	 * @param pais_Origem
	 * @param pais_Origem2
	 * @param oid_Pais_Destino
	 * @param pais_Destino
	 * @param pais_Destino2
	 * @param oid_aidof
	 * @param CD_aidof
	 * @param nr_permisso
	 */
	public Permisso_PaisED(int oid_Permisso_Pais, 
			int oid_Pais_Origem,
			String CD_Pais_Origem, 
			String NM_Pais_Origem, 
			int oid_Pais_Destino,
			String CD_Pais_Destino, 
			String NM_Pais_Destino, 
			int oid_aidof,
			String CD_aidof,
			String NR_Permisso) {
		super();
		this.oid_Permisso_Pais = oid_Permisso_Pais;
		this.oid_Pais_Origem = oid_Pais_Origem;
		this.CD_Pais_Origem = CD_Pais_Origem;
		this.NM_Pais_Origem = NM_Pais_Origem;
		this.oid_Pais_Destino = oid_Pais_Destino;
		this.CD_Pais_Destino = CD_Pais_Destino;
		this.NM_Pais_Destino = NM_Pais_Destino;
		this.oid_aidof = oid_aidof;
		this.CD_aidof = CD_aidof;
		this.NR_Permisso = NR_Permisso;
	}
	/** Construtor com campos da tabela
	 * @param oid_Permisso_Pais
	 * @param oid_pais_origem
	 * @param oid_pais_destino
	 * @param oid_aidof
	 * @param nr_permisso
	 */
	public Permisso_PaisED(int oid_Permisso_Pais, 
			int oid_Pais_Origem,
			int oid_Pais_Destino, 
			int oid_aidof, 
			String NR_Permisso) {
		super();
		this.oid_Permisso_Pais = oid_Permisso_Pais;
		this.oid_Pais_Origem = oid_Pais_Origem;
		this.oid_Pais_Destino = oid_Pais_Destino;
		this.oid_aidof = oid_aidof;
		this.NR_Permisso = NR_Permisso;
	}
	public String getCD_Pais_Destino() {
		return CD_Pais_Destino;
	}
	public void setCD_Pais_Destino(String CD_Pais_Destino) {
		this.CD_Pais_Destino = CD_Pais_Destino;
	}
	public String getCD_Pais_Origem() {
		return CD_Pais_Origem;
	}
	public void setCD_Pais_Origem(String CD_Pais_Origem) {
		this.CD_Pais_Origem = CD_Pais_Origem;
	}
	public String getNM_Pais_Destino() {
		return NM_Pais_Destino;
	}
	public void setNM_Pais_Destino(String NM_Pais_Destino) {
		this.NM_Pais_Destino = NM_Pais_Destino;
	}
	public String getNM_Pais_Origem() {
		return NM_Pais_Origem;
	}
	public void setNM_Pais_Origem(String NM_Pais_Origem) {
		this.NM_Pais_Origem = NM_Pais_Origem;
	}
	public String getNR_Permisso() {
		return NR_Permisso;
	}
	public void setNR_Permisso(String NR_Permisso) {
		this.NR_Permisso = NR_Permisso;
	}
	public int getOid_aidof() {
		return oid_aidof;
	}
	public void setOid_aidof(int oid_aidof) {
		this.oid_aidof = oid_aidof;
	}
	public int getOid_Pais_Destino() {
		return oid_Pais_Destino;
	}
	public void setOid_Pais_Destino(int oid_Pais_Destino) {
		this.oid_Pais_Destino = oid_Pais_Destino;
	}
	public int getOid_Pais_Origem() {
		return oid_Pais_Origem;
	}
	public void setOid_Pais_Origem(int oid_Pais_Origem) {
		this.oid_Pais_Origem = oid_Pais_Origem;
	}
	public int getOid_Permisso_Pais() {
		return oid_Permisso_Pais;
	}
	public void setOid_Permisso_Pais(int oid_Permisso_Pais) {
		this.oid_Permisso_Pais = oid_Permisso_Pais;
	}
    public String getCD_aidof() {
        return this.CD_aidof;
    }
    public void setCD_aidof(String CD_aidof) {
        this.CD_aidof = CD_aidof;
    }
}