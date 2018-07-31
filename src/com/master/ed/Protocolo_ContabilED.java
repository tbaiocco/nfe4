package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis
 * @serial Protocolo Contabil
 * @serialData 03/2006
 */
public class Protocolo_ContabilED extends MasterED implements Serializable {

	private static final long serialVersionUID = 1L;

	public Protocolo_ContabilED() {
        super();
    }
	
    private long oid_Protocolo_Contabil;
    private long oid_Origem;
    private long oid_Usuario;
    private String dt_Data;
    private String tx_Referencia;
    private String nm_Arquivo;
    private String dm_Situacao; // P em processo - O processado OK - E com erro
    private long nr_Lote;
    // Campo so de tela
    private String nm_Usuario;
    private String nm_Origem;
    private String cd_Origem;
    // Campo de filtro
    private String dt_Inicial;
    private String dt_Final;
    
	public String getDm_Situacao() {
		return dm_Situacao;
	}
	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}
	public String getDt_Data() {
		return dt_Data;
	}
	public void setDt_Data(String dt_Data) {
		this.dt_Data = dt_Data;
	}
	public String getNm_Arquivo() {
		return nm_Arquivo;
	}
	public void setNm_Arquivo(String nm_Arquivo) {
		this.nm_Arquivo = nm_Arquivo;
	}
	public long getOid_Origem() {
		return oid_Origem;
	}
	public void setOid_Origem(long oid_Origem) {
		this.oid_Origem = oid_Origem;
	}
	public long getOid_Protocolo_Contabil() {
		return oid_Protocolo_Contabil;
	}
	public void setOid_Protocolo_Contabil(long oid_Protocolo_Contabil) {
		this.oid_Protocolo_Contabil = oid_Protocolo_Contabil;
	}
	public long getOid_Usuario() {
		return oid_Usuario;
	}
	public void setOid_Usuario(long oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
	}
	public String getTx_Referencia() {
		return tx_Referencia;
	}
	public void setTx_Referencia(String tx_Referencia) {
		this.tx_Referencia = tx_Referencia;
	}
	public String getNm_Origem() {
		return nm_Origem;
	}
	public void setNm_Origem(String nm_origem) {
		this.nm_Origem = nm_origem;
	}
	public String getNm_Usuario() {
		return nm_Usuario;
	}
	public void setNm_Usuario(String nm_Usuario) {
		this.nm_Usuario = nm_Usuario;
	}
	public String getCd_Origem() {
		return cd_Origem;
	}
	public void setCd_Origem(String cd_Origem) {
		this.cd_Origem = cd_Origem;
	}
	public long getNr_Lote() {
		return nr_Lote;
	}
	public void setNr_Lote(long nr_Lote) {
		this.nr_Lote = nr_Lote;
	}
	public String getDt_Final() {
		return dt_Final;
	}
	public void setDt_Final(String dt_Final) {
		this.dt_Final = dt_Final;
	}
	public String getDt_Inicial() {
		return dt_Inicial;
	}
	public void setDt_Inicial(String dt_Inicial) {
		this.dt_Inicial = dt_Inicial;
	}
	
}