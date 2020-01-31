package net.jgp.labs.java.math.prime.lab100simpleCounter;

import java.util.ArrayList;
import java.util.List;

public class BasicPrimeCounter {

  public static void main(String[] args) {
    List<Integer> prime = new ArrayList<>();
    prime.add(3);
    int count = 3;
    print(1, 2);
    print(2, 3);

    for (int i = 5; true; i += 2) {
      boolean isPrime = true;
      for (int j = 0; j < prime.size(); j++) {
        if (i % prime.get(j) == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        prime.add(i);
        print(count++, i);
      }
    }
  }

  private static void print(int i, int j) {
    System.out.println(i + " ... " + j);
    if (i % 9 == 0) {
      System.out.println("");
    }
  }
}
