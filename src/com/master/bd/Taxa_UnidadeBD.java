package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Taxa_UnidadeED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Taxa_UnidadeBD {
	private ExecutaSQL executasql;

	public Taxa_UnidadeBD(ExecutaSQL sql) {
		this.executasql = sql;
	}

	public Taxa_UnidadeED inclui(Taxa_UnidadeED ed) throws Excecoes{
		String sql = null;
		long valOid = 1;
		try{
			ResultSet rs = executasql.executarConsulta("select OID_Taxa_Unidade as result from Taxas_Unidades order by OID_Taxa_Unidade desc limit 1");
			while (rs.next()){
				valOid = rs.getLong("result") + 1;
			}
			ed.setOid_Taxa_Unidade(valOid);

			sql = " insert into Taxas_Unidades " +
				  " (OID_Taxa_Unidade, oid_Unidade, oid_Tipo_Servico, " +
				  "  PE_ISSQN, " +
				  "  dt_Stamp, usuario_stamp, dm_stamp ) values ";
			sql += "(" + ed.getOid_Taxa_Unidade() + "," + ed.getOid_Unidade() + "," + ed.getOid_Tipo_Servico() + "," +
					     ed.getPe_Issqn() + ",'"  + Data.getDataDMY() + "','" + ed.getUser() + "','" + ed.getDm_Stamp()+ "')";

			executasql.executarUpdate(sql);

		}
     	catch(Exception exc){
     		exc.printStackTrace();
     		throw new Excecoes("Erro ao Incluir",this.getClass().getName(),"inclui");
     	}
     	return ed;
	}

	public void altera(Taxa_UnidadeED ed) throws Excecoes{
		String sql = null;
		try{
			sql =  " update Taxas_Unidades set oid_Taxa_Unidade = " + ed.getOid_Taxa_Unidade();
			sql += ",oid_unidade = " + ed.getOid_Unidade();
			sql += ",oid_tipo_servico = " + ed.getOid_Tipo_Servico();
			sql += ",pe_issqn = " + ed.getPe_Issqn();
			sql += ",dt_stamp = '" + Data.getDataDMY() + "'";
			sql += ",usuario_stamp = '" + ed.getUser() + "'";
			sql += " where oid_Taxa_Unidade = " + ed.getOid_Taxa_Unidade();
			executasql.executarUpdate(sql);
		}
		catch(Exception exc){
			exc.printStackTrace();
     		throw new Excecoes("Erro ao Alterar",this.getClass().getName(),"altera");
     	}
	}

	public void deleta(Taxa_UnidadeED ed) throws Excecoes{
		String sql = null;
		try{
			sql =  "delete from Taxas_Unidades WHERE oid_Taxa_Unidade = ";
			sql += ed.getOid_Taxa_Unidade();
			executasql.executarUpdate(sql);
		}
		catch(Exception exc){
			exc.printStackTrace();
     		throw new Excecoes("Erro ao Excluir",this.getClass().getName(),"deleta");
     	}
	}

	public ArrayList lista(Taxa_UnidadeED ed)throws Excecoes{
		String sql = null;
    	ArrayList list = new ArrayList();
    	try{
    		sql =  " SELECT Taxas_Unidades.*, " +
    			   " Unidades.cd_unidade, Pessoas.NM_Fantasia, " +
    			   " Tipos_Servicos.cd_tipo_servico, Tipos_Servicos.nm_tipo_servico" +
    			   " from Taxas_Unidades, Unidades, Pessoas, Tipos_Servicos ";
    		sql += " WHERE Taxas_Unidades.oid_unidade = Unidades.oid_unidade ";
    		sql += " AND   Unidades.oid_pessoa = Pessoas.oid_Pessoa ";
    		sql += " AND   Taxas_Unidades.oid_tipo_Servico = Tipos_Servicos.oid_tipo_servico ";
    		if(JavaUtil.doValida(String.valueOf(ed.getOid_Unidade())))
    			sql += " AND   Taxas_Unidades.OID_Unidade = " + ed.getOid_Unidade();
    		if(JavaUtil.doValida(String.valueOf(ed.getOid_Tipo_Servico())))
    			sql += " AND   Taxas_Unidades.OID_Tipo_Servico = " + ed.getOid_Tipo_Servico();
    		if(JavaUtil.doValida(String.valueOf(ed.getOid_Taxa_Unidade())))
    			sql += " AND   Taxas_Unidades.OID_Taxa_Unidade = " + ed.getOid_Taxa_Unidade();
    		sql += " Order by Unidades.cd_unidade, Tipos_Servicos.cd_tipo_servico, Taxas_Unidades.OID_Taxa_Unidade ";

    		ResultSet res = null;
    		res = this.executasql.executarConsulta(sql);
    		while (res.next()){
    			Taxa_UnidadeED edVolta = new Taxa_UnidadeED();
    			edVolta.setOid_Taxa_Unidade(res.getLong("OID_Taxa_Unidade"));
    			edVolta.setOid_Unidade(res.getLong("OID_Unidade"));
    			edVolta.setCd_Unidade(res.getString("cd_Unidade"));
    			edVolta.setNm_Fantasia(res.getString("nm_Fantasia"));
    			edVolta.setOid_Tipo_Servico(res.getLong("OID_Tipo_Servico"));
    			edVolta.setCd_Tipo_Servico(res.getString("cd_tipo_servico"));
    			edVolta.setNm_Tipo_Servico(res.getString("nm_tipo_servico"));
    			edVolta.setPe_Issqn(res.getDouble("PE_ISSQN"));

    			list.add(edVolta);
    		}
    	}
    	catch(Exception exc){
    		exc.printStackTrace();
     		throw new Excecoes("Erro ao Listar Registros",this.getClass().getName(),"lista");
     	}
    	return list;
	}

  	public Taxa_UnidadeED getByRecord(Taxa_UnidadeED ed)throws Excecoes{
  		Taxa_UnidadeED edVolta = new Taxa_UnidadeED();
  		try{
  			Iterator it = this.lista(ed).iterator();
  			if(it.hasNext())
  				edVolta = (Taxa_UnidadeED)it.next();
  		}
  		catch(Exception exc){
  			exc.printStackTrace();
     		throw new Excecoes("Erro ao Buscar Registro",this.getClass().getName(),"getByRecord");
     	}

  		return edVolta;
  	}


}
