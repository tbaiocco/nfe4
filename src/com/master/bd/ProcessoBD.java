package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.ProcessoED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class ProcessoBD {
  public ProcessoBD () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);

  public ProcessoBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public ProcessoED inclui (ProcessoED ed) throws Excecoes {

    String sql = null;
    try {

      ed.setOid_Processo (String.valueOf (util.getAutoIncremento (
          "oid_Processo" , "Processos")));

      if (util.doValida(ed.getNr_Processo ())) {
        if (util.doExiste ("Processos" ,
                           "NR_Processo = '" + ed.getNr_Processo () + "'"))
          throw new Mensagens (
              "Ja existe uma Ordem de de Serviço com o Número Informado!");
      }else {
        ed.setNr_Processo (String.valueOf (util.getAutoIncremento (
            "NR_Processo" , "Processos")));
      }
      while (ed.getNr_Processo().length()<6) {
        ed.setNr_Processo("0" + ed.getNr_Processo());
      }


      sql = " INSERT INTO Processos (" +
          "      OID_Processo" +
          "     ,NR_Processo" +
          "     ,DT_STAMP" +
          "     ,USUARIO_STAMP" +
          "     ,DM_STAMP" +
          "     ,DM_TIPO_Processo" +
          "     ,OID_CONTA" +
          "     ,OID_EMPRESA" +
          "     ,OID_MOEDA" +
          "     ,OID_PESSOA" +
          "     ,DM_Situacao" +
          "     ,NM_Contato" +
          "     ,oid_Veiculo" +
          "     ,oid_Motorista" +
          "     ,DT_Abertura" +
          "     ,HR_Abertura" +
          "     ,DT_Previsao" +
          "     ,HR_Previsao" +
          "     ,DT_Encerramento" +
          "     ,HR_Encerramento" +
          "     ,NR_Odometro_Inicial" +
          "     ,NR_Odometro_Final" +
          "     ,DM_Meio_Pagamento" +
          "     ,NM_Ajudante" +
          "     ,TX_Servico" +
          "     ,DM_Tipo_Faturamento) " +
          " VALUES (" + ed.getOid_Processo () +
          ",'" + ed.getNr_Processo () + "'" +
          ",'" + ed.getDt_stamp () + "'" +
          ",'" + ed.getUsuario_Stamp () + "'" +
          ",'" + ed.getDm_Stamp () + "'" +
          ",'" + ed.getDm_Tipo_Processo () + "'" +
          "," + ed.getOid_Conta () +
          "," + ed.getOid_Empresa () +
          "," + ed.getOid_Moeda () +
          ",'" + ed.getOid_Pessoa () + "'" +
          ",'B'" +
          "," + util.getSQLString (ed.getNM_Contato ()) +
          "," + util.getSQLString (ed.getOid_Veiculo ()) +
          "," + util.getSQLString (ed.getOid_Motorista ()) +
          "," + util.getSQLDate (ed.getDT_Abertura ()) +
          "," + util.getSQLString (ed.getHR_Abertura ()) +
          "," + util.getSQLDate (ed.getDT_Previsao ()) +
          "," + util.getSQLString (ed.getHR_Previsao ()) +
          "," + util.getSQLDate (ed.getDT_Encerramento ()) +
          "," + util.getSQLString (ed.getHR_Encerramento ()) +
          "," + ed.getNR_Odometro_Inicial () +
          "," + ed.getNR_Odometro_Final () +
          "," + util.getSQLStringDef (ed.getDM_Meio_Pagamento () , "") +
          "," + util.getSQLStringDef (ed.getNM_Ajudante () , "") +
          "," + util.getSQLStringDef (ed.getTX_Servico () , "") +
          "," + util.getSQLStringDef (ed.getDM_Tipo_Faturamento () , "") +
          ")";

      executasql.executarUpdate (sql);
      return ed;
    }
    catch (Mensagens exc) {
      throw exc;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "inclui()");
    }
  }

  public void alteraSituacao (ProcessoED ed) throws Excecoes {

    String sql = null;
    try {
      if ("CANCELA".equals(ed.getNM_Situacao())) {
        ed.setDM_Situacao("C");
      }
      if ("LIBERA".equals(ed.getNM_Situacao())) {
        ed.setDM_Situacao("L");
      }
      sql = " UPDATE Processos SET ";
      sql += "      DM_Situacao = " + util.getSQLString (ed.getDM_Situacao());
      sql += "     ,DT_STAMP = " + util.getSQLDate (ed.getDt_stamp ());
      sql += "     ,USUARIO_STAMP = " + util.getSQLString (ed.getUsuario_Stamp ());
      sql += "     ,DM_STAMP = " + util.getSQLStringDef (ed.getDm_Stamp () , "");
/*
      if ("C".equals(ed.getDM_Situacao()) || "E".equals(ed.getDM_Situacao())){
        sql += "   ,DT_Encerramento = " + util.getSQLDate (ed.getDT_Encerramento ());
        sql += "   ,HR_Encerramento = " + util.getSQLString (ed.getHR_Encerramento ());
      }
*/

      sql += " WHERE oid_Processo = '" + ed.getOid_Processo () + "'";

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "alteraSituacao()");
    }
  }

  public void altera (ProcessoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " UPDATE Processos SET ";
      sql += "      NR_Processo = " + util.getSQLString (ed.getNr_Processo ());
      sql += "     ,DT_STAMP = " + util.getSQLDate (ed.getDt_stamp ());
      sql += "     ,USUARIO_STAMP = " + util.getSQLString (ed.getUsuario_Stamp ());
      sql += "     ,DM_STAMP = " + util.getSQLStringDef (ed.getDm_Stamp () , "");
      sql += "     ,DM_TIPO_Processo = " +
          util.getSQLStringDef (ed.getDm_Tipo_Processo () , "");
      sql += "     ,OID_CONTA = " + ed.getOid_Conta ();
      sql += "     ,NM_Contato = " + util.getSQLString (ed.getNM_Contato ());
      sql += "     ,oid_Veiculo = " + util.getSQLString (ed.getOid_Veiculo ());
      sql += "     ,oid_Motorista = " + util.getSQLString (ed.getOid_Motorista ());
      sql += "     ,DT_Abertura = " + util.getSQLDate (ed.getDT_Abertura ());
      sql += "     ,HR_Abertura = " + util.getSQLString (ed.getHR_Abertura ());
      sql += "     ,DT_Previsao = " + util.getSQLDate (ed.getDT_Previsao ());
      sql += "     ,HR_Previsao = " + util.getSQLString (ed.getHR_Previsao ());
      sql += "     ,NR_Odometro_Inicial = " + ed.getNR_Odometro_Inicial ();
      sql += "     ,NR_Odometro_Final = " + ed.getNR_Odometro_Final ();
      sql += "   ,DT_Encerramento = " + util.getSQLDate (ed.getDT_Encerramento ());
      sql += "   ,HR_Encerramento = " + util.getSQLString (ed.getHR_Encerramento ());

      sql += "     ,DM_Meio_Pagamento = " +
          util.getSQLStringDef (ed.getDM_Meio_Pagamento () , "");
      sql += "     ,NM_Ajudante = " +
          util.getSQLStringDef (ed.getNM_Ajudante () , "");
      sql += "     ,TX_Servico = " +
          util.getSQLStringDef (ed.getTX_Servico () , "");
      sql += "     ,DM_Tipo_Faturamento = " +
          util.getSQLStringDef (ed.getDM_Tipo_Faturamento () , "");
      sql += " WHERE oid_Processo = '" + ed.getOid_Processo () + "'";

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "altera()");
    }
  }

  public void deleta (ProcessoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = "DELETE from Movimentos_Processos WHERE oid_Processo = ";
      sql += ed.getOid_Processo();

      executasql.executarUpdate(sql);


      sql = " DELETE FROM Processos WHERE oid_Processo = ";
      sql += ed.getOid_Processo ();
      executasql.executarUpdate (sql);
      
      /*
      sql =" SELECT oid_Processo, NR_PRocesso FROM Processos Order By NR_Processo";
      ResultSet res = this.executasql.executarConsulta (sql);
      String NR_Processo="";
      while (res.next ()) {
        if (NR_Processo.equals(res.getString("NR_PRocesso"))) {
          // System.out.print("Excluir ->> " +res.getString("NR_PRocesso") );
          sql = " DELETE FROM Processos WHERE oid_Processo = ";
          sql += res.getString("oid_Processo");
          executasql.executarUpdate (sql);
        }
        NR_Processo=res.getString("NR_PRocesso");
      }
      */

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "deleta()");
    }
  }

  public ArrayList lista (ProcessoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    boolean consulta_lista = true;
    try {
      sql = " SELECT * " +
          " FROM Processos concor" +
          "     ,Pessoas pes" +
          "     ,Empresas emp" +
          "     ,Contas con" +
          "     ,Moedas moe " +
          " WHERE concor.oid_conta = con.oid_conta " +
          "   AND concor.oid_Empresa = emp.oid_Empresa" +
          "   AND concor.oid_moeda = moe.oid_moeda " +
          "   AND concor.oid_pessoa = pes.oid_pessoa ";
      if (util.doValida (ed.getOid_Processo ())){
        sql += " and concor.oid_Processo = '" + ed.getOid_Processo () + "'";
        consulta_lista = false;
      }else if (util.doValida (ed.getNr_Processo ())){
          sql += " and concor.nr_Processo = '" + ed.getNr_Processo () + "'";
          consulta_lista = false;
      }

      if (consulta_lista) {
        if (util.doValida (ed.getOid_Pessoa ()))
          sql += " and concor.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
        if (ed.getOid_Empresa () != null &&
            ed.getOid_Empresa ().intValue () > 0)
          sql += " and concor.oid_Empresa = " + ed.getOid_Empresa ();
        if (ed.getOid_Conta () != null && ed.getOid_Conta ().intValue () > 0)
          sql += " and concor.oid_Conta = " + ed.getOid_Conta ().intValue();
        if (ed.getOid_Moeda () != null && ed.getOid_Moeda ().intValue () > 0)
          sql += " and concor.oid_Moeda = " + ed.getOid_Moeda ();
        if (util.doValida (ed.getOid_Veiculo ()))
          sql += " and concor.Oid_Veiculo = '" + ed.getOid_Veiculo () + "'";
        if (util.doValida (ed.getOid_Motorista ()))
          sql += " and concor.Oid_Motorista = '" + ed.getOid_Motorista () + "'";
        if (util.doValida (ed.getDm_Tipo_Processo ()))
          sql += " and concor.Dm_Tipo_Processo = '" + ed.getDm_Tipo_Processo () +              "'";
        if (util.doValida (ed.getDM_Meio_Pagamento ()))
          sql += " and concor.DM_Meio_Pagamento = '" + ed.getDM_Meio_Pagamento () + "'";
        if (util.doValida (ed.getDM_Tipo_Faturamento ()))
          sql += " and concor.DM_Tipo_Faturamento = '" +ed.getDM_Tipo_Faturamento () + "'";
        if (util.doValida (ed.getDT_Abertura_Inicial()))
            sql += " and concor.DT_Abertura >= '" +  ed.getDT_Abertura_Inicial() + "' ";
        if (util.doValida (ed.getDT_Abertura_Final()))
            sql += " and concor.DT_Abertura <= '" +  ed.getDT_Abertura_Final() + "' ";
        if (util.doValida (ed.getDT_Previsao_Inicial()))
            sql += " and concor.DT_Previsao >= '" +  ed.getDT_Previsao_Inicial() + "' ";
        if (util.doValida (ed.getDT_Previsao_Final()))
            sql += " and concor.DT_Previsao <= '" +  ed.getDT_Previsao_Final() + "' ";

        if ("C".equals (ed.getDM_Situacao ())) {
          sql += " and concor.DM_Situacao ='C' ";
        }
        if ("B".equals (ed.getDM_Situacao ())) {
          sql += " and concor.DM_Situacao ='B' ";
        }
        if (util.doValida (ed.getDM_Ordenacao ())) {
          sql += "  ORDER BY nm_razao_social, concor.NR_Processo desc";
        }
        else {
          sql += " ORDER BY concor.NR_Processo desc";
        }
      }



      // System.out.println(sql);
      
      ResultSet res = this.executasql.executarConsulta (sql);
      ResultSet res2 = null;
      String NR_Processo="";
      while (res.next ()) {
          ProcessoED edVolta = new ProcessoED ();
          NR_Processo=res.getString ("nr_Processo");

          edVolta.setOid_Processo (res.getString ("oid_Processo"));
          edVolta.setNr_Processo (res.getString ("nr_Processo"));

          edVolta.setNr_Processo (res.getString ("nr_Processo"));
          edVolta.setDm_Tipo_Processo (res.getString ("dm_tipo_Processo"));
          // dados de conta
          edVolta.setCd_Conta (res.getString ("cd_conta"));
          edVolta.setNm_Conta (res.getString ("nm_conta"));
          edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
          edVolta.setNm_Empresa (res.getString ("nm_Empresa"));
          edVolta.setCd_Empresa (res.getString ("cd_Empresa"));
          edVolta.setOid_Empresa (new Integer (res.getInt ("oid_Empresa")));
          // dados do correntista
          edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
          edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
          edVolta.setNm_Razao_Social (res.getString ("nm_razao_social"));
          // dados da moeda
          edVolta.setOid_Moeda (new Integer (res.getInt ("oid_moeda")));
          edVolta.setCd_Moeda (res.getString ("cd_moeda"));
          edVolta.setNm_Moeda (res.getString ("nm_moeda"));

          edVolta.setNM_Contato (res.getString ("NM_Contato"));

          edVolta.setOid_Lote_Faturamento (res.getLong ("Oid_Lote_Faturamento"));

          edVolta.setOid_Veiculo (res.getString ("oid_Veiculo"));
          edVolta.setOid_Motorista (res.getString ("oid_Motorista"));
          edVolta.setDT_Abertura (new FormataDataBean ().getDT_FormataData (res.
              getString ("DT_Abertura")));
          edVolta.setHR_Abertura (res.getString ("HR_Abertura"));

          edVolta.setDT_Previsao (new FormataDataBean ().getDT_FormataData (res.
              getString ("DT_Previsao")));
          edVolta.setHR_Previsao (res.getString ("HR_Previsao"));

          edVolta.setDT_Encerramento (new FormataDataBean ().getDT_FormataData (
              res.getString ("DT_Encerramento")));
          edVolta.setHR_Encerramento (res.getString ("HR_Encerramento"));
          edVolta.setNR_Odometro_Inicial (res.getInt ("NR_Odometro_Inicial"));
          edVolta.setNR_Odometro_Final (res.getInt ("NR_Odometro_Final"));
          edVolta.setDM_Meio_Pagamento (res.getString ("DM_Meio_Pagamento"));
          edVolta.setDM_Tipo_Faturamento (res.getString ("DM_Tipo_Faturamento"));
          edVolta.setNM_Ajudante (res.getString ("NM_Ajudante"));
          edVolta.setTX_Servico (res.getString ("TX_Servico"));

          sql =" SELECT SUM (vl_lancamento) as vl_lancamento " +
               " FROM   Movimentos_Processos " +
               " WHERE  Movimentos_Processos.dm_debito_credito ='C' " +
               " AND    Movimentos_Processos.oid_processo = '" +res.getString ("oid_Processo") + "'"; 
            res2 = this.executasql.executarConsulta (sql);
            while (res2.next ()) {
              edVolta.setVL_Credito(res2.getDouble("vl_lancamento"));
              // System.out.println("CREDITO=" + res2.getDouble("vl_lancamento"));
            }

            
          sql =" SELECT SUM (vl_lancamento) as vl_lancamento " +
               " FROM   Movimentos_Processos " +
               " WHERE  Movimentos_Processos.dm_debito_credito ='D' " +
               " AND    Movimentos_Processos.oid_processo = '" +res.getString ("oid_Processo") + "'"; 
            res2 = this.executasql.executarConsulta (sql);
            while (res2.next ()) {
              edVolta.setVL_Debito(res2.getDouble("vl_lancamento"));
              // System.out.println("DEBITO=" + res2.getDouble("vl_lancamento"));
            }
           
          double vl_faturado=0;  
          sql =" SELECT SUM (vl_faturado) as vl_faturado " +
               " FROM   documentos_lotes_faturamentos " +
               " WHERE  documentos_lotes_faturamentos.oid_processo = '" +res.getString ("oid_Processo") + "'"; 
            res2 = this.executasql.executarConsulta (sql);
            while (res2.next ()) {
              vl_faturado=res2.getDouble("vl_faturado");
              // System.out.println("FATURADO=" + res2.getDouble("vl_faturado"));
            }

          edVolta.setVL_Saldo (edVolta.getVL_Credito()- vl_faturado);

          // System.out.println ("SALDO=" + edVolta.getVL_Saldo());
          
          edVolta.setDM_Liberado_Cancelar("S");
          
          if ("C".equals(edVolta.getDM_Situacao())){
            edVolta.setDM_Liberado_Cancelar("N");
          }
          
          if (edVolta.getVL_Credito () > 0 &&
              edVolta.getVL_Credito () > edVolta.getVL_Saldo ()) {
            edVolta.setDM_Liberado_Cancelar("N");
          }

          if (!consulta_lista && "S".equals(edVolta.getDM_Liberado_Cancelar())) {
            sql = " SELECT OID_Viagem " +
                  " FROM  Viagens " +
                  " WHERE Viagens.OID_Processo = " + edVolta.getOid_Processo ();
            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              edVolta.setDM_Liberado_Cancelar("N");
            }
          }

          edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

          edVolta.setNM_Situacao ("Bloqueada");
          if ("C".equals (res.getString ("DM_Situacao"))) {
            edVolta.setNM_Situacao ("Cancelada");
          }
          else {
            if ("F".equals (res.getString ("DM_Situacao"))) {
              edVolta.setNM_Situacao ("Faturada");
            }
            else {
              if ("L".equals (res.getString ("DM_Situacao"))) {
                edVolta.setNM_Situacao ("Liberada");
                if (edVolta.getVL_Saldo () == 0 && edVolta.getVL_Credito () > 0) {
                  edVolta.setNM_Situacao ("Fat.Integral");
                }
                if (edVolta.getVL_Saldo () > 0 &&
                    edVolta.getVL_Credito () > 0 &&
                    edVolta.getVL_Credito () >= edVolta.getVL_Saldo ()) {
                  edVolta.setNM_Situacao ("Fat.Parcial");
                }
              }
            }
            
          }
          boolean lista=true;
          if (consulta_lista) {
            if (!"T".equals (ed.getDM_Situacao ()) && //todas
                !"C".equals (ed.getDM_Situacao ()) && //canceladas
                !"B".equals (ed.getDM_Situacao ())) { //uma os determinada
              lista = false;
              if ("L".equals (ed.getDM_Situacao ())) {
                if ("Liberada".equals (edVolta.getNM_Situacao ()) ||
                    "Fat.Parcial".equals (edVolta.getNM_Situacao ())) {
                  lista = true;
                }
              }
              if ("P".equals (ed.getDM_Situacao ())) {
                if ("Fat.Parcial".equals (edVolta.getNM_Situacao ())) {
                  lista = true;
                }
              }
              if ("F".equals (ed.getDM_Situacao ())) {
                if ("Fat.Integral".equals (edVolta.getNM_Situacao ())) {
                  lista = true;
                }
              }
            }
          }

          if (lista) list.add (edVolta);

      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "lista()");
    }

  }


  public ArrayList lista_Fatura (ProcessoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {

      sql = "SELECT  Processos.oid_Processo, Processos.NR_Processo, Duplicatas.NR_Duplicata, Duplicatas.oid_Duplicata, Duplicatas.DT_Emissao , Duplicatas.VL_Saldo " +
          " FROM  PRocessos, documentos_lotes_faturamentos, origens_duplicatas, Duplicatas, Conhecimentos " +
          " WHERE Processos.oid_processo = documentos_lotes_faturamentos.oid_Processo " +
          " AND	documentos_lotes_faturamentos.oid_Lote_Faturamento = Conhecimentos.oid_Lote_Faturamento " +
          " AND   Conhecimentos.oid_Conhecimento = origens_duplicatas.oid_conhecimento " +
          " AND   origens_duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata " +
          " AND   PRocessos.OID_Processo = " + ed.getOid_Processo ();
      // System.out.println (sql);
      // System.out.println(sql);
      
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
          ProcessoED edVolta = new ProcessoED ();

          edVolta.setOid_Processo (res.getString ("oid_Processo"));
          edVolta.setOid_Duplicata (res.getLong ("oid_Duplicata"));
          edVolta.setNr_Processo (res.getString ("nr_Processo"));
          edVolta.setNR_Duplicata (res.getString ("NR_Duplicata"));
          edVolta.setDT_Abertura(new FormataDataBean ().getDT_FormataData (res.getString ("DT_Emissao")));
          edVolta.setVL_Saldo (res.getDouble("VL_Saldo"));
          list.add (edVolta);

      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,                          "lista()");
    }
  }

  public ProcessoED getByRecord (ProcessoED ed) throws Excecoes {

    try {
      Iterator it = this.lista (ed).iterator ();
      if (it.hasNext ())
        return (ProcessoED) it.next ();
      else return new ProcessoED ();
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecord()");
    }
  }

  private void jbInit () throws Exception {
  }

}
