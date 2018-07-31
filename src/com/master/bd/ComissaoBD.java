package com.master.bd;
import java.sql.ResultSet;

import com.master.ed.ComissaoED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.rn.ComissaoRN;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

public class ComissaoBD extends Transacao{

  private ExecutaSQL executasql;
     double vl_total_frete = 0;
     double vl_notas_fiscais=0;
     double nr_peso=0;
     double nr_peso_cubado=0;
     double nr_volumes=0;
     double vl_margem=0;
     double vl_total_custo=0;
     double nr_despachos=0;
     String Nr_Sort = "";
     String OID_Pessoa ="PRIMEIRO";
     String OID_Pessoa_Lida ="";
     long OID_Modal =999999;
     long OID_Grupo_Economico =999999;

     long OID_Cidade_Origem =999999;
     long OID_Cidade_Destino =999999;


     double tvl_total_frete = 0;
     double tvl_notas_fiscais=0;
     double tnr_peso=0;
     double tnr_peso_cubado=0;
     double tnr_volumes=0;
     double tvl_margem=0;
     double tnr_despachos=0;

     String DM_Quebra_Unidade="N";
     String DM_Quebra_Cliente="N";
     String DM_Quebra_Vendedor="N";
     String DM_Quebra_Modal="N";
     String DM_Quebra_Produto="N";
     String DM_Quebra_Grupo_Economico="N";
     String DM_Quebra_Cidade="N";
     String DM_Mapa="N";

     String DM_Analise="";
     double Vl_Analise=0;
     String Dt_Analise="";

     double v1=0, v2=0, v3=0, v4=0, v5=0, vencido=0, vencer=0;
     long OID_Carteira=0, OID_Conta_Corrente=0, OID_Conta=0;
     long OID_Comissao=0;

  public ComissaoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }


  public byte[] geraComissao_Vendas(ComissaoED ed)throws Excecoes{

     String sql = null;
     byte[] b = null;

     OID_Comissao=0;


    // System.out.println("geraAnalise_Comissao_Conhecimentos >>>  "  );

      sql = "  select Conhecimentos.oid_conhecimento, Conhecimentos.oid_Vendedor, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Modal, Conhecimentos.oid_cidade, Conhecimentos.oid_cidade_destino, Conhecimentos.oid_Pessoa_Pagador, Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.nr_volumes, Conhecimentos.vl_total_frete, Conhecimentos.vl_total_custo , Conhecimentos.NR_Peso, Conhecimentos.NR_Peso_Cubado, Conhecimentos.NR_Volumes, Conhecimentos.VL_Nota_Fiscal ";
      sql += " FROM   Conhecimentos  WHERE 1=1 ";

      if (String.valueOf(ed.getOID_Vendedor()) != null &&
         !String.valueOf(ed.getOID_Vendedor()).equals("") &&
         !String.valueOf(ed.getOID_Vendedor()).equals("null")){
        sql += " and Conhecimentos.OID_Vendedor = '" + ed.getOID_Vendedor() + "'";
      }

      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
        !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
        !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
        sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
      !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
      !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
        sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }


//     if (DM_Quebra_Vendedor.equals("S") ) {
//        sql += " order by conhecimentos.oid_Vendedor ";
//     }

      //// System.out.println(" sql " + sql);

    ComissaoED edVolta = new ComissaoED();
    ComissaoRN ComissaoRN = new ComissaoRN();
    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED();
    Movimento_ConhecimentoRN movimento_ConhecimentoRN = new Movimento_ConhecimentoRN();


    try{

      //// //// System.out.println(sql);

     ResultSet res = null;
     res = this.executasql.executarConsulta(sql.toString());

     Nr_Sort = String.valueOf(System.currentTimeMillis()).toString();

     while (res.next()){

             if (DM_Quebra_Vendedor.equals("S")) {

                  if (OID_Pessoa.equals("PRIMEIRO") || res.getString("OID_Vendedor").equals(OID_Pessoa)){
                      OID_Pessoa = res.getString("OID_Vendedor");
                      edVolta = this.totaliza_conhecimento(edVolta,res,"acumula");
                  }
                  else {
                      if ( vl_total_frete>0){
                        ComissaoRN.inclui(edVolta = carregaED(ed));
                      }
                      OID_Pessoa = res.getString("OID_Vendedor");
                      edVolta = this.totaliza_conhecimento(edVolta,res,"zera");
                      }
                  }
             }
             if ( vl_total_frete>0){
                ComissaoRN.inclui(edVolta = carregaED(edVolta));
             }
             ed.setNR_Sort(Nr_Sort);
             ed.setTVL_Total_Frete(tvl_total_frete);
             ed.setTVL_Margem(tvl_margem);
             ed.setTVL_Notas_Fiscais(tvl_notas_fiscais);
             ed.setTNR_Despachos(tnr_despachos);
             ed.setTNR_Peso(tnr_peso);
             ed.setTNR_Peso_Cubado(tnr_peso_cubado);
             ed.setTNR_Volumes(tnr_volumes);

//             b = this.imprime_Comissao_conhecimento(ed);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ComissaoED ed)");
    }
    return b;
  }


  public byte[] geraAnalise_Comissao_Financeira(ComissaoED ed)throws Excecoes{

     String sql = null;
     byte[] b = null;

     ComissaoED edVolta = new ComissaoED();
     ComissaoRN ComissaoRN = new ComissaoRN();

    try{

       //// System.out.println("bd 1");


     Nr_Sort = String.valueOf(System.currentTimeMillis()).toString();


     sql  = " select Duplicatas.OID_Carteira, Duplicatas.VL_Saldo, Duplicatas.DT_Vencimento ";
     sql += " From  Duplicatas ";
     sql += " WHere Duplicatas.VL_Saldo > 0  ";

     //// System.out.println(" sql " + sql);

     ResultSet res = null;
     res = this.executasql.executarConsulta(sql.toString());

     while (res.next()){
          this.totaliza_financeiro(ed, "duplicata",res);
     }

//         b = this.imprime_Comissao_financeiro(ed);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ComissaoED ed)");
    }
    return b;
  }


  public ComissaoED totaliza_financeiro(ComissaoED ed, String Origem, ResultSet res) throws Excecoes{

    String sql = null;
    long valOid = 0;
    int tem_Comissao=0, i=0;
    try{


     sql  = " select OID_Comissao FROM Comissao ";
     sql += " WHere 1=1 ";

     if (Origem.equals("duplicata")) {
        sql += " and Comissao.oid_carteira= " + res.getString("oid_Carteira");
        OID_Carteira=res.getLong("oid_Carteira");
     }

//     if (Origem.equals("compromisso")) {
//        sql += " and Comissao.oid_conta= " + res.getString("oid_conta");
//     }
//     if (Origem.equals("banco")) {
//        sql += " and Comissao.oid_conta_corrente= " + res.getString("oid_conta_corrente");
//     }

//     //// System.out.println(" sql " + sql);

     ResultSet  rs = this.executasql.executarConsulta(sql.toString());
     OID_Comissao=0;
     while (rs.next()){

        //// System.out.println("tem_Comissao 1 ->>" + res.getString("DT_Vencimento") );

        tem_Comissao++;
        OID_Comissao=rs.getLong("OID_Comissao");
        v1=rs.getDouble("vl_1");
        v2=rs.getDouble("vl_2");
        v3=rs.getDouble("vl_3");
        v4=rs.getDouble("vl_4");
        v5=rs.getDouble("vl_5");
        vencido=rs.getDouble("vl_vencido");
        vencer=rs.getDouble("vl_vencer");

     }

     //// System.out.println("achou 1  tem_Comissao->>" + tem_Comissao );

     ComissaoED edComissao = new ComissaoED();
//     ComissaoRN.inclui(edComissao = carregaED(edComissao));


     if (tem_Comissao <= 0) {

         sql = " update Comissao set (VL_Vencido= " + vencido + ", V1_1= " + v1 + ", V1_2= " + v2 + ", V1_3= " + v3 + ", V1_4= " + v4 + ", V1_5= " + v5 + ", Vl_vencer= " + vencer  + ")";
     }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro de Comissao de dados");
      excecoes.setExc(exc);
    }
     return ed;
  }



  public ComissaoED totaliza_conhecimento(ComissaoED ed, ResultSet res, String acao ) throws Excecoes{

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED();
    Movimento_ConhecimentoRN movimento_ConhecimentoRN = new Movimento_ConhecimentoRN();

// //// System.out.println("totaliza_conhecimento..." + acao);

    try{

                if (acao.equals("zera")) {
                    nr_despachos=1;
                    vl_total_frete=0;
                    vl_margem=0;
                    vl_notas_fiscais=0;
                    nr_peso=0;
                    nr_peso_cubado=0;
                    nr_volumes=0;
                }

                vl_total_custo = res.getDouble("vl_total_custo");
                if (vl_total_custo <=0) {
                  movimento_ConhecimentoED.setOID_Conhecimento(res.getString("oid_conhecimento"));
                  movimento_ConhecimentoED = movimento_ConhecimentoRN.calcula_Margem(movimento_ConhecimentoED);
                  vl_total_custo = movimento_ConhecimentoED.getVL_Despesas();
                }

                nr_despachos++;
                vl_total_frete=vl_total_frete+res.getDouble("vl_total_frete");

                vl_margem=vl_margem + (res.getDouble("vl_total_frete")-vl_total_custo);

                vl_notas_fiscais=vl_notas_fiscais+res.getDouble("VL_Nota_Fiscal");
                nr_peso=nr_peso+res.getDouble("nr_peso");
                nr_peso_cubado=nr_peso_cubado+res.getDouble("nr_peso_cubado");
                nr_volumes=nr_volumes+res.getDouble("NR_Volumes");

                tnr_despachos++;
                tvl_margem=tvl_margem+vl_margem;
                tvl_total_frete=tvl_total_frete+res.getDouble("vl_total_frete");
                tvl_notas_fiscais=tvl_notas_fiscais+res.getDouble("VL_Nota_Fiscal");
                tnr_peso=tnr_peso+res.getDouble("nr_peso");
                tnr_peso_cubado=tnr_peso_cubado+res.getDouble("nr_peso_cubado");
                tnr_volumes=tnr_volumes+res.getDouble("NR_Volumes");

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao totaliza_conhecimentor ");
      excecoes.setMetodo("");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;
  }

  public ComissaoED carregaED(ComissaoED ed) throws Excecoes{

// //// System.out.println("carregaED..." );

    ComissaoED edComissao = new ComissaoED();
    try{
         edComissao.setOID_Comissao(OID_Comissao);

         edComissao.setOID_Pessoa(OID_Pessoa);
         edComissao.setOID_Modal(OID_Modal);
         edComissao.setOID_Grupo_Economico(OID_Grupo_Economico);
         edComissao.setOID_Cidade_Origem(OID_Cidade_Origem);
         edComissao.setOID_Cidade_Destino(OID_Cidade_Destino);
         edComissao.setNR_Sort(Nr_Sort);
         edComissao.setVL_Total_Frete(vl_total_frete);
         edComissao.setVL_Margem(vl_margem);
         edComissao.setVL_Notas_Fiscais(vl_notas_fiscais);
         edComissao.setNR_Despachos(nr_despachos);
         edComissao.setNR_Peso(nr_peso);
         edComissao.setNR_Peso_Cubado(nr_peso_cubado);
         edComissao.setNR_Volumes(nr_volumes);

         edComissao.setTVL_Total_Frete(tvl_total_frete);
         edComissao.setTVL_Margem(tvl_margem);
         edComissao.setTVL_Notas_Fiscais(tvl_notas_fiscais);
         edComissao.setTNR_Despachos(tnr_despachos);
         edComissao.setTNR_Peso(tnr_peso);
         edComissao.setTNR_Peso_Cubado(tnr_peso_cubado);
         edComissao.setTNR_Volumes(tnr_volumes);

         edComissao.setOID_Carteira(OID_Carteira);
         edComissao.setOID_Conta(OID_Conta);
         edComissao.setOID_Conta_Corrente(OID_Conta_Corrente);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao totaliza_conhecimentor ");
      excecoes.setMetodo("");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edComissao;
  }



//  public byte[] imprime_Comissao_conhecimento(ComissaoED ed)throws Excecoes{
//
//    String sql = null;
//    byte[] b = null;
//
//    try{
//
//     ResultSet res = null;
//
//            sql = " select ";
//            sql+="  Comissao.OID_Comissao, ";
//            sql+="  Comissao.VL_TOTAL_FRETE, ";
//            sql+="  Comissao.NR_PESO, ";
//            sql+="  Comissao.OID_PESSOA, ";
//            sql+="  Comissao.NR_PESO_CUBADO, ";
//            sql+="  Comissao.NR_VOLUMES, ";
//            sql+="  Comissao.VL_MARGEM, ";
//            sql+="  Comissao.NR_DESPACHOS, ";
//            sql+="  Comissao.NR_SORT, ";
//            sql+="  Comissao.VL_NOTAS_FISCAIS, ";
//            sql+="  Comissao.VL_COLETA, ";
//            sql+="  Comissao.VL_ICMS, ";
//            sql+="  Comissao.VL_SIMPLES, ";
//            sql+="  Comissao.VL_SEGURO, ";
//            sql+="  Comissao.VL_CUSTO_COLETA, ";
//            sql+="  Comissao.VL_CUSTO_ENTREGA, ";
//            sql+="  Comissao.VL_CARGA, ";
//            sql+="  Comissao.NM_CAMPO, ";
//
//          if (DM_Quebra_Cliente.equals("S")) {
//              sql+="  NM_Razao_Social ";
//              sql+="  from Comissao,  Pessoas ";
//              sql+="  where Comissao.oid_pessoa = Pessoas.oid_pessoa " ;
//          }
//          if (DM_Quebra_Modal.equals("S")) {
//              sql+="  Modal.CD_Modal, ";
//              sql+="  Modal.NM_Modal ";
//              sql+="  from Comissao, Modal ";
//              sql+="  where Comissao.oid_modal = modal.oid_modal " ;
//          }
//          if (DM_Quebra_Grupo_Economico.equals("S")) {
//              sql+="  Grupos_Economicos.CD_Grupo_Economico, ";
//              sql+="  Grupos_Economicos.NM_Grupo_Economico ";
//              sql+="  from Comissao, Grupos_Economicos ";
//              sql+="  where Comissao.oid_Grupo_Economico = Grupos_Economicos.oid_Grupo_Economico " ;
//          }
//
//          if (DM_Quebra_Cidade.equals("S")) {
//              sql+=" Cidade_Origem.nm_cidade as NM_Cidade_Origem, Cidade_Destino.nm_cidade as NM_Cidade_Destino ";
//              sql+=" from Comissao, Cidades Cidade_Origem, Cidades Cidade_Destino  ";
//              sql+=" where Comissao.oid_Cidade_Origem  = Cidade_Origem.oid_Cidade " ;
//              sql+=" and   Comissao.oid_Cidade_Destino = Cidade_Destino.oid_Cidade " ;
//          }
//          if (DM_Quebra_Vendedor.equals("S")) {
//              sql+="  NM_Razao_Social ";
//              sql+="  from Comissao,  Pessoas ";
//              sql+="  where Comissao.oid_pessoa = Pessoas.oid_pessoa " ;
//          }
//
//
//          sql += " and   Comissao.Nr_Sort = '" + ed.getNR_Sort() + "'" ;
//          if (ed.getDM_Classificar().equals("1")) sql += " order by Comissao.vl_total_Frete desc ";
//          if (ed.getDM_Classificar().equals("2")) sql += " order by Comissao.vl_margem desc ";
//          if (ed.getDM_Classificar().equals("3")) sql += " order by Comissao.NR_Peso desc ";
//          if (ed.getDM_Classificar().equals("4")) sql += " order by Comissao.NR_Peso_Cubado desc ";
//          if (ed.getDM_Classificar().equals("5")) sql += " order by Comissao.NR_Volumes desc ";
//          if (ed.getDM_Classificar().equals("6")) sql += " order by Comissao.VL_Notas_Fiscais desc ";
//          if (ed.getDM_Classificar().equals("7")) sql += " order by Comissao.NR_Despachos desc ";
//
// //// System.out.println("sql ->>  " + sql);
//
//          res = this.executasql.executarConsulta(sql.toString());
//
//          ComissaoRL conRL = new ComissaoRL();
//
//  //         b =  conRL.geraAnalise_Comissao_Conhecimentos_Modelo01(res,ed);
//
//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Comissao_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Comissao_Conhecimentos_Modelo02(res,ed);
//    }
//
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(ComissaoED ed)");
//    }
//    return b;
//  }
//

//  public byte[] imprime_Comissao_financeiro(ComissaoED ed)throws Excecoes{
//
//    String sql = null;
//    byte[] b = null;
//
//    try{
//
//     ResultSet res = null;
//
//            sql = " select ";
//            sql+="  Comissao.OID_Comissao, ";
//            sql+="  Comissao.VL_TOTAL_FRETE, ";
//            sql+="  Comissao.NR_PESO, ";
//            sql+="  Comissao.OID_PESSOA, ";
//            sql+="  Comissao.NR_PESO_CUBADO, ";
//            sql+="  Comissao.NR_VOLUMES, ";
//            sql+="  Comissao.VL_MARGEM, ";
//            sql+="  Comissao.NR_DESPACHOS, ";
//            sql+="  Comissao.NR_SORT, ";
//            sql+="  Comissao.VL_NOTAS_FISCAIS, ";
//            sql+="  Comissao.VL_COLETA, ";
//            sql+="  Comissao.VL_ICMS, ";
//            sql+="  Comissao.VL_SIMPLES, ";
//            sql+="  Comissao.VL_SEGURO, ";
//            sql+="  Comissao.VL_CUSTO_COLETA, ";
//            sql+="  Comissao.VL_CUSTO_ENTREGA, ";
//            sql+="  Comissao.VL_CARGA, ";
//            sql+="  Comissao.NM_CAMPO, ";
//
//
// //// System.out.println("sql ->>  " + sql);
//
//          res = this.executasql.executarConsulta(sql.toString());
//
//         // ComissaoRL conRL = new ComissaoRL();
//
//          // b =  conRL.geraAnalise_Financeira_Modelo01(res,ed);
//
//          if (ed.getDM_Relatorio().equals("CSC")) b =  conRL.geraAnalise_Comissao_Conhecimentos_Modelo01(res,ed);
//          if (ed.getDM_Relatorio().equals("CSCID")) b =  conRL.geraAnalise_Comissao_Conhecimentos_Modelo02(res,ed);
//    }
//
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(ComissaoED ed)");
//    }
//    return b;
//  }



  public void inclui(ComissaoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Comissao) as result from Comissao");

      while (rs.next()) valOid = rs.getInt("result");
      sql = " insert into Comissao (OID_Comissao, OID_Pessoa, OID_Modal, OID_Grupo_Economico, OID_Conta, OID_Carteira, OID_Conta_Corrente, OID_Cidade_Origem, OID_Cidade_Destino, Nr_Sort, VL_Total_Frete, VL_Notas_Fiscais, NR_Peso, NR_Peso_Cubado, NR_Volumes,  NR_Despachos, VL_Vencido, Vl_1, Vl_2, Vl_3, Vl_4, Vl_5, Vl_vencer , VL_Margem  ) values ";
      sql += "(" + ++valOid + ",'";
      sql += ed.getOID_Pessoa() + "',";
      sql += ed.getOID_Modal() + ",";
      sql += ed.getOID_Grupo_Economico() + ",";
      sql += ed.getOID_Conta() + ",";
      sql += ed.getOID_Carteira() + ",";
      sql += ed.getOID_Conta_Corrente() + ",";
      sql += ed.getOID_Cidade_Origem() + ",";
      sql += ed.getOID_Cidade_Destino() + ",'";
      sql += ed.getNR_Sort() + "',";
      sql += ed.getVL_Total_Frete() + ",";
      sql += ed.getVL_Notas_Fiscais() + ",";
      sql += ed.getNR_Peso() + ",";
      sql += ed.getNR_Peso_Cubado() + ",";
      sql += ed.getNR_Volumes() + ",";
      sql += ed.getNR_Despachos() + ",";
      sql += ed.getVL_Vencido() + ",";
      sql += ed.getVL_1() + ",";
      sql += ed.getVL_2() + ",";
      sql += ed.getVL_3() + ",";
      sql += ed.getVL_4() + ",";
      sql += ed.getVL_5() + ",";
      sql += ed.getVL_Vencer() + ",";
      sql += ed.getVL_Margem() + ")";

 //// System.out.println("inclui" + sql);

      int i = executasql.executarUpdate(sql);
// //// System.out.println("ok");
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro de Comissao de dados");
      excecoes.setExc(exc);
    }
  }

  public void deleta(ComissaoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Comissao WHERE oid_Comissao = ";
      sql += "(" + ed.getOID_Comissao() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir ");
      excecoes.setMetodo("deletar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

}