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

public class MainActivity extends AppCompatActivity {
    private TextInputEditText edtPrimeiroNome, edtSobrenome, edtTelefone;
    private Spinner spinnerCursos;
    private Button btnSalvar, btnLimpar, btnMeusDados, btnFinalizar;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        setupController();
        setupSpinner();
        controller.carregarDadosSalvos();
        setupListeners();
    }

    private void initializeComponents() {
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
        controller = new MainController(new AlunoRepository(this), this);
    }

    private void setupSpinner() {
        SpinnerConfig.getInstance(this).configure(this, spinnerCursos);
    }

    private void setupListeners() {
        btnSalvar.setOnClickListener(v -> controller.salvarDados(
                edtPrimeiroNome.getText().toString(),
                edtSobrenome.getText().toString(),
                edtTelefone.getText().toString(),
                spinnerCursos.getSelectedItem().toString()
        ));
        btnLimpar.setOnClickListener(v -> controller.limparCampos(
                edtPrimeiroNome.getText().toString(),
                edtSobrenome.getText().toString(),
                edtTelefone.getText().toString(),
                spinnerCursos.getSelectedItemPosition()
        ));
        btnMeusDados.setOnClickListener(v -> controller.verMeusDados());
        btnFinalizar.setOnClickListener(v -> controller.finalizarApp());
    }

    public void clearFields() {
        edtPrimeiroNome.setText("");
        edtSobrenome.setText("");
        edtTelefone.setText("");
        spinnerCursos.setSelection(0);
    }

    public void setPrimeiroNome(String primeiroNome) {
        edtPrimeiroNome.setText(primeiroNome);
    }

    public void setSobrenome(String sobrenome) {
        edtSobrenome.setText(sobrenome);
    }

    public void setTelefone(String telefone) {
        edtTelefone.setText(telefone);
    }

    public void setCurso(String curso) {
        SpinnerConfig.getInstance(this).selectCurso(spinnerCursos, curso);
    }
};