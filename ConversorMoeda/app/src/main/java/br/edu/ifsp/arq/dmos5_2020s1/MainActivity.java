package br.edu.ifsp.arq.dmos5_2020s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final double VALOR_DOLAR = 4.5;

    private EditText entradaEditText;
    private Button dolar2realButton;
    private Button real2dolarButton;
    private TextView saidaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entradaEditText = findViewById(R.id.edittext_entrada);
        saidaTextView = findViewById(R.id.textview_saida);
        dolar2realButton = findViewById(R.id.button_dolar2real);
        real2dolarButton = findViewById(R.id.button_real2dolar);

        dolar2realButton.setOnClickListener(this);
        real2dolarButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == dolar2realButton){
            paraReal();
        }

        if(v == real2dolarButton){
            paraDolar();
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

    private void paraDolar(){
        double valor;

        try{
            valor = getEntrada();
        }catch (NumberFormatException ex){
            Toast.makeText(this, "Entrada inválida!", Toast.LENGTH_SHORT).show();
            valor = 0;
        }

        saidaTextView.setText(String.format("USD: %.2f", valor/VALOR_DOLAR));
    }

    private void paraReal(){
        double valor;

        try{
            valor = getEntrada();
        }catch (NumberFormatException ex){
            Toast.makeText(this, "Entrada inválida!", Toast.LENGTH_SHORT).show();
            valor = 0;
        }

        saidaTextView.setText(String.format("R$: %.2f", valor*VALOR_DOLAR));
    }
}
