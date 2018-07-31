/*
 * Created on 04/07/2006
 *
 */
package com.master.bd;

/**
 * @author Jonas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.ChamadoED;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.mail.Mailer;



public class ChamadoBD {

	private ExecutaSQL executasql;

	  public ChamadoBD(ExecutaSQL sql) {
	    this.executasql = sql;
	  }


	  public ChamadoED inclui(ChamadoED ed) throws Excecoes{

	  	String sql = "select max(oid_chamado) as result from chamados ";
	  	long valOid = 1;

	    ChamadoED ChamadoED = new ChamadoED();

	    try{
	      ResultSet rs = executasql.executarConsulta(sql);
	      while (rs.next()){
	      	valOid = rs.getLong("result");
	      }

	      valOid++;

	      sql = " insert into Chamados (OID_Chamado, OID_Cliente, OID_Responsavel, DT_Entrada, HR_entrada, dt_inicio, hr_inicio, DM_Prioridade, DM_Classificacao, NM_Solicitante, TX_Descricao, NR_Previsao, DM_Situacao, usuario_stamp) values ";
	      sql += "(" + valOid + ",'" + ed.getOID_Cliente() + "','" + ed.getOID_Responsavel() + "','" + ed.getDT_Entrada() + "','" + ed.getHR_Entrada() + "','" + ed.getDT_Inicio() + "','" + ed.getHR_Inicio() + "','" + ed.getDM_Prioridade() + "','" + ed.getDM_Classificacao() + "','" + ed.getNM_Solicitante() + "','" + ed.getTX_Descricao() + "','" + ed.getNR_Previsao() + "','" + ed.getDM_Situacao() + "','" + ed.getUsuario_stamp() + "')";

	      int i = executasql.executarUpdate(sql);


	      ChamadoED.setOID_Chamado(valOid);

	      //this.enviaMail(ChamadoED, "N");

	    }
	      catch(Exception exc){
	    	  exc.printStackTrace();
	        Excecoes excecoes = new Excecoes();
	        excecoes.setClasse(this.getClass().getName());
	        excecoes.setMensagem("Erro ao incluir Chamado");
	        excecoes.setMetodo("inclui");
	        excecoes.setExc(exc);
	        throw excecoes;
	      }
	      return ChamadoED;

	  }

	  public ChamadoED altera(ChamadoED ed) throws Excecoes{

		  	String sql = null;

		    ChamadoED ChamadoED = new ChamadoED();

		    try{

		        sql = " update Chamados set dm_prioridade = '" + ed.getDM_Prioridade() + "', dm_classificacao = '" + ed.getDM_Classificacao() + "'";


		        if (!ed.getOID_Cliente().equals(null) && !ed.getOID_Cliente().equals("") && !ed.getOID_Cliente().equals("null"))
		    		sql+=", oid_cliente='"+ed.getOID_Cliente()+"'";


		    	if (!ed.getOID_Responsavel().equals(null) && !ed.getOID_Responsavel().equals("") && !ed.getOID_Responsavel().equals("null")){
		    		sql+=", oid_responsavel='"+ed.getOID_Responsavel()+"'";
		    	}

		    	if (!ed.getNM_Solicitante().equals(null) && !ed.getNM_Solicitante().equals("") && !ed.getNM_Solicitante().equals("null")){
		    		sql+=", nm_solicitante='"+ed.getNM_Solicitante()+"'";
		    	}

		    	if (!ed.getNR_Previsao().equals(null) && !ed.getNR_Previsao().equals("") && !ed.getNR_Previsao().equals("null")){
		    		sql+=", nr_previsao='"+ed.getNR_Previsao()+"'";
		    	}

		        if (!ed.getDT_Entrada().equals(null) && !ed.getDT_Entrada().equals("") && !ed.getDT_Entrada().equals("null")){
		        	sql+=", dt_entrada='"+ed.getDT_Entrada()+"'";
		        }

		        if (!ed.getHR_Entrada().equals(null) && !ed.getHR_Entrada().equals("") && !ed.getHR_Entrada().equals("null")){
		        	sql+=", hr_entrada='"+ed.getHR_Entrada()+"'";
		        }

		        if (!ed.getDT_Inicio().equals(null) && !ed.getDT_Inicio().equals("") && !ed.getDT_Inicio().equals("null")){
		        	sql+=", dt_inicio='"+ed.getDT_Inicio()+"'";
		        }

		        if (!ed.getHR_Inicio().equals(null) && !ed.getHR_Inicio().equals("") && !ed.getHR_Inicio().equals("null")){
		        	sql+=", hr_inicio='"+ed.getHR_Inicio()+"'";
		        }

		        if (!ed.getDT_Conclusao().equals(null) && !ed.getDT_Conclusao().equals("") && !ed.getDT_Conclusao().equals("null")){
		        	sql+=", dt_conclusao='"+ed.getDT_Conclusao()+"'";
		        }

		        if (!ed.getHR_Conclusao().equals(null) && !ed.getHR_Conclusao().equals("") && !ed.getHR_Conclusao().equals("null")){
		        	sql+=", hr_conclusao='"+ed.getHR_Conclusao()+"'";
		        }

		        if (!ed.getTX_Descricao().equals(null) && !ed.getTX_Descricao().equals("") && !ed.getTX_Descricao().equals("null")){
		        	sql+=", tx_descricao = '" + ed.getTX_Descricao() + "'";
		        }

		        if (!ed.getDM_Situacao().equals(null) && !ed.getDM_Situacao().equals("") && !ed.getDM_Situacao().equals("null")){
		        	sql+=", dm_situacao = '" + ed.getDM_Situacao() + "'";
		        }



		        sql += " where oid_chamado = " + ed.getOID_Chamado();
		        // System.out.println(ed.getOID_Chamado());

		        // System.out.println("#################sql#####################");
		        // System.out.println(sql);
		        // System.out.println();

		        int i = executasql.executarUpdate(sql);


		      }

		      catch(Exception exc){
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao alterar Chamado");
		        excecoes.setMetodo("altera");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }
		      return ChamadoED;
		  }

	  public void deleta(ChamadoED ed) throws Excecoes{

	    String sql = null;

	    try{

    	  sql = "delete from Movimentos_Chamados WHERE oid_chamado = ";
	      sql += "(" + ed.getOID_Chamado() + ")";

	      int i = executasql.executarUpdate(sql);

	      sql = "delete from Chamados WHERE oid_chamado = ";
	      sql += "(" + ed.getOID_Chamado() + ")";

	      i = executasql.executarUpdate(sql);
	    }

	      catch(Exception exc){
	        Excecoes excecoes = new Excecoes();
	        excecoes.setClasse(this.getClass().getName());
	        excecoes.setMensagem("Erro ao excluir Chamado");
	        excecoes.setMetodo("excluir");
	        excecoes.setExc(exc);
	        throw excecoes;
	      }
	  }

	  public ArrayList lista(ChamadoED ed)throws Excecoes{

	    String sql = null;
	    ArrayList list = new ArrayList();

	    try{

	    	sql="select * from chamados where 1=1 ";
	    	if (ed.getOID_Chamado() > 0)
	    		sql+=" and oid_chamado='"+ed.getOID_Chamado()+"'";

	    	if (!ed.getDT_Entrada().equals(null) && !ed.getDT_Entrada().equals(""))
	    		sql+="dt_entrada>'"+ed.getDT_Entrada()+"'";

	    	if (!ed.getDT_Entrada().equals(null) && !ed.getDT_Entrada().equals(""))
	    		sql+=" and dt_entrada>'"+ed.getDT_Entrada()+"'";

	    	if (!ed.getDT_Entrada_Final().equals(null) && !ed.getDT_Entrada_Final().equals(""))
	    		sql+=" and dt_entrada<'"+ed.getDT_Entrada_Final()+"'";

	    	if (!ed.getDT_Inicio().equals(null) && !ed.getDT_Inicio().equals(""))
	    		sql+=" and dt_inicio>'"+ed.getDT_Inicio()+"'";

	    	if (!ed.getDT_Inicio_Final().equals(null) && !ed.getDT_Inicio_Final().equals(""))
	    		sql+=" and dt_inicio_Final>'"+ed.getDT_Inicio_Final()+"'";

	    	if (!ed.getDM_Prioridade().equals(null)  && !ed.getDM_Prioridade().equals("") && !ed.getDM_Prioridade().equals("T"))
	    		sql+=" and dm_prioridade='"+ed.getDM_Prioridade()+"'";

	    	if (!ed.getDM_Classificacao().equals(null) && !ed.getDM_Classificacao().equals("") && !ed.getDM_Classificacao().equals("T"))
	    		sql+=" and dm_classificacao='"+ed.getDM_Classificacao()+"'";

	    	if (!ed.getDM_Situacao().equals(null) && !ed.getDM_Situacao().equals("") && !ed.getDM_Situacao().equals("T"))
	    		sql+=" and dm_situacao='"+ed.getDM_Situacao()+"'";

	    	if (!ed.getOID_Cliente().equals(null) && !ed.getOID_Cliente().equals("") && !ed.getOID_Cliente().equals("null"))
	    		sql+=" and oid_cliente='"+ed.getOID_Cliente()+"'";

	    	if (!ed.getOID_Responsavel().equals(null) && !ed.getOID_Responsavel().equals("") && !ed.getOID_Responsavel().equals("null"))
	    		sql+=" and oid_responsavel='"+ed.getOID_Responsavel()+"'";

	    	if (!ed.getNM_Solicitante().equals(null) && !ed.getNM_Solicitante().equals("") && !ed.getNM_Solicitante().equals("null"))
	    		sql+=" and nm_solicitante='"+ed.getNM_Solicitante()+"'";

	    	if (!ed.getNR_Previsao().equals(null) && !ed.getNR_Previsao().equals("") && !ed.getNR_Previsao().equals("null"))
	    		sql+=" and nr_previsao='"+ed.getNR_Previsao()+"'";

	    	sql+=" order by OID_Chamado desc";

	    	// System.out.println("##########CHAMADO LISTA##########");
	    	// System.out.println(sql);
	    	// System.out.println();

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);

	      FormataDataBean DataFormatada = new FormataDataBean();

	      while (res.next()){
	        ChamadoED edVolta = new ChamadoED();

	        edVolta.setOID_Chamado(new Long(res.getString("OID_Chamado")).longValue());
	        edVolta.setOID_Cliente(res.getString("OID_Cliente"));
	        edVolta.setOID_Responsavel(res.getString("OID_Responsavel"));

	        edVolta.setNM_Solicitante(res.getString("NM_Solicitante"));
	        edVolta.setNR_Previsao(res.getString("nr_previsao"));

	        edVolta.setDT_Entrada(res.getString("dt_entrada"));
	        edVolta.setDM_Prioridade(res.getString("dm_prioridade"));
	        edVolta.setDM_Situacao(res.getString("dm_situacao"));
	        edVolta.setTX_Descricao(res.getString("tx_descricao"));

	        list.add(edVolta);
	      }
	    }
	      catch(Exception exc){
	        Excecoes excecoes = new Excecoes();
	        excecoes.setClasse(this.getClass().getName());
	        excecoes.setMensagem("Erro ao listar");
	        excecoes.setMetodo("listar");
	        excecoes.setExc(exc);
	        throw excecoes;
	      }

	    return list;
	  }

	  public ChamadoED getByRecord(ChamadoED ed)throws Excecoes{

	    String sql = null;

	    ChamadoED edVolta = new ChamadoED();

	    try{

	      sql =  " SELECT * FROM Chamados WHERE";

	      if (String.valueOf(ed.getOID_Chamado()) != null &&
	          !String.valueOf(ed.getOID_Chamado()).equals("")){
	        sql += " OID_Chamado = " + ed.getOID_Chamado();
	      }

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      while (res.next()){
	        edVolta.setOID_Chamado(res.getLong("OID_Chamado"));
	        edVolta.setOID_Cliente(res.getString("OID_Cliente"));
	        edVolta.setOID_Responsavel(res.getString("OID_Responsavel"));
	        edVolta.setDT_Entrada(FormataData.formataDataBT(res.getString("dt_entrada")));
	        edVolta.setHR_Entrada(res.getString("hr_entrada"));
	        edVolta.setDM_Prioridade(res.getString("dm_prioridade"));
	        edVolta.setDM_Classificacao(res.getString("dm_classificacao"));
	        edVolta.setNM_Solicitante(res.getString("nm_solicitante"));
	        edVolta.setTX_Descricao(res.getString("tx_descricao").replaceAll("\n","<br>"));
	        edVolta.setNR_Previsao(res.getString("nr_previsao"));
	        edVolta.setDT_Inicio(FormataData.formataDataBT(res.getString("dt_inicio")));
	        edVolta.setHR_Inicio(res.getString("hr_inicio"));
	        edVolta.setDT_Conclusao(FormataData.formataDataBT(res.getString("dt_conclusao")));
	        edVolta.setHR_Conclusao(res.getString("hr_conclusao"));
	        edVolta.setDM_Situacao(res.getString("dm_situacao"));
	        edVolta.setUsuario_stamp(res.getString("usuario_stamp"));
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

	  public void enviaMail(ChamadoED ed, String acao) throws Excecoes{

		    String sql = null;
		    String msg = "";
		    String subject = "";
		    String to = "";

		    try{

		    	sql =  " SELECT * FROM Chamados WHERE OID_Chamado = " + ed.getOID_Chamado();

		    	ResultSet res = null;
		    	res = this.executasql.executarConsulta(sql);
		    	while (res.next()){

		    		PessoaBean resp = PessoaBean.getByOID(res.getString("OID_Responsavel"));
		    		PessoaBean cli = PessoaBean.getByOID(res.getString("OID_Cliente"));

		    		if(JavaUtil.doValida(acao)){
		    			if(acao.equals("N")){
		    				subject = "NOVO CHAMADO NR. " + res.getLong("OID_Chamado") + " ABERTO";
		    				msg = "Srs(as),\n Um novo chamado foi aberto na Central de Suporte ao Sistema Nalthus.";
		    				msg += "\nA descricao do chamado e: ";
		    				msg += "\n\t" + res.getString("tx_descricao");
		    				msg += "\nO solicitante do chamado foi: " + res.getString("nm_solicitante");
		    				msg += "\n\tDo Cliente: " + cli.getNM_Razao_Social();
		    				if(JavaUtil.doValida(res.getString("nr_previsao"))){
		    					msg += "\nO chamado tem previsao de: " + res.getString("nr_previsao") + " horas de envolvimento.";
		    					if(JavaUtil.doValida(res.getString("dt_conclusao")))
		    						msg += "\nCom conclusao prevista para " + FormataData.formataDataBT(res.getString("dt_conclusao"));
		    				}
		    				msg += "\nO responsavel pelo chamado, atualmente, e: " + resp.getNM_Razao_Social();
		    				msg += "\n\n\n Atenciosamente\n Central de Suporte ao Sistema Nalthus";
		    				msg += "\n\n\n Este é um envio automático, não o responda!";

		    				to = "paula@nalthus.com.br";
		    				//o TO está fixo para a Paula... mais
		    				if(JavaUtil.doValida(cli.getEMail())) to += ";"+cli.getEMail();
		    				if(JavaUtil.doValida(resp.getEMail())) to += ";"+resp.getEMail();
// System.out.println("TO>"+to);
		    				Mailer.sendMail(subject,to,msg);

		    			} else if(acao.equals("S")){
		    				subject = "CHAMADO NR. " + res.getLong("OID_Chamado") + " ENCERRADO";
		    				to = "paula@nalthus.com.br";
		    				//o TO está fixo para a Paula... mais
		    				if(JavaUtil.doValida(cli.getEMail())) to += ";"+cli.getEMail();
		    				if(JavaUtil.doValida(resp.getEMail())) to += ";"+resp.getEMail();

		    				Mailer.sendMail(subject,to,msg);
		    			} else if(acao.equals("R")){
		    				subject = "CHAMADO NR. " + res.getLong("OID_Chamado") + " RE-ABERTO";
		    				msg = "Srs(as),\n Um chamado foi re-aberto pela Central de Suporte ao Sistema Nalthus.";
		    				msg += "\nA descricao do chamado e: ";
		    				msg += "\n\t" + res.getString("tx_descricao");
		    				msg += "\nO solicitante do chamado foi: " + res.getString("nm_solicitante");
		    				msg += "\n\tDo Cliente: " + cli.getNM_Razao_Social();
		    				if(JavaUtil.doValida(res.getString("nr_previsao"))){
		    					msg += "\nO chamado tem previsao de: " + res.getString("nr_previsao") + " horas de envolvimento.";
		    					if(JavaUtil.doValida(res.getString("dt_conclusao")))
		    						msg += "\nCom conclusao prevista para " + FormataData.formataDataBT(res.getString("dt_conclusao"));
		    				}
		    				msg += "\nO responsavel pelo chamado, atualmente, e: " + resp.getNM_Razao_Social();
		    				msg += "\n\n\n Atenciosamente\n Central de Suporte ao Sistema Nalthus";
		    				msg += "\n\n\n Este é um envio automático, não o responda!";
		    				to = "paula@nalthus.com.br";
		    				//o TO está fixo para a Paula... mais
		    				if(JavaUtil.doValida(cli.getEMail())) to += ";"+cli.getEMail();
		    				if(JavaUtil.doValida(resp.getEMail())) to += ";"+resp.getEMail();

		    				Mailer.sendMail(subject,to,msg);
		    			}

		    		}

		    	}
		    }
		    catch(Exception exc){
		    	exc.printStackTrace();
		        Excecoes excecoes = new Excecoes();
		        excecoes.setClasse(this.getClass().getName());
		        excecoes.setMensagem("Erro ao enviar E-Mail");
		        excecoes.setMetodo("enviaMail");
		        excecoes.setExc(exc);
		        throw excecoes;
		      }
		  }

}
