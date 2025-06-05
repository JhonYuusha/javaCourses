package com.joao.listacursos.model;

import android.content.Context;

public class AlunoRepository {
    private final DatabaseHelper dbHelper;

    public AlunoRepository(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public void salvarDadosPessoais(String primeiroNome, String sobrenome, String telefone, String curso) {
        dbHelper.salvarDados(primeiroNome, sobrenome, telefone, curso);
    }

    public Aluno getDadosPessoais() {
        return dbHelper.getDados();
    }

    public boolean excluirDadosPessoais() {
        return dbHelper.excluirDados();
    }
};