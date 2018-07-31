package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Movimento_Produto_ClienteED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Produto_ClienteED;
import com.master.ed.WMS_LocalizacaoED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.ed.WMS_Nota_FiscalED;

/**
 * @author André Valadas
 * - Movimento dos Produtos Clientes
 */
public class Movimento_Produto_ClienteBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Movimento_Produto_ClienteBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql; 
    }

    public Movimento_Produto_ClienteED inclui(Movimento_Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        try {
//            ed.setOid_Movimento_Produto(getAutoIncremento("oid_Movimento_Produto", "Movimentos_Produtos_Clientes"));

            ed.setOid_Movimento_Produto(new Long(String.valueOf(System.currentTimeMillis()).toString()).longValue());

            sql = " INSERT INTO Movimentos_Produtos_Clientes (" +
            	  "		 oid_Movimento_Produto" +
            	  "		,oid_Produto_Cliente" +
                  "     ,oid_Tipo_Estoque" +
            	  "		,oid_Localizacao" +
            	  "		,oid_Nota_Fiscal" +
            	  "		,oid_Tipo_Movimento_Produto" +
            	  "		,oid_Operador" +
            	  "		,oid_Requisicao_Produto" +
            	  "		,NR_Quantidade_Requerida" +
            	  "		,NR_Quantidade_Efetiva" +
            	  "		,NR_Quantidade_Estoque_Apos" +
            	  "		,DT_Movimentacao" +
            	  "		,HR_Movimentacao" +
            	  "     ,TX_Observacao) " +
            	  " VALUES (" +
            	  	ed.getOid_Movimento_Produto() +
            	  	",'" + ed.getOid_Produto_Cliente() +"'"+
                    ","  + ed.getOid_Tipo_Estoque() +
            	  	","  + ed.getOid_Localizacao() +
            	  	",'" + ed.getOid_Nota_Fiscal() +"'"+
            	  	"," + ed.getOid_Tipo_Movimento_Produto() +
            	  	"," + ed.getOid_Operador() +
            	  	"," + ed.getOid_Requisicao_Produto() +
            	  	"," + ed.getNR_Quantidade_Requerida() +
            	  	"," + ed.getNR_Quantidade_Efetiva() +
            	  	"," + ed.getNR_Quantidade_Estoque_Apos() +
            	  	",'" + ed.getDT_Movimentacao() + "'" +
            	  	",'" + ed.getHR_Movimentacao() + "'";
            
            if (doValida(ed.getTx_Observacao())){
                sql += ",'" + ed.getTx_Observacao() + "')";
            }else{
                sql += ",null)";
            }

            executasql.executarUpdate(sql);
        	return ed;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public ArrayList lista(Movimento_Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Movimentos_Produtos_Clientes " ;
            if (doValida(ed.getOid_Produto_Cliente()))
            	sql+= ", Produtos_Clientes";
            if (ed.getOid_Tipo_Movimento_Produto() > 0)
            	sql+= ", Tipos_Movimentos_Produtos";
            sql+= " WHERE 1 = 1";

            if (ed.getOid_Movimento_Produto() > 0)
                sql += "   AND Movimentos_Produtos_Clientes.oid_Movimento_Produto = "+ed.getOid_Movimento_Produto();
            if (doValida(ed.getOid_Produto_Cliente()))
                sql += "   AND Movimentos_Produtos_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente" +
                	   "   AND Movimentos_Produtos_Clientes.oid_Produto_Cliente = '"+ed.getOid_Produto_Cliente()+"'";
            if (doValida(ed.getOid_Nota_Fiscal()))
                sql += "   AND Movimentos_Produtos_Clientes.oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"'";
            if (ed.getOid_Tipo_Estoque() > 0)
                sql += "   AND Movimentos_Produtos_Clientes.oid_Tipo_Estoque = "+ed.getOid_Tipo_Estoque();
            if (ed.getOid_Localizacao() > 0)
                sql += "   AND Movimentos_Produtos_Clientes.oid_Localizacao = "+ed.getOid_Localizacao();
            if (ed.getOid_Tipo_Movimento_Produto() > 0)
                sql += "   AND Movimentos_Produtos_Clientes.oid_Tipo_Movimento_Produto = Tipos_Movimentos_Produtos.oid_Tipo_Movimento_Produto" +
                	   "   AND Movimentos_Produtos_Clientes.oid_Tipo_Movimento_Produto = "+ed.getOid_Tipo_Movimento_Produto();
            if (ed.getOid_Operador() > 0)
                sql += "   AND Movimentos_Produtos_Clientes.oid_Operador = "+ed.getOid_Operador();
            if (ed.getOid_Requisicao_Produto() > 0)
                sql += "   AND Movimentos_Produtos_Clientes.oid_Requisicao_Produto = "+ed.getOid_Requisicao_Produto();

            if (doValida(ed.getDt_inicial()))
                sql += "   AND Movimentos_Produtos_Clientes.dt_movimentacao	>= '"+ed.getDt_inicial()+"' ";
            if (doValida(ed.getDt_final()))
                sql += "   AND Movimentos_Produtos_Clientes.dt_movimentacao	<= '"+ed.getDt_final()+"' ";

            if ("C".equals(ed.getDm_Ordenacao())) {
            	sql += " ORDER BY Movimentos_Produtos_Clientes.DT_Movimentacao , Movimentos_Produtos_Clientes.HR_Movimentacao, nr_quantidade_estoque_apos	";
            } else {
            	sql += " ORDER BY Movimentos_Produtos_Clientes.DT_Movimentacao DESC, Movimentos_Produtos_Clientes.HR_Movimentacao DESC, nr_quantidade_estoque_apos DESC";	
            }
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Movimento_Produto_ClienteED edVolta = new Movimento_Produto_ClienteED();

                edVolta.setOid_Movimento_Produto(res.getLong("oid_Movimento_Produto"));
                edVolta.setOid_Produto_Cliente(res.getString("oid_Produto_Cliente"));
                edVolta.setOid_Tipo_Estoque(res.getInt("oid_Tipo_Estoque"));
                if (edVolta.getOid_Tipo_Estoque() > 0)
                    edVolta.edTipo_Estoque = new WMS_Tipo_EstoqueBD(executasql).getByOid(edVolta.getOid_Tipo_Estoque());
                edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
                edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edVolta.setOid_Tipo_Movimento_Produto(res.getInt("oid_Tipo_Movimento_Produto"));
                edVolta.setOid_Operador(res.getInt("oid_Operador"));
                edVolta.setOid_Requisicao_Produto(res.getInt("oid_Requisicao_Produto"));
                edVolta.setNR_Quantidade_Requerida(res.getDouble("NR_Quantidade_Requerida"));
                edVolta.setNR_Quantidade_Efetiva(res.getDouble("NR_Quantidade_Efetiva"));
                edVolta.setNR_Quantidade_Estoque_Apos(res.getDouble("NR_Quantidade_Estoque_Apos"));
                edVolta.setDT_Movimentacao(new FormataDataBean().getDT_FormataData(res.getString("DT_Movimentacao")));
                edVolta.setHR_Movimentacao(res.getString("HR_Movimentacao"));
                edVolta.setTx_Observacao(res.getString("Tx_Observacao"));
                //*** Carrega Dados
                if (doValida(edVolta.getOid_Produto_Cliente()))
                    edVolta.edProduto = new Produto_ClienteBD(executasql).getByRecord(new Produto_ClienteED(edVolta.getOid_Produto_Cliente()));
                if (edVolta.getOid_Tipo_Movimento_Produto() > 0)
                    edVolta.edTipo_Movimento = new WMS_Tipo_MovimentoBD(executasql).getByOid(edVolta.getOid_Tipo_Movimento_Produto());
                if (edVolta.getOid_Localizacao() > 0)
                    edVolta.edLocalizacao = new WMS_LocalizacaoBD(executasql).getByRecord(new WMS_LocalizacaoED(edVolta.getOid_Localizacao()));
                if (doValida(edVolta.getOid_Nota_Fiscal()))
                    edVolta.edNota = new Nota_Fiscal_EletronicaBD(executasql).getByRecord(new Nota_Fiscal_EletronicaED(edVolta.getOid_Nota_Fiscal()));
                lista.add(edVolta);
            }
            return lista;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }

    public Movimento_Produto_ClienteED getByRecord(Movimento_Produto_ClienteED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Movimento_Produto_ClienteED) iterator.next();
        } else return new Movimento_Produto_ClienteED();
    }
}