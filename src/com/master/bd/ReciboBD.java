package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Conta_CorrenteED;
import com.master.ed.ReciboED;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @JavaBean.class Recibos
 * @serialData 13/10/2005
 */
public class ReciboBD extends BancoUtil {

    private ExecutaSQL executasql;

    public ReciboBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public ReciboED inclui(ReciboED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Recibo(getAutoIncremento("oid_Recibo", "Recibos"));
            ed.setNR_Recibo(getTableIntValue("NR_Proximo_Recibo", 
                                             "Parametros_Filiais",
                                             "oid_Unidade = "+ed.getOid_Unidade()));
            
            sql = " INSERT INTO Recibos (" +
            	  "		 oid_Recibo" +
            	  "		,oid_Unidade" +
                  "     ,oid_Conta_Corrente" +
                  "     ,oid_Pessoa" +
            	  "		,NR_Recibo" +
                  "     ,VL_Recibo" +
            	  "		,DT_Recibo" +
            	  "		,DM_Tipo" +
            	  "		,TX_Observacao) " +
            	  " VALUES (" +
            	  	ed.getOid_Recibo() +
            	  	"," + ed.getOid_Unidade() +
                    ",'" + ed.getOid_Conta_Corrente() + "'" +
                    ",'" + ed.getOid_Pessoa() + "'" +
            	  	"," + ed.getNR_Recibo() +
                    "," + ed.getVL_Recibo() +
            	  	",'" + getValueDef(ed.getDT_Recibo(),Data.getDataYMD()) + "'" +
            	  	",'" + ed.getDM_Tipo()+ "'" +
            	  	",'" + ed.getTX_Observacao() + "')";
            executasql.executarUpdate(sql);
            
            sql = " UPDATE Parametros_Filiais SET NR_Proximo_Recibo = " + (ed.getNR_Recibo() + 1);
            sql +=" WHERE oid_Unidade = " + ed.getOid_Unidade();
            executasql.executarUpdate(sql);
            
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(ReciboED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Recibos SET ";
            sql += " 	 oid_Recibo = oid_Recibo ";
            if (doValida(ed.getOid_Conta_Corrente())) 
                sql += " 	,oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente() +"'";
            if (doValida(ed.getOid_Pessoa())) 
                sql += "    ,oid_Pessoa = '" + ed.getOid_Pessoa() +"'";
            if (ed.getVL_Recibo() > 0) 
                sql += "    ,VL_Recibo = " + ed.getVL_Recibo();
            if (doValida(ed.getDT_Recibo())) 
                sql += "    ,DT_Recibo = '" + ed.getDT_Recibo() +"'";
            if (doValida(ed.getDM_Tipo())) 
                sql += "    ,DM_Tipo = '" + ed.getDM_Tipo() +"'";
            if (doValida(ed.getTX_Observacao())) 
                sql += "    ,TX_Observacao = '" + ed.getTX_Observacao() +"'";
            sql += " WHERE oid_Recibo = " + ed.getOid_Recibo();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(ReciboED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Recibos " +
            	  " WHERE oid_Recibo = " + ed.getOid_Recibo();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(ReciboED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Recibos " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_Recibo() > 0)
                sql += "   AND Recibos.oid_Recibo = "+ed.getOid_Recibo();
            if (ed.getOid_Unidade() > 0)
                sql += "   AND Recibos.oid_Unidade = "+ed.getOid_Unidade();
            if (doValida(ed.getOid_Conta_Corrente()))
                sql += "   AND Recibos.oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'";
            if (doValida(ed.getOid_Pessoa()))
                sql += "   AND Recibos.oid_Pessoa = '"+ed.getOid_Pessoa()+"'";
            if (ed.getNR_Recibo() > 0)
                sql += "   AND Recibos.NR_Recibo = "+ed.getNR_Recibo();
            if (doValida(ed.getDM_Tipo()))
                sql += "   AND Recibos.DM_Tipo = '"+ed.getDM_Tipo()+"'";
            if (ed.getVL_Recibo() > 0)
                sql += "   AND Recibos.VL_Recibo = '"+ed.getVL_Recibo()+"'";
            //*** Data da Recibo
            if (doValida(ed.getDT_Recibo()) && doValida(ed.getDT_Recibo_Final())) {
                sql +=" AND Recibos.DT_Recibo BETWEEN '"+ed.getDT_Recibo()+"' AND '"+ed.getDT_Recibo_Final()+"'";
            } else if (doValida(ed.getDT_Recibo()) || doValida(ed.getDT_Recibo_Final())) {
                sql +=" AND Recibos.DT_Recibo = '"+(doValida(ed.getDT_Recibo()) ? ed.getDT_Recibo() : ed.getDT_Recibo_Final())+"'";
            }
            sql += " ORDER BY Recibos.DT_Recibo";
            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                ReciboED edVolta = new ReciboED();
          
                edVolta.setOid_Recibo(res.getInt("oid_Recibo"));
                edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
                edVolta.setOid_Conta_Corrente(res.getString("oid_Conta_Corrente"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setNR_Recibo(res.getInt("NR_Recibo"));
                edVolta.setVL_Recibo(res.getDouble("VL_Recibo"));
                edVolta.setDM_Tipo(res.getString("DM_Tipo"));
                edVolta.setDT_Recibo(FormataDataBean.getFormatDate(res.getString("DT_Recibo")));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                
                //*** Carrega Dados
                if (doValida(edVolta.getOid_Conta_Corrente()) && ed.isCarregaCCorrente())
                    edVolta.edConta_Corrente = new Conta_CorrenteBD(executasql).getByRecord(new Conta_CorrenteED(edVolta.getOid_Conta_Corrente()));
                if (edVolta.getOid_Unidade() > 0 && ed.isCarregaUnidade())
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade());
                if (doValida(edVolta.getOid_Pessoa()) && ed.isCarregaPessoa())
                    edVolta.edPessoa = PessoaBean.getByOID(edVolta.getOid_Pessoa());
                
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }

    public ReciboED getByRecord(ReciboED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (ReciboED) iterator.next();
        } else return new ReciboED();
    }
    
    /** ------------ RELATÓRIOS ---------------- */ 
}