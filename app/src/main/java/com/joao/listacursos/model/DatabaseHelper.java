package com.joao.listacursos.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "aluno_database.db";
    private static final int DATABASE_VERSION = 5; // Incrementado para recriar o banco
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

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALUNO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNO);
        onCreate(db);
    }

    private SQLiteDatabase getDb() {
        if (db == null || !db.isOpen()) {
            db = getWritableDatabase();
        }
        return db;
    }

    public long salvarDados(String primeiroNome, String sobrenome, String telefone, String curso) {
        try {
            SQLiteDatabase db = getDb();
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRIMEIRO_NOME, primeiroNome);
            values.put(COLUMN_SOBRENOME, sobrenome);
            values.put(COLUMN_TELEFONE, telefone);
            values.put(COLUMN_CURSO, curso);
            return db.insert(TABLE_ALUNO, null, values);
        } catch (Exception e) {
            Log.e(TAG, "Erro ao salvar dados: " + e.getMessage());
            return -1;
        }
    }

    public List<Aluno> getTodosDados() {
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getDb();
            cursor = db.query(TABLE_ALUNO, new String[]{COLUMN_PRIMEIRO_NOME, COLUMN_SOBRENOME, COLUMN_TELEFONE, COLUMN_CURSO},
                    null, null, null, null, null);
            while (cursor.moveToNext()) {
                String primeiroNome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIMEIRO_NOME));
                String sobrenome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOBRENOME));
                String telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE));
                String curso = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CURSO));
                alunos.add(new Aluno(primeiroNome, sobrenome, telefone, curso));
            }
        } catch (Exception e) {
            Log.e(TAG, "Erro ao recuperar dados: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return alunos;
    }

    public boolean excluirDados() {
        try {
            SQLiteDatabase db = getDb();
            int rowsDeleted = db.delete(TABLE_ALUNO, null, null);
            return rowsDeleted > 0;
        } catch (Exception e) {
            Log.e(TAG, "Erro ao excluir dados: " + e.getMessage());
            return false;
        }
    }
};