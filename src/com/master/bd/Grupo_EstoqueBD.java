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

import com.master.ed.Grupo_EstoqueED;
import com.master.rl.Grupo_EstoqueRL;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class Grupo_EstoqueBD {

  private ExecutaSQL executasql;

  public Grupo_EstoqueBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui(Grupo_EstoqueED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Grupo_Estoque) as result from Grupos_Estoques");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Grupos_Estoques (oid_Grupo_Estoque, cd_Grupo_Estoque, nm_Grupo_Estoque) values ";
      sql += "(" + ++valOid + ",'" + ed.getCD_Grupo_Estoque() + "','" + ed.getNM_Grupo_Estoque() + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro de Banco de dados");
      excecoes.setExc(exc);
    }
  }

  public void altera(Grupo_EstoqueED ed) throws Excecoes{

    String sql = null;

    try{

      sql  = " update Grupos_Estoques set ";
      sql += " cd_Grupo_Estoque = '" + ed.getCD_Grupo_Estoque() + "', ";
      sql += " nm_Grupo_Estoque = '" + ed.getNM_Grupo_Estoque() + "' ";
      sql += " where oid_Grupo_Estoque = " + ed.getOID_Grupo_Estoque();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro de Banco de dados");
      excecoes.setExc(exc);
    }
  }

  public void deleta(Grupo_EstoqueED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Grupos_Estoques WHERE oid_Grupo_Estoque = ";
      sql += "(" + ed.getOID_Grupo_Estoque() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro de Banco de dados");
      excecoes.setExc(exc);
    }
  }

  public ArrayList lista(Grupo_EstoqueED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = "select * from Grupos_Estoques where oid_grupo_estoque > 0";

      if (ed.getNM_Grupo_Estoque() != null && !ed.getNM_Grupo_Estoque().equals("")){
        sql += " and NM_Grupo_Estoque LIKE '" + ed.getNM_Grupo_Estoque() + "%'";
      }
      if (ed.getCD_Grupo_Estoque() != null && !ed.getCD_Grupo_Estoque().equals("")){
        sql += " and CD_Grupo_Estoque = '" + ed.getCD_Grupo_Estoque() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Grupo_EstoqueED edVolta = new Grupo_EstoqueED();
        edVolta.setCD_Grupo_Estoque(res.getString("cd_Grupo_Estoque"));
        edVolta.setNM_Grupo_Estoque(res.getString("nm_Grupo_Estoque"));
        edVolta.setOID_Grupo_Estoque(res.getLong("oid_Grupo_Estoque"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
    }

    return list;
  }

  public Grupo_EstoqueED getByRecord(Grupo_EstoqueED ed)throws Excecoes{

    String sql = null;

    Grupo_EstoqueED edVolta = new Grupo_EstoqueED();

    try{

      sql = "select * from Grupos_Estoques where oid_grupo_estoque > 0";

      if (String.valueOf(ed.getOID_Grupo_Estoque()) != null &&
          !String.valueOf(ed.getOID_Grupo_Estoque()).equals("0")){
        sql += " and OID_Grupo_Estoque = " + ed.getOID_Grupo_Estoque();
      }
      if (ed.getCD_Grupo_Estoque() != null && !ed.getCD_Grupo_Estoque().equals("")){
        sql += " and CD_Grupo_Estoque = '" + ed.getCD_Grupo_Estoque() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setCD_Grupo_Estoque(res.getString("cd_Grupo_Estoque"));
        edVolta.setNM_Grupo_Estoque(res.getString("nm_Grupo_Estoque"));
        edVolta.setOID_Grupo_Estoque(res.getLong("oid_Grupo_Estoque"));
      }
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
    }

    return edVolta;
  }

//  public String geraRelatorio(Grupo_EstoqueED ed, javax.servlet.http.HttpServletResponse out)throws Excecoes{
//
//    String sql = null;
//
//    Grupo_EstoqueED edVolta = new Grupo_EstoqueED();
//
//    try{
//
//      sql = "select * from Grupos_Estoques where oid_grupo_estoque > 0";
//
//      if (ed.getCD_Grupo_Estoque() != null && !ed.getCD_Grupo_Estoque().equals("")){
//        sql += " and CD_Grupo_Estoque = '" + ed.getCD_Grupo_Estoque() + "'";
//      }
//      if (ed.getNM_Grupo_Estoque() != null && !ed.getNM_Grupo_Estoque().equals("")){
//        sql += " and NM_Grupo_Estoque = '" + ed.getNM_Grupo_Estoque() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Grupo_EstoqueRL grupo_estoque_rl = new Grupo_EstoqueRL();
//      String arquivo = grupo_estoque_rl.geraRelatEstoque(res);
//

//      java.io.BufferedOutputStream writePDFOut = new java.io.BufferedOutputStream(out.getOutputStream());
//      out.setHeader("Content-Disposition", arquivo);
//      out.setContentType("application/pdf");
//      out.setContentLength(request.getContentSize());
//      writePDFOut.write(request.getContent());
//      writePDFOut.flush();
//      writePDFOut.close();
//
//      out.setContentType("application/pdf");
//
//      java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
//
//      java.io.FileInputStream from = new java.io.FileInputStream(arquivo);
//
//      byte[] buffer = new byte[4096];
//      int bytes_read;
//
//      while((bytes_read = from.read(buffer)) != -1)
//      baos.write(buffer, 0, bytes_read);
//
//      out.setContentLength( baos.size());
//      baos.writeTo( out.getOutputStream() );
//
//
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Grupo_EstoqueED ed)");
//    }
//
//  }

  public byte[] geraRelatorio(Grupo_EstoqueED ed)throws Excecoes{

    String sql = null;
    String arquivo = null;
    byte[] b=null;
    Grupo_EstoqueED edVolta = new Grupo_EstoqueED();

    try{

      sql = "select * from Grupos_Estoques where oid_grupo_estoque > 0";

      if (ed.getCD_Grupo_Estoque() != null && !ed.getCD_Grupo_Estoque().equals("")){
        sql += " and CD_Grupo_Estoque = '" + ed.getCD_Grupo_Estoque() + "'";
      }
      if (ed.getNM_Grupo_Estoque() != null && !ed.getNM_Grupo_Estoque().equals("")){
        sql += " and NM_Grupo_Estoque = '" + ed.getNM_Grupo_Estoque() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Grupo_EstoqueRL grupo_estoque_rl = new Grupo_EstoqueRL();
      b = grupo_estoque_rl.geraRelatEstoque(res);

    }
    catch (Excecoes e){
      throw e;
    }

    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Grupo_EstoqueED ed)");
    }

    return b;

  }

  }