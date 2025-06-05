package com.joao.listacursos.model;

import android.content.Context;

import java.util.List;

public class AlunoRepository {
    private final DatabaseHelper dbHelper;

    public AlunoRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void salvarDadosPessoais(String primeiroNome, String sobrenome, String telefone, String curso) {
        dbHelper.salvarDados(primeiroNome, sobrenome, telefone, curso);
    }

    public List<Aluno> getDadosPessoais() {
        return dbHelper.getTodosDados();
    }

    public boolean excluirDadosPessoais() {
        return dbHelper.excluirDados();
    }
};