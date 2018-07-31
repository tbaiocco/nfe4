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

import com.master.ed.LocalizaED;
import com.master.rl.Nota_FiscalRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class LocalizaBD {

  private ExecutaSQL executasql;

  public LocalizaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public LocalizaED inclui(LocalizaED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    LocalizaED localizaED = new LocalizaED();

    try{

      chave = String.valueOf(ed.getOID_Pessoa()) + String.valueOf(ed.getNR_Nota_Fiscal()) + ed.getNM_Serie();
      ed.setOID_Nota_Fiscal(chave);

      sql = " insert into Localiza (OID_Nota_Fiscal, OID_Pessoa, OID_Pessoa_Destinatario, OID_Pessoa_Consignatario, NR_Nota_Fiscal, NR_Volumes, NM_Serie, DT_Entrada, HR_Entrada, VL_Nota_Fiscal, DM_Situacao, NR_Pedido, DT_Emissao, NR_Peso, DM_Transferencia ) values ";
      sql += "('" + ed.getOID_Nota_Fiscal() + "','" + ed.getOID_Pessoa() + "','" + ed.getOID_Pessoa_Destinatario() + "','" + ed.getOID_Pessoa_Consignatario() + "'," + ed.getNR_Nota_Fiscal() + "," + ed.getNR_Volumes() + ",'" + ed.getNM_Serie() + "','"  + ed.getDT_Entrada() + "','" + ed.getHR_Entrada() + "'," + ed.getVL_Nota_Fiscal() + ",'" + ed.getDM_Situacao() + "','" + ed.getNR_Pedido() + "','" + ed.getDT_Emissao() + "'," + ed.getNR_Peso() + ",'" + ed.getDM_Transferencia() + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);

      localizaED.setOID_Nota_Fiscal(ed.getOID_Nota_Fiscal());
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return localizaED;
  }

  public void altera(LocalizaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Localiza set OID_Pessoa= '" + ed.getOID_Pessoa() + "', OID_Pessoa_Destinatario= '" + ed.getOID_Pessoa_Destinatario()   + "', OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "', NR_Nota_Fiscal= " + ed.getNR_Nota_Fiscal() + ", NR_Volumes= " + ed.getNR_Volumes() + ", DT_Entrada= '" + ed.getDT_Entrada() + "', HR_Entrada= '" + ed.getHR_Entrada() + "', VL_Nota_Fiscal= " + ed.getVL_Nota_Fiscal() + ", DM_Situacao= '" + ed.getDM_Situacao() + "', NR_Pedido= '" + ed.getNR_Pedido() + "', DT_Emissao= '" + ed.getDT_Emissao() + "', NR_Peso= " + ed.getNR_Peso() + " , DM_Transferencia= '" + ed.getDM_Transferencia() + "'";
      sql += " where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'" ;

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

  public void deleta(LocalizaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Localiza WHERE oid_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Nota_Fiscal() + "')";

      int i = executasql.executarUpdate(sql);
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

  public ArrayList lista(LocalizaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = " select NR_Peso, NR_Volumes, VL_Nota_Fiscal, DT_Emissao, NR_Nota_Fiscal, OID_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Localiza, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario ";
      sql += " where Localiza.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Localiza.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
        !String.valueOf(ed.getNR_Nota_Fiscal()).equals("0")){
        sql += " and Localiza.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
        !String.valueOf(ed.getOID_Pessoa()).equals("")){
        sql += " and Localiza.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
        sql += " and Localiza.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null &&
        !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("")){
        sql += " and Localiza.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao()) != null &&
        !String.valueOf(ed.getDT_Emissao()).equals("")){
        sql += " and Localiza.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }

      sql += " Order by Localiza.NR_Nota_Fiscal ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        LocalizaED edVolta = new LocalizaED();

        edVolta.setNR_Peso(res.getDouble("NR_Peso"));
        edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
        edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
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

  public LocalizaED getByRecord(LocalizaED ed)throws Excecoes{

    String sql = null;

    LocalizaED edVolta = new LocalizaED();

    try{

      sql = " select * from Localiza where 1=1 ";

      if (String.valueOf(ed.getOID_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("")){
        sql += " and OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
      }
      if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
        !String.valueOf(ed.getNR_Nota_Fiscal()).equals("0")){
        sql += " and NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
        sql += " and OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        edVolta.setHR_Entrada(res.getString("HR_Entrada"));
        edVolta.setNR_Pedido(res.getString("NR_Pedido"));
        edVolta.setNM_Serie(res.getString("NM_Serie"));
        edVolta.setDM_Transferencia(res.getString("DM_Transferencia"));
        edVolta.setNR_Peso(res.getDouble("NR_Peso"));
        edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
        edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
        edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Consignatario(res.getString("OID_Pessoa_Consignatario"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setDT_Entrada(res.getString("DT_Entrada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Entrada());
        edVolta.setDT_Entrada(DataFormatada.getDT_FormataData());
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(LocalizaED ed)throws Excecoes{

    String sql = null;

    LocalizaED edVolta = new LocalizaED();

    try{

      sql = "select * from Localiza where oid_Nota_Fiscal > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Nota_FiscalRL Nota_Fiscal_rl = new Nota_FiscalRL();
      //Nota_Fiscal_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(LocalizaED ed)");
    }

  }

}