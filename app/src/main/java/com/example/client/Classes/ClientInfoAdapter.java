package com.example.client.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Models.ClientInfo;
import com.example.client.R;

import java.util.List;

public class ClientInfoAdapter extends RecyclerView.Adapter<ClientInfoAdapter.ClientInfoHolder> {

    public Context context;
    public List<ClientInfo> clientInfoList;

    public ClientInfoAdapter(Context context, List<ClientInfo> clientInfoList) {
        this.context = context;
        this.clientInfoList = clientInfoList;
    }

    @NonNull
    @Override
    public ClientInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.client_info_list, parent, false);
        return new ClientInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientInfoHolder holder, int position) {
        ClientInfo clientInfo = clientInfoList.get(position);
        holder.user_id.setText(clientInfo.UserId);
        holder.gov_number_car_client.setText(clientInfo.govNumberCarNewClient);
        holder.manufacturer_car_client.setText(clientInfo.manufacturerCarNewClient);
        holder.model_car_client.setText(clientInfo.modelCarNewClient);
        holder.name_surname_client.setText(clientInfo.nameSurnameNewClient);
        holder.num_phone_client.setText((clientInfo.numPhoneNewClient)+"");
    }

    @Override
    public int getItemCount() {
        return clientInfoList.size();
    }

    class ClientInfoHolder extends RecyclerView.ViewHolder{

        TextView user_id;
        TextView gov_number_car_client;
        TextView manufacturer_car_client;
        TextView model_car_client;
        TextView name_surname_client;
        TextView num_phone_client;

        public ClientInfoHolder(@NonNull View itemView) {
            super(itemView);
            user_id = itemView.findViewById(R.id.userId);
            gov_number_car_client = itemView.findViewById(R.id.govNum);
            manufacturer_car_client = itemView.findViewById(R.id.manufCar);
            model_car_client = itemView.findViewById(R.id.modelCar);
            name_surname_client = itemView.findViewById(R.id.nameSurname);
            num_phone_client = itemView.findViewById(R.id.phoneClient);
        }
    }
}
