package com.joao.listacursos.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AlunoRepository {
    private static final String PREFS_FILE = "dados_usuario";
    private static final String KEY_PRIMEIRO_NOME = "primeiro_nome";
    private static final String KEY_SOBRENOME = "sobrenome";
    private static final String KEY_TELEFONE = "telefone";
    private static final String KEY_CURSO = "curso";
    private final Context context;
    private static final String TAG = "AlunoRepository";

    public AlunoRepository(Context context) {
        this.context = context;
    }

    public void salvarDadosPessoais(String primeiroNome, String sobrenome, String telefone, String curso) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_PRIMEIRO_NOME, primeiroNome);
        editor.putString(KEY_SOBRENOME, sobrenome);
        editor.putString(KEY_TELEFONE, telefone);
        editor.putString(KEY_CURSO, curso);
        editor.apply();
        Log.d(TAG, "Dados salvos: primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", telefone=" + telefone + ", curso=" + curso);
    }

    public Aluno getDadosPessoais() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        String primeiroNome = prefs.getString(KEY_PRIMEIRO_NOME, null);
        String sobrenome = prefs.getString(KEY_SOBRENOME, null);
        String telefone = prefs.getString(KEY_TELEFONE, null);
        String curso = prefs.getString(KEY_CURSO, null);
        Log.d(TAG, "Dados lidos: primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", telefone=" + telefone + ", curso=" + curso);
        if (primeiroNome != null && sobrenome != null && telefone != null && curso != null) {
            return new Aluno(primeiroNome, sobrenome, telefone, curso);
        }
        return null;
    }

    public boolean excluirDadosPessoais() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        boolean isCleared = prefs.getString(KEY_PRIMEIRO_NOME, null) == null;
        Log.d(TAG, "Dados excluídos: " + (isCleared ? "Sucesso" : "Falha"));
        return isCleared;
    }
};