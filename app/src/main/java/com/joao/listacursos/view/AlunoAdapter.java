package com.joao.listacursos.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.joao.listacursos.R;
import com.joao.listacursos.model.Aluno;
import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.ViewHolder> {
    private final List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.primeiroNomeText.setText(aluno.getPrimeiroNome());
        holder.sobrenomeText.setText(aluno.getSobrenome());
        holder.telefoneText.setText(aluno.getTelefone());
        holder.cursoText.setText(aluno.getCurso());
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView primeiroNomeText, sobrenomeText, telefoneText, cursoText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            primeiroNomeText = itemView.findViewById(R.id.tvPrimeiroNome);
            sobrenomeText = itemView.findViewById(R.id.tvSobrenome);
            telefoneText = itemView.findViewById(R.id.tvTelefone);
            cursoText = itemView.findViewById(R.id.tvCurso);
        }
    }
};