package santi.denis.receitas.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import santi.denis.receitas.R;
import santi.denis.receitas.db.BancoController;
import santi.denis.receitas.db.CriaBanco;

public class Receitas extends AppCompatActivity {

    String codigo = null;
    BancoController bancoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receitas);

        codigo = this.getIntent().getStringExtra("codigo");

        if (codigo != null) {
            bancoController = new BancoController(getBaseContext());
            TextView nome = findViewById(R.id.tv_nome);
            TextView ingredientes = findViewById(R.id.tv_ingredientes);
            TextView modo_preparo = findViewById(R.id.tv_modo_preparo);
            Cursor cursor = bancoController.carregaDadosById(Integer.parseInt(codigo));
            nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME)));
            ingredientes.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.INGREDIENTES)));
            modo_preparo.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.MODO_PREPARO)));
        }
    }
}
