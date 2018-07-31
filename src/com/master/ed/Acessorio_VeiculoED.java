package com.master.ed;

/**
 * <p>Title: Acessorio_VeiculoED </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

public class Acessorio_VeiculoED extends MasterED implements java.io.Serializable{

    private long oid_Tipo_Acessorio;
    private String NM_Acessorio_Veiculo;

    private String oid_Veiculo;
    private String oid_Acessorio_Veiculo;
    private double VL_Quantidade;
  private String CD_Estoque;
  private String NM_Estoque;
  private long oid_Estoque;
  private String DM_Acessorio_Veiculo;
  private long oid_Movimento_Ordem_Servico;
  private String DM_Tipo_Acessorio;


    public String getNM_Acessorio_Veiculo() {
        return NM_Acessorio_Veiculo;
    }
    public void setNM_Acessorio_Veiculo(String Acessorio_Veiculo) {
        NM_Acessorio_Veiculo = Acessorio_Veiculo;
    }
    public String getOid_Acessorio_Veiculo() {
        return oid_Acessorio_Veiculo;
    }
    public void setOid_Acessorio_Veiculo(String oid_Acessorio_Veiculo) {
        this.oid_Acessorio_Veiculo = oid_Acessorio_Veiculo;
    }
    public String getOid_Veiculo() {
        return oid_Veiculo;
    }
    public void setOid_Veiculo(String oid_Veiculo) {
        this.oid_Veiculo = oid_Veiculo;
    }
    public double getVL_Quantidade() {
        return VL_Quantidade;
    }
    public void setVL_Quantidade(double quantidade) {
        VL_Quantidade = quantidade;
    }
  public void setCD_Estoque(String CD_Estoque) {
    this.CD_Estoque = CD_Estoque;
  }
  public String getCD_Estoque() {
    return CD_Estoque;
  }
  public void setNM_Estoque(String NM_Estoque) {
    this.NM_Estoque = NM_Estoque;
  }
  public String getNM_Estoque() {
    return NM_Estoque;
  }
  public void setOid_Estoque(long oid_Estoque) {
    this.oid_Estoque = oid_Estoque;
  }
  public long getOid_Estoque() {
    return oid_Estoque;
  }
  public void setDM_Acessorio_Veiculo(String DM_Acessorio_Veiculo) {
    this.DM_Acessorio_Veiculo = DM_Acessorio_Veiculo;
  }
  public String getDM_Acessorio_Veiculo() {
    return DM_Acessorio_Veiculo;
  }
  public void setOid_Movimento_Ordem_Servico(long oid_Movimento_Ordem_Servico) {
    this.oid_Movimento_Ordem_Servico = oid_Movimento_Ordem_Servico;
  }
  public long getOid_Movimento_Ordem_Servico() {
    return oid_Movimento_Ordem_Servico;
  }
  public long getOid_Tipo_Acessorio() {
    return oid_Tipo_Acessorio;
  }
  public void setOid_Tipo_Acessorio(long oid_Tipo_Acessorio) {
    this.oid_Tipo_Acessorio = oid_Tipo_Acessorio;
  }
  public void setDM_Tipo_Acessorio(String DM_Tipo_Acessorio) {
    this.DM_Tipo_Acessorio = DM_Tipo_Acessorio;
  }
  public String getDM_Tipo_Acessorio() {
    return DM_Tipo_Acessorio;
  }
}

