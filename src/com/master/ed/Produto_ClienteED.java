package com.master.ed;

import com.master.util.Valor;

/**
 * Título: Produto_ClienteED Descrição: Produtos Clientes - ED Data da criação:
 * 10/2003 Atualizado em: 02/2004 Empresa: ÊxitoLogística Mastercom Autor:
 * Carlos Eduardo de Holleben
 */

public class Produto_ClienteED extends MasterED {

    public Produto_ClienteED(String produto_Cliente) {
        OID_Produto_Cliente = produto_Cliente;
    }
    public Produto_ClienteED() {
        super();
    }
    private String OID_Produto_Cliente;
    private String OID_Pessoa;
    private int OID_Produto;
    private int OID_Embalagem;
    private int oid_Localizacao;
    private double NR_Peso;
    private double NR_Peso_Liquido;
    private double NR_Peso_Bruto;
    private double NR_Quantidade;
    
    private String DM_Rotatividade;
    private String NM_Referencia;
    private String DM_Serie;
    private String DM_Validade;
    private String CD_Produto;
    private String NM_Produto;
    private String NM_Razao_Social;
    private String NM_Embalagem;
    private String DM_Situacao;

    private double VL_Preco_Custo;
    private double VL_Markup;
    private double VL_Produto;
    private double PE_Desconto_Avista;
    private double PE_Desconto_7_Dias;
    private double PE_Acrescimo_21_Dias;
    private double PE_Acrescimo_28_Dias;

    private double PE_Comissao;

    private double NR_QT_Minimo;
    private double nr_Altura;
    private double nr_Largura;
    private double nr_Profundidade;
    private int nr_Empilhagem;

    private int oid_Tipo_Pallet;
    private String NM_Tipo_Pallet;
    private String NR_CNPJ_CPF;
    private double NR_QT_Atual;
    private String NM_Estoque;
    private String NM_Localizacao;
    private long OID_Tipo_Estoque;
    private String NM_Deposito;
    private String NM_Rua;
    private String NM_Endereco;
    private String NM_Apartamento;
    private String DT_Validade;
    private long NR_Lote;
    private String DT_Cadastro;
    
    public WMS_LocalizacaoED edLocalizacao = new WMS_LocalizacaoED(); 

    public String getDescDM_Situacao() {
        return getDescDM_Situacao(this.DM_Situacao);
    }
    public static String getDescDM_Situacao(String dm_Situacao) {
        if ("L".equals(dm_Situacao))
            return "Liberado";
        else if ("B".equals(dm_Situacao))
            return "Bloqueado";
        else if ("V".equals(dm_Situacao))
            return "Bloqueado Venda";
        else if ("C".equals(dm_Situacao))
            return "Bloqueado Compra";
        else return "Não Informado!";
    }
    /** SALDO FINANCEIRO **/
    public double getSaldoFinanceiro() {
        return Valor.round(this.getNR_QT_Atual()*this.getVL_Preco_Custo(), 2);
    }
    
    public String getDM_Rotatividade() {
        return DM_Rotatividade;
    }

    public String getNM_Referencia() {
        return NM_Referencia;
    }

    public double getNR_Peso() {
        return NR_Peso;
    }

    public int getOID_Embalagem() {
        return OID_Embalagem;
    }

    public String getOID_Pessoa() {
        return OID_Pessoa;
    }

    public String getOID_Produto_Cliente() {
        return OID_Produto_Cliente;
    }

    public int getOID_Produto() {
        return OID_Produto;
    }

    public void setDM_Rotatividade(String DM_Rotatividade) {
        this.DM_Rotatividade = DM_Rotatividade;
    }

    public void setNM_Referencia(String NM_Referencia) {
        this.NM_Referencia = NM_Referencia;
    }

    public void setNR_Peso(double NR_Peso) {
        this.NR_Peso = NR_Peso;
    }

    public void setOID_Embalagem(int OID_Embalagem) {
        this.OID_Embalagem = OID_Embalagem;
    }

    public void setOID_Pessoa(String OID_Pessoa) {
        this.OID_Pessoa = OID_Pessoa;
    }

    public void setOID_Produto(int OID_Produto) {
        this.OID_Produto = OID_Produto;
    }

    public void setOID_Produto_Cliente(String OID_Produto_Cliente) {
        this.OID_Produto_Cliente = OID_Produto_Cliente;
    }

    public String getDM_Serie() {
        return DM_Serie;
    }

    public void setDM_Serie(String DM_Serie) {
        this.DM_Serie = DM_Serie;
    }

    public double getNr_Altura() {
        return nr_Altura;
    }

    public int getNr_Empilhagem() {
        return nr_Empilhagem;
    }

    public double getNr_Largura() {
        return nr_Largura;
    }

    public double getNr_Profundidade() {
        return nr_Profundidade;
    }

    public void setNr_Altura(double nr_Altura) {
        this.nr_Altura = nr_Altura;
    }

    public void setNr_Empilhagem(int nr_Empilhagem) {
        this.nr_Empilhagem = nr_Empilhagem;
    }

    public void setNr_Largura(double nr_Largura) {
        this.nr_Largura = nr_Largura;
    }

    public void setNr_Profundidade(double nr_Profundidade) {
        this.nr_Profundidade = nr_Profundidade;
    }

    public int getOid_Tipo_Pallet() {
        return oid_Tipo_Pallet;
    }

    public void setOid_Tipo_Pallet(int oid_Tipo_Pallet) {
        this.oid_Tipo_Pallet = oid_Tipo_Pallet;
    }

    public String getNM_Produto() {
        return NM_Produto;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public void setNM_Produto(String NM_Produto) {
        this.NM_Produto = NM_Produto;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public String getNM_Embalagem() {
        return NM_Embalagem;
    }

    public void setNM_Embalagem(String NM_Embalagem) {
        this.NM_Embalagem = NM_Embalagem;
    }

    public String getNM_Tipo_Pallet() {
        return NM_Tipo_Pallet;
    }

    public void setNM_Tipo_Pallet(String NM_Tipo_Pallet) {
        this.NM_Tipo_Pallet = NM_Tipo_Pallet;
    }

    public String getCD_Produto() {
        return CD_Produto;
    }

    public void setCD_Produto(String CD_Produto) {
        this.CD_Produto = CD_Produto;
    }

    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }

    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }

    public String getDM_Situacao() {
        return DM_Situacao;
    }

    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }

    public void setNR_QT_Minimo(double NR_QT_Minimo) {
        this.NR_QT_Minimo = NR_QT_Minimo;
    }

    public double getNR_QT_Minimo() {
        return NR_QT_Minimo;
    }

    public void setVL_Markup(double VL_Markup) {
        this.VL_Markup = VL_Markup;
    }

    public void setVL_Preco_Custo(double VL_Preco_Custo) {
        this.VL_Preco_Custo = VL_Preco_Custo;
    }

    public void setVL_Produto(double VL_Produto) {
        this.VL_Produto = VL_Produto;
    }

    public double getVL_Markup() {
        return VL_Markup;
    }

    public double getVL_Preco_Custo() {
        return VL_Preco_Custo;
    }

    public double getVL_Produto() {
        return VL_Produto;
    }

    public void setNR_QT_Atual(double NR_QT_Atual) {
        this.NR_QT_Atual = NR_QT_Atual;
    }

    public double getNR_QT_Atual() {
        return NR_QT_Atual;
    }

    public void setNM_Estoque(String NM_Estoque) {
        this.NM_Estoque = NM_Estoque;
    }

    public String getNM_Estoque() {
        return NM_Estoque;
    }

    public void setNM_Localizacao(String NM_Localizacao) {
        this.NM_Localizacao = NM_Localizacao;
    }

    public String getNM_Localizacao() {
        return NM_Localizacao;
    }

    public void setOID_Tipo_Estoque(long OID_Tipo_Estoque) {
        this.OID_Tipo_Estoque = OID_Tipo_Estoque;
    }

    public long getOID_Tipo_Estoque() {
        return OID_Tipo_Estoque;
    }

    public void setNM_Deposito(String NM_Deposito) {
        this.NM_Deposito = NM_Deposito;
    }

    public String getNM_Deposito() {
        return NM_Deposito;
    }

    public void setNM_Rua(String NM_Rua) {
        this.NM_Rua = NM_Rua;
    }

    public String getNM_Rua() {
        return NM_Rua;
    }

    public void setNM_Endereco(String NM_Endereco) {
        this.NM_Endereco = NM_Endereco;
    }

    public String getNM_Endereco() {
        return NM_Endereco;
    }

    public void setNM_Apartamento(String NM_Apartamento) {
        this.NM_Apartamento = NM_Apartamento;
    }

    public String getNM_Apartamento() {
        return NM_Apartamento;
    }

    public void setNR_Lote(long NR_Lote) {
        this.NR_Lote = NR_Lote;
    }

    public long getNR_Lote() {
        return NR_Lote;
    }

    public void setDT_Validade(String DT_Validade) {
        this.DT_Validade = DT_Validade;
    }

    public String getDT_Validade() {
        return DT_Validade;
    }

    public void setDM_Validade(String DM_Validade) {
        this.DM_Validade = DM_Validade;
    }

    public String getDM_Validade() {
        return DM_Validade;
    }

    public double getPE_Comissao() {
        return PE_Comissao;
    }

    public void setPE_Comissao(double comissao) {
        PE_Comissao = comissao;
    }
    public int getOid_Localizacao() {
        return oid_Localizacao;
    }
    public void setOid_Localizacao(int oid_Localizacao) {
        this.oid_Localizacao = oid_Localizacao;
    }
    public String getDT_Cadastro() {
        return DT_Cadastro;
    }
    public void setDT_Cadastro(String cadastro) {
        DT_Cadastro = cadastro;
    }
    
    public double getPE_Acrescimo_21_Dias() {
        return PE_Acrescimo_21_Dias;
    }
    
    public void setPE_Acrescimo_21_Dias(double acrescimo_21_Dias) {
        PE_Acrescimo_21_Dias = acrescimo_21_Dias;
    }
    
    public double getPE_Acrescimo_28_Dias() {
        return PE_Acrescimo_28_Dias;
    }
    
    public void setPE_Acrescimo_28_Dias(double acrescimo_28_Dias) {
        PE_Acrescimo_28_Dias = acrescimo_28_Dias;
    }
    
    public double getPE_Desconto_7_Dias() {
        return PE_Desconto_7_Dias;
    }
    
    public void setPE_Desconto_7_Dias(double desconto_7_Dias) {
        PE_Desconto_7_Dias = desconto_7_Dias;
    }
    
    public double getPE_Desconto_Avista() {
        return PE_Desconto_Avista;
    }
    
    public void setPE_Desconto_Avista(double desconto_Avista) {
        PE_Desconto_Avista = desconto_Avista;
    }
	public double getNR_Peso_Bruto() {
		return NR_Peso_Bruto;
	}
	public void setNR_Peso_Bruto(double peso_Bruto) {
		NR_Peso_Bruto = peso_Bruto;
	}
	public double getNR_Peso_Liquido() {
		return NR_Peso_Liquido;
	}
	public void setNR_Peso_Liquido(double peso_Liquido) {
		NR_Peso_Liquido = peso_Liquido;
	}
	public double getNR_Quantidade() {
		return NR_Quantidade;
	}
	public void setNR_Quantidade(double quantidade) {
		NR_Quantidade = quantidade;
	}
}