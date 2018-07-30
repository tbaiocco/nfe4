package com.master.iu;

import javax.servlet.http.HttpServletRequest;
import com.master.ed.Parametro_ContaED;
import com.master.rn.Parametro_ContaRN;
import com.master.util.Excecoes;

public class ctb002Bean {

	
  public void altera (HttpServletRequest request) throws Excecoes {

    Parametro_ContaED ed = new Parametro_ContaED ();

    ed.setCd_conta_cofins_credito (request.getParameter ("FT_Cd_conta_cofins_credito"));
    ed.setCd_conta_cofins_debito (request.getParameter ("FT_Cd_conta_cofins_debito"));
    ed.setCd_conta_cofins_nota_fiscal (request.getParameter ("FT_Cd_conta_cofins_nota_fiscal"));
    ed.setCd_conta_csll_nota_fiscal (request.getParameter ("FT_Cd_conta_csll_nota_fiscal"));
    ed.setCd_conta_desconto_nota_fiscal (request.getParameter ("FT_Cd_conta_desconto_nota_fiscal"));
    ed.setCd_conta_icms_credito (request.getParameter ("FT_Cd_conta_icms_credito"));
    ed.setCd_conta_icms_debito (request.getParameter ("FT_Cd_conta_icms_debito"));
    ed.setCd_conta_icms_nota_fiscal (request.getParameter ("FT_Cd_conta_icms_nota_fiscal"));
    ed.setCd_conta_inss_empresa_credito (request.getParameter ("FT_Cd_conta_inss_empresa_credito"));
    ed.setCd_conta_inss_empresa_debito (request.getParameter ("FT_Cd_conta_inss_empresa_debito"));
    ed.setCd_conta_inss_nota_fiscal (request.getParameter ("FT_Cd_conta_inss_nota_fiscal"));
    ed.setCd_conta_inss_terceiro_credito (request.getParameter ("FT_Cd_conta_inss_terceiro_credito"));
    ed.setCd_conta_ipi_nota_fiscal (request.getParameter ("FT_Cd_conta_ipi_nota_fiscal"));
    ed.setCd_conta_irrf_credito (request.getParameter ("FT_Cd_conta_irrf_credito"));
    ed.setCd_conta_irrf_nota_fiscal (request.getParameter ("FT_Cd_conta_irrf_nota_fiscal"));
    ed.setCd_conta_isqn_nota_fiscal (request.getParameter ("FT_Cd_conta_isqn_nota_fiscal"));
    ed.setCd_conta_liquido_nota_fiscal (request.getParameter ("FT_Cd_conta_liquido_nota_fiscal"));
    ed.setCd_conta_pis_credito (request.getParameter ("FT_Cd_conta_pis_credito"));
    ed.setCd_conta_pis_debito (request.getParameter ("FT_Cd_conta_pis_debito"));
    ed.setCd_conta_pis_nota_fiscal (request.getParameter ("FT_Cd_conta_pis_nota_fiscal"));
    ed.setCd_conta_seguro_credito (request.getParameter ("FT_Cd_conta_seguro_credito"));
    ed.setCd_conta_seguro_debito (request.getParameter ("FT_Cd_conta_seguro_debito"));
    ed.setCd_conta_servico_nota_fiscal (request.getParameter ("FT_Cd_conta_servico_nota_fiscal"));
    ed.setCd_conta_sest_senat_credito (request.getParameter ("FT_Cd_conta_sest_senat_credito"));

    ed.setCd_conta_multa_pagamento (request.getParameter ("FT_Cd_conta_multa_pagamento"));
    ed.setCd_conta_juros_pagamento (request.getParameter ("FT_Cd_conta_juros_pagamento"));
    ed.setCd_conta_desconto_pagamento (request.getParameter ("FT_Cd_conta_desconto_pagamento"));
    ed.setCd_conta_outras_despesas_pagamento (request.getParameter ("FT_Cd_conta_outras_despesas_pagamento"));
    ed.setCd_conta_transitoria_compensacao_cheque (request.getParameter ("FT_Cd_conta_transitoria_compensacao_cheque"));
    ed.setCd_conta_fornecedores_geral (request.getParameter ("FT_Cd_conta_fornecedores_geral"));
    ed.setCd_conta_venda (request.getParameter ("FT_Cd_conta_venda"));
    ed.setCd_conta_cliente_geral (request.getParameter ("FT_Cd_conta_cliente_geral"));
    ed.setCd_conta_a_faturar (request.getParameter ("FT_Cd_conta_a_faturar"));
    ed.setCd_conta_adiantamento_viagem (request.getParameter ("FT_Cd_conta_adiantamento_viagem"));
    ed.setCd_conta_adiantamento_funcionario (request.getParameter ("FT_Cd_conta_adiantamento_funcionario"));
    ed.setCd_conta_adiantamento_terceiro (request.getParameter ("FT_Cd_conta_adiantamento_terceiro"));
    ed.setCd_conta_despesas_duplicata (request.getParameter ("FT_Cd_conta_despesas_duplicata"));
    ed.setCd_conta_juros_duplicata (request.getParameter ("FT_Cd_conta_juros_duplicata"));
    ed.setCd_conta_desconto_duplicata (request.getParameter ("FT_Cd_conta_desconto_duplicata"));
    ed.setCd_conta_pedagio (request.getParameter ("FT_Cd_conta_pedagio"));
    ed.setCd_conta_zera_resultado (request.getParameter ("FT_Cd_conta_zera_resultado"));

    
    ed.setCd_conta_compensacao_cheque (request.getParameter ("FT_Cd_conta_compensacao_cheque"));
    ed.setCd_conta_ofpf_debito (request.getParameter ("FT_Cd_conta_ofpf_debito"));
    ed.setCd_conta_ofpf_credito (request.getParameter ("FT_Cd_conta_ofpf_credito"));
    ed.setCd_conta_ofpj_debito (request.getParameter ("FT_Cd_conta_ofpj_debito"));
    ed.setCd_conta_ofpj_credito (request.getParameter ("FT_Cd_conta_ofpj_credito"));
    
    
    new Parametro_ContaRN ().altera (ed);
  }

  public Parametro_ContaED getByRecord (HttpServletRequest request) throws Excecoes {

    Parametro_ContaED ed = new Parametro_ContaED ();

    return new Parametro_ContaRN ().getByRecord (ed);

  }
}