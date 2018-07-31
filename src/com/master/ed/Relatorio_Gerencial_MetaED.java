package com.master.ed;

import java.io.Serializable;

import com.master.rn.Relatorio_Gerencial_MetaRN;
import com.master.util.Excecoes;
import com.master.util.Valores;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais Metas
 * @serialData 08/01/2006
 */
public class Relatorio_Gerencial_MetaED extends RelatorioBaseED implements Serializable {

	private static final long serialVersionUID = 1L;

	public Relatorio_Gerencial_MetaED() {
        super();
    }
	private long oid_Relatorio_Gerencial_Meta;
	private long oid_Relatorio_Gerencial;
	private long oid_Unidade;
	private long oid_Relatorio_Gerencial_Conta;
	private String tx_Mes_Ano;
	private double vl_Meta;
	private String vl_Meta_TX;
	private double vl_Tendencia;
	private String vl_Tendencia_TX;
	private double vl_Realizado;
	private String vl_Realizado_TX;
	private String dm_Congelado;
	// Outros campos utilizados em relatorios
	private String cd_Relatorio_Gerencial;
	private String nm_Relatorio_Gerencial;
	private String cd_Unidade;
	private String nm_Fantasia;
	private long oid_Relatorio_Gerencial_Grupo;
	private String cd_Relatorio_Gerencial_Grupo;
	private String nm_Relatorio_Gerencial_Grupo;
	private long oid_Conta;
	private String cd_Conta;
	private String nm_Conta;
	private String cd_Estrutural;
	private long oid_Usuario;
	private String nm_Usuario;
   
	private String tx_Mes_Ano_Ext1;
	private String tx_Mes_Ano_Ext2;
	private String tx_Mes_Ano_Ext3;
	private double vl_Meta2;
	private double vl_Tendencia2;
	private double vl_Realizado2;
	private double vl_Meta3;
	private double vl_Tendencia3;
	private double vl_Realizado3;
	private String tx_Ultima_Data_Mes1;
	private String tx_Ultima_Data_Mes2;
	private String tx_Ultima_Data_Mes3;
		
	// Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    
	public String getCd_Conta() {
		return cd_Conta;
	}
	public String getCd_Estrutural() {
		return cd_Estrutural;
	}
	public String getCd_Relatorio_Gerencial() {
		return cd_Relatorio_Gerencial;
	}
	public String getCd_Relatorio_Gerencial_Grupo() {
		return cd_Relatorio_Gerencial_Grupo;
	}
	public String getDm_Congelado() {
		return dm_Congelado;
	}
	public String getNm_Conta() {
		return nm_Conta;
	}
	public String getNm_Relatorio_Gerencial() {
		return nm_Relatorio_Gerencial;
	}
	public String getNm_Relatorio_Gerencial_Grupo() {
		return nm_Relatorio_Gerencial_Grupo;
	}
	public String getNm_Usuario() {
		return nm_Usuario;
	}
	public long getOid_Conta() {
		return oid_Conta;
	}
	public long getOid_Relatorio_Gerencial() {
		return oid_Relatorio_Gerencial;
	}
	public long getOid_Relatorio_Gerencial_Conta() {
		return oid_Relatorio_Gerencial_Conta;
	}
	public long getOid_Relatorio_Gerencial_Grupo() {
		return oid_Relatorio_Gerencial_Grupo;
	}
	public long getOid_Relatorio_Gerencial_Meta() {
		return oid_Relatorio_Gerencial_Meta;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public long getOid_Usuario() {
		return oid_Usuario;
	}
	public String getTx_Mes_Ano() {
		return tx_Mes_Ano;
	}
	public double getVl_Meta() {
		return vl_Meta;
	}
	public String getVl_Meta_TX() {
		return vl_Meta_TX;
	}
	public double getVl_Realizado() {
		return vl_Realizado;
	}
	public String getVl_Realizado_TX() {
		return vl_Realizado_TX;
	}
	public double getVl_Tendencia() {
		return vl_Tendencia;
	}
	public String getVl_Tendencia_TX() {
		return vl_Tendencia_TX;
	}
	public void setCd_Conta(String cd_Conta) {
		this.cd_Conta = cd_Conta;
	}
	public void setCd_Estrutural(String cd_Estrutural) {
		this.cd_Estrutural = cd_Estrutural;
	}
	public void setCd_Relatorio_Gerencial(String cd_Relatorio_Gerencial) {
		this.cd_Relatorio_Gerencial = cd_Relatorio_Gerencial;
	}
	public void setCd_Relatorio_Gerencial_Grupo(String cd_Relatorio_Gerencial_Grupo) {
		this.cd_Relatorio_Gerencial_Grupo = cd_Relatorio_Gerencial_Grupo;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
	public void setDm_Congelado(String dm_Congelado) {
		this.dm_Congelado = dm_Congelado;
	}
	public void setNm_Conta(String nm_Conta) {
		this.nm_Conta = nm_Conta;
	}
	public void setNm_Relatorio_Gerencial(String nm_Relatorio_Gerencial) {
		this.nm_Relatorio_Gerencial = nm_Relatorio_Gerencial;
	}
	public void setNm_Relatorio_Gerencial_Grupo(String nm_Relatorio_Gerencial_Grupo) {
		this.nm_Relatorio_Gerencial_Grupo = nm_Relatorio_Gerencial_Grupo;
	}
	public void setNm_Usuario(String nm_Usuario) {
		this.nm_Usuario = nm_Usuario;
	}
	public void setOid_Conta(long oid_Conta) {
		this.oid_Conta = oid_Conta;
	}
	public void setOid_Relatorio_Gerencial(long oid_Relatorio_Gerencial) {
		this.oid_Relatorio_Gerencial = oid_Relatorio_Gerencial;
	}
	public void setOid_Relatorio_Gerencial_Conta(long oid_Relatorio_Gerencial_Conta) {
		this.oid_Relatorio_Gerencial_Conta = oid_Relatorio_Gerencial_Conta;
	}
	public void setOid_Relatorio_Gerencial_Grupo(long oid_Relatorio_Gerencial_Grupo) {
		this.oid_Relatorio_Gerencial_Grupo = oid_Relatorio_Gerencial_Grupo;
	}
	public void setOid_Relatorio_Gerencial_Meta(long oid_Relatorio_Gerencial_Meta) {
		this.oid_Relatorio_Gerencial_Meta = oid_Relatorio_Gerencial_Meta;
	}
	public void setOid_Usuario(long oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public void setTx_Mes_Ano(String tx_Mes_Ano) {
		this.tx_Mes_Ano = tx_Mes_Ano;
	}
	public void setVl_Meta(double vl_Meta) {
		this.vl_Meta = vl_Meta;
	}
	public void setVl_Meta_TX(String vl_Meta_TX) {
		this.vl_Meta_TX = vl_Meta_TX;
		this.setVl_Meta(Valores.converteStringToDouble(vl_Meta_TX));
	}
	public void setVl_Realizado(double vl_Realizado) {
		this.vl_Realizado = vl_Realizado;
	}
	public void setVl_Realizado_TX(String vl_Realizado_TX) {
		this.vl_Realizado_TX = vl_Realizado_TX;
		this.setVl_Realizado(Valores.converteStringToDouble(vl_Realizado_TX));
	}
	public void setVl_Tendencia(double vl_Tendencia) {
		this.vl_Tendencia = vl_Tendencia;
	}
	public void setVl_Tendencia_TX(String vl_Tendencia_TX) {
		this.vl_Tendencia_TX = vl_Tendencia_TX;
		this.setVl_Tendencia(Valores.converteStringToDouble(vl_Tendencia_TX));
	}
	/**
	 * isIncluidoOK
	 * Verifica se o código já existe para inclusão
	 * Retorna false se existir e não inclui
	 * @return boolean
	 * @throws Excecoes
	 */
	public boolean isIncluidoOK() throws Excecoes {
		if (this.isCodigoExistente() == false){
			Relatorio_Gerencial_MetaRN rn = new Relatorio_Gerencial_MetaRN();
			rn.inclui(this);
			return true;
		}else{
			return false;
		}
	}
	public void setIncluidoOK(boolean incluidoOK) {
		this.incluidoOK = incluidoOK;
	}
	/***
	 * isCodigoExistente()
	 * Verifica se o codigo deste ed já existe cadastrado 
	 * @return boolean
	 * @throws Excecoes
	 */
	public boolean isCodigoExistente()  throws Excecoes {
		boolean dmVolta = true;
		Relatorio_Gerencial_MetaED ed = new Relatorio_Gerencial_MetaED();  			 // Instancia ed para a busca
		ed.setOid_Relatorio_Gerencial_Conta(this.getOid_Relatorio_Gerencial_Conta());// Monta o oid da conta gerencial
		ed.setTx_Mes_Ano(this.getTx_Mes_Ano());										 // Monta o mes ano
		ed = new Relatorio_Gerencial_MetaRN().getByRecord(ed);  					 // Retorna o registro no mesmo ed instanciado
		if (ed.getOid_Relatorio_Gerencial_Meta()== 0){          					 // Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Relatorio_Gerencial_Meta() == this.getOid_Relatorio_Gerencial()){ // Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public double getVl_Meta2() {
		return vl_Meta2;
	}
	public double getVl_Meta3() {
		return vl_Meta3;
	}
	public double getVl_Realizado2() {
		return vl_Realizado2;
	}
	public double getVl_Realizado3() {
		return vl_Realizado3;
	}
	public double getVl_Tendencia2() {
		return vl_Tendencia2;
	}
	public double getVl_Tendencia3() {
		return vl_Tendencia3;
	}
	public void setVl_Meta2(double vl_Meta2) {
		this.vl_Meta2 = vl_Meta2;
	}
	public void setVl_Meta3(double vl_Meta3) {
		this.vl_Meta3 = vl_Meta3;
	}
	public void setVl_Realizado2(double vl_Realizado2) {
		this.vl_Realizado2 = vl_Realizado2;
	}
	public void setVl_Realizado3(double vl_Realizado3) {
		this.vl_Realizado3 = vl_Realizado3;
	}
	public void setVl_Tendencia2(double vl_Tendencia2) {
		this.vl_Tendencia2 = vl_Tendencia2;
	}
	public void setVl_Tendencia3(double vl_Tendencia3) {
		this.vl_Tendencia3 = vl_Tendencia3;
	}
	public String getTx_Mes_Ano_Ext1() {
		return tx_Mes_Ano_Ext1;
	}
	public String getTx_Mes_Ano_Ext2() {
		return tx_Mes_Ano_Ext2;
	}
	public String getTx_Mes_Ano_Ext3() {
		return tx_Mes_Ano_Ext3;
	}
	public void setTx_Mes_Ano_Ext1(String tx_Mes_Ano_Ext1) {
		this.tx_Mes_Ano_Ext1 = tx_Mes_Ano_Ext1;
	}
	public void setTx_Mes_Ano_Ext2(String tx_Mes_Ano_Ext2) {
		this.tx_Mes_Ano_Ext2 = tx_Mes_Ano_Ext2;
	}
	public void setTx_Mes_Ano_Ext3(String tx_Mes_Ano_Ext3) {
		this.tx_Mes_Ano_Ext3 = tx_Mes_Ano_Ext3;
	}
	public String getCd_Unidade() {
		return cd_Unidade;
	}
	public String getNm_Fantasia() {
		return nm_Fantasia;
	}
	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}
	public void setNm_Fantasia(String nm_Fantasia) {
		this.nm_Fantasia = nm_Fantasia;
	}
	public String getTx_Ultima_Data_Mes1() {
		return tx_Ultima_Data_Mes1;
	}
	public void setTx_Ultima_Data_Mes1(String tx_Ultima_Data_Mes1) {
		this.tx_Ultima_Data_Mes1 = tx_Ultima_Data_Mes1;
	}
	public String getTx_Ultima_Data_Mes2() {
		return tx_Ultima_Data_Mes2;
	}
	public void setTx_Ultima_Data_Mes2(String tx_Ultima_Data_Mes2) {
		this.tx_Ultima_Data_Mes2 = tx_Ultima_Data_Mes2;
	}
	public String getTx_Ultima_Data_Mes3() {
		return tx_Ultima_Data_Mes3;
	}
	public void setTx_Ultima_Data_Mes3(String tx_Ultima_Data_Mes3) {
		this.tx_Ultima_Data_Mes3 = tx_Ultima_Data_Mes3;
	}
}       
