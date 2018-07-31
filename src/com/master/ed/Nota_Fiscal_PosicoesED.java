/**
 * Classe para posicionar os campos na impressao de Notas Fiscais
 */
package com.master.ed;

import com.master.root.VeiculoBean;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Nota_Fiscal_PosicoesED
{

    public Nota_Fiscal_PosicoesED()  
    {
        parametro_FixoED = new Parametro_FixoED();
        ExecutaSQL = new ExecutaSQL();
        PessoaED = new PessoaED();
        VeiculoBean = new VeiculoBean();
        Nota_FiscalED = new Nota_FiscalED();
        ConhecimentoED = new ConhecimentoED();
    }

    public int getNr_Aliquota_Icms()
    {
        return nr_Aliquota_Icms;
    }

    public void setNr_Aliquota_Icms(int nr_Aliquota_Icms)
    {
        this.nr_Aliquota_Icms = nr_Aliquota_Icms;
    }

    public int getNr_Cd_Cliente_Palm()
    {
        return nr_Cd_Cliente_Palm;
    }

    public void setNr_Cd_Cliente_Palm(int nr_Cd_Cliente_Palm)
    {
        this.nr_Cd_Cliente_Palm = nr_Cd_Cliente_Palm;
    }

    public int getNr_Cd_Cep()
    {
        return nr_Cd_Cep;
    }

    public void setNr_Cd_Cep(int nr_Cd_Cep)
    {
        this.nr_Cd_Cep = nr_Cd_Cep;
    }

    public int getNr_Cd_Estado()
    {
        return nr_Cd_Estado;
    }

    public void setNr_Cd_Estado(int nr_Cd_Estado)
    {
        this.nr_Cd_Estado = nr_Cd_Estado;
    }

    public int getNr_Cd_Natureza()
    {
        return nr_Cd_Natureza;
    }

    public void setNr_Cd_Natureza(int nr_Cd_Natureza)
    {
        this.nr_Cd_Natureza = nr_Cd_Natureza;
    }

    public int getNr_Cd_Produto()
    {
        return nr_Cd_Produto;
    }

    public void setNr_Cd_Produto(int nr_Cd_Produto)
    {
        this.nr_Cd_Produto = nr_Cd_Produto;
    }

    public int getNr_Cd_Situacao_Tributaria()
    {
        return nr_Cd_Situacao_Tributaria;
    }

    public void setNr_Cd_Situacao_Tributaria(int nr_Cd_Situacao_Tributaria)
    {
        this.nr_Cd_Situacao_Tributaria = nr_Cd_Situacao_Tributaria;
    }

    public int getNr_Cd_Unidade()
    {
        return nr_Cd_Unidade;
    }

    public void setNr_Cd_Unidade(int nr_Cd_Unidade)
    {
        this.nr_Cd_Unidade = nr_Cd_Unidade;
    }

    public int getNr_Dm_Frete_Por_Conta()
    {
        return nr_Dm_Frete_Por_Conta;
    }

    public void setNr_Dm_Frete_Por_Conta(int nr_Dm_Frete_Por_Conta)
    {
        this.nr_Dm_Frete_Por_Conta = nr_Dm_Frete_Por_Conta;
    }

    public int getNr_Dt_Emissao()
    {
        return nr_Dt_Emissao;
    }

    public void setNr_Dt_Emissao(int nr_Dt_Emissao)
    {
        this.nr_Dt_Emissao = nr_Dt_Emissao;
    }

    public int getNr_Dt_Saida()
    {
        return nr_Dt_Saida;
    }

    public void setNr_Dt_Saida(int nr_Dt_Saida)
    {
        this.nr_Dt_Saida = nr_Dt_Saida;
    }

    public int getNr_Hr_Saida()
    {
        return nr_Hr_Saida;
    }

    public void setNr_Hr_Saida(int nr_Hr_Saida)
    {
        this.nr_Hr_Saida = nr_Hr_Saida;
    }

    public int getNr_Nf_De_Total()
    {
        return nr_Nf_De_Total;
    }

    public void setNr_Nf_De_Total(int nr_Nf_De_Total)
    {
        this.nr_Nf_De_Total = nr_Nf_De_Total;
    }

    public int getNr_nm_Bairro_Remetente()
    {
        return nr_nm_Bairro_Remetente;
    }

    public void setNr_nm_Bairro_Remetente(int nr_nm_Bairro_Remetente)
    {
        this.nr_nm_Bairro_Remetente = nr_nm_Bairro_Remetente;
    }

    public int getNr_Nm_Cidade_Remetente()
    {
        return nr_Nm_Cidade_Remetente;
    }

    public void setNr_Nm_Cidade_Remetente(int nr_Nm_Cidade_Remetente)
    {
        this.nr_Nm_Cidade_Remetente = nr_Nm_Cidade_Remetente;
    }

    public int getNr_nm_Endereco_Remetente()
    {
        return nr_nm_Endereco_Remetente;
    }

    public void setNr_nm_Endereco_Remetente(int nr_nm_Endereco_Remetente)
    {
        this.nr_nm_Endereco_Remetente = nr_nm_Endereco_Remetente;
    }

    public int getNr_Nm_Fone()
    {
        return nr_Nm_Fone;
    }

    public void setNr_Nm_Fone(int nr_Nm_Fone)
    {
        this.nr_Nm_Fone = nr_Nm_Fone;
    }

    public int getNr_nm_Ie_Remetente()
    {
        return nr_nm_Ie_Remetente;
    }

    public void setNr_nm_Ie_Remetente(int nr_nm_Ie_Remetente)
    {
        this.nr_nm_Ie_Remetente = nr_nm_Ie_Remetente;
    }

    public int getNr_Nm_Natureza()
    {
        return nr_Nm_Natureza;
    }

    public void setNr_Nm_Natureza(int nr_Nm_Natureza)
    {
        this.nr_Nm_Natureza = nr_Nm_Natureza;
    }

    public int getNr_Nm_Produto()
    {
        return nr_Nm_Produto;
    }

    public void setNr_Nm_Produto(int nr_Nm_Produto)
    {
        this.nr_Nm_Produto = nr_Nm_Produto;
    }

    public int getNr_Nm_Remetente()
    {
        return nr_Nm_Remetente;
    }

    public void setNr_Nm_Remetente(int nr_Nm_Remetente)
    {
        this.nr_Nm_Remetente = nr_Nm_Remetente;
    }

    public int getNr_Nm_Tipo()
    {
        return nr_Nm_Tipo;
    }

    public void setNr_Nm_Tipo(int nr_Nm_Tipo)
    {
        this.nr_Nm_Tipo = nr_Nm_Tipo;
    }

    public int getNr_Nm_Transportador()
    {
        return nr_Nm_Transportador;
    }

    public void setNr_Nm_Transportador(int nr_Nm_Transportador)
    {
        this.nr_Nm_Transportador = nr_Nm_Transportador;
    }

    public int getNr_Nr_Cnpj_Remetente()
    {
        return nr_Nr_Cnpj_Remetente;
    }

    public void setNr_Nr_Cnpj_Remetente(int nr_Nr_Cnpj_Remetente)
    {
        this.nr_Nr_Cnpj_Remetente = nr_Nr_Cnpj_Remetente;
    }

    public int getNr_Nr_Numero()
    {
        return nr_Nr_Numero;
    }

    public void setNr_Nr_Numero(int nr_Nr_Numero)
    {
        this.nr_Nr_Numero = nr_Nr_Numero;
    }

    public int getNr_Nr_Numero_canhoto()
    {
        return Nr_Nr_Numero_canhoto;
    }

    public void setNr_Nr_Numero_canhoto(int nr_Nr_Numero_canhoto)
    {
        Nr_Nr_Numero_canhoto = nr_Nr_Numero_canhoto;
    }

    public int getNr_Nr_Numero_Canhoto_Superior() {
		return Nr_Nr_Numero_Canhoto_Superior;
	}

	public void setNr_Nr_Numero_Canhoto_Superior(int nr_Nr_Numero_Canhoto_Superior) {
		Nr_Nr_Numero_Canhoto_Superior = nr_Nr_Numero_Canhoto_Superior;
	}

	public int getNr_Nr_Peso_Liquido()
    {
        return nr_Nr_Peso_Liquido;
    }

    public void setNr_Nr_Peso_Liquido(int nr_Nr_Peso_Liquido)
    {
        this.nr_Nr_Peso_Liquido = nr_Nr_Peso_Liquido;
    }

    public int getNr_Nr_Peso_Bruto()
    {
        return nr_Nr_Peso_Bruto;
    }

    public void setNr_Nr_Peso_Bruto(int nr_Nr_Peso_Bruto)
    {
        this.nr_Nr_Peso_Bruto = nr_Nr_Peso_Bruto;
    }

    public int getNr_Nr_Qt_Itens_Possiveis()
    {
        return nr_Nr_Qt_Itens_Possiveis;
    }

    public void setNr_Nr_Qt_Itens_Possiveis(int nr_Nr_Qt_Itens_Possiveis)
    {
        this.nr_Nr_Qt_Itens_Possiveis = nr_Nr_Qt_Itens_Possiveis;
    }

    public int getNr_Nr_Quantidade()
    {
        return nr_Nr_Quantidade;
    }

    public void setNr_Nr_Quantidade(int nr_Nr_Quantidade)
    {
        this.nr_Nr_Quantidade = nr_Nr_Quantidade;
    }

    public int getNr_Tx_Observacao()
    {
        return nr_Tx_Observacao;
    }

    public void setNr_Tx_Observacao(int nr_Tx_Observacao)
    {
        this.nr_Tx_Observacao = nr_Tx_Observacao;
    }

    public int getNr_Tx_Placa()
    {
        return nr_Tx_Placa;
    }

    public void setNr_Tx_Placa(int nr_Tx_Placa)
    {
        this.nr_Tx_Placa = nr_Tx_Placa;
    }

    public int getNr_Tx_SubTotal_Base_ICMS()
    {
        return nr_Tx_SubTotal_Base_ICMS;
    }

    public void setNr_Tx_SubTotal_Base_ICMS(int nr_Tx_SubTotal_Base_ICMS)
    {
        this.nr_Tx_SubTotal_Base_ICMS = nr_Tx_SubTotal_Base_ICMS;
    }

    public int getNr_Tx_SubTotal_Subtotal()
    {
        return nr_Tx_SubTotal_Subtotal;
    }

    public void setNr_Tx_SubTotal_Subtotal(int nr_Tx_SubTotal_Subtotal)
    {
        this.nr_Tx_SubTotal_Subtotal = nr_Tx_SubTotal_Subtotal;
    }

    public int getNr_Tx_SubTotal_Valor_ICMS()
    {
        return nr_Tx_SubTotal_Valor_ICMS;
    }

    public void setNr_Tx_SubTotal_Valor_ICMS(int nr_Tx_SubTotal_Valor_ICMS)
    {
        this.nr_Tx_SubTotal_Valor_ICMS = nr_Tx_SubTotal_Valor_ICMS;
    }

    public int getNr_Vl_Base_Calculo_ICMS()
    {
        return nr_Vl_Base_Calculo_ICMS;
    }

    public void setNr_Vl_Base_Calculo_ICMS(int nr_Vl_Base_Calculo_ICMS)
    {
        this.nr_Vl_Base_Calculo_ICMS = nr_Vl_Base_Calculo_ICMS;
    }

    public int getNr_Vl_Base_Calculo_ICMS_Subst()
    {
        return nr_Vl_Base_Calculo_ICMS_Subst;
    }

    public void setNr_Vl_Base_Calculo_ICMS_Subst(int nr_Vl_Base_Calculo_ICMS_Subst)
    {
        this.nr_Vl_Base_Calculo_ICMS_Subst = nr_Vl_Base_Calculo_ICMS_Subst;
    }

    public int getNr_Nr_Vl_Despesas_Acessorias()
    {
        return nr_Nr_Vl_Despesas_Acessorias;
    }

    public void setNr_Nr_Vl_Despesas_Acessorias(int nr_Vl_Despesas_Acessorias)
    {
        nr_Nr_Vl_Despesas_Acessorias = nr_Vl_Despesas_Acessorias;
    }

    public int getNr_Nr_Vl_Total_Frete()
    {
        return nr_Nr_Vl_Total_Frete;
    }

    public void setNr_Nr_Vl_Total_Frete(int nr_Vl_Frete)
    {
        nr_Nr_Vl_Total_Frete = nr_Vl_Frete;
    }

    public int getNr_Vl_ICMS()
    {
        return nr_Vl_ICMS;
    }

    public void setNr_Vl_ICMS(int nr_Vl_ICMS)
    {
        this.nr_Vl_ICMS = nr_Vl_ICMS;
    }

    public int getNr_Vl_ICMS_Subst()
    {
        return nr_Vl_ICMS_Subst;
    }

    public void setNr_Vl_ICMS_Subst(int nr_Vl_ICMS_Subst)
    {
        this.nr_Vl_ICMS_Subst = nr_Vl_ICMS_Subst;
    }

    public int getNr_Vl_Item()
    {
        return nr_Vl_Item;
    }

    public void setNr_Vl_Item(int nr_Vl_Item)
    {
        this.nr_Vl_Item = nr_Vl_Item;
    }

    public int getNr_Nr_Vl_Seguro()
    {
        return nr_Nr_Vl_Seguro;
    }

    public void setNr_Nr_Vl_Seguro(int nr_Vl_Seguro)
    {
        nr_Nr_Vl_Seguro = nr_Vl_Seguro;
    }

    public int getNr_Vl_SubTotal_Base_ICMS()
    {
        return nr_Vl_SubTotal_Base_ICMS;
    }

    public void setNr_Vl_SubTotal_Base_ICMS(int nr_Vl_SubTotal_Base_ICMS)
    {
        this.nr_Vl_SubTotal_Base_ICMS = nr_Vl_SubTotal_Base_ICMS;
    }

    public int getNr_Vl_SubTotal_Subtotal()
    {
        return nr_Vl_SubTotal_Subtotal;
    }

    public void setNr_Vl_SubTotal_Subtotal(int nr_Vl_SubTotal_Subtotal)
    {
        this.nr_Vl_SubTotal_Subtotal = nr_Vl_SubTotal_Subtotal;
    }

    public int getNr_Vl_SubTotal_Valor_ICMS()
    {
        return nr_Vl_SubTotal_Valor_ICMS;
    }

    public void setNr_Vl_SubTotal_Valor_ICMS(int nr_Vl_SubTotal_Valor_ICMS)
    {
        this.nr_Vl_SubTotal_Valor_ICMS = nr_Vl_SubTotal_Valor_ICMS;
    }

    public int getNr_Vl_Total()
    {
        return nr_Vl_Total;
    }

    public void setNr_Vl_Total(int nr_Vl_Total)
    {
        this.nr_Vl_Total = nr_Vl_Total;
    }

    public int getNr_Nr_Vl_Total_Ipi()
    {
        return nr_Nr_Vl_Total_Ipi;
    }

    public void setNr_Nr_Vl_Total_Ipi(int nr_Vl_Total_Ipi)
    {
        nr_Nr_Vl_Total_Ipi = nr_Vl_Total_Ipi;
    }

    public int getNr_Vl_Total_Produtos()
    {
        return nr_Vl_Total_Produtos;
    }

    public void setNr_Vl_Total_Produtos(int nr_Vl_Total_Produtos)
    {
        this.nr_Vl_Total_Produtos = nr_Vl_Total_Produtos;
    }

    public int getNr_Vl_Unitario()
    {
        return nr_Vl_Unitario;
    }

    public void setNr_Vl_Unitario(int nr_Vl_Unitario)
    {
        this.nr_Vl_Unitario = nr_Vl_Unitario;
    }

    public int getNr_Tx_Saldo_Transporte()
    {
        return nr_Tx_Saldo_Transporte;
    }

    public void setNr_Tx_Saldo_Transporte(int nr_Tx_Saldo_Transporte)
    {
        this.nr_Tx_Saldo_Transporte = nr_Tx_Saldo_Transporte;
    }

    public int getNr_Vl_Saldo_Transporte()
    {
        return nr_Vl_Saldo_Transporte;
    }

    public void setNr_Vl_Saldo_Transporte(int nr_Vl_Saldo_Transporte)
    {
        this.nr_Vl_Saldo_Transporte = nr_Vl_Saldo_Transporte;
    }

    public int getNr_Tx_Desconto_Especial()
    {
        return nr_Tx_Desconto_Especial;
    }

    public void setNr_Tx_Desconto_Especial(int nr_Tx_Desconto_Especial)
    {
        this.nr_Tx_Desconto_Especial = nr_Tx_Desconto_Especial;
    }

    public int getNr_Vl_Desconto_Especial()
    {
        return nr_Vl_Desconto_Especial;
    }

    public void setNr_Vl_Desconto_Especial(int nr_Vl_Desconto_Especial)
    {
        this.nr_Vl_Desconto_Especial = nr_Vl_Desconto_Especial;
    }

    public int getNr_Tx_Total_Desconto()
    {
        return nr_Tx_Total_Desconto;
    }

    public void setNr_Tx_Total_Desconto(int nr_Tx_Total_Desconto)
    {
        this.nr_Tx_Total_Desconto = nr_Tx_Total_Desconto;
    }

    public int getNr_Vl_Total_Desconto()
    {
        return nr_Vl_Total_Desconto;
    }

    public void setNr_Vl_Total_Desconto(int nr_Vl_Total_Desconto)
    {
        this.nr_Vl_Total_Desconto = nr_Vl_Total_Desconto;
    }

    public int getNr_Tx_Saldo_a_Transportar()
    {
        return nr_Tx_Saldo_a_Transportar;
    }

    public void setNr_Tx_Saldo_a_Transportar(int nr_Tx_Saldo_a_Transportar)
    {
        this.nr_Tx_Saldo_a_Transportar = nr_Tx_Saldo_a_Transportar;
    }

    public int getNr_Vl_Saldo_a_Transportar()
    {
        return nr_Vl_Saldo_a_Transportar;
    }

    public void setNr_Vl_Saldo_a_Transportar(int nr_Vl_Saldo_a_Transportar)
    {
        this.nr_Vl_Saldo_a_Transportar = nr_Vl_Saldo_a_Transportar;
    }

    public int getNr_margemL()
    {
        return nr_margemL;
    }

    public void setNr_margemL(int nr_margemL)
    {
        this.nr_margemL = nr_margemL;
    }

    public int getNr_maxItens()
    {
        return nr_maxItens;
    }

    public void setNr_maxItens(int nr_maxItens)
    {
        this.nr_maxItens = nr_maxItens;
    }

    public String getNm_Razao_Social_Empresa()
    {
        return nm_Razao_Social_Empresa;
    }

    public void setNm_Razao_Social_Empresa(String nm_Razao_Social_Empresa)
    {
        this.nm_Razao_Social_Empresa = nm_Razao_Social_Empresa;
    }

    public int getNr_nm_Razao_Social_Empresa()
    {
        return nr_nm_Razao_Social_Empresa;
    }

    public void setNr_nm_Razao_Social_Empresa(int nr_nm_Razao_Social_Empresa)
    {
        this.nr_nm_Razao_Social_Empresa = nr_nm_Razao_Social_Empresa;
    }

    public int getNr_Cd_Estado_Transportador()
    {
        return nr_Cd_Estado_Transportador;
    }

    public void setNr_Cd_Estado_Transportador(int nr_Cd_Estado_Transportador)
    {
        this.nr_Cd_Estado_Transportador = nr_Cd_Estado_Transportador;
    }

    public int getNr_Nm_Cidade_Transportador()
    {
        return nr_Nm_Cidade_Transportador;
    }

    public void setNr_Nm_Cidade_Transportador(int nr_Nm_Cidade_Transportador)
    {
        this.nr_Nm_Cidade_Transportador = nr_Nm_Cidade_Transportador;
    }

    public int getNr_Nm_Endereco_Transportador()
    {
        return nr_Nm_Endereco_Transportador;
    }

    public void setNr_Nm_Endereco_Transportador(int nr_Nm_Endereco_Transportador)
    {
        this.nr_Nm_Endereco_Transportador = nr_Nm_Endereco_Transportador;
    }

    public int getNr_Nr_CNPJ_Transportador()
    {
        return nr_Nr_CNPJ_Transportador;
    }

    public void setNr_Nr_CNPJ_Transportador(int nr_Nr_CNPJ_Transportador)
    {
        this.nr_Nr_CNPJ_Transportador = nr_Nr_CNPJ_Transportador;
    }

    public int getNr_Nm_IE_Transportador()
    {
        return nr_Nm_IE_Transportador;
    }

    public void setNr_Nm_IE_Transportador(int nr_Nm_IE_Transportador)
    {
        this.nr_Nm_IE_Transportador = nr_Nm_IE_Transportador;
    }

    public int getNr_Nm_UF_Transportador()
    {
        return nr_Nm_UF_Transportador;
    }

    public void setNr_Nm_UF_Transportador(int nr_Nm_UF_Transportador)
    {
        this.nr_Nm_UF_Transportador = nr_Nm_UF_Transportador;
    }

    public int getNr_Nr_Especie()
    {
        return nr_Nr_Especie;
    }

    public void setNr_Nr_Especie(int nr_Nr_Especie)
    {
        this.nr_Nr_Especie = nr_Nr_Especie;
    }

    public String getNm_Especie() {
		return nm_Especie;
	}

	public void setNm_Especie(String nm_Especie) {
		this.nm_Especie = nm_Especie;
	}

	public int getNr_Nr_Marca() {
		return nr_Nr_Marca;
	}

	public void setNr_Nr_Marca(int nr_Nr_Marca) {
		this.nr_Nr_Marca = nr_Nr_Marca;
	}

	public String getNm_Marca() {
		return nm_Marca;
	}

	public void setNm_Marca(String nm_Marca) {
		this.nm_Marca = nm_Marca;
	}

	public int getNr_Pulo_1()
    {
        return nr_Pulo_1;
    }

    public void setNr_Pulo_1(int nr_Pulo_1)
    {
        this.nr_Pulo_1 = nr_Pulo_1;
    }

    public int getNr_Pulo_2()
    {
        return nr_Pulo_2;
    }

    public void setNr_Pulo_2(int nr_Pulo_2)
    {
        this.nr_Pulo_2 = nr_Pulo_2;
    }

    public int getNr_Pulo_3()
    {
        return nr_Pulo_3;
    }

    public void setNr_Pulo_3(int nr_Pulo_3)
    {
        this.nr_Pulo_3 = nr_Pulo_3;
    }

    public int getNr_Pulo_4()
    {
        return nr_Pulo_4;
    }

    public void setNr_Pulo_4(int nr_Pulo_4)
    {
        this.nr_Pulo_4 = nr_Pulo_4;
    }

    public int getNr_Pulo_5()
    {
        return nr_Pulo_5;
    }

    public void setNr_Pulo_5(int nr_Pulo_5)
    {
        this.nr_Pulo_5 = nr_Pulo_5;
    }

    public int getNr_Pulo_6()
    {
        return nr_Pulo_6;
    }

    public void setNr_Pulo_6(int nr_Pulo_6)
    {
        this.nr_Pulo_6 = nr_Pulo_6;
    }

    public int getNr_Pulo_7()
    {
        return nr_Pulo_7;
    }

    public void setNr_Pulo_7(int nr_Pulo_7)
    {
        this.nr_Pulo_7 = nr_Pulo_7;
    }

    public int getNr_Pulo_8()
    {
        return nr_Pulo_8;
    }

    public void setNr_Pulo_8(int nr_Pulo_8)
    {
        this.nr_Pulo_8 = nr_Pulo_8;
    }

    public int getNr_Pulo_9()
    {
        return nr_Pulo_9;
    }

    public void setNr_Pulo_9(int nr_Pulo_9)
    {
        this.nr_Pulo_9 = nr_Pulo_9;
    }

    public int getNr_Pulo_10()
    {
        return nr_Pulo_10;
    }

    public void setNr_Pulo_10(int nr_Pulo_10)
    {
        this.nr_Pulo_10 = nr_Pulo_10;
    }

    public int getNr_Pulo_11()
    {
        return nr_Pulo_11;
    }

    public void setNr_Pulo_11(int nr_Pulo_11)
    {
        this.nr_Pulo_11 = nr_Pulo_11;
    }

    public int getNr_Pulo_12()
    {
        return nr_Pulo_12;
    }

    public void setNr_Pulo_12(int nr_Pulo_12)
    {
        this.nr_Pulo_12 = nr_Pulo_12;
    }

    public int getNr_Pulo_13()
    {
        return nr_Pulo_13;
    }

    public void setNr_Pulo_13(int nr_Pulo_13)
    {
        this.nr_Pulo_13 = nr_Pulo_13;
    }

    public int getNr_Pulo_14()
    {
        return nr_Pulo_14;
    }

    public void setNr_Pulo_14(int nr_Pulo_14)
    {
        this.nr_Pulo_14 = nr_Pulo_14;
    }

    public int getNr_Pulo_15()
    {
        return nr_Pulo_15;
    }

    public void setNr_Pulo_15(int nr_Pulo_15)
    {
        this.nr_Pulo_15 = nr_Pulo_15;
    }

    public int getNr_Pulo_16()
    {
        return nr_Pulo_16;
    }

    public void setNr_Pulo_16(int nr_Pulo_16)
    {
        this.nr_Pulo_16 = nr_Pulo_16;
    }

    public int getNr_Pulo_17()
    {
        return nr_Pulo_17;
    }

    public void setNr_Pulo_17(int nr_Pulo_17)
    {
        this.nr_Pulo_17 = nr_Pulo_17;
    }

    public int getNr_Pulo_Entre_17_18() {
		return nr_Pulo_Entre_17_18;
	}

	public void setNr_Pulo_Entre_17_18(int nr_Pulo_Entre_17_18) {
		this.nr_Pulo_Entre_17_18 = nr_Pulo_Entre_17_18;
	}

	public int getNr_Pulo_18()
    {
        return nr_Pulo_18;
    }

    public void setNr_Pulo_18(int nr_Pulo_18)
    {
        this.nr_Pulo_18 = nr_Pulo_18;
    }

    public int getNr_Pulo_19()
    {
        return nr_Pulo_19;
    }

    public void setNr_Pulo_19(int nr_Pulo_19)
    {
        this.nr_Pulo_19 = nr_Pulo_19;
    }

    public int getNr_Pulo_20()
    {
        return nr_Pulo_20;
    }

    public void setNr_Pulo_20(int nr_Pulo_20)
    {
        this.nr_Pulo_20 = nr_Pulo_20;
    }

    public int getNr_Pulo_21()
    {
        return nr_Pulo_21;
    }

    public void setNr_Pulo_21(int nr_Pulo_21)
    {
        this.nr_Pulo_21 = nr_Pulo_21;
    }

    public int getNr_Pulo_22()
    {
        return nr_Pulo_22;
    }

    public void setNr_Pulo_22(int nr_Pulo_22)
    {
        this.nr_Pulo_22 = nr_Pulo_22;
    }

    public int getNr_Pulo_23()
    {
        return nr_Pulo_23;
    }

    public void setNr_Pulo_23(int nr_Pulo_23)
    {
        this.nr_Pulo_23 = nr_Pulo_23;
    }

	public int getNr_Pulo_Entre_23_24() {
		return nr_Pulo_Entre_23_24;
	}

	public void setNr_Pulo_Entre_23_24(int nr_Pulo_Entre_23_24) {
		this.nr_Pulo_Entre_23_24 = nr_Pulo_Entre_23_24;
	}

	public int getNr_Pulo_24()
    {
        return nr_Pulo_24;
    }

    public void setNr_Pulo_24(int nr_Pulo_24)
    {
        this.nr_Pulo_24 = nr_Pulo_24;
    }

    public int getNr_Pulo_Entre_24_25() {
		return nr_Pulo_Entre_24_25;
	}

	public void setNr_Pulo_Entre_24_25(int nr_Pulo_Entre_24_25) {
		this.nr_Pulo_Entre_24_25 = nr_Pulo_Entre_24_25;
	}

	public ExecutaSQL getExecutaSQL()
    {
        return ExecutaSQL;
    }

    public void setExecutaSQL(ExecutaSQL executaSQL)
    {
        ExecutaSQL = executaSQL;
    }

    public int getNr_Pulo_25()
    {
        return nr_Pulo_25;
    }

    public void setNr_Pulo_25(int nr_Pulo_25)
    {
        this.nr_Pulo_25 = nr_Pulo_25;
    }

    public int getNr_Pulo_0()
    {
        return nr_Pulo_0;
    }

    public void setNr_Pulo_0(int nr_Pulo_0)
    {
        this.nr_Pulo_0 = nr_Pulo_0;
    }

    public int getNr_Pulo_Entre_0_1() {
		return nr_Pulo_Entre_0_1;
	}

	public void setNr_Pulo_Entre_0_1(int nr_Pulo_Entre_0_1) {
		this.nr_Pulo_Entre_0_1 = nr_Pulo_Entre_0_1;
	}

	public int getNr_Nr_Volumes() {
		return nr_Nr_Volumes;
	}

	public void setNr_Nr_Volumes(int nr_Nr_Volumes) {
		this.nr_Nr_Volumes = nr_Nr_Volumes;
	}

    public int getNr_Asterisco1() {
		return nr_Asterisco1;
	}

	public void setNr_Asterisco1(int nr_Asterisco1) {
		this.nr_Asterisco1 = nr_Asterisco1;
	}

	public int getNr_Asterisco10() {
		return nr_Asterisco10;
	}

	public void setNr_Asterisco10(int nr_Asterisco10) {
		this.nr_Asterisco10 = nr_Asterisco10;
	}

	public int getNr_Asterisco2() {
		return nr_Asterisco2;
	}

	public void setNr_Asterisco2(int nr_Asterisco2) {
		this.nr_Asterisco2 = nr_Asterisco2;
	}

	public int getNr_Asterisco3() {
		return nr_Asterisco3;
	}

	public void setNr_Asterisco3(int nr_Asterisco3) {
		this.nr_Asterisco3 = nr_Asterisco3;
	}

	public int getNr_Asterisco4() {
		return nr_Asterisco4;
	}

	public void setNr_Asterisco4(int nr_Asterisco4) {
		this.nr_Asterisco4 = nr_Asterisco4;
	}

	public int getNr_Asterisco5() {
		return nr_Asterisco5;
	}

	public void setNr_Asterisco5(int nr_Asterisco5) {
		this.nr_Asterisco5 = nr_Asterisco5;
	}

	public int getNr_Asterisco6() {
		return nr_Asterisco6;
	}

	public void setNr_Asterisco6(int nr_Asterisco6) {
		this.nr_Asterisco6 = nr_Asterisco6;
	}

	public int getNr_Asterisco7() {
		return nr_Asterisco7;
	}

	public void setNr_Asterisco7(int nr_Asterisco7) {
		this.nr_Asterisco7 = nr_Asterisco7;
	}

	public int getNr_Asterisco8() {
		return nr_Asterisco8;
	}

	public void setNr_Asterisco8(int nr_Asterisco8) {
		this.nr_Asterisco8 = nr_Asterisco8;
	}

	public int getNr_Asterisco9() {
		return nr_Asterisco9;
	}

	public void setNr_Asterisco9(int nr_Asterisco9) {
		this.nr_Asterisco9 = nr_Asterisco9;
	}

	public int getNr_Trunca00() {
		return nr_Trunca00;
	}

	public void setNr_Trunca00(int nr_Trunca00) {
		this.nr_Trunca00 = nr_Trunca00;
	}

	public int getNr_Trunca01() {
		return nr_Trunca01;
	}

	public void setNr_Trunca01(int nr_Trunca01) {
		this.nr_Trunca01 = nr_Trunca01;
	}

	public int getNr_Trunca02() {
		return nr_Trunca02;
	}

	public void setNr_Trunca02(int nr_Trunca02) {
		this.nr_Trunca02 = nr_Trunca02;
	}

	public int getNr_Trunca03() {
		return nr_Trunca03;
	}

	public void setNr_Trunca03(int nr_Trunca03) {
		this.nr_Trunca03 = nr_Trunca03;
	}

	public int getNr_Trunca04() {
		return nr_Trunca04;
	}

	public void setNr_Trunca04(int nr_Trunca04) {
		this.nr_Trunca04 = nr_Trunca04;
	}

	public int getNr_Trunca05() {
		return nr_Trunca05;
	}

	public void setNr_Trunca05(int nr_Trunca05) {
		this.nr_Trunca05 = nr_Trunca05;
	}

	public int getNr_Trunca06() {
		return nr_Trunca06;
	}

	public void setNr_Trunca06(int nr_Trunca06) {
		this.nr_Trunca06 = nr_Trunca06;
	}

	public int getNr_Trunca07() {
		return nr_Trunca07;
	}

	public void setNr_Trunca07(int nr_Trunca07) {
		this.nr_Trunca07 = nr_Trunca07;
	}

	public int getNr_Trunca08() {
		return nr_Trunca08;
	}

	public void setNr_Trunca08(int nr_Trunca08) {
		this.nr_Trunca08 = nr_Trunca08;
	}

	public int getNr_Trunca09() {
		return nr_Trunca09;
	}

	public void setNr_Trunca09(int nr_Trunca09) {
		this.nr_Trunca09 = nr_Trunca09;
	}

	public int getNr_Trunca10() {
		return nr_Trunca10;
	}

	public void setNr_Trunca10(int nr_Trunca10) {
		this.nr_Trunca10 = nr_Trunca10;
	}

	public int getNr_Trunca11() {
		return nr_Trunca11;
	}

	public void setNr_Trunca11(int nr_Trunca11) {
		this.nr_Trunca11 = nr_Trunca11;
	}

	public int getNr_Trunca12() {
		return nr_Trunca12;
	}

	public void setNr_Trunca12(int nr_Trunca12) {
		this.nr_Trunca12 = nr_Trunca12;
	}

	public int getCd_Empresa() {
		return cd_Empresa;
	}

	public void setCd_Empresa(int cd_Empresa) {
		this.cd_Empresa = cd_Empresa;
	}

	public void queEmpresa(String emp)
    {
        if("ASTRAL".equals(emp))
            setAstral();
        else
	        if("DAUDT".equals(emp))
	            setDaudt();
        else
	        if("BORRUSSIA".equals(emp))
	            setBorrussia();
	    else
		    if("MIRO".equals(emp))
		         setMiro();

    }

    public void setAstral()
    {
        setNr_margemL(0);
        setNr_maxItens(14);
        setNm_Razao_Social_Empresa(" ");
        setNr_nm_Razao_Social_Empresa(11);

        setNr_Nr_Numero(132);
        setNr_Nf_De_Total(79);//
        setNr_Nm_Tipo(96);//

        setNr_Nm_Natureza(8);//
        setNr_Cd_Natureza(53);//
        setNr_Nr_Numero_Canhoto_Superior(145);
        setNr_Nm_Remetente(9);//
        setNr_Cd_Cliente_Palm(62);
        setNr_Nr_Cnpj_Remetente(95);
        setNr_Dt_Emissao(126);
        setNr_nm_Endereco_Remetente(9);//
        setNr_nm_Bairro_Remetente(73);
        setNr_Cd_Cep(103);//
        setNr_Dt_Saida(126);//
        setNr_Nm_Cidade_Remetente(9);//
        setNr_Nm_Fone(58);//
        setNr_Cd_Estado(87);//
        setNr_nm_Ie_Remetente(95);//
        setNr_Hr_Saida(126);//


        setNr_Cd_Produto(9);//
        setNr_Nm_Produto(23);//
        setNr_Cd_Situacao_Tributaria(95);//
        setNr_Cd_Unidade(100);//
        setNr_Nr_Quantidade(111);//
        setNr_Vl_Unitario(121);//
        setNr_Vl_Item(136);//
        setNr_Aliquota_Icms(140);//

        setNr_Tx_SubTotal_Base_ICMS(11);
        setNr_Vl_SubTotal_Base_ICMS(28);
        setNr_Tx_SubTotal_Valor_ICMS(48);
        setNr_Vl_SubTotal_Valor_ICMS(66);
        setNr_Tx_SubTotal_Subtotal(80);
        setNr_Vl_SubTotal_Subtotal(100);

        setNr_Vl_Base_Calculo_ICMS(21);//
        setNr_Vl_ICMS(45);//
        setNr_Vl_Base_Calculo_ICMS_Subst(70);//
        setNr_Vl_ICMS_Subst(95);//
        setNr_Vl_Total_Produtos(130);//

        setNr_Nr_Vl_Total_Frete(17);//
        setNr_Nr_Vl_Seguro(41);//
        setNr_Nr_Vl_Despesas_Acessorias(66);//
        setNr_Nr_Vl_Total_Ipi(91);//
        setNr_Vl_Total(126);//

        setNr_Asterisco1(15);
        setNr_Asterisco2(40);
        setNr_Asterisco3(64);
        setNr_Asterisco4(90);
        setNr_Asterisco5(114);

        setNr_Asterisco6(15);
        setNr_Asterisco7(40);
        setNr_Asterisco8(64);
        setNr_Asterisco9(90);
        setNr_Asterisco10(114);

        setNr_Nm_Transportador(9);//
        setNr_Dm_Frete_Por_Conta(86);//
        setNr_Tx_Placa(100);//
        setNr_Nr_CNPJ_Transportador(114);//
        setNr_Nm_Endereco_Transportador(9);//
        setNr_Nm_UF_Transportador(109);//
        setNr_Nm_IE_Transportador(114);//
        setNr_Nr_Qt_Itens_Possiveis(9);//
        setNr_Nm_Cidade_Transportador(70);//

        setNr_Nr_Especie(29);//
        setNm_Especie("EMB.GERAL");
        setNr_Nr_Marca(35);
        setNm_Marca("");
        setNr_Nr_Peso_Bruto(109);//
        setNr_Nr_Peso_Liquido(126);//

        setNr_Nr_Volumes(9);//

        setNr_Tx_Saldo_a_Transportar(13);
        setNr_Vl_Saldo_a_Transportar(129);
        setNr_Tx_Total_Desconto(1);
        setNr_Vl_Total_Desconto(40);

        setNr_Tx_Observacao(10);
        setNr_Nr_Numero_canhoto(114);

        //MENSAGEM 2
        setNr_Trunca03(66);//
        setNr_Trunca04(132);//
        setNr_Trunca05(0);
        setNr_Trunca06(0);
        //TRUNCA NATUREZA OPERA��O
        setNr_Trunca05(40);

        setNr_Pulo_0(1);
        setNr_Pulo_Entre_0_1(0); // ENTRE N�MERO DA NF E TIPO DE NOTA FISCAL
        setNr_Pulo_1(4);//
        setNr_Pulo_2(2);
        setNr_Pulo_3(2);
        setNr_Pulo_4(2);
        setNr_Pulo_5(4);
        setNr_Pulo_6(0);
        setNr_Pulo_7(1);
        setNr_Pulo_8(1);
        setNr_Pulo_9(1);
        setNr_Pulo_10(1);
        setNr_Pulo_11(13);//
        setNr_Pulo_12(0);
        setNr_Pulo_13(0);
        setNr_Pulo_14(0);
        setNr_Pulo_15(0);
        setNr_Pulo_16(3);
        setNr_Pulo_17(1);
        setNr_Pulo_Entre_17_18(2);//PULO ENTRE LINHA DO ENDERE�O TRANSPORTADOR E O QUANT. TRANSPORTADOR;        
        setNr_Pulo_18(1);
        setNr_Pulo_19(1);
        setNr_Pulo_20(0);//1(ANTES) PULO ENTRE Mensagem Fiscal E COMPLEMENTO;    
        setNr_Pulo_21(2);
        setNr_Pulo_22(2);
        setNr_Pulo_23(10);
        setNr_Pulo_Entre_23_24(0); // PULO ENTRE ITENS E C�LCULO DO IMPOSTO        
        setNr_Pulo_24(2);
        setNr_Pulo_Entre_24_25(13);//PULO ENTRE ASTERISCOS E O N�MERO DA NF DO CANHOTO
        setNr_Pulo_25(7);

        setCd_Empresa(0);
        setComplementada(true);
    }

    public void setDaudt()
    {
        setNr_margemL(0);
        setNr_maxItens(14);
        setNm_Razao_Social_Empresa("*Novo Nome Empresarial: G.R. Daudt Transportes LTDA.");
        setNr_nm_Razao_Social_Empresa(12);
        setNr_Nr_Numero(111);
        setNr_Nf_De_Total(59);
        setNr_Nm_Tipo(79);
        setNr_Nm_Natureza(0);
        setNr_Cd_Natureza(40);
        setNr_Nm_Remetente(0);
        setNr_Cd_Cliente_Palm(62);
        setNr_Nr_Cnpj_Remetente(75);
        setNr_Dt_Emissao(106);
        setNr_nm_Endereco_Remetente(0);
        setNr_nm_Bairro_Remetente(59);
        setNr_Cd_Cep(90);
        setNr_Dt_Saida(106);
        setNr_Nm_Cidade_Remetente(0);
        setNr_Nm_Fone(38);
        setNr_Cd_Estado(67);
        setNr_nm_Ie_Remetente(75);
        setNr_Hr_Saida(106);
        setNr_Nr_Qt_Itens_Possiveis(0);
        setNr_Cd_Produto(1);
        setNr_Nm_Produto(12);
        setNr_Cd_Situacao_Tributaria(56);
        setNr_Cd_Unidade(63);
        setNr_Nr_Quantidade(74);
        setNr_Vl_Unitario(89);
        setNr_Vl_Item(104);
        setNr_Aliquota_Icms(121);
        setNr_Tx_SubTotal_Base_ICMS(12);
        setNr_Vl_SubTotal_Base_ICMS(28);
        setNr_Tx_SubTotal_Valor_ICMS(48);
        setNr_Vl_SubTotal_Valor_ICMS(66);
        setNr_Tx_SubTotal_Subtotal(80);
        setNr_Vl_SubTotal_Subtotal(100);
        setNr_Vl_Base_Calculo_ICMS(21);
        setNr_Vl_ICMS(39);
        setNr_Vl_Base_Calculo_ICMS_Subst(59);
        setNr_Vl_ICMS_Subst(82);
        setNr_Vl_Total_Produtos(114);
        setNr_Nr_Vl_Total_Frete(17);
        setNr_Nr_Vl_Seguro(35);
        setNr_Nr_Vl_Despesas_Acessorias(55);
        setNr_Nr_Vl_Total_Ipi(78);
        setNr_Vl_Total(104);
        setNr_Nm_Transportador(0);
        setNr_Dm_Frete_Por_Conta(68);
        setNr_Tx_Placa(62);
        setNr_Nr_CNPJ_Transportador(104);
        setNr_Nm_Endereco_Transportador(0);
        setNr_Nm_UF_Transportador(89);
        setNr_Nm_IE_Transportador(104);
        setNr_Nm_Cidade_Transportador(57);
        setNr_Nr_Volumes(0);
        setNr_Nr_Especie(14);
        setNm_Especie("EMB.GERAL");
        setNr_Nr_Marca(35);
        setNm_Marca("");
        setNr_Nr_Peso_Bruto(85);
        setNr_Nr_Peso_Liquido(113);
        setNr_Tx_Saldo_a_Transportar(13);
        setNr_Vl_Saldo_a_Transportar(129);
        setNr_Tx_Total_Desconto(1);
        setNr_Vl_Total_Desconto(40);
        setNr_Nr_Numero_canhoto(131);

        setNr_Asterisco1(1);
        setNr_Asterisco2(25);
        setNr_Asterisco3(44);
        setNr_Asterisco4(62);
        setNr_Asterisco5(85);

        setNr_Asterisco6(1);
        setNr_Asterisco7(25);
        setNr_Asterisco8(44);
        setNr_Asterisco9(62);
        setNr_Asterisco10(85);

        //MENSAGEM 2
        setNr_Trunca03(41);
        setNr_Trunca04(82);
        setNr_Trunca05(0);
        setNr_Trunca06(0);
        //TRUNCA NATUREZA OPERA��O
        setNr_Trunca05(32);


        setNr_Pulo_0(1);
        setNr_Pulo_Entre_0_1(0); // ENTRE N�MERO DA NF E TIPO DE NOTA FISCAL
        setNr_Pulo_1(5);
        setNr_Pulo_2(3);
        setNr_Pulo_3(2);
        setNr_Pulo_4(3);
        setNr_Pulo_5(5);
        setNr_Pulo_6(0);
        setNr_Pulo_7(1);
        setNr_Pulo_8(1);
        setNr_Pulo_9(1);
        setNr_Pulo_10(1);
        setNr_Pulo_11(2);
        setNr_Pulo_12(0);
        setNr_Pulo_13(0);
        setNr_Pulo_14(0);
        setNr_Pulo_15(0);
        setNr_Pulo_16(4);
        setNr_Pulo_17(1);
        setNr_Pulo_Entre_17_18(3);//PULO ENTRE LINHA DO ENDERE�O TRANSPORTADOR E O QUANT. TRANSPORTADOR;        
        setNr_Pulo_18(2);
        setNr_Pulo_19(1);
        setNr_Pulo_20(1);
        setNr_Pulo_21(4);
        setNr_Pulo_22(4);
        setNr_Pulo_23(4);
        setNr_Pulo_Entre_23_24(0); // PULO ENTRE ITENS E C�LCULO DO IMPOSTO        
        setNr_Pulo_24(2);
        setNr_Pulo_Entre_24_25(17);//PULO ENTRE ASTERISCOS E O N�MERO DA NF DO CANHOTO
        setNr_Pulo_25(10);

        setCd_Empresa(1);

        setComplementada(false);
    }

    public void setMiro()
    {
        setNr_margemL(0);
        setNr_maxItens(14);

        setNm_Razao_Social_Empresa(" ");
        setNr_nm_Razao_Social_Empresa(12);

        setNr_Nr_Numero(128);
        setNr_Nf_De_Total(119);
        setNr_Nm_Tipo(93);
        setNr_Nm_Natureza(4);
        setNr_Cd_Natureza(49);

        setNr_Nm_Remetente(4);
        setNr_Cd_Cliente_Palm(62);
        setNr_Nr_Cnpj_Remetente(93);
        setNr_nm_Endereco_Remetente(4);
        setNr_nm_Bairro_Remetente(83);
        setNr_Cd_Cep(106);
        setNr_Nm_Cidade_Remetente(4);
        setNr_Nm_Fone(72);
        setNr_Cd_Estado(87);
        setNr_nm_Ie_Remetente(93);

        setNr_Dt_Emissao(126);
        setNr_Dt_Saida(126);
        setNr_Hr_Saida(126);

        setNr_Nr_Qt_Itens_Possiveis(0);
        setNr_Cd_Produto(3);
        setNr_Nm_Produto(13);
        setNr_Cd_Situacao_Tributaria(59);
        setNr_Cd_Unidade(70);
        setNr_Nr_Quantidade(87);
        setNr_Vl_Unitario(100);
        setNr_Vl_Item(118);
        setNr_Aliquota_Icms(128);

        setNr_Tx_SubTotal_Base_ICMS(12);
        setNr_Vl_SubTotal_Base_ICMS(28);
        setNr_Tx_SubTotal_Valor_ICMS(48);
        setNr_Vl_SubTotal_Valor_ICMS(66);
        setNr_Tx_SubTotal_Subtotal(80);
        setNr_Vl_SubTotal_Subtotal(100);

        setNr_Vl_Base_Calculo_ICMS(21);
        setNr_Vl_ICMS(49);
        setNr_Vl_Base_Calculo_ICMS_Subst(75);
        setNr_Vl_ICMS_Subst(102);
        setNr_Vl_Total_Produtos(126);

        setNr_Nr_Vl_Total_Frete(17);
        setNr_Nr_Vl_Seguro(45);
        setNr_Nr_Vl_Despesas_Acessorias(71);
        setNr_Nr_Vl_Total_Ipi(98);
        setNr_Vl_Total(96);

        setNr_Nm_Transportador(4);
        setNr_Dm_Frete_Por_Conta(89);
        setNr_Tx_Placa(62);
        setNr_Nr_CNPJ_Transportador(119);
        setNr_Nm_Endereco_Transportador(4);
        setNr_Nm_UF_Transportador(93);
        setNr_Nm_IE_Transportador(119);
        setNr_Nm_Cidade_Transportador(79);
        setNr_Nr_Volumes(8);
        setNr_Nr_Especie(21);
        setNm_Especie("EMB.GERAL");
        setNr_Nr_Marca(35);
        setNm_Marca("");
        
        setNr_Nr_Peso_Bruto(106);
        setNr_Nr_Peso_Liquido(126);
        setNr_Tx_Saldo_a_Transportar(13);
        setNr_Vl_Saldo_a_Transportar(129);
        setNr_Tx_Total_Desconto(1);
        setNr_Vl_Total_Desconto(40);
        setNr_Tx_Observacao(8);
        setNr_Nr_Numero_canhoto(126);

        setNr_Asterisco1(7);
        setNr_Asterisco2(37);
        setNr_Asterisco3(65);
        setNr_Asterisco4(93);
        setNr_Asterisco5(120);

        setNr_Asterisco6(7);
        setNr_Asterisco7(37);
        setNr_Asterisco8(65);
        setNr_Asterisco9(93);
        setNr_Asterisco10(120);

        //MENSAGEM 1 e 2
        setNr_Trunca00(0);
        //MENSAGEM 1
        setNr_Trunca01(75);
        setNr_Trunca02(150);
        //MENSAGEM 2
        setNr_Trunca03(75);
        setNr_Trunca04(150);
        //TRUNCA NATUREZA OPERA��O
        setNr_Trunca05(40);

        setNr_Pulo_0(1);
        setNr_Pulo_Entre_0_1(0); // ENTRE N�MERO DA NF E TIPO DE NOTA FISCAL
        setNr_Pulo_1(5);
        setNr_Pulo_2(3);
        setNr_Pulo_3(3);
        setNr_Pulo_4(2);
        setNr_Pulo_5(6);
        setNr_Pulo_6(0);
        setNr_Pulo_7(0);
        setNr_Pulo_8(0);
        setNr_Pulo_9(0);
        setNr_Pulo_10(1);
        setNr_Pulo_11(4);
        setNr_Pulo_12(0);
        setNr_Pulo_13(0);
        setNr_Pulo_14(1);
        setNr_Pulo_15(0);
        setNr_Pulo_16(4);
        setNr_Pulo_17(2);
        setNr_Pulo_Entre_17_18(2);//PULO ENTRE LINHA DO ENDERE�O TRANSPORTADOR E O QUANT. TRANSPORTADOR;        
        setNr_Pulo_18(3);
        setNr_Pulo_19(1);
        setNr_Pulo_20(2);
        setNr_Pulo_21(17);
        setNr_Pulo_22(6);
        setNr_Pulo_23(6);
        setNr_Pulo_Entre_23_24(0); // PULO ENTRE ITENS E C�LCULO DO IMPOSTO        
        setNr_Pulo_24(2);
        setNr_Pulo_Entre_24_25(35);//PULO ENTRE ASTERISCOS E O N�MERO DA NF DO CANHOTO
        setNr_Pulo_25(9);

        setCd_Empresa(1);

        setComplementada(false);
    }

    public void setBorrussia()
    {
        setNr_margemL(0);
        setNr_maxItens(14);

        setNm_Razao_Social_Empresa(" ");
        setNr_nm_Razao_Social_Empresa(12);

        // CABE�ALHO
        setNr_Nr_Numero(131); // N�MERO DE NF
        setNr_Nf_De_Total(126); // QUANTIDADE DE P�GINAS
        setNr_Nm_Tipo(93); // TIPO DE NF ENTRADA/SA�DA
        setNr_Nm_Natureza(2); // NATUREZA DE OPERA��O
        setNr_Cd_Natureza(48); // CFOP

        // DESTINAT�RIO
        setNr_Nm_Remetente(2);
        setNr_Cd_Cliente_Palm(62);
        setNr_Nr_Cnpj_Remetente(90);
        setNr_nm_Endereco_Remetente(2);
        setNr_nm_Bairro_Remetente(69);
        setNr_Cd_Cep(100);
        setNr_Nm_Cidade_Remetente(2);
        setNr_Nm_Fone(57);
        setNr_Cd_Estado(84);
        setNr_nm_Ie_Remetente(90);
        
        // DATAS E HOR�RIOS
        setNr_Dt_Emissao(124);
        setNr_Dt_Saida(124);
        setNr_Hr_Saida(127);

        // ITENS DA NF
        setNr_Nr_Qt_Itens_Possiveis(0);
        setNr_Cd_Produto(4);
        setNr_Nm_Produto(9);
        setNr_Cd_Situacao_Tributaria(61);
        setNr_Cd_Unidade(70);
        setNr_Nr_Quantidade(83);
        setNr_Vl_Unitario(95);
        setNr_Vl_Item(113);
        setNr_Aliquota_Icms(120);
        
        // SUBTOTAL ITENS NF
        setNr_Tx_SubTotal_Base_ICMS(12);
        setNr_Vl_SubTotal_Base_ICMS(28);
        setNr_Tx_SubTotal_Valor_ICMS(48);
        setNr_Vl_SubTotal_Valor_ICMS(66);
        setNr_Tx_SubTotal_Subtotal(83);
        setNr_Vl_SubTotal_Subtotal(107);

        // C�LCULO DO IMPOSTO
        setNr_Vl_Base_Calculo_ICMS(15);
        setNr_Vl_ICMS(41);
        setNr_Vl_Base_Calculo_ICMS_Subst(67);
        setNr_Vl_ICMS_Subst(95);
        setNr_Vl_Total_Produtos(123);
        //
        setNr_Nr_Vl_Total_Frete(11);
        setNr_Nr_Vl_Seguro(37);
        setNr_Nr_Vl_Despesas_Acessorias(63);
        setNr_Nr_Vl_Total_Ipi(91);
        setNr_Vl_Total(110);

        // TRANSPORTADOR
        setNr_Nm_Transportador(2);
        setNr_Dm_Frete_Por_Conta(86);
        setNr_Tx_Placa(86);
        setNr_Nr_CNPJ_Transportador(115);
        setNr_Nm_Endereco_Transportador(2);
        setNr_Nm_Cidade_Transportador(76);        
        setNr_Nm_UF_Transportador(106);
        setNr_Nm_IE_Transportador(115);
        setNr_Nr_Volumes(12);
        setNr_Nr_Especie(29);
        setNm_Especie("SUINO");
        setNr_Nr_Marca(53);
        setNm_Marca("BORRUSSIA");
        
        // VOLUMES TRANSPORTADOS
        setNr_Nr_Peso_Bruto(104);
        setNr_Nr_Peso_Liquido(119);
        
        // NAO TEM
        setNr_Tx_Saldo_a_Transportar(13);
        setNr_Vl_Saldo_a_Transportar(129);
        setNr_Tx_Total_Desconto(2);
        setNr_Vl_Total_Desconto(40);
        /////////////////////////
        
        // OBSERVACOES
        setNr_Tx_Observacao(2);

        // N�MERO NF CANHOTO
        setNr_Nr_Numero_canhoto(120);

        // ASTER�SCOS DO C�LCULO DE IMPOSTO, 
        // QUANDO SE TEM DUAS P�GINAS 
        setNr_Asterisco1(7);
        setNr_Asterisco2(37);
        setNr_Asterisco3(65);
        setNr_Asterisco4(93);
        setNr_Asterisco5(120);
        //
        setNr_Asterisco6(7);
        setNr_Asterisco7(37);
        setNr_Asterisco8(65);
        setNr_Asterisco9(93);
        setNr_Asterisco10(120);

        //MENSAGEM 1 e 2
        setNr_Trunca00(0);
        //MENSAGEM 1
        setNr_Trunca01(74);
        setNr_Trunca02(150);
        //MENSAGEM 2
        setNr_Trunca03(75);
        setNr_Trunca04(150);
        //TRUNCA NATUREZA OPERA��O
        setNr_Trunca05(40);

        // PULOS DE LINHAS
        setNr_Pulo_0(1);
        setNr_Pulo_Entre_0_1(1); // ENTRE N�MERO DA NF E TIPO DE NOTA FISCAL
        setNr_Pulo_1(7); // ANTES DA NATUREZA DE OPERACAO
        setNr_Pulo_2(3);
        setNr_Pulo_3(3);
        setNr_Pulo_4(3);
        setNr_Pulo_5(9);
        setNr_Pulo_6(0);
        setNr_Pulo_7(0);
        setNr_Pulo_8(0);
        setNr_Pulo_9(0);
        setNr_Pulo_10(1);
        setNr_Pulo_11(3);
        setNr_Pulo_12(0);
        setNr_Pulo_13(0);
        setNr_Pulo_14(1);
        setNr_Pulo_15(0);
        setNr_Pulo_16(4);
        setNr_Pulo_17(2);
        setNr_Pulo_Entre_17_18(2);//PULO ENTRE LINHA DO ENDERE�O TRANSPORTADOR E O QUANT. TRANSPORTADOR;        
        setNr_Pulo_18(1);
        setNr_Pulo_19(1);
        setNr_Pulo_20(2);
        setNr_Pulo_21(9);
        setNr_Pulo_22(1);
        setNr_Pulo_23(5);
        setNr_Pulo_Entre_23_24(12); // PULO ENTRE ITENS E C�LCULO DO IMPOSTO
        setNr_Pulo_24(2);
        setNr_Pulo_Entre_24_25(35);//PULO ENTRE ASTERISCOS E O N�MERO DA NF DO CANHOTO
        setNr_Pulo_25(9);

        setCd_Empresa(3);

        setComplementada(false);
    }
    
    
    Parametro_FixoED parametro_FixoED;
    ExecutaSQL ExecutaSQL;
    PessoaED PessoaED;
    VeiculoBean VeiculoBean;
    Nota_FiscalED Nota_FiscalED;
    ConhecimentoED ConhecimentoED;
    private int nr_margemL;
    private int nr_maxItens;
    private int nr_nm_Razao_Social_Empresa;
    private String nm_Razao_Social_Empresa;
    private int nr_Pulo_0;
    private int nr_Pulo_Entre_0_1;     
    private int nr_Pulo_1;
    private int nr_Pulo_2;
    private int nr_Pulo_3;
    private int nr_Pulo_4;
    private int nr_Pulo_5;
    private int nr_Pulo_6;
    private int nr_Pulo_7;
    private int nr_Pulo_8;
    private int nr_Pulo_9;
    private int nr_Pulo_10;
    private int nr_Pulo_11;
    private int nr_Pulo_12;
    private int nr_Pulo_13;
    private int nr_Pulo_14;
    private int nr_Pulo_15;
    private int nr_Pulo_16;
    private int nr_Pulo_17;
    private int nr_Pulo_Entre_17_18;    
    private int nr_Pulo_18;
    private int nr_Pulo_19;
    private int nr_Pulo_20;
    private int nr_Pulo_21;
    private int nr_Pulo_22;
    private int nr_Pulo_23;
    private int nr_Pulo_Entre_23_24;
    private int nr_Pulo_24;
    private int nr_Pulo_Entre_24_25;
    private int nr_Pulo_25;
    private int nr_Nm_Tipo;
    private int nr_Nr_Numero;
    private int nr_Nf_De_Total;
    private int nr_Nm_Natureza;
    private int nr_Cd_Natureza;
    private int nr_Nm_Remetente;
    private int nr_Cd_Cliente_Palm;
    private int nr_Nr_Cnpj_Remetente;
    private int nr_Dt_Emissao;
    private int nr_nm_Endereco_Remetente;
    private int nr_nm_Bairro_Remetente;
    private int nr_Cd_Cep;
    private int nr_Dt_Saida;
    private int nr_Nm_Cidade_Remetente;
    private int nr_Nm_Fone;
    private int nr_Cd_Estado;
    private int nr_nm_Ie_Remetente;
    private int nr_Hr_Saida;
    private int nr_Nr_Qt_Itens_Possiveis;
    private int nr_Cd_Produto;
    private int nr_Nm_Produto;
    private int nr_Cd_Situacao_Tributaria;
    private int nr_Cd_Unidade;
    private int nr_Nr_Quantidade;
    private int nr_Vl_Unitario;
    private int nr_Vl_Item;
    private int nr_Aliquota_Icms;
    private int nr_Tx_SubTotal_Base_ICMS;
    private int nr_Vl_SubTotal_Base_ICMS;
    private int nr_Tx_SubTotal_Valor_ICMS;
    private int nr_Vl_SubTotal_Valor_ICMS;
    private int nr_Tx_SubTotal_Subtotal;
    private int nr_Vl_SubTotal_Subtotal;
    private int nr_Tx_Saldo_Transporte;
    private int nr_Vl_Saldo_Transporte;
    private int nr_Tx_Desconto_Especial;
    private int nr_Vl_Desconto_Especial;
    private int nr_Vl_Base_Calculo_ICMS;
    private int nr_Vl_ICMS;
    private int nr_Vl_Base_Calculo_ICMS_Subst;
    private int nr_Vl_ICMS_Subst;
    private int nr_Vl_Total_Produtos;
    private int nr_Nr_Vl_Total_Frete;
    private int nr_Nr_Vl_Seguro;
    private int nr_Nr_Vl_Despesas_Acessorias;
    private int nr_Nr_Vl_Total_Ipi;
    private int nr_Vl_Total;
    private int nr_Nm_Transportador;
    private int nr_Dm_Frete_Por_Conta;
    private int nr_Nr_CNPJ_Transportador;
    private int nr_Tx_Placa;
    private int nr_Nr_Peso_Liquido;
    private int nr_Nr_Peso_Bruto;
    private int nr_Nr_Volumes;
    private int nr_Nm_UF_Transportador;
    private int nr_Nm_Cidade_Transportador;
    private int nr_Nm_Endereco_Transportador;
    private int nr_Cd_Estado_Transportador;
    private int nr_Nm_IE_Transportador;
    private int nr_Nr_Especie;
    private String nm_Especie;
    private int nr_Nr_Marca;    
    private String nm_Marca;
    private int nr_Tx_Total_Desconto;
    private int nr_Vl_Total_Desconto;
    private int nr_Tx_Saldo_a_Transportar;
    private int nr_Vl_Saldo_a_Transportar;
    private int nr_Tx_Observacao;
    private int Nr_Nr_Numero_canhoto;
    private int Nr_Nr_Numero_Canhoto_Superior;
    private int nr_Asterisco1;
    private int nr_Asterisco2;
    private int nr_Asterisco3;
    private int nr_Asterisco4;
    private int nr_Asterisco5;
    private int nr_Asterisco6;
    private int nr_Asterisco7;
    private int nr_Asterisco8;
    private int nr_Asterisco9;
    private int nr_Asterisco10;
    private int nr_Trunca00;
    private int nr_Trunca01;
    private int nr_Trunca02;
    private int nr_Trunca03;
    private int nr_Trunca04;
    private int nr_Trunca05;
    private int nr_Trunca06;
    private int nr_Trunca07;
    private int nr_Trunca08;
    private int nr_Trunca09;
    private int nr_Trunca10;
    private int nr_Trunca11;
    private int nr_Trunca12;
    private int cd_Empresa;
    private boolean complementada;

	public boolean isComplementada() {
		return complementada;
	}

	public void setComplementada(boolean complementada) {
		this.complementada = complementada;
	}

}
