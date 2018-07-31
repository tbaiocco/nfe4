package com.master.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import com.master.root.MoedaBean;


public class Extenso {
	private ArrayList nro;
	private BigInteger num;

	private String Qualificadores[][] = {
			{"centavo", "centavos"},
			{"", ""},
			{"mil", "mil"},
			{"milhao", "milhoes"},
			{"bilhao", "bilhoes"},
			{"trilhao", "trilhoes"},
			{"quatrilhao", "quatrilhoes"},
			{"quintilhao", "quintilhoes"},
			{"sextilhao", "sextilhoes"},
			{"septilhao", "septilhoes"}
			};
	private String NumerosM[][] = {
			{"zero", "um", "dois", "tres", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
			"onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"},
			{"vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"},
			{"cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos",
			"setecentos", "oitocentos", "novecentos"}
			};

	private String NumerosF[][] = {
			{"zero", "uma", "duas", "tres", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
			"onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"},
			{"vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"},
			{"cem", "cento", "duzentas", "trezentas", "quatrocentas", "quinhentas", "seiscentas",
			"setecentas", "oitocentas", "novecentas"}
			};

	/**
	 *  Construtor
	 */
	public Extenso() {
		nro = new ArrayList();
	}

	/**
	 * Muda descriçao por extenso do valor para o formato da Moeda passada(oid_Moeda)
	 * @param oid_Moeda
	 * @param value(valor por extenso)
	 * @return extensao convertida
	 * @throws Exception
	 */
	static public String getExtensoByMoeda(int oid_Moeda, String value)
	throws Excecoes {
	    if (oid_Moeda > 0) {
	        if (JavaUtil.doValida(value)) {
	            MoedaBean edMoeda;
                try {
                    edMoeda = MoedaBean.getByOID(oid_Moeda);
                } catch (Exception e) {
                    throw new Excecoes(e.getMessage(), e, Extenso.class.getName(), "getExtensoByMoeda(int oid_Moeda, String value)");
                }
                // EXTENSO
	            value = value.replaceAll("REAIS", edMoeda.getNM_Extenso_Plural())
	            			 .replaceAll("Reais", edMoeda.getNM_Extenso_Plural())
	            			 .replaceAll("reais", edMoeda.getNM_Extenso_Plural())
	            			 .replaceAll("REAL", edMoeda.getNM_Extenso_Singular())
	            			 .replaceAll("Real", edMoeda.getNM_Extenso_Singular())
	            			 .replaceAll("real", edMoeda.getNM_Extenso_Singular());
	            // FRACIONÁRIO
	            value = value.replaceAll("CENTAVOS", edMoeda.getNM_Fracao_Plural())
	            			 .replaceAll("Centavos", edMoeda.getNM_Fracao_Plural())
	            			 .replaceAll("centavos", edMoeda.getNM_Fracao_Plural())
	            			 .replaceAll("CENTAVO", edMoeda.getNM_Fracao_Singular())
	            			 .replaceAll("Centavo", edMoeda.getNM_Fracao_Singular())
	            			 .replaceAll("centavo", edMoeda.getNM_Fracao_Singular());
	        }
	    }
	    return value;
	}
	/**
	 *  Seta o atributo Numero do objeto Extenso
	 *
	 *@param  dec  Valor a ser atribuído
	 */
	private void setNumber(BigDecimal dec) {
		// Converte para inteiro arredondando os centavos
		num = dec
			.setScale(2, BigDecimal.ROUND_HALF_UP)
			.multiply(BigDecimal.valueOf(100))
			.toBigInteger();

		// Adiciona valores
		nro.clear();
		if (num.equals(BigInteger.ZERO)) {
			// Centavos
			nro.add(new Integer(0));
			// Valor
			nro.add(new Integer(0));
		}
		else {
			// Adiciona centavos
			addRemainder(100);

			// Adiciona grupos de 1000
			while (!num.equals(BigInteger.ZERO)) {
				addRemainder(1000);
			}
		}
	}

	/**
	 *
	 *@return    Descriçao por extenso do valor retornado
	 */
	public String getExtenso(double dec, char genero) {
		return toString(new BigDecimal(dec), genero);
	}

	/**
	 * Descriçao por extenso do valor retornado sem unidade monetaria
	 * @param dec
	 * @return
	 */
	public String getExtensoSemUnidade(double dec, char genero) {
		String temp  = toString(new BigDecimal(dec), genero);
		if (temp.indexOf(" rea") > -1)
			temp = temp.substring(0, temp.indexOf(" rea"));
		else
			temp = "zero";
		return temp;
	}

	/**
	 *
	 *@return    Descriçao por extenso do valor retornado
	 */
	public String getExtenso(BigDecimal dec, char genero) {
		return toString(dec, genero);
	}

	/**
	 * Descriçao por extenso do valor retornado sem unidade monetaria
	 * @param dec
	 * @return
	 */
	public String getExtensoSemUnidade(BigDecimal dec, char genero) {
		String temp = toString(dec, genero);
		if (temp.indexOf(" rea") > -1)
			temp = temp.substring(0, temp.indexOf(" rea"));
		else
			temp = "zero";
		return temp;
	}

	private String toString(BigDecimal dec, char genero) {
								setNumber(dec);

		StringBuffer buf = new StringBuffer();

		int ct;

		for (ct = nro.size() - 1; ct > 0; ct--) {
			// Se ja existe texto e o atual nao é zero
			if (buf.length() > 0 && ! ehGrupoZero(ct)) {
				buf.append(" e ");
			}
			buf.append(numToString(((Integer) nro.get(ct)).intValue(), ct, genero));
		}
		if (buf.length() > 0) {
			if (ehUnicoGrupo())
				buf.append(" de ");
			while (buf.toString().endsWith(" "))
				buf.setLength(buf.length()-1);
			if (ehPrimeiroGrupoUm())
				buf.insert(0, " ");   //"h" de hum
			if (nro.size() == 2 && ((Integer)nro.get(1)).intValue() == 1) {
				buf.append(" real");
			} else {
				buf.append(" reais");
			}
			if (((Integer) nro.get(0)).intValue() != 0) {
				buf.append(" e ");
			}
		}
		if (((Integer) nro.get(0)).intValue() != 0) {
			buf.append(numToString(((Integer) nro.get(0)).intValue(), 0, genero));
		}
		return buf.toString();
	}

	private boolean ehPrimeiroGrupoUm() {
		if (((Integer)nro.get(nro.size()-1)).intValue() == 1)
			return true;
		return false;
	}

	/**
	 *  Adds a feature to the Remainder attribute of the Extenso object
	 *
	 *@param  divisor  The feature to be added to the Remainder attribute
	 */
	private void addRemainder(int divisor) {
		// Encontra newNum[0] = num modulo divisor, newNum[1] = num dividido divisor
		BigInteger[] newNum = num.divideAndRemainder(BigInteger.valueOf(divisor));

		// Adiciona modulo
		nro.add(new Integer(newNum[1].intValue()));

		// Altera numero
		num = newNum[0];
	}


	/**
	 *  Description of the Method
	 *
	 *@param  ps  Description of Parameter
	 *@return     Description of the Returned Value
	 */
	private boolean temMaisGrupos(int ps) {
		for (; ps > 0; ps--) {
			if (((Integer) nro.get(ps)).intValue() != 0) {
				return true;
			}
		}

		return false;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  ps  Description of Parameter
	 *@return     Description of the Returned Value
	 */
	private boolean ehUltimoGrupo(int ps) {
		return (ps > 0) && ((Integer)nro.get(ps)).intValue() != 0 && !temMaisGrupos(ps - 1);
	}


	/**
	 *  Description of the Method
	 *
	 *@return     Description of the Returned Value
	 */
	private boolean ehUnicoGrupo() {
		if (nro.size() <= 3)
			return false;
		if (!ehGrupoZero(1) && !ehGrupoZero(2))
			return false;
		boolean hasOne = false;
		for(int i=3; i < nro.size(); i++) {
			if (((Integer)nro.get(i)).intValue() != 0) {
				if (hasOne)
					return false;
				hasOne = true;
			}
		}
		return true;
	}

	boolean ehGrupoZero(int ps) {
		if (ps <= 0 || ps >= nro.size())
			return true;
		return ((Integer)nro.get(ps)).intValue() == 0;
	}

	/**
	 *  Description of the Method
	 *
	 *@param  numero  Description of Parameter
	 *@param  escala  Description of Parameter
	 *@return         Description of the Returned Value
	 */
	private String numToString(int numero, int escala, char genero) {
		int unidade = (numero % 10);
		int dezena = (numero % 100); //* nao pode dividir por 10 pois verifica de 0..19
		int centena = (numero / 100);
		StringBuffer buf = new StringBuffer();

		if (numero != 0) {
			if (centena != 0) {
				if (dezena == 0 && centena == 1) {
						if (genero == 'M')
							buf.append(NumerosM[2][0]);
						else
							buf.append(NumerosF[2][0]);

				}
				else {
					if (genero == 'M')
						buf.append(NumerosM[2][centena]);
					else
						buf.append(NumerosF[2][centena]);
				}
			}

			if ((buf.length() > 0) && (dezena != 0)) {
				buf.append(" e ");
			}
			if (dezena > 19) {
				dezena /= 10;
				if (genero == 'M')
					buf.append(NumerosM[1][dezena - 2]);
				else
					buf.append(NumerosF[1][dezena - 2]);
				if (unidade != 0) {
					buf.append(" e ");
					if (genero == 'M')
						buf.append(NumerosM[0][unidade]);
					else
						buf.append(NumerosF[0][unidade]);
				}
			}
			else
				if (centena == 0 || dezena != 0) {
					if (genero == 'M')
						buf.append(NumerosM[0][dezena]);
					else
						buf.append(NumerosF[0][dezena]);
			}

			buf.append(" ");
			if (numero == 1) {
				buf.append(Qualificadores[escala][0]);
			}
			else {
				buf.append(Qualificadores[escala][1]);
			}
		}

		return buf.toString();
	}
}
