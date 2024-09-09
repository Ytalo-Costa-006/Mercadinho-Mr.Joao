package com.example.mercadinomrjoao;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ItemAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private final OnItemDeleteListener listener;
    private final Context context;
    private final List<DataClass> dataList;


    public ItemAdapter(Context context, List<DataClass> dataList, OnItemDeleteListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DataClass data = dataList.get(position);
        holder.textViewAdapter1.setText("Descrição: " + data.getCampo1());
        holder.textViewAdapter2.setText("Preço: R$" +data.getCampo2());
        holder.textViewAdapter3.setText("Unidade: " +data.getCampo3());
        holder.textViewAdapter4.setText("ID: " + data.getCampo4());
        holder.btnDeletaProduto.setOnClickListener(view -> {
            if (listener != null){
                listener.onDeleteClick(data, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public interface OnItemDeleteListener {
        void onDeleteClick(DataClass data, int position);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textViewAdapter1, textViewAdapter2, textViewAdapter3, textViewAdapter4;
    Button btnDeletaProduto;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewAdapter1 = itemView.findViewById(R.id.textViewAdapter1);
        textViewAdapter2 = itemView.findViewById(R.id.textViewAdapter2);
        textViewAdapter3 = itemView.findViewById(R.id.textViewAdapter3);
        textViewAdapter4 = itemView.findViewById(R.id.textViewAdapter4);
        btnDeletaProduto = itemView.findViewById(R.id.btnDeletaProduto);
    }
}