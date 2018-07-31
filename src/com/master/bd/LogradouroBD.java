package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.LogradouroED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class LogradouroBD {

  private ExecutaSQL executasql;

  public LogradouroBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public LogradouroED inclui(LogradouroED ed) throws Excecoes{

    String sql = null;
    long valOid = 1;
    String chave = null;
    int cont=9;
    String CD_Tipo_Movimento = null;

    LogradouroED LogradouroED = new LogradouroED();

    try{
      ResultSet rs = executasql.executarConsulta("select max(OID_Logradouro) as result from CEP");
      while (rs.next()){
        valOid = rs.getLong("result") + 1;
      }
      ed.setOID_Logradouro(valOid);

      sql = " insert into CEP (OID_Logradouro, NR_KM, NM_Logradouro, OID_Rota, NM_Contato, NM_Telefone, DM_Tipo_Logradouro, NM_REFERENCIA, OID_Cidade, NM_Endereco, NR_CEP, DM_Logradouro_Rastreado ) values ";
      sql += "(" + ed.getOID_Logradouro() + "," + ed.getNR_KM() + ",'" + ed.getNM_Logradouro() + "'," + ed.getOID_Rota() + ",'"  + ed.getNM_Contato() + "','" + ed.getNM_Telefone() + "','" + ed.getDM_Tipo_Logradouro()+ "','" + ed.getNM_Referencia() + "'," + ed.getOID_Cidade_Logradouro()+ ",'" + ed.getNM_Endereco() + "','" + ed.getNR_CEP() + "','" + ed.getDM_Logradouro_Rastreado() + "')";

      int i = executasql.executarUpdate(sql);

      int x=0;

      LogradouroED.setOID_Logradouro(ed.getOID_Logradouro());

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return LogradouroED;
  }

  public void altera(LogradouroED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update CEP set NM_Referencia = '" + ed.getNM_Referencia() + "', NM_Logradouro = '" + ed.getNM_Logradouro() + "', DM_Tipo_Logradouro = '" + ed.getDM_Tipo_Logradouro() + "' , NM_Contato = '" + ed.getNM_Contato() + "' , NM_Telefone = '" + ed.getNM_Telefone() + "', NR_KM = " + ed.getNR_KM()+ ", OID_Cidade = " + ed.getOID_Cidade_Logradouro() + ", NM_Endereco = '" + ed.getNM_Endereco() + "'" + ", NR_CEP = '" + ed.getNR_CEP() + "'" + ", DM_Logradouro_Rastreado = '" + ed.getDM_Logradouro_Rastreado() + "'";
      sql += " where oid_Logradouro = " + ed.getOID_Logradouro();

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

  public void deleta(LogradouroED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from CEP WHERE oid_Logradouro = ";
      sql += "(" + ed.getOID_Logradouro() + ")";

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

  public ArrayList lista(LogradouroED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      if (ed.getCD_Roteiro() != null && !ed.getCD_Roteiro().equals("null") && !ed.getCD_Roteiro().equals("")){
      sql =  " SELECT CEP.NR_KM, NM_Referencia, DM_Tipo_Logradouro, CEP.OID_Logradouro, NM_Logradouro, NM_Contato, NM_Telefone, Rotas.OID_Rota, Rotas.NM_Rodovia "+
            " from CEP, Rotas, Roteiros, Rotas_Roteiros "+
            " WHERE "+
            " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
            " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro and "+
            " CEP.OID_Rota = Rotas.OID_Rota and "+
            " Roteiros.CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
      }else{
        sql =  " SELECT CEP.NR_KM, NM_Referencia, DM_Tipo_Logradouro, CEP.OID_Logradouro, NM_Logradouro, NM_Contato, NM_Telefone, Rotas.OID_Rota, Rotas.NM_Rodovia from CEP, Rotas";
        sql += " WHERE CEP.OID_Rota = Rotas.OID_Rota ";
        sql += " AND CEP.OID_Rota = " + ed.getOID_Rota();
      }
      sql += " Order by CEP.NR_KM ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        LogradouroED edVolta = new LogradouroED();

        edVolta.setOID_Logradouro(new Long(res.getString("OID_Logradouro")).longValue());
        edVolta.setOID_Rota(res.getLong("OID_Rota"));

        edVolta.setNM_Logradouro(res.getString("NM_Logradouro"));
        edVolta.setNR_KM(res.getDouble("NR_KM"));

        edVolta.setNM_Contato(res.getString("NM_Contato"));
        if (edVolta.getNM_Contato() == null){
          edVolta.setNM_Contato(" ");
        }

        edVolta.setNM_Telefone(res.getString("NM_Telefone"));

        edVolta.setNM_Referencia(res.getString("NM_Referencia"));

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

  public LogradouroED getByRecord(LogradouroED ed)throws Excecoes{

    String sql = null;

    LogradouroED edVolta = new LogradouroED();

    try{

      sql =  " SELECT NM_Endereco, NR_CEP, CEP.oid_Cidade as OID_Cidade_Logradouro, Rotas.oid_Cidade as OID_Cidade_Rota, Rotas.oid_Cidade_Destino, CEP.NR_KM, NM_Referencia, DM_Tipo_Logradouro, CEP.OID_Logradouro, NM_Logradouro, NM_Contato, NM_Telefone, DM_Logradouro_Rastreado, Rotas.OID_Rota, Rotas.NM_Rodovia from CEP, Rotas";
      sql += " WHERE CEP.OID_Rota = Rotas.OID_Rota ";

      if (String.valueOf(ed.getOID_Logradouro()) != null &&
          !String.valueOf(ed.getOID_Logradouro()).equals("")){
        sql += " and CEP.OID_Logradouro = " + ed.getOID_Logradouro();
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Logradouro(res.getLong("OID_Logradouro"));
        edVolta.setOID_Rota(res.getLong("OID_Rota"));

        edVolta.setNM_Logradouro(res.getString("NM_Logradouro"));
        edVolta.setNR_KM(res.getDouble("NR_KM"));

        edVolta.setNM_Contato(res.getString("NM_Contato"));

        edVolta.setNM_Telefone(res.getString("NM_Telefone"));
        edVolta.setNM_Endereco(res.getString("NM_Endereco"));
        edVolta.setNR_CEP(res.getString("NR_CEP"));

        edVolta.setNM_Referencia(res.getString("NM_Referencia"));

        edVolta.setDM_Tipo_Logradouro(res.getString("DM_Tipo_Logradouro"));
        edVolta.setDM_Logradouro_Rastreado(res.getString("DM_Logradouro_Rastreado"));

        edVolta.setOID_Cidade(res.getLong("OID_Cidade_Rota"));
        edVolta.setOID_Cidade_Logradouro(res.getLong("OID_Cidade_Logradouro"));
        edVolta.setOID_Cidade_Destino(res.getLong("OID_Cidade_Destino"));


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

  public void geraRelatorio(LogradouroED ed)throws Excecoes{

    String sql = null;

    LogradouroED edVolta = new LogradouroED();

    try{

      sql = "select * from CEP where 1=1";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(LogradouroED ed)");
    }

  }

}
