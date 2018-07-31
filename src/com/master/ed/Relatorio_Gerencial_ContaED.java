package com.master.ed;

import java.io.Serializable;

import com.master.rn.Relatorio_Gerencial_ContaRN;
import com.master.util.Excecoes;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 08/01/2006
 */
public class Relatorio_Gerencial_ContaED extends MasterED implements Serializable {

	private static final long serialVersionUID = 1L;

	public Relatorio_Gerencial_ContaED() {
        super();
    }
	private long oid_Relatorio_Gerencial_Conta;
	private long oid_Relatorio_Gerencial;
	private long oid_Conta;
	private long oid_Usuario;
	private long oid_Relatorio_Gerencial_Grupo;
	
	private String cd_Conta;
	private String cd_Estrutural;
	private String nm_Conta;
	private String nm_Usuario;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    
	public String getCd_Conta() {
		return cd_Conta;
	}
	public String getCd_Estrutural() {
		return cd_Estrutural;
	}
	public String getNm_Conta() {
		return nm_Conta;
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
	public long getOid_Usuario() {
		return oid_Usuario;
	}
	public void setCd_Conta(String cd_Conta) {
		this.cd_Conta = cd_Conta;
	}
	public void setCd_Estrutural(String cd_Estrutural) {
		this.cd_Estrutural = cd_Estrutural;
	}
	public void setNm_Conta(String nm_Conta) {
		this.nm_Conta = nm_Conta;
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
	public void setOid_Usuario(long oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
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
			Relatorio_Gerencial_ContaRN rn = new Relatorio_Gerencial_ContaRN();
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
		Relatorio_Gerencial_ContaED ed = new Relatorio_Gerencial_ContaED();  		  // Instancia ed para a busca
		ed.setOid_Relatorio_Gerencial_Grupo(this.getOid_Relatorio_Gerencial_Grupo()); // Monta o oid do relatorio gerencial
		ed.setOid_Conta(this.getOid_Conta()); 										  // Monta o oid da conta
		ed = new Relatorio_Gerencial_ContaRN().getByRecord(ed);  					  // Retorna o registro no mesmo ed instanciado
		if (ed.getOid_Relatorio_Gerencial_Conta()== 0){          					  // Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Relatorio_Gerencial_Conta() == this.getOid_Relatorio_Gerencial_Conta()){ // Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
}       
