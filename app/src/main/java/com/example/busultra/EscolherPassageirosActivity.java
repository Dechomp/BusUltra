package com.example.busultra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EscolherPassageirosActivity extends AppCompatActivity {
    LinearLayout lnContainerPassageiros;

    Button btEscolherPix, btEscolherCredito, btEscolherTransferencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher_passageiros);

        lnContainerPassageiros = findViewById(R.id.lnContainerPassageiros);

        btEscolherCredito = findViewById(R.id.btEscolherCredito);
        btEscolherPix = findViewById(R.id.btEscolherPix);
        btEscolherTransferencia = findViewById(R.id.btEscolherTransferencia);


        LayoutInflater inflater = LayoutInflater.from(this);

        int  i = 0;
        while (i < Global.numPassageiros){
            //Clona o layout
            ConstraintLayout blocoPassageiro = (ConstraintLayout) inflater.inflate(R.layout.activity_layout_escolher_passageiros, lnContainerPassageiros, false);

            //Definir os parâmetros para deixar bonito
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            //Deini realmente os parâmetros
            params.setMargins(0,0,0,16);

            blocoPassageiro.setLayoutParams(params);

            TextView tvNumeroBanco = blocoPassageiro.findViewById(R.id.tvNumeroBanco);
            TextView tvNomePassageiro = blocoPassageiro.findViewById(R.id.tvNomePassageiro);
            TextView tvDocumento = blocoPassageiro.findViewById(R.id.tvDocumento);
            View divider = blocoPassageiro.findViewById(R.id.dvPassageiros);
            Button btAlterarInformacao = blocoPassageiro.findViewById(R.id.btAlterarInformacao);


            tvNumeroBanco.setText(Global.numBancos[i]);
            tvNomePassageiro.setText(Global.nomePassageiros[i]);
            tvDocumento.setText(Global.documentos[i]);

            int numPassageiro = i;
            btAlterarInformacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Global.passageiroAlterar = numPassageiro;

                    Intent telaAlterarInformacoes;

                    telaAlterarInformacoes = new Intent(EscolherPassageirosActivity.this, AlterarInformacoesActivity.class);
                    startActivity(telaAlterarInformacoes);

                    finish();

                }
            });

            lnContainerPassageiros.addView(blocoPassageiro);


            i++;
        }
        btEscolherCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.metodoPagamento = "Crédito";
                pagarPassagens();
            }
        });

        btEscolherPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.metodoPagamento = "PIX";
                pagarPassagens();
            }
        });

        btEscolherTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.metodoPagamento = "Transferência";
                pagarPassagens();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void pagarPassagens(){
        int i;
        for(i = 0; i < Global.numPassageiros; i++){
            if(Global.nomePassageiros[i].contains("Fulano") || Global.documentos[i].contains("Documento")){
                Toast.makeText(EscolherPassageirosActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        if (i == Global.numPassageiros){
            for(i = 0; i < Global.numPassageiros; i++){
                Global.passageiroPagar = i;
                Intent telaPagamento;

                telaPagamento = new Intent(EscolherPassageirosActivity.this, PagamentoActivity.class);
                startActivity(telaPagamento);
            }


        }

    }
}