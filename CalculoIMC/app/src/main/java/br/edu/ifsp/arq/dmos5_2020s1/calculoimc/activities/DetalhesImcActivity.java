package br.edu.ifsp.arq.dmos5_2020s1.calculoimc.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.R;
import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.model.Pessoa;

public class DetalhesImcActivity extends AppCompatActivity {

    private TextView imcTextView;
    private TextView resultadoTextView;
    private TextView detalhesTextView;

    private Pessoa mPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_imc);

        imcTextView = findViewById(R.id.textview_saida_imc);
        resultadoTextView = findViewById(R.id.textview_saida_resultado);
        detalhesTextView = findViewById(R.id.textview_saida_detalhes);

        extrairArgumentos();
        exibeDados();
    }

    private void extrairArgumentos(){
        Intent intent = getIntent();
        Bundle embrulho = intent.getExtras();

        if(embrulho != null){
            double arg1 = embrulho.getDouble(MainActivity.KEY_PESO);
            double arg2 = embrulho.getDouble(MainActivity.KEY_ALTURA);

            mPessoa = new Pessoa(arg1, arg2);
        }
    }

    private void exibeDados(){
        imcTextView.setText(String.format("%.2f", mPessoa.imc()));
        resultadoTextView.setText(getString(mPessoa.resultado()));
        detalhesTextView.setText(getString(mPessoa.explicativo()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
