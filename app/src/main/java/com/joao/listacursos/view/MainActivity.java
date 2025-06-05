package com.joao.listacursos.view;

import android.os.Bundle;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.joao.listacursos.R;
import com.joao.listacursos.controller.MainController;
import com.google.android.material.button.MaterialButton;
import com.joao.listacursos.model.AlunoRepository;
import com.joao.listacursos.util.SpinnerConfig;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText primeiroNomeEdit, sobrenomeEdit, telefoneEdit;
    private Spinner cursosSpinner;
    private MaterialButton salvarButton, limparButton, finalizarButton;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initController();
        initSpinner();
        setupListeners();
        controller.loadSavedData();
    }

    private void initComponents() {
        primeiroNomeEdit = findViewById(R.id.edtPrimeiroNome);
        sobrenomeEdit = findViewById(R.id.edtSobrenome);
        telefoneEdit = findViewById(R.id.edtTelefone);
        cursosSpinner = findViewById(R.id.spinnerCursos);
        salvarButton = findViewById(R.id.btnSalvar);
        limparButton = findViewById(R.id.btnLimpar);
        finalizarButton = findViewById(R.id.btnFinalizar);
    }

    private void initController() {
        controller = new MainController(new AlunoRepository(this), this);
    }

    private void initSpinner() {
        SpinnerConfig.getInstance(this).configure(this, cursosSpinner);
    }

    private void setupListeners() {
        salvarButton.setOnClickListener(v -> controller.saveData(
                primeiroNomeEdit.getText().toString(),
                sobrenomeEdit.getText().toString(),
                telefoneEdit.getText().toString(),
                cursosSpinner.getSelectedItem().toString()));
        limparButton.setOnClickListener(v -> controller.clearFields(
                primeiroNomeEdit.getText().toString(),
                sobrenomeEdit.getText().toString(),
                telefoneEdit.getText().toString(),
                cursosSpinner.getSelectedItemPosition()));
        finalizarButton.setOnClickListener(v -> controller.finishApp());
    }

    public void clearFields() {
        primeiroNomeEdit.setText("");
        sobrenomeEdit.setText("");
        telefoneEdit.setText("");
        cursosSpinner.setSelection(0);
    }

    public void setPrimeiroNome(String primeiroNome) {
        primeiroNomeEdit.setText(primeiroNome);
    }

    public void setSobrenome(String sobrenome) {
        sobrenomeEdit.setText(sobrenome);
    }

    public void setTelefone(String telefone) {
        telefoneEdit.setText(telefone);
    }

    public void setCurso(String curso) {
        SpinnerConfig.getInstance(this).selectCurso(cursosSpinner, curso);
    }
};