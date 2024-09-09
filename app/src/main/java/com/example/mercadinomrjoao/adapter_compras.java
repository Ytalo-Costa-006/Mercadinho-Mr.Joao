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

public class adapter_compras extends RecyclerView.Adapter<MyViewHolderCompras> {
    private Context context;
    private List<DataClassCompras> dataClassComprasList;
    private OnItemDeleteListenerCompras listenerCompras;

    public adapter_compras(Context context, List<DataClassCompras> dataClassComprasList, OnItemDeleteListenerCompras listenerCompras){
        this.context = context;
        this.dataClassComprasList = dataClassComprasList;
        this.listenerCompras = listenerCompras;

    }

    @NonNull
    @Override
    public MyViewHolderCompras onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.compras_adapter, parent, false);
        return new MyViewHolderCompras(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCompras holder, int position) {
        DataClassCompras data = dataClassComprasList.get(position);
        holder.nomeCliente.setText("Nome: " + data.getCampo1Compras());
        holder.valorCompra.setText("Valor: R$" + data.getCampo2Compras());
        holder.dataCompra.setText("Data: " + data.getCampo3Compras());
        holder.idCompra.setText("ID Compra: " + data.getCampo4Compras());
        holder.btnDeletaCompra.setOnClickListener(view -> {
            if (listenerCompras != null){
                listenerCompras.onDeleteClickCompras(data, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataClassComprasList.size();
    }
}
    interface OnItemDeleteListenerCompras{
        void onDeleteClickCompras(DataClassCompras data, int position);

    }

class MyViewHolderCompras extends RecyclerView.ViewHolder{
    TextView nomeCliente, dataCompra, valorCompra, idCompra;
    Button btnDeletaCompra;


    public MyViewHolderCompras(@NonNull View itemView) {
        super(itemView);
        nomeCliente = itemView.findViewById(R.id.textViewComprasAdapter1);
        dataCompra = itemView.findViewById(R.id.textViewComprasAdapter2);
        valorCompra = itemView.findViewById(R.id.textViewComprasAdapter3);
        idCompra = itemView.findViewById(R.id.textViewComprasAdapter4);
        btnDeletaCompra = itemView.findViewById(R.id.btnDeletaCompras);
    }
}
