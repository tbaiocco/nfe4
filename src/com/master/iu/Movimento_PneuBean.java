package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Movimento_PneuED;
import com.master.rn.Movimento_PneuRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;

/**
 * @author André Valadas
 * - Movimento Pneus
 */
public class Movimento_PneuBean  {

  public ArrayList lista (HttpServletRequest request) throws Excecoes {

    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String oid_Pneu = request.getParameter ("oid_Pneu");
    Utilitaria util = new Utilitaria();

    Movimento_PneuED ed = new Movimento_PneuED ();
    if (util.doValida (oid_Veiculo))
      ed.setOid_Veiculo (oid_Veiculo);
    if (util.doValida (oid_Pneu))
      ed.setOid_Pneu (Integer.parseInt (oid_Pneu));

    return new Movimento_PneuRN ().lista (ed);
  }
  
  public ArrayList lista_nalthus (HttpServletRequest request) throws Excecoes {

	    String oid_Veiculo = request.getParameter ("oid_Veiculo");
	    String oid_Pneu = request.getParameter ("oid_Pneu");
	    Utilitaria util = new Utilitaria();

	    Movimento_PneuED ed = new Movimento_PneuED ();
	    if (util.doValida (oid_Veiculo))
	      ed.setOid_Veiculo (oid_Veiculo);
	    if (util.doValida (oid_Pneu))
	      ed.setOid_Pneu (Integer.parseInt (oid_Pneu));

	    return new Movimento_PneuRN ().lista_nalthus (ed);
	  }
  
}