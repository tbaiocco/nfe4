package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.ModeloNotaFiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class ModeloNotaFiscalBD {

  private ExecutaSQL executasql;

  public ModeloNotaFiscalBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ModeloNotaFiscalED inclui(ModeloNotaFiscalED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    ModeloNotaFiscalED manED = new ModeloNotaFiscalED();
    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_modelo_nota_fiscal) as result from MODELOS_NOTAS_FISCAIS");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");
      valOid = valOid+1;

      ed.setOid_Modelo_Nota_Fiscal(valOid);
      sql =  " insert into MODELOS_NOTAS_FISCAIS (oid_modelo_nota_fiscal, nm_nota_fiscal, cd_nota_fiscal, dm_aceita_contabilizacao, oid_sugestao_contabil, dm_aceita_estoque, dm_estoque_soma, dm_aceita_escrita, dm_aceita_financeiro, dm_aceita_imobilizado, dm_inss, dm_irrf, dm_ipi, dm_isqn, dm_icms) values ";
      sql += "(" + ed.getOid_Modelo_Nota_Fiscal() + ",'" + ed.getNM_Nota_Fiscal() + "','" + ed.getCD_Nota_Fiscal() + "','" + ed.getDM_Aceita_Contabilizacao() + "'," + ed.getOid_Sugestao_Contabil() + ",'" + ed.getDM_Aceita_Estoque() + "','" + ed.getDM_Estoque_Soma() + "','" + ed.getDM_Aceita_Escrita() + "','" + ed.getDM_Aceita_Financeiro()  + "','" + ed.getDM_Aceita_Imobilizado() + "','" + ed.getDM_INSS() + "','" + ed.getDM_IRRF() + "','"+ ed.getDM_IPI() +"', '"+ed.getDM_ISQN()+"', '"+ ed.getDM_ICMS()+"')";

      int i = executasql.executarUpdate(sql);
      manED.setOid_Modelo_Nota_Fiscal(ed.getOid_Modelo_Nota_Fiscal());
      manED.setOid_Sugestao_Contabil(ed.getOid_Sugestao_Contabil());

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir ModeloNotaFiscal");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return manED;

  }


    public ModeloNotaFiscalED associa(ModeloNotaFiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql =  "insert into sugestoes_modelos (oid_modelo_nota_fiscal, oid_sugestao_contabil) values ";
      sql += "(" + ed.getOid_Modelo_Nota_Fiscal() + "," + ed.getOid_Sugestao_Contabil() + ")";
//// System.out.println(sql);
      int i = executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir ModeloNotaFiscal");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;

  }

  public void altera(ModeloNotaFiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update MODELOS_NOTAS_FISCAIS set "+
      "nm_nota_fiscal = '"+ed.getNM_Nota_Fiscal()+"', "+
      "cd_nota_fiscal = '"+ed.getCD_Nota_Fiscal()+"', "+
      "dm_aceita_contabilizacao = '"+ed.getDM_Aceita_Contabilizacao()+"', "+
      "oid_sugestao_contabil = "+ed.getOid_Sugestao_Contabil()+", "+
      "dm_aceita_estoque = '"+ed.getDM_Aceita_Estoque()+"', "+
      "dm_estoque_soma = '"+ed.getDM_Estoque_Soma()+"', "+
      "dm_aceita_escrita = '"+ed.getDM_Aceita_Escrita()+"', "+
      "dm_aceita_financeiro = '"+ed.getDM_Aceita_Financeiro()+"', "+
      "dm_aceita_imobilizado = '"+ed.getDM_Aceita_Imobilizado()+"', "+
      "dm_inss = '"+ed.getDM_INSS()+"', "+
      "dm_irrf = '"+ed.getDM_IRRF()+"', "+
      "dm_ipi = '"+ed.getDM_IPI()+"', "+
      "dm_isqn = '"+ed.getDM_ISQN()+"', "+
      "dm_icms = '"+ed.getDM_ICMS()+"' " +
      "where oid_modelo_nota_fiscal = " + ed.getOid_Modelo_Nota_Fiscal();
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar ModeloNotaFiscal");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(ModeloNotaFiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from MODELOS_NOTAS_FISCAIS WHERE oid_modelo_nota_fiscal = ";
      sql += "(" + ed.getOid_Modelo_Nota_Fiscal() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir ModeloNotaFiscal");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

    public void excluiAssociacao(ModeloNotaFiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from sugestoes_modelos WHERE oid_modelo_nota_fiscal = ";
      sql += "(" + ed.getOid_Modelo_Nota_Fiscal() + ")";
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir ModeloNotaFiscal");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


  public ArrayList lista(ModeloNotaFiscalED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT * FROM MODELOS_NOTAS_FISCAIS ";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        ModeloNotaFiscalED edVolta = new ModeloNotaFiscalED();
        edVolta.setNM_Nota_Fiscal(res.getString("nm_nota_fiscal"));
        edVolta.setCD_Nota_Fiscal(res.getString("cd_nota_fiscal"));
        edVolta.setDM_Aceita_Contabilizacao(res.getString("dm_aceita_contabilizacao"));
        edVolta.setOid_Sugestao_Contabil(res.getLong("oid_sugestao_contabil"));
        edVolta.setDM_Aceita_Estoque(res.getString("dm_aceita_estoque"));
        edVolta.setDM_Estoque_Soma(res.getString("dm_estoque_soma"));
        edVolta.setDM_Aceita_Escrita(res.getString("dm_aceita_escrita"));
        edVolta.setDM_Aceita_Financeiro(res.getString("dm_aceita_financeiro"));
        edVolta.setDM_Aceita_Imobilizado(res.getString("dm_aceita_imobilizado"));
        edVolta.setDM_INSS(res.getString("dm_inss"));
        edVolta.setDM_IRRF(res.getString("dm_irrf"));
        edVolta.setDM_IPI(res.getString("dm_ipi"));
        edVolta.setDM_ISQN(res.getString("dm_isqn"));
        edVolta.setDM_ICMS(res.getString("dm_icms"));
        edVolta.setOid_Modelo_Nota_Fiscal(res.getLong("oid_modelo_nota_fiscal"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar ModeloNotaFiscal");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ModeloNotaFiscalED getByRecord(ModeloNotaFiscalED ed)throws Excecoes{

    String sql = null;
    ModeloNotaFiscalED edVolta = new ModeloNotaFiscalED();
    try{
      sql = "SELECT * FROM MODELOS_NOTAS_FISCAIS " +
      		"WHERE 1=1 ";
      if (ed.getOid_Modelo_Nota_Fiscal() > 0) {
          sql += "  AND oid_modelo_nota_fiscal = " + ed.getOid_Modelo_Nota_Fiscal();
      } else if (ed.getCD_Nota_Fiscal() != null) {
          sql += "  AND cd_nota_fiscal = '" + ed.getCD_Nota_Fiscal() +"' ";
      }     

      ResultSet res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        edVolta.setNM_Nota_Fiscal(res.getString("nm_nota_fiscal"));
        edVolta.setCD_Nota_Fiscal(res.getString("cd_nota_fiscal"));
        edVolta.setDM_Aceita_Contabilizacao(res.getString("dm_aceita_contabilizacao"));
        edVolta.setOid_Sugestao_Contabil(res.getLong("oid_sugestao_contabil"));
        edVolta.setDM_Aceita_Estoque(res.getString("dm_aceita_estoque"));
        edVolta.setDM_Estoque_Soma(res.getString("dm_estoque_soma"));
        edVolta.setDM_Aceita_Escrita(res.getString("dm_aceita_escrita"));
        edVolta.setDM_Aceita_Financeiro(res.getString("dm_aceita_financeiro"));
        edVolta.setDM_Aceita_Imobilizado(res.getString("dm_aceita_imobilizado"));
        edVolta.setDM_INSS(res.getString("dm_inss"));
        edVolta.setDM_IRRF(res.getString("dm_irrf"));
        edVolta.setDM_IPI(res.getString("dm_ipi"));
        edVolta.setDM_ISQN(res.getString("dm_isqn"));
        edVolta.setDM_ICMS(res.getString("dm_icms"));
        edVolta.setOid_Modelo_Nota_Fiscal(res.getLong("oid_modelo_nota_fiscal"));

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar ModeloNotaFiscal");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edVolta;
  }

  public ArrayList getByNM_Nota_Fiscal(ModeloNotaFiscalED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    String aux = "";

    try{
     aux = ed.getNM_Nota_Fiscal();

     if((aux.equals(""))||(aux == null)||(aux.equals("null"))){
        sql = "SELECT * FROM MODELOS_NOTAS_FISCAIS ORDER BY oid_modelo_nota_fiscal";
     }else{
        sql = "SELECT * FROM MODELOS_NOTAS_FISCAIS WHERE nm_nota_fiscal Like '"+ed.getNM_Nota_Fiscal()+"%' ORDER BY cd_nota_fiscal";
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        ModeloNotaFiscalED edVolta = new ModeloNotaFiscalED();
        edVolta.setNM_Nota_Fiscal(res.getString("nm_nota_fiscal"));
        edVolta.setCD_Nota_Fiscal(res.getString("cd_nota_fiscal"));
        edVolta.setDM_Aceita_Contabilizacao(res.getString("dm_aceita_contabilizacao"));
        edVolta.setOid_Sugestao_Contabil(res.getLong("oid_sugestao_contabil"));
        edVolta.setDM_Aceita_Estoque(res.getString("dm_aceita_estoque"));
        edVolta.setDM_Estoque_Soma(res.getString("dm_estoque_soma"));
        edVolta.setDM_Aceita_Escrita(res.getString("dm_aceita_escrita"));
        edVolta.setDM_Aceita_Financeiro(res.getString("dm_aceita_financeiro"));
        edVolta.setDM_Aceita_Imobilizado(res.getString("dm_aceita_imobilizado"));
        edVolta.setDM_INSS(res.getString("dm_inss"));
        edVolta.setDM_IRRF(res.getString("dm_irrf"));
        edVolta.setDM_IPI(res.getString("dm_ipi"));
        edVolta.setDM_ISQN(res.getString("dm_isqn"));
        edVolta.setDM_ICMS(res.getString("dm_icms"));
        edVolta.setOid_Modelo_Nota_Fiscal(res.getLong("oid_modelo_nota_fiscal"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar ModeloNotaFiscal");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

  public void alteraAssociacao(ModeloNotaFiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update sugestoes_modelos set "+
      "oid_sugestao_contabil = "+ed.getOid_Sugestao_Contabil()+" "+
      "where oid_modelo_nota_fiscal = " + ed.getOid_Modelo_Nota_Fiscal();
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar ModeloNotaFiscal");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


}
