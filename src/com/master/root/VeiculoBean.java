package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.iu.Opn039Bean;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

import auth.OracleConnection2;

public class VeiculoBean
    extends Transacao {
  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria ();

  private String NR_Placa;
  private String NR_Frota;
  private String TX_Observacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private String oid;
  private int oid_Modelo_Veiculo;
  private String oid_Motorista;
  private String oid_Pessoa;
  private int oid_Cidade;
  private String NM_Motorista;
  private String NM_Proprietario;
  private String NM_Modelo;
  private String NM_Tipo;
  private String DM_Tipo_Implemento;
  private String NM_Marca;
  private String NM_Cidade;
  private String NR_Rastreador;
  private String DM_Procedencia;
  private long NR_Kilometragem_Atual;
  private long NR_Eixos;
  private String CD_Estado;
  private String NR_Chassis;
  private String NR_Ano_Veiculo;
  private String NM_Cor;
  private String DM_Rastreamento;
  private String DM_Monitorado;
  private String DM_Situacao;
  private long oid_Ultima_Ocorrencia;
  private String NM_Ultima_Ocorrencia;
  private boolean DM_Veiculo_Pendente_Manutencao;
  private boolean DM_Liberacao_Licenciamento;

  private String NR_RNTRC;

  public boolean isDM_Veiculo_Pendente_Manutencao() {
	return DM_Veiculo_Pendente_Manutencao;
}

public void setDM_Veiculo_Pendente_Manutencao(
		boolean veiculo_Pendente_Manutencao) {
	DM_Veiculo_Pendente_Manutencao = veiculo_Pendente_Manutencao;
}

public VeiculoBean () {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
    oid = "";
    NR_Placa = "";
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public void setNR_Placa (String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }

  public String getNR_Frota () {
    return NR_Frota;
  }

  public void setNR_Frota (String NR_Frota) {
    this.NR_Frota = NR_Frota;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  /*
   *---------------- Bloco Padr�o para Todas Classes ------------------
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

  public int getOID_Modelo_Veiculo () {
    return oid_Modelo_Veiculo;
  }

  public void setOID_Modelo_Veiculo (int n) {
    this.oid_Modelo_Veiculo = n;
  }

  public String getOID_Motorista () {
    return oid_Motorista;
  }

  public void setOID_Motorista (String n) {
    this.oid_Motorista = n;
  }

  public String getNM_Motorista () {
    return NM_Motorista;
  }

  public void setNM_Motorista (String NM_Motorista) {
    this.NM_Motorista = NM_Motorista;
  }

  public String getOID_Pessoa () {
    return oid_Pessoa;
  }

  public void setOID_Pessoa (String n) {
    this.oid_Pessoa = n;
  }

  public String getNM_Proprietario () {
    return NM_Proprietario;
  }

  public void setNM_Proprietario (String NM_Proprietario) {
    this.NM_Proprietario = NM_Proprietario;
  }

  public int getOID_Cidade () {
    return oid_Cidade;
  }

  public void setOID_Cidade (int n) {
    this.oid_Cidade = n;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public String getNM_Modelo () {
    return NM_Modelo;
  }

  public void setNM_Modelo (String NM_Modelo) {
    this.NM_Modelo = NM_Modelo;
  }

  public String getNM_Marca () {
    return NM_Marca;
  }

  public void setNM_Marca (String NM_Marca) {
    this.NM_Marca = NM_Marca;
  }

  public String getNM_Tipo () {
    return NM_Tipo;
  }

  public void setNM_Tipo (String NM_Tipo) {
    this.NM_Tipo = NM_Tipo;
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
    /*
     * Define o insert.
     */

    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Veiculos (OID_Veiculo, OID_Cidade, OID_Modelo_Veiculo, OID_Pessoa_Proprietario, OID_Pessoa, NR_Placa, NR_Frota, TX_Observacao, DT_Stamp, Usuario_Stamp, Dm_Stamp, NR_Rastreador, DM_Rastreamento, DM_Monitorado) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID ());
      pstmt.setInt (2 , getOID_Cidade ());
      pstmt.setInt (3 , getOID_Modelo_Veiculo ());
      pstmt.setString (4 , getOID_Pessoa ());
      pstmt.setString (5 , getOID_Motorista ());
      pstmt.setString (6 , getNR_Placa ());
      pstmt.setString (7 , getNR_Frota ());
      pstmt.setString (8 , getTX_Observacao ());
      pstmt.setDate (9 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (10 , getUsuario_Stamp ());
      pstmt.setString (11 , getDm_Stamp ());
      pstmt.setString (12 , getNR_Rastreador ());
      pstmt.setString (13 , getDM_Rastreamento ());
      pstmt.setString (14 , getDM_Monitorado ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
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
    Connection conn = null;
    try {
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
    buff.append (" UPDATE Veiculos SET OID_Cidade=?, OID_Modelo_Veiculo=?, OID_Pessoa_Proprietario=?, OID_Pessoa=?, NR_Frota=?, TX_Observacao=?, NR_Rastreador=?, NR_Kilometragem_Atual=?, DM_Rastreamento=?, DM_Monitorado=? ");
    buff.append (" WHERE OID_Veiculo=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Cidade ());
      pstmt.setInt (2 , getOID_Modelo_Veiculo ());
      pstmt.setString (3 , getOID_Pessoa ());
      pstmt.setString (4 , getOID_Motorista ());
      pstmt.setString (5 , getNR_Frota ());
      pstmt.setString (6 , getTX_Observacao ());
      pstmt.setString (7 , getNR_Rastreador ());
      pstmt.setLong (8 , getNR_Kilometragem_Atual ());
      pstmt.setString (9 , getDM_Rastreamento ());
      pstmt.setString (10 , getDM_Monitorado ());
      pstmt.setString (11 , getOID ());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
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
    Connection conn = null;
    try {
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
    buff.append ("DELETE FROM Veiculos ");
    buff.append ("WHERE OID_Veiculo=?");
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
     * Faz o commit e fecha a conex�o.
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

  //*** Consulta Simples do Veiculo
   public static final VeiculoBean getByRecord (VeiculoBean ed) throws Exception {
     Connection conn = null;
     try {
       conn = OracleConnection2.getWEB ();
       conn.setAutoCommit (false);
     }
     catch (Exception e) {
       e.printStackTrace ();
       throw e;
     }

     VeiculoBean p = new VeiculoBean ();
     try {
       StringBuffer buff = new StringBuffer ();
       buff.append (" SELECT oid_Veiculo");
       buff.append ("	 ,oid_Cidade");
       buff.append ("	 ,oid_Modelo_Veiculo");
       buff.append ("	 ,oid_Pessoa_Proprietario");
       buff.append ("	 ,oid_Pessoa");
       buff.append ("	 ,NR_Placa");
       buff.append ("	 ,NR_Frota");
       buff.append ("	 ,NR_Rastreador");
       buff.append ("    ,TX_Observacao");
       buff.append (" FROM Veiculos ");
       buff.append (" WHERE 1=1 ");
       if (JavaUtil.doValida (ed.getOID ())) {
         buff.append ("    AND Veiculos.oid_Veiculo = '" + ed.getOID () + "'");
       }
       if (JavaUtil.doValida (ed.getNR_Placa ())) {
         buff.append ("    AND Veiculos.NR_Placa = '" + ed.getNR_Placa () + "'");
       }

       Statement stmt = conn.createStatement ();
       ResultSet cursor = stmt.executeQuery (buff.toString ());
       while (cursor.next ()) {
         p.setOID (cursor.getString ("oid_Veiculo"));
         p.setOID_Cidade (cursor.getInt ("oid_Cidade"));
         p.setOID_Modelo_Veiculo (cursor.getInt ("oid_Modelo_Veiculo"));
         p.setOID_Pessoa (cursor.getString ("oid_Pessoa_Proprietario"));
         p.setOID_Motorista (cursor.getString ("oid_Pessoa"));
         p.setNR_Placa (cursor.getString ("NR_Placa"));
         p.setNR_Frota (cursor.getString ("NR_Frota"));
         p.setNR_Rastreador (cursor.getString ("NR_Rastreador"));
         p.setTX_Observacao (cursor.getString ("TX_Observacao"));

         if (p.getOID_Modelo_Veiculo () > 0) {
           p.setNM_Modelo (new Utilitaria ().getTableStringValue ("NM_Modelo_Veiculo" , "Modelos_Veiculos" , "oid_Modelo_Veiculo = " + p.getOID_Modelo_Veiculo ()));
           p.setNM_Marca (new Utilitaria ().getTableStringValue ("NM_Marca_Veiculo" , "Marcas_Veiculos" , "Marcas_Veiculos.oid_Marca_Veiculo = Modelos_Veiculos.oid_Marca_Veiculo AND Modelos_Veiculos.oid_Modelo_Veiculo = " + p.getOID_Modelo_Veiculo ()));
         }
       }

       cursor.close ();
       stmt.close ();
       conn.close ();
     }
     catch (Exception e) {
       e.printStackTrace ();
       throw e;
     }
     return p;
   }

  public static final VeiculoBean getByOID (String oid) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    VeiculoBean p = new VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Veiculos.OID_Veiculo, ");
      buff.append ("   Veiculos.OID_Cidade, ");
      buff.append ("   Veiculos.OID_Modelo_Veiculo, ");
      buff.append ("   Veiculos.OID_Pessoa_Proprietario, ");
      buff.append ("   Veiculos.OID_Pessoa, ");
      buff.append ("   Veiculos.NR_Placa, ");
      buff.append ("   Veiculos.NR_Frota, ");
      buff.append ("   Veiculos.TX_Observacao,  ");
      buff.append ("   Proprietario.NM_Razao_Social,  ");
      buff.append ("   Modelos_Veiculos.NM_Modelo_Veiculo,  ");
      buff.append ("   Tipos_Veiculos.NM_Tipo_Veiculo,  ");
      buff.append ("   Marcas_Veiculos.NM_Marca_Veiculo,  ");
      buff.append ("   Veiculos.NR_Rastreador,  ");
      buff.append ("   Veiculos.NR_Kilometragem_Atual,  ");
      buff.append ("   Tipos_Veiculos.DM_Tipo_Implemento, ");
      buff.append ("   Veiculos.DM_Rastreamento,  ");
      buff.append ("   Veiculos.DM_Situacao,  ");
      buff.append ("   Veiculos.oid_Ultima_Ocorrencia,  ");
      buff.append ("   Veiculos.DM_Monitorado,  ");
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
      buff.append ("	Paises.CD_Pais ");
      buff.append ("FROM Veiculos, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises, ");
      buff.append ("     Pessoas Proprietario, ");
      buff.append ("     Modelos_Veiculos, ");
      buff.append ("     Marcas_Veiculos, ");
      buff.append ("     Tipos_Veiculos");
      buff.append (" WHERE Veiculos.oid_Pessoa_Proprietario   = Proprietario.oid_Pessoa ");
      buff.append ("   AND Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo ");
      buff.append ("   AND Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo ");
      buff.append ("   AND Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo ");
      buff.append ("   AND Veiculos.OID_Cidade = Cidades.OID_Cidade ");
      buff.append ("   AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append ("   AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append ("   AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append ("   AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append ("   AND Veiculos.OID_Veiculo = '");
      buff.append (oid);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setOID_Modelo_Veiculo (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Motorista (cursor.getString (5));
        p.setNR_Placa (cursor.getString (6));
        p.setNR_Frota (cursor.getString (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setNM_Proprietario (cursor.getString (9));
        p.setNM_Modelo (cursor.getString (10));
        p.setNM_Tipo (cursor.getString (11));
        p.setNM_Marca (cursor.getString (12));
        p.setNR_Rastreador (cursor.getString (13));
        p.setNR_Kilometragem_Atual (cursor.getLong (14));
        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
        p.setNM_Motorista (new Utilitaria ().getTableStringValue ("NM_Razao_Social" , "Pessoas" , "oid_Pessoa='" + p.getOID_Motorista () + "'"));
        p.setDM_Rastreamento (cursor.getString ("DM_Rastreamento"));
        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
        p.setOid_Ultima_Ocorrencia (cursor.getLong ("Oid_Ultima_Ocorrencia"));
        p.setDM_Monitorado (cursor.getString ("DM_Monitorado"));
        p.setNM_Cidade(cursor.getString ("NM_Cidade"));
        p.setCD_Estado(cursor.getString ("CD_Estado"));

        StringBuffer buff2 = new StringBuffer ();
        buff2.append ("SELECT Complementos_Veiculos.DM_Procedencia, NR_Ano_Veiculo, NR_Ano_Modelo, NM_Cor, NR_Eixos  ");
        buff2.append ("FROM   Complementos_Veiculos  ");
        buff2.append (" WHERE Complementos_Veiculos.oid_Complemento_Veiculo= '");
        buff2.append (oid);
        buff2.append ("'");

        Statement stmt2 = conn.createStatement ();
        ResultSet cursor2 = stmt2.executeQuery (buff2.toString ());

        while (cursor2.next ()) {
          p.setDM_Procedencia (cursor2.getString (1));
          p.setNR_Ano_Veiculo(cursor2.getString ("NR_Ano_Veiculo"));
          p.setNM_Cor(cursor2.getString ("NM_Cor"));
          p.setNR_Eixos(cursor2.getLong("NR_Eixos"));
        }

        if (p.getOid_Ultima_Ocorrencia()>0){
          buff2 = new StringBuffer ();
          buff2.append (" SELECT *   ");
          buff2.append (" FROM   Tipos_Ocorrencias_Veiculos, Ocorrencias_Veiculos  ");
          buff2.append (" WHERE Tipos_Ocorrencias_Veiculos.oid_Tipo_Ocorrencia = Ocorrencias_Veiculos.oid_Tipo_Ocorrencia ");
          buff2.append (" AND   Ocorrencias_Veiculos.oid_ocorrencia_veiculo= '");
          buff2.append (p.getOid_Ultima_Ocorrencia());
          buff2.append ("'");
          cursor2 = stmt2.executeQuery (buff2.toString ());
          while (cursor2.next ()) {
            p.setNM_Ultima_Ocorrencia (cursor2.getString("NM_Tipo_Ocorrencia") + "  (" + FormataData.formataDataBT ( cursor2.getString("dt_ocorrencia_veiculo")) +" "+ cursor2.getString("hr_ocorrencia_veiculo")+ ")" );
          }
        }

        buff2 = new StringBuffer ();
        buff2.append (" SELECT veiculos.oid_Veiculo   ");
        buff2.append (" FROM Manutencoes_Preventivas, Tipos_Servicos, Veiculos " +
        		" WHERE Manutencoes_Preventivas.OID_Veiculo = Veiculos.OID_Veiculo " +
        		" AND   Manutencoes_Preventivas.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico " +
        		" AND  (Manutencoes_Preventivas.NR_Kilometragem_Prevista - Veiculos.NR_Kilometragem_Atual) <= tipos_servicos.nr_km_aviso ");
        buff2.append (" AND  Manutencoes_Preventivas.OID_Veiculo = '");
        buff2.append (oid);
        buff2.append ("'");
        buff2.append (" limit 1");
        cursor2 = stmt2.executeQuery (buff2.toString ());
        while (cursor2.next ()) {
           p.setDM_Veiculo_Pendente_Manutencao(true);
        }
        Opn039Bean liberacao = new Opn039Bean();

        p.setDM_Liberacao_Licenciamento(liberacao.getLiberacao_Licenciamento(p.getOID()));

      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public VeiculoBean getByOID_DM_Tipo_Implemento (String oidVeiculo , String oidConjunto , String[] descTipoImplemento) throws Excecoes {
    try {
      VeiculoBean veiculo = getByOID (oidVeiculo);
      //Valida se ve�culo existe
      if (veiculo.getOID () != null && !"".equals (veiculo.getOID ())) {
        if (veiculo.getDM_Tipo_Implemento () == null) {
          throw new Excecoes ("Tipo do implemento n�o informado! Informe no cadastro do Tipo de Ve�culo");
        }
        String descTipoImplementoV = Tipo_VeiculoBean.getDescTipoImplemento (Integer.parseInt (veiculo.getDM_Tipo_Implemento ()));
        for (int i = 0; i < descTipoImplemento.length; i++) {
          if (descTipoImplemento[i].equals (descTipoImplementoV)) {
            return veiculo;
          }
        }
        throw new Excecoes ("Ve�culo informado n�o � um " + descTipoImplemento[0] + "!");
      }
      else {
        throw new Excecoes ("Registro n�o encontrado!");
      }
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "getCavaloByOID(String oid)");
    }
  }

  public static final VeiculoBean getByNR_Placa (String NR_Placa) throws Exception {
    return getByOID (NR_Placa);
  }

  public static final VeiculoBean getByNR_Frota (String NR_Frota) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    VeiculoBean p = new VeiculoBean ();

    try {
        StringBuffer buff = new StringBuffer ();
        buff.append ("SELECT Veiculos.OID_Veiculo, ");
        buff.append ("   Veiculos.OID_Cidade, ");
        buff.append ("   Veiculos.OID_Modelo_Veiculo, ");
        buff.append ("   Veiculos.OID_Pessoa_Proprietario, ");
        buff.append ("   Veiculos.OID_Pessoa, ");
        buff.append ("   Veiculos.NR_Placa, ");
        buff.append ("   Veiculos.NR_Frota, ");
        buff.append ("   Veiculos.TX_Observacao,  ");
        buff.append ("   Proprietario.NM_Razao_Social,  ");
        buff.append ("   Modelos_Veiculos.NM_Modelo_Veiculo,  ");
        buff.append ("   Tipos_Veiculos.NM_Tipo_Veiculo,  ");
        buff.append ("   Marcas_Veiculos.NM_Marca_Veiculo,  ");
        buff.append ("   Veiculos.NR_Rastreador,  ");
        buff.append ("   Veiculos.NR_Kilometragem_Atual,  ");
        buff.append ("   Tipos_Veiculos.DM_Tipo_Implemento, ");
        buff.append ("   Veiculos.DM_Rastreamento,  ");
        buff.append ("   Veiculos.DM_Situacao,  ");
        buff.append ("   Veiculos.oid_Ultima_Ocorrencia,  ");
        buff.append ("   Veiculos.DM_Monitorado,  ");
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
        buff.append ("	Paises.CD_Pais ");
        buff.append ("FROM Veiculos, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises, ");
        buff.append ("     Pessoas Proprietario, ");
        buff.append ("     Modelos_Veiculos, ");
        buff.append ("     Marcas_Veiculos, ");
        buff.append ("     Tipos_Veiculos");
        buff.append (" WHERE Veiculos.oid_Pessoa_Proprietario   = Proprietario.oid_Pessoa ");
        buff.append ("   AND Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo ");
        buff.append ("   AND Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo ");
        buff.append ("   AND Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo ");
        buff.append ("   AND Veiculos.OID_Cidade = Cidades.OID_Cidade ");
        buff.append ("   AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
        buff.append ("   AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
        buff.append ("   AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
        buff.append ("   AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
        buff.append ("   AND Veiculos.NR_Frota= '");
        buff.append (NR_Frota);
        buff.append ("'");

        Statement stmt = conn.createStatement ();
        ResultSet cursor = stmt.executeQuery (buff.toString ());

        while (cursor.next ()) {
          p.setOID (cursor.getString (1));
          p.setOID_Cidade (cursor.getInt (2));
          p.setOID_Modelo_Veiculo (cursor.getInt (3));
          p.setOID_Pessoa (cursor.getString (4));
          p.setOID_Motorista (cursor.getString (5));
          p.setNR_Placa (cursor.getString (6));
          p.setNR_Frota (cursor.getString (7));
          p.setTX_Observacao (cursor.getString (8));
          p.setNM_Proprietario (cursor.getString (9));
          p.setNM_Modelo (cursor.getString (10));
          p.setNM_Tipo (cursor.getString (11));
          p.setNM_Marca (cursor.getString (12));
          p.setNR_Rastreador (cursor.getString (13));
          p.setNR_Kilometragem_Atual (cursor.getLong (14));
          p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
          p.setNM_Motorista (new Utilitaria ().getTableStringValue ("NM_Razao_Social" , "Pessoas" , "oid_Pessoa='" + p.getOID_Motorista () + "'"));
          p.setDM_Rastreamento (cursor.getString ("DM_Rastreamento"));
          p.setDM_Situacao (cursor.getString ("DM_Situacao"));
          p.setOid_Ultima_Ocorrencia (cursor.getLong ("Oid_Ultima_Ocorrencia"));
          p.setDM_Monitorado (cursor.getString ("DM_Monitorado"));
          p.setNM_Cidade(cursor.getString ("NM_Cidade"));
          p.setCD_Estado(cursor.getString ("CD_Estado"));

        }

        cursor.close ();
        stmt.close ();
        conn.close ();
      }
      catch (Exception e) {
        e.printStackTrace ();
        throw e;
      }
      return p;
  }

  public static final List getByNR_Frota_Lista (String NR_Frota) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Veiculos.OID_Veiculo, ");
      buff.append ("	Veiculos.OID_Cidade, ");
      buff.append ("	Veiculos.OID_Modelo_Veiculo, ");
      buff.append ("	Veiculos.OID_Pessoa_Proprietario, ");
      buff.append ("	Veiculos.OID_Pessoa, ");
      buff.append ("	Veiculos.NR_Placa, ");
      buff.append ("	Veiculos.NR_Frota, ");
      buff.append ("	Veiculos.TX_Observacao,  ");
      buff.append ("	Proprietario.NM_Razao_Social,  ");
      buff.append ("	Modelos_Veiculos.NM_Modelo_Veiculo,  ");
      buff.append ("	Tipos_Veiculos.NM_Tipo_Veiculo,  ");
      buff.append ("	Marcas_Veiculos.NM_Marca_Veiculo,  ");
      buff.append ("	Tipos_Veiculos.DM_Tipo_Implemento ");
      buff.append ("FROM Veiculos, ");
      buff.append ("     Pessoas Proprietario, ");
      buff.append ("     Modelos_Veiculos, ");
      buff.append ("     Marcas_Veiculos, ");
      buff.append ("     Tipos_Veiculos ");
      buff.append (" WHERE Veiculos.oid_Pessoa_Proprietario   = Proprietario.oid_Pessoa ");
      buff.append (" AND   Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo ");
      buff.append (" AND   Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo ");
      buff.append (" AND   Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo ");
      buff.append ("AND   NR_Frota = '");
      buff.append (NR_Frota);
      buff.append ("'");
      buff.append (" ORDER BY Veiculos.OID_Veiculo ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        VeiculoBean p = new VeiculoBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setOID_Modelo_Veiculo (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Motorista (cursor.getString (5));
        p.setNR_Placa (cursor.getString (6));
        p.setNR_Frota (cursor.getString (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setNM_Proprietario (cursor.getString (9));
        p.setNM_Modelo (cursor.getString (10));
        p.setNM_Tipo (cursor.getString (11));
        p.setNM_Marca (cursor.getString (12));
        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
        Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Veiculos_Lista;
  }

  public static final List getByNR_Placa_Lista (String NR_Placa) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Veiculos.OID_Veiculo, ");
      buff.append ("	Veiculos.OID_Cidade, ");
      buff.append ("	Veiculos.OID_Modelo_Veiculo, ");
      buff.append ("	Veiculos.OID_Pessoa_Proprietario, ");
      buff.append ("	Veiculos.OID_Pessoa, ");
      buff.append ("	Veiculos.NR_Placa, ");
      buff.append ("	Veiculos.NR_Frota, ");
      buff.append ("	Veiculos.TX_Observacao,  ");
      buff.append ("	Proprietario.NM_Razao_Social,  ");
      buff.append ("	Modelos_Veiculos.NM_Modelo_Veiculo,  ");
      buff.append ("	Tipos_Veiculos.NM_Tipo_Veiculo,  ");
      buff.append ("	Marcas_Veiculos.NM_Marca_Veiculo,  ");
      buff.append ("	Tipos_Veiculos.DM_Tipo_Implemento ");
      buff.append ("FROM Veiculos, ");
      buff.append ("     Pessoas Proprietario, ");
      buff.append ("     Modelos_Veiculos, ");
      buff.append ("     Marcas_Veiculos, ");
      buff.append ("     Tipos_Veiculos ");
      buff.append (" WHERE Veiculos.oid_Pessoa_Proprietario   = Proprietario.oid_Pessoa ");
      buff.append (" AND   Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo ");
      buff.append (" AND   Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo ");
      buff.append (" AND   Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo ");
      buff.append ("AND   NR_Placa LIKE '");
      buff.append (NR_Placa);
      buff.append ("%'");
      buff.append (" ORDER BY Veiculos.OID_Veiculo ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        VeiculoBean p = new VeiculoBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setOID_Modelo_Veiculo (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Motorista (cursor.getString (5));
        p.setNR_Placa (cursor.getString (6));
        p.setNR_Frota (cursor.getString (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setNM_Proprietario (cursor.getString (9));
        p.setNM_Modelo (cursor.getString (10));
        p.setNM_Tipo (cursor.getString (11));
        p.setNM_Marca (cursor.getString (12));
        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
        Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Veiculos_Lista;
  }

  public static final List getAll () throws Exception {
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o nome do DSN
      // o nome de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Veiculos.OID_Veiculo, ");
      buff.append ("	Veiculos.OID_Cidade, ");
      buff.append ("	Veiculos.OID_Modelo_Veiculo, ");
      buff.append ("	Veiculos.OID_Pessoa_Proprietario, ");
      buff.append ("	Veiculos.OID_Pessoa, ");
      buff.append ("	Veiculos.NR_Placa, ");
      buff.append ("	Veiculos.NR_Frota, ");
      buff.append ("	Veiculos.TX_Observacao,  ");
      buff.append ("	Proprietario.NM_Razao_Social,  ");
      buff.append ("	Modelos_Veiculos.NM_Modelo_Veiculo,  ");
      buff.append ("	Tipos_Veiculos.NM_Tipo_Veiculo,  ");
      buff.append ("	Marcas_Veiculos.NM_Marca_Veiculo,  ");
      buff.append ("	Tipos_Veiculos.DM_Tipo_Implemento ");
      buff.append ("FROM Veiculos, ");
      buff.append ("     Pessoas Proprietario, ");
      buff.append ("     Modelos_Veiculos, ");
      buff.append ("     Marcas_Veiculos, ");
      buff.append ("     Tipos_Veiculos ");
      buff.append (" WHERE Veiculos.oid_Pessoa_Proprietario   = Proprietario.oid_Pessoa ");
      buff.append (" AND   Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo ");
      buff.append (" AND   Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo ");
      buff.append (" AND   Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo ");
      buff.append (" ORDER BY Modelos_Veiculos.NM_Modelo_Veiculo, Veiculos.OID_Veiculo ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        VeiculoBean p = new VeiculoBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setOID_Modelo_Veiculo (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Motorista (cursor.getString (5));
        p.setNR_Placa (cursor.getString (6));
        p.setNR_Frota (cursor.getString (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setNM_Proprietario (cursor.getString (9));
        p.setNM_Modelo (cursor.getString (10));
        p.setNM_Tipo (cursor.getString (11));
        p.setNM_Marca (cursor.getString (12));
        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
        Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Veiculos_Lista;
  }

  public void geraRelVeiculos (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
    String DM_Layout = request.getParameter ("FT_DM_Layout");
    List lista = lista (request);
  }

  public byte[] Imprime_Check_List (HttpServletRequest request) throws Excecoes {

    String oid_Veiculo = request.getParameter ("oid_Veiculo");

    String sql = null;
    byte[] b = null;

    try {

      sql = "Select " +
          " Veiculos.NR_PLACA," +
          " Modelos_Veiculos.CD_MODELO_VEICULO," +
          " Modelos_Veiculos.NM_MODELO_VEICULO," +
          " Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
          " Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
          " Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
          " Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
          " Pessoa_Proprietario.NR_TELEFONE as NR_TELEFONE, " +
          " Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
          " Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
          " FROM " +
          " Veiculos, Pessoas Pessoa_Proprietario, " +
          " Cidades Cidade_Proprietario, " +
          " Regioes_Estados Regiao_Estado_Proprietario, " +
          " Estados Estado_Proprietario, " +
          " Modelos_Veiculos, " +
          " Cidades Cidade_Veiculo, " +
          " Regioes_Estados Regiao_Estado_Veiculo, " +
          " Estados Estado_Veiculo " +
          " WHERE Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.OID_Pessoa " +
          " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
          " AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
          " AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
          " AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
          " AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
          " and Veiculos.oid_Veiculo = '" + oid_Veiculo + "'";

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


    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
    return b;

  }

  public byte[] Imprime_Check_List_Coleta (HttpServletRequest request) throws Excecoes {

    String oid_Coleta = request.getParameter ("oid_Coleta");

    String sql = null;
    byte[] b = null;

    try {

      sql = "Select " +
          " Coletas.oid_Unidade," +
          " Coletas.NR_Coleta," +
          " Coletas.oid_Motorista," +
          " Coletas.oid_pessoa," +
          " Coletas.nm_documento," +
          " Coletas.nm_cidade_destinatario," +
          " Coletas.nm_destinatario," +
          " Veiculos.NR_PLACA," +
          " Modelos_Veiculos.CD_MODELO_VEICULO," +
          " Modelos_Veiculos.NM_MODELO_VEICULO," +
          " Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
          " Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
          " Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
          " Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
          " Pessoa_Proprietario.NR_TELEFONE as NR_TELEFONE, " +
          " Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
          " Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
          " FROM " +
          " Veiculos, Pessoas Pessoa_Proprietario, Coletas, " +
          " Cidades Cidade_Proprietario, " +
          " Regioes_Estados Regiao_Estado_Proprietario, " +
          " Estados Estado_Proprietario, " +
          " Modelos_Veiculos, " +
          " Cidades Cidade_Veiculo, " +
          " Regioes_Estados Regiao_Estado_Veiculo, " +
          " Estados Estado_Veiculo " +
          " WHERE Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.OID_Pessoa " +
          " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
          " AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
          " AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
          " AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
          " AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
          " AND Veiculos.OID_Veiculo = Coletas.OID_Veiculo " +
          " and Coletas.oid_Coleta = '" + oid_Coleta + "'";

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


    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
    return b;

  }

  public void Imprime_Ficha (javax.servlet.http.HttpServletRequest request , javax.servlet.http.HttpServletResponse res) {

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    long oid_Unidade = Parametro_FixoED.getInstancia ().getOID_Unidade_Faturamento();

    if (request.getParameter ("oid_Unidade") != null && !"null".equals(request.getParameter ("oid_Unidade")) &&  !"".equals(request.getParameter ("oid_Unidade"))) {
    	oid_Unidade = new Long (request.getParameter ("oid_Unidade")).longValue ();
    }

    String sql = null;

    try {

      sql = "Select " +
          " Veiculos.NR_PLACA," +
          " Modelos_Veiculos.CD_MODELO_VEICULO," +
          " Modelos_Veiculos.NM_MODELO_VEICULO," +
          " Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
          " Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
          " Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
          " Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
          " Pessoa_Proprietario.NR_TELEFONE as NR_TELEFONE, " +
          " Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
          " Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
          " FROM " +
          " Veiculos, Pessoas Pessoa_Proprietario, " +
          " Cidades Cidade_Proprietario, " +
          " Regioes_Estados Regiao_Estado_Proprietario, " +
          " Estados Estado_Proprietario, " +
          " Modelos_Veiculos, " +
          " Cidades Cidade_Veiculo, " +
          " Regioes_Estados Regiao_Estado_Veiculo, " +
          " Estados Estado_Veiculo " +
          " WHERE Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.OID_Pessoa " +
          " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
          " AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
          " AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
          " AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
          " AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
          " and Veiculos.oid_Veiculo = '" + oid_Veiculo + "'";

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


    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }

  }

  public void Imprime_Ficha_Manutencao (javax.servlet.http.HttpServletRequest request , javax.servlet.http.HttpServletResponse res) {

    String oid_Veiculo = request.getParameter ("oid_Veiculo");

    String sql = null;

    try {

      sql = "Select " +
          " Veiculos.NR_PLACA," +
          " Modelos_Veiculos.CD_MODELO_VEICULO," +
          " Modelos_Veiculos.NM_MODELO_VEICULO," +
          " Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
          " Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
          " Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
          " Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
          " Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
          " Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
          " Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
          " FROM " +
          " Veiculos, Pessoas Pessoa_Proprietario, " +
          " Cidades Cidade_Proprietario, " +
          " Regioes_Estados Regiao_Estado_Proprietario, " +
          " Estados Estado_Proprietario, " +
          " Modelos_Veiculos, " +
          " Cidades Cidade_Veiculo, " +
          " Regioes_Estados Regiao_Estado_Veiculo, " +
          " Estados Estado_Veiculo " +
          " WHERE Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.OID_Pessoa " +
          " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
          " AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
          " AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
          " AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
          " AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
          " AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
          " and Veiculos.oid_Veiculo = '" + oid_Veiculo + "'";

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


    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
  }

  public String getNR_Rastreador () {
    return NR_Rastreador;
  }

  public void setNR_Rastreador (String NR_Rastreador) {
    this.NR_Rastreador = NR_Rastreador;
  }

  public void setDM_Procedencia (String DM_Procedencia) {
    this.DM_Procedencia = DM_Procedencia;
  }

  public String getDM_Procedencia () {
    return DM_Procedencia;
  }

  public void setNR_Kilometragem_Atual (long NR_Kilometragem_Atual) {
    this.NR_Kilometragem_Atual = NR_Kilometragem_Atual;
  }

  public long getNR_Kilometragem_Atual () {
    return NR_Kilometragem_Atual;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public String getNR_Chassis () {
    return NR_Chassis;
  }

  public void setNR_Chassis (String NR_Chassis) {
    this.NR_Chassis = NR_Chassis;
  }

  public String getNR_Ano_Veiculo () {
    return NR_Ano_Veiculo;
  }

  public void setNR_Ano_Veiculo (String NR_Ano_Veiculo) {
    this.NR_Ano_Veiculo = NR_Ano_Veiculo;
  }

  public String getDM_Tipo_Implemento () {
    return DM_Tipo_Implemento;
  }

  public void setDM_Tipo_Implemento (String tipo_Implemento) {
    DM_Tipo_Implemento = tipo_Implemento;
  }

  public String getNM_Tipo_Implemento () {
    return getDM_Tipo_Implemento () == null ? "" : Tipo_VeiculoBean.getDescTipoImplemento (Integer.parseInt (DM_Tipo_Implemento));
  }

  public void atualizaKMAtual (String oid_Veiculo , long kmAtual , ExecutaSQL sql) throws Excecoes {
    if (JavaUtil.doValida (oid_Veiculo)) {
      String sqlUpdate = "update Veiculos set NR_Kilometragem_Atual = " + kmAtual + " where oid_Veiculo = '" + oid_Veiculo + "'";
      try {
        sql.executarUpdate (sqlUpdate);
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "atualizaKMAtual(String oid_Veiculo, long kmAtual)");
      }
    }
    else {
      throw new Excecoes ("Ve�culo n�o informado!" , null , this.getClass ().getName () , "atualizaKMAtual(String oid_Veiculo, long kmAtual)");
    }
  }

  public List lista (HttpServletRequest request) throws Excecoes {
    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String NR_Frota = request.getParameter ("FT_NR_Frota");
    String oid_Tipo_Veiculo = request.getParameter ("oid_Tipo_Veiculo");
    String oid_Modelo_Veiculo = request.getParameter ("oid_Modelo_Veiculo");
    String oid_Marca_Veiculo = request.getParameter ("oid_Marca_Veiculo");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String oid_Proprietario = request.getParameter ("oid_Proprietario");
    String DM_Procedencia = request.getParameter ("FT_DM_Procedencia");
    String DM_Rastreamento = request.getParameter ("FT_DM_Rastreamento");
    String DM_Layout = request.getParameter ("FT_DM_Layout");

    String sqlBusca =
        "SELECT Veiculos.OID_Veiculo, " +
        "	Veiculos.OID_Cidade, " +
        "	Veiculos.OID_Modelo_Veiculo, " +
        "	Veiculos.OID_Pessoa_Proprietario, " +
        "	Veiculos.OID_Pessoa, " +
        "	Veiculos.NR_Placa, " +
        "	Veiculos.NR_Frota, " +
        "	Veiculos.NR_Rastreador, " +
        "	Veiculos.TX_Observacao, " +
        "       Complementos_Veiculos.DM_Procedencia, " +
        "   Complementos_Veiculos.NR_Chassis, " +
        "   Complementos_Veiculos.NR_Ano_Veiculo, " +
        "       Complementos_Veiculos.NM_Cor, " +
        "	Proprietario.NM_Razao_Social, " +
        "	Modelos_Veiculos.NM_Modelo_Veiculo, " +
        "	Tipos_Veiculos.NM_Tipo_Veiculo, " +
        "	Marcas_Veiculos.NM_Marca_Veiculo, " +
        "	Tipos_Veiculos.DM_Tipo_Implemento, " +
        "   Cidades.NM_Cidade, " +
        "   Estados.CD_Estado, " +
        "   Motorista.NM_Razao_Social As NM_Motorista " +
        "FROM Veiculos " +
        "     left join Complementos_Veiculos " +
        "       on Veiculos.OID_Veiculo = Complementos_Veiculos.OID_Veiculo " +
        "     left join Pessoas Motorista " +
        "       on Veiculos.oid_Pessoa = Motorista.oid_Pessoa, " +
        "     Pessoas Proprietario, " +
        "     Modelos_Veiculos, " +
        "     Marcas_Veiculos, " +
        "     Tipos_Veiculos, " +
        "     Cidades, Regioes_Estados, " +
        "     Estados ";
    sqlBusca +=
        " WHERE Veiculos.oid_Pessoa_Proprietario   = Proprietario.oid_Pessoa " +
        "   AND Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo " +
        "   AND Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo " +
        "   AND Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo " +
        "   and Veiculos.OID_Cidade = Cidades.OID_Cidade " +
        "   and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
        "   and Regioes_Estados.OID_Estado = Estados.OID_Estado ";
    if (JavaUtil.doValida (oid_Veiculo)) {
      sqlBusca += "   AND Veiculos.OID_Veiculo = '" + oid_Veiculo + "' ";
    }
    else {
      if (JavaUtil.doValida (DM_Procedencia)) {
        if ("PA".equals (DM_Procedencia)) {
          sqlBusca += "  AND (Complementos_Veiculos.DM_Procedencia ='P' OR" +
              "       Complementos_Veiculos.DM_Procedencia ='A' )";
        }
        else {
          sqlBusca += "  AND Complementos_Veiculos.DM_Procedencia ='" + DM_Procedencia + "'";
        }
      }
      if (JavaUtil.doValida (NR_Frota)) {
        sqlBusca += "   AND Veiculos.NR_Frota = '" + NR_Frota + "' ";
      }
      if (JavaUtil.doValida (oid_Modelo_Veiculo)) {
        sqlBusca += "   AND Veiculos.oid_Modelo_Veiculo = '" + oid_Modelo_Veiculo + "' ";
      }
      if (JavaUtil.doValida (oid_Tipo_Veiculo)) {
        sqlBusca += "   AND Modelos_Veiculos.oid_Tipo_Veiculo = '" + oid_Tipo_Veiculo + "' ";
      }
      if (JavaUtil.doValida (DM_Rastreamento) && !"".equals (DM_Rastreamento)) {
        sqlBusca += "   AND Veiculos.DM_Rastreamento = '" + DM_Rastreamento + "' ";
      }
      if (JavaUtil.doValida (oid_Marca_Veiculo)) {
        sqlBusca += "   AND Modelos_Veiculos.oid_Marca_Veiculo = '" + oid_Marca_Veiculo + "' ";
      }
      if (JavaUtil.doValida (oid_Motorista)) {
        sqlBusca += "   AND Veiculos.OID_Pessoa = '" + oid_Motorista + "' ";
      }
      if (JavaUtil.doValida (oid_Proprietario)) {
        sqlBusca += "   AND Veiculos.OID_Pessoa_Proprietario = '" + oid_Proprietario + "' ";
      }
      if ("2".equals (DM_Layout)) {
        sqlBusca += " ORDER BY Modelos_Veiculos.NM_Modelo_Veiculo, Veiculos.OID_Veiculo ";
      }
      if ("3".equals (DM_Layout)) {
        sqlBusca += " ORDER BY Motorista.NM_Razao_Social, Modelos_Veiculos.NM_Modelo_Veiculo, Veiculos.OID_Veiculo ";
      }
      if ("4".equals (DM_Layout)) {
        sqlBusca += " ORDER BY Tipos_Veiculos.NM_Tipo_Veiculo, Veiculos.OID_Veiculo ";
      }
    }

    this.inicioTransacao ();
    try {
      ResultSet res = sql.executarConsulta (sqlBusca);
      try {
        List toReturn = new ArrayList ();
        while (res.next ()) {
          VeiculoBean veiculoBean = new VeiculoBean ();
          veiculoBean.setOID (res.getString ("OID_Veiculo"));
          veiculoBean.setOID_Cidade (res.getInt ("OID_Cidade"));
          veiculoBean.setOID_Modelo_Veiculo (res.getInt ("OID_Modelo_Veiculo"));
          veiculoBean.setOID_Pessoa (res.getString ("OID_Pessoa_Proprietario"));
          veiculoBean.setOID_Motorista (res.getString ("OID_Pessoa"));
          veiculoBean.setNM_Motorista (res.getString ("NM_Motorista"));
          veiculoBean.setNM_Cor (res.getString ("NM_Cor"));
          veiculoBean.setNM_Tipo (res.getString ("NM_Tipo_Veiculo"));
          veiculoBean.setNR_Placa (res.getString ("NR_Placa"));
          veiculoBean.setNR_Rastreador (res.getString ("NR_Rastreador"));
          veiculoBean.setDM_Procedencia (res.getString ("DM_Procedencia"));
          veiculoBean.setNR_Frota (res.getString ("NR_Frota"));
          veiculoBean.setTX_Observacao (res.getString ("TX_Observacao"));
          veiculoBean.setNM_Proprietario (res.getString ("NM_Razao_Social"));
          veiculoBean.setNM_Modelo (res.getString ("NM_Modelo_Veiculo"));
          veiculoBean.setNM_Tipo (res.getString ("NM_Tipo_Veiculo"));
          veiculoBean.setNM_Marca (res.getString ("NM_Marca_Veiculo"));
          veiculoBean.setDM_Tipo_Implemento (res.getString ("DM_Tipo_Implemento"));
          veiculoBean.setNM_Cidade (res.getString ("NM_Cidade"));
          veiculoBean.setCD_Estado (res.getString ("CD_Estado"));
          veiculoBean.setNR_Chassis (res.getString ("NR_Chassis"));
          veiculoBean.setNR_Ano_Veiculo (res.getString ("NR_Ano_Veiculo"));
          toReturn.add (veiculoBean);
        }
        return toReturn;
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista(HttpServletRequest request)");
      }
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public void setNM_Cor (String NM_Cor) {
    this.NM_Cor = NM_Cor;
  }

  public String getNM_Cor () {
    return NM_Cor;
  }

  public void setDM_Rastreamento (String DM_Rastreamento) {
    this.DM_Rastreamento = DM_Rastreamento;
  }

  public String getDM_Rastreamento () {
    return DM_Rastreamento;
  }

  public void setDM_Monitorado (String DM_Monitorado) {
    this.DM_Monitorado = DM_Monitorado;
  }

  public String getDM_Monitorado () {
    return DM_Monitorado;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setOid_Ultima_Ocorrencia (long oid_Ultima_Ocorrencia) {
    this.oid_Ultima_Ocorrencia = oid_Ultima_Ocorrencia;
  }

  public void setNM_Ultima_Ocorrencia (String NM_Ultima_Ocorrencia) {
    this.NM_Ultima_Ocorrencia = NM_Ultima_Ocorrencia;
  }

  public long getOid_Ultima_Ocorrencia () {
    return oid_Ultima_Ocorrencia;
  }

  public String getNM_Ultima_Ocorrencia () {
    return NM_Ultima_Ocorrencia;
  }



  public byte[] geraRelOcorrencia_Veiculos (javax.servlet.http.HttpServletRequest request ,
                                            javax.servlet.http.HttpServletResponse res) {

    byte[] b = null;
    try {

      String DM_Procedencia = request.getParameter ("FT_DM_Procedencia");
      String oid_Tipo_Ocorrencia = request.getParameter ("oid_Tipo_Ocorrencia");
      String DT_Ocorrencia_Inicial = request.getParameter ("FT_DT_Ocorrencia_Inicial");
      String DT_Ocorrencia_Final = request.getParameter ("FT_DT_Ocorrencia_Final");
      String oid_Veiculo = request.getParameter ("oid_Veiculo");

      String sql = null;
      sql =
          "SELECT * FROM Ocorrencias_Veiculos, Tipos_Ocorrencias_Veiculos, Veiculos, Complementos_Veiculos " +
          "WHERE  Ocorrencias_Veiculos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias_Veiculos.OID_Tipo_Ocorrencia " +
          "AND    Ocorrencias_Veiculos.OID_Veiculo = Veiculos.OID_Veiculo " +
          "AND    Complementos_Veiculos.OID_Veiculo = Veiculos.OID_Veiculo ";

      if (!"".equals(DM_Procedencia)){
        sql += " and Complementos_Veiculos.DM_Procedencia = '" + DM_Procedencia + "'";
      }

      if (oid_Veiculo != null && !oid_Veiculo.equals ("") && !oid_Veiculo.equals ("null")) {
        sql += " and Ocorrencias_Veiculos.oid_Veiculo = '" + oid_Veiculo + "'";
      }

      if (oid_Tipo_Ocorrencia != null && !oid_Tipo_Ocorrencia.equals ("") && !oid_Tipo_Ocorrencia.equals ("null")) {
        sql += " and Ocorrencias_Veiculos.oid_Tipo_Ocorrencia = " + oid_Tipo_Ocorrencia;
      }

      if (DT_Ocorrencia_Inicial != null && !DT_Ocorrencia_Inicial.equals ("") && !DT_Ocorrencia_Inicial.equals ("null")) {
        sql += " and Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo >= '" + DT_Ocorrencia_Inicial + "'";
      }

      if (DT_Ocorrencia_Final != null && !DT_Ocorrencia_Final.equals ("") && !DT_Ocorrencia_Final.equals ("null")) {
        sql += " and Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo <= '" + DT_Ocorrencia_Final + "'";
      }

      sql += " Order by Ocorrencias_Veiculos.oid_Veiculo, Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo";

      // System.out.println(sql);

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



    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }

    return b;

  }

public long getNR_Eixos() {
	return NR_Eixos;
}

public void setNR_Eixos(long eixos) {
	NR_Eixos = eixos;
}

public int getOid_Cidade() {
	return oid_Cidade;
}

public void setOid_Cidade(int oid_Cidade) {
	this.oid_Cidade = oid_Cidade;
}

public int getOid_Modelo_Veiculo() {
	return oid_Modelo_Veiculo;
}

public void setOid_Modelo_Veiculo(int oid_Modelo_Veiculo) {
	this.oid_Modelo_Veiculo = oid_Modelo_Veiculo;
}

public String getOid_Motorista() {
	return oid_Motorista;
}

public void setOid_Motorista(String oid_Motorista) {
	this.oid_Motorista = oid_Motorista;
}

public String getOid_Pessoa() {
	return oid_Pessoa;
}

public void setOid_Pessoa(String oid_Pessoa) {
	this.oid_Pessoa = oid_Pessoa;
}

public boolean isDM_Liberacao_Licenciamento() {
	return DM_Liberacao_Licenciamento;
}

public void setDM_Liberacao_Licenciamento(boolean liberacao_Licenciamento) {
	DM_Liberacao_Licenciamento = liberacao_Licenciamento;
}
public String getNR_RNTRC() {
	return NR_RNTRC;
}

public void setNR_RNTRC(String nr_rntrc) {
	NR_RNTRC = nr_rntrc;
}
}
