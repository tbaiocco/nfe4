package com.master.iu;

/**
 * Título: est023Bean
 * Descrição: Itens da Requisição de Materiais - Bean
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
*/

import javax.servlet.http.*;
import com.master.rn.Item_Requisicao_CompraRN;
import com.master.ed.Requisicao_CompraED;
import com.master.util.Excecoes;
import com.master.util.Data;
import java.util.*;

public class est023Bean {

  public Requisicao_CompraED inclui(HttpServletRequest request)throws Excecoes{

    try{
        Item_Requisicao_CompraRN Item_Requisicao_CompraRN = new Item_Requisicao_CompraRN();
        Requisicao_CompraED ed = new Requisicao_CompraED();

        String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
		{
			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
		}
        String oid_Requisicao_Compra = request.getParameter("oid_Requisicao_Compra");
		if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length() > 0)
		{
			ed.setOid_requisicao_compra(Long.parseLong(oid_Requisicao_Compra));
		}
		String oid_Estoque = request.getParameter("oid_Estoque");
		if (oid_Estoque != null && oid_Estoque.length() > 0)
		{
			ed.setOid_estoque(Long.parseLong(oid_Estoque));
		}
		String Valor = request.getParameter("FT_VL_Quantidade");
		if (Valor != null && Valor.length() > 0)
		{
			ed.setVl_quantidade(Double.parseDouble(Valor));
		}
		String DM_Status = request.getParameter("FT_DM_Status");
		if (DM_Status != null && DM_Status.length() > 0)
		{
			ed.setDm_status_item(DM_Status);
		}

		ed.setDt_stamp(Data.getDataDMY());

		String NM_Obs = request.getParameter("FT_TX_Observacoes");
		if (NM_Obs != null && NM_Obs.length() > 0)
		{
			ed.setNm_observacoes(NM_Obs);
		}
		String NR_Prazo_Entrega = request.getParameter("FT_NR_Prazo_Entrega");
		if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length() > 0)
		{
			ed.setNr_prazo_entrega(Long.parseLong(NR_Prazo_Entrega));
		}
		String NR_Contrato = request.getParameter("FT_NR_Contrato");
		if (NR_Contrato != null && NR_Contrato.length() > 0)
		{
			ed.setNr_contrato(NR_Contrato);
		}

      return Item_Requisicao_CompraRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
        Item_Requisicao_CompraRN Item_Requisicao_CompraRN = new Item_Requisicao_CompraRN();
        Requisicao_CompraED ed = new Requisicao_CompraED();

        String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
		{
			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
		}
        String oid_Requisicao_Compra = request.getParameter("oid_Requisicao_Compra");
		if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length() > 0)
		{
			ed.setOid_requisicao_compra(Long.parseLong(oid_Requisicao_Compra));
		}
		String oid_Estoque = request.getParameter("oid_Estoque");
		if (oid_Estoque != null && oid_Estoque.length() > 0)
		{
			ed.setOid_estoque(Long.parseLong(oid_Estoque));
		}
		String Valor = request.getParameter("FT_VL_Quantidade");
		if (Valor != null && Valor.length() > 0)
		{
			ed.setVl_quantidade(Double.parseDouble(Valor));
		}
		String DM_Status = request.getParameter("FT_DM_Status");
		if (DM_Status != null && DM_Status.length() > 0)
		{
			ed.setDm_status_item(DM_Status);
		}

		String DT_Entrega = request.getParameter("FT_DT_Entrega");
		if (DT_Entrega != null && DT_Entrega.length() > 0)
		{
			ed.setDt_entrega(DT_Entrega);
		}
		ed.setDt_stamp(Data.getDataDMY());

		String NM_Obs = request.getParameter("FT_TX_Observacoes");
		if (NM_Obs != null && NM_Obs.length() > 0)
		{
			ed.setNm_observacoes(NM_Obs);
		}
		String NR_Prazo_Entrega = request.getParameter("FT_NR_Prazo_Entrega");
		if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length() > 0)
		{
			ed.setNr_prazo_entrega(Long.parseLong(NR_Prazo_Entrega));
		}
		String NR_Contrato = request.getParameter("FT_NR_Contrato");
		if (NR_Contrato != null && NR_Contrato.length() > 0)
		{
			ed.setNr_contrato(NR_Contrato);
		}

		Item_Requisicao_CompraRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
        Item_Requisicao_CompraRN Item_Requisicao_CompraRN = new Item_Requisicao_CompraRN();
        Requisicao_CompraED ed = new Requisicao_CompraED();

        String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
		{
			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
		}

		Item_Requisicao_CompraRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void cancela(HttpServletRequest request)throws Excecoes{

      try{
          Item_Requisicao_CompraRN Item_Requisicao_CompraRN = new Item_Requisicao_CompraRN();
          Requisicao_CompraED ed = new Requisicao_CompraED();

          String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
  		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
  		{
  			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
  		}

  		Item_Requisicao_CompraRN.cancela(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
    }

  public void finaliza(HttpServletRequest request)throws Excecoes{

      try{
          Item_Requisicao_CompraRN Item_Requisicao_CompraRN = new Item_Requisicao_CompraRN();
          Requisicao_CompraED ed = new Requisicao_CompraED();

          String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
  		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
  		{
  			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
  		}

  		Item_Requisicao_CompraRN.finaliza(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
    }

  public void entrega(HttpServletRequest request)throws Excecoes{

      try{
          Item_Requisicao_CompraRN Item_Requisicao_CompraRN = new Item_Requisicao_CompraRN();
          Requisicao_CompraED ed = new Requisicao_CompraED();

          String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
  		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
  		{
  			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
  		}
  		String DT_Entrega = request.getParameter("FT_DT_Entrega");
		if (DT_Entrega != null && DT_Entrega.length() > 0)
		{
			ed.setDt_entrega(DT_Entrega);
		} else ed.setDt_entrega(Data.getDataDMY());

  		Item_Requisicao_CompraRN.cancela(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
    }

  public Requisicao_CompraED getByRecord(HttpServletRequest request)throws Excecoes{

      Requisicao_CompraED ed = new Requisicao_CompraED();

      String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
		{
			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
		}

     return new Item_Requisicao_CompraRN().getByRecord(ed);

  }

  public ArrayList Lista(HttpServletRequest request)throws Excecoes{

      Requisicao_CompraED ed = new Requisicao_CompraED();

		String oid_Requisicao_Compra = request.getParameter("oid_Requisicao_Compra");
		if (oid_Requisicao_Compra != null && oid_Requisicao_Compra.length() > 0)
		{
			ed.setOid_requisicao_compra(Long.parseLong(oid_Requisicao_Compra));
		}
		String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
		{
			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
		}

     return new Item_Requisicao_CompraRN().Lista(ed);

  }

  public void altera_Qtde_Entrega(HttpServletRequest request)throws Excecoes{

      try{
          Item_Requisicao_CompraRN Item_Requisicao_CompraRN = new Item_Requisicao_CompraRN();
          Requisicao_CompraED ed = new Requisicao_CompraED();

          String oid_Item_Requisicao_Compra = request.getParameter("oid_Item_Requisicao_Compra");
  		if (oid_Item_Requisicao_Compra != null && oid_Item_Requisicao_Compra.length() > 0)
  		{
  			ed.setOid_item_requisicao_compra(Long.parseLong(oid_Item_Requisicao_Compra));
  		}

  		String Valor = request.getParameter("FT_VL_Quantidade");
  		if (Valor != null && Valor.length() > 0)
  		{
  			ed.setVl_quantidade_entrega(Double.parseDouble(Valor));
  		}

  		Item_Requisicao_CompraRN.altera_Qtde_Entrega(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
    }

}
