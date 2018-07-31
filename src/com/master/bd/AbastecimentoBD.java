/*
 * Created on 02/04/2008
 *
 */
package com.master.bd;
/** 
 * @author: Regis Steigleder
 */


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.ed.AbastecimentoED;
import com.master.ed.Movimento_Ordem_ServicoED;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class AbastecimentoBD extends Utilitaria {

	private ExecutaSQL executasql;
    
    String sql = null;
   
    public AbastecimentoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }
       
	public AbastecimentoED inclui(AbastecimentoED ed) throws Excecoes {
		try {
			ed.setOid_Abastecimento(getAutoIncremento("oid_Abastecimento", "Abastecimentos"));
			sql ="INSERT INTO " +
				 "Abastecimentos " +
				 "(" +
				 "oid_Abastecimento" +
				 ",oid_Empresa" +
				 ",oid_Veiculo" +
				 ",dm_Tipo_Abastec" +
				 ",dm_Completo" +
				 ",nr_Odometro" +
				 ",nr_Kilometragem" +
				 ",dt_Abastecimento" +
				 ",dm_Ano_Mes" +
				 ",vl_Abastecimento" +
				 ",nr_Quantidade" +
				 ",nr_Documento" ;
		if (ed.getOid_Bomba()>0){
			sql+=",oid_Bomba" ;
		}	
		if (doValida(ed.getOid_Fornecedor())){
			sql+=",oid_Fornecedor" ;
		}	
		if (doValida(ed.getOid_Motorista()) ){
			sql+=",oid_Motorista";
		}
		if (ed.getNr_Apontador_Ctf()>0){
			sql+=",nr_Apontador_Ctf" ;
		}	
		if (ed.getNr_Numabast_Ctf()>0){
			sql+=",nr_Numabast_Ctf" ;
		}	
		if (doValida(ed.getDt_Debito_Ctf())){
			sql+=",dt_Debito_Ctf" ;
		}	
			sql+=",nr_Kilometragem_Percurso" +
				 ",nr_Kilometragem_Percurso_Media" +
				 ",nr_Quantidade_Media" +
				 ",vl_Abastecimento_Media" +
				 ") " +
				 " VALUES ( " 
				 + ed.getOid_Abastecimento() +
				 "," + ed.getOid_Empresa() +
				 ",'" + ed.getOid_Veiculo() + "'" +
				 ",'" + ed.getDm_Tipo_Abastec() + "'" +
				 ",'" + ed.getDm_Completo() + "'" +
				 "," + ed.getNr_Odometro() +
				 "," + ed.getNr_Kilometragem() + 
				 ",'" + ed.getDt_Abastecimento() + "'" +
				 ",'" + FormataData.stringTostringTB(ed.getDt_Abastecimento()).substring(0, 7) + "'" +
				 "," + ed.getVl_Abastecimento() + 
				 "," + ed.getNr_Quantidade() + 
				 ",'" + ed.getNr_Documento()+ "'" ; 
		if (ed.getOid_Bomba()>0){
			sql+="," + ed.getOid_Bomba() ;
		}	
		if (doValida(ed.getOid_Fornecedor())){
			sql+=",'" + ed.getOid_Fornecedor() + "' ";
		}
		if (doValida(ed.getOid_Motorista()) ){
			sql+=",'" + ed.getOid_Motorista() + "' ";
		}
		if (ed.getNr_Apontador_Ctf()>0){
			sql+="," + ed.getNr_Apontador_Ctf() ;
		}	
		if (ed.getNr_Numabast_Ctf()>0){ 
			sql+="," + ed.getNr_Numabast_Ctf() ;
		}	
		if (doValida(ed.getDt_Debito_Ctf())){
			sql+=",'" + ed.getDt_Debito_Ctf() + "'" ;
		}	
			sql+="," + ed.getNr_Kilometragem_Percurso() +
				 "," + ed.getNr_Kilometragem_Percurso_Media() +
				 "," + ed.getNr_Quantidade_Media() +
				 "," + ed.getVl_Abastecimento_Media() +
				 ")";
			
			
			executasql.executarUpdate(sql);
			
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Movimento_Ordem_ServicoED ed)");
		}
	}

	  public void delete (Movimento_Ordem_ServicoED ed) throws Excecoes {
		    try {
				           sql = " DELETE FROM movimentos_ordens_Servicos "+
				        		 " WHERE " 							     +
				        		 "oid_empresa = "  + ed.getOid_Empresa() +
				        		 " and "								     +
				        		 "oid_Movimento_Ordem_Servico = "  + ed.getOid_Movimento_Ordem_Servico();  
	      executasql.executarUpdate (sql);
		    	}
		    	catch (SQLException e) {
		    		throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleta(PneuED ed)");
	    }
	  }	
    
    public List lista(Movimento_Ordem_ServicoED ed)
    throws Excecoes{
        String sql =
            " select Ordens_Servicos.oid_Ordem_Servico, " +
            "        Ordens_Servicos.NR_Ordem_Servico, " +
            "        Movimentos_Ordens_Servicos.oid_Veiculo, " +
            "        Movimentos_Ordens_Servicos.oid_Tipo_Servico, " +
            "        Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico, " +
            "        Movimentos_Ordens_Servicos.NR_Kilometragem, " +
            "        Movimentos_Ordens_Servicos.NR_Documento, " +
            "        Movimentos_Ordens_Servicos.NR_Quantidade, " +
            "        Movimentos_Ordens_Servicos.VL_Movimento, " +
            "        Tipos_Servicos.CD_Tipo_Servico, " +
            "        Tipos_Servicos.NM_Tipo_Servico, " +
            "        Pessoa_Fornecedor_OS.oid_Pessoa as oid_Fornecedor_OS, " +
            "        Pessoa_Fornecedor_OS.NR_CNPJ_CPF as NR_CNPJ_CPF_Fornecedor_OS, " +
            "        Pessoa_Fornecedor_OS.NM_Razao_Social as NM_Razao_Social_Fornecedor_OS, " +
            "        Pessoa_Fornecedor_OS.oid_Pessoa as oid_Fornecedor_Movimento_OS, " +
            "        Pessoa_Fornecedor_OS.NR_CNPJ_CPF as NR_CNPJ_CPF_Fornecedor_Moviment, " +
            "        Pessoa_Fornecedor_Movimento_OS.NM_Razao_Social as NM_Razao_Social_Fornecedor_Movi, " +
            "        Pessoa_Motorista.oid_Pessoa as oid_Pessoa_Motorista, " +
            "        Pessoa_Motorista.NR_CNPJ_CPF as NR_CNPJ_CPF_Motorista, " +
            "        Pessoa_Motorista.NM_Fantasia as NM_Razao_Social_Motorista ";
        sql += getSQLFrom(ed);
        sql += getSQLFiltro(ed);
        //TIAGO - Manter este order by por veiculo-dt movimento por causa do relat�rio de abastecimentos.
        sql += " order by Movimentos_Ordens_Servicos.oid_Veiculo, Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico, Movimentos_Ordens_Servicos.NR_Kilometragem asc ";
        ResultSet res = executasql.executarConsulta(sql);
        try {
            List lista = new ArrayList();
            while (res.next()) {
                Movimento_Ordem_ServicoED edVolta = new Movimento_Ordem_ServicoED();
                edVolta.setOid_ordem_servico(res.getInt("oid_Ordem_Servico"));
                edVolta.setNr_ordem_servico(res.getInt("NR_Ordem_Servico"));
                edVolta.setOid_veiculo(res.getString("oid_Veiculo"));
                edVolta.setOid_tipo_servico(res.getString("oid_Tipo_Servico"));
                edVolta.setCd_tipo_servico(res.getString("CD_Tipo_Servico"));
                edVolta.setNm_tipo_servico(res.getString("NM_Tipo_Servico"));
                edVolta.setNr_kilometragem(res.getInt("NR_Kilometragem"));
                edVolta.setNr_documento(res.getString("NR_Documento"));
                edVolta.setNr_quantidade(res.getDouble("NR_Quantidade"));
                edVolta.setVl_movimento(res.getDouble("VL_Movimento"));
                edVolta.setOid_fornecedor_os(res.getString("oid_Fornecedor_OS"));
                edVolta.setNr_cnpj_cpf_fornecedor_os(res.getString("NR_CNPJ_CPF_Fornecedor_OS"));
                edVolta.setNm_fornecedor_os(res.getString("NM_Razao_Social_Fornecedor_OS"));
                edVolta.setOid_fornecedor_movimento_os(res.getString("oid_Fornecedor_Movimento_OS"));
                edVolta.setNr_cnpj_cpf_fornecedor_movimento_os(res.getString("NR_CNPJ_CPF_Fornecedor_Moviment"));
                edVolta.setNm_fornecedor_movimento_os(res.getString("NM_Razao_Social_Fornecedor_Movi"));
                edVolta.setDt_movimento_ordem_servico(FormataData.formataDataBT(res.getDate("DT_Movimento_Ordem_Servico")));
                edVolta.setOid_motorista(JavaUtil.getStringNotNull(res.getString("oid_Pessoa_Motorista"), ""));
                edVolta.setNr_cnpj_cpf_motorista(JavaUtil.getStringNotNull(res.getString("NR_CNPJ_CPF_Motorista"), ""));
                edVolta.setNm_motorista(JavaUtil.getStringNotNull(res.getString("NM_Razao_Social_Motorista"), ""));
                lista.add(edVolta);
            }
            return lista;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Movimento_Ordem_ServicoED ed)");
        }
    }
    
	public ArrayList listaMovimento_Ordem_Servico(Movimento_Ordem_ServicoED ed) throws Excecoes {
		double ultima_Km_Anterior = 0;
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"* " +
			"FROM " +
			"Movimentos_Ordens_Servicos as mos, " +
			"tipos_servicos as ts , " +
			"veiculos as v, " +
			"ordens_servicos as os, " +
			"estoques as e, " +
			"motoristas as m, " +
			"fornecedores as f, " +
			"pessoas as p " +
			"WHERE " +
			"mos.oid_tipo_servico = ts.oid_tipo_servico and "  +
			"mos.oid_Veiculo = v.oid_veiculo and " +
			"mos.oid_ordem_Servico = os.oid_ordem_servico and " +
			"mos.oid_estoque = e.oid_estoque and " +
			"mos.oid_motorista = m.oid_motorista and " +
			"mos.oid_pessoa = f.oid_fornecedor and " +
			"m.oid_motorista = p.oid_pessoa " +
			"and mos.oid_Empresa = " + ed.getOid_Empresa() ;
			if (ed.getOid_Veiculo() > 0)
				sql += " and mos.oid_Veiculo = " + ed.getOid_Veiculo()+ " ";	
			if (doValida(ed.getDt_Inicial())) {
			    sql += " AND mos.dt_Movimento_Ordem_Servico > '" + ed.getDt_Inicial()+ "' ";
			}
			if (doValida(ed.getDt_Final())) {
			    sql += " AND mos.dt_Movimento_Ordem_Servico < '" + ed.getDt_Final()+ "' ";
			}
			sql += " ORDER BY " + 
			"mos.dt_Movimento_Ordem_Servico";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Movimento_Ordem_ServicoED mosED = new Movimento_Ordem_ServicoED();
				mosED.setOid_Movimento_Ordem_Servico(res.getLong("oid_Movimento_Ordem_Servico"));
				mosED.setNr_Frota(res.getString("nr_frota"));
				mosED.setDt_Movimento_Ordem_Servico(FormataData.formataDataBT(res.getString("dt_Movimento_Ordem_Servico")));
				mosED.setNr_Odometro(res.getDouble("nr_Odometro"));
				mosED.setNr_Quantidade(res.getDouble("nr_Quantidade"));
				mosED.setNr_Kilometragem(res.getDouble("nr_Kilometragem"));
				mosED.setNr_Kilometragem_Percurso(mosED.getNr_Kilometragem() - ultima_Km_Anterior);
				mosED.setVl_Media_Abastecimento( (mosED.getNr_Kilometragem() - ultima_Km_Anterior) / mosED.getNr_Quantidade());
				ultima_Km_Anterior = (res.getDouble("nr_Kilometragem"));
				mosED.setNr_Documento(res.getLong("nr_Documento"));
				mosED.setOid_Tipo_Servico(res.getLong("oid_Tipo_Servico"));
				if (mosED.getOid_Tipo_Servico() == 1){
					mosED.setNm_Tipo_Servico("Parcial");
				}
				if (mosED.getOid_Tipo_Servico() == 2){
					mosED.setNm_Tipo_Servico("Completo");
				}
				mosED.setDm_Tipo_Abastec(res.getString("dm_Tipo_Abastec"));
				if ("1".equals(mosED.getDm_Tipo_Abastec())){
					mosED.setNm_Tipo_Abastec("Externo");
				}
				if ("2".equals(mosED.getDm_Tipo_Abastec())){
					mosED.setNm_Tipo_Abastec("Interno");
				}
				mosED.setVl_Movimento(res.getDouble("vl_Movimento"));
				mosED.setNm_Motorista(res.getString("nm_Razao_Social"));
				list.add(mosED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}
	
	public ArrayList lista_Abastec_Externo (Movimento_Ordem_ServicoED ed) throws Excecoes {

		String sql = null;
			ArrayList lista = new ArrayList ();
			try {
				sql = "Select " +
				"nm_Razao_Social, " +
				"dm_Tipo_Abastec, " +	
				"mos.oid_Tipo_Servico, " +
				"nr_frota, " +
				"mos.nr_Odometro, " +
				"dt_Movimento_Ordem_Servico, " +
				"nr_Documento, " +
				"mos.oid_Pessoa, " +
				"mos.oid_Motorista, " +
				"mos.nr_Quantidade, "+
				"vl_Movimento " +
				
				"FROM " +
				"Movimentos_Ordens_Servicos as mos, " +
				"veiculos as v, " +
				"fornecedores as f " +
				
				"where " +
				" mos.dm_Tipo_Abastec ='1' and " +
				" mos.oid_veiculo = v.oid_veiculo AND " +
				" mos.oid_Pessoa = oid_Fornecedor AND " +
				" mos.oid_Empresa = " + ed.getOid_Empresa() ;
				  
				if (ed.getOid_Pessoa () > 0) {
					sql += " AND mos.oid_Pessoa = " + ed.getOid_Pessoa () +
					" AND mos.oid_Pessoa = f.oid_Fornecedor" ;
				}
				if (doValida(ed.getDt_Inicial())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico >= '" + ed.getDt_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Final())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico <= '" + ed.getDt_Final()+ "' ";
				}
				sql += " order by " +
					   "mos.dt_Movimento_Ordem_Servico";
				ResultSet res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					Movimento_Ordem_ServicoED popula = new Movimento_Ordem_ServicoED ();
						popula.setNm_Razao_Social(res.getString("nm_Razao_Social"));
						popula.setDm_Tipo_Abastec(res.getString("dm_Tipo_Abastec"));
						popula.setOid_Tipo_Servico(res.getLong("oid_Tipo_Servico"));
						popula.setNr_Frota(res.getString("nr_Frota"));
						popula.setNr_Odometro(res.getDouble("nr_Odometro"));
						popula.setDt_Movimento_Ordem_Servico(FormataData.formataDataBT(res.getString ("Dt_Movimento_Ordem_Servico")));
						popula.setNr_Documento(res.getLong("nr_Documento"));
						popula.setOid_Pessoa(res.getLong("oid_Pessoa"));
						popula.setOid_Motorista(res.getLong("oid_Motorista"));
						popula.setNr_Quantidade(res.getDouble("nr_Quantidade"));
						popula.setVl_Movimento(res.getDouble("vl_Movimento"));
						popula.setVl_Combinado(popula.getNr_Quantidade() * popula.getVl_Movimento());
					  lista.add(popula);  
				}
				return lista;
			
			}
			catch (Exception exc) {
				throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
			}
		}
	
	public ArrayList lista_Abastec_Int (Movimento_Ordem_ServicoED ed) throws Excecoes {

		String sql = null;
			ArrayList lista = new ArrayList ();
			try {
				sql = "Select " +
				"nr_frota, " +
				"mos.nr_Odometro, " +
				"dt_Movimento_Ordem_Servico, " +
				"nr_Documento, " +
				"mos.nr_Quantidade, "+
				"vl_Movimento " +
				
				"FROM " +
				"Movimentos_Ordens_Servicos as mos, " +
				"veiculos as v, " +
				"fornecedores as f " +
				
				"where " +
				" mos.dm_Tipo_Abastec ='2' and " +
				" mos.oid_veiculo = v.oid_veiculo AND " +
				" mos.oid_Pessoa = oid_Fornecedor AND " +
				" mos.oid_Empresa = " + ed.getOid_Empresa() ;
				  
				if (ed.getOid_Pessoa () > 0) {
					sql += " AND mos.oid_Pessoa = " + ed.getOid_Pessoa () +
					" AND mos.oid_Pessoa = f.oid_Fornecedor" ;
				}
				if (doValida(ed.getDt_Inicial())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico >= '" + ed.getDt_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Final())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico <= '" + ed.getDt_Final()+ "' ";
				}
				sql += " order by " +
					   "mos.dt_Movimento_Ordem_Servico";
				ResultSet res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					Movimento_Ordem_ServicoED popula = new Movimento_Ordem_ServicoED ();
						popula.setNr_Frota(res.getString("nr_Frota"));
						popula.setNr_Odometro(res.getDouble("nr_Odometro"));
						popula.setDt_Movimento_Ordem_Servico(FormataData.formataDataBT(res.getString ("Dt_Movimento_Ordem_Servico")));
						popula.setNr_Documento(res.getLong("nr_Documento"));
						popula.setNr_Quantidade(res.getDouble("nr_Quantidade"));
						popula.setVl_Movimento(res.getDouble("vl_Movimento"));
						popula.setVl_Combinado(popula.getNr_Quantidade() * popula.getVl_Movimento());
					  lista.add(popula);  
				}
				return lista;
			
			}
			catch (Exception exc) {
				throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
			}
		}
	
	public ArrayList list_Medias_Cons (Movimento_Ordem_ServicoED ed) throws Excecoes {
		double nr_Kilometragem_Percurso = 0;
		String sql = null;
			ArrayList lista = new ArrayList ();
			try {
				sql = " Select " +
				" nr_frota, " +
				" nr_Kilometragem, " +
				" nr_Quantidade, " +
				" vl_Movimento, " +
				" mv.nr_media_superior," +
				" mv.nr_media_inferior " +
				
				" FROM " +
				" Movimentos_Ordens_Servicos as mos, " +
				" veiculos as v, " +
				" modelos_veiculos as mv " +
				
				" where " +
				" mos.oid_Veiculo = mv.oid_modelo_veiculo AND " +
				" mos.oid_veiculo = v.oid_veiculo AND " +
				" mos.oid_Empresa = " + ed.getOid_Empresa() ;
				if (ed.getOid_Veiculo () > 0) {
					sql += " AND mos.oid_Veiculo = " + ed.getOid_Veiculo ();
				}
				if (doValida(ed.getDt_Inicial())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico >= '" + ed.getDt_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Final())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico <= '" + ed.getDt_Final()+ "' ";
				}
				sql += " order by " +
					   "mos.oid_Veiculo";
				ResultSet res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					Movimento_Ordem_ServicoED popula = new Movimento_Ordem_ServicoED ();
						popula.setNr_Frota(res.getString("nr_Frota"));
						popula.setNr_Kilometragem(res.getDouble("nr_Kilometragem"));
						popula.setVl_Movimento((res.getDouble("vl_Movimento")));
						popula.setNr_Quantidade(res.getDouble("nr_Quantidade"));
						popula.setNr_Media_Inferior(res.getDouble("nr_Media_Inferior"));
						popula.setNr_Media_Superior(res.getDouble("nr_Media_Superior"));
						popula.setNr_Kilometragem_Percurso(popula.getNr_Kilometragem() - nr_Kilometragem_Percurso);
						popula.setVl_Media_Abastecimento(popula.getNr_Kilometragem_Percurso() / popula.getNr_Quantidade());
						popula.setVl_Km(popula.getVl_Movimento() / popula.getNr_Kilometragem_Percurso());
						
					  lista.add(popula);  
				}
				return lista;
			
			}
			catch (Exception exc) {
				throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
			}
		}
	
	public ArrayList list_Totais_Abastec (Movimento_Ordem_ServicoED ed) throws Excecoes {
		
		String sql = null;
			ArrayList lista = new ArrayList ();
			try {
				sql = "Select " +
				"nm_Razao_Social, " +
				//"nm_Combustivel, " +
				"vl_Movimento, " +
				"nr_Quantidade " +
				
				
				"FROM " +
				"Movimentos_Ordens_Servicos as mos, " +
				"veiculos as v, " +
				"fornecedores as f " +
				
				" where " +
				" mos.oid_veiculo = v.oid_veiculo AND " +
				" mos.oid_Pessoa = f.oid_Fornecedor AND" +
				" mos.oid_Empresa = " + ed.getOid_Empresa() ;
				if (ed.getOid_Pessoa () > 0) {
					sql += " AND mos.oid_Pessoa = " + ed.getOid_Pessoa ();
				}
				if (doValida(ed.getDt_Inicial())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico >= '" + ed.getDt_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Final())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico <= '" + ed.getDt_Final()+ "' ";
				}
				sql += " order by " +
					   " mos.oid_Veiculo";
				ResultSet res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					Movimento_Ordem_ServicoED popula = new Movimento_Ordem_ServicoED ();
						popula.setNm_Razao_Social(res.getString("nm_Razao_Social"));
						//popula.setNm_Combustivel(res.getString("nm_Combustivel"));
						popula.setVl_Movimento(res.getDouble("vl_Movimento"));
						popula.setNr_Quantidade(res.getDouble("nr_Quantidade"));
					  lista.add(popula);  
				}
				return lista;
			
			}
			catch (Exception exc) {
				throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
			}
		}
	
	public ArrayList lista_Excessoes_Medias (Movimento_Ordem_ServicoED ed) throws Excecoes {
		double nr_Kilometragem_Percurso = 0;
		String sql = null;
			ArrayList lista = new ArrayList ();
			try {
				sql = "Select " +
				" nr_Frota , " +
				" nr_Kilometragem , " +
				" nm_tipo_veiculo , " +
				" nm_marca_veiculo , " +
				" mov.nr_Media_Inferior , " +
				" mov.nr_Media_Superior , " +
				" mos.nr_Odometro , " +
				" dt_Movimento_Ordem_Servico , " +
				" nr_Quantidade , " +
				" vl_Movimento " +
				
				"FROM " +
				" Movimentos_Ordens_Servicos as mos, " +
				" veiculos as v, " +
				" tipos_veiculos as tv, " +
				" marcas_veiculos as mv, " +
				" modelos_veiculos as mov " +
				
				"where " +
				" mov.oid_tipo_veiculo = tv.oid_tipo_Veiculo and " +
				" mos.oid_veiculo = v.oid_veiculo AND " +
				" mov.oid_marca_veiculo = mv.oid_marca_Veiculo and " +
				" v.oid_modelo_veiculo = mov.oid_modelo_Veiculo and " +
				" mos.oid_Empresa = " + ed.getOid_Empresa() ;
				
				if (ed.getOid_Veiculo () > 0) {
					sql += " AND mos.oid_Veiculo = " + ed.getOid_Veiculo ();
				}
				if (doValida(ed.getDt_Inicial())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico >= '" + ed.getDt_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Final())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico <= '" + ed.getDt_Final()+ "' ";
				}
				sql += " order by " +
					   "mos.dt_Movimento_Ordem_Servico";
				ResultSet res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					double kilometro = (res.getDouble("nr_Kilometragem"));
					double litros = (res.getDouble("nr_Quantidade"));
					double media = (kilometro - nr_Kilometragem_Percurso) / litros;
					Movimento_Ordem_ServicoED popula = new Movimento_Ordem_ServicoED ();
					if (media < res.getDouble("nr_Media_Inferior") || media > res.getDouble("nr_Media_Superior")) {
						popula.setNr_Frota(res.getString("nr_Frota"));
						popula.setNr_Kilometragem(res.getDouble("nr_Kilometragem"));
						popula.setNm_Tipo_Veiculo(res.getString("nm_Tipo_Veiculo"));
						popula.setNm_Marca_Veiculo(res.getString("nm_Marca_Veiculo"));
						popula.setNr_Media_Inferior(res.getDouble("nr_Media_Inferior"));
						popula.setNr_Media_Superior(res.getDouble("nr_Media_Superior"));
						popula.setNr_Odometro(res.getDouble("nr_Odometro"));
						popula.setDt_Movimento_Ordem_Servico(FormataData.formataDataBT(res.getString ("Dt_Movimento_Ordem_Servico")));
						popula.setNr_Quantidade(res.getDouble("nr_Quantidade"));
						popula.setVl_Movimento(res.getDouble("vl_Movimento"));
						popula.setVl_Media_Abastecimento((popula.getNr_Kilometragem() - nr_Kilometragem_Percurso) / popula.getNr_Quantidade());
					  lista.add(popula); 
					}
				}
				return lista;
			}
			catch (Exception exc) {
				throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
			}
		}
	
	public ArrayList lista_Abastec_Doc_Mot (Movimento_Ordem_ServicoED ed) throws Excecoes {
			String sql = null;
			ArrayList lista = new ArrayList ();
			try {
				sql = "Select " +
				" dt_Movimento_Ordem_Servico , " +
				" nr_Frota , " +
				" mos.nr_Odometro  , " +
				" nr_Quantidade , " +
				" vl_Movimento , " +
				" nr_Documento , " +
				" nm_tipo_veiculo " +
				"FROM " +
				" Movimentos_Ordens_Servicos as mos, " +
				" veiculos as v, " +
				" tipos_veiculos as tv, " +
				" modelos_veiculos as mov " +
				
				"where " +
				" mov.oid_tipo_veiculo = tv.oid_tipo_Veiculo and  " +
				" mos.oid_veiculo = v.oid_veiculo AND  " +
				" v.oid_modelo_veiculo = mov.oid_modelo_Veiculo and  " +
				" mos.oid_Empresa = " + ed.getOid_Empresa() ;
				
				if (ed.getNr_Documento() > 0 ) {
					sql += " AND nr_Documento = " + ed.getNr_Documento();
				}
				if (ed.getOid_Motorista() > 0 ) {
					sql += " AND oid_Motorista = " + ed.getOid_Motorista();
				}
				if (doValida(ed.getDt_Inicial())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico >= '" + ed.getDt_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Final())) {
				    sql += " AND mos.dt_Movimento_Ordem_Servico <= '" + ed.getDt_Final()+ "' ";
				}
				sql += " order by " +
					   "mos.dt_Movimento_Ordem_Servico";
				ResultSet res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					Movimento_Ordem_ServicoED popula = new Movimento_Ordem_ServicoED ();
						popula.setDt_Movimento_Ordem_Servico(FormataData.formataDataBT(res.getString ("Dt_Movimento_Ordem_Servico")));
						popula.setNr_Frota(res.getString("nr_Frota"));
						popula.setNr_Odometro(res.getDouble("nr_Odometro"));
						popula.setNr_Quantidade(res.getDouble("nr_Quantidade"));
						popula.setVl_Movimento(res.getDouble("vl_Movimento"));
						popula.setNr_Documento(res.getLong("nr_Documento"));
						popula.setNm_Tipo_Veiculo(res.getString("nm_Tipo_Veiculo"));
					lista.add(popula);
					}
				return lista;
			}
			catch (Exception exc) {
				throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
			}
		}
	
    /** ------------ RELAT�RIOS ---------------- */
    //*** Relat�rio de Manuten��es Preventivas por ve�culo
    public void relMovimentosOrdemServico(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
        ed.setLista(lista(ed));
        //*** Chama o Gerador de Relat�rios Jasper
    }
    public void relAbastecimento(Movimento_Ordem_ServicoED ed) throws Excecoes {
        String veiculoAnterior;
        int kilometragemAnterior;
        List lista = lista(ed);
        veiculoAnterior = "";
        kilometragemAnterior = 0;
        Iterator iterator = lista.iterator();
        while (iterator.hasNext())
        {
            Movimento_Ordem_ServicoED mos = (Movimento_Ordem_ServicoED)iterator.next();
            //Quebra por ve�culo
            if (!veiculoAnterior.equals(mos.getOid_veiculo()))
            {
                veiculoAnterior = mos.getOid_veiculo();
                if (doValida(ed.getDt_inicial())) {
                    kilometragemAnterior = getKMUltimoServicoByRecord(ed, mos.getOid_veiculo());
                } else kilometragemAnterior = mos.getNr_kilometragem();
            }
            if (kilometragemAnterior == 0)
                mos.setNr_kilometragem_diferenca(0);
            else mos.setNr_kilometragem_diferenca(mos.getNr_kilometragem() - kilometragemAnterior);
            
            kilometragemAnterior = mos.getNr_kilometragem();
            mos.setMedia_consumo(mos.getNr_kilometragem_diferenca() / mos.getNr_quantidade());
        }
        ed.setLista(lista);
        //*** Chama o Gerador de Relat�rios Jasper
    }

    private int getKMUltimoServicoByRecord(Movimento_Ordem_ServicoED ed, String oid_Veiculo)
    throws Excecoes {
        Movimento_Ordem_ServicoED filtro;
        try {
            filtro = (Movimento_Ordem_ServicoED)ed.clone();
        } catch (CloneNotSupportedException e) {
            throw new Excecoes("M�todo clone n�o implementado na classe [Movimento_Ordem_ServicoED]!");
        }
        filtro.setDt_inicial(null);
        //Pega o dia anterior a ed.getDt_inicial()
        String dtFinal;
        if (doValida(ed.getDt_inicial())) {
            dtFinal = FormataData.formataDataBT(new java.sql.Date(FormataData.formataDataTB(ed.getDt_inicial()).getTime() - FormataData.DIA_EM_MILISEGUNDOS));
        } else throw new Mensagens("Data inicial n�o informada!");
        filtro.setDt_final(dtFinal);
        filtro.setOid_veiculo(oid_Veiculo);
        String sqlDTUltimoServico =
            "select max(Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico) as dtUltimoServico";
        sqlDTUltimoServico += getSQLFrom(filtro);
        sqlDTUltimoServico += getSQLFiltro(filtro);
        String dtUltimoServico = "";
        int kmUltimoServico = 0;
        ResultSet resDTUltimoServico = executasql.executarConsulta(sqlDTUltimoServico);
        try {
            try {
                if (resDTUltimoServico.next()) {
                    dtUltimoServico = FormataData.formataDataBT(resDTUltimoServico.getDate("dtUltimoServico"));
                }
            } catch (SQLException e) {
                throw new Excecoes(e.getMessage(), e, getClass().getName(), "getKMByRecord(Movimento_Ordem_ServicoED ed)");
            }
        } finally {
            closeResultset(resDTUltimoServico);
        }
        if (doValida(dtUltimoServico)) {
	        filtro.setDt_inicial(dtUltimoServico);
	        filtro.setDt_final(dtUltimoServico);
	        String sqlKMUltimoServico =
	            "select max(Movimentos_Ordens_Servicos.NR_Kilometragem) as kmUltimoServico";
	        sqlKMUltimoServico += getSQLFrom(filtro);
	        sqlKMUltimoServico += getSQLFiltro(filtro);
	        ResultSet resKMUltimoServico = executasql.executarConsulta(sqlKMUltimoServico);
	        try {
	            try {
	                if (resKMUltimoServico.next()) {
	                    kmUltimoServico = resKMUltimoServico.getInt("kmUltimoServico");
	                }
	            } catch (SQLException e) {
	                throw new Excecoes(e.getMessage(), e, getClass().getName(), "getKMByRecord(Movimento_Ordem_ServicoED ed)");
	            }
	        } finally {
	            closeResultset(resKMUltimoServico);
	        }
	        return kmUltimoServico;
        } else return 0;
    }

    private String getSQLFiltro(Movimento_Ordem_ServicoED ed) {
        String toReturn = " where 1 = 1 ";
        if (ed.getOid_ordem_servico() > 0) {
            toReturn += " and Ordens_Servicos.oid_Ordem_Servico = " + ed.getOid_ordem_servico();
        }
        if (ed.getOid_Empresa() > 0) {
            toReturn += " and Unidades.oid_Empresa = " + ed.getOid_Empresa();
        }
        if (ed.getOid_Unidade() > 0) {
            toReturn += " and Unidades.oid_Unidade = " + ed.getOid_Unidade();
        }
        if (doValida(ed.getOid_veiculo())) {
            toReturn += " and Movimentos_Ordens_Servicos.oid_Veiculo = " + JavaUtil.getSQLString(ed.getOid_veiculo());
        }
        if (doValida(ed.getDt_inicial())) {
            toReturn += " and Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico >= " + JavaUtil.getSQLDate(ed.getDt_inicial());
        }
        if (doValida(ed.getDt_final())) {
            toReturn += " and Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico <= " + JavaUtil.getSQLDate(ed.getDt_final());
        }
        if (doValida(ed.getOid_tipo_servico())) {
            toReturn += " and Movimentos_Ordens_Servicos.oid_Tipo_Servico = " + ed.getOid_tipo_servico();
        }
        if (doValida(ed.getOid_fornecedor_os())) {
            toReturn += " and Ordens_Servicos.oid_Pessoa_Fornecedor = " + ed.getOid_fornecedor_os();
        }
        if (doValida(ed.getOid_fornecedor_movimento_os())) {
            toReturn += " and Movimentos_Ordens_Servicos.oid_Pessoa = " + ed.getOid_fornecedor_movimento_os();
        }
        if (doValida(ed.getDm_tipo_pagamento())) {
            toReturn += " and Movimentos_Ordens_Servicos.DM_Faturado_Pago = " + JavaUtil.getSQLString(ed.getDm_tipo_pagamento());
        }
        if (doValida(ed.getDm_tipo_despesa())) {
            toReturn += " and Tipos_Servicos.DM_Tipo_Despesa = " + JavaUtil.getSQLString(ed.getDm_tipo_despesa());
        }
        if (doValida(ed.getOid_motorista())) {
            toReturn += " and Movimentos_Ordens_Servicos.oid_Motorista = " + JavaUtil.getSQLString(ed.getOid_motorista());
        }
        return toReturn;
    }

    private String getSQLFrom(Movimento_Ordem_ServicoED ed) {
        String toReturn =
	        " from Ordens_Servicos " +
	        "      inner join (Movimentos_Ordens_Servicos " +
	        "                  inner join Tipos_Servicos " +
	        "                    on Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico " +
	        "                  inner join Veiculos " +
	        "                    on Movimentos_Ordens_Servicos.oid_Veiculo = Veiculos.oid_Veiculo " +
	        "                  inner join Pessoas Pessoa_Fornecedor_Movimento_OS " +
	        "                    on Movimentos_Ordens_Servicos.oid_Pessoa = Pessoa_Fornecedor_Movimento_OS.oid_Pessoa " +
	        "                  left join Pessoas Pessoa_Motorista " +
	        "                    on Movimentos_Ordens_Servicos.oid_Motorista = Pessoa_Motorista.oid_Pessoa" +
	        "      ) " +
	        "        on Ordens_Servicos.oid_Ordem_Servico = Movimentos_Ordens_Servicos.oid_Ordem_Servico " +
	        "      inner join Unidades " +
	        "        on Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade " +
	        "      inner join Pessoas Pessoa_Fornecedor_OS " +
	        "        on Ordens_Servicos.oid_Pessoa_Fornecedor = Pessoa_Fornecedor_OS.oid_Pessoa ";
        return toReturn;
    }

    public void relKMsRodados(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
    	String sql =
    		" select oid_Veiculo " +
    		"       ,NR_Placa " +
    		"       ,Modelos_Veiculos.NM_Modelo_Veiculo " +
			" from Veiculos, " +
			"      Modelos_Veiculos " +
			" where Veiculos.oid_Modelo_Veiculo = Modelos_Veiculos.oid_Modelo_Veiculo ";
    	if (doValida(ed.getOid_veiculo())) {
    		sql += "   and Veiculos.oid_Veiculo = " + getSQLString(ed.getOid_veiculo());
    	}
    	sql += " order by Veiculos.NR_Placa ";
    	ResultSet res = executasql.executarConsulta(sql);
    	try {
    		try {
    			List lista = new ArrayList();
				while (res.next()) {
					Movimento_Ordem_ServicoED edVolta = new Movimento_Ordem_ServicoED();
					edVolta.setOid_veiculo(res.getString("oid_Veiculo"));
					edVolta.setNR_Placa(res.getString("NR_Placa"));
					edVolta.setNM_Modelo_Veiculo(res.getString("NM_Modelo_Veiculo"));
					int kmMin;
					int kmMax;
					ResultSet resMin = executasql.executarConsulta(getSQLStringKMRodados(edVolta.getOid_veiculo(), true, ed));
					try {
						if (resMin.next()) {
							kmMin = resMin.getInt("km");
						} else kmMin = 0;
					} finally {
						closeResultset(resMin);
					}
					ResultSet resMax = executasql.executarConsulta(getSQLStringKMRodados(edVolta.getOid_veiculo(), false, ed));
					try {
						if (resMax.next()) {
							kmMax = resMax.getInt("km");
						} else kmMax = 0;
					} finally {
						closeResultset(resMax);
					}
					edVolta.setNr_kilometragem_diferenca(kmMax - kmMin);
					lista.add(edVolta);
				}
		        ed.setLista(lista);
			} catch (SQLException e) {
				throw new Excecoes(e.getMessage(), e, getClass().getName(), "relKMsRodados(Movimento_Ordem_ServicoED ed)");
			}
    	} finally {
    		closeResultset(res);
    	}
    }

    private String getSQLStringKMRodados(String oid_Veiculo, boolean min, Movimento_Ordem_ServicoED ed) {
		String toReturn =
			" select " + (min ? "min" : "max") + "(Movimentos_Ordens_Servicos.NR_Kilometragem) as km ";
		toReturn +=
			" from Movimentos_Ordens_Servicos " +
			"     ,Ordens_Servicos " +
			"     ,Unidades " +
			" where Movimentos_Ordens_Servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
			"   and Ordens_Servicos.oid_Unidade = Unidades.oid_Unidade" +
			"   and Movimentos_Ordens_Servicos.oid_Veiculo = " + getSQLString(oid_Veiculo);
		if (ed.getOid_Empresa() > 0) {
			toReturn += "   and Unidades.oid_Empresa = " + ed.getOid_Empresa();
		}
		if (ed.getOid_Unidade() > 0) {
			toReturn += "   and Movimentos_Ordens_Servicos.oid_Unidade = " + ed.getOid_Unidade();
		}
		if (doValida(ed.getDt_inicial())) {
			toReturn += "   and Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico >= " + JavaUtil.getSQLDate(ed.getDt_inicial());
		}
		if (doValida(ed.getDt_final())) {
			toReturn += "   and Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico <= " + JavaUtil.getSQLDate(ed.getDt_final());
		}
    	return toReturn;
    }


    /**
     * Retorna a lista dos abastecimentos de um dado per�odo com a m�dia levando em considera��o o abastecimento completo
     * @param ed
     * @return
     * @throws Excecoes
     */
    public ArrayList listaAbastecimentosComMedia (AbastecimentoED ed) throws Excecoes {
    	String sql = null;
    	ArrayList lista = new ArrayList ();
    	try {
    		sql = " Select " +
			" a.* " +
    		",v.nr_Frota " +
    		",r.nm_Marca_Veiculo " +
    		",m.nm_Modelo_Veiculo " +
    		",t.nm_Apelido " +
    		",f.nm_Razao_Social " +
    		",m.nr_Media_Superior" +
    		",m.nr_Media_Inferior " +
    		" FROM " +
    		" Abastecimentos as a " +
    		"  		left join Motoristas as t on t.oid_motorista = a.oid_motorista "+
    		"  		left join Fornecedores as f on f.oid_fornecedor = a.oid_fornecedor "+
    		"  		left join Veiculos as v on v.oid_Veiculo = a.oid_Veiculo "+
    		"  		left join Modelos_Veiculos as m on m.oid_Modelo_Veiculo = v.oid_Modelo_Veiculo "+
    		"		left join marcas_veiculos as r on r.oid_marca_veiculo = m.oid_marca_veiculo "+
    		" where " +
    		" a.oid_Empresa = " + ed.getOid_Empresa() ;
    		if (doValida(ed.getOid_Veiculo ())) {
    			sql += " AND a.oid_Veiculo = '" + ed.getOid_Veiculo() +"' ";
    		}
    		if (doValida(ed.getDt_Inicial())) {
    			sql += " AND a.dt_Abastecimento >= '" + ed.getDt_Inicial()+ "' ";
    		}
    		if (doValida(ed.getDt_Final())) {
    			sql += " AND a.dt_Abastecimento <= '" + ed.getDt_Final()+ "' ";
    		}
    		sql += " order by " +
    		"v.nr_Frota , " +
    		"a.dt_Abastecimento";
    		ResultSet res = this.executasql.executarConsulta (sql);
    		while (res.next ()) {
    			AbastecimentoED pop = new AbastecimentoED();
				pop.setOid_Abastecimento(res.getInt("oid_Abastecimento"));
				pop.setOid_Empresa(res.getInt("oid_Empresa"));
				pop.setOid_Veiculo(res.getString("oid_Veiculo"));
				pop.setOid_Bomba(res.getInt("oid_Bomba"));
				pop.setOid_Motorista(res.getString("oid_Motorista"));
				pop.setOid_Fornecedor(res.getString("oid_Fornecedor"));
				pop.setDm_Completo(res.getString("dm_Completo"));
				pop.setDm_Tipo_Abastec(res.getString("dm_Tipo_Abastec"));
				pop.setDt_Abastecimento(FormataData.formataDataBT(res.getDate("dt_Abastecimento")));
				pop.setNr_Quantidade(res.getDouble("nr_Quantidade_Media"));
				pop.setNr_Kilometragem(res.getDouble("nr_Kilometragem"));
				pop.setNr_Odometro(res.getDouble("nr_Odometro"));
				pop.setNr_Documento(res.getString("nr_Documento"));
    			pop.setVl_Abastecimento(res.getDouble("vl_Abastecimento"));
    			pop.setNr_Apontador_Ctf(res.getInt("nr_Apontador_Ctf"));
    			pop.setNr_Numabast_Ctf(res.getInt("nr_Numabast_Ctf"));
    			pop.setDt_Debito_Ctf(FormataData.formataDataBT(res.getDate("dt_Debito_Ctf")));
    			pop.setDm_Stamp(res.getString("dm_Stamp"));
    			pop.setDt_Stamp(FormataData.formataDataBT(res.getDate("dt_Stamp")));
    			pop.setUsuario_Stamp(res.getString("usuario_Stamp"));
    			pop.setNr_Kilometragem_Percurso(res.getDouble("nr_Kilometragem_Percurso_Media"));
    			//Calcula a m�dia e arredonda
    			if ("C".equals(pop.getDm_Completo())) 
					pop.setVl_Media_Abastecimento(new BigDecimal(pop.getNr_Kilometragem_Percurso()/pop.getNr_Quantidade()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    			// Campos de outras tabelas
    			pop.setNm_Razao_Social(res.getString("nm_Razao_Social"));
    			pop.setNm_Motorista(res.getString("nm_apelido"));
    			pop.setNm_Marca_Veiculo(res.getString("nm_Marca_Veiculo"));
    			pop.setNm_Modelo_Veiculo(res.getString("nm_Modelo_Veiculo"));
				// Coloca na lista
    			lista.add(pop);  
    		}
    		return lista;
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista2()");
    	}
    }

    /**
     * Calcula a m�dia mensal levando em considera��o o abastecimento completo
     * @param ed
     * @return
     * @throws Excecoes
     */
    public ArrayList mediaMensal (AbastecimentoED ed) throws Excecoes {
    	String sql = null;
    	ArrayList lista = new ArrayList ();
    	try {
    		sql = " select " +
    		" a.oid_veiculo as oid_veiculo "+
    		",a.dm_Ano_Mes "+
    		",v.nr_Frota " +
    		",r.nm_Marca_Veiculo" +
    		",m.nm_Modelo_Veiculo " +
    		",max(nr_kilometragem) as nr_kilometragem " +
    		",max(m.nr_media_superior) as nr_media_superior "+ 
        	",max(m.nr_media_inferior) as nr_media_inferior "+
    		",sum(nr_kilometragem_Percurso_Media) as nr_Kilometragem_Percurso " +
    		",sum(nr_Quantidade_Media) as qtd " +
    		",sum(nr_kilometragem_Percurso_Media)/sum(nr_Quantidade_Media) as vl_Media_Abastecimento " +
    		",sum(vl_Abastecimento_Media) as vl_Abastecimento "+
    		",sum(vl_Abastecimento_Media)/sum(nr_kilometragem_percurso_media) as vl_km "+
    		" FROM " +
    		" Abastecimentos as a " +
    		"		left join Veiculos as v on v.oid_Veiculo = a.oid_Veiculo  " +
    		"		left join Modelos_Veiculos as m on m.oid_Modelo_Veiculo =  v.oid_Modelo_Veiculo " +
    		"		left join marcas_veiculos as r on r.oid_marca_veiculo = m.oid_marca_veiculo  "+
    		" where " +
    		" a.dm_Completo = 'C' and " + 
    		" a.oid_Empresa = " + ed.getOid_Empresa() ;
    		if ( doValida(ed.getOid_Veiculo()))
    			sql += " and a.oid_Veiculo = '" + ed.getOid_Veiculo() + "' ";
    		if (doValida(ed.getDt_Inicial())) 
    			sql += " and a.dt_Abastecimento >= '" + ed.getDt_Inicial() + "' " ;
    		if (doValida(ed.getDt_Final()))
    			sql += " and a.dt_Abastecimento <= '" + ed.getDt_Final() + "' " ;
    		sql += " group by " +
    		" a.oid_veiculo " +
    		",a.dm_Ano_Mes," +
    		" v.nr_Frota" +
    		",r.nm_Marca_Veiculo" +
    		",m.nm_Modelo_Veiculo";
    		ResultSet res = this.executasql.executarConsulta (sql);
    		while (res.next ()) {
    			AbastecimentoED pop = new AbastecimentoED();
				pop.setOid_Veiculo(res.getString("oid_Veiculo"));
				pop.setDt_Abastecimento(FormataData.getMesAno(res.getString("dm_Ano_Mes")));
				pop.setNr_Frota(res.getString("nr_Frota"));
				pop.setNr_Kilometragem(res.getDouble("nr_kilometragem"));
				pop.setNr_Quantidade(res.getDouble("qtd"));
				pop.setNr_Kilometragem_Percurso(res.getDouble("nr_Kilometragem_Percurso"));
				pop.setVl_Abastecimento(res.getDouble("vl_Abastecimento"));
				//Arredonda a m�dia
				pop.setVl_Media_Abastecimento(new BigDecimal(res.getDouble("vl_Media_Abastecimento")).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				//Arredonda o valor do km
				pop.setVl_Km(new BigDecimal(res.getDouble("vl_km")).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
				// Campos de outras tabelas
    			pop.setNm_Marca_Veiculo(res.getString("nm_Marca_Veiculo"));
    			pop.setNm_Modelo_Veiculo(res.getString("nm_Modelo_Veiculo"));
				// Coloca na lista
				lista.add(pop);
    		}
    		return lista;
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "mediaMensal()");
    	}
    }

    /**
     * Retorna o nr_Kilometragem do abastecimento anterior a uma dada data para um dado veiculo
     * @param ed
     * @return
     * @throws Excecoes
     */
    public double getKmAbastecimentoAnterior (AbastecimentoED ed) throws Excecoes {
    	double nrKmAnt = 0;
    	String sql = null;
    	try {
    		sql = " select " +
    		" nr_Kilometragem " +
    		" FROM " +
    		" Abastecimentos as a " +
    		" where " +
    		" a.oid_Empresa = " + ed.getOid_Empresa() +
			" and a.oid_Veiculo = " + ed.getOid_Veiculo () +
			" and a.dt_Abastecimento <='" + ed.getDt_Abastecimento()+"'" + 
    		" and a.nr_Kilometragem < " + ed.getNr_Kilometragem() +
    		" order by " +
    		" a.dt_Abastecimento desc, " +
    		" a.nr_Kilometragem desc " +
    		" limit 1 ";
    		ResultSet res = this.executasql.executarConsulta (sql);
    		if (res.next ()) 
    			nrKmAnt = res.getDouble("nr_Kilometragem");
    		return nrKmAnt;
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "kmAbastecimentoAnterior()");
    	}
    }

    /**
     * Retorna um ed com o abastecimento imediatamente seguinte a data[opcional], km e oid[opcional] 
     * @param ed
     * @return
     * @throws Excecoes
     */
    public AbastecimentoED getProximoAbastecimento (AbastecimentoED ed) throws Excecoes {
    	AbastecimentoED pop = new AbastecimentoED ();
    	String sql = null;
    	try {
    		sql = " select " +	
    		" * " +
    		" FROM " +
    		" Abastecimentos as a " +
    		" where " +
    		" a.oid_Empresa = " + ed.getOid_Empresa() +
			" and a.oid_Veiculo = '" + ed.getOid_Veiculo () + "'" ;
		if (doValida(ed.getDt_Abastecimento())) 
			sql +=" and a.dt_Abastecimento >= '" + ed.getDt_Abastecimento() + "'" +
			" and a.oid_Abastecimento <> " + ed.getOid_Abastecimento() ;
		sql+=" and a.nr_Kilometragem > " + ed.getNr_Kilometragem() +
    		" order by " +
    		" a.dt_Abastecimento asc, " +
    		" a.nr_Kilometragem asc " +
    		" limit 1 ";
    		ResultSet res = this.executasql.executarConsulta (sql);
    		if (res.next ()) {
				pop.setOid_Abastecimento(res.getInt("oid_Abastecimento"));
				pop.setOid_Empresa(res.getInt("oid_Empresa"));
				pop.setOid_Veiculo(res.getString("oid_Veiculo"));
				pop.setOid_Bomba(res.getInt("oid_Bomba"));
				pop.setOid_Motorista(res.getString("oid_Motorista"));
				pop.setOid_Fornecedor(res.getString("oid_Fornecedor"));
				pop.setDm_Completo(res.getString("dm_Completo"));
				pop.setDm_Tipo_Abastec(res.getString("dm_Tipo_Abastec"));
				pop.setDt_Abastecimento(FormataData.formataDataBT(res.getDate("dt_Abastecimento")));
				pop.setNr_Kilometragem_Percurso(res.getDouble("nr_Kilometragem_Percurso"));
				pop.setNr_Quantidade(res.getDouble("nr_Quantidade"));
				pop.setVl_Abastecimento(res.getDouble("vl_Abastecimento"));
				pop.setNr_Kilometragem(res.getDouble("nr_Kilometragem"));
				pop.setNr_Odometro(res.getDouble("nr_Odometro"));
				pop.setNr_Documento(res.getString("nr_Documento"));
    			pop.setNr_Apontador_Ctf(res.getInt("nr_Apontador_Ctf"));
    			pop.setNr_Numabast_Ctf(res.getInt("nr_Numabast_Ctf"));
    			pop.setDt_Debito_Ctf(FormataData.formataDataBT(res.getDate("dt_Debito_Ctf")));
    			pop.setNr_Kilometragem_Percurso_Media(res.getDouble("nr_Kilometragem_Percurso_Media"));
    			pop.setNr_Quantidade_Media(res.getDouble("nr_Quantidade_Media"));
    			pop.setVl_Abastecimento_Media(res.getDouble("vl_Abastecimento_Media"));
    			pop.setDm_Stamp(res.getString("dm_Stamp"));
    			pop.setDt_Stamp(FormataData.formataDataBT(res.getDate("dt_Stamp")));
    			pop.setUsuario_Stamp(res.getString("usuario_Stamp"));
    		}	
    		return pop;
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getProximoAbastecimento()");
    	}
    }
    /**
     * Retorna um ed com o abastecimento imediatamente anterior a data[opcional] e km  
     * @param ed
     * @return
     * @throws Excecoes
     */
    public AbastecimentoED getAbastecimentoAnterior (AbastecimentoED ed) throws Excecoes {
    	AbastecimentoED pop = new AbastecimentoED ();
    	String sql = null;
    	try {
    		sql = " select " +	
    		" * " +
    		" FROM " +
    		" Abastecimentos as a " +
    		" where " +
    		" a.oid_Empresa = " + ed.getOid_Empresa() +
			" and a.oid_Veiculo = " + ed.getOid_Veiculo () ;
		if (doValida(ed.getDt_Abastecimento())) 
			sql+=" and a.dt_Abastecimento <='" + ed.getDt_Abastecimento()+"'"; 
		sql+=" and a.nr_Kilometragem < " + ed.getNr_Kilometragem() +
    		" order by " +
    		" a.dt_Abastecimento desc, " +
    		" a.nr_Kilometragem desc " +
    		" limit 1 ";
    		ResultSet res = this.executasql.executarConsulta (sql);
    		if (res.next ()) {
				pop.setOid_Abastecimento(res.getInt("oid_Abastecimento"));
				pop.setOid_Empresa(res.getInt("oid_Empresa"));
				pop.setOid_Veiculo(res.getString("oid_Veiculo"));
				pop.setOid_Bomba(res.getInt("oid_Bomba"));
				pop.setOid_Motorista(res.getString("oid_Motorista"));
				pop.setOid_Fornecedor(res.getString("oid_Fornecedor"));
				pop.setDm_Completo(res.getString("dm_Completo"));
				pop.setDm_Tipo_Abastec(res.getString("dm_Tipo_Abastec"));
				pop.setDt_Abastecimento(FormataData.formataDataBT(res.getDate("dt_Abastecimento")));
				pop.setNr_Kilometragem_Percurso(res.getDouble("nr_Kilometragem_Percurso"));
				pop.setNr_Quantidade(res.getDouble("nr_Quantidade"));
				pop.setVl_Abastecimento(res.getDouble("vl_Abastecimento"));
				pop.setNr_Kilometragem(res.getDouble("nr_Kilometragem"));
				pop.setNr_Odometro(res.getDouble("nr_Odometro"));
				pop.setNr_Documento(res.getString("nr_Documento"));
    			pop.setNr_Apontador_Ctf(res.getInt("nr_Apontador_Ctf"));
    			pop.setNr_Numabast_Ctf(res.getInt("nr_Numabast_Ctf"));
    			pop.setDt_Debito_Ctf(FormataData.formataDataBT(res.getDate("dt_Debito_Ctf")));
    			pop.setNr_Kilometragem_Percurso_Media(res.getDouble("nr_Kilometragem_Percurso_Media"));
    			pop.setNr_Quantidade_Media(res.getDouble("nr_Quantidade_Media"));
    			pop.setVl_Abastecimento_Media(res.getDouble("vl_Abastecimento_Media"));
    			pop.setDm_Stamp(res.getString("dm_Stamp"));
    			pop.setDt_Stamp(FormataData.formataDataBT(res.getDate("dt_Stamp")));
    			pop.setUsuario_Stamp(res.getString("usuario_Stamp"));
    		}	
    		return pop;
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getAbastecimentoAnterior()");
    	}
    }

    /**
     * Retorna um ed com o abastecimento imediatamente seguinte a data, km e oid 
     * @param ed
     * @return
     * @throws Excecoes
     */
    public AbastecimentoED getProximoAbastecimentoCompleto (AbastecimentoED ed) throws Excecoes {
    	AbastecimentoED pop = new AbastecimentoED ();
    	String sql = null;
    	try {
    		sql = " select " +	
    		" * " +
    		" FROM " +
    		" Abastecimentos as a " +
    		" where " +
    		" a.oid_Empresa = " + ed.getOid_Empresa() +
			" and a.oid_Veiculo = '" + ed.getOid_Veiculo () + "'" +
			" and a.dt_Abastecimento >= '" + ed.getDt_Abastecimento() + "'" +
			" and a.oid_Abastecimento <> " + ed.getOid_Abastecimento() +
			" and a.dm_Completo = 'C' "  +
    		" order by " +
    		" a.dt_Abastecimento asc, " +
    		" a.nr_Kilometragem asc " +
    		" limit 1 ";
    		ResultSet res = this.executasql.executarConsulta (sql);
    		if (res.next ()) {
				pop.setOid_Abastecimento(res.getInt("oid_Abastecimento"));
				pop.setOid_Empresa(res.getInt("oid_Empresa"));
				pop.setOid_Veiculo(res.getString("oid_Veiculo"));
				pop.setOid_Bomba(res.getInt("oid_Bomba"));
				pop.setOid_Motorista(res.getString("oid_Motorista"));
				pop.setOid_Fornecedor(res.getString("oid_Fornecedor"));
				pop.setDm_Completo(res.getString("dm_Completo"));
				pop.setDm_Tipo_Abastec(res.getString("dm_Tipo_Abastec"));
				pop.setDt_Abastecimento(FormataData.formataDataBT(res.getDate("dt_Abastecimento")));
				pop.setNr_Kilometragem_Percurso(res.getDouble("nr_Kilometragem_Percurso"));
				pop.setNr_Quantidade(res.getDouble("nr_Quantidade"));
				pop.setVl_Abastecimento(res.getDouble("vl_Abastecimento"));
				pop.setNr_Kilometragem(res.getDouble("nr_Kilometragem"));
				pop.setNr_Odometro(res.getDouble("nr_Odometro"));
				pop.setNr_Documento(res.getString("nr_Documento"));
    			pop.setNr_Apontador_Ctf(res.getInt("nr_Apontador_Ctf"));
    			pop.setNr_Numabast_Ctf(res.getInt("nr_Numabast_Ctf"));
    			pop.setDt_Debito_Ctf(FormataData.formataDataBT(res.getDate("dt_Debito_Ctf")));
    			pop.setNr_Kilometragem_Percurso_Media(res.getDouble("nr_Kilometragem_Percurso_Media"));
    			pop.setNr_Quantidade_Media(res.getDouble("nr_Quantidade_Media"));
    			pop.setVl_Abastecimento_Media(res.getDouble("vl_Abastecimento_Media"));
    			pop.setDm_Stamp(res.getString("dm_Stamp"));
    			pop.setDt_Stamp(FormataData.formataDataBT(res.getDate("dt_Stamp")));
    			pop.setUsuario_Stamp(res.getString("usuario_Stamp"));
    		}	
    		return pop;
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getProximoAbastecimentoCompleto()");
    	}
    }

    /**
     * Atualiza a km do percurso e a km do percurso para o calculo da m�dia
     * @param ed
     * @return
     * @throws Excecoes
     */
    public void ajustaKilometragemPercurso (AbastecimentoED ed) throws Excecoes {
    	String sql = null;
    	try {
    		sql = " update" +	
    		" Abastecimentos" +
    		" set " +
    		" oid_Abastecimento = oid_Abastecimento " ;
    	
		if (ed.getNr_Kilometragem_Percurso()>0)
			sql+=",nr_Kilometragem_Percurso = " + ed.getNr_Kilometragem_Percurso(); 
		if (ed.getNr_Kilometragem_Percurso_Media()>0)
			sql+=",nr_Kilometragem_Percurso_Media = " + ed.getNr_Kilometragem_Percurso_Media();
		if (ed.getNr_Quantidade_Media()>0)
			sql+=",nr_Quantidade_Media = " + ed.getNr_Quantidade_Media();
		if (ed.getVl_Abastecimento_Media()>0)
			sql+=",vl_Abastecimento_Media = " + ed.getVl_Abastecimento_Media();
		
    		sql +=" where" +
			" oid_Abastecimento = " + ed.getOid_Abastecimento();
    		this.executasql.executarUpdate(sql);
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "ajustaKilometragemPercurso()");
    	}
    }
    
    /**
     * Retorna o o somatorio dos percursos, valores, e litros dos abastecimentos parciais de um completo
     * @param ed
     * @return
     * @throws Excecoes
     */
    public AbastecimentoED somaParcial (AbastecimentoED ed) throws Excecoes {
    	double nrPerc=0, nrQtde=0, vlAbas=0;
    	String sql = null;
    	try {
    		sql = " select " +
    		" dt_Abastecimento " +
    		",dm_Completo " +
    		",nr_kilometragem_percurso " +
    		",nr_quantidade " +
    		",vl_abastecimento " +
    		" FROM " +
    		" Abastecimentos as a " +
    		" where " +
    		" a.oid_Empresa = " + ed.getOid_Empresa() +
			" and a.oid_Veiculo = '" + ed.getOid_Veiculo () + "' " +
			" and a.dt_Abastecimento <= '" + ed.getDt_Abastecimento() + "' " +
			" and a.nr_kilometragem < " + ed.getNr_Kilometragem() +
			" order by " +
    		" a.dt_Abastecimento desc, " +
    		" a.nr_Kilometragem desc " +
    		" limit 50 ";
    		ResultSet res = this.executasql.executarConsulta (sql);
    		while (res.next ()) {
    			if ("C".equals(res.getString("dm_Completo"))) { 
    				break;
    			} else {
	    			nrPerc+=res.getDouble("nr_Kilometragem_Percurso");
	    			nrQtde+=res.getDouble("nr_Quantidade");
	    			vlAbas+=res.getDouble("vl_Abastecimento");
    			}
    		}	
    		AbastecimentoED abaED = new AbastecimentoED();
    		abaED.setNr_Kilometragem_Percurso(nrPerc);
    		abaED.setNr_Quantidade(nrQtde);
    		abaED.setVl_Abastecimento(vlAbas);
    		return abaED;
    	}
    	catch (Exception exc) {
    		throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "somaParcial()");
    	}
    }
    
    
}