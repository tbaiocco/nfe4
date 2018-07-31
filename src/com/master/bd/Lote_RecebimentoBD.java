package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import com.master.ed.Cheque_ClienteED;
import com.master.ed.Conta_CorrenteED;
import com.master.ed.Lote_ChequeED;
import com.master.ed.Lote_RecebimentoED;
import com.master.ed.RelatorioED;
import com.master.rl.JasperRL;
import com.master.root.FormataDataBean;
import com.master.root.Tipo_DocumentoBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Lotes Recebimentos
 * @serialData 15/08/2005
 */
public class Lote_RecebimentoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Lote_RecebimentoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Lote_RecebimentoED inclui(Lote_RecebimentoED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Lote_Recebimento(getAutoIncremento("oid_Lote_Recebimento", "Lotes_Recebimentos"));
            ed.setNR_Lote(getTableIntValue("NR_Proximo_Lote_Recebimento", 
                                           "Parametros_Filiais",
                                           "oid_Unidade = "+ed.getOid_Unidade()));
            
            sql = " INSERT INTO Lotes_Recebimentos (" +
            	  "		 oid_Lote_Recebimento" +
            	  "		,oid_Unidade" +
            	  "		,NR_Lote" +
                  "     ,VL_Lote" +
            	  "		,oid_Conta_Corrente" +
            	  "		,oid_Tipo_Documento" +
            	  "		,DT_Emissao" +
            	  "		,DT_Programada" +
            	  "		,DM_Situacao" +
            	  "		,TX_Observacao) " +
            	  " VALUES (" +
            	  	ed.getOid_Lote_Recebimento() +
            	  	"," + ed.getOid_Unidade() +
            	  	"," + ed.getNR_Lote() +
                    "," + ed.getVL_Lote() +
            	  	",'" + ed.getOid_Conta_Corrente() + "'" +
            	  	"," + ed.getOid_Tipo_Documento() +
            	  	",'" + getValueDef(ed.getDT_Emissao(),Data.getDataYMD()) + "'" +
            	  	",'" + ed.getDT_Programada() + "'" +
            	  	",'" + getValueDef(ed.getDM_Situacao(),"N") + "'" +
            	  	",'" + ed.getTX_Observacao() + "')";
            
            executasql.executarUpdate(sql);
            sql = " UPDATE Parametros_Filiais SET NR_Proximo_Lote_Recebimento = " + (ed.getNR_Lote() + 1);
            sql +=" WHERE oid_Unidade = " + ed.getOid_Unidade();
            executasql.executarUpdate(sql);
            
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Lote_RecebimentoED ed) throws Excecoes {

        String sql = null;
        try {
            
            sql =  " UPDATE Lotes_Recebimentos SET ";
            sql += " 	 oid_Lote_Recebimento = oid_Lote_Recebimento ";
            if (doValida(ed.getOid_Conta_Corrente())) 
                sql += " 	,oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente() +"'";
            if (ed.getOid_Tipo_Documento() > 0) 
                sql += "    ,oid_Tipo_Documento = " + ed.getOid_Tipo_Documento();
            if (ed.getVL_Lote() > 0) 
                sql += "    ,VL_Lote = " + ed.getVL_Lote();
            if (doValida(ed.getDT_Programada())) 
                sql += "    ,DT_Programada = '" + ed.getDT_Programada() +"'";
            if (doValida(ed.getDT_Compensacao())) 
                sql += "    ,DT_Compensacao = '" + ed.getDT_Compensacao() +"'";
            if (doValida(ed.getDM_Situacao())) 
                sql += "    ,DM_Situacao = '" + ed.getDM_Situacao() +"'";
            if (doValida(ed.getTX_Observacao())) 
                sql += "    ,TX_Observacao = '" + ed.getTX_Observacao() +"'";
            sql += " WHERE oid_Lote_Recebimento = " + ed.getOid_Lote_Recebimento();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Lote_RecebimentoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Lotes_Recebimentos " +
            	  " WHERE oid_Lote_Recebimento = " + ed.getOid_Lote_Recebimento();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    //*** Atribui Valor dos Cheques no Lote
    public void calculaCheque(Lote_RecebimentoED ed) throws Exception {

        try {
            if (ed.getOid_Lote_Recebimento() < 1)
                throw new Excecoes("Lote Recebimento não informado!");
            
            executasql.executarUpdate(" UPDATE Lotes_Recebimentos SET " +
                                      "     VL_Lote = (SELECT sum(VL_Cheque)" +
                                      "                FROM Cheques_Clientes" +
                                      "                WHERE Cheques_Clientes.oid_Cheque_Cliente = Lotes_Cheques.oid_Cheque_Cliente" +
                                      "                  AND Lotes_Cheques.oid_Lote_Recebimento =  "+ed.getOid_Lote_Recebimento()+")" +
                                      " WHERE oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento());
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "calculaCheque()");
        }
    }
    
    public ArrayList lista(Lote_RecebimentoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Lotes_Recebimentos " +
            	  " WHERE 1=1";
            	  
            if (ed.getOid_Lote_Recebimento() > 0)
                sql += "   AND Lotes_Recebimentos.oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento();
            if (ed.getOid_Unidade() > 0)
                sql += "   AND Lotes_Recebimentos.oid_Unidade = "+ed.getOid_Unidade();
            if (doValida(ed.getOid_Conta_Corrente()))
                sql += "   AND Lotes_Recebimentos.oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'";
            if (ed.getNR_Lote() > 0)
                sql += "   AND Lotes_Recebimentos.NR_Lote = "+ed.getNR_Lote();
            if (ed.getOid_Tipo_Documento() > 0)
                sql += "   AND Lotes_Recebimentos.oid_Tipo_Documento = "+ed.getOid_Tipo_Documento();
            if (doValida(ed.getOid_Conta_Corrente()))
                sql += "   AND Lotes_Recebimentos.oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'";
            if (doValida(ed.getDM_Situacao()))
                sql += "   AND Lotes_Recebimentos.DM_Situacao = '"+ed.getDM_Situacao()+"'";
            if (ed.getVL_Lote() > 0)
                sql += "   AND Lotes_Recebimentos.VL_Lote = '"+ed.getVL_Lote()+"'";
            //*** Data da Emissão
            if (doValida(ed.getDT_Emissao()) && doValida(ed.getDT_Emissao_Final())) {
                sql +=" AND Lotes_Recebimentos.DT_Emissao BETWEEN '"+ed.getDT_Emissao()+"' AND '"+ed.getDT_Emissao_Final()+"'";
            } else if (doValida(ed.getDT_Emissao()) || doValida(ed.getDT_Emissao_Final())) {
                sql +=" AND Lotes_Recebimentos.DT_Emissao = '"+(doValida(ed.getDT_Emissao()) ? ed.getDT_Emissao() : ed.getDT_Emissao_Final())+"'";
            }
            //*** Data da Programada
            if (doValida(ed.getDT_Programada()) && doValida(ed.getDT_Programada_Final())) {
                sql +=" AND Lotes_Recebimentos.DT_Programada BETWEEN '"+ed.getDT_Programada()+"' AND '"+ed.getDT_Programada_Final()+"'";
            } else if (doValida(ed.getDT_Programada()) || doValida(ed.getDT_Programada_Final())) {
                sql +=" AND Lotes_Recebimentos.DT_Programada = '"+(doValida(ed.getDT_Programada()) ? ed.getDT_Programada() : ed.getDT_Programada_Final())+"'";
            }
            //*** Data da Compensacao
            if (doValida(ed.getDT_Compensacao()) && doValida(ed.getDT_Compensacao_Final())) {
                sql +=" AND Lotes_Recebimentos.DT_Compensacao BETWEEN '"+ed.getDT_Compensacao()+"' AND '"+ed.getDT_Compensacao_Final()+"'";
            } else if (doValida(ed.getDT_Compensacao()) || doValida(ed.getDT_Compensacao_Final())) {
                sql +=" AND Lotes_Recebimentos.DT_Compensacao = '"+(doValida(ed.getDT_Compensacao()) ? ed.getDT_Compensacao() : ed.getDT_Compensacao_Final())+"'";
            }
            sql += " ORDER BY Lotes_Recebimentos.DT_Programada";
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Lote_RecebimentoED edVolta = new Lote_RecebimentoED();
          
                edVolta.setOid_Lote_Recebimento(res.getInt("oid_Lote_Recebimento"));
                edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
                edVolta.setNR_Lote(res.getInt("NR_Lote"));
                edVolta.setVL_Lote(res.getDouble("VL_Lote"));
                edVolta.setOid_Conta_Corrente(res.getString("oid_Conta_Corrente"));
                edVolta.setOid_Tipo_Documento(res.getInt("oid_Tipo_Documento"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDT_Emissao(FormataDataBean.getFormatDate(res.getString("DT_Emissao")));
                edVolta.setDT_Programada(FormataDataBean.getFormatDate(res.getString("DT_Programada")));
                edVolta.setDT_Compensacao(FormataDataBean.getFormatDate(res.getString("DT_Compensacao")));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                
                //*** Carrega Dados
                if (doValida(edVolta.getOid_Conta_Corrente()) && ed.isCarregaCCorrente())
                    edVolta.edConta_Corrente = new Conta_CorrenteBD(executasql).getByRecord(new Conta_CorrenteED(edVolta.getOid_Conta_Corrente()));
                if (edVolta.getOid_Unidade() > 0 && ed.isCarregaUnidade())
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade());
                if (edVolta.getOid_Tipo_Documento() > 0 && ed.isCarregaTipoDoc())
                    edVolta.edTipoDoc = Tipo_DocumentoBean.getByOID(edVolta.getOid_Tipo_Documento());
                
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
        return list;
    }

    public Lote_RecebimentoED getByRecord(Lote_RecebimentoED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Lote_RecebimentoED) iterator.next();
        } else return new Lote_RecebimentoED();
    }
    
    /** ------------ RELATÓRIOS ---------------- */ 
    //*** Lotes Recebimentos
    public void relLoteRecebimento(RelatorioED ed) throws Excecoes {

        ArrayList lista = new ArrayList();
        int lastOID = 0;
        try {
            //*** Busca Lotes
            Lote_RecebimentoED edLote = new Lote_RecebimentoED();
            ArrayList listaLotesCheques = new Lote_ChequeBD(executasql).lista(new Lote_ChequeED(getValueDef(ed.getDm_situacao(),"F"), 0, ed.getOid_lote_recebimento()));
            for (int i=0; i<listaLotesCheques.size(); i++)
            {
                Lote_ChequeED edLoteCheque = (Lote_ChequeED) listaLotesCheques.get(i);
                if (lastOID != edLoteCheque.getOid_Lote_Recebimento())
                    edLote = this.getByRecord(new Lote_RecebimentoED(edLoteCheque.getOid_Lote_Recebimento(), true, true, false));
                lastOID = edLoteCheque.getOid_Lote_Recebimento();
                
                Cheque_ClienteED edCheque = new Cheque_ClienteED(edLoteCheque.getOid_Cheque_Cliente(), true, false, true, false, false, false);
                edCheque = new Cheque_ClienteBD(executasql).getByRecord(edCheque);
                
                RelatorioED edRel = new RelatorioED();
                //*** Lote
                edRel.setOid_lote_recebimento(edLote.getOid_Lote_Recebimento());
                edRel.setNr_lote_recebimento(edLote.getNR_Lote());
                edRel.setVl_lote(edLote.getVL_Lote());
                edRel.setCd_banco_lote(edLote.edConta_Corrente.getCd_Banco());
                edRel.setNr_agencia_lote(edLote.edConta_Corrente.getNr_Agencia());
                edRel.setNr_conta_lote(edLote.edConta_Corrente.getNr_Conta_Corrente());
                edRel.setNm_tipo_documento(edLote.edTipoDoc.getNM_Tipo_Documento());
                edRel.setNm_situacao(edLote.getDescDM_Situacao());
                
                //*** Cheques
                edRel.setOid_cheque_cliente(edCheque.getOid_Cheque_Cliente());
                edRel.setDt_cheque(edCheque.getDT_Programado());
                edRel.setDia_semana(Data.getDiaSemana(edRel.getDt_cheque()));
                edRel.setNr_cheque(edCheque.getNR_Cheque());
                edRel.setCd_banco(edCheque.edBanco.getCD_Banco());
                edRel.setNr_agencia(edCheque.getNR_Agencia());
                edRel.setNr_conta(edCheque.getNR_Conta());
                edRel.setVl_cheque(edCheque.getVL_Cheque());
                edRel.setDm_situacao(edCheque.getDescSituacao());

                lista.add(edRel);
            }
            //*** ORDENA CHEQUES por DATA
            Collections.sort(lista, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return ((RelatorioED)o1).getDt_cheque().compareTo(((RelatorioED)o2).getDt_cheque());
                }
            });
            
            ed.setLista(lista);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "relLoteRecebimento()");
        }
        //*** Chama o Gerador de Relatórios Jasper
        new JasperRL(ed).geraRelatorio();
    }
}