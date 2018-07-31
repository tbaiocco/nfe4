package com.master.bd;

import java.sql.*;
import java.util.*;
import com.master.ed.*;
import com.master.root.FormataDataBean;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.BancoUtil;

public class EDI_SincoBD {

	private ExecutaSQL executasql;
	BancoUtil util = new BancoUtil();
	  
	public EDI_SincoBD (ExecutaSQL sql) {
	    this.executasql = sql;
	}

  	public ArrayList gera_Arquivo_EDI_Sinco_Movimento(EDI_SincoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		  
	    try {
	    	list = carregaMovimentosContabeis(ed, list);
	    	
	    }  
	    catch (Exception exc) {
	      exc.printStackTrace ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao listar");
	      excecoes.setMetodo ("listar");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return list;
  	}
 
	public ArrayList gera_Arquivo_EDI_Sinco_Conta(EDI_SincoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		  
	    try {
	    	list = carregaContas(ed, list);
	    	
	    }  
	    catch (Exception exc) {
	      exc.printStackTrace ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao listar");
	      excecoes.setMetodo ("listar");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return list;
  	}

  	private EDI_SincoED carregaED (EDI_SincoED ed) throws Excecoes {
      
	    EDI_SincoED edVolta = new EDI_SincoED ();
	    edVolta.setOID_Unidade(ed.getOID_Unidade());
	    edVolta.setDT_Inicial(ed.getDT_Inicial());
	    edVolta.setDT_Final(ed.getDT_Final());
	    return edVolta;
	    
  	}
  
  	public ArrayList carregaMovimentosContabeis (EDI_SincoED ed, ArrayList list) throws Excecoes {
	    
	  	String sql = null;
	    ResultSet rs = null;
	   
	    try {
	      sql = "SELECT " +
	      		" M.dt_Movimento," +
	      		" M.vl_Lancamento," +
	      		" M.dm_Debito_Credito," +
	      		" M.tx_Historico," +
	      		" M.tx_Historico_Complementar," +
	      		" C.cd_Estrutural," +
	      		" C.cd_Conta" +
	            " FROM " +
	            " Movimentos_Contabeis as M,  " +
	            " Contas as C" +
	            " WHERE " +
	            " C.oid_Conta = M.oid_Conta";
	      
	      if (util.doValida (ed.getDT_Inicial()) && util.doValida (ed.getDT_Final())) {
	        sql += " AND M.dt_Movimento between '" + ed.getDT_Inicial () + "' AND '" + ed.getDT_Final ()+ "' ";
	      }
	      else {
	        if (util.doValida (ed.getDT_Inicial ())) {
	          sql += " AND M.dt_Movimento = '" + ed.getDT_Inicial () + "'";
	        	}
	        if (util.doValida (ed.getDT_Final ())) {
	          sql += " AND M.dt_Movimento = '" + ed.getDT_Final ()+ "' ";
	        }
	      }
	      
	      rs = this.executasql.executarConsulta (sql);

	      while (rs.next ()) {
	    	EDI_SincoED edVolta = new EDI_SincoED ();
	        edVolta = carregaED(ed);
	        edVolta.setCD_Tipo("MOVIMENTOS");
	        edVolta.setDt_Lancamento(FormataDataBean.getFormatDate(rs.getString("dt_movimento")));
	        edVolta.setCd_Conta(rs.getString("cd_estrutural")+(" ")+rs.getString("cd_conta"));
	        edVolta.setCd_Centro_Custo(" ");
	        edVolta.setCd_Contra_Partida(" ");
	        edVolta.setVl_Lancamento(rs.getString("vl_lancamento"));
	        edVolta.setDm_Tipo_Debito_Credito(rs.getString("dm_debito_credito"));
	        edVolta.setNm_Historico_Lancamento(rs.getString("tx_historico") + rs.getString("tx_historico_complementar"));
	        list.add (edVolta);
	      }
	      return list;
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "lista(EDI_SincoED ed)");
	    }
  	}
  	
  	public ArrayList carregaContas (EDI_SincoED ed, ArrayList list) throws Excecoes {

	    String sql = null;
	    ResultSet rs = null;


	    try {
	      
	      sql = " SELECT * FROM Contas " ;
	      rs = this.executasql.executarConsulta (sql);
	
	      while (rs.next ()){
	        EDI_SincoED edVolta = new EDI_SincoED ();
	        edVolta = this.carregaED(ed);
	        
	    	edVolta.setCD_Tipo("CONTAS");
	        edVolta.setCd_Conta(rs.getString("cd_estrutural")+(" ")+rs.getString ("cd_conta"));
	        edVolta.setDm_Tipo_Conta(rs.getString("dm_tipo_conta"));
	        edVolta.setCd_Contabil(rs.getString("cd_estrutural"));
	        edVolta.setNm_Conta(rs.getString("nm_conta"));
	        list.add (edVolta);
	      }

	    }  
	    catch (Exception exc) {
	      exc.printStackTrace ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao listar");
	      excecoes.setMetodo ("carregaContas");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return list;
	}
}
