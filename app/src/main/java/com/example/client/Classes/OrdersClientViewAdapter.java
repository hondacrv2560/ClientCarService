package com.example.client.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Models.OrderViewClient;
import com.example.client.R;

import java.util.List;

public class OrdersClientViewAdapter extends RecyclerView.Adapter<OrdersClientViewAdapter.OrdersClientViewHolder> {

    private Context context;
    private List<OrderViewClient> orderViewClients;

    @NonNull
    @Override
    public OrdersClientViewAdapter.OrdersClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orders_client_list, parent, false);

        return new OrdersClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersClientViewAdapter.OrdersClientViewHolder holder, int position) {

        OrderViewClient orderViewClient = orderViewClients.get(position);
        holder.id_user.setText((orderViewClient.idUser));
        holder.id_service.setText((orderViewClient.idService));
        holder.title_service.setText(orderViewClient.titleService);
        holder.hourService.setText(orderViewClient.hourService);
        holder.minuteService.setText(orderViewClient.minService);
        holder.dayService.setText(orderViewClient.dayService);
        holder.monthService.setText(orderViewClient.monthService);
        holder.yearService.setText(orderViewClient.yearService);
    }

    @Override
    public int getItemCount() {
        return orderViewClients.size();
    }

    class OrdersClientViewHolder extends RecyclerView.ViewHolder{

        TextView id_user;
        TextView id_service;
        TextView title_service;
        TextView hourService;
        TextView minuteService;
        TextView dayService;
        TextView monthService;
        TextView yearService;
        public OrdersClientViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
