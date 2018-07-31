package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Menu_Perfil_SistemaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Perfil de menu do sistema
 * @serialData 05/2007
 */
public class Menu_Perfil_SistemaBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Menu_Perfil_SistemaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Menu_Perfil_SistemaED inclui(Menu_Perfil_SistemaED ed) throws Excecoes {

		try {
			ed.setOid_Menu_Perfil_Sistema(getAutoIncremento("oid_Menu_Perfil_Sistema", "Menus_Perfis_Sistemas"));
			sql = "INSERT INTO Menus_Perfis_Sistemas (" +
			      "oid_Menu_Perfil_Sistema," +
			      "oid_Menu_Perfil, " +
			      "oid_Menu_Sistema, " +
			      "dm_Acesso " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Menu_Perfil_Sistema() +
			      "," + ed.getOid_Menu_Perfil() + 
			      "," + ed.getOid_Menu_Sistema() +
			      ",'" + ed.getDm_Acesso() +
			      "')";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Menus_Perfis_Sistemas ed)");
		}
	}

	public void altera(Menu_Perfil_SistemaED ed) throws Excecoes {

		try {
			sql= "UPDATE Menus_Perfis_Sistemas SET " +
				 "dt_Stamp = '" + ed.getDt_stamp() + "' " +
				 ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				 ",dm_Stamp = '" + ed.getDm_Stamp() + "' " ;
			if (ed.getOid_Menu_Perfil()>0)
				sql +=",oid_Menu_Perfil = " + ed.getOid_Menu_Perfil() ;
			if ( ed.getOid_Menu_Sistema()>0 ) 
				sql += ",oid_Menu_Sistema = " + ed.getOid_Menu_Sistema() ;
			sql+= ",dm_Acesso = '" + ed.getDm_Acesso() + "' " +
				  "WHERE " +
				  "oid_Menu_Perfil_Sistema = " + ed.getOid_Menu_Perfil_Sistema();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Menu_Perfil_SistemaED ed)");
		}
	}

	public void deleta(Menu_Perfil_SistemaED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Menus_Perfis_Sistemas " +
				  "WHERE " +
				  "oid_Menu_Perfil_Sistema = " + ed.getOid_Menu_Perfil_Sistema();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Menu_Perfil_SistemaED ed)");
		}
	}

	public ArrayList lista(Menu_Perfil_SistemaED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
				  "FROM " + 
				  "Menus_Perfis_Sistemas, " +
				  "Menus_Sistemas " +
				  "WHERE " +
				  "Menus_Perfis_Sistemas.oid_menu_sistema = Menus_Sistemas.oid_menu_sistema ";
				  if (ed.getOid_Menu_Perfil()>0)
					  sql+="and Menus_Perfis_Sistemas.oid_Menu_Perfil = " + ed.getOid_Menu_Perfil() + " " ;
				  sql+="ORDER BY Menus_Sistemas.cd_Opcao";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res,ed.isIdentado()));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(Menus_Perfis_SistemasED ed)");
		}
	}

	public Menu_Perfil_SistemaED getByRecord(Menu_Perfil_SistemaED ed) throws Excecoes {

		Menu_Perfil_SistemaED edQBR = new Menu_Perfil_SistemaED();
		try {
			sql = "SELECT * " +
			  	  "FROM " + 
			  	  "Menus_Perfis_Sistemas, " +
			  	  "Menus_Sistemas " +
			  	  "WHERE " +
			  	  "Menus_Perfis_Sistemas.oid_menu_sistema = Menu_Sistemas.oid_menu_sistema " +  
				  "and Menus_Perfis_Sistemas.oid_Menu_Perfil_Sistema = " + ed.getOid_Menu_Perfil_Sistema() + " " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res,ed.isIdentado());
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Menu_Perfil_SistemaED ed)");
		}
		return edQBR;
	}

	private Menu_Perfil_SistemaED populaRegistro(ResultSet res, boolean identado) throws SQLException, Excecoes {
		String brancos = "               ";
		Menu_Perfil_SistemaED ed = new Menu_Perfil_SistemaED();
		ed.setOid_Menu_Perfil_Sistema(res.getInt("oid_Menu_Perfil_Sistema"));
		ed.setOid_Menu_Perfil(res.getInt("oid_Menu_Perfil"));
		ed.setOid_Menu_Sistema(res.getInt("oid_Menu_Sistema"));
		ed.setCd_Opcao(res.getString("cd_Opcao"));
		ed.setCd_Opcao_Pai(res.getString("cd_Opcao_Pai"));
		ed.setNr_Nivel(res.getInt("nr_Nivel"));
		if (identado==true) {
			ed.setNm_Opcao( (ed.getNr_Nivel() > 0 ? brancos.substring(0,(ed.getNr_Nivel()*3)): "") + res.getString("nm_Opcao"));
		} else {
			ed.setNm_Opcao(res.getString("nm_Opcao"));
		}	
		ed.setNm_Tela(res.getString("nm_Tela"));
		ed.setDm_Acesso(res.getString("dm_Acesso"));
		ed.setDm_Stamp(res.getString("dm_Stamp"));
		ed.setUsuario_Stamp(res.getString("usuario_Stamp"));
		ed.setDt_stamp(res.getString("dt_stamp"));
		return ed;
	}
	
}
