package com.master.ed;

/**
 * @author André Valadas
 * @serial Mapa Resumo PDV
 * @serialData 16/03/2006
 */
public class Mapa_ResumoED extends MasterED {

    public Mapa_ResumoED() {
    }
    public Mapa_ResumoED(int oid_mapa_resumo) {
        super();
        oid_Mapa_Resumo = oid_mapa_resumo;
    }
    public Mapa_ResumoED(String fiscal) {
        super();
        oid_Nota_Fiscal = fiscal;
    }
    
    private int oid_Mapa_Resumo;
    private String oid_Nota_Fiscal;
    private String DT_Emissao;
    private int NR_Documento;
    private int NR_Ultimo_Documento;
    private int NR_Reducao;
    private double VL_GT_Inicial;
    private double VL_GT_Final;
    private double VL_Cancelamento;
    private double VL_Desconto;
    
    public String getDT_Emissao() {
        return DT_Emissao;
    }
    
    public void setDT_Emissao(String emissao) {
        DT_Emissao = emissao;
    }
    
    public int getNR_Documento() {
        return NR_Documento;
    }
    
    public void setNR_Documento(int documento) {
        NR_Documento = documento;
    }
    
    public int getNR_Reducao() {
        return NR_Reducao;
    }
    
    public void setNR_Reducao(int reducao) {
        NR_Reducao = reducao;
    }
    
    public int getNR_Ultimo_Documento() {
        return NR_Ultimo_Documento;
    }
    
    public void setNR_Ultimo_Documento(int ultimo_Documento) {
        NR_Ultimo_Documento = ultimo_Documento;
    }
    
    public int getOid_Mapa_Resumo() {
        return oid_Mapa_Resumo;
    }
    
    public void setOid_Mapa_Resumo(int oid_Mapa_Resumo) {
        this.oid_Mapa_Resumo = oid_Mapa_Resumo;
    }
    
    public String getOid_Nota_Fiscal() {
        return oid_Nota_Fiscal;
    }
    
    public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
    }
    
    public double getVL_Cancelamento() {
        return VL_Cancelamento;
    }
    
    public void setVL_Cancelamento(double cancelamento) {
        VL_Cancelamento = cancelamento;
    }
    
    public double getVL_Desconto() {
        return VL_Desconto;
    }
    
    public void setVL_Desconto(double desconto) {
        VL_Desconto = desconto;
    }
    
    public double getVL_GT_Final() {
        return VL_GT_Final;
    }
    
    public void setVL_GT_Final(double final1) {
        VL_GT_Final = final1;
    }
    
    public double getVL_GT_Inicial() {
        return VL_GT_Inicial;
    }
    
    public void setVL_GT_Inicial(double inicial) {
        VL_GT_Inicial = inicial;
    }
}