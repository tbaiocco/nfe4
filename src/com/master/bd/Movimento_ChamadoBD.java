/*
 * Created on 05/07/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.master.bd;


import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Movimento_ChamadoED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Jonas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Movimento_ChamadoBD {

	private ExecutaSQL executasql;

	public Movimento_ChamadoBD(ExecutaSQL sql){
		this.executasql = sql;
	}

	public Movimento_ChamadoED inclui(Movimento_ChamadoED ed) throws Excecoes{

		  	String sql = null;
		  	long valOid = 1;

		    Movimento_ChamadoED Movimento_ChamadoED = new Movimento_ChamadoED();


		    try{
		      ResultSet rs = executasql.executarConsulta("select max(oid_movimento_chamado) as result from movimentos_chamados group by oid_movimento_chamado");
		      while (rs.next()){
		      	valOid = rs.getLong("result");
		      }
		      valOid++;


		      ed.setOID_Movimento_Chamado(Long.toString(valOid));

		      sql = " insert into Movimentos_Chamados (oid_movimento_chamado, oid_chamado, tx_descricao, oid_responsavel, dt_inicio, hr_inicio, dt_fim, hr_fim, dm_tipo_movimento, dt_stamp) values ";
		      sql += "('" + ed.getOID_Movimento_Chamado() + "','" + ed.getOID_Chamado() + "','" + ed.getTX_Descricao() + "','" + ed.getOID_Responsavel() + "',";

		      if(JavaUtil.doValida(ed.getDT_Inicio())){
		      	sql+= "'" + ed.getDT_Inicio() + "','";
		      } else{
		      	sql+= "null,'";
		      }

		      sql+= ed.getHR_Inicio() + "',";

		      if(JavaUtil.doValida(ed.getDT_Fim())){
		      	sql+= "'" + ed.getDT_Fim() + "','";
		      } else{
		      	sql+= "null,'";
		      }

		      sql+= ed.getHR_Fim() +  "',";

		      if(JavaUtil.doValida(ed.getDM_Tipo_Movimento())){
		      	sql+= "'" + ed.getDM_Tipo_Movimento() +"',";
		      }else{
		      	sql+= "null,";
		      }

			  sql+=ed.getDT_Stamp() + ")";




		      // System.out.println("###############BD################");
		      // System.out.println(sql);

		      int i = executasql.executarUpdate(sql);

		      Movimento_ChamadoED.setOID_Movimento_Chamado(ed.getOID_Movimento_Chamado());

		    }
		      catch(Exception exc){
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao incluir Movimento_Chamado");
		        excecoes.setMetodo("inclui");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }
		      return Movimento_ChamadoED;

		  }

		  public Movimento_ChamadoED altera(Movimento_ChamadoED ed) throws Excecoes{

			  	String sql = null;

			    Movimento_ChamadoED Movimento_ChamadoED = new Movimento_ChamadoED();

			    try{

			        sql = " update Movimentos_Chamados set " +
			        	  " oid_responsavel = '" + ed.getOID_Responsavel() + "', " +
			        	  " tx_descricao = '" + ed.getTX_Descricao() + "'" + ", " +
	        	  		  " dt_inicio = " + JavaUtil.getSQLDate(ed.getDT_Inicio()) + ", " +
	        	  		  " hr_inicio = '" + ed.getHR_Inicio() + "'" + ", " +
	        	  		  " dt_fim = " + JavaUtil.getSQLDate(ed.getDT_Fim()) + ", " +
	        	  		  " hr_fim = '" + ed.getHR_Fim() + "'" + ", " +
	        	  		  " dm_tipo_movimento = '" + ed.getDM_Tipo_Movimento() + "'";
			        sql += " where oid_movimento_chamado = '" + ed.getOID_Movimento_Chamado() + "'";

			        int i = executasql.executarUpdate(sql);

			      }

			      catch(Exception exc){
			        Excecoes excecoes = new Excecoes();
			        excecoes.setClasse(this.getClass().getName());
			        excecoes.setMensagem("Erro ao alterar Movimento_Chamado");
			        excecoes.setMetodo("altera");
			        excecoes.setExc(exc);
			        throw excecoes;
			      }
			      return Movimento_ChamadoED;
			  }

		  public void deleta(Movimento_ChamadoED ed) throws Excecoes{

		    String sql = null;

		    try{

		      sql = "delete from Movimentos_Chamados WHERE oid_movimento_chamado = ";
		      sql += "'" + ed.getOID_Movimento_Chamado() + "'";

		      int i = executasql.executarUpdate(sql);
		    }

		      catch(Exception exc){
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao excluir Movimento_Chamado");
		        excecoes.setMetodo("excluir");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }
		  }

		  public ArrayList lista(Movimento_ChamadoED ed)throws Excecoes{

		    String sql = null;
		    ArrayList list = new ArrayList();

		    try{

		    	sql="select * from movimentos_chamados where oid_chamado = '"+ed.getOID_Chamado()+"' ORDER BY oid_movimento_chamado";

		    	// System.out.println(sql);


		      ResultSet res = null;
		      res = this.executasql.executarConsulta(sql);

		      FormataDataBean DataFormatada = new FormataDataBean();

		      while (res.next()){
		        Movimento_ChamadoED edVolta = new Movimento_ChamadoED();

		        if (JavaUtil.doValida(res.getString("oid_movimento_chamado")))
		        	edVolta.setOID_Movimento_Chamado(res.getString("oid_movimento_chamado"));

		        if (JavaUtil.doValida(res.getString("oid_chamado")))
		        	edVolta.setOID_Chamado(Long.parseLong(res.getString("oid_chamado")));

		        if (JavaUtil.doValida(res.getString("oid_responsavel")))
		        	edVolta.setOID_Responsavel(res.getString("oid_responsavel"));

		        if (JavaUtil.doValida(res.getString("dt_fim")))
		        	edVolta.setDT_Fim(res.getString("dt_fim"));

		        if (JavaUtil.doValida(res.getString("DM_Tipo_Movimento")))
		        	edVolta.setDM_Tipo_Movimento(res.getString("DM_Tipo_Movimento"));
		        	//edVolta.setTX_Descricao(res.getString("TX_Descricao"));

		        if (JavaUtil.doValida(res.getString("TX_Descricao")))
		        	edVolta.setTX_Descricao(res.getString("TX_Descricao"));

		        if (JavaUtil.doValida(res.getString("dt_inicio")))
		        	edVolta.setDT_Inicio(res.getString("dt_inicio"));

		        list.add(edVolta);
		      }
		    }
		      catch(Exception exc){
		      	exc.printStackTrace();
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao listar");
		        excecoes.setMetodo("listar");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }

		    return list;
		  }

		  public Movimento_ChamadoED getByRecord(Movimento_ChamadoED ed)throws Excecoes{

		    String sql = null;

		    Movimento_ChamadoED edVolta = new Movimento_ChamadoED();

		    try{

		      sql =  " SELECT * FROM Movimentos_Chamados WHERE";

		      if (String.valueOf(ed.getOID_Movimento_Chamado()) != null &&
		          !String.valueOf(ed.getOID_Movimento_Chamado()).equals("")){
		        sql += " OID_Movimento_Chamado = " + ed.getOID_Movimento_Chamado();
		      }

		      // System.out.println("#############Movimento_ChamadoED getByRecord##############");
		      // System.out.println(sql);


		      ResultSet res = null;
		      res = this.executasql.executarConsulta(sql);
		      while (res.next()){

		      	if (JavaUtil.doValida(res.getString("OID_Chamado")))
		      		edVolta.setOID_Chamado(res.getLong("OID_Chamado"));

		      	if (JavaUtil.doValida(res.getString("OID_Movimento_Chamado")))
		      		edVolta.setOID_Movimento_Chamado(res.getString("OID_Movimento_Chamado"));

		      	if (JavaUtil.doValida(res.getString("OID_Responsavel")))
		      		edVolta.setOID_Responsavel(res.getString("OID_Responsavel"));

		      	if (JavaUtil.doValida(res.getString("dt_inicio")))
		      		edVolta.setDT_Inicio(res.getString("dt_inicio"));

		      	if (JavaUtil.doValida(res.getString("hr_inicio")))
		      		edVolta.setHR_Inicio(res.getString("hr_inicio"));

		      	if (JavaUtil.doValida(res.getString("TX_Descricao"))){
		      		edVolta.setTX_Descricao(res.getString("TX_Descricao"));
		      		edVolta.setTX_Descricao(ManipulaString.Enter2BR(edVolta.getTX_Descricao()));
		      	}
		      	if (JavaUtil.doValida(res.getString("dt_fim")))
		      		edVolta.setDT_Fim(res.getString("dt_fim"));

		      	if (JavaUtil.doValida(res.getString("hr_fim")))
		      		edVolta.setHR_Fim(res.getString("hr_fim"));

		      	if (JavaUtil.doValida(res.getString("dm_tipo_movimento")))
		      		edVolta.setDM_Tipo_Movimento(res.getString("dm_tipo_movimento"));

		      	if (JavaUtil.doValida(res.getString("usuario_stamp")))
		      		edVolta.setDT_Stamp(res.getString("usuario_stamp"));

		      }
		    }
		      catch(Exception exc){
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao selecionar");
		        excecoes.setMetodo("seleção");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }

		    return edVolta;
		  }

}
