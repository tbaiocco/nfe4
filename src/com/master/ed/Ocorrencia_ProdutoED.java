/*
 * Created on 28/09/2004
 */
package com.master.ed;


/**
 * @author Andre Valadas
 */
public class Ocorrencia_ProdutoED extends MasterED {
	
	private int oid_Ocorrencia_Produto;
	private int oid_Tipo_Ocorrencia;
	private int oid_Produto;
	private String DT_Ocorrencia_Produto;
	private String HR_Ocorrencia_Produto;	
	private String TX_Descricao;
	
	//*** Implementados
	private String CD_Tipo_Ocorrencia;
	private String NM_Tipo_Ocorrencia;
	private String CD_Produto;
	private String NM_Produto;
	
    /**
     * @return Returns the cD_Produto.
     */
    public String getCD_Produto() {
        return CD_Produto;
    }
    /**
     * @param produto The cD_Produto to set.
     */
    public void setCD_Produto(String produto) {
        CD_Produto = produto;
    }
    /**
     * @return Returns the nM_Produto.
     */
    public String getNM_Produto() {
        return NM_Produto;
    }
    /**
     * @param produto The nM_Produto to set.
     */
    public void setNM_Produto(String produto) {
        NM_Produto = produto;
    }
    /**
     * @return Returns the cD_Tipo_Ocorrencia.
     */
    public String getCD_Tipo_Ocorrencia() {
        return CD_Tipo_Ocorrencia;
    }
    /**
     * @param tipo_Ocorrencia The cD_Tipo_Ocorrencia to set.
     */
    public void setCD_Tipo_Ocorrencia(String tipo_Ocorrencia) {
        CD_Tipo_Ocorrencia = tipo_Ocorrencia;
    }
    /**
     * @return Returns the dT_Ocorrencia_Produto.
     */
    public String getDT_Ocorrencia_Produto() {
        return DT_Ocorrencia_Produto;
    }
    /**
     * @param ocorrencia_Produto The dT_Ocorrencia_Produto to set.
     */
    public void setDT_Ocorrencia_Produto(String ocorrencia_Produto) {
        DT_Ocorrencia_Produto = ocorrencia_Produto;
    }
    /**
     * @return Returns the hR_Ocorrencia.
     */
    public String getHR_Ocorrencia_Produto() {
        return HR_Ocorrencia_Produto;
    }
    /**
     * @param ocorrencia The hR_Ocorrencia to set.
     */
    public void setHR_Ocorrencia_Produto(String ocorrencia) {
        HR_Ocorrencia_Produto = ocorrencia;
    }
        /**
     * @return Returns the nM_Tipo_Ocorrencia.
     */
    public String getNM_Tipo_Ocorrencia() {
        return NM_Tipo_Ocorrencia;
    }
    /**
     * @param tipo_Ocorrencia The nM_Tipo_Ocorrencia to set.
     */
    public void setNM_Tipo_Ocorrencia(String tipo_Ocorrencia) {
        NM_Tipo_Ocorrencia = tipo_Ocorrencia;
    }
    /**
     * @return Returns the oid_Ocorrencia_Produto.
     */
    public int getOid_Ocorrencia_Produto() {
        return oid_Ocorrencia_Produto;
    }
    /**
     * @param oid_Ocorrencia_Produto The oid_Ocorrencia_Produto to set.
     */
    public void setOid_Ocorrencia_Produto(int oid_Ocorrencia_Produto) {
        this.oid_Ocorrencia_Produto = oid_Ocorrencia_Produto;
    }
    /**
     * @return Returns the oid_Produto.
     */
    public int getOid_Produto() {
        return oid_Produto;
    }
    /**
     * @param oid_Produto The oid_Produto to set.
     */
    public void setOid_Produto(int oid_Produto) {
        this.oid_Produto = oid_Produto;
    }
    /**
     * @return Returns the oid_Tipo_Ocorrencia.
     */
    public int getOid_Tipo_Ocorrencia() {
        return oid_Tipo_Ocorrencia;
    }
    /**
     * @param oid_Tipo_Ocorrencia The oid_Tipo_Ocorrencia to set.
     */
    public void setOid_Tipo_Ocorrencia(int oid_Tipo_Ocorrencia) {
        this.oid_Tipo_Ocorrencia = oid_Tipo_Ocorrencia;
    }
    /**
     * @return Returns the tX_Descricao.
     */
    public String getTX_Descricao() {
        return TX_Descricao;
    }
    /**
     * @param descricao The tX_Descricao to set.
     */
    public void setTX_Descricao(String descricao) {
        TX_Descricao = descricao;
    }
}
