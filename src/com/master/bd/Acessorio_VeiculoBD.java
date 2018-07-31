package com.master.bd;

/**
 * <p>Title: Acessorio_VeiculoBD </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.ed.Acessorio_VeiculoED;
import java.util.*;
import java.sql.*;
import com.master.root.FormataDataBean;

public class Acessorio_VeiculoBD {

  private ExecutaSQL executasql;

  public Acessorio_VeiculoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }




    public Acessorio_VeiculoED inclui(Acessorio_VeiculoED ed) throws Excecoes{

        String sql = null;
        long valOid = 0;
        String chave = null;
        FormataDataBean dataFormatada = new FormataDataBean();

        try{

          sql = " insert into Acessorios_Veiculos (oid_Acessorio_Veiculo, oid_Tipo_Acessorio, oid_Veiculo, " +
          		"dt_stamp, usuario_stamp, dm_stamp, vl_quantidade) values ";
          sql += "('" + ed.getOid_Acessorio_Veiculo() + "'," + ed.getOid_Tipo_Acessorio() + ",'" + ed.getOid_Veiculo() + "','" +
                  ed.getDt_stamp()+"','"+ed.getUsuario_Stamp()+"','"+ed.getDm_Stamp()+"', " + ed.getVL_Quantidade() + ")";

	  // System.out.print(sql);

          int i = executasql.executarUpdate(sql);
        }
        catch(Exception exc){
            exc.printStackTrace();
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMetodo("inclui_VeiculoxAcessorio()");
          excecoes.setExc(exc);
          throw excecoes;
        }
        return ed;
      }

    public void altera(Acessorio_VeiculoED ed) throws Excecoes{

        String sql = null;
        FormataDataBean dataFormatada = new FormataDataBean();

        try{
          sql = "update Acessorios_Veiculos "+
                "set vl_Quantidade = "+ed.getVL_Quantidade()+
                ", dt_stamp ='"+Data.getDataDMY()+"'"+
                ", usuario_stamp ='"+ed.getUsuario_Stamp()+"'"+
                ", dm_stamp ='S' "+
                "Where OID_Acessorio_Veiculo ='"+ed.getOid_Acessorio_Veiculo()+"'";
	    // System.out.println(sql);

          int i = executasql.executarUpdate(sql);
        }

        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao alterar");
          excecoes.setMetodo("altera_VeiculoxAcessorio()");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

    public void deleta(Acessorio_VeiculoED ed) throws Excecoes{

        String sql = null;
        String DM_Impresso = null;

        try{
          sql = "delete from Acessorios_Veiculos Where oid_Acessorio_Veiculo ='"+ed.getOid_Acessorio_Veiculo() + "'";

          int i = executasql.executarUpdate(sql);
        }

        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMetodo("deleta_VeiculoxAcessorio()");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

    public ArrayList lista(Acessorio_VeiculoED ed)
    throws Excecoes{

        String sql = null;
        ArrayList list = new ArrayList();
        FormataDataBean dataFormatada = new FormataDataBean();

    // System.out.println("Lista Acessorio bd");

        try{
          sql = "Select Acessorios_Veiculos.oid_Acessorio_Veiculo, "+
                " Acessorios_Veiculos.oid_Veiculo, "+
                " Acessorios_Veiculos.oid_Tipo_Acessorio, "+
                " Acessorios_Veiculos.vl_quantidade, "+
                " Tipos_Acessorios.nm_Tipo_Acessorio "+
                " From  Acessorios_Veiculos, Tipos_Acessorios "+
                " Where Acessorios_Veiculos.oid_Tipo_Acessorio=Tipos_Acessorios.oid_Tipo_Acessorio" ;

              if (ed.getDM_Tipo_Acessorio() != null &&
                  !ed.getDM_Tipo_Acessorio().equals("") &&
                  !ed.getDM_Tipo_Acessorio().equals("null")){
                  sql += " AND DM_Tipo_Acessorio = '" + ed.getDM_Tipo_Acessorio() + "'";
              }


//          if (String.valueOf(ed.getOid_Acessorio_Veiculo()) != null &&
//                  !String.valueOf(ed.getOid_Acessorio_Veiculo()).equals("") &&
//                  !String.valueOf(ed.getOid_Acessorio_Veiculo()).equals("null") &&
//                  !String.valueOf(ed.getOid_Acessorio_Veiculo()).equals("0")){
//                sql += " AND Tipos_Acessorios.oid_Tipo_Acessorio = '" + ed.getOid_Acessorio_Veiculo() + "'";
//              }
          if (ed.getOid_Veiculo() != null &&
                  !ed.getOid_Veiculo().equals("") &&
                  !ed.getOid_Veiculo().equals("null")){
                sql += " AND oid_Veiculo = '" + ed.getOid_Veiculo() + "'";
              }
//          if (ed.getOid_Acessorio_Veiculo() != null &&
//                  !ed.getOid_Acessorio_Veiculo().equals("") &&
//                  !ed.getOid_Acessorio_Veiculo().equals("null")){
//                sql += " AND oid_Acessorio_Veiculo = '" + ed.getOid_Acessorio_Veiculo() + "'";
//              }
          sql += " ORDER BY oid_Tipo_Acessorio";

	    // System.out.println("Lista Acessorio bd - " + sql);


          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);
          double valor = 0;
          //popula
          while (res.next()){
	    // System.out.println("Lista Acessorio wh 1");

            Acessorio_VeiculoED edVolta = new Acessorio_VeiculoED();
            edVolta.setOid_Acessorio_Veiculo(res.getString("oid_Acessorio_Veiculo"));
            edVolta.setOid_Tipo_Acessorio(res.getLong("oid_Tipo_Acessorio"));
            edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
            edVolta.setVL_Quantidade(res.getDouble("vl_quantidade"));
	    edVolta.setNM_Acessorio_Veiculo(res.getString("nm_Tipo_Acessorio"));
            list.add(edVolta);
          }
        }
        catch(SQLException e){
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Acessorio_VeiculoED ed)");
        }

        return list;
      }



    public Acessorio_VeiculoED getByRecord(Acessorio_VeiculoED ed)throws Excecoes{

        String sql = null;
        Acessorio_VeiculoED edVolta = new Acessorio_VeiculoED();
        FormataDataBean dataFormatada = new FormataDataBean();

        try{
          sql = "Select Acessorios_Veiculos.oid_Acessorio_Veiculo, "+
                " Acessorios_Veiculos.oid_Veiculo, "+
                " Acessorios_Veiculos.oid_Tipo_Acessorio, "+
                " Acessorios_Veiculos.vl_quantidade, "+
                " Tipos_Acessorios.nm_Tipo_Acessorio "+
                " From  Acessorios_Veiculos, Tipos_Acessorios "+
                " Where Acessorios_Veiculos.oid_Tipo_Acessorio=Tipos_Acessorios.oid_Tipo_Acessorio" ;
	      if (String.valueOf(ed.getOid_Acessorio_Veiculo()) != null &&
	              !String.valueOf(ed.getOid_Acessorio_Veiculo()).equals("") &&
	              !String.valueOf(ed.getOid_Acessorio_Veiculo()).equals("null") &&
	              !String.valueOf(ed.getOid_Acessorio_Veiculo()).equals("0")){
	            sql += " AND oid_Acessorio_Veiculo = '" + ed.getOid_Acessorio_Veiculo() + "'";
	          }

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      double valor = 0;
	      //popula
	      while (res.next()){
		  edVolta.setOid_Acessorio_Veiculo(res.getString("oid_Acessorio_Veiculo"));
		  edVolta.setOid_Tipo_Acessorio(res.getLong("oid_Tipo_Acessorio"));
		  edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
		  edVolta.setVL_Quantidade(res.getDouble("vl_quantidade"));
		  edVolta.setNM_Acessorio_Veiculo(res.getString("nm_Tipo_Acessorio"));
	      }
        }
        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao selecionar");
          excecoes.setMetodo("getByRecord_VeiculoxAcessorio()");
          excecoes.setExc(exc);
          throw excecoes;
        }

        return edVolta;
      }

}
