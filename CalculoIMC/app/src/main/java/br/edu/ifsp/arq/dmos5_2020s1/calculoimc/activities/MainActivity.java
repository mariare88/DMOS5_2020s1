package br.edu.ifsp.arq.dmos5_2020s1.calculoimc.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.R;
import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.model.Pessoa;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
            pessoa = new Pessoa(peso, altura);
            Toast.makeText(this, String.format("IMC : %.2f", pessoa.imc()), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Entrada de dados inválida.", Toast.LENGTH_SHORT).show();
        }
    }
}
