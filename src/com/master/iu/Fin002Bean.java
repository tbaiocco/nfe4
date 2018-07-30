package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Centro_CustoED;
import com.master.rn.Centro_CustoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Fin002Bean
    extends JavaUtil {


  public Centro_CustoED inclui (HttpServletRequest request) throws Excecoes {

    String CD_Centro_Custo = request.getParameter ("FT_CD_Centro_Custo");
    String NM_Centro_Custo = request.getParameter ("FT_NM_Centro_Custo");

    if (!doValida (CD_Centro_Custo))
      throw new Mensagens ("Código Centro de Custo não informado!");
    if (!doValida (NM_Centro_Custo))
      throw new Mensagens ("Descrição Centro de Custo não informada!");

    if (new BancoUtil ().doExiste ("Centros_Custos" , "CD_Centro_Custo = '" + CD_Centro_Custo + "'"))
      throw new Mensagens ("Já existe um Centro de Custo com o código informado!");

    Centro_CustoED ed = new Centro_CustoED ();
    ed.setCd_Centro_Custo (CD_Centro_Custo);
    ed.setNm_Centro_Custo (NM_Centro_Custo);
    String DM_Aplicacao = request.getParameter ("FT_DM_Aplicacao");
    ed.setDM_Aplicacao (DM_Aplicacao);

    return new Centro_CustoRN ().inclui (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    String CD_Centro_Custo = request.getParameter ("FT_CD_Centro_Custo");
    String NM_Centro_Custo = request.getParameter ("FT_NM_Centro_Custo");

    if (!doValida (oid_Centro_Custo))
      throw new Mensagens ("ID Centro de Custo não informado!");
    if (!doValida (CD_Centro_Custo))
      throw new Mensagens ("Código Centro de Custo não informado!");
    if (!doValida (NM_Centro_Custo))
      throw new Mensagens ("Descrição Centro de Custo não informada!");

    new BancoUtil ().doValidaUpdate (Integer.parseInt (oid_Centro_Custo) , CD_Centro_Custo , "Centros_Custos" , "oid_Centro_Custo" , "CD_Centro_Custo");

    Centro_CustoED ed = new Centro_CustoED ();
    ed.setOid_Centro_Custo (new Integer (oid_Centro_Custo));
    ed.setCd_Centro_Custo (CD_Centro_Custo);
    ed.setNm_Centro_Custo (NM_Centro_Custo);
    String DM_Aplicacao = request.getParameter ("FT_DM_Aplicacao");
    ed.setDM_Aplicacao (DM_Aplicacao);

    new Centro_CustoRN ().altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    Centro_CustoED ed = new Centro_CustoED ();
    ed.setOid_Centro_Custo (new Integer (request.getParameter ("oid_Centro_Custo")));

    new Centro_CustoRN ().deleta (ed);
  }

  public ArrayList centro_Custo_Lista_Aplicacao (String dm_Aplicacao) throws Excecoes {

    Centro_CustoED ed = new Centro_CustoED ();

    if (doValida (dm_Aplicacao))
      ed.setDM_Aplicacao (dm_Aplicacao);

    return new Centro_CustoRN ().lista (ed);
  }


  public ArrayList centro_Custo_Lista (HttpServletRequest request) throws Excecoes {

    Centro_CustoED ed = new Centro_CustoED ();

    String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    String cd_Centro_Custo = request.getParameter ("FT_CD_Centro_Custo");
    String NM_Centro_Custo = request.getParameter ("FT_NM_Centro_Custo");

    if (doValida (oid_Centro_Custo))
      ed.setOid_Centro_Custo (new Integer (oid_Centro_Custo));
    if (doValida (cd_Centro_Custo))
      ed.setCd_Centro_Custo (cd_Centro_Custo);
    if (doValida (NM_Centro_Custo))
      ed.setNm_Centro_Custo (NM_Centro_Custo);

    return new Centro_CustoRN ().lista (ed);
  }

  public Centro_CustoED getByRecord (HttpServletRequest request) throws Excecoes {

    Centro_CustoED ed = new Centro_CustoED ();

    String oid_Centro_Custo = request.getParameter ("oid_Centro_Custo");
    if (oid_Centro_Custo != null && oid_Centro_Custo.length () > 0) {
      ed.setOid_Centro_Custo (new Integer (oid_Centro_Custo));
    }
    String CD_Centro_Custo = request.getParameter ("FT_CD_Centro_Custo");
    if (doValida (CD_Centro_Custo)) {
      ed.setCd_Centro_Custo (CD_Centro_Custo);
      //return new Centro_CustoRN().getByRecord(ed);
    }

    return new Centro_CustoRN ().getByRecord (ed);
  }

  public Centro_CustoED getByCD_Centro_Custo (HttpServletRequest request) throws Excecoes {

    Centro_CustoED ed = new Centro_CustoED ();

    String CD_Centro_Custo = request.getParameter ("FT_CD_Centro_Custo");
    if (doValida (CD_Centro_Custo)) {
      ed.setCd_Centro_Custo (CD_Centro_Custo);
      return new Centro_CustoRN ().getByRecord (ed);
    }
    return ed;
  }

  public Centro_CustoED getByOID_Centro_Custo (String oid_Centro_Custo) throws Excecoes {

    Centro_CustoED ed = new Centro_CustoED ();

    if (doValida (oid_Centro_Custo)) {
      ed.setOid_Centro_Custo (new Integer (oid_Centro_Custo));
      return new Centro_CustoRN ().getByRecord (ed);
    }
    return ed;
  }

  public void geraRelatorio (HttpServletRequest req) throws Excecoes {
    Centro_CustoED ed = new Centro_CustoED ();
    new Centro_CustoRN ().geraRelatorio (ed);
  }

}