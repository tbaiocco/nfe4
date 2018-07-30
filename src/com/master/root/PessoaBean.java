package com.master.root;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

import auth.*;
import com.master.util.*;
import com.master.util.ed.*;

public class PessoaBean
    extends JavaUtil {

  private String NR_CNPJ_CPF;
  private String NM_Razao_Social;
  private String NM_Fantasia;
  private String NM_Endereco;
  private String NM_Bairro;
  private String NR_CEP;
  private String NM_Inscricao_Estadual;
  private String NM_Inscricao_Municipal;
  private String NM_Cidade;
  private String NM_Cidade_Estado_Pais;
  private String Usuario_Stamp;

  private String NM_Codigo_Aereo;
  private String DM_Tipo_Localizacao;
  private String DM_Suframa;
  private String DM_Fluvial;
  private String CD_Regiao_Estado;
  private String NM_Regiao_Estado;
  private String CD_Estado;
  private String NM_Estado;
  private String CD_Regiao_Pais;
  private String NM_Regiao_Pais;
  private String CD_Pais;
  private String NM_Pais;

  private String Dt_Stamp;
  private String Dm_Stamp;
  private String oid;
  private int oid_Cidade;
  private String CD_Grupo_Pessoa;
  private String NM_Grupo_Pessoa;
  private String DM_Tipo_Pessoa;
  private String OID_Vendedor;
  private String CD_Unidade;
  private String CD_Cidade;
  private int oid_Regiao_Estado;
  private int oid_Estado;
  private int oid_Regiao_Pais;
  private int oid_Pais;
  private String NR_Telefone;
  private String NR_Fax;
  private String eMail;
  private String NM_Site;
  private String DM_CNPJ_CPF_Valido;

  public PessoaBean () {
  }

  public String getNR_Fax () {
    return NR_Fax;
  }

  public void setNR_Fax (String fax) {
    NR_Fax = fax;
  }

  public String getNM_Site () {
    return NM_Site;
  }

  public void setNM_Site (String site) {
    NM_Site = site;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public void setNR_CNPJ_CPF (String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public String getNM_Cidade_Estado_Pais () {
    return NM_Cidade_Estado_Pais;
  }

  public void setNM_Cidade_Estado_Pais (String NM_Cidade_Estado_Pais) {
    this.NM_Cidade_Estado_Pais = NM_Cidade_Estado_Pais;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public String getNM_Endereco () {
    return NM_Endereco;
  }

  public void setNM_Endereco (String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }

  public String getNM_Bairro () {
    return NM_Bairro;
  }

  public void setNM_Bairro (String NM_Bairro) {
    this.NM_Bairro = NM_Bairro;
  }

  public String getNR_CEP () {
    return NR_CEP;
  }

  public void setNR_CEP (String NR_CEP) {
    this.NR_CEP = NR_CEP;
  }

  public String getNM_Inscricao_Estadual () {
    return NM_Inscricao_Estadual;
  }

  public void setNM_Inscricao_Estadual (String NM_Inscricao_Estadual) {
    this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
  }

  public String getCD_Grupo_Pessoa () {
    return CD_Grupo_Pessoa;
  }

  public void setCD_Grupo_Pessoa (String CD_Grupo_Pessoa) {
    this.CD_Grupo_Pessoa = CD_Grupo_Pessoa;
  }

  public String getNM_Grupo_Pessoa () {
    return NM_Grupo_Pessoa;
  }

  public void setNM_Grupo_Pessoa (String NM_Grupo_Pessoa) {
    this.NM_Grupo_Pessoa = NM_Grupo_Pessoa;
  }

  public String getDM_Tipo_Pessoa () {
    return DM_Tipo_Pessoa;
  }

  public void setDM_Tipo_Pessoa (String DM_Tipo_Pessoa) {
    this.DM_Tipo_Pessoa = DM_Tipo_Pessoa;
  }

  public String getOID_Vendedor () {
    return OID_Vendedor;
  }

  public void setOID_Vendedor (String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }

  /*
   * ---------------- Bloco Padrão para Todas Classes ------------------
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

  public String getOID () {
    return oid;
  }

  public void setOID (String n) {
    this.oid = n;
  }

  public int getOID_Cidade () {
    return oid_Cidade;
  }

  public void setOID_Cidade (int n) {
    this.oid_Cidade = n;
  }

  public void insert () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    try {

      if (!JavaUtil.doValida (getOID ())) {
        if (!this.getCD_Pais ().equals ("BR") && !this.getCD_Pais ().equals ("BRA")) {
          this.setOID (this.getDigitoVerificador ("1"));
        }
        else {
          this.setOID (this.getNR_CNPJ_CPF ());
        }
      }

      StringBuffer buff = new StringBuffer ();

      buff.append ("INSERT INTO Pessoas (OID_Pessoa, OID_Cidade, NR_CNPJ_CPF, NM_Endereco, " +
                   "NM_Bairro, NR_CEP, NM_Razao_Social, NM_Fantasia, NM_Inscricao_Estadual, " +
                   "NR_Telefone, EMail, NM_Site, NR_Fax, DM_Valida_Sefaz) ");
      buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , getOID ());
      pstmt.setInt (2 , getOID_Cidade ());
      pstmt.setString (3 , getNR_CNPJ_CPF ());
      pstmt.setString (4 , getNM_Endereco ());
      pstmt.setString (5 , getNM_Bairro ());
      pstmt.setString (6 , getNR_CEP ());
      pstmt.setString (7 , getNM_Razao_Social ());
      pstmt.setString (8 , JavaUtil.trunc (getNM_Fantasia () , 30));
      pstmt.setString (9 , getNM_Inscricao_Estadual ());
      pstmt.setString (10 , getNR_Telefone ());
      pstmt.setString (11 , getEMail ());
      pstmt.setString (12 , getNM_Site ());
      pstmt.setString (13 , getNR_Fax ());
      pstmt.setString (14 , (getValue (getNR_CNPJ_CPF ()).length () == 14 && Validacao.CNPJ (getNR_CNPJ_CPF ()) ? "S" : "N"));
      pstmt.executeUpdate ();

    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

  }

  public void insert_Grupo_Pessoa () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    try {
      StringBuffer buff = new StringBuffer ();

      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      buff.append ("INSERT INTO Grupos_Pessoas_Cargas (OID_Pessoa, CD_GRUPO_PESSOA, DM_TIPO_PESSOA) ");
      buff.append ("VALUES (?,?,?)");

      if (this.getCD_Grupo_Pessoa () == null || this.getCD_Grupo_Pessoa ().equals ("null") || this.getCD_Grupo_Pessoa ().equals ("")) {
        this.setCD_Grupo_Pessoa ("9999");
      }
      pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , this.getOID ());
      pstmt.setString (2 , this.getCD_Grupo_Pessoa ());
      pstmt.setString (3 , this.getDM_Tipo_Pessoa ());
      pstmt.executeUpdate ();

    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

  }

  public void update_old () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Endereco do DSN
      // o NM_Endereco de usuário e a senha do banco.
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
    buff.append ("UPDATE Pessoas SET OID_Cidade=?, NR_CNPJ_CPF=?, NM_Endereco=?, NM_Bairro=?, " + "NR_CEP=?, NM_Razao_Social=?, NM_Fantasia=?, NM_Inscricao_Estadual=?, Dt_Stamp=?, "
                 + "Usuario_Stamp=?, Dm_Stamp=? , NR_Telefone=?, EMail=?, NM_Site=?, nr_fax=?, DM_Valida_Sefaz=? ");
    buff.append ("WHERE OID_Pessoa=?");
    /*
     * Define os dados do SQL e executa o insert no banco.
     */
    try {

      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Cidade ());
      pstmt.setString (2 , getNR_CNPJ_CPF ());
      pstmt.setString (3 , getNM_Endereco ());
      pstmt.setString (4 , getNM_Bairro ());
      pstmt.setString (5 , getNR_CEP ());
      pstmt.setString (6 , getNM_Razao_Social ());
      pstmt.setString (7 , getNM_Fantasia ());
      pstmt.setString (8 , getNM_Inscricao_Estadual ());
      pstmt.setString (9 , getDt_Stamp ());
      pstmt.setString (10 , getUsuario_Stamp ());
      pstmt.setString (11 , getDm_Stamp ());
      pstmt.setString (12 , getNR_Telefone ());
      pstmt.setString (13 , getEMail ());
      pstmt.setString (14 , getNM_Site ());
      pstmt.setString (15 , getNR_Fax ());
      pstmt.setString (16 , (getValue (getNR_CNPJ_CPF ()).length () == 14 && Validacao.CNPJ (getNR_CNPJ_CPF ()) ? "S" : "N"));
      pstmt.setString (17 , getOID ());
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
      // passando como parâmetro o NM_Endereco do DSN
      // o NM_Endereco de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Pessoas ");
    buff.append ("WHERE OID_Pessoa=?");
    /*
     * Define os dados do SQL e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID ());
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

  public static final PessoaBean getByOID_old (String oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Endereco do DSN
      // o NM_Endereco de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    PessoaBean p = new PessoaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Pessoa, ");
      buff.append ("	OID_Cidade, ");
      buff.append ("	NR_CNPJ_CPF, ");
      buff.append ("	NM_Razao_Social, ");
      buff.append ("	NM_Endereco, ");
      buff.append ("	NM_Bairro, ");
      buff.append ("	NR_CEP, ");
      buff.append ("	NM_Fantasia, ");
      buff.append ("	NM_Inscricao_Estadual, ");
      buff.append ("	NM_Inscricao_Municipal ");
      buff.append ("FROM Pessoas ");
      buff.append ("WHERE OID_Pessoa= '");
      buff.append (oid);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Inscricao_Municipal (cursor.getString (10));

        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
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

  public boolean CGCValido (String number) {

    int soma = 0;

    if (number.length () == 14) {
      for (int i = 0 , j = 5; i < 12; i++) {
        soma += j-- * (number.charAt (i) - '0');
        if (j < 2) {
          j = 9;
        }
      }
      soma = 11 - (soma % 11);
      if (soma > 9) {
        soma = 0;
      }
      if (soma == (number.charAt (12) - '0')) {
        soma = 0;
        for (int i = 0 , j = 6; i < 13; i++) {
          soma += j-- * (number.charAt (i) - '0');
          if (j < 2) {
            j = 9;
          }
        }
        soma = 11 - (soma % 11);
        if (soma > 9) {
          soma = 0;
        }
        if (soma == (number.charAt (13) - '0')) {
          return true;
        }
      }
    }
    return false;
  }

  public String getDigitoVerificador (String oid) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    int oid_Unidade = Parametro_FixoED.getInstancia ().getOID_Unidade_Padrao ();

    String NR_PROXIMO_CNPJ = null;
    String number = null;
    int oid_Parametro_Filial = 0;

    DecimalFormat dec = new DecimalFormat ("00");

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Parametros_Filiais.NR_PROXIMO_CNPJ, Parametros_Filiais.oid_Parametro_Filial ");
      buff.append ("FROM  Parametros_Filiais ");
      buff.append ("WHERE oid_unidade = " + oid_Unidade);

      // System.out.println (buff.toString ());

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      if (cursor.next ()) {
        NR_PROXIMO_CNPJ = cursor.getString (1);
        oid_Parametro_Filial = cursor.getInt (2);
        // System.out.println ("NR_PROXIMO_CNPJ 1=" + NR_PROXIMO_CNPJ);
      }
      double nr_p = new Long (NR_PROXIMO_CNPJ).longValue ();
      if (nr_p == 0 || nr_p < 999999 * 100000) {
        NR_PROXIMO_CNPJ = "999999000000";
      }
      // System.out.println ("NR_PROXIMO_CNPJ 2=" + NR_PROXIMO_CNPJ);

      if (NR_PROXIMO_CNPJ != null) {

        PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
        long gg = new Long (NR_PROXIMO_CNPJ).longValue () + 1;
        String NR_Proximo_CNPJ = String.valueOf (gg);

        String sql = " UPDATE Parametros_Filiais SET  NR_Proximo_CNPJ='" + NR_Proximo_CNPJ + "'" +
            " WHERE OID_Parametro_Filial=" + oid_Parametro_Filial;

        // System.out.println (sql);

        pstmt.executeUpdate (sql);

        pstmt.close ();

        boolean DM_Achou_Digito = false;
        String cc = "00";

        int cont = 0;
        while (!DM_Achou_Digito && cont < 100) {
          cont++;

          int bb = new Integer (cc).intValue () + 1;
          cc = dec.format (bb);
          number = NR_PROXIMO_CNPJ + cc;
          DM_Achou_Digito = this.CGCValido (number);
        }
      }
      cursor.close ();
      stmt.close ();
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
    return JavaUtil.trunc (number , 14);
  }

  public static String verifica_CNPJ_CPF (String CNPJ_CPF) throws Exception {
    String DM_CNPJ_CPF_Valido = "S";
    if ("S".equals (Parametro_FixoED.getInstancia ().getDM_Verifica_CNPJ_CPF_Pessoa ())) {
      DM_CNPJ_CPF_Valido = "N";
      if (CNPJ_CPF.length () == 14 && Validacao.CNPJ (CNPJ_CPF)) {
        DM_CNPJ_CPF_Valido = "S";
      }
      else {
        if (Validacao.CPF (CNPJ_CPF)) {
          DM_CNPJ_CPF_Valido = "S";
        }
      }
    }
    return DM_CNPJ_CPF_Valido;
  }

  public static final List getByOID_Cidade (int OID_Cidade) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Endereco do DSN
      // o NM_Endereco de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Pessoa, ");
      buff.append ("	OID_Cidade, ");
      buff.append ("	NR_CNPJ_CPF, ");
      buff.append ("	NM_Razao_Social, ");
      buff.append ("	NM_Endereco, ");
      buff.append ("	NM_Bairro, ");
      buff.append ("	NR_CEP, ");
      buff.append ("	NM_Fantasia, ");
      buff.append ("	NM_Inscricao_Estadual ");
      buff.append ("FROM Pessoas ");
      buff.append ("WHERE OID_Cidade =");
      buff.append (OID_Cidade);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));

        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public static final PessoaBean getByNR_CNPJ_CPF (String NR_CNPJ_CPF) throws Exception {
    PessoaBean p = new PessoaBean ();

    //*** Valida Parâmetro
     if (!JavaUtil.doValida (NR_CNPJ_CPF)) {
       return p;
     }
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    try {
      String sqlBusca = " SELECT " + "   Pessoas.OID_Pessoa, " + "	Pessoas.OID_Cidade, " + "	Pessoas.NR_CNPJ_CPF, " + "	Pessoas.NM_Razao_Social, " + "	Pessoas.NM_Endereco, "
          + "	Pessoas.NM_Bairro, " + "	Pessoas.NR_CEP, " + "	Pessoas.NM_Fantasia, " + "	Pessoas.NM_Inscricao_Estadual, " + "	Pessoas.NR_Telefone, " + "   Pessoas.OID_Cidade,"
          + "   Cidades.CD_Cidade, " + "   Cidades.NM_Cidade, " + "   Estados.OID_Estado, " + "   Estados.CD_Estado, " + "   Estados.NM_Estado, " + "   Pessoas.EMail, "
          + "   Pessoas.NM_Site, Pessoas.nr_fax " + " FROM Pessoas, Cidades, Regioes_Estados, Estados " + " WHERE Pessoas.NR_CNPJ_CPF= '" + NR_CNPJ_CPF.trim () + "'"
          + "   and Pessoas.OID_Cidade = Cidades.OID_Cidade " + "   and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado "
          + "   and Regioes_Estados.OID_Estado = Estados.OID_Estado";

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sqlBusca);

      while (cursor.next ()) {
        p.setOID (cursor.getString ("OID_Pessoa"));
        p.setOID_Cidade (cursor.getInt ("OID_Cidade"));
        p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        p.setNM_Endereco (cursor.getString ("NM_Endereco"));
        p.setNM_Bairro (cursor.getString ("NM_Bairro"));
        p.setNR_CEP (cursor.getString ("NR_CEP"));
        p.setNM_Fantasia (cursor.getString ("NM_Fantasia"));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNR_Telefone (cursor.getString (10));

        p.setOid_Cidade (cursor.getInt ("OID_Cidade"));
        p.setCD_Cidade (cursor.getString ("CD_Cidade"));
        p.setNM_Cidade (cursor.getString ("NM_Cidade"));
        p.setOid_Estado (cursor.getInt ("OID_Estado"));
        p.setCD_Estado (cursor.getString ("CD_Estado"));
        p.setNM_Estado (cursor.getString ("NM_Estado"));
        p.setEMail (cursor.getString ("EMail"));
        p.setNM_Site (cursor.getString ("NM_Site"));
        p.setNR_Fax (cursor.getString ("nr_fax"));
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

  public static final PessoaBean getByEndereco_Completo (String NR_CNPJ_CPF) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    PessoaBean p = new PessoaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Pessoas.NR_Telefone, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.NM_Codigo_Aereo, ");
      buff.append ("	Cidades.DM_Tipo_Localizacao, ");
      buff.append ("	Cidades.DM_Suframa, ");
      buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
      buff.append ("	Estados.OID_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
      buff.append ("	Paises.OID_Pais, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais, ");
      buff.append ("	Pessoas.EMail, ");
      buff.append ("   Pessoas.NM_Site, ");
      buff.append ("   Pessoas.nr_fax ");
      buff.append (" FROM Pessoas, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
      buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" AND Cidades.OID_Cidade = Pessoas.oid_Cidade");
      buff.append (" AND NR_CNPJ_CPF= '");
      buff.append (NR_CNPJ_CPF);
      buff.append ("'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNR_Telefone (cursor.getString (10));
        p.setNM_Cidade (cursor.getString (11));
        p.setCD_Cidade (cursor.getString (12));
        p.setNM_Codigo_Aereo (cursor.getString (13));
        p.setDM_Tipo_Localizacao (cursor.getString (14));
        p.setDM_Suframa (cursor.getString (15));
        p.setOid_Regiao_Estado (cursor.getInt (16));
        p.setNM_Regiao_Estado (cursor.getString (17));
        p.setCD_Regiao_Estado (cursor.getString (18));
        p.setOid_Estado (cursor.getInt (19));
        p.setNM_Estado (cursor.getString (20));
        p.setCD_Estado (cursor.getString (21));
        p.setOid_Regiao_Pais (cursor.getInt (22));
        p.setNM_Regiao_Pais (cursor.getString (23));
        p.setCD_Regiao_Pais (cursor.getString (24));
        p.setOid_Pais (cursor.getInt (25));
        p.setNM_Pais (cursor.getString (26));
        p.setCD_Pais (cursor.getString (27));
        p.setEMail (cursor.getString (28));
        p.setNM_Site (cursor.getString (29));
        p.setNM_Cidade_Estado_Pais (p.getNM_Cidade ().trim () + " - " + p.CD_Estado.trim () + " - " + p.NM_Pais.trim ());
        p.setNR_Fax (cursor.getString (30));

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

  public static final List getByNM_Nome_Fantasia (String NM_Nome_Fantasia) throws Exception {

    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade ");
      buff.append (" FROM Pessoas, Cidades ");
      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append (" AND Pessoas.NM_Fantasia LIKE '");
      buff.append (NM_Nome_Fantasia);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public static final List getByNR_Telefone (String NR_Telefone) throws Exception {

    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade ");
      buff.append (" FROM Pessoas, Cidades ");
      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append (" AND Pessoas.NR_Telefone LIKE '");
      buff.append (NR_Telefone);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade ");
      buff.append (" FROM Pessoas, Cidades ");
      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append (" AND Pessoas.NR_CNPJ_CPF = '");
      buff.append (NR_CNPJ_CPF);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public static final List getByNM_Razao_Social (String NM_Razao_Social) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade ");
      buff.append (" FROM Pessoas, Cidades ");
      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append (" AND Pessoas.NM_Razao_Social LIKE '");
      buff.append (NM_Razao_Social);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (JavaUtil.trunc (p.getNM_Razao_Social () , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public static final List getByNM_Razao_Social_Endereco (String NM_Razao_Social) throws Exception {

    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.NM_Codigo_Aereo, ");
      buff.append ("	Cidades.DM_Tipo_Localizacao, ");
      buff.append ("	Cidades.DM_Suframa, ");
      buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
      buff.append ("	Estados.OID_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
      buff.append ("	Paises.OID_Pais, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais, ");
      buff.append ("	Cidades.oid_cidade ");
      buff.append (" FROM Pessoas, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
      buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" AND Cidades.OID_Cidade = Pessoas.oid_Cidade");
      buff.append (" AND Pessoas.NM_Razao_Social LIKE '");
      buff.append (NM_Razao_Social);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));

        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (JavaUtil.trunc (p.getNM_Razao_Social () , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        p.setCD_Cidade (cursor.getString (11));
        p.setNM_Codigo_Aereo (cursor.getString (12));
        p.setDM_Tipo_Localizacao (cursor.getString (13));
        p.setDM_Suframa (cursor.getString (14));
        p.setOid_Regiao_Estado (cursor.getInt (15));
        p.setNM_Regiao_Estado (cursor.getString (16));
        p.setCD_Regiao_Estado (cursor.getString (17));
        p.setOid_Estado (cursor.getInt (18));
        p.setNM_Estado (cursor.getString (19));
        p.setCD_Estado (cursor.getString (20));
        p.setOid_Regiao_Pais (cursor.getInt (21));
        p.setNM_Regiao_Pais (cursor.getString (22));
        p.setCD_Regiao_Pais (cursor.getString (23));
        p.setOid_Pais (cursor.getInt (24));
        p.setNM_Pais (cursor.getString (25));
        p.setCD_Pais (cursor.getString (26));
        p.setNM_Cidade_Estado_Pais (p.getNM_Cidade ().trim () + " - " + p.CD_Estado.trim () + " - " + p.NM_Pais.trim ());
        p.setOid_Cidade (cursor.getInt (27));

        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  //*** FILTRO GENERICO
   public static final List listaPessoa (HttpServletRequest request , String orderByField) throws Exception {

     Connection conn = null;
     try {
       conn = OracleConnection2.getWEB ();
       conn.setAutoCommit (false);
     }
     catch (Exception e) {
       e.printStackTrace ();
       throw e;
     }
     //*** Parâmetros
      String oid_Pessoa = request.getParameter ("oid_Pessoa");
     String nrCnpjCpf = request.getParameter ("FT_NR_CNPJ_CPF");
     String nmRazaoSocial = request.getParameter ("FT_NM_Razao_Social");
     String nmFantasia = request.getParameter ("FT_NM_Fantasia");
     String nrCEP = request.getParameter ("FT_NR_CEP");
     String nmEndereco = request.getParameter ("FT_NM_Endereco");
     String nmBairro = request.getParameter ("FT_NM_Bairro");
     String nmCidade = request.getParameter ("FT_NM_Cidade");

     List lista = new ArrayList ();
     try {
       StringBuffer buff = new StringBuffer ();
       buff.append ("SELECT Pessoas.OID_Pessoa, ");
       buff.append ("   Pessoas.OID_Cidade, ");
       buff.append ("   Pessoas.NR_CNPJ_CPF, ");
       buff.append ("   Pessoas.NM_Razao_Social, ");
       buff.append ("   Pessoas.NM_Endereco, ");
       buff.append ("   Pessoas.NM_Bairro, ");
       buff.append ("   Pessoas.NR_CEP, ");
       buff.append ("   Pessoas.NM_Fantasia, ");
       buff.append ("   Pessoas.NM_Inscricao_Estadual, ");
       buff.append ("   Cidades.NM_Cidade, ");
       buff.append ("   Cidades.CD_Cidade, ");
       buff.append ("   Cidades.NM_Codigo_Aereo, ");
       buff.append ("   Cidades.DM_Tipo_Localizacao, ");
       buff.append ("   Cidades.DM_Suframa, ");
       buff.append ("   Regioes_Estados.OID_Regiao_Estado, ");
       buff.append ("   Regioes_Estados.NM_Regiao_Estado, ");
       buff.append ("   Regioes_Estados.CD_Regiao_Estado, ");
       buff.append ("   Estados.OID_Estado, ");
       buff.append ("   Estados.NM_Estado, ");
       buff.append ("   Estados.CD_Estado, ");
       buff.append ("   Regioes_Paises.OID_Regiao_Pais, ");
       buff.append ("   Regioes_Paises.NM_Regiao_Pais, ");
       buff.append ("   Regioes_Paises.CD_Regiao_Pais, ");
       buff.append ("   Paises.OID_Pais, ");
       buff.append ("   Paises.NM_Pais, ");
       buff.append ("   Paises.CD_Pais, ");
       buff.append ("   Cidades.oid_cidade ");
       buff.append (" FROM Pessoas, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
       buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
       buff.append ("   AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
       buff.append ("   AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
       buff.append ("   AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
       buff.append ("   AND Cidades.OID_Cidade = Pessoas.oid_Cidade");
       if (doValida (oid_Pessoa)) {
         buff.append (" AND Pessoas.oid_Pessoa = '" + oid_Pessoa + "'");
       }
       else {
         if (doValida (nrCnpjCpf)) {
           buff.append (" AND Pessoas.NR_CNPJ_CPF = '" + nrCnpjCpf + "'");
         }
         if (doValida (nmRazaoSocial)) {
           buff.append (" AND Pessoas.NM_Razao_Social LIKE '" + nmRazaoSocial + "%'");
         }
         if (doValida (nmFantasia)) {
           buff.append (" AND Pessoas.NM_Fantasia LIKE '" + nmFantasia + "%'");
         }
         if (doValida (nrCEP)) {
           buff.append (" AND Pessoas.NR_CEP LIKE '" + nrCEP + "%'");
         }
         if (doValida (nmEndereco)) {
           buff.append (" AND Pessoas.NM_Endereco LIKE '" + nmEndereco + "%'");
         }
         if (doValida (nmBairro)) {
           buff.append (" AND Pessoas.NM_Bairro LIKE '" + nmBairro + "%'");
         }
         if (doValida (nmCidade)) {
           buff.append (" AND Cidades.NM_Cidade LIKE '" + nmCidade + "%'");
         }
       }

       if ("NR_CNPJ_CPF".equals (orderByField)) {
         buff.append (" ORDER BY Pessoas.NR_CNPJ_CPF ");
       }
       else if ("NM_Razao_Social".equals (orderByField)) {
         buff.append (" ORDER BY Pessoas.NM_Razao_Social ");
       }
       else if ("NM_Fantasia".equals (orderByField)) {
         buff.append (" ORDER BY Pessoas.NM_Fantasia ");
       }
       else if ("NR_CEP".equals (orderByField)) {
         buff.append (" ORDER BY Pessoas.NR_CEP ");
       }
       else if ("NM_Endereco".equals (orderByField)) {
         buff.append (" ORDER BY Pessoas.NM_Endereco ");
       }
       else if ("NM_Bairro".equals (orderByField)) {
         buff.append (" ORDER BY Pessoas.NM_Bairro ");
       }
       else if ("NM_Cidade".equals (orderByField)) {
         buff.append (" ORDER BY Cidades.NM_Cidade ");
       }
       else {
         buff.append (" ORDER BY Pessoas.NM_Razao_Social ");
       }

       Statement stmt = conn.createStatement ();
       ResultSet cursor = stmt.executeQuery (buff.toString ());

       while (cursor.next ()) {
         PessoaBean p = new PessoaBean ();
         p.setOID (cursor.getString (1));
         p.setOID_Cidade (cursor.getInt (2));
         p.setNR_CNPJ_CPF (cursor.getString (3));
         p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

         p.setNM_Razao_Social (cursor.getString (4));
         p.setNM_Endereco (getValueDef (cursor.getString (5) , ""));
         p.setNM_Bairro (getValueDef (cursor.getString (6) , ""));
         p.setNR_CEP (getValueDef (cursor.getString (7) , ""));
         p.setNM_Fantasia (getValueDef (cursor.getString (8) , trunc (p.getNM_Razao_Social () , 30)));
         p.setNM_Inscricao_Estadual (getValueDef (cursor.getString (9) , ""));
         p.setNM_Cidade (cursor.getString (10));
         p.setCD_Cidade (cursor.getString (11));
         p.setNM_Codigo_Aereo (cursor.getString (12));
         p.setDM_Tipo_Localizacao (cursor.getString (13));
         p.setDM_Suframa (cursor.getString (14));
         p.setOid_Regiao_Estado (cursor.getInt (15));
         p.setNM_Regiao_Estado (cursor.getString (16));
         p.setCD_Regiao_Estado (cursor.getString (17));
         p.setOid_Estado (cursor.getInt (18));
         p.setNM_Estado (cursor.getString (19));
         p.setCD_Estado (cursor.getString (20));
         p.setOid_Regiao_Pais (cursor.getInt (21));
         p.setNM_Regiao_Pais (cursor.getString (22));
         p.setCD_Regiao_Pais (cursor.getString (23));
         p.setOid_Pais (cursor.getInt (24));
         p.setNM_Pais (cursor.getString (25));
         p.setCD_Pais (cursor.getString (26));
         p.setNM_Cidade_Estado_Pais (p.getNM_Cidade ().trim () + " - " + p.CD_Estado.trim () + " - " + p.NM_Pais.trim ());
         p.setOid_Cidade (cursor.getInt (27));
         lista.add (p);
       }
       cursor.close ();
       stmt.close ();
     }
     catch (Exception e) {
       e.printStackTrace ();
     }
     finally {
       conn.close ();
     }
     return lista;
   }

  ///### MEGA LISTAR UNIDADES X PESSOAS
  public static final List getByUnidades (String NM_Razao_Social) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("   Unidades.CD_Unidade ");
      buff.append (" FROM Pessoas, Cidades, Unidades ");
      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa  ");
      buff.append (" AND Pessoas.NM_Razao_Social LIKE'");
      buff.append (NM_Razao_Social);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        p.setCD_Unidade (cursor.getString (11));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public static final PessoaBean getByOID_Unidade (String OID_Pessoa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    PessoaBean edVolta = new PessoaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Pessoas.NM_Inscricao_Municipal, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("   Unidades.CD_Unidade ");
      buff.append (" FROM Pessoas, Cidades, Unidades ");
      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa  ");
      buff.append (" AND Pessoas.OID_Pessoa = '");
      buff.append (OID_Pessoa);
      buff.append ("'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        edVolta.setOID (cursor.getString (1));
        edVolta.setOID_Cidade (cursor.getInt (2));
        edVolta.setNR_CNPJ_CPF (cursor.getString (3));
        edVolta.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (edVolta.getNR_CNPJ_CPF ()));

        edVolta.setNM_Razao_Social (cursor.getString (4));
        edVolta.setNM_Endereco (cursor.getString (5));
        edVolta.setNM_Bairro (cursor.getString (6));
        edVolta.setNR_CEP (cursor.getString (7));
        edVolta.setNM_Fantasia (cursor.getString (8));
        if (edVolta.getNM_Fantasia () == null || edVolta.getNM_Fantasia ().equals ("null") || edVolta.getNM_Fantasia ().equals ("")) {
          edVolta.setNM_Fantasia ( (edVolta.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }
        edVolta.setNM_Inscricao_Estadual (cursor.getString (9));
        edVolta.setNM_Inscricao_Municipal (cursor.getString (10));
        edVolta.setNM_Cidade (cursor.getString (11));
        edVolta.setCD_Unidade (cursor.getString (12));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return edVolta;
  }

  public static final PessoaBean getByOID_Unidade(int oid_unidade) throws Exception {
	    Connection conn = null;
	    try {
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }

	    PessoaBean edVolta = new PessoaBean ();
	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT Pessoas.OID_Pessoa, ");
	      buff.append ("	Pessoas.OID_Cidade, ");
	      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
	      buff.append ("	Pessoas.NM_Razao_Social, ");
	      buff.append ("	Pessoas.NM_Endereco, ");
	      buff.append ("	Pessoas.NM_Bairro, ");
	      buff.append ("	Pessoas.NR_CEP, ");
	      buff.append ("	Pessoas.NM_Fantasia, ");
	      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
	      buff.append ("	Pessoas.NM_Inscricao_Municipal, ");
	      buff.append ("	Cidades.NM_Cidade, ");
	      buff.append ("   Unidades.CD_Unidade ");
	      buff.append (" FROM Pessoas, Cidades, Unidades ");
	      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa  ");
	      buff.append (" AND Unidades.OID_unidade = ");
	      buff.append (oid_unidade);
	      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor = stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	        edVolta.setOID (cursor.getString (1));
	        edVolta.setOID_Cidade (cursor.getInt (2));
	        edVolta.setNR_CNPJ_CPF (cursor.getString (3));
	        edVolta.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (edVolta.getNR_CNPJ_CPF ()));

	        edVolta.setNM_Razao_Social (cursor.getString (4));
	        edVolta.setNM_Endereco (cursor.getString (5));
	        edVolta.setNM_Bairro (cursor.getString (6));
	        edVolta.setNR_CEP (cursor.getString (7));
	        edVolta.setNM_Fantasia (cursor.getString (8));
	        if (edVolta.getNM_Fantasia () == null || edVolta.getNM_Fantasia ().equals ("null") || edVolta.getNM_Fantasia ().equals ("")) {
	          edVolta.setNM_Fantasia ( (edVolta.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
	        }
	        edVolta.setNM_Inscricao_Estadual (cursor.getString (9));
	        edVolta.setNM_Inscricao_Municipal (cursor.getString (10));
	        edVolta.setNM_Cidade (cursor.getString (11));
	        edVolta.setCD_Unidade (cursor.getString (12));

	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	    }
	    return edVolta;
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

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade ");
      buff.append (" FROM Pessoas, Cidades ");
      buff.append (" WHERE Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public void importa_Pessoa () throws Exception {
    //EDI_AuxiliarBD
  }

  public void importa_Pessoa_Busca_Cidade () throws Exception {
    //EDI_AuxiliarBD
  }

  public void update () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    try {
      StringBuffer buff = new StringBuffer ();

      buff.append ("UPDATE Pessoas SET OID_Cidade=?, NR_CNPJ_CPF=?, NM_Endereco=?, " +
                   "NM_Bairro=?, NR_CEP=?, NM_Razao_Social=?, NM_Fantasia=?, NM_Inscricao_Estadual=?, " +
                   "Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, NR_Telefone=?, EMail=?, NM_Site=?, nr_fax=?, DM_Valida_Sefaz=? ");
      buff.append ("WHERE OID_Pessoa=?");

      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Cidade ());
      pstmt.setString (2 , getNR_CNPJ_CPF ());
      pstmt.setString (3 , getNM_Endereco ());
      pstmt.setString (4 , getNM_Bairro ());
      pstmt.setString (5 , getNR_CEP ());
      pstmt.setString (6 , getNM_Razao_Social ());
      pstmt.setString (7 , getNM_Fantasia ());
      pstmt.setString (8 , getNM_Inscricao_Estadual ());
      pstmt.setString (9 , getDt_Stamp ());
      pstmt.setString (10 , getUsuario_Stamp ());
      pstmt.setString (11 , getDm_Stamp ());
      pstmt.setString (12 , getNR_Telefone ());
      pstmt.setString (13 , getEMail ());
      pstmt.setString (14 , getNM_Site ());
      pstmt.setString (15 , getNR_Fax ());
      pstmt.setString (16 , (getValue (getNR_CNPJ_CPF ()).length () == 14 && Validacao.CNPJ (getNR_CNPJ_CPF ()) ? "S" : "N"));
      pstmt.setString (17 , getOID ());
      pstmt.executeUpdate ();

    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final PessoaBean getByOID (String oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Endereco do DSN
      // o NM_Endereco de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    PessoaBean p = new PessoaBean ();

    String sqlBusca = " SELECT " +
        "   Pessoas.OID_Pessoa, " +
        "	Pessoas.OID_Cidade, " +
        "	Pessoas.NR_CNPJ_CPF, " +
        "	Pessoas.NM_Razao_Social, " +
        "	Pessoas.NM_Endereco, " +
        "	Pessoas.NM_Bairro, " +
        "	Pessoas.NR_CEP, " +
        "	Pessoas.NM_Fantasia, " +
        "	Pessoas.NM_Inscricao_Estadual, " +
        "	Pessoas.NR_Telefone, " +
        "   Pessoas.OID_Cidade," +
        "   Cidades.CD_Cidade, " +
        "   Cidades.NM_Cidade, " +
        "   Estados.OID_Estado, " +
        "   Estados.CD_Estado, " +
        "   Estados.NM_Estado, " +
        "   Pessoas.EMail, " +
        "   Pessoas.NM_Site, " +
        "   Pessoas.nr_fax " +
        " FROM Pessoas, Cidades, Regioes_Estados, Estados " +
        " WHERE Pessoas.OID_Pessoa= '" + JavaUtil.getStringNotNull (oid , "").trim () + "'" +
        "   and Pessoas.OID_Cidade = Cidades.OID_Cidade " +
        "   and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
        "   and Regioes_Estados.OID_Estado = Estados.OID_Estado";
//System.out.println(sqlBusca);
    Statement stmt = conn.createStatement ();
    ResultSet cursor = stmt.executeQuery (sqlBusca);

    while (cursor.next ()) {
      p.setOID (cursor.getString ("OID_Pessoa"));
      p.setOID_Cidade (cursor.getInt ("OID_Cidade"));
      p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
      p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));
      p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
      p.setNM_Endereco (cursor.getString ("NM_Endereco"));
      p.setNM_Bairro (cursor.getString ("NM_Bairro"));
      p.setNR_CEP (cursor.getString ("NR_CEP"));
      p.setNM_Fantasia (cursor.getString ("NM_Fantasia"));
      if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
        p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
      }

      p.setNM_Inscricao_Estadual (cursor.getString ("NM_Inscricao_Estadual"));
      p.setNR_Telefone (cursor.getString (10));

      p.setOid_Cidade (cursor.getInt ("OID_Cidade"));
      p.setCD_Cidade (cursor.getString ("CD_Cidade"));
      p.setNM_Cidade (cursor.getString ("NM_Cidade"));
      p.setOid_Estado (cursor.getInt ("OID_Estado"));
      p.setCD_Estado (cursor.getString ("CD_Estado"));
      p.setNM_Estado (cursor.getString ("NM_Estado"));

      p.setEMail (cursor.getString ("EMail"));

      p.setNM_Site (cursor.getString ("NM_Site"));
      p.setNR_Fax (cursor.getString ("nr_fax"));
    }
    if (Parametro_FixoED.getInstancia ().getNM_Empresa ().equals ("MEGATRENDS")) {
      sqlBusca = "SELECT CD_Grupo_Pessoa " + "FROM Grupos_Pessoas_Cargas " + "WHERE Grupos_Pessoas_Cargas.OID_Pessoa= '" + oid + "'";

      stmt = conn.createStatement ();
      cursor = stmt.executeQuery (sqlBusca);

      while (cursor.next ()) {
        p.setCD_Grupo_Pessoa (cursor.getString (1));
      }

      if (p.getCD_Grupo_Pessoa () != null && !p.getCD_Grupo_Pessoa ().equals ("9999") && !p.getCD_Grupo_Pessoa ().equals ("")) {
        sqlBusca = " SELECT GrPe.CD_Grupo_Pessoa, GrPe.NM_Grupo_Pessoa " +
            " FROM Antara_Teste.Grupos_Pessoas GrPe, Grupos_Pessoas_Cargas " +
            " WHERE GrPe.CD_GRUPO_PESSOA = Grupos_Pessoas_Cargas.CD_GRUPO_PESSOA " +
            "   AND Grupos_Pessoas_Cargas.OID_Pessoa= '" + oid + "'";

        stmt = conn.createStatement ();
        cursor = stmt.executeQuery (sqlBusca);

        while (cursor.next ()) {
          p.setCD_Grupo_Pessoa (cursor.getString (1));
          p.setNM_Grupo_Pessoa (cursor.getString (2));
        }
      }
      else {
        p.setNM_Grupo_Pessoa ("");
        p.setCD_Grupo_Pessoa ("");
      }
    }

    cursor.close ();
    stmt.close ();
    conn.close ();
    return p;
  }

  ///### MEGA
  public static final List Grupo_Pessoa_Lista (String oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT GrPe.CD_Grupo_Pessoa, GrPe.NM_Grupo_Pessoa ");
      buff.append ("FROM Antara_Teste.Grupos_Pessoas GrPe, Grupos_Pessoas_Cargas ");
      buff.append ("WHERE GrPe.CD_GRUPO_PESSOA = Grupos_Pessoas_Cargas.CD_GRUPO_PESSOA ");
      buff.append ("AND Grupos_Pessoas_Cargas.OID_Pessoa= '");
      buff.append (oid);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setCD_Grupo_Pessoa (cursor.getString (1));
        p.setNM_Grupo_Pessoa (cursor.getString (2));
        Pessoa_Lista.add (p);
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  ///###
  public static final PessoaBean getByOID_Pessoa_Cliente (String oid) throws Exception {

      // System.out.println("getByOID_Pessoa_Cliente");

	  /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Endereco do DSN
      // o NM_Endereco de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    PessoaBean p = new PessoaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	OID_Cidade, ");
      buff.append ("	NR_CNPJ_CPF, ");
      buff.append ("	NM_Razao_Social, ");
      buff.append ("	NM_Endereco, ");
      buff.append ("	NM_Bairro, ");
      buff.append ("	NR_CEP, ");
      buff.append ("	NM_Fantasia, ");
      buff.append ("	NM_Inscricao_Estadual, ");
      buff.append ("	OID_Vendedor ");
      buff.append (" FROM Pessoas, Clientes ");
      buff.append (" WHERE Pessoas.oid_Pessoa = Clientes.OID_Pessoa  ");
      buff.append (" AND Pessoas.OID_Pessoa = '");
      buff.append (oid);
      buff.append ("'");

      // System.out.println(buff.toString());

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setDM_CNPJ_CPF_Valido (verifica_CNPJ_CPF (p.getNR_CNPJ_CPF ()));

        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }

        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setOID_Vendedor (cursor.getString (10));
      }

      buff.delete (0 , buff.length ());

      buff.append ("SELECT CD_Grupo_Pessoa ");
      buff.append ("FROM Grupos_Pessoas_Cargas ");
      buff.append ("WHERE Grupos_Pessoas_Cargas.OID_Pessoa= '");
      buff.append (oid);
      buff.append ("'");

      stmt = conn.createStatement ();
      cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setCD_Grupo_Pessoa (cursor.getString (1));
      }

      if (p.getCD_Grupo_Pessoa () != null && !p.getCD_Grupo_Pessoa ().equals ("9999") && !p.getCD_Grupo_Pessoa ().equals ("")) {
        buff.delete (0 , buff.length ());

        buff.append ("SELECT GrPe.CD_Grupo_Pessoa, GrPe.NM_Grupo_Pessoa ");
        buff.append ("FROM Antara_Teste.Grupos_Pessoas GrPe, Grupos_Pessoas_Cargas ");
        buff.append ("WHERE GrPe.CD_GRUPO_PESSOA = Grupos_Pessoas_Cargas.CD_GRUPO_PESSOA ");
        buff.append ("AND Grupos_Pessoas_Cargas.OID_Pessoa= '");
        buff.append (oid);
        buff.append ("'");

        stmt = conn.createStatement ();
        cursor = stmt.executeQuery (buff.toString ());

        while (cursor.next ()) {
          p.setCD_Grupo_Pessoa (cursor.getString (1));
          p.setNM_Grupo_Pessoa (cursor.getString (2));
        }

      }
      else {
        p.setNM_Grupo_Pessoa ("");
        p.setCD_Grupo_Pessoa ("");
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

  public void compromisso () throws Exception {
    Connection conn = null;
    long NR_Contador = 0;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    try {
      ManipulaArquivo man = new ManipulaArquivo (";");
      BufferedReader buff = man.leArquivo ("C:\\temp\\comp.txt");

      StringTokenizer st = null;
      String a = null;
      long OID_Compromisso = 0;
      while ( (a = buff.readLine ()) != null) {
        st = new StringTokenizer (a , ";");
        while (st.hasMoreTokens ()) {
          NR_Contador = NR_Contador + 1;
          OID_Compromisso = new Long (st.nextToken ()).longValue ();
          String oid_Cidade_Temp = st.nextToken ();
          this.oid_Cidade = new Integer (oid_Cidade_Temp.trim ()).intValue ();

          StringBuffer buffComp = new StringBuffer ();

          buffComp.append ("UPDATE Compromissos SET OID_Conta=? ");
          buffComp.append ("WHERE OID_Compromisso=?");

          PreparedStatement pstmt = conn.prepareStatement (buffComp.toString ());
          pstmt.setInt (1 , oid_Cidade);
          pstmt.setLong (2 , OID_Compromisso);
          pstmt.executeUpdate ();

        }
      }
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final PessoaBean getCEP (String NR_Cep) throws Exception {
    PessoaBean p = new PessoaBean ();

    //*** Valida Parâmetro
     if (!JavaUtil.doValida (NR_Cep)) {
       //return p;
     }
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    try {
      if (JavaUtil.doValida (Parametro_FixoED.getInstancia ().getDM_Valida_CEP ()) &&
          Parametro_FixoED.getInstancia ().getDM_Valida_CEP ().equals ("S")) {
        //String sqlBusca = " SELECT upper(limpa_texto(logradouro)) as logradouro, upper(limpa_texto(bairro)) as bairro, cep from temp_cep " +
        String sqlBusca = "SELECT * FROM temp_cep " +
            " WHERE cep= '" + NR_Cep + "'";

        Statement stmt = conn.createStatement ();
        ResultSet cursor = stmt.executeQuery (sqlBusca);

        while (cursor.next ()) {
          p.setNM_Endereco (cursor.getString ("tipo") + " " + cursor.getString ("logradouro"));
          p.setNM_Bairro (cursor.getString ("Bairro"));
          p.setNR_CEP (cursor.getString ("CEP"));
          p.setNM_Cidade (cursor.getString ("localidade"));
          p.setCD_Estado (cursor.getString ("uf"));
        }
        cursor.close ();
        stmt.close ();
        conn.close ();
      }
      else {
        p.setNM_Endereco ("");
        p.setNM_Bairro ("");
        p.setNR_CEP (NR_Cep);
      }
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }

  public static void main (String args[]) throws Exception {
//        PessoaBean pp = new PessoaBean();
//        pp.importa_Pessoa();
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public String getCD_Pais () {
    return CD_Pais;
  }

  public String getCD_Regiao_Estado () {
    return CD_Regiao_Estado;
  }

  public String getCD_Regiao_Pais () {
    return CD_Regiao_Pais;
  }

  public void setCD_Pais (String CD_Pais) {
    this.CD_Pais = CD_Pais;
  }

  public void setCD_Regiao_Estado (String CD_Regiao_Estado) {
    this.CD_Regiao_Estado = CD_Regiao_Estado;
  }

  public void setCD_Regiao_Pais (String CD_Regiao_Pais) {
    this.CD_Regiao_Pais = CD_Regiao_Pais;
  }

  public void setDM_Fluvial (String DM_Fluvial) {
    this.DM_Fluvial = DM_Fluvial;
  }

  public String getDM_Fluvial () {
    return DM_Fluvial;
  }

  public String getDM_Suframa () {
    return DM_Suframa;
  }

  public String getDM_Tipo_Localizacao () {
    return DM_Tipo_Localizacao;
  }

  public void setDM_Suframa (String DM_Suframa) {
    this.DM_Suframa = DM_Suframa;
  }

  public void setDM_Tipo_Localizacao (String DM_Tipo_Localizacao) {
    this.DM_Tipo_Localizacao = DM_Tipo_Localizacao;
  }

  public void setNM_Codigo_Aereo (String NM_Codigo_Aereo) {
    this.NM_Codigo_Aereo = NM_Codigo_Aereo;
  }

  public String getNM_Codigo_Aereo () {
    return NM_Codigo_Aereo;
  }

  public String getNM_Estado () {
    return NM_Estado;
  }

  public void setNM_Estado (String NM_Estado) {
    this.NM_Estado = NM_Estado;
  }

  public void setNM_Pais (String NM_Pais) {
    this.NM_Pais = NM_Pais;
  }

  public void setNM_Regiao_Estado (String NM_Regiao_Estado) {
    this.NM_Regiao_Estado = NM_Regiao_Estado;
  }

  public void setNM_Regiao_Pais (String NM_Regiao_Pais) {
    this.NM_Regiao_Pais = NM_Regiao_Pais;
  }

  public String getNM_Pais () {
    return NM_Pais;
  }

  public String getNM_Regiao_Estado () {
    return NM_Regiao_Estado;
  }

  public String getNM_Regiao_Pais () {
    return NM_Regiao_Pais;
  }

  public void setOid (String oid) {
    this.oid = oid;
  }

  public String getOid () {
    return oid;
  }

  public int getOid_Cidade () {
    return oid_Cidade;
  }

  public void setOid_Cidade (int oid_Cidade) {
    this.oid_Cidade = oid_Cidade;
  }

  public int getOid_Estado () {
    return oid_Estado;
  }

  public int getOid_Pais () {
    return oid_Pais;
  }

  public int getOid_Regiao_Estado () {
    return oid_Regiao_Estado;
  }

  public int getOid_Regiao_Pais () {
    return oid_Regiao_Pais;
  }

  public void setOid_Estado (int oid_Estado) {
    this.oid_Estado = oid_Estado;
  }

  public void setOid_Pais (int oid_Pais) {
    this.oid_Pais = oid_Pais;
  }

  public void setOid_Regiao_Estado (int oid_Regiao_Estado) {
    this.oid_Regiao_Estado = oid_Regiao_Estado;
  }

  public void setOid_Regiao_Pais (int oid_Regiao_Pais) {
    this.oid_Regiao_Pais = oid_Regiao_Pais;
  }

  public String getCD_Cidade () {
    return CD_Cidade;
  }

  public void setCD_Cidade (String CD_Cidade) {
    this.CD_Cidade = CD_Cidade;
  }

  public void setNR_Telefone (String NR_Telefone) {
    this.NR_Telefone = NR_Telefone;
  }

  public String getNR_Telefone () {
    return NR_Telefone;
  }

  public String getEMail () {
    return eMail;
  }

  public String getDM_CNPJ_CPF_Valido () {
    return DM_CNPJ_CPF_Valido;
  }

  public void setEMail (String mail) {
    eMail = mail;
  }

  public void setDM_CNPJ_CPF_Valido (String DM_CNPJ_CPF_Valido) {
    this.DM_CNPJ_CPF_Valido = DM_CNPJ_CPF_Valido;
  }

  public String getNR_CNPJ_CPFMasc () {
    return JavaUtil.formataCNPJ_CPF (NR_CNPJ_CPF , CD_Pais);
  }

public String getNM_Inscricao_Municipal() {
	return NM_Inscricao_Municipal;
}

public void setNM_Inscricao_Municipal(String inscricao_Municipal) {
	NM_Inscricao_Municipal = inscricao_Municipal;
}

}