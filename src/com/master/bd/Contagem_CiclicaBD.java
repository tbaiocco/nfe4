package com.master.bd;

/**
 * <p>Title: Contagem_CiclicaBD</p>
 * <p>Description: DAO para contagem ciclica, relativa a tabela contagens_ciclicas</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: SPMti</p>
 * @author Teofilo Poletto Baiocco - nuovonet
 * @version 0.1.0
 */

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import com.master.ed.Contagem_CiclicaED;
import com.master.ed.OperadorED;
import com.master.ed.Produto_Cliente_ContagemED;
import com.master.ed.WMS_DepositoED;
import com.master.ed.WMS_Tipo_EstoqueED;
import com.master.rn.OperadorRN;
import com.master.rn.WMS_DepositoRN;
import com.master.rn.WMS_Tipo_EstoqueRN;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Contagem_CiclicaBD {

	private ExecutaSQL executasql;
	DecimalFormat dec = new DecimalFormat("###,###,##0.00");

	public Contagem_CiclicaBD(ExecutaSQL sql) {
		this.executasql = sql;
	}

	public Contagem_CiclicaED inclui(Contagem_CiclicaED ed) throws Excecoes{

		String sql = null;
		long valOid = 1;

		Contagem_CiclicaED Contagem_CiclicaED = new Contagem_CiclicaED();

		try{
			sql = "select (oid_contagem_ciclica) as result from contagens_ciclicas order by oid_contagem_ciclica desc limit 1";
			ResultSet rs = this.executasql.executarConsulta(sql);
			while(rs.next())
				valOid = rs.getLong("result") + 1;
			ed.setOid_contagem_ciclica(valOid);

			sql = " insert into Contagens_Ciclicas (" +
				  " oid_contagem_ciclica, " +
				  " oid_pessoa, " +
				  " oid_tipo_estoque, " +
				  " oid_armazem, " +
				  " oid_operador, " +
				  " dt_inicio, " +
				  " dt_fim, " +
				  " dm_situacao, " +
				  " nr_qt_itens, " +
				  " nr_qt_itens_contados, " +
				  " tx_observacao, " +
				  " nr_dias_contagem_ciclica " +
				  " ) values ( ";
			sql += " " + ed.getOid_contagem_ciclica() + ", " +
				   "'" + ed.getOid_pessoa() + "', " +
				   " " + ed.getOid_tipo_estoque() + ", " +
				   " " + ed.getOid_armazem() + ", " +
				   " " + ed.getOid_operador() + ", " +
				   " " + JavaUtil.getSQLDate(ed.getDt_inicio()) + ", " +
				   " " + JavaUtil.getSQLDate(ed.getDt_fim()) + ", " +
				   "'" + ed.getDm_situacao() + "', " +
				   " " + ed.getNr_qt_itens() + ", " +
				   " " + ed.getNr_qt_itens_contados()+ ", " +
				   "'" + ed.getTx_observacao() + "', " +
				   " " + ed.getNr_dias_contagem_ciclica() + " )";
			int i = executasql.executarUpdate(sql);

			Contagem_CiclicaED.setOid_contagem_ciclica(ed.getOid_contagem_ciclica());

		} catch(Exception exc){
			exc.printStackTrace();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public Contagem_CiclicaED inclui(Contagem_CiclicaED ed)");
		}
		return Contagem_CiclicaED;
	}

	public void altera(Contagem_CiclicaED ed) throws Excecoes{

		String sql = null;
		try{

			sql = " update Contagens_Ciclicas set " +
				  " oid_pessoa = '" + ed.getOid_pessoa() + "' " +
				  " ,oid_tipo_estoque = " + ed.getOid_tipo_estoque() +
				  " ,oid_armazem = " + ed.getOid_armazem() +
				  " ,oid_operador = " + ed.getOid_operador() +
				  " ,dt_inicio = " + JavaUtil.getSQLDate(ed.getDt_inicio()) +
				  " ,dt_fim = " + JavaUtil.getSQLDate(ed.getDt_fim()) +
				  //" ,dm_situacao = '" + ed.getDm_situacao() + "' " +
				  " ,nr_qt_itens = " + ed.getNr_qt_itens() +
				  " ,nr_qt_itens_contados = " + ed.getNr_qt_itens_contados() +
				  " ,tx_observacao = '" + ed.getTx_observacao() + "' " +
				  " ,nr_dias_contagem_ciclica = " + ed.getNr_dias_contagem_ciclica();
			sql += " where oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();

			int i = executasql.executarUpdate(sql);

		} catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void altera(Contagem_CiclicaED ed)");
		}
	}

	public void deleta(Contagem_CiclicaED ed) throws Excecoes{

		String sql = null;
		try{
			sql = "delete from Contagens_Ciclicas where oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();

			int i = executasql.executarUpdate(sql);
		} catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void deleta(Contagem_CiclicaED ed)");
        }
	}

	public void encerra(Contagem_CiclicaED ed) throws Excecoes{

		String sql = null;
		try{

			sql = " SELECT COUNT(oid_produto_cliente_contagem) "+
            	  " from produtos_clientes_contagens "+
            	  " WHERE oid_contagem_ciclica = " + ed.getOid_contagem_ciclica() +
            	  " AND dm_contado != 'S' ";
			ResultSet res = null;
			res = executasql.executarConsulta(sql);
			if(res.next())
				throw new Excecoes("Não foram contados TODOS os ítens desta Contagem Cíclica!");

			sql = " SELECT sum(case when (produtos.nr_qt_contada!=produtos.nr_qt_estoque) then 1 else 0 end) as cont "+
	              " from produtos_clientes_contagens produtos"+
	              " WHERE produtos.oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();
			res = null;
			res = executasql.executarConsulta(sql);
			if(res.next())
				ed.setNr_acuracidade_contagem((res.getInt("cont")/ed.getNr_qt_itens())*100);

			sql = " update Contagens_Ciclicas set " +
				  " dm_situacao = 'E' " +
				  " ,nr_acuracidade_contagem = " + ed.getNr_acuracidade_contagem();
			sql += " where oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();
			int i = executasql.executarUpdate(sql);

		} catch(Excecoes exc){
			throw exc;
		} catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void encerra(Contagem_CiclicaED ed)");
		}
	}

 	public ArrayList lista(Contagem_CiclicaED ed) throws Excecoes{

 		String sql = null;
 		ArrayList list = new ArrayList();
 		try{
 			sql = " SELECT contagens.* "+
	              " from contagens_ciclicas contagens "+
	              " WHERE 1 = 1 ";
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_contagem_ciclica())))
 				sql +=  " AND contagens.oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();
 			if(JavaUtil.doValida(ed.getOid_pessoa()))
 				sql +=  " AND contagens.oid_pessoa = '" + ed.getOid_pessoa() + "'";
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_tipo_estoque())))
 				sql +=  " AND contagens.oid_tipo_estoque = " + ed.getOid_tipo_estoque();
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_armazem())))
 				sql +=  " AND contagens.oid_armazem = " + ed.getOid_armazem();
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_operador())))
 				sql +=  " AND contagens.oid_operador = " + ed.getOid_operador();
 			if(JavaUtil.doValida(ed.getDm_situacao()) && !ed.getDm_situacao().equals("T"))
 				sql +=  " AND contagens.dm_situacao = '" + ed.getDm_situacao() + "'";
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_qt_itens())))
 				sql +=  " AND contagens.Nr_qt_itens = " + ed.getNr_qt_itens();
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_qt_itens_contados())))
 				sql +=  " AND contagens.Nr_qt_itens_contados = " + ed.getNr_qt_itens_contados();
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_dias_contagem_ciclica())))
 				sql +=  " AND contagens.Nr_dias_contagem_ciclica = " + ed.getNr_dias_contagem_ciclica();
 			//DATAS
 			if(JavaUtil.doValida(ed.getDt_inicio()))
 				sql +=  " AND contagens.dt_inicio >= '" + ed.getDt_inicio() + "'";
 			if(JavaUtil.doValida(ed.getDt_inicio_final()))
 				sql +=  " AND contagens.dt_inicio <= '" + ed.getDt_inicio_final() + "'";
 			if(JavaUtil.doValida(ed.getDt_fim()))
 				sql +=  " AND contagens.dt_fim >= '" + ed.getDt_fim() + "'";
 			if(JavaUtil.doValida(ed.getDt_fim_final()))
 				sql +=  " AND contagens.dt_fim <= '" + ed.getDt_fim_final() + "'";

 			sql += " order by contagens.oid_contagem_ciclica ";
 			ResultSet res = null;
 			res = this.executasql.executarConsulta(sql);
 			FormataDataBean DataFormatada = new FormataDataBean();
 			while (res.next()){
 				Contagem_CiclicaED edVolta = new Contagem_CiclicaED();
 				edVolta.setOid_contagem_ciclica(res.getLong("oid_contagem_ciclica"));
 				edVolta.setOid_pessoa(res.getString("oid_pessoa"));
 				edVolta.setOid_tipo_estoque(res.getLong("oid_tipo_estoque"));
 				edVolta.setOid_armazem(res.getLong("oid_armazem"));
 				edVolta.setOid_operador(res.getLong("oid_operador"));
 				edVolta.setDt_inicio(DataFormatada.getDT_FormataData(res.getString("dt_inicio")));
 				edVolta.setDt_fim(DataFormatada.getDT_FormataData(res.getString("dt_fim")));
 				edVolta.setDm_situacao(res.getString("dm_situacao"));
 				edVolta.setNr_qt_itens(res.getLong("nr_qt_itens"));
 				edVolta.setNr_qt_itens_contados(res.getLong("nr_qt_itens_contados"));
 				edVolta.setTx_observacao(res.getString("tx_observacao"));
 				edVolta.setNr_dias_contagem_ciclica(res.getLong("nr_dias_contagem_ciclica"));
 				edVolta.setNr_acuracidade_contagem(res.getDouble("nr_acuracidade_contagem"));
 				edVolta = this.getDetalhes_FKs(edVolta);
 				list.add(edVolta);
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public ArrayList lista(Contagem_CiclicaED ed)");
 		}
 		return list;
	}

 	public Contagem_CiclicaED getByRecord(Contagem_CiclicaED ed) throws Excecoes{

 		Contagem_CiclicaED edVolta = new Contagem_CiclicaED();
 		try{
 			Iterator iterator = this.lista(ed).iterator();
 			while(iterator.hasNext())
 				edVolta = (Contagem_CiclicaED)iterator.next();
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public Contagem_CiclicaED getByRecord(Contagem_CiclicaED ed)");
        }
 		return edVolta;
 	}

 	private Contagem_CiclicaED getDetalhes_FKs(Contagem_CiclicaED ed) throws Excecoes{

 		String sql = null;
 		try{
 			if(JavaUtil.doValida(ed.getOid_pessoa())){
 				PessoaBean pessoa = PessoaBean.getByOID(ed.getOid_pessoa());
 				ed.setNr_cnpj_cpf(pessoa.getNR_CNPJ_CPF());
 				ed.setNm_razao_social(pessoa.getNM_Razao_Social());
 			}
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_tipo_estoque()))){
 				WMS_Tipo_EstoqueED te = new WMS_Tipo_EstoqueED(new Long(ed.getOid_tipo_estoque()).intValue());
 				te = new WMS_Tipo_EstoqueRN().getByRecord(te);
 				ed.setCd_tipo_estoque(te.getCd_Tipo_Estoque());
 				ed.setNm_tipo_estoque(te.getNm_Tipo_Estoque());
 			}
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_armazem()))){
 				WMS_DepositoED d = new WMS_DepositoED();
 				d.setOid_Deposito(new Integer(String.valueOf(ed.getOid_armazem())));
 				d = new WMS_DepositoRN().getByRecord(d);
 				ed.setCd_deposito(d.getCd_Deposito());
 				ed.setNm_deposito(d.getNm_Deposito());
 			}
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_operador()))){
 				OperadorED o = new OperadorED();
 				o.setOid_Operador(new Integer(String.valueOf(ed.getOid_operador())));
 				o = new OperadorRN().getByRecord(o);
 				ed.setCd_operador(o.getCd_Operador());
 				ed.setNm_operador(o.getNm_Operador());
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "private Contagem_CiclicaED getDetalhes_FKs(Contagem_CiclicaED ed)");
 		}
 		return ed;
	}

 	public ArrayList listaRelatorio(Contagem_CiclicaED ed) throws Excecoes{

 		String sql = null;
 		ArrayList list = new ArrayList();
 		try{
 			sql = " SELECT contagens.* "+
	              " from contagens_ciclicas contagens "+
	              " WHERE 1 = 1 ";
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_contagem_ciclica())))
 				sql +=  " AND contagens.oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();
 			if(JavaUtil.doValida(ed.getOid_pessoa()))
 				sql +=  " AND contagens.oid_pessoa = '" + ed.getOid_pessoa() + "'";
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_tipo_estoque())))
 				sql +=  " AND contagens.oid_tipo_estoque = " + ed.getOid_tipo_estoque();
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_armazem())))
 				sql +=  " AND contagens.oid_armazem = " + ed.getOid_armazem();
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_operador())))
 				sql +=  " AND contagens.oid_operador = " + ed.getOid_operador();
 			if(JavaUtil.doValida(ed.getDm_situacao()) && !ed.getDm_situacao().equals("T"))
 				sql +=  " AND contagens.dm_situacao = '" + ed.getDm_situacao() + "'";
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_qt_itens())))
 				sql +=  " AND contagens.Nr_qt_itens = " + ed.getNr_qt_itens();
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_qt_itens_contados())))
 				sql +=  " AND contagens.Nr_qt_itens_contados = " + ed.getNr_qt_itens_contados();
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_dias_contagem_ciclica())))
 				sql +=  " AND contagens.Nr_dias_contagem_ciclica = " + ed.getNr_dias_contagem_ciclica();
 			//DATAS
 			if(JavaUtil.doValida(ed.getDt_inicio()))
 				sql +=  " AND contagens.dt_inicio >= '" + ed.getDt_inicio() + "'";
 			if(JavaUtil.doValida(ed.getDt_inicio_final()))
 				sql +=  " AND contagens.dt_inicio <= '" + ed.getDt_inicio_final() + "'";
 			if(JavaUtil.doValida(ed.getDt_fim()))
 				sql +=  " AND contagens.dt_fim >= '" + ed.getDt_fim() + "'";
 			if(JavaUtil.doValida(ed.getDt_fim_final()))
 				sql +=  " AND contagens.dt_fim <= '" + ed.getDt_fim_final() + "'";

 			sql += " order by contagens.oid_contagem_ciclica ";
 			ResultSet res = null;
 			res = this.executasql.executarConsulta(sql);
 			FormataDataBean DataFormatada = new FormataDataBean();
 			while (res.next()){
 				Contagem_CiclicaED edVolta = new Contagem_CiclicaED();
 				edVolta.setOid_contagem_ciclica(res.getLong("oid_contagem_ciclica"));
 				edVolta.setOid_pessoa(res.getString("oid_pessoa"));
 				edVolta.setOid_tipo_estoque(res.getLong("oid_tipo_estoque"));
 				edVolta.setOid_armazem(res.getLong("oid_armazem"));
 				edVolta.setOid_operador(res.getLong("oid_operador"));
 				edVolta.setDt_inicio(DataFormatada.getDT_FormataData(res.getString("dt_inicio")));
 				edVolta.setDt_fim(DataFormatada.getDT_FormataData(res.getString("dt_fim")));
 				edVolta.setDm_situacao(res.getString("dm_situacao"));
 				edVolta.setNr_qt_itens(res.getLong("nr_qt_itens"));
 				edVolta.setNr_qt_itens_contados(res.getLong("nr_qt_itens_contados"));
 				edVolta.setTx_observacao(res.getString("tx_observacao"));
 				edVolta.setNr_dias_contagem_ciclica(res.getLong("nr_dias_contagem_ciclica"));
 				edVolta.setNr_acuracidade_contagem(res.getDouble("nr_acuracidade_contagem"));
 				edVolta = this.getDetalhes_FKs(edVolta);
 				edVolta.setSublista(this.sublistaDatas(edVolta));
 				list.add(edVolta);
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public ArrayList lista(Contagem_CiclicaED ed)");
 		}
 		return list;
	}

 	public ArrayList sublistaDatas(Contagem_CiclicaED ed) throws Excecoes{

 		String sql = null;
 		ArrayList list = new ArrayList();
 		try{
 			sql = " SELECT distinct(produtos.dt_contagem), produtos.oid_contagem_ciclica, " +
 				  " sum(case when (produtos.nr_qt_contada!=produtos.nr_qt_estoque) then 1 else 0 end) as cont, " +
 				  " count(produtos.oid_produto_cliente_contagem) as total "+
	              " from produtos_clientes_contagens produtos "+
	              " WHERE 1 = 1 ";
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_contagem_ciclica())))
 				sql +=  " AND produtos.oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();
 			sql += " group by produtos.dt_contagem, produtos.oid_contagem_ciclica ";

 			ResultSet res = null;
 			res = this.executasql.executarConsulta(sql);
 			FormataDataBean DataFormatada = new FormataDataBean();
 			while (res.next()){
 				Produto_Cliente_ContagemED edVolta = new Produto_Cliente_ContagemED();
 				edVolta.setOid_contagem_ciclica(res.getLong("oid_contagem_ciclica"));
 				edVolta.setDt_contagem(DataFormatada.getDT_FormataData(res.getString("dt_contagem")));
 				edVolta.setNr_divergentes(res.getInt("cont"));
 				edVolta.setNr_qt_contada(res.getDouble("total"));
 				edVolta.setNr_acuracidade(dec.format(100-(res.getDouble("cont")/res.getDouble("total"))*100));
 				list.add(edVolta);
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public ArrayList listaDatas(Contagem_CiclicaED ed)");
 		}
 		return list;
	}

}
