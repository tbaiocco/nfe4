package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.master.ed.ConsertoED;
import com.master.ed.IndexadorED;
import com.master.ed.Vida_PneuED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

/**
 * @serial Vidas de pneus
 * @serialData 06/2007
 * @author Ralph Renato
 */
public class Vida_PneuBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Vida_PneuBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Vida_PneuED inclui(Vida_PneuED ed) throws Excecoes {
		try {
			ed.setOid_Vida_Pneu(getAutoIncremento("oid_Vida_Pneu", "Vidas_Pneus"));
			sql = "INSERT INTO Vidas_Pneus(" +
			"oid_Empresa"+
			",oid_Vida_Pneu"+
			",oid_Pneu"+
			",oid_Fornecedor"+
			",oid_Fabricante_Banda"+
			",oid_Banda"+
			",nr_Km_Inicial"+
			",nr_Km_Final"+
			",nr_Os"+
			",nr_Nota_Fiscal_Recape"+
			",nr_Mm_Inicial"+
			",nr_Mm_Final"+
			",vl_Vida"+
			",dt_Inicial"+
			",dt_Final"+
			",nr_Vida"+
			",dm_Eixo"+
			",oid_Veiculo"+
			",dm_Stamp" +
	  	    ",dt_Stamp" +
	  	    ",usuario_Stamp"+
	  	    ",oid_usuario"+
	  	    ",time_millis"+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() +
			"," + ed.getOid_Vida_Pneu() +
			"," + ed.getOid_Pneu() +
			"," + ed.getOid_Fornecedor() +
			"," + ed.getOid_Fabricante_Banda() +
			"," + ed.getOid_Banda() +
			"," + ed.getNr_Km_Inicial() +
			"," + ed.getNr_Km_Final() +
			",'" + ed.getNr_Os()+"'" +
			"," + ed.getNr_Nota_Fiscal_Recape() +
			"," + ed.getNr_Mm_Inicial() +
			"," + ed.getNr_Mm_Final() +
			"," + ed.getVl_Vida() +
   		    ","+ getSQLDate(ed.getDt_Inicial()) +
 		    ","+ getSQLDate(ed.getDt_Final()) +
			", "+ ed.getNr_Vida()+
			",'"+ (doValida(ed.getDm_Eixo()) ? ed.getDm_Eixo(): "" )+"' "+
			",'" + ed.getOid_Veiculo()+"'" +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		"," + ed.getUser() +
	  	 	"," + ed.getTime_millis() +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Vida_PneuED ed)");
		}
	}

	public void altera(Vida_PneuED ed) throws Excecoes {
		try {
			sql = "UPDATE Vidas_Pneus SET " +
			"oid_Empresa = " + ed.getOid_Empresa() +
			",oid_Vida_Pneu = " + ed.getOid_Vida_Pneu() +
			",oid_Pneu = " + ed.getOid_Pneu() +
			",oid_Fornecedor = " + ed.getOid_Fornecedor() +
			",oid_Fabricante_Banda = " + ed.getOid_Fabricante_Banda() +
			",oid_Banda = " + ed.getOid_Banda() +
			",nr_Km_Inicial = " + ed.getNr_Km_Inicial()+
			",nr_Km_Final = " + ed.getNr_Km_Final()+
			",nr_Os = '" + ed.getNr_Os() + "' " +
			",nr_Nota_Fiscal_Recape = " + ed.getNr_Nota_Fiscal_Recape() +
			",nr_Mm_Inicial = " + ed.getNr_Mm_Inicial() +
			",nr_Mm_Final = " + ed.getNr_Mm_Final() +
			",vl_Vida = " + ed.getVl_Vida() +
			",dt_Inicial ="+ getSQLDate(ed.getDt_Inicial())+" "+
			",dt_Final = "+getSQLDate(ed.getDt_Final())+" "+
			",nr_Vida = " + ed.getNr_Vida() +
			",dm_Eixo = '" + (doValida(ed.getDm_Eixo()) ? ed.getDm_Eixo(): "" )+"' "+
			",oid_Veiculo = '" + ed.getOid_Veiculo()+"' "+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
  	   		",oid_usuario = " + ed.getUser() +
  	   		",time_millis = " + ed.getTime_millis() +
			"WHERE " +
			"oid_Vida_Pneu = " + ed.getOid_Vida_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Vida_PneuED ed)");
		}
	}

	public void deleta(Vida_PneuED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Vidas_Pneus " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa()+
			" and " +
			"oid_Vida_Pneu = "  + ed.getOid_Vida_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Vida_PneuED ed)");
		}
	}

	public ArrayList lista(Vida_PneuED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT v.*, " +
	  		"v.usuario_Stamp as usu_Stmp " +
	  		",v.dt_Stamp as dt_Stmp " +
	  		",v.dm_Stamp as dm_Stmp " +
			",f.nm_fabricante_banda " +
			",f.cd_fabricante_banda, " +
			"m.nm_banda, m.cd_banda, " +
			//"fo.nm_razao_social as fonm_razao_social, " +
			"pe.nm_razao_social as penm_razao_social, " +
			"pe.nr_cnpj_cpf as penr_cnpj_cpf, " +
			"veiculos.nr_frota, " +
			"pneus.nr_fogo, pneus.cd_pneu " +
			" FROM " +
			"vidas_pneus v " +
			"left join veiculos on v.oid_veiculo = veiculos.oid_veiculo " +
			"left join fabricantes_bandas f on v.oid_fabricante_banda = f.oid_fabricante_banda " +
			"left join bandas m on v.oid_banda = m.oid_banda " +
			"left join fornecedores fo on v.oid_fornecedor = fo.oid_fornecedor " +
			"left join pessoas pe on fo.oid_pessoa = pe.oid_pessoa " +
			"left join pneus on pneus.oid_pneu = v.oid_pneu " +
			"WHERE " +
		    "pneus.oid_pneu = v.oid_pneu "  ;
			if (ed.getOid_Vida_Pneu()>0)
				sql += " and v.oid_Vida_Pneu = " + ed.getOid_Vida_Pneu() + " " ;
			else {
				sql += " and v.oid_Empresa = " + ed.getOid_Empresa();
				if (ed.getOid_Banda()>0) {
					sql += " and v.oid_Banda = " + ed.getOid_Banda() + " " ;
				}
				if (doValida (ed.getNm_Banda())) {
	   		    	sql += " and m.nm_Banda like '" + ed.getNm_Banda() + "%'";
				}
	   		    if (doValida (ed.getNm_Fabricante_Banda())) {
	   		    	sql += " and f.nm_Fabricante_Banda like '" + ed.getNm_Fabricante_Banda() + "%'";
				}
	   		    if (doValida (ed.getNm_Fornecedor())) {
	   		    	sql += " and pe.nm_razao_social like '" + ed.getNm_Fornecedor() + "%'";
				} else
	   		    if (doValida (ed.getOid_Fornecedor())) {
	   		    	sql += " and v.oid_Fornecedor = '" + ed.getOid_Fornecedor() + "' ";
				}
				if (doValida(ed.getNr_Fogo())) {
					sql += "  and pneus.nr_Fogo = '" + ed.getNr_Fogo() + "' ";
				}
				if (ed.getOid_Pneu()>0) {
					sql += " and v.oid_Pneu = " + ed.getOid_Pneu() + " " ;
				}
				if (ed.getNr_Vida()>=0 &&ed.getNr_Vida()<99) {
					sql += " and v.nr_Vida = " + ed.getNr_Vida() ;
				}
				if (ed.getNr_Vida()==99) {
					sql += " and v.nr_Vida > 0 " ;
				}
			}
			sql += " ORDER BY " +
			"pneus.nr_Fogo, v.nr_vida";
			System.out.println(sql);
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	  public ArrayList listaIndiceRecapagem (Vida_PneuED ed) throws Excecoes {
		  ArrayList list = new ArrayList ();
		  try {
			  sql = "SELECT ";
			  if (doValida(ed.getDm_Fabricante_Pneu())) {
				  sql += "f.nm_fabricante_pneu, " ;
			  }
			  if (doValida(ed.getDm_Dimensao_Pneu())) {
				  sql += "d.nm_dimensao_pneu, " ;
			  }
			  if (doValida(ed.getDm_Modelo_Pneu())) {
				  sql += "m.nm_modelo_pneu, " ;
			  }
			  sql += "count (v.nr_Vida) as cta_tot_Vidas, " +
			  "sum (case when v.nr_vida = 0 then 1 else 0 end ) as cta_vida0, " +
			  "sum (case when v.nr_vida = 1 then 1 else 0 end ) as cta_vida1, " +
			  "sum (case when v.nr_vida = 2 then 1 else 0 end ) as cta_vida2, " +
			  "sum (case when v.nr_vida = 3 then 1 else 0 end ) as cta_vida3, " +
			  "sum (case when v.nr_vida = 4 then 1 else 0 end ) as cta_vida4, " +
			  "sum (case when v.nr_vida = 5 then 1 else 0 end ) as cta_vida5, " +
			  "sum (case when v.nr_vida = 6 then 1 else 0 end ) as cta_vida6 " +
			  "FROM " +
			  "vidas_pneus as v, " +
			  "pneus as p , " +
			  "dimensoes_pneus as d, " +
			  "fabricantes_pneus as f , " +
			  "modelos_pneus as m " +
			  "WHERE " +
			  "v.oid_pneu = p.oid_pneu " ;
			  sql += "AND p.oid_fabricante_pneu = f.oid_Fabricante_Pneu " ;
			  sql += "AND p.oid_dimensao_pneu = d.oid_dimensao_pneu " ;
			  sql += "AND p.oid_modelo_pneu = m.oid_Modelo_Pneu " ;
			  if (ed.getOid_Empresa() > 0) {
				  sql += " AND v.oid_Empresa = " + ed.getOid_Empresa();
			  }
			  if (doValida(ed.getDm_Dimensao_Pneu())) {
				  sql += "  Group BY  d.nm_dimensao_pneu ";
			  }
			  if (doValida(ed.getDm_Fabricante_Pneu())) {
				  sql += " , f.nm_fabricante_pneu ";
			  }
			  if (doValida(ed.getDm_Modelo_Pneu())) {
				  sql += " , m.nm_modelo_pneu ";
			  }
			  ResultSet rs = this.executasql.executarConsulta(sql);
			  while (rs.next ()) {
				  Vida_PneuED popula = new Vida_PneuED ();
				  if (doValida(ed.getDm_Fabricante_Pneu())) {
					  popula.setNm_Fabricante_Pneu(rs.getString("nm_Fabricante_Pneu"));
				  }
				  if (doValida(ed.getDm_Dimensao_Pneu())) {
					  popula.setNm_Dimensao_Pneu(rs.getString("nm_Dimensao_Pneu"));
				  }
				  if (doValida(ed.getDm_Modelo_Pneu())) {
					  popula.setNm_Modelo_Pneu(rs.getString("nm_Modelo_Pneu"));
				  }
				  popula.setNr_Qtd_N0(rs.getInt("cta_vida0"));
				  popula.setNr_Qtd_R1(rs.getInt("cta_vida1"));
				  popula.setNr_Qtd_R2(rs.getInt("cta_vida2"));
				  popula.setNr_Qtd_R3(rs.getInt("cta_vida3"));
				  popula.setNr_Qtd_R4(rs.getInt("cta_vida4"));
				  popula.setNr_Qtd_R5(rs.getInt("cta_vida5"));
				  popula.setNr_Qtd_R6(rs.getInt("cta_vida6"));
				  popula.setNr_Qtd_Total_Vidas(
						  popula.getNr_Qtd_R1() + popula.getNr_Qtd_R2() + popula.getNr_Qtd_R3() +
						  popula.getNr_Qtd_R4() + popula.getNr_Qtd_R5() + popula.getNr_Qtd_R6() );
				  popula.setVl_Vida_Total(popula.getNr_Qtd_Total_Vidas() * 1.0 / popula.getNr_Qtd_N0());
				  popula.setVl_Vida_R1(popula.getNr_Qtd_R1() * 1.0 / popula.getNr_Qtd_N0());
				  popula.setVl_Vida_R2(popula.getNr_Qtd_R2() * 1.0 / popula.getNr_Qtd_N0());
				  popula.setVl_Vida_R3(popula.getNr_Qtd_R3() * 1.0 / popula.getNr_Qtd_N0());
				  popula.setVl_Vida_R4(popula.getNr_Qtd_R4() * 1.0 / popula.getNr_Qtd_N0());
				  popula.setVl_Vida_R5(popula.getNr_Qtd_R5() * 1.0 / popula.getNr_Qtd_N0());
				  popula.setVl_Vida_R6(popula.getNr_Qtd_R6() * 1.0 / popula.getNr_Qtd_N0());
				  list.add(popula);
			  }
			  return list;
		  }
		  catch (Exception e) {
			  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
		  }
	  }

	public Vida_PneuED getByRecord(Vida_PneuED ed) throws Excecoes {

		Vida_PneuED edQBR = new Vida_PneuED();
		try {
			sql = "SELECT v.* " +
		  		",v.usuario_Stamp as usu_Stmp " +
		  		",v.dt_Stamp as dt_Stmp " +
		  		",v.dm_Stamp as dm_Stmp " +
				",f.nm_fabricante_banda, " +
				"m.nm_banda, " +
				"fo.nm_razao_social, " +
				"veiculos.nr_frota, " +
				"pneus.nr_fogo " +
			" FROM " +
			"vidas_pneus v, " +
			"fabricantes_bandas f, " +
			"bandas m, " +
			"fornecedores fo, " +
			"pneus, " +
			"veiculos " +
			"WHERE " +
			"v.oid_fabricante_banda = f.oid_fabricante_banda " +
			"AND " +
		    "v.oid_banda = m.oid_banda " +
			"AND " +
		    "v.oid_fornecedor = fo.oid_fornecedor " +
		    "AND " +
		    "pneus.oid_pneu = v.oid_pneu " +
		    "AND " +
			"v.oid_veiculo = veiculos.oid_veiculo " ;
			if (ed.getOid_Vida_Pneu()>0)
				sql += " and v.oid_Vida_Pneu = " + ed.getOid_Vida_Pneu() + " " ;
			else {
				sql += " and v.oid_Empresa = " + ed.getOid_Empresa();
				if (doValida (ed.getNm_Banda())) {
	   		    	sql += " and m.nm_Banda like '" + ed.getNm_Banda() + "%'";
				}
	   		    if (ed.getOid_Banda () > 0) {
	   			    sql += " and v.oid_Banda = " + ed.getOid_Banda ();
	   		    }
	   		    if (doValida (ed.getNm_Fabricante_Banda())) {
	   		    	sql += " and f.nm_Fabricante_Banda like '" + ed.getNm_Fabricante_Banda() + "%'";
				}
	   		    if (ed.getOid_Fabricante_Banda () > 0) {
	   			    sql += " and v.oid_fabricante_Banda = " + ed.getOid_Fabricante_Banda ();
	   		    }
	   		    if (doValida (ed.getNm_Fornecedor())) {
	   		    	sql += " and fo.nm_razao_social like '" + ed.getNm_Fornecedor() + "%'";
				}
	   		    if (doValida(ed.getOid_Fornecedor())) {
	   			    sql += " and v.oid_fornecedor = " + ed.getOid_Fornecedor ();
	   		    }
	   		    if (doValida(ed.getOid_Veiculo ())) {
	   			    sql += " and ve.oid_veiculo = " + ed.getOid_Veiculo ();
	   		    }
	   		    if (doValida(ed.getNr_Fogo())){
	   			    sql += " and p.nr_Fogo = '" + ed.getNr_Fogo() + "' ";
	   		    }
	   		    if (doValida(ed.getNr_Frota())){
	   			    sql += " and v.nr_Frota = '" + ed.getNr_Frota() + "' ";
	   		    }
	   		    if (ed.getNr_Vida()>0){
	   			    sql += " and v.nr_Vida = " + ed.getNr_Vida() + " ";
	   		    }
				if (ed.getOid_Pneu()>0) {
					sql += " and v.oid_Pneu = " + ed.getOid_Pneu() + " " ;
				}
			}
   		    sql += " ORDER BY " +
			" v.nr_vida";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Vida_PneuED ed)");
		}
		return edQBR;
	}

	private Vida_PneuED populaRegistro(ResultSet res) throws SQLException {
		Vida_PneuED ed = new Vida_PneuED();
		ed.setOid_Empresa(res.getLong("oid_Empresa"));
		ed.setOid_Vida_Pneu(res.getLong("oid_Vida_Pneu"));
		ed.setOid_Pneu(res.getLong("oid_Pneu"));
		ed.setCd_Pneu(res.getString("cd_Pneu"));
		ed.setOid_Fornecedor(res.getString("oid_Fornecedor"));
		ed.setOid_Fabricante_Banda(res.getLong("oid_Fabricante_Banda"));
		ed.setOid_Banda(res.getLong("oid_Banda"));
		ed.setOid_Veiculo(getValueDef(res.getString("oid_Veiculo"),""));
		ed.setNr_Km_Inicial(res.getDouble("nr_km_inicial"));
		ed.setNr_Km_Final(res.getDouble("nr_Km_Final"));
		ed.setNr_Os(res.getString("nr_Os"));
		ed.setNr_Nota_Fiscal_Recape(res.getLong("nr_Nota_Fiscal_Recape"));
		ed.setNr_Mm_Inicial(res.getDouble("nr_Mm_Inicial"));
		ed.setNr_Mm_Final(res.getDouble("nr_Mm_Final"));
		ed.setVl_Vida(res.getDouble("vl_Vida"));
		ed.setDt_Inicial(FormataData.formataDataBT(res.getString("dt_Inicial")));
		ed.setDt_Final(FormataData.formataDataBT(res.getString("dt_Final")));
		ed.setDm_Eixo(res.getString("dm_Eixo"));
		ed.setNr_Vida(res.getLong("nr_Vida"));
		ed.setCd_Fabricante_Banda(res.getString("cd_Fabricante_Banda"));
		ed.setNm_Fabricante_Banda(res.getString("nm_Fabricante_Banda"));
//		if (Utilitaria.doValida(res.getString("fonm_Razao_Social"))) {
//			ed.setNm_Fornecedor(res.getString("fonm_Razao_Social"));
//		} else {
		ed.setNr_Cnpj_Cpf(res.getString("penr_cnpj_cpf"));
		ed.setNm_Fornecedor(res.getString("penm_Razao_Social"));
		ed.setNm_Razao_Social(res.getString("penm_Razao_Social"));
//		}
		ed.setCd_Banda(res.getString("cd_Banda"));
		ed.setNm_Banda(res.getString("nm_Banda"));
		ed.setNm_Vida(ed.getDescVida());
		ed.setNr_Frota(res.getString("nr_Frota"));
		if (!doValida(ed.getNr_Frota()))
			ed.setNr_Frota("");
		ed.setNr_Fogo(res.getString("nr_Fogo"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		//Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
		if(!doValida(ed.getMsg_Stamp())){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		}
		return ed;
	}

	/**
	 * Pega a última vida de um pneu
	 * @param ed
	 * @return
	 * @throws Excecoes
	 */
	public Vida_PneuED getUltimaVida(Vida_PneuED ed) throws Excecoes {
		Vida_PneuED edQBR = new Vida_PneuED();
		try {
			sql = "SELECT * " +
				  "from Vidas_Pneus " +
				  "where " +
				  "Vidas_Pneus.oid_Pneu = " + ed.getOid_Pneu() + " " +
				  "order by " +
				  "nr_Vida Desc limit 1";
			ResultSet res = this.executasql.executarConsulta(sql);
			if  (res.last()) {
				edQBR = populaRegistroSimples(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getUltimaVida(Vida_PneuED ed)");
		}
		return edQBR;
	}

	private Vida_PneuED populaRegistroSimples(ResultSet res) throws SQLException {
		Vida_PneuED ed = new Vida_PneuED();
		ed.setOid_Empresa(res.getLong("oid_Empresa"));
		ed.setOid_Vida_Pneu(res.getLong("oid_Vida_Pneu"));
		ed.setOid_Pneu(res.getLong("oid_Pneu"));
		ed.setOid_Fornecedor(res.getString("oid_Fornecedor"));
		ed.setOid_Fabricante_Banda(res.getLong("oid_Fabricante_banda"));
		ed.setOid_Banda(res.getLong("oid_Banda"));
		ed.setOid_Veiculo(res.getString("oid_Veiculo"));
		ed.setNr_Km_Inicial(res.getDouble("nr_Km_Inicial"));
		ed.setNr_Km_Final(res.getDouble("nr_Km_Final"));
		ed.setNr_Os(res.getString("nr_Os"));
		ed.setNr_Nota_Fiscal_Recape(res.getLong("nr_Nota_Fiscal_Recape"));
		ed.setNr_Mm_Inicial(res.getDouble("nr_Mm_Inicial"));
		ed.setNr_Mm_Final(res.getDouble("nr_Mm_Final"));
		ed.setVl_Vida(res.getDouble("vl_Vida"));
		ed.setDt_Inicial(FormataData.formataDataBT(res.getString("dt_Inicial")));
		ed.setDt_Final(FormataData.formataDataBT(res.getString("dt_Final")));
		ed.setDm_Eixo(res.getString("dm_Eixo"));
		ed.setNr_Vida(res.getLong("nr_Vida"));
		return ed;
	}


	public ArrayList listaAnaliseCarcaca(Vida_PneuED ed,String tipo) throws Excecoes {
		String nr_Fogo_Compara = null;
		double nr_Km_Vida = 0;
		double vl_Vida = 0;
		double inx = 0;
		boolean boolInx = false;
		IndexadorED inxED = new IndexadorED();
		Vida_PneuED vidaED = null;
		ArrayList list = new ArrayList();
		try {
			inxED.setOid_Empresa(ed.getOid_Empresa());
			boolInx = new IndexadorBD(this.executasql).getUsaInx(inxED);
			sql = "select " +
				  "v.dm_Eixo," +
				  "d.nm_Dimensao_Pneu," +
				  "f.nm_Fabricante_Pneu," +
				  "m.nm_Modelo_Pneu," +
				  "p.oid_pneu," +
				  "p.nr_Fogo," +
				  "p.dt_Venda," +
				  "p.vl_Venda," +
				  "v.nr_Vida, " +
				  "v.nr_Km_Final-v.nr_Km_Inicial as nr_Km_Vida,"  +
				  "v.vl_Vida, " +
				  "v.dt_inicial " +
				  "from " +
				  "pneus p, " +
				  "vidas_Pneus v, " +
				  "dimensoes_Pneus as d, " +
				  "fabricantes_Pneus f, " +
				  "modelos_Pneus m " +
				  "where " +
				  "p.oid_pneu = v.oid_pneu and " +
				  "p.oid_dimensao_pneu = d.oid_dimensao_pneu and " +
				  "p.oid_fabricante_pneu = f.oid_fabricante_pneu and " +
				  "p.oid_modelo_pneu = m.oid_modelo_pneu and " +
				  "p.dm_controle_parcial = 'false' and " +
				  "p.oid_Empresa = " + ed.getOid_Empresa() + " ";
			//
			if ("2".equals(ed.getDm_Situacao())) {
				sql += "and p.dt_sucateamento is not null ";
			} else
			if ("3".equals(ed.getDm_Situacao())) {
				sql += "and p.dt_sucateamento is null  ";
			}
			if ("false".equals(ed.getDm_Total())) {
				// Periodo de encerramento das vidas
				sql += " and v.dt_final >= '" + ed.getDt_Inicial() + "' " +
					   " and v.dt_final <= '" + ed.getDt_Final() + "' "   ;
			}
			if ("true".equals(ed.getDm_Vida_Ate()) ) {
				sql += "and v.Nr_Vida <= " + ed.getNr_Vida_Ate() + " ";
			} else
			if ("true".equals(ed.getDm_Vida_So()) ) {
				sql += "and v.Nr_Vida = " + ed.getNr_Vida_So() + " ";
			}
			if (doValida(ed.getDm_Eixo())) {
				sql += "and v.dm_Eixo = '" + ed.getDm_Eixo() + "'  ";
			}
			if (ed.getOid_Dimensao_Pneu()>0) {
				sql += "and p.oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu() + "  ";
			}
			if (ed.getOid_Fabricante_Pneu()>0) {
				sql += "and p.oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() + "  ";
			}
			if (ed.getOid_Modelo_Pneu()>0) {
				sql += "and p.oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu() + "  ";
			}
			if ("rel".equals(tipo)) {
				sql+= "Order by " +
				  "v.dm_Eixo," +
				  "d.nm_Dimensao_Pneu," +
				  "f.nm_Fabricante_Pneu," +
				  "m.nm_Modelo_Pneu,"+
				  "p.nr_Fogo," +
				  "v.nr_vida";
			}
			if ("gra".equals(tipo)) {
				sql+= "Order by " +
				  "f.nm_Fabricante_Pneu," +
				  "m.nm_Modelo_Pneu";
			}
System.out.println(sql);
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				if (!res.getString("nr_Fogo").equals(nr_Fogo_Compara)) {
					if (doValida(nr_Fogo_Compara)) {
						list.add(vidaED);
					}
					vidaED = new Vida_PneuED();
					vidaED.setOid_Pneu(res.getLong("oid_Pneu"));
					vidaED.setDm_Eixo(res.getString("dm_Eixo"));
					vidaED.setNm_Dimensao_Pneu(res.getString("nm_Dimensao_Pneu"));
					vidaED.setNm_Fabricante_Pneu(res.getString("nm_Fabricante_Pneu"));
					vidaED.setNm_Modelo_Pneu(res.getString("nm_Modelo_Pneu"));
					vidaED.setNr_Fogo(res.getString("nr_Fogo"));
					//
					nr_Fogo_Compara=vidaED.getNr_Fogo();
				}
				nr_Km_Vida=res.getDouble("nr_Km_Vida");
				// Busca o indexador se é que utiliza algum
				inx=1;
				if (boolInx){
					inxED.setOid_Empresa(ed.getOid_Empresa());
					inxED.setDt_Indexador(res.getString("dt_inicial"));
					inxED = new IndexadorBD(this.executasql).getByData(inxED);
					if (inxED.getVl_Indexador()>0)
						inx = inxED.getVl_Indexador();
				}
				// Valor da vida
				vl_Vida = (res.getDouble("vl_Vida") * inx);
				//
				// Busca o valor dos consertos para a vida
				ConsertoED consED = new ConsertoED();
				consED.setOid_Empresa(ed.getOid_Empresa());
				consED.setOid_Pneu_Conserto(vidaED.getOid_Pneu());
				consED.setDm_Vida_Conserto((res.getInt("nr_Vida")==0 ? 10 : res.getInt("nr_Vida")));
				ArrayList lstCons = new ConsertoBD(this.executasql).lista(consED);
				for (int i=0; i<lstCons.size(); i++){
					consED =  (ConsertoED)lstCons.get(i);
					// Busca o indexador se é que utiliza algum
					inx=1;
					if (boolInx){
						inxED.setOid_Empresa(ed.getOid_Empresa());
						inxED.setDt_Indexador(consED.getDt_Conserto());
						inxED = new IndexadorBD(this.executasql).getByData(inxED);
						if (inxED.getVl_Indexador()>0)
							inx = inxED.getVl_Indexador();
					}
					vl_Vida = vl_Vida + (consED.getVl_Conserto() * inx);
				}
				// Coloca o valor da vida no campo correto
				if (res.getInt("nr_Vida")==0) {
					// Se pneu vendido e subtrai valor da venda ...
					if ("0".equals(ed.getDm_Vl_Venda()) ) {
						if (res.getDouble("vl_venda")>0) {
							inx=1;
							if (boolInx){
								inxED.setOid_Empresa(ed.getOid_Empresa());
								inxED.setDt_Indexador(res.getString("dt_Venda"));
								inxED = new IndexadorBD(this.executasql).getByData(inxED);
								if (inxED.getVl_Indexador()>0)
									inx = inxED.getVl_Indexador();
							}
							// Valor da vida - venda indexado
							vl_Vida -= (res.getDouble("vl_Venda") * inx);
						}
					}
					//
					vidaED.setNr_Km_Vida_N0(nr_Km_Vida);
					vidaED.setVl_Vida_N0(vl_Vida);
					vidaED.setNr_Qtd_N0(1);
				}
				if (res.getInt("nr_Vida")==1) {
					vidaED.setNr_Km_Vida_R1(nr_Km_Vida);
					vidaED.setVl_Vida_R1(vl_Vida);
					vidaED.setNr_Qtd_R1(1);
				}
				if (res.getInt("nr_Vida")==2) {
					vidaED.setNr_Km_Vida_R2(nr_Km_Vida);
					vidaED.setVl_Vida_R2(vl_Vida);
					vidaED.setNr_Qtd_R2(1);
				}
				if (res.getInt("nr_Vida")==3) {
					vidaED.setNr_Km_Vida_R3(nr_Km_Vida);
					vidaED.setVl_Vida_R3(vl_Vida);
					vidaED.setNr_Qtd_R3(1);
				}
				if (res.getInt("nr_Vida")==4) {
					vidaED.setNr_Km_Vida_R4(nr_Km_Vida);
					vidaED.setVl_Vida_R4(vl_Vida);
					vidaED.setNr_Qtd_R4(1);
				}
				if (res.getInt("nr_Vida")==5) {
					vidaED.setNr_Km_Vida_R5(nr_Km_Vida);
					vidaED.setVl_Vida_R5(vl_Vida);
					vidaED.setNr_Qtd_R5(1);
				}
				if (res.getInt("nr_Vida")==6) {
					vidaED.setNr_Km_Vida_R6(nr_Km_Vida);
					vidaED.setVl_Vida_R6(vl_Vida);
					vidaED.setNr_Qtd_R6(1);
				}
			}

			if (doValida(nr_Fogo_Compara)) {
				list.add(vidaED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public ArrayList listaAnaliseRecapagem(Vida_PneuED ed) throws Excecoes {
		String nr_Fogo_Compara = null;
		double nr_Km_Vida = 0;
		double vl_Vida = 0;
		double inx = 0;
		boolean boolInx = false;
		IndexadorED inxED = new IndexadorED();
		Vida_PneuED vidaED = null;
		ArrayList list = new ArrayList();
		try {
			inxED.setOid_Empresa(ed.getOid_Empresa());
			boolInx = new IndexadorBD(this.executasql).getUsaInx(inxED);
			sql = "select " +
				  "v.dm_Eixo," +
				  "b.nm_Banda,"+
				  "f.nm_Fabricante_Banda,"+
				  "o.nm_Razao_Social,"+
				  "d.nm_Dimensao_Pneu," +
				  "m.nm_Modelo_Pneu," +
				  "p.oid_pneu," +
				  "p.nr_Fogo," +
				  "p.dt_Venda," +
				  "p.vl_Venda," +
				  "v.nr_Vida, " +
				  "v.nr_Km_Final-v.nr_Km_Inicial as nr_Km_Vida,"  +
				  "v.vl_Vida, " +
				  "v.dt_inicial " +
				  "from " +
				  "pneus p, " +
				  "vidas_Pneus v, " +
				  "dimensoes_Pneus as d, " +
				  "bandas as b," +
				  "fabricantes_bandas as f," +
				  "fornecedores as o," +
				  "modelos_Pneus m " +
				  "where " +
				  "p.oid_pneu = v.oid_pneu and " +
				  "p.oid_dimensao_pneu = d.oid_dimensao_pneu and " +
				  "v.oid_Banda = b.oid_Banda and " +
				  "b.oid_Fabricante_Banda = f.oid_Fabricante_Banda and " +
				  "p.oid_modelo_pneu = m.oid_modelo_pneu and " +
				  "v.oid_Fornecedor = o.oid_Fornecedor and " +
				  "p.dm_controle_parcial = 'false' and " +
				  "p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
				  "v.Nr_Vida > 0 ";
			//
			if ( ed.getDm_Situacao() == "2") {
				// só sucateados
				sql += "and p.dt_sucateamento is not null ";
			} else
			if ( ed.getDm_Situacao() == "3") {
				// não sucateados
				sql += "and p.dt_sucateamento is null  ";
			}
			if ("false".equals(ed.getDm_Total())) {
				// Periodo de encerramento das vidas
				sql += " and v.dt_final >= '" + ed.getDt_Inicial() + "' " +
					   " and v.dt_final <= '" + ed.getDt_Final() + "' "   ;
			}
			if ("true".equals(ed.getDm_Vida_Ate()) ) {
				sql += "and v.Nr_Vida <= " + ed.getNr_Vida_Ate() + " ";
			} else
			if ("true".equals(ed.getDm_Vida_So()) ) {
				sql += "and v.Nr_Vida = " + ed.getNr_Vida_So() + " ";
			}
			if (ed.getOid_Banda()>0) {
				sql += "and v.oid_Banda = " + ed.getOid_Banda() + "  ";
			}
			if (ed.getOid_Fabricante_Banda()>0) {
				sql += "and f.oid_Fabricante_Banda = " + ed.getOid_Fabricante_Banda() + "  ";
			}
			if (doValida(ed.getDm_Eixo())) {
				sql += "and v.dm_Eixo = '" + ed.getDm_Eixo() + "'  ";
			}
			if (ed.getOid_Dimensao_Pneu()>0) {
				sql += "and p.oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu() + "  ";
			}
			if (ed.getOid_Modelo_Pneu()>0) {
				sql += "and p.oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu() + "  ";
			}
			sql+= "Order by " ;
			// Seta o nome do relatório
			if ("desenho".equals(ed.getDm_Vl_Venda()) )
				sql+= "b.nm_Banda, " ;
			else
			if ("borracha".equals(ed.getDm_Vl_Venda()) )
				sql+= "f.nm_Fabricante_Banda, " ;
			if ("recapador".equals(ed.getDm_Vl_Venda()) )
				sql+= "o.nm_Razao_Social, " ;
			sql+= "v.dm_Eixo," +
				  "d.nm_Dimensao_Pneu," +
				  "m.nm_Modelo_Pneu,"+
				  "p.nr_Fogo," +
				  "v.nr_vida";

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				if (!res.getString("nr_Fogo").equals(nr_Fogo_Compara)) {
					if (doValida(nr_Fogo_Compara)) {
						list.add(vidaED);
					}
					vidaED = new Vida_PneuED();
					vidaED.setOid_Pneu(res.getLong("oid_Pneu"));

					if ("desenho".equals(ed.getDm_Vl_Venda()) )
						vidaED.setNm_Banda(res.getString("nm_Banda"));
					else
					if ("borracha".equals(ed.getDm_Vl_Venda()) )
						vidaED.setNm_Banda(res.getString("nm_Fabricante_Banda"));
					else
					if ("recapador".equals(ed.getDm_Vl_Venda()) )
						vidaED.setNm_Banda(res.getString("nm_Razao_Social"));

					vidaED.setDm_Eixo(res.getString("dm_Eixo"));
					vidaED.setNm_Dimensao_Pneu(res.getString("nm_Dimensao_Pneu"));
					vidaED.setNm_Modelo_Pneu(res.getString("nm_Modelo_Pneu"));
					vidaED.setNr_Fogo(res.getString("nr_Fogo"));
					//
					nr_Fogo_Compara=vidaED.getNr_Fogo();
				}
				nr_Km_Vida=res.getDouble("nr_Km_Vida");
				// Busca o indexador se é que utiliza algum
				inx=1;
				if (boolInx){
					inxED.setOid_Empresa(ed.getOid_Empresa());
					inxED.setDt_Indexador(res.getString("dt_inicial"));
					inxED = new IndexadorBD(this.executasql).getByData(inxED);
					if (inxED.getVl_Indexador()>0)
						inx = inxED.getVl_Indexador();
				}
				// Valor da vida
				vl_Vida = (res.getDouble("vl_Vida") * inx);
				//
				// Busca o valor dos consertos para a vida
				ConsertoED consED = new ConsertoED();
				consED.setOid_Empresa(ed.getOid_Empresa());
				consED.setOid_Pneu_Conserto(vidaED.getOid_Pneu());
				consED.setDm_Vida_Conserto((res.getInt("nr_Vida")==0 ? 10 : res.getInt("nr_Vida")));
				ArrayList lstCons = new ConsertoBD(this.executasql).lista(consED);
				for (int i=0; i<lstCons.size(); i++){
					consED =  (ConsertoED)lstCons.get(i);
					// Busca o indexador se é que utiliza algum
					inx=1;
					if (boolInx){
						inxED.setOid_Empresa(ed.getOid_Empresa());
						inxED.setDt_Indexador(consED.getDt_Conserto());
						inxED = new IndexadorBD(this.executasql).getByData(inxED);
						if (inxED.getVl_Indexador()>0)
							inx = inxED.getVl_Indexador();
					}
					vl_Vida = vl_Vida + (consED.getVl_Conserto() * inx);
				}
				// Coloca o valor da vida no campo correto
				if (res.getInt("nr_Vida")==1) {
					vidaED.setNr_Km_Vida_R1(nr_Km_Vida);
					vidaED.setVl_Vida_R1(vl_Vida);
					vidaED.setNr_Qtd_R1(1);
				}
				if (res.getInt("nr_Vida")==2) {
					vidaED.setNr_Km_Vida_R2(nr_Km_Vida);
					vidaED.setVl_Vida_R2(vl_Vida);
					vidaED.setNr_Qtd_R2(1);
				}
				if (res.getInt("nr_Vida")==3) {
					vidaED.setNr_Km_Vida_R3(nr_Km_Vida);
					vidaED.setVl_Vida_R3(vl_Vida);
					vidaED.setNr_Qtd_R3(1);
				}
				if (res.getInt("nr_Vida")==4) {
					vidaED.setNr_Km_Vida_R4(nr_Km_Vida);
					vidaED.setVl_Vida_R4(vl_Vida);
					vidaED.setNr_Qtd_R4(1);
				}
				if (res.getInt("nr_Vida")==5) {
					vidaED.setNr_Km_Vida_R5(nr_Km_Vida);
					vidaED.setVl_Vida_R5(vl_Vida);
					vidaED.setNr_Qtd_R5(1);
				}
				if (res.getInt("nr_Vida")==6) {
					vidaED.setNr_Km_Vida_R6(nr_Km_Vida);
					vidaED.setVl_Vida_R6(vl_Vida);
					vidaED.setNr_Qtd_R6(1);
				}
			}
			if (doValida(nr_Fogo_Compara)) {
				list.add(vidaED);
			}

			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

}
