package net.jgp.labs.enums.lab110complexColor;

import org.junit.Test;

import net.jgp.labs.java.enums.lab110complexColor.Color;

public class ColorTest {

    @Test
    public void printRed() {
        System.out.println(Color.RED);
    }

    @Test
    public void compareRedAndBlue() {
        if (Color.RED == Color.BLUE) {
            System.out.println("RED is dead");
        } else {
            System.out.println("RED is not BLUE, Java is not color blind");
        }
    }

    @Test
    public void switchTest() {
        Color myColor = Color.WHITE;

        switch (myColor) {
        case WHITE:
            System.out.println("Your color is white");
            break;
        case BLUE:
            System.out.println("Your color is blue");
            break;
        case RED:
            System.out.println("Your color is red");
            break;
        default:
            System.out.println("Your color is not known to the system");
            break;
        }
    }

}
