package br.edu.ifsp.arq.dmos5_2020s1.calculoimc.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.R;
import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.model.Pessoa;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String KEY_PESO = "peso";
    public static final String KEY_ALTURA = "altura";

    private EditText pesoEditText;
    private EditText alturaEditText;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperando as referências dos elementos de layout
        pesoEditText = findViewById(R.id.edittext_peso);
        alturaEditText = findViewById(R.id.edittext_altura);
        calcularButton = findViewById(R.id.button_calcular_imc);

        //Definindo o OnClickListern do botão
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

            //Cria um Bundle (embrulho) com os argumentos necessários na outra activity
            Bundle args = new Bundle();
            //Os dados são inseridos no bundle como um objeto do tipo Map (chave, valor)
            args.putDouble(KEY_PESO, peso);
            args.putDouble(KEY_ALTURA, altura);

            //Cria a intenção de abrir uma nova tela
            Intent intent = new Intent(this, DetalhesImcActivity.class);
            //Inclui os argumentos na intenção
            intent.putExtras(args);
            //Solicita a abertura da nova activity
            startActivity(intent);
        }else{
            Toast.makeText(this, "Entrada de dados inválida.", Toast.LENGTH_SHORT).show();
        }
    }
}
