package br.edu.ifsp.arq.dmos5_2020s1.ciclodevida;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MeuApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Executando o onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Executando o onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Executando o onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Executando o onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Executando o onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Executando o onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Executando o onDestroy()");
    }
}
