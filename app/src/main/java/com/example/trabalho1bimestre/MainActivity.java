package com.example.trabalho1bimestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edNome, edEmail, edIdade, edDisciplina, edNota1, edNota2;
    private TextView tvResultado;
    private Button btEnviar, btLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.edNome);
        edEmail = findViewById(R.id.edEmail);
        edIdade = findViewById(R.id.edIdade);
        edDisciplina = findViewById(R.id.edDisciplina);
        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        tvResultado = findViewById(R.id.tvResultado);
        btEnviar = findViewById(R.id.btEnviar);
        btLimpar = findViewById(R.id.btLimpar);

        // aqui é botão Enviar
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDados();
            }
        });

        // aqui é botão Limpar
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparFormulario();
            }
        });
    }

    private void enviarDados() {
        String nome = edNome.getText().toString();
        String email = edEmail.getText().toString();
        String idadeStr = edIdade.getText().toString();
        String disciplina = edDisciplina.getText().toString();
        String nota1Str = edNota1.getText().toString();
        String nota2Str = edNota2.getText().toString();

        // Validações
        if (nome.isEmpty() || email.isEmpty() || idadeStr.isEmpty() || disciplina.isEmpty() || nota1Str.isEmpty() || nota2Str.isEmpty()) {
            Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@")) {
            Toast.makeText(this, "Email inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        int idade;
        float nota1, nota2;
        try {
            idade = Integer.parseInt(idadeStr);
            nota1 = Float.parseFloat(nota1Str);
            nota2 = Float.parseFloat(nota2Str);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Idade ou notas inválidas!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (idade <= 0) {
            Toast.makeText(this, "A idade deve ser positiva!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
            Toast.makeText(this, "As notas devem ser entre 0 e 10!", Toast.LENGTH_SHORT).show();
            return;
        }

        // aqui calcula a média
        float media = (nota1 + nota2) / 2;
        String status = media >= 6 ? "Aprovado" : "Reprovado";

        // aqui ta exibindo os dados
        String resultado = "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Idade: " + idade + "\n" +
                "Disciplina: " + disciplina + "\n" +
                "Notas:  nota 1 -\"" + nota1 + ", nota 2 - " + nota2 + "\n" +
                "Média: " + media + "\n" +
                "Status: " + status;
        tvResultado.setText(resultado);
    }

    private void limparFormulario() {
        // esse faz a função da limpeza de todos os campos
        edNome.setText("");
        edEmail.setText("");
        edIdade.setText("");
        edDisciplina.setText("");
        edNota1.setText("");
        edNota2.setText("");
        tvResultado.setText("");
    }
}
