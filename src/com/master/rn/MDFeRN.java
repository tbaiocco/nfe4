package com.master.rn;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.MDFeBD;
import com.master.ed.ManifestoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.nuovonet.rest.model.BaseRequisicao;
import com.nuovonet.rest.model.BaseRetorno;

import br.mdfe.model.Empresa;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeConsulta;
import br.mdfe.model.MdfeEvento;
import br.mdfe.model.MdfeLote;
import br.mdfe.model.MdfeRetornoEnvioLote;
import br.nfe.utils.Configuracoes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class MDFeRN extends Transacao {
	MDFeBD mdfeBD = null;

	public MDFeRN() {
		super();
	}
	
	public MDFeRN(Empresa empresa) {
		super(empresa);
	}

	protected void finalize() throws Throwable {
		if (this.sql != null)
			this.abortaTransacao();
	}
	
	//UNILEVER - 21/08/2017
	public boolean isUnilever(ManifestoED ed) {
		boolean toReturn = false;
		this.inicioTransacao();
		toReturn = new MDFeBD(this.sql).isUnilever(ed);
		this.fimTransacao(false);
		return toReturn;
	}

	public void alteraEnvio(ManifestoED ed, MdfeLote retorno) throws Excecoes {

		try {

			this.inicioTransacao();

			new MDFeBD(this.sql).alteraEnvio(ed, retorno);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar envio MDFe", e, this.getClass()
					.getName(), "alteraEnvio()");
		}

	}

	public void alteraRetorno(ManifestoED ed, MdfeRetornoEnvioLote ret)
	throws Excecoes {

		try {

			this.inicioTransacao();

			new MDFeBD(this.sql).alteraRetorno(ed, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar retorno MDFe", e, this.getClass()
					.getName(), "alteraRetorno()");
		}

	}

	public void alteraCancelamento(ManifestoED ed, MdfeEvento ret)
	throws Excecoes {

		try {

			this.inicioTransacao();

			new MDFeBD(this.sql).alteraCancelamento(ed, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar cancelamento MDFe", e, this.getClass()
					.getName(), "alteraCancelamento()");
		}
	}

	public void alteraEncerramento(ManifestoED ed, MdfeEvento ret)
	throws Excecoes {

		try {

			this.inicioTransacao();

			new MDFeBD(this.sql).alteraEncerramento(ed, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar encerramento MDFe", e, this.getClass()
					.getName(), "alteraEncerramento()");
		}
	}

	public void atualizaMDFe(ManifestoED ed, MdfeConsulta ret)
	throws Excecoes {

		try {

			this.inicioTransacao();

			new MDFeBD(this.sql).atualizaMDFe(ed, ret);

			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar retorno MDFe", e, this.getClass()
					.getName(), "alteraRetorno()");
		}

	}

	public Mdfe getDados(ManifestoED ed, boolean envio) throws Excecoes {
		Mdfe mdfe = new Mdfe();
		this.inicioTransacao();
		mdfe = new MDFeBD(this.sql).getDados(ed, envio);
		this.fimTransacao(false);
		return mdfe;
	}

	public List<Mdfe> getDados(ManifestoED ed) throws Excecoes {
		List<Mdfe> toReturn = new ArrayList<Mdfe>();
		this.inicioTransacao();
		toReturn = new MDFeBD(this.sql).getDados(ed);
		this.fimTransacao(false);
		return toReturn;
	}

	public ManifestoED getEDByChave(String chave) throws Excecoes {
		ManifestoED mdfe = new ManifestoED();
		this.inicioTransacao();
		mdfe = new MDFeBD(this.sql).getEDByChave(chave);
		this.fimTransacao(false);
		return mdfe;
	}

	public void imprimeDAMDFE(String oid, HttpServletResponse response) throws Exception {

    	try {
    		this.inicioTransacao();

    		ManifestoED ed = new ManifestoED();
    		ed.setOID_Manifesto(oid);
    		Mdfe mdfe = new MDFeBD(this.sql).getDados(ed, false);

			this.fimTransacao(false);

//			mdfe.getEmit().setCNPJ(Formatador.getInstance().formataCnpjNumberToString( mdfe.getEmit().getCNPJ()));
//	        mdfe.getEmit().setCEP(Formatador.getInstance().formataCepNumberString(mdfe.getEmit().getCEP()));

	        ArrayList<Mdfe> list = new ArrayList<Mdfe>();
            list.add(mdfe);

            String emissor = mdfe.getEmit().getCNPJ();
            HashMap parametros = new HashMap();
            String img = "/data/cte/relatorios/"+emissor+".jpg";
//System.out.println("logo:"+img);
            File f = new File(img);
            if (f.exists()) {
                parametros.put("logo", img);
            }
            String pathJasper = Configuracoes.getInstance().getAppDir() + "/relatorios/";
            JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(list);
            String arquivo = "DAMDFe";
            String path = pathJasper + arquivo + ".jasper";
            try {
            	System.out.println("relatorio:"+path);
                JasperPrint jp = JasperFillManager.fillReport(path, parametros, jrbcds);
                response.setHeader("application/pdf", "Content-Type");
                response.setHeader("Content-Disposition:","inline; filename=" + "DAMDFE" + mdfe.getNMDF() + ".pdf");
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
	
	public BaseRequisicao getDadosRaster(ManifestoED ed) throws Excecoes {
		
		BaseRequisicao mdfe = new BaseRequisicao();
		this.inicioTransacao();
		mdfe = new MDFeBD(this.sql).getDadosRaster(ed);
		this.fimTransacao(false);
		return mdfe;
	}

	public void alteraEnvioRaster(ManifestoED ed, BaseRetorno retorno) throws Excecoes {

		try {
			this.inicioTransacao();
			new MDFeBD(this.sql).alteraEnvioRaster(ed, retorno);
			this.fimTransacao(true);
		}
		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}
		catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao alterar envio PreSM", e, this.getClass()
					.getName(), "alteraEnvioRaster()");
		}

	}
}
