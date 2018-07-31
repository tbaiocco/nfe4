package com.master.ed;

import java.io.Serializable;
import com.master.rn.OrigemRN;
import com.master.util.Excecoes;

/**
 * @author André Valadas
 * @serial Origens
 * @serialData 14/10/2005
 */
public class OrigemED extends MasterED implements Serializable {

	private static final long serialVersionUID = 2659129313400346635L;

	public OrigemED() {
        super();
    }
	
    private long OID_Origem;
    private String CD_Origem;
    private String NM_Origem;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    private boolean fixo;
   
	/**
	 * @return Returns the cD_Origem.
	 */
	public String getCD_Origem() {
		return CD_Origem;
	}
	/**
	 * @return Returns the nM_Origem.
	 */
	public String getNM_Origem() {
		return NM_Origem;
	}
	/**
	 * @return Returns the oID_Origem.
	 */
	public long getOID_Origem() {
		return OID_Origem;
	}
	/**
	 * @param origem The cD_Origem to set.
	 */
	public void setCD_Origem(String origem) {
		CD_Origem = origem;
	}
	/**
	 * @param origem The nM_Origem to set.
	 */
	public void setNM_Origem(String origem) {
		NM_Origem = origem;
	}
	/**
	 * @param origem The oID_Origem to set.
	 */
	public void setOID_Origem(long origem) {
		OID_Origem = origem;
	}
	/**
	 * isFixo
	 * Verifica se o registro é de uso interno
	 * Retorna true se for
	 * @return boolean
	 */
	public boolean isFixo() {
		if (this.OID_Origem < 100 ) {
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
			OrigemRN rn = new OrigemRN();
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
		OrigemED ed = new OrigemED();         // Instancia ed para a busca
		ed.setCD_Origem(this.getCD_Origem()); // Busca o registro
		ed = new OrigemRN().getByRecord(ed);  // Retorna o registro no mesmo ed
		if (ed.getOID_Origem()== 0){          // Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOID_Origem()== this.getOID_Origem()){ // Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}

}