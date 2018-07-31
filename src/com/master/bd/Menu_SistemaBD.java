package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Menu_PerfilED;
import com.master.ed.Menu_Perfil_SistemaED;
import com.master.ed.Menu_SistemaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Menus de sistemas
 * @serialData 05/2007
 */
public class Menu_SistemaBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Menu_SistemaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Menu_SistemaED inclui(Menu_SistemaED ed) throws Excecoes {

		try {
			ed.setOid_Menu_Sistema(getAutoIncremento("oid_Menu_Sistema", "Menus_Sistemas"));
			ed.setNr_Nivel(this.getNivel(ed));
			sql = "INSERT INTO Menus_Sistemas (" +
			      "oid_Menu_Sistema," +
			      "oid_Sistema,"+
			      "cd_Opcao,"+
			      "nm_Opcao," +
			      "nm_tela," +
			      "cd_Opcao_Pai,"+
			      "nr_Nivel " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Menu_Sistema() + 
			      "," + ed.getOid_Sistema() +
			      ",'" + ed.getCd_Opcao() +
			      "','" + ed.getNm_Opcao() +
			      "','" + (doValida(ed.getNm_Tela()) ? ed.getNm_Tela(): "") +
			      "','" + (doValida(ed.getCd_Opcao_Pai()) ? ed.getCd_Opcao_Pai(): "") +
			      "'," + ed.getNr_Nivel() +
			      ")";
			executasql.executarUpdate(sql);
			
			// Incluir em	 todos os perfis ( menus_perfis_sistemas ) 
			Menu_PerfilED mpED = new Menu_PerfilED();						// Instancia ed para os perfis
			mpED.setOid_Sistema(ed.getOid_Sistema());						// Seta o sistema para buscar perfis
			ArrayList lst = new Menu_PerfilBD(this.executasql).lista(mpED); // Busca todos os perfis para o sistema
			for (int i=0; i<lst.size(); i++){								// Itera sobre os registros retornados
				mpED = (Menu_PerfilED) lst.get(i);							
				Menu_Perfil_SistemaED mpsED = new Menu_Perfil_SistemaED();	// Monta ed com os dados do perfil do sistema
				mpsED.setOid_Menu_Perfil(mpED.getOid_Menu_Perfil());		// 
				mpsED.setOid_Menu_Sistema(ed.getOid_Menu_Sistema());
				mpsED.setDm_Acesso("false");
				new Menu_Perfil_SistemaBD(this.executasql).inclui(mpsED);	// Inclui a opção de menu do sistema no perfil.
			}

			return this.getByRecord(ed);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Menu_SistemaED ed)");
		}
	}

	public void altera(Menu_SistemaED ed) throws Excecoes {

		try {
			ed.setNr_Nivel(this.getNivel(ed));
			sql = "UPDATE Menus_Sistemas SET " +
				  "cd_Opcao = '" + ed.getCd_Opcao() + "' " +
				  ",cd_Opcao_Pai = '" + (doValida(ed.getCd_Opcao_Pai()) ? ed.getCd_Opcao_Pai(): "") + "' " +
				  ",nm_Opcao = '" + ed.getNm_Opcao() + "' " +
				  ",nm_Tela = '" + (doValida(ed.getNm_Tela()) ? ed.getNm_Tela(): "") + "' " +
				  ",nr_Nivel = " + ed.getNr_Nivel() + " " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  "WHERE " +
				  "oid_Menu_Sistema = " + ed.getOid_Menu_Sistema();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Menu_SistemaED ed)");
		}
	}

	public void deleta(Menu_SistemaED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Menus_Sistemas " +
				  "WHERE " +
				  "oid_Menu_Sistema = " + ed.getOid_Menu_Sistema();
			executasql.executarUpdate(sql);

			// Apaga o registro de todos os perfis ( menus_perfis_sistemas )
			sql = "DELETE FROM menus_perfis_sistemas " +
			  	  "WHERE " +
			  	  "oid_Menu_Sistema = " + ed.getOid_Menu_Sistema();
		executasql.executarUpdate(sql);

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Menu_SistemaED ed)");
		}
	}

	public ArrayList lista(Menu_SistemaED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
				  "Menus_Sistemas.*, "  +
				  "Sistemas.nm_Sistema " +
				  "FROM " +
				  "Menus_Sistemas, " +
				  "Sistemas " +
				  "WHERE " +
				  "Menus_Sistemas.oid_sistema = Sistemas.oid_sistema " ;
			if (ed.getOid_Sistema()>0)
				sql += " and Menus_Sistemas.oid_Sistema = " + ed.getOid_Sistema() + " ";
			sql += "ORDER BY Menus_Sistemas.cd_Opcao";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public ArrayList listaFilhos(Menu_SistemaED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
				  "Menus_Sistemas.*, "  +
				  "Sistemas.nm_Sistema " +
				  "FROM " +
				  "Menus_Sistemas, " +
				  "Sistemas " +
				  "WHERE " +
				  "Menus_Sistemas.oid_sistema = Sistemas.oid_sistema " ;
			if (doValida(ed.getCd_Opcao())) {
				sql += "and cd_opcao_pai = '" + ed.getCd_Opcao() + "'  ";
			} else {
				sql += "and cd_opcao_pai isnull or cd_opcao_pai = ''  ";
			}	  
			sql += "ORDER BY Menus_Sistemas.cd_Opcao";

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Menu_SistemaED getByRecord(Menu_SistemaED ed) throws Excecoes {

		Menu_SistemaED edQBR = new Menu_SistemaED();
		try {
			sql = "SELECT " +
			  	  "Menus_Sistemas.*, "  +
			  	  "Sistemas.nm_Sistema " +
			  	  "FROM " +
			  	  "Menus_Sistemas, " +
			  	  "Sistemas " +
				  "WHERE " +
				  "Menus_Sistemas.oid_sistema = Sistemas.oid_sistema ";
				  if (ed.getOid_Menu_Sistema()>0) {
					  sql+=" and Menus_Sistemas.oid_Menu_Sistema = " + ed.getOid_Menu_Sistema() ;
				  } else{
					  if (doValida(ed.getCd_Opcao())) {
						  sql+=" and Menus_Sistemas.cd_Opcao = '" + ed.getCd_Opcao()+ "' " ;
					  }
					  if (ed.getOid_Sistema()>0) {
						  sql+=" and Menus_Sistemas.oid_sistema = '" + ed.getOid_Sistema() + "' " ;
					  }
				  }
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Menu_SistemaED ed)");
		}
		return edQBR;
	}
	
	private Menu_SistemaED populaRegistro(ResultSet res) throws SQLException {
		Menu_SistemaED ed = new Menu_SistemaED();
		ed.setOid_Sistema(res.getInt("oid_Sistema"));
		ed.setOid_Menu_Sistema(res.getInt("oid_Menu_Sistema"));
		ed.setCd_Opcao(res.getString("cd_Opcao"));
		ed.setCd_Opcao_Pai(res.getString("cd_Opcao_Pai"));
		ed.setNm_Opcao(res.getString("nm_Opcao"));
		ed.setNm_Tela(res.getString("nm_Tela"));
		ed.setNm_Sistema(res.getString("nm_Sistema"));
		ed.setNr_Nivel(res.getInt("nr_Nivel"));
		return ed;
	}

	/**
	 * Fornece o nível de identação do menu 
	 * @param cd - Código da opção para procurar o nível
	 * @return
	 * @throws Excecoes
	 */
	public int getNivel(Menu_SistemaED ed) throws Excecoes {
		Menu_SistemaED msED = new Menu_SistemaED();
		int nivel=1;
		
		if (!doValida(ed.getCd_Opcao_Pai()))  // Se não tem pai retorna 0 (zero) no nível
			return 0;

		msED.setCd_Opcao(ed.getCd_Opcao_Pai());
		msED.setOid_Sistema(ed.getOid_Sistema());
		try {
			while (msED.getCd_Opcao().length()>0) {
				sql = "SELECT * " +
				  	  "FROM " + 
				  	  "Menus_Sistemas " +
				  	  "WHERE " +
				  	  "oid_Sistema = " + msED.getOid_Sistema() + " "+
					  "and cd_opcao = '" + msED.getCd_Opcao() + "' " ;
				ResultSet res = this.executasql.executarConsulta(sql);
				res.next();
				msED.setCd_Opcao(res.getString("cd_Opcao_Pai")) ;
				if (msED.getCd_Opcao().length()> 0) nivel++;
			};
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getNivel(Menu_SistemaED ed)");
		}
		return nivel;
	}

}
