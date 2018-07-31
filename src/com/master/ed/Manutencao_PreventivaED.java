/*
 * Created on 05/04/2005
 *
 */
package com.master.ed;

import javax.servlet.http.*;

/**
 * @author Tiago
 *
 */
public class Manutencao_PreventivaED extends RelatorioBaseED {
    private int oid_tipo_servico;
    private String nm_tipo_servico;
    private String oid_veiculo;
    private String dt_ultimo_servico;
    private int nr_kilometragem_realizada;
    private int nr_kilometragem_prevista;
    private int nr_kilometragem_atual;
    private String dm_situacao;
  private String DM_Procedencia;

  public Manutencao_PreventivaED() {
        super();
    }
    public Manutencao_PreventivaED(HttpServletResponse response,
            String nomeRelatorio) {
        super(response, nomeRelatorio);
    }

    public String getDm_situacao() {
        return this.dm_situacao;
    }
    public void setDm_situacao(String dm_situacao) {
        this.dm_situacao = dm_situacao;
    }
    public int getOid_tipo_servico() {
        return this.oid_tipo_servico;
    }
    public void setOid_tipo_servico(int oid_tipo_servico) {
        this.oid_tipo_servico = oid_tipo_servico;
    }
    public String getOid_veiculo() {
        return this.oid_veiculo;
    }
    public void setOid_veiculo(String oid_veiculo) {
        this.oid_veiculo = oid_veiculo;
    }
    public String getNm_tipo_servico() {
        return this.nm_tipo_servico;
    }
    public void setNm_tipo_servico(String nm_tipo_servico) {
        this.nm_tipo_servico = nm_tipo_servico;
    }
    public String getDt_ultimo_servico() {
        return this.dt_ultimo_servico;
    }
    public void setDt_ultimo_servico(String dt_ultimo_servico) {
        this.dt_ultimo_servico = dt_ultimo_servico;
    }
    public int getNr_kilometragem_atual() {
        return this.nr_kilometragem_atual;
    }
    public void setNr_kilometragem_atual(int nr_kilometragem_atual) {
        this.nr_kilometragem_atual = nr_kilometragem_atual;
    }
    public int getNr_kilometragem_prevista() {
        return this.nr_kilometragem_prevista;
    }
    public void setNr_kilometragem_prevista(int nr_kilometragem_prevista) {
        this.nr_kilometragem_prevista = nr_kilometragem_prevista;
    }
    public int getNr_kilometragem_realizada() {
        return this.nr_kilometragem_realizada;
    }

  public String getDM_Procedencia () {
    return DM_Procedencia;
  }

  public void setNr_kilometragem_realizada(int nr_kilometragem_realizada) {
        this.nr_kilometragem_realizada = nr_kilometragem_realizada;
    }

  public void setDM_Procedencia (String DM_Procedencia) {
    this.DM_Procedencia = DM_Procedencia;
  }
}