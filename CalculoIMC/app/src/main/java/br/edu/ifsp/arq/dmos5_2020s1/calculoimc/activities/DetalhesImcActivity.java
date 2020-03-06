package br.edu.ifsp.arq.dmos5_2020s1.calculoimc.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.R;
import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.model.Pessoa;

public class DetalhesImcActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = DetalhesImcActivity.class.getSimpleName();
    private TextView imcTextView;
    private TextView resultadoTextView;
    private TextView detalhesTextView;
    private Button saibaMaisButton;

    private Pessoa mPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_imc);

        imcTextView = findViewById(R.id.textview_saida_imc);
        resultadoTextView = findViewById(R.id.textview_saida_resultado);
        detalhesTextView = findViewById(R.id.textview_saida_detalhes);
        saibaMaisButton = findViewById(R.id.button_saiba_mais);

        detalhesTextView.setMovementMethod(new ScrollingMovementMethod());
        saibaMaisButton.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extrairArgumentos();
        exibeDados();
    }


    @Override
    public void finish() {

        Bundle argumento = new Bundle();
        argumento.putSerializable(MainActivity.KEY_PESSOA, mPessoa);

        Intent intent = new Intent();
        intent.putExtras(argumento);

        setResult(RESULT_OK, intent);
        Log.d(MainActivity.TAG, "Destruindo - ResultCode : " + RESULT_OK);


        super.finish();
    }

    @Override
    public void onClick(View v) {
        if(v == saibaMaisButton){
            String busca = "Como atingir um bom IMC";
            Intent intent = new Intent(Intent.ACTION_SEARCH);
            intent.putExtra(SearchManager.QUERY, busca);
            startActivity(intent);
        }
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
        switch (mPessoa.resultado()){
            case R.string.peso_normal:
                alteraCor(R.color.colorNormal);
                break;
            case R.string.sobrepeso:
                alteraCor(R.color.colorRisco);
                break;
            case R.string.abaixo_do_peso:
            case R.string.obesidade_grau_1:
            case R.string.obesidade_grau_2:
            case R.string.obesidade_grau_3:
                alteraCor(R.color.colorPerigo);
                break;
        }
        imcTextView.setText(String.format("%.2f", mPessoa.imc()));
        resultadoTextView.setText(getString(mPessoa.resultado()));
        detalhesTextView.setText(getString(mPessoa.explicativo()));
    }

    private void alteraCor(int color){
        imcTextView.setTextColor(getColor(color));
        resultadoTextView.setTextColor(getColor(color));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
