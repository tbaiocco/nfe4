package com.master.ed;

import java.io.Serializable;

import com.master.rn.DreRN;
import com.master.util.Excecoes;

/**
 * @author Régis Steigleder
 * @serial Demonstrativo de Resultados
 * @serialData 02/2006
 */
public class DreED extends MasterED implements Serializable {

    public DreED() {
        super();
    }
  
    private long oid_Dre;
    private String cd_Dre;
    private String nm_Dre;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    
	public String getCd_Dre() {
		return cd_Dre;
	}
	public void setCd_Dre(String cd_Dre) {
		this.cd_Dre = cd_Dre;
	}
	public String getNm_Dre() {
		return nm_Dre;
	}
	public void setNm_Dre(String nm_Dre) {
		this.nm_Dre = nm_Dre;
	}
	public long getOid_Dre() {
		return oid_Dre;
	}
	public void setOid_Dre(long oid_Dre) {
		this.oid_Dre = oid_Dre;
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
			DreRN rn = new DreRN();
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
		DreED ed = new DreED();         				// Instancia ed para a busca
		ed.setCd_Dre(this.getCd_Dre()); 				// Busca o registro
		ed = new DreRN().getByRecord(ed);               // Retorna o registro no mesmo ed
		if (ed.getOid_Dre()== 0){                       // Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Dre()== this.getOid_Dre()){ 	// Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}

}