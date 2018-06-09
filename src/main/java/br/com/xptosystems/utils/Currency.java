package br.com.xptosystems.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Classe que padroniza a internacionalizacao de valores monetarios
 *
 * @version 0.1
 * @see java.util.Locale
 * @see java.text.DecimalFormat
 * @see java.text.DecimalFormatSymbols
 */
public final class Currency {

    /**
     * Simbolos especificos do Dolar Americano
     */
    private static final DecimalFormatSymbols DOLAR = new DecimalFormatSymbols(Locale.US);
    /**
     * Mascara de dinheiro para Dolar Americano
     */
    public static final DecimalFormat DINHEIRO_DOLAR = new DecimalFormat("###,###,##0.00", DOLAR);
    /**
     * Simbolos especificos do Euro
     */
    private static final DecimalFormatSymbols EURO = new DecimalFormatSymbols(Locale.GERMANY);
    /**
     * Mascara de dinheiro para Euro
     */
    public static final DecimalFormat DINHEIRO_EURO = new DecimalFormat("� ###,###,##0.00", EURO);
    /**
     * Locale Brasileiro
     */
    private static final Locale BRAZIL = new Locale("pt", "BR");
    /**
     * S�mbolos especificos do Real Brasileiro
     */
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    /**
     * Mascara de dinheiro para Real Brasileiro
     */
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00", REAL);

    /**
     * Mascara texto com formatacao monetaria
     *
     * @param valor Valor a ser mascarado
     * @param moeda Padrao monetario a ser usado
     * @return Valor mascarado de acordo com o padrao especificado Ex: String m
     * = Currency.maskMoney(100,Currency.DINHEIRO_REAL); m = R$ 100.00
     */
    public static String maskMoney(BigDecimal valor, DecimalFormat moeda) {
        return moeda.format(valor);
    }

    public static BigDecimal conveterRealToDolar(String dolar) {
        if (dolar == null) {
            dolar = "0,00";
        }
        if (dolar.length() >= 3) {
            String wponto = dolar.substring(dolar.trim().length() - 3, dolar.trim().length() - 2);
            if (wponto.equals(",")) {
                dolar = dolar.replace(".", "");
                dolar = dolar.replace(",", ".");
            }
        }
        return new BigDecimal(dolar);
    }

    public static String conveterDolarToReal(String dolar) {
        //dolar = dolar.replaceAll("[^0-9]", "");
        if (dolar == null || dolar.trim().equals("")) {
            return "0,00";
        }
        dolar = Currency.replaceComma(dolar);
        if (dolar.length() >= 3) {
            String wponto = dolar.substring(dolar.trim().length() - 3, dolar.trim().length() - 2);
            if (!wponto.equals(",")) {
                dolar = Currency.maskMoney(new BigDecimal(dolar), Currency.DINHEIRO_REAL);
            }
        } else {
            dolar = Currency.maskMoney(new BigDecimal(dolar), Currency.DINHEIRO_REAL);
        }
        return dolar;
    }

    public static String converteRealToBigDecimal(BigDecimal valor) {
        String dolar = valor.toString();
        if ((dolar == null) || (dolar.trim().equals(""))) {
            return "0,00";
        }
        dolar = Currency.replaceComma(dolar);
        if (dolar.length() >= 3) {
            String wponto = dolar.substring(dolar.trim().length() - 3, dolar.trim().length() - 2);
            if (!wponto.equals(",")) {
                dolar = Currency.maskMoney(new BigDecimal(dolar), Currency.DINHEIRO_REAL);
            }
        } else {
            dolar = Currency.maskMoney(new BigDecimal(dolar), Currency.DINHEIRO_REAL);
        }
        return dolar;
    }

    public static BigDecimal converteBigDecimalR$BigDecimal(BigDecimal v) {
        String dolar = v.toString();
        if ((dolar == null) || (dolar.trim().equals(""))) {
            return new BigDecimal(0);
        }
        dolar = Currency.replaceComma(dolar);
        if (dolar.length() >= 3) {
            String wponto = dolar.substring(dolar.trim().length() - 3, dolar.trim().length() - 2);
            if (!wponto.equals(",")) {
                dolar = Currency.maskMoney(new BigDecimal(dolar), Currency.DINHEIRO_REAL);
            }
        } else {
            dolar = Currency.maskMoney(new BigDecimal(dolar), Currency.DINHEIRO_REAL);
        }
        return Currency.replaceCommaToBigDecimal(dolar);
    }

    /**
     * Substitui vírgula
     *
     * @param v
     * @return
     */
    public static String replaceComma(String v) {
        if (!v.contains(",")) {
            return v;
        }
        v = v.replace(".", "");
        v = v.replace(",", ".");
        return v;
    }

    public static BigDecimal replaceCommaToBigDecimal(String v) {
        if (!v.contains(",")) {
            return new BigDecimal(v);
        }
        v = v.replace(".", "");
        v = v.replace(",", ".");
        return new BigDecimal(v);
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        try {
            return a.add(b);
        } catch (Exception e) {
            return null;
        }
    }

    public static String increment(String a, String b) { // a = boleto somado   ,  b = 1
        BigDecimal aBig = new BigDecimal(a);
        BigDecimal bBig = new BigDecimal(b);
        BigDecimal result = aBig.add(bBig);
        return result.toString().substring(1);
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        try {
            return a.subtract(b);
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        try {
            return a.multiply(b);
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        try {
            return a.divide(b);
        } catch (Exception e) {
            return null;
        }
    }

    public static String clearPoint(String v) {
        v = conveterDolarToReal(v);
        v = replaceComma(v);
        int i = 0;
        String result = "";
        while (i < v.length()) {
            if (v.charAt(i) != '.') {
                result += v.charAt(i);
            } else {
                if (((i + 2) > v.length()) && (v.charAt(i + 1) == '0')) {
                    i++;
                }
            }
            i++;
        }
        return result;
    }

    public static String clearComma(String v) {
        int i = 0;
        String result = "";
        while (i < v.length()) {
            if (v.charAt(i) != ',') {
                result += v.charAt(i);
            } else {
                if ((i + 2) > v.length()) {
                    result += v.charAt(i + 1);
                    result += v.charAt(i + 2);
                    break;
                }
            }
            i++;
        }
        return result;
    }
}
