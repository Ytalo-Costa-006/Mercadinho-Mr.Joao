package com.example.mercadinomrjoao;

public class DataClass {
    private String campo1;
    private String campo2;
    private String campo3;
    private int campo4;
    // Construtor
    public DataClass(String campo1, String campo2, String campo3, int campo4) {
        this.campo1 = campo1;
        this.campo2 = campo2;
        this.campo3 = campo3;
        this.campo4 = campo4;
    }

    // Getters
    public String getCampo1() {
        return campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public String getCampo3() {
        return campo3;
    }
    public int getCampo4(){
        return campo4;
    }
}
