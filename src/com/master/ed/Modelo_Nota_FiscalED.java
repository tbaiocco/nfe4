package com.master.ed;

/**
 * @author André Valadas
 * - Modelos de Notas Fiscais
 */
public class Modelo_Nota_FiscalED extends MasterED {

    public Modelo_Nota_FiscalED() {
        super();
    }     
    public Modelo_Nota_FiscalED(int oid_Modelo_Nota_Fiscal) {
        this.oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
    }
    public Modelo_Nota_FiscalED(int oid_Modelo_Nota_Fiscal, String tipo_Nota_Fiscal) {
        this.oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
        DM_Tipo_Nota_Fiscal = tipo_Nota_Fiscal;
    }

    
    
    
    public WMS_Tipo_EstoqueED edTipo_Estoque = new WMS_Tipo_EstoqueED();
    public WMS_Tipo_MovimentoED edTipo_Movimento = new WMS_Tipo_MovimentoED();

    private int oid_Modelo_Nota_Fiscal;
    private String CD_Modelo_Nota_Fiscal;
    private String NM_Modelo_Nota_Fiscal;
    private String DM_Tipo_Nota_Fiscal; //Entrada ou Saída
    private String DM_Gera_Fiscal;//Inclui ou não no Livro Fiscal
    private String DM_Gera_Contabilizacao;//Inclui ou não na contabilidade
    private String DM_Gera_Devolucao;//Inclui ou não no Nota de Devolucao
    private String DM_Nota_Fiscal;//Inclui ou não no Livro Fiscal
    private String DM_Movimenta_Estoque;//Movimenta ou não Estoque
    private String DM_Movimenta_Financeiro;//Movimenta ou não Financeiro
    private String DM_Exige_Pedido;//Para NFs de Saida
    private String DM_Aliquota_ICMS;//Para NFs sem produtos, informa a Aliquota na própria NF
    private String DM_Permite_Servico;//Se a NF é mista = Saida + Serviço
    private String TX_Observacao;
    private int oid_Tipo_Estoque;
    private int oid_Tipo_Movimento_Produto;
    private long Oid_Sugestao_Contabil;

    public String getDescDM_Tipo_Nota_Fiscal() {
        if ("E".equals(DM_Tipo_Nota_Fiscal))
            return "Entrada";
        else if ("S".equals(DM_Tipo_Nota_Fiscal))
            return "Saída";
        else if ("D".equals(DM_Tipo_Nota_Fiscal))
            return "Devolução";
        else if ("X".equals(DM_Tipo_Nota_Fiscal))
            return "Diversa";
        else return "Não Informada!";
    }
    public String getDescSimNao(String DM_Situacao) {
        if ("S".equals(DM_Situacao))
            return "Sim";
        else if ("N".equals(DM_Situacao))
            return "Não";
        else return "Não Informada!";
    }

    public String getCD_Modelo_Nota_Fiscal() {
        return CD_Modelo_Nota_Fiscal;
    }
    public void setCD_Modelo_Nota_Fiscal(String modelo_Nota_Fiscal) {
        CD_Modelo_Nota_Fiscal = modelo_Nota_Fiscal;
    }
    public String getDM_Gera_Fiscal() {
        return DM_Gera_Fiscal;
    }
    public void setDM_Gera_Fiscal(String gera_Fiscal) {
        DM_Gera_Fiscal = gera_Fiscal;
    }
    public String getDM_Movimenta_Estoque() {
        return DM_Movimenta_Estoque;
    }
    public void setDM_Movimenta_Estoque(String movimenta_Estoque) {
        DM_Movimenta_Estoque = movimenta_Estoque;
    }
    public String getNM_Modelo_Nota_Fiscal() {
        return NM_Modelo_Nota_Fiscal;
    }
    public void setNM_Modelo_Nota_Fiscal(String modelo_Nota_Fiscal) {
        NM_Modelo_Nota_Fiscal = modelo_Nota_Fiscal;
    }
    public int getOid_Modelo_Nota_Fiscal() {
        return oid_Modelo_Nota_Fiscal;
    }
    public void setOid_Modelo_Nota_Fiscal(int oid_Modelo_Nota_Fiscal) {
        this.oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setTX_Observacao(String observacao) {
        TX_Observacao = observacao;
    }
    public String getDM_Tipo_Nota_Fiscal() {
        return DM_Tipo_Nota_Fiscal;
    }
    public void setDM_Tipo_Nota_Fiscal(String tipo_Nota_Fiscal) {
        DM_Tipo_Nota_Fiscal = tipo_Nota_Fiscal;
    }
    public int getOid_Tipo_Estoque() {
        return oid_Tipo_Estoque;
    }
    public void setOid_Tipo_Estoque(int oid_Tipo_Estoque) {
        this.oid_Tipo_Estoque = oid_Tipo_Estoque;
    }
    public String getDM_Movimenta_Financeiro() {
        return DM_Movimenta_Financeiro;
    }
    public void setDM_Movimenta_Financeiro(String movimenta_Financeiro) {
        DM_Movimenta_Financeiro = movimenta_Financeiro;
    }
    public String getDM_Exige_Pedido() {
        return DM_Exige_Pedido;
    }
    public void setDM_Exige_Pedido(String exige_Pedido) {
        DM_Exige_Pedido = exige_Pedido;
    }
    public int getOid_Tipo_Movimento_Produto() {
        return oid_Tipo_Movimento_Produto;
    }
    public void setOid_Tipo_Movimento_Produto(int oid_Tipo_Movimento_Produto) {
        this.oid_Tipo_Movimento_Produto = oid_Tipo_Movimento_Produto;
    }
    public String getDM_Aliquota_ICMS() {
        return DM_Aliquota_ICMS;
    }
    public void setDM_Aliquota_ICMS(String aliquota_ICMS) {
        DM_Aliquota_ICMS = aliquota_ICMS;
    }
	public long getOid_Sugestao_Contabil() {
		return Oid_Sugestao_Contabil;
	}
	public void setOid_Sugestao_Contabil(long oid_Sugestao_Contabil) {
		Oid_Sugestao_Contabil = oid_Sugestao_Contabil;
	}
	public String getDM_Nota_Fiscal() {
		return DM_Nota_Fiscal;
	}
	public void setDM_Nota_Fiscal(String nota_Fiscal) {
		DM_Nota_Fiscal = nota_Fiscal;
	}
	public String getDM_Gera_Devolucao() {
		return DM_Gera_Devolucao;
	}
	public void setDM_Gera_Devolucao(String gera_Devolucao) {
		DM_Gera_Devolucao = gera_Devolucao;
	}
	public String getDM_Permite_Servico() {
		return DM_Permite_Servico;
	}
	public void setDM_Permite_Servico(String permite_Servico) {
		DM_Permite_Servico = permite_Servico;
	}
	public String getDM_Gera_Contabilizacao() {
		return DM_Gera_Contabilizacao;
	}
	public void setDM_Gera_Contabilizacao(String gera_Contabilizacao) {
		DM_Gera_Contabilizacao = gera_Contabilizacao;
	}
}
