package com.master.ed;

import java.io.Serializable;

import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;

/**
 * @author André Valadas
 */
public class Ordem_EmbarqueED extends MasterED implements Serializable {

    public Ordem_EmbarqueED() {
        super();
    }
    public Ordem_EmbarqueED(int oid_Ordem_Embarque) {
        this.oid_Ordem_Embarque = oid_Ordem_Embarque;
    }
    public Ordem_EmbarqueED(int oid_Ordem_Embarque, int oid_Ordem_Servico) {
        this.oid_Ordem_Embarque = oid_Ordem_Embarque;
        this.oid_Ordem_Servico = oid_Ordem_Servico;
    }
    public Ordem_EmbarqueED(boolean Tecnico, boolean Ordem_Servico, boolean veiculo) {
        carregaTecnico = Tecnico;
        carregaOrdem_Servico = Ordem_Servico;
        carregaVeiculo = veiculo;
    }
    public Ordem_EmbarqueED(int oid_Ordem_Embarque, boolean Tecnico, boolean Ordem_Servico, boolean veiculo) {
        this.oid_Ordem_Embarque = oid_Ordem_Embarque;
        carregaTecnico = Tecnico;
        carregaOrdem_Servico = Ordem_Servico;
        carregaVeiculo = veiculo;
    }
    
    public VeiculoBean edVeiculo = new VeiculoBean();
    
    private boolean carregaTecnico = true;
    private boolean carregaOrdem_Servico = true;
    private boolean carregaVeiculo = true;
    
    private String cd_ordem_servico;
    private String nm_tecnico;
    private String nr_placa;

	public String getCd_ordem_servico() {
		return cd_ordem_servico;
	}
	public void setCd_ordem_servico(String cd_ordem_servico) {
		this.cd_ordem_servico = cd_ordem_servico;
	}
	public String getNm_tecnico() {
		return nm_tecnico;
	}
	public void setNm_tecnico(String nm_tecnico) {
		this.nm_tecnico = nm_tecnico;
	}
	public String getNr_placa() {
		return nr_placa;
	}
	public void setNr_placa(String nr_placa) {
		this.nr_placa = nr_placa;
	}
    private int oid_Ordem_Embarque;
    private int oid_Ordem_Servico;
    private int oid_Tecnico;
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
    public int getOid_Ordem_Embarque() {
        return oid_Ordem_Embarque;
    }
    public int getOid_Tecnico() {
        return oid_Tecnico;
    }
    public int getOid_Ordem_Servico() {
        return oid_Ordem_Servico;
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
    public void setOid_Ordem_Embarque(int oid_Ordem_Embarque) {
        this.oid_Ordem_Embarque = oid_Ordem_Embarque;
    }
    public void setOid_Tecnico(int oid_Tecnico) {
        this.oid_Tecnico = oid_Tecnico;
    }
    public void setOid_Ordem_Servico(int oid_Ordem_Servico) {
        this.oid_Ordem_Servico = oid_Ordem_Servico;
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
    public boolean isCarregaTecnico() {
        return carregaTecnico;
    }
    public void setCarregaTecnico(boolean carregaTecnico) {
        this.carregaTecnico = carregaTecnico;
    }
    public boolean isCarregaOrdem_Servico() {
        return carregaOrdem_Servico;
    }
    public void setCarregaOrdem_Servico(boolean carregaOrdem_Servico) {
        this.carregaOrdem_Servico = carregaOrdem_Servico;
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