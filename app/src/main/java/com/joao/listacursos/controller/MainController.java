package com.joao.listacursos.controller;

import com.joao.listacursos.model.Aluno;
import com.joao.listacursos.model.AlunoRepository;
import com.joao.listacursos.util.UiUtils;
import com.joao.listacursos.view.MainActivity;

// Classe que gerencia a lógica de interação entre a View e o Model.
public class MainController {
    private final AlunoRepository repository;
    private final MainActivity view;

    public MainController(AlunoRepository repository, MainActivity view) {
        this.repository = repository;
        this.view = view;
    }

    public void salvarDados(String primeiroNome, String sobrenome, String telefone, String curso) {
        if (primeiroNome.trim().isEmpty() || sobrenome.trim().isEmpty() || telefone.trim().isEmpty() || curso.equals("Selecione um curso")) {
            UiUtils.showToast(view, "Preencha todos os campos corretamente");
            return;
        }
        String nomeCompleto = primeiroNome + " " + sobrenome;
        repository.salvarDadosPessoais(nomeCompleto, telefone, curso);
        UiUtils.showToast(view, "Dados salvos com sucesso!");
        view.clearFields();
    }

    public void verMeusDados() {
        Aluno aluno = repository.getDadosPessoais();
        if (aluno == null) {
            UiUtils.showToast(view, "Nenhum dado encontrado");
        } else {
            String mensagem = "Curso: " + aluno.getCurso() + "\nNome: " + aluno.getNome() + "\nTelefone: " + aluno.getTelefone();
            UiUtils.showDialog(view, "Seus Dados", mensagem);
        }
    }

    public void limparCampos(String primeiroNome, String sobrenome, String telefone, int spinnerPosition) {
        if (primeiroNome.trim().isEmpty() && sobrenome.trim().isEmpty() && telefone.trim().isEmpty() && spinnerPosition == 0) {
            UiUtils.showToast(view, "Ao menos digite algo primeiro, seu otário!");
        } else {
            view.clearFields();
            UiUtils.showToast(view, "Campos limpos!");
        }
    }

    public void finalizarApp() {
        UiUtils.showToast(view, "Volte numa próxima", true);
        UiUtils.finishWithDelay(view, 2000);
    }
};