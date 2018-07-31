package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.ed.LicenciamentoED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;
import com.master.util.Data;

public class LicenciamentoBD extends BancoUtil {


    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public LicenciamentoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public LicenciamentoED inclui(LicenciamentoED ed) throws Excecoes {

        String sql = null;
        try {

        	ed.setOid_Licenciamento(String.valueOf((getAutoIncremento ("OID_Licenciamento" , "Licenciamentos"))));

        	if (ed.getOid_Licenciamento() != null && "0".equals(ed.getOid_Licenciamento())){
        		ed.setOid_Licenciamento("1");
        	}

            sql = " INSERT INTO Licenciamentos (" +
                  "     OID_Licenciamento" +
                  "     ,DT_Limite" +
                  "     ,nr_Dezena_Placa" +
                  "     ,DT_STAMP" +
                  "     ,USUARIO_STAMP" +
                  "     ,DM_STAMP ) " +
                  " VALUES (" +
                         ed.getOid_Licenciamento() +
                  ",'" + ed.getDt_Limite() + "'" +
                  ",'" + ed.getNr_Dezena_Placa() + "'" +
                  ",'" + ed.getDt_stamp() + "'" +
                  ",'" + ed.getUsuario_Stamp() + "'" +
                  ",'" + ed.getDm_Stamp() + "')";

                executasql.executarUpdate(sql);

                ed.setOid_Licenciamento(ed.getOid_Licenciamento());
                return ed;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(LicenciamentoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Licenciamentos SET ";
            sql +="     DT_Limite = '" + ed.getDt_Limite() + "',";
            sql +="     Nr_Dezena_Placa = '" + ed.getNr_Dezena_Placa() + "',";
            sql +="     DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql +="     USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql +="     DM_STAMP = '" + ed.getDm_Stamp() + "'";
            sql +=" WHERE oid_Licenciamento = " + ed.getOid_Licenciamento();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "alterar()");
        }
    }

    public void deleta(LicenciamentoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " DELETE FROM Licenciamentos WHERE oid_Licenciamento = " + ed.getOid_Licenciamento() ;

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(LicenciamentoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT Licenc.* " +
            	  " FROM Licenciamentos Licenc " +
                  " WHERE 1=1 " ;
            if (util.doValida(ed.getOid_Licenciamento()))
                sql += " and Licenc.oid_Licenciamento = " + ed.getOid_Licenciamento();
            else {
                if (util.doValida(ed.getDt_Limite()))
                    sql += " and Licenc.DT_Limite = '" + ed.getDt_Limite() + "'";
                if (util.doValida(ed.getNr_Dezena_Placa()))
                    sql += " and Licenc.Nr_Dezena_Placa = '" + ed.getNr_Dezena_Placa() + "'";

            }
            sql +=" ORDER BY oid_Licenciamento ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                LicenciamentoED edVolta = new LicenciamentoED();

                edVolta.setOid_Licenciamento(res.getString("oid_Licenciamento"));
                edVolta.setNr_Dezena_Placa(res.getString("Nr_Dezena_Placa"));

                FormataDataBean DataFormatada = new FormataDataBean ();
                DataFormatada.setDT_FormataData (res.getString ("DT_Limite"));
                edVolta.setDt_Limite (DataFormatada.getDT_FormataData ());

                list.add(edVolta);
            }
            return list;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public LicenciamentoED getByRecord(LicenciamentoED ed) throws Excecoes {

        LicenciamentoED edVolta = new LicenciamentoED();
        try {

            String sql = " SELECT Licenc.* " +
                " FROM Licenciamentos Licenc" +
                " WHERE 1=1 " ;

            if (util.doValida(ed.getOid_Licenciamento()))
                sql += " and Licenc.oid_Licenciamento = " + ed.getOid_Licenciamento();
            else {
                if (util.doValida(ed.getDt_Limite()))
                    sql += " and Licenc.DT_Limite = '" + ed.getDt_Limite() + "'";
                if (util.doValida(ed.getNr_Dezena_Placa()))
                    sql += " and Licenc.Nr_Dezena_Placa = '" + ed.getNr_Dezena_Placa() + "'";

            }
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new LicenciamentoED();
                edVolta.setOid_Licenciamento(res.getString("oid_Licenciamento"));
                FormataDataBean DataFormatada = new FormataDataBean ();
                DataFormatada.setDT_FormataData (res.getString ("DT_Limite"));
                edVolta.setDt_Limite (DataFormatada.getDT_FormataData ());
                edVolta.setNr_Dezena_Placa(res.getString("Nr_Dezena_Placa"));

            }
            return edVolta;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord()");
        }
    }

    public boolean getLiberacao_Licenciamento(String oid_Veiculo) throws Excecoes {

        boolean DM_Liberacao_Licenciamento = false;
        String DT_Validade_Licenciamento = null;
        String DT_Ano_Validade_Licenciamento = null;
        String DT_Ano_Hoje = null;
        String NR_Dezena_Placa_Veiculo = null;
        String NR_Dezena_Licenciamento = null;
        LicenciamentoED edVolta = new LicenciamentoED();

        try {

            String sql = " SELECT DT_Validade_Licenciamento, nr_licenca " +
                " FROM complementos_veiculos compl_veic" +
                " WHERE compl_veic.oid_veiculo = '" + oid_Veiculo + "'";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                if (!util.doValida(res.getString ("nr_licenca"))){
                	return DM_Liberacao_Licenciamento=false;
                }

                if (util.doValida(res.getString ("DT_Validade_Licenciamento"))){
                	DT_Ano_Validade_Licenciamento = res.getString ("DT_Validade_Licenciamento").substring(0, 4);
                	DT_Ano_Hoje = Data.getDataYMD().substring(0, 4);
                	if (DT_Ano_Validade_Licenciamento.equals(DT_Ano_Hoje)){
                		return DM_Liberacao_Licenciamento=true;
                	}else{

                		NR_Dezena_Placa_Veiculo = oid_Veiculo.substring(5, 7);

                		edVolta.setNr_Dezena_Placa(NR_Dezena_Placa_Veiculo);

                		edVolta = this.getByRecord(edVolta);

                		int nr_Validade = Data.diferencaDias(Data.getDataDMY(), edVolta.getDt_Limite());

                	    if (nr_Validade <= 50){
                    		return DM_Liberacao_Licenciamento=false;
                		}else{
                    		return DM_Liberacao_Licenciamento=true;
                		}
                	}
            	}

            }
            return DM_Liberacao_Licenciamento;
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getLiberacao_Licenciamento()");
        }
    }

}
