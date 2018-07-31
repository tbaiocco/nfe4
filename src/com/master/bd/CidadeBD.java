package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.CidadeED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class CidadeBD  {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public CidadeBD(ExecutaSQL sql) {
      this.executasql = sql;
      util = new Utilitaria(this.executasql);
    }

    public CidadeED getByRecord(CidadeED ed) throws Excecoes {

        String sql = null;

        CidadeED edVolta = new CidadeED();

        try {

            sql = "SELECT * FROM CIDADES, REGIOES_ESTADOS, ESTADOS, Regioes_Paises, Paises   WHERE 1=1";
            sql += " AND CIDADES.OID_REGIAO_ESTADO  = REGIOES_ESTADOS.OID_REGIAO_ESTADO ";
            sql += " AND REGIOES_ESTADOS.OID_ESTADO = ESTADOS.OID_ESTADO  ";
            sql += " AND ESTADOS.OID_Regiao_Pais = Regioes_Paises.OID_ESTADO  ";
            sql += " AND Regioes_Paises.OID_Pais = Paises.OID_Pais  ";


            if (ed.getOID_Cidade() > 0) {
                sql += " and OID_Cidade = " + ed.getOID_Cidade();
            }
            if (util.doValida(ed.getNM_Cidade())) {
                sql += " and NM_Cidade LIKE '" + ed.getNM_Cidade() + "%'";
            }
            if (util.doValida(ed.getCD_Estado())) {
                sql += " AND ESTADOS.CD_ESTADO = '" + ed.getCD_Estado() + "'";
            }

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new CidadeED();
                edVolta.setOID_Cidade(res.getLong("oid_Cidade"));
                edVolta.setNM_Cidade(res.getString("NM_Cidade"));
                edVolta.setCD_Estado(res.getString("CD_Estado"));
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(CidadeED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return edVolta;
    }

  	public List lista(CidadeED ed)
  	throws Excecoes{
  	    String sql =
  	        " select Cidades.oid_Cidade, " +
  	        "        Cidades.NM_Cidade , " +
  	        "        Regioes_Estados.oid_Regiao_Estado, " +
  	        "        Regioes_Estados.CD_Regiao_Estado, " +
  	        "        Regioes_Estados.NM_Regiao_Estado, " +
  	        "        Estados.oid_Estado, " +
  	        "        Estados.CD_Estado, " +
  	        "        Estados.NM_Estado " +
  	        " from Cidades, " +
  	        "      Regioes_Estados, " +
  	        "      Estados " +
  	        " where Cidades.oid_Regiao_Estado = Regioes_Estados.oid_Regiao_Estado " +
  	        "   and Regioes_Estados.oid_Estado = Estados.oid_Estado ";
  	    if (ed.getOID_Cidade() > 0) {
  	        sql += " and Cidades.oid_Cidade = " + ed.getOID_Cidade();
  	    }
  	    if (ed.getOid_regiao_estado() > 0) {
  	        sql += " and Regioes_Estados.oid_Regiao_Estado = " + ed.getOid_regiao_estado();
  	    }
  	    if (ed.getOid_estado() > 0) {
  	      sql += " and Estados.oid_Estado = " + ed.getOid_estado();
  	    }
  	    sql +=
  	        " order by Regioes_Estados.CD_Regiao_Estado, " +
  	    	"          Cidades.NM_Cidade ";
  	    ResultSet res = executasql.executarConsulta(sql);
  	    try {
  	        List lista = new ArrayList();
            while (res.next()) {
                CidadeED edVolta = new CidadeED();
                edVolta.setOID_Cidade(res.getLong("oid_Cidade"));
                edVolta.setNM_Cidade(res.getString("NM_Cidade"));
                edVolta.setOid_regiao_estado(res.getInt("oid_Regiao_Estado"));
                edVolta.setCd_regiao_estado(res.getString("CD_Regiao_Estado"));
                edVolta.setNm_regiao_estado(res.getString("NM_Regiao_Estado"));
                edVolta.setOid_estado(res.getInt("oid_Estado"));
                edVolta.setCD_Estado(res.getString("CD_Estado"));
                edVolta.setNM_Estado(res.getString("NM_Estado"));
                lista.add(edVolta);
            }
            return lista;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(CidadeED ed)");
        }
	}

  	public boolean isMesmoEstado(int oid_CidadeOrigem, int oid_CidadeDestino) throws Excecoes {

  	    try {
	  	    if (oid_CidadeOrigem < 1)
	  	        throw new Mensagens("Cidade de Origem n�o informada!");
	  	    if (oid_CidadeDestino < 1)
		        throw new Mensagens("Cidade de Destino n�o informada!");

	  	    int oid_EstadoOrigem = util.getTableIntValue("oid_Estado",
	  	                                                 "Regioes_Estados",
                                                         "Cidades.oid_Cidade = "+oid_CidadeOrigem+
                                                         " AND Cidades.oid_Regiao_Estado = Regioes_Estados.oid_Regiao_Estado");
	  	    int oid_EstadoDestino = util.getTableIntValue("oid_Estado",
	  	                                                  "Regioes_Estados",
                                                          "Cidades.oid_Cidade = "+oid_CidadeDestino+
                                                          " AND Cidades.oid_Regiao_Estado = Regioes_Estados.oid_Regiao_Estado");

	  	    if (oid_EstadoOrigem < 1)
		        throw new Mensagens("Estado de Origem n�o encontrado!");
		    if (oid_EstadoDestino < 1)
		        throw new Mensagens("Estado de Destino n�o encontrado!");

		    return (oid_EstadoOrigem == oid_EstadoDestino);
        } catch (Excecoes e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "isMesmoEstado");
        }
	}
  	public boolean isMesmoEstado(String oid_PessoaOrigem, String oid_PessoaDestino) throws Excecoes {

  	    try {
	  	    if (!JavaUtil.doValida(oid_PessoaOrigem))
	  	        throw new Mensagens("Pessoa de Origem n�o informada!");
	  	    if (!JavaUtil.doValida(oid_PessoaDestino))
		        throw new Mensagens("Pessoa de Destino n�o informada!");

		    return this.isMesmoEstado(util.getTableIntValue("oid_Cidade","Pessoas","oid_Pessoa = '"+oid_PessoaOrigem+"'"),
		            				  util.getTableIntValue("oid_Cidade","Pessoas","oid_Pessoa = '"+oid_PessoaDestino+"'"));
        } catch (Excecoes e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "isMesmoEstado");
        }
	}

  	/** ------------ RELAT�RIOS ---------------- */
    //*** Relat�rio de Cidades
  	public void relCidades(CidadeED ed) throws Excecoes {
  	    ed.setLista(lista(ed));
        //*** Chama o Gerador de Relat�rios Jasper
    }
}
