package com.joao.listacursos.model;

import android.content.Context;
import android.util.Log;

public class AlunoRepository {
    private final DatabaseHelper dbHelper;
    private static final String TAG = "AlunoRepository";

    public AlunoRepository(Context context) {
        Log.d(TAG, "Inicializando AlunoRepository com contexto");
        this.dbHelper = new DatabaseHelper(context);
    }

    public void salvarDadosPessoais(String primeiroNome, String sobrenome, String telefone, String curso) {
        Log.d(TAG, "Chamando salvarDados no DatabaseHelper");
        dbHelper.salvarDados(primeiroNome, sobrenome, telefone, curso);
    }

    public Aluno getDadosPessoais() {
        Log.d(TAG, "Chamando getDados no DatabaseHelper");
        return dbHelper.getDados();
    }

    public boolean excluirDadosPessoais() {
        Log.d(TAG, "Chamando excluirDados no DatabaseHelper");
        return dbHelper.excluirDados();
    }
};