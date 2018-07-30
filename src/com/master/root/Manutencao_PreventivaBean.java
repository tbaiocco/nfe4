package com.master.root;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import auth.*;
import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;

public class Manutencao_PreventivaBean {
  private String DT_Servico;
  private String NM_Complemento;
  private double VL_Servico;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Tipo_Servico;
  private int oid_Movimento_Ordem_Servico;
  private String NM_Tipo_Servico;
  private String oid_Veiculo;
  private long NR_Kilometragem_Prevista;
  private long NR_Kilometragem_Servico;
  private String DM_Situacao;
  private long OID_Movimento_Ordem_Servico;
  private String DT_Ultimo_Servico;
  private String CD_Tipo_Servico;
  private long NR_Kilometragem_Atual;
  private long NR_Kilometragem_Realizada;
  private String DM_Procedencia;
  private long OID_Servico_Preventivo;

  public Manutencao_PreventivaBean () {
    NM_Complemento = " ";

  }

  public String getDT_Servico () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Servico);
    DT_Servico = DataFormatada.getDT_FormataData ();

    return DT_Servico;
  }

  public void setDT_Servico (String DT_Servico) {
    this.DT_Servico = DT_Servico;
  }

  public String getNM_Complemento () {
    return NM_Complemento;
  }

  public void setNM_Complemento (String NM_Complemento) {
    this.NM_Complemento = NM_Complemento;
  }

  public String getOID_Veiculo () {
    return oid_Veiculo;
  }

  public void setOID_Veiculo (String oid_Veiculo) {
    this.oid_Veiculo = oid_Veiculo;
  }

  public double getVL_Servico () {
    return VL_Servico;
  }

  public void setVL_Servico (double VL_Servico) {
    this.VL_Servico = VL_Servico;
  }

  /*
   *---------------- Bloco Padrão para Todas Classes ------------------
   */
  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public void setDt_Stamp (String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public void setDm_Stamp (String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }

  public int getOID () {
    return oid;
  }

  public void setOID (int n) {
    this.oid = n;
  }

  public int getOID_Tipo_Servico () {
    return oid_Tipo_Servico;
  }

  public void setOID_Tipo_Servico (int n) {
    this.oid_Tipo_Servico = n;
  }

  public int getoid_Movimento_Ordem_Servico () {
    return oid_Movimento_Ordem_Servico;
  }

  public long getOID_Servico_Preventivo () {
    return OID_Servico_Preventivo;
  }

  public String getDM_Procedencia () {
    return DM_Procedencia;
  }

  public void setoid_Movimento_Ordem_Servico (int n) {
    this.oid_Movimento_Ordem_Servico = n;
  }

  public void setOID_Servico_Preventivo (long OID_Servico_Preventivo) {
    this.OID_Servico_Preventivo = OID_Servico_Preventivo;
  }

  public void setDM_Procedencia (String DM_Procedencia) {
    this.DM_Procedencia = DM_Procedencia;
  }

  public long getNR_Kilometragem_Prevista () {
    return NR_Kilometragem_Prevista;
  }

  public void setNR_Kilometragem_Prevista (long NR_Kilometragem_Prevista) {
    this.NR_Kilometragem_Prevista = NR_Kilometragem_Prevista;
  }

  public String getNM_Tipo_Servico () {
    return NM_Tipo_Servico;
  }

  public void setNM_Tipo_Servico (String NM_Tipo_Servico) {
    this.NM_Tipo_Servico = NM_Tipo_Servico;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Usuario do DSN
      // o NM_Usuario de usuário e a URL do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Gera um novo código (OID)
     */
    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (
          "SELECT MAX(OID_Manutencao_Preventiva) FROM Manutencoes_Preventivas");

      while (cursor.next ()) {
        int oid = cursor.getInt (1);
        setOID (oid + 1);
      }
      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    // System.out.println ("insert" + getOID_Tipo_Servico ());

    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Manutencoes_Preventivas (OID_Manutencao_Preventiva, OID_Veiculo, oid_Movimento_Ordem_Servico, OID_Tipo_Servico, NR_Kilometragem_Prevista, DT_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getOID_Veiculo ());
      pstmt.setInt (3 , getoid_Movimento_Ordem_Servico ());
      pstmt.setInt (4 , getOID_Tipo_Servico ());
      pstmt.setLong (5 , getNR_Kilometragem_Prevista ());
      pstmt.setString (6 , Data.getDataDMY ());
      pstmt.setString (7 , getUsuario_Stamp ());
      pstmt.setString (8 , getDm_Stamp ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Complemento do DSN
      // o NM_Complemento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o update.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Manutencoes_Preventivas SET OID_Tipo_Servico=?, NR_Kilometragem_Prevista=? ");
    buff.append ("WHERE OID_Manutencao_Preventiva=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Tipo_Servico ());
      pstmt.setLong (2 , getNR_Kilometragem_Prevista ());
      pstmt.setInt (3 , getOID ());

      // System.out.println (getNR_Kilometragem_Prevista ());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void delete () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Complemento do DSN
      // o NM_Complemento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o DELETE.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("DELETE FROM Manutencoes_Preventivas ");
    buff.append ("WHERE OID_Manutencao_Preventiva=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final List getByNR_Placa (String NR_Placa) throws Exception {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o nome do DSN
      // o nome de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Manutencao_Preventivas_Lista = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {

      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Manutencoes_Preventivas.OID_Manutencao_Preventiva, ");
      buff.append ("	Manutencoes_Preventivas.oid_Movimento_Ordem_Servico, ");
      buff.append ("	Manutencoes_Preventivas.OID_Veiculo, ");
      buff.append ("	Manutencoes_Preventivas.OID_Tipo_Servico, ");
      buff.append ("	Manutencoes_Preventivas.NR_Kilometragem_Prevista,  ");
      buff.append ("	Tipos_Servicos.NM_Tipo_Servico,  ");
      buff.append ("	Veiculos.NR_Kilometragem_Atual,  ");
      buff.append ("	Manutencoes_Preventivas.DT_Stamp,  ");
      buff.append ("	Manutencoes_Preventivas.NR_Kilometragem_Realizada,  ");
      buff.append ("	Manutencoes_Preventivas.oid_servico_preventivo, ");
      buff.append ("	Manutencoes_Preventivas.DT_Servico  ");
      buff.append (" FROM Manutencoes_Preventivas, Tipos_Servicos, Veiculos ");
      buff.append (" WHERE Manutencoes_Preventivas.OID_Veiculo = Veiculos.OID_Veiculo ");
      buff.append ("  AND  Manutencoes_Preventivas.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico ");
      buff.append ("  AND  Manutencoes_Preventivas.OID_Veiculo = '");
      buff.append (NR_Placa);
      buff.append ("'");
      buff.append ("  ORDER BY  Manutencoes_Preventivas.NR_Kilometragem_Prevista ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Manutencao_PreventivaBean p = new Manutencao_PreventivaBean ();
        p.setOID (cursor.getInt (1));
        p.setoid_Movimento_Ordem_Servico (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setOID_Tipo_Servico (cursor.getInt (4));
        p.setNR_Kilometragem_Prevista (cursor.getLong (5));
        p.setNM_Tipo_Servico (cursor.getString (6));
        p.setNR_Kilometragem_Atual (cursor.getLong (7));

        DataFormatada.setDT_FormataData (cursor.getString (8));
        p.setDt_Stamp (DataFormatada.getDT_FormataData ());

        p.setNR_Kilometragem_Realizada (cursor.getLong (9));

        p.setOID_Servico_Preventivo (cursor.getInt (10));

        DataFormatada.setDT_FormataData (cursor.getString (11));
        p.setDT_Servico (DataFormatada.getDT_FormataData ());

        p.setDM_Situacao ("");
        p.setDT_Ultimo_Servico ("");
        if (p.getNR_Kilometragem_Prevista () < p.getNR_Kilometragem_Atual ()) {
          p.setDM_Situacao ("Pendente");

        }

        Manutencao_Preventivas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Manutencao_Preventivas_Lista;
  }

  public static final List atualiza (String NR_Placa) throws Exception {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o nome do DSN
      // o nome de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Manutencao_Preventivas_Lista = new ArrayList ();

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Servicos_Preventivos.oid_Tipo_Servico, ");
      buff.append ("	Servicos_Preventivos.NR_Kilometragem_Servico, ");
      buff.append ("	Veiculos.NR_Kilometragem_Atual ");
      buff.append (" FROM Servicos_Preventivos, Veiculos, Complementos_Veiculos ");
      buff.append (" WHERE Servicos_Preventivos.oid_Modelo_Veiculo = Veiculos.oid_Modelo_Veiculo ");
      buff.append ("  AND  Complementos_Veiculos.DM_PROCEDENCIA = 'P' ");
      buff.append ("  AND  Veiculos.OID_Veiculo = '");
      buff.append (NR_Placa);
      buff.append ("'");
      buff.append ("  ORDER BY  Servicos_Preventivos.NR_Kilometragem_Servico ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      ResultSet cursor2 = null;
      String sql = null;
      while (cursor.next ()) {
        Manutencao_PreventivaBean p = new Manutencao_PreventivaBean ();
        p.setOID_Tipo_Servico (cursor.getInt (1));
        p.setNR_Kilometragem_Servico (cursor.getLong (2));
        p.setNR_Kilometragem_Atual (cursor.getLong (3));

        sql = " SELECT oid_Manutencao_Preventiva " +
            " FROM Manutencoes_Preventivas " +
            " WHERE Manutencoes_Preventivas.oid_Veiculo ='" + NR_Placa + "'" +
            " AND Manutencoes_Preventivas.oid_Tipo_Servico =" + p.getOID_Tipo_Servico ();

        Statement stmt2 = conn.createStatement ();
        // System.out.println ("sql" + sql);

        int i = 0;
        cursor2 = stmt2.executeQuery (sql);
        while (cursor2.next ()) {
          i++;
        }
        if (i == 0) {
          // System.out.println ("tem que inc serv->>" + p.getOID_Tipo_Servico ());
          p.setOID_Veiculo (NR_Placa);
          p.setNR_Kilometragem_Prevista (p.getNR_Kilometragem_Atual () + p.getNR_Kilometragem_Servico ());
          p.insert ();
        }
        Manutencao_Preventivas_Lista.add (p);

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Manutencao_Preventivas_Lista;
  }

  public static final Manutencao_PreventivaBean getByOID (String oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Complemento do DSN
      // o NM_Complemento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Manutencao_PreventivaBean p = new Manutencao_PreventivaBean ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Manutencoes_Preventivas.OID_Manutencao_Preventiva, ");
      buff.append ("	Manutencoes_Preventivas.oid_Movimento_Ordem_Servico, ");
      buff.append ("	Manutencoes_Preventivas.OID_Veiculo, ");
      buff.append ("	Manutencoes_Preventivas.OID_Tipo_Servico, ");
      buff.append ("	Manutencoes_Preventivas.NR_Kilometragem_Prevista,  ");
      buff.append ("	Tipos_Servicos.CD_Tipo_Servico,  ");
      buff.append ("	Tipos_Servicos.NM_Tipo_Servico,  ");
      buff.append ("	Manutencoes_Preventivas.DT_Stamp  ");
      buff.append (" FROM Manutencoes_Preventivas, Tipos_Servicos ");
      buff.append (" WHERE Manutencoes_Preventivas.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico ");
      buff.append (" AND OID_Manutencao_Preventiva= '");
      buff.append (oid);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setoid_Movimento_Ordem_Servico (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setOID_Tipo_Servico (cursor.getInt (4));
        p.setNR_Kilometragem_Prevista (cursor.getLong (5));

        p.setCD_Tipo_Servico (cursor.getString (6));
        p.setNM_Tipo_Servico (cursor.getString (7));

        DataFormatada.setDT_FormataData (cursor.getString (8));
        p.setDt_Stamp (DataFormatada.getDT_FormataData ());

        p.setDM_Situacao ("Pendente");
        p.setDT_Ultimo_Servico ("");
        if (p.getOID_Movimento_Ordem_Servico () > 0) {
          p.setDM_Situacao ("OK");

        }

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }

  public static final List getAll () throws Exception {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o nome do DSN
      // o nome de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Manutencao_Preventivas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Manutencoes_Preventivas.OID_Manutencao_Preventiva, ");
      buff.append ("	Manutencoes_Preventivas.oid_Movimento_Ordem_Servico, ");
      buff.append ("	Manutencoes_Preventivas.OID_Veiculo, ");
      buff.append ("	Manutencoes_Preventivas.OID_Tipo_Servico, ");
      buff.append ("	Manutencoes_Preventivas.DT_Servico, ");
      buff.append ("	Manutencoes_Preventivas.NM_Complemento, ");
      buff.append ("	Manutencoes_Preventivas.VL_Servico,  ");
      buff.append ("	Paises.NR_Kilometragem_Prevista,  ");
      buff.append ("	Tipos_Servicos.NM_Tipo_Servico  ");
      buff.append (" FROM Manutencoes_Preventivas, ");
      buff.append ("      Paises, ");
      buff.append ("      Tipos_Servicos ");
      buff.append (" WHERE Manutencoes_Preventivas.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico ");
      buff.append (" AND    Manutencoes_Preventivas.oid_Movimento_Ordem_Servico 		     = Paises.oid_Movimento_Ordem_Servico ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Manutencao_PreventivaBean p = new Manutencao_PreventivaBean ();
        p.setOID (cursor.getInt (1));
        p.setoid_Movimento_Ordem_Servico (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setOID_Tipo_Servico (cursor.getInt (4));
        p.setDT_Servico (cursor.getString (5));
        p.setNM_Complemento (cursor.getString (6));
        p.setVL_Servico (cursor.getDouble (7));
        p.setNR_Kilometragem_Prevista (cursor.getLong (8));
        p.setNM_Tipo_Servico (cursor.getString (9));
        Manutencao_Preventivas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Manutencao_Preventivas_Lista;
  }

  //*** Relatório de Manutenções Preventivas por veículo
   public void relManutencaoPreventivaVeiculo (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
     String Relatorio = request.getParameter ("Relatorio");
     String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
     String oid_Veiculo = request.getParameter ("oid_Veiculo");
     String DM_Situacao = request.getParameter ("FT_DM_Situacao");
     String DM_Procedencia = request.getParameter ("FT_DM_Procedencia");

     if (!JavaUtil.doValida (Relatorio)) {
       throw new Mensagens ("Nome do Relatório não informado!");
     }


     Manutencao_PreventivaED ed = new Manutencao_PreventivaED (response , Relatorio);
     if (JavaUtil.doValida (oid_Tipo_Servico)) {
       ed.setOid_tipo_servico (Integer.parseInt (oid_Tipo_Servico));
     }
     if (JavaUtil.doValida (oid_Veiculo)) {
       ed.setOid_veiculo (oid_Veiculo);
     }
     if (JavaUtil.doValida (DM_Situacao)) {
       ed.setDm_situacao (DM_Situacao);
     }
     if (JavaUtil.doValida (DM_Procedencia)) {
       ed.setDM_Procedencia (DM_Procedencia);
     }

     new Manutencao_PreventivaRN ().relManutencaoPreventivaVeiculo (ed);
   }

  public void setNR_Kilometragem_Servico (long NR_Kilometragem_Servico) {
    this.NR_Kilometragem_Servico = NR_Kilometragem_Servico;
  }

  public long getNR_Kilometragem_Servico () {
    return NR_Kilometragem_Servico;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setOID_Movimento_Ordem_Servico (long OID_Movimento_Ordem_Servico) {
    this.OID_Movimento_Ordem_Servico = OID_Movimento_Ordem_Servico;
  }

  public long getOID_Movimento_Ordem_Servico () {
    return OID_Movimento_Ordem_Servico;
  }

  public void setDT_Ultimo_Servico (String DT_Ultimo_Servico) {
    this.DT_Ultimo_Servico = DT_Ultimo_Servico;
  }

  public String getDT_Ultimo_Servico () {
    return DT_Ultimo_Servico;
  }

  public void setCD_Tipo_Servico (String CD_Tipo_Servico) {
    this.CD_Tipo_Servico = CD_Tipo_Servico;
  }

  public String getCD_Tipo_Servico () {
    return CD_Tipo_Servico;
  }

  public void setNR_Kilometragem_Atual (long NR_Kilometragem_Atual) {
    this.NR_Kilometragem_Atual = NR_Kilometragem_Atual;
  }

  public long getNR_Kilometragem_Atual () {
    return NR_Kilometragem_Atual;
  }

  public void setNR_Kilometragem_Realizada (long NR_Kilometragem_Realizada) {
    this.NR_Kilometragem_Realizada = NR_Kilometragem_Realizada;
  }

  public long getNR_Kilometragem_Realizada () {
    return NR_Kilometragem_Realizada;
  }

}
