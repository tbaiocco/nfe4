package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.ColetaRCEED;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class ColetaRCEBD {

  private ExecutaSQL executasql;
  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();

  public ColetaRCEBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void altera (ColetaRCEED ed) throws Excecoes {

  }

  public void deleta (ColetaRCEED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from coletas_rce WHERE oid_coleta_rce = ";
      sql += "'" + ed.getOID_ColetaRCE () + "'";

      int i = executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      throw new Excecoes ("Erro ao excluir." , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public ArrayList lista (ColetaRCEED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT Coletas_RCE.*, Coletas.oid_unidade, Coletas.oid_pessoa, Coletas.oid_pessoa_destinatario, Coletas.nr_coleta," +
          " Manifestos.nr_manifesto, Coletas.dt_emissao " +
          " from Coletas_RCE, Manifestos, Coletas ";
      sql += " WHERE Coletas_RCE.OID_Manifesto = Manifestos.OID_Manifesto " +
          " AND Coletas_RCE.OID_Coleta = Coletas.OID_Coleta ";

      if (JavaUtil.doValida (ed.getOID_Manifesto ())) {
        sql += " and Coletas_RCE.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
      }
      if (JavaUtil.doValida (ed.getOID_ColetaRCE ())) {
        sql += " and Coletas_RCE.OID_coleta_rce = '" + ed.getOID_ColetaRCE () + "'";
      }

      sql += " Order by Coletas.oid_unidade, Coletas.nr_coleta, Coletas_RCE.dt_coleta_rce, Coletas_RCE.hr_coleta_rce ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      // System.out.println (sql);
      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        ColetaRCEED edVolta = new ColetaRCEED ();

        edVolta.setOID_ColetaRCE (res.getString ("OID_Coleta_rce"));
        edVolta.setOID_Coleta (res.getString ("OID_Coleta"));
        edVolta.setNR_Coleta (res.getString ("NR_Coleta"));
        edVolta.setOID_Manifesto (res.getString ("OID_Manifesto"));
        edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));
        edVolta.setOID_Unidade (res.getLong ("oid_unidade"));
        UnidadeBean unidade = UnidadeBean.getByOID_Unidade (res.getInt ("oid_unidade"));
        edVolta.setCD_Unidade (unidade.getCD_Unidade ());
        edVolta.setNM_Fantasia (unidade.getNM_Fantasia ());
        edVolta.setOID_Pessoa (res.getString ("oid_Pessoa"));
        PessoaBean pessoa = PessoaBean.getByOID (res.getString ("oid_pessoa"));
        edVolta.setNR_CNPJ_CPF (pessoa.getNR_CNPJ_CPF ());
        edVolta.setNM_Pessoa_Remetente (pessoa.getNM_Razao_Social ());
        edVolta.setOID_Pessoa_Destinatario (res.getString ("oid_pessoa_destinatario"));
        pessoa = PessoaBean.getByOID (res.getString ("oid_pessoa_destinatario"));
        edVolta.setNR_CNPJ_CPF_Destinatario (pessoa.getNR_CNPJ_CPF ());
        edVolta.setNM_Pessoa_Destinatario (pessoa.getNM_Razao_Social ());
        edVolta.setVL_Entrega (res.getDouble ("vl_previsto"));
        edVolta.setDT_Coleta_RCE (DataFormatada.getDT_FormataData (res.getString ("dt_coleta_rce")));
        edVolta.setHR_Coleta_RCE (res.getString ("hr_coleta_rce"));
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData (res.getString ("dt_emissao")));
        edVolta.setDM_Tipo ("C");
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      throw new Excecoes ("Erro ao selecionar." , exc , this.getClass ().getName () , "lista()");
    }

    return list;
  }

  public ColetaRCEED getByRecord (ColetaRCEED ed) throws Excecoes {

    Iterator iterator = this.lista (ed).iterator ();
    if (iterator.hasNext ()) {
      return (ColetaRCEED) iterator.next ();
    }
    else {
      return new ColetaRCEED ();
    }
  }

  public void inclui (ColetaRCEED ed) throws Excecoes {

    String sql = null;
    long valOid = 0 , nr_manifesto = 0;
    String chave = null;

    try {

      chave = (ed.getOID_Manifesto () + ed.getOID_Coleta ());
      //chave = String.valueOf (System.currentTimeMillis ()).toString ();

      sql = " insert into Coletas_rce (" +
          " OID_Coleta_RCE, OID_Coleta, OID_Manifesto, DT_Coleta_RCE, HR_Coleta_RCE, usuario_stamp, dt_stamp, dm_stamp, vl_previsto, DM_Tipo " +
          " ) values ";
      sql += "('" + chave + "'" +
          ",'" + ed.getOID_Coleta () + "'" +
          ",'" + ed.getOID_Manifesto () + "'" +
          ",'" + ed.getDT_Coleta_RCE () + "'" +
          ",'" + ed.getHR_Coleta_RCE () + "'" +
          ",'" + ed.getUsuario_Stamp () + "'" +
          ",'" + Data.getDataDMY () + "'" +
          ",'S'" +
          "," + ed.getVL_Entrega () +
          ",'C')";

      int i = executasql.executarUpdate (sql);

    }
    catch (SQLException e) {
      // System.out.println (sql);
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ColetaRCEED ed)");
    }
    catch (Exception e) {
      // System.out.println (sql);
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ColetaRCEED ed)");
    }
  }

}
