package com.example.mercadinomrjoao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cliente_adapter extends RecyclerView.Adapter<MyViewHolderCliente> {
    private final OnItemDeleteListenerCliente listener;
    private Context context;
    private List<DataClassClientes> dataList; // Mudança para DataClassClientes

    public cliente_adapter(Context context, List<DataClassClientes> dataList, OnItemDeleteListenerCliente listener) { // Correção aqui também
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cliente_adapter, parent, false);
        return new MyViewHolderCliente(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCliente holder, int position) {
        DataClassClientes data = dataList.get(position); // Mudança para DataClassClientes
        holder.textViewAdapter1.setText("Nome: " + data.getNome()); // Usando o método correto
        holder.textViewAdapter2.setText("CPF: " + data.getCpf());
        holder.textViewAdapter3.setText("Data Vencimento: " + data.getDiaVencimento());
        holder.textViewAdapter4.setText("ID: " + data.getId());
        holder.btnDeletaCliente.setOnClickListener(view -> {
            if (listener != null){
                listener.onDeleteClickCliente(data, position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
public interface OnItemDeleteListenerCliente {
    void onDeleteClickCliente(DataClassClientes data, int position);

    }
}

class MyViewHolderCliente extends RecyclerView.ViewHolder {
    TextView textViewAdapter1, textViewAdapter2, textViewAdapter3, textViewAdapter4;
    Button btnDeletaCliente;
    public MyViewHolderCliente(@NonNull View itemView) {
        super(itemView);
        textViewAdapter1 = itemView.findViewById(R.id.textViewClienteAdapter1);
        textViewAdapter2 = itemView.findViewById(R.id.textViewClienteAdapter2);
        textViewAdapter3 = itemView.findViewById(R.id.textViewClienteAdapter3);
        textViewAdapter4 = itemView.findViewById(R.id.textViewClienteAdapter4);
        btnDeletaCliente = itemView.findViewById(R.id.btnDeletaCliente);
    }
}
