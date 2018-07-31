package com.master.ed;

import com.master.util.JavaUtil;
import com.master.util.Valores;

public class Programacao_CargaED {

	public Programacao_CargaED(long oid) {

	}

	public Programacao_CargaED() {

	}

	private long oid_Programacao_Carga;
	private long oid_Pedido_Carga;
	private double nr_Volumes;
	private String nr_Volumes_TX;
	private String dm_Veiculo;
	private String dt_Programacao;
	private String dt_Carga_Efetiva;
	private String oid_Motorista;
	private String dm_Situacao;
	private String dm_Motivo_Cancelamento;
	private String oid_Veiculo;
	private String oid_Conhecimento;
	private String oid_Coleta;
	private long oid_Unidade;

	private String dm_Situacao_TX;
	private String dm_Veiculo_TX;

//	Consulta
	private String dt_Programacao_Final;
	private String dt_Carga_Efetiva_Final;

//	Descritivos de FK's
	private String nr_Cnpj_Cpf_Motorista;
	private String nm_Motorista;
	private String nr_Placa;
	private String nr_Conhecimento;

	//PEDIDO - RELATORIO
	private long oid_Produto;
	private String oid_Cliente;
	private String oid_Pessoa_Origem;
	private String oid_Pessoa_Destino;
	private double nr_Volumes_Prog;
	private double nr_Volumes_Canc;
	private double nr_Volumes_Efet;
	private double nr_Volumes_Tot;
	private String dm_Volumes;
	private String dt_Pedido;
	private String dt_Prazo;
	private double vl_Tarifa;
	private double vl_Pedagio;
	private double vl_Tarifa_Bitrem;
	private double vl_Pedagio_Bitrem;

	private double vl_Frete;
	private String dm_Carga;
	private String tx_Frete;
	private String dt_Saida;
	private String dt_Entrega;
	private String hr_Entrega;
	private String tx_Observacao;
	private double vl_Seguro;
	private String dm_ICMS;
	private String dm_Descarga;

	//Consulta
	private String dt_Pedido_Final;
	private String dt_Prazo_Final;

	//Descritivos de FK's
	private String nr_Cnpj_Cpf_Cliente;
	private String nr_Cnpj_Cpf_Origem;
	private String nr_Cnpj_Cpf_Destino;
	private String nm_Cliente;
	private String nm_Origem;
	private String nm_Destino;
	private String cd_Produto;
	private String nm_Produto;
	private String cd_Unidade;
	private String nm_Unidade;

	private String oid_Carreta;
	private String oid_Carreta2;

	private String nr_Ordem_Frete;
	private double vl_Ordem_Frete;
	private String oid_Ordem_Frete;


	public String getDescMotivo_Cancelamento(){
		if(JavaUtil.doValida(this.dm_Motivo_Cancelamento))
			if(this.dm_Motivo_Cancelamento.equalsIgnoreCase("S"))
				return "Sem Ve�culo Dispon�vel";
			else if(this.dm_Motivo_Cancelamento.equalsIgnoreCase("C"))
				return "Cancelado pelo Cliente";
			else if(this.dm_Motivo_Cancelamento.equalsIgnoreCase("O"))
				return "Outros";
			else return "N/A";
		else return "N/A";
	}

	public String getDescSituacao(){
		if(JavaUtil.doValida(this.dm_Situacao))
			if(this.dm_Situacao.equalsIgnoreCase("A"))
				return "Aberta";
			else if(this.dm_Situacao.equalsIgnoreCase("C"))
				return "Cancelada";
			else if(this.dm_Situacao.equalsIgnoreCase("L"))
				return "Liberada";
			else if(this.dm_Situacao.equalsIgnoreCase("V"))
				return "Vinculada";
			else return "N/A";
		else return "N/A";
	}

	public String getDescVeiculo(){
		if(JavaUtil.doValida(this.dm_Veiculo))
			if(this.dm_Veiculo.equalsIgnoreCase("C"))
				return "Carreta";
			else if(this.dm_Veiculo.equalsIgnoreCase("B"))
				return "Bitrem";
			else if(this.dm_Veiculo.equalsIgnoreCase("O"))
				return "Fracionado";
			else if(this.dm_Veiculo.equalsIgnoreCase("K"))
				return "Truck";
			else if(this.dm_Veiculo.equalsIgnoreCase("15"))
				return "Carreta 15";
			else if(this.dm_Veiculo.equalsIgnoreCase("R"))
				return "Rebaixada";
			else if(this.dm_Veiculo.equalsIgnoreCase("P"))
				return "Prancha";
			else if(this.dm_Veiculo.equalsIgnoreCase("L"))
				return "Lagartixa";
			else if(this.dm_Veiculo.equalsIgnoreCase("E"))
				return "Extensiva";
			else if(this.dm_Veiculo.equalsIgnoreCase("S"))
				return "Cav. Mec. Simples";
			else if(this.dm_Veiculo.equalsIgnoreCase("U"))
				return "Cav. Mec. Trucado";
			else if(this.dm_Veiculo.equalsIgnoreCase("A"))
				return "Cav. Mec. Tracado";
			else if(this.dm_Veiculo.equalsIgnoreCase("M"))
				return "Truck c/ Munck";
			else if(this.dm_Veiculo.equalsIgnoreCase("G"))
				return "Carreta c/ Guincho";
			else if(this.dm_Veiculo.equalsIgnoreCase("V"))
				return "Valor do Transporte";
			else return "N/A";
		else return "N/A";
	}

	public String getDm_Motivo_Cancelamento() {
		return dm_Motivo_Cancelamento;
	}
	public void setDm_Motivo_Cancelamento(String dm_Motivo_Cancelamento) {
		this.dm_Motivo_Cancelamento = dm_Motivo_Cancelamento;
	}
	public String getDm_Situacao() {
		return dm_Situacao;
	}
	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
		this.dm_Situacao_TX = getDescSituacao();
	}
	public String getDm_Veiculo() {
		return dm_Veiculo;
	}
	public void setDm_Veiculo(String dm_Veiculo) {
		this.dm_Veiculo = dm_Veiculo;
		this.dm_Veiculo_TX = getDescVeiculo();
	}
	public String getDt_Carga_Efetiva() {
		return dt_Carga_Efetiva;
	}
	public void setDt_Carga_Efetiva(String dt_Carga_Efetiva) {
		this.dt_Carga_Efetiva = dt_Carga_Efetiva;
	}
	public String getDt_Programacao() {
		return dt_Programacao;
	}
	public void setDt_Programacao(String dt_Programacao) {
		this.dt_Programacao = dt_Programacao;
	}
	public double getNr_Volumes() {
		return nr_Volumes;
	}
	public void setNr_Volumes(double nr_Volumes) {
		this.nr_Volumes = nr_Volumes;
	}
	public String getNr_Volumes_TX() {
		return nr_Volumes_TX;
	}
	public void setNr_Volumes_TX(String nr_Volumes_TX) {
		this.nr_Volumes_TX = nr_Volumes_TX;
		this.setNr_Volumes(Valores.converteStringToDouble(nr_Volumes_TX));
	}
	public String getOid_Motorista() {
		return oid_Motorista;
	}
	public void setOid_Motorista(String oid_Motorista) {
		this.oid_Motorista = oid_Motorista;
	}
	public long getOid_Pedido_Carga() {
		return oid_Pedido_Carga;
	}
	public void setOid_Pedido_Carga(long oid_Pedido_Carga) {
		this.oid_Pedido_Carga = oid_Pedido_Carga;
	}
	public long getOid_Programacao_Carga() {
		return oid_Programacao_Carga;
	}
	public void setOid_Programacao_Carga(long oid_Programacao_Carga) {
		this.oid_Programacao_Carga = oid_Programacao_Carga;
	}
	public String getNm_Motorista() {
		return nm_Motorista;
	}
	public void setNm_Motorista(String nm_Motorista) {
		this.nm_Motorista = nm_Motorista;
	}
	public String getNr_Cnpj_Cpf_Motorista() {
		return nr_Cnpj_Cpf_Motorista;
	}
	public void setNr_Cnpj_Cpf_Motorista(String nr_Cnpj_Cpf_Motorista) {
		this.nr_Cnpj_Cpf_Motorista = nr_Cnpj_Cpf_Motorista;
	}

	public String getOid_Veiculo() {
		return oid_Veiculo;
	}

	public void setOid_Veiculo(String oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}

	public String getDt_Carga_Efetiva_Final() {
		return dt_Carga_Efetiva_Final;
	}

	public void setDt_Carga_Efetiva_Final(String dt_Carga_Efetiva_Final) {
		this.dt_Carga_Efetiva_Final = dt_Carga_Efetiva_Final;
	}

	public String getDt_Programacao_Final() {
		return dt_Programacao_Final;
	}

	public void setDt_Programacao_Final(String dt_Programacao_Final) {
		this.dt_Programacao_Final = dt_Programacao_Final;
	}

	public String getNr_Placa() {
		return nr_Placa;
	}

	public void setNr_Placa(String nr_Placa) {
		this.nr_Placa = nr_Placa;
	}

	public String getNr_Conhecimento() {
		return nr_Conhecimento;
	}

	public void setNr_Conhecimento(String nr_Conhecimento) {
		this.nr_Conhecimento = nr_Conhecimento;
	}

	public String getOid_Conhecimento() {
		return oid_Conhecimento;
	}

	public void setOid_Conhecimento(String oid_Conhecimento) {
		this.oid_Conhecimento = oid_Conhecimento;
	}

	public String getDm_Situacao_TX() {
		return dm_Situacao_TX;
	}

	public void setDm_Situacao_TX(String dm_Situacao_TX) {
		this.dm_Situacao_TX = dm_Situacao_TX;
	}

	public String getDm_Veiculo_TX() {
		return dm_Veiculo_TX;
	}

	public void setDm_Veiculo_TX(String dm_Veiculo_TX) {
		this.dm_Veiculo_TX = dm_Veiculo_TX;
	}

	public String getCd_Produto() {
		return cd_Produto;
	}

	public void setCd_Produto(String cd_Produto) {
		this.cd_Produto = cd_Produto;
	}

	public String getDm_Volumes() {
		return dm_Volumes;
	}

	public void setDm_Volumes(String dm_Volumes) {
		this.dm_Volumes = dm_Volumes;
	}

	public String getDt_Pedido() {
		return dt_Pedido;
	}

	public void setDt_Pedido(String dt_Pedido) {
		this.dt_Pedido = dt_Pedido;
	}

	public String getDt_Pedido_Final() {
		return dt_Pedido_Final;
	}

	public void setDt_Pedido_Final(String dt_Pedido_Final) {
		this.dt_Pedido_Final = dt_Pedido_Final;
	}

	public String getDt_Prazo() {
		return dt_Prazo;
	}

	public void setDt_Prazo(String dt_Prazo) {
		this.dt_Prazo = dt_Prazo;
	}

	public String getDt_Prazo_Final() {
		return dt_Prazo_Final;
	}

	public void setDt_Prazo_Final(String dt_Prazo_Final) {
		this.dt_Prazo_Final = dt_Prazo_Final;
	}

	public String getNm_Cliente() {
		return nm_Cliente;
	}

	public void setNm_Cliente(String nm_Cliente) {
		this.nm_Cliente = nm_Cliente;
	}

	public String getNm_Destino() {
		return nm_Destino;
	}

	public void setNm_Destino(String nm_Destino) {
		this.nm_Destino = nm_Destino;
	}

	public String getNm_Origem() {
		return nm_Origem;
	}

	public void setNm_Origem(String nm_Origem) {
		this.nm_Origem = nm_Origem;
	}

	public String getNm_Produto() {
		return nm_Produto;
	}

	public void setNm_Produto(String nm_Produto) {
		this.nm_Produto = nm_Produto;
	}

	public String getNr_Cnpj_Cpf_Cliente() {
		return nr_Cnpj_Cpf_Cliente;
	}

	public void setNr_Cnpj_Cpf_Cliente(String nr_Cnpj_Cpf_Cliente) {
		this.nr_Cnpj_Cpf_Cliente = nr_Cnpj_Cpf_Cliente;
	}

	public String getNr_Cnpj_Cpf_Destino() {
		return nr_Cnpj_Cpf_Destino;
	}

	public void setNr_Cnpj_Cpf_Destino(String nr_Cnpj_Cpf_Destino) {
		this.nr_Cnpj_Cpf_Destino = nr_Cnpj_Cpf_Destino;
	}

	public String getNr_Cnpj_Cpf_Origem() {
		return nr_Cnpj_Cpf_Origem;
	}

	public void setNr_Cnpj_Cpf_Origem(String nr_Cnpj_Cpf_Origem) {
		this.nr_Cnpj_Cpf_Origem = nr_Cnpj_Cpf_Origem;
	}

	public double getNr_Volumes_Canc() {
		return nr_Volumes_Canc;
	}

	public void setNr_Volumes_Canc(double nr_Volumes_Canc) {
		this.nr_Volumes_Canc = nr_Volumes_Canc;
	}

	public double getNr_Volumes_Efet() {
		return nr_Volumes_Efet;
	}

	public void setNr_Volumes_Efet(double nr_Volumes_Efet) {
		this.nr_Volumes_Efet = nr_Volumes_Efet;
	}

	public double getNr_Volumes_Prog() {
		return nr_Volumes_Prog;
	}

	public void setNr_Volumes_Prog(double nr_Volumes_Prog) {
		this.nr_Volumes_Prog = nr_Volumes_Prog;
	}

	public double getNr_Volumes_Tot() {
		return nr_Volumes_Tot;
	}

	public void setNr_Volumes_Tot(double nr_Volumes_Tot) {
		this.nr_Volumes_Tot = nr_Volumes_Tot;
	}

	public String getOid_Cliente() {
		return oid_Cliente;
	}

	public void setOid_Cliente(String oid_Cliente) {
		this.oid_Cliente = oid_Cliente;
	}

	public String getOid_Pessoa_Destino() {
		return oid_Pessoa_Destino;
	}

	public void setOid_Pessoa_Destino(String oid_Pessoa_Destino) {
		this.oid_Pessoa_Destino = oid_Pessoa_Destino;
	}

	public String getOid_Pessoa_Origem() {
		return oid_Pessoa_Origem;
	}

	public void setOid_Pessoa_Origem(String oid_Pessoa_Origem) {
		this.oid_Pessoa_Origem = oid_Pessoa_Origem;
	}

	public long getOid_Produto() {
		return oid_Produto;
	}

	public void setOid_Produto(long oid_Produto) {
		this.oid_Produto = oid_Produto;
	}

	public double getVl_Pedagio() {
		return vl_Pedagio;
	}

	public void setVl_Pedagio(double vl_Pedagio) {
		this.vl_Pedagio = vl_Pedagio;
	}

	public double getVl_Pedagio_Bitrem() {
		return vl_Pedagio_Bitrem;
	}

	public void setVl_Pedagio_Bitrem(double vl_Pedagio_Bitrem) {
		this.vl_Pedagio_Bitrem = vl_Pedagio_Bitrem;
	}

	public double getVl_Tarifa() {
		return vl_Tarifa;
	}

	public void setVl_Tarifa(double vl_Tarifa) {
		this.vl_Tarifa = vl_Tarifa;
	}

	public double getVl_Tarifa_Bitrem() {
		return vl_Tarifa_Bitrem;
	}

	public void setVl_Tarifa_Bitrem(double vl_Tarifa_Bitrem) {
		this.vl_Tarifa_Bitrem = vl_Tarifa_Bitrem;
	}

	public String getOid_Carreta() {
		return oid_Carreta;
	}

	public void setOid_Carreta(String oid_Carreta) {
		this.oid_Carreta = oid_Carreta;
	}

	public String getOid_Carreta2() {
		return oid_Carreta2;
	}

	public void setOid_Carreta2(String oid_Carreta2) {
		this.oid_Carreta2 = oid_Carreta2;
	}

	public String getDm_Carga() {
		return dm_Carga;
	}

	public void setDm_Carga(String dm_Carga) {
		this.dm_Carga = dm_Carga;
	}

	public double getVl_Frete() {
		return vl_Frete;
	}

	public void setVl_Frete(double vl_Frete) {
		this.vl_Frete = vl_Frete;
		this.setTx_Frete(Valores.converteDoubleToString(vl_Frete));
	}

	public String getTx_Frete() {
		return tx_Frete;
	}

	public void setTx_Frete(String tx_Frete) {
		this.tx_Frete = tx_Frete;
		this.vl_Frete=(Valores.converteStringToDouble(tx_Frete));
	}

	public String getDt_Entrega() {
		return dt_Entrega;
	}

	public void setDt_Entrega(String dt_Entrega) {
		this.dt_Entrega = dt_Entrega;
	}

	public String getDt_Saida() {
		return dt_Saida;
	}

	public void setDt_Saida(String dt_Saida) {
		this.dt_Saida = dt_Saida;
	}

	public String getHr_Entrega() {
		return hr_Entrega;
	}

	public void setHr_Entrega(String hr_Entrega) {
		this.hr_Entrega = hr_Entrega;
	}

	public String getTx_Observacao() {
		return tx_Observacao;
	}

	public void setTx_Observacao(String tx_Observacao) {
		this.tx_Observacao = tx_Observacao;
	}

	public String getDm_Descarga() {
		return dm_Descarga;
	}

	public void setDm_Descarga(String dm_Descarga) {
		this.dm_Descarga = dm_Descarga;
	}

	public String getDm_ICMS() {
		return dm_ICMS;
	}

	public void setDm_ICMS(String dm_ICMS) {
		this.dm_ICMS = dm_ICMS;
	}

	public double getVl_Seguro() {
		return vl_Seguro;
	}

	public void setVl_Seguro(double vl_Seguro) {
		this.vl_Seguro = vl_Seguro;
	}

	public String getOid_Coleta() {
		return oid_Coleta;
	}

	public void setOid_Coleta(String oid_Coleta) {
		this.oid_Coleta = oid_Coleta;
	}

	public String getNr_Ordem_Frete() {
		return nr_Ordem_Frete;
	}

	public void setNr_Ordem_Frete(String nr_Ordem_Frete) {
		this.nr_Ordem_Frete = nr_Ordem_Frete;
	}

	public String getOid_Ordem_Frete() {
		return oid_Ordem_Frete;
	}

	public void setOid_Ordem_Frete(String oid_Ordem_Frete) {
		this.oid_Ordem_Frete = oid_Ordem_Frete;
	}

	public double getVl_Ordem_Frete() {
		return vl_Ordem_Frete;
	}

	public void setVl_Ordem_Frete(double vl_Ordem_Frete) {
		this.vl_Ordem_Frete = vl_Ordem_Frete;
	}

	public long getOid_Unidade() {
		return oid_Unidade;
	}

	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}

	public String getCd_Unidade() {
		return cd_Unidade;
	}

	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}

	public String getNm_Unidade() {
		return nm_Unidade;
	}

	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}

}
