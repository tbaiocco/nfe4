package nfe.teste;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;

import java.lang.invoke.MethodHandles;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

/**
 * @author Samuel Oliveira
 */
public class EnvioNfeSincronoTeste {

    public static void main(String[] args) {

        try {

        	// Inicia As Certificado
            Certificado certificado = CertificadoService.certificadoPfx(
            		"/home/oem/eclipse-workspace/DOC-e/web/certificados/miro_cac.pfx", 
            		"1444");
            //Esse Objeto Voc� pode guardar em uma Session.
            ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(Estados.RS,
                    ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                    certificado,
                    MethodHandles.lookup().lookupClass().getResource("/schemas").getPath(), //PEGAR SCHEMAS EM AMBIENTE WEB ESTA PASTA ESTA DENTRO DE RESOURCES
                    false);

            TNFe nfe = new TNFe();
            TNFe.InfNFe infNFe = new InfNFe();

            infNFe.setId("xxx");
            infNFe.setVersao("4.00");

            // Dados Nfe
            Ide ide = new Ide();
            ide.setCUF("xxx");
            ide.setCNF("xxx");
            ide.setNatOp("xxx");
            ide.setMod("xxx");
            ide.setSerie("xxx");
            ide.setNNF("xxx");
            ide.setDhEmi("xxx");
            ide.setTpNF("xxx");
            ide.setIdDest("xxx");
            ide.setCMunFG("xxx");
            ide.setTpImp("xxx");
            ide.setTpEmis("xxx");
            ide.setCDV("xxx");
            ide.setTpAmb("xxx");
            ide.setFinNFe("xxx");
            ide.setIndFinal("xxx");
            ide.setIndPres("xxx");
            ide.setProcEmi("xxx");
            ide.setVerProc("xxx");
            infNFe.setIde(ide);

            //Emitente
            Emit emit = new Emit();
            emit.setCNPJ("xxx");
            emit.setXNome("xxx");
            emit.setXFant("xxx");
            TEnderEmi enderEmit = new TEnderEmi();
            enderEmit.setXLgr("xxx");
            enderEmit.setNro("xxx");
            enderEmit.setXCpl("xxx");
            enderEmit.setXBairro("xxx");
            enderEmit.setCMun("xxx");
            enderEmit.setXMun("xxx");
            enderEmit.setUF(TUfEmi.valueOf("xxx"));
            enderEmit.setCEP("xxx");
            enderEmit.setCPais("xxx");
            enderEmit.setXPais("xxx");
            enderEmit.setFone("xxx");
            emit.setEnderEmit(enderEmit);
            emit.setIE("xxx");
            emit.setCRT("xxx");
            infNFe.setEmit(emit);

            //Destinatario
            Dest dest = new Dest();
            dest.setCNPJ("xxx");
            dest.setXNome("xxx");
            TEndereco enderDest = new TEndereco();
            enderDest.setXLgr("xxx");
            enderDest.setNro("xxx");
            enderDest.setXBairro("xxx");
            enderDest.setCMun("xxx");
            enderDest.setXMun("xxx");
            enderDest.setUF(TUf.valueOf("xxx"));
            enderDest.setCEP("xxx");
            enderDest.setCPais("xxx");
            enderDest.setXPais("xxx");
            enderDest.setFone("xxx");
            dest.setEnderDest(enderDest);
            dest.setEmail("xxx");
            dest.setIndIEDest("xxx");
            infNFe.setDest(dest);
            
            Det det = new Det();
            det.setNItem("1");
            
            //Produto
            Prod prod = new Prod();
            prod.setCProd("xxx");
            prod.setCEAN("xxx");
            prod.setXProd("xxx");
            prod.setNCM("xxx");
            prod.setCEST("xxx");
            prod.setIndEscala("xxx");
            prod.setCFOP("xxx");
            prod.setUCom("xxx");
            prod.setQCom("xxx");
            prod.setVUnCom("xxx");
            prod.setVProd("xxx");
            prod.setCEANTrib("xxx");
            prod.setUTrib("xxx");
            prod.setQTrib("xxx");
            prod.setVUnTrib("xxx");
            prod.setIndTot("xxx");
            det.setProd(prod);

            //Impostos
            Imposto imposto = new Imposto();
            ICMS icms = new ICMS();
            ICMS60 icms60 = new ICMS60();
            icms60.setOrig("xxx");
            icms60.setCST("xxx");
            icms60.setVBCSTRet("xxx");
            icms60.setPST("xxx");
            icms60.setVICMSSTRet("xxx");
            icms.setICMS60(icms60);

            PIS pis = new PIS();
            PISAliq pisAliq = new PISAliq();
            pisAliq.setCST("xxx");
            pisAliq.setVBC("xxx");
            pisAliq.setPPIS("xxx");
            pisAliq.setVPIS("xxx");
            pis.setPISAliq(pisAliq);

            COFINS cofins = new COFINS();
            COFINSAliq cofinsAliq = new COFINSAliq();
            cofinsAliq.setCST("xxx");
            cofinsAliq.setVBC("xxx");
            cofinsAliq.setPCOFINS("xxx");
            cofinsAliq.setVCOFINS("xxx");
            cofins.setCOFINSAliq(cofinsAliq);

            JAXBElement<ICMS> icmsElement = new JAXBElement<ICMS>(new QName("ICMS"), ICMS.class, icms);
            imposto.getContent().add(icmsElement);

            JAXBElement<PIS> pisElement = new JAXBElement<PIS>(new QName("PIS"), PIS.class, pis);
            imposto.getContent().add(pisElement);

            JAXBElement<COFINS> cofinsElement = new JAXBElement<COFINS>(new QName("COFINS"), COFINS.class, cofins);
            imposto.getContent().add(cofinsElement);

            det.setImposto(imposto);
            infNFe.getDet().add(det);

            // TOTAIS
            Total total = new Total();

            ICMSTot icmstot = new ICMSTot();
            icmstot.setVBC("xxx");
            icmstot.setVICMS("xxx");
            icmstot.setVICMSDeson("xxx");
            icmstot.setVFCP("xxx");
            icmstot.setVFCPST("xxx");
            icmstot.setVFCPSTRet("xxx");
            icmstot.setVBCST("xxx");
            icmstot.setVST("xxx");
            icmstot.setVProd("xxx");
            icmstot.setVFrete("xxx");
            icmstot.setVSeg("xxx");
            icmstot.setVDesc("xxx");
            icmstot.setVII("xxx");
            icmstot.setVIPI("xxx");
            icmstot.setVIPIDevol("xxx");
            icmstot.setVPIS("xxx");
            icmstot.setVCOFINS("xxx");
            icmstot.setVOutro("xxx");
            icmstot.setVNF("xxx");
            total.setICMSTot(icmstot);
            infNFe.setTotal(total);

            Transp transp = new Transp();
            transp.setModFrete("xxx");
            infNFe.setTransp(transp);

            InfAdic infAdic = new InfAdic();
            infAdic.setInfCpl("xxx");
            infNFe.setInfAdic(infAdic);

            Pag pag = new Pag();
            Pag.DetPag detPag = new Pag.DetPag();
            detPag.setTPag("xxx");
            detPag.setVPag("xxx");
            pag.getDetPag().add(detPag);

            infNFe.setPag(pag);

            nfe.setInfNFe(infNFe);

            // Monta EnviNfe
            TEnviNFe enviNFe = new TEnviNFe();
            enviNFe.setVersao("4.00");
            enviNFe.setIdLote("1");
            enviNFe.setIndSinc("1");
            enviNFe.getNFe().add(nfe);

            // Monta e Assina o XML
            enviNFe = Nfe.montaNfe(enviNFe, true);

            // Envia a Nfe para a Sefaz
            TRetEnviNFe retorno = Nfe.enviarNfe(enviNFe, ConstantesUtil.NFE);

            if (!retorno.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
            }

            if (!retorno.getProtNFe().getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                throw new NfeException("Status:" + retorno.getProtNFe().getInfProt().getCStat() + " - Motivo:" + retorno.getProtNFe().getInfProt().getXMotivo());
            }

            System.out.println("Status:" + retorno.getProtNFe().getInfProt().getCStat());
            System.out.println("Motivo:" + retorno.getProtNFe().getInfProt().getXMotivo());
            System.out.println("Data:" + retorno.getProtNFe().getInfProt().getDhRecbto());
            System.out.println("Protocolo:" + retorno.getProtNFe().getInfProt().getNProt());

            System.out.println("Xml Final :" + XmlUtil.criaNfeProc(enviNFe, retorno.getProtNFe()));

        } catch (NfeException | JAXBException | CertificadoException e) {
            System.out.println("Erro:" + e.getMessage());
        }

    }
}