package com.joao.listacursos.model;

// Classe que representa um aluno com nome completo, telefone e curso.
public class Aluno {
    private String nome;    // Nome completo do aluno (Primeiro Nome + Sobrenome)
    private String telefone; // Telefone do aluno
    private String curso;   // Curso selecionado pelo aluno

    // Construtor
    public Aluno(String nome, String telefone, String curso) {
        this.nome = nome;
        this.telefone = telefone;
        this.curso = curso;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCurso() {
        return curso;
    }
};