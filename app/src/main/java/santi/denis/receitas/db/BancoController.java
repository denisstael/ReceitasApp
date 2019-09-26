package santi.denis.receitas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController (Context context) {
        banco = new CriaBanco(context);
    }

    public String insereReceita(String nome, String ingredientes, String modo_preparo) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.INGREDIENTES, ingredientes);
        valores.put(CriaBanco.MODO_PREPARO, modo_preparo);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao inserir receita";
        else
            return "Receita salva!";
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {banco.ID, banco.NOME};

        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, banco.ID);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }

    public Cursor carregaDadosById(int id) {
        Cursor cursor;
        String[] campos = {banco.ID, banco.NOME, banco.INGREDIENTES, banco.MODO_PREPARO};
        String where = banco.ID + "=?";
        String[] args = {String.valueOf(id)};

        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, where, args, null, null, banco.ID);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }

    public String alteraReceita(String id, String nome, String ingredientes, String modo_preparo) {
        ContentValues valores;
        String where;
        db = banco.getWritableDatabase();
        where = CriaBanco.ID + "=?";
        String[] args = new String[]{id};
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.INGREDIENTES, ingredientes);
        valores.put(CriaBanco.MODO_PREPARO, modo_preparo);
        long resultado = db.update(CriaBanco.TABELA, valores, where, args);
        db.close();

        if(resultado == -1)
            return "Erro ao alterar receita";
        else
            return "Receita alterada!";

    }

    public String excluiReceita(String id) {
        String where = banco.ID + "=?";
        db = banco.getReadableDatabase();
        long resultado = db.delete(banco.TABELA, where, new String[]{id});
        db.close();

        if(resultado == -1)
            return "Erro ao excluir receita";
        else
            return "Receita excluída!";
    }
}
