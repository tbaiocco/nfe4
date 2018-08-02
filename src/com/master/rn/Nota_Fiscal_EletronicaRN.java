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

import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.cte.model.Empresa;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.nfe.core.BeanDanfeItens;
import br.nfe.model.NfeInutilizacao;
import br.nfe.model.NfeLote;
import br.nfe.model.NfeNotaFiscal;
import br.nfe.model.NfeRetornoEnvioLote;
import br.nfe.utils.Configuracoes;
import br.servicos.NfeServicos;


public class Nota_Fiscal_EletronicaRN extends Transacao {
	
	Empresa empresa;

    public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Nota_Fiscal_EletronicaRN() {
    	super();
    }
    
    public Nota_Fiscal_EletronicaRN(Empresa empresa) {
		super(empresa);
		this.empresa = empresa;
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
	                	edCidade_Orig = CidadeBean.getByOID(null, bancoUtil.getTableIntValue("oid_Cidade",
                                                                                    "Pessoas",
                                                                                    "oid_Pessoa = '"+oidPessoaOrigem+"'"));

	                	//*** Estado DESTINO
	                	edCidade_Dest = CidadeBean.getByOID(null, bancoUtil.getTableIntValue("oid_Cidade",
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

    public TNFe geraNFe(String oid_nota_fiscal, String dtSaida, String hrSaida) throws Excecoes {
    	try {
            this.inicioTransacao();
            TNFe nota = new Nota_Fiscal_EletronicaBD(this.sql).geraNFe(empresa, new Nota_Fiscal_EletronicaED(oid_nota_fiscal), dtSaida, hrSaida);
            this.fimTransacao(true);
            return nota;
            
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

    public void geraNFe_old(ArrayList listaNotasFiscais, String dtSaida, String hrSaida, HttpServletResponse response) throws Excecoes {

//        try {
//            this.inicioTransacao();
//            TNFe nota = new Nota_Fiscal_EletronicaBD(this.sql).geraNFe(listaNotasFiscais, dtSaida, hrSaida);
//            this.fimTransacao(true);
////            if(nota.getcStat().intValue()==100){
////
////            	String arqPDF = this.imprimeDANFE(nota);
////
////            	//envio e-mail
////            	String cliente = ManipulaString.limpaCampo(nota.getDestinatario().getCnpj());
////            	PessoaBean pes = PessoaBean.getByOID(cliente);
////            	System.out.print(pes.getEMail()+"....");
////            	if(JavaUtil.doValida(pes.getEMail())){
////            		if(Parametro_FixoED.getInstancia().getNM_Empresa().equals("TSG")){
////            			System.out.println("EMAIL....");
////                    	String caminho = "/data/doc-e/nfe/"+ManipulaString.limpaCampo(nota.getEmitente().getCnpj())+"/";
////                    	String arq = nota.getChaveAcesso()+"-procNFe.xml";
////                    	//Aqui manda o e-mail...
////                    	EmailED mail = new EmailED();
////                    	mail.setNM_Host("smtp.transmiro.com.br"); //host
////                    	mail.setNM_Email_Origem("nfe@silveiragomes.com.br"); //sender
////                    	mail.setNM_Username("nfe@transmiro.com.br"); //user
////                    	mail.setNM_Protocolo("smtp");
////                    	mail.setNM_Senha("tr20nf15"); //pass
////                    	mail.setNM_Subject("TSG, arquivos XML e PDF de emissao da NFE "+nota.getNNF()); //subject
////                    	mail.setNM_Email_Destino(pes.getEMail()+";nfe@silveiragomes.com.br"); //to
//////                    	mail.setNM_Email_Destino("teo@nalthus.com.br"); //to
////                    	mail.setTX_Mensagem(" </br><strong>A TRANSPORTES SILVEIRA GOMES acaba de EMITIR a NFe acima para sua empresa.</strong></br> Em anexo os arquivos XML e PDF desta NFe.</br>" +
////                    			"<font color=red>*** Esta eh uma mensagem automatica, <strong>NAO RESPONDA ESTA MENSAGEM!</strong> ***</font></br></br></br></br>"); //message
////                    	mail.setNM_Path(caminho); //path of attachment
////                    	mail.setNM_File(arq); //file to attach
////                    	mail.setNM_Path2(Configuracoes.getInstance().getAppDir() + "/relatorios/out/"); //path of attachment
////                    	mail.setNM_File2("DANFE" + nota.getNNF() + ".pdf"); //file to attach
////                    	mail.setNR_Porta("25"); //port to server
////                    	mail.setDM_Autenticacao("S"); //auth: S / N
//////                    	Mailer.sendMail(mail);
////            		} else {
////            			System.out.println("EMAIL....");
////                    	String caminho = "/data/doc-e/nfe/"+ManipulaString.limpaCampo(nota.getEmitente().getCnpj())+"/";
////                    	String arq = nota.getChaveAcesso()+"-procNFe.xml";
////                    	//Aqui manda o e-mail...
////                    	EmailED mail = new EmailED();
////                    	mail.setNM_Host("smtp.mirolog.com.br"); //host
////                    	mail.setNM_Email_Origem("nfe@mirolog.com.br"); //sender
////                    	mail.setNM_Username("nfe@mirolog.com.br"); //user
////                    	mail.setNM_Protocolo("smtp");
////                    	mail.setNM_Senha("tr20nf15"); //pass
////                    	mail.setNM_Subject("TRANSMIRO, arquivos XML e PDF de emissao da NFE "+nota.getNNF()); //subject
////                    	mail.setNM_Email_Destino(pes.getEMail()+";nfe@mirolog.com.br"); //to
//////                    	mail.setNM_Email_Destino("teo@nalthus.com.br"); //to
////                    	mail.setTX_Mensagem(" </br><strong>A TRANSMIRO acaba de EMITIR a NFe acima para sua empresa.</strong></br> Em anexo os arquivos XML e PDF desta NFe.</br>" +
////                    			"<font color=red>*** Esta eh uma mensagem automatica, <strong>NAO RESPONDA ESTA MENSAGEM!</strong> ***</font></br></br></br></br>"); //message
////                    	mail.setNM_Path(caminho); //path of attachment
////                    	mail.setNM_File(arq); //file to attach
////                    	mail.setNM_Path2(Configuracoes.getInstance().getAppDir() + "/relatorios/out/"); //path of attachment
////                    	mail.setNM_File2("DANFE" + nota.getNNF() + ".pdf"); //file to attach
////                    	mail.setNR_Porta("25"); //port to server
////                    	mail.setDM_Autenticacao("S"); //auth: S / N
//////                    	Mailer.sendMail(mail);
////            		}
////            	}
////            	response.setHeader("application/pdf", "Content-Type");
////                response.setHeader("Content-Disposition:","inline; filename=" + "DANFE" + nota.getNNF() + ".pdf");
////                response.setContentType("application/pdf");
////                new EnviaPDF().enviaArquivo(response, arqPDF);
////
//////            	this.imprimeDANFE(nota, response);
////
////            } else if(nota.getcStat().intValue()==110){
////            	
//////            	this.denegacaoNFVenda((Nota_Fiscal_EletronicaED)listaNotasFiscais.get(0));
////            }
////            else {
////            	throw new Excecoes("NF RECUSADA! C�d. "+nota.getcStat().intValue()+" | Desc.:"+nota.getxMotivo());
////            }
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

    public String updateRetornoNFE(Nota_Fiscal_EletronicaED ed, TRetEnviNFe retorno, TEnviNFe enviNFe) throws Excecoes {

        try {
            this.inicioTransacao();
            String toReturn = new Nota_Fiscal_EletronicaBD(this.sql).updateRetornoNFE(ed, retorno, enviNFe);
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

    public void enviaNFE_cancelada(Empresa empresa, Nota_Fiscal_EletronicaED ed, ConfiguracoesWebNfe config) throws Excecoes {

        try {
            this.inicioTransacao();
            new Nota_Fiscal_EletronicaBD(this.sql).cancelaNFE(empresa, ed, config);
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