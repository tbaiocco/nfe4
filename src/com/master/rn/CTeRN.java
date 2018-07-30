package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.servicos.CteServicos;
import br.nfe.utils.Configuracoes;

import com.master.bd.CTeBD;
import com.master.bd.ConhecimentoBD;
import com.master.ed.ConhecimentoED;
import com.master.ed.EmailED;
import com.master.ed.Origem_DuplicataED;
import com.master.ed.UsuarioED;
import com.master.root.PessoaBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.bd.Transacao;
import com.master.util.mail.Mailer;

import br.cte.bean.BeanDacte;
import br.cte.model.Cte;
import br.cte.model.CteCancelamento;
import br.cte.model.CteEvento;
import br.cte.model.CteInutilizacao;
import br.cte.model.CteLote;
import br.cte.model.CteRetornoEnvioLote;
import br.cte.model.Empresa;

public class CTeRN extends Transacao {
	CTeBD cteBD = null;

	public CTeRN(Empresa empresa) {
		super(empresa);
	}

	protected void finalize() throws Throwable {
		if (this.sql != null)
			this.abortaTransacao();
	}
	
	//UNILIVER - 21/08/2017
	public ArrayList<String> getCNPJUnilever() {
		this.inicioTransacao();
		ArrayList<String> list = new CTeBD(this.sql).getCNPJUnilever();
		this.fimTransacao(false);
		return list;
	}

	public void numeraCTe(ConhecimentoED ed) throws Excecoes {

		try {

			this.inicioTransacao();
			//abre uma transacao sem concorrencia...
			this.doBeginTransaction();

			new CTeBD(this.sql).numeraCTe(ed);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao numerar CTe", e, this.getClass()
					.getName(), "alteraEnvio()");
		}

	}

	public void alteraEnvio(ConhecimentoED ed, CteLote retorno) throws Excecoes {

		try {

			this.inicioTransacao();

			new CTeBD(this.sql).alteraEnvio(ed, retorno);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar envio CTe", e, this.getClass()
					.getName(), "alteraEnvio()");
		}

	}

	public void alteraRetornoCTe(ConhecimentoED ed, CteRetornoEnvioLote ret) throws Excecoes {

		try {

			this.inicioTransacao();

			new CTeBD(this.sql).alteraRetornoCTe(ed, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar retorno CTe", e, this.getClass()
					.getName(), "alteraRetornoCTe()");
		}

	}

	public void alteraCancelamentoCTe(ConhecimentoED ed, CteEvento ret) throws Excecoes {

		try {

			this.inicioTransacao();

			new CTeBD(this.sql).alteraCancelamentoCTe(ed, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar cancelamento CTe", e, this.getClass()
					.getName(), "alteraCancelamentoCTe()");
		}
	}

	public void incluiInutilizacao(String emissor, String xJust, UsuarioED user, CteInutilizacao ret) throws Excecoes {

		try {

			this.inicioTransacao();

			new CTeBD(this.sql).incluiInutilizacaoNumero(emissor, xJust, user, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao incluir inutilizacao CTe", e, this
					.getClass().getName(), "incluiInutilizacaoNumero()");
		}
	}

	public Cte getDadosEnvio(ConhecimentoED ed) throws Excecoes{
		Cte cte = new Cte();
		this.inicioTransacao();
		cte = new CTeBD(this.sql).getDadosEnvio(ed);
		this.fimTransacao(false);
		return cte;
	}

	public ConhecimentoED getDadosRetorno(ConhecimentoED ed) throws Excecoes{

		this.inicioTransacao();
		ed = new CTeBD(this.sql).getConhecimentoED(ed);
		this.fimTransacao(false);
		return ed;
	}

	public ArrayList<ConhecimentoED> listaNaoRetornados(ConhecimentoED ed) throws Excecoes{

		this.inicioTransacao();
		ArrayList<ConhecimentoED> list = new CTeBD(this.sql).listaNaoRetornados(ed);
		this.fimTransacao(false);
		return list;
	}

	public ArrayList<ConhecimentoED> listaParaImpressao(ConhecimentoED ed) throws Excecoes{

		this.inicioTransacao();
		ArrayList<ConhecimentoED> list = new CTeBD(this.sql).listaParaImpressao(ed);
		this.fimTransacao(false);
		return list;
	}

	public ArrayList<ConhecimentoED> listaOrigemImpressao(Origem_DuplicataED ed) throws Excecoes{

		this.inicioTransacao();
		ArrayList<ConhecimentoED> list = new CTeBD(this.sql).listaOrigemImpressao(ed);
		this.fimTransacao(false);
		return list;
	}

	public void imprimeDACTE(String oid, HttpServletResponse response) throws Exception {

    	try {
    		this.inicioTransacao();

    		ConhecimentoED ed = new ConhecimentoED();
    		ed.setOID_Conhecimento(oid);
    		Cte cte = new CTeBD(this.sql).getDadosDacte(ed);
//    		Cte cte = new CTeBD(this.sql).getDadosEnvio(ed);

			this.fimTransacao(false);

            CteServicos servico = new CteServicos();
            BeanDacte dacte = servico.getDacte(cte);

            ArrayList<BeanDacte> list = new ArrayList<BeanDacte>();
            list.add(dacte);

            String emissor = cte.getEmitente().getCNPJ();
            HashMap parametros = new HashMap();
            String img = "/data/cte/relatorios/"+emissor+".jpg";
System.out.println("logo:"+img);
            File f = new File(img);
            if (f.exists()) {
                parametros.put("logo", img);
            }
            String pathJasper = br.utils.Configuracoes.getInstance().getAppDir() + "/relatorios/";
            JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(list);
            String arquivo = "dacte_rod";
            if(cte.getModal()==2){
            	//AEREO
            	arquivo = "dacte_aer";
            }
            String path = pathJasper + arquivo + ".jasper";
            try {
            	System.out.println("relatorio:"+path);
                JasperPrint jp = JasperFillManager.fillReport(path, parametros, jrbcds);
                response.setHeader("application/pdf", "Content-Type");
                response.setHeader("Content-Disposition:","inline; filename=" + "DACTE" + cte.getNCT() + ".pdf");
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
            } catch (JRException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    	} catch(RuntimeException e) {
    		this.abortaTransacao();
            e.printStackTrace();
        } catch(Exception e){
        	this.abortaTransacao();
            e.printStackTrace();
        }

    }

	public Cte imprimeDACTEtoFile(String oid, String filePath) throws Exception {

		Cte cte = new Cte();
    	try {
    		this.inicioTransacao();

    		ConhecimentoED ed = new ConhecimentoED();
    		ed.setOID_Conhecimento(oid);
    		cte = new CTeBD(this.sql).getDadosDacte(ed);
//    		Cte cte = new CTeBD(this.sql).getDadosEnvio(ed);

			this.fimTransacao(false);

            CteServicos servico = new CteServicos();
            BeanDacte dacte = servico.getDacte(cte);

            ArrayList<BeanDacte> list = new ArrayList<BeanDacte>();
            list.add(dacte);

            String emissor = cte.getEmitente().getCNPJ();
            HashMap parametros = new HashMap();
            String img = "/data/cte/relatorios/"+emissor+".jpg";
//System.out.println("logo:"+img);
            File f = new File(img);
            if (f.exists()) {
                parametros.put("logo", img);
            }
            String pathJasper = br.utils.Configuracoes.getInstance().getAppDir() + "/relatorios/";
            JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(list);
            String arquivo = "dacte_rod";
            if(cte.getModal()==2){
            	//AEREO
            	arquivo = "dacte_aer";
            }
            String path = pathJasper + arquivo + ".jasper";
            try {
            	System.out.println("relatorio:"+path);
                JasperPrint jp = JasperFillManager.fillReport(path, parametros, jrbcds);

                JasperExportManager.exportReportToPdfFile(jp, (filePath + "DACTE" + cte.getNCT() + ".pdf"));
            } catch (JRException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    	} catch(RuntimeException e) {
    		this.abortaTransacao();
            e.printStackTrace();
        } catch(Exception e){
        	this.abortaTransacao();
            e.printStackTrace();
        }
        return cte;
    }

	public Cte imprimeDACTE(String oid, String filePath) throws Exception {

		Cte cte = new Cte();
    	try {
    		this.inicioTransacao();

    		ConhecimentoED ed = new ConhecimentoED();
    		ed.setOID_Conhecimento(oid);
    		cte = new CTeBD(this.sql).getDadosDacte(ed);
//    		Cte cte = new CTeBD(this.sql).getDadosEnvio(ed);

			this.fimTransacao(false);

            CteServicos servico = new CteServicos();
            BeanDacte dacte = servico.getDacte(cte);

            ArrayList<BeanDacte> list = new ArrayList<BeanDacte>();
            list.add(dacte);

            String emissor = cte.getEmitente().getCNPJ();
            HashMap parametros = new HashMap();
            String img = "/data/cte/relatorios/"+emissor+".jpg";
//System.out.println("logo:"+img);
            File f = new File(img);
            if (f.exists()) {
                parametros.put("logo", img);
            }
            String pathJasper = br.utils.Configuracoes.getInstance().getAppDir() + "/relatorios/";
            JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(list);
            String arquivo = "dacte_rod";
            if(cte.getModal()==2){
            	//AEREO
            	arquivo = "dacte_aer";
            }
            String path = pathJasper + arquivo + ".jasper";
            try {
            	System.out.println("relatorio:"+path);
                JasperPrint jp = JasperFillManager.fillReport(path, parametros, jrbcds);

                JasperExportManager.exportReportToPdfFile(jp, (filePath));
            } catch (JRException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    	} catch(RuntimeException e) {
    		this.abortaTransacao();
            e.printStackTrace();
        } catch(Exception e){
        	this.abortaTransacao();
            e.printStackTrace();
        }
        return cte;
    }

	public void enviaEmail(String oid, String path) throws Exception {

		try{

			//ver cancelados, nao vai pdf e muda nome do arq xml!!!

			String caminho = path+"/";
			String filePath = path+"/out/";
			File ff = new File(filePath);
			if(!ff.exists()){
				ff.mkdir();
			}
			Cte cte = this.imprimeDACTEtoFile(oid, filePath);

        	//envio e-mail
			String dests="";
        	String cliente = ManipulaString.limpaCampo(cte.getRemetente().getCNPJ());
        	PessoaBean pes = PessoaBean.getByOID(cliente);
        	if(JavaUtil.doValida(pes.getEMail()))
        		dests+=pes.getEMail()+";";
        	cliente = ManipulaString.limpaCampo(cte.getDestinatario().getCNPJ());
        	pes = PessoaBean.getByOID(cliente);
        	if(JavaUtil.doValida(pes.getEMail()))
        		dests+=pes.getEMail()+";";

        	if(cte.getToma().getToma()==4){
	        	cliente = ManipulaString.limpaCampo(cte.getToma().getCNPJ());
	        	pes = PessoaBean.getByOID(cliente);
	        	if(JavaUtil.doValida(pes.getEMail()))
	        		dests+=pes.getEMail()+";";
        	}

        	dests+="cte@mirolog.com.br";

//        	System.out.print(dests+"....");
        	if(JavaUtil.doValida(dests)){

        		System.out.println("EMAIL....");

            	String arq = cte.getChaveAcesso()+"-procCte.xml";
            	//Aqui manda o e-mail...
            	EmailED mail = new EmailED();
            	mail.setNM_Host("smtp.mirolog.com.br"); //host
            	mail.setNM_Email_Origem("cte@mirolog.com.br"); //sender
            	mail.setNM_Username("cte@mirolog.com.br"); //user
            	mail.setNM_Protocolo("smtp");
            	mail.setNM_Senha("tr20ct15"); //pass
            	mail.setNM_Subject("TRANSMIRO, arquivos XML e PDF de emissao do CTe "+cte.getNCT()); //subject
            	mail.setNM_Email_Destino(dests); //to
//            	mail.setNM_Email_Destino("teo@nalthus.com.br"); //to
            	mail.setTX_Mensagem(" </br><strong>A TRANSMIRO acaba de EMITIR o CTe acima para sua empresa.</strong></br> Em anexo os arquivos XML e PDF deste CTe.</br>" +
            			"<font color=red>*** Esta eh uma mensagem automatica, <strong>NAO RESPONDA ESTA MENSAGEM!</strong> ***</font></br></br></br></br>"); //message
            	mail.setNM_Path(caminho); //path of attachment
            	mail.setNM_File(arq); //file to attach
            	mail.setNM_Path2(filePath); //path of attachment
            	mail.setNM_File2("DACTE" + cte.getNCT() + ".pdf"); //file to attach
            	mail.setNR_Porta("25"); //port to server
            	mail.setDM_Autenticacao("S"); //auth: S / N
            	Mailer.sendMail(mail);
        	}
		} catch(RuntimeException e) {
    		this.abortaTransacao();
            e.printStackTrace();
        } catch(Exception e){
        	this.abortaTransacao();
            e.printStackTrace();
        }
	}

}