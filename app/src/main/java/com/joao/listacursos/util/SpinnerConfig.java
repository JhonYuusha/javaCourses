package com.joao.listacursos.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

// Classe utilitária para configurar o Spinner de cursos.
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

    public static void configureCursosSpinner(Context context, Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, CURSOS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
};