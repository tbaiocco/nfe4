package com.master.bd;

/**
 * @author André Valadas
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Parcelamento_PedidoED;
import com.master.ed.Solicitacao_CompraED;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;

public class Parcelamento_PedidoBD
    extends BancoUtil {

  private ExecutaSQL executasql;
  FormataDataBean dataFormatada = new FormataDataBean ();

  public Parcelamento_PedidoBD (ExecutaSQL sql) {
    super (sql);
    this.executasql = sql;
  }

  public String gera_Parcelamento (Parcelamento_PedidoED ed ) throws Excecoes {

		String sql = null;
		String retorno="";
	    int i = 0;
	    double vl_parcela = 0;
	    double vl_pedido = 0;
	    double tt_parcela = 0;
	    int dias_vencimento=10;
	    String dt_pagamento="";
	    String dm_vencimento_conta="";
	    String dt_pedido_compra=ed.getDT_Pagamento();
	    String dt_vencimento_minimo=Data.getDataDMY();

	    try {
	        Solicitacao_CompraED solicitacaoED = new Solicitacao_CompraED ();
	        solicitacaoED.setOid_Pedido_compra(new Long(ed.getOID_Pedido_Compra()).longValue());
	    	Solicitacao_CompraBD solicitacao_CompraBD = new Solicitacao_CompraBD(this.executasql);
	    	solicitacaoED = solicitacao_CompraBD.getByRecord_pedido(solicitacaoED);

	    	if (solicitacaoED.getVl_Pedido()>0){

		    	     //EXCLUI PEDIDOS
		    	 	 sql=" DELETE FROM parcelamentos_pedidos WHERE oid_Pedido_Compra='" + ed.getOID_Pedido_Compra() + "'";
		    	 	 executasql.executarUpdate (sql);
		    	 	 vl_pedido=solicitacaoED.getVl_Pedido();

		    	 	 System.out.println("vl_pedido=" + vl_pedido);

					 System.out.println("ed.getVl_Parcela_Entrada()=" + ed.getVl_Parcela_Entrada());
					 if (ed.getVl_Parcela_Entrada()>vl_pedido){
						 ed.setVl_Parcela_Entrada(0);
					 }
					 if (ed.getVl_Parcela_Entrada()==vl_pedido){
						 //ed.setVl_Parcela_Entrada(0);
						 ed.setNR_Parcelas(1);
					 }



				     sql =" SELECT Contas.dm_vencimento " +
				      	   " FROM   Contas, Solicitacoes_Compras " +
				      	   " WHERE 	Solicitacoes_Compras.oid_Conta = Contas.oid_Conta " +
				 	   	   " AND    Contas.dm_vencimento IS NOT NULL" +
					       " AND    Solicitacoes_Compras.oid_solicitacao_compra='" + solicitacaoED.getOid_Solicitacao_compra() + "'";
				     System.out.println(sql);
				     ResultSet resCta = executasql.executarConsulta (sql);
				     if (resCta.next ()) {
				    	     if (resCta.getString("dm_vencimento")!= null && !resCta.getString("dm_vencimento").equals("null") && !resCta.getString("dm_vencimento").equals("")){
					    	     dm_vencimento_conta=resCta.getString("dm_vencimento");
					    	     dias_vencimento=resCta.getInt("dm_vencimento");
					    	     dt_vencimento_minimo=Data.manipulaDias(dt_pedido_compra, dias_vencimento);
				    	     }
				     }
					 System.out.println("dm_vencimento_conta=" + dm_vencimento_conta);


					 System.out.println("dt_pagamento=" + dt_pagamento);
					 System.out.println("dt_vencimento_minimo=" + dt_vencimento_minimo);

				     if (Data.comparaData(dt_vencimento_minimo, dt_pagamento)){
				    	  dt_pagamento=dt_vencimento_minimo;
				     }
					 System.out.println("dt_vencimento_minimo 2=" + dt_vencimento_minimo);



				     while (i < ed.getNR_Parcelas()) {

					        vl_parcela = vl_pedido;


							dt_pagamento=Data.manipulaDias(dt_pedido_compra, (i*30));

						    if (Data.comparaData(dt_vencimento_minimo, dt_pagamento)){
						    	  dt_pagamento=dt_vencimento_minimo;
						    }


					        if (ed.getNR_Parcelas()>1){


					            vl_parcela = Valor.getValorArredondado (vl_pedido / ed.getNR_Parcelas() );

								if (ed.getVl_Parcela_Entrada()>0){
									if (i==0){
									    vl_parcela = ed.getVl_Parcela_Entrada();
									}else{
							            vl_parcela = Valor.getValorArredondado ((vl_pedido- ed.getVl_Parcela_Entrada()) / (ed.getNR_Parcelas()-1) );
									}
								}

					            if (i+1 == ed.getNR_Parcelas()) {
					            	vl_parcela = Valor.getValorArredondado (vl_pedido - tt_parcela);
					            }
					        }

							System.out.println("vl_parcela=" + vl_parcela);
							System.out.println("i=" + i);

					        Parcelamento_PedidoED edPed = new Parcelamento_PedidoED ();
					        edPed.setVl_Parcela_Entrada(ed.getVl_Parcela_Entrada());
					        edPed.setOID_Pedido_Compra( ed.getOID_Pedido_Compra());
					        edPed.setDT_Pagamento(dt_pagamento);
					        edPed.setNR_Parcelamento(i+1);
					        edPed.setVL_Parcela(vl_parcela);
					        edPed = inclui(edPed);

					        if (1==2 && !"".equals(dt_pagamento)){
					        	sql =" UPDATE parcelamentos_pedidos SET dm_vencimento_conta='" + dm_vencimento_conta + "', dt_vencimento_minimo='" + dt_vencimento_minimo + "', dt_pagamento='" + dt_pagamento + "' WHERE oid_parcelamento = " + edPed.getOID_Parcelamento();
					            System.out.println(sql);

					            executasql.executarUpdate (sql);
					   	        dt_pagamento=Data.manipulaDias(dt_pagamento, 30);
					        }

					        sql =   " UPDATE Solicitacoes_compras SET " +
						          	"	      nr_parcelas = " + ed.getNR_Parcelas() +
						          	"	     ,vl_parcela_entrada = " + ed.getVl_Parcela_Entrada() +
						          	" WHERE oid_solicitacao_compra='" + ed.getOID_Pedido_Compra() + "'";
					        executasql.executarUpdate (sql);


					        tt_parcela += vl_parcela;
					        i++;
				     }
			  }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	    return retorno;
	  }



  public Parcelamento_PedidoED inclui (Parcelamento_PedidoED ed) throws Excecoes {

    String sql = null;
    try {
      //*** Calcula Desdobramento

      //*** Incrementa oid
       ed.setOID_Parcelamento (getAutoIncremento ("oid_parcelamento" , "parcelamentos_Pedidos"));

      dataFormatada.setDT_FormataData (ed.getDT_Pagamento ());
      ed.setDT_Pagamento (dataFormatada.getDT_FormataData ());

      sql = " INSERT INTO parcelamentos_Pedidos (" +
          "		OID_Parcelamento, " +
          "		OID_Pedido_Compra, " +
          "		nr_parcelamento, " +
          "		vl_parcela, " +
          "		vl_parcela_entrada, " +
          "		vl_desconto, " +
          "		dt_pagamento, " +
          "		dt_stamp, " +
          "		usuario_stamp, " +
          "		dm_stamp, " +
          "		DM_Tipo_Pagamento) " +
          " VALUES ";
      sql += "(" + ed.getOID_Parcelamento () +
          ",'" + ed.getOID_Pedido_Compra () +
          "'," + ed.getNR_Parcelamento () +
          "," + ed.getVL_Parcela () +
          "," + ed.getVl_Parcela_Entrada () +
          "," + ed.getVL_Desconto () +
          ",'" + ed.getDT_Pagamento () +
          "','" + ed.getDt_stamp () +
          "','" + ed.getUsuario_Stamp () +
          "','" + ed.getDm_Stamp () +
          "','" + (doValida(ed.getDM_Tipo_Pagamento()) ? ed.getDM_Tipo_Pagamento() : "B" )+ "')";

      System.out.println(sql);

      executasql.executarUpdate (sql);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
    return ed;
  }

  public String  altera (Parcelamento_PedidoED ed) throws Excecoes {

    String sql = null;
    String retorno="";
    try {

      Parcelamento_PedidoED edVolta = this.getByRecord(ed);

      //dataFormatada.setDT_FormataData (ed.getDT_Pagamento ());
      //ed.setDT_Pagamento (dataFormatada.getDT_FormataData ());


      //if (edVolta.getDT_Vencimento_Minimo()!=null && edVolta.getDT_Vencimento_Minimo().length()>4){
    //	  if (Data.comparaData(edVolta.getDT_Vencimento_Minimo(), ed.getDT_Pagamento ())){
		//		retorno=" Data INFERIOR a Data Mínima de Vencimento!";
    	 // }
     // }

      if ("".equals(retorno)){
          sql =   " UPDATE parcelamentos_Pedidos SET " +
		          "	      dt_pagamento = '" + ed.getDT_Pagamento () + "'" +
			      " WHERE OID_Parcelamento = " + ed.getOID_Parcelamento ();
	      executasql.executarUpdate (sql);
      }

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
    return retorno;
  }

  public void deleta (Parcelamento_PedidoED ed) throws Excecoes {

    String sql = null;
    try {
      //*** Primeiro verifico se existem ligaçs
       /*sql = " Select OID_Compromisso, OID_Parcelamento from parcelas_compromissos "+
          " Where OID_Parcelamento ="+ed.getOID_Parcelamento();
                 ResultSet res = executasql.executarConsulta(sql);

                while(res.next()){
          //*** Agora verifica se o compromisso vinculado nãestám lote de pagamento
            sql = "select * from compromissos c, parcelas_compromissos p ";
            sql +="where c.vl_saldo = c.vl_compromisso ";
            sql +="and c.nr_documento = '"+ed.getNR_Nota_Fiscal()+"' ";
            sql +="and c.oid_compromisso=p.oid_compromisso ";
            sql +="and p.OID_Parcelamento  ="+ res.getLong("OID_Parcelamento");
            sql +=" AND c.oid_compromisso = " + res.getLong("OID_Compromisso");

            ResultSet rs = executasql.executarConsulta(sql);
            while(rs.next()){
                sql = " DELETE from parcelas_compromissos "+
                    " WHERE oid_parcelamento = " + res.getLong("OID_Parcelamento")+
                    " AND oid_compromisso = " + res.getLong("OID_Compromisso");
                executasql.executarUpdate(sql);

                sql = "delete from Compromissos WHERE oid_Compromisso = ";
                sql +=res.getLong("OID_Compromisso");
                executasql.executarUpdate(sql);
            }
                  }*/
        sql = " DELETE from parcelamentos_Pedidos " +
            " WHERE oid_parcelamento = " + ed.getOID_Parcelamento ();
      executasql.executarUpdate (sql);


    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }


  public ArrayList lista (Parcelamento_PedidoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {
      sql = " SELECT * " +
          " FROM Parcelamentos_Pedidos  " +
          " WHERE OID_Pedido_Compra = '" + ed.getOID_Pedido_Compra () + "'" +
      	  " ORDER BY nr_parcelamento ";

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Parcelamento_PedidoED edVolta = new Parcelamento_PedidoED ();
        edVolta.setOID_Parcelamento (res.getLong ("oid_parcelamento"));
        edVolta.setOID_Pedido_Compra (res.getString ("OID_Pedido_Compra"));
        edVolta.setDT_Pagamento (res.getString ("dt_pagamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Pagamento ());
        edVolta.setDT_Pagamento (dataFormatada.getDT_FormataData ());

        edVolta.setNR_Parcelamento (res.getLong ("nr_parcelamento"));
        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Vencimento_Conta(res.getString ("dm_vencimento_conta"));
        edVolta.setVL_Parcela (res.getDouble ("vl_parcela"));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    return list;
  }

  public Parcelamento_PedidoED getByRecord (Parcelamento_PedidoED ed) throws Excecoes {

    String sql = null;
    Parcelamento_PedidoED edVolta = new Parcelamento_PedidoED ();
    try {
      sql = " SELECT * " +
          	" FROM  parcelamentos_Pedidos  " +
          	" WHERE Parcelamentos_Pedidos.oid_parcelamento = '" + ed.getOID_Parcelamento () + "'";

      System.out.println("sql=" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Parcelamento (res.getLong ("oid_parcelamento"));
        edVolta.setOID_Pedido_Compra (res.getString ("OID_Pedido_Compra"));
        edVolta.setDT_Pagamento (res.getString ("dt_pagamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Pagamento ());
        edVolta.setDT_Pagamento (dataFormatada.getDT_FormataData ());

        edVolta.setNR_Parcelamento (res.getLong ("nr_parcelamento"));
        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Vencimento_Conta(res.getString ("dm_vencimento_conta"));
        edVolta.setVL_Parcela (res.getDouble ("vl_parcela"));
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

}