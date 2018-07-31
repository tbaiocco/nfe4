package com.master.ed;

public class LicenciamentoED extends MasterED {

    public LicenciamentoED() {
        super();
  }
    public LicenciamentoED(String oid_Licenciamento) {
        this.oid_Licenciamento = oid_Licenciamento;
    }

  private String oid_Licenciamento;
  private String dt_Limite;
  private String nr_Dezena_Placa;

public String getDt_Limite() {
	return dt_Limite;
}
public void setDt_Limite(String dt_Limite) {
	this.dt_Limite = dt_Limite;
}
public String getNr_Dezena_Placa() {
	return nr_Dezena_Placa;
}
public void setNr_Dezena_Placa(String nr_Dezena_Placa) {
	this.nr_Dezena_Placa = nr_Dezena_Placa;
}
public String getOid_Licenciamento() {
	return oid_Licenciamento;
}
public void setOid_Licenciamento(String oid_Licenciamento) {
	this.oid_Licenciamento = oid_Licenciamento;
}

}
