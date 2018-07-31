package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Movimento_PneuED;
import com.master.ed.PneuED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**o
 * @author André Valadas
 * - Movimento Pneus
 */
public class Movimento_PneuBD extends BancoUtil {

	private ExecutaSQL executaSQL;

	public Movimento_PneuBD(ExecutaSQL executaSQL) {
		super(executaSQL);
		this.executaSQL = executaSQL;
	}

	public Movimento_PneuED inclui (Movimento_PneuED ed) throws Excecoes {

		String sql = null;
		try {

			ed.setOid_Movimento_Pneu (getAutoIncremento ("oid_Movimento_Pneu" , "Movimentos_Pneus"));

			sql = " INSERT INTO Movimentos_Pneus (" +
			"		 oid_Movimento_Pneu" +
			"		,oid_Pneu" +
			"		,oid_Veiculo" +
			"		,KM_Pneu" +
			"		,KM_Inicial" +
			"		,KM_Final" +
			"		,DT_Inclusao" +
			"		,DT_Remocao" +
			"		,DM_Localizacao" +
			"		,DM_Situacao" +
			"       ,HR_Alteracao" +
			"		,TX_Observacao " ;

			// Laszlo - by PRS //
			if (ed.getOid_Empresa()>0) {
				sql+=",oid_empresa" +
				     ",oid_local_estoque" +
				     ",dt_entrada" +
					 ",nr_odometro_entrada " +
					 ",nr_km_acumulada_entrada " +
					 ",dm_posicao " +
					 ",dm_eixo " +
					 ",nr_mm_entrada " +
					 ",nr_vida " +
					 ",tx_motivo"+
					 ",dm_tipo_movimento " ;
					 if (doValida(ed.getDm_Rodou_Pouco())) {
						 sql+=",dm_rodou_pouco " +
						 ",nr_media_comparacao ";
					 }
				ed.setDM_Localizacao("");
				ed.setDM_Situacao("");
			}

			sql+= ") VALUES (" +
			ed.getOid_Movimento_Pneu () +
			"," + ed.getOid_Pneu () +
			",'" + ed.getOid_Veiculo () + "'" +
			"," + ed.getKM_Pneu () +
			"," + ed.getKM_Inicial () +
			"," + ed.getKM_Final () +
			",'" + ed.getDT_Inclusao () + "'" +
			",'" + ed.getDT_Remocao () + "'" +
			",'" + ed.getDM_Localizacao () + "'" +
			",'" + ed.getDM_Situacao () + "'" +
			",'" + Data.getHoraHM () + "'" +
			",'" + ed.getTX_Observacao () + "'" ;

			// Se oidEmpresa for informado é porque é uma operação laszlo
			if (ed.getOid_Empresa()>0) {
				sql+=", " + ed.getOid_Empresa() +
					 ", " + ed.getOid_Local_Estoque() +
					 ",'" + ed.getDt_Entrada() +"' " +
					 ", " + ed.getNr_Odometro_Entrada() +
					 ", " + ed.getNr_Km_Acumulada_Entrada() +
					 ",'" + ed.getDm_Posicao() + "'" +
					 ",'" + ed.getDm_Eixo() + "'" +
					 ", " +ed.getNr_Mm_Entrada() +
					 ", " +ed.getNr_Vida() +
					 ", " +(doValida(ed.getTx_Motivo())?"'"+ed.getTx_Motivo()+"'":null) +
					 ",'" +ed.getDm_Tipo_Movimento() + "'" ;
					 if (doValida(ed.getDm_Rodou_Pouco())) {
						 sql+=",'" +ed.getDm_Rodou_Pouco() + "'" +
						   	  ", " + ed.getNr_Media_Comparacao();
					}
			}
			sql += ")";
			executaSQL.executarUpdate (sql);
			return ed;
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
					"inclui()");
		}
	}

	public void altera (Movimento_PneuED ed) throws Excecoes {

		String sql = null;
		try {

			sql = " UPDATE Movimentos_Pneus SET " +
			"      KM_Pneu =" + ed.getKM_Pneu () +
			"     ,KM_Final =" + ed.getKM_Final () +
			"     ,DT_Remocao = '" + ed.getDT_Remocao () + "'" +
			"     ,DT_Inclusao = ' '" +
			" WHERE oid_Movimento_Pneu =" + ed.getOid_Movimento_Pneu ();

			executaSQL.executarUpdate (sql);
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
					"altera()");
		}
	}


	public ArrayList lista (Movimento_PneuED ed) throws Excecoes {

		String sql = null;
		ArrayList lista = new ArrayList ();
		try {

			sql = " SELECT * " +
			" FROM Movimentos_Pneus, Pneus " ;
			if (doValida (ed.getOid_Veiculo ())) {
				sql += ",Veiculos ";
			}
			sql +=" WHERE Movimentos_Pneus.oid_Pneu = Pneus.oid_Pneu";

			if (ed.getOid_Movimento_Pneu () > 0) {
				sql += "   AND Movimentos_Pneus.oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
			}
			if (doValida (ed.getOid_Veiculo ())) {
				sql += "   AND Movimentos_Pneus.oid_Veiculo = Veiculos.oid_Veiculo" +
				"   AND Movimentos_Pneus.oid_Veiculo = '" + ed.getOid_Veiculo () + "'";
			}
			if (ed.getOid_Pneu () > 0) {
				sql += "   AND Movimentos_Pneus.oid_Pneu = " + ed.getOid_Pneu ();
			}
			sql += " ORDER BY Movimentos_Pneus.oid_Movimento_Pneu DESC";

			ResultSet res = this.executaSQL.executarConsulta (sql);

			//popula
			while (res.next ()) {
					Movimento_PneuED edVolta = populaRegistro(res);

					lista.add (edVolta);
			}
			return lista;
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
					"lista()");
		}
	}

	public ArrayList lista_nalthus (Movimento_PneuED ed) throws Excecoes {

		String sql = null;
		ArrayList lista = new ArrayList ();
		try {

			sql = " SELECT * " +
			" FROM Movimentos_Pneus, Pneus " ;
			if (doValida (ed.getOid_Veiculo ())) {
				sql += ",Veiculos ";
			}
			sql +=" WHERE Movimentos_Pneus.oid_Pneu = Pneus.oid_Pneu";

			if (ed.getOid_Movimento_Pneu () > 0) {
				sql += "   AND Movimentos_Pneus.oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
			}
			if (doValida (ed.getOid_Veiculo ())) {
				sql += "   AND Movimentos_Pneus.oid_Veiculo = Veiculos.oid_Veiculo" +
				"   AND Movimentos_Pneus.oid_Veiculo = '" + ed.getOid_Veiculo () + "'";
			}
			if (ed.getOid_Pneu () > 0) {
				sql += "   AND Movimentos_Pneus.oid_Pneu = " + ed.getOid_Pneu ();
			}
			sql += " ORDER BY Movimentos_Pneus.oid_Movimento_Pneu DESC";

			ResultSet res = this.executaSQL.executarConsulta (sql);

			//popula
			while (res.next ()) {
					Movimento_PneuED edVolta = populaRegistro2(res);

					lista.add (edVolta);
			}
			return lista;
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
					"lista()");
		}
	}

	public Movimento_PneuED getByRecord (Movimento_PneuED ed) throws Excecoes {

		Iterator iterator = this.lista (ed).iterator ();
		if (iterator.hasNext ()) {
			return (Movimento_PneuED) iterator.next ();
		}
		else {
			return new Movimento_PneuED ();
		}
	}


	public ArrayList lista1 (Movimento_PneuED ed) throws Excecoes {

	String sql = null;
		ArrayList lista = new ArrayList ();
		try {
			sql = "SELECT " +
			"mv.*, " +
			"ps.nr_fogo as nr_Fogo_Saida, " +
			"pe.nr_fogo as nr_Fogo_Entrada, " +
			"ve.nr_frota as nr_Frota " +
			"FROM " +
			"Movimentos_Pneus as mv " +
			"left join Pneus as pe on mv.oid_pneu = pe.oid_pneu " +
			"left join Pneus as ps on mv.oid_pneu_colocado = ps.oid_pneu "+
			"left join Veiculos as ve on mv.oid_veiculo = ve.oid_veiculo "+
			"WHERE 1=1 ";
			if (ed.getOid_Movimento_Pneu () > 0)
				sql += " and oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
			else {
				if (doValida (ed.getOid_Veiculo ()))
					sql += " and mv.oid_Veiculo = '" + ed.getOid_Veiculo () + "'";
				if (ed.getOid_Pneu () > 0)
					sql += " and mv.oid_Pneu = " + ed.getOid_Pneu ();
				if (ed.getOid_Pneu_Colocado() > 0 )
					sql += " and mv.oid_Pneu_Colocado = " + ed.getOid_Pneu_Colocado();
				if (doValida(ed.getDm_Posicao()))
					sql += " and mv.dm_Posicao = '" + ed.getDm_Posicao() + "' ";
				if (doValida(ed.getDm_Posicao_Destino()))
					sql += " and mv.dm_Posicao_Destino = '" + ed.getDm_Posicao_Destino() + "' ";
				if (ed.getNr_Odometro_Entrada()>0)
					sql += " and mv.nr_Odometro_Entrada = " + ed.getNr_Odometro_Entrada() + " ";
				if (ed.getNr_Odometro_Saida()>0)
					sql += " and mv.nr_Odometro_Saida = " + ed.getNr_Odometro_Saida() + " ";
				if (doValida(ed.getDm_Tipo_Movimento()))
					sql += " and mv.dm_Tipo_Movimento = '" + ed.getDm_Tipo_Movimento() + "' ";
				if (doValida(ed.getDt_Inicio())) {
					sql += " and mv.dt_Entrada >= '" + ed.getDt_Inicio()+ "' ";
				}
				if (doValida(ed.getDt_Fim())) {
					sql += " and mv.dt_Entrada <= '" + ed.getDt_Fim()+ "' ";
				}
				sql += " ORDER BY  " ;
				if ("R".equals(ed.getDm_Tipo_Movimento()))
					sql += " dt_entrada desc , nr_km_acumulada_entrada desc ";
				else if (ed.getOid_Pneu() > 0 )
					sql += " pe.nr_fogo, mv.nr_km_acumulada_entrada " ;
				else if (ed.getOid_Pneu_Colocado() > 0 )
					sql += " ps.nr_fogo, mv.nr_km_acumulada_saida " ;
				else if ( doValida ( ed.getOid_Veiculo () ) && ( ed.getOid_Pneu_Colocado() == 0 && ed.getOid_Pneu() == 0 ) )
					sql += " dt_entrada desc , nr_km_acumulada_entrada desc ";

			}
			System.out.println("mov: "+sql);
			ResultSet res = this.executaSQL.executarConsulta (sql);

			while (res.next ()) {
				lista.add (populaRegistro(res));
			}
			return lista;
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista1()");
		}
	}


	public ArrayList lista2 (Movimento_PneuED ed) throws Excecoes {

	String sql = null;
		ArrayList lista = new ArrayList ();
		try {
			sql = "SELECT " +
			"nr_fogo, " +
			"nr_frota, " +
			"dt_nota_fiscal," +
			"d.nm_dimensao_pneu," +
			"f.nm_fabricante_pneu," +
			"m.nm_modelo_pneu," +
			"fo.nm_razao_social," +
			"mp.dt_entrada, " +
			"mp.dm_posicao, " +
			" case" +
			" 	when mp.nr_vida = 0 then 'Novo'" +
			"   when mp.nr_vida = 1 then '1º Rec'" +
			"   when mp.nr_vida = 2 then '2º Rec'" +
			"   when mp.nr_vida = 3 then '3º Rec'" +
			"   when mp.nr_vida = 4 then '4º Rec'" +
			"   when mp.nr_vida = 5 then '5º Rec'" +
			"   when mp.nr_vida = 6 then '6º Rec'" +
			" end as nm_Vida," +
			" case " +
			"   when oid_fornecedor_recapagem is not null then vl_negociado_recapagem" +
			"   else vl_preco" +
			" end as preco " +
			"FROM " +
			"movimentos_pneus mp, " +
			"pneus p, " +
			"veiculos v, "+
			"dimensoes_pneus d," +
			"fabricantes_pneus f," +
			"modelos_pneus m," +
			"fornecedores fo "+
			"WHERE " +
			"mp.oid_pneu = p.oid_pneu " +
			"AND " +
			"mp.oid_veiculo = v.oid_veiculo " +
			"AND " +
			"p.oid_dimensao_pneu = d.oid_dimensao_pneu " +
			"AND " +
			"p.oid_fabricante_pneu = f.oid_fabricante_pneu " +
			"AND " +
			"p.oid_modelo_pneu = m.oid_modelo_pneu " +
			"AND " +
			"p.oid_fornecedor = fo.oid_fornecedor " +
			"AND " +
			"mp.dt_entrada is not null ";
			if (ed.getOid_Movimento_Pneu () > 0)
				sql += " AND oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
			else {
				  if (ed.getOid_Empresa()> 0 ){
					  sql += " AND mp.oid_Empresa = " + ed.getOid_Empresa() ;
				  }
				  if (doValida(ed.getDt_Inicial())) {
					  sql += " AND mp.dt_entrada >= '" + ed.getDt_Inicial()+ "' ";
				  }
				  if (doValida(ed.getDt_Final())) {
					  sql += " AND mp.dt_entrada <= '" + ed.getDt_Final()+ "' ";
				  }
			}
			sql += " ORDER BY" +
				   " mp.dt_entrada";

			ResultSet res = this.executaSQL.executarConsulta (sql);
			while (res.next ()) {
				  Movimento_PneuED popula = new Movimento_PneuED ();
					popula.setNr_Fogo(res.getString("nr_fogo"));
					popula.setNm_Vida(res.getString("nm_vida"));
					popula.setVl_Pneu(res.getDouble("preco"));
					popula.setDt_Nota_Fiscal(FormataData.formataDataBT(res.getString("dt_nota_fiscal")));
					popula.setNm_Razao_Social(res.getString("nm_razao_social"));
					popula.setNm_Dimensao_Pneu(res.getString("nm_dimensao_pneu"));
					popula.setNm_Fabricante_Pneu(res.getString("nm_fabricante_pneu"));
					popula.setNm_Modelo_Pneu(res.getString("nm_modelo_pneu"));
					popula.setDt_Entrada(FormataData.formataDataBT(res.getString ("dt_entrada")));
					popula.setNr_Frota(res.getString ("nr_frota"));
					popula.setDm_Posicao(res.getString("dm_posicao"));
				  lista.add(popula);
			}
			return lista;
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
		}
	}

	public ArrayList listaSaidaBaixa (Movimento_PneuED ed) throws Excecoes {

		String sql = null;
			ArrayList lista = new ArrayList ();
			try {
				sql = "Select " +
				"p.nr_Fogo, " +
				"d.nm_Dimensao_Pneu, " +
				"f.nm_Fabricante_Pneu	, " +
				"m.dt_Entrada, " +
				"m.dt_Saida, " +
				"m.nr_Odometro_Entrada, " +
				"m.nr_Odometro_Saida, " +
				"(m.nr_Odometro_Saida - m.nr_Odometro_Entrada) AS km_Veiculo, "+
				"v.nr_Frota, " +
				"m.dm_Posicao, " +
				"m.nr_Vida, " +
				"m.tx_Motivo " +

				"from " +
				"movimentos_pneus as m, " +
				"pneus as p, " +
				"dimensoes_pneus as d, " +
				"fabricantes_pneus as f, " +
				"veiculos as v "+

				"where " +
				"m.dm_Rodou_Pouco ='S' and " +
				"m.oid_Pneu = p.oid_Pneu and " +
				"p.oid_Dimensao_Pneu = d.oid_Dimensao_Pneu and " +
				"p.oid_Fabricante_Pneu = f.oid_Fabricante_Pneu and " +
				"m.oid_Veiculo = v.oid_Veiculo ";

				if (ed.getOid_Movimento_Pneu () > 0)
					sql += " and oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
				else {
					  if (ed.getOid_Empresa () > 0)  {
							sql += " and m.oid_Empresa = " + ed.getOid_Empresa ();
					  }
					  if (doValida(ed.getDt_Inicial())) {
						  sql += " and m.dt_saida >= '" + ed.getDt_Inicial()+ "' ";
					  }
					  if (doValida(ed.getDt_Final())) {
						  sql += " and m.dt_saida <= '" + ed.getDt_Final()+ "' ";
					  }
				}
				sql += " order by " +
					   " m.dt_entrada";
				ResultSet res = this.executaSQL.executarConsulta (sql);
				while (res.next ()) {
					  Movimento_PneuED popula = new Movimento_PneuED ();
						popula.setNr_Fogo(res.getString("nr_fogo"));
						popula.setNm_Dimensao_Pneu(res.getString("nm_dimensao_pneu"));
						popula.setNm_Fabricante_Pneu(res.getString("nm_fabricante_pneu"));
						popula.setDt_Entrada(FormataData.formataDataBT(res.getString ("dt_entrada")));
						popula.setDt_Saida(FormataData.formataDataBT(res.getString ("dt_saida")));
						popula.setNr_Odometro_Entrada(res.getDouble ("nr_Odometro_Entrada"));
						popula.setNr_Odometro_Saida(res.getDouble ("nr_Odometro_Saida"));
						popula.setNr_Km_Acumulada_Veiculo(res.getDouble ("Km_Veiculo"));
						popula.setNr_Frota(res.getString ("nr_frota"));
						popula.setDm_Posicao(res.getString("dm_posicao"));
						popula.setNr_Vida(res.getLong("nr_vida"));
						popula.setTx_Motivo(res.getString("tx_motivo"));
					  lista.add(popula);
				}
				return lista;
			}
			catch (Exception exc) {
				throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
			}
		}

	public ArrayList lista3 (Movimento_PneuED ed) throws Excecoes {

	String sql = null;
		ArrayList lista = new ArrayList ();
		try {
			sql = "SELECT " +
			"mv.*, " +
			"pe.nr_fogo," +
			"d.nm_dimensao_pneu," +
			"f.nm_fabricante_pneu," +
			"m.nm_modelo_pneu," +
			"fo.nm_razao_social," +
			" case " +
			"   when pe.oid_fornecedor_recapagem is not null then pe.vl_negociado_recapagem" +
			"   else pe.vl_preco" +
			" end as preco, " +
			"ve.nr_frota as nr_Frota " +
			"FROM " +
			"Movimentos_Pneus as mv " +
			"left join Pneus as pe on mv.oid_pneu = pe.oid_pneu " +
			"left join Pneus as ps on mv.oid_pneu_colocado = ps.oid_pneu "+
			"left join Veiculos as ve on mv.oid_veiculo = ve.oid_veiculo," +
			"dimensoes_pneus d," +
			"fabricantes_pneus f," +
			"modelos_pneus m," +
			"fornecedores fo "+
			"WHERE 1=1 " +
			"AND " +
			"mv.dt_saida is not null " +
			"AND " +
			"pe.oid_dimensao_pneu = d.oid_dimensao_pneu " +
			"AND " +
			"pe.oid_fabricante_pneu = f.oid_fabricante_pneu " +
			"AND " +
			"pe.oid_modelo_pneu = m.oid_modelo_pneu " +
			"AND " +
			"pe.oid_fornecedor = fo.oid_fornecedor " ;

			if (ed.getOid_Movimento_Pneu () > 0)
				sql += " and oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
			else {
				if (ed.getOid_Empresa() > 0)
					sql += " AND mv.oid_Empresa = " + ed.getOid_Empresa();
			    if (doValida(ed.getDt_Inicial_Saida()))
					sql += " AND mv.dt_saida >= '" + ed.getDt_Inicial_Saida()+ "' ";
				if (doValida(ed.getDt_Final_Saida()))
				    sql += " AND mv.dt_saida <= '" + ed.getDt_Final_Saida()+ "' ";
				sql += " ORDER BY  " ;
				sql += " mv.dt_saida,pe.nr_fogo ";
			}

			ResultSet res = this.executaSQL.executarConsulta (sql);
			while (res.next ()) {
				Movimento_PneuED popula = new Movimento_PneuED ();
				popula.setNr_Fogo(res.getString("nr_fogo"));
				popula.setVl_Pneu(res.getDouble("preco"));
				popula.setNm_Razao_Social(res.getString("nm_razao_social"));
				popula.setNm_Dimensao_Pneu(res.getString("nm_dimensao_pneu"));
				popula.setNm_Fabricante_Pneu(res.getString("nm_fabricante_pneu"));
				popula.setNm_Modelo_Pneu(res.getString("nm_modelo_pneu"));
				popula.setNr_Frota(res.getString ("nr_frota"));
				popula.setDm_Posicao(res.getString("dm_posicao"));
				popula.setDt_Saida(FormataData.formataDataBT(res.getString ("dt_saida")));
				popula.setNr_Odometro_Saida(res.getDouble("nr_odometro_saida"));
				popula.setNr_Km_Acumulada_Saida(res.getDouble("nr_km_acumulada_saida"));
				popula.setTx_Motivo(res.getString("tx_motivo"));
				popula.setDm_Posicao(res.getString("dm_posicao"));
				popula.setNr_Vida(res.getInt("nr_Vida"));
				lista.add(popula);
			}
			return lista;
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista3()");
		}
	}

	public Movimento_PneuED getByRecord1 (Movimento_PneuED ed) throws Excecoes {

		Iterator iterator = this.lista1 (ed).iterator ();
		if (iterator.hasNext ()) {
			return (Movimento_PneuED) iterator.next ();
		}
		else {
			return new Movimento_PneuED ();
		}
	}

	/**
	 * Retorna o último movimento selecionado
	 * @param ed
	 * @return
	 * @throws Excecoes
	 */
	public Movimento_PneuED getUltimoMovimento (Movimento_PneuED ed) throws Excecoes {
		ArrayList lstMov = new ArrayList();
		Movimento_PneuED movPneuED = new Movimento_PneuED();
		lstMov = this.lista1(ed);
		for (int i=0; i<lstMov.size(); i++){
			movPneuED = (Movimento_PneuED) lstMov.get(i);
		}
		return movPneuED;
	}

	/**
	 * Completa o registro de movimentação com os dados da saida e com o pneu que entrou
	 * @param ed
	 * @throws Excecoes
	 */
	public void registraSaida (Movimento_PneuED ed) throws Excecoes {
		String sql = null;
		double kmMedia =0;
		try {
			// para o Laszlo - Busca a km media de pneus iguais na paosicao só faz isso para Troca ( finalizada )
			if (ed.getOid_Empresa()>0) {
				ed.setDm_Rodou_Pouco("N");
				if ("T".equals(ed.getDm_Tipo_Movimento())) {
					kmMedia = this.getMedia(ed.getOid_Pneu(), ed.getDm_Posicao());
					ed.setNr_Media_Comparacao(kmMedia);
					if ((ed.getNr_Km_Acumulada_Saida() - ed.getNr_Mm_Entrada()) < (kmMedia * 0.75)){
						ed.setDm_Rodou_Pouco("S");
					}
				}
			}

			sql = "UPDATE " +
				  " Movimentos_Pneus " +
				  " SET " +
				  " dt_Saida = '" + ed.getDt_Saida() + "' " +
				  ",nr_Odometro_Saida = " + ed.getNr_Odometro_Saida() +
				  ",nr_Km_Acumulada_Saida = " + ed.getNr_Km_Acumulada_Saida() +
				  ",nr_Mm_Saida = " + ed.getNr_Mm_Saida() +
				  ",oid_Pneu_Colocado = " + ed.getOid_Pneu_Colocado() +
				  ",dm_Posicao_Destino = '" + ed.getDm_Posicao_Destino() + "' " +
				  ",dm_Tipo_Movimento = '" + ed.getDm_Tipo_Movimento() + "' " +
				  ",tx_Motivo = '" + ed.getTx_Motivo() + "' " +
				  ",dm_rodou_pouco = '" +ed.getDm_Rodou_Pouco() + "' " +
				  ",nr_media_comparacao =" + ed.getNr_Media_Comparacao() +
				  " WHERE " +
				  " oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
//			System.out.println(sql);
			executaSQL.executarUpdate (sql);
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,	"registraSaida()");
		}
	}

	/**
	 * Desfaz o movimento devido a destroca ou desmontagem
	 * @param ed
	 * @param tipo - DT = Destroca, DM = Desmontagem
	 * @throws Excecoes
	 */
	public void desfazMovimento (Movimento_PneuED ed,String tipo) throws Excecoes {

		String sql = null;
		try {
			if ("DM".equals(tipo) || "DT".equals(tipo) || "DR".equals(tipo) ) {
				// Apaga o registro de movimento do pneu que foi Colocado
				sql = "DELETE from " +
					  " Movimentos_Pneus " +
				      " WHERE " +
				      " oid_Pneu = " + ( "DT".equals(tipo)? ed.getOid_Pneu_Colocado() : ed.getOid_Pneu() ) +
				      " and oid_Veiculo = '" + ed.getOid_Veiculo() + "' " +
				      " and dm_Posicao = '" + ed.getDm_Posicao() + "' " +
				      " and nr_odometro_entrada = " + ("DM".equals(tipo) ? ed.getNr_Odometro_Entrada() : ed.getNr_Odometro_Saida()) ;
				System.out.println(sql);
				executaSQL.executarUpdate (sql);
			}
			if ("DT".equals(tipo) || "DR".equals(tipo) ) {
				// Apaga os dados do pneu que foi Colocado no movimento
				sql = " UPDATE " +
					  " Movimentos_Pneus " +
					  " SET " +
					  " dt_Saida = null " +
					  ",nr_Odometro_Saida = null " +
					  ",nr_Km_Acumulada_Saida = null " +
					  ",nr_Mm_Saida = null " +
					  ",oid_Pneu_Colocado = null " +
					  ",dm_Posicao_Destino = null " +
					  ",dm_Tipo_Movimento = 'N' " +
					  ",tx_Motivo = null " +
					  " WHERE " +
					  " oid_Movimento_Pneu = " + ed.getOid_Movimento_Pneu ();
				System.out.println(sql);
				executaSQL.executarUpdate (sql);
			}
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,	"registraSaida()");
		}
	}

	/**
	 * Pega a média que pneus na mesma dimensão rodaram na posição informada
	 * @param oidPn - Oid do pneu -m Para buscar a dimensao
	 * @param pos - Posição do pneu retirado
	 * @return - média rodada na posição (todos os pneus da mesma dimensão)
	 * @throws SQLException
	 * @throws Exception
	 * @throws Excecoes
	 */
	private double getMedia(long oidPn, String pos) throws SQLException, Exception, Excecoes {

		String sql;
		double kmMedia=0;
		PneuED pnED = new PneuED();
		pnED.setOid_Pneu((int)oidPn);
		try {
		// Pega o oid da dimensão do pneu
		long oidDP = new PneuBD(this.sql).getByRecordJSTL(pnED).getOid_Dimensao_Pneu();

		// Busca a média rodada na mesma posicao
		sql ="SELECT " +
			 "avg(m.nr_km_acumulada_saida - m.nr_km_acumulada_entrada) as nr_km_no_veic " +
			 "from " +
			 "movimentos_pneus as m , " +
			 "pneus as p " +
			 "where " +
			 "m.dt_saida is not null and " +
			 "m.oid_pneu = p.oid_pneu and " +
			 "p.oid_dimensao_pneu = "+ oidDP +" and " +
			 "m.dm_posicao = '" + pos + "' and " +
			 "m.dm_tipo_movimento = 'T'" ;
		ResultSet res = this.executaSQL.executarConsulta (sql);
		if (res.next ())
			kmMedia= res.getDouble("nr_km_no_veic");
		else
			kmMedia=0;
		}
		catch (Exception exc) {
			throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,	"getMedia(long oidPn, String pos))") ;
		}

		// pneus colocados
		return kmMedia;
	}


	/**
	 * populaRegistro
	 */
	private Movimento_PneuED populaRegistro(ResultSet res) throws SQLException, Exception, Excecoes {
		Movimento_PneuED edVolta = new Movimento_PneuED ();
		// pneus colocados
		//edVolta.setNr_Fogo(res.getString("nr_Fogo_Entrada"));
		// outro
		edVolta.setOid_Movimento_Pneu (res.getInt ("oid_Movimento_Pneu"));
		edVolta.setOid_Veiculo (res.getString ("oid_Veiculo"));
		edVolta.setOid_Pneu (res.getInt ("oid_Pneu"));
		edVolta.setOid_Pneu_Colocado (res.getLong ("oid_Pneu_Colocado"));
		edVolta.setOid_Empresa (res.getLong ("oid_Empresa"));
		edVolta.setOid_Local_Estoque(res.getLong ("oid_Local_Estoque"));
		edVolta.setDt_Entrada (FormataData.formataDataBT(res.getString ("dt_Entrada")));
		edVolta.setNr_Odometro_Entrada (res.getDouble ("nr_Odometro_Entrada"));
		edVolta.setNr_Km_Acumulada_Entrada (res.getDouble ("nr_Km_Acumulada_Entrada"));
		edVolta.setDt_Saida (FormataData.formataDataBT(res.getString ("dt_Saida")));
		edVolta.setNr_Odometro_Saida (res.getDouble ("nr_Odometro_Saida"));
		edVolta.setNr_Km_Acumulada_Saida (res.getDouble ("nr_Km_Acumulada_Saida"));
		edVolta.setDm_Posicao (res.getString ("dm_Posicao"));
		edVolta.setDm_Eixo (res.getString ("dm_Eixo"));
		edVolta.setTx_Motivo(doValida(res.getString("tx_Motivo"))?res.getString("tx_Motivo"):"");
		edVolta.setNr_Vida (res.getInt ("nr_Vida"));
		edVolta.setNr_Odometro_Veiculo (res.getDouble ("nr_Odometro_Veiculo"));
		edVolta.setNr_Km_Acumulada_Veiculo (res.getDouble ("nr_Km_Acumulada_Veiculo"));
		edVolta.setNr_Media_Comparacao (res.getDouble ("nr_Media_Comparacao"));
		edVolta.setDm_Rodou_Pouco (res.getString ("dm_Rodou_Pouco"));
		edVolta.setNr_Fogo_Saida(res.getString ("nr_Fogo_Saida"));
		edVolta.setNr_Fogo_Entrada(res.getString ("nr_Fogo_Entrada"));
		edVolta.setNr_Mm_Saida(res.getDouble ("nr_Mm_Saida"));
		edVolta.setNr_Mm_Entrada(res.getDouble ("nr_Mm_Entrada"));
		edVolta.setNr_Frota(res.getString ("nr_Frota"));
		edVolta.setDm_Posicao_Destino(res.getString ("dm_Posicao_Destino"));
		edVolta.setDm_Tipo_Movimento(res.getString ("dm_Tipo_Movimento"));
		if (edVolta.getNr_Km_Acumulada_Saida()>0){
			edVolta.setNr_Km_Veiculo((edVolta.getNr_Km_Acumulada_Saida()) - (edVolta.getNr_Km_Acumulada_Entrada()));
		}


		return edVolta;
	}

	private Movimento_PneuED populaRegistro2(ResultSet res) throws SQLException, Exception, Excecoes {
		Movimento_PneuED edVolta = new Movimento_PneuED ();
		// pneus colocados
		//edVolta.setNr_Fogo(res.getString("nr_Fogo_Entrada"));
		// outro
		edVolta.setKM_Pneu(res.getInt ("km_pneu"));
		edVolta.setTX_Observacao(res.getString ("tx_observacao"));
		edVolta.setDM_Localizacao(res.getString ("dm_localizacao"));
		edVolta.setDM_Situacao(res.getString ("dm_situacao"));
		edVolta.setDT_Inclusao(FormataData.formataDataBT(res.getString ("dt_inclusao")));
		edVolta.setDT_Remocao(FormataData.formataDataBT(res.getString ("dt_remocao")));
		edVolta.setKM_Inicial(res.getInt ("km_inicial"));
		edVolta.setKM_Final(res.getInt ("km_final"));
		edVolta.setOid_Veiculo(res.getString ("oid_veiculo"));
		edVolta.setNr_Frota(res.getString ("oid_veiculo"));

		return edVolta;
	}

}