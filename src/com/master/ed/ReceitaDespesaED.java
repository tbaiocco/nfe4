package com.master.ed;

/**
 * @author Administrador
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ReceitaDespesaED {
		
	private String nr_manifesto;
	private String oid_veiculo;
	private double vl_bruto;
	private double vl_icms;
	private double vl_pis;
	private double vl_liquido;
	private double vl_bruto_total;
	private double vl_icms_total;
	private double vl_pis_total;
	private double vl_liquido_total;
	private double vl_adto;
	private double vl_adto_total;
	private double vl_custo;
	private double vl_custo_total;
	private double vl_saldo;
	private double vl_saldo_total;
	private double pe_custo;
	private double pe_saldo;
	private double pe_custo_total;
	private double pe_saldo_total;
	
	private java.util.Collection ReceitaDespesaDetalhes;
	private String nr_conhecimento;
	private String dt_inicio;
	private String dt_fim;
	private String oid_unidade;
	private String dataRel;
	private String siglaRel;
	private String procedencia;
	
	private String viagem;
	private String origem;
	private String destino;
	private String nr_cf;
	
	private double pe_adto;
	private double pe_adto_total;
	
	private String nm_motorista;
	
	public ReceitaDespesaED(){
	}

	public String getOid_veiculo() {
		return oid_veiculo;
	}

	public void setOid_veiculo(String oid_veiculo) {
		this.oid_veiculo = oid_veiculo;
	}

	public double getPe_custo() {
		return pe_custo;
	}

	public void setPe_custo(double pe_custo) {
		this.pe_custo = pe_custo;
	}

	public double getPe_saldo() {
		return pe_saldo;
	}

	public void setPe_saldo(double pe_saldo) {
		this.pe_saldo = pe_saldo;
	}

	public java.util.Collection getReceitaDespesaDetalhes() {
		return ReceitaDespesaDetalhes;
	}

	public void setReceitaDespesaDetalhes(java.util.Collection receitaDespesaDetalhes) {
		ReceitaDespesaDetalhes = receitaDespesaDetalhes;
	}

	public double getVl_bruto() {
		return vl_bruto;
	}

	public void setVl_bruto(double vl_bruto) {
		this.vl_bruto = vl_bruto;
	}

	public double getVl_bruto_total() {
		return vl_bruto_total;
	}

	public void setVl_bruto_total(double vl_bruto_total) {
		this.vl_bruto_total = vl_bruto_total;
	}

	public double getVl_custo() {
		return vl_custo;
	}

	public void setVl_custo(double vl_custo) {
		this.vl_custo = vl_custo;
	}

	public double getVl_icms() {
		return vl_icms;
	}

	public void setVl_icms(double vl_icms) {
		this.vl_icms = vl_icms;
	}

	public double getVl_icms_total() {
		return vl_icms_total;
	}

	public void setVl_icms_total(double vl_icms_total) {
		this.vl_icms_total = vl_icms_total;
	}

	public double getVl_liquido() {
		return vl_liquido;
	}

	public void setVl_liquido(double vl_liquido) {
		this.vl_liquido = vl_liquido;
	}

	public double getVl_liquido_total() {
		return vl_liquido_total;
	}

	public void setVl_liquido_total(double vl_liquido_total) {
		this.vl_liquido_total = vl_liquido_total;
	}

	public double getVl_pis() {
		return vl_pis;
	}

	public void setVl_pis(double vl_pis) {
		this.vl_pis = vl_pis;
	}

	public double getVl_pis_total() {
		return vl_pis_total;
	}

	public void setVl_pis_total(double vl_pis_total) {
		this.vl_pis_total = vl_pis_total;
	}

	public double getVl_saldo() {
		return vl_saldo;
	}

	public void setVl_saldo(double vl_saldo) {
		this.vl_saldo = vl_saldo;
	}

	public String getNr_conhecimento() {
		return nr_conhecimento;
	}

	public void setNr_conhecimento(String nr_conhecimento) {
		this.nr_conhecimento = nr_conhecimento;
	}

	public String getDt_fim() {
		return dt_fim;
	}

	public void setDt_fim(String dt_fim) {
		this.dt_fim = dt_fim;
	}

	public String getDt_inicio() {
		return dt_inicio;
	}

	public void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public String getOid_unidade() {
		return oid_unidade;
	}

	public void setOid_unidade(String oid_unidade) {
		this.oid_unidade = oid_unidade;
	}

	public String getNr_manifesto() {
		return nr_manifesto;
	}

	public void setNr_manifesto(String nr_manifesto) {
		this.nr_manifesto = nr_manifesto;
	}

	public String getDataRel() {
		return dataRel;
	}

	public void setDataRel(String dataRel) {
		this.dataRel = dataRel;
	}

	public String getSiglaRel() {
		return siglaRel;
	}

	public void setSiglaRel(String siglaRel) {
		this.siglaRel = siglaRel;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public double getPe_custo_total() {
		return pe_custo_total;
	}

	public void setPe_custo_total(double pe_custo_total) {
		this.pe_custo_total = pe_custo_total;
	}

	public double getPe_saldo_total() {
		return pe_saldo_total;
	}

	public void setPe_saldo_total(double pe_saldo_total) {
		this.pe_saldo_total = pe_saldo_total;
	}

	public double getVl_custo_total() {
		return vl_custo_total;
	}

	public void setVl_custo_total(double vl_custo_total) {
		this.vl_custo_total = vl_custo_total;
	}

	public double getVl_saldo_total() {
		return vl_saldo_total;
	}

	public void setVl_saldo_total(double vl_saldo_total) {
		this.vl_saldo_total = vl_saldo_total;
	}

	public String getViagem() {
		return viagem;
	}

	public void setViagem(String viagem) {
		this.viagem = viagem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public double getVl_adto() {
		return vl_adto;
	}

	public void setVl_adto(double vl_adto) {
		this.vl_adto = vl_adto;
	}

	public double getVl_adto_total() {
		return vl_adto_total;
	}

	public void setVl_adto_total(double vl_adto_total) {
		this.vl_adto_total = vl_adto_total;
	}

	public String getNr_cf() {
		return nr_cf;
	}

	public void setNr_cf(String nr_cf) {
		this.nr_cf = nr_cf;
	}

	public double getPe_adto() {
		return pe_adto;
	}

	public void setPe_adto(double pe_adto) {
		this.pe_adto = pe_adto;
	}

	public double getPe_adto_total() {
		return pe_adto_total;
	}

	public void setPe_adto_total(double pe_adto_total) {
		this.pe_adto_total = pe_adto_total;
	}

    public String getNm_motorista() {
        return nm_motorista;
    }
    public void setNm_motorista(String nm_motorista) {
        this.nm_motorista = nm_motorista;
    }
}
