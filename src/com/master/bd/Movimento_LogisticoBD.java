package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.master.ed.Movimento_LogisticoED;
import com.master.root.UnidadeBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;


public class Movimento_LogisticoBD {

	private ExecutaSQL executasql;

	public Movimento_LogisticoBD(ExecutaSQL sql) {
		this.executasql = sql;
	}

/********************************************************
 *
 *******************************************************/
	public Movimento_LogisticoED inclui(Movimento_LogisticoED ed) throws Excecoes{
		String sql = null;
		long valOid = 0;
		Movimento_LogisticoED movimentoED  = new Movimento_LogisticoED();
		String chave_ordem = "";
		try{
			ResultSet rs = executasql.executarConsulta("select (oid_movimento_logistico) as result from movimentos_logisticos order by oid_movimento_logistico desc limit 1");
			//pega proximo valor da chave
			while (rs.next())
				valOid = rs.getInt("result");
			valOid = valOid+1;

			chave_ordem = ed.getDm_chave_ordem();

			sql = " insert into Movimentos_Logisticos (" +
				  " oid_movimento_logistico, " +
				  " oid_conhecimento, " +
				  " dt_hr_movimento, " +
				  " dm_tipo_movimento, " +
				  " oid_unidade_origem, " +
				  " oid_unidade_destino, " +
				  " nr_quantidade, " +
				  " oid_manifesto, " +
				  " oid_viagem," +
				  " oid_nota_fiscal, "+
				  " dt_hr_entrada_nota_fiscal, "+
				  " dt_hr_emissao_conhecimento, "+
				  " dt_hr_entrega, "+
				  " dt_stamp, " +
				  " usuario_stamp, " +
				  " dm_stamp, " +
				  " dm_chave_ordem " +
				  " ) values ( " +
				  valOid +
				  ", '" + ed.getOid_conhecimento() +
				  "'," + (FormataData.formataDataHoraTB(ed.getDt_hr_movimento())==""?"null":"'"+FormataData.formataDataHoraTB(ed.getDt_hr_movimento())+"'") +
				  ",'" + ed.getDm_tipo_movimento() +
				  "', " + ed.getOid_unidade_origem() +
				  " , " + ed.getOid_unidade_destino() +
				  " , " + ed.getNr_quantidade() +
				  ", '" + ed.getOid_manifesto() +
				  "','" + ed.getOid_viagem() +
				  "','" + ed.getOid_nota_fiscal() +
				  "'," + (FormataData.formataDataHoraTB(ed.getDt_hr_entrada_nota_fiscal())==""?"null":"'"+FormataData.formataDataHoraTB(ed.getDt_hr_entrada_nota_fiscal())+"'") +
				  "," + (FormataData.formataDataHoraTB(ed.getDt_hr_emissao_conhecimento())==""?"null":"'"+FormataData.formataDataHoraTB(ed.getDt_hr_emissao_conhecimento())+"'") +
				  "," + (FormataData.formataDataHoraTB(ed.getDt_hr_entrega())==""?"null":"'"+FormataData.formataDataHoraTB(ed.getDt_hr_entrega())+"'") +
				  ",'" + ed.getDt_stamp() +
				  "','" + ed.getUsuario_Stamp() +
				  "','" + ed.getDm_Stamp() +
				  "','" + chave_ordem +
				  "' )";

//			 System.err.println("inc mov: " +sql);
			executasql.executarUpdate(sql);
			movimentoED.setOid_movimento_logistico(valOid);
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de inclusao.", exc, this.getClass().getName(), "inclui()");
		}
		return movimentoED;
	}

/********************************************************
 *
 *******************************************************/
	public void altera(Movimento_LogisticoED ed) throws Excecoes{
		String sql = null;
		try{
			sql =  " update movimentos_logisticos set ";
			sql += " dt_hr_movimento = '" + FormataData.formataDataHoraTB(ed.getDt_hr_movimento()) + "', ";
			sql += " dm_tipo_movimento = '" + ed.getDm_tipo_movimento() + "', ";
			sql += " oid_unidade_origem = " + ed.getOid_unidade_origem() + ", ";
			sql += " oid_unidade_destino = " + ed.getOid_unidade_destino() + ", ";
			sql += " nr_quantidade = " + ed.getNr_quantidade() + ", ";
			sql += " oid_manifesto = '" + ed.getOid_manifesto() + "', ";
			sql += " oid_viagem = '" + ed.getOid_viagem() + "', ";
			sql += " oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "', ";
			sql += " dt_hr_entrada_nota_fiscal = '" + FormataData.formataDataHoraTB(ed.getDt_hr_entrada_nota_fiscal()) + "', ";
			sql += " dt_hr_emissao_conhecimento = '" + FormataData.formataDataHoraTB(ed.getDt_hr_emissao_conhecimento()) + "', ";
			sql += " dt_hr_entrega = '" + FormataData.formataDataHoraTB(ed.getDt_hr_entrega()) + "', ";
			sql += " dt_stamp = '" + ed.getDt_stamp() + "', ";
			sql += " usuario_stamp = '" + ed.getUsuario_Stamp() + "', ";
			sql += " dm_stamp = '" + ed.getDm_Stamp() + "' ";
			sql += " where oid_movimento_logistico = " + ed.getOid_movimento_logistico();

			executasql.executarUpdate(sql);
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de alteracao.", exc, this.getClass().getName(), "altera()");
		}
	}

/********************************************************
 *
 *******************************************************/
	public void deleta(Movimento_LogisticoED ed) throws Excecoes{
		String sql = null;
		try{
			sql = "delete from movimentos_logisticos WHERE oid_movimento_logistico = "+ed.getOid_movimento_logistico();
			executasql.executarUpdate(sql);
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de exclusao.", exc, this.getClass().getName(), "deleta()");
		}
	}

/********************************************************
 *
 *******************************************************/
	public ArrayList lista(Movimento_LogisticoED ed)throws Excecoes{
		String sql = null;
		ArrayList list = new ArrayList();
		try{
			sql =  "select movimentos_logisticos.*, notas_fiscais.nr_nota_fiscal " +
				   " from movimentos_logisticos, notas_fiscais " +
				   " where movimentos_logisticos.oid_nota_fiscal = notas_fiscais.oid_nota_fiscal";

			if (JavaUtil.doValida(String.valueOf(ed.getOid_movimento_logistico()))){
				sql += " and movimentos_logisticos.oid_movimento_logistico = " + ed.getOid_movimento_logistico() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_unidade_origem()))){
				sql += " and movimentos_logisticos.oid_unidade_origem = " + ed.getOid_unidade_origem() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_unidade_destino()))){
				sql += " and movimentos_logisticos.oid_unidade_destino = " + ed.getOid_unidade_destino() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_conhecimento()))){
				sql += " and movimentos_logisticos.oid_conhecimento = '" + ed.getOid_conhecimento() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_nota_fiscal()))){
				sql += " and movimentos_logisticos.oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_manifesto()))){
				sql += " and movimentos_logisticos.oid_manifesto = '" + ed.getOid_manifesto() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_viagem()))){
				sql += " and movimentos_logisticos.oid_viagem = '" + ed.getOid_viagem() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getDm_tipo_movimento())) &&  !"T".equals(ed.getDm_tipo_movimento())){
				sql += " and movimentos_logisticos.dm_tipo_movimento = '" + ed.getDm_tipo_movimento() + "'";
			}
			sql += " order by movimentos_logisticos.oid_nota_fiscal, movimentos_logisticos.dm_chave_ordem, movimentos_logisticos.dt_hr_entrada_nota_fiscal ";
// System.err.println(sql);
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			//popula
			while (res.next()){
				Movimento_LogisticoED edVolta = new Movimento_LogisticoED();
				edVolta.setOid_movimento_logistico(res.getLong("Oid_movimento_logistico"));
				edVolta.setOid_conhecimento(res.getString("Oid_conhecimento"));
				edVolta.setOid_nota_fiscal(res.getString("Oid_nota_fiscal"));
				edVolta.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
				edVolta.setDt_hr_movimento(FormataData.strToDate(res.getString("dt_hr_movimento")));
				edVolta.setDt_hr_entrada_nota_fiscal(FormataData.strToDate(res.getString("dt_hr_entrada_nota_fiscal")));
				edVolta.setDt_hr_emissao_conhecimento(FormataData.strToDate(res.getString("dt_hr_emissao_conhecimento")));
				edVolta.setDt_hr_entrega(FormataData.strToDate(res.getString("dt_hr_entrega")));
				edVolta.setDm_tipo_movimento(res.getString("dm_tipo_movimento"));
				edVolta.setOid_unidade_origem(res.getLong("Oid_unidade_origem"));
				edVolta.setCd_unidade_origem(UnidadeBean.getByOID_Unidade(res.getInt("Oid_unidade_origem")).getCD_Unidade());
				edVolta.setOid_unidade_destino(res.getLong("Oid_unidade_destino"));
				edVolta.setCd_unidade_destino(UnidadeBean.getByOID_Unidade(res.getInt("Oid_unidade_destino")).getCD_Unidade());
				edVolta.setNr_quantidade(res.getDouble("Nr_quantidade"));
				edVolta.setOid_manifesto(res.getString("Oid_manifesto"));
				edVolta.setNr_manifesto(" - ");
				sql = "SELECT nr_manifesto from manifestos where oid_manifesto = '" + res.getString("Oid_manifesto") + "'";
				ResultSet rs = this.executasql.executarConsulta(sql);
				if(rs.next())
					edVolta.setNr_manifesto(rs.getString(1));

				edVolta.setOid_viagem(res.getString("Oid_viagem"));
				list.add(edVolta);
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de Listagem.", exc, this.getClass().getName(), "lista()");
		}
		return list;
	}

/********************************************************
 *
 *******************************************************/
	public Movimento_LogisticoED getByRecord(Movimento_LogisticoED ed)throws Excecoes{
		Movimento_LogisticoED edVolta = new Movimento_LogisticoED();
		try{
			Iterator iterador = this.lista(ed).iterator();
			while(iterador.hasNext()){
				edVolta = (Movimento_LogisticoED)iterador.next();
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de Listagem.", exc, this.getClass().getName(), "lista()");
		}
		return edVolta;
	}

	public void geraRelatorio(Movimento_LogisticoED ed)throws Excecoes{

	}

	public void gera_Movimento(Movimento_LogisticoED ed) throws Excecoes{
		String sql = null;
		ResultSet res = null;
		Movimento_LogisticoED to_insert = new Movimento_LogisticoED();

		try{
			//deleta movimentos do CRT
			sql = "delete from movimentos_logisticos WHERE oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";
			this.executasql.executarUpdate(sql);
			//fim deleta

			//inclusao
			//Busca nota_fiscal
			sql = "Select oid_nota_fiscal, nr_nota_fiscal, oid_unidade, nr_volumes, " +
				  " dt_entrada from notas_fiscais where oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";
			res = this.executasql.executarConsulta(sql);
			if(res.next()){
				to_insert = new Movimento_LogisticoED();
				to_insert.setOid_nota_fiscal(res.getString(1));
				to_insert.setNr_nota_fiscal(res.getLong(2));
				to_insert.setDm_tipo_movimento("P");
				to_insert.setOid_unidade_origem(res.getLong(3));
				to_insert.setNr_quantidade(res.getDouble(4));
				to_insert.setDt_hr_entrada_nota_fiscal(res.getDate(5));
			}

//			Conhecimento
			sql = "Select ct.oid_conhecimento, ct.dt_emissao, " +
				  " ct.dt_entrega, ct.hr_entrega " +
				  " from conhecimentos ct, conhecimentos_notas_fiscais " +
				  " where ct.oid_conhecimento = conhecimentos_notas_fiscais.oid_conhecimento " +
				  " and conhecimentos_notas_fiscais.oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "' " +
				  " order by ct.dt_entrega asc, ct.hr_entrega asc ";
			res = this.executasql.executarConsulta(sql);
			while(res.next()){
				to_insert.setOid_conhecimento(res.getString(1));
				String data = FormataData.formataDataBT(res.getDate(2));
				if(JavaUtil.doValida(data)){
					to_insert.setDt_hr_emissao_conhecimento(Data.stringToCalendar(data, "dd/MM/yyyy").getTime());
				}
				data = FormataData.formataDataBT(res.getDate(3));
				String hora = res.getString(4);
				if(JavaUtil.doValida(hora) && JavaUtil.doValida(data)){
					data = data + hora;
					to_insert.setDt_hr_entrega(Data.stringToCalendar(data, "dd/MM/yyyyHH:mm").getTime());
				} else {
					to_insert.setDt_hr_entrega(new Date());
				}
				// System.out.println("VALIDOU? " + (JavaUtil.doValida(hora) && JavaUtil.doValida(data)));
				// System.out.println("DATA >>> " + to_insert.getDt_hr_movimento());
			}
//			fim Conhecimento
			this.inclui(to_insert);
//			fim inclusao

		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de geracao.", exc, this.getClass().getName(), "gera_Movimento()");
		}
	}

	public ArrayList dados_relatorio(Movimento_LogisticoED ed)throws Excecoes{
		String sql = null;
		ArrayList list = new ArrayList();
		String prefixo = "";
		String from = "";
		String where = "";
		String sufixo = "";
		try{
			prefixo =
				   "select movimentos_logisticos.*, notas_fiscais.nr_nota_fiscal, " +
				   " origem.cd_unidade as cd_origem, " +
				   " rem.nm_razao_social as remetente, dest.nm_razao_social as destinatario ";
			from =
				   " from movimentos_logisticos, notas_fiscais, unidades origem, pessoas rem, pessoas dest ";
			where =
				   " where movimentos_logisticos.oid_nota_fiscal = notas_fiscais.oid_nota_fiscal " +
				   " and movimentos_logisticos.oid_unidade_origem = origem.oid_unidade " +
				   " and notas_fiscais.oid_pessoa = rem.oid_pessoa " +
				   " and notas_fiscais.oid_pessoa_destinatario = dest.oid_pessoa ";

			if (JavaUtil.doValida(String.valueOf(ed.getOid_movimento_logistico()))){
				where += " and movimentos_logisticos.oid_movimento_logistico = " + ed.getOid_movimento_logistico() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_unidade_origem()))){
				where += " and movimentos_logisticos.oid_unidade_origem = " + ed.getOid_unidade_origem() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_unidade_destino()))){
				where += " and movimentos_logisticos.oid_unidade_destino = " + ed.getOid_unidade_destino() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_conhecimento()))){
				where += " and movimentos_logisticos.oid_conhecimento = '" + ed.getOid_conhecimento() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_nota_fiscal()))){
				where += " and movimentos_logisticos.oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_manifesto()))){
				where += " and movimentos_logisticos.oid_manifesto = '" + ed.getOid_manifesto() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_viagem()))){
				where += " and movimentos_logisticos.oid_viagem = '" + ed.getOid_viagem() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getDm_tipo_movimento())) &&  !"T".equals(ed.getDm_tipo_movimento())){
				where += " and movimentos_logisticos.dm_tipo_movimento = '" + ed.getDm_tipo_movimento() + "'";
			}

			if (JavaUtil.doValida(String.valueOf(ed.getNr_conhecimento()))){
				from += ", conhecimentos ";
				where += " AND movimentos_logisticos.oid_conhecimento = conhecimentos.oid_conhecimento ";
				where += " and Conhecimentos.Nr_conhecimento = '" + ed.getNr_conhecimento() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_pessoa()))){
				where += " and notas_fiscais.oid_pessoa = '" + ed.getOid_pessoa() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_pessoa_destinatario()))){
				where += " and notas_fiscais.oid_pessoa_destinatario = '" + ed.getOid_pessoa_destinatario() + "'";
			}

			if (JavaUtil.doValida(String.valueOf(ed.getDt_inicial()))){
				where += " and notas_fiscais.dt_emissao >= '" + ed.getDt_inicial() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getDt_final()))){
				where += " and notas_fiscais.dt_emissao <= '" + ed.getDt_final() + "'";
			}

			sufixo += " order by movimentos_logisticos.oid_nota_fiscal, movimentos_logisticos.dt_hr_entrada_nota_fiscal ";

			sql = prefixo+from+where+sufixo;
// System.err.println("DADOS TTIME: "+sql);
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			//popula
			while (res.next()){
				Movimento_LogisticoED edVolta = new Movimento_LogisticoED();
				edVolta.setOid_movimento_logistico(res.getLong("Oid_movimento_logistico"));
				edVolta.setOid_conhecimento(res.getString("Oid_conhecimento"));

				edVolta.setOid_nota_fiscal(res.getString("Oid_nota_fiscal"));
				edVolta.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
				edVolta.setDt_hr_movimento(FormataData.strToDate(res.getString("dt_hr_movimento")));
				edVolta.setDt_hr_entrada_nota_fiscal(FormataData.strToDate(res.getString("dt_hr_entrada_nota_fiscal")));
				edVolta.setDt_hr_emissao_conhecimento(FormataData.strToDate(res.getString("dt_hr_emissao_conhecimento")));
				edVolta.setDt_hr_entrega(FormataData.strToDate(res.getString("dt_hr_entrega")));
				edVolta.setDm_tipo_movimento(res.getString("dm_tipo_movimento"));
				edVolta.setOid_unidade_origem(res.getLong("Oid_unidade_origem"));
				edVolta.setOid_unidade_destino(res.getLong("Oid_unidade_destino"));
				edVolta.setNr_quantidade(res.getDouble("Nr_quantidade"));
				edVolta.setOid_manifesto(res.getString("Oid_manifesto"));
				edVolta.setNr_manifesto(" - ");
				sql = "SELECT nr_manifesto from manifestos where oid_manifesto = '" + res.getString("Oid_manifesto") + "'";
				ResultSet rs = this.executasql.executarConsulta(sql);
				if(rs.next())
					edVolta.setNr_manifesto(rs.getString(1));

				edVolta.setOid_viagem(res.getString("Oid_viagem"));

				sql = "SELECT nr_conhecimento from conhecimentos where Oid_conhecimento = '" + res.getString("Oid_conhecimento") + "'";
				rs = this.executasql.executarConsulta(sql);
				if(rs.next())
					edVolta.setNr_conhecimento(rs.getString(1));

				edVolta.setCd_unidade_origem(res.getString("cd_origem"));

				edVolta.setNm_pessoa(res.getString("remetente"));
				edVolta.setNm_pessoa_destinatario(res.getString("destinatario"));
				list.add(edVolta);
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes("Erro de Listagem.", exc, this.getClass().getName(), "dados_relatorio()");
		}
		return list;
	}

	public ArrayList getRegistros_para_geracao() throws Excecoes{

		ArrayList lista = new ArrayList();
		try{
			String sql = "Select oid_nota_fiscal from notas_fiscais " +
					" where dt_emissao >= '01/01/2008'";
			ResultSet res = this.executasql.executarConsulta(sql);
			while(res.next()){
				lista.add(res.getString(1));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Excecoes();
		}

		return lista;
	}

	public ArrayList getRegistrosByCtrc(Movimento_LogisticoED ed) throws Excecoes{

		ArrayList lista = new ArrayList();
		try{
			String sql = "Select oid_nota_fiscal from conhecimentos_notas_fiscais " +
						 " where conhecimentos_notas_fiscais = '" + ed.getOid_conhecimento() + "'";
			ResultSet res = this.executasql.executarConsulta(sql);
			while(res.next()){
				lista.add(res.getString(1));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Excecoes();
		}

		return lista;
	}

	public ArrayList getRegistros_para_geracao_via_Relatorio(Movimento_LogisticoED ed) throws Excecoes{

		ArrayList lista = new ArrayList();
		try{
			String sql = "Select oid_nota_fiscal " +
						 " from notas_fiscais " +
						 " where 1 = 1 ";
			if (JavaUtil.doValida(String.valueOf(ed.getOid_unidade_origem()))){
				sql += " and notas_fiscais.oid_unidade = " + ed.getOid_unidade_origem() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_pessoa()))){
				sql += " and notas_fiscais.oid_pessoa = '" + ed.getOid_pessoa() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOid_pessoa_destinatario()))){
				sql += " and notas_fiscais.oid_pessoa_destinatario = '" + ed.getOid_pessoa_destinatario() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getDt_inicial()))){
				sql += " and notas_fiscais.dt_emissao >= '" + ed.getDt_inicial() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getDt_final()))){
				sql += " and notas_fiscais.dt_emissao <= '" + ed.getDt_final() + "'";
			}
//			System.out.println("TTIME: "+sql);

			ResultSet res = this.executasql.executarConsulta(sql);
			while(res.next()){
				lista.add(res.getString(1));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Excecoes();
		}

		return lista;
	}

}