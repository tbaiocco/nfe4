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
import com.master.rn.Peca_VeiculoRN;
import com.master.ed.Peca_VeiculoED;
import com.master.util.Excecoes;
import java.util.*;
import com.master.root.FormataDataBean;
import com.master.util.Data;

public class fro027Bean {



        public Peca_VeiculoED inclui(HttpServletRequest request)throws Excecoes{

            try{
              Peca_VeiculoRN Peca_VeiculoRN = new Peca_VeiculoRN();
              Peca_VeiculoED ed = new Peca_VeiculoED();


	      String  oid_Tipo_Acessorio = request.getParameter("oid_Tipo_Acessorio");
	      if((oid_Tipo_Acessorio != null) && (!oid_Tipo_Acessorio.equals("")) && (!oid_Tipo_Acessorio.equals("null")))
		 ed.setOid_Tipo_Acessorio(Long.parseLong(oid_Tipo_Acessorio));

	      String  oid_Movimento_Ordem_Servico = request.getParameter("oid_Movimento_Ordem_Servico");
	      if((oid_Movimento_Ordem_Servico != null) && (!oid_Movimento_Ordem_Servico.equals("")) && (!oid_Movimento_Ordem_Servico.equals("null")))
		 ed.setOid_Movimento_Ordem_Servico(Long.parseLong(oid_Movimento_Ordem_Servico));

               ed.setDT_Garantia(request.getParameter("FT_DT_Garantia"));

               ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

              String  oid_Veiculo = request.getParameter("FT_NR_Placa");
              if((oid_Veiculo != null) && (!oid_Veiculo.equals("")) && (!oid_Veiculo.equals("null")))
                 ed.setOid_Veiculo(oid_Veiculo);

              String vl_Quantidade = request.getParameter("FT_VL_Quantidade");
              if(vl_Quantidade != null)
                  ed.setVL_Quantidade(Double.parseDouble(vl_Quantidade));

                String KM_Garantia = request.getParameter("FT_KM_Garantia");
                if(KM_Garantia != null)
                    ed.setKM_Garantia(Long.parseLong(KM_Garantia));


              ed.setOid_Peca_Veiculo(oid_Tipo_Acessorio+oid_Veiculo+oid_Movimento_Ordem_Servico);

              ed.setDt_stamp(Data.getDataDMY());
              ed.setDm_Stamp("S");
              return new Peca_VeiculoRN().inclui(ed);
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
              Peca_VeiculoRN Peca_VeiculoRN = new Peca_VeiculoRN();
              Peca_VeiculoED ed = new Peca_VeiculoED();
              String  oid_Peca_Veiculo = request.getParameter("oid_Peca_Veiculo");
              if((oid_Peca_Veiculo != null) && (!oid_Peca_Veiculo.equals("")) && (!oid_Peca_Veiculo.equals("null")))
                 ed.setOid_Peca_Veiculo(oid_Peca_Veiculo);

               ed.setDT_Garantia(request.getParameter("FT_DT_Garantia"));

               ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

              String vl_Quantidade = request.getParameter("FT_VL_Quantidade");
              if(vl_Quantidade != null)
                  ed.setVL_Quantidade(Double.parseDouble(vl_Quantidade));

                String KM_Garantia = request.getParameter("FT_KM_Garantia");
                if(KM_Garantia != null)
                    ed.setKM_Garantia(Long.parseLong(KM_Garantia));

              Peca_VeiculoRN.altera(ed);
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
              Peca_VeiculoRN Peca_VeiculoRN = new Peca_VeiculoRN();
              Peca_VeiculoED ed = new Peca_VeiculoED();
              String  oid_Peca_Veiculo = request.getParameter("oid_Peca_Veiculo");
              ed.setOid_Peca_Veiculo(oid_Peca_Veiculo);
              Peca_VeiculoRN.deleta(ed);
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

          public Peca_VeiculoED getByRecord(HttpServletRequest request)throws Excecoes{

              Peca_VeiculoED ed = new Peca_VeiculoED();

              String  oid_Peca_Veiculo = request.getParameter("oid_Peca_Veiculo");

              // System.out.println("getByRecord iu->>" + request.getParameter("oid_Peca_Veiculo"));


              if((oid_Peca_Veiculo != null) && (!oid_Peca_Veiculo.equals("")) && (!oid_Peca_Veiculo.equals("null")))
                 ed.setOid_Peca_Veiculo(oid_Peca_Veiculo);

              return new Peca_VeiculoRN().getByRecord(ed);

          }

            public ArrayList Lista(HttpServletRequest request)throws Excecoes{


	    // System.out.println("Lista Acessorio iu->>" + request.getParameter("oid_Movimento_Ordem_Servico"));
              Peca_VeiculoED ed = new Peca_VeiculoED();

              String  oid_Movimento_Ordem_Servico = request.getParameter("oid_Movimento_Ordem_Servico");
              if((oid_Movimento_Ordem_Servico != null) && (!oid_Movimento_Ordem_Servico.equals("")) && (!oid_Movimento_Ordem_Servico.equals("null")))
                 ed.setOid_Movimento_Ordem_Servico(Long.parseLong(oid_Movimento_Ordem_Servico));

              String  oid_Veiculo = request.getParameter("oid_Veiculo");
              if((oid_Veiculo != null) && (!oid_Veiculo.equals("")) && (!oid_Veiculo.equals("null")))
                 ed.setOid_Veiculo(oid_Veiculo);

              return new Peca_VeiculoRN().lista(ed);
          }


  }
