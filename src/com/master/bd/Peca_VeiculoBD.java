package com.master.bd;

/**
 * <p>Title: Peca_VeiculoBD </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Peca_VeiculoED;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Peca_VeiculoBD {

  private ExecutaSQL executasql;
    FormataDataBean dataFormatada = new FormataDataBean();

  public Peca_VeiculoBD(ExecutaSQL sql) {

    this.executasql = sql;
  }




    public Peca_VeiculoED inclui(Peca_VeiculoED ed) throws Excecoes{

        String sql = null;
        long valOid = 0;
        String chave = null;
        FormataDataBean dataFormatada = new FormataDataBean();

        try{

          sql = " insert into Pecas_Veiculos (oid_Peca_Veiculo, oid_Tipo_Acessorio, oid_Movimento_Ordem_Servico, oid_Veiculo, " +
          		"dt_stamp, usuario_stamp, dt_garantia, DM_Situacao, dm_stamp, vl_quantidade, km_garantia) values ";
          sql += "('" + ed.getOid_Peca_Veiculo() + "'," + ed.getOid_Tipo_Acessorio() + "," + ed.getOid_Movimento_Ordem_Servico() + ",'" + ed.getOid_Veiculo() + "','" +
                  ed.getDt_stamp()+"','"+ed.getUsuario_Stamp() +"','"+ed.getDT_Garantia()+"','"+ed.getDM_Situacao() +"','"+ed.getDm_Stamp()+"'," + ed.getVL_Quantidade() +"," + ed.getKM_Garantia() + ")";

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

    public void altera(Peca_VeiculoED ed) throws Excecoes{

        String sql = null;
        FormataDataBean dataFormatada = new FormataDataBean();

        try{
          sql = "update Pecas_Veiculos "+
                "set km_garantia = "+ed.getKM_Garantia()+
                ", dt_garantia ='"+ed.getDT_Garantia()+"'"+
                ", DM_Situacao ='"+ed.getDM_Situacao()+"'"+
                ", dt_stamp ='"+Data.getDataDMY()+"'"+
                ", usuario_stamp ='"+ed.getUsuario_Stamp()+"'"+
                ", dm_stamp ='S' "+
                "Where OID_Peca_Veiculo ='"+ed.getOid_Peca_Veiculo()+"'";
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

    public void deleta(Peca_VeiculoED ed) throws Excecoes{

        String sql = null;
        String DM_Impresso = null;

        try{
          sql = "delete from Pecas_Veiculos Where oid_Peca_Veiculo ='"+ed.getOid_Peca_Veiculo() + "'";

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

    public ArrayList lista(Peca_VeiculoED ed)
    throws Excecoes{

        String sql = null;
        ArrayList list = new ArrayList();
        FormataDataBean dataFormatada = new FormataDataBean();

    // System.out.println("Lista Acessorio bd");

        try{
          sql = " Select Pecas_Veiculos.oid_Peca_Veiculo, "+
                " Movimentos_Ordens_Servicos.dt_movimento_ordem_servico, "+
                " Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico, "+
                " Pecas_Veiculos.oid_Veiculo, "+
                " Pecas_Veiculos.oid_Tipo_Acessorio, "+
                " Pecas_Veiculos.dt_garantia, "+
                " Pecas_Veiculos.km_garantia, "+
                " Tipos_Acessorios.NM_Tipo_Acessorio, "+
                " Pecas_Veiculos.vl_quantidade, "+
                " Pecas_Veiculos.dm_Situacao "+
                " From  Pecas_Veiculos, Tipos_Acessorios, Movimentos_Ordens_Servicos  "+
                " Where Pecas_Veiculos.oid_Tipo_Acessorio=Tipos_Acessorios.oid_Tipo_Acessorio" +
                " AND   Pecas_Veiculos.oid_Movimento_Ordem_Servico=Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico" ;

          if (ed.getOid_Veiculo() != null &&
                  !ed.getOid_Veiculo().equals("") &&
                  !ed.getOid_Veiculo().equals("null")){
                sql += " AND Pecas_Veiculos.oid_Veiculo = '" + ed.getOid_Veiculo() + "'";
              }
          if (ed.getOid_Movimento_Ordem_Servico()>0) {
                sql += " AND Pecas_Veiculos.oid_Movimento_Ordem_Servico = '" + ed.getOid_Movimento_Ordem_Servico() + "'";
              }
          sql += " ORDER BY oid_Tipo_Acessorio";

	    // System.out.println("Lista Acessorio bd - " + sql);

          ResultSet res = executasql.executarConsulta(sql);
          double valor = 0;
          //popula
          while (res.next()){
	    // System.out.println("Lista Acessorio wh 1");

            Peca_VeiculoED edVolta = new Peca_VeiculoED();
            edVolta.setOid_Peca_Veiculo(res.getString("oid_Peca_Veiculo"));
            edVolta.setOid_Tipo_Acessorio(res.getLong("oid_Tipo_Acessorio"));
            edVolta.setOid_Movimento_Ordem_Servico(res.getLong("oid_Movimento_Ordem_Servico"));
            edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
            edVolta.setKM_Garantia(res.getLong("KM_Garantia"));
            edVolta.setVL_Quantidade(res.getDouble("vl_quantidade"));
            edVolta.setNM_Peca_Veiculo(res.getString("NM_Tipo_Acessorio"));
	    edVolta.setDM_Situacao(res.getString("DM_Situacao"));
            if ("N".equals(edVolta.getDM_Situacao())) edVolta.setNM_Situacao("Nova Original");
            if ("S".equals(edVolta.getDM_Situacao())) edVolta.setNM_Situacao("Nova Segunda Linha");
            if ("R".equals(edVolta.getDM_Situacao())) edVolta.setNM_Situacao("Recuperada/Retificada");
            if ("U".equals(edVolta.getDM_Situacao())) edVolta.setNM_Situacao("Usada");

	    dataFormatada.setDT_FormataData(res.getString("DT_Garantia"));
	    edVolta.setDT_Garantia(dataFormatada.getDT_FormataData());
            dataFormatada.setDT_FormataData(res.getString("dt_movimento_ordem_servico"));
            edVolta.setDT_Servico(dataFormatada.getDT_FormataData());

            list.add(edVolta);
          }
        }
        catch(SQLException e){
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Peca_VeiculoED ed)");
        }

        return list;
      }



    public Peca_VeiculoED getByRecord(Peca_VeiculoED ed)throws Excecoes{

        String sql = null;
        Peca_VeiculoED edVolta = new Peca_VeiculoED();
        FormataDataBean dataFormatada = new FormataDataBean();

        // System.out.println("getByRecord BD->>" );


        try{
          sql = " Select Pecas_Veiculos.oid_Peca_Veiculo, "+
                " Movimentos_Ordens_Servicos.dt_movimento_ordem_servico, "+
                " Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico, "+
                " Pecas_Veiculos.oid_Veiculo, "+
                " Pecas_Veiculos.oid_Tipo_Acessorio, "+
                " Pecas_Veiculos.dt_garantia, "+
                " Pecas_Veiculos.km_garantia, "+
                " Tipos_Acessorios.NM_Tipo_Acessorio, "+
                " Pecas_Veiculos.vl_quantidade, "+
                " Pecas_Veiculos.dm_Situacao "+
                " From  Pecas_Veiculos, Tipos_Acessorios, Movimentos_Ordens_Servicos  "+
                " Where Pecas_Veiculos.oid_Tipo_Acessorio=Tipos_Acessorios.oid_Tipo_Acessorio" +
                " AND   Pecas_Veiculos.oid_Movimento_Ordem_Servico=Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico" ;

	      if (String.valueOf(ed.getOid_Peca_Veiculo()) != null &&
	              !String.valueOf(ed.getOid_Peca_Veiculo()).equals("") &&
	              !String.valueOf(ed.getOid_Peca_Veiculo()).equals("null") &&
	              !String.valueOf(ed.getOid_Peca_Veiculo()).equals("0")){
	            sql += " AND oid_Peca_Veiculo = '" + ed.getOid_Peca_Veiculo() + "'";
	          }

                  // System.out.println("getByRecord ->> 1" + sql);

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      double valor = 0;
	      //popula
	      while (res.next()){
                // System.out.println("Lista Acessorio ->> 1");

                edVolta.setOid_Peca_Veiculo(res.getString("oid_Peca_Veiculo"));
                edVolta.setOid_Tipo_Acessorio(res.getLong("oid_Tipo_Acessorio"));
                edVolta.setOid_Movimento_Ordem_Servico(res.getLong("oid_Movimento_Ordem_Servico"));
                edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
                edVolta.setKM_Garantia(res.getLong("KM_Garantia"));
                edVolta.setVL_Quantidade(res.getDouble("vl_quantidade"));
                edVolta.setNM_Peca_Veiculo(res.getString("NM_Tipo_Acessorio"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                dataFormatada.setDT_FormataData(res.getString("DT_Garantia"));
                edVolta.setDT_Garantia(dataFormatada.getDT_FormataData());
                dataFormatada.setDT_FormataData(res.getString("dt_movimento_ordem_servico"));
                edVolta.setDT_Servico(dataFormatada.getDT_FormataData());
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
