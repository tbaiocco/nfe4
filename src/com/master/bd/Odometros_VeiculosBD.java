package com.master.bd;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Odometros_VeiculosED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Data;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Odometros_VeiculosBD {

  private ExecutaSQL executasql;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public Odometros_VeiculosBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui(String oid_Veiculo, String oid_Carreta, String nm_origem_odometro, long oid_Usuario, long km_informada, long km_viagem) throws Excecoes{

		Odometros_VeiculosED ed = new Odometros_VeiculosED();
		ed.setOID_Veiculo(oid_Veiculo);
		ed.setOID_Carreta(oid_Carreta);
		ed.setNM_Origem_Odometro(nm_origem_odometro);
		ed.setOID_Usuario(oid_Usuario);
		ed.setDT_Inclusao(Data.getDataDMY());
		ed.setHR_Inclusao(Data.getHoraHM());
		ed.setKM_Informada(km_informada);
		ed.setKM_Viagem(km_viagem);
		this.inclui(ed);

  }


  public void inclui(Odometros_VeiculosED ed) throws Excecoes{


	Odometros_VeiculosED Odometro_VeiculoED = new Odometros_VeiculosED();
	String  sql ="";
	long nr_kilometragem_atual=0;
	try {
		sql =" SELECT Odometros_Veiculos.oid_Odometro_Veiculo " +
			 " FROM  Odometros_Veiculos " +
			 " WHERE Odometros_Veiculos.oid_Veiculo='" + ed.getOID_Veiculo() + "'" +
			 " AND   Odometros_Veiculos.DT_Inclusao='" + ed.getDT_Inclusao() + "'" +
			 " AND   Odometros_Veiculos.nm_origem_odometro='" + ed.getNM_Origem_Odometro() + "'" +
			 " AND   Odometros_Veiculos.km_informada=" +  ed.getKM_Informada();
		ResultSet rs = executasql.executarConsulta(sql);
		if (!rs.next() && ed.getOID_Usuario() >0 && ed.getKM_Informada()>0) {

		    sql = " SELECT veiculos.nr_kilometragem_atual " +
			      " FROM veiculos" +
			      " WHERE oid_veiculo = '" + ed.getOID_Veiculo() + "'";
			System.out.println(sql);
			rs = this.executasql.executarConsulta (sql);

			if (rs.next ()) {
				nr_kilometragem_atual=rs.getLong("nr_kilometragem_atual");

				sql = "select max(oid_Odometro_Veiculo) as result from odometros_veiculos";
				long valOid = 1;
				rs = executasql.executarConsulta(sql);

				while (rs.next()) {
					valOid = rs.getLong("result");
				}

				valOid++;

		        sql = " INSERT INTO odometros_veiculos ( "
		        		+ "oid_Odometro_Veiculo , "
						+ "oid_veiculo , "
						+ "dt_inclusao , "
						+ "hr_inclusao , "
						+ "oid_usuario , "
						+ "nm_origem_odometro  , "
						+ "km_informada , "
						+ "km_viagem , "
						+ "km_anterior , "
						+ "dm_atualizado, "
						+ "nr_odometro, "
						+ "nr_odometro_anterior) VALUES";

				sql += "(" + valOid + ",'"
						+ ed.getOID_Veiculo() + "','"
						+ ed.getDT_Inclusao() + "','"
						+ ed.getHR_Inclusao() + "',"
						+ ed.getOID_Usuario() + ",'"
						+ ed.getNM_Origem_Odometro() + "',"
						+ ed.getKM_Informada() + ","
						+ ed.getKM_Viagem()+ ","
						+ nr_kilometragem_atual + ","
						+ "'N',"  //  sql += "'A',";
						+ ed.getNR_Odometro() + ","
						+ ed.getNR_Odometro_Anterior()  + ")";


			      executasql.executarUpdate(sql);

			      Odometro_VeiculoED.setOID_Odometro_Veiculo(valOid);

			      boolean altera_odometro = false;

			      if ("veiculo".equals(ed.getNM_Origem_Odometro()) && "S".equals(parametro_FixoED.getDM_Atualiza_Odometro_Veiculo())){
			    	  altera_odometro = true;
			      }
			      if ("acerto".equals(ed.getNM_Origem_Odometro()) && "S".equals(parametro_FixoED.getDM_Atualiza_Odometro_Acerto())){
			    	  altera_odometro = true;
			      }
			      if ("ordem_servico".equals(ed.getNM_Origem_Odometro()) && "S".equals(parametro_FixoED.getDM_Atualiza_Odometro_Ordem_Servico())){
			    	  altera_odometro = true;
			      }
			      if ("pneus".equals(ed.getNM_Origem_Odometro()) && "S".equals(parametro_FixoED.getDM_Atualiza_Odometro_Pneus())){
			    	  altera_odometro = true;
			      }
			      if ("portaria".equals(ed.getNM_Origem_Odometro()) && "S".equals(parametro_FixoED.getDM_Atualiza_Odometro_Portaria())){
			    	  altera_odometro = true;
			      }
			      if ("abastecimento".equals(ed.getNM_Origem_Odometro()) && "S".equals(parametro_FixoED.getDM_Atualiza_Odometro_Abastecimento())){
			    	  altera_odometro = true;
			      }
			      if ("rastreador".equals(ed.getNM_Origem_Odometro()) && "S".equals(parametro_FixoED.getDM_Atualiza_Odometro_Rastreador())){
			    	  altera_odometro = true;
			      }
			      if ("AAA1234".equals(ed.getOID_Veiculo())) {
			    	//  altera_odometro = true;
			      }


			      if (altera_odometro ){

						sql = " UPDATE veiculos" +
							  " SET nr_kilometragem_atual= '" + ed.getKM_Informada() + "'" +
							  "    ,nr_odometro= '" + ed.getKM_Informada() + "'" +
							  " WHERE oid_veiculo = '" + ed.getOID_Veiculo() + "'";

						executasql.executarUpdate (sql);


						sql = " UPDATE odometros_veiculos";
							if (nr_kilometragem_atual>0)
								sql += " SET dm_atualizado= 'S'";
							else {
								sql += " SET dm_atualizado= 'S *'";
							}
							sql+=" WHERE oid_Odometro_Veiculo = '" +valOid+ "'";
						executasql.executarUpdate (sql);


						String oid_Carreta="";

						if (ed.getOID_Carreta() != null && ed.getOID_Carreta().length()>4) {
							oid_Carreta=ed.getOID_Carreta();
						}else {
							sql =" SELECT Conjuntos_Veiculos.placa_carreta FROM Conjuntos_Veiculos WHERE oid_veiculo = '" + ed.getOID_Veiculo() + "'";
						    System.out.println("--ed.getOID_Carreta()-->" + sql);
							ResultSet res = this.executasql.executarConsulta (sql);
							if (res.next ()) {
								oid_Carreta=res.getString("placa_carreta");
							}
						}

						if (oid_Carreta != null && oid_Carreta.length()>4) {

						    sql = 	" SELECT veiculos.nr_kilometragem_atual " +
						      		" FROM veiculos" +
						      		" WHERE oid_veiculo = '" + oid_Carreta+ "'";
							rs = this.executasql.executarConsulta (sql);

							if (rs.next ()) {

								//SETA DIRETO A KM DO CAV NA CARRETA
								sql = " UPDATE veiculos" +
								  	  " SET nr_kilometragem_atual= '" + ed.getKM_Informada() + "'" +
								  	  "    ,nr_odometro= '" + ed.getKM_Informada() + "'" +
								  	  " WHERE oid_veiculo = '" + oid_Carreta + "'";

								executasql.executarUpdate (sql);

								//SOMA A DIF
								long km_somar=ed.getKM_Informada()-nr_kilometragem_atual;
								if (ed.getKM_Viagem()>0) km_somar=ed.getKM_Viagem();
								if (1==2 && km_somar>0) {
									// Soma a km rodada na carreta...
									sql = "UPDATE Veiculos " +
										  " SET " +
										  " nr_Kilometragem_Atual = (nr_Kilometragem_Atual + "  + km_somar + ") " +
										  ",nr_Odometro = (nr_Odometro + " + km_somar + ") " +
										  " WHERE oid_Veiculo = '" + oid_Carreta + "'";
										  executasql.executarUpdate (sql);
								}


						        sql = " INSERT INTO odometros_veiculos ( "
					        		+ "oid_Odometro_Veiculo , "
									+ "oid_veiculo , "
									+ "dt_inclusao , "
									+ "hr_inclusao , "
									+ "oid_usuario , "
									+ "nm_origem_odometro  , "
									+ "km_informada , "
									+ "km_viagem , "
									+ "km_anterior , "
									+ "dm_atualizado, "
									+ "nr_odometro, "
									+ "nr_odometro_anterior) VALUES";

						        sql += "(" + valOid++ + ",'"
									+ oid_Carreta + "','"
									+ ed.getDT_Inclusao() + "','"
									+ ed.getHR_Inclusao() + "',"
									+ ed.getOID_Usuario() + ",'"
									+ ed.getNM_Origem_Odometro() + "/" + ed.getOID_Veiculo()+ "',"
									+ ed.getKM_Informada() + ","
									+ ed.getKM_Viagem()+ ","
									+ rs.getLong("nr_kilometragem_atual") + ","
									+ "'S',"  //  sql += "'A',";
									+ ed.getNR_Odometro() + ","
									+ ed.getNR_Odometro_Anterior()  + ")";

						        executasql.executarUpdate(sql);


							}


						}
					}
		      	}
		}



    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

 /* public void altera(Odometros_VeiculosED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "UPDATE " + " odometros_veiculos " + "SET "
			+ " oid_veiculo = " +ed.getOID_Veiculo() + ","
			+ " dt_inclusao = '" + ed.getDT_Inclusao() + "', "
			+ " hr_inclusao = '" + ed.getHR_Inclusao() + "', "
			+ " oid_usuario = '" + ed.getOID_Usuario() + "', "
			+ " nm_origem_odometro = '" + ed.getNM_Origem_Odometro() + "', "
			+ " km_informada = '" + ed.getKM_Informada() + "', "
			+ " km_anterior = '" + ed.getKM_Anterior() + "', "
			+ " nr_odometro = '" + ed.getNR_Odometro() + "', "
			+ " nr_odometro_anterior = '" + ed.getNR_Odometro_Anterior() + "'";

	sql += " where oid_veiculo = '" + ed.getOID_Veiculo() + "'";

      executasql.executarUpdate(sql);
    }


    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }*/

  public void deleta(Odometros_VeiculosED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from odometros_veiculos WHERE oid_veiculo = ";
      sql += "('" + ed.getOID_Veiculo() + "')";

      executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Odometros_VeiculosED ed) throws Excecoes{
  	ArrayList list = new ArrayList();


	String sql =
		" SELECT * " +
		" FROM odometros_veiculos " +
		" WHERE oid_veiculo = '" + ed.getOID_Veiculo() + "'";

	if (JavaUtil.doValida (ed.getOID_Veiculo())) {
		sql += " AND odometros_veiculos.oid_veiculo='" + ed.getOID_Veiculo()+"'";
	}
	if (JavaUtil.doValida (ed.getDT_Inclusao())) {
		sql += " AND odometros_veiculos.dt_inclusao   >='" + ed.getDT_Inclusao()+"'";
	}
	if (JavaUtil.doValida (ed.getHR_Inclusao())) {
		sql += " AND odometros_veiculos.hr_inclusao  <='" + ed.getHR_Inclusao()+"'";
	}
	if (JavaUtil.doValida (ed.getNM_Origem_Odometro())) {
		sql += " AND odometros_veiculos.nm_origem_odometro='" + ed.getNM_Origem_Odometro()+"'";
	}

  	sql += " Order by odometros_veiculos.dt_inclusao desc";

  	ResultSet res = executasql.executarConsulta(sql);
  	try {
		//popula
		while (res.next()){
			Odometros_VeiculosED edVolta = new Odometros_VeiculosED();

			edVolta.setOID_Odometro_Veiculo(res.getShort("oid_Odometro_Veiculo"));
			edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
			FormataDataBean DataFormatada = new FormataDataBean ();
	        DataFormatada.setDT_FormataData (res.getString ("DT_Inclusao"));
	        edVolta.setDT_Inclusao (DataFormatada.getDT_FormataData ());
			edVolta.setHR_Inclusao(res.getString("HR_Inclusao"));
			edVolta.setOID_Usuario(res.getLong("OID_Usuario"));
			edVolta.setNM_Origem_Odometro(res.getString("NM_Origem_Odometro"));
			edVolta.setKM_Informada(res.getLong("KM_Informada"));
			edVolta.setKM_Anterior(res.getLong("KM_Anterior"));
			edVolta.setKM_Viagem(res.getLong("KM_Viagem"));
			edVolta.setNR_Odometro(res.getLong("NR_Odometro"));
			edVolta.setNR_Odometro_Anterior(res.getLong("NR_Odometro_Anterior"));
			edVolta.setDM_Atualizado(res.getString("DM_Atualizado"));

			sql= "Select nm_usuario FROM usuarios WHERE oid_usuario=" + res.getLong("OID_Usuario");

			ResultSet res2 = executasql.executarConsulta(sql);
			if (res2.next()){
				edVolta.setNM_Usuario (res2.getString("NM_Usuario"));
			}

			//edVolta.setDM_Inclusao(res.getString("DM_Inclusao"));

			list.add(edVolta);
		}
		return list;

	} catch (SQLException e) {
		throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Odometro_VeiculoED ed)");
	}

  }


  public Odometros_VeiculosED getByRecord(Odometros_VeiculosED ed) throws Exception{
  	Odometros_VeiculosED edVolta = new Odometros_VeiculosED();

  		String sql =
  			" SELECT *  FROM odometros_veiculos " +
  			" WHERE oid_veiculo = '" + ed.getOID_Veiculo() + "'";


  	ResultSet res = this.executasql.executarConsulta(sql);

  	try {
		while (res.next()){

			edVolta.setOID_Odometro_Veiculo(res.getShort("oid_Odometro_Veiculo"));
			edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
			FormataDataBean DataFormatada = new FormataDataBean ();
	        DataFormatada.setDT_FormataData (res.getString ("DT_Inclusao"));
	        edVolta.setDT_Inclusao (DataFormatada.getDT_FormataData ());
			edVolta.setHR_Inclusao(res.getString("HR_Inclusao"));
			edVolta.setOID_Usuario(res.getLong("OID_Usuario"));
			edVolta.setNM_Origem_Odometro(res.getString("NM_Origem_Odometro"));
			edVolta.setKM_Informada(res.getLong("KM_Informada"));
			edVolta.setKM_Anterior(res.getLong("KM_Anterior"));
			edVolta.setNR_Odometro(res.getLong("NR_Odometro"));
			edVolta.setNR_Odometro_Anterior(res.getLong("NR_Odometro_Anterior"));
			edVolta.setDM_Atualizado(res.getString("DM_Atualizado"));
		}
		return edVolta;
	} catch (SQLException e) {
		throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Odometro_VeiculoED ed)");
	}
  }



}