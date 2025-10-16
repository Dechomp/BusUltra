package com.example.busultra;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import androidx.gridlayout.widget.GridLayout;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class EscolherBancoActivity extends AppCompatActivity {

    //Definição das variáveis e componentes
    GridLayout grLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher_banco);

        //Relaciona ao lugar certo
        grLugares = findViewById(R.id.grLugares);

        //Pego o tamanho das  colunas do GridLayout, pois assim, casoo eu queira aumentar, já está pré configurado
        int colunas = grLugares.getColumnCount();

        //Quantidade de fileiras que eu quiser
        int fileiras = 10;

        //Tamanho das fileiras(Caso aumente depois o tamanho do onibus)
        int tamCorredor = 1;

        //Calcula a quantidade de lugares, tirando o corredor
        int quantLugares = (colunas - tamCorredor) * fileiras;

        //Quantidade de assentos ocupados
        int assentosOcupados = 10;

        //Traz uma classe para aleatorizar
        Random random = new Random();

        //Inicializa os lugares ocupados
        Set<Integer> ocupados = new HashSet<>();

        //Gerando assentos ocupados aleatóriamente
        //Enquanto o tamanho é menor que o número de lugares ocupados
        while (ocupados.size() < assentosOcupados){
            //Aleatoriza a posição dos assentos ocupados
            ocupados.add(random.nextInt(quantLugares) + 1);
        }

        /*
        *Aqui uma amos  usar uma classe chamada AtomicInteger
        * Iremos usala, pois ficará mais fácil e legivel de ler as mudanças no valor da quantidade de assentos escolhidos
        * Por que mais fácil? Como teremos quue somar ou diminuir os valores, não podemos usar variáveis normias
        * Pois como temos vários componentes, podendo não achar os valores corretos um outro método, no caso seria criar uma classe
        * Por isto também é mais fácil de ler o código, para não ter que fiar fazendo isto, então faremos usando esta classe já feita
        * Sem contar que aparte de adicionar e reitrar os assentos também já fica  pronta
        */
        AtomicInteger assentosEscolhidos = new AtomicInteger(0);

        int numeroAssento = 1;

        //Laço de repetição para criar os lugares
        //Primeiro as fileiras
        for (int i = 0; i < fileiras; i++){
            //Agora as colunas
            for (int j = 0; j < colunas; j++){
                //Cria o lugar
                //Caso seja 2, quer dizer que é o corredor
                if (j == 2){
                    //Cria uma view para ocupar o espaço como se fosse uma corredor
                    View corredor = new View(this);

                    //Pega as configurações do GridLayout
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();

                    //Configura o tamanho do corredor
                    params.width = 40;
                    params.height = 40;

                    //Seta as coonfigurações do corredor
                    corredor.setLayoutParams(params);

                    //Adiciona o corredor na tela
                    grLugares.addView(corredor);

                    //Agora, como eu uero que o laço continua, eu não posso usar o break, pois ele sai do laço
                    //por isso vamos usar o continue, pois ele sai apenas da compilação atual, mas contiinuaa o laço
                    continue;
                }


                //Extra, para  deixar o texto embaixo, vou usar um LinearLayout
                LinearLayout bloco = new LinearLayout(this);
                bloco.setOrientation(LinearLayout.VERTICAL);
                bloco.setGravity(Gravity.CENTER);
                bloco.setPadding(12, 12, 12, 12);


                //Criando o assento
                CheckBox assento = new CheckBox(this);

                //Tirando o texto para colocar com o textView
                assento.setText(null);

                //Definne os parametross do assento
                assento.setLayoutParams(new LinearLayout.LayoutParams(
                        120,
                        120
                ));

                //Texto de indicação
                TextView textoAssento = new TextView(this);

                //Cetraliza o texto
                textoAssento.setGravity(Gravity.CENTER);





                //Pega as configurações do GridLayout
                //Configuração do texto caso seja o corredor ou não
                /*
                Vamos lá, aqui há lógica pode ser complexa, mas vou explicar
                A lógica de numeração dos ônibus é a seguinte:
                Os bancos na janela são números impáres e os do corredoor, por isto sua vez, pares
                Então ficariam mais ou menos assim
                1 2   4  3
                5 6   8  7
                9 10  12 11
                O nosso problema, é que eles sãoo criados em sequencia
                Então se quisessemos criar sem os parametros/configurações, os bancos ficariam assim
                1 2  3  4
                5 6  7  8
                9 10 11 12

                Então, como eu fiz para identificar os bancos?
                Bom, eu sei que os dos corredores sempre vão ser divisiveis por 2
                A diferença é que os do lado esquerdo, não são divisiveis por 4
                Então, caso o número seja divisivel por dois e não seja divisivel por 4
                SSó mantenho o número
                Neste caso, os bancos do coreredor esquerdo estão prontos
                Afinal, os números 2, 6, 10 caem nesta condição ficando com o corredor pronto
                 2
                 6
                 10
                Pro primeiro da janela, também vimos que podemos manter com o mesmo número
                Mas nem todos os impares teremos um divisor rcomum (além do 1)
                Mas especificamente estes perto da janela, se você adiconar 3, todos serão divisiveis por 4
                Difeente do asssentos do lados direito, ou seja, 1 + 3 == 4 e 4 % 4 == 0, e isto ocorre para os números 5, 9, 13
                E assim por diante, e como falei, não aocontece com o outro lado, pois 4 + 3 = 7 e 7 % 4 != 0
                E com os números impates do outro lado ficam 3 + 3 == 6 mas 6 % 4 != 0
                Então, os pumeros que se mantém no corrdor seguem esta lógica, ficando
                1 2
                5 6
                9 10
                Já refrente ao outro lado, fica mais simples
                Como já sabemos que os núemeros que sobraram só sobram dois resultados possiveis
                Ou são divisiveis por 2 e por 4, sobrando os núemros 4, 8, 12 ou somados com 3 não
                são divisiveis por 4
                Aqui preferi calcular pelos divisiveis por 4
                Segindo a tabelinha de números na sequencia, sabemos que todos os divisiveis por 4, acabaaam ficando na janela
                Enão é simples, apenas subritaimos 1, e pronto, outra coluna da janela pronta ficando
                1 2   3
                5 6   7
                9 10  11
                E por sua vez, todos os que sobraram são aqueles que não são divisíveis por 4
                então basta apenas somar 1 para funcionar
                Então, na sequencia, os bancos ficariam assim
                1 2  3  4
                5 6  7  8
                9 10 11 12


                E corrigidos eles seguem a lógica normalmente
                1 2   4  3
                5 6   8  7
                9 10  12 11

                Não sei se já fizeram isto antes, mas afirmo que não me basiei em uma formula já feita
                */
                if((numeroAssento % 2 == 0 && numeroAssento %4 != 0) || ((numeroAssento +  3) % 4 == 0)){
                    //assento.setText(j + "-" + numeroAssento);

                    //Substituindo pelo TexView
                    textoAssento.setText(j + "-" + numeroAssento);
                }
                else if (numeroAssento % 4 == 0) {
                    //assento.setText(j + "-" + (numeroAssento - 1));
                    textoAssento.setText(j + "-" + (numeroAssento - 1));
                }
                else{
                    //assento.setText(j + "-" + (numeroAssento + 1));
                    textoAssento.setText(j + "-" + (numeroAssento + 1));

                }

                //Tamanho do texto
                textoAssento.setTextSize(16f);





                /*
                //Coloca o centro para baixo
                assento.setGravity(Gravity.CENTER);



                //Espaçamento entre os assentos
                assento.setPadding(10,10,10,10);

                //Tamanho do texto
                assento.setTextSize(16f);

                */

                //Retira a imagem normal do checkbox
                assento.setButtonDrawable(null);

                //Troca o icone do banco por um desenhado
                assento.setBackgroundResource(R.drawable.poltrona_seletor);

                //Verifica se é um banco ocupado
                if (ocupados.contains(numeroAssento)){
                    //Caso seja, coloca a cor vermelha
                    assento.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#F44336")));
                    assento.setEnabled(false);
                }
                else{
                    //Caso não seja

                    //Checa quando for selecionado
                    String idetificador = textoAssento.getText().toString();
                    assento.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Verifica se está selecionado
                            if (assento.isChecked()){
                                //Adiciona a a lista de selecionados
                                assentosEscolhidos.incrementAndGet();

                                //Deixa a cor azul de selecionado
                                assento.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#2196F3")));

                                //Selecionna a caixa
                                assento.setChecked(true);

                                //Mostra que foi selecionado
                                Toast.makeText(EscolherBancoActivity.this, "Assento " + idetificador + " selecionado", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //Caso não esteja selecionado

                                //Retira da lista
                                assentosEscolhidos.decrementAndGet();

                                //Volta para a cor padrão
                                assento.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                            }
                            //Mostra quantos bancos foram selecionados no total
                            Toast.makeText(EscolherBancoActivity.this, "Quantidade de assentos escolhidos: " + assentosEscolhidos, Toast.LENGTH_SHORT).show();
                        }

                    });


                }

                //Adicionando o texto e a view no bloco
                bloco.addView(assento);
                bloco.addView(textoAssento);
                //Adiciona a grade na tela
                grLugares.addView(bloco);

                //Incrementa o número do assento
                numeroAssento++;

            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}