package com.master.util;

/**
 * Classe para Manipulação de SubString
 * @author André Valadas
 */
public class ArquivoUtil extends JavaUtil {

    int length = 0;
    int nlinha = 0;
    String strLinha = "";
    
    public ArquivoUtil(String strLinha, int nlinha){
        setLinha(strLinha, nlinha);
    }
    public void setLinha(String strLinha, int nlinha) {
        this.nlinha = nlinha;
        length = (this.strLinha = getValueDef(strLinha, "")).length();
    }
    
    /** VALIDAÇÕES */
    private boolean valida(int beginIndex, int endIndex) {
        return !((length < 1 ) || (beginIndex < 0 ) || (endIndex > length) || (beginIndex > endIndex));
    }
    
    /** STRING */
    public String getString(int begin, int end) {
    	return valida(begin, end) ? strLinha.substring(begin, end).trim() : "";
    }
    
    /** DOUBLE */
    public double getDouble(int begin, int end) {
        
        try {
            if (strLinha.substring(begin, end).indexOf("-") > 0)
                begin += strLinha.substring(begin, end).indexOf("-");
            return valida(begin, end) ? Double.parseDouble(strLinha.substring(begin, end).replace(',','.').trim()) : 0;
        } catch (Exception e) {
             System.out.println("ERRO ao converter DOUBLE! / ERRO["+e.getMessage()+"]\n" +
 				   			   " - LINHA: "+nlinha+"\n" +
 				   			   " - POSIÇÃO: "+begin+" a "+end+"\n");
            return 0;
        }
    }
    /** DOUBLE ARREDONDADO */
    public double getDouble(int begin, int end, boolean round) {
        
        if (round == true) 
        {
            try {
                if (strLinha.substring(begin, end).indexOf("-") > 0)
                    begin += strLinha.substring(begin, end).indexOf("-");
                return valida(begin, end) ? Valor.getValorArredondado(Double.parseDouble(strLinha.substring(begin, end).replace(',','.').trim())) : 0;
            } catch (Exception e) {
                 System.out.println("ERRO ao converter DOUBLE ARREDONDADO! / ERRO["+e.getMessage()+"]\n" +
                        		   " - LINHA: "+nlinha+"\n" +
                        		   " - POSIÇÃO: "+begin+" a "+end+"\n");
                return 0;
            }
        } else return getDouble(begin, end);
    }
    /** DOUBLE ARREDONDADO C/ CASAS DECIMAIS*/
    public double getDouble(int begin, int end, boolean round, int casas) {
        
        if (round == true) 
        {
            if (casas > 2)
            {
                try {
                    if (strLinha.substring(begin, end).indexOf("-") > 0)
                        begin += strLinha.substring(begin, end).indexOf("-");
                    return valida(begin, end) ? Valor.getValorArredondado(Double.parseDouble(strLinha.substring(begin, end).replace(',','.').trim()), casas) : 0;
                } catch (Exception e) {
                     System.out.println("ERRO ao converter DOUBLE ARREDONDADO! / ERRO["+e.getMessage()+"]\n" +
                            		   " - LINHA: "+nlinha+"\n" +
                            		   " - POSIÇÃO: "+begin+" a "+end+"\n");
                    return 0;
                }
            } else return getDouble(begin, end, true);
        } else return getDouble(begin, end);
    }
    /** INTEGER */
    public int getInteger(int begin, int end) {
        
        try {
            return valida(begin, end) ? Integer.parseInt(strLinha.substring(begin, end).trim()) : 0;
        } catch (Exception e) {
             System.out.println("ERRO ao converter INTEGER! / ERRO["+e.getMessage()+"]\n" +
            				   " - LINHA: "+nlinha+"\n" +
            				   " - POSIÇÃO: "+begin+" a "+end+"\n");
            return 0;
        }
    }
    /** DATA - Converte Data(AAAAMMDD) vinda do PALM */
    public String getData(int begin, int end) {
        
        String data = getString(begin, end);
        if (doValida(data) && data.length() == 8) {
    		return data.substring(0,4) + "-" + data.substring(4,6) + "-" + data.substring(6,8);
        } else return Data.getDataYMD();
    }
    /** HORA - Converte Hora(HHMMSS) vinda do PALM */
    public String getHora(int begin, int end) {
        
        String hora = getString(begin, end);
        if (doValida(hora) && hora.length() == 6) {
    		return hora.substring(0,2) + ":" + hora.substring(2,4);
        } else return Data.getHoraHM();
    }
    
    public String toString() {
        return strLinha;
    }
}