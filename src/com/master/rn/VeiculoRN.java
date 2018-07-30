package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.VeiculoBD;
import com.master.ed.VeiculoED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

/**
 * @author Cristian Vianna Garcia
 * @serial Cadastro de Veículos
 * @serialData 06/2007
 */
public class VeiculoRN extends Transacao {

    public VeiculoRN() {
    }

	/**
	 * Recebe Transação e a mantem aberta
	 * @param executasql
	 */
	public VeiculoRN(ExecutaSQL executasql) {
		super(executasql);
	}


    public VeiculoED inclui(VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new VeiculoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new VeiculoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new VeiculoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new VeiculoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaSituacaoFrota (VeiculoED ed) throws Excecoes {
	    inicioTransacao ();
	    try {
	      return new VeiculoBD(this.sql).listaSituacaoFrota (ed);
	    }
	    finally {
	      this.fimTransacao (false);
	    }
	  }

    public void lista(VeiculoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new VeiculoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public VeiculoED getByRecord(VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new VeiculoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(VeiculoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		VeiculoED edQBR = new VeiculoBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Veiculo()== "" ? null : edQBR);
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(VeiculoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new VeiculoBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns013"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNr_Frota()))
				nm_Filtro+=" Frota=" + ed.getNr_Frota();
			if (bu.doValida(ed.getNr_Placa()))
				nm_Filtro+=" Placa=" + ed.getNr_Placa();
			if (bu.doValida(ed.getNm_Modelo_Veiculo()))
				nm_Filtro+=" Modelo=" + ed.getNm_Modelo_Veiculo();
			if (bu.doValida(ed.getNm_Marca_Veiculo()))
				nm_Filtro+=" Marca=" + ed.getNm_Marca_Veiculo();
			if (bu.doValida(ed.getNm_Tipo_Veiculo()))
				nm_Filtro+=" Tipo=" + ed.getNm_Tipo_Veiculo();
			if (bu.doValida(ed.getNm_Unidade()))
				nm_Filtro+=" Unidade=" + ed.getNm_Unidade();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relatorioSituacaoFrota(VeiculoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new VeiculoBD(sql).listaSituacaoFrota(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns325"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Modelo_Veiculo()))
				nm_Filtro+=" Modelo=" + ed.getNm_Modelo_Veiculo();
			if (bu.doValida(ed.getNm_Unidade()))
				nm_Filtro+=" Unidade=" + ed.getNm_Unidade();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    /**
     * processaRL
     * Processa solicitação de relatorio OL retornando sempre um PDF.
     * @param rel = Qual o relatorio a ser chamado
     * @param Obj = Um bean populado com parametros para a geracao do relatorio
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    public void processaRL(String rel, Object Obj, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	VeiculoED ed = (VeiculoED)Obj;

		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		}
		if ("2".equals(rel)) {
			this.relatorioSituacaoFrota(ed, request, response);
		}
    }

    /**
     * processaOL
     * Processa solicitação de tela OL retornando sempre arquivo XML com a seguinte estrutura.
     * <cad>
     * 		<item campo=valor />
     * </cad>
     * @param acao
     * @param Obj
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    public void processaOL(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	VeiculoED ed = (VeiculoED)Obj;

    	//Prepara a saída
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuploFrota(ed,acao)) {
    			out.println("<ret><item oknok='Veículo já existente com este nr de frota!'/></ret>");
    		} else {
	    		if (checkDuploPlaca(ed,acao) ) {
	    			out.println("<ret><item oknok='Veículo já existente com esta placa !'/></ret>");
	    		} else {
		    		ed = this.inclui(ed);
		    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Veiculo() + "' /></ret>");
	    		}
    		}
    	} else
		if ("A".equals(acao)) {
    		if (checkDuploFrota(ed,acao) ) {
    			out.println("<ret><item oknok='Veículo já existente com este nr de frota !'/></ret>");
    		} else {
	    		if (checkDuploPlaca(ed,acao) ) {
	    			out.println("<ret><item oknok='Veículo já existente com esta placa !'/></ret>");
	    		} else {
					this.altera(ed);
					out.println("<ret><item oknok='AOK' /></ret>");
	    		}
    		}
		} else
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Veiculo em uso!' /></ret>");
			} else {
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
			}
		} else
		if ("C".equals(acao)) {
			VeiculoED edVolta = this.getByRecord(ed);
			if (Utilitaria.doValida(edVolta.getOid_Veiculo())) {
				out.println("<cad>");
				out.println(montaRegistro(edVolta));
				out.println("</cad>");
			} else
				out.println("<ret><item oknok='Veiculo não encontrado!' /></ret>");
		} else
		if ("lookup".equals(acao)) {
			ArrayList lst = this.lista(ed);
			if (!lst.isEmpty()) {
				String saida = null;
				out.println("<cad>");
				for (int i=0; i<lst.size(); i++){
					VeiculoED edVolta = (VeiculoED)lst.get(i);
					saida = "<item ";
					saida += "oid_Veiculo='" + edVolta.getOid_Veiculo() + "' ";
					saida += "nr_Placa='" + edVolta.getNr_Placa() + "' ";
					saida += "nm_Tipo_Veiculo='" + edVolta.getNm_Tipo_Veiculo() + "' ";
					saida += "/>";
					out.println(saida);
				}
				out.println("</cad>");
			} else {
				out.println("<ret><item oknok='Veículo não encontrado!'/></ret>");
			}
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		if ("LSF".equals(acao)) {
			lst = this.listaSituacaoFrota(ed);
		} else{
			lst = this.lista(ed);
		}
		for (int i=0; i<lst.size(); i++){
			VeiculoED edVolta = new VeiculoED();
			edVolta = (VeiculoED)lst.get(i);
			if ("L".equals(acao) || "LSF".equals(acao)) {
				saida = montaRegistro(edVolta);
			}else
			if ("CB".equals(acao)) {
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Veiculo() + "'>";
				saida +=  edVolta.getNr_Frota();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    /**
	 * @param edVolta
	 * @return
	 */
    private String montaRegistro( VeiculoED edVolta ){
    	String saida;
		saida = "<item ";
		saida += "oid_Veiculo='" + edVolta.getOid_Veiculo() + "' ";
		saida += "nr_Frota='" + edVolta.getNr_Frota() + "' ";
		saida += "nr_Placa='" + edVolta.getNr_Placa() + "' ";
		saida += "oid_Modelo_Veiculo='" + edVolta.getOid_Modelo_Veiculo() + "' ";
		saida += "nm_Modelo_Veiculo='" + edVolta.getNm_Modelo_Veiculo() + "' ";
		saida += "nm_Marca_Veiculo='" + edVolta.getNm_Marca_Veiculo() + "' ";
		saida += "nm_Tipo_Veiculo='" + edVolta.getNm_Tipo_Veiculo() + "' ";
		saida += "nr_Odometro='" + FormataValor.formataValorBT( edVolta.getNr_Odometro(), 1 ) + "' ";
		saida += "dm_Odometro_Maximo='" + FormataValor.formataValorBT(edVolta.getDm_Odometro_Maximo(),0) + "' ";
		saida += "nr_Kilometragem_Atual='" + FormataValor.formataValorBT( edVolta.getNr_Kilometragem_Atual(),1) + "' ";
		saida += "dm_Qtd_Estepes='" + edVolta.getDm_Qtd_Estepes() + "' ";
		saida += "nm_Tipo_Roda='" + edVolta.getNm_Tipo_Roda() + "' ";
		saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
		saida += "nm_Unidade='" + edVolta.getNm_Unidade() + "' ";
		saida += "dm_Tipo_Chassis='" + edVolta.getDm_Tipo_Chassis() + "' ";
		saida += "nm_Dimensao_Pneu='" + edVolta.getNm_Dimensao_Pneu() + "' ";
		saida += "dm_Combustivel='" + edVolta.getDm_Combustivel() + "' ";

		saida += "oid_Unidade_Federativa='" + edVolta.getOid_Unidade_Federativa() + "' ";
		saida += "oid_Motorista='" + edVolta.getOid_Motorista() + "' ";
		saida += "oid_Centro_Custo='" + edVolta.getOid_Centro_Custo() + "' ";
		saida += "nm_Centro_Custo='" + edVolta.getNm_Centro_Custo() + "' ";

		saida += "nr_Capacidade_Tanque='" + edVolta.getNr_Capacidade_Tanque() + "' ";
		saida += "nr_Media_Inferior='" + FormataValor.formataValorBT(edVolta.getNr_Media_Inferior(),1) + "' ";
		saida += "nr_Media_Superior='" + FormataValor.formataValorBT(edVolta.getNr_Media_Superior(),1) + "' ";
		saida += "nr_Ano_Fabricacao='" + FormataValor.formataValorBT(edVolta.getNr_Ano_Fabricacao(),0) + "' ";
		saida += "nr_Ano_Modelo='" + FormataValor.formataValorBT(edVolta.getNr_Ano_Modelo(),0) + "' ";
		saida += "nr_Chassis='" + edVolta.getNr_Chassis() + "' ";
		saida += "nr_Renavan='" + edVolta.getNr_Renavan() + "' ";
		saida += "nm_Cor='" + edVolta.getNm_Cor() + "' ";

		saida += "nr_Total='" + edVolta.getNr_Total() + "' ";
		saida += "nr_Qtd_N0='" + edVolta.getNr_Qtd_N0() + "' ";
		saida += "nr_Qtd_R1='" + edVolta.getNr_Qtd_R1() + "' ";
		saida += "nr_Qtd_R2='" + edVolta.getNr_Qtd_R2() + "' ";
		saida += "nr_Qtd_R3='" + edVolta.getNr_Qtd_R3() + "' ";
		saida += "nr_Qtd_R4='" + edVolta.getNr_Qtd_R4() + "' ";
		saida += "nr_Qtd_R5='" + edVolta.getNr_Qtd_R5() + "' ";
		saida += "nr_Qtd_R6='" + edVolta.getNr_Qtd_R6() + "' ";
		saida += "nr_Pct_N0='" + FormataValor.formataValorBT( edVolta.getNr_Pct_N0(), 2 ) + "' ";
		saida += "nr_Pct_R1='" + FormataValor.formataValorBT( edVolta.getNr_Pct_R1(), 2 ) + "' ";
		saida += "nr_Pct_R2='" + FormataValor.formataValorBT( edVolta.getNr_Pct_R2(), 2 ) + "' ";
		saida += "nr_Pct_R3='" + FormataValor.formataValorBT( edVolta.getNr_Pct_R3(), 2 ) + "' ";
		saida += "nr_Pct_R4='" + FormataValor.formataValorBT( edVolta.getNr_Pct_R4(), 2 ) + "' ";
		saida += "nr_Pct_R5='" + FormataValor.formataValorBT( edVolta.getNr_Pct_R5(), 2 ) + "' ";
		saida += "nr_Pct_R6='" + FormataValor.formataValorBT( edVolta.getNr_Pct_R6(), 2 ) + "' ";

		saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
		saida += "/>";
		return saida;
    }

    public boolean checkDuploFrota ( VeiculoED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	BancoUtil bu = new BancoUtil();
    	VeiculoED edChk = new VeiculoED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNr_Frota(ed.getNr_Frota());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && bu.doValida(edChk.getOid_Veiculo()))
    		ret = true;
    	else
    	if ("A".equals(acao) && bu.doValida(edChk.getOid_Veiculo()) ) {
			if (!ed.getOid_Veiculo().equals(edChk.getOid_Veiculo()) )
				ret = true ;
    	}
    	return ret;
    }

    public boolean checkDuploPlaca ( VeiculoED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	BancoUtil bu = new BancoUtil();
    	VeiculoED edChk = new VeiculoED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNr_Placa(ed.getNr_Placa());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && bu.doValida(edChk.getOid_Veiculo()))
    		ret = true;
    	else
        	if ("A".equals(acao) && bu.doValida(edChk.getOid_Veiculo()) ) {
    			if (!ed.getOid_Veiculo().equals(edChk.getOid_Veiculo()) )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso ( VeiculoED ed ) throws Excecoes {
		try {
			// Colocar este teste apontando para pneus..... e historico de pneus ...
			VeiculoED veiculoED = new VeiculoED();
			veiculoED.setOid_Empresa(ed.getOid_Empresa());
			veiculoED.setOid_Veiculo(ed.getOid_Veiculo());
			this.inicioTransacao();
			return (new VeiculoBD(this.sql).lista(veiculoED).size()>0 ? true : false);
        } finally {
            this.fimTransacao(false);
        }
    }

    // Busca o eixo da posicao
    public String getEixo(String pos, VeiculoED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new VeiculoBD(this.sql).getEixo(pos, ed);
        } finally {
            this.fimTransacao(false);
        }

    }

	/**
	 *  Retorna um array com todas as posicoes possiveis para um veículo
	 * @param veicED
	 * @return addPosicao = Array com todas as posicoes possiveis
	 * @throws Excecoes
	 */
	public ArrayList pegaPosicoes(VeiculoED veicED) throws Excecoes {

		ArrayList addPosicao = new ArrayList();

		if (veicED.getDm_Tipo_Chassis()==1 || veicED.getDm_Tipo_Chassis()==2 ||
			veicED.getDm_Tipo_Chassis()==3 || veicED.getDm_Tipo_Chassis()==4 ||
			veicED.getDm_Tipo_Chassis()==5 || veicED.getDm_Tipo_Chassis()==11 ||
			veicED.getDm_Tipo_Chassis()==17 | veicED.getDm_Tipo_Chassis()==19 ||
			veicED.getDm_Tipo_Chassis()==20){
			addPosicao.add("L1EE");
			addPosicao.add("L1EI");
			addPosicao.add("L1DI");
			addPosicao.add("L1DE");
		}
		if (veicED.getDm_Tipo_Chassis()==2 || veicED.getDm_Tipo_Chassis()==3 ||
		    veicED.getDm_Tipo_Chassis()==4 || veicED.getDm_Tipo_Chassis()==5 ||
		    veicED.getDm_Tipo_Chassis()==11 || veicED.getDm_Tipo_Chassis()==17 ||
		    veicED.getDm_Tipo_Chassis()==19 || veicED.getDm_Tipo_Chassis()==20){
  			addPosicao.add("L2EE");
  			addPosicao.add("L2EI");
  			addPosicao.add("L2DI");
  			addPosicao.add("L2DE");
		}
		if (veicED.getDm_Tipo_Chassis()==3 || veicED.getDm_Tipo_Chassis()==4 ||
			veicED.getDm_Tipo_Chassis()==5 || veicED.getDm_Tipo_Chassis()==20){
  	  		addPosicao.add("L3EE");
  	  		addPosicao.add("L3EI");
  	  		addPosicao.add("L3DI");
  	  		addPosicao.add("L3DE");
		}
		if (veicED.getDm_Tipo_Chassis()==4 || veicED.getDm_Tipo_Chassis()==5){
  			addPosicao.add("L4EE");
  			addPosicao.add("L4EI");
  			addPosicao.add("L4DI");
  			addPosicao.add("L4DE");
		}
		if (veicED.getDm_Tipo_Chassis()==5){
			addPosicao.add("L5EE");
			addPosicao.add("L5EI");
			addPosicao.add("L5DI");
			addPosicao.add("L5DE");
			addPosicao.add("L7EE");
			addPosicao.add("L7EI");
			addPosicao.add("L7DI");
			addPosicao.add("L7DE");
			addPosicao.add("L6EE");
			addPosicao.add("L6EI");
			addPosicao.add("L6DI");
			addPosicao.add("L6DE");
			addPosicao.add("L8EE");
			addPosicao.add("L8EI");
			addPosicao.add("L8DI");
			addPosicao.add("L8DE");
		}
		if (veicED.getDm_Tipo_Chassis()==6 || veicED.getDm_Tipo_Chassis()==7){
			addPosicao.add("L2EE");
			addPosicao.add("L2DE");
		}
		if (veicED.getDm_Tipo_Chassis()==8 || veicED.getDm_Tipo_Chassis()==9 ||
			veicED.getDm_Tipo_Chassis()==10 || veicED.getDm_Tipo_Chassis()==12 ||
			veicED.getDm_Tipo_Chassis()==13 || veicED.getDm_Tipo_Chassis()==14 ||
			veicED.getDm_Tipo_Chassis()==15 || veicED.getDm_Tipo_Chassis()==16 ||
			veicED.getDm_Tipo_Chassis()==17){
			addPosicao.add("DE");
			addPosicao.add("TEE");
			addPosicao.add("DD");
			addPosicao.add("TDE");
		}
		if (veicED.getDm_Tipo_Chassis()==9 || veicED.getDm_Tipo_Chassis()==10 ||
			veicED.getDm_Tipo_Chassis()==13 || veicED.getDm_Tipo_Chassis()==14 ||
			veicED.getDm_Tipo_Chassis()==15 || veicED.getDm_Tipo_Chassis()==16 ||
			veicED.getDm_Tipo_Chassis()==17){
			addPosicao.add("TEI");
			addPosicao.add("TDI");
		}
		if (veicED.getDm_Tipo_Chassis()==10 || veicED.getDm_Tipo_Chassis()==16){
			addPosicao.add("TKEI");
			addPosicao.add("TKDI");
		}
		if (veicED.getDm_Tipo_Chassis()==10 || veicED.getDm_Tipo_Chassis()==12 ||
			veicED.getDm_Tipo_Chassis()==13 || veicED.getDm_Tipo_Chassis()==14 ||
			veicED.getDm_Tipo_Chassis()==15 || veicED.getDm_Tipo_Chassis()==16){
			addPosicao.add("TKEE");
			addPosicao.add("TKDE");
		}
		if (veicED.getDm_Tipo_Chassis()==15 || veicED.getDm_Tipo_Chassis()==16 ||
			veicED.getDm_Tipo_Chassis()==17){
			addPosicao.add("D2E");
			addPosicao.add("D2D");
		}
		if (veicED.getDm_Tipo_Chassis()==6 || veicED.getDm_Tipo_Chassis()==7 ||
			veicED.getDm_Tipo_Chassis()==18){
			addPosicao.add("L1EE");
			addPosicao.add("L1DE");
		}
		if (!(veicED.getDm_Tipo_Chassis()==5 || veicED.getDm_Tipo_Chassis()==11 ||
			  veicED.getDm_Tipo_Chassis()==18 || veicED.getDm_Tipo_Chassis()==19 ||
			  veicED.getDm_Tipo_Chassis()==20)){
				if (veicED.getDm_Tipo_Chassis()==8){
					addPosicao.add("STP1");
				}else{
					addPosicao.add("STP1");
					addPosicao.add("STP2");
				}
			}

		return addPosicao;
   }

	public void Relatorio_Vistoria(VeiculoED ed,HttpServletRequest request, HttpServletResponse response, String relatorio) throws Excecoes {
		ArrayList listVeiculoRel = new ArrayList(); // Array para enviar para o relatório
		try {
			this.inicioTransacao();
			// Busca o movimento de lancamentos para o periodo informado com o ed da tela de filtro ctbF011.jsp
			VeiculoED EDFiltro = new VeiculoED();
			String NR_Placa = request.getParameter ("FT_NR_Placa");

			// Monta a parte fixa que veio do bean da tela de filtro

			if (Utilitaria.doValida (NR_Placa)){
				// TESTE PARA TRATAR NULL PLACA DA VEÍCULO
				if (Utilitaria.doValida (NR_Placa)){
					EDFiltro.setNr_Placa(NR_Placa);
				}else{
					EDFiltro.setNr_Placa("XXX-0000");
				}
			}
			listVeiculoRel.add(EDFiltro);

			if (listVeiculoRel.size() > 0 ) {
				ed.setLista(listVeiculoRel); // Joga a lista de movimentos no ed para enviar pro relatório
				ed.setResponse(response);
				ed.setNomeRelatorio(relatorio); // Seta o nome do relatório
				new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
			}
		}
		finally {
			this.fimTransacao(false);
		}
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }

}