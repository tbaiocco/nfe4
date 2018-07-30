/*
 * Created on 12/11/2004
 *
 */
package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Fabricante_PneuED;
import com.master.rn.Fabricante_PneuRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author Tiago
 *
 */
public class Fabricante_PneuBean
    extends JavaUtil implements Serializable {
  public static final String FIELD_OID_FABRICANTE_PNEU = "oid_Fabricante_Pneu";
  public static final String FIELD_CD_FABRICANTE_PNEU = "FT_CD_Fabricante_Pneu";
  public static final String FIELD_NM_FABRICANTE_PNEU = "FT_NM_Fabricante_Pneu";

  public Fabricante_PneuED inclui (HttpServletRequest request) throws Excecoes {
    String cd_Fabricante_Pneu = request.getParameter (FIELD_CD_FABRICANTE_PNEU);
    String nm_Fabricante_Pneu = request.getParameter (FIELD_NM_FABRICANTE_PNEU);

    //Validações dos campos do request
    if (doValida (cd_Fabricante_Pneu) && doValida (nm_Fabricante_Pneu)) {
      Fabricante_PneuED ed = new Fabricante_PneuED (0 , cd_Fabricante_Pneu , nm_Fabricante_Pneu);
      //*** Valida se o registro já existe
       if (!doExiste (ed)) {
         return new Fabricante_PneuRN ().inclui (ed);
       }
       else {
         throw new Mensagens ("Este fabricante de pneus já existe!");
       }
    }
    else {
      throw new Mensagens ("Parâmetros inválidos!");
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {
    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
    String cd_Fabricante_Pneu = request.getParameter (FIELD_CD_FABRICANTE_PNEU);
    String nm_Fabricante_Pneu = request.getParameter (FIELD_NM_FABRICANTE_PNEU);

    if (doValida (oid_Fabricante_Pneu) && doValida (cd_Fabricante_Pneu) && doValida (nm_Fabricante_Pneu)) {
      Fabricante_PneuED ed = new Fabricante_PneuED (Integer.parseInt (oid_Fabricante_Pneu) ,
                                                    cd_Fabricante_Pneu , nm_Fabricante_Pneu);

      //Valida se é possível a atualização do registro
      new BancoUtil ().doValidaUpdate (ed.getOid_Fabricante_Pneu () , ed.getCD_Fabricante_Pneu () ,
                                       "Fabricantes_Pneus" , "oid_fabricante_pneu" , "cd_fabricante_pneu");
      new Fabricante_PneuRN ().altera (ed);
    }
    else {
      throw new Mensagens ("Parâmetros inválidos");
    }
  }

  public void delete (HttpServletRequest request) throws Excecoes {
    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);

    if (doValida (oid_Fabricante_Pneu)) {
      Fabricante_PneuED ed = new Fabricante_PneuED (Integer.parseInt (oid_Fabricante_Pneu) , "" , "");
      new Fabricante_PneuRN ().delete (ed);
    }
    else {
      throw new Mensagens ("Parâmetros inválidos");
    }
  }

  public ArrayList lista (HttpServletRequest request) throws Excecoes {
    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
    String cd_Fabricante_Pneu = request.getParameter (FIELD_CD_FABRICANTE_PNEU);
    String nm_Fabricante_Pneu = request.getParameter (FIELD_NM_FABRICANTE_PNEU);
    if (oid_Fabricante_Pneu == null) {
      oid_Fabricante_Pneu = "0";
    }
    else if (oid_Fabricante_Pneu.equals ("") || oid_Fabricante_Pneu.equals ("null")) {
      oid_Fabricante_Pneu = "0";
    }
    if (cd_Fabricante_Pneu == null) {
      cd_Fabricante_Pneu = "";
    }
    if (nm_Fabricante_Pneu == null) {
      nm_Fabricante_Pneu = "";
    }
    return new Fabricante_PneuRN ().lista (new Fabricante_PneuED (Integer.parseInt (oid_Fabricante_Pneu) ,
                                                                  cd_Fabricante_Pneu , nm_Fabricante_Pneu));
  }

  public Fabricante_PneuED getByRecord (HttpServletRequest request) throws Excecoes {
    String oid = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
    if (doValida (oid)) {
      return new Fabricante_PneuRN ().getByRecord (Integer.parseInt (oid));
    }
    else {
      throw new Mensagens ("Parâmetros inválidos!");
    }
  }

  public Fabricante_PneuED getByCodigo (HttpServletRequest request) throws Excecoes {
    String codigo = request.getParameter (FIELD_CD_FABRICANTE_PNEU);
    if (doValida (codigo)) {
      return new Fabricante_PneuRN ().getByCodigo (codigo);
    }
    else {
      throw new Mensagens ("Parâmetros inválidos!");
    }
  }

  //*** Verifica se registro já existe!
   private boolean doExiste (Fabricante_PneuED ed) throws Excecoes {

     //*** Validações
      String strFrom = "Fabricantes_Pneus";
     String strWhere = " cd_fabricante_pneu = '" + ed.getCD_Fabricante_Pneu () + "'";

     return new BancoUtil ().doExiste (strFrom , strWhere);
   }
}