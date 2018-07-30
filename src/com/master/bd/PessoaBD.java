package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.PessoaED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class PessoaBD {

  private ExecutaSQL executasql;

  public PessoaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ArrayList lista (PessoaED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT * FROM Pessoas " +
          " WHERE nr_cnpj_cpf='" + ed.getNR_CNPJ_CPF () +
          "' AND (dm_situacao IS NULL OR dm_situacao!='E')";

      ResultSet res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        PessoaED edVolta = new PessoaED ();
        edVolta.setOid_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setNR_CNPJ_CPF (res.getString ("NR_Cnpj_Cpf"));
        edVolta.setNM_Razao_Social (res.getString ("NM_razao_social"));
        edVolta.setNM_Endereco (res.getString ("NM_endereco"));
        edVolta.setNR_CEP (res.getString ("NR_Cep"));
        edVolta.setDt_stamp (res.getString ("DT_STAMP"));
        edVolta.setUsuario_Stamp (res.getString ("USUARIO_STAMP"));
        edVolta.setDm_Stamp (res.getString ("DM_STAMP"));
        edVolta.setNM_Inscricao_Estadual (res.getString ("NM_Inscricao_Estadual"));
        edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
        edVolta.setNM_Bairro (res.getString ("NM_Bairro"));
        edVolta.setDm_Situacao (res.getString ("DM_SITUACAO"));
        edVolta.setOid_Cidade (res.getLong ("OID_Cidade"));
        edVolta.setEMail (res.getString ("EMail"));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Pessoas - SQL=" + sql);
      excecoes.setMetodo ("lista(PessoasED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public PessoaED getByRecord (PessoaED ed) throws Excecoes {

    String sql = null;
    PessoaED edVolta = new PessoaED ();

    try {

      sql = " SELECT * FROM Pessoas, Cidades, Regioes_Estados, Estados " +
          " WHERE  Pessoas.oid_Cidade = Cidades.oid_Cidade " +
          " AND    Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
          " AND    Regioes_Estados.OID_Estado = Estados.OID_Estado ";
      if (ed.getOid_Empresa () > 0) {
        sql += " AND oid_Empresa=" + ed.getOid_Empresa () + " ";
      }
      if (JavaUtil.doValida (ed.getOid_Pessoa ())) {
        sql += " AND    OID_Pessoa='" + ed.getOid_Pessoa () + "'";
      }
      if (JavaUtil.doValida (ed.getNR_CNPJ_CPF ())) {
        sql += " AND    NR_CNPJ_CPF='" + ed.getNR_CNPJ_CPF () + "'";
      }

//     System.out.println (sql);
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta = new PessoaED ();
        edVolta.setOid_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setNR_CNPJ_CPF (res.getString ("NR_Cnpj_Cpf"));
        edVolta.setNM_Razao_Social (res.getString ("NM_razao_social"));
        edVolta.setNM_Endereco (res.getString ("NM_endereco"));
        edVolta.setNR_CEP (res.getString ("NR_Cep"));
        edVolta.setNR_Telefone (res.getString ("NR_Telefone"));
        edVolta.setDt_stamp (res.getString ("DT_STAMP"));
        edVolta.setUsuario_Stamp (res.getString ("USUARIO_STAMP"));
        edVolta.setDm_Stamp (res.getString ("DM_STAMP"));
        edVolta.setNM_Inscricao_Estadual (JavaUtil.getValueDef (res.getString ("NM_Inscricao_Estadual") , "ISENTO"));
        edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
        edVolta.setNM_Bairro (res.getString ("NM_Bairro"));
        edVolta.setDm_Situacao (res.getString ("DM_SITUACAO"));
        edVolta.setOid_Cidade (res.getLong ("OID_Cidade"));
        edVolta.setCD_Cidade (res.getString ("CD_Cidade"));
        edVolta.setNM_Cidade (res.getString ("NM_Cidade"));
        edVolta.setCD_Estado (res.getString ("CD_Estado"));
        edVolta.setEMail (res.getString ("EMail"));

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord(PessoaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public PessoaED getByRecord (String oid_Pessoa) throws Excecoes {

    PessoaED edVolta = new PessoaED ();

    edVolta.setOid_Pessoa (oid_Pessoa);

    return this.getByRecord (edVolta);
  }
}