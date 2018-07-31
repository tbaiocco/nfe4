package com.master.bd;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.io.File;
import java.io.LineNumberReader;

import com.master.ed.*;
import com.master.rl.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;
import com.master.bd.Plano_ViagemBD;

public class Movimento_EstoqueBD extends Transacao{
  private ExecutaSQL executasql;
  FormataDataBean DataFormatada = new FormataDataBean();
  CidadeBean CidadeBean = new CidadeBean();
  SeparaEndereco SeparaEndereco = new SeparaEndereco();

  Plano_ViagemBD Plano_ViagemBD = null;

  Utilitaria util = new Utilitaria(executasql);

  public Movimento_EstoqueBD(ExecutaSQL sql) {
      this.executasql = sql;
      util = new Utilitaria(this.executasql);
  }

  FormataDataBean dataFormatada = new FormataDataBean ();
  SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  UnidadeBean Unidades = new UnidadeBean ();

  String Nr_Sort = "";
  String data = "";
  String data1 = "";
  String data2 = "";


  String OID_Veiculo = "";

  String DM_Analitico_Sintetico = "S";
  String DM_Analise = "";
  String OID_Movimento_Estoque = "";

  int lidos = 0;


  public byte[] gera_Relatorio_Movimento_Estoque(Movimento_EstoqueED ed)
  throws Excecoes{
      String sql =
          " SELECT Multas.OID_Multa, " +
          " from Multas, " +
          " WHERE Multas.OID_Veiculo = Veiculos.OID_Veiculo " +
          "   AND (Multas.OID_Pessoa_Entregadora is null or Multas.OID_Pessoa_Entregadora = '' ) ";
      if (ed.getOID_Empresa() > 0){
          sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa();
      }
      if (ed.getOID_Unidade() > 0) {
          sql += " and Multas.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (util.doValida(ed.getDT_Emissao_Inicial())){
          sql += " and Multas.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
      }

      if (util.doValida(ed.getDT_Emissao_Final())) {
          sql += " and Multas.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }
      if (util.doValida(ed.getOID_Veiculo())) {
          sql += " and Multas.OID_Veiculo = '" + ed.getOID_Veiculo() + "' ";
      }
      sql += " Order by Multas.Dt_Emissao, Multas.NR_Multa ";

      ResultSet res = this.executasql.executarConsulta(sql.toString());
      return new Movimento_EstoqueRL().gera_Relatorio_Movimento_Estoque(res, ed);
  }



}
