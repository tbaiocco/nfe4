package com.master.ed;

import java.io.Serializable;

import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;

/**
 * @author André Valadas
 */
public class Carga_EntregaED extends MasterED implements Serializable {

    public Carga_EntregaED() {
        super();
    }
    public Carga_EntregaED(int oid_Carga_Entrega) {
        this.oid_Carga_Entrega = oid_Carga_Entrega;
    }
    public Carga_EntregaED(int oid_Carga_Entrega, int oid_Unidade) {
        this.oid_Carga_Entrega = oid_Carga_Entrega;
        this.oid_Unidade = oid_Unidade;
    }
    public Carga_EntregaED(boolean entregador, boolean unidade, boolean veiculo) {
        carregaEntregador = entregador;
        carregaUnidade = unidade;
        carregaVeiculo = veiculo;
    }
    public Carga_EntregaED(int oid_Carga_Entrega, boolean entregador, boolean unidade, boolean veiculo) {
        this.oid_Carga_Entrega = oid_Carga_Entrega;
        carregaEntregador = entregador;
        carregaUnidade = unidade;
        carregaVeiculo = veiculo;
    }
    
    public EntregadorED edEntregador = new EntregadorED();
    public UnidadeBean edUnidade = new UnidadeBean();
    public VeiculoBean edVeiculo = new VeiculoBean();
    
    private boolean carregaEntregador = true;
    private boolean carregaUnidade = true;
    private boolean carregaVeiculo = true;
    
    private int oid_Carga_Entrega;
    private int oid_Unidade;
    private int oid_Entregador;
    private String oid_Veiculo;
    private int NR_Carga;
    private String DT_Entrega;
    
    private int NR_QT_Pedidos;
    private int NR_QT_Notas;
    private double VL_Carga;
    private double Pesagem_Carga;
    
    public String getDT_Entrega() {
        return DT_Entrega;
    }
    public int getNR_Carga() {
        return NR_Carga;
    }
    public int getOid_Carga_Entrega() {
        return oid_Carga_Entrega;
    }
    public int getOid_Entregador() {
        return oid_Entregador;
    }
    public int getOid_Unidade() {
        return oid_Unidade;
    }
    public String getOid_Veiculo() {
        return oid_Veiculo;
    }
    public double getVL_Carga() {
        return VL_Carga;
    }
    public void setDT_Entrega(String entrega) {
        DT_Entrega = entrega;
    }
    public void setNR_Carga(int carga) {
        NR_Carga = carga;
    }
    public void setOid_Carga_Entrega(int oid_Carga_Entrega) {
        this.oid_Carga_Entrega = oid_Carga_Entrega;
    }
    public void setOid_Entregador(int oid_Entregador) {
        this.oid_Entregador = oid_Entregador;
    }
    public void setOid_Unidade(int oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }
    public void setOid_Veiculo(String oid_Veiculo) {
        this.oid_Veiculo = oid_Veiculo;
    }
    public int getNR_QT_Notas() {
        return NR_QT_Notas;
    }
    public void setNR_QT_Notas(int notas) {
        NR_QT_Notas = notas;
    }
    public int getNR_QT_Pedidos() {
        return NR_QT_Pedidos;
    }
    public void setNR_QT_Pedidos(int pedidos) {
        NR_QT_Pedidos = pedidos;
    }
    public void setVL_Carga(double carga) {
        VL_Carga = carga;
    }
    public boolean isCarregaEntregador() {
        return carregaEntregador;
    }
    public void setCarregaEntregador(boolean carregaEntregador) {
        this.carregaEntregador = carregaEntregador;
    }
    public boolean isCarregaUnidade() {
        return carregaUnidade;
    }
    public void setCarregaUnidade(boolean carregaUnidade) {
        this.carregaUnidade = carregaUnidade;
    }
    public boolean isCarregaVeiculo() {
        return carregaVeiculo;
    }
    public void setCarregaVeiculo(boolean carregaVeiculo) {
        this.carregaVeiculo = carregaVeiculo;
    }
    public double getPesagem_Carga() {
        return Pesagem_Carga;
    }
    public void setPesagem_Carga(double pesagem_Carga) {
        Pesagem_Carga = pesagem_Carga;
    }
}