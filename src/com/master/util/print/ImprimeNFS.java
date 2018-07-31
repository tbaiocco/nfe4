package com.master.util.print;


public class ImprimeNFS {

    private Impressora impressora = null;

    public ImprimeNFS(Impressora impressora) {
        super();
        this.impressora = impressora;
        Imprime();
    }

    private void Imprime() {
        // Quantidade maxima de itens por NF
        int QtdeMaxItens;

        // Comprime a fonte
        impressora.setCaracterCondensado();

        // Ajusta alinhamento vertical
        impressora.setAlinhamentoVertical18();
        
        // Configura o tamanho da pagina
        impressora.setTamanhoPagina();

        // Numero da Nota Fiscal: 96
        impressora.print("96");
        QtdeMaxItens = 10;

        impressora.println("1234");

        impressora.print("71");
        impressora.println("X");
        impressora.println();
        impressora.println();
        impressora.println();
        impressora.println();
        impressora.println();
        impressora.println();

        // Natureza da Operacao: 17
        impressora.print("                 ");
        impressora.print("CD NOP");
        impressora.println("DESC NOP");
        impressora.println();
        impressora.println();

        // Nome/Razao Social
        impressora.print("                  ");
        impressora.print("ANDRE VALADAS");
        impressora.print("00726775037");
        impressora.println("20/12/2005");
        impressora.println();

        // Endereco
        /*impressora.print(Strings.replicate(' ', 18));
        impressora.print(Strings.insCharRight(venda.getCliente().getEndereco(), 40));
        impressora.print(Strings.insCharRight(venda.getCliente().getBairro(), 20));
        impressora.print(Strings.insCharRight(venda.getCliente().getCep(), 14));
        impressora.println(Strings.formatDate(Calendar.getInstance()));
        impressora.println();

        // Municipio
        impressora.print(Strings.replicate(' ', 18));
        impressora.print(Strings.insCharRight(venda.getCliente().getCidade(), 33));
        impressora.print(Strings.insCharRight(venda.getCliente().getTelefone(), 14));
        impressora.print(Strings.insCharRight(venda.getCliente().getEstado(), 8));
        // impressora.print(Strings.insCharRight(venda.getCliente().getIe_ou_rg(),
        // 19));
        impressora.println(Strings.formatTime(Calendar.getInstance()));
        impressora.println();
        impressora.println();
        impressora.println();

        // Itens da notafiscal
        for (ListIterator l = venda.getItens().listIterator(); l.hasNext();) {
            ItemVenda item = (ItemVenda) l.next();
            impressora.print(Strings.replicate(' ', 18));
            impressora.print(Strings.insCharRight(item.getProduto().getCodigoInterno().toString(), 7));
            impressora.print(Strings.insCharRight(item.getProduto().getDescricao(), 35) + " ");
            impressora.print(Strings.insCharRight("00", 3));
            impressora.print(Strings.insCharRight(item.getProduto().getUnidade(), 2));
            impressora.print(Strings.insCharLeft(Strings.formatNumber(item.getQuantidade().doubleValue(), 3), 9));
            impressora.print(Strings.insCharLeft(Strings.formatMoney(item.getPrecoEmbDesconto().doubleValue()), 10));
            impressora.print(Strings.insCharLeft(Strings.formatMoney(item.getTotalItem().doubleValue()), 13));
            impressora.println(Strings.insCharLeft(Strings.formatNumber(item.getProduto().getAliquotaICMS().doubleValue(), 1), 6));
        }

        // Observacoes da nota fiscal
        for (int i = 0; i < QtdeMaxItens - venda.getItens().size(); i++) {
            impressora.println();
        }
        impressora.print(Strings.replicate(' ', 18));
        impressora.println("CESTA BASICA - PRODUTO JA TRIBUTADO NAS DEMAIS FASES DE COMERCIALIZACAO");
        impressora.println();
        impressora.println();

        // Imprime rodape da nota
        impressora.print(Strings.replicate(' ', 18));
        impressora.print(Strings.insCharLeft(Strings.formatMoney(venda.getBaseCalculo().doubleValue()), 15) + " ");
        impressora.print(Strings.insCharLeft(Strings.formatMoney(venda.getTotalICMS().doubleValue()), 16) + " ");
        impressora.print(Strings.replicate(' ', 34));
        impressora.println(Strings.insCharLeft(Strings.formatMoney(venda.getTotalItens().doubleValue()), 18));
        impressora.println();

        impressora.print(Strings.replicate(' ', 18 + 16 + 17 + 34));
        impressora.println(Strings.insCharLeft(Strings.formatMoney(venda.getTotalItens().doubleValue()), 18));
        impressora.println();
        impressora.println();
        impressora.println();

        impressora.print(Strings.replicate(' ', 18));
        impressora.print(Strings.insCharLeft(Strings.formatNumber(venda.getTotalVolumes().doubleValue(), 3), 10));
        impressora.print(Strings.replicate(' ', 45));
        impressora.print(Strings.insCharLeft(Strings.formatNumber(venda.getTotalPesoBruto().doubleValue(), 3), 10));
        impressora.print(Strings.replicate(' ', 7));
        impressora.println(Strings.insCharLeft(Strings.formatNumber(venda.getTotalPesoLiquido().doubleValue(), 3), 10));
        impressora.println();
        impressora.println();

        impressora.print(Strings.replicate(' ', 18));
        impressora.println("Vendedor : " + venda.getVendedor().getNome());

        impressora.print(Strings.replicate(' ', 18));
        impressora.println("Cliente : " + venda.getCliente().getCodigo().intValue());

        impressora.print(Strings.replicate(' ', 18));
        impressora.println("Liquidacao: " + venda.getLiquidacao().getCodigo().intValue());

        impressora.print(Strings.replicate(' ', 18));
        impressora.println("Pedido : " + venda.getCodigo().intValue());

        impressora.print(Strings.replicate(' ', 18));
        impressora.print(Strings.insCharRight("Forma Pag.: " + venda.getOperacao().getDescricao(), 42));
        impressora.println("NF: " + venda.getNotaFiscal());

        impressora.print(Strings.replicate(' ', 18));
        impressora.println("Recebido em: ___/___/____");

        impressora.print(Strings.replicate(' ', 18));
        impressora.println("_________________________");

        impressora.print(Strings.replicate(' ', 18));*/
        impressora.println(" ASSINATURA");

        // Posiciona no inicio da proxima nota, e reseta
        impressora.printff();
        impressora.reset();
        impressora.flush();
    }
    
    public static void main(String[] args) {
        Impressora impressora = new ImpressoraEpson("LPT1");
        
        new ImprimeNFS(impressora);
    }
}
