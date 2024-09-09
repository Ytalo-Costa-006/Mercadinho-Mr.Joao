package com.example.mercadinomrjoao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

public class cadastro_produtos extends AppCompatActivity {
    EditText descricaoProduto, precoProduto, unidadeProduto;
    Button menu, cadastrarProduto;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_produtos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null );

        db.execSQL("CREATE TABLE IF NOT EXISTS produtos(_id INTEGER PRIMARY KEY AUTOINCREMENT, descricao CHAR(50) NOT NULL, preco FLOAT NOT NULL, unidade CHAR(5) NOT NULL)");

        descricaoProduto = findViewById(R.id.edtDescricao);
        precoProduto = findViewById(R.id.edtPreco);
        unidadeProduto = findViewById(R.id.edtUnidade);
        cadastrarProduto = findViewById(R.id.btnCadastra);
        menu = findViewById(R.id.btnMenu);

        cadastrarProduto.setOnClickListener(view -> {
            if (!descricaoProduto.getText().toString().isEmpty() || !precoProduto.getText().toString().isEmpty() || !unidadeProduto.getText().toString().isEmpty()){
                Inserir();
                descricaoProduto.setText("");
                precoProduto.setText("");
                unidadeProduto.setText("");
                Toast.makeText(this, "Produto Adiconado com Sucesso!!", Toast.LENGTH_SHORT).show();
                descricaoProduto.requestFocus();
            }
                });
        menu.setOnClickListener(view -> {
            Menu();
        });

    }
    public void Inserir(){
        ContentValues registro = new ContentValues();
        registro.put("descricao",descricaoProduto.getText().toString());
        registro.put("preco", Float.parseFloat(precoProduto.getText().toString()));
        registro.put("unidade", unidadeProduto.getText().toString());
        db.insert("produtos", null, registro);

    }
    public void Menu(){
        finish();
    }
}