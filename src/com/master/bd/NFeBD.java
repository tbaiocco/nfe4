package com.master.bd;

import java.sql.ResultSet;

import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class NFeBD {

	private ExecutaSQL executasql;

	FormataDataBean dataFormatada = new FormataDataBean();

	public NFeBD(ExecutaSQL sql) {
		this.executasql = sql;
	}

	public Nota_Fiscal_EletronicaED numeraNFe(Nota_Fiscal_EletronicaED ed,String nmSerie) throws Exception {

    	// .println("setNrSerieNotaFromAIDOF");

		ResultSet rs = null;
		long nrFinal = 0, oid_AIDOF = 0;
		try {
			if (!JavaUtil.doValida(nmSerie))
				throw new Excecoes("S�rie n�o informada!");

			String sql = "SELECT " +
						 "NR_Proximo, " +
						 "NR_Final, " +
						 "OID_Aidof, " +
						 "NM_Serie " +
						 "FROM AIDOF " +
						 "WHERE " +
						 "CD_Aidof = '" + nmSerie + "'";

			//*** Seta NR e S�rie da Nota Fiscal
			rs = this.executasql.executarConsulta(sql);
			while (rs.next()) {
				ed.setNm_serie(rs.getString("NM_Serie"));
				ed.setNr_nota_fiscal(rs.getLong("NR_Proximo"));
				nrFinal = rs.getLong("NR_Final");
				oid_AIDOF = rs.getLong("OID_Aidof");
			}
			if (oid_AIDOF < 1)
				throw new Excecoes("AIDOF n�o encontrado : "
						+ nmSerie);

			//*** Verifica por seguran�a se NR ja existe
//			while (JavaUtil.doExiste("Notas_Fiscais", "NR_Nota_Fiscal = "
//					+ ed.getNr_nota_fiscal() +
//					" and NM_Serie = '" + ed.getNm_serie() + "'" +
//					" and oid_Pessoa = '" + ed.getOid_pessoa() + "'"
//					))
//				ed.setNr_nota_fiscal(ed.getNr_nota_fiscal() + 1);

			//*** Se Fora do "Range" da NF informa erro
			if ((ed.getNr_nota_fiscal() + 1) > nrFinal)
				throw new Excecoes(
						"N�mero sequencial da Nota Fiscal esgotado! Atualize no AIDOF!");

	    	// .println("setNrSerieNotaFromAIDOF ed.getNr_nota_fiscal="+ed.getNr_nota_fiscal());

			sql = "UPDATE AIDOF SET " +
				  "NR_Proximo= " + (ed.getNr_nota_fiscal() + 1) + " " +
			      "WHERE OID_Aidof = " + oid_AIDOF;
			this.executasql.executarUpdate(sql);

			sql = "UPDATE Notas_Fiscais SET " +
				  "DM_Impresso = 'S' " +
				  ",DM_Situacao = 'F' " +
				  ",DM_Finalizado = 'F' " +
            	  ",NR_Nota_Fiscal = '"+ed.getNr_nota_fiscal()+"' " +
            	  "WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";

System.out.println("numeraNFe ->>" +sql);

			this.executasql.executarUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
			throw new com.master.util.Excecoes(e.getMessage(), e, this.getClass().getName(), "numeraNFe()");
		} finally {
			rs.close();
		}

		return ed;
	}

}
