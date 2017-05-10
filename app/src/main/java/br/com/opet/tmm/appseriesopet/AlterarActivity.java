package br.com.opet.tmm.appseriesopet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarActivity extends Activity {

    EditText serie;
    EditText temporadas;
    EditText episodios;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        serie = (EditText)findViewById(R.id.editText4);
        temporadas = (EditText)findViewById(R.id.editText5);
        episodios = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        serie.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        temporadas.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TEMPORADAS)));
        episodios.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EPISODIOS)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Serie serieObj = new Serie();
                serieObj.set_ID(Integer.parseInt(codigo));
                serieObj.setTitulo(serie.getText().toString());
                serieObj.setTemporadas(Integer.parseInt(temporadas.getText().toString()));
                serieObj.setEpisodios(Integer.parseInt(episodios.getText().toString()));
                crud.alteraRegistro(serieObj);
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
