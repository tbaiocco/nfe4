package com.master.iu;

/**
* @author André Valadas
*/

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Validade_ProdutoED;
import com.master.rn.Validade_ProdutoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Pro006Bean extends JavaUtil{

	public Validade_ProdutoED inclui(HttpServletRequest request)throws Excecoes{

	    Validade_ProdutoED ed = new Validade_ProdutoED();

        String OID_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String OID_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String OID_Produto = request.getParameter("oid_Produto");
        String DT_Validade = request.getParameter("FT_DT_Validade");
  	    String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
  	    String NR_Lote = request.getParameter("FT_NR_Lote");
        
        if (doValida(OID_Produto_Cliente))
            ed.setOID_Produto_Cliente(OID_Produto_Cliente);       
        if (doValida(OID_Nota_Fiscal))
            ed.setOID_Nota_Fiscal(OID_Nota_Fiscal);
        if (doValida(OID_Produto))
            ed.setOID_Produto(OID_Produto);
        if (doValida(DT_Validade))        
            ed.setDT_Validade(DT_Validade);
        if (doValida(NR_Lote))
            ed.setNR_Lote(NR_Lote);
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Integer.parseInt(NR_Quantidade));
      
        return new Validade_ProdutoRN().inclui(ed);
	}

  	public void altera(HttpServletRequest request)throws Excecoes{

  	    Validade_ProdutoED ed = new Validade_ProdutoED();
  	    
  	    String oid_Validade_Produto = request.getParameter("oid_Validade_Produto");
  	    String DT_Validade = request.getParameter("FT_DT_Validade");
  	    String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
  	    String NR_Lote = request.getParameter("FT_NR_Lote");
  	    
        if (doValida(oid_Validade_Produto))
            ed.setOID_Validade_Produto(Long.parseLong(oid_Validade_Produto));
        else throw new Excecoes("Parâmetros incorretos!");

        if (doValida(DT_Validade))
            ed.setDT_Validade(DT_Validade);
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Integer.parseInt(NR_Quantidade));
        if (doValida(NR_Lote))
          	ed.setNR_Lote(NR_Lote);

        new Validade_ProdutoRN().altera(ed);
  	}

  	public void deleta(HttpServletRequest request)throws Excecoes{

  	    String oid_Validade_Produto = request.getParameter("oid_Validade_Produto");
  	    if (doValida(oid_Validade_Produto)) {
  	        new Validade_ProdutoRN().deleta(new Validade_ProdutoED(Long.parseLong(oid_Validade_Produto)));
  	    } else throw new Excecoes("Parâmetros incorretos!");        
  	}

  	public Validade_ProdutoED getByRecord(HttpServletRequest request)throws Excecoes{

  	    String oid_Validade_Produto = request.getParameter("oid_Validade_Produto");
	    if (doValida(oid_Validade_Produto)) {
	        return new Validade_ProdutoRN().getByRecord(new Validade_ProdutoED(Long.parseLong(oid_Validade_Produto)));
	    } else return null;	    
  	}

  	public ArrayList Validade_Produto_Lista(String oid_Produto_Cliente, String oid_Nota_Fiscal, String oid_Produto )throws Excecoes{

    	Validade_ProdutoED ed = new Validade_ProdutoED();

        if (doValida(oid_Produto_Cliente))
            ed.setOID_Produto_Cliente(oid_Produto_Cliente);
        if (doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (doValida(oid_Nota_Fiscal))
            ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);

        return new Validade_ProdutoRN().lista(ed);
  	}
}