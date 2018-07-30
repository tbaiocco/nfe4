package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Padrao_EDIED;
import com.master.rn.Padrao_EDIRN;
import com.master.util.Data;
import com.master.util.Excecoes;

       public class Edi003Bean {

       private Data dt_stamp = new Data();

       /**
 * <p>Title: incluiPadrao </p>
 * <p>Description: Inclui padrão de importação
 * @author Teófilo Poletto Baiocco
 */
  public Padrao_EDIED incluiPadrao(HttpServletRequest request)throws Excecoes{

    try{
      Padrao_EDIRN Padrao_EDIrn = new Padrao_EDIRN();
      Padrao_EDIED padrao_ed = new Padrao_EDIED();

      //request em cima dos campos dos forms html

      padrao_ed.setCd_padrao(request.getParameter("FT_CD_Padrao"));
      padrao_ed.setDm_tipo_padrao(request.getParameter("FT_DM_Tipo_Padrao"));
      padrao_ed.setDm_tipo_transacao(request.getParameter("FT_DM_Tipo_Transacao"));

      padrao_ed.setDm_classe(request.getParameter("FT_DM_Classe"));

      padrao_ed.setCd_padrao(request.getParameter("FT_CD_Padrao"));

      padrao_ed.setNm_descricao_padrao(request.getParameter("FT_NM_Descricao"));
      padrao_ed.setUsuario_stamp(request.getParameter("FT_NM_Usuario"));
      padrao_ed.setDt_stamp_padrao(dt_stamp.getDataDMY());
      padrao_ed.setOid_tipo_padrao(Long.parseLong(request.getParameter("oid_Tipo_Padrao")));
      //// System.out.println(padrao_ed.getOid_tipo_padrao());

      return Padrao_EDIrn.incluiPadrao(padrao_ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir o Padrao");
      excecoes.setMetodo("incluiPadrao");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  /**
 * <p>Title: incluiTipo </p>
 * <p>Description: Inclui Tipo-padrão de importação
 * @author Teófilo Poletto Baiocco
 */
  public Padrao_EDIED incluiTipo(HttpServletRequest request)throws Excecoes{

    try{
      Padrao_EDIRN Padrao_EDIrn = new Padrao_EDIRN();
      Padrao_EDIED padrao_ed = new Padrao_EDIED();

      //request em cima dos campos dos forms html

      padrao_ed.setCd_tipo(request.getParameter("FT_CD_Tipo"));
      padrao_ed.setNm_descricao_tipo(request.getParameter("FT_NM_Descricao"));
      padrao_ed.setUsuario_stamp_tipo(request.getParameter("FT_NM_Usuario"));
      padrao_ed.setDt_stamp_tipo(dt_stamp.getDataDMY());

      return Padrao_EDIrn.incluiTipo(padrao_ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir o Padrao");
      excecoes.setMetodo("incluiPadrao");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  /**
 * <p>Title: getByCD </p>
 * <p>Description: Seleciona Tipo-padrão pelo código
 * @author Teófilo Poletto Baiocco
 */
  public Padrao_EDIED getByCd(HttpServletRequest request) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String CD_Tipo = request.getParameter("FT_CD_Tipo");
         if (CD_Tipo != null && CD_Tipo.length() > 0)
         {
	    padrao_ed.setCd_tipo(request.getParameter("FT_CD_Tipo"));
        }

        return new Padrao_EDIRN().getByCd(padrao_ed);

  }

  /**
 * <p>Title: getByCdPadrao </p>
 * <p>Description: Seleciona padrão pelo código
 * @author Teófilo Poletto Baiocco
 */
  public Padrao_EDIED getByCdPadrao(HttpServletRequest request) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String CD_Padrao = request.getParameter("FT_CD_Padrao");
         if (CD_Padrao != null && CD_Padrao.length() > 0)
         {
	    padrao_ed.setCd_padrao(request.getParameter("FT_CD_Padrao"));
        }

        return new Padrao_EDIRN().getByCdPadrao(padrao_ed);

  }

  /**
 * <p>Title: getByCdPadrao2 </p>
 * <p>Description: Seleciona padrão recebendo uma string cd
 * @author Teófilo Poletto Baiocco
 */
   public Padrao_EDIED getByCdPadrao2(String cd) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String CD_Padrao = cd;
         if (CD_Padrao != null && CD_Padrao.length() > 0)
         {
	    padrao_ed.setCd_padrao(cd);
        }

        return new Padrao_EDIRN().getByCdPadrao(padrao_ed);

  }

  /**
 * <p>Title: getByNmDescricao </p>
 * <p>Description: Seleciona Tipo-padrão recebendo uma string NM_Descricao
 * @author Teófilo Poletto Baiocco
 */
  public ArrayList getByNmDescricao(String NM_Descricao) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String nm_descricao_tipo = NM_Descricao;
         if (nm_descricao_tipo != null && nm_descricao_tipo.length() > 0)
         {
	    padrao_ed.setNm_descricao_tipo(NM_Descricao);
        }

        return new Padrao_EDIRN().getByNmDescricao(padrao_ed);

  }

  /**
 * <p>Title: getByNmDescricao2 </p>
 * <p>Description: Seleciona padrão recebendo uma string NM_Descricao
 * @author Teófilo Poletto Baiocco
 */
  public ArrayList getByNmDescricao2(String NM_Descricao) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String nm_descricao = NM_Descricao;
         if (nm_descricao != null && nm_descricao.length() > 0)
         {
	    padrao_ed.setNm_descricao_padrao(NM_Descricao);
        }

        return new Padrao_EDIRN().getByNmDescricao2(padrao_ed);

  }

  /**
 * <p>Title: getByOidPadrao </p>
 * <p>Description: Seleciona padrão através da oid_Padrao
 * @author Teófilo Poletto Baiocco
 */
  public ArrayList getByOidPadrao(HttpServletRequest request) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String oid_padrao = request.getParameter("oid_Padrao");
//// System.out.println("bean " +oid_padrao);
         if (String.valueOf(oid_padrao) != null && String.valueOf(oid_padrao).length() > 0)
         {
	    padrao_ed.setOid_padrao(new Long(request.getParameter("oid_Padrao")).longValue());
        }

        return new Padrao_EDIRN().getByOidPadrao(padrao_ed);

  }

  /**
 * <p>Title: getByOidTipo2 </p>
 * <p>Description: Seleciona Tipo-padrão através da oid_tipo
 * gerando uma lista.
 * @author Teófilo Poletto Baiocco
 */
  public ArrayList getByOidTipo2(HttpServletRequest request) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String oid_tipo = request.getParameter("oid_tipo");
//// System.out.println("bean " +oid_tipo);
         if (String.valueOf(oid_tipo) != null && String.valueOf(oid_tipo).length() > 0)
         {
	    padrao_ed.setOid_tipo_padrao(new Long(request.getParameter("oid_tipo")).longValue());
        }

        return new Padrao_EDIRN().getByOidTipo2(padrao_ed);

  }

   /**
 * <p>Title: deleta </p>
 * <p>Description: Exclui padrão de importação
 * @author Teófilo Poletto Baiocco
 */
  public boolean deleta(HttpServletRequest request)throws Excecoes{

    try{
      Padrao_EDIRN rn = new Padrao_EDIRN();
      Padrao_EDIED ed = new Padrao_EDIED();
      //request em cima dos campos dos forms html

      String oid_padrao = request.getParameter("oid_Padrao");
//// System.out.println("delete: " +oid_padrao);
         if (String.valueOf(oid_padrao) != null && String.valueOf(oid_padrao).length() > 0)
         {
	    ed.setOid_padrao(new Long(request.getParameter("oid_Padrao")).longValue());
        }
      return rn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao deletar");
      excecoes.setMetodo("deletar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  /**
 * <p>Title: deletaTipo </p>
 * <p>Description: Exclui Tipo-padrão de importação
 * @author Teófilo Poletto Baiocco
 */
  public boolean deletaTipo(HttpServletRequest request)throws Excecoes{

    try{
      Padrao_EDIRN rn = new Padrao_EDIRN();
      Padrao_EDIED ed = new Padrao_EDIED();
      //request em cima dos campos dos forms html

      String oid_tipo_padrao = request.getParameter("oid_Tipo_Padrao");
//// System.out.println("delete: " +oid_tipo_padrao);
         if (String.valueOf(oid_tipo_padrao) != null && String.valueOf(oid_tipo_padrao).length() > 0)
         {
	    ed.setOid_tipo_padrao(new Long(request.getParameter("oid_Tipo_Padrao")).longValue());
        }
      return rn.deletaTipo(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao deletar tipo");
      excecoes.setMetodo("deletaTipo");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  /**
 * <p>Title: getByRecord </p>
 * <p>Description: Seleciona padrão através da oid_padrao
 * @author Teófilo Poletto Baiocco
 */
  public Padrao_EDIED getByRecord(HttpServletRequest request) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String oid_padrao = request.getParameter("oid_Padrao");
         if (oid_padrao != null && oid_padrao.length() > 0)
         {
	    padrao_ed.setOid_padrao(new Long(request.getParameter("oid_Padrao")).longValue());
        }

        return new Padrao_EDIRN().getByRecord(padrao_ed);

  }

  /**
 * <p>Title: getByRecord2 </p>
 * <p>Description: Seleciona padrão recebendo uma string oid
 * @author Teófilo Poletto Baiocco
 */
  public Padrao_EDIED getByRecord2(String oid) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

	 String oid_padrao = oid;
         if (oid_padrao != null && oid_padrao.length() > 0)
         {
	    padrao_ed.setOid_padrao(new Long(oid_padrao).longValue());
        }

        return new Padrao_EDIRN().getByRecord(padrao_ed);

  }

  /**
 * <p>Title: getByOidTipo </p>
 * <p>Description: Seleciona Tipo-padrão através de uma string oid_tipo
 * @author Teófilo Poletto Baiocco
 */
  public Padrao_EDIED getByOidTipo(String oid_tipo) throws Excecoes{

	 Padrao_EDIED padrao_ed = new Padrao_EDIED();

         if (oid_tipo != null && oid_tipo.length() > 0)
         {
	    padrao_ed.setOid_tipo_padrao(new Long(oid_tipo).longValue());
        }

        return new Padrao_EDIRN().getByOidTipo(padrao_ed);

  }

  /**
 * <p>Title: updatePadrao </p>
 * <p>Description: Altera padrão de importação
 * @author Teófilo Poletto Baiocco
 */
  public void updatePadrao(HttpServletRequest request)throws Excecoes{

    try{
      Padrao_EDIRN Padrao_EDIrn = new Padrao_EDIRN();
      Padrao_EDIED padrao_ed = new Padrao_EDIED();

      //request em cima dos campos dos forms html

      padrao_ed.setOid_padrao(Long.parseLong(request.getParameter("oid_Padrao")));
      padrao_ed.setCd_padrao(request.getParameter("FT_CD_Padrao"));
      padrao_ed.setDm_classe(request.getParameter("FT_DM_Classe"));

      padrao_ed.setNm_descricao_padrao(request.getParameter("FT_NM_Descricao"));
      padrao_ed.setUsuario_stamp(request.getParameter("FT_NM_Usuario"));
      padrao_ed.setDt_stamp_padrao(dt_stamp.getDataDMY());
      padrao_ed.setOid_tipo_padrao(Long.parseLong(request.getParameter("oid_Tipo_Padrao")));
      //// System.out.println(padrao_ed.getOid_tipo_padrao());
      padrao_ed.setDm_tipo_padrao(request.getParameter("FT_DM_Tipo_Padrao"));
      padrao_ed.setDm_tipo_transacao(request.getParameter("FT_DM_Tipo_Transacao"));

      Padrao_EDIrn.updatePadrao(padrao_ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar o Padrao");
      excecoes.setMetodo("updatePadrao");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  /**
 * <p>Title: updateTipo </p>
 * <p>Description: Altera Tipo-padrão de importação
 * @author Teófilo Poletto Baiocco
 */
  public void updateTipo(HttpServletRequest request)throws Excecoes{

    try{
      Padrao_EDIRN Padrao_EDIrn = new Padrao_EDIRN();
      Padrao_EDIED padrao_ed = new Padrao_EDIED();

      //request em cima dos campos dos forms html

      padrao_ed.setOid_tipo_padrao(Long.parseLong(request.getParameter("oid_Tipo_Padrao")));
      padrao_ed.setCd_tipo(request.getParameter("FT_CD_Tipo"));
      padrao_ed.setNm_descricao_tipo(request.getParameter("FT_NM_Descricao"));
      padrao_ed.setUsuario_stamp_tipo(request.getParameter("FT_NM_Usuario"));
      padrao_ed.setDt_stamp_tipo(dt_stamp.getDataDMY());
      padrao_ed.setOid_tipo_padrao(Long.parseLong(request.getParameter("oid_Tipo_Padrao")));
      //// System.out.println(padrao_ed.getOid_tipo_padrao());

      Padrao_EDIrn.updateTipo(padrao_ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar o Padrao");
      excecoes.setMetodo("updateTipo");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }
}