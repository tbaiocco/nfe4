/*
 * Created on 12/11/2004
 *
 */
package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Modelo_PneuED;
import com.master.rn.Modelo_PneuRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class Modelo_PneuBean
    extends JavaUtil implements Serializable {
  public static final String FIELD_OID_MODELO_PNEU = "oid_Modelo_Pneu";
  public static final String FIELD_CD_MODELO_PNEU = "FT_CD_Modelo_Pneu";
  public static final String FIELD_NM_MODELO_PNEU = "FT_NM_Modelo_Pneu";

  public Modelo_PneuED inclui (HttpServletRequest request) throws Excecoes {
    String cd_Modelo_Pneu = request.getParameter (FIELD_CD_MODELO_PNEU);
    String nm_Modelo_Pneu = request.getParameter (FIELD_NM_MODELO_PNEU);

    //Validações dos campos do request
    if (doValida (cd_Modelo_Pneu) && doValida (nm_Modelo_Pneu)) {
      Modelo_PneuED ed = new Modelo_PneuED (0 , cd_Modelo_Pneu , nm_Modelo_Pneu);
      //*** Valida se o registro já existe
       if (!doExiste (ed)) {
         return new Modelo_PneuRN ().inclui (ed);
       }
       else {
         throw new Mensagens ("Este modelo de pneus já existe!");
       }
    }
    else {
      throw new Mensagens ("Parâmetros inválidos!");
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {
    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);
    String cd_Modelo_Pneu = request.getParameter (FIELD_CD_MODELO_PNEU);
    String nm_Modelo_Pneu = request.getParameter (FIELD_NM_MODELO_PNEU);

    if (doValida (oid_Modelo_Pneu) && doValida (cd_Modelo_Pneu) && doValida (nm_Modelo_Pneu)) {
      Modelo_PneuED ed = new Modelo_PneuED (Integer.parseInt (oid_Modelo_Pneu) ,
                                            cd_Modelo_Pneu , nm_Modelo_Pneu);

      //Valida se é possível a atualização do registro
      new BancoUtil ().doValidaUpdate (ed.getOid_Modelo_Pneu () , ed.getCD_Modelo_Pneu () ,
                                       "Modelos_Pneus" , "oid_modelo_pneu" , "cd_modelo_pneu");
      new Modelo_PneuRN ().altera (ed);
    }
    else {
      throw new Mensagens ("Parâmetros inválidos");
    }
  }

  public void delete (HttpServletRequest request) throws Excecoes {
    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);

    if (doValida (oid_Modelo_Pneu)) {
      Modelo_PneuED ed = new Modelo_PneuED (Integer.parseInt (oid_Modelo_Pneu) , "" , "");
      new Modelo_PneuRN ().delete (ed);
    }
    else {
      throw new Mensagens ("Parâmetros inválidos");
    }
  }

  public ArrayList lista (HttpServletRequest request) throws Excecoes {
    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);
    String cd_Modelo_Pneu = request.getParameter (FIELD_CD_MODELO_PNEU);
    String nm_Modelo_Pneu = request.getParameter (FIELD_NM_MODELO_PNEU);
    if (oid_Modelo_Pneu == null) {
      oid_Modelo_Pneu = "0";
    }
    else if (oid_Modelo_Pneu.equals ("") || oid_Modelo_Pneu.equals ("null")) {
      oid_Modelo_Pneu = "0";
    }
    if (cd_Modelo_Pneu == null) {
      cd_Modelo_Pneu = "";
    }
    if (nm_Modelo_Pneu == null) {
      nm_Modelo_Pneu = "";
    }
    return new Modelo_PneuRN ().lista (new Modelo_PneuED (Integer.parseInt (oid_Modelo_Pneu) ,
                                                          cd_Modelo_Pneu , nm_Modelo_Pneu));
  }
  public Modelo_PneuED getByRecord (HttpServletRequest request) throws Excecoes {
	    String oid = request.getParameter (FIELD_OID_MODELO_PNEU);
	    if (doValida (oid)) {
	      return new Modelo_PneuRN ().getByRecord (Integer.parseInt (oid));
	    }
	    else {
	      throw new Mensagens ("Parâmetros inválidos!");
	    }
	  }

  public Modelo_PneuED getByCodigo (HttpServletRequest request) throws Excecoes {
    String codigo = request.getParameter (FIELD_CD_MODELO_PNEU);
    if (doValida (codigo)) {
      return new Modelo_PneuRN ().getByCodigo (codigo);
    }
    else {
      throw new Mensagens ("Parâmetros inválidos!");
    }
  }

  //*** Verifica se registro já existe!
   private boolean doExiste (Modelo_PneuED ed) throws Excecoes {

     //*** Validações
      String strFrom = "Modelos_Pneus";
     String strWhere = " cd_modelo_pneu = '" + ed.getCD_Modelo_Pneu () + "'";

     return new BancoUtil ().doExiste (strFrom , strWhere);
   }
}