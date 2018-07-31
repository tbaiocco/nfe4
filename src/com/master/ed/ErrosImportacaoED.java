package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis Steigleder
 * @serial Erros Importacao
 * @serialData Mar/2006
 */
public class ErrosImportacaoED extends MasterED implements Serializable {

	private static final long serialVersionUID = 1L;

	public ErrosImportacaoED() {
        super();
    }

	private String tx_Erro;
	
	public String getTx_Erro() {
		return tx_Erro;
	}
	public void setTx_Erro(String tx_Erro) {
		this.tx_Erro = tx_Erro;
	}
}