/*
 * Created on 05/04/2005
 *
 */
package com.master.bd;

/**
 * @author: Tiago Sauter Lauxen
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.Manutencao_PreventivaED;
import com.master.rl.JasperRL;
import com.master.util.Utilitaria;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Manutencao_PreventivaBD extends Utilitaria {

    private ExecutaSQL executasql;

	public Manutencao_PreventivaBD(ExecutaSQL sql) {
	    this.executasql = sql;
	}

  	public List lista(Manutencao_PreventivaED ed)
  	throws Excecoes{

  	    String sql =
              " select Manutencoes_Preventivas.oid_Veiculo, " +
              "        Manutencoes_Preventivas.DT_Servico, " +
              "        Manutencoes_Preventivas.NR_Kilometragem_Realizada, " +
              "        Manutencoes_Preventivas.NR_Kilometragem_Prevista, " +
              "        Manutencoes_Preventivas.oid_Tipo_Servico, " +
              "        Tipos_Servicos.NM_Tipo_Servico, " +
              "        Veiculos.NR_Kilometragem_Atual " +
              " from Manutencoes_Preventivas, " +
              "      Tipos_Servicos, " +
              "      Veiculos " +
              "      left join Complementos_Veiculos " +
              "        on Veiculos.oid_Veiculo = Complementos_Veiculos.oid_Veiculo " +
              " where Manutencoes_Preventivas.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico " +
  	        "   and Manutencoes_Preventivas.oid_Veiculo = Veiculos.oid_Veiculo ";
  	    if (ed.getOid_tipo_servico() > 0) {
  	        sql += " and Manutencoes_Preventivas.oid_Tipo_Servico = " + ed.getOid_tipo_servico();
  	    }
  	    if (doValida(ed.getOid_veiculo())) {
  	        sql += " and Manutencoes_Preventivas.oid_Veiculo = " + JavaUtil.getSQLString(ed.getOid_veiculo());
  	    }
  	    if ("Pendente".equals(ed.getDm_situacao())) {
  	        sql += " and Manutencoes_Preventivas.NR_Kilometragem_Prevista < Veiculos.NR_Kilometragem_Atual ";
  	    } else if ("Ok".equals(ed.getDm_situacao())) {
  	      sql += " and Manutencoes_Preventivas.NR_Kilometragem_Prevista >= Veiculos.NR_Kilometragem_Atual ";
  	    }

            if (!"X".equals(ed.getDM_Procedencia())){
  	      sql += " and Complementos_Veiculos.dm_procedencia= '" + ed.getDM_Procedencia() + "'";
            }
              
              
  	    sql += " order by Manutencoes_Preventivas.oid_Veiculo ";

  // System.out.println(sql);

            ResultSet res = executasql.executarConsulta(sql);
  	    try {
  	        List lista = new ArrayList();
            while (res.next()) {
                Manutencao_PreventivaED edVolta = new Manutencao_PreventivaED();
                edVolta.setOid_veiculo(res.getString("oid_Veiculo"));
                edVolta.setOid_tipo_servico(res.getInt("oid_Tipo_Servico"));
                edVolta.setNm_tipo_servico(res.getString("NM_Tipo_Servico"));
                edVolta.setDt_ultimo_servico(FormataData.formataDataBT(res.getDate("DT_Servico")));
                edVolta.setNr_kilometragem_realizada(res.getInt("NR_Kilometragem_Realizada"));
                edVolta.setNr_kilometragem_prevista(res.getInt("NR_Kilometragem_Prevista"));
                edVolta.setNr_kilometragem_atual(res.getInt("NR_Kilometragem_Atual"));
                if (edVolta.getNr_kilometragem_prevista() < edVolta.getNr_kilometragem_atual()) {
                    edVolta.setDm_situacao("Pendente");
                } else {
                    edVolta.setDm_situacao("Ok");
                }
                lista.add(edVolta);
            }
            return lista;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Manutencao_PreventivaED ed)");
        }
	}

  	/** ------------ RELATÓRIOS ---------------- */
    //*** Relatório de Manutenções Preventivas por veículo
  	public void relManutencaoPreventivaVeiculo(Manutencao_PreventivaED ed) throws Excecoes {
  	    ed.setLista(lista(ed));
        //*** Chama o Gerador de Relatórios Jasper
        new JasperRL(ed).geraRelatorio();
    }
}
