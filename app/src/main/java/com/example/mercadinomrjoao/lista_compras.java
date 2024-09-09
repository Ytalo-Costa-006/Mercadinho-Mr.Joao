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

public class lista_compras extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DataClassCompras> dataClassListCompras;
    Button menu;
    adapter_compras adapter;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_compras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        menu = findViewById(R.id.btnMenu);
        menu.setOnClickListener(view -> finish());
        db = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);
        recyclerView = findViewById(R.id.listaCompras);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataClassListCompras = loadDataFromDatabaseCompras();
        adapter = new adapter_compras(this, dataClassListCompras,this::onDeleteClickCompras);
        recyclerView.setAdapter(adapter);
    }

    private List<DataClassCompras> loadDataFromDatabaseCompras(){
        List<DataClassCompras> dataClassComprasArrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            // SQL query to fetch data with a join
            cursor = db.rawQuery("SELECT valorCompra, dataCompra, cliente.nome, compras._id FROM compras INNER JOIN cliente ON compras.idCliente = cliente._id", null);
            if (cursor.moveToFirst()) {
                do {
                    // Get values from the cursor
                    String campo1Compras = cursor.getString(0);
                    String campo2Compras = cursor.getString(1);
                    String campo3Compras = cursor.getString(2);
                    int campo4Compras = cursor.getInt(3);
                    // Add to the list
                    dataClassComprasArrayList.add(new DataClassCompras(campo1Compras, campo2Compras, campo3Compras, campo4Compras));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // Log error
        } finally {
            // Ensure cursor is closed
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return dataClassComprasArrayList;
    }


    public void onDeleteClickCompras(DataClassCompras data, int position) {
        dataClassListCompras.remove(position);
        adapter.notifyItemRemoved(position);
        db.execSQL("DELETE FROM compras WHERE _id =" + data.getCampo4Compras());
    }

}