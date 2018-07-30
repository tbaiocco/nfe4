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
import com.master.rn.Acessorio_VeiculoRN;
import com.master.ed.Acessorio_VeiculoED;
import com.master.util.Excecoes;
import java.util.*;
import com.master.root.FormataDataBean;
import com.master.util.Data;

public class fro025Bean {



        public Acessorio_VeiculoED inclui(HttpServletRequest request)throws Excecoes{

            try{
              Acessorio_VeiculoRN Acessorio_VeiculoRN = new Acessorio_VeiculoRN();
              Acessorio_VeiculoED ed = new Acessorio_VeiculoED();


	      String  oid_Tipo_Acessorio = request.getParameter("oid_Tipo_Acessorio");
	      if((oid_Tipo_Acessorio != null) && (!oid_Tipo_Acessorio.equals("")) && (!oid_Tipo_Acessorio.equals("null")))
		 ed.setOid_Tipo_Acessorio(Long.parseLong(oid_Tipo_Acessorio));

              String  oid_Veiculo = request.getParameter("oid_Veiculo");
              if((oid_Veiculo != null) && (!oid_Veiculo.equals("")) && (!oid_Veiculo.equals("null")))
                 ed.setOid_Veiculo(oid_Veiculo);

              String vl_Quantidade = request.getParameter("FT_VL_Quantidade");
              if(vl_Quantidade != null)
                  ed.setVL_Quantidade(Double.parseDouble(vl_Quantidade));

              ed.setOid_Acessorio_Veiculo(oid_Tipo_Acessorio+oid_Veiculo);

              ed.setDt_stamp(Data.getDataDMY());
              ed.setDm_Stamp("S");
              return new Acessorio_VeiculoRN().inclui(ed);
            }
            catch (Excecoes exc){
              throw exc;
            }
            catch(Exception exc){
              Excecoes excecoes = new Excecoes();
              excecoes.setClasse(this.getClass().getName());
              excecoes.setMensagem("erro ao incluir");
              excecoes.setMetodo("inclui_VeiculoxAcessorio");
              excecoes.setExc(exc);
              throw excecoes;
            }
          }

        public void altera(HttpServletRequest request)throws Excecoes{

            try{
              Acessorio_VeiculoRN Acessorio_VeiculoRN = new Acessorio_VeiculoRN();
              Acessorio_VeiculoED ed = new Acessorio_VeiculoED();
              String  oid_Acessorio_Veiculo = request.getParameter("oid_Acessorio_Veiculo");
              if((oid_Acessorio_Veiculo != null) && (!oid_Acessorio_Veiculo.equals("")) && (!oid_Acessorio_Veiculo.equals("null")))
                 ed.setOid_Acessorio_Veiculo(oid_Acessorio_Veiculo);

              String vl_Quantidade = request.getParameter("FT_VL_Quantidade");
              if(vl_Quantidade != null)
                  ed.setVL_Quantidade(Double.parseDouble(vl_Quantidade));

              Acessorio_VeiculoRN.altera(ed);
            }
            catch (Excecoes exc){
              throw exc;
            }
            catch(Exception exc){
                exc.printStackTrace();
              Excecoes excecoes = new Excecoes();
              excecoes.setClasse(this.getClass().getName());
              excecoes.setMensagem("erro ao incluir");
              excecoes.setMetodo("altera_VeiculoxAcessorio");
              excecoes.setExc(exc);
              throw excecoes;
            }
          }

        public void deleta(HttpServletRequest request)throws Excecoes{

            try{
              Acessorio_VeiculoRN Acessorio_VeiculoRN = new Acessorio_VeiculoRN();
              Acessorio_VeiculoED ed = new Acessorio_VeiculoED();
              String  oid_Acessorio_Veiculo = request.getParameter("oid_Acessorio_Veiculo");
              ed.setOid_Acessorio_Veiculo(oid_Acessorio_Veiculo);
              Acessorio_VeiculoRN.deleta(ed);
            }
            catch (Excecoes exc){
              throw exc;
            }
            catch(Exception exc){
              Excecoes excecoes = new Excecoes();
              excecoes.setClasse(this.getClass().getName());
              excecoes.setMensagem("erro ao excluir");
              excecoes.setMetodo("excluir_VeiculoxAcessorio");
              excecoes.setExc(exc);
              throw excecoes;
            }
          }

          public Acessorio_VeiculoED getByRecord(HttpServletRequest request)throws Excecoes{

              Acessorio_VeiculoED ed = new Acessorio_VeiculoED();

              String  oid_Acessorio_Veiculo = request.getParameter("oid_Acessorio_Veiculo");
              if((oid_Acessorio_Veiculo != null) && (!oid_Acessorio_Veiculo.equals("")) && (!oid_Acessorio_Veiculo.equals("null")))
                 ed.setOid_Acessorio_Veiculo(oid_Acessorio_Veiculo);

              return new Acessorio_VeiculoRN().getByRecord(ed);

          }

            public ArrayList Lista(HttpServletRequest request)throws Excecoes{


	    // System.out.println("Lista Acessorio iu");
              Acessorio_VeiculoED ed = new Acessorio_VeiculoED();

              String  oid_Acessorio_Veiculo = request.getParameter("oid_Acessorio_Veiculo");
              if((oid_Acessorio_Veiculo != null) && (!oid_Acessorio_Veiculo.equals("")) && (!oid_Acessorio_Veiculo.equals("null")))
                 ed.setOid_Acessorio_Veiculo(oid_Acessorio_Veiculo);

              String  oid_Veiculo = request.getParameter("oid_Veiculo");
              if((oid_Veiculo != null) && (!oid_Veiculo.equals("")) && (!oid_Veiculo.equals("null")))
                 ed.setOid_Veiculo(oid_Veiculo);

              String  DM_Tipo_Acessorio = request.getParameter("FT_DM_Tipo_Acessorio");
              if((DM_Tipo_Acessorio != null) && (!DM_Tipo_Acessorio.equals("")) && (!DM_Tipo_Acessorio.equals("null")))
                 ed.setDM_Tipo_Acessorio(DM_Tipo_Acessorio);

              return new Acessorio_VeiculoRN().lista(ed);
          }


  }
