package com.master.ed;

public class RastreamentoED  extends Nota_FiscalED {
  private long NR_Volumes_Item;
  private String NM_Motorista;
  private String NM_Referencia_Rastreamento;
  private double nr_latitude;
  private double nr_longitude;



  public String getNM_Motorista () {
    return NM_Motorista;
  }

  public void setNM_Motorista (String NM_Motorista) {
    this.NM_Motorista = NM_Motorista;
  }

  public String getNM_Referencia_Rastreamento () {
    return NM_Referencia_Rastreamento;
  }

  public void setNM_Referencia_Rastreamento (String NM_Referencia_Rastreamento) {
    this.NM_Referencia_Rastreamento = NM_Referencia_Rastreamento;
  }

  public long getNR_Volumes_Item () {
    return NR_Volumes_Item;
  }

  public void setNR_Volumes_Item (long NR_Volumes_Item) {
    this.NR_Volumes_Item = NR_Volumes_Item;
  }

  public void setNr_latitude (double nr_latitude) {
    this.nr_latitude = nr_latitude;
  }

  public void setNr_longitude (double nr_longitude) {
    this.nr_longitude = nr_longitude;
  }

  public double getNr_latitude () {
    return nr_latitude;
  }

  public double getNr_longitude () {
    return nr_longitude;
  }
}