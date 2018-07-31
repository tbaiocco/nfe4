package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.DespachanteED;
import com.master.ed.Processo_AduaneiroED;
import com.master.iu.DespachanteBean;
import com.master.rl.Processo_AduaneiroRL;
import com.master.root.PessoaBean;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class Processo_AduaneiroBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria();

  public Processo_AduaneiroBD (ExecutaSQL sql) {
    this.executasql = sql;
  }


  public Processo_AduaneiroED inclui(Processo_AduaneiroED ed) throws Excecoes {

//  // System.out.println(" bd 1");

  String sql = null;
  int oid = 0;

  try {
      
      ResultSet res = this.executasql.executarConsulta("Select count(OID_Processo_Aduaneiro) as result from Processos_Aduaneiros " +
      		                                           " where oid_conhecimento= '" + ed.getOid_Conhecimento() + "'");
      while(res.next()) oid = res.getInt("result");
      ed.setOid_Processo_Aduaneiro(ed.getOid_Conhecimento()+String.valueOf(oid+1));

	  sql = " INSERT INTO Processos_Aduaneiros (" +
    		"OID_Processo_Aduaneiro, " +
    		"OID_Conhecimento, " +
            "DT_Emissao, " +
            "HR_Emissao, " +
            "NR_Fatura, " +
            "NR_Nota_Fiscal, " +
            "NR_SD, " +
            "NR_RE, " +
            "oid_corretora, "+
            "oid_despachante, "+
            "oid_aduana, "+
            "DT_Validade, " +
            "DT_Chegada, " +
            "DT_Solicitacao_Cruze, " +
            "DT_Liberacao, " +
            "DT_Lancamento, " +
            "DT_Cruze, " +
            "DT_Encerramento, " +
            "TX_Observacao, nm_volume) values ";

	  sql += "('" + ed.getOid_Processo_Aduaneiro() +
			 "','" + ed.getOid_Conhecimento() +
			 "','" + ed.getDT_Emissao() +
			 "','" + ed.getHR_Emissao() +
			 "','" + ed.getNR_Fatura() +
			 "','" + ed.getNR_Nota_Fiscal() +
			 "','" + ed.getNR_SD() +
			 "','" + ed.getNR_RE() +
			 "'," + ed.getOid_Corretora() + 
             "," + ed.getOid_Despachante() +
			 "," + ed.getOid_Aduana() +
             ",'" + ed.getDT_Validade() +
             "','" + ed.getDT_Chegada() +
             "','" + ed.getDT_Solicitacao_Cruze() +
			 "','" + ed.getDT_Liberacao() +
			 "','" + ed.getDT_Lancamento() +
			 "','" + ed.getDT_Cruze() +
			 "','" + ed.getDT_Encerramento() +
			 "','" + ed.getTX_Observacao() + 
			 "','" + ed.getNM_Volumes_Observacao() + "')";

        // System.out.println("sql->" + sql);

        this.executasql.executarUpdate (sql);

  }

  catch (Exception exc) {
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Processo_Aduaneiro");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
  }
  return ed;
}



  public Processo_AduaneiroED getByRecord(Processo_AduaneiroED ed)throws Excecoes {
    Processo_AduaneiroED edVolta = new Processo_AduaneiroED();

    try {
        ResultSet res = this.executasql.executarConsulta(montaSql(ed));
        while (res.next()){
          edVolta = carregaED(res);
        }
        
    } catch(SQLException exc){
        throw new Excecoes(exc.getMessage(), exc,  this.getClass().getName(), "getByRecord()");
    } catch(Exception exc){
        throw new Excecoes(exc.getMessage(), exc,  this.getClass().getName(), "getByRecord()");
    }
    return edVolta;
}

  private Processo_AduaneiroED carregaED(ResultSet res)throws Excecoes {
    Processo_AduaneiroED edVolta = new Processo_AduaneiroED();
    ResultSet resLocal = null;
    String sqlBusca = "";

    try {
          // System.out.println ("carregaED 2 ");

          edVolta.setOid_Processo_Aduaneiro(res.getString("OID_Processo_Aduaneiro"));
          edVolta.setOid_Conhecimento(res.getString("oid_Conhecimento"));
          edVolta.setNR_Conhecimento(res.getLong("nr_Conhecimento"));
          String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
          String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
								  res.getString("NR_Permisso") + ".";
          int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
          for(int a = 0 ; a < completa_Nr_CRT ; a++){
              nr_CRT_Parcial = nr_CRT_Parcial + "0";
          }
          edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
          
          edVolta.setDM_Tipo("EXPORTA��O");
          if(JavaUtil.doValida(res.getString("CD_Pais")) && res.getString("CD_Pais").equals("BR")) 
              edVolta.setDM_Tipo("IMPORTA��O");
          
          edVolta.setOid_Pessoa_Remetente(res.getString("OID_PessoaE"));
          edVolta.setOid_Pessoa_Destinatario(res.getString("OID_PessoaI"));
          edVolta.setDT_Emissao(FormataData.formataDataBT(res.getString ("DT_Emissao")));
          edVolta.setHR_Emissao(res.getString ("HR_Emissao"));
          
          edVolta.setOid_Despachante(res.getInt("OID_Despachante"));
          DespachanteED edDespachante = new DespachanteBean().getByOid(res.getString("OID_Despachante"));
          edVolta.setNM_Despachante(edDespachante.getNM_Despachante());
          edVolta.setNR_CNPJ_CPF_Despachante(PessoaBean.getByOID(edDespachante.getOid_Pessoa()).getNR_CNPJ_CPF());
          edVolta.setNR_Telefone_Despachante(PessoaBean.getByOID(edDespachante.getOid_Pessoa()).getNR_Telefone());
          edVolta.setNR_Fax_Despachante(PessoaBean.getByOID(edDespachante.getOid_Pessoa()).getNR_Fax());
  		  
  		  edVolta.setOid_Corretora(res.getInt("OID_Corretora"));
  		  edDespachante = new DespachanteBean().getByOid(res.getString("OID_Corretora"));
  		  edVolta.setNM_Corretora(edDespachante.getNM_Despachante());
          
          edVolta.setOid_Aduana(res.getInt("OID_Aduana"));
          
          edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
          
          edVolta.setNR_Fatura(res.getString("NR_Fatura"));
          edVolta.setNR_Nota_Fiscal(res.getString("NR_Nota_Fiscal"));
          edVolta.setNR_SD(res.getString("NR_SD"));
          edVolta.setNR_RE(res.getString("NR_RE"));

          edVolta.setDT_Validade(FormataData.formataDataBT(res.getString ("DT_Validade")));
          edVolta.setDT_Chegada(FormataData.formataDataBT(res.getString ("DT_Chegada")));
          edVolta.setDT_Solicitacao_Cruze(FormataData.formataDataBT(res.getString ("DT_Solicitacao_Cruze")));
          edVolta.setDT_Liberacao(FormataData.formataDataBT(res.getString ("DT_Liberacao")));
          edVolta.setDT_Lancamento(FormataData.formataDataBT(res.getString ("DT_Lancamento")));
          edVolta.setDT_Cruze(FormataData.formataDataBT(res.getString ("DT_Cruze")));
          edVolta.setDT_Encerramento(FormataData.formataDataBT(res.getString ("DT_Encerramento")));
          edVolta.setNM_Volumes_Observacao(res.getString("NM_volume"));
          
          edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_SocialE"));
          edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_SocialI"));
          edVolta.setOid_Unidade(res.getLong("oid_unidade"));
          sqlBusca = "select pessoas.nm_fantasia, pessoas.nm_razao_social " +
          		   " from unidades, pessoas " +
          		   " where unidades.oid_pessoa = pessoas.oid_pessoa " +
          		   " and unidades.oid_unidade = "+ res.getInt("OID_UNIDADE");
          resLocal = this.executasql.executarConsulta(sqlBusca);
          while(resLocal.next()){
          	//edVolta.setNM_Fantasia(resLocal.getString("nm_fantasia"));
          	edVolta.setNM_Fantasia(resLocal.getString("nm_razao_social"));
          }
          
          edVolta.setNR_Volumes_Observacao(res.getLong("nr_volumes_observacao"));
          edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
          edVolta.setVL_Peso(res.getDouble("VL_Peso"));
          edVolta.setVL_Peso_Cubado(res.getDouble("VL_Peso_Cubado"));
                    

    } catch(SQLException exc){
        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "carregaED()");
    } catch(Exception exc){
        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "carregaED()");
    }
    return edVolta;
}

  private String montaSql(Processo_AduaneiroED ed )throws Excecoes {

    String sql =
          " SELECT Processos_Aduaneiros.*, " +
          "        Conhecimentos_Internacionais.nr_conhecimento, " +
          "        Conhecimentos_Internacionais.oid_unidade    , " +
          "        Conhecimentos_Internacionais.nr_volumes_observacao    , " +
          "        Conhecimentos_Internacionais.VL_Nota_Fiscal    , " +
          "        Conhecimentos_Internacionais.VL_Peso    , " +
          "        Conhecimentos_Internacionais.VL_Peso_Cubado    , " +
          "        Conhecimentos_Internacionais.CD_Pais    , " +
          "        Conhecimentos_Internacionais.NR_Permisso    , " +
          "        PessoasE.NM_Razao_Social as NM_Razao_SocialE, " +
          "        PessoasI.NM_Razao_Social as NM_Razao_SocialI, " +
          "        PessoasE.oid_pessoa as oid_pessoaE          , " +
          "        PessoasI.oid_pessoa as oid_pessoaI            " +
          " FROM Processos_Aduaneiros, Pessoas PessoasE, Pessoas PessoasI, Conhecimentos_Internacionais " +
          " WHERE Processos_Aduaneiros.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento " +
          " AND Conhecimentos_Internacionais.OID_Pessoa = PessoasE.OID_Pessoa " +
          " AND Conhecimentos_Internacionais.OID_Pessoa_Destinatario = PessoasI.OID_Pessoa ";

      if (JavaUtil.doValida(ed.getOid_Processo_Aduaneiro())) {
        sql += " and Processos_Aduaneiros.OID_Processo_Aduaneiro = '" + ed.getOid_Processo_Aduaneiro() + "'";
      }
      if (JavaUtil.doValida(ed.getOid_Conhecimento())) {
        sql += " and Processos_Aduaneiros.Oid_Conhecimento = '" + ed.getOid_Conhecimento() + "'";
      }
      // System.out.println(sql);
      return sql;
}

  public ArrayList Lista_Processo_Aduaneiro (Processo_AduaneiroED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    try {
        ResultSet res = this.executasql.executarConsulta(montaSql(ed));
        while (res.next()){
          // System.out.println (res.getString("oid_Processo_Aduaneiro"));

          list.add (carregaED(res));
         
        }
      }
      catch (SQLException exc) {
        throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                            "getByRecord()");
      }
      return list;
    }



      public void altera(Processo_AduaneiroED ed) throws Excecoes{

          try{
              String sql = " UPDATE Processos_Aduaneiros " +
            	           " SET oid_conhecimento=oid_conhecimento";
              if (JavaUtil.doValida(ed.getNR_Fatura())) {
                  sql += " ,NR_Fatura = '" + ed.getNR_Fatura() + "'" ;
              }
              if (JavaUtil.doValida(ed.getNR_Nota_Fiscal())) {
                  sql += " ,NR_Nota_Fiscal = '" + ed.getNR_Nota_Fiscal() + "'" ;
              }
              if (JavaUtil.doValida(ed.getNR_SD())) {
                  sql += " ,NR_SD = '" + ed.getNR_SD() + "'" ;
              }
              if (JavaUtil.doValida(ed.getNR_RE())) {
                  sql += " ,NR_RE = '" + ed.getNR_RE() + "'" ;
              }
              if (ed.getOid_Corretora()>0) {
                  sql += " ,oid_corretora = '" + ed.getOid_Corretora() + "'" ;
              }
              if (ed.getOid_Despachante()>0) {
                  sql += " ,oid_despachante = '" + ed.getOid_Despachante() + "'" ;
              }
              if (ed.getOid_Aduana()>0) {
                  sql += " ,oid_aduana = '" + ed.getOid_Aduana() + "'" ;
              }
              if (JavaUtil.doValida(ed.getDT_Validade())) {
                  sql += " ,DT_Validade = '" + ed.getDT_Validade() + "'" ;
              }
              if (JavaUtil.doValida(ed.getDT_Chegada())) {
                  sql += " ,DT_Chegada = '" + ed.getDT_Chegada() + "'" ;
              }
              if (JavaUtil.doValida(ed.getDT_Solicitacao_Cruze())) {
                  sql += " ,DT_Solicitacao_Cruze = '" + ed.getDT_Solicitacao_Cruze() + "'" ;
              }
              if (JavaUtil.doValida(ed.getDT_Liberacao())) {
                  sql += " ,DT_Liberacao = '" + ed.getDT_Liberacao() + "'" ;
              }
              if (JavaUtil.doValida(ed.getDT_Lancamento())) {
                  sql += " ,DT_Lancamento = '" + ed.getDT_Lancamento() + "'" ;
              }
              if (JavaUtil.doValida(ed.getDT_Cruze())) {
                  sql += " ,DT_Cruze = '" + ed.getDT_Cruze() + "'" ;
              }
              if (JavaUtil.doValida(ed.getDT_Encerramento())) {
                  sql += " ,DT_Encerramento = '" + ed.getDT_Encerramento() + "'" ;
              }
              if (JavaUtil.doValida(ed.getTX_Observacao())) {
                  sql += " ,TX_Observacao = '" + ed.getTX_Observacao() + "'" ;
              }
              if (JavaUtil.doValida(ed.getNM_Volumes_Observacao())) {
                  sql += " ,nm_volume = '" + ed.getNM_Volumes_Observacao() + "'" ;
              }
              
              sql += " WHERE  OID_Processo_Aduaneiro ='"+ed.getOid_Processo_Aduaneiro()+"'";

// System.out.println(sql);
     		  int i = executasql.executarUpdate(sql);
     		  
          } catch (SQLException exc) {
              throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
              "altera()");
          }catch (Exception exc) {
              throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
              "altera()");
          }
        }

      public void Imprime(Processo_AduaneiroED ed, HttpServletResponse resp)throws Excecoes {
          Processo_AduaneiroED edVolta = new Processo_AduaneiroED();
          ArrayList list = new ArrayList ();
          try {
              ResultSet res = this.executasql.executarConsulta(montaSql(ed));
              while (res.next()){
                  list.add(carregaED(res));
              }
              new Processo_AduaneiroRL().Imprime(list, ed, resp);
          } catch(SQLException exc){
              throw new Excecoes(exc.getMessage(), exc,  this.getClass().getName(), "getByRecord()");
          }
      }
      
      public void deleta(Processo_AduaneiroED ed) throws Excecoes{

          String sql = null;

          try{
            sql = "delete from Processos_Aduaneiros "+
                  "Where OID_Processo_Aduaneiro ='"+ed.getOid_Processo_Aduaneiro()+"'";
// System.out.println(sql);
            int i = executasql.executarUpdate(sql);
          }

          catch(Exception exc){
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir");
            excecoes.setMetodo("deleta(Processo_AduaneiroED ed)");
            excecoes.setExc(exc);
            throw excecoes;
          }
        }


  }
