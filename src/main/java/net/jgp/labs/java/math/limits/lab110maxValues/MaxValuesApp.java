/**
 * 
 */
package net.jgp.labs.java.math.limits.lab110maxValues;

import java.math.BigInteger;

/**
 * @author jgp
 * 
 */
public class MaxValuesApp {

  /**
   * @param args
   */
  public static void main(String[] args) {
    MaxValuesApp app = new MaxValuesApp();
    app.start();
  }

  private boolean start() {
    int maxInt = Integer.MAX_VALUE;
    System.out.println("Max value of an integer: "
        + maxInt);
    maxInt++;
    System.out.println("Max value of an integer +1: "
        + maxInt);
    return true;
  }

}
