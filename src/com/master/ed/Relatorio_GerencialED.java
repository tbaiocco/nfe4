package com.master.ed;

import java.io.Serializable;
import com.master.rn.Relatorio_GerencialRN;
import com.master.util.Excecoes;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 14/12/2005
 */
public class Relatorio_GerencialED extends MasterED implements Serializable {

	private static final long serialVersionUID = -1316350212018394756L;

	public Relatorio_GerencialED() {
        super();
    }
	
    private long oid_Relatorio_Gerencial;
    private String cd_Relatorio_Gerencial;
    private String nm_Relatorio_Gerencial;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;

	public String getCd_Relatorio_Gerencial() {
		return cd_Relatorio_Gerencial;
	}
	public String getNm_Relatorio_Gerencial() {
		return nm_Relatorio_Gerencial;
	}
	public long getOid_Relatorio_Gerencial() {
		return oid_Relatorio_Gerencial;
	}
	public void setCd_Relatorio_Gerencial(String cd_Relatorio_Gerencial) {
		this.cd_Relatorio_Gerencial = cd_Relatorio_Gerencial;
	}
	public void setNm_Relatorio_Gerencial(String nm_Relatorio_Gerencial) {
		this.nm_Relatorio_Gerencial = nm_Relatorio_Gerencial;
	}
	public void setOid_Relatorio_Gerencial(long oid_Relatorio_Gerencial) {
		this.oid_Relatorio_Gerencial = oid_Relatorio_Gerencial;
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
			Relatorio_GerencialRN rn = new Relatorio_GerencialRN();
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
		Relatorio_GerencialED ed = new Relatorio_GerencialED();         // Instancia ed para a busca
		ed.setCd_Relatorio_Gerencial(this.getCd_Relatorio_Gerencial()); // Busca o registro
		ed = new Relatorio_GerencialRN().getByRecord(ed);               // Retorna o registro no mesmo ed
		if (ed.getOid_Relatorio_Gerencial()== 0){                       // Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Relatorio_Gerencial()== this.getOid_Relatorio_Gerencial()){ // Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
}       
