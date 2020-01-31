package net.jgp.labs.java.math.floating_point.lab100float_count;

public class FloatAdditionApp {

  public static void main(String[] args) {
    {
      double x = 0.1 + 0.1 + 0.1;
      double y = 0.3;
      System.out.println(x == y); // Evaluates to false
      System.out.println(x); // 0.30000000000000004
      System.out.println(y); // 0.3
    }
    {
      float x = 0.1f + 0.1f + 0.1f;
      float y = 0.3f;
      System.out.println(x == y); // Evaluates to true
      System.out.println(x); // 0.3
      System.out.println(y); // 0.3
    }
  }

}
