package com.master.bd;

import java.sql.*;
import java.util.*;

import com.master.ed.*;
import com.master.rl.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class MultaBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria(executasql);

  Parametro_FixoED  edParametro_Fixo = new Parametro_FixoED();

  public MultaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public MultaED inclui(MultaED ed)
  throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;


    Parametro_FixoED parametroFixoED = new Parametro_FixoED();

    MultaED manED = new MultaED();
    try{

          // System.out.println("man bd 1 " );

      ResultSet rs = executasql.executarConsulta ("select max(oid_Multa) as result from Multas");
      while (rs.next ()) valOid = rs.getInt ("result");

      ed.setOID_Multa(String.valueOf(valOid+1));

      sql =  " insert into Multas (OID_Multa, NR_Multa, OID_Tipo_Ocorrencia, OID_Pessoa, OID_Veiculo, OID_Cidade, OID_Unidade, DM_Culpa, NM_Local, NR_Odometro_Inicial, DT_Emissao, TX_Observacao, HR_Multa, VL_Multa) values ";
      sql += "('" + ed.getOID_Multa() + "'," + 
                    ed.getNR_Multa() + "," + 
                    ed.getOID_Tipo_Ocorrencia() + ",'" + 
                    ed.getOID_Pessoa() + "','" + 
                    ed.getOID_Veiculo() +"'," + 
                    ed.getOID_Cidade() + "," + 
                    ed.getOID_Unidade() + ",'" + 
                    ed.getDM_Culpa()+ "','" + 
                    ed.getNM_Local() + "'," + 
                    ed.getNR_Odometro_Inicial() + ",'" + 
                    ed.getDT_Emissao() + "','" + 
                    ed.getTX_Observacao() + "','" + 
                    ed.getHR_Multa() + "', " + 
                    ed.getVL_Multa() + ")"; 

          // System.out.println("man bd inclui ->>>>>>>>>>>>>>>.. " + sql);


      int i = executasql.executarUpdate(sql);

      manED.setOID_Multa(ed.getOID_Multa());


    }
    catch(Excecoes e){
        throw e;
      }
    catch(SQLException e){
      throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(MultaED ed)");
    }
    catch(Exception e){
        throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(MultaED ed)");
     }
    return manED;
  }



  public void altera(MultaED ed) throws Excecoes{

    String sql = null;

    try{

      sql =
      	" update Multas " +
      	" set  oid_Cidade=" + ed.getOID_Cidade() + ", " +
      	"      Oid_Veiculo= '" + ed.getOID_Veiculo() + "', " +
      	"      oid_Pessoa = '" + ed.getOID_Pessoa() + "', " +
      	"      DM_Culpa= '" + ed.getDM_Culpa() + "', " +
      	"      NM_Local = '" + ed.getNM_Local() + "', " +
      	"      NR_Odometro_Inicial = " + ed.getNR_Odometro_Inicial()  + ", " +
      	"      TX_Observacao = '" + ed.getTX_Observacao() + "', " +
      	"      HR_Multa = '" + ed.getHR_Multa() + "', " +
      	"      VL_Multa = " + ed.getVL_Multa() + ", " +
      	"      OID_Unidade = " + ed.getOID_Unidade() + ", " +
      	"      oid_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia();


        // System.out.println("man bd 5");

      sql += " where oid_Multa = '" + ed.getOID_Multa() + "'";

        // System.out.println("man bd >>>>>>>> UPDATE 999" + sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


  public void deleta(MultaED ed) throws Excecoes{
    String sql = null;
    ResultSet res = null;
    try{

     //// System.out.println("exclui " + ed.getDM_Lancado_Ordem_Frete());


      sql = "delete from Multas WHERE oid_Multa = ";
      sql += "('" + ed.getOID_Multa() + "')";

      executasql.executarUpdate(sql);
           //// System.out.println("exclui Multa ");


    }

    catch(Exception exc){
    	throw new Excecoes("Erro ao excluir Multa: " + exc.getMessage(),
    			exc, this.getClass().getName(), "deleta(MultaED ed)");
    }
  }

  public ArrayList lista(MultaED ed)throws Excecoes{


    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  " SELECT Multas.*, " +
             "        Cidades.NM_Cidade, " +
             "        Pessoas.NM_Razao_Social " +
             " FROM Multas, Cidades, Pessoas ";
      sql += " WHERE Multas.OID_Cidade = Cidades.OID_Cidade ";
      sql += " AND   Multas.OID_Pessoa = Pessoas.OID_Pessoa ";

      if (String.valueOf(ed.getDT_Emissao()) != null && !String.valueOf(ed.getDT_Emissao()).equals("") && !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Multas.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }

      if (String.valueOf(ed.getNR_Multa()) != null &&
          !String.valueOf(ed.getNR_Multa()).equals("0") &&
          !String.valueOf(ed.getNR_Multa()).equals("null")){
        sql += " and Multas.NR_Multa = " + ed.getNR_Multa();
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Multas.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Multas.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Veiculo()) != null &&
            !String.valueOf(ed.getOID_Veiculo()).equals("") &&
            !String.valueOf(ed.getOID_Veiculo()).equals("null")){
          sql += " and Multas.oid_Veiculo = '" + ed.getOID_Veiculo() + "'";
        }

//// System.out.println("6 ");

      sql += " Order by Multas.DT_Emissao ";

// System.out.println("6 " + sql) ;

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        MultaED edVolta = new MultaED();

// System.out.println("7 ");

        edVolta.setOID_Multa(res.getString("OID_Multa"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));

        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNR_Placa(res.getString("oid_Veiculo"));

        edVolta.setDM_Culpa(res.getString("DM_Culpa"));
        edVolta.setNR_Multa(res.getLong("NR_Multa"));
        edVolta.setVL_Multa(res.getDouble("VL_Multa"));
        edVolta.setNM_Razao_Social_Pessoa(res.getString("NM_Razao_Social"));
        edVolta.setNM_Local(res.getString("NM_Cidade") + "-" + res.getString("NM_Local"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Multa");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Parcela_Conta_Corrente(MultaED ed)throws Excecoes{


    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  " SELECT Multas.NR_Multa, Multas.oid_Multa, oid_movimento_conta_corrente, dt_movimento_conta_corrente, vl_lancamento" +
             " FROM movimentos_contas_correntes, Multas ";
      sql += " WHERE movimentos_contas_correntes.oid_Multa ='" + ed.getOID_Multa() + "'";
      sql += " AND   movimentos_contas_correntes.oid_Multa = Multas.oid_Multa ";
      sql += " Order by movimentos_contas_correntes.dt_movimento_conta_corrente ";

// System.out.println("6 " + sql) ;

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        MultaED edVolta = new MultaED();

// System.out.println("7 ");

        edVolta.setOID_Multa(res.getString("OID_Multa"));
        edVolta.setOID_Movimento_Conta_Corrente(res.getLong("OID_Movimento_Conta_Corrente"));
        edVolta.setNR_Multa(res.getLong("NR_Multa"));

        edVolta.setDT_Parcela(res.getString("dt_movimento_conta_corrente"));

        DataFormatada.setDT_FormataData(edVolta.getDT_Parcela());
        edVolta.setDT_Parcela(DataFormatada.getDT_FormataData());

        edVolta.setVL_Parcela(res.getDouble("vl_lancamento"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Multa");
      excecoes.setMetodo("lista_Conta_Corrente");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }



  public MultaED getByRecord(MultaED ed)
  throws Excecoes{
      MultaED edVolta = new MultaED();
      try{
          String sql =
              " SELECT *, " +
              "        Unidades.CD_Unidade, " +
              "        Pessoas.NM_Fantasia " +
              " from Multas, " +
              "      Unidades, " +
              "      Pessoas " +
              " WHERE Multas.oid_unidade = Unidades.oid_unidade  "+
              " and   Unidades.oid_Pessoa = Pessoas.oid_pessoa  ";
          if (util.doValida(ed.getOID_Multa())) {
              sql += " and Multas.OID_Multa = '" + ed.getOID_Multa() + "'";
          }
          if (ed.getNR_Multa() > 0) {
              sql += " and Multas.NR_Multa = " + ed.getNR_Multa();
          }
          if (ed.getOID_Unidade() > 0){
              sql += " and Multas.OID_Unidade = " + ed.getOID_Unidade();
          }
          ResultSet res = this.executasql.executarConsulta(sql);
          ResultSet res2 = null;

          while (res.next()){
              edVolta.setOID_Multa(res.getString("OID_Multa"));
              edVolta.setOID_Cidade(res.getLong("OID_Cidade"));

              edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
              edVolta.setNR_Placa(res.getString("OID_Veiculo"));

              edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
              edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
              edVolta.setDM_Culpa(res.getString("DM_Culpa"));

              edVolta.setDM_Situacao(res.getString("DM_Situacao"));
              edVolta.setDT_Emissao(FormataData.formataDataBT(res.getString("DT_Emissao")));
              edVolta.setHR_Multa(res.getString("HR_Multa"));
              edVolta.setTX_Observacao(res.getString("TX_Observacao"));
              edVolta.setNM_Local(res.getString("NM_Local"));
              edVolta.setNR_Multa(res.getLong("NR_Multa"));
              edVolta.setNR_Odometro_Inicial(res.getLong("NR_Odometro_Inicial"));
              edVolta.setOID_Unidade(res.getLong("OID_Unidade"));

              edVolta.setCD_Unidade(res.getString("CD_Unidade"));
              edVolta.setVL_Multa(res.getDouble("vl_multa"));

              sql=" SELECT SUM (vl_lancamento) as vl_lancamento " +
                  " FROM  movimentos_contas_correntes " +
                  " WHERE movimentos_contas_correntes.oid_multa ='" + res.getString("OID_Multa") + "'";
                  
              res2 = this.executasql.executarConsulta(sql);
              while (res2.next()){
                edVolta.setVL_Saldo(res.getDouble("vl_multa")-res2.getDouble("vl_lancamento"));
              }


          }
      } catch (SQLException e) {
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(MultaED ed)");
      }
      return edVolta;
  }



  public byte[] gera_Relatorio_Multa(MultaED ed)
  throws Excecoes{
      String sql =
          " SELECT Multas.OID_Multa, " +
          "        Multas.DM_Culpa, " +
          "        Multas.DT_Emissao, " +
          "        Multas.DT_Previsao_Chegada, " +
          "        Multas.HR_Previsao_Chegada, " +
          "        Multas.NR_Multa, " +
          "        Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, " +
          "        Pessoa_Motorista.NM_Razao_Social as NM_Motorista, " +
          "        Veiculos.NR_Placa,  Veiculos.NR_Rastreador, " +
          "        Cidade_Multa.NM_Cidade as NM_Cidade_Multa, " +
          "        Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade " +
          " from Multas, " +
          "      Unidades, " +
          "      Veiculos, " +
          "      Cidades Cidade_Multa, " +
          "      Pessoas Pessoa_Proprietario, " +
          "      Pessoas Pessoa_Motorista, " +
          "      Pessoas Pessoa_Unidade, " +
          "      Cidades Cidade_Unidade " +
          " WHERE Multas.OID_Veiculo = Veiculos.OID_Veiculo " +
          "   AND Multas.OID_Unidade = Unidades.OID_Unidade " +
          "   AND Multas.OID_Cidade = Cidade_Multa.OID_Cidade " +
          "   AND Multas.OID_Pessoa = Pessoa_Motorista.Oid_Pessoa " +
          "   AND Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.oid_Pessoa " +
          "   AND Unidades.OID_Pessoa = Pessoa_Unidade.OID_Pessoa " +
          "   AND Pessoa_Unidade.OID_Cidade = Cidade_Unidade.OID_Cidade " +
          "   AND (Multas.OID_Pessoa_Entregadora is null or Multas.OID_Pessoa_Entregadora = '' ) ";
      if (ed.getNR_Multa() > 0) {
          sql += " and Multas.NR_Multa = " + ed.getNR_Multa();
      }
      if (ed.getOID_Cidade() > 0) {
          sql += " and Multas.OID_Cidade = " + ed.getOID_Cidade();
      }
      if (ed.getOID_Empresa() > 0){
          sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa();
      }
      if (ed.getOID_Unidade() > 0) {
          sql += " and Multas.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (util.doValida(ed.getDT_Emissao_Inicial())){
          sql += " and Multas.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
      }

      if (util.doValida(ed.getDT_Emissao_Final())) {
          sql += " and Multas.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }
      if (util.doValida(ed.getOID_Pessoa())) {
          sql += " and Multas.OID_Pessoa = '" + ed.getOID_Pessoa() + "' ";
      }
      if (util.doValida(ed.getOID_Veiculo())) {
          sql += " and Multas.OID_Veiculo = '" + ed.getOID_Veiculo() + "' ";
      }
      sql += " Order by Multas.Dt_Emissao, Multas.NR_Multa ";

      ResultSet res = this.executasql.executarConsulta(sql.toString());
      return new MultaRL().gera_Relatorio_Multa(res, ed);
  }


  public MultaED inclui_Parcela_Conta_Corrente(MultaED ed)
  throws Excecoes{

    String sql = null;
    String oid_Conta_Corrente="";

    Parametro_FixoED parametroFixoED = new Parametro_FixoED();

    MultaED manED = new MultaED();
    try{

          // System.out.println("man bd 1 " );
          sql=" SELECT oid_Conta_Corrente from Contas_Correntes WHERE Contas_Correntes.oid_pessoa= '" + ed.getOID_Pessoa() + "'";
          ResultSet res = this.executasql.executarConsulta(sql);

          while (res.next()){
            oid_Conta_Corrente=res.getString("oid_Conta_Corrente");
          }
          if (!"".equals(oid_Conta_Corrente)){
            Movimento_Conta_CorrenteED edMov = new Movimento_Conta_CorrenteED ();
            edMov.setDT_Movimento_Conta_Corrente (ed.getDT_Parcela());
            edMov.setNR_Documento (String.valueOf (ed.getNR_Multa ()));
            edMov.setNM_Complemento_Historico ("Parcela Multa: " + ed.getNR_Multa ());
            edMov.setOid_Multa(ed.getOID_Multa());
            edMov.setOid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
            edMov.setDM_Debito_Credito ("D");
            edMov.setDM_Tipo_Lancamento ("L");
            edMov.setOid_Lote_Pagamento (0);
            edMov.setVL_Lancamento (new Double (ed.getVL_Parcela()));

            sql = " SELECT * from Contas WHERE Contas.oid_Conta= " + parametroFixoED.getOID_Conta_Multa_Transito ();
            res = this.executasql.executarConsulta (sql);

            while (res.next ()) {
              edMov.setOID_Tipo_Documento (new Integer (res.getString ("oid_Tipo_Documento")));
              edMov.setOid_Historico (new Integer (res.getString ("oid_historico")));
              edMov.setOid_Conta (res.getInt ("oid_Conta"));
            }
            edMov = new Movimento_Conta_CorrenteBD (this.executasql).inclui (edMov);
          }
    }
    catch(Excecoes e){
        throw e;
      }
    catch(SQLException e){
      throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(MultaED ed)");
    }
    catch(Exception e){
        throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(MultaED ed)");
     }
    return manED;
  }



}
