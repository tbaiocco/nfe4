package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.ConhecimentoED;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.rl.ConhecimentoRL;
import com.master.rl.Conhecimento_InternacionalRL;
import com.master.root.CidadeBean;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;
import com.master.root.VendedorBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;


public class Conhecimento_InternacionalBD {
  Calcula_FreteBD Calcula_FreteBD = null;
  private ExecutaSQL executasql;
  Utilitaria util = null;

  public Conhecimento_InternacionalBD(ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria(executasql);
  }

  public Conhecimento_InternacionalED inclui (Conhecimento_InternacionalED ed) throws Excecoes{

    String sql = null;
    String chave = null;
    String DM_Quebra_Faturamento = null;

    ResultSet rs = null;
    
    boolean DM_Cotacao = true;
    boolean pk_Duplicada = false;
    boolean semPermisso = false;
    
    Conhecimento_InternacionalED conED = new Conhecimento_InternacionalED();
    ConhecimentoED conNacED = new ConhecimentoED();

    try{
        String oid_pessUn = UnidadeBean.getByOID_Unidade(ed.getOID_Unidade_Origem()).getOID_Pessoa();
        ed.setCD_Pais(PessoaBean.getByEndereco_Completo(PessoaBean.getByOID(oid_pessUn).getNR_CNPJ_CPF()).getCD_Pais());
        
// System.out.println("Conhec_InterBD.inclui() - sql permissos_paises = " + sql);

      rs = null;
      rs = this.executasql.executarConsulta(sql);
      
      String nr_Proximo = null;
      String nm_Serie = null;
      
      while (rs.next()){
       	nr_Proximo = rs.getString("nr_proximo");
      	nm_Serie = rs.getString("nm_serie");
      }
      
      ed.setDM_Exportacao_Importacao("E");
      ed.setNM_Serie(nm_Serie);
      ed.setNR_Conhecimento(new Long(nr_Proximo).longValue());
      
      sql = "SELECT nr_conhecimento FROM Conhecimentos_internacionais " +
      		" WHERE nr_conhecimento = " + ed.getNR_Conhecimento();
      sql +=" AND OID_UNIDADE = " + ed.getOID_Unidade_Origem();
      sql +=" AND OID_Unidade_Destino = " + ed.getOID_Unidade_Destino();
      
      rs = null;
      rs = this.executasql.executarConsulta(sql);
      if(rs.next()){
      	pk_Duplicada = true;
      	throw new Exception();
      }
      
      int u = executasql.executarUpdate(sql);

      chave = String.valueOf(ed.getOID_Unidade_Origem()) + String.valueOf(ed.getNR_Conhecimento()) + ed.getNM_Serie();
      ed.setOID_Conhecimento(chave);
      
      sql = " insert into Conhecimentos_Internacionais ( " +
      		"OID_Conhecimento," +//1
      		"NR_Conhecimento, " +//15
			
      		"OID_Pessoa, " +//2
      		"NM_Endereco_Remetente, " +//69
      		"NM_Cidade_Estado_Pais_Remetente, " +//86
      		"nr_cnpj_remetente_complementar, " +//3
      		"NM_Remetente, " +//68
			
      		"OID_Pessoa_Destinatario, " +//4
      		"nr_cnpj_destinatario_complementar, " +//5
      		"NM_Destinatario, " +//71
      		"NM_Endereco_Destinatario, " +//72
      		"NM_Cidade_Estado_Pais_Destinatario, " +//87
			
      		"OID_Pessoa_Consignatario, " +//6
      		"NM_Consignatario, " +//73
			"NM_Endereco_Consignatario, " +//74
			"NM_Cidade_Estado_Pais_Consignatario, " +//88
			"nr_cnpj_Consignatario_complementar, " +
			
      		"oid_pessoa_notificar, " +//89
      		"NM_Notificar, " +//90
			"NM_Endereco_Notificar, " +//91
			"NM_Cidade_Estado_Pais_Notificar, " +//92
			"nr_cnpj_notificar_complementar, " + 
			
      		"OID_Modal, " +//7
      		"OID_Unidade, " +//8
      		"OID_Unidade_Destino, " +//9
      		"OID_Cidade, " +//10
      		"OID_Cidade_Destino, " +//11
      		"OID_Cidade_Embarque, " +//12
      		"OID_Produto, " +//14
      		"NM_Serie, " +//16
      		"DM_Responsavel_Cobranca, " +//17
			
      		"TX_Observacao, " +//19
      		"TX_Observacao2, " +//20
      		"TX_Observacao3, " +//21
      		"TX_Observacao4, " +//22
      		"TX_Observacao5, " +//23
      		"TX_Observacao6, " +//24
      		"TX_Observacao7, " +//25
      		"TX_Observacao8, " +//26
      		"TX_Observacao9, " +//27
      		"TX_Observacao10, " +//28
      		"TX_Observacao11, " +//29
      		"TX_Observacao12, " +//30
      		"TX_Observacao13, " +//31
      		"TX_Observacao14, " +//32
      		"TX_Observacao15, " +//33
      		"TX_Observacao16, " +
      		"TX_Observacao17, " +
      		"TX_Observacao18, " +
			
      		"DM_Isento_Seguro, " +//34
      		"DT_Emissao, " +//35
      		"DM_Situacao, " +//36
			
      		"TX_Documentos, " +//37
      		"TX_Documentos2, " +//38
      		"TX_Documentos3, " +//39
      		"TX_Documentos4, " +//93
      		"TX_Documentos5, " +//94
      		"TX_Documentos6, " +//95
			
      		"TX_Declaracao, " +//40
      		"TX_Declaracao2, " +//41
      		"TX_Declaracao3, " +//42
      		"TX_Declaracao4, " +//43
      		"TX_Declaracao5, " +//99
      		"TX_Declaracao6, " +//100
      		"TX_Declaracao7, " +//101
      		"TX_Declaracao8, " +//102
			
      		"TX_Remetente, " +//44
			
      		"TX_Alfandega, " +//45
      		"TX_Alfandega2, " +//46
      		"TX_Alfandega3, " +//47
      		"TX_Alfandega4, " +//96
      		"TX_Alfandega5, " +//97
      		"TX_Alfandega6, " +//98
			
      		"NM_Gasto_Remetente1, " +//48
      		"NM_Gasto_Remetente2, " +//49
      		"NM_Gasto_Remetente3, " +//50
      		"NM_Gasto_Remetente4, " +//51
			
      		"VL_Gasto_Remetente1, " +//52
      		"VL_Gasto_Remetente2, " +//53
      		"VL_Gasto_Remetente3, " +//54
      		"VL_Gasto_Remetente4, " +//55
			
      		"VL_Gasto_Destinatario1, " +//56
      		"VL_Gasto_Destinatario2, " +//57
      		"VL_Gasto_Destinatario3, " +//58
      		"VL_Gasto_Destinatario4, " +//59
			
      		"VL_Frete, " +//60
      		"VL_Seguro, " +//61
      		"VL_Reembolso, " +//62
      		"VL_Peso, " +//63
      		"VL_Peso_Cubado, " +//64
      		"VL_Nota_Fiscal, " +//65
      		"NR_Volumes, " +//66
      		"NR_Original, " +//67
      		"CD_Pais, " +//75
      		"NR_Permisso, " +//76
      		"DM_Exportacao_Importacao, " +//77
      		"NR_Fatura, " +//78
      		"VL_Mercadoria, " +//79
      		"dm_1_original, " +//80
      		"dm_2_original, " +//81
      		"dm_3_original, " +//82
      		"OID_Produto_Custo, " +//84
      		"OID_Unidade_Negocio, " +//85
      		"nm_localidade_emissao, " +//103
      		"nm_localidade_embarque, " +//104
      		"nm_localidade_entrega, " +//105
      		"vl_dolar, " +//106
      		"oid_unidade_fronteira, " +//107
      		"oid_pessoa_cotacao, " +//108
      		"NM_cotacao, " +//109
      		"oid_pessoa_devedor_exportador, " +//110
      		"NM_devedor_exportador, " +//111
      		"oid_pessoa_devedor_importador, " +//112
      		"NM_devedor_importador, " +
      		"oid_Tabela_Frete, " +//113
      					
      		//TEO 18/04/05
      		"VL_Frete_Editado, " +//60
      		"VL_Peso_Editado, " +//63
      		"VL_Peso_Cubado_Editado, " +//64
      		"NR_Volumes_Editado, " +//66
      		"NR_Volumes_Observacao, " +//66
      		"VL_Mercadoria_Editado " +//79
      		", NM_Icoterm " +//79
      		", c15_vl_frete_peso " +
      		", c15_vl_ad_valorem " +
      		", c15_vl_taxas " +
      		", c15_vl_outros " +
      		", c15_vl_total " +
      		", dm_veiculo_novo " +
      		", vl_rctrc " +
      		", vl_desconto_rctrc " +
      		", vl_rctr_vi " +
      		", vl_rctr_dc " +
      		", oid_vendedor " +
      		", oid_coleta " +
      		", pe_Exportador " +
      		") values (";
      
      sql += "'"+ed.getOID_Conhecimento()+"'," +//1
			 ""+ed.getNR_Conhecimento()+"," +//15
	  
      		 "'"+ed.getOID_Pessoa()+"'," +//2
			 "'"+ed.getNM_Endereco_Remetente() + "'," +//69
			 "'"+ed.getNM_Cidade_Estado_Pais_Remetente() + "', " +//86
      	 	 "'"+ed.getNR_CNPJ_Remetente_Complementar()+"'," +//3
			 "'"+ed.getNM_Remetente() + "'," +//68
			
      		 "'"+ed.getOID_Pessoa_Destinatario()+"', " +//4
      		 "'"+ed.getNR_CNPJ_Destinatario_Complementar()+"', " +//5
			 "'"+ed.getNM_Destinatario() + "', " +//71
			 "'"+ed.getNM_Endereco_Destinatario() + "', " +//72
			 "'"+ed.getNM_Cidade_Estado_Pais_Destinatario() + "', " +//87
			 
			 "'"+ed.getOID_Pessoa_Consignatario()+"'," +//6
			 "'"+ed.getNM_Consignatario() + "'," +//73
			 "'"+ed.getNM_Endereco_Consignatario()  + "'," +//74
			 "'"+ed.getNM_Cidade_Estado_Pais_Consignatario()  + "'," +//88
			 "'"+ed.getNR_CPF_CNPJ_Consignatario_Complementar()  + "'," +
			 
			 "'"+ed.getOID_Pessoa_Notificar()+"'," + //89
			 "'"+ed.getNM_Notificar()+"'," + //90
			 "'"+ed.getNM_Endereco_Notificar()+"'," + //91
			 "'"+ed.getNM_Cidade_Estado_Pais_Notificar()+"'," + //92
			 "'"+ed.getNR_CPF_CNPJ_Notificar_Complementar()  + "'," +
			 
			 ""+ed.getOID_Modal()+"," +//7
			 ""+ed.getOID_Unidade_Origem()+"," +//8
			 ""+ed.getOID_Unidade_Destino()+"," +//9
			 ""+ed.getOID_Cidade()+"," +//10
			 ""+ed.getOID_Cidade_Destino()+"," +//11
			 ""+ed.getOID_Cidade_Embarque()+"," +//12
			 ""+ed.getOID_Produto()+"," +//14
			 "'"+ed.getNM_Serie()+"'," +//16
			 "'"+ed.getDM_Responsavel_Cobranca()+"'," +//17
			 "'"+ed.getTX_Observacao1()+"'," +//19
			 "'"+ed.getTX_Observacao2()+"'," +//20
			 "'"+ed.getTX_Observacao3()+"'," +//21
			 "'"+ed.getTX_Observacao4()+"'," +//22
			 "'"+ed.getTX_Observacao5()+"'," +//23
			 "'"+ed.getTX_Observacao6()+"'," +//24
			 "'"+ed.getTX_Observacao7()+"'," +//25
			 "'"+ed.getTX_Observacao8()+"'," +//26
			 "'"+ed.getTX_Observacao9()+"'," +//27
			 "'"+ed.getTX_Observacao10()+"'," +//28
			 "'"+ed.getTX_Observacao11()+"'," +//29
			 "'"+ed.getTX_Observacao12()+"'," +//30
			 "'"+ed.getTX_Observacao13()+"'," +//31
			 "'"+ed.getTX_Observacao14()+"'," +//32
			 "'"+ed.getTX_Observacao15()+"'," +//33
			 "'"+ed.getTX_Observacao16()+"'," +
			 "'"+ed.getTX_Observacao17()+"'," +
			 "'"+ed.getTX_Observacao18()+"'," +
			 "'"+ed.getDM_Isento_Seguro()+"'," +//34
			 "'"+ed.getDT_Emissao()+"'," +//35
			 "'"+"G"+"'," +//36
			 
			 "'"+ed.getTX_Documentos1()+"'," +//37
			 "'"+ed.getTX_Documentos2()+"'," +//38
			 "'"+ed.getTX_Documentos3()+"'," +//39
			 "'"+ed.getTX_Documentos4()+"'," +//93
			 "'"+ed.getTX_Documentos5()+"'," +//94
			 "'"+ed.getTX_Documentos6()+"'," +//95
			 
			 "'"+ed.getTX_Declaracao1()+"'," +//40
			 "'"+ed.getTX_Declaracao2()+"'," +//41
			 "'"+ed.getTX_Declaracao3()+"'," +//42
			 "'"+ed.getTX_Declaracao4()+"'," +//43
			 "'"+ed.getTX_Declaracao5()+"'," +//99
			 "'"+ed.getTX_Declaracao6()+"'," +//100
			 "'"+ed.getTX_Declaracao7()+"'," +//101
			 "'"+ed.getTX_Declaracao8()+"'," +//102		 
			 
			 "'"+ed.getTX_Remetente()+"'," +//44
			 
			 "'"+ed.getTX_Alfandega1()+"'," +//45
			 "'"+ed.getTX_Alfandega2()+"'," +//46
			 "'"+ed.getTX_Alfandega3()+"'," +//47
			 "'"+ed.getTX_Alfandega4()+"'," +//96
			 "'"+ed.getTX_Alfandega5()+"'," +//97
			 "'"+ed.getTX_Alfandega6()+"'," +//98			 
			 
			 "'"+ed.getNM_Gasto_Remetente1()+"'," +//48
			 "'"+ed.getNM_Gasto_Remetente2()+"'," +//49
			 "'"+ed.getNM_Gasto_Remetente3()+"'," +//50
			 "'"+ed.getNM_Gasto_Remetente4()+"'," +//51
			 " "+ed.getVL_Gasto_Remetente1()+"," +//52
			 " "+ed.getVL_Gasto_Remetente2()+"," +//53
			 " "+ed.getVL_Gasto_Remetente3()+"," +//54
			 " "+ed.getVL_Gasto_Remetente4()+"," +//55
			 " "+ed.getVL_Gasto_Destinatario1()+"," +//56
			 " "+ed.getVL_Gasto_Destinatario2()+"," +//57
			 " "+ed.getVL_Gasto_Destinatario3()+"," +//58
			 " "+ed.getVL_Gasto_Destinatario4()+"," +//59
			 " "+ed.getVL_Frete()+"," +//60
			 " "+ed.getVL_Seguro()+"," +//61
			 " "+ed.getVL_Reembolso()+"," +//62
			 " "+ed.getVL_Peso()+","  +//63
			 ed.getVL_Peso_Cubado() + "," +//64
			 " "+ed.getVL_Nota_Fiscal() + "," +//65
			 " "+ed.getNR_Volumes() + "," +//66
			 "1," +//67
			 "'"+ed.getCD_Pais() + "'," +//75
			 "'"+ed.getNR_Permisso()+ "'," +//76
			 "'"+ed.getDM_Exportacao_Importacao()  + "'," +//77
			 "'"+ed.getNR_Fatura() + "'," +//78
			 " "+ed.getVL_Mercadoria() + "," +//79
			 "'N'," +//80
			 "'N'," +//81
			 "'N'," +//82
			 (ed.getOID_Produto_Custo().equals("") ? "null" : ed.getOID_Produto_Custo()) + "," +//84
             (ed.getOID_Unidade_Negocio().equals("") ? "null" : ed.getOID_Unidade_Negocio()) + "," +//85
			 
			 "'"+ed.getNM_Cidade_Estado_Pais_Emissao()+ "'," +//103
			 "'"+ed.getNM_Cidade_Estado_Pais_Embarque() + "'," +//104
			 "'"+ed.getNM_Cidade_Estado_Pais_Entrega() + "'," +//105
			 ed.getVL_Dolar() + ", " +//106
			 ed.getOID_Unidade_Fronteira() +", " +//107
			 "'"+ed.getOID_Pessoa_Cotacao()+"', " + //108
			 "'"+ed.getNM_Cotacao()+"'," + //109
			 "'"+ed.getOID_Pessoa_Devedor_Exportador()+"', " + //110
			 "'"+ed.getNM_Devedor_Exportador()+"'," + //111
			 "'"+ed.getOID_Pessoa_Devedor_Importador()+"'," + //112
			 "'"+ed.getNM_Devedor_Importador()+"'," + //113
			 	+ ed.getOid_Tabela_Frete() + "," +
			 	
			 //TEO 18/04/05
			 " '"+ed.getVL_Frete_Editado()+"'," +
			 " '"+ed.getVL_Peso_Editado()+"',"  +
			 " '"+ed.getVL_Peso_Cubado_Editado() + "'," +
			 " '"+ed.getNR_Volumes_Editado() + "'," +
			 " '"+ed.getNR_Volumes_Observacao() + "'," +
			 " '"+ed.getVL_Mercadoria_Editado() + "'" +
			 ", '"+ed.getNM_Icoterm() + "'" +
			 ", "+ed.getC15_VL_Frete_Peso() + "" +
			 ", "+ed.getC15_VL_Ad_Valorem() + "" +
			 ", "+ed.getC15_VL_Taxas() + "" +
			 ", "+ed.getC15_VL_Outros() + "" +
			 ", "+ed.getC15_VL_Total() + "" +
			 ", '"+ed.getDM_Veiculo_Novo() + "'" +
			 ", "+ed.getVL_RCTRC() + "" +
			 ", "+ed.getVL_Desconto_RCTRC() + "" +
			 ", "+ed.getVL_RCTR_VI() + "" +
			 ", "+ed.getVL_RCTR_DC() + "" +
			 ", '"+ed.getOID_Vendedor() + "'" +
			 ", "+ed.getOID_Coleta() +
			 ", "+ed.getPE_Exportador() +
			 ")";

// System.out.println("Conhec_InternacBD.inclui() - sql2 = "+sql);
      
      int i = executasql.executarUpdate(sql);

      conED.setOID_Conhecimento(ed.getOID_Conhecimento());
      conED.setNR_Conhecimento(ed.getNR_Conhecimento());
      
      //Inclus�o de Faturas apartir do campo 17
      if(ed.getFaturas()!=null){
	      conED.setDm_Stamp("S");
	      conED.setUsuario_Stamp(" ");
	      conED.setDt_stamp(Data.getDataDMY());
	      conED.setHr_stamp(Data.getHoraHM());
	      ArrayList faturas = new ArrayList(ed.getFaturas());
// System.out.println("faturas = "+faturas.size());
	      if(faturas.size() > 0){
	          for(int t=0;t<faturas.size();t++){
	              conED.setNR_Fatura(String.valueOf(faturas.get(t)));
	              this.inclui_fatura(conED);
	          }
	      }
      }
      //Fim da inclus�o de faturas
      
      
    }

    catch(Exception exc){
    	exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      
      if(pk_Duplicada){
      	excecoes.setMensagem("O Nr de Conhecimento " + ed.getNR_Conhecimento() + " j� existe. Clique em voltar e troque o n�mero!");
      }else if (!DM_Cotacao){
        excecoes.setMensagem("Moeda sem cota��o na data!");
      }else if (semPermisso){
        excecoes.setMensagem("N�o existe n�mero de permisso para as unidades digitadas! Retorne e altere estes dados.");
      }else{
        excecoes.setMensagem("Erro ao incluir Conhecimento_Internacional. Contate o suporte!");
      }
      
      excecoes.setMetodo("inclui");
//      excecoes.setExc(exc);
      throw excecoes;
    }
    return conED;
  }

  public void altera(Conhecimento_InternacionalED ed) throws Excecoes{

    String sql = null;
    ConhecimentoED conNacED = new ConhecimentoED();
    boolean DM_Cotacao = true;
    String DM_Situacao = null;
    double VL_Cotacao = 0;
    boolean oidPaisesIguais = false;
    boolean semPermisso = false;

    try{

     /* sql = "Select CD_Pais FROM Paises, Cidades, Regioes_Estados, Estados, Regioes_Paises "+
            " where "+
            " cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado and"+
            " regioes_estados.oid_Estado = estados.oid_Estado and"+
            " Regioes_Estados.OID_Estado = Estados.OID_Estado and" +
            " Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais and" +
            " Regioes_Paises.OID_Pais = Paises.OID_Pais and "+
            " Cidades.oid_Cidade = '" + ed.getOID_Cidade()+ "'";

      ResultSet rsTP = this.executasql.executarConsulta(sql);
      String CD_Pais = null;
      while (rsTP.next()){
        CD_Pais = rsTP.getString("CD_Pais");
      }

      if (CD_Pais != null && CD_Pais.equals("BR")){
        ed.setDM_Exportacao_Importacao("E");
      }else{
        ed.setDM_Exportacao_Importacao("I");
      }

      String VL_Cotacao_Moeda = this.busca_Valor_Cotacao(ed.getDT_Emissao(), ed.getOID_Pessoa_Pagador());
      DM_Cotacao = true;
      VL_Cotacao = Double.parseDouble(VL_Cotacao_Moeda);
//      if(VL_Cotacao == 0){
//        Excecoes exc = new Excecoes();
//        DM_Cotacao = false;
//        exc.setMensagem("Sem cotacao disponivel para este dia");
//        throw exc;
//      }

      double VL_Total_Frete = ed.getVL_Gasto_Remetente1()+
          ed.getVL_Gasto_Remetente2()+
          ed.getVL_Gasto_Remetente3()+
          ed.getVL_Gasto_Remetente4()+
          ed.getVL_Gasto_Destinatario1()+
          ed.getVL_Gasto_Destinatario2()+
          ed.getVL_Gasto_Destinatario3()+
          ed.getVL_Gasto_Destinatario4();

      conNacED.setVL_TOTAL_FRETE(VL_Cotacao*VL_Total_Frete);

      sql = "Select DM_Situacao FROM Conhecimentos "+
            " where OID_Conhecimento = '" + ed.getOID_Conhecimento()+"'";

      ResultSet rs = this.executasql.executarConsulta(sql);

      while (rs.next()){
        DM_Situacao = rs.getString("DM_Situacao");
      }

    if(!DM_Situacao.equals("F")){

      Parametro_FixoED parametro_FixoED = new Parametro_FixoED();
      double VL_CSLL_Calculado = 0;
      if (ed.getDM_Exportacao_Importacao() != null && ed.getDM_Exportacao_Importacao().equals("E")){
        VL_CSLL_Calculado = (((VL_Cotacao*VL_Total_Frete) * parametro_FixoED.getPE_Aliquota_CSLL())/100);
      }

      /*sql = " update Conhecimentos set "+
      " VL_Total_Frete= " + VL_Total_Frete + '*' + VL_Cotacao_Moeda +
      ", vl_frete_dolar= " + VL_Total_Frete+
      ", VL_CSLL_Empresa= " + VL_CSLL_Calculado;
      sql += ", OID_Pessoa= '" + ed.getOID_Pessoa() +
	  "', OID_Pessoa_Destinatario= '" + ed.getOID_Pessoa_Destinatario() +
	  "', OID_Pessoa_Consignatario= '" + ed.getOID_Pessoa_Consignatario() +
	  "', OID_Cidade= " + ed.getOID_Cidade() +
	  ", OID_Cidade_Destino= " + ed.getOID_Cidade_Destino()+
            ", OID_Produto_Custo = " ;
      sql += ed.getOID_Produto_Custo().equals("") ? "null" : ed.getOID_Produto_Custo();
      sql += ", OID_Unidade_Negocio = " ;
      sql += ed.getOID_Unidade_Negocio().equals("") ? "null" : ed.getOID_Unidade_Negocio();

      if (ed.getNR_Fatura() != null && !ed.getNR_Fatura().equals("")
            && !ed.getNR_Fatura().equals("null")){
            sql += ", NR_Fatura = '" + ed.getNR_Fatura() + "'";
          }

      sql += ", OID_Pessoa_Pagador= '" + ed.getOID_Pessoa_Pagador() + "'";
      sql += " where OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'" ;
      //// System.out.println(sql);
      int u = executasql.executarUpdate(sql);

      Calcula_FreteBD = new Calcula_FreteBD(this.executasql);
//      Calcula_FreteBD.gera_Vencimento(ed.getOID_Conhecimento());

    }*/

        int oidCidadeOrigem = Integer.parseInt(String.valueOf(ed.getOID_Cidade()));
        String oid_Pais_Origem = CidadeBean.getByOID(oidCidadeOrigem).getCD_Pais();
        
        int oidPaisDestino = Integer.parseInt(String.valueOf(ed.getOID_Cidade_Destino()));
        String oid_Pais_Destino = CidadeBean.getByOID(oidPaisDestino).getCD_Pais();

        if(oid_Pais_Origem.equals(oid_Pais_Destino)){
          	oidPaisesIguais = true;
          	throw new Exception();
        }
    	
//        Permisso_UnidadeBean puBean = Permisso_UnidadeBean.getPermissoUnidadeByOidUnidades(
//        		ed.getOID_Unidade_Origem(), 
//				ed.getOID_Unidade_Destino(), 
//				ed.getOID_Unidade_Fronteira(), 
//				null); 
//
//        String nr_Permisso = puBean.getNr_Permisso();
//        
//        if(!JavaUtil.doValida(nr_Permisso)){
//        	semPermisso = true;
//        	throw new Exception();
//        }
        
      sql = " update Conhecimentos_Internacionais set " +
	  		
//	  		"NR_Permisso='" + nr_Permisso + "'," + 
	  
	  		"OID_Pessoa= '" + ed.getOID_Pessoa() + "', " +
	  		"nr_cnpj_remetente_complementar= '" + ed.getNR_CNPJ_Remetente_Complementar() + "', " +
			
			"OID_Pessoa_Destinatario= '" + ed.getOID_Pessoa_Destinatario() + "', " +
			"nr_cnpj_destinatario_complementar= '" + ed.getNR_CNPJ_Destinatario_Complementar() + "', " +
			
			"OID_Pessoa_Consignatario= '" + ed.getOID_Pessoa_Consignatario() + "', " +
			"NM_Endereco_Consignatario = '" + ed.getNM_Endereco_Consignatario() + "', " +
		    "nm_consignatario = '" + ed.getNM_Consignatario() + "', " +
		    "NM_Cidade_Estado_Pais_Consignatario = '" + ed.getNM_Cidade_Estado_Pais_Consignatario() + "', " +
		    "nr_cnpj_consignatario_complementar= '" + ed.getNR_CPF_CNPJ_Consignatario_Complementar() + "', " +
			
			"OID_Modal= " + ed.getOID_Modal() + ", " +
			"OID_Produto= " + ed.getOID_Produto() + ", " +
			"OID_Cidade= " + ed.getOID_Cidade() + ", " +
			"OID_Cidade_Destino= " + ed.getOID_Cidade_Destino() + ", " +
			"OID_Cidade_Embarque= " + ed.getOID_Cidade_Embarque() + ", " +
			"DM_Responsavel_Cobranca= '" + ed.getDM_Responsavel_Cobranca() + "', " +
			"oid_unidade_destino= '" + ed.getOID_Unidade_Destino() + "', " +
			
			"TX_Observacao= '" + ed.getTX_Observacao1() + "', " +
			"TX_Observacao2= '" + ed.getTX_Observacao2() + "', " +
			"TX_Observacao3= '" + ed.getTX_Observacao3() + "', " +
			"TX_Observacao4= '" + ed.getTX_Observacao4() + "', " +
			"TX_Observacao5= '" + ed.getTX_Observacao5() + "', " +
			"TX_Observacao6= '" + ed.getTX_Observacao6() + "', " +
			"TX_Observacao7= '" + ed.getTX_Observacao7() + "', " +
			"TX_Observacao8= '" + ed.getTX_Observacao8() + "', " +
			"TX_Observacao9= '" + ed.getTX_Observacao9() + "', " +
			"TX_Observacao10= '" + ed.getTX_Observacao10() + "', " +
			"TX_Observacao11= '" + ed.getTX_Observacao11() + "', " +
			"TX_Observacao12= '" + ed.getTX_Observacao12() + "', " +
			"TX_Observacao13= '" + ed.getTX_Observacao13() + "', " +
			"TX_Observacao14= '" + ed.getTX_Observacao14() + "', " +
			"TX_Observacao15= '" + ed.getTX_Observacao15() + "', " +
			"TX_Observacao16= '" + ed.getTX_Observacao16() + "', " +
			"TX_Observacao17= '" + ed.getTX_Observacao17() + "', " +
			"TX_Observacao18= '" + ed.getTX_Observacao18() + "', " +
			
			"DM_Isento_Seguro= '" + ed.getDM_Isento_Seguro() + "', " +
			"NR_Fatura= '" + ed.getNR_Fatura() + "', " +
			
			"TX_Documentos= '" + ed.getTX_Documentos1()+ "', " +
			"TX_Documentos2= '" + ed.getTX_Documentos2()+ "', " +
			"TX_Documentos3= '" + ed.getTX_Documentos3() + "', " +
			"TX_Documentos4= '" + ed.getTX_Documentos4()+ "', " +
			"TX_Documentos5= '" + ed.getTX_Documentos5()+ "', " +
			"TX_Documentos6= '" + ed.getTX_Documentos6() + "', " +
			
			"TX_Declaracao= '" + ed.getTX_Declaracao1() + "', " +
			"TX_Declaracao2= '" + ed.getTX_Declaracao2()+ "', " +
			"TX_Declaracao3= '" + ed.getTX_Declaracao3() + "', " +
			"TX_Declaracao4= '" + ed.getTX_Declaracao4() + "', " +
			"TX_Declaracao5= '" + ed.getTX_Declaracao5() + "', " +
			"TX_Declaracao6= '" + ed.getTX_Declaracao6()+ "', " +
			"TX_Declaracao7= '" + ed.getTX_Declaracao7() + "', " +
			"TX_Declaracao8= '" + ed.getTX_Declaracao8() + "', " +
			
			"TX_Alfandega= '" + ed.getTX_Alfandega1() + "', " +
			"TX_Alfandega2= '" + ed.getTX_Alfandega2() + "', " +
			"TX_Alfandega3= '" + ed.getTX_Alfandega3() + "', " +
			"TX_Alfandega4= '" + ed.getTX_Alfandega4() + "', " +
			"TX_Alfandega5= '" + ed.getTX_Alfandega5() + "', " +
			"TX_Alfandega6= '" + ed.getTX_Alfandega6() + "', " +
			
			"TX_Remetente= '" + ed.getTX_Remetente() + "', " +
			
			"NM_Gasto_Remetente1= '" + ed.getNM_Gasto_Remetente1() + "', " +
			"NM_Gasto_Remetente2= '" + ed.getNM_Gasto_Remetente2() + "', " +
			"NM_Gasto_Remetente3= '" + ed.getNM_Gasto_Remetente3() + "', " +
			"NM_Gasto_Remetente4= '" + ed.getNM_Gasto_Remetente4() +  "', " +
       
			"NM_Remetente= '" + ed.getNM_Remetente() + "', " +
			"NM_Endereco_Remetente= '" + ed.getNM_Endereco_Remetente() + "', " +
			"NM_Cidade_Estado_Pais_Remetente = '"+ed.getNM_Cidade_Estado_Pais_Remetente()+"', " + 
//			"NM_Endereco_Remetente2= '" + ed.getNM_Endereco_Remetente2() + "', " +
			
			"NM_Destinatario= '" + ed.getNM_Destinatario() + "', " +
			"NM_Endereco_Destinatario= '" + ed.getNM_Endereco_Destinatario() + "', " +
			"nm_cidade_estado_pais_destinatario = '" + ed.getNM_Cidade_Estado_Pais_Destinatario() + "', " + 
			
			"oid_pessoa_notificar= '" + ed.getOID_Pessoa_Notificar() + "', " +
			"NM_Notificar= '" + ed.getNM_Notificar() + "', " +
			"NM_Endereco_Notificar= '" + ed.getNM_Endereco_Notificar() + "', " +
			"NM_Cidade_Estado_Pais_Notificar= '" + ed.getNM_Cidade_Estado_Pais_Notificar() + "', " +
			"nr_cnpj_notificar_complementar= '" + ed.getNR_CPF_CNPJ_Notificar_Complementar() + "', " +
			   
			"OID_Produto_Custo = " + (ed.getOID_Produto_Custo().equals("") ? "null" : ed.getOID_Produto_Custo()) + ", " +
			"OID_Unidade_Negocio = " + (ed.getOID_Unidade_Negocio().equals("") ? "null" : ed.getOID_Unidade_Negocio()) + ", " +
			"OID_Tabela_Frete = " + ed.getOid_Tabela_Frete() + ", ";
			
      
            //if(!DM_Situacao.equals("F")){ 
            	sql += " VL_Gasto_Remetente1= " + ed.getVL_Gasto_Remetente1() + ", " +
            		   "VL_Gasto_Remetente2= " + ed.getVL_Gasto_Remetente2() + ", " +
					   "VL_Gasto_Remetente3= " + ed.getVL_Gasto_Remetente3() + ", " +
					   "VL_Gasto_Remetente4= " + ed.getVL_Gasto_Remetente4() + ", " +
					   "VL_Gasto_Destinatario1= " + ed.getVL_Gasto_Destinatario1() + ", " +
					   "VL_Gasto_Destinatario2= " + ed.getVL_Gasto_Destinatario2() + ", " +
					   "VL_Gasto_Destinatario3= " + ed.getVL_Gasto_Destinatario3() + ", " +
					   "VL_Gasto_Destinatario4= " + ed.getVL_Gasto_Destinatario4() + ", " ;
            //}
            
            sql += "VL_Frete= " + ed.getVL_Frete()+ ", " +
            	   "VL_Reembolso= " + ed.getVL_Reembolso() + ", " +
				   "VL_Peso= " + ed.getVL_Peso() +  ", " +
				   "VL_Peso_Cubado= " + ed.getVL_Peso_Cubado() + ", " +
				   "DT_Emissao= '" + ed.getDT_Emissao() + "', " +
				   "VL_Nota_Fiscal= " + ed.getVL_Nota_Fiscal() + ", " +
				   "NR_Volumes= " + ed.getNR_Volumes() + ", " +
				   "NR_Original= " + ed.getNR_Original() + ", " +
				   "VL_Seguro= " + ed.getVL_Seguro() + ", " +
				   "VL_Mercadoria= " + ed.getVL_Mercadoria() + ", " +

				   
				   //TEO 18/04/05
				   "VL_Frete_Editado= '" + ed.getVL_Frete_Editado()+ "', " +
				   "VL_Peso_Editado= '" + ed.getVL_Peso_Editado() +  "', " +
				   "VL_Peso_Cubado_Editado= '" + ed.getVL_Peso_Cubado_Editado() + "', " +
				   "NR_Volumes_Editado= '" + ed.getNR_Volumes_Editado() + "', " +
				   "NR_Volumes_Observacao= '" + ed.getNR_Volumes_Observacao() + "', " +
				   "VL_Mercadoria_Editado= '" + ed.getVL_Mercadoria_Editado() + "', " +
				   
				   "nm_icoterm= '"+ed.getNM_Icoterm()+ "'," +
				   
				   "c15_VL_Frete_Peso= " + ed.getC15_VL_Frete_Peso() + ", " +
				   "c15_VL_Ad_Valorem= " + ed.getC15_VL_Ad_Valorem() + ", " +
				   "c15_VL_Taxas= " + ed.getC15_VL_Taxas() + ", " +
				   "c15_VL_Outros= " + ed.getC15_VL_Outros() + ", " +
				   "c15_VL_Total= " + ed.getC15_VL_Total() + ", " +
				   
				   "dm_veiculo_novo= '"+ed.getDM_Veiculo_Novo()+ "'," +
				   "VL_desconto_rctrc= " + ed.getVL_Desconto_RCTRC() + ", " +
				   "VL_rctrc= " + ed.getVL_RCTRC() + ", " +
				   "VL_rctr_vi= " + ed.getVL_RCTR_VI() + ", " +
				   "VL_rctr_dc= " + ed.getVL_RCTR_DC() + ", " +
				   "oid_Vendedor= '" + ed.getOID_Vendedor() + "', " +
				   
				   "oid_coleta= " + ed.getOID_Coleta() + ", " +
				   
				   "nm_localidade_emissao= '"+ed.getNM_Cidade_Estado_Pais_Emissao()+ "'," +
				   "nm_localidade_embarque= '"+ed.getNM_Cidade_Estado_Pais_Embarque() + "'," +
				   "nm_localidade_entrega= '"+ed.getNM_Cidade_Estado_Pais_Entrega() + "', " +
				   "vl_dolar= "+ed.getVL_Dolar() + ", " +
				   //"oid_unidade_fronteira= " + ed.getOID_Unidade_Fronteira() + ", " +
				   
				   "oid_pessoa_cotacao = '"+ed.getOID_Pessoa_Cotacao()+"', " +
				   "nm_cotacao= '"+ed.getNM_Cotacao()+"', " +
				   
				   "oid_pessoa_devedor_exportador = '"+ed.getOID_Pessoa_Devedor_Exportador()+"', " +
				   "nm_devedor_exportador= '"+ed.getNM_Devedor_Exportador()+"', " +
				   
				   "oid_pessoa_devedor_importador = '"+ed.getOID_Pessoa_Devedor_Importador()+"', " +
				   "nm_devedor_importador= '"+ed.getNM_Devedor_Importador()+"' ";
				   
			if (ed.getDT_Conversao() != null && !ed.getDT_Conversao().equals("") && !ed.getDT_Conversao().equals("null")){
			    sql += ", dt_conversao = '" + ed.getDT_Conversao() + "' ";
			}
			if (String.valueOf(ed.getPE_Exportador()) != null && ed.getPE_Exportador() > 0){
			    sql += ", pe_exportador = " + ed.getPE_Exportador();
			}

            sql += " where OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'" ;

      // System.out.println("conhec_InterBD.altera() sql = "+sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (!DM_Cotacao){
        excecoes.setMensagem("Cota��o com problemas!!!");
      }else if (semPermisso){
        excecoes.setMensagem("N�o existe n�mero de permisso para as unidades digitadas! Retorne e altere estes dados.");
      }else if (oidPaisesIguais){
        excecoes.setMensagem("As cidades de origem e destino n�o podem ser do mesmo pa�s! Retorne e altere estes dados.");
      }else{
        excecoes.setMensagem("Erro ao alterar");
      }
      excecoes.setMetodo("alterar");
//      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera_Pagador(Conhecimento_InternacionalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Conhecimentos_Internacionais set OID_Pessoa_Pagador= '" + ed.getOID_Pessoa_Pagador() + "' ";
      sql += " where OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'" ;

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Conhecimento_InternacionalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Conhecimentos_Internacionais WHERE OID_Conhecimento = ";
      sql += "('" + ed.getOID_Conhecimento() + "')";

      int i = executasql.executarUpdate(sql);

      sql = "delete from Conhecimentos WHERE OID_Conhecimento = ";
      sql += "('" + ed.getOID_Conhecimento() + "')";

      int u = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Conhecimento_InternacionalED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = " select DT_Emissao, DT_Conversao, NR_Conhecimento, NR_Original, OID_Conhecimento, Conhecimentos_Internacionais.DM_Situacao, " +
      		" VL_Gasto_Remetente1, VL_Gasto_Remetente2, VL_Gasto_Remetente3, VL_Gasto_Remetente4, VL_Gasto_Destinatario1, VL_Gasto_Destinatario2, VL_Gasto_Destinatario3, VL_Gasto_Destinatario4, CD_Pais, NR_Permisso, " +
      		" Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
      		" from Conhecimentos_Internacionais, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario ";
      sql += " where Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade ";
      sql += " AND Conhecimentos_Internacionais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Conhecimentos_Internacionais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      //sql += " AND Conhecimentos_Internacionais.oid_Pessoa_Consignatario = Pessoa_Consignatario.oid_Pessoa ";
      if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
        sql += " and Conhecimentos_Internacionais.NR_Conhecimento = " + ed.getNR_Conhecimento();
      }
      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
        sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_Origem();
      }
      if (String.valueOf(ed.getOID_Unidade_Destino()) != null && !String.valueOf(ed.getOID_Unidade_Destino()).equals("0")){
        sql += " and Conhecimentos_Internacionais.OID_Unidade_destino = " + ed.getOID_Unidade_Destino();
      }
      if (String.valueOf(ed.getOID_Unidade_Fronteira()) != null && !String.valueOf(ed.getOID_Unidade_Fronteira()).equals("0")){
        sql += " and Conhecimentos_Internacionais.OID_Unidade_Fronteira = " + ed.getOID_Unidade_Fronteira();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")&& !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null && !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("") && !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("null")){
        sql += " and Conhecimentos_Internacionais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao()) != null && !String.valueOf(ed.getDT_Emissao()).equals("") && !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Fim()) != null && !String.valueOf(ed.getDT_Fim()).equals("") && !String.valueOf(ed.getDT_Fim()).equals("null")){
          sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDT_Fim() + "'";
        }
      if (String.valueOf(ed.getDM_Situacao()) != null && !String.valueOf(ed.getDM_Situacao()).equals("") && !String.valueOf(ed.getDM_Situacao()).equals("null") && !String.valueOf(ed.getDM_Situacao()).equals("T")){
          sql += " and Conhecimentos_Internacionais.DM_Situacao = '" + ed.getDM_Situacao() + "'";
        }
      if (JavaUtil.doValida(String.valueOf(ed.getOID_Coleta()))){
          sql += " and Conhecimentos_Internacionais.OID_Coleta = '" + ed.getOID_Coleta() + "'";
      }
//      if (String.valueOf(ed.getNR_Fatura()) != null &&
//          !String.valueOf(ed.getNR_Fatura()).equals("") &&
//          !String.valueOf(ed.getNR_Fatura()).equals("null")){
//        sql += " and Conhecimentos_Internacionais.NR_Fatura = '" + ed.getNR_Fatura() + "'";
//      }
// // System.out.println(ed.getNR_Fatura());
      if (String.valueOf(ed.getNR_Fatura()) != null &&
          !String.valueOf(ed.getNR_Fatura()).equals("") &&
          !String.valueOf(ed.getNR_Fatura()).equals("null")){
		sql += " AND Conhecimentos_internacionais.oid_conhecimento = Conhecimentos_internacionais_faturas.oid_conhecimento "+
		       " AND Conhecimentos_internacionais_faturas.nr_fatura LIKE '%" + ed.getNR_Fatura() + "%' ";
      }

      sql += " Order by Conhecimentos_Internacionais.oid_unidade, Conhecimentos_Internacionais.NR_Conhecimento ";

// System.out.println("Conhecimento_InternacionalBD.lista() sql = " + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setDT_Conversao(res.getString("DT_Conversao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Conversao());
        edVolta.setDT_Conversao(DataFormatada.getDT_FormataData());

        edVolta.setNR_Original(res.getLong("NR_Original"));
        edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
        edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
        //edVolta.setNM_Pessoa_Consignatario(res.getString("NM_Razao_Social_Consignatario"));
        
        edVolta.setDM_Situacao(res.getString("dm_situacao"));
       
        edVolta.setVL_Total_Frete(res.getDouble("VL_Gasto_Remetente1") + 
                res.getDouble("VL_Gasto_Remetente2") + 
                res.getDouble("VL_Gasto_Remetente3") + 
                res.getDouble("VL_Gasto_Remetente4") + 
                res.getDouble("VL_Gasto_Destinatario1") + 
                res.getDouble("VL_Gasto_Destinatario2") + 
                res.getDouble("VL_Gasto_Destinatario3") + 
                res.getDouble("VL_Gasto_Destinatario4"));
                
        String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
								res.getString("NR_Permisso") + ".";
			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
			for(int a = 0 ; a < completa_Nr_CRT ; a++){
				nr_CRT_Parcial = nr_CRT_Parcial + "0";
			}
		edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
        
        list.add(edVolta);
      }
    }

    catch(Exception exc){
        exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Conhecimento_InternacionalED getByRecord(Conhecimento_InternacionalED ed)
  throws Excecoes{

    String sql = null;
    String sqlBusca = "";

    Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

    try{

      sql = " select * from Conhecimentos_Internacionais where 1=1 ";

      if (JavaUtil.doValida(ed.getOID_Conhecimento())){
        sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
      }
      
      if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
        sql += " and NR_Conhecimento = " + ed.getNR_Conhecimento();
      }
      
      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
        sql += " and OID_Unidade = " + ed.getOID_Unidade_Origem();
      }

// System.err.println("get by rec cto int " + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){

        edVolta.setDM_Isento_Seguro(res.getString("DM_Isento_Seguro"));
        edVolta.setDM_Responsavel_Cobranca(res.getString("DM_Responsavel_Cobranca"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setDT_Conversao(res.getString("DT_Conversao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Conversao());
        edVolta.setDT_Conversao(DataFormatada.getDT_FormataData());

        edVolta.setNM_Serie(res.getString("NM_SERIE"));
        edVolta.setNR_Conhecimento(res.getLong("NR_CONHECIMENTO"));
        edVolta.setOID_Cidade(res.getLong("OID_CIDADE"));
        edVolta.setOID_Cidade_Destino(res.getLong("OID_CIDADE_DESTINO"));
        edVolta.setOID_Cidade_Embarque(res.getLong("OID_CIDADE_EMBARQUE"));
        edVolta.setOID_Conhecimento(res.getString("OID_CONHECIMENTO"));
        edVolta.setOID_Modal(res.getLong("OID_MODAL"));
        
        edVolta.setOID_Produto(res.getLong("OID_PRODUTO"));
        
        ResultSet resUN = null;
        edVolta.setOID_Unidade_Origem(res.getInt("OID_UNIDADE"));
        sqlBusca = "select unidades.cd_unidade, pessoas.nm_fantasia " +
        		   " from unidades, pessoas " +
        		   " where unidades.oid_pessoa = pessoas.oid_pessoa " +
        		   " and unidades.oid_unidade = "+ res.getInt("OID_UNIDADE");
        resUN = this.executasql.executarConsulta(sqlBusca);
        while(resUN.next()){
        	edVolta.setCD_Unidade_Origem(resUN.getString("cd_unidade"));
        	edVolta.setNM_Fantasia_Origem(resUN.getString("nm_fantasia"));
        }
        edVolta.setOID_Unidade_Destino(res.getInt("oid_unidade_destino"));
        sqlBusca = "select unidades.cd_unidade, pessoas.nm_fantasia " +
		   " from unidades, pessoas " +
		   " where unidades.oid_pessoa = pessoas.oid_pessoa " +
		   " and unidades.oid_unidade = "+ res.getInt("OID_UNIDADE_destino");
		resUN = this.executasql.executarConsulta(sqlBusca);
		while(resUN.next()){
			edVolta.setCD_Unidade_Destino(resUN.getString("cd_unidade"));
			edVolta.setNM_Fantasia_Destino(resUN.getString("nm_fantasia"));
		}
        edVolta.setOID_Unidade_Fronteira(res.getInt("oid_unidade_fronteira"));
        sqlBusca = "select unidades.cd_unidade, pessoas.nm_fantasia " +
		   " from unidades, pessoas " +
		   " where unidades.oid_pessoa = pessoas.oid_pessoa " +
		   " and unidades.oid_unidade = "+ res.getInt("OID_UNIDADE_fronteira");
		resUN = this.executasql.executarConsulta(sqlBusca);
		while(resUN.next()){
			edVolta.setCD_Unidade_Fronteira(resUN.getString("cd_unidade"));
			edVolta.setNM_Fantasia_Fronteira(resUN.getString("nm_fantasia"));
		}
        
        edVolta.setTX_Observacao1(res.getString("TX_Observacao"));
        edVolta.setTX_Observacao2(res.getString("TX_Observacao2"));
        edVolta.setTX_Observacao3(res.getString("TX_Observacao3"));
        edVolta.setTX_Observacao4(res.getString("TX_Observacao4"));
        edVolta.setTX_Observacao5(res.getString("TX_Observacao5"));
        edVolta.setTX_Observacao6(res.getString("TX_Observacao6"));
        edVolta.setTX_Observacao7(res.getString("TX_Observacao7"));
        edVolta.setTX_Observacao8(res.getString("TX_Observacao8"));
        edVolta.setTX_Observacao9(res.getString("TX_Observacao9"));
        edVolta.setTX_Observacao10(res.getString("TX_Observacao10"));
        edVolta.setTX_Observacao11(res.getString("TX_Observacao11"));
        edVolta.setTX_Observacao12(res.getString("TX_Observacao12"));
        edVolta.setTX_Observacao13(res.getString("TX_Observacao13"));
        edVolta.setTX_Observacao14(res.getString("TX_Observacao14"));
        edVolta.setTX_Observacao15(res.getString("TX_Observacao15"));
        edVolta.setTX_Observacao16(res.getString("TX_Observacao16"));
        edVolta.setTX_Observacao17(res.getString("TX_Observacao17"));
        edVolta.setTX_Observacao18(res.getString("TX_Observacao18"));
        
        edVolta.setTX_Alfandega1(res.getString("TX_Alfandega"));
        edVolta.setTX_Alfandega2(res.getString("TX_Alfandega2"));
        edVolta.setTX_Alfandega3(res.getString("TX_Alfandega3"));
        edVolta.setTX_Alfandega4(res.getString("TX_Alfandega4"));
        edVolta.setTX_Alfandega5(res.getString("TX_Alfandega5"));
        edVolta.setTX_Alfandega6(res.getString("TX_Alfandega6"));
        
        edVolta.setTX_Documentos1(res.getString("TX_Documentos"));
        edVolta.setTX_Documentos2(res.getString("TX_Documentos2"));
        edVolta.setTX_Documentos3(res.getString("TX_Documentos3"));
        edVolta.setTX_Documentos4(res.getString("TX_Documentos4"));
        edVolta.setTX_Documentos5(res.getString("TX_Documentos5"));
        edVolta.setTX_Documentos6(res.getString("TX_Documentos6"));
        
        edVolta.setTX_Declaracao1(res.getString("TX_Declaracao"));
        edVolta.setTX_Declaracao2(res.getString("TX_Declaracao2"));
        edVolta.setTX_Declaracao3(res.getString("TX_Declaracao3"));
        edVolta.setTX_Declaracao4(res.getString("TX_Declaracao4"));
        edVolta.setTX_Declaracao5(res.getString("TX_Declaracao5"));
        edVolta.setTX_Declaracao6(res.getString("TX_Declaracao6"));
        edVolta.setTX_Declaracao7(res.getString("TX_Declaracao7"));
        edVolta.setTX_Declaracao8(res.getString("TX_Declaracao8"));
        
        edVolta.setTX_Remetente(res.getString("TX_Remetente"));
        edVolta.setNM_Gasto_Remetente1(res.getString("NM_Gasto_Remetente1"));
        edVolta.setNM_Gasto_Remetente2(res.getString("NM_Gasto_Remetente2"));
        edVolta.setNM_Gasto_Remetente3(res.getString("NM_Gasto_Remetente3"));
        edVolta.setNM_Gasto_Remetente4(res.getString("NM_Gasto_Remetente4"));
        edVolta.setVL_Gasto_Remetente1(res.getDouble("VL_Gasto_Remetente1"));
        edVolta.setVL_Gasto_Remetente2(res.getDouble("VL_Gasto_Remetente2"));
        edVolta.setVL_Gasto_Remetente3(res.getDouble("VL_Gasto_Remetente3"));
        edVolta.setVL_Gasto_Remetente4(res.getDouble("VL_Gasto_Remetente4"));
        edVolta.setVL_Gasto_Destinatario1(res.getDouble("VL_Gasto_Destinatario1"));
        edVolta.setVL_Gasto_Destinatario2(res.getDouble("VL_Gasto_Destinatario2"));
        edVolta.setVL_Gasto_Destinatario3(res.getDouble("VL_Gasto_Destinatario3"));
        edVolta.setVL_Gasto_Destinatario4(res.getDouble("VL_Gasto_Destinatario4"));
        
        edVolta.setVL_Total_Frete(res.getDouble("VL_Gasto_Remetente1") + 
                                  res.getDouble("VL_Gasto_Remetente2") + 
                                  res.getDouble("VL_Gasto_Remetente3") + 
                                  res.getDouble("VL_Gasto_Remetente4") + 
                                  res.getDouble("VL_Gasto_Destinatario1") + 
                                  res.getDouble("VL_Gasto_Destinatario2") + 
                                  res.getDouble("VL_Gasto_Destinatario3") + 
                                  res.getDouble("VL_Gasto_Destinatario4"));
        
        edVolta.setVL_Frete(res.getDouble("VL_Frete"));
        edVolta.setVL_Seguro(res.getDouble("VL_Seguro"));
        edVolta.setVL_Mercadoria(res.getDouble("VL_Mercadoria"));
        edVolta.setVL_Reembolso(res.getDouble("VL_Reembolso"));
        edVolta.setVL_Dolar(res.getDouble("vl_dolar"));
        
        edVolta.setPE_Exportador(res.getDouble("pe_exportador"));
        
        edVolta.setVL_Peso(res.getDouble("VL_Peso"));
        edVolta.setVL_Peso_Cubado(res.getDouble("VL_Peso_Cubado"));
        ed.setVL_Peso_Cubado_Calculado(res.getDouble("VL_Peso_Cubado_Calculado"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
        edVolta.setNR_Volumes(res.getDouble("NR_Volumes"));
        edVolta.setNR_Original(res.getLong("NR_Original"));

        edVolta.setOID_Pessoa(res.getString("OID_PESSOA"));
        edVolta.setNM_Remetente(res.getString("NM_Remetente"));
        edVolta.setNM_Endereco_Remetente(res.getString("NM_Endereco_Remetente"));
        edVolta.setNM_Cidade_Estado_Pais_Remetente(res.getString("NM_Cidade_Estado_Pais_Remetente"));
        edVolta.setNR_CNPJ_Remetente_Complementar(res.getString("NR_CNPJ_Remetente_Complementar"));
        //edVolta.setNM_Endereco_Remetente2(res.getString("NM_Endereco_Remetente2"));
        
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_PESSOA_DESTINATARIO"));
        edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));
        edVolta.setNM_Endereco_Destinatario(res.getString("NM_Endereco_Destinatario"));
        edVolta.setNR_CNPJ_Destinatario_Complementar(res.getString("NR_CNPJ_Destinatario_Complementar"));
        edVolta.setNM_Cidade_Estado_Pais_Destinatario(res.getString("NM_Cidade_Estado_Pais_Destinatario"));
        
        edVolta.setOID_Pessoa_Consignatario(res.getString("OID_PESSOA_CONSIGNATARIO"));
        edVolta.setNM_Consignatario(res.getString("NM_Consignatario"));
        edVolta.setNM_Endereco_Consignatario(res.getString("NM_Endereco_Consignatario"));
        edVolta.setNM_Cidade_Estado_Pais_Consignatario(res.getString("NM_Cidade_Estado_Pais_Consignatario"));
        edVolta.setNR_CPF_CNPJ_Consignatario_Complementar(res.getString("NR_CNPJ_Consignatario_Complementar"));
        
        edVolta.setOID_Pessoa_Notificar(res.getString("OID_PESSOA_NOTIFICAR"));
        edVolta.setNM_Notificar(res.getString("NM_Notificar"));
        edVolta.setNM_Endereco_Notificar(res.getString("NM_Endereco_Notificar"));
        edVolta.setNM_Cidade_Estado_Pais_Notificar(res.getString("NM_Cidade_Estado_Pais_Notificar"));
        edVolta.setNR_CPF_CNPJ_Notificar_Complementar(res.getString("NR_CNPJ_Notificar_Complementar"));
        
        edVolta.setOID_Pessoa_Cotacao(res.getString("oid_pessoa_cotacao"));
        edVolta.setNM_Cotacao(res.getString("nm_cotacao"));

        edVolta.setOID_Pessoa_Devedor_Exportador(res.getString("oid_pessoa_devedor_exportador"));
        edVolta.setNM_Devedor_Exportador(res.getString("nm_devedor_exportador"));
        
        edVolta.setOID_Pessoa_Devedor_Importador(res.getString("oid_pessoa_devedor_importador"));
        edVolta.setNM_Devedor_Importador(res.getString("nm_devedor_importador"));
        
        edVolta.setCD_Pais(res.getString("CD_Pais"));
        edVolta.setNR_Permisso(res.getString("NR_Permisso"));
        edVolta.setDM_Exportacao_Importacao(res.getString("DM_Exportacao_Importacao"));
        edVolta.setNR_Fatura(res.getString("NR_Fatura"));
        
        edVolta.setNM_Cidade_Estado_Pais_Emissao(res.getString("nm_localidade_emissao"));
        edVolta.setNM_Cidade_Estado_Pais_Embarque(res.getString("nm_localidade_embarque"));
        edVolta.setNM_Cidade_Estado_Pais_Entrega(res.getString("nm_localidade_entrega"));
        
        edVolta.setDM_Situacao(res.getString("dm_situacao"));
        edVolta.setOid_Tabela_Frete(res.getLong("oid_Tabela_Frete"));
        
        //TEO 18/04/05
        edVolta.setVL_Frete_Editado(res.getString("vl_frete_editado"));
        edVolta.setVL_Mercadoria_Editado(res.getString("vl_mercadoria_editado"));
        edVolta.setVL_Peso_Editado(res.getString("vl_peso_editado"));
        edVolta.setVL_Peso_Cubado_Editado(res.getString("vl_peso_cubado_editado"));
        edVolta.setNR_Volumes_Editado(res.getString("nr_volumes_editado"));
        edVolta.setNR_Volumes_Observacao(res.getLong("nr_volumes_observacao"));
        
        edVolta.setNM_Icoterm(res.getString("nm_icoterm"));
        
        edVolta.setC15_VL_Frete_Peso(res.getDouble("c15_vl_frete_peso"));
        edVolta.setC15_VL_Ad_Valorem(res.getDouble("c15_vl_Ad_Valorem"));
        edVolta.setC15_VL_Taxas(res.getDouble("c15_vl_Taxas"));
        edVolta.setC15_VL_Outros(res.getDouble("c15_vl_Outros"));
        edVolta.setC15_VL_Total(res.getDouble("c15_vl_Total"));
        
        edVolta.setDM_Veiculo_Novo(res.getString("dm_veiculo_novo"));
        edVolta.setVL_RCTRC(res.getDouble("vl_rctrc"));
        edVolta.setVL_Desconto_RCTRC(res.getDouble("vl_desconto_rctrc"));
        edVolta.setVL_RCTR_VI(res.getDouble("vl_rctr_vi"));
        edVolta.setVL_RCTR_DC(res.getDouble("vl_rctr_dc"));
        
        edVolta.setOID_Vendedor(res.getString("oid_vendedor"));
        
        edVolta.setOID_Coleta(res.getLong("OID_Coleta"));
        sqlBusca = "select coletas.nr_coleta " +
		   " from coletas " +
		   " where oid_coleta = "+ res.getLong("OID_Coleta");
		resUN = this.executasql.executarConsulta(sqlBusca);
		while(resUN.next()){
			edVolta.setNR_Coleta(resUN.getLong("nr_coleta"));
		}
		util.closeResultset(resUN);

        if (res.getString("OID_Produto_Custo") != null &&
          !res.getString("OID_Produto_Custo").equals("null")){

          sql = "select * from Produtos_Custos, Unidades_Grupos, Unidades_Negocios, Grupos_Produtos where "+
          " Unidades_Grupos.oid_Unidade_Negocio = Unidades_Negocios.oid_Unidade_Negocio "+
          " and Unidades_Grupos.oid_Grupo_Produto = Grupos_Produtos.oid_Grupo_Produto "+
          " and Produtos_Custos.oid_Unidade_Grupo = Unidades_Grupos.oid_Unidade_Grupo ";
          sql += " AND oid_Produto_custo =" + res.getString("OID_Produto_Custo");

          ResultSet rs = null;
          rs = this.executasql.executarConsulta(sql);

          while (rs.next()){
            edVolta.setOID_Produto_Custo(rs.getString("OID_Produto_Custo"));
            edVolta.setNM_Produto_Custo(rs.getString("NM_Produto_Custo") + " - " + rs.getString("nm_Unidade_Negocio") + " - " + rs.getString("nm_Grupo_Produto"));
          }
        }

        if (res.getString("OID_Unidade_Negocio") != null &&
          !res.getString("OID_Unidade_Negocio").equals("null")){

          sql = "select * from Unidades_Negocios where ";
          sql += "oid_Unidade_Negocio =" + res.getString("OID_Unidade_Negocio");

          ResultSet rs = null;
          rs = this.executasql.executarConsulta(sql);

          while (rs.next()){
            edVolta.setOID_Unidade_Negocio(rs.getString("OID_Unidade_Negocio"));
            edVolta.setNM_Unidade_Negocio(rs.getString("NM_Unidade_Negocio"));
          }
        }
      }
    } catch(SQLException e){
      throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Conhecimento_InternacionalED ed)");
    }

    return edVolta;
  }

  public void geraRelatorio(Conhecimento_InternacionalED ed)throws Excecoes{

    String sql = null;

    Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

    try{

      sql = "select * from Conhecimentos_Internacionais where 1=1 ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      ConhecimentoRL Conhecimento_Internacional_rl = new ConhecimentoRL();
      Conhecimento_Internacional_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no m�todo listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Conhecimento_InternacionalED ed)");
    }

  }


  public byte[] geraRelConhecInternacionalAntigo(Conhecimento_InternacionalED ed)throws Excecoes{

      String sql = null;
      byte[] b = null;
      long valOid = 0;
      long NR_Proximo = 0;
      long NR_Final = 0;
      long NR_Conhecimento = 0;

        sql = " select "+
        " Conhecimentos_Internacionais.OID_MODAL, " +
        " Conhecimentos_Internacionais.OID_UNIDADE, " +
        " Conhecimentos_Internacionais.OID_UNIDADE_Destino, " +
        " Conhecimentos_Internacionais.OID_PRODUTO, " +
        " Conhecimentos_Internacionais.OID_CIDADE, " +
        " Conhecimentos_Internacionais.OID_CIDADE_EMBARQUE, " +
        " Conhecimentos_Internacionais.OID_CIDADE_DESTINO, " +
        " Conhecimentos_Internacionais.OID_PESSOA, " +
        " Conhecimentos_Internacionais.OID_PESSOA_DESTINATARIO, " +
        " Conhecimentos_Internacionais.OID_PESSOA_CONSIGNATARIO, " +
        " Conhecimentos_Internacionais.OID_PESSOA_NOTIFICAR, " +
        " Conhecimentos_Internacionais.NR_CONHECIMENTO, " +
        " Conhecimentos_Internacionais.NM_SERIE, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO2, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO3, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO4, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO5, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO6, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO7, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO8, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO9, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO10, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO11, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO12, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO13, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO14, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO15, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO16, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO17, " +
        " Conhecimentos_Internacionais.TX_OBSERVACAO18, " +
        " Conhecimentos_Internacionais.DT_EMISSAO, " +
        " Conhecimentos_Internacionais.TX_DOCUMENTOS, " +
        " Conhecimentos_Internacionais.TX_DOCUMENTOS2, " +
        " Conhecimentos_Internacionais.TX_DOCUMENTOS3, " +
        " Conhecimentos_Internacionais.TX_DECLARACAO, " +
        " Conhecimentos_Internacionais.TX_ALFANDEGA, " +
        " Conhecimentos_Internacionais.TX_REMETENTE, " +
        " Conhecimentos_Internacionais.NM_GASTO_REMETENTE1, " +
        " Conhecimentos_Internacionais.NM_GASTO_REMETENTE2, " +
        " Conhecimentos_Internacionais.NM_GASTO_REMETENTE3, " +
        " Conhecimentos_Internacionais.NM_GASTO_REMETENTE4, " +
        " Conhecimentos_Internacionais.VL_GASTO_REMETENTE1, " +
        " Conhecimentos_Internacionais.VL_GASTO_REMETENTE2, " +
        " Conhecimentos_Internacionais.VL_GASTO_REMETENTE3, " +
        " Conhecimentos_Internacionais.VL_GASTO_REMETENTE4, " +
        " Conhecimentos_Internacionais.VL_GASTO_DESTINATARIO1, " +
        " Conhecimentos_Internacionais.VL_GASTO_DESTINATARIO2, " +
        " Conhecimentos_Internacionais.VL_GASTO_DESTINATARIO3, " +
        " Conhecimentos_Internacionais.VL_GASTO_DESTINATARIO4, " +
        " Conhecimentos_Internacionais.VL_PESO, " +
        " Conhecimentos_Internacionais.VL_PESO_CUBADO, " +
        " Conhecimentos_Internacionais.VL_NOTA_FISCAL, " +
        " Conhecimentos_Internacionais.VL_SEGURO, " +
        " Conhecimentos_Internacionais.VL_Mercadoria, " +
        " Conhecimentos_Internacionais.NR_VOLUMES, " +
        " Conhecimentos_Internacionais.NR_ORIGINAL, " +
        " Conhecimentos_Internacionais.VL_FRETE, " +
        " Conhecimentos_Internacionais.VL_REEMBOLSO, " +
        " Conhecimentos_Internacionais.VL_DOLAR " +
        " from Conhecimentos_Internacionais where 1=1 ";

        sql = "select * from Conhecimentos_Internacionais where 1=1 ";

        if (String.valueOf(ed.getOID_Conhecimento()) != null &&
            !String.valueOf(ed.getOID_Conhecimento()).equals("")){
          sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
        }


      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();


      try{
        ResultSet res = null;
        res = this.executasql.executarConsulta(sql.toString());


//         // System.out.println(sql);
//        while (res.next()){
//            // System.out.println("tem");
//        }

        Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
        return conhecimento_InternacionalRL.geraRelConhecInternacionalAntigo(res, ed);

      }

      catch (Excecoes e){
        throw e;
      }
      catch(Exception exc){
        Excecoes exce = new Excecoes();
        exce.setExc(exc);
        if(NR_Proximo > NR_Final){
          exce.setMensagem("AIDOF sem numera��o dispon�vel");
        }else{
          exce.setMensagem("Erro no m�todo listar");
        }
        exce.setClasse(this.getClass().getName());
        exce.setMetodo("geraRelatorio(Conhecimento_InternacionalED ed)");
      }
      return b;
    }


  public void geraRelConhecInternacional(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

    String sql = null;
    long valOid = 0;

      sql = "select * from Conhecimentos_Internacionais where 1=1 ";

      if (String.valueOf(ed.getOID_Conhecimento()) != null &&
          !String.valueOf(ed.getOID_Conhecimento()).equals("")){
        sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
      }

    Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

    ResultSet res = this.executasql.executarConsulta(sql.toString());
    try{
      Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
      conhecimento_InternacionalRL.geraRelConhecInternacional(res, ed,response, executasql);
    } finally {
        util.closeResultset(res);
	}

  }

    public void alteraOriginal(String original, String oid) throws Excecoes{

    String sql = null;

    try{
       if (original.equals("1"))
           sql = "update conhecimentos_internacionais set dm_1_original = 'S', dm_situacao='I'  where oid_conhecimento = '" + oid +"'";

       if (original.equals("2"))
           sql = "update conhecimentos_internacionais set dm_2_original = 'S', dm_situacao='I' where oid_conhecimento = '" + oid +"'";

       if (original.equals("3"))
           sql = "update conhecimentos_internacionais set dm_3_original = 'S', dm_situacao='I' where oid_conhecimento = '" + oid +"'";

       if (original.equals("9"))
           sql = "update conhecimentos set dm_impresso = 'S' where oid_conhecimento = '" + oid +"'";

// // System.out.println(sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar original");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void cancela(Conhecimento_InternacionalED ed) throws Excecoes{

    	String sql = null;

    	try{

    		sql = "update Conhecimentos_Internacionais set dm_situacao = 'C' WHERE OID_Conhecimento = ";
    		sql += "('" + ed.getOID_Conhecimento() + "')";
// // System.out.println(sql);
    		int i = executasql.executarUpdate(sql);

    		sql = "update Conhecimentos set dm_situacao = 'C' WHERE OID_Conhecimento = ";
    		sql += "('" + ed.getOID_Conhecimento() + "')";

// // System.out.println(sql);
    		int u = executasql.executarUpdate(sql);
    	}

    	catch(Exception exc){
    		Excecoes excecoes = new Excecoes();
    		excecoes.setClasse(this.getClass().getName());
    		excecoes.setMensagem("Erro ao cancelar");
    		excecoes.setMetodo("cancela");
    		excecoes.setExc(exc);
    		throw excecoes;
    	}
    }

    public byte[] geraPre_Faturamento_inter(ConhecimentoED ed)throws Excecoes{

    	String sql = null;
    	byte[] b = null;

    	sql = "SELECT " +
    		  "CI.dt_emissao, " +
    		  "CI.nr_conhecimento, " +
    		  "CI.oid_conhecimento, " +
    		  "CI.nr_volumes, " +
    		  //"CI.vl_total_frete, " +
    		  "CF.OID_Pessoa_Pagador, "+
    		  "CF.vl_faturar, "+
			  "CI.nr_peso_cubado_calculado, " +
			  "Unidades.CD_Unidade, " +
			  "Cidades.NM_Cidade, " +
			  "Cidade_Pagador.NM_Cidade as NM_Cidade_Pagador, "+
			  "Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente, "+
			  "Pessoa_Unidade.NM_Fantasia          as NM_Fantasia, "+
			  "Pessoa_Pagador.NM_Razao_Social      as NM_Razao_Social_Pagador, "+
			  "Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, "+
			  
			  "(CI.vl_gasto_remetente1 + "+
			  "CI.vl_gasto_remetente2 + "+
			  "CI.vl_gasto_remetente3 + "+
			  "CI.vl_gasto_remetente4 + "+
			  "CI.vl_gasto_destinatario1 + "+
			  "CI.vl_gasto_destinatario2 + "+
			  "CI.vl_gasto_destinatario3 + "+
			  "CI.vl_gasto_destinatario4) as vl_total_frete "+
			  
			  "FROM Unidades, Cidades, Cidades Cidade_Pagador, conhecimentos_internacionais CI, " +
			  "Conhecimentos_Faturamentos CF, Pessoas Pessoa_Remetente, " +
			  "Pessoas Pessoa_Pagador, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario "+
			  
			  "WHERE Unidades.oid_Unidade = CI.oid_Unidade "+
			  "AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
			  "AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "+
			  "AND CF.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "+
			  "AND Pessoa_Pagador.oid_cidade = Cidade_Pagador.oid_Cidade "+
			  "AND CI.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "+
			  "AND CI.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
			  "AND CI.oid_conhecimento = CF.oid_conhecimento " +
			  "AND CF.dm_situacao = 'L' " +
    	      "AND CI.dm_situacao = 'L' ";

    	if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
    		sql += " and CI.NR_Conhecimento = " + ed.getNR_Conhecimento();
    	}
    	
    	if (String.valueOf(ed.getOID_Unidade()) != null && !String.valueOf(ed.getOID_Unidade()).equals("0")){
    		sql += " and CI.OID_Unidade = " + ed.getOID_Unidade();
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
    		sql += " and CI.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
    		sql += " and CI.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null && !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("")){
    		sql += " and CI.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa_Pagador()) != null &&
    			!String.valueOf(ed.getOID_Pessoa_Pagador()).equals("") &&
				!String.valueOf(ed.getOID_Pessoa_Pagador()).equals("null")){
    		sql += " and CF.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "'";
    	}
    	
    	if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
    			!String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
				!String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
    		sql += " and CI.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
    	}
    	
    	if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
    			!String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
				!String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
    		sql += " and CI.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
    	}

    	sql += " order by unidades.cd_unidade, CF.OID_Pessoa_Pagador, Pessoa_Pagador.NM_Razao_Social, CI.nr_conhecimento ";


    	ConhecimentoED edVolta = new ConhecimentoED();

    	try{

    		ResultSet res = null;
    		
//    		// System.out.println("Conhecimento_InternacionalBD.geraPre_Faturamento_inter() sql = " + sql);
    		
    		res = this.executasql.executarConsulta(sql.toString());

    		Conhecimento_InternacionalRL conRL = new Conhecimento_InternacionalRL();
    		b =  conRL.geraPre_Faturamento_inter(res);

    	}

    	catch (Excecoes e){
    	    e.printStackTrace();
    		throw e;
    	}
    	catch(Exception exc){
    		Excecoes exce = new Excecoes();
    		exce.setExc(exc);
    		exce.setMensagem("Erro no m�do listar");
    		exce.setClasse(this.getClass().getName());
    		exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    	}
    	return b;
    }

    
    public byte[] geraRelCRTEmitidoInter(ConhecimentoED ed)throws Excecoes{

    	String sql = null;
    	byte[] b = null;

    	sql = "SELECT " +
    		  "distinct CI.oid_conhecimento, " +
    		  "CI.dt_emissao, " +
    		  "CI.nr_conhecimento, " +
    		  "CI.cd_pais, " +
    		  "CI.nr_permisso, " +
    		  "CI.dm_situacao, " +
    		  "CI.vl_total_frete, " +
			  "U.CD_Unidade, " +
//			  "Cidades.NM_Cidade, " +
//			  "Cidade_Pagador.NM_Cidade as NM_Cidade_Pagador, "+
			  "Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente, "+
			  "Pessoa_Unidade.NM_Fantasia          as NM_Fantasia, "+
//			  "Pessoa_Pagador.NM_Razao_Social      as NM_Razao_Social_Pagador, "+
			  "Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, " +
			  
			  "Pessoa_Importador.NM_Razao_Social   as nm_razao_social_importador, "+
			  "Pessoa_Exportador.NM_Razao_Social   as nm_razao_social_exportador, "+
			  
			  "CI.vl_gasto_remetente1, "+
			  "CI.vl_gasto_remetente2, "+
			  "CI.vl_gasto_remetente3, "+
			  "CI.vl_gasto_remetente4, "+
			  "CI.vl_gasto_destinatario1, "+
			  "CI.vl_gasto_destinatario2, "+
			  "CI.vl_gasto_destinatario3, "+
			  "CI.vl_gasto_destinatario4 "+
			  
			  "FROM Unidades U, "+//Cidades, Cidades Cidade_Pagador, " +
			  "conhecimentos_internacionais CI, " +
			  "Pessoas Pessoa_Remetente, " +//Pessoas Pessoa_Pagador, " +
			  "Pessoas Pessoa_Unidade, " +
			  "Pessoas Pessoa_Destinatario, Pessoas Pessoa_Importador, Pessoas Pessoa_Exportador "+
			  
			  "WHERE U.oid_Unidade = CI.oid_Unidade "+
			  "AND U.oid_pessoa = Pessoa_Unidade.oid_Pessoa "+
//			  "AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "+
//			  "AND Pessoa_Pagador.oid_cidade = Cidade_Pagador.oid_Cidade "+
			  
			  "AND Pessoa_Exportador.oid_pessoa = CI.oid_pessoa_devedor_exportador "+
			  "AND Pessoa_Importador.oid_pessoa = CI.oid_pessoa_devedor_importador "+
			  
			  "AND CI.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "+
			  "AND CI.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
    	
    	if (JavaUtil.doValida(ed.getDM_Situacao()) && !ed.getDM_Situacao().equals("T") && !ed.getDM_Situacao().equals("X")){
    		sql += " AND CI.dm_situacao = '" + ed.getDM_Situacao() + "' ";
    	}
    	if (JavaUtil.doValida(ed.getDM_Situacao()) && ed.getDM_Situacao().equals("X")){
    		sql += " AND (CI.dm_situacao = 'I' or CI.dm_situacao = 'G') ";
    	}

    	if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
    		sql += " and CI.NR_Conhecimento = " + ed.getNR_Conhecimento();
    	}
    	
    	if (String.valueOf(ed.getOID_Unidade()) != null && !String.valueOf(ed.getOID_Unidade()).equals("0")){
    		sql += " and CI.OID_Unidade = " + ed.getOID_Unidade();
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
    		sql += " and CI.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
    		sql += " and CI.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null && !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("")){
    		sql += " and CI.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
    	}
    	
    	if (String.valueOf(ed.getDt_Emissao_Inicial()) != null &&
    			!String.valueOf(ed.getDt_Emissao_Inicial()).equals("") &&
				!String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")){
    		sql += " and CI.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
    	}
    	
    	if (String.valueOf(ed.getOID_Pessoa_Pagador()) != null && !String.valueOf(ed.getOID_Pessoa_Pagador()).equals("") && !String.valueOf(ed.getOID_Pessoa_Pagador()).equals("null")){
    		sql += " and (CI.OID_Pessoa_Devedor_Exportador = '" + ed.getOID_Pessoa_Pagador() + "'" +
    			   " or CI.OID_Pessoa_Devedor_Importador = '" + ed.getOID_Pessoa_Pagador() + "')";
    	}
    	
    	if (String.valueOf(ed.getDt_Emissao_Final()) != null &&
    			!String.valueOf(ed.getDt_Emissao_Final()).equals("") &&
				!String.valueOf(ed.getDt_Emissao_Final()).equals("null")){
    		sql += " and CI.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
    	}

    	sql += " order by U.CD_Unidade, CI.nr_conhecimento";


    	ConhecimentoED edVolta = new ConhecimentoED();

    	try{

    		ResultSet res = null;
    		
    		// System.out.println("Conhecimento_InternacionalBD.geraEmitido_inter() sql = " + sql);
    		
    		res = this.executasql.executarConsulta(sql.toString());

    		Conhecimento_InternacionalRL conRL = new Conhecimento_InternacionalRL(this.executasql);
    		b =  conRL.geraRelCRTEmitidoInter(res);

    	}

    	catch (Excecoes e){
    	    e.printStackTrace();
    		throw e;
    	}
    	catch(Exception exc){
    		Excecoes exce = new Excecoes();
    		exce.setExc(exc);
    		exce.setMensagem("Erro no m�do listar");
    		exce.setClasse(this.getClass().getName());
    		exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    	}
    	return b;
    }
    
    
  /*public String busca_Valor_Cotacao(String DT_Emissao, String OID_Pessoa_Pagador) throws Excecoes{

    String sql = null;
    String DM_D_Menos_Um = null;
    String VL_Cotacao = null;

    try{
      sql = "Select Clientes.DM_D_Menos_Um from Clientes "+
            " where Clientes.OID_Cliente = '"+ OID_Pessoa_Pagador + "'";

      ResultSet rs = this.executasql.executarConsulta(sql);
      while (rs.next()){
        DM_D_Menos_Um = rs.getString("DM_D_Menos_Um");

      }

      if (DM_D_Menos_Um != null && DM_D_Menos_Um.equals("N")){
        sql = "Select VL_Cotacao FROM Indice_Financeiro ";
              if (OID_Pessoa_Pagador.equals("89674782000158") ||
                OID_Pessoa_Pagador.equals("99999999015890") ||
                OID_Pessoa_Pagador.equals("61076055000170") ||
                OID_Pessoa_Pagador.equals("84720630000120") ||
                OID_Pessoa_Pagador.equals("99999815003702"))
                  sql += " where Indice_Financeiro.oid_moeda = 5";
              else if (OID_Pessoa_Pagador.equals("XXXXXX"))
                  sql += " where Indice_Financeiro.oid_moeda = 6";
              else sql += " where Indice_Financeiro.oid_moeda = 2";
              sql += " AND Indice_Financeiro.DT_Cotacao = '" + DT_Emissao + "'";
      }else{

        String  SHORT_DATE = "dd/MM/yyyy";
        DateFormatter dateFormatter = new DateFormatter();
        Data data = new Data();

        Calendar cal = Data.stringToCalendar(DT_Emissao,"dd/MM/yyyy");

        int diasAtuais = cal.get(Calendar.DAY_OF_MONTH);
        String NR_Dias = "-1";
        int NR_Dias_Vencimento = new Integer(NR_Dias).intValue();
        cal.set(Calendar.DAY_OF_MONTH,diasAtuais+NR_Dias_Vencimento);

        String DT_Cotacao_dia_anterior = dateFormatter.calendarToString(cal, SHORT_DATE);

        sql = "Select VL_Cotacao FROM Indice_Financeiro ";
              if (OID_Pessoa_Pagador.equals("89674782000158") ||
                OID_Pessoa_Pagador.equals("99999999015890") ||
                OID_Pessoa_Pagador.equals("61076055000170") ||
                OID_Pessoa_Pagador.equals("84720630000120") ||
                OID_Pessoa_Pagador.equals("99999815003702"))
                sql += " where Indice_Financeiro.oid_moeda = 5";
              else if (OID_Pessoa_Pagador.equals("XXXXXX"))
                sql += " where Indice_Financeiro.oid_moeda = 6";
              else sql += " where Indice_Financeiro.oid_moeda = 2";
              sql += " AND Indice_Financeiro.DT_Cotacao = '" + DT_Cotacao_dia_anterior + "'";

      }
      VL_Cotacao = "0";

      rs = this.executasql.executarConsulta(sql);
      while (rs.next()){
        VL_Cotacao = rs.getString("VL_Cotacao");
      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao gerar movimento");
      excecoes.setMetodo("gera_movimento");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return VL_Cotacao;
  }*/

  public ArrayList lista_fatura(Conhecimento_InternacionalED ed)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();

      try{

        sql = " select Conhecimentos_Internacionais.NR_Conhecimento, Conhecimentos_Internacionais.OID_Conhecimento, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
        	  " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Conhecimentos_Internacionais_faturas.NR_Fatura, Conhecimentos_Internacionais_faturas.oid_Conhecimento_Internacional_fatura " +
        	  " from Conhecimentos_Internacionais, Conhecimentos_Internacionais_faturas, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario ";
        sql += " where Conhecimentos_Internacionais.oid_conhecimento = Conhecimentos_Internacionais_faturas.oid_conhecimento" +
        	   " and Conhecimentos_Internacionais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Conhecimentos_Internacionais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        if (String.valueOf(ed.getNR_Fatura()) != null && !String.valueOf(ed.getNR_Fatura()).equals("0") && !String.valueOf(ed.getNR_Fatura()).equals("null")){
          sql += " and Conhecimentos_Internacionais_faturas.NR_Fatura = " + ed.getNR_Fatura();
        }
        if (ed.getOID_Conhecimento() != null && !ed.getOID_Conhecimento().equals("null")){
          sql += " and Conhecimentos_Internacionais_faturas.OID_conhecimento = '" + ed.getOID_Conhecimento() +"'";
        }

        sql += " Order by Conhecimentos_Internacionais_faturas.NR_fatura ";

//    	// System.out.println("Conhec_InterBD.lista_fatura() sql = " + sql);

        ResultSet res = null;
        res = this.executasql.executarConsulta(sql);

        //popula

        FormataDataBean DataFormatada = new FormataDataBean();

        while (res.next()){
          Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

          edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
          edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
          edVolta.setOID_Conhecimento_Internacional_Fatura(res.getString("OID_Conhecimento_Internacional_Fatura"));
          edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
          edVolta.setNR_Fatura(res.getString("NR_Fatura"));
          list.add(edVolta);
        }
      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return list;
    }

 public Conhecimento_InternacionalED getByRecord_fatura(Conhecimento_InternacionalED ed)throws Excecoes{

      String sql = null;

      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

      try{

        sql = " select * " +
        	  " from Conhecimentos_Internacionais_Faturas, conhecimentos_internacionais " +
        	  " where Conhecimentos_Internacionais_Faturas.oid_conhecimento = Conhecimentos_Internacionais.oid_conhecimento ";

        if (String.valueOf(ed.getOID_Conhecimento_Internacional_Fatura()) != null &&
            !String.valueOf(ed.getOID_Conhecimento_Internacional_Fatura()).equals("") &&
            !String.valueOf(ed.getOID_Conhecimento_Internacional_Fatura()).equals("null")){
          sql += " and OID_Conhecimento_Internacional_Fatura = '" + ed.getOID_Conhecimento_Internacional_Fatura() + "'";
        }
        if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
          sql += " and NR_Conhecimento = " + ed.getNR_Conhecimento();
          //sql += " and OID_Unidade = " + ed.getOID_Unidade_Origem();
        }

       // System.err.println("get by rec cto int fat " + sql);

        ResultSet res = null;
        res = this.executasql.executarConsulta(sql);

        FormataDataBean DataFormatada = new FormataDataBean();

        while (res.next()){

          edVolta.setDt_stamp(res.getString("DT_Stamp"));
          DataFormatada.setDT_FormataData(edVolta.getDt_stamp());
          edVolta.setDt_stamp(DataFormatada.getDT_FormataData());
          edVolta.setHr_stamp(res.getString("HR_Stamp"));

          edVolta.setNR_Conhecimento(res.getLong("NR_CONHECIMENTO"));
          edVolta.setOID_Conhecimento(res.getString("OID_CONHECIMENTO"));
          edVolta.setOID_Conhecimento_Internacional_Fatura(res.getString("OID_CONHECIMENTO_INTERNACIONAL_FATURA"));

          edVolta.setOID_Pessoa(res.getString("OID_PESSOA"));
          edVolta.setOID_Pessoa_Destinatario(res.getString("OID_PESSOA_DESTINATARIO"));
          edVolta.setOID_Unidade_Origem(res.getInt("OID_UNIDADE"));

          edVolta.setNR_Fatura(res.getString("NR_Fatura"));

        }


      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar");
        excecoes.setMetodo("selecionar");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return edVolta;
    }

  public void deleta_fatura(Conhecimento_InternacionalED ed) throws Excecoes{

      String sql = null;

      try{

        sql = "delete from Conhecimentos_Internacionais_faturas WHERE OID_Conhecimento_internacional_fatura = ";
        sql += "('" + ed.getOID_Conhecimento_Internacional_Fatura() + "')";
        // System.out.println("VAI 4 > "+sql);
        int i = executasql.executarUpdate(sql);
      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void altera_fatura(Conhecimento_InternacionalED ed) throws Excecoes{

      String sql = null;

      try{

        sql = " update Conhecimentos_Internacionais_faturas set nr_fatura= '" + ed.getNR_Fatura() + "' ";
        sql += " where OID_Conhecimento_internacional_fatura = '" + ed.getOID_Conhecimento_Internacional_Fatura() + "'" ;

        int i = executasql.executarUpdate(sql);
      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public Conhecimento_InternacionalED inclui_fatura(Conhecimento_InternacionalED ed) throws Excecoes{

      String sql = null;
      long valOid = 0;
      String chave = null;
      String tx_doc = "";

      Conhecimento_InternacionalED conED = new Conhecimento_InternacionalED();

      try{


        chave = String.valueOf(ed.getOID_Conhecimento()) + String.valueOf(ed.getNR_Fatura());
        ed.setOID_Conhecimento_Internacional_Fatura(chave);

        sql = " insert into Conhecimentos_Internacionais_faturas (OID_Conhecimento_internacional_fatura, OID_Conhecimento, " +
        	  " nr_fatura, dt_stamp, hr_stamp, dm_stamp, usuario_stamp) values ";
        sql +=  "('"+ed.getOID_Conhecimento_Internacional_Fatura()+"','"+ed.getOID_Conhecimento()+
                "','"+ed.getNR_Fatura()+"','"+ed.getDt_stamp()+"','"+ed.getHr_stamp()+"','"+ed.getDm_Stamp()+"','"+ed.getUsuario_Stamp()+"')";

// System.err.println(sql);

        int i = executasql.executarUpdate(sql);
        
//        sql = " select tx_documentos from conhecimentos_internacionais where oid_conhecimento = '"+ed.getOID_Conhecimento()+"'";
//        ResultSet res = null;
//        res = executasql.executarConsulta(sql);
//        while(res.next()){
//            tx_doc = res.getString("tx_documentos");
//        }
//        if(!tx_doc.equals("")){
//            tx_doc = tx_doc + " / " + String.valueOf(ed.getNR_Fatura());
//        }else {
//            tx_doc = "FACTURA COMERCIAL NR. " + String.valueOf(ed.getNR_Fatura());
//        }
//        if(tx_doc.trim().length() > 150) tx_doc = tx_doc.substring(0,150);
//        
//        sql = " update conhecimentos_internacionais set tx_documentos = '" + tx_doc + "'" +
//        	  " where oid_conhecimento = '"+ed.getOID_Conhecimento()+"'";
//        i = executasql.executarUpdate(sql);

        conED.setOID_Conhecimento(ed.getOID_Conhecimento());
        conED.setNR_Conhecimento(ed.getNR_Conhecimento());
      }

      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir Conhecimento_Internacional_Fatura");
        excecoes.setMetodo("inclui_fatura()");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return conED;
    }

  public void altera_Totais(Conhecimento_InternacionalED ed) throws Excecoes {
      String sql =
          " update Conhecimentos_Internacionais " +
          " set VL_Total_Frete = 0 " +
          "    ,VL_Total_Custo = 0 " + " where oid_Conhecimento = '"
      + ed.getOID_Conhecimento() + "'";
      
      try {
          int i = executasql.executarUpdate(sql);
      } catch (SQLException e) {
          throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "altera_Totais(ConhecimentoED ed)");
      }
  }
  
  public ArrayList lista_MIC(Conhecimento_InternacionalED ed)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();

      try{

        sql = " select conhecimentos_internacionais.*, " +
        		" Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente " +
        		" from Conhecimentos_Internacionais, Unidades, Pessoas Pessoa_Remetente ";
        sql += " where Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade ";
        sql += " AND Conhecimentos_Internacionais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Conhecimentos_Internacionais.dm_situacao != 'C' ";
        //sql += " and Conhecimentos_Internacionais.oid_conhecimento not in (select oid_conhecimento from viagens_internacionais)";
        
        if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
          sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_Origem();
        }
        
        if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("") && !String.valueOf(ed.getOID_Pessoa()).equals("null")){
          sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
        }
        
        if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("") && !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
            sql += " and Conhecimentos_Internacionais.nr_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
          }

        sql += " Order by Conhecimentos_Internacionais.oid_unidade, Conhecimentos_Internacionais.NR_Conhecimento ";

// System.out.println("Conhecimento_InternacionalBD.lista() sql = " + sql);

        ResultSet res = null;
        res = this.executasql.executarConsulta(sql);

        //popula

        FormataDataBean DataFormatada = new FormataDataBean();

        while (res.next()){
            Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
            
            edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
            edVolta.setCD_Unidade(res.getString("CD_Unidade"));
            edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
         
            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
  								res.getString("NR_Permisso") + ".";
  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
  			}
  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
  		    
  		    edVolta.setDM_Isento_Seguro(res.getString("DM_Isento_Seguro"));
  		    edVolta.setDM_Responsavel_Cobranca(res.getString("DM_Responsavel_Cobranca"));

  		    edVolta.setDT_Emissao(res.getString("DT_Emissao"));
  		    DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
  		    edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

  		    edVolta.setDT_Conversao(res.getString("DT_Conversao"));
  		    DataFormatada.setDT_FormataData(edVolta.getDT_Conversao());
  		    edVolta.setDT_Conversao(DataFormatada.getDT_FormataData());

  		    edVolta.setNM_Serie(res.getString("NM_SERIE"));
  		    edVolta.setNR_Conhecimento(res.getLong("NR_CONHECIMENTO"));
  		    edVolta.setOID_Cidade(res.getLong("OID_CIDADE"));
  		    edVolta.setOID_Cidade_Destino(res.getLong("OID_CIDADE_DESTINO"));
  		    edVolta.setOID_Cidade_Embarque(res.getLong("OID_CIDADE_EMBARQUE"));
  		    edVolta.setOID_Conhecimento(res.getString("OID_CONHECIMENTO"));
  		    edVolta.setOID_Modal(res.getLong("OID_MODAL"));
          
  		    edVolta.setOID_Produto(res.getLong("OID_PRODUTO"));
          
  		    edVolta.setOID_Unidade_Origem(res.getInt("OID_UNIDADE"));
	    	edVolta.setOID_Unidade_Destino(res.getInt("oid_unidade_destino"));
	    	edVolta.setOID_Unidade_Fronteira(res.getInt("oid_unidade_fronteira"));
          
	    	edVolta.setTX_Observacao1(res.getString("TX_Observacao"));
	    	edVolta.setTX_Observacao2(res.getString("TX_Observacao2"));
	    	edVolta.setTX_Observacao3(res.getString("TX_Observacao3"));
	    	edVolta.setTX_Observacao4(res.getString("TX_Observacao4"));
	    	edVolta.setTX_Observacao5(res.getString("TX_Observacao5"));
	    	edVolta.setTX_Observacao6(res.getString("TX_Observacao6"));
	    	edVolta.setTX_Observacao7(res.getString("TX_Observacao7"));
	    	edVolta.setTX_Observacao8(res.getString("TX_Observacao8"));
	    	edVolta.setTX_Observacao9(res.getString("TX_Observacao9"));
	    	edVolta.setTX_Observacao10(res.getString("TX_Observacao10"));
	    	edVolta.setTX_Observacao11(res.getString("TX_Observacao11"));
	    	edVolta.setTX_Observacao12(res.getString("TX_Observacao12"));
	    	edVolta.setTX_Observacao13(res.getString("TX_Observacao13"));
	    	edVolta.setTX_Observacao14(res.getString("TX_Observacao14"));
	    	edVolta.setTX_Observacao15(res.getString("TX_Observacao15"));
	    	edVolta.setTX_Observacao16(res.getString("TX_Observacao16"));
	    	edVolta.setTX_Observacao17(res.getString("TX_Observacao17"));
	    	edVolta.setTX_Observacao18(res.getString("TX_Observacao18"));
          
	    	edVolta.setTX_Alfandega1(res.getString("TX_Alfandega"));
	    	edVolta.setTX_Alfandega2(res.getString("TX_Alfandega2"));
	    	edVolta.setTX_Alfandega3(res.getString("TX_Alfandega3"));
	    	edVolta.setTX_Alfandega4(res.getString("TX_Alfandega4"));
	    	edVolta.setTX_Alfandega5(res.getString("TX_Alfandega5"));
	    	edVolta.setTX_Alfandega6(res.getString("TX_Alfandega6"));
          
	    	edVolta.setTX_Documentos1(res.getString("TX_Documentos"));
	    	edVolta.setTX_Documentos2(res.getString("TX_Documentos2"));
	    	edVolta.setTX_Documentos3(res.getString("TX_Documentos3"));
	    	edVolta.setTX_Documentos4(res.getString("TX_Documentos4"));
	    	edVolta.setTX_Documentos5(res.getString("TX_Documentos5"));
	    	edVolta.setTX_Documentos6(res.getString("TX_Documentos6"));
          
	    	edVolta.setTX_Declaracao1(res.getString("TX_Declaracao"));
	    	edVolta.setTX_Declaracao2(res.getString("TX_Declaracao2"));
	    	edVolta.setTX_Declaracao3(res.getString("TX_Declaracao3"));
	    	edVolta.setTX_Declaracao4(res.getString("TX_Declaracao4"));
	    	edVolta.setTX_Declaracao5(res.getString("TX_Declaracao5"));
	    	edVolta.setTX_Declaracao6(res.getString("TX_Declaracao6"));
	    	edVolta.setTX_Declaracao7(res.getString("TX_Declaracao7"));
	    	edVolta.setTX_Declaracao8(res.getString("TX_Declaracao8"));
          
	    	edVolta.setTX_Remetente(res.getString("TX_Remetente"));
	    	edVolta.setNM_Gasto_Remetente1(res.getString("NM_Gasto_Remetente1"));
	    	edVolta.setNM_Gasto_Remetente2(res.getString("NM_Gasto_Remetente2"));
	    	edVolta.setNM_Gasto_Remetente3(res.getString("NM_Gasto_Remetente3"));
	    	edVolta.setNM_Gasto_Remetente4(res.getString("NM_Gasto_Remetente4"));
	    	edVolta.setVL_Gasto_Remetente1(res.getDouble("VL_Gasto_Remetente1"));
	    	edVolta.setVL_Gasto_Remetente2(res.getDouble("VL_Gasto_Remetente2"));
	    	edVolta.setVL_Gasto_Remetente3(res.getDouble("VL_Gasto_Remetente3"));
	    	edVolta.setVL_Gasto_Remetente4(res.getDouble("VL_Gasto_Remetente4"));
	    	edVolta.setVL_Gasto_Destinatario1(res.getDouble("VL_Gasto_Destinatario1"));
	    	edVolta.setVL_Gasto_Destinatario2(res.getDouble("VL_Gasto_Destinatario2"));
	    	edVolta.setVL_Gasto_Destinatario3(res.getDouble("VL_Gasto_Destinatario3"));
	    	edVolta.setVL_Gasto_Destinatario4(res.getDouble("VL_Gasto_Destinatario4"));
          
	    	edVolta.setVL_Total_Frete(res.getDouble("VL_Gasto_Remetente1") + 
                                    res.getDouble("VL_Gasto_Remetente2") + 
                                    res.getDouble("VL_Gasto_Remetente3") + 
                                    res.getDouble("VL_Gasto_Remetente4") + 
                                    res.getDouble("VL_Gasto_Destinatario1") + 
                                    res.getDouble("VL_Gasto_Destinatario2") + 
                                    res.getDouble("VL_Gasto_Destinatario3") + 
                                    res.getDouble("VL_Gasto_Destinatario4"));
          
	    	edVolta.setVL_Frete(res.getDouble("VL_Frete"));
	    	edVolta.setVL_Seguro(res.getDouble("VL_Seguro"));
	    	edVolta.setVL_Mercadoria(res.getDouble("VL_Mercadoria"));
	    	edVolta.setVL_Reembolso(res.getDouble("VL_Reembolso"));
	    	edVolta.setVL_Dolar(res.getDouble("vl_dolar"));
          
	    	edVolta.setVL_Peso(res.getDouble("VL_Peso"));
	    	edVolta.setVL_Peso_Cubado(res.getDouble("VL_Peso_Cubado"));
	    	ed.setVL_Peso_Cubado_Calculado(res.getDouble("VL_Peso_Cubado_Calculado"));
	    	edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
	    	edVolta.setNR_Volumes(res.getDouble("NR_Volumes"));
	    	edVolta.setNR_Original(res.getLong("NR_Original"));

	    	edVolta.setOID_Pessoa(res.getString("OID_PESSOA"));
	    	edVolta.setNM_Remetente(res.getString("NM_Remetente"));
	    	edVolta.setNM_Endereco_Remetente(res.getString("NM_Endereco_Remetente"));
	    	edVolta.setNM_Cidade_Estado_Pais_Remetente(res.getString("NM_Cidade_Estado_Pais_Remetente"));
	    	edVolta.setNR_CNPJ_Remetente_Complementar(res.getString("NR_CNPJ_Remetente_Complementar"));
          
	    	edVolta.setOID_Pessoa_Destinatario(res.getString("OID_PESSOA_DESTINATARIO"));
	    	edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));
	    	edVolta.setNM_Endereco_Destinatario(res.getString("NM_Endereco_Destinatario"));
	    	edVolta.setNR_CNPJ_Destinatario_Complementar(res.getString("NR_CNPJ_Destinatario_Complementar"));
	    	edVolta.setNM_Cidade_Estado_Pais_Destinatario(res.getString("NM_Cidade_Estado_Pais_Destinatario"));
          
	    	edVolta.setOID_Pessoa_Consignatario(res.getString("OID_PESSOA_CONSIGNATARIO"));
	    	edVolta.setNM_Consignatario(res.getString("NM_Consignatario"));
	    	edVolta.setNM_Endereco_Consignatario(res.getString("NM_Endereco_Consignatario"));
	    	edVolta.setNM_Cidade_Estado_Pais_Consignatario(res.getString("NM_Cidade_Estado_Pais_Consignatario"));
	    	edVolta.setNR_CPF_CNPJ_Consignatario_Complementar(res.getString("NR_CNPJ_Consignatario_Complementar"));
          
	    	edVolta.setOID_Pessoa_Notificar(res.getString("OID_PESSOA_NOTIFICAR"));
	    	edVolta.setNM_Notificar(res.getString("NM_Notificar"));
	    	edVolta.setNM_Endereco_Notificar(res.getString("NM_Endereco_Notificar"));
	    	edVolta.setNM_Cidade_Estado_Pais_Notificar(res.getString("NM_Cidade_Estado_Pais_Notificar"));
	    	edVolta.setNR_CPF_CNPJ_Notificar_Complementar(res.getString("NR_CNPJ_Notificar_Complementar"));
          
	    	edVolta.setOID_Pessoa_Cotacao(res.getString("oid_pessoa_cotacao"));
	    	edVolta.setNM_Cotacao(res.getString("nm_cotacao"));

	    	edVolta.setOID_Pessoa_Devedor_Exportador(res.getString("oid_pessoa_devedor_exportador"));
	    	edVolta.setNM_Devedor_Exportador(res.getString("nm_devedor_exportador"));
          
	    	edVolta.setOID_Pessoa_Devedor_Importador(res.getString("oid_pessoa_devedor_importador"));
	    	edVolta.setNM_Devedor_Importador(res.getString("nm_devedor_importador"));
          
	    	edVolta.setCD_Pais(res.getString("CD_Pais"));
	    	edVolta.setNR_Permisso(res.getString("NR_Permisso"));
	    	edVolta.setDM_Exportacao_Importacao(res.getString("DM_Exportacao_Importacao"));
	    	edVolta.setNR_Fatura(res.getString("NR_Fatura"));
          
	    	edVolta.setNM_Cidade_Estado_Pais_Emissao(res.getString("nm_localidade_emissao"));
	    	edVolta.setNM_Cidade_Estado_Pais_Embarque(res.getString("nm_localidade_embarque"));
	    	edVolta.setNM_Cidade_Estado_Pais_Entrega(res.getString("nm_localidade_entrega"));
          
	    	edVolta.setDM_Situacao(res.getString("dm_situacao"));
	    	edVolta.setOid_Tabela_Frete(res.getLong("oid_Tabela_Frete"));
          
          //TEO 18/04/05
	    	edVolta.setVL_Frete_Editado(res.getString("vl_frete_editado"));
	    	edVolta.setVL_Mercadoria_Editado(res.getString("vl_mercadoria_editado"));
	    	edVolta.setVL_Peso_Editado(res.getString("vl_peso_editado"));
	    	edVolta.setVL_Peso_Cubado_Editado(res.getString("vl_peso_cubado_editado"));
	    	edVolta.setNR_Volumes_Editado(res.getString("nr_volumes_editado"));
	    	edVolta.setNR_Volumes_Observacao(res.getLong("nr_volumes_observacao"));
          
	    	edVolta.setNM_Icoterm(res.getString("nm_icoterm"));
          
	    	edVolta.setC15_VL_Frete_Peso(res.getDouble("c15_vl_frete_peso"));
	    	edVolta.setC15_VL_Ad_Valorem(res.getDouble("c15_vl_Ad_Valorem"));
	    	edVolta.setC15_VL_Taxas(res.getDouble("c15_vl_Taxas"));
	    	edVolta.setC15_VL_Outros(res.getDouble("c15_vl_Outros"));
	    	edVolta.setC15_VL_Total(res.getDouble("c15_vl_Total"));
          
	    	edVolta.setDM_Veiculo_Novo(res.getString("dm_veiculo_novo"));
	    	edVolta.setVL_RCTRC(res.getDouble("vl_rctrc"));
	    	edVolta.setVL_Desconto_RCTRC(res.getDouble("vl_desconto_rctrc"));
	    	edVolta.setVL_RCTR_VI(res.getDouble("vl_rctr_vi"));
	    	edVolta.setVL_RCTR_DC(res.getDouble("vl_rctr_dc"));
          
	    	edVolta.setOID_Vendedor(res.getString("oid_vendedor"));
          
	    	edVolta.setOID_Coleta(res.getLong("OID_Coleta"));
  		    
  		    list.add(edVolta);
        }
      }

      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return list;
    }
  
  public void geraRelCRTEmitidoJasper(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      ArrayList lista = new ArrayList();
      long valOid = 0;
      ResultSet res = null;
      
      try{
          

	      sql = " select ci.*, po.cd_pais as pOr, pd.cd_pais as pDest " +
	         	" from Conhecimentos_Internacionais ci, paises po, cidades co, regioes_estados reo, estados eo, regioes_paises rpo," +
	        	" paises pd, cidades cd, regioes_estados red, estados ed, regioes_paises rpd " +
	        	" where ci.oid_cidade = co.oid_cidade " +
	        	" and co.oid_regiao_estado = reo.oid_regiao_estado " +
	        	" and reo.oid_estado = eo.oid_estado " +
	        	" and eo.oid_regiao_pais = rpo.oid_regiao_pais " +
	        	" and rpo.oid_pais = po.oid_pais " +
	        	" and ci.oid_cidade_destino = cd.oid_cidade" +
	        	" and cd.oid_regiao_estado = red.oid_regiao_estado" +
	        	" and red.oid_estado = ed.oid_estado" +
	        	" and ed.oid_regiao_pais = rpd.oid_regiao_pais" +
	        	" and rpd.oid_pais = pd.oid_pais ";
	
	      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
	            sql += " and ci.OID_Unidade = " + ed.getOID_Unidade_Origem();
	      }
	      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
	            sql += " and ci.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
	      }
	      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
	            sql += " and ci.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
	      }
	      if (JavaUtil.doValida(ed.getOID_Pessoa_Pagador())){
	            sql += " and conhecimentos_faturamentos.oid_conhecimento = ci.oid_conhecimento ";
	            sql += " and Conhecimentos_faturamentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "' ";
	            //sql += " and (Conhecimentos_Internacionais.OID_Pessoa_Devedor_Importador = '" + ed.getOID_Pessoa_Pagador() + "' or Conhecimentos_Internacionais.OID_Pessoa_Devedor_Exportador = '" + ed.getOID_Pessoa_Pagador() + "')";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
	          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
			  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
	    		sql += " and CI.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	      }
	      if (JavaUtil.doValida(ed.getDM_Situacao()) && !ed.getDM_Situacao().equals("T") && !ed.getDM_Situacao().equals("X")){
	    		sql += " AND CI.dm_situacao = '" + ed.getDM_Situacao() + "' ";
	      }
	      if (JavaUtil.doValida(ed.getDM_Situacao()) && ed.getDM_Situacao().equals("X")){
	          sql += " AND (CI.dm_situacao = 'I' or CI.dm_situacao = 'G') ";
	      }
	      
	      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
	    			!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
	    		sql += " and CI.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Origem()))){
	    	    sql += " and po.oid_pais = " + ed.getOid_Origem() + "";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Destino()))){
	    	    sql += " and pd.oid_pais = " + ed.getOid_Destino() + "";
	      }
	    	
	      sql += "order by po.cd_pais, pd.cd_pais, ci.oid_unidade, ci.dt_emissao ";
// System.out.println(sql.toString());
	      res = this.executasql.executarConsulta(sql.toString());
	      
	      FormataDataBean DataFormatada = new FormataDataBean();
	      
	      while (res.next()){
	            Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

	            double exp = res.getDouble("VL_Gasto_Remetente1") + 
                			 res.getDouble("VL_Gasto_Remetente2") + 
                			 res.getDouble("VL_Gasto_Remetente3") + 
                			 res.getDouble("VL_Gasto_Remetente4");
	            double imp = res.getDouble("VL_Gasto_Destinatario1") + 
                			 res.getDouble("VL_Gasto_Destinatario2") + 
                			 res.getDouble("VL_Gasto_Destinatario3") + 
                			 res.getDouble("VL_Gasto_Destinatario4");
	            edVolta.setOID_Unidade_Negocio(res.getString("oid_unidade_destino"));
	            if (exp > 0){
	                edVolta.setOID_Unidade_Negocio(res.getString("oid_unidade"));
	            }
	            UnidadeBean un = UnidadeBean.getByOID_Unidade(Integer.parseInt(edVolta.getOID_Unidade_Negocio()));
	            edVolta.setNM_Unidade_Negocio(un.getCD_Unidade());
	            
	            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
	            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
	            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
	            edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
	            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	            
	            PessoaBean pE = PessoaBean.getByOID(res.getString("oid_pessoa"));
	            edVolta.setNM_Pessoa_Remetente(pE.getNM_Razao_Social());
	            PessoaBean pI = PessoaBean.getByOID(res.getString("oid_pessoa_destinatario"));
	            edVolta.setNM_Pessoa_Destinatario(pI.getNM_Razao_Social());
	            
//	            PessoaBean pP = PessoaBean.getByOID(res.getString("oid_pessoa_destinatario"));
//	            edVolta.setNM_Pessoa_Consignatario(pP.getNM_Razao_Social());
//	            edVolta.setCD_Unidade(res.getString("CD_Unidade"));
	            
	            edVolta.setVL_Total_Frete(res.getDouble("VL_Gasto_Remetente1") + 
	                    res.getDouble("VL_Gasto_Remetente2") + 
	                    res.getDouble("VL_Gasto_Remetente3") + 
	                    res.getDouble("VL_Gasto_Remetente4") + 
	                    res.getDouble("VL_Gasto_Destinatario1") + 
	                    res.getDouble("VL_Gasto_Destinatario2") + 
	                    res.getDouble("VL_Gasto_Destinatario3") + 
	                    res.getDouble("VL_Gasto_Destinatario4"));
	            
	            edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
	            edVolta.setVL_Frete_Merc((edVolta.getVL_Total_Frete()/edVolta.getVL_Nota_Fiscal())*100);
	            
	            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  								res.getString("NR_Permisso") + ".";
	  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  			}
	  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
	  		    // System.out.println("add...");
	  		    
                edVolta.setNM_Pais_Embarque(res.getString("pOr").trim() + " - " + res.getString("pDest").trim());
	            	  		    
	  		    lista.add(edVolta);
	        }
	      util.closeResultset(res);
          Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
          conhecimento_InternacionalRL.geraRelCRTEmitidoJasper(ed, lista, response);
        
        
      } catch(Exception exc) {
          util.closeResultset(res);
        exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
  	  }
    }
  
  public void geraRelCRTporCidade(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      ArrayList lista = new ArrayList();
      ResultSet res = null;
      
      try{
          

	      sql = " select ci.*, co.nm_cidade as nm_Cid, co.cd_cidade as cd_Cid, co.oid_cidade as oid_Cid " +
	         	" from Conhecimentos_Internacionais ci, cidades co " +
	        	" where ci.oid_cidade_embarque = co.oid_cidade " +
	        	" and ci.oid_unidade_fronteira not in (10, 20) ";
	
	      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
	            sql += " and ci.OID_Unidade = " + ed.getOID_Unidade_Origem();
	      }
	      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
	            sql += " and ci.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
	      }
	      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
	            sql += " and ci.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
	      }
	      if (JavaUtil.doValida(ed.getOID_Pessoa_Pagador())){
	            sql += " and conhecimentos_faturamentos.oid_conhecimento = ci.oid_conhecimento ";
	            sql += " and Conhecimentos_faturamentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "' ";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
	          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
			  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
	    		sql += " and CI.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	      }
	      
	      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
	    			!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
	    		sql += " and CI.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
	      }
	      
	      sql += " AND CI.dm_situacao != 'C' ";
	    	
	      sql += "order by ci.oid_unidade, co.nm_cidade, ci.dt_emissao ";
// System.out.println(sql.toString());
	      res = this.executasql.executarConsulta(sql.toString());
	      
	      FormataDataBean DataFormatada = new FormataDataBean();
	      
	      while (res.next()){
	            Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

	            double exp = res.getDouble("VL_Gasto_Remetente1") + 
                			 res.getDouble("VL_Gasto_Remetente2") + 
                			 res.getDouble("VL_Gasto_Remetente3") + 
                			 res.getDouble("VL_Gasto_Remetente4");
	            double imp = res.getDouble("VL_Gasto_Destinatario1") + 
                			 res.getDouble("VL_Gasto_Destinatario2") + 
                			 res.getDouble("VL_Gasto_Destinatario3") + 
                			 res.getDouble("VL_Gasto_Destinatario4");
	            edVolta.setOID_Unidade_Negocio(res.getString("oid_unidade_destino"));
	            if (exp > 0){
	                edVolta.setOID_Unidade_Negocio(res.getString("oid_unidade"));
	            }
	            UnidadeBean un = UnidadeBean.getByOID_Unidade(Integer.parseInt(edVolta.getOID_Unidade_Negocio()));
	            edVolta.setNM_Unidade_Negocio(un.getCD_Unidade());
	            
	            edVolta.setNM_Cidade_Embarque(res.getString("nm_Cid"));
	            
	            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
	            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
	            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
	            edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
	            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	            
	            PessoaBean pE = PessoaBean.getByOID(res.getString("oid_pessoa"));
	            edVolta.setNM_Pessoa_Remetente(pE.getNM_Razao_Social());
	            PessoaBean pI = PessoaBean.getByOID(res.getString("oid_pessoa_destinatario"));
	            edVolta.setNM_Pessoa_Destinatario(pI.getNM_Razao_Social());
	            
	            edVolta.setVL_Dolar(res.getDouble("VL_Gasto_Remetente1") + 
	                    res.getDouble("VL_Gasto_Remetente2") + 
	                    res.getDouble("VL_Gasto_Remetente3") + 
	                    res.getDouble("VL_Gasto_Remetente4") + 
	                    res.getDouble("VL_Gasto_Destinatario1") + 
	                    res.getDouble("VL_Gasto_Destinatario2") + 
	                    res.getDouble("VL_Gasto_Destinatario3") + 
	                    res.getDouble("VL_Gasto_Destinatario4"));
	            
	            edVolta.setVL_Total_Frete(convertToReal(res.getString("DT_Emissao"), edVolta.getVL_Dolar() , Parametro_FixoED.getInstancia().getOID_Moeda_Dollar()));
	            
	            edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
	            edVolta.setVL_Frete_Merc((edVolta.getVL_Total_Frete()/edVolta.getVL_Nota_Fiscal())*100);
	            
	            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  								res.getString("NR_Permisso") + ".";
	  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  			}
	  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
	  		    //// System.out.println("add...");
	  		    //CidadeBean cid = CidadeBean.getByOID(res.getInt("oid_Cid"));
	  		    //edVolta.setNM_Pais_Embarque(res.getString("nm_Cid").trim()+ "/" +  cid.getCD_Pais().trim());
                edVolta.setNM_Pais_Embarque(res.getString("nm_Cid").trim());
	            	  		    
	  		    lista.add(edVolta);
	        }
	      util.closeResultset(res);
          Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
          conhecimento_InternacionalRL.geraRelCRTporCidade(ed, lista, response);
        
        
      } catch(Exception exc) {
          util.closeResultset(res);
        exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
  	  }
    }
  
  public void geraRelCRT_NaoFaturado(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql, oidCRT = null;
      ArrayList lista = new ArrayList();
      long valOid = 0;
      ResultSet res = null;
      ResultSet res2 = null;
      
      try{
          

	      sql = " select ci.*, po.cd_pais as pOr, pd.cd_pais as pDest, cf.vl_faturar, cf.oid_pessoa_pagador " +
	         	" from Conhecimentos_Internacionais ci, paises po, cidades co, regioes_estados reo, estados eo, regioes_paises rpo," +
	        	" paises pd, cidades cd, regioes_estados red, estados ed, regioes_paises rpd, conhecimentos_faturamentos cf " +
	        	" where ci.oid_cidade = co.oid_cidade " +
	        	" and co.oid_regiao_estado = reo.oid_regiao_estado " +
	        	" and reo.oid_estado = eo.oid_estado " +
	        	" and eo.oid_regiao_pais = rpo.oid_regiao_pais " +
	        	" and rpo.oid_pais = po.oid_pais " +
	        	" and ci.oid_cidade_destino = cd.oid_cidade" +
	        	" and cd.oid_regiao_estado = red.oid_regiao_estado" +
	        	" and red.oid_estado = ed.oid_estado" +
	        	" and ed.oid_regiao_pais = rpd.oid_regiao_pais" +
	        	" and rpd.oid_pais = pd.oid_pais "+
	        	" and cf.oid_conhecimento = ci.oid_conhecimento "+
	        	" and cf.oid_duplicata = 0 ";
	
	      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
	            sql += " and ci.OID_Unidade = " + ed.getOID_Unidade_Origem();
	      }
	      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
	            sql += " and ci.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
	      }
	      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
	            sql += " and ci.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
	      }
	      if (JavaUtil.doValida(ed.getOID_Pessoa_Pagador())){
	            sql += " and cf.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "' ";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
	          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
			  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
	    		sql += " and CI.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	      }
	      if (JavaUtil.doValida(ed.getDM_Situacao()) && !ed.getDM_Situacao().equals("T")){
	    		sql += " AND CI.dm_situacao = '" + ed.getDM_Situacao() + "' ";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
	    			!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
	    		sql += " and CI.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Origem()))){
	    	    sql += " and po.oid_pais = " + ed.getOid_Origem() + "";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Destino()))){
	    	    sql += " and pd.oid_pais = " + ed.getOid_Destino() + "";
	      }
	    	
	      if(ed.getDM_SO().equals("NP")){
	          sql += "order by po.cd_pais, pd.cd_pais, ci.oid_unidade, ci.dt_emissao ";
	      } else sql += "order by cf.oid_pessoa_pagador, ci.dt_emissao ";
// System.out.println(sql.toString());
	      res = this.executasql.executarConsulta(sql.toString());
	      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();
	      oidCRT = "";
	      FormataDataBean DataFormatada = new FormataDataBean();
	      
	      while (res.next()){
	          if (!oidCRT.equals(res.getString("OID_Conhecimento"))){
	            edVolta = new Conhecimento_InternacionalED();

	            
                edVolta.setDT_Cruze(res.getString("dt_cruze"));
                DataFormatada.setDT_FormataData(edVolta.getDT_Cruze());
	            edVolta.setDT_Cruze(DataFormatada.getDT_FormataData());
           
			    edVolta.setDT_Entrega(res.getString("dt_entrega"));
			    DataFormatada.setDT_FormataData(edVolta.getDT_Entrega());
	            edVolta.setDT_Entrega(DataFormatada.getDT_FormataData());
	            
	            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
	            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
	            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
	            edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
	            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	            
	            PessoaBean pE = PessoaBean.getByOID(res.getString("oid_pessoa_pagador"));
	            edVolta.setNM_Pessoa_Pagador(pE.getNM_Razao_Social());
	            
	            edVolta.setVL_Total_Frete(res.getDouble("vl_faturar"));
	            
	            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  								res.getString("NR_Permisso") + ".";
	  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  			}
	  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
	  		    
                edVolta.setNM_Pais_Embarque(res.getString("pOr").trim() + " - " + res.getString("pDest").trim());
	          } else {
	              edVolta = new Conhecimento_InternacionalED();
	              if(ed.getDM_SO().equals("NC")){
	                  String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  	  			  String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  	  			  						  res.getString("NR_Permisso") + ".";
	  	  			  int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  	  			  for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  	  			  }
	  	  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
	              }
	              edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	              edVolta.setDT_Emissao(res.getString("DT_Emissao"));
		            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
		            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
	              edVolta.setNM_Pais_Embarque(res.getString("pOr").trim() + " - " + res.getString("pDest").trim());
	              PessoaBean pE = PessoaBean.getByOID(res.getString("oid_pessoa_pagador"));
	              edVolta.setNM_Pessoa_Pagador(pE.getNM_Razao_Social());
		            
	              edVolta.setVL_Total_Frete(res.getDouble("vl_faturar"));
	          }
                
                oidCRT = res.getString("OID_Conhecimento");
                // System.out.println("add...");
                lista.add(edVolta);
	        }
	      util.closeResultset(res);
          Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
          conhecimento_InternacionalRL.geraRelCRT_NaoFaturado(ed, lista, response);
        
        
      } catch(Exception exc) {
          util.closeResultset(res);
        exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
  	  }
    }
  
  public void geraRelFaturado(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql, oidCRT, busca = null;
      ArrayList lista = new ArrayList();
      long valOid = 0;
      ResultSet res = null;
      ResultSet res2 = null;
      
      try{
          

	      sql = " select ci.*, po.cd_pais as pOr, pd.cd_pais as pDest, cf.vl_faturar, cf.oid_pessoa_pagador, " +
	      		" duplicatas.dt_emissao as dt_fatura, duplicatas.dt_vencimento, duplicatas.oid_duplicata " +
	         	" from Conhecimentos_Internacionais ci, paises po, cidades co, regioes_estados reo, estados eo, regioes_paises rpo," +
	        	" paises pd, cidades cd, regioes_estados red, estados ed, regioes_paises rpd, conhecimentos_faturamentos cf, duplicatas " +
	        	" where ci.oid_cidade = co.oid_cidade " +
	        	" and co.oid_regiao_estado = reo.oid_regiao_estado " +
	        	" and reo.oid_estado = eo.oid_estado " +
	        	" and eo.oid_regiao_pais = rpo.oid_regiao_pais " +
	        	" and rpo.oid_pais = po.oid_pais " +
	        	" and ci.oid_cidade_destino = cd.oid_cidade" +
	        	" and cd.oid_regiao_estado = red.oid_regiao_estado" +
	        	" and red.oid_estado = ed.oid_estado" +
	        	" and ed.oid_regiao_pais = rpd.oid_regiao_pais" +
	        	" and rpd.oid_pais = pd.oid_pais "+
	        	" and cf.oid_conhecimento = ci.oid_conhecimento "+
	        	" and cf.oid_duplicata = duplicatas.oid_duplicata ";
	
	      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
	            sql += " and ci.OID_Unidade = " + ed.getOID_Unidade_Origem();
	      }
	      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
	            sql += " and ci.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
	      }
	      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
	            sql += " and ci.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
	      }
	      if (JavaUtil.doValida(ed.getOID_Pessoa_Pagador())){
	            sql += " and cf.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "' ";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
	          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
			  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
	    		sql += " and CI.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
	    			!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
	    		sql += " and CI.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Origem()))){
	    	    sql += " and po.oid_pais = " + ed.getOid_Origem() + "";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Destino()))){
	    	    sql += " and pd.oid_pais = " + ed.getOid_Destino() + "";
	      }
	    	
	      sql += "order by ci.nr_conhecimento, ci.dt_emissao ";
//// System.out.println(sql.toString());
	      res = this.executasql.executarConsulta(sql.toString());
	      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();
	      oidCRT = "";
	      FormataDataBean DataFormatada = new FormataDataBean();
	      
	      while (res.next()){
	            edVolta = new Conhecimento_InternacionalED();

	            Data dt = new Data();
	            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
	            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
	            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
                edVolta.setDT_Fatura(res.getString("dt_fatura"));
                DataFormatada.setDT_FormataData(edVolta.getDT_Fatura());
	            edVolta.setDT_Fatura(DataFormatada.getDT_FormataData());
			    edVolta.setDT_Vencimento(res.getString("dt_vencimento"));
			    DataFormatada.setDT_FormataData(edVolta.getDT_Vencimento());
	            edVolta.setDT_Vencimento(DataFormatada.getDT_FormataData());
	            
	            Calendar hj = Data.stringToCalendar(edVolta.getDT_Emissao(),"dd/MM/yyyy");

	            busca = "select dt_movimento as pgto from movimentos_duplicatas " +
	            		" where (oid_tipo_instrucao = 6 or oid_tipo_instrucao = 20 or oid_tipo_instrucao = 39 or oid_tipo_instrucao = 43) " +
	            		" and oid_duplicata = '" + res.getString("oid_duplicata") + "'";
//// System.out.println(busca);
	            res2 = this.executasql.executarConsulta(busca);
	            while(res2.next()){
	                edVolta.setDT_Pagamento(res2.getString("pgto"));
	                DataFormatada.setDT_FormataData(edVolta.getDT_Pagamento());
		            edVolta.setDT_Pagamento(DataFormatada.getDT_FormataData());
		            Calendar pgto = Data.stringToCalendar(edVolta.getDT_Pagamento(),"dd/MM/yyyy");
		            edVolta.setDias_Pgto(dt.diferencaDias(pgto,hj));
	            }
	            
			    Calendar fat = Data.stringToCalendar(edVolta.getDT_Fatura(),"dd/MM/yyyy");
			    Calendar vcto = Data.stringToCalendar(edVolta.getDT_Vencimento(),"dd/MM/yyyy");
			    
	            edVolta.setDias_Fatura(dt.diferencaDias(fat,hj));	
	            edVolta.setDias_Vcto(dt.diferencaDias(vcto,hj));
	            
	            edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
	            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	            
	            edVolta.setVL_Total_Frete(res.getDouble("vl_faturar"));
	            
	            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  								res.getString("NR_Permisso") + ".";
	  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  			}
	  			if(!oidCRT.equals(res.getString("OID_Conhecimento"))){
		  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
		  			PessoaBean pE = PessoaBean.getByOID(res.getString("oid_pessoa_pagador"));
		            edVolta.setNM_Pessoa_Pagador(pE.getNM_Razao_Social());
	  			}
	  		    
                // System.out.println("add...");
                lista.add(edVolta);
	        }
	      util.closeResultset(res);
          Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
          conhecimento_InternacionalRL.geraRelCRTFaturado(ed, lista, response);
        
        
      } catch(Exception exc) {
          util.closeResultset(res);
        exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
  	  }
    }
  
  public void geraRelCRT_Comissao(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql, oidCRT = null;
      ArrayList lista = new ArrayList();
      long valOid = 0;
      ResultSet res = null;
      ResultSet res2 = null;
      
      try{

	      sql = " select ci.*, po.cd_pais as pOr, pd.cd_pais as pDest " +
	         	" from Conhecimentos_Internacionais ci, paises po, cidades co, regioes_estados reo, estados eo, regioes_paises rpo," +
	        	" paises pd, cidades cd, regioes_estados red, estados ed, regioes_paises rpd " +
	        	" where ci.oid_cidade = co.oid_cidade " +
	        	" and co.oid_regiao_estado = reo.oid_regiao_estado " +
	        	" and reo.oid_estado = eo.oid_estado " +
	        	" and eo.oid_regiao_pais = rpo.oid_regiao_pais " +
	        	" and rpo.oid_pais = po.oid_pais " +
	        	" and ci.oid_cidade_destino = cd.oid_cidade" +
	        	" and cd.oid_regiao_estado = red.oid_regiao_estado" +
	        	" and red.oid_estado = ed.oid_estado" +
	        	" and ed.oid_regiao_pais = rpd.oid_regiao_pais" +
	        	" and rpd.oid_pais = pd.oid_pais " +
	        	" and ci.dm_situacao != 'C' ";
	
	      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
	            sql += " and CI.OID_Unidade = " + ed.getOID_Unidade_Origem();
	      }
	      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
	            sql += " and CI.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
	      }
	      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
	            sql += " and CI.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
	      }
	      if (String.valueOf(ed.getOID_Vendedor()) != null && !String.valueOf(ed.getOID_Vendedor()).equals("")){
	            sql += " and CI.OID_Vendedor = '" + ed.getOID_Vendedor() + "'";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
	          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
			  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
	    		sql += " and CI.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	      }
	      
	      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
	    			!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
	    		sql += " and CI.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
	      }
	      sql += "order by ci.oid_vendedor, ci.dt_emissao ";
// System.out.println(sql.toString());
	      res = this.executasql.executarConsulta(sql.toString());
	      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();
	      oidCRT = "";
	      String Pagador = "";
	      FormataDataBean DataFormatada = new FormataDataBean();
	      
	      while (res.next()){
	            edVolta = new Conhecimento_InternacionalED();
	            
	            Pagador = res.getString("oid_pessoa_devedor_importador");
	            double exp = res.getDouble("VL_Gasto_Remetente1") + 
   			 				 res.getDouble("VL_Gasto_Remetente2") + 
   			 				 res.getDouble("VL_Gasto_Remetente3") + 
   			 				 res.getDouble("VL_Gasto_Remetente4");
	            if(exp > 0) Pagador = res.getString("oid_pessoa_devedor_exportador");
                	            
	            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
	            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
	            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
	            edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
	            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	            
	            PessoaBean pE = PessoaBean.getByOID(Pagador);
	            edVolta.setNM_Pessoa_Pagador(pE.getNM_Razao_Social());
	            
	            edVolta.setVL_Total_Frete(res.getDouble("VL_Gasto_Remetente1") + 
	                    				  res.getDouble("VL_Gasto_Remetente2") + 
	                    				  res.getDouble("VL_Gasto_Remetente3") + 
	                    				  res.getDouble("VL_Gasto_Remetente4") + 
	                    				  res.getDouble("VL_Gasto_Destinatario1") + 
	                    				  res.getDouble("VL_Gasto_Destinatario2") + 
	                    				  res.getDouble("VL_Gasto_Destinatario3") + 
	                    				  res.getDouble("VL_Gasto_Destinatario4"));
	            
	            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  								res.getString("NR_Permisso") + ".";
	  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  			}
	  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
	  		    
                edVolta.setNM_Pais_Embarque(res.getString("pOr").trim() + " - " + res.getString("pDest").trim());
                
                VendedorBean vend = VendedorBean.getByOID_Vendedor(res.getString("oid_vendedor"));
                edVolta.setNM_Vendedor(vend.getNM_Fantasia());
                edVolta.setPE_Comissao(vend.getPE_Comissao());
// System.out.println(vend.getNM_Fantasia());                
                edVolta.setVL_Comissao((edVolta.getVL_Total_Frete()*edVolta.getPE_Comissao())/100);
                
                oidCRT = res.getString("OID_Conhecimento");
                // System.out.println("add...");
                lista.add(edVolta);
	        }
	      util.closeResultset(res);
          Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
          conhecimento_InternacionalRL.geraRelCRT_Comissao(ed, lista, response);
        
        
      } catch(Exception exc) {
          util.closeResultset(res);
        exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
  	  }
    }
  
  public void geraRelConhecInternacionalMultiplo(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      long valOid = 0;

        sql = "select * from Conhecimentos_Internacionais where 1=1 ";

        if (JavaUtil.doValida(ed.getOID_Conhecimento())){
          sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
        }
        if (JavaUtil.doValida(String.valueOf(ed.getNR_Conhecimento()))){
          sql += " and Conhecimentos_Internacionais.NR_Conhecimento >= " + ed.getNR_Conhecimento();
        }
        if (JavaUtil.doValida(String.valueOf(ed.getNR_Conhecimento_Final()))){
            sql += " and Conhecimentos_Internacionais.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final();
          }
        if (JavaUtil.doValida(String.valueOf(ed.getOID_Unidade_Origem()))){
          sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_Origem();
        }
        if (JavaUtil.doValida(String.valueOf(ed.getOID_Unidade_Destino()))){
            sql += " and Conhecimentos_Internacionais.OID_Unidade_destino = " + ed.getOID_Unidade_Destino();
        }
        if (JavaUtil.doValida(String.valueOf(ed.getOID_Unidade_Fronteira()))){
        	sql += " and Conhecimentos_Internacionais.OID_Unidade_Fronteira = " + ed.getOID_Unidade_Fronteira();
        }
        if (JavaUtil.doValida(ed.getDT_Emissao())){
          sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
        }
        if (JavaUtil.doValida(ed.getDT_Fim())){
          sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDT_Fim() + "'";
        }
        
        sql += " order by nr_conhecimento ";
        
// System.out.println(sql);
      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

      ResultSet res = this.executasql.executarConsulta(sql.toString());
      try{
        Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
        conhecimento_InternacionalRL.geraRelConhecInternacionalMultiplo(res, ed,response, executasql);
      } finally {
          util.closeResultset(res);
  	}

    }
  
  public void LiberaFaturamentoInternacionalMultiplo(Conhecimento_InternacionalED ed)throws Excecoes{

      String sql = null;
      long valOid = 0;
      double vl_remetente = 0;
      double vl_destinatario = 0;
      ArrayList lista = new ArrayList();

        sql = "select * from Conhecimentos_Internacionais where dm_situacao != 'C' ";

        if (JavaUtil.doValida(ed.getOID_Conhecimento())){
          sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
        }
        if (JavaUtil.doValida(String.valueOf(ed.getNR_Conhecimento()))){
          sql += " and Conhecimentos_Internacionais.NR_Conhecimento >= " + ed.getNR_Conhecimento();
        }
        if (JavaUtil.doValida(String.valueOf(ed.getNR_Conhecimento_Final()))){
            sql += " and Conhecimentos_Internacionais.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final();
          }
        if (JavaUtil.doValida(String.valueOf(ed.getOID_Unidade_Origem()))){
          sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_Origem();
        }
        if (JavaUtil.doValida(ed.getDT_Emissao())){
          sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
        }
        if (JavaUtil.doValida(ed.getDT_Fim())){
          sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDT_Fim() + "'";
        }
        sql += " order by nr_conhecimento ";
        
// System.out.println(sql);
      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();
      Data data = new Data();
      ResultSet res = this.executasql.executarConsulta(sql.toString());
      try{
          FormataDataBean DataFormatada = new FormataDataBean();
          while (res.next()){
              edVolta = new Conhecimento_InternacionalED();
	   		  String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
			  String nr_CRT_Parcial = res.getString("CD_Pais") + "." + res.getString("NR_Permisso") + ".";
			  int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
			  for(int a = 0 ; a < completa_Nr_CRT ; a++){
			      nr_CRT_Parcial = nr_CRT_Parcial + "0";
			  }
			  edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
			  
			  if(res.getString("DM_Situacao").equals("L")){
			      throw new Exception("<font color='#FF0000'><strong> Conhecimento "+edVolta.getNM_Conhecimento()+" j� liberado!</strong></font>");
			  }
			  if(res.getString("DM_Situacao").equals("F")){
			      throw new Exception("<font color='#FF0000'><strong> Conhecimento "+edVolta.getNM_Conhecimento()+" j� Faturado!</strong></font>");
			  }
			  
			  vl_remetente = res.getDouble("VL_Gasto_Remetente1") + 
		                	 res.getDouble("VL_Gasto_Remetente2") + 
		                	 res.getDouble("VL_Gasto_Remetente3") + 
		                	 res.getDouble("VL_Gasto_Remetente4");
			  vl_destinatario = res.getDouble("VL_Gasto_Destinatario1") + 
		                		res.getDouble("VL_Gasto_Destinatario2") + 
		                		res.getDouble("VL_Gasto_Destinatario3") + 
		                		res.getDouble("VL_Gasto_Destinatario4");
// System.out.println("remetente: "+vl_remetente);
// System.out.println("destinatario: "+vl_destinatario);
              String oid_Conhecimento = res.getString("oid_Conhecimento");
    		  double exp = vl_remetente;
    		  double imp = vl_destinatario;
    		   lista.add(edVolta);
          }
          if(lista.size() == 0){
		      throw new Exception("<font color='#FF0000'><strong> N�o foram encontrados Conhecimento para liberar!</strong></font>");
		  }
      } catch (SQLException e){
          util.closeResultset(res);
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "LiberaFaturamentoInternacionalMultiplo(Conhecimento_InternacionalED ed)");
      } catch (Exception exc){
          util.closeResultset(res);
          throw new Excecoes(exc.getMessage(), exc, getClass().getName(), "LiberaFaturamentoInternacionalMultiplo(Conhecimento_InternacionalED ed)");
      } 
    }
  
  
  public void geraRelCusto(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql, oidCRT = null;
      ArrayList lista = new ArrayList();
      long valOid = 0;
      ResultSet res = null;
      ResultSet res2 = null;
      double vl_remetente = 0;
      double vl_destinatario = 0;
      double vl_truck = 0;
      double vl_carreta = 0;
      double vl_outros = 0;
      double vl_despesa_OF = 0;
      double vl_despesa_CRT = 0;
      double vl_receita_CRT = 0;
      int nr_crts = 0;
      int nr_mics = 0;
      
      try{

	      sql = " select ci.*, po.cd_pais as pOr, pd.cd_pais as pDest, mi.nr_manifesto_internacional, mi.oid_manifesto_internacional, " +
	      		" mi.cd_roteiro, mi.oid_veiculo " +
	         	" from Conhecimentos_Internacionais ci, paises po, cidades co, regioes_estados reo, estados eo, regioes_paises rpo," +
	        	" paises pd, cidades cd, regioes_estados red, estados ed, regioes_paises rpd, Manifestos_Internacionais mi " +
	        	" where ci.oid_cidade = co.oid_cidade " +
	        	" and co.oid_regiao_estado = reo.oid_regiao_estado " +
	        	" and reo.oid_estado = eo.oid_estado " +
	        	" and eo.oid_regiao_pais = rpo.oid_regiao_pais " +
	        	" and rpo.oid_pais = po.oid_pais " +
	        	" and ci.oid_cidade_destino = cd.oid_cidade" +
	        	" and cd.oid_regiao_estado = red.oid_regiao_estado" +
	        	" and red.oid_estado = ed.oid_estado" +
	        	" and ed.oid_regiao_pais = rpd.oid_regiao_pais" +
	        	" and rpd.oid_pais = pd.oid_pais "+
	        	" and ci.oid_conhecimento = viagens_internacionais.oid_conhecimento "+
	        	" and viagens_internacionais.oid_manifesto_internacional = mi.oid_manifesto_internacional ";
	
	      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
	            sql += " and ci.OID_Unidade = " + ed.getOID_Unidade_Origem();
	      }
	      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
	            sql += " and ci.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
	      }
	      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
	            sql += " and ci.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
	          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
			  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
	    		sql += " and CI.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	      }
	      if (JavaUtil.doValida(ed.getDM_Situacao()) && !ed.getDM_Situacao().equals("T")){
	    		sql += " AND CI.dm_situacao = '" + ed.getDM_Situacao() + "' ";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
	    			!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
	    		sql += " and CI.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Origem()))){
	    	    sql += " and po.oid_pais = " + ed.getOid_Origem() + "";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_Destino()))){
	    	    sql += " and pd.oid_pais = " + ed.getOid_Destino() + "";
	      }
	      
	      if (JavaUtil.doValida(String.valueOf(ed.getNR_Conhecimento()))){
	            sql += " and ci.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getNM_Manifesto()))){
	            sql += " and mi.NR_Manifesto = '" + ed.getNM_Manifesto() + "'";
	      }
	      if (ed.getDM_SO() != null && ed.getDM_SO().equals("CM")){
	          sql += "order by mi.NR_Manifesto, ci.dt_emissao, ci.OID_Pessoa ";
	      } else {
	          sql += "order by ci.dt_emissao, ci.OID_Pessoa ";
	      }
// System.out.println(sql.toString());
	      res = this.executasql.executarConsulta(sql.toString());
	      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();
	      oidCRT = "";
	      FormataDataBean DataFormatada = new FormataDataBean();
	      
	      while (res.next()){
	          	vl_despesa_OF = 0;
	          	vl_despesa_CRT = 0;
	            edVolta = new Conhecimento_InternacionalED();
	            
	            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  								res.getString("NR_Permisso") + ".";
	  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  			}
	  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);

	  		    PessoaBean pR = PessoaBean.getByOID(res.getString("oid_pessoa"));
	            edVolta.setNM_Pessoa_Remetente(pR.getNM_Razao_Social());
	            PessoaBean pD = PessoaBean.getByOID(res.getString("oid_pessoa_destinatario"));
	            edVolta.setNM_Pessoa_Destinatario(pD.getNM_Razao_Social());
	            
	            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
	            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
	            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
	            
	            edVolta.setNM_Manifesto(res.getString("NR_Manifesto_Internacional"));
	            edVolta.setOID_Veiculo(res.getString("oid_veiculo"));

	            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	            
                edVolta.setNM_Pais_Embarque(res.getString("pOr").trim() + " - " + res.getString("pDest").trim());
                
                edVolta.setNM_Roteiro("");
                edVolta.setCD_Roteiro("");
                if (JavaUtil.doValida(res.getString("CD_Roteiro"))){
                    sql = "Select  "+
      				"Roteiros.CD_Roteiro, " +
      				"Roteiros.NM_Roteiro, "+
      				"Roteiros.CD_Roteiro, " +
      				"Roteiros.vl_km_truck, " +
      				"Roteiros.vl_km_carreta, " +
      				"Roteiros.vl_km_outros " +
      				" from Roteiros "+
      				" where Roteiros.CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";
      				
    				sql += " Order by Roteiros.CD_Roteiro, Rotas_Roteiros.NR_Sequencia ";
// System.out.println("ROT sql : "+sql);
    		
    				res2 = this.executasql.executarConsulta(sql);
    		
    				while (res2.next()){
    				    vl_truck = res2.getDouble("vl_km_truck");
    				    vl_carreta = res2.getDouble("vl_km_carreta");
    				    vl_outros = res2.getDouble("vl_km_outros");
    				    edVolta.setNM_Roteiro(res2.getString("nm_roteiro"));
    				    edVolta.setCD_Roteiro(res2.getString("cd_roteiro"));
    				}
				}
                VeiculoBean v = VeiculoBean.getByOID(edVolta.getOID_Veiculo());
      			if(JavaUtil.doValida(v.getDM_Tipo_Implemento())){
    	  			if(v.getDM_Tipo_Implemento().equals("5") || v.getDM_Tipo_Implemento().equals("6") || v.getDM_Tipo_Implemento().equals("7")) 
    	  			    edVolta.setVL_Rota(vl_truck);
    	  			if(v.getDM_Tipo_Implemento().equals("1")) 
    	  			    edVolta.setVL_Rota(vl_carreta);
    	  			if(v.getDM_Tipo_Implemento().equals("9") || v.getDM_Tipo_Implemento().equals("8")) 
    	  			    edVolta.setVL_Rota(vl_outros);
      			} else edVolta.setVL_Rota(0.0);
      			edVolta.setNR_Placa(v.getNR_Placa());
                
                vl_remetente = res.getDouble("VL_Gasto_Remetente1") + 
				           	   res.getDouble("VL_Gasto_Remetente2") + 
				           	   res.getDouble("VL_Gasto_Remetente3") + 
				           	   res.getDouble("VL_Gasto_Remetente4");
				vl_destinatario = res.getDouble("VL_Gasto_Destinatario1") + 
								  res.getDouble("VL_Gasto_Destinatario2") + 
				           		  res.getDouble("VL_Gasto_Destinatario3") + 
				           		  res.getDouble("VL_Gasto_Destinatario4");
				vl_remetente = convertToReal(res.getString("DT_Emissao"), vl_remetente , Parametro_FixoED.getInstancia().getOID_Moeda_Dollar());
				vl_destinatario = convertToReal(res.getString("DT_Emissao"), vl_destinatario , Parametro_FixoED.getInstancia().getOID_Moeda_Dollar());
			 	vl_receita_CRT = vl_remetente+vl_destinatario;
			 	
			 	sql =  " SELECT count(oid_Manifesto_Internacional) " +
						" from viagens_internacionais ";
				sql += " WHERE viagens_internacionais.oid_conhecimento = '" + res.getString("OID_Conhecimento") + "'";
				res2 = null;
				res2 = this.executasql.executarConsulta(sql);
				while (res2.next()){
				    nr_mics = res2.getInt(1);
				}
				if(nr_mics > 0){
				    edVolta.setVL_Receita(vl_receita_CRT/nr_mics);
				} else edVolta.setVL_Receita(vl_receita_CRT);
			 	
			 	sql =  " SELECT Conhecimentos_custos.vl_faturar as vl_custo, Conhecimentos_custos.dt_emissao, Conhecimentos_custos.oid_moeda " +
					   " from Conhecimentos_custos ";
				sql += " WHERE Conhecimentos_custos.oid_conhecimento = '" + res.getString("OID_Conhecimento") + "'";
				res2 = null;
				res2 = this.executasql.executarConsulta(sql);
	    		while (res2.next()){
	    		    vl_despesa_CRT += convertToReal(res2.getString("DT_Emissao"), res2.getDouble("vl_custo") , res2.getInt("oid_moeda"));
	    		    //vl_despesa_CRT += res2.getDouble("vl_custo");
	    		}
	    		sql =  " SELECT Ordens_Fretes.vl_Ordem_Frete_convertida " +
					   " from Ordens_MIC, Manifestos_Internacionais, Ordens_Fretes ";
				sql += " WHERE Ordens_MIC.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
				sql += " AND Ordens_MIC.OID_Ordem_Frete = Ordens_Fretes.OID_Ordem_Frete ";
				sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = '" + res.getString("oid_Manifesto_Internacional") + "'";
				res2 = null;
				res2 = this.executasql.executarConsulta(sql);
				while (res2.next()){
				    vl_despesa_OF += res2.getDouble("vl_Ordem_Frete_convertida");
				}
	    		sql =  " SELECT count(oid_conhecimento) " +
						" from viagens_internacionais ";
				sql += " WHERE viagens_internacionais.oid_Manifesto_Internacional = '" + res.getString("oid_Manifesto_Internacional") + "'";
				res2 = null;
				res2 = this.executasql.executarConsulta(sql);
				while (res2.next()){
				    nr_crts = res2.getInt(1);
				}
				
				if(nr_crts > 0){
				    edVolta.setVL_Despesa((vl_despesa_OF/nr_crts)+vl_despesa_CRT);
				} else edVolta.setVL_Despesa(vl_despesa_OF+vl_despesa_CRT);
	          
                lista.add(edVolta);
	        }
	      util.closeResultset(res);
          Conhecimento_InternacionalRL conhecimento_InternacionalRL = new Conhecimento_InternacionalRL();
          conhecimento_InternacionalRL.geraRelCusto(ed, lista, response);
        
        
      } catch(Exception exc) {
          util.closeResultset(res);
          util.closeResultset(res2);
        exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
  	  }
    }
  
  private double convertToReal(String data, double vl, int moeda) throws Excecoes {
      
      double vl_real = 0;
      return Valor.round(vl_real, 2);
  }
  
  
}
