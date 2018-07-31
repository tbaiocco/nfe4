/*
 * Created on 02/02/2005
 */
package com.master.ed;

/**
 * @author André Valadas
 */
public class Pedido_Nota_FiscalED extends MasterED {

    public Pedido_Nota_FiscalED() {
        super();
    }
    
    public Pedido_Nota_FiscalED(int oid_Pedido_Nota_Fiscal) {
        super();
        this.oid_Pedido_Nota_Fiscal = oid_Pedido_Nota_Fiscal;
    }
    
    public Pedido_Nota_FiscalED(int oid_Pedido_Nota_Fiscal, int oid_Pedido, String oid_Nota_Fiscal, String entrada_Saida, String situacao) {
        this.oid_Pedido_Nota_Fiscal = oid_Pedido_Nota_Fiscal;
        this.oid_Pedido = oid_Pedido;
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
        DM_Entrada_Saida = entrada_Saida;
        DM_Situacao = situacao;
    }
    
    public PedidoED edPedido = new PedidoED();
    public Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaED();
    
    private int oid_Pedido_Nota_Fiscal;
    private int oid_Pedido;
    private String oid_Nota_Fiscal;
    private String DM_Entrada_Saida;
    private String DM_Situacao;
    
    public String getDescDM_Entrada_Saida() {
        if ("S".equals(DM_Entrada_Saida))
            return "Saida";
        else if ("E".equals(DM_Entrada_Saida))
            return "Entrada";
        else return "Não informado!";
    }
    public String getDescDM_Situacao() {
        if ("F".equals(DM_Situacao))
            return "Finalizada";
        else if ("N".equals(DM_Situacao) || "A".equals(DM_Situacao))
            return "Em aberto";
        else if ("C".equals(DM_Situacao))
            return "Cancelada";
        else return "Não informado!";
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public int getOid_Pedido_Nota_Fiscal() {
        return oid_Pedido_Nota_Fiscal;
    }
    public int getOid_Pedido() {
        return oid_Pedido;
    }
    public String getOid_Nota_Fiscal() {
        return oid_Nota_Fiscal;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public void setOid_Pedido_Nota_Fiscal(int oid_Pedido_Nota_Fiscal) {
        this.oid_Pedido_Nota_Fiscal = oid_Pedido_Nota_Fiscal;
    }
    public void setOid_Pedido(int oid_Pedido) {
        this.oid_Pedido = oid_Pedido;
    }
    public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
    }
    public String getDM_Entrada_Saida() {
        return DM_Entrada_Saida;
    }
    public void setDM_Entrada_Saida(String entrada_Saida) {
        DM_Entrada_Saida = entrada_Saida;
    }
}