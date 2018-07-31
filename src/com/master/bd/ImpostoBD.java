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

import com.master.ed.ImpostoED;
import com.master.ed.ImpostoPesquisaED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class ImpostoBD {

  private ExecutaSQL executasql;

  public ImpostoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ImpostoED inclui(ImpostoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    ImpostoED ImpostoED = new ImpostoED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Imposto) as result from Impostos");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");
      valOid = valOid+1;

      sql = " insert into Impostos "+
      "(OID_IMPOSTO, VL_IMPOSTO, PE_IMPOSTO, DT_STAMP, USUARIO_STAMP, DM_STAMP, OID_TIPO_IMPOSTO, OID_COMPROMISSO) values "+
      "(" + valOid + ",";
//      sql +=  ed.getVL_Imposto() == null ? "null," : ed.getVL_Imposto() + ",";
//      sql +=  ed.getPE_Imposto() == null ? "null," : ed.getPE_Imposto() + ",";
      sql += "'" + ed.getDt_stamp() + "',";
      sql += "'" + ed.getUsuario_Stamp() + "',";
      sql += "'" + ed.getDm_Stamp() + "',";
      sql += ed.getOid_Tipo_Imposto() + ",";
      sql += ed.getOid_Compromisso() + ")";

   // //// System.out.println(sql);

      int i = executasql.executarUpdate(sql);
 //     ImpostoED.setOid_Imposto(new Integer(Long.toString(valOid)));

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Imposto");
      excecoes.setMetodo("Inclui(ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return ImpostoED;

  }

  public ImpostoPesquisaED getByRecord(ImpostoED ed)throws Excecoes{

    String sql = null;
    ImpostoPesquisaED edVolta = new ImpostoPesquisaED();

    try{

      sql = "select * from Impostos, compromissos, pessoas, tipos_impostos where "+
      " Impostos.oid_compromisso = compromissos.oid_compromisso and "+
      " Impostos.oid_tipo_imposto = tipos_impostos.oid_tipo_imposto and "+
      " compromissos.oid_pessoa = pessoas.oid_pessoa ";

//      if (ed.getOid_Imposto() != null)
//        sql += " and Impostos.oid_Imposto = " + ed.getOid_Imposto();

     // // //// System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        //popular os dados de Imposto para voltar
      //// //// System.out.println(sql);

        edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
        edVolta.setNm_Tipo_Imposto(res.getString("nm_tipo_imposto"));
 //       edVolta.setNr_Parcela(new Integer(res.getString("nr_parcela")));
//        edVolta.setNr_Compromisso(new Integer(res.getString("nr_compromisso")));
        edVolta.setNr_Documento(res.getString("NR_Documento"));
//        edVolta.setVl_Imposto(new Double(res.getString("Vl_Imposto")));
//        edVolta.setPe_Imposto(new Double(res.getString("Pe_Imposto")));
//        edVolta.setVl_Saldo(new Double(res.getString("vl_saldo")));
//        edVolta.setOid_Imposto(new Integer(res.getString("oid_imposto")));
//        edVolta.setOid_Compromisso(new Integer(res.getString("oid_compromisso")));

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao acessar Imposto");
      excecoes.setMetodo("getByRecord(ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void altera(ImpostoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Impostos set ";

      //if (ed.getVL_Imposto() != null)
      //  sql += " VL_Imposto = " + ed.getVL_Imposto() + ", ";
      //else
      //  sql += " VL_Imposto = null,";

      //if (ed.getPE_Imposto() != null)
      //  sql += " PE_Imposto = " + ed.getPE_Imposto() + ", ";
      //else
      //  sql += " PE_Imposto = null,";

      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "',";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
      sql += " where oid_Imposto = " + ed.getOid_Imposto();

      //// //// System.out.println(sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados de Imposto ");
      excecoes.setMetodo("altera(ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void atualiza_imposto(ImpostoED ed) throws Excecoes{

	    String sql = null;

        String data_inicial = "01/" + ed.getNM_Mes() + "/" + ed.getNM_Ano();
        String data_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes() + "/" + ed.getNM_Ano());

	    try{
	    	
	    	
	    	
	      sql = " update Impostos set ";

	     // if (ed.getVL_Imposto() != null)
	     //   sql += " VL_Imposto = " + ed.getVL_Imposto() + ", ";
	     // else
	     //   sql += " VL_Imposto = null,";

	     // if (ed.getPE_Imposto() != null)
	     //   sql += " PE_Imposto = " + ed.getPE_Imposto() + ", ";
	     // else
	     //   sql += " PE_Imposto = null,";

	      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
	      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "',";
	      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
	      sql += " where oid_Imposto = " + ed.getOid_Imposto();

	      //// //// System.out.println(sql);

	      int i = executasql.executarUpdate(sql);
	    }

	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao alterar dados de Imposto ");
	      excecoes.setMetodo("altera(ImpostoED)");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	  }
  
  public void deleta(ImpostoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Impostos WHERE oid_Imposto = ";
      sql += ed.getOid_Imposto();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Imposto");
      excecoes.setMetodo("deleta(ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;

    }
  }


  public ArrayList lista(ImpostoPesquisaED edComp)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    ImpostoPesquisaED ed = (ImpostoPesquisaED)edComp;

    try{
      sql = "select * from Impostos, compromissos, pessoas, tipos_impostos where "+
      " Impostos.oid_compromisso = compromissos.oid_compromisso and "+
      " Impostos.oid_tipo_imposto = tipos_impostos.oid_tipo_imposto and "+
      " compromissos.oid_pessoa = pessoas.oid_pessoa and " +
       "Impostos.oid_Compromisso = " + ed.getOid_Compromisso();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        ImpostoPesquisaED edVolta = new ImpostoPesquisaED();
        edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
        edVolta.setNm_Tipo_Imposto(res.getString("nm_tipo_imposto"));
        //edVolta.setNr_Parcela(new Integer(res.getString("nr_parcela")));
       // edVolta.setNr_Compromisso(new Integer(res.getString("nr_compromisso")));
        edVolta.setNr_Documento(res.getString("NR_Documento"));
       // edVolta.setVl_Imposto(new Double(res.getString("Vl_Imposto")));
       // edVolta.setPe_Imposto(new Double(res.getString("Pe_Imposto")));
       // edVolta.setVl_Saldo(new Double(res.getString("vl_saldo")));
       // edVolta.setOid_Imposto(new Integer(res.getString("oid_imposto")));
        list.add(edVolta);
      }
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar dados de Imposto");
      excecoes.setMetodo("lista(ImpostoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public void geraRelatorio(ImpostoED ed)throws Excecoes{

  }

}
