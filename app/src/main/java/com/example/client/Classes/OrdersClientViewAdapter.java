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

    public Context context;
    public List<OrderViewClient> orderViewClients;

    public OrdersClientViewAdapter(Context context, List<OrderViewClient> orderViewClients) {
        this.context = context;
        this.orderViewClients = orderViewClients;
    }

    @NonNull
    @Override
    public OrdersClientViewAdapter.OrdersClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orders_client_list, parent, false);

        return new OrdersClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersClientViewAdapter.OrdersClientViewHolder holder, int position) {

        OrderViewClient orderViewClient = orderViewClients.get(position);
//        holder.id_user.setText((orderViewClient.UserId));
        holder.id_service.setText((orderViewClient.idService)+"");
        holder.title_service.setText((orderViewClient.titleService)+"");
        holder.hourService.setText((orderViewClient.startTimeHour)+"");
        holder.minuteService.setText((orderViewClient.startTimeMinute)+"");
        holder.dayService.setText((orderViewClient.startDayOfMonth)+"");
        holder.monthService.setText((orderViewClient.startTimeMonth)+"");
        holder.yearService.setText((orderViewClient.startTimeYear)+"");
    }

    @Override
    public int getItemCount() {
        return orderViewClients.size();
    }

    class OrdersClientViewHolder extends RecyclerView.ViewHolder{

//        TextView id_user;
        TextView id_service;
        TextView title_service;
        TextView hourService;
        TextView minuteService;
        TextView dayService;
        TextView monthService;
        TextView yearService;
        public OrdersClientViewHolder(@NonNull View itemView) {
            super(itemView);
//            id_user = itemView.findViewById(R.id.id_user);
            id_service = itemView.findViewById(R.id.id_service);
            title_service = itemView.findViewById(R.id.title_service);
            hourService = itemView.findViewById(R.id.hourService);
            minuteService = itemView.findViewById(R.id.minuteService);
            dayService = itemView.findViewById(R.id.dayService);
            monthService = itemView.findViewById(R.id.monthService);
            yearService = itemView.findViewById(R.id.yearService);
        }
    }
}
