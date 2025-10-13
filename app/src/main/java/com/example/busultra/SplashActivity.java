package com.example.busultra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    WebView imgGif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        //Estou relacionando o minha variavel a um componenete na interface
        imgGif = findViewById(R.id.imgGif);

        imgGif.getSettings().setLoadWithOverviewMode(true);
        imgGif.getSettings().setUseWideViewPort(true);
        imgGif.setInitialScale(1);

        imgGif.loadUrl("file:///android_res/drawable/gamestudandologo.gif");


        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                exibirLoginActivy();

            }
        }, 6300);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void exibirLoginActivy(){
        Intent telaInicial;

        telaInicial = new Intent(SplashActivity.this, LoginActivity.class);


        startActivity(telaInicial);
        finish();
    }
}