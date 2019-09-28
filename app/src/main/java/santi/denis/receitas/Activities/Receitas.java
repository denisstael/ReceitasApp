package santi.denis.receitas.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import santi.denis.receitas.R;
import santi.denis.receitas.db.BancoController;
import santi.denis.receitas.db.CriaBanco;

public class Receitas extends AppCompatActivity {

    String codigo = null;
    BancoController bancoController;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receitas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        codigo = this.getIntent().getStringExtra("codigo");
        button = findViewById(R.id.editar);

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditarReceita.class);
                Bundle params = new Bundle();
                params.putString("codigo", codigo);
                intent.putExtras(params);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                funcao();
                break;
            default:break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            funcao();
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void funcao() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle params = new Bundle();
        params.putString("codigo", codigo);
        intent.putExtras(params);
        startActivity(intent);  //O efeito ao ser pressionado do botão (no caso abre a activity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
        }
    }
}
