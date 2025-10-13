package com.example.busultra;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    Button btEscolherData, btBuscarPassagem;

    EditText edDataViagem;

    Spinner spOrigem, spDestino;

    String origem, destino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Botões
        btEscolherData = findViewById(R.id.btEscolherData);
        btBuscarPassagem = findViewById(R.id.btBuscar);

        //Textos editáveis
        edDataViagem = findViewById(R.id.edDataViagem);

        //Spinners
        spOrigem = findViewById(R.id.spOrigem);
        spDestino = findViewById(R.id.spDestino);

        //Lista de origens
        ArrayList<String> origens = new ArrayList<>();
        origens.add("Selecione...");
        origens.add("Santana de Parnaíba");
        origens.add("Barueri");
        origens.add("São Paulo");
        origens.add("Campos do Jordão");
        origens.add("Rio de Janeiro");

        //Lista de destinos
        ArrayList<String> destinos = new ArrayList<>();
        destinos.add("Selecione...");
        destinos.add("Santana de Parnaíba");
        destinos.add("Campos do Jordão");
        destinos.add("Rio de Janeiro");
        destinos.add("Barueri");
        destinos.add("São Paulo");

        //Quando clicar no botão btEscolherData
        btEscolherData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Crio uma variável do tipo calendário
               Calendar calendario = Calendar.getInstance();

               //Crio uma variável para cada informação de data
               int dia, mes, ano;

               //Cada uma recebe o tipo da informação
               dia = calendario.get(Calendar.DAY_OF_MONTH);
               mes = calendario.get(Calendar.MONTH);
               ano = calendario.get(Calendar.YEAR);

               //Cria o calendário flutuante
               DatePickerDialog calendarioFlutuante = new DatePickerDialog(
                       //Nesta tela
                       MainActivity.this,
                       //Os parâmetros que irá usar
                       (view, year, month, dayOfMonth) -> {
                           Calendar dataSelecionada = Calendar.getInstance();
                           dataSelecionada.set(year, month, dayOfMonth, 0, 0, 0);


                           //Caso ele escolha a data de hoje
                           if(dataSelecionada.before(calendario)){
                               Toast.makeText(MainActivity.this, "Escolha uma data posterior a hoje", Toast.LENGTH_SHORT).show();
                               edDataViagem.setText("");
                           }
                           else{
                               //Assim que der OK, vai criar o texto da data
                               String dataEsc = dayOfMonth + "/" + (month + 1) + "/" + year;

                               //Mostra a mensagem da data escolhida
                               Toast.makeText(MainActivity.this, "Data escolhida: " + dataEsc, Toast.LENGTH_SHORT).show();

                               //Troca o texto do edDataViagem com fim de armazenamento
                               edDataViagem.setText(dataEsc);

                           }

                       },
                       //Referente a ordem dos parâmetros
                       ano, mes, dia
               );

               //Impede que o usuário selecione datas passadas
               calendarioFlutuante.getDatePicker().setMinDate(calendario.getTimeInMillis());

               //Mostra o calendário na tela
               calendarioFlutuante.show();
           }
        });

        /*
        Para adicionar a lista ao spinner, teremos que criar um adaptador
        Como temos duas listas, iremos criar uma função para isto
        ...
        Agora é só chamar a função
        */
        try {
            spOrigem.setAdapter(adapatarLista(origens));
            spDestino.setAdapter(adapatarLista(destinos));
        }
        catch (Exception ex){
            Toast.makeText(MainActivity.this, "Erro: " + ex, Toast.LENGTH_SHORT).show();
        }

        //Agora configurar os cliques
        spOrigem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //Assim que um item for selecionado e não tiver escrito "Selecione", mostra o destino escolhido
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                origem = parent.getItemAtPosition(position).toString();
                if (! origem.contains("Selecione")){
                    Toast.makeText(MainActivity.this, "Origem selecionada: " + origem, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nada por enquanto
            }
        });

        spDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destino = parent.getItemAtPosition(position).toString();
                if(!destino.contains("Selecione")){
                    Toast.makeText(MainActivity.this, "Destino selecionado: " + destino, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nada por enquanto
            }
        });

        //Quando colocar para buscar passagem
        btBuscarPassagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.tempo = 0;
                Global.preco = 0;

                //Primeiro vai verificar campos vazios ou não escolhidos
                if(edDataViagem.equals("") || origem.contains("Selecione") || destino.contains("Selecione")){
                    Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }

                //Impede de escolher o destino igual a origem
                else if(origem == destino){
                    Toast.makeText(MainActivity.this, "Escolha a destino diferente da origem", Toast.LENGTH_SHORT).show();
                }

                //Mandar as informações para a próxima tela
                else{

                    Global.origem = origem;
                    Global.destino = destino;

                    if (origem == "Santana de Parnaíba"){
                        Global.preco = 20;
                        Global.tempo = 0;
                    }
                    else if (origem == "Barueri"){
                        Global.preco = 15;
                        Global.tempo = 15;
                    }
                    else if (origem == "São Paulo"){
                        Global.preco = 10;
                        Global.tempo = 80;
                    }
                    else if (origem == "Campos do Jordão") {
                        Global.preco = 25;
                        Global.tempo = 210;
                    }
                    else if (origem == "Rio de Janeiro") {
                        Global.preco = 30;
                        Global.tempo = 500;
                    }

                    Toast.makeText(MainActivity.this, "Tempo: " + Global.tempo, Toast.LENGTH_SHORT).show();
                    if (destino == "Santana de Parnaíba"){
                        Global.preco += 20;
                        Global.tempo -= 0;
                    }
                    else if (destino == "Barueri"){
                        Global.preco += 15;
                        Global.tempo -= 15;
                    }
                    else if (destino == "São Paulo"){
                        Global.preco += 10;
                        Global.tempo -= 80;
                    }
                    else if (destino == "Campos do Jordão") {
                        Global.preco += 25;
                        Global.tempo -= 210;
                    }
                    else if (destino == "Rio de Janeiro") {
                        Global.preco += 30;
                        Global.tempo -= 500;
                    }

                    if (Global.tempo < 0){
                        Global.tempo *= -1;
                    }

                    Global.preco *= 2;

                    Toast.makeText(MainActivity.this, "Tempo: " + Global.tempo, Toast.LENGTH_SHORT).show();

                    if(Global.tempo <= 20){
                        Global.preco -= 60;
                    }
                    else if (Global.tempo <= 80) {
                        Global.preco -= 40;
                    }


                    //Mandar para a próxima tela
                    Intent telaEscolherPassagem;
                    telaEscolherPassagem = new Intent(MainActivity.this, EscolherPassagemActivity.class);
                    //Função que manda para próxima tela
                    startActivity(telaEscolherPassagem);

                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Função para adaptar uma lista
    private ArrayAdapter adapatarLista(ArrayList lista){

        //Cria um array adaptado
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                //Tela atual
                MainActivity.this,
                //Formato
                android.R.layout.simple_spinner_item,
                //Array a ser adaptada
                lista
        );
        //Formata como vai aparecer
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adaptador;

    }


}