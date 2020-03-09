package br.edu.ifsp.arq.dmos5_2020s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText entradaEditText;
    private Button tempCelsiusButton;
    private TextView saidaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entradaEditText = findViewById(R.id.edittext_entrada);
        saidaTextView = findViewById(R.id.textview_saida);
        tempCelsiusButton = findViewById(R.id.button_tempCelsiusButton);


        tempCelsiusButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == tempCelsiusButton){
            converterCelsius();
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


    private void converterCelsius(){
        double valor;

        try{
            valor = getEntrada();
        }catch (NumberFormatException ex){
            Toast.makeText(this, "Entrada inválida!", Toast.LENGTH_SHORT).show();
            valor = 0;
        }

        saidaTextView.setText(String.format("%.2f°C",(valor - 32)/1.8));
    }
}
