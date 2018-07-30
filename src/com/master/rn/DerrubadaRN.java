package com.master.rn;

import java.io.File;
import java.io.LineNumberReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.DerrubadaBD;
import com.master.bd.Item_DerrubadaBD;
import com.master.ed.DerrubadaED;
import com.master.ed.Item_DerrubadaED;
import com.master.ed.ProdutoED;
import com.master.rl.JasperRL;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.Valores;
import com.master.util.bd.Transacao;

public class DerrubadaRN extends Transacao {

	DerrubadaBD  DerrubadaBD = null;

    public DerrubadaRN() {

    }

    /***************************************************************************
     *
     **************************************************************************/
    public  DerrubadaED inclui( DerrubadaED ed) throws Excecoes {
    	DerrubadaED  DerrubadaED = new  DerrubadaED();
        try {
            this.inicioTransacao();
            DerrubadaBD = new  DerrubadaBD(this.sql);
            DerrubadaED =  DerrubadaBD.inclui(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }
        catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao incluir Derrubada",e,this.getClass().getName(),"inclui");
        }
        return  DerrubadaED;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void altera( DerrubadaED ed) throws Excecoes {
    	try {
            this.inicioTransacao();
            DerrubadaBD = new  DerrubadaBD(this.sql);
            DerrubadaBD.altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao alterar dados de Derrubada",e,this.getClass().getName(),"altera");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void deleta( DerrubadaED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            DerrubadaBD = new  DerrubadaBD(this.sql);
            DerrubadaBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao excluir Derrubada",e,this.getClass().getName(),"deleta");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public ArrayList lista( DerrubadaED ed) throws Excecoes {
        this.inicioTransacao();
        ArrayList lista = new  DerrubadaBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public  DerrubadaED getByRecord( DerrubadaED ed) throws Excecoes {
    	this.inicioTransacao();
    	DerrubadaED edVolta = new  DerrubadaED();
        edVolta = new  DerrubadaBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    /***************************************************************************
    *
    **************************************************************************/
   public void Confirma_Derrubada( DerrubadaED ed) throws Excecoes {
       try {
           this.inicioTransacao();
           DerrubadaBD = new  DerrubadaBD(this.sql);
           DerrubadaBD.Confirma_Derrubada(ed);
           this.fimTransacao(true);
       } catch (Excecoes exc) {
           throw exc;
       } catch (Exception e) {
           this.abortaTransacao();
           throw new Excecoes("Erro ao confirmar Derrubada",e,this.getClass().getName(),"deleta");
       }
   }

   public void Cancela( DerrubadaED ed) throws Excecoes {
       try {
           this.inicioTransacao();
           DerrubadaBD = new  DerrubadaBD(this.sql);
           DerrubadaBD.Cancela(ed);
           this.fimTransacao(true);
       } catch (Excecoes exc) {
           throw exc;
       } catch (Exception e) {
           this.abortaTransacao();
           throw new Excecoes("Erro ao cancelar Derrubada",e,this.getClass().getName(),"deleta");
       }
   }

    //Modelo para Relatorio
    public void relDerrubada(DerrubadaED ed, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
    	try {
    		this.inicioTransacao();

    		//Relatorio
    		ed = new DerrubadaBD(sql).getByRecord(ed);
    		Item_DerrubadaED item = new Item_DerrubadaED();
    		item.setOid_derrubada(ed.getOid_derrubada());
    		ArrayList toReport = new ArrayList();
    		Iterator iterador = new Item_DerrubadaBD(sql).lista(item).iterator();
    		while(iterador.hasNext()){
    			item = (Item_DerrubadaED)iterador.next();
    			item.setNm_situacao(ed.getDescSituacao());
        		item.setCd_unidade(ed.getCd_unidade());
        		item.setNm_fantasia(ed.getNm_fantasia());
        		item.setDt_derrubada(ed.getDt_derrubada());
        		item.setHr_derrubada(ed.getHr_derrubada());
        		item.setNr_placa(ed.getNr_placa());
        		toReport.add(item);
    		}
    		item.setLista(toReport);
    		item.setResponse(response);
    		item.setNomeRelatorio("Derrubada_Conferencia"); // Seta o nome do relatório
    		new JasperRL(item).geraRelatorio(); // Chama o relatorio passando o ed

    	} catch ( Exception e) {
    		this.abortaTransacao();
    		throw e;
    	}
    	finally {
    		this.fimTransacao(false);
    	}
    }

    public String importaDaudt(DerrubadaED ed, String arquivo) throws Excecoes {
	    String NM_Registro = "";
	    int linha = 0;
	    String tipo = "";
	    String cd_produto = "";
	    String msg = "";

	    try {
//	    	// System.out.println("Chegou com...");
//	    	// System.out.println("Unidade..." + ed.getOid_unidade());
//	    	// System.out.println("Arquivo..." + arquivo);
	    	if(JavaUtil.doValida(arquivo) && new File(arquivo).canRead()){
	    		ManipulaArquivo man = new ManipulaArquivo ("");
	  	      	LineNumberReader line = man.leLinha (arquivo);
	  	      	if (line.ready ()) {
	  	      		while ( (NM_Registro = line.readLine ()) != null) {
	  	      			DerrubadaED importaED = new DerrubadaED();
	  	      			Item_DerrubadaED item_importaED = new Item_DerrubadaED();
	  	      			StringTokenizer stk = new StringTokenizer (NM_Registro , ";");
	  	      			if (stk.hasMoreTokens ()) {
	  	      				tipo = stk.nextToken();
	  	      			}
	  	      			if(tipo.equals("000")){
	  	      				importaED = new DerrubadaED();
	  	      				importaED.setOid_unidade(ed.getOid_unidade());
	  	      				if (stk.hasMoreTokens ()) {
	  	      					String nr_derrubada = stk.nextToken();
	  	      					if(JavaUtil.doValida(nr_derrubada))
	  	      						importaED.setOid_derrubada(new Long(nr_derrubada).longValue());
	  	      					else throw new Excecoes("Formato de arquivo incorreto!");
	  	      				}
		  	      			if (stk.hasMoreTokens ()) {
		  	      				importaED.setOid_veiculo(stk.nextToken());
		  	      			}
		  	      			if (stk.hasMoreTokens ()) {
		  	      				importaED.setDt_derrubada(stk.nextToken());
		  	      			}
		  	      			if (stk.hasMoreTokens ()) {
		  	      				importaED.setHr_derrubada(stk.nextToken());
		  	      			}
		  	      			this.inclui(importaED);
		  	      			msg += "\\nDerrubada nr. " + importaED.getOid_derrubada() + " inserida com sucesso!\\n";

	  	      			} else if(tipo.equals("001")){
	  	      				item_importaED = new Item_DerrubadaED();
		  	      			if (stk.hasMoreTokens ()) {
	  	      					String nr_derrubada = stk.nextToken();
	  	      					if(JavaUtil.doValida(nr_derrubada))
	  	      						item_importaED.setOid_derrubada(new Long(nr_derrubada).longValue());
	  	      					else throw new Excecoes("Formato de arquivo incorreto!");
	  	      				}
		  	      			if (stk.hasMoreTokens ()) {
		  	      				cd_produto = stk.nextToken();
		  	      				ProdutoED produto = new ProdutoED();
		  	      				produto.setCD_Produto(cd_produto);
		  	      				produto = new ProdutoRN().getByRecord(produto);
			  	      			if(JavaUtil.doValida(produto.getOID_Produto())){
			  	      				item_importaED.setOid_produto(new Long(produto.getOID_Produto()).intValue());
			  	      			}
		  	      			}
			  	      		if (stk.hasMoreTokens ()) {
		  	      				item_importaED.setDt_lote(stk.nextToken());
		  	      			}
		  	      			if (stk.hasMoreTokens ()) {
			  	      			String nr_qtde = stk.nextToken();
	  	      					if(JavaUtil.doValida(nr_qtde))
	  	      						item_importaED.setNr_quantidade(Valores.converteStringToDouble(nr_qtde));
	  	      					else throw new Excecoes("Formato de arquivo incorreto!");
		  	      			}
			  	      		if(!JavaUtil.doValida(String.valueOf(item_importaED.getOid_produto()))){
		  	      				msg += "Produto cd.:" + cd_produto + " não cadastrado... Não importado!\\n";
			  	      		} else {
			  	      			new Item_DerrubadaRN().inclui(item_importaED);
			  	      			msg += "Prod.:" + cd_produto + " ok.\\n";
			  	      		}
	  	      			}
	  	      			linha++;
	  	      			line.setLineNumber (linha);
	  	      		}
	  	      	}
	  	      	line.close ();
	    	} else {
	    		throw new Excecoes("Arquivo inexistente ou não pode ser lido!");
	    	}

	    	FileUtil.deleteFile(arquivo);
	    }
	    catch (Excecoes ex){
	    	ex.printStackTrace();
	    	throw ex;
	    }
	    catch (Exception exc) {
	    	exc.printStackTrace();
	    	throw new Excecoes("Erro ao ler arquivo...",exc,this.getClass().getName(),"importaDaudt");
	    }
	    return msg;
	  }

//    public void importaDaudt(DerrubadaED ed, String arquivo) throws Excecoes {
//        try {
//            this.inicioTransacao();
//            DerrubadaBD = new  DerrubadaBD(this.sql);
//            DerrubadaBD.importaDaudt(ed, arquivo);
//            this.fimTransacao(true);
//        } catch (Excecoes exc) {
//            throw exc;
//        } catch (Exception e) {
//            this.abortaTransacao();
//            throw new Excecoes("Erro ao importar Derrubadas",e,this.getClass().getName(),"importaDaudt");
//        }
//    }

}