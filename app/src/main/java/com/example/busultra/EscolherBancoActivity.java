package com.example.busultra;

import android.os.Bundle;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EscolherBancoActivity extends AppCompatActivity {

    GridLayout grLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher_banco);

        grLugares = findViewById(R.id.grLugares);

        int colunas = 5;
        int linhas = 10;
        int quantLugares = colunas * linhas;
        int luuagresOcupados = 10;

        Random random = new Random();
        Set<Integer> ocupados = new HashSet<>();

        //Gerando lugares ocupados aleatóriamente
        //Enquanto o tamanho é menor que o número de lugares ocupados
        while (ocupados.size() < luuagresOcupados){
            ocupados.add(random.nextInt(quantLugares));
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}