package com.joao.listacursos.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.joao.listacursos.R;
import com.joao.listacursos.controller.MainController;
import com.joao.listacursos.model.AlunoRepository;
import com.joao.listacursos.util.SpinnerConfig;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText edtPrimeiroNome, edtSobrenome, edtTelefone;
    private Spinner spinnerCursos;
    private Button btnSalvar, btnLimpar, btnMeusDados, btnFinalizar;
    private MainController controller;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate chamado");

        initializeComponents();
        setupController();
        setupSpinner();
        controller.carregarDadosSalvos();
        setupListeners();
    }

    private void initializeComponents() {
        Log.d(TAG, "Inicializando componentes");
        edtPrimeiroNome = findViewById(R.id.edtPrimeiroNome);
        edtSobrenome = findViewById(R.id.edtSobrenome);
        edtTelefone = findViewById(R.id.edtTelefone);
        spinnerCursos = findViewById(R.id.spinnerCursos);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnMeusDados = findViewById(R.id.btnMeusDados);
        btnFinalizar = findViewById(R.id.btnFinalizar);
    }

    private void setupController() {
        Log.d(TAG, "Configurando controller");
        controller = new MainController(new AlunoRepository(this), this);
    }

    private void setupSpinner() {
        Log.d(TAG, "Configurando spinner");
        SpinnerConfig.getInstance(this).configure(this, spinnerCursos);
    }

    private void setupListeners() {
        Log.d(TAG, "Configurando listeners");
        btnSalvar.setOnClickListener(v -> {
            Log.d(TAG, "Botão Salvar clicado");
            controller.salvarDados(
                    edtPrimeiroNome.getText().toString(),
                    edtSobrenome.getText().toString(),
                    edtTelefone.getText().toString(),
                    spinnerCursos.getSelectedItem().toString()
            );
        });
        btnLimpar.setOnClickListener(v -> {
            Log.d(TAG, "Botão Limpar clicado");
            controller.limparCampos(
                    edtPrimeiroNome.getText().toString(),
                    edtSobrenome.getText().toString(),
                    edtTelefone.getText().toString(),
                    spinnerCursos.getSelectedItemPosition()
            );
        });
        btnMeusDados.setOnClickListener(v -> {
            Log.d(TAG, "Botão Ver Meus Dados clicado");
            controller.verMeusDados();
        });
        btnFinalizar.setOnClickListener(v -> {
            Log.d(TAG, "Botão Finalizar clicado");
            controller.finalizarApp();
        });
    }

    public void clearFields() {
        Log.d(TAG, "Limpando campos");
        edtPrimeiroNome.setText("");
        edtSobrenome.setText("");
        edtTelefone.setText("");
        spinnerCursos.setSelection(0);
    }

    public void setPrimeiroNome(String primeiroNome) {
        Log.d(TAG, "Definindo primeiroNome: " + primeiroNome);
        edtPrimeiroNome.setText(primeiroNome);
    }

    public void setSobrenome(String sobrenome) {
        Log.d(TAG, "Definindo sobrenome: " + sobrenome);
        edtSobrenome.setText(sobrenome);
    }

    public void setTelefone(String telefone) {
        Log.d(TAG, "Definindo telefone: " + telefone);
        edtTelefone.setText(telefone);
    }

    public void setCurso(String curso) {
        Log.d(TAG, "Definindo curso: " + curso);
        SpinnerConfig.getInstance(this).selectCurso(spinnerCursos, curso);
    }
};