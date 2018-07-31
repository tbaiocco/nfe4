package com.master.bd;

import java.io.File;
import java.util.ArrayList;
import com.master.ed.Cheque_ClienteED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.ModuloUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.print.TextPrint;

public class EDI_Malote_EletronicoBD extends BancoUtil {

    private ExecutaSQL executasql;
    public EDI_Malote_EletronicoBD(ExecutaSQL sql) {
        super(sql, false);
        this.executasql = sql;
    }
    
    /** MALOTE ELETR�NICO BANRISUL - BIU
     *  @author Andre Valadas
     *  @throws Excecoes
     * */
    public String geraMaloteBanrisul(Cheque_ClienteED ed) throws Excecoes {
        
        try {
            String toReturn = "";
            boolean isHeader = true;
            int sequencia = 0;
            double vlTotalCheques = 0; 
            TextPrint text = new TextPrint(false);
            //*** Parametros para 
            ed.setCarregaBanco(true);
            ed.setCarregaCCorrente(false);
            ed.setCarregaCliente(false);
            ed.setCarregaEntrega(false);
            ed.setCarregaMotivoDev(false);
            ed.setCarregaNota(false);
            ArrayList lista = new Cheque_ClienteBD(executasql).lista(ed);
            
            //*** Cheques Avista BANRISUL n�o entram no BIU
            next:
            for (int i=0; i<lista.size(); i++)
            {
                Cheque_ClienteED edCheque = (Cheque_ClienteED) lista.get(i);
                if ("041".equals(ed.edBanco.getCD_Banco()) && !Data.comparaData(edCheque.getDT_Programado(), Data.getDataDMY()))
                {
                    // System.out.println(" -- NOT IN BIU: CHEQUE BANRISUL >> "+ed.getNR_Cheque()+" Data > "+edCheque.getDT_Programado());
                    continue next;
                }
                sequencia++;
                /** HEADER **/
                if (isHeader)
                {
                    isHeader = false;
                    text.write("H");//H01 - Fixo "H"
                    text.write("0290");//H02 - Ag�ncia do depositante
                    text.write("0601317104");//H03 - Conta do depositante
                    text.write("JOHANN ALIMENTOS LTDA    ");//H04 - Nome da empresa depositante
                    text.write("041");//H05 - N�mero do Banco Apresentante
                    text.write("8");//H06 - DV do n�mero do Banco
                    text.write("9");//H07 - 9�Interfaces externos - Maiores e Menores, 1 � s� menores, 2 � s� maiores
                    text.write(Data.getDataAAAAMMDD(Data.getDataDMY()));//H08 - Data do movimento
                    text.write(Data.getDataAAAAMMDD(Data.getDataDMY()));//H09 - Data cria��o do arquivo
                    text.write(Data.getHoraHM().replaceAll(":",""));//H10 - Hora e minuto cria��o do arquivo
                    text.write("0001");//H11 - Remessa
                    text.write("  ");//H12 - C�digo de Devolu��o
                    text.write(" ");//H13 - Filler
                    text.write("2024");//H14 - C�digo da empresa
                    text.write("01");//H15 - Conv�nio
                    text.write("000001");//H16 - C�digo do arquivo
                    text.write("0290");//H17 - Ag�ncia apresentante
                    text.write(JavaUtil.LFill("", 62, ' ', true));//H18 - Filler
                    text.write(JavaUtil.LFill(sequencia, 10, true));//H19 - Seq�encial do registro
                    text.nextLine();//H20 - Final do registro
                    sequencia++;
                }
                
                /** DETALHES **/
                // comp+banco+agencia+x2+00+C.Corrente+x1+nrCheque+x3
                // ex.: "0100080134000065683673090000321"
                String cdBanco = JavaUtil.LFill(getTableStringValue("CD_Banco","Bancos","oid_Banco="+edCheque.getOid_Banco()),3,true);
                String banda = edCheque.getNR_Comp();
                       banda+= cdBanco;
                       banda+= edCheque.getNR_Agencia();
                       banda+= ModuloUtil.getModulo10(edCheque.getNR_Comp()+edCheque.getNR_Cheque()+"5");
                       banda+= "00";
                       banda+= edCheque.getNR_Conta();
                       banda+= ModuloUtil.getModulo10(cdBanco+edCheque.getNR_Agencia());
                       banda+= edCheque.getNR_Cheque();
                       banda+= ModuloUtil.getModulo10(edCheque.getNR_Conta());
                text.write(banda);
                text.write("  ");//D09 - Tipo de documento
                text.write(JavaUtil.LFill(FormataValor.formataValorBT(edCheque.getVL_Cheque(), 2).replaceAll(",",""),17,true));//D10 - Valor
                text.write("  ");//D11 - C�digo de devolu��o
                text.write("5");//D12 - Tipifica��o
                text.write("041");//D13 - Banco apresentante
                text.write("0001");//D14 -C�digo da loja
                text.write("001");//D15 - Impressora fiscal � POS
                text.write(JavaUtil.LFill(1, 6, true));//D16 - Seq��ncia do cheque no lote
                text.write(" ");//D17 - Filler
                text.write(Data.getDataAAAAMMDD(edCheque.getDT_Programado()));//D18 - Data de efetiva��o
                text.write(JavaUtil.LFill("", 14, ' ', true));//D19 - C�digo de garantia
                text.write("00000001");//D20 - Controle da empresa
                text.write("000000");//D21 - Lote da empresa
                text.write(JavaUtil.LFill("", 20, ' ', true));//D22 - Filler
                text.write(JavaUtil.LFill("",14,true));//D23 - CPF/CNPJ Emitente
                text.write(JavaUtil.LFill("", 10, ' ', true));//D24 - Filler
                text.write(JavaUtil.LFill(sequencia, 10, true));//D25 - Seq�encial do registro
                text.nextLine();
                vlTotalCheques += ed.getVL_Cheque();
                
                /** TRAILLER **/
                if ((lista.size() -1) == i)
                {
                    sequencia++;
                    text.write("T");//T01 - Fixo "T"
                    text.write("0290");//T02 - Ag�ncia do depositante
                    text.write("0601317104");//T03 - Conta do depositante
                    text.write("JOHANN ALIMENTOS LTDA    ");//T04 - Nome da empresa depositante
                    text.write("041");//T05 - N�mero do Banco Apresentante
                    text.write("8");//T06 - DV do n�mero do Banco
                    text.write("9");//T07 - 9�Interfaces externos - Maiores e Menores, 1 � s� menores, 2 � s� maiores
                    text.write(Data.getDataAAAAMMDD(Data.getDataDMY()));//T08 - Data do movimento
                    text.write(Data.getDataAAAAMMDD(Data.getDataDMY()));//T09 - Data cria��o do arquivo
                    text.write(Data.getHoraHM().replaceAll(":",""));//T10 - Hora e minuto cria��o do arquivo
                    text.write("0001");//T11 - Remessa
                    text.write("  ");//T12 - C�digo de Devolu��o
                    text.write(" ");//T13 - Filler
                    text.write("2024");//T14 - C�digo da empresa
                    text.write("   ");//T15 - Filler
                    text.write(JavaUtil.LFill(FormataValor.formataValorBT(vlTotalCheques, 2).replaceAll(",",""),15,true));//T16 - Valor Total do arquivo
                    text.write(JavaUtil.LFill("", 56, ' ', true));//T17 - Filler
                    text.write(JavaUtil.LFill(sequencia, 10, true));//T18 - Seq�encial do arquivo
                    
                    //*** Cria Arquivo p/ salvar em Disco
                    String fileName = "BIUVCM1.MOV"; 
                    String arquivoPathOutput = Parametro_FixoED.getInstancia().getRelatoriosMatricialOutput()+"/"+fileName;
                    File file = new File(arquivoPathOutput);
                    if (file.exists())
                        file.delete();
                    text.saveToFile(arquivoPathOutput);
                    toReturn = "relatoriosmatricial/output/"+fileName;
                }
            }
            
            if (!doValida(toReturn))
                throw new Mensagens("N�o foram localizadas arquivos para salvar.");
            return toReturn;
        } catch (Excecoes e) {
            throw e;
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "geraMaloteBanrisul()");
        }
    }
}