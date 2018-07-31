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
 * @author R�gis
 * @serial Perfil de menu
 * @serialData 05/2007
 */
public class Menu_PerfilBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Menu_PerfilBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Menu_PerfilED inclui(Menu_PerfilED ed) throws Excecoes {

		try {
			ed.setOid_Menu_Perfil(getAutoIncremento("oid_Menu_Perfil", "Menus_Perfis"));
			sql = "INSERT INTO Menus_Perfis (" +
				  "oid_Empresa, " +
			      "oid_menu_perfil," +
			      "oid_Sistema," +
			      "nm_menu_perfil " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Empresa() +
			      "," + ed.getOid_Menu_Perfil() +
			      "," + ed.getOid_Sistema() + 
			      ",'" + ed.getNm_Menu_Perfil() + 
			      "')";
			executasql.executarUpdate(sql);
			
			// Inclui para este perfil todas as op��es de menu do sistema
			// Precisa ler as op��es do sistema (menus_sistemas) e iterar sobre ele incluido em op�oes do perfil (menus_perfis_sistemas)  
			Menu_SistemaED msED = new Menu_SistemaED(); 						// Instancia um ed para os opcoes de menus do sistema
			msED.setOid_Sistema(ed.getOid_Sistema()); 							// Seta o oid do sistema no ed
			ArrayList lst = new Menu_SistemaBD(this.executasql).lista(msED); 	// Busca a lista de op�oes do menu do sistema
			for (int i=0 ; i<lst.size() ; i++ ){								// Itera sobre a lista de op��es de menu do sistema
				msED = (Menu_SistemaED)lst.get(i);								// Instancia um ed para op�es do perfil
				Menu_Perfil_SistemaED mpsED = new Menu_Perfil_SistemaED();      // Monta o ed com os dados das op��es de menu do sistema 
				mpsED.setOid_Menu_Perfil(ed.getOid_Menu_Perfil());				 
				mpsED.setOid_Menu_Sistema(msED.getOid_Menu_Sistema());			 
				mpsED.setDm_Acesso("true");
				new Menu_Perfil_SistemaBD(this.executasql).inclui(mpsED);		// Inclui a op��o de menu do sistema no perfil.
			}
			
			
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Menus_Perfis ed)");
		}
	}

	
	public void altera(Menu_PerfilED ed) throws Excecoes {

	}

	public void deleta(Menu_PerfilED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Menus_Perfis " +
				  "WHERE " +
				  "oid_Menu_Perfil = " + ed.getOid_Menu_Perfil();
			executasql.executarUpdate(sql);
			
			// Deletar todas as op��es de menu do perfil ( menus_perfis_sistemas )
			sql = "DELETE FROM Menus_Perfis_Sistemas " +
			  	  "WHERE " +
			      "oid_Menu_Perfil = " + ed.getOid_Menu_Perfil();
			executasql.executarUpdate(sql);
			
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Menu_PerfilED ed)");
		}
	}

	public ArrayList lista(Menu_PerfilED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  "FROM Menus_Perfis, Sistemas " +
				  "WHERE " +
				  "Menus_Perfis.oid_Sistema = Sistemas.oid_Sistema " ;
				  if (ed.getOid_Empresa()>0) 
					  sql+= "and Menus_Perfis.oid_Empresa = " + ed.getOid_Empresa() + " " ;
			  	  if (ed.getOid_Menu_Perfil()>0)
			  		  sql+="and Menus_Perfis.oid_Menu_Perfil = " + ed.getOid_Menu_Perfil() + " " ;
			  	  else if (ed.getOid_Sistema()>0)
					  sql+="and Menus_Perfis.oid_Sistema = " + ed.getOid_Sistema() + " " ;
				  sql+="ORDER BY Sistemas.Nm_Sistema, Menus_Perfis.nm_Menu_Perfil";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(Menus_PerfisED ed)");
		}
	}

	public Menu_PerfilED getByRecord(Menu_PerfilED ed) throws Excecoes {

		Menu_PerfilED edQBR = new Menu_PerfilED();
		try {
			sql = "SELECT * " +
				  "FROM Menus_Perfis, Sistemas " +
				  "WHERE " +
				  "Menus_Perfis.oid_Sistema = Sistemas.oid_Sistema "+
				  "and Menus_Perfis.oid_Empresa = " + ed.getOid_Empresa() + 
				  "and Menus_Perfis.oid_Menu_Perfil = " + ed.getOid_Menu_Perfil() + " " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Menu_PerfilED ed)");
		}
		return edQBR;
	}

	private Menu_PerfilED populaRegistro(ResultSet res) throws SQLException {
		Menu_PerfilED ed = new Menu_PerfilED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Menu_Perfil(res.getInt("oid_Menu_Perfil"));
		ed.setOid_Sistema(res.getInt("oid_Sistema"));
		ed.setNm_Menu_Perfil(res.getString("Nm_Menu_Perfil"));
		ed.setNm_Sistema(res.getString("Nm_Sistema"));
		ed.setDm_Stamp(res.getString("dm_Stamp"));
		ed.setUsuario_Stamp(res.getString("usuario_Stamp"));
		ed.setDt_stamp(res.getString("dt_stamp"));
		return ed;
	}
}
