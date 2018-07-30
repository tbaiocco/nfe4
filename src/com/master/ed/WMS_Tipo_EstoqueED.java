package com.master.ed;

/**
 * Título: WMS_Tipo_EstoqueED
 * Descrição: Tipos de Estoques - ED
 * Data da criação: 11/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_Tipo_EstoqueED extends MasterED {

    public WMS_Tipo_EstoqueED() {
        super();
    }
    public WMS_Tipo_EstoqueED(int oid_Tipo_Estoque) {
        this.oid_Tipo_Estoque = oid_Tipo_Estoque;
    }
    
  private int oid_Tipo_Estoque;
  private String nm_Tipo_Estoque;
  private String cd_Tipo_Estoque;
  private String DM_Situacao;
  
  public String getDesc_DM_Situacao(){      
      if (this.DM_Situacao != null){
          if (this.DM_Situacao.toUpperCase().trim().equals("D")){
              return "Disponível";
          } else if (this.DM_Situacao.toUpperCase().trim().equals("B")){
              return "Bloqueado";
          } else return "Não encontrado";
      } else return "Não informado";      
  }

  public void setOid_Tipo_Estoque(int oid_Tipo_Estoque) {
    this.oid_Tipo_Estoque = oid_Tipo_Estoque;
  }
  public int getOid_Tipo_Estoque() {
    return oid_Tipo_Estoque;
  }
  public String getNm_Tipo_Estoque() {
    return nm_Tipo_Estoque;
  }
  public void setNm_Tipo_Estoque(String nm_Tipo_Estoque) {
    this.nm_Tipo_Estoque = nm_Tipo_Estoque;
  }
  public String getCd_Tipo_Estoque() {
    return cd_Tipo_Estoque;
  }
  public void setCd_Tipo_Estoque(String cd_Tipo_Estoque) {
    this.cd_Tipo_Estoque = cd_Tipo_Estoque;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
}