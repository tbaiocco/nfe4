package com.master.ed;

import java.io.Serializable;

import com.master.rn.Dre_Item_FormacaoRN;
import com.master.util.Excecoes;

/**
 * @author Régis Steigleder	
 * @serial Formações dos Itens do DRE
 * @serialData 02/2006
 */
public class Dre_Item_FormacaoED extends MasterED implements Serializable {

    public Dre_Item_FormacaoED() {
        super();
    }
    
    private long oid_Dre_Item_Formacao;
    private long oid_Dre_Item;
    private long oid_Conta;
    // Dados da conta Conta
    private String cd_Conta;
    private String cd_Estrutural;
    private String nm_Conta;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;
    
	public long getOid_Conta() {
		return oid_Conta;
	}
	public void setOid_Conta(long oid_Conta) {
		this.oid_Conta = oid_Conta;
	}
	public long getOid_Dre_Item() {
		return oid_Dre_Item;
	}
	public void setOid_Dre_Item(long oid_Dre_Item) {
		this.oid_Dre_Item = oid_Dre_Item;
	}
	public long getOid_Dre_Item_Formacao() {
		return oid_Dre_Item_Formacao;
	}
	public String getCd_Conta() {
		return cd_Conta;
	}
	public void setCd_Conta(String cd_Conta) {
		this.cd_Conta = cd_Conta;
	}
	public String getCd_Estrutural() {
		return cd_Estrutural;
	}
	public void setCd_Estrutural(String cd_Estrutural) {
		this.cd_Estrutural = cd_Estrutural;
	}
	public void setOid_Dre_Item_Formacao(long oid_Dre_Item_Formacao) {
		this.oid_Dre_Item_Formacao = oid_Dre_Item_Formacao;
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
			Dre_Item_FormacaoRN rn = new Dre_Item_FormacaoRN();
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
		Dre_Item_FormacaoED ed = new Dre_Item_FormacaoED();         // Instancia ed para a busca
		ed.setOid_Dre_Item(this.getOid_Dre_Item()); 				// Busca o registro
		ed.setOid_Conta(this.getOid_Conta()); 						// Busca o registro
		ed = new Dre_Item_FormacaoRN().getByRecord(ed);             // Retorna o registro no mesmo ed
		if (ed.getOid_Dre_Item_Formacao()== 0){                     // Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Dre_Item_Formacao()== this.getOid_Dre_Item_Formacao()){ // Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
	public String getNm_Conta() {
		return nm_Conta;
	}
	public void setNm_Conta(String nm_Conta) {
		this.nm_Conta = nm_Conta;
	}
}