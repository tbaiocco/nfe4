package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Produto_CustoRN;
import com.master.ed.Produto_CustoED;
import com.master.util.Excecoes;
import java.util.*;

public class Cad218Bean {

  public Produto_CustoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Produto_CustoRN Produto_CustoRN = new Produto_CustoRN();
      Produto_CustoED ed = new Produto_CustoED();

//// // System.out.println("Produto_Custo 11");
      ed.setCd_Produto_Custo(request.getParameter("FT_CD_Produto_Custo"));
//// // System.out.println("Produto_Custo 12");
      ed.setNm_Produto_Custo(request.getParameter("FT_NM_Produto_Custo"));
//// // System.out.println("Produto_Custo 13");
      ed.setOid_Unidade_Grupo(new Integer(request.getParameter("FT_OID_Unidade_Grupo")));
//// // System.out.println("Produto_Custo 14");

      return Produto_CustoRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Produto_CustoRN Produto_CustoRN = new Produto_CustoRN();
      Produto_CustoED ed = new Produto_CustoED();

      ed.setOid_Produto_Custo(new Integer(request.getParameter("oid_Produto_Custo")));
      ed.setCd_Produto_Custo(request.getParameter("FT_CD_Produto_Custo"));
      ed.setNm_Produto_Custo(request.getParameter("FT_NM_Produto_Custo"));
      ed.setOid_Unidade_Grupo(new Integer(request.getParameter("FT_OID_Unidade_Grupo")));
      Produto_CustoRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Produto_CustoRN Produto_Custorn = new Produto_CustoRN();
      Produto_CustoED ed = new Produto_CustoED();

      ed.setOid_Produto_Custo(new Integer(request.getParameter("oid_Produto_Custo")));

      Produto_Custorn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Produto_Custo_Lista(HttpServletRequest request)throws Excecoes{

      Produto_CustoED ed = new Produto_CustoED();

      String cd_Produto_Custo = request.getParameter("FT_CD_Produto_Custo");
      String oid_Unidade_Grupo = request.getParameter("FT_OID_Unidade_Grupo");
      String nm_Produto_Custo = request.getParameter("FT_NM_Produto_Custo");
      String oid_Unidade_Negocio = request.getParameter("oid_Unidade_Negocio");

      if (oid_Unidade_Negocio != null && !oid_Unidade_Negocio.equals(""))
        ed.setOID_Unidade_Negocio(oid_Unidade_Negocio);

      if (cd_Produto_Custo != null && !cd_Produto_Custo.equals(""))
        ed.setCd_Produto_Custo(cd_Produto_Custo);


      if (oid_Unidade_Grupo != null && !oid_Unidade_Grupo.equals(""))
        ed.setOid_Unidade_Grupo(new Integer(oid_Unidade_Grupo));

      if (nm_Produto_Custo != null && !nm_Produto_Custo.equals(""))
        ed.setNm_Produto_Custo(nm_Produto_Custo);

      return new Produto_CustoRN().lista(ed);

  }
 
  public Produto_CustoED getByRecord(HttpServletRequest request)throws Excecoes{

      Produto_CustoED ed = new Produto_CustoED();

      String oid_Produto_Custo = request.getParameter("oid_Produto_Custo");
      String cd_Produto_Custo = request.getParameter("FT_CD_Produto_Custo");
      String oid_Unidade_Negocio = request.getParameter("oid_Unidade_Negocio");

      if (oid_Unidade_Negocio != null && !oid_Unidade_Negocio.equals(""))
        ed.setOID_Unidade_Negocio(oid_Unidade_Negocio);

      if (oid_Produto_Custo != null && oid_Produto_Custo.length() > 0)
      {
        ed.setOid_Produto_Custo(new Integer(oid_Produto_Custo));
      }
      if (cd_Produto_Custo != null && !cd_Produto_Custo.equals("0")){
        ed.setCd_Produto_Custo(cd_Produto_Custo);
      }

     return new Produto_CustoRN().getByRecord(ed);

  }
  public Produto_CustoED getByCD(HttpServletRequest request)throws Excecoes{

      Produto_CustoED ed = new Produto_CustoED();

      String cd_Produto_Custo = request.getParameter("FT_CD_Produto_Custo");

       if (cd_Produto_Custo != null && !cd_Produto_Custo.equals("0")){
        ed.setCd_Produto_Custo(cd_Produto_Custo);
      }

     return new Produto_CustoRN().getByRecord(ed);

  }

    public Produto_CustoED getByOid(String Produto_Custo)throws Excecoes{

      Produto_CustoED ed = new Produto_CustoED();

      String oid_Produto_Custo = Produto_Custo;

      if (oid_Produto_Custo != null && oid_Produto_Custo.length() > 0)
      {
        ed.setOid_Produto_Custo(new Integer(oid_Produto_Custo));
      }

     return new Produto_CustoRN().getByRecord(ed);

  }


  public Produto_CustoED getByRecord_Credito(HttpServletRequest request)throws Excecoes{

      Produto_CustoED ed = new Produto_CustoED();

      String oid_Produto_Custo = request.getParameter("oid_Produto_Custo_Credito");
      String cd_Produto_Custo = request.getParameter("FT_CD_Produto_Custo_Credito");

      if (oid_Produto_Custo != null && oid_Produto_Custo.length() > 0)
      {
        ed.setOid_Produto_Custo(new Integer(oid_Produto_Custo));
      }
      if (cd_Produto_Custo != null && !cd_Produto_Custo.equals("0")){
        ed.setCd_Produto_Custo(cd_Produto_Custo);
      }

     return new Produto_CustoRN().getByRecord(ed);

  }
  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Produto_CustoED ed = new Produto_CustoED();

    Produto_CustoRN geRN = new Produto_CustoRN();
    geRN.geraRelatorio(ed);
  }


}
