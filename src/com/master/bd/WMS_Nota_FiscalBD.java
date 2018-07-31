package com.master.bd;

/**
 * Título: WMS_Nota_FiscalBD
 * Descrição: Notas Fiscais - BD
 * Data da criação: 11/2003
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.ed.WMS_Nota_Fiscal_DevolucaoED;
import com.master.iu.wms008Bean;
import com.master.rl.WMS_Nota_FiscalRL;
import com.master.rn.WMS_Item_Produto_ClienteRN;
import com.master.rn.WMS_Nota_FiscalRN;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class WMS_Nota_FiscalBD {

  private ExecutaSQL executasql;

  public WMS_Nota_FiscalBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_Nota_FiscalED inclui(WMS_Nota_FiscalED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    long NR_Proximo = 0;
    long NR_Final = 0;

    WMS_Nota_FiscalED conED = new WMS_Nota_FiscalED();

    try{
        sql =  " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
        sql += " FROM AIDOF ";
        sql += " WHERE AIDOF.NM_Serie = 'NF1' ";

        ResultSet rs = null;
        rs = this.executasql.executarConsulta(sql);

        while (rs.next()){         
          ed.setNm_serie(rs.getString("NM_Serie"));
          ed.setNr_nota_fiscal(rs.getLong("NR_Proximo"));
          valOid = rs.getLong("OID_AIDOF");
          NR_Proximo = rs.getLong("NR_Proximo") + 1;
          NR_Final = rs.getLong("NR_FINAL");
        }

        if(NR_Proximo > NR_Final){
          Excecoes exc = new Excecoes();
          throw exc;
        }

        sql =  " UPDATE AIDOF SET NR_Proximo= " + (ed.getNr_nota_fiscal() + 1);
        sql += " WHERE OID_AIDOF = " + valOid ;
        int u = executasql.executarUpdate(sql); 
                    
      chave = String.valueOf(ed.getOid_pessoa()) + String.valueOf(ed.getNr_nota_fiscal()) + ed.getNm_serie();

      ed.setOid_nota_fiscal(chave);           
      
      ed.setNr_volumes( 0 );
      
      sql = "Insert Into notas_fiscais_wms (oid_nota_fiscal, nr_nota_fiscal, "+
            "dt_entrada, hr_entrada, oid_pessoa, oid_pessoa_destinatario, oid_pessoa_transportador, oid_natureza_operacao,  oid_modelo_nota_fiscal, vl_total_frete, "+
            "vl_icms, vl_servico, vl_inss, vl_irrf, vl_ipi, vl_isqn, vl_total_seguro, vl_total_despesas, vl_nota_fiscal, vl_liquido_nota_fiscal, "+
            "dm_tipo_nota_fiscal, dt_stamp, dm_observacao, vl_descontos, dm_finalizado, oid_unidade_fiscal, oid_unidade_contabil, "+
            "oid_Empresa,VL_Base_Calculo_ICMS,VL_Base_Calculo_ICMS_Substituicao,VL_ICMS_Substituicao,VL_Total_Produtos,VL_PIS,VL_CSLL,VL_Cofins, "+
            "nm_serie,DM_Impresso, oid_unidade_emissora, DM_Gerado ) values ";

      sql += "('" + ed.getOid_nota_fiscal() + "'," +ed.getNr_nota_fiscal()+ ",'"+
              ed.getDt_entrada()+"','"+
              ed.getHr_entrada()+"', '"+
              ed.getOid_pessoa()+"', '"+
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
              ed.getVl_isqn()+","+
              ed.getVl_total_seguro()+", "+
              ed.getVl_total_despesas()+", "+
              ed.getVl_nota_fiscal()+", "+
              ed.getVl_liquido_nota_fiscal()+", '"+
              ed.getDm_tipo_nota_fiscal()+"','"+
              ed.getDt_stamp()+"', UPPER('"+
              ed.getDm_observacao()+"'),"+
              ed.getVl_descontos()+",'N',"+
              ed.getOID_Unidade_Fiscal()+","+
              ed.getOID_Unidade_Contabil()+","+          
              ed.getOID_Empresa()+", "+ 
              ed.getVL_Base_Calculo_ICMS()+", "+ 
              ed.getVL_Base_Calculo_ICMS_Substituicao()+", "+ 
              ed.getVL_ICMS_Substituicao()+", "+
              ed.getVL_Total_Produtos()+", "+  
              ed.getVL_PIS()+", "+ 
              ed.getVL_CSLL()+", "+ 
              ed.getVL_Cofins()+", '"+
              ed.getNm_serie()+"','N',"+
              ed.getOID_Unidade()+", '"+
              ed.getDM_Gerado()+"' )";

          
      int i = executasql.executarUpdate(sql);

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
      conED.setOID_Unidade_Fiscal(ed.getOID_Unidade_Fiscal());
      conED.setOID_Unidade_Contabil(ed.getOID_Unidade_Contabil());
      conED.setDm_finalizado("N");
      conED.setOID_Unidade(ed.getOID_Unidade());
      conED.setOID_Empresa(ed.getOID_Empresa());
      conED.setVL_Base_Calculo_ICMS(ed.getVL_Base_Calculo_ICMS());
      conED.setVL_Base_Calculo_ICMS_Substituicao(ed.getVL_Base_Calculo_ICMS_Substituicao());
      conED.setVL_ICMS_Substituicao(ed.getVL_ICMS_Substituicao());
      conED.setVL_Total_Produtos(ed.getVL_Total_Produtos());
      conED.setVL_PIS(ed.getVL_PIS());
      conED.setVL_CSLL(ed.getVL_CSLL());
      conED.setVL_Cofins(ed.getVL_Cofins());
      conED.setNR_Quantidade(ed.getNR_Quantidade());
      conED.setDM_Frete(ed.getDM_Frete());
      conED.setNM_Especie(ed.getNM_Especie());
      conED.setNM_Marca(ed.getNM_Marca());
      conED.setNR_Placa(ed.getNR_Placa());
      conED.setNR_Numero(ed.getNR_Numero());
      conED.setNR_Peso_Bruto(ed.getNR_Peso_Bruto());
      conED.setNR_Peso_Liquido(ed.getNR_Peso_Liquido());
      conED.setDM_Gerado( ed.getDM_Gerado() );
    }

    catch(Exception exc){
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
    String chave = null;

    try{
        wms008Bean wms = new wms008Bean();
      
        ed.setNr_volumes( wms.calcula_Quantidade_Itens(ed) );
        
        
        sql = "UPDATE notas_fiscais_wms "+
              "SET nr_nota_fiscal ="+ ed.getNr_nota_fiscal()+", "+             
              "nr_volumes ="+ ed.getNr_volumes()+", "+            
              "dt_entrada ='"+ ed.getDt_entrada()+"', "+
              "hr_entrada ='"+ ed.getHr_entrada()+"', "+
              "oid_pessoa ='"+ ed.getOid_pessoa()+"', "+
              "oid_pessoa_destinatario ='"+ ed.getOid_pessoa_destinatario()+"', "+
              "oid_pessoa_transportador ='"+ ed.getOid_pessoa_transportador()+"', "+
              "oid_natureza_operacao ="+ ed.getOid_natureza_operacao()+", "+
              "oid_modelo_nota_fiscal ="+ ed.getOid_modelo_nota_fiscal()+", "+
              "vl_total_frete ="+ ed.getVl_total_frete()+", "+
              "vl_icms ="+ ed.getVl_icms()+", "+
              "vl_inss ="+ ed.getVl_inss()+", "+
              "vl_irrf ="+ ed.getVl_irrf()+", "+
              "vl_ipi ="+ ed.getVl_ipi()+", "+
              "vl_isqn ="+ ed.getVl_isqn()+", "+
              "vl_servico ="+ ed.getVL_Servico()+", "+
              "vl_total_seguro ="+ ed.getVl_total_seguro()+", "+
              "vl_pis ="+ ed.getVL_PIS()+", "+
              "vl_csll ="+ ed.getVL_CSLL()+", "+
              "vl_cofins ="+ ed.getVL_Cofins()+", "+
              "vl_total_despesas ="+ ed.getVl_total_despesas()+", "+
              "vl_nota_fiscal ="+ ed.getVl_nota_fiscal()+", "+
              "vl_liquido_nota_fiscal ="+ ed.getVl_liquido_nota_fiscal()+", "+
              "dm_tipo_nota_fiscal ='"+ed.getDm_tipo_nota_fiscal()+"', "+
              "dt_stamp ='"+ed.getDt_stamp()+"', "+
              "dm_observacao ='"+ed.getDm_observacao()+"', "+             
              "vl_descontos ="+ ed.getVl_descontos()+", "+
              "oid_unidade_fiscal ="+ ed.getOID_Unidade_Fiscal()+", "+
              "oid_unidade_contabil ="+ ed.getOID_Unidade_Contabil()+", "+
              "oid_unidade_emissora ="+ ed.getOID_Unidade()+", "+
              "OID_Empresa ="+ ed.getOID_Empresa()+", "+
              "VL_Base_Calculo_ICMS ="+ ed.getVL_Base_Calculo_ICMS()+", "+
              "VL_Base_Calculo_ICMS_Substituicao ="+ ed.getVL_Base_Calculo_ICMS_Substituicao()+", "+
              "VL_ICMS_Substituicao ="+ ed.getVL_ICMS_Substituicao()+", "+
              "VL_Total_Produtos ="+ ed.getVL_Total_Produtos()+  

              "WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";


        int i = executasql.executarUpdate(sql);
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

  public void deleta(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;  
    ResultSet res = null;   

    try{     
       sql = "DELETE FROM itens_produtos_clientes "+
             "WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
     
       int i = executasql.executarUpdate(sql);

       sql = "DELETE FROM notas_fiscais_wms "+
             "WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";

       i = executasql.executarUpdate(sql);
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
      sql = "SELECT * FROM notas_fiscais_wms WHERE 1=1 ";

     if(edV.getNr_nota_fiscal()!= 0)
         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal();

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

     if((edV.getDm_finalizado()!= null)&&(!edV.getDm_finalizado().equals(""))){
         if(edV.getDm_finalizado().equals("T")){
        sql += " and (Dm_finalizado ='S' or Dm_finalizado ='N' or Dm_finalizado ='R' or Dm_finalizado ='E')";
         }else sql += " and Dm_finalizado ='"+edV.getDm_finalizado()+"' ";
     }

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
        ed.setVL_PIS(res.getDouble("vl_pis"));
        ed.setVL_CSLL(res.getDouble("vl_csll"));
        ed.setVL_Cofins(res.getDouble("vl_cofins"));      
        ed.setVl_isqn(res.getDouble("vl_isqn"));
        ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
        ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
        ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
        ed.setVL_Servico(res.getDouble("vl_servico"));
        ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
        ed.setDt_stamp(res.getString("dt_stamp"));
        ed.setDm_observacao(res.getString("dm_observacao"));     
        ed.setVl_descontos(res.getDouble("vl_descontos"));
        ed.setDm_finalizado(res.getString("dm_finalizado"));
        ed.setOID_Unidade_Contabil(res.getLong("oid_unidade_fiscal"));
        ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_contabil"));
        ed.setOID_Unidade(res.getLong("oid_unidade_emissora"));
        ed.setOID_Empresa(res.getInt("OID_Empresa"));
        ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
        ed.setVL_Base_Calculo_ICMS_Substituicao(res.getDouble("VL_Base_Calculo_ICMS_Substituicao"));
        ed.setVL_ICMS_Substituicao(res.getDouble("VL_ICMS_Substituicao"));
        ed.setVL_Total_Produtos(res.getDouble("VL_Total_Produtos"));
        ed.setDM_Gerado(res.getString("DM_Gerado")); 
        
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
    WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
    FormataDataBean DataFormatada = new FormataDataBean();

    try{
     sql = "SELECT * FROM notas_fiscais_wms WHERE 1=1 ";

     if((edV.getNr_nota_fiscal()!= 0)&&(edV.getNm_serie()!= null))
         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal()+" and nm_serie ='"+edV.getNm_serie()+"' ";
     if((edV.getNr_nota_fiscal()!= 0))
         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal();
     if(edV.getOid_pessoa()!= null)
        sql += " and oid_pessoa ='"+edV.getOid_pessoa()+"' ";
     if(edV.getOid_pessoa_destinatario()!= null)
        sql += " and oid_pessoa_destinatario ='"+edV.getOid_pessoa_destinatario()+"' ";
     if(edV.getOid_pessoa_transportador()!= null)
        sql += " and oid_pessoa_transportador ='"+edV.getOid_pessoa_transportador()+"' ";
     if(edV.getOid_modelo_nota_fiscal()!= 0)
        sql += " and Oid_modelo_nota_fiscal ="+edV.getOid_modelo_nota_fiscal();
     if(edV.getOid_nota_fiscal()!= null)
        sql += " and Oid_nota_fiscal ='"+edV.getOid_nota_fiscal()+"'";
     if(edV.getOID_Unidade()!= 0)
        sql += " and OID_Unidade_Emissora = "+edV.getOID_Unidade();
      ResultSet res = null, res2 = null;
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
        ed.setDM_Gerado(res.getString("DM_Gerado")); 
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
        ed.setVL_PIS(res.getDouble("vl_pis"));
        ed.setVL_CSLL(res.getDouble("vl_csll"));
        ed.setVL_Cofins(res.getDouble("vl_cofins"));    
        ed.setVL_Servico(res.getDouble("vl_servico"));
        ed.setVl_isqn(res.getDouble("vl_isqn"));
        ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
        ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
        ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
        ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
        ed.setDt_stamp(res.getString("dt_stamp"));
        ed.setDm_observacao(res.getString("dm_observacao"));    
        ed.setVl_descontos(res.getDouble("vl_descontos"));
        ed.setOID_Unidade_Contabil(res.getLong("oid_unidade_contabil"));
        ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_fiscal"));
        ed.setDm_finalizado(res.getString("dm_finalizado"));
        ed.setOID_Unidade(res.getLong("oid_unidade_emissora"));
        ed.setOID_Empresa(res.getInt("OID_Empresa"));
        ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
        ed.setVL_Base_Calculo_ICMS_Substituicao(res.getDouble("VL_Base_Calculo_ICMS_Substituicao"));
        ed.setVL_ICMS_Substituicao(res.getDouble("VL_ICMS_Substituicao"));
        ed.setVL_Total_Produtos(res.getDouble("VL_Total_Produtos"));
     
        sql = "SELECT * FROM empresas WHERE oid_empresa = " + ed.getOID_Empresa();
        res2 = this.executasql.executarConsulta(sql);
        while( res2.next() ){
          ed.setCD_Empresa( res2.getString( "CD_Empresa" ) );
          ed.setNM_Empresa( res2.getString( "NM_Empresa" ) );
        }


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
    try{
      sql = "SELECT * FROM notas_fiscais_wms WHERE 1=1 ";

     if(edV.getNr_nota_fiscal()!= 0)
         sql += "and nr_nota_fiscal="+edV.getNr_nota_fiscal();

     if(edV.getNm_serie()!= null)
        sql += " and nm_serie ='"+edV.getNm_serie()+"' ";
      
     if( edV.getOID_Unidade() != 0 )
        sql += " and OID_Unidade_Emissora = "+edV.getOID_Unidade();

     if((edV.getOid_pessoa_destinatario()!= null)&&(!edV.getOid_pessoa_destinatario().equals("")))
        sql += " and oid_pessoa_destinatario ='"+edV.getOid_pessoa_destinatario()+"' ";
      
     if((edV.getOid_pessoa_transportador()!= null)&&(!edV.getOid_pessoa_transportador().equals("")))
        sql += " and oid_pessoa_transportador ='"+edV.getOid_pessoa_transportador()+"' ";

     if((edV.getOid_pessoa()!= null)&&(!edV.getOid_pessoa().equals("")))
        sql += " and oid_pessoa ='"+edV.getOid_pessoa()+"' ";

     if((edV.getDt_emissao()!= null)&&(!edV.getDt_emissao().equals("")))
        sql += " and dt_emissao ='"+edV.getDt_emissao()+"' ";

     if((edV.getDm_finalizado()!= null)&&(!edV.getDm_finalizado().equals(""))){
         if(edV.getDm_finalizado().equals("T")){
        sql += " and (Dm_impresso ='S' or Dm_impresso ='N' or Dm_impresso ='C')";
         }else sql += " and Dm_impresso ='"+edV.getDm_finalizado()+"' ";
     }

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
        ed.setDM_Gerado(res.getString("DM_Gerado")); 
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
        ed.setVL_PIS(res.getDouble("vl_pis"));
        ed.setVL_CSLL(res.getDouble("vl_csll"));
        ed.setVL_Cofins(res.getDouble("vl_cofins")); 
        ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
        ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
        ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
        ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
        ed.setDt_stamp(res.getString("dt_stamp"));
        ed.setDm_observacao(res.getString("dm_observacao"));     
        ed.setVl_descontos(res.getDouble("vl_descontos"));
        ed.setDm_finalizado(res.getString("dm_impresso"));
        ed.setOID_Unidade_Contabil(res.getLong("oid_unidade_fiscal"));
        ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_contabil"));
        ed.setOID_Unidade(res.getLong("oid_unidade_emissora"));
        
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
        
      if(ed.getDm_finalizado().equals("N"))
         ed.setDm_finalizado("Não Emitida");		 
      else if(ed.getDm_finalizado().equals("C"))
         ed.setDm_finalizado("Cancelada");
      else ed.setDm_finalizado("Emitida");


        sql = "select * from empresas where oid_empresa ="+ed.getOID_Empresa();
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          ed.setCD_Empresa( res2.getString( "CD_Empresa" ) );
          ed.setNM_Empresa( res2.getString( "NM_Empresa" ) );
        }

        ed.setOID_Empresa(res.getInt("OID_Empresa"));
        ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
        ed.setVL_Base_Calculo_ICMS_Substituicao(res.getDouble("VL_Base_Calculo_ICMS_Substituicao"));
        ed.setVL_ICMS_Substituicao(res.getDouble("VL_ICMS_Substituicao"));
        ed.setVL_Total_Produtos(res.getDouble("VL_Total_Produtos"));
      
         list.add(ed);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
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
              " DT_Emissao = '" + Data.getDataDMY() + "' ";
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
      wms008Bean wms = new wms008Bean();  
        
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
      wms008Bean wms = new wms008Bean();  
        
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
      wms008Bean wms = new wms008Bean();  
        
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
  
  public void atualiza_DM_Gerado(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;   

    try{ 
      sql = " UPDATE notas_fiscais_wms ";
      sql+= " SET dm_gerado = 'S'";
      sql+= " WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
           
      int i = executasql.executarUpdate(sql);
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("atualiza_DM_Gerado(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }    
  }
  
  public void concatena_Observacao(String novoOid, String str) throws Excecoes{
    String sql = null;   

    try{ 
       sql = "SELECT dm_observacao from notas_fiscais_wms where oid_nota_fiscal = '"+ novoOid +"'";
       ResultSet resAux = executasql.executarConsulta(sql);
       while( resAux.next() ){
           sql = "UPDATE notas_fiscais_wms set dm_observacao = '"+ resAux.getString( "dm_observacao" ) + ", " + str +"'";
           sql+= " WHERE oid_nota_fiscal = '"+ novoOid +"'";                                         

           int i = executasql.executarUpdate(sql);           
       }                  
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("concatena_Observacao(WMS_Nota_FiscalED ed, String str)");
      excecoes.setExc(exc);
      throw excecoes;
    }    
  }
  
  public void insere_Nota_Devolucao( WMS_Nota_Fiscal_DevolucaoED ed ) throws Excecoes{
    String sql = null;   

    try{  
   
        if( ed.getOID_Item_Saida_Cliente() != null ) 
            ed.setOID_Item_Saida_Cliente( "'"+ed.getOID_Item_Saida_Cliente()+"'" );
       
        if( ed.getOID_Nota_Fiscal_Saida_Cliente() != null ) 
            ed.setOID_Nota_Fiscal_Saida_Cliente( "'"+ed.getOID_Nota_Fiscal_Saida_Cliente()+"'" );
       
 
        if( ed.getOID_Nota_Fiscal_Entrada_Cliente() != null && !ed.getOID_Nota_Fiscal_Entrada_Cliente().equals( "" ) ){
            sql = "INSERT into notas_fiscais_devolucao ";
            sql+= "( oid_nota_fiscal_devolucao, ";
            sql+= "oid_nota_fiscal_saida_cliente, ";
            sql+= "oid_nota_fiscal_entrada_cliente, ";  
            sql+= "oid_nota_fiscal_saida, ";
            sql+= "oid_item_saida_cliente, ";
            sql+= "oid_item_entrada_cliente, ";       
            sql+= "nr_quantidade ) ";
            sql+= "values ( ";
            sql+= ed.getOID_Nota_Fiscal_Devolucao() + ",";
            sql+= ed.getOID_Nota_Fiscal_Saida_Cliente() + ",'";
            sql+= ed.getOID_Nota_Fiscal_Entrada_Cliente() + "','";   
            sql+= ed.getOID_Nota_Fiscal_Saida() + "',";
            sql+= ed.getOID_Item_Saida_Cliente() + ",'";
            sql+= ed.getOID_Item_Entrada_Cliente() + "',";       
            sql+= ed.getNR_Quantidade() + ")";
        }else{
            sql = "INSERT into notas_fiscais_devolucao ";
            sql+= "( oid_nota_fiscal_devolucao, ";
            sql+= "oid_nota_fiscal_saida_cliente, ";     
            sql+= "oid_nota_fiscal_saida, ";
            sql+= "oid_item_saida_cliente, ";            
            sql+= "nr_quantidade ) ";
            sql+= "values ( ";
            sql+= ed.getOID_Nota_Fiscal_Devolucao() + ",";
            sql+= ed.getOID_Nota_Fiscal_Saida_Cliente() + ",'";    
            sql+= ed.getOID_Nota_Fiscal_Saida() + "',";
            sql+= ed.getOID_Item_Saida_Cliente() + ",";                
            sql+= ed.getNR_Quantidade() + ")";           
        }   
    
        int i = executasql.executarUpdate(sql);                   
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("insere_Nota_Devolucao( WMS_Nota_Fiscal_DevolucaoED ed )");
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
  
  public boolean isCancelada(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;
    ResultSet res = null;
    boolean impresso = false;

    try{         
       sql = "Select * ";
       sql+= "from notas_fiscais_wms Where oid_Nota_Fiscal ='"+ed.getOid_nota_fiscal()+"'";
       res = executasql.executarConsulta(sql);
       while(res.next()){
          if( res.getString( "dm_impresso" ).equals( "C" ) ) impresso = true;
       }
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("isCancelada(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return impresso;
  }
  
  public void verificaItens(WMS_Nota_FiscalED edV) throws Excecoes{
    int nr_qt_saiu = 0;
    int nr_qt_tirar = 0;
    int nr_qt_disponivel = 0;
    int valOid = 0;
    int valOid2 = 0;
    int nr_qt_devolucao = 0;   
    String novoOid = "";
    String sql = null;   
    ResultSet resSaida = null, resEntrada = null, rs = null, res = null, resAux = null;
        
    try{     
       wms008Bean wms = new wms008Bean();        
       WMS_Nota_FiscalRN RN = new WMS_Nota_FiscalRN(); 
       WMS_Nota_FiscalED ED = new WMS_Nota_FiscalED(); 
       WMS_Nota_FiscalED ed2 = new WMS_Nota_FiscalED();
       WMS_Item_Produto_ClienteRN itemRN = new WMS_Item_Produto_ClienteRN();
       WMS_Item_Produto_ClienteED itemED = new WMS_Item_Produto_ClienteED();
       WMS_Nota_Fiscal_DevolucaoED devED = new WMS_Nota_Fiscal_DevolucaoED();
   
       sql = "SELECT * FROM notas_fiscais_wms ";
       sql+= "WHERE dm_tipo_nota_fiscal ='S' ";     
       sql+= "AND dm_impresso is null ";
       
       if( edV.getOID_Unidade() != 0 )
           sql+= " AND oid_unidade_emissora = " + edV.getOID_Unidade();
       
       if( edV.getOid_pessoa() != null && !edV.getOid_pessoa().equals( "" ) )
           sql+= " AND oid_pessoa = '" + edV.getOid_pessoa() + "'";
       
       if( edV.getDt_emissao() != null && !edV.getDt_emissao().equals( "" ) )
           sql+= " AND Dt_emissao >= '" + edV.getDt_emissao() + "'";
       
       if( edV.getDt_emissao_final() != null && !edV.getDt_emissao_final().equals( "" ) )
           sql+= " AND Dt_emissao <= '" + edV.getDt_emissao_final() + "'";

       resSaida = executasql.executarConsulta(sql);
       while( resSaida.next() ){     
         
           sql = "SELECT * FROM itens_produtos_clientes ";
           sql+= "WHERE itens_produtos_clientes.oid_nota_fiscal = '"+ resSaida.getString( "oid_Nota_Fiscal" ) +"'";
           sql+= "AND ( itens_produtos_clientes.nr_quantidade_movimento > itens_produtos_clientes.nr_quantidade_devolucao OR itens_produtos_clientes.nr_quantidade_devolucao is null ) ";
           res = executasql.executarConsulta(sql);
           while(res.next()){                  
                    
                sql = "SELECT * FROM itens_produtos_clientes, notas_fiscais_wms ";
                sql+= "WHERE itens_produtos_clientes.oid_nota_fiscal = notas_fiscais_wms.oid_nota_fiscal ";
                sql+= "AND ( itens_produtos_clientes.nr_quantidade_movimento > itens_produtos_clientes.nr_quantidade_devolucao OR itens_produtos_clientes.nr_quantidade_devolucao is null ) ";
                sql+= "AND notas_fiscais_wms.dm_tipo_nota_fiscal ='E' ";
                sql+= "AND itens_produtos_clientes.oid_produto_cliente = '"+res.getString( "oid_produto_cliente" )+"'";                
                
                   if( edV.getOID_Unidade() != 0 )
                       sql+= " AND notas_fiscais_wms.oid_unidade_emissora = " + edV.getOID_Unidade();

                   if( edV.getOid_pessoa() != null && !edV.getOid_pessoa().equals( "" ) )
                       sql+= " AND notas_fiscais_wms.oid_pessoa = '" + edV.getOid_pessoa() + "'";                 
                
                sql+= " ORDER BY notas_fiscais_wms.dt_emissao "; 
                

                nr_qt_saiu = 0;
                nr_qt_devolucao = 0;
                nr_qt_tirar = res.getInt( "nr_quantidade_movimento" ) - res.getInt( "nr_quantidade_devolucao" );

                resEntrada = executasql.executarConsulta(sql);
                while(resEntrada.next() && nr_qt_saiu < nr_qt_tirar ){
         
                    WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();						

                    nr_qt_disponivel = resEntrada.getInt( "nr_quantidade_movimento" ) - resEntrada.getInt( "nr_quantidade_devolucao" );	

                    if ( ( nr_qt_tirar - nr_qt_saiu ) > nr_qt_disponivel ){
                       nr_qt_devolucao = nr_qt_disponivel;                   
                    }
                    else{
                       nr_qt_devolucao = nr_qt_tirar - nr_qt_saiu;
                    }					

                    nr_qt_saiu += nr_qt_devolucao;	

                    // 1) GRAVA qtde_devolucao na nota_fiscal de entrada                       
                    itemED.setOid_Nota_Fiscal( resEntrada.getString( "oid_Nota_Fiscal" ) );
                    itemED.setNr_Quantidade_Devolucao( nr_qt_devolucao );
                    itemED.setOid_Item_Produto_Cliente( resEntrada.getString( "oid_item_produto_cliente" ) );
                    RN.atualiza_Quantidade_Devolucao( itemED );

                    // 2) GRAVA qtde_devolucao na nota_fiscal de saida
                    itemED.setOid_Nota_Fiscal( resSaida.getString( "oid_Nota_Fiscal" ) );
                    itemED.setNr_Quantidade_Devolucao( nr_qt_devolucao );
                    itemED.setOid_Item_Produto_Cliente( res.getString( "oid_item_produto_cliente" ) );
                    RN.atualiza_Quantidade_Devolucao( itemED );
               
                    // 3) INCLUI (SE AINDA NAO FOI) a nota_fiscal delta                  
                                        
                    novoOid = RN.isInclui(edV);
                    if( novoOid.equals( "null" ) ){  
                        
                        ed.setOid_pessoa( edV.getOid_pessoa() );
                        
                        sql = " select pessoas.oid_pessoa AS REMETENTE from unidades, pessoas ";
                        sql+= " where unidades.oid_unidade = "+edV.getOID_Unidade();         
                        sql+= " and pessoas.oid_pessoa = unidades.oid_pessoa";
                        rs = executasql.executarConsulta( sql );
                        while(rs.next()){
                            ed.setOid_pessoa( rs.getString( "REMETENTE" ) );                            
                        } 
                                            
                      ed.setOid_pessoa_destinatario( "10948651000161" );              
                      ed.setOid_natureza_operacao( 156 );          
                      ed.setOID_Unidade_Fiscal( 1 );
                      ed.setOID_Unidade_Contabil( 1 );
                      ed.setOID_Unidade( edV.getOID_Unidade() );
                      ed.setOID_Empresa( 2 );                   
                      ed.setDt_entrada( Data.getDataDMY() );
                      ed.setHr_entrada( Data.getHoraHM() );
                      ed.setOid_pessoa_transportador( "33333333333" );
                      ed.setVL_Base_Calculo_ICMS( 0 );
                      ed.setVL_Base_Calculo_ICMS_Substituicao( 0 ); 
                      ed.setVL_ICMS_Substituicao( 0 );
                      ed.setVL_Total_Produtos( 0 );   
                      ed.setOid_modelo_nota_fiscal( 0 );
                      ed.setVl_total_frete( 0 );
                      ed.setVl_icms( 0 );
                      ed.setVL_Servico( 0 );
                      ed.setVl_inss( 0 );
                      ed.setVL_PIS( 0 );
                      ed.setVL_CSLL( 0 );
                      ed.setVL_Cofins( 0 );
                      ed.setVl_irrf( 0 );
                      ed.setVl_ipi( 0 );
                      ed.setVl_isqn( 0 );
                      ed.setVl_total_seguro( 0 );
                      ed.setVl_total_despesas( 0 );
                      ed.setVl_nota_fiscal( 0 );
                      ed.setVl_liquido_nota_fiscal( 0 );
                      ed.setDm_tipo_nota_fiscal( "S" );                
                      ed.setDm_observacao( "ICMS NÃO INCIDENTE CFM LIVRO I ART 11 INC XI DECRETO 37699/97 REF NF: "+ resEntrada.getString( "nr_nota_fiscal" ) );
                      ed.setVl_descontos( 0 );
                      ed.setDM_Gerado( edV.getDM_Gerado() );

                      ed2 = RN.inclui( ed );                 

                      itemED.setOid_Nota_Fiscal( ed2.getOid_nota_fiscal() );
                    }else{           
                      itemED.setOid_Nota_Fiscal( novoOid );                      
                      RN.concatena_Observacao( novoOid, resEntrada.getString( "nr_nota_fiscal" ) );                    
                    }                   
                 
                    // 4) INCLUI registro na itens_produtos_clientes da nota fiscal de saida/delta
                    itemED.setOid_Tipo_Estoque( resEntrada.getInt( "OID_Tipo_Estoque" ) );
                    itemED.setOid_Produto_Cliente( resEntrada.getString( "oid_Produto_Cliente" ) );
                    itemED.setVl_Produto( resEntrada.getDouble( "vl_produto" ) );
                    itemED.setNr_Quantidade_Movimento( nr_qt_devolucao );  
                    itemRN.inclui( itemED );

                    // 5) INCLUI registro na nota_fiscal_devolucao
                    sql = "select max(oid_nota_fiscal_devolucao) as result from notas_fiscais_devolucao ";              
                    rs = executasql.executarConsulta( sql );
                    while(rs.next()){
                        valOid = rs.getInt( "result" );                            
                    }  
              
                    devED.setOID_Nota_Fiscal_Devolucao( ++valOid );                  
                    devED.setOID_Nota_Fiscal_Saida_Cliente( res.getString( "oid_nota_fiscal" ) );                  
                    devED.setOID_Nota_Fiscal_Entrada_Cliente( resEntrada.getString( "oid_nota_fiscal" ) );  
                    devED.setOID_Nota_Fiscal_Saida( itemED.getOid_Nota_Fiscal() );
                    devED.setOID_Item_Saida_Cliente( res.getString( "oid_item_produto_cliente" ) );          
                    devED.setOID_Item_Entrada_Cliente( resEntrada.getString( "oid_item_produto_cliente" ) );                  
                    devED.setNR_Quantidade( nr_qt_devolucao );                   

                    RN.insere_Nota_Devolucao( devED );
                   
                    ED.setOid_nota_fiscal( itemED.getOid_Nota_Fiscal() );
        
                    wms.atualiza_Total_Nota(ED);
                    wms.atualiza_Total_Produtos(ED);
                    wms.atualiza_Quantidade_Itens(ED);
                }
           }
       }      
             
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("verificaItens()");
      excecoes.setExc(exc);
      throw excecoes;
    }  
  }
  
  public String isInclui(WMS_Nota_FiscalED edV) throws Excecoes{
    String sql = null;
    ResultSet res = null, rs = null;
    int cont = 0;
    boolean isInclui = true;   
    String volta = "null";
    try{    
              
       sql = "SELECT * FROM notas_fiscais_wms ";
       sql+= "WHERE dm_tipo_nota_fiscal ='S' ";    
       sql+= "AND dm_impresso = 'N' ";
       
       if( edV.getOID_Unidade() != 0 )
           sql+= " AND oid_unidade_emissora = " + edV.getOID_Unidade();
       
       if( edV.getOid_pessoa() != null && !edV.getOid_pessoa().equals( "" ) )
           sql+= " AND oid_pessoa = '" + edV.getOid_pessoa() + "'";    
       
       if( edV.getDM_Gerado() != null && !edV.getDM_Gerado().equals( "" ) )
           sql+= " AND DM_Gerado = '" + edV.getDM_Gerado() + "'";   
             

       res = executasql.executarConsulta(sql);
       while(res.next()){                     
            sql = "select max(nr_item_produto_cliente) as result from itens_produtos_clientes ";
            sql+= " where oid_nota_fiscal = '"+ res.getString( "oid_nota_fiscal" ) +"'";
            rs = executasql.executarConsulta( sql );
            while(rs.next()){
                if( rs.getInt( "result" ) < 13 ){
                    isInclui = false;
                    break;
                }           
            }           
            
            if( !isInclui ){                
                volta = res.getString( "oid_nota_fiscal" );                
                break;
            }
       }
       
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("isInclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }        
    return volta;
  }
  
  public void atualiza_Notas(WMS_Nota_FiscalED edV) throws Excecoes{
    String sql = null;
    ResultSet res = null, rs = null;
    int i = 0;
    String oid_produto_cliente = "";
   
    try{         
       sql = "SELECT * FROM notas_fiscais_devolucao ";       
       sql+= "WHERE oid_nota_fiscal_saida = '"+ edV.getOid_nota_fiscal() +"'";             

       res = executasql.executarConsulta(sql);
       while(res.next()){ 
                      
            sql = "UPDATE itens_produtos_clientes SET ";
            sql+= "nr_quantidade_devolucao = ( nr_quantidade_devolucao - " + res.getString( "nr_quantidade" ) +")"; 
            sql+= "WHERE oid_nota_fiscal = '"+ res.getString( "oid_nota_fiscal_saida_cliente" ) +"' ";
            sql+= "AND oid_item_produto_cliente = '"+ res.getString( "oid_item_saida_cliente" ) +"' ";           
             
            i = executasql.executarUpdate(sql);
            
            if( res.getString( "oid_nota_fiscal_entrada_cliente" ) != null && !res.getString( "oid_nota_fiscal_entrada_cliente" ).equals( "" ) ){          
                sql = "UPDATE itens_produtos_clientes SET ";
                sql+= "nr_quantidade_devolucao = ( nr_quantidade_devolucao - " + res.getString( "nr_quantidade" ) +")"; 
                sql+= "WHERE oid_nota_fiscal = '"+ res.getString( "oid_nota_fiscal_entrada_cliente" ) +"' ";
                sql+= "AND oid_item_produto_cliente = '"+ res.getString( "oid_item_entrada_cliente" ) +"' ";         

                i = executasql.executarUpdate(sql);            
            }
           
            sql = "DELETE from notas_fiscais_devolucao ";
            sql+= "WHERE oid_nota_fiscal_devolucao = " + res.getInt( "oid_nota_fiscal_devolucao" );
            
            i = executasql.executarUpdate(sql);          
       }
       
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("atualiza_Notas(WMS_Nota_FiscalED edV)");
      excecoes.setExc(exc);
      throw excecoes;
    }  
  }
  
  public void itens_Nao_Encontrados(WMS_Nota_FiscalED edV) throws Excecoes{   
    int nr_qt_tirar = 0;   
    int valOid = 0;
    int valOid2 = 0;  
    String novoOid = "";
    String sql = null;   
    ResultSet resSaida = null, res = null, rs = null;
        
    try{     
       wms008Bean wms = new wms008Bean();        
       WMS_Nota_FiscalRN RN = new WMS_Nota_FiscalRN(); 
       WMS_Nota_FiscalED ED = new WMS_Nota_FiscalED(); 
       WMS_Nota_FiscalED ed2 = new WMS_Nota_FiscalED();
       WMS_Item_Produto_ClienteRN itemRN = new WMS_Item_Produto_ClienteRN();
       WMS_Item_Produto_ClienteED itemED = new WMS_Item_Produto_ClienteED();
       WMS_Nota_Fiscal_DevolucaoED devED = new WMS_Nota_Fiscal_DevolucaoED();                    
           
       sql = "SELECT * FROM notas_fiscais_wms ";
       sql+= "WHERE dm_tipo_nota_fiscal ='S' ";     
       sql+= "AND dm_impresso is null ";
       
       if( edV.getOID_Unidade() != 0 )
           sql+= " AND oid_unidade_emissora = " + edV.getOID_Unidade();
       
       if( edV.getOid_pessoa() != null && !edV.getOid_pessoa().equals( "" ) )
           sql+= " AND oid_pessoa = '" + edV.getOid_pessoa() + "'";
       
       if( edV.getDt_emissao() != null && !edV.getDt_emissao().equals( "" ) )
           sql+= " AND Dt_emissao >= '" + edV.getDt_emissao() + "'";
       
       if( edV.getDt_emissao_final() != null && !edV.getDt_emissao_final().equals( "" ) )
           sql+= " AND Dt_emissao <= '" + edV.getDt_emissao_final() + "'";

       resSaida = executasql.executarConsulta(sql);
       while( resSaida.next() ){     
         
           sql = "SELECT * FROM itens_produtos_clientes ";
           sql+= "WHERE itens_produtos_clientes.oid_nota_fiscal = '"+ resSaida.getString( "oid_Nota_Fiscal" ) +"'";
           sql+= "AND ( itens_produtos_clientes.nr_quantidade_movimento > itens_produtos_clientes.nr_quantidade_devolucao OR itens_produtos_clientes.nr_quantidade_devolucao is null ) ";
           res = executasql.executarConsulta(sql);
                           
           while(res.next()){                                 
               
                    nr_qt_tirar = res.getInt( "nr_quantidade_movimento" ) - res.getInt( "nr_quantidade_devolucao" );             
         
                    WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();					
                
                    // 1) GRAVA qtde_devolucao na nota_fiscal de saida
                    itemED.setOid_Nota_Fiscal( resSaida.getString( "oid_Nota_Fiscal" ) );
                    itemED.setNr_Quantidade_Devolucao( nr_qt_tirar );
                    itemED.setOid_Item_Produto_Cliente( res.getString( "oid_item_produto_cliente" ) );
                    RN.atualiza_Quantidade_Devolucao( itemED );                    

                    // 2) INCLUI (SE AINDA NAO FOI) a nota_fiscal delta                      
                    
                    novoOid = RN.isInclui(edV);
                    if( novoOid.equals( "null" ) ){  
                      ed.setOid_pessoa( edV.getOid_pessoa() );
                      ed.setOid_pessoa_destinatario( "10948651000161" );              
                      ed.setOid_natureza_operacao( 156 );          
                      ed.setOID_Unidade_Fiscal( 1 );
                      ed.setOID_Unidade_Contabil( 1 );
                      ed.setOID_Unidade( edV.getOID_Unidade() );
                      ed.setOID_Empresa( 2 );                   
                      ed.setDt_entrada( Data.getDataDMY() );
                      ed.setHr_entrada( Data.getHoraHM() );
                      ed.setOid_pessoa_transportador( "33333333333" );
                      ed.setVL_Base_Calculo_ICMS( 0 );
                      ed.setVL_Base_Calculo_ICMS_Substituicao( 0 ); 
                      ed.setVL_ICMS_Substituicao( 0 );
                      ed.setVL_Total_Produtos( 0 );   
                      ed.setOid_modelo_nota_fiscal( 0 );
                      ed.setVl_total_frete( 0 );
                      ed.setVl_icms( 0 );
                      ed.setVL_Servico( 0 );
                      ed.setVl_inss( 0 );
                      ed.setVl_irrf( 0 );
                      ed.setVL_PIS( 0 );
                      ed.setVL_CSLL( 0 );
                      ed.setVL_Cofins( 0 );
                      ed.setVl_ipi( 0 );
                      ed.setVl_isqn( 0 );
                      ed.setVl_total_seguro( 0 );
                      ed.setVl_total_despesas( 0 );
                      ed.setVl_nota_fiscal( 0 );
                      ed.setVl_liquido_nota_fiscal( 0 );
                      ed.setDm_tipo_nota_fiscal( "S" );                
                      ed.setDm_observacao( "ICMS NÃO INCIDENTE CFM LIVRO I ART 11 INC XI DECRETO 37699/97 REF NF: " );
                      ed.setVl_descontos( 0 );
                      ed.setDM_Gerado( edV.getDM_Gerado() );
                      
                      ed2 = RN.inclui( ed );                 

                      itemED.setOid_Nota_Fiscal( ed2.getOid_nota_fiscal() );
                    }else{           
                      itemED.setOid_Nota_Fiscal( novoOid );
                    }

                    // 4) INCLUI registro na itens_produtos_clientes da nota fiscal de saida/delta
                    itemED.setOid_Tipo_Estoque( res.getInt( "OID_Tipo_Estoque" ) );
                    itemED.setOid_Produto_Cliente( res.getString( "oid_Produto_Cliente" ) );
                    itemED.setVl_Produto( 0 );                    
                    itemED.setNr_Quantidade_Movimento( nr_qt_tirar );  
                    itemRN.inclui( itemED );    
                    
                    
                    // 5) INCLUI registro na nota_fiscal_devolucao
                    sql = "select max(oid_nota_fiscal_devolucao) as result from notas_fiscais_devolucao ";              
                    rs = executasql.executarConsulta( sql );
                    while(rs.next()){
                        valOid = rs.getInt( "result" );                            
                    }  

                    devED.setOID_Nota_Fiscal_Devolucao( ++valOid );                  
                    devED.setOID_Nota_Fiscal_Saida_Cliente( res.getString( "oid_nota_fiscal" ) );   
                    devED.setOID_Nota_Fiscal_Saida( itemED.getOid_Nota_Fiscal() );  
                    devED.setOID_Item_Saida_Cliente( res.getString( "oid_item_produto_cliente" ) );   
                    devED.setNR_Quantidade( nr_qt_tirar );                   

                    RN.insere_Nota_Devolucao( devED );               
                    
                    ED.setOid_nota_fiscal( itemED.getOid_Nota_Fiscal() );
          
                    wms.atualiza_Total_Nota(ED);
                    wms.atualiza_Total_Produtos(ED);
                    wms.atualiza_Quantidade_Itens(ED);                
           }
       }       
       
       
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("itens_Nao_Encontrados()");
      excecoes.setExc(exc);
      throw excecoes;
    }  
  }
  
  public void atualiza_Quantidade_Devolucao(WMS_Item_Produto_ClienteED ed) throws Excecoes{
    String sql = null;   

    try{                                
      sql = "UPDATE itens_produtos_clientes set nr_quantidade_devolucao = ( nr_quantidade_devolucao + "+ ed.getNr_Quantidade_Devolucao() + ")";
      sql+= " WHERE oid_nota_fiscal = '"+ ed.getOid_Nota_Fiscal() +"'";
      sql+= " AND oid_item_produto_cliente = '"+ ed.getOid_Item_Produto_Cliente() +"'";
           
      int i = executasql.executarUpdate(sql);
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("atualiza_Quantidade_Devolucao(WMS_Item_Produto_ClienteED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }    
  }
  
  public void cancela_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
    String sql = null;   
    ResultSet res = null, rs = null;

    try{         
          WMS_Nota_FiscalED edNota = new WMS_Nota_FiscalED();
          WMS_Nota_FiscalED edNotaVolta = new WMS_Nota_FiscalED();
          WMS_Nota_FiscalRN rnNota = new WMS_Nota_FiscalRN();
          WMS_Item_Produto_ClienteED edItem = new WMS_Item_Produto_ClienteED();
          WMS_Item_Produto_ClienteED edItemVolta = new WMS_Item_Produto_ClienteED();
          WMS_Item_Produto_ClienteRN rnItem = new WMS_Item_Produto_ClienteRN();
          wms008Bean wms = new wms008Bean();

          sql = " UPDATE notas_fiscais_wms ";
          sql+= " SET dm_impresso = 'C'";
          sql+= " WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";

          int i = executasql.executarUpdate(sql);

          sql = " SELECT * from notas_fiscais_wms WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
          res = executasql.executarConsulta(sql);
          while( res.next() ){ 
              
            edNota.setOid_pessoa( res.getString( "Oid_pessoa" ) );
            edNota.setOid_pessoa_destinatario( res.getString( "Oid_pessoa_destinatario" ) );              
            edNota.setOid_natureza_operacao( res.getLong( "Oid_natureza_operacao" ) );          
            edNota.setOID_Unidade_Fiscal( res.getLong( "OID_Unidade_Fiscal" ) );
            edNota.setOID_Unidade_Contabil( res.getLong( "OID_Unidade_Contabil" ) );
            edNota.setOID_Unidade( res.getLong( "OID_Unidade_Emissora" ) );
            edNota.setOID_Empresa( res.getInt( "OID_Empresa" ) );                   
            edNota.setDt_entrada( res.getString( "Dt_entrada" ) );
            edNota.setHr_entrada( res.getString( "Hr_entrada" ) );
            edNota.setOid_pessoa_transportador( res.getString( "Oid_pessoa_transportador" ) );
            edNota.setVL_Base_Calculo_ICMS( res.getDouble( "VL_Base_Calculo_ICMS" ) );
            edNota.setVL_Base_Calculo_ICMS_Substituicao( res.getDouble( "VL_Base_Calculo_ICMS_Substituicao" ) ); 
            edNota.setVL_ICMS_Substituicao( res.getDouble( "VL_ICMS_Substituicao" ) );
            edNota.setVL_Total_Produtos( res.getDouble( "VL_Total_Produtos" ) );   
            edNota.setOid_modelo_nota_fiscal( res.getLong( "Oid_modelo_nota_fiscal" ) );
            edNota.setVl_total_frete( res.getDouble( "Vl_total_frete" ) );
            edNota.setVl_icms( res.getDouble( "Vl_icms" ) );
            edNota.setVL_Servico( res.getDouble( "VL_Servico" ) );
            edNota.setVl_inss( res.getDouble( "Vl_inss" ) );
            edNota.setVl_irrf( res.getDouble( "Vl_irrf" ) );
            edNota.setVL_PIS( res.getDouble( "VL_PIS" ) );
            edNota.setVL_CSLL( res.getDouble( "VL_CSLL" ) );
            edNota.setVL_Cofins( res.getDouble( "VL_Cofins" ) );
            edNota.setVl_ipi( res.getDouble( "Vl_ipi" ) );
            edNota.setVl_isqn( res.getDouble( "Vl_isqn" ) );
            edNota.setVl_total_seguro( res.getDouble( "Vl_total_seguro" ) );
            edNota.setVl_total_despesas( res.getDouble( "Vl_total_despesas" ) );
            edNota.setVl_nota_fiscal( res.getDouble( "Vl_nota_fiscal" ) );
            edNota.setVl_liquido_nota_fiscal( res.getDouble( "Vl_liquido_nota_fiscal" ) );
            edNota.setDm_tipo_nota_fiscal( "S" );                
            edNota.setDm_observacao( res.getString( "Dm_observacao" ) );
            edNota.setVl_descontos( res.getDouble( "Vl_descontos" ) );
            edNota.setDM_Gerado( res.getString( "DM_Gerado" ) );
                      
            edNotaVolta = rnNota.inclui( edNota );  
            
            sql = " SELECT * from itens_produtos_clientes WHERE oid_nota_fiscal ='"+ ed.getOid_nota_fiscal()+"'";
            rs = executasql.executarConsulta(sql);
            while( rs.next() ){ 
                
                edItem.setOid_Nota_Fiscal( edNotaVolta.getOid_nota_fiscal() );
                edItem.setOid_Tipo_Estoque( rs.getInt( "OID_Tipo_Estoque" ) );
                edItem.setOid_Produto_Cliente( rs.getString( "oid_Produto_Cliente" ) );
                edItem.setVl_Produto( rs.getDouble( "vl_produto" ) );                    
                edItem.setNr_Quantidade_Movimento( rs.getInt( "NR_Quantidade_Movimento" ) );  
                rnItem.inclui( edItem );    
                               
            }
            
            wms.atualiza_Quantidade_Itens( edNotaVolta );     

          }      
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("cancela_Nota(WMS_Nota_FiscalED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }    
  }

}
