package com.joao.listacursos.controller;

import android.util.Log;
import com.joao.listacursos.model.Aluno;
import com.joao.listacursos.model.AlunoRepository;
import com.joao.listacursos.util.UiUtils;
import com.joao.listacursos.view.MainActivity;

public class MainController {
    private final AlunoRepository repository;
    private final MainActivity view;
    private static final String TAG = "MainController";

    public MainController(AlunoRepository repository, MainActivity view) {
        this.repository = repository;
        this.view = view;
    }

    public void salvarDados(String primeiroNome, String sobrenome, String telefone, String curso) {
        if (primeiroNome.trim().isEmpty() || sobrenome.trim().isEmpty() || telefone.trim().isEmpty() || curso.equals("Selecione um curso")) {
            UiUtils.showToast(view, "Preencha todos os campos corretamente");
            return;
        }
        repository.salvarDadosPessoais(primeiroNome, sobrenome, telefone, curso);
        UiUtils.showToast(view, "Dados salvos com sucesso!");
        view.clearFields();
        Log.d(TAG, "Salvando dados: primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", telefone=" + telefone + ", curso=" + curso);
    }

    public void verMeusDados() {
        Aluno aluno = repository.getDadosPessoais();
        if (aluno == null) {
            UiUtils.showToast(view, "Nenhum dado encontrado");
        } else {
            String mensagem = "Curso: " + aluno.getCurso() + "\nPrimeiro Nome: " + aluno.getPrimeiroNome() + "\nSobrenome: " + aluno.getSobrenome() + "\nTelefone: " + aluno.getTelefone();
            UiUtils.showDialog(view, "Seus Dados", mensagem);
        }
    }

    public void limparCampos(String primeiroNome, String sobrenome, String telefone, int spinnerPosition) {
        boolean camposVazios = primeiroNome.trim().isEmpty() && sobrenome.trim().isEmpty() && telefone.trim().isEmpty() && spinnerPosition == 0;
        boolean dadosExcluidos = repository.excluirDadosPessoais();

        if (camposVazios && !dadosExcluidos) {
            UiUtils.showToast(view, "Ao menos digite algo primeiro, seu otário!");
        } else {
            view.clearFields();
            UiUtils.showToast(view, "Campos limpos e dados excluídos!");
        }
        Log.d(TAG, "Limpar campos: camposVazios=" + camposVazios + ", dadosExcluidos=" + dadosExcluidos);
    }

    public void finalizarApp() {
        UiUtils.showToast(view, "Volte numa próxima", true);
        UiUtils.finishWithDelay(view, 2000);
    }

    public void carregarDadosSalvos() {
        Aluno aluno = repository.getDadosPessoais();
        if (aluno != null) {
            view.setPrimeiroNome(aluno.getPrimeiroNome());
            view.setSobrenome(aluno.getSobrenome());
            view.setTelefone(aluno.getTelefone());
            view.setCurso(aluno.getCurso());
            Log.d(TAG, "Dados carregados na inicialização: primeiroNome=" + aluno.getPrimeiroNome() + ", sobrenome=" + aluno.getSobrenome() + ", telefone=" + aluno.getTelefone() + ", curso=" + aluno.getCurso());
        }
    }
};