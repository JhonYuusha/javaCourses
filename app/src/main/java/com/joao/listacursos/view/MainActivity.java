package com.joao.listacursos.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.joao.listacursos.R;
import com.joao.listacursos.controller.MainController;
import com.joao.listacursos.model.AlunoRepository;

// Classe responsável pela interface do usuário principal (View), configurando componentes e delegando eventos ao Controller.
public class MainActivity extends AppCompatActivity {
    private TextInputEditText edtPrimeiroNome; // Campo para entrada do primeiro nome
    private TextInputEditText edtSobrenome; // Campo para entrada do sobrenome
    private TextInputEditText edtTelefone; // Campo para entrada do telefone
    private Spinner spinnerCursos; // Menu suspenso para seleção de cursos
    private Button btnSalvar; // Botão para salvar dados
    private Button btnLimpar; // Botão para limpar campos
    private Button btnMeusDados; // Botão para visualizar dados pessoais
    private Button btnFinalizar; // Botão para finalizar o app
    private MainController controller; // Instância do controlador para gerenciar lógica

    // Configura a interface e associa eventos aos botões.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da atividade

        // Inicializa os componentes da interface
        edtPrimeiroNome = findViewById(R.id.edtPrimeiroNome);
        edtSobrenome = findViewById(R.id.edtSobrenome);
        edtTelefone = findViewById(R.id.edtTelefone);
        spinnerCursos = findViewById(R.id.spinnerCursos);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnMeusDados = findViewById(R.id.btnMeusDados);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        // Inicializa o controlador, passando o repositório e a própria atividade como View
        controller = new MainController(new AlunoRepository(this), this);
        configurarSpinner(); // Configura o menu suspenso de cursos

        // Associa ações aos botões
        btnSalvar.setOnClickListener(v -> controller.salvarDados(
                edtPrimeiroNome.getText().toString(),
                edtSobrenome.getText().toString(),
                edtTelefone.getText().toString(),
                spinnerCursos.getSelectedItem().toString()
        )); // Delega o salvamento dos dados ao controlador
        btnLimpar.setOnClickListener(v -> controller.limparCampos(
                edtPrimeiroNome.getText().toString(),
                edtSobrenome.getText().toString(),
                edtTelefone.getText().toString(),
                spinnerCursos.getSelectedItemPosition()
        )); // Delega a limpeza dos campos ao controlador
        btnMeusDados.setOnClickListener(v -> controller.verMeusDados()); // Delega a exibição de dados pessoais ao controlador
        btnFinalizar.setOnClickListener(v -> controller.finalizarApp()); // Delega a finalização do app ao controlador
    }

    // Configura o Spinner com a lista de cursos disponíveis.
    private void configurarSpinner() {
        String[] cursos = {
                "Selecione um curso",
                "Dev de Sistemas",
                "Estética",
                "Manicure",
                "Barbeiro",
                "Cozinheiro",
                "Jardinagem",
                "Informática",
                "Segurança de Redes",
                "Design Gráfico",
                "Administração",
                "Marketing Digital"
        }; // Lista de opções do Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cursos); // Cria o adaptador
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Define o layout do menu suspenso
        spinnerCursos.setAdapter(adapter); // Associa o adaptador ao Spinner
    }

    // Limpa os campos da interface, redefinindo os valores padrão.
    public void limparCampos() {
        edtPrimeiroNome.setText(""); // Limpa o campo de primeiro nome
        edtSobrenome.setText(""); // Limpa o campo de sobrenome
        edtTelefone.setText(""); // Limpa o campo de telefone
        spinnerCursos.setSelection(0); // Reseta o Spinner para a primeira opção
    }

    // Exibe uma mensagem Toast na tela.
    public void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show(); // Mostra uma mensagem longa para "Volte numa próxima"
    }
    // Exibe um diálogo simples com título e mensagem.
    public void mostrarDialog(String titulo, String mensagem) {
        new AlertDialog.Builder(this)
                .setTitle(titulo) // Define o título do diálogo
                .setMessage(mensagem) // Define a mensagem do diálogo
                .setPositiveButton("OK", null) // Adiciona um botão "OK" sem ação
                .show(); // Exibe o diálogo
    }

    // Finaliza o aplicativo com atraso de 2 segundos.
    public void finalizarComAtraso() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            finish(); // Fecha a atividade
            System.exit(0); // Encerra o processo do aplicativo
        }, 2000); // Atraso de 2 segundos
    }
};