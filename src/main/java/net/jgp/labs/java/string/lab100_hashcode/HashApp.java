package net.jgp.labs.java.string.lab100_hashcode;

public class HashApp {

    public static void main(String[] args) {
        HashApp app = new HashApp();
        app.start();
    }

    private boolean start() {
        String s = "identifier";
        System.out.println(s.hashCode());
        return true;
    }
    
}
