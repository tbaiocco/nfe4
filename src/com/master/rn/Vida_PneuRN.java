package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Dimensao_PneuBD;
import com.master.bd.PneuBD;
import com.master.bd.RecapagemBD;
import com.master.bd.Vida_PneuBD;
import com.master.ed.ConsertoED;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.ed.PneuED;
import com.master.ed.RecapagemED;
import com.master.ed.UsuarioED;
import com.master.ed.Vida_PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.Transacao;


/**
 * @author Ralph Renato
 * @serial Vidas de pneus
 * @serialData 06/2007
 */
public class Vida_PneuRN extends Transacao {

	BancoUtil bu = new BancoUtil();

    public Vida_PneuRN() {
    }

    public Vida_PneuED inclui(Vida_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Vida_PneuBD(this.sql).inclui(ed);
            PneuED pneu = new PneuED();
			pneu.setOid_Pneu(new Long(ed.getOid_Pneu()).intValue());
			if(JavaUtil.doValida(String.valueOf(pneu.getOid_Pneu()))){
				pneu.setNr_Vida(ed.getNr_Vida());
				pneu.setDt_stamp(ed.getDt_stamp());
				pneu.setUsuario_Stamp(ed.getUsuario_Stamp());
				pneu.setDm_Stamp(ed.getDm_Stamp());
				pneu.setUser(ed.getUser());
				pneu.setTime_millis(ed.getTime_millis());
				new PneuBD(this.sql).alteraVida(pneu);
			}
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

    public void altera(Vida_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Vida_PneuBD(this.sql).altera(ed);
            PneuED pneu = new PneuED();
			pneu.setOid_Pneu(new Long(ed.getOid_Pneu()).intValue());
			if(JavaUtil.doValida(String.valueOf(pneu.getOid_Pneu()))){
				pneu.setNr_Vida(ed.getNr_Vida());
				pneu.setDt_stamp(ed.getDt_stamp());
				pneu.setUsuario_Stamp(ed.getUsuario_Stamp());
				pneu.setDm_Stamp(ed.getDm_Stamp());
				pneu.setUser(ed.getUser());
				pneu.setTime_millis(ed.getTime_millis());
				new PneuBD(this.sql).alteraVida(pneu);
			}
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Vida_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Vida_PneuBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Vida_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Vida_PneuBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaIndiceRecapagem (Vida_PneuED ed) throws Excecoes {
	    inicioTransacao ();
	    try {
	      return new Vida_PneuBD(this.sql).listaIndiceRecapagem (ed);
	    }
	    finally {
	      this.fimTransacao (false);
	    }
	  }

    public void lista(Vida_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Vida_PneuBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Vida_PneuED getByRecord(Vida_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Vida_PneuBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public PneuED getByRecord(PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new PneuBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public void getByRecord(Vida_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Vida_PneuED edQBR = new Vida_PneuBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Vida_Pneu()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Vida_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Vida_PneuBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns016"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			if (bu.doValida(ed.getNr_Fogo()))
				nm_Filtro+=" Pneu=" + ed.getNr_Fogo();
			if (bu.doValida(ed.getNm_Fornecedor()))
				nm_Filtro+=" Fornecedor=" + ed.getNm_Fornecedor();
			if (bu.doValida(ed.getNm_Fabricante_Banda()))
				nm_Filtro+=" Fabricante=" + ed.getNm_Fabricante_Banda();
			if (bu.doValida(ed.getNm_Banda()))
				nm_Filtro+=" Desenho=" + ed.getNm_Banda();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }
    /**
     * Relatório de analise de carcaça e custo do km rodado
     * @param ed
     * @param request
     * @param response
     * @throws Excecoes
     */
    public void relatorioCarcaca(Vida_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String nivel = null;
        try {
            this.inicioTransacao();
            String nm_Filtro = "";

            ArrayList lista = new Vida_PneuBD(sql).listaAnaliseCarcaca(ed,"rel");
			ed.setLista(lista); // Joga a lista de vidas no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns501"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			if ( "1".equals(ed.getDm_Situacao())) {
				// só sucateados
				nm_Filtro+=" Pneus: todos; ";
			} else
			if ( "2".equals(ed.getDm_Situacao()) ) {
				// só sucateados
				nm_Filtro+=" Pneus: sucateados; ";
			} else
			if ( "3".equals(ed.getDm_Situacao()) ) {
				// não sucateados
				nm_Filtro+=" Pneus: nao sucateados; ";
			}
			if ("false".equals(ed.getDm_Total())) {
				nm_Filtro+="Período: de " + ed.getDt_Inicial() + " até " + ed.getDt_Final() + "; "   ;
			} else {
				nm_Filtro+="Período: tudo; ";
			}
			if ("true".equals(ed.getDm_Vida_Ate()) ) {
				nm_Filtro+="Vida até: " + ed.getNr_Vida_Ate() + "; ";
			} else
			if ("true".equals(ed.getDm_Vida_So()) ) {
				nm_Filtro += "Somente a vida: " + ed.getNr_Vida_So() + "; ";
			}
			if (bu.doValida(ed.getDm_Eixo())) {
				nm_Filtro+=" Eixo: " + ed.getDm_Eixo()+ "; ";
			}
			if (ed.getOid_Dimensao_Pneu()>0) {
				nm_Filtro+=" Dimensão:" + ed.getNm_Dimensao_Pneu()+ "; ";
			}
			if (ed.getOid_Fabricante_Pneu()>0) {
				nm_Filtro+=" Marca: " + ed.getNm_Fabricante_Pneu()+ "; ";
			}
			if (ed.getOid_Modelo_Pneu()>0) {
				nm_Filtro+=" Modelo: " + ed.getNm_Modelo_Pneu()+ "; ";
			}
			if ("0".equals(ed.getDm_Vl_Venda()) ) {
				nm_Filtro+="Subtrai venda: sim; ";
			}
			ed.setDescFiltro(nm_Filtro);
    		HashMap map = new HashMap();
    		map.put("ANALITICO", String.valueOf(ed.getDm_Tipo()));
    		nivel="0";
    		if ("true".equals(ed.getDm_Agrupa_Eixo()))
    			nivel="1";
    		if ("true".equals(ed.getDm_Agrupa_Dimensao()))
    			nivel="2";
    		if ("true".equals(ed.getDm_Agrupa_Marca()))
    			nivel="3";
    		if ("true".equals(ed.getDm_Agrupa_Modelo()))
    			nivel="4";
    		map.put("NIVEL", nivel);

    		ed.setHashMap(map);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    /**
     * Relatório de analise de recapagem e custo do km rodado
     * @param ed
     * @param request
     * @param response
     * @throws Excecoes
     */
    public void relatorioRecapagem(Vida_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String nivel = null;
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Vida_PneuBD(sql).listaAnaliseRecapagem(ed);
			ed.setLista(lista); // Joga a lista de vidas no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns502"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			if ( "1".equals(ed.getDm_Situacao())) {
				// só sucateados
				nm_Filtro+=" Pneus: todos; ";
			} else
			if ( "2".equals(ed.getDm_Situacao()) ) {
				// só sucateados
				nm_Filtro+=" Pneus: sucateados; ";
			} else
			if ( "3".equals(ed.getDm_Situacao()) ) {
				// não sucateados
				nm_Filtro+=" Pneus: nao sucateados; ";
			}
			if ("false".equals(ed.getDm_Total())) {
				nm_Filtro+="Período: de " + ed.getDt_Inicial() + " até " + ed.getDt_Final() + "; "   ;
			} else {
				nm_Filtro+="Período: tudo; ";
			}
			if ("true".equals(ed.getDm_Vida_Ate()) ) {
				nm_Filtro+="Vida até: " + ed.getNr_Vida_Ate() + "; ";
			} else
			if ("true".equals(ed.getDm_Vida_So()) ) {
				nm_Filtro += "Somente a vida: " + ed.getNr_Vida_So() + "; ";
			}
			if (bu.doValida(ed.getDm_Eixo())) {
				nm_Filtro+=" Eixo: " + ed.getDm_Eixo()+ "; ";
			}
			if (ed.getOid_Dimensao_Pneu()>0) {
				nm_Filtro+=" Dimensão:" + ed.getNm_Dimensao_Pneu()+ "; ";
			}
			if (ed.getOid_Fabricante_Pneu()>0) {
				nm_Filtro+=" Marca: " + ed.getNm_Fabricante_Pneu()+ "; ";
			}
			if (ed.getOid_Modelo_Pneu()>0) {
				nm_Filtro+=" Modelo: " + ed.getNm_Modelo_Pneu()+ "; ";
			}
			ed.setDescFiltro(nm_Filtro);

    		HashMap map = new HashMap();
    		map.put("ANALITICO", String.valueOf(ed.getDm_Tipo()));
    		map.put("TIPO", String.valueOf(ed.getDm_Vl_Venda())); // Tipo de relatorio - borracha,desenho,recapador
    		nivel="0";
    		if ("true".equals(ed.getDm_Agrupa_Desenho()))
    			nivel="1";
    		if ("true".equals(ed.getDm_Agrupa_Eixo()))
    			nivel="2";
    		if ("true".equals(ed.getDm_Agrupa_Dimensao()))
    			nivel="3";
    		if ("true".equals(ed.getDm_Agrupa_Modelo()))
    			nivel="4";
    		map.put("NIVEL", nivel);
    		ed.setHashMap(map);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relatorioIndiceRecapagem(Vida_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Vida_PneuBD(sql).listaIndiceRecapagem(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns323"); // Seta o nome do relatório
			HashMap map = new HashMap();
			map.put("dm_Fabricante_Pneu", (ed.getDm_Fabricante_Pneu()));
			map.put("dm_Dimensao_Pneu", (ed.getDm_Dimensao_Pneu()));
			map.put("dm_Modelo_Pneu", (ed.getDm_Modelo_Pneu()));
			ed.setHashMap(map);
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public String graficoCarcacasMediasPorVida(Vida_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String string = null;
    	string="Análise de Carcaça Dimensões ";
        try {
            this.inicioTransacao();
    		// Monta o filtro e a descricao do filtro utilizado
            String nm_Filtro = "";
        	if ("1".equals(ed.getDm_Situacao())){
        		nm_Filtro+="Todos Pneus";
        	}
        	if ("2".equals(ed.getDm_Situacao())){
        		nm_Filtro+="Somente Sucateados";
        	}
        	if ("3".equals(ed.getDm_Situacao())){
        		nm_Filtro+="Somente Não Sucateados";
        	}
        	if (ed.getOid_Dimensao_Pneu() > 0){
        		nm_Filtro+=" - Dimensão " + ed.getNm_Dimensao_Pneu();
        	}
        	if (ed.getOid_Dimensao_Pneu() == 0){
        		nm_Filtro+=" - Todas Dimensões ";
        	}
        	// Cabeçalho do xml do gráfico
        	string+="<graph xaxisname='Vidas' yaxisname='R$/1000km' hovercapbg='DEDEBE' " +
        	" hovercapborder='889E6D' rotateNames='0' yAxisMaxValue='100' numdivlines='9' " +
        	" divLineColor='CCCCCC' divLineAlpha='80' decimalPrecision='2' showAlternateHGridColor='1' " +
        	" AlternateHGridAlpha='30' AlternateHGridColor='CCCCCC' decimalSeparator=',' " +
        	"caption='" + nm_Filtro + "' > " +
			"<categories font='Arial' fontSize='11' fontColor='000000'> "+
			"<category name='Novo' hoverText='Novo'/> " +
			"<category name='1ª Vida' hoverText='Vida 1'/> " +
			"<category name='2ª Vida' hoverText='Vida 2'/> " +
			"<category name='3ª Vida' hoverText='Vida 3'/> " +
			"<category name='4ª Vida' hoverText='Vida 4'/> " +
			"<category name='5ª Vida' hoverText='Vida 5'/> " +
			"<category name='6ª Vida' hoverText='Vida 6'/> " +
			" </categories> " ;
			ArrayList lst0 = new Vida_PneuBD(sql).listaAnaliseCarcaca(ed,"gra");
			ArrayList lst1 = new ArrayList();
			double totkm = 0;
			double totkm0 = 0;
			double totkm1 = 0;
			double totkm2 = 0;
			double totkm3 = 0;
			double totkm4 = 0;
			double totkm5 = 0;
			double totkm6 = 0;
			double totvl = 0;
			double totvl0 = 0;
			double totvl1 = 0;
			double totvl2 = 0;
			double totvl3 = 0;
			double totvl4 = 0;
			double totvl5 = 0;
			double totvl6 = 0;
			String varMarca = "";
			long varVida=10;
			for (int i=0; i<lst0.size(); i++){
				ed = (Vida_PneuED)lst0.get(i);
				if (! varMarca.equals(ed.getNm_Fabricante_Pneu())   ) {
					if (varMarca.length() > 0 ) {
						// descarregar no ed da lista final
						Vida_PneuED edD = new Vida_PneuED();
						edD.setNm_Fabricante_Pneu(varMarca);
						edD.setNr_Vida(varVida);
						edD.setVl_Vida_N0(totvl0);
						edD.setNr_Km_Vida_N0(totkm0);
						edD.setVl_Vida_R1(totvl1);
						edD.setNr_Km_Vida_R1(totkm1);
						edD.setVl_Vida_R2(totvl2);
						edD.setNr_Km_Vida_R2(totkm2);
						edD.setVl_Vida_R3(totvl3);
						edD.setNr_Km_Vida_R3(totkm3);
						edD.setVl_Vida_R4(totvl4);
						edD.setNr_Km_Vida_R4(totkm4);
						edD.setVl_Vida_R5(totvl5);
						edD.setNr_Km_Vida_R5(totkm5);
						edD.setVl_Vida_R6(totvl6);
						edD.setNr_Km_Vida_R6(totkm6);
						lst1.add(edD);
					}
					// preparo nova acumulação
					totvl0=ed.getVl_Vida_N0();
					totkm0=ed.getNr_Km_Vida_N0();
					totvl1=ed.getVl_Vida_R1();
					totkm1=ed.getNr_Km_Vida_R1();
					totvl2=ed.getVl_Vida_R2();
					totkm2=ed.getNr_Km_Vida_R2();
					totvl3=ed.getVl_Vida_R3();
					totkm3=ed.getNr_Km_Vida_R3();
					totvl4=ed.getVl_Vida_R4();
					totkm4=ed.getNr_Km_Vida_R4();
					totvl5=ed.getVl_Vida_R5();
					totkm5=ed.getNr_Km_Vida_R5();
					totvl6=ed.getVl_Vida_R6();
					totkm6=ed.getNr_Km_Vida_R6();
					varMarca=ed.getNm_Fabricante_Pneu();
					varVida=ed.getNr_Vida();
				} else {
					totvl0+=ed.getVl_Vida_N0();
					totkm0+=ed.getNr_Km_Vida_N0();
					totvl1+=ed.getVl_Vida_R1();
					totkm1+=ed.getNr_Km_Vida_R1();
					totvl2+=ed.getVl_Vida_R2();
					totkm2+=ed.getNr_Km_Vida_R2();
					totvl3+=ed.getVl_Vida_R3();
					totkm3+=ed.getNr_Km_Vida_R3();
					totvl4+=ed.getVl_Vida_R4();
					totkm4+=ed.getNr_Km_Vida_R4();
					totvl5+=ed.getVl_Vida_R5();
					totkm5+=ed.getNr_Km_Vida_R5();
					totvl6+=ed.getVl_Vida_R6();
					totkm6+=ed.getNr_Km_Vida_R6();
				}
			}
			Vida_PneuED edD = new Vida_PneuED();
			edD.setNm_Fabricante_Pneu(varMarca);
			edD.setNr_Vida(varVida);
			edD.setVl_Vida_N0(totvl0);
			edD.setNr_Km_Vida_N0(totkm0);
			edD.setVl_Vida_R1(totvl1);
			edD.setNr_Km_Vida_R1(totkm1);
			edD.setVl_Vida_R2(totvl2);
			edD.setNr_Km_Vida_R2(totkm2);
			edD.setVl_Vida_R3(totvl3);
			edD.setNr_Km_Vida_R3(totkm3);
			edD.setVl_Vida_R4(totvl4);
			edD.setNr_Km_Vida_R4(totkm4);
			edD.setVl_Vida_R5(totvl5);
			edD.setNr_Km_Vida_R5(totkm5);
			edD.setVl_Vida_R6(totvl6);
			edD.setNr_Km_Vida_R6(totkm6);
			lst1.add(edD);
		    for (int i=0; i < lst1.size(); i++){
				Vida_PneuED edVolta = new Vida_PneuED();
				edVolta = (Vida_PneuED)lst1.get(i);
		    	string+="<dataset  seriesname='" + edVolta.getNm_Fabricante_Pneu().trim() + "' " + " color='"+new Utilitaria().getCorGrafico(i)+"'> " ;
		    			if ((edVolta.getVl_Vida_N0() > 0) || (edVolta.getNr_Km_Vida_N0() > 0)){
		    				string+="<set value='" + (edVolta.getVl_Vida_N0() / edVolta.getNr_Km_Vida_N0() * 1000) + "' />" ;
		    			}else{
		    				string+="<set value='0' />" ;
		    			}
		    			if ((edVolta.getVl_Vida_R1() > 0) || (edVolta.getNr_Km_Vida_R1() > 0)){
		    				string+="<set value='" + (edVolta.getVl_Vida_R1() / edVolta.getNr_Km_Vida_R1() * 1000) + "' />" ;
		    			}else{
		    				string+="<set value='0' />" ;
		    			}
		    			if ((edVolta.getVl_Vida_R2() > 0) || (edVolta.getNr_Km_Vida_R2() > 0)){
		    				string+="<set value='" + (edVolta.getVl_Vida_R2() / edVolta.getNr_Km_Vida_R2() * 1000)+ "' />";
		    			}else{
		    				string+="<set value='0' />" ;
		    			}
		    			if ((edVolta.getVl_Vida_R3() > 0) || (edVolta.getNr_Km_Vida_R3() > 0)){
		    				string+="<set value='" + (edVolta.getVl_Vida_R3() / edVolta.getNr_Km_Vida_R3() * 1000) + "' />" ;
		    			}else{
		    				string+="<set value='0' />" ;
		    			}
		    			if ((edVolta.getVl_Vida_R4() > 0) || (edVolta.getNr_Km_Vida_R4() > 0)){
		    				string+="<set value='" + (edVolta.getVl_Vida_R4() / edVolta.getNr_Km_Vida_R4() * 1000)+ "' />";
		    			}else{
		    				string+="<set value='0' />" ;
		    			}
		    			if ((edVolta.getVl_Vida_R5() > 0) || (edVolta.getNr_Km_Vida_R5() > 0)){
		    				string+="<set value='" + (edVolta.getVl_Vida_R5() / edVolta.getNr_Km_Vida_R5() * 1000)+ "' />";
		    			}else{
		    				string+="<set value='0' />" ;
		    			}
		    			if ((edVolta.getVl_Vida_R6() > 0) || (edVolta.getNr_Km_Vida_R6() > 0)){
		    				string+="<set value='" + (edVolta.getVl_Vida_R6() / edVolta.getNr_Km_Vida_R6() * 1000)+ "' />";
		    			}else{
		    				string+="<set value='0' />" ;
		    			}
		    			string+=" </dataset> ";
		    }
		    string+="</graph>";
        } finally {
            this.fimTransacao(false);
        }

    	return string;
    }

    public String graficoCustoAtualizadoKmRodado(Vida_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String string = null;
   		string="Custo Atualizado do KM Rodado ";
    	try {
            this.inicioTransacao();
    		// Monta o filtro e a descricao do filtro utilizado
            String nm_Filtro = "";
            if ("1".equals(ed.getDm_Situacao())){
        		nm_Filtro+="Todos Pneus";
        	}
        	if ("2".equals(ed.getDm_Situacao())){
        		nm_Filtro+="Somente Sucateados";
        	}
        	if ("3".equals(ed.getDm_Situacao())){
        		nm_Filtro+="Somente Não Sucateados";
        	}
        	if (ed.getOid_Dimensao_Pneu() > 0){
        		nm_Filtro+=" - Dimensão " + ed.getNm_Dimensao_Pneu();
        	}
        	if (ed.getOid_Dimensao_Pneu() == 0){
        		nm_Filtro+=" - Todas Dimensões ";
        	}
        	// Cabeçalho do xml do gráfico
			ArrayList lst0 = new Vida_PneuBD(sql).listaAnaliseCarcaca(ed,"gra");
			ArrayList lst1 = new ArrayList();
			double totkm = 0;
			double totkm0 = 0;
			double totkm1 = 0;
			double totkm2 = 0;
			double totkm3 = 0;
			double totkm4 = 0;
			double totkm5 = 0;
			double totkm6 = 0;
			double totvl = 0;
			double totvl0 = 0;
			double totvl1 = 0;
			double totvl2 = 0;
			double totvl3 = 0;
			double totvl4 = 0;
			double totvl5 = 0;
			double totvl6 = 0;
			String varMarca = "";
			long varVida=10;
			for (int i=0; i<lst0.size(); i++){
				ed = (Vida_PneuED)lst0.get(i);
				if (! varMarca.equals(ed.getNm_Fabricante_Pneu())   ) {
					if (varMarca.length() > 0 ) {
						// descarregar no ed da lista final
						Vida_PneuED edD = new Vida_PneuED();
						edD.setNm_Fabricante_Pneu(varMarca);
						edD.setNr_Vida(varVida);
						edD.setVl_Vida_N0(totvl0);
						edD.setNr_Km_Vida_N0(totkm0);
						edD.setVl_Vida_R1(totvl1);
						edD.setNr_Km_Vida_R1(totkm1);
						edD.setVl_Vida_R2(totvl2);
						edD.setNr_Km_Vida_R2(totkm2);
						edD.setVl_Vida_R3(totvl3);
						edD.setNr_Km_Vida_R3(totkm3);
						edD.setVl_Vida_R4(totvl4);
						edD.setNr_Km_Vida_R4(totkm4);
						edD.setVl_Vida_R5(totvl5);
						edD.setNr_Km_Vida_R5(totkm5);
						edD.setVl_Vida_R6(totvl6);
						edD.setNr_Km_Vida_R6(totkm6);
						lst1.add(edD);
					}
					// preparo nova acumulação
					totvl0=ed.getVl_Vida_N0();
					totkm0=ed.getNr_Km_Vida_N0();
					totvl1=ed.getVl_Vida_R1();
					totkm1=ed.getNr_Km_Vida_R1();
					totvl2=ed.getVl_Vida_R2();
					totkm2=ed.getNr_Km_Vida_R2();
					totvl3=ed.getVl_Vida_R3();
					totkm3=ed.getNr_Km_Vida_R3();
					totvl4=ed.getVl_Vida_R4();
					totkm4=ed.getNr_Km_Vida_R4();
					totvl5=ed.getVl_Vida_R5();
					totkm5=ed.getNr_Km_Vida_R5();
					totvl6=ed.getVl_Vida_R6();
					totkm6=ed.getNr_Km_Vida_R6();
					varMarca=ed.getNm_Fabricante_Pneu();
					varVida=ed.getNr_Vida();
				} else {
					totvl0+=ed.getVl_Vida_N0();
					totkm0+=ed.getNr_Km_Vida_N0();
					totvl1+=ed.getVl_Vida_R1();
					totkm1+=ed.getNr_Km_Vida_R1();
					totvl2+=ed.getVl_Vida_R2();
					totkm2+=ed.getNr_Km_Vida_R2();
					totvl3+=ed.getVl_Vida_R3();
					totkm3+=ed.getNr_Km_Vida_R3();
					totvl4+=ed.getVl_Vida_R4();
					totkm4+=ed.getNr_Km_Vida_R4();
					totvl5+=ed.getVl_Vida_R5();
					totkm5+=ed.getNr_Km_Vida_R5();
					totvl6+=ed.getVl_Vida_R6();
					totkm6+=ed.getNr_Km_Vida_R6();
				}
			}
			Vida_PneuED edD = new Vida_PneuED();
			edD.setNm_Fabricante_Pneu(varMarca);
			edD.setNr_Vida(varVida);
			edD.setVl_Vida_N0(totvl0);
			edD.setNr_Km_Vida_N0(totkm0);
			edD.setVl_Vida_R1(totvl1);
			edD.setNr_Km_Vida_R1(totkm1);
			edD.setVl_Vida_R2(totvl2);
			edD.setNr_Km_Vida_R2(totkm2);
			edD.setVl_Vida_R3(totvl3);
			edD.setNr_Km_Vida_R3(totkm3);
			edD.setVl_Vida_R4(totvl4);
			edD.setNr_Km_Vida_R4(totkm4);
			edD.setVl_Vida_R5(totvl5);
			edD.setNr_Km_Vida_R5(totkm5);
			edD.setVl_Vida_R6(totvl6);
			edD.setNr_Km_Vida_R6(totkm6);
			if (lst0.size() > 0){
				lst1.add(edD);
			}
			string+="<graph  " +
			"bgColor='EAEAEA' "+
			"caption='" + nm_Filtro + "' " +
			"xAxisName='Marca' " +
			"yaxisname='R$/1000km' " +
			"decimalPrecision='2' " +
			"formatNumberScale='0' " +
			"decimalSeparator=',' " +
			"showNames='1' " +
			"rotateNames='1' >" ;
			for (int i=0; i < lst1.size(); i++){
				Vida_PneuED edVolta = new Vida_PneuED();
				edVolta = (Vida_PneuED)lst1.get(i);
				double nr_km_Total = 0;
				double nr_Vl_Total = 0;
				nr_km_Total = ed.getNr_Km_Vida_N0() + ed.getNr_Km_Vida_R1() + ed.getNr_Km_Vida_R2() + ed.getNr_Km_Vida_R3() + ed.getNr_Km_Vida_R4() + ed.getNr_Km_Vida_R5() + ed.getNr_Km_Vida_R6();
				nr_Vl_Total = ed.getVl_Vida_N0() + ed.getVl_Vida_R1() + ed.getVl_Vida_R2() + ed.getVl_Vida_R3() + ed.getVl_Vida_R4() + ed.getVl_Vida_R5() + ed.getVl_Vida_R6();
				string+="<set " +
    			"name='" +  edVolta.getNm_Fabricante_Pneu().trim() +  "' " ;
				if (nr_Vl_Total > 0 && nr_km_Total>0){
					string+="value='" + (nr_Vl_Total / nr_km_Total * 1000) + "' " ;
				}else{
					string+="value='0' " ;
				}
				string+="color='"+new Utilitaria().getCorGrafico(i)+"'/>";
			}
		    string+="</graph>";
        } finally {
            this.fimTransacao(false);
        }

    	return string;
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
    	Vida_PneuED ed = (Vida_PneuED)Obj;
    	if ("1".equals(rel)) {
    		this.relatorio(ed, request, response);
    	}
    	if ("2".equals(rel)) {
    		this.relatorioCarcaca(ed, request, response);
    	}
    	if ("3".equals(rel)) {
    		this.relatorioRecapagem(ed, request, response);
    	}
    	if ("4".equals(rel)) {
    		this.relatorioIndiceRecapagem(ed, request, response);
    	}
    }

	public String processaGR(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		Vida_PneuED ed = (Vida_PneuED)Obj;
		UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		if ("GR1".equals(acao)) {
			return this.graficoCarcacasMediasPorVida(ed, request, response);
		}
		if ("GR2".equals(acao)) {
			return this.graficoCustoAtualizadoKmRodado(ed, request, response);
		}
		return " ";
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
    	Vida_PneuED ed = (Vida_PneuED)Obj;
    	//Prepara a saída

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuploVida(ed,acao)){
    			out.println("<ret><item oknok='Pneu já existente com esta Vida!'/></ret>");
       		}else {
       			ed = this.inclui(ed);
   				out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Vida_Pneu() + "' /></ret>");
       		}
       	}else
		if ("A".equals(acao)) {
    		if (checkDuploVida(ed,acao)){
    			out.println("<ret><item oknok='Pneu já existente com esta Vida!'/></ret>");
       		}else{
	    		this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
       		}
		} else
		if ("D".equals(acao)) {
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		String saida;
		ArrayList lst = new ArrayList();
		if ("L".equals(acao)) {
			lst = this.lista(ed);
		}
		if ("LIR".equals(acao)) { // Lista de Indice de Recapagens
			lst = this.listaIndiceRecapagem(ed);
		}
		for (int i=0; i<lst.size(); i++){
			Vida_PneuED edVolta = new Vida_PneuED();
			edVolta = (Vida_PneuED)lst.get(i);
			if ("L".equals(acao) || "LIR".equals(acao)) {
				saida = "<item ";
				saida += "oid_Empresa='"+ edVolta.getOid_Empresa() + "' ";
				saida += "oid_Vida_Pneu='"+ edVolta.getOid_Vida_Pneu() + "' ";
				saida += "oid_Pneu='"+ edVolta.getOid_Pneu() + "' ";
				saida += "oid_Fornecedor='"+ edVolta.getOid_Fornecedor() + "' ";
				saida += "oid_Fabricante_Banda='"+ edVolta.getOid_Fabricante_Banda() + "' ";
				saida += "oid_Banda='"+ edVolta.getOid_Banda() + "' ";
				saida += "oid_Veiculo='"+ edVolta.getOid_Veiculo() + "' ";
				saida += "nr_Frota='"+ edVolta.getNr_Frota() + "' ";
				saida += "nr_Fogo='"+ edVolta.getNr_Fogo() + "' ";
				saida += "nr_Km_Inicial='"+ FormataValor.formataValorBT(edVolta.getNr_Km_Inicial(),1) + "' ";
				saida += "nr_Km_Final='"+ FormataValor.formataValorBT(edVolta.getNr_Km_Final(),1) + "' ";
				saida += "nr_Km_Vida='"+ FormataValor.formataValorBT((edVolta.getNr_Km_Final() - edVolta.getNr_Km_Inicial()),1) + "' ";
				saida += "nr_Os='"+ edVolta.getNr_Os() + "' ";
				saida += "nr_Nota_Fiscal_Recape='"+ edVolta.getNr_Nota_Fiscal_Recape() + "' ";
				saida += "nr_Mm_Inicial='"+ FormataValor.formataValorBT(edVolta.getNr_Mm_Inicial(),1) + "' ";
				saida += "nr_Mm_Final='"+ FormataValor.formataValorBT(edVolta.getNr_Mm_Final(),1) + "' ";
				saida += "vl_Vida='"+ FormataValor.formataValorBT(edVolta.getVl_Vida(),2) + "' ";
				saida += "vl_Km_Vida='"+ FormataValor.formataValorBT(((edVolta.getVl_Vida() / (edVolta.getNr_Km_Final() - edVolta.getNr_Km_Inicial())) * 1000),3) + "' ";
				saida += "dt_Inicial='"+ edVolta.getDt_Inicial() + "' ";
				saida += "dt_Final='"+ edVolta.getDt_Final() + "' ";
				saida += "nr_Vida='"+ edVolta.getNr_Vida() + "' ";
				saida += "dm_Eixo='"+ edVolta.getDm_Eixo() + "' ";
				saida += "nm_Razao_Social='"+ edVolta.getNm_Fornecedor() + "' ";
				saida += "nm_Banda='"+ edVolta.getNm_Banda() + "' ";
				saida += "nm_Fabricante_Banda='"+ edVolta.getNm_Fabricante_Banda() + "' ";
				saida += "nm_Vida_Ext='"+ edVolta.getNm_Vida_Ext() + "' ";
				saida += "vl_Vida_Varre='"+ edVolta.getVl_Vida() + "' ";
				saida += "nm_Fabricante_Pneu='"+ edVolta.getNm_Fabricante_Pneu() + "' ";
				saida += "nm_Modelo_Pneu='"+ edVolta.getNm_Modelo_Pneu()+ "' ";
				saida += "nm_Dimensao_Pneu='"+ edVolta.getNm_Dimensao_Pneu()+ "' ";
				if (edVolta.getNr_Qtd_Total_Vidas() > 0) {
					saida += "nr_Qtd_Total_Vidas='"+ edVolta.getNr_Qtd_Total_Vidas() + "' ";
				}else{
					saida += "nr_Qtd_Total_Vidas='" + "" + "' ";
				}
				if (edVolta.getNr_Qtd_N0() > 0) {
					saida += "nr_Qtd_N0='"+ edVolta.getNr_Qtd_N0() + "' ";
				}else{
					saida += "nr_Qtd_N0='" + "" + "' ";
				}
				if (edVolta.getNr_Qtd_R1() > 0) {
					saida += "nr_Qtd_R1='"+ edVolta.getNr_Qtd_R1() + "' ";
				}else{
					saida += "nr_Qtd_R1='" + "" + "' ";
				}
				if (edVolta.getNr_Qtd_R2() > 0) {
					saida += "nr_Qtd_R2='"+ edVolta.getNr_Qtd_R2() + "' ";
				}else{
					saida += "nr_Qtd_R2='" + "" + "' ";
				}
				if (edVolta.getNr_Qtd_R3() > 0) {
					saida += "nr_Qtd_R3='"+ edVolta.getNr_Qtd_R3() + "' ";
				}else{
					saida += "nr_Qtd_R3='" + "" + "' ";
				}
				if (edVolta.getNr_Qtd_R4() > 0) {
					saida += "nr_Qtd_R4='"+ edVolta.getNr_Qtd_R4() + "' ";
				}else{
					saida += "nr_Qtd_R4='" + "" + "' ";
				}
				if (edVolta.getNr_Qtd_R5() > 0) {
					saida += "nr_Qtd_R5='"+ edVolta.getNr_Qtd_R5() + "' ";
				}else{
					saida += "nr_Qtd_R5='" + "" + "' ";
				}
				if (edVolta.getNr_Qtd_R6() > 0) {
					saida += "nr_Qtd_R6='"+ edVolta.getNr_Qtd_R6() + "' ";
				}else{
					saida += "nr_Qtd_R6='" + "" + "' ";
				}
				if (edVolta.getVl_Vida_Total() > 0) {
					saida += "vl_Vida_Total='"+ FormataValor.formataValorBT(edVolta.getVl_Vida_Total(),2) + "' ";
				}else{
					saida += "vl_Vida_Total='" + "" + "' ";
				}
				if (edVolta.getVl_Vida_R1() > 0) {
					saida += "vl_Vida_R1='"+ FormataValor.formataValorBT(edVolta.getVl_Vida_R1(),2) + "' ";
				}else{
					saida += "vl_Vida_R1='" + "" + "' ";
				}
				if (edVolta.getVl_Vida_R2() > 0) {
					saida += "vl_Vida_R2='"+ FormataValor.formataValorBT(edVolta.getVl_Vida_R2(),2) + "' ";
				}else{
					saida += "vl_Vida_R2='" + "" + "' ";
				}
				if (edVolta.getVl_Vida_R3() > 0) {
					saida += "vl_Vida_R3='"+ FormataValor.formataValorBT(edVolta.getVl_Vida_R3(),2) + "' ";
				}else{
					saida += "vl_Vida_R3='" + "" + "' ";
				}
				if (edVolta.getVl_Vida_R4() > 0) {
					saida += "vl_Vida_R4='"+ FormataValor.formataValorBT(edVolta.getVl_Vida_R4(),2) + "' ";
				}else{
					saida += "vl_Vida_R4='" + "" + "' ";
				}
				if (edVolta.getVl_Vida_R5() > 0) {
					saida += "vl_Vida_R5='"+ FormataValor.formataValorBT(edVolta.getVl_Vida_R5(),2) + "' ";
				}else{
					saida += "vl_Vida_R5='" + "" + "' ";
				}
				if (edVolta.getVl_Vida_R6() > 0) {
					saida += "vl_Vida_R6='"+ FormataValor.formataValorBT(edVolta.getVl_Vida_R6(),2) + "' ";
				}else{
					saida += "vl_Vida_R6='" + "" + "' ";
				}
				saida += "/>";
				out.println(saida);
			}
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(Vida_PneuED ed,
			HttpServletRequest request) throws ServletException, IOException,
			Excecoes {
		Object toReturn = new Object();
		// PADRAO PARA TODAS...
		String acao = JavaUtil.getValueDef(request.getParameter("acao"), "M");
		UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,
				"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		ed.setDm_Stamp(acao);
		ed.setTime_millis(System.currentTimeMillis());
		System.out.println(acao);
		// FIM PADRAO!
		if ("I".equals(acao)) {
			this.inclui(ed);
		} else if ("A".equals(acao)) {
			this.altera(ed);
		} else if ("D".equals(acao)) {
			this.deleta(ed);
		} else {
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			if ("L".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("vida_pneu", (Vida_PneuED) lst
						.get(0));
			}
		}
	}

    public void processaRelatorios(String rel, Vida_PneuED ed, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Excecoes {
    	UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		ed.setRequest(request);

		if ("1".equals(rel)) {
    		this.relatorio(ed, request, response);
    	}
    	if ("2".equals(rel)) {
    		this.relatorioCarcaca(ed, request, response);
    	}
    	if (rel.startsWith("3")) {
    		if(rel.endsWith("b"))
    			ed.setDm_Vl_Venda("borracha");
    		if(rel.endsWith("d"))
    			ed.setDm_Vl_Venda("desenho");
    		if(rel.endsWith("r"))
    			ed.setDm_Vl_Venda("recapador");
    		this.relatorioRecapagem(ed, request, response);
    	}
    	if ("4".equals(rel)) {
    		this.relatorioIndiceRecapagem(ed, request, response);
    	}
    }

    public boolean checkDuploVida ( Vida_PneuED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Vida_PneuED edChk = new Vida_PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setOid_Pneu(ed.getOid_Pneu());
		edChk.setNr_Vida(ed.getNr_Vida());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Vida_Pneu()>0)
    		ret = true;
    	else
    	if ("A".equals(acao) && edChk.getOid_Vida_Pneu()>0 ) {
			if (ed.getOid_Vida_Pneu()!=ed.getOid_Vida_Pneu())
				ret = true ;

    	}
    	return ret;
    }

    public boolean checkEmUso ( Vida_PneuED ed ) throws Excecoes {
		try {
			Vida_PneuED vida_pneuED = new Vida_PneuED();
			vida_pneuED.setOid_Empresa(ed.getOid_Empresa());
			vida_pneuED.setOid_Vida_Pneu(ed.getOid_Vida_Pneu());
			this.inicioTransacao();
			return (new Vida_PneuBD(this.sql).lista(vida_pneuED).size()>0 ? true : false);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}