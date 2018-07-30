/*
 * Created on 12/11/2004
 *
 */
package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.PneuED;
import com.master.rn.PneuRN;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class PneuBean {
  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);

  public static final String FIELD_OID_PNEU = "oid_Pneu";
  public static final String FIELD_CD_PNEU = "FT_CD_Pneu";
  public static final String FIELD_NR_FABRICA = "FT_NR_Fabrica";
  public static final String FIELD_OID_FABRICANTE_PNEU = "oid_Fabricante_Pneu";
  public static final String FIELD_CD_FABRICANTE_PNEU = "FT_CD_Fabricante_Pneu";
  public static final String FIELD_NM_FABRICANTE_PNEU = "FT_NM_Fabricante_Pneu";
  public static final String FIELD_OID_MODELO_PNEU = "oid_Modelo_Pneu";
  public static final String FIELD_CD_MODELO_PNEU = "FT_CD_Modelo_Pneu";
  public static final String FIELD_NM_MODELO_PNEU = "FT_NM_Modelo_Pneu";
  public static final String FIELD_DM_LOCALIZACAO = "FT_DM_Localizacao";
  public static final String FIELD_DM_SITUACAO = "FT_DM_Situacao";
  public static final String FIELD_DM_POSICAO = "FT_DM_Posicao";
  public static final String FIELD_KM_ATUAL = "FT_KM_Atual";
  public static final String FIELD_MM_Atual = "FT_MM_Atual";
  public static final String FIELD_OID_VEICULO = "oid_Veiculo";
  public static final String FIELD_NR_PLACA = "FT_NR_Placa";
  public static final String FIELD_NR_FROTA = "FT_NR_Frota";

  public static final String FIELD_NOT_INTO_LOC = "notInto_Loc";
  public static final String FIELD_NOT_INTO_SIT = "notInto_Sit";

  public static final String FIELD_OID_ESTOQUE = "oid_Estoque";
  public static final String FIELD_NM_ESTOQUE = "FT_NM_Estoque";
  public static final String FIELD_CD_ESTOQUE = "FT_CD_Estoque";

  public PneuED inclui (HttpServletRequest request) throws Excecoes {
    String cd_Pneu = request.getParameter (FIELD_CD_PNEU);
    String nr_Fabrica = request.getParameter (FIELD_NR_FABRICA);
    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);
    String dm_Localizacao = request.getParameter (FIELD_DM_LOCALIZACAO);
    String dm_Situacao = request.getParameter (FIELD_DM_SITUACAO);
    String km_Atual = request.getParameter (FIELD_KM_ATUAL);
    String MM_Atual = request.getParameter (FIELD_MM_Atual);

    String oid_Estoque = request.getParameter (FIELD_OID_ESTOQUE);

    if (!util.doValida (km_Atual)) {
      km_Atual = "0";
    }
    if (!util.doValida (MM_Atual)) {
      MM_Atual = "0";
    }

    //Validações dos campos do request
    if (!util.doValida (oid_Fabricante_Pneu)) {
      throw new Mensagens ("Fabricante não informado!");
    }
    if (!util.doValida (oid_Modelo_Pneu)) {
      throw new Mensagens ("Modelo não informado!");
    }
    if (!util.doValida (dm_Localizacao)) {
      throw new Mensagens ("Localização não informada!");
    }
    if (!util.doValida (dm_Situacao)) {
      throw new Mensagens ("Situação não informada!");
    }

    PneuED ed = new PneuED (0 , cd_Pneu , nr_Fabrica , Integer.parseInt (oid_Fabricante_Pneu) ,
                            Integer.parseInt (oid_Modelo_Pneu) , dm_Localizacao ,
                            dm_Situacao , Integer.parseInt (km_Atual) , Double.parseDouble (MM_Atual) , "");

    if (util.doValida (oid_Estoque)) {
      ed.setOid_estoque (Long.parseLong (oid_Estoque));
    }

    //*** Valida se o registro já existe
     if (existeCodigo (ed)) {
       throw new Mensagens ("Já existe um pneu com este código!");
     }
    if (existeNRFabrica (ed)) {
      throw new Mensagens ("Já existe um pneu com este número de fábrica!");
    }
    return new PneuRN ().inclui (ed);
  }

  public void incluiPneuVeiculo (HttpServletRequest request) throws Exception {

    String oid_Veiculo = request.getParameter (FIELD_OID_VEICULO);
    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
    String DM_Posicao = request.getParameter (FIELD_DM_POSICAO);
    String KM_Atual = request.getParameter (FIELD_KM_ATUAL);    

    //Validações dos campos do request
    if (!util.doValida (oid_Veiculo)) {
      throw new Mensagens ("ID Veiculo não informado!");
    }
    if (!util.doValida (oid_Pneu)) {
      throw new Mensagens ("ID Pneu não informado!");
    }
    if (!util.doValida (DM_Posicao)) {
      throw new Mensagens ("Posição do Pneu não informada!");
    }

    //Valida se ja existe outro pneu na mesma posição
    if (util.doExiste ("Pneus" , "oid_Veiculo = '" + oid_Veiculo + "'" +
                       " AND DM_Posicao = '" + DM_Posicao + "'")) {
      throw new Mensagens ("Já Existe nesse Veículo um pneu na posição informada!");
    }

    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu));
    ed.setOid_Veiculo (oid_Veiculo);
    ed.setDM_Posicao (DM_Posicao);
    ed.setKM_Atual(Integer.parseInt (KM_Atual));    
    new PneuRN ().incluiPneuVeiculo (ed);
  }

  public void alteraPneuVeiculo (HttpServletRequest request) throws Excecoes {

    String oid_Veiculo = request.getParameter (FIELD_OID_VEICULO);
    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
    String DM_Posicao = request.getParameter (FIELD_DM_POSICAO);
    String KM_Atual = request.getParameter (FIELD_KM_ATUAL);

    //Validações dos campos do request
    if (!util.doValida (oid_Veiculo)) {
      throw new Mensagens ("ID Veiculo não informado!");
    }
    if (!util.doValida (oid_Pneu)) {
      throw new Mensagens ("ID Pneu não informado!");
    }
    if (!util.doValida (DM_Posicao)) {
      throw new Mensagens ("Posição do Pneu não informada!");
    }

    //Valida se ja existe outro pneu na mesma posição
    if (util.doExiste ("Pneus" , "oid_Veiculo = '" + oid_Veiculo + "'" +
                       " AND DM_Posicao = '" + DM_Posicao + "'" +
                       " AND oid_Pneu <> " + oid_Pneu)) {
      throw new Mensagens ("Já Existe nesse Veículo outro pneu na posição informada!");
    }

    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu));
    ed.setOid_Veiculo (oid_Veiculo);
    ed.setDM_Posicao (DM_Posicao);
    ed.setKM_Atual(Integer.parseInt (KM_Atual));
    new PneuRN ().alteraPneuVeiculo (ed);
  }

  public void removePneuVeiculo (HttpServletRequest request) throws Exception {

    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
    String dm_Localizacao = request.getParameter (FIELD_DM_LOCALIZACAO);
    String dm_Situacao = request.getParameter (FIELD_DM_SITUACAO);

    //Validações dos campos do request
    if (!util.doValida (oid_Pneu)) {
      throw new Mensagens ("ID Pneu não informado!");
    }
    if (!util.doValida (dm_Localizacao)) {
      throw new Mensagens ("Localização não informada!");
    }
    if (!util.doValida (dm_Situacao)) {
      throw new Mensagens ("Situação não informada!");
    }

    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu));
    ed.setDM_Localizacao (dm_Localizacao);
    ed.setDM_Situacao (dm_Situacao);
    new PneuRN ().removePneuVeiculo (ed);
  }

  public void manutencaoPneuVeiculo (HttpServletRequest request) throws Exception {

    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
    String dm_Localizacao = request.getParameter (FIELD_DM_LOCALIZACAO);
    String dm_Situacao = request.getParameter (FIELD_DM_SITUACAO);

    //Validações dos campos do request
    if (!util.doValida (oid_Pneu)) {
      throw new Mensagens ("ID Pneu não informado!");
    }
    if (!util.doValida (dm_Localizacao)) {
      throw new Mensagens ("Localizacão não informada!");
    }
    if (!util.doValida (dm_Situacao)) {
      throw new Mensagens ("Situação não informada!");
    }

    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu));
    ed.setDM_Localizacao (dm_Localizacao);
    ed.setDM_Situacao (dm_Situacao);
    new PneuRN ().manutencaoPneuVeiculo (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
    String cd_Pneu = request.getParameter (FIELD_CD_PNEU);
    String nr_Fabrica = request.getParameter (FIELD_NR_FABRICA);
    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);
    String dm_Localizacao = request.getParameter (FIELD_DM_LOCALIZACAO);
    String dm_Situacao = request.getParameter (FIELD_DM_SITUACAO);
    String km_Atual = request.getParameter (FIELD_KM_ATUAL);
    String oid_Veiculo = request.getParameter (FIELD_OID_VEICULO);

    String oid_Estoque = request.getParameter (FIELD_OID_ESTOQUE);

    if (!util.doValida (km_Atual)) {
      km_Atual = "0";
    }
    String MM_Atual = request.getParameter (FIELD_MM_Atual);
    if (!util.doValida (MM_Atual)) {
      MM_Atual = "0";
    }

    //Validações dos campos do request
    if (!util.doValida (oid_Pneu)) {
      throw new Mensagens ("ID Pneu não informado!");
    }
    if (!util.doValida (cd_Pneu)) {
      throw new Mensagens ("Código não informado!");
    }
    if (!util.doValida (oid_Fabricante_Pneu)) {
      throw new Mensagens ("Fabricante não informado!");
    }
    if (!util.doValida (oid_Modelo_Pneu)) {
      throw new Mensagens ("Modelo não informado!");
    }
    if (!util.doValida (dm_Localizacao)) {
      throw new Mensagens ("Localização não informada!");
    }
    if (!util.doValida (dm_Situacao)) {
      throw new Mensagens ("Situação não informada!");
    }

    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu) , cd_Pneu , nr_Fabrica , Integer.parseInt (oid_Fabricante_Pneu) ,
                            Integer.parseInt (oid_Modelo_Pneu) , dm_Localizacao ,
                            dm_Situacao , Integer.parseInt (km_Atual) , Double.parseDouble (MM_Atual) , oid_Veiculo);

    //Valida se é possível a atualização do registro
    util.doValidaUpdate (ed.getOid_Pneu () , ed.getCD_Pneu () , "Pneus" , "oid_pneu" , "cd_pneu");
    //Valida se é possível a atualização do registro
    util.doValidaUpdate (ed.getOid_Pneu () , ed.getNR_Fabrica () , "Pneus" , "oid_pneu" , "nr_fabrica");
    new PneuRN ().altera (ed);
  }

  public void delete (HttpServletRequest request) throws Excecoes {
    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);

    if (!util.doValida (oid_Pneu)) {
      throw new Mensagens ("ID Pneu não informado!");
    }
    new PneuRN ().delete (new PneuED (Integer.parseInt (oid_Pneu)));
  }

  public ArrayList lista (HttpServletRequest request) throws Excecoes {

    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
    String cd_Pneu = request.getParameter (FIELD_CD_PNEU);
    String nr_Fabrica = request.getParameter (FIELD_NR_FABRICA);
    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);
    String dm_Localizacao = request.getParameter (FIELD_DM_LOCALIZACAO);
    String dm_Situacao = request.getParameter (FIELD_DM_SITUACAO);
    String km_Atual = request.getParameter (FIELD_KM_ATUAL);
    String oid_Veiculo = request.getParameter (FIELD_OID_VEICULO);

    String notInto_Loc = request.getParameter (FIELD_NOT_INTO_LOC);
    String notInto_Sit = request.getParameter (FIELD_NOT_INTO_SIT);
    String MM_Atual = request.getParameter (FIELD_MM_Atual);
    if (!util.doValida (MM_Atual)) {
      MM_Atual = "0";
    }

    if (!util.doValida (oid_Pneu)) {
      oid_Pneu = "0";
    }
    if (!util.doValida (cd_Pneu)) {
      cd_Pneu = "";
    }
    if (!util.doValida (nr_Fabrica)) {
      nr_Fabrica = "";
    }
    if (!util.doValida (oid_Fabricante_Pneu)) {
      oid_Fabricante_Pneu = "0";
    }
    if (!util.doValida (oid_Modelo_Pneu)) {
      oid_Modelo_Pneu = "0";
    }
    if (!util.doValida (dm_Localizacao)) {
      dm_Localizacao = "";
    }
    if (!util.doValida (dm_Situacao)) {
      dm_Situacao = "";
    }
    if (!util.doValida (km_Atual)) {
      km_Atual = "0";
    }
    if (!util.doValida (oid_Veiculo)) {
      oid_Veiculo = "0";
    }

    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu) , cd_Pneu , nr_Fabrica , Integer.parseInt (oid_Fabricante_Pneu) ,
                            Integer.parseInt (oid_Modelo_Pneu) , dm_Localizacao ,
                            dm_Situacao , Integer.parseInt (km_Atual) , Double.parseDouble (MM_Atual) , oid_Veiculo);
    if (util.doValida (notInto_Loc)) {
      ed.setNotINTO_Loc (notInto_Loc);
    }
    if (util.doValida (notInto_Sit)) {
      ed.setNotINTO_Sit (notInto_Sit);
    }

    return new PneuRN ().lista (ed);
  }
  
  public ArrayList lista_Consulta (HttpServletRequest request) throws Excecoes {

	    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
	    String cd_Pneu = request.getParameter (FIELD_CD_PNEU);
	    String nr_Fabrica = request.getParameter (FIELD_NR_FABRICA);
	    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
	    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);
	    String dm_Localizacao = request.getParameter (FIELD_DM_LOCALIZACAO);
	    String dm_Situacao = request.getParameter (FIELD_DM_SITUACAO);
	    String km_Atual = request.getParameter (FIELD_KM_ATUAL);
	    String oid_Veiculo = request.getParameter (FIELD_OID_VEICULO);

	    String notInto_Loc = request.getParameter (FIELD_NOT_INTO_LOC);
	    String notInto_Sit = request.getParameter (FIELD_NOT_INTO_SIT);
	    String MM_Atual = request.getParameter (FIELD_MM_Atual);
	    if (!util.doValida (MM_Atual)) {
	      MM_Atual = "0";
	    }

	    if (!util.doValida (oid_Pneu)) {
	      oid_Pneu = "0";
	    }
	    if (!util.doValida (cd_Pneu)) {
	      cd_Pneu = "";
	    }
	    if (!util.doValida (nr_Fabrica)) {
	      nr_Fabrica = "";
	    }
	    if (!util.doValida (oid_Fabricante_Pneu)) {
	      oid_Fabricante_Pneu = "0";
	    }
	    if (!util.doValida (oid_Modelo_Pneu)) {
	      oid_Modelo_Pneu = "0";
	    }
	    if (!util.doValida (dm_Localizacao)) {
	      dm_Localizacao = "";
	    }
	    if (!util.doValida (dm_Situacao)) {
	      dm_Situacao = "";
	    }
	    if (!util.doValida (km_Atual)) {
	      km_Atual = "0";
	    }
	    if (!util.doValida (oid_Veiculo)) {
	      oid_Veiculo = "0";
	    }

	    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu) , cd_Pneu , nr_Fabrica , Integer.parseInt (oid_Fabricante_Pneu) ,
	                            Integer.parseInt (oid_Modelo_Pneu) , dm_Localizacao ,
	                            dm_Situacao , Integer.parseInt (km_Atual) , Double.parseDouble (MM_Atual) , oid_Veiculo);
	    if (util.doValida (notInto_Loc)) {
	      ed.setNotINTO_Loc (notInto_Loc);
	    }
	    if (util.doValida (notInto_Sit)) {
	      ed.setNotINTO_Sit (notInto_Sit);
	    }

	    return new PneuRN ().lista_Consulta (ed);
  }

  public PneuED getByRecord (HttpServletRequest request) throws Excecoes {
    String oid = request.getParameter (FIELD_OID_PNEU);
    if (util.doValida (oid)) {
      return new PneuRN ().getByRecord (Integer.parseInt (oid));
    }
    else {
      throw new Mensagens ("Parâmetros inválidos!");
    }
  }

  public PneuED getByCodigo (HttpServletRequest request) throws Excecoes {
    String codigo = request.getParameter (FIELD_CD_PNEU);
    if (util.doValida (codigo)) {
      return new PneuRN ().getByCodigo (codigo);
    }
    else {
      throw new Mensagens ("Código Pneu não informado!");
    }
  }

  public PneuED getByNrFabrica (HttpServletRequest request) throws Excecoes {
    String nrFabrica = request.getParameter (FIELD_NR_FABRICA);
    if (util.doValida (nrFabrica)) {
      return new PneuRN ().getByNrFabrica (nrFabrica);
    }
    else {
      throw new Mensagens ("Nr Fábrica não informada!");
    }
  }

  public PneuED getByOIDLocSit (String oid_Pneu , String notInto_Loc , String notInto_Sit) throws Excecoes {

    PneuED ed = new PneuED ();
    if (!util.doValida (oid_Pneu)) {
      throw new Mensagens ("ID Pneu não informado!");
    }
    if (!util.doValida (notInto_Loc)) {
      throw new Mensagens ("Localização não informada!");
    }
    if (!util.doValida (notInto_Sit)) {
      throw new Mensagens ("Situação não informada!");
    }

    ed.setOid_Pneu (Integer.parseInt (oid_Pneu));
    ed.setNotINTO_Loc (notInto_Loc);
    ed.setNotINTO_Sit (notInto_Sit);
    return new PneuRN ().getByRecord (ed);
  }

  public PneuED getByCDLocSit (String cd_Pneu , String notInto_Loc , String notInto_Sit) throws Excecoes {

    PneuED ed = new PneuED ();
    if (!util.doValida (cd_Pneu)) {
      throw new Mensagens ("Código Pneu não informado!");
    }
    if (!util.doValida (notInto_Loc)) {
      throw new Mensagens ("Localização não informada!");
    }
    if (!util.doValida (notInto_Sit)) {
      throw new Mensagens ("Situação não informada!");
    }

    ed.setCD_Pneu (cd_Pneu);
    ed.setNotINTO_Loc (notInto_Loc);
    ed.setNotINTO_Sit (notInto_Sit);
    return new PneuRN ().getByRecord (ed);
  }

  //*** Verifica se registro já existe!
   private boolean existeCodigo (PneuED ed) throws Excecoes {
     //*** Validações
      String strFrom = "Pneus";
     String strWhere = " cd_pneu = '" + ed.getCD_Pneu () + "'";
     return util.doExiste (strFrom , strWhere);
   }

  //*** Verifica se registro já existe!
   private boolean existeNRFabrica (PneuED ed) throws Excecoes {
     //*** Validações
      String strFrom = "Pneus";
     String strWhere = " nr_fabrica = '" + ed.getNR_Fabrica () + "'";
     return util.doExiste (strFrom , strWhere);
   }

  public void geraRelPneus (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    String oid_Pneu = request.getParameter (FIELD_OID_PNEU);
    String cd_Pneu = request.getParameter (FIELD_CD_PNEU);
    String nr_Fabrica = request.getParameter (FIELD_NR_FABRICA);
    String oid_Fabricante_Pneu = request.getParameter (FIELD_OID_FABRICANTE_PNEU);
    String oid_Modelo_Pneu = request.getParameter (FIELD_OID_MODELO_PNEU);
    String dm_Localizacao = request.getParameter (FIELD_DM_LOCALIZACAO);
    String dm_Situacao = request.getParameter (FIELD_DM_SITUACAO);
    String km_Atual = request.getParameter (FIELD_KM_ATUAL);
    String oid_Veiculo = request.getParameter (FIELD_OID_VEICULO);

    String notInto_Loc = request.getParameter (FIELD_NOT_INTO_LOC);
    String notInto_Sit = request.getParameter (FIELD_NOT_INTO_SIT);

    String MM_Atual = request.getParameter (FIELD_MM_Atual);
    if (!util.doValida (MM_Atual)) {
      MM_Atual = "0";
    }

    if (!util.doValida (oid_Pneu)) {
      oid_Pneu = "0";
    }
    if (!util.doValida (cd_Pneu)) {
      cd_Pneu = "";
    }
    if (!util.doValida (nr_Fabrica)) {
      nr_Fabrica = "";
    }
    if (!util.doValida (oid_Fabricante_Pneu)) {
      oid_Fabricante_Pneu = "0";
    }
    if (!util.doValida (oid_Modelo_Pneu)) {
      oid_Modelo_Pneu = "0";
    }
    if (!util.doValida (dm_Localizacao)) {
      dm_Localizacao = "";
    }
    if (!util.doValida (dm_Situacao)) {
      dm_Situacao = "";
    }
    if (!util.doValida (km_Atual)) {
      km_Atual = "0";
    }
    if (!util.doValida (oid_Veiculo)) {
      oid_Veiculo = "0";
    }

    PneuED ed = new PneuED (Integer.parseInt (oid_Pneu) , cd_Pneu , nr_Fabrica , Integer.parseInt (oid_Fabricante_Pneu) ,
                            Integer.parseInt (oid_Modelo_Pneu) , dm_Localizacao ,
                            dm_Situacao , Integer.parseInt (km_Atual) , Double.parseDouble (MM_Atual) , oid_Veiculo);
    if (util.doValida (notInto_Loc)) {
      ed.setNotINTO_Loc (notInto_Loc);
    }
    if (util.doValida (notInto_Sit)) {
      ed.setNotINTO_Sit (notInto_Sit);
    }

    new PneuRN ().geraRelPneus (ed , response);
  }

}
