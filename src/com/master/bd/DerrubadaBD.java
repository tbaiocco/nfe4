package com.master.bd;

import java.io.File;
import java.io.LineNumberReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.master.ed.DerrubadaED;
import com.master.ed.EDI_ImportacaoED;
import com.master.ed.Item_DerrubadaED;
import com.master.rn.Item_DerrubadaRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.SeparaEndereco;
import com.master.util.Valores;
import com.master.util.bd.ExecutaSQL;

public class DerrubadaBD extends BancoUtil{

    private ExecutaSQL executasql;

    public DerrubadaBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public DerrubadaED inclui(DerrubadaED ed) throws Excecoes {

        String sql = null;
        DerrubadaED edVolta = new DerrubadaED();

        try {

            //*** Pega o valor máximo
        	if(ed.getOid_derrubada()>0 && !doExiste("derrubadas", "oid_Derrubada="+ed.getOid_derrubada()))
        		edVolta.setOid_derrubada(ed.getOid_derrubada());
        	else
        		edVolta.setOid_derrubada(getAutoIncremento("oid_Derrubada", "derrubadas"));

            sql = "insert into derrubadas (" +
                  "oid_Derrubada, oid_unidade, " +
                  "oid_Veiculo, " +
                  "dt_Derrubada, hr_derrubada, " +
                  "dm_situacao, " +
                  "usuario_stamp, dt_stamp, " +
                  "dm_stamp) values (" +
                  edVolta.getOid_derrubada() + ", " + ed.getOid_unidade() + ", '" +
                  ed.getOid_veiculo() + "','" +
                  JavaUtil.getValueDef(ed.getDt_derrubada(), Data.getDataDMY()) + "','" +
                  JavaUtil.getValueDef(ed.getHr_derrubada(), Data.getHoraHM()) + "','A','" +
                  ed.getUsuario_Stamp() + "','" + Data.getDataDMY() + "','N')";

            executasql.executarUpdate(sql);

        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao incluir Derrubada",exc,this.getClass().getName(),"inclui");
        }
        return edVolta;
    }

    public void altera(DerrubadaED ed) throws Excecoes {

        String sql = null;

        try {

            sql =  " update derrubadas set ";
            sql += " oid_veiculo= " + getSQLString(ed.getOid_veiculo());
            sql += ", oid_unidade= " + ed.getOid_unidade();
            sql += ", dm_situacao= " + getSQLString(ed.getDm_situacao());
            sql += ", dt_Derrubada= " + getSQLDate(ed.getDt_derrubada());
            sql += ", dt_finalizado= " + getSQLDate(ed.getDt_finalizado());
            sql += ", hr_derrubada= " + getSQLString(ed.getHr_derrubada());
            sql += ", usuario_stamp= " + getSQLString(ed.getUsuario_Stamp());
            sql += ", dt_stamp= '" + Data.getDataDMY() + "'";
            sql += " where oid_derrubada = " + ed.getOid_derrubada();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
        	throw new Excecoes("Erro ao alterar dados de Derrubada",exc,this.getClass().getName(),"altera");
        }
    }

    public void deleta(DerrubadaED ed) throws Excecoes {

        String sql = null;
        Item_DerrubadaED item = new Item_DerrubadaED();

        try {
        	Item_DerrubadaBD itBD = new Item_DerrubadaBD(executasql);
        	item.setOid_derrubada(ed.getOid_derrubada());
        	Iterator iterador = itBD.lista(item).iterator();
        	while(iterador.hasNext()){
        		item = (Item_DerrubadaED)iterador.next();
        		itBD.deleta(item);
        	}

            sql =  " delete from derrubadas " +
            	   " where oid_Derrubada = " + ed.getOid_derrubada();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
        	throw new Excecoes("Erro ao excluir Derrubada",exc,this.getClass().getName(),"deleta");
        }
    }

    public ArrayList lista(DerrubadaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " select derrubadas.*, unidades.cd_unidade, pessoas.oid_pessoa, pessoas.nm_fantasia, pessoas.nm_razao_social, pessoas.nr_cnpj_cpf " +
            	  " from derrubadas, unidades, pessoas " +
            	  " where derrubadas.oid_unidade = unidades.oid_unidade " +
            	  " and unidades.oid_pessoa = pessoas.oid_pessoa ";

            if(doValida(String.valueOf(ed.getOid_derrubada())))
            	sql += " and derrubadas.oid_derrubada = " + ed.getOid_derrubada();
            if(doValida(String.valueOf(ed.getOid_unidade())))
            	sql += " and derrubadas.oid_unidade = " + ed.getOid_unidade();
            if(doValida(String.valueOf(ed.getOid_veiculo())))
            	sql += " and derrubadas.oid_veiculo = '" + ed.getOid_veiculo() + "'";

            if(doValida(String.valueOf(ed.getDt_derrubada())))
            	sql += " and derrubadas.dt_derrubada >= '" + ed.getDt_derrubada() + "'";
            if(doValida(String.valueOf(ed.getDt_derrubada_final())))
            	sql += " and derrubadas.dt_derrubada <= '" + ed.getDt_derrubada_final() + "'";
            if(doValida(String.valueOf(ed.getDt_finalizado())))
            	sql += " and derrubadas.dt_finalizado >= '" + ed.getDt_finalizado() + "'";
            if(doValida(String.valueOf(ed.getDt_finalizado_final())))
            	sql += " and derrubadas.dt_finalizado <= '" + ed.getDt_finalizado_final() + "'";
            if(doValida(String.valueOf(ed.getDm_situacao())) && !"T".equals(ed.getDm_situacao()))
            	sql += " and derrubadas.dm_situacao = '" + ed.getDm_situacao() + "'";

// System.out.println("DERRUBADA: "+ sql);

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	DerrubadaED edVolta = new DerrubadaED();

                edVolta.setOid_derrubada(res.getLong("oid_Derrubada"));
                edVolta.setDm_situacao(res.getString("dm_situacao"));
                edVolta.setDt_derrubada(FormataData.formataDataBT(res.getString("dt_derrubada")));
                edVolta.setHr_derrubada(res.getString("hr_derrubada"));
                edVolta.setDt_finalizado(FormataData.formataDataBT(res.getString("dt_finalizado")));
                edVolta.setOid_unidade(res.getString("oid_unidade"));
                edVolta.setCd_unidade(res.getString("cd_unidade"));
                edVolta.setOid_pessoa(res.getString("oid_pessoa"));
                edVolta.setNr_cnpj_cpf(res.getString("nr_cnpj_cpf"));
                edVolta.setNm_razao_social(res.getString("nm_razao_social"));
                edVolta.setNm_fantasia(res.getString("nm_fantasia"));

                edVolta.setOid_veiculo(res.getString("oid_veiculo"));

				String sqlBusca = "SELECT nr_placa from veiculos " +
								  " where oid_veiculo = '" + res.getString("oid_veiculo") + "'";
				ResultSet rs = this.executasql.executarConsulta(sqlBusca);
				if(rs.next()){
					edVolta.setNr_placa(rs.getString("nr_placa"));
				}

                list.add(edVolta);
            }

        } catch (Exception exc) {
        	throw new Excecoes("Erro ao listar Derrubada",exc,this.getClass().getName(),"lista");
        }

        return list;
    }

    public DerrubadaED getByRecord(DerrubadaED ed) throws Excecoes {

        DerrubadaED edVolta = new DerrubadaED();

        Iterator iterador = this.lista(ed).iterator();
        while(iterador.hasNext())
        	edVolta = (DerrubadaED)iterador.next();

        return edVolta;
    }

    public void Confirma_Derrubada(DerrubadaED ed) throws Excecoes {

        String sql = null;
        Item_DerrubadaED item = new Item_DerrubadaED();

        try {
        	Item_DerrubadaBD itBD = new Item_DerrubadaBD(executasql);
        	item.setOid_derrubada(ed.getOid_derrubada());
        	Iterator iterador = itBD.lista(item).iterator();
        	while(iterador.hasNext()){
        		item = (Item_DerrubadaED)iterador.next();
        		itBD.Saida_Estoque(item);
        		if(item.getNr_quantidade_contada()>0 && item.getNr_quantidade_contada()!=item.getNr_quantidade()){
        			item.setNr_quantidade(item.getNr_quantidade_contada());
            	}
        		itBD.Entrada_Estoque(item, 1);
        	}

        	sql = "UPDATE derrubadas set dm_situacao = 'F', dt_finalizado = '" + Data.getDataDMY() + "' where oid_derrubada = " + ed.getOid_derrubada();
        	executasql.executarUpdate(sql);
        }
        catch(Excecoes e){
        	throw e;
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao Confirmar Derrubada",exc,this.getClass().getName(),"deleta");
        }
    }

    public void Cancela(DerrubadaED ed) throws Excecoes {

        String sql = null;
        Item_DerrubadaED item = new Item_DerrubadaED();

        try {
        	Item_DerrubadaBD itBD = new Item_DerrubadaBD(executasql);
        	item.setOid_derrubada(ed.getOid_derrubada());
        	Iterator iterador = itBD.lista(item).iterator();
        	while(iterador.hasNext()){
        		item = (Item_DerrubadaED)iterador.next();
//        		if(item.getNr_quantidade_contada()>0 && item.getNr_quantidade_contada()!=item.getNr_quantidade()){
//        			item.setNr_quantidade(item.getNr_quantidade_contada());
//            	}
        		itBD.Saida_Estoque(item,1);
        	}

        	sql = "UPDATE derrubadas set dm_situacao = 'C' where oid_derrubada = " + ed.getOid_derrubada();
        	executasql.executarUpdate(sql);
        }
        catch(Excecoes e){
        	throw e;
        }
        catch (Exception exc) {
        	throw new Excecoes("Erro ao cancelar Derrubada",exc,this.getClass().getName(),"deleta");
        }
    }

//    public void importaDaudt(DerrubadaED ed, String arquivo) throws Excecoes {
//	    String NM_Registro = "";
//	    int linha = 0;
//	    String tipo = "";
//	    String sql = "";
//
//	    try {
//	    	// System.out.println("Chegou com...");
//	    	// System.out.println("Unidade..." + ed.getOid_unidade());
//	    	// System.out.println("Arquivo..." + arquivo);
//	    	if(JavaUtil.doValida(arquivo) && new File(arquivo).canRead()){
//	    		ManipulaArquivo man = new ManipulaArquivo ("");
//	  	      	LineNumberReader line = man.leLinha (arquivo);
//	  	      	if (line.ready ()) {
//	  	      		while ( (NM_Registro = line.readLine ()) != null) {
//	  	      			DerrubadaED importaED = new DerrubadaED();
//	  	      			Item_DerrubadaED item_importaED = new Item_DerrubadaED();
//	  	      			StringTokenizer stk = new StringTokenizer (NM_Registro , ";");
//	  	      			if (stk.hasMoreTokens ()) {
//	  	      				tipo = stk.nextToken();
//	  	      			}
//	  	      			if(tipo.equals("000")){
//	  	      				importaED = new DerrubadaED();
//	  	      				importaED.setOid_unidade(ed.getOid_unidade());
//	  	      				if (stk.hasMoreTokens ()) {
//	  	      					String nr_derrubada = stk.nextToken();
//	  	      					if(JavaUtil.doValida(nr_derrubada))
//	  	      						importaED.setOid_derrubada(new Long(nr_derrubada).longValue());
//	  	      					else throw new Excecoes("Formato de arquivo incorreto!");
//	  	      				}
//		  	      			if (stk.hasMoreTokens ()) {
//		  	      				importaED.setOid_veiculo(stk.nextToken());
//		  	      			}
//		  	      			if (stk.hasMoreTokens ()) {
//		  	      				importaED.setDt_derrubada(stk.nextToken());
//		  	      			}
//		  	      			if (stk.hasMoreTokens ()) {
//		  	      				importaED.setHr_derrubada(stk.nextToken());
//		  	      			}
//		  	      			this.inclui(importaED);
//		  	      			//this.executasql.executarUpdate("commit");
//
//	  	      			} else if(tipo.equals("001")){
//	  	      				item_importaED = new Item_DerrubadaED();
//		  	      			if (stk.hasMoreTokens ()) {
//	  	      					String nr_derrubada = stk.nextToken();
//	  	      					if(JavaUtil.doValida(nr_derrubada))
//	  	      						item_importaED.setOid_derrubada(new Long(nr_derrubada).longValue());
//	  	      					else throw new Excecoes("Formato de arquivo incorreto!");
//	  	      				}
//		  	      			if (stk.hasMoreTokens ()) {
//		  	      				String cd_produto = stk.nextToken();
//			  	      			sql = "select oid_produto from produtos " +
//			  	      				  " WHERE cd_produto = '" + cd_produto + "'";
//			  	      			ResultSet res = executasql.executarConsulta(sql);
//			  	      			while(res.next()){
//			  	      				item_importaED.setOid_produto(res.getInt("oid_produto"));
//			  	      			}
//			  	      			if(!JavaUtil.doValida(String.valueOf(item_importaED.getOid_produto())))
//			  	      				throw new Excecoes("Produto cd.:" + cd_produto + " não cadastrado... Impossível importar!");
//		  	      			}
//			  	      		if (stk.hasMoreTokens ()) {
//		  	      				item_importaED.setDt_lote(stk.nextToken());
//		  	      			}
//		  	      			if (stk.hasMoreTokens ()) {
//			  	      			String nr_qtde = stk.nextToken();
//	  	      					if(JavaUtil.doValida(nr_qtde))
//	  	      						item_importaED.setNr_quantidade(Valores.converteStringToDouble(nr_qtde));
//	  	      					else throw new Excecoes("Formato de arquivo incorreto!");
//		  	      			}
//		  	      			new Item_DerrubadaBD(executasql).inclui(item_importaED);
//	  	      			}
//	  	      			linha++;
//	  	      			line.setLineNumber (linha);
//	  	      		}
//	  	      	}
//	  	      	line.close ();
//	    	} else {
//	    		throw new Excecoes("Arquivo inexistente ou não pode ser lido!");
//	    	}
//
//	    	FileUtil.deleteFile(arquivo);
//	    }
//	    catch (Excecoes ex){
//	    	ex.printStackTrace();
//	    	throw ex;
//	    }
//	    catch (Exception exc) {
//	    	exc.printStackTrace();
//	    	throw new Excecoes("Erro ao ler arquivo...",exc,this.getClass().getName(),"importaDaudt");
//	    }
//	  }

}