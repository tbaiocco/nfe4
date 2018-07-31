package com.master.ed;

import java.io.Serializable;
import com.master.rn.Tecnico_SuporteRN;
import com.master.util.Excecoes;



/**
 * @author Jeanine e Vinícius
 * @serial Técnico_Suporte
 * @serialData 21/05/2008
 */

public class Tecnico_SuporteED extends MasterED implements Serializable {



	private static final long serialVersionUID = 2481636482323271690L;

	public Tecnico_SuporteED() {
        super();
    }
	
    private long oid_Tecnico;
    private String cd_Tecnico;
    private String nm_Tecnico;
    private String dm_Tecnico;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    private boolean fixo;

	public String getCd_Tecnico() {
		return cd_Tecnico;
	}
	public void setCd_Tecnico(String cd_Tecnico) {
		this.cd_Tecnico = cd_Tecnico;
	}
	public String getDm_Tecnico() {
		return dm_Tecnico;
	}
	public void setDm_Tecnico(String dm_Tecnico) {
		this.dm_Tecnico = dm_Tecnico;
	}
	public String getNm_Tecnico() {
		return nm_Tecnico;
	}
	public void setNm_Tecnico(String nm_Tecnico) {
		this.nm_Tecnico = nm_Tecnico;
	}
	public long getOid_Tecnico() {
		return oid_Tecnico;
	}
	public void setOid_Tecnico(long oid_Tecnico) {
		this.oid_Tecnico = oid_Tecnico;
	}
	/**
	 * isFixo
	 * Verifica se o registro é de uso interno
	 * Retorna true se for
	 * @return boolean
	 */
	public boolean isFixo() {
		if (this.oid_Tecnico < 100 ) {
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
			Tecnico_SuporteRN rn = new Tecnico_SuporteRN();
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
		Tecnico_SuporteED ed = new Tecnico_SuporteED();         // Instancia ed para a busca
		ed.setCd_Tecnico(this.getCd_Tecnico()); 				// Busca o registro
		ed = new Tecnico_SuporteRN().getByRecord(ed);  			// Retorna o registro no mesmo ed
		if (ed.getOid_Tecnico()== 0){          					// Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Tecnico()== this.getOid_Tecnico()){ 	// Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}


}