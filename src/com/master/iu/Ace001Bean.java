package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.UsuarioED;
import com.master.rn.Acerto_ViagemRN;
import com.master.rn.UsuarioRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class Ace001Bean {

  Utilitaria util = new Utilitaria ();

  public UsuarioED inclui (HttpServletRequest request) throws Excecoes {

    UsuarioED ed = new UsuarioED ();
    //ed.setOid_Usuario(new Integer(request.getParameter("FT_OID_Usuario")));
    ed.setOid_Unidade (new Integer (request.getParameter ("oid_Unidade")));
    ed.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
    ed.setNM_Senha (request.getParameter ("FT_NM_Senha"));
    ed.setDM_Perfil (request.getParameter ("FT_DM_Perfil"));
    ed.setOid_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));

    return new UsuarioRN ().inclui (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    UsuarioED ed = new UsuarioED ();

    ed.setOid_Usuario (new Integer (request.getParameter ("FT_OID_Usuario")));
    ed.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
    ed.setNM_Senha (request.getParameter ("FT_NM_Senha"));
    ed.setDM_Perfil (request.getParameter ("FT_DM_Perfil"));
    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));
    ed.setOid_Unidade (new Integer (request.getParameter ("oid_Unidade")));
    ed.setNm_Email (request.getParameter ("FT_NM_Email"));
    ed.setOid_Pessoa (request.getParameter ("oid_Pessoa"));

    new UsuarioRN ().altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    UsuarioED ed = new UsuarioED ();

    ed.setOid_Usuario (new Integer (request.getParameter ("FT_OID_Usuario")));

    new UsuarioRN ().deleta (ed);
  }

  public ArrayList Usuario_Lista (HttpServletRequest request) throws Excecoes {

    UsuarioED ed = new UsuarioED ();

    String oid_Usuario = request.getParameter ("FT_OID_Usuario");
    String oid_Unidade = request.getParameter ("FT_OID_Unidade");
    String nm_Usuario = request.getParameter ("FT_NM_Usuario");

    if (util.doValida (oid_Usuario)) {
      ed.setOid_Usuario (new Integer (oid_Usuario));
    }
    if (util.doValida (oid_Unidade)) {
      ed.setOid_Unidade (new Integer (oid_Unidade));
    }
    if (util.doValida (nm_Usuario)) {
      ed.setNm_Usuario (nm_Usuario);
    }

    return new UsuarioRN ().lista (ed);

  }

  public UsuarioED getByRecord (HttpServletRequest request) throws Excecoes {

    UsuarioED ed = new UsuarioED ();

    String oid_Usuario = request.getParameter ("FT_OID_Usuario");
    if (util.doValida (oid_Usuario)) {
      ed.setOid_Usuario (new Integer (oid_Usuario));
    }
    else {
      oid_Usuario = request.getParameter ("oid_Usuario");
      if (util.doValida (oid_Usuario)) {
        ed.setOid_Usuario (new Integer (oid_Usuario));
      }
    }

    ed.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
    ed.setNM_Senha (request.getParameter ("FT_NM_Senha"));

    return new UsuarioRN ().getByRecord (ed);
  }

  //*** Verifica se Usuário ja existe
   public boolean doExiste (UsuarioED ed) throws Excecoes {

     return util.doExiste ("Usuarios" ,
                           "oid_Usuairo = " + ed.getOid_Usuario ());
   }

  public void geraRelatorio (HttpServletRequest req) throws Excecoes {

    UsuarioED ed = new UsuarioED ();
    //    ed.setCD_Usuario(req.getParameter("codigo"));
    //    ed.setCD_Remessa(req.getParameter("nome"));
    UsuarioRN geRN = new UsuarioRN ();
    geRN.geraRelatorio (ed);
  }

  public byte[] geraRelacaoUsuarios(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
	    UsuarioED ed = new UsuarioED ();
	    
	    ed.setOid_Pessoa(request.getParameter ("oid_Pessoa"));
	    ed.setDM_Relatorio(request.getParameter ("FT_DM_Relatorio"));
	    ed.setDM_Situacao(request.getParameter ("FT_DM_Situacao"));
	    ed.setDT_Acesso_Inicial(request.getParameter ("FT_DT_Acesso_Inicial"));
	    ed.setDT_Acesso_Final(request.getParameter ("FT_DT_Acesso_Final"));
	    
	    return new UsuarioRN().geraRelacaoUsuarios(ed);
  }
	  
  public boolean getByEncrypt (String chave , String usuario) throws Excecoes {

    return new UsuarioRN ().getByEncrypt (chave , usuario);
  }

  public boolean getByEncrypt (String chave , String usuario , String acao) throws Excecoes {

    return new UsuarioRN ().getByEncrypt (chave , usuario , acao);
  }

  public UsuarioED getByOID (String oid_Usuario) throws Excecoes {

    UsuarioED ed = new UsuarioED ();

    if (util.doValida (oid_Usuario)) {
      ed.setOid_Usuario (new Integer (oid_Usuario));
    }

    return new UsuarioRN ().getByRecord (ed);
  }

  public UsuarioED getByRecord_Encrypt (HttpServletRequest request) throws Excecoes {

    UsuarioED ed = new UsuarioED ();

    String oid_Usuario = request.getParameter ("FT_OID_Usuario");
    if (util.doValida (oid_Usuario)) {
      ed.setOid_Usuario (new Integer (oid_Usuario));
    }
    else {
      oid_Usuario = request.getParameter ("oid_Usuario");
      if (util.doValida (oid_Usuario)) {
        ed.setOid_Usuario (new Integer (oid_Usuario));
      }
    }

    ed.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
    ed.setNM_Senha (request.getParameter ("enc_senha"));

    return new UsuarioRN ().getByRecord_Encrypt (ed);
  }

  /**
   * Busca usuário STGP
   * @param request
   * @return
   * @throws Excecoes
   */
  public UsuarioED getByRecord_Encrypt_Stgp (HttpServletRequest request) throws Excecoes {

	    UsuarioED ed = new UsuarioED ();

	    ed.setNr_Cnpj (request.getParameter ("FT_CNPJ"));
	    ed.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
	    ed.setNM_Senha (request.getParameter ("enc_senha"));
	    
	    return new UsuarioRN ().getByRecord_Encrypt_Stgp (ed);
	  }

  /**
   * Busca usuário HELP
   * @param request
   * @return
   * @throws Excecoes
   */
  public UsuarioED getByRecord_Encrypt_Help (HttpServletRequest request) throws Excecoes {

	    UsuarioED ed = new UsuarioED ();

	    ed.setNm_Usuario (request.getParameter ("FT_NM_Usuario"));
	    ed.setNM_Senha (request.getParameter ("enc_senha"));
	    
	    return new UsuarioRN ().getByRecord_Encrypt_Help (ed);
	  }
  

}
