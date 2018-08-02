/*
 * Created on 28/09/2004
 */
package com.master.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class BancoUtil
    extends Transacao {

  public BancoUtil () {
    super ();
  }

  public BancoUtil (boolean autoCloseConnection) {
    super ();
    this.autoCloseConnection = autoCloseConnection;
  }

  public BancoUtil (ExecutaSQL executasql) {
    super (executasql);
  }

  public BancoUtil (ExecutaSQL executasql , boolean autoCloseConnection) {
    super (executasql);
    this.autoCloseConnection = autoCloseConnection;
  }

  String query = "";
  boolean autoCloseConnection = true;

  /**
   * Retorna um valor String de um campo especifico de uma tabela
   */
  public String getTableStringValue (String campo , String from , String where) throws Excecoes {

    String objResult = "";
    ResultSet res = null;
    this.inicioTransacao ();
    try {

      query = " select " + campo + " as String_Value from " + from;
      if (!where.equals ("")) {
        query += " where " + where;
      }

      res = this.sql.executarConsulta (query);

      if (res.next ()) {
        objResult = res.getString ("String_Value");
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getTableValue()");
    }
    finally {
      if (autoCloseConnection) {
        this.fimTransacao (false);
      }
      this.closeResultset (res);
    }
    return objResult;
  }

  /**
   * Retorna um valor INT de um campo especifico de uma tabela
   */
  public int getTableIntValue (String campo , String from , String where) throws Excecoes {

    int objResult = 0;
    ResultSet res = null;
    this.inicioTransacao ();
    try {

      query = " select " + campo + " as Int_Value from " + from;
      if (!where.equals ("")) {
        query += " where " + where;
      }

      res = this.sql.executarConsulta (query);

      if (res.next ()) {
        objResult = res.getInt ("Int_Value");
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getTableValue()");
    }
    finally {
      if (autoCloseConnection) {
        this.fimTransacao (false);
      }
      this.closeResultset (res);
    }
    return objResult;
  }

  /**
   * Retorna um valor DOUBLE de um campo especifico de uma tabela
   */
  public double getTableDoubleValue (String campo , String from , String where) throws Excecoes {

    double objResult = 0;
    ResultSet res = null;
    this.inicioTransacao ();
    try {

      query = " select " + campo + " as Double_Value from " + from;
      if (!where.equals ("")) {
        query += " where " + where;
      }

      res = this.sql.executarConsulta (query);

      if (res.next ()) {
        objResult = res.getDouble ("Double_Value");
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getTableValue()");
    }
    finally {
      if (autoCloseConnection) {
        this.fimTransacao (false);
      }
      this.closeResultset (res);
    }
    return objResult;
  }

  /**
   * Retorna o pr�ximo ID dispon�vel
   */
  public int getAutoIncremento (String campo , String from) throws Excecoes {

    return (getAutoIncremento (campo , from , true));

  }

  public int getAutoIncremento (String campo , String from , boolean abreTransacao) throws Excecoes {

    return (getMaximo (campo , from , "" , abreTransacao) + 1);

  }

  /**
   * Retorna o maior valor do campo informado
   */
  public int getMaximo (String campo , String from , String where) throws Excecoes {
    return getMaximo (campo , from , where , true);
  }

  /**
   * Retorna o maior valor do campo informado
   */
  public int getMaximo (String campo , String from , String where , boolean abreTransacao) throws Excecoes {

    int next = -1;
    ResultSet res = null;
    if (abreTransacao) {
      this.inicioTransacao ();
    }
    try {

	  query = " select " + campo + " as count from " + from;

//      query = " select max(" + campo + ") as count from " + from;
      if (!where.equals ("")) {
        query += " where " + where;
      }
	  query += " order by "+ campo + " desc limit 1 ";

      res = this.sql.executarConsulta (query);

      if (res.next ()) {
        next = res.getInt ("count");
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getMaximo()");
    }
    finally {
      if (autoCloseConnection) {
        this.fimTransacao (false);
      }
      this.closeResultset (res);
    }

    return next;
  }

  /**
   * Retorna o maior DATA do campo informado
   */
  public String getMaximoData (String campo , String from , String where) throws Excecoes {

    String next = null;
    ResultSet res = null;
    this.inicioTransacao ();
    try {

      query = " select max(" + campo + ") as max_data from " + from;
      if (!where.equals ("")) {
        query += " where " + where;
      }

      res = this.sql.executarConsulta (query);

      if (res.next ()) {
        next = res.getString ("max_data");
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getMaximoData()");
    }
    finally {
      if (autoCloseConnection) {
        this.fimTransacao (false);
      }
      this.closeResultset (res);
    }
    return next;
  }

  /**
   * Verifica se o registro existe
   */
  public boolean doExiste (String strFrom , String strWhere) throws Excecoes {

    boolean existe = false;
    ResultSet res = null;
    this.inicioTransacao ();
    try {

      query = " select count(*) as count";
      query += " from " + strFrom;
      query += " where 1=1 and " + strWhere;

      res = this.sql.executarConsulta (query);
      if (res.next ()) {
        existe = (res.getInt ("count") > 0);
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "doExiste()");
    }
    finally {
      if (autoCloseConnection) {
        this.fimTransacao (false);
      }
      this.closeResultset (res);
    }
    return existe;
  }

  /**
   * Executa CONSULTA, retornando um ResultSet
   */
  public ResultSet executarConsulta (String query) throws Excecoes {

    try {
      this.inicioTransacao ();
      return this.sql.executarConsulta (query);
    }
    finally {
      if (autoCloseConnection) {
        this.fimTransacao (false);
      }
    }
  }

  /**
   * Executa UPDATE
   */
  public void executarUpdate (String query) throws Excecoes {

    try {
      this.inicioTransacao ();
      this.sql.executarUpdate (query);
      this.fimTransacao (true);
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "executarUpdate()");
    }
  }

  /**
   * Verifica se o par�metro do request � v�lido
   */
  public boolean doValida (String parametro) {
    return JavaUtil.doValida (parametro);
  }

  /**
   * Verifica se o par�metro do request � v�lido [STRING]
   */
  public String getValueDef (String value , String strDefault) {
    return JavaUtil.getValueDef (value , strDefault);
  }

  /**
   * Verifica se o par�metro do request � v�lido [DOUBLE]
   */
  public double getValueDef (String value , double dblDefault) {

    return (doValida (value)) ? Double.parseDouble (value) : dblDefault;
  }

  /**
   * Verifica se o par�metro � v�lido [DOUBLE]
   */
  public double getValueDef (double value , double dblDefault) {

    return JavaUtil.getValueDef (value , dblDefault);
  }

  /**
   * Verifica se o par�metro do request � v�lido [INT]
   */
  public int getValueDef (int value , int intDefault) {
    return JavaUtil.getValueDef (value , intDefault);
  }

  /**
   * Verifica se o par�metro do request � v�lido [INT]
   */
  public int getValueDef (String value , int intDefault) {
    return (doValida (value)) ? Integer.parseInt (value) : intDefault;
  }

  public String getSQLString (String string) {
    return JavaUtil.getSQLString (string);
  }

  public static String getSQLStringLike (String string) {
    return JavaUtil.getSQLStringLike (string);
  }

  public String getSQLStringDef (String string , String defaultValue) {
    return JavaUtil.getSQLStringDef (string , defaultValue);
  }

  /**
   * Verifica se
   * - O ID foi exclu�do por outro usu�rio
   * - J� existe outro ID com o mesmo c�digo
   */
  public void doValidaUpdate (int oid , String cd , String table , String fieldOid , String fieldCd) throws Excecoes {
    String from = table;
    String where = fieldOid + " = " + oid;
    // Valida se o registro em edi��o foi exclu�do por outro usu�rio
    if (doExiste (from , where)) {
      where = fieldOid + " <> " + oid +
          " and " + fieldCd + " = '" + cd + "'";
      // Valida se existe outro registro com o mesmo c�digo
      if (doExiste (from , where)) {
        throw new Mensagens ("J� existe um registro com este c�digo!");
      }
    }
    else {
      throw new Excecoes ("ID informado para atualiza��o n�o existe (pode ter sido " +
                          "exclu�do por outro usu�rio), efetue a consulta novamente!");
    }
  }

  public void closeResultset (ResultSet res) {
    if (res != null) {
      try {
        res.close ();
      }
      catch (SQLException e) {
        e.printStackTrace ();
      }
      try {
        if (res.getStatement () != null) {
          res.getStatement ().close ();
        }
      }
      catch (SQLException e) {
        //No driver jdbc mais novo, o statement � fechado com o fechamento do resultset
      }
    }
  }

  public String getSQLDate (String date) {
    return JavaUtil.getSQLDate (date);
  }
}