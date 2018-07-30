package com.master.iu;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ReferenciaED;
import com.master.rn.ReferenciaRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;


public class cad024Bean
    extends JavaUtil implements Serializable {
  private static final int INCLUIR = 1;
  private static final int ALTERAR = 2;
  private static final int EXCLUIR = 3;
  private static final int LISTAR = 4;
  private static final int GETBYRECORD = 5;

  // Valida os campos informador no formulário html
  private ReferenciaED validaCampos (HttpServletRequest request , int metodo) throws Excecoes {
    ReferenciaED ed = new ReferenciaED ();
    String oid_Referencia = request.getParameter ("oid_Referencia");
    String oid_Produto = request.getParameter ("oid_Produto");
    String CD_Referencia = request.getParameter ("FT_CD_Referencia");
    String NM_Referencia = request.getParameter ("FT_NM_Referencia");
    String DM_Tipo_Produto = request.getParameter ("FT_DM_Tipo_Produto");
    String NM_Produto = request.getParameter ("FT_NM_Produto");

    if (doValida (oid_Referencia)) {
      ed.setOID_Referencia (Long.parseLong (oid_Referencia));
    }
    else {
      switch (metodo) {
        case ALTERAR:
        case EXCLUIR:
          throw new Excecoes ("Informe a referência!");
        default:
          break;
      }
    }
    if (doValida (oid_Produto)) {
      ed.setOID_Produto (Long.parseLong (oid_Produto));
    }
    else {
      switch (metodo) {
        case INCLUIR:
        case ALTERAR:
          throw new Excecoes ("Informe o produto!");
        default:
          break;
      }
    }
    if (doValida (CD_Referencia)) {
      ed.setCD_Referencia (CD_Referencia);
    }
    else {
      switch (metodo) {
        case INCLUIR:
        case ALTERAR:
          throw new Excecoes ("Informe código da referência!");
        default:
          break;
      }
    }
    if (doValida (NM_Referencia)) {
      ed.setNM_Referencia (NM_Referencia);
    }
    else {
      switch (metodo) {
        case INCLUIR:
        case ALTERAR:
          throw new Excecoes ("Informe o nome da referência!");
        default:
          break;
      }
    }
    if (doValida (DM_Tipo_Produto)) {
      ed.setDM_Tipo_Produto (DM_Tipo_Produto);
    }
    else {
      switch (metodo) {
        case INCLUIR:
        case ALTERAR:
          throw new Excecoes ("Informe o tipo do produto!");
        default:
          break;
      }
    }
    if (doValida (NM_Produto)) {
      ed.setNM_Produto (NM_Produto);
    }
    return ed;
  }

  public ReferenciaED inclui (HttpServletRequest request) throws Excecoes {
    ReferenciaED ed = validaCampos (request , INCLUIR);
    if (!doExiste (ed)) {
      return new ReferenciaRN ().inclui (ed);
    }
    else {
      throw new Excecoes ("Este código de referência já foi incluído!");
    }
  }

  public ReferenciaED inclui (ReferenciaED ed) throws Excecoes {
    return new ReferenciaRN ().inclui (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {
    ReferenciaED ed = validaCampos (request , ALTERAR);
    new BancoUtil ().doValidaUpdate (new Long (ed.getOID_Referencia ()).intValue () , ed.getCD_Referencia () , "Referencias" , "oid_Referencia" , "CD_Referencia");
    new ReferenciaRN ().altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {
    new ReferenciaRN ().deleta (validaCampos (request , EXCLUIR));
  }

  public List lista (HttpServletRequest request) throws Excecoes {
    return new ReferenciaRN ().lista (validaCampos (request , LISTAR));
  }

  public List listaReferencia (HttpServletRequest request) throws Excecoes {
    return new ReferenciaRN ().listaReferencia (validaCampos (request , LISTAR));
  }

  public ReferenciaED getByRecord (HttpServletRequest request) throws Excecoes {
    return new ReferenciaRN ().getByRecord (validaCampos (request , GETBYRECORD));
  }

  public ReferenciaED getByOID (int oid_Referencia) throws Excecoes {
    return new ReferenciaRN ().getByRecord (new ReferenciaED (oid_Referencia));
  }

  public void geraRelatorio (HttpServletRequest req) throws Excecoes {
    ReferenciaED ed = new ReferenciaED ();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
         /* SETAR O ED PARA PESQUISA NO BD
      */
     ReferenciaRN geRN = new ReferenciaRN ();
    geRN.geraRelatorio (ed);
  }

  //*** Verifica se registro já existe!
   public boolean doExiste (ReferenciaED ed) throws Excecoes {

     //*** Validações
      String strFrom = "Referencias";
     String strWhere = " CD_Referencia = '" + ed.getCD_Referencia () + "'";
     return new BancoUtil ().doExiste (strFrom , strWhere);
   }
}