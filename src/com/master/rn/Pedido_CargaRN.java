package com.master.rn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Pedido_CargaBD;
import com.master.bd.Programacao_CargaBD;
import com.master.bd.UsuarioBD;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.ed.Pedido_CargaED;
import com.master.ed.Programacao_CargaED;
import com.master.ed.UsuarioED;
import com.master.rl.JasperRL;
import com.master.root.CampoPadraoBean;
import com.master.root.CidadeBean;
import com.master.root.ContatoBean;
import com.master.root.PessoaBean;
import com.master.root.VeiculoBean;
import com.master.root.VendedorBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Extenso;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class Pedido_CargaRN extends Transacao {

	Pedido_CargaBD  Pedido_CargaBD = null;

    public Pedido_CargaRN() {

    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Pedido_CargaED inclui( Pedido_CargaED ed) throws Excecoes {
    	Pedido_CargaED  Pedido_CargaED = new  Pedido_CargaED();
        try {
            this.inicioTransacao();
            if(!JavaUtil.doValida(ed.getVl_Tarifa_TX()) && !JavaUtil.doValida(ed.getVl_Tarifa_Bitrem_TX()))
//            	throw new Excecoes("Deve-se preencher, ao menos, uma tarifa com valor superior à zero (0,00)!");
            Pedido_CargaED = new Pedido_CargaBD(this.sql).inclui(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }
        catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao incluir Pedido Carga",e,this.getClass().getName(),"inclui");
        }
        return  Pedido_CargaED;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void altera( Pedido_CargaED ed) throws Excecoes {
    	try {
            this.inicioTransacao();
            Pedido_CargaBD = new  Pedido_CargaBD(this.sql);
            Pedido_CargaBD.altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao alterar dados de Pedido Carga",e,this.getClass().getName(),"altera");
        }
    }

    public void efetivaPedido( Pedido_CargaED ed) throws Excecoes {
    	try {
            this.inicioTransacao();
            Pedido_CargaBD = new  Pedido_CargaBD(this.sql);
            Pedido_CargaBD.efetivaPedido(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao Efetivar Pedido Carga",e,this.getClass().getName(),"efetivaPedido");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void deleta( Pedido_CargaED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            Pedido_CargaBD = new  Pedido_CargaBD(this.sql);
            Pedido_CargaBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao deletar Pedido Carga",e,this.getClass().getName(),"deleta");
        }
    }

    public void cancela( Pedido_CargaED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            Programacao_CargaED prog = new Programacao_CargaED();
            prog.setOid_Pedido_Carga(ed.getOid_Pedido_Carga());
            new Pedido_CargaBD(this.sql).cancela(ed);
            new Programacao_CargaBD(this.sql).cancelaPedido(prog);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao deletar Pedido Carga",e,this.getClass().getName(),"deleta");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public ArrayList lista( Pedido_CargaED ed) throws Excecoes {
        this.inicioTransacao();
        ArrayList lista = new  Pedido_CargaBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Pedido_CargaED getByRecord( Pedido_CargaED ed) throws Excecoes {
    	this.inicioTransacao();
    	Pedido_CargaED edVolta = new  Pedido_CargaED();
        edVolta = new  Pedido_CargaBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    /***************************************************************************
    * TABELAS DE FRETE
    **************************************************************************/
   public ArrayList getTabelas(String oid_Pessoa, long oid_Produto, long oid_Origem, long oid_Destino) throws Excecoes {
       this.inicioTransacao();
       ArrayList lista = new  Pedido_CargaBD(sql).getTabelas(oid_Pessoa, oid_Produto, oid_Origem, oid_Destino);
       if(lista.size()==0)
    	   lista = new  Pedido_CargaBD(sql).getTabelas(oid_Pessoa, 0, oid_Origem, 0);
       if(lista.size()==0)
    	   lista = new  Pedido_CargaBD(sql).getTabelas(oid_Pessoa, 0, 0, 0);
       this.fimTransacao(false);
       return lista;
   }

   public void relTarifas(Pedido_CargaED ed, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
       try {
           this.inicioTransacao();

           //Relatorio
           ed.setDm_tipo_operacao("REPORT");
           ArrayList toReport = new Pedido_CargaBD(sql).lista(ed);
//           //Ordena por cliente
//           Collections.sort(toReport, new Comparator() {
//	            public int compare(Object o1, Object o2) {
//	            	Pedido_CargaED ed1 = (Pedido_CargaED)o1;
//	            	Pedido_CargaED ed2 = (Pedido_CargaED)o2;
//	                return ed1.getNm_Cliente().compareTo(ed2.getNm_Cliente());
//	            }
//	        });
//           //Ordena por data, decrescente
//           Collections.sort(toReport, new Comparator() {
//	            public int compare(Object o1, Object o2) {
//	            	Pedido_CargaED ed1 = (Pedido_CargaED)o1;
//	            	Pedido_CargaED ed2 = (Pedido_CargaED)o2;
//	            	Calendar c1 = null;
//	                Calendar c2 = null;
//	                try {
//	                  c1 = Data.stringToCalendar(ed1.getDt_Pedido() , "dd/MM/yyyy");
//	                  c2 = Data.stringToCalendar(ed2.getDt_Pedido() , "dd/MM/yyyy");
//	                }
//	                catch (Excecoes exc) {
//	                	//do nothing...
//	                	return 0;
//	                }
//	            	if (c1.before(c2)) {
//	            		return 1;
//	            	}
//	            	else if (c1.after(c2)) {
//	            		return -1;
//	            	}
//	            	else return 0;
//	            }
//	        });

           	ed.setLista(toReport);
			ed.setResponse(response);
			ed.setNomeRelatorio("Historico_Tarifas_Completo"); // Seta o nome do relatório
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed

       } catch ( Exception e) {
           this.abortaTransacao();
           throw e;
       }
       finally {
           this.fimTransacao(false);
       }
	}

   public void imprimePedido( Pedido_CargaED ed, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
       try {
           this.inicioTransacao();

           HashMap parametros = new HashMap();

           Pedido_CargaED pedido = new Pedido_CargaED();
           pedido = new Pedido_CargaBD(sql).getByRecord(ed);

           //campos padrão de Propostas
//           parametros.put("TEXTO1", CampoPadraoBean.getByCampo_Origem("1", "P").getTX_Descricao());
           parametros.put("TEXTO1", ed.getCampo_Livre1());
           parametros.put("TEXTO2", CampoPadraoBean.getByCampo_Origem("2", "P").getTX_Descricao());
           parametros.put("TEXTO3", CampoPadraoBean.getByCampo_Origem("3", "P").getTX_Descricao());
           parametros.put("TEXTO4", ed.getCampo_Livre2());
           //Assinatura do usuario da proposta
           //UsuarioED user = new UsuarioBD(sql).getByRecord(new UsuarioED(new Integer(pedido.getUser())));
//           PessoaBean pessoaUsuario = PessoaBean.getByOID(user.getOid_Pessoa());
           String assinatura = "";
           PessoaBean pessoaUsuario = PessoaBean.getByOID(pedido.getOid_Pessoa_Vendedor());
           String celular = VendedorBean.getByOID_Pessoa(pedido.getOid_Pessoa_Vendedor()).getNR_Celular();
           assinatura += pessoaUsuario.getNM_Razao_Social();
           assinatura += "\nwww.rodoviarionovaera.com.br";
           assinatura += "\n" + pessoaUsuario.getNR_Telefone() + " / " + (JavaUtil.doValida(celular)?celular:pessoaUsuario.getNR_Fax()) + " / Fax: 051 33 02 99 23";
           parametros.put("USUARIO", assinatura);

           parametros.put("ANO", Data.getDataDMY().substring(8));

           String dadosCliente = "";
           String dadosContato = "";

           PessoaBean cliente = PessoaBean.getByOID(pedido.getOid_Cliente());
           pedido.setNr_Cnpj_Cpf_Cliente(cliente.getNR_CNPJ_CPFMasc());
           dadosCliente += "\n" + cliente.getNM_Endereco();
           dadosCliente += "\n" + (!JavaUtil.doValida(cliente.getNM_Bairro())?"":cliente.getNM_Bairro());
           dadosCliente += "\n" + cliente.getNM_Cidade() + "/" + cliente.getCD_Estado();
           if(JavaUtil.doValida(cliente.getEMail()))
        	   dadosCliente += "\n" + cliente.getEMail();
           if(JavaUtil.doValida(cliente.getNR_Telefone()))
        	   dadosCliente += "\n" + cliente.getNR_Telefone();

//           Iterator contatos = ContatoBean.getByNR_CNPJ_CPF(cliente.getNR_CNPJ_CPF()).iterator();
//           if(contatos.hasNext()){
//        	   ContatoBean contato = (ContatoBean)contatos.next();
//        	   dadosContato += "\n" + contato.getNM_Contato();
//               if(JavaUtil.doValida(contato.getNM_Email()))
//            	   dadosContato += "\n" + contato.getNM_Email();
//               if(JavaUtil.doValida(contato.getNR_Telefone()))
//            	   dadosContato += "\n" + contato.getNR_Telefone();
//           }
           dadosContato += "\n" + (JavaUtil.doValida(pedido.getNm_Contato())?pedido.getNm_Contato():" ");
           dadosContato += "\n" + (JavaUtil.doValida(pedido.getNm_Email_Contato())?pedido.getNm_Email_Contato():" ");
           dadosContato += "\n" + (JavaUtil.doValida(pedido.getNm_Fone_Contato())?pedido.getNm_Fone_Contato():" ");

           pedido.setNm_razao_social(dadosCliente);
           pedido.setNm_contato(dadosContato);
           pedido.setDt_stamp("CANOAS, " + Data.getDataDMY().substring(0,2) + " de " + Data.getMesCorrente() + " de " + Data.getDataDMY().substring(6,10));

           StringBuffer valores = new StringBuffer();
           StringBuffer labels = new StringBuffer();
           if(pedido.getVl_Valor_Toco()>0){
        	   pedido.setVl_Valor_Toco(pedido.getVl_Valor_Toco()*(pedido.getNr_Qtde_Toco()==99?1:pedido.getNr_Qtde_Toco()));
        	   labels.append("Fracionado:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Toco() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Toco(), 'M').toLowerCase().toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Toco().equals("S") && pedido.getVl_Seguro_Toco()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Toco() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Toco()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Toco() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Toco().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Truck()>0){
        	   pedido.setVl_Valor_Truck(pedido.getVl_Valor_Truck()*(pedido.getNr_Qtde_Truck()==99?1:pedido.getNr_Qtde_Truck()));
        	   labels.append("Truck:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Truck() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Truck(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Truck().equals("S") && pedido.getVl_Seguro_Truck()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Truck() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Truck()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Truck() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Truck().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Carreta()>0){
        	   pedido.setVl_Valor_Carreta(pedido.getVl_Valor_Carreta()*(pedido.getNr_Qtde_Carreta()==99?1:pedido.getNr_Qtde_Carreta()));
        	   labels.append("Carreta 12:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Carreta() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Carreta(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Carreta().equals("S") && pedido.getVl_Seguro_Carreta()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Carreta() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Carreta()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Carreta() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Carreta().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Carreta15()>0){
        	   pedido.setVl_Valor_Carreta15(pedido.getVl_Valor_Carreta15()*(pedido.getNr_Qtde_Carreta15()==99?1:pedido.getNr_Qtde_Carreta15()));
        	   labels.append("Carreta 15:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Carreta15() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Carreta15(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Carreta15().equals("S") && pedido.getVl_Seguro_Carreta15()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Carreta15() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Carreta15()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Carreta15() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Carreta15().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Prancha()>0){
        	   pedido.setVl_Valor_Prancha(pedido.getVl_Valor_Prancha()*(pedido.getNr_Qtde_Prancha()==99?1:pedido.getNr_Qtde_Prancha()));
        	   labels.append("Prancha:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Prancha() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Prancha(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Prancha().equals("S") && pedido.getVl_Seguro_Prancha()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Prancha() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Prancha()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Prancha() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Prancha().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Rebaixada()>0){
        	   pedido.setVl_Valor_Rebaixada(pedido.getVl_Valor_Rebaixada()*(pedido.getNr_Qtde_Rebaixada()==99?1:pedido.getNr_Qtde_Rebaixada()));
        	   labels.append("Rebaixada:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Rebaixada() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Rebaixada(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Rebaixada().equals("S") && pedido.getVl_Seguro_Rebaixada()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Rebaixada() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Rebaixada()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Rebaixada() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Rebaixada().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Lagartixa()>0){
        	   pedido.setVl_Valor_Lagartixa(pedido.getVl_Valor_Lagartixa()*(pedido.getNr_Qtde_Lagartixa()==99?1:pedido.getNr_Qtde_Lagartixa()));
        	   labels.append("Lagartixa:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Lagartixa() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Lagartixa(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Lagartixa().equals("S") && pedido.getVl_Seguro_Lagartixa()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Lagartixa() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Lagartixa()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Lagartixa() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Lagartixa().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Extensiva()>0){
        	   pedido.setVl_Valor_Extensiva(pedido.getVl_Valor_Extensiva()*(pedido.getNr_Qtde_Extensiva()==99?1:pedido.getNr_Qtde_Extensiva()));
        	   labels.append("Extensiva:");
        	   labels.append("\n");
        	   valores.append("R$ " + pedido.getTx_Valor_Extensiva() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Extensiva(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Extensiva().equals("S") && pedido.getVl_Seguro_Extensiva()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Extensiva() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Extensiva()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Extensiva() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Extensiva().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
            	   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Simples()>0){
        	   pedido.setVl_Valor_Simples(pedido.getVl_Valor_Simples()*(pedido.getNr_Qtde_Simples()==99?1:pedido.getNr_Qtde_Simples()));
        	   labels.append("Cav. Mec. Simples:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Simples() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Simples(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Simples().equals("S") && pedido.getVl_Seguro_Simples()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Simples() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Simples()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Simples() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Simples().equals("S")){
            		   valores.append(". Valor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Tracado()>0){
        	   pedido.setVl_Valor_Tracado(pedido.getVl_Valor_Tracado()*(pedido.getNr_Qtde_Tracado()==99?1:pedido.getNr_Qtde_Tracado()));
        	   labels.append("Cav. Mec. Tracado:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Tracado() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Tracado(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Tracado().equals("S") && pedido.getVl_Seguro_Tracado()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Tracado() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Tracado()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Tracado() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Tracado().equals("S")){
            		   valores.append("\n\tValor a ser acrescido de ICMS.");
            		   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Trucado()>0){
        	   pedido.setVl_Valor_Trucado(pedido.getVl_Valor_Trucado()*(pedido.getNr_Qtde_Trucado()==99?1:pedido.getNr_Qtde_Trucado()));
        	   labels.append("Cav. Mec. Trucado:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Trucado() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Trucado(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Trucado().equals("S") && pedido.getVl_Seguro_Trucado()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Trucado() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Trucado()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Trucado() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Trucado().equals("S")){
        			   valores.append("\n\tValor a ser acrescido de ICMS.");
        			   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Munck()>0){
        	   pedido.setVl_Valor_Munck(pedido.getVl_Valor_Munck()*(pedido.getNr_Qtde_Munck()==99?1:pedido.getNr_Qtde_Munck()));
        	   labels.append("Truck c/ Munck:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Munck() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Munck(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Munck().equals("S") && pedido.getVl_Seguro_Munck()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Munck() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Munck()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Munck() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Munck().equals("S")){
        			   valores.append("\n\tValor a ser acrescido de ICMS.");
        			   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Guincho()>0){
        	   pedido.setVl_Valor_Guincho(pedido.getVl_Valor_Guincho()*(pedido.getNr_Qtde_Guincho()==99?1:pedido.getNr_Qtde_Guincho()));
        	   labels.append("Carreta c/ Guincho:");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Guincho() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Guincho(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Guincho().equals("S") && pedido.getVl_Seguro_Guincho()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Guincho() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Guincho()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Guincho() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Guincho().equals("S")){
        			   valores.append("\n\tValor a ser acrescido de ICMS.");
        			   labels.append("\n");
        		   }
        	   }

        	   valores.append("\n");

           }
           if(pedido.getVl_Valor_Diversos()>0){
        	   pedido.setVl_Valor_Diversos(pedido.getVl_Valor_Diversos()*(pedido.getNr_Qtde_Diversos()==99?1:pedido.getNr_Qtde_Diversos()));
        	   labels.append(" ");
        	   labels.append("\n");

        	   valores.append("R$ " + pedido.getTx_Valor_Diversos() + " (" + new Extenso().getExtenso(pedido.getVl_Valor_Diversos(), 'M').toLowerCase() + ")");
//        	 icms e seguro
        	   if(pedido.getDm_ICMS_Diversos().equals("S") && pedido.getVl_Seguro_Diversos()>0){
        		   valores.append("\n\tValor a ser acrescido de ICMS e Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Diversos() + "% sobre o valor da nota fiscal.");
        		   labels.append("\n");
        	   } else {
        		   if(pedido.getVl_Seguro_Diversos()>0){
            		   valores.append("\n\tValor a ser acrescido de Seguro Total dos equipamentos, " + pedido.getTx_Seguro_Diversos() + "% sobre o valor da nota fiscal.");
            		   labels.append("\n");
        		   } else if(pedido.getDm_ICMS_Diversos().equals("S")){
        			   valores.append("\n\tValor a ser acrescido de ICMS.");
        			   labels.append("\n");
        		   }
        	   }
        	   valores.append("\n");

           }

           parametros.put("VALORES", valores.toString());
           parametros.put("LABELS", labels.toString());

           //Cidades Origem e Destino
           CidadeBean cidade = CidadeBean.getByOID(pedido.getOid_Cidade_Origem());
           pedido.setNm_Origem(cidade.getNM_Cidade() + "/" + cidade.getCD_Estado());
           cidade = CidadeBean.getByOID(pedido.getOid_Cidade_Destino());
           pedido.setNm_Destino(cidade.getNM_Cidade() + "/" + cidade.getCD_Estado());

           cidade = CidadeBean.getByOID(pedido.getOid_Cidade_Entrega());
           pedido.setNm_Cidade_Entrega(cidade.getNM_Cidade() + "/" + cidade.getCD_Estado());

           String pgto = "";
           if(JavaUtil.doValida(pedido.getDm_Pagamento()))
        	   pgto = pedido.getDm_Pagamento();
           if(JavaUtil.doValida(pedido.getDm_Pagamento2()))
        	   pgto += ", " + pedido.getDm_Pagamento2();
           if(JavaUtil.doValida(pedido.getDm_Pagamento3()))
        	   pgto += ", " + pedido.getDm_Pagamento3();
           if(JavaUtil.doValida(pedido.getDm_Pagamento4()))
        	   pgto += ", " + pedido.getDm_Pagamento4();

           pedido.setDm_Pagamento(pgto + " dias");

           //Relatorio
           ArrayList toReport = new ArrayList();
           toReport.add(pedido);

           ed.setLista(toReport);
           ed.setResponse(response);
           ed.setHashMap(parametros);
           ed.setNomeRelatorio("Pedido_Carga_01"); // Seta o nome do relatório
           new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed

       } catch ( Exception e) {
           this.abortaTransacao();
           throw e;
       }
       finally {
           this.fimTransacao(false);
       }

	}

   public String ValidaQuantidades(Pedido_CargaED pedido){
	   String toReturn = "";

	   if(pedido.getVl_Valor_Toco()>0){
    	   if(pedido.getNr_Qtde_Toco()<=0){
    		   toReturn += "\'FRACIONADO: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Truck()>0){
    	   if(pedido.getNr_Qtde_Truck()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'TRUCK: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Carreta()>0){
    	   if(pedido.getNr_Qtde_Carreta()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'CARRETA 12: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Carreta15()>0){
    	   if(pedido.getNr_Qtde_Carreta15()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'CARRETA 15: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Prancha()>0){
    	   if(pedido.getNr_Qtde_Prancha()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'PRANCHA: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Rebaixada()>0){
    	   if(pedido.getNr_Qtde_Rebaixada()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'REBAIXADA: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Lagartixa()>0){
    	   if(pedido.getNr_Qtde_Lagartixa()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'LAGARTIXA: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Extensiva()>0){
    	   if(pedido.getNr_Qtde_Extensiva()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'EXTENSIVA: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Simples()>0){
    	   if(pedido.getNr_Qtde_Simples()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'CAVALO MEC. SIMPLES: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Tracado()>0){
    	   if(pedido.getNr_Qtde_Tracado()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'CAVALO MEC. TRAÇADO: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Trucado()>0){
    	   if(pedido.getNr_Qtde_Trucado()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'CAVALO MEC. TRUCADO: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Munck()>0){
    	   if(pedido.getNr_Qtde_Munck()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'TRUCK C/ MUNCK: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Guincho()>0){
    	   if(pedido.getNr_Qtde_Guincho()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'CARRETA C/ GUINCHO: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }
       if(pedido.getVl_Valor_Diversos()>0){
    	   if(pedido.getNr_Qtde_Diversos()<=0){
    		   if(JavaUtil.doValida(toReturn))
    			   toReturn += "+'\\n'+";
    		   toReturn += "\'VALOR TRANSPORTE: a QUANTIDADE deve ser preenchida se houver valor de transporte!\'";
    	   }
       }

	   return toReturn;
   }

}