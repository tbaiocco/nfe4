package com.master.iu;
/**
 * <p>Title: fro024Bean </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */
import javax.servlet.http.*;
import com.master.rn.Tipo_AcessorioRN;
import com.master.ed.Tipo_AcessorioED;
import com.master.util.Excecoes;
import java.util.*;
import com.master.root.FormataDataBean;
import com.master.util.Data;

public class fro024Bean {

    public Tipo_AcessorioED inclui(HttpServletRequest request)throws Excecoes{

        try{
          Tipo_AcessorioRN Tipo_AcessorioRN = new Tipo_AcessorioRN();
          Tipo_AcessorioED ed = new Tipo_AcessorioED();
          String  oid_Tipo_Acessorio = request.getParameter("oid_Tipo_Acessorio");
          if((oid_Tipo_Acessorio != null) && (!oid_Tipo_Acessorio.equals("")) && (!oid_Tipo_Acessorio.equals("null")))
             ed.setOid_Tipo_Acessorio(Long.parseLong(oid_Tipo_Acessorio));
          String  nm_Tipo_Acessorio = request.getParameter("FT_NM_Tipo_Acessorio");
          if((nm_Tipo_Acessorio != null) && (!nm_Tipo_Acessorio.equals("")) && (!nm_Tipo_Acessorio.equals("null")))
             ed.setNM_Tipo_Acessorio(nm_Tipo_Acessorio);

          String  DM_Tipo_Acessorio = request.getParameter("FT_DM_Tipo_Acessorio");
          if((DM_Tipo_Acessorio != null) && (!DM_Tipo_Acessorio.equals("")) && (!DM_Tipo_Acessorio.equals("null")))
             ed.setDM_Tipo_Acessorio(DM_Tipo_Acessorio);

          ed.setDt_stamp(Data.getDataDMY());
          ed.setDm_Stamp("S");
          return new Tipo_AcessorioRN().inclui(ed);
        }
        catch (Excecoes exc){
          throw exc;
        }
        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("erro ao incluir");
          excecoes.setMetodo("inclui");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

      public void altera(HttpServletRequest request)throws Excecoes{

        try{
          Tipo_AcessorioRN Tipo_AcessorioRN = new Tipo_AcessorioRN();
          Tipo_AcessorioED ed = new Tipo_AcessorioED();

          String  oid_Tipo_Acessorio = request.getParameter("oid_Tipo_Acessorio");
          if((oid_Tipo_Acessorio != null) && (!oid_Tipo_Acessorio.equals("")) && (!oid_Tipo_Acessorio.equals("null")))
             ed.setOid_Tipo_Acessorio(Long.parseLong(oid_Tipo_Acessorio));

          String  DM_Tipo_Acessorio = request.getParameter("FT_DM_Tipo_Acessorio");
          if((DM_Tipo_Acessorio != null) && (!DM_Tipo_Acessorio.equals("")) && (!DM_Tipo_Acessorio.equals("null")))
             ed.setDM_Tipo_Acessorio(DM_Tipo_Acessorio);

          String  oid_Estoque = request.getParameter("oid_Estoque");
          if((oid_Estoque != null) && (!oid_Estoque.equals("")) && (!oid_Estoque.equals("null")))
             ed.setOid_Estoque(Long.parseLong(oid_Estoque));

          String  nm_Tipo_Acessorio = request.getParameter("FT_NM_Tipo_Acessorio");
          if((nm_Tipo_Acessorio != null) && (!nm_Tipo_Acessorio.equals("")) && (!nm_Tipo_Acessorio.equals("null")))
             ed.setNM_Tipo_Acessorio(nm_Tipo_Acessorio);

          ed.setDt_stamp(Data.getDataDMY());
          ed.setDm_Stamp(" ");

          Tipo_AcessorioRN.altera(ed);
        }
        catch (Excecoes exc){
          throw exc;
        }
        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("erro ao alterar");
          excecoes.setMetodo("alterar");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

      public void deleta(HttpServletRequest request)throws Excecoes{

        try{
          Tipo_AcessorioRN Tipo_AcessorioRN = new Tipo_AcessorioRN();
          Tipo_AcessorioED ed = new Tipo_AcessorioED();
          String  oid_Tipo_Acessorio = request.getParameter("oid_Tipo_Acessorio");
          ed.setOid_Tipo_Acessorio(Long.parseLong(oid_Tipo_Acessorio));
          Tipo_AcessorioRN.deleta(ed);
        }
        catch (Excecoes exc){
          throw exc;
        }
        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("erro ao excluir");
          excecoes.setMetodo("excluir");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

      public Tipo_AcessorioED getByRecord(HttpServletRequest request)throws Excecoes{

          Tipo_AcessorioED ed = new Tipo_AcessorioED();

          String  oid_Tipo_Acessorio = request.getParameter("oid_Tipo_Acessorio");
          if((oid_Tipo_Acessorio != null) && (!oid_Tipo_Acessorio.equals("")) && (!oid_Tipo_Acessorio.equals("null")))
             ed.setOid_Tipo_Acessorio(Long.parseLong(oid_Tipo_Acessorio));

          return new Tipo_AcessorioRN().getByRecord(ed);

      }

        public ArrayList Lista(HttpServletRequest request)throws Excecoes{

          Tipo_AcessorioED ed = new Tipo_AcessorioED();

          String  oid_Tipo_Acessorio = request.getParameter("oid_Tipo_Acessorio");
          if((oid_Tipo_Acessorio != null) && (!oid_Tipo_Acessorio.equals("")) && (!oid_Tipo_Acessorio.equals("null")))
             ed.setOid_Tipo_Acessorio(Long.parseLong(oid_Tipo_Acessorio));
          String  nm_Tipo_Acessorio = request.getParameter("FT_NM_Tipo_Acessorio");
          if((nm_Tipo_Acessorio != null) && (!nm_Tipo_Acessorio.equals("")) && (!nm_Tipo_Acessorio.equals("null")))
             ed.setNM_Tipo_Acessorio(nm_Tipo_Acessorio);

          String  DM_Tipo_Acessorio = request.getParameter("FT_DM_Tipo_Acessorio");
          if((DM_Tipo_Acessorio != null) && (!DM_Tipo_Acessorio.equals("")) && (!DM_Tipo_Acessorio.equals("null")))
             ed.setDM_Tipo_Acessorio(DM_Tipo_Acessorio);

          return new Tipo_AcessorioRN().lista(ed);
      }


  }
