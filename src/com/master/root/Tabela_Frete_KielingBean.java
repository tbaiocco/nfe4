package com.master.root;

import java.sql.*;
import java.util.*;

import com.master.util.Data;

import auth.*;

public class Tabela_Frete_KielingBean {

  private String DM_Origem;
  private String NM_Origem;
  private String DM_Destino;
  private String NM_Destino;
  private String DT_Vigencia;
  private String DT_Validade;
  private double VL_C_Acima1000;
  private double VL_C_Taxa_Minima;
  private double PE_Ad_Valorem;
  private double VL_Pedagio_Minimo;
  private double VL_TX_Coleta;
  private double VL_TX_Entrega;
  private double TX_Col_Urg_200;
  private double TX_Col_Urg_1000;
  private double TX_Col_Urg_Ton;
  private double VL_TX_Ent_Urg_200;
  private double VL_TX_Ent_Urg_1000;
  private double VL_TX_Ent_Urg_Ton;
  private double VL_TX_Exc_Coleta;
  private double VL_C_Ate25;
  private double VL_C_Ate50;
  private double VL_C_Ate300;
  private double VL_C_Ate500;
  private double VL_C_Ate1000;
  private double VL_TX_Exc_Entrega;
  private double VL_TX_Pedagio;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private long oid_Produto;
  private long oid_Origem;
  private long oid_Destino;
  private long OID_Modal;
  private String oid_Pessoa;
  private String NM_Razao_Social;
  private String NM_Modal;
  private String NM_Produto;
  private String CD_Produto;
  private String CD_Modal;
  private String OID_Vendedor;
  private double VL_TX_Col_Urg_200;
  private double VL_TX_Col_Urg_1000;
  private double VL_TX_Col_Urg_Ton;
  private double VL_Taxa_Minima;
  private double VL_D_Ate1C;
  private double VL_D_Ate2C;
  private double VL_D_Ate3C;
  private double VL_D_Ate4C;
  private double VL_D_Ate5C;
  private double VL_D_Ate6C;
  private double VL_D_Ate7C;
  private double VL_D_Ate8C;
  private double VL_D_Ate9C;
  private double VL_D_Ate10C;
  private double VL_D_Ate1D;
  private double VL_D_Ate2D;
  private double VL_D_Ate3D;
  private double VL_D_Ate4D;
  private double VL_D_Ate5D;
  private double VL_D_Ate6D;
  private double VL_D_Ate7D;
  private double VL_D_Ate8D;
  private double VL_D_Ate9D;
  private double VL_D_Ate10D;
  private double VL_R_Ate10;
  private double VL_R_Ate20;
  private double VL_R_Ate30;
  private double VL_R_Ate50;
  private double VL_R_Ate70;
  private double VL_R_Ate100;
  private double VL_R_Ate150;
  private double VL_R_Ate200;
  private double VL_R_Acima200;
  private double PE_Ademe;
  private double VL_Ademe_Minimo;
  private double PE_Suframa;
  private double VL_Suframa_Minimo;
  private double PE_Fluvial;
  private double VL_Fluvial_Minimo;
  private double VL_D_ExcedenteC;
  private double VL_D_ExcedenteD;
  private double PE_D_Ad_Valorem;
  private double VL_TX_Km_Rodado;
  private double PE_Desc_FP;
  private double PE_Desc_FV;
  private double VL_E_1Kg;
  private double VL_E_Ate1;
  private double VL_E_Excedente;
  private double PE_E_Ad_Valorem;
  private double PE_C_Ad_Valorem;
  private double VL_C_Pedagio;
  private String DM_Tipo_Tabela_R;
  private String DM_Tipo_Tabela_C;
  private String DM_Tipo_Tabela_D;
  private String DM_Tipo_Tabela_E;
  private String DM_Tipo_Peso;
  private double PE_C_Desc_FP;
  private double PE_D_Desc_FP;
  private double PE_E_Desc_FP;
  private double PE_R_Desc_FP;
  private String DM_Tipo_Pedagio;
  private double VL_R_Ate7500;
  private double VL_R_Ate14500;
  private double VL_R_Acima14500;
  private double PE_Ad_Valorem2;
  private String OID;
  private String oid;
  private double NR_Prazo_Entrega_C;
  private double NR_Prazo_Entrega_D;
  private double NR_Prazo_Entrega_E;
  private double NR_Prazo_Entrega_R;

  public void setOID_Vendedor (String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }

  public String getOID_Vendedor () {
    return OID_Vendedor;
  }

  public String getDM_Origem () {
    return DM_Origem;
  }

  public void setDM_Origem (String DM_Origem) {
    this.DM_Origem = DM_Origem;
  }

  public String getNM_Origem () {
    return NM_Origem;
  }

  public void setNM_Origem (String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }

  public String getDM_Destino () {
    return DM_Destino;
  }

  public void setDM_Destino (String DM_Destino) {
    this.DM_Destino = DM_Destino;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public String getDT_Vigencia () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Vigencia);
    DT_Vigencia = DataFormatada.getDT_FormataData ();
    return DT_Vigencia;
  }

  public void setDT_Vigencia (String DT_Vigencia) {
    this.DT_Vigencia = DT_Vigencia;
  }

  public String getDT_Validade () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Validade);
    DT_Validade = DataFormatada.getDT_FormataData ();
    return DT_Validade;
  }

  public void setDT_Validade (String DT_Validade) {
    this.DT_Validade = DT_Validade;
  }

  public double getVL_C_Acima1000 () {
    return VL_C_Acima1000;
  }

  public void setVL_C_Acima1000 (double VL_C_Acima1000) {
    this.VL_C_Acima1000 = VL_C_Acima1000;
  }

  public double getVL_C_Taxa_Minima () {
    return VL_C_Taxa_Minima;
  }

  public void setVL_C_Taxa_Minima (double VL_C_Taxa_Minima) {
    this.VL_C_Taxa_Minima = VL_C_Taxa_Minima;
  }

  public double getPE_Ad_Valorem () {
    return PE_Ad_Valorem;
  }

  public void setPE_Ad_Valorem (double PE_Ad_Valorem) {
    this.PE_Ad_Valorem = PE_Ad_Valorem;
  }

  public double getVL_TX_Coleta () {
    return VL_TX_Coleta;
  }

  public void setVL_TX_Coleta (double VL_TX_Coleta) {
    this.VL_TX_Coleta = VL_TX_Coleta;
  }

  public double getVL_TX_Entrega () {
    return VL_TX_Entrega;
  }

  public void setVL_TX_Entrega (double VL_TX_Entrega) {
    this.VL_TX_Entrega = VL_TX_Entrega;
  }

  public double getTX_Col_Urg_Ton () {
    return TX_Col_Urg_Ton;
  }

  public void setTX_Col_Urg_Ton (double TX_Col_Urg_Ton) {
    this.TX_Col_Urg_Ton = TX_Col_Urg_Ton;
  }

  public double getTX_Col_Urg_1000 () {
    return TX_Col_Urg_1000;
  }

  public void setTX_Col_Urg_1000 (double TX_Col_Urg_1000) {
    this.TX_Col_Urg_1000 = TX_Col_Urg_1000;
  }

  public double getTX_Col_Urg_200 () {
    return TX_Col_Urg_200;
  }

  public void setTX_Col_Urg_200 (double TX_Col_Urg_200) {
    this.TX_Col_Urg_200 = TX_Col_Urg_200;
  }

  public double getVL_TX_Ent_Urg_200 () {
    return VL_TX_Ent_Urg_200;
  }

  public void setVL_TX_Ent_Urg_200 (double VL_TX_Ent_Urg_200) {
    this.VL_TX_Ent_Urg_200 = VL_TX_Ent_Urg_200;
  }

  public double getVL_TX_Ent_Urg_1000 () {
    return VL_TX_Ent_Urg_1000;
  }

  public void setVL_TX_Ent_Urg_1000 (double VL_TX_Ent_Urg_1000) {
    this.VL_TX_Ent_Urg_1000 = VL_TX_Ent_Urg_1000;
  }

  public double getVL_TX_Ent_Urg_Ton () {
    return VL_TX_Ent_Urg_Ton;
  }

  public void setVL_TX_Ent_Urg_Ton (double VL_TX_Ent_Urg_Ton) {
    this.VL_TX_Ent_Urg_Ton = VL_TX_Ent_Urg_Ton;
  }

  public double getVL_TX_Exc_Coleta () {
    return VL_TX_Exc_Coleta;
  }

  public void setVL_TX_Exc_Coleta (double VL_TX_Exc_Coleta) {
    this.VL_TX_Exc_Coleta = VL_TX_Exc_Coleta;
  }

  public double getVL_C_Ate25 () {
    return VL_C_Ate25;
  }

  public void setVL_C_Ate25 (double VL_C_Ate25) {
    this.VL_C_Ate25 = VL_C_Ate25;
  }

  public double getVL_C_Ate50 () {
    return VL_C_Ate50;
  }

  public void setVL_C_Ate50 (double VL_C_Ate50) {
    this.VL_C_Ate50 = VL_C_Ate50;
  }

  public double getVL_C_Ate1000 () {
    return VL_C_Ate1000;
  }

  public void setVL_C_Ate1000 (double VL_C_Ate1000) {
    this.VL_C_Ate1000 = VL_C_Ate1000;
  }

  public double getVL_C_Ate500 () {
    return VL_C_Ate500;
  }

  public void setVL_C_Ate500 (double VL_C_Ate500) {
    this.VL_C_Ate500 = VL_C_Ate500;
  }

  public double getVL_C_Ate300 () {
    return VL_C_Ate300;
  }

  public void setVL_C_Ate300 (double VL_C_Ate300) {
    this.VL_C_Ate300 = VL_C_Ate300;
  }

  public double getVL_TX_Pedagio () {
    return VL_TX_Pedagio;
  }

  public void setVL_TX_Pedagio (double VL_TX_Pedagio) {
    this.VL_TX_Pedagio = VL_TX_Pedagio;
  }

  public double getVL_TX_Exc_Entrega () {
    return VL_TX_Exc_Entrega;
  }

  public void setVL_TX_Exc_Entrega (double VL_TX_Exc_Entrega) {
    this.VL_TX_Exc_Entrega = VL_TX_Exc_Entrega;
  }

  public long getOID_Produto () {
    return oid_Produto;
  }

  public void setOID_Produto (long n) {
    this.oid_Produto = n;
  }

  public long getOID_Origem () {
    return oid_Origem;
  }

  public void setOID_Origem (long n) {
    this.oid_Origem = n;
  }

  public long getOID_Destino () {
    return oid_Destino;
  }

  public void setOID_Destino (long n) {
    this.oid_Destino = n;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public String getCD_Produto () {
    return CD_Produto;
  }

  public void setCD_Produto (String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Modal (long n) {
    this.OID_Modal = n;
  }

  public String getCD_Modal () {
    return CD_Modal;
  }

  public void setCD_Modal (String CD_Modal) {
    this.CD_Modal = CD_Modal;
  }

  public String getNM_Modal () {
    return NM_Modal;
  }

  public void setNM_Modal (String NM_Modal) {
    this.NM_Modal = NM_Modal;
  }

  public String getOID_Pessoa () {
    return oid_Pessoa;
  }

  public void setOID_Pessoa (String n) {
    this.oid_Pessoa = n;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
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

  public String getOID () {
    return oid;
  }

  public void setOID (String OID) {
    this.oid = OID;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    // System.out.println ("System.currentTimeMillis()" + System.currentTimeMillis ());
    String xx = "1" +
        String.valueOf (System.currentTimeMillis ()).toString ().
        substring (4 , 13);

    // System.out.println ("System.currentTimeMillis() xx" + xx);

    setOID (xx);

    // System.out.println ("System.setOID() xx" + getOID ());

    /*
     * Define o insert.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Tabelas_Fretes (OID_Tabela_Frete, OID_Produto, OID_Modal, OID_Pessoa, OID_Origem, OID_Destino, DM_Origem, NM_Origem, DM_Destino, NM_Destino, DT_Vigencia, DT_Validade, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Tipo_Tabela_C, DM_Tipo_Tabela_D, DM_Tipo_Tabela_R, DM_Tipo_Tabela_E) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , getOID ());
      pstmt.setLong (2 , getOID_Produto ());
      pstmt.setLong (3 , getOID_Modal ());
      pstmt.setString (4 , getOID_Pessoa ());
      pstmt.setLong (5 , getOID_Origem ());
      pstmt.setLong (6 , getOID_Destino ());
      pstmt.setString (7 , getDM_Origem ());
      pstmt.setString (8 , getNM_Origem ());
      pstmt.setString (9 , getDM_Destino ());
      pstmt.setString (10 , getNM_Destino ());
      pstmt.setString (11 , this.DT_Vigencia);
      pstmt.setString (12 , this.DT_Validade);
      pstmt.setString (13 , getDt_Stamp ());
      pstmt.setString (14 , getUsuario_Stamp ());
      pstmt.setString (15 , getDm_Stamp ());
      pstmt.setString (16 , " ");
      pstmt.setString (17 , " ");
      pstmt.setString (18 , " ");
      pstmt.setString (19 , " ");

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
    update_Mov_Cliente(getOID_Pessoa());
  }

  public void update () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
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
    buff.append ("UPDATE Tabelas_Fretes SET DT_Vigencia=?, DT_Validade=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , this.DT_Vigencia);
      pstmt.setString (2 , this.DT_Validade);
      pstmt.setString (3 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_C () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Tabelas_Fretes SET  VL_C_Ate25=?, VL_C_Ate50=?, VL_C_Ate300=?, VL_C_Ate500=?, VL_C_Ate1000=?, VL_C_Acima1000=?, VL_TX_Coleta=?, VL_TX_Entrega=?, VL_TX_Col_Urg_200=?, VL_TX_Col_Urg_1000=?,  VL_TX_Col_Urg_Ton=?,  VL_TX_Ent_Urg_200=?, VL_TX_Ent_Urg_1000=?, VL_TX_Ent_Urg_Ton=?, VL_TX_Exc_Coleta=?, VL_TX_Exc_Entrega=?, VL_C_Pedagio=?, PE_C_Ad_Valorem=?, VL_C_Taxa_Minima=? , VL_TX_Km_Rodado=?, DM_Tipo_Tabela_C=?, NR_Prazo_Entrega_C=?, PE_C_Desc_FP=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getVL_C_Ate25 ());
      pstmt.setDouble (2 , getVL_C_Ate50 ());
      pstmt.setDouble (3 , getVL_C_Ate300 ());
      pstmt.setDouble (4 , getVL_C_Ate500 ());
      pstmt.setDouble (5 , getVL_C_Ate1000 ());
      pstmt.setDouble (6 , getVL_C_Acima1000 ());
      pstmt.setDouble (7 , getVL_TX_Coleta ());
      pstmt.setDouble (8 , getVL_TX_Entrega ());
      pstmt.setDouble (9 , getVL_TX_Col_Urg_200 ());
      pstmt.setDouble (10 , getVL_TX_Col_Urg_1000 ());
      pstmt.setDouble (11 , getVL_TX_Col_Urg_Ton ());
      pstmt.setDouble (12 , getVL_TX_Ent_Urg_200 ());
      pstmt.setDouble (13 , getVL_TX_Ent_Urg_1000 ());
      pstmt.setDouble (14 , getVL_TX_Ent_Urg_Ton ());
      pstmt.setDouble (15 , getVL_TX_Exc_Coleta ());
      pstmt.setDouble (16 , getVL_TX_Exc_Entrega ());
      pstmt.setDouble (17 , getVL_C_Pedagio ());
      pstmt.setDouble (18 , getPE_C_Ad_Valorem ());
      pstmt.setDouble (19 , getVL_C_Taxa_Minima ());
      pstmt.setDouble (20 , getVL_TX_Km_Rodado ());
      pstmt.setString (21 , getDM_Tipo_Tabela_C ());
      pstmt.setDouble (22 , getNR_Prazo_Entrega_C ());
      pstmt.setDouble (23 , getPE_C_Desc_FP ());
      pstmt.setString (24 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_Perc () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Tabelas_Fretes SET  PE_Desc_FP=?, PE_Desc_FV=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getPE_Desc_FP ());
      pstmt.setDouble (2 , getPE_Desc_FV ());
      pstmt.setString (3 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_Rold () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Tabelas_Fretes SET  VL_R_Ate10=?, VL_R_Ate20=?, VL_R_Ate30=?, VL_R_Ate50=?, VL_R_Ate70=?, VL_R_Ate100=?, VL_R_Ate150=?, VL_R_Ate200=?, VL_R_Acima200=?, PE_Ad_Valorem=?,  PE_Ademe=?,  VL_Ademe_Minimo=?, PE_Suframa=?, VL_Suframa_Minimo=?, PE_Fluvial=?, VL_Fluvial_Minimo=?, VL_Pedagio=?,  DM_Tipo_Tabela_R=? , DM_Tipo_Peso=?, PE_R_Desc_FP=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getVL_R_Ate10 ());
      pstmt.setDouble (2 , getVL_R_Ate20 ());
      pstmt.setDouble (3 , getVL_R_Ate30 ());
      pstmt.setDouble (4 , getVL_R_Ate50 ());
      pstmt.setDouble (5 , getVL_R_Ate70 ());
      pstmt.setDouble (6 , getVL_R_Ate100 ());
      pstmt.setDouble (7 , getVL_R_Ate150 ());
      pstmt.setDouble (8 , getVL_R_Ate200 ());
      pstmt.setDouble (9 , getVL_R_Acima200 ());
      pstmt.setDouble (10 , getPE_Ad_Valorem ());
      pstmt.setDouble (11 , getPE_Ademe ());
      pstmt.setDouble (12 , getVL_Ademe_Minimo ());
      pstmt.setDouble (13 , getPE_Suframa ());
      pstmt.setDouble (14 , getVL_Suframa_Minimo ());
      pstmt.setDouble (15 , getPE_Fluvial ());
      pstmt.setDouble (16 , getVL_Fluvial_Minimo ());
      pstmt.setDouble (17 , getVL_TX_Pedagio ());
      pstmt.setString (18 , getDM_Tipo_Tabela_R ());
      pstmt.setString (19 , getDM_Tipo_Peso ());
      pstmt.setDouble (20 , getPE_R_Desc_FP ());
      pstmt.setString (21 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_R () throws Exception {
    /*
     * Abre a conexão com o banco
     */

    // System.out.println ("update");
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Tabelas_Fretes SET  VL_R_Ate10=?, VL_R_Ate20=?, VL_R_Ate30=?, VL_R_Ate50=?, VL_R_Ate70=?, VL_R_Ate100=?, VL_R_Ate150=?, VL_R_Ate200=?, VL_R_Acima200=?, PE_Ad_Valorem=?,  PE_Ademe=?,  VL_Ademe_Minimo=?, PE_Suframa=?, VL_Suframa_Minimo=?, PE_Fluvial=?, VL_Fluvial_Minimo=?, VL_Pedagio=?,  DM_Tipo_Tabela_R=? , DM_Tipo_Peso=?, PE_R_Desc_FP=?, DM_Tipo_Pedagio=?, VL_R_ATE7500=? , VL_R_ATE14500=? , VL_R_Acima14500=?, PE_Ad_Valorem2=?, NR_Prazo_Entrega_R=? , VL_Pedagio_Minimo=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getVL_R_Ate10 ());
      pstmt.setDouble (2 , getVL_R_Ate20 ());
      pstmt.setDouble (3 , getVL_R_Ate30 ());
      pstmt.setDouble (4 , getVL_R_Ate50 ());
      pstmt.setDouble (5 , getVL_R_Ate70 ());
      pstmt.setDouble (6 , getVL_R_Ate100 ());
      pstmt.setDouble (7 , getVL_R_Ate150 ());
      pstmt.setDouble (8 , getVL_R_Ate200 ());
      pstmt.setDouble (9 , getVL_R_Acima200 ());
      pstmt.setDouble (10 , getPE_Ad_Valorem ());
      pstmt.setDouble (11 , getPE_Ademe ());
      pstmt.setDouble (12 , getVL_Ademe_Minimo ());
      pstmt.setDouble (13 , getPE_Suframa ());
      pstmt.setDouble (14 , getVL_Suframa_Minimo ());
      pstmt.setDouble (15 , getPE_Fluvial ());
      pstmt.setDouble (16 , getVL_Fluvial_Minimo ());
      pstmt.setDouble (17 , getVL_TX_Pedagio ());
      pstmt.setString (18 , getDM_Tipo_Tabela_R ());
      pstmt.setString (19 , "1");
      pstmt.setDouble (20 , getPE_R_Desc_FP ());
      pstmt.setString (21 , getDM_Tipo_Pedagio ());
      pstmt.setDouble (22 , getVL_R_Ate7500 ());
      pstmt.setDouble (23 , getVL_R_Ate14500 ());
      pstmt.setDouble (24 , getVL_R_Acima14500 ());
      pstmt.setDouble (25 , getPE_Ad_Valorem2 ());
      pstmt.setDouble (26 , getNR_Prazo_Entrega_R ());
      pstmt.setDouble (27 , getVL_Pedagio_Minimo());
      pstmt.setString (28 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_D () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Tabelas_Fretes SET  VL_D_Ate1C=?, VL_D_Ate2C=?, VL_D_Ate3C=?, VL_D_Ate4C=?, VL_D_Ate5C=?, VL_D_Ate6C=?, VL_D_Ate7C=?, VL_D_Ate8C=?, VL_D_Ate9C=?, VL_D_Ate10C=?, VL_D_Ate1D=?, VL_D_Ate2D=?, VL_D_Ate3D=?, VL_D_Ate4D=?, VL_D_Ate5D=?, VL_D_Ate6D=?, VL_D_Ate7D=?, VL_D_Ate8D=?, VL_D_Ate9D=?, VL_D_Ate10D=?, VL_D_ExcedenteC=?, VL_D_ExcedenteD=?, PE_D_Ad_Valorem=? , DM_Tipo_Tabela_D=?, PE_D_Desc_FP=?, NR_Prazo_Entrega_D=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getVL_D_Ate1C ());
      pstmt.setDouble (2 , getVL_D_Ate2C ());
      pstmt.setDouble (3 , getVL_D_Ate3C ());
      pstmt.setDouble (4 , getVL_D_Ate4C ());
      pstmt.setDouble (5 , getVL_D_Ate5C ());
      pstmt.setDouble (6 , getVL_D_Ate6C ());
      pstmt.setDouble (7 , getVL_D_Ate7C ());
      pstmt.setDouble (8 , getVL_D_Ate8C ());
      pstmt.setDouble (9 , getVL_D_Ate9C ());
      pstmt.setDouble (10 , getVL_D_Ate10C ());
      pstmt.setDouble (11 , getVL_D_Ate1D ());
      pstmt.setDouble (12 , getVL_D_Ate2D ());
      pstmt.setDouble (13 , getVL_D_Ate3D ());
      pstmt.setDouble (14 , getVL_D_Ate4D ());
      pstmt.setDouble (15 , getVL_D_Ate5D ());
      pstmt.setDouble (16 , getVL_D_Ate6D ());
      pstmt.setDouble (17 , getVL_D_Ate7D ());
      pstmt.setDouble (18 , getVL_D_Ate8D ());
      pstmt.setDouble (19 , getVL_D_Ate9D ());
      pstmt.setDouble (20 , getVL_D_Ate10D ());

      pstmt.setDouble (21 , getVL_D_ExcedenteC ());
      pstmt.setDouble (22 , getVL_D_ExcedenteD ());
      pstmt.setDouble (23 , getPE_D_Ad_Valorem ());
      pstmt.setString (24 , getDM_Tipo_Tabela_D ());
      pstmt.setDouble (25 , getPE_D_Desc_FP ());
      pstmt.setDouble (26 , getNR_Prazo_Entrega_D ());
      pstmt.setString (27 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_E () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Tabelas_Fretes SET  VL_E_Ate1=?, VL_E_1Kg=?, VL_E_Excedente=?, PE_E_Ad_Valorem=?, DM_Tipo_Tabela_E=?, PE_E_Desc_FP=?, NR_Prazo_Entrega_E=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getVL_E_Ate1 ());
      pstmt.setDouble (2 , getVL_E_1Kg ());
      pstmt.setDouble (3 , getVL_E_Excedente ());
      pstmt.setDouble (4 , getPE_E_Ad_Valorem ());
      pstmt.setString (5 , getDM_Tipo_Tabela_E ());
      pstmt.setDouble (6 , getPE_E_Desc_FP ());
      pstmt.setDouble (7 , getNR_Prazo_Entrega_E ());
      pstmt.setString (8 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_Desconto () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Tabelas_Fretes SET  PE_C_Desc_FP=?, PE_D_Desc_FP=?, PE_E_Desc_FP=?, PE_R_Desc_FP=? ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getPE_C_Desc_FP ());
      pstmt.setDouble (2 , getPE_D_Desc_FP ());
      pstmt.setDouble (3 , getPE_E_Desc_FP ());
      pstmt.setDouble (4 , getPE_R_Desc_FP ());
      pstmt.setString (5 , getOID ());
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
    //System.out.println("TABELA: " + new Long(getOID()).longValue());
    String cliente = getByOID(new Long(getOID()).longValue()).getOID_Pessoa();
    //System.out.println("PESSOA: " + cliente);
    update_Mov_Cliente(cliente);
  }

  public void update_Mov_Cliente(String oid_Cliente) throws Exception {
	    /*
	     * Abre a conexão com o banco
	     */
	    Connection conn = null;
	    try {
	      // Pede uma conexão ao gerenciador do driver
	      // passando como parâmetro o NM_Tabela_Frete do DSN
	      // o NM_Tabela_Frete de usuário e a senha do banco.
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }
	    StringBuffer buff = new StringBuffer ();
	    buff.append ("UPDATE clientes SET  DT_Ultimo_Movimento='" + Data.getDataDMY() + "'");
	    buff.append (" WHERE oid_cliente='" + oid_Cliente + "'");
	    try {
	    	//System.out.println(buff.toString());
	      PreparedStatement pstmt =
	          conn.prepareStatement (buff.toString ());

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
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Tabelas_Fretes ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
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

  public static final Tabela_Frete_KielingBean getByOID (long oid) throws
      Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tabela_Frete_KielingBean p = new Tabela_Frete_KielingBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tabela_Frete, Tabelas_Fretes.OID_Produto, Tabelas_Fretes.OID_Modal, Tabelas_Fretes.OID_Pessoa, Pessoas.NM_Razao_Social, Produtos.CD_Produto, Produtos.NM_Produto, Modal.CD_Modal, Modal.NM_Modal, OID_Origem, OID_Destino , NM_Origem, NM_Destino , DM_Origem, DM_Destino,  DT_Vigencia, DT_Validade, ");
      buff.append (" VL_C_Ate25, VL_C_Ate50, VL_C_Ate300, VL_C_Ate500, VL_C_Ate1000, VL_C_Acima1000, VL_TX_Coleta, VL_TX_Entrega, VL_TX_Col_Urg_200, VL_TX_Col_Urg_1000,  VL_TX_Col_Urg_Ton,  VL_TX_Ent_Urg_200, VL_TX_Ent_Urg_1000, VL_TX_Ent_Urg_Ton, VL_TX_Exc_Coleta, VL_TX_Exc_Entrega, VL_Pedagio, PE_Ad_Valorem, VL_C_Taxa_Minima,    ");
      buff.append (" VL_D_Ate1C, VL_D_Ate2C, VL_D_Ate3C, VL_D_Ate4C, VL_D_Ate5C, VL_D_Ate6C, VL_D_Ate7C, VL_D_Ate8C, VL_D_Ate9C, VL_D_Ate10C, VL_D_Ate1D, VL_D_Ate2D, VL_D_Ate3D, VL_D_Ate4D, VL_D_Ate5D, VL_D_Ate6D, VL_D_Ate7D, VL_D_Ate8D, VL_D_Ate9D, VL_D_Ate10D,  ");
      buff.append (" VL_R_Ate10, VL_R_Ate20, VL_R_Ate30, VL_R_Ate50, VL_R_Ate70, VL_R_Ate100, VL_R_Ate150, VL_R_Ate200, VL_R_Acima200, PE_Ademe, VL_Ademe_Minimo, PE_Suframa, VL_Suframa_Minimo, PE_Fluvial, VL_Fluvial_Minimo, PE_D_Ad_Valorem, VL_D_ExcedenteC, VL_D_ExcedenteD, VL_TX_Km_Rodado, PE_Desc_FP, PE_Desc_FV, VL_E_ATE1, VL_E_Excedente, PE_E_AD_Valorem, VL_C_Pedagio, PE_C_Ad_Valorem, DM_Tipo_Tabela_C, DM_Tipo_Tabela_D, DM_Tipo_Tabela_E, DM_Tipo_Tabela_R , DM_Tipo_Peso, PE_C_Desc_FP, PE_D_Desc_FP, PE_E_Desc_FP, PE_R_Desc_FP, DM_Tipo_Pedagio, ");
      buff.append (" VL_R_Ate7500, VL_R_Ate14500, VL_R_Acima14500, PE_AD_VALOREM2, VL_E_1Kg, NR_Prazo_Entrega_C, NR_Prazo_Entrega_D, NR_Prazo_Entrega_E, NR_Prazo_Entrega_R, VL_Pedagio_Minimo ");
      buff.append (" FROM Tabelas_Fretes, Pessoas, Modal, Produtos ");
      buff.append ("WHERE Tabelas_Fretes.OID_Produto = Produtos.OID_Produto AND Tabelas_Fretes.OID_Modal = Modal.OID_Modal AND Tabelas_Fretes.OID_Pessoa = Pessoas.OID_Pessoa  ");
      buff.append (" AND Tabelas_Fretes.OID_Tabela_Frete =");
      buff.append (oid);
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getString (1));
        p.setOID_Produto (cursor.getLong (2));
        p.setOID_Modal (cursor.getLong (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setNM_Razao_Social (cursor.getString (5));
        p.setCD_Produto (cursor.getString (6));
        p.setNM_Produto (cursor.getString (7));
        p.setCD_Modal (cursor.getString (8));
        p.setNM_Modal (cursor.getString (9));
        p.setOID_Origem (cursor.getLong (10));
        p.setOID_Destino (cursor.getLong (11));
        p.setNM_Origem (cursor.getString (12));
        p.setNM_Destino (cursor.getString (13));
        p.setDM_Origem (cursor.getString (14));
        p.setDM_Destino (cursor.getString (15));
        p.setDT_Vigencia (cursor.getString (16));
        p.setDT_Validade (cursor.getString (17));

        p.setVL_C_Ate25 (cursor.getDouble (18));
        p.setVL_C_Ate50 (cursor.getDouble (19));
        p.setVL_C_Ate300 (cursor.getDouble (20));
        p.setVL_C_Ate500 (cursor.getDouble (21));
        p.setVL_C_Ate1000 (cursor.getDouble (22));
        p.setVL_C_Acima1000 (cursor.getDouble (23));
        p.setVL_TX_Coleta (cursor.getDouble (24));
        p.setVL_TX_Entrega (cursor.getDouble (25));
        p.setVL_TX_Col_Urg_200 (cursor.getDouble (26));
        p.setVL_TX_Col_Urg_1000 (cursor.getDouble (27));
        p.setVL_TX_Col_Urg_Ton (cursor.getDouble (28));
        p.setVL_TX_Ent_Urg_200 (cursor.getDouble (29));
        p.setVL_TX_Ent_Urg_1000 (cursor.getDouble (30));
        p.setVL_TX_Ent_Urg_Ton (cursor.getDouble (31));
        p.setVL_TX_Exc_Coleta (cursor.getDouble (32));
        p.setVL_TX_Exc_Entrega (cursor.getDouble (33));
        p.setVL_TX_Pedagio (cursor.getDouble (34));
        p.setPE_Ad_Valorem (cursor.getDouble (35));
        p.setVL_C_Taxa_Minima (cursor.getDouble (36));

        p.setVL_D_Ate1C (cursor.getDouble (37));
        p.setVL_D_Ate2C (cursor.getDouble (38));
        p.setVL_D_Ate3C (cursor.getDouble (39));
        p.setVL_D_Ate4C (cursor.getDouble (40));
        p.setVL_D_Ate5C (cursor.getDouble (41));
        p.setVL_D_Ate6C (cursor.getDouble (42));
        p.setVL_D_Ate7C (cursor.getDouble (43));
        p.setVL_D_Ate8C (cursor.getDouble (44));
        p.setVL_D_Ate9C (cursor.getDouble (45));
        p.setVL_D_Ate10C (cursor.getDouble (46));
        p.setVL_D_Ate1D (cursor.getDouble (47));
        p.setVL_D_Ate2D (cursor.getDouble (48));
        p.setVL_D_Ate3D (cursor.getDouble (49));
        p.setVL_D_Ate4D (cursor.getDouble (50));
        p.setVL_D_Ate5D (cursor.getDouble (51));
        p.setVL_D_Ate6D (cursor.getDouble (52));
        p.setVL_D_Ate7D (cursor.getDouble (53));
        p.setVL_D_Ate8D (cursor.getDouble (54));
        p.setVL_D_Ate9D (cursor.getDouble (55));
        p.setVL_D_Ate10D (cursor.getDouble (56));

        p.setVL_R_Ate10 (cursor.getDouble (57));
        p.setVL_R_Ate20 (cursor.getDouble (58));
        p.setVL_R_Ate30 (cursor.getDouble (59));
        p.setVL_R_Ate50 (cursor.getDouble (60));
        p.setVL_R_Ate70 (cursor.getDouble (61));
        p.setVL_R_Ate100 (cursor.getDouble (62));
        p.setVL_R_Ate150 (cursor.getDouble (63));
        p.setVL_R_Ate200 (cursor.getDouble (64));
        p.setVL_R_Acima200 (cursor.getDouble (65));
        p.setPE_Ademe (cursor.getDouble (66));
        p.setVL_Ademe_Minimo (cursor.getDouble (67));
        p.setPE_Suframa (cursor.getDouble (68));
        p.setVL_Suframa_Minimo (cursor.getDouble (69));
        p.setPE_Fluvial (cursor.getDouble (70));
        p.setVL_Fluvial_Minimo (cursor.getDouble (71));
        p.setPE_D_Ad_Valorem (cursor.getDouble (72));
        p.setVL_D_ExcedenteC (cursor.getDouble (73));
        p.setVL_D_ExcedenteD (cursor.getDouble (74));
        p.setVL_TX_Km_Rodado (cursor.getDouble (75));
        p.setPE_Desc_FP (cursor.getDouble (76));
        p.setPE_Desc_FV (cursor.getDouble (77));

        p.setVL_E_Ate1 (cursor.getDouble (78));
        p.setVL_E_Excedente (cursor.getDouble (79));
        p.setPE_E_Ad_Valorem (cursor.getDouble (80));
        p.setVL_C_Pedagio (cursor.getDouble (81));
        p.setPE_C_Ad_Valorem (cursor.getDouble (82));

        p.setDM_Tipo_Tabela_C (cursor.getString (83));
        p.setDM_Tipo_Tabela_D (cursor.getString (84));
        p.setDM_Tipo_Tabela_E (cursor.getString (85));
        p.setDM_Tipo_Tabela_R (cursor.getString (86));
        p.setDM_Tipo_Peso (cursor.getString (87));

        p.setPE_C_Desc_FP (cursor.getDouble (88));
        p.setPE_D_Desc_FP (cursor.getDouble (89));
        p.setPE_E_Desc_FP (cursor.getDouble (90));
        p.setPE_R_Desc_FP (cursor.getDouble (91));
        p.setDM_Tipo_Pedagio (cursor.getString (92));

        p.setVL_R_Ate7500 (cursor.getDouble (93));
        p.setVL_R_Ate14500 (cursor.getDouble (94));
        p.setVL_R_Acima14500 (cursor.getDouble (95));
        p.setPE_Ad_Valorem2 (cursor.getDouble (96));
        p.setVL_E_1Kg (cursor.getDouble (97));
        p.setNR_Prazo_Entrega_C (cursor.getDouble (98));
        p.setNR_Prazo_Entrega_D (cursor.getDouble (99));
        p.setNR_Prazo_Entrega_E (cursor.getDouble (100));
        p.setNR_Prazo_Entrega_R (cursor.getDouble (101));
        p.setVL_Pedagio_Minimo (cursor.getDouble (102));
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

    List Tabela_Fretees_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Tabelas_Fretes.OID_Tabela_Frete, Tabelas_Fretes.OID_Produto, Tabelas_Fretes.OID_Modal, Tabelas_Fretes.OID_Pessoa, Pessoas.NM_Razao_Social, Produtos.CD_Produto, Produtos.NM_Produto, Modal.CD_Modal, Modal.NM_Modal, Tabelas_Fretes.OID_Origem, Tabelas_Fretes.OID_Destino , Tabelas_Fretes.NM_Origem, Tabelas_Fretes.NM_Destino, DM_Tipo_Tabela_C, DM_Tipo_Tabela_D, DM_Tipo_Tabela_E, DM_Tipo_Tabela_R ");
      buff.append ("FROM Tabelas_Fretes, Pessoas, Modal, Produtos ");
      buff.append ("WHERE Tabelas_Fretes.OID_Produto = Produtos.OID_Produto AND Tabelas_Fretes.OID_Modal = Modal.OID_Modal AND Tabelas_Fretes.OID_Pessoa = Pessoas.OID_Pessoa  ");
      buff.append (" ORDER BY NM_Razao_Social, NM_ORIGEM, NM_DESTINO");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tabela_Frete_KielingBean p = new Tabela_Frete_KielingBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Produto (cursor.getLong (2));
        p.setOID_Modal (cursor.getLong (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setNM_Razao_Social (cursor.getString (5));
        p.setCD_Produto (cursor.getString (6));
        p.setNM_Produto (cursor.getString (7));
        p.setCD_Modal (cursor.getString (8));
        p.setNM_Modal (cursor.getString (9));
        p.setOID_Origem (cursor.getLong (10));
        p.setOID_Destino (cursor.getLong (11));
        p.setNM_Origem (cursor.getString (12));
        p.setNM_Destino (cursor.getString (13));
        p.setDM_Tipo_Tabela_C (cursor.getString (14));
        p.setDM_Tipo_Tabela_D (cursor.getString (15));
        p.setDM_Tipo_Tabela_E (cursor.getString (16));
        p.setDM_Tipo_Tabela_R (cursor.getString (17));

        Tabela_Fretees_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tabela_Fretees_Lista;
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF) throws
      Exception {
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

    List Tabela_Fretees_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Tabelas_Fretes.OID_Tabela_Frete, Tabelas_Fretes.OID_Produto, Tabelas_Fretes.OID_Modal, Tabelas_Fretes.OID_Pessoa, Pessoas.NM_Razao_Social, Produtos.CD_Produto, Produtos.NM_Produto, Modal.CD_Modal, Modal.NM_Modal, Tabelas_Fretes.OID_Origem, Tabelas_Fretes.OID_Destino , Tabelas_Fretes.NM_Origem, Tabelas_Fretes.NM_Destino, DM_Tipo_Tabela_C, DM_Tipo_Tabela_D, DM_Tipo_Tabela_E, DM_Tipo_Tabela_R ");
      buff.append ("FROM Tabelas_Fretes, Pessoas, Modal, Produtos ");
      buff.append ("WHERE Tabelas_Fretes.OID_Produto = Produtos.OID_Produto AND Tabelas_Fretes.OID_Modal = Modal.OID_Modal AND Tabelas_Fretes.OID_Pessoa = Pessoas.OID_Pessoa  ");
      buff.append (" AND Tabelas_Fretes.OID_Pessoa ='");
      buff.append (NR_CNPJ_CPF);
      buff.append ("' ORDER BY  NM_ORIGEM, NM_DESTINO");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());
      //// System.out.println("tabela 1 " + NR_CNPJ_CPF );

      while (cursor.next ()) {
        //// System.out.println("tabela 2" + NR_CNPJ_CPF );
        Tabela_Frete_KielingBean p = new Tabela_Frete_KielingBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Produto (cursor.getLong (2));
        p.setOID_Modal (cursor.getLong (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setNM_Razao_Social (cursor.getString (5));
        p.setCD_Produto (cursor.getString (6));
        p.setNM_Produto (cursor.getString (7));
        p.setCD_Modal (cursor.getString (8));
        p.setNM_Modal (cursor.getString (9));
        p.setOID_Origem (cursor.getLong (10));
        p.setOID_Destino (cursor.getLong (11));
        p.setNM_Origem (cursor.getString (12));
        p.setNM_Destino (cursor.getString (13));
        p.setDM_Tipo_Tabela_C (cursor.getString (14));
        p.setDM_Tipo_Tabela_D (cursor.getString (15));
        p.setDM_Tipo_Tabela_E (cursor.getString (16));
        p.setDM_Tipo_Tabela_R (cursor.getString (17));
        Tabela_Fretees_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tabela_Fretees_Lista;
  }

  public static void main (String args[]) throws Exception {
    Tabela_Frete_KielingBean pp = new Tabela_Frete_KielingBean ();
    pp.setOID_Pessoa ("11111111111");
    pp.setOID_Modal (3);
    pp.setOID_Produto (1);
    pp.insert ();

    //Tabela_Frete_KielingBean p = getByOID(1);
    //System.out.prdoubleln(p.getOID());

  }

  public void setVL_TX_Col_Urg_200 (double VL_TX_Col_Urg_200) {
    this.VL_TX_Col_Urg_200 = VL_TX_Col_Urg_200;
  }

  public double getVL_TX_Col_Urg_200 () {
    return VL_TX_Col_Urg_200;
  }

  public void setVL_TX_Col_Urg_1000 (double VL_TX_Col_Urg_1000) {
    this.VL_TX_Col_Urg_1000 = VL_TX_Col_Urg_1000;
  }

  public double getVL_TX_Col_Urg_1000 () {
    return VL_TX_Col_Urg_1000;
  }

  public void setVL_TX_Col_Urg_Ton (double VL_TX_Col_Urg_Ton) {
    this.VL_TX_Col_Urg_Ton = VL_TX_Col_Urg_Ton;
  }

  public double getVL_TX_Col_Urg_Ton () {
    return VL_TX_Col_Urg_Ton;
  }

  public void setVL_Taxa_Minima (double VL_Taxa_Minima) {
    this.VL_Taxa_Minima = VL_Taxa_Minima;
  }

  public double getVL_Taxa_Minima () {
    return VL_Taxa_Minima;
  }

  public void setVL_D_Ate1C (double VL_D_Ate1C) {
    this.VL_D_Ate1C = VL_D_Ate1C;
  }

  public double getVL_D_Ate1C () {
    return VL_D_Ate1C;
  }

  public void setVL_D_Ate2C (double VL_D_Ate2C) {
    this.VL_D_Ate2C = VL_D_Ate2C;
  }

  public double getVL_D_Ate2C () {
    return VL_D_Ate2C;
  }

  public void setVL_D_Ate3C (double VL_D_Ate3C) {
    this.VL_D_Ate3C = VL_D_Ate3C;
  }

  public double getVL_D_Ate3C () {
    return VL_D_Ate3C;
  }

  public void setVL_D_Ate4C (double VL_D_Ate4C) {
    this.VL_D_Ate4C = VL_D_Ate4C;
  }

  public double getVL_D_Ate4C () {
    return VL_D_Ate4C;
  }

  public void setVL_D_Ate5C (double VL_D_Ate5C) {
    this.VL_D_Ate5C = VL_D_Ate5C;
  }

  public double getVL_D_Ate5C () {
    return VL_D_Ate5C;
  }

  public void setVL_D_Ate6C (double VL_D_Ate6C) {
    this.VL_D_Ate6C = VL_D_Ate6C;
  }

  public double getVL_D_Ate6C () {
    return VL_D_Ate6C;
  }

  public void setVL_D_Ate7C (double VL_D_Ate7C) {
    this.VL_D_Ate7C = VL_D_Ate7C;
  }

  public double getVL_D_Ate7C () {
    return VL_D_Ate7C;
  }

  public void setVL_D_Ate8C (double VL_D_Ate8C) {
    this.VL_D_Ate8C = VL_D_Ate8C;
  }

  public double getVL_D_Ate8C () {
    return VL_D_Ate8C;
  }

  public void setVL_D_Ate9C (double VL_D_Ate9C) {
    this.VL_D_Ate9C = VL_D_Ate9C;
  }

  public double getVL_D_Ate9C () {
    return VL_D_Ate9C;
  }

  public void setVL_D_Ate10C (double VL_D_Ate10C) {
    this.VL_D_Ate10C = VL_D_Ate10C;
  }

  public double getVL_D_Ate10C () {
    return VL_D_Ate10C;
  }

  public void setVL_D_Ate1D (double VL_D_Ate1D) {
    this.VL_D_Ate1D = VL_D_Ate1D;
  }

  public double getVL_D_Ate1D () {
    return VL_D_Ate1D;
  }

  public void setVL_D_Ate2D (double VL_D_Ate2D) {
    this.VL_D_Ate2D = VL_D_Ate2D;
  }

  public double getVL_D_Ate2D () {
    return VL_D_Ate2D;
  }

  public void setVL_D_Ate3D (double VL_D_Ate3D) {
    this.VL_D_Ate3D = VL_D_Ate3D;
  }

  public double getVL_D_Ate3D () {
    return VL_D_Ate3D;
  }

  public void setVL_D_Ate4D (double VL_D_Ate4D) {
    this.VL_D_Ate4D = VL_D_Ate4D;
  }

  public double getVL_D_Ate4D () {
    return VL_D_Ate4D;
  }

  public void setVL_D_Ate5D (double VL_D_Ate5D) {
    this.VL_D_Ate5D = VL_D_Ate5D;
  }

  public double getVL_D_Ate5D () {
    return VL_D_Ate5D;
  }

  public void setVL_D_Ate6D (double VL_D_Ate6D) {
    this.VL_D_Ate6D = VL_D_Ate6D;
  }

  public double getVL_D_Ate6D () {
    return VL_D_Ate6D;
  }

  public void setVL_D_Ate7D (double VL_D_Ate7D) {
    this.VL_D_Ate7D = VL_D_Ate7D;
  }

  public double getVL_D_Ate7D () {
    return VL_D_Ate7D;
  }

  public void setVL_D_Ate8D (double VL_D_Ate8D) {
    this.VL_D_Ate8D = VL_D_Ate8D;
  }

  public double getVL_D_Ate8D () {
    return VL_D_Ate8D;
  }

  public void setVL_D_Ate9D (double VL_D_Ate9D) {
    this.VL_D_Ate9D = VL_D_Ate9D;
  }

  public double getVL_D_Ate9D () {
    return VL_D_Ate9D;
  }

  public void setVL_D_Ate10D (double VL_D_Ate10D) {
    this.VL_D_Ate10D = VL_D_Ate10D;
  }

  public double getVL_D_Ate10D () {
    return VL_D_Ate10D;
  }

  public void setVL_R_Ate10 (double VL_R_Ate10) {
    this.VL_R_Ate10 = VL_R_Ate10;
  }

  public double getVL_R_Ate10 () {
    return VL_R_Ate10;
  }

  public void setVL_R_Ate20 (double VL_R_Ate20) {
    this.VL_R_Ate20 = VL_R_Ate20;
  }

  public double getVL_R_Ate20 () {
    return VL_R_Ate20;
  }

  public void setVL_R_Ate30 (double VL_R_Ate30) {
    this.VL_R_Ate30 = VL_R_Ate30;
  }

  public double getVL_R_Ate30 () {
    return VL_R_Ate30;
  }

  public void setVL_R_Ate50 (double VL_R_Ate50) {
    this.VL_R_Ate50 = VL_R_Ate50;
  }

  public double getVL_R_Ate50 () {
    return VL_R_Ate50;
  }

  public void setVL_R_Ate70 (double VL_R_Ate70) {
    this.VL_R_Ate70 = VL_R_Ate70;
  }

  public double getVL_R_Ate70 () {
    return VL_R_Ate70;
  }

  public void setVL_R_Ate100 (double VL_R_Ate100) {
    this.VL_R_Ate100 = VL_R_Ate100;
  }

  public double getVL_R_Ate100 () {
    return VL_R_Ate100;
  }

  public void setVL_R_Ate150 (double VL_R_Ate150) {
    this.VL_R_Ate150 = VL_R_Ate150;
  }

  public double getVL_R_Ate150 () {
    return VL_R_Ate150;
  }

  public void setVL_R_Ate200 (double VL_R_Ate200) {
    this.VL_R_Ate200 = VL_R_Ate200;
  }

  public double getVL_R_Ate200 () {
    return VL_R_Ate200;
  }

  public void setVL_R_Acima200 (double VL_R_Acima200) {
    this.VL_R_Acima200 = VL_R_Acima200;
  }

  public double getVL_R_Acima200 () {
    return VL_R_Acima200;
  }

  public void setPE_Ademe (double PE_Ademe) {
    this.PE_Ademe = PE_Ademe;
  }

  public double getPE_Ademe () {
    return PE_Ademe;
  }

  public void setVL_Ademe_Minimo (double VL_Ademe_Minimo) {
    this.VL_Ademe_Minimo = VL_Ademe_Minimo;
  }

  public double getVL_Ademe_Minimo () {
    return VL_Ademe_Minimo;
  }

  public void setPE_Suframa (double PE_Suframa) {
    this.PE_Suframa = PE_Suframa;
  }

  public double getPE_Suframa () {
    return PE_Suframa;
  }

  public void setVL_Suframa_Minimo (double VL_Suframa_Minimo) {
    this.VL_Suframa_Minimo = VL_Suframa_Minimo;
  }

  public double getVL_Suframa_Minimo () {
    return VL_Suframa_Minimo;
  }

  public void setPE_Fluvial (double PE_Fluvial) {
    this.PE_Fluvial = PE_Fluvial;
  }

  public double getPE_Fluvial () {
    return PE_Fluvial;
  }

  public void setVL_Fluvial_Minimo (double VL_Fluvial_Minimo) {
    this.VL_Fluvial_Minimo = VL_Fluvial_Minimo;
  }

  public double getVL_Fluvial_Minimo () {
    return VL_Fluvial_Minimo;
  }

  public void setVL_D_ExcedenteC (double VL_D_ExcedenteC) {
    this.VL_D_ExcedenteC = VL_D_ExcedenteC;
  }

  public double getVL_D_ExcedenteC () {
    return VL_D_ExcedenteC;
  }

  public void setVL_D_ExcedenteD (double VL_D_ExcedenteD) {
    this.VL_D_ExcedenteD = VL_D_ExcedenteD;
  }

  public double getVL_D_ExcedenteD () {
    return VL_D_ExcedenteD;
  }

  public void setPE_D_Ad_Valorem (double PE_D_Ad_Valorem) {
    this.PE_D_Ad_Valorem = PE_D_Ad_Valorem;
  }

  public double getPE_D_Ad_Valorem () {
    return PE_D_Ad_Valorem;
  }

  public void setVL_TX_Km_Rodado (double VL_TX_Km_Rodado) {
    this.VL_TX_Km_Rodado = VL_TX_Km_Rodado;
  }

  public double getVL_TX_Km_Rodado () {
    return VL_TX_Km_Rodado;
  }

  public void setPE_Desc_FP (double PE_Desc_FP) {
    this.PE_Desc_FP = PE_Desc_FP;
  }

  public double getPE_Desc_FP () {
    return PE_Desc_FP;
  }

  public void setPE_Desc_FV (double PE_Desc_FV) {
    this.PE_Desc_FV = PE_Desc_FV;
  }

  public double getPE_Desc_FV () {
    return PE_Desc_FV;
  }

  public void setVL_E_Ate1 (double VL_E_Ate1) {
    this.VL_E_Ate1 = VL_E_Ate1;
  }

  public double getVL_E_Ate1 () {
    return VL_E_Ate1;
  }

  public void setVL_E_Excedente (double VL_E_Excedente) {
    this.VL_E_Excedente = VL_E_Excedente;
  }

  public double getVL_E_Excedente () {
    return VL_E_Excedente;
  }

  public void setPE_E_Ad_Valorem (double PE_E_Ad_Valorem) {
    this.PE_E_Ad_Valorem = PE_E_Ad_Valorem;
  }

  public double getPE_E_Ad_Valorem () {
    return PE_E_Ad_Valorem;
  }

  public void setPE_C_Ad_Valorem (double PE_C_Ad_Valorem) {
    this.PE_C_Ad_Valorem = PE_C_Ad_Valorem;
  }

  public double getPE_C_Ad_Valorem () {
    return PE_C_Ad_Valorem;
  }

  public void setVL_C_Pedagio (double VL_C_Pedagio) {
    this.VL_C_Pedagio = VL_C_Pedagio;
  }

  public double getVL_C_Pedagio () {
    return VL_C_Pedagio;
  }

  public void setDM_Tipo_Tabela_R (String DM_Tipo_Tabela_R) {
    this.DM_Tipo_Tabela_R = DM_Tipo_Tabela_R;
  }

  public String getDM_Tipo_Tabela_R () {
    return DM_Tipo_Tabela_R;
  }

  public void setDM_Tipo_Tabela_C (String DM_Tipo_Tabela_C) {
    this.DM_Tipo_Tabela_C = DM_Tipo_Tabela_C;
  }

  public String getDM_Tipo_Tabela_C () {
    return DM_Tipo_Tabela_C;
  }

  public void setDM_Tipo_Tabela_D (String DM_Tipo_Tabela_D) {
    this.DM_Tipo_Tabela_D = DM_Tipo_Tabela_D;
  }

  public String getDM_Tipo_Tabela_D () {
    return DM_Tipo_Tabela_D;
  }

  public void setDM_Tipo_Tabela_E (String DM_Tipo_Tabela_E) {
    this.DM_Tipo_Tabela_E = DM_Tipo_Tabela_E;
  }

  public String getDM_Tipo_Tabela_E () {
    return DM_Tipo_Tabela_E;
  }

  public void setDM_Tipo_Peso (String DM_Tipo_Peso) {
    this.DM_Tipo_Peso = DM_Tipo_Peso;
  }

  public String getDM_Tipo_Peso () {
    return DM_Tipo_Peso;
  }

  public void setPE_C_Desc_FP (double PE_C_Desc_FP) {
    this.PE_C_Desc_FP = PE_C_Desc_FP;
  }

  public double getPE_C_Desc_FP () {
    return PE_C_Desc_FP;
  }

  public void setPE_D_Desc_FP (double PE_D_Desc_FP) {
    this.PE_D_Desc_FP = PE_D_Desc_FP;
  }

  public double getPE_D_Desc_FP () {
    return PE_D_Desc_FP;
  }

  public void setPE_E_Desc_FP (double PE_E_Desc_FP) {
    this.PE_E_Desc_FP = PE_E_Desc_FP;
  }

  public double getPE_E_Desc_FP () {
    return PE_E_Desc_FP;
  }

  public void setPE_R_Desc_FP (double PE_R_Desc_FP) {
    this.PE_R_Desc_FP = PE_R_Desc_FP;
  }

  public double getPE_R_Desc_FP () {
    return PE_R_Desc_FP;
  }

  public void setDM_Tipo_Pedagio (String DM_Tipo_Pedagio) {
    this.DM_Tipo_Pedagio = DM_Tipo_Pedagio;
  }

  public String getDM_Tipo_Pedagio () {
    return DM_Tipo_Pedagio;
  }

  public void setVL_R_Ate7500 (double VL_R_Ate7500) {
    this.VL_R_Ate7500 = VL_R_Ate7500;
  }

  public double getVL_R_Ate7500 () {
    return VL_R_Ate7500;
  }

  public void setVL_R_Ate14500 (double VL_R_Ate14500) {
    this.VL_R_Ate14500 = VL_R_Ate14500;
  }

  public double getVL_R_Ate14500 () {
    return VL_R_Ate14500;
  }

  public void setVL_R_Acima14500 (double VL_R_Acima14500) {
    this.VL_R_Acima14500 = VL_R_Acima14500;
  }

  public double getVL_R_Acima14500 () {
    return VL_R_Acima14500;
  }

  public void setPE_Ad_Valorem2 (double PE_Ad_Valorem2) {
    this.PE_Ad_Valorem2 = PE_Ad_Valorem2;
  }

  public double getPE_Ad_Valorem2 () {
    return PE_Ad_Valorem2;
  }

  public double getVL_E_1Kg () {
    return VL_E_1Kg;
  }

  public void setVL_E_1Kg (double VL_E_1Kg) {
    this.VL_E_1Kg = VL_E_1Kg;
  }

  public double getNR_Prazo_Entrega_C () {
    return NR_Prazo_Entrega_C;
  }

  public double getNR_Prazo_Entrega_D () {
    return NR_Prazo_Entrega_D;
  }

  public double getNR_Prazo_Entrega_E () {
    return NR_Prazo_Entrega_E;
  }

  public double getNR_Prazo_Entrega_R () {
    return NR_Prazo_Entrega_R;
  }

  public void setNR_Prazo_Entrega_C (double NR_Prazo_Entrega_C) {
    this.NR_Prazo_Entrega_C = NR_Prazo_Entrega_C;
  }

  public String getOid () {
    return oid;
  }

  public void setOid (String oid) {
    this.oid = oid;
  }

  public void setNR_Prazo_Entrega_D (double NR_Prazo_Entrega_D) {
    this.NR_Prazo_Entrega_D = NR_Prazo_Entrega_D;
  }

  public void setNR_Prazo_Entrega_E (double NR_Prazo_Entrega_E) {
    this.NR_Prazo_Entrega_E = NR_Prazo_Entrega_E;
  }

  public void setNR_Prazo_Entrega_R (double NR_Prazo_Entrega_R) {
    this.NR_Prazo_Entrega_R = NR_Prazo_Entrega_R;
  }

public double getVL_Pedagio_Minimo() {
	return VL_Pedagio_Minimo;
}

public void setVL_Pedagio_Minimo(double pedagio_Minimo) {
	VL_Pedagio_Minimo = pedagio_Minimo;
}
}
