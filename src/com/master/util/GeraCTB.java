package com.master.util;

import com.master.rn.Importacao_ContabilRN;

public class GeraCTB {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Excecoes{
		// TODO Auto-generated method stub
		try {
	    	System.out.println("passo 1 main() ");
	    	Importacao_ContabilRN ictbRN = new Importacao_ContabilRN();
	    	ictbRN.gera_Contabilizacao_Total();
	        System.out.println("pronto!");
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse ("Importacao_ContabilBD");
	      exc.printStackTrace ();
	      excecoes.setMetodo ("Lancamento_ContabilBD - Construtor");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	}

}
