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

import com.master.ed.EDI_DuplicataED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;


public class EDI_DuplicataBD extends Transacao {

  private ExecutaSQL executasql;

  public EDI_DuplicataBD(ExecutaSQL sql) {
    this.executasql = sql;
  }



  public ArrayList gera_EDI_Duplicata(EDI_DuplicataED edComp)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    EDI_DuplicataED ed = (EDI_DuplicataED)edComp;

  // System.err.println("edi bd 1");

      try{

             sql =  " SELECT " +
             		"Duplicatas.NR_Duplicata," +
             		"Duplicatas.DT_Emissao," +
             		"Duplicatas.DT_Vencimento," +
             		"Conhecimentos.NR_Conhecimento," +
             		"Duplicatas.VL_Duplicata," +
  				"Conhecimentos.VL_Pedagio," +
  				"Conhecimentos.vl_frete_peso," +
             		"Conhecimentos.vl_frete_valor "+
                 " from Conhecimentos, Duplicatas, Origens_Duplicatas "+
                 " WHERE Conhecimentos.oid_Conhecimento = Origens_Duplicatas.oid_Conhecimento "+
                 " AND Duplicatas.oid_Duplicata = Origens_Duplicatas.oid_Duplicata "+
                 " AND Origens_Duplicatas.dm_situacao = 'A' ";
              // System.err.println("->>>> "  + sql);
//            data//edi_duplicata//duplicata.txt
              // System.err.println("dt ini ->>>> "  + ed.getDT_Inicial());


            if (String.valueOf(ed.getOid_Unidade()) != null &&
              !String.valueOf(ed.getOid_Unidade()).equals("0")){
              sql += " and Duplicatas.Oid_Unidade = " + ed.getOid_Unidade();
            }

            if (String.valueOf(ed.getOid_Pessoa()) != null &&
                  !String.valueOf(ed.getOid_Pessoa()).equals("") &&
                  !String.valueOf(ed.getOid_Pessoa()).equals("null")){
                  sql += " and Duplicatas.oid_pessoa = '" + ed.getOid_Pessoa() + "'";
                }

            if (String.valueOf(ed.getDT_Inicial()) != null &&
              !String.valueOf(ed.getDT_Inicial()).equals("") &&
              !String.valueOf(ed.getDT_Inicial()).equals("null")){
              sql += " and Duplicatas.DT_Emissao >= '" + ed.getDT_Inicial() + "'";
            }
            if (String.valueOf(ed.getDT_Final()) != null &&
            !String.valueOf(ed.getDT_Final()).equals("") &&
            !String.valueOf(ed.getDT_Final()).equals("null")){
              sql += " and Duplicatas.DT_Emissao <= '" + ed.getDT_Final() + "'";
            }

            sql += " order by Duplicatas.dt_emissao, Duplicatas.nr_Duplicata ";
            // System.err.println(sql);

        ResultSet res = null;
        ResultSet res2 = null;

        res = this.executasql.executarConsulta(sql);

        FormataDataBean dataFormatada = new FormataDataBean();
        while (res.next()){
          EDI_DuplicataED edVolta = new EDI_DuplicataED();

            long nr_cto=1000000+res.getLong("NR_Duplicata");
            String cto=String.valueOf(nr_cto);

            edVolta.setNR_Duplicata(cto.substring(1,7));

            dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
            edVolta.setDT_Emissao_Duplicata(dataFormatada.getDT_FormataData());

            dataFormatada.setDT_FormataData(res.getString("DT_Vencimento"));
            edVolta.setDT_Vencimento_Duplicata(dataFormatada.getDT_FormataData());

            nr_cto=1000000+res.getLong("NR_Conhecimento");
            cto=String.valueOf(nr_cto);

            edVolta.setNR_Conhecimento(cto.substring(1,7));

            edVolta.setVL_Duplicata(res.getDouble("Vl_Duplicata"));
            edVolta.setVL_Frete_Peso(res.getDouble("Vl_frete_peso"));
            edVolta.setVL_Frete_Valor(res.getDouble("Vl_frete_valor"));
            edVolta.setVL_Pedagio(res.getDouble("VL_Pedagio"));


          list.add(edVolta);
        }
     // System.err.println("->>>> 999 " );

      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao gerar REL duplicata");
        excecoes.setMetodo("gera_EDI_Duplicata");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return list;
    }

  public ArrayList gera_EDI_Cliente(EDI_DuplicataED edComp)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    EDI_DuplicataED ed = (EDI_DuplicataED)edComp;

  // System.err.println("edi bd 1");

      try{

             sql =  " SELECT " +
		      		"Pessoas.NR_CNPJ_CPF," +
		     		"Pessoas.nm_razao_social," +
		     		"Pessoas.nm_endereco," +
		     		"Pessoas.nm_bairro," +
		     		"Pessoas.nr_cep," +
		     		"Pessoas.nr_telefone," +
		     		"Pessoas.nm_inscricao_estadual," +
		     		"Cidades.nm_cidade," +
		     		"Estados.cd_estado" +
                 " from Pessoas, Clientes, Cidades, Estados, Regioes_Estados "+
                 " WHERE Pessoas.oid_pessoa = Clientes.oid_pessoa "+
                 " AND Pessoas.oid_cidade = Cidades.oid_Cidade "+
                 " AND Regioes_Estados.oid_regiao_estado = Cidades.oid_regiao_estado "+
                 " AND Regioes_Estados.oid_estado = Estados.oid_estado ";

				 // System.err.println("->>>> "  + sql);

            if (String.valueOf(ed.getOid_Pessoa()) != null &&
                  !String.valueOf(ed.getOid_Pessoa()).equals("") &&
                  !String.valueOf(ed.getOid_Pessoa()).equals("null")){
                  sql += " and Pessoas.oid_pessoa = '" + ed.getOid_Pessoa() + "'";
                }

            if (String.valueOf(ed.getDT_Inicial()) != null &&
                    !String.valueOf(ed.getDT_Inicial()).equals("") &&
                    !String.valueOf(ed.getDT_Inicial()).equals("null")){
                    sql += " and Clientes.DT_Cadastro >= '" + ed.getDT_Inicial() + "'";
                  }
                  if (String.valueOf(ed.getDT_Final()) != null &&
                  !String.valueOf(ed.getDT_Final()).equals("") &&
                  !String.valueOf(ed.getDT_Final()).equals("null")){
                    sql += " and Clientes.DT_Cadastro <= '" + ed.getDT_Final() + "'";
                  }

            sql += " order by Pessoas.NM_Razao_Social ";
            // System.err.println(sql);

        ResultSet res = null;
        ResultSet res2 = null;

        res = this.executasql.executarConsulta(sql);

        FormataDataBean dataFormatada = new FormataDataBean();
        while (res.next()){
          EDI_DuplicataED edVolta = new EDI_DuplicataED();

            //long nr_cto=1000000+res.getLong("NR_Duplicata");
            //String cto=String.valueOf(nr_cto);

            //edVolta.setNR_Duplicata(cto.substring(1,7));

            //dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
            //edVolta.setDT_Emissao_Duplicata(dataFormatada.getDT_FormataData());

          edVolta.setOid_Pessoa(res.getString("NR_CNPJ_CPF"));
          edVolta.setNM_Razao_Social(res.getString("nm_razao_social"));
          edVolta.setNM_Endereco(res.getString("nm_endereco"));
          edVolta.setNM_Bairro(res.getString("nm_bairro"));
          edVolta.setNR_CEP(res.getString("nr_cep"));
          edVolta.setNR_Telefone(res.getString("nr_telefone"));
          edVolta.setNM_Inscricao_Estadual(res.getString("nm_inscricao_estadual"));
          edVolta.setNM_Cidade(res.getString("nm_cidade"));
          edVolta.setNM_Estado(res.getString("cd_estado"));

          list.add(edVolta);
        }
     // System.err.println("->>>> 999 " );

      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao gerar REL duplicata");
        excecoes.setMetodo("gera_EDI_Duplicata");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return list;
    }

}
