package com.master.iu;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Grupo_PessoaED;
import com.master.rn.Grupo_PessoaRN;
import com.master.util.Excecoes;

public class cad028Bean {

  public Grupo_PessoaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_PessoaRN Grupo_PessoaRN = new Grupo_PessoaRN();
      Grupo_PessoaED ed = new Grupo_PessoaED();

      ed.setCd_Grupo_Pessoa(request.getParameter("FT_CD_Grupo_Pessoa"));
      ed.setNm_Grupo_Pessoa(request.getParameter("FT_NM_Grupo_Pessoa"));

      return Grupo_PessoaRN.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_PessoaRN Grupo_PessoaRN = new Grupo_PessoaRN();
      Grupo_PessoaED ed = new Grupo_PessoaED();

      ed.setCd_Grupo_Pessoa(request.getParameter("FT_CD_Grupo_Pessoa"));
      ed.setNm_Grupo_Pessoa(request.getParameter("FT_NM_Grupo_Pessoa"));

      Grupo_PessoaRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_PessoaRN Grupo_Pessoarn = new Grupo_PessoaRN();
      Grupo_PessoaED ed = new Grupo_PessoaED();

      ed.setCd_Grupo_Pessoa(request.getParameter("FT_CD_Grupo_Pessoa"));

      Grupo_Pessoarn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Grupo_Pessoa_Lista(HttpServletRequest request)throws Excecoes{

      Grupo_PessoaED ed = new Grupo_PessoaED();

      String Cd_Grupo_Pessoa = request.getParameter("FT_CD_Grupo_Pessoa");
      String nm_Grupo_Pessoa = request.getParameter("FT_NM_Grupo_Pessoa");

      if (Cd_Grupo_Pessoa != null && !Cd_Grupo_Pessoa.equals(""))
        ed.setCd_Grupo_Pessoa(Cd_Grupo_Pessoa);

      if (nm_Grupo_Pessoa != null && !nm_Grupo_Pessoa.equals(""))
        ed.setNm_Grupo_Pessoa(nm_Grupo_Pessoa);


      return new Grupo_PessoaRN().lista(ed);

  }

  public Grupo_PessoaED getByRecord(HttpServletRequest request)throws Excecoes{

      Grupo_PessoaED ed = new Grupo_PessoaED();

      String Cd_Grupo_Pessoa = request.getParameter("FT_CD_Grupo_Pessoa");
      if (Cd_Grupo_Pessoa != null && Cd_Grupo_Pessoa.length() > 0)
      {
        ed.setCd_Grupo_Pessoa(Cd_Grupo_Pessoa);
      }

     return new Grupo_PessoaRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Grupo_PessoaED ed = new Grupo_PessoaED();

//    ed.setCD_Grupo_Pessoa(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Grupo_PessoaRN geRN = new Grupo_PessoaRN();
    geRN.geraRelatorio(ed);
  }


}
