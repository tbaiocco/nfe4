package com.master.bd;

/**
 * Título: WMS_EmbalagemBD
 * Descrição: Embalagens - BD
 * Data da criação: 10/2003
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_EmbalagemED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class WMS_EmbalagemBD {

  private ExecutaSQL executasql;

  public WMS_EmbalagemBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_EmbalagemED inclui(WMS_EmbalagemED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    WMS_EmbalagemED WMS_EmbalagemED = new WMS_EmbalagemED();

    try{


      ResultSet rs = executasql.executarConsulta("select max(Oid_Embalagem) as result from Embalagens");

      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Embalagens (";
      sql+= "Oid_Embalagem,Nm_Tipo,Nm_Descricao, NR_Peso_Liquido, NR_Peso_Bruto, Nm_Material ) values (";
      sql+=  ++valOid + ",";
      sql+= "'" + ed.getNm_Tipo() + "',";
      sql+= "'" + ed.getNm_Descricao() + "',";
      sql+=       ed.getNr_Peso_Liquido() + ", ";
      sql+=       ed.getNr_Peso_Bruto() + ", ";
      sql+= "'" + ed.getNm_Material() + "')";


      int i = executasql.executarUpdate(sql);
      WMS_EmbalagemED.setOid_Embalagem(new Integer(Long.toString(valOid)).intValue());
      WMS_EmbalagemED.setNm_Tipo(ed.getNm_Tipo());
      WMS_EmbalagemED.setNm_Tipo(ed.getNm_Tipo());
      WMS_EmbalagemED.setNm_Descricao(ed.getNm_Descricao());
      WMS_EmbalagemED.setNm_Material(ed.getNm_Material());
      WMS_EmbalagemED.setNr_Peso_Liquido(ed.getNr_Peso_Liquido());
      WMS_EmbalagemED.setNr_Peso_Bruto(ed.getNr_Peso_Bruto());
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Embalagem já registrada!");
      excecoes.setMetodo("inclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return WMS_EmbalagemED;

  }

  public void altera(WMS_EmbalagemED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Embalagens set ";
      sql += " Nm_Tipo = '" + ed.getNm_Tipo() + "', ";
      sql += " Nm_Descricao = '" + ed.getNm_Descricao() + "', ";
      sql += " Nm_Material = '" + ed.getNm_Material() + "', ";
      sql += " Nr_Peso_Liquido = " + ed.getNr_Peso_Liquido()+ ", ";
      sql += " Nr_Peso_Bruto = " + ed.getNr_Peso_Bruto();
      sql += " where oid_Embalagem = " + ed.getOid_Embalagem();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados da Embalagem");
      excecoes.setMetodo("altera()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(WMS_EmbalagemED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Embalagens WHERE Oid_Embalagem = ";
      sql += "(" + ed.getOid_Embalagem() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Esta Embalagem está sendo usado pelo sistema!");
      excecoes.setMetodo("deleta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public WMS_EmbalagemED getByRecord(WMS_EmbalagemED ed)throws Excecoes{

    String sql = null;

    WMS_EmbalagemED edVolta = new WMS_EmbalagemED();

    try{

      sql = " select * from Embalagens";

      if ( !String.valueOf(ed.getOid_Embalagem()).equals("0")){
        sql += " where Oid_Embalagem = " + ed.getOid_Embalagem();
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        edVolta = new WMS_EmbalagemED();

        edVolta.setOid_Embalagem(new Integer(res.getString("Oid_Embalagem")).intValue());
        edVolta.setNm_Tipo(res.getString("NM_Tipo"));
        edVolta.setNm_Descricao(res.getString("NM_Descricao"));
        edVolta.setNm_Material(res.getString("NM_Material"));
        edVolta.setNr_Peso_Liquido(res.getDouble("Nr_Peso_Liquido"));
        edVolta.setNr_Peso_Bruto(res.getDouble("Nr_Peso_Bruto"));
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList getAll()throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    WMS_EmbalagemED edVolta = new WMS_EmbalagemED();

    try{

      sql = " select * from Embalagens";

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        edVolta = new WMS_EmbalagemED();

        edVolta.setOid_Embalagem(new Integer(res.getString("Oid_Embalagem")).intValue());
        edVolta.setNm_Tipo(res.getString("NM_Tipo"));
        edVolta.setNm_Descricao(res.getString("NM_Descricao"));
        edVolta.setNm_Material(res.getString("NM_Material"));
        edVolta.setNr_Peso_Liquido(res.getDouble("Nr_Peso_Liquido"));
        edVolta.setNr_Peso_Bruto(res.getDouble("Nr_Peso_Bruto"));
        
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getAll()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public WMS_EmbalagemED getByOid( String oid_embalagem )throws Excecoes{

    String sql = null;

    WMS_EmbalagemED edVolta = new WMS_EmbalagemED();

    try{

      sql = " select * from Embalagens";
      sql += " where Oid_Embalagem = " + oid_embalagem;


      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        edVolta = new WMS_EmbalagemED();

        edVolta.setOid_Embalagem(new Integer(res.getString("Oid_Embalagem")).intValue());
        edVolta.setNm_Tipo(res.getString("NM_Tipo"));
        edVolta.setNm_Descricao(res.getString("NM_Descricao"));
        edVolta.setNm_Material(res.getString("NM_Material"));
        edVolta.setNr_Peso_Liquido(res.getDouble("Nr_Peso_Liquido"));
        edVolta.setNr_Peso_Bruto(res.getDouble("Nr_Peso_Bruto"));
        
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

public ArrayList getByNm_Tipo(String NM_Tipo) throws Exception
{
    String sql = null;
    ArrayList list = new ArrayList();
    try{
    sql = "select * from Embalagens";
    sql+= " where nm_tipo LIKE '"+NM_Tipo+"%'";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        WMS_EmbalagemED edVolta = new WMS_EmbalagemED();
        edVolta = new WMS_EmbalagemED();
        edVolta.setOid_Embalagem(new Integer(res.getString("Oid_Embalagem")).intValue());
        edVolta.setNm_Tipo(res.getString("FT_NM_Tipo"));
        edVolta.setNm_Descricao(res.getString("FT_NM_Descricao"));
        edVolta.setNm_Material(res.getString("FT_NM_Material"));
        edVolta.setNr_Peso_Liquido(res.getDouble("Nr_Peso_Liquido"));
        edVolta.setNr_Peso_Bruto(res.getDouble("Nr_Peso_Bruto"));
        
        list.add(edVolta);
      }
    }
    catch(Exception excc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByNm_Embalagem()");
      excecoes.setExc(excc);
      throw excecoes;
    }

    return list;

}

  public ArrayList lista(WMS_EmbalagemED edV, String orderby)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    String order_by = " ORDER BY oid_Embalagem";
    try{
      sql = "SELECT * FROM Embalagens WHERE 1=1 ";

      if(edV.getOid_Embalagem() > 0){
         sql += " and oid_Embalagem="+edV.getOid_Embalagem();
      } else {   
          if(edV.getNm_Descricao()!= null && !edV.getNm_Descricao().equals("null") && !edV.getNm_Descricao().equals("0"))
              sql += " and nm_Descricao LIKE '"+edV.getNm_Descricao()+"%'";
          if(edV.getNm_Material()!= null && !edV.getNm_Material().equals("null") && !edV.getNm_Material().equals("0"))
              sql += " and Nm_Material LIKE '"+edV.getNm_Material()+"%'";
          if(edV.getNm_Tipo()!= null && !edV.getNm_Tipo().trim().equals("null") && !edV.getNm_Tipo().trim().equals("0"))
              sql += " and Nm_Tipo LIKE '"+edV.getNm_Tipo()+"%'";
          if( !order_by.equals( orderby ) )
              sql += orderby;
          else sql += order_by;
      }    
      ResultSet res = null, res2 = null;

      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
          WMS_EmbalagemED ed = new WMS_EmbalagemED();

          ed.setOid_Embalagem( res.getInt( "oid_Embalagem" ) );
          ed.setNm_Descricao( res.getString( "Nm_Descricao" ) );
          ed.setNm_Material( res.getString( "Nm_Material" ) );
          ed.setNm_Tipo( res.getString( "Nm_Tipo" ));
          ed.setNr_Peso_Liquido(res.getDouble("Nr_Peso_Liquido"));
          ed.setNr_Peso_Bruto(res.getDouble("Nr_Peso_Bruto"));

          list.add(ed);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Embalagens");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

}