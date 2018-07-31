package com.master.ed;

/**
 * @author André Valadas
 * @serial Motivos de Devolução dos Cheques
 * @serialData 24/08/2005
 */
public class Motivo_DevolucaoED extends MasterED {
 
    public Motivo_DevolucaoED() {
    }
    public Motivo_DevolucaoED(int oid_Motivo_Devolucao) {
        this.oid_Motivo_Devolucao = oid_Motivo_Devolucao;
    }
    
    private int oid_Motivo_Devolucao;
    private String CD_Motivo_Devolucao;
    private String NM_Motivo_Devolucao;
    
    public String getCD_Motivo_Devolucao() {
        return CD_Motivo_Devolucao;
    }
    public int getOid_Motivo_Devolucao() {
        return oid_Motivo_Devolucao;
    }
    public void setCD_Motivo_Devolucao(String Motivo_Devolucao) {
        CD_Motivo_Devolucao = Motivo_Devolucao;
    }
    public void setOid_Motivo_Devolucao(int oid_Motivo_Devolucao) {
        this.oid_Motivo_Devolucao = oid_Motivo_Devolucao;
    }
    public String getNM_Motivo_Devolucao() {
        return NM_Motivo_Devolucao;
    }
    public void setNM_Motivo_Devolucao(String Motivo_Devolucao) {
        NM_Motivo_Devolucao = Motivo_Devolucao;
    }
}