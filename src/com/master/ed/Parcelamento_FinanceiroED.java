package com.master.ed;

public class Parcelamento_FinanceiroED extends MasterED implements java.io.Serializable {

    public Parcelamento_FinanceiroED() {
        super();
    }
    public Parcelamento_FinanceiroED(long parcelamento) {
        OID_Parcelamento = parcelamento;
    }
    public Parcelamento_FinanceiroED(String notaFiscal) {
        OID_Nota_Fiscal = notaFiscal;
    }

    private long OID_Parcelamento;
    private long OID_Compromisso;
    private String OID_Nota_Fiscal;
    private double VL_Porcentagem_Parcela;
    private String DT_Pagamento;
    private String DT_Vencimento_Minimo;
    private String DM_Vencimento_Conta;
    private long NR_Parcelamento;
    private String NR_Nota_Fiscal;
    private double VL_Bruto;
    private double VL_Parcela;
    private String DM_Tipo_Pagamento;
    private double VL_Desconto;
    private String DM_Situacao;

    private boolean atualizaCompromisso = false;

    public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
        this.OID_Nota_Fiscal = OID_Nota_Fiscal;
    }

    public void setOID_Parcelamento(long OID_Parcelamento) {
        this.OID_Parcelamento = OID_Parcelamento;
    }

    public void setDT_Pagamento(String DT_Pagamento) {
        this.DT_Pagamento = DT_Pagamento;
    }

    public void setVL_Porcentagem_Parcela(double VL_Porcentagem_Parcela) {
        this.VL_Porcentagem_Parcela = VL_Porcentagem_Parcela;
    }

    public double getVL_Porcentagem_Parcela() {
        return VL_Porcentagem_Parcela;
    }

    public long getOID_Parcelamento() {
        return OID_Parcelamento;
    }

    public String getOID_Nota_Fiscal() {
        return OID_Nota_Fiscal;
    }

    public String getDT_Pagamento() {
        return DT_Pagamento;
    }

    public void setNR_Parcelamento(long NR_Parcelamento) {
        this.NR_Parcelamento = NR_Parcelamento;
    }

    public long getNR_Parcelamento() {
        return NR_Parcelamento;
    }

    public void setNR_Nota_Fiscal(String NR_Nota_Fiscal) {
        this.NR_Nota_Fiscal = NR_Nota_Fiscal;
    }

    public String getNR_Nota_Fiscal() {
        return NR_Nota_Fiscal;
    }

    public void setVL_Bruto(double VL_Bruto) {
        this.VL_Bruto = VL_Bruto;
    }

    public double getVL_Bruto() {
        return VL_Bruto;
    }

    public void setVL_Parcela(double VL_Parcela) {
        this.VL_Parcela = VL_Parcela;
    }

    public double getVL_Parcela() {
        return VL_Parcela;
    }

    public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
        this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
    }

    public String getDM_Tipo_Pagamento() {
        return DM_Tipo_Pagamento;
    }

    public void setVL_Desconto(double VL_Desconto) {
        this.VL_Desconto = VL_Desconto;
    }

    public double getVL_Desconto() {
        return VL_Desconto;
    }

    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }

    public String getDM_Situacao() {
        return DM_Situacao;
    }

    public boolean isAtualizaCompromisso() {
        return atualizaCompromisso;
    }

    public void setAtualizaCompromisso(boolean atualizaCompromisso) {
        this.atualizaCompromisso = atualizaCompromisso;
    }
	public String getDM_Vencimento_Conta() {
		return DM_Vencimento_Conta;
	}
	public void setDM_Vencimento_Conta(String vencimento_Conta) {
		DM_Vencimento_Conta = vencimento_Conta;
	}
	public String getDT_Vencimento_Minimo() {
		return DT_Vencimento_Minimo;
	}
	public void setDT_Vencimento_Minimo(String vencimento_Minimo) {
		DT_Vencimento_Minimo = vencimento_Minimo;
	}
	public long getOID_Compromisso() {
		return OID_Compromisso;
	}
	public void setOID_Compromisso(long compromisso) {
		OID_Compromisso = compromisso;
	}
}