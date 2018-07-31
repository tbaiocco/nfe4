package com.master.ed;

/**
 * @author André Valadas
 * @serial Tipos de Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tipo_Tabela_VendaED extends MasterED {

    public Tipo_Tabela_VendaED() {
    }
    public Tipo_Tabela_VendaED(int oid_Tipo_Tabela_Venda) {
        this.oid_Tipo_Tabela_Venda = oid_Tipo_Tabela_Venda;
    }
    
    private int oid_Tipo_Tabela_Venda;
    private String CD_Tipo_Tabela_Venda;
    private String NM_Tipo_Tabela_Venda;
    
    public String getCD_Tipo_Tabela_Venda() {
        return CD_Tipo_Tabela_Venda;
    }
    public int getOid_Tipo_Tabela_Venda() {
        return oid_Tipo_Tabela_Venda;
    }
    public void setCD_Tipo_Tabela_Venda(String tipo_Tabela_Venda) {
        CD_Tipo_Tabela_Venda = tipo_Tabela_Venda;
    }
    public void setOid_Tipo_Tabela_Venda(int oid_Tipo_Tabela_Venda) {
        this.oid_Tipo_Tabela_Venda = oid_Tipo_Tabela_Venda;
    }
    public String getNM_Tipo_Tabela_Venda() {
        return NM_Tipo_Tabela_Venda;
    }
    public void setNM_Tipo_Tabela_Venda(String tipo_Tabela_Venda) {
        NM_Tipo_Tabela_Venda = tipo_Tabela_Venda;
    }
}