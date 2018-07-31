package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.BancoED;
import com.master.ed.Cheque_ClienteED;
import com.master.ed.Conta_CorrenteED;
import com.master.ed.EntregaED;
import com.master.ed.Lote_ChequeED;
import com.master.ed.Motivo_DevolucaoED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.root.ClienteBean;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Cheques Clientes
 * @serialData 04/04/2005
 */
public class Cheque_ClienteBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Cheque_ClienteBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Cheque_ClienteED inclui(Cheque_ClienteED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Cheque_Cliente(getAutoIncremento("oid_Cheque_Cliente", "Cheques_Clientes"));
            
            sql = " INSERT INTO Cheques_Clientes (" +
            	  "		 oid_Cheque_Cliente" +
            	  "		,oid_Cliente" +
            	  "		,oid_Nota_Fiscal" +
            	  "		,oid_Entrega" +
            	  "		,oid_Banco" +
            	  "		,NM_Pessoa_Emissor" +
                  "     ,NR_Comp" +
            	  "		,NR_Agencia" +
                  "     ,NR_C1" +
            	  "		,NR_Conta" +
                  "     ,NR_C2" +
            	  "		,NR_Cheque" +
                  "     ,NR_C3" +
            	  "		,DT_Programado" +
            	  "		,VL_Cheque" +
          		  "		,VL_Saldo" +
            	  "		,DM_Origem" +
                  "     ,oid_Motivo_Devolucao" +
                  "     ,oid_Conta_Corrente_Unidade" +
                  "     ,DM_Situacao" +
                  "     ,DM_Apresentacao" +
            	  "		,DT_Entrada) " +
            	  " VALUES (" +
            	  	ed.getOid_Cheque_Cliente() +
            	  	",'" + ed.getOid_Cliente() + "'" +
            	  	",'" + ed.getOid_Nota_Fiscal() + "'" +
            	  	"," + ed.getOid_Entrega() +
            	  	"," + ed.getOid_Banco() +
            	  	",'" + ed.getNM_Pessoa_Emissor() + "'" +
                    ",'" + JavaUtil.LFill(ed.getNR_Comp(), 3, true) + "'" +
            	  	",'" + JavaUtil.LFill(ed.getNR_Agencia(), 4, true) + "'" +
                    ",'" + getValueDef(ed.getNR_C1(),"0") + "'" +
            	  	",'" + JavaUtil.LFill(ed.getNR_Conta(), 10, true) + "'" +
                    ",'" + getValueDef(ed.getNR_C2(),"0") + "'" +
            	  	",'" + JavaUtil.LFill(ed.getNR_Cheque(), 6, true) + "'" +
                    ",'" + getValueDef(ed.getNR_C3(),"0") + "'" +
            	  	",'" + ed.getDT_Programado() + "'" +
            	  	"," + ed.getVL_Cheque() +
          			"," + ed.getVL_Cheque () +
            	  	",'" + ed.getDM_Origem() + "'" +
                    "," + ed.getOid_Motivo_Devolucao()+
                    ",'" + ed.getOid_Conta_Corrente_Unidade() + "'" +
                    ",'" + getValueDef(ed.getDM_Situacao(), "N") + "'" +
                    ",'" + getValueDef(ed.getDM_Apresentacao(), "S") + "'" +
            	  	",'"+ ed.getDT_Entrada() + "')";
            executasql.executarUpdate(sql);
            
            //*** Atribui valor do Cheque ao Acerto do Entregador
            if (ed.getOid_Entrega() > 0)
                new EntregaBD(executasql).calculaCheque(new EntregaED(ed.getOid_Entrega()));
            //*** Inclusão apartir de um Lote de Recebimento
            if (ed.getOid_Lote_Recebimento() > 0)
            {
                new Lote_ChequeBD(executasql).inclui(new Lote_ChequeED(getValueDef(ed.getDM_Situacao(), "N")
                                                                      ,ed.getOid_Cheque_Cliente()
                                                                      ,ed.getOid_Lote_Recebimento()));
                ed.setOid_Conta_Corrente(getTableStringValue("oid_Conta_Corrente","Lotes_Recebimentos","oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento()));
                executasql.executarUpdate(" UPDATE Cheques_Clientes SET" +
                                          "    oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'" +
                                          " WHERE oid_Cheque_Cliente = "+ed.getOid_Cheque_Cliente());
            }
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Cheque_ClienteED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Cheques_Clientes SET ";
            sql += " 	 oid_Banco = " + ed.getOid_Banco();
            sql += " 	,NM_Pessoa_Emissor = '" + ed.getNM_Pessoa_Emissor() +"'";
            sql += "    ,NR_Comp = '" + JavaUtil.LFill(ed.getNR_Comp(), 3, true) +"'";
            sql += " 	,NR_Agencia = '" + JavaUtil.LFill(ed.getNR_Agencia(), 4, true) +"'";
            sql += "    ,NR_C1 = '" + getValueDef(ed.getNR_C1(),"0") +"'";
            sql += " 	,NR_Conta = '" + JavaUtil.LFill(ed.getNR_Conta(), 10, true) +"'";
            sql += "    ,NR_C2 = '" + getValueDef(ed.getNR_C2(),"0") +"'";
            sql += " 	,NR_Cheque = '" + JavaUtil.LFill(ed.getNR_Cheque(), 6, true) +"'";
            sql += "    ,NR_C3 = '" + getValueDef(ed.getNR_C3(),"0") +"'";
            sql += " 	,DT_Programado = '" + ed.getDT_Programado() +"'";
            sql += "    ,oid_Conta_Corrente_Unidade = '" + ed.getOid_Conta_Corrente_Unidade() +"'";
            sql += " 	,VL_Cheque = " + ed.getVL_Cheque();
            sql += " 	,DM_Origem = '" + ed.getDM_Origem() +"'";
            sql += " WHERE oid_Cheque_Cliente = " + ed.getOid_Cheque_Cliente();
            
            executasql.executarUpdate(sql);
            
            //*** Altera valor do Cheque ao Acerto do Entregador
            if (ed.getOid_Entrega() > 0)
                new EntregaBD(executasql).calculaCheque(new EntregaED(ed.getOid_Entrega()));
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Cheque_ClienteED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Cheques_Clientes " +
            	  " WHERE oid_Cheque_Cliente = " + ed.getOid_Cheque_Cliente();
            
            executasql.executarUpdate(sql);
            
            //*** Diminui valor do Cheque no Acerto do Entregador
            if (ed.getOid_Entrega() > 0)
                new EntregaBD(executasql).calculaCheque(new EntregaED(ed.getOid_Entrega()));
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }
    
    public ArrayList lista(Cheque_ClienteED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT DISTINCT Cheques_Clientes.*" +
                  //"       ,Lotes_Cheques.oid_Lote_Recebimento" +
            	  " FROM Cheques_Clientes " +
                  //"     LEFT JOIN Lotes_Cheques " +
                  //"         ON Cheques_Clientes.oid_Cheque_Cliente = Lotes_Cheques.oid_Cheque_Cliente " +
            	  " WHERE Cheques_Clientes.oid_Cliente = Clientes.oid_Cliente";
            	  
            if (ed.getOid_Cheque_Cliente() > 0)
                sql += "   AND Cheques_Clientes.oid_Cheque_Cliente = "+ed.getOid_Cheque_Cliente();
            if (doValida(ed.getOid_Cliente()))
                sql += "   AND Cheques_Clientes.oid_Cliente = '"+ed.getOid_Cliente()+"'";
            if (doValida(ed.getOid_Nota_Fiscal()))
                sql += "   AND Cheques_Clientes.oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"'" +
                       "   AND Cheques_Clientes.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal";
            if (ed.getOid_Entrega() > 0)
                sql += "   AND Cheques_Clientes.oid_Entrega = "+ed.getOid_Entrega()+
                       "   AND Cheques_Clientes.oid_Entrega = Entregas.oid_Entrega";
            if (doValida(ed.getOid_Conta_Corrente()))
                sql += "   AND Cheques_Clientes.oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'";
            if (doValida(ed.getOid_Conta_Corrente_Unidade()))
                sql += "   AND Cheques_Clientes.oid_Conta_Corrente_Unidade = '"+ed.getOid_Conta_Corrente_Unidade()+"'";
            if (ed.getOid_Banco() > 0)
                sql += "   AND Cheques_Clientes.oid_Banco = "+ed.getOid_Banco();
            if (doValida(ed.getNM_Pessoa_Emissor()))
                sql += "   AND Cheques_Clientes.NM_Pessoa_Emissor LIKE '"+ed.getNM_Pessoa_Emissor()+"%'";
            if (doValida(ed.getDM_Origem()))
                sql += "   AND Cheques_Clientes.DM_Origem = '"+ed.getDM_Origem()+"'";
            if (doValida(ed.getDM_Situacao()))
                sql += "   AND Cheques_Clientes.DM_Situacao = '"+ed.getDM_Situacao()+"'";
            if (ed.getOid_Motivo_Devolucao() > 0)
                sql += "   AND Cheques_Clientes.oid_Motivo_Devolucao = "+ed.getOid_Motivo_Devolucao();
            if (doValida(ed.getDM_Apresentacao()))
                sql += "   AND Cheques_Clientes.DM_Apresentacao = '"+ed.getDM_Apresentacao()+"'";
            if (ed.getVL_Cheque() > 0)
                sql += "   AND Cheques_Clientes.VL_Cheque = '"+ed.getVL_Cheque()+"'";
            if (doValida(ed.getNR_Cheque()))
                sql += "   AND Cheques_Clientes.NR_Cheque LIKE '%"+ed.getNR_Cheque()+"%'";
            //*** Filtra Cheques q não estejam nos Lotes
            if (ed.isSemLote())
                sql += "   AND Lotes_Cheques.oid_Cheque_Cliente = Cheques_Clientes.oid_Cheque_Cliente" +
                       "   AND ((Lotes_Cheques.oid_Lote_Recebimento IS NULL OR Lotes_Cheques.oid_Lote_Recebimento < 1) OR (Cheques_Clientes.oid_Conta_Corrente IS NULL AND Cheques_Clientes.DM_Situacao <> 'F'))";
            else if (ed.getOid_Lote_Recebimento() > 0)
                sql += "   AND Lotes_Cheques.oid_Cheque_Cliente = Cheques_Clientes.oid_Cheque_Cliente" +
                       "   AND Lotes_Cheques.oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento();
                
            //*** Filtra Cheques q não estejam nas Entregas
            if (ed.isSemEntrega())
                sql += "   AND (Cheques_Clientes.oid_Entrega IS NULL OR Cheques_Clientes.oid_Entrega < 1) ";
            //*** Data da Entrada
            if (doValida(ed.getDT_Entrada()) && doValida(ed.getDT_Entrada_Final())) {
                sql +=" AND Cheques_Clientes.DT_Entrada BETWEEN '"+ed.getDT_Entrada()+"' AND '"+ed.getDT_Entrada_Final()+"'";
            } else if (doValida(ed.getDT_Entrada()) || doValida(ed.getDT_Entrada_Final())) {
                sql +=" AND Cheques_Clientes.DT_Entrada = '"+(doValida(ed.getDT_Entrada()) ? ed.getDT_Entrada() : ed.getDT_Entrada_Final())+"'";
            }
            //*** Data da Programado
            if (doValida(ed.getDT_Programado()) && doValida(ed.getDT_Programado_Final())) {
                sql +=" AND Cheques_Clientes.DT_Programado BETWEEN '"+ed.getDT_Programado()+"' AND '"+ed.getDT_Programado_Final()+"'";
            } else if (doValida(ed.getDT_Programado()) || doValida(ed.getDT_Programado_Final())) {
                sql +=" AND Cheques_Clientes.DT_Programado = '"+(doValida(ed.getDT_Programado()) ? ed.getDT_Programado() : ed.getDT_Programado_Final())+"'";
            }
            //*** Data da Compensacao
            if (doValida(ed.getDT_Compensacao()) && doValida(ed.getDT_Compensacao_Final())) {
                sql +=" AND Cheques_Clientes.DT_Compensacao BETWEEN '"+ed.getDT_Compensacao()+"' AND '"+ed.getDT_Compensacao_Final()+"'";
            } else if (doValida(ed.getDT_Compensacao()) || doValida(ed.getDT_Compensacao_Final())) {
                sql +=" AND Cheques_Clientes.DT_Compensacao = '"+(doValida(ed.getDT_Compensacao()) ? ed.getDT_Compensacao() : ed.getDT_Compensacao_Final())+"'";
            }
            //*** Data da 1º Apresentação
            if (doValida(ed.getDT_Apresentacao1()) && doValida(ed.getDT_Apresentacao1_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao1 BETWEEN '"+ed.getDT_Apresentacao1()+"' AND '"+ed.getDT_Apresentacao1_Final()+"'";
            } else if (doValida(ed.getDT_Apresentacao1()) || doValida(ed.getDT_Apresentacao1_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao1 = '"+(doValida(ed.getDT_Apresentacao1()) ? ed.getDT_Apresentacao1() : ed.getDT_Apresentacao1_Final())+"'";
            }
            //*** Data da 2º Apresentação
            if (doValida(ed.getDT_Apresentacao2()) && doValida(ed.getDT_Apresentacao2_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao2 BETWEEN '"+ed.getDT_Apresentacao2()+"' AND '"+ed.getDT_Apresentacao2_Final()+"'";
            } else if (doValida(ed.getDT_Apresentacao2()) || doValida(ed.getDT_Apresentacao2_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao2 = '"+(doValida(ed.getDT_Apresentacao2()) ? ed.getDT_Apresentacao2() : ed.getDT_Apresentacao2_Final())+"'";
            }
            //*** Data da 3º Apresentação
            if (doValida(ed.getDT_Apresentacao3()) && doValida(ed.getDT_Apresentacao3_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao3 BETWEEN '"+ed.getDT_Apresentacao3()+"' AND '"+ed.getDT_Apresentacao3_Final()+"'";
            } else if (doValida(ed.getDT_Apresentacao3()) || doValida(ed.getDT_Apresentacao3_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao3 = '"+(doValida(ed.getDT_Apresentacao3()) ? ed.getDT_Apresentacao3() : ed.getDT_Apresentacao3_Final())+"'";
            }
            sql += " ORDER BY Cheques_Clientes.DT_Programado, Cheques_Clientes.NR_Cheque";
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next())
            {
                Cheque_ClienteED edVolta = new Cheque_ClienteED();
          
                edVolta.setOid_Cheque_Cliente(res.getInt("oid_Cheque_Cliente"));
                edVolta.setOid_Cliente(res.getString("oid_Cliente"));
                edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edVolta.setOid_Entrega(res.getInt("oid_Entrega"));
                edVolta.setOid_Banco(res.getInt("oid_Banco"));
                edVolta.setNM_Pessoa_Emissor(res.getString("NM_Pessoa_Emissor"));
                edVolta.setOid_Conta_Corrente(res.getString("oid_Conta_Corrente"));
                edVolta.setOid_Conta_Corrente_Unidade(res.getString("oid_Conta_Corrente_Unidade"));
                edVolta.setOid_Motivo_Devolucao(res.getInt("oid_Motivo_Devolucao"));
                
                //*** Carrega Dados
                if (doValida(edVolta.getOid_Cliente()) && ed.isCarregaCliente())
                    edVolta.edCliente = ClienteBean.getByOID_Cliente(edVolta.getOid_Cliente());
                if (doValida(edVolta.getOid_Nota_Fiscal()) && ed.isCarregaNota())
                    edVolta.edNota = new Nota_Fiscal_EletronicaBD(executasql).getByRecord(new Nota_Fiscal_EletronicaED(edVolta.getOid_Nota_Fiscal()));
                if (edVolta.getOid_Entrega() > 0 && ed.isCarregaEntrega())
                    edVolta.edEntrega = new EntregaBD(executasql).getByRecord(new EntregaED(edVolta.getOid_Entrega(), false, true, false));
                if (edVolta.getOid_Banco() > 0 && ed.isCarregaBanco())
                    edVolta.edBanco = new BancoBD(executasql).getByRecord(new BancoED(edVolta.getOid_Banco()));
                if (doValida(edVolta.getOid_Conta_Corrente()) && ed.isCarregaCCorrente())
                    edVolta.edConta_Corrente = new Conta_CorrenteBD(executasql).getByRecord(new Conta_CorrenteED(edVolta.getOid_Conta_Corrente()));
                if (edVolta.getOid_Motivo_Devolucao() > 0 && ed.isCarregaMotivoDev())
                    edVolta.edMotivo = new Motivo_DevolucaoBD(executasql).getByRecord(new Motivo_DevolucaoED(edVolta.getOid_Motivo_Devolucao()));
                
                edVolta.setNR_Comp(res.getString("NR_Comp"));
                edVolta.setNR_Agencia(res.getString("NR_Agencia"));
                edVolta.setNR_C1(res.getString("NR_C1"));
                edVolta.setNR_Conta(res.getString("NR_Conta"));
                edVolta.setNR_C2(res.getString("NR_C2"));
                edVolta.setNR_Cheque(res.getString("NR_Cheque"));
                edVolta.setNR_C3(res.getString("NR_C3"));
                edVolta.setDT_Programado(FormataDataBean.getFormatDate(res.getString("DT_Programado")));
                
                edVolta.setVL_Cheque(res.getDouble("VL_Cheque"));
                edVolta.setDM_Origem(res.getString("DM_Origem"));
                edVolta.setDT_Entrada(FormataDataBean.getFormatDate(res.getString("DT_Entrada")));
                
                edVolta.setDT_Apresentacao1(FormataDataBean.getFormatDate(res.getString("DT_Apresentacao1")));
                edVolta.setDT_Apresentacao2(FormataDataBean.getFormatDate(res.getString("DT_Apresentacao2")));
                edVolta.setDT_Apresentacao3(FormataDataBean.getFormatDate(res.getString("DT_Apresentacao3")));
                edVolta.setDT_Compensacao(FormataDataBean.getFormatDate(res.getString("DT_Compensacao")));

                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDM_Apresentacao(res.getString("DM_Apresentacao"));
                edVolta.setVL_Taxa(res.getDouble("VL_Taxa"));
                edVolta.setVL_Juro_Mora_Dia(res.getDouble("VL_Juro_Mora_Dia"));

                edVolta.setNR_Vale(res.getString("NR_Vale"));
                
                //edVolta.setOid_Lote_Recebimento(res.getInt("oid_Lote_Recebimento"));
                edVolta.setOid_Lote_Recebimento(getTableIntValue("count(*)","Lotes_Cheques","oid_Cheque_Cliente="+edVolta.getOid_Cheque_Cliente()));
        		edVolta.setVL_Saldo(this.saldoCheque(edVolta));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
        return list;
    }
    
    public ArrayList listaSemLote(Cheque_ClienteED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT DISTINCT Cheques_Clientes.*" +
                  " FROM Cheques_Clientes " +
                  " WHERE Cheques_Clientes.oid_Cliente = Clientes.oid_Cliente";
                  
            if (ed.getOid_Cheque_Cliente() > 0)
                sql += "   AND Cheques_Clientes.oid_Cheque_Cliente = "+ed.getOid_Cheque_Cliente();
            if (doValida(ed.getOid_Cliente()))
                sql += "   AND Cheques_Clientes.oid_Cliente = '"+ed.getOid_Cliente()+"'";
            if (doValida(ed.getOid_Nota_Fiscal()))
                sql += "   AND Cheques_Clientes.oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"'" +
                       "   AND Cheques_Clientes.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal";
            if (ed.getOid_Entrega() > 0)
                sql += "   AND Cheques_Clientes.oid_Entrega = "+ed.getOid_Entrega()+
                       "   AND Cheques_Clientes.oid_Entrega = Entregas.oid_Entrega";
            if (doValida(ed.getOid_Conta_Corrente()))
                sql += "   AND Cheques_Clientes.oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'";
            if (doValida(ed.getOid_Conta_Corrente_Unidade()))
                sql += "   AND Cheques_Clientes.oid_Conta_Corrente_Unidade = '"+ed.getOid_Conta_Corrente_Unidade()+"'";
            if (ed.getOid_Banco() > 0)
                sql += "   AND Cheques_Clientes.oid_Banco = "+ed.getOid_Banco();
            if (doValida(ed.getNM_Pessoa_Emissor()))
                sql += "   AND Cheques_Clientes.NM_Pessoa_Emissor LIKE '"+ed.getNM_Pessoa_Emissor()+"%'";
            if (doValida(ed.getDM_Origem()))
                sql += "   AND Cheques_Clientes.DM_Origem = '"+ed.getDM_Origem()+"'";
            if (doValida(ed.getDM_Situacao()))
                sql += "   AND Cheques_Clientes.DM_Situacao = '"+ed.getDM_Situacao()+"'";
            if (ed.getOid_Motivo_Devolucao() > 0)
                sql += "   AND Cheques_Clientes.oid_Motivo_Devolucao = "+ed.getOid_Motivo_Devolucao();
            if (doValida(ed.getDM_Apresentacao()))
                sql += "   AND Cheques_Clientes.DM_Apresentacao = '"+ed.getDM_Apresentacao()+"'";
            if (ed.getVL_Cheque() > 0)
                sql += "   AND Cheques_Clientes.VL_Cheque = '"+ed.getVL_Cheque()+"'";
            if (doValida(ed.getNR_Cheque()))
                sql += "   AND Cheques_Clientes.NR_Cheque LIKE '%"+ed.getNR_Cheque()+"%'";
            //*** Filtra Cheques q não estejam nos Lotes
            if (ed.isSemLote())
                sql += "   AND (Cheques_Clientes.oid_Conta_Corrente IS NULL AND Cheques_Clientes.DM_Situacao <> 'F')";
            //*** Filtra Cheques q não estejam nas Entregas
            if (ed.isSemEntrega())
                sql += "   AND (Cheques_Clientes.oid_Entrega IS NULL OR Cheques_Clientes.oid_Entrega < 1) ";
            //*** Data da Entrada
            if (doValida(ed.getDT_Entrada()) && doValida(ed.getDT_Entrada_Final())) {
                sql +=" AND Cheques_Clientes.DT_Entrada BETWEEN '"+ed.getDT_Entrada()+"' AND '"+ed.getDT_Entrada_Final()+"'";
            } else if (doValida(ed.getDT_Entrada()) || doValida(ed.getDT_Entrada_Final())) {
                sql +=" AND Cheques_Clientes.DT_Entrada = '"+(doValida(ed.getDT_Entrada()) ? ed.getDT_Entrada() : ed.getDT_Entrada_Final())+"'";
            }
            //*** Data da Programado
            if (doValida(ed.getDT_Programado()) && doValida(ed.getDT_Programado_Final())) {
                sql +=" AND Cheques_Clientes.DT_Programado BETWEEN '"+ed.getDT_Programado()+"' AND '"+ed.getDT_Programado_Final()+"'";
            } else if (doValida(ed.getDT_Programado()) || doValida(ed.getDT_Programado_Final())) {
                sql +=" AND Cheques_Clientes.DT_Programado = '"+(doValida(ed.getDT_Programado()) ? ed.getDT_Programado() : ed.getDT_Programado_Final())+"'";
            }
            //*** Data da Compensacao
            if (doValida(ed.getDT_Compensacao()) && doValida(ed.getDT_Compensacao_Final())) {
                sql +=" AND Cheques_Clientes.DT_Compensacao BETWEEN '"+ed.getDT_Compensacao()+"' AND '"+ed.getDT_Compensacao_Final()+"'";
            } else if (doValida(ed.getDT_Compensacao()) || doValida(ed.getDT_Compensacao_Final())) {
                sql +=" AND Cheques_Clientes.DT_Compensacao = '"+(doValida(ed.getDT_Compensacao()) ? ed.getDT_Compensacao() : ed.getDT_Compensacao_Final())+"'";
            }
            //*** Data da 1º Apresentação
            if (doValida(ed.getDT_Apresentacao1()) && doValida(ed.getDT_Apresentacao1_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao1 BETWEEN '"+ed.getDT_Apresentacao1()+"' AND '"+ed.getDT_Apresentacao1_Final()+"'";
            } else if (doValida(ed.getDT_Apresentacao1()) || doValida(ed.getDT_Apresentacao1_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao1 = '"+(doValida(ed.getDT_Apresentacao1()) ? ed.getDT_Apresentacao1() : ed.getDT_Apresentacao1_Final())+"'";
            }
            //*** Data da 2º Apresentação
            if (doValida(ed.getDT_Apresentacao2()) && doValida(ed.getDT_Apresentacao2_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao2 BETWEEN '"+ed.getDT_Apresentacao2()+"' AND '"+ed.getDT_Apresentacao2_Final()+"'";
            } else if (doValida(ed.getDT_Apresentacao2()) || doValida(ed.getDT_Apresentacao2_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao2 = '"+(doValida(ed.getDT_Apresentacao2()) ? ed.getDT_Apresentacao2() : ed.getDT_Apresentacao2_Final())+"'";
            }
            //*** Data da 3º Apresentação
            if (doValida(ed.getDT_Apresentacao3()) && doValida(ed.getDT_Apresentacao3_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao3 BETWEEN '"+ed.getDT_Apresentacao3()+"' AND '"+ed.getDT_Apresentacao3_Final()+"'";
            } else if (doValida(ed.getDT_Apresentacao3()) || doValida(ed.getDT_Apresentacao3_Final())) {
                sql +=" AND Cheques_Clientes.DT_Apresentacao3 = '"+(doValida(ed.getDT_Apresentacao3()) ? ed.getDT_Apresentacao3() : ed.getDT_Apresentacao3_Final())+"'";
            }
            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next())
            {
                Cheque_ClienteED edVolta = new Cheque_ClienteED();
          
                edVolta.setOid_Cheque_Cliente(res.getInt("oid_Cheque_Cliente"));
                edVolta.setOid_Cliente(res.getString("oid_Cliente"));
                edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edVolta.setOid_Entrega(res.getInt("oid_Entrega"));
                edVolta.setOid_Banco(res.getInt("oid_Banco"));
                edVolta.setNM_Pessoa_Emissor(res.getString("NM_Pessoa_Emissor"));
                edVolta.setOid_Conta_Corrente(res.getString("oid_Conta_Corrente"));
                edVolta.setOid_Conta_Corrente_Unidade(res.getString("oid_Conta_Corrente_Unidade"));
                edVolta.setOid_Motivo_Devolucao(res.getInt("oid_Motivo_Devolucao"));
                
                //*** Carrega Dados
                if (doValida(edVolta.getOid_Cliente()) && ed.isCarregaCliente())
                    edVolta.edCliente = ClienteBean.getByOID_Cliente(edVolta.getOid_Cliente());
                if (doValida(edVolta.getOid_Nota_Fiscal()) && ed.isCarregaNota())
                    edVolta.edNota = new Nota_Fiscal_EletronicaBD(executasql).getByRecord(new Nota_Fiscal_EletronicaED(edVolta.getOid_Nota_Fiscal()));
                if (edVolta.getOid_Entrega() > 0 && ed.isCarregaEntrega())
                    edVolta.edEntrega = new EntregaBD(executasql).getByRecord(new EntregaED(edVolta.getOid_Entrega()));
                if (edVolta.getOid_Banco() > 0 && ed.isCarregaBanco())
                    edVolta.edBanco = new BancoBD(executasql).getByRecord(new BancoED(edVolta.getOid_Banco()));
                if (doValida(edVolta.getOid_Conta_Corrente()) && ed.isCarregaCCorrente())
                    edVolta.edConta_Corrente = new Conta_CorrenteBD(executasql).getByRecord(new Conta_CorrenteED(edVolta.getOid_Conta_Corrente()));
                if (edVolta.getOid_Motivo_Devolucao() > 0 && ed.isCarregaMotivoDev())
                    edVolta.edMotivo = new Motivo_DevolucaoBD(executasql).getByRecord(new Motivo_DevolucaoED(edVolta.getOid_Motivo_Devolucao()));
                
                edVolta.setNR_Comp(res.getString("NR_Comp"));
                edVolta.setNR_Agencia(res.getString("NR_Agencia"));
                edVolta.setNR_C1(res.getString("NR_C1"));
                edVolta.setNR_Conta(res.getString("NR_Conta"));
                edVolta.setNR_C2(res.getString("NR_C2"));
                edVolta.setNR_Cheque(res.getString("NR_Cheque"));
                edVolta.setNR_C3(res.getString("NR_C3"));
                edVolta.setDT_Programado(FormataDataBean.getFormatDate(res.getString("DT_Programado")));
                
                edVolta.setVL_Cheque(res.getDouble("VL_Cheque"));
                edVolta.setDM_Origem(res.getString("DM_Origem"));
                edVolta.setDT_Entrada(FormataDataBean.getFormatDate(res.getString("DT_Entrada")));
                
                edVolta.setDT_Apresentacao1(FormataDataBean.getFormatDate(res.getString("DT_Apresentacao1")));
                edVolta.setDT_Apresentacao2(FormataDataBean.getFormatDate(res.getString("DT_Apresentacao2")));
                edVolta.setDT_Apresentacao3(FormataDataBean.getFormatDate(res.getString("DT_Apresentacao3")));
                edVolta.setDT_Compensacao(FormataDataBean.getFormatDate(res.getString("DT_Compensacao")));

                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDM_Apresentacao(res.getString("DM_Apresentacao"));
                edVolta.setVL_Taxa(res.getDouble("VL_Taxa"));
                edVolta.setVL_Juro_Mora_Dia(res.getDouble("VL_Juro_Mora_Dia"));
                edVolta.setVL_Saldo(res.getDouble("VL_Saldo"));
                edVolta.setNR_Vale(res.getString("NR_Vale"));
                
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "lista()");
        }
        return list;
    }
    
    public Cheque_ClienteED getByRecord(Cheque_ClienteED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Cheque_ClienteED) iterator.next();            
        } else return new Cheque_ClienteED();
    }
    
    public Cheque_ClienteED getByRecordSemLote(Cheque_ClienteED ed) throws Excecoes {

        Iterator iterator = this.listaSemLote(ed).iterator();
        if (iterator.hasNext()) {
            return (Cheque_ClienteED) iterator.next();            
        } else return new Cheque_ClienteED();
    }
    
    private double saldoCheque(Cheque_ClienteED ed) throws Excecoes {
        double VL_Saldo = ed.getVL_Cheque();
        try {
            String sql = " SELECT SUM (vl_pagamento) as vl_pagamento " + " FROM Movimentos_Cheques " + " WHERE Movimentos_Cheques.oid_Cheque = " + ed.getOid_Cheque_Cliente();
            // System.out.println(sql);
            ResultSet res = this.executasql.executarConsulta(sql);
            // popula
            while (res.next()) {
                VL_Saldo = ed.getVL_Cheque() - res.getDouble("vl_pagamento");
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "saldoCheque()");
        }
        return VL_Saldo;
    }
}