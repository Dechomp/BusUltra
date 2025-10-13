package com.example.busultra;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

public class EscolherPassagemActivity extends AppCompatActivity {

    LinearLayout container;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher_passagem);

        //Container Principal
        container = findViewById(R.id.lnContainer);


        //Teste de criação de containers usando um inflater
        LayoutInflater inflater = LayoutInflater.from(this);
        for(int i = 0; i < 10; i++) {

            //Clona o layout
            ConstraintLayout blocoPassagem = (ConstraintLayout) inflater.inflate(R.layout.activity_layout_containers, container, false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            blocoPassagem.setLayoutParams(params);

            TextView tvOrigem = blocoPassagem.findViewById(R.id.tvOrigem);
            TextView tvDestino = blocoPassagem.findViewById(R.id.tvDestino);
            TextView tvPreco = blocoPassagem.findViewById(R.id.tvPreco);
            TextView tvHorarioPartida = blocoPassagem.findViewById(R.id.tvHorarioPartida);
            TextView tvHorarioChegada = blocoPassagem.findViewById(R.id.tvHorarioChegada);
            Button btMaisInfo = blocoPassagem.findViewById(R.id.btMaisInfo);
            Button btEscolherPassagem = blocoPassagem.findViewById(R.id.btEscolherPassagem);
            View diDivisor = blocoPassagem.findViewById(R.id.diDivisor);


            tvOrigem.setText(Global.origem);
            tvDestino.setText(Global.destino);
            tvPreco.setText("R$ " + Global.preco);
            tvHorarioPartida.setText("Teste " + Global.distancia);



            int numBotao = i;
            btEscolherPassagem.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      try{

                       Toast.makeText(v.getContext(), "Passagem número " + numBotao + " escolhida",Toast.LENGTH_SHORT).show();
                      }
                      catch (Exception e){
                          Toast.makeText(v.getContext(), "Erro ao escolher a passagem" + e.getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }
            });

            btMaisInfo.setOnClickListener(v -> {
                Toast.makeText(EscolherPassagemActivity.this, "Mais Informações", Toast.LENGTH_SHORT).show();
            });


            container.addView(blocoPassagem);

        }
        /*

        Jeito travado

        //Laço de repetição para criar várias views
        for(int i = 0; i < 10; i++){
            //Cria o conteiner
            LinearLayout blocoPassagem = new LinearLayout(this);

            //Criando os botões
            Button btEscolherPassagem = new Button(this), btMaisInfo = new Button(this);;

            //Criando as Views
            TextView tvHorarioPartida = new TextView(this), tvHorarioChegada = new TextView(this);

            TextView tvOrigem = new TextView(this), tvDestino = new TextView(this);

            TextView tvPreco = new TextView(this);

            //Vamos configurar as informações do blocosPassagem
            //Primeira coisa, adiconar a orientação
            blocoPassagem.setOrientation(LinearLayout.VERTICAL);

            //Configurar o espaçamento
            blocoPassagem.setPadding(16,16,16,16);

            //Configurar as cores
            blocoPassagem.setBackgroundColor(Color.parseColor("#cce6ff"));


            //Configurar o espaçamento entre os blocos
            LinearLayout.LayoutParams parametro = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            //Arruma os espaçamentos entre os containers
            parametro.setMargins(0,0,0,16);

            //Colocar o parametro no bloco
            blocoPassagem.setLayoutParams(parametro);

            //Textos dos TextsViews
            tvOrigem.setText("Teste Origem"); //Usar a variável de texto do origem
            tvDestino.setText("Teste Destino"); //Usar a variável de texto do destino


            /*
            * Criar uma variável global chamada destino
            * Quero criar um valor para cada cidade
            * Exemplo Santana == 10 e Barueri == 15
            * Aí faz um calculo santana + barueri * 1.2
            * Aí temos o preço da passagem
            * Assim, consigo fazer a conta idependente do destino ou origem escolhidos
            /
            tvPreco.setText("R$ teste"); // Usar a variável de texto do preço
            tvHorarioPartida.setText("Teste Partidas"); // variável do tempo
            tvHorarioChegada.setText("Teste Chegada"); // variável do tempo provavelmente
            //iremos usar outra conta para calcula o tempo de distancia

            //Agora é só adicionar botões
            btMaisInfo.setText("+ INFO");
            btMaisInfo.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            btMaisInfo.setTextColor(Color.parseColor("#FFFFFF"));

            btEscolherPassagem.setText("Escolher passagem");

            //Configurar o tamanho dos componentes
            //Tamanho dos botões
            btMaisInfo.setTextSize(18);
            btEscolherPassagem.setTextSize(18);

            //Tamanho dos TextViews
            tvDestino.setTextSize(18);
            tvHorarioPartida.setTextSize(18);
            tvPreco.setTextSize(18);
            tvOrigem.setTextSize(18);
            tvHorarioChegada.setTextSize(18);

            //Cor do texto
            tvDestino.setTextColor(Color.parseColor("#000000"));
            tvHorarioPartida.setTextColor(Color.parseColor("#000000"));
            tvPreco.setTextColor(Color.parseColor("#000000"));
            tvOrigem.setTextColor(Color.parseColor("#000000"));
            tvHorarioChegada.setTextColor(Color.parseColor("#000000"));



            //Adicionar tudo ao blocoPassagem
            blocoPassagem.addView(tvDestino);
            blocoPassagem.addView(tvHorarioPartida);
            blocoPassagem.addView(tvPreco);
            blocoPassagem.addView(tvOrigem);
            blocoPassagem.addView(tvHorarioChegada);
            blocoPassagem.addView(btMaisInfo);
            blocoPassagem.addView(btEscolherPassagem);




           // lnScroll.addView(blocoPassagem);
            lnScroll.addView(blocoPassagem);
        }
        */

        //Programação dos botões
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}