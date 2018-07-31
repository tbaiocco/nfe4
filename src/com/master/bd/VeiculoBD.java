package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.Composicao_ChassisED;
import com.master.ed.PneuED;
import com.master.ed.VeiculoED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Cristian Vianna Garcia
 * @serial Modelo_Veiculo
 * @serialData 06/2007
 */
public class VeiculoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public VeiculoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public VeiculoED inclui(VeiculoED ed) throws Excecoes {

		try {

			ed.setOid_Veiculo(String.valueOf(getAutoIncremento("oid_seq", "Veiculos")));
			sql = "INSERT INTO Veiculos (" +
			"oid_seq, " +
			"oid_Empresa, " +
			"oid_Veiculo," +
			"nr_frota, " +
			"nr_Placa, " +
			"oid_Modelo_Veiculo, " +
			"nr_Odometro, " +
			"dm_Odometro_Maximo, " +
			"nr_kilometragem_atual, " +
			"dm_Qtd_Estepes, " +
			"nm_tipo_Roda, " +
			"oid_unidade " +
			",dm_Stamp" +
		  	",dt_Stamp" +
		  	",usuario_Stamp";

			if ("F".equals(ed.getDm_Frota())) { // Isso existe para compatibilidade com o módulo de pneus sozinho STGP
				sql+=",nr_Capacidade_Tanque" +
				",oid_Motorista" +
				",oid_Centro_Custo" +
				",nr_Media_Inferior" +
				",nr_Media_Superior" +
				",nr_Ano_Fabricacao" +
				",nr_Ano_Modelo" +
				",oid_Unidade_Federativa" +
				",nr_Chassis" +
				",nr_Renavan" +
				",nm_Cor";
			}

			sql += ") " +
			" VALUES " +
			"(" +
			Integer.parseInt(ed.getOid_Veiculo()) +
			","  + ed.getOid_Empresa() +
			"," + ed.getOid_Veiculo() +
			",'" + ed.getNr_Frota() +
			"','" + ed.getNr_Placa() +
			"'," + ed.getOid_Modelo_Veiculo() +
			"," + ed.getNr_Odometro() +
			"," + ed.getDm_Odometro_Maximo() +
			"," + ed.getNr_Kilometragem_Atual() +
			"," + ed.getDm_Qtd_Estepes() +
			",'" + ed.getNm_Tipo_Roda() +
			"'," + ed.getOid_Unidade() +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" ;

			if ("F".equals(ed.getDm_Frota())) { // Isso existe para compatibilidade com o módulo de pneus sozinho STGP
				sql+="," + ed.getNr_Capacidade_Tanque() +
				"," + ed.getOid_Motorista() +
				"," + ed.getOid_Centro_Custo() +
				"," + ed.getNr_Media_Inferior() +
				"," + ed.getNr_Media_Superior() +
				"," + ed.getNr_Ano_Fabricacao() +
				"," + ed.getNr_Ano_Modelo() +
				"," + ed.getOid_Unidade_Federativa() +
				",'" + ed.getNr_Chassis() + "'" +
				",'" + ed.getNr_Renavan() +"'" +
				",'" + ed.getNm_Cor() +"'";
			}

	  		sql += ") " ;
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(VeiculoED ed)");
		}
	}

	public void altera(VeiculoED ed) throws Excecoes {
		try {
			sql = "UPDATE Veiculos SET " +
			"dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' "+
			",nr_frota = '" + ed.getNr_Frota() + "' " +
			",nr_placa = '" + ed.getNr_Placa() + "' " +
			",oid_modelo_veiculo = " + ed.getOid_Modelo_Veiculo() + " " +
			",dm_odometro_maximo = " + ed.getDm_Odometro_Maximo() + " " +
			",dm_qtd_estepes= " + ed.getDm_Qtd_Estepes() + " " +
			",nm_tipo_roda = '" + ed.getNm_Tipo_Roda() + "' " ;

			if ("F".equals(ed.getDm_Frota())) { // Isso existe para compatibilidade com o módulo de pneus sozinho STGP
				sql+=",nr_Capacidade_Tanque=" + ed.getNr_Capacidade_Tanque() +
				",oid_Motorista=" + ed.getOid_Motorista() +
				",nr_Media_Superior=" + ed.getNr_Media_Inferior() +
				",nr_Media_Inferior=" + ed.getNr_Media_Superior() +
				",nr_Ano_Fabricacao=" + ed.getNr_Ano_Fabricacao() +
				",nr_Ano_Modelo=" + ed.getNr_Ano_Modelo() +
				",oid_Unidade_Federativa=" + ed.getOid_Unidade_Federativa() +
				",nr_Chassis='" + ed.getNr_Chassis() + "'" +
				",nr_Renavan='" + ed.getNr_Renavan() +"'" +
				",nm_Cor='" + ed.getNm_Cor() +"'";
			} else {
				sql +=",oid_unidade=" + ed.getOid_Unidade() + " " ;
			}
			sql +=" WHERE " +
			"oid_Veiculo = '" + ed.getOid_Veiculo()+ "' ";
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(VeiculoED ed)");
		}
	}

	public void alteraUnidade(VeiculoED ed) throws Excecoes {
		try {
			sql = "UPDATE Veiculos SET " +
			"dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' "+
			",oid_unidade = " + ed.getOid_Unidade() + " " +
			",oid_Centro_Custo="  + ed.getOid_Centro_Custo() ;
			sql +=" WHERE " +
			"oid_Veiculo = '" + ed.getOid_Veiculo()+ "' ";
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(VeiculoED ed)");
		}
	}

	/**
	 * Atualiza a km e hodometro do veiculo - VeiculoED
	 */
	public void atualizaKm(VeiculoED ed) throws Excecoes {
		try {
			sql = "UPDATE Veiculos SET " +
			"nr_odometro = " + ed.getNr_Odometro() + ", " +
			"nr_kilometragem_atual = " + ed.getNr_Kilometragem_Atual() + " " +
			"WHERE " +
			"oid_Veiculo = '" + ed.getOid_Veiculo()+ "'" ;

			///atualizar apenas pelo odometros_veiculosBD
			///executasql.executarUpdate(sql);



		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"atualizaKm(VeiculoED ed)");
		}
	}

	/**
	 * Atualiza a km e hodometro do veiculo apartir da troca de pneu - PneuED
	 */
	public void atualizaKm(PneuED pnED) throws Excecoes {
		try {
			VeiculoED ed = new VeiculoED();
			ed.setOid_Veiculo(pnED.getOid_Veiculo());
			ed.setNr_Odometro(pnED.getNr_Hodometro_Veiculo());
			ed.setNr_Kilometragem_Atual(pnED.getNr_Km_Acumulada_Veiculo());
			this.atualizaKm(ed);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"atualizaKm(VeiculoED ed)");
		}
	}
	public void deleta(VeiculoED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Veiculos " +
			"WHERE " +
			"oid_Veiculo = '" + ed.getOid_Veiculo()+ "' and " +
			"oid_Empresa = " + ed.getOid_Empresa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(VeiculoED ed)");
		}
	}

	public ArrayList lista(VeiculoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {

			sql="SELECT " +
			"* " +
			",cv.dm_procedencia " +
			",ve.usuario_Stamp as usu_Stmp " +
			",ve.dt_Stamp as dt_Stmp " +
			",ve.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Veiculos  ve " +
			"left join Modelos_Veiculos mo on ve.oid_Modelo_Veiculo = mo.oid_Modelo_Veiculo " +
			"left join Unidades un on un.oid_Unidade = ve.oid_unidade " +
			"left join complementos_veiculos cv on ve.oid_veiculo = cv.oid_veiculo " +
			"left join Marcas_Veiculos ma on ma.oid_Marca_Veiculo = mo.oid_Marca_Veiculo " +
			"left join Tipos_Veiculos tp on tp.oid_Tipo_Veiculo = mo.oid_Tipo_Veiculo " +
			"left join Motoristas mt on mt.oid_Motorista = ve.oid_Motorista " +
			"left join Unidades_Federativas uf on uf.oid_Unidade_Federativa = ve.oid_Unidade_Federativa " +
			"left join Centros_Custos cc on cc.oid_Centro_Custo = ve.oid_Centro_Custo " +
			"WHERE ve.oid_Empresa = " + ed.getOid_Empresa() + " " ;

			if (ed.getOid_Empresa() == 1 ){ // Se for uma implantação sobre o nalthus (ed.getOid_Empresa() == 1) pega somente os veiculos próprios
				sql+="and cv.dm_procedencia ='P'";
			}
			if (doValida(ed.getNr_Frota()))
				sql += " and ve.nr_frota like '" + ed.getNr_Frota() + "%' ";
			if (doValida(ed.getNr_Placa()))
				sql += " and ve.nr_Placa like '" + ed.getNr_Placa() + "%' ";
			if (doValida(ed.getNm_Modelo_Veiculo()))
				sql += " and mo.nm_Modelo_Veiculo like '" + ed.getNm_Modelo_Veiculo() + "%' ";
			if (doValida(ed.getNm_Marca_Veiculo()))
				sql += " and ma.nm_marca_veiculo like '" + ed.getNm_Marca_Veiculo() + "%' ";
			if (doValida(ed.getNm_Tipo_Veiculo()))
				sql += " and tp.nm_tipo_veiculo like '" + ed.getNm_Tipo_Veiculo() + "%' ";
			if (ed.getOid_Unidade()>0)
				sql += " and ve.oid_Unidade = " + ed.getOid_Unidade() + " ";
			if (ed.getOid_Modelo_Veiculo()>0)
				sql += " and ve.oid_modelo_Veiculo =  " + ed.getOid_Modelo_Veiculo()+ " ";
			if (ed.getOid_Tipo_Veiculo()>0)
				sql += " and mo.oid_tipo_Veiculo =  " + ed.getOid_Tipo_Veiculo()+ " ";
			if (ed.getOid_Centro_Custo()>0)
				sql += " and ve.oid_Centro_Custo =  " + ed.getOid_Centro_Custo()+ " ";
			if (ed.getOid_Unidade_Federativa()>0)
				sql += " and ve.oid_Unidade_Federativa =  " + ed.getOid_Unidade_Federativa()+ " ";
			sql += " ORDER BY " +
			"ve.nr_frota";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public ArrayList listaSituacaoFrota(VeiculoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"d.nm_dimensao_pneu, count (p.nr_Vida) as cta_tot, " +
			"sum (case when p.nr_vida = 0 then 1 else 0 end ) as cta_vida0, " +
			"sum (case when p.nr_vida = 1 then 1 else 0 end ) as cta_vida1, " +
			"sum (case when p.nr_vida = 2 then 1 else 0 end ) as cta_vida2, " +
			"sum (case when p.nr_vida = 3 then 1 else 0 end ) as cta_vida3, " +
			"sum (case when p.nr_vida = 4 then 1 else 0 end ) as cta_vida4, " +
			"sum (case when p.nr_vida = 5 then 1 else 0 end ) as cta_vida5, " +
			"sum (case when p.nr_vida = 6 then 1 else 0 end ) as cta_vida6 "+
			"FROM " +
			"pneus as p , " +
			"dimensoes_pneus as d, " +
			"veiculos as v, " +
			"unidades as u " +
			"WHERE " +
			"p.dt_entrada is not null and " +
			"p.oid_dimensao_pneu = d.oid_dimensao_pneu and " +
			"p.oid_veiculo = v.oid_veiculo and " +
			"u.oid_unidade = v.oid_unidade " +
			"and v.oid_Empresa = " + ed.getOid_Empresa() ;
			if (ed.getOid_Unidade()>0)
				sql += " and v.oid_Unidade = " + ed.getOid_Unidade() + " ";
			if (ed.getOid_Modelo_Veiculo()>0)
				sql += " and v.oid_modelo_Veiculo =  " + ed.getOid_Modelo_Veiculo()+ " ";
			sql += " Group BY  d.nm_dimensao_pneu " ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				VeiculoED popula = new VeiculoED ();
				popula.setNm_Dimensao_Pneu(res.getString("nm_Dimensao_Pneu"));
				popula.setNr_Total(res.getLong("cta_tot"));
				popula.setNr_Qtd_N0(res.getLong("cta_vida0"));
				popula.setNr_Qtd_R1(res.getLong("cta_vida1"));
				popula.setNr_Qtd_R2(res.getLong("cta_vida2"));
				popula.setNr_Qtd_R3(res.getLong("cta_vida3"));
				popula.setNr_Qtd_R4(res.getLong("cta_vida4"));
				popula.setNr_Qtd_R5(res.getLong("cta_vida5"));
				popula.setNr_Qtd_R6(res.getLong("cta_vida6"));
				popula.setNr_Pct_N0((popula.getNr_Qtd_N0() * 100) / popula.getNr_Total() );
				popula.setNr_Pct_R1((popula.getNr_Qtd_R1() * 100) / popula.getNr_Total() );
				popula.setNr_Pct_R2((popula.getNr_Qtd_R2() * 100) / popula.getNr_Total() );
				popula.setNr_Pct_R3((popula.getNr_Qtd_R3() * 100) / popula.getNr_Total() );
				popula.setNr_Pct_R4((popula.getNr_Qtd_R4() * 100) / popula.getNr_Total() );
				popula.setNr_Pct_R5((popula.getNr_Qtd_R5() * 100) / popula.getNr_Total() );
				popula.setNr_Pct_R6((popula.getNr_Qtd_R6() * 100) / popula.getNr_Total() );
				list.add(popula);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public VeiculoED getByRecord(VeiculoED ed) throws Excecoes {
		VeiculoED edQBR = new VeiculoED();
		try {

			sql="SELECT " +
			"* " +
			",cv.dm_procedencia " +
			",ve.usuario_Stamp as usu_Stmp " +
			",ve.dt_Stamp as dt_Stmp " +
			",ve.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Veiculos  ve " +
			"left join Modelos_Veiculos mo on ve.oid_Modelo_Veiculo = mo.oid_Modelo_Veiculo " +
			"left join Unidades un on un.oid_Unidade = ve.oid_unidade " +
			"left join complementos_veiculos cv on ve.oid_veiculo = cv.oid_veiculo " +
			"left join Marcas_Veiculos ma on ma.oid_Marca_Veiculo = mo.oid_Marca_Veiculo " +
			"left join Tipos_Veiculos tp on tp.oid_Tipo_Veiculo = mo.oid_Tipo_Veiculo " +
			"left join Motoristas mt on mt.oid_Motorista = ve.oid_Motorista " +
			"left join Unidades_Federativas uf on uf.oid_Unidade_Federativa = ve.oid_Unidade_Federativa " +
			"left join Centros_Custos cc on cc.oid_Centro_Custo = ve.oid_Centro_Custo " +
			"WHERE 1=1 ";

			if (doValida(ed.getOid_Veiculo())) {
				sql += "and ve.oid_Veiculo = '" + ed.getOid_Veiculo() + "' " ;
			} else {
				if (ed.getOid_Empresa()>0)
				sql += "and ve.oid_Empresa = " + ed.getOid_Empresa() + " ";
				if (doValida(ed.getNr_Frota()))
					sql += "and ve.nr_frota = '" + ed.getNr_Frota() + "'" ;
				if (doValida(ed.getNr_Placa()))
					sql += " and ve.nr_Placa = '" + ed.getNr_Placa() + "'" ;
			}
			if (ed.getOid_Empresa() == 1 ){ // Se for uma implantação sobre o nalthus (ed.getOid_Empresa() == 1) pega somente os veiculos próprios
				sql+="and cv.dm_procedencia ='P'";
			}

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(VeiculoED ed)");
		}
		return edQBR;
	}

	public VeiculoED Distinct_Eixo(VeiculoED ed) throws Excecoes {
		VeiculoED edQBR = new VeiculoED();
		try {
			sql = "SELECT DISTINCT nr_frota, p.dm_posicao, dm_eixo " +
			"FROM Veiculos v, " +
			"Pneus p " +
			"WHERE " +
			"v.oid_veiculo = p.oid_veiculo";
			if (doValida(ed.getOid_Veiculo())) {
				sql += "and v.oid_Veiculo = '" + ed.getOid_Veiculo() + "' " ;
			} else {
				if (ed.getOid_Empresa()>0)
				sql += "and v.oid_Empresa = " + ed.getOid_Empresa() + " ";
			}
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(VeiculoED ed)");
		}
		return edQBR;
	}


	/**
	 *  getEixo
	 *  Devolve o eixo partindo da posição e do tipo de chassis do veiculo
	 *
	 * @param pos - Posicao do pneu
	 * @param ed - ed do veiculo para a busca do veiculo ( oid_Veiculo ou nr_Frota )
	 * @return
	 * @throws Excecoes
	 */
	public String getEixo(String pos, VeiculoED ed) throws Excecoes {
		String eixo="";
		try {
			ed = this.getByRecord(ed);
			if (doValida(String.valueOf(ed.getDm_Tipo_Chassis()))) {
				sql = "SELECT dm_posicao_pneu, dm_eixo, oid_chassis, oid_composicao_chassis " +
				"FROM Composicoes_Chassis " +
				"WHERE oid_chassis = " + ed.getDm_Tipo_Chassis();
				sql += " AND dm_posicao_pneu = '" + pos + "'";
				System.out.println(sql);
				ResultSet res = this.executasql.executarConsulta(sql);
				while (res.next()) {
					eixo = res.getString("dm_eixo");
				}
			}
			// Se nao enquadrou nenhum ...
			if (eixo.length()==0) eixo="X";
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(VeiculoED ed)");
		}
		return eixo;
	}

	private VeiculoED populaRegistro(ResultSet res) throws SQLException {
		VeiculoED ed = new VeiculoED();
		ed.setOid_Empresa(res.getLong("oid_Empresa"));
		ed.setOid_Veiculo(res.getString("oid_veiculo"));
		ed.setNr_Frota(res.getString("nr_frota"));
		ed.setNm_Modelo_Veiculo(res.getString("nm_modelo_veiculo"));
		ed.setNm_Marca_Veiculo(res.getString("nm_marca_veiculo"));
		ed.setNm_Tipo_Veiculo(res.getString("nm_Tipo_Veiculo"));
		ed.setNr_Odometro(res.getDouble("nr_Odometro"));
		ed.setDm_Odometro_Maximo(res.getLong("dm_Odometro_Maximo"));
		ed.setNr_Kilometragem_Atual(res.getDouble("nr_kilometragem_atual"));
		ed.setNr_Placa(res.getString("nr_Placa"));
		ed.setOid_Modelo_Veiculo(res.getLong("oid_Modelo_Veiculo"));
		ed.setDm_Qtd_Estepes(res.getLong("dm_Qtd_Estepes"));
		ed.setNm_Tipo_Roda(getValueDef(res.getString("nm_Tipo_Roda"),""));
		ed.setOid_Unidade(res.getLong("oid_unidade"));
		ed.setNm_Unidade(res.getString("nm_Unidade"));
		ed.setDm_Tipo_Chassis(res.getLong("dm_Tipo_Chassis"));
		ed.setDm_Combustivel(res.getLong("dm_Combustivel"));

		ed.setOid_Unidade_Federativa(res.getLong("oid_Unidade_Federativa"));
		ed.setOid_Motorista(res.getLong("oid_Motorista"));
		ed.setOid_Centro_Custo(res.getLong("oid_Centro_Custo"));
		ed.setNm_Centro_Custo(res.getString("nm_Centro_Custo"));

		ed.setNr_Capacidade_Tanque(res.getLong("nr_Capacidade_Tanque"));
		ed.setNr_Media_Inferior(res.getDouble("nr_Media_Inferior"));
		ed.setNr_Media_Superior(res.getDouble("nr_Media_Superior"));
		ed.setNr_Ano_Fabricacao(res.getLong("nr_Ano_Fabricacao"));
		ed.setNr_Ano_Modelo(res.getLong("nr_Ano_Modelo"));
		ed.setNr_Chassis(getValueDef(res.getString("nr_Chassis"),""));
		ed.setNr_Renavan(getValueDef(res.getString("nr_Renavan"),""));
		ed.setNm_Cor(getValueDef(res.getString("nm_Cor"),""));

		try{
			ed.setImg_Chassis(new BancoUtil(this.executasql).getTableStringValue("url_img", "chassis", "oid_chassis="+res.getLong("dm_Tipo_Chassis")));
			ed.setPosicoes(this.getComposicao_Chassis(ed));
		} catch(Exception e){
			ed.setImg_Chassis("");
		}

		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}

	public List getComposicao_Chassis(VeiculoED ed) throws Excecoes {
		ArrayList toReturn = new ArrayList();
		try {
			if (doValida(String.valueOf(ed.getDm_Tipo_Chassis()))) {
				sql = "SELECT dm_posicao_pneu, dm_eixo, oid_chassis, oid_composicao_chassis " +
				"FROM Composicoes_Chassis " +
				"WHERE oid_chassis = " + ed.getDm_Tipo_Chassis();
				sql += " order by dm_eixo ";
				ResultSet res = this.executasql.executarConsulta(sql);
				while (res.next()) {
					Composicao_ChassisED pos = new Composicao_ChassisED();
					pos.setOid_Chassis(res.getLong("oid_chassis"));
					pos.setOid_Composicao_Chassis(res.getLong("oid_composicao_chassis"));
					pos.setDm_Eixo(res.getString("dm_eixo"));
					pos.setDm_Posicao_Pneu(res.getString("dm_posicao_pneu"));
					pos.setDm_Posicao_Anterior(res.getString("dm_posicao_pneu"));

					//Pneu...
					sql = "SELECT oid_pneu, cd_pneu " +
					"FROM pneus " +
					"WHERE oid_veiculo = '" + ed.getOid_Veiculo() + "'";
					sql += " AND dm_posicao = '" + res.getString("dm_posicao_pneu") + "'";
					sql += " AND dm_eixo = '" + res.getString("dm_eixo") + "'";
					ResultSet resP = this.executasql.executarConsulta(sql);
					if(resP.next()){
						pos.setOid_Pneu(resP.getLong("oid_pneu"));
						pos.setCd_Pneu(resP.getString("cd_pneu"));
					} else {
						pos.setOid_Pneu(0);
						pos.setCd_Pneu("livre");
					}

					toReturn.add(pos);
				}
			}

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getComposicao_Chassis(VeiculoED ed)");
		}
		return toReturn;
	}

}
