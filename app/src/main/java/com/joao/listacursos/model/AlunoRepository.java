package com.joao.listacursos.model;

import android.content.Context;
import android.content.SharedPreferences;

// Classe responsável por gerenciar os dados pessoais do aluno (Model) usando SharedPreferences.
public class AlunoRepository {
    private static final String PREFS = "dados_usuario"; // Nome do SharedPreferences para dados pessoais
    private final Context context; // Contexto da aplicação

    // Construtor que recebe o contexto da aplicação
    public AlunoRepository(Context context) {
        this.context = context;
    }

    // Salva os dados pessoais do usuário (nome completo, telefone, curso) no SharedPreferences.
    public void salvarDadosPessoais(String nome, String telefone, String curso) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nome", nome); // Salva o nome completo
        editor.putString("telefone", telefone); // Salva o telefone
        editor.putString("curso", curso); // Salva o curso
        editor.apply(); // Aplica as alterações de forma assíncrona
    }

    // Recupera os dados pessoais do usuário do SharedPreferences, retornando um objeto Aluno ou null se não existirem.
    public Aluno getDadosPessoais() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String nome = prefs.getString("nome", null); // Recupera o nome completo
        String telefone = prefs.getString("telefone", null); // Recupera o telefone
        String curso = prefs.getString("curso", null); // Recupera o curso
        if (nome != null && telefone != null && curso != null) {
            return new Aluno(nome, telefone, curso); // Retorna um objeto Aluno se todos os dados existirem
        }
        return null; // Retorna null se algum dado estiver faltando
    }
};