package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.master.ed.ConsertoED;
import com.master.ed.RecapagemED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Recapagens
 * @serialData 10/2007
 * MODIFICADO
 * @author Ralph Renato
 * @serial consulta recapagens efetuadas
 * @serialData 11/2007
 */
public class RecapagemBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public RecapagemBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public RecapagemED inclui(RecapagemED ed) throws Excecoes {
		try {
			ed.setOid_Recapagem(getAutoIncremento("oid_Recapagem", "Recapagens"));
			sql = "INSERT INTO Recapagens (" +
			"oid_Recapagem," +
			"oid_Pneu,"+
			"oid_Empresa,"+
			"oid_Fornecedor_Recapagem,"+
			"oid_Fabricante_Banda, "+
			"oid_Banda," +
			"vl_Recapagem," +
			"dt_Recapagem," +
			"nr_Nota_Fiscal_Recapagem," +
			"nr_Os_Recapagem,"+
			"nr_Mm_Sulco_Recapagem,"+
			"nr_Perimetro,"+
			"dm_Garantia_Recapagem " +
			",dm_Stamp" +
	  	    ",dt_Stamp" +
	  	    ",usuario_Stamp"+
	  	    ",oid_usuario"+
	  	    ",time_millis"+
		    ") " +
			" VALUES (" +
			ed.getOid_Recapagem()+
			", "+ed.getOid_Pneu()+
			", "+ed.getOid_Empresa()+
			",'"+ed.getOid_Fornecedor()+"' "+
			", "+ed.getOid_Fabricante_Banda()+
			", "+ed.getOid_Banda()+
			", "+ed.getVl_Recapagem()+
			",'"+ed.getDt_Recapagem()+"' "+
			", "+ed.getNr_Nota_Fiscal_Recapagem()+
			",'"+ed.getNr_Os_Recapagem()+"' "+
			", "+ed.getNr_Mm_Sulco_Recapagem()+
			", "+ed.getNr_Perimetro()+
			",'"+ed.getDm_Garantia_Recapagem()+"' "+
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		"," + ed.getUser() +
		  	"," + ed.getTime_millis() +
	  		")";
			System.out.println("RECAP.: "+sql);
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(RecapagemED ed)");
		}
	}

	public void altera(RecapagemED ed) throws Excecoes {
		try {
			sql = "UPDATE Recapagens SET " +
			"oid_pneu="+ed.getOid_Pneu()+
			",oid_fornecedor_recapagem='"+ed.getOid_Fornecedor()+"'"+
			",oid_Fabricante_Banda="+ed.getOid_Fabricante_Banda()+
			",oid_Banda="+ed.getOid_Banda()+
			",vl_Recapagem="+ed.getVl_Recapagem()+
			",dt_Recapagem='"+ed.getDt_Recapagem()+"'"+
			",nr_Nota_Fiscal_Recapagem=" + ed.getNr_Nota_Fiscal_Recapagem() +
			",nr_Os_Recapagem='"+ed.getNr_Os_Recapagem()+"'"+
			",nr_Mm_Sulco_Recapagem="+ed.getNr_Mm_Sulco_Recapagem()+
			",dm_Garantia_Recapagem='"+ed.getDm_Garantia_Recapagem()+"' "+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
  	   	    ",oid_usuario = " + ed.getUser() +
		    ",time_millis = " + ed.getTime_millis() +
			"WHERE " +
			"oid_Recapagem = " + ed.getOid_Recapagem();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(RecapagemED ed)");
		}
	}

	public void delete(RecapagemED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Recapagens " +
			"WHERE " +
			"oid_Recapagem = " + ed.getOid_Recapagem();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(RecapagemED ed)");
		}
	}

	public ArrayList lista(RecapagemED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT *, pessoas.nm_razao_social as nm_fornecedor " +
	  		",Recapagens.usuario_Stamp as usu_Stmp " +
	  		",Recapagens.dt_Stamp as dt_Stmp " +
	  		",Recapagens.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Recapagens " +
			"left join fornecedores " +
			"	left join pessoas " +
			" 		on pessoas.oid_pessoa = fornecedores.oid_pessoa " +
			" on fornecedores.oid_fornecedor = Recapagens.oid_Fornecedor_Recapagem, " +
			"Pneus, " +
			"Fabricantes_Bandas, " +
			"Bandas," +
			"Dimensoes_Pneus "+
			"WHERE " +
			"Recapagens.oid_Pneu = Pneus.oid_Pneu " +
//			"AND " +
//			"Recapagens.oid_Fornecedor_Recapagem = Fornecedores.oid_Fornecedor " +
//			"AND fornecedores.oid_pessoa = pessoas.oid_pessoa " +
			"AND "+
			"Recapagens.oid_Fabricante_Banda = Fabricantes_bandas.oid_Fabricante_Banda " +
			"AND "+
			"Recapagens.oid_Banda = Bandas.oid_Banda " +
			"AND " +
			"Pneus.oid_dimensao_pneu = dimensoes_pneus.oid_dimensao_pneu ";

			if (ed.getOid_Recapagem()>0)
				sql+="AND Recapagens.oid_Recapagem="+ed.getOid_Recapagem()+ " ";
			else
				if (ed.getOid_Pneu()>0)
					sql+="AND Recapagens.oid_Pneu="+ed.getOid_Pneu()+ " ";
				else {
					sql+="AND Recapagens.oid_Empresa= "+ed.getOid_Empresa()+ " ";
				}
				if (ed.getOid_Fabricante_Banda()>0){
					  sql += "AND Recapagens.Oid_Fabricante_Banda= " + ed.getOid_Fabricante_Banda()+ " ";
				}
				if (ed.getOid_Banda()>0){
					  sql += "AND Recapagens.oid_Banda= " + ed.getOid_Banda()+ " ";
				}
				if (doValida(ed.getOid_Fornecedor())){
					  sql += "AND Recapagens.oid_Fornecedor_Recapagem = '" + ed.getOid_Fornecedor()+ "' ";
				}
				if (ed.getOid_Dimensao_Pneu()>0){
					  sql += "AND Pneus.oid_dimensao_pneu= " + ed.getOid_Dimensao_Pneu()+ " ";
				}

			sql += "ORDER BY " +
			" Recapagens.dt_Recapagem";
//			System.out.println(sql);
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(RecapagemED ed)");
		}
	}

	public ArrayList listaRecapagensEfetuadas(RecapagemED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT *, pessoas.nm_razao_social as nm_fornecedor " +
	  		",Recapagens.usuario_Stamp as usu_Stmp " +
	  		",Recapagens.dt_Stamp as dt_Stmp " +
	  		",Recapagens.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Recapagens " +
			"left join fornecedores " +
			"	left join pessoas " +
			" 		on pessoas.oid_pessoa = fornecedores.oid_pessoa " +
			" on fornecedores.oid_fornecedor = Recapagens.oid_Fornecedor_Recapagem, " +
			"Pneus, " +
			"Fabricantes_Bandas, " +
			"Bandas," +
			"Dimensoes_Pneus "+
			"WHERE " +
			"Recapagens.oid_Pneu = Pneus.oid_Pneu " +
//			"AND " +
//			"Recapagens.oid_Fornecedor_Recapagem = Fornecedores.oid_Fornecedor " +
			"AND "+
			"Recapagens.oid_Fabricante_Banda = Fabricantes_bandas.oid_Fabricante_Banda " +
			"AND "+
			"Recapagens.oid_Banda = Bandas.oid_Banda " +
			"AND " +
			"Pneus.oid_dimensao_pneu = dimensoes_pneus.oid_dimensao_pneu ";
			if (ed.getOid_Recapagem()>0)
				sql+="AND Recapagens.oid_Recapagem="+ed.getOid_Recapagem()+ " ";
			else
				if (ed.getOid_Pneu()>0)
					sql+="AND Recapagens.oid_Pneu="+ed.getOid_Pneu()+ " ";
				else {
					sql+="AND Recapagens.oid_Empresa= "+ed.getOid_Empresa()+ " ";
				}
			if (doValida(ed.getDt_Recapagem_Inicial())) {
				sql += " AND Recapagens.dt_Recapagem >= '" + ed.getDt_Recapagem_Inicial()+ "' ";
			}
			if (doValida(ed.getDt_Recapagem_Final())) {
				sql += " AND Recapagens.dt_Recapagem <= '" + ed.getDt_Recapagem_Final()+ "' ";
		    }
			if (ed.getOid_Dimensao_Pneu()>0){
				sql+="AND dimensoes_pneus.oid_dimensao_pneu= "+ed.getOid_Dimensao_Pneu()+ " ";
			}
			if (ed.getOid_Banda()>0){
				sql+="AND recapagens.oid_banda= "+ed.getOid_Banda()+ " ";
			}
			if (doValida(ed.getNr_Fogo())) {
				sql += " AND pneus.nr_fogo= '" + ed.getNr_Fogo()+ "' ";
		    }
			if (ed.getOid_Fabricante_Banda()>0) {
				sql += " AND fabricantes_bandas.oid_fabricante_banda= '" + ed.getOid_Fabricante_Banda()+ "' ";
		    }
			if (doValida(ed.getOid_Fornecedor()) ) {
				sql += " AND Recapagens.oid_fornecedor_recapagem= '" + ed.getOid_Fornecedor()+ "' ";
		    }

			sql += "ORDER BY " +
			"Dimensoes_Pneus.nm_dimensao_pneu , Bandas.nm_banda,  Pneus.nr_Fogo";

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
			  RecapagemED popula = new RecapagemED ();
				  popula.setOid_Empresa(res.getInt("oid_Empresa"));
				  popula.setVl_Recapagem(res.getDouble("vl_Recapagem"));
				  popula.setDt_Recapagem(FormataData.formataDataBT(res.getString("dt_Recapagem")));
				  popula.setNr_Nota_Fiscal_Recapagem(res.getInt("nr_Nota_Fiscal_Recapagem"));
				  popula.setNr_Fogo(res.getString("nr_Fogo"));
//				  popula.setNm_Fornecedor_Recapagem(res.getString("nm_Razao_Social"));
				  popula.setNm_Fornecedor_Recapagem(JavaUtil.doValida(res.getString("nm_fornecedor"))?res.getString("nm_fornecedor"):"");
				  popula.setNm_Fabricante_Banda(res.getString("nm_Fabricante_Banda"));
				  popula.setNm_Banda(res.getString("nm_Banda")+ " - L:" +FormataValor.formataValorBT(res.getDouble("nr_Largura"),1)+ " - P:" +FormataValor.formataValorBT(res.getDouble("nr_Profundidade"),1));
				  popula.setNr_Vida(res.getLong("nr_Vida"));
				  popula.setNm_Dimensao_Pneu(res.getString("nm_Dimensao_Pneu"));
			  list.add(popula);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(RecapagemED ed)");
		}
	}

	public RecapagemED getByRecord (RecapagemED ed) throws Excecoes {
		ArrayList lista = this.lista (ed);
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (RecapagemED) iterator.next ();
		}
		else {
			return new RecapagemED();
		}
	}

	public RecapagemED getLastRecord (RecapagemED ed) throws Excecoes {
		try {
			RecapagemED edVolta = new RecapagemED();
			sql="SELECT *, pes.nr_cnpj_cpf "+
	  		",Recapagens.usuario_Stamp as usu_Stmp " +
	  		",Recapagens.dt_Stamp as dt_Stmp " +
	  		",Recapagens.dm_Stamp as dm_Stmp " +
			"FROM Recapagens, Pneus, Fornecedores, Fabricantes_Bandas, Bandas, pessoas pes "+
			"WHERE "+
			"Recapagens.oid_Pneu = Pneus.oid_Pneu and "+
			"Recapagens.oid_Fornecedor_Recapagem = Fornecedores.oid_Fornecedor and "+
			"Fornecedores.oid_Pessoa = pes.oid_Pessoa and "+
			"Recapagens.oid_Fabricante_Banda = Fabricantes_bandas.oid_Fabricante_Banda and "+
			"Recapagens.oid_Banda = Bandas.oid_Banda and "+
			"Recapagens.oid_Pneu="+ed.getOid_Pneu()+" "+
			"ORDER BY " +
			"Recapagens.dt_Recapagem";
			ResultSet res = this.executasql.executarConsulta(sql);
			if (res.last()) {
				edVolta = populaRegistro(res);
			}
			return edVolta;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getLastRecord(RecapagemED ed)");
		}
	}

	private RecapagemED populaRegistro(ResultSet res) throws SQLException {
		RecapagemED ed = new RecapagemED();
		ed.setOid_Recapagem(res.getInt("oid_Recapagem"));
		ed.setOid_Pneu(res.getInt("oid_Pneu"));
		ed.setCd_Pneu(res.getString("cd_Pneu"));
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Fornecedor(res.getString("oid_Fornecedor_Recapagem"));
		ed.setOid_Fabricante_Banda(res.getLong("oid_Fabricante_Banda"));
		ed.setOid_Banda(res.getInt("oid_Banda"));
		ed.setVl_Recapagem(res.getDouble("vl_Recapagem"));
		ed.setDt_Recapagem(FormataData.formataDataBT(res.getString("dt_Recapagem")));
		ed.setNr_Nota_Fiscal_Recapagem(res.getInt("nr_Nota_Fiscal_Recapagem"));
		ed.setNr_Os_Recapagem(res.getString("nr_Os_Recapagem"));
		ed.setNr_Mm_Sulco_Recapagem(res.getDouble("nr_Mm_Sulco_Recapagem"));
		ed.setDm_Garantia_Recapagem(res.getString("dm_Garantia_Recapagem"));
		ed.setNr_Fogo(res.getString("nr_Fogo"));
		ed.setNr_Cnpj_Cpf(res.getString("nr_Cnpj_Cpf"));
//		ed.setNm_Fornecedor(res.getString("nm_Razao_Social"));
		ed.setNm_Fornecedor(JavaUtil.doValida(res.getString("nm_fornecedor"))?res.getString("nm_fornecedor"):"");
		ed.setCd_Fabricante_Banda(res.getString("cd_Fabricante_Banda"));
		ed.setNm_Fabricante_Banda(res.getString("nm_Fabricante_Banda"));
		ed.setCd_Banda(res.getString("cd_Banda"));
		ed.setNm_Banda(res.getString("nm_Banda"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		//Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
		return ed;
	}
}