package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Simulacao_FreteED;
import com.master.root.CidadeBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Simulacao_FreteBD {

	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);
	Parametro_FixoED paramED = Parametro_FixoED.getInstancia();
	CidadeBean cidadeBean = new CidadeBean ();

	public Simulacao_FreteBD(ExecutaSQL sql) {
		this.executasql = sql;
	    util = new Utilitaria (this.executasql);
	}

	public Simulacao_FreteED inclui(Simulacao_FreteED ed) throws Excecoes
	{
		String sql = "";
		try{
			long id = util.getAutoIncremento("oid_simulacao", "simulacoes_fretes");
			if(!util.doExiste("simulacoes_fretes", "oid_simulacao = " + id)){
				ed.setOid_Simulacao(id);
				if(!JavaUtil.doValida(String.valueOf(ed.getNr_Simulacao())))
					ed.setNr_Simulacao(util.getAutoIncremento("nr_simulacao", "simulacoes_fretes"));

				sql = "INSERT into simulacoes_fretes " +
					  "( oid_simulacao, oid_cliente, oid_transportador, " +
					  " oid_origem, oid_destino, " +
					  " vl_mercadoria, vl_peso, nr_simulacao ) " +
					  " values " +
					  "("  + ed.getOid_Simulacao() + "" +
					  ",'" + ed.getOid_Pessoa_Cliente() + "'" +
					  ",'" + ed.getOid_Pessoa_Transportador() + "'" +
					  ","  + ed.getOid_Origem() + "" +
					  ","  + ed.getOid_Destino() + "" +
					  ","  + ed.getVl_Mercadoria() + "" +
					  ","  + ed.getVl_Peso() + "" +
					  ","  + ed.getNr_Simulacao() + "" +
					  ")";
				this.executasql.executarUpdate(sql);

			}

		}
		catch (Excecoes e){
			throw e;
		}
		catch (Exception e){
			throw new Excecoes("Erro ao incluir!",this.getClass().getName(),"inclui(Simulacao_FreteED ed)");
		}

		return ed;
	}

	public void update(Simulacao_FreteED ed) throws Excecoes
	{
		String sql = "";

		try{
			sql = "UPDATE simulacoes_fretes " +
				  " SET " +
				  "  oid_origem = " + ed.getOid_Origem() +
				  " ,oid_destino = " + ed.getOid_Destino() +
				  " ,oid_cliente = " + util.getSQLString(ed.getOid_Pessoa_Cliente()) +
				  " ,oid_transportador = " + util.getSQLString(ed.getOid_Pessoa_Transportador()) +
				  " ,vl_mercadoria = " + ed.getVl_Mercadoria() +
				  " ,vl_peso = " + ed.getVl_Peso() +
				  " ,vl_frete_peso = " + ed.getVl_Frete_Peso() +
				  " ,vl_frete_valor = " + ed.getVl_Frete_Valor() +
				  " ,vl_taxas = " + ed.getVl_Taxas() +
				  " ,vl_pedagio = " + ed.getVl_Pedagio() +
				  " ,vl_total_frete = " + ed.getVl_Total_Frete() +
				  " ,nr_quantidade_ctrc = " + ed.getNr_Quantidade_Ctrc() +
				  " WHERE oid_simulacao = " + ed.getOid_Simulacao();
			this.executasql.executarUpdate(sql);

		}
		catch (Excecoes e){
			throw e;
		}
		catch (Exception e){
			throw new Excecoes("Erro ao alterar!",this.getClass().getName(),"update(Simulacao_FreteED ed)");
		}

	}

	public void deleta(Simulacao_FreteED ed) throws Excecoes
	{
		String sql = "";
		try{
			sql = "DELETE from simulacoes_fretes " +
				  "WHERE oid_simulacao = " + ed.getOid_Simulacao();
			this.executasql.executarUpdate(sql);
		}
		catch (Excecoes e){
			throw e;
		}
		catch (Exception e){
			throw new Excecoes("Erro ao excluir!",this.getClass().getName(),"deleta(Simulacao_FreteED ed)");
		}
	}

	public void deleta(ArrayList lista) throws Excecoes
	{
		try{
			Iterator it = lista.iterator();
			while(it.hasNext()){
				Simulacao_FreteED ed = (Simulacao_FreteED)it.next();
				this.deleta(ed);
			}
		}
		catch (Excecoes e){
			throw e;
		}
		catch (Exception e){
			throw new Excecoes("Erro ao excluir!",this.getClass().getName(),"deleta(ArrayList lista)");
		}

	}

	public ArrayList lista(Simulacao_FreteED ed) throws Excecoes
	{
		ArrayList toReturn = new ArrayList();
		String sql = "";

		try{
			sql = "SELECT * from simulacoes_fretes " +
				  " WHERE 1 = 1 ";
			if(JavaUtil.doValida(ed.getOid_Pessoa_Cliente())){
				sql += " AND simulacoes_fretes.oid_cliente = '" + ed.getOid_Pessoa_Cliente() + "'";
			}
			if(JavaUtil.doValida(ed.getOid_Pessoa_Transportador())){
				sql += " AND simulacoes_fretes.oid_transportador = '" + ed.getOid_Pessoa_Transportador() + "'";
			}
			if(JavaUtil.doValida(String.valueOf(ed.getOid_Origem()))){
				sql += " AND simulacoes_fretes.oid_origem = " + ed.getOid_Origem();
			}
			if(JavaUtil.doValida(String.valueOf(ed.getOid_Destino()))){
				sql += " AND simulacoes_fretes.oid_destino = " + ed.getOid_Destino();
			}
			if(JavaUtil.doValida(String.valueOf(ed.getOid_Simulacao()))){
				sql += " AND simulacoes_fretes.oid_simulacao = " + ed.getOid_Simulacao();
			}
			if(JavaUtil.doValida(String.valueOf(ed.getNr_Simulacao()))){
				sql += " AND simulacoes_fretes.Nr_Simulacao = " + ed.getNr_Simulacao();
			}
			sql += " ORDER BY simulacoes_fretes.oid_cliente, simulacoes_fretes.Nr_Simulacao, AND simulacoes_fretes.oid_simulacao ";

			ResultSet res = this.executasql.executarConsulta(sql);
			while(res.next()){
				Simulacao_FreteED edVolta = new Simulacao_FreteED();
				edVolta.setOid_Simulacao(res.getLong("oid_simulacao"));
				edVolta.setNr_Simulacao(res.getLong("nr_simulacao"));
				edVolta.setVl_Mercadoria(res.getDouble("vl_mercadoria"));
				edVolta.setVl_Peso(res.getDouble("vl_peso"));
				edVolta.setVl_Frete_Peso(res.getDouble("vl_frete_peso"));
				edVolta.setVl_Frete_Valor(res.getDouble("vl_frete_valor"));
				edVolta.setVl_Taxas(res.getDouble("vl_taxas"));
				edVolta.setVl_Pedagio(res.getDouble("vl_pedagio"));
				edVolta.setVl_Total_Frete(res.getDouble("vl_total_frete"));
				edVolta.setNr_Quantidade_Ctrc(res.getLong("nr_quantidade_ctrc"));

				edVolta.setOid_Origem(res.getInt("oid_origem"));
				edVolta.setOid_Destino(res.getInt("oid_destino"));

				edVolta.setOid_Pessoa_Cliente(res.getString("oid_cliente"));
				edVolta.setOid_Pessoa_Transportador(res.getString("oid_transportador"));

				toReturn.add(edVolta);
			}

		}
		catch (Excecoes e){
			throw e;
		}
		catch (Exception e){
			throw new Excecoes("Erro ao listar!",this.getClass().getName(),"lista(Simulacao_FreteED ed)");
		}

		return toReturn;
	}

	public Simulacao_FreteED getByRecord(Simulacao_FreteED ed) throws Excecoes
	{
		Simulacao_FreteED toReturn = new Simulacao_FreteED();
		try {
			Iterator it = this.lista(ed).iterator();
			while(it.hasNext()){
				toReturn = (Simulacao_FreteED)it.next();
			}
		}
		catch (Excecoes e){
			throw e;
		}
		catch (Exception e){
			throw new Excecoes("Erro ao recuperar registro!",this.getClass().getName(),"getByRecord(Simulacao_FreteED ed)");
		}
		return toReturn;
	}

	public void calculaByTabela(Simulacao_FreteED ed) throws Excecoes
	{
		Simulacao_FreteED toCalc = new Simulacao_FreteED();
		try{
			Iterator it = this.lista(ed).iterator();
			while(it.hasNext()){
				toCalc = (Simulacao_FreteED)it.next();

				//aqui faz o cálculo do frete

				//aqui popula o toCalc com os valores localizados na tabela

				//aqui faz os updates
				this.update(toCalc);
			}

		}
		catch (Excecoes e){
			throw e;
		}
		catch (Exception e){
			throw new Excecoes("Erro ao efetuar calculo!",this.getClass().getName(),"calculaByTabela(Simulacao_FreteED ed)");
		}
	}

}
