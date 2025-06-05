package com.joao.listacursos.model;

public class Aluno {
    private final String primeiroNome;
    private final String sobrenome;
    private final String telefone;
    private final String curso;

    public Aluno(String primeiroNome, String sobrenome, String telefone, String curso) {
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.curso = curso;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCurso() {
        return curso;
    }
};