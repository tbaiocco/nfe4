package nfe.teste;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;

import java.lang.invoke.MethodHandles;

/**
 * @author Samuel Oliveira
 */
public class StatusServicoWebTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Certificado
            Certificado certificado = CertificadoService.certificadoPfx(
            		"/home/oem/eclipse-workspace/DOC-e/web/certificados/miro_cac.pfx", 
            		"1444");
            //Esse Objeto Você pode guardar em uma Session.
            ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(Estados.RS,
                    ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                    certificado,
                    MethodHandles.lookup().lookupClass().getResource("/schemas").getPath(), //PEGAR SCHEMAS EM AMBIENTE WEB ESTA PASTA ESTA DENTRO DE RESOURCES
                    false);


            TRetConsStatServ retorno = NfeWeb.statusServico(config, ConstantesUtil.NFCE);
            System.out.println("Status:" + retorno.getCStat());
            System.out.println("Motivo:" + retorno.getXMotivo());
            System.out.println("Data:" + retorno.getDhRecbto());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}