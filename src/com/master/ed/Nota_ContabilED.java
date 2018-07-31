
package com.master.ed;

import com.master.rn.Nota_ContabilRN;
import com.master.util.Excecoes;

/**
 * @author Vinícius
	Date: 09/10/2008
 */

public class Nota_ContabilED extends RelatorioBaseED{

	  private long oid_Nota_Contabil;
	  private String dt_Inicial;
	  private String dt_Final;
	  private long nr_Nota;
	  private String tx_Nota;
	  private String tx_Assunto;
	  
	  
	  //======  Fucoes   =======\\
	  
	  private boolean codigoExistente;
	  private boolean incluidoOK;
      
	  
	  
	public String getDt_Final() {
		return dt_Final;
	}
	public void setDt_Final(String dt_Final) {
		this.dt_Final = dt_Final;
	}
	public String getDt_Inicial() {
		return dt_Inicial;
	}
	public void setDt_Inicial(String dt_Inicial) {
		this.dt_Inicial = dt_Inicial;
	}
	public long getNr_Nota() {
		return nr_Nota;
	}
	public void setNr_Nota(long nr_Nota) {
		this.nr_Nota = nr_Nota;
	}
	public long getOid_Nota_Contabil() {
		return oid_Nota_Contabil;
	}
	public void setOid_Nota_Contabil(long oid_Nota_Contabil) {
		this.oid_Nota_Contabil = oid_Nota_Contabil;
	}
	public String getTx_Nota() {
		return tx_Nota;
	}
	public void setTx_Nota(String tx_Nota) {
		this.tx_Nota = tx_Nota;
	}
	
	public boolean isIncluidoOK() throws Excecoes {
		if (this.isCodigoExistente() == false){
			Nota_ContabilRN rn = new Nota_ContabilRN();
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
		
			Nota_ContabilED ed = new Nota_ContabilED();						        // Instancia ed para a busca
			ed.setNr_Nota(this.getNr_Nota()); 					                    // Busca o registro
			if(nr_Nota !=0){
			ed = new Nota_ContabilRN().getByRecord(ed);                             // Retorna o registro no mesmo ed
			if (ed.getNr_Nota() == 0){                                              // Se registro não existir retorna false
				dmVolta = false;
			}else{
				if (ed.oid_Nota_Contabil == this.getOid_Nota_Contabil()){           // Se registro é o mesmo oid retorna false	
					dmVolta =  false;
				}	
			}
			return dmVolta;
		}
		dmVolta = false;
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
	public String getTx_Assunto() {
		return tx_Assunto;
	}
	public void setTx_Assunto(String tx_Assunto) {
		this.tx_Assunto = tx_Assunto;
	}
	
	  
}
