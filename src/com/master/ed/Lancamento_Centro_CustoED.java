package com.master.ed;

public class Lancamento_Centro_CustoED extends MasterED {

    public Lancamento_Centro_CustoED() {
        super();
    }
    public Lancamento_Centro_CustoED(int custo) {
        oid_Lancamento_Centro_Custo = custo;
    } 
    public Lancamento_Centro_CustoED(int custo, int custo2, String fiscal) {
        oid_Centro_Custo = custo;
        oid_Lancamento_Centro_Custo = custo2;
        oid_Nota_Fiscal = fiscal;
    }
    
    private int oid_Lancamento_Centro_Custo;
    private int oid_Centro_Custo;
    private String oid_Nota_Fiscal;
    private int oid_Lancamento;
    private double PE_Lancamento;
    private double VL_Lancamento;
    private String DM_Tipo_Lancamento; //*** "N"==Nota Principal, "R"==Rateio dos outros Centros de Custo
    
    public Centro_CustoED edCentro = new Centro_CustoED();

    public String getDescDM_Tipo_Lancamento() {
        if ("N".equals(DM_Tipo_Lancamento))
            return "Nota Fiscal";
        else if ("R".equals(DM_Tipo_Lancamento))
            return "Rateio";
        else return "Não informado!";
    }
    public int getOid_Centro_Custo() {
        return oid_Centro_Custo;
    }
    public void setOid_Centro_Custo(int oid_Centro_Custo) {
        this.oid_Centro_Custo = oid_Centro_Custo;
    }
    public int getOid_Lancamento() {
        return oid_Lancamento;
    }
    public void setOid_Lancamento(int oid_Lancamento) {
        this.oid_Lancamento = oid_Lancamento;
    }
    public int getOid_Lancamento_Centro_Custo() {
        return oid_Lancamento_Centro_Custo;
    }
    public void setOid_Lancamento_Centro_Custo(int oid_Lancamento_Centro_Custo) {
        this.oid_Lancamento_Centro_Custo = oid_Lancamento_Centro_Custo;
    }
    public String getOid_Nota_Fiscal() {
        return oid_Nota_Fiscal;
    }
    public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
    }
    public double getPE_Lancamento() {
        return PE_Lancamento;
    }
    public void setPE_Lancamento(double lancamento) {
        PE_Lancamento = lancamento;
    }
    public double getVL_Lancamento() {
        return VL_Lancamento;
    }
    public void setVL_Lancamento(double lancamento) {
        VL_Lancamento = lancamento;
    }
    public String getDM_Tipo_Lancamento() {
        return DM_Tipo_Lancamento;
    }
    public void setDM_Tipo_Lancamento(String tipo_Lancamento) {
        DM_Tipo_Lancamento = tipo_Lancamento;
    }
}