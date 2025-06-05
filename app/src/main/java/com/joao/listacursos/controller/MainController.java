package com.joao.listacursos.controller;

import com.joao.listacursos.model.Aluno;
import com.joao.listacursos.model.AlunoRepository;
import com.joao.listacursos.util.UiUtils;
import com.joao.listacursos.view.MainActivity;
import java.util.List;

public class MainController {
    private final AlunoRepository repository;
    private final MainActivity view;

    public MainController(AlunoRepository repository, MainActivity view) {
        this.repository = repository;
        this.view = view;
    }

    public void saveData(String primeiroNome, String sobrenome, String telefone, String curso) {
        if (isEmpty(primeiroNome) || isEmpty(sobrenome) || isEmpty(telefone) || curso.equals("Selecione um curso")) {
            UiUtils.showToast(view, "Preencha todos os campos corretamente!");
            return;
        }
        repository.salvarDadosPessoais(primeiroNome, sobrenome, telefone, curso);
        UiUtils.showToast(view, "Dados salvos com sucesso!");
        view.clearFields();
    }

    public void clearFields(String primeiroNome, String sobrenome, String telefone, int spinnerPosition) {
        boolean camposVazios = isEmpty(primeiroNome) && isEmpty(sobrenome) && isEmpty(telefone) && spinnerPosition == 0;
        boolean dadosExcluidos = repository.excluirDadosPessoais();
        if (camposVazios && !dadosExcluidos) {
            UiUtils.showToast(view, "Ao menos digite algo primeiro, seu otário!");
        } else {
            view.clearFields();
            UiUtils.showToast(view, "Campos limpos e dados excluídos!");
        }
    }

    public void finishApp() {
        UiUtils.showToast(view, "Volte numa próxima!", true);
        UiUtils.finishWithDelay(view, 2000);
    }

    public void loadSavedData() {
        List<Aluno> alunos = repository.getDadosPessoais();
        if (!alunos.isEmpty()) {
            Aluno ultimoAluno = alunos.get(alunos.size() - 1);
            view.setPrimeiroNome(ultimoAluno.getPrimeiroNome());
            view.setSobrenome(ultimoAluno.getSobrenome());
            view.setTelefone(ultimoAluno.getTelefone());
            view.setCurso(ultimoAluno.getCurso());
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
};