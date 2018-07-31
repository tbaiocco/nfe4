package com.master.ed;

import java.io.Serializable;
import com.master.rn.Empresa_HelpRN;
import com.master.util.Excecoes;


public class Empresa_HelpED extends MasterED implements Serializable {



	private static final long serialVersionUID = 2481636482323271690L;

	public Empresa_HelpED() {
        super();
    }
	
    private long oid_Empresa;
    private String nm_Empresa;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    private boolean fixo;

	
	public String getNm_Empresa() {
		return nm_Empresa;
	}
	public void setNm_Empresa(String nm_Empresa) {
		this.nm_Empresa = nm_Empresa;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	/**
	 * isFixo
	 * Verifica se o registro é de uso interno
	 * Retorna true se for
	 * @return boolean
	 */
	public boolean isFixo() {
		if (this.oid_Empresa < 100 ) {
			return true;
		}else{
			return false;
		}
	}
	public void setFixo(boolean fixo) {
		this.fixo = fixo;
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
			Empresa_HelpRN rn = new Empresa_HelpRN();
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
		Empresa_HelpED ed = new Empresa_HelpED();         // Instancia ed para a busca
		//ed.setCd_Tecnico(this.getCd_Tecnico()); 				// Busca o registro
		ed = new Empresa_HelpRN().getByRecord(ed);  			// Retorna o registro no mesmo ed
		if (ed.getOid_Empresa()== 0){          					// Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Empresa()== this.getOid_Empresa()){ 	// Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}


}