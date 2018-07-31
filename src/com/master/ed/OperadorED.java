package com.master.ed;

/**
 * @author Andre Valadas
 */
public class OperadorED extends MasterED{
    
    private Integer oid_Operador;
    private String cd_Operador;
    private String nm_Operador;
    private String dm_Situacao;

    /**
     * @return Returns the cd_Operador.
     */
    public String getCd_Operador() {
        return cd_Operador;
    }
    /**
     * @param cd_Operador The cd_Operador to set.
     */
    public void setCd_Operador(String cd_Operador) {
        this.cd_Operador = cd_Operador;
    }
    /**
     * @return Returns the dm_Situacao.
     */
    public String getDm_Situacao() {
        return dm_Situacao;
    }
    /**
     * @param dm_Situacao The dm_Situacao to set.
     */
    public void setDm_Situacao(String dm_Situacao) {
        this.dm_Situacao = dm_Situacao;
    }
    /**
     * @return Returns the nm_Operador.
     */
    public String getNm_Operador() {
        return nm_Operador;
    }
    /**
     * @param nm_Operador The nm_Operador to set.
     */
    public void setNm_Operador(String nm_Operador) {
        this.nm_Operador = nm_Operador;
    }
    /**
     * @return Returns the oid_Operador.
     */
    public Integer getOid_Operador() {
        return oid_Operador;
    }
    /**
     * @param oid_Operador The oid_Operador to set.
     */
    public void setOid_Operador(Integer oid_Operador) {
        this.oid_Operador = oid_Operador;
    }
}
