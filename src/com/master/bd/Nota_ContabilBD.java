
package com.master.bd;

/**
 * @author Vinícius
 * date: 09/10/2008
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Nota_ContabilED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;


public class Nota_ContabilBD extends BancoUtil {

	private ExecutaSQL executasql;
	FormataDataBean dataFormatada = new FormataDataBean ();

	public Nota_ContabilBD(ExecutaSQL sql) {
		// super ();
		this.executasql = sql;
	}

	// =================== Inclui =======================\\
	public Nota_ContabilED inclui(Nota_ContabilED ed) throws Excecoes {


		String sql = "select max(oid_Nota_Contabil) as result, max (nr_Nota) as nr_Nota from notas_contabeis ";
		long valOid = 1;
		long nr_Nota = 0;

		Nota_ContabilED nota_ContabilED = new Nota_ContabilED();

		try {
			ResultSet rs = executasql.executarConsulta(sql);
			while (rs.next()) {
				valOid = rs.getLong("result");
				nr_Nota = rs.getLong("nr_Nota");
			}

			valOid++;
			nr_Nota++;

			sql = " insert into notas_contabeis ( " 
					+ "oid_Nota_Contabil, "
					+ "nr_Nota, " 
					+ "dt_Inicial, " 
					+ "dt_Final, "
					+ "tx_Nota) values";

			
			sql += "(" + valOid + "," 
					+ nr_Nota + ","
					+ (doValida(ed.getDt_Inicial()) ? "'" + ed.getDt_Inicial()+ "'" : "null") + ", "
					+ (doValida(ed.getDt_Final()) ? "'" + ed.getDt_Final()+ "'" : "null") + ", "
					+ (doValida(ed.getTx_Nota()) ? "'" + ed.getTx_Nota()+ "'" : "null") + ")";

				executasql.executarUpdate(sql);
				
				nota_ContabilED.setOid_Nota_Contabil(valOid);


		} catch (Exception exc) {
			exc.printStackTrace();
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao incluir a Nota Contabil");
			excecoes.setMetodo("inclui");
			excecoes.setExc(exc);
			throw excecoes;
		}
		return nota_ContabilED;

	}


	// =============== Altera =======================\\
	public void altera(Nota_ContabilED ed) throws Excecoes {
		String sql = null;
		try {
			
			sql = "UPDATE notas_contabeis " + "SET "
					+ " nr_Nota = " + ed.getNr_Nota() 
					+ " ,dt_Inicial = " + (doValida(ed.getDt_Inicial()) ? "'" + ed.getDt_Inicial()+ "'" : "null") 
					+ " ,dt_Final = " + (doValida(ed.getDt_Final()) ? "'" + ed.getDt_Final()+ "'" : "null") 
					+ " ,tx_Nota = " + (doValida(ed.getTx_Nota()) ? "'" + ed.getTx_Nota()+ "'" : "null") ;
			
			sql += " where oid_Nota_Contabil = '" + ed.getOid_Nota_Contabil() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "altera (Nota_ContabilED ed)");
		}
	}
	
	// ==================== Lista ===========================\\
	public ArrayList lista(Nota_ContabilED ed) throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList();
		try {

			sql = " SELECT " +
			  " notas_contabeis.oid_Nota_Contabil, " +
			  " notas_contabeis.nr_Nota, " +
			  " notas_contabeis.dt_Inicial, " +
			  " notas_contabeis.dt_Final,  " +
			  " notas_contabeis.tx_Nota " +
			  " FROM " +
			  " notas_contabeis" +
			  " WHERE 1=1 ";
			
			if (ed.getOid_Nota_Contabil() > 0)
				sql += "   AND notas_contabeis.oid_Nota_Contabil = " + ed.getOid_Nota_Contabil();
			else {
				if (ed.getNr_Nota() > 0)
					sql += "   AND notas_contabeis.nr_Nota = " + ed.getNr_Nota();
				
				if (doValida(ed.getDt_Inicial())) {
				    sql += " AND notas_contabeis.dt_Inicial = '" + ed.getDt_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Final())) {
				    sql += " AND notas_contabeis.dt_Final = '" + ed.getDt_Final()+ "' ";
				}
			}	
	            
			sql += " ORDER BY notas_contabeis.nr_Nota";

			ResultSet res = this.executasql.executarConsulta(sql);

			
			while (res.next()) {
				Nota_ContabilED edVolta = new Nota_ContabilED();

				edVolta.setOid_Nota_Contabil(new Long(res.getString("oid_Nota_Contabil")).longValue());
				edVolta.setNr_Nota(res.getLong("nr_Nota"));
				edVolta.setDt_Inicial(FormataData.formataDataBT(res.getString("dt_Inicial")));
				edVolta.setDt_Final(FormataData.formataDataBT(res.getString("dt_Final")));
				edVolta.setTx_Nota(res.getString("tx_Nota"));
				if (edVolta.getTx_Nota().indexOf(13)>0) {
					if (edVolta.getTx_Nota().indexOf(13)>72) 
						edVolta.setTx_Assunto(edVolta.getTx_Nota().substring(0,72));
					else	
						edVolta.setTx_Assunto(edVolta.getTx_Nota().substring(0, edVolta.getTx_Nota().indexOf(13)));
				} else {
					edVolta.setTx_Assunto(edVolta.getTx_Nota());
				}
				
				list.add(edVolta);
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "lista(Nota_ContabilED ed)");
		}
	}


	// ==========================================================================\\
	public Nota_ContabilED getByRecord(Nota_ContabilED ed) throws Excecoes {
		String sql = null;
		Nota_ContabilED edQBR = new Nota_ContabilED();
		try {
			sql = " SELECT " +
			  " notas_contabeis.oid_Nota_Contabil, " +
			  " notas_contabeis.nr_Nota, " +
			  " notas_contabeis.dt_Inicial, " +
			  " notas_contabeis.dt_Final,  " +
			  " notas_contabeis.tx_Nota " +
			  " FROM " +
			  " notas_contabeis" +
			  " WHERE 1=1" ;
			
			if (ed.getOid_Nota_Contabil()>0) 
				sql+=" and oid_Nota_Contabil = " + ed.getOid_Nota_Contabil();
			if (ed.getNr_Nota()>0)
				sql+=" and nr_Nota = " + ed.getNr_Nota();
				
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				
				edQBR.setOid_Nota_Contabil(new Long(res.getString("oid_Nota_Contabil")).longValue());
				edQBR.setNr_Nota(res.getLong("nr_Nota"));
				edQBR.setDt_Inicial(FormataData.formataDataBT(res.getString("dt_Inicial")));
				edQBR.setDt_Final(FormataData.formataDataBT(res.getString("dt_Final")));
				edQBR.setTx_Nota(res.getString("tx_Nota"));

			}
		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(),
					"getByRecord(Nota_ContabilED ed)");
		}
		return edQBR;
	}

}