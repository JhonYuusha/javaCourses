package com.joao.listacursos.controller;

import com.joao.listacursos.model.Aluno;
import com.joao.listacursos.model.AlunoRepository;
import com.joao.listacursos.view.MainActivity;

// Classe que gerencia a lógica de interação entre a MainActivity (View) e o AlunoRepository (Model).
public class MainController {
    private final AlunoRepository repository; // Repositório para gerenciar dados
    private final MainActivity view; // View para atualizar a interface

    // Construtor que recebe o repositório e a View
    public MainController(AlunoRepository repository, MainActivity view) {
        this.repository = repository;
        this.view = view;
    }

    // Salva os dados do usuário, validando os campos antes.
    public void salvarDados(String primeiroNome, String sobrenome, String telefone, String curso) {
        if (primeiroNome.trim().isEmpty() || sobrenome.trim().isEmpty() || telefone.trim().isEmpty() || curso.equals("Selecione um curso")) {
            view.mostrarMensagem("Preencha todos os campos corretamente"); // Mostra erro se os campos não forem válidos
            return;
        }
        String nomeCompleto = primeiroNome + " " + sobrenome; // Concatena primeiro nome e sobrenome
        repository.salvarDadosPessoais(nomeCompleto, telefone, curso); // Salva os dados pessoais no SharedPreferences
        view.mostrarMensagem("Dados salvos com sucesso!"); // Exibe mensagem de sucesso
        view.limparCampos(); // Limpa os campos da interface
    }

    // Exibe os dados pessoais do usuário em um diálogo.
    public void verMeusDados() {
        Aluno aluno = repository.getDadosPessoais(); // Recupera os dados pessoais do SharedPreferences
        if (aluno == null) {
            view.mostrarMensagem("Nenhum dado encontrado"); // Mostra mensagem se não houver dados
        } else {
            String mensagem = "Curso: " + aluno.getCurso() + "\nNome: " + aluno.getNome() + "\nTelefone: " + aluno.getTelefone(); // Formata a mensagem
            view.mostrarDialog("Seus Dados", mensagem); // Exibe os dados em um diálogo
        }
    }

    // Limpa os campos, verificando se há algo para limpar.
    public void limparCampos(String primeiroNome, String sobrenome, String telefone, int spinnerPosition) {
        if (primeiroNome.trim().isEmpty() && sobrenome.trim().isEmpty() && telefone.trim().isEmpty() && spinnerPosition == 0) {
            view.mostrarMensagem("Ao menos digite algo primeiro, seu otário!"); // Mensagem se todos os campos estiverem vazios
        } else {
            view.limparCampos(); // Limpa os campos
            view.mostrarMensagem("Campos limpos!"); // Confirma a limpeza
        }
    }

    // Finaliza o aplicativo com uma mensagem e atraso.
    public void finalizarApp() {
        view.mostrarMensagem("Volte numa próxima"); // Exibe mensagem de despedida
        view.finalizarComAtraso(); // Finaliza após 2 segundos
    }
};