package com.master.ed;

import java.util.Date;

public class Movimento_LogisticoED extends MasterED{

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	private long oid_movimento_logistico;
	private String oid_conhecimento;
	private String oid_nota_fiscal;
	private Date dt_hr_movimento;
	private Date dt_hr_entrada_nota_fiscal;
	private Date dt_hr_emissao_conhecimento;
	private Date dt_hr_entrega;
	private String dm_tipo_movimento; //Entrada, Saida, eNtrega, Chegada, comPleto
	private long oid_unidade_origem;
	private long oid_unidade_destino;
	private double nr_quantidade;
	private String oid_manifesto_internacional;
	private String oid_viagem_internacional;
	private String oid_manifesto;
	private String oid_viagem;

	//FKs
	private String nr_conhecimento;
	private long nr_nota_fiscal;
	private String nr_manifesto;
	private String cd_unidade_origem;
	private String cd_unidade_destino;
	private String nm_tipo_movimento;

	//Relatorio
	private String dt_inicial;
	private String dt_final;
	private long oid_unidade_final;
	private String oid_pessoa;
	private String oid_pessoa_destinatario;
	private String oid_pessoa_pagador;
	private String relatorio;

	private Date tempo_total_crt;
	private Date tempo_total_geral;

	private Date tempo_total_transito;
	private Date tempo_total_unidade;

	private long dias_viagem;
	private long dias_ocioso;

	private String nm_pessoa;
	private String nm_pessoa_destinatario;

	private String dm_chave_ordem;

	private boolean zera_data=false;

	private Date dt_hr_movimento_entrada;

	public Movimento_LogisticoED(String oid_nf) {
		this.oid_nota_fiscal = oid_nf;
	}

	public Movimento_LogisticoED() {
	}

	public String getDm_tipo_movimento() {
		return dm_tipo_movimento;
	}
	public void setDm_tipo_movimento(String dm_tipo_movimento) {
		this.dm_tipo_movimento = dm_tipo_movimento;
	}
	public Date getDt_hr_movimento() {
		return dt_hr_movimento;
	}
	public void setDt_hr_movimento(Date dt_hr_movimento) {
		this.dt_hr_movimento = dt_hr_movimento;
	}
	public double getNr_quantidade() {
		return nr_quantidade;
	}
	public void setNr_quantidade(double nr_quantidade) {
		this.nr_quantidade = nr_quantidade;
	}
	public String getOid_conhecimento() {
		return oid_conhecimento;
	}
	public void setOid_conhecimento(String oid_conhecimento) {
		this.oid_conhecimento = oid_conhecimento;
	}
	public String getOid_manifesto_internacional() {
		return oid_manifesto_internacional;
	}
	public void setOid_manifesto_internacional(String oid_manifesto_internacional) {
		this.oid_manifesto_internacional = oid_manifesto_internacional;
	}
	public long getOid_movimento_logistico() {
		return oid_movimento_logistico;
	}
	public void setOid_movimento_logistico(long oid_movimento_logistico) {
		this.oid_movimento_logistico = oid_movimento_logistico;
	}
	public long getOid_unidade_destino() {
		return oid_unidade_destino;
	}
	public void setOid_unidade_destino(long oid_unidade_destino) {
		this.oid_unidade_destino = oid_unidade_destino;
	}
	public long getOid_unidade_origem() {
		return oid_unidade_origem;
	}
	public void setOid_unidade_origem(long oid_unidade_origem) {
		this.oid_unidade_origem = oid_unidade_origem;
	}
	public String getOid_viagem_internacional() {
		return oid_viagem_internacional;
	}
	public void setOid_viagem_internacional(String oid_viagem_internacional) {
		this.oid_viagem_internacional = oid_viagem_internacional;
	}
	public String getCd_unidade_destino() {
		return cd_unidade_destino;
	}
	public void setCd_unidade_destino(String cd_unidade_destino) {
		this.cd_unidade_destino = cd_unidade_destino;
	}
	public String getCd_unidade_origem() {
		return cd_unidade_origem;
	}
	public void setCd_unidade_origem(String cd_unidade_origem) {
		this.cd_unidade_origem = cd_unidade_origem;
	}
	public String getNm_tipo_movimento() {
		if(dm_tipo_movimento.equals("E"))
			return "ENTRADA";
		else if(dm_tipo_movimento.equals("S"))
			return "SAIDA";
		else if(dm_tipo_movimento.equals("C"))
			return "CHEGADA";
		else if(dm_tipo_movimento.equals("N"))
			return "ENTREGA";
		else return "NDA";
	}
	public void setNm_tipo_movimento(String dm_tipo_movimento) {
		if(dm_tipo_movimento.equals("E"))
			this.nm_tipo_movimento = "ENTRADA";
		else if(dm_tipo_movimento.equals("S"))
			this.nm_tipo_movimento = "SAIDA";
		else if(dm_tipo_movimento.equals("C"))
			this.nm_tipo_movimento = "CHEGADA";
		else if(dm_tipo_movimento.equals("N"))
			this.nm_tipo_movimento = "ENTREGA";
		else this.nm_tipo_movimento = "NDA";
	}
	public String getNr_conhecimento() {
		return nr_conhecimento;
	}
	public void setNr_conhecimento(String nr_conhecimento) {
		this.nr_conhecimento = nr_conhecimento;
	}
	public String getNr_manifesto() {
		return nr_manifesto;
	}
	public void setNr_manifesto(String nr_manifesto) {
		this.nr_manifesto = nr_manifesto;
	}
	public String getDt_final() {
		return dt_final;
	}
	public void setDt_final(String dt_final) {
		this.dt_final = dt_final;
	}
	public String getDt_inicial() {
		return dt_inicial;
	}
	public void setDt_inicial(String dt_inicial) {
		this.dt_inicial = dt_inicial;
	}
	public long getOid_unidade_final() {
		return oid_unidade_final;
	}
	public void setOid_unidade_final(long oid_unidade_final) {
		this.oid_unidade_final = oid_unidade_final;
	}
	public String getOid_pessoa() {
		return oid_pessoa;
	}
	public void setOid_pessoa(String oid_pessoa) {
		this.oid_pessoa = oid_pessoa;
	}
	public String getOid_pessoa_destinatario() {
		return oid_pessoa_destinatario;
	}
	public void setOid_pessoa_destinatario(String oid_pessoa_destinatario) {
		this.oid_pessoa_destinatario = oid_pessoa_destinatario;
	}
	public String getOid_pessoa_pagador() {
		return oid_pessoa_pagador;
	}
	public void setOid_pessoa_pagador(String oid_pessoa_pagador) {
		this.oid_pessoa_pagador = oid_pessoa_pagador;
	}
	public String getRelatorio() {
		return relatorio;
	}
	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}
	public Date getTempo_total_crt() {
		return tempo_total_crt;
	}
	public void setTempo_total_crt(Date tempo_total_crt) {
		this.tempo_total_crt = tempo_total_crt;
	}
	public Date getTempo_total_geral() {
		return tempo_total_geral;
	}
	public void setTempo_total_geral(Date tempo_total_geral) {
		this.tempo_total_geral = tempo_total_geral;
	}
	public String getNm_pessoa() {
		return nm_pessoa;
	}
	public void setNm_pessoa(String nm_pessoa) {
		this.nm_pessoa = nm_pessoa;
	}
	public String getNm_pessoa_destinatario() {
		return nm_pessoa_destinatario;
	}
	public void setNm_pessoa_destinatario(String nm_pessoa_destinatario) {
		this.nm_pessoa_destinatario = nm_pessoa_destinatario;
	}
	public String getDm_chave_ordem() {
		return dm_chave_ordem;
	}
	public void setDm_chave_ordem(String dm_chave_ordem) {
		this.dm_chave_ordem = dm_chave_ordem;
	}
	public Date getDt_hr_movimento_entrada() {
		return dt_hr_movimento_entrada;
	}
	public void setDt_hr_movimento_entrada(Date dt_hr_movimento_entrada) {
		this.dt_hr_movimento_entrada = dt_hr_movimento_entrada;
	}
	public boolean getZera_data() {
		return zera_data;
	}
	public void setZera_data(boolean zera_data) {
		this.zera_data = zera_data;
	}
	public Date getTempo_total_transito() {
		return tempo_total_transito;
	}
	public void setTempo_total_transito(Date tempo_total_transito) {
		this.tempo_total_transito = tempo_total_transito;
	}
	public Date getTempo_total_unidade() {
		return tempo_total_unidade;
	}
	public void setTempo_total_unidade(Date tempo_total_unidade) {
		this.tempo_total_unidade = tempo_total_unidade;
	}
	public long getDias_ocioso() {
		return dias_ocioso;
	}
	public void setDias_ocioso(long dias_ocioso) {
		this.dias_ocioso = dias_ocioso;
	}
	public long getDias_viagem() {
		return dias_viagem;
	}
	public void setDias_viagem(long dias_viagem) {
		this.dias_viagem = dias_viagem;
	}
	public Date getDt_hr_emissao_conhecimento() {
		return dt_hr_emissao_conhecimento;
	}
	public void setDt_hr_emissao_conhecimento(Date dt_hr_emissao_conhecimento) {
		this.dt_hr_emissao_conhecimento = dt_hr_emissao_conhecimento;
	}
	public Date getDt_hr_entrada_nota_fiscal() {
		return dt_hr_entrada_nota_fiscal;
	}
	public void setDt_hr_entrada_nota_fiscal(Date dt_hr_entrada_nota_fiscal) {
		this.dt_hr_entrada_nota_fiscal = dt_hr_entrada_nota_fiscal;
	}
	public Date getDt_hr_entrega() {
		return dt_hr_entrega;
	}
	public void setDt_hr_entrega(Date dt_hr_entrega) {
		this.dt_hr_entrega = dt_hr_entrega;
	}
	public String getOid_manifesto() {
		return oid_manifesto;
	}
	public void setOid_manifesto(String oid_manifesto) {
		this.oid_manifesto = oid_manifesto;
	}
	public String getOid_nota_fiscal() {
		return oid_nota_fiscal;
	}
	public void setOid_nota_fiscal(String oid_nota_fiscal) {
		this.oid_nota_fiscal = oid_nota_fiscal;
	}
	public String getOid_viagem() {
		return oid_viagem;
	}
	public void setOid_viagem(String oid_viagem) {
		this.oid_viagem = oid_viagem;
	}
	public long getNr_nota_fiscal() {
		return nr_nota_fiscal;
	}
	public void setNr_nota_fiscal(long nr_nota_fiscal) {
		this.nr_nota_fiscal = nr_nota_fiscal;
	}

}