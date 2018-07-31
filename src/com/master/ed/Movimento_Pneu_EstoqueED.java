/*
* Created on 25/06/2007
*
*/
package com.master.ed;

/**
 * @author PRS
 * @serial Movimentação de pneus entre estoque 
 * @serialData 08/2007
 */
public class Movimento_Pneu_EstoqueED  extends RelatorioBaseED {

	private static final long serialVersionUID = -7120289437971131820L;
	
	private long oid_Movimento_Pneu_Estoque;
	private long oid_Empresa;
	private long oid_Pneu;
	private String dt_Movimento_Pneu_Estoque;
	private long oid_Local_Estoque_Origem;
	private long oid_Local_Estoque_Destino;
	// Para consultas
	private String dt_Movimento_Pneu_Estoque_Inicial;
	private String dt_Movimento_Pneu_Estoque_Final;
	private String nm_Local_Estoque_Origem;
	private String nm_Local_Estoque_Destino;
	private String nr_Fogo;
	
	public Movimento_Pneu_EstoqueED() {
	}

	public String getDt_Movimento_Pneu_Estoque() {
		return dt_Movimento_Pneu_Estoque;
	}

	public void setDt_Movimento_Pneu_Estoque(String dt_Movimento_Pneu_Estoque) {
		this.dt_Movimento_Pneu_Estoque = dt_Movimento_Pneu_Estoque;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public long getOid_Local_Estoque_Destino() {
		return oid_Local_Estoque_Destino;
	}

	public void setOid_Local_Estoque_Destino(long oid_Local_Estoque_Destino) {
		this.oid_Local_Estoque_Destino = oid_Local_Estoque_Destino;
	}

	public long getOid_Local_Estoque_Origem() {
		return oid_Local_Estoque_Origem;
	}

	public void setOid_Local_Estoque_Origem(long oid_Local_Estoque_Origem) {
		this.oid_Local_Estoque_Origem = oid_Local_Estoque_Origem;
	}

	public long getOid_Movimento_Pneu_Estoque() {
		return oid_Movimento_Pneu_Estoque;
	}

	public void setOid_Movimento_Pneu_Estoque(long oid_Movimento_Pneu_Estoque) {
		this.oid_Movimento_Pneu_Estoque = oid_Movimento_Pneu_Estoque;
	}

	public long getOid_Pneu() {
		return oid_Pneu;
	}

	public void setOid_Pneu(long oid_Pneu) {
		this.oid_Pneu = oid_Pneu;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDt_Movimento_Pneu_Estoque_Final() {
		return dt_Movimento_Pneu_Estoque_Final;
	}

	public void setDt_Movimento_Pneu_Estoque_Final(
			String dt_Movimento_Pneu_Estoque_Final) {
		this.dt_Movimento_Pneu_Estoque_Final = dt_Movimento_Pneu_Estoque_Final;
	}

	public String getDt_Movimento_Pneu_Estoque_Inicial() {
		return dt_Movimento_Pneu_Estoque_Inicial;
	}

	public void setDt_Movimento_Pneu_Estoque_Inicial(
			String dt_Movimento_Pneu_Estoque_Inicial) {
		this.dt_Movimento_Pneu_Estoque_Inicial = dt_Movimento_Pneu_Estoque_Inicial;
	}

	public String getNm_Local_Estoque_Destino() {
		return nm_Local_Estoque_Destino;
	}

	public void setNm_Local_Estoque_Destino(String nm_Local_Estoque_Destino) {
		this.nm_Local_Estoque_Destino = nm_Local_Estoque_Destino;
	}

	public String getNm_Local_Estoque_Origem() {
		return nm_Local_Estoque_Origem;
	}

	public void setNm_Local_Estoque_Origem(String nm_Local_Estoque_Origem) {
		this.nm_Local_Estoque_Origem = nm_Local_Estoque_Origem;
	}

	public String getNr_Fogo() {
		return nr_Fogo;
	}

	public void setNr_Fogo(String nr_Fogo) {
		this.nr_Fogo = nr_Fogo;
	}
}