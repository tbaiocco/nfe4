package com.master.ed;

public class RoteiroED extends MasterED{

  private String NM_Roteiro;
  private String CD_Roteiro;
  private long OID_Cidade;
  private long OID_Cidade_Destino;
  private String NM_Cidade_Destino;
  private String NM_Cidade_Origem;
  private String NM_Estado_Origem;
  private String NM_Estado_Destino;
  private long NR_Sequencia;
  private String NM_Motorista;
  private String NR_Placa;
  private double VL_KM_Carreta;
  private double VL_KM_Truck;
  private double VL_KM_Outros;
  private double VL_KM_Carreta_Adto;
  private double VL_KM_Truck_Adto;
  private double VL_KM_Outros_Adto;
  private String TX_Observacao;
  private double NR_KM_Viagem;

  public void setNM_Roteiro(String NM_Roteiro) {
    this.NM_Roteiro = NM_Roteiro;
  }
  public String getNM_Roteiro() {
    return NM_Roteiro;
  }
  public void setCD_Roteiro(String CD_Roteiro) {
    this.CD_Roteiro = CD_Roteiro;
  }
  public String getCD_Roteiro() {
    return CD_Roteiro;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }
  public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }
  public long getOID_Cidade_Destino() {
    return OID_Cidade_Destino;
  }
  public void setNM_Cidade_Destino(String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }
  public String getNM_Cidade_Destino() {
    return NM_Cidade_Destino;
  }
  public void setNM_Cidade_Origem(String NM_Cidade_Origem) {
    this.NM_Cidade_Origem = NM_Cidade_Origem;
  }
  public String getNM_Cidade_Origem() {
    return NM_Cidade_Origem;
  }
  public void setNM_Estado_Origem(String NM_Estado_Origem) {
    this.NM_Estado_Origem = NM_Estado_Origem;
  }
  public String getNM_Estado_Origem() {
    return NM_Estado_Origem;
  }
  public void setNM_Estado_Destino(String NM_Estado_Destino) {
    this.NM_Estado_Destino = NM_Estado_Destino;
  }
  public String getNM_Estado_Destino() {
    return NM_Estado_Destino;
  }
  public void setNR_Sequencia(long NR_Sequencia) {
    this.NR_Sequencia = NR_Sequencia;
  }
  public long getNR_Sequencia() {
    return NR_Sequencia;
  }
  public String getNM_Motorista() {
    return NM_Motorista;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public void setNM_Motorista(String NM_Motorista) {
    this.NM_Motorista = NM_Motorista;
  }
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public void setVL_KM_Carreta(double VL_KM_Carreta) {
    this.VL_KM_Carreta = VL_KM_Carreta;
  }
  public double getVL_KM_Carreta() {
    return VL_KM_Carreta;
  }
  public void setVL_KM_Truck(double VL_KM_Truck) {
    this.VL_KM_Truck = VL_KM_Truck;
  }
  public double getVL_KM_Truck() {
    return VL_KM_Truck;
  }
  public void setVL_KM_Outros(double VL_KM_Outros) {
    this.VL_KM_Outros = VL_KM_Outros;
  }
  public double getVL_KM_Outros() {
    return VL_KM_Outros;
  }

  public void setVL_KM_Carreta_Adto(double VL_KM_Carreta_Adto) {
    this.VL_KM_Carreta_Adto = VL_KM_Carreta_Adto;
  }
  public double getVL_KM_Carreta_Adto() {
    return VL_KM_Carreta_Adto;
  }
  public void setVL_KM_Truck_Adto(double VL_KM_Truck_Adto) {
    this.VL_KM_Truck_Adto = VL_KM_Truck_Adto;
  }
  public double getVL_KM_Truck_Adto() {
    return VL_KM_Truck_Adto;
  }
  public void setVL_KM_Outros_Adto(double VL_KM_Outros_Adto) {
    this.VL_KM_Outros_Adto = VL_KM_Outros_Adto;
  }
  public double getVL_KM_Outros_Adto() {
    return VL_KM_Outros_Adto;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setNR_KM_Viagem (double NR_KM_Viagem) {
    this.NR_KM_Viagem = NR_KM_Viagem;
  }

  public double getNR_KM_Viagem () {
    return NR_KM_Viagem;
  }

}