package com.joao.listacursos.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.joao.listacursos.R;
import com.joao.listacursos.model.AlunoRepository;

public class DadosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDados);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AlunoAdapter(new AlunoRepository(this).getDadosPessoais()));
        findViewById(R.id.tvNoData).setVisibility(recyclerView.getAdapter().getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
};