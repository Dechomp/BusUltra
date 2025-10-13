package com.example.busultra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    Button btLogar, btCancelarLogin;

    EditText edUser, edSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //Estou relacionando o minha variavel a um componenete na interface
        //Botões do meu código
        btLogar = findViewById(R.id.btLogar);
        btCancelarLogin = findViewById(R.id.btCancelarLogin);

        //Campos editaveis do meu código
        edUser = findViewById(R.id.edUser);
        edSenha = findViewById(R.id.edSenha);

        btCancelarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Fecha a tela e remove da memória ram do dispositivo
                finishAndRemoveTask();
            }
        });
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cria duas variaveis vazias
                String user = "", senha = "";

                //Recebo os texto dos campos
                user = edUser.getText().toString();
                senha = edSenha.getText().toString();

                //Comparo para ver se há algum componente vazio
                if(user.equals("") || senha.equals("")){
                    //Mostro a mensagem de campo vazio
                    Toast.makeText(LoginActivity.this, "Campos vázios, digite novamente!", Toast.LENGTH_SHORT).show();
                }
                //Verifico se ele acertou o nome do usuário
                else if(user.equals("Dechomp")){
                    //Se ele acertou o nome  a senha, levo para a  próxima tela
                    if (senha.equals("DcBusUltra")){
                        //Cria uma variavel de "transporte de telas"
                        Intent telaPrincipal;

                        //Passo para ele, qual tela estou e para aonde eu quero ir
                        telaPrincipal = new Intent(LoginActivity.this, MainActivity.class);

                        //Inicio a nova tela
                        startActivity(telaPrincipal);

                        //Mensagem de login
                        Toast.makeText(LoginActivity.this, "Bem-vindo" + user, Toast.LENGTH_SHORT).show();

                        //Fecho a tela de login
                        finish();

                    }
                    //Se eu não acertar a senha, mostra que a senha está incorreta
                    else{
                        Toast.makeText(LoginActivity.this, "Senha incorreta, digite novamente!", Toast.LENGTH_SHORT).show();
                    }
                }
                // Se eu errar o usuário, aviso para redigitar
                else{
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorreto, digite novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Verifica se eu tenho foco
        edUser.setOnFocusChangeListener((v, hasFocus) -> {
            //O hasFocus nestes caso é verdadeiro, mas eu só quero q funcione quando for falso
            //Para isto, eu tenho que inverter o valor
            //Ou seja, usar o not, ou em java a !
            if(!hasFocus){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        edSenha.setOnFocusChangeListener((v, hasFocus) -> {
            //O hasFocus nestes caso é verdadeiro, mas eu só quero q funcione quando for falso
            //Para isto, eu tenho que inverter o valor
            //Ou seja, usar o not, ou em java a !
            if(!hasFocus){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}