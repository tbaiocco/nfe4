package com.master.iu;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Produto_VendedorED;
import com.master.rn.Produto_VendedorRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 25/10/2004
 */

/**
 * @author Andre Valadas
 */
public class Produto_VendedorBean extends JavaUtil implements Serializable {
    
    public static void main(String[] args) throws Excecoes {
        
        Produto_VendedorED ed = new Produto_VendedorED();
        //*** Geral
        ed.setOid_Pessoa("87226528000161");
		ed.setModulo(Produto_VendedorED.MODULO_PRODUTO);
		ed.setOperacao(Produto_VendedorED.OP_EXCLUIR);
		//*** Produto
		ed.setOid_Produto(7);
		ed.setPE_Comissao(33);
		//*** Mix Produto(oid_Mix) e Mix Vendedor
		ed.setOid_Mix(7);
		ed.setOid_Vendedor("00726775037");		
		
		/*Calendar cal = Calendar.getInstance(); 
		java.util.Date data = cal.getTime(); 

		// System.err.println(Calendar.getInstance().getTime());

		//convertendo a data 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String sData = formatter.format(data); 

		// System.err.println(sData);
		//agora vc precisa armazenar essa data num objeto java.sql.Date 
		java.sql.Date jdbcData = java.sql.Date.valueOf(sData);
		
		// System.err.println(jdbcData);*/
				
		new Produto_VendedorRN().GravaProdutos(ed);        
        
    }

	public Produto_VendedorED GravaProdutos(HttpServletRequest request, String Modulo, String Operacao) throws Excecoes {

		try {
			
			Produto_VendedorED ed = new Produto_VendedorED();

			//*** Geral
			String Oid_Pessoa = request.getParameter("oid_Pessoa");
			ed.setModulo(Modulo);
			ed.setOperacao(Operacao);
			//*** Produto
			String Oid_Produto = request.getParameter("oid_Produto");
			String PE_Comissao = request.getParameter("PE_Comissao");
			//*** Mix Produto(oid_Mix) e Mix Vendedor
			String Oid_Mix = request.getParameter("oid_Mix");
			String Oid_Vendedor = request.getParameter("oid_Vendedor");
			
			//*** Validações
			if ((Oid_Pessoa != null && !Oid_Pessoa.equals("0") && !Oid_Pessoa.equals("") && !Oid_Pessoa.equals("null")) && 
				(Oid_Vendedor != null && !Oid_Vendedor.equals("0")) && !Oid_Vendedor.equals("") && !Oid_Vendedor.equals("null")){				
				ed.setOid_Pessoa(Oid_Pessoa);
				ed.setOid_Vendedor(Oid_Vendedor);				
			}else // System.err.println("ERRO! Falta de parâmetros! [Produto_VendedorBean]");			
			ed.setOid_Produto(Integer.parseInt(Oid_Produto));
			ed.setPE_Comissao(Integer.parseInt(PE_Comissao));
			ed.setOid_Mix(Integer.parseInt(Oid_Mix));
						
			return new Produto_VendedorRN().GravaProdutos(ed);
		}
		catch (Excecoes exc) {
			throw exc;
		}
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		try {
			Produto_VendedorRN Produto_VendedorRN = new Produto_VendedorRN();
			Produto_VendedorED ed = new Produto_VendedorED();

			String oid_Produto_Vendedor = request.getParameter("oid_Produto_Vendedor");
			
			//*** Validações
			if (oid_Produto_Vendedor != null && !oid_Produto_Vendedor.equals("0") && 
				!oid_Produto_Vendedor.equals("") && !oid_Produto_Vendedor.equals("null")){
				
				ed.setOid_Produto_Vendedor(Integer.parseInt(oid_Produto_Vendedor));
				
			}else // System.err.println("ERRO! Parâmetros incorretos! [Produto_VendedorBean]");				

			Produto_VendedorRN.deleta(ed);
		} catch (Excecoes exc) {
			throw exc;
		}
	}
	
	//*** RELATÓRIOS
	// Produtos do Vendedor
    /*public void RelProduto_Vendedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String oid_Vendedor = request.getParameter("oid_Vendedor"); 
        String Layout = request.getParameter("FT_Layout");
        String Ordenar = request.getParameter("FT_Ordenar");
        
        new Produto_VendedorRN().RelProduto_Vendedor(response, oid_Vendedor, Layout, Ordenar);
        
    }*/
}