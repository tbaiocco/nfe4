package com.master.ed;

import java.io.Serializable;

import com.master.rn.Periodo_AbertoRN;
import com.master.util.*;
/**
 * @author Régis Steigleder
 * @serial Movimentos Contabeis Temporarios
 * @serialData 23/11/2005
 */
public class Movimento_Contabil_TempED extends MasterED implements Serializable {

	private static final long serialVersionUID = -5753379960307800891L;

	public Movimento_Contabil_TempED() {
        super();
    }

    private long oid_Movimento_Contabil;
    private long  oid_Usuario;
	private String dt_Movimento;
	private long oid_Unidade;
	private long oid_Origem;
	private long oid_Conta;
	private String tx_Historico;
	private String tx_Historico_Complementar;
	private String dm_Debito_Credito;
	private double vl_Lancamento;
	private double vl_Lancamento_Inicial;
	private double vl_Lancamento_Final;
	private String dt_Digitacao;
	private String tx_Chave_Origem;
	private long nr_Lote;
	private String cd_Lote;
	private long oid_Historico;
	private long oid_Centro_Custo;
	private long nr_Lote_Originario;
	private int dm_Tela;
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
	private String cd_Estrutural;
	private String dm_Tipo_Conta;
	//Campos tela de filtro
	private String dt_Movimento_Inicial;
	private String dt_Movimento_Final;
	private String dt_Digitacao_Inicial;
	private String dt_Digitacao_Final;
	private String tipo;
	//Campos tela de lançamento
	private String cd_Centro_Custo;
	private String nm_Centro_Custo;
	private String dm_Centro_Custo;
	//Funções de validação
	private boolean periodo_Aberto;

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
	public long getOid_Centro_Custo() {
		return oid_Centro_Custo;
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
	public long getOid_Usuario() {
		return oid_Usuario;
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
	public long getNr_Lote_Originario() {
		return nr_Lote_Originario;
	}
	public String getCd_Centro_Custo() {
		return cd_Centro_Custo;
	}
	public String getNm_Centro_Custo() {
		return nm_Centro_Custo;
	}
	public String getDm_Centro_Custo() {
		return dm_Centro_Custo;
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
	public void setOid_Centro_Custo(long oid_Centro_Custo) {
		this.oid_Centro_Custo = oid_Centro_Custo;
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
	public void setOid_Usuario(long oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
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
	public void setNr_Lote_Originario(
			long nr_Lote_Originario) {
		this.nr_Lote_Originario = nr_Lote_Originario;
	}
	public void setCd_Centro_Custo(String cd_Centro_Custo) {
		this.cd_Centro_Custo = cd_Centro_Custo;
	}
	public void setNm_Centro_Custo(String nm_Centro_Custo) {
		this.nm_Centro_Custo = nm_Centro_Custo;
	}
	public void setDm_Centro_Custo(String dm_Centro_Custo) {
		this.dm_Centro_Custo = dm_Centro_Custo;
	}
	public boolean isPeriodo_Aberto() throws Excecoes {
		this.setPeriodo_Aberto(new Periodo_AbertoRN().isPeriodoAberto(this.getDt_Movimento()));
		return periodo_Aberto;
	}
	public void setPeriodo_Aberto(boolean periodo_Aberto) {
		this.periodo_Aberto = periodo_Aberto;
	}
	public String getCd_Estrutural() {
		return cd_Estrutural;
	}
	public void setCd_Estrutural(String cd_Estrutural) {
		this.cd_Estrutural = cd_Estrutural;
	}
	public String getDm_Tipo_Conta() {
		return dm_Tipo_Conta;
	}
	public void setDm_Tipo_Conta(String dm_Tipo_Conta) {
		this.dm_Tipo_Conta = dm_Tipo_Conta;
	}
	public int getDm_Tela() {
		return dm_Tela;
	}
	public void setDm_Tela(int dm_Tela) {
		this.dm_Tela = dm_Tela;
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
}
