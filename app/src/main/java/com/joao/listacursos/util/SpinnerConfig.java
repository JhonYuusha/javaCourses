package com.joao.listacursos.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerConfig {
    private static final String[] CURSOS = {
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
    };

    private static SpinnerConfig instance;

    private SpinnerConfig() {}

    public static SpinnerConfig getInstance(Context context) {
        if (instance == null) {
            instance = new SpinnerConfig();
        }
        return instance;
    }

    public void configure(Context context, Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, CURSOS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void selectCurso(Spinner spinner, String curso) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
        int position = adapter.getPosition(curso);
        if (position >= 0) {
            spinner.setSelection(position);
        } else {
            spinner.setSelection(0); // Default para "Selecione um curso"
        }
    }
};