package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Lote_ChequeED;
import com.master.ed.Lote_RecebimentoED;
import com.master.ed.Motivo_DevolucaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Lotes Cheques
 * @serialData 16/08/2005
 */
public class Lote_ChequeBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Lote_ChequeBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Lote_ChequeED inclui(Lote_ChequeED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Lote_Cheque(getAutoIncremento("oid_Lote_Cheque", "Lotes_Cheques"));
            sql = " INSERT INTO Lotes_Cheques (" +
            	  "		 oid_Lote_Cheque" +
            	  "		,oid_Lote_Recebimento" +
            	  "		,oid_Cheque_Cliente" +
            	  "		,DM_Situacao) " +
            	  " VALUES (" +
            	  	ed.getOid_Lote_Cheque() +
            	  	"," + ed.getOid_Lote_Recebimento() +
            	  	"," + ed.getOid_Cheque_Cliente() +
            	  	",'N')";
            executasql.executarUpdate(sql);

            //*** Seta OID_Conta_Corrente nos cheques do LOTE
            executasql.executarUpdate(" UPDATE Cheques_Clientes SET" +
                                      "      oid_Conta_Corrente = '"+getTableStringValue("oid_Conta_Corrente","Lotes_Recebimentos", "oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento())+"'" +
                                      "     ,DM_Situacao = 'N'"+
                                      " WHERE oid_Cheque_Cliente = "+ed.getOid_Cheque_Cliente());
            
            //*** Atribui valor dor Cheque ao Lote!
            new Lote_RecebimentoBD(executasql).calculaCheque(new Lote_RecebimentoED(ed.getOid_Lote_Recebimento()));
            
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Lote_ChequeED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Lotes_Cheques SET ";
            sql += " 	 oid_Lote_Cheque = oid_Lote_Cheque ";
            if (doValida(ed.getDM_Situacao())) 
                sql += "    ,DM_Situacao = '" + ed.getDM_Situacao() +"'";
            if (ed.getOid_Motivo_Devolucao() > 0) 
                sql += "    ,oid_Motivo_Devolucao = "+ ed.getOid_Motivo_Devolucao();
            sql += " WHERE 1=1 ";
            if (ed.getOid_Lote_Cheque() > 0)
                sql += " AND oid_Lote_Cheque = " + ed.getOid_Lote_Cheque();
            if (ed.getOid_Lote_Recebimento() > 0)
                sql += " AND oid_Lote_Recebimento = " + ed.getOid_Lote_Recebimento();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Lote_ChequeED ed) throws Excecoes {

        String sql = null;
        try {
            ed = this.getByRecord(ed);
            sql = " DELETE FROM Lotes_Cheques " +
            	  " WHERE oid_Lote_Cheque = " + ed.getOid_Lote_Cheque();
            executasql.executarUpdate(sql);
            
            //*** Seta Como NULL a Conta Corrente q foi informada Pelo Lote
            if (ed.getOid_Cheque_Cliente() > 0)
            {
                executasql.executarUpdate(" UPDATE Cheques_Clientes SET" +
                                          "    oid_Conta_Corrente = null" +
                                          "   ,DT_Compensacao = null" +
                                          "   ,DM_Situacao = 'N'" +
                                          " WHERE oid_Cheque_Cliente = "+ed.getOid_Cheque_Cliente());
            }
            //*** Atribui valor dor Cheque ao Lote!
            new Lote_RecebimentoBD(executasql).calculaCheque(new Lote_RecebimentoED(ed.getOid_Lote_Recebimento()));
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(Lote_ChequeED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Lotes_Cheques " +
            	  " WHERE 1=1";
            if (ed.getOid_Lote_Cheque() > 0)
                sql += "   AND Lotes_Cheques.oid_Lote_Cheque = "+ed.getOid_Lote_Cheque();
            if (ed.getOid_Lote_Recebimento() > 0)
                sql += "   AND Lotes_Cheques.oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento();
            if (ed.getOid_Cheque_Cliente() > 0)
                sql += "   AND Lotes_Cheques.oid_Cheque_Cliente = "+ed.getOid_Cheque_Cliente();
            if (ed.getOid_Motivo_Devolucao() > 0)
                sql += "   AND Lotes_Cheques.oid_Motivo_Devolucao = "+ed.getOid_Motivo_Devolucao();
            if (doValida(ed.getDM_Situacao()))
                sql += "   AND Lotes_Cheques.DM_Situacao = '"+ed.getDM_Situacao()+"'";
            sql += " ORDER BY Lotes_Cheques.oid_Lote_Recebimento";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Lote_ChequeED edVolta = new Lote_ChequeED();
                edVolta.setOid_Lote_Cheque(res.getInt("oid_Lote_Cheque"));
                edVolta.setOid_Lote_Recebimento(res.getInt("oid_Lote_Recebimento"));
                edVolta.setOid_Cheque_Cliente(res.getInt("oid_Cheque_Cliente"));
                edVolta.setOid_Motivo_Devolucao(res.getInt("oid_Motivo_Devolucao"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                if (edVolta.getOid_Motivo_Devolucao() > 0 && ed.isCarregaMotivoDev())
                    edVolta.edMotivo = new Motivo_DevolucaoBD(executasql).getByRecord(new Motivo_DevolucaoED(edVolta.getOid_Motivo_Devolucao()));
                
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
        return list;
    }
        
    public Lote_ChequeED getByRecord(Lote_ChequeED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Lote_ChequeED) iterator.next();
        } else return new Lote_ChequeED();
    }
    
    /**
     * LOTES RECEBIMENTOS
     */
    public void addChequesEntregas(Lote_ChequeED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " SELECT DISTINCT Cheques_Clientes.oid_Cheque_Cliente " +
                  " FROM Cheques_Clientes " +
                  "     left join Lotes_Cheques" +
                  "        on Cheques_Clientes.oid_Cheque_Cliente = Lotes_Cheques.oid_Cheque_Cliente " +
                  " WHERE Cheques_Clientes.oid_Entrega = Entregas.oid_Entrega" +
                  "   AND Entregas.oid_Entrega = Documentos_Entregas.oid_Entrega" +
                  "   AND Documentos_Entregas.DM_Situacao NOT IN('D','C')" +
                  "   AND Entregas.DM_Situacao = 'F'" +
                  "   AND (Lotes_Cheques.oid_Cheque_Cliente is null OR (Lotes_Cheques.oid_Cheque_Cliente is not null AND Lotes_Cheques.DM_Situacao <> 'F'))" +
                  "   AND Cheques_Clientes.oid_Conta_Corrente is null" +
                  "   AND Cheques_Clientes.DM_Apresentacao = 'S'" +
                  "   AND Cheques_Clientes.DT_Programado = '"+getTableStringValue("DT_Programada","Lotes_Recebimentos", "oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento())+"'";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Lote_ChequeED edVolta = new Lote_ChequeED();
                edVolta.setOid_Lote_Recebimento(ed.getOid_Lote_Recebimento());
                edVolta.setOid_Cheque_Cliente(res.getInt("oid_Cheque_Cliente"));
                this.inclui(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "addChequesEntregas()");
        }
    }
    public void addChequesByData(Lote_ChequeED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " SELECT DISTINCT Cheques_Clientes.oid_Cheque_Cliente " +
                  " FROM Cheques_Clientes " +
                  "     left join Lotes_Cheques" +
                  "        on Cheques_Clientes.oid_Cheque_Cliente = Lotes_Cheques.oid_Cheque_Cliente" +
                  " WHERE (Lotes_Cheques.oid_Cheque_Cliente is null OR (Lotes_Cheques.oid_Cheque_Cliente is not null AND Lotes_Cheques.DM_Situacao <> 'F'))" +
                  "   AND Cheques_Clientes.oid_Conta_Corrente is null" +
                  "   AND Cheques_Clientes.DM_Apresentacao = 'S'" +
                  "   AND Cheques_Clientes.DT_Programado = '"+getTableStringValue("DT_Programada","Lotes_Recebimentos", "oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento())+"'";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Lote_ChequeED edVolta = new Lote_ChequeED();
                edVolta.setOid_Lote_Recebimento(ed.getOid_Lote_Recebimento());
                edVolta.setOid_Cheque_Cliente(res.getInt("oid_Cheque_Cliente"));
                this.inclui(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "addChequesByData()");
        }
    }
}