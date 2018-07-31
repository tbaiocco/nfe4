package com.master.bd;

/**
 * <p>Title: Produto_Cliente_ContagemBD</p>
 * <p>Description: DAO para produtos da contagem ciclica, relativa a tabela produtos_clientes_contagens</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: SPMti</p>
 * @author Teofilo Poletto Baiocco - nuovonet
 * @version 0.1.0
 */

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.master.ed.Contagem_CiclicaED;
import com.master.ed.OperadorED;
import com.master.ed.Produto_Cliente_ContagemED;
import com.master.ed.WMS_DepositoED;
import com.master.ed.WMS_EstoqueED;
import com.master.ed.WMS_LocalizacaoED;
import com.master.ed.WMS_Tipo_EstoqueED;
import com.master.rn.Contagem_CiclicaRN;
import com.master.rn.OperadorRN;
import com.master.rn.WMS_DepositoRN;
import com.master.rn.WMS_EstoqueRN;
import com.master.rn.WMS_LocalizacaoRN;
import com.master.rn.WMS_Tipo_EstoqueRN;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Produto_Cliente_ContagemBD {

	private ExecutaSQL executasql;
	DecimalFormat dec = new DecimalFormat("###,###,##0.00");

	public Produto_Cliente_ContagemBD(ExecutaSQL sql) {
		this.executasql = sql;
	}

	public Produto_Cliente_ContagemED inclui(Produto_Cliente_ContagemED ed) throws Excecoes{

		String sql = null;
		long valOid = 1;

		Produto_Cliente_ContagemED Produto_Cliente_ContagemED = new Produto_Cliente_ContagemED();

		try{
			sql = "select (oid_produto_cliente_contagem) as result from produtos_clientes_contagens order by oid_produto_cliente_contagem desc limit 1";
			ResultSet rs = this.executasql.executarConsulta(sql);
			while(rs.next())
				valOid = rs.getLong("result") + 1;
			ed.setOid_produto_cliente_contagem(valOid);

			sql = " insert into produtos_clientes_contagens (" +
				  " oid_contagem_ciclica, " +
				  " oid_produto_cliente_contagem, " +
				  " oid_estoque_cliente, " +
				  " oid_operador, " +
				  " nr_qt_estoque, " +
				  " nr_qt_contada, " +
				  " dt_contagem " +
				  " ) values ( ";
			sql += " " + ed.getOid_contagem_ciclica() + ", " +
				   " " + ed.getOid_produto_cliente_contagem() + ", " +
				   "'" + ed.getOid_estoque_cliente() + "', " +
				   " " + ed.getOid_operador() + ", " +
				   " " + ed.getNr_qt_estoque() + ", " +
				   " " + ed.getNr_qt_contada() + ", " +
				   " " + JavaUtil.getSQLDate(ed.getDt_contagem()) + " )";
			int i = executasql.executarUpdate(sql);

			Produto_Cliente_ContagemED.setOid_produto_cliente_contagem(ed.getOid_contagem_ciclica());

		} catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public Produto_Cliente_ContagemED inclui(Produto_Cliente_ContagemED ed)");
		}
		return Produto_Cliente_ContagemED;
	}

	public void altera(Produto_Cliente_ContagemED ed) throws Excecoes{

		String sql = null;
		int contados = 0;
		try{

			sql = " update produtos_clientes_contagens set " +
				  " oid_contagem_ciclica = " + ed.getOid_contagem_ciclica() +
				  " ,oid_estoque_cliente = '" + ed.getOid_estoque_cliente() + "' " +
				  " ,oid_operador = " + ed.getOid_operador() +
				  //" ,nr_qt_estoque = " + ed.getNr_qt_estoque() +
				  " ,nr_qt_contada = " + ed.getNr_qt_contada() +
				  " ,dt_contagem = " + JavaUtil.getSQLDate(ed.getDt_contagem()) +
				  " ,dm_contado = " + JavaUtil.getSQLString(ed.getDm_contado());
			sql += " where oid_produto_cliente_contagem = " + ed.getOid_produto_cliente_contagem();
			int i = executasql.executarUpdate(sql);

			sql = " SELECT COUNT(oid_produto_cliente_contagem) "+
	              " from produtos_clientes_contagens "+
	              " WHERE oid_contagem_ciclica = " + ed.getOid_contagem_ciclica() +
	              " AND dm_contado = 'S' ";
			ResultSet res = null;
 			res = executasql.executarConsulta(sql);
 			if(res.next())
 				contados = res.getInt(1);

 			sql = " update Contagens_Ciclicas set " +
				  " nr_qt_itens_contados = " + (contados);
			sql += " where oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();
			i = executasql.executarUpdate(sql);

		} catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void altera(Produto_Cliente_ContagemED ed)");
		}
	}

	public void deleta(Produto_Cliente_ContagemED ed) throws Excecoes{

		String sql = null;
		try{
			sql = "delete from produtos_clientes_contagens where oid_produto_cliente_contagem = " + ed.getOid_produto_cliente_contagem();

			int i = executasql.executarUpdate(sql);
		} catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void deleta(Produto_Cliente_ContagemED ed)");
        }
	}

 	public ArrayList lista(Produto_Cliente_ContagemED ed) throws Excecoes{

 		String sql = null;
 		ArrayList list = new ArrayList();
 		try{
 			sql = " SELECT produtos.* "+
	              " from produtos_clientes_contagens produtos "+
	              " WHERE 1 = 1 ";
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_produto_cliente_contagem())))
 				sql +=  " AND produtos.oid_produto_cliente_contagem = " + ed.getOid_produto_cliente_contagem();
 			if(JavaUtil.doValida(ed.getOid_estoque_cliente()))
 				sql +=  " AND produtos.oid_estoque_cliente = '" + ed.getOid_estoque_cliente() + "'";
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_contagem_ciclica())))
 				sql +=  " AND produtos.oid_contagem_ciclica = " + ed.getOid_contagem_ciclica();
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_operador())))
 				sql +=  " AND produtos.oid_operador = " + ed.getOid_operador();
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_qt_estoque())) && ed.getNr_qt_estoque() > 0.0)
 				sql +=  " AND produtos.nr_qt_estoque = " + ed.getNr_qt_estoque();
 			if(JavaUtil.doValida(String.valueOf(ed.getNr_qt_contada())) && ed.getNr_qt_contada() > 0.0)
 				sql +=  " AND produtos.nr_qt_contada = " + ed.getNr_qt_contada();
 			//DATAS
 			if(JavaUtil.doValida(ed.getDt_contagem()))
 				sql +=  " AND produtos.dt_contagem >= '" + ed.getDt_contagem() + "'";
 			if(JavaUtil.doValida(ed.getDt_contagem_final()))
 				sql +=  " AND produtos.dt_contagem <= '" + ed.getDt_contagem_final() + "'";

 			sql += " order by produtos.oid_contagem_ciclica, produtos.dt_contagem, produtos.oid_produto_cliente_contagem ";

 			ResultSet res = null;
 			res = this.executasql.executarConsulta(sql);
 			FormataDataBean DataFormatada = new FormataDataBean();
 			while (res.next()){
 				Produto_Cliente_ContagemED edVolta = new Produto_Cliente_ContagemED();
 				edVolta.setOid_contagem_ciclica(res.getLong("oid_contagem_ciclica"));
 				edVolta.setOid_produto_cliente_contagem(res.getLong("oid_produto_cliente_contagem"));
 				edVolta.setOid_operador(res.getLong("oid_operador"));
 				edVolta.setOid_estoque_cliente(res.getString("oid_estoque_cliente"));
 				edVolta.setDt_contagem(DataFormatada.getDT_FormataData(res.getString("dt_contagem")));
 				edVolta.setNr_qt_estoque(res.getDouble("nr_qt_estoque"));
 				edVolta.setNr_qt_contada(res.getDouble("nr_qt_contada"));
 				edVolta.setNm_qt_estoque(dec.format(res.getDouble("nr_qt_estoque")));
 				edVolta.setNm_qt_contada(dec.format(res.getDouble("nr_qt_contada")));
 				edVolta.setDm_contado(res.getString("dm_contado"));
 				edVolta = this.getDetalhes_FKs(edVolta);
 				edVolta.setNr_acuracidade("0,00");
 				if(JavaUtil.doValida(res.getString("dm_contado")) && res.getString("dm_contado").equals("S")){
 					edVolta.setNr_acuracidade(dec.format((res.getDouble("nr_qt_contada")/res.getDouble("nr_qt_estoque"))*100));
 					if(res.getDouble("nr_qt_contada") <= 0.0 || res.getDouble("nr_qt_estoque") <= 0.0)
 						edVolta.setNr_acuracidade("0,00");
 					else if((res.getDouble("nr_qt_contada")/res.getDouble("nr_qt_estoque")) > 1.0){
 						int fator = 1;
 						fator += res.getDouble("nr_qt_contada")/res.getDouble("nr_qt_estoque");
 						fator = fator * 100;
 						edVolta.setNr_acuracidade(dec.format(fator-(res.getDouble("nr_qt_contada")/res.getDouble("nr_qt_estoque"))*100));
 					}
 				}
 				list.add(edVolta);
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public ArrayList lista(Produto_Cliente_ContagemED ed)");
 		}
 		return list;
	}

 	public Produto_Cliente_ContagemED getByRecord(Produto_Cliente_ContagemED ed) throws Excecoes{

 		Produto_Cliente_ContagemED edVolta = new Produto_Cliente_ContagemED();
 		try{
 			Iterator iterator = this.lista(ed).iterator();
 			while(iterator.hasNext())
 				edVolta = (Produto_Cliente_ContagemED)iterator.next();
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public Produto_Cliente_ContagemED getByRecord(Produto_Cliente_ContagemED ed)");
        }
 		return edVolta;
 	}

 	public ArrayList getItensToContagem(Contagem_CiclicaED ed) throws Excecoes{

 		String sql = null;
 		ArrayList list = new ArrayList();
 		String indices_remover = "";
 		ArrayList toReturn = new ArrayList();
 		long nr_itens_dia = 0, nr_itens;
 		try{
 			WMS_EstoqueED estoque = new WMS_EstoqueED();
 			estoque.setOid_Pessoa_Distribuidor(ed.getOid_pessoa());
 			estoque.setOID_Tipo_Estoque(new Long(ed.getOid_tipo_estoque()).intValue());
 			list = new WMS_EstoqueRN().lista(estoque, "Order By Estoques_Clientes.oid_Estoque_Cliente");
 			for(int i=0;i<list.size();i++){
 				estoque = (WMS_EstoqueED)list.get(i);
 				WMS_LocalizacaoED local = new WMS_LocalizacaoRN().getByOid_Localizacao(new WMS_LocalizacaoED(estoque.getOID_Localizacao()));
 				if(ed.getOid_armazem() > 0 && local.getOid_Deposito() != ed.getOid_armazem())
 					list.remove(i);
 			}
 			Collections.shuffle(list);

 			nr_itens = list.size();
 			nr_itens_dia = nr_itens/ed.getNr_dias_contagem_ciclica();
 			for(int i=0;i<list.size();i++){
 				int fator_dia = new Long(i/nr_itens_dia).intValue();
 				estoque = (WMS_EstoqueED)list.get(i);
 				Produto_Cliente_ContagemED produto = new Produto_Cliente_ContagemED();
 				produto.setOid_contagem_ciclica(ed.getOid_contagem_ciclica());
 				produto.setOid_estoque_cliente(estoque.getOID_Estoque());
 				produto.setNr_qt_estoque(estoque.getNR_Quantidade());
 				produto.setNr_qt_contada(0.0);
 				produto.setOid_operador(ed.getOid_operador());
 				produto.setDt_contagem(Data.manipulaDias(ed.getDt_inicio(),fator_dia));
 				toReturn.add(produto);
 			}

 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public ArrayList getItensToContagem(Contagem_CiclicaED ed))");
 		}
 		return toReturn;
	}

 	private Produto_Cliente_ContagemED getDetalhes_FKs(Produto_Cliente_ContagemED ed) throws Excecoes{

 		String sql = null;
 		try{
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_operador()))){
 				OperadorED o = new OperadorED();
 				o.setOid_Operador(new Integer(String.valueOf(ed.getOid_operador())));
 				o = new OperadorRN().getByRecord(o);
 				ed.setCd_operador(o.getCd_Operador());
 				ed.setNm_operador(o.getNm_Operador());
 			}
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_estoque_cliente()))){
 				WMS_EstoqueED estoque = new WMS_EstoqueED(ed.getOid_estoque_cliente());
 				estoque = new WMS_EstoqueRN().getByRecord(estoque);
 				ed.setCd_estoque_cliente(estoque.getCD_Produto());
 				ed.setNm_estoque_cliente(estoque.getNM_Produto());
 				ed.setCd_localizacao(new WMS_LocalizacaoRN().getByOid_Localizacao(new WMS_LocalizacaoED(estoque.getOID_Localizacao())).getCD_Localizacao());
 			}
 			if(JavaUtil.doValida(String.valueOf(ed.getOid_contagem_ciclica()))){
 				Contagem_CiclicaED contagem = new Contagem_CiclicaED();
 				contagem.setOid_contagem_ciclica(ed.getOid_contagem_ciclica());
 				contagem = new Contagem_CiclicaRN().getByRecord(contagem);
 				ed.setOid_pessoa(contagem.getOid_pessoa());
 				ed.setNr_cnpj_cpf(contagem.getNr_cnpj_cpf());
 				ed.setNm_razao_social(contagem.getNm_razao_social());
 				ed.setOid_tipo_estoque(contagem.getOid_tipo_estoque());
 				ed.setCd_tipo_estoque(contagem.getCd_tipo_estoque());
 				ed.setNm_tipo_estoque(contagem.getNm_tipo_estoque());
 				ed.setOid_armazem(contagem.getOid_armazem());
 				ed.setCd_deposito(contagem.getCd_deposito());
 				ed.setNm_deposito(contagem.getNm_deposito());
 				ed.setOid_operador(contagem.getOid_operador());
 				ed.setCd_operador(contagem.getCd_operador());
 				ed.setNm_operador(contagem.getNm_operador());
 				ed.setDm_situacao(contagem.getDm_situacao());
 				ed.setNr_qt_itens(contagem.getNr_qt_itens());
 				ed.setDt_inicio(contagem.getDt_inicio());
 				ed.setDt_fim(contagem.getDt_fim());
 				ed.setNr_acuracidade_contagem(contagem.getNr_acuracidade_contagem());
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "private Contagem_CiclicaED getDetalhes_FKs(Contagem_CiclicaED ed)");
 		}
 		return ed;
	}

 	public ArrayList listaDatas(Contagem_CiclicaED ed) throws Excecoes{

 		String sql = null;
 		ArrayList list = new ArrayList();
 		try{
 			sql = " SELECT distinct(produtos.dt_contagem), produtos.oid_contagem_ciclica, " +
 				  " sum(case when (produtos.nr_qt_contada!=produtos.nr_qt_estoque) then 1 else 0 end) as cont "+
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
 				list.add(edVolta);
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public ArrayList listaDatas(Contagem_CiclicaED ed)");
 		}
 		return list;
	}

 	public double getAcuracidadeDia(String data, long oid_contagem_ciclica) throws Excecoes{

 		String sql = null;
 		double toReturn = 0;
 		try{
 			sql = " SELECT sum(case when (produtos.nr_qt_contada!=produtos.nr_qt_estoque) then 1 else 0 end) as cont, " +
 				  " sum(oid_produto_cliente_contagem) as total " +
	              " from produtos_clientes_contagens produtos " +
	              " WHERE produtos.dt_contagem = '" + data + "' " +
	              " AND produtos.oid_contagem_ciclica = " + oid_contagem_ciclica;

 			ResultSet res = null;
 			res = this.executasql.executarConsulta(sql);
 			while (res.next()){
 				toReturn = (res.getInt("cont")/res.getInt("total"))*100;
 			}
 		} catch(Exception exc){
 			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public double getAcuracidadeDia(String data, long oid_contagem_ciclica)");
 		}
 		return toReturn;
	}

}
