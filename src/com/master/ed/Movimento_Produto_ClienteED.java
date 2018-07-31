package com.master.ed;

/**
 * @author André Valadas
 * - Movimento dos Produtos Clientes
 */
public class Movimento_Produto_ClienteED extends MasterED {

    public Movimento_Produto_ClienteED() {
        super();
    }
    public Movimento_Produto_ClienteED(long oid_Movimento_Produto) {
        this.oid_Movimento_Produto = oid_Movimento_Produto;
    }
    public Movimento_Produto_ClienteED(int oid_Tipo_Movimento_Produto, 
    								   String oid_Produto_Cliente, 
    								   int oid_Tipo_Estoque, 
    								   int oid_Localizacao, 
    								   String oid_Nota_Fiscal, 
    								   int oid_Operador, 
    								   int oid_Requisicao_Produto, 
    								   double quantidade_Requerida,
    								   double quantidade_Efetiva,
    								   double quantidade_Apos,
    								   String dt_movimentacao, 
    								   String hr_movimentacao,
    								   String tx_Observacao) {
        this.oid_Tipo_Movimento_Produto = oid_Tipo_Movimento_Produto;
        this.oid_Produto_Cliente = oid_Produto_Cliente;
        this.oid_Tipo_Estoque = oid_Tipo_Estoque;
        this.oid_Localizacao = oid_Localizacao;
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
        this.oid_Operador = oid_Operador;
        this.oid_Requisicao_Produto = oid_Requisicao_Produto;
        this.NR_Quantidade_Requerida = quantidade_Requerida;
        this.NR_Quantidade_Efetiva = quantidade_Efetiva;
        this.NR_Quantidade_Estoque_Apos = quantidade_Apos;
        this.DT_Movimentacao = dt_movimentacao;
        this.HR_Movimentacao = hr_movimentacao;
        this.tx_Observacao = tx_Observacao;
    }

    public Produto_ClienteED edProduto = new Produto_ClienteED();
    public WMS_Tipo_MovimentoED edTipo_Movimento = new WMS_Tipo_MovimentoED();
    public WMS_Tipo_EstoqueED edTipo_Estoque = new WMS_Tipo_EstoqueED();
    public WMS_LocalizacaoED edLocalizacao = new WMS_LocalizacaoED();
    public Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaED();

    private long oid_Movimento_Produto;
    private int oid_Tipo_Movimento_Produto;
    private String oid_Produto_Cliente;
    private int oid_Localizacao;
    private int oid_Tipo_Estoque;
    private String oid_Nota_Fiscal;
    private int oid_Operador;
    private int oid_Requisicao_Produto;
    private double NR_Quantidade_Requerida;
    private double NR_Quantidade_Efetiva;
    private double NR_Quantidade_Estoque_Apos;
    private String DT_Movimentacao;
    private String HR_Movimentacao;
    private String dm_Ordenacao;
    private String tx_Observacao;
    
    private String dt_inicial;
    private String dt_final;

    public String getDT_Movimentacao() {
        return DT_Movimentacao;
    }
    public void setDT_Movimentacao(String movimentacao) {
        DT_Movimentacao = movimentacao;
    }
    public String getHR_Movimentacao() {
        return HR_Movimentacao;
    }
    public void setHR_Movimentacao(String movimentacao) {
        HR_Movimentacao = movimentacao;
    }
    public double getNR_Quantidade_Efetiva() {
        return NR_Quantidade_Efetiva;
    }
    public void setNR_Quantidade_Efetiva(double quantidade_Efetiva) {
        NR_Quantidade_Efetiva = quantidade_Efetiva;
    }
    public double getNR_Quantidade_Requerida() {
        return NR_Quantidade_Requerida;
    }
    public void setNR_Quantidade_Requerida(double quantidade_Requerida) {
        NR_Quantidade_Requerida = quantidade_Requerida;
    }
    public long getOid_Movimento_Produto() {
        return oid_Movimento_Produto;
    }
    public void setOid_Movimento_Produto(long oid_Movimento_Produto) {
        this.oid_Movimento_Produto = oid_Movimento_Produto;
    }
    public int getOid_Operador() {
        return oid_Operador;
    }
    public void setOid_Operador(int oid_Operador) {
        this.oid_Operador = oid_Operador;
    }
    public String getOid_Produto_Cliente() {
        return oid_Produto_Cliente;
    }
    public void setOid_Produto_Cliente(String oid_Produto_Cliente) {
        this.oid_Produto_Cliente = oid_Produto_Cliente;
    }
    public int getOid_Requisicao_Produto() {
        return oid_Requisicao_Produto;
    }
    public void setOid_Requisicao_Produto(int oid_Requisicao_Produto) {
        this.oid_Requisicao_Produto = oid_Requisicao_Produto;
    }
    public int getOid_Tipo_Movimento_Produto() {
        return oid_Tipo_Movimento_Produto;
    }
    public void setOid_Tipo_Movimento_Produto(int oid_Tipo_Movimento_Produto) {
        this.oid_Tipo_Movimento_Produto = oid_Tipo_Movimento_Produto;
    }
    public String getOid_Nota_Fiscal() {
        return oid_Nota_Fiscal;
    }
    public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
    }
    public int getOid_Localizacao() {
        return oid_Localizacao;
    }
    public void setOid_Localizacao(int oid_Localizacao) {
        this.oid_Localizacao = oid_Localizacao;
    }
    public int getOid_Tipo_Estoque() {
        return oid_Tipo_Estoque;
    }
    public void setOid_Tipo_Estoque(int oid_Tipo_Estoque) {
        this.oid_Tipo_Estoque = oid_Tipo_Estoque;
    }
	public String getDm_Ordenacao() {
		return dm_Ordenacao;
	}
	public void setDm_Ordenacao(String dm_Ordenacao) {
		this.dm_Ordenacao = dm_Ordenacao;
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
	public double getNR_Quantidade_Estoque_Apos() {
		return NR_Quantidade_Estoque_Apos;
	}
	public void setNR_Quantidade_Estoque_Apos(double quantidade_Estoque_Apos) {
		NR_Quantidade_Estoque_Apos = quantidade_Estoque_Apos;
	}
	public String getTx_Observacao() {
		return tx_Observacao;
	}
	public void setTx_Observacao(String tx_Observacao) {
		this.tx_Observacao = tx_Observacao;
	}
}