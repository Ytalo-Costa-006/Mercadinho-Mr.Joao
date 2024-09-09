package com.example.mercadinomrjoao;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button cadastraCliente, cadastroProduto, registraCompra, btnListaProdutos, btnListaClientes, btnListaCompras;
    private SQLiteDatabase banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnListaProdutos = findViewById(R.id.btnListaProdutos);
        cadastraCliente = findViewById(R.id.btnCadastraCliente);
        cadastroProduto = findViewById(R.id.btnCadastraProduto);
        registraCompra = findViewById(R.id.bntRegistraCompra);
        btnListaClientes = findViewById(R.id.btnListaClientes);
        btnListaCompras = findViewById(R.id.btnListaCompras);
        banco = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);

        btnListaCompras.setOnClickListener(view -> {
            ListaCompras();
        });
        cadastraCliente.setOnClickListener(view -> {
            CadastraCliente();
        });
        cadastroProduto.setOnClickListener(view -> {
            CadastraProduto();
        });
        registraCompra.setOnClickListener(view -> {
            CadastroCompras();
        });
        btnListaProdutos.setOnClickListener(view -> {
            ListaProdutos();
        });
        btnListaClientes.setOnClickListener(view -> {
            ListaClientes();
        });

    }
    public void CadastraCliente(){
        Intent intent = new Intent(this, tela_cadastro_clientes.class);
        startActivity(intent);
    }

    public void ListaProdutos(){
        Intent intent = new Intent(this, lista_produtos.class);
        startActivity(intent);
    }
    public void CadastraProduto(){
        Intent intent = new Intent(this, cadastro_produtos.class);
        startActivity(intent);
    }
    public void CadastroCompras(){
        Intent intent = new Intent(this, cadastro_de_compras.class);
        startActivity(intent);
    }
    public void ListaClientes(){
        Intent intent = new Intent(this, lista_de_clientes.class);
        startActivity(intent);
    }
    public void ListaCompras(){
        Intent intent = new Intent(this, lista_compras.class);
        startActivity(intent);
    }

}