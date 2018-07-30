package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_LocalizacaoED;
import com.master.rn.WMS_LocalizacaoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class wms003Bean
    {
	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

  public WMS_LocalizacaoED inclui (HttpServletRequest request) throws Excecoes {

    String oid_Deposito = request.getParameter ("oid_Deposito");
    String NM_Rua = request.getParameter ("FT_NM_Rua");
    String NR_Endereco = request.getParameter ("FT_NR_Endereco");
    String NR_Apartamento = request.getParameter ("FT_NR_Apartamento");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");

    if (!util.doValida (oid_Deposito)) {
      throw new Mensagens ("Armazem não informado!");
    }
    if (!util.doValida (NM_Rua)) {
      throw new Mensagens ("Corredor não informado!");
    }
    if (!util.doValida (NR_Endereco)) {
      throw new Mensagens ("Lado não informado!");
    }
    if (!util.doValida (NR_Apartamento)) {
      throw new Mensagens ("Pallet não informado!");
    }
    if (!util.doValida (DM_Situacao)) {
      throw new Mensagens ("Situação não informada!");
    }

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    ed.setOid_Deposito (Integer.parseInt (oid_Deposito));
    ed.setNm_Rua (JavaUtil.LFill (NM_Rua , 2 , true));
    ed.setNr_Endereco (NR_Endereco);
    ed.setNr_Apartamento (JavaUtil.LFill (NR_Apartamento , 3 , true));
    ed.setDM_Situacao (DM_Situacao);

    //*** Valida se Existe
     if (util.doExiste ("Localizacoes" , "oid_Deposito = " + ed.getOid_Deposito () +
                   " AND NM_Rua = '" + ed.getNm_Rua () + "'" +
                   " AND NR_Endereco = '" + ed.getNr_Endereco () + "'" +
                   " AND NR_Apartamento = '" + ed.getNr_Apartamento () + "'")) {
       throw new Mensagens ("Essa Localização já Existe!");
     }

     return new WMS_LocalizacaoRN ().inclui (ed);
    }

  public WMS_LocalizacaoED altera (HttpServletRequest request) throws Excecoes {

    String oid_Localizacao = request.getParameter ("oid_Localizacao");
    String oid_Deposito = request.getParameter ("oid_Deposito");
    String NM_Rua = request.getParameter ("FT_NM_Rua");
    String NR_Endereco = request.getParameter ("FT_NR_Endereco");
    String NR_Apartamento = request.getParameter ("FT_NR_Apartamento");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");

    if (!util.doValida (oid_Localizacao)) {
      throw new Mensagens ("ID Localização não informada!");
    }
    if (!util.doValida (oid_Deposito)) {
      throw new Mensagens ("Armazem não informado!");
    }
    if (!util.doValida (NM_Rua)) {
      throw new Mensagens ("Corredor não informado!");
    }
    if (!util.doValida (NR_Endereco)) {
      throw new Mensagens ("Lado não informado!");
    }
    if (!util.doValida (NR_Apartamento)) {
      throw new Mensagens ("Pallet não informado!");
    }
    if (!util.doValida (DM_Situacao)) {
      throw new Mensagens ("Situação não informada!");
    }

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED (Integer.parseInt (oid_Localizacao));
    ed.setOid_Deposito (Integer.parseInt (oid_Deposito));
    ed.setNm_Rua (JavaUtil.LFill (NM_Rua , 2 , true));
    ed.setNr_Endereco (NR_Endereco);
    ed.setNr_Apartamento (JavaUtil.LFill (NR_Apartamento , 3 , true));
    ed.setDM_Situacao (DM_Situacao);

    //*** Valida Update
     if (util.doExiste ("Localizacoes" , "oid_Deposito = " + ed.getOid_Deposito () +
                   " AND NM_Rua = '" + ed.getNm_Rua () + "'" +
                   " AND NR_Endereco = '" + ed.getNr_Endereco () + "'" +
                   " AND NR_Apartamento = '" + ed.getNr_Apartamento () + "'" +
                   " AND oid_Localizacao <> " + ed.getOid_Localizacao ())) {
       throw new Mensagens ("Essa Localização já existe para esse Armazem!");
     }

    return new WMS_LocalizacaoRN ().altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    new WMS_LocalizacaoRN ().deleta (new WMS_LocalizacaoED (Integer.parseInt (request.getParameter ("oid_Localizacao"))));
  }

  public WMS_LocalizacaoED getByRecord (HttpServletRequest request) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    ed.setOid_Localizacao (util.getValueDef (request.getParameter ("oid_Localizacao") , 0));
    return new WMS_LocalizacaoRN ().getByRecord (ed);

  }

  public WMS_LocalizacaoED getByOid_Localizacao (HttpServletRequest request) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    ed.setOid_Localizacao (util.getValueDef (request.getParameter ("oid_Localizacao") , 0));
    return new WMS_LocalizacaoRN ().getByOid_Localizacao (ed);

  }

  public WMS_LocalizacaoED getByOid_Localizacao (String oid_Localizacao) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    if (util.doValida (oid_Localizacao)) {
      ed.setOid_Localizacao (Integer.parseInt (oid_Localizacao));
      return new WMS_LocalizacaoRN ().getByRecord (ed);
    }
    else {
      return ed;
    }
  }

  public WMS_LocalizacaoED getByOid_Localizacao (String oid_Localizacao , String oid_Deposito) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    if (util.doValida (oid_Localizacao) && util.doValida (oid_Deposito)) {
      ed.setOid_Localizacao (Integer.parseInt (oid_Localizacao));
      ed.setOid_Deposito (Integer.parseInt (oid_Deposito));
      return new WMS_LocalizacaoRN ().getByRecord (ed);
    }
    else {
      return ed;
    }
  }

  public WMS_LocalizacaoED getByCD_Localizacao (String CD_Localizacao) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    if (util.doValida (CD_Localizacao)) {
      ed.setCD_Localizacao (CD_Localizacao);
      return new WMS_LocalizacaoRN ().getByRecord (ed);
    }
    else {
      return ed;
    }
  }

  public WMS_LocalizacaoED getByCD_Localizacao (String CD_Localizacao , String oid_Deposito) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    if (util.doValida (CD_Localizacao) && util.doValida (oid_Deposito)) {
      ed.setCD_Localizacao (CD_Localizacao);
      ed.setOid_Deposito (Integer.parseInt (oid_Deposito));
      return new WMS_LocalizacaoRN ().getByRecord (ed);
    }
    else {
      return ed;
    }
  }

  public WMS_LocalizacaoED getByOid_Localizacao_Final (HttpServletRequest request) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    ed.setOid_Localizacao (Integer.parseInt (request.getParameter ("FT_OID_Localizacao_Final")));
    return new WMS_LocalizacaoRN ().getByOid_Localizacao (ed);

  }

  public WMS_LocalizacaoED getByOid_Deposito (String oid_Deposito) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();
    ed.setOid_Deposito (Integer.parseInt (oid_Deposito));
    return new WMS_LocalizacaoRN ().getByOid_Deposito (ed);

  }

  public ArrayList lista (HttpServletRequest request) throws Excecoes {

    WMS_LocalizacaoED ed = new WMS_LocalizacaoED ();

    String oid_Deposito = request.getParameter ("oid_Deposito");
    String NM_Rua = request.getParameter ("FT_NM_Rua");
    String NM_Deposito = request.getParameter ("FT_NM_Deposito");
    String NR_Endereco = request.getParameter ("FT_NR_Endereco");
    String NR_Apartamento = request.getParameter ("FT_NR_Apartamento");

   
    if (util.doValida (oid_Deposito)) {
      ed.setOid_Deposito (Integer.parseInt (oid_Deposito));
    }
    else {
      if (util.doValida (NM_Rua)) {
        ed.setNm_Rua (JavaUtil.LFill (NM_Rua , 2 , true));
      }
      if (util.doValida (NR_Endereco)) {
        ed.setNr_Endereco (NR_Endereco);
      }
      if (util.doValida (NR_Apartamento)) {
        ed.setNr_Apartamento (JavaUtil.LFill (NR_Apartamento , 3 , true));
      }
      if (util.doValida (NM_Deposito)) {
          ed.setNM_Deposito(NM_Deposito);
        }
    }
    return new WMS_LocalizacaoRN ().lista (ed);
  }
}