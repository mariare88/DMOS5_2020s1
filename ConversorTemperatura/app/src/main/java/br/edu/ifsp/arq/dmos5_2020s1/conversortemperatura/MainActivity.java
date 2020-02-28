package br.edu.ifsp.arq.dmos5_2020s1.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText temperaturaEditText;
    private Button paraCelciusButton;
    private Button paraFahrenheitButton;
    private TextView saidaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperaturaEditText = findViewById(R.id.edittext_temperatura);
        paraCelciusButton = findViewById(R.id.button_para_celcius);
        paraFahrenheitButton = findViewById(R.id.button_para_fahrenheit);
        saidaTextView = findViewById(R.id.textview_saida);

        paraCelciusButton.setOnClickListener(this);
        paraFahrenheitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        double entrada, saida=0;
        String saidaFormatada;
        saidaFormatada = "%.2f º";
        if(v == paraCelciusButton){
            entrada = recuperqaTemperatura();
            saida = paraCelsius(entrada);
            saidaFormatada += "C ";
        }
        if(v == paraFahrenheitButton){
            entrada = recuperqaTemperatura();
            saida = paraFahrenheit(entrada);
            saidaFormatada += "F ";
        }
        saidaTextView.setText(String.format(saidaFormatada, saida));
    }

    private void mostraMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private double paraCelsius(double temperatura){
        /*
        Fórmula disponível em https://www.infoescola.com/fisica/conversao-de-escalas-termometricas/

        C = (F - 32) / 1,8
         */
        double celsius;
        celsius = (temperatura - 32) / 1.8;
        return celsius;
    }

    private double paraFahrenheit(double temperatura){
         /*
        Fórmula disponível em https://www.infoescola.com/fisica/conversao-de-escalas-termometricas/

        F = 1,8 * C + 32
         */
        return 1.8 * temperatura + 32;
    }

    private double recuperqaTemperatura(){
        double temperatura;
        try{
            temperatura = Double.valueOf(temperaturaEditText.getText().toString());
        }catch (NumberFormatException nfe){
            temperatura = 0;
            mostraMensagem(getString(R.string.temperatura_invalida));
        }catch (Exception e){
            temperatura = 0;
            mostraMensagem(getString(R.string.erro_entrada));
        }
        return temperatura;
    }
}
