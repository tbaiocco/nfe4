package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.PessoaED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Validacao;
import com.master.util.bd.ExecutaSQL;

public class PessoaBD {

  private ExecutaSQL executasql;

  public PessoaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public boolean inclui (PessoaED ed) throws Excecoes {

    String sql = null;
    boolean incluiu = false;

    try {
      sql = "INSERT INTO Pessoas (";
      sql += " OID_Pessoa,";
      sql += " NR_Cnpj_Cpf,";
      sql += " NM_razao_social ,";
      sql += " NM_endereco,";
      sql += " NR_Cep,";
      sql += " NM_Inscricao_Estadual,";
      sql += " NM_Fantasia,";
      sql += " DT_STAMP,";
      sql += " USUARIO_STAMP,";
      sql += " DM_STAMP,";
      sql += " NM_Bairro,";
      sql += " NR_Telefone,";
      sql += " OID_Cidade,";
      sql += " EMail,";
      sql += " DM_Valida_Sefaz)";
      sql += " VALUES ('";
      // System.out.println ("PESSOABD 1 ");
      sql += JavaUtil.getValue (ed.getNR_CNPJ_CPF ()) + "', '";
      // System.out.println ("PESSOABD 2 ");
      sql += JavaUtil.getValue (ed.getNR_CNPJ_CPF ()) + "','";
      // System.out.println ("PESSOABD 3 ");
      sql += JavaUtil.getValue (ed.getNM_Razao_Social ()) + "', '";
      // System.out.println ("PESSOABD 4 ");
      sql += JavaUtil.getValue (ed.getNM_Endereco ()) + "', '";
      // System.out.println ("PESSOABD 5 ");
      sql += JavaUtil.getValue (ed.getNR_CEP ()) + "', '";
      // System.out.println ("PESSOABD 6 ");
      sql += JavaUtil.getValue (ed.getNM_Inscricao_Estadual ()) + "', '";
      // System.out.println ("PESSOABD 7 ");
      sql += JavaUtil.getValue (ed.getNM_Fantasia ()) + "', '";
      // System.out.println ("PESSOABD 8 ");
      sql += Data.getDataDMY () + "', '";
      // System.out.println ("PESSOABD 9 ");
      sql += JavaUtil.getValue (ed.getUsuario_Stamp ()) + "', '";
      // System.out.println ("PESSOABD 10 ");
      sql += JavaUtil.getValue (ed.getDm_Stamp ()) + "', '";
      // System.out.println ("PESSOABD 11 ");
      sql += JavaUtil.getValue (ed.getNM_Bairro ()) + "', '";
      // System.out.println ("PESSOABD 12 ");
      sql += JavaUtil.getValue (ed.getNR_Telefone ()) + "', ";
      // System.out.println ("PESSOABD 13");
      sql += ed.getOid_Cidade () + ",";
      // System.out.println ("PESSOABD 14 ");
      sql += "'" + JavaUtil.getValue (ed.getEMail ()) + "',";
      // System.out.println ("PESSOABD 15 ");
      sql += JavaUtil.getSQLString ( (JavaUtil.getValue (ed.getNR_CNPJ_CPF ().trim ()).length () == 14 && Validacao.CNPJ (ed.getNR_CNPJ_CPF ().trim ()) ? "S" : "N")) + ")";

//System.out.println ("ins->> " + sql);

      executasql.executarUpdate (sql);
      incluiu = true;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      incluiu = false;
      throw excecoes;
    }
    return incluiu;
  }

  public boolean altera (PessoaED ed) throws Excecoes {

    String sql = null;
    boolean alterou = false;

    try {
      sql = " UPDATE Pessoas SET ";
      sql += " NM_razao_social = '" + ed.getNM_Razao_Social ().trim () + "', ";
      sql += " NM_endereco = '" + ed.getNM_Endereco ().trim () + "', ";
      sql += " NR_Cep = '" + ed.getNR_CEP ().trim () + "', ";
      sql += " DT_STAMP = '" + ed.getDt_stamp ().trim () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp ().trim () + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp ().trim () + "', ";
      sql += " NM_Inscricao_Estadual = '" + ed.getNM_Inscricao_Estadual ().trim () + "', ";
      sql += " NM_Fantasia = '" + ed.getNM_Fantasia ().trim () + "', ";
      sql += " NM_Bairro = '" + ed.getNM_Bairro ().trim () + "', ";
      sql += " NR_Telefone = '" + ed.getNR_Telefone ().trim () + "', ";
      sql += " OID_Cidade = " + ed.getOid_Cidade () + ",";
      sql += " EMail = '" + ed.getEMail () + "',";
      sql += " DM_Valida_Sefaz = " + JavaUtil.getSQLString ( (JavaUtil.getValue (ed.getOid_Pessoa ().trim ()).length () == 14 && Validacao.CNPJ (ed.getOid_Pessoa ().trim ()) ? "S" : "N"));
      sql += " WHERE OID_Pessoa='" + ed.getOid_Pessoa () + "'";

      //System.out.println ("sql ----->>" + sql);

      executasql.executarUpdate (sql);
      alterou = true;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("altera(PessoaED)");
      excecoes.setExc (exc);
      alterou = false;
      throw excecoes;
    }
    return alterou;
  }

  public void deleta (PessoaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Pessoas SET ";
      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "', ";
      sql += " DM_SITUACAO ='" + ed.getDm_Situacao () + "',";
      sql += " WHERE  nr_cnpj_cpf='" + ed.getNR_CNPJ_CPF () + "' AND dm_situacao='E'";

      executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar Pessoa");
      excecoes.setMetodo ("deleta(PessoaED)");
      excecoes.setExc (exc);
      throw excecoes;
    }
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