package com.master.bd;

/**
 * Título: WMS_Nota_Fiscal_ClientesBD
 * Descrição: Notas Fiscais Clientes - BD
 * Data da criação: 03/2004
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Movimento_Produto_ClienteED;
import com.master.ed.WMS_EstoqueED;
import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.ed.WMS_Movimentos_ProdutosED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.ed.WMS_Requisicoes_ProdutosED;
import com.master.iu.wms020Bean;
import com.master.rl.WMS_Nota_FiscalRL;
import com.master.rn.WMS_Movimentos_ProdutosRN;
import com.master.rn.WMS_Requisicoes_ProdutosRN;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;


public class WMS_Nota_Fiscal_ClientesBD extends BancoUtil {

  private ExecutaSQL executasql;

  public WMS_Nota_Fiscal_ClientesBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_Nota_FiscalED inclui(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;
    String chave = null;
    chave = String.valueOf(ed.getOid_cliente()).trim() + String.valueOf(ed.getNr_nota_fiscal()).trim() + ed.getNm_serie().trim();
    ed.setOid_nota_fiscal(chave);           
    ed.setNr_volumes(0);
    ed.setDM_Gerado("N");
    ed.setDM_Frete("N");
    ed.setDm_finalizado("N");
    
    WMS_Nota_FiscalED conED = new WMS_Nota_FiscalED();
    try{
      sql = "Insert Into notas_fiscais_wms (" +
			"oid_nota_fiscal, " +
			"nr_nota_fiscal, " +
			"nr_volumes, " +
			"dt_emissao, "+
			"dt_entrada, " +
			"hr_entrada, " +
			"oid_pessoa, " +
			"oid_cliente, " +
			"oid_pessoa_destinatario, " +
			"oid_pessoa_transportador, " +
			"oid_natureza_operacao, " +
			"oid_modelo_nota_fiscal, " +
			"vl_total_frete, "+
			"vl_icms, " +
			"vl_servico, " +
			"vl_inss, " +
			"vl_irrf, " +
			"vl_ipi, " +
			"vl_isqn, " +
			"vl_total_seguro, " +
			"vl_total_despesas, " +
			"vl_nota_fiscal, " +
			"vl_liquido_nota_fiscal, "+
			"dm_tipo_nota_fiscal, " +
			"dt_stamp, " +
			"dm_observacao, " +
			"vl_descontos, " +
			"dm_finalizado, "+
			"oid_Empresa, " +
			"VL_Base_Calculo_ICMS," +
			"VL_Base_Calculo_ICMS_Substituicao," +
			"VL_ICMS_Substituicao," +
			"VL_Total_Produtos," +
			"NR_Quantidade," +
			"DM_Frete," +
			"NM_Especie, "+
			"NM_Marca," +
			"NR_Placa," +
			"NR_Numero," +
			"NR_Peso_Bruto," +
			"NR_Peso_Liquido," +
			"nm_serie, " +
			"oid_unidade_emissora, " +
			"oid_unidade_fiscal, " +
			"DM_Gerado, " +
			"dm_Devolvido, " +
            ") values ";
      sql += "('" + ed.getOid_nota_fiscal()+"',"+ 
			ed.getNr_nota_fiscal()+ ", "+
			ed.getNr_volumes()+", '"+
			ed.getDt_emissao()+"','"+
			ed.getDt_entrada()+"','"+
			ed.getHr_entrada()+"', '"+
			ed.getOid_pessoa()+"', '"+
			ed.getOid_cliente()+"', '"+
			ed.getOid_pessoa_destinatario()+"', '"+
			ed.getOid_pessoa_transportador()+"', "+
			ed.getOid_natureza_operacao()+", "+
			ed.getOid_modelo_nota_fiscal()+", "+
			ed.getVl_total_frete()+", "+
			ed.getVl_icms()+", "+
			ed.getVL_Servico()+","+
			ed.getVl_inss()+", "+
			ed.getVl_irrf()+", "+
			ed.getVl_ipi()+", "+
			ed.getVl_isqn()+", "+
			ed.getVl_total_seguro()+", "+
			ed.getVl_total_despesas()+", "+
			ed.getVl_nota_fiscal()+", "+
			ed.getVl_liquido_nota_fiscal()+", '"+
			ed.getDm_tipo_nota_fiscal()+"', '"+
			ed.getDt_stamp()+"', UPPER('"+
			ed.getDm_observacao()+"'), "+
			ed.getVl_descontos()+", '"+ 
			ed.getDm_finalizado()+"', "+
			ed.getOID_Empresa()+", "+ 
			ed.getVL_Base_Calculo_ICMS()+", "+ 
			ed.getVL_Base_Calculo_ICMS_Substituicao()+", "+ 
			ed.getVL_ICMS_Substituicao()+", "+
			ed.getVL_Total_Produtos()+", "+ 
			ed.getNR_Quantidade()+", '"+
			ed.getDM_Frete()+"', '"+
			ed.getNM_Especie()+"', '"+
			ed.getNM_Marca()+"', '"+
			ed.getNR_Placa()+"', '"+
			ed.getNR_Numero()+"', "+
			ed.getNR_Peso_Bruto()+", "+
			ed.getNR_Peso_Liquido() +" , '"+
			ed.getNm_serie()+"', "+
			ed.getOID_Unidade()+", "+
			ed.getOID_Unidade()+", '"+
			ed.getDM_Gerado()+"'," + 
			"' ')";
      executasql.executarUpdate(sql);
      
      conED.setOid_nota_fiscal(ed.getOid_nota_fiscal());
      conED.setNr_nota_fiscal(ed.getNr_nota_fiscal());    
      conED.setNr_volumes(ed.getNr_volumes());    
      conED.setOid_natureza_operacao(ed.getOid_natureza_operacao());
      conED.setOid_modelo_nota_fiscal(ed.getOid_modelo_nota_fiscal());
      conED.setVl_total_frete(ed.getVl_total_frete());
      conED.setVl_icms(ed.getVl_icms());
      conED.setVl_inss(ed.getVl_inss());
      conED.setVl_irrf(ed.getVl_irrf());
      conED.setVl_ipi(ed.getVl_ipi());
      conED.setVl_isqn(ed.getVl_isqn());
      conED.setVl_total_seguro(ed.getVl_total_seguro());
      conED.setVl_total_despesas(ed.getVl_total_despesas());
      conED.setVl_nota_fiscal(ed.getVl_nota_fiscal());
      conED.setVl_liquido_nota_fiscal(ed.getVl_liquido_nota_fiscal());
      conED.setDm_tipo_nota_fiscal(ed.getDm_tipo_nota_fiscal());
      conED.setDm_observacao(ed.getDm_observacao());     
      conED.setVl_descontos(ed.getVl_descontos());
      conED.setDm_finalizado("N");
      conED.setOID_Unidade(ed.getOID_Unidade());
      conED.setOID_Empresa(ed.getOID_Empresa());
      conED.setVL_Base_Calculo_ICMS(ed.getVL_Base_Calculo_ICMS());
      conED.setVL_Base_Calculo_ICMS_Substituicao(ed.getVL_Base_Calculo_ICMS_Substituicao());
      conED.setVL_ICMS_Substituicao(ed.getVL_ICMS_Substituicao());
      conED.setVL_Total_Produtos(ed.getVL_Total_Produtos());
      conED.setNR_Quantidade(ed.getNR_Quantidade());
      conED.setDM_Frete(ed.getDM_Frete());
      conED.setNM_Especie(ed.getNM_Especie());
      conED.setNM_Marca(ed.getNM_Marca());
      conED.setNR_Placa(ed.getNR_Placa());
      conED.setNR_Numero(ed.getNR_Numero());
      conED.setNR_Peso_Bruto(ed.getNR_Peso_Bruto());
      conED.setNR_Peso_Liquido(ed.getNR_Peso_Liquido());
    }

    catch(Exception exc){
        exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return conED;
  }

  public void altera(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;

    try{
    	if (doValida(ed.getDm_finalizado())) {
			if ("S".equals(ed.getDm_finalizado())) {
				// Atulizar estoque
			}
    	}
        sql="UPDATE notas_fiscais_wms SET ";
		if (doValida(ed.getDm_finalizado())) 
			sql+="dm_finalizado = '"+  ed.getDm_finalizado() +"', ";
        if (doValida(ed.getDM_Frete()))
            sql+="DM_Frete = '" + ed.getDM_Frete()+"', ";
	    if (doValida(ed.getDM_Gerado()))
			sql+="DM_Gerado = '" + ed.getDM_Gerado()+"', ";             
	   sql+="nr_nota_fiscal = "+ed.getNr_nota_fiscal()+", "+             
            "nr_volumes = "+ed.getNr_volumes()+", "+
            "dt_emissao = '"+ed.getDt_emissao()+"', "+
            "dt_entrada = '"+ed.getDt_entrada()+"', "+
            "hr_entrada = '"+ed.getHr_entrada()+"', "+
			"oid_pessoa = '"+ed.getOid_pessoa()+"', "+
			"oid_cliente = '"+ed.getOid_cliente()+"', "+
			"oid_pessoa_destinatario = '" + ed.getOid_pessoa_destinatario()+"', "+
			"oid_pessoa_transportador = '" + ed.getOid_pessoa_transportador()+"', "+
			"oid_natureza_operacao = " + ed.getOid_natureza_operacao()+", "+
			"oid_modelo_nota_fiscal = " + ed.getOid_modelo_nota_fiscal()+", "+
			"vl_total_frete = "+ ed.getVl_total_frete()+", "+
			"vl_icms = " + ed.getVl_icms()+", "+
			"vl_servico = " + ed.getVL_Servico()+","+
			"vl_inss = " + ed.getVl_inss()+", "+
			"vl_irrf = " + ed.getVl_irrf()+", "+
			"vl_ipi = " + ed.getVl_ipi()+", "+
			"vl_isqn = " + ed.getVl_isqn()+", "+
			"vl_total_seguro = " + ed.getVl_total_seguro()+", "+
			"vl_total_despesas = " + ed.getVl_total_despesas()+", "+
			"vl_nota_fiscal = " + ed.getVl_nota_fiscal()+", "+
			"vl_liquido_nota_fiscal = "+ ed.getVl_liquido_nota_fiscal()+", "+
			"dm_tipo_nota_fiscal = '" + ed.getDm_tipo_nota_fiscal()+"', "+ 
			"dt_stamp = '" + ed.getDt_stamp()+"', "+ 
			"dm_observacao = UPPER('"+ed.getDm_observacao()+"'), "+
			"vl_descontos = " + ed.getVl_descontos()+", "+
	        "oid_Empresa = " + ed.getOID_Empresa()+", "+ 
			"VL_Base_Calculo_ICMS = " + ed.getVL_Base_Calculo_ICMS()+", "+ 
			"VL_Base_Calculo_ICMS_Substituicao = " + ed.getVL_Base_Calculo_ICMS_Substituicao()+", "+ 
			"VL_ICMS_Substituicao = " + ed.getVL_ICMS_Substituicao()+", "+
			"VL_Total_Produtos = " + ed.getVL_Total_Produtos()+", "+ 
			"NR_Quantidade = " + ed.getNR_Quantidade()+", "+
			"NM_Especie = '" + ed.getNM_Especie()+"', "+
			"NM_Marca = '" + ed.getNM_Marca()+"', "+
			"NR_Placa = '" + ed.getNR_Placa()+"', "+
			"NR_Numero = " + ed.getNR_Numero()+", "+
			"NR_Peso_Bruto = " + ed.getNR_Peso_Bruto()+", "+
			"NR_Peso_Liquido = " + ed.getNR_Peso_Liquido() +", "+
			"nm_serie = '" + ed.getNm_serie()+"', "+ 
			"oid_unidade_emissora  = " + ed.getOID_Unidade()+", "+
			"oid_unidade_fiscal = " + ed.getOID_Unidade()+" "+
	        "WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";

        	executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("altera");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void finaliza(WMS_Nota_FiscalED ed) throws Excecoes{
	  String sql = null;
	  try {
		  
		  sql="UPDATE notas_fiscais_wms SET " +
		      "dm_finalizado = 'S' "+
		      "WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal()+"'";
		  executasql.executarUpdate(sql);
		  if ("E".equals(ed.getDm_tipo_nota_fiscal()) ) {
			  //new WMS_EstoqueBD(executasql).entradaEstoqueByNota(ed);
		  }else
		  if ("S".equals(ed.getDm_tipo_nota_fiscal()) ) {
			 // new WMS_EstoqueBD(executasql).saidaEstoqueByNota(ed);
		  }			  
	  }
	  catch(Exception exc){
		  Excecoes excecoes = new Excecoes();
		  excecoes.setClasse(this.getClass().getName());
		  excecoes.setMensagem("Erro ao finalizar");
		  excecoes.setMetodo("finaliza");
		  excecoes.setExc(exc);
		  throw excecoes;
	  }
  }

  public void deleta(WMS_Nota_FiscalED ed) throws Excecoes{
	  ArrayList lstItensNF = new ArrayList();
	  String sql = null;  
	  String Oid_Estoque_Cliente = new String(); 
	  try{    
		  if ("S".equals(ed.getDm_finalizado()) && !"D".equals(ed.getDm_tipo_nota_fiscal()) ) {
			  // Intera os itens e retira/devolve do/ao estoque e gera movimento
			  WMS_Item_Produto_ClienteED ipcED = new WMS_Item_Produto_ClienteED();
			  ipcED.setOid_Nota_Fiscal(ed.getOid_nota_fiscal());
			  lstItensNF = new WMS_Item_Produto_ClienteBD(executasql).lista(ipcED,"");
			  for (int i=0; i<lstItensNF.size(); i++) {
				  ipcED = (WMS_Item_Produto_ClienteED)lstItensNF.get(i);
				  //*** Monta a chave do estoque e diminui/aumenta do/o estoque 
				  Oid_Estoque_Cliente = ipcED.getOid_Produto_Cliente() + ipcED.getOid_Localizacao() + ipcED.getOid_Tipo_Estoque();
				  if ("E".equals(ed.getDm_tipo_nota_fiscal())){
					  new WMS_EstoqueBD(executasql).subtrai(Oid_Estoque_Cliente,ipcED.getNr_Quantidade_Movimento(),true);
				  } else
				  if ("S".equals(ed.getDm_tipo_nota_fiscal())){
					 // new WMS_EstoqueBD(executasql).soma(Oid_Estoque_Cliente,ipcED.getNr_Quantidade_Movimento(),true);
				  }
			  }
		  }
		  //Apaga os registros dos itens
		  sql = "DELETE FROM itens_produtos_clientes "+
		  "WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
		  executasql.executarUpdate(sql);
		  //Apaga o regstro da NF
		  sql = "DELETE FROM notas_fiscais_wms "+
		  "WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
		  executasql.executarUpdate(sql);
	  }
	  
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("deleta");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(WMS_Nota_FiscalED edV)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    FormataDataBean DataFormatada = new FormataDataBean();

    try{
         sql = "SELECT " + 
	   		"notas_fiscais_wms.*, "+
	   		"unidades.cd_unidade, " + 
	   		"pu.nm_razao_social as punm, "+
	   		"pc.nm_razao_social as pcnm, "+
	   		"pr.nm_razao_social as prnm "+  
	   		"FROM " + 
	   		"notas_fiscais_wms, "+
	   		"unidades, " +
	   		"pessoas as pu, " +
	   		"pessoas as pc, " +
	   		"pessoas as pr " +
	   		"WHERE 	"+ 
	   		"unidades.oid_unidade = notas_fiscais_wms.oid_unidade_fiscal and " +
	   		"pu.oid_pessoa = unidades.oid_pessoa  and " +
	   		"pc.oid_pessoa = notas_fiscais_wms.oid_cliente and "+
	   		"pr.oid_pessoa = notas_fiscais_wms.oid_pessoa ";                    // Remetente e destinatario estão aqui, depende de dm_tipo_nota S/N/D  


     if(edV.getNr_nota_fiscal()!= 0)
         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal();
      
     if( edV.getOID_Unidade() != 0 )
        sql += " and OID_Unidade_Emissora = "+edV.getOID_Unidade();

     if(edV.getNm_serie()!= null)
        sql += " and nm_serie ='"+edV.getNm_serie()+"' ";

     if((edV.getOid_pessoa_destinatario()!= null)&&(!edV.getOid_pessoa_destinatario().equals("")))
        sql += " and oid_pessoa_destinatario ='"+edV.getOid_pessoa_destinatario()+"' ";
      
     if((edV.getOid_pessoa_transportador()!= null)&&(!edV.getOid_pessoa_transportador().equals("")))
        sql += " and oid_pessoa_transportador ='"+edV.getOid_pessoa_transportador()+"' ";

     if((edV.getOid_pessoa()!= null)&&(!edV.getOid_pessoa().equals("")))
        sql += " and pr.oid_pessoa ='"+edV.getOid_pessoa()+"' ";

     if((edV.getDt_emissao()!= null)&&(!edV.getDt_emissao().equals("")))
        sql += " and dt_emissao ='"+edV.getDt_emissao()+"' ";
     
     if((edV.getDt_entrada()!= null)&&(!edV.getDt_entrada().equals("")))
         sql += " and dt_entrada ='"+edV.getDt_entrada()+"' ";
     
     if(doValida(edV.getOid_cliente()))
    	 sql += " and oid_cliente ='"+edV.getOid_cliente()+"' ";
     
     if(doValida(edV.getDm_tipo_nota_fiscal()))
    	 sql += " and dm_tipo_nota_fiscal ='"+edV.getDm_tipo_nota_fiscal()+"' ";

     if(doValida(edV.getDm_finalizado()))
    	 sql += " and dm_finalizado ='"+edV.getDm_finalizado()+"' ";

	 sql += " and dm_impresso is null";  
      
      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
			WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
			ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
			ed.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
			
			ed.setDt_emissao(res.getString("dt_emissao"));
			DataFormatada.setDT_FormataData(ed.getDt_emissao());
			ed.setDt_emissao(DataFormatada.getDT_FormataData());
			ed.setDt_entrada(res.getString("dt_entrada"));
			DataFormatada.setDT_FormataData(ed.getDt_entrada());
			ed.setDt_entrada(DataFormatada.getDT_FormataData());
			ed.setOID_Unidade(res.getLong("oid_unidade_emissora"));
			
			ed.setNr_volumes(res.getLong("nr_volumes"));
			ed.setNm_serie(res.getString("nm_serie"));
			
			ed.setHr_entrada(res.getString("hr_entrada"));
			ed.setOid_pessoa(res.getString("oid_pessoa"));
			ed.setOid_pessoa_destinatario(res.getString("oid_pessoa_destinatario"));
			ed.setOid_pessoa_transportador(res.getString("oid_pessoa_transportador"));
			ed.setOid_natureza_operacao(res.getLong("oid_natureza_operacao"));
			ed.setOid_modelo_nota_fiscal(res.getLong("oid_modelo_nota_fiscal"));
			ed.setVl_total_frete(res.getDouble("vl_total_frete"));
			ed.setVl_icms(res.getDouble("vl_icms"));
			ed.setVl_inss(res.getDouble("vl_inss"));
			ed.setVl_irrf(res.getDouble("vl_irrf"));
			ed.setVl_ipi(res.getDouble("vl_ipi"));
			ed.setVl_ipi(res.getDouble("vl_servico"));
			ed.setVl_isqn(res.getDouble("vl_isqn"));
			ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
			ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
			ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
			ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
			ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
			ed.setDt_stamp(res.getString("dt_stamp"));
			ed.setDm_finalizado(res.getString("dm_finalizado"));
			
			if( res.getString("dm_observacao") == null ) ed.setDm_observacao("");
			else ed.setDm_observacao(res.getString("dm_observacao")); 
			
			ed.setVl_descontos(res.getDouble("vl_descontos"));
			  
			ed.setOID_Empresa(res.getInt("OID_Empresa"));
			ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
			ed.setVL_Base_Calculo_ICMS_Substituicao(res.getDouble("VL_Base_Calculo_ICMS_Substituicao"));
			ed.setVL_ICMS_Substituicao(res.getDouble("VL_ICMS_Substituicao"));
			ed.setVL_Total_Produtos(res.getDouble("VL_Total_Produtos"));
			ed.setNR_Quantidade(res.getInt("NR_Quantidade"));
			ed.setDM_Frete(res.getString("DM_Frete"));
			ed.setNM_Especie(res.getString("NM_Especie"));
			ed.setNM_Marca(res.getString("NM_Marca"));
			ed.setNR_Placa(res.getString("NR_Placa"));
			ed.setNR_Numero(res.getString("NR_Numero"));
			ed.setNR_Peso_Bruto(res.getDouble("NR_Peso_Bruto"));
			ed.setNR_Peso_Liquido(res.getDouble("NR_Peso_Liquido"));
			// Campos numericos formatados
			ed.setVL_Base_Calculo_ICMS_TX(FormataValor.formataValorBT(res.getDouble("VL_Base_Calculo_ICMS"), 2));
			ed.setVl_icms_TX(FormataValor.formataValorBT(res.getDouble("vl_icms"), 2));
			ed.setVL_Base_Calculo_ICMS_Substituicao_TX(FormataValor.formataValorBT(res.getDouble("VL_Base_Calculo_ICMS_Substituicao"), 2));
			ed.setVL_ICMS_Substituicao_TX(FormataValor.formataValorBT(res.getDouble("VL_ICMS_Substituicao"), 2));
			ed.setVL_Total_Produtos_TX(FormataValor.formataValorBT(res.getDouble("VL_Total_Produtos"), 2));
			ed.setVl_total_frete_TX(FormataValor.formataValorBT(res.getDouble("vl_total_frete"), 2));
			ed.setVl_total_seguro_TX(FormataValor.formataValorBT(res.getDouble("vl_total_seguro"), 2));
			ed.setVl_total_despesas_TX(FormataValor.formataValorBT(res.getDouble("vl_total_despesas"), 2));
			ed.setVl_ipi_TX(FormataValor.formataValorBT(res.getDouble("vl_ipi"), 2));
			ed.setVl_liquido_nota_fiscal_TX(FormataValor.formataValorBT(res.getDouble("vl_liquido_nota_fiscal"), 2));
			// Campos de outras tabelas 
			ed.setCD_Unidade_Fiscal(res.getString("cd_unidade"));
			ed.setNM_Unidade_Fiscal(res.getString("punm"));
			ed.setNm_razao_social_cliente(res.getString("pcnm"));
			ed.setNM_Razao_Social_Remetente(res.getString("prnm"));
			ed.setNM_Razao_Social_Destinatario(res.getString("prnm"));
			
			list.add(ed);
      	}
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Notas Fiscais");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }


  public WMS_Nota_FiscalED getByRecord(WMS_Nota_FiscalED edV)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
    FormataDataBean DataFormatada = new FormataDataBean();

    try{
     sql = "SELECT " + 
 		   "notas_fiscais_wms.*, "+
 		   "unidades.cd_unidade, " + 
 		   "pu.nm_fantasia as punm, "+
 		   "pc.nm_fantasia as pcnm, "+
 		   "pr.nm_fantasia as prnm, "+
 		   "cd_natureza_operacao, "+
 		   "nm_natureza_operacao "+
    	   "FROM " + 
    	   "notas_fiscais_wms, "+
    	   "unidades, " +
    	   "pessoas as pu, " +
    	   "pessoas as pc, " +
    	   "pessoas as pr, " +
    	   "naturezas_operacoes "+
    	   "WHERE "+ 
    	   "unidades.oid_unidade = notas_fiscais_wms.oid_unidade_fiscal and " +
    	   "pu.oid_pessoa = unidades.oid_pessoa  and " +
    	   "pc.oid_pessoa = notas_fiscais_wms.oid_cliente and "+
    	   "pr.oid_pessoa = notas_fiscais_wms.oid_pessoa and "+
    	   "naturezas_operacoes.oid_natureza_operacao = notas_fiscais_wms.oid_natureza_operacao ";  
    	   
     if(edV.getOid_nota_fiscal()!= null) {
         sql += " and Oid_nota_fiscal ='"+edV.getOid_nota_fiscal()+"'";
     } else {
	     if((edV.getNr_nota_fiscal()!= 0)&&(edV.getNm_serie()!= null))
	         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal()+" and nm_serie ='"+edV.getNm_serie()+"' ";
	     if((edV.getNr_nota_fiscal()!= 0))
	         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal();
	     if(edV.getOid_pessoa()!= null)
	        sql += " and oid_pessoa ='"+edV.getOid_pessoa()+"' ";
	     if(edV.getOid_pessoa_destinatario()!= null)
	        sql += " and oid_pessoa_destinatario ='"+edV.getOid_pessoa_destinatario()+"' ";
	     if(edV.getOid_cliente()!= null)
	        sql += " and oid_cliente ='"+edV.getOid_cliente()+"' ";
     }   
      
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){

        ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
        ed.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
        ed.setNr_volumes(res.getLong("nr_volumes"));
        ed.setNm_serie(res.getString("nm_serie"));
        ed.setDt_emissao(res.getString("dt_emissao"));
        DataFormatada.setDT_FormataData(ed.getDt_emissao());
        ed.setDt_emissao(DataFormatada.getDT_FormataData());
        ed.setDt_entrada(res.getString("dt_entrada"));
        DataFormatada.setDT_FormataData(ed.getDt_entrada());
        ed.setDt_entrada(DataFormatada.getDT_FormataData());
        ed.setOID_Unidade(res.getLong("oid_unidade_emissora"));
        ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_fiscal"));
        ed.setHr_entrada(res.getString("hr_entrada"));
        ed.setOid_pessoa(res.getString("oid_pessoa"));
        ed.setOid_pessoa_destinatario(res.getString("oid_pessoa_destinatario"));
        ed.setOid_pessoa_transportador(res.getString("oid_pessoa_transportador"));
        ed.setOid_natureza_operacao(res.getLong("oid_natureza_operacao"));
        ed.setOid_modelo_nota_fiscal(res.getLong("oid_modelo_nota_fiscal"));
        ed.setVl_total_frete(res.getDouble("vl_total_frete"));
        ed.setVl_icms(res.getDouble("vl_icms"));
        ed.setVl_inss(res.getDouble("vl_inss"));
        ed.setVl_irrf(res.getDouble("vl_irrf"));
        ed.setVl_ipi(res.getDouble("vl_ipi"));
        ed.setVL_Servico(res.getDouble("vl_servico"));
        ed.setVl_isqn(res.getDouble("vl_isqn"));
        ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
        ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
        ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
        ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
        ed.setDt_stamp(res.getString("dt_stamp"));
        ed.setDm_finalizado(res.getString("dm_finalizado"));
        
        if( res.getString("dm_observacao") == null ) ed.setDm_observacao("");
        else ed.setDm_observacao(res.getString("dm_observacao"));  
        
        ed.setVl_descontos(res.getDouble("vl_descontos"));
       
        ed.setOID_Empresa(res.getInt("OID_Empresa"));
        ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
        ed.setVL_Base_Calculo_ICMS_Substituicao(res.getDouble("VL_Base_Calculo_ICMS_Substituicao"));
        ed.setVL_ICMS_Substituicao(res.getDouble("VL_ICMS_Substituicao"));
        ed.setVL_Total_Produtos(res.getDouble("VL_Total_Produtos"));
        ed.setNR_Quantidade(res.getInt("NR_Quantidade"));
        ed.setDM_Frete(res.getString("DM_Frete"));
        ed.setNM_Especie(res.getString("NM_Especie"));
        ed.setNM_Marca(res.getString("NM_Marca"));
        ed.setNR_Placa(res.getString("NR_Placa"));
        ed.setNR_Numero(res.getString("NR_Numero"));
        ed.setNR_Peso_Bruto(res.getDouble("NR_Peso_Bruto"));
        ed.setNR_Peso_Liquido(res.getDouble("NR_Peso_Liquido"));
        ed.setOid_cliente(res.getString("oid_cliente"));
        // Campos numericos formatados
        ed.setVL_Base_Calculo_ICMS_TX(FormataValor.formataValorBT(res.getDouble("VL_Base_Calculo_ICMS"), 2));
        ed.setVl_icms_TX(FormataValor.formataValorBT(res.getDouble("vl_icms"), 2));
        ed.setVL_Base_Calculo_ICMS_Substituicao_TX(FormataValor.formataValorBT(res.getDouble("VL_Base_Calculo_ICMS_Substituicao"), 2));
        ed.setVL_ICMS_Substituicao_TX(FormataValor.formataValorBT(res.getDouble("VL_ICMS_Substituicao"), 2));
        ed.setVL_Total_Produtos_TX(FormataValor.formataValorBT(res.getDouble("VL_Total_Produtos"), 2));
        ed.setVl_total_frete_TX(FormataValor.formataValorBT(res.getDouble("vl_total_frete"), 2));
        ed.setVl_total_seguro_TX(FormataValor.formataValorBT(res.getDouble("vl_total_seguro"), 2));
        ed.setVl_total_despesas_TX(FormataValor.formataValorBT(res.getDouble("vl_total_despesas"), 2));
        ed.setVl_ipi_TX(FormataValor.formataValorBT(res.getDouble("vl_ipi"), 2));
        ed.setVl_liquido_nota_fiscal_TX(FormataValor.formataValorBT(res.getDouble("vl_liquido_nota_fiscal"), 2));
        // Campos de outras tabelas 
        ed.setCD_Unidade_Fiscal(res.getString("cd_unidade"));
        ed.setCD_Unidade(res.getString("cd_unidade"));
        ed.setNM_Unidade_Fiscal(res.getString("punm"));
        ed.setNm_Fantasia(res.getString("punm"));
        ed.setNm_razao_social_cliente(res.getString("pcnm"));
        ed.setNM_Razao_Social_Remetente(res.getString("prnm"));
        ed.setCd_Natureza_Operacao(res.getString("cd_natureza_operacao"));
        ed.setNm_Natureza_Operacao(res.getString("nm_natureza_operacao"));
    	}
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Sugestao");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;
  }


  public ArrayList lista(WMS_Nota_FiscalED edV, String orderby)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    FormataDataBean DataFormatada = new FormataDataBean();
    String order_by = " ORDER BY NR_Nota_Fiscal desc";
    boolean bol = false;
    try{
      sql = "SELECT * FROM notas_fiscais_wms WHERE 1=1 ";

     if(edV.getNr_nota_fiscal()!= 0)
         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal();
      
     if( edV.getOID_Unidade() != 0 )
        sql += " and OID_Unidade_Emissora = "+edV.getOID_Unidade();

     if(edV.getNm_serie()!= null)
        sql += " and nm_serie ='"+edV.getNm_serie()+"' ";

     if((edV.getOid_pessoa_destinatario()!= null)&&(!edV.getOid_pessoa_destinatario().equals("")))
        sql += " and oid_pessoa_destinatario ='"+edV.getOid_pessoa_destinatario()+"' ";
      
     if((edV.getOid_pessoa_transportador()!= null)&&(!edV.getOid_pessoa_transportador().equals("")))
        sql += " and oid_pessoa_transportador ='"+edV.getOid_pessoa_transportador()+"' ";

     if((edV.getOid_pessoa()!= null)&&(!edV.getOid_pessoa().equals("")))
        sql += " and oid_pessoa ='"+edV.getOid_pessoa()+"' ";

     if((edV.getDt_emissao()!= null)&&(!edV.getDt_emissao().equals("")))
        sql += " and dt_emissao ='"+edV.getDt_emissao()+"' ";
      
     if((edV.getDm_observacao()!= null)&&(!edV.getDm_observacao().equals("")))
        sql += " and dm_tipo_nota_fiscal ='"+edV.getDm_observacao()+"' ";
      
     sql += " and dm_impresso is null ";

      if( !order_by.equals( orderby ) )
        sql += orderby;
      else
        sql += order_by;

      ResultSet res = null, res2 = null;

      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
        ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
        ed.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));

        ed.setDt_emissao(res.getString("dt_emissao"));
        DataFormatada.setDT_FormataData(ed.getDt_emissao());
        ed.setDt_emissao(DataFormatada.getDT_FormataData());
        ed.setDt_entrada(res.getString("dt_entrada"));
        DataFormatada.setDT_FormataData(ed.getDt_entrada());
        ed.setDt_entrada(DataFormatada.getDT_FormataData());


        ed.setNr_volumes(res.getLong("nr_volumes"));
        ed.setNm_serie(res.getString("nm_serie"));

       ed.setHr_entrada(res.getString("hr_entrada"));
        ed.setOid_pessoa(res.getString("oid_pessoa"));
        ed.setOid_pessoa_destinatario(res.getString("oid_pessoa_destinatario"));
        ed.setOid_pessoa_transportador(res.getString("oid_pessoa_transportador"));
        ed.setOid_natureza_operacao(res.getLong("oid_natureza_operacao"));
        ed.setOid_modelo_nota_fiscal(res.getLong("oid_modelo_nota_fiscal"));
        ed.setVl_total_frete(res.getDouble("vl_total_frete"));      
           
        ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
       
        ed.setOID_Unidade(res.getLong("oid_unidade_emissora"));
        ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_fiscal"));
        
        sql = "select * from pessoas where oid_pessoa ='"+ed.getOid_pessoa()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){ ed.setNM_Razao_Social_Remetente( res2.getString( "NM_Razao_Social" ) ); }

        sql = "select * from pessoas where oid_pessoa ='"+ed.getOid_pessoa_destinatario()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){ ed.setNM_Razao_Social_Destinatario( res2.getString( "NM_Razao_Social" ) ); }

        sql = "select * from pessoas where oid_pessoa ='"+ed.getOid_pessoa_transportador()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){ ed.setNM_Razao_Social_Transportador( res2.getString( "NM_Razao_Social" ) ); }

      if(ed.getDm_tipo_nota_fiscal().equals("E"))
         ed.setDm_tipo_nota_fiscal("Entrada");
      else ed.setDm_tipo_nota_fiscal("Saída");
     
        sql = "select * from empresas where oid_empresa ="+ed.getOID_Empresa();
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          ed.setCD_Empresa( res2.getString( "CD_Empresa" ) );
          ed.setNM_Empresa( res2.getString( "NM_Empresa" ) );
        }

        ed.setOID_Empresa(res.getInt("OID_Empresa"));
       
        ed.setVL_Total_Produtos(res.getDouble("VL_Total_Produtos"));
        ed.setNR_Quantidade(res.getInt("NR_Quantidade"));
              
        ed.setDM_Gerado( "N" );
        sql = "select * from itens_produtos_clientes where oid_nota_fiscal = '"+ed.getOid_nota_fiscal()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          if( res2.getInt( "nr_quantidade_movimento" ) == res2.getInt( "nr_quantidade_devolucao" ) ){
            ed.setDM_Gerado( "S" );
          }    
        }
        
        bol = false; 
        
        sql = "select * from requisicoes_produtos where oid_nota_fiscal_transacao = '"+ed.getOid_nota_fiscal()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
           bol = true;                          
        }   
        
        if( bol ) ed.setDM_Requisicao( "S" );
        else ed.setDM_Requisicao( "N" );

         list.add(ed);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Notas Fiscais");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

  public WMS_Requisicoes_ProdutosED gerarRequisicao( WMS_Requisicoes_ProdutosED ed )throws Excecoes{
    String sql = null;
    ResultSet res = null;
    String mensagem = "Erro ao Gerar Requisição";
    WMS_Requisicoes_ProdutosED Requisicoes_ProdutosVolta = new WMS_Requisicoes_ProdutosED();
    WMS_Requisicoes_ProdutosRN Requisicoes_ProdutosRN = new WMS_Requisicoes_ProdutosRN();
    try{
      sql = "select * from requisicoes_produtos where oid_nota_fiscal_transacao = '"+ ed.getOID_Nota_Fiscal_Transacao() +"'";

      res = this.executasql.executarConsulta(sql);

      while( res.next() ){
        mensagem = "Já foi gerada uma requisição para esta Nota Fiscal";
        throw new Exception();
      }

      Requisicoes_ProdutosVolta = Requisicoes_ProdutosRN.inclui( ed );
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem( mensagem );
      excecoes.setMetodo("gerarRequisicao()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Requisicoes_ProdutosVolta;
  }

  public void gerarMovimentos( javax.servlet.http.HttpServletRequest request, int OID_Requisicao_Produto )throws Excecoes{
    String sql = null;
    ResultSet res = null;
    String DM_Tipo_Movimento_Produto = "";
    String mensagem = "Erro ao Gerar Movimentos de Produtos";
    try{
      WMS_Movimentos_ProdutosED Movimentos_ProdutosED = new WMS_Movimentos_ProdutosED();
      WMS_Movimentos_ProdutosRN Movimentos_ProdutosRN = new WMS_Movimentos_ProdutosRN();
      Parametro_FixoED Parametro_Fixo = new Parametro_FixoED();

        if( request.getParameter( "FT_DM_TipoNota" ).equals( "E" ) ){
          Parametro_Fixo.setOID_Tipo_Movimento_Produto( 2 );
        }else
        if( request.getParameter( "FT_DM_TipoNota" ).equals( "S" ) ){
          Parametro_Fixo.setOID_Tipo_Movimento_Produto( 3 );
        }

      sql = "select * from tipos_movimentos_produtos where OID_Tipo_Movimento_Produto = "+ Parametro_Fixo.getOID_Tipo_Movimento_Produto();
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        DM_Tipo_Movimento_Produto = res.getString( "dm_tipo_movimento" );
      }

      sql = "select * from itens_produtos_clientes where oid_nota_fiscal = '"+ request.getParameter("oid_Nota_Fiscal") +"'";
      res = this.executasql.executarConsulta(sql);

      while (res.next()){

        Movimentos_ProdutosED.setOID_Produto_Cliente( res.getString( "oid_produto_cliente" ) );

        Movimentos_ProdutosED.setOID_Requisicao_Produto( OID_Requisicao_Produto );

        Movimentos_ProdutosED.setNR_Quantidade_Requerida( res.getInt( "nr_quantidade_movimento" ) );

        Movimentos_ProdutosED.setOID_Operador( Parametro_Fixo.getOID_Operador() );

        Movimentos_ProdutosED.setOID_Tipo_Movimento_Produto( Parametro_Fixo.getOID_Tipo_Movimento_Produto() );
        if( request.getParameter( "FT_DM_TipoNota" ).equals( "E" ) )
          Movimentos_ProdutosED.setDM_Tipo_Movimento( "E" );
        else
        if( request.getParameter( "FT_DM_TipoNota" ).equals( "S" ) )
          Movimentos_ProdutosED.setDM_Tipo_Movimento( "S" );

        Movimentos_ProdutosED.setDM_Tipo_Movimento( DM_Tipo_Movimento_Produto );

        Movimentos_ProdutosRN.inclui( Movimentos_ProdutosED );

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem( mensagem );
      excecoes.setMetodo("gerarRequisicao()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public byte[] geraRelatorioNotaFiscal(WMS_Nota_FiscalED ed)throws Excecoes{

    String sql = null;
   
    byte[] b = null;
    WMS_Nota_FiscalED conED = new WMS_Nota_FiscalED();
    long valOid = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;
    long NR_Nota_Fiscal = 0;
    int u = 0;
    Data data = new Data();

    try{     
                
      sql = "Select * "+
      " from "+
      " Notas_Fiscais_wms "+
      " where "+        
      " DM_Impresso = 'N' ";
      
      if( ed.getOid_nota_fiscal() != null )
        sql += " AND oid_nota_fiscal = '"+ ed.getOid_nota_fiscal() +"'";
    
      ResultSet resCTRC = null;
      resCTRC = this.executasql.executarConsulta(sql);
                       
      while (resCTRC.next()){                  
     
        conED.setOid_pessoa(resCTRC.getString("Oid_pessoa"));
        conED.setOid_nota_fiscal(resCTRC.getString("Oid_nota_fiscal"));

        sql =  " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
        sql += " FROM AIDOF ";
        sql += " WHERE AIDOF.NM_Serie = 'NF2' ";

        ResultSet resTP = null;
        resTP = this.executasql.executarConsulta(sql.toString());

        while (resTP.next()){
          conED.setNm_serie(resTP.getString("NM_Serie"));
          NR_Nota_Fiscal = resTP.getLong("NR_Proximo");
          valOid = resTP.getLong("OID_AIDOF");
          NR_Proximo = resTP.getLong("NR_Proximo") + 1;
          NR_Final = resTP.getLong("NR_FINAL");
        }

        if(NR_Proximo > NR_Final){
          Excecoes exc = new Excecoes();
          throw exc;
        }

        sql =  " UPDATE AIDOF SET NR_Proximo= " + (NR_Nota_Fiscal + 1);
        sql += " WHERE OID_AIDOF = " + valOid ;

        u = executasql.executarUpdate(sql);

        sql = " update Notas_Fiscais_wms set "+
              " NR_Nota_Fiscal = " + NR_Nota_Fiscal + ", "+
              " NM_Serie = '" + conED.getNm_serie() + "', "+
              " DT_Emissao = '" + data.getDataDMY() + "' ";
        sql += " where oid_nota_fiscal = '" + conED.getOid_nota_fiscal() + "'";

        u = executasql.executarUpdate(sql);
       
      }
      
      sql = "Select * "+     
      " from "+
      " Notas_Fiscais_wms "+      
      " where "+     
      " DM_Impresso = 'N' ";
      
      if( ed.getOid_nota_fiscal() != null )
        sql += " AND oid_nota_fiscal = '"+ ed.getOid_nota_fiscal() +"'";
    
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      WMS_Nota_FiscalRL WMS_Nota_Fiscal_rl = new WMS_Nota_FiscalRL();
      b = WMS_Nota_Fiscal_rl.geraRelatorioNotaFiscal(res);
      

      sql = "Select * "+
      " from "+
      " notas_fiscais_wms "+
      " where "+
      " DM_Impresso = 'N' ";
      
      if( ed.getOid_nota_fiscal() != null )
        sql += " AND oid_nota_fiscal = '"+ ed.getOid_nota_fiscal() +"'";    
   
      resCTRC = null;
      resCTRC = this.executasql.executarConsulta(sql);

      while (resCTRC.next()){

        sql = " update notas_fiscais_wms set DM_Impresso = 'S'";
        sql += " where oid_nota_fiscal = '" + resCTRC.getString("oid_nota_fiscal") + "'";

        u = executasql.executarUpdate(sql);
      }         

    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorioNotaFiscal(WMS_Nota_FiscalED ed)");
    }
    return b;
  }
  
  public long calcula_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;
    ResultSet res = null;
    long quantidade = 0;

    try{         
       sql = "Select nr_quantidade_movimento ";
       sql+= "from itens_produtos_clientes Where oid_Nota_Fiscal ='"+ed.getOid_nota_fiscal()+"'";
       res = executasql.executarConsulta(sql);
       while(res.next()){
          quantidade += res.getLong( "nr_quantidade_movimento" );
       }
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("calcula_Quantidade_Itens(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return quantidade;
  }
  
  public void atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;   

    try{         
      wms020Bean wms = new wms020Bean();  
        
      sql = " UPDATE notas_fiscais_wms ";
      sql+= " SET nr_volumes ="+ wms.calcula_Quantidade_Itens(ed);
      sql+= " WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
           
      int i = executasql.executarUpdate(sql);
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }    
  }
  
  public double calcula_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;
    ResultSet res = null;
    double quantidade = 0;

    try{         
       sql = "Select * ";
       sql+= "from itens_produtos_clientes Where oid_Nota_Fiscal ='"+ed.getOid_nota_fiscal()+"'";
       res = executasql.executarConsulta(sql);
       while(res.next()){
          quantidade += ( res.getLong( "nr_quantidade_movimento" ) * res.getDouble( "vl_produto" ) );
       }
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("calcula_Total_Produtos(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return quantidade;
  }
  
  public double calcula_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;
    ResultSet res = null;
    double quantidade = 0;

    try{         
       sql = "Select * ";
       sql+= "from itens_produtos_clientes Where oid_Nota_Fiscal ='"+ed.getOid_nota_fiscal()+"'";
       res = executasql.executarConsulta(sql);
       while(res.next()){
          quantidade += ( res.getLong( "nr_quantidade_movimento" ) * res.getDouble( "vl_produto" ) );
       }
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("calcula_Total_Nota(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return quantidade;
  }
  
  public void atualiza_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;   

    try{         
      wms020Bean wms = new wms020Bean();  
        
      sql = " UPDATE notas_fiscais_wms ";
      sql+= " SET vl_Total_Produtos ="+ wms.calcula_Total_Produtos(ed);
      sql+= " WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
           
      int i = executasql.executarUpdate(sql);
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("atualiza_Total_Produtos(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }    
  }
  
  public void atualiza_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;   

    try{         
      wms020Bean wms = new wms020Bean();  
        
      sql = " UPDATE notas_fiscais_wms ";
      sql+= " SET vl_liquido_nota_fiscal ="+ wms.calcula_Total_Nota(ed);
      sql+= " WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
           
      int i = executasql.executarUpdate(sql);
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("atualiza_Total_Nota(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }    
  }
  
  public boolean isImpresso(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;
    ResultSet res = null;
    boolean impresso = false;

    try{         
       sql = "Select * ";
       sql+= "from notas_fiscais_wms Where oid_Nota_Fiscal ='"+ed.getOid_nota_fiscal()+"'";
       res = executasql.executarConsulta(sql);
       while(res.next()){
          if( res.getString( "dm_impresso" ).equals( "S" ) ) impresso = true;
       }
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("isImpresso(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return impresso;
  }

}
