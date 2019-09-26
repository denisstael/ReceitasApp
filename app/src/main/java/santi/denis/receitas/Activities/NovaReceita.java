package santi.denis.receitas.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import santi.denis.receitas.R;
import santi.denis.receitas.db.BancoController;
import santi.denis.receitas.db.CriaBanco;

public class NovaReceita extends AppCompatActivity {

    String codigo = null;
    BancoController bancoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_receita);
        codigo = this.getIntent().getStringExtra("codigo");

        if (codigo != null) {
            bancoController = new BancoController(getBaseContext());
            EditText nome = findViewById(R.id.editText);
            EditText ingredientes = findViewById(R.id.editText2);
            EditText modo_preparo = findViewById(R.id.editText3);
            TextView id = findViewById(R.id.textView);
            Cursor cursor = bancoController.carregaDadosById(Integer.parseInt(codigo));
            id.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID)));
            nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME)));
            ingredientes.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.INGREDIENTES)));
            modo_preparo.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.MODO_PREPARO)));
        }
    }

    public void onClickCadastrar(View view) {
        BancoController bancoController = new BancoController(getBaseContext());
        TextView id = findViewById(R.id.textView);
        EditText nome = findViewById(R.id.editText);
        EditText ingredientes = findViewById(R.id.editText2);
        EditText modo_preparo = findViewById(R.id.editText3);
        String nome_String = nome.getText().toString();
        String ingredientes_String = ingredientes.getText().toString();
        String modo_preparo_String = modo_preparo.getText().toString();
        String id_String = id.getText().toString();
        String resultado;
        if(id_String == "") {
            resultado = bancoController.insereReceita(nome_String, ingredientes_String, modo_preparo_String);
        }else{
            resultado = bancoController.alteraReceita(id_String, nome_String, ingredientes_String, modo_preparo_String);
        }
        Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickExcluir(View view) {
        TextView id = findViewById(R.id.textView);
        String idString = id.getText().toString();
        BancoController bancoController = new BancoController(getBaseContext());
        String resultado = bancoController.excluiReceita(idString);
        Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
