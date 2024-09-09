package com.example.mercadinomrjoao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class lista_de_clientes extends AppCompatActivity {
    cliente_adapter adapter;
    RecyclerView recyclerView;
    Button menu;
    private SQLiteDatabase db;
    private List<DataClassClientes> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_de_clientes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        menu = findViewById(R.id.btnMenu);
        menu.setOnClickListener(view -> {
            finish();
        });

        db = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);
        recyclerView = findViewById(R.id.listaClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dataList = loadDataFromDatabaseCliente();


        adapter = new cliente_adapter(this, dataList, this::onDeleteClickCliente );
        recyclerView.setAdapter(adapter);
    }


    private List<DataClassClientes> loadDataFromDatabaseCliente() {
        List<DataClassClientes> dataListClientes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT nome, cpf, diaVencimento, _id FROM cliente", null);
        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(0);
                String cpf = cursor.getString(1);
                String diaVencimento = cursor.getString(2);
                int id = cursor.getInt(3);
                dataListClientes.add(new DataClassClientes(nome, cpf, diaVencimento, id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataListClientes;
    }
    public void onDeleteClickCliente(DataClassClientes data, int position) {

        dataList.remove(position);
        adapter.notifyItemRemoved(position);
        db.execSQL("DELETE FROM cliente WHERE _id =" + data.getId());
    }

}
