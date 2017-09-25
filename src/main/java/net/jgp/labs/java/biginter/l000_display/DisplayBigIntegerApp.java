package net.jgp.labs.java.biginter.l000_display;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Based on https://stackoverflow.com/questions/38534460/how-to-convert-biginteger-number-into-scientific-notation-string-and-again-recon
 * 
 * @author jperrin
 */
public class DisplayBigIntegerApp {

    public static void main(String[] args) {
        // Create a large, ugly number.
        BigInteger bi = BigInteger.valueOf(1001).pow(345);

        // Convert to scientific notation using invariant Locale.ROOT
        NumberFormat formatter = new DecimalFormat("0.###E0", DecimalFormatSymbols.getInstance(Locale.ROOT));
        String str = formatter.format(bi);

        System.out.println(bi);
        System.out.println();
        System.out.println(str);

        // No need for a formatter here.
        BigDecimal bd = new BigDecimal(str);
        BigInteger output = bd.toBigInteger();

        System.out.println();
        System.out.println(output);
    }

}
