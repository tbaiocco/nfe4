package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.master.ed.IndexadorED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.FormataData;
import com.master.util.Data;

/**
 * @author Ralph
 * @serial Cadastro de Indexadores
 * @serialData 06/2006
 */
public class IndexadorBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;
	String dt_inicial = null;
	String dt_final = null;
	String dias = null;
	public IndexadorBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public IndexadorED inclui(IndexadorED ed) throws Excecoes {

		try {
			ed.setOid_Indexador(getAutoIncremento("oid_Indexador", "Indexadores"));
			sql = "INSERT INTO Indexadores (" +
			"oid_Empresa, " +
			"oid_Indexador," +
			"dt_Indexador, " +
			"vl_Indexador " +
			",dm_Stamp" +
		  	",dt_Stamp" +
       	    ",usuario_Stamp"+
       	    ",oid_usuario"+
	  	    ",time_millis"+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() +
			"," + ed.getOid_Indexador() +
			",'" + ed.getDt_Indexador() +
			"'," + ed.getVl_Indexador() +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		"," + ed.getUser() +
	  	 	"," + ed.getTime_millis() +
			")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(IndexadorED ed)");
		}
	}

	public void altera(IndexadorED ed) throws Excecoes {

		try {
			sql = "UPDATE Indexadores SET " +
			"dt_Indexador= '" + ed.getDt_Indexador() + "' " +
			",vl_Indexador= '" + ed.getVl_Indexador() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			",oid_usuario = " + ed.getUser() +
			",time_millis = " + ed.getTime_millis() +
			"WHERE " +
			"oid_Indexador = " + ed.getOid_Indexador();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(IndexadorED ed)");
		}
	}

	public void deleta(IndexadorED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Indexadores " +
			"WHERE " +
			"oid_Indexador = " + ed.getOid_Indexador();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(IndexadorED ed)");
		}
	}

	public ArrayList lista(IndexadorED ed) throws Excecoes {

		String ano, mes;
		ArrayList list = new ArrayList();
		try {
			ano = ed.getDt_Ano_Indexador();
			mes = ed.getDt_Mes_Indexador();
			// Se o ano e o mes não forem informados busca todo o conteudo da tabela
			if (!doValida(mes) && !doValida(ano) ){
				dt_inicial="01/01/1900";
				dt_final="31/12/3000";
			} else
				// Se somente o ano for informado busca odos os registros do ano
				if (!doValida(mes) && doValida(ano) ){
					dt_inicial = "01/01/" + ano ;
					dt_final="31/12/" + ano ;
				} else {
					// Somente o mes for informado, pega o ano corrente
					if (doValida(mes) && !doValida(ano) ){
						ed.setDt_Ano_Indexador(Data.getDataYMD().substring(0, 4));
					}
					// Testa se os meses tem dia 31
					if( "01".equals(mes) || "03".equals(mes) || "05".equals(mes) || "07".equals(mes) || "08".equals(mes) || "10".equals(mes) || "12".equals(mes)){
						dias="31";
					} else
						// Testa se os meses tem dia 30
						if ( "04".equals(mes) || "06".equals(mes) || "09".equals(mes) || "11".equals(mes) ){
							dias="30";
						} else
							// Testa se o ano é bissexto
							if(Integer.parseInt(ano) % 4 != 0){
								dias = "28"; // se o ano "NÃO" for bissexto
							}else {
								dias = "29"; // se o ano for bissexto
							}
					// Montas as variáveis "dt_inicial" e "dt_final" para a busca no bd
					dt_inicial = "01/" + mes + "/" + ano ;
					dt_final = dias + "/" + mes + "/" + ano;
				}

			sql = "SELECT * " +
			",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Indexadores " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " ";
			if (doValida(ed.getDt_Inicial()))
				sql += " and Indexadores.dt_Indexador >= '" + ed.getDt_Inicial() + "' " ;
			if (doValida(ed.getDt_Final()))
				sql += " and Indexadores.dt_Indexador <= '" + ed.getDt_Final() + "' " ;

			if (ed.getOid_Indexador()>0)
				sql += " and Indexadores.oid_Indexador = " + ed.getOid_Indexador() + " " ;
			if (doValida(ed.getDt_Indexador()))
				sql += " and Indexadores.dt_Indexador = '" + ed.getDt_Indexador() + "' " ;

			sql += "and Indexadores.dt_indexador between '" + dt_inicial+ "' and '" + dt_final + "'" ;

			sql += "ORDER BY Indexadores.dt_Indexador";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}

	}

	public IndexadorED getByRecord(IndexadorED ed) throws Excecoes {
		IndexadorED edQBR = new IndexadorED();
		try {
			sql = "SELECT * " +
			",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Indexadores " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() ;
			if (ed.getOid_Indexador()>0)
				sql += " and Indexadores.oid_Indexador = " + ed.getOid_Indexador() + " " ;
			if (doValida(ed.getDt_Indexador()))
				sql += " and Indexadores.dt_Indexador = '" + ed.getDt_Indexador() + "' " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(IndexadorED ed)");
		}
		return edQBR;
	}

	/**
	 * Busca o indexador mais proximo da data informada ....
	 * @param ed
	 * @return
	 * @throws Excecoes
	 */
	public IndexadorED getByData(IndexadorED ed) throws Excecoes {

		IndexadorED edQBR = new IndexadorED();
		try {
			sql = "SELECT * " +
			",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Indexadores " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() ;
			if (doValida(ed.getDt_Indexador()))
				sql += " and Indexadores.dt_Indexador <= '" + ed.getDt_Indexador() + "' " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			if (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(IndexadorED ed)");
		}
		return edQBR;
	}

	private IndexadorED populaRegistro(ResultSet res) throws SQLException {
		IndexadorED ed = new IndexadorED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Indexador(res.getInt("oid_Indexador"));
		ed.setVl_Indexador(res.getDouble("Vl_Indexador"));
		ed.setDt_Indexador(FormataData.formataDataBT(res.getString("Dt_Indexador")));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		//Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
		return ed;
	}
	/**
	 * Verifica se a empresa utiliza indexador. Se não utiliza, evita acessos desnecessários a esta tabela
	 * @param ed
	 * @return
	 * @throws Excecoes
	 */
	public boolean getUsaInx(IndexadorED ed) throws Excecoes {
		boolean usaInx = false;
		try {
			sql = "SELECT * " +
			"FROM " +
			"Indexadores " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() ;
			ResultSet res = this.executasql.executarConsulta(sql);
			if (res.next()) {
				usaInx=true;
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(IndexadorED ed)");
		}
		return usaInx;
	}

}
