
package br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.R;
import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.constants.Constantes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_PESO = "peso";
    public static final String KEY_ALTURA = "altura";
    public static final String KEY_PESSOA = "pessoa";
    public static final int CALCULAR_IMC_CODE = 99;

    private EditText pesoEditText;
    private EditText alturaEditText;
    private Button calcularButton;

    private Pessoa mPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperando as referÃªncias dos elementos de layout
        pesoEditText = findViewById(R.id.edittext_peso);
        alturaEditText = findViewById(R.id.edittext_altura);
        calcularButton = findViewById(R.id.button_calcular_imc);

        //Definindo o OnClickListern do botÃ£o
        calcularButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pesoEditText.setText("");
        alturaEditText.setText("");
    }

    @Override
    public void onClick(View v) {
        if(v == calcularButton){
            calculaIMC();
        }
    }

    private void calculaIMC(){
        Pessoa pessoa;
        double peso, altura;
        try{
            peso = Double.valueOf(pesoEditText.getText().toString());
            altura = Double.valueOf(alturaEditText.getText().toString());
        }catch (NumberFormatException nfe){
            peso = 0;
            altura = 0;
        }

        if(peso != 0 && altura != 0){

            //Cria um Bundle (embrulho) com os argumentos necessÃ¡rios na outra activity
            Bundle args = new Bundle();
            //Os dados sÃ£o inseridos no bundle como um objeto do tipo Map (chave, valor)
            args.putDouble(KEY_PESO, peso);
            args.putDouble(KEY_ALTURA, altura);

            //Cria a intenÃ§Ã£o de abrir uma nova tela
            Intent intent = new Intent(this, DetalhesImcActivity.class);
            //Inclui os argumentos na intenÃ§Ã£o
            intent.putExtras(args);
            //Solicita a abertura da nova activity
            //Contudo essa activity receberÃ¡ informaÃ§Ã£o como resultado
            startActivityForResult(intent, CALCULAR_IMC_CODE);
        }else{
            Toast.makeText(this, "Entrada de dados invÃ¡lida.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CALCULAR_IMC_CODE){
            Log.d(TAG, "No ActivityResults - resultCode = " + resultCode);

            if(resultCode == RESULT_OK){
                Log.d(TAG, "Resultado OK");
                Bundle embrulho = data.getExtras();
                if(embrulho != null && embrulho.containsKey(KEY_PESSOA)){
                    mPessoa = (Pessoa) embrulho.getSerializable(KEY_PESSOA);
                    String msg = String.format("Seu peso ideal Ã© entre %.2f e %.2f quilos.", mPessoa.pesoMinimo(), mPessoa.pesoMaximo());

                    AlertDialog builder = new AlertDialog.Builder(this)
                            .setTitle("Peso ideal")
                            .setMessage(msg)
                            .setPositiveButton("OK", null)
                            .create();
                    builder.show();
                }
            }
        }
    }
}

/*
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityCalculadora extends AppCompatActivity implements View.OnClickListener {


    private EditText RESULTADO;
    private Button memoria;
    private Button DIVISAO;
  //  private TextView saidaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculadora);

        entradaEditText = findViewById(R.id.edittext_entrada);
        saidaTextView = findViewById(R.id.textview_saida);
        tempADICAOButton = findViewById(R.android.R.id.button_tempADICAOButton);
        tempSUBTRACAOButton = findViewById(R.android.R.id.button_tempSUBTRACAOButton);
        tempMULTIPLICACAOButton = findViewById(R.android.R.id.button_tempMULTIPLICACAOButton);
        tempDIVISAOButton = findViewById(R.android.R.id.button_tempDIVISAOButton);
        tempNULOButton = findViewById(R.android.R.id.button_tempNULOButton);
        tempMemoriaButton = findViewById(R.android.R.id.button_tempMemoriaButton);

        tempADICAOButton.setOnClickListener(this);
        tempSUBTRACAOButton.setOnClickListener(this);
        tempMULTIPLICACAOButton.setOnClickListener(this);
        tempDIVISAOButton.setOnClickListener(this);
        tempNULOButton.setOnClickListener(this);
        tempMemoriaButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v == tempADICAOButton){
            calcular();
        }

        if(v == tempSUBTRACAOButton){
            calcular();
        }
        if(v ==tempMULTIPLICACAOButton){
            calcular();
        }
        if(v ==  tempDIVISAOButton){
            calcular();
        }
        if(v == tempNULOButton){
            calcular();
        }
    }

    private double getEntrada() throws NumberFormatException{
        double entrada;
        try{
            entrada = Double.valueOf(entradaEditText.getText().toString());
        }catch (NumberFormatException ex){
            entrada = 0;
            throw ex;
        }
        return entrada;
    }

    private void converterFarh(){
        double valor;

        try{
            valor = getEntrada();
        }catch (NumberFormatException ex){
            Toast.makeText(this, "Entrada invÃ¡lida!", Toast.LENGTH_SHORT).show();
            valor = 0;
        }

        saidaTextView.setText(String.format("%.2fÂ°C", (1.8*valor)+32));
    }

    private void converterCelsius(){
        double valor;

        try{
            valor = getEntrada();
        }catch (NumberFormatException ex){
            Toast.makeText(this, "Entrada invÃ¡lida!", Toast.LENGTH_SHORT).show();
            valor = 0;
        }

        saidaTextView.setText(String.format("%.2fÂ°F",(valor - 32)/1.8));
    }
}
*/