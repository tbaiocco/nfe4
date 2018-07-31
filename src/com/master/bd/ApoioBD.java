package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.ApoioED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class ApoioBD {

  private ExecutaSQL executasql;

  public ApoioBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ApoioED inclui(ApoioED ed) throws Excecoes{

    String sql = null;
    long valOid = 1;
    String chave = null;
    int cont=9;
    String CD_Tipo_Movimento = null;

    ApoioED ApoioED = new ApoioED();

    try{
      ResultSet rs = executasql.executarConsulta("select max(OID_Apoio) as result from Apoios");
      while (rs.next()){
        valOid = rs.getLong("result") + 1;
      }
      ed.setOID_Apoio(valOid);

      sql = " insert into Apoios (OID_Apoio, NR_KM, NM_Apoio, OID_Rota, NM_Contato, NM_Telefone, DM_Tipo_Apoio, NM_REFERENCIA, OID_Cidade, NM_Endereco, NR_CEP, DM_Apoio_Rastreado ) values ";
      sql += "(" + ed.getOID_Apoio() + "," + ed.getNR_KM() + ",'" + ed.getNM_Apoio() + "'," + ed.getOID_Rota() + ",'"  + ed.getNM_Contato() + "','" + ed.getNM_Telefone() + "','" + ed.getDM_Tipo_Apoio()+ "','" + ed.getNM_Referencia() + "'," + ed.getOID_Cidade_Apoio()+ ",'" + ed.getNM_Endereco() + "','" + ed.getNR_CEP() + "','" + ed.getDM_Apoio_Rastreado() + "')";

      int i = executasql.executarUpdate(sql);

      int x=0;

      ApoioED.setOID_Apoio(ed.getOID_Apoio());

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return ApoioED;
  }

  public void altera(ApoioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Apoios set NM_Referencia = '" + ed.getNM_Referencia() + "', NM_Apoio = '" + ed.getNM_Apoio() + "', DM_Tipo_Apoio = '" + ed.getDM_Tipo_Apoio() + "' , NM_Contato = '" + ed.getNM_Contato() + "' , NM_Telefone = '" + ed.getNM_Telefone() + "', NR_KM = " + ed.getNR_KM()+ ", OID_Cidade = " + ed.getOID_Cidade_Apoio() + ", NM_Endereco = '" + ed.getNM_Endereco() + "'" + ", NR_CEP = '" + ed.getNR_CEP() + "'" + ", DM_Apoio_Rastreado = '" + ed.getDM_Apoio_Rastreado() + "'";
      sql += " where oid_Apoio = " + ed.getOID_Apoio();

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

  public void deleta(ApoioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Apoios WHERE oid_Apoio = ";
      sql += "(" + ed.getOID_Apoio() + ")";

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

  public ArrayList lista(ApoioED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      if (ed.getCD_Roteiro() != null && !ed.getCD_Roteiro().equals("null") && !ed.getCD_Roteiro().equals("")){
      sql =  " SELECT Apoios.NR_KM, NM_Referencia, DM_Tipo_Apoio, Apoios.OID_Apoio, NM_Apoio, NM_Contato, NM_Telefone, Rotas.OID_Rota, Rotas.NM_Rodovia "+
            " from Apoios, Rotas, Roteiros, Rotas_Roteiros "+
            " WHERE "+
            " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
            " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro and "+
            " Apoios.OID_Rota = Rotas.OID_Rota and "+
            " Roteiros.CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
      }else{
        sql =  " SELECT Apoios.NR_KM, NM_Referencia, DM_Tipo_Apoio, Apoios.OID_Apoio, NM_Apoio, NM_Contato, NM_Telefone, Rotas.OID_Rota, Rotas.NM_Rodovia from Apoios, Rotas";
        sql += " WHERE Apoios.OID_Rota = Rotas.OID_Rota ";
        sql += " AND Apoios.OID_Rota = " + ed.getOID_Rota();
      }
      sql += " Order by Apoios.NR_KM ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        ApoioED edVolta = new ApoioED();

        edVolta.setOID_Apoio(new Long(res.getString("OID_Apoio")).longValue());
        edVolta.setOID_Rota(res.getLong("OID_Rota"));

        edVolta.setNM_Apoio(res.getString("NM_Apoio"));
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

  public ApoioED getByRecord(ApoioED ed)throws Excecoes{

    String sql = null;

    ApoioED edVolta = new ApoioED();

    try{

      sql =  " SELECT NM_Endereco, NR_CEP, Apoios.oid_Cidade as OID_Cidade_Apoio, Rotas.oid_Cidade as OID_Cidade_Rota, Rotas.oid_Cidade_Destino, Apoios.NR_KM, NM_Referencia, DM_Tipo_Apoio, Apoios.OID_Apoio, NM_Apoio, NM_Contato, NM_Telefone, DM_Apoio_Rastreado, Rotas.OID_Rota, Rotas.NM_Rodovia from Apoios, Rotas";
      sql += " WHERE Apoios.OID_Rota = Rotas.OID_Rota ";

      if (String.valueOf(ed.getOID_Apoio()) != null &&
          !String.valueOf(ed.getOID_Apoio()).equals("")){
        sql += " and Apoios.OID_Apoio = " + ed.getOID_Apoio();
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Apoio(res.getLong("OID_Apoio"));
        edVolta.setOID_Rota(res.getLong("OID_Rota"));

        edVolta.setNM_Apoio(res.getString("NM_Apoio"));
        edVolta.setNR_KM(res.getDouble("NR_KM"));

        edVolta.setNM_Contato(res.getString("NM_Contato"));

        edVolta.setNM_Telefone(res.getString("NM_Telefone"));
        edVolta.setNM_Endereco(res.getString("NM_Endereco"));
        edVolta.setNR_CEP(res.getString("NR_CEP"));

        edVolta.setNM_Referencia(res.getString("NM_Referencia"));

        edVolta.setDM_Tipo_Apoio(res.getString("DM_Tipo_Apoio"));
        edVolta.setDM_Apoio_Rastreado(res.getString("DM_Apoio_Rastreado"));

        edVolta.setOID_Cidade(res.getLong("OID_Cidade_Rota"));
        edVolta.setOID_Cidade_Apoio(res.getLong("OID_Cidade_Apoio"));
        edVolta.setOID_Cidade_Destino(res.getLong("OID_Cidade_Destino"));


      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar");
        excecoes.setMetodo("sele��o");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }

  public void geraRelatorio(ApoioED ed)throws Excecoes{

    String sql = null;

    ApoioED edVolta = new ApoioED();

  }

}
