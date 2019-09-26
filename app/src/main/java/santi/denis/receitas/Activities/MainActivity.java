package santi.denis.receitas.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import santi.denis.receitas.R;
import santi.denis.receitas.db.BancoController;
import santi.denis.receitas.db.CriaBanco;

public class MainActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BancoController bancoController = new BancoController(getBaseContext());
        final Cursor cursor = bancoController.carregaDados();

        String[] nomeCampos = new String[]{CriaBanco.NOME};
        int[] idViews = new int[]{R.id.nome};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_receitas_adapter, cursor, nomeCampos, idViews, 0);
        lista = findViewById(R.id.lvReceitas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));

                Intent intent = new Intent(getBaseContext(), Receitas.class);
                Bundle params = new Bundle();
                params.putString("codigo", codigo);
                intent.putExtras(params);
                startActivity(intent);
            }
        });
    }

    public void onClickAdd(View view) {
        Intent intent = new Intent(this, NovaReceita.class);
        startActivity(intent);
    }
}
