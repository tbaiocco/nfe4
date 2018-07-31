package com.master.ed;

/**
 * <p>Title: Tipo_AcessorioED </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

public class Tipo_AcessorioED extends MasterED implements java.io.Serializable{

    private long oid_Tipo_Acessorio;
    private String NM_Tipo_Acessorio;

    private String oid_Veiculo;
    private String oid_Acessorio_Veiculo;
    private double VL_Quantidade;
  private String CD_Estoque;
  private String NM_Estoque;
  private long oid_Estoque;
  private String DM_Tipo_Acessorio;
  private long oid_Movimento_Ordem_Servico;


    public String getNM_Tipo_Acessorio() {
        return NM_Tipo_Acessorio;
    }
    public void setNM_Tipo_Acessorio(String tipo_Acessorio) {
        NM_Tipo_Acessorio = tipo_Acessorio;
    }
    public long getOid_Tipo_Acessorio() {
        return oid_Tipo_Acessorio;
    }
    public void setOid_Tipo_Acessorio(long oid_Tipo_Acessorio) {
        this.oid_Tipo_Acessorio = oid_Tipo_Acessorio;
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
  public void setDM_Tipo_Acessorio(String DM_Tipo_Acessorio) {
    this.DM_Tipo_Acessorio = DM_Tipo_Acessorio;
  }
  public String getDM_Tipo_Acessorio() {
    return DM_Tipo_Acessorio;
  }
  public void setOid_Movimento_Ordem_Servico(long oid_Movimento_Ordem_Servico) {
    this.oid_Movimento_Ordem_Servico = oid_Movimento_Ordem_Servico;
  }
  public long getOid_Movimento_Ordem_Servico() {
    return oid_Movimento_Ordem_Servico;
  }
}

