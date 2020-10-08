package com.example.client.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Models.SearchClientInfo;
import com.example.client.R;

import java.util.List;

public class SearchClientInfoAdapter extends RecyclerView.Adapter<SearchClientInfoAdapter.SearchClientInfoHolder>{

    public Context context;
    public List<SearchClientInfo> searchClientInfoList;

    public SearchClientInfoAdapter(Context context, List<SearchClientInfo> searchClientInfoList) {
        this.context = context;
        this.searchClientInfoList = searchClientInfoList;
    }

    @NonNull
    @Override
    public SearchClientInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_client_list, parent, false);
        return new SearchClientInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchClientInfoHolder holder, int position) {

        SearchClientInfo searchClientInfo = searchClientInfoList.get(position);
        holder.searchGovNumCar.setText(searchClientInfo.carGovNumber);
        holder.searchPhoneClient.setText(searchClientInfo.phoneNumberClient);
    }

    @Override
    public int getItemCount() {
        return searchClientInfoList.size();
    }


    class SearchClientInfoHolder extends RecyclerView.ViewHolder {

        TextView searchPhoneClient;
        TextView searchGovNumCar;
//        TextView startDayOfMonth;
//        TextView startTimeMonth;
//        TextView startTimeYear;
        public SearchClientInfoHolder(@NonNull View itemView) {
            super(itemView);

            searchGovNumCar = itemView.findViewById(R.id.searchGovNumberCar);
            searchPhoneClient = itemView.findViewById(R.id.searchNumberPhone);
        }
    }
}
