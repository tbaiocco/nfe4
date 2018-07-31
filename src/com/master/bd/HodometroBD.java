package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.HodometroED;
import com.master.ed.VeiculoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.FormataValor;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Dimensões de pneus
 * @serialData 06/2007
 */
public class HodometroBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public HodometroBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public HodometroED inclui(HodometroED ed) throws Excecoes {
		try {
			ed.setOid_Odometro(getAutoIncremento("oid_Odometro", "Odometros"));
			sql = "INSERT INTO Odometros (" +
			"oid_Odometro" +
			",oid_empresa" +
			",oid_veiculo" +
			",nr_odometro_retirado" +
			",nr_odometro_colocado" +
			",nr_odometro_maximo" +
			",dt_odometro_troca" +
			",nr_km_acum_troca" +
			",nr_odometro_anterior" +
			",nr_km_acum_anterior" +
			",nr_odometro_maximo_anterior" +
			",dm_tipo_troca" +
			",dm_Stamp " +
		  	",dt_Stamp " +
		  	",usuario_Stamp "+
			") " +
			" VALUES (" +
			ed.getOid_Odometro() + 
			"," + ed.getOid_Empresa() +
			",'" + ed.getOid_Veiculo() + "'" +
			"," + ed.getNr_Odometro_Retirado() + 
			"," + ed.getNr_Odometro_Colocado() + 
			"," + ed.getNr_Odometro_Maximo() +
			",'" + ed.getDt_Odometro_Troca() + "'" +
			"," + ed.getNr_Km_Acum_Troca() +
			"," + ed.getNr_Odometro_Anterior() +
			"," + ed.getNr_Km_Acum_Anterior() +
			"," + ed.getNr_Odometro_Maximo_Anterior() +
			",'" + ed.getDm_Tipo_Troca() + "'" +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(HodometroED ed)");
		}
	}


	public void deleta(HodometroED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Odometros " +
			"WHERE " +
			"oid_Odometro = " + ed.getOid_Odometro();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(HodometroED ed)");
		}
	}

	public ArrayList lista(HodometroED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
			",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Odometros " +
			"WHERE " +
			"oid_Veiculo = '" + ed.getOid_Veiculo() + "' "; 
			if (doValida(ed.getDt_Odometro_Troca())) {
				sql += "and dt_Odometro_Troca > '" + ed.getDt_Odometro_Troca() + "' ";
			}
			sql += "ORDER BY " +
			"Odometros.dt_Odometro_troca";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	//Procura na lista de hodometros comprando a data da troca passada no parametro cmp
	public ArrayList lista(HodometroED ed,String cmp) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
			",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Odometros " +
			"WHERE " +
			"oid_Veiculo = '" + ed.getOid_Veiculo() + "' "; 
			if ( doValida(cmp) && doValida(ed.getDt_Odometro_Troca()) )
				sql +=" and dt_Odometro_Troca " + cmp + " '" + ed.getDt_Odometro_Troca() + "' ";  
			sql += "ORDER BY " +
			"Odometros.dt_Odometro_troca";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public HodometroED getByRecord(HodometroED ed) throws Excecoes {

		HodometroED edQBR = new HodometroED();
		try {
			sql = "SELECT * " +
			",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM Odometros " +
			"WHERE " +
			"oid_Odometro = " + ed.getOid_Odometro() ; 
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(HodometroED ed)");
		}
		return edQBR;
	}

	private HodometroED populaRegistro(ResultSet res) throws SQLException {
		HodometroED ed = new HodometroED();
		ed.setOid_Odometro(res.getInt("oid_Odometro"));
		ed.setOid_Veiculo(res.getString("oid_Veiculo"));
		ed.setNr_Odometro_Retirado(res.getDouble("nr_Odometro_Retirado")); 
		ed.setNr_Odometro_Colocado(res.getDouble("nr_Odometro_Colocado"));
		ed.setNr_Odometro_Maximo(res.getInt("nr_Odometro_Maximo"));
		ed.setDt_Odometro_Troca(FormataData.formataDataBT(res.getString("dt_Odometro_Troca")));
		ed.setNr_Km_Acum_Troca(res.getDouble("nr_Km_Acum_Troca"));
		ed.setNr_Odometro_Anterior(res.getDouble("nr_Odometro_Anterior"));
		ed.setNr_Km_Acum_Anterior(res.getDouble("nr_Km_Acum_Anterior"));
		ed.setNr_Odometro_Maximo_Anterior(res.getInt("nr_Odometro_Maximo_Anterior"));
		ed.setDm_Tipo_Troca(res.getString("dm_Tipo_Troca"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp( res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
	
	// Calcula a km acumulada - Se houver uma virada de hodometro registra ela.
    public HodometroED getKmAcumulada (HodometroED ed)  throws Excecoes  {
    	HodometroED odoED = new HodometroED();
    	double wKmAcum = 0 , wOdo = 0 ;
    	double wKmInicial = 0 ,wKmFinal = 0; 
    	double wKmAcumTroca = 0;
    	String dt_Troca_Hodometro=null;
    	String queOdo="ATUAL";
    	ArrayList lstOdo1 = new ArrayList();
    	ArrayList lstOdo2 = new ArrayList();
    	ArrayList lstOdo3 = new ArrayList();
    	try {
    		//Acessa o veiculo para pegar hodometro atual e km acumulada atual e o hodometro máximo
	    	VeiculoED veiED = new VeiculoED();
	    	veiED.setOid_Veiculo(ed.getOid_Veiculo());
	    	veiED = new VeiculoBD(this.executasql).getByRecord(veiED);
	    	// Guarda km e atual hodometro do veiculo e hodometro máximo
	    	wKmAcum = veiED.getNr_Kilometragem_Atual();
	    	wOdo = veiED.getNr_Odometro();
	    	wKmFinal = veiED.getDm_Odometro_Maximo();
	    	//Pega os registro de troca de hodometro para ao veiculo
	    	odoED.setOid_Veiculo(ed.getOid_Veiculo());
	    	lstOdo1 = this.lista(odoED);
	    	// Se o veiculo teve alguma troca de hodometro...
	    	if (lstOdo1.size() > 0) {
	    		queOdo="ANTERIOR";
	    		// Busca uma troca de hodometro superior a data da troca do pneu
	    		odoED.setDt_Odometro_Troca(ed.getDt_Odometro_Troca());
	    		lstOdo2 = this.lista(odoED,">");
				if (lstOdo2.size() > 0) {
		    		// Pega o hodometro correspondente a data da troca anteriores
					odoED.setDt_Odometro_Troca(ed.getDt_Odometro_Troca());
		    		lstOdo3 = this.lista(odoED,"<=");
		    		if (lstOdo3.size() > 0) {
			    		odoED = (HodometroED) lstOdo3.get(lstOdo3.size()-1); // Pega o último registro da lista
			    		wKmInicial = odoED.getNr_Odometro_Colocado(); // Pega o odometro para os limites
			    		wOdo = odoED.getNr_Odometro_Colocado(); // Pega o odometro do veiculo na troca do hodometro
			    		wKmAcum = odoED.getNr_Km_Acum_Troca(); // Pega a km acumulada do veiculo na troca do hodometro
			    		dt_Troca_Hodometro = odoED.getDt_Odometro_Troca();
			    		odoED = (HodometroED) lstOdo2.get(0); // Pega o primeiro > que a dt troca
			    		wKmFinal = odoED.getNr_Odometro_Retirado(); // O final é o odometro retirado do registro > que a dt troca 
		    		} else {
		    			odoED = (HodometroED) lstOdo1.get(0); // Pega o primeiro registro da lista
		    			if (lstOdo1.size() > 1) {
		    				wKmInicial = odoED.getNr_Odometro_Anterior();
		    				wKmAcum = odoED.getNr_Km_Acum_Troca();
		    			} else {
		    				wKmInicial = 0; 						// Assume que se é a primeira troca então é zero
		    				wKmAcum = ed.getNr_Km_Acum_Anterior();  // Pega a km acumulada anterior
		    			}
		    			wKmFinal = odoED.getNr_Odometro_Retirado();
		    			wOdo = odoED.getNr_Odometro_Retirado();
		    			
		    			dt_Troca_Hodometro = odoED.getDt_Odometro_Troca();
		    		}
				} else {
					queOdo="ATUAL";
					odoED = (HodometroED) lstOdo1.get(lstOdo1.size()-1);
					wKmInicial=odoED.getNr_Odometro_Colocado();
					dt_Troca_Hodometro = odoED.getDt_Odometro_Troca();
				}	
	    	}
	    	// Testa para ver se o hodometro informado para a troca do pneu está nos limites que foram estabelecidos pelas trocas de hodometros
	    	if ( ed.getNr_Odometro_Colocado() <  wKmInicial || ed.getNr_Odometro_Colocado() > wKmFinal ) {
	    		String texto = 	"Hodômetro da troca inválido ! " ;
	    			   texto+=	"&lt;br&gt;";
	    			   texto+=	"Hodômetro mínimo = "+ FormataValor.formataValorBT(wKmInicial,1);
	    			   texto+=	"&lt;br&gt;";
	    			   texto+=	"Hodômetro máximo = "+ FormataValor.formataValorBT(wKmFinal,1);
    			if (doValida(dt_Troca_Hodometro)) 
    				texto+=	"&lt;br&gt;";
    				texto+=  "Troca de hodômetro efetuada em " + dt_Troca_Hodometro + ".";
	    		odoED.setDm_Retorno(texto);
	    		return odoED;
    		} else {
    			// Calcula a km acumulada 
    	    	// A km acumulada é o seguinte: hodometro informado - hodometro do carro + km acumulada do carro.
    	    	if (ed.getNr_Odometro_Colocado() > wOdo) {
    	    		wKmAcumTroca = wKmAcum + ( ed.getNr_Odometro_Colocado() - wOdo );
    	    	} else	
    	    	if (ed.getNr_Odometro_Colocado() < wOdo) {
    	    		if (wKmAcum > 0 )
    	    			wKmAcumTroca = wKmAcum - ( wOdo - ed.getNr_Odometro_Colocado() );
    	    		else
    	    			wKmAcumTroca = ed.getNr_Odometro_Colocado() ;
    	    	} else {
    	    		wKmAcumTroca = wKmAcum;
    	    	}
    	    	
    	    	// Virada do hodômetro
    	    	// Só faz o teste da virada se for hodometro atual do carro ou o ultimo hodometro trocado
    			if ("ATUAL".equals(queOdo)) { // Se o hodometro é o atual  
		    		//Faz teste da virada
		    		if (ed.getNr_Odometro_Colocado() < wOdo) {
		    			// Testa virada ou retroativo - Vai fazer a pergunta lá no laszlo
		    			if ( "V".equals(ed.getDm_Virada()) || "SV".equals(ed.getDm_Virada()) ) {
		    		    	//wKmAcum = veiED.getNr_Kilometragem_Atual();
		    		    	//wOdo = veiED.getNr_Odometro();
		    		    	//wKmFinal = veiED.getDm_Odometro_Maximo();
		    				wKmAcumTroca = wKmAcum + ( veiED.getDm_Odometro_Maximo() + 1 + ed.getNr_Odometro_Colocado() ) - veiED.getNr_Kilometragem_Atual();
		    				// Inclui a virada do hodometro
		    				HodometroED viraED = new HodometroED();
		    				viraED.setOid_Empresa(ed.getOid_Empresa());
		    				viraED.setOid_Veiculo(ed.getOid_Veiculo());
		    				viraED.setNr_Odometro_Retirado(veiED.getNr_Odometro());
		    				viraED.setNr_Odometro_Colocado(ed.getNr_Odometro_Colocado()); // Hodometro da troca o colocado
		    				viraED.setNr_Odometro_Maximo(veiED.getDm_Odometro_Maximo()); 
		    				viraED.setDt_Odometro_Troca(ed.getDt_Odometro_Troca()); 
		    				viraED.setNr_Km_Acum_Troca(wKmAcumTroca); // Km acumulada da troca 
		    				viraED.setNr_Odometro_Anterior(veiED.getNr_Odometro()); 
		    				viraED.setNr_Km_Acum_Anterior(veiED.getNr_Kilometragem_Atual()); 
		    				viraED.setNr_Odometro_Maximo_Anterior(veiED.getDm_Odometro_Maximo()); 
		    				viraED.setDm_Tipo_Troca("A");
		    				if ( "SV".equals(ed.getDm_Virada()) ){}else{ 
		    					this.inclui(viraED);
		    				}
		    			} else
	    				if ( !"R".equals(ed.getDm_Virada()) ) {
	    					odoED.setDm_Retorno("VIRADA"); // Informa ao OL que deve confirmar a virada ou lancamento retroativo
	    					return odoED;
	    				} else odoED.setDm_Virada("R"); // Marca como Retroativo para não atualizar hodometro/km acumulada do veiculo
		    		}
		    	} else { // Se o hodometro é anterior
		    		odoED.setDm_Virada("R"); // Marca como Retroativo para não atualizar hodometro/km acumulada do veiculo
		    	}
    			// Retorna a km acumulada calculada 
    			odoED.setNr_Km_Acum_Troca(wKmAcumTroca);
    		}
	    	
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(HodometroED ed)");
		}
    	return odoED;
    }
    
}