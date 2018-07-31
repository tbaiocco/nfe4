package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Conhecimento_Internacional_Nota_FiscalED;
import com.master.rl.Conhecimento_Internacional_Nota_FiscalRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Conhecimento_Internacional_Nota_FiscalBD {

  private ExecutaSQL executasql;

  public Conhecimento_Internacional_Nota_FiscalBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Conhecimento_Internacional_Nota_FiscalED inclui(Conhecimento_Internacional_Nota_FiscalED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    Conhecimento_Internacional_Nota_FiscalED Conhecimento_Internacional_Nota_FiscalED = new Conhecimento_Internacional_Nota_FiscalED();
    String CD_CFO_CONHECIMENTO = null;
    boolean DM_Nota_Em_Conhecimento = false;

    try{

      chave = (ed.getOID_Conhecimento() + ed.getOID_Nota_Fiscal());

      ed.setOID_Conhecimento_Internacional_Nota_Fiscal(chave);

//      sql =  " SELECT * from Conhecimentos_Internacionais_Notas_Fiscais";
//      sql += " WHERE Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
//      ResultSet rs = null;
//      rs = this.executasql.executarConsulta(sql);
//
//      while (rs.next()){
//        DM_Nota_Em_Conhecimento = true;
//      }

      sql = " insert into Conhecimentos_Internacionais_Notas_Fiscais (OID_Conhecimento_Internacional_Nota_Fiscal, OID_Conhecimento, OID_Nota_Fiscal, DT_Conhecimento_Internacional_Nota_Fiscal, HR_Conhecimento_Internacional_Nota_Fiscal ) values ";
      sql += "('" + ed.getOID_Conhecimento_Internacional_Nota_Fiscal() + "','" + ed.getOID_Conhecimento() + "','" + ed.getOID_Nota_Fiscal() +  "','"  + ed.getDT_Conhecimento_Internacional_Nota_Fiscal() + "','" + ed.getHR_Conhecimento_Internacional_Nota_Fiscal() + "')";
      int i = executasql.executarUpdate(sql);
//////// // System.out.println(sql);

      Conhecimento_Internacional_Nota_FiscalED.setOID_Conhecimento_Internacional_Nota_Fiscal(ed.getOID_Conhecimento_Internacional_Nota_Fiscal());
      Conhecimento_Internacional_Nota_FiscalED.setOID_Conhecimento(ed.getOID_Conhecimento());
      Conhecimento_Internacional_Nota_FiscalED.setOID_Unidade(ed.getOID_Unidade());

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return Conhecimento_Internacional_Nota_FiscalED;
  }

  public void altera(Conhecimento_Internacional_Nota_FiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Conhecimentos_Internacionais_Notas_Fiscais set 1 = 1";
      sql += " where oid_Conhecimento_Internacional_Nota_Fiscal = " + ed.getOID_Conhecimento_Internacional_Nota_Fiscal();

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public void deleta(Conhecimento_Internacional_Nota_FiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Conhecimentos_Internacionais_Notas_Fiscais WHERE oid_Conhecimento_Internacional_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Conhecimento_Internacional_Nota_Fiscal() + "')";

      int i = executasql.executarUpdate(sql);

/**      sql =  " SELECT SUM(Notas_Fiscais.VL_Nota_Fiscal) AS VL_Notas, SUM(Notas_Fiscais.NR_Volumes) AS NR_Volume, SUM(Notas_Fiscais.NR_Peso) AS NR_Peso FROM Notas_Fiscais, Conhecimentos_Internacionais_Notas_Fiscais ";
      sql += " WHERE Notas_Fiscais.OID_Nota_Fiscal = Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";

      ResultSet rs = null;
      rs = this.executasql.executarConsulta(sql);

      while (rs.next()){
        ed.setVL_Nota_Fiscal(rs.getDouble("VL_Notas"));
        ed.setNR_Volumes(rs.getLong("NR_Volume"));
        ed.setNR_Peso(rs.getDouble("NR_Peso"));
        ed.setNR_Peso_Cubado(rs.getDouble("NR_Peso"));
      }

      sql = " update Conhecimentos_Internacionais set VL_Nota_Fiscal= " + ed.getVL_Nota_Fiscal() + ", NR_Volumes= " + ed.getNR_Volumes() + ", NR_Peso= " + ed.getNR_Peso() + ", NR_Peso_Cubado= " + ed.getNR_Peso_Cubado();
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento() + "'" ;

      int u = executasql.executarUpdate(sql);
*/
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public ArrayList lista(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
//////// // System.out.println("BD 0");

    try{
      sql =  " SELECT Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento_Internacional_Nota_Fiscal as oid, Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, Conhecimentos_Internacionais.NR_Conhecimento, Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, Conhecimentos_Internacionais_Notas_Fiscais.DT_Conhecimento_Internacional_Nota_Fiscal as dt, Conhecimentos_Internacionais_Notas_Fiscais.HR_Conhecimento_Internacional_Nota_Fiscal as hr, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Volumes, Notas_Fiscais.NR_Peso, Notas_Fiscais.VL_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Conhecimentos_Internacionais_Notas_Fiscais, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Conhecimentos_Internacionais ";
      sql += " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";

//      if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
//          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("0") &&
//          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("null")){
//        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
//      }

      if (String.valueOf(ed.getOID_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("") &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("null")){
        sql += " and Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
      }

      if (String.valueOf(ed.getOID_Conhecimento()) != null &&
          !String.valueOf(ed.getOID_Conhecimento()).equals("") &&
          !String.valueOf(ed.getOID_Conhecimento()).equals("null")){
        sql += " and Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
      }

      sql += " Order by Notas_Fiscais.NR_Nota_Fiscal ";
//////// // System.out.println(sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();
//////// // System.out.println("E1");
        edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
//////// // System.out.println("E2");

        edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
//////// // System.out.println("E3");

        edVolta.setOID_Conhecimento_Internacional_Nota_Fiscal(res.getString("oid"));
//////// // System.out.println("E4");
        edVolta.setDT_Conhecimento_Internacional_Nota_Fiscal(res.getString("dt"));
//////// // System.out.println("E5");
        DataFormatada.setDT_FormataData(edVolta.getDT_Conhecimento_Internacional_Nota_Fiscal());
        edVolta.setDT_Conhecimento_Internacional_Nota_Fiscal(DataFormatada.getDT_FormataData());

        edVolta.setHR_Conhecimento_Internacional_Nota_Fiscal(res.getString("hr"));
//////// // System.out.println("E6");
        edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
//////// // System.out.println("E7");

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
//////// // System.out.println("E8");
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
//////// // System.out.println("E9");
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
//////// // System.out.println("E10");
        edVolta.setNR_Peso(res.getDouble("NR_Peso"));
//////// // System.out.println("E11");
        edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
//////// // System.out.println("E12");
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
//////// // System.out.println("E13");
        list.add(edVolta);
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return list;
  }

  public Conhecimento_Internacional_Nota_FiscalED getByRecord(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
//////// // System.out.println("BD 0");

    Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();
//////// // System.out.println("BD 1");
    try{

      sql =  " SELECT  Notas_Fiscais.OID_NATUREZA_OPERACAO, Notas_Fiscais.DM_Situacao, Notas_Fiscais.DM_Exportacao, Notas_Fiscais.DM_Transferencia, Notas_Fiscais.OID_Nota_Fiscal, Conhecimentos_Internacionais_Notas_Fiscais.DT_Conhecimento_Internacional_Nota_Fiscal as dt, Conhecimentos_Internacionais_Notas_Fiscais.HR_Conhecimento_Internacional_Nota_Fiscal as hr, Notas_Fiscais.OID_Pessoa, Notas_Fiscais.OID_Pessoa_Destinatario, Notas_Fiscais.OID_Pessoa_Consignatario, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Conhecimentos_Internacionais.NR_Conhecimento, "+
       " Conhecimentos_Internacionais.OID_Conhecimento, Conhecimentos_Internacionais.OID_Unidade, Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento_Internacional_Nota_Fiscal as oid, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Conhecimentos_Internacionais.DT_Emissao as DT_Emissao_Conhecimento, Notas_Fiscais.NM_Serie, Notas_Fiscais.NR_Pedido, Notas_Fiscais.DT_Entrada, Notas_Fiscais.HR_Entrada from Conhecimentos_Internacionais_Notas_Fiscais, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Conhecimentos_Internacionais ";
      sql += " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
//////// // System.out.println("BD 1");

      if (String.valueOf(ed.getOID_Conhecimento_Internacional_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Conhecimento_Internacional_Nota_Fiscal()).equals("")){
        sql += " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento_Internacional_Nota_Fiscal = '" + ed.getOID_Conhecimento_Internacional_Nota_Fiscal() + "'";
      }
//////// // System.out.println(sql);
      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        edVolta.setDM_Transferencia(res.getString("DM_Transferencia"));
        edVolta.setDM_Exportacao(res.getString("DM_Exportacao"));
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));

        edVolta.setDT_Conhecimento_Internacional_Nota_Fiscal(res.getString("dt"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Conhecimento_Internacional_Nota_Fiscal());
        edVolta.setDT_Conhecimento_Internacional_Nota_Fiscal(DataFormatada.getDT_FormataData());

        edVolta.setHR_Conhecimento_Internacional_Nota_Fiscal(res.getString("hr"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Consignatario(res.getString("OID_Pessoa_Consignatario"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
        edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
        edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
        edVolta.setOID_Unidade(res.getLong("OID_Unidade"));
        edVolta.setOID_Conhecimento_Internacional_Nota_Fiscal(res.getString("oid"));
        edVolta.setNR_Peso(res.getDouble("NR_Peso"));
        edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));

        edVolta.setDT_Emissao_Conhecimento(res.getString("DT_Emissao_Conhecimento"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao_Conhecimento());
        edVolta.setDT_Emissao_Conhecimento(DataFormatada.getDT_FormataData());

        edVolta.setNM_Serie(res.getString("NM_Serie"));
        edVolta.setNR_Pedido(res.getString("NR_Pedido"));
        edVolta.setDT_Entrada(res.getString("DT_Entrada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Entrada());
        edVolta.setDT_Entrada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Entrada(res.getString("HR_Entrada"));

        edVolta.setOID_Natureza_Operacao(res.getLong("OID_NATUREZA_OPERACAO"));

        sql = " select * from Naturezas_Operacoes where";
        sql += " OID_NATUREZA_OPERACAO = " + edVolta.getOID_Natureza_Operacao();

        ResultSet resTP = null;
        resTP = this.executasql.executarConsulta(sql);

        while (resTP.next()){
          edVolta.setNM_Natureza_Operacao(resTP.getString("NM_Natureza_Operacao"));
          edVolta.setCD_Natureza_Operacao(resTP.getString("CD_Natureza_Operacao"));
        }

      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar");
        excecoes.setMetodo("seleção");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }

  public byte[] geraRelTratoresPendentes(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;

      if (ed.getDM_Situacao() != null && ed.getDM_Situacao().equals("E")){
        sql =  " SELECT Estados.NM_Estado, "+
        "Notas_Fiscais.DT_Entrada, "+
        "Conhecimentos_Internacionais.DT_Emissao, "+
        "Conhecimentos_Internacionais.NR_Conhecimento, "+
        "Notas_Fiscais.oid_Nota_Fiscal, "+
        "Notas_Fiscais.NR_Nota_Fiscal, "+
        "Notas_Fiscais.DM_Situacao, "+
        "Notas_Fiscais.VL_Nota_Fiscal, "+
        "Notas_Fiscais.nr_pedido, "+
        "Veiculos.NR_PLACA, "+
        "Manifestos.DT_Emissao as DT_Emissao_Manifesto, "+
        "Pessoas.NM_RAZAO_SOCIAL, "+
        "Cidade_Destinatario.Nm_Cidade as Nm_Cidade_Destinatario, "+
        "Itens_Notas_Fiscais.CD_Chassis_Serie, "+
        "Itens_Notas_Fiscais.NR_Item_Nota_Fiscal, "+
        "Itens_Notas_Fiscais.OID_Item_Nota_Fiscal, "+
        "Itens_Notas_Fiscais.NR_Volumes as Quantidade_Itens, "+
        "Itens_Notas_Fiscais.VL_Produto, "+
        "Referencias.OID_Produto, "+
        "Referencias.CD_Referencia " +
        "from "+
        "Pessoas,  "+
        "Veiculos,  "+
        "Notas_Fiscais,  "+
        "Itens_Notas_Fiscais, "+
        "Referencias, "+
        "Regioes_Estados, "+
        "Estados, "+
        "Conhecimentos, "+
        "Viagens, "+
        "Manifestos, "+
        "Produtos, "+
        "Conhecimentos_Internacionais_Notas_Fiscais, "+
        "Cidades Cidade_Destinatario  "+
        "WHERE  Conhecimentos_Internacionais.OID_CIDADE_DESTINO = Cidade_Destinatario.oid_cidade "+
        " AND Manifestos.oid_Veiculo = Veiculos.oid_Veiculo"+
        " AND Pessoas.oid_Pessoa = Veiculos.oid_Pessoa"+
        " AND Cidade_Destinatario.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado "+
        " AND Regioes_Estados.OID_Estado = Estados.OID_Estado "+
        " AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal "+
        " AND Conhecimentos_Internacionais.OID_CONHECIMENTO = Conhecimentos_Internacionais_Notas_Fiscais.OID_CONHECIMENTO "+
        " AND Conhecimentos_Internacionais.OID_CONHECIMENTO = Viagens.OID_CONHECIMENTO "+
        " AND Manifestos.OID_Manifesto = Viagens.OID_Manifesto "+
        " AND Notas_Fiscais.oid_Nota_Fiscal = Conhecimentos_Internacionais_Notas_Fiscais.oid_Nota_Fiscal "+
        " AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_REFERENCIA "+
        " AND (Conhecimentos_Internacionais.DM_Situacao = 'G' "+
        " or Conhecimentos_Internacionais.DM_Situacao = 'F')"+
        " AND Conhecimentos_Internacionais.DM_Impresso = 'S' "+
        " AND Conhecimentos_Internacionais.VL_Total_Frete > 0" +
        " AND Produtos.OID_PRODUTO = Referencias.OID_PRODUTO "+
        " AND (Produtos.CD_PRODUTO = 'TL6'  "+
        " OR Produtos.CD_PRODUTO = 'TP'  "+
        " OR Produtos.CD_PRODUTO = 'TM'  "+
        " OR Produtos.CD_PRODUTO = 'TG'  "+
        " OR Produtos.CD_PRODUTO = 'RET'  "+
        " OR Produtos.CD_PRODUTO = 'TEP'  "+
        " OR Produtos.CD_PRODUTO = 'CKD'  "+
        " OR Produtos.CD_PRODUTO = 'EMP'  "+
        " OR Produtos.CD_PRODUTO = 'PC'  "+
        " OR Produtos.CD_PRODUTO = '680'  "+
        " OR Produtos.CD_PRODUTO = '650'  "+
        " OR Produtos.CD_PRODUTO = '617'  "+
        " OR Produtos.CD_PRODUTO = '660'  "+
        " OR Produtos.CD_PRODUTO = '6000'  "+
        " OR Produtos.CD_PRODUTO = 'TEG')  ";
      }else{
        sql =  " SELECT Estados.NM_Estado, "+
        "Notas_Fiscais.DT_Entrada, "+
        "Conhecimentos_Internacionais.DT_Emissao, "+
        "Conhecimentos_Internacionais.NR_Conhecimento, "+
        "Notas_Fiscais.oid_Nota_Fiscal, "+
        "Notas_Fiscais.NR_Nota_Fiscal, "+
        "Notas_Fiscais.DM_Situacao, "+
        "Notas_Fiscais.VL_Nota_Fiscal, "+
        "Notas_Fiscais.nr_pedido, "+
        "Cidade_Destinatario.Nm_Cidade as Nm_Cidade_Destinatario, "+
        "Itens_Notas_Fiscais.CD_Chassis_Serie, "+
        "Itens_Notas_Fiscais.NR_Item_Nota_Fiscal, "+
        "Itens_Notas_Fiscais.OID_Item_Nota_Fiscal, "+
        "Itens_Notas_Fiscais.NR_Volumes as Quantidade_Itens, "+
        "Itens_Notas_Fiscais.VL_Produto, "+
        "Referencias.OID_Produto, "+
        "Referencias.CD_Referencia " +
        "from Notas_Fiscais,  "+
        "Itens_Notas_Fiscais, "+
        "Referencias, "+
        "Regioes_Estados, "+
        "Estados, "+
        "Conhecimentos, "+
        "Produtos, "+
        "Conhecimentos_Internacionais_Notas_Fiscais, "+
        "Cidades Cidade_Destinatario  "+
        "WHERE  Conhecimentos_Internacionais.OID_CIDADE_DESTINO = Cidade_Destinatario.oid_cidade "+
        " AND Cidade_Destinatario.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado "+
        " AND Regioes_Estados.OID_Estado = Estados.OID_Estado "+
        " AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal "+
        " AND Conhecimentos_Internacionais.OID_CONHECIMENTO = Conhecimentos_Internacionais_Notas_Fiscais.OID_CONHECIMENTO "+
        " AND Notas_Fiscais.oid_Nota_Fiscal = Conhecimentos_Internacionais_Notas_Fiscais.oid_Nota_Fiscal "+
        " AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_REFERENCIA "+
        " AND (Conhecimentos_Internacionais.DM_Situacao = 'G' "+
        " or Conhecimentos_Internacionais.DM_Situacao = 'F')"+
        " AND Conhecimentos_Internacionais.DM_Impresso = 'S' "+
        " AND Conhecimentos_Internacionais.VL_Total_Frete > 0" +
        " AND Produtos.OID_PRODUTO = Referencias.OID_PRODUTO "+
        " AND (Produtos.CD_PRODUTO = 'TL6'  "+
        " OR Produtos.CD_PRODUTO = 'TP'  "+
        " OR Produtos.CD_PRODUTO = 'TM'  "+
        " OR Produtos.CD_PRODUTO = 'TG'  "+
        " OR Produtos.CD_PRODUTO = 'RET'  "+
        " OR Produtos.CD_PRODUTO = 'TEP'  "+
        " OR Produtos.CD_PRODUTO = 'CKD'  "+
        " OR Produtos.CD_PRODUTO = 'EMP'  "+
        " OR Produtos.CD_PRODUTO = '680'  "+
        " OR Produtos.CD_PRODUTO = '650'  "+
        " OR Produtos.CD_PRODUTO = '617'  "+
        " OR Produtos.CD_PRODUTO = '660'  "+
        " OR Produtos.CD_PRODUTO = '6000'  "+
        " OR Produtos.CD_PRODUTO = 'PC'  "+
        " OR Produtos.CD_PRODUTO = 'TEG')  ";
      }

      if (String.valueOf(ed.getOID_Pessoa()) != null &&
        !String.valueOf(ed.getOID_Pessoa()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
        sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("null")){
        sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
      }

      if (ed.getDM_Situacao() != null && ed.getDM_Situacao().equals("E")){
        if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
          !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
          !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
          sql += " and Manifestos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
        }
        if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
        !String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
        !String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
          sql += " and Manifestos.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
        }
      }else{
        if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
          !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
          !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
          sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
        }
        if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
        !String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
        !String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
          sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
        }
      }

      if (String.valueOf(ed.getDM_Situacao()) != null &&
        !String.valueOf(ed.getDM_Situacao()).equals("") &&
        !String.valueOf(ed.getDM_Situacao()).equals("T") &&
        !String.valueOf(ed.getDM_Situacao()).equals("null")){
        sql += " and Notas_Fiscais.DM_Situacao = '" + ed.getDM_Situacao() + "'";
      }


      if (ed.getDM_Situacao() != null && ed.getDM_Situacao().equals("E")){
         sql += " order by estados.nm_estado, nr_placa, nm_cidade_destinatario, Notas_Fiscais.nr_pedido ";
      }
      else {
         sql += " order by estados.nm_estado, nm_cidade_destinatario, Notas_Fiscais.nr_pedido ";
      }
////////// // System.out.println(sql);
    Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();

    try{
//////// // System.out.println("no try");
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql.toString());
//////// // System.out.println("executou"+ sql);


      Conhecimento_Internacional_Nota_FiscalRL Conhecimento_Internacional_Nota_Fiscal_rl = new Conhecimento_Internacional_Nota_FiscalRL();
      b = Conhecimento_Internacional_Nota_Fiscal_rl.geraRelTratoresPendentes(res);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Conhecimento_Internacional_Nota_FiscalED ed)");
    }

    return b;

  }

  public byte[] geraRelTratoresEmbarc(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;

      sql =  " SELECT Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento_Internacional_Nota_Fiscal, "+
      "Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, Manifestos.Dt_Previsao_Chegada, "+
      "Conhecimentos_Internacionais.NR_Conhecimento, Conhecimentos_Internacionais.dt_previsao_entrega, "+
      "Conhecimentos_Internacionais.Vl_Total_Frete, Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, "+
      "unidades.oid_unidade, unidades.cd_unidade,  Cidades.NM_Cidade, "+
      "Conhecimentos_Internacionais_Notas_Fiscais.DT_Conhecimento_Internacional_Nota_Fiscal, "+
      "Conhecimentos_Internacionais_Notas_Fiscais.HR_Conhecimento_Internacional_Nota_Fiscal, "+
      "Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao, "+
      "Notas_Fiscais.NR_Volumes as Quantidade_Notas, Notas_Fiscais.NR_Peso, "+
      "Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.nr_pedido, "+
      "Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, "+
      "Pessoa_Unidade.NM_Fantasia as NM_Fantasia, "+
      "Cidade_Remetente.Nm_Cidade as Nm_Cidade_Remetente, "+
      "Cidade_Destinatario.Nm_Cidade as Nm_Cidade_Destinatario, "+
      "Itens_Notas_Fiscais.CD_Chassis_Serie, Itens_Notas_Fiscais.NR_Item_Nota_Fiscal, "+
      "Itens_Notas_Fiscais.OID_Item_Nota_Fiscal, "+
      "Itens_Notas_Fiscais.NR_Volumes as Quantidade_Itens, "+
      "Itens_Notas_Fiscais.VL_Produto, Referencias.OID_Produto, "+
      "Referencias.CD_Referencia, " +
      "Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario "+
      "from Conhecimentos_Internacionais_Notas_Fiscais, Notas_Fiscais,  Itens_Notas_Fiscais, "+
      "Referencias, Pessoas Pessoa_Remetente, "+
      "Pessoas Pessoa_Destinatario, Pessoas Pessoa_Unidade, "+
      "Conhecimentos, Unidades, Cidades, Viagens, Manifestos, "+
      "Cidades Cidade_Remetente, Cidades Cidade_Destinatario  "+
      "WHERE Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal "+
      " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento "+
      " AND Viagens.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento "+
      " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto "+
      " AND Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade "+
      " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
      " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "+
      " AND Pessoa_Remetente.oid_Cidade = Cidade_Remetente.oid_cidade "+
      " AND Pessoa_Destinatario.oid_Cidade = Cidade_Destinatario.oid_cidade "+
      " AND Conhecimentos_Internacionais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "+
      " AND Conhecimentos_Internacionais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa "+
      " AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal "+
      " AND (Conhecimentos_Internacionais.DM_Situacao = 'G' "+
      " or Conhecimentos_Internacionais.DM_Situacao = 'F')"+
      " AND Conhecimentos_Internacionais.DM_Impresso = 'S' "+
      " AND Conhecimentos_Internacionais.VL_Total_Frete > 0" +
      " AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_REFERENCIA ";

      if (String.valueOf(ed.getNR_Conhecimento()) != null &&
        !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
        sql += " and Conhecimentos_Internacionais.NR_Conhecimento = " + ed.getNR_Conhecimento();
      }
      if (String.valueOf(ed.getOID_Unidade()) != null &&
        !String.valueOf(ed.getOID_Unidade()).equals("0")){
        sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
        !String.valueOf(ed.getOID_Pessoa()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
      }

      if (String.valueOf(ed.getDM_Tipo_Embarque()) != null &&
      !String.valueOf(ed.getDM_Tipo_Embarque()).equals("null") &&
      String.valueOf(ed.getDM_Tipo_Embarque()).equals("F")) {
        sql += " and Conhecimentos_Internacionais.NR_Peso <= 7500 ";
      }

      if (String.valueOf(ed.getDM_Tipo_Embarque()) != null &&
      !String.valueOf(ed.getDM_Tipo_Embarque()).equals("null") &&
      String.valueOf(ed.getDM_Tipo_Embarque()).equals("C")) {
        sql += " and Conhecimentos_Internacionais.NR_Peso > 7500 ";
      }

      sql += " order by unidades.cd_unidade, Conhecimentos_Internacionais.dt_emissao, Conhecimentos_Internacionais.nr_conhecimento ";

    Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();

    try{

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql.toString());

      Conhecimento_Internacional_Nota_FiscalRL Conhecimento_Internacional_Nota_FiscalRL = new Conhecimento_Internacional_Nota_FiscalRL();
      b =  Conhecimento_Internacional_Nota_FiscalRL.geraRelTratoresEmbarc(res);
//////// // System.out.println("fiz o sql" + sql);
    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] geraSeguroNacional(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;

     if (String.valueOf(ed.getDM_Situacao()) != null &&
        !String.valueOf(ed.getDM_Situacao()).equals("T")){
       sql =  " SELECT Conhecimentos_Internacionais.NR_Conhecimento, "+
       " Conhecimentos_Internacionais.Dt_Emissao, Conhecimentos_Internacionais.Vl_Nota_Fiscal, "+
       " Taxas.PE_Aliquota_Seguro, unidades.oid_unidade, "+
       " Estados.CD_Estado, "+
       " Estado_Destino.CD_Estado as CD_Estado_Destino, "+
       " unidades.cd_unidade,  Cidades.NM_Cidade as NM_Cidade_Origem, "+
       " Pessoa_Unidade.NM_Fantasia as NM_Fantasia, "+
       " Cidade_Destinatario.Nm_Cidade as Nm_Cidade_Destino "+
       " from Pessoas Pessoa_Unidade, "+
       " Conhecimentos, Unidades, Cidades, "+
       " Cidades Cidade_Destinatario, Regioes_Estados, Estados, "+
       " Estados Estado_Destino , Regioes_Estados Regiao_Estado_Destino, Taxas"+
       " WHERE Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade "+
       " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
       " AND Conhecimentos_Internacionais.oid_Cidade = Cidades.oid_cidade "+
       " AND Conhecimentos_Internacionais.oid_Cidade_Destino = Cidade_Destinatario.oid_cidade "+
       " AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado "+
       " AND Regioes_Estados.OID_Estado = Estados.OID_Estado "+
       " AND Cidade_Destinatario.OID_Regiao_Estado = Regiao_Estado_Destino.OID_Regiao_Estado "+
       " AND Regiao_Estado_Destino.OID_Estado = Estado_Destino.OID_Estado "+
       " AND Estados.OID_Estado = Taxas.oid_Estado "+
       " AND Estado_Destino.OID_Estado = Taxas.oid_Estado_Destino "+
       " AND (Conhecimentos_Internacionais.DM_Situacao = 'G' "+
       " or Conhecimentos_Internacionais.DM_Situacao = 'F')"+
       " AND Conhecimentos_Internacionais.DM_Impresso = 'S' "+
       " AND Conhecimentos_Internacionais.VL_Total_Frete > 0";

//       " AND Clientes.DM_Isencao_Seguro = '" + ed.getDM_Situacao() + "'";

     }
     else {
        sql =  " SELECT Conhecimentos_Internacionais.NR_Conhecimento, "+
        " Conhecimentos_Internacionais.Dt_Emissao, "+
        " Conhecimentos_Internacionais.Vl_Nota_Fiscal, "+
        " Taxas.PE_Aliquota_Seguro, "+
        " Unidades.oid_unidade, Estados.CD_Estado, "+
        " Estado_Destino.CD_Estado as CD_Estado_Destino, "+
        " Unidades.cd_unidade, Cidades.NM_Cidade as NM_Cidade_Origem, "+
        " Pessoa_Unidade.NM_Fantasia as NM_Fantasia, "+
        " Cidade_Destinatario.Nm_Cidade as Nm_Cidade_Destino "+
        " from Pessoas Pessoa_Unidade, "+
        " Conhecimentos, Unidades, Cidades, Cidades Cidade_Destinatario, "+
        " Regioes_Estados, Estados, Estados Estado_Destino , "+
        " Regioes_Estados Regiao_Estado_Destino, Taxas "+
        " WHERE Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade "+
        " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
        " AND Conhecimentos_Internacionais.oid_Cidade = Cidades.oid_cidade "+
        " AND Conhecimentos_Internacionais.oid_Cidade_Destino = Cidade_Destinatario.oid_cidade "+
        " AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado "+
        " AND Regioes_Estados.OID_Estado = Estados.OID_Estado "+
        " AND Cidade_Destinatario.OID_Regiao_Estado = Regiao_Estado_Destino.OID_Regiao_Estado "+
        " AND Regiao_Estado_Destino.OID_Estado = Estado_Destino.OID_Estado "+
        " AND Estado_Destino.OID_Estado = Taxas.oid_Estado_Destino "+
        " AND Estados.OID_Estado = Taxas.oid_Estado "+
        " AND (Conhecimentos_Internacionais.DM_Situacao = 'G' "+
        " or Conhecimentos_Internacionais.DM_Situacao = 'F')"+
        " AND Conhecimentos_Internacionais.DM_Impresso = 'S' "+
        " AND Conhecimentos_Internacionais.VL_Total_Frete > 0";
     }


      if (String.valueOf(ed.getOID_Unidade()) != null &&
        !String.valueOf(ed.getOID_Unidade()).equals("0")){
        sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
        !String.valueOf(ed.getOID_Pessoa()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
      }


      sql += " order by unidades.cd_unidade, Conhecimentos_Internacionais.dt_emissao, Conhecimentos_Internacionais.nr_conhecimento ";

       //////// // System.out.println(sql);


    Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();

    try{


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql.toString());

      //// //////// // System.out.println(res);

      Conhecimento_Internacional_Nota_FiscalRL Conhecimento_Internacional_Nota_FiscalRL = new Conhecimento_Internacional_Nota_FiscalRL();
      b =  Conhecimento_Internacional_Nota_FiscalRL.geraSeguroNacional(res);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }


  public byte[] geraRelConhecTrocaNota(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;

      sql =  " SELECT Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento_Internacional_Nota_Fiscal, Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, "+
      "Conhecimentos_Internacionais.NR_Conhecimento, Conhecimentos_Internacionais.dt_previsao_entrega, Conhecimentos_Internacionais.Vl_Total_Frete, Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, unidades.oid_unidade,"+
      "unidades.cd_unidade,  Cidades.NM_Cidade, Conhecimentos_Internacionais_Notas_Fiscais.DT_Conhecimento_Internacional_Nota_Fiscal, Conhecimentos_Internacionais_Notas_Fiscais.HR_Conhecimento_Internacional_Nota_Fiscal, "+
      "Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Volumes, Notas_Fiscais.NR_Peso, "+
      "Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.nr_pedido, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Unidade.NM_Fantasia as NM_Fantasia, Cidade_Remetente.Nm_Cidade as Nm_Cidade_Remetente, Cidade_Destinatario.Nm_Cidade as Nm_Cidade_Destinatario, "+
      "Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Conhecimentos_Internacionais_Notas_Fiscais, Notas_Fiscais, Pessoas "+
      "Pessoa_Remetente, Pessoas Pessoa_Destinatario, Pessoas Pessoa_Unidade, Conhecimentos, Unidades, Cidades, Cidades Cidade_Remetente, Cidades Cidade_Destinatario  "+
      "WHERE Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal "+
      " AND Notas_Fiscais.DM_Transferencia = 'S' "+
      " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento "+
      " AND Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade "+
      " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
      " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "+
      " AND Pessoa_Remetente.oid_Cidade = Cidade_Remetente.oid_cidade "+
      " AND Pessoa_Destinatario.oid_Cidade = Cidade_Destinatario.oid_cidade "+
      " AND Conhecimentos_Internacionais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "+
      " AND (Conhecimentos_Internacionais.DM_Situacao = 'G' "+
      " or Conhecimentos_Internacionais.DM_Situacao = 'F')"+
      " AND Conhecimentos_Internacionais.DM_Impresso = 'S' "+
      " AND Conhecimentos_Internacionais.VL_Total_Frete > 0" +
      " AND Conhecimentos_Internacionais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf(ed.getNR_Conhecimento()) != null &&
        !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
        sql += " and Conhecimentos_Internacionais.NR_Conhecimento = " + ed.getNR_Conhecimento();
      }
      if (String.valueOf(ed.getOID_Unidade()) != null &&
        !String.valueOf(ed.getOID_Unidade()).equals("0")){
        sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
        !String.valueOf(ed.getOID_Pessoa()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
      }

      sql += " order by unidades.cd_unidade, Conhecimentos_Internacionais.dt_emissao, Conhecimentos_Internacionais.nr_conhecimento ";

    Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();

    try{


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql.toString());

      Conhecimento_Internacional_Nota_FiscalRL Conhecimento_Internacional_Nota_FiscalRL = new Conhecimento_Internacional_Nota_FiscalRL();
      b =  Conhecimento_Internacional_Nota_FiscalRL.geraRelConhecTrocaNota(res);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] geraRelMercadoriasEmbarcadas(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;

      sql =  " SELECT Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento_Internacional_Nota_Fiscal, "+
      "Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, Manifestos.Dt_Previsao_Chegada, "+
      "Conhecimentos_Internacionais.NR_Conhecimento, Conhecimentos_Internacionais.dt_previsao_entrega, "+
      "Conhecimentos_Internacionais.Vl_Total_Frete, Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento, "+
      "unidades.oid_unidade, unidades.cd_unidade,  Cidades.NM_Cidade, "+
      "Conhecimentos_Internacionais_Notas_Fiscais.DT_Conhecimento_Internacional_Nota_Fiscal, "+
      "Conhecimentos_Internacionais_Notas_Fiscais.HR_Conhecimento_Internacional_Nota_Fiscal, "+
      "Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao, "+
      "Notas_Fiscais.NR_Volumes as Quantidade_Notas, Notas_Fiscais.NR_Peso, "+
      "Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.nr_pedido, "+
      "Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, "+
      "Pessoa_Pagador.NM_Razao_Social as NM_Razao_Social_Pagador, "+
      "Pessoa_Unidade.NM_Fantasia as NM_Fantasia, "+
      "Cidade_Remetente.Nm_Cidade as Nm_Cidade_Remetente, "+
      "Cidade_Destinatario.Nm_Cidade as Nm_Cidade_Destinatario, "+
      "Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario "+
      "from Conhecimentos_Internacionais_Notas_Fiscais, Notas_Fiscais, "+
      "Pessoas Pessoa_Remetente, "+
      "Pessoas Pessoa_Pagador, "+
      "Pessoas Pessoa_Destinatario, Pessoas Pessoa_Unidade, "+
      "Conhecimentos, Unidades, Cidades, Viagens, Manifestos, "+
      "Cidades Cidade_Remetente, Cidades Cidade_Destinatario  "+
      "WHERE Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal "+
      " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento "+
      " AND Viagens.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento "+
      " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto "+
      " AND Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade "+
      " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
      " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "+
      " AND Pessoa_Remetente.oid_Cidade = Cidade_Remetente.oid_cidade "+
      " AND Pessoa_Destinatario.oid_Cidade = Cidade_Destinatario.oid_cidade "+
      " AND Conhecimentos_Internacionais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "+
      " AND Conhecimentos_Internacionais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa "+
      " AND Conhecimentos_Internacionais.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "+
      " AND (Conhecimentos_Internacionais.DM_Situacao = 'G' "+
      " or Conhecimentos_Internacionais.DM_Situacao = 'F')"+
      " AND Conhecimentos_Internacionais.DM_Impresso = 'S' "+
      " AND Conhecimentos_Internacionais.OID_Produto = 1 ";

      if (String.valueOf(ed.getNR_Conhecimento()) != null &&
        !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
        sql += " and Conhecimentos_Internacionais.NR_Conhecimento = " + ed.getNR_Conhecimento();
      }
      if (String.valueOf(ed.getOID_Unidade()) != null &&
        !String.valueOf(ed.getOID_Unidade()).equals("0")){
        sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
        !String.valueOf(ed.getOID_Pessoa()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
        !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
      }
      if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
      !String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
      }

      sql += " order by unidades.cd_unidade, Conhecimentos_Internacionais.dt_emissao, Conhecimentos_Internacionais.nr_conhecimento ";

    Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();

    try{


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql.toString());

      Conhecimento_Internacional_Nota_FiscalRL Conhecimento_Internacional_Nota_FiscalRL = new Conhecimento_Internacional_Nota_FiscalRL();
      b =  Conhecimento_Internacional_Nota_FiscalRL.geraRelMercadoriasEmbarcadas(res);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

}
