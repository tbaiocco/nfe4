package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.ed.Ordem_Frete_TerceiroED;
import com.master.rl.Ordem_Frete_TerceiroRL;
import com.master.rn.Movimento_Conta_CorrenteRN;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Ordem_Frete_TerceiroBD {

  private ExecutaSQL executasql;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

  public Ordem_Frete_TerceiroBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ordem_Frete_TerceiroED inclui(Ordem_Frete_TerceiroED ed) throws Excecoes{

    //// System.out.println(" inicio" );
    String sql = null;
    long valOid = 0;
    String chave = null;
    String dm_Impresso="N";
    if ("T".equals(ed.getDM_Frete())){
      //dm_Impresso="S";
    }
    long nr_Programacao=0;
    
    Ordem_Frete_TerceiroED ordem_FreteED = new Ordem_Frete_TerceiroED();

    try{

      ResultSet rs = null;      
      rs = executasql.executarConsulta("select max(OID_Ordem_Frete) as result from ordens_fretes where dm_serie='" +ed.getNM_Serie() + "'");
      while (rs.next()) valOid = rs.getInt("result");
      ++valOid;
      if (valOid==1) valOid=999900001;

      rs = executasql.executarConsulta("select max(nr_programacao) as result from ordens_fretes where dm_serie='" +ed.getNM_Serie() + "'");
      while (rs.next()) nr_Programacao = rs.getLong("result");
      ++nr_Programacao;


     //// System.out.println(" 2 h" + valOid);

      chave = String.valueOf( valOid) ;

     //// System.out.println(" 2 F" + chave);

      ed.setOID_Ordem_Frete_Terceiro(chave);

      ed.setVL_Descontos(ed.getVL_Devolvido());
      double vl_adiantamento2=ed.getVL_Ordem_Frete_Terceiro()-ed.getVL_Adiantamento();
      sql =  " insert into Ordens_Fretes (OID_Ordem_Frete, DT_Emissao, DT_Coleta, DT_Entrega, OID_Unidade, OID_Pessoa, OID_Motorista, OID_Fornecedor, NM_Origem, NM_Destino, NM_Produto, OID_Veiculo, NR_Ordem_Frete, TX_Observacao, DM_Frete, dm_serie, DM_Impresso, VL_Ordem_Frete,  VL_Adiantamento_Terceiro, VL_Adiantamento2, VL_Cheque, VL_Especie, VL_Devolvido, VL_Saldo, VL_Despesas, VL_Descontos, VL_Frete_Devolvido, VL_Deposito, VL_Carga, VL_Descarga, VL_Agenciamento, nr_Programacao, oid_Usuario ) values ";
      sql += "('" + ed.getOID_Ordem_Frete_Terceiro() + "','" + ed.getDT_Emissao()+ "','" + ed.getDT_Coleta() + "','" + ed.getDT_Entrega()+ "'," + ed.getOID_Unidade() + ",'" + ed.getOID_Pessoa()+ "','" + ed.getOID_Motorista()+ "','" + ed.getOID_Fornecedor() + "','" + ed.getNM_Origem()+ "','" + ed.getNM_Destino() + "','" + ed.getNM_Produto() + "','" + ed.getOID_Veiculo() + "'," + ed.getNR_Ordem_Frete_Terceiro() + ",'" + ed.getTX_Observacao() + "','" + ed.getDM_Frete() + "','" + ed.getNM_Serie() + "','" + dm_Impresso + "'," + ed.getVL_Ordem_Frete_Terceiro() + "," + ed.getVL_Adiantamento() + "," + vl_adiantamento2  +  "," + ed.getVL_Cheque()+ "," + ed.getVL_Especie()+ "," + ed.getVL_Devolvido() + "," + ed.getVL_Saldo()+ "," + ed.getVL_Despesas() + "," + ed.getVL_Descontos() + "," + ed.getVL_Frete_Devolvido() + "," + ed.getVL_Deposito()+ "," + ed.getVL_Carga()+ "," + ed.getVL_Descarga()+ "," + ed.getVL_Agenciamento() + "," +nr_Programacao + "," + ed.getOID_Usuario() +")";

     // System.out.println(" 2 F" + sql);

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
      ordem_FreteED.setOID_Ordem_Frete_Terceiro(ed.getOID_Ordem_Frete_Terceiro());

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Ordem de Frete");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ordem_FreteED;

  }

  public void cancela (Ordem_Frete_TerceiroED ed) throws Excecoes {

    String sql = null;
    int i=0;

    try {
      // System.out.println ("Cancela 1");

      sql = " update Ordens_Fretes set DM_Impresso = 'C', VL_Descontos=0 ";
      sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete_Terceiro() + "'";
      // System.out.println ("Cancela 6" + sql);
      i = executasql.executarUpdate (sql);
      // System.out.println ("Cancela 7");

      if ("S".equals(parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro())) {

        sql =  " SELECT Contas_Correntes.oid_Conta_Corrente, Ordens_Fretes.NR_Ordem_Frete, Ordens_Fretes.NR_Recibo , Ordens_Fretes.VL_Devolvido ";
        sql += " FROM Unidades, Contas_Correntes, Ordens_Fretes ";
        sql += " WHERE Unidades.oid_Pessoa = Contas_Correntes.oid_Pessoa ";
        sql += " AND Ordens_Fretes.oid_Unidade = Unidades.oid_Unidade ";
        sql += " AND Ordens_Fretes.OID_oRDEM_FRETE = '" + ed.getOID_Ordem_Frete_Terceiro() + "'";

        // System.out.println ("Cancela 86" + sql);

        ResultSet rs = this.executasql.executarConsulta(sql);
        // System.out.println(sql);

        String oid_conta_corrente="";
        while (rs.next()){
         // System.out.println("TEM CONTA CORRENTE" +rs.getString("oid_conta_corrente") );
          oid_conta_corrente = rs.getString("oid_conta_corrente");
        }

        if (oid_conta_corrente != "") {
          Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED ();

          Movimento_Conta_CorrenteRN Movimento_Conta_CorrenteRN = new Movimento_Conta_CorrenteRN ();
          edMovConta.setDT_Movimento_Conta_Corrente (parametro_FixoED.getDT_Hoje());
          // System.out.println ("inclui 1");
          edMovConta.setOid_Lote_Pagamento (0);
          // System.out.println ("inclui 2");
          edMovConta.setNR_Documento (String.valueOf (rs.getString("NR_Recibo")));
          edMovConta.setDM_Tipo_Lancamento ("G");
          // System.out.println ("inclui 6");
          edMovConta.setOID_Tipo_Documento (new Integer (parametro_FixoED.getOID_Tipo_Documento_Ordem_Frete ()));
          edMovConta.setOid_Historico (new Integer (18)); //parametro_FixoED.getOID_Historico_Lancamento_Ordem_Frete_Terceiro ()));
          edMovConta.setOid_Conta_Corrente (oid_conta_corrente);
          // System.out.println ("inclui 10");

          edMovConta.setNM_Complemento_Historico ("Cancelamento OF  Nr:" + String.valueOf (rs.getString("NR_Ordem_Frete")));
          edMovConta.setDM_Debito_Credito ("D");
          edMovConta.setVL_Lancamento (new Double (rs.getString("VL_Devolvido")));
          Movimento_Conta_CorrenteRN.inclui (edMovConta);

        }
      }


    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("cancela(Ordem_FreteED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public void altera(Ordem_Frete_TerceiroED ed) throws Excecoes{

    String sql = null;

    try{
      ed.setVL_Descontos(ed.getVL_Devolvido());
      double vl_adiantamento2=ed.getVL_Ordem_Frete_Terceiro()-ed.getVL_Adiantamento();

      sql = " update Ordens_Fretes set TX_Observacao= '" +  ed.getTX_Observacao()+ "', NM_Origem= '" + ed.getNM_Origem() + "', OID_Motorista= '" + ed.getOID_Motorista()  + "', NM_Destino= '" + ed.getNM_Destino() + "', VL_Ordem_Frete= " + ed.getVL_Ordem_Frete_Terceiro() + ", VL_Adiantamento_Terceiro= " + ed.getVL_Adiantamento() + ", VL_Adiantamento2= " + vl_adiantamento2+ ", VL_Descontos= " + ed.getVL_Descontos() + ", VL_Despesas= " + ed.getVL_Despesas() + ", VL_Cheque= " + ed.getVL_Cheque() + ", VL_Especie= " + ed.getVL_Especie() + ", VL_Devolvido= " + ed.getVL_Devolvido()+ ", VL_Deposito= " + ed.getVL_Deposito() + ", VL_Frete_Devolvido= " + ed.getVL_Frete_Devolvido()  + ", VL_Saldo= " + ed.getVL_Saldo() + ", NR_Ordem_Frete=" + ed.getNR_Ordem_Frete_Terceiro() ;
      sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete_Terceiro() + "'";

      // System.out.println(sql);
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Ordem de Frete");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Ordem_Frete_TerceiroED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ordens_Fretes WHERE oid_Ordem_Frete = ";
      sql += "('" + ed.getOID_Ordem_Frete_Terceiro() + "')";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Ordem de Frete");
      excecoes.setMetodo("deletar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Ordem_Frete_TerceiroED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT Ordens_Fretes.DM_Impresso, Ordens_Fretes.OID_Ordem_Frete, Ordens_Fretes.NR_Recibo, Ordens_Fretes.OID_Veiculo, Ordens_Fretes.OID_Usuario,  Ordens_Fretes.DT_Emissao, Ordens_Fretes.NR_Ordem_Frete, Ordens_Fretes.NR_Programacao, Ordens_Fretes.NM_Origem, Ordens_Fretes.NM_Destino, Ordens_Fretes.VL_Ordem_Frete, Pessoas.NM_Fantasia, Unidades.CD_Unidade from Ordens_Fretes, Unidades, Pessoas ";
      sql += " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade ";
      sql += " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";
      
      if ("T".equals( ed.getDM_Frete())){
        sql += " AND Ordens_Fretes.DM_Frete = '" + ed.getDM_Frete () + "'";
      }else {
        sql += " AND Ordens_Fretes.NR_Programacao >0 " ;
      }

      if (String.valueOf(ed.getNR_Programacao()) != null &&
          !String.valueOf(ed.getNR_Programacao()).equals("0") &&
          !String.valueOf(ed.getNR_Programacao()).equals("null")){
        sql += " and Ordens_Fretes.NR_Programacao = " + ed.getNR_Programacao();
      }

      if (String.valueOf(ed.getDT_Emissao()) != null && !String.valueOf(ed.getDT_Emissao()).equals("")){
        sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
      }

      if (String.valueOf(ed.getOID_Veiculo()) != null && !String.valueOf(ed.getOID_Veiculo()).equals("") && !String.valueOf(ed.getOID_Veiculo()).equals("null")){
        sql += " and Ordens_Fretes.OID_Veiculo = '" + ed.getOID_Veiculo() + "'";
      }

      if (String.valueOf(ed.getNR_Ordem_Frete_Terceiro()) != null &&
          !String.valueOf(ed.getNR_Ordem_Frete_Terceiro()).equals("0") &&
          !String.valueOf(ed.getNR_Ordem_Frete_Terceiro()).equals("null")){
        sql += " and Ordens_Fretes.NR_Ordem_Frete = " + ed.getNR_Ordem_Frete_Terceiro();
      }


      if (String.valueOf(ed.getNR_Recibo()) != null &&
          !String.valueOf(ed.getNR_Recibo()).equals("0") &&
          !String.valueOf(ed.getNR_Recibo()).equals("null")){
        sql += " and Ordens_Fretes.NR_Recibo = " + ed.getNR_Recibo();
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade();
      }

      sql += " Order by Ordens_Fretes.NR_Ordem_Frete ";

	  // System.out.println(sql);
      
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
    	  // System.out.println("1");
        Ordem_Frete_TerceiroED edVolta = new Ordem_Frete_TerceiroED();

        edVolta.setOID_Ordem_Frete_Terceiro(res.getString("OID_Ordem_Frete"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
        edVolta.setNR_Recibo(res.getLong("NR_Recibo"));
        edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
  	  // System.out.println("2");

        edVolta.setNM_Origem (res.getString ("NM_Origem"));
        edVolta.setNM_Destino (res.getString ("NM_Destino"));
        edVolta.setNR_Programacao (res.getLong ("NR_Programacao"));
        edVolta.setNR_Ordem_Frete_Terceiro (res.getLong ("NR_Ordem_Frete"));

        if ("C".equals(res.getString("DM_Impresso"))){
          edVolta.setOID_Veiculo("CANCELADO");
        }else {
          edVolta.setVL_Ordem_Frete_Terceiro (res.getDouble ("VL_Ordem_Frete"));
        }
  	  // System.out.println("3");
        
        edVolta.setNM_Unidade(res.getString("CD_Unidade"));
        
        edVolta.setNM_Usuario("---");
        if (res.getString("oid_usuario") != null && res.getLong("oid_usuario")>0){
          sql= " SELECT NM_Usuario FROM Usuarios WHERE oid_Usuario="+ res.getString("oid_usuario");
          ResultSet res2 = this.executasql.executarConsulta (sql);
          if (res2.next ()) {
            edVolta.setNM_Usuario(res2.getString("NM_Usuario"));
          }
        }        
        
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Ordem Frete");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Ordem_Frete_TerceiroED getByRecord(Ordem_Frete_TerceiroED ed)throws Excecoes{

    String sql = null;

    Ordem_Frete_TerceiroED edVolta = new Ordem_Frete_TerceiroED();

    try{
      sql =  " SELECT * from Ordens_Fretes ";
      if (String.valueOf(ed.getOID_Ordem_Frete_Terceiro()) != null &&
          !String.valueOf(ed.getOID_Ordem_Frete_Terceiro()).equals("")&&
          !String.valueOf(ed.getOID_Ordem_Frete_Terceiro()).equals("null")){
        sql += " WHERE OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete_Terceiro() + "'";
      }else {
        if (ed.getNR_Recibo()>0) {
          sql += " WHERE NR_Recibo = " + ed.getNR_Recibo ();
        }
      }
      
      // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        edVolta.setOID_Ordem_Frete_Terceiro(res.getString("OID_Ordem_Frete"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Fornecedor(res.getString("OID_Fornecedor"));
        edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
        edVolta.setOID_Motorista(res.getString("OID_Motorista"));
        edVolta.setOID_Unidade(res.getLong("OID_Unidade"));
        edVolta.setOID_Usuario(res.getLong("OID_Usuario"));

        edVolta.setNM_Origem(res.getString("NM_Origem"));
        edVolta.setNM_Destino(res.getString("NM_Destino"));
        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));

        edVolta.setNR_Recibo(res.getLong("NR_Recibo"));
        edVolta.setNR_Programacao(res.getLong("NR_Programacao"));
        edVolta.setOid_Lote_Faturamento(res.getLong("Oid_Lote_Faturamento"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());


        edVolta.setNR_Ordem_Frete_Terceiro(res.getLong("NR_Ordem_Frete"));
        edVolta.setVL_Adiantamento(res.getDouble("VL_Adiantamento_Terceiro"));
        edVolta.setVL_Descontos(res.getDouble("VL_Descontos"));
        edVolta.setDM_Frete(res.getString("DM_Frete"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setVL_Ordem_Frete_Terceiro(res.getDouble("VL_Ordem_Frete"));

        edVolta.setVL_Cheque(res.getDouble("VL_Cheque"));
        edVolta.setVL_Saldo(res.getDouble("VL_Saldo"));
        edVolta.setVL_Despesas(res.getDouble("VL_Despesas"));
        edVolta.setVL_Especie(res.getDouble("VL_Especie"));
        edVolta.setVL_Devolvido(res.getDouble("VL_Devolvido"));
        edVolta.setVL_Deposito(res.getDouble("VL_Deposito"));
        edVolta.setVL_Frete_Devolvido(res.getDouble("VL_Frete_Devolvido"));

        edVolta.setDT_Coleta(res.getString("DT_Coleta"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Coleta());
        edVolta.setDT_Coleta(DataFormatada.getDT_FormataData());

        edVolta.setDT_Entrega(res.getString("DT_Entrega"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Entrega());
        edVolta.setDT_Entrega(DataFormatada.getDT_FormataData());

        edVolta.setNM_Produto(res.getString("NM_Produto"));

        edVolta.setVL_Carga(res.getDouble("VL_Carga"));
        edVolta.setVL_Descarga(res.getDouble("VL_Descarga"));
        edVolta.setVL_Agenciamento(res.getDouble("VL_Agenciamento"));

        edVolta.setNM_Usuario("---");
        if (res.getString("oid_usuario") != null && res.getLong("oid_usuario")>0){
          sql= " SELECT NM_Usuario FROM Usuarios WHERE oid_Usuario="+ res.getString("oid_usuario");
          ResultSet res2 = this.executasql.executarConsulta (sql);
          if (res2.next ()) {
            edVolta.setNM_Usuario(res2.getString("NM_Usuario"));
          }
        }        

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao Selecionar");
      excecoes.setMetodo("Registro");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public byte[] geraRelOrdemFreteTerceiro (Ordem_Frete_TerceiroED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    byte[] b = null;

    sql = " SELECT *, " +
          " Ordens_Fretes.OID_pessoa as oid_Proprietario, " +
          " Unidades.CD_Unidade, " +
          " Pessoas.NM_Fantasia, " +
          " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, " +
          " Pessoa_Motorista.NM_Razao_Social as NM_Motorista, " +
          " Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade " +
          " FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade  " +
          " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade " +
          " AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa " +
          " AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa "+
          " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa "+
          " AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade " +
          " AND (Ordens_Fretes.DM_Frete = 'T' OR Ordens_Fretes.NR_Programacao >0)";


    if (String.valueOf (ed.getOID_Empresa ()) != null &&
        !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
      sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
    }

    if (String.valueOf (ed.getOID_Unidade ()) != null &&
        !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
    }

    if (String.valueOf (ed.getOID_Pessoa ()) != null &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
      sql += " and Ordens_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getOID_Veiculo ()) != null &&
        !String.valueOf (ed.getOID_Veiculo ()).equals ("") &&
        !String.valueOf (ed.getOID_Veiculo ()).equals ("null")) {
      sql += " and Ordens_Fretes.OID_Veiculo = '" + ed.getOID_Veiculo () + "'";
    }
    if (String.valueOf (ed.getOID_Motorista ()) != null &&
        !String.valueOf (ed.getOID_Motorista ()).equals ("") &&
        !String.valueOf (ed.getOID_Motorista ()).equals ("null")) {
      sql += " and Ordens_Fretes.OID_Motorista = '" + ed.getOID_Motorista () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
      sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
      sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
    }

    if ("P".equals(ed.getDM_Acerto ())){
      sql += " AND Ordens_Fretes.NR_Recibo is null ";
    }
    if ("C".equals(ed.getDM_Acerto ())){
      sql += " AND Ordens_Fretes.NR_Recibo > 0 ";
    }

    if ("C".equals(ed.getDM_Situacao ())){
      sql += " AND Ordens_Fretes.DM_Impresso = 'C' " ;
    }
    
    sql += " order by Ordens_Fretes.NR_Programacao, Ordens_Fretes.DT_Emissao ";

    // System.out.println(sql);

    try {
    
      Ordem_Frete_TerceiroRL conRL = new Ordem_Frete_TerceiroRL ();
      res = this.executasql.executarConsulta (sql);
    
      //if (ed.getDM_Relatorio ().equals ("M1") ) {
        b = conRL.geraRelOrdemFreteTerceiro (res , ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("OrdemFreteRelatorio(Ordem_FreteED ed)");
    }
    return b;
  }

  public byte[] imprime_Ordem_Frete_Terceiro(Ordem_Frete_TerceiroED ed)throws Excecoes{


    // System.out.println("imprime_Ordem_Frete_Terceiro BD");

    String sql = null;
    byte[] b = null;
    long valOid = 0;
    long Nr_Recibo = 0;
    try{

      if ("T".equals(ed.getDM_Frete())){
      
      
        sql = "Select * " +
            " from " +
            " Ordens_Fretes, Pessoas " +
            " where Ordens_Fretes.oid_Fornecedor = Pessoas.oid_Pessoa " +
            " AND (Ordens_Fretes.NR_Recibo is null or Ordens_Fretes.NR_Recibo=0) " +
            " AND (Ordens_Fretes.DM_Frete = 'T' OR Ordens_Fretes.DM_Frete = 'S' ) " +
            " AND Ordens_Fretes.VL_ORDEM_FRETE > 0";
      
        if (String.valueOf (ed.getOID_Ordem_Frete_Terceiro ()) != null &&
            !String.valueOf (ed.getOID_Ordem_Frete_Terceiro ()).equals ("0")) {
          sql += " and Ordens_Fretes.OID_ORDEM_FRETE = '" + ed.getOID_Ordem_Frete_Terceiro () + "'";
        }
      
        // System.out.println (sql);
      
        ResultSet resCTRC = null;
        resCTRC = this.executasql.executarConsulta (sql);
      
        // System.out.println ("----------ORDEM FRETE TERCEIRO ------INICIO-----------------");
      
        while (resCTRC.next ()) {
      
          // System.out.println ("TEM CF->>" + resCTRC.getString ("OID_Unidade"));

          ResultSet rs = executasql.executarConsulta ("select max(Nr_Recibo) as result from ordens_fretes ");
          while (rs.next ()) Nr_Recibo = rs.getLong ("result");
          ++Nr_Recibo;
          
          sql = " UPDATE Ordens_Fretes set DM_Impresso='S', DM_Frete='T', " +
                " NR_Recibo = " + (Nr_Recibo + 1) +
                " WHERE Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete_Terceiro () + "'";
      
          executasql.executarUpdate (sql);
      
          // System.out.println ("parametro_FixoED->" + parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro ());
      
          if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro ())) {
      
            sql = " SELECT Contas_Correntes.oid_Conta_Corrente  ";
            sql += " FROM Unidades, Contas_Correntes ";
            sql += " WHERE Unidades.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente ";
            sql += " AND Unidades.OID_Unidade = " + resCTRC.getString ("OID_Unidade");
            rs = this.executasql.executarConsulta (sql);
            // System.out.println (sql);
      
            String oid_conta_corrente = "";
            while (rs.next ()) {
              // System.out.println ("TEM CONTA CORRENTE" + rs.getString ("oid_conta_corrente"));
              oid_conta_corrente = rs.getString ("oid_conta_corrente");
            }
      
            if (oid_conta_corrente != "") {
              Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED ();
      
              edMovConta.setDT_Movimento_Conta_Corrente (parametro_FixoED.getDT_Hoje ());
              // System.out.println ("inclui 1");
              edMovConta.setOid_Lote_Pagamento (0);
              // System.out.println ("inclui 2");
              edMovConta.setNR_Documento (String.valueOf (Nr_Recibo + 1));
              edMovConta.setDM_Tipo_Lancamento ("W");
              // System.out.println ("inclui 6");
              edMovConta.setOID_Tipo_Documento (new Integer (parametro_FixoED.getOID_Tipo_Documento_Ordem_Frete ()));
              edMovConta.setOid_Historico (new Integer (18)); //parametro_FixoED.getOID_Historico_Lancamento_Ordem_Frete_Terceiro ()));
              edMovConta.setOid_Conta_Corrente (oid_conta_corrente);
              // System.out.println ("inclui 10");
      
              edMovConta.setNM_Complemento_Historico ("Total do Frete Ordem  Nr:" + resCTRC.getString ("NR_Ordem_Frete") + "-" + resCTRC.getString ("NM_Razao_Social"));
              edMovConta.setDM_Debito_Credito ("C");
              edMovConta.setVL_Lancamento (new Double (resCTRC.getString ("VL_Ordem_Frete")));

              new Movimento_Conta_CorrenteBD(this.executasql).inclui(edMovConta);

              // System.out.println ("inclui 20");
      
              /*
                              edMovConta.setNM_Complemento_Historico ("(Devolvido Ordem Frete)" );
                              edMovConta.setDM_Debito_Credito ("D");
                              edMovConta.setVL_Lancamento (new Double (resCTRC.getString("VL_Frete_Devolvido")));
                              Movimento_Conta_CorrenteRN.inclui (edMovConta);
      
                              edMovConta.setNM_Complemento_Historico ("(Devolvido Cheque)" );
                              edMovConta.setDM_Debito_Credito ("D");
                              edMovConta.setVL_Lancamento (new Double (resCTRC.getString("VL_Cheque")));
                              Movimento_Conta_CorrenteRN.inclui (edMovConta);
      
                              edMovConta.setNM_Complemento_Historico ("(Devolvido Deposito)" );
                              edMovConta.setDM_Debito_Credito ("D");
                              edMovConta.setVL_Lancamento (new Double (resCTRC.getString("VL_Deposito")));
                              Movimento_Conta_CorrenteRN.inclui (edMovConta);
      
                              edMovConta.setNM_Complemento_Historico ("(Devolvido Especie)" );
                              edMovConta.setDM_Debito_Credito ("D");
                              edMovConta.setVL_Lancamento (new Double (resCTRC.getString("VL_Especie")));
                              Movimento_Conta_CorrenteRN.inclui (edMovConta);
      
                              edMovConta.setNM_Complemento_Historico ("(Total Valores Devolvido)");
                              edMovConta.setDM_Debito_Credito ("C");
                              edMovConta.setVL_Lancamento (new Double (rs.getString("VL_Devolvido")));
                              Movimento_Conta_CorrenteRN.inclui (edMovConta);
                              // System.out.println ("inclui 50");
               */

              // System.out.println ("inclui 30");
      
              edMovConta.setNM_Complemento_Historico ("(Adiantamento Acerto)");
              edMovConta.setDM_Debito_Credito ("D");

              edMovConta.setVL_Lancamento (new Double (resCTRC.getString ("VL_Saldo")));

              // System.out.println ("inclui 40");

              new Movimento_Conta_CorrenteBD(this.executasql).inclui(edMovConta);
      
            }
          }
      
        }
      }

        sql = "Select " +
            " Ordens_Fretes.*," +
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
            " Ordens_Fretes, Veiculos, Pessoas Pessoa_Proprietario, " +
            " Cidades Cidade_Proprietario, " +
            " Regioes_Estados Regiao_Estado_Proprietario, " +
            " Estados Estado_Proprietario, " +
            " Modelos_Veiculos, " +
            " Cidades Cidade_Veiculo, " +
            " Regioes_Estados Regiao_Estado_Veiculo, " +
            " Estados Estado_Veiculo " +
            " WHERE Ordens_Fretes.OID_Pessoa = Pessoa_Proprietario.OID_Pessoa " +
            " AND Ordens_Fretes.OID_Veiculo = Veiculos.OID_Veiculo " +
            " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
            " AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
            " AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
            " AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
            " AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
            " AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
            " AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO ";

        if (String.valueOf (ed.getOID_Ordem_Frete_Terceiro ()) != null &&
            !String.valueOf (ed.getOID_Ordem_Frete_Terceiro ()).equals ("0")) {
          sql += " and Ordens_Fretes.OID_ORDEM_FRETE = '" +
              ed.getOID_Ordem_Frete_Terceiro () + "'";
        }

        sql += " order by Ordens_Fretes.NR_ORDEM_FRETE";

        ResultSet res = null;
        res = this.executasql.executarConsulta (sql.toString ());

        Ordem_Frete_TerceiroRL ordem_Frete_TerceiroRL = new
            Ordem_Frete_TerceiroRL ();
        b = ordem_Frete_TerceiroRL.imprime_Ordem_Frete_Terceiro (ed, res);
    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("  ");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("imprime_Ordem_Frete_Terceiro(Ordem_FreteED ed)");
    }
    return b;
  }

}
