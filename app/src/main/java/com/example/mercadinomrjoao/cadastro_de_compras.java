package com.example.mercadinomrjoao;

import static java.time.LocalDate.now;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class cadastro_de_compras extends AppCompatActivity {
    TextView menu, buscar,valorUnitarioProduto, valorTotal,valorCompra;
    EditText edtBusca, edtData, quantidadeProduto,idProduto;
    Button btnCadastraCompra, btnAdicionarItens;
    private SQLiteDatabase db;
    TextView textViewDataVencimento;
    String diaVencimento,valorDoProduto;
    Calendar calendar = Calendar.getInstance();
    int diaAtual = calendar.get(Calendar.DAY_OF_MONTH);
    int mesAtual = calendar.get(Calendar.MONTH);
    float valorTotalAcumulado =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_de_compras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS compras (_id INTEGER PRIMARY KEY AUTOINCREMENT, idCliente INTEGER NOT NULL, dataCompra DATE NOT NULL, valorCompra FLOAT NOT NULL, dataPagamento DATE, FOREIGN KEY (idCliente) REFERENCES cliente(_id))");
        db.execSQL("CREATE TABLE IF NOT EXISTS item_compra (_id INTEGER PRIMARY KEY AUTOINCREMENT, idProduto INTEGER NOT NULL, idCompra INTEGER NOT NULL, unitario FLOAT NOT NULL, quantidade FLAOT NOT NULL, total FLOAT NOT NULL, FOREIGN KEY (idProduto) REFERENCES produtos(_id), FOREIGN KEY (idCompra) REFERENCES compras(_id))");
        IniciaComponentes();
        menu.setOnClickListener(view -> {
            Menu();
        });
        buscar.setOnClickListener(view -> {
            BuscaCliente();
        });
        btnCadastraCompra.setOnClickListener(view -> {
            if (!edtData.getText().toString().isEmpty()){
                InserirCompra();
                valorCompra.setText("Valor Da Compra: R$" + String.valueOf(valorTotalAcumulado));
                Toast.makeText(this, "Compra Criada com Sucesso!!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Preencha Todos os Campos!!", Toast.LENGTH_SHORT).show();
            }

        });
        btnAdicionarItens.setOnClickListener(view -> {
            AdicionaProduto();
        });
        }
    void IniciaComponentes(){
        edtBusca = findViewById(R.id.edtBusca);
        edtData = findViewById(R.id.edtData);
        valorCompra = findViewById(R.id.valorCompra);
        valorUnitarioProduto = findViewById(R.id.valorUnidade);
        quantidadeProduto = findViewById(R.id.edtQuantidade);
        valorTotal = findViewById(R.id.valorTotal);
        buscar = findViewById(R.id.btnBuscar);
        btnCadastraCompra = findViewById(R.id.btnCadastraCompra);
        btnAdicionarItens = findViewById(R.id.btnAdicionarItens);
        menu = findViewById(R.id.btnMenu);
        textViewDataVencimento = findViewById(R.id.textViewDataVencimento);
        idProduto = findViewById(R.id.edtIdProduto);
    }
    public void Menu(){
        finish();
    }

    public void InserirCompra(){
        ContentValues registro = new ContentValues();

        registro.put("dataCompra", edtData.getText().toString());
        Date dttmp = new Date();
        registro.put("valorCompra",valorTotalAcumulado);
        db.insert("compras",null, registro);

    }
    public void BuscaCliente(){
        String idDigitado = edtBusca.getText().toString().trim();
        if (!idDigitado.isEmpty()){

            String query = "SELECT diaVencimento FROM cliente WHERE _id = ?";
            Cursor cursor = db.rawQuery(query, new String[]{idDigitado});

            if (cursor.moveToFirst()) {
                diaVencimento = cursor.getString(0);
                textViewDataVencimento.setText("Dia de vencimento: " + diaVencimento);
            } else {
                textViewDataVencimento.setText("Cliente não encontrado.");
            }
            cursor.close();
        }
    }
    public void AdicionaProduto(){
        String idDigitadoProduto = idProduto.getText().toString().trim();
        if (!idDigitadoProduto.isEmpty()){

            String query = "SELECT preco FROM produtos WHERE _id = ?";
            Cursor cursor = db.rawQuery(query, new String[]{idDigitadoProduto});

            if (cursor.moveToFirst()) {
                valorDoProduto = cursor.getString(0);
                valorUnitarioProduto.setText("Valor da Unidade: R$" + valorDoProduto);
            }
            float quantidadeProdutoFloat =  Float.parseFloat(quantidadeProduto.getText().toString());
            float valorProdutoFloat = Float.parseFloat(valorDoProduto);

            float valorItemQuantidade = valorProdutoFloat * quantidadeProdutoFloat;

            valorTotalAcumulado += valorItemQuantidade;
            valorTotal.setText("Valor Total: R$" + valorProdutoFloat * quantidadeProdutoFloat);
            cursor.close();
        }

    }
}
