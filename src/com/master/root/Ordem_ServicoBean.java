package com.master.root;

import java.sql.*;
import java.util.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import auth.*;
import com.master.util.*;
import com.master.util.ed.*;

public class Ordem_ServicoBean {

  private int NR_Ordem_Servico;
  private int NR_Acerto;
  private String NM_Solicitante;
  private String DT_Ordem_Servico;
  private String DT_Encerramento;
  private int NR_Meses_Amortizacao;
  private int NR_Kilometragem;
  private int KM_Preventiva;
  private double VL_Previsto;
  private String TX_Observacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Unidade;
  private int oid_Tipo_Servico;
  private int oid_AIDOF;
  private String oid_Pessoa;
  private String oid_Veiculo;
  private String oid_Carreta;
  private String NM_Fantasia;
  private String NM_Razao_Social;
  private String NM_Tipo_Servico;
  private String CD_Tipo_Servico;
  private String DM_Tipo_Despesa;
  private String CD_Unidade;
  private String OID_Pessoa_Fornecedor;
  private String NM_Fornecedor;
  private String NR_CNPJ_CPF_Fornecedor;
  private String DM_Servico;
  private String NM_Servico1;
  private String NM_Servico2;
  private String NM_Servico3;
  private String NM_Servico4;
  private String NM_Servico5;
  private String NM_Servico6;
  private String NM_Servico7;
  private String NM_Servico8;
  private String NM_Servico9;
  private String NM_Servico10;
  private String NM_Condicao_Pagamento;
  private String OID_Pessoa_Faturado;
  private int OID_Acerto;

  private String oid_Veiculo2;
  private String oid_Veiculo3;
  private int NR_Kilometragem2;
  private int NR_Kilometragem3;
  private String DT_Hoje;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();


  
  public Ordem_ServicoBean () {

    NR_Meses_Amortizacao = 0;
    NR_Kilometragem = 0;
    NR_Kilometragem2 = 0;
    NR_Kilometragem3 = 0;
    VL_Previsto = 0;
    TX_Observacao = " ";
    Usuario_Stamp = "";
    Dm_Stamp = "";
    oid = 0;
    oid_Unidade = 0;
    oid_Tipo_Servico = 0;
    oid_AIDOF = 0;
    oid_Pessoa = "";
    oid_Veiculo = "";
    oid_Veiculo2 = "";
    oid_Veiculo3 = "";
    NM_Fantasia = "";
    NM_Razao_Social = "";
    NM_Tipo_Servico = "";
    CD_Tipo_Servico = "";
    CD_Unidade = "";

  }

  public int getNR_Ordem_Servico () {
    return NR_Ordem_Servico;
  }

  public void setNR_Ordem_Servico (int NR_Ordem_Servico) {
    this.NR_Ordem_Servico = NR_Ordem_Servico;
  }

  public String getDT_Ordem_Servico () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Ordem_Servico);
    DT_Ordem_Servico = DataFormatada.getDT_FormataData ();

    return DT_Ordem_Servico;
  }

  public void setDT_Ordem_Servico (String DT_Ordem_Servico) {
    this.DT_Ordem_Servico = DT_Ordem_Servico;
  }

  public String getDT_Encerramento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Encerramento);
    DT_Encerramento = DataFormatada.getDT_FormataData ();

    return DT_Encerramento;
  }

  public void setDT_Encerramento (String DT_Encerramento) {
    this.DT_Encerramento = DT_Encerramento;
  }

  public int getNR_Meses_Amortizacao () {
    return NR_Meses_Amortizacao;
  }

  public void setNR_Meses_Amortizacao (int NR_Meses_Amortizacao) {
    this.NR_Meses_Amortizacao = NR_Meses_Amortizacao;
  }

  public int getNR_Kilometragem () {
    return NR_Kilometragem;
  }

  public void setNR_Kilometragem (int NR_Kilometragem) {
    this.NR_Kilometragem = NR_Kilometragem;
  }

  public double getVL_Previsto () {
    return VL_Previsto;
  }

  public void setVL_Previsto (double VL_Previsto) {
    this.VL_Previsto = VL_Previsto;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public int getOID_Unidade () {
    return oid_Unidade;
  }

  public void setOID_Unidade (int n) {
    this.oid_Unidade = n;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getOID_Pessoa () {
    return oid_Pessoa;
  }

  public void setOID_Pessoa (String n) {
    this.oid_Pessoa = n;
  }

  public String getOID_Veiculo () {
    return oid_Veiculo;
  }

  public void setOID_Veiculo (String n) {
    this.oid_Veiculo = n;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public int getOID_Tipo_Servico () {
    return oid_Tipo_Servico;
  }

  public void setOID_Tipo_Servico (int n) {
    this.oid_Tipo_Servico = n;
  }

  public String getNM_Tipo_Servico () {
    return NM_Tipo_Servico;
  }

  public void setNM_Tipo_Servico (String NM_Tipo_Servico) {
    this.NM_Tipo_Servico = NM_Tipo_Servico;
  }

  public String getCD_Tipo_Servico () {
    return CD_Tipo_Servico;
  }

  public void setCD_Tipo_Servico (String CD_Tipo_Servico) {
    this.CD_Tipo_Servico = CD_Tipo_Servico;
  }

  public String getDM_Tipo_Despesa () {
    return DM_Tipo_Despesa;
  }

  public void setDM_Tipo_Despesa (String DM_Tipo_Despesa) {
    this.DM_Tipo_Despesa = DM_Tipo_Despesa;
  }

  public int getOID_AIDOF () {
    return oid_AIDOF;
  }

  public void setOID_AIDOF (int n) {
    this.oid_AIDOF = n;
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

  public void insert () throws Exception {
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);

    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery ("SELECT MAX(OID_Ordem_Servico) FROM Ordens_Servicos");

      if (cursor.next ()) {
        setOID (cursor.getInt (1) + 1);
        setNR_Ordem_Servico (this.getOID());
      }

      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    /*
     * Define o insert.
     */
  	//System.out.println(getDM_Servico());

    if ("P".equals(getDM_Servico())) {
        int qrServ=1;
      	ResultSet cursorPre = null;
      	ResultSet cursorGrupo = null;
      	String nm_Tipo_Servico="";
      	String sqlPre = " SELECT Tipos_Servicos.oid_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Tipos_Servicos.NM_Tipo_Servico,  " +
      				"         Manutencoes_Preventivas.NR_Kilometragem_Prevista,  Tipos_Servicos.DM_Custo " +
					"  FROM   Manutencoes_Preventivas, Tipos_Servicos " +
      				"  WHERE  Manutencoes_Preventivas.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico " +
      				"  AND    Manutencoes_Preventivas.OID_Veiculo = '" +getOID_Veiculo () +"'" +
      				"  AND    Manutencoes_Preventivas.NR_Kilometragem_Prevista <=  " + getKM_Preventiva() +
      				"  ORDER BY  NM_Tipo_Servico ";
      	//aSystem.out.println(sqlPre);

      	Statement stmt = conn.createStatement ();
      	cursorPre = stmt.executeQuery (sqlPre);
      	while (cursorPre.next () && qrServ<=10) {
      		nm_Tipo_Servico=cursorPre.getString("NM_Tipo_Servico");
      		if ("G".equals(cursorPre.getString("DM_Custo"))) {
      	      	sqlPre = " SELECT Tipos_Servicos.CD_Tipo_Servico, Tipos_Servicos.NM_Tipo_Servico  " +
				"  FROM   Grupos_Servicos, Tipos_Servicos " +
  				"  WHERE  Grupos_Servicos.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico " +
  				"  AND    Grupos_Servicos.OID_Grupo_Servico = '" +cursorPre.getInt("oid_Tipo_Servico") +"'" +
  				"  ORDER BY  NM_Tipo_Servico ";
      	      	//System.out.println(sqlPre);

      	      	Statement stmt2 = conn.createStatement ();
  				cursorGrupo = stmt2.executeQuery (sqlPre);
  				while (cursorGrupo.next () && qrServ<=10) {
	  	          	if (qrServ==1)  setNM_Servico1 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==2)  setNM_Servico2 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==3)  setNM_Servico3 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==4)  setNM_Servico4 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==5)  setNM_Servico5 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==6)  setNM_Servico6 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==7)  setNM_Servico7 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==8)  setNM_Servico8 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==9)  setNM_Servico9 (nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	          	if (qrServ==10) setNM_Servico10(nm_Tipo_Servico+"-"+cursorGrupo.getString("NM_Tipo_Servico"));
	  	      		qrServ++;
  				}
      		}else {
	          	if (qrServ==1)  setNM_Servico1 (nm_Tipo_Servico);
	          	if (qrServ==2)  setNM_Servico2 (nm_Tipo_Servico);
	          	if (qrServ==3)  setNM_Servico3 (nm_Tipo_Servico);
	          	if (qrServ==4)  setNM_Servico4 (nm_Tipo_Servico);
	          	if (qrServ==5)  setNM_Servico5 (nm_Tipo_Servico);
	          	if (qrServ==6)  setNM_Servico6 (nm_Tipo_Servico);
	          	if (qrServ==7)  setNM_Servico7 (nm_Tipo_Servico);
	          	if (qrServ==8)  setNM_Servico8 (nm_Tipo_Servico);
	          	if (qrServ==9)  setNM_Servico9 (nm_Tipo_Servico);
	          	if (qrServ==10) setNM_Servico10(nm_Tipo_Servico);
	      		qrServ++;
	          	
      		}
      	}	
      }
      
    
    StringBuffer buff = new StringBuffer ();

    buff.append ("INSERT INTO Ordens_Servicos (OID_Ordem_Servico, OID_Unidade, OID_Veiculo, OID_Tipo_Servico, NR_Ordem_Servico, DT_Ordem_Servico, DT_Encerramento, NR_Meses_Amortizacao, NR_Kilometragem, VL_Previsto, TX_Observacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp, OID_Pessoa_Fornecedor, NM_Servico1, NM_Servico2, NM_Servico3, NM_Servico4, NM_Servico5, NM_Servico6, NM_Servico7, NM_Servico8, NM_Servico9, NM_Servico10, NM_Condicao_Pagamento, oid_Carreta, oid_veiculo2, oid_veiculo3, NR_Kilometragem2, NR_Kilometragem3, KM_Preventiva ) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Unidade ());
      pstmt.setString (3 , getOID_Veiculo ());
      pstmt.setInt (4 , getOID_Tipo_Servico ());
      pstmt.setInt (5 , getNR_Ordem_Servico ());
      pstmt.setDate (6 , FormataData.formataDataTB (getDT_Ordem_Servico ()));
      pstmt.setDate (7 , FormataData.formataDataTB (getDT_Encerramento ()));
      pstmt.setInt (8 , getNR_Meses_Amortizacao ());
      pstmt.setInt (9 , getNR_Kilometragem ());
      pstmt.setDouble (10 , getVL_Previsto ());
      pstmt.setString (11 , getTX_Observacao ());
      pstmt.setDate (12 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (13 , getUsuario_Stamp ());
      pstmt.setString (14 , getDm_Stamp ());
      pstmt.setString (15 , getOID_Pessoa_Fornecedor ());
      pstmt.setString (16 , getNM_Servico1 ());
      pstmt.setString (17 , getNM_Servico2 ());
      pstmt.setString (18 , getNM_Servico3 ());
      pstmt.setString (19 , getNM_Servico4 ());
      pstmt.setString (20 , getNM_Servico5 ());
      pstmt.setString (21 , getNM_Servico6 ());
      pstmt.setString (22 , getNM_Servico7 ());
      pstmt.setString (23 , getNM_Servico8 ());
      pstmt.setString (24 , getNM_Servico9 ());
      pstmt.setString (25 , getNM_Servico10 ());
      pstmt.setString (26 , getNM_Condicao_Pagamento ());
      pstmt.setString (27 , getOid_Carreta ());
      pstmt.setString (28 , getOid_Veiculo2());
      pstmt.setString (29 , getOid_Veiculo3());
      pstmt.setInt (30 , getNR_Kilometragem2());
      pstmt.setInt (31 , getNR_Kilometragem3());
      pstmt.setInt (32 , getKM_Preventiva());

      pstmt.executeUpdate ();
      
      
      if (getOID_Tipo_Servico () ==parametro_FixoED.getOID_Tipo_Servico_Acerto_Contas()){

      ///ABASTECIMENTOS EXT

      String sql =" SELECT oid_movimento_ordem_servico, oid_Ordem_Servico " +
                  " FROM  Movimentos_Ordens_Servicos " +
                  " WHERE Movimentos_Ordens_Servicos.oid_Tipo_Servico = " + parametro_FixoED.getOID_Tipo_Servico_Abastecimento() +
                  " AND   Movimentos_Ordens_Servicos.dt_movimento_ordem_servico >= '" + getDT_Ordem_Servico () + "'" +
                  " AND   Movimentos_Ordens_Servicos.oid_Veiculo = '" + getOID_Veiculo () + "'" +
                  " AND   Movimentos_Ordens_Servicos.NR_Quantidade >0 " +
                  " AND   Movimentos_Ordens_Servicos.DM_Acerto is null " +
                  " AND   Movimentos_Ordens_Servicos.oid_Ordem_Servico = 1 ";

              // System.out.println(sql);

              Statement stmt2 = conn.createStatement ();
              ResultSet res2 = stmt2.executeQuery (sql);

              while (res2.next ()) {
                // System.out.println(res2.getLong("oid_movimento_ordem_servico"));
                sql = " UPDATE Movimentos_Ordens_Servicos " +
                      " SET OID_Ordem_SERVICO = " + getOID () +
                      " , DM_Acerto = 'S' " +
                      " , oid_ordem_servico_anterior = " + res2.getLong("oid_Ordem_Servico") +
                      " WHERE oid_movimento_ordem_servico= " + res2.getLong("oid_movimento_ordem_servico");
                  // System.out.println(sql);

                PreparedStatement pst = conn.prepareStatement (sql);
                pst.executeUpdate ();

              }

          ///ABASTECIMENTOS INT (estoque)
             // select Movimentos_Ordens_Servicos.OID_Movimento_Ordem_Servico,
             //     movimentos_estoques where Movimentos_Ordens_Servicos.oid_ordem_servico=Movimentos_estoques.oid_ordem_servico and Movimentos_Ordens_Servicos.oid_veiculo='IML4110';


          sql ="   SELECT Movimentos_Ordens_Servicos.OID_Movimento_Ordem_Servico, Movimentos_Estoques.oid_movimento_estoque, Movimentos_Ordens_Servicos.oid_Ordem_Servico  " +
               "   FROM  Movimentos_Ordens_Servicos, Movimentos_Estoques " +
               "   WHERE Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico =  Movimentos_Estoques.oid_Movimento_Ordem_Servico " +
               "   AND   Movimentos_Ordens_Servicos.oid_Veiculo ='" + getOID_Veiculo () + "'" +
               "   AND   Movimentos_Estoques.NR_Quantidade >0 " +
               "   AND   Movimentos_Ordens_Servicos.DM_Acerto is null ";

              // System.out.println(sql);

              Statement stmt3 = conn.createStatement ();
              res2 = stmt3.executeQuery (sql);

              while (res2.next ()) {
                sql = " UPDATE Movimentos_Ordens_Servicos " +
                      " SET OID_Ordem_SERVICO = " + getOID () +
                      " ,DM_Acerto = 'S' " +
                      " ,oid_ordem_servico_anterior = " + res2.getLong("oid_Ordem_Servico") +
                      " WHERE oid_movimento_ordem_servico= " + res2.getLong("oid_movimento_ordem_servico");
                  // System.out.println(sql);

                PreparedStatement pst = conn.prepareStatement (sql);
                pst.executeUpdate ();

              }

      }


      /*
       * Define o update.
       */

      setNR_Ordem_Servico (getNR_Ordem_Servico () + 1);

      buff.delete (0 , buff.length ());

      buff.append ("UPDATE Parametros_Filiais SET NR_Proxima_Ordem_Servico=? ");
      buff.append ("WHERE OID_Unidade =?");

      PreparedStatement pstmt1 =
          conn.prepareStatement (buff.toString ());

      pstmt1.setInt (1 , getNR_Ordem_Servico ());
      pstmt1.setInt (2 , getOID_Unidade ());
      pstmt1.executeUpdate ();

      setNR_Ordem_Servico (getNR_Ordem_Servico () - 1);

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

  public void finaliza () throws Exception {
	  DT_Hoje = Data.getDataDMY();
	  /*
	     * Abre a conexão com o banco
	     */
	    Connection conn = null;
	    try {
	      // Pede uma conexão ao gerenciador do driver
	      // passando como parâmetro o NM_Ordem_Servico do DSN
	      // o NM_Ordem_Servico de usuário e a senha do banco.
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }

	    StringBuffer buff = new StringBuffer ();
	    /*
	      buff.append("SELECT Ordens_Servicos.DT_Encerramento ");
	      buff.append("FROM Ordens_Servicos ");
	      buff.append(" WHERE Ordens_Servicos.oid_Ordem_Servico = ");
	      buff.append(getOID());

	      Statement stmt = conn.createStatement();
	      ResultSet cursor =
	       stmt.executeQuery(buff.toString());

	      while (cursor.next())
	      {
	       setDT_Encerramento(cursor.getString(1));
	      }

	      if (this.DT_Encerramento != null)
	      {
	       return;
	      }
	     */
	    /*
	     * Define o update.
	     */
	    buff.delete (0 , buff.length ());

	    buff.append ("UPDATE Ordens_Servicos SET DT_Encerramento=?");
	    buff.append ("WHERE OID_Ordem_Servico=?");
	    /*
	     * Define os dados do SQL
	     * e executa o insert no banco.
	     */
	    try {
	      PreparedStatement pstmt =
	          conn.prepareStatement (buff.toString ());
	      pstmt.setDate (1 , FormataData.formataDataTB(DT_Hoje));
	      pstmt.setInt (2 , getOID ());
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
      // passando como parâmetro o NM_Ordem_Servico do DSN
      // o NM_Ordem_Servico de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    StringBuffer buff = new StringBuffer ();
    /*
      buff.append("SELECT Ordens_Servicos.DT_Encerramento ");
      buff.append("FROM Ordens_Servicos ");
      buff.append(" WHERE Ordens_Servicos.oid_Ordem_Servico = ");
      buff.append(getOID());

      Statement stmt = conn.createStatement();
      ResultSet cursor =
       stmt.executeQuery(buff.toString());

      while (cursor.next())
      {
       setDT_Encerramento(cursor.getString(1));
      }

      if (this.DT_Encerramento != null)
      {
       return;
      }
     */
    /*
     * Define o update.
     */
    buff.delete (0 , buff.length ());

    buff.append ("UPDATE Ordens_Servicos SET OID_Tipo_Servico=?, NR_Meses_Amortizacao=?, NR_Kilometragem=?, VL_Previsto=?, TX_Observacao=?, DT_Ordem_Servico=?, DT_Encerramento=?, NM_Servico1=?, NM_Servico2=?, NM_Servico3=?, NM_Servico4=?, NM_Servico5=?, NM_Servico6=?, NM_Servico7=?, NM_Servico8=?, NM_Servico9=?, NM_Servico10=?, NM_Condicao_Pagamento=?, oid_Carreta=?, oid_veiculo2=?, oid_veiculo3=?, NR_Kilometragem2=?, NR_Kilometragem3=?, KM_Preventiva=? ");
    buff.append ("WHERE OID_Ordem_Servico=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Tipo_Servico ());
      pstmt.setInt (2 , getNR_Meses_Amortizacao ());
      pstmt.setInt (3 , getNR_Kilometragem ());
      pstmt.setDouble (4 , getVL_Previsto ());
      pstmt.setString (5 , getTX_Observacao ());
      pstmt.setDate (6 , FormataData.formataDataTB (getDT_Ordem_Servico ()));
      pstmt.setDate (7 , FormataData.formataDataTB (getDT_Encerramento ()));
      pstmt.setString (8 , getNM_Servico1 ());
      pstmt.setString (9 , getNM_Servico2 ());
      pstmt.setString (10 , getNM_Servico3 ());
      pstmt.setString (11 , getNM_Servico4 ());
      pstmt.setString (12 , getNM_Servico5 ());
      pstmt.setString (13 , getNM_Servico6 ());
      pstmt.setString (14 , getNM_Servico7 ());
      pstmt.setString (15 , getNM_Servico8 ());
      pstmt.setString (16 , getNM_Servico9 ());
      pstmt.setString (17 , getNM_Servico10 ());
      pstmt.setString (18 , getNM_Condicao_Pagamento ());
      pstmt.setString (19 , getOid_Carreta ());
      pstmt.setString (20 , getOid_Veiculo2());
      pstmt.setString (21 , getOid_Veiculo3());
      pstmt.setInt (22 , getNR_Kilometragem2());
      pstmt.setInt (23 , getNR_Kilometragem3());
      pstmt.setInt (24 , getKM_Preventiva());
      pstmt.setInt (25 , getOID ());
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
      // passando como parâmetro o NM_Ordem_Servico do DSN
      // o NM_Ordem_Servico de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Ordens_Servicos ");
    buff.append ("WHERE OID_Ordem_Servico=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {

//		    // System.out.println("Ordem_ServicoBean.delete() sql = "+buff.toString());

      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
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

  public static final Ordem_ServicoBean getByOID (int oid) throws Excecoes {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ordem_Servico do DSN
      // o NM_Ordem_Servico de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , Ordem_ServicoBean.class.getName () , "getByOID(int oid)");
    }

    Ordem_ServicoBean p = new Ordem_ServicoBean ();
    try {
      String sql =
          " SELECT Ordens_Servicos.OID_Ordem_Servico,	" +
          "        Ordens_Servicos.OID_Unidade, " +
          "        Ordens_Servicos.OID_Tipo_Servico, " +
          "        Ordens_Servicos.NR_Ordem_Servico, " +
          "        Ordens_Servicos.DT_Ordem_Servico, " +
          "        Ordens_Servicos.DT_Encerramento, " +
          "        Ordens_Servicos.NR_Meses_Amortizacao, " +
          "        Ordens_Servicos.NR_Kilometragem, " +
          "        Ordens_Servicos.VL_Previsto, " +
          "        Ordens_Servicos.TX_Observacao, " +
          "        Ordens_Servicos.Dt_Stamp, " +
          "        Ordens_Servicos.Usuario_Stamp, " +
          "        Ordens_Servicos.Dm_Stamp, " +
          "        Tipos_Servicos.NM_Tipo_Servico, " +
          "        Tipos_Servicos.CD_Tipo_Servico, " +
          "        Unidades.CD_Unidade, " +
          "        Ordens_Servicos.OID_Veiculo, " +
          "        Pessoas.NM_Fantasia, " +
          "        Tipos_Servicos.DM_Tipo_Despesa, " +
          "        Ordens_Servicos.oid_pessoa_fornecedor, " +
          "        Ordens_Servicos.NM_Servico1, " +
          "        Ordens_Servicos.NM_Servico2, " +
          "        Ordens_Servicos.NM_Servico3, " +
          "        Ordens_Servicos.NM_Servico4, " +
          "        Ordens_Servicos.NM_Servico5, " +
          "        Ordens_Servicos.NM_Servico6, " +
          "        Ordens_Servicos.NM_Servico7, " +
          "        Ordens_Servicos.NM_Servico8, " +
          "        Ordens_Servicos.NM_Servico9, " +
          "        Ordens_Servicos.NM_Servico10, " +
          "        Ordens_Servicos.OID_Carreta, " +
          "        Ordens_Servicos.OID_Veiculo2, " +
          "        Ordens_Servicos.OID_Veiculo3, " +
          "        Ordens_Servicos.NR_Kilometragem2, " +
          "        Ordens_Servicos.NR_Kilometragem3, " +
          "        Ordens_Servicos.KM_Preventiva, " +
          "        Ordens_Servicos.NM_Condicao_Pagamento " +
          " FROM Ordens_Servicos, " +
          "      Unidades, " +
          "      Tipos_Servicos, " +
          "      Pessoas, " +
          "      Pessoas Pessoa_Fornecedor " +
          " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
          "   AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
          "   AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa " +
          "   AND Ordens_Servicos.OID_Pessoa_Fornecedor = Pessoa_Fornecedor.OID_Pessoa " +
          "   AND Ordens_Servicos.OID_Ordem_Servico = " + oid;
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Servico (cursor.getInt (3));
        p.setNR_Ordem_Servico (cursor.getInt (4));
        p.setDT_Ordem_Servico (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setNR_Meses_Amortizacao (cursor.getInt (7));
        p.setNR_Kilometragem (cursor.getInt (8));
        p.setVL_Previsto (cursor.getDouble (9));
        p.setTX_Observacao (cursor.getString (10));
        p.setDt_Stamp (cursor.getString (11));
        p.setUsuario_Stamp (cursor.getString (12));
        p.setDm_Stamp (cursor.getString (13));
        p.setNM_Tipo_Servico (cursor.getString (14));
        p.setCD_Tipo_Servico (cursor.getString (15));
        p.setCD_Unidade (cursor.getString (16));
        p.setOID_Veiculo (cursor.getString (17));
        p.setNM_Fantasia (cursor.getString (18));
        p.setDM_Tipo_Despesa (cursor.getString (19));
        p.setOID_Pessoa_Fornecedor (cursor.getString (20));
        p.setNM_Servico1 (cursor.getString (21));
        p.setNM_Servico2 (cursor.getString (22));
        p.setNM_Servico3 (cursor.getString (23));
        p.setNM_Servico4 (cursor.getString (24));
        p.setNM_Servico5 (cursor.getString (25));
        p.setNM_Servico6 (cursor.getString (26));
        p.setNM_Servico7 (cursor.getString (27));
        p.setNM_Servico8 (cursor.getString (28));
        p.setNM_Servico9 (cursor.getString (29));
        p.setNM_Servico10 (cursor.getString (30));
        p.setOid_Carreta (cursor.getString (31));
        if (p.getOid_Carreta() == null || p.getOid_Carreta().length () <= 4)
          p.setOid_Carreta (" ");
        p.setOid_Veiculo2(cursor.getString (32));
        p.setOid_Veiculo3(cursor.getString (33));
        p.setNR_Kilometragem2(cursor.getInt (34));
        p.setNR_Kilometragem3(cursor.getInt (35));
        p.setKM_Preventiva(cursor.getInt (36));
        p.setNM_Condicao_Pagamento (cursor.getString (37));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Ordem_ServicoBean.class.getName () , "getByOID(int oid)");
    }
    return p;
  }

  public static final Ordem_ServicoBean getByNR_Ordem_Servico (int NR_Ordem_Servico) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ordem_Servico do DSN
      // o NM_Ordem_Servico de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Ordem_ServicoBean p = new Ordem_ServicoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Ordens_Servicos.Dt_Stamp, Ordens_Servicos.Usuario_Stamp, Ordens_Servicos.Dm_Stamp, Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo, Ordens_Servicos.OID_Veiculo2, Ordens_Servicos.OID_Veiculo3, Ordens_Servicos.NR_Kilometragem2, Ordens_Servicos.NR_Kilometragem3 ");
      buff.append ("FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos ");
      buff.append ("WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo ");
      buff.append (" AND Ordens_Servicos.NR_Ordem_Servico =");
      buff.append (NR_Ordem_Servico);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Servico (cursor.getInt (3));
        p.setNR_Ordem_Servico (cursor.getInt (4));
        p.setDT_Ordem_Servico (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setNR_Meses_Amortizacao (cursor.getInt (7));
        p.setNR_Kilometragem (cursor.getInt (8));
        p.setVL_Previsto (cursor.getDouble (9));
        p.setTX_Observacao (cursor.getString (10));
        p.setDt_Stamp (cursor.getString (11));
        p.setUsuario_Stamp (cursor.getString (12));
        p.setDm_Stamp (cursor.getString (13));
        p.setNM_Tipo_Servico (cursor.getString (14));
        p.setCD_Tipo_Servico (cursor.getString (15));
        p.setCD_Unidade (cursor.getString (16));
        p.setOID_Veiculo (cursor.getString (17));
        p.setOid_Veiculo2(cursor.getString (18));
        p.setOid_Veiculo3(cursor.getString (19));
        p.setNR_Kilometragem2(cursor.getInt (20));
        p.setNR_Kilometragem3(cursor.getInt (21));

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

  public static final List getByNR_Ordem_Servico_Lista (int NR_Ordem_Servico) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ordem_Servico do DSN
      // o NM_Ordem_Servico de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Ordens_Servicos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Ordens_Servicos.Dt_Stamp, Ordens_Servicos.Usuario_Stamp, Ordens_Servicos.Dm_Stamp, Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo, Ordens_Servicos.OID_Veiculo2, Ordens_Servicos.OID_Veiculo3, Ordens_Servicos.NR_Kilometragem2, Ordens_Servicos.NR_Kilometragem3 ");
      buff.append ("FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos ");
      buff.append ("WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo ");
      buff.append (" AND Ordens_Servicos.NR_Ordem_Servico =");
      buff.append (NR_Ordem_Servico);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ordem_ServicoBean p = new Ordem_ServicoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Servico (cursor.getInt (3));
        p.setNR_Ordem_Servico (cursor.getInt (4));
        p.setDT_Ordem_Servico (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setNR_Meses_Amortizacao (cursor.getInt (7));
        p.setNR_Kilometragem (cursor.getInt (8));
        p.setVL_Previsto (cursor.getDouble (9));
        p.setTX_Observacao (cursor.getString (10));
        p.setDt_Stamp (cursor.getString (11));
        p.setUsuario_Stamp (cursor.getString (12));
        p.setDm_Stamp (cursor.getString (13));
        p.setNM_Tipo_Servico (cursor.getString (14));
        p.setCD_Tipo_Servico (cursor.getString (15));
        p.setCD_Unidade (cursor.getString (16));
        p.setOID_Veiculo (cursor.getString (17));
        p.setOid_Veiculo2(cursor.getString (18));
        p.setOid_Veiculo3(cursor.getString (19));
        p.setNR_Kilometragem2(cursor.getInt (20));
        p.setNR_Kilometragem3(cursor.getInt (21));
        Ordens_Servicos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ordens_Servicos_Lista;
  }

  public static final List getAll (String NR_Ordem_Servico , String NR_Placa , String oid_Tipo_Servico , String oid_Unidade , String oid_Pessoa , String DT_Inicial , String DT_Final) throws Exception {
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

    List Ordens_Servicos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Ordens_Servicos.Dt_Stamp, Ordens_Servicos.Usuario_Stamp, Ordens_Servicos.Dm_Stamp, Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo ");
      buff.append ("FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos ");
      buff.append ("WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo ");
      if (NR_Placa != null && !NR_Placa.equals ("") && !NR_Placa.equals ("null")) {
        buff.append (" AND Ordens_Servicos.OID_VEICULO ='");
        buff.append (NR_Placa + "'");
      }
      if (oid_Pessoa != null && !oid_Pessoa.equals ("") && !oid_Pessoa.equals ("null")) {
        buff.append (" AND Ordens_Servicos.oid_Pessoa_Fornecedor ='");
        buff.append (oid_Pessoa + "'");
      }
      if (oid_Tipo_Servico != null && !oid_Tipo_Servico.equals ("") && !oid_Tipo_Servico.equals ("null")) {
        buff.append (" AND Ordens_Servicos.oid_Tipo_Servico ='");
        buff.append (oid_Tipo_Servico + "'");
      }
      if (oid_Unidade != null && !oid_Unidade.equals ("") && !oid_Unidade.equals ("null")) {
        buff.append (" AND Ordens_Servicos.oid_Unidade ='");
        buff.append (oid_Unidade + "'");
      }

      if (DT_Inicial != null && !DT_Inicial.equals ("") && !DT_Inicial.equals ("null")) {
        buff.append (" AND Ordens_Servicos.DT_ORDEM_SERVICO >='");
        buff.append (DT_Inicial + "'");
      }
      if (DT_Final != null && !DT_Final.equals ("") && !DT_Final.equals ("null")) {
        buff.append (" AND Ordens_Servicos.DT_ORDEM_SERVICO <='");
        buff.append (DT_Final + "'");
      }

      if (NR_Ordem_Servico != null && !NR_Ordem_Servico.equals ("") && !NR_Ordem_Servico.equals ("null")) {
        buff.append (" AND Ordens_Servicos.NR_Ordem_Servico =");
        buff.append (NR_Ordem_Servico);
      }

      buff.append (" ORDER BY Ordens_Servicos.NR_Ordem_Servico DESC");

      // System.out.println (buff.toString ());

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ordem_ServicoBean p = new Ordem_ServicoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Servico (cursor.getInt (3));
        p.setNR_Ordem_Servico (cursor.getInt (4));
        p.setDT_Ordem_Servico (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setNR_Meses_Amortizacao (cursor.getInt (7));
        p.setNR_Kilometragem (cursor.getInt (8));
        p.setVL_Previsto (cursor.getDouble (9));
        p.setTX_Observacao (cursor.getString (10));
        p.setDt_Stamp (cursor.getString (11));
        p.setUsuario_Stamp (cursor.getString (12));
        p.setDm_Stamp (cursor.getString (13));
        p.setNM_Tipo_Servico (cursor.getString (14));
        p.setCD_Tipo_Servico (cursor.getString (15));
        p.setCD_Unidade (cursor.getString (16));
        p.setOID_Veiculo (cursor.getString (17));

        Ordens_Servicos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ordens_Servicos_Lista;
  }

  public static final List getAll(HttpServletRequest request) throws Exception {
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

	    String NR_Ordem_Servico = request.getParameter("FT_NR_Ordem_Servico");
	    String NR_Placa = request.getParameter("FT_NR_Placa");
	    String NR_Placa2 = request.getParameter("FT_NR_Placa2");
	    String NR_Placa3 = request.getParameter("FT_NR_Placa3");
	    String oid_Tipo_Servico = request.getParameter("oid_Tipo_Servico");
	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    String DT_Inicial = request.getParameter("FT_DT_Inicial");
	    String DT_Final = request.getParameter("FT_DT_Final");

	    List Ordens_Servicos_Lista = new ArrayList ();
	    try {
	    	StringBuffer buff = new StringBuffer ();
	    	buff.append ("SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Ordens_Servicos.Dt_Stamp, Ordens_Servicos.Usuario_Stamp, Ordens_Servicos.Dm_Stamp, Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo, Ordens_Servicos.OID_Veiculo2, Ordens_Servicos.OID_Veiculo3, Ordens_Servicos.NR_Kilometragem2, Ordens_Servicos.NR_Kilometragem3 ");
	    	buff.append ("FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos ");
	    	buff.append ("WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo ");
	    	if (NR_Placa != null && !NR_Placa.equals ("") && !NR_Placa.equals ("null")) {
	    		buff.append (" AND Ordens_Servicos.OID_VEICULO ='");
	    		buff.append (NR_Placa + "'");
	    	}
	 	     if (NR_Placa2 != null && !NR_Placa2.equals ("") && !NR_Placa2.equals ("null")) {
		        buff.append (" AND Ordens_Servicos.OID_VEICULO2 ='");
		        buff.append (NR_Placa2 + "'");
		      }
	 	     if (NR_Placa3 != null && !NR_Placa3.equals ("") && !NR_Placa3.equals ("null")) {
		        buff.append (" AND Ordens_Servicos.OID_VEICULO3 ='");
		        buff.append (NR_Placa3 + "'");
		      }

	      if (oid_Pessoa != null && !oid_Pessoa.equals ("") && !oid_Pessoa.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.oid_Pessoa_Fornecedor ='");
	        buff.append (oid_Pessoa + "'");
	      }
	      if (oid_Tipo_Servico != null && !oid_Tipo_Servico.equals ("") && !oid_Tipo_Servico.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.oid_Tipo_Servico ='");
	        buff.append (oid_Tipo_Servico + "'");
	      }
	      if (oid_Unidade != null && !oid_Unidade.equals ("") && !oid_Unidade.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.oid_Unidade ='");
	        buff.append (oid_Unidade + "'");
	      }

	      if (DT_Inicial != null && !DT_Inicial.equals ("") && !DT_Inicial.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.DT_ORDEM_SERVICO >='");
	        buff.append (DT_Inicial + "'");
	      }
	      if (DT_Final != null && !DT_Final.equals ("") && !DT_Final.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.DT_ORDEM_SERVICO <='");
	        buff.append (DT_Final + "'");
	      }

	      if (NR_Ordem_Servico != null && !NR_Ordem_Servico.equals ("") && !NR_Ordem_Servico.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.NR_Ordem_Servico =");
	        buff.append (NR_Ordem_Servico);
	      }

	      buff.append (" ORDER BY Ordens_Servicos.NR_Ordem_Servico DESC");

	      // System.out.println (buff.toString ());

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor =
	          stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	        Ordem_ServicoBean p = new Ordem_ServicoBean ();
	        p.setOID (cursor.getInt (1));
	        p.setOID_Unidade (cursor.getInt (2));
	        p.setOID_Tipo_Servico (cursor.getInt (3));
	        p.setNR_Ordem_Servico (cursor.getInt (4));
	        p.setDT_Ordem_Servico (cursor.getString (5));
	        p.setDT_Encerramento (cursor.getString (6));
	        p.setNR_Meses_Amortizacao (cursor.getInt (7));
	        p.setNR_Kilometragem (cursor.getInt (8));
	        p.setVL_Previsto (cursor.getDouble (9));
	        p.setTX_Observacao (cursor.getString (10));
	        p.setDt_Stamp (cursor.getString (11));
	        p.setUsuario_Stamp (cursor.getString (12));
	        p.setDm_Stamp (cursor.getString (13));
	        p.setNM_Tipo_Servico (cursor.getString (14));
	        p.setCD_Tipo_Servico (cursor.getString (15));
	        p.setCD_Unidade (cursor.getString (16));
	        p.setOID_Veiculo (cursor.getString (17));
	        p.setOid_Veiculo2(cursor.getString (18));
	        p.setOid_Veiculo3(cursor.getString (19));
	        p.setNR_Kilometragem2(cursor.getInt (20));
	        p.setNR_Kilometragem3(cursor.getInt (21));
	        Ordens_Servicos_Lista.add (p);
	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	    }
	    return Ordens_Servicos_Lista;
	  }

  public static final List getAllAcerto (String NR_Acerto , String NR_Placa , String oid_Unidade , String oid_Motorista , String DT_Inicial , String DT_Final) throws Exception {
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

	    List Ordens_Servicos_Lista = new ArrayList ();
	    Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Ordens_Servicos.Dt_Stamp, Ordens_Servicos.Usuario_Stamp, Ordens_Servicos.Dm_Stamp, Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo ");
	      buff.append ("FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos ");
	      buff.append ("WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo AND Ordens_Servicos.oid_Tipo_Servico = " + parametro_FixoED.getOID_Tipo_Servico_Acerto_Contas ());
	      if (NR_Placa != null && !NR_Placa.equals ("") && !NR_Placa.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.OID_VEICULO ='");
	        buff.append (NR_Placa + "'");
	      }
	      if (oid_Unidade != null && !oid_Unidade.equals ("") && !oid_Unidade.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.oid_Unidade ='");
	        buff.append (oid_Unidade + "'");
	      }
	      if (oid_Motorista != null && !oid_Motorista.equals ("") && !oid_Motorista.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.oid_Motorista ='");
	        buff.append (oid_Motorista + "'");
	      }

	      if (DT_Inicial != null && !DT_Inicial.equals ("") && !DT_Inicial.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.DT_ORDEM_SERVICO >='");
	        buff.append (DT_Inicial + "'");
	      }
	      if (DT_Final != null && !DT_Final.equals ("") && !DT_Final.equals ("null")) {
	        buff.append (" AND Ordens_Servicos.DT_ORDEM_SERVICO <='");
	        buff.append (DT_Final + "'");
	      }

	      buff.append (" ORDER BY Ordens_Servicos.NR_Ordem_Servico DESC");

	      // System.out.println (buff.toString ());

	      // System.out.println ("motorista =>>>>" + oid_Motorista);
	      // System.out.println ("NR_Acerto =>>>>" + NR_Acerto);

	      if (NR_Acerto != null && !NR_Acerto.equals ("") && !NR_Acerto.equals ("null")) {
	        buff = new StringBuffer ();
	        buff.append ("SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Ordens_Servicos.Dt_Stamp, Ordens_Servicos.Usuario_Stamp, Ordens_Servicos.Dm_Stamp, Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo ");
	        buff.append ("FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos, Acertos ");
	        buff.append ("WHERE ORdens_Servicos.oid_ordem_Servico = Acertos.oid_Ordem_Servico and Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo AND Ordens_Servicos.oid_Tipo_Servico = " +
	                     parametro_FixoED.getOID_Tipo_Servico_Acerto_Contas ());
	        buff.append (" AND Acertos.NR_Acerto =");
	        buff.append (NR_Acerto);
	      }
	      if (oid_Motorista != null && !oid_Motorista.equals ("") && !oid_Motorista.equals ("null")) {
	        buff = new StringBuffer ();
	        buff.append ("SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Ordens_Servicos.Dt_Stamp, Ordens_Servicos.Usuario_Stamp, Ordens_Servicos.Dm_Stamp, Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo ");
	        buff.append ("FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos, Acertos ");
	        buff.append ("WHERE ORdens_Servicos.oid_ordem_Servico = Acertos.oid_Ordem_Servico and Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade AND Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo AND Ordens_Servicos.oid_Tipo_Servico = " +
	                     parametro_FixoED.getOID_Tipo_Servico_Acerto_Contas ());
	        buff.append (" AND Acertos.oid_Motorista ='");
	        buff.append (oid_Motorista + "'");
	      }

	      // System.out.println (buff);

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor =
	          stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	        Ordem_ServicoBean p = new Ordem_ServicoBean ();

	        p.setOID (cursor.getInt (1));
	        p.setOID_Unidade (cursor.getInt (2));
	        p.setOID_Tipo_Servico (cursor.getInt (3));
	        p.setNR_Ordem_Servico (cursor.getInt (4));
	        p.setDT_Ordem_Servico (cursor.getString (5));
	        p.setDT_Encerramento (cursor.getString (6));
	        p.setNR_Meses_Amortizacao (cursor.getInt (7));
	        p.setNR_Kilometragem (cursor.getInt (8));
	        p.setVL_Previsto (cursor.getDouble (9));
	        p.setTX_Observacao (cursor.getString (10));
	        p.setDt_Stamp (cursor.getString (11));
	        p.setUsuario_Stamp (cursor.getString (12));
	        p.setDm_Stamp (cursor.getString (13));
	        p.setNM_Tipo_Servico (cursor.getString (14));
	        p.setCD_Tipo_Servico (cursor.getString (15));
	        p.setCD_Unidade (cursor.getString (16));
	        p.setOID_Veiculo (cursor.getString (17));
	        p.setNM_Razao_Social (" ");

	        StringBuffer buff2 = new StringBuffer ();
	        buff2.append ("SELECT oid_Acerto, NR_Acerto, NM_Razao_Social From Acertos, Pessoas WHERE Acertos.oid_Motorista = Pessoas.oid_Pessoa and Acertos.oid_ORdem_Servico = " + p.getOID ());
	        stmt = conn.createStatement ();
	        ResultSet cursor2 = stmt.executeQuery (buff2.toString ());
	        while (cursor2.next ()) {
	          p.setOID_Acerto (cursor2.getInt (1));
	          p.setNR_Acerto (cursor2.getInt (2));
	          p.setNM_Razao_Social (cursor2.getString (3));
	        }
	        cursor2.close ();

	        Ordens_Servicos_Lista.add (p);
	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	    }
	    return Ordens_Servicos_Lista;
	  }

  public static void main (String args[]) throws Exception {
    Ordem_ServicoBean pp = new Ordem_ServicoBean ();
    pp.setOID (1);
    pp.setVL_Previsto (99999999.88);
    pp.insert ();

    Ordem_ServicoBean p = getByOID (11);
    // //// System.out.println(p.getOID());
    // //// System.out.println(p.getVL_Previsto());


  }

  public void setOID_Pessoa_Fornecedor (String OID_Pessoa_Fornecedor) {
    this.OID_Pessoa_Fornecedor = OID_Pessoa_Fornecedor;
  }

  public String getOID_Pessoa_Fornecedor () {
    return OID_Pessoa_Fornecedor;
  }

  public void setNM_Fornecedor (String NM_Fornecedor) {
    this.NM_Fornecedor = NM_Fornecedor;
  }

  public String getNM_Fornecedor () {
    return NM_Fornecedor;
  }

  public void setNR_CNPJ_CPF_Fornecedor (String NR_CNPJ_CPF_Fornecedor) {
    this.NR_CNPJ_CPF_Fornecedor = NR_CNPJ_CPF_Fornecedor;
  }

  public String getNR_CNPJ_CPF_Fornecedor () {
    return NR_CNPJ_CPF_Fornecedor;
  }

  public void setNM_Servico1 (String NM_Servico1) {
    this.NM_Servico1 = NM_Servico1;
  }

  public String getNM_Servico1 () {
    return NM_Servico1;
  }

  public void setNM_Servico2 (String NM_Servico2) {
    this.NM_Servico2 = NM_Servico2;
  }

  public String getNM_Servico2 () {
    return NM_Servico2;
  }

  public void setNM_Servico3 (String NM_Servico3) {
    this.NM_Servico3 = NM_Servico3;
  }

  public String getNM_Servico3 () {
    return NM_Servico3;
  }

  public void setNM_Servico4 (String NM_Servico4) {
    this.NM_Servico4 = NM_Servico4;
  }

  public String getNM_Servico4 () {
    return NM_Servico4;
  }

  public void setNM_Servico5 (String NM_Servico5) {
    this.NM_Servico5 = NM_Servico5;
  }

  public String getNM_Servico5 () {
    return NM_Servico5;
  }

  public void setNM_Servico6 (String NM_Servico6) {
    this.NM_Servico6 = NM_Servico6;
  }

  public String getNM_Servico6 () {
    return NM_Servico6;
  }

  public void setNM_Servico7 (String NM_Servico7) {
    this.NM_Servico7 = NM_Servico7;
  }

  public String getNM_Servico7 () {
    return NM_Servico7;
  }

  public void setNM_Servico8 (String NM_Servico8) {
    this.NM_Servico8 = NM_Servico8;
  }

  public String getNM_Servico8 () {
    return NM_Servico8;
  }

  public void setNM_Servico9 (String NM_Servico9) {
    this.NM_Servico9 = NM_Servico9;
  }

  public String getNM_Servico9 () {
    return NM_Servico9;
  }

  public void setNM_Servico10 (String NM_Servico10) {
    this.NM_Servico10 = NM_Servico10;
  }

  public String getNM_Servico10 () {
    return NM_Servico10;
  }

  public void setNM_Condicao_Pagamento (String NM_Condicao_Pagamento) {
    this.NM_Condicao_Pagamento = NM_Condicao_Pagamento;
  }

  public String getNM_Condicao_Pagamento () {
    return NM_Condicao_Pagamento;
  }

  public void setOID_Pessoa_Faturado (String OID_Pessoa_Faturado) {
    this.OID_Pessoa_Faturado = OID_Pessoa_Faturado;
  }

  public String getOID_Pessoa_Faturado () {
    return OID_Pessoa_Faturado;
  }

  public int getNR_Acerto () {
    return NR_Acerto;
  }

  public void setNR_Acerto (int NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }

  public void setOID_Acerto (int OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }

  public int getOID_Acerto () {
    return OID_Acerto;
  }

  public String getOid_Carreta () {
    return oid_Carreta;
  }

  public void setOid_Carreta (String oid_Carreta) {
    this.oid_Carreta = oid_Carreta;
  }

public String getOid_Veiculo2() {
	return oid_Veiculo2;
}

public void setOid_Veiculo2(String oid_Veiculo2) {
	this.oid_Veiculo2 = oid_Veiculo2;
}

public String getOid_Veiculo3() {
	return oid_Veiculo3;
}

public void setOid_Veiculo3(String oid_Veiculo3) {
	this.oid_Veiculo3 = oid_Veiculo3;
}

public int getNR_Kilometragem2() {
	return NR_Kilometragem2;
}

public void setNR_Kilometragem2(int kilometragem2) {
	NR_Kilometragem2 = kilometragem2;
}

public int getNR_Kilometragem3() {
	return NR_Kilometragem3;
}

public void setNR_Kilometragem3(int kilometragem3) {
	NR_Kilometragem3 = kilometragem3;
}

public int getKM_Preventiva() {
	return KM_Preventiva;
}

public void setKM_Preventiva(int preventiva) {
	KM_Preventiva = preventiva;
}

public String getDM_Servico() {
	return DM_Servico;
}

public void setDM_Servico(String servico) {
	DM_Servico = servico;
}
}
