package com.master.ed;

/**
 * T�tulo: WMS_Tipo_EstoqueED
 * Descri��o: Tipos de Estoques - ED
 * Data da cria��o: 11/2003
 * Atualizado em: 12/2003
 * Empresa: �xitoLog�stica Mastercom
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
              return "Dispon�vel";
          } else if (this.DM_Situacao.toUpperCase().trim().equals("B")){
              return "Bloqueado";
          } else return "N�o encontrado";
      } else return "N�o informado";      
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