package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Movimento_Contabil_TempED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.FormataValor;
import com.master.util.FormataData;

/**
 * @author Régis Steigleder
 * @serial Movimentos_Contabeis_Temp
 * @serialData 23/11/2005
 */
public class Movimento_Contabil_TempBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Movimento_Contabil_TempBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Movimento_Contabil_TempED inclui(Movimento_Contabil_TempED ed) throws Excecoes {
    	String sql = null;
        try {
            ed.setOid_Movimento_Contabil(getAutoIncremento("oid_Movimento_Contabil", "Movimentos_Contabeis_Temp"));

            if ( ed.getOid_Movimento_Contabil() == 0 ){
            	ed.setOid_Movimento_Contabil(1);
            }


            if ( ed.getNr_Lote() == 0 ) ed.setNr_Lote(getAutoIncremento("nr_Lote", "Movimentos_Contabeis_Temp"));

            if ( ed.getNr_Lote() == 0 ){
            	ed.setNr_Lote(1);
            }

            ed.setCd_Lote(ed.getDt_Movimento() + ed.getOid_Unidade() + ed.getOid_Origem());
            sql = "INSERT INTO " +
            " Movimentos_Contabeis_Temp " +
            " ( " +
			" oid_Movimento_Contabil, " +
			" oid_Usuario, " +
			" dt_Movimento, " +
			" oid_Unidade, " +
			" oid_Origem, " +
			" oid_Conta, " +
			" tx_Historico, " +
			" tx_Historico_Complementar, " +
			" dm_Debito_Credito, " +
			" vl_Lancamento, " +
			" dt_Digitacao, " +
			" tx_Chave_Origem, " +
			" nr_Lote, " +
			" cd_Lote, " +
			" oid_Historico, " +
			" oid_Centro_Custo, " +
			" nr_Lote_Originario, " +
			" dm_tela " +
			" ) " +
			" VALUES " +
			" ( " +
			ed.getOid_Movimento_Contabil() +
			",'" + ed.getOid_Usuario() +
			"','" + ed.getDt_Movimento() +
			"'," + ed.getOid_Unidade() +
			"," + ed.getOid_Origem() +
			"," + ed.getOid_Conta() +
			",'" + ed.getTx_Historico() +
			"','" + this.getValueDef(ed.getTx_Historico_Complementar()," ") +
			"','" + ed.getDm_Debito_Credito() +
			"'," + ed.getVl_Lancamento() +
			",'" + ed.getDt_Digitacao() +
			"','" + ed.getTx_Chave_Origem() +
			"'," + ed.getNr_Lote() +
			",'" + ed.getCd_Lote() +
			"'," + ed.getOid_Historico() +
			"," + ed.getOid_Centro_Custo() +
			"," + ed.getNr_Lote_Originario() +
			"," + ed.getDm_Tela() +
			" )";

            executasql.executarUpdate(sql);

            return ed;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Movimento_Contabil_TempED ed)");
        }
    }

    public void altera(Movimento_Contabil_TempED ed) throws Excecoes {
    	ed.setCd_Lote(ed.getDt_Movimento() + ed.getOid_Unidade() + ed.getOid_Origem());
    	String sql = null;
        try {
            sql = "UPDATE " +
            " Movimentos_Contabeis_Temp " +
            "SET " +
            " oid_Movimento_Contabil = oid_Movimento_Contabil, " +
            " oid_Usuario = " + ed.getOid_Usuario() + ", " +
			" dt_Movimento = '" + ed.getDt_Movimento() + "', " +
			" oid_Unidade = " + ed.getOid_Unidade() + ", " +
			" oid_Origem = " + ed.getOid_Origem() + ", " +
			" oid_Conta = " + ed.getOid_Conta() + ", " +
			" tx_Historico = '" + ed.getTx_Historico() + "', " +
			" tx_Historico_Complementar = '" + this.getValueDef(ed.getTx_Historico_Complementar()," ") + "', " +
			" dm_Debito_Credito = '" + ed.getDm_Debito_Credito() + "', " +
			" vl_Lancamento = " + ed.getVl_Lancamento() + ", " +
			" dt_Digitacao = '" + ed.getDt_Digitacao() + "', " +
			" tx_Chave_Origem = '" + ed.getTx_Chave_Origem() + "', " +
			" nr_Lote = " + ed.getNr_Lote() + ", " +
			" cd_Lote = '" + ed.getCd_Lote() + "', " +
			" oid_Historico = " + ed.getOid_Historico() + ", " +
			" oid_Centro_Custo = " + ed.getOid_Centro_Custo() + ", " +
            " nr_Lote_Originario = " + ed.getNr_Lote_Originario() + ", " +
            " Dm_Tela = " + ed.getDm_Tela() +
            " WHERE " +
            " oid_Movimento_Contabil = " + ed.getOid_Movimento_Contabil();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Movimento_Contabil_TempED ed)");
        }
    }

    public void deletaCP(Movimento_Contabil_TempED ed) throws Excecoes {
        String sql = null;
        try {
            sql = "DELETE FROM " +
            " Movimentos_Contabeis_Temp " +
            " WHERE " +
            " oid_Usuario = " + ed.getOid_Usuario();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deletaCP(Movimento_Contabil_TempED ed)");
        }
    }

    public void deleta(Movimento_Contabil_TempED ed) throws Excecoes {
        String sql = null;
        try {
            sql = "DELETE FROM " +
            " Movimentos_Contabeis_Temp " +
            " WHERE " +
            " oid_Movimento_Contabil = " + ed.getOid_Movimento_Contabil();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Movimento_Contabil_TempED ed)");
        }
    }

    public ArrayList lista(Movimento_Contabil_TempED ed,String gravaLancamentos) throws Excecoes {
        String sql = null;
        ArrayList list = new ArrayList();
        try {
        	sql = "SELECT * " +
			" FROM " +
			" Movimentos_Contabeis_Temp " +
            " WHERE " +
            " oid_Usuario = " + ed.getOid_Usuario() +
            " ORDER BY "+
            " oid_Movimento_Contabil";

            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                Movimento_Contabil_TempED edVolta = new Movimento_Contabil_TempED();
        		edVolta.setOid_Movimento_Contabil(res.getInt("Oid_Movimento_Contabil"));
        		edVolta.setOid_Usuario(res.getInt("Oid_Usuario"));
        		edVolta.setDt_Movimento(FormataData.formataDataBT(res.getString("Dt_Movimento")));
        		edVolta.setOid_Unidade(res.getInt("Oid_Unidade"));
        		edVolta.setOid_Origem(res.getInt("Oid_Origem"));
        		edVolta.setOid_Conta(res.getInt("Oid_Conta"));
        		edVolta.setTx_Historico(res.getString("Tx_Historico"));
        		edVolta.setTx_Historico_Complementar(res.getString("Tx_Historico_Complementar"));
        		edVolta.setDm_Debito_Credito(res.getString("Dm_Debito_Credito"));
        		edVolta.setVl_Lancamento(res.getDouble("vl_Lancamento"));
        		edVolta.setDt_Digitacao(FormataData.formataDataBT(res.getString("Dt_Digitacao")));
        		edVolta.setTx_Chave_Origem(res.getString("Tx_Chave_Origem"));
        		edVolta.setNr_Lote(res.getInt("nr_Lote"));
        		edVolta.setCd_Lote(res.getString("Cd_Lote"));
        		edVolta.setOid_Historico(res.getInt("oid_Historico"));
        		edVolta.setOid_Centro_Custo(res.getInt("oid_Centro_Custo"));
        		edVolta.setNr_Lote_Originario(res.getInt("nr_Lote_Originario"));
                list.add(edVolta);
            }
            return list;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Movimento_Contabil_TempED ed, String gravaLancamentos )");
        }
    }

    public ArrayList lista(Movimento_Contabil_TempED ed) throws Excecoes {
        String sql = null;
        ArrayList list = new ArrayList();
        try {
        	sql = "SELECT * " +
			" FROM " +
			" Movimentos_Contabeis_Temp as m , " +
			" Unidades as u , " +
			" Origens as o , " +
			" Contas as c , " +
			" Historicos as h, " +
			" Pessoas as p, " +
			" Centros_Custos as cc " +
            " WHERE " +
            " m.oid_Usuario = "+ ed.getOid_Usuario() + " and " +
            " m.oid_Movimento_Contabil <> " + ed.getOid_Movimento_Contabil() + " and " +
        	" u.oid_unidade = m.oid_unidade and " +
			" o.oid_origem = m.oid_origem and  " +
			" c.oid_conta = m.oid_conta and  " +
			" h.oid_historico = m.oid_historico and " +
			" p.oid_pessoa = u.oid_pessoa and " +
			" cc.oid_centro_custo = m.oid_centro_custo " +
            " ORDER BY "+
            " oid_Movimento_Contabil";

            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                Movimento_Contabil_TempED edVolta = new Movimento_Contabil_TempED();
                edVolta = populaRegistro(res, edVolta);
                list.add(edVolta);
            }
            return list;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Movimento_Contabil_TempED ed)");
        }
    }

    public Movimento_Contabil_TempED getByRecord(Movimento_Contabil_TempED ed) throws Excecoes {
	    String sql = null;
	    Movimento_Contabil_TempED edQBR = new Movimento_Contabil_TempED();
	    try{
			sql = "SELECT * " +
			" FROM " +
			" Movimentos_Contabeis_Temp as m , " +
			" Unidades as u , " +
			" Origens as o , " +
			" Contas as c , " +
			" Historicos as h, " +
			" Pessoas as p, " +
			" Centros_Custos as cc " +
			" WHERE ";
			if (ed.getOid_Movimento_Contabil()> 0 ) {
				sql += " oid_Movimento_Contabil = " + ed.getOid_Movimento_Contabil() + " and ";
			}else{
				if (ed.getOid_Usuario()> 0 ){
					sql += " oid_Usuario = "+ ed.getOid_Usuario() + " and ";
				}
			}
			sql += " u.oid_unidade = m.oid_unidade and " +
			" o.oid_origem = m.oid_origem and  " +
			" c.oid_conta = m.oid_conta and  " +
			" h.oid_historico = m.oid_historico and " +
			" p.oid_pessoa = u.oid_pessoa " +
			" and cc.oid_centro_custo = m.oid_centro_custo " +
			"order by m.oid_Movimento_Contabil desc ";

			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next())
			{
			   edQBR = populaRegistro(res, edQBR);
			}
	    }
	    catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Movimento_Contabil_TempED ed)");
	    }
	    return edQBR;
    }

    public Movimento_Contabil_TempED getFirstRecord(Movimento_Contabil_TempED ed) throws Excecoes {
	    String sql = null;
	    Movimento_Contabil_TempED edQBR = new Movimento_Contabil_TempED();

	    try{
			sql = "SELECT * " +
			" FROM " +
			" Movimentos_Contabeis_Temp as m , " +
			" Unidades as u , " +
			" Origens as o , " +
			" Contas as c , " +
			" Historicos as h, " +
			" Pessoas as p, " +
			" Centros_Custos as cc " +
			" WHERE " +
			" oid_Usuario = "+ ed.getOid_Usuario() + " and " +
			" u.oid_unidade = m.oid_unidade and " +
			" o.oid_origem = m.oid_origem and  " +
			" c.oid_conta = m.oid_conta and  " +
			" h.oid_historico = m.oid_historico and " +
			" p.oid_pessoa = u.oid_pessoa " +
			" and cc.oid_centro_custo = m.oid_centro_custo " +
			"order by m.oid_Movimento_Contabil ";

			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			if (res.next()) edQBR = populaRegistro(res, edQBR);

	    }
	    catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Movimento_Contabil_TempED ed)");
	    }
	    return edQBR;
    }

    public ArrayList getLista(Movimento_Contabil_TempED ed) throws Excecoes {
        String sql = null;
        ArrayList list = new ArrayList();
        try {
        	sql = "SELECT * " +
			" FROM " +
			" Movimentos_Contabeis_Temp as m , " +
			" Unidades as u , " +
			" Origens as o , " +
			" Contas as c , " +
			" Historicos as h, " +
			" Pessoas as p, " +
			" Centros_Custos as cc " +
            " WHERE " +
            " m.oid_Usuario = "+ ed.getOid_Usuario() + " and " +
        	" u.oid_unidade = m.oid_unidade and " +
			" o.oid_origem = m.oid_origem and  " +
			" c.oid_conta = m.oid_conta and  " +
			" h.oid_historico = m.oid_historico and " +
			" p.oid_pessoa = u.oid_pessoa and " +
			" cc.oid_centro_custo = m.oid_centro_custo " +
            " ORDER BY "+
            " oid_Movimento_Contabil";

        	ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Movimento_Contabil_TempED edVolta = new Movimento_Contabil_TempED();
                edVolta = populaRegistro(res, edVolta);
                list.add(edVolta);
            }
            return list;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Movimento_Contabil_TempED ed)");
        }
    }

    /**
     * Dependendo do tipo de lote que estiver no banco:
     * se for gravado pela tela 2 retorna "ctbM002.jsp"
     * se for gravado pela tela 4 retorna "ctbM004.jsp"
     * @param ed
     * @return "ctbM004.jsp" : "ctbM002.jsp" ;
     * @throws Excecoes
     */

    public String getQualTela(Movimento_Contabil_TempED ed) throws Excecoes {
	    String sql = null;
	    int dmTela = 0;
	    try{
			sql = "SELECT " +
			" distinct dm_tela " +
			" FROM " +
			" Movimentos_Contabeis_Temp " +
			" WHERE " +
			" oid_Usuario = " + ed.getOid_Usuario()  ;

			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next())
			{
			   dmTela = res.getInt("dm_tela"); // tela que gravou o lançamento
			}
	    }
	    catch(Exception e){
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "getSomaConta(SistemaED ed)");
	    }
	    return  dmTela == 4  ? "ctbM004.jsp" : "ctbM002.jsp" ;
    }

	private Movimento_Contabil_TempED  populaRegistro(ResultSet res, Movimento_Contabil_TempED edVolta) throws SQLException {

		edVolta.setOid_Movimento_Contabil(res.getInt("Oid_Movimento_Contabil"));
		edVolta.setOid_Usuario(res.getInt("Oid_Usuario"));
		edVolta.setDt_Movimento(FormataData.formataDataBT(res.getString("Dt_Movimento")));
		edVolta.setOid_Unidade(res.getInt("Oid_Unidade"));
		edVolta.setOid_Origem(res.getInt("Oid_Origem"));
		edVolta.setOid_Conta(res.getInt("Oid_Conta"));
		edVolta.setTx_Historico(res.getString("Tx_Historico"));
		edVolta.setTx_Historico_Complementar(res.getString("Tx_Historico_Complementar"));
		edVolta.setDm_Debito_Credito(res.getString("Dm_Debito_Credito"));
		edVolta.setVl_Lancamento_TX(FormataValor.formataValorBT(res.getDouble("Vl_Lancamento"), 2));
		edVolta.setDt_Digitacao(FormataData.formataDataBT(res.getString("Dt_Digitacao")));
		edVolta.setTx_Chave_Origem(res.getString("Tx_Chave_Origem"));
		edVolta.setNr_Lote(res.getInt("nr_Lote"));
		edVolta.setCd_Lote(res.getString("Cd_Lote"));
		edVolta.setCd_Unidade(res.getString("cd_Unidade"));
		edVolta.setNm_Fantasia(res.getString("nm_Fantasia"));
		edVolta.setCd_Origem(res.getString("cd_Origem"));
		edVolta.setNm_Origem(res.getString("nm_Origem"));
		edVolta.setCd_Conta(res.getString("cd_Conta"));
		edVolta.setNm_Conta(res.getString("nm_Conta"));
		edVolta.setCd_Estrutural(res.getString("cd_Estrutural"));
		edVolta.setDm_Tipo_Conta(res.getString("dm_Tipo_Conta"));
		edVolta.setVl_Lancamento(res.getDouble("vl_Lancamento"));
		edVolta.setCd_Historico(res.getString("cd_Historico"));
		edVolta.setOid_Historico(res.getInt("oid_Historico"));
		edVolta.setOid_Centro_Custo(res.getInt("Oid_Centro_Custo"));
		edVolta.setCd_Centro_Custo(res.getString("cd_Centro_Custo"));
		edVolta.setNm_Centro_Custo(res.getString("nm_Centro_Custo"));
		edVolta.setDm_Centro_Custo(res.getString("dm_Centro_Custo"));
		edVolta.setNr_Lote_Originario(res.getInt("nr_Lote_Originario"));

		return edVolta;
	}
}
