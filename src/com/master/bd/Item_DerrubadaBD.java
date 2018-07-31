package com.master.bd;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.DerrubadaED;
import com.master.ed.Item_DerrubadaED;
import com.master.ed.Movimento_Produto_ClienteED;
import com.master.ed.Parametro_WmsED;
import com.master.ed.Produto_ClienteED;
import com.master.ed.WMS_EstoqueED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Item_DerrubadaBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Item_DerrubadaBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Item_DerrubadaED inclui(Item_DerrubadaED ed) throws Excecoes {

        String sql = null;
        Item_DerrubadaED edVolta = new Item_DerrubadaED();

        try {

            //*** Pega o valor máximo
            if(ed.getOid_item_derrubada()>0 && !doExiste("itens_derrubadas", "oid_Item_Derrubada="+ed.getOid_item_derrubada()))
            	edVolta.setOid_item_derrubada(ed.getOid_item_derrubada());
            else {
            	edVolta.setOid_item_derrubada(getAutoIncremento("oid_Item_Derrubada", "itens_derrubadas"));
            	ed.setOid_item_derrubada(edVolta.getOid_item_derrubada());
            }
            edVolta.setOid_derrubada(ed.getOid_derrubada());

            sql = "insert into itens_derrubadas (" +
                  "oid_Item_Derrubada, oid_derrubada, " +
                  "oid_produto, dt_lote, " +
                  "nr_quantidade, nr_quantidade_contada, " +
                  "usuario_stamp, dt_stamp, " +
                  "dm_stamp) values (" +
                  edVolta.getOid_item_derrubada() + ", " + ed.getOid_derrubada() + ", " +
                  ed.getOid_produto() + ",'" +
                  ed.getDt_lote() + "'," +
                  ed.getNr_quantidade() + "," +
                  ed.getNr_quantidade_contada() + ",'" +
                  ed.getUsuario_Stamp() + "','" + Data.getDataDMY() + "','N')";

            executasql.executarUpdate(sql);

            this.Entrada_Estoque(ed, 4);

        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao incluir Item Derrubada",exc,this.getClass().getName(),"inclui");
        }
        return edVolta;
    }

    public void altera(Item_DerrubadaED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " update itens_derrubadas set ";
            sql += " oid_produto= " + ed.getOid_produto();
            sql += ", dt_lote= " + getSQLDate(ed.getDt_lote());
            sql += ", nr_quantidade= " + ed.getNr_quantidade();
            sql += ", nr_quantidade_contada= " + ed.getNr_quantidade_contada();
            sql += ", usuario_stamp= " + getSQLString(ed.getUsuario_Stamp());
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";
            sql += " where oid_item_derrubada = " + ed.getOid_item_derrubada();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
        	throw new Excecoes("Erro ao alterar dados de Item Derrubada",exc,this.getClass().getName(),"altera");
        }
    }

    public void deleta(Item_DerrubadaED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " delete from itens_derrubadas " +
            	   " where oid_Item_Derrubada = " + ed.getOid_item_derrubada();

            executasql.executarUpdate(sql);

            this.Saida_Estoque(ed);
        }

        catch (Exception exc) {
        	throw new Excecoes("Erro ao excluir Item Derrubada",exc,this.getClass().getName(),"deleta");
        }
    }

    public ArrayList lista(Item_DerrubadaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select itens_derrubadas.*, produtos.cd_produto, produtos.nm_produto " +
            	  " from itens_derrubadas, produtos " +
            	  " where Produtos.oid_produto = itens_derrubadas.oid_produto ";

            if(doValida(String.valueOf(ed.getOid_derrubada())))
            	sql += " and itens_derrubadas.oid_derrubada = " + ed.getOid_derrubada();
            if(doValida(String.valueOf(ed.getOid_item_derrubada())))
            	sql += " and itens_derrubadas.oid_item_derrubada = " + ed.getOid_item_derrubada();

            sql += " order by itens_derrubadas.oid_derrubada, produtos.cd_produto, itens_derrubadas.dt_lote ";

// System.out.println("IT DERR.: "+ sql);

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	Item_DerrubadaED edVolta = new Item_DerrubadaED();

                edVolta.setOid_item_derrubada(res.getLong("oid_Item_Derrubada"));
                edVolta.setOid_derrubada(res.getLong("oid_Derrubada"));
                edVolta.setDt_lote(FormataData.formataDataBT(res.getString("dt_lote")));
                edVolta.setNr_quantidade_TX(FormataValor.formataValorBT(res.getDouble("nr_quantidade"), 3));
                edVolta.setNr_quantidade_contada_TX(FormataValor.formataValorBT(res.getDouble("nr_quantidade_contada"), 3));
                edVolta.setOid_produto(res.getInt("oid_produto"));
                edVolta.setCd_produto(res.getString("cd_produto"));
                edVolta.setNm_produto(res.getString("nm_produto"));

                list.add(edVolta);
            }

        } catch (Exception exc) {
        	throw new Excecoes("Erro ao listar Item Derrubada",exc,this.getClass().getName(),"lista");
        }

        return list;
    }

    public Item_DerrubadaED getByRecord(Item_DerrubadaED ed) throws Excecoes {

        Item_DerrubadaED edVolta = new Item_DerrubadaED();

        Iterator iterador = this.lista(ed).iterator();
        while(iterador.hasNext())
        	edVolta = (Item_DerrubadaED)iterador.next();

        return edVolta;
    }

    public void Entrada_Estoque(Item_DerrubadaED ed, int tipo_estoque) throws Excecoes {

        String sql = null;
        Parametro_WmsED pWMS = new Parametro_WmsED();
        Produto_ClienteED pcED = new Produto_ClienteED();
        DerrubadaED derrubada = new DerrubadaED();
        WMS_EstoqueED wms_estoque = new WMS_EstoqueED();
        int tipo_movimento = 12;

        try {
        	double nr_qtde_entrada = ed.getNr_quantidade();
//        	if(ed.getNr_quantidade_contada()>0 && ed.getNr_quantidade_contada()!=ed.getNr_quantidade()){
//        		nr_qtde_entrada = ed.getNr_quantidade_contada();
//        	}
        	if(tipo_estoque==4){
        		tipo_movimento=13;

        	}
        	//parametros do WMS
            pWMS = new Parametro_WmsBD(executasql).getByRecord(pWMS);

            //derrubada
            derrubada.setOid_derrubada(ed.getOid_derrubada());
            derrubada = new DerrubadaBD(executasql).getByRecord(derrubada);

            //pega o produto
            sql = "select * from produtos " +
                  " WHERE oid_produto = " + ed.getOid_produto();
            ResultSet res = executasql.executarConsulta(sql);
            while(res.next()){
            	pcED.setOID_Produto(res.getInt("oid_produto"));
                pcED.setOID_Produto_Cliente(res.getInt("oid_produto") + derrubada.getNr_cnpj_cpf());
                pcED = new Produto_ClienteBD(executasql).getByRecord(pcED);
                //se não tem cria?
                if(!JavaUtil.doValida(pcED.getOID_Produto_Cliente())){
//                	------ aqui então.
                } else {
                	//estoque cliente
                    wms_estoque.setOID_Tipo_Estoque(tipo_estoque); //disponivel é 1, para transito usar 4
                    wms_estoque.setOID_Localizacao(pcED.getOid_Localizacao());
                    wms_estoque.setOID_Produto_Cliente(pcED.getOID_Produto_Cliente());
                    wms_estoque = new WMS_EstoqueBD(executasql).getByRecord(new WMS_EstoqueED(wms_estoque.getOID_Produto_Cliente() + wms_estoque.getOID_Localizacao() + wms_estoque.getOID_Tipo_Estoque()));
                    //se não tem, vai criar
                	wms_estoque.setOID_Tipo_Estoque(tipo_estoque);
                    wms_estoque.setOID_Localizacao(pcED.getOid_Localizacao());
                    wms_estoque.setOID_Produto_Cliente(pcED.getOID_Produto_Cliente());
              	  	wms_estoque.setNR_Quantidade(nr_qtde_entrada);
              	    wms_estoque = new WMS_EstoqueBD(executasql).inclui(wms_estoque,false);
              	    //inclui movimento
//              	    new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(tipo_movimento
//    																								   ,wms_estoque.getOID_Produto_Cliente()
//    																								   ,wms_estoque.getOID_Tipo_Estoque()
//    																								   ,wms_estoque.getOID_Localizacao(),""
//    																								   ,0,0
//    																								   ,nr_qtde_entrada,nr_qtde_entrada,nr_qtde_entrada
//    																								   ,Data.getDataDMY(), Data.getHoraHM(), "Entrada da DERRUBADA nr. " + ed.getOid_derrubada()));

                }

            }
            //se não tem o produto, não faz nada... hehehe
        }
        catch(Excecoes e){
        	e.printStackTrace();
        	throw e;
        }
        catch (Exception exc) {
        	exc.printStackTrace();
        	throw new Excecoes("Erro ao dar entrada no estoque para o Item Derrubada: " + ed.getOid_item_derrubada(),exc,this.getClass().getName(),"altera");
        }
    }

    public void Saida_Estoque(Item_DerrubadaED ed) throws Excecoes {

        String sql = null;
        Parametro_WmsED pWMS = new Parametro_WmsED();
        Produto_ClienteED pcED = new Produto_ClienteED();
        DerrubadaED derrubada = new DerrubadaED();
        WMS_EstoqueED wms_estoque = new WMS_EstoqueED();
        int tipo_movimento = 14;
        int tipo_estoque = 4;

        try {
        	double nr_qtde_entrada = ed.getNr_quantidade();

        	//parametros do WMS
            pWMS = new Parametro_WmsBD(executasql).getByRecord(pWMS);

            //derrubada
            derrubada.setOid_derrubada(ed.getOid_derrubada());
            derrubada = new DerrubadaBD(executasql).getByRecord(derrubada);

            //pega o produto
            sql = "select * from produtos " +
                  " WHERE oid_produto = " + ed.getOid_produto();
            ResultSet res = executasql.executarConsulta(sql);
            while(res.next()){
            	pcED.setOID_Produto(res.getInt("oid_produto"));
                pcED.setOID_Produto_Cliente(res.getInt("oid_produto") + derrubada.getNr_cnpj_cpf());
                pcED = new Produto_ClienteBD(executasql).getByRecord(pcED);
                //se não tem cria?
                if(!JavaUtil.doValida(pcED.getOID_Produto_Cliente())){
//                	nada
                } else {
                	//estoque cliente
                    wms_estoque.setOID_Tipo_Estoque(tipo_estoque);
                    wms_estoque.setOID_Localizacao(pcED.getOid_Localizacao());
                    wms_estoque.setOID_Produto_Cliente(pcED.getOID_Produto_Cliente());
                    wms_estoque = new WMS_EstoqueBD(executasql).getByRecord(new WMS_EstoqueED(wms_estoque.getOID_Produto_Cliente() + wms_estoque.getOID_Localizacao() + wms_estoque.getOID_Tipo_Estoque()));
                    //subtrai
              	    new WMS_EstoqueBD(executasql).subtrai(wms_estoque.getOID_Estoque(),nr_qtde_entrada,false);
              	    //inclui movimento
//              	    new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(tipo_movimento
//    																								   ,wms_estoque.getOID_Produto_Cliente()
//    																								   ,wms_estoque.getOID_Tipo_Estoque()
//    																								   ,wms_estoque.getOID_Localizacao(),""
//    																								   ,0,0
//    																								   ,nr_qtde_entrada,nr_qtde_entrada,nr_qtde_entrada
//    																								   ,Data.getDataDMY(), Data.getHoraHM(), "Confirmação da DERRUBADA nr. " + ed.getOid_derrubada()));
                }
            }
        }
        catch(Excecoes e){
        	throw e;
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao subtrair do estoque para o Item Derrubada: " + ed.getOid_item_derrubada(),exc,this.getClass().getName(),"altera");
        }
    }

    public void Saida_Estoque(Item_DerrubadaED ed, int tipo_estoque) throws Excecoes {

        String sql = null;
        Parametro_WmsED pWMS = new Parametro_WmsED();
        Produto_ClienteED pcED = new Produto_ClienteED();
        DerrubadaED derrubada = new DerrubadaED();
        WMS_EstoqueED wms_estoque = new WMS_EstoqueED();
        int tipo_movimento = 14;

        try {
        	double nr_qtde_entrada = ed.getNr_quantidade();

        	//parametros do WMS
            pWMS = new Parametro_WmsBD(executasql).getByRecord(pWMS);

            //derrubada
            derrubada.setOid_derrubada(ed.getOid_derrubada());
            derrubada = new DerrubadaBD(executasql).getByRecord(derrubada);

            //pega o produto
            sql = "select * from produtos " +
                  " WHERE oid_produto = " + ed.getOid_produto();
            ResultSet res = executasql.executarConsulta(sql);
            while(res.next()){
            	pcED.setOID_Produto(res.getInt("oid_produto"));
                pcED.setOID_Produto_Cliente(res.getInt("oid_produto") + derrubada.getNr_cnpj_cpf());
                pcED = new Produto_ClienteBD(executasql).getByRecord(pcED);
                //se não tem cria?
                if(!JavaUtil.doValida(pcED.getOID_Produto_Cliente())){
//                	nada
                } else {
                	//estoque cliente
                    wms_estoque.setOID_Tipo_Estoque(tipo_estoque);
                    wms_estoque.setOID_Localizacao(pcED.getOid_Localizacao());
                    wms_estoque.setOID_Produto_Cliente(pcED.getOID_Produto_Cliente());
                    wms_estoque = new WMS_EstoqueBD(executasql).getByRecord(new WMS_EstoqueED(wms_estoque.getOID_Produto_Cliente() + wms_estoque.getOID_Localizacao() + wms_estoque.getOID_Tipo_Estoque()));
                    //subtrai
              	    new WMS_EstoqueBD(executasql).subtrai(wms_estoque.getOID_Estoque(),nr_qtde_entrada,false);
              	    //inclui movimento
//              	    new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(tipo_movimento
//    																								   ,wms_estoque.getOID_Produto_Cliente()
//    																								   ,wms_estoque.getOID_Tipo_Estoque()
//    																								   ,wms_estoque.getOID_Localizacao(),""
//    																								   ,0,0
//    																								   ,nr_qtde_entrada,nr_qtde_entrada,nr_qtde_entrada
//    																								   ,Data.getDataDMY(), Data.getHoraHM(), "Confirmação da DERRUBADA nr. " + ed.getOid_derrubada()));
                }
            }
        }
        catch(Excecoes e){
        	throw e;
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao subtrair do estoque para o Item Derrubada: " + ed.getOid_item_derrubada(),exc,this.getClass().getName(),"altera");
        }
    }

}