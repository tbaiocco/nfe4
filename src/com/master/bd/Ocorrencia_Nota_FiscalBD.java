package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.rl.Ocorrencia_Nota_FiscalRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class Ocorrencia_Nota_FiscalBD  {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria(executasql);

    public Ocorrencia_Nota_FiscalBD(ExecutaSQL sql) {
      this.executasql = sql;
      util = new Utilitaria(this.executasql);
    }

    public Ocorrencia_Nota_FiscalED inclui(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        try {
            //ed.setOID_Ocorrencia_Nota_Fiscal(getAutoIncremento("oid_Ocorrencia_Nota_Fiscal", "Ocorrencias_Notas_Fiscais"));
            ed.setOID_Ocorrencia_Nota_Fiscal(new Long(String.valueOf(System.currentTimeMillis()).toString()).longValue());

            sql = " INSERT INTO Ocorrencias_Notas_Fiscais (" +
            	  "		OID_Ocorrencia_Nota_Fiscal" +
            	  "		,OID_Nota_Fiscal" +
            	  "		,OID_Tipo_Ocorrencia" +
            	  "		,DT_Ocorrencia_Nota_Fiscal" +
            	  "		,HR_Ocorrencia_Nota_Fiscal" +
            	  "		,TX_Observacao" +
            	  "		,TX_Observacao_Cliente" +
            	  "		,NM_Pessoa" +
            	  "		,DM_Origem_Ocorrencia)" +
            	  " VALUES ";
            sql +="(" + ed.getOID_Ocorrencia_Nota_Fiscal() +
            	  ",'" + ed.getOID_Nota_Fiscal() +
            	  "'," + ed.getOID_Tipo_Ocorrencia() +
            	  ",'" + ed.getDT_Ocorrencia_Nota_Fiscal() +
            	  "','"+ ed.getHR_Ocorrencia_Nota_Fiscal() +
            	  "','" + ed.getTX_Observacao() +
            	  "','" + ed.getTX_Observacao_Cliente() +
            	  "','" + ed.getNM_Pessoa() +
            	  "','" + ed.getDM_Origem_Ocorrencia()+ "')";
            executasql.executarUpdate(sql);
            if (util.doValida(ed.getDM_Pendencia()))
            {
                sql = " UPDATE Notas_Fiscais SET DM_Pendencia = '" +ed.getDM_Pendencia()+ "'" +
                	  " WHERE  Notas_Fiscais.oid_Nota_Fiscal = '" +ed.getOID_Nota_Fiscal()+ "'";
                executasql.executarUpdate(sql);
            }
            return ed;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        String sql = null;

        try {
            sql = " UPDATE Ocorrencias_Notas_Fiscais SET " +
            	  "		OID_Tipo_Ocorrencia= " + ed.getOID_Tipo_Ocorrencia() +
            	  "		,TX_Observacao = '" + ed.getTX_Observacao() + "'";
            sql +=" WHERE oid_Ocorrencia_Nota_Fiscal = " + ed.getOID_Ocorrencia_Nota_Fiscal();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " DELETE FROM Ocorrencias_Notas_Fiscais " +
                  " WHERE ";

            if (ed.getOID_Ocorrencia_Nota_Fiscal() > 0)
            	sql += " oid_Ocorrencia_Nota_Fiscal = "+ ed.getOID_Ocorrencia_Nota_Fiscal();
            else
            	if (Utilitaria.doValida(ed.getOID_Nota_Fiscal())) 
            		sql += " oid_nota_Fiscal = '" + ed.getOID_Nota_Fiscal() +"' " ;

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"deleta()");
        }
    }

    public ArrayList lista(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {
            sql = " SELECT OID_Ocorrencia_Nota_Fiscal" +
            	  "		   ,DT_Ocorrencia_Nota_Fiscal" +
            	  "		   ,HR_Ocorrencia_Nota_Fiscal" +
            	  "		   ,ocorrencias_notas_fiscais.TX_Observacao" +
            	  "		   ,Notas_Fiscais.oid_Nota_Fiscal" +
            	  "		   ,Notas_Fiscais.NR_Nota_Fiscal" +
            	  "		   ,Notas_Fiscais.DM_Tipo_Nota_Fiscal" +
            	  "	       ,Notas_Fiscais.DT_Emissao" +
            	  "		   ,Tipos_Ocorrencias.NM_Tipo_Ocorrencia" +
            	  "		   ,Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente" +
            	  "		   ,Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario" +
            	  "		   ,Notas_Fiscais.DM_Pendencia " +
            	  " FROM Ocorrencias_Notas_Fiscais, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Tipos_Ocorrencias ";
            sql +=" WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
            sql +="   AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
            sql +="   AND Ocorrencias_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
            sql +="   AND Ocorrencias_Notas_Fiscais.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
            sql +="   AND Ocorrencias_Notas_Fiscais.oid_Ocorrencia_Nota_Fiscal > 0 ";

            if (util.doValida(ed.getOID_Nota_Fiscal())) {
                sql += " and Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
            }
            if (ed.getNR_Nota_Fiscal() > 0) {
                sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
            }
            if (ed.getOID_Tipo_Ocorrencia() > 0) {
                sql += " and Tipos_Ocorrencias.OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia();
            }
            if (util.doValida(ed.getOID_Pessoa())) {
                sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (util.doValida(ed.getOID_Pessoa_Destinatario())) {
                sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
            if (util.doValida(ed.getDT_Emissao())) {
                sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao() + "'";
            }
            if (util.doValida(ed.getDT_Ocorrencia_Nota_Fiscal())) {
                sql += " and Ocorrencias_Notas_Fiscais.DT_Ocorrencia_Nota_Fiscal = '" + ed.getDT_Ocorrencia_Nota_Fiscal() + "'";
            }
            if (util.doValida(ed.getDM_Acesso())) {
                sql += " and Tipos_Ocorrencias.DM_Acesso ='" + ed.getDM_Acesso() + "'";
            }

            sql += " Order by Notas_Fiscais.NR_Nota_Fiscal, DT_Ocorrencia_Nota_Fiscal, HR_Ocorrencia_Nota_Fiscal ";

            ResultSet res = this.executasql.executarConsulta(sql);

            FormataDataBean DataFormatada = new FormataDataBean();

            //popula
            while (res.next()) {
                Ocorrencia_Nota_FiscalED edVolta = new Ocorrencia_Nota_FiscalED();

                edVolta.setOID_Ocorrencia_Nota_Fiscal(res.getLong("OID_Ocorrencia_Nota_Fiscal"));

                edVolta.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edVolta.setDM_Tipo_Nota_Fiscal(res.getString("DM_Tipo_Nota_Fiscal"));

                edVolta.setDT_Ocorrencia_Nota_Fiscal(res.getString("DT_Ocorrencia_Nota_Fiscal"));
                DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Nota_Fiscal());
                edVolta.setDT_Ocorrencia_Nota_Fiscal(DataFormatada.getDT_FormataData());

                edVolta.setHR_Ocorrencia_Nota_Fiscal(res.getString("HR_Ocorrencia_Nota_Fiscal"));
                edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));

                edVolta.setDT_Emissao(res.getString("DT_Emissao"));
                DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
                edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

                edVolta.setNM_Tipo_Ocorrencia(res.getString("NM_Tipo_Ocorrencia"));
                edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
                edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                edVolta.setDM_Pendencia(res.getString("DM_Pendencia"));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"lista()");
        }
        return list;
    }

    public Ocorrencia_Nota_FiscalED getByRecord(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        Ocorrencia_Nota_FiscalED edVolta = new Ocorrencia_Nota_FiscalED();

        try {

            sql = " SELECT OID_Ocorrencia_Nota_Fiscal" +
            		"	,OID_Tipo_Ocorrencia" +
            		"	,DT_Ocorrencia_Nota_Fiscal" +
            		"	,DM_Pendencia" +
            		"	,HR_Ocorrencia_Nota_Fiscal" +
            		"	,Ocorrencias_Notas_Fiscais.TX_Observacao" +
            		"	,Ocorrencias_Notas_Fiscais.NM_Pessoa" +
            		"	,Notas_Fiscais.oid_Nota_Fiscal" +
            		"	,Notas_Fiscais.DM_Tipo_Nota_Fiscal" +
            		"	,Notas_Fiscais.oid_Pessoa" +
            		"	,Notas_Fiscais.oid_Pessoa_Destinatario" +
            		"	,Notas_Fiscais.NR_Nota_Fiscal" +
            		"	,Notas_Fiscais.DT_Emissao " +
                    " FROM Ocorrencias_Notas_Fiscais" +
            		"	   ,Notas_Fiscais ";
            sql += " WHERE Ocorrencias_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";

            if (ed.getOID_Ocorrencia_Nota_Fiscal() > 0) {
                sql += " and OID_Ocorrencia_Nota_Fiscal = '" + ed.getOID_Ocorrencia_Nota_Fiscal()+"'";
            }
            FormataDataBean DataFormatada = new FormataDataBean();

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta.setOID_Ocorrencia_Nota_Fiscal(res.getLong("OID_Ocorrencia_Nota_Fiscal"));
                edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
                edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
                edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
                edVolta.setDM_Pendencia(res.getString("DM_Pendencia"));
                edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
                edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));

                edVolta.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edVolta.setDM_Tipo_Nota_Fiscal(res.getString("DM_Tipo_Nota_Fiscal"));

                edVolta.setDT_Ocorrencia_Nota_Fiscal(res.getString("DT_Ocorrencia_Nota_Fiscal"));
                DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Nota_Fiscal());
                edVolta.setDT_Ocorrencia_Nota_Fiscal(DataFormatada.getDT_FormataData());

                edVolta.setHR_Ocorrencia_Nota_Fiscal(res.getString("HR_Ocorrencia_Nota_Fiscal"));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                edVolta.setNM_Pessoa(res.getString("NM_Pessoa"));

                edVolta.setDT_Emissao(res.getString("DT_Emissao"));
                DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
                edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"getByRecord()");
        }
        return edVolta;
    }

    public void geraRelatorio(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        Ocorrencia_Nota_FiscalED edVolta = new Ocorrencia_Nota_FiscalED();

        try {

            sql = "select * from Ocorrencia_Nota_Fiscals where oid_Ocorrencia_Nota_Fiscal > 0";

            ResultSet res = this.executasql.executarConsulta(sql);

            new Ocorrencia_Nota_FiscalRL().geraRelatEstoque(res);
        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"geraRelatorio()");
        }
    }
}
