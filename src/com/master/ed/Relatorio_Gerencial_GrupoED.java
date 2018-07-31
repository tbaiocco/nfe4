package com.master.ed;

import java.io.Serializable;

import com.master.rn.Relatorio_Gerencial_GrupoRN;
import com.master.util.Excecoes;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 08/01/2006
 */
public class Relatorio_Gerencial_GrupoED extends MasterED implements Serializable {

	private static final long serialVersionUID = 1L;

	public Relatorio_Gerencial_GrupoED() {
        super();
    }
	private long oid_Relatorio_Gerencial_Grupo;
	private String cd_Relatorio_Gerencial_Grupo;
	private String nm_Relatorio_Gerencial_Grupo;
	private long oid_Relatorio_Gerencial;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    
	public String getCd_Relatorio_Gerencial_Grupo() {
		return cd_Relatorio_Gerencial_Grupo;
	}
	public String getNm_Relatorio_Gerencial_Grupo() {
		return nm_Relatorio_Gerencial_Grupo;
	}
	public long getOid_Relatorio_Gerencial() {
		return oid_Relatorio_Gerencial;
	}
	public long getOid_Relatorio_Gerencial_Grupo() {
		return oid_Relatorio_Gerencial_Grupo;
	}
	public void setCd_Relatorio_Gerencial_Grupo(String cd_Relatorio_Gerencial_Grupo) {
		this.cd_Relatorio_Gerencial_Grupo = cd_Relatorio_Gerencial_Grupo;
	}
	public void setNm_Relatorio_Gerencial_Grupo(String nm_Relatorio_Gerencial_Grupo) {
		this.nm_Relatorio_Gerencial_Grupo = nm_Relatorio_Gerencial_Grupo;
	}
	public void setOid_Relatorio_Gerencial(long oid_Relatorio_Gerencial) {
		this.oid_Relatorio_Gerencial = oid_Relatorio_Gerencial;
	}
	public void setOid_Relatorio_Gerencial_Grupo(long oid_Relatorio_Gerencial_Grupo) {
		this.oid_Relatorio_Gerencial_Grupo = oid_Relatorio_Gerencial_Grupo;
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
			Relatorio_Gerencial_GrupoRN rn = new Relatorio_Gerencial_GrupoRN();
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
		Relatorio_Gerencial_GrupoED ed = new Relatorio_Gerencial_GrupoED();  		// Instancia ed para a busca
		ed.setOid_Relatorio_Gerencial(this.getOid_Relatorio_Gerencial());			// Monta o oid do relatorio gerencial
		ed.setCd_Relatorio_Gerencial_Grupo(this.getCd_Relatorio_Gerencial_Grupo()); // Monta o código do grupo
		ed = new Relatorio_Gerencial_GrupoRN().getByRecord(ed);  					// Retorna o registro no mesmo ed instanciado
		if (ed.getOid_Relatorio_Gerencial_Grupo()== 0){          					// Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Relatorio_Gerencial_Grupo() == this.getOid_Relatorio_Gerencial_Grupo()){ // Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
}       
