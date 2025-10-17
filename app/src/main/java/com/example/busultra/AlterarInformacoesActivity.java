package com.example.busultra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlterarInformacoesActivity extends AppCompatActivity {

    EditText edNome, edDocumento;

    TextView tvNumBancoAlterar;



    Button btAlterarInformacoesPasssageiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alterar_informacoes);


        edNome = findViewById(R.id.edNome);
        edDocumento = findViewById(R.id.edDocumento);
        tvNumBancoAlterar = findViewById(R.id.tvNumBancoAlterar);


        tvNumBancoAlterar.setText(Global.numBancos[Global.passageiroAlterar]);

        Button btAlterarInformacoesPasssageiro = findViewById(R.id.btAlterarInformacoesPasssageiro);

        btAlterarInformacoesPasssageiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, documento;


                nome = edNome.getText().toString();
                documento = edDocumento.getText().toString();

                if(nome.equals("") || documento.equals("")){
                    Toast.makeText(v.getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Global.nomePassageiros[Global.passageiroAlterar] = nome;
                    Global.documentos[Global.passageiroAlterar] = documento;

                    Intent telaAlterarInformacoesPassageiros;

                    telaAlterarInformacoesPassageiros = new Intent(AlterarInformacoesActivity.this, EscolherPassageirosActivity.class);
                    startActivity(telaAlterarInformacoesPassageiros);

                    finish();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}