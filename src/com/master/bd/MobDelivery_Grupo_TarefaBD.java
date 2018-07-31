package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.ConhecimentoED;
import com.master.ed.ManifestoED;
import com.master.ed.MobDelivery_Grupo_TarefaED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Agrupamento de tarefas no MobDelivery
 * @serialData 19/05/2008
 */
public class MobDelivery_Grupo_TarefaBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public MobDelivery_Grupo_TarefaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public MobDelivery_Grupo_TarefaED inclui(MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		try {
			ed.setOid_Grupo_Tarefa(getAutoIncremento("oid_Grupo_Tarefa", "MobDelivery_Grupos_Tarefas"));
			sql = "INSERT INTO MobDelivery_Grupos_Tarefas (" +
			"oid_Grupo_Tarefa," +
			"oid_Unidade,"+
			"nr_Grupo_Tarefa,"+
			"nm_Grupo_Tarefa,"+
			"dt_Grupo_Tarefa, "+
			"oid_Romaneio, "+
			"dm_Stamp," +
	  	    "dt_Stamp," +
	  	    "usuario_Stamp"+
		    ") " +
			" VALUES (" +
			ed.getOid_Grupo_Tarefa()+
			", "+ed.getOid_Unidade()+
			", "+ed.getNr_Grupo_Tarefa()+
			",'"+ed.getNm_Grupo_Tarefa()+"' "+
			",'"+ed.getDt_Grupo_Tarefa()+"' "+ 
			",'"+ed.getOid_Romaneio()+"' "+
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(MobDelivery_Grupo_TarefaED ed)");
		}
	}

	public void altera(MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Grupos_Tarefas SET " +
			"oid_Grupo_Tarefa="+ed.getOid_Grupo_Tarefa()+
			",oid_Unidade="+ed.getOid_Unidade()+
			",nr_Grupo_Tarefa="+ed.getNr_Grupo_Tarefa()+
			",nm_Grupo_Tarefa='"+ed.getNm_Grupo_Tarefa()+"' "+
			",dt_Grupo_Tarefa='"+ed.getDt_Grupo_Tarefa()+"' "+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Recapagem = " + ed.getOid_Grupo_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_Grupo_TarefaED ed)");
		}
	}

	public void delete(MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		try {
			sql = "DELETE FROM MobDelivery_Grupos_Tarefas " +
			"WHERE " +
			"oid_Grupo_Tarefa = " + ed.getOid_Grupo_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(MobDelivery_Grupo_TarefaED ed)");
		}
	}
	
	public ArrayList lista(MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
	  		",g.usuario_Stamp as usu_Stmp " +
	  		",g.dt_Stamp as dt_Stmp " +
	  		",g.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Grupos_Tarefas as g " +
			"WHERE 1=1 " ;  
			
			if (ed.getOid_Grupo_Tarefa()>0)
				sql+="AND g.oid_Grupo_Tarefa="+ed.getOid_Grupo_Tarefa()+ " ";
			else {
				if (ed.getOid_Unidade()>0)
					sql+="AND g.oid_Unidade="+ed.getOid_Unidade()+ " ";
				if (ed.getNr_Grupo_Tarefa()> 0)
					sql+="AND g.nr_Grupo_Tarefa="+ed.getNr_Grupo_Tarefa()+ " ";
			}
			sql += "ORDER BY " +
			"g.nm_Grupo_Tarefa";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_Grupo_TarefaED ed)");
		}
	}
	/**
	 * Le todos os manifestos tipo romaneio que existam para a empresa para a importação no MobDelivery
	 * @param ed - Ed do manifesto com a data para pesquisa
	 * @return 
	 * @throws Excecoes
	 * @Coments - Deverá ser movido para a classe ManifestoBD futuramente
	 */
	public ArrayList listaManifestos(ManifestoED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"m.oid_Manifesto, " +
			"m.dt_Emissao, " +
			"m.nr_Manifesto, " +
			"u.cd_Unidade, " +
			"p.nm_Razao_Social as NM_Motorista, " +
			"v.nr_Placa "+
			"FROM " +
			"Manifestos as m , " +
			"Unidades as u , " +
			"Veiculos as v , " +
			"Pessoas as p " +
			"WHERE " +
			"m.OID_Unidade = u.OID_Unidade and " +
			"m.OID_Veiculo = v.OID_Veiculo and " +
			"m.OID_Pessoa = p.Oid_Pessoa and " +
			"m.dm_tipo_manifesto = 'R' and	" +
			"m.DT_Emissao >= '"+ed.getDT_Emissao_Inicial()+"' and " +
			"m.DT_Emissao <= '"+ed.getDT_Emissao_Final()+"' " ;  
			sql += "ORDER BY " +
			"u.cd_Unidade";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				ManifestoED vED = new ManifestoED();
				vED.setOID_Manifesto(res.getString("oid_Manifesto"));
				vED.setDT_Emissao(FormataData.formataDataBT(res.getString("dt_Emissao")));
				vED.setNR_Manifesto(res.getInt("Nr_Manifesto"));
				vED.setCD_Unidade(res.getString("CD_Unidade"));
				vED.setNM_Ajudante1(res.getString("NM_Motorista"));
				vED.setNR_Placa(res.getString("nr_Placa"));
				list.add(vED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"listaManifestos(ManifestoED ed)");
		}
	}

	/**
	 * Le os conhecimentos da viagens 
	 * @param ed - Ed do conhecimento montano no metodo listaViagens
	 * @return 
	 * @throws Excecoes
	 * @Coments - Deverá ser movido para a classe ManifestoBD futuramente
	 */
	public ArrayList listaConhecimento(ConhecimentoED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " + 
			"co.oid_Conhecimento, " +
			"co.nr_Conhecimento, " +
			"co.nr_Volumes, " +
			"cd.nm_Cidade , " +
			"pd.nm_Razao_Social  " +
			"FROM " +
			"Manifestos as ma left join Viagens as vi on ma.oid_Manifesto = vi.oid_Manifesto " + 
			"left join Conhecimentos as co on vi.oid_Conhecimento = co.oid_Conhecimento "+ 
			"left join Cidades as cd on co.oid_Cidade_Destino = cd.oid_Cidade "+ 
			"left join Pessoas as pd on co.oid_Pessoa_Destinatario = pd.oid_Pessoa "+ 
			"WHERE "+
			"ma.oid_Manifesto ='"+ed.getOID_Conhecimento()+"' " +
			"Order by vi.nr_Sequencia, "+ 
			"co.nr_Conhecimento";

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				ConhecimentoED cED = new ConhecimentoED();
				cED.setOID_Conhecimento(res.getString("oid_Conhecimento"));
				cED.setNR_Conhecimento(res.getInt("nr_Conhecimento"));
				cED.setNR_Volumes(res.getDouble("nr_Volumes"));
				cED.setNM_Cidade_Destinatario(res.getString("nm_Cidade"));
				cED.setNM_Pessoa_Destinatario(res.getString("nm_Razao_Social"));
				list.add(cED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"listaManifestos(ManifestoED ed)");
		}
	}
	
	/**
	 * Lista de grupos de tarefas que tenham alguma tarefa não alocada
	 * @param ed
	 * @return
	 * @throws Excecoes
	 */
	public ArrayList listaGrupoTna(MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"g.nm_Grupo_Tarefa as nm " +
			",g.oid_grupo_tarefa " +
			",g.oid_unidade " +
			",g.nr_grupo_tarefa " +
			",g.nm_grupo_tarefa " +
			",g.dt_grupo_tarefa " +
			",t.oid_Grupo_Tarefa " +
	  		",g.usuario_Stamp as usu_Stmp " +
	  		",g.dt_Stamp as dt_Stmp " +
	  		",g.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Grupos_Tarefas as g left join MobDelivery_Tarefas as t on g.oid_Grupo_Tarefa = t.oid_Grupo_Tarefa " +
			"WHERE " +
			"t.oid_Executor isnull " ;  
			
			if (ed.getOid_Unidade()>0)
				sql+="AND g.oid_Unidade="+ed.getOid_Unidade()+ " ";
			else if (ed.getOid_Grupo_Tarefa()>0)
				sql+="AND g.oid_Grupo_Tarefa="+ed.getOid_Grupo_Tarefa()+ " ";
			if (doValida(ed.getDt_Grupo_Tarefa()))
				sql+="AND g.dt_Grupo_Tarefa='"+ed.getDt_Grupo_Tarefa()+ "' ";

			sql +="GROUP BY " +
			"g.nm_Grupo_Tarefa " +
			",g.oid_grupo_tarefa " +
			",g.oid_unidade " +
			",g.nr_grupo_tarefa " +
			",g.nm_grupo_tarefa " +
			",g.dt_grupo_tarefa" +
			",t.oid_Grupo_Tarefa " +
			",g.usuario_Stamp " +
			",g.dt_Stamp " +
			",g.dm_Stamp "+
			"HAVING " +
			"not t.oid_Grupo_Tarefa isnull " +
			"ORDER BY " +
			"g.nm_Grupo_Tarefa";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_Grupo_TarefaED ed)");
		}
	}
	
	public MobDelivery_Grupo_TarefaED getByRecord (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		ArrayList lista = this.lista (ed);
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (MobDelivery_Grupo_TarefaED) iterator.next ();
		}
		else {
			return new MobDelivery_Grupo_TarefaED();
		}
	}	
	
	private MobDelivery_Grupo_TarefaED populaRegistro(ResultSet res) throws SQLException {
		MobDelivery_Grupo_TarefaED ed = new MobDelivery_Grupo_TarefaED();
		ed.setOid_Grupo_Tarefa(res.getInt("oid_Grupo_Tarefa"));
		ed.setOid_Unidade(res.getInt("oid_Unidade"));
		ed.setNr_Grupo_Tarefa(res.getInt("nr_Grupo_Tarefa"));
		ed.setNm_Grupo_Tarefa(res.getString("nm_Grupo_Tarefa"));
		ed.setDt_Grupo_Tarefa(FormataData.formataDataBT(res.getString("dt_Grupo_Tarefa")));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;	
	}
}