package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Parcelamento_FinanceiroED;
import com.master.ed.UsuarioED;
import com.master.rn.Parcelamento_FinanceiroRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author Andre Valadas
 */
public class Ped006Bean extends JavaUtil{

    public void inclui(HttpServletRequest request)throws Excecoes {

    	Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();

    	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
    	String VL_Parcela = request.getParameter("FT_VL_Parcela");
    	String VL_Desconto = request.getParameter("FT_VL_Desconto");
    	String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
    	String NR_Parcelamento = request.getParameter("FT_NR_Parcelamento");
    	String DT_Pagamento = request.getParameter("FT_DT_Pagamento");
    	
    	if (doValida(oid_Nota_Fiscal))
    	    ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
    	else throw new Excecoes("Parâmetros incorretos!");
    	
    	if (doValida(VL_Parcela))
    	    ed.setVL_Parcela(Double.parseDouble(VL_Parcela));
    	if (doValida(VL_Desconto))
    	    ed.setVL_Desconto(Double.parseDouble(VL_Desconto));
    	if (doValida(DM_Tipo_Pagamento))
    	    ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
    	if (doValida(NR_Parcelamento))
    	    ed.setNR_Parcelamento(Long.parseLong(NR_Parcelamento));
    	if (doValida(DT_Pagamento))
    	    ed.setDT_Pagamento(DT_Pagamento);
    	else ed.setDT_Pagamento(Data.getDataDMY());
    	
    	new Parcelamento_FinanceiroRN().inclui(ed);
    }

    public void altera(HttpServletRequest request)throws Excecoes{

    	this.altera(request, false);
    }
    public void altera(HttpServletRequest request, boolean atualizaCompromisso)throws Excecoes{

        String oid_Parcelamento = request.getParameter("oid_Parcelamento");
        String VL_Parcela = request.getParameter("FT_VL_Parcela");
        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
        String DT_Pagamento = request.getParameter("FT_DT_Pagamento");
        UsuarioED edUser = (UsuarioED) request.getSession(true).getAttribute("usuario");

        if (!doValida(oid_Parcelamento))
            throw new Excecoes("ID Parcelamento não informado!");
        
        Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED(Long.parseLong(oid_Parcelamento));
        ed.setAtualizaCompromisso(atualizaCompromisso);
        //*** USUARIO
        ed.setUsuario_Stamp(edUser.getNm_Usuario());
        ed.setDt_stamp(edUser.getDt_stamp());
        ed.setDm_Stamp(edUser.getDM_Perfil());
        //****
        if (doValida(VL_Parcela))
            ed.setVL_Parcela(Double.parseDouble(VL_Parcela));
        if (doValida(VL_Desconto))
            ed.setVL_Desconto(Double.parseDouble(VL_Desconto));
        if (doValida(DM_Tipo_Pagamento))
            ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
        if (doValida(DT_Pagamento))
            ed.setDT_Pagamento(DT_Pagamento);
        else ed.setDT_Pagamento(Data.getDataDMY());

        new Parcelamento_FinanceiroRN().altera(ed);
    }
    
    public void deleta(HttpServletRequest request)throws Excecoes{

        String oid_Parcelamento = request.getParameter("oid_Parcelamento");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Parcelamento))
            throw new Excecoes("ID Parcelamento não informado!");
        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("ID Nota Fiscal não informado!");
        
        Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED(Long.parseLong(oid_Parcelamento));
        ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
        new Parcelamento_FinanceiroRN().deleta(ed);
    }
    
    public Parcelamento_FinanceiroED getByRecord(HttpServletRequest request)throws Excecoes{

        Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();

        String oid_Parcelamento = request.getParameter("oid_Parcelamento");
        if(doValida(oid_Parcelamento))
            ed.setOID_Parcelamento(Long.parseLong(oid_Parcelamento));
        else throw new Excecoes("Parâmetros incorretos!");
        
    	return new Parcelamento_FinanceiroRN().getByRecord(ed);
  	}

    public ArrayList Lista(HttpServletRequest request)throws Excecoes{

        Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();
        
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");      
        if (doValida(oid_Nota_Fiscal))
            ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
  		else throw new Excecoes("Parâmetros incorretos!");
        
  		return new Parcelamento_FinanceiroRN().lista(ed);
    }

    public double checkSoma(String nota)throws Excecoes{
        return new Parcelamento_FinanceiroRN().checkSoma(nota);
    }
    
    //*** Valor TOTAL das parcelas da Nota Fiscal
    public double vlTotalParcelas(String oid_Nota_Fiscal) throws Excecoes{
        if (doValida(oid_Nota_Fiscal)) {
            return new Parcelamento_FinanceiroRN().vlTotalParcelas(oid_Nota_Fiscal);
        } else throw new Excecoes("Parâmetros incorretos!");
    }
        
    public int getUltimoNRParc(String oid_Nota_Fiscal) throws Excecoes{
        return new BancoUtil().getMaximo("nr_parcelamento",
                                		 "parcelamentos_financeiros", 
                                		 "     oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'");
    }
}