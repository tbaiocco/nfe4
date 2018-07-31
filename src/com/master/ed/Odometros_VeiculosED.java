package com.master.ed;


public class Odometros_VeiculosED extends RelatorioBaseED {
    
	private long OID_Odometro_Veiculo;
	private String OID_Veiculo;
	private String OID_Carreta;
	private long OID_Usuario;
	private String NM_Usuario;
	private String DT_Inclusao;
	private String HR_Inclusao;
	private String NM_Origem_Odometro;	
	private long KM_Informada;
	private long KM_Viagem;
	private long KM_Anterior;
	private long NR_Odometro;
	private long NR_Odometro_Anterior;
	private String DM_Atualizado;
    
    
    public Odometros_VeiculosED() {
    }


	public String getDT_Inclusao() {
		return DT_Inclusao;
	}


	public void setDT_Inclusao(String inclusao) {
		DT_Inclusao = inclusao;
	}


	public String getHR_Inclusao() {
		return HR_Inclusao;
	}


	public void setHR_Inclusao(String inclusao) {
		HR_Inclusao = inclusao;
	}


	public long getKM_Anterior() {
		return KM_Anterior;
	}


	public void setKM_Anterior(long anterior) {
		KM_Anterior = anterior;
	}


	public long getKM_Informada() {
		return KM_Informada;
	}


	public void setKM_Informada(long informada) {
		KM_Informada = informada;
	}


	public String getNM_Origem_Odometro() {
		return NM_Origem_Odometro;
	}


	public void setNM_Origem_Odometro(String origem_Odometro) {
		NM_Origem_Odometro = origem_Odometro;
	}


	public long getNR_Odometro() {
		return NR_Odometro;
	}


	public void setNR_Odometro(long odometro) {
		NR_Odometro = odometro;
	}


	public long getNR_Odometro_Anterior() {
		return NR_Odometro_Anterior;
	}


	public void setNR_Odometro_Anterior(long odometro_Anterior) {
		NR_Odometro_Anterior = odometro_Anterior;
	}


	public long getOID_Odometro_Veiculo() {
		return OID_Odometro_Veiculo;
	}


	public void setOID_Odometro_Veiculo(long odometro_Veiculo) {
		OID_Odometro_Veiculo = odometro_Veiculo;
	}


	public long getOID_Usuario() {
		return OID_Usuario;
	}


	public void setOID_Usuario(long usuario) {
		OID_Usuario = usuario;
	}


	public String getOID_Veiculo() {
		return OID_Veiculo;
	}


	public void setOID_Veiculo(String veiculo) {
		OID_Veiculo = veiculo;
	}


	public String getNM_Usuario() {
		return NM_Usuario;
	}


	public void setNM_Usuario(String usuario) {
		NM_Usuario = usuario;
	}


	public String getDM_Atualizado() {
		return DM_Atualizado;
	}


	public void setDM_Atualizado(String atualizado) {
		DM_Atualizado = atualizado;
	}


	public String getOID_Carreta() {
		return OID_Carreta;
	}


	public void setOID_Carreta(String carreta) {
		OID_Carreta = carreta;
	}


	public long getKM_Viagem() {
		return KM_Viagem;
	}


	public void setKM_Viagem(long viagem) {
		KM_Viagem = viagem;
	}


	
	
    
    
}