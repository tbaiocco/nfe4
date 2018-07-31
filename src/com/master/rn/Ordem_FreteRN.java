package com.master.rn;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import br.nfe.utils.Configuracoes;

import com.master.bd.ColetaBD;
import com.master.bd.CompromissoBD;
import com.master.bd.ManifestoBD;
import com.master.bd.Ordem_FreteBD;
import com.master.bd.PessoaBD;
import com.master.bd.ViagemBD;
import com.master.ed.CFeED;
import com.master.ed.ColetaED;
import com.master.ed.CompromissoED;
import com.master.ed.CompromissoPesquisaED;
import com.master.ed.ManifestoED;
import com.master.ed.Manifesto_InternacionalED;
import com.master.ed.Ordem_FreteED;
import com.master.ed.PessoaED;
import com.master.ed.Tipo_EventoED;
import com.master.ed.ViagemED;
import com.master.rl.Ordem_FreteRL;
import com.master.root.CidadeBean;
import com.master.root.FornecedorBean;
import com.master.root.MotoristaBean;
import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.Transacao;
import com.nuovonet.pamcard.transacional.cfe.CfeFacade;
import com.nuovonet.pamcard.transacional.cfe.imp.CfeFacadeImp;
import com.nuovonet.util.Parametros;

import br.cte.model.Empresa;


public class Ordem_FreteRN extends Transacao  {
  Ordem_FreteBD Ordem_FreteBD = null;


  public Ordem_FreteRN(Empresa empresa) {
    super(empresa);

  }

  public void enviaCFE(Ordem_FreteED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Ordem_Frete()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Ordem_Frete não foi informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      Ordem_FreteBD = new Ordem_FreteBD(this.sql);
	      //aqui faz o get das informacoes para o Map
	      ed = Ordem_FreteBD.getByRecord(ed);
	      //aqui monta o bagulho
	      FileUtil.appendToFile("/data/cte/tmp/logCFE.log", "montando dados para envio. unidade - nr_ordem_frete:" + ed.getOID_Unidade() +" - "+ed.getNR_Ordem_Frete());
	      CfeFacade cfe = new CfeFacadeImp();
	      Map<String, String> map = (HashMap<String, String>)this.montaDadosEnvioCFe(ed);
//	      PamcardUtil.acceptSSL();
	      System.out.println("certif:"+Parametros.CERT_FILE);
//	      cfe.incluirContratoFrete(Configuracoes.getInstance().getAppDir() + "/certificados/pamcard_transmiro_87283164000151.crt",map);
	     
	      cfe.incluirContratoFrete(Parametros.CERT_FILE, Parametros.CERT_PASS, map);
	      
	      FileUtil.appendToFile("/data/cte/tmp/logCFE.log", "" + cfe.toString());
	      //colocar CIOT, Viagem e data
	      ed.setCIOT(cfe.getCiotNumero());
	      ed.setVIAGEMID(cfe.getViagemIdentificacao());
	      ed.setDt_envio_cfe(Data.getDataCompleta());
	      //seta null
	      ed.setDt_encerramento_cfe("");
	      ed.setDt_cancelamento_cfe("");




	      //aqui altera a OF para CIOT e V ID
	      Ordem_FreteBD.alteraEnvio_CFE(ed);

	      this.fimTransacao(true);

	      try{
	    	  this.baixaCompromisso_ADTO_Pamcard(ed, ed.getCIOT());
	      } catch (Exception e){
	    	  System.out.println("problema na baixa do compromisso...");
	    	  FileUtil.appendToFile("/data/cte/tmp/logCFE.log", "Problema na baixa do compromisso: " + e.getMessage());
	    	  e.printStackTrace();
	      }

	    }
	    catch(Excecoes exc){
	    	exc.printStackTrace();
	    	FileUtil.appendToFile("/data/cte/tmp/logCFE.log", "Problema: " + exc.getMessage());
		    this.abortaTransacao();
		    throw exc;
	    }

	    catch(Exception e){
	      //faz rollback pois deu algum erro
	    	e.printStackTrace();
	    	FileUtil.appendToFile("/data/cte/tmp/logCFE.log", "Problema: " + e.getMessage());
	      this.abortaTransacao();

	      throw new Excecoes(e.getMessage(),this.getClass().getName(),"enviaCFE(Ordem_FreteED)");
	    }
	  }

  public void baixaCompromisso_ADTO_Pamcard(Ordem_FreteED ed, String CIOT)throws Excecoes{

	    if (String.valueOf(ed.getOID_Ordem_Frete()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Ordem_Frete não foi informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      Ordem_FreteBD = new Ordem_FreteBD(this.sql);

	      //aqui baixa do compr adto1 automático
	      System.out.println("baixando adto...");
	      int r = Ordem_FreteBD.baixaCompromisso_ADTO_Pamcard(ed, CIOT);
	      System.out.println("fim:"+r);

	      this.fimTransacao(true);

	    }
	    catch(Excecoes exc){
	    	exc.printStackTrace();
		    this.abortaTransacao();
		    throw exc;
	    }

	    catch(Exception e){
	      //faz rollback pois deu algum erro
	    	e.printStackTrace();
	      this.abortaTransacao();

	      throw new Excecoes(e.getMessage(),this.getClass().getName(),"enviaCFE(Ordem_FreteED)");
	    }
	  }

public void encerraCFE(Ordem_FreteED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Ordem_Frete()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Ordem_Frete não foi informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      Ordem_FreteBD = new Ordem_FreteBD(this.sql);
	      //aqui faz o get das informacoes para o Map
	      ed = Ordem_FreteBD.getByRecord(ed);
	      //aqui monta o bagulho
	      CfeFacade cfe = new CfeFacadeImp();
	      Map<String, String> map = (HashMap<String, String>)this.montaDadosEncerramentoCFe(ed);
//	      PamcardUtil.acceptSSL();
	      cfe.encerrarContratoFrete(Parametros.CERT_FILE, Parametros.CERT_PASS,map);
	      //colocar CIOT, Viagem e data
//	      ed.setCIOT(cfe.getCiotNumero());
//	      ed.setVIAGEMID(cfe.getViagemIdentificacao());
//	      ed.setDt_envio_cfe(Data.getDataCompleta());

	      //seta null
	      ed.setDt_encerramento_cfe(Data.getDataCompleta());
	      ed.setDt_cancelamento_cfe("");

	      //aqui altera a OF para CIOT e V ID
	      Ordem_FreteBD.alteraEnvio_CFE(ed);

	      this.fimTransacao(true);

	    }
	    catch(Excecoes exc){
		    this.abortaTransacao();
		    throw exc;
	    }

	    catch(Exception e){
	      //faz rollback pois deu algum erro
	      this.abortaTransacao();

	      throw new Excecoes(e.getMessage(),this.getClass().getName(),"enviaCFE(Ordem_FreteED)");
	    }
	  }

public void cancelaCFE(Ordem_FreteED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Ordem_Frete()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Ordem_Frete não foi informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      Ordem_FreteBD = new Ordem_FreteBD(this.sql);
	      //aqui faz o get das informacoes para o Map
	      ed = Ordem_FreteBD.getByRecord(ed);
	      //aqui monta o bagulho
	      CfeFacade cfe = new CfeFacadeImp();
	      Map<String, String> map = (HashMap<String, String>)this.montaDadosCancelamentoCFe(ed);
//	      PamcardUtil.acceptSSL();
	      cfe.cancelarContratoFrete(Parametros.CERT_FILE, Parametros.CERT_PASS,map);
	      //colocar CIOT, Viagem e data
//	      ed.setCIOT(cfe.getCiotNumero());
//	      ed.setVIAGEMID(cfe.getViagemIdentificacao());
//	      ed.setDt_envio_cfe(Data.getDataCompleta());
	      //seta null
//	      ed.setDt_encerramento_cfe(Data.getDataCompleta());
	      ed.setDt_cancelamento_cfe(Data.getDataCompleta());

	      //aqui altera a OF para CIOT e V ID
	      Ordem_FreteBD.alteraEnvio_CFE(ed);

	      this.fimTransacao(true);

	    }
	    catch(Excecoes exc){
		    this.abortaTransacao();
		    throw exc;
	    }

	    catch(Exception e){
	      //faz rollback pois deu algum erro
	      this.abortaTransacao();

	      throw new Excecoes(e.getMessage(),this.getClass().getName(),"enviaCFE(Ordem_FreteED)");
	    }
	  }

public void imprimeCFe(Ordem_FreteED ed, HttpServletResponse response) throws Excecoes{

	//antes de invocar chamada ao relatorio deve-se
	//fazer validacoes de regra de negocio
	  CFeED cfe = new CFeED();
	  ArrayList lista = new ArrayList();
	  if (String.valueOf(ed.getOID_Ordem_Frete()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Ordem_Frete não foi informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      Ordem_FreteBD = new Ordem_FreteBD(this.sql);
	      //aqui faz o get das informacoes para o Map
	      ed = Ordem_FreteBD.getByRecord(ed);
	      //aqui monta o bagulho
System.out.println("Pega dados...");

			cfe.setCIOT(ed.getCIOT());
			cfe.setVIAGEMID(ed.getVIAGEMID());
			cfe.setNrContrato(String.valueOf(ed.getNR_Ordem_Frete()));

			Utilitaria util = new Utilitaria(this.sql);
			PessoaED favo = new PessoaBD(this.sql).getByRecord(ed.getOID_Fornecedor());
			PessoaED motora = new PessoaBD(this.sql).getByRecord(ed.getOID_Motorista());

			//dados Miro e da unidade
			PessoaED miro = new PessoaBD(this.sql).getByRecord("87283164000151");
			UnidadeBean un = UnidadeBean.getByOID_Unidade(new Long(ed.getOID_Unidade()).intValue());
			String aux = "";
			aux += ""+(JavaUtil.doValida(miro.getNR_CNPJ_CPF())?miro.getNR_CNPJ_CPFFormatado():"")+" / "+(JavaUtil.doValida(miro.getNM_Razao_Social())?miro.getNM_Razao_Social():"")+"\n";
			aux += "\n";
			aux += "END.   : "+(JavaUtil.doValida(miro.getNM_Endereco())?miro.getNM_Endereco():"")+" - "+(JavaUtil.doValida(miro.getNM_Bairro())?miro.getNM_Bairro():"")+"\n";
			aux += "CIDADE : "+(JavaUtil.doValida(miro.getCD_Estado())?miro.getCD_Estado()+"/":"")+(JavaUtil.doValida(miro.getNM_Cidade())?miro.getNM_Cidade():"")+"\t CEP: "+(JavaUtil.doValida(miro.getNR_CEP())?miro.getNR_CEP():"")+"\n";
			aux += "FONE   : "+(JavaUtil.doValida(miro.getNR_Telefone())?miro.getNR_Telefone():"")+"\n";
			cfe.setDadosEmpresa(aux);
			cfe.setUnidade("UNIDADE: "+(JavaUtil.doValida(un.getCD_Unidade())?un.getCD_Unidade():"")+" - "+(JavaUtil.doValida(un.getNM_Fantasia())?un.getNM_Fantasia():""));

			//motorista
			aux = "";
			aux += ""+(JavaUtil.doValida(motora.getNR_CNPJ_CPF())?motora.getNR_CNPJ_CPFFormatado():"")+" / "+(JavaUtil.doValida(motora.getNM_Razao_Social())?motora.getNM_Razao_Social():"")+"\n";
			aux += "END.     : "+(JavaUtil.doValida(motora.getNM_Endereco())?motora.getNM_Endereco():"")+" - "+(JavaUtil.doValida(motora.getNM_Bairro())?motora.getNM_Bairro():"")+"\n";
			aux += "CIDADE   : "+(JavaUtil.doValida(motora.getNM_Cidade())?motora.getNM_Cidade()+"/"+motora.getCD_Estado():"")+"\t\t\t CEP: "+(JavaUtil.doValida(motora.getNR_CEP())?motora.getNR_CEP():"")+"\n";
			aux += "FONE     : "+(JavaUtil.doValida(motora.getNR_Telefone())?motora.getNR_Telefone():"")+"\n";
			cfe.setDadosMotorista(aux);

			//fornecedor
			MotoristaBean mot = MotoristaBean.getByOID_Motorista(favo.getOid_Pessoa());
			FornecedorBean forn = FornecedorBean.getByOID_Fornecedor(mot.getOID_Pessoa());
			String cc = (JavaUtil.doValida(forn.getCD_Banco())?forn.getCD_Banco():"") + "/";
			cc += (JavaUtil.doValida(forn.getNM_Agencia())?forn.getNM_Agencia():"") + "-";
			cc += (JavaUtil.doValida(forn.getCD_Conta_Corrente())?forn.getCD_Conta_Corrente():"") + "";

			aux = "";
			aux += ""+(JavaUtil.doValida(favo.getNR_CNPJ_CPF())?favo.getNR_CNPJ_CPFFormatado():"")+" / "+(JavaUtil.doValida(favo.getNM_Razao_Social())?favo.getNM_Razao_Social():"")+"\n";
			aux += "END.       : "+(JavaUtil.doValida(favo.getNM_Endereco())?favo.getNM_Endereco():"")+" - "+(JavaUtil.doValida(favo.getNM_Bairro())?favo.getNM_Bairro():"")+"\n";
			aux += "CIDADE     : "+(JavaUtil.doValida(favo.getNM_Cidade())?favo.getNM_Cidade()+"/"+favo.getCD_Estado():"")+"\t\t\t CEP: "+(JavaUtil.doValida(favo.getNR_CEP())?favo.getNR_CEP():"")+"\n";
			aux += "FONE       : "+(JavaUtil.doValida(favo.getNR_Telefone())?favo.getNR_Telefone():"")+"\n";
			aux += " \n";
			if(JavaUtil.doValida(mot.getNR_Cartao_CFE()))
				aux += "CARTÃO     : "+(JavaUtil.doValida(mot.getNR_Cartao_CFE())?mot.getNR_Cartao_CFE():"")+"\n";
			if(JavaUtil.doValida(forn.getCD_Conta_Corrente()))
				aux += "BANCO/AG-CC: "+cc+"\n";
			cfe.setDadosFornecedor(aux);

			VeiculoBean veic = VeiculoBean.getByOID(ed.getOID_Veiculo());
			aux = "";
			aux += "VEÍCULO: "+(JavaUtil.doValida(veic.getNR_Placa())?veic.getNR_Placa():"")+"\t\t\t\t\t\t\t\t\t\t\t\t\t RNTRC: "+(JavaUtil.doValida(veic.getNR_RNTRC())?veic.getNR_RNTRC():"")+"\n";
			cfe.setDadosVeiculo(aux);

			//dados de manifesto e documentos
			String oid_man = util.getTableStringValue("oid_manifesto", "ordens_manifestos", "oid_ordem_frete='"+ed.getOID_Ordem_Frete()+"'");
			ManifestoED man = new ManifestoED();
			man.setOID_Manifesto(oid_man);
			man = new ManifestoBD(this.sql).getByRecord(man);
			cfe.setDtEmbarque(man.getDT_Emissao()+" - "+man.getHR_Saida());
			cfe.setDtEmbarque(man.getDT_Previsao_Chegada()+" - "+man.getHR_Previsao_Chegada());
			CidadeBean cid = CidadeBean.getByOID(new Long(man.getOID_Cidade_Origem()).intValue());
			cfe.setOrigem(cid.getCD_Estado()+"/"+cid.getNM_Cidade());
			cid = CidadeBean.getByOID(new Long(man.getOID_Cidade()).intValue());
			cfe.setDestino(cid.getCD_Estado()+"/"+cid.getNM_Cidade());

			double pedagio_total = ed.getVL_Pedagio()+ed.getVL_Vale_Pedagio()+ed.getVL_Vale_Pedagio_Empresa();

			cfe.setVlPeso(man.getNR_Total_Peso_CTRC());
			cfe.setVlFrete(man.getVL_Total_Frete_CTRC());

			//dados dos CTe e NFs...
			ViagemED viagem = new ViagemED();
			viagem.setOID_Manifesto(man.getOID_Manifesto());
			ArrayList docs = new ViagemBD(this.sql).lista(viagem);
			java.util.Iterator it = docs.iterator();
			int contaDoc = 1;

			String docsOF = "";
			aux="";
			while(it.hasNext()){
				ViagemED v = (ViagemED)it.next();
				v = new ViagemBD(this.sql).getByRecord(v);
				aux+=contaDoc+": ";
				aux+=v.getNR_Conhecimento()>0?"CTe:"+String.valueOf(v.getNR_Conhecimento()+" "):"NFe:"+String.valueOf(v.getNR_Nota_Fiscal())+" ";
				aux+="\t\t\t";
				aux+="| VOL:"+new DecimalFormat("#,##0.00").format(v.getNR_Volumes());
				aux+="\t\t\t";
				aux+="| PESO:"+new DecimalFormat("#,##0.00").format(v.getNR_Peso());
				aux+="\t\t\t";
				aux+="| MERC.:"+new DecimalFormat("#,##0.00").format(v.getVL_Nota_Fiscal());
				aux+="\t\t\t";
				aux+="| FRETE:"+new DecimalFormat("#,##0.00").format(v.getVL_Total_Frete());
//				aux+="\t";

//				PessoaED rem = new PessoaBD(this.sql).getByRecord(v.getOID_Pessoa());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.documento.tipo"), rem.getNR_CNPJ_CPF().length()>11?"1":"2");
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.documento.numero"), rem.getNR_CNPJ_CPF());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.nome"), rem.getNM_Razao_Social());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.logradouro"), JavaUtil.trunc(rem.getNM_Endereco(),40));
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.numero"), "0");
////				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.complemento"), "");
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.bairro"), rem.getNM_Bairro());
//				cid = new CidadeBean().getByOID(new Long(rem.getOid_Cidade()).intValue());
//				cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.cidade.ibge"), cUF+cid.getNM_Codigo_IBGE());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.cep"), rem.getNR_CEP());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.tipo"), "2"); //1-rem, 2-dest, 3-cons
////				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.codigo"), "2");
//				PessoaED dest = new PessoaBD(this.sql).getByRecord(v.getOID_Pessoa_Destinatario());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.documento.tipo"), dest.getNR_CNPJ_CPF().length()>11?"1":"2");
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.documento.numero"), dest.getNR_CNPJ_CPF());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.nome"), dest.getNM_Razao_Social());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.logradouro"), JavaUtil.trunc(dest.getNM_Endereco(),40));
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.numero"), "0");
////				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.complemento"), "");
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.bairro"), dest.getNM_Bairro());
//				cid = new CidadeBean().getByOID(new Long(dest.getOid_Cidade()).intValue());
//				cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.cidade.ibge"), cUF+cid.getNM_Codigo_IBGE());
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.cep"), dest.getNR_CEP());

				aux+="\n";
				contaDoc++;
			}
			cfe.setDadosDocumentos(aux);

			//valores
			String cpr1 = "",cpr2 = "",cpr3 = "";
			CompromissoPesquisaED edComp = new CompromissoPesquisaED();
			edComp.setOID_Ordem_Frete(ed.getOID_Ordem_Frete());
			ArrayList cpgs = new CompromissoBD(this.sql).lista_Ordem(edComp);
			it = cpgs.iterator();
			while(it.hasNext()){
				CompromissoED cpg = (CompromissoED)it.next();
				if(cpg.getNr_Parcela().intValue()==1)
					cpr1=String.valueOf(cpg.getNr_Compromisso());
				if(cpg.getNr_Parcela().intValue()==2)
					cpr2=String.valueOf(cpg.getNr_Compromisso());
				if(cpg.getNr_Parcela().intValue()==3)
					cpr3=String.valueOf(cpg.getNr_Compromisso());
			}
			aux="";
			cfe.setVlBruto(ed.getVL_Ordem_Frete()+ed.getVL_Descarga()+ed.getVL_Outros());
			cfe.setVlLiquido(ed.getVL_Liquido_Ordem_Frete());
			cfe.setVlIRRF(ed.getVL_IRRF());
			cfe.setVlSENAT(ed.getVL_Set_Senat());
			cfe.setVlINSS(ed.getVL_INSS_Prestador());
			cfe.setVlOutros(ed.getVL_Descontos());

			int parcelas = 1;
			aux="";
			if(ed.getVL_Adiantamento1()>0){
				aux+=parcelas+": ADTO 1";
				aux+="\t";
				aux+="| AUTOMÁTICO";
				aux+="\t";
				aux+="| DT:"+ed.getDT_Adiantamento1();
				aux+="\t";
				aux+="| VL:"+new DecimalFormat("#,##0.00").format(ed.getVL_Adiantamento1());
				aux+="\t";
				aux+="| Comp.:"+cpr1;
				aux+="\n";
				parcelas++;
			}
			cfe.setDadosAdto1(aux);
			aux="";
			if(ed.getVL_Adiantamento2()>0){
				aux+=parcelas+": ADTO 2";
				aux+="\t";
				aux+="| AUTOMÁTICO";
				aux+="\t";
				aux+="| DT:"+ed.getDT_Adiantamento2();
				aux+="\t";
				aux+="| VL:"+new DecimalFormat("#,##0.00").format(ed.getVL_Adiantamento2());
				aux+="\t";
				aux+="| Comp.:"+cpr2;
				aux+="\n";
				parcelas++;
			}
			cfe.setDadosAdto2(aux);
			if(!JavaUtil.doValida(cpr3) && ed.getVL_Saldo()>0)
				cpr3=cpr2;
			if(!JavaUtil.doValida(cpr3) && ed.getVL_Saldo()>0)
				cpr3=cpr1;
			aux="";
			if(ed.getVL_Saldo()>0){
				aux+=parcelas+": SALDO";
				aux+="\t";
				aux+="| MANUAL";
				aux+="\t";
				aux+="| DT:"+ed.getDT_Saldo();
				aux+="\t";
				aux+="| VL:"+new DecimalFormat("#,##0.00").format(ed.getVL_Saldo());
				aux+="\t";
				aux+="| Comp.:"+cpr3;
				aux+="\n";
				parcelas++;
			}
			cfe.setDadosSaldo(aux);

//			cfe.setObs(pgtos+docsOF);

	      this.fimTransacao(false);

//	      new Ordem_FreteRL().imprimeCFe(cfe, response);

	    }
	    catch(Excecoes exc){
	    	exc.printStackTrace();
		    this.abortaTransacao();
		    throw exc;
	    }

	    catch(Exception e){
	      //faz rollback pois deu algum erro
	    	e.printStackTrace();
	      this.abortaTransacao();

	      throw new Excecoes(e.getMessage(),this.getClass().getName(),"imprimeCFe(Ordem_FreteED)");
	    }
	  }

private Map montaDadosEnvioCFe(Ordem_FreteED ed) throws Excecoes{
	  Map<String, String> map = new LinkedHashMap<String, String>();
	  String tp_favorecido_parcelas="3";
	  boolean contacorrente = false;
	  String vlTarifaPamcary = "5";
	  String qtTarifaPamcary = "4";
	  String tpTarifaPamcary = "315";
	  String tpTarifaPamcary_Transferencia = "316";
	  String vlTarifaPamcary_Transferencia = "5";
	  String qtTarifaPamcary_Transferencia = "4";
	  double vlTotal_Tarifa = 0;
	  try {
System.out.println("Monta dados...");
			Utilitaria util = new Utilitaria(this.sql);
			map.put("transacional.operacao", "26");
			map.put("viagem.contratante.documento.numero", "87283164000151");
			map.put("viagem.id.cliente", ManipulaString.corrigeNumero(ed.getOID_Ordem_Frete()));
			map.put("viagem.contrato.numero", String.valueOf(ed.getNR_Ordem_Frete()));

			VeiculoBean veic =  new Ordem_FreteBD(this.sql).getVeiculo(ed.getOID_Veiculo());
//System.out.println(ed.getOID_Veiculo()+"..."+veic.getNR_Placa());
			PessoaED favo = new PessoaED();
			if ("A".equals(ed.getDM_Frete())){
				favo = new PessoaBD(this.sql).getByRecord(ed.getOID_Motorista());
			}else{
				favo = new PessoaBD(this.sql).getByRecord(ed.getOID_Fornecedor());
			}
			PessoaED prop = new PessoaBD(this.sql).getByRecord(veic.getOID_Pessoa());
			//Vai o FORNECEDOR sempre, se precisar vai motorista também
			PessoaED motora = new PessoaBD(this.sql).getByRecord(ed.getOID_Motorista());

			//questão do proprietario vs motorista
			boolean esqMiro = false;

			esqMiro = (favo.getOid_Pessoa().equals(motora.getOid_Pessoa())) && !(prop.getOid_Pessoa().equals(motora.getOid_Pessoa()));
//			esqMiro = !(prop.getOid_Pessoa().equals(motora.getOid_Pessoa()));

			if(esqMiro){
				map.put("viagem.favorecido.qtde", "2"); //proprietario e motora

				//motora
				MotoristaBean mot = new Ordem_FreteBD(this.sql).getMotorista(favo.getOid_Pessoa());

				FornecedorBean forn = new Ordem_FreteBD(this.sql).getFornecedor(mot.getOID_Pessoa());

				if(!JavaUtil.doValida(mot.getNR_Cartao_CFE())){
					contacorrente = true;
				}

//				if(!JavaUtil.doValida(mot.getNR_Cartao_CFE()) && !contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem NÚMERO DE CARTÃO no cadastro!");
//
//				if(!JavaUtil.doValida(forn.getCD_Conta_Corrente()) && contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem CONTA CORRENTE no cadastro!");
//				if(!JavaUtil.doValida(forn.getNM_Agencia()) && contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem AGENCIA no cadastro!");
//				if(!JavaUtil.doValida(forn.getCD_Banco()) && contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem BANCO no cadastro!");

				map.put("viagem.favorecido1.tipo", "3"); //1-contratado, 2-sub-contratante, 3-motorista
				tp_favorecido_parcelas="3";
				map.put("viagem.favorecido1.documento.qtde", "1");
				map.put("viagem.favorecido1.documento1.tipo", favo.getNR_CNPJ_CPF().length()>11?"1":"2"); //1-cnpj, 2-cpf
				map.put("viagem.favorecido1.documento1.numero", favo.getNR_CNPJ_CPF());
				map.put("viagem.favorecido1.nome", favo.getNM_Razao_Social());
				if(favo.getNR_CNPJ_CPF().length()==11) {
					map.put("viagem.favorecido1.data.nascimento", mot.getDT_Nascimento());
				}
				map.put("viagem.favorecido1.endereco.logradouro", favo.getNM_Endereco());
				map.put("viagem.favorecido1.endereco.bairro", favo.getNM_Bairro());
				CidadeBean cid = new Ordem_FreteBD(this.sql).getCidade(new Long(favo.getOid_Cidade()).intValue());
				String cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
				map.put("viagem.favorecido1.endereco.cidade.ibge", cUF+cid.getNM_Codigo_IBGE());
				map.put("viagem.favorecido1.endereco.cep", favo.getNR_CEP());
				if(!contacorrente){
					map.put("viagem.favorecido1.meio.pagamento", "1"); //1-cartao, 2-conta
					if(JavaUtil.doValida(mot.getNR_Cartao_CFE()))
						map.put("viagem.favorecido1.cartao.numero", mot.getNR_Cartao_CFE());
				} else {
					map.put("viagem.favorecido1.meio.pagamento", "2"); //1-cartao, 2-conta
					if(JavaUtil.doValida(forn.getCD_Banco()))
						map.put("viagem.favorecido1.conta.banco", forn.getCD_Banco());
					if(JavaUtil.doValida(forn.getNM_Agencia()))
						map.put("viagem.favorecido1.conta.agencia", forn.getNM_Agencia());
					if(JavaUtil.doValida(forn.getCD_Conta_Corrente()))
						map.put("viagem.favorecido1.conta.numero", forn.getCD_Conta_Corrente());
					map.put("viagem.favorecido1.conta.tipo", "1"); //1 - CC, 2 - Poup
				}

//				contratado - caso for proprietario diferente do motorista
				mot = new Ordem_FreteBD(this.sql).getMotorista(prop.getOid_Pessoa());
//				if(!JavaUtil.doValida(mot.getNR_Cartao_CFE()))
//					throw new Excecoes("Proprietário sem NÚMERO DE CARTÃO no cadastro!");
				map.put("viagem.favorecido2.tipo", "1"); //1-contratado, 2-sub-contratante, 3-motorista
				map.put("viagem.favorecido2.documento.qtde", "1");
				map.put("viagem.favorecido2.documento1.tipo", prop.getNR_CNPJ_CPF().length()>11?"1":"2"); //1-cnpj, 2-cpf
				map.put("viagem.favorecido2.documento1.numero", prop.getNR_CNPJ_CPF());
				map.put("viagem.favorecido2.nome", prop.getNM_Razao_Social());
				if(JavaUtil.doValida(mot.getDT_Nascimento()))
					map.put("viagem.favorecido2.data.nascimento", mot.getDT_Nascimento());
				if(JavaUtil.doValida(prop.getNM_Endereco()))
					map.put("viagem.favorecido2.endereco.logradouro", prop.getNM_Endereco());
				if(JavaUtil.doValida(prop.getNM_Bairro()))
					map.put("viagem.favorecido2.endereco.bairro", prop.getNM_Bairro());
				cid = new Ordem_FreteBD(this.sql).getCidade(new Long(prop.getOid_Cidade()).intValue());
				cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
				map.put("viagem.favorecido2.endereco.cidade.ibge", cUF+cid.getNM_Codigo_IBGE());
				if(JavaUtil.doValida(prop.getNR_CEP()))
					map.put("viagem.favorecido2.endereco.cep", prop.getNR_CEP());
				if(!contacorrente){
					map.put("viagem.favorecido2.meio.pagamento", "1"); //1-cartao, 2-conta
					if(JavaUtil.doValida(mot.getNR_Cartao_CFE())){
						map.put("viagem.favorecido2.cartao.numero", mot.getNR_Cartao_CFE());
					}
				} else {
					map.put("viagem.favorecido2.meio.pagamento", "2"); //1-cartao, 2-conta
					if(JavaUtil.doValida(forn.getCD_Banco()))
						map.put("viagem.favorecido2.conta.banco", forn.getCD_Banco());
					if(JavaUtil.doValida(forn.getNM_Agencia()))
						map.put("viagem.favorecido2.conta.agencia", forn.getNM_Agencia());
					if(JavaUtil.doValida(forn.getCD_Conta_Corrente()))
						map.put("viagem.favorecido2.conta.numero", forn.getCD_Conta_Corrente());

					map.put("viagem.favorecido2.conta.tipo", "1"); //1 - CC, 2 - Poup
				}


			} else {
				if(favo.getOid_Pessoa().equals(motora.getOid_Pessoa()))
					map.put("viagem.favorecido.qtde", "1"); //se for o mesmo soh deve ter um fornecedor como contratado
				else
					map.put("viagem.favorecido.qtde", "2"); //se for motora e contratado

				MotoristaBean mot = new Ordem_FreteBD(this.sql).getMotorista(favo.getOid_Pessoa());

				FornecedorBean forn = new Ordem_FreteBD(this.sql).getFornecedor(mot.getOID_Pessoa());

				if(!JavaUtil.doValida(mot.getNR_Cartao_CFE())){
					contacorrente = true;
				}

//				if(!JavaUtil.doValida(mot.getNR_Cartao_CFE()) && !contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem NÚMERO DE CARTÃO no cadastro!");
//
//				if(!JavaUtil.doValida(forn.getCD_Conta_Corrente()) && contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem CONTA CORRENTE no cadastro!");
//				if(!JavaUtil.doValida(forn.getNM_Agencia()) && contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem AGENCIA no cadastro!");
//				if(!JavaUtil.doValida(forn.getCD_Banco()) && contacorrente)
//					throw new Excecoes("Contratado/FORNECEDOR sem BANCO no cadastro!");

				//contratado
				map.put("viagem.favorecido1.tipo", "1"); //1-contratado, 2-sub-contratante, 3-motorista
				tp_favorecido_parcelas="1";
				map.put("viagem.favorecido1.documento.qtde", "1");
				map.put("viagem.favorecido1.documento1.tipo", favo.getNR_CNPJ_CPF().length()>11?"1":"2"); //1-cnpj, 2-cpf
				map.put("viagem.favorecido1.documento1.numero", favo.getNR_CNPJ_CPF());
				map.put("viagem.favorecido1.nome", favo.getNM_Razao_Social());
				if(favo.getNR_CNPJ_CPF().length()==11) {
					map.put("viagem.favorecido1.data.nascimento", mot.getDT_Nascimento());
				}
				map.put("viagem.favorecido1.endereco.logradouro", favo.getNM_Endereco());
				map.put("viagem.favorecido1.endereco.bairro", favo.getNM_Bairro());
				CidadeBean cid = new Ordem_FreteBD(this.sql).getCidade(new Long(favo.getOid_Cidade()).intValue());
				String cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
				map.put("viagem.favorecido1.endereco.cidade.ibge", cUF+cid.getNM_Codigo_IBGE());
				map.put("viagem.favorecido1.endereco.cep", favo.getNR_CEP());
				if(!contacorrente){
					map.put("viagem.favorecido1.meio.pagamento", "1"); //1-cartao, 2-conta
					if(JavaUtil.doValida(mot.getNR_Cartao_CFE()))
						map.put("viagem.favorecido1.cartao.numero", mot.getNR_Cartao_CFE());
				} else {
					map.put("viagem.favorecido1.meio.pagamento", "2"); //1-cartao, 2-conta
					if(JavaUtil.doValida(forn.getCD_Banco()))
						map.put("viagem.favorecido1.conta.banco", forn.getCD_Banco());
					if(JavaUtil.doValida(forn.getNM_Agencia()))
						map.put("viagem.favorecido1.conta.agencia", forn.getNM_Agencia());
					if(JavaUtil.doValida(forn.getCD_Conta_Corrente()))
						map.put("viagem.favorecido1.conta.numero", forn.getCD_Conta_Corrente());

					map.put("viagem.favorecido1.conta.tipo", "1"); //1 - CC, 2 - Poup
				}

//				motorista - caso for fornecedor diferente do motorista
				if(!favo.getOid_Pessoa().equals(motora.getOid_Pessoa())){
					mot = new Ordem_FreteBD(this.sql).getMotorista(motora.getOid_Pessoa());
					forn = new Ordem_FreteBD(this.sql).getFornecedor(mot.getOID_Pessoa());

					if(!JavaUtil.doValida(mot.getNR_Cartao_CFE())){
						contacorrente = true;
					}
//
//					if(!JavaUtil.doValida(mot.getNR_Cartao_CFE()) && !contacorrente)
//						throw new Excecoes("Contratado/FORNECEDOR sem NÚMERO DE CARTÃO no cadastro!");
//
//					if(!JavaUtil.doValida(forn.getCD_Conta_Corrente()) && contacorrente)
//						throw new Excecoes("Contratado/FORNECEDOR sem CONTA CORRENTE no cadastro!");
//					if(!JavaUtil.doValida(forn.getNM_Agencia()) && contacorrente)
//						throw new Excecoes("Contratado/FORNECEDOR sem AGENCIA no cadastro!");
//					if(!JavaUtil.doValida(forn.getCD_Banco()) && contacorrente)
//						throw new Excecoes("Contratado/FORNECEDOR sem BANCO no cadastro!");

					map.put("viagem.favorecido2.tipo", "3"); //1-contratado, 2-sub-contratante, 3-motorista
					map.put("viagem.favorecido2.documento.qtde", "1");
					map.put("viagem.favorecido2.documento1.tipo", motora.getNR_CNPJ_CPF().length()>11?"1":"2"); //1-cnpj, 2-cpf
					map.put("viagem.favorecido2.documento1.numero", motora.getNR_CNPJ_CPF());
					map.put("viagem.favorecido2.nome", motora.getNM_Razao_Social());
					if(JavaUtil.doValida(mot.getDT_Nascimento()))
						map.put("viagem.favorecido2.data.nascimento", mot.getDT_Nascimento());
					if(JavaUtil.doValida(motora.getNM_Endereco()))
						map.put("viagem.favorecido2.endereco.logradouro", motora.getNM_Endereco());
					if(JavaUtil.doValida(motora.getNM_Bairro()))
						map.put("viagem.favorecido2.endereco.bairro", motora.getNM_Bairro());
					cid = new Ordem_FreteBD(this.sql).getCidade(new Long(motora.getOid_Cidade()).intValue());
					cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
					map.put("viagem.favorecido2.endereco.cidade.ibge", cUF+cid.getNM_Codigo_IBGE());
					if(JavaUtil.doValida(motora.getNR_CEP()))
						map.put("viagem.favorecido2.endereco.cep", motora.getNR_CEP());
					if(!contacorrente){
						map.put("viagem.favorecido2.meio.pagamento", "1"); //1-cartao, 2-conta
						if(JavaUtil.doValida(mot.getNR_Cartao_CFE())){
							map.put("viagem.favorecido2.cartao.numero", mot.getNR_Cartao_CFE());
						}
					} else {
						map.put("viagem.favorecido2.meio.pagamento", "2"); //1-cartao, 2-conta
						if(JavaUtil.doValida(forn.getCD_Banco()))
							map.put("viagem.favorecido2.conta.banco", forn.getCD_Banco());
						if(JavaUtil.doValida(forn.getNM_Agencia()))
							map.put("viagem.favorecido2.conta.agencia", forn.getNM_Agencia());
						if(JavaUtil.doValida(forn.getCD_Conta_Corrente()))
							map.put("viagem.favorecido2.conta.numero", forn.getCD_Conta_Corrente());

						map.put("viagem.favorecido2.conta.tipo", "1"); //1 - CC, 2 - Poup
					}

				}
			}

			//usar o mesmo para teste, depois pega da OF
			if(!JavaUtil.doValida(veic.getNR_RNTRC()))
				throw new Excecoes("Veiculo sem RNTRC no cadastro!");
			map.put("viagem.veiculo.qtde", "1");
			map.put("viagem.veiculo1.placa", veic.getNR_Placa().trim());
			map.put("viagem.veiculo1.rntrc", veic.getNR_RNTRC().trim());
			map.put("viagem.veiculo.categoria", "3");

			//dados de manifesto e documentos
			String oid_man = util.getTableStringValue("oid_manifesto", "ordens_manifestos", "oid_ordem_frete='"+ed.getOID_Ordem_Frete()+"'");
			ManifestoED man = new ManifestoED();
			man.setOID_Manifesto(oid_man);

			man = new ManifestoBD(this.sql).getByRecord(man);

			if(Data.comparaData(Data.getDataDMY(),man.getDT_Emissao()))
				man.setDT_Emissao(Data.getDataDMY());
			if(Data.comparaData(Data.getDataDMY(),man.getDT_Previsao_Chegada()))
				man.setDT_Previsao_Chegada(Data.getDataDMY());
			map.put("viagem.data.partida", man.getDT_Emissao());
			map.put("viagem.data.termino", man.getDT_Previsao_Chegada());
//			map.put("viagem.rota.id", "");
//			map.put("viagem.rota.nome", "");
			CidadeBean cid = new CidadeBean().getByOID(new Long(man.getOID_Cidade_Origem()).intValue());
			String cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
			map.put("viagem.origem.cidade.ibge", cUF+cid.getNM_Codigo_IBGE());
			cid = new CidadeBean().getByOID(new Long(man.getOID_Cidade()).intValue());
			cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
			map.put("viagem.destino.cidade.ibge", cUF+cid.getNM_Codigo_IBGE());
			map.put("viagem.ponto.qtde", "1");
			map.put("viagem.ponto1.cidade.ibge", cUF+cid.getNM_Codigo_IBGE());

			double pedagio_total = ed.getVL_Pedagio()+ed.getVL_Vale_Pedagio()+ed.getVL_Vale_Pedagio_Empresa();

			if(pedagio_total <= 0){
//				throw new Excecoes("Valor do Pedágio (incluindo vales cliente e empresa) deve ser maior que 0 (zero)!");
			} else {
//				map.put("viagem.pedagio.solucao.id", "");
				map.put("viagem.pedagio.valor", String.valueOf(pedagio_total));
//				map.put("viagem.pedagio.status.id", "");//1 - Pendente, 2 - Liberada, 3 - Bloqueada, 4 - Excluida
				map.put("viagem.pedagio.roteirizar", "N");
				map.put("viagem.pedagio.obter.praca", "N");
//				map.put("viagem.pedagio.utiliza.saldo", "N");
//				map.put("viagem.pedagio.cartao", "");
			}

//			map.put("viagem.carga.tipo", "");

			//dados dos CTe e NFs...
			map.put("viagem.carga.natureza", "1210"); //????
			map.put("viagem.carga.peso", String.valueOf(Valor.trunc(man.getNR_Total_Peso_CTRC(),2)));
			ViagemED viagem = new ViagemED();
			viagem.setOID_Manifesto(man.getOID_Manifesto());
			ArrayList docs = new ViagemBD(this.sql).lista(viagem);
			map.put("viagem.documento.qtde", String.valueOf(docs.size()));
			java.util.Iterator it = docs.iterator();
			int contaDoc = 1;

			String docsOF = "";

			while(it.hasNext()){
				ViagemED v = (ViagemED)it.next();
				v = new ViagemBD(this.sql).getByRecord(v);
				docsOF+=v.getNR_Conhecimento()>0?"CTe:"+String.valueOf(v.getNR_Conhecimento()+" "):"NFe:"+String.valueOf(v.getNR_Nota_Fiscal())+" ";
				map.put(new String("viagem.documento"+contaDoc+".tipo"), v.getNR_Conhecimento()>0?"5":"6"); // Tipo igual a 5 é conhecimento de frete, 6 é nota fiscal
				map.put(new String("viagem.documento"+contaDoc+".numero"), v.getNR_Conhecimento()>0?String.valueOf(v.getNR_Conhecimento()):String.valueOf(v.getNR_Nota_Fiscal()));
				map.put(new String("viagem.documento"+contaDoc+".serie"), "1");
				map.put(new String("viagem.documento"+contaDoc+".quantidade"), String.valueOf(v.getNR_Volumes()));
				map.put(new String("viagem.documento"+contaDoc+".especie"), "MERCADORIA");
				map.put(new String("viagem.documento"+contaDoc+".cubagem"), "1");
				map.put(new String("viagem.documento"+contaDoc+".natureza"), "1210");
				map.put(new String("viagem.documento"+contaDoc+".peso"), String.valueOf(v.getNR_Peso()));
				if(v.getVL_Nota_Fiscal()>0){
					map.put(new String("viagem.documento"+contaDoc+".mercadoria.valor"), String.valueOf(v.getVL_Nota_Fiscal()));
				} else {
					map.put(new String("viagem.documento"+contaDoc+".mercadoria.valor"), "500.0");
				}
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal.qtde"), "2");
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.tipo"), "1"); //1-rem, 2-dest, 3-cons
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.codigo"), "1");
				PessoaED rem = new PessoaBD(this.sql).getByRecord(v.getOID_Pessoa());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.documento.tipo"), rem.getNR_CNPJ_CPF().length()>11?"1":"2");
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.documento.numero"), rem.getNR_CNPJ_CPF());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.nome"), rem.getNM_Razao_Social());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.logradouro"), JavaUtil.trunc(rem.getNM_Endereco(),40));
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.numero"), "0");
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.complemento"), "");
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.bairro"), rem.getNM_Bairro());
				cid = new CidadeBean().getByOID(new Long(rem.getOid_Cidade()).intValue());
				cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.cidade.ibge"), cUF+cid.getNM_Codigo_IBGE());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal1.endereco.cep"), rem.getNR_CEP());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.tipo"), "2"); //1-rem, 2-dest, 3-cons
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.codigo"), "2");
				PessoaED dest = new PessoaBD(this.sql).getByRecord(v.getOID_Pessoa_Destinatario());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.documento.tipo"), dest.getNR_CNPJ_CPF().length()>11?"1":"2");
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.documento.numero"), dest.getNR_CNPJ_CPF());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.nome"), dest.getNM_Razao_Social());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.logradouro"), JavaUtil.trunc(dest.getNM_Endereco(),40));
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.numero"), "0");
//				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.complemento"), "");
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.bairro"), dest.getNM_Bairro());
				cid = new CidadeBean().getByOID(new Long(dest.getOid_Cidade()).intValue());
				cUF = util.getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+cid.getOID_Estado());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.cidade.ibge"), cUF+cid.getNM_Codigo_IBGE());
				map.put(new String("viagem.documento"+contaDoc+".pessoafiscal2.endereco.cep"), dest.getNR_CEP());

				contaDoc++;
			}

			map.put("viagem.documento.complementar.qtde", "0");
//			map.put("viagem.documento.complementarN.tipo", "");

			if(JavaUtil.doValida(ed.getTpTarifaPamcary()))
				tpTarifaPamcary = ed.getTpTarifaPamcary();
			if(JavaUtil.doValida(ed.getVlTarifaPamcary()))
				vlTarifaPamcary = ed.getVlTarifaPamcary();
			if(JavaUtil.doValida(ed.getQtTarifaPamcary()))
				qtTarifaPamcary = ed.getQtTarifaPamcary();

			if(JavaUtil.doValida(ed.getTpTarifaPamcary_Transferencia()))
				tpTarifaPamcary_Transferencia = ed.getTpTarifaPamcary_Transferencia();
			if(JavaUtil.doValida(ed.getVlTarifaPamcary_Transferencia()))
				vlTarifaPamcary_Transferencia = ed.getVlTarifaPamcary_Transferencia();
			if(JavaUtil.doValida(ed.getQtTarifaPamcary_Transferencia()))
				qtTarifaPamcary_Transferencia = ed.getQtTarifaPamcary_Transferencia();

			// Para ordem de frete adiantamento da frota, a tarifa é a metade

			if ("A".equals(ed.getDM_Frete())){
				vlTarifaPamcary = String.valueOf(new Double(vlTarifaPamcary).doubleValue() / 2);
				vlTarifaPamcary_Transferencia = String.valueOf(new Double(vlTarifaPamcary_Transferencia).doubleValue() / 2);
			}


			vlTotal_Tarifa = new Double(vlTarifaPamcary).doubleValue() + new Double(vlTarifaPamcary_Transferencia).doubleValue();
			int parcelas = 1;

			//primeiro vai a tarifa saque
			map.put("viagem.parcela1.efetivacao.tipo", "2");//1 - manual, 2 - Automática, 4 - Quintação
			map.put("viagem.parcela1.valor", String.valueOf(vlTotal_Tarifa));
			map.put("viagem.parcela1.subtipo", "1"); //adto
//			map.put("viagem.parcela1.base", "N");
			if (ed.getVL_Adiantamento1()==0 && ed.getVL_Adiantamento2()==0){
				map.put("viagem.parcela1.status.id", "1");//1 - Pendente, 2 - Liberada, 3 - Bloqueada, 4 - Excluida
			}else{
				map.put("viagem.parcela1.status.id", "2");//1 - Pendente, 2 - Liberada, 3 - Bloqueada, 4 - Excluida
			}
			if (ed.getVL_Adiantamento1()==0 && ed.getVL_Adiantamento2()==0){
				map.put("viagem.parcela1.data", ed.getDT_Saldo());
			}else{
				map.put("viagem.parcela1.data", man.getDT_Emissao());
			}
			map.put("viagem.parcela1.favorecido.tipo.id",tp_favorecido_parcelas);
			//nr OF + (1-adto1,2-adto2 ou 3-saldo)
			map.put("viagem.parcela1.numero.cliente", String.valueOf(ed.getNR_Ordem_Frete()+"2"));

			if (ed.getVL_Saldo()>0){
				parcelas++;

				map.put("viagem.parcela"+parcelas+".efetivacao.tipo", "1");//1 - manual, 2 - Automática, 4 - Quintação
				map.put("viagem.parcela"+parcelas+".valor", String.valueOf(ed.getVL_Saldo()));
				map.put("viagem.parcela"+parcelas+".subtipo", "3"); //adto
				map.put("viagem.parcela"+parcelas+".base", "N");
				map.put("viagem.parcela"+parcelas+".status.id", "2");//1 - Pendente, 2 - Liberada, 3 - Bloqueada, 4 - Excluida
				map.put("viagem.parcela"+parcelas+".data", ed.getDT_Saldo());
				map.put("viagem.parcela"+parcelas+".favorecido.tipo.id", tp_favorecido_parcelas);
				//nr OF + (1-adto1,2-adto2 ou 3-saldo)
				map.put("viagem.parcela"+parcelas+".numero.cliente", String.valueOf(ed.getNR_Ordem_Frete()+"3"));
			}

			if(ed.getVL_Adiantamento1()>0){
				parcelas++;
				map.put("viagem.parcela"+parcelas+".efetivacao.tipo", "2");//1 - manual, 2 - Automática, 4 - Quintação
				map.put("viagem.parcela"+parcelas+".valor", String.valueOf(ed.getVL_Adiantamento1()));
				map.put("viagem.parcela"+parcelas+".subtipo", "1"); //adto
				map.put("viagem.parcela"+parcelas+".base", "N");
				map.put("viagem.parcela"+parcelas+".status.id", "2");//1 - Pendente, 2 - Liberada, 3 - Bloqueada, 4 - Excluida
				map.put("viagem.parcela"+parcelas+".data", ed.getDT_Adiantamento1());
				map.put("viagem.parcela"+parcelas+".favorecido.tipo.id", tp_favorecido_parcelas);
				//nr OF + (1-adto1,2-adto2 ou 3-saldo)
				map.put("viagem.parcela"+parcelas+".numero.cliente", String.valueOf(ed.getNR_Ordem_Frete()+"1"));
			}
			if(ed.getVL_Adiantamento2()>0){
				parcelas++;
				map.put("viagem.parcela"+parcelas+".efetivacao.tipo", "2");//1 - manual, 2 - Automática, 4 - Quintação
				map.put("viagem.parcela"+parcelas+".valor", String.valueOf(ed.getVL_Adiantamento2()));
				map.put("viagem.parcela"+parcelas+".subtipo", "2"); //intermediaria
				map.put("viagem.parcela"+parcelas+".base", "N");
				map.put("viagem.parcela"+parcelas+".status.id", "2");//1 - Pendente, 2 - Liberada, 3 - Bloqueada, 4 - Excluida
				map.put("viagem.parcela"+parcelas+".data", ed.getDT_Adiantamento2());
				map.put("viagem.parcela"+parcelas+".favorecido.tipo.id", tp_favorecido_parcelas);
				//nr OF + (1-adto1,2-adto2 ou 3-saldo)
				map.put("viagem.parcela"+parcelas+".numero.cliente", String.valueOf(ed.getNR_Ordem_Frete()+"4"));
			}
			map.put("viagem.parcela.qtde", String.valueOf(parcelas));

			String pgtos = "";
			CompromissoPesquisaED edComp = new CompromissoPesquisaED();
			edComp.setOID_Ordem_Frete(ed.getOID_Ordem_Frete());
			ArrayList cpgs = new CompromissoBD(this.sql).lista_Ordem(edComp);
			it = cpgs.iterator();
			while(it.hasNext()){
				CompromissoED cpg = (CompromissoED)it.next();
				pgtos += "Parc.:"+cpg.getNr_Parcela()+"-"+cpg.getNr_Compromisso()+" ";
			}

			//tarifas saque
			map.put("viagem.frete.item1.tipo", tpTarifaPamcary); //saque
			map.put("viagem.frete.item1.valor", vlTarifaPamcary); //padrao para quantidade 4
//			map.put("viagem.frete.item1.tarifa.quantidade", qtTarifaPamcary); //nao precisa ir se for padrao 4

			//tarifas transferência
			map.put("viagem.frete.item2.tipo", tpTarifaPamcary_Transferencia); //transferência
			map.put("viagem.frete.item2.valor", vlTarifaPamcary_Transferencia); //padrao para quantidade 4
//			map.put("viagem.frete.item2.tarifa.quantidade", qtTarifaPamcary_Transferencia); //nao precisa ir se for padrao 4

			int frt_item = 2;
			if(ed.getVL_IRRF()>0){
				frt_item++;
				map.put("viagem.frete.item"+frt_item+".tipo", "1");
				map.put("viagem.frete.item"+frt_item+".valor", String.valueOf(ed.getVL_IRRF()));
			}
			if(ed.getVL_INSS_Prestador()>0){
				frt_item++;
				map.put("viagem.frete.item"+frt_item+".tipo", "2");
				map.put("viagem.frete.item"+frt_item+".valor", String.valueOf(ed.getVL_INSS_Prestador()));
			}
			if(ed.getVL_Set_Senat()>0){
				frt_item++;
				map.put("viagem.frete.item"+frt_item+".tipo", "3");
				map.put("viagem.frete.item"+frt_item+".valor", String.valueOf(ed.getVL_Set_Senat()));
			}
			map.put("viagem.frete.item.qtde", String.valueOf(frt_item)); // aqui vai set/senat, irrf, inss

			map.put("viagem.quitacao.prazo", "1");
//			map.put("viagem.quitacao.indicador", "");
//			map.put("viagem.quitacao.entrega.ressalva", "");
//			map.put("viagem.origem.pagamento.quitacao", "");
			map.put("viagem.comprovacao.observacao", pgtos+docsOF);

			if ("A".equals(ed.getDM_Frete())){
				map.put("viagem.frete.valor.bruto", String.valueOf(ed.getVL_Ordem_Frete()));
			}else{
				map.put("viagem.frete.valor.bruto", String.valueOf(ed.getVL_Ordem_Frete()+ed.getVL_Descarga()));
			}


//			map.put("viagem.frete.item.qtde", "0"); // aqui vai set/senat, irrf, inss
//			map.put("viagem.frete.item1.tipo", "");
//			map.put("viagem.frete.item1.valor", "");

//			map.put("viagem.favorecido1.empresa.nome", "COOP RURAL VENETO");
//			map.put("viagem.favorecido1.empresa.cnpj", "00105016000169");
//			map.put("viagem.favorecido1.empresa.rntrc", "");

			//debug log
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey()+"="+entry.getValue());
			}

	  } catch(Excecoes e){
		  //ver erros...
		  e.printStackTrace();
		  throw e;
	  } catch(Exception e){
		  //ver erros...
		  e.printStackTrace();
		  throw new Excecoes(e.getMessage());
	  }
	  return map;
}

private Map montaDadosEncerramentoCFe(Ordem_FreteED ed) throws Excecoes{
	  Map<String, String> map = new HashMap<String, String>();
	  try {
		  map.put("transacional.operacao", "33");
			map.put("viagem.contratante.documento.numero", "87283164000151");
//			map.put("viagem.unidade.documento.tipo", "1");
//			map.put("viagem.unidade.documento.numero", "87283164000151");
			map.put("viagem.id.cliente", ManipulaString.corrigeNumero(ed.getOID_Ordem_Frete()));
			map.put("viagem.id", ed.getVIAGEMID());
			map.put("viagem.antt.ciot.numero", ed.getCIOT());
//			map.put("viagem.frete.valor.bruto", String.valueOf(ed.getVL_Ordem_Frete()));
//			map.put("viagem.frete.item.qtde", "0"); // aqui vai set/senat, irrf, inss
//			map.put("viagem.frete.item1.tipo", "");
//			map.put("viagem.frete.item1.valor", "");
//			map.put("viagem.carga.peso", String.valueOf(man.getNR_Total_Peso_CTRC());
	  } catch(Exception e){
		  //ver erros...
	  }
	  return map;
}

private Map montaDadosCancelamentoCFe(Ordem_FreteED ed) throws Excecoes{
	  Map<String, String> map = new HashMap<String, String>();
	  try {
		  map.put("transacional.operacao", "4");
			map.put("viagem.contratante.documento.numero", "87283164000151");
			map.put("viagem.id", ed.getVIAGEMID());
			map.put("viagem.antt.cancelamento.motivo", "contrato feito em ambiente teste");
//			debug log
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey()+"="+entry.getValue());
			}
	  } catch(Exception e){
		  //ver erros...
	  }
	  return map;
}

}
