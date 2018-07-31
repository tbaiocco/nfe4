package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.master.ed.PatrimonioED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class PatrimonioBD extends BancoUtil{

  private ExecutaSQL executasql;

  public PatrimonioBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public PatrimonioED inclui(PatrimonioED ed) throws Excecoes{

    String sql = null;
    long valOid = 1;

    PatrimonioED PatrimonioED = new PatrimonioED();

    try{
      ResultSet rs = executasql.executarConsulta("select OID_Patrimonio as result from Patrimonios order by OID_Patrimonio desc limit 1");
      while (rs.next()){
        valOid = rs.getLong("result") + 1;
      }
      ed.setOid_patrimonio(valOid);

      sql = " insert into Patrimonios (" +
      		" oid_patrimonio, " +
			" oid_unidade, " +
			" oid_centro_custo, " +
			" dt_stamp, " +
			" usuario_stamp, " +
			" dm_stamp, " +
			" cd_patrimonio, " +
			" tx_descricao, " +
			" dt_compra, " +
			" vl_original, " +
			" vl_depreciacao, " +
			" nr_quantidade_meses, " +
			" vl_depreciado, " +
			" dt_baixa, " +
			" dt_venda_sucateamento, " +
			" vl_venda_sucateamento " +
      		" ) values ( ";
      sql += ed.getOid_patrimonio() + "," +
      		 ed.getOid_unidade() + "," +
      		 ed.getOid_centro_custo() + "," +
      		 getSQLDate(Data.getDataDMY()) + "," +
      		 getSQLString(ed.getUsuario_Stamp()) + "," +
      		 getSQLString("S") + "," +
      		 getSQLString(ed.getCd_patrimonio()) + "," +
      		 getSQLString(ed.getTx_descricao()) + "," +
      		 getSQLDate(ed.getDt_compra()) + "," +
      		 ed.getVl_original() + "," +
      		 ed.getVl_depreciacao() + "," +
      		 ed.getNr_quantidade_meses() + "," +
      		 ed.getVl_depreciado() + "," +
      		 getSQLDate(ed.getDt_baixa()) + "," +
      		 getSQLDate(ed.getDt_venda_sucateamento()) + "," +
      		 ed.getVl_venda_sucateamento() +
      		 ")";

      executasql.executarUpdate(sql);

      PatrimonioED.setOid_patrimonio(ed.getOid_patrimonio());

    }
    catch(Exception exc){
    	throw new Excecoes("Erro ao incluir", this.getClass().getName(), "inclui");
    }
    return PatrimonioED;
  }

  public void altera(PatrimonioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Patrimonios set ";
      sql +="  dt_stamp=" + getSQLDate(Data.getDataDMY());
      sql +=", usuario_stamp=" + getSQLString(ed.getUsuario_Stamp());
      sql +=", dm_stamp=" + getSQLString("A");

      if(JavaUtil.doValida(String.valueOf(ed.getOid_unidade())))
    	  sql +=", oid_unidade = " + ed.getOid_unidade();
      if(JavaUtil.doValida(String.valueOf(ed.getOid_centro_custo())))
    	  sql +=", Oid_centro_custo=" + ed.getOid_centro_custo();

      if(JavaUtil.doValida(String.valueOf(ed.getCd_patrimonio())))
    	  sql +=", Cd_patrimonio=" + getSQLString(ed.getCd_patrimonio());
      if(JavaUtil.doValida(String.valueOf(ed.getTx_descricao())))
    	  sql +=", Tx_descricao=" + getSQLString(ed.getTx_descricao());
      if(JavaUtil.doValida(String.valueOf(ed.getDt_compra())))
    	  sql +=", Dt_compra=" + getSQLDate(ed.getDt_compra());
      if(JavaUtil.doValida(String.valueOf(ed.getVl_original())))
    	  sql +=", Vl_original=" + ed.getVl_original();
      if(JavaUtil.doValida(String.valueOf(ed.getVl_depreciacao())))
    	  sql +=", Vl_depreciacao=" + ed.getVl_depreciacao();
      if(JavaUtil.doValida(String.valueOf(ed.getNr_quantidade_meses())))
    	  sql +=", Nr_quantidade_meses=" + ed.getNr_quantidade_meses();
      if(JavaUtil.doValida(String.valueOf(ed.getVl_depreciado())))
    	  sql +=", Vl_depreciado=" + ed.getVl_depreciado();
      if(JavaUtil.doValida(String.valueOf(ed.getDt_baixa())))
    	  sql +=", Dt_baixa=" + getSQLDate(ed.getDt_baixa());
      if(JavaUtil.doValida(String.valueOf(ed.getDt_venda_sucateamento())))
    	  sql +=", Dt_venda_sucateamento=" + getSQLDate(ed.getDt_venda_sucateamento());
      if(JavaUtil.doValida(String.valueOf(ed.getVl_venda_sucateamento())))
    	  sql +=", Vl_venda_sucateamento=" + ed.getVl_venda_sucateamento();

      sql +=" where oid_Patrimonio = " + ed.getOid_patrimonio();

      executasql.executarUpdate(sql);

    }

      catch(Exception exc){
    	  throw new Excecoes("Erro ao alterar", this.getClass().getName(), "altera");
      }
  }

  public void deleta(PatrimonioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Patrimonios WHERE oid_Patrimonio = ";
      sql += "(" + ed.getOid_patrimonio() + ")";

      executasql.executarUpdate(sql);
    }

      catch(Exception exc){
    	  throw new Excecoes("Erro ao excluir", this.getClass().getName(), "deleta");
      }
  }

  public ArrayList lista(PatrimonioED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

    	sql =  " SELECT Patrimonios.*, Unidades.cd_unidade, Pessoas.nm_fantasia ";
    	sql += " from Patrimonios, Unidades, Pessoas ";
        sql += " WHERE Patrimonios.oid_unidade = Unidades.oid_unidade" +
        	   " AND Unidades.oid_pessoa = Pessoas.oid_pessoa ";

        if(JavaUtil.doValida(String.valueOf(ed.getOid_patrimonio())))
        	sql += " AND Patrimonios.OID_Patrimonio = " + ed.getOid_patrimonio();
        if(JavaUtil.doValida(String.valueOf(ed.getOid_unidade())))
        	sql += " AND Patrimonios.OID_Unidade = " + ed.getOid_unidade();
        if(JavaUtil.doValida(String.valueOf(ed.getOid_centro_custo())))
        	sql += " AND Patrimonios.OID_centro_custo = " + ed.getOid_centro_custo();

        if(JavaUtil.doValida(String.valueOf(ed.getCd_patrimonio())))
        	sql += " AND Patrimonios.cd_patrimonio LIKE " + getSQLString(ed.getCd_patrimonio() + "%");
        if(JavaUtil.doValida(String.valueOf(ed.getTx_descricao())))
        	sql += " AND Patrimonios.Tx_descricao LIKE " + getSQLString("%" + ed.getTx_descricao() + "%");

        if(JavaUtil.doValida(String.valueOf(ed.getDt_compra())))
        	sql += " AND Patrimonios.Dt_compra >= " + getSQLDate(ed.getDt_compra());
        if(JavaUtil.doValida(String.valueOf(ed.getDt_compra_final())))
        	sql += " AND Patrimonios.Dt_compra <= " + getSQLDate(ed.getDt_compra_final());

        if(JavaUtil.doValida(String.valueOf(ed.getDt_baixa())))
        	sql += " AND Patrimonios.Dt_baixa >= " + getSQLDate(ed.getDt_baixa());
        if(JavaUtil.doValida(String.valueOf(ed.getDt_baixa_final())))
        	sql += " AND Patrimonios.Dt_baixa <= " + getSQLDate(ed.getDt_baixa_final());

        if(JavaUtil.doValida(String.valueOf(ed.getDt_venda_sucateamento())))
        	sql += " AND Patrimonios.Dt_venda_sucateamento >= " + getSQLDate(ed.getDt_venda_sucateamento());
        if(JavaUtil.doValida(String.valueOf(ed.getDt_venda_sucateamento_final())))
        	sql += " AND Patrimonios.Dt_venda_sucateamento <= " + getSQLDate(ed.getDt_venda_sucateamento_final());

        sql += " Order by Unidades.cd_unidade, Patrimonios.cd_patrimonio ";

        ResultSet res = null;
      	res = this.executasql.executarConsulta(sql);

      	FormataDataBean DataFormatada = new FormataDataBean();

      	while (res.next()){
      		PatrimonioED edVolta = new PatrimonioED();
      		double meses = 0.0;

      		edVolta.setOid_patrimonio(res.getLong("OID_Patrimonio"));
      		edVolta.setOid_unidade(res.getString("oid_unidade"));
      		edVolta.setCd_unidade(res.getString("cd_unidade"));
      		edVolta.setNm_fantasia(res.getString("nm_fantasia"));

      		edVolta.setOid_centro_custo(res.getInt("oid_centro_custo"));
      		String sqlAux = "SELECT cd_centro_custo, nm_centro_custo from centros_custos where " +
      				        " oid_centro_custo = " + res.getInt("oid_centro_custo");
      		ResultSet rs = this.executasql.executarConsulta(sqlAux);
      		if(rs.next()){
      			edVolta.setCd_centro_custo(rs.getString(1));
      			edVolta.setNm_centro_custo(rs.getString(2));
      		}

      		edVolta.setCd_patrimonio(res.getString("cd_patrimonio"));
      		edVolta.setTx_descricao(res.getString("tx_descricao"));
      		edVolta.setDt_compra(DataFormatada.getDT_FormataData(res.getString("dt_compra")));
      		edVolta.setDt_baixa(DataFormatada.getDT_FormataData(res.getString("dt_baixa")));
      		edVolta.setDt_venda_sucateamento(DataFormatada.getDT_FormataData(res.getString("dt_venda_sucateamento")));

      		edVolta.setVl_original(res.getDouble("vl_original"));
      		edVolta.setVl_depreciacao(res.getDouble("vl_depreciacao"));
      		edVolta.setNr_quantidade_meses(res.getDouble("Nr_quantidade_meses"));
      		edVolta.setVl_depreciado(res.getDouble("vl_depreciado"));

      		edVolta.setVl_venda_sucateamento(res.getDouble("vl_venda_sucateamento"));

      		if(JavaUtil.doValida(res.getString("Nr_quantidade_meses"))){
      			Calendar cal1 = Data.stringToCalendar(edVolta.getDt_compra(), Data.SHORT_DATE);

      			Calendar cal2 = Calendar.getInstance();
      			if(JavaUtil.doValida(edVolta.getDt_venda_sucateamento()))
      				cal2 = Data.stringToCalendar(edVolta.getDt_venda_sucateamento(), Data.SHORT_DATE);
      			else if(JavaUtil.doValida(edVolta.getDt_baixa()))
      				cal2 = Data.stringToCalendar(edVolta.getDt_baixa(), Data.SHORT_DATE);
      			else
      				cal2 = Data.stringToCalendar(Data.getDataDMY(), Data.SHORT_DATE);

      			meses = Data.diferencaMeses(cal1, cal2);

      		}
      		if(!JavaUtil.doValida(res.getString("vl_depreciado"))){
      			double pe_deprec = 0.0;
      			pe_deprec = meses / edVolta.getNr_quantidade_meses();
      			edVolta.setVl_depreciado(edVolta.getVl_depreciacao()*pe_deprec);
      		}


      		list.add(edVolta);
      	}
    }
      catch(Exception exc){
    	  throw new Excecoes("Erro ao listar", this.getClass().getName(), "lista");
      }

    return list;
  }

  public PatrimonioED getByRecord(PatrimonioED ed)throws Excecoes{
	  Iterator it = this.lista(ed).iterator();
    if(it.hasNext())
    	return (PatrimonioED)it.next();
    else
    	return new PatrimonioED();
  }

  public void geraRelatorio(PatrimonioED ed)throws Excecoes{

  }

}
