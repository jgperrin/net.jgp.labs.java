package net.jgp.labs.java.string.lab200_basic_conversions;

public class BasicConversionsApp {

    public static void main(String[] args) {
        BasicConversionsApp app = new BasicConversionsApp();
        app.start();
    }

    private boolean start() {
        String s = "toto";
        System.out.println(s);
        System.out.println(s.getBytes());
        System.out.println(new String(s.getBytes()));
        return true;
    }
    
}
