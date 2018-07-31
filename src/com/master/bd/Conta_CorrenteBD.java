package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Conta_CorrenteED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class Conta_CorrenteBD  {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public Conta_CorrenteBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Conta_CorrenteED inclui(Conta_CorrenteED ed) throws Excecoes {

        String sql = null;
        try {

            if (util.doExiste("Contas_Correntes","oid_conta_corrente = '"+ed.getCd_Conta_Corrente()+"'"))
                throw new Excecoes("Já Existe uma Conta Corrente com esse Código!");

            sql = " INSERT INTO Contas_Correntes (" + 
                  "     OID_CONTA_CORRENTE" +
                  "     ,NR_CONTA_CORRENTE" +
                  "     ,DT_STAMP" +
                  "     ,USUARIO_STAMP" +
                  "     ,DM_CONTROLE_SALDO" +
                  "     ,DM_CONTROLE_COBRANCA" +
                  "     ,DM_Grupo" +
                  "     ,DM_Contabilizacao" +
                  "     ,DM_STAMP" +
                  "     ,VL_SALDO_INICIAL" +
                  "     ,DM_TIPO_CONTA_CORRENTE" +
                  "     ,CD_CONTA_CORRENTE,NR_AGENCIA" +
                  "     ,OID_CONTA" +
                  "     ,OID_EMPRESA" +
                  "     ,OID_MOEDA" +
                  "     ,OID_PESSOA" +
                  "     ,oid_unidade) " +
                  " VALUES (" + 
                  "'" + ed.getCd_Conta_Corrente() + "'" +
                  ",'" + ed.getNr_Conta_Corrente() + "'" +
                  ",'" + ed.getDt_stamp() + "'" +
                  ",'" + ed.getUsuario_Stamp() + "'" +
                  ",'" + ed.getDm_Controle_Saldo() + "'" +
                  ",'" + ed.getDm_Controle_Cobranca() + "'" +
                  ",'" + ed.getDm_Grupo() + "'" +
                  ",'" + ed.getDm_Contabilizacao() + "'" +
                  ",'" + ed.getDm_Stamp() + "',";
                sql += ed.getVl_Saldo_Inicial() == null ? "null," : ed.getVl_Saldo_Inicial() + ",'" + ed.getDm_Tipo_Conta_Corrente() + "','" + ed.getCd_Conta_Corrente() + "','" + ed.getNr_Agencia()
                        + "'," + ed.getOid_Conta() + "," + ed.getOid_Empresa() + "," + ed.getOid_Moeda() + ",'" + ed.getOid_Pessoa() + "'";

                if (ed.getOid_Unidade() > 0)
                    sql += "," + ed.getOid_Unidade();
                else sql += ",null";
                sql += ")";

                executasql.executarUpdate(sql);
                ed.setOid_Conta_Corrente(ed.getCd_Conta_Corrente());
                return ed;
                
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Conta_CorrenteED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Contas_Correntes SET ";
            sql +="     NR_CONTA_CORRENTE = '" + ed.getNr_Conta_Corrente() + "',";
            sql +="     DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql +="     USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql +="     DM_Controle_Saldo = '" + ed.getDm_Controle_Saldo() + "', ";
            sql +="     DM_Controle_Cobranca = '" + ed.getDm_Controle_Cobranca() + "', ";
            sql +="     DM_Grupo = '" + ed.getDm_Grupo() + "', ";
            sql +="     DM_Contabilizacao = '" + ed.getDm_Contabilizacao() + "', ";
            sql +="     DM_STAMP = '" + ed.getDm_Stamp() + "', ";
            sql +="     CD_CONTA_CORRENTE = '" + ed.getCd_Conta_Corrente() + "',";
            sql +="     NR_AGENCIA = '" + ed.getNr_Agencia() + "',";
            sql +="     OID_CONTA = " + ed.getOid_Conta() + ",";
            sql +="     OID_MOEDA = " + ed.getOid_Moeda() + ",";
            sql +="     OID_EMPRESA = " + ed.getOid_Empresa() + ",";
            sql +="     OID_PESSOA = '" + ed.getOid_Pessoa() + "'";
            sql +=" WHERE oid_conta_corrente = '" + ed.getOid_Conta_Corrente() + "'";

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "alterar()");
        }
    }

    public void deleta(Conta_CorrenteED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " DELETE FROM Contas_Correntes WHERE oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'";

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(Conta_CorrenteED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {
            
            sql = " SELECT * FROM Contas_Correntes concor, pessoas pes, contas con, moedas moe " +
                  " WHERE concor.oid_conta = con.oid_conta " +
                  "   AND concor.oid_moeda = moe.oid_moeda " +
                  "   AND concor.oid_pessoa = pes.oid_pessoa ";
            if (util.doValida(ed.getCd_Conta_Corrente()))
                sql += " and concor.cd_conta_corrente = '" + ed.getCd_Conta_Corrente() + "'";
            else {
                if (util.doValida(ed.getNr_Conta_Corrente()))
                    sql += " and concor.nr_conta_corrente = '" + ed.getNr_Conta_Corrente() + "'";
                if (util.doValida(ed.getOid_Pessoa()))
                    sql += " and concor.oid_pessoa = " + ed.getOid_Pessoa();
                if (util.doValida(ed.getNm_Razao_Social()))
                    sql += " and pes.NM_Razao_Social  LIKE '%" + ed.getNm_Razao_Social() + "%'";
            }

            if (util.doValida(ed.getDm_Tipo_Conta_Corrente())){
                sql += " and concor.Dm_Tipo_Conta_Corrente = '" + ed.getDm_Tipo_Conta_Corrente() + "'";
            }
              
            
            sql +=" ORDER BY concor.DM_Tipo_Conta_Corrente, concor.cd_Conta_corrente ";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Conta_CorrenteED edVolta = new Conta_CorrenteED();

                edVolta.setOid_Conta_Corrente(res.getString("oid_conta_corrente"));
                edVolta.setCd_Conta_Corrente(res.getString("cd_conta_corrente"));
                edVolta.setNr_Conta_Corrente(res.getString("nr_Conta_corrente"));
                edVolta.setNr_Agencia(res.getString("nr_agencia"));
                edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
                edVolta.setOid_Unidade(res.getInt("oid_unidade"));
                edVolta.setDm_Tipo_Conta_Corrente((res.getString("Dm_Tipo_Conta_Corrente")));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public Conta_CorrenteED getByRecord(Conta_CorrenteED ed) throws Excecoes {
        
        Conta_CorrenteED edVolta = new Conta_CorrenteED();
        try {
            
            String sql = " SELECT concor.* " + 
                "       ,con.oid_conta " + 
                "       ,con.cd_conta " + 
                "       ,con.nm_conta " +
                "       ,Empresas.oid_Empresa " + 
                "       ,Empresas.cd_Empresa " + 
                "       ,Empresas.nm_Empresa " + 
                "       ,pes.oid_pessoa " + 
                "       ,pes.nr_cnpj_cpf " + 
                "       ,pes.nm_razao_social " +
                "       ,moe.oid_moeda " + 
                "       ,moe.cd_moeda " + 
                "       ,moe.nm_moeda " +
                " FROM Contas_Correntes concor" +
                "      ,Pessoas pes " +
                "      ,Contas con" +
                "      ,Moedas moe" +
                "      ,Empresas " + 
                " WHERE concor.oid_conta = con.oid_conta " + 
                "   AND concor.oid_moeda = moe.oid_moeda " + 
                "   AND concor.oid_empresa = empresas.oid_empresa " + 
                "   AND concor.oid_pessoa = pes.oid_pessoa ";

            if (util.doValida(ed.getOid_Conta_Corrente()))
                sql += " and concor.oid_conta_corrente = '" + ed.getOid_Conta_Corrente() + "'";
            if (util.doValida(ed.getCd_Conta_Corrente()))
                sql += " and concor.cd_conta_Corrente = '" + ed.getCd_Conta_Corrente() + "'";
            if (util.doValida(ed.getOid_Pessoa()))
                sql += " and pes.OID_Pessoa = '" + ed.getOid_Pessoa() + "'";



            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new Conta_CorrenteED();
                edVolta.setOid_Conta_Corrente(res.getString("oid_conta_corrente"));
                edVolta.setCd_Conta_Corrente(res.getString("cd_conta_corrente"));
                edVolta.setNr_Conta_Corrente(res.getString("nr_Conta_corrente"));

                edVolta.setNr_Agencia(res.getString("nr_agencia"));
                edVolta.setNr_Conta_Corrente(res.getString("nr_conta_corrente"));
                edVolta.setDm_Tipo_Conta_Corrente(res.getString("dm_tipo_conta_corrente"));
                edVolta.setDm_Controle_Saldo(res.getString("dm_Controle_Saldo"));
                edVolta.setDm_Controle_Cobranca(res.getString("dm_Controle_Cobranca"));
                edVolta.setDm_Grupo(res.getString("dm_Grupo"));
                edVolta.setDm_Contabilizacao(res.getString("dm_Contabilizacao"));
                // dados de conta
                edVolta.setCd_Conta(res.getString("cd_conta"));
                edVolta.setNm_Conta(res.getString("nm_conta"));
                edVolta.setOid_Conta(new Integer(res.getString("oid_conta")));
                edVolta.setNm_Empresa(res.getString("nm_Empresa"));
                edVolta.setCd_Empresa(res.getString("cd_Empresa"));
                edVolta.setOid_Empresa(new Integer(res.getString("oid_Empresa")));
                // dados do correntista
                edVolta.setOid_Pessoa(res.getString("oid_pessoa"));
                edVolta.setNr_CNPJ_CPF(res.getString("nr_cnpj_cpf"));
                edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
                // dados da moeda
                edVolta.setOid_Moeda(new Integer(res.getString("oid_moeda")));
                edVolta.setCd_Moeda(res.getString("cd_moeda"));
                edVolta.setNm_Moeda(res.getString("nm_moeda"));
                edVolta.setOid_Unidade(res.getInt("oid_unidade"));

                if (util.doValida(edVolta.getOid_Pessoa())) 
                    edVolta.setCd_Banco(util.getTableStringValue("CD_Banco", "Bancos", "oid_Pessoa = '"+edVolta.getOid_Pessoa()+"'"));


                sql =" SELECT oid_Tipo_Documento  " + 
                     " FROM   Documentos_Contas_Correntes " +
                     " WHERE  DM_Padrao='S' " +
                     " AND    oid_conta_corrente = '" + res.getString("oid_conta_corrente") + "'"; 
                 ResultSet res2 = this.executasql.executarConsulta (sql);
                 if (res2.next ()){
                   edVolta.setOid_Documento_Padrao(res2.getLong("oid_Tipo_Documento"));
                 }


            }
            return edVolta;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord()");
        }
    }
}
