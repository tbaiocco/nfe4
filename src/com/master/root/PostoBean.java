package com.master.root;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import auth.*;
import com.master.rl.*;
import com.master.util.*;

public class PostoBean {
  private String DT_Cadastro;
  private String NM_Apelido;
  private String DM_Situacao;
  private String TX_Observacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private String NR_CNPJ_CPF;
  private String NM_Razao_Social;
  private String OID_Pessoa;
  private String OID_Posto;
  private double PE_Desconto_Diesel;
  private double VL_Litro_Diesel;
  private double VL_Limite_Abastecimento;

  public PostoBean () {
    OID_Pessoa = "";
    NM_Apelido = " ";
    TX_Observacao = " ";
    VL_Litro_Diesel = 0.0;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Situacao (String situacao) {
    DM_Situacao = situacao;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public void setDm_Stamp (String dm_Stamp) {
    Dm_Stamp = dm_Stamp;
  }

  public String getDT_Cadastro () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Cadastro);
    DT_Cadastro = DataFormatada.getDT_FormataData ();
    return DT_Cadastro;
  }

  public void setDT_Cadastro (String nascimento) {
    DT_Cadastro = nascimento;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public void setDt_Stamp (String dt_Stamp) {
    Dt_Stamp = dt_Stamp;
  }

  public String getNM_Apelido () {
    return NM_Apelido;
  }

  public void setNM_Apelido (String apelido) {
    NM_Apelido = apelido;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String razao_Social) {
    NM_Razao_Social = razao_Social;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public void setNR_CNPJ_CPF (String nr_cnpj_cpf) {
    NR_CNPJ_CPF = nr_cnpj_cpf;
  }

  public String getOID_Posto () {
    return OID_Posto;
  }

  public void setOID_Posto (String funcionario) {
    OID_Posto = funcionario;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa (String pessoa) {
    OID_Pessoa = pessoa;
  }

  public double getPE_Desconto_Diesel () {
    return PE_Desconto_Diesel;
  }

  public void setPE_Desconto_Diesel (double comissao) {
    PE_Desconto_Diesel = comissao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String observacao) {
    TX_Observacao = observacao;
  }

  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String usuario_Stamp) {
    Usuario_Stamp = usuario_Stamp;
  }

  public double getVL_Litro_Diesel () {
    return VL_Litro_Diesel;
  }

  public void setVL_Litro_Diesel (double Litro_Diesel) {
    VL_Litro_Diesel = Litro_Diesel;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
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

    /*
     * Define o insert.
     */

    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Postos ( ");
    buff.append ("  OID_Posto,            ");
    buff.append ("  OID_Pessoa,            ");
    buff.append ("  NM_Apelido,               ");
    buff.append ("  DM_Situacao,     ");
    buff.append ("  TX_Observacao,     ");
    buff.append ("  Dt_Stamp,               ");
    buff.append ("  Usuario_Stamp,          ");
    buff.append ("  Dm_Stamp,               ");
    buff.append ("  DT_Cadastro,     ");
    buff.append ("  PE_Desconto_Diesel,     ");
    buff.append ("  vl_Litro_Diesel, ");
    buff.append ("  vl_limite_abastecimento  )");

    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Posto ());
      pstmt.setString (2 , getOID_Pessoa ());
      pstmt.setString (3 , JavaUtil.trunc (getNM_Apelido () , 30));
      pstmt.setString (4 , getDM_Situacao ());
      pstmt.setString (5 , getTX_Observacao ());
      pstmt.setString (6 , getDt_Stamp ());
      pstmt.setString (7 , getUsuario_Stamp ());
      pstmt.setString (8 , getDm_Stamp ());
      pstmt.setString (9 , this.DT_Cadastro == " " ? null : this.DT_Cadastro);
      pstmt.setDouble (10 , getPE_Desconto_Diesel ());
      pstmt.setDouble (11 , getVL_Litro_Diesel ());
      pstmt.setDouble (12 , getVL_Limite_Abastecimento ());

      // System.out.println (pstmt.toString ());

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
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
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
    buff.append ("UPDATE Postos SET ");
    buff.append ("  NM_Apelido=?,               ");
    buff.append ("  DM_Situacao=?,     ");
    buff.append ("  TX_Observacao=?,     ");
    buff.append ("  PE_Desconto_Diesel=?,     ");
    buff.append ("  vl_Litro_Diesel =?,     ");
    buff.append ("  vl_limite_abastecimento =?             ");
    buff.append (" WHERE OID_Posto=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , JavaUtil.trunc (getNM_Apelido () , 30));
      pstmt.setString (2 , getDM_Situacao ());
      pstmt.setString (3 , getTX_Observacao ());
      pstmt.setDouble (4 , getPE_Desconto_Diesel ());
      pstmt.setDouble (5 , getVL_Litro_Diesel ());
      pstmt.setDouble (6 , getVL_Limite_Abastecimento ());
      pstmt.setString (7 , getOID_Posto ());

      // System.out.println (pstmt.toString ());

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
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Postos ");
    buff.append ("WHERE OID_Posto=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Posto ());
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

  public static final PostoBean getByOID_Posto (String oid_Pessoa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    PostoBean p = new PostoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Postos.*,      ");
      buff.append (" Pessoas.NM_Razao_Social,   ");
      buff.append (" Pessoas.NR_CNPJ_CPF        ");
      buff.append (" FROM                       ");
      buff.append (" Postos,              ");
      buff.append (" Pessoas                    ");
      buff.append (" WHERE Postos.OID_Pessoa = Pessoas.OID_Pessoa ");
      buff.append (" AND   Postos.OID_Pessoa = '");
      buff.append (oid_Pessoa);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Posto (cursor.getString ("OID_Posto"));
        p.setOID_Pessoa (cursor.getString ("OID_Pessoa"));
        p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
        p.setNM_Apelido (cursor.getString ("NM_Apelido"));
        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
        p.setTX_Observacao (cursor.getString ("TX_Observacao"));
        p.setDT_Cadastro (cursor.getString ("DT_Cadastro"));
        p.setPE_Desconto_Diesel (cursor.getDouble ("PE_Desconto_Diesel"));
        p.setVL_Litro_Diesel (cursor.getDouble ("VL_Litro_Diesel"));
        p.setVL_Limite_Abastecimento (cursor.getDouble ("VL_Limite_Abastecimento"));
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

  public static double getVL_Limite_Abastecimento (String oid_Pessoa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    double vl_Limite_Abastecimento = 0;
    double vl_Abastecimento_Mes = 0;

    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    try {
      String sql = " SELECT Postos.vl_Limite_Abastecimento " +
          " FROM Postos  " +
          " WHERE  Postos.OID_Pessoa = '" + oid_Pessoa + "'";

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (sql);

      while (cursor.next ()) {
        vl_Limite_Abastecimento = cursor.getDouble ("VL_Limite_Abastecimento");
      }
      if (vl_Limite_Abastecimento > 0) {
        String dia = Data.getDataDMY () + "     ";
        String data_ini = "01/" + dia.substring (3 , 10);
        // System.out.println ("data_ini->>" + data_ini);
        sql = " SELECT SUM (vl_previsto) as vl_abastecimento " +
            " FROM   Ordens_Compras " +
            " WHERE  Ordens_Compras.OID_Pessoa_Posto = '" + oid_Pessoa + "'" +
            " AND    Ordens_Compras.dt_ordem_compra >= '" + data_ini + "'";

        // System.out.println ("sql->>" + sql);

        cursor = stmt.executeQuery (sql);
        while (cursor.next ()) {
          vl_Abastecimento_Mes = cursor.getDouble ("vl_abastecimento");
        }
        // System.out.println ("vl_Abastecimento_Mes->>" + vl_Abastecimento_Mes);
        vl_Limite_Abastecimento = vl_Limite_Abastecimento - vl_Abastecimento_Mes;
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return vl_Limite_Abastecimento;
  }

  public static final PostoBean getByRecord (HttpServletRequest request) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    PostoBean p = new PostoBean ();
    try {
      String oid_Pessoa = request.getParameter ("oid_Pessoa");
      String oid_Posto = request.getParameter ("oid_Posto");
      String NR_CNPJ_CPF = request.getParameter ("FT_NR_CNPJ_CPF");

      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Postos.*,      ");
      buff.append (" Pessoas.NM_Razao_Social,   ");
      buff.append (" Pessoas.NR_CNPJ_CPF        ");
      buff.append (" FROM                       ");
      buff.append (" Postos,              ");
      buff.append (" Pessoas                    ");
      buff.append (" WHERE Postos.OID_Pessoa = Pessoas.OID_Pessoa ");
      if (JavaUtil.doValida (oid_Pessoa)) {
        buff.append (" AND   Postos.OID_Pessoa = '");
        buff.append (oid_Pessoa);
        buff.append ("'");
      }
      if (JavaUtil.doValida (oid_Posto)) {
        buff.append (" AND   Postos.OID_Posto = '");
        buff.append (oid_Posto);
        buff.append ("'");
      }
      if (JavaUtil.doValida (NR_CNPJ_CPF)) {
        buff.append (" AND   Pessoas.NR_CNPJ_CPF = '");
        buff.append (NR_CNPJ_CPF);
        buff.append ("'");
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Posto (cursor.getString ("OID_Posto"));
        p.setOID_Pessoa (cursor.getString ("OID_Pessoa"));
        p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
        p.setNM_Apelido (cursor.getString ("NM_Apelido"));
        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
        p.setTX_Observacao (cursor.getString ("TX_Observacao"));
        p.setDT_Cadastro (cursor.getString ("DT_Cadastro"));
        p.setPE_Desconto_Diesel (cursor.getDouble ("PE_Desconto_Diesel"));
        p.setVL_Litro_Diesel (cursor.getDouble ("VL_Litro_Diesel"));
        p.setVL_Limite_Abastecimento (cursor.getDouble ("VL_Limite_Abastecimento"));
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

  public static final List Lista (HttpServletRequest request) throws Exception {
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

    List Posto_Lista = new ArrayList ();
    try {
      String NM_Razao_Social = request.getParameter ("FT_NM_Razao_Social");
      String NM_Apelido = request.getParameter ("FT_NM_Apelido");
      String NR_CNPJ_CPF = request.getParameter ("FT_NR_CNPJ_CPF");

      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Postos.*,      ");
      buff.append (" Pessoas.NM_Razao_Social,   ");
      buff.append (" Pessoas.NR_CNPJ_CPF        ");
      buff.append (" FROM                       ");
      buff.append (" Postos,              ");
      buff.append (" Pessoas                    ");
      buff.append (" WHERE Postos.OID_Pessoa = Pessoas.OID_Pessoa ");

      if (JavaUtil.doValida (NM_Razao_Social)) {
        buff.append (" AND   Pessoas.NM_Razao_Social LIKE '");
        buff.append (NM_Razao_Social);
        buff.append ("%'");
      }
      if (JavaUtil.doValida (NM_Apelido)) {
        buff.append (" AND   Postos.NM_Apelido LIKE '%");
        buff.append (NM_Apelido);
        buff.append ("'");
      }
      if (JavaUtil.doValida (NR_CNPJ_CPF)) {
        buff.append (" AND   Pessoas.NR_CNPJ_CPF = '");
        buff.append (NR_CNPJ_CPF);
        buff.append ("'");
      }
      if (JavaUtil.doValida (NM_Apelido)) {
        buff.append (" ORDER BY Postos.NM_Apelido, Pessoas.NM_Razao_Social ");
      }
      else {
        buff.append (" ORDER BY Pessoas.NM_Razao_Social, Postos.NM_Apelido ");
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PostoBean p = new PostoBean ();
        p.setOID_Posto (cursor.getString ("OID_Posto"));
        p.setOID_Pessoa (cursor.getString ("OID_Pessoa"));
        p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
        p.setNM_Apelido (cursor.getString ("NM_Apelido"));
        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
        p.setTX_Observacao (cursor.getString ("TX_Observacao"));
        p.setDT_Cadastro (cursor.getString ("DT_Cadastro"));
        p.setPE_Desconto_Diesel (cursor.getDouble ("PE_Desconto_Diesel"));
        p.setVL_Litro_Diesel (cursor.getDouble ("VL_Litro_Diesel"));
        Posto_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Posto_Lista;
  }

  //Metodos do MotoristaBean...
  //Ficaram para ajudar no futuro, impressoes e etc.


  public byte[] geraRelacaoPostos (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    byte[] b = null;

    try {
      String DM_Situacao = request.getParameter ("FT_DM_Situacao");
      String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");
      String DT_Inicial = request.getParameter ("FT_DT_Inicial");
      String DT_Final = request.getParameter ("FT_DT_Final");
      String oid_Posto = request.getParameter ("oid_Posto");

      String sql = null;

      sql =
          "Select * from Pessoas, Postos, Cidades, Estados, Regioes_Estados " +
          "where Pessoas.OID_Pessoa = Postos.OID_Pessoa " +
          "and Pessoas.OID_Cidade = Cidades.OID_Cidade " +
          "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
          "and Regioes_Estados.OID_Estado = Estados.OID_Estado ";

      if (oid_Posto != null && !oid_Posto.equals ("") &&
          !oid_Posto.equals ("") && !oid_Posto.equals ("null")) {
        sql += " and Postos.OID_Pessoa = '" + oid_Posto + "'";
      }
      else {
        if (DM_Situacao != null && !DM_Situacao.equals ("T") &&
            !DM_Situacao.equals ("") && !DM_Situacao.equals ("null")) {
          sql += " and Postos.DM_Situacao = '" + DM_Situacao + "'";
        }
        sql += " Order by NM_Razao_Social";
      }

      Connection conn = null;

      try {
        conn = OracleConnection2.getWEB ();
        conn.setAutoCommit (false);
      }
      catch (Exception e) {
        e.printStackTrace ();
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      PostoRL motRL = new PostoRL ();
      b = motRL.geraRelacaoPostos (cursor , DM_Situacao , DM_Relatorio , DT_Inicial , DT_Final);

    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
    return b;
  }

  public void setVL_Limite_Abastecimento (double VL_Limite_Abastecimento) {
    this.VL_Limite_Abastecimento = VL_Limite_Abastecimento;
  }

  public double getVL_Limite_Abastecimento () {
    return VL_Limite_Abastecimento;
  }

}
