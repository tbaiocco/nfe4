package com.master.ed;

/**
 * <p>Title: Peca_VeiculoED </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

public class Peca_VeiculoED extends MasterED implements java.io.Serializable{

    private long oid_Tipo_Acessorio;
    private String NM_Peca_Veiculo;

    private String oid_Veiculo;
    private String oid_Peca_Veiculo;
    private double VL_Quantidade;
  private String CD_Estoque;
  private String NM_Estoque;
  private long oid_Estoque;
  private String DM_Peca_Veiculo;
  private long oid_Movimento_Ordem_Servico;
  private String DM_Tipo_Acessorio;
  private long KM_Garantia;
  private String DT_Garantia;
  private String DM_Situacao;
  private String DT_Servico;
  private String NM_Razao_Social;
  private String NM_Situacao;

  public String getNM_Peca_Veiculo() {
        return NM_Peca_Veiculo;
    }
    public void setNM_Peca_Veiculo(String Peca_Veiculo) {
        NM_Peca_Veiculo = Peca_Veiculo;
    }
    public String getOid_Peca_Veiculo() {
        return oid_Peca_Veiculo;
    }
    public void setOid_Peca_Veiculo(String oid_Peca_Veiculo) {
        this.oid_Peca_Veiculo = oid_Peca_Veiculo;
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
  public void setDM_Peca_Veiculo(String DM_Peca_Veiculo) {
    this.DM_Peca_Veiculo = DM_Peca_Veiculo;
  }
  public String getDM_Peca_Veiculo() {
    return DM_Peca_Veiculo;
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

  public String getNM_Situacao () {
    return NM_Situacao;
  }

  public void setOid_Tipo_Acessorio(long oid_Tipo_Acessorio) {
    this.oid_Tipo_Acessorio = oid_Tipo_Acessorio;
  }

  public void setNM_Situacao (String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }

  public void setDM_Tipo_Acessorio(String DM_Tipo_Acessorio) {
    this.DM_Tipo_Acessorio = DM_Tipo_Acessorio;
  }
  public String getDM_Tipo_Acessorio() {
    return DM_Tipo_Acessorio;
  }
  public void setKM_Garantia(long KM_Garantia) {
    this.KM_Garantia = KM_Garantia;
  }
  public long getKM_Garantia() {
    return KM_Garantia;
  }
  public void setDT_Garantia(String DT_Garantia) {
    this.DT_Garantia = DT_Garantia;
  }
  public String getDT_Garantia() {
    return DT_Garantia;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setDT_Servico(String DT_Servico) {
    this.DT_Servico = DT_Servico;
  }
  public String getDT_Servico() {
    return DT_Servico;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
}

