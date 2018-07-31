/*
 * Created on 25/06/2007
 *
 */
package com.master.ed;

import java.util.Collection;

/**
 * @author Cristian Vianna Garcia_Frota
 * @author ALTERADO POR: (Ralph Renato - "chassi-08/2007")
 * @serial CadasTro de Veículos
 * @serialData 06/2007
 */
public class VeiculoED extends RelatorioBaseED {

	private static final long serialVersionUID = -2670106759671649079L;
	// V E Í C U L O
	private String oid_Veiculo;
	private long oid_Empresa;
	private String nr_Frota;
	private String nr_Placa;
	private long oid_Modelo_Veiculo;
	private String nm_Modelo_Veiculo;
	private long oid_Marca_Veiculo;
	private String nm_Marca_Veiculo;
	private long oid_Tipo_Veiculo;
	private String nm_Tipo_Veiculo;
	private String nm_Tipo_Roda;
	private long oid_Unidade;
	private String nm_Unidade;
	private double nr_Kilometragem_Atual;
	private double nr_Odometro;
	private long dm_Odometro_Maximo;
	private long dm_Qtd_Estepes;
	private long dm_Tipo_Chassis;
	private long dm_Combustivel;
	private long oid_Centro_Custo;
	private String nm_Centro_Custo;
	private long oid_Unidade_Federativa;

	private long nr_Capacidade_Tanque;
	private long oid_Motorista;
	private long nr_Ano_Fabricacao;
	private long nr_Ano_Modelo;
	private double nr_Media_Inferior;
	private double nr_Media_Superior;
	private String nr_Chassis;
	private String nr_Renavan;
	private String nm_Cor;

	// Indicador F se o ed foi montado no gen013.
	transient String dm_Frota;

	// C H A S S I
	private String de;
	private String d2e;
	private String tee;
	private String tei;
	private String tkee;
	private String tkei;
	private String l1ee;
	private String l1ei;
	private String l2ee;
	private String l2ei;
	private String l3ee;
	private String l3ei;
	private String l4ee;
	private String l4ei;
	private String l5ee;
	private String l5ei;
	private String l6ee;
	private String l6ei;
	private String l7ee;
	private String l7ei;
	private String l8ee;
	private String l8ei;
	private String dd;
	private String d2d;
	private String tde;
	private String tdi;
	private String tkde;
	private String tkdi;
	private String l1de;
	private String l1di;
	private String l2de;
	private String l2di;
	private String l3de;
	private String l3di;
	private String l4de;
	private String l4di;
	private String l5de;
	private String l5di;
	private String l6de;
	private String l6di;
	private String l7de;
	private String l7di;
	private String l8de;
	private String l8di;
	private String stp1;
	private String stp2;

	public void defaultPosicoes () {
		this.setStp1("STP1");
		this.setStp2("STP2");
		this.setDe("DE");
		this.setD2e("D2E");
		this.setTee("TEE");
		this.setTei("TEI");
		this.setTkee("TKEE");
		this.setTkei("TKEI");
		this.setL1ee("L1EE");
		this.setL1ei("L1EI");
		this.setL2ee("L2EE");
		this.setL2ei("L2EI");
		this.setL3ee("L3EE");
		this.setL3ei("L3EI");
		this.setL4ee("L4EE");
		this.setL4ei("L4EI");
		this.setL5ee("L5EE");
		this.setL5ei("L5EI");
		this.setL6ee("L6EE");
		this.setL6ei("L6EI");
		this.setL7ee("L7EE");
		this.setL7ei("L7EI");
		this.setL8ee("L8EE");
		this.setL8ei("L8EI");
		this.setDd("DD");
		this.setD2d("D2D");
		this.setTde("TDE");
		this.setTdi("TDI");
		this.setTkde("TKDE");
		this.setTkdi("TKDI");
		this.setL1de("L1DE");
		this.setL1di("L1DI");
		this.setL2de("L2DE");
		this.setL2di("L2DI");
		this.setL3de("L3DE");
		this.setL3di("L3DI");
		this.setL4de("L4DE");
		this.setL4di("L4DI");
		this.setL5de("L5DE");
		this.setL5di("L5DI");
		this.setL6de("L6DE");
		this.setL6di("L6DI");
		this.setL7de("L7DE");
		this.setL7di("L7DI");
		this.setL8de("L8DE");
		this.setL8di("L8DI");
	}

//	Lista Situação da Frota
	private long nr_Total;
	private long nr_Qtd_N0;
	private long nr_Qtd_R1;
	private long nr_Qtd_R2;
	private long nr_Qtd_R3;
	private long nr_Qtd_R4;
	private long nr_Qtd_R5;
	private long nr_Qtd_R6;

	private double nr_Pct_N0;
	private double nr_Pct_R1;
	private double nr_Pct_R2;
	private double nr_Pct_R3;
	private double nr_Pct_R4;
	private double nr_Pct_R5;
	private double nr_Pct_R6;
	private String nm_Dimensao_Pneu;

	private String img_Chassis;
	private Collection posicoes;

	public long getDm_Odometro_Maximo() {
		return dm_Odometro_Maximo;
	}
	public void setDm_Odometro_Maximo(long dm_Odometro_Maximo) {
		this.dm_Odometro_Maximo = dm_Odometro_Maximo;
	}
	public long getDm_Qtd_Estepes() {
		return dm_Qtd_Estepes;
	}
	public void setDm_Qtd_Estepes(long dm_Qtd_Estepes) {
		this.dm_Qtd_Estepes = dm_Qtd_Estepes;
	}
	public long getDm_Tipo_Chassis() {
		return dm_Tipo_Chassis;
	}
	public void setDm_Tipo_Chassis(long dm_Tipo_Chassis) {
		this.dm_Tipo_Chassis = dm_Tipo_Chassis;
	}
	public String getNm_Marca_Veiculo() {
		return nm_Marca_Veiculo;
	}
	public void setNm_Marca_Veiculo(String nm_Marca_Veiculo) {
		this.nm_Marca_Veiculo = nm_Marca_Veiculo;
	}
	public String getNm_Modelo_Veiculo() {
		return nm_Modelo_Veiculo;
	}
	public void setNm_Modelo_Veiculo(String nm_Modelo_Veiculo) {
		this.nm_Modelo_Veiculo = nm_Modelo_Veiculo;
	}
	public String getNm_Tipo_Roda() {
		return nm_Tipo_Roda;
	}
	public void setNm_Tipo_Roda(String nm_Tipo_Roda) {
		this.nm_Tipo_Roda = nm_Tipo_Roda;
	}
	public String getNm_Tipo_Veiculo() {
		return nm_Tipo_Veiculo;
	}
	public void setNm_Tipo_Veiculo(String nm_Tipo_Veiculo) {
		this.nm_Tipo_Veiculo = nm_Tipo_Veiculo;
	}
	public String getNm_Unidade() {
		return nm_Unidade;
	}
	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}
	public String getNr_Frota() {
		return nr_Frota;
	}
	public void setNr_Frota(String nr_Frota) {
		this.nr_Frota = nr_Frota;
	}
	public double getNr_Kilometragem_Atual() {
		return nr_Kilometragem_Atual;
	}
	public void setNr_Kilometragem_Atual(double nr_Kilometragem_Atual) {
		this.nr_Kilometragem_Atual = nr_Kilometragem_Atual;
	}
	public double getNr_Odometro() {
		return nr_Odometro;
	}
	public void setNr_Odometro(double nr_Odometro) {
		this.nr_Odometro = nr_Odometro;
	}
	public String getNr_Placa() {
		return nr_Placa;
	}
	public void setNr_Placa(String nr_Placa) {
		this.nr_Placa = nr_Placa;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Marca_Veiculo() {
		return oid_Marca_Veiculo;
	}
	public void setOid_Marca_Veiculo(long oid_Marca_Veiculo) {
		this.oid_Marca_Veiculo = oid_Marca_Veiculo;
	}
	public long getOid_Modelo_Veiculo() {
		return oid_Modelo_Veiculo;
	}
	public void setOid_Modelo_Veiculo(long oid_Modelo_Veiculo) {
		this.oid_Modelo_Veiculo = oid_Modelo_Veiculo;
	}
	public long getOid_Tipo_Veiculo() {
		return oid_Tipo_Veiculo;
	}
	public void setOid_Tipo_Veiculo(long oid_Tipo_Veiculo) {
		this.oid_Tipo_Veiculo = oid_Tipo_Veiculo;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public String getOid_Veiculo() {
		return oid_Veiculo;
	}
	public void setOid_Veiculo(String oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	//P O S I Ç Õ E S  ***  C H A S S I ****

	public String getD2d() {
		return d2d;
	}
	public void setD2d(String d2d) {
		this.d2d = d2d;
	}
	public String getD2e() {
		return d2e;
	}
	public void setD2e(String d2e) {
		this.d2e = d2e;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getL1de() {
		return l1de;
	}
	public void setL1de(String l1de) {
		this.l1de = l1de;
	}
	public String getL1di() {
		return l1di;
	}
	public void setL1di(String l1di) {
		this.l1di = l1di;
	}
	public String getL1ee() {
		return l1ee;
	}
	public void setL1ee(String l1ee) {
		this.l1ee = l1ee;
	}
	public String getL1ei() {
		return l1ei;
	}
	public void setL1ei(String l1ei) {
		this.l1ei = l1ei;
	}
	public String getL2de() {
		return l2de;
	}
	public void setL2de(String l2de) {
		this.l2de = l2de;
	}
	public String getL2di() {
		return l2di;
	}
	public void setL2di(String l2di) {
		this.l2di = l2di;
	}
	public String getL2ee() {
		return l2ee;
	}
	public void setL2ee(String l2ee) {
		this.l2ee = l2ee;
	}
	public String getL2ei() {
		return l2ei;
	}
	public void setL2ei(String l2ei) {
		this.l2ei = l2ei;
	}
	public String getL3de() {
		return l3de;
	}
	public void setL3de(String l3de) {
		this.l3de = l3de;
	}
	public String getL3di() {
		return l3di;
	}
	public void setL3di(String l3di) {
		this.l3di = l3di;
	}
	public String getL3ee() {
		return l3ee;
	}
	public void setL3ee(String l3ee) {
		this.l3ee = l3ee;
	}
	public String getL3ei() {
		return l3ei;
	}
	public void setL3ei(String l3ei) {
		this.l3ei = l3ei;
	}
	public String getL4de() {
		return l4de;
	}
	public void setL4de(String l4de) {
		this.l4de = l4de;
	}
	public String getL4ee() {
		return l4ee;
	}
	public void setL4ee(String l4ee) {
		this.l4ee = l4ee;
	}
	public String getL4ei() {
		return l4ei;
	}
	public void setL4ei(String l4ei) {
		this.l4ei = l4ei;
	}
	public String getL5de() {
		return l5de;
	}
	public void setL5de(String l5de) {
		this.l5de = l5de;
	}
	public String getL5di() {
		return l5di;
	}
	public void setL5di(String l5di) {
		this.l5di = l5di;
	}
	public String getL5ee() {
		return l5ee;
	}
	public void setL5ee(String l5ee) {
		this.l5ee = l5ee;
	}
	public String getL5ei() {
		return l5ei;
	}
	public void setL5ei(String l5ei) {
		this.l5ei = l5ei;
	}
	public String getL6de() {
		return l6de;
	}
	public void setL6de(String l6de) {
		this.l6de = l6de;
	}
	public String getL6di() {
		return l6di;
	}
	public void setL6di(String l6di) {
		this.l6di = l6di;
	}
	public String getL6ee() {
		return l6ee;
	}
	public void setL6ee(String l6ee) {
		this.l6ee = l6ee;
	}
	public String getL6ei() {
		return l6ei;
	}
	public void setL6ei(String l6ei) {
		this.l6ei = l6ei;
	}
	public String getL7de() {
		return l7de;
	}
	public void setL7de(String l7de) {
		this.l7de = l7de;
	}
	public String getL7di() {
		return l7di;
	}
	public void setL7di(String l7di) {
		this.l7di = l7di;
	}
	public String getL7ee() {
		return l7ee;
	}
	public void setL7ee(String l7ee) {
		this.l7ee = l7ee;
	}
	public String getL7ei() {
		return l7ei;
	}
	public void setL7ei(String l7ei) {
		this.l7ei = l7ei;
	}
	public String getL8de() {
		return l8de;
	}
	public void setL8de(String l8de) {
		this.l8de = l8de;
	}
	public String getL8di() {
		return l8di;
	}
	public void setL8di(String l8di) {
		this.l8di = l8di;
	}
	public String getL8ee() {
		return l8ee;
	}
	public void setL8ee(String l8ee) {
		this.l8ee = l8ee;
	}
	public String getL8ei() {
		return l8ei;
	}
	public void setL8ei(String l8ei) {
		this.l8ei = l8ei;
	}
	public String getTde() {
		return tde;
	}
	public void setTde(String tde) {
		this.tde = tde;
	}
	public String getTdi() {
		return tdi;
	}
	public void setTdi(String tdi) {
		this.tdi = tdi;
	}
	public String getTee() {
		return tee;
	}
	public void setTee(String tee) {
		this.tee = tee;
	}
	public String getTei() {
		return tei;
	}
	public void setTei(String tei) {
		this.tei = tei;
	}
	public String getTkde() {
		return tkde;
	}
	public void setTkde(String tkde) {
		this.tkde = tkde;
	}
	public String getTkdi() {
		return tkdi;
	}
	public void setTkdi(String tkdi) {
		this.tkdi = tkdi;
	}
	public String getTkee() {
		return tkee;
	}
	public void setTkee(String tkee) {
		this.tkee = tkee;
	}
	public String getTkei() {
		return tkei;
	}
	public void setTkei(String tkei) {
		this.tkei = tkei;
	}
	public String getStp1() {
		return stp1;
	}
	public void setStp1(String stp1) {
		this.stp1 = stp1;
	}
	public String getStp2() {
		return stp2;
	}
	public void setStp2(String stp2) {
		this.stp2 = stp2;
	}

	public String getL4di() {
		return l4di;
	}

	public void setL4di(String l4di) {
		this.l4di = l4di;
	}
	public double getNr_Pct_N0() {
		return nr_Pct_N0;
	}
	public void setNr_Pct_N0(double nr_Pct_N0) {
		this.nr_Pct_N0 = nr_Pct_N0;
	}
	public double getNr_Pct_R1() {
		return nr_Pct_R1;
	}
	public void setNr_Pct_R1(double nr_Pct_R1) {
		this.nr_Pct_R1 = nr_Pct_R1;
	}
	public double getNr_Pct_R2() {
		return nr_Pct_R2;
	}
	public void setNr_Pct_R2(double nr_Pct_R2) {
		this.nr_Pct_R2 = nr_Pct_R2;
	}
	public double getNr_Pct_R3() {
		return nr_Pct_R3;
	}
	public void setNr_Pct_R3(double nr_Pct_R3) {
		this.nr_Pct_R3 = nr_Pct_R3;
	}
	public double getNr_Pct_R4() {
		return nr_Pct_R4;
	}
	public void setNr_Pct_R4(double nr_Pct_R4) {
		this.nr_Pct_R4 = nr_Pct_R4;
	}
	public double getNr_Pct_R5() {
		return nr_Pct_R5;
	}
	public void setNr_Pct_R5(double nr_Pct_R5) {
		this.nr_Pct_R5 = nr_Pct_R5;
	}
	public double getNr_Pct_R6() {
		return nr_Pct_R6;
	}
	public void setNr_Pct_R6(double nr_Pct_R6) {
		this.nr_Pct_R6 = nr_Pct_R6;
	}
	public long getNr_Qtd_N0() {
		return nr_Qtd_N0;
	}
	public void setNr_Qtd_N0(long nr_Qtd_N0) {
		this.nr_Qtd_N0 = nr_Qtd_N0;
	}
	public long getNr_Qtd_R1() {
		return nr_Qtd_R1;
	}
	public void setNr_Qtd_R1(long nr_Qtd_R1) {
		this.nr_Qtd_R1 = nr_Qtd_R1;
	}
	public long getNr_Qtd_R2() {
		return nr_Qtd_R2;
	}
	public void setNr_Qtd_R2(long nr_Qtd_R2) {
		this.nr_Qtd_R2 = nr_Qtd_R2;
	}
	public long getNr_Qtd_R3() {
		return nr_Qtd_R3;
	}
	public void setNr_Qtd_R3(long nr_Qtd_R3) {
		this.nr_Qtd_R3 = nr_Qtd_R3;
	}
	public long getNr_Qtd_R4() {
		return nr_Qtd_R4;
	}
	public void setNr_Qtd_R4(long nr_Qtd_R4) {
		this.nr_Qtd_R4 = nr_Qtd_R4;
	}
	public long getNr_Qtd_R5() {
		return nr_Qtd_R5;
	}
	public void setNr_Qtd_R5(long nr_Qtd_R5) {
		this.nr_Qtd_R5 = nr_Qtd_R5;
	}
	public long getNr_Qtd_R6() {
		return nr_Qtd_R6;
	}
	public void setNr_Qtd_R6(long nr_Qtd_R6) {
		this.nr_Qtd_R6 = nr_Qtd_R6;
	}
	public long getNr_Total() {
		return nr_Total;
	}
	public void setNr_Total(long nr_Total) {
		this.nr_Total = nr_Total;
	}
	public String getNm_Dimensao_Pneu() {
		return nm_Dimensao_Pneu;
	}
	public void setNm_Dimensao_Pneu(String nm_Dimensao_Pneu) {
		this.nm_Dimensao_Pneu = nm_Dimensao_Pneu;
	}
	public long getDm_Combustivel() {
		return dm_Combustivel;
	}
	public void setDm_Combustivel(long dm_Combustivel) {
		this.dm_Combustivel = dm_Combustivel;
	}
	public long getOid_Centro_Custo() {
		return oid_Centro_Custo;
	}
	public void setOid_Centro_Custo(long oid_Centro_Custo) {
		this.oid_Centro_Custo = oid_Centro_Custo;
	}
	public long getOid_Unidade_Federativa() {
		return oid_Unidade_Federativa;
	}
	public void setOid_Unidade_Federativa(long oid_Unidade_Federativa) {
		this.oid_Unidade_Federativa = oid_Unidade_Federativa;
	}
	public String getDm_Frota() {
		return dm_Frota;
	}
	public void setDm_Frota(String dm_Frota) {
		this.dm_Frota = dm_Frota;
	}
	public String getNm_Cor() {
		return nm_Cor;
	}
	public void setNm_Cor(String nm_Cor) {
		this.nm_Cor = nm_Cor;
	}
	public long getNr_Ano_Fabricacao() {
		return nr_Ano_Fabricacao;
	}
	public void setNr_Ano_Fabricacao(long nr_Ano_Fabricacao) {
		this.nr_Ano_Fabricacao = nr_Ano_Fabricacao;
	}
	public long getNr_Ano_Modelo() {
		return nr_Ano_Modelo;
	}
	public void setNr_Ano_Modelo(long nr_Ano_Modelo) {
		this.nr_Ano_Modelo = nr_Ano_Modelo;
	}
	public long getNr_Capacidade_Tanque() {
		return nr_Capacidade_Tanque;
	}
	public void setNr_Capacidade_Tanque(long nr_Capacidade_Tanque) {
		this.nr_Capacidade_Tanque = nr_Capacidade_Tanque;
	}
	public String getNr_Chassis() {
		return nr_Chassis;
	}
	public void setNr_Chassis(String nr_Chassis) {
		this.nr_Chassis = nr_Chassis;
	}
	public double getNr_Media_Inferior() {
		return nr_Media_Inferior;
	}
	public void setNr_Media_Inferior(double nr_Media_Inferior) {
		this.nr_Media_Inferior = nr_Media_Inferior;
	}
	public double getNr_Media_Superior() {
		return nr_Media_Superior;
	}
	public void setNr_Media_Superior(double nr_Media_Superior) {
		this.nr_Media_Superior = nr_Media_Superior;
	}
	public String getNr_Renavan() {
		return nr_Renavan;
	}
	public void setNr_Renavan(String nr_Renavan) {
		this.nr_Renavan = nr_Renavan;
	}
	public long getOid_Motorista() {
		return oid_Motorista;
	}
	public void setOid_Motorista(long oid_Motorista) {
		this.oid_Motorista = oid_Motorista;
	}
	public String getNm_Centro_Custo() {
		return nm_Centro_Custo;
	}
	public void setNm_Centro_Custo(String nm_Centro_Custo) {
		this.nm_Centro_Custo = nm_Centro_Custo;
	}
	public String getImg_Chassis() {
		return img_Chassis;
	}
	public void setImg_Chassis(String img_Chassis) {
		this.img_Chassis = img_Chassis;
	}
	public Collection getPosicoes() {
		return posicoes;
	}
	public void setPosicoes(Collection posicoes) {
		this.posicoes = posicoes;
	}

}

