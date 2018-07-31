package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.MobDelivery_TarefaED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Tarefas no MobDelivery
 * @serialData 19/05/2008
 */
public class MobDelivery_TarefaBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public MobDelivery_TarefaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public MobDelivery_TarefaED inclui(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			ed.setOid_Tarefa(getAutoIncremento("oid_Tarefa", "MobDelivery_Tarefas"));
			sql = "INSERT INTO MobDelivery_Tarefas (" +
			"oid_Tarefa," +
			"oid_Grupo_Tarefa," +
			"oid_Unidade,"+
			"dt_Tarefa,"+
			"nr_Tarefa,"+
			"nr_Volume,"+
			"nm_Endereco, "+
			"nm_Nome, "+
			"oid_Conhecimento, "+
			"nr_Ordem, "+
			"dm_Situacao," +
			"dm_Tipo," +
			"dm_Stamp," +
	  	    "dt_Stamp," +
	  	    "usuario_Stamp"+
		    ") " +
			" VALUES (" +
			ed.getOid_Tarefa()+
			", "+ed.getOid_Grupo_Tarefa()+
			", "+ed.getOid_Unidade()+
			",'"+ed.getDt_Tarefa()+"'"+
			", "+ed.getNr_Tarefa()+
			", "+ed.getNr_Volume()+
			",'"+ed.getNm_Endereco()+"' "+
			",'"+ed.getNm_Nome()+"' "+
			",'"+ed.getOid_Conhecimento()+"' "+
			","+ed.getNr_Ordem()+
			",'0'" +
			",'"+ed.getDm_Tipo()+"'"+
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(MobDelivery_TarefaED ed)");
		}
	}

	public void altera(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Tarefas SET " +
			"oid_Tarefa="+ed.getOid_Tarefa()+
			",oid_Grupo_Tarefa="+ed.getOid_Grupo_Tarefa()+
			",oid_Unidade="+ed.getOid_Unidade()+
			",nr_Tarefa="+ed.getNr_Tarefa()+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Tarefa = " + ed.getOid_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_TarefaED ed)");
		}
	}

	public void alocaTarefa(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Tarefas SET " +
			"oid_Executor="+ed.getOid_Executor()+
			",nr_Ordem_Alocado="+ed.getNr_Ordem_Alocado()+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Tarefa = " + ed.getOid_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"alocaTarefa(MobDelivery_TarefaED ed)");
		}
	}

	public void deAlocaTarefa(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Tarefas SET " +
			"oid_Executor=null"+
			",nr_Ordem_Alocado=null"+
			",dm_Protocolo=null"+
			",nr_Protocolo=null"+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Tarefa = " + ed.getOid_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deAlocaTarefa(MobDelivery_TarefaED ed)");
		}
	}

	public void reposicionaTarefa(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Tarefas SET " +
			"nr_Ordem_Alocado="+ed.getNr_Ordem_Alocado()+
			",dm_Protocolo=null"+
			",nr_Protocolo=null"+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Tarefa = " + ed.getOid_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_TarefaED ed)");
		}
	}

	public long getUltimoNrAlocado(MobDelivery_TarefaED ed) throws Excecoes {
		long nr_Ordem_Alocado=0;
		try {
			sql = "Select " +
			"max(nr_Ordem_Alocado) as nr_Alocado "+
			"From " +
			"MobDelivery_Tarefas " +
			"WHERE " +
			"oid_executor = " + ed.getOid_Executor() + " " +
			"and dt_tarefa ='" + ed.getDt_Tarefa() +"'";
			ResultSet res = this.executasql.executarConsulta(sql);
			if (res.next())
				nr_Ordem_Alocado = res.getLong("nr_Alocado");
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deAlocaTarefa(MobDelivery_TarefaED ed)");
		}
		return nr_Ordem_Alocado;
		
	}

	public void delete(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "DELETE FROM MobDelivery_Tarefas " +
			"WHERE " +
			"oid_Grupo_Tarefa = " + ed.getOid_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(MobDelivery_TarefaED ed)");
		}
	}
	
	public ArrayList lista(MobDelivery_TarefaED ed, String ordem) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
	  		",t.usuario_Stamp as usu_Stmp " +
	  		",t.dt_Stamp as dt_Stmp " +
	  		",t.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Tarefas as t " +
			"left join MobDelivery_Situacoes as s on s.cd_situacao = t.dm_Situacao " +
			"WHERE 1=1 " ;  
			
			if (ed.getOid_Tarefa()>0)
				sql+="AND t.oid_Tarefa="+ed.getOid_Tarefa()+ " ";
			else {
				if (ed.getOid_Grupo_Tarefa()>0)
					sql+="AND t.oid_Grupo_Tarefa="+ed.getOid_Grupo_Tarefa()+ " ";
				if (ed.getOid_Executor()>0)
					sql+="AND t.oid_Executor="+ed.getOid_Executor()+ " ";
				else if (ed.getOid_Executor()==-1)
					sql+="AND t.oid_Executor is null ";
				if (ed.getOid_Unidade()>0)
					sql+="AND t.oid_Unidade="+ed.getOid_Unidade()+ " ";
				if (ed.getNr_Tarefa()>0)
					sql +="AND t.nr_Tarefa="+ed.getNr_Tarefa()+ " ";
				if (doValida(ed.getDt_Tarefa()))
					sql+="AND t.dt_Tarefa='"+ed.getDt_Tarefa()+ "' ";
				if (doValida(ed.getDm_Protocolo()) ) {
					if ("C".equals(ed.getDm_Protocolo()))
						sql+=" AND t.dm_Protocolo is null ";
					else if (!"T".equals(ed.getDm_Protocolo()))
							sql+=" AND t.dm_Protocolo = '" + ed.getDm_Protocolo() +"' ";
					if(ed.getNr_Protocolo()>0)
						sql+=" AND t.nr_Protocolo = " + ed.getNr_Protocolo() +" ";
				} else {
					if (doValida(ed.getDm_Situacao()) || "0".equals(ed.getDm_Situacao()))
						sql+="AND t.dm_Situacao='"+ed.getDm_Situacao()+"' ";
				}
			}
			sql += "ORDER BY " ;
			if ("nr_ordem".equals(ordem))
				sql += "t.nr_Ordem" ;
			if ("nr_ordem_alocado".equals(ordem))
				sql += "t.nr_Ordem_Alocado" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_TarefaED ed)");
		}
	}

	public ArrayList desempenhoExecutores(MobDelivery_TarefaED ed, String tipo) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"e.nm_executor,	" +
			"count(t.oid_Tarefa) as nr_Total_Tarefas, " +
			"sum(case when t.dm_Situacao<>'0' then 1 else 0 end) as nr_Tarefas_Executadas,	" +
			"sum(case when t.dm_Situacao='0' then 1 else 0 end) as nr_Tarefas_N_Executadas " +
			"FROM " +
			"MobDelivery_Tarefas t left join MobDelivery_Executores e on t.oid_Executor = e.oid_Executor " +
			"WHERE 1=1 " +
			"AND t.oid_Executor Is Not Null " ;
			
			if (ed.getOid_Unidade()>0)
				sql+="AND t.oid_Unidade="+ed.getOid_Unidade()+ " ";
			if (doValida(ed.getDt_Inicial()))
				sql+="AND t.dt_Tarefa >='"+ed.getDt_Inicial()+ "' ";
			if (doValida(ed.getDt_Final()))
				sql+="AND t.dt_Tarefa <='"+ed.getDt_Final()+ "' ";

			sql += "GROUP BY e.nm_executor " +
				"ORDER BY e.nm_executor " ;
			
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				MobDelivery_TarefaED edD = new MobDelivery_TarefaED();
				edD.setNm_Executor(res.getString("nm_Executor"));
				edD.setNr_Total_Tarefas(res.getInt("nr_Total_Tarefas"));
				edD.setNr_Tarefas_Executadas(res.getInt("nr_Tarefas_Executadas"));
				edD.setNr_Tarefas_N_Executadas(res.getInt("nr_Tarefas_N_Executadas"));
				list.add(edD);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_TarefaED ed)");
		}
	}

	public MobDelivery_TarefaED getAnteriorProximo(MobDelivery_TarefaED ed) throws Excecoes {
		MobDelivery_TarefaED edD = new MobDelivery_TarefaED();
		try {
			sql = "SELECT " +
			"* " +
	  		",t.usuario_Stamp as usu_Stmp " +
	  		",t.dt_Stamp as dt_Stmp " +
	  		",t.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Tarefas t " +
			"left join MobDelivery_Situacoes as s on s.cd_situacao = t.dm_Situacao " +
			"WHERE 1=1 " +
			"and t.oid_Executor = " + ed.getOid_Executor() + " " +
			"and t.dt_tarefa = '" + ed.getDt_Tarefa() + "' " +
			"and t.nr_Ordem_Alocado " + ("E".equals(ed.getDm_Situacao())? "< " : "> ") + ed.getNr_Ordem_Alocado() + " ";
			sql+="ORDER BY t.nr_Ordem_Alocado " +("E".equals(ed.getDm_Situacao())? " desc" : " asc ") ; 
			ResultSet res = this.executasql.executarConsulta(sql);
			if (res.next()) { edD=populaRegistro(res); }
			return edD;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_TarefaED ed)");
		}
	}

	
	public void registraEnvio(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Tarefas " +
			"SET " +
			" dm_Protocolo = 'E' " +
			",nr_Protocolo = " + ed.getNr_Protocolo() +
			" WHERE " +
			" oid_Executor="+ed.getOid_Executor()+
			" AND dm_Protocolo is null ";
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"registraEnvio(MobDelivery_TarefaED ed)");
		}
	}

	public void registraRecebimento(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Tarefas " +
			"SET " +
			" dm_Protocolo = 'R' " +
			"WHERE " +
			" oid_Executor="+ed.getOid_Executor()+
			" and dm_Protocolo = 'E' " +
			" and nr_Protocolo = " + ed.getNr_Protocolo() ;
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"registraRecebimento(MobDelivery_TarefaED ed)");
		}
	}

	public void registraFechamentoDia(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Tarefas " +
			"SET " +
			" dm_Protocolo = 'F' " +
			" WHERE " +
			" oid_Executor="+ed.getOid_Executor();
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"registraFechamento(MobDelivery_TarefaED ed)");
		}
	}

	public void limpaProtocolo(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Tarefas " +
			"SET " +
			" dm_Protocolo = null " +
			",nr_Protocolo = null " +
			"WHERE " +
			" oid_Executor="+ed.getOid_Executor();
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"limpaProtocolo(MobDelivery_TarefaED ed)");
		}
	}

	public void baixaTarefa(MobDelivery_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Tarefas " +
			"SET " +
			" dm_Situacao = '" + ed.getDm_Situacao() +"' " +
			",dt_Execucao = '" + ed.getDt_Execucao() +"' " +
			",hr_Execucao = '" + ed.getHr_Execucao() +"' " +
			",dt_Registro_Execucao = '" + Data.getDataDMY() +"' " +
			",hr_Registro_Execucao = '" + Data.getHoraHM() +"' " ;
			if (doValida(ed.getTx_Rg_Agente())) // Caso não seja informado o agente pois a situação não exige não grava ...
				sql +=",tx_Rg_Agente = '" + ed.getTx_Rg_Agente().toUpperCase() +"' " +
				",nm_Agente = '" + ed.getNm_Agente().toUpperCase() +"' " ;
			sql +="WHERE " +
			" oid_Tarefa="+ed.getOid_Tarefa();
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"limpaProtocolo(MobDelivery_TarefaED ed)");
		}
	}

	/**
	 * Retorna uma lista para a tela mob001C com os executores e suas tarefas.
	 * Se o executor não tem tarefa retorna ed somente com o executor. 
	 * @param ed Ed montado na tela
	 * @return lista com as tarefas
	 * @throws Excecoes
	 */
	public ArrayList listaCompleta(MobDelivery_TarefaED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		int cta = 0; 
		try {
			// Busca os executores alocados na unidade
			sql = "SELECT * "  +
			"FROM " +
			"MobDelivery_Executores as e " +
			"WHERE 1=1 " ;  
			if (ed.getOid_Unidade()>0)
				sql+="AND e.oid_Unidade="+ed.getOid_Unidade()+ " ";
			if (ed.getOid_Executor()>0)
				sql+="AND e.oid_Executor="+ed.getOid_Executor()+ " ";
			
			sql += "ORDER BY " +
			"e.oid_Unidade " +
			",e.nm_Executor " ;
			ResultSet rsExec = this.executasql.executarConsulta(sql);
			
			while (rsExec.next()) {
				MobDelivery_TarefaED edE = new MobDelivery_TarefaED();
				edE.setOid_Executor(rsExec.getInt("oid_Executor"));
				edE.setNm_Executor(rsExec.getString("nm_Executor"));
				
				// Busca as tarefas do executor
				sql = "SELECT *	" +
				",t.usuario_Stamp as usu_Stmp " +
				",t.dt_Stamp as dt_Stmp " +
				",t.dm_Stamp as dm_Stmp " +
				"FROM " +
				"MobDelivery_Tarefas as t " +
				"left join MobDelivery_Situacoes as s on s.cd_situacao = t.dm_Situacao " +
				"left join MobDelivery_Grupos_Tarefas as g on t.oid_Grupo_Tarefa = g.oid_Grupo_Tarefa " +
				"WHERE 1=1 AND " ;
				sql +="t.oid_Executor="+rsExec.getInt("oid_Executor")+" " ;
				if (doValida(ed.getDt_Tarefa()))
					sql+="AND t.dt_Tarefa='"+ed.getDt_Tarefa()+ "' ";
				sql+="ORDER BY  " +
				"t.nr_Ordem_Alocado";	
				ResultSet rsTar = this.executasql.executarConsulta(sql);
				cta=0;
				while (rsTar.next()) {
					MobDelivery_TarefaED edT = new MobDelivery_TarefaED();
					edT.setOid_Executor(rsExec.getInt("oid_Executor"));
					edT.setNm_Executor(rsExec.getString("nm_Executor"));
					edT.setOid_Tarefa(rsTar.getInt("oid_Tarefa"));
					edT.setOid_Grupo_Tarefa(rsTar.getInt("oid_Grupo_Tarefa"));
					edT.setOid_Unidade(rsTar.getInt("oid_Unidade"));
					edT.setNr_Tarefa(rsTar.getInt("nr_Tarefa"));
					edT.setNm_Endereco(rsTar.getString("nm_Endereco"));
					edT.setNm_Nome(rsTar.getString("nm_Nome"));
					edT.setNr_Volume(rsTar.getDouble("nr_Volume"));
					edT.setNr_Peso(rsTar.getDouble("nr_Peso"));
					edT.setNm_Solicitante(rsTar.getString("nm_Solicitante"));
					edT.setTx_Observacao(rsTar.getString("tx_Observacao"));
					edT.setDm_Situacao(rsTar.getString("dm_Situacao"));
					edT.setDt_Tarefa(FormataData.formataDataBT(rsTar.getString("dt_tarefa")));
					edT.setNm_Grupo_Tarefa(rsTar.getString("nm_Grupo_Tarefa"));
					edT.setDt_Grupo_Tarefa(FormataData.formataDataBT(rsTar.getString("dt_grupo_tarefa")));
					edT.setNr_Cor(rsTar.getInt("nr_Cor"));
					edT.setNm_Situacao(rsTar.getString("nm_Situacao"));
					edT.setDt_Execucao(FormataData.formataDataBT(rsTar.getString("dt_Execucao")));
					edT.setHr_Execucao(rsTar.getString("hr_Execucao"));
					edT.setOid_Conhecimento(rsTar.getString("oid_Conhecimento"));
					edT.setNr_Ordem_Alocado(rsTar.getInt("nr_Ordem_Alocado"));
					edT.setDm_Tipo(rsTar.getString("dm_Tipo"));
					edT.setMsg_Stamp(("I".equals(rsTar.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + rsTar.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(rsTar.getString("dt_Stmp")));
					// Adiciona na lista
					list.add(edT);
					cta++;
				}
				if (cta==0) { list.add(edE); }
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"listaCompleta(MobDelivery_TarefaED ed)");
		}
	}

	public MobDelivery_TarefaED getByRecord (MobDelivery_TarefaED ed) throws Excecoes {
		ArrayList lista = this.lista (ed,"nr_ordem");
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (MobDelivery_TarefaED) iterator.next ();
		}
		else {
			return new MobDelivery_TarefaED();
		}
	}	
	
	private MobDelivery_TarefaED populaRegistro(ResultSet res) throws SQLException {
		MobDelivery_TarefaED ed = new MobDelivery_TarefaED();
		ed.setOid_Tarefa(res.getInt("oid_Tarefa"));
		ed.setOid_Grupo_Tarefa(res.getInt("oid_Grupo_Tarefa"));
		ed.setOid_Unidade(res.getInt("oid_Unidade"));
		ed.setNr_Tarefa(res.getInt("nr_Tarefa"));
		ed.setNm_Endereco(res.getString("nm_Endereco"));
		ed.setNm_Nome(res.getString("nm_Nome"));
		ed.setNr_Volume(res.getDouble("nr_Volume"));
		ed.setNr_Peso(res.getDouble("nr_Peso"));
		ed.setNm_Solicitante(res.getString("nm_Solicitante"));
		ed.setTx_Observacao(res.getString("tx_Observacao"));
		ed.setDm_Tipo(res.getString("dm_Tipo"));
		ed.setOid_Executor(res.getInt("oid_Executor"));
		ed.setDt_Tarefa(FormataData.formataDataBT(res.getString("dt_Tarefa")));
		ed.setDm_Situacao(res.getString("dm_Situacao"));
		ed.setNm_Situacao(res.getString("nm_Situacao"));
		ed.setDt_Execucao(FormataData.formataDataBT(res.getString("dt_Execucao")));
		ed.setHr_Execucao(res.getString("hr_Execucao"));
		ed.setTx_Rg_Agente(res.getString("tx_Rg_Agente"));
		ed.setNm_Agente(res.getString("nm_Agente"));
		ed.setOid_Conhecimento(res.getString("oid_Conhecimento"));
		ed.setNr_Ordem(res.getInt("nr_Ordem"));
		ed.setNr_Ordem_Alocado(res.getInt("nr_Ordem_Alocado"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;	
	}
}