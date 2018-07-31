package com.master.rn;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Carga_EntregaBD;
import com.master.bd.CidadeBD;
import com.master.bd.Item_Nota_Fiscal_TransacoesBD;
import com.master.bd.Item_PedidoBD;
import com.master.bd.Lancamento_ContabilBD;
import com.master.bd.Livro_FiscalBD;
import com.master.bd.Modelo_Nota_FiscalBD;
import com.master.bd.Nota_Fiscal_EletronicaBD;
import com.master.bd.Ocorrencia_Nota_FiscalBD;
import com.master.bd.Origem_DuplicataBD;
import com.master.bd.Parametro_WmsBD;
import com.master.bd.Parcelamento_FinanceiroBD;
import com.master.bd.PedidoBD;
import com.master.bd.WMS_EstoqueBD;
import com.master.ed.EmailED;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.Item_PedidoED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Lote_CompromissoED;
import com.master.ed.Modelo_Nota_FiscalED;
import com.master.ed.Movimento_DuplicataED;
import com.master.ed.Nota_Fiscal_CompraED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.ed.Origem_DuplicataED;
import com.master.ed.Parametro_WmsED;
import com.master.ed.Parcelamento_FinanceiroED;
import com.master.ed.Posto_CompromissoED;
import com.master.ed.RelatorioED;
import com.master.ed.UsuarioED;
import com.master.iu.NFEBean;
import com.master.root.CidadeBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

import br.cte.model.Empresa;
import br.nfe.core.BeanDanfeItens;
import br.nfe.model.NfeInutilizacao;
import br.nfe.model.NfeLote;
import br.nfe.model.NfeNotaFiscal;
import br.nfe.model.NfeRetornoEnvioLote;
import br.nfe.utils.Configuracoes;
import br.servicos.NfeServicos;


public class Nota_Fiscal_EletronicaRN extends Transacao {

    public Nota_Fiscal_EletronicaRN() {
    }
    
    public Nota_Fiscal_EletronicaRN(Empresa empresa) {
		super(empresa);
	}

    public Nota_Fiscal_EletronicaED inclui(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Nota_Fiscal_EletronicaBD(this.sql).inclui(ed);
            //Transit-Time
//            new Movimento_LogisticoBD(this.sql).gera_Movimento(new Movimento_LogisticoED(ed.getOid_nota_fiscal()));
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        return ed;
    }

    public Nota_Fiscal_EletronicaED inclui_Nota_Entrada(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Nota_Fiscal_EletronicaBD(this.sql).inclui_Nota_Entrada(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        return ed;
    }


    public boolean inclui_Lancamento(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        boolean valida = false;
        this.inicioTransacao();
        if ("S".equals(ed.getDM_Contabiliza())) {
            new Nota_Fiscal_EletronicaBD(this.sql).inclui_Lancamento(ed);
            valida = true;
        }
        this.fimTransacao(true);
        return valida;
    }

    public boolean inclui_Parcelamento(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        boolean validou = false;
        this.inicioTransacao();
        if ("S".equals(ed.getDM_Financeiro())) {
            new Nota_Fiscal_EletronicaBD(this.sql).inclui_Parcelamento(ed, 0, 0);
            validou = true;
        }
        this.fimTransacao(true);
        return validou;
    }

    public void altera(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Nota_Fiscal_EletronicaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera_CFOP(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Nota_Fiscal_EletronicaBD(this.sql).altera_CFOP(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void correcao(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Nota_Fiscal_EletronicaBD(this.sql).correcao(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Nota_Fiscal_EletronicaED ed, boolean excluirParcelas) throws Excecoes {

        try {
            this.inicioTransacao();
            // Apaga as ocorrencias da NF
        	Ocorrencia_Nota_FiscalED ocoNF =new Ocorrencia_Nota_FiscalED();
        	ocoNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
        	new Ocorrencia_Nota_FiscalBD(this.sql).deleta(ocoNF);
        	// Apaga os itens da NF que n�o forem D=Devolu�ao.
        	// Os Itens das notas fiscais de devolu��o s�o excluidos junto da exclus�o da nota fiscal
        	if (!"D".equals(ed.getDm_tipo_nota_fiscal())){
        		Item_Nota_Fiscal_TransacoesED itnNF = new Item_Nota_Fiscal_TransacoesED();
        		itnNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
        		new Item_Nota_Fiscal_TransacoesBD(this.sql).deleta(itnNF);
        	}

        	//Exclui a nf
            new Nota_Fiscal_EletronicaBD(this.sql).deleta(ed, excluirParcelas);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** Finaliza Nota Fiscal de Entrada
    public Nota_Fiscal_EletronicaED finalizaNF_Entrada(Nota_Fiscal_EletronicaED ed) throws Exception {

        try {
	        this.inicioTransacao();
	        Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);

	        if (!"15".equals(String.valueOf(ed.getOid_modelo_nota_fiscal()))){
		        //*** Atualiza Valores da NF(Caso n�o possua itens tbm)
		        Nota_FiscalBD.atualizaValorICMS(ed.getOid_nota_fiscal());
	        }

	        //*** Situa��o X ou oid_modelo_nota_fiscal = 15 - cr�dito icms finaliza a Nota Fiscal Direto!
	        if (!"X".equals(ed.getDM_Situacao()) && !"15".equals(String.valueOf(ed.getOid_modelo_nota_fiscal())))
	        {
	            Nota_FiscalBD.verificaProdutos(ed);
	            Nota_FiscalBD.verificaParcelamentos(ed);
	            Nota_FiscalBD.verificaFiscal(ed);
	        }
	        if (!"X".equals(ed.getDM_Situacao()) && "15".equals(String.valueOf(ed.getOid_modelo_nota_fiscal())))
	        {
	            Nota_FiscalBD.verificaFiscal(ed);
	        }

	        //*** Finaliza NF de Compra
	        Nota_FiscalBD.finalizaNF_Entrada(ed);
	        //*** Gera NF de Devolu��o caso exista NR_QT_Devolucao nos itens
	        //this.geraNFDevolucaoFornecedor(ed);

	        this.fimTransacao(true);
	        return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** Finaliza��o da NF de Saida (impress�o)
    public boolean  finalizaNF_Saida(Nota_Fiscal_EletronicaED edNota) throws Excecoes {

        try {
	        this.inicioTransacao();
	        Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);

	        Nota_Fiscal_EletronicaED ed = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(edNota.getOid_nota_fiscal()));

	        ed.setDT_Entrega(edNota.getDT_Entrega());
	        ed.setHr_entrada(edNota.getHr_entrada());

	        //*** Verifica se ja foi impresso
	        if ("S".equals(ed.getDM_Impresso()))
	            throw new Mensagens("Nota Fiscal ja Finalizada e impressa!");
	        //*** Verifica se ja foi finalizada
	        if ("F".equals(ed.getDM_Situacao()))
	            throw new Mensagens("Nota Fiscal ja Finalizada!");
	        // System.out.println("1");

	        //*** Verifica Valor Calculados Pelos Produtos(ICMS, IPI, etc...)
	        if (!"R".equals(ed.getDm_tipo_nota_fiscal()) && !"13".equals(String.valueOf(ed.getOid_modelo_nota_fiscal()))){
		        Nota_FiscalBD.verificaProdutos(ed);
	        }

	        //*** VERIFICA DE DEVE MOVIMENTAR FIANCEIRO
	        if ("S".equals(ed.edModelo.getDM_Movimenta_Financeiro()))
	        {
                //*** Caso seje informado DATA de SAIDA(entrega), calcula parcelamentos pela mesmo caso contrario busca data ATUAL
                ed.setDt_emissao(JavaUtil.getValueDef(ed.getDt_emissao(), Data.getDataDMY()));
	            //*** Parcelamentos Financeiros - [PARCELAS]
	            Nota_FiscalBD.inclui_Parcelamento(ed, 0, 0);
	            //*** Gera Duplicatas - [Contas a Receber]
	            String dm_faturamento_cliente = "I";
//	            ClienteBean cliente = ClienteBean.getByOID_Cliente(ed.getOid_pessoa_destinatario());
//	            dm_faturamento_cliente = cliente.getDM_Faturamento();
	            if(JavaUtil.doValida(dm_faturamento_cliente) && "I".equals(dm_faturamento_cliente)){
	            	Nota_FiscalBD.geraDuplicatas(ed);
	            }
	        }
	        // System.out.println("3");

	        //*** Gerar Livro Fiscal
	        if ("S".equals(ed.edModelo.getDM_Gera_Fiscal()))
	        {
                //*** CUPOM FISCAL
                if ("ECF".equals(JavaUtil.getValue(ed.getNm_serie()).toUpperCase()))
                    new Livro_FiscalBD(this.sql).geraLivro_Fiscal_PDV(new Livro_FiscalED(ed.getOid_nota_fiscal(), "CF"), "S");
                else new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "S");
	        }

	        //*** Gerar Contabiliza��o
	        if ("S".equals(ed.edModelo.getDM_Gera_Contabilizacao()))
	        {
	        	Nota_Fiscal_CompraED nfEd = new Nota_Fiscal_CompraED();
	        	nfEd.setOid_nota_fiscal(ed.getOid_nota_fiscal());
                new Lancamento_ContabilBD(this.sql).inclui_CTB_Nota_Fiscal_Servicos(nfEd);
	        }

	        // System.out.println("4");

	        Ocorrencia_Nota_FiscalBD ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.sql);
	        Ocorrencia_Nota_FiscalED ocoNF = new Ocorrencia_Nota_FiscalED();
        	ocoNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());

        	ocorrencia_Nota_FiscalBD.deleta(ocoNF);

        	// new Ocorrencia_Nota_FiscalRN().deleta(ocoNF);
	        // System.out.println("4.1");

	        //*** VERIFICA DE DEVE MOVIMENTAR ESTOQUE
	        boolean stkOK = true;
	        // System.out.println("4.2");
	        if ("S".equals(ed.edModelo.getDM_Movimenta_Estoque()) && !"S".equals(ed.getDM_Estoque())) {
	        	stkOK = new WMS_EstoqueBD(this.sql).saidaEstoqueByNota(ed,"T");
	        	if (stkOK) ed.setDM_Estoque("S");
	        } else ed.setDM_Estoque("N");
	        // System.out.println("5");

	        if (!stkOK) {
		        this.fimTransacao(false);
	        	return false;
	        } else {
	        	//*** FAZ A BAIXA DA DEVOLUCAO NA NOTA DE ENTRADA
		        if ("D".equals(ed.getDm_tipo_nota_fiscal()) ) {
			        ArrayList lstNFi = new ArrayList(); // Cont�m os itens da nota de devolu��o
			        ArrayList lstNFe = new ArrayList(); // Cont�m os n�meros das notas de entrada que tiveram itens devolvidos
			        ArrayList lstNF = new ArrayList();  // Cont�m os n�meros das notas j� retonados
			        String NF_nr ="";					// String com os n�meros das notas separados por virgula
		        	// Busca todos os itens da nota fiscal de devolucao
		        	Item_Nota_Fiscal_TransacoesED edNFi = new Item_Nota_Fiscal_TransacoesED();
		        	edNFi.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
		        	lstNFi = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(edNFi);
		        	//Itera os itens da nota de devolucao para buscar nas notas de entrada
		        	for (int x = 0; x <lstNFi.size(); x++){
		        		edNFi = (Item_Nota_Fiscal_TransacoesED)lstNFi.get(x);
		        		// D� baixa da quantidade devolvida da nota fiscal original de entrada, retorna array c/nrs das notas originais
		        		lstNFe =  new Item_Nota_Fiscal_TransacoesBD(this.sql).devolveItem(edNFi,edNFi.getOid_Produto_Cliente(),edNFi.getNR_Quantidade());
		        		for (int y = 0; y < lstNFe.size(); y++) {
		        			String nr_Nota = (String)lstNFe.get(y);
		        			if (!lstNF.contains(nr_Nota)) { // Verifica se nr nf n�o est� no array
		    					lstNF.add(nr_Nota);
		        			}
		        		}
		        	}
		        	// Altera a nota fiscal de devolu��o colocando os nrs das notas originais na observacao.
		    		for (int y = 0; y < lstNF.size(); y++) { // Itera o array de nrs nf para formar um string �nico para a observa��o
						NF_nr = NF_nr +", " + lstNF.get(y);
		    		}
		    		// Anota as nfs retornadas nas observacoes da nota de devolucao
		        	ed.setDm_observacao(ed.getDm_observacao() + NF_nr.substring(2).trim());
		        }
		        // System.out.println("6");

		        //*** FINALIZA NF
		        ed.setDM_Pendencia("N");
		        Nota_FiscalBD.finalizaNF_Saida(ed);
		        // System.out.println("7");

		        //*** VERIFICA SE EXISTE PEDIDO
		        if (JavaUtil.doValida(new NFEBean().getOid_Pedido(ed.getOid_nota_fiscal())))
		        {
		            //*** Seta PEDIDO VENDA como FINALIZADO
		            new PedidoBD(this.sql).finalizaPedidoVenda(0, ed.getOid_nota_fiscal());
		        }
		        // System.out.println("8");

	        }
	        this.fimTransacao(true);

	        return true;

        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        } catch(Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Falha ao realizar finaliza��o. Problemas ao buscar dados no cliente...");
        }
    }

    //*** Finaliza��o da NF de Devolucao (impress�o)
    public boolean finalizaNF_Devolucao(Nota_Fiscal_EletronicaED ed, boolean abreTrans) throws Excecoes {

        try {
        	if (abreTrans) this.inicioTransacao(); // Esta gambiarra foi colocada aqui porque h� um m�todo nesta classe que tambem usa este m�todo s� que ela j� abriu a transa�ao....

	        Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);

	        //*** Verifica se ja foi impresso
	        if ("S".equals(ed.getDM_Impresso()))
	            throw new Mensagens("Nota Fiscal ja Finalizada e impressa!");
	        //*** Verifica se ja foi finalizada
	        if ("F".equals(ed.getDM_Situacao()))
	            throw new Mensagens("Nota Fiscal ja Finalizada!");

	        //*** Verifica Valor Calculados Pelos Produtos(ICMS, IPI, etc...)
	        if (!"R".equals(ed.getDm_tipo_nota_fiscal())){
		        Nota_FiscalBD.verificaProdutos(ed);
	        }
//Gelson
	        Ocorrencia_Nota_FiscalED ocoNF = new Ocorrencia_Nota_FiscalED();
        	ocoNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
        	new Ocorrencia_Nota_FiscalBD(this.sql).deleta(ocoNF);
	        //*** Faz a baixa na nf de entrada da devolucao simbolica


        	boolean finaliza = Nota_FiscalBD.baixaDevolucaoSimbolica(ed); // Retorna true se deu tudo certo na baixa da nf entrada.
//        	boolean finaliza = true;
	        //*** Gerar Livro Fiscal
	        if ("S".equals(ed.edModelo.getDM_Gera_Fiscal())) {
               new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "S");
	        }
	        if (finaliza) { // Se conseguiu dar baixa na NF de entrada d� baixa no estoque e Finaliza
		        //*** VERIFICA SE DEVE MOVIMENTAR ESTOQUE
//Estoque

	        	if ("S".equals(ed.edModelo.getDM_Movimenta_Estoque()) && !"S".equals(ed.getDM_Estoque())) {
		            new WMS_EstoqueBD(this.sql).saidaEstoqueByNota(ed,"T");
	                ed.setDM_Estoque("S");
		        } else ed.setDM_Estoque("N");

		        //*** FINALIZA NF
		        Nota_FiscalBD.finalizaNF_Saida(ed);
	        }

	        if (abreTrans) this.fimTransacao(true);

	        return finaliza;

        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** EXCLUI Nota Fiscal de COMPRA
    public void excluiNFCompra(Nota_Fiscal_EletronicaED ed) throws Exception {

        try {
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o informada para Exclus�o!");
            if (!JavaUtil.doValida(ed.getDm_tipo_nota_fiscal()))
                throw new Mensagens("Tipo de Nota n�o informado!");
            if (!"E".equals(ed.getDm_tipo_nota_fiscal()) && !"F".equals(ed.getDm_tipo_nota_fiscal()))
                throw new Mensagens("Tipo e Nota incorreto para opera��o!");
            this.inicioTransacao();
            Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
            //*** Busca Dados da NF

            Nota_Fiscal_EletronicaED edNota = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(ed.getOid_nota_fiscal()));
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o encontrada para Exclus�o!");

            //*** Se Gerou Livro Fiscal
            if ("S".equals(edNota.edModelo.getDM_Gera_Fiscal()))
            {
                //*** Exclui Registros dos Livros Fiscais
                this.sql.executarUpdate(" DELETE FROM Livros_Fiscais " +
                        " WHERE oid_Nota_Fiscal ='"+edNota.getOid_nota_fiscal()+"'");
            }

            //*** VERIFICA SE MOVIMENTOU FIANCEIRO
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Financeiro()))
            {
                //*** Compromissos
                if ("F".equals(edNota.getDM_Situacao()))
                    this.sql.executarUpdate(" DELETE FROM Compromissos" +
                                            " WHERE Compromissos.VL_Saldo = Compromissos.VL_Compromisso" +
                                            "   AND Compromissos.oid_Compromisso IN (SELECT ParCom.oid_Compromisso " +
                                            "                                        FROM Parcelas_Compromissos as ParCom" +
                                            "                                            ,Parcelamentos_Financeiros as ParFin" +
                                            "                                        WHERE ParCom.oid_Parcelamento = ParFin.oid_Parcelamento" +
                                            "                                          AND ParFin.oid_Nota_Fiscal = '"+edNota.getOid_nota_fiscal()+"')");
                this.sql.executarUpdate(" DELETE FROM Parcelamentos_financeiros" +
                                        " WHERE oid_nota_fiscal = '"+edNota.getOid_nota_fiscal()+"'");
            }

            //*** ESTORNA QUANTIDADE ATENDIDA DOS PEDIDOS
            ArrayList pedidos = new ArrayList();
            Item_PedidoBD ItemPedidoBD = new Item_PedidoBD(this.sql);
            Item_Nota_Fiscal_TransacoesBD ItemNotaBD = new Item_Nota_Fiscal_TransacoesBD(this.sql);
            Iterator it = ItemNotaBD.lista(new Item_Nota_Fiscal_TransacoesED(edNota.getOid_nota_fiscal())).iterator();
            while (it.hasNext())
            {
                Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) it.next();
                //*** Atualiza QTD_Atendida no Pedido de COMPRA
                if (edItem.getOID_Item_Pedido() > 0 && edItem.getNR_QT_Atendido() > 0)
                {
                    ItemNotaBD.atualizaQTDAtendido(-edItem.getNR_QT_Atendido(), edItem.getOID_Item_Pedido());
                    ItemPedidoBD.atualizaSituacaoItemPedido(new Item_PedidoED(edItem.getOID_Item_Pedido(), 0, "N"));
                    //*** Adiciona Pedidos a Serem Reabertos
                    if (edItem.getOID_Pedido() > 0 && !pedidos.contains(String.valueOf(edItem.getOID_Pedido())))
                        pedidos.add(String.valueOf(edItem.getOID_Pedido()));
                }
            }
            //*** REABRE PEDIDOS
            PedidoBD PedidoBD = new PedidoBD(this.sql);
            Iterator itPed = pedidos.iterator();
            while (itPed.hasNext())
                PedidoBD.atualizaSituacaoPedido(Long.parseLong((String)itPed.next()), "N");

            //*** VERIFICA SE MOVIMENTOU O ESTOQUE
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Estoque()))
            {
                //*** ATUALIZA ESTOQUE
                if ("F".equals(edNota.getDM_Situacao()) && "S".equals(edNota.getDM_Estoque()))
                {
                    edNota.edModelo.setOid_Tipo_Movimento_Produto(Parametro_FixoED.getInstancia().getOID_Tipo_Movimento_Ajuste_Exc());
                    new WMS_EstoqueBD(this.sql).saidaEstoqueByNota(edNota,"T");
                }
            }

            //*** EXCLUI NOTA
            this.sql.executarUpdate(" DELETE FROM Ocorrencias_Notas_Fiscais " +
                    " WHERE oid_Nota_Fiscal ='"+edNota.getOid_nota_fiscal()+"'");

            //*** EXCLUI NOTA
            this.sql.executarUpdate(" DELETE FROM Notas_Fiscais " +
                    " WHERE oid_Nota_Fiscal ='"+edNota.getOid_nota_fiscal()+"'");

            if (JavaUtil.doValida(ed.getOid_Nota_Fiscal_Original())){

	            // REFAZ TODO O PROCESSO DA NOTA FISCAL DE SAIDA QUE FOI RETORNADA

	            ed = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(edNota.getOid_Nota_Fiscal_Original()));

	            //*** LIBERA NOTA FISCAL DE SAIDA PARA EXECUTAR NOVO RETORNO
	            this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
	                    "     DM_Situacao = 'F'" +
	                    " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"'");

		        //*** VERIFICA DE DEVE MOVIMENTAR FIANCEIRO

	            if ("S".equals(ed.edModelo.getDM_Movimenta_Financeiro()))
		        {
		            //*** Parcelamentos Financeiros - [PARCELAS]
		            Nota_FiscalBD.inclui_Parcelamento(ed, 0, 0);
		            //*** Gera Duplicatas - [Contas a Receber]
		            Nota_FiscalBD.geraDuplicatas(ed);
		        }
            }

            this.fimTransacao(true);

        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** CANCELA Nota Fiscal de COMPRA
    public void cancelaNFCompra(Nota_Fiscal_EletronicaED ed) throws Exception {

    	String DM_Tipo_Devolucao = null;
    	try {
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o informada para Cancelamento!");
            if (!JavaUtil.doValida(ed.getDm_tipo_nota_fiscal()))
                throw new Mensagens("Tipo de Nota n�o informado!");
            if (!"E".equals(ed.getDm_tipo_nota_fiscal()) && !"F".equals(ed.getDm_tipo_nota_fiscal()))
                throw new Mensagens("Tipo e Nota incorreto para opera��o!");

            this.inicioTransacao();
            Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
            //*** Busca Dados da NF
            Nota_Fiscal_EletronicaED edNota = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(ed.getOid_nota_fiscal()));
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o encontrada para Cancelamento!");

            DM_Tipo_Devolucao = edNota.getDM_Tipo_Devolucao();

            //*** VERIFICA SE MOVIMENTOU FIANCEIRO
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Financeiro()))
            {
                //*** Compromissos
                if ("F".equals(edNota.getDM_Situacao()))
                    this.sql.executarUpdate(" DELETE FROM Compromissos" +
                                            " WHERE Compromissos.VL_Saldo = Compromissos.VL_Compromisso" +
                                            "   AND Compromissos.oid_Compromisso IN (SELECT ParCom.oid_Compromisso " +
                                            "                                        FROM Parcelas_Compromissos as ParCom" +
                                            "                                            ,Parcelamentos_Financeiros as ParFin" +
                                            "                                        WHERE ParCom.oid_Parcelamento = ParFin.oid_Parcelamento" +
                                            "                                          AND ParFin.oid_Nota_Fiscal = '"+edNota.getOid_nota_fiscal()+"')");
                this.sql.executarUpdate(" DELETE FROM Parcelamentos_financeiros" +
                                        " WHERE oid_nota_fiscal = '"+edNota.getOid_nota_fiscal()+"'");
            }

            //*** ESTORNA QUANTIDADE ATENDIDA DOS PEDIDOS
            ArrayList pedidos = new ArrayList();
            Item_PedidoBD ItemPedidoBD = new Item_PedidoBD(this.sql);
            Item_Nota_Fiscal_TransacoesBD ItemNotaBD = new Item_Nota_Fiscal_TransacoesBD(this.sql);
            Iterator it = ItemNotaBD.lista(new Item_Nota_Fiscal_TransacoesED(edNota.getOid_nota_fiscal())).iterator();
            while (it.hasNext())
            {
                Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) it.next();
                //*** Atualiza QTD_Atendida no Pedido de COMPRA
                if (edItem.getOID_Item_Pedido() > 0 && edItem.getNR_QT_Atendido() > 0)
                {
                    ItemNotaBD.atualizaQTDAtendido(-edItem.getNR_QT_Atendido(), edItem.getOID_Item_Pedido());
                    ItemPedidoBD.atualizaSituacaoItemPedido(new Item_PedidoED(edItem.getOID_Item_Pedido(), 0, "N"));
                    //*** Adiciona Pedidos a Serem Reabertos
                    if (edItem.getOID_Pedido() > 0 && !pedidos.contains(String.valueOf(edItem.getOID_Pedido())))
                        pedidos.add(String.valueOf(edItem.getOID_Pedido()));
                }
            }
            //*** REABRE PEDIDOS
            PedidoBD PedidoBD = new PedidoBD(this.sql);
            Iterator itPed = pedidos.iterator();
            while (itPed.hasNext())
                PedidoBD.atualizaSituacaoPedido(Long.parseLong((String)itPed.next()), "N");

            //*** VERIFICA SE MOVIMENTOU O ESTOQUE
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Estoque()))
            {
                //*** ATUALIZA ESTOQUE
                if ("F".equals(edNota.getDM_Situacao()) && "S".equals(edNota.getDM_Estoque()))
                {
                    edNota.edModelo.setOid_Tipo_Movimento_Produto(Parametro_FixoED.getInstancia().getOID_Tipo_Movimento_Ajuste_Exc());
                    new WMS_EstoqueBD(this.sql).saidaEstoqueByNota(edNota,"T");
                }
            }

            //*** CANCELA NOTA
            this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
                    "     DM_Situacao = 'C'" +
                    "    ,DM_Estoque = '"+("S".equals(edNota.getDM_Estoque()) ? "E" : "N")+"'" +
                    " WHERE oid_Nota_Fiscal = '"+edNota.getOid_nota_fiscal()+"'");

//          CANCELA na NFE SEFAZ
            Nota_FiscalBD.cancelaNFE(edNota);

            //*** Gerar Livro Fiscal

            if ("S".equals(edNota.edModelo.getDM_Gera_Fiscal()))
	        {
                //*** CUPOM FISCAL
                if ("ECF".equals(JavaUtil.getValue(edNota.getNm_serie()).toUpperCase()))
                    new Livro_FiscalBD(this.sql).geraLivro_Fiscal_PDV(new Livro_FiscalED(edNota.getOid_nota_fiscal(), edNota.getNm_serie()), edNota.getDm_tipo_nota_fiscal());
                else
                	new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Entradas(new Livro_FiscalED(edNota.getOid_nota_fiscal(), edNota.getNm_serie()), edNota.getDm_tipo_nota_fiscal());
	        }

            if (!JavaUtil.doValida(ed.getOid_Nota_Fiscal_Original()) && JavaUtil.doValida(DM_Tipo_Devolucao)){

	            // REFAZ TODO O PROCESSO DA NOTA FISCAL DE SAIDA QUE FOI RETORNADA

	            ed = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(edNota.getOid_Nota_Fiscal_Original()));

	            //*** LIBERA NOTA FISCAL DE SAIDA PARA EXECUTAR NOVO RETORNO
	            this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
	                    "     DM_Situacao = 'F'" +
	                    " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"'");

		        //*** VERIFICA DE DEVE MOVIMENTAR FIANCEIRO

	            if ("S".equals(ed.edModelo.getDM_Movimenta_Financeiro()))
		        {
		            //*** Parcelamentos Financeiros - [PARCELAS]
//		            Nota_FiscalBD.inclui_Parcelamento(ed, 0, 0);
		            //*** Gera Duplicatas - [Contas a Receber]
//		            Nota_FiscalBD.geraDuplicatas(ed);

                    new Nota_Fiscal_EletronicaBD(this.sql).estornaDuplicatas(edNota.getOid_Nota_Fiscal_Original());
		        }
            }
            this.fimTransacao(true);

        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** CANCELA Nota Fiscal de Venda
    public void cancelaNFVenda(Nota_Fiscal_EletronicaED ed) throws Exception {

        try {
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o informada para Cancelamento");
            if (!JavaUtil.doValida(ed.getDM_Tipo_Devolucao()))
                throw new Mensagens("Tipo de Cancelamento n�o informado!");

            this.inicioTransacao();
            Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
            Utilitaria util = new Utilitaria(this.sql);
            //*** Busca Dados da NF Original
            Nota_Fiscal_EletronicaED edNota = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(ed.getOid_nota_fiscal()));
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o encontrada para Cancelamento");

            /** N�O DEVE retirar do livro fiscal e sim gerar um lan�amento com os valores zerados
            //*** Se Gerou Livro Fiscal
            if ("S".equals(edNota.edModelo.getDM_Gera_Fiscal()))
            {
                //*** Exclui Registros dos Livros Fiscais
                this.sql.executarUpdate(" DELETE FROM Livros_Fiscais " +
                        " WHERE oid_Nota_Fiscal ='"+ed.getOid_nota_fiscal()+"'");
            }
            */

            //*** VERIFICA SE MOVIMENTOU FIANCEIRO
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Financeiro()))
            {
                //*** Exclui Duplicatas
                Nota_FiscalBD.deletaDuplicatas(ed.getOid_nota_fiscal());
            }

            //*** VERIFICA SE MOVIMENTOU O ESTOQUE
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Estoque()) && "S".equals(edNota.getDM_Estoque()))
            {
                /*** Atualiza Estoque
                 *   Atualiza o estoque direto pela nota fiscal de sa�da e n�o pela
                 *   nota de entrada respectiva. Veja em entradaEstoqueByNota() abaixo
                 ***/
                Item_Nota_Fiscal_TransacoesED edItem_NF = new Item_Nota_Fiscal_TransacoesED();
                edItem_NF.setOID_Nota_Fiscal(edNota.getOid_nota_fiscal());
                edItem_NF.setOID_Unidade(edNota.getOID_Unidade());
                edItem_NF.setOID_Pessoa(edNota.getOid_pessoa());
                //*** Deposito Auxiliar
                edItem_NF.setOID_Deposito(Parametro_FixoED.getInstancia().getOID_Deposito());
                edItem_NF.setCD_Local_Descarga("0101A002");
                edItem_NF.setOid_Tipo_Estoque(edNota.edModelo.getOid_Tipo_Estoque());
                edItem_NF.setOid_Tipo_Movimento_Produto(Parametro_FixoED.getInstancia().getOID_Tipo_Movimento_Ajuste_Canc());
                new WMS_EstoqueBD(this.sql).entradaEstoqueByNota(edItem_NF);
            }

            //*** Libera Pedido para gerar nova NF,
            //    ou Cancela o mesmo
            String oid_Pedido = new NFEBean().getOid_Pedido(edNota.getOid_nota_fiscal());
            if (JavaUtil.doValida(oid_Pedido))
            {
                PedidoBD PedidoBD = new PedidoBD(this.sql);
                if ("R".equals(ed.getDM_Tipo_Devolucao()))
                {
                    PedidoBD.atualizaSituacaoPedido(Long.parseLong(oid_Pedido), "N");
                    PedidoBD.atualizaCriticaEstoque(Long.parseLong(oid_Pedido), "N");
                } else {
                    PedidoBD.atualizaSituacaoPedido(Long.parseLong(oid_Pedido), "C");
                }
            }
            //*** Carga Entrega
            if (edNota.getOid_Carga_Entrega() > 0)
                new Carga_EntregaBD(this.sql).updateTotais(edNota.getOid_Carga_Entrega());

            //*** Seta NF com Situa��o de Cancelada
            this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
                                    "     DM_Situacao = 'C'" +
                                    "    ,DM_Estoque = '"+("S".equals(edNota.getDM_Estoque()) ? "E" : "N")+"'" +
                                    " WHERE oid_Nota_Fiscal = '"+edNota.getOid_nota_fiscal()+"'");
//          CANCELA na NFE SEFAZ
            Nota_FiscalBD.cancelaNFE(edNota);

            //*** Seta NF de Saida como NAO DEVOLVIDA, caso a nota atual seja de devolucao
            if ("D".equals(edNota.edModelo.getDM_Tipo_Nota_Fiscal())){
            	this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
        								"        oid_nota_fiscal_devolucao = null" +
        								"       ,dm_devolvido = 'N'" +
        								" WHERE  oid_Nota_Fiscal = '"+edNota.getOid_Nota_Fiscal_Original()+"'");
            	//*** Cancela as devolucoes nas NFs de Entrada
            	Item_Nota_Fiscal_TransacoesED edNFi = new Item_Nota_Fiscal_TransacoesED();
            	edNFi.setOID_Nota_Fiscal(edNota.getOid_nota_fiscal());
            	ArrayList lstNFi = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(edNFi);
            	for (int x = 0; x <lstNFi.size(); x++){
            		edNFi = (Item_Nota_Fiscal_TransacoesED)lstNFi.get(x);
            		// CANCELA baixa da quantidade devolvida da nota fiscal original de entrada
            		String sqlAux="SELECT "+
        		  	  		   "itn.oid_Item_Nota_Fiscal, "+
        		  	  		   "itn.nr_Qt_Devolucao, "+
        		  	  		   "itn.nr_Lote_Produto "+
        		  	  		   "FROM "+
        		  	  		   "Notas_Fiscais as nfe, "+
        		  	  		   "Itens_Notas_Fiscais as itn, " +
        		  	  		   "Itens_notas_fiscais_vinculadas itv "+
        		  	  		   "WHERE "+
        		  	  		   "nfe.oid_Nota_Fiscal = itn.oid_Nota_Fiscal and "+
        		  	  		   "nfe.dm_tipo_nota_fiscal = 'E' and " +
        		  	  		   "itv.oid_item_nota_fiscal_entrada = itn.oid_Item_Nota_Fiscal and " +
        		  	  		   "itv.oid_item_nota_fiscal_devolvida = '" + edNFi.getOID_Item_Nota_Fiscal() + "' and " +
        		  	  		   "itn.oid_Produto_Cliente = '"+ edNFi.getOid_Produto_Cliente() +"'";

            		if (JavaUtil.doValida(edNFi.getNR_Lote_Produto())){
            			sqlAux +=" and itn.NR_Lote_Produto = '"+ edNFi.getNR_Lote_Produto() +"' ";
        		  	}
            		ResultSet itnE = this.sql.executarConsulta(sqlAux);
            		//Itera os itens
            		while( itnE.next() ){
            			this.sql.executarUpdate(" UPDATE Itens_Notas_Fiscais SET" +
												"        nr_Qt_Devolucao = " + (itnE.getDouble("nr_qt_devolucao")-edNFi.getNR_QT_Atendido()) +
												"       ,dm_Devolvido = 'N' " +
												" WHERE  oid_Item_Nota_Fiscal = '"+itnE.getString("oid_Item_Nota_Fiscal")+"'");
            		}

            	}
            }

            //*** Gerar Livro Fiscal
	        if ("S".equals(edNota.edModelo.getDM_Gera_Fiscal()))
	        {
                //*** CUPOM FISCAL
                if ("ECF".equals(JavaUtil.getValue(ed.getNm_serie()).toUpperCase()))
                    new Livro_FiscalBD(this.sql).geraLivro_Fiscal_PDV(new Livro_FiscalED(edNota.getOid_nota_fiscal(), "CF"), "S");
                else
                	new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(edNota.getOid_nota_fiscal(), "NF"), "S");
	        }

            this.fimTransacao(true);

        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void denegacaoNFVenda(Nota_Fiscal_EletronicaED ed) throws Exception {

        try {
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o informada para Cancelamento");

            this.inicioTransacao();
            Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
            Utilitaria util = new Utilitaria(this.sql);
            //*** Busca Dados da NF Original
            Nota_Fiscal_EletronicaED edNota = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(ed.getOid_nota_fiscal()));
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota fiscal n�o encontrada para Cancelamento");

            /** N�O DEVE retirar do livro fiscal e sim gerar um lan�amento com os valores zerados
            //*** Se Gerou Livro Fiscal
            if ("S".equals(edNota.edModelo.getDM_Gera_Fiscal()))
            {
                //*** Exclui Registros dos Livros Fiscais
                this.sql.executarUpdate(" DELETE FROM Livros_Fiscais " +
                        " WHERE oid_Nota_Fiscal ='"+ed.getOid_nota_fiscal()+"'");
            }
            */

            //*** VERIFICA SE MOVIMENTOU FIANCEIRO
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Financeiro()))
            {
                //*** Exclui Duplicatas
                Nota_FiscalBD.deletaDuplicatas(ed.getOid_nota_fiscal());
            }

            //*** VERIFICA SE MOVIMENTOU O ESTOQUE
            if ("S".equals(edNota.edModelo.getDM_Movimenta_Estoque()) && "S".equals(edNota.getDM_Estoque()))
            {
                /*** Atualiza Estoque
                 *   Atualiza o estoque direto pela nota fiscal de sa�da e n�o pela
                 *   nota de entrada respectiva. Veja em entradaEstoqueByNota() abaixo
                 ***/
                Item_Nota_Fiscal_TransacoesED edItem_NF = new Item_Nota_Fiscal_TransacoesED();
                edItem_NF.setOID_Nota_Fiscal(edNota.getOid_nota_fiscal());
                edItem_NF.setOID_Unidade(edNota.getOID_Unidade());
                edItem_NF.setOID_Pessoa(edNota.getOid_pessoa());
                //*** Deposito Auxiliar
                edItem_NF.setOID_Deposito(Parametro_FixoED.getInstancia().getOID_Deposito());
                edItem_NF.setCD_Local_Descarga("0101A002");
                edItem_NF.setOid_Tipo_Estoque(edNota.edModelo.getOid_Tipo_Estoque());
                edItem_NF.setOid_Tipo_Movimento_Produto(Parametro_FixoED.getInstancia().getOID_Tipo_Movimento_Ajuste_Canc());
                new WMS_EstoqueBD(this.sql).entradaEstoqueByNota(edItem_NF);
            }

            //*** Carga Entrega
            if (edNota.getOid_Carga_Entrega() > 0)
                new Carga_EntregaBD(this.sql).updateTotais(edNota.getOid_Carga_Entrega());

//            //*** Seta NF com Situa��o de DENEGADA
            this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
                                    "     DM_Situacao = 'D'" +
                                    "    ,DM_Estoque = '"+("S".equals(edNota.getDM_Estoque()) ? "E" : "N")+"'" +
                                    " WHERE oid_Nota_Fiscal = '"+edNota.getOid_nota_fiscal()+"'");

            //*** Seta NF de Saida como NAO DEVOLVIDA, caso a nota atual seja de devolucao
            if ("D".equals(edNota.edModelo.getDM_Tipo_Nota_Fiscal())){
            	this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
        								"        oid_nota_fiscal_devolucao = null" +
        								"       ,dm_devolvido = 'N'" +
        								" WHERE  oid_Nota_Fiscal = '"+edNota.getOid_Nota_Fiscal_Original()+"'");
            	//*** Cancela as devolucoes nas NFs de Entrada
            	Item_Nota_Fiscal_TransacoesED edNFi = new Item_Nota_Fiscal_TransacoesED();
            	edNFi.setOID_Nota_Fiscal(edNota.getOid_nota_fiscal());
            	ArrayList lstNFi = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(edNFi);
            	for (int x = 0; x <lstNFi.size(); x++){
            		edNFi = (Item_Nota_Fiscal_TransacoesED)lstNFi.get(x);
            		// CANCELA baixa da quantidade devolvida da nota fiscal original de entrada
            		String sqlAux="SELECT "+
        		  	  		   "itn.oid_Item_Nota_Fiscal, "+
        		  	  		   "itn.nr_Qt_Devolucao, "+
        		  	  		   "itn.nr_Lote_Produto "+
        		  	  		   "FROM "+
        		  	  		   "Notas_Fiscais as nfe, "+
        		  	  		   "Itens_Notas_Fiscais as itn, " +
        		  	  		   "Itens_notas_fiscais_vinculadas itv "+
        		  	  		   "WHERE "+
        		  	  		   "nfe.oid_Nota_Fiscal = itn.oid_Nota_Fiscal and "+
        		  	  		   "nfe.dm_tipo_nota_fiscal = 'E' and " +
        		  	  		   "itv.oid_item_nota_fiscal_entrada = itn.oid_Item_Nota_Fiscal and " +
        		  	  		   "itv.oid_item_nota_fiscal_devolvida = '" + edNFi.getOID_Item_Nota_Fiscal() + "' and " +
        		  	  		   "itn.oid_Produto_Cliente = '"+ edNFi.getOid_Produto_Cliente() +"'";

            		if (JavaUtil.doValida(edNFi.getNR_Lote_Produto())){
            			sqlAux +=" and itn.NR_Lote_Produto = '"+ edNFi.getNR_Lote_Produto() +"' ";
        		  	}
            		ResultSet itnE = this.sql.executarConsulta(sqlAux);
            		//Itera os itens
            		while( itnE.next() ){
            			this.sql.executarUpdate(" UPDATE Itens_Notas_Fiscais SET" +
												"        nr_Qt_Devolucao = " + (itnE.getDouble("nr_qt_devolucao")-edNFi.getNR_QT_Atendido()) +
												"       ,dm_Devolvido = 'N' " +
												" WHERE  oid_Item_Nota_Fiscal = '"+itnE.getString("oid_Item_Nota_Fiscal")+"'");
            		}

            	}
            }

            //*** Gerar Livro Fiscal
	        if ("S".equals(edNota.edModelo.getDM_Gera_Fiscal()))
	        {
                //*** CUPOM FISCAL
                if ("ECF".equals(JavaUtil.getValue(ed.getNm_serie()).toUpperCase()))
                    new Livro_FiscalBD(this.sql).geraLivro_Fiscal_PDV(new Livro_FiscalED(edNota.getOid_nota_fiscal(), "CF"), "S");
                else
                	new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(edNota.getOid_nota_fiscal(), "NF"), "S");
	        }

            this.fimTransacao(true);

        } catch(Excecoes e) {
        	e.printStackTrace();
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
        	e.printStackTrace();
            this.abortaTransacao();
            throw e;
        }
    }

    //*** Devolu��o Nota Fiscal de Venda
    public Nota_Fiscal_EletronicaED geraNFDevolucaoVenda(Nota_Fiscal_EletronicaED edDevolucao, Ocorrencia_Nota_FiscalED edOcorrencia) throws Exception {

        try {
            this.inicioTransacao();
	        edDevolucao.edModelo = new Modelo_Nota_FiscalBD(this.sql).getByRecord(new Modelo_Nota_FiscalED((int)edDevolucao.getOid_modelo_nota_fiscal()));
	        //*** Busca Tipo("Entrada") no Modelo da NF
	        edDevolucao.setDm_tipo_nota_fiscal(edDevolucao.edModelo.getDM_Tipo_Nota_Fiscal());
	        edDevolucao.setOid_Nota_Fiscal_Original(edDevolucao.getOid_nota_fiscal());
	        //*** Verifica deve ser impressa
	        edDevolucao.setDM_Impresso("N");
            edDevolucao.setDM_Situacao("F");
            if ("S".equals(edDevolucao.edModelo.getDM_Movimenta_Estoque()))
                edDevolucao.setDM_Estoque("S");
	        edDevolucao.setHr_entrada(Data.getHoraHM());
	        /** Inclui NF de Devolu��o! **/
	        String Nm_serie = edDevolucao.getNm_serie();
            edDevolucao.setNm_serie("DEVOLUCAO_SAIDA");//p/ gerar novo Numero
            edDevolucao.setNr_nota_fiscal(0);//p/ gerar novo Numero
	        edDevolucao = new Nota_Fiscal_EletronicaBD(this.sql).inclui(edDevolucao);
            edDevolucao.setNm_serie(Nm_serie);

            /** Inclui Itens da Nota **/
            Item_Nota_Fiscal_TransacoesBD Item_Nota_FiscalBD = new Item_Nota_Fiscal_TransacoesBD(this.sql);
            ArrayList itensNota = Item_Nota_FiscalBD.lista(new Item_Nota_Fiscal_TransacoesED(edDevolucao.getOid_Nota_Fiscal_Original()));

            for (int i=0; i<itensNota.size(); i++)
            {
                Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED)itensNota.get(i);
                edItem.setOID_Pedido(0);
                edItem.setOID_Item_Nota_Fiscal(0);
                edItem.setOID_Nota_Fiscal(edDevolucao.getOid_nota_fiscal());
                //Troca o oid da natureza do item da NF de devolu��o pelo correspondente a do item da NF original
                String cdNaturezaDevolucao = Item_Nota_FiscalBD.getTableStringValue("cd_cfo_conhecimento", "Naturezas_Operacoes", "oid_Natureza_Operacao = '"+edItem.getOid_natureza_operacao()+"'");
                //Pega o oid da da natureza de devolu�ao pelo codigo dela
                edItem.setOid_natureza_operacao(Item_Nota_FiscalBD.getTableIntValue("oid_natureza_operacao", "Naturezas_Operacoes", "cd_natureza_operacao = '"+cdNaturezaDevolucao+"'"));
                Item_Nota_FiscalBD.inclui(edItem);
            }

            /** Gerar Livro Fiscal **/
	        if ("S".equals(edDevolucao.edModelo.getDM_Gera_Fiscal()))
	        {
                if ("E".equals(edDevolucao.getDm_tipo_nota_fiscal()))
                    new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Entradas(new Livro_FiscalED(edDevolucao.getOid_nota_fiscal(), edDevolucao.getNm_serie()), edDevolucao.getDm_tipo_nota_fiscal());
                else new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(edDevolucao.getOid_Nota_Fiscal_Original(), edDevolucao.getNm_serie()), edDevolucao.getDm_tipo_nota_fiscal());
	        }

            /** EXCLUI DUPLICATAS DA NOTA DEVOLVIDA **/
//            new Nota_Fiscal_TransacoesBD(this.sql).deletaDuplicatas(edDevolucao.getOid_Nota_Fiscal_Original());

            BancoUtil bancoUtil = new BancoUtil();
            //*** Movimentos Duplicatas - [PARCELAS]
            Parcelamento_FinanceiroED edParcela = new Parcelamento_FinanceiroED();
            edParcela.setOID_Nota_Fiscal(edDevolucao.getOid_Nota_Fiscal_Original());

            ArrayList listaParcelas = new Parcelamento_FinanceiroBD(this.sql).lista(edParcela);
            for (int x=0; x < listaParcelas.size(); x++)
            {
                Parcelamento_FinanceiroED edParc = (Parcelamento_FinanceiroED) listaParcelas.get(x);
                Origem_DuplicataED edDuplicataOrig = new Origem_DuplicataBD(this.sql).getByParcelamento(new Origem_DuplicataED((int)edParc.getOID_Parcelamento(), edParc.getOID_Nota_Fiscal()));

                Movimento_DuplicataED edMov = new Movimento_DuplicataED();
                edMov.setDM_Debito_Credito("C");
                edMov.setDM_Tipo_Lancamento("L");
                edMov.setDM_Tipo_Pagamento("T");
                //Esta linha estava com problemas... N�o h� no Parametro_FixoED getOID_Historico_Liquidacao_Cobranca_Retorno()
                //edMov.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Liquidacao_Cobranca_Retorno()));
                //substitu�da por essa
                edMov.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Liquidacao_Cobranca()));

                edMov.setOid_Duplicata(new Long(edDuplicataOrig.getOID_Duplicata()).longValue());
                double vlLiquidar = edParc.getVL_Parcela();

                edMov.setVL_Pago(vlLiquidar);
                //Esta linha estava com problemas... N�o h� no Parametro_FixoED getOID_Carteira_Nota_Retorno()
                //edMov.setOID_Carteira(new Integer(Parametro_FixoED.getInstancia().getOID_Carteira_Nota_Retorno()));
                //substitu�da por essa
                edMov.setOID_Carteira(new Integer(new Long(Parametro_FixoED.getInstancia().getOID_Carteira_Faturamento()).intValue()));

                edMov.setOid_Conta_Corrente(bancoUtil.getTableStringValue("oid_Conta_Corrente","Carteiras","oid_Carteira="+edMov.getOID_Carteira()));
                edMov.setOid_Conta(new Integer(bancoUtil.getTableIntValue("oid_Conta","Contas_Correntes","oid_Conta_Corrente="+bancoUtil.getSQLString(edMov.getOid_Conta_Corrente()))));
                edMov.setDT_Movimento(Data.getDataDMY());
                String NR_Nota_Retorno = bancoUtil.getTableStringValue("NR_Nota_Fiscal","Notas_Fiscais","oid_nota_fiscal='"+edParc.getOID_Nota_Fiscal()+"'");
                edMov.setNR_Documento(NR_Nota_Retorno);
                edMov.setNM_Complemento_Historico("Nota Fiscal Retorno : " + NR_Nota_Retorno);
                new Movimento_DuplicataRN().inclui(edMov);
            }

	        /** Gera Ocorrencia na NF de Origem **/
	        new Ocorrencia_Nota_FiscalBD(this.sql).inclui(edOcorrencia);

	        /** VERIFICA DE DEVE MOVIMENTAR ESTOQUE **/
	        if ("S".equals(edDevolucao.edModelo.getDM_Movimenta_Estoque()))
	        {
                /*** Atualiza Estoque
                 *   Atualiza o estoque direto pela nota fiscal de sa�da e n�o pela
                 *   nota de entrada respectiva. Veja em entradaEstoqueByNota() abaixo
                 ***/
		        Item_Nota_Fiscal_TransacoesED edItem_NF = new Item_Nota_Fiscal_TransacoesED();
		        edItem_NF.setOID_Nota_Fiscal(edDevolucao.getOid_Nota_Fiscal_Original());
				edItem_NF.setOID_Unidade(edDevolucao.getOID_Unidade());
				edItem_NF.setOID_Deposito(Parametro_FixoED.getInstancia().getOID_Deposito());
				edItem_NF.setOid_Tipo_Estoque(edDevolucao.edModelo.getOid_Tipo_Estoque());
				edItem_NF.setOid_Tipo_Movimento_Produto(edDevolucao.edModelo.getOid_Tipo_Movimento_Produto());
				edItem_NF.setOid_Localizacao(Parametro_FixoED.getInstancia().getOID_Localizacao_Devolucao());
                new WMS_EstoqueBD(this.sql).entradaEstoqueByNota(edItem_NF);
	        }

	        //*** Seta TIPO_DEVOLUCAO se PARCIAL, Libera Pedido para gerar nova NF,
	        //					      se TOTAL Cancela o Pedido
			String oid_Pedido = new NFEBean().getOid_Pedido(edDevolucao.getOid_Nota_Fiscal_Original());
	        if (JavaUtil.doValida(oid_Pedido))
            {
	            PedidoBD PedidoBD = new PedidoBD(this.sql);
	            if ("P".equals(edDevolucao.getDM_Tipo_Devolucao()))
                {
	                PedidoBD.atualizaSituacaoPedido(Long.parseLong(oid_Pedido), "N");
	                PedidoBD.atualizaCriticaEstoque(Long.parseLong(oid_Pedido), "N");
	            } else {
	                PedidoBD.atualizaSituacaoPedido(Long.parseLong(oid_Pedido), "C");
	            }
	        }
	        /** Seta NF Original com Situa��o Devolvida **/
            this.sql.executarUpdate(" UPDATE Notas_Fiscais SET" +
                                    "     DM_Situacao = 'D'" +
                                    "    ,oid_Nota_Fiscal_Devolucao = '"+edDevolucao.getOid_nota_fiscal()+"'" +
                                    " WHERE oid_Nota_Fiscal = '"+edDevolucao.getOid_Nota_Fiscal_Original()+"'");

	        this.fimTransacao(true);
	        return edDevolucao;

        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** Devolu��o Fornecedor Nota Fiscal de Saida + impress�o (QTD_Devolu��o nos itens da NF)
    private void geraNFDevolucaoFornecedor(Nota_Fiscal_EletronicaED edNota) throws Exception {

        try {

	        if (!JavaUtil.doValida(edNota.getOid_nota_fiscal()))
	            throw new Excecoes("ID Nota Fiscal n�o infomado!");

	        if (!new BancoUtil().doExiste("Itens_Notas_Fiscais","oid_Nota_Fiscal = '"+edNota.getOid_nota_fiscal()+"'" +
	        									  				" AND NR_QT_Devolucao > 0"))
	            return;

	        Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
	        edNota = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(edNota.getOid_nota_fiscal()));

	        int oid_Modelo = 0;
	        if (new CidadeBD(this.sql).isMesmoEstado(UnidadeBean.getByOID_Unidade((int)edNota.getOID_Unidade()).getOID_Pessoa(),
	                								 edNota.getOid_pessoa_fornecedor())) {
	            oid_Modelo = Parametro_FixoED.getInstancia().getOid_Modelo_NF_DevFornecedor_Dentro_Estado();
	        } else oid_Modelo = Parametro_FixoED.getInstancia().getOid_Modelo_NF_DevFornecedor_Fora_Estado();

	        edNota.edModelo = new Modelo_Nota_FiscalBD(this.sql).getByRecord(new Modelo_Nota_FiscalED(oid_Modelo));
	        edNota.setOid_modelo_nota_fiscal(oid_Modelo);
	        edNota.setOid_Nota_Fiscal_Original(edNota.getOid_nota_fiscal());
	        edNota.setOid_natureza_operacao(0);

	        //*** Busca NR da Nota e S�rie
	        Nota_FiscalBD.setNrSerieNotaFromAIDOF(edNota, "DEV");
	        edNota.setDm_tipo_nota_fiscal(edNota.edModelo.getDM_Tipo_Nota_Fiscal());
	        //*** Verifica deve ser impressa
	        edNota.setDt_entrada(Data.getDataDMY());
	        edNota.setHr_entrada(Data.getHoraHM());
	        edNota.setDm_finalizado("N");
	        edNota.setDM_Pendencia("N");
	        edNota.setDM_Situacao("N");
	        edNota.setDM_Impresso("N");

	        //*** Inclui Nota Fiscal de Devolu�ao Fornecedor
	        edNota = Nota_FiscalBD.inclui(edNota);

	        double nrItens = 0, vlICMS = 0, vlIPI = 0, vlICMSAprov = 0, vlProdutos = 0;
	        //*** Carrega os dados do Item Pedido pelo OID_PEDIDO
	        ArrayList listaItens = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(new Item_Nota_Fiscal_TransacoesED(edNota.getOid_Nota_Fiscal_Original()));
	        for (int i = 0; i < listaItens.size(); i++)
            {
	            Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) listaItens.get(i);
	            //*** Lista Produtos para Devolu��o
	            if (edItem.getNR_QT_Devolucao() > 0)
                {
	                edItem.setOID_Item_Nota_Fiscal(0);
	                edItem.setOID_Nota_Fiscal(edNota.getOid_nota_fiscal());
	                edItem = new Item_Nota_Fiscal_TransacoesBD(this.sql).inclui(edItem);
	                nrItens += edItem.isPesagem() ? edItem.getNR_Peso_Real() : edItem.getNR_QT_Atendido();
	                vlICMS += edItem.getVL_ICMS();
	                vlICMSAprov += edItem.getVL_ICMS_Aprov();
	                vlIPI += edItem.getVL_IPI();
	                vlProdutos += edItem.getVL_Produto();
	            }
	        }

	        edNota.setVl_icms(vlICMS);
	        edNota.setVl_ipi(vlIPI);
	        edNota.setVl_nota_fiscal(vlProdutos);
	        edNota.setVl_liquido_nota_fiscal(vlProdutos+vlIPI);
	        Nota_FiscalBD.altera(edNota);
	        Nota_FiscalBD.atualizaValorICMS(edNota.getOid_nota_fiscal());

	        //*** Gerar Livro Fiscal
	        if ("S".equals(edNota.edModelo.getDM_Gera_Fiscal()))
	        {
	            new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(edNota.getOid_Nota_Fiscal_Original(), "NF"), edNota.getDm_tipo_nota_fiscal());
	        }
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** Finaliza uma LISTA de Notas Fiscais
    public void finalizaListaNF(ArrayList lista, String dmTipo_NF, int oid_Carga_Entrega, String dtSaida, String hrSaida) throws Exception {

        if (lista.size() < 1)
            throw new Mensagens("Lista de Notas vazia! Execute novamente a consulta!");
        if (!JavaUtil.doValida(dmTipo_NF))
            throw new Mensagens("Tipo de Nota n�o informada!");

        //*** NFs de saida
        if ("S".equals(dmTipo_NF))
        {
            for (int i = 0; i < lista.size(); i++)
            {
                Nota_Fiscal_EletronicaED ed = (Nota_Fiscal_EletronicaED) lista.get(i);
                ed.setOid_Carga_Entrega(oid_Carga_Entrega);
                ed.setDT_Entrega(dtSaida);
                ed.setHr_entrada(hrSaida);
                this.finalizaNF_Saida(ed);
            }
        } else
        //*** NFs de Entrada
        if ("E".equals(dmTipo_NF))
        {
            for (int i = 0; i < lista.size(); i++)
                this.finalizaNF_Entrada((Nota_Fiscal_EletronicaED) lista.get(i));
        } else throw new Mensagens("Tipo de Nota n�o reconhecido!");
    }

    //*** Finaliza Nota Fiscal de Venda Direta
    public void finalizaNF_VendaDireta(String oid_Nota_Fiscal, String oid_Conhecimento) throws Exception {

        if (!JavaUtil.doValida(oid_Nota_Fiscal))
            throw new Mensagens("ID Nota Fiscal n�o informada!");
        if (!JavaUtil.doValida(oid_Conhecimento))
            throw new Mensagens("ID Conhecimento n�o informado!");

        try {
	        this.inicioTransacao();
            BancoUtil bancoUtil = new BancoUtil();
	        Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
            Nota_Fiscal_EletronicaED ed = Nota_FiscalBD.getByRecord(new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal));
            if (!JavaUtil.doValida(ed.getOid_nota_fiscal()))
                throw new Mensagens("Nota Fiscal n�o encontrada!");

            //*** Gerar Livro Fiscal
            String dm_tipo_documento = bancoUtil.getTableStringValue("dm_tipo_documento","Conhecimentos","oid_conhecimento='"+oid_Conhecimento+"'");
            if ("S".equals(ed.edModelo.getDM_Gera_Fiscal()) && "C".equals(dm_tipo_documento))
            {
                Livro_FiscalED edLivro = new Livro_FiscalED(oid_Nota_Fiscal, "NF");
                edLivro.setOID_Conhecimento(oid_Conhecimento);
                new Livro_FiscalBD(this.sql).geraLivro_Fiscal_VendaDireta(edLivro, "S");
            }else{
            	this.sql.executarUpdate("DELETE FROM Livros_Fiscais " +
                        "WHERE oid_Nota_Fiscal = '"+ ed.getOid_nota_fiscal() + "'");

            }
            //*** VERIFICA DE DEVE MOVIMENTAR FINANCEIRO
	        if ("S".equals(ed.edModelo.getDM_Movimenta_Financeiro()))
	        {
                //*** Caso seje informado DATA de SAIDA(entrega), calcula parcelamentos pela mesmo caso contrario busca data ATUAL
                ed.setDt_emissao(JavaUtil.getValueDef(ed.getDt_emissao(), Data.getDataDMY()));
	            //*** Parcelamentos Financeiros - [PARCELAS]
	            Nota_FiscalBD.inclui_Parcelamento(ed, 0, 0);
	            //*** Gera Duplicatas - [Contas a Receber]
	            Nota_FiscalBD.geraDuplicatas(ed);
	        }

	        this.sql.executarUpdate(" UPDATE Notas_Fiscais SET " +
                                    "    oid_Conhecimento = "+JavaUtil.getSQLString(oid_Conhecimento)+
	        						"	,DM_Situacao = 'F'" +
	        						" WHERE oid_Nota_Fiscal = "+JavaUtil.getSQLString(oid_Nota_Fiscal));
	        this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Nota_Fiscal_EletronicaBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_em_Viagem(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Nota_Fiscal_EletronicaBD(sql).lista_em_Viagem(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Nota_Fiscal_EletronicaED getByRecord(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void reabre(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        if (ed.getOid_nota_fiscal().compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("C�digo da Nota Fiscal n�o foi informado!!!");
            throw exc;
        }
        this.inicioTransacao();
        new Nota_Fiscal_EletronicaBD(this.sql).reabre(ed);
        this.fimTransacao(true);
    }

    public boolean inclui_Lancamentos_Pagamento_Compromisso(Lote_CompromissoED ed) throws Excecoes {

        boolean aux = false;
        Lote_CompromissoED auxiliar = (Lote_CompromissoED) ed;
        this.inicioTransacao();
        Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
        if (ed.getDM_Contabiliza().equals("S")) {
            Nota_FiscalBD.inclui_Lancamentos_Pagamento_Compromisso(auxiliar);
            aux = true;
        }
        this.fimTransacao(true);
        return aux;
    }

    public boolean inclui_Lancamentos_Lote_Posto(Posto_CompromissoED ed) throws Excecoes {
        boolean aux = false;
        Posto_CompromissoED auxiliar = (Posto_CompromissoED) ed;

        this.inicioTransacao();
        Nota_Fiscal_EletronicaBD Nota_FiscalBD = new Nota_Fiscal_EletronicaBD(this.sql);
        if (ed.getDM_Contabiliza().equals("S")) {
            Nota_FiscalBD.inclui_Lancamentos_Lote_Posto(auxiliar);
            aux = true;
        }
        this.fimTransacao(true);
        return aux;
    }

    /** ------------ RELAT�RIOS ---------------- */
//  *** Notas Fiscais Matricial
    public String geraNotaFiscal_to_EmissorNFe(ArrayList listaNotasFiscais, String dtSaida, String hrSaida) throws Excecoes {
    	return "";
//        try {
//            this.inicioTransacao();
//            String toReturn = new Nota_Fiscal_TransacoesRL(this.sql).geraNotaFiscal_to_NFe(listaNotasFiscais, dtSaida, hrSaida);
//            this.fimTransacao(true);
//            return toReturn;
//        } catch (Excecoes e) {
//            this.abortaTransacao();
//            e.printStackTrace();
//            throw e;
//        } catch(RuntimeException e) {
//            this.abortaTransacao();
//            e.printStackTrace();
//            throw e;
//        } catch(Exception e){
//        	this.abortaTransacao();
//            e.printStackTrace();
//            throw new Excecoes();
//        }
    }

    //*** Notas Fiscais Matricial
    public String imprime_NotasFiscaisMatricial(ArrayList listaNotasFiscais, String dtSaida, String hrSaida) throws Excecoes {

        return "";
    }

//  *** Notas Fiscais Servico Matricial
    public String imprime_NotasFiscaisServicoMatricial(ArrayList listaNotasFiscais, String dtSaida, String hrSaida) throws Excecoes {

        return "";
    }

    //*** Notas Fiscal de Alter��o de Placa
    public String imprime_NotaAlteracaoPlaca(ArrayList listaNotasFiscais, String dtSaida, String hrSaida) throws Excecoes {

        return "";
    }

    //*** Notas Fiscais de Venda
    public void relNF_Venda(RelatorioED ed) throws Exception {

    }

    //*** Notas Fiscais de ENTRADA Confer�ncia
    public void relNFConferencia(RelatorioED ed) throws Exception {

    }
    //*** Notas Fiscais de ENTRADA Volume de Compra
    public void relNFVolume_de_Compras(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
            //new Nota_Fiscal_TransacoesRL(this.sql).relNFVolume_de_Compras(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    //*** Notas Fiscais de ENTRADA Emitidas pela Johann
    public void relNFEntrada_Emitida(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
//            new Nota_Fiscal_TransacoesRL(this.sql).relNFEntrada_Emitida(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    //*** Notas Fiscais de ENTRADA/Ntureza/Tipo
    public void relNFEntradaNT(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
//            new Nota_Fiscal_TransacoesRL(this.sql).relNFEntradaNT(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    //*** Relat�rio da Evolu��o dos Vendedores em     Valores /(vendas - Devolu��es)
    public void relNFEvolucaoVendedor(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
//            new Nota_Fiscal_TransacoesRL(this.sql).relNFEvolucaoVendedor(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    //*** Relat�rio de notas fiscais de venda (gen�rico)
    public void relNFiscaisVendas(RelatorioED ed) throws Exception {

        try {
            this.inicioTransacao();
//            new Nota_Fiscal_TransacoesRL(this.sql).relNFiscaisVendas(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    /**
     * Devolu��o Simb�lica Nota Fiscal de Venda
     * 1 - Le todas as NFs de saida ainda n�o devolvidas do cliente
     * 2 - Cria uma nota fiscal de devolucao para cada nota de sa�da , trocando o cfop
     * 3 - Para cada item da nf de saida cria um item na nota de devolucao
     * 4 - Atualiza a nota de saida para informar que j� foi devolvida e o oid da nf de devolucao, se devolveu ok.
     */
    public boolean geraNFDevolucaoSimbolica(Nota_Fiscal_EletronicaED ed) throws Excecoes {

    	// Gelson

    	ArrayList lstNFc = new ArrayList();
    	ArrayList lstNFi = new ArrayList();
    	String oid_NF_Saida = "", oid_NF_Devolucao = "";
    	long oid_Modelo_Nota_Fiscal =0, oid_Natureza_Operacao =0;

    	boolean finalizado = true;
        try {
            this.inicioTransacao();
            // Pega o oid_pessoa da unidade
            BancoUtil bancoUtil = new BancoUtil();
            String oid_Pessoa = bancoUtil.getTableStringValue("oid_pessoa","unidades","oid_unidade='"+ed.getOID_Unidade_Contabil()+"'");
        	//Pega o oid_modelo_nota_fiscal e oid_natureza_operacao
            Parametro_WmsED edPWms = new Parametro_WmsED();
            edPWms = new Parametro_WmsBD(this.sql).getByRecord(edPWms);
            oid_Modelo_Nota_Fiscal = bancoUtil.getTableIntValue("oid_Modelo_Nota_Fiscal","Modelos_Notas_Fiscais","cd_Modelo_Nota_Fiscal='"+edPWms.getCd_Modelo_Nota_Fiscal()+"'");
            // Busca todas as notas fiscais de saida do cliente n�o devolvidas
            ed.setDm_tipo_nota_fiscal("S");
            ed.setDm_Devolvido("N");
            ed.setDM_Situacao("F");
            lstNFc = new Nota_Fiscal_EletronicaBD(this.sql).lista(ed);
            // Itera as notas retornadas para geracao das nfs de devolu��o simb�lica
            for (int i = 0; i <lstNFc.size(); i++){
            	Nota_Fiscal_EletronicaED edNFc = new Nota_Fiscal_EletronicaED();
            	edNFc = (Nota_Fiscal_EletronicaED)lstNFc.get(i);

                String dm_gera_devolucao = bancoUtil.getTableStringValue("dm_gera_devolucao","Modelos_Notas_Fiscais","oid_Modelo_Nota_Fiscal="+edNFc.getOid_modelo_nota_fiscal());

                if ("S".equals(dm_gera_devolucao)){

	            	//Guarda o oid da nota para buscar seus itens e atualizar a NF no final
	            	oid_NF_Saida = edNFc.getOid_nota_fiscal();
	            	// Coloca a pessoa dona da nota de saida como destinat�rio/fornecedor
	            	edNFc.setOid_pessoa_fornecedor(edNFc.getOid_pessoa());
	            	edNFc.setOid_pessoa_destinatario(edNFc.getOid_pessoa());
	            	// Coloca o armazenador como dono da nota
	            	edNFc.setOid_pessoa(oid_Pessoa);
	            	// Troca o modelo da nota
	            	edNFc.setOid_modelo_nota_fiscal(oid_Modelo_Nota_Fiscal);
                    // Busca o oid da pessoa de origem e destino da nota fiscal para achar o Estado de Origem e Destino
	            	// para definir o oid_Natureza_Operacao.
	            	String oidPessoaOrigem = bancoUtil.getTableStringValue("oid_Pessoa","unidades","oid_unidade = "+edNFc.getOID_Unidade());
	                String oidPessoaDestino=edNFc.getOid_pessoa_fornecedor();
	                CidadeBean edCidade_Orig = new CidadeBean();
	                CidadeBean edCidade_Dest = new CidadeBean();
	                try{
		                //*** Estado ORIGEM
	                	edCidade_Orig = CidadeBean.getByOID(bancoUtil.getTableIntValue("oid_Cidade",
                                                                                    "Pessoas",
                                                                                    "oid_Pessoa = '"+oidPessoaOrigem+"'"));

	                	//*** Estado DESTINO
	                	edCidade_Dest = CidadeBean.getByOID(bancoUtil.getTableIntValue("oid_Cidade",
	                        														"Pessoas",
	                        														"oid_Pessoa = '"+oidPessoaDestino+"'"));
	                } catch(Exception exc) {
	                	throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraNFDevolucaoSimbolica()");
	                }

	                if (edCidade_Orig.getCD_Estado().equals(edCidade_Dest.getCD_Estado())){
	                    oid_Natureza_Operacao = bancoUtil.getTableIntValue("oid_natureza_operacao", "Naturezas_Operacoes", "cd_natureza_operacao = '"+edPWms.getCd_Cfop_Retorno_Simbolico()+"'");
	                }else{
	                    oid_Natureza_Operacao = bancoUtil.getTableIntValue("oid_natureza_operacao", "Naturezas_Operacoes", "cd_natureza_operacao = '"+edPWms.getCd_Cfop_Retorno_Simbolico_Interestadual()+"'");
	                }

	            	// Troca o oid da natureza da nf
	            	edNFc.setOid_natureza_operacao(oid_Natureza_Operacao);
	            	edNFc.setOid_nota_fiscal("");
	            	edNFc.setNr_nota_fiscal(0);
	            	edNFc.setNm_serie("NF1");
	            	edNFc.setDM_Situacao("N");
	            	edNFc.setDM_Impresso("N");
	            	edNFc.setDm_tipo_nota_fiscal("D");

	            	String dm_obs_nota_saida_wms= bancoUtil.getTableStringValue("dm_obs_nota_saida_wms","Clientes","oid_cliente = "+oidPessoaDestino);

	            	if (!"S".equals(dm_obs_nota_saida_wms)){
		            	edNFc.setDm_observacao("");
		            	edNFc.setTx_Observacao("");
	            	}
	            	// Gelson
	            	ed.edModelo.getDM_Movimenta_Estoque();
	            	edNFc.setOid_Nota_Fiscal_Original(oid_NF_Saida);
	            	// Insere a nota e pega ela no mesmo objeto, j� com novo oid
	            	edNFc = new Nota_Fiscal_EletronicaBD(this.sql).inclui(edNFc);
	            	edNFc = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(edNFc);
	            	oid_NF_Devolucao = edNFc.getOid_nota_fiscal();
	            	// Busca todos os itens da nota fiscal de saida
	            	Item_Nota_Fiscal_TransacoesED edNFi = new Item_Nota_Fiscal_TransacoesED();
	            	edNFi.setOID_Nota_Fiscal(oid_NF_Saida);
	            	lstNFi = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(edNFi);
	            	//Itera os itens da nota de sa�da para a gera��o dos itens da devolu��o
	            	for (int x = 0; x <lstNFi.size(); x++){
	            		edNFi = (Item_Nota_Fiscal_TransacoesED)lstNFi.get(x);
	            		edNFi.setOID_Item_Nota_Fiscal(0);
	            		// Pega o oid da natureza de devolucao do item
	            		edNFi.setOID_Nota_Fiscal(edNFc.getOid_nota_fiscal());// Coloca o oid da nf de devolucao para link com os itens
	                	edNFi.setOid_natureza_operacao(oid_Natureza_Operacao);
	            		// Grava o item da nf devolucao
	            		new Item_Nota_Fiscal_TransacoesBD(this.sql).inclui(edNFi);

	            	}
	            	// Busca Novamente a nota fiscal de devolu��o j� com os valores atualizados
	            	edNFc = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(edNFc);

	            	//Finaliza a nota de devolucao e retorna true se foi possivel dar baixa na nf de entrada
	            	finalizado = this.finalizaNF_Devolucao(edNFc,false);

	            	// Pega a nota fiscal de saida para marcar que j� foi devolvida e colocar o oid da nf de devolu��o
	            	Nota_Fiscal_EletronicaED edNFs = new Nota_Fiscal_EletronicaED();
	            	edNFs.setOid_nota_fiscal(oid_NF_Saida);
	            	edNFs = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(edNFs);
	            	edNFs.setOid_Nota_Fiscal_Devolucao(oid_NF_Devolucao);
	            	edNFs.setDm_Devolvido("S");
	            	new Nota_Fiscal_EletronicaBD(this.sql).altera(edNFs);
                }
            }
            this.fimTransacao(true);
            if ( lstNFc.size() <=0 ) throw new Mensagens("N�o h� notas de sa�das para devolu��o simb�lica nesta sele��o!");

        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        return finalizado;
    }

    public ArrayList listaNFparaFaturamento(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Nota_Fiscal_EletronicaBD(sql).listaNFparaFaturamento(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public String FaturamentoNotaFiscal(Nota_Fiscal_EletronicaED ed) throws Excecoes {

    	ArrayList notas = new ArrayList();
        try {
            this.inicioTransacao();
            notas = new Nota_Fiscal_EletronicaBD(sql).listaNFparaFaturamento(ed);

            //Faz o faturamento
            return new Nota_Fiscal_EletronicaBD(sql).FaturamentoNotaFiscal(notas);


        } finally {
            this.fimTransacao(true);
        }
    }


    public void geraNFe(ArrayList listaNotasFiscais, String dtSaida, String hrSaida, HttpServletResponse response) throws Excecoes {

        try {
            this.inicioTransacao();
            NfeNotaFiscal nota = new Nota_Fiscal_EletronicaBD(this.sql).geraNFe(listaNotasFiscais, dtSaida, hrSaida);
            this.fimTransacao(true);
            if(nota.getcStat().intValue()==100){

            	String arqPDF = this.imprimeDANFE(nota);

            	//envio e-mail
            	String cliente = ManipulaString.limpaCampo(nota.getDestinatario().getCnpj());
            	PessoaBean pes = PessoaBean.getByOID(cliente);
            	System.out.print(pes.getEMail()+"....");
            	if(JavaUtil.doValida(pes.getEMail())){
            		if(Parametro_FixoED.getInstancia().getNM_Empresa().equals("TSG")){
            			System.out.println("EMAIL....");
                    	String caminho = "/data/doc-e/nfe/"+ManipulaString.limpaCampo(nota.getEmitente().getCnpj())+"/";
                    	String arq = nota.getChaveAcesso()+"-procNFe.xml";
                    	//Aqui manda o e-mail...
                    	EmailED mail = new EmailED();
                    	mail.setNM_Host("smtp.transmiro.com.br"); //host
                    	mail.setNM_Email_Origem("nfe@silveiragomes.com.br"); //sender
                    	mail.setNM_Username("nfe@transmiro.com.br"); //user
                    	mail.setNM_Protocolo("smtp");
                    	mail.setNM_Senha("tr20nf15"); //pass
                    	mail.setNM_Subject("TSG, arquivos XML e PDF de emissao da NFE "+nota.getNNF()); //subject
                    	mail.setNM_Email_Destino(pes.getEMail()+";nfe@silveiragomes.com.br"); //to
//                    	mail.setNM_Email_Destino("teo@nalthus.com.br"); //to
                    	mail.setTX_Mensagem(" </br><strong>A TRANSPORTES SILVEIRA GOMES acaba de EMITIR a NFe acima para sua empresa.</strong></br> Em anexo os arquivos XML e PDF desta NFe.</br>" +
                    			"<font color=red>*** Esta eh uma mensagem automatica, <strong>NAO RESPONDA ESTA MENSAGEM!</strong> ***</font></br></br></br></br>"); //message
                    	mail.setNM_Path(caminho); //path of attachment
                    	mail.setNM_File(arq); //file to attach
                    	mail.setNM_Path2(Configuracoes.getInstance().getAppDir() + "/relatorios/out/"); //path of attachment
                    	mail.setNM_File2("DANFE" + nota.getNNF() + ".pdf"); //file to attach
                    	mail.setNR_Porta("25"); //port to server
                    	mail.setDM_Autenticacao("S"); //auth: S / N
//                    	Mailer.sendMail(mail);
            		} else {
            			System.out.println("EMAIL....");
                    	String caminho = "/data/doc-e/nfe/"+ManipulaString.limpaCampo(nota.getEmitente().getCnpj())+"/";
                    	String arq = nota.getChaveAcesso()+"-procNFe.xml";
                    	//Aqui manda o e-mail...
                    	EmailED mail = new EmailED();
                    	mail.setNM_Host("smtp.mirolog.com.br"); //host
                    	mail.setNM_Email_Origem("nfe@mirolog.com.br"); //sender
                    	mail.setNM_Username("nfe@mirolog.com.br"); //user
                    	mail.setNM_Protocolo("smtp");
                    	mail.setNM_Senha("tr20nf15"); //pass
                    	mail.setNM_Subject("TRANSMIRO, arquivos XML e PDF de emissao da NFE "+nota.getNNF()); //subject
                    	mail.setNM_Email_Destino(pes.getEMail()+";nfe@mirolog.com.br"); //to
//                    	mail.setNM_Email_Destino("teo@nalthus.com.br"); //to
                    	mail.setTX_Mensagem(" </br><strong>A TRANSMIRO acaba de EMITIR a NFe acima para sua empresa.</strong></br> Em anexo os arquivos XML e PDF desta NFe.</br>" +
                    			"<font color=red>*** Esta eh uma mensagem automatica, <strong>NAO RESPONDA ESTA MENSAGEM!</strong> ***</font></br></br></br></br>"); //message
                    	mail.setNM_Path(caminho); //path of attachment
                    	mail.setNM_File(arq); //file to attach
                    	mail.setNM_Path2(Configuracoes.getInstance().getAppDir() + "/relatorios/out/"); //path of attachment
                    	mail.setNM_File2("DANFE" + nota.getNNF() + ".pdf"); //file to attach
                    	mail.setNR_Porta("25"); //port to server
                    	mail.setDM_Autenticacao("S"); //auth: S / N
//                    	Mailer.sendMail(mail);
            		}
            	}
            	response.setHeader("application/pdf", "Content-Type");
                response.setHeader("Content-Disposition:","inline; filename=" + "DANFE" + nota.getNNF() + ".pdf");
                response.setContentType("application/pdf");
                new EnviaPDF().enviaArquivo(response, arqPDF);

//            	this.imprimeDANFE(nota, response);

            } else if(nota.getcStat().intValue()==110){
            	this.denegacaoNFVenda((Nota_Fiscal_EletronicaED)listaNotasFiscais.get(0));
            }
            else {
            	throw new Excecoes("NF RECUSADA! C�d. "+nota.getcStat().intValue()+" | Desc.:"+nota.getxMotivo());
            }
        } catch (Excecoes e) {
            this.abortaTransacao();
            e.printStackTrace();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            e.printStackTrace();
            throw e;
        } catch(Exception e){
        	this.abortaTransacao();
            e.printStackTrace();
            throw new Excecoes();
        }
    }

    public String imprimeDANFE(NfeNotaFiscal nota) throws Exception {

    	NfeServicos servico = new NfeServicos();
        HashMap parametros = servico.getParametrosNfeJasper(nota);
        Collection<BeanDanfeItens> danfeItens = servico.getDanfeItens();

        String pathPDF = "";

//        String img = Configuracoes.getInstance().getAppDir() + "/logo.png";
        return pathPDF;
    }

    public void imprimeDANFE(Nota_Fiscal_EletronicaED ed, HttpServletResponse response) throws Exception {


    }

    public String geraNotaFiscal_to_NFe(ArrayList listaNotasFiscais, String dtSaida, String hrSaida) throws Excecoes {
    	return "";
//        try {
//            this.inicioTransacao();
//            String toReturn = new Nota_Fiscal_TransacoesRL(this.sql).geraNotaFiscal_to_NFe(listaNotasFiscais, dtSaida, hrSaida);
//            this.fimTransacao(true);
//            return toReturn;
//        } catch (Excecoes e) {
//            this.abortaTransacao();
//            e.printStackTrace();
//            throw e;
//        } catch(RuntimeException e) {
//            this.abortaTransacao();
//            e.printStackTrace();
//            throw e;
//        } catch(Exception e){
//        	this.abortaTransacao();
//            e.printStackTrace();
//            throw new Excecoes();
//        }
    }

    public String updateRetornoLote(Nota_Fiscal_EletronicaED ed, NfeLote retorno) throws Excecoes {

        try {
            this.inicioTransacao();
            String toReturn = new Nota_Fiscal_EletronicaBD(this.sql).updateRetornoLote(ed, retorno);
            this.fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            e.printStackTrace();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            e.printStackTrace();
            throw e;
        } catch(Exception e){
        	this.abortaTransacao();
            e.printStackTrace();
            throw new Excecoes();
        }
    }

    public String updateRetornoNFE(Nota_Fiscal_EletronicaED ed, NfeLote retorno, NfeRetornoEnvioLote ret) throws Excecoes {

        try {
            this.inicioTransacao();
            String toReturn = new Nota_Fiscal_EletronicaBD(this.sql).updateRetornoNFE(ed, retorno, ret);
            this.fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            e.printStackTrace();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            e.printStackTrace();
            throw e;
        } catch(Exception e){
        	this.abortaTransacao();
            e.printStackTrace();
            throw new Excecoes();
        }
    }

    public void enviaNFE_cancelada(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Nota_Fiscal_EletronicaBD(this.sql).cancelaNFE(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

//  *** Busca Oid, Numero, S�rie da Nota Fiscal
	public Nota_Fiscal_EletronicaED numeraNFe(Nota_Fiscal_EletronicaED ed,String nmSerie) throws Exception {
		try {
            this.inicioTransacao();
            ed = new Nota_Fiscal_EletronicaBD(this.sql).numeraNFe(ed, nmSerie);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        return ed;
	}
	
	public void incluiInutilizacao(String emissor, String xJust, UsuarioED user, NfeInutilizacao ret) throws Excecoes {

		try {

			this.inicioTransacao();

			new Nota_Fiscal_EletronicaBD(this.sql).incluiInutilizacaoNumero(emissor, xJust, user, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao incluir inutilizacao NFe", e, this
					.getClass().getName(), "incluiInutilizacaoNumero()");
		}
	}

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql != null)
            this.abortaTransacao();
    }
}