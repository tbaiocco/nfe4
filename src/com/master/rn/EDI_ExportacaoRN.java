package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.EDI_ExportacaoBD;
import com.master.ed.EDI_ExportacaoED;
import com.master.ed.EDI_Nota_FiscalED;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.ManipulaArquivo2;
import com.master.util.ManipulaString;
import com.master.util.bd.Transacao;

public class EDI_ExportacaoRN extends Transacao  {
  EDI_ExportacaoBD EDI_ExportacaoBD = null;

  public EDI_ExportacaoRN() {
  }

  public ArrayList gera_EDI_Faturas(EDI_ExportacaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).gera_EDI_Faturas(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_FaturasIpiranga(EDI_ExportacaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).gera_EDI_FaturasIpiranga(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_Conhecimento(EDI_ExportacaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).gera_EDI_Conhecimento(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList exporta_Conhecimento(EDI_ExportacaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).exporta_Conhecimento(ed);
      this.fimTransacao(false);
      return lista;
  }


  public ArrayList gera_EDI_Ocorrencia(EDI_ExportacaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).gera_EDI_Ocorrencia(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_Nota_Fiscal(EDI_ExportacaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).gera_EDI_Nota_Fiscal(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_Nota_FiscalByManifesto(EDI_ExportacaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).gera_EDI_Nota_FiscalByManifesto(ed);
      this.fimTransacao(false);
      return lista;
  }

  public void exporta_Nota_Fiscal(EDI_ExportacaoED ed, HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_ExportacaoBD(sql).exporta_Nota_Fiscal_Astral(ed);
      this.fimTransacao(false);

      String Arquivo = "NF_ASTRAL_"+Data.getDataYMD()+".txt";
      ManipulaArquivo2 man = new ManipulaArquivo2("^",Arquivo);

      int n;
      if ((n = lista.size()) != 0)
      {
    	  for(int i=0; i<lista.size(); i++){
        	  EDI_Nota_FiscalED edVolta = new EDI_Nota_FiscalED();
        	  edVolta = (EDI_Nota_FiscalED)lista.get(i);

//        	  Registro 1
        	  man.insereValor("1");
        	  man.insereValor(edVolta.getCd_empresa());
        	  man.insereValor(edVolta.getCd_unidade());
        	  man.insereValor(edVolta.getNr_nota_fiscal());
        	  man.insereValor(edVolta.getNm_serie());
        	  man.insereValor("10005");
        	  man.insereValor("0");
        	  man.insereValor(ManipulaString.limpaCampo(edVolta.getDt_emissao()));
        	  man.insereValor(ManipulaString.limpaCampo(edVolta.getDt_recebimento()));
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor(ManipulaString.limpaCampo(edVolta.getDt_base()));
        	  man.insereValor("15");
        	  man.insereValor("3");
        	  man.insereValor("55");
        	  man.insereValor("RS");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor(ManipulaString.limpaCampo(edVolta.getDt_emissao()));
        	  man.insereValor("9007");
        	  man.insereValor(ManipulaString.limpaCampo(edVolta.getDt_emissao()));
        	  man.insereValor("1");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("10005");
        	  man.insereValor("15");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereCampo("");
        	  man.insereQuebra();

//        	  Registro 2
        	  man.insereValor("2");
        	  man.insereValor(edVolta.getCd_empresa());
        	  man.insereValor(edVolta.getCd_unidade());
        	  man.insereValor(edVolta.getNr_nota_fiscal());
        	  man.insereValor(edVolta.getNm_serie());
        	  man.insereValor("10005");
        	  man.insereValor("1113");
        	  man.insereValor("1");
        	  man.insereValor("1353");
        	  man.insereValor("900");
        	  man.insereValor(String.valueOf(edVolta.getVl_produtos()));
        	  man.insereValor(String.valueOf(edVolta.getVl_nota_fiscal()));
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("1");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereCampo("");
        	  man.insereQuebra();

//        	  Registro 3
        	  man.insereValor("3");
        	  man.insereValor(edVolta.getCd_empresa());
        	  man.insereValor(edVolta.getCd_unidade());
        	  man.insereValor(edVolta.getNr_nota_fiscal());
        	  man.insereValor(edVolta.getNm_serie());
        	  man.insereValor("10005");
        	  man.insereValor("1113");
        	  man.insereValor("1");
        	  man.insereValor("");
        	  man.insereValor("0");
        	  man.insereValor("0");
        	  man.insereValor("0");
        	  man.insereValor(String.valueOf(edVolta.getVl_nota_fiscal()));
        	  man.insereValor("0");
        	  man.insereValor("0");
        	  man.insereValor("0");
        	  man.insereCampo("0");
        	  man.insereQuebra();

//        	  Registro 4 - Itens
        	  ArrayList itens = new ArrayList(edVolta.getItens());
        	  for(int j=0; j<itens.size(); j++){
            	  Item_Nota_Fiscal_TransacoesED itemED = new Item_Nota_Fiscal_TransacoesED();
            	  itemED = (Item_Nota_Fiscal_TransacoesED)itens.get(j);
            	  man.insereValor("4");
            	  man.insereValor(edVolta.getCd_empresa());
            	  man.insereValor(edVolta.getCd_unidade());
            	  man.insereValor(edVolta.getNr_nota_fiscal());
            	  man.insereValor(edVolta.getNm_serie());
            	  man.insereValor("10005");
            	  man.insereValor("1");
            	  man.insereValor("1113");
            	  man.insereValor("1");
            	  man.insereValor("1");
            	  man.insereValor("");
            	  man.insereValor(edVolta.getNr_ordem_compra());
            	  man.insereValor("");
            	  man.insereValor("10607");
            	  man.insereValor("Un");
            	  man.insereValor("1");
            	  man.insereValor("Un");
            	  man.insereValor("1");
            	  man.insereValor("1");
            	  man.insereValor(String.valueOf(edVolta.getVl_nota_fiscal()));
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor(String.valueOf(edVolta.getVl_nota_fiscal()));
            	  man.insereValor("");
            	  man.insereValor("01010");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("0");
            	  man.insereValor("");
            	  man.insereValor("0");
            	  man.insereValor("0");
            	  man.insereValor("0");
            	  man.insereValor("0");
            	  man.insereValor("0");
            	  man.insereValor("0");
            	  man.insereValor("0");
            	  man.insereValor("0");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor(String.valueOf(edVolta.getVl_nota_fiscal()*0.0165));
            	  man.insereValor(String.valueOf(edVolta.getVl_nota_fiscal()*0.0760));
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor(String.valueOf(edVolta.getVl_nota_fiscal()));
            	  man.insereValor("1");
            	  man.insereValor("");
            	  man.insereValor("0");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("900");
            	  man.insereValor("1353");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereValor("");
            	  man.insereCampo("");
            	  man.insereQuebra();
        	  }

//        	  Registro 5
        	  man.insereValor("5");
        	  man.insereValor(edVolta.getCd_empresa());
        	  man.insereValor(edVolta.getCd_unidade());
        	  man.insereValor(edVolta.getNr_nota_fiscal());
        	  man.insereValor(edVolta.getNm_serie());
        	  man.insereValor("10005");
        	  man.insereValor(edVolta.getNr_nota_fiscal());
        	  man.insereValor("1");
        	  man.insereValor(String.valueOf(edVolta.getVl_titulo()));
        	  man.insereValor(ManipulaString.limpaCampo(edVolta.getDt_vencimento()));
        	  man.insereValor("0");
        	  man.insereValor("234");
        	  man.insereValor("900");
        	  man.insereValor("3");
        	  man.insereValor("");
        	  man.insereValor("2");
        	  man.insereValor("1113");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor(ManipulaString.limpaCampo(edVolta.getDt_aceite()));
        	  man.insereValor("");
        	  man.insereValor("15");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereValor("");
        	  man.insereCampo("");
        	  man.insereQuebra();
          }

    	  //envia arquivo para download
    	  man.geraArquivo(request, response, Arquivo);

    	  //TODO @Fazer metodo para marcar como EXPORTADA!
//    	  Nota_Fiscal_TransacoesRN nfrn = new Nota_Fiscal_TransacoesRN();
//    	  nfrn.exporta();

      } else throw new Excecoes("Não foram localizados dados para exportar!");
      //return lista;
  }

}
