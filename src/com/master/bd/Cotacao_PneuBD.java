package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Cotacao_PneuED;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.Modelo_PneuED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Cotação de pneus
 * @serialData 12/2007
 */
public class Cotacao_PneuBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Cotacao_PneuBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Cotacao_PneuED inclui(Cotacao_PneuED ed) throws Excecoes {
		try {
			ed.setOid_Cotacao_Pneu(getAutoIncremento("oid_Cotacao_Pneu", "Cotacoes_Pneus"));
			sql = "INSERT INTO Cotacoes_Pneus (" +
			"oid_Empresa, " +
			"oid_Cotacao_Pneu," +
			"oid_Modelo_Pneu," +
			"oid_Dimensao_Pneu," +
			"vl_Cotacao_Pneu," +
			"dt_Cotacao_Pneu " +
			",dm_Stamp" +
		  	",dt_Stamp" +
		  	",usuario_Stamp"+
			") VALUES (" +
			ed.getOid_Empresa() + 
			"," + ed.getOid_Cotacao_Pneu() +
			"," + ed.getOid_Modelo_Pneu() +
			"," + ed.getOid_Dimensao_Pneu() +
			"," + ed.getVl_Cotacao_Pneu()+
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" ;
			if (doValida(ed.getDt_Cotacao_Pneu()))  
				sql += ",'" + ed.getDt_Cotacao_Pneu() + "' " ;
			else
				sql += ",null " ;	
			sql +=  ")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Cotacao_PneuED ed)");
		}
	}

	public void altera(Cotacao_PneuED ed) throws Excecoes {
		try {
			sql = "UPDATE Cotacoes_Pneus SET " +
			"vl_Cotacao_Pneu = " + ed.getVl_Cotacao_Pneu() +
			",dt_Cotacao_Pneu = '" + ed.getDt_Cotacao_Pneu() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			"WHERE " +
			"oid_Cotacao_Pneu = " + ed.getOid_Cotacao_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Cotacao_PneuED ed)");
		}
	}

	public void deleta(Cotacao_PneuED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Cotacoes_Pneus " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Cotacao_Pneu = " + ed.getOid_Cotacao_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Cotacao_PneuED ed)");
		}
	}

	public ArrayList lista(Cotacao_PneuED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			" c.* " +
	  		" ,c.usuario_Stamp as usu_Stmp " +
	  		" ,c.dt_Stamp as dt_Stmp " +
	  		" ,c.dm_Stamp as dm_Stmp " +
			" ,m.nm_modelo_pneu " + 
			" ,d.nm_dimensao_pneu " +
			" ,f.nm_fabricante_pneu " +
			" FROM " +
			" cotacoes_pneus c " +
			" ,modelos_pneus m " +
			" ,dimensoes_pneus d " +
			" ,fabricantes_pneus f " +
			" WHERE " +
			" c.oid_empresa = " + ed.getOid_Empresa() + " " +
			" and c.oid_modelo_pneu = m.oid_modelo_pneu " +
			" and m.oid_fabricante_pneu = f.oid_fabricante_pneu " +
			" and c.oid_dimensao_pneu = d.oid_dimensao_pneu " ;
			if (doValida(ed.getDt_Cotacao_Pneu()))
				sql += " and C.dt_Cotacao_Pneu = '" + ed.getDt_Cotacao_Pneu() + "%' ";
			sql += " ORDER BY " +
			" d.nm_dimensao_pneu " +
			" ,m.nm_modelo_pneu " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Cotacao_PneuED getByRecord(Cotacao_PneuED ed) throws Excecoes {
		Cotacao_PneuED edQBR = new Cotacao_PneuED();
		try {
			sql = "SELECT " +
			"c.* " +
	  		",c.usuario_Stamp as usu_Stmp " +
	  		",c.dt_Stamp as dt_Stmp " +
	  		",c.dm_Stamp as dm_Stmp " +
			",m.nm_modelo_pneu " + 
			",d.nm_dimensao_pneu " +
			",f.nm_fabricante_pneu " +
			"FROM " +
			"cotacoes_pneus c " +
			",modelos_pneus m " +
			",dimensoes_pneus d " +
			",fabricantes_pneus f " +
			"WHERE " +
			"c.oid_modelo_pneu = m.oid_modelo_pneu " +
			"and m.oid_fabricante_pneu = f.oid_fabricante_pneu " +
			"and c.oid_dimensao_pneu = d.oid_dimensao_pneu " ;
			if (ed.getOid_Cotacao_Pneu()>0)
				sql+=" and c.oid_Cotacao_Pneu = " + ed.getOid_Cotacao_Pneu() + " ";
			else {
				sql+=" and c.oid_Empresa = " + ed.getOid_Empresa() + " ";
				if (ed.getOid_Dimensao_Pneu()>0)
					sql+=" and c.oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu() + " ";
				if (ed.getOid_Modelo_Pneu()>0)
					sql+=" and  c.oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu() + " ";
			}
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Cotacao_PneuED ed)");
		}
		return edQBR;
	}
	
	private Cotacao_PneuED populaRegistro(ResultSet res) throws SQLException {
		Cotacao_PneuED ed = new Cotacao_PneuED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Cotacao_Pneu(res.getInt("oid_Cotacao_Pneu"));
		ed.setOid_Dimensao_Pneu(res.getInt("oid_Dimensao_Pneu"));
		ed.setOid_Modelo_Pneu(res.getInt("oid_Modelo_Pneu"));
		ed.setDt_Cotacao_Pneu(FormataData.formataDataBT(res.getString("dt_Cotacao_Pneu")));
		ed.setVl_Cotacao_Pneu(res.getDouble("vl_Cotacao_Pneu"));
		ed.setNm_Dimensao_Pneu(res.getString("nm_Dimensao_Pneu"));
		ed.setNm_Modelo_Pneu(res.getString("nm_Modelo_Pneu"));
		ed.setNm_Fabricante_Pneu(res.getString("nm_Fabricante_Pneu"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
	
	/**
	 * Busca no cadastro de pneus os pneus sucateados e agrupa por dimensao e modelo
	 * Acessa as cotações para verificar se já existe um registro para esta dimensao/modelo
	 * se não houver cria.
	 * Após busca a lista da cotação e devolve um array com os registros
	 * @param ed
	 * @return
	 * @throws Excecoes
	 */
	public ArrayList preparaListaCotacao(Cotacao_PneuED ed) throws Excecoes {
		Cotacao_PneuED cotEDVolta = new Cotacao_PneuED();
		try {
			sql = "SELECT " +
			"distinct " +
			"oid_dimensao_pneu, " +
			"oid_modelo_pneu " +
			"FROM " +
			"pneus " +
			"WHERE " +
			"oid_empresa = " + ed.getOid_Empresa() + " " +
			"and dt_sucateamento > 0 " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Cotacao_PneuED cotED = new Cotacao_PneuED();
				cotED.setOid_Empresa(ed.getOid_Empresa());
				cotED.setOid_Dimensao_Pneu(res.getInt("oid_Dimensao_Pneu"));
				cotED.setOid_Modelo_Pneu(res.getInt("oid_Modelo_Pneu"));
				cotEDVolta = this.getByRecord(cotED);
				if (cotEDVolta.getOid_Cotacao_Pneu()==0) {
					this.inclui(cotED);
				}
			}
			// Pega a nova lista implementado com os possiveis novos registros colocados acima
			return this.lista(ed);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public ArrayList listaCustoReposicao(Cotacao_PneuED ed,String tipo) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " ;
			if ("rel".equals(tipo)) {
				sql+="d.nm_dimensao_pneu, " +
				"t.nm_tipo_pneu, "; 
			}
			sql+="f.nm_fabricante_pneu, " +
			"m.nm_Modelo_Pneu, " +
			"count(p.oid_pneu) as nr_Quantidade, " +
			"sum(p.nr_km_acumulada) as nr_Km_Acumulada, " +
			"max(c.vl_cotacao_pneu) as vl_cotacao_pneu, " +
			"max(c.dt_cotacao_pneu) as dt_cotacao_pneu  " +
			"FROM " +
			"pneus p " +
			",cotacoes_pneus c " +
			",modelos_pneus m " +
			",dimensoes_pneus d " +
			",fabricantes_pneus f " +
			",tipos_pneus t " +
			"WHERE " +
			"p.oid_empresa = " + ed.getOid_Empresa() + " and " +
			"p.nr_km_acumulada > 0 and " +
			"p.dt_sucateamento is not null and " +
			"( c.oid_empresa = p.oid_empresa and c.oid_modelo_pneu = p.oid_modelo_pneu and c.oid_dimensao_pneu = p.oid_dimensao_pneu ) and " +
			"t.oid_tipo_pneu = p.oid_tipo_pneu and " +
			"d.oid_dimensao_pneu = p.oid_dimensao_pneu and " +
			"f.oid_fabricante_pneu = p.oid_fabricante_pneu and " +
			"m.oid_Modelo_Pneu = p.oid_Modelo_Pneu " ;
			
	    	if (ed.getOid_Modelo_Pneu()>0 ) {
	    		sql+="and p.oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu() + " ";
	    	}	
	    	if (ed.getOid_Dimensao_Pneu()>0 ) { 
	    		sql+="and p.oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu() + " ";
	    	}	
	    	if (ed.getOid_Fabricante_Pneu()>0 ) { 
	    		sql+="and p.oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() + " ";
	    	}	
			
			sql+="group by ";
			if ("rel".equals(tipo)) {
				sql+="d.nm_dimensao_pneu, " +
				"t.nm_tipo_pneu, "; 
			}
			sql+="f.nm_fabricante_pneu, " +
			"m.nm_Modelo_Pneu " ;
			if ("gra".equals(tipo)) {
				sql+="order by (max(c.vl_cotacao_pneu) / sum(p.nr_km_acumulada) * 1000 ) desc " ;
			}
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Cotacao_PneuED pneuED = new Cotacao_PneuED();
				pneuED.setDt_Cotacao_Pneu(FormataData.formataDataBT(res.getString("dt_Cotacao_Pneu")));
				pneuED.setVl_Cotacao_Pneu(res.getDouble("vl_Cotacao_Pneu"));
				pneuED.setNr_Quantidade(res.getLong("nr_Quantidade"));
				pneuED.setNr_Km_Acumulada(res.getDouble("nr_Km_Acumulada"));
				pneuED.setNm_Modelo_Pneu(res.getString("nm_Modelo_Pneu"));
				pneuED.setNm_Fabricante_Pneu(res.getString("nm_Fabricante_Pneu"));
				if ("rel".equals(tipo)) {
					pneuED.setNm_Dimensao_Pneu(res.getString("nm_Dimensao_Pneu"));
					pneuED.setNm_Tipo_Pneu(res.getString("nm_Tipo_Pneu"));
				}
				list.add(pneuED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Cotacao_PneuED ed)");
		}
	}
	
}
