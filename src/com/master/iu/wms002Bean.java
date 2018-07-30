package com.master.iu;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_DepositoED;
import com.master.rn.WMS_DepositoRN;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;


public class wms002Bean
    {
	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

  public WMS_DepositoED inclui (HttpServletRequest request) throws Excecoes {

    WMS_DepositoED ed = new WMS_DepositoED ();

    ed.setOid_Unidade (new Integer (request.getParameter ("oid_Unidade")));
    ed.setCd_Deposito (request.getParameter ("FT_CD_Deposito"));
    ed.setNm_Deposito (request.getParameter ("FT_NM_Deposito"));
    ed.setNM_Senha (request.getParameter ("FT_NM_Senha"));
    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));
    ed.setCD_Local_Picking (request.getParameter ("FT_CD_Local_Picking"));
    ed.setCD_Local_Carga (request.getParameter ("FT_CD_Local_Carga"));
    ed.setCD_Local_Entrada (request.getParameter ("FT_CD_Local_Entrada"));
    ed.setCD_Local_Saida (request.getParameter ("FT_CD_Local_Saida"));
    ed.setCD_Local_Descarga (request.getParameter ("FT_CD_Local_Descarga"));
    ed.setOid_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

    if (ed.getOid_Unidade () == null || ed.getOid_Unidade ().intValue () < 1) {
      throw new Mensagens ("Unidade não informada!");
    }
    if (!util.doValida (ed.getCd_Deposito ())) {
      throw new Mensagens ("Código do Depósito não informado!");
    }
    if (!util.doValida (ed.getNm_Deposito ())) {
      throw new Mensagens ("Descrição do Depósito não informada!");
    }

    //*** Valida se Existe
     if (util.doExiste ("Depositos" , "oid_Unidade = " + ed.getOid_Unidade () + " AND CD_Deposito = '" + ed.getCd_Deposito () + "'")) {
       throw new Mensagens ("Esse Depósito já Existe!");
     }

    return new WMS_DepositoRN ().inclui (ed);

  }

  public void altera (HttpServletRequest request) throws Excecoes {

    WMS_DepositoED ed = new WMS_DepositoED ();

    ed.setOid_Deposito (new Integer (request.getParameter ("oid_Deposito")));
    ed.setCd_Deposito (request.getParameter ("FT_CD_Deposito"));
    ed.setNm_Deposito (request.getParameter ("FT_NM_Deposito"));
    ed.setNM_Senha (request.getParameter ("FT_NM_Senha"));
    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));
    ed.setOid_Unidade (new Integer (request.getParameter ("oid_Unidade")));

    ed.setCD_Local_Picking (request.getParameter ("FT_CD_Local_Picking"));
    ed.setCD_Local_Carga (request.getParameter ("FT_CD_Local_Carga"));
    ed.setCD_Local_Entrada (request.getParameter ("FT_CD_Local_Entrada"));
    ed.setCD_Local_Saida (request.getParameter ("FT_CD_Local_Saida"));
    ed.setCD_Local_Descarga (request.getParameter ("FT_CD_Local_Descarga"));

    if (ed.getOid_Deposito () == null || ed.getOid_Deposito ().intValue () < 1) {
      throw new Mensagens ("Deposito não informado!");
    }
    if (ed.getOid_Unidade () == null || ed.getOid_Unidade ().intValue () < 1) {
      throw new Mensagens ("Unidade não informada!");
    }
    if (!util.doValida (ed.getCd_Deposito ())) {
      throw new Mensagens ("Código do Depósito não informado!");
    }
    if (!util.doValida (ed.getNm_Deposito ())) {
      throw new Mensagens ("Descrição do Depósito não informada!");
    }

    //*** Valida se Existe
     if (util.doExiste ("Depositos" , "oid_Unidade = " + ed.getOid_Unidade () + " AND CD_Deposito = '" + ed.getCd_Deposito () + "' AND oid_Deposito <> " + ed.getOid_Deposito ())) {
       throw new Mensagens ("Já Existe um Depósito com esse Código e Unidade!");
     }

    new WMS_DepositoRN ().altera (ed);

  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    if (!util.doValida (request.getParameter ("oid_Deposito"))) {
      throw new Mensagens ("ID Deposito não informado!");
    }

    WMS_DepositoED ed = new WMS_DepositoED ();
    ed.setOid_Deposito (new Integer (request.getParameter ("oid_Deposito")));

    new WMS_DepositoRN ().deleta (ed);

  }

  public ArrayList Deposito_Lista (HttpServletRequest request) throws Excecoes {

    WMS_DepositoED ed = new WMS_DepositoED ();

    String oid_Deposito = request.getParameter ("oid_Deposito");
    String CD_Deposito = request.getParameter ("FT_CD_Deposito");
    String nm_Deposito = request.getParameter ("FT_NM_Deposito");

    if (util.doValida (oid_Deposito)) {
      ed.setOid_Deposito (new Integer (oid_Deposito));
    }
    if (util.doValida (CD_Deposito)) {
      ed.setCd_Deposito (CD_Deposito);
    }
    if (util.doValida (nm_Deposito)) {
      ed.setNm_Deposito (nm_Deposito);
    }

    return new WMS_DepositoRN ().lista (ed);
  }

  public WMS_DepositoED getByRecord (HttpServletRequest request) throws Excecoes {

    WMS_DepositoED ed = new WMS_DepositoED ();

    String oid_Deposito = request.getParameter ("oid_Deposito");
    if (!util.doValida (oid_Deposito)) {
      throw new Mensagens ("ID Deposito não informado!");
    }


    ed.setOid_Deposito (new Integer (oid_Deposito));
    return new WMS_DepositoRN ().getByRecord (ed);
  }

  public WMS_DepositoED getByRecord (String oid_Deposito) throws Excecoes {

    WMS_DepositoED ed = new WMS_DepositoED ();

    ed.setOid_Deposito (new Integer (oid_Deposito));
    return new WMS_DepositoRN ().getByRecord (ed);
  }


  public WMS_DepositoED getByCD_Deposito (String CD_Deposito) throws Excecoes {

    if (util.doValida (CD_Deposito)) {
      return new WMS_DepositoRN ().getByCD_Deposito (CD_Deposito);
    }
    else {
      return new WMS_DepositoED ();
    }
  }

  public void geraRelatorio (HttpServletRequest req) throws Excecoes {

    WMS_DepositoED ed = new WMS_DepositoED ();
    new WMS_DepositoRN ().geraRelatorio (ed);
  }
}