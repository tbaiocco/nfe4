package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import com.master.ed.Cheque_ClienteED;
import com.master.ed.Documento_EntregaED;
import com.master.ed.EntregaED;
import com.master.ed.EntregadorED;
import com.master.ed.RelatorioED;
import com.master.rl.JasperRL;
import com.master.root.ClienteBean;
import com.master.root.FormataDataBean;
import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andr� Valadas
 */
public class EntregaBD extends BancoUtil {

    private ExecutaSQL executasql;

    public EntregaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public EntregaED inclui(EntregaED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Entrega(getAutoIncremento("oid_Entrega", "Entregas"));
            ed.setNR_Entrega(getMaximo("NR_PROXIMA_ENTREGA",
	  	               				   "Parametros_Filiais",
	  	               				   "oid_Unidade = "+ ed.getOid_Unidade()));	
            
            sql = " INSERT INTO Entregas (" +
            	  "		 oid_Entrega" +
            	  "		,oid_Unidade" +
            	  "		,oid_Entregador" +
            	  "		,oid_Veiculo" +
            	  "		,NR_Entrega" +
            	  "		,DM_Situacao" +
            	  "		,DT_Entrega) " +
            	  " VALUES (" +
            	  	ed.getOid_Entrega() +
            	  	"," + ed.getOid_Unidade() +
            	  	"," + ed.getOid_Entregador() +
            	  	",'" + ed.getOid_Veiculo() + "'" +
            	  	"," + ed.getNR_Entrega() +
            	  	",'" + ed.getDM_Situacao() + "'" +
            	  	",'"+ ed.getDT_Entrega() + "')";
            
            executasql.executarUpdate(sql);
            //*** Atualiza Parametros_Filiais
            executasql.executarUpdate(" UPDATE Parametros_Filiais SET" +
            						  " NR_PROXIMA_ENTREGA = " + (ed.getNR_Entrega()+1) + 
            						  " WHERE oid_Unidade = "  + ed.getOid_Unidade());
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(EntregaED ed) throws Excecoes {

        String sql = null;
        
        try {
            sql =  " UPDATE Entregas SET ";
            sql += " 	 VL_Vendas = " + ed.getVL_Vendas();
            sql += " 	,VL_Cobranca = " + ed.getVL_Cobranca();
            sql += " 	,VL_Devolucao_Cancelamento = " + ed.getVL_Devolucao_Cancelamento();
            sql += " 	,VL_Dinheiro = " + ed.getVL_Dinheiro();
            sql += " 	,VL_Moeda = " + ed.getVL_Moeda();
            sql += " 	,VL_Cheque = " + ed.getVL_Cheque();
            sql += " 	,VL_Vale = " + ed.getVL_Vale();
            sql += " 	,VL_Saldo = " + ed.getVL_Saldo();
            sql += " WHERE oid_Entrega = " + ed.getOid_Entrega();
            
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(EntregaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Entregas " +
            	  " WHERE oid_Entrega = " + ed.getOid_Entrega();
            executasql.executarUpdate(sql);
            
            //*** Exclui Cheques das Entregas
            sql = " DELETE FROM Cheques_Clientes " +
                  " WHERE oid_Entrega = " + ed.getOid_Entrega();
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(EntregaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Entregas " +
            	  " WHERE Entregas.oid_Unidade = Unidades.oid_Unidade" +
            	  "   AND Entregas.oid_Entregador = Entregadores.oid_Entregador " +
            	  "   AND Entregas.oid_Veiculo = Veiculos.oid_Veiculo";
            	  
            if (ed.getOid_Entrega() > 0)
                sql += "   AND Entregas.oid_Entrega = "+ed.getOid_Entrega();
            if (ed.getOid_Unidade() > 0)
                sql += "   AND Entregas.oid_Unidade = "+ed.getOid_Unidade();
            if (ed.getOid_Entregador() > 0)
                sql += "   AND Entregas.oid_Entregador = "+ed.getOid_Entregador();
            if (doValida(ed.getOid_Veiculo()))
                sql += "   AND Entregas.oid_Veiculo = '"+ed.getOid_Veiculo()+"'";
            if (doValida(ed.getDM_Situacao()))
                sql += "   AND Entregas.DM_Situacao = '"+ed.getDM_Situacao()+"'";
            if (ed.getNR_Entrega() > 0)
                sql += "   AND Entregas.NR_Entrega = "+ed.getNR_Entrega();
            //*** Data da Entrega
            if (doValida(ed.getDT_Entrega()) && doValida(ed.getDT_Entrega_Final())) {
                sql +=" AND Entregas.DT_Entrega BETWEEN '"+ed.getDT_Entrega()+"' AND '"+ed.getDT_Entrega_Final()+"'";
            } else if (doValida(ed.getDT_Entrega()) || doValida(ed.getDT_Entrega_Final())) {
                sql +=" AND Entregas.DT_Entrega = '"+(doValida(ed.getDT_Entrega()) ? ed.getDT_Entrega() : ed.getDT_Entrega_Final())+"'";
            }
            //*** Data da Acerto
            if (doValida(ed.getDT_Acerto()) && doValida(ed.getDT_Acerto_Final())) {
                sql +=" AND Entregas.DT_Acerto BETWEEN '"+ed.getDT_Acerto()+"' AND '"+ed.getDT_Acerto_Final()+"'";
            } else if (doValida(ed.getDT_Acerto()) || doValida(ed.getDT_Acerto_Final())) {
                sql +=" AND Entregas.DT_Acerto = '"+(doValida(ed.getDT_Acerto()) ? ed.getDT_Acerto() : ed.getDT_Acerto_Final())+"'";
            }
            sql += " ORDER BY Entregas.oid_Entregador, Entregas.NR_Entrega";
            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                EntregaED edVolta = new EntregaED();
          
                edVolta.setOid_Entrega(res.getInt("oid_Entrega"));
                edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
                edVolta.setOid_Entregador(res.getInt("oid_Entregador"));
                edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
                //*** Carrega Dados
                if (edVolta.getOid_Unidade() > 0 && ed.isCarregaUnidade())
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade());
                if (edVolta.getOid_Entregador() > 0 && ed.isCarregaEntregador())
                    edVolta.edEntregador = new EntregadorBD(executasql).getByRecord(new EntregadorED(edVolta.getOid_Entregador()));
                if (doValida(edVolta.getOid_Veiculo()) && ed.isCarregaVeiculo())
                {   
                    edVolta.edVeiculo.setOID(edVolta.getOid_Veiculo());
                    edVolta.edVeiculo = VeiculoBean.getByRecord(edVolta.edVeiculo);
                }
                
                edVolta.setNR_Entrega(res.getInt("NR_Entrega"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDT_Entrega(FormataDataBean.getFormatDate(res.getString("DT_Entrega")));
                edVolta.setDT_Acerto(FormataDataBean.getFormatDate(res.getString("DT_Acerto")));
                
                edVolta.setVL_Vendas(res.getDouble("VL_Vendas"));
                edVolta.setVL_Cobranca(res.getDouble("VL_Cobranca"));
                edVolta.setVL_Devolucao_Cancelamento(res.getDouble("VL_Devolucao_Cancelamento"));
                edVolta.setVL_Dinheiro(res.getDouble("VL_Dinheiro"));
                edVolta.setVL_Moeda(res.getDouble("VL_Moeda"));
                edVolta.setVL_Cheque(res.getDouble("VL_Cheque"));
                edVolta.setVL_Vale(res.getDouble("VL_Vale"));
                edVolta.setVL_Saldo(res.getDouble("VL_Saldo"));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
        return list;
    }
        
    public EntregaED getByRecord(EntregaED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (EntregaED) iterator.next();            
        } else return new EntregaED();
    }
    
    //*** Atribui Valor dos Cheques informados
    public void calculaCheque(EntregaED ed) throws Excecoes {

        try {
            if (ed.getOid_Entrega() < 1)
                throw new Excecoes("Entrega n�o informada!");
            
            this.executasql.executarUpdate(" UPDATE Entregas SET " +
                                           "     VL_Cheque = (SELECT sum(VL_Cheque) FROM Cheques_Clientes WHERE Cheques_Clientes.oid_Entrega = "+ed.getOid_Entrega()+")" +
                                           " WHERE oid_Entrega = "+ed.getOid_Entrega());
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), 
                    "calculaCheque()");
        }
    }
    
    //*** Finaliza Entrega setando os Documentos "Em Rota" como "Recebido Sem Canhoto" == "E"
    public void finalizaAcerto(EntregaED ed) throws Excecoes {

        try {
            if (ed.getOid_Entrega() < 1)
                throw new Excecoes("Entrega n�o informada!");
            
            Documento_EntregaED edDoc = new Documento_EntregaED();
            edDoc.setOid_Entrega(ed.getOid_Entrega());
            //*** Finaliza Documentos EM ROTA setando-os como RECEBIDO SEM CANHOTO(E)
            new Documento_EntregaBD(executasql).finalizaDocumentos(edDoc);
        
            //*** Finaliza ENTREGA
            this.executasql.executarUpdate(" UPDATE Entregas SET " +
            							   "	 DM_Situacao = 'F'" +
            							   "	,DT_Acerto = '"+Data.getDataYMD()+"'" +
            							   " WHERE oid_Entrega = "+ed.getOid_Entrega());
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), 
                    "finalizaAcerto()");
        }
    }
    
    /** ------------ RELAT�RIOS ---------------- */	
    //*** Acerto de Entregas
    public void relAcertoEntrega(RelatorioED ed) throws Excecoes {

        ArrayList lista = new ArrayList();
        ArrayList subLista = new ArrayList();
        ArrayList subLista2 = new ArrayList();
        
        try {
            //*** Variaveis Auxiliares
            int qt_Avista = 0, qt_Aprazo = 0, qt_Cheque = 0;
            double vl_Avista = 0, vl_Aprazo = 0, vl_Cheque = 0,
                   vlDinheiro = 0, vlMoeda = 0, vlVale = 0;
            
            Documento_EntregaED filter = new Documento_EntregaED();
            filter.setOid_Entrega(ed.getOid_entrega());
            filter.edEntrega.setDM_Situacao(ed.getDm_situacao());
            
            ArrayList listaDoc = new Documento_EntregaBD(executasql).lista(filter); 
            for (int i=0; i<listaDoc.size(); i++)
            {
                Documento_EntregaED edDoc = (Documento_EntregaED) listaDoc.get(i);
                RelatorioED edRel = new RelatorioED();
                
                edRel.setNr_acerto(edDoc.edEntrega.getNR_Entrega());
                edRel.setDt_acerto(edDoc.edEntrega.getDT_Acerto());

          	  	edRel.setOid_entregador(edDoc.edEntrega.edEntregador.getOid_Entregador());
          	  	edRel.setCd_entregador(edDoc.edEntrega.edEntregador.getCD_Entregador());
                edRel.setNm_entregador(edDoc.edEntrega.edEntregador.getNM_Entregador());
                
                edRel.setNr_placa(edDoc.edEntrega.edVeiculo.getNR_Placa());
                edRel.setNm_canhoto(edDoc.getDescDM_Situacao());

                edRel.setNr_nota_fiscal((int)edDoc.edNota.getNr_nota_fiscal());
                edRel.setVl_nota_fiscal(edDoc.edNota.getVl_liquido_nota_fiscal());
                
                ClienteBean edPes = ClienteBean.getByOID_Cliente(edDoc.edNota.getOid_pessoa());
                //PessoaED edPes = new PessoaBD(executasql).getByRecord(new PessoaED(edDoc.edNota.getOid_pessoa()));
                //edRel.setNr_cnpj_cpf(edPes.getNR_CNPJ_CPFFormatado());
                edRel.setNr_cnpj_cpf(getValueDef(edPes.getCD_Cliente_Palm(), "-------"));
                edRel.setNm_razao_social(edPes.getNM_Razao_Social());
                
                edRel.setCd_vendedor(getTableStringValue("CD_Vendedor","Vendedores","oid_Vendedor = '"+edDoc.edNota.getOid_pessoa_fornecedor()+"'"));
                
                //*** Monta Condi��o de Pagamento
                //*** 0 == A VISTA
                
                //*** Cheque
                if ("2".equals(edRel.getDm_forma_pagamento()))
                {
                    if (!"D".equals(edDoc.getDM_Situacao()))
                    {
                        ++qt_Cheque;
                        vl_Cheque += edRel.getVl_nota_fiscal();
                    }
                    edRel.setVl_cheque(edRel.getVl_nota_fiscal());
                    edRel.setNm_condicao_pagamento(edDoc.getDescDM_Forma_Recebimento());
                //*** A Prazo
                } else 
                if (!"1".equals(edRel.getCd_condicao_pagamento()))
                {
                    if (!"D".equals(edDoc.getDM_Situacao()))
                    {
                        ++qt_Aprazo;
                        vl_Aprazo += edRel.getVl_nota_fiscal();
                    }
                    edRel.setVl_aprazo(edRel.getVl_nota_fiscal());
                    edRel.setNm_condicao_pagamento("A Prazo "+edDoc.getDescDM_Forma_Recebimento());
                //*** A Vista
                } else {
                    if (!"D".equals(edDoc.getDM_Situacao()) && !"C".equals(edDoc.getDM_Situacao()))
                    {
                        ++qt_Avista;
                        vl_Avista += edRel.getVl_nota_fiscal();
                    }
                    edRel.setVl_avista(edRel.getVl_nota_fiscal());
                    edRel.setNm_condicao_pagamento("A Vista "+edDoc.getDescDM_Forma_Recebimento());
                }
                
                //*** Carrega lista com NFs de Venda Direta
                if ("D".equals(edDoc.edNota.getDm_tipo_nota_fiscal()))
                {
                    edRel.setCd_fornecedor(edDoc.edNota.getOid_pessoa_destinatario());
                    edRel.setNm_fornecedor(getTableStringValue("NM_Razao_Social", "Pessoas", "oid_Pessoa = '"+edDoc.edNota.getOid_pessoa_destinatario()+"'"));
                    subLista.add(edRel);
                } else lista.add(edRel);
                
                vlDinheiro = edDoc.edEntrega.getVL_Dinheiro();
                vlMoeda = edDoc.edEntrega.getVL_Moeda();
                vlVale = edDoc.edEntrega.getVL_Vale();
            }
            //*** Busca Rela��o de Cheques
            Cheque_ClienteED edCheque = new Cheque_ClienteED(true, false, false, false, false, false);
            edCheque.setOid_Entrega(ed.getOid_entrega());
            ArrayList listaCheques = new Cheque_ClienteBD(executasql).lista(edCheque);
            if (listaCheques.size() > 0)
            {
                for (int i=0; i<listaCheques.size(); i++)
                {
                    Cheque_ClienteED edVolta = (Cheque_ClienteED) listaCheques.get(i);
                    RelatorioED edRel = new RelatorioED();
                    
                    edRel.setNr_cheque(edVolta.getNR_Cheque());
                    edRel.setCd_banco(edVolta.edBanco.getCD_Banco());
                    edRel.setNr_agencia(edVolta.getNR_Agencia());
                    edRel.setNr_conta(edVolta.getNR_Conta());
                    edRel.setDt_cheque(edVolta.getDT_Programado());
                    edRel.setVl_cheque(edVolta.getVL_Cheque());
                    
                    edRel.setVl_dinheiro(vlDinheiro);
                    edRel.setVl_moeda(vlMoeda);
                    edRel.setVl_vale(vlVale);
                    subLista2.add(edRel);
                }
            } else {
                RelatorioED edRel = new RelatorioED();
                edRel.setVl_dinheiro(vlDinheiro);
                edRel.setVl_moeda(vlMoeda);
                edRel.setVl_vale(vlVale);
                subLista2.add(edRel);
            }
            RelatorioED edListas = new RelatorioED();
            //*** Agrupa subLista a lista completa
            if (lista.size() > 0)
                edListas = (RelatorioED) lista.get(lista.size()-1);
            else if (subLista.size() > 0)
                edListas = (RelatorioED) subLista.get(subLista.size()-1);
            //*** Ordena NF VEnda Direta por Fornecedor
            Collections.sort(subLista, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return (((RelatorioED)o1).getCd_fornecedor().compareTo(((RelatorioED)o2).getCd_fornecedor()));
                }
            });
            //*** Ordena CHEQUES por Data
            Collections.sort(subLista2, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return (((RelatorioED)o1).getDt_cheque().compareTo(((RelatorioED)o2).getDt_cheque()));
                }
            });
            edListas.setSublista(subLista);
            edListas.setSublista2(subLista2);
            if (lista.size() > 0)
                lista.set(lista.size()-1, edListas);
            else lista.add(edListas);

            ed.setLista(lista);
            //*** Seta Filtro e Variaveis
            HashMap map = new HashMap();
            map.put("QT_AVISTA", new Integer(qt_Avista));
            map.put("QT_APRAZO", new Integer(qt_Aprazo));
            map.put("QT_CHEQUE", new Integer(qt_Cheque));
            map.put("VL_AVISTA", new Double(vl_Avista));
            map.put("VL_APRAZO", new Double(vl_Aprazo));
            map.put("VL_CHEQUE", new Double(vl_Cheque));
            
            ed.setHashMap(map);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "relAcertoEntrega()");
        }
        //*** Chama o Gerador de Relat�rios Jasper
        new JasperRL(ed).geraRelatorio();
    }
}