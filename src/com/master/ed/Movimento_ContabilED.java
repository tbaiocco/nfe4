package com.master.ed;

import java.io.Serializable;

import java.util.ArrayList;
import com.master.util.*;

/**
 * @author Régis Steigleder
 * @serial Movimentos Contabeis
 * @serialData 14/10/2005
 */

public class Movimento_ContabilED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = -4435790779568737045L;

	public Movimento_ContabilED() {
        super();
    }

    private long oid_Movimento_Contabil;
    private String dt_Movimento;
	private long oid_Unidade;
	private long oid_Origem;
	private long oid_Conta;
	private String tx_Historico;
	private String tx_Historico_Complementar;
	private String dm_Debito_Credito;
	private String dm_Lista_Encerramento;
	private double vl_Lancamento;
	private double vl_Lancamento_Inicial;
	private double vl_Lancamento_Final;
	private String dt_Digitacao;
	private String tx_Chave_Origem;
	private long nr_Lote;
	private String cd_Lote;
	private long oid_Historico;
	private long oid_Centro_Custo;
	//Campos só de tela
	private String cd_Unidade;
	private String nm_Fantasia;
	private String cd_Origem;
	private String nm_Origem;
	private String cd_Conta;
	private String nm_Conta;
	private String cd_Historico;
	private String vl_Lancamento_TX;
	private String vl_Lancamento_Inicial_TX;
	private String vl_Lancamento_Final_TX;
	private String cd_Centro_Custo;
	private String nm_Centro_Custo;
	//Campos só de telas de filtros
	private String dt_Movimento_Inicial;
	private String dt_Movimento_Final;
	private String dt_Digitacao_Inicial;
	private String dt_Digitacao_Final;
	private String tipo;
	private String cd_Estrutural_Inicial;
	private String cd_Estrutural_Final;
	private String nm_Estrutural_Inicial;
	private String nm_Estrutural_Final;
	private long oid_Estrutural_Inicial;
	private long oid_Estrutural_Final;
	private long nr_Livro;
	private long nr_Pagina;
	private String dt_Emissao;
	private String cd_Conta_Contrapartida;
	private String nm_Conta_Contrapartida;
	private long oid_Conta_Contrapartida;
	private String tx_Inconsistencia;
	// Campos para relatórios
	private String dm_Tipo_Conta;
	private String cd_Estrutural;
	private double vl_Debito;
	private double vl_Credito;
	private double vl_Saldo;
	private double vl_Saldo_Atual;
	private Integer nr_Grau;

	private ArrayList movctbLista;

	private String nm_Cabecalho;

	public String getNm_Cabecalho() {
		return nm_Cabecalho;
	}
	public void setNm_Cabecalho(String nm_Cabecalho) {
		this.nm_Cabecalho = nm_Cabecalho;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getCd_Conta() {
		return cd_Conta;
	}
	public String getCd_Historico() {
		return cd_Historico;
	}
	public String getCd_Lote() {
		return cd_Lote;
	}
	public String getCd_Origem() {
		return cd_Origem;
	}
	public String getCd_Unidade() {
		return cd_Unidade;
	}
	public String getDm_Debito_Credito() {
		return dm_Debito_Credito;
	}
	public String getDt_Digitacao() {
		return dt_Digitacao;
	}
	public String getDt_Movimento() {
		return dt_Movimento;
	}
	public String getNm_Conta() {
		return nm_Conta;
	}
	public String getNm_Fantasia() {
		return nm_Fantasia;
	}
	public String getNm_Origem() {
		return nm_Origem;
	}
	public long getOid_Conta() {
		return oid_Conta;
	}
	public long getOid_Historico() {
		return oid_Historico;
	}
	public long getOid_Movimento_Contabil() {
		return oid_Movimento_Contabil;
	}
	public long getNr_Lote() {
		return nr_Lote;
	}
	public long getOid_Origem() {
		return oid_Origem;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public String getTx_Chave_Origem() {
		return tx_Chave_Origem;
	}
	public String getTx_Historico() {
		return tx_Historico;
	}
	public String getTx_Historico_Complementar() {
		return tx_Historico_Complementar ;
	}
	public double getVl_Lancamento() {
		return vl_Lancamento;
	}
	public String getVl_Lancamento_TX() {
		return vl_Lancamento_TX;
	}
	public String getDt_Digitacao_Final() {
		return dt_Digitacao_Final;
	}
	public String getDt_Digitacao_Inicial() {
		return dt_Digitacao_Inicial;
	}
	public String getDt_Movimento_Final() {
		return dt_Movimento_Final;
	}
	public String getDt_Movimento_Inicial() {
		return dt_Movimento_Inicial;
	}
	public String getTipo() {
		return tipo;
	}
	public long getOid_Centro_Custo() {
		return oid_Centro_Custo;
	}
	public String getCd_Centro_Custo() {
		return cd_Centro_Custo;
	}
	public String getNm_Centro_Custo() {
		return nm_Centro_Custo;
	}
	public String getCd_Estrutural_Final() {
		return cd_Estrutural_Final;
	}
	public String getCd_Estrutural_Inicial() {
		return cd_Estrutural_Inicial;
	}
	public String getDt_Emissao() {
		return dt_Emissao;
	}
	public ArrayList getMovctbLista() {
		return movctbLista;
	}
	public long getNr_Livro() {
		return nr_Livro;
	}
	public long getNr_Pagina() {
		return nr_Pagina;
	}
	public String getCd_Conta_Contrapartida() {
		return cd_Conta_Contrapartida;
	}
	public String getNm_Conta_Contrapartida() {
		return nm_Conta_Contrapartida;
	}
	public String getNm_Estrutural_Final() {
		return nm_Estrutural_Final;
	}
	public String getNm_Estrutural_Inicial() {
		return nm_Estrutural_Inicial;
	}
	public long getOid_Conta_Contrapartida() {
		return oid_Conta_Contrapartida;
	}
	public long getOid_Estrutural_Final() {
		return oid_Estrutural_Final;
	}
	public long getOid_Estrutural_Inicial() {
		return oid_Estrutural_Inicial;
	}
	public String getCd_Estrutural() {
		return cd_Estrutural;
	}
	public double getVl_Credito() {
		return vl_Credito;
	}
	public double getVl_Debito() {
		return vl_Debito;
	}
	public double getVl_Saldo() {
		return vl_Saldo;
	}
	public double getVl_Saldo_Atual() {
		return vl_Saldo_Atual;
	}
	public String getDm_Tipo_Conta() {
		return dm_Tipo_Conta;
	}
	public Integer getNr_Grau() {
		return nr_Grau;
	}
	public String getTx_Inconsistencia() {
		return tx_Inconsistencia;
	}
	public void setCd_Conta(String cd_Conta) {
		this.cd_Conta = cd_Conta;
	}
	public void setCd_Historico(String cd_Historico) {
		this.cd_Historico = cd_Historico;
	}
	public void setCd_Lote(String cd_Lote) {
		this.cd_Lote = cd_Lote;
	}
	public void setCd_Origem(String cd_Origem) {
		this.cd_Origem = cd_Origem;
	}
	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}
	public void setDm_Debito_Credito(String dm_Debito_Credito) {
		this.dm_Debito_Credito = dm_Debito_Credito;
	}
	public void setDt_Digitacao(String dt_Digitacao) {
		this.dt_Digitacao = dt_Digitacao;
	}
	public void setDt_Movimento(String dt_Movimento) {
		this.dt_Movimento = dt_Movimento;
	}
	public void setNm_Conta(String nm_Conta) {
		this.nm_Conta = nm_Conta;
	}
	public void setNm_Fantasia(String nm_Fantasia) {
		this.nm_Fantasia = nm_Fantasia;
	}
	public void setNm_Origem(String nm_Origem) {
		this.nm_Origem = nm_Origem;
	}
	public void setOid_Conta(long oid_Conta) {
		this.oid_Conta = oid_Conta;
	}
	public void setOid_Historico(long oid_Historico) {
		this.oid_Historico = oid_Historico;
	}
	public void setOid_Movimento_Contabil(long oid_Movimento_Contabil) {
		this.oid_Movimento_Contabil = oid_Movimento_Contabil;
	}
	public void setNr_Lote(
			long nr_Lote) {
		this.nr_Lote = nr_Lote;
	}
	public void setOid_Origem(long oid_Origem) {
		this.oid_Origem = oid_Origem;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public void setTx_Chave_Origem(String tx_Chave_Origem) {
		this.tx_Chave_Origem = tx_Chave_Origem;
	}
	public void setTx_Historico(String tx_Historico) {
		this.tx_Historico = tx_Historico;
	}
	public void setTx_Historico_Complementar(String tx_Historico_Complementar) {
		this.tx_Historico_Complementar = tx_Historico_Complementar;
	}
	public void setVl_Lancamento(double vl_Lancamento) {
		this.vl_Lancamento = vl_Lancamento;
	}
	public void setVl_Lancamento_TX(String vl_Lancamento_TX) {
		this.vl_Lancamento_TX = vl_Lancamento_TX;
        this.setVl_Lancamento(Valores.converteStringToDouble(vl_Lancamento_TX));
    }
	public void setDt_Digitacao_Final(String dt_Digitacao_Final) {
		this.dt_Digitacao_Final = dt_Digitacao_Final;
	}
	public void setDt_Digitacao_Inicial(String dt_Digitacao_Inicial) {
		this.dt_Digitacao_Inicial = dt_Digitacao_Inicial;
	}
	public void setDt_Movimento_Final(String dt_Movimento_Final) {
		this.dt_Movimento_Final = dt_Movimento_Final;
	}
	public void setDt_Movimento_Inicial(String dt_Movimento_Inicial) {
		this.dt_Movimento_Inicial = dt_Movimento_Inicial;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setOid_Centro_Custo(long oid_Centro_Custo) {
		this.oid_Centro_Custo = oid_Centro_Custo;
	}
	public void setCd_Centro_Custo(String cd_Centro_Custo) {
		this.cd_Centro_Custo = cd_Centro_Custo;
	}
	public void setNm_Centro_Custo(String nm_Centro_Custo) {
		this.nm_Centro_Custo = nm_Centro_Custo;
	}
	public void setCd_Estrutural_Final(String cd_Estrutural_Final) {
		this.cd_Estrutural_Final = cd_Estrutural_Final;
	}
	public void setCd_Estrutural_Inicial(String cd_Estrutural_Inicial) {
		this.cd_Estrutural_Inicial = cd_Estrutural_Inicial;
	}
	public void setDt_Emissao(String dt_Emissao) {
		this.dt_Emissao = dt_Emissao;
	}
	public void setMovctbLista(ArrayList movctbLista) {
		this.movctbLista = movctbLista;
	}
	public void setNr_Livro(long nr_Livro) {
		this.nr_Livro = nr_Livro;
	}
	public void setNr_Pagina(long nr_Pagina) {
		this.nr_Pagina = nr_Pagina;
	}
	public void setCd_Conta_Contrapartida(String cd_Conta_Contrapartida) {
		this.cd_Conta_Contrapartida = cd_Conta_Contrapartida;
	}
	public void setNm_Conta_Contrapartida(String nm_Conta_Contrapartida) {
		this.nm_Conta_Contrapartida = nm_Conta_Contrapartida;
	}
	public void setNm_Estrutural_Final(String nm_Estrutural_Final) {
		this.nm_Estrutural_Final = nm_Estrutural_Final;
	}
	public void setNm_Estrutural_Inicial(String nm_Estrutural_Inicial) {
		this.nm_Estrutural_Inicial = nm_Estrutural_Inicial;
	}
	public void setOid_Conta_Contrapartida(long oid_Conta_Contrapartida) {
		this.oid_Conta_Contrapartida = oid_Conta_Contrapartida;
	}
	public void setOid_Estrutural_Final(long oid_Estrutural_Final) {
		this.oid_Estrutural_Final = oid_Estrutural_Final;
	}
	public void setOid_Estrutural_Inicial(long oid_Estrutural_Inicial) {
		this.oid_Estrutural_Inicial = oid_Estrutural_Inicial;
	}
	public void setCd_Estrutural(String cd_Estrutural) {
		this.cd_Estrutural = cd_Estrutural;
	}
	public void setVl_Credito(double vl_Credito) {
		this.vl_Credito = vl_Credito;
	}
	public void setVl_Debito(double vl_Debito) {
		this.vl_Debito = vl_Debito;
	}
	public void setVl_Saldo(double vl_Saldo) {
		this.vl_Saldo = vl_Saldo;
	}
	public void setVl_Saldo_Atual(double vl_Saldo_Atual) {
		this.vl_Saldo_Atual = vl_Saldo_Atual;
	}
	public void setDm_Tipo_Conta(String dm_Tipo_Conta) {
		this.dm_Tipo_Conta = dm_Tipo_Conta;
	}
	public void setNr_Grau(Integer nr_Grau) {
		this.nr_Grau = nr_Grau;
	}
	public void setTx_Inconsistencia(String tx_Inconsistencia) {
		this.tx_Inconsistencia = tx_Inconsistencia;
	}
	public double getVl_Lancamento_Final() {
		return vl_Lancamento_Final;
	}
	public void setVl_Lancamento_Final(double vl_Lancamento_Final) {
		this.vl_Lancamento_Final = vl_Lancamento_Final;
	}
	public double getVl_Lancamento_Inicial() {
		return vl_Lancamento_Inicial;
	}
	public void setVl_Lancamento_Inicial(double vl_Lancamento_Inicial) {
		this.vl_Lancamento_Inicial = vl_Lancamento_Inicial;
	}
	public String getVl_Lancamento_Final_TX() {
		return vl_Lancamento_Final_TX;
	}
	public void setVl_Lancamento_Final_TX(String vl_Lancamento_Final_TX) {
		this.vl_Lancamento_Final_TX = vl_Lancamento_Final_TX;
		this.setVl_Lancamento_Final(Valores.converteStringToDouble(vl_Lancamento_Final_TX));
	}
	public String getVl_Lancamento_Inicial_TX() {
		return vl_Lancamento_Inicial_TX;
	}
	public void setVl_Lancamento_Inicial_TX(String vl_Lancamento_Inicial_TX) {
		this.vl_Lancamento_Inicial_TX = vl_Lancamento_Inicial_TX;
		this.setVl_Lancamento_Inicial(Valores.converteStringToDouble(vl_Lancamento_Inicial_TX));
	}
	public String getDm_Lista_Encerramento() {
		return dm_Lista_Encerramento;
	}
	public void setDm_Lista_Encerramento(String dm_Lista_Encerramento) {
		this.dm_Lista_Encerramento = dm_Lista_Encerramento;
	}
}
