package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Item_DerrubadaBD;
import com.master.ed.Item_DerrubadaED;
import com.master.rl.JasperRL;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class Item_DerrubadaRN extends Transacao {

	Item_DerrubadaBD  Item_DerrubadaBD = null;

    public Item_DerrubadaRN() {

    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Item_DerrubadaED inclui( Item_DerrubadaED ed) throws Excecoes {
    	Item_DerrubadaED  Item_DerrubadaED = new  Item_DerrubadaED();
        try {
            this.inicioTransacao();
            Item_DerrubadaBD = new  Item_DerrubadaBD(this.sql);
            Item_DerrubadaED = Item_DerrubadaBD.inclui(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }
        catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao incluir Item Derrubada",e,this.getClass().getName(),"inclui");
        }
        return  Item_DerrubadaED;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void altera( Item_DerrubadaED ed) throws Excecoes {
    	try {
            this.inicioTransacao();
            Item_DerrubadaBD = new  Item_DerrubadaBD(this.sql);
            Item_DerrubadaBD.altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao alterar dados de Item Derrubada",e,this.getClass().getName(),"altera");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void deleta( Item_DerrubadaED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            Item_DerrubadaBD = new  Item_DerrubadaBD(this.sql);
            Item_DerrubadaBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes("Erro ao excluir Item Derrubada",e,this.getClass().getName(),"deleta");
        }
    }

    /***************************************************************************
     *
     **************************************************************************/
    public ArrayList lista( Item_DerrubadaED ed) throws Excecoes {
        this.inicioTransacao();
        ArrayList lista = new  Item_DerrubadaBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Item_DerrubadaED getByRecord( Item_DerrubadaED ed) throws Excecoes {
    	this.inicioTransacao();
    	Item_DerrubadaED edVolta = new  Item_DerrubadaED();
        edVolta = new  Item_DerrubadaBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }


    //Modelo para Relatorio
    public void relItem_Derrubada(Item_DerrubadaED ed, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
    	try {
    		this.inicioTransacao();

    		//Relatorio
    		ArrayList toReport = new Item_DerrubadaBD(sql).lista(ed);
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

}