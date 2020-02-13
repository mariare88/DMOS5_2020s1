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

    private EditText valorReaisEditText;
    private Button converterButton;
    private TextView valorDolarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorReaisEditText = findViewById(R.id.edittext_valor_reais);
        converterButton = findViewById(R.id.button_converter);
        valorDolarTextView = findViewById(R.id.textview_valor_dolar);

        converterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == converterButton){
            converterValor();
        }
    }

    private void converterValor(){

        double reais;
        String valorReaisString;

        valorReaisString = valorReaisEditText.getText().toString();

        try{
            reais = Double.valueOf(valorReaisString);
        }catch (NumberFormatException ex){
            Toast.makeText(this, "valor inválido digitado!", Toast.LENGTH_SHORT).show();
            reais = 0;
        }

        valorDolarTextView.setText(String.valueOf(reais/VALOR_DOLAR));
    }
}
