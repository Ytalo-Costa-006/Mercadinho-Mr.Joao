package com.example.mercadinomrjoao;

public class DataClassClientes {
    private String nome;
    private String cpf;
    private String diaVencimento;
    private int id;

    public DataClassClientes(String nome, String cpf, String diaVencimento, int id) {
        this.nome = nome;
        this.cpf = cpf;
        this.diaVencimento = diaVencimento;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDiaVencimento() {
        return diaVencimento;
    }

    public int getId() {
        return id;
    }
}
