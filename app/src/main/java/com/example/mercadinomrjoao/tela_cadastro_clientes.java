package com.example.mercadinomrjoao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class tela_cadastro_clientes extends AppCompatActivity {
    EditText nome, numero, cpf, cep, dataVencimento, email, logadouro, cidade, estado, telefone, complemento, bairro;
    Button menu, cadastra;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro_clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nome = findViewById(R.id.edtNome);
        numero = findViewById(R.id.edtNumeroRua);
        cpf = findViewById(R.id.edtCPF);
        cep = findViewById(R.id.edtCep);
        dataVencimento = findViewById(R.id.edtDiaVencimento);
        email = findViewById(R.id.edtEmail);
        logadouro = findViewById(R.id.edtRua);
        cidade = findViewById(R.id.edtCidade);
        estado = findViewById(R.id.edtEstado);
        telefone = findViewById(R.id.edtTelefone);
        complemento = findViewById(R.id.edtComplemento);
        bairro = findViewById(R.id.edtBairro);
        menu = findViewById(R.id.btnMenu);
        cadastra = findViewById(R.id.btnCadastraCliente);
        db = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS cliente(_id INTEGER PRIMARY KEY AUTOINCREMENT, nome CHAR(50) NOT NULL, logadouro CHAR(50) NOT NULL, numero CHAR(10) NOT NULL, cidade CHAR(50) NOT NULL, cep CHAR(8) NOT NULL, estado CHAR(2) NOT NULL, cpf CHAR(11) NOT NULL ,telefone CHAR(11) NOT NULL, diaVencimento CHAR(2) NOT NULL, complemento CHAR(10) NOT NULL, email CHAR(100) NOT NULL, bairro CHAR(50) NOT NULL )");

        cadastra.setOnClickListener(view -> {
            if (!nome.getText().toString().isEmpty() || !numero.getText().toString().isEmpty() || !cpf.getText().toString().isEmpty() || !cep.getText().toString().isEmpty() || !dataVencimento.getText().toString().isEmpty() || !email.getText().toString().isEmpty() || !logadouro.getText().toString().isEmpty() || !cidade.getText().toString().isEmpty() || !estado.getText().toString().isEmpty() || !telefone.getText().toString().isEmpty() || !complemento.getText().toString().isEmpty() || !bairro.getText().toString().isEmpty()){
                InserirCliente();
                nome.setText("");
                numero.setText("");
                cpf.setText("");
                cep.setText("");
                dataVencimento.setText("");
                email.setText("");
                logadouro.setText("");
                cidade.setText("");
                estado.setText("");
                telefone.setText("");
                complemento.setText("");
                bairro.setText("");
                Toast.makeText(this, "Cliente cadastrado Com sucesso!!", Toast.LENGTH_SHORT).show();
                nome.requestFocus();
            }
            });

        menu.setOnClickListener(view -> {
            Menu();
        });
    }
    public void InserirCliente(){
        ContentValues registro = new ContentValues();
        registro.put("nome", nome.getText().toString());
        registro.put("logadouro", logadouro.getText().toString());
        registro.put("numero", numero.getText().toString());
        registro.put("cidade", cidade.getText().toString());
        registro.put("cep", cep.getText().toString());
        registro.put("estado", estado.getText().toString());
        registro.put("cpf",cpf.getText().toString());
        registro.put("telefone",telefone.getText().toString());
        registro.put("diaVencimento", dataVencimento.getText().toString());
        registro.put("complemento",complemento.getText().toString());
        registro.put("email", email.getText().toString());
        registro.put("bairro", bairro.getText().toString());
        db.insert("cliente", null, registro);
        Toast.makeText(this, "Cliente adiionado com sucesso", Toast.LENGTH_SHORT).show();
    }
    public void Menu(){
        finish();
    }
}