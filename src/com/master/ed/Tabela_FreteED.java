package com.master.ed;
 
public class Tabela_FreteED extends RelatorioED{
	private String DM_Origem;
	private String NM_Origem;
	private String DM_Destino;
	private String NM_Destino;
	private String DT_Vigencia;
	private String DT_Validade;
	private double VL_Frete;
	private double VL_Frete_Kg;
	private double VL_Taxas;
	private double PE_Ad_Valorem;
	private double VL_Outros1;
	private double VL_Outros2;
	private double VL_Outros3;
	private double VL_Outros4;
	private double VL_Outros5;
	private double VL_Outros6;
	private double VL_Outros7;
	private double VL_Outros8;
	private double VL_Outros9;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private long oid;
	private long oid_Produto;
	private long oid_Origem;
	private long oid_Destino;
	private String NM_Razao_Social;
	private String NM_Modal;
	private String NM_Produto;
	private String CD_Produto;
	private String CD_Modal;
	private String DM_Tipo_Pedagio;
	private String NM_Tipo_Pedagio;
	private double VL_Frete_Valor_Minimo;
	private double VL_Pedagio;
	private double VL_Frete_Minimo;
	private long nr_Cotacao;
	private long oid_Unidade;
	private long OID_Empresa;
	private String OID_Pessoa_Redespacho;
	private long NR_Prazo_Entrega;
	private long NR_Prazo_Faturamento;
	private long NR_Peso_Minimo;
	private String DM_ICMS;
	private String DM_Tipo_Tabela;
	private String DM_Tipo_Peso;
	private String NM_Tipo_Peso;
	private double VL_Ademe_Minimo;
	private double VL_Ad_Valorem_Minimo;
	private double VL_Maximo_Nota_Fiscal;
	private double VL_Minimo_Nota_Fiscal;
	private double VL_Despacho;
	private double PE_Devolucao;
	private double PE_Refaturamento;
	private double PE_Reentrega;
	private double PE_Ademe;
	private String DM_Unidade;
	private double PE_Reembolso;
	private double PE_Reembolso_Minimo;
	private double VL_Reembolso;	
	private String OID_Pessoa;
	private String OID_Pessoa_Destinatario;
	private int OID_Produto_Origem;
	private int OID_Produto_Destino;
	private String DT_Encerramento;
	private String DT_Vigencia_Inicial;
	private String DT_Vigencia_Final;
	private long OID_Cidade_Origem;
	private long OID_Cidade_Destino;
	private String DM_Transferencia;
	private double PE_Reajuste;
	private int OID_Modal;
	private double NR_Peso_1;
	private double NR_Peso_2;
	private double NR_Peso_Inicial;
	private double NR_Peso_Final;
	
	private java.util.Collection CotacaoDetalhes;
    private String dataRel;
    private String siglaRel;
    private String DM_CRT;
    private String DT_Emissao_Inicial;
    private String DT_Emissao_Final;
    private String NM_Conhecimento;
	
	public void setOID_Pessoa(String OID_Pessoa) {
		this.OID_Pessoa = OID_Pessoa;
	}
	public String getOID_Pessoa() {
		return OID_Pessoa;
	}
	public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
		this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
	}
	public String getOID_Pessoa_Destinatario() {
		return OID_Pessoa_Destinatario;
	}
	public void setOID_Produto_Origem(int OID_Produto_Origem) {
		this.OID_Produto_Origem = OID_Produto_Origem;
	}
	public int getOID_Produto_Origem() {
		return OID_Produto_Origem;
	}
	public void setOID_Produto_Destino(int OID_Produto_Destino) {
		this.OID_Produto_Destino = OID_Produto_Destino;
	}
	public int getOID_Produto_Destino() {
		return OID_Produto_Destino;
	}
	public void setDT_Encerramento(String DT_Encerramento) {
		this.DT_Encerramento = DT_Encerramento;
	}
	public String getDT_Encerramento() {
		return DT_Encerramento;
	}
	public void setDT_Vigencia_Inicial(String DT_Vigencia_Inicial) {
		this.DT_Vigencia_Inicial = DT_Vigencia_Inicial;
	}
	public String getDT_Vigencia_Inicial() {
		return DT_Vigencia_Inicial;
	}
	public void setDT_Vigencia_Final(String DT_Vigencia_Final) {
		this.DT_Vigencia_Final = DT_Vigencia_Final;
	}
	public String getDT_Vigencia_Final() {
		return DT_Vigencia_Final;
	}
	public void setOID_Cidade_Origem(long OID_Cidade_Origem) {
		this.OID_Cidade_Origem = OID_Cidade_Origem;
	}
	public long getOID_Cidade_Origem() {
		return OID_Cidade_Origem;
	}
	public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
		this.OID_Cidade_Destino = OID_Cidade_Destino;
	}
	public long getOID_Cidade_Destino() {
		return OID_Cidade_Destino;
	}
	public void setDM_Transferencia(String DM_Transferencia) {
		this.DM_Transferencia = DM_Transferencia;
	}
	public String getDM_Transferencia() {
		return DM_Transferencia;
	}
	public void setPE_Reajuste(double PE_Reajuste) {
		this.PE_Reajuste = PE_Reajuste;
	}
	public double getPE_Reajuste() {
		return PE_Reajuste;
	}
	public int getOID_Modal() {
		return OID_Modal;
	}
	public void setOID_Modal(int OID_Modal) {
		this.OID_Modal = OID_Modal;
	}
	public double getNR_Peso_1() {
		return NR_Peso_1;
	}
	public void setNR_Peso_1(double peso_1) {
		NR_Peso_1 = peso_1;
	}
	public double getNR_Peso_2() {
		return NR_Peso_2;
	}
	public void setNR_Peso_2(double peso_2) {
		NR_Peso_2 = peso_2;
	}
	public String getCD_Modal() {
		return CD_Modal;
	}
	public void setCD_Modal(String modal) {
		CD_Modal = modal;
	}
	public String getCD_Produto() {
		return CD_Produto;
	}
	public void setCD_Produto(String produto) {
		CD_Produto = produto;
	}
	public String getDM_Destino() {
		return DM_Destino;
	}
	public void setDM_Destino(String destino) {
		DM_Destino = destino;
	}
	public String getDM_ICMS() {
		return DM_ICMS;
	}
	public void setDM_ICMS(String dm_icms) {
		DM_ICMS = dm_icms;
	}
	public String getDM_Origem() {
		return DM_Origem;
	}
	public void setDM_Origem(String origem) {
		DM_Origem = origem;
	}
	public String getDm_Stamp() {
		return Dm_Stamp;
	}
	public void setDm_Stamp(String dm_Stamp) {
		Dm_Stamp = dm_Stamp;
	}
	public String getDM_Tipo_Pedagio() {
		return DM_Tipo_Pedagio;
	}
	public void setDM_Tipo_Pedagio(String tipo_Pedagio) {
		DM_Tipo_Pedagio = tipo_Pedagio;
	}
	public String getNM_Tipo_Pedagio() {

		if ("C".equals(DM_Tipo_Pedagio)) {
			return "CTRC";
		} else if ("K".equals(DM_Tipo_Pedagio)) {
			return "Kg";
		} else if ("F".equals(DM_Tipo_Pedagio)) {
			return "Fr";
		} else if ("T".equals(DM_Tipo_Pedagio)) {
			return "Ton";
		} else if ("E".equals(DM_Tipo_Pedagio)) {
			return "Eix";
		}
		
		else {
			return "---";
		}

	}
	public void setNM_Tipo_Pedagio(String tipo_Pedagio) {
		NM_Tipo_Pedagio = tipo_Pedagio;
	}
	public String getDM_Tipo_Peso() {
		return DM_Tipo_Peso;
	}
	public void setDM_Tipo_Peso(String tipo_Peso) {
		DM_Tipo_Peso = tipo_Peso;
	}
	public String getNM_Tipo_Peso() {

		if ("T".equals(DM_Tipo_Peso)) {
			return "Ton";
		} else if ("C".equals(DM_Tipo_Peso)) {
			return "M3";
		} else if ("K".equals(DM_Tipo_Peso)) {
			return "Kg";
		} else if ("F".equals(DM_Tipo_Peso)) {
			return "Fin";
		} else if ("E".equals(DM_Tipo_Peso)) {
			return "Exc";
		}
		
		else {
			return "---";
		}

	}
	public void setNM_Tipo_Peso(String tipo_Peso) {
		NM_Tipo_Peso = tipo_Peso;
	}
	public String getDM_Tipo_Tabela() {
		return DM_Tipo_Tabela;
	}
	public void setDM_Tipo_Tabela(String tipo_Tabela) {
		DM_Tipo_Tabela = tipo_Tabela;
	}
	public String getDM_Unidade() {
		return DM_Unidade;
	}
	public void setDM_Unidade(String unidade) {
		DM_Unidade = unidade;
	}
	public String getDt_Stamp() {
		return Dt_Stamp;
	}
	public void setDt_Stamp(String dt_Stamp) {
		Dt_Stamp = dt_Stamp;
	}
	public String getDT_Validade() {
		return DT_Validade;
	}
	public void setDT_Validade(String validade) {
		DT_Validade = validade;
	}
	public String getDT_Vigencia() {
		return DT_Vigencia;
	}
	public void setDT_Vigencia(String vigencia) {
		DT_Vigencia = vigencia;
	}
	public String getNM_Destino() {
		return NM_Destino;
	}
	public void setNM_Destino(String destino) {
		NM_Destino = destino;
	}
	public String getNM_Modal() {
		return NM_Modal;
	}
	public void setNM_Modal(String modal) {
		NM_Modal = modal;
	}
	public String getNM_Origem() {
		return NM_Origem;
	}
	public void setNM_Origem(String origem) {
		NM_Origem = origem;
	}
	public String getNM_Produto() {
		return NM_Produto;
	}
	public void setNM_Produto(String produto) {
		NM_Produto = produto;
	}
	public String getNM_Razao_Social() {
		return NM_Razao_Social;
	}
	public void setNM_Razao_Social(String razao_Social) {
		NM_Razao_Social = razao_Social;
	}
	public long getNr_Cotacao() {
		return nr_Cotacao;
	}
	public void setNr_Cotacao(long nr_Cotacao) {
		this.nr_Cotacao = nr_Cotacao;
	}
	public long getNR_Peso_Minimo() {
		return NR_Peso_Minimo;
	}
	public void setNR_Peso_Minimo(long peso_Minimo) {
		NR_Peso_Minimo = peso_Minimo;
	}
	public long getNR_Prazo_Entrega() {
		return NR_Prazo_Entrega;
	}
	public void setNR_Prazo_Entrega(long prazo_Entrega) {
		NR_Prazo_Entrega = prazo_Entrega;
	}
	public long getNR_Prazo_Faturamento() {
		return NR_Prazo_Faturamento;
	}
	public void setNR_Prazo_Faturamento(long prazo_Faturamento) {
		NR_Prazo_Faturamento = prazo_Faturamento;
	}
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public long getOid_Destino() {
		return oid_Destino;
	}
	public void setOid_Destino(long oid_Destino) {
		this.oid_Destino = oid_Destino;
	}
	public long getOID_Empresa() {
		return OID_Empresa;
	}
	public void setOID_Empresa(long empresa) {
		OID_Empresa = empresa;
	}
	public long getOid_Origem() {
		return oid_Origem;
	}
	public void setOid_Origem(long oid_Origem) {
		this.oid_Origem = oid_Origem;
	}
	public String getOID_Pessoa_Redespacho() {
		return OID_Pessoa_Redespacho;
	}
	public void setOID_Pessoa_Redespacho(String pessoa_Redespacho) {
		OID_Pessoa_Redespacho = pessoa_Redespacho;
	}
	public long getOid_Produto() {
		return oid_Produto;
	}
	public void setOid_Produto(long oid_Produto) {
		this.oid_Produto = oid_Produto;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public double getPE_Ad_Valorem() {
		return PE_Ad_Valorem;
	}
	public void setPE_Ad_Valorem(double ad_Valorem) {
		PE_Ad_Valorem = ad_Valorem;
	}
	public double getPE_Ademe() {
		return PE_Ademe;
	}
	public void setPE_Ademe(double ademe) {
		PE_Ademe = ademe;
	}
	public double getPE_Devolucao() {
		return PE_Devolucao;
	}
	public void setPE_Devolucao(double devolucao) {
		PE_Devolucao = devolucao;
	}
	public double getPE_Reembolso() {
		return PE_Reembolso;
	}
	public void setPE_Reembolso(double reembolso) {
		PE_Reembolso = reembolso;
	}
	public double getPE_Reembolso_Minimo() {
		return PE_Reembolso_Minimo;
	}
	public void setPE_Reembolso_Minimo(double reembolso_Minimo) {
		PE_Reembolso_Minimo = reembolso_Minimo;
	}
	public double getPE_Reentrega() {
		return PE_Reentrega;
	}
	public void setPE_Reentrega(double reentrega) {
		PE_Reentrega = reentrega;
	}
	public double getPE_Refaturamento() {
		return PE_Refaturamento;
	}
	public void setPE_Refaturamento(double refaturamento) {
		PE_Refaturamento = refaturamento;
	}
	public String getUsuario_Stamp() {
		return Usuario_Stamp;
	}
	public void setUsuario_Stamp(String usuario_Stamp) {
		Usuario_Stamp = usuario_Stamp;
	}
	public double getVL_Ad_Valorem_Minimo() {
		return VL_Ad_Valorem_Minimo;
	}
	public void setVL_Ad_Valorem_Minimo(double ad_Valorem_Minimo) {
		VL_Ad_Valorem_Minimo = ad_Valorem_Minimo;
	}
	public double getVL_Ademe_Minimo() {
		return VL_Ademe_Minimo;
	}
	public void setVL_Ademe_Minimo(double ademe_Minimo) {
		VL_Ademe_Minimo = ademe_Minimo;
	}
	public double getVL_Despacho() {
		return VL_Despacho;
	}
	public void setVL_Despacho(double despacho) {
		VL_Despacho = despacho;
	}
	public double getVL_Frete() {
		return VL_Frete;
	}
	public void setVL_Frete(double frete) {
		VL_Frete = frete;
	}
	public double getVL_Frete_Kg() {
		return VL_Frete_Kg;
	}
	public void setVL_Frete_Kg(double frete_Kg) {
		VL_Frete_Kg = frete_Kg;
	}
	public double getVL_Frete_Minimo() {
		return VL_Frete_Minimo;
	}
	public void setVL_Frete_Minimo(double frete_Minimo) {
		VL_Frete_Minimo = frete_Minimo;
	}
	public double getVL_Frete_Valor_Minimo() {
		return VL_Frete_Valor_Minimo;
	}
	public void setVL_Frete_Valor_Minimo(double frete_Valor_Minimo) {
		VL_Frete_Valor_Minimo = frete_Valor_Minimo;
	}
	public double getVL_Maximo_Nota_Fiscal() {
		return VL_Maximo_Nota_Fiscal;
	}
	public void setVL_Maximo_Nota_Fiscal(double maximo_Nota_Fiscal) {
		VL_Maximo_Nota_Fiscal = maximo_Nota_Fiscal;
	}
	public double getVL_Minimo_Nota_Fiscal() {
		return VL_Minimo_Nota_Fiscal;
	}
	public void setVL_Minimo_Nota_Fiscal(double minimo_Nota_Fiscal) {
		VL_Minimo_Nota_Fiscal = minimo_Nota_Fiscal;
	}
	public double getVL_Outros1() {
		return VL_Outros1;
	}
	public void setVL_Outros1(double outros1) {
		VL_Outros1 = outros1;
	}
	public double getVL_Outros2() {
		return VL_Outros2;
	}
	public void setVL_Outros2(double outros2) {
		VL_Outros2 = outros2;
	}
	public double getVL_Outros3() {
		return VL_Outros3;
	}
	public void setVL_Outros3(double outros3) {
		VL_Outros3 = outros3;
	}
	public double getVL_Outros4() {
		return VL_Outros4;
	}
	public void setVL_Outros4(double outros4) {
		VL_Outros4 = outros4;
	}
	public double getVL_Outros5() {
		return VL_Outros5;
	}
	public void setVL_Outros5(double outros5) {
		VL_Outros5 = outros5;
	}
	public double getVL_Outros6() {
		return VL_Outros6;
	}
	public void setVL_Outros6(double outros6) {
		VL_Outros6 = outros6;
	}
	public double getVL_Outros7() {
		return VL_Outros7;
	}
	public void setVL_Outros7(double outros7) {
		VL_Outros7 = outros7;
	}
	public double getVL_Outros8() {
		return VL_Outros8;
	}
	public void setVL_Outros8(double outros8) {
		VL_Outros8 = outros8;
	}
	public double getVL_Outros9() {
		return VL_Outros9;
	}
	public void setVL_Outros9(double outros9) {
		VL_Outros9 = outros9;
	}
	public double getVL_Pedagio() {
		return VL_Pedagio;
	}
	public void setVL_Pedagio(double pedagio) {
		VL_Pedagio = pedagio;
	}
	public double getVL_Reembolso() {
		return VL_Reembolso;
	}
	public void setVL_Reembolso(double reembolso) {
		VL_Reembolso = reembolso;
	}
	public double getVL_Taxas() {
		return VL_Taxas;
	}
	public void setVL_Taxas(double taxas) {
		VL_Taxas = taxas;
	}
    public java.util.Collection getCotacaoDetalhes() {
        return CotacaoDetalhes;
    }
    public void setCotacaoDetalhes(java.util.Collection cotacaoDetalhes) {
        CotacaoDetalhes = cotacaoDetalhes;
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
    public String getDM_CRT() {
        return DM_CRT;
    }
    public void setDM_CRT(String dm_crt) {
        DM_CRT = dm_crt;
    }
    public String getDT_Emissao_Final() {
        return DT_Emissao_Final;
    }
    public void setDT_Emissao_Final(String emissao_Final) {
        DT_Emissao_Final = emissao_Final;
    }
    public String getDT_Emissao_Inicial() {
        return DT_Emissao_Inicial;
    }
    public void setDT_Emissao_Inicial(String emissao_Inicial) {
        DT_Emissao_Inicial = emissao_Inicial;
    }
    public String getNM_Conhecimento() {
        return NM_Conhecimento;
    }
    public void setNM_Conhecimento(String conhecimento) {
        NM_Conhecimento = conhecimento;
    }
	public double getNR_Peso_Final() {
		return NR_Peso_Final;
	}
	public void setNR_Peso_Final(double peso_Final) {
		NR_Peso_Final = peso_Final;
	}
	public double getNR_Peso_Inicial() {
		return NR_Peso_Inicial;
	}
	public void setNR_Peso_Inicial(double peso_Inicial) {
		NR_Peso_Inicial = peso_Inicial;
	}
}