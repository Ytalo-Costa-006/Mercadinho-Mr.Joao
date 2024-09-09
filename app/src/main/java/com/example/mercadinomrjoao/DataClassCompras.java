package com.example.mercadinomrjoao;

public class DataClassCompras {

    private String campo1Compras;
    private String campo2Compras;

    public String getCampo1Compras() {
        return campo1Compras;
    }

    public String getCampo2Compras() {
        return campo2Compras;
    }

    public String getCampo3Compras() {
        return campo3Compras;
    }

    public int getCampo4Compras() {
        return campo4Compras;
    }

    private String campo3Compras;

    public DataClassCompras(String campo1Compras, String campo2Compras, String campo3Compras, int campo4Compras) {
        this.campo1Compras = campo1Compras;
        this.campo2Compras = campo2Compras;
        this.campo3Compras = campo3Compras;
        this.campo4Compras = campo4Compras;
    }

    private int campo4Compras;
}
