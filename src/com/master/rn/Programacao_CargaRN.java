package com.master.rn;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Movimento_ContabilBD;
import com.master.bd.Pedido_CargaBD;
import com.master.bd.Programacao_CargaBD;
import com.master.ed.DiarioED;
import com.master.ed.Movimento_ContabilED;
import com.master.ed.Pedido_CargaED;
import com.master.ed.Programacao_CargaED;
import com.master.rl.JasperRL;
import com.master.root.CidadeBean;
import com.master.root.VeiculoBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class Programacao_CargaRN extends Transacao {

	Programacao_CargaBD  Programacao_CargaBD = null;

    public Programacao_CargaRN() {

    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Programacao_CargaED inclui( Programacao_CargaED ed) throws Excecoes {
    	Programacao_CargaED  Programacao_CargaED = new  Programacao_CargaED();
        try {
            this.inicioTransacao();
            Pedido_CargaED pedido = new Pedido_CargaED();
        	pedido.setOid_Pedido_Carga(ed.getOid_Pedido_Carga());
        	pedido = new Pedido_CargaBD(sql).getByRecord(pedido);
        	if(Data.comparaData(pedido.getDt_Pedido(), ed.getDt_Programacao()))
        		throw new Excecoes("A Data da Programação deve ser Superior à Data do Pedido!");
//        	if(Data.comparaData(ed.getDt_Programacao(),pedido.getDt_Prazo()))
//        		throw new Excecoes("A Data da Programação deve ser Inferior à Data do Prazo!");
//        	if(ed.getNr_Volumes()<=0.0)
//        		throw new Excecoes("O número de volumes deve ser superior à zero(0,00)!");
            Programacao_CargaBD = new  Programacao_CargaBD(this.sql);
            Programacao_CargaED =  Programacao_CargaBD.inclui(ed);
            if(JavaUtil.doValida(ed.getOid_Veiculo()))
            	Programacao_CargaBD.libera(Programacao_CargaED);
            this.fimTransacao(true);
        } catch (Excecoes e) {
        	this.abortaTransacao();
            throw e;
        }
        catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao incluir Programação Carga",e,this.getClass().getName(),"inclui");
        }
        return  Programacao_CargaED;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void altera( Programacao_CargaED ed) throws Excecoes {
    	try {
            this.inicioTransacao();
            Programacao_CargaBD = new  Programacao_CargaBD(this.sql);
            Programacao_CargaBD.altera(ed);
            if(JavaUtil.doValida(ed.getOid_Veiculo()))
            	Programacao_CargaBD.libera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
        	this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao alterar dados de Programação Carga",e,this.getClass().getName(),"altera");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void deleta( Programacao_CargaED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            Programacao_CargaBD = new  Programacao_CargaBD(this.sql);
            Programacao_CargaBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
        	this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao deletar Programação Carga",e,this.getClass().getName(),"deleta");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public ArrayList lista( Programacao_CargaED ed) throws Excecoes {
        this.inicioTransacao();
        ArrayList toReturn = new ArrayList();
        ArrayList lista = new  Programacao_CargaBD(sql).lista(ed);
        for (int i=0;i<lista.size();i++) {
        	Programacao_CargaED prog = (Programacao_CargaED)lista.get(i);
     	    prog = new Programacao_CargaBD(sql).getColeta(prog);
      	    prog = new Programacao_CargaBD(sql).getConhecimento(prog);
      	    prog = new Programacao_CargaBD(sql).getOrdemFrete(prog);
      	    toReturn.add(prog);
        }
        this.fimTransacao(false);
        return toReturn;
    }

    /***************************************************************************
    *
    **************************************************************************/
    public ArrayList planilha( Programacao_CargaED ed) throws Excecoes {
       this.inicioTransacao();
       ArrayList toReturn = new ArrayList();
       try{
    	   ArrayList lista = new Programacao_CargaBD(sql).lista(ed);
           for (int i=0;i<lista.size();i++) {
        	   Programacao_CargaED prog = (Programacao_CargaED)lista.get(i);
        	   prog = new Programacao_CargaBD(sql).getColeta(prog);
         	   prog = new Programacao_CargaBD(sql).getConhecimento(prog);
         	   prog = new Programacao_CargaBD(sql).getOrdemFrete(prog);

        	   //dados do pedido
        	   Pedido_CargaED pedido = new Pedido_CargaBD(sql).getByRecord(new Pedido_CargaED(prog.getOid_Pedido_Carga()));
        	   prog.setOid_Cliente(pedido.getOid_Cliente());
        	   prog.setNr_Cnpj_Cpf_Cliente(pedido.getNr_Cnpj_Cpf_Cliente());
        	   prog.setNm_Cliente(pedido.getNm_Cliente());

        	   prog.setOid_Pessoa_Origem(pedido.getOid_Pessoa_Origem());
         	   prog.setNr_Cnpj_Cpf_Origem(pedido.getNr_Cnpj_Cpf_Origem());
         	   prog.setNm_Pessoa_Origem(pedido.getNm_Pessoa_Origem());

    	       	prog.setOid_Pessoa_Pagador(pedido.getOid_Pessoa_Pagador());
    	       	prog.setNr_Cnpj_Cpf_Pagador(pedido.getNr_Cnpj_Cpf_Pagador());
    	       	prog.setNm_Pagador(pedido.getNm_Pagador());
    	       	prog.setTx_Descricao(pedido.getTx_Descricao());
    	       	prog.setOid_Cidade_Origem(pedido.getOid_Cidade_Origem());
    	       	prog.setCd_Origem(pedido.getCd_Origem());
    	       	//prog.setNm_Origem(pedido.getNm_Origem());
    	       	CidadeBean cidade = CidadeBean.getByOID(pedido.getOid_Cidade_Origem());
    	           prog.setNm_Origem(cidade.getNM_Cidade() + "/" + cidade.getCD_Estado());
    	       	prog.setOid_Cidade_Destino(pedido.getOid_Cidade_Destino());
    	       	prog.setCd_Destino(pedido.getCd_Destino());
    	       	//prog.setNm_Destino(pedido.getNm_Destino());
    	       	prog.setDm_Carga(pedido.getDm_carga());
    	       	prog.setDm_carga(pedido.getDm_carga());
    	       	cidade = CidadeBean.getByOID(pedido.getOid_Cidade_Destino());
    	           prog.setNm_Destino(cidade.getNM_Cidade() + "/" + cidade.getCD_Estado());

    	           String pgto = "";
    	           if(JavaUtil.doValida(pedido.getDm_Pagamento()))
    	        	   pgto = pedido.getDm_Pagamento();
    	           if(JavaUtil.doValida(pedido.getDm_Pagamento2()))
    	        	   pgto += ", " + pedido.getDm_Pagamento2();
    	           if(JavaUtil.doValida(pedido.getDm_Pagamento3()))
    	        	   pgto += ", " + pedido.getDm_Pagamento3();
    	           if(JavaUtil.doValida(pedido.getDm_Pagamento4()))
    	        	   pgto += ", " + pedido.getDm_Pagamento4();
    	           prog.setDm_Pagamento(pgto + " dias");

    	       	//valores
    	       	prog.setVl_Valor_Toco(pedido.getVl_Valor_Toco());
    	       	prog.setVl_Seguro_Toco(pedido.getVl_Seguro_Toco());
    	       	prog.setDm_ICMS_Toco(pedido.getDm_ICMS_Toco());
    	       	prog.setDm_Descarga_Toco(pedido.getDm_Descarga_Toco());
    	       	prog.setNr_Qtde_Toco(pedido.getNr_Qtde_Toco());
    	       	prog.setVl_Valor_Truck(pedido.getVl_Valor_Truck());
    	       	prog.setVl_Seguro_Truck(pedido.getVl_Seguro_Truck());
    	       	prog.setDm_ICMS_Truck(pedido.getDm_ICMS_Truck());
    	       	prog.setDm_Descarga_Truck(pedido.getDm_Descarga_Truck());
    	       	prog.setNr_Qtde_Truck(pedido.getNr_Qtde_Truck());
    	       	prog.setVl_Valor_Carreta(pedido.getVl_Valor_Carreta());
    	       	prog.setVl_Seguro_Carreta(pedido.getVl_Seguro_Carreta());
    	       	prog.setDm_ICMS_Carreta(pedido.getDm_ICMS_Carreta());
    	       	prog.setDm_Descarga_Carreta(pedido.getDm_Descarga_Carreta());
    	       	prog.setNr_Qtde_Carreta(pedido.getNr_Qtde_Carreta());
    	       	prog.setVl_Valor_Carreta15(pedido.getVl_Valor_Carreta15());
    	       	prog.setVl_Seguro_Carreta15(pedido.getVl_Seguro_Carreta15());
    	       	prog.setDm_ICMS_Carreta15(pedido.getDm_ICMS_Carreta15());
    	       	prog.setDm_Descarga_Carreta15(pedido.getDm_Descarga_Carreta15());
    	       	prog.setNr_Qtde_Carreta15(pedido.getNr_Qtde_Carreta15());
    	       	prog.setVl_Valor_Prancha(pedido.getVl_Valor_Prancha());
    	       	prog.setVl_Seguro_Prancha(pedido.getVl_Seguro_Prancha());
    	       	prog.setDm_ICMS_Prancha(pedido.getDm_ICMS_Prancha());
    	       	prog.setDm_Descarga_Prancha(pedido.getDm_Descarga_Prancha());
    	       	prog.setNr_Qtde_Prancha(pedido.getNr_Qtde_Prancha());
    	       	prog.setVl_Valor_Rebaixada(pedido.getVl_Valor_Rebaixada());
    	       	prog.setVl_Seguro_Rebaixada(pedido.getVl_Seguro_Rebaixada());
    	       	prog.setDm_ICMS_Rebaixada(pedido.getDm_ICMS_Rebaixada());
    	       	prog.setDm_Descarga_Rebaixada(pedido.getDm_Descarga_Rebaixada());
    	       	prog.setNr_Qtde_Rebaixada(pedido.getNr_Qtde_Rebaixada());
    	       	prog.setVl_Valor_Lagartixa(pedido.getVl_Valor_Lagartixa());
    	       	prog.setVl_Seguro_Lagartixa(pedido.getVl_Seguro_Lagartixa());
    	       	prog.setDm_ICMS_Lagartixa(pedido.getDm_ICMS_Lagartixa());
    	       	prog.setDm_Descarga_Lagartixa(pedido.getDm_Descarga_Lagartixa());
    	       	prog.setNr_Qtde_Lagartixa(pedido.getNr_Qtde_Lagartixa());
    	       	prog.setVl_Valor_Extensiva(pedido.getVl_Valor_Extensiva());
    	       	prog.setVl_Seguro_Extensiva(pedido.getVl_Seguro_Extensiva());
    	       	prog.setDm_ICMS_Extensiva(pedido.getDm_ICMS_Extensiva());
    	       	prog.setDm_Descarga_Extensiva(pedido.getDm_Descarga_Extensiva());
    	       	prog.setNr_Qtde_Extensiva(pedido.getNr_Qtde_Extensiva());
    	       	prog.setVl_Valor_Simples(pedido.getVl_Valor_Simples());
    	       	prog.setVl_Seguro_Simples(pedido.getVl_Seguro_Simples());
    	       	prog.setDm_ICMS_Simples(pedido.getDm_ICMS_Simples());
    	       	prog.setDm_Descarga_Simples(pedido.getDm_Descarga_Simples());
    	       	prog.setNr_Qtde_Simples(pedido.getNr_Qtde_Simples());
    	       	prog.setVl_Valor_Trucado(pedido.getVl_Valor_Trucado());
    	       	prog.setVl_Seguro_Trucado(pedido.getVl_Seguro_Trucado());
    	       	prog.setDm_ICMS_Trucado(pedido.getDm_ICMS_Trucado());
    	       	prog.setDm_Descarga_Trucado(pedido.getDm_Descarga_Trucado());
    	       	prog.setNr_Qtde_Trucado(pedido.getNr_Qtde_Trucado());
    	       	prog.setVl_Valor_Tracado(pedido.getVl_Valor_Tracado());
    	       	prog.setVl_Seguro_Tracado(pedido.getVl_Seguro_Tracado());
    	       	prog.setDm_ICMS_Tracado(pedido.getDm_ICMS_Tracado());
    	       	prog.setDm_Descarga_Tracado(pedido.getDm_Descarga_Tracado());
    	       	prog.setNr_Qtde_Tracado(pedido.getNr_Qtde_Tracado());
    	       	prog.setVl_Valor_Munck(pedido.getVl_Valor_Munck());
    	       	prog.setVl_Seguro_Munck(pedido.getVl_Seguro_Munck());
    	       	prog.setDm_ICMS_Munck(pedido.getDm_ICMS_Munck());
    	       	prog.setDm_Descarga_Munck(pedido.getDm_Descarga_Munck());
    	       	prog.setNr_Qtde_Munck(pedido.getNr_Qtde_Munck());
    	       	prog.setVl_Valor_Guincho(pedido.getVl_Valor_Guincho());
    	       	prog.setVl_Seguro_Guincho(pedido.getVl_Seguro_Guincho());
    	       	prog.setDm_ICMS_Guincho(pedido.getDm_ICMS_Guincho());
    	       	prog.setDm_Descarga_Guincho(pedido.getDm_Descarga_Guincho());
    	       	prog.setNr_Qtde_Guincho(pedido.getNr_Qtde_Guincho());
    	       	prog.setVl_Valor_Diversos(pedido.getVl_Valor_Diversos());
    	       	prog.setVl_Seguro_Diversos(pedido.getVl_Seguro_Diversos());
    	       	prog.setDm_ICMS_Diversos(pedido.getDm_ICMS_Diversos());
    	       	prog.setDm_Descarga_Diversos(pedido.getDm_Descarga_Diversos());
    	       	prog.setNr_Qtde_Diversos(pedido.getNr_Qtde_Diversos());

    	       	toReturn.add(prog);
           }
           //ORDENACAO
           if(!toReturn.isEmpty()){
        	   Collections.sort (toReturn , new Comparator () {
       	          public int compare (Object o1 , Object o2) {
       	        	Programacao_CargaED ed1 = (Programacao_CargaED) o1;
       	        	Programacao_CargaED ed2 = (Programacao_CargaED) o2;
       	        	if(JavaUtil.doValida(ed1.getNm_Pagador()) && JavaUtil.doValida(ed2.getNm_Pagador())){
       	        		return ed1.getNm_Pagador().compareTo(ed2.getNm_Pagador());
       	        	} else
       	        		return 0;
       	          }
       	        });
                Collections.sort (toReturn , new Comparator () {
      	          public int compare (Object o1 , Object o2) {
      	        	Programacao_CargaED ed1 = (Programacao_CargaED) o1;
      	        	Programacao_CargaED ed2 = (Programacao_CargaED) o2;
      	        	if(JavaUtil.doValida(ed1.getNm_Pessoa_Origem()) && JavaUtil.doValida(ed2.getNm_Pessoa_Origem())){
       	        		return ed1.getNm_Pessoa_Origem().compareTo(ed2.getNm_Pessoa_Origem());
       	        	} else
       	        		return 0;
      	          }
      	        });
                Collections.sort (toReturn , new Comparator () {
       	          public int compare (Object o1 , Object o2) {
       	        	Programacao_CargaED ed1 = (Programacao_CargaED) o1;
       	        	Programacao_CargaED ed2 = (Programacao_CargaED) o2;
       	        	if(JavaUtil.doValida(ed1.getDt_Saida()) && JavaUtil.doValida(ed2.getDt_Saida())){
       	        		return ed1.getDt_Saida().compareTo(ed2.getDt_Saida());
       	        	} else
       	        		return 0;
       	          }
       	        });
           }

       } catch (Excecoes exc) {
    	   this.abortaTransacao();
    	   throw exc;
       } catch (Exception e) {
    	   this.abortaTransacao();
    	   throw new Excecoes("Erro ao listar Programação Carga",e,this.getClass().getName(),"planilha");
       }

       this.fimTransacao(false);
       return toReturn;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Programacao_CargaED getByRecord( Programacao_CargaED ed) throws Excecoes {
    	this.inicioTransacao();
    	Programacao_CargaED prog = new  Programacao_CargaED();
        ArrayList lista = new  Programacao_CargaBD(this.sql).lista(ed);
        for (int i=0;i<lista.size();i++) {
        	prog = (Programacao_CargaED)lista.get(i);
     	    prog = new Programacao_CargaBD(sql).getColeta(prog);
      	    prog = new Programacao_CargaBD(sql).getConhecimento(prog);
      	    prog = new Programacao_CargaBD(sql).getOrdemFrete(prog);

        }
        this.fimTransacao(false);
        return prog;
    }

    /***************************************************************************
    *
    **************************************************************************/
    public void cancela( Programacao_CargaED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		Programacao_CargaBD = new  Programacao_CargaBD(this.sql);
    		Programacao_CargaBD.cancela(ed);
    		this.fimTransacao(true);
    	} catch (Excecoes exc) {
    		this.abortaTransacao();
    		throw exc;
    	} catch (Exception e) {
    		this.abortaTransacao();
    		throw new Excecoes("Erro ao cancelar Programação Carga",e,this.getClass().getName(),"cancela");
    	}
    }

    /***************************************************************************
    *
    **************************************************************************/
    public void vincula( Programacao_CargaED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		Programacao_CargaBD = new  Programacao_CargaBD(this.sql);
    		Programacao_CargaBD.vincula(ed);
    		this.fimTransacao(true);
    	} catch (Excecoes exc) {
    		this.abortaTransacao();
    		throw exc;
    	} catch (Exception e) {
    		this.abortaTransacao();
    		throw new Excecoes("Erro ao vincular Programação Carga",e,this.getClass().getName(),"vincula");
    	}
    }

    /***************************************************************************
    *
    **************************************************************************/
    public void libera( Programacao_CargaED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		Programacao_CargaBD = new  Programacao_CargaBD(this.sql);
    		Programacao_CargaBD.libera(ed);
    		this.fimTransacao(true);
    	} catch (Excecoes exc) {
    		this.abortaTransacao();
    		throw exc;
    	} catch (Exception e) {
    		this.abortaTransacao();
    		throw new Excecoes("Erro ao liberar Programação Carga",e,this.getClass().getName(),"libera");
    	}
    }

    /***************************************************************************
    *
    **************************************************************************/
    public void setDataCarga( Programacao_CargaED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		Programacao_CargaBD = new  Programacao_CargaBD(this.sql);
    		Programacao_CargaBD.setDataCarga(ed);
    		this.fimTransacao(true);
    	} catch (Excecoes exc) {
    		this.abortaTransacao();
    		throw exc;
    	} catch (Exception e) {
    		this.abortaTransacao();
    		throw new Excecoes("Erro ao acertar Data de Carga da Programação Carga",e,this.getClass().getName(),"setDataCarga");
    	}
    }

    public void relPedido( Programacao_CargaED ed, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
        try {
            this.inicioTransacao();

            ArrayList lista = new Programacao_CargaBD(sql).lista(ed);
            Pedido_CargaED pedido = new Pedido_CargaED();
        	pedido.setOid_Pedido_Carga(ed.getOid_Pedido_Carga());
        	pedido = new Pedido_CargaBD(sql).getByRecord(pedido);

            //Relatorio
            ArrayList toReport = new ArrayList();
            for (int i=0;i<lista.size();i++) {
            	Programacao_CargaED prog = (Programacao_CargaED)lista.get(i);

//            	Dados do Pedido
            	prog.setNm_Produto(pedido.getNm_Produto());
            	prog.setNm_Cliente(pedido.getNm_Cliente());
            	prog.setNm_Origem(pedido.getNm_Origem());
            	prog.setNm_Destino(pedido.getNm_Destino());
            	prog.setNr_Volumes_Tot(pedido.getNr_Volumes());
            	prog.setNr_Volumes_Canc(pedido.getNr_Volumes_Canc());
            	prog.setNr_Volumes_Efet(pedido.getNr_Volumes_Efet());
            	prog.setNr_Volumes_Prog(pedido.getNr_Volumes_Prog());
            	if(JavaUtil.doValida(pedido.getDm_Volumes())){
            		if(pedido.getDm_Volumes().equals("KG")){
            			prog.setDm_Volumes("Quilogramas");
            		} else if(pedido.getDm_Volumes().equals("LT")){
            			prog.setDm_Volumes("Litros");
            		} else if(pedido.getDm_Volumes().equals("M3")){
            			prog.setDm_Volumes("--- M3 ---");
            		} else prog.setDm_Volumes("N/A");
            	} else prog.setDm_Volumes("N/A");
            	prog.setDt_Pedido(pedido.getDt_Pedido());
            	prog.setDt_Prazo(pedido.getDt_Prazo());
            	prog.setVl_Tarifa(pedido.getVl_Tarifa());
            	prog.setVl_Tarifa_Bitrem(pedido.getVl_Tarifa_Bitrem());
            	prog.setVl_Pedagio(pedido.getVl_Pedagio());
            	prog.setVl_Pedagio_Bitrem(pedido.getVl_Pedagio_Bitrem());
            	prog.setNr_Placa(VeiculoBean.getByOID(prog.getOid_Veiculo()).getNR_Frota());

				toReport.add(prog);
			}

            ed.setLista(toReport);
			ed.setResponse(response);
			ed.setNomeRelatorio("Pedido_Carga"); // Seta o nome do relatório
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed

        } catch ( Exception e) {
            this.abortaTransacao();
            throw e;
        }
        finally {
            this.fimTransacao(false);
        }

	}

    public void relProgramacoes( Programacao_CargaED ed, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
        double nr_volumes_total = 0;
        double nr_volumes_ctrc = 0;
        double nr_volumes_cancelado = 0;

    	try {
            this.inicioTransacao();

            ArrayList lista = new Programacao_CargaBD(sql).listaToReport(ed);

            ed.setLista(lista);
            for (int i=0;i<lista.size();i++) {
            	Programacao_CargaED prog = (Programacao_CargaED)lista.get(i);
            	nr_volumes_total += prog.getNr_Volumes();
            	if(prog.getDm_Situacao().equals("C"))
            		nr_volumes_cancelado += prog.getNr_Volumes();
            	if(prog.getDm_Situacao().equals("V"))
            		nr_volumes_ctrc += prog.getNr_Volumes();
            }
            HashMap map = new HashMap();
            map.put("TOTAL", new Double(nr_volumes_total));
            map.put("CTRC", new Double(nr_volumes_ctrc));
            map.put("CANCELADO", new Double(nr_volumes_cancelado));
            ed.setHashMap(map);
            ed.setNr_Volumes_Tot(nr_volumes_total);
            ed.setNr_Volumes_Efet(nr_volumes_ctrc);
            ed.setNr_Volumes_Canc(nr_volumes_cancelado);
			ed.setResponse(response);
			ed.setNomeRelatorio("Programacao_Carga"); // Seta o nome do relatório
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed

        } catch ( Exception e) {
            this.abortaTransacao();
            throw e;
        }
        finally {
            this.fimTransacao(false);
        }

	}

    public void relProgramacoesCompleto( Programacao_CargaED ed, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
        double nr_volumes_total = 0;
        double nr_volumes_ctrc = 0;
        double nr_volumes_cancelado = 0;
        ArrayList toReport = new ArrayList();

    	try {
            this.inicioTransacao();

            ArrayList lista = new Programacao_CargaBD(sql).lista(ed);
            for (int i=0;i<lista.size();i++) {
            	Programacao_CargaED prog = (Programacao_CargaED)lista.get(i);
         	    prog = new Programacao_CargaBD(sql).getColeta(prog);
          	    prog = new Programacao_CargaBD(sql).getConhecimento(prog);
          	    prog = new Programacao_CargaBD(sql).getOrdemFrete(prog);

            	nr_volumes_total += prog.getNr_Volumes();
            	if(prog.getDm_Situacao().equals("C"))
            		nr_volumes_cancelado += prog.getNr_Volumes();
            	if(prog.getDm_Situacao().equals("V"))
            		nr_volumes_ctrc += prog.getNr_Volumes();

            	//dados do pedido
            	Pedido_CargaED pedido = new Pedido_CargaBD(sql).getByRecord(new Pedido_CargaED(prog.getOid_Pedido_Carga()));
            	prog.setOid_Cliente(pedido.getOid_Cliente());
            	prog.setNr_Cnpj_Cpf_Cliente(pedido.getNr_Cnpj_Cpf_Cliente());
            	prog.setNm_Cliente(pedido.getNm_Cliente());
            	prog.setOid_Pessoa_Pagador(pedido.getOid_Pessoa_Pagador());
            	prog.setNr_Cnpj_Cpf_Pagador(pedido.getNr_Cnpj_Cpf_Pagador());
            	prog.setNm_Pagador(pedido.getNm_Pagador());
            	prog.setTx_Descricao(pedido.getTx_Descricao());
            	prog.setOid_Cidade_Origem(pedido.getOid_Cidade_Origem());
            	prog.setCd_Origem(pedido.getCd_Origem());
            	//prog.setNm_Origem(pedido.getNm_Origem());
            	CidadeBean cidade = CidadeBean.getByOID(pedido.getOid_Cidade_Origem());
                prog.setNm_Origem(cidade.getNM_Cidade() + "/" + cidade.getCD_Estado());
            	prog.setOid_Cidade_Destino(pedido.getOid_Cidade_Destino());
            	prog.setCd_Destino(pedido.getCd_Destino());
            	//prog.setNm_Destino(pedido.getNm_Destino());
            	cidade = CidadeBean.getByOID(pedido.getOid_Cidade_Destino());
                prog.setNm_Destino(cidade.getNM_Cidade() + "/" + cidade.getCD_Estado());

                String pgto = "";
                if(JavaUtil.doValida(pedido.getDm_Pagamento()))
             	   pgto = pedido.getDm_Pagamento();
                if(JavaUtil.doValida(pedido.getDm_Pagamento2()))
             	   pgto += ", " + pedido.getDm_Pagamento2();
                if(JavaUtil.doValida(pedido.getDm_Pagamento3()))
             	   pgto += ", " + pedido.getDm_Pagamento3();
                if(JavaUtil.doValida(pedido.getDm_Pagamento4()))
             	   pgto += ", " + pedido.getDm_Pagamento4();
                prog.setDm_Pagamento(pgto + " dias");

            	//valores
            	prog.setVl_Valor_Toco(pedido.getVl_Valor_Toco());
            	prog.setVl_Seguro_Toco(pedido.getVl_Seguro_Toco());
            	prog.setDm_ICMS_Toco(pedido.getDm_ICMS_Toco());
            	prog.setDm_Descarga_Toco(pedido.getDm_Descarga_Toco());
            	prog.setNr_Qtde_Toco(pedido.getNr_Qtde_Toco());
            	prog.setVl_Valor_Truck(pedido.getVl_Valor_Truck());
            	prog.setVl_Seguro_Truck(pedido.getVl_Seguro_Truck());
            	prog.setDm_ICMS_Truck(pedido.getDm_ICMS_Truck());
            	prog.setDm_Descarga_Truck(pedido.getDm_Descarga_Truck());
            	prog.setNr_Qtde_Truck(pedido.getNr_Qtde_Truck());
            	prog.setVl_Valor_Carreta(pedido.getVl_Valor_Carreta());
            	prog.setVl_Seguro_Carreta(pedido.getVl_Seguro_Carreta());
            	prog.setDm_ICMS_Carreta(pedido.getDm_ICMS_Carreta());
            	prog.setDm_Descarga_Carreta(pedido.getDm_Descarga_Carreta());
            	prog.setNr_Qtde_Carreta(pedido.getNr_Qtde_Carreta());
            	prog.setVl_Valor_Carreta15(pedido.getVl_Valor_Carreta15());
            	prog.setVl_Seguro_Carreta15(pedido.getVl_Seguro_Carreta15());
            	prog.setDm_ICMS_Carreta15(pedido.getDm_ICMS_Carreta15());
            	prog.setDm_Descarga_Carreta15(pedido.getDm_Descarga_Carreta15());
            	prog.setNr_Qtde_Carreta15(pedido.getNr_Qtde_Carreta15());
            	prog.setVl_Valor_Prancha(pedido.getVl_Valor_Prancha());
            	prog.setVl_Seguro_Prancha(pedido.getVl_Seguro_Prancha());
            	prog.setDm_ICMS_Prancha(pedido.getDm_ICMS_Prancha());
            	prog.setDm_Descarga_Prancha(pedido.getDm_Descarga_Prancha());
            	prog.setNr_Qtde_Prancha(pedido.getNr_Qtde_Prancha());
            	prog.setVl_Valor_Rebaixada(pedido.getVl_Valor_Rebaixada());
            	prog.setVl_Seguro_Rebaixada(pedido.getVl_Seguro_Rebaixada());
            	prog.setDm_ICMS_Rebaixada(pedido.getDm_ICMS_Rebaixada());
            	prog.setDm_Descarga_Rebaixada(pedido.getDm_Descarga_Rebaixada());
            	prog.setNr_Qtde_Rebaixada(pedido.getNr_Qtde_Rebaixada());
            	prog.setVl_Valor_Lagartixa(pedido.getVl_Valor_Lagartixa());
            	prog.setVl_Seguro_Lagartixa(pedido.getVl_Seguro_Lagartixa());
            	prog.setDm_ICMS_Lagartixa(pedido.getDm_ICMS_Lagartixa());
            	prog.setDm_Descarga_Lagartixa(pedido.getDm_Descarga_Lagartixa());
            	prog.setNr_Qtde_Lagartixa(pedido.getNr_Qtde_Lagartixa());
            	prog.setVl_Valor_Extensiva(pedido.getVl_Valor_Extensiva());
            	prog.setVl_Seguro_Extensiva(pedido.getVl_Seguro_Extensiva());
            	prog.setDm_ICMS_Extensiva(pedido.getDm_ICMS_Extensiva());
            	prog.setDm_Descarga_Extensiva(pedido.getDm_Descarga_Extensiva());
            	prog.setNr_Qtde_Extensiva(pedido.getNr_Qtde_Extensiva());
            	prog.setVl_Valor_Simples(pedido.getVl_Valor_Simples());
            	prog.setVl_Seguro_Simples(pedido.getVl_Seguro_Simples());
            	prog.setDm_ICMS_Simples(pedido.getDm_ICMS_Simples());
            	prog.setDm_Descarga_Simples(pedido.getDm_Descarga_Simples());
            	prog.setNr_Qtde_Simples(pedido.getNr_Qtde_Simples());
            	prog.setVl_Valor_Trucado(pedido.getVl_Valor_Trucado());
            	prog.setVl_Seguro_Trucado(pedido.getVl_Seguro_Trucado());
            	prog.setDm_ICMS_Trucado(pedido.getDm_ICMS_Trucado());
            	prog.setDm_Descarga_Trucado(pedido.getDm_Descarga_Trucado());
            	prog.setNr_Qtde_Trucado(pedido.getNr_Qtde_Trucado());
            	prog.setVl_Valor_Tracado(pedido.getVl_Valor_Tracado());
            	prog.setVl_Seguro_Tracado(pedido.getVl_Seguro_Tracado());
            	prog.setDm_ICMS_Tracado(pedido.getDm_ICMS_Tracado());
            	prog.setDm_Descarga_Tracado(pedido.getDm_Descarga_Tracado());
            	prog.setNr_Qtde_Tracado(pedido.getNr_Qtde_Tracado());
            	prog.setVl_Valor_Munck(pedido.getVl_Valor_Munck());
            	prog.setVl_Seguro_Munck(pedido.getVl_Seguro_Munck());
            	prog.setDm_ICMS_Munck(pedido.getDm_ICMS_Munck());
            	prog.setDm_Descarga_Munck(pedido.getDm_Descarga_Munck());
            	prog.setNr_Qtde_Munck(pedido.getNr_Qtde_Munck());
            	prog.setVl_Valor_Guincho(pedido.getVl_Valor_Guincho());
            	prog.setVl_Seguro_Guincho(pedido.getVl_Seguro_Guincho());
            	prog.setDm_ICMS_Guincho(pedido.getDm_ICMS_Guincho());
            	prog.setDm_Descarga_Guincho(pedido.getDm_Descarga_Guincho());
            	prog.setNr_Qtde_Guincho(pedido.getNr_Qtde_Guincho());
            	prog.setVl_Valor_Diversos(pedido.getVl_Valor_Diversos());
            	prog.setVl_Seguro_Diversos(pedido.getVl_Seguro_Diversos());
            	prog.setDm_ICMS_Diversos(pedido.getDm_ICMS_Diversos());
            	prog.setDm_Descarga_Diversos(pedido.getDm_Descarga_Diversos());
            	prog.setNr_Qtde_Diversos(pedido.getNr_Qtde_Diversos());
            	//coloca um por linha
            	if(JavaUtil.doValida(prog.getDm_Veiculo())
            			&& (prog.getVl_Tarifa()<=0 || prog.getVl_Seguro()<=0
            			    || !JavaUtil.doValida(prog.getDm_ICMS()) || !JavaUtil.doValida(prog.getDm_Descarga()))
            	){
            		if(prog.getDm_Veiculo().equalsIgnoreCase("C")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Carreta());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Carreta());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Carreta());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Carreta());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("B")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Carreta());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Carreta());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Carreta());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Carreta());
            		}

            		else if(prog.getDm_Veiculo().equalsIgnoreCase("O")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Toco());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Toco());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Toco());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Toco());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("K")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Truck());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Truck());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Truck());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Truck());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("15")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Carreta15());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Carreta15());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Carreta15());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Carreta15());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("R")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Rebaixada());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Rebaixada());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Rebaixada());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Rebaixada());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("P")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Prancha());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Prancha());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Prancha());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Prancha());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("L")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Lagartixa());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Lagartixa());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Lagartixa());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Lagartixa());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("E")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Extensiva());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Extensiva());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Extensiva());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Extensiva());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("S")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Simples());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Simples());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Simples());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Simples());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("U")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Trucado());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Trucado());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Trucado());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Trucado());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("A")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Tracado());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Tracado());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Tracado());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Tracado());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("M")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Munck());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Munck());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Munck());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Munck());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("G")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Guincho());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Guincho());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Guincho());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Guincho());
            		}
            		else if(prog.getDm_Veiculo().equalsIgnoreCase("V")){
            			prog.setVl_Tarifa(pedido.getVl_Valor_Diversos());
            			prog.setVl_Seguro(pedido.getVl_Seguro_Diversos());
            			prog.setDm_ICMS(pedido.getDm_ICMS_Diversos());
            			prog.setDm_Descarga(pedido.getDm_Descarga_Diversos());
            		}
            	}

            	toReport.add(prog);
            }
            ed.setLista(toReport);
            HashMap map = new HashMap();
            map.put("TOTAL", new Double(nr_volumes_total));
            map.put("CTRC", new Double(nr_volumes_ctrc));
            map.put("CANCELADO", new Double(nr_volumes_cancelado));
            ed.setHashMap(map);
            ed.setNr_Volumes_Tot(nr_volumes_total);
            ed.setNr_Volumes_Efet(nr_volumes_ctrc);
            ed.setNr_Volumes_Canc(nr_volumes_cancelado);
			ed.setResponse(response);
			ed.setNomeRelatorio("Programacao_Carga_01"); // Seta o nome do relatório
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed

        } catch ( Exception e) {
            this.abortaTransacao();
            throw e;
        }
        finally {
            this.fimTransacao(false);
        }

	}

    public ArrayList getValoresPedido(Pedido_CargaED pedido) throws Excecoes{
    	Programacao_CargaED prog = new Programacao_CargaED();
    	ArrayList toReturn = new ArrayList();
    	long programado = 0;
    	try{
    		this.inicioTransacao();
    		if(pedido.getVl_Valor_Carreta()>0){
    			prog.setDm_Veiculo("C");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Carreta());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Carreta());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Carreta());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Carreta());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Carreta());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Toco()>0){
    			prog.setDm_Veiculo("O");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Toco());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Toco());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Toco());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Toco());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Toco());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Truck()>0){
    			prog.setDm_Veiculo("K");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Truck());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Truck());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Truck());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Truck());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Truck());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Carreta15()>0){
    			prog.setDm_Veiculo("15");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Carreta15());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Carreta15());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Carreta15());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Carreta15());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Carreta15());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Rebaixada()>0){
    			prog.setDm_Veiculo("R");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Rebaixada());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Rebaixada());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Rebaixada());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Rebaixada());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Rebaixada());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
//System.out.println("PRANCHA: " + pedido.getVl_Valor_Prancha());
    		if(pedido.getVl_Valor_Prancha()>0){
    			prog.setDm_Veiculo("P");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Prancha());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Prancha());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Prancha());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Prancha());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Prancha());
//System.out.println("prog: " + pedido.getNr_Qtde_Prancha());
    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
//System.out.println("programado: " + programado);
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Lagartixa()>0){
    			prog.setDm_Veiculo("L");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Lagartixa());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Lagartixa());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Lagartixa());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Lagartixa());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Lagartixa());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Extensiva()>0){
    			prog.setDm_Veiculo("E");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Extensiva());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Extensiva());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Extensiva());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Extensiva());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Extensiva());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Simples()>0){
    			prog.setDm_Veiculo("S");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Simples());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Simples());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Simples());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Simples());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Simples());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Trucado()>0){
    			prog.setDm_Veiculo("U");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Trucado());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Trucado());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Trucado());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Trucado());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Trucado());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Tracado()>0){
    			prog.setDm_Veiculo("A");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Tracado());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Tracado());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Tracado());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Tracado());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Tracado());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Munck()>0){
    			prog.setDm_Veiculo("M");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Munck());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Munck());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Munck());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Munck());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Munck());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Guincho()>0){
    			prog.setDm_Veiculo("G");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Guincho());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Guincho());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Guincho());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Guincho());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Guincho());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    		if(pedido.getVl_Valor_Diversos()>0){
    			prog.setDm_Veiculo("V");
    			prog.setVl_Tarifa(pedido.getVl_Valor_Diversos());
    			prog.setVl_Seguro(pedido.getVl_Seguro_Diversos());
    			prog.setDm_ICMS(pedido.getDm_ICMS_Diversos());
    			prog.setDm_Descarga(pedido.getDm_Descarga_Diversos());
    			prog.setNr_quantidade_consumo(pedido.getNr_Qtde_Diversos());

    			programado = new Programacao_CargaBD(sql).getQtdeProgramacoes(pedido.getOid_Pedido_Carga(), prog.getDm_Veiculo());
    			if(prog.getNr_quantidade_consumo()>programado || pedido.getDm_Tipo().equals("O")){
    				toReturn.add(prog);
    			}

    			prog = new Programacao_CargaED();
    			programado = 0;
    		}
    	} catch ( Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao buscar Programação Carga",e,this.getClass().getName(),"getQtdeProgramacoes()");
        }
        finally {
            this.fimTransacao(false);
        }

    	return toReturn;
    }

    public String getValueFromList(ArrayList list, String value_name, Programacao_CargaED edPage) throws Excecoes{
    	String toReturn = "";
    	Iterator it = list.iterator();
    	while(it.hasNext()){
    		Programacao_CargaED ed = (Programacao_CargaED)it.next();
    		if(ed.getDm_Veiculo().equals(edPage.getDm_Veiculo())){
    			String nome_campo = String.valueOf("get" + value_name.substring(0, 1).toUpperCase() + value_name.substring(1));
    			try{
//    				System.out.println(nome_campo);
    				Method metodo = ed.getClass().getMethod(String.valueOf(nome_campo), null);
//    				System.out.println(metodo.getName());
        			toReturn = String.valueOf((Object)metodo.invoke(ed, null));
    			} catch (Exception e){
    				e.printStackTrace();
    				throw new Excecoes("problemas no campo: " + String.valueOf(value_name.substring(0, 1).toUpperCase() + value_name.substring(1)));
    			}

    		}
    	}
    	return toReturn;
    }

}