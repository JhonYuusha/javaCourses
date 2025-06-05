package com.joao.listacursos.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "aluno_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ALUNO = "aluno";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRIMEIRO_NOME = "primeiro_nome";
    private static final String COLUMN_SOBRENOME = "sobrenome";
    private static final String COLUMN_TELEFONE = "telefone";
    private static final String COLUMN_CURSO = "curso";
    private static final String TAG = "DatabaseHelper";

    private static final String CREATE_TABLE_ALUNO = "CREATE TABLE " + TABLE_ALUNO + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PRIMEIRO_NOME + " TEXT NOT NULL, " +
            COLUMN_SOBRENOME + " TEXT NOT NULL, " +
            COLUMN_TELEFONE + " TEXT NOT NULL, " +
            COLUMN_CURSO + " TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALUNO);
        Log.d(TAG, "Tabela aluno criada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNO);
        onCreate(db);
        Log.d(TAG, "Banco de dados atualizado: tabela aluno recriada");
    }

    public long salvarDados(String primeiroNome, String sobrenome, String telefone, String curso) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRIMEIRO_NOME, primeiroNome);
        values.put(COLUMN_SOBRENOME, sobrenome);
        values.put(COLUMN_TELEFONE, telefone);
        values.put(COLUMN_CURSO, curso);

        // Deleta o registro existente (mantém apenas um registro)
        db.delete(TABLE_ALUNO, null, null);

        long result = db.insert(TABLE_ALUNO, null, values);
        db.close();
        Log.d(TAG, "Dados salvos: primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", telefone=" + telefone + ", curso=" + curso + ", result=" + result);
        return result;
    }

    public Aluno getDados() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ALUNO, new String[]{COLUMN_PRIMEIRO_NOME, COLUMN_SOBRENOME, COLUMN_TELEFONE, COLUMN_CURSO},
                null, null, null, null, null);

        Aluno aluno = null;
        if (cursor.moveToFirst()) {
            String primeiroNome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIMEIRO_NOME));
            String sobrenome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOBRENOME));
            String telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE));
            String curso = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CURSO));
            aluno = new Aluno(primeiroNome, sobrenome, telefone, curso);
            Log.d(TAG, "Dados lidos: primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", telefone=" + telefone + ", curso=" + curso);
        }
        cursor.close();
        db.close();
        return aluno;
    }

    public boolean excluirDados() {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_ALUNO, null, null);
        db.close();
        boolean isCleared = rowsDeleted > 0 || getDados() == null;
        Log.d(TAG, "Dados excluídos: " + (isCleared ? "Sucesso" : "Falha") + ", rowsDeleted=" + rowsDeleted);
        return isCleared;
    }
};