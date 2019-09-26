package santi.denis.receitas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "receitas";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String INGREDIENTES = "ingredientes";
    public static final String MODO_PREPARO = "modo_preparo";
    private static final int VERSAO = 1;

    public CriaBanco (Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + "("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + INGREDIENTES + " text,"
                + MODO_PREPARO + " text"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
