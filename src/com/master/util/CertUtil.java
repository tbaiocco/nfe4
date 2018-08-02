package com.master.util;

import org.jasypt.util.text.BasicTextEncryptor;

import br.cte.model.Empresa;

public class CertUtil {

	private static String chaveMestra = "liquid***###+++";
	
	public static String getSenhaPlain(Empresa empresa) {
		String pass = "";
        try {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword("A" + chaveMestra);
            pass = textEncryptor.decrypt(empresa.getSenha());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return pass;
	}
}
