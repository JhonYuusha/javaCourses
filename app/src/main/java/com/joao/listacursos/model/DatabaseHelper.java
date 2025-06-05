package com.joao.listacursos.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "aluno_database.db";
    private static final int DATABASE_VERSION = 2; // Incrementado para recriar o banco, se necessário
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

    private SQLiteDatabase writableDb;
    private SQLiteDatabase readableDb;
    private final Object lock = new Object(); // Para sincronização

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "DatabaseHelper inicializado");
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

    private SQLiteDatabase getWritableDb() {
        synchronized (lock) {
            if (writableDb == null || !writableDb.isOpen()) {
                writableDb = getWritableDatabase();
                Log.d(TAG, "Abrindo writableDb");
            }
            return writableDb;
        }
    }

    private SQLiteDatabase getReadableDb() {
        synchronized (lock) {
            if (readableDb == null || !readableDb.isOpen()) {
                readableDb = getReadableDatabase();
                Log.d(TAG, "Abrindo readableDb");
            }
            return readableDb;
        }
    }

    public long salvarDados(String primeiroNome, String sobrenome, String telefone, String curso) {
        synchronized (lock) {
            try {
                SQLiteDatabase db = getWritableDb();
                ContentValues values = new ContentValues();
                values.put(COLUMN_PRIMEIRO_NOME, primeiroNome);
                values.put(COLUMN_SOBRENOME, sobrenome);
                values.put(COLUMN_TELEFONE, telefone);
                values.put(COLUMN_CURSO, curso);

                // Deleta o registro existente (mantém apenas um registro)
                db.delete(TABLE_ALUNO, null, null);

                long result = db.insert(TABLE_ALUNO, null, values);
                Log.d(TAG, "Dados salvos: primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", telefone=" + telefone + ", curso=" + curso + ", result=" + result);
                return result;
            } catch (Exception e) {
                Log.e(TAG, "Erro ao salvar dados: " + e.getMessage());
                return -1;
            }
        }
    }

    public Aluno getDados() {
        synchronized (lock) {
            try {
                SQLiteDatabase db = getReadableDb();
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
                } else {
                    Log.d(TAG, "Nenhum dado encontrado na tabela aluno");
                }
                cursor.close();
                return aluno;
            } catch (Exception e) {
                Log.e(TAG, "Erro ao recuperar dados: " + e.getMessage());
                return null;
            }
        }
    }

    public boolean excluirDados() {
        synchronized (lock) {
            try {
                SQLiteDatabase db = getWritableDb();
                int rowsDeleted = db.delete(TABLE_ALUNO, null, null);
                Log.d(TAG, "Dados excluídos: " + (rowsDeleted > 0 ? "Sucesso" : "Nenhum dado para excluir") + ", rowsDeleted=" + rowsDeleted);
                return rowsDeleted > 0 || getDados() == null;
            } catch (Exception e) {
                Log.e(TAG, "Erro ao excluir dados: " + e.getMessage());
                return false;
            }
        }
    }

    public void closeDatabase() {
        synchronized (lock) {
            if (writableDb != null && writableDb.isOpen()) {
                writableDb.close();
                writableDb = null;
                Log.d(TAG, "writableDb fechado");
            }
            if (readableDb != null && readableDb.isOpen()) {
                readableDb.close();
                readableDb = null;
                Log.d(TAG, "readableDb fechado");
            }
        }
    }
};