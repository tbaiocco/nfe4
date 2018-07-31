/*
 * Created on 01/02/2005
 *
 */
package com.master.bd;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.ed.ReferenciaED;
import com.master.iu.cad024Bean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class ImportaProdutosRoflan extends BancoUtil {
	private static final long oid_ProdutoPadrao = 5;
	/*<option value="1">Outros</option>
    <option value="2">Trator</option>
    <option value="3">Retro</option>
    <option value="4">Colheitadeira</option>
    <option value="5">Peças</option>
    <option value="6">Embalagens</option>*/
	private static final String DM_Tipo_Produto_Padrao = "5";
	public static void main(String[] args) {
		boolean ocorreuErro = false;
		// System.out.println("..| Programa de Importação de Referências |..");
		if (args.length > 0) {
			File file = new File(args[0]);
			if (file.exists()) {
				try {
					FileReader fileReader = new FileReader(file);
					int character = 0;
					int reads = 0;
					String codigo = "";
					String descricao = "";
					int coluna = 1;
					List lista = new ArrayList();
					while ((character = fileReader.read()) > 0) {
						reads++;
						char letra = (char)character;
						if (character == 9) { //Backspace
							coluna = 2;
						} else if (character == 13) {
							coluna = 1;
							lista.add(new ReferenciaED(oid_ProdutoPadrao, codigo, descricao, DM_Tipo_Produto_Padrao));
							codigo = "";
							descricao = "";
						} else if (character != 10) {
							if (coluna == 1) {
								codigo += String.valueOf(letra);
							} else {
								descricao += String.valueOf(letra);
							}
						}
					}
					Iterator iterator = lista.iterator();
					cad024Bean bean = new cad024Bean();
					while (iterator.hasNext()) {
						ReferenciaED ed = (ReferenciaED)iterator.next();
						try {
							if (!bean.doExiste(ed)) {
								bean.inclui(ed);
							} else {
								// System.err.println("ERRO: Referência duplicada: Código <" + ed.getCD_Referencia() + "> / Descrição <" + ed.getNM_Referencia() + ">");
								ocorreuErro = true;
							}
						} catch (Excecoes e) {
							// System.err.println("ERRO: Erro ao incluir Código <" + ed.getCD_Referencia() + "> / Descrição <" + ed.getNM_Referencia() + "> / Erro <" + e.getMessage() + ">");
							e.printStackTrace();
							ocorreuErro = true;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
			} else {
				// System.err.println("Arquivo [" + file.getPath() + "] não existe!");
				System.exit(1);
			}
			if (!ocorreuErro) {
				// System.out.println("Importação concluída com sucesso!");
			} else {
				// System.out.println("Importação concluída com erros! Confira o log acima...");
			}
			
			System.exit(0);
		}
	}
}
