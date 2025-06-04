package com.joao.listacursos.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AlunoRepository {
    private static final String PREFS_FILE = "dados_usuario";
    private static final String KEY_NOME = "nome";
    private static final String KEY_TELEFONE = "telefone";
    private static final String KEY_CURSO = "curso";
    private final Context context;
    private static final String TAG = "AlunoRepository";

    public AlunoRepository(Context context) {
        this.context = context;
    }

    public void salvarDadosPessoais(String nome, String telefone, String curso) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NOME, nome);
        editor.putString(KEY_TELEFONE, telefone);
        editor.putString(KEY_CURSO, curso);
        editor.apply();
        Log.d(TAG, "Dados salvos: nome=" + nome + ", telefone=" + telefone + ", curso=" + curso);
    }

    public Aluno getDadosPessoais() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        String nome = prefs.getString(KEY_NOME, null);
        String telefone = prefs.getString(KEY_TELEFONE, null);
        String curso = prefs.getString(KEY_CURSO, null);
        Log.d(TAG, "Dados lidos: nome=" + nome + ", telefone=" + telefone + ", curso=" + curso);
        if (nome != null && telefone != null && curso != null) {
            return new Aluno(nome, telefone, curso);
        }
        return null;
    }

    public boolean excluirDadosPessoais() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        boolean isCleared = prefs.getString(KEY_NOME, null) == null;
        Log.d(TAG, "Dados excluídos: " + (isCleared ? "Sucesso" : "Falha"));
        return isCleared;
    }
};