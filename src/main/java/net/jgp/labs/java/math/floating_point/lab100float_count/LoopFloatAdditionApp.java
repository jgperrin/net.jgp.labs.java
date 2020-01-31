package net.jgp.labs.java.math.floating_point.lab100float_count;

import java.math.BigDecimal;

public class LoopFloatAdditionApp {

  public static void main(String[] args) {
    double x = 0;
    long n0 = System.nanoTime();
    for (int i = 0; i < 10000000; i++) {
      x += 0.1;
    }
    System.out
        .println("x is " + x + "/took " + (System.nanoTime() - n0) / 1000
            + "µs");

    n0 = System.nanoTime();
    float y = 0;
    for (int i = 0; i < 10000000; i++) {
      y += 0.1f;
    }
    System.out
        .println("y is " + y + "/took " + (System.nanoTime() - n0) / 1000
            + "µs");

    n0 = System.nanoTime();
    BigDecimal z = BigDecimal.ZERO;
    BigDecimal inc = BigDecimal.ONE.divide(BigDecimal.TEN);
    for (int i = 0; i < 10000000; i++) {
      z = z.add(inc);
    }
    System.out
        .println("z is " + z + "/took " + (System.nanoTime() - n0) / 1000
            + "µs");
  }
}
