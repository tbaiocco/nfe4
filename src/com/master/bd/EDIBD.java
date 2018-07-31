package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.EDIED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class EDIBD {

  private ExecutaSQL executasql;

  public EDIBD(ExecutaSQL sql) {
    this.executasql = sql;
  }
  public ArrayList gera_Arquivo_EDI(EDIED edComp)throws Excecoes{

  String sql = null;
  ArrayList list = new ArrayList();
  EDIED ed = (EDIED)edComp;

    try{
      sql = "Select "+
        " Veiculos.NR_PLACA,"+
        " Veiculos.NR_FROTA,"+
        " Veiculos.TX_OBSERVACAO,"+
        " Modelos_Veiculos.CD_MODELO_VEICULO,"+
        " Modelos_Veiculos.NM_MODELO_VEICULO,"+
        " Complementos_Veiculos.VL_COMPRA,"+
        " Complementos_Veiculos.NR_ANO_VEICULO,"+
        " Complementos_Veiculos.PE_DEPRECIACAO,"+
        " Complementos_Veiculos.NR_COMPRIMENTO,"+
        " Complementos_Veiculos.NR_LARGURA,"+
        " Complementos_Veiculos.NR_ALTURA,"+
        " Complementos_Veiculos.NR_PESO_LIMITE,"+
        " Complementos_Veiculos.DM_PROCEDENCIA,"+
        " Marcas_Veiculos.NM_MARCA_VEICULO,"+
        " Tipos_Veiculos.NM_TIPO_VEICULO,"+
        " Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, "+
        " Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, "+
        " Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, "+
        " Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, "+
        " Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, "+
        " Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, "+
        " Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, "+
        " Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, "+
        " Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO "+
        " FROM "+
        " Veiculos, Pessoas Pessoa_Proprietario, "+
        " Cidades Cidade_Proprietario, "+
        " Regioes_Estados Regiao_Estado_Proprietario, Marcas_Veiculos, Tipos_Veiculos,"+
        " Estados Estado_Proprietario, "+
        " Modelos_Veiculos, Complementos_Veiculos, "+
        " Cidades Cidade_Veiculo, "+
        " Regioes_Estados Regiao_Estado_Veiculo, "+
        " Estados Estado_Veiculo "+
        " WHERE Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.OID_Pessoa "+
        " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO "+
        " AND Marcas_Veiculos.OID_MARCA_VEICULO = Modelos_Veiculos.OID_MARCA_VEICULO "+
        " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO "+
        " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO "+
        " AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE "+
        " AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO "+
        " AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO "+
        " AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE "+
        " AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO "+
        " AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO ";

      if (ed.getNR_Placa() != null &&
         !ed.getNR_Placa().equals("") &&
         !ed.getNR_Placa().equals("null")){
        sql += " AND Veiculos.oid_Veiculo = '" + ed.getNR_Placa() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      FormataDataBean dataFormatada = new FormataDataBean();

      while (res.next()){

        EDIED edVolta = new EDIED();

        edVolta.setNR_Placa(res.getString("NR_PLACA"));
        edVolta.setNR_Frota(res.getString("NR_FROTA"));
        edVolta.setNM_Descricao(res.getString("TX_OBSERVACAO"));
        edVolta.setNM_Modelo(res.getString("NM_MODELO_VEICULO"));
        edVolta.setNM_Ano(res.getString("NR_ANO_VEICULO"));
        edVolta.setNR_Comprimento(res.getString("NR_COMPRIMENTO"));
        edVolta.setNR_Largura(res.getString("NR_LARGURA"));
        edVolta.setNR_Altura(res.getString("NR_ALTURA"));
        edVolta.setNR_Capacidade(res.getString("NR_PESO_LIMITE"));
        edVolta.setNM_Procedencia(res.getString("DM_PROCEDENCIA"));
        edVolta.setNM_Marca(res.getString("NM_MARCA_VEICULO"));
        edVolta.setNM_Tipo(res.getString("NM_TIPO_VEICULO"));
        edVolta.setVL_Compra(res.getDouble("VL_COMPRA"));
        edVolta.setPE_Depreciacao(res.getDouble("PE_DEPRECIACAO"));
        edVolta.setNM_Cidade_Veiculo(res.getString("NM_CIDADE_VEICULO"));
        edVolta.setNM_Estado_Veiculo(res.getString("CD_ESTADO_VEICULO"));
        edVolta.setNR_CNPJ_CPF(res.getString("NR_CNPJ_CPF_PROPRIETARIO"));
        edVolta.setNM_Proprietario(res.getString("NM_RAZAO_SOCIAL_PROPRIETARIO"));
        edVolta.setNM_Cidade(res.getString("NM_CIDADE_PROPRIETARIO"));
        edVolta.setNM_Estado(res.getString("CD_ESTADO_PROPRIETARIO"));

        list.add(edVolta);
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("gera_Arquivo_EDI(EDIED edComp)");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

}