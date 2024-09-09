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

public class lista_produtos extends AppCompatActivity{
    RecyclerView recyclerView;
    List<DataClass> dataClassList;
    Button menu;
    ItemAdapter adapter;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_produtos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        menu = findViewById(R.id.btnMenu);
        menu.setOnClickListener(view -> {
            Menu();
        });

        db = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);
        recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataClassList = loadDataFromDatabase();


        adapter = new ItemAdapter(this, dataClassList, this::onDeleteClick);
        recyclerView.setAdapter(adapter);
    }
    private List<DataClass> loadDataFromDatabase(){
        List<DataClass> dataList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT descricao, preco, unidade,_id FROM produtos",null);
        if (cursor.moveToFirst()){
            do {
                String campo1 = cursor.getString(0);
                String campo2 = cursor.getString(1);
                String campo3 = cursor.getString(2);
                int campo4 = cursor.getInt(3);
                dataList.add(new DataClass(campo1,campo2,campo3,campo4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }
    public void Menu(){
        finish();
    }
    public void onDeleteClick(DataClass data, int position) {
        dataClassList.remove(position);
        adapter.notifyItemRemoved(position);
        db.execSQL("DELETE FROM PRODUTOS WHERE _id =" + data.getCampo4());
    }
}