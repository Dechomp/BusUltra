package com.example.busultra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagamentoActivity extends AppCompatActivity {

    TextView tvMetodoPagamento, tvNomePagamento, tvDocumentoPagamento, tvPrecoPagar, tvBancoPagar;

    Button btFinalizarPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagamento);

        tvMetodoPagamento = findViewById(R.id.tvMetodoPagamento);
        tvNomePagamento = findViewById(R.id.tvNomePagamento);
        tvDocumentoPagamento = findViewById(R.id.tvDocumentoPagamento);
        tvPrecoPagar = findViewById(R.id.tvPrecoPagar);
        tvBancoPagar = findViewById(R.id.tvBancoPagar);

        tvBancoPagar.setText(Global.numBancos[Global.passageiroPagar]);

        tvMetodoPagamento.setText(Global.metodoPagamento);
        tvPrecoPagar.setText(Global.preco + "");
        tvNomePagamento.setText(Global.nomePassageiros[Global.passageiroPagar]);
        tvDocumentoPagamento.setText(Global.documentos[Global.passageiroPagar]);

        btFinalizarPagamento = findViewById(R.id.btFinalizarPagamento);



        btFinalizarPagamento.setOnClickListener(v -> {
            if (Global.passageiroPagos == Global.numPassageiros - 1) {
                Intent telaPagamentoAprovado;

                telaPagamentoAprovado = new Intent(PagamentoActivity.this, PagamentoFeitoActivity.class);
                startActivity(telaPagamentoAprovado);
                finish();
            }
            else{
                Global.passageiroPagos++;
                finish();

            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}