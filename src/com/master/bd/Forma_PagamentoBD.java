package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Forma_PagamentoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * - Formas de Pagamentos
 */
public class Forma_PagamentoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Forma_PagamentoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Forma_PagamentoED inclui(Forma_PagamentoED ed) throws Excecoes {

        String sql = null;
        try {            
            ed.setOid_Forma_Pagamento(getAutoIncremento("oid_Forma_Pagamento", "Formas_Pagamentos"));
            
            sql = " INSERT INTO Formas_Pagamentos (" +
            	  "		 oid_Forma_Pagamento" +
            	  "		,oid_Condicao_Pagamento" +
            	  "		,DM_Tipo_Pagamento" +
            	  "		,DM_Tipo_Operacao" +
            	  "		,DM_Tipo_Cobranca) " +
            	  " VALUES (" +
            	  	ed.getOid_Forma_Pagamento() +
            	  	"," + ed.getOid_Condicao_Pagamento() +
            	  	",'" + ed.getDM_Tipo_Pagamento() +"'"+
            	  	",'" + ed.getDM_Tipo_Operacao() +"'"+
            	  	",'" + ed.getDM_Tipo_Cobranca() + "')";
            executasql.executarUpdate(sql);
        	return ed;
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Forma_PagamentoED ed) throws Excecoes {

        String sql = null;
        try {
            sql =  " UPDATE Formas_Pagamentos SET ";
            sql += "	 DM_Tipo_Pagamento = '"+ed.getDM_Tipo_Pagamento()+"'";
            sql += "	,DM_Tipo_Operacao = '"+ed.getDM_Tipo_Operacao()+"'";
            sql += "	,DM_Tipo_Cobranca = '"+ed.getDM_Tipo_Cobranca()+"'";
            sql += " WHERE oid_Forma_Pagamento = " + ed.getOid_Forma_Pagamento();
            executasql.executarUpdate(sql);
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Forma_PagamentoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Formas_Pagamentos " +
            	  " WHERE oid_Forma_Pagamento = " + ed.getOid_Forma_Pagamento();
            executasql.executarUpdate(sql);
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(Forma_PagamentoED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Formas_Pagamentos " +
            	  " WHERE Formas_Pagamentos.oid_Condicao_Pagamento = Condicoes_Pagamentos.oid_Condicao_Pagamento";
            	  
            if (ed.getOid_Forma_Pagamento() > 0)
                sql += "   AND Formas_Pagamentos.oid_Forma_Pagamento = "+ed.getOid_Forma_Pagamento();
            else {
                if (ed.getOid_Condicao_Pagamento() > 0)
                    sql += "   AND Formas_Pagamentos.oid_Condicao_Pagamento = "+ed.getOid_Condicao_Pagamento();
                if (doValida(ed.getDM_Tipo_Pagamento()))
                    sql += "   AND Formas_Pagamentos.DM_Tipo_Pagamento = '"+ed.getDM_Tipo_Pagamento()+"'";
                if (doValida(ed.getDM_Tipo_Operacao()))
                    sql += "   AND Formas_Pagamentos.DM_Tipo_Operacao = '"+ed.getDM_Tipo_Operacao()+"'";
                if (doValida(ed.getDM_Tipo_Cobranca()))
                    sql += "   AND Formas_Pagamentos.DM_Tipo_Cobranca = '"+ed.getDM_Tipo_Cobranca()+"'";
            }
            sql += " ORDER BY Formas_Pagamentos.DM_Tipo_Operacao";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Forma_PagamentoED edVolta = new Forma_PagamentoED();
                edVolta.setOid_Forma_Pagamento(res.getInt("oid_Forma_Pagamento"));
                edVolta.setOid_Condicao_Pagamento(res.getInt("oid_Condicao_Pagamento"));
                edVolta.setDM_Tipo_Pagamento(res.getString("DM_Tipo_Pagamento"));
                edVolta.setDM_Tipo_Operacao(res.getString("DM_Tipo_Operacao"));
                edVolta.setDM_Tipo_Cobranca(res.getString("DM_Tipo_Cobranca"));
                lista.add(edVolta);
            }
            return lista;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }
        
    public Forma_PagamentoED getByRecord(Forma_PagamentoED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Forma_PagamentoED) iterator.next();            
        } else return new Forma_PagamentoED();
    }
}