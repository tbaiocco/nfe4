package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.ConsertoED;
import com.master.ed.PneuED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Cristian Vianna Garcia
 * @serial Consertos/Resulcagens
 * @serialData 08/2007
 */
public class ConsertoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public ConsertoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public ConsertoED inclui(ConsertoED ed) throws Excecoes {
		try {
			ed.setOid_Conserto(getAutoIncremento("oid_Conserto_Pneu", "Consertos_Pneus"));
			sql = "INSERT INTO Consertos_Pneus (" +
			"oid_Empresa, " +
			"oid_Conserto_Pneu," +
			"oid_Pneu," +
			"oid_Veiculo," +
			"dt_Conserto, " +
			"oid_Fornecedor," +
			"vl_Conserto, " +
			"vl_prof_sulco_Resulcagem, " +
			"tx_Descricao," +
			"nr_Vida," +
			"tx_Documento " +
			",dm_Stamp" +
		  	",dt_Stamp" +
		  	",usuario_Stamp"+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() + 
			"," + ed.getOid_Conserto() + 
			"," + ed.getOid_Pneu_Conserto() + 
			",'" + ed.getOid_Veiculo() + 
			"','" + ed.getDt_Conserto() +
			"'," + ed.getOid_Fornecedor_Conserto() +
			"," + ed.getVl_Conserto() +
			"," + ed.getVl_Prof_Sulco_Resulcagem() +
			",'" + ed.getTx_Descricao_Conserto()+
			"'," + ed.getDm_Vida_Conserto()+
			",'" + ed.getNr_Documento_Conserto()+
			"','I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(ConsertoED ed)");
		}
	}


	public void altera(ConsertoED ed) throws Excecoes {
		try {
			sql = "UPDATE Consertos_Pneus SET " +
			"dt_Conserto = '" + ed.getDt_Conserto() + "', " +
			"dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " ;
			if (ed.getOid_Pneu_Conserto() > 0) {
				sql += ",oid_Pneu = " + ed.getOid_Pneu_Conserto() + ", " ;
			}
			sql += ",oid_Veiculo = '" + ed.getOid_Veiculo() + "', " ;
			sql += "oid_fornecedor = " + ed.getOid_Fornecedor_Conserto() + ", " +
			"vl_Conserto = " + ed.getVl_Conserto() + ", " +
			"vl_Prof_Sulco_Resulcagem = " + ed.getVl_Prof_Sulco_Resulcagem() + ", " +
			"tx_Descricao = '" + ed.getTx_Descricao_Conserto()+ "', " +
			"nr_Vida = " + ed.getDm_Vida_Conserto()+ ", " +
			"tx_Documento = '" + ed.getNr_Documento_Conserto()+ "' " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Conserto_Pneu = " + ed.getOid_Conserto(); 
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(ConsertoED ed)");
		}
	}

	public void deleta(ConsertoED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Consertos_Pneus " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Conserto_Pneu = " + ed.getOid_Conserto();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(ConsertoED ed)");
		}
	}

	public ArrayList lista(ConsertoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"* " +
	  		",Consertos_Pneus.usuario_Stamp as usu_Stmp " +
	  		",Consertos_Pneus.dt_Stamp as dt_Stmp " +
	  		",Consertos_Pneus.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Consertos_Pneus left join Veiculos " +
				"on veiculos.oid_veiculo = consertos_pneus.oid_veiculo," +
			"Pneus, " +
			"Fornecedores " +
			"WHERE " +
			" consertos_Pneus.oid_Empresa = " + ed.getOid_Empresa() +
			"  and consertos_Pneus.oid_Pneu = pneus.oid_pneu " +
			"  and consertos_Pneus.oid_Fornecedor = Fornecedores.oid_Fornecedor " ;
			if (ed.getDm_Vida_Conserto()!=10)
				sql += " and Consertos_Pneus.nr_vida = " + ed.getDm_Vida_Conserto() + " ";
			if (doValida(ed.getDt_Inicial_Conserto())) {
			    sql += " and Consertos_Pneus.dt_Conserto >= '" + ed.getDt_Inicial_Conserto()+ "' ";
			}
			if (doValida(ed.getDt_Final_Conserto())) {
				sql += " and Consertos_Pneus.dt_Conserto <= '" + ed.getDt_Final_Conserto()+ "' ";
			}
			if (doValida(ed.getNr_Fogo_Conserto())) { 
				sql += " and pneus.nr_Fogo = " + ed.getNr_Fogo_Conserto() + " ";
				sql += " and pneus.oid_pneu = consertos_pneus.oid_pneu " ;
			}
			if ((ed.getOid_Pneu_Conserto() > 0)) { 
				sql += " and consertos_pneus.oid_Pneu = " + ed.getOid_Pneu_Conserto() + " ";
			}
			if ("1".equals(ed.getDm_Opcao())){
				if (doValida(ed.getNr_Frota_Conserto())){
					sql += " and veiculos.nr_Frota = " + ed.getNr_Frota_Conserto() + " ";
					sql += " and veiculos.oid_veiculo = consertos_pneus.oid_veiculo " ;
				}
				//sql += " and consertos_Pneus.oid_veiculo = veiculos.oid_veiculo " ;
				sql += " and vl_prof_sulco_resulcagem = 0  " ;	
			}
			if ("2".equals(ed.getDm_Opcao())){
				sql += " and vl_prof_sulco_resulcagem > 0  " ;	
			}
			sql += " ORDER BY " +
			"pneus.nr_fogo";
			ResultSet res = this.executasql.executarConsulta(sql);	
			while (res.next()) {
				list.add(populaRegistro(res));
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
		return list;
	}

	  public ArrayList listaConsertosPorVida (ConsertoED ed) throws Excecoes {
		  ArrayList list = new ArrayList ();
		  try {
			  sql = "SELECT ";
			  if (doValida(ed.getDm_Fabricante_Pneu())) { 
				  sql += "fabricantes_pneus.nm_fabricante_pneu, " ;
			  }
			  if (doValida(ed.getDm_Dimensao_Pneu())) { 
				  sql += "dimensoes_pneus.nm_dimensao_pneu, " ;
			  }
			  if (doValida(ed.getDm_Modelo_Pneu())) { 
				  sql += "modelos_pneus.nm_modelo_pneu, " ;
			  }
			  sql += "consertos_pneus.nr_vida, " +
			  "count(consertos_pneus.oid_pneu) as qtd " +
			  "FROM " +
			  "pneus  ," +
			  "consertos_pneus, " +
			  "fabricantes_pneus, " +
			  "dimensoes_pneus, " +
			  "modelos_Pneus " +
			  "WHERE " +
			  "consertos_pneus.oid_pneu = pneus.oid_pneu " ;
				  sql += "AND " +
				  "pneus.oid_fabricante_pneu = fabricantes_pneus.oid_Fabricante_Pneu " ;
				  sql += "AND " +
				  "pneus.oid_dimensao_pneu = dimensoes_pneus.oid_dimensao_pneu " ;
				  sql += "AND " +
			  	  "pneus.oid_modelo_pneu = modelos_pneus.oid_Modelo_Pneu " ;
			  if (ed.getOid_Empresa() > 0) {
				  sql += " AND pneus.oid_Empresa = " + ed.getOid_Empresa();
			  }
			  if (ed.getOid_Fabricante_Pneu() > 0) {
				sql += " AND pneus.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu();
			  }
			  if (ed.getOid_Dimensao_Pneu() > 0) {
				sql += " AND pneus.oid_dimensao_pneu = " + ed.getOid_Dimensao_Pneu();
			  }
			  if (ed.getOid_Modelo_Pneu() > 0) {
				sql += " AND pneus.oid_Modelo_pneu = " + ed.getOid_Modelo_Pneu();
			  }
			  if (doValida(ed.getDt_Inicial_Conserto())) {
				sql += " and Consertos_Pneus.dt_Conserto >= '" + ed.getDt_Inicial_Conserto()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Conserto())) {
				sql += " and Consertos_Pneus.dt_Conserto <= '" + ed.getDt_Final_Conserto()+ "' ";
			  }
			  sql += " Group BY " +
			  		 " consertos_pneus.nr_vida " ;
			  	if (doValida(ed.getDm_Fabricante_Pneu())) { 
				  	sql += " , fabricantes_pneus.nm_fabricante_pneu ";
			  	}
			  	if (doValida(ed.getDm_Dimensao_Pneu())) { 
			  		sql += " , dimensoes_pneus.nm_dimensao_pneu ";
			  	}	
			  	if (doValida(ed.getDm_Modelo_Pneu())) { 
			  		sql += " , modelos_pneus.nm_modelo_pneu ";
			  	}
			  	ResultSet rs = this.executasql.executarConsulta(sql);
				while (rs.next ()) {
				  ConsertoED popula = new ConsertoED ();
				  if (doValida(ed.getDm_Fabricante_Pneu())) { 
					  popula.setNm_Fabricante_Pneu(rs.getString("nm_Fabricante_Pneu"));
				  }
				  if (doValida(ed.getDm_Dimensao_Pneu())) { 
					  popula.setNm_Dimensao_Pneu(rs.getString("nm_Dimensao_Pneu"));
				  }
				  if (doValida(ed.getDm_Modelo_Pneu())) { 
					  popula.setNm_Modelo_Pneu(rs.getString("nm_Modelo_Pneu"));
				  }
				  popula.setNr_Consertos(rs.getLong("qtd"));
				  popula.setNr_Vida(rs.getLong("nr_Vida"));
				  list.add(popula);  
				}
			  return list;
		  }
		  catch (Exception e) {
			  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
		  }
	  }
	
	
	public ConsertoED getByRecord(ConsertoED ed) throws Excecoes {

		ConsertoED edQBR = new ConsertoED();
		try {
			sql = "SELECT " +
			"* " +
		  	",Consertos_Pneus.usuario_Stamp as usu_Stmp " +
		  	",Consertos_Pneus.dt_Stamp as dt_Stmp " +
		  	",Consertos_Pneus.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Veiculos, " +
			"Pneus , " +
			"Consertos_Pneus " +
			"WHERE " +
			"consertos_Pneus.oid_Empresa = " + ed.getOid_Empresa() ; 
			if (doValida(ed.getNr_Frota_Conserto()))
				sql += " and veiculos.nr_Frota = '" + ed.getNr_Frota_Conserto() + "'" ;
				sql += " and veiculos.oid_veiculo = consertos_pneus.oid_veiculo " ;
			if (doValida(ed.getNr_Fogo_Conserto()))
				sql += " and pneus.nr_Fogo = " + ed.getNr_Fogo_Conserto() + " ";
				sql += " and pneus.oid_pneu = consertos_pneus.oid_pneu " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(ConsertoED ed)");
		}
		return edQBR;
	}

	private ConsertoED populaRegistro(ResultSet res) throws SQLException {
		ConsertoED ed = new ConsertoED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Conserto(res.getLong("oid_Conserto_Pneu"));
		ed.setOid_Pneu_Conserto(res.getLong("oid_Pneu"));
		ed.setOid_Veiculo(res.getString("oid_Veiculo"));
		ed.setNr_Fogo_Conserto(res.getString("Nr_Fogo"));
		ed.setNr_Frota_Conserto(res.getString("Nr_Frota"));
		if (!doValida(ed.getNr_Frota_Conserto())) {
			ed.setNr_Frota_Conserto("");
		}
		ed.setDt_Conserto(FormataData.formataDataBT(res.getString("dt_Conserto")));
		ed.setVl_Conserto(res.getDouble("vl_Conserto"));
		ed.setDm_Vida_Conserto(res.getLong("nr_Vida"));
		ed.setNr_Documento_Conserto(res.getString("tx_Documento"));
		ed.setOid_Fornecedor_Conserto(res.getString("oid_Fornecedor"));
		ed.setTx_Descricao_Conserto(res.getString("tx_Descricao"));
		ed.setVl_Prof_Sulco_Resulcagem(res.getDouble("vl_prof_Sulco_resulcagem"));
		ed.setNm_Fornecedor(res.getString("nm_Razao_Social"));
	    ed.setUsuario_Stamp(res.getString("usu_Stmp"));
	    ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
}
