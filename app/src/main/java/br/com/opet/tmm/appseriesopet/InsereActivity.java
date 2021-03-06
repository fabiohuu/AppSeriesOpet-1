package br.com.opet.tmm.appseriesopet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsereActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere);

        Button botao = (Button)findViewById(R.id.button);

        /*
         * Questão 11: O que são Listeners? Em quais casos eles são utilizados?.
         * */
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.editText);
                String tituloString = titulo.getText().toString();
                String resultado;

                Serie serie = new Serie(tituloString);

                resultado = crud.insereDado(serie);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(InsereActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
