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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

public class EscolherPassagemActivity extends AppCompatActivity {

    LinearLayout container;


    //Cria as variáveis da classe
    int tempoChegada = 0;
    int tempoPartida = 0;
    int tempoTotal = Global.tempo;
    Button btVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher_passagem);

        //Container Principal
        container = findViewById(R.id.lnContainer);

        //Botão voltar
        btVoltar = findViewById(R.id.btVoltarMain);


        //Teste de criação de containers usando um inflater
        LayoutInflater inflater = LayoutInflater.from(this);
        int i = 0;

        //Funciona até chegar em 23:59
        while (tempoPartida < 60 * 25) {

            //Clona o layout
            ConstraintLayout blocoPassagem = (ConstraintLayout) inflater.inflate(R.layout.activity_layout_containers, container, false);

            //Definir os parâmetros para deixar bonito
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            //Deini realmente os parâmetros
            params.setMargins(0,0,0,16);
            blocoPassagem.setLayoutParams(params);


            //Associa os componentes a partir da tela de layout de containers
            TextView tvOrigem = blocoPassagem.findViewById(R.id.tvOrigem);
            TextView tvDestino = blocoPassagem.findViewById(R.id.tvDestino);
            TextView tvPreco = blocoPassagem.findViewById(R.id.tvPreco);
            TextView tvHorarioPartida = blocoPassagem.findViewById(R.id.tvHorarioPartida);
            TextView tvHorarioChegada = blocoPassagem.findViewById(R.id.tvHorarioChegada);
            Button btMaisInfo = blocoPassagem.findViewById(R.id.btMaisInfo);
            Button btEscolherPassagem = blocoPassagem.findViewById(R.id.btEscolherPassagem);
            View diDivisor = blocoPassagem.findViewById(R.id.diDivisor);

            //Calcula o tempo a partir do i
            /*
            * Exemplo: se demorar 15 minutos o tempo de distancia, ele vai funcionar assim:
            * Inicia as meia noite tempo(15) * i(0) = 0
            * Depois
            * tempo(15) * i (1) = 15
            * Depois
            * tempo(15) * i (2) = 30
            * Depois
            * tempo(15) * i (3) = 45
            * Depois
            * tempo(15) * i (4) = 60
            * tempo(60) % 60 = 0 minutos
            * (tempo(60) - minutos(0)) /60 = 1 hora
            * Depois
            * tempo(15) * i (5) = 75
            * tempo(75) % 60 = 15 minutos
            * (tempo(75) - minutos(15)) /60 = 1 hora
            * E assim por diante
            * Ou seja, vai funcionar assim até chegar no fim do dia
            * E também, significa que, teoricamente, só sai 1 onibus assim que o outro
            * Chega no destino
            */
            //Define o tempo inicial
            tempoPartida = (tempoTotal * i);

            //Define os minutos
            int minutos = tempoPartida % 60;

            //Inicia as horas
            int horas = 0;

            //Quando o tempo de partida passa de 1 hora
            if (tempoPartida >= 60) {
                //Calcula a quantidade de horas
                horas = (tempoPartida - minutos) / 60;
            }
            //Inicia os textos de horas e minutos
            String horasTexto = "", minutosTexto = "";

            //Define os textos
            horasTexto = "" + horas;

            //Caso o texto comece com zero, ou seja, menor que 10
            if(horas < 10){
                //Confiiura com um 00 na frente
                horasTexto = "0" + horas;
            }
            else if (horas >= 24){
                //Há a possibilidade de chegar no outro dia
                break;
            }

            //Define a quantidade de minutos
            minutosTexto = "" + minutos;

            //Mesma coisa das horas, caso não chegue a 10 minutos
            if(minutos < 10){
                //Coloca o zero na frente
                minutosTexto = "0" + minutos;
            }

            //Configura o texto para o usuário
            tvHorarioPartida.setText(horasTexto + ":" + minutosTexto);

            //Agora configura o tempo de chegada

            //Para fins de configuração, aumento o valor aqui
            i ++;

            //Calcula o tempo de chegada
            tempoChegada = tempoTotal * i;

            //Configuraão que já comentei
            minutos = tempoChegada % 60;
            horas = (tempoChegada - minutos) / 60;

            horasTexto = "" + horas;
            if(horas < 10){
                horasTexto = "0" + horas;
            }
            else if (horas >= 24) {
                //Aqui começa algumas diferenças
                //Começo calculando a quantidade de horas que passam de meia noite
                horas %= 24;

                //Configura o texto final
                if(horas < 10){
                    horasTexto = "No outro dia às 0" + horas;
                    tvHorarioChegada.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                }
                //Ainda não aconteceu, mas caso chegue depois das 10 do outro dia
                else{
                    horasTexto = "No outro dia às" + horas;
                    tvHorarioChegada.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                }
            }

            //Mesma configuração do textos
            minutosTexto = "" + minutos;

            if(minutos < 10){
                minutosTexto = "0" + minutos;
            }

            //Define o texto para o usuário
            tvHorarioChegada.setText(horasTexto + ":" + minutosTexto);

            //Configura os textos de origem, destino e preço destino
            tvOrigem.setText(Global.origem);
            tvDestino.setText(Global.destino);
            tvPreco.setText("R$ " + Global.preco);

            //Ignorar
            /*int minutos = (tempoTotal * (i + 1)) % 60 ;

            int horas = 0;

            if (Global.tempo * (i + 1) >= 60){
               horas = (Global.tempo - minutos) / 60;
            }



            String horaTexto = "", minutoTexto = "";
            if (horas < 10){
                horaTexto = "0" + horas;
            }
            else{
                horaTexto = "" + horas;
            }
            if (minutos < 10){
                minutoTexto = "0" + minutos;
            }
            else{
                minutoTexto = "" + minutos;

            }
            */
            //tvHorarioPartida.setText(horaTexto + ":" + minutoTexto);





            //Apenas para mostar qual passagem escolheu
            int numBotao = i;

            //Salva os textos da passagem escolhida
            String horaSaida, horaChegada;

            //Salva os textos
            horaSaida = tvHorarioPartida.getText().toString();
            horaChegada = tvHorarioChegada.getText().toString();

            //QUando clicar no botão escolher passagem
            btEscolherPassagem.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      try{
                          //Salva os textos globalmente
                          Global.horarioSaida = horaSaida;
                          Global.horarioChegada = horaChegada;

                          //Manda para outra tela
                          Intent telaEscolherBanco;
                          telaEscolherBanco = new Intent(EscolherPassagemActivity.this, EscolherBancoActivity.class);
                          startActivity(telaEscolherBanco);

                          Toast.makeText(v.getContext(), "Passagem número " + numBotao + 1 + " escolhida",Toast.LENGTH_SHORT).show();
                      }
                      //Caso de erro
                      catch (Exception e){
                          Toast.makeText(v.getContext(), "Erro ao escolher a passagem" + e.getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }
            });

            //Caso aperte o botão mais info, configurar a tela do mais info
            btMaisInfo.setOnClickListener(v -> {
                Toast.makeText(EscolherPassagemActivity.this, "Mais Informações", Toast.LENGTH_SHORT).show();
            });

            //Adiciona o bloco na tela dentro do scroll
            container.addView(blocoPassagem);

        }

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Volta para a outra tela
                finish();
            }
        });

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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}