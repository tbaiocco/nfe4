package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Programacao_CargaED;
import com.master.rn.Pedido_CargaRN;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Programacao_CargaBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Programacao_CargaBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Programacao_CargaED inclui(Programacao_CargaED ed) throws Excecoes {

        String sql = null;
        Programacao_CargaED edVolta = new Programacao_CargaED();

        try {

            //*** Pega o valor m�ximo
            edVolta.setOid_Programacao_Carga(getAutoIncremento("oid_Programacao_Carga", "programacoes_cargas"));
            edVolta.setOid_Pedido_Carga(ed.getOid_Pedido_Carga());

            sql = "insert into programacoes_cargas (" +
                  "oid_Programacao_Carga, " +
                  "oid_Pedido_Carga, oid_Motorista, " +
                  "oid_Veiculo, oid_Carreta, oid_Carreta2," +
                  "nr_Volumes, dm_Veiculo, " +
                  "dt_Programacao, dt_Carga_Efetiva, " +
                  "dm_Situacao, dm_Motivo_Cancelamento, " +
                  "vl_frete, dm_carga, " +
                  "vl_tarifa, vl_seguro, dm_icms, dm_descarga, " +
                  "dt_Saida, dt_Entrega, hr_Entrega, " +
                  "tx_Observacao, oid_unidade, " +
                  "usuario_stamp, dt_stamp, " +
                  "dm_stamp) values (" +
                  edVolta.getOid_Programacao_Carga() + ", " +
                  ed.getOid_Pedido_Carga() + ",'" +
                  ed.getOid_Motorista() + "','" +
                  ed.getOid_Veiculo() + "','" +
                  ed.getOid_Carreta() + "','" +
                  ed.getOid_Carreta2() + "'," +
                  ed.getNr_Volumes() + ",'" +
                  ed.getDm_Veiculo() + "'," +
                  getSQLDate(ed.getDt_Programacao()) + "," +
                  getSQLDate(ed.getDt_Carga_Efetiva()) + "," +
                  "'A', '', " +
                  ed.getVl_Frete() + ",'" + ed.getDm_Carga() + "'," +
                  ed.getVl_Tarifa() + "," + ed.getVl_Seguro() + ",'" +
                  ed.getDm_ICMS() + "','" + ed.getDm_Descarga() + "'," +
                  getSQLDate(ed.getDt_Saida()) + "," +
                  getSQLDate(ed.getDt_Entrega()) + ",'" +
                  ed.getHr_Entrega() + "','" +
                  ed.getTx_Observacao() + "'," +
                  ed.getOid_Unidade() + ",'" +
                  null + "','" + Data.getDataDMY() + "','N')";

            executasql.executarUpdate(sql);

        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao incluir Programa��o Carga",exc,this.getClass().getName(),"inclui");
        }
        return edVolta;
    }

    public void altera(Programacao_CargaED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " update programacoes_cargas set ";
            sql += " oid_Motorista= '" + ed.getOid_Motorista() + "'";
            sql += ", oid_Veiculo= '" + ed.getOid_Veiculo() + "'";
            sql += ", oid_Carreta= '" + ed.getOid_Carreta() + "'";
            sql += ", oid_Carreta2= '" + ed.getOid_Carreta2() + "'";
            sql += ", nr_Volumes= " + ed.getNr_Volumes();
            //sql += ", dm_Veiculo= '" + ed.getDm_Veiculo() + "'";
            sql += ", dt_Programacao= " + getSQLDate(ed.getDt_Programacao());
            sql += ", dt_Carga_Efetiva= " + getSQLDate(ed.getDt_Carga_Efetiva());
            sql += ", dm_Motivo_Cancelamento= '" + ed.getDm_Motivo_Cancelamento() + "'";
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";

            sql += ", dm_Carga= '" + ed.getDm_Carga() + "'";
            sql += ", vl_Frete= " + ed.getVl_Frete();
            sql += ", oid_Unidade= " + ed.getOid_Unidade();
            sql += ", dt_Saida= " + getSQLDate(ed.getDt_Saida());
            sql += ", dt_Entrega= " + getSQLDate(ed.getDt_Entrega());
            sql += ", hr_Entrega= '" + ed.getHr_Entrega() + "'";
            sql += ", tx_Observacao= '" + ed.getTx_Observacao() + "'";

            sql += " where oid_programacao_carga = " + ed.getOid_Programacao_Carga();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
        	throw new Excecoes("Erro ao alterar dados de Programa��o Carga",exc,this.getClass().getName(),"altera");
        }
    }

    public void deleta(Programacao_CargaED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " delete from programacoes_cargas " +
            	   " where oid_Programacao_Carga = " + ed.getOid_Programacao_Carga();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
        	throw new Excecoes("Erro ao deletar Programa��o Carga",exc,this.getClass().getName(),"deleta");
        }
    }

    public ArrayList lista(Programacao_CargaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select programacoes_cargas.* " +
            	  " from programacoes_cargas " +
            	  " where 1 = 1 ";

            if(doValida(String.valueOf(ed.getOid_Programacao_Carga())))
            	sql += " and programacoes_cargas.oid_programacao_carga = " + ed.getOid_Programacao_Carga();
            if(doValida(String.valueOf(ed.getOid_Pedido_Carga())))
            	sql += " and programacoes_cargas.oid_pedido_carga = " + ed.getOid_Pedido_Carga();
            if(doValida(String.valueOf(ed.getOid_Motorista())))
            	sql += " and programacoes_cargas.oid_motorista = '" + ed.getOid_Motorista() + "'";
            if(doValida(String.valueOf(ed.getOid_Veiculo())))
            	sql += " and programacoes_cargas.oid_veiculo = '" + ed.getOid_Veiculo() + "'";
            if(doValida(String.valueOf(ed.getDm_Situacao())) && !"T".equals(ed.getDm_Situacao()) && !"X".equals(ed.getDm_Situacao()))
            	sql += " and programacoes_cargas.dm_situacao = '" + ed.getDm_Situacao() + "'";
            if(doValida(String.valueOf(ed.getDm_Situacao())) && "X".equals(ed.getDm_Situacao()))
            	sql += " and programacoes_cargas.dm_situacao != 'C'";
            if(doValida(String.valueOf(ed.getDm_Veiculo())) && !"T".equals(ed.getDm_Veiculo()))
            	sql += " and programacoes_cargas.dm_veiculo = '" + ed.getDm_Veiculo() + "'";
            if(doValida(String.valueOf(ed.getDt_Programacao())))
            	sql += " and programacoes_cargas.dt_Programacao >= '" + ed.getDt_Programacao() + "'";
            if(doValida(String.valueOf(ed.getDt_Programacao_Final())))
            	sql += " and programacoes_cargas.dt_Programacao <= '" + ed.getDt_Programacao_Final() + "'";
            if(doValida(String.valueOf(ed.getDt_Carga_Efetiva())))
            	sql += " and programacoes_cargas.dt_Carga_Efetiva >= '" + ed.getDt_Carga_Efetiva() + "'";
            if(doValida(String.valueOf(ed.getDt_Carga_Efetiva_Final())))
            	sql += " and programacoes_cargas.dt_Carga_Efetiva <= '" + ed.getDt_Carga_Efetiva_Final() + "'";
            if(doValida(String.valueOf(ed.getOid_Unidade())))
            	sql += " and programacoes_cargas.oid_unidade = " + ed.getOid_Unidade();

            sql += " order by programacoes_cargas.dt_Saida, programacoes_cargas.oid_pedido_carga, programacoes_cargas.oid_programacao_carga, programacoes_cargas.dm_situacao ";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	Programacao_CargaED edVolta = new Programacao_CargaED();

                edVolta.setOid_Programacao_Carga(res.getLong("oid_Programacao_Carga"));
                edVolta.setOid_Pedido_Carga(res.getLong("oid_Pedido_Carga"));
                //edVolta.setNr_Volumes(res.getDouble("nr_Volumes"));
                edVolta.setNr_Volumes_TX(FormataValor.formataValorBT(res.getDouble("nr_Volumes"), 2));
                edVolta.setDm_Veiculo(res.getString("dm_Veiculo"));
                edVolta.setDt_Programacao(FormataData.formataDataBT(res.getString("dt_programacao")));
                edVolta.setDt_Carga_Efetiva(FormataData.formataDataBT(res.getString("dt_Carga_Efetiva")));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
                edVolta.setDm_Motivo_Cancelamento(res.getString("dm_Motivo_Cancelamento"));
                edVolta.setOid_Carreta(JavaUtil.doValida(res.getString("Oid_Carreta")) ? res.getString("Oid_Carreta") : "");
                edVolta.setOid_Carreta2(JavaUtil.doValida(res.getString("Oid_Carreta2")) ? res.getString("Oid_Carreta2") : "");

                edVolta.setVl_Frete(res.getDouble("vl_Frete"));
                edVolta.setDm_Carga(res.getString("dm_Carga"));
                edVolta.setDt_Saida(FormataData.formataDataBT(res.getString("dt_Saida")));
                edVolta.setDt_Entrega(FormataData.formataDataBT(res.getString("dt_Entrega")));
                edVolta.setHr_Entrega(res.getString("hr_Entrega"));
                edVolta.setTx_Observacao(res.getString("tx_Observacao"));

                edVolta.setVl_Tarifa(res.getDouble("vl_Tarifa"));
                edVolta.setVl_Seguro(res.getDouble("vl_Seguro"));
                edVolta.setDm_ICMS(res.getString("dm_ICMS"));
                edVolta.setDm_Descarga(res.getString("dm_descarga"));

//                edVolta.setOid_Unidade(res.getLong("oid_Unidade"));
                UnidadeBean unidade = UnidadeBean.getByOID_Unidade(res.getInt("oid_Unidade"));
                edVolta.setOid_Unidade(unidade.getOID_Unidade());
                edVolta.setCd_Unidade(unidade.getCD_Unidade());
                edVolta.setNm_Unidade(unidade.getNM_Fantasia());

//              Veiculo
                String sqlBusca = "SELECT oid_Veiculo, nr_Placa from Veiculos " +
								  " where oid_Veiculo = '" + res.getString("oid_Veiculo") + "'";
				ResultSet rs = this.executasql.executarConsulta(sqlBusca);
				if(rs.next()){
	                edVolta.setOid_Veiculo(rs.getString(1));
	                edVolta.setNr_Placa(rs.getString(1));
				} else {
					edVolta.setOid_Veiculo("");
	                edVolta.setNr_Placa("");
				}
//              Motorista
				sqlBusca = "SELECT oid_Pessoa, nr_CNPJ_CPF, nm_Razao_Social from pessoas " +
						  " where oid_Pessoa = '" + res.getString("oid_Motorista") + "'";
				rs = this.executasql.executarConsulta(sqlBusca);
				if(rs.next()){
					edVolta.setOid_Motorista(rs.getString(1));
	                edVolta.setNr_Cnpj_Cpf_Motorista(rs.getString(2));
	                edVolta.setNm_Motorista(rs.getString(3));
				} else {
					edVolta.setOid_Motorista("");
	                edVolta.setNr_Cnpj_Cpf_Motorista("");
	                edVolta.setNm_Motorista("");
				}

			   	if(!JavaUtil.doValida(res.getString("dm_Situacao")))
		     		edVolta.setDm_Situacao("L");

                list.add(edVolta);
            }

        } catch (Exception exc) {
        	exc.printStackTrace();
        	throw new Excecoes("Erro ao listar Programa��o Carga",exc,this.getClass().getName(),"lista");
        }

        return list;
    }

    public ArrayList planilha(Programacao_CargaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select programacoes_cargas.* " +
            	  " from programacoes_cargas, pedidos_cargas " +
            	  " where programacoes_cargas.oid_pedido_carga = pedidos_cargas.oid_pedido_carga ";

            if(doValida(String.valueOf(ed.getOid_Programacao_Carga())))
            	sql += " and programacoes_cargas.oid_programacao_carga = " + ed.getOid_Programacao_Carga();
            if(doValida(String.valueOf(ed.getOid_Pedido_Carga())))
            	sql += " and programacoes_cargas.oid_pedido_carga = " + ed.getOid_Pedido_Carga();
            if(doValida(String.valueOf(ed.getOid_Motorista())))
            	sql += " and programacoes_cargas.oid_motorista = '" + ed.getOid_Motorista() + "'";
            if(doValida(String.valueOf(ed.getOid_Veiculo())))
            	sql += " and programacoes_cargas.oid_veiculo = '" + ed.getOid_Veiculo() + "'";
            if(doValida(String.valueOf(ed.getDm_Situacao())) && !"T".equals(ed.getDm_Situacao()) && !"X".equals(ed.getDm_Situacao()))
            	sql += " and programacoes_cargas.dm_situacao = '" + ed.getDm_Situacao() + "'";
            if(doValida(String.valueOf(ed.getDm_Situacao())) && "X".equals(ed.getDm_Situacao()))
            	sql += " and programacoes_cargas.dm_situacao != 'C'";
            if(doValida(String.valueOf(ed.getDm_Veiculo())) && !"T".equals(ed.getDm_Veiculo()))
            	sql += " and programacoes_cargas.dm_veiculo = '" + ed.getDm_Veiculo() + "'";
            if(doValida(String.valueOf(ed.getDt_Programacao())))
            	sql += " and programacoes_cargas.dt_Programacao >= '" + ed.getDt_Programacao() + "'";
            if(doValida(String.valueOf(ed.getDt_Programacao_Final())))
            	sql += " and programacoes_cargas.dt_Programacao <= '" + ed.getDt_Programacao_Final() + "'";
            if(doValida(String.valueOf(ed.getDt_Carga_Efetiva())))
            	sql += " and programacoes_cargas.dt_Carga_Efetiva >= '" + ed.getDt_Carga_Efetiva() + "'";
            if(doValida(String.valueOf(ed.getDt_Carga_Efetiva_Final())))
            	sql += " and programacoes_cargas.dt_Carga_Efetiva <= '" + ed.getDt_Carga_Efetiva_Final() + "'";
            if(doValida(String.valueOf(ed.getOid_Unidade())))
            	sql += " and programacoes_cargas.oid_unidade = " + ed.getOid_Unidade();

            sql += " order by programacoes_cargas.dt_Saida, pedidos_cargas.oid_pessoa_origem, pedidos_cargas.oid_cliente, programacoes_cargas.oid_pedido_carga, programacoes_cargas.oid_programacao_carga, programacoes_cargas.dm_situacao ";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	Programacao_CargaED edVolta = new Programacao_CargaED();

                edVolta.setOid_Programacao_Carga(res.getLong("oid_Programacao_Carga"));
                edVolta.setOid_Pedido_Carga(res.getLong("oid_Pedido_Carga"));
                //edVolta.setNr_Volumes(res.getDouble("nr_Volumes"));
                edVolta.setNr_Volumes_TX(FormataValor.formataValorBT(res.getDouble("nr_Volumes"), 2));
                edVolta.setDm_Veiculo(res.getString("dm_Veiculo"));
                edVolta.setDt_Programacao(FormataData.formataDataBT(res.getString("dt_programacao")));
                edVolta.setDt_Carga_Efetiva(FormataData.formataDataBT(res.getString("dt_Carga_Efetiva")));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
                edVolta.setDm_Motivo_Cancelamento(res.getString("dm_Motivo_Cancelamento"));
                edVolta.setOid_Carreta(JavaUtil.doValida(res.getString("Oid_Carreta")) ? res.getString("Oid_Carreta") : "");
                edVolta.setOid_Carreta2(JavaUtil.doValida(res.getString("Oid_Carreta2")) ? res.getString("Oid_Carreta2") : "");

                edVolta.setVl_Frete(res.getDouble("vl_Frete"));
                edVolta.setDm_Carga(res.getString("dm_Carga"));
                edVolta.setDt_Saida(FormataData.formataDataBT(res.getString("dt_Saida")));
                edVolta.setDt_Entrega(FormataData.formataDataBT(res.getString("dt_Entrega")));
                edVolta.setHr_Entrega(res.getString("hr_Entrega"));
                edVolta.setTx_Observacao(res.getString("tx_Observacao"));

                edVolta.setVl_Tarifa(res.getDouble("vl_Tarifa"));
                edVolta.setVl_Seguro(res.getDouble("vl_Seguro"));
                edVolta.setDm_ICMS(res.getString("dm_ICMS"));
                edVolta.setDm_Descarga(res.getString("dm_descarga"));

//              edVolta.setOid_Unidade(res.getLong("oid_Unidade"));
                UnidadeBean unidade = UnidadeBean.getByOID_Unidade(res.getInt("oid_Unidade"));
                edVolta.setOid_Unidade(unidade.getOID_Unidade());
                edVolta.setCd_Unidade(unidade.getCD_Unidade());
                edVolta.setNm_Unidade(unidade.getNM_Fantasia());

//              Veiculo
                String sqlBusca = "SELECT oid_Veiculo, nr_Placa from Veiculos " +
								  " where oid_Veiculo = '" + res.getString("oid_Veiculo") + "'";
				ResultSet rs = this.executasql.executarConsulta(sqlBusca);
				if(rs.next()){
	                edVolta.setOid_Veiculo(rs.getString(1));
	                edVolta.setNr_Placa(rs.getString(1));
				} else {
					edVolta.setOid_Veiculo("");
	                edVolta.setNr_Placa("");
				}
//              Motorista
				sqlBusca = "SELECT oid_Pessoa, nr_CNPJ_CPF, nm_Razao_Social from pessoas " +
						  " where oid_Pessoa = '" + res.getString("oid_Motorista") + "'";
				rs = this.executasql.executarConsulta(sqlBusca);
				if(rs.next()){
					edVolta.setOid_Motorista(rs.getString(1));
	                edVolta.setNr_Cnpj_Cpf_Motorista(rs.getString(2));
	                edVolta.setNm_Motorista(rs.getString(3));
				} else {
					edVolta.setOid_Motorista("");
	                edVolta.setNr_Cnpj_Cpf_Motorista("");
	                edVolta.setNm_Motorista("");
				}


			   	//ORDEM FRETE
			   	sqlBusca = "SELECT ordens_fretes.oid_ordem_frete, ordens_fretes.nr_ordem_frete, ordens_fretes.vl_ordem_frete " +
			   			   " from ordens_fretes, Ordens_Manifestos, manifestos, viagens, conhecimentos " +
			   			   " where (ordens_fretes.dm_situacao is null or ordens_fretes.dm_situacao != 'C') " +
			   			   " AND    Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
			   			   " AND    Ordens_Manifestos.oid_Manifesto = Manifestos.oid_Manifesto " +
			   			   " AND    Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
			   			   " AND    Viagens.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
			   			   " and conhecimentos.oid_Programacao_Carga = " + res.getLong("oid_Programacao_Carga");
			   	rs = this.executasql.executarConsulta(sqlBusca);
			   	if(rs.next()){
			   		edVolta.setOid_Ordem_Frete(rs.getString(1));
					edVolta.setNr_Ordem_Frete(rs.getString(2));
					edVolta.setVl_Ordem_Frete(rs.getDouble(3));
			   	} else {
			   		edVolta.setOid_Ordem_Frete("");
					edVolta.setNr_Ordem_Frete("");
					edVolta.setVl_Ordem_Frete(0.0);
			   	}
			   	//FIM ORDEM FRETE

			   	if(!JavaUtil.doValida(res.getString("dm_Situacao")))
		     		edVolta.setDm_Situacao("L");

                list.add(edVolta);
            }

        } catch (Exception exc) {
        	exc.printStackTrace();
        	throw new Excecoes("Erro ao listar Programa��o Carga",exc,this.getClass().getName(),"lista");
        }

        return list;
    }

    public Programacao_CargaED getByRecord(Programacao_CargaED ed) throws Excecoes {

        Programacao_CargaED edVolta = new Programacao_CargaED();

        Iterator iterador = this.lista(ed).iterator();
        while(iterador.hasNext()){
        	edVolta = (Programacao_CargaED)iterador.next();
        }

        return edVolta;
    }

    public void cancela(Programacao_CargaED ed) throws Excecoes {
        String sql = null;
        try {
            sql =  " update programacoes_cargas set ";
            sql += " dm_situacao= 'C'";
            sql += ", dm_Motivo_Cancelamento= '" + ed.getDm_Motivo_Cancelamento() + "'";
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";
            sql += " where oid_programacao_carga = " + ed.getOid_Programacao_Carga();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao cancelar Programa��o Carga",exc,this.getClass().getName(),"cancela");
        }
    }

    public void cancelaPedido(Programacao_CargaED ed) throws Excecoes {
        String sql = null;
        try {
            sql =  " update programacoes_cargas set ";
            sql += " dm_situacao= 'C'";
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";
            sql += " where oid_pedido_carga = " + ed.getOid_Pedido_Carga();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao cancelar Programa��o Carga",exc,this.getClass().getName(),"cancela");
        }
    }

    public void vincula(Programacao_CargaED ed) throws Excecoes {
        String sql = null;
        try {
            sql =  " update programacoes_cargas set ";
            sql += " dm_situacao= 'V'";
            sql += ", dt_Carga_Efetiva= '" + Data.getDataDMY() + "'";
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";
            sql += " where oid_programacao_carga = " + ed.getOid_Programacao_Carga();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao vincular Programa��o Carga",exc,this.getClass().getName(),"vincula");
        }
    }

    public void libera(Programacao_CargaED ed) throws Excecoes {
        String sql = null;
        try {
            sql =  " update programacoes_cargas set ";
            sql += " dm_situacao= 'L'";
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";
            sql += " where oid_programacao_carga = " + ed.getOid_Programacao_Carga();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao liberar Programa��o Carga",exc,this.getClass().getName(),"libera");
        }
    }

    public void setDataCarga(Programacao_CargaED ed) throws Excecoes {
        String sql = null;
        try {
            sql =  " update programacoes_cargas set ";
            sql += " dt_Carga_Efetiva= '" + ed.getDt_Carga_Efetiva() + "'";
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";
            sql += " where oid_programacao_carga = " + ed.getOid_Programacao_Carga();
            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao acertar Data de Carga da Programa��o Carga",exc,this.getClass().getName(),"setDataCarga");
        }
    }

    public void setVolumes(String oid_Conhecimento, double nr_volumes) throws Excecoes {

        String sql = null;
        long oid_programacao = 0;
        String tipo = "";

        try {
        	sql = "SELECT Programacoes_Cargas.oid_Programacao_Carga, Pedidos_Cargas.dm_volumes " +
        		  " from conhecimentos, Programacoes_Cargas, Pedidos_Cargas " +
        		  " where conhecimentos.oid_Programacao_Carga = Programacoes_Cargas.oid_Programacao_Carga " +
        		  " AND Programacoes_Cargas.oid_Pedido_Carga = Pedidos_Cargas.oid_Pedido_Carga " +
        		  " AND conhecimentos.oid_conhecimento = '" + oid_Conhecimento + "'";
        	ResultSet res = this.executasql.executarConsulta(sql);
        	if(res.next()){
        		oid_programacao = res.getLong(1);
        		tipo = res.getString(2);
        	}

        	if(JavaUtil.doValida(String.valueOf(oid_programacao))
        			&& (JavaUtil.doValida(tipo) && "KG".equals(tipo))){
        		sql =  " update programacoes_cargas set ";
                sql += " nr_Volumes= " + nr_volumes;
                sql += " where oid_programacao_carga = " + oid_programacao;
                executasql.executarUpdate(sql);
        	}

        }

        catch (Exception exc) {
        	throw new Excecoes("Erro ao alterar dados de Programa��o Carga",exc,this.getClass().getName(),"altera");
        }
    }

    //*** RELAT�RIOS

    public ArrayList listaToReport(Programacao_CargaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select programacoes_cargas.*, " +
            	  " Pedidos_Cargas.vl_tarifa, Pedidos_Cargas.vl_tarifa_bitrem, Pedidos_Cargas.vl_pedagio, Pedidos_Cargas.vl_pedagio_bitrem, " +
            	  " Pedidos_Cargas.oid_cliente, Pedidos_Cargas.dt_prazo, Pedidos_Cargas.oid_pessoa_destino " +
            	  " from programacoes_cargas, pedidos_cargas " +
            	  " where programacoes_cargas.oid_pedido_carga = pedidos_cargas.oid_pedido_carga ";

            if(doValida(String.valueOf(ed.getOid_Programacao_Carga())))
            	sql += " and programacoes_cargas.oid_programacao_carga = " + ed.getOid_Programacao_Carga();
            if(doValida(String.valueOf(ed.getOid_Pedido_Carga())))
            	sql += " and programacoes_cargas.oid_pedido_carga = " + ed.getOid_Pedido_Carga();
            if(doValida(String.valueOf(ed.getOid_Motorista())))
            	sql += " and programacoes_cargas.oid_motorista = '" + ed.getOid_Motorista() + "'";
            if(doValida(String.valueOf(ed.getOid_Cliente())))
            	sql += " and pedidos_cargas.oid_Cliente = '" + ed.getOid_Cliente() + "'";
            if(doValida(String.valueOf(ed.getOid_Veiculo())))
            	sql += " and programacoes_cargas.oid_veiculo = '" + ed.getOid_Veiculo() + "'";
            if(doValida(String.valueOf(ed.getDm_Situacao())) && !"T".equals(ed.getDm_Situacao()))
            	sql += " and programacoes_cargas.dm_situacao = '" + ed.getDm_Situacao() + "'";
            if(doValida(String.valueOf(ed.getDm_Veiculo())) && !"T".equals(ed.getDm_Veiculo()))
            	sql += " and programacoes_cargas.dm_veiculo = '" + ed.getDm_Veiculo() + "'";
            if(doValida(String.valueOf(ed.getDt_Programacao())))
            	sql += " and programacoes_cargas.dt_Programacao >= '" + ed.getDt_Programacao() + "'";
            if(doValida(String.valueOf(ed.getDt_Programacao_Final())))
            	sql += " and programacoes_cargas.dt_Programacao <= '" + ed.getDt_Programacao_Final() + "'";
            if(doValida(String.valueOf(ed.getDt_Carga_Efetiva())))
            	sql += " and programacoes_cargas.dt_Carga_Efetiva >= '" + ed.getDt_Carga_Efetiva() + "'";
            if(doValida(String.valueOf(ed.getDt_Carga_Efetiva_Final())))
            	sql += " and programacoes_cargas.dt_Carga_Efetiva <= '" + ed.getDt_Carga_Efetiva_Final() + "'";

            sql += " order by programacoes_cargas.dt_Programacao, programacoes_cargas.dt_Carga_Efetiva, programacoes_cargas.oid_pedido_carga, programacoes_cargas.oid_programacao_carga, programacoes_cargas.dm_situacao ";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	Programacao_CargaED edVolta = new Programacao_CargaED();

                edVolta.setOid_Programacao_Carga(res.getLong("oid_Programacao_Carga"));
                edVolta.setOid_Pedido_Carga(res.getLong("oid_Pedido_Carga"));
                edVolta.setNr_Volumes_TX(FormataValor.formataValorBT(res.getDouble("nr_Volumes"), 2));
                edVolta.setDm_Veiculo(res.getString("dm_Veiculo"));
                edVolta.setDt_Programacao(FormataData.formataDataBT(res.getString("dt_programacao")));
                edVolta.setDt_Carga_Efetiva(FormataData.formataDataBT(res.getString("dt_Carga_Efetiva")));
                if(edVolta.getDt_Carga_Efetiva().equals(""))
                	edVolta.setDt_Carga_Efetiva(FormataData.formataDataBT(res.getString("dt_programacao")));
                edVolta.setDt_Prazo(FormataData.formataDataBT(res.getString("dt_prazo")));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
                edVolta.setDm_Motivo_Cancelamento(res.getString("dm_Motivo_Cancelamento"));
                edVolta.setDm_Motivo_Cancelamento(edVolta.getDescMotivo_Cancelamento());

                edVolta.setVl_Frete(res.getDouble("vl_Frete"));
                edVolta.setDm_Carga(res.getString("dm_Carga"));
                edVolta.setDt_Saida(FormataData.formataDataBT(res.getString("dt_Saida")));
                edVolta.setDt_Entrega(FormataData.formataDataBT(res.getString("dt_Entrega")));
                edVolta.setHr_Entrega(res.getString("hr_Entrega"));
                edVolta.setTx_Observacao(res.getString("tx_Observacao"));

                edVolta.setOid_Carreta(JavaUtil.doValida(res.getString("Oid_Carreta")) ? res.getString("Oid_Carreta") : "");
                edVolta.setOid_Carreta2(JavaUtil.doValida(res.getString("Oid_Carreta2")) ? res.getString("Oid_Carreta2") : "");

                edVolta.setVl_Tarifa(res.getDouble("vl_tarifa"));
                edVolta.setVl_Pedagio(res.getDouble("vl_pedagio"));
                if(JavaUtil.doValida(res.getString("dm_Veiculo")) && res.getString("dm_Veiculo").equals("B")){
                	edVolta.setVl_Tarifa(res.getDouble("vl_tarifa_bitrem"));
                    edVolta.setVl_Pedagio(res.getDouble("vl_pedagio_bitrem"));
                }

//              Veiculo
                String sqlBusca = "SELECT oid_Veiculo, nr_Placa, nr_frota from Veiculos " +
								  " where oid_Veiculo = '" + res.getString("oid_Veiculo") + "'";
				ResultSet rs = this.executasql.executarConsulta(sqlBusca);
				if(rs.next()){
	                edVolta.setOid_Veiculo(rs.getString(1));
	                edVolta.setNr_Placa(rs.getString(3));
				} else {
					edVolta.setOid_Veiculo("");
	                edVolta.setNr_Placa("");
				}
//              Cliente
				sqlBusca = "SELECT pessoas.oid_Pessoa, pessoas.nr_CNPJ_CPF, pessoas.nm_Razao_Social, cidades.nm_cidade " +
						   " from pessoas, cidades " +
						  " where pessoas.oid_cidade = cidades.oid_cidade " +
						  " and pessoas.oid_Pessoa = '" + res.getString("oid_cliente") + "'";

				rs = this.executasql.executarConsulta(sqlBusca);
				if(rs.next()){
					edVolta.setOid_Cliente(rs.getString(1));
	                edVolta.setNr_Cnpj_Cpf_Cliente(rs.getString(2));
	                edVolta.setNm_Cliente(rs.getString(3));
				} else {
					edVolta.setOid_Cliente("");
	                edVolta.setNr_Cnpj_Cpf_Cliente("");
	                edVolta.setNm_Cliente("");
				}
//				CTRC
                sqlBusca = "SELECT oid_Conhecimento, nr_Conhecimento, vl_frete_peso, vl_nota_fiscal from conhecimentos " +
                		   " where oid_Programacao_Carga = " + res.getLong("oid_Programacao_Carga");
                rs = this.executasql.executarConsulta(sqlBusca);
                if(rs.next()){
                	edVolta.setOid_Conhecimento(rs.getString(1));
                	edVolta.setNr_Conhecimento(rs.getString(2));
                } else {
                	edVolta.setOid_Conhecimento("");
                	edVolta.setNr_Conhecimento("");
                }

                list.add(edVolta);
            }

        } catch (Exception exc) {
        	exc.printStackTrace();
        	throw new Excecoes("Erro ao listar Programa��o Carga",exc,this.getClass().getName(),"lista");
        }

        return list;
    }

    public long getQtdeProgramacoes(long pedido, String dm_tipo_veiculo) throws Excecoes{
    	long toReturn = 0;
    	String sql = "";
    	try{
    		sql = "select count(oid_programacao_carga) from programacoes_cargas " +
    			  " WHERE programacoes_cargas.dm_veiculo = '" + dm_tipo_veiculo + "' " +
    			  " AND programacoes_cargas.oid_pedido_carga = " + pedido;
    		ResultSet rs = this.executasql.executarConsulta(sql);
    		while(rs.next()){
    			toReturn = rs.getLong(1);
    		}

    	}catch(Exception exc){
    		exc.printStackTrace();
        	throw new Excecoes("Erro ao buscar Programa��o Carga",exc,this.getClass().getName(),"getQtdeProgramacoes()");
    	}

    	return toReturn;
    }

    public Programacao_CargaED getColeta(Programacao_CargaED edVolta) throws Excecoes{
    	String sql = "";
    	try{
    		edVolta.setOid_Coleta(null);
    	} catch(Exception exc){
    		exc.printStackTrace();
        	throw new Excecoes("Erro ao buscar Coleta",exc,this.getClass().getName(),"getColeta()");
    	}
    	return edVolta;
    }

    public Programacao_CargaED getConhecimento(Programacao_CargaED edVolta) throws Excecoes{
    	String sql = "";
    	try{
    		edVolta.setOid_Conhecimento(null);
    		edVolta.setNr_Conhecimento(null);

//    		CTRC
			sql = "SELECT oid_Conhecimento, nr_Conhecimento, vl_total_frete, vl_nota_fiscal, vl_icms from conhecimentos " +
 			      " where dm_situacao != 'C' " +
	   			  " and oid_Programacao_Carga = " + edVolta.getOid_Programacao_Carga();
		   	ResultSet rs = this.executasql.executarConsulta(sql);
		   	if(rs.next()){
		   		edVolta.setOid_Conhecimento(rs.getString(1));
		     	edVolta.setNr_Conhecimento(rs.getString(2));
		   	}
//			FIM CTRC
    	} catch(Exception exc){
    		exc.printStackTrace();
        	throw new Excecoes("Erro ao buscar CTRC",exc,this.getClass().getName(),"getConhecimento()");
    	}
    	return edVolta;
    }

    public Programacao_CargaED getOrdemFrete(Programacao_CargaED edVolta) throws Excecoes{
    	String sql = "";
    	try{
    		edVolta.setOid_Ordem_Frete("");
			edVolta.setNr_Ordem_Frete("");
			edVolta.setVl_Ordem_Frete(0.0);

//    		OF
			sql = "SELECT ordens_fretes.oid_ordem_frete, ordens_fretes.nr_ordem_frete, ordens_fretes.vl_ordem_frete " +
			      " from ordens_fretes, Ordens_Manifestos, manifestos, viagens, conhecimentos " +
			      " where (ordens_fretes.dm_situacao is null or ordens_fretes.dm_situacao != 'C') " +
   			 	  " AND    Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
   			 	  " AND    Ordens_Manifestos.oid_Manifesto = Manifestos.oid_Manifesto " +
   			 	  " AND    Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
   			 	  " AND    Viagens.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
   			 	  " and conhecimentos.oid_Programacao_Carga = "+ edVolta.getOid_Programacao_Carga();
		   	ResultSet rs = this.executasql.executarConsulta(sql);
		   	if(rs.next()){
		   		edVolta.setOid_Ordem_Frete(rs.getString(1));
				edVolta.setNr_Ordem_Frete(rs.getString(2));
				edVolta.setVl_Ordem_Frete(rs.getDouble(3));
		   	}
//			FIM OF
    	} catch(Exception exc){
    		exc.printStackTrace();
        	throw new Excecoes("Erro ao buscar OF",exc,this.getClass().getName(),"getOrdemFrete()");
    	}
    	return edVolta;
    }

}