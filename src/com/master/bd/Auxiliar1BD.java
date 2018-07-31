package com.master.bd;

import java.io.File;
import java.io.FilenameFilter;
import java.io.LineNumberReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Auxiliar1ED;
import com.master.ed.Calcula_FreteED;
import com.master.ed.CompromissoED;
import com.master.ed.ConhecimentoED;
import com.master.ed.ContaED;
import com.master.ed.EDI_ImportacaoED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.PessoaED;
import com.master.rl.ContaRL;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.root.CidadeBean;
import com.master.root.ClienteBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.ManipulaString;
import com.master.util.Mensagens;
import com.master.util.SeparaEndereco;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

import br.core.importacao.v200.LeitorXML;
import br.core.importacao.v200.TNFe;
import br.core.importacao.v200.TNfeProc;

public class Auxiliar1BD
    extends Transacao {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  CidadeBean cidadeBean = new CidadeBean ();
  PessoaED pessoaED = new PessoaED ();
  Nota_FiscalBD Nota_FiscalBD = null;
  ConhecimentoBD ConhecimentoBD = null;
  PessoaBD PessoaBD = null;
  Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;

  public Auxiliar1BD (ExecutaSQL sql) {
    this.executasql = sql;

  }

  public void inclui (Auxiliar1ED ed) throws Excecoes {

    //// //System.out.println (">>>>>Auxiliar1 inc 1 ");

    String sql = null;

    try {

      String valOid = String.valueOf (System.currentTimeMillis ()).substring (7 , 12);

      //// //System.out.println (">>>>>Auxiliar1 inc 2 ");

      sql = " insert into Auxiliar1 (OID_Auxiliar1, Nr_Sort, oid_tabela, NM_Tabela, NM_Classifica1, NM_Classifica2, NM_Classifica3, NM_Classifica4, NM_Classifica5, NM_Classifica6, NM_Classifica7, VL_Classifica1, VL_Classifica2, VL_Classifica3, VL_Classifica4, VL_Classifica5, VL_Classifica6, VL_Classifica7, D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15, D16, D17, D18, D19, D20, D21, D22, D23, D24, D25, D26, D27, D28, D29, D30, D31, DTotal, A1, A2, A3 ) values ";
      sql += "(" + valOid + ",";

      sql += ed.getNR_Sort () + ",";

      sql += "'" + ed.getOID_Tabela () + "',";
      sql += "'" + ed.getNM_Tabela () + "','";
      sql += "'" + ed.getNM_Classifica1 () == null ? "null," : ed.getNM_Classifica1 () + "','";
      sql += "'" + ed.getNM_Classifica2 () == null ? "null," : ed.getNM_Classifica2 () + "','";
      sql += "'" + ed.getNM_Classifica3 () == null ? "null," : ed.getNM_Classifica3 () + "','";
      sql += "'" + ed.getNM_Classifica4 () == null ? "null," : ed.getNM_Classifica4 () + "','";
      sql += "'" + ed.getNM_Classifica5 () == null ? "null," : ed.getNM_Classifica5 () + "','";
      sql += "'" + ed.getNM_Classifica6 () == null ? "null," : ed.getNM_Classifica6 () + "','";
      sql += "'" + ed.getNM_Classifica7 () == null ? "null," : ed.getNM_Classifica7 () + "',";
      sql += ed.getVL_Classifica1 () + ",";
      sql += ed.getVL_Classifica2 () + ",";
      sql += ed.getVL_Classifica3 () + ",";
      sql += ed.getVL_Classifica4 () + ",";
      sql += ed.getVL_Classifica5 () + ",";
      sql += ed.getVL_Classifica6 () + ",";
      sql += ed.getVL_Classifica7 () + ",";
      sql += ed.getD0 () + ",";
      sql += ed.getD1 () + ",";
      sql += ed.getD2 () + ",";
      sql += ed.getD3 () + ",";
      sql += ed.getD4 () + ",";
      sql += ed.getD5 () + ",";
      sql += ed.getD6 () + ",";
      sql += ed.getD7 () + ",";
      sql += ed.getD8 () + ",";
      sql += ed.getD9 () + ",";
      sql += ed.getD10 () + ",";
      sql += ed.getD11 () + ",";
      sql += ed.getD12 () + ",";
      sql += ed.getD13 () + ",";
      sql += ed.getD14 () + ",";
      sql += ed.getD15 () + ",";
      sql += ed.getD16 () + ",";
      sql += ed.getD17 () + ",";
      sql += ed.getD18 () + ",";
      sql += ed.getD19 () + ",";
      sql += ed.getD20 () + ",";
      sql += ed.getD21 () + ",";
      sql += ed.getD22 () + ",";
      sql += ed.getD23 () + ",";
      sql += ed.getD24 () + ",";
      sql += ed.getD25 () + ",";
      sql += ed.getD26 () + ",";
      sql += ed.getD27 () + ",";
      sql += ed.getD28 () + ",";
      sql += ed.getD29 () + ",";
      sql += ed.getD30 () + ",";
      sql += ed.getD31 () + ",";
      sql += ed.getDTotal () + ",";
      sql += ed.getA1 () + ",";
      sql += ed.getA2 () + ",";
      sql += ed.getA3 () + ")";
      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa

      // //System.out.println (sql);

      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de Auxiliar1 de dados");
      excecoes.setExc (exc);
    }
  }

  public void deleta (Auxiliar1ED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from Auxiliar1 WHERE oid_Auxiliar1 = ";
      sql += "(" + ed.getOID_auxiliar1 () + ")";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setMensagem ("Erro de Auxiliar1 de dados");
      excecoes.setExc (exc);
    }
  }

  public byte[] geraRelacaoContas (ContaED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    try {

      ResultSet res = null;

      sql = " select * from contas, grupos_contas where " +
          " contas.oid_grupo_Conta = grupos_contas.oid_grupo_conta " +
          " order by grupos_contas.nm_grupo_conta, Contas.nm_conta ";

      //// //System.out.println ("AUXILIAR sql ->>  " + sql);

      res = this.executasql.executarConsulta (sql.toString ());

      ContaRL conRL = new ContaRL ();

      return conRL.geraRelacaoContas (res , ed);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(GerencialED ed)");
    }
    return b;
  }

  public void testeLeituraNFE(String arquivo) throws Excecoes {

  	try{
  		if(!JavaUtil.doValida(arquivo))
  			arquivo="/data/doc-e/nfe/43110387283164000151550010000000101003252209-procNFe.xml";

  		TNfeProc procnfe = LeitorXML.leXML(arquivo);

  		if (procnfe != null) {
  			System.out.println("tem TNfeProc...");
            TNFe.InfNFe nfe = procnfe.getNFe().getInfNFe();
            System.out.println("... tem informacao");
            String ch = procnfe.getProtNFe().getInfProt().getChNFe();
            TNFe.InfNFe.Ide detalhe = nfe.getIde();
            System.out.println("Chave:"+ch);
            System.out.println("nro:"+detalhe.getNNF()+" |cfop:"+detalhe.getNatOp()+" |amb:"+detalhe.getTpAmb());
            Iterator<TNFe.InfNFe.Det> it = nfe.getDet().iterator();
            while(it.hasNext()){
                TNFe.InfNFe.Det d = (TNFe.InfNFe.Det)it.next();
                TNFe.InfNFe.Det.Prod p = d.getProd();
                System.out.println("prod:"+p.getCProd()+" |"+p.getXProd()+" |"+p.getUCom()+" |"+p.getQCom()+" |"+p.getVUnCom()+" |"+p.getVProd());
            }
        }

  	} catch(Exception e){
  		System.out.println("erro na leitura...");
  		e.printStackTrace();
  	}

  }

  public String leituraNFE(String arquivo) throws Excecoes {
	  String toReturn = "";
	  	try{
	  		if(!JavaUtil.doValida(arquivo))
	  			arquivo="/data/doc-e/nfe/43110387283164000151550010000000101003252209-procNFe.xml";

	  		TNfeProc procnfe = LeitorXML.leXML(arquivo);

	  		if (procnfe != null) {
	  			toReturn+="<font color='red'>Arquivo origem:"+arquivo+"</font> <br>---------- DADOS:<br>";
	  			System.out.println("tem TNfeProc...");
	            TNFe.InfNFe nfe = procnfe.getNFe().getInfNFe();
	            System.out.println("... tem informacao");
	            String ch = procnfe.getProtNFe().getInfProt().getChNFe();
	            TNFe.InfNFe.Ide detalhe = nfe.getIde();
	            toReturn+=("Chave:"+ch+"<br>");
	            toReturn+=("nro:"+detalhe.getNNF()+" |cfop:"+detalhe.getNatOp()+" |amb:"+detalhe.getTpAmb()+"<br>");
	            Iterator<TNFe.InfNFe.Det> it = nfe.getDet().iterator();
	            toReturn+=" - ITENS:<br>";
	            int conta=0;
	            while(it.hasNext()){
	            	conta++;
	                TNFe.InfNFe.Det d = (TNFe.InfNFe.Det)it.next();
	                TNFe.InfNFe.Det.Prod p = d.getProd();
	                toReturn+=(" >>> "+conta+":  PROD.:"+p.getCProd()+" - "+p.getXProd()+" |UN.:"+p.getUCom()+" |QTDE:"+p.getQCom()+" |V.UN.:"+p.getVUnCom()+" |VALOR:"+p.getVProd()+"<br>");
	            }
	            toReturn+="---------------------------------------------------------";
	        }

	  	} catch(Exception e){
	  		System.out.println("erro na leitura...");
	  		e.printStackTrace();
	  	}
	  	return toReturn;

	  }

  public void bacas (String executar, String arquivo) throws Exception {

     //System.out.println ("bacas executar->>  " + executar);
    //// //System.out.println ("bacas arquivo->>  " + arquivo);

    if ("importaCEP_Pessoas".equals (executar)) {
    	importaCEP_Pessoas (arquivo);
    }

    if ("importaPessoa_CSV_1".equals (executar)) {
    	importaPessoa_CSV_1 (arquivo);
    }

    if ("importaPessoa_CSV_2".equals (executar)) {
    	importaPessoa_CSV_2 (arquivo);
    }

    if ("importaPessoa_CSV_3".equals (executar)) {
    	importaPessoa_CSV_3 (arquivo);
    }

    if ("importaPessoa_CSV_4".equals (executar)) {
    	importaPessoa_CSV_4 (arquivo);
    }

    if ("acerta_peso".equals (executar)) {
      baca_acerta_peso_nf ("");
    }
    if ("acerta_CFOP".equals (executar)) {
      baca_acerta_CFOP ();
    }
    if ("acerta_data".equals (executar)) {
      baca_acerta_data_nf ("");
    }
    if ("acerta_data_lote".equals (executar)) {
      baca_acerta_data_lote ("");
    }
    if ("acerta_juro_multa".equals (executar)) {
      baca_acerta_valor_juros_multa ("");
    }
    if ("acerta_CEP".equals (executar)) {
      baca_acerta_CEP ("");
    }
    if ("acerta_lote".equals (executar)) {
      baca_acerta_oid_lote_fornecedor ("");
    }
    if ("importa_Pessoa_LayOut1".equals (executar)) {
      importaArquivo ("/data/edi/diversos/" , "importa_Pessoa_LayOut1");
    }
    if ("Inclui_Cliente".equals (executar)) {
      baca_Inclui_Cliente ("");
    }
    if ("Calcula_Seguro".equals (executar)) {
      this.bacas_calculaSeguro ();
    }
    if ("CD_Fornecedor".equals (executar)) {
      this.baca_CD_Fornecedor ("");
    }
    if ("baca_lote_entrega".equals (executar)) {
      this.baca_lote_entrega ("");
    }
    if ("baca_CalculaDV_Cto".equals (executar)) {
      this.baca_CalculaDV_Cto ("");
    }
    if ("baca_gera_MCC_ORF".equals (executar)) {
      this.baca_gera_MCC_ORF ("");
    }
    if ("baca_acerta_Previsao_Entrega".equals (executar)) {
        this.baca_acerta_Previsao_Entrega ();
      }
    if ("bacas_acertaMovimentoCto".equals (executar)) {
        this.bacas_acertaMovimentoCto ();
      }

    if ("baca_regera_Livro_Fiscal".equals (executar)) {
        this.baca_regera_Livro_Fiscal ("");
      }

    if ("baca_regera_Livro_Fiscal_Saida".equals (executar)) {
        this.baca_regera_Livro_Fiscal_Saida ("");
      }

    if ("importa_Plano_de_Contas".equals (executar)) {
        this.importa_Plano_de_Contas (arquivo);
      }

    if ("importa_Entrega".equals (executar)) {
        this.importa_Entrega (arquivo);
    }

    if ("baca_regera_Livro_Fiscal_Ent".equals (executar)) {
        this.baca_regera_Livro_Fiscal_Entrada ("");
      }
    if ("baca_acerta_veiculo_os".equals (executar)) {
        this.baca_acerta_veiculo_os ("");
      }
	if ("baca_regera_Livro_Fiscal_Compra".equals (executar)) {
        this.baca_regera_Livro_Fiscal_Entrada_Compras("");
      }

	if ("IBGE".equals (executar)) {
        this.AtualizaIBGE("/tmp/ibge_munic.csv");
      }

	if ("NFeXML".equals (executar)) {
        this.testeLeituraNFE(arquivo);
      }


  }

  private void baca_acerta_peso_nf (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_peso_nf");

    try {
      ResultSet res = null;
      ResultSet res2 = null;
      sql = " SELECT oid_Nota_Fiscal FROM Notas_Fiscais WHERE NR_Peso = 0 ";
      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        double vl_nota_fiscal = 0;
        double NR_Peso = 0;
        sql = " Select NR_Peso, VL_Nota_Fiscal FROM Conhecimentos, Conhecimentos_Notas_Fiscais WHERE Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
            " AND Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = '" + res.getString ("oid_Nota_Fiscal") + "'";
        //// //System.out.println (sql);

        res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {
          vl_nota_fiscal += res2.getDouble ("VL_Nota_Fiscal");
          NR_Peso += res2.getDouble ("NR_Peso");
        }
        sql = " UPDATE Notas_Fiscais SET NR_Peso=" + NR_Peso + " ,vl_nota_fiscal=" + vl_nota_fiscal + " WHERE oid_Nota_Fiscal='" + res.getString ("oid_Nota_Fiscal") + "'";
        executasql.executarUpdate (sql);

        //// //System.out.println (sql);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_peso_nf)");
    }
  }

  private void baca_acerta_veiculo_os (String acesso) throws Excecoes {

	    String sql = null;
	    //// //System.out.println ("baca_acerta_peso_nf");

	    try {
	      ResultSet res = null;
	      ResultSet res2 = null;
	      sql = " select oid_movimento_ordem_servico, ordens_servicos.oid_veiculo from ordens_servicos, movimentos_ordens_servicos where ordens_servicos.oid_ordem_servico=movimentos_ordens_servicos.oid_ordem_servico and movimentos_ordens_servicos.oid_veiculo is null";
	      //System.out.println (sql);
	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	        sql = " UPDATE movimentos_ordens_servicos SET oid_Veiculo='" + res.getString("oid_veiculo") + "' WHERE oid_Movimento_Ordem_Servico='" + res.getString ("oid_movimento_ordem_servico") + "'";
	        executasql.executarUpdate (sql);

	        //// //System.out.println (sql);
	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "baca_acerta_veiculo_os)");
	    }
	  }

	    private void baca_regera_Livro_Fiscal_Entrada_Compras (String acesso) throws Excecoes, SQLException {

	    //// //System.out.println ("baca_regera_Livro_Fiscal_Entrada ");
	    try {

	      Livro_FiscalED edLivro = new Livro_FiscalED (); // instancia um ed de Livro
	      // Altere este sql de modo a que retorne somente o oid das notaws a seremm regeradas
	      String sql = "select oid_Nota_Fiscal from notas_fiscais_transacoes where nr_nota_fiscal = 15258 and (DM_finalizado = 'S' or DM_finalizado = 'F')";
	      ResultSet res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	    	  //// //System.out.println("Regera Cto=" +res.getString("oid_Nota_Fiscal"));
			  Livro_FiscalBD LivroBD = new Livro_FiscalBD (executasql); // Instancia o Livro para gravar
	    	  edLivro.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
	    	  edLivro.setCD_Especie("NF");
	        // Aten�ao: aqui est� gerando somente para os livros de CTRC (sa�da)
	    	  LivroBD.geraLivro_Fiscal_Entrada_Simplificada(edLivro);
	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,  "baca_regera_Livro_Fiscal)");
	    }

		}
  private void baca_gera_MCC_ORF (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_gera_MCC_ORF");

    try {

      sql = "Select * " +
          " from " +
          " Ordens_Fretes, Pessoas " +
          " where Ordens_Fretes.oid_Fornecedor = Pessoas.oid_Pessoa " +
          " AND Ordens_Fretes.DM_Frete = 'T'  " +
          " AND Ordens_Fretes.oid_Unidade = 2 " +
          " AND Ordens_Fretes.VL_ORDEM_FRETE > 0 AND DM_Impresso='S' ";

      //// //System.out.println (sql);

      ResultSet resCTRC = null;
      resCTRC = this.executasql.executarConsulta (sql);

      //// //System.out.println ("----------ORDEM FRETE TERCEIRO ------INICIO-----------------");

      while (resCTRC.next ()) {

        //// //System.out.println ("TEM NR_Recibo->>" + resCTRC.getString ("NR_Recibo"));

        sql = "SELECT oid_Movimento_Conta_Corrente FROM Movimentos_Contas_correntes WHERE NR_Documento ='" + resCTRC.getString ("NR_Recibo") + "' AND VL_Lancamento = " + resCTRC.getString ("VL_Ordem_Frete");
        //// //System.out.println (sql);

        ResultSet rs = this.executasql.executarConsulta (sql);

        if (!rs.next ()) {

          //// //System.out.println ("nao tem no CC ->>" + resCTRC.getString ("NR_Recibo"));

          sql = " UPDATE Ordens_Fretes set dm_stamp='W' " +
              " WHERE Ordens_Fretes.OID_Ordem_Frete = '" + resCTRC.getString ("OID_Ordem_Frete") + "'";

          //// //System.out.println (sql);

          executasql.executarUpdate (sql);

          sql = " SELECT Contas_Correntes.oid_Conta_Corrente  ";
          sql += " FROM Unidades, Contas_Correntes ";
          sql += " WHERE Unidades.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente ";
          sql += " AND Unidades.OID_Unidade = " + resCTRC.getString ("OID_Unidade");
          rs = this.executasql.executarConsulta (sql);
          //// //System.out.println (sql);

          String oid_conta_corrente = "";
          while (rs.next ()) {
            //// //System.out.println ("TEM CONTA CORRENTE" + rs.getString ("oid_conta_corrente"));
            oid_conta_corrente = rs.getString ("oid_conta_corrente");
          }

          if (oid_conta_corrente != "") {
            Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED ();

            edMovConta.setDT_Movimento_Conta_Corrente (String.valueOf (resCTRC.getString ("dt_emissao")));
            //// //System.out.println ("inclui 1");
            edMovConta.setOid_Lote_Pagamento (0);
            //// //System.out.println ("inclui 2");
            edMovConta.setNR_Documento (String.valueOf (resCTRC.getString ("NR_Recibo")));
            edMovConta.setDM_Tipo_Lancamento ("W");
            //// //System.out.println ("inclui 6");
            edMovConta.setOID_Tipo_Documento (new Integer (3));
            edMovConta.setOid_Historico (new Integer (18));
            edMovConta.setOid_Conta_Corrente (oid_conta_corrente);
            //// //System.out.println ("inclui 10");

            edMovConta.setNM_Complemento_Historico ("Total do Frete Ordem  Nr:" + resCTRC.getString ("NR_Ordem_Frete") + "-" + resCTRC.getString ("NM_Razao_Social"));
            edMovConta.setDM_Debito_Credito ("C");
            edMovConta.setVL_Lancamento (new Double (resCTRC.getString ("VL_Ordem_Frete")));

            new Movimento_Conta_CorrenteBD (this.executasql).inclui (edMovConta);

            //// //System.out.println ("inclui =>>" + resCTRC.getString ("NR_Recibo") + "--->>" + resCTRC.getString ("VL_Ordem_Frete"));
          }
        }
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_gera_MCC_ORF)");
    }
  }

  private void baca_CalculaDV_Cto (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_oid_lote_fornecedor");

    try {
      ResultSet res = null;
      sql = " SELECT Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento " +
          " FROM Conhecimentos " +
          " WHERE Conhecimentos.DT_Emissao>='20/06/2007' ";
      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        this.calculaDV (res.getString ("NR_Conhecimento"));
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_CalculaDV_Cto)");
    }
  }

  public static String calculaDV (String nr_Conhecimento) throws Excecoes {
    String dv = "";
    String aux = String.valueOf (System.currentTimeMillis ()).toString ();
    nr_Conhecimento = JavaUtil.LFill (nr_Conhecimento , 6 , true);
    dv = aux.substring (aux.length () - 2 , aux.length () - 1) + nr_Conhecimento.substring (5 , 6) + aux.substring (aux.length () - 1 , aux.length ());
    return dv;
  }

  private void baca_acerta_oid_lote_fornecedor (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_oid_lote_fornecedor");

    try {
      ResultSet res = null;
      ResultSet res3 = null;
      sql = " SELECT Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, oid_Lote_Fornecedor, oid_Tipo_Movimento " +
          " FROM Conhecimentos, Movimentos_Conhecimentos " +
          " WHERE  Conhecimentos.oid_Conhecimento =  Movimentos_Conhecimentos.oid_Conhecimento " +
          " AND  Movimentos_Conhecimentos.oid_Lote_Fornecedor is not null " +
          " AND  Movimentos_Conhecimentos.oid_Lote_Fornecedor > 0 ";
      //" AND  Conhecimentos.DT_Emissao>='01/03/2007' ";
      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        sql = " SELECT oid_Documento_Lote_Fornecedor " +
            " FROM  Documentos_Lotes_Fornecedores " +
            " WHERE Documentos_Lotes_Fornecedores.NR_Documento =" + res.getString ("NR_Conhecimento") +
            " AND Documentos_Lotes_Fornecedores.oid_Conhecimento ='2830784CN1' " +
            " AND Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor = " + res.getString ("oid_lote_fornecedor") +
            " AND Documentos_Lotes_Fornecedores.oid_Tipo_Movimento = " + res.getString ("oid_Tipo_Movimento");
        //// //System.out.println (sql);

        res3 = this.executasql.executarConsulta (sql);
        if (res3.next ()) {
          sql = " UPDATE Documentos_Lotes_Fornecedores SET oid_cto_novo='X', oid_Conhecimento='" + res.getString ("oid_Conhecimento") + "' WHERE oid_Documento_Lote_Fornecedor='" + res3.getString ("oid_Documento_Lote_Fornecedor") + "'";
          //// //System.out.println (sql);
          executasql.executarUpdate (sql);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_oid_lote_fornecedor)");
    }
  }

  private void baca_regera_Livro_Fiscal (String acesso) throws Excecoes, SQLException {

	    //// //System.out.println ("baca_regera_Livro_Fiscal 222");
	    try {


	      Livro_FiscalED edLivro = new Livro_FiscalED (); // instancia um ed de Livro
	      // Altere este sql de modo a que retorne somente o oid das notaws a seremm regeradas
	      String sql = "select distinct(conhecimentos.oid_conhecimento) " +
	          "from conhecimentos " +
	          "where conhecimentos.DM_Tipo_Documento = 'C' " +
	          "and conhecimentos.dm_impresso = 'S' "; // +
	       //" and dt_emissao between '2008-10-01' and '2008-10-31' and oid_unidade = 5";
	          //"and conhecimentos.dt_emissao between '" + dt_inicio + "' and '" + dt_fim + "'";
	//// //System.out.println("BACA Regera FISCAl CTRC: "+sql);
	      ResultSet res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	    	  //// //System.out.println("Regera Cto=" +res.getString("Oid_conhecimento"));
			  Livro_FiscalBD LivroBD = new Livro_FiscalBD (executasql); // Instancia o Livro para gravar

	    	  edLivro.setOID_Conhecimento(res.getString("Oid_conhecimento")); // Set o oid do CTRC para gerar o livro
	        // Aten�ao: aqui est� gerando somente para os livros de CTRC (sa�da)
	    	  LivroBD.geraLivro_Fiscal_CTRC(edLivro);
	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
	                          "baca_regera_Livro_Fiscal)");
	    }

  }

  private void baca_regera_Livro_Fiscal_Saida (String acesso) throws Excecoes, SQLException {

	    //// //System.out.println ("baca_regera_Livro_Fiscal_Saida ");
	    try {

	      Livro_FiscalED edLivro = new Livro_FiscalED (); // instancia um ed de Livro
	      // Altere este sql de modo a que retorne somente o oid das notaws a seremm regeradas
	      String sql = "select oid_Nota_Fiscal from Notas_FIscais where oid_Modelo_Nota_Fiscal = 8 and dm_finalizado='F' and nr_nota_fiscal>0";
	      ResultSet res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	    	  //// //System.out.println("Regera Cto=" +res.getString("oid_Nota_Fiscal"));
			  Livro_FiscalBD LivroBD = new Livro_FiscalBD (executasql); // Instancia o Livro para gravar

	    	  edLivro.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal")); // Set o oid do CTRC para gerar o livro
	        // Aten�ao: aqui est� gerando somente para os livros de CTRC (sa�da)
	    	  LivroBD.geraLivro_Fiscal_Saidas(edLivro, "S");
	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,  "baca_regera_Livro_Fiscal)");
	    }

}

  private void baca_regera_Livro_Fiscal_Entrada (String acesso) throws Excecoes, SQLException {

	    //// //System.out.println ("baca_regera_Livro_Fiscal_Entrada ");
	    try {

	      Livro_FiscalED edLivro = new Livro_FiscalED (); // instancia um ed de Livro
	      // Altere este sql de modo a que retorne somente o oid das notaws a seremm regeradas
	      String sql = "select oid_Nota_Fiscal from Notas_FIscais where DM_Tipo_Nota_Fiscal='E'";
	      ResultSet res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	    	  //// //System.out.println("Regera Cto=" +res.getString("oid_Nota_Fiscal"));
			  Livro_FiscalBD LivroBD = new Livro_FiscalBD (executasql); // Instancia o Livro para gravar

	    	  edLivro.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal")); // Set o oid do CTRC para gerar o livro
	        // Aten�ao: aqui est� gerando somente para os livros de CTRC (sa�da)
	    	  LivroBD.geraLivro_Fiscal_Entradas(edLivro, "E");
	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,  "baca_regera_Livro_Fiscal)");
	    }

}

  private void baca_acerta_data_nf (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_data_nf");

    try {
      ResultSet res = null;
      sql = "                            SELECT Conhecimentos_notas_fiscais.oid_nota_Fiscal, Conhecimentos.dt_Emissao " +
          "                            FROM   Conhecimentos_notas_fiscais, Conhecimentos " +
          "                            WHERE  Conhecimentos.oid_conhecimento = Conhecimentos_notas_fiscais.oid_conhecimento " +
          "                            AND    Conhecimentos.dt_Emissao >='20/04/2006' " +
          "                            AND    Conhecimentos.dt_emissao <='30/04/2006' ";

      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        sql = " UPDATE Notas_Fiscais SET DT_Emissao='" + res.getString ("dt_Emissao") + "', DT_Entrada='" + res.getString ("dt_Emissao") + "' WHERE oid_Nota_Fiscal='" + res.getString ("oid_Nota_Fiscal") + "'";
        executasql.executarUpdate (sql);

        //// //System.out.println (sql);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_peso_nf)");
    }
  }

  private void baca_lote_entrega (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_lote_entrega");

    try {
      ResultSet res = null;
      sql = " SELECT  cto_homo.oid_comprovante_entrega " +
          " FROM    homologa.conhecimentos cto_homo " +
          " WHERE   cto_homo.oid_comprovante_entrega >0 " +
          " AND     cto_homo.dt_Emissao>'10/05/2007' ";

      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        //// //System.out.println ("oid_comprovante_entrega=>" + res.getString ("oid_comprovante_entrega"));
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_peso_nf)");
    }
  }

  private void baca_acerta_data_lote (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_peso_nf");

    try {
      ResultSet res = null;
      /*
             sql=" SELECT lotes_pagamentos.dt_emissao, Movimentos_contas_correntes.oid_movimento_conta_corrente " +
          " FROM Lotes_Pagamentos, Movimentos_contas_correntes " +
          " WHERE Lotes_Pagamentos.oid_lote_pagamento = Movimentos_contas_correntes.oid_lote_pagamento " +
          " AND Movimentos_contas_correntes.dm_tipo_lancamento ='G' ";

          //// //System.out.println (sql);
             res = this.executasql.executarConsulta (sql);
             while (res.next ()) {
        sql = " UPDATE Movimentos_contas_correntes SET dt_movimento_conta_corrente='" + res.getString("dt_emissao") +  "' WHERE oid_movimento_conta_corrente='" + res.getString("oid_movimento_conta_corrente")  + "'";
        //// //System.out.println (sql);
        executasql.executarUpdate(sql);

             }
       */
      sql = " SELECT movimentos_duplicatas.dt_movimento, Movimentos_contas_correntes.oid_movimento_conta_corrente " +
          " FROM movimentos_duplicatas, Movimentos_contas_correntes " +
          " WHERE movimentos_duplicatas.oid_movimento_duplicata = Movimentos_contas_correntes.oid_movimento_duplicata " +
          " AND Movimentos_contas_correntes.dm_tipo_lancamento ='G' ";

      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        sql = " UPDATE Movimentos_contas_correntes SET dt_movimento_conta_corrente='" + res.getString ("dt_movimento") + "' WHERE oid_movimento_conta_corrente='" + res.getString ("oid_movimento_conta_corrente") + "'";
        //// //System.out.println (sql);
        executasql.executarUpdate (sql);

      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_data_lote)");
    }
  }

  private void baca_acerta_valor_juros_multa (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_valor_juros_multa");
    ResultSet resComp = null;

    try {
      sql = " SELECT oid_mov_compromisso, Lotes_Compromissos.vl_multa_pagamento, Lotes_Compromissos.vl_juros_pagamento , Lotes_Compromissos.vl_outras_despesas, Lotes_Compromissos.vl_desconto" +
          " FROM   movimentos_compromissos, Lotes_Compromissos " +
          " WHERE movimentos_compromissos.oid_Compromisso = Lotes_Compromissos.oid_Compromisso " +
          " AND   movimentos_compromissos.oid_lote_pagamento = Lotes_Compromissos.oid_lote_pagamento " +
          " AND   movimentos_compromissos.dt_pagamento > '01/01/2006' ";
      resComp = this.executasql.executarConsulta (sql.toString ());
      while (resComp.next ()) {
        sql = " UPDATE movimentos_compromissos SET " +
            "  vl_multa_pagamento = " + resComp.getDouble ("vl_multa_pagamento") +
            " ,vl_juros_pagamento = " + resComp.getDouble ("vl_juros_pagamento") +
            " ,vl_outras_despesas = " + resComp.getDouble ("vl_outras_despesas") +
            " ,vl_desconto = " + resComp.getDouble ("vl_desconto") +
            " WHERE movimentos_compromissos.oid_mov_compromisso = " + resComp.getLong ("oid_mov_compromisso");
        //// //System.out.println (sql);
        executasql.executarUpdate (sql);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "baca_acerta_valor_juros_multa)");
    }
  }

  private void baca_acerta_Movimento_Estoque (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_Movimento_Estoque");

    try {
      ResultSet res = null;
      sql = "  SELECT Movimentos_Estoques.oid_Movimento_estoque " +
          "  FROM  Movimentos_Ordens_Servicos, Movimentos_Estoques " +
          "  WHERE Movimentos_Ordens_Servicos.oid_Ordem_Servico =  Movimentos_Estoques.oid_Ordem_Servico " +
          "  GROUP BY  Movimentos_Estoques.oid_Movimento_estoque";

      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        sql = " UPDATE Notas_Fiscais SET DT_Emissao='" + res.getString ("dt_Emissao") + "', DT_Entrada='" + res.getString ("dt_Emissao") + "' WHERE oid_Nota_Fiscal='" + res.getString ("oid_Nota_Fiscal") + "'";
        executasql.executarUpdate (sql);

        //// //System.out.println (sql);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_peso_nf)");
    }
  }

  private void baca_Inclui_Cliente (String acesso) throws Exception {

    String sql = null;
    //// //System.out.println ("baca_Inclui_Cliente");

    //instanciar clietne com cnpj 01588286000130
    try {

      ResultSet res = null;
      ResultSet res2 = null;
      sql = "  SELECT oid_Pessoa FROM Pessoas ";

      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        //// //System.out.println ("lendo pessoa->>" + res.getString ("oid_Pessoa"));

        //ver se este oid_pessoa ja nao esta na tabela de clientes
        sql = "  SELECT oid_Cliente FROM Clientes WHERE oid_Cliente ='" + res.getString ("oid_Pessoa") + "'";

        //// //System.out.println ("lendo sql->>" + sql);

        res2 = this.executasql.executarConsulta (sql);
        if (!res2.next ()) {
          //// //System.out.println ("nao tem pessoa->>");
          ClienteBean Cliente = new ClienteBean ();
          Cliente = ClienteBean.getByOID_Cliente ("01588286000130");

          //// //System.out.println ("ok ");

          // alterar o ed para o oid lido
          Cliente.setOID_Pessoa (res.getString ("oid_Pessoa"));
          Cliente.setOID_Cliente (res.getString ("oid_Pessoa"));

          // incluir o novo cliente
          Cliente.insert ();
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_peso_nf)");
    }
  }

  private void baca_CD_Fornecedor (String acesso) throws Exception {

    String sql = null;
    //// //System.out.println ("baca_CD_Fornecedor");

    try {

      ResultSet res = null;
      sql = "  SELECT oid_fornecedor, CD_Fornecedor FROM Fornecedores ";
      int cd_fornec = 0;
      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        cd_fornec++;

        //// //System.out.println ("lendo oid_fornecedor->>" + res.getString ("oid_fornecedor") + "     " + cd_fornec);

        if (res.getString ("CD_Fornecedor") == null || res.getString ("CD_Fornecedor").equals ("") || res.getString ("CD_Fornecedor").equals ("null")) {
          sql = "UPDATE Fornecedores SET CD_Fornecedor='" + cd_fornec + "' WHERE oid_Fornecedor='" + res.getString ("oid_fornecedor") + "'";

          //// //System.out.println (sql);

          executasql.executarUpdate (sql);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_CD_Fornecedor)");
    }
  }

  private void baca_acerta_CEP (String acesso) throws Excecoes {

    String sql = null;
    //// //System.out.println ("baca_acerta_CEP 99999---00");
    long count = 0 , cc = 0;
    try {
      ResultSet res = null;
      sql = " SELECT *, Temp_CEP.oid FROM Temp_CEP  WHERE 1=1 ";

      //// //System.out.println (sql);
      res = this.executasql.executarConsulta (sql);

      while (res.next () && count < 1000) {
        count++;
        cc++;
        ////// //System.out.println (count);


        String uf = SeparaEndereco.corrigeString (res.getString ("uf").toUpperCase ().trim ());
        String tipo = SeparaEndereco.corrigeString (res.getString ("tipo").toUpperCase ().trim ());
        String logradouro = SeparaEndereco.corrigeString (res.getString ("logradouro").toUpperCase ().trim ());
        String bairro = SeparaEndereco.corrigeString (res.getString ("bairro").toUpperCase ().trim ());
        String localidade = SeparaEndereco.corrigeString (res.getString ("localidade").toUpperCase ().trim ());

        ////// //System.out.println ("OK");

        sql = " UPDATE Temp_CEP SET uf='" + uf.trim () + "'" +
            " ,tipo = '" + tipo.trim () + "'" +
            " ,logradouro = '" + logradouro.trim () + "'" +
            " ,bairro = '" + bairro.trim () + "'" +
            " ,localidade = '" + localidade.trim () + "'" +
            " WHERE cep='" + res.getString ("cep") + "'";
        if (count > 500) {
          //// //System.out.println ("LENDO CEP : " + cc);
          count = 0;
        }
        executasql.executarUpdate (sql);

      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "baca_acerta_CEP)");
    }
  }

  public void importaArquivo (String pasta , String padrao) throws Exception {

    //// //System.out.println ("importaArquivo passo 1->>");
    int qt_files = 0;

    /// fazer select pessoas_edi e achar as pastas e padroes;

    //// //System.out.println ("importaArquivo pasta->>" + pasta);
    //// //System.out.println (" importaArquivo padrao->>" + padrao);

    if (!util.doValida (pasta)) {
      //// //System.out.println (" diretorio: [" + pasta + "] Inv�lidos!");
      return;
    }

    //*** Filtro para Arquivos de importa��o
     FilenameFilter filter = new FilenameFilter () {
       public boolean accept (File dir , String name) {

         //// //System.out.println (" filter name->>" + name);

         return name.startsWith ("");
       }
     };

    //*** Valida��es
     try {
       if (util.doValida (pasta)) {
         File dir = new File (pasta);
         if (dir.exists ()) {
           if (dir.isDirectory ()) {
             File[] files = dir.listFiles (filter);
             if (files != null) {
               for (int i = 0; i < files.length; i++) {
                 // Pega o nome do arquivo
                 String arquivo = files[i].getAbsolutePath ();

                 //// //System.out.println (" filename->>" + arquivo);
                 qt_files++;
                 inicioTransacao ();
                 if ("importa_Pessoa_LayOut1".equals (padrao)) {
                   importa_Pessoa_LayOut1 (arquivo);
                 }
                 fimTransacao (true);
               }
             }
           }
         }
         else {
           throw new Mensagens ("Arquivo: " + dir.getAbsolutePath () + " n�o encontrado!");
         }
       }
       else {
         throw new Mensagens ("Arquivo n�o informado!");
       }

       this.fimTransacao (true);
     }
     catch (Excecoes e) {
       this.abortaTransacao ();
       throw e;
     }
     catch (RuntimeException e) {
       this.abortaTransacao ();
       throw e;
     }
  }

  private void importaPessoa_CSV_1 (String arquivo) throws Excecoes {

	    String NM_Registro = "";
	    //// //System.out.println ("importaPessoaCSV -->> " + arquivo);

	    String nr_cep = "";
	    String cnpj_cpf = "", nome="", endereco="", bairro="", cep="", cidade="", uf="";
	    int linha=0;

	    try {
	      ManipulaArquivo man = new ManipulaArquivo ("");
	      LineNumberReader line = man.leLinha (arquivo);


	      if (line.ready ()) {

	        //// //System.out.println ("Line Ready");

	        while ( (NM_Registro = line.readLine ()) != null) {

	          ////// //System.out.println ("Line ->>" + linha + " - " + NM_Registro);

	          if (NM_Registro != null && NM_Registro.length () > 10) {

		            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
		            NM_Registro = NM_Registro.toUpperCase ();

		            cnpj_cpf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 1, ";");
		            nome=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 2, ";");
		            endereco=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 3, ";");
		            cidade=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 4, ";");
		            uf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 5, ";");


		            // //System.out.println ("cnpj_cpf ------->>" + cnpj_cpf);
	                // //System.out.println ("nome -------->>" + nome);
	                // //System.out.println ("endereco -------->>" + endereco);
	                // //System.out.println ("cidade -------->>" + cidade);
	                // //System.out.println ("uf -------->>" + uf);

	                confirma_Pessoa(cnpj_cpf, "", nome, "", endereco, cep, bairro, cidade, uf, "", "");

	          }
	          linha++;
	          line.setLineNumber (linha);
	        }
	      }
	      line.close ();
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("importaPessoaCSV");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  private void importaPessoa_CSV_2 (String arquivo) throws Excecoes {

	    String NM_Registro = "";
	    // //System.out.println ("importaPessoaCSV 2  xxxxx-->> " + arquivo);


	    String cnpj_cpf = "", insc_est="", nome="", endereco="", bairro="", cep="", cidade="", uf="", telefone="", email="";
	    int linha=0;

	    try {
	      ManipulaArquivo man = new ManipulaArquivo ("");
	      LineNumberReader line = man.leLinha (arquivo);


	      if (line.ready ()) {

	        //// //System.out.println ("Line Ready");

	        while ( (NM_Registro = line.readLine ()) != null) {

	          ////// //System.out.println ("Line ->>" + linha + " - " + NM_Registro);

	          if (NM_Registro != null && NM_Registro.length () > 10) {

		            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
		            NM_Registro = NM_Registro.toUpperCase ();

		            cnpj_cpf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 1, ";");
		            nome=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 2, ";");
		            insc_est=SeparaEndereco.corrigeNumero(ManipulaString.buscaSegmentoDelimitado(NM_Registro, 4, ";"));
		            endereco=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 5, ";");
		            bairro=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 6, ";");
		            cidade=ManipulaString.buscaSegmentoDelimitado(NM_Registro,7 , ";");
		            uf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 8, ";");
		            cep=SeparaEndereco.corrigeNumero(ManipulaString.buscaSegmentoDelimitado(NM_Registro, 9, ";"));

		            telefone=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 11, ";");
		            email=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 12, ";");

		            // //System.out.println ("cnpj_cpf ------->>" + cnpj_cpf);
	                // //System.out.println ("insc_est -------->>" + insc_est);
	                // //System.out.println ("nome -------->>" + nome);
	                // //System.out.println ("endereco -------->>" + endereco);
	                // //System.out.println ("bairro -------->>" + bairro);
	                // //System.out.println ("cidade -------->>" + cidade);
	                // //System.out.println ("uf -------->>" + uf);
	                // //System.out.println ("cep -------->>" + cep);
	                // //System.out.println ("telefone -------->>" + telefone);
	                // //System.out.println ("email -------->>" + email);

	                confirma_Pessoa(cnpj_cpf, insc_est, nome, "", endereco, cep, bairro, cidade, uf, telefone, email);

	          }
	          linha++;
	          line.setLineNumber (linha);
	        }
	      }
	      line.close ();
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("importaPessoaCSV");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  private void importaPessoa_CSV_3 (String arquivo) throws Excecoes {

	    String NM_Registro = "";
	    // //System.out.println ("importaPessoa_CSV_3 -->> " + arquivo);


	    String cnpj_cpf = "", cpf="", insc_est="", nome="", endereco="", bairro="", cep="", cidade="", uf="", telefone="", email="";
	    int linha=0;

	    try {
	      ManipulaArquivo man = new ManipulaArquivo ("");
	      LineNumberReader line = man.leLinha (arquivo);


	      if (line.ready ()) {

	        //// //System.out.println ("Line Ready");

	        while ( (NM_Registro = line.readLine ()) != null) {

	          //// //System.out.println ("Line ->>" + linha + " - " + NM_Registro);

	          if (NM_Registro != null && NM_Registro.length () > 10) {

		            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
		            NM_Registro = NM_Registro.toUpperCase ();

		            cnpj_cpf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 2, ";");
		            cpf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 3, ";");

		            if (cnpj_cpf ==null || cnpj_cpf.length()<5) {
		            	cnpj_cpf=cpf;
		            }


		            nome=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 4, ";");
		            insc_est=SeparaEndereco.corrigeNumero(ManipulaString.buscaSegmentoDelimitado(NM_Registro, 5, ";"));
		            endereco=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 7, ";");
		            bairro=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 8, ";");
		            cidade=ManipulaString.buscaSegmentoDelimitado(NM_Registro,9 , ";");
		            uf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 10, ";");
		            cep=SeparaEndereco.corrigeNumero(ManipulaString.buscaSegmentoDelimitado(NM_Registro, 11, ";"));

		            telefone=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 12, ";");
		            email=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 13, ";");

		            // //System.out.println ("cnpj_cpf ------->>" + cnpj_cpf);
	                // //System.out.println ("insc_est -------->>" + insc_est);
	                // //System.out.println ("nome -------->>" + nome);
	                // //System.out.println ("endereco -------->>" + endereco);
	                // //System.out.println ("bairro -------->>" + bairro);
	                // //System.out.println ("cidade -------->>" + cidade);
	                // //System.out.println ("uf -------->>" + uf);
	                // //System.out.println ("cep -------->>" + cep);
	                // //System.out.println ("telefone -------->>" + telefone);
	                // //System.out.println ("email -------->>" + email);

	                confirma_Pessoa(cnpj_cpf, insc_est, nome, "", endereco, cep, bairro, cidade, uf, telefone, email);

	          }
	          linha++;
	          line.setLineNumber (linha);
	        }
	      }
	      line.close ();
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("importaPessoaCSV");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }


  private void importaPessoa_CSV_4 (String arquivo) throws Excecoes {

	    String NM_Registro = "";
	     //System.out.println ("importaPessoa_CSV_4 -->> " + arquivo);


	    String cnpj_cpf = "", cpf="", insc_est="", nome="", fantasia="", endereco="", bairro="", cep="", cidade="", uf="", telefone="", email="";
	    int linha=0;

	    try {
	      ManipulaArquivo man = new ManipulaArquivo ("");
	      LineNumberReader line = man.leLinha (arquivo);


	      if (line.ready ()) {

	        //// //System.out.println ("Line Ready");

	        while ( (NM_Registro = line.readLine ()) != null) {

	          //// //System.out.println ("Line ->>" + linha + " - " + NM_Registro);

	          if (NM_Registro != null && NM_Registro.length () > 10) {

		            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
		            NM_Registro = NM_Registro.toUpperCase ();

		            cnpj_cpf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 1, ";");
		            nome=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 2, ";");
		            fantasia=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 3, ";");
		            endereco=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 4, ";");
		            bairro=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 5, ";");
		            cidade=ManipulaString.buscaSegmentoDelimitado(NM_Registro,6 , ";");
		            uf=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 7, ";");
		            cep=SeparaEndereco.corrigeNumero(ManipulaString.buscaSegmentoDelimitado(NM_Registro, 8, ";"));
		            insc_est=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 9, ";");
		            telefone=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 10, ";")+"-"+ManipulaString.buscaSegmentoDelimitado(NM_Registro, 12, ";");

		            //email=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 13, ";");

		            // //System.out.println ("cnpj_cpf ------->>" + cnpj_cpf);
	                // //System.out.println ("insc_est -------->>" + insc_est);
	                // //System.out.println ("nome -------->>" + nome);
	                // //System.out.println ("endereco -------->>" + endereco);
	                // //System.out.println ("bairro -------->>" + bairro);
	                // //System.out.println ("cidade -------->>" + cidade);
	                // //System.out.println ("uf -------->>" + uf);
	                // //System.out.println ("cep -------->>" + cep);
	                // //System.out.println ("telefone -------->>" + telefone);
	                // //System.out.println ("email -------->>" + email);

	                confirma_Pessoa(cnpj_cpf, insc_est, nome, fantasia, endereco, cep, bairro, cidade, uf, telefone, email);

	          }
	          linha++;
	          line.setLineNumber (linha);
	        }
	      }
	      line.close ();
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("importaPessoaCSV");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }


  private void importaCEP_Pessoas (String arquivo) throws Excecoes {

	    String NM_Registro = "";
	    //// //System.out.println ("importaCEP_Pessoas -->> " + arquivo);

	    String nr_cep = "";
	    String NR_CNPJ_CPF = "";
	    int linha=0;

	    try {
	      ManipulaArquivo man = new ManipulaArquivo ("");
	      LineNumberReader line = man.leLinha (arquivo);


	      if (line.ready ()) {

	        //// //System.out.println ("Line Ready");

	        while ( (NM_Registro = line.readLine ()) != null) {

	          ////// //System.out.println ("Line ->>" + linha + " - " + NM_Registro);

	          if (NM_Registro != null && NM_Registro.length () > 10) {

		            NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "       ";
		            NM_Registro = NM_Registro.toUpperCase ();

		            NR_CNPJ_CPF=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 12, ";");
		            nr_cep=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 21, ";");

		            //// //System.out.println ("NR_CNPJ_CPF ------->>" + NR_CNPJ_CPF);
	                //// //System.out.println ("nr_cep -------->>" + nr_cep);

	                String sql = " SELECT oid_Pessoa FROM Pessoas  WHERE oid_Pessoa = '" + NR_CNPJ_CPF  +  "'";

	                ResultSet rs = this.executasql.executarConsulta (sql);

	                if (rs.next ()) {
		                sql = " UPDATE Pessoas SET  NR_CEP='" +nr_cep + "'  WHERE oid_Pessoa = '" + NR_CNPJ_CPF  +  "'";
		                ////// //System.out.println ("sql -------->>" + sql);
		                executasql.executarUpdate (sql);

	                }

	          }
	          linha++;
	          line.setLineNumber (linha);
	        }
	      }
	      line.close ();
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("importa_Remessa");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }


  private void importa_Pessoa_LayOut1 (String arquivo) throws Excecoes {

    String NM_Registro = "";

    //// //System.out.println (" importa_Pessoa_LayOut1->>" + arquivo);
    String nm_registro1;
    String nm_registro2;
    String nm_registro3;
    String nm_registro4;
    String nm_registro5;
    String nm_registro6;
    PessoaBD = new PessoaBD (this.sql);

    int linha = 0;

    try {
      ManipulaArquivo man = new ManipulaArquivo ("");
      LineNumberReader line = man.leLinha (arquivo);

      if (line.ready ()) {

        while ( (NM_Registro = line.readLine ()) != null) {

          NM_Registro = SeparaEndereco.corrigeString (NM_Registro) + "                                                             ".toUpperCase ();

          linha++;
          line.setLineNumber (linha);
          nm_registro2 = line.readLine ();
          linha++;
          line.setLineNumber (linha);
          nm_registro3 = line.readLine ();
          linha++;
          line.setLineNumber (linha);
          nm_registro4 = line.readLine ();

          nm_registro2 = SeparaEndereco.corrigeString (nm_registro2) + "                                                             ".toUpperCase ();
          nm_registro3 = SeparaEndereco.corrigeString (nm_registro3) + "                                                             ".toUpperCase ();
          nm_registro4 = SeparaEndereco.corrigeString (nm_registro4) + "                                                             ".toUpperCase ();

          //// //System.out.println (" lendo linha 1->>" + linha + " -> " + NM_Registro);
          //// //System.out.println (" lendo linha 2->>" + linha + " -> " + nm_registro2);
          //// //System.out.println (" lendo linha 3->>" + linha + " -> " + nm_registro3);
          //// //System.out.println (" lendo linha 4->>" + linha + " -> " + nm_registro4);

          EDI_ImportacaoED importaED = new EDI_ImportacaoED ();
          importaED.setNR_CNPJ_CPF_Remetente (SeparaEndereco.corrigeNumero (nm_registro4.substring (0 , 20).trim ()));

          //// //System.out.println (" lendo setNR_CNPJ_CPF_Remetente ->>" + importaED.getNR_CNPJ_CPF_Remetente ());

          importaED.setNM_Razao_Social_Remetente (nm_registro2.substring (0 , 40).trim ());
          //// //System.out.println (" lendo setNM_Razao_Social_Remetente ->>" + importaED.getNM_Razao_Social_Remetente ());

          if ("ZELIA".equals (importaED.getNM_Razao_Social_Remetente ().substring (0 , 5))) {
            break;
          }

          importaED.setNM_Endereco_Remetente (nm_registro3.substring (0 , 44).trim ());
          //// //System.out.println (" lendo setNM_Endereco_Remetente ->>" + importaED.getNM_Endereco_Remetente ());

          importaED.setNM_Cidade_Remetente (nm_registro2.substring (53 , 85).trim ());
          //// //System.out.println (" lendo setNM_Cidade_Remetente ->>" + importaED.getNM_Cidade_Remetente ());

          importaED.setCD_Estado_Remetente (nm_registro2.substring (85 , 88).trim ());
          //// //System.out.println (" lendo setCD_Estado_Remetente ->>" + importaED.getCD_Estado_Remetente ());

          //importaED.setNR_CEP_Remetente (NM_Registro.substring (188 , 218).trim ());
          importaED.setNM_INSCRICAO_Remetente (SeparaEndereco.corrigeNumero (nm_registro4.substring (32 , 45).trim ()));
          //// //System.out.println (" lendo setNM_INSCRICAO_Remetente ->>" + importaED.getNM_INSCRICAO_Remetente ());

          //importaED.setNM_Fantasia_Remetente (NM_Registro.substring (249 , 269).trim ());
          importaED.setNM_Bairro_Remetente (nm_registro3.substring (55 , 75).trim ());
          //// //System.out.println (" lendo setNM_Bairro_Remetente ->>" + importaED.getNM_Bairro_Remetente ());

          confirma_Pessoa (importaED);
          if (linha > 700) {
            break;
          }
        }
      }
      line.close ();
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  private boolean confirma_Pessoa (EDI_ImportacaoED ed) throws Excecoes {

    //// //System.out.println ("Passei EDI confirma_Pessoa 1");

    String cnpj_cpf = "";
    String cidade = "" , uf = "" , endereco = "" , cep = "" , nome = "" , insc_est = "";
    boolean pessoa_ok = false;

    try {
      cnpj_cpf = ed.getNR_CNPJ_CPF_Remetente ();
      nome = ed.getNM_Razao_Social_Remetente ();
      endereco = ed.getNM_Endereco_Remetente ();
      cep = ed.getNR_CEP_Remetente ();
      insc_est = ed.getNM_INSCRICAO_Remetente ();
      cidade = ed.getNM_Cidade_Remetente ();
      uf = ed.getCD_Estado_Remetente ();
      if (cnpj_cpf != null && cnpj_cpf.length () > 4) {

        pessoaED.setNR_CNPJ_CPF (cnpj_cpf);
        pessoaED = new PessoaBD (this.executasql).getByRecord (pessoaED);

        if (pessoaED.getOid_Pessoa () != null && cnpj_cpf.equals (pessoaED.getNR_CNPJ_CPF ())) {
          pessoa_ok = true;
        }
        else {
          cidadeBean = CidadeBean.getByCidade (cidade , uf);
          if (cidadeBean.getOID () > 0) {
            pessoaED.setNR_CNPJ_CPF (cnpj_cpf);
            pessoaED.setNM_Razao_Social (nome);
            pessoaED.setNM_Endereco (endereco);
            pessoaED.setNM_Inscricao_Estadual (insc_est);
            pessoaED.setNR_CEP (cep);
            pessoaED.setOid_Cidade (cidadeBean.getOID ());

            //// //System.out.println ("Passei EDI confirma_Pessoa 2");

            pessoa_ok = new PessoaBD (this.executasql).inclui (pessoaED);
            //// //System.out.println ("Passei EDI confirma_Pessoa 3");

          }
        }
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return pessoa_ok;
  }

  private boolean confirma_Pessoa (String cnpj_cpf, String insc_est, String nome, String fantasia, String endereco, String cep, String bairro, String cidade, String uf, String telefone, String email) throws Excecoes {

	    // //System.out.println ("Passei EDI confirma_Pessoa==========================");

	    boolean pessoa_ok = false;
	    PessoaED pessoaED = new PessoaED ();

	    try {
	      if (cnpj_cpf != null && cnpj_cpf.length () > 10) {
	        pessoaED.setNR_CNPJ_CPF (cnpj_cpf);
	        pessoaED = new PessoaBD (this.executasql).getByRecord (pessoaED);
	        if ("ISENTO".equals(insc_est)){
	        	insc_est="0";
	        }else {
	        	insc_est=SeparaEndereco.corrigeNumero(insc_est);
	        }
	        if (pessoaED.getOid_Cidade()>0) {
	          pessoa_ok = true;
	          pessoaED.setNM_Razao_Social (nome+" ");
	          pessoaED.setNM_Fantasia (fantasia+" ");
	          pessoaED.setNM_Endereco (endereco+"  ");
	          pessoaED.setNM_Bairro (bairro+"  ");
	          pessoaED.setNM_Inscricao_Estadual (insc_est+"  ");
	          pessoaED.setNR_CEP (cep+"  ");
	          pessoaED.setDt_stamp(Data.getDataDMY());
	          pessoaED.setUsuario_Stamp(" ");
	          pessoaED.setDm_Stamp(" ");
	          pessoaED.setNR_Telefone (telefone+" ");
	          pessoaED.setEMail(email+" ");
	          pessoaED.setNM_Bairro (bairro+" ");


              ////System.out.println ("ALTERA -->>" + cnpj_cpf);

		      new PessoaBD (this.executasql).altera(pessoaED);
	        }
	        else {
	        cidadeBean = CidadeBean.getByCidade (cidade , uf);
	          if (cidadeBean.getOID () > 0) {
	            ////System.out.println ("INCLUI ----->>" + email);
	            pessoaED.setNR_CNPJ_CPF (cnpj_cpf);
	            pessoaED.setNM_Razao_Social (nome);
	            pessoaED.setNM_Endereco (endereco);
	            pessoaED.setNM_Bairro (bairro);
	            pessoaED.setNM_Inscricao_Estadual (insc_est);
	            pessoaED.setNR_CEP (cep);
	            pessoaED.setNR_Telefone (telefone);
	            pessoaED.setEMail(email);
	            pessoaED.setNM_Bairro (bairro);
	            pessoaED.setOid_Cidade (cidadeBean.getOID ());

	            // //System.out.println ("Passei EDI confirma_Pessoa --------------cep="+cep);
	            // //System.out.println ("Passei EDI confirma_Pessoa --------------bairro="+bairro);
	            // //System.out.println ("Passei EDI confirma_Pessoa --------------insc_est="+insc_est);

	            pessoa_ok = new PessoaBD (this.executasql).inclui (pessoaED);
	            if (pessoa_ok) {
	            	// //System.out.println ("Pessoa OK");
	            }else {
	            	// //System.out.println ("deu merda");
	            }

	          }
	        }
	      }
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao selecionar");
	      excecoes.setMetodo ("selecionar");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return pessoa_ok;
	  }

  private void bacas_calculaSeguro () throws Excecoes {
    try {
      double pe_aliquota_rctrc = 0;
      double pe_aliquota_rctr_vi = 0;
      double pe_aliquota_rctr_dc = 0;
      double pe_aliquota_rcta = 0;
      double VL_Seguro = 0;
      ResultSet rs = null;
      ResultSet rsCto = null;
      ResultSet rsMov = null;
      String sql = "";
      int calc_seg = 0;

      //// //System.out.println ("bacas_calculaSeguro 1");

      Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();
      Calcula_FreteED ed = new Calcula_FreteED ();

      sql = " SELECT oid_Conhecimento, dt_emissao FROM Conhecimentos " +
          " WHERE Conhecimentos.dt_Emissao >='12/03/2007' " +
          //" AND   Conhecimentos.NR_Conhecimento=120527" +
          " AND   Conhecimentos.vl_Nota_Fiscal> 0 " +
          " AND   Conhecimentos.vl_custo_seguro=0 ";

      rsCto = this.executasql.executarConsulta (sql);
      while (rsCto.next ()) {

        //// //System.out.println ("bacas_calculaSeguro cto->" + rsCto.getString ("oid_Conhecimento") + rsCto.getString ("dt_emissao"));

        this.inicioTransacao ();
        this.executasql = this.sql;

        ConhecimentoED conhecimentoED = new ConhecimentoED ();
        conhecimentoED.setOID_Conhecimento (rsCto.getString ("oid_Conhecimento"));
        ConhecimentoBD = new ConhecimentoBD (this.sql);

        //// //System.out.println ("bacas_calculaSeguro cto2->" + rsCto.getString ("oid_Conhecimento"));

        conhecimentoED = ConhecimentoBD.getByRecord (conhecimentoED);
        ed = this.carregaParametros (conhecimentoED);

        //// //System.out.println ("TAXAS: getDM_Isencao_Seguro=" + ed.getDM_Isencao_Seguro ());

        if (!"S".equals (ed.getDM_Isencao_Seguro ())) {
          sql = " SELECT * FROM Taxas ";
          sql += " WHERE Taxas.OID_Estado = " + ed.getOID_Estado_Origem ();
          sql += " AND   Taxas.OID_Estado_Destino = " + ed.getOID_Estado_Destino ();
          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            pe_aliquota_rctrc = rs.getDouble ("pe_aliquota_rctrc");
            pe_aliquota_rctr_vi = rs.getDouble ("pe_aliquota_rctr_vi");
            pe_aliquota_rctr_dc = rs.getDouble ("pe_aliquota_rctr_dc");
            pe_aliquota_rcta = rs.getDouble ("pe_aliquota_rcta");
          }
          //// //System.out.println ("TAXAS: pe_aliquota_rctrc=" + pe_aliquota_rctrc + " pe_aliquota_rctr_dc=" + pe_aliquota_rctr_dc + " pe_aliquota_rctr_vi=" + pe_aliquota_rctr_vi + " pe_aliquota_rcta=" + pe_aliquota_rcta);
          //// //System.out.println ("Cliente: ed.getDM_rctrc()=" + ed.getDM_rctrc () + " ed.getDM_rctr_dc()=" + ed.getDM_rctr_dc () + " ed.getDM_rctr_vi()=" + ed.getDM_rctr_vi () + " ed.getDM_rcta()=" + ed.getDM_rcta ());

          if ("N".equals (ed.getDM_rcta ())) {
            pe_aliquota_rcta = 0;
          }
          if ("N".equals (ed.getDM_rctr_dc ())) {
            pe_aliquota_rctr_dc = 0;
          }
          if ("N".equals (ed.getDM_rctr_vi ())) {
            pe_aliquota_rctr_vi = 0;
          }
          if ("N".equals (ed.getDM_rctrc ())) {
            pe_aliquota_rctrc = 0;
          }
          if ("A".equals (ed.getDM_Tipo_Transporte ())) {
            if (pe_aliquota_rcta > 0) {
              VL_Seguro = ( (ed.getVL_Nota_Fiscal () * pe_aliquota_rcta) / 100);
              Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
              edMovimento_Conhecimento.setOID_Tipo_Movimento (85); //rca
              edMovimento_Conhecimento.setVL_Movimento (Valor.round (VL_Seguro , 2));
              edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
              edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
              edMovimento_Conhecimento.setOID_Conhecimento (ed.getOID_Conhecimento ());
              edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
              edMovimento_Conhecimento.setDM_Lancado_Gerado ("G");
              edMovimento_Conhecimento.setOID_Tabela_Frete ("");
              edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");
              sql = " SELECT oid FROM Movimentos_Conhecimentos " +
                  " WHERE  Movimentos_Conhecimentos.oid_Conhecimento ='" + edMovimento_Conhecimento.getOID_Conhecimento () + "'" +
                  " AND    Movimentos_Conhecimentos.oid_Tipo_Movimento ='" + edMovimento_Conhecimento.getOID_Tipo_Movimento () + "'";
              //// //System.out.println ("inclui seg rca");

              rsMov = this.executasql.executarConsulta (sql);
              if (!rsMov.next ()) {
                Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.executasql);
                Movimento_ConhecimentoBD.inclui (edMovimento_Conhecimento);
                calc_seg++;
              }
            }
          }
          else {
            if (pe_aliquota_rctr_dc > 0) {
              VL_Seguro = ( (ed.getVL_Nota_Fiscal () * pe_aliquota_rctr_dc) / 100);
              Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
              edMovimento_Conhecimento.setOID_Tipo_Movimento (84); //rcf
              edMovimento_Conhecimento.setVL_Movimento (Valor.round (VL_Seguro , 2));
              edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
              edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
              edMovimento_Conhecimento.setOID_Conhecimento (ed.getOID_Conhecimento ());
              edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
              edMovimento_Conhecimento.setDM_Lancado_Gerado ("G");
              edMovimento_Conhecimento.setOID_Tabela_Frete ("");
              edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");

              sql = " SELECT oid FROM Movimentos_Conhecimentos " +
                  " WHERE  Movimentos_Conhecimentos.oid_Conhecimento ='" + edMovimento_Conhecimento.getOID_Conhecimento () + "'" +
                  " AND    Movimentos_Conhecimentos.oid_Tipo_Movimento ='" + edMovimento_Conhecimento.getOID_Tipo_Movimento () + "'";
              rsMov = this.executasql.executarConsulta (sql);
              if (!rsMov.next ()) {
                //// //System.out.println ("inclui seg rcf");

                Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.executasql);
                Movimento_ConhecimentoBD.inclui (edMovimento_Conhecimento);
                calc_seg++;
              }
            }
            if (pe_aliquota_rctrc > 0) {
              Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
              edMovimento_Conhecimento.setOID_Tipo_Movimento (11); //SEG
              edMovimento_Conhecimento.setVL_Movimento (Valor.round (VL_Seguro , 2));
              edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
              edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
              edMovimento_Conhecimento.setOID_Conhecimento (ed.getOID_Conhecimento ());
              edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
              edMovimento_Conhecimento.setDM_Lancado_Gerado ("G");
              edMovimento_Conhecimento.setOID_Tabela_Frete ("");
              edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");

              sql = " SELECT oid FROM Movimentos_Conhecimentos " +
                  " WHERE  Movimentos_Conhecimentos.oid_Conhecimento ='" + edMovimento_Conhecimento.getOID_Conhecimento () + "'" +
                  " AND    Movimentos_Conhecimentos.oid_Tipo_Movimento ='" + edMovimento_Conhecimento.getOID_Tipo_Movimento () + "'";
              rsMov = this.executasql.executarConsulta (sql);
              if (!rsMov.next ()) {
                //// //System.out.println ("inclui seg ");

                Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.executasql);
                Movimento_ConhecimentoBD.inclui (edMovimento_Conhecimento);
                calc_seg++;
              }
            }
          }
          if (calc_seg > 0) {
            //// //System.out.println ("inclui calcula_Margem");
            Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
            edMovimento_Conhecimento.setOID_Conhecimento (rsCto.getString ("oid_Conhecimento"));
            movimento_ConhecimentoED = Movimento_ConhecimentoBD.calcula_Margem (movimento_ConhecimentoED);
          }
        }
        this.fimTransacao (true);
      }
    }

    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , getClass ().getName () , "calculaSeguro");
    }
  }

  private void bacas_acertaMovimentoCto () throws Excecoes {
	    try {
	      double VL_Movimento = 0;
	      long oid_Tipo_Movimento =0;
	      String oid_Conhecimento="";
	      ResultSet rs = null;
	      ResultSet rsCto = null;
	      ResultSet rsMov = null;
	      String sql = "";
	      int calc = 0;

	      //// //System.out.println ("bacas_acertaMovimentoConhecimento ===");

	      Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();
	      Calcula_FreteED ed = new Calcula_FreteED ();

	      sql = " SELECT oid_Movimento_Conhecimento, oid_Conhecimento, oid_Tipo_Movimento, dt_movimento_conhecimento FROM Movimentos_Conhecimentos " +
	          " WHERE Movimentos_Conhecimentos.dt_movimento_conhecimento >='01/01/2008' " +
	          //" AND   Movimentos_Conhecimentos.oid_Conhecimento='2980566CN2' " +
	          " AND   Movimentos_Conhecimentos.DM_Tipo_Movimento ='D' " +
	      	  " ORDER BY oid_Conhecimento, oid_Tipo_Movimento , oid_Lote_Fornecedor DESC";
          //// //System.out.println (sql);

	      rsCto = this.executasql.executarConsulta (sql);
	      while (rsCto.next ()) {

	    	if (oid_Conhecimento.equals(rsCto.getString("oid_Conhecimento")) && oid_Tipo_Movimento==rsCto.getLong("oid_Tipo_Movimento")){
	    		calc++;
	    		//// //System.out.println ("Exclui MOV 1");
	    	     Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
			     Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
			     edMovimento_Conhecimento.setOID_Movimento_Conhecimento (rsCto.getString ("oid_Movimento_Conhecimento"));
			     edMovimento_Conhecimento.setOID_Conhecimento (rsCto.getString ("oid_Conhecimento"));
	    	     Movimento_Conhecimentorn.deleta (edMovimento_Conhecimento);

	    	}
	    	oid_Conhecimento=rsCto.getString("oid_Conhecimento");
	    	oid_Tipo_Movimento=rsCto.getLong("oid_Tipo_Movimento");

	    	//// //System.out.println ("bacas_acertaMovimentoConhecimento cto->" + rsCto.getString ("oid_Conhecimento") + rsCto.getString ("dt_movimento_conhecimento") +"  CACLC=" + calc);

	      }
	    }

	    catch (SQLException exc) {
	      throw new Excecoes (exc.getMessage () , exc , getClass ().getName () , "bacas_acertaMovimentoConhecimento");
	    }
	  }

  protected Calcula_FreteED carregaParametros (ConhecimentoED edConhec) throws Excecoes {
    ResultSet rs = null;
    String sql = "";

    Calcula_FreteED ed = new Calcula_FreteED ();
    try {
      ed.setDM_Responsavel_Cobranca (edConhec.getDM_Responsavel_Cobranca ());
      ed.setDM_Tipo_Pagamento (edConhec.getDM_Tipo_Pagamento ());
      ed.setDM_Tipo_Desconto_Pedagio (edConhec.getDM_Tipo_Desconto_Pedagio ());
      ed.setDM_Tipo_Conhecimento ("1");
      ed.setDM_Tipo_Documento (edConhec.getDM_Tipo_Documento ());
      if (edConhec.getDM_Tipo_Conhecimento () != null && !edConhec.getDM_Tipo_Conhecimento ().equals ("")) {
        ed.setDM_Tipo_Conhecimento (edConhec.getDM_Tipo_Conhecimento ());
      }

      ed.setDT_Emissao (edConhec.getDT_Emissao ());
      ed.setDT_Previsao_Entrega (edConhec.getDT_Previsao_Entrega ());
      ed.setHR_Previsao_Entrega (edConhec.getHR_Previsao_Entrega ());
      ed.setOID_Modal (edConhec.getOID_Modal ());
      ed.setDM_Tipo_Tabela_Frete (edConhec.getDM_Tipo_Tabela_Frete ());

      //// //System.out.println ("  carregaParametros getDM_Tipo_Tabela_Frete ->>" + ed.getDM_Tipo_Tabela_Frete ());

      ed.setDM_Tipo_Coleta (edConhec.getDM_Tipo_Coleta ());
      ed.setDM_Tipo_Entrega (edConhec.getDM_Tipo_Entrega ());
      ed.setDM_Tipo_Transporte (edConhec.getDM_Tipo_Transporte ());

      ed.setOID_Pessoa (edConhec.getOID_Pessoa ());
      ed.setOID_Pessoa_Consignatario (edConhec.getOID_Pessoa_Consignatario ());
      ed.setOID_Pessoa_Redespacho (edConhec.getOID_Pessoa_Redespacho ());
      ed.setOID_Pessoa_Destinatario (edConhec.getOID_Pessoa_Destinatario ());
      ed.setOID_Pessoa_Pagador (edConhec.getOID_Pessoa_Pagador ());
      ed.setOID_Pessoa_Pagador_Tabela (edConhec.getOID_Pessoa_Pagador ());
      ed.setOID_Produto (edConhec.getOID_Produto ());

      sql = " SELECT oid_Produto_Tabela_Frete, DM_Classificacao " +
          " FROM  Produtos " +
          " WHERE Produtos.oid_Produto = " + edConhec.getOID_Produto () +
          " AND   Produtos.oid_Produto_Tabela_Frete is not null ";
      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        ed.setOID_Produto (rs.getLong ("oid_Produto_Tabela_Frete"));
        ed.setDM_Classificacao_Produto (rs.getString ("DM_Classificacao"));
      }

      ed.setOID_Unidade (edConhec.getOID_Unidade ());
      ed.setOID_Coleta (edConhec.getOID_Coleta ());
      ed.setOID_Empresa (edConhec.getOID_Empresa ());
      ed.setOID_Conhecimento (edConhec.getOID_Conhecimento ());
      ed.setOID_Cidade_Remetente (edConhec.getOID_Cidade ());
      ed.setOID_Cidade_Destinatario (edConhec.getOID_Cidade_Destino ());
      ed.setOID_Lote_Faturamento (edConhec.getOID_Lote_Faturamento ());
      ed.setNR_Cubagem (edConhec.getNR_Cubagem ());

      ed.setNR_Peso (edConhec.getNR_Peso ());
      ed.setNR_Peso_Cubado (edConhec.getNR_Peso_Cubado ());

      if (edConhec.getNR_Peso_Cubado () > 0 && "C".equals (edConhec.getDM_Tipo_Conhecimento ())) { /// cubagen pelo cto
        ed.setNR_Cubagem_Total (edConhec.getNR_Peso_Cubado ());
      }
      //// //System.out.println ("  getNR_Peso_Cubado ->>" + edConhec.getNR_Peso_Cubado ());
      //// //System.out.println ("  getDM_Tipo_Conhecimento ->>" + edConhec.getDM_Tipo_Conhecimento ());
      //// //System.out.println ("  setNR_Cubagem_Total ->>" + ed.getNR_Cubagem_Total ());

      ed.setVL_Nota_Fiscal (edConhec.getVL_Nota_Fiscal ());
      ed.setNR_Volumes (edConhec.getNR_Volumes ());

      ed.setDM_Tipo_Tarifa (edConhec.getDM_Tipo_Tarifa ());

      ed.setPE_Carga_Expressa (edConhec.getPE_Carga_Expressa ());

      ed.setOID_Cidade_Origem (edConhec.getOID_Cidade ());

      ed.setVL_Frete_Peso (edConhec.getVL_FRETE_PESO ());
      ed.setVL_Pedagio (edConhec.getVL_PEDAGIO ());
      ed.setVL_Total_Frete (edConhec.getVL_TOTAL_FRETE ());

      sql = " SELECT Estados.oid_Estado, Regioes_Estados.oid_Regiao_Estado, Regioes_Estados.oid_Subregiao from Cidades, Regioes_Estados, Estados " +
          " WHERE cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado " +
          " AND regioes_estados.oid_Estado = estados.oid_Estado " +
          " AND cidades.oid_cidade = " + ed.getOID_Cidade_Remetente ();
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        ed.setOID_Estado_Origem (rs.getLong ("oid_Estado"));
        ed.setOID_Regiao_Origem (rs.getLong ("oid_Regiao_Estado"));
        ed.setOID_Subregiao_Origem (rs.getLong ("oid_Subregiao"));
      }

      ed.setOID_Cidade_Destino (edConhec.getOID_Cidade_Destino ());
      ed.setDM_Tipo_Localizacao ("I");
      ed.setDM_Suframa ("N");
      ed.setDM_Fluvial ("N");

      sql = " SELECT cidades.DM_TIPO_LOCALIZACAO, cidades.DM_SUFRAMA, cidades.DM_FLUVIAL, Estados.oid_Estado, Regioes_Estados.oid_Regiao_Estado, Regioes_Estados.oid_Subregiao from Cidades, Regioes_Estados, Estados " +
          " WHERE cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado " +
          " AND regioes_estados.oid_Estado = estados.oid_Estado " +
          " AND cidades.oid_cidade = " + ed.getOID_Cidade_Destino ();
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        ed.setOID_Estado_Destino (rs.getLong ("oid_Estado"));
        ed.setOID_Regiao_Destino (rs.getLong ("oid_Regiao_Estado"));
        ed.setOID_Subregiao_Destino (rs.getLong ("oid_Subregiao"));
        ed.setDM_Tipo_Localizacao (rs.getString ("DM_TIPO_LOCALIZACAO"));
        ed.setDM_Suframa (rs.getString ("DM_SUFRAMA"));
        ed.setDM_Fluvial (rs.getString ("DM_FLUVIAL"));
      }

      ed.setDM_Isencao_ICMS ("N");
      ed.setDM_Isencao_Seguro ("N");
      ed.setDM_Inclui_Icms_Parcela_CTRC ("N");
      ed.setCD_Grupo_CFO ("351");
      ed.setNM_Inscricao_Estadual ("9999999999999");

      sql = " SELECT Pessoas.NM_Inscricao_Estadual, " +
          " Clientes.DM_PIS_COFINS, " +
          " Clientes.PE_PIS_COFINS, " +
          " Clientes.DM_Isencao_ICMS," +
          " Clientes.DM_Isencao_Seguro, " +
          " Clientes.DM_rctrc, " +
          " Clientes.DM_rctr_vi, " +
          " Clientes.DM_rctr_dc, " +
          " Clientes.DM_rcta, " +
          " Ramos_Atividades.CD_Grupo_CFO, " +
          " Clientes.DM_Inclui_Icms_Parcela_CTRC " +
          " FROM Clientes, Ramos_Atividades, Pessoas " +
          " WHERE  Clientes.oid_ramo_atividade = Ramos_Atividades.oid_ramo_atividade " +
          " AND    Clientes.OID_Pessoa = Pessoas.OID_Pessoa " +
          " AND    Clientes.OID_Pessoa = '" + ed.getOID_Pessoa_Pagador () + "'";
      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        ed.setDM_Isencao_ICMS (rs.getString ("DM_Isencao_ICMS"));
        ed.setDM_rctrc (rs.getString ("DM_rctrc"));
        ed.setDM_rctr_vi (rs.getString ("DM_rctr_vi"));
        ed.setDM_rctr_dc (rs.getString ("DM_rctr_dc"));
        ed.setDM_rcta (rs.getString ("DM_rcta"));

        ed.setDM_Inclui_Icms_Parcela_CTRC (rs.getString ("DM_Inclui_Icms_Parcela_CTRC"));
        ed.setNM_Inscricao_Estadual (rs.getString ("NM_Inscricao_Estadual"));

        ed.setPE_PIS_COFINS (rs.getDouble ("PE_PIS_COFINS"));
        if (JavaUtil.doValida (rs.getString ("CD_Grupo_CFO"))) {
          ed.setCD_Grupo_CFO (rs.getString ("CD_Grupo_CFO"));
        }
        if (rs.getString ("DM_PIS_COFINS") != null && !rs.getString ("DM_PIS_COFINS").equals ("") && !rs.getString ("DM_PIS_COFINS").equals ("null")) {
          ed.setDM_PIS_COFINS (rs.getString ("DM_PIS_COFINS"));

        }
      }

      //// //System.out.println ("ed.getOID_Cidade_Destino()=" + ed.getOID_Cidade_Destino () + " ISENCAO ICMS=" + ed.getDM_Isencao_ICMS ());

    }

    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , getClass ().getName () , "carregaParametros");
    }

    return ed;
  }

  private void baca_acerta_CFOP () throws Excecoes {

    //// //System.out.println ("baca_acerta_CFOP ->>");

    String sql = null , cd_cfo_conhecimento;
    ResultSet res = null;
    ResultSet rs = null;
    long OID_Estado_Origem_CFOP = 0 , OID_Estado_Destino_CFOP = 0;

    long oid_Unidade = 1;
    String dt_Inicial = "01/06/2007";
    String dt_Final = "31/08/2007";

    try {

      sql = " SELECT   " +
          " Conhecimentos.OID_Conhecimento, " +
          " Conhecimentos.cd_cfo_conhecimento, " +
          " Conhecimentos.oid_pessoa_Pagador," +
          " Conhecimentos.oid_Pessoa_Destinatario," +
          " Estado_Origem.oid_Estado as oid_Estado_Origem, " +
          " Estado_Destino.oid_Estado as oid_Estado_Destino, " +
          " Unidades.oid_Unidade " +
          " FROM  " +
          " Conhecimentos, " +
          " Unidades, " +
          " Cidades Cidade_Origem, " +
          " Cidades Cidade_Destino, " +
          " Regioes_Estados Regiao_Estado_Origem,  " +
          " Regioes_Estados Regiao_Estado_Destino, " +
          " Estados Estado_Origem, " +
          " Estados Estado_Destino " +
          " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
          " AND Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
          " AND Conhecimentos.DM_Tipo_Documento='C' " +
          " AND Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
          " AND Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
          " AND Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
          " AND Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
          " AND Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado " +
          //" AND Unidades.OID_Unidade =" + oid_Unidade +
          " AND Conhecimentos.DT_Emissao >= '" + dt_Inicial + "'" +
          " AND Conhecimentos.DT_Emissao <= '" + dt_Final + "'";

      res = this.executasql.executarConsulta (sql.toString ());
      //// //System.out.println (sql);
      while (res.next ()) {
        cd_cfo_conhecimento = res.getString ("cd_cfo_conhecimento");
        OID_Estado_Origem_CFOP = res.getLong ("oid_Estado_Origem");
        OID_Estado_Destino_CFOP = res.getLong ("oid_Estado_Destino");

        if ("U".equals (Parametro_FixoED.getInstancia ().getDM_Estado_Origem_CFOP ())) {
          sql = " SELECT Estados.oid_Estado " +
              " FROM   Cidades, Regioes_Estados, Estados, Pessoas, Unidades " +
              " WHERE  Cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado " +
              " AND regioes_estados.oid_Estado = estados.oid_Estado " +
              " AND cidades.oid_cidade = pessoas.oid_cidade " +
              " AND Pessoas.oid_Pessoa = Pessoas.oid_Pessoa" +
              " AND Unidades.oid_Unidade = '" + res.getString ("OID_Unidade") + "'";

          //// //System.out.println ("Origem CFOP=U ->>" + sql);

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            OID_Estado_Origem_CFOP = rs.getLong ("oid_Estado");
          }
        }

        if ("P".equals (Parametro_FixoED.getInstancia ().getDM_Estado_Destino_CFOP ())) {
          sql = " SELECT Estados.oid_Estado " +
              " FROM  Cidades, Regioes_Estados, Estados, Pessoas " +
              " WHERE Cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado " +
              " AND regioes_estados.oid_Estado = estados.oid_Estado " +
              " AND cidades.oid_cidade = pessoas.oid_cidade " +
              " AND pessoas.oid_pessoa = '" + res.getString ("oid_pessoa_Pagador") + "'";
          //// //System.out.println ("Origem CFOP=P ->>" + sql);

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            OID_Estado_Destino_CFOP = rs.getLong ("oid_Estado");
          }
        }

        if ("DESTINATARIO".equals (Parametro_FixoED.getInstancia ().getDM_Estado_Destino_CFOP ())) {
          sql = " SELECT Estados.oid_Estado " +
              " FROM  Cidades, Regioes_Estados, Estados, Pessoas " +
              " WHERE Cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado " +
              " AND regioes_estados.oid_Estado = estados.oid_Estado " +
              " AND cidades.oid_cidade = pessoas.oid_cidade " +
              " AND pessoas.oid_pessoa = '" + res.getString ("oid_Pessoa_Destinatario") + "'";

          //// //System.out.println ("Origem CFOP=DEST ->>" + sql);

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            OID_Estado_Destino_CFOP = rs.getLong ("oid_Estado");
          }
        }

        //// //System.out.println ("OID_Estado_Origem_CFOP ->>" + OID_Estado_Origem_CFOP);
        //// //System.out.println ("OID_Estado_Destino_CFOP->>" + OID_Estado_Destino_CFOP);

        //long oid_taxa = new Calcula_Frete (this.executasql).defineCriterioTaxa (OID_Estado_Origem_CFOP , OID_Estado_Destino_CFOP , res.getLong ("oid_Unidade") , res.getString ("oid_pessoa_Pagador"));
        if (OID_Estado_Origem_CFOP != OID_Estado_Destino_CFOP) {
          //CD_CFO = "6";
        }

      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "atualiza_CFOP(ConhecimentoED ed)");
    }
  }

  private void baca_acerta_Previsao_Entrega () throws Excecoes {

	    //// //System.out.println ("baca_acerta_Previsao_Entrega ->>");

	    String sql = null;
	    ResultSet res = null;
	    int contCto=0;

	    String dt_Inicial = "01/01/2008";
	    String dt_Final = "07/02/2009";

	    try {

	      sql = " SELECT   " +
	          " Conhecimentos.OID_Conhecimento, " +
	          " Conhecimentos.NR_Conhecimento, " +
	          " Conhecimentos.oid_Modal, " +
	          " Pessoas.oid_Cidade as oid_Cidade_Unidade," +
	          " Conhecimentos.oid_Cidade," +
	          " Conhecimentos.dt_emissao," +
	          " Conhecimentos.dt_previsao_entrega," +
	          " Conhecimentos.dt_entrega," +
	          " Conhecimentos.NR_Dias_Entrega_Previsto," +
	          " regioes_estados.oid_Estado as oid_Estado_Destino," +
	          " Conhecimentos.oid_Cidade_Destino " +
	          " FROM  " +
	          " Conhecimentos, Unidades, Pessoas, regioes_estados, Cidades " +
	          " WHERE  Conhecimentos.DM_Impresso='S' and Conhecimentos.DM_Situacao <>'C' " +
	          " AND Conhecimentos.oid_Unidade = Unidades.oid_Unidade and Unidades.oid_pessoa = Pessoas.oid_pessoa " +
	          " AND Conhecimentos.oid_Cidade_Destino = cidades.oid_Cidade " +
	          " AND cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado " +
	          " AND Conhecimentos.DT_Emissao >= '" + dt_Inicial + "'" +
	          " AND Conhecimentos.DT_Emissao <= '" + dt_Final + "'";

	      res = this.executasql.executarConsulta (sql.toString ());
	      //// //System.out.println (sql);
	      while (res.next ()) {

	    	    //// //System.out.println ("NR_Conhecimento  ===>>" + res.getString("NR_Conhecimento"));
	    	    //// //System.out.println ("dt_previsao_entrega CTO ===>>" + FormataData.formataDataBT (res.getDate ("dt_previsao_entrega")));
	    	    //// //System.out.println ("NR_Dias_Entrega_Previsto CTO===>>" + res.getLong("NR_Dias_Entrega_Previsto"));
	    	    //// //System.out.println ("dt_entrega CTO ===>>" + FormataData.formataDataBT (res.getDate ("dt_entrega")));

	    	    Calcula_FreteED ed = new Calcula_FreteED ();
	    	    ed.setDT_Programada(FormataData.formataDataBT (res.getDate ("dt_emissao")));
	    	    ed.setOID_Cidade_Origem(res.getLong("oid_Cidade_Unidade"));
	    	    ed.setOID_Cidade_Destino(res.getLong("oid_Cidade_Destino"));
	    	    ed.setOID_Estado_Destino(res.getLong("oid_Estado_Destino"));
	    	    ed.setOID_Modal(res.getLong("oid_Modal"));

	    	    //// //System.out.println ("dt_previsao_entrega ===>>" + ed.getDT_Previsao_Entrega());
	    	    //// //System.out.println ("NR_Dias_Entrega_Previsto E===>>" + ed.getNR_Dias_Entrega_Previsto());
	    	    //// //System.out.println ("NR_Prazo_Entrega E===>>" + ed.getNR_Prazo_Entrega());

	    	    if (res.getDate ("dt_previsao_entrega")==null || !FormataData.formataDataBT (res.getDate ("dt_previsao_entrega")).equals(ed.getDT_Previsao_Entrega())) {
	    	    	contCto++;

	    	          sql = " UPDATE Conhecimentos set dt_previsao_entrega='" + ed.getDT_Previsao_Entrega() + "', NR_Prazo_Entrega = '" + ed.getNR_Prazo_Entrega() + "', TX_Local_Retirada='U' WHERE oid_Conhecimento ='" + res.getString ("OID_Conhecimento") + "'";
	    	          //// //System.out.println (sql);
	    	          int i = executasql.executarUpdate (sql);

	    	    	//// //System.out.println ("contCto ===>>" + contCto);
	    	    }


	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "atualiza_CFOP(ConhecimentoED ed)");
	    }
	  }

  private void baca_acerta_Compromisso () throws Excecoes {

    //// //System.out.println ("baca_acerta_Compromisso ->>");

    ResultSet res = null;
    String sql = "";

    try {
      long oid_Compromisso = 0;
      sql = " SELECT * FROM Compromisso2 order BY oid_Compromisso  ";

      res = this.executasql.executarConsulta (sql.toString ());
      //// //System.out.println (sql);
      while (res.next ()) {

        //// //System.out.println ("baca_acerta_Compromisso=>> " + res.getLong ("oid_Compromisso"));

        if (oid_Compromisso != res.getLong ("oid_Compromisso")) {
          CompromissoED ed = new CompromissoED ();
          ed.setDt_Emissao (res.getString ("Dt_Emissao"));
          ed.setDt_Vencimento (res.getString ("Dt_Vencimento"));
          ed.setDt_Entrada (res.getString ("Dt_Entrada"));
          ed.setNr_Documento (res.getString ("Nr_Documento"));
          ed.setNr_Parcela (new Integer (res.getString ("Nr_Parcela")));
          ed.setOid_Centro_Custo (new Integer (res.getString ("oid_Centro_Custo")));
          ed.setOid_Conta (new Integer (res.getString ("oid_Conta")));
          ed.setOid_Pessoa (res.getString ("oid_Pessoa"));
          ed.setOid_Tipo_Documento (new Integer (res.getString ("oid_Tipo_Documento")));
          ed.setOid_Unidade (new Long (res.getString ("oid_Unidade")));
          ed.setOid_Unidade_Pagamento (res.getInt ("oid_Unidade"));
          ed.setTx_Observacao (res.getString ("FT_TX_Observacao"));
          ed.setDM_Tipo_Pagamento ("*");
          ed.setVl_Compromisso (new Double (res.getString ("Vl_Compromisso")));
          ed.setVl_Juro_Mora_dia (new Double (res.getString ("Vl_Juro_Mora_dia")));
          ed.setVl_Saldo (new Double (res.getString ("VL_Saldo")));

          this.inicioTransacao ();
          new CompromissoBD (this.sql).inclui (ed);
          this.fimTransacao (true);

        }
        oid_Compromisso = res.getLong ("oid_Compromisso");

      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "atualiza_CFOP(ConhecimentoED ed)");
    }
  }

  private void importa_Plano_de_Contas (String arquivo) throws Excecoes {

	    String NM_Registro = "";

	    String sql = "", nm_conta = "", cd_conta_estrutural;
	    int cd_conta = 0;
	    int linha=0;

	    try {
	      ManipulaArquivo man = new ManipulaArquivo ("");
	      LineNumberReader line = man.leLinha (arquivo);

	      if (line.ready ()) {

	        while ( (NM_Registro = line.readLine ()) != null) {

	          if (NM_Registro != null && NM_Registro.length () > 4) {

	            NM_Registro = ManipulaString.corrigeString (NM_Registro) + "       ";
	            NM_Registro = NM_Registro.toUpperCase ();

	            cd_conta_estrutural=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 2, ";");
	            cd_conta = new Integer(ManipulaString.buscaSegmentoDelimitado(NM_Registro, 1, ";")).intValue();

  	            sql = " UPDATE Contas set cd_estrutural_sped ='" + cd_conta_estrutural + "' WHERE cd_conta = '" + cd_conta + "'";

  	            //System.out.println(sql);

  	            executasql.executarUpdate (sql);

	          }
	          linha++;
	          line.setLineNumber (linha);
	        }
	      }
	      line.close ();
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("importa_Plano_de_Contas");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  private void importa_Entrega (String arquivo) throws Excecoes {

	    String NM_Registro = "";

	    String sql = "", nr_conhecimento = "", dt_entrega="", tx_observacao="";
	    int linha=0;

	    try {
	      ManipulaArquivo man = new ManipulaArquivo ("");
	      LineNumberReader line = man.leLinha (arquivo);

	      if (line.ready ()) {

	        while ( (NM_Registro = line.readLine ()) != null) {

	          if (NM_Registro != null && NM_Registro.length () > 4) {

	            NM_Registro = ManipulaString.corrigeString (NM_Registro) + "       ";
	            NM_Registro = NM_Registro.toUpperCase ();

	            nr_conhecimento=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 1, ";");
	            dt_entrega=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 2, ";");
	            tx_observacao=ManipulaString.buscaSegmentoDelimitado(NM_Registro, 3, ";");

	            if (!"00".equals(dt_entrega.substring(0, 2))){

		            sql = " SELECT oid_conhecimento FROM Conhecimentos where nr_conhecimento = " + nr_conhecimento;

		            //System.out.println(sql);

		            ResultSet res = this.executasql.executarConsulta (sql.toString ());
		            String oid_Conhecimento = "";
		            while (res.next ()) {
		            	oid_Conhecimento = res.getString("oid_conhecimento");
		            }

		            //System.out.println("oid_Conhecimento  ==== "+oid_Conhecimento);

		    	    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
		    	    ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento(dt_entrega);
		    	    ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento("10:00");
		    	    ocorrencia_ConhecimentoED.setTX_Observacao(tx_observacao);
		    	    ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia(16);
		    	    ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega(" ");
		    	    ocorrencia_ConhecimentoED.setOID_Conhecimento(oid_Conhecimento);

		            //System.out.println("Fui  ==== ");

		            new Ocorrencia_ConhecimentoBD (this.executasql).inclui (ocorrencia_ConhecimentoED);

		            //System.out.println("Voltei  ==== ");

	            }

	          }
	          linha++;
	          line.setLineNumber (linha);
	        }
	      }
	      line.close ();
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("importa_Plano_de_Contas");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

	public void AtualizaIBGE(String arquivo) throws Excecoes {
		ArrayList list = new ArrayList();
		ResultSet resCli = null;

		try {

			String NM_Registro = "";

			String codigo = "";
			int linha = 0;

			ManipulaArquivo man = new ManipulaArquivo("");
			LineNumberReader line = man.leLinha(arquivo);
			int atualizados = 0;
			if (line.ready()) {
				while ((NM_Registro = line.readLine()) != null) {
					linha++;

					if (NM_Registro != null && NM_Registro.length() > 10) {

						NM_Registro = ManipulaString.corrigeString(NM_Registro)
								+ "                                                                        ";
						NM_Registro = NM_Registro.toUpperCase();

						codigo = ManipulaString.buscaSegmentoDelimitado(
								NM_Registro, 2, ";");

						if (!"".equals(codigo)) {

							String uf = ManipulaString
									.buscaSegmentoDelimitado(NM_Registro, 1,
											";");

							String cidade = ManipulaString
									.buscaSegmentoDelimitado(NM_Registro, 3,
											";");
							cidade = ManipulaString.tiraAspas(cidade);
							cidade = ManipulaString.corrigeString(cidade);

							CidadeBean cid = CidadeBean.getByCidade(cidade, uf);
							if(cid.getOID()>0){
								String sql = " UPDATE cidades SET nm_codigo_ibge='"
										+ codigo
										+ "', WHERE oid_cidade ="+cid.getOID();

								executasql.executarUpdate(sql);
								atualizados++;
							}

						}
					}
				}
				System.out.println("Atualizados:" + atualizados);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			Excecoes excecoes = new Excecoes();
			excecoes.setMetodo("gera_Remessa");
			excecoes.setExc(exc);
			throw excecoes;
		}
	}

}
