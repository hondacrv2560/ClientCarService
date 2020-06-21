package com.example.client.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Models.FullOrders;
import com.example.client.R;

import java.util.List;

public class CheckPaymentAdapter extends RecyclerView.Adapter<CheckPaymentAdapter.CheckPaymentHolder>  {
    public Context context;
    public List<FullOrders> fullOrdersList;

    public CheckPaymentAdapter(Context context, List<FullOrders> fullOrdersList) {
        this.context = context;
        this.fullOrdersList = fullOrdersList;
    }

    @NonNull
    @Override
    public CheckPaymentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_payment_list, parent, false);
        return new CheckPaymentAdapter.CheckPaymentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckPaymentHolder holder, int position) {
        FullOrders fullOrders = fullOrdersList.get(position);
        holder.idService.setText(fullOrders.idService);
        holder.titleService.setText(fullOrders.titleService);
        holder.category.setText(fullOrders.category);
        holder.priceService.setText(fullOrders.priceService+"");
        holder.idClient.setText(fullOrders.idClient);
        holder.idOrder.setText((fullOrders.idOrder));
        holder.currentDate.setText(fullOrders.currentDate);
        holder.currentTime.setText(fullOrders.currentTime);
        holder.box.setText((fullOrders.box));
    }

    @Override
    public int getItemCount() {
        return fullOrdersList.size();
    }

    public class CheckPaymentHolder extends RecyclerView.ViewHolder {

        TextView idService;
        TextView titleService;
        TextView category;
        TextView priceService;
        TextView idClient;
        TextView idOrder;
        TextView currentDate;
        TextView currentTime;
        TextView box;

        public CheckPaymentHolder(@NonNull View itemView) {
            super(itemView);
            idService = itemView.findViewById(R.id.idService);
            titleService = itemView.findViewById(R.id.titleService);
            category = itemView.findViewById(R.id.category);
            priceService = itemView.findViewById(R.id.priceService);
            idClient = itemView.findViewById(R.id.idClient);
            idOrder = itemView.findViewById(R.id.idOrder);
            currentDate = itemView.findViewById(R.id.currentDate);
            currentTime = itemView.findViewById(R.id.currentTime);
            box = itemView.findViewById(R.id.box);
        }
    }
}
