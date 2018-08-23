package net.jgp.labs.enums.lab110complexColor;

public enum Color {
    RED("#ff0000"), WHITE("#ffffff"), BLUE("#0000ff");

    private String hexCode;

    private Color(String hexCode) {
        this.hexCode = hexCode;
    }
    
    public String getHexCode() {
        return this.hexCode;
    }
}
