package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.PedidoED;
import com.master.ed.Pedido_Nota_FiscalED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serialData 02/02/2005
 * @JavaBean.class Pedido_Nota_FiscalBD
 */
public class Pedido_Nota_FiscalBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Pedido_Nota_FiscalBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Pedido_Nota_FiscalED inclui(Pedido_Nota_FiscalED ed) throws Excecoes {

        String sql = null;

        try {
            if (ed.getOid_Pedido_Nota_Fiscal() < 1)
                ed.setOid_Pedido_Nota_Fiscal(getAutoIncremento("oid_Pedido_Nota_Fiscal", "Pedidos_Notas_Fiscais"));  
            
            sql = " INSERT INTO Pedidos_Notas_Fiscais (" +
            	  "		 oid_Pedido_Nota_Fiscal" +
            	  "		,oid_Pedido" +
            	  "		,oid_Nota_Fiscal" +
            	  "		,DM_Entrada_Saida"+
            	  "		,DM_Situacao) " +
            	  " VALUES (" +
            	  	ed.getOid_Pedido_Nota_Fiscal() +
            	  	"," + ed.getOid_Pedido() +
            	  	",'" + ed.getOid_Nota_Fiscal() + "'" +
            	  	",'" + ed.getDM_Entrada_Saida() + "'" +
            	  	",'" + ed.getDM_Situacao() + "')";
            executasql.executarUpdate(sql);
        	return ed;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Pedido_Nota_FiscalED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Pedidos_Notas_Fiscais SET ";
            sql += " 	DM_Situacao = '" + ed.getDM_Situacao() + "'";
            sql += " WHERE oid_Pedido_Nota_Fiscal = " + ed.getOid_Pedido_Nota_Fiscal();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Pedido_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Pedidos_Notas_Fiscais " +
            	  " WHERE oid_Pedido_Nota_Fiscal = " + ed.getOid_Pedido_Nota_Fiscal();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(Pedido_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Pedidos_Notas_Fiscais " +
            	  " WHERE Pedidos_Notas_Fiscais.oid_Pedido = Pedidos.oid_Pedido " +
            	  "   AND Pedidos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal ";
            if (ed.getOid_Pedido_Nota_Fiscal() > 0)
                sql +=" AND Pedidos_Notas_Fiscais.oid_Pedido_Nota_Fiscal = "+ed.getOid_Pedido_Nota_Fiscal();
            if (ed.getOid_Pedido() > 0)
                sql +=" AND Pedidos_Notas_Fiscais.oid_Pedido = "+ed.getOid_Pedido();
            if (doValida(ed.getOid_Nota_Fiscal()))
                sql +=" AND Pedidos_Notas_Fiscais.oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"'";
            if (doValida(ed.edPedido.getOID_Vendedor()))
                sql +=" AND Pedidos.oid_Vendedor = '"+ed.edPedido.getOID_Vendedor()+"'";
            if (doValida(ed.getDM_Entrada_Saida()))
                sql +=" AND Pedidos_Notas_Fiscais.DM_Entrada_Saida = '"+ed.getDM_Entrada_Saida()+"'";
            if (doValida(ed.getDM_Situacao()))
                sql +=" AND Pedidos_Notas_Fiscais.DM_Situacao = '"+ed.getDM_Situacao()+"'";
            if (doValida(ed.edPedido.getDM_Pedido()))
                sql +=" AND Pedidos.DM_Pedido = '"+ed.edPedido.getDM_Pedido()+"'";
            
            sql +=" ORDER BY oid_Pedido_Nota_Fiscal, DM_Situacao";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Pedido_Nota_FiscalED edVolta = new Pedido_Nota_FiscalED();

                edVolta.setOid_Pedido_Nota_Fiscal(res.getInt("oid_Pedido_Nota_Fiscal"));
                edVolta.setOid_Pedido(res.getInt("oid_Pedido"));
                //*** Carrega DADOS do PEDIDO
                if (edVolta.getOid_Pedido() > 0)
                    edVolta.edPedido = new PedidoBD(executasql).getByRecord(new PedidoED(edVolta.getOid_Pedido()));
                //*** Carrega DADOS da NOTA_FISCAL
                edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                if (doValida(edVolta.getOid_Nota_Fiscal()))
                    edVolta.edNota_Fiscal = new Nota_Fiscal_EletronicaBD(executasql).getByRecord(new Nota_Fiscal_EletronicaED(edVolta.getOid_Nota_Fiscal()));

                edVolta.setDM_Entrada_Saida(res.getString("DM_Entrada_Saida"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));

                lista.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
        return lista;
    }

    public Pedido_Nota_FiscalED getByRecord(Pedido_Nota_FiscalED ed) throws Excecoes {

        Iterator it = this.lista(ed).iterator();
        if (it.hasNext())
            return (Pedido_Nota_FiscalED) it.next();
        else return new Pedido_Nota_FiscalED();
    }
}