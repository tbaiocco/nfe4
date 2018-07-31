package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.BancoED;
import com.master.ed.Livro_FiscalED;
import com.master.rl.BancoRL;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

import com.master.ed.EDI_ImportacaoED;

import com.master.bd.Item_Nota_Fiscal_TransacoesBD;;

public class DirfBD {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria();

    public DirfBD(ExecutaSQL sql) {
        this.executasql = sql;
        util = new Utilitaria(this.executasql);
    }

    public void inclui(BancoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = "delete from dirf_aux ";

            executasql.executarUpdate(sql);

            sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
            		" sum(vl_inss_prestador) as vl_inss, " +
            		" sum(vl_set_senat) as vl_sest_senat, " +
            		" sum(vl_irrf) as vl_irrf, " +
            		" Pessoa_Proprietario.nr_cnpj_cpf, " +
            		" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
            		" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
            		" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
            		" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
            		" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
            		" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
            		" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
            		" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
            		" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
            		" and Unidades.OID_Empresa = 1 " +
            		" and Ordens_Fretes.DT_Emissao >= '01/01/2009' and Ordens_Fretes.DT_Emissao <= '31/01/2009' " +
            		" and Ordens_Fretes.DM_Frete = 'P' " +
            		" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
            		" AND Ordens_Fretes.DM_Impresso = 'S' " +
            		" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
            		" order by Pessoa_Proprietario.NM_Razao_Social";

            // System.out.println(sql);

            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            while (res.next()) {
                // System.out.println(" Entrei 1");

            	sql = "insert into dirf_aux (vl_ordem_frete_janeiro, nm_proprietario, nr_cnpj_cpf) values (" +
                res.getDouble("vl_ordem_frete") + ",'" +
                res.getString("nm_proprietario") + "','" +
                res.getString("nr_cnpj_cpf") + "')"
            	;

            	// System.out.println(sql);
                executasql.executarUpdate(sql);
            }

            sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
    		      " sum(vl_inss_prestador) as vl_inss, " +
    		      " sum(vl_set_senat) as vl_sest_senat, " +
    		      " sum(vl_irrf) as vl_irrf, " +
    		      " Pessoa_Proprietario.nr_cnpj_cpf, " +
    		      " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
    		      " FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
    		      " Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
    		      " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
    		      " AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
    		      " AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
    		      " AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
    		      " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
    		      " AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
    		      " and Unidades.OID_Empresa = 1 " +
    		      " and Ordens_Fretes.DT_Emissao >= '01/02/2009' and Ordens_Fretes.DT_Emissao <= '28/02/2009' " +
    		      " and Ordens_Fretes.DM_Frete = 'P' " +
    		      " and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
    		      " AND Ordens_Fretes.DM_Impresso = 'S' " +
    		      " group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
    		      " order by Pessoa_Proprietario.NM_Razao_Social";

           // System.out.println(sql);

           res = null;
           res = this.executasql.executarConsulta(sql);

           while (res.next()) {
               // System.out.println(" Entrei 1");

               sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
               ResultSet resLocal = this.executasql.executarConsulta(sql);

               boolean dm_cadastrado = false;
               while (resLocal.next()) {
            	  dm_cadastrado = true;
               }

               if (dm_cadastrado){
            	   sql = " update dirf_aux set vl_ordem_frete_fevereiro = " + res.getDouble("vl_ordem_frete") +
            	         " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
               }else{
        	       sql = "insert into dirf_aux (vl_ordem_frete_fevereiro, nm_proprietario, nr_cnpj_cpf) values (" +
                   res.getDouble("vl_ordem_frete") + ",'" +
                   res.getString("nm_proprietario") + "','" +
                   res.getString("nr_cnpj_cpf") + "')"
        	       ;
               }

    	       // System.out.println(sql);
               executasql.executarUpdate(sql);
           }

           sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
		      " sum(vl_inss_prestador) as vl_inss, " +
		      " sum(vl_set_senat) as vl_sest_senat, " +
		      " sum(vl_irrf) as vl_irrf, " +
		      " Pessoa_Proprietario.nr_cnpj_cpf, " +
		      " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
		      " FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
		      " Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
		      " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
		      " AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
		      " AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
		      " AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
		      " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
		      " AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
		      " and Unidades.OID_Empresa = 1 " +
		      " and Ordens_Fretes.DT_Emissao >= '01/03/2009' and Ordens_Fretes.DT_Emissao <= '31/03/2009' " +
		      " and Ordens_Fretes.DM_Frete = 'P' " +
		      " and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
		      " AND Ordens_Fretes.DM_Impresso = 'S' " +
		      " group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
		      " order by Pessoa_Proprietario.NM_Razao_Social";

    // System.out.println(sql);

    res = null;
    res = this.executasql.executarConsulta(sql);

    while (res.next()) {
        // System.out.println(" Entrei 1");

        sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
        ResultSet resLocal = this.executasql.executarConsulta(sql);

        boolean dm_cadastrado = false;
        while (resLocal.next()) {
     	  dm_cadastrado = true;
        }

        if (dm_cadastrado){
     	   sql = " update dirf_aux set vl_ordem_frete_marco = " + res.getDouble("vl_ordem_frete") +
     	         " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
        }else{
 	       sql = "insert into dirf_aux (vl_ordem_frete_marco, nm_proprietario, nr_cnpj_cpf) values (" +
            res.getDouble("vl_ordem_frete") + ",'" +
            res.getString("nm_proprietario") + "','" +
            res.getString("nr_cnpj_cpf") + "')"
 	       ;
        }

	       // System.out.println(sql);
        executasql.executarUpdate(sql);
    }


    sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
    " sum(vl_inss_prestador) as vl_inss, " +
    " sum(vl_set_senat) as vl_sest_senat, " +
    " sum(vl_irrf) as vl_irrf, " +
    " Pessoa_Proprietario.nr_cnpj_cpf, " +
    " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
    " FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
    " Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
    " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
    " AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
    " AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
    " AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
    " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
    " AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
    " and Unidades.OID_Empresa = 1 " +
    " and Ordens_Fretes.DT_Emissao >= '01/04/2009' and Ordens_Fretes.DT_Emissao <= '30/04/2009' " +
    " and Ordens_Fretes.DM_Frete = 'P' " +
    " and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
    " AND Ordens_Fretes.DM_Impresso = 'S' " +
    " group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
    " order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
 // System.out.println(" Entrei 1");

 sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
 ResultSet resLocal = this.executasql.executarConsulta(sql);

 boolean dm_cadastrado = false;
 while (resLocal.next()) {
	  dm_cadastrado = true;
 }

 if (dm_cadastrado){
	   sql = " update dirf_aux set vl_ordem_frete_abril = " + res.getDouble("vl_ordem_frete") +
	         " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
 }else{
     sql = "insert into dirf_aux (vl_ordem_frete_abril, nm_proprietario, nr_cnpj_cpf) values (" +
     res.getDouble("vl_ordem_frete") + ",'" +
     res.getString("nm_proprietario") + "','" +
     res.getString("nr_cnpj_cpf") + "')"
     ;
 }

 // System.out.println(sql);
 executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/05/2009' and Ordens_Fretes.DT_Emissao <= '31/05/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_maio = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_maio, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/06/2009' and Ordens_Fretes.DT_Emissao <= '30/06/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_junho = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_junho, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/07/2009' and Ordens_Fretes.DT_Emissao <= '31/07/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_julho = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_julho, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/08/2009' and Ordens_Fretes.DT_Emissao <= '31/08/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_agosto = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_agosto, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/09/2009' and Ordens_Fretes.DT_Emissao <= '30/09/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_setembro = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_setembro, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/10/2009' and Ordens_Fretes.DT_Emissao <= '31/10/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_outubro = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_outubro, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/11/2009' and Ordens_Fretes.DT_Emissao <= '30/11/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_novembro = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_novembro, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

sql = "select sum(vl_ordem_frete) as vl_ordem_frete, " +
" sum(vl_inss_prestador) as vl_inss, " +
" sum(vl_set_senat) as vl_sest_senat, " +
" sum(vl_irrf) as vl_irrf, " +
" Pessoa_Proprietario.nr_cnpj_cpf, " +
" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario " +
" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, " +
" Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade   " +
" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade  " +
" AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa  " +
" AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa  " +
" AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
" AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade  " +
" and Unidades.OID_Empresa = 1 " +
" and Ordens_Fretes.DT_Emissao >= '01/12/2009' and Ordens_Fretes.DT_Emissao <= '31/12/2009' " +
" and Ordens_Fretes.DM_Frete = 'P' " +
" and Ordens_Fretes.DM_Tipo_Pessoa ='PF' " +
" AND Ordens_Fretes.DM_Impresso = 'S' " +
" group by Pessoa_Proprietario.NM_Razao_Social, Pessoa_Proprietario.nr_cnpj_cpf  " +
" order by Pessoa_Proprietario.NM_Razao_Social";

// System.out.println(sql);

res = null;
res = this.executasql.executarConsulta(sql);

while (res.next()) {
// System.out.println(" Entrei 1");

sql = " select * from dirf_aux where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
ResultSet resLocal = this.executasql.executarConsulta(sql);

boolean dm_cadastrado = false;
while (resLocal.next()) {
dm_cadastrado = true;
}

if (dm_cadastrado){
 sql = " update dirf_aux set vl_ordem_frete_dezembro = " + res.getDouble("vl_ordem_frete") +
       " where nr_cnpj_cpf = '" + res.getString("nr_cnpj_cpf") + "'";
}else{
 sql = "insert into dirf_aux (vl_ordem_frete_dezembro, nm_proprietario, nr_cnpj_cpf) values (" +
 res.getDouble("vl_ordem_frete") + ",'" +
 res.getString("nm_proprietario") + "','" +
 res.getString("nr_cnpj_cpf") + "')"
 ;
}

// System.out.println(sql);
executasql.executarUpdate(sql);
}

        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

}