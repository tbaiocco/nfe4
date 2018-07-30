package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.HodometroBD;
import com.master.bd.VeiculoBD;
import com.master.bd.Vida_PneuBD;
import com.master.ed.HodometroED;
import com.master.ed.VeiculoED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.Utilitaria;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Hodometroes
 * @serialData 06/2007
 */
public class HodometroRN extends Transacao {

    public HodometroRN() {
    }

    public HodometroED inclui(HodometroED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            // Acessa veiculo em questao. 
            VeiculoED veicED = new VeiculoED();
            veicED.setOid_Veiculo(ed.getOid_Veiculo());
            veicED = new VeiculoBD(this.sql).getByRecord(veicED);
            //Calcula a km acumulada...
            double km_Acumulada = veicED.getNr_Kilometragem_Atual() + (ed.getNr_Odometro_Retirado() - veicED.getNr_Odometro());
            //Pega os dados atuais do veiculo e coloca no registro do Hodometro.
            ed.setNr_Odometro_Anterior(veicED.getNr_Odometro());
            ed.setNr_Odometro_Maximo_Anterior(veicED.getDm_Odometro_Maximo());
            ed.setNr_Km_Acum_Anterior(veicED.getNr_Kilometragem_Atual());
            ed.setNr_Km_Acum_Troca(km_Acumulada);
            //Atualiza os dados do veiculo
            veicED.setNr_Kilometragem_Atual(km_Acumulada);
            veicED.setNr_Odometro(ed.getNr_Odometro_Colocado());
            veicED.setDm_Odometro_Maximo(ed.getNr_Odometro_Maximo());
            //Inclui o hodometro
            ed = new HodometroBD(this.sql).inclui(ed);
            //Atualiza o veiculo
            new VeiculoBD(this.sql).altera(veicED);
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

    public void deleta(HodometroED ed) throws Excecoes {

        try {
        	// Pega os dados do hodometro a ser destrocado - PEGAR ÚLTIMO 
        	HodometroED hodoED = this.getByRecord(ed);
        	// Inicia a transação para alteção dos dados
            this.inicioTransacao();
            // Acessa veiculo
            VeiculoED veicED = new VeiculoED();
            veicED.setOid_Veiculo(ed.getOid_Veiculo());
            veicED = new VeiculoBD(this.sql).getByRecord(veicED);
            veicED.setNr_Odometro(hodoED.getNr_Odometro_Anterior());
            veicED.setNr_Kilometragem_Atual(hodoED.getNr_Km_Acum_Anterior());
            veicED.setDm_Odometro_Maximo(hodoED.getNr_Odometro_Maximo_Anterior());
            //Apaga o hodometro
            new HodometroBD(this.sql).deleta(ed);
            //Atualiza o veiculo
            new VeiculoBD(this.sql).atualizaKm(veicED);            
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(HodometroED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new HodometroBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public HodometroED getByRecord(HodometroED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new HodometroBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    /**
     * Calcula a km acumulada.
     * Verifica no cadatro de hodometros se existe uma troca de hodometro para o veiculo em questão e na data em que está sendo feita a consulta
     * @param ed
     */
    public HodometroED getKmAcumulada (HodometroED ed) throws Excecoes {
    	HodometroED odoED = new HodometroED();
        try {
            this.inicioTransacao();
            odoED = new HodometroBD(this.sql).getKmAcumulada(ed);
        } finally {
            this.fimTransacao(false);
        }
    	return odoED;
    }

    public void relatorio(HodometroED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new HodometroBD(this.sql).lista(ed);
			ed.setLista(lista); // Joga a lista no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns301"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			nm_Filtro+="Frota=" + ed.getNr_Frota();
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
    	HodometroED ed = (HodometroED)Obj;
    	ed.setRequest(request);
    	//if ("1".equals(rel)) {
    		this.relatorio(ed, request, response);	
    	//} 
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
    	HodometroED ed = (HodometroED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (this.lista(ed).size()>0) {
    			out.println("<ret><item oknok='Impossível incluir hodômetro! Há hodômetro com data superior !' /></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Odometro() + "' /></ret>");
    		}
    	} else 
		if ("D".equals(acao)) {
			this.deleta(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		if ("L".equals(acao)) {
			String saida=null;
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			for (int i=0; i<lst.size(); i++){
				HodometroED edVolta = new HodometroED();
				edVolta = (HodometroED)lst.get(i);
				saida = "<item ";
				saida += "oid_Odometro='" + edVolta.getOid_Odometro() + "' ";
				saida += "oid_Veiculo='" + edVolta.getOid_Veiculo() + "' ";
				saida += "nr_Odometro_Retirado='" + FormataValor.formataValorBT(edVolta.getNr_Odometro_Retirado(),1) + "' ";
				saida += "nr_Odometro_Colocado='" + FormataValor.formataValorBT(edVolta.getNr_Odometro_Colocado(),1) + "' ";
				saida += "nr_Odometro_Maximo='" + FormataValor.formataValorBT(edVolta.getNr_Odometro_Maximo(),0) + "' ";
				saida += "dt_Odometro_Troca='" + edVolta.getDt_Odometro_Troca() + "' ";
				saida += "nr_Km_Acum_Troca='" + edVolta.getNr_Km_Acum_Troca() + "' ";
				saida += "nr_Odometro_Anterior='" + FormataValor.formataValorBT(edVolta.getNr_Odometro_Anterior(),1) + "' ";
				saida += "nr_Km_Acum_Anterior='" + FormataValor.formataValorBT(edVolta.getNr_Km_Acum_Anterior(),1) + "' ";
				saida += "nr_Odometro_Maximo_Anterior='" + edVolta.getNr_Odometro_Maximo_Anterior() + "' ";
				saida += "dm_Tipo_Troca='" + edVolta.getDm_Tipo_Troca() + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
				out.println(saida);
			}
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }
    
    private boolean validaTroca (HodometroED ed ) throws Excecoes {
    	if (this.lista(ed).size()>0)
    		return false;
    	else
    		return true;	
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}